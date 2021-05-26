package skyglass.publisher.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import skyglass.publisher.service.ArticleFactoryImpl;
import skyglass.publisher.service.MagazineFactoryImpl;
import skyglass.publisher.service.UserFactoryImpl;

public class ArticleServiceTest {

	private UserFactory userFactory;

	private MagazineFactory magazineFactory;

	private ArticleFactory articleFactory;

	@Before
	public void init() {
		this.userFactory = new UserFactoryImpl();
		this.magazineFactory = new MagazineFactoryImpl();
		this.articleFactory = new ArticleFactoryImpl();
	}

	@Test
	public void articleTest() {
		Editor editor = userFactory.createEditor("editor");

		Magazine magazine = magazineFactory.createMagazine("magazine1", editor);
		MagazineEdition edition = magazineFactory.createEdition("edition", magazine);

		Topic topic1 = magazineFactory.createTopic("topic1");
		Topic topic2 = magazineFactory.createTopic("topic2");
		Topic topic3 = magazineFactory.createTopic("topic3");

		edition.assignTopics(editor, topic1, topic2, topic3);

		Copywriter copywriter1 = userFactory.createCopywriter("John Doe");
		Copywriter copywriter2 = userFactory.createCopywriter("John Copywriter");
		Copywriter copywriter3 = userFactory.createCopywriter("John Reviewer");

		Journalist journalist1 = userFactory.createJournalist("journalist1");
		Journalist journalist2 = userFactory.createJournalist("journalist2");

		Article article1 = articleFactory.submitDraftArticle(edition, journalist1, "article1", "content1", topic1);
		Article article2 = articleFactory.submitDraftArticle(edition, journalist2, "article2", "content2", topic2);

		article1.assignCopywriters(copywriter1);
		article2.assignCopywriters(copywriter2, copywriter3);

		article2.assignTopics(edition, topic3);

		SuggestedChange suggestedChange1 = articleFactory.suggestChange(article1, copywriter1, "test1");
		SuggestedChange suggestedChange2 = articleFactory.suggestChange(article1, copywriter1, "test2");
		SuggestedChange suggestedChange3 = articleFactory.suggestChange(article2, copywriter2, "test3");
		SuggestedChange suggestedChange4 = articleFactory.suggestChange(article2, copywriter2, "test4");
		SuggestedChange suggestedChange5 = articleFactory.suggestChange(article2, copywriter3, "test5");

		article1.respondToSuggestion(journalist1, "article1 test response", suggestedChange1);
		article1.respondToSuggestion(journalist1, "article1 test2 response", suggestedChange2);
		article2.respondToSuggestion(journalist2, "article2 test3 response", suggestedChange3);
		article2.respondToSuggestion(journalist2, "article2 test4 response", suggestedChange4);
		article2.respondToSuggestion(journalist2, "article2 test5 response", suggestedChange5);

		suggestedChange1.resolveSuggestion(copywriter1);
		suggestedChange2.resolveSuggestion(copywriter1);
		suggestedChange3.resolveSuggestion(copywriter2);
		suggestedChange4.resolveSuggestion(copywriter2);
		suggestedChange5.resolveSuggestion(copywriter3);

		article1.publishArticle(journalist1);
		article2.publishArticle(journalist2);

		String article1Content = article1.getLatestRevision();
		String article2Content = article2.getLatestRevision();

		Assert.assertEquals("article1 test2 response", article1Content);
		Assert.assertEquals("article2 test5 response", article2Content);

	}

}
