package org.hibernate.test.annotations.cid.subsetreference;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReferencePK implements Serializable {
	public int refId;
	public int rowId;

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		ReferencePK that = (ReferencePK) o;

		if ( refId != that.refId ) {
			return false;
		}
		if ( rowId != that.rowId ) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = refId;
		result = 31 * result + rowId;
		return result;
	}
}
