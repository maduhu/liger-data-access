package net.canadensys.dataportal.occurrence.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test PublisherInformationModel serialization since it contains cyclic references.
 *  
 * @author Pedro Guimarães
 * 
 */
public class PublisherInformationModelTest {

	@Test
	public void PublisherInformationModel() {
		
		PublisherInformationModel publisherInformationModel = new PublisherInformationModel();
		publisherInformationModel.setName("test Publisher name");

		PublisherContactModel publisherContactModel = new PublisherContactModel();
		publisherContactModel.setName("Contact Name");
		publisherInformationModel.addContact(publisherContactModel);

		ObjectMapper om = new ObjectMapper();
		try {
			// serialize as json
			String json = om.writeValueAsString(publisherInformationModel);

			// read to object back from json string
			PublisherInformationModel publisherInformationModelFromJson = om.readValue(json, PublisherInformationModel.class);
			PublisherContactModel firstContactFromJson = publisherInformationModelFromJson.getContacts().iterator().next();

			assertEquals("Contact Name", firstContactFromJson.getName());

			// check that the reference to 'parent' PublisherInformationModel is there
			assertNotNull(firstContactFromJson.getPublisherInformation());
			assertEquals("test Publisher name", firstContactFromJson.getPublisherInformation().getName());
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}