package com.letv.test.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * BaseTestCase without Transaction
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:07
 * 
 */
@ContextConfiguration(locations = { TestConstants.LOCATIONS })
public abstract class BaseTestCase extends AbstractJUnit4SpringContextTests {

}
