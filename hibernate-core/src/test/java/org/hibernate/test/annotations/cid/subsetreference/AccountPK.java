package org.hibernate.test.annotations.cid.subsetreference;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AccountPK implements Serializable {
	public String type;
	public long accountId;

	public AccountPK(String type, long accountId) {
		this.type = type;
		this.accountId = accountId;
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		AccountPK accountPK = (AccountPK) o;

		if ( accountId != accountPK.accountId ) {
			return false;
		}
		if ( !type.equals( accountPK.type ) ) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = type.hashCode();
		result = 31 * result + (int) (accountId ^ (accountId >>> 32));
		return result;
	}
}
