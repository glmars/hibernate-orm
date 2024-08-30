package org.hibernate.test.annotations.cid.subsetreference;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Account {
	@EmbeddedId
	public AccountPK id;
}
