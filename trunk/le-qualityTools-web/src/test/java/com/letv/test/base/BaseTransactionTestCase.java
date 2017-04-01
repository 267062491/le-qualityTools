package com.letv.test.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Base TestCase with Transaction
 * 
 * @author wangshanjing
 * @version 2017-2-12 20:46:07
 * 
 */
@ContextConfiguration(locations = { TestConstants.LOCATIONS })
@TransactionConfiguration(transactionManager = TestConstants.TRANSACTION_MANAGER, defaultRollback = true)
public abstract class BaseTransactionTestCase extends AbstractTransactionalJUnit4SpringContextTests {

}
