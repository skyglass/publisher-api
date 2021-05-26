package skyglass.publisher.service;

import java.util.HashMap;
import java.util.Map;

import skyglass.publisher.domain.Copywriter;
import skyglass.publisher.domain.Editor;
import skyglass.publisher.domain.Journalist;
import skyglass.publisher.domain.UserFactory;

public class UserFactoryImpl implements UserFactory {

	private Map<String, Editor> editors = new HashMap<>();

	private Map<String, Copywriter> copywriters = new HashMap<>();

	private Map<String, Journalist> journalists = new HashMap<>();

	@Override
	public Editor createEditor(String name) {
		Editor editor = new Editor(name);
		editors.put(editor.getUuid(), editor);
		return editor;
	}

	@Override
	public Copywriter createCopywriter(String name) {
		Copywriter copywriter = new Copywriter(name);
		copywriters.put(copywriter.getUuid(), copywriter);
		return copywriter;
	}

	@Override
	public Journalist createJournalist(String name) {
		Journalist journalist = new Journalist(name);
		journalists.put(journalist.getUuid(), journalist);
		return journalist;
	}

}
