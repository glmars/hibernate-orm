package org.hibernate.test.annotations.cid.subsetreference;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.hibernate.AnnotationException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Table;
import org.hibernate.mapping.UniqueKey;
import org.hibernate.service.ServiceRegistry;

import org.junit.Test;

import org.hibernate.testing.ServiceRegistryBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

	@Test
	public void testNoFKToRootClassTable() throws Exception {
		Configuration cfg = buildMappings( ReferencePK.class, Reference.class, SampleReference.class, Order.class );

		Table order = getTable( "Order", cfg );
		Iterator iter = order.getForeignKeyIterator();

		if ( iter.hasNext() ) {
			fail("Link source table has foreign key to root class table because of link to subclass entity: " + iter.next().toString());
		}
	}

	@Test
	public void testNoUniqueKeyInRootClassTable() throws Exception {
		Configuration cfg = buildMappings( ReferencePK.class, Reference.class, SampleReference.class, Order.class );

		Table reference = getTable( "Reference", cfg );
		Iterator<UniqueKey> iter = reference.getUniqueKeyIterator();
		
		if ( iter.hasNext() ) {
			fail("Root class table has unique index because of link to subclass entity: " + iter.next().toString());
		}
	}

	private Table getTable(String tableName, Configuration cfg) {
		Iterator<Table> tableMappings = cfg.getTableMappings();
		
		while(tableMappings.hasNext()) {
			Table table = tableMappings.next();
			if ( tableName.equals( table.getName() ) ) {
				return table;
			}
		}
		
		throw new NoSuchElementException( tableName );
	}
	
	private void buildSessionFactory(Class<?>... entities) {
		Configuration cfg = buildMappings( entities );

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
	
	private Configuration buildMappings(Class<?>... entities) {
		Configuration cfg = new Configuration();
		for ( Class<?> entity : entities ) {
			cfg.addAnnotatedClass(entity);
		}
		cfg.buildMappings();
		
		return cfg;
	}
}
