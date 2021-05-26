package skyglass.publisher.service;

import java.util.HashMap;
import java.util.Map;

import skyglass.publisher.domain.Editor;
import skyglass.publisher.domain.Magazine;
import skyglass.publisher.domain.MagazineEdition;
import skyglass.publisher.domain.MagazineFactory;
import skyglass.publisher.domain.Topic;

public class MagazineFactoryImpl implements MagazineFactory {

	private Map<String, Magazine> magazines = new HashMap<>();

	private Map<String, MagazineEdition> editions = new HashMap<>();

	@Override
	public Topic createTopic(String name) {
		Topic topic = new Topic(name);
		return topic;
	}

	@Override
	public Magazine createMagazine(String name, Editor editor) {
		Magazine magazine = new Magazine(name, editor);
		magazines.put(magazine.getUuid(), magazine);
		return magazine;
	}

	@Override
	public MagazineEdition createEdition(String name, Magazine magazine) {
		MagazineEdition magazineEdition = new MagazineEdition(name, magazine);
		editions.put(magazineEdition.getUuid(), magazineEdition);
		return magazineEdition;
	}

}
