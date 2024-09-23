package vn.gt.tichhop.report.CrewList;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import com.fds.nsw.nghiepvu.model.TempCrewList;

public class CrewListModel {
	private String signPlace;
	private String signDate;
	private List<TempCrewList> tempCrewLists;
	private List<TempCrewDetails> tempCrewDetails;
	private String documentTypeCode;
	
	/**
	 * @param tempCrewLists
	 * @param tempCrewDetails
	 */
	public CrewListModel(List<TempCrewList> tempCrewLists,
			List<TempCrewDetails> tempCrewDetails) {
		super();
		this.tempCrewLists = tempCrewLists;
		this.tempCrewDetails = tempCrewDetails;
	}
	public String getSignPlace() {
		return signPlace;
	}
	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}
	public CrewListModel() {
		
	}
	
	public List<TempCrewList> getTempCrewLists() {
		return tempCrewLists;
	}
	public void setTempCrewLists(List<TempCrewList> tempCrewLists) {
		this.tempCrewLists = tempCrewLists;
	}
	public List<TempCrewDetails> getTempCrewDetails() {
		return tempCrewDetails;
	}
	public void setTempCrewDetails(List<TempCrewDetails> tempCrewDetails) {
		this.tempCrewDetails = tempCrewDetails;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getDocumentTypeCode() {
		return documentTypeCode;
	}
	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
	
}
