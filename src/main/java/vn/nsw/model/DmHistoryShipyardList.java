//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.22 at 10:34:25 AM ICT 
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
 *         &lt;element name="DmHistoryShipyard" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MaritimeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipYardCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TaxCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CompanyAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ContactEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TelNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FaxNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Remarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CompanyShortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ProfileMaintainane" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ProfileConstruction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ProfileDeconstruction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MarkupMaintainane" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="MarkupConstruction" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="MarkupDeconstruction" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "dmHistoryShipyard"
})
@XmlRootElement(name = "DmHistoryShipyardList")
public class DmHistoryShipyardList {

    @XmlElement(name = "DmHistoryShipyard", required = true)
    protected List<DmHistoryShipyardList.DmHistoryShipyard> dmHistoryShipyard;

    /**
     * Gets the value of the dmHistoryShipyard property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dmHistoryShipyard property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDmHistoryShipyard().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DmHistoryShipyardList.DmHistoryShipyard }
     * 
     * 
     */
    public List<DmHistoryShipyardList.DmHistoryShipyard> getDmHistoryShipyard() {
        if (dmHistoryShipyard == null) {
            dmHistoryShipyard = new ArrayList<DmHistoryShipyardList.DmHistoryShipyard>();
        }
        return this.dmHistoryShipyard;
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
     *         &lt;element name="MaritimeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ShipYardCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TaxCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CompanyAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ContactEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TelNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FaxNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Remarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CompanyShortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ProfileMaintainane" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ProfileConstruction" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ProfileDeconstruction" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MarkupMaintainane" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="MarkupConstruction" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="MarkupDeconstruction" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        "maritimeCode",
        "shipYardCode",
        "taxCode",
        "companyName",
        "companyAddress",
        "contactEmail",
        "telNo",
        "faxNo",
        "remarks",
        "companyShortName",
        "profileMaintainane",
        "profileConstruction",
        "profileDeconstruction",
        "markupMaintainane",
        "markupConstruction",
        "markupDeconstruction",
        "status",
        "syncVersion"
    })
    public static class DmHistoryShipyard {

        @XmlElement(name = "MaritimeCode", required = true)
        protected String maritimeCode;
        @XmlElement(name = "ShipYardCode", required = true)
        protected String shipYardCode;
        @XmlElement(name = "TaxCode", required = true)
        protected String taxCode;
        @XmlElement(name = "CompanyName", required = true)
        protected String companyName;
        @XmlElement(name = "CompanyAddress", required = true)
        protected String companyAddress;
        @XmlElement(name = "ContactEmail", required = true)
        protected String contactEmail;
        @XmlElement(name = "TelNo", required = true)
        protected String telNo;
        @XmlElement(name = "FaxNo", required = true)
        protected String faxNo;
        @XmlElement(name = "Remarks", required = true)
        protected String remarks;
        @XmlElement(name = "CompanyShortName", required = true)
        protected String companyShortName;
        @XmlElement(name = "ProfileMaintainane", required = true)
        protected String profileMaintainane;
        @XmlElement(name = "ProfileConstruction", required = true)
        protected String profileConstruction;
        @XmlElement(name = "ProfileDeconstruction", required = true)
        protected String profileDeconstruction;
        @XmlElement(name = "MarkupMaintainane")
        protected int markupMaintainane;
        @XmlElement(name = "MarkupConstruction")
        protected int markupConstruction;
        @XmlElement(name = "MarkupDeconstruction")
        protected int markupDeconstruction;
        @XmlElement(name = "Status")
        protected int status;
        @XmlElement(name = "SyncVersion", required = true)
        protected String syncVersion;

        /**
         * Gets the value of the maritimeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMaritimeCode() {
            return maritimeCode;
        }

        /**
         * Sets the value of the maritimeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMaritimeCode(String value) {
            this.maritimeCode = value;
        }

        /**
         * Gets the value of the shipYardCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipYardCode() {
            return shipYardCode;
        }

        /**
         * Sets the value of the shipYardCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipYardCode(String value) {
            this.shipYardCode = value;
        }

        /**
         * Gets the value of the taxCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTaxCode() {
            return taxCode;
        }

        /**
         * Sets the value of the taxCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTaxCode(String value) {
            this.taxCode = value;
        }

        /**
         * Gets the value of the companyName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCompanyName() {
            return companyName;
        }

        /**
         * Sets the value of the companyName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCompanyName(String value) {
            this.companyName = value;
        }

        /**
         * Gets the value of the companyAddress property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCompanyAddress() {
            return companyAddress;
        }

        /**
         * Sets the value of the companyAddress property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCompanyAddress(String value) {
            this.companyAddress = value;
        }

        /**
         * Gets the value of the contactEmail property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getContactEmail() {
            return contactEmail;
        }

        /**
         * Sets the value of the contactEmail property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setContactEmail(String value) {
            this.contactEmail = value;
        }

        /**
         * Gets the value of the telNo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTelNo() {
            return telNo;
        }

        /**
         * Sets the value of the telNo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTelNo(String value) {
            this.telNo = value;
        }

        /**
         * Gets the value of the faxNo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFaxNo() {
            return faxNo;
        }

        /**
         * Sets the value of the faxNo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFaxNo(String value) {
            this.faxNo = value;
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
         * Gets the value of the companyShortName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCompanyShortName() {
            return companyShortName;
        }

        /**
         * Sets the value of the companyShortName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCompanyShortName(String value) {
            this.companyShortName = value;
        }

        /**
         * Gets the value of the profileMaintainane property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProfileMaintainane() {
            return profileMaintainane;
        }

        /**
         * Sets the value of the profileMaintainane property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProfileMaintainane(String value) {
            this.profileMaintainane = value;
        }

        /**
         * Gets the value of the profileConstruction property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProfileConstruction() {
            return profileConstruction;
        }

        /**
         * Sets the value of the profileConstruction property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProfileConstruction(String value) {
            this.profileConstruction = value;
        }

        /**
         * Gets the value of the profileDeconstruction property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProfileDeconstruction() {
            return profileDeconstruction;
        }

        /**
         * Sets the value of the profileDeconstruction property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProfileDeconstruction(String value) {
            this.profileDeconstruction = value;
        }

        /**
         * Gets the value of the markupMaintainane property.
         * 
         */
        public int getMarkupMaintainane() {
            return markupMaintainane;
        }

        /**
         * Sets the value of the markupMaintainane property.
         * 
         */
        public void setMarkupMaintainane(int value) {
            this.markupMaintainane = value;
        }

        /**
         * Gets the value of the markupConstruction property.
         * 
         */
        public int getMarkupConstruction() {
            return markupConstruction;
        }

        /**
         * Sets the value of the markupConstruction property.
         * 
         */
        public void setMarkupConstruction(int value) {
            this.markupConstruction = value;
        }

        /**
         * Gets the value of the markupDeconstruction property.
         * 
         */
        public int getMarkupDeconstruction() {
            return markupDeconstruction;
        }

        /**
         * Sets the value of the markupDeconstruction property.
         * 
         */
        public void setMarkupDeconstruction(int value) {
            this.markupDeconstruction = value;
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
