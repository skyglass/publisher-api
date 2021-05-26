package skyglass.publisher.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class Topic extends AObject {

	private static final long serialVersionUID = 9175403019820722889L;

	private String name;

	public Topic(String name) {
		this.name = name;
	}

	private List<Article> articles;

}
