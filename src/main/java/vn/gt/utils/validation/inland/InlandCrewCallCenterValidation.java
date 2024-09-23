/**
 * 
 */
package vn.gt.utils.validation.inland;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.inland.InlandCrewCallCenter;
import vn.nsw.model.inland.InlandCrewCallCenter.Crew;


/**
 * @author win_64
 *
 */
public class InlandCrewCallCenterValidation {

	public boolean validation(InlandCrewCallCenter crewList, Header header, String requestDirection) throws Exception {
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
		status = ValidationUtils.checkValidation(crewList.getPortName(), logMessageValidation, "66", "PortName", "02", maLoi2, 100, status);
		 
//DocumentYear
		status = ValidationUtils.checkValidation(crewList.getMaritimePortCode(), logMessageValidation, "66", "MaritimePortCode", "03", maLoi2, 3, status);
		
//UserCreated
		status = ValidationUtils.checkValidation(crewList.getUserCreated(), logMessageValidation, "66", "UserCreated", "04", maLoi2, 14, status);
		
//IsArrival
		status = ValidationUtils.checkValidationNumber(String.valueOf(crewList.getIsArrival()), logMessageValidation, "66", "IsArrival", "05", maLoi1, 0, 1, 0, status);
		
//NameOfShip
		status = ValidationUtils.checkValidation(crewList.getNameOfShip(), logMessageValidation, "66", "NameOfShip", "06", maLoi2, 100, status);
//CallSign		
		status = ValidationUtils.checkValidation(crewList.getCallSign(), logMessageValidation, "66", "CallSign", "07", maLoi2, 9, status);
//NameOfMaster		
		status = ValidationUtils.checkValidation(crewList.getNameOfMaster(), logMessageValidation, "66", "NameOfMaster", "08", maLoi2, 100, status);
		
//CertificateNumber		
		status = ValidationUtils.checkValidation(crewList.getCertificateNumber(), logMessageValidation, "66", "CertificateNumber", "09", maLoi2, 100, status);
		
		if (crewList.getCrew().size() > 0)
		{
			for (Crew crew: crewList.getCrew())
			status = validationlist(crew, header, requestDirection);			
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
		status = ValidationUtils.checkValidation(crew.getCrewCode(), logMessageValidation, "66", "CrewCode", "16", maLoi2, 5, status);

//FullName
		if(crew.getFullName() != null && crew.getFullName().length() >0){
		status = ValidationUtils.checkValidation(crew.getFullName(), logMessageValidation, "66", "FullName", "17", maLoi2, 50, status);
		}
//CrewNumber
		if(crew.getFullName() != null && crew.getFullName().length() >0){
		status = ValidationUtils.checkValidation(crew.getCrewNumber(), logMessageValidation, "66", "CrewNumber", "18", maLoi2, 20, status);
		}

		return status;
	}
}

