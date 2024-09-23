package vn.gt.tichhop.report.PassengerList;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempPassengerDetails;
import com.fds.nsw.nghiepvu.model.TempPassengerList;

public class PassengerListModel {
	
	private String signPlace;
	private String documentTypeCode;
	
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
	private String signDate;
	private List<TempPassengerList> tempPassengerLists;
	private List<TempPassengerDetails> tempPassengerDetails;
	
	/**
	 * @param tempPassengerLists
	 * @param tempPassengerDetails
	 */
	public PassengerListModel(List<TempPassengerList> tempPassengerLists,
			List<TempPassengerDetails> tempPassengerDetails) {
		super();
		this.tempPassengerLists = tempPassengerLists;
		this.tempPassengerDetails = tempPassengerDetails;
	}

	public PassengerListModel(){
	}
	
	public List<TempPassengerList> getTempPassengerLists() {
		return tempPassengerLists;
	}
	public void setTempPassengerLists(List<TempPassengerList> tempPassengerLists) {
		this.tempPassengerLists = tempPassengerLists;
	}
	public List<TempPassengerDetails> getTempPassengerDetails() {
		return tempPassengerDetails;
	}
	public void setTempPassengerDetails(
			List<TempPassengerDetails> tempPassengerDetails) {
		this.tempPassengerDetails = tempPassengerDetails;
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
	
	
}
