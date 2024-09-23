/**
 * 
 */
package vn.gt.utils.validation;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.ShipsStoresDeclaration;
import vn.nsw.model.ShipsStoresDeclaration.ListShipsStores.ShipsStoreItem;


/**
 * @author win_64
 *
 */
public class ShipsStoresDeclarationValidation {

	public boolean validation(ShipsStoresDeclaration shipsStoresDeclaration, Header header, String requestDirection) throws Exception {
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
		status = ValidationUtils.checkValidationNumber(shipsStoresDeclaration.getDocumentName(), logMessageValidation, "54", "DocumentName", "02", maLoi1, 0, 9, 0, status);
		 
//DocumentYear
		if(shipsStoresDeclaration.getDocumentYear() != null && shipsStoresDeclaration.getDocumentYear().length() >0){
		status = ValidationUtils.checkValidationNumber(shipsStoresDeclaration.getDocumentYear(), logMessageValidation, "54", "DocumentYear", "03", maLoi5, 0, 9, 0, status);
		}
//UserCreated
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getUserCreated(), logMessageValidation, "54", "UserCreated", "04", maLoi2, 14, status);
		
//IsArrival
		status = ValidationUtils.checkValidationNumber(shipsStoresDeclaration.getIsArrival(), logMessageValidation, "54", "IsArrival", "05", maLoi1, 0, 1, 0, status);
		
//NameOfShip
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getNameOfShip(), logMessageValidation, "54", "NameOfShip", "06", maLoi2, 100, status);
		
//IMONumber
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getIMONumber(), logMessageValidation, "54", "IMONumber", "07", maLoi2, 7, status);
		
//CallSign
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getCallsign(), logMessageValidation, "54", "CallSign", "08", maLoi2, 9, status);
		
//VoyageNumber
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getVoyageNumber(), logMessageValidation, "54", "VoyageNumber", "09", maLoi2, 20, status);
//NationalityOfShip danh muc
		status = ValidationUtils.checkValidationDanhMuc(shipsStoresDeclaration.getNationalityOfShip(), logMessageValidation, "54", "NationalityOfShip", "10", maLoi6, 2, 11, status);

//PortOfArrivalCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(shipsStoresDeclaration.getPortOfArrivalCode(), logMessageValidation, "54", "PortOfArrivalCode", "11", maLoi6, 5, 9, status);

//AnchorageCode
		if(shipsStoresDeclaration.getAnchorageCode() != null && shipsStoresDeclaration.getAnchorageCode().length() >0){
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getAnchorageCode(), logMessageValidation, "54", "AnchorageCode", "12", maLoi4, 5, status);
		}
//DateOfArrival
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getDateOfArrival(), logMessageValidation, "54", "DateOfArrival", "13", maLoi3, 100, status);
		
//LastPortOfCallCode danh muc
		status = ValidationUtils.checkValidationDanhMuc(shipsStoresDeclaration.getLastPortOfCallCode(), logMessageValidation, "54", "LastPortOfCallCode", "14", maLoi6, 5, 9, status);

//NumberOfPersonsOnBoat
		status = ValidationUtils.checkValidationNumber(shipsStoresDeclaration.getNumberOfPersonsOnBoat(), logMessageValidation, "54", "NumberOfPersonsOnBoat", "15", maLoi1, 0, 9, 0, status);
		
//PeriodOfStay
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getPeriodOfStay(), logMessageValidation, "54", "PeriodOfStay", "16", maLoi2, 50, status);
		
//PeriodOfStayUnit
		if(shipsStoresDeclaration.getPeriodOfStayUnit() != null && shipsStoresDeclaration.getPeriodOfStayUnit().length() >0){
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getPeriodOfStayUnit(), logMessageValidation, "54", "PeriodOfStayUnit", "17", maLoi4, 4, status);
		}
//NameOfMaster
		if(shipsStoresDeclaration.getNameOfMaster() != null && shipsStoresDeclaration.getNameOfMaster().length() >0){
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getNameOfMaster(), logMessageValidation, "54", "NameOfMaster", "18", maLoi4, 100, status);
		}
//SignPlace
		if(shipsStoresDeclaration.getSignPlace() != null && shipsStoresDeclaration.getSignPlace().length() >0){
		status = ValidationUtils.checkValidation(shipsStoresDeclaration.getSignPlace(), logMessageValidation, "54", "SignPlace", "19", maLoi4, 50, status);
		}
//SignDate
		if (shipsStoresDeclaration.getSignDate() != null && !FormatData.isThisDateValid(shipsStoresDeclaration.getSignDate())) {
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N54728: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//MasterSigned
		if(shipsStoresDeclaration.getMasterSigned() != null && shipsStoresDeclaration.getMasterSigned().length() >0){
		status = ValidationUtils.checkValidationNumber(shipsStoresDeclaration.getMasterSigned(), logMessageValidation, "54", "MasterSigned", "21", maLoi5, 0, 9, 0, status);
		}
		return status;
	}
	
	private boolean validationlist(ShipsStoreItem shipsStoreItem, Header header, String requestDirection) throws Exception {
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
		
//ShipsStoreItemCode
		status = ValidationUtils.checkValidation(shipsStoreItem.getShipsStoreItemCode(), logMessageValidation, "54", "ShipsStoreItemCode", "24", maLoi2, 12, status);
		
//NameOfArticle
		status = ValidationUtils.checkValidation(shipsStoreItem.getNameOfArticle(), logMessageValidation, "54", "NameOfArticle", "25", maLoi2, 250, status);
		
//Quantity
		status = ValidationUtils.checkValidation(shipsStoreItem.getQuantity(), logMessageValidation, "54", "Quantity", "26", maLoi2, 20, status);
		
//QuantityUnit
		if(shipsStoreItem.getQuantityUnit() != null && shipsStoreItem.getQuantityUnit().length() >0){
		status = ValidationUtils.checkValidation(shipsStoreItem.getQuantityUnit(), logMessageValidation, "54", "QuantityUnit", "27", maLoi4, 4, status);
		}
//LocationOnBoat
		status = ValidationUtils.checkValidation(shipsStoreItem.getLocationOnBoat(), logMessageValidation, "54", "LocationOnBoat", "28", maLoi2, 250, status);
		
//UseInBoat
		status = ValidationUtils.checkValidationNumber(shipsStoreItem.getUseInBoat(), logMessageValidation, "54", "UseInBoat", "29", maLoi1, 0, 1, 0, status);
		return status;
	}
}

