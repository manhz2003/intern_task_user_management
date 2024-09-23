//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.29 at 05:50:11 PM ICT 
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
 *         &lt;element name="Port" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LoCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FullNameVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortOrder" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AddressVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="StateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="IsDelete" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MarkedAsDelete" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ModifiedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RequestedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SyncVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "port"
})
@XmlRootElement(name = "PortList")
public class PortList {

    @XmlElement(name = "Port", required = true)
    protected List<PortList.Port> port;

    /**
     * Gets the value of the port property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the port property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortList.Port }
     * 
     * 
     */
    public List<PortList.Port> getPort() {
        if (port == null) {
            port = new ArrayList<PortList.Port>();
        }
        return this.port;
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
     *         &lt;element name="PortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LoCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FullNameVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortOrder" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AddressVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="StateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="IsDelete" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MarkedAsDelete" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ModifiedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RequestedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SyncVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "portCode",
        "loCode",
        "portName",
        "fullName",
        "fullNameVN",
        "portType",
        "portOrder",
        "address",
        "addressVN",
        "stateCode",
        "cityCode",
        "status",
        "isDelete",
        "markedAsDelete",
        "modifiedDate",
        "requestedDate",
        "syncVersion"
    })
    public static class Port {

        @XmlElement(name = "PortCode", required = true)
        protected String portCode;
        @XmlElement(name = "LoCode", required = true)
        protected String loCode;
        @XmlElement(name = "PortName", required = true)
        protected String portName;
        @XmlElement(name = "FullName", required = true)
        protected String fullName;
        @XmlElement(name = "FullNameVN", required = true)
        protected String fullNameVN;
        @XmlElement(name = "PortType", required = true)
        protected String portType;
        @XmlElement(name = "PortOrder", required = true)
        protected String portOrder;
        @XmlElement(name = "Address", required = true)
        protected String address;
        @XmlElement(name = "AddressVN", required = true)
        protected String addressVN;
        @XmlElement(name = "StateCode", required = true)
        protected String stateCode;
        @XmlElement(name = "CityCode", required = true)
        protected String cityCode;
        @XmlElement(name = "Status", required = true)
        protected String status;
        @XmlElement(name = "IsDelete", required = true)
        protected String isDelete;
        @XmlElement(name = "MarkedAsDelete", required = true)
        protected String markedAsDelete;
        @XmlElement(name = "ModifiedDate", required = true)
        protected String modifiedDate;
        @XmlElement(name = "RequestedDate", required = true)
        protected String requestedDate;
        @XmlElement(name = "SyncVersion", required = true)
        protected String syncVersion;

        /**
         * Gets the value of the portCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPortCode() {
            return portCode;
        }

        /**
         * Sets the value of the portCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPortCode(String value) {
            this.portCode = value;
        }

        /**
         * Gets the value of the loCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLoCode() {
            return loCode;
        }

        /**
         * Sets the value of the loCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLoCode(String value) {
            this.loCode = value;
        }

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
         * Gets the value of the fullNameVN property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFullNameVN() {
            return fullNameVN;
        }

        /**
         * Sets the value of the fullNameVN property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFullNameVN(String value) {
            this.fullNameVN = value;
        }

        /**
         * Gets the value of the portType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPortType() {
            return portType;
        }

        /**
         * Sets the value of the portType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPortType(String value) {
            this.portType = value;
        }

        /**
         * Gets the value of the portOrder property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPortOrder() {
            return portOrder;
        }

        /**
         * Sets the value of the portOrder property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPortOrder(String value) {
            this.portOrder = value;
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
         * Gets the value of the addressVN property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAddressVN() {
            return addressVN;
        }

        /**
         * Sets the value of the addressVN property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAddressVN(String value) {
            this.addressVN = value;
        }

        /**
         * Gets the value of the stateCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStateCode() {
            return stateCode;
        }

        /**
         * Sets the value of the stateCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStateCode(String value) {
            this.stateCode = value;
        }

        /**
         * Gets the value of the cityCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCityCode() {
            return cityCode;
        }

        /**
         * Sets the value of the cityCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCityCode(String value) {
            this.cityCode = value;
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
         * Gets the value of the isDelete property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIsDelete() {
            return isDelete;
        }

        /**
         * Sets the value of the isDelete property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIsDelete(String value) {
            this.isDelete = value;
        }

        /**
         * Gets the value of the markedAsDelete property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMarkedAsDelete() {
            return markedAsDelete;
        }

        /**
         * Sets the value of the markedAsDelete property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMarkedAsDelete(String value) {
            this.markedAsDelete = value;
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

        /**
         * Gets the value of the requestedDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRequestedDate() {
            return requestedDate;
        }

        /**
         * Sets the value of the requestedDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRequestedDate(String value) {
            this.requestedDate = value;
        }

        /**
         * Gets the value of the syncVersion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSyncVersion() {
            return syncVersion;
        }

        /**
         * Sets the value of the syncVersion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSyncVersion(String value) {
            this.syncVersion = value;
        }

    }

}