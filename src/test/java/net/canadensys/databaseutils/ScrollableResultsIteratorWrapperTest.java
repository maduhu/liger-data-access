package net.canadensys.databaseutils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import net.canadensys.dataportal.occurrence.model.DownloadLogModel;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.GenericJDBCException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Test ScrollableResultsIteratorWrapper implementation
 * 
 * @author cgendreau
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class ScrollableResultsIteratorWrapperTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	@Transactional
	public void testScrollableResultsIteratorWrapperHasNext() {
		Session hibernateSession = sessionFactory.getCurrentSession();
		ScrollableResults sr = hibernateSession.createCriteria(DownloadLogModel.class).add(Restrictions.eq("email", "mock@test.test")).scroll();

		ScrollableResultsIteratorWrapper<DownloadLogModel> it = new ScrollableResultsIteratorWrapper<DownloadLogModel>(sr, hibernateSession);

		int i = 0;
		while (it.hasNext()) {
			// ensure calling hasNext multiple time won't affect the result
			it.hasNext();
			it.next();
			i++;
		}

		assertNull(it.next());

		// after that we should not be able to iterate on the ScrollableResults
		boolean gotJdbcEx = false;
		try {
			assertFalse(sr.next());
		}
		catch (GenericJDBCException jdbcEx) {
			gotJdbcEx = true;
		}
		assertTrue(gotJdbcEx);

		// our test data includes 2 mock DownloadLog
		assertEquals(2, i);
	}

	@Test
	@Transactional
	public void testScrollableResultsIteratorWrapperNext() {
		Session hibernateSession = sessionFactory.getCurrentSession();
		ScrollableResults sr = hibernateSession.createCriteria(DownloadLogModel.class).add(Restrictions.eq("email", "mock@test.test")).scroll();

		ScrollableResultsIteratorWrapper<DownloadLogModel> it = new ScrollableResultsIteratorWrapper<DownloadLogModel>(sr, hibernateSession);

		int i = 0;
		while (it.next() != null) {
			// ensure calling hasNext multiple time won't affect the result
			it.hasNext();
			i++;
		}

		// after that we should not be able to iterate on the ScrollableResults
		boolean gotJdbcEx = false;
		try {
			assertFalse(sr.next());
		}
		catch (GenericJDBCException jdbcEx) {
			gotJdbcEx = true;
		}
		assertTrue(gotJdbcEx);

		// our test data includes 2 mock DownloadLog
		assertEquals(2, i);
	}

}
