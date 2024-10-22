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
 *         &lt;element name="mtgateway" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaritimeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipPreviousName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipTypeMT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipBoat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HasTugBoat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TugBoatName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NameOfMaster" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChiefOfEngineer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMONumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CallSign" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FlagStateOfShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VRCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RegistryNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RegistryDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RegistryPortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DWT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LOA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Breadth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ClearanceHeight" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Power" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaxDraft" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShownDraftxF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShownDraftxA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UnitPower" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductionCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductionYear" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipOwnerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipOperatorCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipAgencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExpiredDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Remarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsDelete" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ModifiedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MarkedAsDelete" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "mtgateway",
    "maritimeCode",
    "shipName",
    "shipPreviousName",
    "shipTypeMT",
    "shipTypeCode",
    "shipBoat",
    "hasTugBoat",
    "tugBoatName",
    "nameOfMaster",
    "chiefOfEngineer",
    "imoNumber",
    "callSign",
    "flagStateOfShip",
    "vrCode",
    "registryNumber",
    "registryDate",
    "registryPortCode",
    "gt",
    "nt",
    "dwt",
    "loa",
    "breadth",
    "clearanceHeight",
    "power",
    "maxDraft",
    "shownDraftxF",
    "shownDraftxA",
    "unitPower",
    "productionCountry",
    "productionYear",
    "shipOwnerCode",
    "shipOperatorCode",
    "shipAgencyCode",
    "expiredDate",
    "remarks",
    "isDelete",
    "modifiedDate",
    "markedAsDelete",
    "requestedDate",
    "syncVersion"
})
@XmlRootElement(name = "VmaShip")
public class VmaShip {

    @XmlElement(required = true)
    protected String mtgateway;
    @XmlElement(name = "MaritimeCode", required = true)
    protected String maritimeCode;
    @XmlElement(name = "ShipName", required = true)
    protected String shipName;
    @XmlElement(name = "ShipPreviousName", required = true)
    protected String shipPreviousName;
    @XmlElement(name = "ShipTypeMT", required = true)
    protected String shipTypeMT;
    @XmlElement(name = "ShipTypeCode", required = true)
    protected String shipTypeCode;
    @XmlElement(name = "ShipBoat", required = true)
    protected String shipBoat;
    @XmlElement(name = "HasTugBoat", required = true)
    protected String hasTugBoat;
    @XmlElement(name = "TugBoatName", required = true)
    protected String tugBoatName;
    @XmlElement(name = "NameOfMaster", required = true)
    protected String nameOfMaster;
    @XmlElement(name = "ChiefOfEngineer", required = true)
    protected String chiefOfEngineer;
    @XmlElement(name = "IMONumber", required = true)
    protected String imoNumber;
    @XmlElement(name = "CallSign", required = true)
    protected String callSign;
    @XmlElement(name = "FlagStateOfShip", required = true)
    protected String flagStateOfShip;
    @XmlElement(name = "VRCode", required = true)
    protected String vrCode;
    @XmlElement(name = "RegistryNumber", required = true)
    protected String registryNumber;
    @XmlElement(name = "RegistryDate", required = true)
    protected String registryDate;
    @XmlElement(name = "RegistryPortCode", required = true)
    protected String registryPortCode;
    @XmlElement(name = "GT", required = true)
    protected String gt;
    @XmlElement(name = "NT", required = true)
    protected String nt;
    @XmlElement(name = "DWT", required = true)
    protected String dwt;
    @XmlElement(name = "LOA", required = true)
    protected String loa;
    @XmlElement(name = "Breadth", required = true)
    protected String breadth;
    @XmlElement(name = "ClearanceHeight", required = true)
    protected String clearanceHeight;
    @XmlElement(name = "Power", required = true)
    protected String power;
    @XmlElement(name = "MaxDraft", required = true)
    protected String maxDraft;
    @XmlElement(name = "ShownDraftxF", required = true)
    protected String shownDraftxF;
    @XmlElement(name = "ShownDraftxA", required = true)
    protected String shownDraftxA;
    @XmlElement(name = "UnitPower", required = true)
    protected String unitPower;
    @XmlElement(name = "ProductionCountry", required = true)
    protected String productionCountry;
    @XmlElement(name = "ProductionYear", required = true)
    protected String productionYear;
    @XmlElement(name = "ShipOwnerCode", required = true)
    protected String shipOwnerCode;
    @XmlElement(name = "ShipOperatorCode", required = true)
    protected String shipOperatorCode;
    @XmlElement(name = "ShipAgencyCode", required = true)
    protected String shipAgencyCode;
    @XmlElement(name = "ExpiredDate", required = true)
    protected String expiredDate;
    @XmlElement(name = "Remarks", required = true)
    protected String remarks;
    @XmlElement(name = "IsDelete", required = true)
    protected String isDelete;
    @XmlElement(name = "ModifiedDate", required = true)
    protected String modifiedDate;
    @XmlElement(name = "MarkedAsDelete", required = true)
    protected String markedAsDelete;
    @XmlElement(name = "RequestedDate", required = true)
    protected String requestedDate;
    @XmlElement(name = "SyncVersion", required = true)
    protected String syncVersion;

    /**
     * Gets the value of the mtgateway property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtgateway() {
        return mtgateway;
    }

    /**
     * Sets the value of the mtgateway property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtgateway(String value) {
        this.mtgateway = value;
    }

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
     * Gets the value of the shipPreviousName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipPreviousName() {
        return shipPreviousName;
    }

    /**
     * Sets the value of the shipPreviousName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipPreviousName(String value) {
        this.shipPreviousName = value;
    }

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
     * Gets the value of the shipBoat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipBoat() {
        return shipBoat;
    }

    /**
     * Sets the value of the shipBoat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipBoat(String value) {
        this.shipBoat = value;
    }

    /**
     * Gets the value of the hasTugBoat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasTugBoat() {
        return hasTugBoat;
    }

    /**
     * Sets the value of the hasTugBoat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasTugBoat(String value) {
        this.hasTugBoat = value;
    }

    /**
     * Gets the value of the tugBoatName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTugBoatName() {
        return tugBoatName;
    }

    /**
     * Sets the value of the tugBoatName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTugBoatName(String value) {
        this.tugBoatName = value;
    }

    /**
     * Gets the value of the nameOfMaster property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOfMaster() {
        return nameOfMaster;
    }

    /**
     * Sets the value of the nameOfMaster property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOfMaster(String value) {
        this.nameOfMaster = value;
    }

    /**
     * Gets the value of the chiefOfEngineer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiefOfEngineer() {
        return chiefOfEngineer;
    }

    /**
     * Sets the value of the chiefOfEngineer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiefOfEngineer(String value) {
        this.chiefOfEngineer = value;
    }

    /**
     * Gets the value of the imoNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMONumber() {
        return imoNumber;
    }

    /**
     * Sets the value of the imoNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMONumber(String value) {
        this.imoNumber = value;
    }

    /**
     * Gets the value of the callSign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallSign() {
        return callSign;
    }

    /**
     * Sets the value of the callSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallSign(String value) {
        this.callSign = value;
    }

    /**
     * Gets the value of the flagStateOfShip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagStateOfShip() {
        return flagStateOfShip;
    }

    /**
     * Sets the value of the flagStateOfShip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagStateOfShip(String value) {
        this.flagStateOfShip = value;
    }

    /**
     * Gets the value of the vrCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVRCode() {
        return vrCode;
    }

    /**
     * Sets the value of the vrCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVRCode(String value) {
        this.vrCode = value;
    }

    /**
     * Gets the value of the registryNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistryNumber() {
        return registryNumber;
    }

    /**
     * Sets the value of the registryNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistryNumber(String value) {
        this.registryNumber = value;
    }

    /**
     * Gets the value of the registryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistryDate() {
        return registryDate;
    }

    /**
     * Sets the value of the registryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistryDate(String value) {
        this.registryDate = value;
    }

    /**
     * Gets the value of the registryPortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistryPortCode() {
        return registryPortCode;
    }

    /**
     * Sets the value of the registryPortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistryPortCode(String value) {
        this.registryPortCode = value;
    }

    /**
     * Gets the value of the gt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGT() {
        return gt;
    }

    /**
     * Sets the value of the gt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGT(String value) {
        this.gt = value;
    }

    /**
     * Gets the value of the nt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNT() {
        return nt;
    }

    /**
     * Sets the value of the nt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNT(String value) {
        this.nt = value;
    }

    /**
     * Gets the value of the dwt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDWT() {
        return dwt;
    }

    /**
     * Sets the value of the dwt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDWT(String value) {
        this.dwt = value;
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
     * Gets the value of the maxDraft property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxDraft() {
        return maxDraft;
    }

    /**
     * Sets the value of the maxDraft property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxDraft(String value) {
        this.maxDraft = value;
    }

    /**
     * Gets the value of the shownDraftxF property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShownDraftxF() {
        return shownDraftxF;
    }

    /**
     * Sets the value of the shownDraftxF property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShownDraftxF(String value) {
        this.shownDraftxF = value;
    }

    /**
     * Gets the value of the shownDraftxA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShownDraftxA() {
        return shownDraftxA;
    }

    /**
     * Sets the value of the shownDraftxA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShownDraftxA(String value) {
        this.shownDraftxA = value;
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
     * Gets the value of the productionCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductionCountry() {
        return productionCountry;
    }

    /**
     * Sets the value of the productionCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductionCountry(String value) {
        this.productionCountry = value;
    }

    /**
     * Gets the value of the productionYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductionYear() {
        return productionYear;
    }

    /**
     * Sets the value of the productionYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductionYear(String value) {
        this.productionYear = value;
    }

    /**
     * Gets the value of the shipOwnerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipOwnerCode() {
        return shipOwnerCode;
    }

    /**
     * Sets the value of the shipOwnerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipOwnerCode(String value) {
        this.shipOwnerCode = value;
    }

    /**
     * Gets the value of the shipOperatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipOperatorCode() {
        return shipOperatorCode;
    }

    /**
     * Sets the value of the shipOperatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipOperatorCode(String value) {
        this.shipOperatorCode = value;
    }

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
     * Gets the value of the expiredDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpiredDate() {
        return expiredDate;
    }

    /**
     * Sets the value of the expiredDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiredDate(String value) {
        this.expiredDate = value;
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
