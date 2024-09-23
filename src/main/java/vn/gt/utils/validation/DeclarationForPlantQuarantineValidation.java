/**
 * 
 */
package vn.gt.utils.validation;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.DeclarationForPlantQuarantine;

/**
 * @author win_64
 *
 */
public class DeclarationForPlantQuarantineValidation {

	public boolean validation(DeclarationForPlantQuarantine declarationForPlantQuarantine, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
		int [] maLoi1={1,3,4};
		int [] maLoi2={1,5,6};
		int [] maLoi3={1,7};
		int [] maLoi4={5,6};
		int [] maLoi5={3,4};
		int [] maLoi6={1,2,5,6};
//DocumentName
		status = ValidationUtils.checkValidationNumber(declarationForPlantQuarantine.getDocumentName(), logMessageValidation, "57", "DocumentName", "02", maLoi1, 0, 9, 0, status);
		 
//DocumentYear
		status = ValidationUtils.checkValidationNumber(declarationForPlantQuarantine.getDocumentYear(), logMessageValidation, "57", "DocumentYear", "03", maLoi1, 0, 9, 0, status);
		
//UserCreated
		status = ValidationUtils.checkValidation(declarationForPlantQuarantine.getUserCreated(), logMessageValidation, "57", "UserCreated", "04", maLoi2, 14, status);
		
//NameOfShip
		status = ValidationUtils.checkValidation(declarationForPlantQuarantine.getNameOfShip(), logMessageValidation, "57", "NameOfShip", "05", maLoi2, 100, status);
		
//FlagStateOfShip danh muc
		status = ValidationUtils.checkValidationDanhMuc(declarationForPlantQuarantine.getFlagStateOfShip(), logMessageValidation, "57", "FlagStateOfShip", "06", maLoi6, 2, 11, status);

//NameOfMaster
		if(declarationForPlantQuarantine.getNameOfMaster() != null && declarationForPlantQuarantine.getNameOfMaster().length() >0){
		status = ValidationUtils.checkValidation(declarationForPlantQuarantine.getNameOfMaster(), logMessageValidation, "57", "NameOfMaster", "07", maLoi4, 100, status);
		}
//DoctorName
		status = ValidationUtils.checkValidation(declarationForPlantQuarantine.getDoctorName(), logMessageValidation, "57", "DoctorName", "08", maLoi2, 100, status);
		
//NumberOfCrew		
		status = ValidationUtils.checkValidationNumber(declarationForPlantQuarantine.getNumberOfCrew(), logMessageValidation, "57", "NumberOfCrew", "09", maLoi1, 0, 9, 0, status);
		
//NumberOfPassengers
		status = ValidationUtils.checkValidationNumber(declarationForPlantQuarantine.getNumberOfPassengers(), logMessageValidation, "57", "NumberOfPassengers", "10", maLoi1, 0, 9, 0, status);

//LastPortOfCallCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(declarationForPlantQuarantine.getLastPortOfCallCode(), logMessageValidation, "57", "LastPortOfCallCode", "11", maLoi6, 5, 9, status);

//NextPortOfCallCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(declarationForPlantQuarantine.getNextPortOfCallCode(), logMessageValidation, "57", "NextPortOfCallCode", "12", maLoi6, 5, 9, status);

//FirstPortOfLoadingCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(declarationForPlantQuarantine.getFirstPortOfLoadingCode(), logMessageValidation, "57", "FirstPortOfLoadingCode", "13", maLoi6, 5, 9, status);

//DateOfDeparture
		status = ValidationUtils.checkValidation(declarationForPlantQuarantine.getDateOfDeparture(), logMessageValidation, "57", "DateOfDeparture", "14", maLoi3, 100, status);

		
//PlantNameFirst	
		status = ValidationUtils.checkValidation(declarationForPlantQuarantine.getPlantNameFirst(), logMessageValidation, "57", "PlantNameFirst", "15", maLoi2, 500, status);

//PlantNameBetween
		status = ValidationUtils.checkValidation(declarationForPlantQuarantine.getPlantNameBetween(), logMessageValidation, "57", "PlantNameBetween", "16", maLoi2, 4000, status);

//PlantNameThis
		status = ValidationUtils.checkValidation(declarationForPlantQuarantine.getPlantNameThis(), logMessageValidation, "57", "PlantNameThis", "17", maLoi2, 500, status);
		
//SignPlace
		if(declarationForPlantQuarantine.getSignPlace() != null && declarationForPlantQuarantine.getSignPlace().length() >0){
		status = ValidationUtils.checkValidation(declarationForPlantQuarantine.getSignPlace(), logMessageValidation, "57", "SignPlace", "18", maLoi4, 50, status);
		}
//SignDate
		if (declarationForPlantQuarantine.getSignDate() != null && !FormatData.isThisDateValid(declarationForPlantQuarantine.getSignDate())) {
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N57719: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//MasterSigned
		if(declarationForPlantQuarantine.getMasterSigned() != null && declarationForPlantQuarantine.getMasterSigned().length() >0){
		status = ValidationUtils.checkValidationNumber(declarationForPlantQuarantine.getMasterSigned(), logMessageValidation, "57", "MasterSigned", "20", maLoi5, 0, 9, 0, status);
		}
		return status;
	}
}

