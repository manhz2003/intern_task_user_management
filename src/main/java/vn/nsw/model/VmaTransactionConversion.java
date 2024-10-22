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
 *         &lt;element name="ShipTypeMT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FunctionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FunctionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ConversionRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ConversionUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "shipTypeMT",
    "shipTypeCode",
    "functionType",
    "functionName",
    "conversionRate",
    "conversionUnit",
    "modifiedDate"
})
@XmlRootElement(name = "VmaTransactionConversion")
public class VmaTransactionConversion {

    @XmlElement(name = "ShipTypeMT", required = true)
    protected String shipTypeMT;
    @XmlElement(name = "ShipTypeCode", required = true)
    protected String shipTypeCode;
    @XmlElement(name = "FunctionType", required = true)
    protected String functionType;
    @XmlElement(name = "FunctionName", required = true)
    protected String functionName;
    @XmlElement(name = "ConversionRate", required = true)
    protected String conversionRate;
    @XmlElement(name = "ConversionUnit", required = true)
    protected String conversionUnit;
    @XmlElement(name = "ModifiedDate", required = true)
    protected String modifiedDate;

    /**
     * Gets the value of the shipTypeMT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipTypeMT() {
        return shipTypeMT;
    }

    /**
     * Sets the value of the shipTypeMT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipTypeMT(String value) {
        this.shipTypeMT = value;
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
     * Gets the value of the functionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionType() {
        return functionType;
    }

    /**
     * Sets the value of the functionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionType(String value) {
        this.functionType = value;
    }

    /**
     * Gets the value of the functionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Sets the value of the functionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionName(String value) {
        this.functionName = value;
    }

    /**
     * Gets the value of the conversionRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConversionRate() {
        return conversionRate;
    }

    /**
     * Sets the value of the conversionRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConversionRate(String value) {
        this.conversionRate = value;
    }

    /**
     * Gets the value of the conversionUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConversionUnit() {
        return conversionUnit;
    }

    /**
     * Sets the value of the conversionUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConversionUnit(String value) {
        this.conversionUnit = value;
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
