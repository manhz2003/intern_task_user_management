//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.03 at 10:24:36 AM ICT 
//


package vn.nsw.model;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="ShipTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMONumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CallSign" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FlagStateOfShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PortOfArrivalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PortHarbourCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PortRegionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PortWharfCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TimeOfArrival" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SignPlace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SignDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MasterSigned" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PersonList" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Age" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="NationalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="NationalName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="IlnessStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ReasonOfDead" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PersonType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "shipTypeCode",
    "imoNumber",
    "callSign",
    "flagStateOfShip",
    "portOfArrivalCode",
    "portHarbourCode",
    "portRegionCode",
    "portWharfCode",
    "timeOfArrival",
    "signPlace",
    "signDate",
    "masterSigned",
    "personList"
})
@XmlRootElement(name = "ConfirmationOfArrival")
public class ConfirmationOfArrival {

    @XmlElement(name = "DocumentName", required = true)
    protected String documentName;
    @XmlElement(name = "DocumentYear", required = true)
    protected String documentYear;
    @XmlElement(name = "UserCreated", required = true)
    protected String userCreated;
    @XmlElement(name = "NameOfShip", required = true)
    protected String nameOfShip;
    @XmlElement(name = "ShipTypeCode", required = true)
    protected String shipTypeCode;
    @XmlElement(name = "IMONumber", required = true)
    protected String imoNumber;
    @XmlElement(name = "CallSign", required = true)
    protected String callSign;
    @XmlElement(name = "FlagStateOfShip", required = true)
    protected String flagStateOfShip;
    @XmlElement(name = "PortOfArrivalCode", required = true)
    protected String portOfArrivalCode;
    @XmlElement(name = "PortHarbourCode", required = true)
    protected String portHarbourCode;
    @XmlElement(name = "PortRegionCode", required = true)
    protected String portRegionCode;
    @XmlElement(name = "PortWharfCode", required = true)
    protected String portWharfCode;
    @XmlElement(name = "TimeOfArrival", required = true)
    protected String timeOfArrival;
    @XmlElement(name = "SignPlace", required = true)
    protected String signPlace;
    @XmlElement(name = "SignDate", required = true)
    protected String signDate;
    @XmlElement(name = "MasterSigned", required = true)
    protected String masterSigned;
    @XmlElement(name = "PersonList", required = true)
    protected List<ConfirmationOfArrival.PersonList> personList;

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
     * Gets the value of the shipTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipTypeCode() {
        return shipTypeCode;
    }

    /**
     * Sets the value of the shipTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipTypeCode(String value) {
        this.shipTypeCode = value;
    }

    /**
     * Gets the value of the imoNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMONumber() {
        return imoNumber;
    }

    /**
     * Sets the value of the imoNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMONumber(String value) {
        this.imoNumber = value;
    }

    /**
     * Gets the value of the callSign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallSign() {
        return callSign;
    }

    /**
     * Sets the value of the callSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallSign(String value) {
        this.callSign = value;
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
     * Gets the value of the portOfArrivalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortOfArrivalCode() {
        return portOfArrivalCode;
    }

    /**
     * Sets the value of the portOfArrivalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortOfArrivalCode(String value) {
        this.portOfArrivalCode = value;
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
     * Gets the value of the timeOfArrival property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeOfArrival() {
        return timeOfArrival;
    }

    /**
     * Sets the value of the timeOfArrival property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeOfArrival(String value) {
        this.timeOfArrival = value;
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

    /**
     * Gets the value of the personList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConfirmationOfArrival.PersonList }
     * 
     * 
     */
    public List<ConfirmationOfArrival.PersonList> getPersonList() {
        if (personList == null) {
            personList = new ArrayList<ConfirmationOfArrival.PersonList>();
        }
        return this.personList;
    }


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
     *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Age" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="NationalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="NationalName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="IlnessStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ReasonOfDead" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PersonType" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "name",
        "age",
        "nationalCode",
        "nationalName",
        "ilnessStatus",
        "reasonOfDead",
        "personType"
    })
    public static class PersonList {

        @XmlElement(name = "Name", required = true)
        protected String name;
        @XmlElement(name = "Age", required = true)
        protected String age;
        @XmlElement(name = "NationalCode", required = true)
        protected String nationalCode;
        @XmlElement(name = "NationalName", required = true)
        protected String nationalName;
        @XmlElement(name = "IlnessStatus", required = true)
        protected String ilnessStatus;
        @XmlElement(name = "ReasonOfDead", required = true)
        protected String reasonOfDead;
        @XmlElement(name = "PersonType", required = true)
        protected String personType;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the age property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAge() {
            return age;
        }

        /**
         * Sets the value of the age property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAge(String value) {
            this.age = value;
        }

        /**
         * Gets the value of the nationalCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNationalCode() {
            return nationalCode;
        }

        /**
         * Sets the value of the nationalCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNationalCode(String value) {
            this.nationalCode = value;
        }

        /**
         * Gets the value of the nationalName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNationalName() {
            return nationalName;
        }

        /**
         * Sets the value of the nationalName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNationalName(String value) {
            this.nationalName = value;
        }

        /**
         * Gets the value of the ilnessStatus property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIlnessStatus() {
            return ilnessStatus;
        }

        /**
         * Sets the value of the ilnessStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIlnessStatus(String value) {
            this.ilnessStatus = value;
        }

        /**
         * Gets the value of the reasonOfDead property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReasonOfDead() {
            return reasonOfDead;
        }

        /**
         * Sets the value of the reasonOfDead property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReasonOfDead(String value) {
            this.reasonOfDead = value;
        }

        /**
         * Gets the value of the personType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPersonType() {
            return personType;
        }

        /**
         * Sets the value of the personType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPersonType(String value) {
            this.personType = value;
        }

    }

}