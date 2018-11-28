package chen;

import java.util.List;

public class RelationPojo {
	private double confidence;
	private String arg1;
	private String rel;
	private List<String> arg2s;
	private String context;
	private boolean negated;
	private boolean passive;
	private String rawSentence;

	public boolean isPassive() {
		return passive;
	}

	public void setPassive(boolean passive) {
		this.passive = passive;
	}

	public String getRawSentence() {
		return rawSentence;
	}

	public void setRawSentence(String rawSentence) {
		this.rawSentence = rawSentence;
	}

	public boolean isNegated() {
		return negated;
	}

	public void setNegated(boolean negated) {
		this.negated = negated;
	}

	public RelationPojo() {
		super();
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public List<String> getArg2s() {
		return arg2s;
	}

	public void setArg2s(List<String> arg2s) {
		this.arg2s = arg2s;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
