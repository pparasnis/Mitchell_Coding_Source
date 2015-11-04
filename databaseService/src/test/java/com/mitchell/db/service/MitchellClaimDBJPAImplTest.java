package com.mitchell.db.service;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mitchell.db.iService.IMitchellClaimDBInterface;
import com.mitchell.examples.claim.MitchellClaimType;

public class MitchellClaimDBJPAImplTest {

	@Before
	public void setUp() throws Exception {
		testCreateClaim();
	}

	@After
	public void tearDown() throws Exception {
		testDeleteClaimsById();
	}

	public void testCreateClaim() throws Exception {
		File file = getFile("create-claim.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(MitchellClaimType.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
				.unmarshal(file);
		IMitchellClaimDBInterface mitchellClaimDBJPAImpl = new MitchellClaimDBJPAImpl();
		mitchellClaimDBJPAImpl.createClaim(claim);
		MitchellClaimType updatedClaim = mitchellClaimDBJPAImpl
				.findClaimByClaimId(claim.getClaimNumber());
		Assert.assertTrue(claim.getClaimantFirstName().equals(
				updatedClaim.getClaimantFirstName()));
		// Other assert statements to check the remaining fields and nested
		// objects
	}

	@Test
	public void testUpdateClaim() throws Exception {
		File file = getFile("update-claim.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(MitchellClaimType.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
				.unmarshal(file);
		IMitchellClaimDBInterface mitchellClaimDBJPAImpl = new MitchellClaimDBJPAImpl();
		mitchellClaimDBJPAImpl.updateClaim(claim);

		MitchellClaimType updatedClaim = mitchellClaimDBJPAImpl
				.findClaimByClaimId(claim.getClaimNumber());
		Assert.assertTrue(claim.getClaimNumber().equals(
				updatedClaim.getClaimNumber()));
		// Other assert statements to check the updated fields in update claim
		// and
		// claim record from DB
	}

	@Test
	public void testFindClaimsByLossDate() throws Exception {
		File file = getFile("create-claim.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(MitchellClaimType.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
				.unmarshal(file);
		IMitchellClaimDBInterface mitchellClaimDBJPAImpl = new MitchellClaimDBJPAImpl();
		mitchellClaimDBJPAImpl.updateClaim(claim);

		MitchellClaimType updatedClaim = mitchellClaimDBJPAImpl
				.findClaimByClaimId(claim.getClaimNumber());
		Date claimDate = claim.getLossDate();

		Calendar c = Calendar.getInstance();
		c.setTime(claimDate);
		c.add(Calendar.DATE, -2);
		Date sDate = c.getTime();
		c.add(Calendar.DATE, 4);
		Date eDate = c.getTime();
		List<MitchellClaimType> claimsList = mitchellClaimDBJPAImpl
				.findClaimsByLossDate(sDate, eDate);
		assertTrue(claimsList.size() > 0);
		System.out.println(claimsList.size());

	}

	@Test
	public void testFindClaimByClaimId() throws Exception {
		File file = getFile("create-claim.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(MitchellClaimType.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
				.unmarshal(file);

		IMitchellClaimDBInterface mitchellClaimDBJPAImpl = new MitchellClaimDBJPAImpl();
		assertTrue(mitchellClaimDBJPAImpl
				.findClaimByClaimId(claim.getClaimNumber()).getClaimNumber()
				.equals(claim.getClaimNumber()));
	}
	

	public void testDeleteClaimsById() throws Exception {
		File file = getFile("create-claim.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(MitchellClaimType.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
				.unmarshal(file);

		IMitchellClaimDBInterface mitchellClaimDBJPAImpl = new MitchellClaimDBJPAImpl();
		mitchellClaimDBJPAImpl.deleteClaimByClaimId(claim.getClaimNumber());
	}

	private File getFile(String fileName) {
		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
}
