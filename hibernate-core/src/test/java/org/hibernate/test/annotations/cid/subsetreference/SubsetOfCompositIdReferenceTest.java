package org.hibernate.test.annotations.cid.subsetreference;

import org.hibernate.AnnotationException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import org.junit.Test;

import org.hibernate.testing.ServiceRegistryBuilder;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

public class SubsetOfCompositIdReferenceTest {

	@Test
	public void testStillDontUnderstandEmbeddedId() throws Exception {
		try {
			buildSessionFactory(AccountPK.class, Account.class, Transfert.class);
			fail( "Did not throw expected exception" );
		}
		catch (AnnotationException ex) {
			assertEquals(
					"referencedColumnNames(accountId) of org.hibernate.test.annotations.cid.subsetreference.Transfert.from referencing org.hibernate.test.annotations.cid.subsetreference.Account not mapped to a single property",
					ex.getMessage());
		}
	}

	@Test
	public void testCompositIdConsistsFromColumnAndDiscriminator() throws Exception {
		buildSessionFactory(ReferencePK.class, Reference.class, SampleReference.class, Order.class);
	}

	private void buildSessionFactory(Class<?>... entities) {
		Configuration cfg = new Configuration();
		for ( Class<?> entity : entities ) {
			cfg.addAnnotatedClass(entity);
		}
		cfg.buildMappings();

		ServiceRegistry serviceRegistry = null;
		SessionFactory sessionFactory = null;
		try {
			serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( cfg.getProperties() );
			sessionFactory = cfg.buildSessionFactory( serviceRegistry );
		}
		finally {
			if( sessionFactory !=null){
				sessionFactory.close();
			}
			if ( serviceRegistry != null ) {
				ServiceRegistryBuilder.destroy( serviceRegistry );
			}
		}
	}
}
