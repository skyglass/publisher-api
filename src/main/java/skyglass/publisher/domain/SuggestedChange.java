package skyglass.publisher.domain;

import lombok.Getter;

@Getter
public class SuggestedChange extends AObject {

	private static final long serialVersionUID = -2605942373515926530L;

	private Copywriter copywriter;

	private String comment;

	public SuggestedChange(Copywriter copywriter, String comment) {
		this.copywriter = copywriter;
		this.comment = comment;
	}

	private boolean responded;

	private boolean resolved;

	public void setResponded() {
		this.responded = true;
	}

	public void resolveSuggestion(Copywriter copywriter) {
		this.resolved = true;
	}

}
