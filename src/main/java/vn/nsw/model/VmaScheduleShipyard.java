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
 *         &lt;element name="PortofAuthority" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NameOfShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentYear" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NoticeShipType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipYardCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipYardCompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipYardOfficialNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RepairingFrom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RepairingTo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RepairingPlace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RepairingReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Repaired" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "portofAuthority",
    "nameOfShip",
    "documentName",
    "documentYear",
    "noticeShipType",
    "shipYardCode",
    "shipYardCompanyName",
    "shipYardOfficialNo",
    "repairingFrom",
    "repairingTo",
    "repairingPlace",
    "repairingReason",
    "repaired",
    "modifiedDate"
})
@XmlRootElement(name = "VmaScheduleShipyard")
public class VmaScheduleShipyard {

    @XmlElement(name = "ItineraryNo", required = true)
    protected String itineraryNo;
    @XmlElement(name = "PortofAuthority", required = true)
    protected String portofAuthority;
    @XmlElement(name = "NameOfShip", required = true)
    protected String nameOfShip;
    @XmlElement(name = "DocumentName", required = true)
    protected String documentName;
    @XmlElement(name = "DocumentYear", required = true)
    protected String documentYear;
    @XmlElement(name = "NoticeShipType", required = true)
    protected String noticeShipType;
    @XmlElement(name = "ShipYardCode", required = true)
    protected String shipYardCode;
    @XmlElement(name = "ShipYardCompanyName", required = true)
    protected String shipYardCompanyName;
    @XmlElement(name = "ShipYardOfficialNo", required = true)
    protected String shipYardOfficialNo;
    @XmlElement(name = "RepairingFrom", required = true)
    protected String repairingFrom;
    @XmlElement(name = "RepairingTo", required = true)
    protected String repairingTo;
    @XmlElement(name = "RepairingPlace", required = true)
    protected String repairingPlace;
    @XmlElement(name = "RepairingReason", required = true)
    protected String repairingReason;
    @XmlElement(name = "Repaired", required = true)
    protected String repaired;
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
     * Gets the value of the documentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * Sets the value of the documentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentName(String value) {
        this.documentName = value;
    }

    /**
     * Gets the value of the documentYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentYear() {
        return documentYear;
    }

    /**
     * Sets the value of the documentYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentYear(String value) {
        this.documentYear = value;
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
     * Gets the value of the shipYardCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipYardCode() {
        return shipYardCode;
    }

    /**
     * Sets the value of the shipYardCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipYardCode(String value) {
        this.shipYardCode = value;
    }

    /**
     * Gets the value of the shipYardCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipYardCompanyName() {
        return shipYardCompanyName;
    }

    /**
     * Sets the value of the shipYardCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipYardCompanyName(String value) {
        this.shipYardCompanyName = value;
    }

    /**
     * Gets the value of the shipYardOfficialNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipYardOfficialNo() {
        return shipYardOfficialNo;
    }

    /**
     * Sets the value of the shipYardOfficialNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipYardOfficialNo(String value) {
        this.shipYardOfficialNo = value;
    }

    /**
     * Gets the value of the repairingFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepairingFrom() {
        return repairingFrom;
    }

    /**
     * Sets the value of the repairingFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepairingFrom(String value) {
        this.repairingFrom = value;
    }

    /**
     * Gets the value of the repairingTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepairingTo() {
        return repairingTo;
    }

    /**
     * Sets the value of the repairingTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepairingTo(String value) {
        this.repairingTo = value;
    }

    /**
     * Gets the value of the repairingPlace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepairingPlace() {
        return repairingPlace;
    }

    /**
     * Sets the value of the repairingPlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepairingPlace(String value) {
        this.repairingPlace = value;
    }

    /**
     * Gets the value of the repairingReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepairingReason() {
        return repairingReason;
    }

    /**
     * Sets the value of the repairingReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepairingReason(String value) {
        this.repairingReason = value;
    }

    /**
     * Gets the value of the repaired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepaired() {
        return repaired;
    }

    /**
     * Sets the value of the repaired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepaired(String value) {
        this.repaired = value;
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