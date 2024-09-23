package vn.gt.tichhop.message;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import vn.gt.constant.Constants;
import com.fds.nsw.nghiepvu.model.UserPort;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPort;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import com.fds.nsw.nghiepvu.model.DmShipAgency;
import com.fds.nsw.nghiepvu.model.DmState;
import com.fds.nsw.nghiepvu.model.DmVmaShipOwner;

import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaItineraryRemark;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaShip;




import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderChanelLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryRemarksLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaShipLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionBalanceLocalServiceUtil;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;
import vn.nsw.Header;
import vn.nsw.model.ConfirmationOfArrival;
import vn.nsw.model.GeneralDeclaration;
import vn.nsw.model.NoticeOfArrival;

import org.json.JSONArray;
import org.json.JSONException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

@Slf4j
public class DongBoVMABussinessUtils {
	

	// Dong Bo cac bang VMA khi nhan msg tu NSW va tu CV
	public static void updateNghiepVu(Header header, List<Object> liObjects) {
		List<TempDocument> liTempDocuments = TempDocumentLocalServiceUtil
				.findTemDocumentByDocumentNameAndDocumentYear(header
						.getSubject().getReference(), header.getSubject()
						.getDocumentYear());

		TempDocument tempDocument = null;
		if (liTempDocuments != null && liTempDocuments.size() > 0) {
			tempDocument = liTempDocuments.get(0);

			for (Object object : liObjects) {
				try {
					log.info("==initialVmaItinerary==NoticeOfArrival, GeneralDeclaration, ConfirmationOfArrival");

					if (object instanceof NoticeOfArrival
							|| object instanceof GeneralDeclaration
							|| object instanceof ConfirmationOfArrival) {

						setHanhTrinhTau(tempDocument.getDocumentName(),
								tempDocument.getDocumentYear(), object);
					}

				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
	}

	public static JSONObject setHanhTrinhTau(long documentName,
			int documentYear, Object objects)
			throws com.fds.nsw.kernel.exception.SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaItinerary objNewItinerary = new VmaItinerary();
			VmaItinerary objLastItinerary = new VmaItinerary();
			VmaItineraryRemark objItineraryRemarks = new VmaItineraryRemark();

			VmaShip objShip = new VmaShip();
			int foundShip = 0;
			TempDocument tempDoc = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);
			if (Validator.isNotNull(tempDoc)
					&& tempDoc.getShipName().length() > 0) {
				// (IMO) number is a unique identifier for vessels
				// it consists of the three letters "IMO" followed by the
				// seven-digit number
				if (tempDoc.getImo().trim().length() >= 7) {
					// findBy IMO, CallSign together
					// foundShip =
					// VmaShipLocalServiceUtil.countByIMONumber_CallSign(tempDoc.getImo(),
					// tempDoc.getCallSign());
					objShip = VmaShipLocalServiceUtil
							.fetchByIMONumber_CallSign(tempDoc.getImo(),
									tempDoc.getCallSign());
				} else {
					// findBy CallSign only
					// foundShip =
					// VmaShipLocalServiceUtil.countByCallSign(tempDoc.getCallSign());
					objShip = VmaShipLocalServiceUtil.fetchByCallSign(tempDoc
							.getCallSign());
				}
			} else {
				result.put("ItineraryNo", 0);
				return result;
			}

			List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
					.findBydocumentNameAndDocumentYearAndNoticeShipType(
							documentName, documentYear,
							PageType.TYPE_THONG_BAO_TAU_DEN_CANG);
			String sVoyageNumber = StringPool.BLANK; // So chuyen di, ket noi 2
														// luot vao, roi

			if (tempNoTiceShipMessages != null
					&& tempNoTiceShipMessages.size() > 0) {
				TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
						.get(0);
				sVoyageNumber = tempNoTiceShipMessage.getVoyageNumber();
			}
			// Kiem tra 03 yeu to nghiep vu:
			// 1. Xac dinh Thong tin tau: khoi tao thong tin tau moi, chuyen tau
			// truoc (neu can)
			// 2. Canh bao hanh trinh backdate
			// 3. Loai bo thua hanh trinh, xac dinh hanh trinh bang VoyageNumber

			// 1. Xac dinh Thong tin tau
			// Edit by Dungnv - Them dieu kien
			int flagUpdateVmaShip = 0;
			if (objShip == null && tempNoTiceShipMessages != null
					&& tempNoTiceShipMessages.size() > 0) {
				// Must init VmaShip;

				// Set VmaShip
				objShip = new VmaShip();
				flagUpdateVmaShip = 1;
			} else if (objShip != null && tempNoTiceShipMessages != null
					&& tempNoTiceShipMessages.size() > 0) {
				flagUpdateVmaShip = 2;
			} else {
				flagUpdateVmaShip = 0;
			}
			// Edit by Dungnv
			objShip.setMtgateway(1);
			objShip.setMaritimeCode(tempDoc.getMaritimeCode());
			objShip.setShipName(tempDoc.getShipName().trim());
			objShip.setShipPreviousName(null);
			objShip.setShipTypeMT(tempDoc.getShipTypeCode());
			objShip.setShipTypeCode(tempDoc.getShipTypeCode());
			objShip.setShipBoat((tempDoc.getImo().trim().length() >= 7) ? "SHIP"
					: "BOAT");
			objShip.setHasTugBoat(0); // PTTND
			objShip.setTugBoatName(""); // PTTND
			objShip.setNameOfMaster(tempDoc.getShipCaptain());
			objShip.setChiefOfEngineer(""); // PTTND
			objShip.setImoNumber(tempDoc.getImo());
			objShip.setCallSign(tempDoc.getCallSign());
			objShip.setFlagStateOfShip(tempDoc.getStateCode());
			objShip.setVrCode((tempDoc.getImo().trim().length() >= 7) ? "VR"
					: "VR-SB");
			// objShip.setPower // PTTND
			// objShip.setUnitPower // PTTND
			// objShip.setProductionCountry // PTTND
			// objShip.setProductionYear // PTTND
			// objShip.setExpiredDate // PTTND

			objShip.setGt(BigDecimal.valueOf(tempDoc.getGrt()));
			objShip.setNt(BigDecimal.valueOf(tempDoc.getNt()));
			objShip.setDwt(BigDecimal.valueOf(tempDoc.getDwt()));
			if (tempNoTiceShipMessages != null
					&& tempNoTiceShipMessages.size() > 0) {
				TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
						.get(0);
				objShip.setRegistryNumber(tempNoTiceShipMessage
						.getCertificateOfRegistryNumber());
				objShip.setRegistryDate(tempNoTiceShipMessage
						.getCertificateOfRegistryDate());
				objShip.setRegistryPortCode(tempNoTiceShipMessage
						.getCertificateOfRegistryPortName());
				objShip.setLoa(tempNoTiceShipMessage.getLoa());
				objShip.setBreadth(tempNoTiceShipMessage.getBreadth());
				objShip.setClearanceHeight(tempNoTiceShipMessage
						.getClearanceHeight());
				
				objShip.setShownDraftxF(tempNoTiceShipMessage.getShownDraftxF());
				objShip.setShownDraftxA(tempNoTiceShipMessage.getShownDraftxA());
			}

			objShip.setShipOwnerCode(tempDoc.getShipOwnerCode());
			objShip.setShipOperatorCode(tempDoc.getShipOwnerCode());
			objShip.setShipAgencyCode(tempDoc.getShipAgencyCode());

			objShip.setSecurityLevelCode("1"); // edit 18/10/2019 - Dungnv
			objShip.setRemarks("");
			objShip.setIsDelete(0);
			objShip.setMarkedAsDelete(0);
			objShip.setModifiedDate(new Date());
			objShip.setRequestedDate(new Date());
			objShip.setSyncVersion(1 + "");

			// ====== Edit

			if (flagUpdateVmaShip == 1) {
				VmaShipLocalServiceUtil.addVmaShip(objShip);
			} else if (flagUpdateVmaShip == 2) {
				VmaShipLocalServiceUtil.updateVmaShip(objShip);
			}

			if (Validator.isNotNull(objShip)
					&& objShip.getShipName().length() > 0) {
				// Tim kiem thong tin chuyen gan nhat, da ket thuc
				objLastItinerary = new VmaItinerary();
				objLastItinerary = VmaItineraryLocalServiceUtil
						.findVmaItineraryLeftByIMOandCallSign(null,
								tempDoc.getShipDateFrom(),
								tempDoc.getCallSign(), tempDoc.getImo(), "3");
				Date lastShipDateFrom = (Validator.isNotNull(objLastItinerary) ? objLastItinerary
						.getTimeOfDeparture() : new Date());

				// 3. Loai bo thua hanh trinh, xac dinh hanh trinh bang
				// VoyageNumber
				Boolean isNew = false;
				objNewItinerary = new VmaItinerary();
				/*
				 * objNewItinerary = VmaItineraryLocalServiceUtil
				 * .findVmaItineraryByVoyageIMOandCallSign(
				 * tempDoc.getMaritimeCode(), sVoyageNumber,
				 * tempDoc.getCallSign(), tempDoc.getImo(), "1,2");
				 */

				objNewItinerary = VmaItineraryLocalServiceUtil
						.fetchBydocumentNameIN_documentYearIN(documentName,
								documentYear);
				Boolean flagDeparture = true;

				if (Validator.isNotNull(objNewItinerary)) {
					if (objNewItinerary.getMarkedAsArrival() > 0
							|| objNewItinerary.getMarkedAsDeparture() > 0
							|| objNewItinerary.getMarkedAsTransmit() > 0
							|| objNewItinerary.getMarkedAsVoyage() > 0) {
						isNew = false;
					}
				} else {
					objNewItinerary = new VmaItinerary();
					if (tempDoc.getDocumentTypeCode().equalsIgnoreCase(
							MessageType.LOAT_THU_TUC_NHAP_CANH)
							|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									MessageType.LOAT_THU_TUC_VAO_CANG)
							|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									MessageType.LOAT_THU_TUC_QUA_CANH)
							|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									"6")
							|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									MessageType.LOAT_THU_TUC_8)
							|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									MessageType.LOAT_THU_TUC_10)
							|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									MessageType.LOAT_THU_TUC_12)
							|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									MessageType.LOAT_THU_TUC_NHAP_CANH_PTTND)
							|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
						isNew = true;
					}
				}
				// Init VmaItinerary
				objNewItinerary.setMtgateway(1);
				objNewItinerary.setMaritimeCode(tempDoc.getMaritimeCode());

				objNewItinerary.setNameOfShip(tempDoc.getShipName());
				objNewItinerary.setFlagStateOfShip(tempDoc.getStateCode());
				objNewItinerary.setImoNumber(tempDoc.getImo());
				objNewItinerary.setCallSign(tempDoc.getCallSign());
				objNewItinerary
						.setVrCode((tempDoc.getImo().trim().length() >= 7) ? "VR"
								: "VR-SB");
				objNewItinerary.setRegistryNumber(objShip.getRegistryNumber()); // Edit by Dungnv

				objNewItinerary.setShipTypeCode(tempDoc.getShipTypeCode());
				objNewItinerary.setVmaShipTypeCode(tempDoc.getShipTypeCode());
				objNewItinerary.setShipCaptain(tempDoc.getShipCaptain());
				if (isNew == true) {
					String sItineraryNo = VmaItineraryLocalServiceUtil
							.getItineraryNoWithRule(tempDoc.getMaritimeCode());
					objNewItinerary.setItineraryNo(sItineraryNo);
					objNewItinerary.setShipPosition(1);
					objNewItinerary.setPortofAuthority("");
					objNewItinerary.setRepresentativeofAuthority("VP CANG VU");
				}

				if (Validator.isNotNull(tempDoc)
						&& tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_QUA_CANH)) {
					objNewItinerary.setMarkedAsTransmit(1);
					objNewItinerary.setMarkedAsDeparture(1);
					if(objNewItinerary.getMarkedAsArrival() < 1){
						objNewItinerary.setMarkedAsArrival(1);
					}
					
					objNewItinerary.setDocumentNameTRA(tempDoc
							.getDocumentName());
					objNewItinerary.setDocumentYearTRA(tempDoc
							.getDocumentYear());
					objNewItinerary
							.setDocumentNameIN(tempDoc.getDocumentName());
					objNewItinerary
							.setDocumentYearIN(tempDoc.getDocumentYear());
					objNewItinerary.setDocumentNameOUT(tempDoc
							.getDocumentName());
					objNewItinerary.setDocumentYearOUT(tempDoc
							.getDocumentYear());
					flagDeparture = false;
				}

				else if (tempDoc.getDocumentTypeCode().equalsIgnoreCase(
						MessageType.LOAT_THU_TUC_NHAP_CANH)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_VAO_CANG)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase("6")
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_8)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_10)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_12)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_NHAP_CANH_PTTND)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {

					objNewItinerary.setMarkedAsTransmit(0);
					if(objNewItinerary.getMarkedAsArrival() < 1){
						objNewItinerary.setMarkedAsArrival(1);
					}
					objNewItinerary
							.setDocumentNameIN(tempDoc.getDocumentName());
					objNewItinerary
							.setDocumentYearIN(tempDoc.getDocumentYear());
					flagDeparture = false;
				}

				else if (tempDoc.getDocumentTypeCode().equalsIgnoreCase(
						MessageType.LOAT_THU_TUC_XUAT_CANH)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_ROI_CANG)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase("7")
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_9)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_11)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_13)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_XUAT_CANH_PTTND)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {

					objNewItinerary.setMarkedAsTransmit(0);
					objNewItinerary.setMarkedAsDeparture(1);
					objNewItinerary.setDocumentNameOUT(tempDoc
							.getDocumentName());
					objNewItinerary.setDocumentYearOUT(tempDoc
							.getDocumentYear());

				}

				objNewItinerary.setMarkedAsVoyage(0);
				// objNewItinerary.setDocumentNameVOY
				// objNewItinerary.setDocumentYearVOY
				/*objNewItinerary.setShipOwnerCode(tempDoc.getShipOwnerCode());
				objNewItinerary.setShipOwnerName(tempDoc
						.getNameOfShipownersAgents());
				objNewItinerary.setShipOperatorCode(tempDoc.getShipOwnerCode());
				objNewItinerary.setShipOperatorName(tempDoc
						.getNameOfShipownersAgents());*/
				if (objNewItinerary.getMarkedAsTransmit() == 1
						|| objNewItinerary.getMarkedAsArrival() == 1) {
					objNewItinerary.setArrivalShipAgencyCode(tempDoc
							.getShipAgencyCode());
					objNewItinerary.setArrivalShipAgency(tempDoc
							.getNameOfShipownersAgents());
					// TODO check lai case nay
					// edit by Dungnv
					// objNewItinerary.setTimeOfArrival(tempDoc.getShipDateFrom());

					tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
							.findBydocumentNameAndDocumentYearAndNoticeShipType(
									documentName, documentYear,
									PageType.TYPE_THONG_BAO_TAU_DEN_CANG);
					if (tempNoTiceShipMessages != null
							&& tempNoTiceShipMessages.size() > 0
							&& objects instanceof NoticeOfArrival) {
						TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
								.get(0);
						// Lay thong bao cuoi cung
						objNewItinerary.setNameOfShip(tempNoTiceShipMessage
								.getShipName());
						objNewItinerary.setShipCaptain(tempNoTiceShipMessage
								.getShipCaptain());
						// Edit by Dungnv
						if (tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_NHAP_CANH)
								|| tempDoc
										.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_VAO_CANG)
								|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									MessageType.LOAT_THU_TUC_QUA_CANH)
								|| tempDoc.getDocumentTypeCode()
										.equalsIgnoreCase("6")
								|| tempDoc.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_8)
								|| tempDoc.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_10)
								|| tempDoc.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_12)
								|| tempDoc
										.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_NHAP_CANH_PTTND)
								|| tempDoc
										.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
							objNewItinerary
									.setTimeOfArrival(tempNoTiceShipMessage
											.getArrivalDate());
							// edit 18/10 - Dungnv
							objNewItinerary
									.setArrivalPortCode(tempNoTiceShipMessage
											.getArrivalPortCode());
							objNewItinerary
									.setLastPortCode(tempNoTiceShipMessage
											.getPortGoingToCode());
							objNewItinerary
									.setDischargedPorts(tempNoTiceShipMessage
											.getDischargedPorts());
						}
					}
					tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
							.findBydocumentNameAndDocumentYearAndNoticeShipType(
									documentName, documentYear,
									PageType.TYPE_XAC_BAO_TAU_DEN_CANG);
					if (tempNoTiceShipMessages != null
							&& tempNoTiceShipMessages.size() > 0
							&& objects instanceof ConfirmationOfArrival) {
						TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
								.get(0);
						// Lay xac bao cuoi cung
						objNewItinerary.setNameOfShip(tempNoTiceShipMessage
								.getShipName());
						objNewItinerary.setShipCaptain(tempNoTiceShipMessage
								.getShipCaptain());
						objNewItinerary.setTimeOfArrival(tempNoTiceShipMessage
								.getArrivalDate());

					}

					TempGeneralDeclaration banKhaiChungLast = TempGeneralDeclarationLocalServiceUtil
							.getLastByDocumentNameAndDocumentYear(documentName,
									documentYear);
					// Lay Ban khai chung cuoi cung
					if (Validator.isNotNull(banKhaiChungLast)
							&& banKhaiChungLast.getNameOfShip().length() > 0
							&& objects instanceof GeneralDeclaration) {

						objNewItinerary.setNameOfShip(banKhaiChungLast
								.getNameOfShip());
						objNewItinerary.setShipCaptain(banKhaiChungLast
								.getNameOfMaster());
						if (tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_NHAP_CANH)
								|| tempDoc
										.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_VAO_CANG)
								|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									MessageType.LOAT_THU_TUC_QUA_CANH)
								|| tempDoc.getDocumentTypeCode()
										.equalsIgnoreCase("6")
								|| tempDoc.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_8)
								|| tempDoc.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_10)
								|| tempDoc.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_12)
								|| tempDoc
										.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_NHAP_CANH_PTTND)
								|| tempDoc
										.getDocumentTypeCode()
										.equalsIgnoreCase(
												MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
							objNewItinerary.setTimeOfArrival(banKhaiChungLast
									.getDateOfArrival());
						}
					}

				}
				if (objNewItinerary.getMarkedAsTransmit() == 1
						|| objNewItinerary.getMarkedAsDeparture() == 1) {
					objNewItinerary.setDepartureShipAgencyCode(tempDoc
							.getShipAgencyCode());
					objNewItinerary.setDepartureShipAgency(tempDoc
							.getNameOfShipownersAgents());
					
					if (objNewItinerary.getMarkedAsDeparture() == 1) {
						tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
								.findBydocumentNameAndDocumentYearAndNoticeShipType(
										documentName, documentYear,
										PageType.TYPE_THONG_BAO_TAU_DEN_CANG);
						if (tempNoTiceShipMessages != null
								&& tempNoTiceShipMessages.size() > 0) {
							TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
									.get(0);
							// Lay thong bao cuoi cung
							objNewItinerary.setNameOfShip(tempNoTiceShipMessage
									.getShipName());
							objNewItinerary
									.setShipCaptain(tempNoTiceShipMessage
											.getShipCaptain());
							
							objNewItinerary
									.setArrivalPortCode(tempNoTiceShipMessage
											.getArrivalPortCode());
							objNewItinerary
									.setNextPortCode(tempNoTiceShipMessage
											.getPortGoingToCode());
							objNewItinerary
									.setDischargedPorts(tempNoTiceShipMessage
											.getDischargedPorts());
						}
					}

					TempGeneralDeclaration banKhaiChungLast = TempGeneralDeclarationLocalServiceUtil
							.getLastByDocumentNameAndDocumentYear(documentName,
									documentYear);
					// Lay Ban khai chung cuoi cung
					if (Validator.isNotNull(banKhaiChungLast)
							&& banKhaiChungLast.getNameOfShip().length() > 0) {
						objNewItinerary.setNameOfShip(banKhaiChungLast
								.getNameOfShip());
						objNewItinerary.setShipCaptain(banKhaiChungLast
								.getNameOfMaster());
					
					}
				}

				objNewItinerary.setModifiedDate(tempDoc.getCreatedDate());
				if (tempDoc.getDocumentTypeCode().equalsIgnoreCase(
						MessageType.LOAT_THU_TUC_NHAP_CANH)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_VAO_CANG)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
									MessageType.LOAT_THU_TUC_QUA_CANH)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase("6")
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_8)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_10)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_12)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_NHAP_CANH_PTTND)
						|| tempDoc.getDocumentTypeCode().equalsIgnoreCase(
								MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {

					if (isNew == true) {

						VmaItineraryLocalServiceUtil
								.addVmaItinerary(objNewItinerary);

					} else {
						System.out
								.println("====================================>>>>> DongBoVMABussinessUtils "
										+ objNewItinerary.getDocumentNameIN()
										+ "|"
										+ objNewItinerary.getDocumentYearIN()
										+ "-"
										+ objNewItinerary.getDocumentNameOUT()
										+ "|"
										+ objNewItinerary.getDocumentYearOUT());
						VmaItineraryLocalServiceUtil
								.updateVmaItinerary(objNewItinerary);
					}

				}

				Boolean flagBackdateAlert = false;
				if (flagDeparture == false) {
					if (objNewItinerary.getTimeOfArrival().before(
							lastShipDateFrom)) {
						flagBackdateAlert = true;
					}
				}
				// edit by Dungnv
				/*
				 * else if (flagDeparture == true) { if
				 * (objNewItinerary.getTimeOfDeparture().before(
				 * lastShipDateFrom)) { flagBackdateAlert = true; } }
				 */
				if (flagBackdateAlert == true) {
					// 1. Canh bao backdate hanh trinh.
					log.info("E01: Khong cho phep backdate hanh trinh...");
					String E01 = DmDataItemLocalServiceUtil
							.findByDataGroupIdAndCode0(100, "E01").getName();
					if (Validator.isNotNull(E01)) {
						E01 = E01
								+ (flagDeparture == false ? " ETA: "
										+ ReportFunction
												.parserDateToString3LT(objNewItinerary
														.getTimeOfArrival())
										: " ETD: "
												+ ReportFunction
														.parserDateToString3LT(objNewItinerary
																.getTimeOfDeparture()));
					} else {
						E01 = "E01: Khong cho phep backdate hanh trinh.";
					}
					objItineraryRemarks.setItineraryNo(objNewItinerary
							.getItineraryNo());
					objItineraryRemarks.setSequenceNo(1);
					objItineraryRemarks.setDocumentName(tempDoc
							.getDocumentName());
					objItineraryRemarks.setDocumentYear(tempDoc
							.getDocumentYear());
					objItineraryRemarks.setPortofAuthority(tempDoc
							.getMaritimeCode());
					objItineraryRemarks.setNameOfShip(tempDoc.getShipName());
					objItineraryRemarks.setNoticeShipType(1);
					objItineraryRemarks.setRequestDate(new Date());
					objItineraryRemarks.setRequestPerson("HE THONG");
					objItineraryRemarks.setRemarks(E01);
					objItineraryRemarks.setMarkedAsPending(0);
					objItineraryRemarks.setModifiedDate(new Date());

					VmaItineraryRemarksLocalServiceUtil
							.addVmaItineraryRemarks(objItineraryRemarks);
					/*
					 * result.put("ItineraryNo", 0); return result;
					 */

				}
			}

			result.put("ItineraryNo", objNewItinerary.getItineraryNo());
			// Add by TrungNT
			DmShipAgency dmShipAgency = DmShipAgencyLocalServiceUtil
					.getByShipAgencyCode(tempDoc.getShipAgencyCode());
			VmaTransactionBalanceLocalServiceUtil
					.autoInitVmaTransactionBalance(
							tempDoc.getMaritimeCode(),
							tempDoc.getShipAgencyCode(),
							dmShipAgency != null ? dmShipAgency
									.getShipAgencyNameVN() : StringPool.BLANK);

			VmaTransactionBalanceLocalServiceUtil
					.autoInitVmaTransactionBalance(tempDoc.getMaritimeCode(),
							String.valueOf(9999), "TAI KHOAN VANG LAI");
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("ItineraryNo", 0);
			log.info("E09: Loi khoi tao hanh trinh.");
		}

		return result;
	}

	/*
	 * public static JSONObject setInitScheduleTesting(long documentName, int
	 * documentYear) throws com.fds.nsw.kernel.exception.SystemException{
	 * JSONObject result = JSONFactoryUtil.createJSONObject(); try {
	 * result.put("ItineraryNo", 0);
	 * 
	 * } catch (Exception e) { log.error(e.getMessage()); result.put("ItineraryNo", 0);
	 * log.info("E15: Loi khoi tao Ke hoach thu tau."); }
	 * 
	 * return result; }
	 * 
	 * public static JSONObject setInitScheduleShifting(long documentName, int
	 * documentYear) throws com.fds.nsw.kernel.exception.SystemException{
	 * JSONObject result = JSONFactoryUtil.createJSONObject(); try {
	 * result.put("ItineraryNo", 0);
	 * 
	 * } catch (Exception e) { log.error(e.getMessage()); result.put("ItineraryNo", 0);
	 * log.info("E14: Loi khoi tao Ke hoach di chuyen (dieu dong)."); }
	 * 
	 * return result; }
	 * 
	 * 
	 * public static JSONObject setInitScheduleTugboat(long documentName, int
	 * documentYear) throws com.fds.nsw.kernel.exception.SystemException{
	 * JSONObject result = JSONFactoryUtil.createJSONObject(); try {
	 * result.put("ItineraryNo", 0);
	 * 
	 * } catch (Exception e) { log.error(e.getMessage()); result.put("ItineraryNo", 0);
	 * log.info("E13: Loi khoi tao Ke hoach su dung tau lai."); }
	 * 
	 * return result; }
	 * 
	 * 
	 * public static JSONObject setInitSchedulePilot(long documentName, int
	 * documentYear) throws com.fds.nsw.kernel.exception.SystemException{
	 * JSONObject result = JSONFactoryUtil.createJSONObject(); try {
	 * result.put("ItineraryNo", 0);
	 * 
	 * } catch (Exception e) { log.error(e.getMessage()); result.put("ItineraryNo", 0);
	 * log.info("E12: Loi khoi tao Ke hoach su dung hoa tieu."); }
	 * 
	 * return result; }
	 * 
	 * public static JSONObject setInitScheduleAnchorage(long documentName, int
	 * documentYear) throws com.fds.nsw.kernel.exception.SystemException{
	 * JSONObject result = JSONFactoryUtil.createJSONObject(); try {
	 * result.put("ItineraryNo", 0);
	 * 
	 * } catch (Exception e) { log.error(e.getMessage()); result.put("ItineraryNo", 0);
	 * log.info("E11: Loi khoi tao Ke hoach neo dau."); }
	 * 
	 * return result; }
	 */

	public static JSONObject setKhoiTaoKeHoach(long documentName,
			int documentYear, String sItineraryNo)
			throws com.fds.nsw.kernel.exception.SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			result.put("documentName", documentName + "");
			result.put("documentYear", documentYear);
			result.put("itineraryNo", sItineraryNo);

			VmaShip objShip = new VmaShip();

			TempDocument tempDoc = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);
			if (Validator.isNotNull(tempDoc)
					&& tempDoc.getShipName().length() > 0) {
				// (IMO) number is a unique identifier for vessels
				// it consists of the three letters "IMO" followed by the
				// seven-digit number
				if (tempDoc.getImo().trim().length() >= 7) {
					// findBy IMO, CallSign together
					objShip = VmaShipLocalServiceUtil
							.fetchByIMONumber_CallSign(tempDoc.getImo(),
									tempDoc.getCallSign());
				} else {
					// findBy CallSign only
					objShip = VmaShipLocalServiceUtil.fetchByCallSign(tempDoc
							.getCallSign());
				}
			} else {
				result.put("itineraryNo", 0);
				return result;
			}

			if (!(Validator.isNotNull(objShip) && objShip.getShipName()
					.length() > 0)) {
				result.put("itineraryNo", 0);
				return result;
			}
			result.put("shipBoat", objShip.getShipBoat());
			result.put("nameOfShip", objShip.getShipName());
			result.put("shipOwnerCode", objShip.getShipOwnerCode());
			
			if (Validator.isNotNull(objShip.getShipOwnerCode())) {
				
				try {
					DmVmaShipOwner objVmaShipOwner = DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(objShip.getShipOwnerCode());
					if (Validator.isNotNull(objVmaShipOwner)) {
						result.put("shipOwnersName", objVmaShipOwner.getCompanyName());
					}
				} catch (Exception e) {
					
				}
				
			}
			
			//result.put("shipOwnersName", 0);
			result.put("shipOperatorCode", objShip.getShipOperatorCode());
			
			if (Validator.isNotNull(objShip.getShipOperatorCode())) {
				
				try {
					DmVmaShipOwner objVmaShipOperator = DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(objShip.getShipOperatorCode());
					if (Validator.isNotNull(objVmaShipOperator)) {
						result.put("shipOperatorName", objVmaShipOperator.getCompanyName());
					}
				} catch (Exception e) {
					
				}
			}
			// result.put("shipOperatorName", 0);
			result.put("shipPreviousName", objShip.getShipPreviousName());
			result.put("shipTypeMT", objShip.getShipTypeMT());
			result.put("shipTypeCode", objShip.getShipTypeCode());
			result.put("shipBoat", objShip.getShipBoat());
			result.put("hasTugBoat", objShip.getHasTugBoat());
			result.put("tugBoatName", objShip.getTugBoatName());
			result.put("nameOfMaster", objShip.getNameOfMaster());
			result.put("chiefOfEngineer", objShip.getChiefOfEngineer());
			result.put("imoNumber", objShip.getImoNumber());
			result.put("callSign", objShip.getCallSign());
			result.put("flagStateOfShip", objShip.getFlagStateOfShip());
			result.put("vrCode", objShip.getVrCode());
			result.put("registryNumber", objShip.getRegistryNumber());
			result.put("deconstructed", objShip.getDeconstructed());
			result.put("deconstructionShipyardCode",
					objShip.getDeconstructionShipyardCode());
			result.put("constructionShipyardCod",
					objShip.getConstructionShipyardCode());
			result.put("expiredDate", FormatData.parseDateToTringDDMMYYY(objShip.getExpiredDate()));
			result.put(
					"registryDate",
					objShip.getRegistryDate() != null ? FormatData.formatDateShort3
							.format(objShip.getRegistryDate())
							: StringPool.BLANK);
			result.put("registryPortCode", objShip.getRegistryPortCode());
			result.put("violated", objShip.getViolated());
			
			result.put("seat", objShip.getSeat());
			result.put("lies", objShip.getLies());
			result.put("passengers", objShip.getPassengers());
			result.put("craneload", objShip.getCraneload());
			result.put("unitCraneload", objShip.getUnitCraneload());

			
			
			
			TempShipSecurityMessage tempShipSecurityMessages = TempShipSecurityMessageLocalServiceUtil
					.getLastByDocumentNameAndDocumentYear(documentName,
							documentYear);

			if (tempShipSecurityMessages != null) {
				result.put("securityLevelCode",
						tempShipSecurityMessages.getSecurityLevelCode());
				objShip.setSecurityLevelCode(tempShipSecurityMessages
						.getSecurityLevelCode());
				result.put("purposeCode", tempShipSecurityMessages.getPurposeCode());
			}

			List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
					.findBydocumentNameAndDocumentYearAndNoticeShipType(
							documentName, documentYear,
							PageType.TYPE_THONG_BAO_TAU_DEN_CANG);
			String sVoyageNumber = StringPool.BLANK; // So chuyen di, ket noi 2
														// luot vao, roi
			TempNoticeShipMessage tempNoTiceShipMessage = new TempNoticeShipMessage();
			if (tempNoTiceShipMessages != null
					&& tempNoTiceShipMessages.size() > 0) {
				tempNoTiceShipMessage = tempNoTiceShipMessages.get(0);
				sVoyageNumber = tempNoTiceShipMessage.getVoyageNumber();

				// Edit by Dungnv
				String requestCode = tempNoTiceShipMessage.getRequestCode();
				List<TempCargoItems> tempCargoItems = TempCargoItemsLocalServiceUtil
						.findByRequestCode(requestCode);
				if (tempCargoItems != null && !tempCargoItems.isEmpty()) {
					JSONArray array = JSONFactoryUtil.createJSONArray();
					for (TempCargoItems item : tempCargoItems) {
						JSONObject obj = JSONFactoryUtil.createJSONObject();
						obj.put("requestCode", item.getRequestCode());
						obj.put("documentName", item.getDocumentName());
						obj.put("documentYear", item.getDocumentYear());
						obj.put("cargoCategory", item.getCargoCategory());
						obj.put("cargoType", item.getCargoType());
						obj.put("description", item.getDescription());
						obj.put("quantity", item.getQuantity());
						obj.put("unit", item.getUnit());
						obj.put("sequence", item.getSequence());
						obj.put("cargoCode", item.getCargoCode());
						array.put(obj);
					}
					result.put("tempCargoItems", array);
				}
				// ==============
			}
			if (!(Validator.isNotNull(tempNoTiceShipMessage) && tempNoTiceShipMessage
					.getShipName().length() > 0)) {
				result.put("itineraryNo", 0);
				return result;
			}

			if (tempNoTiceShipMessage.getIsArrival() == 1) {
				result.put("noticeShipType",
						MessageType.SHIP_POSSITION_DEN_CANG);

				result.put(
						"timeOfArrival",
						tempNoTiceShipMessage.getArrivalDate() != null ? FormatData.formatDateShort3
								.format(tempNoTiceShipMessage.getArrivalDate())
								: StringPool.BLANK);

			} else {

				result.put("noticeShipType",
						MessageType.SHIP_POSSITION_ROI_CANG);
				result.put(
						"timeOfDeparture",
						tempNoTiceShipMessage.getArrivalDate() != null ? FormatData.formatDateShort3
								.format(tempNoTiceShipMessage.getArrivalDate())
								: StringPool.BLANK);
				result.put(
						"timeOfArrival",
						tempNoTiceShipMessage.getArrivalDate() != null ? FormatData.formatDateShort3
								.format(tempNoTiceShipMessage.getArrivalDate())
								: StringPool.BLANK);

			}

			result.put("shipAgencyCode",
					tempNoTiceShipMessage.getShipAgencyCode());
			result.put("shipAgencyName",
					tempNoTiceShipMessage.getNameOfShipAgent());
			result.put("shipAgencyAddress",
					tempNoTiceShipMessage.getShipAgencyAddress());
			result.put("shipAgencyContactEmail",
					tempNoTiceShipMessage.getShipAgencyEmail());
			result.put("shipAgencyPhone",
					tempNoTiceShipMessage.getShipAgencyPhone());
			result.put("shipAgencyFax",
					tempNoTiceShipMessage.getShipAgencyFax());
			//result.put("securityLevelCode", 0);
			result.put("arrivalPortCode",
					tempNoTiceShipMessage.getArrivalPortCode());
			result.put("portRegionCode",
					tempNoTiceShipMessage.getPortRegionCode());
			result.put("portHarbourCode",
					tempNoTiceShipMessage.getPortHarbourCode());
			result.put("portWharfCode",
					tempNoTiceShipMessage.getPortWharfCode());
			result.put("gt", tempNoTiceShipMessage.getGrt());
			result.put("nt", tempNoTiceShipMessage.getNetTonnage());
			result.put("dwt", tempNoTiceShipMessage.getDwt());
			result.put("loa", tempNoTiceShipMessage.getLoa());
			result.put("breadth", tempNoTiceShipMessage.getBreadth());
			result.put("clearanceHeight",
					tempNoTiceShipMessage.getClearanceHeight());
			result.put("power", objShip.getPower());
			result.put("maxDraft", objShip.getMaxDraft());
			result.put("shownDraftxF", tempNoTiceShipMessage.getShownDraftxF());
			result.put("shownDraftxA", tempNoTiceShipMessage.getShownDraftxA());
			result.put("unitLOA", tempNoTiceShipMessage.getUnitLOA());
			result.put("unitBreadth", tempNoTiceShipMessage.getUnitBreadth());
			result.put("unitClearanceHeight",
					tempNoTiceShipMessage.getUnitClearanceHeight());
			result.put("unitShownDraftxF",
					tempNoTiceShipMessage.getUnitShownDraftxF());
			result.put("unitShownDraftxA",
					tempNoTiceShipMessage.getUnitShownDraftxA());
			result.put("unitGRT", tempNoTiceShipMessage.getUnitGRT());
			result.put("unitNT", tempNoTiceShipMessage.getNetTonnageUnit());
			result.put("unitDWT", tempNoTiceShipMessage.getUnitDWT());
			result.put("unitPower", objShip.getUnitPower());
			result.put("unitMaxDraft",
					tempNoTiceShipMessage.getUnitShownDraftxF());			
			
			result.put("purposeSpecified",
					tempNoTiceShipMessage.getPurposeSpecified());
			result.put("crewNumber", tempNoTiceShipMessage.getCrewNumber());
			result.put("passengerNumber",
					tempNoTiceShipMessage.getPassengerNumber());
			result.put("shipCaptain", tempNoTiceShipMessage.getShipCaptain());
			result.put("otherPersons", tempNoTiceShipMessage.getOtherPersons());
			result.put("asPerManifest", 0);
			result.put("containerNumber", 0);
			result.put("numberTEU", 0);
			result.put("numberTNE", 0);

			result.put(
					"timeOfPORTArrival",
					tempNoTiceShipMessage.getTimeOfPORTArrival() != null ? FormatData.formatDateShort3
							.format(tempNoTiceShipMessage
									.getTimeOfPORTArrival()) : StringPool.BLANK);
			result.put(
					"timeOfPilotOnBoard",
					tempNoTiceShipMessage.getTimeOfPilotOnBoard() != null ? FormatData.formatDateShort3
							.format(tempNoTiceShipMessage
									.getTimeOfPilotOnBoard())
							: StringPool.BLANK);
			result.put(
					"timeOfApproval",
					tempNoTiceShipMessage.getArrivalDate() != null ? FormatData.formatDateShort3
							.format(tempNoTiceShipMessage.getArrivalDate())
							: StringPool.BLANK);

			result.put("tugBoat", tempNoTiceShipMessage.getTugBoat());
			result.put("do_", tempNoTiceShipMessage.getDoField());
			result.put("fo_", tempNoTiceShipMessage.getFo());
			result.put("fw_", tempNoTiceShipMessage.getFw());
			result.put("oilWater", 0);
			result.put("quantityOfCargo",
					tempNoTiceShipMessage.getQuantityOfCargo());
			result.put("remainingCargo",
					tempNoTiceShipMessage.getRemainingCargo());
			result.put("shipRequirementsInTermsOfWaste",
					tempNoTiceShipMessage.getShipRequirementsInTermsOfWaste());
			result.put("previousPortsOfCall",
					tempNoTiceShipMessage.getPreviousPortsOfCall());
			result.put("subsequentPortsOfCall",
					tempNoTiceShipMessage.getSubsequentPortsOfCall());
			result.put("dischargedPorts",
					tempNoTiceShipMessage.getDischargedPorts());

			DmState dmState = null;

			if (Validator.isNotNull(tempNoTiceShipMessage.getPortGoingToCode())
					&& tempNoTiceShipMessage.getPortGoingToCode().length() >= 2) {
				String stateCode = tempNoTiceShipMessage.getPortGoingToCode()
						.substring(0, 2);
				dmState = DmStateLocalServiceUtil.getByStateCode(stateCode);

			}
			result.put("portGoingToStateName",
					dmState != null ? dmState.getStateName()
							: tempNoTiceShipMessage.getPortGoingToStateName());

			result.put("portGoingToCode",
					tempNoTiceShipMessage.getPortGoingToCode());
			result.put("lastPortOfCallCode",
					tempNoTiceShipMessage.getPortGoingToCode());
			result.put("remarks", tempNoTiceShipMessage.getRemarks());
			result.put("requestCodeNoticeShipMessage",
					tempNoTiceShipMessage.getRequestCode());
			result.put("modifiedDate",
					FormatData.formatDateShort3.format(new Date()));

			TempGeneralDeclaration banKhaiChungLast = TempGeneralDeclarationLocalServiceUtil
					.getLastByDocumentNameAndDocumentYear(documentName,
							documentYear);
			if (Validator.isNotNull(banKhaiChungLast)
					&& banKhaiChungLast.getNameOfShip().length() > 0) {
				result.put("shipCaptain", banKhaiChungLast.getNameOfMaster());
				result.put("lastPortOfCallCode",
						banKhaiChungLast.getLastPortOfCallCode());
				result.put("lastProvinceCode",
						banKhaiChungLast.getLastProvinceCode());
				result.put("lastMaritimePortCode",
						banKhaiChungLast.getLastMaritimePortCode());
				result.put("lastPortRegionCode",
						banKhaiChungLast.getLastPortRegionCode());
				result.put("lastPortHarbourCode",
						banKhaiChungLast.getLastPortHarbourCode());
				result.put("lastPortWharfCode",
						banKhaiChungLast.getLastPortWharfCode());
				result.put("nextProvinceCode",
						banKhaiChungLast.getNextProvinceCode());
				result.put("nextMaritimePortCode",
						banKhaiChungLast.getMaritimePortCode());
				result.put("nextPortRegionCode",
						banKhaiChungLast.getNextPortRegionCode());
				result.put("nextPortHarbourCode",
						banKhaiChungLast.getNextPortHarbourCode());
				result.put("nextPortWharfCode",
						banKhaiChungLast.getNextPortWharfCode());

				result.put("requestCodeGeneralDeclaration", 0);
				result.put("requestCodeShipSecurityMessage", 0);
			}

			List<IssuePortClearance> resultsIssuePortClearance = IssuePortClearanceLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.ID,
							KeyParams.ORDER_BY_DESC);
			IssuePortClearance portClearanceEdit = null;
			Date sTimeOfDeparture = new Date();

			if (resultsIssuePortClearance != null
					&& resultsIssuePortClearance.size() > 0) {
				portClearanceEdit = resultsIssuePortClearance.get(0);

				if (Validator.isNotNull(portClearanceEdit.getTimeOfDeparture())) {
					sTimeOfDeparture = portClearanceEdit.getTimeOfDeparture();
				} else {
					sTimeOfDeparture = Validator.isNotNull(banKhaiChungLast) ? banKhaiChungLast
							.getDateOfArrival() : new Date();
				}

			}

			List<IssueShiftingOrder> resultsIssueShiftingOrder = new ArrayList<IssueShiftingOrder>(
					IssueShiftingOrderLocalServiceUtil
							.findByDocumentYearAndDocumentYearOrderByColumn(
									documentName, documentYear, KeyParams.ID,
									KeyParams.ORDER_BY_DESC));
			if (resultsIssueShiftingOrder != null
					&& resultsIssueShiftingOrder.size() > 0) {
				List<IssueShiftingOrderChanel> lstChanel = IssueShiftingOrderChanelLocalServiceUtil
						.findByShiftingOrderId(resultsIssueShiftingOrder.get(0)
								.getId());
				JSONArray portsArrays = JSONFactoryUtil.createJSONArray();
				JSONObject port = null;
				String sChanelCodeList = StringPool.BLANK;
				String sChanelName = StringPool.BLANK;
				for (IssueShiftingOrderChanel issueShiftingOrderChanel : lstChanel) {
					port = JSONFactoryUtil.createJSONObject();

					port.put("code0", issueShiftingOrderChanel.getId().getChanelCode());
					port.put("name", issueShiftingOrderChanel.getChanelName());

					portsArrays.put(port);
					if (Validator.isNotNull(sChanelCodeList)) {
						sChanelCodeList = sChanelCodeList + ","
								+ issueShiftingOrderChanel.getId().getChanelCode();
					} else {
						sChanelCodeList = sChanelCodeList
								+ issueShiftingOrderChanel.getId().getChanelCode();
					}
					if (Validator.isNotNull(sChanelName)) {
						sChanelName = sChanelName + "/n"
								+ issueShiftingOrderChanel.getChanelName();
					} else {
						sChanelName = sChanelName
								+ issueShiftingOrderChanel.getChanelName();
					}
				}

				result.put("chanel", portsArrays);
				result.put("chanelCodeList", sChanelCodeList);
				result.put("chanelName", sChanelName);

			}
			Date sResponseTimeCVHH = new Date();

			result.put("responseTimeCVHH",
					FormatData.formatDateShort3.format(sResponseTimeCVHH));

			VmaShipLocalServiceUtil.updateVmaShip(objShip);

		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("ItineraryNo", 0);
			log.info("E10: Loi khoi tao ke hoach den, roi.");
		}

		return result;
	}

	public static JSONObject getCanhBaoLenhDieuDongLuotVaoRoi(
			ThemeDisplay themeDisplay, long documentName, int documentYear,
			String ItineraryNo, HttpServletRequest request) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		// 1. Bo sung ham lay VmaItinerary theo DocumentName, DocumentYear;
		// 2. Bo sung ham lay VmaItinerarySchedule theo luot vao, roi

		VmaItinerary objItinerary = new VmaItinerary();
		// Bo sung ham lay VmaItinerary theo DocumentName, DocumentYear;
		// VmaItineraryLocalServiceUtil.
		List<VmaItinerarySchedule> lstItinerarySchedule = new ArrayList<VmaItinerarySchedule>();
		// Bo sung ham lay VmaItinerarySchedule theo luot vao, roi
		// VmaItineraryScheduleLocalServiceUtil
		VmaItinerarySchedule objItinerarySchedule = new VmaItinerarySchedule();

		List<IssueShiftingOrder> results = new ArrayList<IssueShiftingOrder>(
				IssueShiftingOrderLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(
								documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC));

		UserPort portDefault = UserPortLocalServiceUtil
				.findByUserId(themeDisplay.getUserId());
		List<DmPort> ports = null;

		if (portDefault != null) {
			ports = DmPortLocalServiceUtil.findByLoCode(portDefault
					.getPortCode());
		}

		if (ports == null) {
			ports = new ArrayList<DmPort>();
		}

		JSONArray portsArrays = JSONFactoryUtil.createJSONArray();
		JSONObject port = null;
		for (DmPort dmPort : ports) {
			port = JSONFactoryUtil.createJSONObject();

			port.put("portCode", dmPort.getPortCode());
			port.put("portName", dmPort.getPortName());

			portsArrays.put(port);
		}
		result.put("ports", portsArrays);

		TempDocument tempDoc = TempDocumentLocalServiceUtil
				.findTemDocumentByDocumentNameDocumentYear(documentName,
						documentYear);

		DmMaritime maritime = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(tempDoc.getMaritimeCode());
		if (Validator.isNull(maritime)) {
			maritime = new DmMaritime();
		}

		result.put("maritimeCityName", maritime.getCityCode());

		DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
				.getByPortRegionCode(tempDoc.getPortRegionCode());

		result.put("portRegionName", dmPortRegion.getPortRegionNameVN());

		// PortHarbour la ben cang,
		List<DmPortHarbour> lstPortHarbour = DmPortHarbourLocalServiceUtil
				.findByPortRegionCode(tempDoc.getMaritimeCode());
		if (Validator.isNull(lstPortHarbour)) {
			lstPortHarbour = new ArrayList<DmPortHarbour>();
		}

		portsArrays = JSONFactoryUtil.createJSONArray();
		port = null;
		for (DmPortHarbour dmPort : lstPortHarbour) {
			port = JSONFactoryUtil.createJSONObject();

			port.put("portHarbourCode", dmPort.getPortHarbourCode());
			port.put("portHarbourName", dmPort.getPortHarbourNameVN());

			portsArrays.put(port);
		}
		result.put("lstPortHarbour", portsArrays);

		String portHarborCode = lstPortHarbour.get(0).getPortHarbourCode();

		List<DmPortWharf> lstPortWharf = new ArrayList<DmPortWharf>();

		if (result != null && results.size() > 0) {
			try {
				result.put("detail", JSONFactoryUtil
						.createJSONObject(JSONFactoryUtil
								.looseSerialize(results.get(0))));

				List<IssueShiftingOrderChanel> lstChanel = IssueShiftingOrderChanelLocalServiceUtil
						.findByShiftingOrderId(results.get(0).getId());

				portsArrays = JSONFactoryUtil.createJSONArray();
				port = null;
				for (IssueShiftingOrderChanel issueShiftingOrderChanel : lstChanel) {
					port = JSONFactoryUtil.createJSONObject();

					port.put("code0", issueShiftingOrderChanel.getId().getChanelCode());
					port.put("name", issueShiftingOrderChanel.getChanelName());

					portsArrays.put(port);
				}

				result.put("chanel", portsArrays);

				try {
					List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
							.findBydocumentNameAndDocumentYearAndNoticeShipType(
									documentName, documentYear,
									PageType.TYPE_THONG_BAO_TAU_DEN_CANG);

					if (tempNoTiceShipMessages != null
							&& tempNoTiceShipMessages.size() > 0) {
						TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
								.get(0);
						lstPortWharf = new ArrayList<DmPortWharf>();

						lstPortWharf = DmPortWharfLocalServiceUtil
								.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
										tempNoTiceShipMessage
												.getPortRegionCode(),
										tempNoTiceShipMessage
												.getPortHarbourCode(),
										KeyParams.ORDER_BY_ASC);

						portsArrays = JSONFactoryUtil.createJSONArray();
						port = null;
						for (DmPortWharf dmPort : lstPortWharf) {
							port = JSONFactoryUtil.createJSONObject();

							port.put("portWharfCode", dmPort.getPortWharfCode());
							port.put("portWharfName",
									dmPort.getPortWharfNameVN());

							portsArrays.put(port);
						}
						result.put("lstPortWharf", portsArrays);

					}

					tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
							.findBydocumentNameAndDocumentYearAndNoticeShipType(
									documentName, documentYear,
									PageType.TYPE_XAC_BAO_TAU_DEN_CANG);

					if (tempNoTiceShipMessages != null
							&& tempNoTiceShipMessages.size() > 0) {
						TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
								.get(0);

						lstPortWharf = new ArrayList<DmPortWharf>();

						lstPortWharf = DmPortWharfLocalServiceUtil
								.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
										tempNoTiceShipMessage
												.getPortRegionCode(),
										tempNoTiceShipMessage
												.getPortHarbourCode(),
										KeyParams.ORDER_BY_ASC);

						portsArrays = JSONFactoryUtil.createJSONArray();
						port = null;
						for (DmPortWharf dmPort : lstPortWharf) {
							port = JSONFactoryUtil.createJSONObject();

							port.put("portWharfCode", dmPort.getPortWharfCode());
							port.put("portWharfName",
									dmPort.getPortWharfNameVN());

							portsArrays.put(port);
						}
						result.put("lstPortWharf", portsArrays);

					}

				} catch (Exception e) {
					log.error(e.getMessage());
				}

			} catch (JSONException e) {
				log.error(e.getMessage());
			}
		} else {
			IssueShiftingOrder issueShiftingOrder = new IssueShiftingOrder();

			try {
				List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
						.findBydocumentNameAndDocumentYearAndNoticeShipType(
								documentName, documentYear,
								PageType.TYPE_THONG_BAO_TAU_DEN_CANG);

				if (tempNoTiceShipMessages != null
						&& tempNoTiceShipMessages.size() > 0) {
					TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
							.get(0);

					issueShiftingOrder
							.setTaxCodeOfShipownersAgents(tempNoTiceShipMessage
									.getShipOwnerStateCode());
					issueShiftingOrder
							.setNameOfShipownersAgents(tempNoTiceShipMessage
									.getNameOfShipOwners());
					issueShiftingOrder
							.setShipAgencyAddress(tempNoTiceShipMessage
									.getShipAgencyAddress());
					issueShiftingOrder.setShipAgencyPhone(tempNoTiceShipMessage
							.getShipAgencyPhone());
					issueShiftingOrder.setShipAgencyFax(tempNoTiceShipMessage
							.getShipAgencyFax());
					issueShiftingOrder.setShipAgencyEmail(tempNoTiceShipMessage
							.getShipAgencyEmail());
					issueShiftingOrder.setTugBoat(tempNoTiceShipMessage
							.getTugBoat());
					issueShiftingOrder.setShownDraftxA(tempNoTiceShipMessage
							.getShownDraftxA());
					issueShiftingOrder.setShownDraftxF(tempNoTiceShipMessage
							.getShownDraftxF());
					issueShiftingOrder.setLoa(tempNoTiceShipMessage.getLoa());
					issueShiftingOrder.setDwt(tempNoTiceShipMessage.getDwt());
					issueShiftingOrder.setNameOfShip(tempNoTiceShipMessage
							.getShipName());

					// DmPort dmPortArrival =
					// DmPortLocalServiceUtil.fetchByPortCodeLoCode(tempNoTiceShipMessage.getArrivalPortCode(),
					// portDefault.getPortCode());

					// String arrivalPortName = StringPool.BLANK;
					//
					// if (Validator.isNotNull(dmPortArrival)) {
					//
					// arrivalPortName = dmPortArrival.getPortName();
					//
					// }
					//
					// issueShiftingOrder.setTo(arrivalPortName);

					DmPort dmPortArrival = DmPortLocalServiceUtil
							.getByPortCode(tempNoTiceShipMessage
									.getPortGoingToCode());

					issueShiftingOrder.setFrom(Validator
							.isNotNull(dmPortArrival) ? dmPortArrival
							.getPortName() : StringPool.BLANK);

					issueShiftingOrder
							.setRepresentative("GI\u00C1M \u0110\u1ED0C");

					issueShiftingOrder
							.setAnchoringPortCode(tempNoTiceShipMessage
									.getArrivalPortCode());

					issueShiftingOrder.setPortHarbourCode(tempNoTiceShipMessage
							.getPortHarbourCode());

					issueShiftingOrder
							.setShiftingPortWharfCode(tempNoTiceShipMessage
									.getPortWharfCode());

					// issueShiftingOrder.setAnchoringPortWharfCode(tempNoTiceShipMessage.getPortWharfCode());

					issueShiftingOrder.setShiftingDate(tempNoTiceShipMessage
							.getArrivalDate());

					lstPortWharf = new ArrayList<DmPortWharf>();

					lstPortWharf = DmPortWharfLocalServiceUtil
							.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
									tempNoTiceShipMessage.getPortRegionCode(),
									tempNoTiceShipMessage.getPortHarbourCode(),
									KeyParams.ORDER_BY_ASC);

					portsArrays = JSONFactoryUtil.createJSONArray();
					port = null;
					for (DmPortWharf dmPort : lstPortWharf) {
						port = JSONFactoryUtil.createJSONObject();

						port.put("portWharfCode", dmPort.getPortWharfCode());
						port.put("portWharfName", dmPort.getPortWharfNameVN());

						portsArrays.put(port);
					}
					result.put("lstPortWharf", portsArrays);

				}

				tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
						.findBydocumentNameAndDocumentYearAndNoticeShipType(
								documentName, documentYear,
								PageType.TYPE_XAC_BAO_TAU_DEN_CANG);

				if (tempNoTiceShipMessages != null
						&& tempNoTiceShipMessages.size() > 0) {
					TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
							.get(0);

					lstPortWharf = new ArrayList<DmPortWharf>();

					lstPortWharf = DmPortWharfLocalServiceUtil
							.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
									tempNoTiceShipMessage.getPortRegionCode(),
									tempNoTiceShipMessage.getPortHarbourCode(),
									KeyParams.ORDER_BY_ASC);

					portsArrays = JSONFactoryUtil.createJSONArray();
					port = null;
					for (DmPortWharf dmPort : lstPortWharf) {
						port = JSONFactoryUtil.createJSONObject();

						port.put("portWharfCode", dmPort.getPortWharfCode());
						port.put("portWharfName", dmPort.getPortWharfNameVN());

						portsArrays.put(port);
					}
					result.put("lstPortWharf", portsArrays);

					issueShiftingOrder.setShiftingDate(tempNoTiceShipMessage
							.getArrivalDate());

					issueShiftingOrder
							.setAnchoringPortCode(tempNoTiceShipMessage
									.getArrivalPortCode());

					issueShiftingOrder.setPortHarbourCode(tempNoTiceShipMessage
							.getPortHarbourCode());

					issueShiftingOrder
							.setShiftingPortWharfCode(tempNoTiceShipMessage
									.getPortWharfCode());
					//
					// DmPort dmPortArrival =
					// DmPortLocalServiceUtil.getByPortCode(tempNoTiceShipMessage.getPortGoingToCode());
					//
					// issueShiftingOrder.setFrom(Validator.isNotNull(dmPortArrival)
					// ? dmPortArrival.getPortName() : StringPool.BLANK);
					//
				}

				result.put("detail", JSONFactoryUtil
						.createJSONObject(JSONFactoryUtil
								.looseSerialize(issueShiftingOrder)));
				result.put("chanel", JSONFactoryUtil.createJSONArray());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		List<DmDataitem> changels = DmDataItemLocalServiceUtil
				.findByDataGroupIdAndNode1(Constants.DM_TUYEN_LUONG,
						tempDoc.getMaritimeCode());

		if (Validator.isNull(changels)) {
			changels = new ArrayList<DmDataitem>();
		}

		try {
			result.put("chanels", JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(changels)));
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("chanels", JSONFactoryUtil.createJSONArray());
		}

		return result;
	}

}