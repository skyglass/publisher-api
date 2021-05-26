package skyglass.publisher.domain;

public interface UserFactory {

	public Editor createEditor(String name);

	public Copywriter createCopywriter(String name);

	public Journalist createJournalist(String name);

}
