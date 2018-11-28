package chen;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import de.mpii.clausie.ClausIE;

public class ClauseIEServer {
	public static ClausIE clausIE;
	public static void main(String[] args) throws Exception {
		Server server = new Server(8780);
		clausIE = new ClausIE();
		clausIE.initParser();
		clausIE.getOptions().print(System.out, "# ");
		ServletContextHandler context = new ServletContextHandler(server, "/");
		context.addServlet(ClauseIEServlet.class, "/clausie/*");
		System.out.println("Listening...");
		server.start();
	}
}
