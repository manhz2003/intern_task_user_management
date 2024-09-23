//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.08.20 at 03:04:12 PM ICT 
//


package vn.nsw.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItineraryNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SequenceNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PortofAuthority" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NameOfShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PurposeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PurposeSpecified" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnchoringDateFrom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnchoringDateTo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnchoringDuration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnchoringPortRegionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnchoringPortHarbourCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnchoringPortWharfCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NoticeShipType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PortRegionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PortHarbourCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PortWharfCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipOwnerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipOperatorCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipAgencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MakePayment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ModifiedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "itineraryNo",
    "sequenceNo",
    "portofAuthority",
    "nameOfShip",
    "purposeCode",
    "purposeSpecified",
    "anchoringDateFrom",
    "anchoringDateTo",
    "anchoringDuration",
    "anchoringPortRegionCode",
    "anchoringPortHarbourCode",
    "anchoringPortWharfCode",
    "noticeShipType",
    "portRegionCode",
    "portHarbourCode",
    "portWharfCode",
    "shipOwnerCode",
    "shipOperatorCode",
    "shipAgencyCode",
    "makePayment",
    "modifiedDate"
})
@XmlRootElement(name = "VmaScheduleAnchorage")
public class VmaScheduleAnchorage {

    @XmlElement(name = "ItineraryNo", required = true)
    protected String itineraryNo;
    @XmlElement(name = "SequenceNo", required = true)
    protected String sequenceNo;
    @XmlElement(name = "PortofAuthority", required = true)
    protected String portofAuthority;
    @XmlElement(name = "NameOfShip", required = true)
    protected String nameOfShip;
    @XmlElement(name = "PurposeCode", required = true)
    protected String purposeCode;
    @XmlElement(name = "PurposeSpecified", required = true)
    protected String purposeSpecified;
    @XmlElement(name = "AnchoringDateFrom", required = true)
    protected String anchoringDateFrom;
    @XmlElement(name = "AnchoringDateTo", required = true)
    protected String anchoringDateTo;
    @XmlElement(name = "AnchoringDuration", required = true)
    protected String anchoringDuration;
    @XmlElement(name = "AnchoringPortRegionCode", required = true)
    protected String anchoringPortRegionCode;
    @XmlElement(name = "AnchoringPortHarbourCode", required = true)
    protected String anchoringPortHarbourCode;
    @XmlElement(name = "AnchoringPortWharfCode", required = true)
    protected String anchoringPortWharfCode;
    @XmlElement(name = "NoticeShipType", required = true)
    protected String noticeShipType;
    @XmlElement(name = "PortRegionCode", required = true)
    protected String portRegionCode;
    @XmlElement(name = "PortHarbourCode", required = true)
    protected String portHarbourCode;
    @XmlElement(name = "PortWharfCode", required = true)
    protected String portWharfCode;
    @XmlElement(name = "ShipOwnerCode", required = true)
    protected String shipOwnerCode;
    @XmlElement(name = "ShipOperatorCode", required = true)
    protected String shipOperatorCode;
    @XmlElement(name = "ShipAgencyCode", required = true)
    protected String shipAgencyCode;
    @XmlElement(name = "MakePayment", required = true)
    protected String makePayment;
    @XmlElement(name = "ModifiedDate", required = true)
    protected String modifiedDate;

    /**
     * Gets the value of the itineraryNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItineraryNo() {
        return itineraryNo;
    }

    /**
     * Sets the value of the itineraryNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItineraryNo(String value) {
        this.itineraryNo = value;
    }

    /**
     * Gets the value of the sequenceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequenceNo() {
        return sequenceNo;
    }

    /**
     * Sets the value of the sequenceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequenceNo(String value) {
        this.sequenceNo = value;
    }

    /**
     * Gets the value of the portofAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortofAuthority() {
        return portofAuthority;
    }

    /**
     * Sets the value of the portofAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortofAuthority(String value) {
        this.portofAuthority = value;
    }

    /**
     * Gets the value of the nameOfShip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOfShip() {
        return nameOfShip;
    }

    /**
     * Sets the value of the nameOfShip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOfShip(String value) {
        this.nameOfShip = value;
    }

    /**
     * Gets the value of the purposeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurposeCode() {
        return purposeCode;
    }

    /**
     * Sets the value of the purposeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurposeCode(String value) {
        this.purposeCode = value;
    }

    /**
     * Gets the value of the purposeSpecified property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurposeSpecified() {
        return purposeSpecified;
    }

    /**
     * Sets the value of the purposeSpecified property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurposeSpecified(String value) {
        this.purposeSpecified = value;
    }

    /**
     * Gets the value of the anchoringDateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchoringDateFrom() {
        return anchoringDateFrom;
    }

    /**
     * Sets the value of the anchoringDateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchoringDateFrom(String value) {
        this.anchoringDateFrom = value;
    }

    /**
     * Gets the value of the anchoringDateTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchoringDateTo() {
        return anchoringDateTo;
    }

    /**
     * Sets the value of the anchoringDateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchoringDateTo(String value) {
        this.anchoringDateTo = value;
    }

    /**
     * Gets the value of the anchoringDuration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchoringDuration() {
        return anchoringDuration;
    }

    /**
     * Sets the value of the anchoringDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchoringDuration(String value) {
        this.anchoringDuration = value;
    }

    /**
     * Gets the value of the anchoringPortRegionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchoringPortRegionCode() {
        return anchoringPortRegionCode;
    }

    /**
     * Sets the value of the anchoringPortRegionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchoringPortRegionCode(String value) {
        this.anchoringPortRegionCode = value;
    }

    /**
     * Gets the value of the anchoringPortHarbourCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchoringPortHarbourCode() {
        return anchoringPortHarbourCode;
    }

    /**
     * Sets the value of the anchoringPortHarbourCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchoringPortHarbourCode(String value) {
        this.anchoringPortHarbourCode = value;
    }

    /**
     * Gets the value of the anchoringPortWharfCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchoringPortWharfCode() {
        return anchoringPortWharfCode;
    }

    /**
     * Sets the value of the anchoringPortWharfCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchoringPortWharfCode(String value) {
        this.anchoringPortWharfCode = value;
    }

    /**
     * Gets the value of the noticeShipType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoticeShipType() {
        return noticeShipType;
    }

    /**
     * Sets the value of the noticeShipType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoticeShipType(String value) {
        this.noticeShipType = value;
    }

    /**
     * Gets the value of the portRegionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortRegionCode() {
        return portRegionCode;
    }

    /**
     * Sets the value of the portRegionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortRegionCode(String value) {
        this.portRegionCode = value;
    }

    /**
     * Gets the value of the portHarbourCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortHarbourCode() {
        return portHarbourCode;
    }

    /**
     * Sets the value of the portHarbourCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortHarbourCode(String value) {
        this.portHarbourCode = value;
    }

    /**
     * Gets the value of the portWharfCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortWharfCode() {
        return portWharfCode;
    }

    /**
     * Sets the value of the portWharfCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortWharfCode(String value) {
        this.portWharfCode = value;
    }

    /**
     * Gets the value of the shipOwnerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipOwnerCode() {
        return shipOwnerCode;
    }

    /**
     * Sets the value of the shipOwnerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipOwnerCode(String value) {
        this.shipOwnerCode = value;
    }

    /**
     * Gets the value of the shipOperatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipOperatorCode() {
        return shipOperatorCode;
    }

    /**
     * Sets the value of the shipOperatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipOperatorCode(String value) {
        this.shipOperatorCode = value;
    }

    /**
     * Gets the value of the shipAgencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipAgencyCode() {
        return shipAgencyCode;
    }

    /**
     * Sets the value of the shipAgencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipAgencyCode(String value) {
        this.shipAgencyCode = value;
    }

    /**
     * Gets the value of the makePayment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMakePayment() {
        return makePayment;
    }

    /**
     * Sets the value of the makePayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMakePayment(String value) {
        this.makePayment = value;
    }

    /**
     * Gets the value of the modifiedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the value of the modifiedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifiedDate(String value) {
        this.modifiedDate = value;
    }

}