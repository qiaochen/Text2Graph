package chen;

import java.util.List;

public class RelationPojo {
	private String Subject;
	private String relation;
	private List<Argument> arguments;

	public RelationPojo() {
		super();
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public List<Argument> getArguments() {
		return arguments;
	}

	public void setArguments(List<Argument> arguments) {
		this.arguments = arguments;
	}

}

class Argument {
	private String arg;
	private boolean isOptional;

	public Argument(String arg, boolean isOptional) {
		this.arg = arg;
		this.isOptional = isOptional;
	}

	public String getArg() {
		return arg;
	}

	public boolean isOptional() {
		return isOptional;
	}

	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

}