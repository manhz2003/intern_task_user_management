package vn.gt.tichhop.report.PttndPortClearance;

import com.fds.nsw.nghiepvu.model.IssuePortClearance;

public class IssuePortClearanceModel extends IssuePortClearance {
	
	private String maritimeName;
	private String maritimeNameVN;
	private String arrivalPortName;
	
	public IssuePortClearanceModel() {
	}
	
	public String getMaritimeName() {
		return maritimeName;
	}
	
	public void setMaritimeName(String maritimeName) {
		this.maritimeName = maritimeName;
	}
	
	public String getMaritimeNameVN() {
		return maritimeNameVN;
	}
	
	public void setMaritimeNameVN(String maritimeNameVN) {
		this.maritimeNameVN = maritimeNameVN;
	}
	
	public String getArrivalPortName() {
		return arrivalPortName;
	}
	
	public void setArrivalPortName(String arrivalPortName) {
		this.arrivalPortName = arrivalPortName;
	}
}
