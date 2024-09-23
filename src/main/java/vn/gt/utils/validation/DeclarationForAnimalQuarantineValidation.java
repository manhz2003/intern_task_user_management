/**
 * 
 */
package vn.gt.utils.validation;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.DeclarationForAnimalQuarantine;

/**
 * @author win_64
 *
 */
public class DeclarationForAnimalQuarantineValidation {

	public boolean validation(DeclarationForAnimalQuarantine declarationForAnimal, Header header, String requestDirection) throws Exception {
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
		status = ValidationUtils.checkValidationNumber(declarationForAnimal.getDocumentName(), logMessageValidation, "58", "DocumentName", "02", maLoi1, 0, 9, 0, status);
		 
//DocumentYear
		status = ValidationUtils.checkValidationNumber(declarationForAnimal.getDocumentYear(), logMessageValidation, "58", "DocumentYear", "03", maLoi1, 0, 9, 0, status);
		
//UserCreated
		status = ValidationUtils.checkValidation(declarationForAnimal.getUserCreated(), logMessageValidation, "58", "UserCreated", "04", maLoi2, 14, status);
		
//NameOfShip
		status = ValidationUtils.checkValidation(declarationForAnimal.getNameOfShip(), logMessageValidation, "58", "NameOfShip", "05", maLoi2, 100, status);
		
//FlagStateOfShip danh muc
		status = ValidationUtils.checkValidationDanhMuc(declarationForAnimal.getFlagStateOfShip(), logMessageValidation, "58", "FlagStateOfShip", "06", maLoi6, 2, 11, status);

//NumberOfCrew		
		status = ValidationUtils.checkValidationNumber(declarationForAnimal.getNumberOfCrew(), logMessageValidation, "58", "NumberOfCrew", "07", maLoi1, 0, 9, 0, status);
		
//NumberOfPassengers
		status = ValidationUtils.checkValidationNumber(declarationForAnimal.getNumberOfPassengers(), logMessageValidation, "58", "NumberOfPassengers", "08", maLoi1, 0, 9, 0, status);

//LastPortOfCallCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(declarationForAnimal.getLastPortOfCallCode(), logMessageValidation, "57", "LastPortOfCallCode", "09", maLoi6, 5, 9, status);

//NextPortOfCallCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(declarationForAnimal.getNextPortOfCallCode(), logMessageValidation, "57", "NextPortOfCallCode", "10", maLoi6, 5, 9, status);

//AnimalNameFirst	
		status = ValidationUtils.checkValidation(declarationForAnimal.getAnimalNameFirst(), logMessageValidation, "58", "AnimalNameFirst", "11", maLoi2, 500, status);

//AnimalNameBetween
		status = ValidationUtils.checkValidation(declarationForAnimal.getAnimalNameBetween(), logMessageValidation, "58", "AnimalNameBetween", "12", maLoi2, 4000, status);

//AnimalNameThis
		status = ValidationUtils.checkValidation(declarationForAnimal.getAnimalNameThis(), logMessageValidation, "58", "AnimalNameThis", "13", maLoi2, 500, status);
		
//NameOfMaster
		if(declarationForAnimal.getNameOfMaster() != null && declarationForAnimal.getNameOfMaster().length() >0){
		status = ValidationUtils.checkValidation(declarationForAnimal.getNameOfMaster(), logMessageValidation, "57", "NameOfMaster", "14", maLoi4, 100, status);
		}		
//SignPlace
		if(declarationForAnimal.getSignPlace() != null && declarationForAnimal.getSignPlace().length() >0){
		status = ValidationUtils.checkValidation(declarationForAnimal.getSignPlace(), logMessageValidation, "58", "SignPlace", "15", maLoi4, 50, status);
		}
//SignDate
		if (declarationForAnimal.getSignDate() != null && !FormatData.isThisDateValid(declarationForAnimal.getSignDate())) {
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N58716: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//MasterSigned
		if(declarationForAnimal.getMasterSigned() != null && declarationForAnimal.getMasterSigned().length() >0){
		status = ValidationUtils.checkValidationNumber(declarationForAnimal.getMasterSigned(), logMessageValidation, "58", "MasterSigned", "17", maLoi5, 0, 9, 0, status);
		}
		return status;
	}
}

