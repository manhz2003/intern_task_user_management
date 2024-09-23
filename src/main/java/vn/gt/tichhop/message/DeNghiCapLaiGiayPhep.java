package vn.gt.tichhop.message;

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
 *         &lt;element name="RenewDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Organization" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Division" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsApprove" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "renewDate",
    "organization",
    "division",
    "name",
    "reason",
    "isApprove"
})
@XmlRootElement(name = "DeNghiCapLaiGiayPhep")
public class DeNghiCapLaiGiayPhep {

    @XmlElement(name = "RenewDate", required = true)
    protected String renewDate;
    @XmlElement(name = "Organization", required = true)
    protected String organization;
    @XmlElement(name = "Division", required = true)
    protected String division;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Reason", required = true)
    protected String reason;
    @XmlElement(name = "IsApprove", required = true)
    protected String isApprove;
    
    
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRenewDate() {
		return renewDate;
	}
	public void setRenewDate(String renewDate) {
		this.renewDate = renewDate;
	}
 
	

}
