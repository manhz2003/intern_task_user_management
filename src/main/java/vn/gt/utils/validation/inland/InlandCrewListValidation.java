/**
 * 
 */
package vn.gt.utils.validation.inland;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.inland.InlandCrewLists;
import vn.nsw.model.inland.InlandCrewLists.CrewList.Crew;


/**
 * @author win_64
 *
 */
public class InlandCrewListValidation {

	public boolean validation(InlandCrewLists crewList, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
		int [] maLoi1={1,3,4};
		int [] maLoi2={1,5,6};
	//	int [] maLoi3={1,7};
		int [] maLoi4={5,6};
		int [] maLoi5={3,4};
		int [] maLoi6={1,2,5,6};
//DocumentName
		status = ValidationUtils.checkValidationNumber(crewList.getDocumentName(), logMessageValidation, "51", "DocumentName", "02", maLoi1, 0, 9, 0, status);
		 
//DocumentYear
		status = ValidationUtils.checkValidationNumber(crewList.getDocumentYear(), logMessageValidation, "51", "DocumentYear", "03", maLoi1, 0, 9, 0, status);
		
//UserCreated
		status = ValidationUtils.checkValidation(crewList.getUserCreated(), logMessageValidation, "51", "UserCreated", "04", maLoi2, 14, status);
		
//IsArrival
		status = ValidationUtils.checkValidationNumber(crewList.getIsArrival(), logMessageValidation, "51", "IsArrival", "05", maLoi1, 0, 1, 0, status);
		
//NameOfShip
		status = ValidationUtils.checkValidation(crewList.getNameOfShip(), logMessageValidation, "51", "NameOfShip", "06", maLoi2, 100, status);
		
//IMONumber
//		status = ValidationUtils.checkValidation(crewList.getIMONumber(), logMessageValidation, "51", "IMONumber", "07", maLoi2, 7, status);
		
//CallSign
//		status = ValidationUtils.checkValidation(crewList.getCallSign(), logMessageValidation, "51", "CallSign", "08", maLoi2, 9, status);
		
//VoyageNumber
//		status = ValidationUtils.checkValidation(crewList.getVoyageNumber(), logMessageValidation, "51", "VoyageNumber", "09", maLoi2, 20, status);
		
//PortOfArrivalCode danh muc
//		status = ValidationUtils.checkValidationDanhMuc(crewList.getPortOfArrivalCode(), logMessageValidation, "51", "PortOfArrivalCode", "10", maLoi6, 5, 9, status);

//DateOfArrival
//		status = ValidationUtils.checkValidation(crewList.getDateOfArrival(), logMessageValidation, "51", "DateOfArrival", "11", maLoi3, 100, status);

//FlagStateOfShip danh muc		
//		status = ValidationUtils.checkValidationDanhMuc(crewList.getFlagStateOfShip(), logMessageValidation, "51", "FlagStateOfShip", "12", maLoi6, 2, 11, status);

//LastPortOfCallCode danh muc
//		status = ValidationUtils.checkValidationDanhMuc(crewList.getLastPortOfCallCode(), logMessageValidation, "51", "LastPortOfCallCode", "10", maLoi6, 5, 9, status);

//SignPlace
		if(crewList.getSignPlace() != null && crewList.getSignPlace().length() > 0){
		status = ValidationUtils.checkValidation(crewList.getSignPlace(), logMessageValidation, "51", "SignPlace", "25", maLoi4, 50, status);
		}
//SignDate
		if (crewList.getSignDate() != null && !FormatData.isThisDateValid(crewList.getSignDate())) {
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N51726: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//MasterSigned
		if(crewList.getMasterSigned() != null && crewList.getMasterSigned().length() >0){
		status = ValidationUtils.checkValidationNumber(crewList.getMasterSigned(), logMessageValidation, "51", "MasterSigned", "27", maLoi5, 0, 9, 0, status);
		}
		return status;
	}
	
	private boolean validationlist(Crew crew, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
		int [] maLoi2={1,5,6};
//		int [] maLoi3={1,7};
		int [] maLoi4={5,6};
		int [] maLoi6={1,2,5,6};
//CrewCode
		status = ValidationUtils.checkValidation(crew.getCrewCode(), logMessageValidation, "51", "CrewCode", "16", maLoi2, 5, status);

//FamilyName
		if(crew.getFamilyName() != null && crew.getFamilyName().length() >0){
		status = ValidationUtils.checkValidation(crew.getFamilyName(), logMessageValidation, "51", "FamilyName", "17", maLoi4, 50, status);
		}
//GivenName
		status = ValidationUtils.checkValidation(crew.getGivenName(), logMessageValidation, "51", "GivenName", "18", maLoi2, 50, status);
		
//RankCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(crew.getRankCode(), logMessageValidation, "51", "RankCode", "19", maLoi6, 12, 13, status);
		
//Nationality danh muc
//		status = ValidationUtils.checkValidationDanhMuc(crew.getNationality(), logMessageValidation, "51", "Nationality", "20", maLoi6, 2, 11, status);

//DateOfBirth
//		status = ValidationUtils.checkValidation(crew.getDateOfBirth(), logMessageValidation, "51", "DateOfBirth", "21", maLoi3, 100, status);
		
//PlaceOfBirth
//		status = ValidationUtils.checkValidation(crew.getPlaceOfBirth(), logMessageValidation, "51", "PlaceOfBirth", "22", maLoi2, 200, status);
		
//PassportNumber
//		status = ValidationUtils.checkValidation(crew.getPassportNumber(), logMessageValidation, "51", "PassportNumber", "23", maLoi2, 20, status);

//PassportTypeCode danh muc
//		status = ValidationUtils.checkValidationDanhMuc(crew.getPassportTypeCode(), logMessageValidation, "51", "PassportTypeCode", "24", maLoi6, 12, 6, status);

		return status;
	}
}

