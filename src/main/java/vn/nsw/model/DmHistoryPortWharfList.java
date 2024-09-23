//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.29 at 03:56:33 PM ICT 
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
 *         &lt;element name="DmHistoryPortWharf" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PortWharfCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortWharfName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortWharfNameVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortWharfType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortRegionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortHarbourCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Note" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "dmHistoryPortWharf"
})
@XmlRootElement(name = "DmHistoryPortWharfList")
public class DmHistoryPortWharfList {

    @XmlElement(name = "DmHistoryPortWharf", required = true)
    protected List<DmHistoryPortWharfList.DmHistoryPortWharf> dmHistoryPortWharf;

    /**
     * Gets the value of the dmHistoryPortWharf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dmHistoryPortWharf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDmHistoryPortWharf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DmHistoryPortWharfList.DmHistoryPortWharf }
     * 
     * 
     */
    public List<DmHistoryPortWharfList.DmHistoryPortWharf> getDmHistoryPortWharf() {
        if (dmHistoryPortWharf == null) {
            dmHistoryPortWharf = new ArrayList<DmHistoryPortWharfList.DmHistoryPortWharf>();
        }
        return this.dmHistoryPortWharf;
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
     *         &lt;element name="PortWharfCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortWharfName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortWharfNameVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortWharfType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortRegionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortHarbourCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Note" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "portWharfCode",
        "portWharfName",
        "portWharfNameVN",
        "portWharfType",
        "portCode",
        "portRegionCode",
        "portHarbourCode",
        "note",
        "isDelete",
        "markedAsDelete",
        "modifiedDate",
        "requestedDate",
        "syncVersion"
    })
    public static class DmHistoryPortWharf {

        @XmlElement(name = "PortWharfCode", required = true)
        protected String portWharfCode;
        @XmlElement(name = "PortWharfName", required = true)
        protected String portWharfName;
        @XmlElement(name = "PortWharfNameVN", required = true)
        protected String portWharfNameVN;
        @XmlElement(name = "PortWharfType", required = true)
        protected String portWharfType;
        @XmlElement(name = "PortCode", required = true)
        protected String portCode;
        @XmlElement(name = "PortRegionCode", required = true)
        protected String portRegionCode;
        @XmlElement(name = "PortHarbourCode", required = true)
        protected String portHarbourCode;
        @XmlElement(name = "Note", required = true)
        protected String note;
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
         * Gets the value of the portWharfName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPortWharfName() {
            return portWharfName;
        }

        /**
         * Sets the value of the portWharfName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPortWharfName(String value) {
            this.portWharfName = value;
        }

        /**
         * Gets the value of the portWharfNameVN property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPortWharfNameVN() {
            return portWharfNameVN;
        }

        /**
         * Sets the value of the portWharfNameVN property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPortWharfNameVN(String value) {
            this.portWharfNameVN = value;
        }

        /**
         * Gets the value of the portWharfType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPortWharfType() {
            return portWharfType;
        }

        /**
         * Sets the value of the portWharfType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPortWharfType(String value) {
            this.portWharfType = value;
        }

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
         * Gets the value of the note property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNote() {
            return note;
        }

        /**
         * Sets the value of the note property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNote(String value) {
            this.note = value;
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