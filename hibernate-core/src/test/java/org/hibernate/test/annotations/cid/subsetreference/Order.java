package org.hibernate.test.annotations.cid.subsetreference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Order {
	@Id
	public int id;
	
	@ManyToOne
	@JoinColumn(name = "type", referencedColumnName = "rowId")
	SampleReference type;
}
