package skyglass.publisher.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lombok.Getter;

@Getter
public class Article extends AObject {

	private static final long serialVersionUID = -2605942373515926530L;

	private String title;

	private String content;

	private Journalist journalist;

	private boolean published;

	private List<Topic> topics = new ArrayList<>();

	private List<Copywriter> copywriters = new ArrayList<>();

	private List<String> revisions = new ArrayList<>();

	private List<SuggestedChange> suggestedChanges = new ArrayList<>();

	public Article(String title, String content, Journalist journalist) {
		this.title = title;
		this.content = content;
		addRevision(content);
		this.journalist = journalist;
	}

	public void assignTopics(MagazineEdition magazineEdition, Topic... topics) {
		magazineEdition.checkTopics(Arrays.asList(topics));
		this.topics.addAll(Arrays.asList(topics));
	}

	public void assignCopywriters(Copywriter... copywriters) {
		checkPublished();
		this.copywriters.addAll(Arrays.asList(copywriters));
	}

	public void suggestChanges(SuggestedChange... suggestedChanges) {
		checkPublished();
		for (SuggestedChange suggestedChange : suggestedChanges) {
			if (!copywriters.contains(suggestedChange.getCopywriter())) {
				throw new BusinessRuleValidationException(
						String.format("Copywriter %s is not assigned to article %s",
								suggestedChange.getCopywriter().getName(), getTitle()));
			}
		}
		this.suggestedChanges.addAll(Arrays.asList(suggestedChanges));
	}

	public String getLatestRevision() {
		return revisions.get(revisions.size() - 1);
	}

	public void respondToSuggestion(Journalist journalist, String revision, SuggestedChange suggestedChange) {
		checkPublished();
		if (!Objects.equals(this.journalist, journalist)) {
			throw new BusinessRuleValidationException(
					String.format("Journalist %s is not assigned to article %s",
							journalist.getName(), getTitle()));
		}
		if (!this.copywriters.contains(suggestedChange.getCopywriter())) {
			throw new BusinessRuleValidationException(
					String.format("Copywriter %s cannot suggest changes to article %s",
							suggestedChange.getCopywriter().getName(), getTitle()));
		}
		addRevision(revision);
		suggestedChange.setResponded();
	}

	public void publishArticle(Journalist journalist) {
		if (!Objects.equals(this.journalist, journalist)) {
			throw new BusinessRuleValidationException(
					String.format("Journalist %s is not assigned to article %s",
							journalist.getName(), getTitle()));
		}
		setPublished();
	}

	private void addRevision(String revision) {
		this.revisions.add(revision);
	}

	private void setPublished() {
		this.published = true;
	}

	private void checkPublished() {
		if (published) {
			throw new BusinessRuleValidationException(
					String.format("Article %s is already published: no changes allowed",
							getTitle()));
		}
	}

}
