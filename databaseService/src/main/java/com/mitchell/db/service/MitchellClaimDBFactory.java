package com.mitchell.db.service;

import com.mitchell.db.iService.IMitchellClaimDBInterface;

public class MitchellClaimDBFactory {

	public static IMitchellClaimDBInterface getIMitchellClaimDBInterface(
			String implementationType) {
		{
			if (null == implementationType) {
				return null;
			} else if (implementationType.equals("JPA")) {
				return new MitchellClaimDBJPAImpl();
			} else {
				return null;
			}
		}
	}
}
