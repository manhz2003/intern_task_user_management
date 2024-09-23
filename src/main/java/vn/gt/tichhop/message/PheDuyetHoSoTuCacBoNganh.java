package vn.gt.tichhop.message;

import javax.xml.bind.annotation.XmlElement;

public class PheDuyetHoSoTuCacBoNganh {
	
	@XmlElement(name = "ApprovalStatus", required = true)
	protected String approvalStatus;
	
	@XmlElement(name = "Comment", required = true)
	protected String comment;
	
	@XmlElement(name = "Organization", required = true)
	protected String organization;
	
	@XmlElement(name = "Division", required = true)
	protected String division;
	
	@XmlElement(name = "Name", required = true)
	protected String name;
	
	@XmlElement(name = "ApprovalDate", required = true)
	protected String approvalDate;
	
	public String getApprovalStatus() {
		return approvalStatus;
	}
	
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
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
	
	public String getApprovalDate() {
		return approvalDate;
	}
	
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	
}
