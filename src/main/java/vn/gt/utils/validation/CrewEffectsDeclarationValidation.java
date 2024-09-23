/**
 * 
 */
package vn.gt.utils.validation;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.CrewEffectsDeclaration;
import vn.nsw.model.CrewEffectsDeclaration.ListCrewEffects.CrewEffectItem;

/**
 * @author win_64
 *
 */
public class CrewEffectsDeclarationValidation {

	public boolean validation(CrewEffectsDeclaration crewEffectsDeclaration, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
		int [] maLoi1={1,3,4};
		int [] maLoi2={1,5,6};
		int [] maLoi4={5,6};
		int [] maLoi5={3,4};
		int [] maLoi6={1,2,5,6};
		
//DocumentName
		status = ValidationUtils.checkValidationNumber(crewEffectsDeclaration.getDocumentName(), logMessageValidation, "55", "DocumentName", "02", maLoi1, 0, 9, 0, status);
		 
//DocumentYear
		status = ValidationUtils.checkValidationNumber(crewEffectsDeclaration.getDocumentYear(), logMessageValidation, "55", "DocumentYear", "03", maLoi1, 0, 9, 0, status);
		
//UserCreated
		status = ValidationUtils.checkValidation(crewEffectsDeclaration.getUserCreated(), logMessageValidation, "55", "UserCreated", "04", maLoi2, 14, status);
		
//NameOfShip
		status = ValidationUtils.checkValidation(crewEffectsDeclaration.getNameOfShip(), logMessageValidation, "55", "NameOfShip", "05", maLoi2, 100, status);
		
//IMONumber	
		status = ValidationUtils.checkValidation(crewEffectsDeclaration.getIMONumber(), logMessageValidation, "55", "IMONumber", "06", maLoi2, 7, status);

//CallSign
		status = ValidationUtils.checkValidation(crewEffectsDeclaration.getCallsign(), logMessageValidation, "55", "CallSign", "07", maLoi2, 9, status);
		
//VoyageNumber
		status = ValidationUtils.checkValidation(crewEffectsDeclaration.getVoyageNumber(), logMessageValidation, "55", "VoyageNumber", "08", maLoi2, 20, status);
		
//FlagStateOfShip danh muc
		status = ValidationUtils.checkValidationDanhMuc(crewEffectsDeclaration.getFlagStateOfShip(), logMessageValidation, "55", "FlagStateOfShip", "09", maLoi6, 2, 11, status);

//NameOfMaster
		if(crewEffectsDeclaration.getNameOfMaster() != null && crewEffectsDeclaration.getNameOfMaster().length() >0){
		status = ValidationUtils.checkValidation(crewEffectsDeclaration.getNameOfMaster(), logMessageValidation, "55", "NameOfMaster", "10", maLoi4, 100, status);
		}
//SignPlace
		if(crewEffectsDeclaration.getSignPlace() != null && crewEffectsDeclaration.getSignPlace().length() >0){
		status = ValidationUtils.checkValidation(crewEffectsDeclaration.getSignPlace(), logMessageValidation, "55", "SignPlace", "18", maLoi4, 50, status);
		}
//SignDate
		if (crewEffectsDeclaration.getSignDate() != null && !FormatData.isThisDateValid(crewEffectsDeclaration.getSignDate())) {
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N55719: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//MasterSigned
		if(crewEffectsDeclaration.getMasterSigned() != null && crewEffectsDeclaration.getMasterSigned().length() >0){
		status = ValidationUtils.checkValidationNumber(crewEffectsDeclaration.getMasterSigned(), logMessageValidation, "55", "MasterSigned", "20", maLoi5, 0, 9, 0, status);
		}
		return status;
	}
	
	private boolean validationlist(CrewEffectItem crewEffectItem, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
		int [] maLoi1={1,3,4};
		int [] maLoi2={1,5,6};
		int [] maLoi4={5,6};
		int [] maLoi6={1,2,5,6};
		
//CrewEffectItemCode
		status = ValidationUtils.checkValidationNumber(crewEffectItem.getCrewEffectItemCode(), logMessageValidation, "55", "CrewEffectItemCode", "13", maLoi1, 0, 9, 0, status);
		
//FamilyName
		if(crewEffectItem.getFamilyName() != null && crewEffectItem.getFamilyName().length() >0){
		status = ValidationUtils.checkValidation(crewEffectItem.getFamilyName(), logMessageValidation, "55", "FamilyName", "14", maLoi4, 50, status);
		}
//GivenName		
		status = ValidationUtils.checkValidation(crewEffectItem.getGivenName(), logMessageValidation, "55", "GivenName", "15", maLoi2, 50, status);
		
//RankOrRatingCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(crewEffectItem.getRankOrRatingCode(), logMessageValidation, "55", "FlagStateOfShip", "16", maLoi6, 12, 13, status);
		
//EffectsIneligibleForRelief
		status = ValidationUtils.checkValidation(crewEffectItem.getEffectsIneligibleForRelief(), logMessageValidation, "55", "EffectsIneligibleForRelief", "17", maLoi2, 500, status);
		return status;
	}
}

