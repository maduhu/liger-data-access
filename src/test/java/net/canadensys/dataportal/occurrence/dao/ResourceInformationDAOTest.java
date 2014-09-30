package net.canadensys.dataportal.occurrence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import net.canadensys.dataportal.occurrence.model.ResourceContactModel;
import net.canadensys.dataportal.occurrence.model.ResourceInformationModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Test Coverage :
 * -Save ResourceInformationModel
 * -Get generated id
 * -Load ResourceInformationModel from id
 * -Delete ResourceInformationModel
 * 
 * @author Pedro Guimarães
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring.xml" })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager")
public class ResourceInformationDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ResourceInformationDAO resourceInformationDAO;

	private JdbcTemplate jdbcTemplate;

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Test
	public void testSaveLoadDelete() {
		// Test ResourceInformation model:
		ResourceInformationModel testResourceInformation = new ResourceInformationModel();
		testResourceInformation.set_abstract("This is the lorem ipsum abstract");
		testResourceInformation.setTitle("TitleTitleTitle");

		// Add contacts to the resource:
		Set<ResourceContactModel> contacts = new HashSet<ResourceContactModel>();

		// Create contact:
		ResourceContactModel testResourceContact = new ResourceContactModel();
		testResourceContact.setName("Test Name");
		testResourceContact.setEmail("a@a.com");
		testResourceContact.setResourceInformation(testResourceInformation);
		contacts.add(testResourceContact);

		// Add other contact
		ResourceContactModel testResourceContact2 = new ResourceContactModel();
		testResourceContact2.setName("Test Name 2");
		testResourceContact2.setEmail("a2@a2.com");
		testResourceContact2.setResourceInformation(testResourceInformation);
		contacts.add(testResourceContact2);

		// Add contacts to information and save information
		testResourceInformation.setContacts(contacts);
		assertTrue(resourceInformationDAO.save(testResourceInformation));

		// Fetch ids from added objects:
		Integer informationId = testResourceInformation.getAuto_id();
		int contact1Id = testResourceContact.getAuto_id();
		int contact2Id = testResourceContact2.getAuto_id();

		ResourceInformationModel loadedInformation = resourceInformationDAO.load(informationId);
		assertEquals("This is the lorem ipsum abstract", loadedInformation.get_abstract());
		assertEquals("TitleTitleTitle", loadedInformation.getTitle());
		// Test contacts:
		assertEquals(contacts, loadedInformation.getContacts());

		// Test contacts load:
		Set<ResourceContactModel> resourceContacts = loadedInformation.getContacts();
		ResourceContactModel loadedContact1 = null;
		ResourceContactModel loadedContact2 = null;
		
		for (ResourceContactModel contact : resourceContacts) {
			Integer autoId = contact.getAuto_id();
			if (autoId == contact1Id) {
				loadedContact1 = contact;
			}
			else if (autoId == contact2Id) {
				loadedContact2 = contact;
			}
			else {
				fail("Unknown contact for auto_id: " + autoId);
			}
		}
		
		assertEquals("Test Name", loadedContact1.getName());
		assertEquals("a@a.com", loadedContact1.getEmail());
		assertEquals(informationId, loadedContact1.getResourceInformation().getAuto_id());
		// Assert resource_information_fkey is being filled:
		Integer fkey = jdbcTemplate.queryForObject("SELECT resource_information_fkey FROM resource_contact WHERE name =\'Test Name\'", Integer.class);
		assertEquals(fkey, informationId);

		assertEquals("Test Name 2", loadedContact2.getName());
		assertEquals("a2@a2.com", loadedContact2.getEmail());
		assertEquals(informationId, loadedContact2.getResourceInformation().getAuto_id());
		// Assert resource_information_fkey is being filled:
		fkey = jdbcTemplate.queryForObject("SELECT resource_information_fkey FROM resource_contact WHERE name =\'Test Name 2\'", Integer.class);
		assertEquals(fkey, informationId);
		
		// Test cascade deletion of information and all contacts after information deletion:
		assertTrue(resourceInformationDAO.delete(loadedInformation));
		// Assert information was deleted:
		assertNull(resourceInformationDAO.load(informationId));

		// Assert contacts were also deleted
		Long contactCount = jdbcTemplate.queryForObject("SELECT count(*) FROM resource_contact WHERE auto_id=" + contact1Id + " OR auto_id ="
				+ contact2Id, Long.class);
		assertEquals(0, contactCount.intValue()); 
	}
}
