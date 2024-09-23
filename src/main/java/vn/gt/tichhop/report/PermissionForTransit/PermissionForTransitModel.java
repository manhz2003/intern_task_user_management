package vn.gt.tichhop.report.PermissionForTransit;

import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;

/* Report GIAY PHEP QUA CANH -- MAU SO 15 */

public class PermissionForTransitModel extends IssuePermissionForTransit {
	
	private String maritimeNameVN;
	private String maritimeName;
	private String documentTypeCode;

	public String getMaritimeNameVN() {
		return maritimeNameVN;
	}

	public void setMaritimeNameVN(String maritimeNameVN) {
		this.maritimeNameVN = maritimeNameVN;
	}

	public String getMaritimeName() {
		return maritimeName;
	}

	public void setMaritimeName(String maritimeName) {
		this.maritimeName = maritimeName;
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
}
