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
 *         &lt;element name="ViolationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ViolationBrief" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DamagedPartName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Conclusion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MetaData" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PaymentAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AmountInWords" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentaryNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentaryDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DecisionNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DecisionDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DecisionOrganization" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OfficialNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OfficialDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OfficialPlace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ViolationPartCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IssueDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IssueBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ViolationPartName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ViolationPartAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Representative" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RepresentativeTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="f1AttachedReport" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="f2AttachedReport" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "violationCode",
    "violationBrief",
    "damagedPartName",
    "conclusion",
    "metaData",
    "paymentAmount",
    "amountInWords",
    "documentaryNo",
    "documentaryDate",
    "decisionNo",
    "decisionDate",
    "decisionOrganization",
    "officialNo",
    "officialDate",
    "officialPlace",
    "violationPartCode",
    "issueDate",
    "issueBy",
    "violationPartName",
    "violationPartAddress",
    "representative",
    "representativeTitle",
    "f1AttachedReport",
    "f2AttachedReport",
    "modifiedDate"
})
@XmlRootElement(name = "VmaAdministrativeViolation")
public class VmaAdministrativeViolation {

    @XmlElement(name = "PortofAuthority", required = true)
    protected String portofAuthority;
    @XmlElement(name = "ViolationCode", required = true)
    protected String violationCode;
    @XmlElement(name = "ViolationBrief", required = true)
    protected String violationBrief;
    @XmlElement(name = "DamagedPartName", required = true)
    protected String damagedPartName;
    @XmlElement(name = "Conclusion", required = true)
    protected String conclusion;
    @XmlElement(name = "MetaData", required = true)
    protected String metaData;
    @XmlElement(name = "PaymentAmount", required = true)
    protected String paymentAmount;
    @XmlElement(name = "AmountInWords", required = true)
    protected String amountInWords;
    @XmlElement(name = "DocumentaryNo", required = true)
    protected String documentaryNo;
    @XmlElement(name = "DocumentaryDate", required = true)
    protected String documentaryDate;
    @XmlElement(name = "DecisionNo", required = true)
    protected String decisionNo;
    @XmlElement(name = "DecisionDate", required = true)
    protected String decisionDate;
    @XmlElement(name = "DecisionOrganization", required = true)
    protected String decisionOrganization;
    @XmlElement(name = "OfficialNo", required = true)
    protected String officialNo;
    @XmlElement(name = "OfficialDate", required = true)
    protected String officialDate;
    @XmlElement(name = "OfficialPlace", required = true)
    protected String officialPlace;
    @XmlElement(name = "ViolationPartCode", required = true)
    protected String violationPartCode;
    @XmlElement(name = "IssueDate", required = true)
    protected String issueDate;
    @XmlElement(name = "IssueBy", required = true)
    protected String issueBy;
    @XmlElement(name = "ViolationPartName", required = true)
    protected String violationPartName;
    @XmlElement(name = "ViolationPartAddress", required = true)
    protected String violationPartAddress;
    @XmlElement(name = "Representative", required = true)
    protected String representative;
    @XmlElement(name = "RepresentativeTitle", required = true)
    protected String representativeTitle;
    @XmlElement(required = true)
    protected String f1AttachedReport;
    @XmlElement(required = true)
    protected String f2AttachedReport;
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
     * Gets the value of the violationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViolationCode() {
        return violationCode;
    }

    /**
     * Sets the value of the violationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViolationCode(String value) {
        this.violationCode = value;
    }

    /**
     * Gets the value of the violationBrief property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViolationBrief() {
        return violationBrief;
    }

    /**
     * Sets the value of the violationBrief property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViolationBrief(String value) {
        this.violationBrief = value;
    }

    /**
     * Gets the value of the damagedPartName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDamagedPartName() {
        return damagedPartName;
    }

    /**
     * Sets the value of the damagedPartName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDamagedPartName(String value) {
        this.damagedPartName = value;
    }

    /**
     * Gets the value of the conclusion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConclusion() {
        return conclusion;
    }

    /**
     * Sets the value of the conclusion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConclusion(String value) {
        this.conclusion = value;
    }

    /**
     * Gets the value of the metaData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetaData() {
        return metaData;
    }

    /**
     * Sets the value of the metaData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetaData(String value) {
        this.metaData = value;
    }

    /**
     * Gets the value of the paymentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the value of the paymentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentAmount(String value) {
        this.paymentAmount = value;
    }

    /**
     * Gets the value of the amountInWords property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmountInWords() {
        return amountInWords;
    }

    /**
     * Sets the value of the amountInWords property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmountInWords(String value) {
        this.amountInWords = value;
    }

    /**
     * Gets the value of the documentaryNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentaryNo() {
        return documentaryNo;
    }

    /**
     * Sets the value of the documentaryNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentaryNo(String value) {
        this.documentaryNo = value;
    }

    /**
     * Gets the value of the documentaryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentaryDate() {
        return documentaryDate;
    }

    /**
     * Sets the value of the documentaryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentaryDate(String value) {
        this.documentaryDate = value;
    }

    /**
     * Gets the value of the decisionNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecisionNo() {
        return decisionNo;
    }

    /**
     * Sets the value of the decisionNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecisionNo(String value) {
        this.decisionNo = value;
    }

    /**
     * Gets the value of the decisionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecisionDate() {
        return decisionDate;
    }

    /**
     * Sets the value of the decisionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecisionDate(String value) {
        this.decisionDate = value;
    }

    /**
     * Gets the value of the decisionOrganization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecisionOrganization() {
        return decisionOrganization;
    }

    /**
     * Sets the value of the decisionOrganization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecisionOrganization(String value) {
        this.decisionOrganization = value;
    }

    /**
     * Gets the value of the officialNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficialNo() {
        return officialNo;
    }

    /**
     * Sets the value of the officialNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficialNo(String value) {
        this.officialNo = value;
    }

    /**
     * Gets the value of the officialDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficialDate() {
        return officialDate;
    }

    /**
     * Sets the value of the officialDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficialDate(String value) {
        this.officialDate = value;
    }

    /**
     * Gets the value of the officialPlace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficialPlace() {
        return officialPlace;
    }

    /**
     * Sets the value of the officialPlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficialPlace(String value) {
        this.officialPlace = value;
    }

    /**
     * Gets the value of the violationPartCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViolationPartCode() {
        return violationPartCode;
    }

    /**
     * Sets the value of the violationPartCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViolationPartCode(String value) {
        this.violationPartCode = value;
    }

    /**
     * Gets the value of the issueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueDate(String value) {
        this.issueDate = value;
    }

    /**
     * Gets the value of the issueBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueBy() {
        return issueBy;
    }

    /**
     * Sets the value of the issueBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueBy(String value) {
        this.issueBy = value;
    }

    /**
     * Gets the value of the violationPartName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViolationPartName() {
        return violationPartName;
    }

    /**
     * Sets the value of the violationPartName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViolationPartName(String value) {
        this.violationPartName = value;
    }

    /**
     * Gets the value of the violationPartAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViolationPartAddress() {
        return violationPartAddress;
    }

    /**
     * Sets the value of the violationPartAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViolationPartAddress(String value) {
        this.violationPartAddress = value;
    }

    /**
     * Gets the value of the representative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepresentative() {
        return representative;
    }

    /**
     * Sets the value of the representative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepresentative(String value) {
        this.representative = value;
    }

    /**
     * Gets the value of the representativeTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepresentativeTitle() {
        return representativeTitle;
    }

    /**
     * Sets the value of the representativeTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepresentativeTitle(String value) {
        this.representativeTitle = value;
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