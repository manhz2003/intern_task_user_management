//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.31 at 04:25:23 PM ICT 
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
 *         &lt;element name="DmVmaTugboat" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MaritimeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TugboatCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TugboatCompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ShipName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Power" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LOA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Breadth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ClearanceHeight" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Displacement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="UnitPower" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VndUnitPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="UsdUnitPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Remarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="GT" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="NT" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="DWT" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="UnitGRT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="UnitNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="UnitDWT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TugboatShortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TugboatExpiredDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "dmVmaTugboat"
})
@XmlRootElement(name = "DmVmaTugboatList")
public class DmVmaTugboatList {

    @XmlElement(name = "DmVmaTugboat", required = true)
    protected List<DmVmaTugboatList.DmVmaTugboat> dmVmaTugboat;

    /**
     * Gets the value of the dmVmaTugboat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dmVmaTugboat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDmVmaTugboat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DmVmaTugboatList.DmVmaTugboat }
     * 
     * 
     */
    public List<DmVmaTugboatList.DmVmaTugboat> getDmVmaTugboat() {
        if (dmVmaTugboat == null) {
            dmVmaTugboat = new ArrayList<DmVmaTugboatList.DmVmaTugboat>();
        }
        return this.dmVmaTugboat;
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
     *         &lt;element name="ShipCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ShipName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Power" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LOA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Breadth" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ClearanceHeight" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Displacement" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="UnitPower" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="VndUnitPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="UsdUnitPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Remarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="GT" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="NT" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="DWT" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="UnitGRT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="UnitNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="UnitDWT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TugboatShortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TugboatExpiredDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "shipCode",
        "shipName",
        "power",
        "loa",
        "breadth",
        "clearanceHeight",
        "displacement",
        "unitPower",
        "vndUnitPrice",
        "usdUnitPrice",
        "remarks",
        "gt",
        "nt",
        "dwt",
        "unitGRT",
        "unitNT",
        "unitDWT",
        "tugboatShortName",
        "tugboatExpiredDate",
        "status",
        "syncVersion"
    })
    public static class DmVmaTugboat {

        @XmlElement(name = "MaritimeCode", required = true)
        protected String maritimeCode;
        @XmlElement(name = "TugboatCompanyCode", required = true)
        protected String tugboatCompanyCode;
        @XmlElement(name = "TugboatCompanyName", required = true)
        protected String tugboatCompanyName;
        @XmlElement(name = "ShipCode", required = true)
        protected String shipCode;
        @XmlElement(name = "ShipName", required = true)
        protected String shipName;
        @XmlElement(name = "Power", required = true)
        protected String power;
        @XmlElement(name = "LOA", required = true)
        protected String loa;
        @XmlElement(name = "Breadth", required = true)
        protected String breadth;
        @XmlElement(name = "ClearanceHeight", required = true)
        protected String clearanceHeight;
        @XmlElement(name = "Displacement", required = true)
        protected String displacement;
        @XmlElement(name = "UnitPower", required = true)
        protected String unitPower;
        @XmlElement(name = "VndUnitPrice", required = true)
        protected String vndUnitPrice;
        @XmlElement(name = "UsdUnitPrice", required = true)
        protected String usdUnitPrice;
        @XmlElement(name = "Remarks", required = true)
        protected String remarks;
        @XmlElement(name = "GT")
        protected int gt;
        @XmlElement(name = "NT")
        protected int nt;
        @XmlElement(name = "DWT")
        protected int dwt;
        @XmlElement(name = "UnitGRT", required = true)
        protected String unitGRT;
        @XmlElement(name = "UnitNT", required = true)
        protected String unitNT;
        @XmlElement(name = "UnitDWT", required = true)
        protected String unitDWT;
        @XmlElement(name = "TugboatShortName", required = true)
        protected String tugboatShortName;
        @XmlElement(name = "TugboatExpiredDate", required = true)
        protected String tugboatExpiredDate;
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
         * Gets the value of the shipCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipCode() {
            return shipCode;
        }

        /**
         * Sets the value of the shipCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipCode(String value) {
            this.shipCode = value;
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
         * Gets the value of the power property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPower() {
            return power;
        }

        /**
         * Sets the value of the power property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPower(String value) {
            this.power = value;
        }

        /**
         * Gets the value of the loa property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLOA() {
            return loa;
        }

        /**
         * Sets the value of the loa property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLOA(String value) {
            this.loa = value;
        }

        /**
         * Gets the value of the breadth property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBreadth() {
            return breadth;
        }

        /**
         * Sets the value of the breadth property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBreadth(String value) {
            this.breadth = value;
        }

        /**
         * Gets the value of the clearanceHeight property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getClearanceHeight() {
            return clearanceHeight;
        }

        /**
         * Sets the value of the clearanceHeight property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setClearanceHeight(String value) {
            this.clearanceHeight = value;
        }

        /**
         * Gets the value of the displacement property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDisplacement() {
            return displacement;
        }

        /**
         * Sets the value of the displacement property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDisplacement(String value) {
            this.displacement = value;
        }

        /**
         * Gets the value of the unitPower property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnitPower() {
            return unitPower;
        }

        /**
         * Sets the value of the unitPower property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnitPower(String value) {
            this.unitPower = value;
        }

        /**
         * Gets the value of the vndUnitPrice property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVndUnitPrice() {
            return vndUnitPrice;
        }

        /**
         * Sets the value of the vndUnitPrice property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVndUnitPrice(String value) {
            this.vndUnitPrice = value;
        }

        /**
         * Gets the value of the usdUnitPrice property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUsdUnitPrice() {
            return usdUnitPrice;
        }

        /**
         * Sets the value of the usdUnitPrice property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUsdUnitPrice(String value) {
            this.usdUnitPrice = value;
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
         * Gets the value of the gt property.
         * 
         */
        public int getGT() {
            return gt;
        }

        /**
         * Sets the value of the gt property.
         * 
         */
        public void setGT(int value) {
            this.gt = value;
        }

        /**
         * Gets the value of the nt property.
         * 
         */
        public int getNT() {
            return nt;
        }

        /**
         * Sets the value of the nt property.
         * 
         */
        public void setNT(int value) {
            this.nt = value;
        }

        /**
         * Gets the value of the dwt property.
         * 
         */
        public int getDWT() {
            return dwt;
        }

        /**
         * Sets the value of the dwt property.
         * 
         */
        public void setDWT(int value) {
            this.dwt = value;
        }

        /**
         * Gets the value of the unitGRT property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnitGRT() {
            return unitGRT;
        }

        /**
         * Sets the value of the unitGRT property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnitGRT(String value) {
            this.unitGRT = value;
        }

        /**
         * Gets the value of the unitNT property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnitNT() {
            return unitNT;
        }

        /**
         * Sets the value of the unitNT property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnitNT(String value) {
            this.unitNT = value;
        }

        /**
         * Gets the value of the unitDWT property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnitDWT() {
            return unitDWT;
        }

        /**
         * Sets the value of the unitDWT property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnitDWT(String value) {
            this.unitDWT = value;
        }

        /**
         * Gets the value of the tugboatShortName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTugboatShortName() {
            return tugboatShortName;
        }

        /**
         * Sets the value of the tugboatShortName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTugboatShortName(String value) {
            this.tugboatShortName = value;
        }

        /**
         * Gets the value of the tugboatExpiredDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTugboatExpiredDate() {
            return tugboatExpiredDate;
        }

        /**
         * Sets the value of the tugboatExpiredDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTugboatExpiredDate(String value) {
            this.tugboatExpiredDate = value;
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
