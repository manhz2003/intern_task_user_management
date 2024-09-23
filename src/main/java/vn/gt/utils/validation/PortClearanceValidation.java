/**
 * 
 */
package vn.gt.utils.validation;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.nsw.Header;
import vn.nsw.model.PortClearance;


/**
 * @author win_64
 *
 */
public class PortClearanceValidation {

	public boolean validation(PortClearance portClearance, Header header, String requestDirection) throws Exception {
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
		int	[] maLoi6={1,2,5,6};
		int [] maLois7 = {2,5,6};
//NumberPortClearance
		status = ValidationUtils.checkValidation(portClearance.getNumberPortClearance(), logMessageValidation, "60", "NumberPortClearance", "02", maLoi2, 30, status);
		
//DocumentName
		status = ValidationUtils.checkValidationNumber(portClearance.getDocumentName(), logMessageValidation, "60", "DocumentName", "03", maLoi1, 0, 9, 0, status);
		 
//DocumentYear
		status = ValidationUtils.checkValidationNumber(portClearance.getDocumentYear(), logMessageValidation, "60", "DocumentYear", "04", maLoi1, 0, 4, 0, status);
		
//PortofAuthority danh muc
		status = ValidationUtils.checkValidationDanhMuc(portClearance.getPortofAuthority(), logMessageValidation, "60", "PortofAuthority", "05", maLoi6, 3, ValidationUtils.DM_MARITIME, status);

//NameOfShip
		status = ValidationUtils.checkValidation(portClearance.getNameOfShip(), logMessageValidation, "60", "NameOfShip", "06", maLoi2, 100, status);

//FlagStateOfShip danh muc
		status = ValidationUtils.checkValidationDanhMuc(portClearance.getFlagStateOfShip(), logMessageValidation, "60", "FlagStateOfShip", "07", maLoi6, 3, 11, status);
		
//NumberOfCrews
		status = ValidationUtils.checkValidationNumber(portClearance.getNumberOfCrews(), logMessageValidation, "60", "NumberOfCrews", "08", maLoi1, 0, 999999999, 0, status);

//NumberOfPassengers
		status = ValidationUtils.checkValidationNumber(portClearance.getNumberOfPassengers(), logMessageValidation, "60", "NumberOfPassengers", "09", maLoi1, 0, 999999999, 0, status);

		
//CallSign
		status = ValidationUtils.checkValidation(portClearance.getCallSign(), logMessageValidation, "60", "CallSign", "10", maLoi2, 9, status);
		
//NameOfMaster
		status = ValidationUtils.checkValidation(portClearance.getNameOfMaster(), logMessageValidation, "60", "NameOfMaster", "11", maLoi2, 100, status);
		
//Cargo danh muc
		status = ValidationUtils.checkValidationDanhMuc(portClearance.getCargo(), logMessageValidation, "60", "Cargo", "12", maLoi6, 50, 12, status);

//VolumeCargo
		status = ValidationUtils.checkValidationNumber(portClearance.getVolumeCargo(), logMessageValidation, "60", "VolumeCargo", "13", maLoi1, 1, 18, 2, status);
		
//CargoUnit
		status = ValidationUtils.checkValidationDanhMuc(portClearance.getCargoUnit(), logMessageValidation, "60", "CargoUnit", "14", maLois7, 50, ValidationUtils.DM_UNIT_GENERAL, status);

//TransitCargo
		status = ValidationUtils.checkValidation(portClearance.getTransitCargo(), logMessageValidation, "60", "TransitCargo", "15", maLoi2, 50, status);

//VolumeTransitCargo
		status = ValidationUtils.checkValidationNumber(portClearance.getVolumeTransitCargo(), logMessageValidation, "60", "VolumeTransitCargo", "16", maLoi1, 1, 18, 2, status);
		
//TransitCargoUnit
		status = ValidationUtils.checkValidationDanhMuc(portClearance.getTransitCargoUnit(), logMessageValidation, "60", "TransitCargoUnit", "17", maLois7, 50, ValidationUtils.DM_UNIT_GENERAL, status);

//TimeOfDeparture
		status = ValidationUtils.checkValidation(portClearance.getTimeOfDeparture(), logMessageValidation, "60", "TimeOfDeparture", "18", maLoi3, 100, status);

//NextPortOfCallCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(portClearance.getNextPortOfCallCode(), logMessageValidation, "60", "NextPortOfCallCode", "19", maLoi6, 12, 9, status);
		
//ValidUntil
		status = ValidationUtils.checkValidation(portClearance.getValidUntil(), logMessageValidation, "60", "ValidUntil", "20", maLoi3, 100, status);

//IssueDate
		status = ValidationUtils.checkValidation(portClearance.getIssueDate(), logMessageValidation, "60", "IssueDate", "21", maLoi3, 100, status);

//DirectorOfMaritimeAdministration
		status = ValidationUtils.checkValidation(portClearance.getDirectorOfMaritimeAdministration(), logMessageValidation, "60", "DirectorOfMaritimeAdministration", "22", maLoi2, 100, status);

//CertificateNo
		status = ValidationUtils.checkValidation(portClearance.getCertificateNo(), logMessageValidation, "60", "CertificateNo", "23", maLoi2, 100, status);
//Representative
		status = ValidationUtils.checkValidation(portClearance.getRepresentative(), logMessageValidation, "60", "Representative", "24", maLoi2, 100, status);
		return status;
	}
}

