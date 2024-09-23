package vn.gt.tichhop.report.CrewEffectsDeclaration;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration;
import com.fds.nsw.nghiepvu.model.TempCrewEffectsItems;

public class CrewEffectsDeclarationModel {
	
	private List<TempCrewEffectsDeclaration> tempCrewEffectsDeclarations;
	private List<TempCrewEffectsItems> tempCrewEffectsItems;
	private String signDate;
	private String signPlace;
	private String documentTypeCode;
	
	public CrewEffectsDeclarationModel(List<TempCrewEffectsDeclaration> tempCrewEffectsDeclarations,
			List<TempCrewEffectsItems> tempCrewEffectsItems, String signDate, String signPlace) {
		super();
		this.tempCrewEffectsDeclarations = tempCrewEffectsDeclarations;
		this.tempCrewEffectsItems = tempCrewEffectsItems;
		this.signDate = signDate;
		this.signPlace = signPlace;
	}
	
	public CrewEffectsDeclarationModel() {
	}
	
	public List<TempCrewEffectsDeclaration> getTempCrewEffectsDeclarations() {
		return tempCrewEffectsDeclarations;
	}
	
	public void setTempCrewEffectsDeclarations(List<TempCrewEffectsDeclaration> tempCrewEffectsDeclarations) {
		this.tempCrewEffectsDeclarations = tempCrewEffectsDeclarations;
	}
	
	public List<TempCrewEffectsItems> getTempCrewEffectsItems() {
		return tempCrewEffectsItems;
	}
	
	public void setTempCrewEffectsItems(List<TempCrewEffectsItems> tempCrewEffectsItems) {
		this.tempCrewEffectsItems = tempCrewEffectsItems;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
}
