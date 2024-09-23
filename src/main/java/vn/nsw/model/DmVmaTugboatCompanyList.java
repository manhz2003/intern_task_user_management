//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.22 at 10:19:31 AM ICT 
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
 *         &lt;element name="DmVmaTugboatCompany" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MaritimeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TugboatCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TugboatCompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CompanyAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ContactEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TelNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FaxNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Remarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CompanyShortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "dmVmaTugboatCompany"
})
@XmlRootElement(name = "DmVmaTugboatCompanyList")
public class DmVmaTugboatCompanyList {

    @XmlElement(name = "DmVmaTugboatCompany", required = true)
    protected List<DmVmaTugboatCompanyList.DmVmaTugboatCompany> dmVmaTugboatCompany;

    /**
     * Gets the value of the dmVmaTugboatCompany property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dmVmaTugboatCompany property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDmVmaTugboatCompany().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DmVmaTugboatCompanyList.DmVmaTugboatCompany }
     * 
     * 
     */
    public List<DmVmaTugboatCompanyList.DmVmaTugboatCompany> getDmVmaTugboatCompany() {
        if (dmVmaTugboatCompany == null) {
            dmVmaTugboatCompany = new ArrayList<DmVmaTugboatCompanyList.DmVmaTugboatCompany>();
        }
        return this.dmVmaTugboatCompany;
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
     *         &lt;element name="TugboatCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TugboatCompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CompanyAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ContactEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TelNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FaxNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Remarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CompanyShortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "tugboatCompanyCode",
        "tugboatCompanyName",
        "companyAddress",
        "contactEmail",
        "telNo",
        "faxNo",
        "remarks",
        "companyShortName",
        "status",
        "syncVersion"
    })
    public static class DmVmaTugboatCompany {

        @XmlElement(name = "MaritimeCode", required = true)
        protected String maritimeCode;
        @XmlElement(name = "TugboatCompanyCode", required = true)
        protected String tugboatCompanyCode;
        @XmlElement(name = "TugboatCompanyName", required = true)
        protected String tugboatCompanyName;
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
         * Gets the value of the tugboatCompanyCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTugboatCompanyCode() {
            return tugboatCompanyCode;
        }

        /**
         * Sets the value of the tugboatCompanyCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTugboatCompanyCode(String value) {
            this.tugboatCompanyCode = value;
        }

        /**
         * Gets the value of the tugboatCompanyName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTugboatCompanyName() {
            return tugboatCompanyName;
        }

        /**
         * Sets the value of the tugboatCompanyName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTugboatCompanyName(String value) {
            this.tugboatCompanyName = value;
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
