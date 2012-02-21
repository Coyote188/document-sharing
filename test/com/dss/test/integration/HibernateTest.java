package com.dss.test.integration;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.testng.annotations.Test;

@Test(groups = { "integrate.init" })
public class HibernateTest {
	
	public void hibernateStartupTest(){
//		SessionFactory session = new AnnotationConfiguration().configure().buildSessionFactory();
//		Assert.assertNotNull(session);
		System.out.println(System.getProperties().getProperty("java.class.path", null));
	}

}
