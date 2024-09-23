package vn.gt.portlet.baocaotichhop.thongke.dvcong;

public class DichVuCongModel {

	private String startDate;
	private String toDate;
	private String maritimeNameVN;
	private String maritimeName;
	private String maritimeCode;
	
	private HoSoDichVuCongDetail hoSoDichVuCongDetail;

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

	public String getMaritimeCode() {
		return maritimeCode;
	}

	public void setMaritimeCode(String maritimeCode) {
		this.maritimeCode = maritimeCode;
	}

	public HoSoDichVuCongDetail getHoSoDichVuCongDetail() {
		return hoSoDichVuCongDetail;
	}

	public void setHoSoDichVuCongDetail(HoSoDichVuCongDetail hoSoDichVuCongDetail) {
		this.hoSoDichVuCongDetail = hoSoDichVuCongDetail;
	}
	
	
	
}
