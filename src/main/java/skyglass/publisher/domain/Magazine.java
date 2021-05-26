package skyglass.publisher.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Magazine extends AObject {

	private static final long serialVersionUID = 1L;

	private String name;

	private Editor editor;

}
