/**
 * 
 */
package vn.gt.utils.validation.inland;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.inland.InlandPassengerList;
import vn.nsw.model.inland.InlandPassengerList.ListPassengers.Passenger;


/**
 * @author win_64
 *
 */
public class InlandPassengerListValidation {

	public boolean validation(InlandPassengerList passengerList, Header header, String requestDirection) throws Exception {
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
		status = ValidationUtils.checkValidationNumber(passengerList.getDocumentName(), logMessageValidation, "52", "DocumentName", "02", maLoi1, 0, 9, 0, status);
		 
//DocumentYear
		if(passengerList.getDocumentYear() != null && passengerList.getDocumentYear().length() >0){
		status = ValidationUtils.checkValidationNumber(passengerList.getDocumentYear(), logMessageValidation, "52", "DocumentYear", "03", maLoi5, 0, 9, 0, status);
		}
//UserCreated
		status = ValidationUtils.checkValidation(passengerList.getUserCreated(), logMessageValidation, "52", "UserCreated", "04", maLoi2, 14, status);
		
//IsArrival
		status = ValidationUtils.checkValidationNumber(passengerList.getIsArrival(), logMessageValidation, "52", "IsArrival", "05", maLoi1, 0, 1, 0, status);
		
//NameOfShip
		status = ValidationUtils.checkValidation(passengerList.getNameOfShip(), logMessageValidation, "52", "NameOfShip", "06", maLoi2, 100, status);
		
//PortOfArrivalCode danh muc
//		status = ValidationUtils.checkValidationDanhMuc(passengerList.getPortOfArrivalCode(), logMessageValidation, "52", "PortOfArrivalCode", "07", maLoi6, 5, 9, status);
		
//AnchorageCode khong co validator
		
//DateOfArrival
//		status = ValidationUtils.checkValidation(passengerList.getDateOfArrival(), logMessageValidation, "52", "DateOfArrival", "09", maLoi3, 100, status);
		
//IMONumber
//		status = ValidationUtils.checkValidation(passengerList.getIMONumber(), logMessageValidation, "52", "IMONumber", "10", maLoi2, 7, status);
		
//CallSign
//		status = ValidationUtils.checkValidation(passengerList.getCallSign(), logMessageValidation, "52", "CallSign", "11", maLoi2, 9, status);
		
//VoyageNumber
//		status = ValidationUtils.checkValidation(passengerList.getVoyageNumber(), logMessageValidation, "52", "VoyageNumber", "12", maLoi2, 20, status);
		
//FlagStateOfShip danh muc		
//		status = ValidationUtils.checkValidationDanhMuc(passengerList.getFlagStateOfShip(), logMessageValidation, "52", "FlagStateOfShip", "13", maLoi6, 2, 11, status);
		
//SignPlace
		if(passengerList.getSignPlace() != null && passengerList.getSignPlace().length() >0){
		status = ValidationUtils.checkValidation(passengerList.getSignPlace(), logMessageValidation, "52", "SignPlace", "27", maLoi4, 50, status);
		}
//SignDate
		if (passengerList.getSignDate() != null && !FormatData.isThisDateValid(passengerList.getSignDate())) {
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N52728: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//MasterSigned
		if(passengerList.getMasterSigned() != null && passengerList.getMasterSigned().length() >0){
		status = ValidationUtils.checkValidationNumber(passengerList.getMasterSigned(), logMessageValidation, "52", "MasterSigned", "29", maLoi5, 0, 9, 0, status);
		}
		return status;
	}
	
	private boolean validationlist(Passenger passenger, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		boolean status = true;
		int [] maLoi1={1,3,4};
		int [] maLoi2={1,5,6};
		int [] maLoi3={1,7};
		int [] maLoi4={5,6};
		int [] maLoi6={1,2,5,6};
//PassengerCode
		status = ValidationUtils.checkValidation(passenger.getPassengerCode(), logMessageValidation, "52", "PassengerCode", "16", maLoi2, 12, status);

//FamilyName
		if(passenger.getFamilyName() != null && passenger.getFamilyName().length() >0){
		status = ValidationUtils.checkValidation(passenger.getFamilyName(), logMessageValidation, "52", "FamilyName", "17", maLoi4, 50, status);
		}
//GivenName
		status = ValidationUtils.checkValidation(passenger.getGivenName(), logMessageValidation, "52", "GivenName", "18", maLoi2, 50, status);
		
//Nationality danh muc
//		status = ValidationUtils.checkValidationDanhMuc(passenger.getNationality(), logMessageValidation, "52", "Nationality", "19", maLoi6, 2, 11, status);

//BirthDay
		status = ValidationUtils.checkValidation(passenger.getBirthDay(), logMessageValidation, "52", "DateOfBirth", "20", maLoi3, 100, status);
		
//BirthPlace
		status = ValidationUtils.checkValidation(passenger.getBirthPlace(), logMessageValidation, "52", "PlaceOfBirth", "21", maLoi2, 200, status);
		
//TypeOfIdentity danh muc
		status = ValidationUtils.checkValidationDanhMuc(passenger.getTypeOfIdentity(), logMessageValidation, "52", "TypeOfIdentity", "22", maLoi6, 2, 6, status);

//SerialNumberOfIdentity
		status = ValidationUtils.checkValidation(passenger.getSerialNumberOfIdentity(), logMessageValidation, "52", "PassportNumber", "23", maLoi2, 20, status);

//PortOfEmbarkation danh muc
//		status = ValidationUtils.checkValidationDanhMuc(passenger.getPortOfEmbarkation(), logMessageValidation, "52", "PortOfEmbarkation", "24", maLoi6, 5, 9, status);

//PortOfDisembarkation danh muc
//		status = ValidationUtils.checkValidationDanhMuc(passenger.getPortOfDisembarkation(), logMessageValidation, "52", "PortOfDisembarkation", "25", maLoi6, 5, 9, status);

//IsTransit
//		status = ValidationUtils.checkValidationNumber(passenger.getIsTransit(), logMessageValidation, "52", "DocumentName", "26", maLoi1, 0, 1, 0, status);
		return status;
	}
}

