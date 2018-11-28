package chen;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import edu.knowitall.tool.parse.ClearParser;
import edu.knowitall.tool.srl.ClearSrl;
import edu.knowitall.tool.postag.ClearPostagger;
import edu.knowitall.tool.tokenize.ClearTokenizer;
import edu.knowitall.openie.OpenIE;

public class OpenIEServer {
	public static OpenIE openIE;
	public static void main(String[] args) throws Exception {
		Server server = new Server(8777);
		openIE = new OpenIE(new ClearParser(new ClearPostagger(new ClearTokenizer())),new ClearSrl(),false,false);
		openIE.apply("I am Chen Qiao");
		ServletContextHandler context = new ServletContextHandler(server, "/");
		context.addServlet(OpenIEServlet.class, "/openie/*");
		System.out.println("Listening...");
		server.start();
	}
}
