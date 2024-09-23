//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.06 at 02:31:21 PM ICT 
//


package vn.nsw.model.inland;

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
 *         &lt;element name="AttachmentList" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AttachmentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ProvidedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DateOfPeriodicInspection" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CertificateNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Order" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "attachmentList"
})
@XmlRootElement(name = "Attachment")
public class Attachment {

    @XmlElement(name = "AttachmentList", required = true)
    protected List<Attachment.AttachmentList> attachmentList;

    /**
     * Gets the value of the attachmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attachmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttachmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Attachment.AttachmentList }
     * 
     * 
     */
    public List<Attachment.AttachmentList> getAttachmentList() {
        if (attachmentList == null) {
            attachmentList = new ArrayList<Attachment.AttachmentList>();
        }
        return this.attachmentList;
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
     *         &lt;element name="AttachmentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ProvidedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DateOfPeriodicInspection" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CertificateNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Order" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "attachmentType",
        "providedDate",
        "expirationDate",
        "dateOfPeriodicInspection",
        "certificateNo",
        "order",
        "comment",
        "description"
    })
    public static class AttachmentList {

        @XmlElement(name = "AttachmentType", required = true)
        protected String attachmentType;
        @XmlElement(name = "ProvidedDate", required = true)
        protected String providedDate;
        @XmlElement(name = "ExpirationDate", required = true)
        protected String expirationDate;
        @XmlElement(name = "DateOfPeriodicInspection", required = true)
        protected String dateOfPeriodicInspection;
        @XmlElement(name = "CertificateNo", required = true)
        protected String certificateNo;
        @XmlElement(name = "Order", required = true)
        protected String order;
        @XmlElement(name = "Comment", required = true)
        protected String comment;
        @XmlElement(name = "Description", required = true)
        protected String description;

        /**
         * Gets the value of the attachmentType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAttachmentType() {
            return attachmentType;
        }

        /**
         * Sets the value of the attachmentType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAttachmentType(String value) {
            this.attachmentType = value;
        }

        /**
         * Gets the value of the providedDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProvidedDate() {
            return providedDate;
        }

        /**
         * Sets the value of the providedDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProvidedDate(String value) {
            this.providedDate = value;
        }

        /**
         * Gets the value of the expirationDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExpirationDate() {
            return expirationDate;
        }

        /**
         * Sets the value of the expirationDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExpirationDate(String value) {
            this.expirationDate = value;
        }

        /**
         * Gets the value of the dateOfPeriodicInspection property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDateOfPeriodicInspection() {
            return dateOfPeriodicInspection;
        }

        /**
         * Sets the value of the dateOfPeriodicInspection property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDateOfPeriodicInspection(String value) {
            this.dateOfPeriodicInspection = value;
        }

        /**
         * Gets the value of the certificateNo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCertificateNo() {
            return certificateNo;
        }

        /**
         * Sets the value of the certificateNo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCertificateNo(String value) {
            this.certificateNo = value;
        }

        /**
         * Gets the value of the order property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrder() {
            return order;
        }

        /**
         * Sets the value of the order property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrder(String value) {
            this.order = value;
        }

        /**
         * Gets the value of the comment property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getComment() {
            return comment;
        }

        /**
         * Sets the value of the comment property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setComment(String value) {
            this.comment = value;
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

    }

}
