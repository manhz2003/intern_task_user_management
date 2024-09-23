package vn.gt.portlet.baocao.kehoachdieudong;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;

public class KeHoachDieuDongModel {

	private String maritimeName;
	private String createDate;
	private String createTime;
	private int reportCode;
	private boolean hasData;
	private List<TempNoticeShipMessage> allNoticeShipMessagesArival;
	private List<TempNoticeShipMessage> allNoticeShipMessagesLeave;
	
	
	public boolean getHasData() {
		return hasData;
	}
	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
	public int getReportCode() {
		return reportCode;
	}
	public void setReportCode(int reportCode) {
		this.reportCode = reportCode;
	}
	public String getMaritimeName() {
		return maritimeName;
	}
	public void setMaritimeName(String maritimeName) {
		this.maritimeName = maritimeName;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public List<TempNoticeShipMessage> getAllNoticeShipMessagesArival() {
		return allNoticeShipMessagesArival;
	}
	public void setAllNoticeShipMessagesArival(
			List<TempNoticeShipMessage> allNoticeShipMessagesArival) {
		this.allNoticeShipMessagesArival = allNoticeShipMessagesArival;
	}
	public List<TempNoticeShipMessage> getAllNoticeShipMessagesLeave() {
		return allNoticeShipMessagesLeave;
	}
	public void setAllNoticeShipMessagesLeave(
			List<TempNoticeShipMessage> allNoticeShipMessagesLeave) {
		this.allNoticeShipMessagesLeave = allNoticeShipMessagesLeave;
	}
	
}
