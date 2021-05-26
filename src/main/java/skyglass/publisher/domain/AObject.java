package skyglass.publisher.domain;

import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Abstract base class for all domain object.
 *
 */
@NoArgsConstructor
@Getter
public abstract class AObject implements IdObject {

	private static final long serialVersionUID = -7247763056488390768L;

	private String uuid = UUID.randomUUID().toString();

	@Override
	public int hashCode() {
		return Objects.hashCode(this.getUuid());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AObject other = (AObject) obj;
		return Objects.equals(this.getUuid(), other.getUuid());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " " + this.getUuid();
	}

}
