package com.mitchell.examples.claim;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "claims")
public class Claims {

	@XmlElement(name = "MitchellClaim", type = MitchellClaimType.class)
	private List<MitchellClaimType> claims = new ArrayList();

	public Claims() {
	}

	public Claims(List<MitchellClaimType> books) {
		this.claims = books;
	}

	public List<MitchellClaimType> getClaims() {
		return claims;
	}

	public void setClaims(List<MitchellClaimType> claims) {
		this.claims = claims;
	}
}
