package skyglass.publisher.domain;

public interface MagazineFactory {

	public Magazine createMagazine(String name, Editor editor);

	public MagazineEdition createEdition(String name, Magazine magazin);

	public Topic createTopic(String name);

}
