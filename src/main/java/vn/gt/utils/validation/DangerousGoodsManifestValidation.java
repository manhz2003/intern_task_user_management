/**
 * 
 */
package vn.gt.utils.validation;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.DangerousGoodsManifest;
import vn.nsw.model.DangerousGoodsManifest.ListDangerousGoods.DangerousGoodsItem;


/**
 * @author win_64
 *
 */
public class DangerousGoodsManifestValidation {

	public boolean validation(DangerousGoodsManifest dangerousGoodsManifest, Header header, String requestDirection) throws Exception {
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
		status = ValidationUtils.checkValidationNumber(dangerousGoodsManifest.getDocumentName(), logMessageValidation, "53", "DocumentName", "02", maLoi1, 0, 9, 0, status);
		 
//DocumentYear
		status = ValidationUtils.checkValidationNumber(dangerousGoodsManifest.getDocumentYear(), logMessageValidation, "53", "DocumentYear", "03", maLoi1, 0, 9, 0, status);
		
//UserCreated
		status = ValidationUtils.checkValidation(dangerousGoodsManifest.getUserCreated(), logMessageValidation, "53", "UserCreated", "04", maLoi2, 14, status);
		
//NameOfShip
		status = ValidationUtils.checkValidation(dangerousGoodsManifest.getNameOfShip(), logMessageValidation, "53", "NameOfShip", "05", maLoi2, 100, status);
		
//FlagStateOfShip danh muc
		status = ValidationUtils.checkValidationDanhMuc(dangerousGoodsManifest.getFlagStateOfShip(), logMessageValidation, "53", "FlagStateOfShip", "06", maLoi6, 2, 11, status);

//MasterName
		status = ValidationUtils.checkValidation(dangerousGoodsManifest.getMasterName(), logMessageValidation, "53", "MasterName", "07", maLoi2, 50, status);

//IMONumber	
		if(dangerousGoodsManifest.getIMONumber() != null && dangerousGoodsManifest.getIMONumber().length() >0){
		status = ValidationUtils.checkValidation(dangerousGoodsManifest.getIMONumber(), logMessageValidation, "53", "IMONumber", "08", maLoi4, 7, status);
		}
//CallSign
		status = ValidationUtils.checkValidation(dangerousGoodsManifest.getCallsign(), logMessageValidation, "53", "CallSign", "09", maLoi2, 9, status);
		
//VoyageNumber
		status = ValidationUtils.checkValidation(dangerousGoodsManifest.getVoyageNumber(), logMessageValidation, "53", "VoyageNumber", "10", maLoi2, 20, status);
		
//PortOfLoadingCode danh muc		
		status = ValidationUtils.checkValidationDanhMuc(dangerousGoodsManifest.getPortOfLoadingCode(), logMessageValidation, "53", "PortOfLoadingCode", "11", maLoi6, 5, 9, status);

//PortOfDischargeCode danh muc	
		status = ValidationUtils.checkValidationDanhMuc(dangerousGoodsManifest.getPortOfDischargeCode(), logMessageValidation, "53", "PortOfDischargeCode", "11", maLoi6, 5, 9, status);

//ShippingAgent
		status = ValidationUtils.checkValidation(dangerousGoodsManifest.getShippingAgent(), logMessageValidation, "53", "ShippingAgent", "13", maLoi2, 250, status);
		
//AdditionalRemark
		if(dangerousGoodsManifest.getAdditionalRemark() != null && dangerousGoodsManifest.getAdditionalRemark().length() >0){
		status = ValidationUtils.checkValidation(dangerousGoodsManifest.getAdditionalRemark(), logMessageValidation, "53", "AdditionalRemark", "14", maLoi4, 500, status);
		}
//SignPlace
		if(dangerousGoodsManifest.getSignPlace() != null && dangerousGoodsManifest.getSignPlace().length() >0){
		status = ValidationUtils.checkValidation(dangerousGoodsManifest.getSignPlace(), logMessageValidation, "53", "SignPlace", "15", maLoi4, 50, status);
		}
//SignDate
		if (dangerousGoodsManifest.getSignDate() != null && !FormatData.isThisDateValid(dangerousGoodsManifest.getSignDate())) {
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N53716: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//MasterSigned
		if(dangerousGoodsManifest.getMasterSigned() != null && dangerousGoodsManifest.getMasterSigned().length() >0){
		status = ValidationUtils.checkValidationNumber(dangerousGoodsManifest.getMasterSigned(), logMessageValidation, "53", "MasterSigned", "17", maLoi5, 0, 9, 0, status);
		}
		return status;
	}
	
	private boolean validationlist(DangerousGoodsItem dangerousGoodsItem, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		boolean status = true;
		int [] maLoi1={1,3,4};
		int [] maLoi2={1,5,6};
		int [] maLoi6={1,2,5,6};
//DangerousGoodItemCode
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getDangerousGoodItemCode(), logMessageValidation, "53", "DangerousGoodItemCode", "20", maLoi2, 12, status);

//RefNumber
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getRefNumber(), logMessageValidation, "53", "RefNumber", "21", maLoi2, 200, status);
		
//MarksContainer
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getMarksContainer(), logMessageValidation, "53", "MarksContainer", "22", maLoi2, 50, status);
		
//NumberContainer
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getNumberContainer(), logMessageValidation, "53", "NumberContainer", "23", maLoi2, 20, status);
		
//NumberOfPackage
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getNumberOfPackage(), logMessageValidation, "53", "NumberOfPackage", "24", maLoi2, 20, status);
		
//KindOfPackages danh muc
		status = ValidationUtils.checkValidationDanhMuc(dangerousGoodsItem.getKindOfPackages(), logMessageValidation, "53", "KindOfPackages", "25", maLoi6, 200, 15, status);

//ProperShippingName
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getProperShippingName(), logMessageValidation, "53", "ProperShippingName", "26", maLoi2, 100, status);
		
//GoodClass danh muc
		status = ValidationUtils.checkValidationDanhMuc(dangerousGoodsItem.getGoodClass(), logMessageValidation, "53", "GoodClass", "27", maLoi6, 20, 14, status);

//UNNumber
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getUNNumber(), logMessageValidation, "53", "UNNumber", "28", maLoi2, 20, status);
		
//PackingGroup
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getPackingGroup(), logMessageValidation, "53", "PackingGroup", "29", maLoi2, 20, status);
		
//SubsidiaryRisk
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getSubsidiaryRisk(), logMessageValidation, "53", "SubsidiaryRisk", "30", maLoi2, 20, status);
		
//FlashPoint
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getFlashPoint(), logMessageValidation, "53", "FlashPoint", "31", maLoi2, 20, status);
		
//MarinePollutant
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getMarinePollutant(), logMessageValidation, "53", "MarinePollutant", "32", maLoi2, 200, status);
		
//GrossWeight
		status = ValidationUtils.checkValidationNumber(dangerousGoodsItem.getGrossWeight(), logMessageValidation, "53", "GrossWeight", "33", maLoi1, 0, 18, 0, status);
		
//Ems
		status = ValidationUtils.checkValidation(dangerousGoodsItem.getEms(), logMessageValidation, "53", "Ems", "34", maLoi2, 500, status);
		return status;
	}
}

