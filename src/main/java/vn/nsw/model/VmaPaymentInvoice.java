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
 *         &lt;element name="DocumentaryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ItineraryNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tugboatDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalhr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrencyExgDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="exchangeRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tugboatFee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="debitnoteid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipAgencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipAgencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PaymentStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "documentaryCode",
    "itineraryNo",
    "tugboatDescription",
    "unitPrice",
    "totalhr",
    "currency",
    "currencyExgDate",
    "exchangeRate",
    "tugboatFee",
    "fee",
    "tax",
    "paymentAmount",
    "debitnoteid",
    "shipAgencyCode",
    "shipAgencyName",
    "address",
    "paymentStatus",
    "departmentCode",
    "departmentName",
    "modifiedDate"
})
@XmlRootElement(name = "VmaPaymentInvoice")
public class VmaPaymentInvoice {

    @XmlElement(name = "DocumentaryCode", required = true)
    protected String documentaryCode;
    @XmlElement(name = "ItineraryNo", required = true)
    protected String itineraryNo;
    @XmlElement(required = true)
    protected String tugboatDescription;
    @XmlElement(required = true)
    protected String unitPrice;
    @XmlElement(required = true)
    protected String totalhr;
    @XmlElement(required = true)
    protected String currency;
    @XmlElement(name = "CurrencyExgDate", required = true)
    protected String currencyExgDate;
    @XmlElement(required = true)
    protected String exchangeRate;
    @XmlElement(required = true)
    protected String tugboatFee;
    @XmlElement(required = true)
    protected String fee;
    @XmlElement(required = true)
    protected String tax;
    @XmlElement(required = true)
    protected String paymentAmount;
    @XmlElement(required = true)
    protected String debitnoteid;
    @XmlElement(name = "ShipAgencyCode", required = true)
    protected String shipAgencyCode;
    @XmlElement(name = "ShipAgencyName", required = true)
    protected String shipAgencyName;
    @XmlElement(name = "Address", required = true)
    protected String address;
    @XmlElement(name = "PaymentStatus", required = true)
    protected String paymentStatus;
    @XmlElement(name = "DepartmentCode", required = true)
    protected String departmentCode;
    @XmlElement(name = "DepartmentName", required = true)
    protected String departmentName;
    @XmlElement(name = "ModifiedDate", required = true)
    protected String modifiedDate;

    /**
     * Gets the value of the documentaryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentaryCode() {
        return documentaryCode;
    }

    /**
     * Sets the value of the documentaryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentaryCode(String value) {
        this.documentaryCode = value;
    }

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
     * Gets the value of the tugboatDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTugboatDescription() {
        return tugboatDescription;
    }

    /**
     * Sets the value of the tugboatDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTugboatDescription(String value) {
        this.tugboatDescription = value;
    }

    /**
     * Gets the value of the unitPrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the value of the unitPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitPrice(String value) {
        this.unitPrice = value;
    }

    /**
     * Gets the value of the totalhr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalhr() {
        return totalhr;
    }

    /**
     * Sets the value of the totalhr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalhr(String value) {
        this.totalhr = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the currencyExgDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyExgDate() {
        return currencyExgDate;
    }

    /**
     * Sets the value of the currencyExgDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyExgDate(String value) {
        this.currencyExgDate = value;
    }

    /**
     * Gets the value of the exchangeRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeRate() {
        return exchangeRate;
    }

    /**
     * Sets the value of the exchangeRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeRate(String value) {
        this.exchangeRate = value;
    }

    /**
     * Gets the value of the tugboatFee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTugboatFee() {
        return tugboatFee;
    }

    /**
     * Sets the value of the tugboatFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTugboatFee(String value) {
        this.tugboatFee = value;
    }

    /**
     * Gets the value of the fee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFee() {
        return fee;
    }

    /**
     * Sets the value of the fee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFee(String value) {
        this.fee = value;
    }

    /**
     * Gets the value of the tax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTax() {
        return tax;
    }

    /**
     * Sets the value of the tax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTax(String value) {
        this.tax = value;
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
     * Gets the value of the debitnoteid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitnoteid() {
        return debitnoteid;
    }

    /**
     * Sets the value of the debitnoteid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitnoteid(String value) {
        this.debitnoteid = value;
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
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatus(String value) {
        this.paymentStatus = value;
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