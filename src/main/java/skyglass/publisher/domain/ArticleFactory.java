package skyglass.publisher.domain;

public interface ArticleFactory {

	public Article submitDraftArticle(MagazineEdition edition, Journalist journalist, String title, String content, Topic... topic);

	public SuggestedChange suggestChange(Article article, Copywriter copywriter, String comment);

}
