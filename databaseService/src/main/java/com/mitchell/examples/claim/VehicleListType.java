//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.11 at 12:03:05 PM PDT 
//

package com.mitchell.examples.claim;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for VehicleListType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="VehicleListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VehicleDetails" type="{http://www.mitchell.com/examples/claim}VehicleInfoType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VehicleListType", propOrder = { "vehicleDetails" })
@Entity
@Table(name = "VEHICLE_LIST_TYPE")
// @Inheritance(strategy = InheritanceType.JOINED)
public class VehicleListType {

	@XmlTransient
	private BigDecimal id;
	@XmlElement(name = "VehicleDetails", required = true)
	protected List<VehicleInfoType> vehicleDetails;

	@Id
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	@SequenceGenerator(name = "vehicleListTypeSeq", sequenceName = "VEHICLE_LIST_TYPE_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicleListTypeSeq")
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	/**
	 * Gets the value of the vehicleDetails property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the vehicleDetails property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getVehicleDetails().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link VehicleInfoType }
	 * 
	 * 
	 */

	@OneToMany(cascade = CascadeType.ALL, targetEntity = VehicleInfoType.class)
	@JoinColumn(name = "VEHICLE_DETAILS_VEHICLE_LIST_0")
	public List<VehicleInfoType> getVehicleDetails() {
		if (vehicleDetails == null) {
			vehicleDetails = new ArrayList<VehicleInfoType>();
		}
		return this.vehicleDetails;
	}

	public void setVehicleDetails(List<VehicleInfoType> vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}

}
