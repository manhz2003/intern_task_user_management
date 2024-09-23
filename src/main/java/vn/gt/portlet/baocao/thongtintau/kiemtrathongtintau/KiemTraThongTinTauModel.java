package vn.gt.portlet.baocao.thongtintau.kiemtrathongtintau;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.ResultCertificate;

public class KiemTraThongTinTauModel {
	
	private String createDate;
	private String maritimeNameVN;
	private String maritimeName;
	private String ministryNameVN;
	private boolean hasData;
	private List<TempDocument> allTemDocument;
	private List<ResultCertificate> allResultCertificate;
	
	public boolean isHasData() {
		return hasData;
	}
	
	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
	
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
	
	public List<TempDocument> getAllTemDocument() {
		return allTemDocument;
	}
	
	public void setAllTemDocument(List<TempDocument> allTemDocument) {
		this.allTemDocument = allTemDocument;
	}
	
	public List<ResultCertificate> getAllResultCertificate() {
		return allResultCertificate;
	}
	
	public void setAllResultCertificate(List<ResultCertificate> allResultCertificate) {
		this.allResultCertificate = allResultCertificate;
	}
	
}
