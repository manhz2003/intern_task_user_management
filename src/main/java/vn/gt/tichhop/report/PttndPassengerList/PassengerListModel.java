package vn.gt.tichhop.report.PttndPassengerList;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempPassengerDetails;
import com.fds.nsw.nghiepvu.model.TempPassengerList;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;

public class PassengerListModel {
	
	private String signPlace;
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
	private List<TempGeneralDeclaration> tempGeneralDeclaration;
	/**
	 * @param tempPassengerLists
	 * @param tempPassengerDetails
	 */
	public PassengerListModel(List<TempPassengerList> tempPassengerLists,
			List<TempPassengerDetails> tempPassengerDetails, List<TempGeneralDeclaration> tempGeneralDeclaration) {
		super();
		this.tempPassengerLists = tempPassengerLists;
		this.tempPassengerDetails = tempPassengerDetails;
		this.tempGeneralDeclaration = tempGeneralDeclaration;
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
	
	public List<TempGeneralDeclaration> getTempGeneralDeclaration() {
		return tempGeneralDeclaration;
	}
	public void setTempGeneralDeclaration(List<TempGeneralDeclaration> tempGeneralDeclaration) {
		this.tempGeneralDeclaration = tempGeneralDeclaration;
	}
	
}
