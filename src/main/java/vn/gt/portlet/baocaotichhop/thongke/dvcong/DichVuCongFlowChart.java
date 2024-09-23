package vn.gt.portlet.baocaotichhop.thongke.dvcong;

import java.util.List;

public class DichVuCongFlowChart {
	
	private String maritimeNameVN;
	private String dateFrom;
	private String dateTo;
	private List<FlowChartDataByDate> listHoSoNhapCanh;
	private List<FlowChartDataByDate> listHoSoXuatCanh;
	private List<FlowChartDataByDate> listHoSoQuaCanh;
	
	public String getMaritimeNameVN() {
		return maritimeNameVN;
	}
	public void setMaritimeNameVN(String maritimeNameVN) {
		this.maritimeNameVN = maritimeNameVN;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public List<FlowChartDataByDate> getListHoSoNhapCanh() {
		return listHoSoNhapCanh;
	}
	public void setListHoSoNhapCanh(List<FlowChartDataByDate> listHoSoNhapCanh) {
		this.listHoSoNhapCanh = listHoSoNhapCanh;
	}
	public List<FlowChartDataByDate> getListHoSoXuatCanh() {
		return listHoSoXuatCanh;
	}
	public void setListHoSoXuatCanh(List<FlowChartDataByDate> listHoSoXuatCanh) {
		this.listHoSoXuatCanh = listHoSoXuatCanh;
	}
	public List<FlowChartDataByDate> getListHoSoQuaCanh() {
		return listHoSoQuaCanh;
	}
	public void setListHoSoQuaCanh(List<FlowChartDataByDate> listHoSoQuaCanh) {
		this.listHoSoQuaCanh = listHoSoQuaCanh;
	}

}
