//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.11 at 12:03:05 PM PDT 
//

package com.mitchell.examples.claim;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for MitchellClaimType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MitchellClaimType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClaimNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ClaimantFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClaimantLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.mitchell.com/examples/claim}StatusCode" minOccurs="0"/>
 *         &lt;element name="LossDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="LossInfo" type="{http://www.mitchell.com/examples/claim}LossInfoType" minOccurs="0"/>
 *         &lt;element name="AssignedAdjusterID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Vehicles" type="{http://www.mitchell.com/examples/claim}VehicleListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MitchellClaimType", propOrder = { "claimNumber",
		"claimantFirstName", "claimantLastName", "status", "lossDate",
		"lossInfo", "assignedAdjusterID", "vehicles" })
@Entity
@Table(name = "MITCHELL_CLAIM_TYPE")
@XmlRootElement(name = "MitchellClaim")
public class MitchellClaimType {

	@XmlTransient
	private BigDecimal id;
	@XmlElement(name = "ClaimNumber", required = true)
	protected String claimNumber;
	@XmlElement(name = "ClaimantFirstName")
	protected String claimantFirstName;
	@XmlElement(name = "ClaimantLastName")
	protected String claimantLastName;
	@XmlElement(name = "Status")
	@XmlSchemaType(name = "string")
	protected StatusCode status;
	@XmlElement(name = "LossDate")
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	protected Date lossDate;
	@XmlElement(name = "LossInfo")
	protected LossInfoType lossInfo;
	@XmlElement(name = "AssignedAdjusterID")
	protected Long assignedAdjusterID;
	@XmlElement(name = "Vehicles")
	protected VehicleListType vehicles;

	@Id
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	@SequenceGenerator(name = "mitchellClaimTypeSeq", sequenceName = "MITCHELL_CLAIM_TYPE_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mitchellClaimTypeSeq")
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	/**
	 * Gets the value of the claimNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	@Column(name = "CLAIM_NUMBER", length = 1020, unique = true, nullable = false)
	public String getClaimNumber() {
		return claimNumber;
	}

	/**
	 * Sets the value of the claimNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClaimNumber(String value) {
		this.claimNumber = value;
	}

	/**
	 * Gets the value of the claimantFirstName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	@Column(name = "CLAIMANT_FIRST_NAME", length = 1020)
	public String getClaimantFirstName() {
		return claimantFirstName;
	}

	/**
	 * Sets the value of the claimantFirstName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClaimantFirstName(String value) {
		this.claimantFirstName = value;
	}

	/**
	 * Gets the value of the claimantLastName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	@Column(name = "CLAIMANT_LAST_NAME", length = 1020)
	public String getClaimantLastName() {
		return claimantLastName;
	}

	/**
	 * Sets the value of the claimantLastName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClaimantLastName(String value) {
		this.claimantLastName = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link StatusCode }
	 * 
	 */
	@Basic
	@Column(name = "STATUS", length = 1020)
	@Enumerated(EnumType.STRING)
	public StatusCode getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link StatusCode }
	 * 
	 */
	public void setStatus(StatusCode value) {
		this.status = value;
	}

	/**
	 * Gets the value of the lossDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	@Basic
	@Column(name = "LOSS_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLossDate() {
		return lossDate;
	}

	/**
	 * Sets the value of the lossDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setLossDate(Date value) {
		this.lossDate = value;
	}

	/**
	 * Gets the value of the lossInfo property.
	 * 
	 * @return possible object is {@link LossInfoType }
	 * 
	 */
	// @ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "LOSS_INFO_MITCHELL_CLAIM_TYP_0")
	public LossInfoType getLossInfo() {
		return lossInfo;
	}

	/**
	 * Sets the value of the lossInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link LossInfoType }
	 * 
	 */
	public void setLossInfo(LossInfoType value) {
		this.lossInfo = value;
	}

	/**
	 * Gets the value of the assignedAdjusterID property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	@Column(name = "ASSIGNED_ADJUSTER_ID", scale = 0)
	public Long getAssignedAdjusterID() {
		return assignedAdjusterID;
	}

	/**
	 * Sets the value of the assignedAdjusterID property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setAssignedAdjusterID(Long value) {
		this.assignedAdjusterID = value;
	}

	/**
	 * Gets the value of the vehicles property.
	 * 
	 * @return possible object is {@link VehicleListType }
	 * 
	 */

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "VEHICLES_MITCHELL_CLAIM_TYPE_0")
	public VehicleListType getVehicles() {
		return vehicles;
	}

	/**
	 * Sets the value of the vehicles property.
	 * 
	 * @param value
	 *            allowed object is {@link VehicleListType }
	 * 
	 */
	public void setVehicles(VehicleListType value) {
		this.vehicles = value;
	}

}