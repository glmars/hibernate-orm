package org.hibernate.test.annotations.cid.subsetreference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(SampleReference.REF_ID_STR)
public class SampleReference extends Reference {
	public static final String REF_ID_STR = "123";
}
