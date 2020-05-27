package org.hibernate.test.annotations.cid.subsetreference;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.DiscriminatorOptions;

@Entity
@IdClass(ReferencePK.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "refId", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorOptions(insert = false)
public class Reference {
	@Id
	public int refId;
	@Id
	public int rowId;
	
	public String name;
}
