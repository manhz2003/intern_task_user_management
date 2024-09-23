/**
 * 
 */
package vn.gt.utils.validation.inland;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.dao.danhmuc.service.DmArrivalPurposeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmGoodsTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmUnitGeneralLocalServiceUtil;
import vn.gt.utils.ActionUtils;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.inland.InlandNoticeOfArrival;

/**
 * @author win_64
 * 
 */
public class InlandNoticeOfArrivalValidation {

	public boolean validation(InlandNoticeOfArrival noticeOfArrival, Header header,
			String requestDirection) throws Exception {
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
		if (noticeOfArrival.getDocumentName() == null) {
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N30102: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsLong(noticeOfArrival.getDocumentName())) {
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N30302: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getDocumentName().length() > 9) {
			status = false;
			logMessageValidation.setTagProperty("DocumentName");
			logMessageValidation.setDataValidation("N30402: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// DocumentYear
		if (noticeOfArrival.getDocumentYear() != null
				&& noticeOfArrival.getDocumentYear().length() > 0) {
			if (!ActionUtils.checkIfIsLong(noticeOfArrival.getDocumentYear())) {
				status = false;
				logMessageValidation.setTagProperty("DocumentYear");
				logMessageValidation.setDataValidation("N30303: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N003", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (noticeOfArrival.getDocumentYear().length() > 9) {
				status = false;
				logMessageValidation.setTagProperty("DocumentYear");
				logMessageValidation.setDataValidation("N30403: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N004", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// UserCreated
		if (noticeOfArrival.getUserCreated() == null) {
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N30104: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getUserCreated().length() > 14) {
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N30504: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(noticeOfArrival
						.getUserCreated())) {
			status = false;
			logMessageValidation.setTagProperty("UserCreated");
			logMessageValidation.setDataValidation("N30604: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// NameOfShip
		if (noticeOfArrival.getNameOfShip() == null) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N30105: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getNameOfShip().length() > 100) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N30505: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(noticeOfArrival
						.getNameOfShip())) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShip");
			logMessageValidation.setDataValidation("N30605: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// ShipTypeCode
		if (noticeOfArrival.getShipTypeCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N30106: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival
				.getShipTypeCode() != null && noticeOfArrival
						.getShipTypeCode().trim().length() > 0 && DmShipTypeLocalServiceUtil.getByShipTypeCode(noticeOfArrival
				.getShipTypeCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N30206: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getShipTypeCode().length() > 5) {
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N30506: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(noticeOfArrival
						.getShipTypeCode())) {
			status = false;
			logMessageValidation.setTagProperty("ShipTypeCode");
			logMessageValidation.setDataValidation("N30606: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// IMONumber
//		if (noticeOfArrival.getIMONumber() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("IMONumber");
//			logMessageValidation.setDataValidation("N30107: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (noticeOfArrival.getIMONumber().length() > 7) {
//			status = false;
//			logMessageValidation.setTagProperty("IMONumber");
//			logMessageValidation.setDataValidation("N30507: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(noticeOfArrival
//						.getIMONumber())) {
//			status = false;
//			logMessageValidation.setTagProperty("IMONumber");
//			logMessageValidation.setDataValidation("N30607: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//
//		}
		// CallSign
		if (noticeOfArrival.getCallSign() == null) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N30108: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getCallSign().length() > 9) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N30508: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(noticeOfArrival.getCallSign())) {
			status = false;
			logMessageValidation.setTagProperty("CallSign");
			logMessageValidation.setDataValidation("N30608: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// FlagStateOfShip
//		if (noticeOfArrival.getFlagStateOfShip() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("FlagStateOfShip");
//			logMessageValidation.setDataValidation("N30109: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (noticeOfArrival
//				.getFlagStateOfShip() != null && noticeOfArrival
//						.getFlagStateOfShip().trim().length() > 0 && DmStateLocalServiceUtil.getByStateCode(noticeOfArrival
//				.getFlagStateOfShip()) == null) {
//			status = false;
//			logMessageValidation.setTagProperty("FlagStateOfShip");
//			logMessageValidation.setDataValidation("N30209: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N002", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (noticeOfArrival.getFlagStateOfShip().length() > 2) {
//			status = false;
//			logMessageValidation.setTagProperty("FlagStateOfShip");
//			logMessageValidation.setDataValidation("N30509: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(noticeOfArrival
//						.getFlagStateOfShip())) {
//			status = false;
//			logMessageValidation.setTagProperty("FlagStateOfShip");
//			logMessageValidation.setDataValidation("N30609: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// NameOfMaster
		if (noticeOfArrival.getNameOfMaster() == null) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N30110: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getNameOfMaster().length() > 150) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N30510: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(noticeOfArrival
						.getNameOfMaster())) {
			status = false;
			logMessageValidation.setTagProperty("NameOfMaster");
			logMessageValidation.setDataValidation("N30610: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// PortOfArrivalCode
		if (noticeOfArrival.getPortOfArrivalCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N30111: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival
				.getPortOfArrivalCode() != null && noticeOfArrival
						.getPortOfArrivalCode().trim().length() >0 && DmPortLocalServiceUtil.getByPortCode(noticeOfArrival
				.getPortOfArrivalCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N30211: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getPortOfArrivalCode().length() > 5) {
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N30511: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(noticeOfArrival
						.getPortOfArrivalCode())) {
			status = false;
			logMessageValidation.setTagProperty("PortOfArrivalCode");
			logMessageValidation.setDataValidation("N30611: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// PortHarbourCode
		if (noticeOfArrival.getPortHarbourCode() != null
				&& noticeOfArrival.getPortHarbourCode().length() > 0) {
			if (noticeOfArrival.getPortHarbourCode()!= null && noticeOfArrival.getPortHarbourCode().trim().length() >0 && DmPortHarbourLocalServiceUtil
					.getByPortHarbourCode(noticeOfArrival.getPortHarbourCode()) == null) {
				status = false;
				logMessageValidation.setTagProperty("PortHarbourCode");
				logMessageValidation.setDataValidation("N30212: "
						+ noticeOfArrival.getPortHarbourCode()
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N002", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (noticeOfArrival.getPortHarbourCode().length() > 12) {
				status = false;
				logMessageValidation.setTagProperty("PortHarbourCode");
				logMessageValidation.setDataValidation("N30512: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getPortHarbourCode())) {
				status = false;
				logMessageValidation.setTagProperty("PortHarbourCode");
				logMessageValidation.setDataValidation("N30612: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// PortRegionCode
		if (noticeOfArrival.getPortRegionCode() != null
				&& noticeOfArrival.getPortRegionCode().length() > 0) {
			if (noticeOfArrival.getPortRegionCode().length() > 12) {
				status = false;
				logMessageValidation.setTagProperty("PortRegionCode");
				logMessageValidation.setDataValidation("N30513: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (noticeOfArrival.getPortRegionCode() != null && noticeOfArrival.getPortRegionCode().trim().length() >0 && DmPortRegionLocalServiceUtil
					.getByPortRegionCode(noticeOfArrival.getPortRegionCode()) == null) {
				status = false;
				logMessageValidation.setTagProperty("PortRegionCode");
				logMessageValidation.setDataValidation("N30213: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N002", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getPortRegionCode())) {
				status = false;
				logMessageValidation.setTagProperty("PortRegionCode");
				logMessageValidation.setDataValidation("N30613: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// PortWharfCode
		if (noticeOfArrival.getPortWharfCode() != null
				&& noticeOfArrival.getPortWharfCode().length() > 0) {
			if (DmPortWharfLocalServiceUtil.getByPortWharfCode(noticeOfArrival
					.getPortWharfCode()) == null) {
				status = false;
				logMessageValidation.setTagProperty("PortWharfCode");
				logMessageValidation.setDataValidation("N30214: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N002", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (noticeOfArrival.getPortWharfCode().length() > 12) {
				status = false;
				logMessageValidation.setTagProperty("PortWharfCode");
				logMessageValidation.setDataValidation("N30514: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getPortWharfCode())) {
				status = false;
				logMessageValidation.setTagProperty("PortWharfCode");
				logMessageValidation.setDataValidation("N30614: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// TimeOfArrival
		if (noticeOfArrival.getTimeOfArrival() == null) {
			status = false;
			logMessageValidation.setTagProperty("TimeOfArrival");
			logMessageValidation.setDataValidation("N30115: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!FormatData.isThisDateValid(noticeOfArrival
				.getTimeOfArrival())) {
			status = false;
			logMessageValidation.setTagProperty("TimeOfArrival");
			logMessageValidation.setDataValidation("N30715: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N007", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// LastPortOfCallCode
		if (noticeOfArrival.getLastPortOfCallCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("LastPortOfCallCode");
			logMessageValidation.setDataValidation("N30116: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival
				.getLastPortOfCallCode() != null && noticeOfArrival
						.getLastPortOfCallCode().trim().length() > 0 && DmPortLocalServiceUtil.getByPortCode(noticeOfArrival
				.getLastPortOfCallCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("LastPortOfCallCode");
			logMessageValidation.setDataValidation("N30216: "
					+ noticeOfArrival.getLastPortOfCallCode()
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getLastPortOfCallCode().length() > 5) {
			status = false;
			logMessageValidation.setTagProperty("LastPortOfCallCode");
			logMessageValidation.setDataValidation("N30516: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(noticeOfArrival
						.getLastPortOfCallCode())) {
			status = false;
			logMessageValidation.setTagProperty("LastPortOfCallCode");
			logMessageValidation.setDataValidation("N30616: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// CertificateOfRegistryNumber
//		if (noticeOfArrival.getCertificateOfRegistryNumber() != null
//				&& noticeOfArrival.getCertificateOfRegistryNumber().length() > 0) {
//			if (noticeOfArrival.getCertificateOfRegistryNumber().length() > 20) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("CertificateOfRegistryNumber");
//				logMessageValidation.setDataValidation("N30517: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(noticeOfArrival
//							.getCertificateOfRegistryNumber())) {
//				status = false;
//				logMessageValidation
//						.setTagProperty("CertificateOfRegistryNumber");
//				logMessageValidation.setDataValidation("N30617: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// CertificateOfRegistryDate
//		if (noticeOfArrival.getCertificateOfRegistryDate() != null
//				&& !FormatData.isThisDateValid(noticeOfArrival
//						.getCertificateOfRegistryDate())) {
//			status = false;
//			logMessageValidation.setTagProperty("CertificateOfRegistryDate");
//			logMessageValidation.setDataValidation("N30718: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N007", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// CertificateOfRegistryPortName
//		if (noticeOfArrival.getCertificateOfRegistryPortName() != null
//				&& noticeOfArrival.getCertificateOfRegistryPortName().length() > 0
//				&& DmPortLocalServiceUtil.getByPortCode(noticeOfArrival
//						.getCertificateOfRegistryPortName()) == null) {
//			status = false;
//			logMessageValidation
//					.setTagProperty("CertificateOfRegistryPortName");
//			logMessageValidation.setDataValidation("N30219: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N002", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (noticeOfArrival.getCertificateOfRegistryPortName().length() > 5) {
//			status = false;
//			logMessageValidation
//					.setTagProperty("CertificateOfRegistryPortName");
//			logMessageValidation.setDataValidation("N30519: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(noticeOfArrival
//						.getCertificateOfRegistryPortName())) {
//			status = false;
//			logMessageValidation
//					.setTagProperty("CertificateOfRegistryPortName");
//			logMessageValidation.setDataValidation("N30619: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// NameOfShipOwners
//		if (noticeOfArrival.getNameOfShipOwners() != null
//				&& noticeOfArrival.getNameOfShipOwners().length() > 0) {
//			if (noticeOfArrival.getNameOfShipOwners().length() > 255) {
//				status = false;
//				logMessageValidation.setTagProperty("NameOfShipOwners");
//				logMessageValidation.setDataValidation("N30520: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(noticeOfArrival
//							.getNameOfShipOwners())) {
//				status = false;
//				logMessageValidation.setTagProperty("NameOfShipOwners");
//				logMessageValidation.setDataValidation("N30620: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// AddressOfShipOwners
//		if (noticeOfArrival.getAddressOfShipOwners() != null
//				&& noticeOfArrival.getAddressOfShipOwners().length() > 0) {
//			if (noticeOfArrival.getAddressOfShipOwners().length() > 255) {
//				status = false;
//				logMessageValidation.setTagProperty("AddressOfShipOwners");
//				logMessageValidation.setDataValidation("N30521: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(noticeOfArrival
//							.getAddressOfShipOwners())) {
//				status = false;
//				logMessageValidation.setTagProperty("AddressOfShipOwners");
//				logMessageValidation.setDataValidation("N30621: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}

		// ShipOwnerStateCode
//		if (noticeOfArrival.getShipOwnerStateCode() != null
//				&& noticeOfArrival.getShipOwnerStateCode().length() > 2) {
//			status = false;
//			logMessageValidation.setTagProperty("ShipOwnerStateCode");
//			logMessageValidation.setDataValidation("N30522: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (noticeOfArrival.getShipOwnerStateCode() != null
//				&& DmStateLocalServiceUtil.getByStateCode(noticeOfArrival
//						.getShipOwnerStateCode()) == null) {
//			status = false;
//			logMessageValidation.setTagProperty("ShipOwnerStateCode");
//			logMessageValidation.setDataValidation("N30222: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N002", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (noticeOfArrival.getShipOwnerStateCode() != null
//				&& !ActionUtils
//						.checkIfIsAValidSpecialCharacters(noticeOfArrival
//								.getShipOwnerStateCode())) {
//			status = false;
//			logMessageValidation.setTagProperty("ShipOwnerStateCode");
//			logMessageValidation.setDataValidation("N30622: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}

		// ShipOwnerPhone
//		if (noticeOfArrival.getShipOwnerPhone() != null
//				&& noticeOfArrival.getShipOwnerPhone().length() > 0) {
//			if (noticeOfArrival.getShipOwnerPhone().length() > 100) {
//				status = false;
//				logMessageValidation.setTagProperty("ShipOwnerPhone");
//				logMessageValidation.setDataValidation("N30523: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(noticeOfArrival
//							.getShipOwnerPhone())) {
//				status = false;
//				logMessageValidation.setTagProperty("ShipOwnerPhone");
//				logMessageValidation.setDataValidation("N30623: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// ShipOwnerFax
//		if (noticeOfArrival.getShipOwnerFax() != null
//				&& noticeOfArrival.getShipOwnerFax().length() > 0) {
//			if (noticeOfArrival.getShipOwnerFax().length() > 100) {
//				status = false;
//				logMessageValidation.setTagProperty("ShipOwnerFax");
//				logMessageValidation.setDataValidation("N30524: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(noticeOfArrival
//							.getShipOwnerFax())) {
//				status = false;
//				logMessageValidation.setTagProperty("ShipOwnerFax");
//				logMessageValidation.setDataValidation("N30624: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// ShipOwnerEmail
//		if (noticeOfArrival.getShipOwnerEmail() != null
//				&& noticeOfArrival.getShipOwnerEmail().length() > 0) {
//			if (noticeOfArrival.getShipOwnerEmail().length() > 50) {
//				status = false;
//				logMessageValidation.setTagProperty("ShipOwnerEmail");
//				logMessageValidation.setDataValidation("N30525: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			} else if (!ActionUtils.checkIfIsAValidEmail(noticeOfArrival
//					.getShipOwnerEmail())) {
//				status = false;
//				logMessageValidation.setTagProperty("ShipOwnerEmail");
//				logMessageValidation.setDataValidation("N30825: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N008", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// LOA
//		if (noticeOfArrival.getLOA() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("LOA");
//			logMessageValidation.setDataValidation("N30126: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkIfIsDouble(noticeOfArrival.getLOA())) {
//			status = false;
//			logMessageValidation.setTagProperty("LOA");
//			logMessageValidation.setDataValidation("N30326: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N003", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkFormatDouble(noticeOfArrival.getLOA(), 3,
//				2)) {
//			status = false;
//			logMessageValidation.setTagProperty("LOA");
//			logMessageValidation.setDataValidation("N30426: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N004", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// LOAUNIT
//		if (noticeOfArrival.getLOAUNIT() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("LOAUNIT");
//			logMessageValidation.setDataValidation("N30127: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//
//		} else if (noticeOfArrival.getLOAUNIT().length() > 4) {
//			status = false;
//			logMessageValidation.setTagProperty("LOAUNIT");
//			logMessageValidation.setDataValidation("N30527: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N005", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//
//		} else if (!ActionUtils
//				.checkIfIsAValidSpecialCharacters(noticeOfArrival.getLOAUNIT())) {
//			status = false;
//			logMessageValidation.setTagProperty("LOAUNIT");
//			logMessageValidation.setDataValidation("N30627: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N006", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// Breadth
//		if (noticeOfArrival.getBreadth() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("Breadth");
//			logMessageValidation.setDataValidation("N30128: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkIfIsDouble(noticeOfArrival.getBreadth())) {
//			status = false;
//			logMessageValidation.setTagProperty("Breadth");
//			logMessageValidation.setDataValidation("N30328: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N003", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkFormatDouble(noticeOfArrival.getBreadth(),
//				3, 2)) {
//			status = false;
//			logMessageValidation.setTagProperty("Breadth");
//			logMessageValidation.setDataValidation("N30428: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N004", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// BreadthUnit
//		if (noticeOfArrival.getBreadthUnit() != null
//				&& noticeOfArrival.getBreadthUnit().length() > 0) {
//			if (noticeOfArrival.getBreadthUnit().length() > 4) {
//				status = false;
//				logMessageValidation.setTagProperty("BreadthUnit");
//				logMessageValidation.setDataValidation("N30529: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(noticeOfArrival
//							.getBreadthUnit())) {
//				status = false;
//				logMessageValidation.setTagProperty("BreadthUnit");
//				logMessageValidation.setDataValidation("N30629: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// ClearanceHeight
		if (noticeOfArrival.getClearanceHeight() == null) {
			status = false;
			logMessageValidation.setTagProperty("ClearanceHeight");
			logMessageValidation.setDataValidation("N30130: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsDouble(noticeOfArrival
				.getClearanceHeight())) {
			status = false;
			logMessageValidation.setTagProperty("ClearanceHeight");
			logMessageValidation.setDataValidation("N30330: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkFormatDouble(
				noticeOfArrival.getClearanceHeight(), 3, 2)) {
			status = false;
			logMessageValidation.setTagProperty("ClearanceHeight");
			logMessageValidation.setDataValidation("N30430: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// ClearanceHeightUnit
		if (noticeOfArrival.getClearanceHeightUnit() != null
				&& noticeOfArrival.getClearanceHeightUnit().length() > 0) {
			if (noticeOfArrival.getClearanceHeightUnit().length() > 4) {
				status = false;
				logMessageValidation.setTagProperty("ClearanceHeightUnit");
				logMessageValidation.setDataValidation("N30531: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);

			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getClearanceHeightUnit())) {
				status = false;
				logMessageValidation.setTagProperty("ClearanceHeightUnit");
				logMessageValidation.setDataValidation("N30631: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// ShownDraftxF
		if (noticeOfArrival.getShownDraftxF() != null
				&& noticeOfArrival.getShownDraftxF().length() > 0) {
			if (!ActionUtils.checkIfIsDouble(noticeOfArrival.getShownDraftxF())) {
				status = false;
				logMessageValidation.setTagProperty("ShownDraftxF");
				logMessageValidation.setDataValidation("N30332: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N003", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils.checkFormatDouble(
					noticeOfArrival.getShownDraftxF(), 3, 2)) {
				status = false;
				logMessageValidation.setTagProperty("ShownDraftxF");
				logMessageValidation.setDataValidation("N30432: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N004", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// UnitShownDraftxF
		if (noticeOfArrival.getUnitShownDraftxF() != null
				&& noticeOfArrival.getUnitShownDraftxF().length() > 0) {
			if (noticeOfArrival.getUnitShownDraftxF().length() > 4) {
				status = false;
				logMessageValidation.setTagProperty("UnitShownDraftxF");
				logMessageValidation.setDataValidation("N30533: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);

			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getUnitShownDraftxF())) {
				status = false;
				logMessageValidation.setTagProperty("UnitShownDraftxF");
				logMessageValidation.setDataValidation("N30633: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// ShownDraftxA
		if (noticeOfArrival.getShownDraftxA() != null
				&& noticeOfArrival.getShownDraftxA().length() > 0) {
			if (!ActionUtils.checkIfIsDouble(noticeOfArrival.getShownDraftxA())) {
				status = false;
				logMessageValidation.setTagProperty("ShownDraftxA");
				logMessageValidation.setDataValidation("N30334: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N003", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils.checkFormatDouble(
					noticeOfArrival.getShownDraftxA(), 3, 2)) {
				status = false;
				logMessageValidation.setTagProperty("ShownDraftxA");
				logMessageValidation.setDataValidation("N30434: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N004", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// UnitShownDraftxA
		if (noticeOfArrival.getUnitShownDraftxA() != null
				&& noticeOfArrival.getUnitShownDraftxA().length() > 0) {
			if (noticeOfArrival.getUnitShownDraftxA().length() > 4) {
				status = false;
				logMessageValidation.setTagProperty("UnitShownDraftxA");
				logMessageValidation.setDataValidation("N30535: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);

			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getUnitShownDraftxA())) {
				status = false;
				logMessageValidation.setTagProperty("UnitShownDraftxA");
				logMessageValidation.setDataValidation("N30635: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// GT
//		if (noticeOfArrival.getGT() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("GT");
//			logMessageValidation.setDataValidation("N30136: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkIfIsDouble(noticeOfArrival.getGT())) {
//			status = false;
//			logMessageValidation.setTagProperty("GT");
//			logMessageValidation.setDataValidation("N30336: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N003", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils
//				.checkFormatDouble(noticeOfArrival.getGT(), 6, 2)) {
//			status = false;
//			logMessageValidation.setTagProperty("GT");
//			logMessageValidation.setDataValidation("N30436: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N004", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// GTUNIT
//		if (noticeOfArrival.getGTUNIT() != null
//				&& noticeOfArrival.getGTUNIT().length() > 0) {
//			if (noticeOfArrival.getGTUNIT().length() > 4) {
//				status = false;
//				logMessageValidation.setTagProperty("GTUNIT");
//				logMessageValidation.setDataValidation("N30537: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(noticeOfArrival
//							.getGTUNIT())) {
//				status = false;
//				logMessageValidation.setTagProperty("GTUNIT");
//				logMessageValidation.setDataValidation("N30637: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// DWT
//		if (noticeOfArrival.getDWT() == null) {
//			status = false;
//			logMessageValidation.setTagProperty("DWT");
//			logMessageValidation.setDataValidation("N30138: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N001", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkIfIsDouble(noticeOfArrival.getDWT())) {
//			status = false;
//			logMessageValidation.setTagProperty("DWT");
//			logMessageValidation.setDataValidation("N30338: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N003", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		} else if (!ActionUtils.checkFormatDouble(noticeOfArrival.getDWT(), 6,
//				2)) {
//			status = false;
//			logMessageValidation.setTagProperty("DWT");
//			logMessageValidation.setDataValidation("N30438: "
//					+ ConfigurationManager.getStrProp(
//							"vn.gt.logMessageValidation.N004", ""));
//			ValidationUtils.addLogMessageValidation(logMessageValidation);
//		}
		// DWTUNIT
//		if (noticeOfArrival.getDWTUNIT() != null
//				&& noticeOfArrival.getDWTUNIT().length() > 0) {
//			if (noticeOfArrival.getDWTUNIT().length() > 4) {
//				status = false;
//				logMessageValidation.setTagProperty("DWTUNIT");
//				logMessageValidation.setDataValidation("N30539: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N005", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//
//			} else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(noticeOfArrival
//							.getDWTUNIT())) {
//				status = false;
//				logMessageValidation.setTagProperty("DWTUNIT");
//				logMessageValidation.setDataValidation("N30639: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
//		}
		// TaxCodeOfShipownersAgents
		if (noticeOfArrival.getTaxCodeOfShipownersAgents() != null
				&& noticeOfArrival.getTaxCodeOfShipownersAgents().length() > 0) {
			if (noticeOfArrival.getTaxCodeOfShipownersAgents().length() > 14) {
				status = false;
				logMessageValidation
						.setTagProperty("TaxCodeOfShipownersAgents");
				logMessageValidation.setDataValidation("N30540: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getTaxCodeOfShipownersAgents())) {
				status = false;
				logMessageValidation
						.setTagProperty("TaxCodeOfShipownersAgents");
				logMessageValidation.setDataValidation("N30640: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// NameOfShipownersAgents
		if (noticeOfArrival.getNameOfShipownersAgents() == null) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShipownersAgents");
			logMessageValidation.setDataValidation("N30141: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getNameOfShipownersAgents().length() > 250) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShipownersAgents");
			logMessageValidation.setDataValidation("N30541: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(noticeOfArrival
						.getNameOfShipownersAgents())) {
			status = false;
			logMessageValidation.setTagProperty("NameOfShipownersAgents");
			logMessageValidation.setDataValidation("N30641: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// ShipAgencyAddress
		if (noticeOfArrival.getShipAgencyAddress() != null
				&& noticeOfArrival.getShipAgencyAddress().length() > 0) {
			if (noticeOfArrival.getShipAgencyAddress().length() > 200) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyAddress");
				logMessageValidation.setDataValidation("N30542: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getShipAgencyAddress())) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyAddress");
				logMessageValidation.setDataValidation("N30642: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// ShipAgencyPhone
		if (noticeOfArrival.getShipAgencyPhone() != null
				&& noticeOfArrival.getShipAgencyPhone().length() > 0) {
			if (noticeOfArrival.getShipAgencyPhone().length() > 100) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyPhone");
				logMessageValidation.setDataValidation("N30543: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getShipAgencyPhone())) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyPhone");
				logMessageValidation.setDataValidation("N30643: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// ShipAgencyFax
		if (noticeOfArrival.getShipAgencyFax() != null
				&& noticeOfArrival.getShipAgencyFax().length() > 0) {
			if (noticeOfArrival.getShipAgencyFax().length() > 100) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyFax");
				logMessageValidation.setDataValidation("N30544: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getShipAgencyFax())) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyFax");
				logMessageValidation.setDataValidation("N30644: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// ShipAgencyEmail
		if (noticeOfArrival.getShipAgencyEmail() != null
				&& noticeOfArrival.getShipAgencyEmail().length() > 0) {
			if (noticeOfArrival.getShipAgencyEmail().length() > 50) {
				status = false;
				logMessageValidation.setTagProperty("ShipAgencyEmail");
				logMessageValidation.setDataValidation("N30545: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} 
//			else if (!ActionUtils
//					.checkIfIsAValidSpecialCharacters(noticeOfArrival
//							.getShipAgencyEmail())) {
//				status = false;
//				logMessageValidation.setTagProperty("ShipAgencyEmail");
//				logMessageValidation.setDataValidation("N30645: "
//						+ ConfigurationManager.getStrProp(
//								"vn.gt.logMessageValidation.N006", ""));
//				ValidationUtils.addLogMessageValidation(logMessageValidation);
//			}
		}
		// PurposeCode
		if (noticeOfArrival.getPurposeCode() == null) {
			status = false;
			logMessageValidation.setTagProperty("PurposeCode");
			logMessageValidation.setDataValidation("N30146: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (DmArrivalPurposeLocalServiceUtil
				.getByPortCode(noticeOfArrival.getPurposeCode()) == null) {
			status = false;
			logMessageValidation.setTagProperty("PurposeCode");
			logMessageValidation.setDataValidation("N30246: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N002", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getPurposeCode().length() > 250) {
			status = false;
			logMessageValidation.setTagProperty("PurposeCode");
			logMessageValidation.setDataValidation("N30546: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N005", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsAValidSpecialCharacters(noticeOfArrival
						.getPurposeCode())) {
			status = false;
			logMessageValidation.setTagProperty("PurposeCode");
			logMessageValidation.setDataValidation("N30646: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N006", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// QuantityOfCargo
		if (noticeOfArrival.getQuantityOfCargo() != null
				&& noticeOfArrival.getQuantityOfCargo().length() > 0) {
			if (noticeOfArrival.getQuantityOfCargo().length() > 150) {
				status = false;
				logMessageValidation.setTagProperty("QuantityOfCargo");
				logMessageValidation.setDataValidation("N30547: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getQuantityOfCargo())) {
				status = false;
				logMessageValidation.setTagProperty("QuantityOfCargo");
				logMessageValidation.setDataValidation("N30647: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}

		// UnitQuantityofCargo
		if (noticeOfArrival.getUnitQuantityofCargo() != null
				&& noticeOfArrival.getUnitQuantityofCargo().length() > 0) {
			if (DmUnitGeneralLocalServiceUtil.getByUnitCode(noticeOfArrival
					.getUnitQuantityofCargo()) == null) {
				status = false;
				logMessageValidation.setTagProperty("UnitQuantityofCargo");
				logMessageValidation.setDataValidation("N30248: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N002", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (noticeOfArrival.getUnitQuantityofCargo().length() > 12) {
				status = false;
				logMessageValidation.setTagProperty("UnitQuantityofCargo");
				logMessageValidation.setDataValidation("N30548: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getUnitQuantityofCargo())) {
				status = false;
				logMessageValidation.setTagProperty("UnitQuantityofCargo");
				logMessageValidation.setDataValidation("N30648: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}

		// TypeOfCargo
		if (noticeOfArrival.getTypeOfCargo() != null
				&& noticeOfArrival.getTypeOfCargo().length() > 0) {
			if (DmGoodsTypeLocalServiceUtil.getByGoodsTypeCode(noticeOfArrival
					.getTypeOfCargo()) == null) {
				status = false;
				logMessageValidation.setTagProperty("TypeOfCargo");
				logMessageValidation.setDataValidation("N30249: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N002", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (noticeOfArrival.getTypeOfCargo().length() > 12) {
				status = false;
				logMessageValidation.setTagProperty("TypeOfCargo");
				logMessageValidation.setDataValidation("N30549: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getTypeOfCargo())) {
				status = false;
				logMessageValidation.setTagProperty("TypeOfCargo");
				logMessageValidation.setDataValidation("N30649: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// NumberOfCrew
		if (noticeOfArrival.getNumberOfCrew() == null) {
			status = false;
			logMessageValidation.setTagProperty("NumberOfCrew");
			logMessageValidation.setDataValidation("N30150: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils
				.checkIfIsLong(noticeOfArrival.getNumberOfCrew())) {
			status = false;
			logMessageValidation.setTagProperty("Breadth");
			logMessageValidation.setDataValidation("N30350: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getNumberOfCrew().length() > 9) {
			status = false;
			logMessageValidation.setTagProperty("NumberOfCrew");
			logMessageValidation.setDataValidation("N30450: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// NumberOfPassengers
		if (noticeOfArrival.getNumberOfPassengers() == null) {
			status = false;
			logMessageValidation.setTagProperty("NumberOfPassengers");
			logMessageValidation.setDataValidation("N30151: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N001", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (!ActionUtils.checkIfIsLong(noticeOfArrival
				.getNumberOfPassengers())) {
			status = false;
			logMessageValidation.setTagProperty("NumberOfPassengers");
			logMessageValidation.setDataValidation("N30351: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N003", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		} else if (noticeOfArrival.getNumberOfPassengers().length() > 9) {
			status = false;
			logMessageValidation.setTagProperty("NumberOfPassengers");
			logMessageValidation.setDataValidation("N30451: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N004", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// OtherPersons
		if (noticeOfArrival.getOtherPersons() != null
				&& noticeOfArrival.getOtherPersons().length() > 0) {
			if (noticeOfArrival.getOtherPersons().length() > 500) {
				status = false;
				logMessageValidation.setTagProperty("OtherPersons");
				logMessageValidation.setDataValidation("N30552: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getOtherPersons())) {
				status = false;
				logMessageValidation.setTagProperty("OtherPersons");
				logMessageValidation.setDataValidation("N30652: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// Remarks
		if (noticeOfArrival.getRemarks() != null
				&& noticeOfArrival.getRemarks().length() > 0) {
			if (noticeOfArrival.getRemarks().length() > 500) {
				status = false;
				logMessageValidation.setTagProperty("Remarks");
				logMessageValidation.setDataValidation("N30553: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getRemarks())) {
				status = false;
				logMessageValidation.setTagProperty("Remarks");
				logMessageValidation.setDataValidation("N30653: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// SignPlace
		if (noticeOfArrival.getSignPlace() != null
				&& noticeOfArrival.getSignPlace().length() > 0) {
			if (noticeOfArrival.getSignPlace().length() > 50) {
				status = false;
				logMessageValidation.setTagProperty("SignPlace");
				logMessageValidation.setDataValidation("N30554: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N005", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (!ActionUtils
					.checkIfIsAValidSpecialCharacters(noticeOfArrival
							.getSignPlace())) {
				status = false;
				logMessageValidation.setTagProperty("SignPlace");
				logMessageValidation.setDataValidation("N30654: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N006", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		// SignDate
		if (noticeOfArrival.getSignDate() != null
				&& !FormatData.isThisDateValid(noticeOfArrival.getSignDate())) {
			status = false;
			logMessageValidation.setTagProperty("SignDate");
			logMessageValidation.setDataValidation("N30755: "
					+ ConfigurationManager.getStrProp(
							"vn.gt.logMessageValidation.N007", ""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		// MasterSigned
		if (noticeOfArrival.getMasterSigned() != null
				&& noticeOfArrival.getMasterSigned().length() > 0) {
			if (!ActionUtils.checkIfIsLong(noticeOfArrival.getMasterSigned())) {
				status = false;
				logMessageValidation.setTagProperty("MasterSigned");
				logMessageValidation.setDataValidation("N30356: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N003", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			} else if (noticeOfArrival.getMasterSigned().length() > 9) {
				status = false;
				logMessageValidation.setTagProperty("MasterSigned");
				logMessageValidation.setDataValidation("N30456: "
						+ ConfigurationManager.getStrProp(
								"vn.gt.logMessageValidation.N004", ""));
				ValidationUtils.addLogMessageValidation(logMessageValidation);
			}
		}
		return status;
	}
}
