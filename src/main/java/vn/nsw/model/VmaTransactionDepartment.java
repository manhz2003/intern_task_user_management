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
 *         &lt;element name="SequenceNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DepartmentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DepartmentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionTypeVND" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionTypeUSD" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "sequenceNo",
    "departmentCode",
    "departmentName",
    "transactionTypeVND",
    "transactionTypeUSD",
    "modifiedDate"
})
@XmlRootElement(name = "VmaTransactionDepartment")
public class VmaTransactionDepartment {

    @XmlElement(name = "PortofAuthority", required = true)
    protected String portofAuthority;
    @XmlElement(name = "SequenceNo", required = true)
    protected String sequenceNo;
    @XmlElement(name = "DepartmentCode", required = true)
    protected String departmentCode;
    @XmlElement(name = "DepartmentName", required = true)
    protected String departmentName;
    @XmlElement(name = "TransactionTypeVND", required = true)
    protected String transactionTypeVND;
    @XmlElement(name = "TransactionTypeUSD", required = true)
    protected String transactionTypeUSD;
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
     * Gets the value of the transactionTypeVND property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTypeVND() {
        return transactionTypeVND;
    }

    /**
     * Sets the value of the transactionTypeVND property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTypeVND(String value) {
        this.transactionTypeVND = value;
    }

    /**
     * Gets the value of the transactionTypeUSD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTypeUSD() {
        return transactionTypeUSD;
    }

    /**
     * Sets the value of the transactionTypeUSD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTypeUSD(String value) {
        this.transactionTypeUSD = value;
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