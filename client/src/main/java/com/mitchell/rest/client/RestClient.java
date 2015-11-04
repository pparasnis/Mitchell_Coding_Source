package com.mitchell.rest.client;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.mitchell.examples.claim.Claims;
import com.mitchell.examples.claim.MitchellClaimType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class RestClient {

	public boolean createClaimClientService(MitchellClaimType mitchellClaimType) {
		boolean success = false;
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8181/RestJersey/webresources/myresource/createClaim");

		ClientResponse response = webResource.type("application/xml").put(
				ClientResponse.class, mitchellClaimType);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		} else {
			success = true;
		}

		return success;
	}

	public boolean updateClaimClientService(MitchellClaimType mitchellClaimType) {
		boolean success = false;
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8181/RestJersey/webresources/myresource/updateClaim");

		ClientResponse response = webResource.type("application/xml").post(
				ClientResponse.class, mitchellClaimType);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		} else {
			success = true;
		}
		return success;
	}

	public boolean deleteClaimClientService(String claimId) {
		boolean success = false;
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8181/RestJersey/webresources/myresource/deleteClaim");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("claimId", claimId);

		ClientResponse response = webResource.queryParams(queryParams).get(
				ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		} else {
			success = true;
		}
		return success;

	}

	public MitchellClaimType findClaimByClaimIdClientService(String claimId) {
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8181/RestJersey/webresources/myresource/findClaimByClaimId");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("claimId", claimId);

		ClientResponse response = webResource.queryParams(queryParams).get(
				ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		return response.getEntity(MitchellClaimType.class);

	}

	public Claims findClaimbyLossDateClientService(Date startDate, Date endDate) {
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8181/RestJersey/webresources/myresource/findClaimbyLossDate");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		queryParams.add("startDate", df.format(startDate));
		queryParams.add("endDate", df.format(endDate));

		ClientResponse response = webResource.queryParams(queryParams).get(
				ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		return response.getEntity(Claims.class);

	}

}
