//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.07 at 11:12:09 AM ICT 
//


package vn.nsw.model.inland;

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
 *         &lt;element name="PortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaritimePortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserCreated" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsArrival" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NameOfShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CallSign" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NameOfMaster" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CertificateNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TimeOfDeparture" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Crew" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CrewCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CrewNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "portName",
    "maritimePortCode",
    "userCreated",
    "isArrival",
    "nameOfShip",
    "callSign",
    "nameOfMaster",
    "certificateNumber",
    "timeOfDeparture",
    "crew"
})
@XmlRootElement(name = "InlandCrewCallCenter")
public class InlandCrewCallCenter {

    @XmlElement(name = "PortName", required = true)
    protected String portName;
    @XmlElement(name = "MaritimePortCode", required = true)
    protected String maritimePortCode;
    @XmlElement(name = "UserCreated", required = true)
    protected String userCreated;
    @XmlElement(name = "IsArrival")
    protected int isArrival;
    @XmlElement(name = "NameOfShip", required = true)
    protected String nameOfShip;
    @XmlElement(name = "CallSign", required = true)
    protected String callSign;
    @XmlElement(name = "NameOfMaster", required = true)
    protected String nameOfMaster;
    @XmlElement(name = "CertificateNumber", required = true)
    protected String certificateNumber;
    @XmlElement(name = "TimeOfDeparture", required = true)
    protected String timeOfDeparture;
    @XmlElement(name = "Crew", required = true)
    protected List<InlandCrewCallCenter.Crew> crew;

    /**
     * Gets the value of the portName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortName() {
        return portName;
    }

    /**
     * Sets the value of the portName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortName(String value) {
        this.portName = value;
    }

    /**
     * Gets the value of the maritimePortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaritimePortCode() {
        return maritimePortCode;
    }

    /**
     * Sets the value of the maritimePortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaritimePortCode(String value) {
        this.maritimePortCode = value;
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
     * Gets the value of the isArrival property.
     * 
     */
    public int getIsArrival() {
        return isArrival;
    }

    /**
     * Sets the value of the isArrival property.
     * 
     */
    public void setIsArrival(int value) {
        this.isArrival = value;
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
     * Gets the value of the certificateNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificateNumber() {
        return certificateNumber;
    }

    /**
     * Sets the value of the certificateNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateNumber(String value) {
        this.certificateNumber = value;
    }

    /**
     * Gets the value of the timeOfDeparture property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeOfDeparture() {
        return timeOfDeparture;
    }

    /**
     * Sets the value of the timeOfDeparture property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeOfDeparture(String value) {
        this.timeOfDeparture = value;
    }

    /**
     * Gets the value of the crew property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crew property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrew().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InlandCrewCallCenter.Crew }
     * 
     * 
     */
    public List<InlandCrewCallCenter.Crew> getCrew() {
        if (crew == null) {
            crew = new ArrayList<InlandCrewCallCenter.Crew>();
        }
        return this.crew;
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
     *         &lt;element name="CrewCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CrewNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "crewCode",
        "fullName",
        "crewNumber"
    })
    public static class Crew {

        @XmlElement(name = "CrewCode", required = true)
        protected String crewCode;
        @XmlElement(name = "FullName", required = true)
        protected String fullName;
        @XmlElement(name = "CrewNumber", required = true)
        protected String crewNumber;

        /**
         * Gets the value of the crewCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCrewCode() {
            return crewCode;
        }

        /**
         * Sets the value of the crewCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCrewCode(String value) {
            this.crewCode = value;
        }

        /**
         * Gets the value of the fullName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFullName() {
            return fullName;
        }

        /**
         * Sets the value of the fullName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFullName(String value) {
            this.fullName = value;
        }

        /**
         * Gets the value of the crewNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCrewNumber() {
            return crewNumber;
        }

        /**
         * Sets the value of the crewNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCrewNumber(String value) {
            this.crewNumber = value;
        }

    }

}
