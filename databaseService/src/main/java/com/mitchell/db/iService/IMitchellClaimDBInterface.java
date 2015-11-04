package com.mitchell.db.iService;

import java.util.Date;
import java.util.List;

import com.mitchell.examples.claim.MitchellClaimType;

public interface IMitchellClaimDBInterface {

	public void createClaim(MitchellClaimType mitchellClaimType)
			throws Exception;

	public MitchellClaimType findClaimByClaimId(String claimId)
			throws Exception;

	public void deleteClaimByClaimId(String claimId) throws Exception;

	public void updateClaim(MitchellClaimType mitchellClaimType)
			throws Exception;

	public List<MitchellClaimType> findClaimsByLossDate(Date startDate,
			Date endDate) throws Exception;
}
