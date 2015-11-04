package com.mitchell.RestJersey.client;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.mitchell.examples.claim.MitchellClaimType;

public class RestClientTest {

	@Test
	public void testCreateClaimClientService() throws Exception {
		File file = getFile("create-claim.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(MitchellClaimType.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
				.unmarshal(file);
		RestClient restClient = new RestClient();
		assertTrue(restClient.createClaimClientService(claim));

	}

	@Test
	public void testUpdateClaimClientService() throws Exception {
		File file = getFile("update-claim.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(MitchellClaimType.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
				.unmarshal(file);
		RestClient restClient = new RestClient();
		assertTrue(restClient.updateClaimClientService(claim));
	}

	@Test
	public void testFindClaimByClaimIdClientService() throws Exception {
		File file = getFile("create-claim.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(MitchellClaimType.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
				.unmarshal(file);
		RestClient restClient = new RestClient();

		assertTrue(restClient
				.findClaimByClaimIdClientService(claim.getClaimNumber())
				.getClaimNumber().equals(claim.getClaimNumber()));
		// /other assert statements to check remaining fields
	}

	@Test
	public void testFindClaimbyLossDateClientService() throws Exception {
		File file = getFile("create-claim.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(MitchellClaimType.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
				.unmarshal(file);
		RestClient restClient = new RestClient();
		Date claimDate = claim.getLossDate();

		Calendar c = Calendar.getInstance();
		c.setTime(claimDate);
		c.add(Calendar.DATE, -2);
		Date sDate = c.getTime();
		c.add(Calendar.DATE, 4);
		Date eDate = c.getTime();
		assertTrue(restClient.findClaimbyLossDateClientService(sDate, eDate).getClaims()
				.size() > 0);
		// /other assert statements to check remaining fields
	}

	@Test
	public void testDeleteClaimClientService() throws Exception {
		File file = getFile("create-claim.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(MitchellClaimType.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
				.unmarshal(file);
		RestClient restClient = new RestClient();
		assertTrue(restClient.deleteClaimClientService(claim.getClaimNumber()));
	}

	private File getFile(String fileName) {
		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
}
