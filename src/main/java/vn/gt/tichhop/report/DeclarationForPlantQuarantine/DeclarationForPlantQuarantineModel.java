package vn.gt.tichhop.report.DeclarationForPlantQuarantine;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempPlantQuarantine;

public class DeclarationForPlantQuarantineModel {

	List<TempPlantQuarantine> tempPlantQuarantines;
	private String documentTypeCode;

	public List<TempPlantQuarantine> getTempPlantQuarantines() {
		return tempPlantQuarantines;
	}

	public void setTempPlantQuarantines(
			List<TempPlantQuarantine> tempPlantQuarantines) {
		this.tempPlantQuarantines = tempPlantQuarantines;
	}

	/**
	 * @param tempPlantQuarantines
	 */
	public DeclarationForPlantQuarantineModel(
			List<TempPlantQuarantine> tempPlantQuarantines) {
		super();
		this.tempPlantQuarantines = tempPlantQuarantines;
	}

	public DeclarationForPlantQuarantineModel(){
		
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
}
