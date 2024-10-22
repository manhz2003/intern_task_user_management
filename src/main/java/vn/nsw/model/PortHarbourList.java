//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:41:34 AM ICT 
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
 *         &lt;element name="PortHarbour" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PortRegionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortHarbourName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PortHarbourCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "portHarbour"
})
@XmlRootElement(name = "PortHarbourList")
public class PortHarbourList {

    @XmlElement(name = "PortHarbour", required = true)
    protected List<PortHarbourList.PortHarbour> portHarbour;

    /**
     * Gets the value of the portHarbour property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portHarbour property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortHarbour().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortHarbourList.PortHarbour }
     * 
     * 
     */
    public List<PortHarbourList.PortHarbour> getPortHarbour() {
        if (portHarbour == null) {
            portHarbour = new ArrayList<PortHarbourList.PortHarbour>();
        }
        return this.portHarbour;
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
     *         &lt;element name="PortRegionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortHarbourName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PortHarbourCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "portRegionCode",
        "portHarbourName",
        "portHarbourCode",
        "status"
    })
    public static class PortHarbour {

        @XmlElement(name = "PortRegionCode", required = true)
        protected String portRegionCode;
        @XmlElement(name = "PortHarbourName", required = true)
        protected String portHarbourName;
        @XmlElement(name = "PortHarbourCode", required = true)
        protected String portHarbourCode;
        @XmlElement(name = "Status", required = true)
        protected String status;

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
         * Gets the value of the portHarbourName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPortHarbourName() {
            return portHarbourName;
        }

        /**
         * Sets the value of the portHarbourName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPortHarbourName(String value) {
            this.portHarbourName = value;
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

    }

}
