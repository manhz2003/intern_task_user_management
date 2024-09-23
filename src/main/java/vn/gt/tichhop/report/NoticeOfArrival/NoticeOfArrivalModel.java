package vn.gt.tichhop.report.NoticeOfArrival;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempPersonList;

public class NoticeOfArrivalModel {

	private List<TempNoticeShipMessage> tempNoTiceShipMessage;
	private String message;
	private String signPlace;
	private String signDate;
	private Integer id;
	
	private Integer countIllness;
	private Integer countDead;
	private Integer countRescued;
	private Integer countStow;
	private String documentTypeCode;
	
	private List<TempPersonList> tempPersonLists;
	
	public String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public NoticeOfArrivalModel() {};
	
	public NoticeOfArrivalModel(List<TempNoticeShipMessage> tempNoTiceShipMessage, String message) {
		super();
		this.tempNoTiceShipMessage = tempNoTiceShipMessage;
		this.message = message;
	}
	
	public List<TempNoticeShipMessage> getTempNoTiceShipMessage() {
		return tempNoTiceShipMessage;
	}
	
	public void setTempNoTiceShipMessage(List<TempNoticeShipMessage> tempNoTiceShipMessage) {
		this.tempNoTiceShipMessage = tempNoTiceShipMessage;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<TempPersonList> getTempPersonLists() {
		return tempPersonLists;
	}

	public void setTempPersonLists(List<TempPersonList> tempPersonLists) {
		this.tempPersonLists = tempPersonLists;
	}

	public Integer getCountIllness() {
		return countIllness;
	}

	public void setCountIllness(Integer countIllness) {
		this.countIllness = countIllness;
	}

	public Integer getCountDead() {
		return countDead;
	}

	public void setCountDead(Integer countDead) {
		this.countDead = countDead;
	}

	public Integer getCountRescued() {
		return countRescued;
	}

	public void setCountRescued(Integer countRescued) {
		this.countRescued = countRescued;
	}

	public Integer getCountStow() {
		return countStow;
	}

	public void setCountStow(Integer countStow) {
		this.countStow = countStow;
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
}
