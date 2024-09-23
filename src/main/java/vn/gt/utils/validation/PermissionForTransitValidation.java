/**
 * 
 */
package vn.gt.utils.validation;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.nsw.Header;
import vn.nsw.model.PermissionForTransit;


/**
 * @author win_64
 *
 */
public class PermissionForTransitValidation {

	public boolean validation(PermissionForTransit permissionForTransit, Header header, String requestDirection) throws Exception {
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
//NumberPermissionForTransbit
		status = ValidationUtils.checkValidation(permissionForTransit.getNumberPermissionForTransit(), logMessageValidation, "90", "NumberPermissionForTransit", "02", maLoi2, 50, status);
		
//DocumentName
		status = ValidationUtils.checkValidationNumber(permissionForTransit.getDocumentName(), logMessageValidation, "90", "DocumentName", "03", maLoi1, 0, 9, 0, status);
		 
//DocumentYear
		status = ValidationUtils.checkValidationNumber(permissionForTransit.getDocumentYear(), logMessageValidation, "90", "DocumentYear", "04", maLoi1, 0, 4, 0, status);
		
//PortofAuthority danh muc
		status = ValidationUtils.checkValidationDanhMuc(permissionForTransit.getPortofAuthority(), logMessageValidation, "90", "PortofAuthority", "05", maLoi6, 3, ValidationUtils.DM_MARITIME, status);

//NameOfShip
		status = ValidationUtils.checkValidation(permissionForTransit.getNameOfShip(), logMessageValidation, "90", "NameOfShip", "06", maLoi2, 100, status);

//FlagStateOfShip danh muc
		status = ValidationUtils.checkValidationDanhMuc(permissionForTransit.getFlagStateOfShip(), logMessageValidation, "90", "FlagStateOfShip", "07", maLoi6, 2, 11, status);
		
//GT
		status = ValidationUtils.checkValidationNumber(permissionForTransit.getGT(), logMessageValidation, "90", "GT", "08", maLoi1, 1, 6, 2, status);
		
//NumberOfCrews
		status = ValidationUtils.checkValidationNumber(permissionForTransit.getNumberOfCrews(), logMessageValidation, "90", "NumberOfCrews", "09", maLoi1, 0, 999999999, 0, status);

//NumberOfPassengers
		status = ValidationUtils.checkValidationNumber(permissionForTransit.getNumberOfPassengers(), logMessageValidation, "90", "NumberOfPassengers", "10", maLoi1, 0, 999999999, 0, status);

		
//CallSign
		status = ValidationUtils.checkValidation(permissionForTransit.getCallSign(), logMessageValidation, "90", "CallSign", "11", maLoi2, 9, status);
		
//NameOfMaster
		status = ValidationUtils.checkValidation(permissionForTransit.getNameOfMaster(), logMessageValidation, "90", "NameOfMaster", "12", maLoi2, 100, status);
		
//TransitCargo
		status = ValidationUtils.checkValidation(permissionForTransit.getTransitCargo(), logMessageValidation, "90", "TransitCargo", "13", maLoi2, 50, status);

//VolumeCargo
		status = ValidationUtils.checkValidationNumber(permissionForTransit.getVolumeCargo(), logMessageValidation, "90", "VolumeCargo", "14", maLoi1, 1, 18, 2, status);
		
//CargoUnit
		status = ValidationUtils.checkValidation(permissionForTransit.getCargoUnit(), logMessageValidation, "90", "CargoUnit", "15", maLoi2, 50, status);

//PermittedTransitFrom
		status = ValidationUtils.checkValidationDanhMuc(permissionForTransit.getPermittedTransitFrom(), logMessageValidation, "90", "PermittedTransitFrom", "16", maLoi6, 3, ValidationUtils.DM_PORT_REGION, status);

//PermittedTransitTo
		status = ValidationUtils.checkValidationDanhMuc(permissionForTransit.getPermittedTransitTo(), logMessageValidation, "90", "PermittedTransitTo", "17", maLoi6, 2, ValidationUtils.DM_STATE, status);

//TimeOfDeparture
		status = ValidationUtils.checkValidation(permissionForTransit.getTimeOfDeparture(), logMessageValidation, "90", "TimeOfDeparture", "18", maLoi3, 100, status);

//ValidUntil
		status = ValidationUtils.checkValidation(permissionForTransit.getValidUntil(), logMessageValidation, "90", "ValidUntil", "19", maLoi3, 100, status);

//DateOfSign
		status = ValidationUtils.checkValidation(permissionForTransit.getDateOfSign(), logMessageValidation, "90", "DateOfSign", "20", maLoi3, 100, status);

//UserCreated
		status = ValidationUtils.checkValidation(permissionForTransit.getUserCreated(), logMessageValidation, "90", "UserCreated", "21", maLoi2, 14, status);

//DirectorOfMaritimeAdministration
		status = ValidationUtils.checkValidation(permissionForTransit.getDirectorOfMaritimeAdministration(), logMessageValidation, "90", "DirectorOfMaritimeAdministration", "22", maLoi2, 100, status);

//CreatedDate
		status = ValidationUtils.checkValidation(permissionForTransit.getCreatedDate(), logMessageValidation, "90", "CreatedDate", "23", maLoi3, 100, status);

//Representative
		status = ValidationUtils.checkValidation(permissionForTransit.getRepresentative(), logMessageValidation, "90", "Representative", "24", maLoi2, 100, status);
		return status;
	}
}

