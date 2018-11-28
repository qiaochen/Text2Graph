package chen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import de.mpii.clausie.ClausIE;
//import de.mpii.clausie.Clause;
import de.mpii.clausie.Proposition;

import java.util.List;
import java.util.ArrayList;


public class ClauseIEServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3305904397195108358L;
	static private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String text = req.getParameter("sent");
		resp.setContentType("json;charset=utf-8");
		resp.setStatus(HttpStatus.OK_200);
		if (text != null) {
			try {
				resp.getWriter().println(extractRelations(ClauseIEServer.clausIE, text));
			} catch (JsonProcessingException e) {
				resp.getWriter().println("Parsing error, check your text string please");
			}
			return;
		}
		resp.getWriter().println("No sentence found!");
	}

	private String extractRelations(ClausIE clausIE, String text) throws JsonProcessingException {
		clausIE.parse(text);
		clausIE.detectClauses();
		clausIE.generatePropositions();
		List<RelationPojo> pojos = new ArrayList<RelationPojo>();
		String sep = "";
//		for (Clause clause : clausIE.getClauses()) {
//	            System.out.println(sep + clause.toString(clausIE.getOptions()));
//	            sep = "                   ";
//	        }
//		
		for (Proposition prop : clausIE.getPropositions()) {
			RelationPojo pojo = new RelationPojo();
			pojo.setSubject(prop.subject());
			pojo.setRelation(prop.relation());
			List<Argument> args = new ArrayList<Argument>();
			for (int i=0; i<prop.noArguments(); ++i) {
				args.add(new Argument(prop.argument(i), prop.isOptionalArgument(i)));
			}
			pojo.setArguments(args);
			pojos.add(pojo);
		}
		return ow.writeValueAsString(pojos);
	}

}
