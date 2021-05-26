package skyglass.publisher.service;

import java.util.HashMap;
import java.util.Map;

import skyglass.publisher.domain.Article;
import skyglass.publisher.domain.ArticleFactory;
import skyglass.publisher.domain.Copywriter;
import skyglass.publisher.domain.Journalist;
import skyglass.publisher.domain.MagazineEdition;
import skyglass.publisher.domain.SuggestedChange;
import skyglass.publisher.domain.Topic;

public class ArticleFactoryImpl implements ArticleFactory {

	private Map<String, Article> articles = new HashMap<>();

	@Override
	public Article submitDraftArticle(MagazineEdition edition, Journalist journalist, String title, String content, Topic... topics) {
		Article article = new Article(title, content, journalist);
		article.assignTopics(edition, topics);
		articles.put(article.getUuid(), article);
		return article;
	}

	@Override
	public SuggestedChange suggestChange(Article article, Copywriter copywriter, String comment) {
		SuggestedChange suggestedChange = new SuggestedChange(copywriter, comment);
		article.suggestChanges(suggestedChange);
		return suggestedChange;
	}

}
