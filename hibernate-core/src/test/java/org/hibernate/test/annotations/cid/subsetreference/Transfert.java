package org.hibernate.test.annotations.cid.subsetreference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transfert {
	@Id
	public long id;

	@ManyToOne
	@JoinColumn(name = "from", referencedColumnName = "accountId")
	public Account from;
}
