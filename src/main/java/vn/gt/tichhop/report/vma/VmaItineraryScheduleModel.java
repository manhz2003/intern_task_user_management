package vn.gt.tichhop.report.vma;


import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;

public class VmaItineraryScheduleModel extends VmaItinerarySchedule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maritimeName;
	private String maritimeNameVN;

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
}
