package vn.gt.tichhop.report.DeclarationOfHealth;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth;
import com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList;
import com.fds.nsw.nghiepvu.model.TempHealthQuestion;

public class DeclarationOfHealthModel {
	
	private List<TempDeclarationOfHealth> tempDeclarationOfHealths;
	private List<TempHealthCrewPaxList> tempHealthCrewPassengerLists;
	private List<TempHealthQuestion> tempHealthQuestions;
	private String masterName;
	private String doctorName;
	private String signDate;
	private String documentTypeCode;
	
	public DeclarationOfHealthModel() { }
	
	public List<TempDeclarationOfHealth> getTempDeclarationOfHealths() {
		return tempDeclarationOfHealths;
	}

	public void setTempDeclarationOfHealths(List<TempDeclarationOfHealth> tempDeclarationOfHealths) {
		this.tempDeclarationOfHealths = tempDeclarationOfHealths;
	}

	public List<TempHealthCrewPaxList> getTempHealthCrewPassengerLists() {
		return tempHealthCrewPassengerLists;
	}
	
	public void setTempHealthCrewPassengerLists(List<TempHealthCrewPaxList> tempHealthCrewPassengerLists) {
		this.tempHealthCrewPassengerLists = tempHealthCrewPassengerLists;
	}
	
	public List<TempHealthQuestion> getTempHealthQuestions() {
		return tempHealthQuestions;
	}
	
	public void setTempHealthQuestions(List<TempHealthQuestion> tempHealthQuestions) {
		this.tempHealthQuestions = tempHealthQuestions;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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
