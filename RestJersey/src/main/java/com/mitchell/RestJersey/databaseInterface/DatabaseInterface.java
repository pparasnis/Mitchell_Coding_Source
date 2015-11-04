package com.mitchell.RestJersey.databaseInterface;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.helpers.DefaultHandler;

import com.mitchell.db.iService.IMitchellClaimDBInterface;
import com.mitchell.db.service.MitchellClaimDBFactory;
import com.mitchell.examples.claim.Claims;
import com.mitchell.examples.claim.MitchellClaimType;

public class DatabaseInterface {

	private String implementationType = "JPA";

	public IMitchellClaimDBInterface getIMitchellClaimDBInterface() {

		return MitchellClaimDBFactory
				.getIMitchellClaimDBInterface(implementationType);
	}

	private File getFile(String fileName) {
		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}

	public boolean validateClaim(MitchellClaimType mitchellClaimType)
			throws Exception {
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(getFile("MitchellClaim.xsd"));
		JAXBContext jc = JAXBContext.newInstance(MitchellClaimType.class);
		JAXBSource source = new JAXBSource(jc, mitchellClaimType);

		Validator validator = schema.newValidator();
		validator.setErrorHandler(new DefaultHandler());
		validator.validate(source);

		// System.out.println("Create claim validated");
		return true;

	}

	public void createClaim(MitchellClaimType mitchellClaimType)
			throws Exception {
		validateClaim(mitchellClaimType);
		getIMitchellClaimDBInterface().createClaim(mitchellClaimType);
	}

	public MitchellClaimType findClaimByClaimId(String claimId)
			throws Exception {
		return getIMitchellClaimDBInterface().findClaimByClaimId(claimId);
	}

	public void deleteClaimByClaimId(String claimId) throws Exception {
		getIMitchellClaimDBInterface().deleteClaimByClaimId(claimId);
	}

	public Claims findClaimsByLossDate(Date startDate, Date endDate)
			throws Exception {
		List<MitchellClaimType> mitchellClaimTypesList = getIMitchellClaimDBInterface()
				.findClaimsByLossDate(startDate, endDate);
		Claims claims = new Claims();
		claims.setClaims(mitchellClaimTypesList);
		return claims;

	}

	public void updateClaim(MitchellClaimType mitchellClaimType)
			throws Exception {
		validateClaim(mitchellClaimType);
		getIMitchellClaimDBInterface().updateClaim(mitchellClaimType);
	}

}
