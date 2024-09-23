package vn.gt.tichhop.message;

public class Messsage {
	//Reference
	private String ref_version;
	private String ref_messageId;
	//From
	private String frm_name;
	private String frm_identity;
	//To
	private String to_name;
	private String to_identity;
	//Subject
	private String documentType;
	private String type;
	private String function;
	private String reference;
	private String preReference;
	private String documentYear;
	private String sendDate;
	private String content;
	public Messsage(){
		
	}
	
	public Messsage(String ref_version, String ref_messageId, String frm_name,
			String frm_identity, String to_name, String to_identity,
			String documentType, String type, String function,
			String reference, String preReference, String documentYear,
			String sendDate) {
		super();
		this.ref_version = ref_version;
		this.ref_messageId = ref_messageId;
		this.frm_name = frm_name;
		this.frm_identity = frm_identity;
		this.to_name = to_name;
		this.to_identity = to_identity;
		this.documentType = documentType;
		this.type = type;
		this.function = function;
		this.reference = reference;
		this.preReference = preReference;
		this.documentYear = documentYear;
		this.sendDate = sendDate;
	}


	public String getRef_version() {
		return ref_version;
	}


	public void setRef_version(String ref_version) {
		this.ref_version = ref_version;
	}


	public String getRef_messageId() {
		return ref_messageId;
	}


	public void setRef_messageId(String ref_messageId) {
		this.ref_messageId = ref_messageId;
	}


	public String getFrm_name() {
		return frm_name;
	}


	public void setFrm_name(String frm_name) {
		this.frm_name = frm_name;
	}


	public String getFrm_identity() {
		return frm_identity;
	}


	public void setFrm_identity(String frm_identity) {
		this.frm_identity = frm_identity;
	}


	public String getTo_name() {
		return to_name;
	}


	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}


	public String getTo_identity() {
		return to_identity;
	}


	public void setTo_identity(String to_identity) {
		this.to_identity = to_identity;
	}


	public String getDocumentType() {
		return documentType;
	}


	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getFunction() {
		return function;
	}


	public void setFunction(String function) {
		this.function = function;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getPreReference() {
		return preReference;
	}


	public void setPreReference(String preReference) {
		this.preReference = preReference;
	}


	public String getDocumentYear() {
		return documentYear;
	}


	public void setDocumentYear(String documentYear) {
		this.documentYear = documentYear;
	}


	public String getSendDate() {
		return sendDate;
	}


	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}
