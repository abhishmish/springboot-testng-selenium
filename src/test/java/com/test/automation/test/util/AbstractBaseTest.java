package com.test.automation.test.util;

import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import com.test.automation.config.*;;

@ContextConfiguration(classes= BeanConfig.class)
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties
public class AbstractBaseTest extends AbstractTestNGSpringContextTests {
    
	private static final Logger LOG = Logger.getLogger(AbstractBaseTest.class.getName());
	
	@BeforeClass(dependsOnMethods={"springTestContextPrepareTestInstance"})
	public void setConfigs(){ 
		LOG.info("Test Execution Started.");
	}
}
