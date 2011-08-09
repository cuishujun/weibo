package com.blog.common.test;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext-*.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false) 
@Transactional 

public class BaseSpringTestCase {
	protected Logger log = Logger.getLogger(this.getClass());
}
