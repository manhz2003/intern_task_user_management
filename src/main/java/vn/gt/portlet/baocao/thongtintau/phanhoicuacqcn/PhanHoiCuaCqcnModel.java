package vn.gt.portlet.baocao.thongtintau.phanhoicuacqcn;

import java.util.List;

import com.fds.nsw.nghiepvu.model.ResultMinistry;


public class PhanHoiCuaCqcnModel {
	
	private String createDate;
	private String maritimeNameVN;
	private String maritimeName;
	private String ministryNameVN;
	private String shipName;
	private String stateCode;
	private String callSign;
	private Double grt;
	private String shipCaptain;
	private String requestCode;
	
	private List<ResultMinistry> allResultHistoryMinistrie;
	
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
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
	
	public String getMinistryNameVN() {
		return ministryNameVN;
	}
	
	public void setMinistryNameVN(String ministryNameVN) {
		this.ministryNameVN = ministryNameVN;
	}
	
	public String getShipName() {
		return shipName;
	}
	
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	public String getStateCode() {
		return stateCode;
	}
	
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	public String getCallSign() {
		return callSign;
	}
	
	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}
	
	public Double getGrt() {
		return grt;
	}
	
	public void setGrt(Double grt) {
		this.grt = grt;
	}
	
	public String getShipCaptain() {
		return shipCaptain;
	}
	
	public void setShipCaptain(String shipCaptain) {
		this.shipCaptain = shipCaptain;
	}
	
	public String getRequestCode() {
		return requestCode;
	}
	
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	
	public List<ResultMinistry> getAllResultHistoryMinistrie() {
		return allResultHistoryMinistrie;
	}
	
	public void setAllResultHistoryMinistrie(List<ResultMinistry> allResultHistoryMinistrie) {
		this.allResultHistoryMinistrie = allResultHistoryMinistrie;
	}
	
}
