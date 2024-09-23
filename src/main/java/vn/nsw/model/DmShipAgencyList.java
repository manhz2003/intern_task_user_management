//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.05 at 04:07:07 PM ICT 
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
 *         &lt;element name="DmShipAgency" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ShipAgencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipAgencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipAgencyNameVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TaxCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="StateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AddressVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Fax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Representative1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RepresentativeTitle1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Representative2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RepresentativeTitle2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Contacter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Position" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ContactTell" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "dmShipAgency"
})
@XmlRootElement(name = "DmShipAgencyList")
public class DmShipAgencyList {

    @XmlElement(name = "DmShipAgency", required = true)
    protected List<DmShipAgencyList.DmShipAgency> dmShipAgency;

    /**
     * Gets the value of the dmShipAgency property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dmShipAgency property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDmShipAgency().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DmShipAgencyList.DmShipAgency }
     * 
     * 
     */
    public List<DmShipAgencyList.DmShipAgency> getDmShipAgency() {
        if (dmShipAgency == null) {
            dmShipAgency = new ArrayList<DmShipAgencyList.DmShipAgency>();
        }
        return this.dmShipAgency;
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
     *         &lt;element name="ShipAgencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ShipAgencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ShipAgencyNameVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TaxCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="StateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AddressVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Fax" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Representative1" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RepresentativeTitle1" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Representative2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RepresentativeTitle2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Contacter" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Position" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ContactTell" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        "shipAgencyCode",
        "shipAgencyName",
        "shipAgencyNameVN",
        "taxCode",
        "stateCode",
        "cityCode",
        "address",
        "addressVN",
        "phone",
        "fax",
        "email",
        "description",
        "representative1",
        "representativeTitle1",
        "representative2",
        "representativeTitle2",
        "contacter",
        "position",
        "contactTell",
        "status"
    })
    public static class DmShipAgency {

        @XmlElement(name = "ShipAgencyCode", required = true)
        protected String shipAgencyCode;
        @XmlElement(name = "ShipAgencyName", required = true)
        protected String shipAgencyName;
        @XmlElement(name = "ShipAgencyNameVN", required = true)
        protected String shipAgencyNameVN;
        @XmlElement(name = "TaxCode", required = true)
        protected String taxCode;
        @XmlElement(name = "StateCode", required = true)
        protected String stateCode;
        @XmlElement(name = "CityCode", required = true)
        protected String cityCode;
        @XmlElement(name = "Address", required = true)
        protected String address;
        @XmlElement(name = "AddressVN", required = true)
        protected String addressVN;
        @XmlElement(name = "Phone", required = true)
        protected String phone;
        @XmlElement(name = "Fax", required = true)
        protected String fax;
        @XmlElement(name = "Email", required = true)
        protected String email;
        @XmlElement(name = "Description", required = true)
        protected String description;
        @XmlElement(name = "Representative1", required = true)
        protected String representative1;
        @XmlElement(name = "RepresentativeTitle1", required = true)
        protected String representativeTitle1;
        @XmlElement(name = "Representative2", required = true)
        protected String representative2;
        @XmlElement(name = "RepresentativeTitle2", required = true)
        protected String representativeTitle2;
        @XmlElement(name = "Contacter", required = true)
        protected String contacter;
        @XmlElement(name = "Position", required = true)
        protected String position;
        @XmlElement(name = "ContactTell", required = true)
        protected String contactTell;
        @XmlElement(name = "Status")
        protected int status;

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
         * Gets the value of the shipAgencyNameVN property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipAgencyNameVN() {
            return shipAgencyNameVN;
        }

        /**
         * Sets the value of the shipAgencyNameVN property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipAgencyNameVN(String value) {
            this.shipAgencyNameVN = value;
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
         * Gets the value of the phone property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPhone() {
            return phone;
        }

        /**
         * Sets the value of the phone property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPhone(String value) {
            this.phone = value;
        }

        /**
         * Gets the value of the fax property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFax() {
            return fax;
        }

        /**
         * Sets the value of the fax property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFax(String value) {
            this.fax = value;
        }

        /**
         * Gets the value of the email property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEmail() {
            return email;
        }

        /**
         * Sets the value of the email property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEmail(String value) {
            this.email = value;
        }

        /**
         * Gets the value of the description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescription() {
            return description;
        }

        /**
         * Sets the value of the description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescription(String value) {
            this.description = value;
        }

        /**
         * Gets the value of the representative1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRepresentative1() {
            return representative1;
        }

        /**
         * Sets the value of the representative1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRepresentative1(String value) {
            this.representative1 = value;
        }

        /**
         * Gets the value of the representativeTitle1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRepresentativeTitle1() {
            return representativeTitle1;
        }

        /**
         * Sets the value of the representativeTitle1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRepresentativeTitle1(String value) {
            this.representativeTitle1 = value;
        }

        /**
         * Gets the value of the representative2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRepresentative2() {
            return representative2;
        }

        /**
         * Sets the value of the representative2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRepresentative2(String value) {
            this.representative2 = value;
        }

        /**
         * Gets the value of the representativeTitle2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRepresentativeTitle2() {
            return representativeTitle2;
        }

        /**
         * Sets the value of the representativeTitle2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRepresentativeTitle2(String value) {
            this.representativeTitle2 = value;
        }

        /**
         * Gets the value of the contacter property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getContacter() {
            return contacter;
        }

        /**
         * Sets the value of the contacter property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setContacter(String value) {
            this.contacter = value;
        }

        /**
         * Gets the value of the position property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPosition() {
            return position;
        }

        /**
         * Sets the value of the position property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPosition(String value) {
            this.position = value;
        }

        /**
         * Gets the value of the contactTell property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getContactTell() {
            return contactTell;
        }

        /**
         * Sets the value of the contactTell property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setContactTell(String value) {
            this.contactTell = value;
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

    }

}
