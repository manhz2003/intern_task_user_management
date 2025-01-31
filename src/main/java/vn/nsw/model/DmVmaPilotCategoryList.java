//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.19 at 02:59:05 PM ICT 
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
 *         &lt;element name="DmVmaPilotCategory" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PilotCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PilotCategoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MaxLength" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="GrossTonage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SafeTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "dmVmaPilotCategory"
})
@XmlRootElement(name = "DmVmaPilotCategoryList")
public class DmVmaPilotCategoryList {

    @XmlElement(name = "DmVmaPilotCategory", required = true)
    protected List<DmVmaPilotCategoryList.DmVmaPilotCategory> dmVmaPilotCategory;

    /**
     * Gets the value of the dmVmaPilotCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dmVmaPilotCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDmVmaPilotCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DmVmaPilotCategoryList.DmVmaPilotCategory }
     * 
     * 
     */
    public List<DmVmaPilotCategoryList.DmVmaPilotCategory> getDmVmaPilotCategory() {
        if (dmVmaPilotCategory == null) {
            dmVmaPilotCategory = new ArrayList<DmVmaPilotCategoryList.DmVmaPilotCategory>();
        }
        return this.dmVmaPilotCategory;
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
     *         &lt;element name="PilotCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PilotCategoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MaxLength" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="GrossTonage" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SafeTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "pilotCategoryCode",
        "pilotCategoryName",
        "maxLength",
        "grossTonage",
        "safeTime",
        "remarks",
        "status",
        "syncVersion"
    })
    public static class DmVmaPilotCategory {

        @XmlElement(name = "PilotCategoryCode", required = true)
        protected String pilotCategoryCode;
        @XmlElement(name = "PilotCategoryName", required = true)
        protected String pilotCategoryName;
        @XmlElement(name = "MaxLength", required = true)
        protected String maxLength;
        @XmlElement(name = "GrossTonage", required = true)
        protected String grossTonage;
        @XmlElement(name = "SafeTime", required = true)
        protected String safeTime;
        @XmlElement(name = "Remarks", required = true)
        protected String remarks;
        @XmlElement(name = "Status")
        protected int status;
        @XmlElement(name = "SyncVersion", required = true)
        protected String syncVersion;

        /**
         * Gets the value of the pilotCategoryCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPilotCategoryCode() {
            return pilotCategoryCode;
        }

        /**
         * Sets the value of the pilotCategoryCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPilotCategoryCode(String value) {
            this.pilotCategoryCode = value;
        }

        /**
         * Gets the value of the pilotCategoryName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPilotCategoryName() {
            return pilotCategoryName;
        }

        /**
         * Sets the value of the pilotCategoryName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPilotCategoryName(String value) {
            this.pilotCategoryName = value;
        }

        /**
         * Gets the value of the maxLength property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMaxLength() {
            return maxLength;
        }

        /**
         * Sets the value of the maxLength property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMaxLength(String value) {
            this.maxLength = value;
        }

        /**
         * Gets the value of the grossTonage property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGrossTonage() {
            return grossTonage;
        }

        /**
         * Sets the value of the grossTonage property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGrossTonage(String value) {
            this.grossTonage = value;
        }

        /**
         * Gets the value of the safeTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSafeTime() {
            return safeTime;
        }

        /**
         * Sets the value of the safeTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSafeTime(String value) {
            this.safeTime = value;
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
