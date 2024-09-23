/**
 * 
 */
package vn.gt.utils.validation.inland;

import java.util.Date;

import com.fds.nsw.kernel.exception.SystemException;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.DmHistoryShipAgency;
import com.fds.nsw.nghiepvu.model.DmShipAgency;
import com.fds.nsw.nghiepvu.model.DmHistoryShipAgency;
import com.fds.nsw.nghiepvu.model.DmShipAgency;
import vn.gt.dao.danhmuc.service.DmHistoryShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.utils.ActionUtils;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.inland.InlandGeneralDeclaration;

/**
 * @author win_64
 * 
 */
public class InlandGeneralDeclarationValidation {

	public boolean validation(InlandGeneralDeclaration generalDeclaration,
			Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference()
				.getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject()
				.getDocumentType()));
		logMessageValidation
				.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject()
				.getDocumentYear());
		boolean status = true;
		// DocumentName
		if (generalDeclaration.getDocumentName() == null) {
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N50102: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsLong(generalDeclaration
				.getDocumentName())) {
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N50302: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration.getDocumentName().length() > 9) {
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N50402: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// DocumentYear
		if (generalDeclaration.getDocumentYear() != null && generalDeclaration.getDocumentYear().length() > 0) {
			if (!ActionUtils
					.checkIfIsLong(generalDeclaration.getDocumentYear())) {
				status = false;
				logMessageValidation.setTagProperty("DocumentYear");
				logMessageValidation.setDataValidation("N50303: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N003", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (generalDeclaration.getDocumentYear().length() > 9) {
				status = false;
				logMessageValidation.setTagProperty("DocumentYear");
				logMessageValidation.setDataValidation("N50403: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N004", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// UserCreated
		if (generalDeclaration.getUserCreated() == null) {
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N50104: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration.getUserCreated().length() > 14) {
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N50504: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(generalDeclaration
						.getUserCreated())) {
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N50604: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// isArrival
		if (generalDeclaration.getIsArrival() == null) {
			status = false;
			logMessageValidation.setTagProperty("isArrival");
			logMessageValidation.setDataValidation("N50105: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsLong(generalDeclaration.getIsArrival())) {
			status = false;
			logMessageValidation.setTagProperty("isArrival");
			logMessageValidation.setDataValidation("N50305: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration.getIsArrival().length() != 1) {
			status = false;
			logMessageValidation.setTagProperty("isArrival");
			logMessageValidation.setDataValidation("N50405: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// NameOfShip
		if (generalDeclaration.getNameOfShip() == null) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N50106: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration.getNameOfShip().length() > 100) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N50506: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(generalDeclaration
						.getNameOfShip())) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N50606: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// ShipTypeCode
		if (generalDeclaration.getShipTypeCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N50107: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration.getShipTypeCode()!=null && generalDeclaration.getShipTypeCode().trim().length()>0 && DmShipTypeLocalServiceUtil
				.getByShipTypeCode(generalDeclaration.getShipTypeCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N50207: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration.getShipTypeCode().length() > 5) {
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N50507: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(generalDeclaration
						.getShipTypeCode())) {
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N50607: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// IMONumber
//		if (generalDeclaration.getIMONumber() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("IMONumber");
//			logMessageValidation.setDataValidation("N50108: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (generalDeclaration.getIMONumber().length() > 7) {
//			status = false;
//			logMessageValidation.setTagProperty("IMONumber");
//			logMessageValidation.setDataValidation("N50508: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(generalDeclaration
//						.getIMONumber())) {
//			status = false;
//			logMessageValidation.setTagProperty("IMONumber");
//			logMessageValidation.setDataValidation("N50608: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// CallSign
		if (generalDeclaration.getCallSign() == null) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N50109: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration.getCallSign().length() > 9) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N50509: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(generalDeclaration
						.getCallSign())) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N50609: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// VoyageNumber
//		if (generalDeclaration.getVoyageNumber() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("VoyageNumber");
//			logMessageValidation.setDataValidation("N50110: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (generalDeclaration.getVoyageNumber().length() > 20) {
//			status = false;
//			logMessageValidation.setTagProperty("CallSign");
//			logMessageValidation.setDataValidation("N50510: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(generalDeclaration
//						.getVoyageNumber())) {
//			status = false;
//			logMessageValidation.setTagProperty("CallSign");
//			logMessageValidation.setDataValidation("N50610: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// PortOfArrivalCode
		if (generalDeclaration.getPortOfArrivalCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N50111: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration
				.getPortOfArrivalCode()!= null && generalDeclaration
						.getPortOfArrivalCode().trim().length()>0 && DmPortLocalServiceUtil.getByPortCode(generalDeclaration
				.getPortOfArrivalCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N50211: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration.getPortOfArrivalCode().length() > 5) {
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N50511: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(generalDeclaration
						.getPortOfArrivalCode())) {
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N50611: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// DateOfArrival
		if (generalDeclaration.getDateOfArrival() == null) {
			status = false;
			logMessageValidation.setTagProperty("DateOfArrival");
			logMessageValidation.setDataValidation("N50112: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!FormatData.isThisDateValid(generalDeclaration
				.getDateOfArrival())) {
			status = false;
			logMessageValidation.setTagProperty("DateOfArrival");
			logMessageValidation.setDataValidation("N50712: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N007", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// PortHarbourCode
		if (generalDeclaration.getPortHarbourCode() != null
				&& generalDeclaration.getPortHarbourCode().length() > 0) {
			if (generalDeclaration
					.getPortHarbourCode() != null && generalDeclaration
							.getPortHarbourCode().trim().length() > 0 && DmPortHarbourLocalServiceUtil
					.getByPortHarbourCode(generalDeclaration
							.getPortHarbourCode()) == null) {
				status = false;
				logMessageValidation.setTagProperty("PortHarbourCode");
				logMessageValidation.setDataValidation("N50213: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N002", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (generalDeclaration.getPortHarbourCode().length() > 12) {
				status = false;
				logMessageValidation.setTagProperty("PortHarbourCode");
				logMessageValidation.setDataValidation("N50513: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(generalDeclaration
							.getPortHarbourCode())) {
				status = false;
				logMessageValidation.setTagProperty("PortHarbourCode");
				logMessageValidation.setDataValidation("N50613: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// PortRegionCode
		if (generalDeclaration.getPortRegionCode() != null
				&& generalDeclaration.getPortRegionCode().length() > 0) {
			if (DmPortRegionLocalServiceUtil
					.getByPortRegionCode(generalDeclaration.getPortRegionCode()) == null) {
				status = false;
				logMessageValidation.setTagProperty("PortRegionCode");
				logMessageValidation.setDataValidation("N50214: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N002", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (generalDeclaration.getPortRegionCode().length() > 12) {
				status = false;
				logMessageValidation.setTagProperty("PortRegionCode");
				logMessageValidation.setDataValidation("N50514: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);

			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(generalDeclaration
							.getPortRegionCode())) {
				status = false;
				logMessageValidation.setTagProperty("PortRegionCode");
				logMessageValidation.setDataValidation("N50614: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// PortWharfCode
		if (generalDeclaration.getPortWharfCode() != null
				&& generalDeclaration.getPortWharfCode().length() > 0) {
			if (DmPortWharfLocalServiceUtil
					.getByPortWharfCode(generalDeclaration.getPortWharfCode()) == null) {
				status = false;
				logMessageValidation.setTagProperty("PortWharfCode");
				logMessageValidation.setDataValidation("N50215: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N002", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (generalDeclaration.getPortWharfCode().length() > 12) {
				status = false;
				logMessageValidation.setTagProperty("PortWharfCode");
				logMessageValidation.setDataValidation("N50515: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(generalDeclaration
							.getPortWharfCode())) {
				status = false;
				logMessageValidation.setTagProperty("PortWharfCode");
				logMessageValidation.setDataValidation("N50615: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// //FlagStateOfShip
//		if (generalDeclaration.getFlagStateOfShip() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("FlagStateOfShip");
//			logMessageValidation.setDataValidation("N50116: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (generalDeclaration
//				.getFlagStateOfShip() != null && generalDeclaration
//						.getFlagStateOfShip().trim().length() > 0 && DmStateLocalServiceUtil.getByStateCode(generalDeclaration
//				.getFlagStateOfShip()) == null) {
//			status = false;
//			logMessageValidation.setTagProperty("FlagStateOfShip");
//			logMessageValidation.setDataValidation("N50216: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N002", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (generalDeclaration.getFlagStateOfShip().length() > 2) {
//			status = false;
//			logMessageValidation.setTagProperty("FlagStateOfShip");
//			logMessageValidation.setDataValidation("N50516: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(generalDeclaration
//						.getFlagStateOfShip())) {
//			status = false;
//			logMessageValidation.setTagProperty("FlagStateOfShip");
//			logMessageValidation.setDataValidation("N50616: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// NameOfMaster
		if (generalDeclaration.getNameOfMaster() == null) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N50117: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration.getNameOfMaster().length() > 100) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N50517: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(generalDeclaration
						.getNameOfMaster())) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N50617: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// //LastPortOfCallCode
		if (generalDeclaration.getLastPortOfCallCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("LastPortOfCallCode");
			logMessageValidation.setDataValidation("N50118: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration
				.getLastPortOfCallCode() != null && generalDeclaration
						.getLastPortOfCallCode().trim().length() >0 && DmPortLocalServiceUtil.getByPortCode(generalDeclaration
				.getLastPortOfCallCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("LastPortOfCallCode");
			logMessageValidation.setDataValidation("N50218: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (generalDeclaration.getLastPortOfCallCode().length() > 5) {
			status = false;
			logMessageValidation.setTagProperty("LastPortOfCallCode");
			logMessageValidation.setDataValidation("N50518: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(generalDeclaration
						.getLastPortOfCallCode())) {
			status = false;
			logMessageValidation.setTagProperty("LastPortOfCallCode");
			logMessageValidation.setDataValidation("N50618: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// CertificateOfRegistryNumber
//		if (generalDeclaration.getCertificateOfRegistryNumber() != null && generalDeclaration.getCertificateOfRegistryNumber().length() >0) {
//			if (generalDeclaration.getCertificateOfRegistryNumber().length() > 20) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("CertificateOfRegistryNumber");
//				logMessageValidation.setDataValidation("N50519: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getCertificateOfRegistryNumber())) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("CertificateOfRegistryNumber");
//				logMessageValidation.setDataValidation("N50619: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// CertificateOfRegistryDate
//		if (generalDeclaration.getCertificateOfRegistryDate() != null
//				&& !FormatData.isThisDateValid(generalDeclaration
//						.getCertificateOfRegistryDate())) {
//			status = false;
//			logMessageValidation.setTagProperty("CertificateOfRegistryDate");
//			logMessageValidation.setDataValidation("N50720: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N007", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// CertificateOfRegistryPortName
//		if (generalDeclaration.getCertificateOfRegistryPortName() != null && generalDeclaration.getCertificateOfRegistryPortName().length() >0) {
//			if (generalDeclaration.getCertificateOfRegistryPortName().length() > 5) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("CertificateOfRegistryPortName");
//				logMessageValidation.setDataValidation("N50521: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getCertificateOfRegistryPortName())) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("CertificateOfRegistryPortName");
//				logMessageValidation.setDataValidation("N50621: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// TaxCodeOfShipownersAgents
		if (generalDeclaration.getTaxCodeOfShipownersAgents() == null) {
			status = false;
			logMessageValidation.setTagProperty("TaxCodeOfShipownersAgents");
			logMessageValidation.setDataValidation("N50122: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (DmShipAgencyLocalServiceUtil
				.getByShipAgencyCode(generalDeclaration
						.getTaxCodeOfShipownersAgents()) == null) {
			// status = false;
			// logMessageValidation.setTagProperty("TaxCodeOfShipownersAgents");
			// logMessageValidation.setDataValidation("N50222: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002",
			// ""));
			// ValidationUtils.addLogMessageValidation(logMessageValidation);
			insertShipAgency(generalDeclaration);

		} else if (generalDeclaration.getTaxCodeOfShipownersAgents().length() > 20) {
			status = false;
			logMessageValidation.setTagProperty("TaxCodeOfShipownersAgents");
			logMessageValidation.setDataValidation("N50522: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(generalDeclaration
						.getTaxCodeOfShipownersAgents())) {
			status = false;
			logMessageValidation.setTagProperty("TaxCodeOfShipownersAgents");
			logMessageValidation.setDataValidation("N50622: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// NameOfShipownersAgents
		if (generalDeclaration.getNameOfShipownersAgents() != null && generalDeclaration.getNameOfShipownersAgents().length() >0) {
			if (generalDeclaration.getNameOfShipownersAgents().length() > 250) {
				status = false;
				logMessageValidation.setTagProperty("NameOfShipownersAgents");
				logMessageValidation.setDataValidation("N50523: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(generalDeclaration
							.getNameOfShipownersAgents())) {
				status = false;
				logMessageValidation.setTagProperty("NameOfShipownersAgents");
				logMessageValidation.setDataValidation("N50623: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// ShipAgencyAddress
		if (generalDeclaration.getShipAgencyAddress() != null && generalDeclaration.getShipAgencyAddress().length() >0) {
			if (generalDeclaration.getShipAgencyAddress().length() > 200) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyAddress");
				logMessageValidation.setDataValidation("N50524: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(generalDeclaration
							.getShipAgencyAddress())) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyAddress");
				logMessageValidation.setDataValidation("N50624: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// ShipAgencyPhone
		if (generalDeclaration.getShipAgencyPhone() != null && generalDeclaration.getShipAgencyPhone().length() >0) {
			if (generalDeclaration.getShipAgencyPhone().length() > 100) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyPhone");
				logMessageValidation.setDataValidation("N50525: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(generalDeclaration
							.getShipAgencyPhone())) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyPhone");
				logMessageValidation.setDataValidation("N50625: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// ShipAgencyFax
		if (generalDeclaration.getShipAgencyFax() != null && generalDeclaration.getShipAgencyFax().length() >0) {
			if (generalDeclaration.getShipAgencyFax().length() > 100) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyFax");
				logMessageValidation.setDataValidation("N50526: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(generalDeclaration
							.getShipAgencyFax())) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyFax");
				logMessageValidation.setDataValidation("N50626: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// ShipAgencyEmail
		if (generalDeclaration.getShipAgencyEmail() != null && generalDeclaration.getShipAgencyEmail().length() >0) {
			if (generalDeclaration.getShipAgencyEmail().length() > 100) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyEmail");
				logMessageValidation.setDataValidation("N50527: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} 
//			else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getShipAgencyEmail())) {
//				status = false;
//				logMessageValidation.setTagProperty("ShipAgencyEmail");
//				logMessageValidation.setDataValidation("N50627: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
		}
		// GrossTonnage
//		if (generalDeclaration.getGrossTonnage() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("GrossTonnage");
//			logMessageValidation.setDataValidation("N50128: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkIfIsDouble(generalDeclaration
//				.getGrossTonnage())) {
//			status = false;
//			logMessageValidation.setTagProperty("GrossTonnage");
//			logMessageValidation.setDataValidation("N50328: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N003", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkFormatDouble(
//				generalDeclaration.getGrossTonnage(), 6, 2)) {
//			status = false;
//			logMessageValidation.setTagProperty("GrossTonnage");
//			logMessageValidation.setDataValidation("N50428: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N004", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// GrossTonnageUnit
//		if (generalDeclaration.getGrossTonnageUnit() != null && generalDeclaration.getGrossTonnageUnit().length() >0) {
//			if (generalDeclaration.getGrossTonnageUnit().length() > 4) {
//				status = false;
//				logMessageValidation.setTagProperty("GrossTonnageUnit");
//				logMessageValidation.setDataValidation("N50529: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getGrossTonnageUnit())) {
//				status = false;
//				logMessageValidation.setTagProperty("GrossTonnageUnit");
//				logMessageValidation.setDataValidation("N50629: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// NetTonnage
//		if (generalDeclaration.getNetTonnage() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("NetTonnage");
//			logMessageValidation.setDataValidation("N50130: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkIfIsDouble(generalDeclaration
//				.getNetTonnage())) {
//			status = false;
//			logMessageValidation.setTagProperty("NetTonnage");
//			logMessageValidation.setDataValidation("N50330: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N003", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkFormatDouble(
//				generalDeclaration.getNetTonnage(), 6, 2)) {
//			status = false;
//			logMessageValidation.setTagProperty("NetTonnage");
//			logMessageValidation.setDataValidation("N50430: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N004", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// NetTonnageUnit
//		if (generalDeclaration.getNetTonnageUnit() != null && generalDeclaration.getNetTonnageUnit().length() >0) {
//			if (generalDeclaration.getNetTonnageUnit().length() > 4) {
//				status = false;
//				logMessageValidation.setTagProperty("NetTonnageUnit");
//				logMessageValidation.setDataValidation("N50531: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getNetTonnageUnit())) {
//				status = false;
//				logMessageValidation.setTagProperty("NetTonnageUnit");
//				logMessageValidation.setDataValidation("N50631: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// PositionOfShipInPort
//		if (generalDeclaration.getPositionOfShipInPort() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("PositionOfShipInPort");
//			logMessageValidation.setDataValidation("N50132: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (generalDeclaration.getPositionOfShipInPort().length() > 250) {
//			status = false;
//			logMessageValidation.setTagProperty("PositionOfShipInPort");
//			logMessageValidation.setDataValidation("N50532: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(generalDeclaration
//						.getPositionOfShipInPort())) {
//			status = false;
//			logMessageValidation.setTagProperty("PositionOfShipInPort");
//			logMessageValidation.setDataValidation("N50632: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// BriefParticularsOfVoyage
//		if (generalDeclaration.getBriefParticularsOfVoyage() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("BriefParticularsOfVoyage");
//			logMessageValidation.setDataValidation("N50133: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (generalDeclaration.getBriefParticularsOfVoyage().length() > 500) {
//			status = false;
//			logMessageValidation.setTagProperty("BriefParticularsOfVoyage");
//			logMessageValidation.setDataValidation("N50533: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(generalDeclaration
//						.getBriefParticularsOfVoyage())) {
//			status = false;
//			logMessageValidation.setTagProperty("BriefParticularsOfVoyage");
//			logMessageValidation.setDataValidation("N50633: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// BriefDescriptionOfTheCargo
//		if (generalDeclaration.getBriefDescriptionOfTheCargo() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("BriefDescriptionOfTheCargo");
//			logMessageValidation.setDataValidation("N50134: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (generalDeclaration.getBriefDescriptionOfTheCargo().length() > 500) {
//			status = false;
//			logMessageValidation.setTagProperty("BriefDescriptionOfTheCargo");
//			logMessageValidation.setDataValidation("N50534: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(generalDeclaration
//						.getBriefDescriptionOfTheCargo())) {
//			status = false;
//			logMessageValidation.setTagProperty("BriefDescriptionOfTheCargo");
//			logMessageValidation.setDataValidation("N50634: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// NumberOfCrew
//		if (generalDeclaration.getNumberOfCrew() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("NumberOfCrew");
//			logMessageValidation.setDataValidation("N50135: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (generalDeclaration.getNumberOfCrew().length() > 9) {
//			status = false;
//			logMessageValidation.setTagProperty("NumberOfCrew");
//			logMessageValidation.setDataValidation("N50535: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(generalDeclaration
//						.getNumberOfCrew())) {
//			status = false;
//			logMessageValidation.setTagProperty("NumberOfCrew");
//			logMessageValidation.setDataValidation("N50635: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// NumberOfPassengers
//		if (generalDeclaration.getNumberOfPassengers() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("NumberOfPassengers");
//			logMessageValidation.setDataValidation("N50136: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (generalDeclaration.getNumberOfPassengers().length() > 9) {
//			status = false;
//			logMessageValidation.setTagProperty("NumberOfPassengers");
//			logMessageValidation.setDataValidation("N50536: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(generalDeclaration
//						.getNumberOfPassengers())) {
//			status = false;
//			logMessageValidation.setTagProperty("NumberOfPassengers");
//			logMessageValidation.setDataValidation("N50636: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// ShipRequirementsInTermsOfWaste
//		if (generalDeclaration.getShipRequirementsInTermsOfWaste() == null) {
//			status = false;
//			logMessageValidation
//					.setTagProperty("ShipRequirementsInTermsOfWaste");
//			logMessageValidation.setDataValidation("N50137: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (generalDeclaration.getShipRequirementsInTermsOfWaste()
//				.length() > 500) {
//			status = false;
//			logMessageValidation
//					.setTagProperty("ShipRequirementsInTermsOfWaste");
//			logMessageValidation.setDataValidation("N50537: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(generalDeclaration
//						.getShipRequirementsInTermsOfWaste())) {
//			status = false;
//			logMessageValidation
//					.setTagProperty("ShipRequirementsInTermsOfWaste");
//			logMessageValidation.setDataValidation("N50637: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// Remarks
		if (generalDeclaration.getRemarks() != null && generalDeclaration.getRemarks().length() >0) {
		if (generalDeclaration.getRemarks().length() > 500) {
			status = false;
			logMessageValidation.setTagProperty("Remarks");
			logMessageValidation.setDataValidation("N50538: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(generalDeclaration
						.getRemarks())) {
			status = false;
			logMessageValidation.setTagProperty("Remarks");
			logMessageValidation.setDataValidation("N50638: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		}
		// NumberCargoDeclaration
//		if (generalDeclaration.getNumberCargoDeclaration() != null && generalDeclaration.getNumberCargoDeclaration().length() >0) {
//			if (generalDeclaration.getNumberCargoDeclaration().length() > 12) {
//				status = false;
//				logMessageValidation.setTagProperty("NumberCargoDeclaration");
//				logMessageValidation.setDataValidation("N50539: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getNumberCargoDeclaration())) {
//				status = false;
//				logMessageValidation.setTagProperty("NumberCargoDeclaration");
//				logMessageValidation.setDataValidation("N50639: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// NumberShipStoreDeclaration
//		if (generalDeclaration.getNumberShipStoreDeclaration() != null && generalDeclaration.getNumberShipStoreDeclaration().length() >0) {
//			if (generalDeclaration.getNumberShipStoreDeclaration().length() > 12) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("NumberShipStoreDeclaration");
//				logMessageValidation.setDataValidation("N50540: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getNumberShipStoreDeclaration())) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("NumberShipStoreDeclaration");
//				logMessageValidation.setDataValidation("N50640: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// NumberCrewList
//		if (generalDeclaration.getNumberCrewList() != null && generalDeclaration.getNumberCrewList().length() >0) {
//			if (generalDeclaration.getNumberCrewList().length() > 12) {
//				status = false;
//				logMessageValidation.setTagProperty("NumberCrewList");
//				logMessageValidation.setDataValidation("N50541: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getNumberCrewList())) {
//				status = false;
//				logMessageValidation.setTagProperty("NumberCrewList");
//				logMessageValidation.setDataValidation("N50641: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// NumberPassengerList
//		if (generalDeclaration.getNumberPassengerList() != null && generalDeclaration.getNumberPassengerList().length() >0) {
//			if (generalDeclaration.getNumberPassengerList().length() > 12) {
//				status = false;
//				logMessageValidation.setTagProperty("NumberPassengerList");
//				logMessageValidation.setDataValidation("N50542: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getNumberPassengerList())) {
//				status = false;
//				logMessageValidation.setTagProperty("NumberPassengerList");
//				logMessageValidation.setDataValidation("N50642: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// NumberCrewEffectsDeclaration
//		if (generalDeclaration.getNumberCrewEffectsDeclaration() != null && generalDeclaration.getNumberCrewEffectsDeclaration().length() >0) {
//			if (generalDeclaration.getNumberCrewEffectsDeclaration().length() > 12) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("NumberCrewEffectsDeclaration");
//				logMessageValidation.setDataValidation("N50543: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getNumberCrewEffectsDeclaration())) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("NumberCrewEffectsDeclaration");
//				logMessageValidation.setDataValidation("N50643: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// NumberHealthMaritimeDeclaration
//		if (generalDeclaration.getNumberHealthMaritimeDeclaration() != null && generalDeclaration.getNumberHealthMaritimeDeclaration().length() >0) {
//			if (generalDeclaration.getNumberHealthMaritimeDeclaration()
//					.length() > 12) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("NumberHealthMaritimeDeclaration");
//				logMessageValidation.setDataValidation("N50544: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(generalDeclaration
//							.getNumberHealthMaritimeDeclaration())) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("NumberHealthMaritimeDeclaration");
//				logMessageValidation.setDataValidation("N50644: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// SignPlace
		if (generalDeclaration.getSignPlace() != null && generalDeclaration.getSignPlace().length() >0) {
			if (generalDeclaration.getSignPlace().length() > 50) {
				status = false;
				logMessageValidation.setTagProperty("SignPlace");
				logMessageValidation.setDataValidation("N50545: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(generalDeclaration
							.getSignPlace())) {
				status = false;
				logMessageValidation.setTagProperty("SignPlace");
				logMessageValidation.setDataValidation("N50645: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// SignDate
		if (generalDeclaration.getSignDate() != null
				&& !FormatData
						.isThisDateValid(generalDeclaration.getSignDate())) {
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N50546: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// MasterSigned
		if (generalDeclaration.getMasterSigned() != null && generalDeclaration.getMasterSigned().length() >0) {
			if (!ActionUtils
					.checkIfIsLong(generalDeclaration.getMasterSigned())) {
				status = false;
				logMessageValidation.setTagProperty("MasterSigned");
				logMessageValidation.setDataValidation("N50347: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N003", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (generalDeclaration.getMasterSigned().length() > 9) {
				status = false;
				logMessageValidation.setTagProperty("MasterSigned");
				logMessageValidation.setDataValidation("N50447: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N004", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		return status;
	}

	private void insertShipAgency(InlandGeneralDeclaration generalDeclaration)
			throws SystemException {
		DmShipAgency dmShipAgency = new DmShipAgency();
		dmShipAgency.setShipAgencyCode(generalDeclaration
				.getTaxCodeOfShipownersAgents());
		dmShipAgency.setShipAgencyName(generalDeclaration
				.getNameOfShipownersAgents());
		dmShipAgency.setShipAgencyNameVN(generalDeclaration
				.getNameOfShipownersAgents());
		dmShipAgency.setTaxCode(generalDeclaration
				.getTaxCodeOfShipownersAgents());
		dmShipAgency.setAddress(generalDeclaration.getShipAgencyAddress());
		dmShipAgency.setAddressVN(generalDeclaration.getShipAgencyAddress());
		dmShipAgency.setPhone(generalDeclaration.getShipAgencyPhone());
		dmShipAgency.setFax(generalDeclaration.getShipAgencyFax());
		dmShipAgency.setEmail(generalDeclaration.getShipAgencyEmail());
		dmShipAgency.setIsDelete(0);
		dmShipAgency.setMarkedAsDelete(0);
		dmShipAgency.setSyncVersion("1|");
		dmShipAgency.setModifiedDate(new Date());
		dmShipAgency.setRequestedDate(new Date());
		// dmShipAgency.setDescription(generalDeclaration.getTaxCodeOfShipownersAgents());
		// dmShipAgency.setContactTell(generalDeclaration.getShipAgencyPhone());
		DmShipAgencyLocalServiceUtil.addDmShipAgency(dmShipAgency);

		DmHistoryShipAgency dmHistoryShipAgency = new DmHistoryShipAgency();
		dmHistoryShipAgency.setShipAgencyCode(generalDeclaration
				.getTaxCodeOfShipownersAgents());
		dmHistoryShipAgency.setShipAgencyName(generalDeclaration
				.getNameOfShipownersAgents());
		dmHistoryShipAgency.setShipAgencyNameVN(generalDeclaration
				.getNameOfShipownersAgents());
		dmHistoryShipAgency.setTaxCode(generalDeclaration
				.getTaxCodeOfShipownersAgents());
		dmHistoryShipAgency.setAddress(generalDeclaration
				.getShipAgencyAddress());
		dmHistoryShipAgency.setAddressVN(generalDeclaration
				.getShipAgencyAddress());
		dmHistoryShipAgency.setPhone(generalDeclaration.getShipAgencyPhone());
		dmHistoryShipAgency.setFax(generalDeclaration.getShipAgencyFax());
		dmHistoryShipAgency.setEmail(generalDeclaration.getShipAgencyEmail());
		dmHistoryShipAgency.setIsDelete(0);
		dmHistoryShipAgency.setMarkedAsDelete(0);
		dmHistoryShipAgency.setModifiedDate(new Date());
		dmHistoryShipAgency.setRequestedDate(new Date());
		dmHistoryShipAgency.setSyncVersion("1");
		// dmHistoryShipAgency.setDescription(generalDeclaration.getTaxCodeOfShipownersAgents());
		// dmHistoryShipAgency.setContactTell(generalDeclaration.getShipAgencyPhone());
		DmHistoryShipAgencyLocalServiceUtil
				.addDmHistoryShipAgency(dmHistoryShipAgency);
	}
}
