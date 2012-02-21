package com.dss.test.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

@Test(groups = { "integrate.init" })
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class EnvironmentTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ApplicationContext context;

	public void springStartUp() {
		Assert.assertEquals(context.containsBean(""), false);
	}

}
