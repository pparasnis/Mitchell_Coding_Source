package com.mitchell.RestJersey;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitchell.RestJersey.databaseInterface.DatabaseInterface;
import com.mitchell.examples.claim.Claims;
import com.mitchell.examples.claim.MitchellClaimType;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MitchellResource {

	private final Logger slf4jLogger = LoggerFactory
			.getLogger(MitchellResource.class);

	/**
	 * Method processing HTTP GET requests to delete, producing "text/plain"
	 * MIME media type.
	 * 
	 * @param claimId
	 * @return response
	 */
	@GET
	@Path("/deleteClaim")
	@Produces("text/plain")
	public Response deleteMitchellClaim(@QueryParam("claimId") String claimId) {
		slf4jLogger.info("Request to delete claim {}", claimId);
		DatabaseInterface databaseInterface = new DatabaseInterface();
		Response response = Response.status(102).build();
		try {
			databaseInterface.deleteClaimByClaimId(claimId);
			response = Response.status(200).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// TODO: Add exception handling code to return appropriate code 404
			// etc
			response = Response.status(500).build();
		}
		slf4jLogger.info("Request to delete claim {} completed", claimId);
		return response;
	}

	/**
	 * Method processing HTTP GET requests to find claim, producing
	 * "application/xml" MIME media type.
	 * 
	 * @param claimId
	 * @return response
	 */
	@GET
	@Path("/findClaimByClaimId")
	@Produces(MediaType.APPLICATION_XML)
	public Response findMitchellClaim(@QueryParam("claimId") String claimId) {
		slf4jLogger.info("Request to find claim {}", claimId);
		DatabaseInterface databaseInterface = new DatabaseInterface();
		MitchellClaimType mitchellClaimType;
		Response response = Response.status(102).build();
		try {
			mitchellClaimType = databaseInterface.findClaimByClaimId(claimId);
			slf4jLogger.info("Request to find claim {} completed",
					mitchellClaimType.getClaimNumber());
			response = Response.status(200).entity(mitchellClaimType).build();
		} catch (Exception e) {
			// Add exception handling code to return appropriate code 404 etc
			slf4jLogger.error("Request to find claim failed");
			e.printStackTrace();
			response = Response.status(500).build();
		}
		return response;
	}

	/**
	 * Method processing HTTP GET requests to find claim by loss date, producing
	 * "text/plain" MIME media type.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("/findClaimbyLossDate")
	@Consumes("multipart/related")
	@Produces(MediaType.APPLICATION_XML)
	public Response findMitchellClaimByLossDate(
			@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate) {
		DatabaseInterface databaseInterface = new DatabaseInterface();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Claims mitchellClaimTypeList;
		Response response = Response.status(102).build();
		try {
			slf4jLogger.info("Request to find claim by loss date completed");
			mitchellClaimTypeList = databaseInterface.findClaimsByLossDate(
					df.parse(startDate), df.parse(endDate));

			response = Response.status(200).entity(mitchellClaimTypeList)
					.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Add exception handling code to return appropriate code 404 etc
			response = Response.status(500).build();
			slf4jLogger.info("Request to find claim by loss date failed");
		}
		return response;
	}

	/**
	 * Method processing HTTP POST requests to create, producing "text/plain"
	 * MIME media type.
	 * 
	 * @param mitchellClaimType
	 * @return
	 */
	@PUT
	@Path("/createClaim")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createMitchellClaim(MitchellClaimType mitchellClaimType) {
		DatabaseInterface databaseInterface = new DatabaseInterface();
		Response response = Response.status(102).build();
		slf4jLogger.info("Request to create claim {}", mitchellClaimType);
		try {
			databaseInterface.createClaim(mitchellClaimType);
			response = Response.status(200).build();
			slf4jLogger.info("Request to create claim completed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Add exception handling code to return appropriate code 404 etc
			slf4jLogger.info("Request to create claim failed");
			response = Response.status(500).build();
		}

		return response;

	}

	/**
	 * Method processing HTTP PUT requests to update claim, producing
	 * "text/plain" MIME media type.
	 * 
	 * @param mitchellClaimType
	 * @return
	 */
	@POST
	@Path("/updateClaim")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateMitchellClaim(MitchellClaimType mitchellClaimType) {

		DatabaseInterface databaseInterface = new DatabaseInterface();
		Response response = Response.status(102).build();
		slf4jLogger.info("Request to update claim {}", mitchellClaimType);
		try {
			databaseInterface.updateClaim(mitchellClaimType);
			response = Response.status(200).build();
			slf4jLogger.info("Request to create claim completed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			slf4jLogger.info("Request to update claim failed");
			// Add exception handling code to return appropriate code 404 etc
			response = Response.status(500).build();
		}

		return response;

	}
}
