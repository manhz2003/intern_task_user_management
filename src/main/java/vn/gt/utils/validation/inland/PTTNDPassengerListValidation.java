/**
 * 
 */
package vn.gt.utils.validation.inland;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.gt.utils.validation.ValidationUtils;
import vn.nsw.Header;
import vn.nsw.model.inland.PTTNDPassengerList;


/**
 * @author win_64
 *
 */
public class PTTNDPassengerListValidation {

	public boolean validation(PTTNDPassengerList object, Header header, String requestDirection) throws Exception {
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
		status = ValidationUtils.checkValidationNumber(object.getDocumentName(), logMessageValidation, "52", "DocumentName", "02", maLoi1, 0, 9, 0, status);
		 
		//DocumentYear
		if(object.getDocumentYear() != null && object.getDocumentYear().length() >0){
			status = ValidationUtils.checkValidationNumber(object.getDocumentYear(), logMessageValidation, "52", "DocumentYear", "03", maLoi5, 0, 9, 0, status);
		}
		
		//UserCreated
		status = ValidationUtils.checkValidation(object.getUserCreated(), logMessageValidation, "52", "UserCreated", "04", maLoi2, 14, status);
		
		//IsArrival
		status = ValidationUtils.checkValidationNumber(object.getIsArrival(), logMessageValidation, "52", "IsArrival", "05", maLoi1, 0, 1, 0, status);
		
		//NameOfShip
		status = ValidationUtils.checkValidation(object.getNameOfShip(), logMessageValidation, "52", "NameOfShip", "06", maLoi2, 100, status);
		
		//DateOfArrival
		status = ValidationUtils.checkValidation(object.getDateOfArrival(), logMessageValidation, "52", "DateOfArrival", "09", maLoi3, 100, status);
		
		//CallSign
		status = ValidationUtils.checkValidation(object.getCallSign(), logMessageValidation, "52", "CallSign", "11", maLoi2, 9, status);
		
		//VoyageNumber
		status = ValidationUtils.checkValidation(object.getVoyageNumber(), logMessageValidation, "52", "VoyageNumber", "12", maLoi2, 20, status);
		
		//NumberOfLeftPassengers
		status = ValidationUtils.checkValidation(object.getNumberOfLeftPassengers(), logMessageValidation, "52", "NumberOfLeftPassengers", "10", maLoi1, 20, status);
		
		//NumberOfVNmeses
		status = ValidationUtils.checkValidation(object.getNumberOfVNmeses(), logMessageValidation, "52", "NumberOfVNmeses", "11", maLoi1, 20, status);
		
		//NumberOfForeigners
		status = ValidationUtils.checkValidation(object.getNumberOfForeigners(), logMessageValidation, "52", "NumberOfForeigners", "12", maLoi1, 20, status);
		
		//TotalPassengers
		status = ValidationUtils.checkValidation(object.getTotalPassengers(), logMessageValidation, "52", "TotalPassengers", "13", maLoi1, 20, status);
		
		//SignPlace
		if(object.getSignPlace() != null && object.getSignPlace().length() >0){
			status = ValidationUtils.checkValidation(object.getSignPlace(), logMessageValidation, "52", "SignPlace", "27", maLoi4, 50, status);
		}
		//SignDate
		if (object.getSignDate() != null && !FormatData.isThisDateValid(object.getSignDate())) {
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N52728: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
		//MasterSigned
		if(object.getMasterSigned() != null && object.getMasterSigned().length() >0){
			status = ValidationUtils.checkValidationNumber(object.getMasterSigned(), logMessageValidation, "52", "MasterSigned", "29", maLoi5, 0, 9, 0, status);
		}
		
		return status;
	}
}

