/**
 * 
 */
package vn.gt.utils.validation.inland;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.nsw.Header;
import vn.nsw.model.inland.InlandShiftingOrder;


/**
 * @author win_64
 *
 */
public class InlandShiftingOrderValidation {

	public boolean validation(InlandShiftingOrder shiftingOrder, Header header, String requestDirection) throws Exception {
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
		//NumberShiftingOrder
//		status = ValidationUtils.checkValidation(shiftingOrder.getNumberShiftingOrder(), logMessageValidation, "70", "NumberShiftingOrder", "02", maLoi2, 30, status);
		
		//DocumentName
		status = ValidationUtils.checkValidationNumber(shiftingOrder.getDocumentName(), logMessageValidation, "70", "DocumentName", "03", maLoi1, 0, 9, 0, status);
		 
		//DocumentYear
		status = ValidationUtils.checkValidationNumber(shiftingOrder.getDocumentYear(), logMessageValidation, "70", "DocumentYear", "04", maLoi1, 0, 4, 0, status);
		
		//PortofAuthority danh muc
		status = ValidationUtils.checkValidationDanhMuc(shiftingOrder.getPortofAuthority(), logMessageValidation, "70", "PortofAuthority", "05", maLoi6, 3, ValidationUtils.DM_MARITIME, status);

		//NameOfShip
		status = ValidationUtils.checkValidation(shiftingOrder.getNameOfShip(), logMessageValidation, "70", "NameOfShip", "06", maLoi2, 100, status);

		//FlagStateOfShip danh muc
//		status = ValidationUtils.checkValidationDanhMuc(shiftingOrder.getFlagStateOfShip(), logMessageValidation, "70", "FlagStateOfShip", "07", maLoi6, 2, 11, status);

		//AnchoringPortCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(shiftingOrder.getAnchoringPortCode(), logMessageValidation, "70", "AnchoringPortCode", "08", maLoi6, 5, 9, status);

		//AnchoringPortWharfCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(shiftingOrder.getAnchoringPortWharfCode(), logMessageValidation, "70", "AnchoringPortWharfCode", "09", maLoi6, 5, 4, status);

		//PortHarbourCode
		status = ValidationUtils.checkValidationDanhMuc(shiftingOrder.getPortHarbourCode(), logMessageValidation, "70", "PortHarbourCode", "09a", maLoi6, 12, ValidationUtils.DM_POST_HARBOUR, status);
		
		//ShiftingPortWharfCode danh muc
	//	status = ValidationUtils.checkValidationDanhMuc(shiftingOrder.getShiftingPortWharfCode(), logMessageValidation, "70", "ShiftingPortWharfCode", "10", maLoi6, 5, 4, status);

		//ShiftingDate
		status = ValidationUtils.checkValidation(shiftingOrder.getShiftingDate(), logMessageValidation, "70", "ShiftingDate", "11", maLoi3, 100, status);
		
		//ReasonToShift
//		status = ValidationUtils.checkValidation(shiftingOrder.getReasonToShift(), logMessageValidation, "70", "ReasonToShift", "12", maLoi2, 250, status);

		//IssueDate	
		status = ValidationUtils.checkValidation(shiftingOrder.getIssueDate(), logMessageValidation, "70", "IssueDate", "13", maLoi3, 100, status);

		//DirectorOfMaritimeAdministration
		status = ValidationUtils.checkValidation(shiftingOrder.getDirectorOfMaritimeAdministration(), logMessageValidation, "70", "DirectorOfMaritimeAdministration", "14", maLoi2, 100, status);

		//CertificateNo
		status = ValidationUtils.checkValidation(shiftingOrder.getCertificateNo(), logMessageValidation, "70", "CertificateNo", "15", maLoi2, 100, status);
		
		//Representative
		status = ValidationUtils.checkValidation(shiftingOrder.getRepresentative(), logMessageValidation, "70", "Representative", "16", maLoi2, 100, status);
		
		return status;
	}
}

