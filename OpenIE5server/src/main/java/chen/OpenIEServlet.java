package chen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import scala.collection.Iterator;
import scala.collection.Seq;

import edu.knowitall.openie.OpenIE;
import edu.knowitall.openie.Instance;
import edu.knowitall.openie.Argument;

public class OpenIEServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6058488729297529707L;
	static private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String text = req.getParameter("sent");
		resp.setContentType("json;charset=utf-8");
		resp.setStatus(HttpStatus.OK_200);
		if (text != null) {
			try {
				resp.getWriter().println(extractRelations(OpenIEServer.openIE, text));
			} catch (JsonProcessingException e) {
				resp.getWriter().println("Parsing error, check your text string please");
			}
			return;
		}
		resp.getWriter().println("No sentence found!");
	}

	private static String extractRelations(OpenIE openie, String text) throws JsonProcessingException {
		Seq<Instance> extractions = openie.apply(text);
		List<RelationPojo> pojos = new ArrayList<RelationPojo>();
		Iterator<Instance> iterator = extractions.iterator();
		while (iterator.hasNext()) {
			Instance inst = iterator.next();
			RelationPojo pojo = new RelationPojo();
			pojo.setConfidence(inst.confidence());
			pojo.setContext(inst.extr().context().toString());
			pojo.setNegated(inst.extr().negated());
			pojo.setPassive(inst.extr().passive());
			pojo.setRel(inst.extr().rel().text());
			pojo.setArg1(inst.extr().arg1().text());

			Iterator<Argument> argIter = inst.extr().arg2s().iterator();
			List<String> arg2s = new ArrayList<String>();
			while (argIter.hasNext()) {
				Argument arg2 = argIter.next();
				arg2s.add(arg2.text());
			}
			pojo.setArg2s(arg2s);
			pojos.add(pojo);
		}
		return ow.writeValueAsString(pojos);
	}
}
