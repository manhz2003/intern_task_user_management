//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.19 at 02:58:59 PM ICT 
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
 *         &lt;element name="DmHistoryVmashipType" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ShipTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ApplyShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ApplyBoat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipTypeRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Remarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "dmHistoryVmashipType"
})
@XmlRootElement(name = "DmHistoryVmashipTypeList")
public class DmHistoryVmashipTypeList {

    @XmlElement(name = "DmHistoryVmashipType", required = true)
    protected List<DmHistoryVmashipTypeList.DmHistoryVmashipType> dmHistoryVmashipType;

    /**
     * Gets the value of the dmHistoryVmashipType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dmHistoryVmashipType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDmHistoryVmashipType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DmHistoryVmashipTypeList.DmHistoryVmashipType }
     * 
     * 
     */
    public List<DmHistoryVmashipTypeList.DmHistoryVmashipType> getDmHistoryVmashipType() {
        if (dmHistoryVmashipType == null) {
            dmHistoryVmashipType = new ArrayList<DmHistoryVmashipTypeList.DmHistoryVmashipType>();
        }
        return this.dmHistoryVmashipType;
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
     *         &lt;element name="ShipTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ShipTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ApplyShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ApplyBoat" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ShipTypeRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Remarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        "shipTypeCode",
        "shipTypeName",
        "applyShip",
        "applyBoat",
        "shipTypeRef",
        "remarks",
        "status",
        "syncVersion"
    })
    public static class DmHistoryVmashipType {

        @XmlElement(name = "ShipTypeCode", required = true)
        protected String shipTypeCode;
        @XmlElement(name = "ShipTypeName", required = true)
        protected String shipTypeName;
        @XmlElement(name = "ApplyShip", required = true)
        protected String applyShip;
        @XmlElement(name = "ApplyBoat", required = true)
        protected String applyBoat;
        @XmlElement(name = "ShipTypeRef", required = true)
        protected String shipTypeRef;
        @XmlElement(name = "Remarks", required = true)
        protected String remarks;
        @XmlElement(name = "Status")
        protected int status;
        @XmlElement(name = "SyncVersion", required = true)
        protected String syncVersion;

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
         * Gets the value of the shipTypeName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipTypeName() {
            return shipTypeName;
        }

        /**
         * Sets the value of the shipTypeName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipTypeName(String value) {
            this.shipTypeName = value;
        }

        /**
         * Gets the value of the applyShip property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApplyShip() {
            return applyShip;
        }

        /**
         * Sets the value of the applyShip property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApplyShip(String value) {
            this.applyShip = value;
        }

        /**
         * Gets the value of the applyBoat property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApplyBoat() {
            return applyBoat;
        }

        /**
         * Sets the value of the applyBoat property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApplyBoat(String value) {
            this.applyBoat = value;
        }

        /**
         * Gets the value of the shipTypeRef property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipTypeRef() {
            return shipTypeRef;
        }

        /**
         * Sets the value of the shipTypeRef property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipTypeRef(String value) {
            this.shipTypeRef = value;
        }

        /**
         * Gets the value of the remarks property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRemarks() {
            return remarks;
        }

        /**
         * Sets the value of the remarks property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRemarks(String value) {
            this.remarks = value;
        }

        /**
         * Gets the value of the status property.
         * 
         */
        public int getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         */
        public void setStatus(int value) {
            this.status = value;
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
