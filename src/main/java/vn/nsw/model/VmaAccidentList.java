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
 *         &lt;element name="PortofAuthority" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccidentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccidentTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccidentRegion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccidentBrief" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccidentConclusion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccidentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccidentCriticalType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DomesticShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InternationalShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DamageHumanLife" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumberOfDead" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumberOfMissed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumberOfInjured" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DamageToCargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RemarksOfCargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DamageToShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RemarksOfShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DamageToEnvironment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RemarksOfEnvironment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DamageToMarineActivity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RemarksOfMarineActivity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccidentOfficialNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccidentOfficialDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="f1AttachedReport" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="f2AttachedReport" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="f3AttachedReport" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="f4AttachedReport" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "portofAuthority",
    "accidentCode",
    "accidentTime",
    "accidentRegion",
    "accidentBrief",
    "accidentConclusion",
    "accidentType",
    "accidentCriticalType",
    "domesticShip",
    "internationalShip",
    "damageHumanLife",
    "numberOfDead",
    "numberOfMissed",
    "numberOfInjured",
    "damageToCargo",
    "remarksOfCargo",
    "damageToShip",
    "remarksOfShip",
    "damageToEnvironment",
    "remarksOfEnvironment",
    "damageToMarineActivity",
    "remarksOfMarineActivity",
    "accidentOfficialNo",
    "accidentOfficialDate",
    "f1AttachedReport",
    "f2AttachedReport",
    "f3AttachedReport",
    "f4AttachedReport",
    "modifiedDate"
})
@XmlRootElement(name = "VmaAccidentList")
public class VmaAccidentList {

    @XmlElement(name = "PortofAuthority", required = true)
    protected String portofAuthority;
    @XmlElement(name = "AccidentCode", required = true)
    protected String accidentCode;
    @XmlElement(name = "AccidentTime", required = true)
    protected String accidentTime;
    @XmlElement(name = "AccidentRegion", required = true)
    protected String accidentRegion;
    @XmlElement(name = "AccidentBrief", required = true)
    protected String accidentBrief;
    @XmlElement(name = "AccidentConclusion", required = true)
    protected String accidentConclusion;
    @XmlElement(name = "AccidentType", required = true)
    protected String accidentType;
    @XmlElement(name = "AccidentCriticalType", required = true)
    protected String accidentCriticalType;
    @XmlElement(name = "DomesticShip", required = true)
    protected String domesticShip;
    @XmlElement(name = "InternationalShip", required = true)
    protected String internationalShip;
    @XmlElement(name = "DamageHumanLife", required = true)
    protected String damageHumanLife;
    @XmlElement(name = "NumberOfDead", required = true)
    protected String numberOfDead;
    @XmlElement(name = "NumberOfMissed", required = true)
    protected String numberOfMissed;
    @XmlElement(name = "NumberOfInjured", required = true)
    protected String numberOfInjured;
    @XmlElement(name = "DamageToCargo", required = true)
    protected String damageToCargo;
    @XmlElement(name = "RemarksOfCargo", required = true)
    protected String remarksOfCargo;
    @XmlElement(name = "DamageToShip", required = true)
    protected String damageToShip;
    @XmlElement(name = "RemarksOfShip", required = true)
    protected String remarksOfShip;
    @XmlElement(name = "DamageToEnvironment", required = true)
    protected String damageToEnvironment;
    @XmlElement(name = "RemarksOfEnvironment", required = true)
    protected String remarksOfEnvironment;
    @XmlElement(name = "DamageToMarineActivity", required = true)
    protected String damageToMarineActivity;
    @XmlElement(name = "RemarksOfMarineActivity", required = true)
    protected String remarksOfMarineActivity;
    @XmlElement(name = "AccidentOfficialNo", required = true)
    protected String accidentOfficialNo;
    @XmlElement(name = "AccidentOfficialDate", required = true)
    protected String accidentOfficialDate;
    @XmlElement(required = true)
    protected String f1AttachedReport;
    @XmlElement(required = true)
    protected String f2AttachedReport;
    @XmlElement(required = true)
    protected String f3AttachedReport;
    @XmlElement(required = true)
    protected String f4AttachedReport;
    @XmlElement(name = "ModifiedDate", required = true)
    protected String modifiedDate;

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
     * Gets the value of the accidentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccidentCode() {
        return accidentCode;
    }

    /**
     * Sets the value of the accidentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccidentCode(String value) {
        this.accidentCode = value;
    }

    /**
     * Gets the value of the accidentTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccidentTime() {
        return accidentTime;
    }

    /**
     * Sets the value of the accidentTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccidentTime(String value) {
        this.accidentTime = value;
    }

    /**
     * Gets the value of the accidentRegion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccidentRegion() {
        return accidentRegion;
    }

    /**
     * Sets the value of the accidentRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccidentRegion(String value) {
        this.accidentRegion = value;
    }

    /**
     * Gets the value of the accidentBrief property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccidentBrief() {
        return accidentBrief;
    }

    /**
     * Sets the value of the accidentBrief property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccidentBrief(String value) {
        this.accidentBrief = value;
    }

    /**
     * Gets the value of the accidentConclusion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccidentConclusion() {
        return accidentConclusion;
    }

    /**
     * Sets the value of the accidentConclusion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccidentConclusion(String value) {
        this.accidentConclusion = value;
    }

    /**
     * Gets the value of the accidentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccidentType() {
        return accidentType;
    }

    /**
     * Sets the value of the accidentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccidentType(String value) {
        this.accidentType = value;
    }

    /**
     * Gets the value of the accidentCriticalType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccidentCriticalType() {
        return accidentCriticalType;
    }

    /**
     * Sets the value of the accidentCriticalType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccidentCriticalType(String value) {
        this.accidentCriticalType = value;
    }

    /**
     * Gets the value of the domesticShip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomesticShip() {
        return domesticShip;
    }

    /**
     * Sets the value of the domesticShip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomesticShip(String value) {
        this.domesticShip = value;
    }

    /**
     * Gets the value of the internationalShip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternationalShip() {
        return internationalShip;
    }

    /**
     * Sets the value of the internationalShip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternationalShip(String value) {
        this.internationalShip = value;
    }

    /**
     * Gets the value of the damageHumanLife property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDamageHumanLife() {
        return damageHumanLife;
    }

    /**
     * Sets the value of the damageHumanLife property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDamageHumanLife(String value) {
        this.damageHumanLife = value;
    }

    /**
     * Gets the value of the numberOfDead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfDead() {
        return numberOfDead;
    }

    /**
     * Sets the value of the numberOfDead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfDead(String value) {
        this.numberOfDead = value;
    }

    /**
     * Gets the value of the numberOfMissed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfMissed() {
        return numberOfMissed;
    }

    /**
     * Sets the value of the numberOfMissed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfMissed(String value) {
        this.numberOfMissed = value;
    }

    /**
     * Gets the value of the numberOfInjured property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfInjured() {
        return numberOfInjured;
    }

    /**
     * Sets the value of the numberOfInjured property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfInjured(String value) {
        this.numberOfInjured = value;
    }

    /**
     * Gets the value of the damageToCargo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDamageToCargo() {
        return damageToCargo;
    }

    /**
     * Sets the value of the damageToCargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDamageToCargo(String value) {
        this.damageToCargo = value;
    }

    /**
     * Gets the value of the remarksOfCargo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarksOfCargo() {
        return remarksOfCargo;
    }

    /**
     * Sets the value of the remarksOfCargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarksOfCargo(String value) {
        this.remarksOfCargo = value;
    }

    /**
     * Gets the value of the damageToShip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDamageToShip() {
        return damageToShip;
    }

    /**
     * Sets the value of the damageToShip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDamageToShip(String value) {
        this.damageToShip = value;
    }

    /**
     * Gets the value of the remarksOfShip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarksOfShip() {
        return remarksOfShip;
    }

    /**
     * Sets the value of the remarksOfShip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarksOfShip(String value) {
        this.remarksOfShip = value;
    }

    /**
     * Gets the value of the damageToEnvironment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDamageToEnvironment() {
        return damageToEnvironment;
    }

    /**
     * Sets the value of the damageToEnvironment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDamageToEnvironment(String value) {
        this.damageToEnvironment = value;
    }

    /**
     * Gets the value of the remarksOfEnvironment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarksOfEnvironment() {
        return remarksOfEnvironment;
    }

    /**
     * Sets the value of the remarksOfEnvironment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarksOfEnvironment(String value) {
        this.remarksOfEnvironment = value;
    }

    /**
     * Gets the value of the damageToMarineActivity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDamageToMarineActivity() {
        return damageToMarineActivity;
    }

    /**
     * Sets the value of the damageToMarineActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDamageToMarineActivity(String value) {
        this.damageToMarineActivity = value;
    }

    /**
     * Gets the value of the remarksOfMarineActivity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarksOfMarineActivity() {
        return remarksOfMarineActivity;
    }

    /**
     * Sets the value of the remarksOfMarineActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarksOfMarineActivity(String value) {
        this.remarksOfMarineActivity = value;
    }

    /**
     * Gets the value of the accidentOfficialNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccidentOfficialNo() {
        return accidentOfficialNo;
    }

    /**
     * Sets the value of the accidentOfficialNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccidentOfficialNo(String value) {
        this.accidentOfficialNo = value;
    }

    /**
     * Gets the value of the accidentOfficialDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccidentOfficialDate() {
        return accidentOfficialDate;
    }

    /**
     * Sets the value of the accidentOfficialDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccidentOfficialDate(String value) {
        this.accidentOfficialDate = value;
    }

    /**
     * Gets the value of the f1AttachedReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getF1AttachedReport() {
        return f1AttachedReport;
    }

    /**
     * Sets the value of the f1AttachedReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setF1AttachedReport(String value) {
        this.f1AttachedReport = value;
    }

    /**
     * Gets the value of the f2AttachedReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getF2AttachedReport() {
        return f2AttachedReport;
    }

    /**
     * Sets the value of the f2AttachedReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setF2AttachedReport(String value) {
        this.f2AttachedReport = value;
    }

    /**
     * Gets the value of the f3AttachedReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getF3AttachedReport() {
        return f3AttachedReport;
    }

    /**
     * Sets the value of the f3AttachedReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setF3AttachedReport(String value) {
        this.f3AttachedReport = value;
    }

    /**
     * Gets the value of the f4AttachedReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getF4AttachedReport() {
        return f4AttachedReport;
    }

    /**
     * Sets the value of the f4AttachedReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setF4AttachedReport(String value) {
        this.f4AttachedReport = value;
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
