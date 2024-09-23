/**
 * 
 */
package vn.gt.utils.validation;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.dao.danhmuc.service.DmGoodsLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPackageLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.utils.ActionUtils;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.CargoDeclaration;
import vn.nsw.model.CargoDeclaration.ListGoods.GoodItem;


/**
 * @author win_64
 *
 */
public class CargoDeclarationValidation {

	public boolean validation(CargoDeclaration cargoDeclaration, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
		
		//	DocumentName
		if (cargoDeclaration.getDocumentName() == null) {
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N20102: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsLong(cargoDeclaration.getDocumentName())) {
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N20302: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(cargoDeclaration.getDocumentName().length() >9){
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N20402: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
// DocumentYear	
		if (cargoDeclaration.getDocumentYear() == null) {
			status = false;
			logMessageValidation.setTagProperty("DocumentYear");
			logMessageValidation.setDataValidation("N20103: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsLong(cargoDeclaration.getDocumentYear())) {
			status = false;
			logMessageValidation.setTagProperty("DocumentYear");
			logMessageValidation.setDataValidation("N20303: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(cargoDeclaration.getDocumentYear().length() >9){
			status = false;
			logMessageValidation.setTagProperty("DocumentYear");
			logMessageValidation.setDataValidation("N20403: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
		//UserCreated
		if (cargoDeclaration.getUserCreated() == null) {
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N20104: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (cargoDeclaration.getUserCreated().length() > 14) {
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N20504: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(cargoDeclaration.getUserCreated())){
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N20604: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//isArrival
		if(cargoDeclaration.getIsArrival()==null){
			status = false;
			logMessageValidation.setTagProperty("isArrival");
			logMessageValidation.setDataValidation("N20105: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsLong(cargoDeclaration.getIsArrival())) {
			status = false;
			logMessageValidation.setTagProperty("isArrival");
			logMessageValidation.setDataValidation("N20305: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);	
		} else if(cargoDeclaration.getIsArrival().length()!= 1){
			status = false;
			logMessageValidation.setTagProperty("isArrival");
			logMessageValidation.setDataValidation("N20405: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//NameOfShip
		if (cargoDeclaration.getNameOfShip() == null) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N20106: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (cargoDeclaration.getNameOfShip().length() > 100) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N20506: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(cargoDeclaration.getNameOfShip())){
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N20606: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//IMONumber
		if(cargoDeclaration.getIMONumber()==null){
			status = false;
			logMessageValidation.setTagProperty("IMONumber");
			logMessageValidation.setDataValidation("N20107: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		else if (cargoDeclaration.getIMONumber().length() >7) {
			status = false;
			logMessageValidation.setTagProperty("IMONumber");
			logMessageValidation.setDataValidation("N20507: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsAValidSpecialCharacters(cargoDeclaration.getIMONumber())) {
			status = false;
			logMessageValidation.setTagProperty("IMONumber");
			logMessageValidation.setDataValidation("N20607: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		}
//CallSign
		if (cargoDeclaration.getCallsign() == null) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N20108: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (cargoDeclaration.getCallsign().length() > 9) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N20508: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(cargoDeclaration.getCallsign())){
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N20608: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//VoyageNumber
		if(cargoDeclaration.getVoyageNumber()==null){
			status = false;
			logMessageValidation.setTagProperty("VoyageNumber");
			logMessageValidation.setDataValidation("N20109: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (cargoDeclaration.getVoyageNumber().length() > 20) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N20509: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(cargoDeclaration.getVoyageNumber())){
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N20609: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//PortReport
		if(cargoDeclaration.getPortReport()==null){
			status = false;
			logMessageValidation.setTagProperty("PortReport");
			logMessageValidation.setDataValidation("N20110: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(cargoDeclaration.getPortReport() != null && cargoDeclaration.getPortReport().trim().length()>0 && DmPortLocalServiceUtil.getByPortCode(cargoDeclaration.getPortReport())==null){
			status = false;
			logMessageValidation.setTagProperty("PortReport");
			logMessageValidation.setDataValidation("N20210: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (cargoDeclaration.getPortReport().length() > 5) {
			status = false;
			logMessageValidation.setTagProperty("PortReport");
			logMessageValidation.setDataValidation("N20510: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(cargoDeclaration.getPortReport())){
			status = false;
			logMessageValidation.setTagProperty("PortReport");
			logMessageValidation.setDataValidation("N20610: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//FlagStateOfShip
		if (cargoDeclaration.getFlagStateOfShip() == null) {
			status = false;
			logMessageValidation.setTagProperty("FlagStateOfShip");
			logMessageValidation.setDataValidation("N20111: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (cargoDeclaration.getFlagStateOfShip() != null && cargoDeclaration.getFlagStateOfShip().trim().length() > 0 && DmStateLocalServiceUtil.getByStateCode(cargoDeclaration.getFlagStateOfShip()) == null) {
			status = false;
			logMessageValidation.setTagProperty("FlagStateOfShip");
			logMessageValidation.setDataValidation("N20211: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (cargoDeclaration.getFlagStateOfShip().length() > 2) {
			status = false;
			logMessageValidation.setTagProperty("FlagStateOfShip");
			logMessageValidation.setDataValidation("N20511: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(cargoDeclaration.getFlagStateOfShip())){
			status = false;
			logMessageValidation.setTagProperty("FlagStateOfShip");
			logMessageValidation.setDataValidation("N20611: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//NameOfMaster
		if (cargoDeclaration.getNameOfMaster() == null) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N20112: " + ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (cargoDeclaration.getNameOfMaster().length() > 100) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N20512: " + ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsAValidSpecialCharacters(cargoDeclaration.getNameOfMaster())) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N20612: " + ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//PortOfLoading
		if (cargoDeclaration.getPortOfLoading() != null && cargoDeclaration.getPortOfLoading().length() > 0) {
		 if(cargoDeclaration.getPortOfLoading() != null && cargoDeclaration.getPortOfLoading().trim().length() >0 && DmPortLocalServiceUtil.getByPortCode(cargoDeclaration.getPortOfLoading())==null){
			status = false;
			logMessageValidation.setTagProperty("PortOfLoading");
			logMessageValidation.setDataValidation("N20213: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(cargoDeclaration.getPortOfLoading().length()>5){
			status = false;
			logMessageValidation.setTagProperty("PortOfLoading");
			logMessageValidation.setDataValidation("N20513: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(cargoDeclaration.getPortOfLoading())) {
			status = false;
			logMessageValidation.setTagProperty("PortOfLoading");
			logMessageValidation.setDataValidation("N20613: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		}
		return status;
	}
	
	
	
	private boolean validationlist(GoodItem goodItem, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
//BillOfLadingNo
		if (goodItem.getBillOfLadingNo() == null) {
			status = false;
			logMessageValidation.setTagProperty("BillOfLadingNo");
			logMessageValidation.setDataValidation("N20116: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(goodItem.getBillOfLadingNo().length()>5){
			status = false;
			logMessageValidation.setTagProperty("BillOfLadingNo");
			logMessageValidation.setDataValidation("N20516: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(goodItem.getBillOfLadingNo())) {
			status = false;
			logMessageValidation.setTagProperty("BillOfLadingNo");
			logMessageValidation.setDataValidation("N20616: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		}
//GoodItemCode
		if (goodItem.getGoodItemCode() != null && goodItem.getGoodItemCode().length() > 0) {
		if(goodItem.getGoodItemCode()!= null && goodItem.getGoodItemCode().trim().length() >0 && DmGoodsLocalServiceUtil.getByGoodsItemCode(goodItem.getGoodItemCode()) == null){
			status = false;
			logMessageValidation.setTagProperty("GoodItemCode");
			logMessageValidation.setDataValidation("N20217: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(goodItem.getBillOfLadingNo().length()>12){
			status = false;
			logMessageValidation.setTagProperty("GoodItemCode");
			logMessageValidation.setDataValidation("N20517: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(goodItem.getBillOfLadingNo())) {
			status = false;
			logMessageValidation.setTagProperty("GoodItemCode");
			logMessageValidation.setDataValidation("N20617: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		}
//GoodItemDescription
		if (goodItem.getGoodItemDescription() == null) {
			status = false;
			logMessageValidation.setTagProperty("GoodItemDescription");
			logMessageValidation.setDataValidation("N20117a: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(goodItem.getGoodItemDescription().length()>4000){
			status = false;
			logMessageValidation.setTagProperty("GoodItemDescription");
			logMessageValidation.setDataValidation("N20517a: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(goodItem.getGoodItemDescription())) {
			status = false;
			logMessageValidation.setTagProperty("GoodItemDescription");
			logMessageValidation.setDataValidation("N20617a: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//NumberOfPackage
		if (goodItem.getNumberOfPackage() != null && goodItem.getNumberOfPackage().length() > 0) {
		if (!ActionUtils.checkIfIsLong(goodItem.getNumberOfPackage())) {
			status = false;
			logMessageValidation.setTagProperty("NumberOfPackage");
			logMessageValidation.setDataValidation("N20318: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(goodItem.getNumberOfPackage().length() >8){
			status = false;
			logMessageValidation.setTagProperty("NumberOfPackage");
			logMessageValidation.setDataValidation("N20418: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		}
//KindOfPackages
		if (goodItem.getKindOfPackages() != null && goodItem.getKindOfPackages().length() > 0) {
		 if(DmPackageLocalServiceUtil.getByPackageCode(goodItem.getKindOfPackages())==null){
			status = false;
			logMessageValidation.setTagProperty("KindOfPackages");
			logMessageValidation.setDataValidation("N20219: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(goodItem.getKindOfPackages().length()>200){
			status = false;
			logMessageValidation.setTagProperty("KindOfPackages");
			logMessageValidation.setDataValidation("N20519: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(goodItem.getKindOfPackages())) {
			status = false;
			logMessageValidation.setTagProperty("KindOfPackages");
			logMessageValidation.setDataValidation("N20619: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		}
//MarksAndNoOfGoods
		if (goodItem.getMarksAndNoOfGoods() != null && goodItem.getMarksAndNoOfGoods().length() > 0) {
		 if(goodItem.getMarksAndNoOfGoods().length()>200){
			status = false;
			logMessageValidation.setTagProperty("MarksAndNoOfGoods");
			logMessageValidation.setDataValidation("N20520: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(goodItem.getMarksAndNoOfGoods())) {
			status = false;
			logMessageValidation.setTagProperty("MarksAndNoOfGoods");
			logMessageValidation.setDataValidation("N20620: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		}
//GrossWeight
		if (goodItem.getGrossWeight() == null) {
			status = false;
			logMessageValidation.setTagProperty("GrossWeight");
			logMessageValidation.setDataValidation("N20121: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		else if(!ActionUtils.checkIfIsDouble(goodItem.getGrossWeight())) {
			status = false;
			logMessageValidation.setTagProperty("getGrossWeight");
			logMessageValidation.setDataValidation("N20321: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkFormatDouble(goodItem.getGrossWeight(), 6, 3)){
			status = false;
			logMessageValidation.setTagProperty("GrossWeight");
			logMessageValidation.setDataValidation("N20421: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//GrossWeightUnit
		if (goodItem.getGrossWeightUnit() != null && goodItem.getGrossWeightUnit().length() > 0) {
			if (goodItem.getGrossWeightUnit().length() > 20) {
			status = false;
			logMessageValidation.setTagProperty("GrossWeightUnit");
			logMessageValidation.setDataValidation("N20522: " + ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsAValidSpecialCharacters(goodItem.getGrossWeightUnit())) {
			status = false;
			logMessageValidation.setTagProperty("GrossWeightUnit");
			logMessageValidation.setDataValidation("N20622: " + ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//Measurement
		if (goodItem.getMeasurement() == null) {
			status = false;
			logMessageValidation.setTagProperty("Measurement");
			logMessageValidation.setDataValidation("N20123: " + ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsDouble(goodItem.getMeasurement())) {
			status = false;
			logMessageValidation.setTagProperty("Measurement");
			logMessageValidation.setDataValidation("N20323: " + ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkFormatDouble(goodItem.getMeasurement(), 6, 3)) {
			status = false;
			logMessageValidation.setTagProperty("Measurement");
			logMessageValidation.setDataValidation("N20423: " + ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//MeasurementUnit
		if (goodItem.getMeasurementUnit() != null && goodItem.getMeasurementUnit().length() > 0) {
			if (goodItem.getMeasurementUnit().length() > 20) {
				status = false;
				logMessageValidation.setTagProperty("MeasurementUnit");
				logMessageValidation.setDataValidation("N20524: " + ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils.checkIfIsAValidSpecialCharacters(goodItem.getMeasurementUnit())) {
				status = false;
				logMessageValidation.setTagProperty("MeasurementUnit");
				logMessageValidation.setDataValidation("N20624: " + ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		return status;
	}
}

