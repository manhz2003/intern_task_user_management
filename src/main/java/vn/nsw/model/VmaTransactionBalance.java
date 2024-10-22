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
 *         &lt;element name="ShipAgencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipAgencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionLevel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SttlmtAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DepartmentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DepartmentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "shipAgencyCode",
    "shipAgencyName",
    "transactionLevel",
    "transactionTypeCode",
    "transactionTypeName",
    "currencyCode",
    "sttlmtAmount",
    "departmentCode",
    "departmentName",
    "modifiedDate"
})
@XmlRootElement(name = "VmaTransactionBalance")
public class VmaTransactionBalance {

    @XmlElement(name = "PortofAuthority", required = true)
    protected String portofAuthority;
    @XmlElement(name = "ShipAgencyCode", required = true)
    protected String shipAgencyCode;
    @XmlElement(name = "ShipAgencyName", required = true)
    protected String shipAgencyName;
    @XmlElement(name = "TransactionLevel", required = true)
    protected String transactionLevel;
    @XmlElement(name = "TransactionTypeCode", required = true)
    protected String transactionTypeCode;
    @XmlElement(name = "TransactionTypeName", required = true)
    protected String transactionTypeName;
    @XmlElement(name = "CurrencyCode", required = true)
    protected String currencyCode;
    @XmlElement(name = "SttlmtAmount", required = true)
    protected String sttlmtAmount;
    @XmlElement(name = "DepartmentCode", required = true)
    protected String departmentCode;
    @XmlElement(name = "DepartmentName", required = true)
    protected String departmentName;
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
     * Gets the value of the shipAgencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipAgencyName() {
        return shipAgencyName;
    }

    /**
     * Sets the value of the shipAgencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipAgencyName(String value) {
        this.shipAgencyName = value;
    }

    /**
     * Gets the value of the transactionLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionLevel() {
        return transactionLevel;
    }

    /**
     * Sets the value of the transactionLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionLevel(String value) {
        this.transactionLevel = value;
    }

    /**
     * Gets the value of the transactionTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTypeCode() {
        return transactionTypeCode;
    }

    /**
     * Sets the value of the transactionTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTypeCode(String value) {
        this.transactionTypeCode = value;
    }

    /**
     * Gets the value of the transactionTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    /**
     * Sets the value of the transactionTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTypeName(String value) {
        this.transactionTypeName = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the sttlmtAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSttlmtAmount() {
        return sttlmtAmount;
    }

    /**
     * Sets the value of the sttlmtAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSttlmtAmount(String value) {
        this.sttlmtAmount = value;
    }

    /**
     * Gets the value of the departmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * Sets the value of the departmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentCode(String value) {
        this.departmentCode = value;
    }

    /**
     * Gets the value of the departmentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Sets the value of the departmentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentName(String value) {
        this.departmentName = value;
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
