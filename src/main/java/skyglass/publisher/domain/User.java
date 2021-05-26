package skyglass.publisher.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class User extends AObject {

	private static final long serialVersionUID = 4410232868992054065L;

	private String name;

}
