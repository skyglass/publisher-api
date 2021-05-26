package skyglass.publisher.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import lombok.Getter;

@Getter
public class MagazineEdition extends AObject {

	private static final long serialVersionUID = 962076831091544626L;

	private String name;

	private Magazine magazine;

	private List<Topic> topics = new ArrayList<>();

	private List<Article> articles = new ArrayList<>();

	public MagazineEdition(String name, Magazine magazine) {
		this.name = name;
		this.magazine = magazine;
	}

	public void assignTopics(Topic... topics) {
		this.topics.addAll(Arrays.asList(topics));
	}

	public void assignArticles(Article... articles) {
		checkTopics(articles);
		this.articles.addAll(Arrays.asList(articles));
	}

	public void checkTopics(Collection<Topic> topics) {
		for (Topic topic : topics) {
			if (!this.topics.contains(topic)) {
				throw new BusinessRuleValidationException("Not allowed topic for this magazine edition");
			}
		}
	}

	public void assignTopics(Editor editor, Topic... topics) {
		if (!Objects.equals(magazine.getEditor(), editor)) {
			throw new BusinessRuleValidationException(String.format("Editor %s is not allowed to assign topics to magazine %s", editor.getName(), magazine.getName()));
		}
		assignTopics(topics);
	}

	private void checkTopics(Article... articles) {
		for (Article article : articles) {
			if (!this.articles.contains(article)) {
				throw new BusinessRuleValidationException(String.format("Article %s is from different magazine edition", article.getTitle()));
			}
			checkTopics(article.getTopics());
		}
	}

}
