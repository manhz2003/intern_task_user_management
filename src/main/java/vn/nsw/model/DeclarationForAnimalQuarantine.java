//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.16 at 04:07:00 PM ICT 
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
 *         &lt;element name="DocumentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentYear" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserCreated" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NameOfShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FlagStateOfShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumberOfCrew" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumberOfPassengers" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastPortOfCallCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NextPortOfCallCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnimalNameFirst" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnimalNameBetween" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnimalNameThis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NameOfMaster" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SignPlace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SignDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MasterSigned" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "documentName",
    "documentYear",
    "userCreated",
    "nameOfShip",
    "flagStateOfShip",
    "numberOfCrew",
    "numberOfPassengers",
    "lastPortOfCallCode",
    "nextPortOfCallCode",
    "animalNameFirst",
    "animalNameBetween",
    "animalNameThis",
    "nameOfMaster",
    "signPlace",
    "signDate",
    "masterSigned"
})
@XmlRootElement(name = "DeclarationForAnimalQuarantine")
public class DeclarationForAnimalQuarantine {

    @XmlElement(name = "DocumentName", required = true)
    protected String documentName;
    @XmlElement(name = "DocumentYear", required = true)
    protected String documentYear;
    @XmlElement(name = "UserCreated", required = true)
    protected String userCreated;
    @XmlElement(name = "NameOfShip", required = true)
    protected String nameOfShip;
    @XmlElement(name = "FlagStateOfShip", required = true)
    protected String flagStateOfShip;
    @XmlElement(name = "NumberOfCrew", required = true)
    protected String numberOfCrew;
    @XmlElement(name = "NumberOfPassengers", required = true)
    protected String numberOfPassengers;
    @XmlElement(name = "LastPortOfCallCode", required = true)
    protected String lastPortOfCallCode;
    @XmlElement(name = "NextPortOfCallCode", required = true)
    protected String nextPortOfCallCode;
    @XmlElement(name = "AnimalNameFirst", required = true)
    protected String animalNameFirst;
    @XmlElement(name = "AnimalNameBetween", required = true)
    protected String animalNameBetween;
    @XmlElement(name = "AnimalNameThis", required = true)
    protected String animalNameThis;
    @XmlElement(name = "NameOfMaster", required = true)
    protected String nameOfMaster;
    @XmlElement(name = "SignPlace", required = true)
    protected String signPlace;
    @XmlElement(name = "SignDate", required = true)
    protected String signDate;
    @XmlElement(name = "MasterSigned", required = true)
    protected String masterSigned;

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
     * Gets the value of the userCreated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserCreated() {
        return userCreated;
    }

    /**
     * Sets the value of the userCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserCreated(String value) {
        this.userCreated = value;
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
     * Gets the value of the flagStateOfShip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagStateOfShip() {
        return flagStateOfShip;
    }

    /**
     * Sets the value of the flagStateOfShip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagStateOfShip(String value) {
        this.flagStateOfShip = value;
    }

    /**
     * Gets the value of the numberOfCrew property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfCrew() {
        return numberOfCrew;
    }

    /**
     * Sets the value of the numberOfCrew property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfCrew(String value) {
        this.numberOfCrew = value;
    }

    /**
     * Gets the value of the numberOfPassengers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /**
     * Sets the value of the numberOfPassengers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfPassengers(String value) {
        this.numberOfPassengers = value;
    }

    /**
     * Gets the value of the lastPortOfCallCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastPortOfCallCode() {
        return lastPortOfCallCode;
    }

    /**
     * Sets the value of the lastPortOfCallCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastPortOfCallCode(String value) {
        this.lastPortOfCallCode = value;
    }

    /**
     * Gets the value of the nextPortOfCallCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextPortOfCallCode() {
        return nextPortOfCallCode;
    }

    /**
     * Sets the value of the nextPortOfCallCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextPortOfCallCode(String value) {
        this.nextPortOfCallCode = value;
    }

    /**
     * Gets the value of the animalNameFirst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnimalNameFirst() {
        return animalNameFirst;
    }

    /**
     * Sets the value of the animalNameFirst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnimalNameFirst(String value) {
        this.animalNameFirst = value;
    }

    /**
     * Gets the value of the animalNameBetween property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnimalNameBetween() {
        return animalNameBetween;
    }

    /**
     * Sets the value of the animalNameBetween property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnimalNameBetween(String value) {
        this.animalNameBetween = value;
    }

    /**
     * Gets the value of the animalNameThis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnimalNameThis() {
        return animalNameThis;
    }

    /**
     * Sets the value of the animalNameThis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnimalNameThis(String value) {
        this.animalNameThis = value;
    }

    /**
     * Gets the value of the nameOfMaster property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOfMaster() {
        return nameOfMaster;
    }

    /**
     * Sets the value of the nameOfMaster property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOfMaster(String value) {
        this.nameOfMaster = value;
    }

    /**
     * Gets the value of the signPlace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignPlace() {
        return signPlace;
    }

    /**
     * Sets the value of the signPlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignPlace(String value) {
        this.signPlace = value;
    }

    /**
     * Gets the value of the signDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignDate() {
        return signDate;
    }

    /**
     * Sets the value of the signDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignDate(String value) {
        this.signDate = value;
    }

    /**
     * Gets the value of the masterSigned property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterSigned() {
        return masterSigned;
    }

    /**
     * Sets the value of the masterSigned property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterSigned(String value) {
        this.masterSigned = value;
    }

}
