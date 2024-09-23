/**
 * 
 */
package vn.gt.utils.validation;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.dao.danhmuc.service.DmArrivalPurposeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmSecurityLevelLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.utils.ActionUtils;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.ShipSecurityNotification;
import vn.nsw.model.ShipSecurityNotification.Last10PortsOfCall.PortOfCall;

/**
 * @author win_64
 *
 */
public class ShipSecurityNotificationValidation {

	public boolean validation(ShipSecurityNotification shipSecurityNotification, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
//	DocumentName
		if (shipSecurityNotification.getDocumentName() == null) {
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N10102: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsLong(shipSecurityNotification.getDocumentName())) {
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N10302: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(shipSecurityNotification.getDocumentName().length() >9){
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N10402: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
// DocumentYear	
		if (shipSecurityNotification.getDocumentYear() == null) {
			status = false;
			logMessageValidation.setTagProperty("DocumentYear");
			logMessageValidation.setDataValidation("N10103: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsLong(shipSecurityNotification.getDocumentYear())) {
			status = false;
			logMessageValidation.setTagProperty("DocumentYear");
			logMessageValidation.setDataValidation("N10303: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(shipSecurityNotification.getDocumentYear().length() >9){
			status = false;
			logMessageValidation.setTagProperty("DocumentYear");
			logMessageValidation.setDataValidation("N10403: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//UserCreated
		if (shipSecurityNotification.getUserCreated() == null) {
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N10104: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getUserCreated().length() > 14) {
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N10504: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getUserCreated())){
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N10604: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//NameOfShip
		if (shipSecurityNotification.getNameOfShip() == null) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N10105: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getNameOfShip().length() > 100) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N10505: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getNameOfShip())){
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N10605: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//FlagStateOfShip
		if (shipSecurityNotification.getFlagStateOfShip() == null) {
			status = false;
			logMessageValidation.setTagProperty("FlagStateOfShip");
			logMessageValidation.setDataValidation("N10106: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getFlagStateOfShip()!= null && shipSecurityNotification.getFlagStateOfShip().trim().length() > 0 && DmStateLocalServiceUtil.getByStateCode(shipSecurityNotification.getFlagStateOfShip()) == null) {
			status = false;
			logMessageValidation.setTagProperty("FlagStateOfShip");
			logMessageValidation.setDataValidation("N10206: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getFlagStateOfShip().length() > 2) {
			status = false;
			logMessageValidation.setTagProperty("FlagStateOfShip");
			logMessageValidation.setDataValidation("N10506: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getFlagStateOfShip())){
			status = false;
			logMessageValidation.setTagProperty("FlagStateOfShip");
			logMessageValidation.setDataValidation("N10606: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//CallSign
		if (shipSecurityNotification.getCallSign() == null) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N10107: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getCallSign().length() > 9) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N10507: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getCallSign())){
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N10607: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//GrossTonnage
		if (shipSecurityNotification.getGrossTonnage() == null) {
			status = false;
			logMessageValidation.setTagProperty("GrossTonnage");
			logMessageValidation.setDataValidation("N10108: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} 
		else if(!ActionUtils.checkIfIsDouble(shipSecurityNotification.getGrossTonnage())) {
			status = false;
			logMessageValidation.setTagProperty("GrossTonnage");
			logMessageValidation.setDataValidation("N10308: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkFormatDouble(shipSecurityNotification.getGrossTonnage(), 6, 2)){
			status = false;
			logMessageValidation.setTagProperty("GrossTonnage");
			logMessageValidation.setDataValidation("N10408: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//GrossTonnageUnit
		if (shipSecurityNotification.getGrossTonnageUnit() != null && shipSecurityNotification.getGrossTonnageUnit().length() > 0) {
			if (shipSecurityNotification.getGrossTonnageUnit().length() > 4) {
				status = false;
				logMessageValidation.setTagProperty("GrossTonnageUnit");
				logMessageValidation.setDataValidation("N10509: "
						+ ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getGrossTonnageUnit())) {
				status = false;
				logMessageValidation.setTagProperty("GrossTonnageUnit");
				logMessageValidation.setDataValidation("N10609: "
						+ ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//ShipTypeCode
		if (shipSecurityNotification.getShipTypeCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N10110: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getShipTypeCode() != null && shipSecurityNotification.getShipTypeCode().trim().length()>0 && DmShipTypeLocalServiceUtil.getByShipTypeCode(shipSecurityNotification.getShipTypeCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N10210: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(shipSecurityNotification.getShipTypeCode().length()>5){
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N10510: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getShipTypeCode())){
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N10610: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//NumberOfCrew
		if (shipSecurityNotification.getNumberOfCrew() == null) {
			status = false;
			logMessageValidation.setTagProperty("NumberOfCrew");
			logMessageValidation.setDataValidation("N10111: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsLong(shipSecurityNotification.getNumberOfCrew())) {
			status = false;
			logMessageValidation.setTagProperty("NumberOfCrew");
			logMessageValidation.setDataValidation("N10311: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(shipSecurityNotification.getNumberOfCrew().length()>9){
			status = false;
			logMessageValidation.setTagProperty("NumberOfCrew");
			logMessageValidation.setDataValidation("N10510: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//ETA
		if (shipSecurityNotification.getETA() == null) {
			status = false;
			logMessageValidation.setTagProperty("ETA");
			logMessageValidation.setDataValidation("N10112: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if () {
//			logMessageValidation.setTagProperty("ETA");
//			logMessageValidation.setDataValidation("N10212: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(!FormatData.isThisDateValid(shipSecurityNotification.getETA())){
			status = false;
			logMessageValidation.setTagProperty("ETA");
			logMessageValidation.setDataValidation("N10712: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//IMONumber
		if(shipSecurityNotification.getIMONumber() != null && shipSecurityNotification.getIMONumber().length() > 0) {
		if (shipSecurityNotification.getIMONumber().length() >7){
			status = false;
			logMessageValidation.setTagProperty("IMONumber");
			logMessageValidation.setDataValidation("N10513: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getIMONumber())) {
			status = false;
			logMessageValidation.setTagProperty("IMONumber");
			logMessageValidation.setDataValidation("N10613: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//PurposeCode
		if (shipSecurityNotification.getPurposeCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("PurposeCode");
			logMessageValidation.setDataValidation("N10114: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getPurposeCode()!= null && shipSecurityNotification.getPurposeCode().trim().length() > 0 && DmArrivalPurposeLocalServiceUtil.getByPortCode(shipSecurityNotification.getPurposeCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("PurposeCode");
			logMessageValidation.setDataValidation("N10214: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(shipSecurityNotification.getPurposeCode().length()>3){
			status = false;
			logMessageValidation.setTagProperty("PurposeCode");
			logMessageValidation.setDataValidation("N10514: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getPurposeCode())) {
			status = false;
			logMessageValidation.setTagProperty("PurposeCode");
			logMessageValidation.setDataValidation("N10614: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		}
//OthersPurpose
		if (shipSecurityNotification.getOthersPurpose() != null && shipSecurityNotification.getOthersPurpose().length() > 0) {
		 if (shipSecurityNotification.getOthersPurpose().length()>250) {
			status = false;
			logMessageValidation.setTagProperty("OthersPurpose");
			logMessageValidation.setDataValidation("N10515: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getOthersPurpose())){
			status = false;
			logMessageValidation.setTagProperty("OthersPurpose");
			logMessageValidation.setDataValidation("N10615: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		}
//PortRegionCode
		if(shipSecurityNotification.getPortRegionCode() == null){
			status = false;
			logMessageValidation.setTagProperty("PortRegionCode");
			logMessageValidation.setDataValidation("N10116: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getPortRegionCode()!= null && shipSecurityNotification.getPortRegionCode().trim().length() > 0 && DmPortRegionLocalServiceUtil.getByPortRegionCode(shipSecurityNotification.getPortRegionCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("PortRegionCode");
			logMessageValidation.setDataValidation("N10216: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getPortRegionCode().length()>12) {
				status = false;
			logMessageValidation.setTagProperty("PortRegionCode");
			logMessageValidation.setDataValidation("N10516: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getPortRegionCode())){
			status = false;
			logMessageValidation.setTagProperty("PortRegionCode");
			logMessageValidation.setDataValidation("N10616: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//PortOfArrivalCode
		if(shipSecurityNotification.getPortOfArrivalCode() != null && shipSecurityNotification.getPortOfArrivalCode().length()>0){
		if (DmPortLocalServiceUtil.getByPortCode(shipSecurityNotification.getPortOfArrivalCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N10216b: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getPortOfArrivalCode().length()>5) {
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N10516b: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getPortOfArrivalCode())){
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N10616b: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//PortHarbourCode
		if(shipSecurityNotification.getPortHarbourCode() != null && shipSecurityNotification.getPortHarbourCode().length()>0){
		if (DmPortHarbourLocalServiceUtil.getByPortHarbourCode(shipSecurityNotification.getPortHarbourCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("PortHarbourCode");
			logMessageValidation.setDataValidation("N10216a: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getPortHarbourCode().length()>12) {
			status = false;
			logMessageValidation.setTagProperty("PortHarbourCode");
			logMessageValidation.setDataValidation("N10516a: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getPortHarbourCode())){
			status = false;
			logMessageValidation.setTagProperty("PortHarbourCode");
			logMessageValidation.setDataValidation("N10616a: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//PortWharfCode
		if(shipSecurityNotification.getPortWharfCode() != null && shipSecurityNotification.getPortWharfCode().length()>0){
		if (DmPortWharfLocalServiceUtil.getByPortWharfCode(shipSecurityNotification.getPortWharfCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("PortWharfCode");
			logMessageValidation.setDataValidation("N10217: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getPortWharfCode().length()>12) {
			status = false;
			logMessageValidation.setTagProperty("PortWharfCode");
			logMessageValidation.setDataValidation("N10517: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getPortWharfCode())){
			status = false;
			logMessageValidation.setTagProperty("PortWharfCode");
			logMessageValidation.setDataValidation("N10617: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//TaxCodeOfShipAgent
		if(shipSecurityNotification.getTaxCodeOfShipAgent() != null && shipSecurityNotification.getTaxCodeOfShipAgent().length() >0){
		if (shipSecurityNotification.getTaxCodeOfShipAgent().length()>13) {
			status = false;
			logMessageValidation.setTagProperty("TaxCodeOfShipAgent");
			logMessageValidation.setDataValidation("N10518: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getTaxCodeOfShipAgent())){
			status = false;
			logMessageValidation.setTagProperty("TaxCodeOfShipAgent");
			logMessageValidation.setDataValidation("N10618: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//NameOfShipAgent		
		if (shipSecurityNotification.getNameOfShipAgent() == null) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShipAgent");
			logMessageValidation.setDataValidation("N10119: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getNameOfShipAgent().length()>250) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShipAgent");
			logMessageValidation.setDataValidation("N10519: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getNameOfShipAgent())){
			status = false;
			logMessageValidation.setTagProperty("NameOfShipAgent");
			logMessageValidation.setDataValidation("N10619: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//AddressOfShipAgent	
		if(shipSecurityNotification.getAddressOfShipAgent() != null && shipSecurityNotification.getAddressOfShipAgent().length() > 0){
		if (shipSecurityNotification.getAddressOfShipAgent().length()>250) {
			status = false;
			logMessageValidation.setTagProperty("AddressOfShipAgent");
			logMessageValidation.setDataValidation("N10520: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getAddressOfShipAgent())){
			status = false;
			logMessageValidation.setTagProperty("AddressOfShipAgent");
			logMessageValidation.setDataValidation("N10620: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//TelOfShipAgency
		if(shipSecurityNotification.getTelOfShipAgency() != null && shipSecurityNotification.getTelOfShipAgency().length() >0){
		if (shipSecurityNotification.getTelOfShipAgency().length()>20) {
			status = false;
			logMessageValidation.setTagProperty("AddressOfShipAgent");
			logMessageValidation.setDataValidation("N10521: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getTelOfShipAgency())){
			status = false;
			logMessageValidation.setTagProperty("AddressOfShipAgent");
			logMessageValidation.setDataValidation("N10621: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//FaxOfShipAgency
		if(shipSecurityNotification.getFaxOfShipAgency() != null && shipSecurityNotification.getFaxOfShipAgency().length() >0){
		if (shipSecurityNotification.getFaxOfShipAgency().length()>250) {
			status = false;
			logMessageValidation.setTagProperty("AddressOfShipAgent");
			logMessageValidation.setDataValidation("N10522: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getFaxOfShipAgency())){
			status = false;
			logMessageValidation.setTagProperty("AddressOfShipAgent");
			logMessageValidation.setDataValidation("N10622: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//IsValidISSC
		if(shipSecurityNotification.getIsValidISSC() != null && shipSecurityNotification.getIsValidISSC().length() >0){
		if (!ActionUtils.checkIfIsLong(shipSecurityNotification.getIsValidISSC())) {
			status = false;
			logMessageValidation.setTagProperty("IsValidISSC");
			logMessageValidation.setDataValidation("N10323: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(shipSecurityNotification.getIsValidISSC().length()!= 1){
			status = false;
			logMessageValidation.setTagProperty("IsValidISSC");
			logMessageValidation.setDataValidation("N10423: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//NameOfAuthorityISSC
		if(shipSecurityNotification.getNameOfAuthorityISSC() != null && shipSecurityNotification.getNameOfAuthorityISSC().length() >0){
		if (shipSecurityNotification.getNameOfAuthorityISSC().length()>250) {
			status = false;
			logMessageValidation.setTagProperty("NameOfAuthorityISSC");
			logMessageValidation.setDataValidation("N10524: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getNameOfAuthorityISSC())){
			status = false;
			logMessageValidation.setTagProperty("NameOfAuthorityISSC");
			logMessageValidation.setDataValidation("N10624: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//DateOfIssueISSC
		if(shipSecurityNotification.getDateOfIssueISSC() != null && !FormatData.isThisDateValid(shipSecurityNotification.getDateOfIssueISSC())){
			status = false;
			logMessageValidation.setTagProperty("DateOfIssueISSC");
			logMessageValidation.setDataValidation("N10725: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
//DateOfExpiryISSC
		if(shipSecurityNotification.getDateOfExpiryISSC() != null && !FormatData.isThisDateValid(shipSecurityNotification.getDateOfExpiryISSC())){
			status = false;
			logMessageValidation.setTagProperty("DateOfExpiryISSC");
			logMessageValidation.setDataValidation("N10726: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//SecurityLevelCode
		if (shipSecurityNotification.getSecurityLevelCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("SecurityLevelCode");
			logMessageValidation.setDataValidation("N10127: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (shipSecurityNotification.getSecurityLevelCode()!= null && shipSecurityNotification.getSecurityLevelCode().trim().length() > 0 && DmSecurityLevelLocalServiceUtil.getBySecurityLevelCode(shipSecurityNotification.getSecurityLevelCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("SecurityLevelCode");
			logMessageValidation.setDataValidation("N10227: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(shipSecurityNotification.getSecurityLevelCode().length()>12){
			status = false;
			logMessageValidation.setTagProperty("SecurityLevelCode");
			logMessageValidation.setDataValidation("N10527: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getSecurityLevelCode())) {
			status = false;
			logMessageValidation.setTagProperty("SecurityLevelCode");
			logMessageValidation.setDataValidation("N10627: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//IsAdditionalSecurityMeasures
		if (shipSecurityNotification.getIsAdditionalSecurityMeasures() == null) {
			status = false;
			logMessageValidation.setTagProperty("IsAdditionalSecurityMeasures");
			logMessageValidation.setDataValidation("N10135: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsLong(shipSecurityNotification.getIsAdditionalSecurityMeasures())) {
			status = false;
			logMessageValidation.setTagProperty("IsAdditionalSecurityMeasures");
			logMessageValidation.setDataValidation("N10335: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(shipSecurityNotification.getIsAdditionalSecurityMeasures().length()!= 1){
			status = false;
			logMessageValidation.setTagProperty("IsAdditionalSecurityMeasures");
			logMessageValidation.setDataValidation("N10435: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//AdditionalSecurityDetail
		if(shipSecurityNotification.getAdditionalSecurityDetail() != null && shipSecurityNotification.getAdditionalSecurityDetail().length() >0){
		if(shipSecurityNotification.getAdditionalSecurityDetail().length()>500){
			status = false;
			logMessageValidation.setTagProperty("AdditionalSecurityDetail");
			logMessageValidation.setDataValidation("N10536: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getAdditionalSecurityDetail())) {
			status = false;
			logMessageValidation.setTagProperty("AdditionalSecurityDetail");
			logMessageValidation.setDataValidation("N10636: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//IsMaintainSecurity
		if (shipSecurityNotification.getIsMaintainSecurity() == null) {
			status = false;
			logMessageValidation.setTagProperty("IsMaintainSecurity");
			logMessageValidation.setDataValidation("N10137: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsLong(shipSecurityNotification.getIsMaintainSecurity())) {
			status = false;
			logMessageValidation.setTagProperty("IsMaintainSecurity");
			logMessageValidation.setDataValidation("N10337: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(shipSecurityNotification.getIsMaintainSecurity().length()!= 1){
			status = false;
			logMessageValidation.setTagProperty("IsMaintainSecurity");
			logMessageValidation.setDataValidation("N10437: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//MaintainSecurityDetail
		if(shipSecurityNotification.getMaintainSecurityDetail() != null && shipSecurityNotification.getMaintainSecurityDetail().length() >0){
		if(shipSecurityNotification.getMaintainSecurityDetail().length()>500){
			status = false;
			logMessageValidation.setTagProperty("MaintainSecurityDetail");
			logMessageValidation.setDataValidation("N10538: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getMaintainSecurityDetail())) {
			status = false;
			logMessageValidation.setTagProperty("MaintainSecurityDetail");
			logMessageValidation.setDataValidation("N10638: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//NameOfMaster
		if (shipSecurityNotification.getNameOfMaster() == null) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N10139: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(shipSecurityNotification.getNameOfMaster().length()>100){
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N10539: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getNameOfMaster())) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N10639: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//Latitude
		if (shipSecurityNotification.getLatitude() == null) {
			status = false;
			logMessageValidation.setTagProperty("Latitude");
			logMessageValidation.setDataValidation("N10140: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(shipSecurityNotification.getLatitude().length()>50){
			status = false;
			logMessageValidation.setTagProperty("Latitude");
			logMessageValidation.setDataValidation("N10540: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getLatitude())) {
			status = false;
			logMessageValidation.setTagProperty("Latitude");
			logMessageValidation.setDataValidation("N10640: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//Longitude
		if (shipSecurityNotification.getLongitude() == null) {
			status = false;
			logMessageValidation.setTagProperty("Longitude");
			logMessageValidation.setDataValidation("N10141: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(shipSecurityNotification.getLongitude().length()>50){
			status = false;
			logMessageValidation.setTagProperty("Longitude");
			logMessageValidation.setDataValidation("N10541: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getLongitude())) {
			status = false;
			logMessageValidation.setTagProperty("Longitude");
			logMessageValidation.setDataValidation("N10641: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}

//SignPlace
		if(shipSecurityNotification.getSignPlace() != null && shipSecurityNotification.getSignPlace().length() >0){
		if(shipSecurityNotification.getSignPlace().length()>50){
			status = false;
			logMessageValidation.setTagProperty("SignPlace");
			logMessageValidation.setDataValidation("N10542: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(shipSecurityNotification.getSignPlace())) {
			status = false;
			logMessageValidation.setTagProperty("SignPlace");
			logMessageValidation.setDataValidation("N10642: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
//SignDate
		if(shipSecurityNotification.getSignDate() != null && !FormatData.isThisDateValid(shipSecurityNotification.getSignDate())){
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N10743: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//MasterSigned
		if(shipSecurityNotification.getMasterSigned() != null && shipSecurityNotification.getMasterSigned().length() >0){
		if (!ActionUtils.checkIfIsLong(shipSecurityNotification.getMasterSigned())) {
			status = false;
			logMessageValidation.setTagProperty("MasterSigned");
			logMessageValidation.setDataValidation("N10344: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			
		} else if(shipSecurityNotification.getMasterSigned().length()>9){
			status = false;
			logMessageValidation.setTagProperty("MasterSigned");
			logMessageValidation.setDataValidation("N10444: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		return status;
	}
	
	
	
	
	private boolean validationlist(PortOfCall portOfCall, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
//PortOfCallCode
		if (portOfCall.getPortOfCallCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("PortOfCallCode");
			logMessageValidation.setDataValidation("N10130: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(portOfCall.getPortOfCallCode().length()>12){
			status = false;
			logMessageValidation.setTagProperty("PortOfCallCode");
			logMessageValidation.setDataValidation("N10530: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(portOfCall.getPortOfCallCode())) {
			status = false;
			logMessageValidation.setTagProperty("PortOfCallCode");
			logMessageValidation.setDataValidation("N10630: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//PortCode
		if (portOfCall.getPortCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("PortCode");
			logMessageValidation.setDataValidation("N10131: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (portOfCall.getPortCode()!= null && portOfCall.getPortCode().trim().length() > 0 && DmPortLocalServiceUtil.getByPortCode(portOfCall.getPortCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("PortCode");
			logMessageValidation.setDataValidation("N10231: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(portOfCall.getPortCode().length()>5){
			status = false;
			logMessageValidation.setTagProperty("PortCode");
			logMessageValidation.setDataValidation("N10531: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(portOfCall.getPortCode())) {
			status = false;
			logMessageValidation.setTagProperty("PortCode");
			logMessageValidation.setDataValidation("N10631: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}		
//DateOfArrival
		if (portOfCall.getDateOfArrival() == null) {
			status = false;
			logMessageValidation.setTagProperty("DateOfArrival");
			logMessageValidation.setDataValidation("N10132: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(!FormatData.isThisDateValid(portOfCall.getDateOfArrival())){
			status = false;
			logMessageValidation.setTagProperty("DateOfArrival");
			logMessageValidation.setDataValidation("N10732: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
//DateOfDeparture
		if (portOfCall.getDateOfDeparture() == null) {
			status = false;
			logMessageValidation.setTagProperty("DateOfDeparture");
			logMessageValidation.setDataValidation("N10133: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if(!FormatData.isThisDateValid(portOfCall.getDateOfDeparture())){
			status = false;
			logMessageValidation.setTagProperty("DateOfDeparture");
			logMessageValidation.setDataValidation("N10733: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
//SecurityLevelCode
		if (portOfCall.getSecurityLevelCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("SecurityLevelCode");
			logMessageValidation.setDataValidation("N10134: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (portOfCall.getSecurityLevelCode()!= null && portOfCall.getSecurityLevelCode().trim().length() > 0 && DmSecurityLevelLocalServiceUtil.getBySecurityLevelCode(portOfCall.getSecurityLevelCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("SecurityLevelCode");
			logMessageValidation.setDataValidation("N10234: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if(portOfCall.getSecurityLevelCode().length()>12){
			status = false;
			logMessageValidation.setTagProperty("SecurityLevelCode");
			logMessageValidation.setDataValidation("N10534: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}else if (!ActionUtils.checkIfIsAValidSpecialCharacters(portOfCall.getSecurityLevelCode())) {
			status = false;
			logMessageValidation.setTagProperty("SecurityLevelCode");
			logMessageValidation.setDataValidation("N10634: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}		
		
		return status;
	}
}

