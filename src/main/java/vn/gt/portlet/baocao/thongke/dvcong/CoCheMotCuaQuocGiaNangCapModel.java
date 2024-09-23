package vn.gt.portlet.baocao.thongke.dvcong;

import java.util.List;

import com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc;


public class CoCheMotCuaQuocGiaNangCapModel {

	private String startDate;
	private String toDate;
	private String maritimeNameVN;
	private String maritimeName;
	private String reportDate;
	
	private String monthOfPeriod;
	private String yearOfPeriod;

	private List<ViewHoanThanhThuTuc> viewHoanThanhThuTuc;			
	
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getMonthOfPeriod() {
		return monthOfPeriod;
	}
	public void setMonthOfPeriod(String monthOfPeriod) {
		this.monthOfPeriod = monthOfPeriod;
	}
	public String getYearOfPeriod() {
		return yearOfPeriod;
	}
	public void setYearOfPeriod(String yearOfPeriod) {
		this.yearOfPeriod = yearOfPeriod;
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
	
	public List<ViewHoanThanhThuTuc> getViewHoanThanhThuTuc() {
		return viewHoanThanhThuTuc;
	}
	
	public void setViewHoanThanhThuTuc(List<ViewHoanThanhThuTuc> viewHoanThanhThuTuc) {
		this.viewHoanThanhThuTuc = viewHoanThanhThuTuc;
	}
	
}
