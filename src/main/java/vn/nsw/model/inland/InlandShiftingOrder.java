//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.27 at 05:11:27 AM ICT 
//


package vn.nsw.model.inland;

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
 *         &lt;element name="DocumentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentYear" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PortofAuthority" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NameOfShip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CallSign" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnchoringPortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PortHarbourCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnchoringPortWharfCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShiftingDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IssueDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DirectorOfMaritimeAdministration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CertificateNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Representative" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AttachedFile">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AttachedTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AttachedTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AttachedDocName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AttachedNote" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FullFileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SHA1FileContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Base64FileContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FileURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "documentName",
    "documentYear",
    "portofAuthority",
    "nameOfShip",
    "callSign",
    "anchoringPortCode",
    "portHarbourCode",
    "anchoringPortWharfCode",
    "shiftingDate",
    "issueDate",
    "directorOfMaritimeAdministration",
    "certificateNo",
    "representative",
    "attachedFile"
})
@XmlRootElement(name = "InlandShiftingOrder")
public class InlandShiftingOrder {

    @XmlElement(name = "DocumentName", required = true)
    protected String documentName;
    @XmlElement(name = "DocumentYear", required = true)
    protected String documentYear;
    @XmlElement(name = "PortofAuthority", required = true)
    protected String portofAuthority;
    @XmlElement(name = "NameOfShip", required = true)
    protected String nameOfShip;
    @XmlElement(name = "CallSign", required = true)
    protected String callSign;
    @XmlElement(name = "AnchoringPortCode", required = true)
    protected String anchoringPortCode;
    @XmlElement(name = "PortHarbourCode", required = true)
    protected String portHarbourCode;
    @XmlElement(name = "AnchoringPortWharfCode", required = true)
    protected String anchoringPortWharfCode;
    @XmlElement(name = "ShiftingDate", required = true)
    protected String shiftingDate;
    @XmlElement(name = "IssueDate", required = true)
    protected String issueDate;
    @XmlElement(name = "DirectorOfMaritimeAdministration", required = true)
    protected String directorOfMaritimeAdministration;
    @XmlElement(name = "CertificateNo", required = true)
    protected String certificateNo;
    @XmlElement(name = "Representative", required = true)
    protected String representative;
    @XmlElement(name = "AttachedFile", required = true)
    protected InlandShiftingOrder.AttachedFile attachedFile;

    /**
     * Gets the value of the documentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * Sets the value of the documentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentName(String value) {
        this.documentName = value;
    }

    /**
     * Gets the value of the documentYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentYear() {
        return documentYear;
    }

    /**
     * Sets the value of the documentYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentYear(String value) {
        this.documentYear = value;
    }

    /**
     * Gets the value of the portofAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortofAuthority() {
        return portofAuthority;
    }

    /**
     * Sets the value of the portofAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortofAuthority(String value) {
        this.portofAuthority = value;
    }

    /**
     * Gets the value of the nameOfShip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOfShip() {
        return nameOfShip;
    }

    /**
     * Sets the value of the nameOfShip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOfShip(String value) {
        this.nameOfShip = value;
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
     * Gets the value of the anchoringPortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchoringPortCode() {
        return anchoringPortCode;
    }

    /**
     * Sets the value of the anchoringPortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchoringPortCode(String value) {
        this.anchoringPortCode = value;
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
     * Gets the value of the anchoringPortWharfCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchoringPortWharfCode() {
        return anchoringPortWharfCode;
    }

    /**
     * Sets the value of the anchoringPortWharfCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchoringPortWharfCode(String value) {
        this.anchoringPortWharfCode = value;
    }

    /**
     * Gets the value of the shiftingDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShiftingDate() {
        return shiftingDate;
    }

    /**
     * Sets the value of the shiftingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShiftingDate(String value) {
        this.shiftingDate = value;
    }

    /**
     * Gets the value of the issueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueDate(String value) {
        this.issueDate = value;
    }

    /**
     * Gets the value of the directorOfMaritimeAdministration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectorOfMaritimeAdministration() {
        return directorOfMaritimeAdministration;
    }

    /**
     * Sets the value of the directorOfMaritimeAdministration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectorOfMaritimeAdministration(String value) {
        this.directorOfMaritimeAdministration = value;
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
     * Gets the value of the representative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepresentative() {
        return representative;
    }

    /**
     * Sets the value of the representative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepresentative(String value) {
        this.representative = value;
    }

    /**
     * Gets the value of the attachedFile property.
     * 
     * @return
     *     possible object is
     *     {@link InlandShiftingOrder.AttachedFile }
     *     
     */
    public InlandShiftingOrder.AttachedFile getAttachedFile() {
        return attachedFile;
    }

    /**
     * Sets the value of the attachedFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link InlandShiftingOrder.AttachedFile }
     *     
     */
    public void setAttachedFile(InlandShiftingOrder.AttachedFile value) {
        this.attachedFile = value;
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
     *         &lt;element name="AttachedTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AttachedTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AttachedDocName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AttachedNote" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FullFileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SHA1FileContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Base64FileContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FileURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "attachedTypeCode",
        "attachedTypeName",
        "attachedDocName",
        "attachedNote",
        "fullFileName",
        "sha1FileContent",
        "base64FileContent",
        "fileURL"
    })
    public static class AttachedFile {

        @XmlElement(name = "AttachedTypeCode", required = true)
        protected String attachedTypeCode;
        @XmlElement(name = "AttachedTypeName", required = true)
        protected String attachedTypeName;
        @XmlElement(name = "AttachedDocName", required = true)
        protected String attachedDocName;
        @XmlElement(name = "AttachedNote", required = true)
        protected String attachedNote;
        @XmlElement(name = "FullFileName", required = true)
        protected String fullFileName;
        @XmlElement(name = "SHA1FileContent", required = true)
        protected String sha1FileContent;
        @XmlElement(name = "Base64FileContent", required = true)
        protected String base64FileContent;
        @XmlElement(name = "FileURL", required = true)
        protected String fileURL;

        /**
         * Gets the value of the attachedTypeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAttachedTypeCode() {
            return attachedTypeCode;
        }

        /**
         * Sets the value of the attachedTypeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAttachedTypeCode(String value) {
            this.attachedTypeCode = value;
        }

        /**
         * Gets the value of the attachedTypeName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAttachedTypeName() {
            return attachedTypeName;
        }

        /**
         * Sets the value of the attachedTypeName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAttachedTypeName(String value) {
            this.attachedTypeName = value;
        }

        /**
         * Gets the value of the attachedDocName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAttachedDocName() {
            return attachedDocName;
        }

        /**
         * Sets the value of the attachedDocName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAttachedDocName(String value) {
            this.attachedDocName = value;
        }

        /**
         * Gets the value of the attachedNote property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAttachedNote() {
            return attachedNote;
        }

        /**
         * Sets the value of the attachedNote property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAttachedNote(String value) {
            this.attachedNote = value;
        }

        /**
         * Gets the value of the fullFileName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFullFileName() {
            return fullFileName;
        }

        /**
         * Sets the value of the fullFileName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFullFileName(String value) {
            this.fullFileName = value;
        }

        /**
         * Gets the value of the sha1FileContent property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSHA1FileContent() {
            return sha1FileContent;
        }

        /**
         * Sets the value of the sha1FileContent property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSHA1FileContent(String value) {
            this.sha1FileContent = value;
        }

        /**
         * Gets the value of the base64FileContent property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBase64FileContent() {
            return base64FileContent;
        }

        /**
         * Sets the value of the base64FileContent property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBase64FileContent(String value) {
            this.base64FileContent = value;
        }

        /**
         * Gets the value of the fileURL property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFileURL() {
            return fileURL;
        }

        /**
         * Sets the value of the fileURL property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFileURL(String value) {
            this.fileURL = value;
        }

    }

}
