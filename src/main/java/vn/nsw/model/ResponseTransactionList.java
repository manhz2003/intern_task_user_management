//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.10.01 at 08:45:47 AM ICT 
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
 *         &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionList" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TransactionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CusName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipOwner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipLOA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Berth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Extra2" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "total",
    "transactionList"
})
@XmlRootElement(name = "ResponseTransactionList")
public class ResponseTransactionList {

    @XmlElement(name = "Total", required = true)
    protected String total;
    @XmlElement(name = "TransactionList", required = true)
    protected List<ResponseTransactionList.TransactionList> transactionList;

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotal(String value) {
        this.total = value;
    }

    /**
     * Gets the value of the transactionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResponseTransactionList.TransactionList }
     * 
     * 
     */
    public List<ResponseTransactionList.TransactionList> getTransactionList() {
        if (transactionList == null) {
            transactionList = new ArrayList<ResponseTransactionList.TransactionList>();
        }
        return this.transactionList;
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
     *         &lt;element name="TransactionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ShipName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ShipFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CusName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ShipOwner" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ShipLOA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Berth" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Extra2" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "transactionCode",
        "status",
        "shipName",
        "shipFlag",
        "cusName",
        "shipOwner",
        "shipLOA",
        "berth",
        "extra2"
    })
    public static class TransactionList {

        @XmlElement(name = "TransactionCode", required = true)
        protected String transactionCode;
        @XmlElement(name = "Status", required = true)
        protected String status;
        @XmlElement(name = "ShipName", required = true)
        protected String shipName;
        @XmlElement(name = "ShipFlag", required = true)
        protected String shipFlag;
        @XmlElement(name = "CusName", required = true)
        protected String cusName;
        @XmlElement(name = "ShipOwner", required = true)
        protected String shipOwner;
        @XmlElement(name = "ShipLOA", required = true)
        protected String shipLOA;
        @XmlElement(name = "Berth", required = true)
        protected String berth;
        @XmlElement(name = "Extra2", required = true)
        protected String extra2;

        /**
         * Gets the value of the transactionCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransactionCode() {
            return transactionCode;
        }

        /**
         * Sets the value of the transactionCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransactionCode(String value) {
            this.transactionCode = value;
        }

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatus(String value) {
            this.status = value;
        }

        /**
         * Gets the value of the shipName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipName() {
            return shipName;
        }

        /**
         * Sets the value of the shipName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipName(String value) {
            this.shipName = value;
        }

        /**
         * Gets the value of the shipFlag property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipFlag() {
            return shipFlag;
        }

        /**
         * Sets the value of the shipFlag property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipFlag(String value) {
            this.shipFlag = value;
        }

        /**
         * Gets the value of the cusName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCusName() {
            return cusName;
        }

        /**
         * Sets the value of the cusName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCusName(String value) {
            this.cusName = value;
        }

        /**
         * Gets the value of the shipOwner property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipOwner() {
            return shipOwner;
        }

        /**
         * Sets the value of the shipOwner property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipOwner(String value) {
            this.shipOwner = value;
        }

        /**
         * Gets the value of the shipLOA property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipLOA() {
            return shipLOA;
        }

        /**
         * Sets the value of the shipLOA property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipLOA(String value) {
            this.shipLOA = value;
        }

        /**
         * Gets the value of the berth property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBerth() {
            return berth;
        }

        /**
         * Sets the value of the berth property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBerth(String value) {
            this.berth = value;
        }

        /**
         * Gets the value of the extra2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExtra2() {
            return extra2;
        }

        /**
         * Sets the value of the extra2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExtra2(String value) {
            this.extra2 = value;
        }

    }

}
