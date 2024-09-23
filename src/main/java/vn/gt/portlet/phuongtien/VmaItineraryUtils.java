package vn.gt.portlet.phuongtien;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import com.fds.nsw.nghiepvu.model.*;
import jakarta.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmArrivalPurposeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmDocTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtReportCategoryLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException;
import com.fds.nsw.nghiepvu.model.VmaItineraryRemark;


import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryProtestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryRemarksLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleAnchorageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleCargolistLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleLaunchingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleMergingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleSecurityLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleShiftingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleShipyardLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTestingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTransferLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaShipLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipDetailsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDebitnote;

import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.utils.FormatData;
import vn.gt.utils.PageType;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.core.ThemeDisplay;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaItineraryUtils
 {

	public static boolean checkActiveVma(long userId) {

		UserPort portDefault = UserPortLocalServiceUtil.findByUserId(userId);

		String maritimeCode = StringPool.BLANK;

		if (Validator.isNotNull(portDefault)) {
			maritimeCode = portDefault.getPortCode();
		}

		if (Validator.isNull(maritimeCode)) {
			return false;
		}

		DmGtReportCategory dmGtReportCategory = DmGtReportCategoryLocalServiceUtil
				.findByOrganizationCode(maritimeCode);

		if (dmGtReportCategory == null) {
			return false;
		}

		return true;
	}

	public static boolean checkActiveVma(ThemeDisplay themeDisplay) {

		UserPort portDefault = UserPortLocalServiceUtil
				.findByUserId(themeDisplay.getUserId());

		String maritimeCode = StringPool.BLANK;

		if (Validator.isNotNull(portDefault)) {
			maritimeCode = portDefault.getPortCode();
		}

		if (Validator.isNull(maritimeCode)) {
			return false;
		}

		DmGtReportCategory dmGtReportCategory = DmGtReportCategoryLocalServiceUtil
				.findByOrganizationCode(maritimeCode);

		if (dmGtReportCategory == null) {
			return false;
		}

		return true;
	}

	public static boolean checkActiveVma(String maritimeCode) {

		if (Validator.isNull(maritimeCode)) {
			return false;
		}

		DmGtReportCategory dmGtReportCategory = DmGtReportCategoryLocalServiceUtil
				.findByOrganizationCode(maritimeCode);

		if (dmGtReportCategory == null) {
			return false;
		}

		return true;
	}

	public static boolean checkActiveVma(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		UserPort portDefault = UserPortLocalServiceUtil
				.findByUserId(themeDisplay.getUserId());

		String maritimeCode = StringPool.BLANK;

		if (Validator.isNotNull(portDefault)) {
			maritimeCode = portDefault.getPortCode();
		}

		if (Validator.isNull(maritimeCode)) {
			return false;
		}

		DmGtReportCategory dmGtReportCategory = DmGtReportCategoryLocalServiceUtil
				.findByOrganizationCode(maritimeCode);

		if (dmGtReportCategory == null) {
			return false;
		}

		return true;
	}

	public static String findItineraryNoByDocumentName_DocumentYear(
			long documentName, int documentYear) {

		String itineraryNo = "---";

		try {
			List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
					.findBydocumentNameAndDocumentYearAndNoticeShipType(
							documentName, documentYear,
							PageType.TYPE_THONG_BAO_TAU_DEN_CANG);

			TempNoticeShipMessage tempNoTiceShipMessage = null;
			if (tempNoTiceShipMessages != null
					&& tempNoTiceShipMessages.size() > 0) {
				tempNoTiceShipMessage = tempNoTiceShipMessages.get(0);

			}
			if (tempNoTiceShipMessage == null
					|| Validator.isNull(tempNoTiceShipMessage.getShipName())) {

				return itineraryNo;
			}

			if (tempNoTiceShipMessage.getIsArrival() == 1) {
				VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
						.fetchBydocumentNameIN_documentYearIN(documentName,
								documentYear);
				if (vmaItinerary != null
						&& Validator.isNotNull(vmaItinerary.getItineraryNo())) {
					itineraryNo = vmaItinerary.getItineraryNo();
				}

			} else if (tempNoTiceShipMessage.getIsArrival() == 0) {

				VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
						.fetchBydocumentNameOUT_documentYearOUT(documentName,
								documentYear);
				if (vmaItinerary != null
						&& Validator.isNotNull(vmaItinerary.getItineraryNo())) {
					itineraryNo = vmaItinerary.getItineraryNo();
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return itineraryNo;
	}

	public static JSONObject findByDocumentName_DocumentYear(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		long documentName = ParamUtil.getLong(request, "documentName");

		int documentYear = ParamUtil.getInteger(request, "documentYear");
		System.out
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>============================== "
						+ documentName + "|" + documentYear);
		try {
			List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
					.findBydocumentNameAndDocumentYearAndNoticeShipType(
							documentName, documentYear,
							PageType.TYPE_THONG_BAO_TAU_DEN_CANG);

			TempNoticeShipMessage tempNoTiceShipMessage = null;
			if (tempNoTiceShipMessages != null
					&& tempNoTiceShipMessages.size() > 0) {
				tempNoTiceShipMessage = tempNoTiceShipMessages.get(0);

				System.out
						.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>============================== tempNoTiceShipMessage "
								+ tempNoTiceShipMessage.getDocumentName()
								+ "|"
								+ tempNoTiceShipMessage.getDocumentYear()
								+ "|"
								+ tempNoTiceShipMessage.getShipName());

			}
			if (tempNoTiceShipMessage == null
					|| Validator.isNull(tempNoTiceShipMessage.getShipName())) {

				return result;
			}

			if (tempNoTiceShipMessage.getIsArrival() == 1) {
				VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
						.fetchBydocumentNameIN_documentYearIN(documentName,
								documentYear);
				if (vmaItinerary != null) {
					result = VMAUtils.object2Json(vmaItinerary,
							VmaItinerary.class);
					result.remove("payment2DepartureStatus");
					result.put("payment2DepartureStatus", 0); // Bo qua Trang thai Tinh phi luot roi
					int countMerging = VmaScheduleMergingLocalServiceUtil
							.countByItineraryNo(vmaItinerary.getItineraryNo());
					if (countMerging > 0) {
						result.put("payment2Merging", 1);
					} else {
						result.put("payment2Merging", 0);
					}
					String itineraryNo = vmaItinerary.getItineraryNo();
					int TINHPHI_TAUBIEN = 1;
					int TINHPHI_TAUBIEN_VAO = 0;
					int TINHPHI_TAUBIEN_ROI = 0;
					int TINHPHI_PTTND = 0;
					int TINHPHI_PTTND_VAO = 0;
					int TINHPHI_PTTND_ROI = 0;
					int TINHPHI_DOANLAI = 0;	
					
					if (countMerging > 0) {
						TINHPHI_TAUBIEN = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN",
								vmaItinerary, null );
						TINHPHI_TAUBIEN_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN_VAO",
								vmaItinerary, null );
						TINHPHI_TAUBIEN_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN_ROI",
								vmaItinerary, null );
						TINHPHI_PTTND = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND",
								vmaItinerary, null );
						TINHPHI_PTTND_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND_VAO",
								vmaItinerary, null );
						TINHPHI_PTTND_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND_ROI",
								vmaItinerary, null );
						TINHPHI_DOANLAI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_DOANLAI",
								vmaItinerary, null );
						
					} else {
						
						TINHPHI_TAUBIEN = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN",
								vmaItinerary, null, null );
						TINHPHI_TAUBIEN_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN_VAO",
								vmaItinerary, null, null );
						TINHPHI_TAUBIEN_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN_ROI",
								vmaItinerary, null, null );
						TINHPHI_PTTND = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND",
								vmaItinerary, null, null );
						TINHPHI_PTTND_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND_VAO",
								vmaItinerary, null, null );
						TINHPHI_PTTND_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND_ROI",
								vmaItinerary, null, null );
						
					}
					result.put("TINHPHI_PTTND", TINHPHI_PTTND);
					result.put("TINHPHI_PTTND_VAO", TINHPHI_PTTND_VAO);
					result.put("TINHPHI_PTTND_ROI", TINHPHI_PTTND_ROI);
					result.put("TINHPHI_TAUBIEN", TINHPHI_TAUBIEN);
					result.put("TINHPHI_TAUBIEN_VAO", TINHPHI_TAUBIEN_VAO);
					result.put("TINHPHI_TAUBIEN_ROI", TINHPHI_TAUBIEN_ROI);
					result.put("TINHPHI_DOANLAI", TINHPHI_DOANLAI);
				}

			} else if (tempNoTiceShipMessage.getIsArrival() == 0) {

				VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
						.fetchBydocumentNameOUT_documentYearOUT(documentName,
								documentYear);
				if (vmaItinerary != null) {
					result = VMAUtils.object2Json(vmaItinerary,
							VmaItinerary.class);
					int countMerging = VmaScheduleMergingLocalServiceUtil
							.countByItineraryNo(vmaItinerary.getItineraryNo());
					if (countMerging > 0) {
						result.put("payment2Merging", 1);
					} else {
						result.put("payment2Merging", 0);
					}
					String itineraryNo = vmaItinerary.getItineraryNo();
					int TINHPHI_TAUBIEN = 1;
					int TINHPHI_TAUBIEN_VAO = 0;
					int TINHPHI_TAUBIEN_ROI = 0;
					int TINHPHI_PTTND = 0;
					int TINHPHI_PTTND_VAO = 0;
					int TINHPHI_PTTND_ROI = 0;
					int TINHPHI_DOANLAI = 0;	
					
					if (countMerging > 0) {
						TINHPHI_TAUBIEN = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN",
								vmaItinerary, null );
						TINHPHI_TAUBIEN_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN_VAO",
								vmaItinerary, null );
						TINHPHI_TAUBIEN_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN_ROI",
								vmaItinerary, null );
						TINHPHI_PTTND = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND",
								vmaItinerary, null );
						TINHPHI_PTTND_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND_VAO",
								vmaItinerary, null );
						TINHPHI_PTTND_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND_ROI",
								vmaItinerary, null );
						TINHPHI_DOANLAI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_DOANLAI",
								vmaItinerary, null );
						
					} else {
						
						TINHPHI_TAUBIEN = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN",
								vmaItinerary, null, null );
						TINHPHI_TAUBIEN_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN_VAO",
								vmaItinerary, null, null );
						TINHPHI_TAUBIEN_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN_ROI",
								vmaItinerary, null, null );
						TINHPHI_PTTND = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND",
								vmaItinerary, null, null );
						TINHPHI_PTTND_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND_VAO",
								vmaItinerary, null, null );
						TINHPHI_PTTND_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND_ROI",
								vmaItinerary, null, null );
						
					}
					result.put("TINHPHI_PTTND", TINHPHI_PTTND);
					result.put("TINHPHI_PTTND_VAO", TINHPHI_PTTND_VAO);
					result.put("TINHPHI_PTTND_ROI", TINHPHI_PTTND_ROI);
					result.put("TINHPHI_TAUBIEN", TINHPHI_TAUBIEN);
					result.put("TINHPHI_TAUBIEN_VAO", TINHPHI_TAUBIEN_VAO);
					result.put("TINHPHI_TAUBIEN_ROI", TINHPHI_TAUBIEN_ROI);
					result.put("TINHPHI_DOANLAI", TINHPHI_DOANLAI);
					
					// Xu ly khi luot vao, roi khac tau
					
					List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo, 2);
					VmaScheduleTransfer vmaScheduleTransfer = null;
					if (vmaScheduleTransfers != null && vmaScheduleTransfers.size() > 0) {
						vmaScheduleTransfer = vmaScheduleTransfers.get(0);
					}
					if (vmaScheduleTransfer != null) {
						result.put("nameOfShip",
								vmaScheduleTransfer.getShipName());
						result.put("flagStateOfShip",
								vmaScheduleTransfer.getFlagStateOfShip());
						result.put("callSign",
								vmaScheduleTransfer.getCallSign());
						result.put("imoNumber",
								vmaScheduleTransfer.getImoNumber());
						result.put("registryNumber",
								vmaScheduleTransfer.getRegistryNumber());
						result.put("vrCode", vmaScheduleTransfer.getVrCode());
						result.put("shipOperatorCode",
								vmaScheduleTransfer.getShipOperatorCode());
						try {
							result.put(
									"shipOperatorName",
									DmVmaShipOwnerLocalServiceUtil
											.fetchByShipOwnerCode(
													vmaScheduleTransfer
															.getShipOperatorCode())
											.getCompanyName());
						} catch (Exception e) {
						}
						result.put("shipOwnerCode",
								vmaScheduleTransfer.getShipOwnerCode());
						try {
							result.put(
									"shipOwnerName",
									DmVmaShipOwnerLocalServiceUtil
											.fetchByShipOwnerCode(
													vmaScheduleTransfer
															.getShipOwnerCode())
											.getCompanyName());
						} catch (Exception e) {
						}
						result.put("shipCaptain",
								vmaScheduleTransfer.getNameOfMaster());
						result.put("vmaShipTypeCode",
								vmaScheduleTransfer.getShipTypeMT());
						result.put("shipTypeCode",
								vmaScheduleTransfer.getShipTypeCode());
						result.remove("payment2ArrivalStatus");
						result.put("payment2ArrivalStatus", 0); // Bo qua Trang thai Tinh phi luot vao khi Ban giao tau
					}
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaItineraryId = ParamUtil.getLong(request, "vmaItineraryId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
					.getVmaItinerary(vmaItineraryId);
			result = VMAUtils.object2Json(vmaItinerary,
					VmaItinerary.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static VmaItinerary getObjectFromRequest(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {

		HttpServletRequest request = actionRequest;

		long id = GetterUtil
				.getLong(request.getParameter("vmaItineraryId"), -1);

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		VmaItinerary vmaItinerary = null;
		if (id > 0) {
			try {
				vmaItinerary = VmaItineraryLocalServiceUtil.getVmaItinerary(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
			String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
					StringPool.BLANK);
			String callSign = VMAUtils.getString(actionRequest, "callSign",
					StringPool.BLANK);
			String flagStateOfShip = VMAUtils.getString(actionRequest,
					"flagStateOfShip", StringPool.BLANK);
			String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
					StringPool.BLANK);
			String vrCode = ParamUtil.getString(actionRequest, "vrCode",
					StringPool.BLANK);
			String registryNumber = VMAUtils.getString(actionRequest,
					"registryNumber", StringPool.BLANK);

			if (vmaItinerary != null
					&& checkChanged(vmaItinerary, nameOfShip, flagStateOfShip,
							imoNumber, callSign, registryNumber)) {
				// Thay đổi Tên tàu, Quốc tịch, Hô Hiệu
				// Chỉ cập nhật nối chuyến.
				int shipPosition = GetterUtil.getInteger(
						request.getParameter("shipPosition"), -1);
				if (shipPosition >= 2) {
					vmaItinerary.setShipPosition(shipPosition);
				} else {
					// Kiem tra dieu kien reset value ShipPostion
					int markedAsArrival = GetterUtil.getInteger(
							request.getParameter("markedAsArrival"), -1); 
					
					if ((vmaItinerary.getShipPosition() == MessageType.SHIP_POSSITION_DEN_CANG)
					&& (markedAsArrival == ChuyenDichTrangThaiUtils.DANH_DAU_DA_DUYET
							|| markedAsArrival == ChuyenDichTrangThaiUtils.DANH_DAU_CHO_DONG_DAU_BCY
							|| markedAsArrival == ChuyenDichTrangThaiUtils.DANH_DAU_DA_KY_DUYET) ){
						vmaItinerary
						.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
					}
				}
				int markedAsDeparture = GetterUtil.getInteger(
						request.getParameter("markedAsDeparture"), -1);
				if (markedAsDeparture >= 0) {
					vmaItinerary.setMarkedAsDeparture(markedAsDeparture);
				}
				long documentNameOUT = GetterUtil.getLong(
						request.getParameter("documentNameOUT"), -1);
				if (documentNameOUT >= 0) {
					vmaItinerary.setDocumentNameOUT(documentNameOUT);
				}
				int documentYearOUT = GetterUtil.getInteger(
						request.getParameter("documentYearOUT"), -1);
				if (documentYearOUT >= 0) {
					vmaItinerary.setDocumentYearOUT(documentYearOUT);
				}
				int markedAsVoyage = GetterUtil.getInteger(
						request.getParameter("markedAsVoyage"), -1);
				if (markedAsVoyage >= 0) {
					vmaItinerary.setMarkedAsVoyage(markedAsVoyage);
				}
				long documentNameVOY = GetterUtil.getLong(
						request.getParameter("documentNameVOY"), -1);
				if (documentNameVOY >= 0) {
					vmaItinerary.setDocumentNameVOY(documentNameVOY);
				}
				int documentYearVOY = GetterUtil.getInteger(
						request.getParameter("documentYearVOY"), -1);
				if (documentYearVOY >= 0) {
					vmaItinerary.setDocumentYearVOY(documentYearVOY);
				}
				String timeOfDeparture = ParamUtil.getString(actionRequest,
						"timeOfDeparture", StringPool.BLANK);
				if (Validator.isNotNull(timeOfDeparture)) {
					try {
						Date date = FormatData.formatDateShort3
								.parse(timeOfDeparture);
						vmaItinerary.setTimeOfDeparture(date);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}
				}
				String departureShipAgencyCode = ParamUtil.getString(
						actionRequest, "departureShipAgencyCode",
						StringPool.BLANK);
				if (Validator.isNotNull(departureShipAgencyCode)) {
					vmaItinerary
							.setDepartureShipAgencyCode(departureShipAgencyCode);
				}
				String departureShipAgency = VMAUtils.getString(actionRequest,
						"departureShipAgency", StringPool.BLANK);
				if (Validator.isNotNull(departureShipAgency)) {
					vmaItinerary.setDepartureShipAgency(departureShipAgency);
				}
				String nextPortCode = ParamUtil.getString(actionRequest,
						"nextPortCode", StringPool.BLANK);

				if (Validator.isNotNull(nextPortCode)) {
					vmaItinerary.setNextPortCode(nextPortCode);
				}
				return vmaItinerary;
			}
			//
			/*
			 * if (Validator.isNotNull(itineraryNo) && vmaItinerary == null) {
			 * try { vmaItinerary = VmaItineraryLocalServiceUtil
			 * .fetchByitineraryNo(itineraryNo); } catch (SystemException e) {
			 * 
			 * log.error(e.getMessage()); return null; } }
			 */

		} else {
			vmaItinerary = new VmaItinerary();
		}
		int mtgateway = GetterUtil.getInteger(
				request.getParameter("mtgateway"), -1);
		if (mtgateway >= 0) {
			vmaItinerary.setMtgateway(mtgateway);
		}
		String maritimeCode = ParamUtil.getString(actionRequest,
				"maritimeCode", StringPool.BLANK);
		if (Validator.isNotNull(maritimeCode)) {
			vmaItinerary.setMaritimeCode(maritimeCode);
		}

		if (Validator.isNotNull(itineraryNo)) {
			vmaItinerary.setItineraryNo(itineraryNo);
		}
		String shipPreviousName = VMAUtils.getString(actionRequest,
				"shipPreviousName", StringPool.BLANK);
		if (Validator.isNotNull(shipPreviousName)) {
			vmaItinerary.setShipPreviousName(shipPreviousName);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaItinerary.setNameOfShip(nameOfShip);
		}
		String flagStateOfShip = VMAUtils.getString(actionRequest,
				"flagStateOfShip", StringPool.BLANK);
		if (Validator.isNotNull(flagStateOfShip)) {
			vmaItinerary.setFlagStateOfShip(flagStateOfShip);
		}
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
				StringPool.BLANK);
		if (Validator.isNotNull(imoNumber)) {
			vmaItinerary.setImoNumber(imoNumber);
		}
		String callSign = VMAUtils.getString(actionRequest, "callSign",
				StringPool.BLANK);
		if (Validator.isNotNull(callSign)) {
			vmaItinerary.setCallSign(callSign);
		}
		String vrCode = ParamUtil.getString(actionRequest, "vrCode",
				StringPool.BLANK);
		if (Validator.isNotNull(vrCode)) {
			vmaItinerary.setVrCode(vrCode);
		}
		String registryNumber = VMAUtils.getString(actionRequest,
				"registryNumber", StringPool.BLANK);
		if (Validator.isNotNull(registryNumber)) {
			vmaItinerary.setRegistryNumber(registryNumber);
		}
		int shipPosition = GetterUtil.getInteger(
				request.getParameter("shipPosition"), -1);
		if (shipPosition >= 0) {
			vmaItinerary.setShipPosition(shipPosition);
		}
		String shipTypeCode = ParamUtil.getString(actionRequest,
				"shipTypeCode", StringPool.BLANK);
		if (Validator.isNotNull(shipTypeCode)) {
			vmaItinerary.setShipTypeCode(shipTypeCode);
		}
		String vmaShipTypeCode = ParamUtil.getString(actionRequest,
				"vmaShipTypeCode", StringPool.BLANK);
		if (Validator.isNotNull(vmaShipTypeCode)) {
			vmaItinerary.setVmaShipTypeCode(vmaShipTypeCode);
		}
		String shipCaptain = VMAUtils.getString(actionRequest, "shipCaptain",
				StringPool.BLANK);
		if (Validator.isNotNull(shipCaptain)) {
			vmaItinerary.setShipCaptain(shipCaptain);
		}
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaItinerary.setPortofAuthority(portofAuthority);
		}
		String representativeofAuthority = VMAUtils.getString(actionRequest,
				"representativeofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(representativeofAuthority)) {
			vmaItinerary
					.setRepresentativeofAuthority(representativeofAuthority);
		}
		int markedAsArrival = GetterUtil.getInteger(
				request.getParameter("markedAsArrival"), -1);
		if (markedAsArrival >= 0) {
			vmaItinerary.setMarkedAsArrival(markedAsArrival);
		}
		int markedAsDeparture = GetterUtil.getInteger(
				request.getParameter("markedAsDeparture"), -1);
		if (markedAsDeparture >= 0) {
			vmaItinerary.setMarkedAsDeparture(markedAsDeparture);
		}
		int markedAsTransmit = GetterUtil.getInteger(
				request.getParameter("markedAsTransmit"), -1);
		if (markedAsTransmit >= 0) {
			vmaItinerary.setMarkedAsTransmit(markedAsTransmit);
		}
		int markedAsVoyage = GetterUtil.getInteger(
				request.getParameter("markedAsVoyage"), -1);
		if (markedAsVoyage >= 0) {
			vmaItinerary.setMarkedAsVoyage(markedAsVoyage);
		}
		long documentNameIN = GetterUtil.getLong(
				request.getParameter("documentNameIN"), -1);
		if (documentNameIN >= 0) {
			vmaItinerary.setDocumentNameIN(documentNameIN);
		}
		int documentYearIN = GetterUtil.getInteger(
				request.getParameter("documentYearIN"), -1);
		if (documentYearIN >= 0) {
			vmaItinerary.setDocumentYearIN(documentYearIN);
		}
		long documentNameOUT = GetterUtil.getLong(
				request.getParameter("documentNameOUT"), -1);
		if (documentNameOUT >= 0) {
			vmaItinerary.setDocumentNameOUT(documentNameOUT);
		}
		int documentYearOUT = GetterUtil.getInteger(
				request.getParameter("documentYearOUT"), -1);
		if (documentYearOUT >= 0) {
			vmaItinerary.setDocumentYearOUT(documentYearOUT);
		}
		long documentNameTRA = GetterUtil.getLong(
				request.getParameter("documentNameTRA"), -1);
		if (documentNameTRA >= 0) {
			vmaItinerary.setDocumentNameTRA(documentNameTRA);
		}
		int documentYearTRA = GetterUtil.getInteger(
				request.getParameter("documentYearTRA"), -1);
		if (documentYearTRA >= 0) {
			vmaItinerary.setDocumentYearTRA(documentYearTRA);
		}
		long documentNameVOY = GetterUtil.getLong(
				request.getParameter("documentNameVOY"), -1);
		if (documentNameVOY >= 0) {
			vmaItinerary.setDocumentNameVOY(documentNameVOY);
		}
		int documentYearVOY = GetterUtil.getInteger(
				request.getParameter("documentYearVOY"), -1);
		if (documentYearVOY >= 0) {
			vmaItinerary.setDocumentYearVOY(documentYearVOY);
		}
		String timeOfArrival = ParamUtil.getString(actionRequest,
				"timeOfArrival", StringPool.BLANK);
		if (Validator.isNotNull(timeOfArrival)) {
			try {
				Date date = FormatData.formatDateShort3.parse(timeOfArrival);
				vmaItinerary.setTimeOfArrival(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String timeOfDeparture = ParamUtil.getString(actionRequest,
				"timeOfDeparture", StringPool.BLANK);
		if (Validator.isNotNull(timeOfDeparture)) {
			try {
				Date date = FormatData.formatDateShort3.parse(timeOfDeparture);
				vmaItinerary.setTimeOfDeparture(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaItinerary.setShipOwnerCode(shipOwnerCode);
			try {
				DmVmaShipOwner objVmaShipOwner = DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(shipOwnerCode);
				if (Validator.isNotNull(objVmaShipOwner)) {
					vmaItinerary.setShipOwnerName(objVmaShipOwner.getCompanyName());
				}
			} catch (Exception e) {
				
			}
			
		}
		String shipOwnerName = VMAUtils.getString(actionRequest,
				"shipOwnerName", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerName) && shipOwnerName.length() >= 2) {
			vmaItinerary.setShipOwnerName(shipOwnerName);
		}
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaItinerary.setShipOperatorCode(shipOperatorCode);
			try {
				DmVmaShipOwner objVmaShipOperator = DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(shipOperatorCode);
				if (Validator.isNotNull(objVmaShipOperator)) {
					vmaItinerary.setShipOperatorName(objVmaShipOperator.getCompanyName());
				}
			} catch (Exception e) {
				
			}
		}
		String shipOperatorName = VMAUtils.getString(actionRequest,
				"shipOperatorName", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorName)&& shipOperatorName.length() >= 2) {
			vmaItinerary.setShipOperatorName(shipOperatorName);
		}
		String arrivalShipAgencyCode = ParamUtil.getString(actionRequest,
				"arrivalShipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(arrivalShipAgencyCode)) {
			vmaItinerary.setArrivalShipAgencyCode(arrivalShipAgencyCode);
		}
		String arrivalShipAgency = VMAUtils.getString(actionRequest,
				"arrivalShipAgency", StringPool.BLANK);
		if (Validator.isNotNull(arrivalShipAgency)) {
			vmaItinerary.setArrivalShipAgency(arrivalShipAgency);
		}
		String departureShipAgencyCode = ParamUtil.getString(actionRequest,
				"departureShipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(departureShipAgencyCode)) {
			vmaItinerary.setDepartureShipAgencyCode(departureShipAgencyCode);
		}
		String departureShipAgency = VMAUtils.getString(actionRequest,
				"departureShipAgency", StringPool.BLANK);
		if (Validator.isNotNull(departureShipAgency)) {
			vmaItinerary.setDepartureShipAgency(departureShipAgency);
		}
		/*
		 * String modifiedDate = ParamUtil.getString(actionRequest,
		 * "modifiedDate", StringPool.BLANK); if
		 * (Validator.isNotNull(modifiedDate)) { try { Date date =
		 * FormatData.formatDateShort3.parse(modifiedDate);
		 * vmaItinerary.setModifiedDate(date); } catch (ParseException e) {
		 * log.error(e.getMessage()); } }
		 */

		String arrivalPortCode = ParamUtil.getString(actionRequest,
				"arrivalPortCode", StringPool.BLANK);

		if (Validator.isNotNull(arrivalPortCode)) {
			vmaItinerary.setArrivalPortCode(arrivalPortCode);
		}

		String nextPortCode = ParamUtil.getString(actionRequest,
				"nextPortCode", StringPool.BLANK);

		if (Validator.isNotNull(nextPortCode)) {
			vmaItinerary.setNextPortCode(nextPortCode);
		}

		String lastPortCode = ParamUtil.getString(actionRequest,
				"lastPortCode", StringPool.BLANK);

		if (Validator.isNotNull(lastPortCode)) {
			vmaItinerary.setLastPortCode(lastPortCode);
		}

		String dischargedPorts = VMAUtils.getString(actionRequest,
				"dischargedPorts", StringPool.BLANK);

		if (Validator.isNotNull(dischargedPorts)) {
			vmaItinerary.setDischargedPorts(dischargedPorts);
		}

		String cargoDescription = ParamUtil.getString(actionRequest,
				"cargoDescription", StringPool.BLANK);
		if (Validator.isNotNull(cargoDescription)) {
			vmaItinerary.setCargoDescription(cargoDescription);
		}
		String tugboatCondition1 = ParamUtil.getString(actionRequest,
				"tugboatCondition1", StringPool.BLANK);
		if (Validator.isNotNull(tugboatCondition1)) {
			vmaItinerary.setTugboatCondition1(tugboatCondition1);
		}
		String tugboatCondition2 = ParamUtil.getString(actionRequest,
				"tugboatCondition2", StringPool.BLANK);
		if (Validator.isNotNull(tugboatCondition2)) {
			vmaItinerary.setTugboatCondition2(tugboatCondition2);
		}
		String inRequestCodeGeneralDeclaration = ParamUtil.getString(
				actionRequest, "inRequestCodeGeneralDeclaration",
				StringPool.BLANK);
		if (Validator.isNotNull(inRequestCodeGeneralDeclaration)) {
			vmaItinerary
					.setInRequestCodeGeneralDeclaration(inRequestCodeGeneralDeclaration);
		}
		String outRequestCodeGeneralDeclaration = ParamUtil.getString(
				actionRequest, "outRequestCodeGeneralDeclaration",
				StringPool.BLANK);
		if (Validator.isNotNull(outRequestCodeGeneralDeclaration)) {
			vmaItinerary
					.setOutRequestCodeGeneralDeclaration(outRequestCodeGeneralDeclaration);
		}
		int payment2ServiceStatus = GetterUtil.getInteger(
				request.getParameter("payment2ServiceStatus"), -1);
		if (payment2ServiceStatus >= 0) {
			vmaItinerary.setPayment2ServiceStatus(payment2ServiceStatus);
		}
		int payment2ItineraryStatus = GetterUtil.getInteger(
				request.getParameter("payment2ItineraryStatus"), -1);
		if (payment2ItineraryStatus >= 0) {
			vmaItinerary.setPayment2ItineraryStatus(payment2ItineraryStatus);
		}
		int payment2ArrivalStatus = GetterUtil.getInteger(
				request.getParameter("payment2ArrivalStatus"), -1);
		if (payment2ArrivalStatus >= 0) {
			vmaItinerary.setPayment2ArrivalStatus(payment2ArrivalStatus);
		}
		int payment2DepartureStatus = GetterUtil.getInteger(
				request.getParameter("payment2DepartureStatus"), -1);
		if (payment2DepartureStatus >= 0) {
			vmaItinerary.setPayment2DepartureStatus(payment2DepartureStatus);
		}
		int payment2AnchorageStatus = GetterUtil.getInteger(
				request.getParameter("payment2AnchorageStatus"), -1);
		if (payment2AnchorageStatus >= 0) {
			vmaItinerary.setPayment2AnchorageStatus(payment2AnchorageStatus);
		}
		int payment2CargoStatus = GetterUtil.getInteger(
				request.getParameter("payment2CargoStatus"), -1);
		if (payment2CargoStatus >= 0) {
			vmaItinerary.setPayment2CargoStatus(payment2CargoStatus);
		}
		int domesticTransportCertificate = GetterUtil.getInteger(
				request.getParameter("domesticTransportCertificate"), -1);
		if (domesticTransportCertificate >= 0) {
			vmaItinerary
					.setDomesticTransportCertificate(domesticTransportCertificate);
		}
		String portClearancePreviousCertificate = ParamUtil.getString(
				actionRequest, "portClearancePreviousCertificate",
				StringPool.BLANK);
		if (Validator.isNotNull(portClearancePreviousCertificate)) {
			vmaItinerary
					.setPortClearancePreviousCertificate(portClearancePreviousCertificate);
		}

		int markupMaintainane = GetterUtil.getInteger(
				request.getParameter("markupMaintainane"), -1);
		if (markupMaintainane >= 0) {
			vmaItinerary.setMarkupMaintainane(markupMaintainane);
		}
		int markupConstruction = GetterUtil.getInteger(
				request.getParameter("markupConstruction"), -1);
		if (markupConstruction >= 0) {
			vmaItinerary.setMarkupConstruction(markupConstruction);
		}
		int markupDeconstruction = GetterUtil.getInteger(
				request.getParameter("markupDeconstruction"), -1);
		if (markupDeconstruction >= 0) {
			vmaItinerary.setMarkupDeconstruction(markupDeconstruction);
		}
		int payment2ProtestStatus = GetterUtil.getInteger(
				request.getParameter("payment2ProtestStatus"), -1);
		if (payment2ProtestStatus >= 0) {
			vmaItinerary.setPayment2ProtestStatus(payment2ProtestStatus);
		}
		String newShipOwnerCode = ParamUtil.getString(actionRequest,
				"newShipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(newShipOwnerCode)) {
			vmaItinerary.setNewShipOwnerCode(newShipOwnerCode);
		}
		String newShipOwnerName = VMAUtils.getString(actionRequest,
				"newShipOwnerName", StringPool.BLANK);
		if (Validator.isNotNull(newShipOwnerName)) {
			vmaItinerary.setNewShipOwnerName(newShipOwnerName);
		}

		String voyageNumber = ParamUtil.getString(actionRequest,
				"voyageNumber", StringPool.BLANK);
		if (Validator.isNotNull(voyageNumber)) {
			vmaItinerary.setVoyageNumber(voyageNumber);
		}
		return vmaItinerary;
	}

	public static VmaItinerary getObjectFromRequest(
			ActionRequest actionRequest, VmaItinerary vmaItinerary) {

		HttpServletRequest request = actionRequest;
		try {
			String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
					StringPool.BLANK);
			String callSign = VMAUtils.getString(actionRequest, "callSign",
					StringPool.BLANK);
			String flagStateOfShip = VMAUtils.getString(actionRequest,
					"flagStateOfShip", StringPool.BLANK);
			String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
					StringPool.BLANK);
			String vrCode = ParamUtil.getString(actionRequest, "vrCode",
					StringPool.BLANK);
			String registryNumber = VMAUtils.getString(actionRequest,
					"registryNumber", StringPool.BLANK);

			if (vmaItinerary != null
					&& checkChanged(vmaItinerary, nameOfShip, flagStateOfShip,
							imoNumber, callSign, registryNumber)) {
				// Thay đổi Tên tàu, Quốc tịch, Hô Hiệu
				// Chỉ cập nhật nối chuyến.
				int shipPosition = GetterUtil.getInteger(
						request.getParameter("shipPosition"), -1);
				if (shipPosition >= 2) {
					vmaItinerary.setShipPosition(shipPosition);
				} else {
					// Kiem tra dieu kien reset value ShipPostion
					int markedAsArrival = GetterUtil.getInteger(
							request.getParameter("markedAsArrival"), -1);
					
					if ((vmaItinerary.getShipPosition() == MessageType.SHIP_POSSITION_DEN_CANG)
					&& (markedAsArrival == ChuyenDichTrangThaiUtils.DANH_DAU_DA_DUYET
							|| markedAsArrival == ChuyenDichTrangThaiUtils.DANH_DAU_CHO_DONG_DAU_BCY
							|| markedAsArrival == ChuyenDichTrangThaiUtils.DANH_DAU_DA_KY_DUYET) ){
						vmaItinerary
						.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
					}
				}
				int markedAsDeparture = GetterUtil.getInteger(
						request.getParameter("markedAsDeparture"), -1);
				if (markedAsDeparture >= 0) {
					vmaItinerary.setMarkedAsDeparture(markedAsDeparture);
				}
				long documentNameOUT = GetterUtil.getLong(
						request.getParameter("documentNameOUT"), -1);
				if (documentNameOUT >= 0) {
					vmaItinerary.setDocumentNameOUT(documentNameOUT);
				}
				int documentYearOUT = GetterUtil.getInteger(
						request.getParameter("documentYearOUT"), -1);
				if (documentYearOUT >= 0) {
					vmaItinerary.setDocumentYearOUT(documentYearOUT);
				}
				int markedAsVoyage = GetterUtil.getInteger(
						request.getParameter("markedAsVoyage"), -1);
				if (markedAsVoyage >= 0) {
					vmaItinerary.setMarkedAsVoyage(markedAsVoyage);
				}
				long documentNameVOY = GetterUtil.getLong(
						request.getParameter("documentNameVOY"), -1);
				if (documentNameVOY >= 0) {
					vmaItinerary.setDocumentNameVOY(documentNameVOY);
				}
				int documentYearVOY = GetterUtil.getInteger(
						request.getParameter("documentYearVOY"), -1);
				if (documentYearVOY >= 0) {
					vmaItinerary.setDocumentYearVOY(documentYearVOY);
				}
				String timeOfDeparture = ParamUtil.getString(actionRequest,
						"timeOfDeparture", StringPool.BLANK);
				if (Validator.isNotNull(timeOfDeparture)) {
					try {
						Date date = FormatData.formatDateShort3
								.parse(timeOfDeparture);
						vmaItinerary.setTimeOfDeparture(date);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}
				}
				String departureShipAgencyCode = ParamUtil.getString(
						actionRequest, "departureShipAgencyCode",
						StringPool.BLANK);
				if (Validator.isNotNull(departureShipAgencyCode)) {
					vmaItinerary
							.setDepartureShipAgencyCode(departureShipAgencyCode);
				}
				String departureShipAgency = VMAUtils.getString(actionRequest,
						"departureShipAgency", StringPool.BLANK);
				if (Validator.isNotNull(departureShipAgency)) {
					vmaItinerary.setDepartureShipAgency(departureShipAgency);
				}
				String nextPortCode = ParamUtil.getString(actionRequest,
						"nextPortCode", StringPool.BLANK);

				if (Validator.isNotNull(nextPortCode)) {
					vmaItinerary.setNextPortCode(nextPortCode);
				}
				return vmaItinerary;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		int mtgateway = GetterUtil.getInteger(
				request.getParameter("mtgateway"), -1);

		if (mtgateway >= 0) {
			vmaItinerary.setMtgateway(mtgateway);
		}
		String maritimeCode = ParamUtil.getString(actionRequest,
				"maritimeCode", StringPool.BLANK);
		if (Validator.isNotNull(maritimeCode)) {
			vmaItinerary.setMaritimeCode(maritimeCode);
		}
		/*
		 * String itineraryNo = ParamUtil.getString(actionRequest,
		 * "itineraryNo", StringPool.BLANK); if
		 * (Validator.isNotNull(itineraryNo)) {
		 * vmaItinerary.setItineraryNo(itineraryNo); }
		 */
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaItinerary.setNameOfShip(nameOfShip);
		}
		String flagStateOfShip = VMAUtils.getString(actionRequest,
				"flagStateOfShip", StringPool.BLANK);
		if (Validator.isNotNull(flagStateOfShip)) {
			vmaItinerary.setFlagStateOfShip(flagStateOfShip);
		}
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
				StringPool.BLANK);
		if (Validator.isNotNull(imoNumber)) {
			vmaItinerary.setImoNumber(imoNumber);
		}
		String callSign = VMAUtils.getString(actionRequest, "callSign",
				StringPool.BLANK);
		if (Validator.isNotNull(callSign)) {
			vmaItinerary.setCallSign(callSign);
		}
		String vrCode = ParamUtil.getString(actionRequest, "vrCode",
				StringPool.BLANK);
		if (Validator.isNotNull(vrCode)) {
			vmaItinerary.setVrCode(vrCode);
		}
		String registryNumber = VMAUtils.getString(actionRequest,
				"registryNumber", StringPool.BLANK);
		if (Validator.isNotNull(registryNumber)) {
			vmaItinerary.setRegistryNumber(registryNumber);
		}
		int shipPosition = GetterUtil.getInteger(
				request.getParameter("shipPosition"), -1);
		if (shipPosition >= 2) {
			vmaItinerary.setShipPosition(shipPosition);
		}
		String shipTypeCode = ParamUtil.getString(actionRequest,
				"shipTypeCode", StringPool.BLANK);
		if (Validator.isNotNull(shipTypeCode)) {
			vmaItinerary.setShipTypeCode(shipTypeCode);
		}
		String vmaShipTypeCode = ParamUtil.getString(actionRequest,
				"vmaShipTypeCode", StringPool.BLANK);
		if (Validator.isNotNull(vmaShipTypeCode)) {
			vmaItinerary.setVmaShipTypeCode(vmaShipTypeCode);
		}
		String shipCaptain = VMAUtils.getString(actionRequest, "shipCaptain",
				StringPool.BLANK);
		if (Validator.isNotNull(shipCaptain)) {
			vmaItinerary.setShipCaptain(shipCaptain);
		}
		String nameOfMaster = VMAUtils.getString(actionRequest, "nameOfMaster",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfMaster)) {
			vmaItinerary.setShipCaptain(nameOfMaster);
		}
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaItinerary.setPortofAuthority(portofAuthority);
		}
		String representativeofAuthority = VMAUtils.getString(actionRequest,
				"representativeofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(representativeofAuthority)) {
			vmaItinerary
					.setRepresentativeofAuthority(representativeofAuthority);
		}
		int markedAsArrival = GetterUtil.getInteger(
				request.getParameter("markedAsArrival"), -1);
		if (markedAsArrival >= 0) {
			vmaItinerary.setMarkedAsArrival(markedAsArrival);
		}
		int markedAsDeparture = GetterUtil.getInteger(
				request.getParameter("markedAsDeparture"), -1);
		if (markedAsDeparture >= 0) {
			vmaItinerary.setMarkedAsDeparture(markedAsDeparture);
		}
		int markedAsTransmit = GetterUtil.getInteger(
				request.getParameter("markedAsTransmit"), -1);
		if (markedAsTransmit >= 0) {
			vmaItinerary.setMarkedAsTransmit(markedAsTransmit);
		}
		int markedAsVoyage = GetterUtil.getInteger(
				request.getParameter("markedAsVoyage"), -1);
		if (markedAsVoyage >= 0) {
			vmaItinerary.setMarkedAsVoyage(markedAsVoyage);
		}
		long documentNameIN = GetterUtil.getLong(
				request.getParameter("documentNameIN"), -1);
		if (documentNameIN >= 0) {
			vmaItinerary.setDocumentNameIN(documentNameIN);
		}
		int documentYearIN = GetterUtil.getInteger(
				request.getParameter("documentYearIN"), -1);
		if (documentYearIN >= 0) {
			vmaItinerary.setDocumentYearIN(documentYearIN);
		}
		long documentNameOUT = GetterUtil.getLong(
				request.getParameter("documentNameOUT"), -1);
		if (documentNameOUT >= 0) {
			vmaItinerary.setDocumentNameOUT(documentNameOUT);
		}
		int documentYearOUT = GetterUtil.getInteger(
				request.getParameter("documentYearOUT"), -1);
		if (documentYearOUT >= 0) {
			vmaItinerary.setDocumentYearOUT(documentYearOUT);
		}
		long documentNameTRA = GetterUtil.getLong(
				request.getParameter("documentNameTRA"), -1);
		if (documentNameTRA >= 0) {
			vmaItinerary.setDocumentNameTRA(documentNameTRA);
		}
		int documentYearTRA = GetterUtil.getInteger(
				request.getParameter("documentYearTRA"), -1);
		if (documentYearTRA >= 0) {
			vmaItinerary.setDocumentYearTRA(documentYearTRA);
		}
		long documentNameVOY = GetterUtil.getLong(
				request.getParameter("documentNameVOY"), -1);
		if (documentNameVOY >= 0) {
			vmaItinerary.setDocumentNameVOY(documentNameVOY);
		}
		int documentYearVOY = GetterUtil.getInteger(
				request.getParameter("documentYearVOY"), -1);
		if (documentYearVOY >= 0) {
			vmaItinerary.setDocumentYearVOY(documentYearVOY);
		}
		String timeOfArrival = ParamUtil.getString(actionRequest,
				"timeOfArrival", StringPool.BLANK);
		if (Validator.isNotNull(timeOfArrival)) {
			try {
				Date date = FormatData.formatDateShort3.parse(timeOfArrival);
				vmaItinerary.setTimeOfArrival(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String timeOfDeparture = ParamUtil.getString(actionRequest,
				"timeOfDeparture", StringPool.BLANK);
		if (Validator.isNotNull(timeOfDeparture)) {
			try {
				Date date = FormatData.formatDateShort3.parse(timeOfDeparture);
				vmaItinerary.setTimeOfDeparture(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {			
			vmaItinerary.setShipOwnerCode(shipOwnerCode);
			
			try {
				DmVmaShipOwner objVmaShipOwner = DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(shipOwnerCode);
				if (Validator.isNotNull(objVmaShipOwner)) {
					vmaItinerary.setShipOwnerName(objVmaShipOwner.getCompanyName());
				}
			} catch (Exception e) {
				
			}
		}
		String shipOwnerName = VMAUtils.getString(actionRequest,
				"shipOwnerName", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerName)) {
			vmaItinerary.setShipOwnerName(shipOwnerName);
		}
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaItinerary.setShipOperatorCode(shipOperatorCode);
			
			try {
				DmVmaShipOwner objVmaShipOperator = DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(shipOperatorCode);
				if (Validator.isNotNull(objVmaShipOperator)) {
					vmaItinerary.setShipOperatorName(objVmaShipOperator.getCompanyName());
				}
			} catch (Exception e) {
				
			}
		}
		String shipOperatorName = VMAUtils.getString(actionRequest,
				"shipOperatorName", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorName)) {
			vmaItinerary.setShipOperatorName(shipOperatorName);
		}
		String arrivalShipAgencyCode = ParamUtil.getString(actionRequest,
				"arrivalShipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(arrivalShipAgencyCode)) {
			vmaItinerary.setArrivalShipAgencyCode(arrivalShipAgencyCode);
		}
		String arrivalShipAgency = VMAUtils.getString(actionRequest,
				"arrivalShipAgency", StringPool.BLANK);
		if (Validator.isNotNull(arrivalShipAgency)) {
			vmaItinerary.setArrivalShipAgency(arrivalShipAgency);
		}
		String departureShipAgencyCode = ParamUtil.getString(actionRequest,
				"departureShipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(departureShipAgencyCode)) {
			vmaItinerary.setDepartureShipAgencyCode(departureShipAgencyCode);
		}
		String departureShipAgency = VMAUtils.getString(actionRequest,
				"departureShipAgency", StringPool.BLANK);
		if (Validator.isNotNull(departureShipAgency)) {
			vmaItinerary.setDepartureShipAgency(departureShipAgency);
		}
		/*
		 * String modifiedDate = ParamUtil.getString(actionRequest,
		 * "modifiedDate", StringPool.BLANK); if
		 * (Validator.isNotNull(modifiedDate)) { try { Date date =
		 * FormatData.formatDateShort3.parse(modifiedDate);
		 * vmaItinerary.setModifiedDate(date); } catch (ParseException e) {
		 * log.error(e.getMessage()); } }
		 */

		String arrivalPortCode = ParamUtil.getString(actionRequest,
				"arrivalPortCode", StringPool.BLANK);

		if (Validator.isNotNull(arrivalPortCode)) {
			vmaItinerary.setArrivalPortCode(arrivalPortCode);
		}

		String nextPortCode = ParamUtil.getString(actionRequest,
				"nextPortCode", StringPool.BLANK);

		if (Validator.isNotNull(nextPortCode)) {
			vmaItinerary.setNextPortCode(nextPortCode);
		}

		String lastPortCode = ParamUtil.getString(actionRequest,
				"lastPortCode", StringPool.BLANK);

		if (Validator.isNotNull(lastPortCode)) {
			vmaItinerary.setLastPortCode(lastPortCode);
		}

		String dischargedPorts = VMAUtils.getString(actionRequest,
				"dischargedPorts", StringPool.BLANK);

		if (Validator.isNotNull(dischargedPorts)) {
			vmaItinerary.setDischargedPorts(dischargedPorts);
		}

		String cargoDescription = ParamUtil.getString(actionRequest,
				"cargoDescription", StringPool.BLANK);
		if (Validator.isNotNull(cargoDescription)) {
			vmaItinerary.setCargoDescription(cargoDescription);
		}
		String tugboatCondition1 = ParamUtil.getString(actionRequest,
				"tugboatCondition1", StringPool.BLANK);
		if (Validator.isNotNull(tugboatCondition1)) {
			vmaItinerary.setTugboatCondition1(tugboatCondition1);
		}
		String tugboatCondition2 = ParamUtil.getString(actionRequest,
				"tugboatCondition2", StringPool.BLANK);
		if (Validator.isNotNull(tugboatCondition2)) {
			vmaItinerary.setTugboatCondition2(tugboatCondition2);
		}
		String inRequestCodeGeneralDeclaration = ParamUtil.getString(
				actionRequest, "inRequestCodeGeneralDeclaration",
				StringPool.BLANK);
		if (Validator.isNotNull(inRequestCodeGeneralDeclaration)) {
			vmaItinerary
					.setInRequestCodeGeneralDeclaration(inRequestCodeGeneralDeclaration);
		}
		String outRequestCodeGeneralDeclaration = ParamUtil.getString(
				actionRequest, "outRequestCodeGeneralDeclaration",
				StringPool.BLANK);
		if (Validator.isNotNull(outRequestCodeGeneralDeclaration)) {
			vmaItinerary
					.setOutRequestCodeGeneralDeclaration(outRequestCodeGeneralDeclaration);
		}
		int payment2ServiceStatus = GetterUtil.getInteger(
				request.getParameter("payment2ServiceStatus"), -1);
		if (payment2ServiceStatus >= 0) {
			vmaItinerary.setPayment2ServiceStatus(payment2ServiceStatus);
		}
		int payment2ItineraryStatus = GetterUtil.getInteger(
				request.getParameter("payment2ItineraryStatus"), -1);
		if (payment2ItineraryStatus >= 0) {
			vmaItinerary.setPayment2ItineraryStatus(payment2ItineraryStatus);
		}
		int payment2ArrivalStatus = GetterUtil.getInteger(
				request.getParameter("payment2ArrivalStatus"), -1);
		if (payment2ArrivalStatus >= 0) {
			vmaItinerary.setPayment2ArrivalStatus(payment2ArrivalStatus);
		}
		int payment2DepartureStatus = GetterUtil.getInteger(
				request.getParameter("payment2DepartureStatus"), -1);
		if (payment2DepartureStatus >= 0) {
			vmaItinerary.setPayment2DepartureStatus(payment2DepartureStatus);
		}
		int payment2AnchorageStatus = GetterUtil.getInteger(
				request.getParameter("payment2AnchorageStatus"), -1);
		if (payment2AnchorageStatus >= 0) {
			vmaItinerary.setPayment2AnchorageStatus(payment2AnchorageStatus);
		}
		int payment2CargoStatus = GetterUtil.getInteger(
				request.getParameter("payment2CargoStatus"), -1);
		if (payment2CargoStatus >= 0) {
			vmaItinerary.setPayment2CargoStatus(payment2CargoStatus);
		}
		int domesticTransportCertificate = GetterUtil.getInteger(
				request.getParameter("domesticTransportCertificate"), -1);
		if (domesticTransportCertificate >= 0) {
			vmaItinerary
					.setDomesticTransportCertificate(domesticTransportCertificate);
		}
		String portClearancePreviousCertificate = ParamUtil.getString(
				actionRequest, "portClearancePreviousCertificate",
				StringPool.BLANK);
		if (Validator.isNotNull(portClearancePreviousCertificate)) {
			vmaItinerary
					.setPortClearancePreviousCertificate(portClearancePreviousCertificate);
		}
		String shipPreviousName = VMAUtils.getString(actionRequest,
				"shipPreviousName", StringPool.BLANK);
		if (Validator.isNotNull(shipPreviousName)) {
			vmaItinerary.setShipPreviousName(shipPreviousName);
		}
		int markupMaintainane = GetterUtil.getInteger(
				request.getParameter("markupMaintainane"), -1);
		if (markupMaintainane >= 0) {
			vmaItinerary.setMarkupMaintainane(markupMaintainane);
		}
		int markupConstruction = GetterUtil.getInteger(
				request.getParameter("markupConstruction"), -1);
		if (markupConstruction >= 0) {
			vmaItinerary.setMarkupConstruction(markupConstruction);
		}
		int markupDeconstruction = GetterUtil.getInteger(
				request.getParameter("markupDeconstruction"), -1);
		if (markupDeconstruction >= 0) {
			vmaItinerary.setMarkupDeconstruction(markupDeconstruction);
		}
		int payment2ProtestStatus = GetterUtil.getInteger(
				request.getParameter("payment2ProtestStatus"), -1);
		if (payment2ProtestStatus >= 0) {
			vmaItinerary.setPayment2ProtestStatus(payment2ProtestStatus);
		}
		String newShipOwnerCode = ParamUtil.getString(actionRequest,
				"newShipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(newShipOwnerCode)) {
			vmaItinerary.setNewShipOwnerCode(newShipOwnerCode);
		}
		String newShipOwnerName = VMAUtils.getString(actionRequest,
				"newShipOwnerName", StringPool.BLANK);
		if (Validator.isNotNull(newShipOwnerName)) {
			vmaItinerary.setNewShipOwnerName(newShipOwnerName);
		}

		/*
		 * String itineraryNo = ParamUtil.getString(actionRequest,
		 * "itineraryNo", StringPool.BLANK);
		 * if(Validator.isNotNull(itineraryNo)){
		 * vmaItinerary.setItineraryNo(itineraryNo); }
		 */

		String voyageNumber = ParamUtil.getString(actionRequest,
				"voyageNumber", StringPool.BLANK);
		if (Validator.isNotNull(voyageNumber)) {
			vmaItinerary.setVoyageNumber(voyageNumber);
		}

		return vmaItinerary;
	}

	public static JSONObject addVmaItinerary(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaItinerary vmaItinerary = getObjectFromRequest(themeDisplay,
				actionRequest);

		if (vmaItinerary == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.addVmaItinerary(vmaItinerary);
			result = VMAUtils.object2Json(vmaItinerary,
					VmaItinerary.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject addVmaItinerary_VmaItinerarySchedule(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaItinerary vmaItinerary = getObjectFromRequest(themeDisplay,
				actionRequest);

		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(actionRequest, "callSign",
				StringPool.BLANK);
		String flagStateOfShip = VMAUtils.getString(actionRequest,
				"flagStateOfShip", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(actionRequest,
				"registryNumber", StringPool.BLANK);

		VmaItinerarySchedule vmaItinerarySchedule = VmaItineraryScheduleUtils
				.getObjectFromRequest(themeDisplay, actionRequest);

		if (vmaItinerary == null || vmaItinerarySchedule == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			// Fix bug double click
			VmaItinerarySchedule temp = null;
			/*
			 * if (vmaItinerarySchedule.getDocumentName() == 0 &&
			 * vmaItinerarySchedule.getDocumentYear() == 0) { try { temp =
			 * VmaItineraryScheduleLocalServiceUtil
			 * .findByItineraryNo_NoticeShipType(
			 * vmaItinerarySchedule.getItineraryNo(),
			 * vmaItinerarySchedule.getNoticeShipType()); } catch (Exception e)
			 * { // nothing to do } } else {
			 * VmaItineraryScheduleLocalServiceUtil
			 * .findByDocumentName_DocumentYear_NoticeShipType(
			 * vmaItinerarySchedule.getDocumentName(),
			 * vmaItinerarySchedule.getDocumentYear(),
			 * vmaItinerarySchedule.getNoticeShipType()); }
			 */

			try {
				temp = VmaItineraryScheduleLocalServiceUtil
						.findByitineraryNo_certificateNo_noticeShipType(
								vmaItinerarySchedule.getItineraryNo(),
								vmaItinerarySchedule.getNoticeShipType(),
								vmaItinerarySchedule.getCertificateNo());
				
				if ((Validator.isNull(temp)) && ((vmaItinerarySchedule.getNoticeShipType() == 1) || (vmaItinerarySchedule.getNoticeShipType() == 2))) {
					temp = VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo_NoticeShipType(
									vmaItinerarySchedule.getItineraryNo(),
									vmaItinerarySchedule.getNoticeShipType());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (temp == null) {
				result = VmaItineraryLocalServiceUtil
						.addVmaItinerary_VmaItinerarySchedule(vmaItinerary,
								vmaItinerarySchedule);
				// Them ban ghi vao vmascheduletranfer khi co su thay doi thong
				// tin tau luot vao luot roi
				if (checkChanged(vmaItinerary, nameOfShip, flagStateOfShip,
						imoNumber, callSign, registryNumber)) {
					long itineraryScheduleId = result
							.getLong("vmaItineraryScheduleId");
					if (itineraryScheduleId > 0) {
						VmaScheduleTransfer vmaScheduleTransfer = null;
						try {
							vmaScheduleTransfer = VmaScheduleTransferLocalServiceUtil
									.findByItineraryScheduleId(itineraryScheduleId);
						} catch (Exception e) {
						}
						vmaScheduleTransfer = VmaScheduleTransferUtils
								.getObjectFromRequest(vmaScheduleTransfer,
										themeDisplay, actionRequest);
						vmaScheduleTransfer
								.setItineraryScheduleId(itineraryScheduleId);
						vmaScheduleTransfer = VmaScheduleTransferUtils
								.updateVmaScheduleTransfer(vmaScheduleTransfer);
					}
				}
				// Write logs - Add by Dungnv
				if (userPort != null) {
					VmaAuditLogLocalServiceUtil.addVmaAuditLog(
							userPort.getUserId(),
							userSign != null ? userSign.getModifyuser()
									: StringPool.BLANK,
							LogsConstant.THEM,
							"vma_itinerary, vma_itinerary_schedule",
							vmaItinerary.getItineraryNo(),
							"vmaItineraryScheduleId: "
									+ result.getLong("vmaItineraryScheduleId")
									+ StringPool.COMMA + "shipBoat: "
									+ result.getString("shipBoat")
									+ StringPool.COMMA + "noticeShipType: "
									+ result.getInt("noticeShipType")
									+ StringPool.COMMA + "nameOfShip: "
									+ result.getString("nameOfShip"));
				}
				if (result != null && result.length() > 0
						&& vmaItinerarySchedule.getNoticeShipType() == 1) {

					VmaScheduleAnchorage vmaScheduleAnchorage = VMAUtils
							.updateVmaScheduleAnchorage(null, vmaItinerary,
									vmaItinerarySchedule);

					vmaScheduleAnchorage.setPurposeCode(vmaItinerarySchedule.getPurposeCode());
					vmaScheduleAnchorage.setPurposeSpecified(vmaItinerarySchedule.getPurposeSpecified());
					vmaScheduleAnchorage
							.setAnchoringDateFrom(vmaItinerarySchedule
									.getTimeOfArrival());
					
					VmaScheduleAnchorageLocalServiceUtil
							.addVmaScheduleAnchorage(vmaScheduleAnchorage);

				} else if (result != null && result.length() > 0
						&& vmaItinerarySchedule.getNoticeShipType() == 2) {

					VmaScheduleAnchorage vmaScheduleAnchorage = VMAUtils
							.updateVmaScheduleAnchorage(null, vmaItinerary,
									vmaItinerarySchedule);

					vmaScheduleAnchorage
							.setAnchoringDateTo(vmaItinerarySchedule
									.getTimeOfDeparture());
					
					VmaScheduleAnchorageLocalServiceUtil
							.addVmaScheduleAnchorage(vmaScheduleAnchorage);

				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject addVmaItinerary_VmaScheduleShifting(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaItinerary vmaItinerary = getObjectFromRequest(themeDisplay,
				actionRequest);

		VmaScheduleShifting vmaScheduleShifting = VmaScheduleShiftingUtils
				.getObjectFromRequest(themeDisplay, actionRequest);

		if (vmaItinerary == null || vmaScheduleShifting == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		VmaScheduleShifting vmaScheduleShiftingTmp = null;
		try {
			vmaScheduleShiftingTmp = VmaScheduleShiftingLocalServiceUtil
					.findByItineraryNo_CertificateNo(
							vmaScheduleShifting.getItineraryNo(),
							vmaScheduleShifting.getCertificateNo());
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (vmaScheduleShiftingTmp != null) {
			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "duplicate_entry"),
					"duplicate_entry", StringPool.BLANK);
		}

		try {
			result = VmaItineraryLocalServiceUtil
					.addVmaItinerary_VmaScheduleShifting(vmaItinerary,
							vmaScheduleShifting);

			// Write logs - add by Dungnv
			if (userPort != null) {
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK,
						LogsConstant.THEM,
						"vma_itinerary, vma_schedule_shifting",
						vmaItinerary.getItineraryNo(),
						"vmaScheduleShiftingId: "
								+ result.getLong("vmaScheduleShiftingId")
								+ StringPool.COMMA + "nameOfShip: "
								+ result.getString("nameOfShip"));
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject addVmaItinerary_VmaScheduleAnchorage(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaItinerary vmaItinerary = getObjectFromRequest(themeDisplay,
				actionRequest);

		VmaScheduleAnchorage vmaScheduleAnchorage = VmaScheduleAnchorageUtils
				.getObjectFromRequest(themeDisplay, actionRequest);

		if (vmaItinerary == null || vmaScheduleAnchorage == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {

			result = VmaItineraryLocalServiceUtil
					.addVmaItinerary_VmaScheduleAnchorage(vmaItinerary,
							vmaScheduleAnchorage);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaItinerary(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaItinerary vmaItinerary = getObjectFromRequest(themeDisplay,
				actionRequest);
		if (vmaItinerary == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.updateVmaItinerary(vmaItinerary);
			result = VMAUtils.object2Json(vmaItinerary,
					VmaItinerary.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaItinerary(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaItineraryId");
		if (id > 0) {
			String description = "";
			try {
				// Đối tượng gốc
				VmaItinerary curItinerary = VmaItineraryLocalServiceUtil
						.getVmaItinerary(id);
				String itineraryNo = curItinerary.getItineraryNo();

				// Đối tượng liên quan
				try {
					List<VmaItinerarySchedule> lstItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstItinerarySchedule != null) {
						for (VmaItinerarySchedule cur : lstItinerarySchedule) {
							VmaItineraryScheduleLocalServiceUtil
									.deleteVmaItinerarySchedule(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaScheduleTugboat> lstScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstScheduleTugboat != null) {
						for (VmaScheduleTugboat cur : lstScheduleTugboat) {
							VmaScheduleTugboatLocalServiceUtil
									.deleteVmaScheduleTugboat(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaSchedulePilot> lstSchedulePilot = VmaSchedulePilotLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstSchedulePilot != null) {
						for (VmaSchedulePilot cur : lstSchedulePilot) {
							VmaSchedulePilotLocalServiceUtil
									.deleteVmaSchedulePilot(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaScheduleShifting> lstScheduleShifting = VmaScheduleShiftingLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstScheduleShifting != null) {
						for (VmaScheduleShifting cur : lstScheduleShifting) {
							VmaScheduleShiftingLocalServiceUtil
									.deleteVmaScheduleShifting(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaScheduleAnchorage> lstScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstScheduleAnchorage != null) {
						for (VmaScheduleAnchorage cur : lstScheduleAnchorage) {
							VmaScheduleAnchorageLocalServiceUtil
									.deleteVmaScheduleAnchorage(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaScheduleSecurity> lstScheduleSecurity = VmaScheduleSecurityLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstScheduleSecurity != null) {
						for (VmaScheduleSecurity cur : lstScheduleSecurity) {
							VmaScheduleSecurityLocalServiceUtil
									.deleteVmaScheduleSecurity(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaScheduleShipyard> lstScheduleShipyard = VmaScheduleShipyardLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstScheduleShipyard != null) {
						for (VmaScheduleShipyard cur : lstScheduleShipyard) {
							VmaScheduleShipyardLocalServiceUtil
									.deleteVmaScheduleShipyard(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaScheduleLaunching> lstScheduleLaunching = VmaScheduleLaunchingLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstScheduleLaunching != null) {
						for (VmaScheduleLaunching cur : lstScheduleLaunching) {
							VmaScheduleLaunchingLocalServiceUtil
									.deleteVmaScheduleLaunching(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaScheduleTesting> lstScheduleTesting = VmaScheduleTestingLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstScheduleTesting != null) {
						for (VmaScheduleTesting cur : lstScheduleTesting) {
							VmaScheduleTestingLocalServiceUtil
									.deleteVmaScheduleTesting(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaScheduleMerging> lstScheduleMerging = VmaScheduleMergingLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstScheduleMerging != null) {
						for (VmaScheduleMerging cur : lstScheduleMerging) {
							VmaScheduleMergingLocalServiceUtil
									.deleteVmaScheduleMerging(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaScheduleTugboatList> lstScheduleTugboat_list = VmaScheduleTugboatListLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstScheduleTugboat_list != null) {
						for (VmaScheduleTugboatList cur : lstScheduleTugboat_list) {
							VmaScheduleTugboatListLocalServiceUtil
									.deleteVmaScheduleTugboatList(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				try {
					List<VmaSchedulePilotList> lstSchedulePilot_list = VmaSchedulePilotListLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstSchedulePilot_list != null) {
						for (VmaSchedulePilotList cur : lstSchedulePilot_list) {
							VmaSchedulePilotListLocalServiceUtil
									.deleteVmaSchedulePilotList(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}
				try {
					List<VmaScheduleCargolist> lstScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstScheduleCargolist != null) {
						for (VmaScheduleCargolist cur : lstScheduleCargolist) {
							VmaScheduleCargolistLocalServiceUtil
									.deleteVmaScheduleCargolist(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}
				try {
					List<VmaItineraryRemark> lstItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstItineraryRemarks != null) {
						for (VmaItineraryRemark cur : lstItineraryRemarks) {
							VmaItineraryRemarksLocalServiceUtil
									.deleteVmaItineraryRemarks(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}
				try {
					List<VmaItineraryProtest> lstItineraryProtest = VmaItineraryProtestLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstItineraryProtest != null) {
						for (VmaItineraryProtest cur : lstItineraryProtest) {
							VmaItineraryProtestLocalServiceUtil
									.deleteVmaItineraryProtest(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}
				try {
					List<VmaTransactionSlip> lstTransactionSlip = VmaTransactionSlipLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstTransactionSlip != null) {
						for (VmaTransactionSlip cur : lstTransactionSlip) {
							VmaTransactionSlipLocalServiceUtil
									.deleteVmaTransactionSlip(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}
				try {
					List<VmaTransactionSlipDetails> lstTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					if (lstTransactionSlipDetails != null) {
						for (VmaTransactionSlipDetails cur : lstTransactionSlipDetails) {
							VmaTransactionSlipDetailsLocalServiceUtil
									.deleteVmaTransactionSlipDetails(cur);
						}
					}
				} catch (Exception e) {
					description += "\n" + e.getMessage();
				}

				return VMAUtils.createResponseMessage(
						LanguageUtil.get(themeDisplay.getLocale(), "success"),
						"", description);
			} catch (Exception e) {
				return VMAUtils.createResponseMessage(LanguageUtil.get(
						themeDisplay.getLocale(), "system_error"),
						"system_error", description);
			}
		} else {
			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "incorrect_identifier"),
					"incorrect_identifier", StringPool.BLANK);
		}
	}

	public static JSONObject doFind(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);

		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode", StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		String arrivalShipAgencyCode = ParamUtil.getString(resourceRequest,
				"arrivalShipAgencyCode", StringPool.BLANK);

		int shipPosition = GetterUtil.getInteger(
				request.getParameter("shipPosition"), -1);

		try {
			String searchQuery = generateQuery("search", nameOfShip, imoNumber,
					flagStateOfShip, callSign, timeOfArrival, timeOfDeparture,
					arrivalShipAgencyCode, itineraryNo, maritimeCode,
					shipPosition >= 0 ? shipPosition : null);

			String countQuery = generateQuery("count", nameOfShip, imoNumber,
					flagStateOfShip, callSign, timeOfArrival, timeOfDeparture,
					arrivalShipAgencyCode, itineraryNo, maritimeCode,
					shipPosition >= 0 ? shipPosition : null);

			JSONObject result = VmaItineraryLocalServiceUtil.findVmaItinerary(
					searchQuery, countQuery, start, end);
			/*
			 * if(documentName > 0 && documentYear > 0){ VmaItinerary
			 * vmaItinerary =
			 * VmaItineraryLocalServiceUtil.fetchBydocumentNameOUT_documentYearOUT
			 * (documentName, documentYear); if(vmaItinerary != null){
			 * List<VmaScheduleTransfer> vmaScheduleTransfers =
			 * VmaScheduleTransferLocalServiceUtil
			 * .findByItineraryNo_NoticeShipType(vmaItinerary.getItineraryNo(),
			 * 2); VmaScheduleTransfer vmaScheduleTransfer = null; if
			 * (vmaScheduleTransfers != null && !vmaScheduleTransfers.isEmpty())
			 * { vmaScheduleTransfer = vmaScheduleTransfers.get(0); }
			 * if(vmaScheduleTransfer != null){ result.put("nameOfShip",
			 * vmaScheduleTransfer.getShipName()); result.put("flagStateOfShip",
			 * vmaScheduleTransfer.getFlagStateOfShip()); result.put("callSign",
			 * vmaScheduleTransfer.getCallSign()); result.put("imoNumber",
			 * vmaScheduleTransfer.getIMONumber()); result.put("registryNumber",
			 * vmaScheduleTransfer.getRegistryNumber()); result.put("vrCode",
			 * vmaScheduleTransfer.getVRCode()); } } }
			 */
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

	public static JSONObject dsChoXacBaoTinhPhi(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);

		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode", StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		String arrivalShipAgencyCode = ParamUtil.getString(resourceRequest,
				"arrivalShipAgencyCode", StringPool.BLANK);

		int shipPosition = GetterUtil.getInteger(
				request.getParameter("shipPosition"), -1);

		try {
			String searchQuery = generateQuery2("search", nameOfShip,
					imoNumber, flagStateOfShip, callSign, timeOfArrival,
					timeOfDeparture, arrivalShipAgencyCode, itineraryNo,
					maritimeCode, shipPosition >= 0 ? shipPosition : null);

			String countQuery = generateQuery2("count", nameOfShip, imoNumber,
					flagStateOfShip, callSign, timeOfArrival, timeOfDeparture,
					arrivalShipAgencyCode, itineraryNo, maritimeCode,
					shipPosition >= 0 ? shipPosition : null);

			JSONObject result = VmaItineraryLocalServiceUtil.findVmaItinerary(
					searchQuery, countQuery, start, end);

			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

	public static long countChoXacBaoTinhPhi(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);

		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode", StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		String arrivalShipAgencyCode = ParamUtil.getString(resourceRequest,
				"arrivalShipAgencyCode", StringPool.BLANK);

		int shipPosition = GetterUtil.getInteger(
				request.getParameter("shipPosition"), -1);

		try {
			String countQuery = generateQuery2("count", nameOfShip, imoNumber,
					flagStateOfShip, callSign, timeOfArrival, timeOfDeparture,
					arrivalShipAgencyCode, itineraryNo, maritimeCode,
					shipPosition >= 0 ? shipPosition : null);

			return VmaItineraryLocalServiceUtil.countVmaItinerary(countQuery);

		} catch (Exception e) {
			log.error(e.getMessage());
			return -1;
		}
	}

	public static void exportVmaItinerary(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode", StringPool.BLANK);
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		String arrivalShipAgencyCode = ParamUtil.getString(resourceRequest,
				"arrivalShipAgencyCode", StringPool.BLANK);
		String arrivalShipAgency = ParamUtil.getString(resourceRequest,
				"arrivalShipAgency", StringPool.BLANK);
		/*
		 * String departureShipAgencyCode = ParamUtil.getString(resourceRequest,
		 * "departureShipAgencyCode", StringPool.BLANK);
		 */
		String departureShipAgency = ParamUtil.getString(resourceRequest,
				"departureShipAgency", StringPool.BLANK);

		int shipPosition = GetterUtil.getInteger(
				request.getParameter("shipPosition"), -1);
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		String[][] exportData = null;

		/*
		 * String[] headers = new String[] {
		 * "STT","Tên tàu","IMO","Quốc tịch","Hô hiệu"
		 * ,"Dự kiến tàu đến","Dự kiến tàu rời","Đại lý" };
		 */

		String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u", "IMO",
				"Qu\u1ED1c t\u1ECBch", "H\u00F4 hi\u1EC7u",
				"D\u1EF1 ki\u1EBFn t\u00E0u \u0111\u1EBFn",
				"D\u1EF1 ki\u1EBFn t\u00E0u r\u1EDDi", "\u0110\u1EA1i l\u00FD" };

		try {

			String searchQuery = generateQuery("search", nameOfShip, imoNumber,
					flagStateOfShip, callSign, timeOfArrival, timeOfDeparture,
					arrivalShipAgencyCode, itineraryNo, maritimeCode,
					shipPosition >= 0 ? shipPosition : null);

			String countQuery = generateQuery("count", nameOfShip, imoNumber,
					flagStateOfShip, callSign, timeOfArrival, timeOfDeparture,
					arrivalShipAgencyCode, itineraryNo, maritimeCode,
					shipPosition >= 0 ? shipPosition : null);

			JSONObject objects = VmaItineraryLocalServiceUtil.findVmaItinerary(
					searchQuery, countQuery, -1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");

					imoNumber = object.getString("imoNumber");

					String stateName = object.getString("stateName");

					callSign = object.getString("callSign");

					timeOfArrival = object.getString("timeOfArrival");

					timeOfDeparture = object.getString("timeOfDeparture");

					arrivalShipAgency = object.getString("arrivalShipAgency");

					departureShipAgency = object
							.getString("departureShipAgency");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][3] = imoNumber;
					exportData[i][2] = stateName;
					exportData[i][4] = callSign;
					exportData[i][5] = timeOfArrival;
					exportData[i][6] = timeOfDeparture;
					exportData[i][7] = arrivalShipAgency + "-"
							+ departureShipAgency;

				}
			}

			String sheetName = "VMA-Itinerary";

			String fileName = sheetName + "-" + System.currentTimeMillis();

			String filePath = VMAUtils.doExportExcelFile(request,
					resourceResponse, sheetName, fileName, headers, exportData);
//todo response file in controller
//			resourceResponse
//					.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//			resourceResponse.setProperty("Content-Disposition",
//					"attachment; filename= " + fileName + ".xls");
//			resourceResponse.setCharacterEncoding("UTF-8");
//			// resourceResponse.setProperty("filename", fileName + ".xls");
//			resourceResponse.addProperty("FILE-NAME", fileName + ".xls");
//
//			resourceResponse.getPortletOutputStream().write(
//					VMAUtils.readFileToByteArray(new File(filePath)));

		} catch (Exception e) {
			log.error(e.getMessage());

		}
	}

	public static JSONObject doFindTempDocument(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 50);

		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		try {
			String searchQuery = generateQuery1("search", maritimeCode,
					imoNumber, callSign);

			String countQuery = generateQuery1("count", maritimeCode,
					imoNumber, callSign);

			return TempDocumentLocalServiceUtil.findTempDocument(searchQuery,
					countQuery, start, end);

		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

	public static JSONObject doFindVmaItinerary(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		int start = GetterUtil.getInteger(request.getParameter("start"), 0);

		int end = GetterUtil.getInteger(request.getParameter("end"), 50);

		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);

		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		try {
			String searchQuery = generateQuery2("search", maritimeCode,
					imoNumber, callSign);

			String countQuery = generateQuery2("count", maritimeCode,
					imoNumber, callSign);

			JSONObject result = VmaItineraryLocalServiceUtil.findVmaItinerary(
					searchQuery, countQuery, start, end);
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

	private static String generateQuery(String cmd, String shipName,
			String imoNumber, String flagStateOfShip, String callSign,
			String timeOfArrival, String timeOfDeparture,
			String arrivalShipAgencyCode, String itineraryNo,
			String maritimeCode, Integer shipPosition) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_itinerary as a";

		} else {
			sql = "SELECT a.* FROM vma_itinerary AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(shipName)) {
			condition.append(VMAUtils.buildSQLCondition("NameOfShip", "'%"
					+ shipName + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(maritimeCode)) {
			condition.append(VMAUtils.buildSQLCondition("MaritimeCode", "'"
					+ maritimeCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(flagStateOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("FlagStateOfShip", "'"
					+ flagStateOfShip + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(VMAUtils.buildSQLCondition("IMONumber", "'%"
					+ imoNumber + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(VMAUtils.buildSQLCondition("CallSign", "'%"
					+ callSign + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(arrivalShipAgencyCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ArrivalShipAgencyCode", "'" + arrivalShipAgencyCode + "'",
					"AND", StringPool.EQUAL, new String[] { "a" }));
		}

		if (Validator.isNotNull(timeOfArrival)) {
			Date date = null;

			try {
				date = FormatData.formatDateShort3.parse(timeOfArrival);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("TimeOfArrival",
						"'" + strDate + " 00:00:00'" + " AND '" + strDate
								+ " 23:59:59'", " AND ", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(timeOfDeparture)) {
			Date date = null;

			try {
				date = FormatData.formatDateShort3.parse(timeOfDeparture);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("TimeOfDeparture",
						"'" + strDate + " 00:00:00'" + " AND '" + strDate
								+ " 23:59:59'", " AND ", StringPool.BETWEEN));
			}
		}

		if (shipPosition != null) {
			condition.append(VMAUtils.buildSQLCondition("ShipPosition",
					+shipPosition, "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		// Edit by Dungnv - MarkedAsArrival, markedAsDeparture -> timeOfArrival,
		// timeOfDeparture
		condition
				.append(" ORDER BY a.MaritimeCode ASC, a.DocumentYearIN DESC, a.TimeOfArrival DESC, a.TimeOfDeparture DESC");

		return sql + condition.toString();
	}

	private static String generateQuery2(String cmd, String shipName,
			String imoNumber, String flagStateOfShip, String callSign,
			String timeOfArrival, String timeOfDeparture,
			String arrivalShipAgencyCode, String itineraryNo,
			String maritimeCode, Integer shipPosition) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_itinerary as a";

		} else {
			sql = "SELECT a.* FROM vma_itinerary AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(shipName)) {
			condition.append(VMAUtils.buildSQLCondition("NameOfShip", "'%"
					+ shipName + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(maritimeCode)) {
			condition.append(VMAUtils.buildSQLCondition("MaritimeCode", "'"
					+ maritimeCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(flagStateOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("FlagStateOfShip", "'"
					+ flagStateOfShip + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(VMAUtils.buildSQLCondition("IMONumber", "'%"
					+ imoNumber + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(VMAUtils.buildSQLCondition("CallSign", "'%"
					+ callSign + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(arrivalShipAgencyCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ArrivalShipAgencyCode", "'" + arrivalShipAgencyCode + "'",
					"AND", StringPool.EQUAL, new String[] { "a" }));
		}

		if (Validator.isNotNull(timeOfArrival)) {
			Date date = null;

			try {
				date = FormatData.formatDateShort3.parse(timeOfArrival);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("TimeOfArrival",
						"'" + strDate + " 00:00:00'" + " AND '" + strDate
								+ " 23:59:59'", " AND ", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(timeOfDeparture)) {
			Date date = null;

			try {
				date = FormatData.formatDateShort3.parse(timeOfDeparture);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("TimeOfDeparture",
						"'" + strDate + " 00:00:00'" + " AND '" + strDate
								+ " 23:59:59'", " AND ", StringPool.BETWEEN));
			}
		}

		if (shipPosition != null) {
			condition.append(VMAUtils.buildSQLCondition("ShipPosition",
					+shipPosition, "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		condition
				.append(" AND ((MarkedAsArrival IN (5 , 8, 9)) OR (MarkedAsDeparture IN (5 , 8, 9))) AND Payment2ServiceStatus = 0 AND Payment2ItineraryStatus = 0 AND Payment2ArrivalStatus = 0 AND Payment2DepartureStatus = 0 ");

		// Edit by Dungnv - MarkedAsArrival, markedAsDeparture -> timeOfArrival,
		// timeOfDeparture
		condition
				.append(" ORDER BY a.MaritimeCode ASC, a.DocumentYearIN DESC, a.TimeOfArrival DESC, a.TimeOfDeparture DESC");

		return sql + condition.toString();
	}

	private static String generateQuery1(String cmd, String maritimeCode,
			String imoNumber, String callSign) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM temp_document as a";

		} else {
			sql = "SELECT a.* FROM temp_document AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(maritimeCode)) {
			condition.append(VMAUtils.buildSQLCondition("MaritimeCode", "'"
					+ maritimeCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(VMAUtils.buildSQLCondition("IMO", "'" + imoNumber
					+ "'", "AND", StringPool.EQUAL, new String[] { "a" }));
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(VMAUtils.buildSQLCondition("CallSign", "'"
					+ callSign + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		condition
				.append(" AND DocumentTypeCode IN ('XC','QC','5','7','9','11','13','15','17')");

		return sql + condition.toString();
	}

	private static String generateQuery2(String cmd, String maritimeCode,
			String imoNumber, String callSign) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_itinerary as a";

		} else {
			sql = "SELECT a.* FROM vma_itinerary AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(maritimeCode)) {
			condition.append(VMAUtils.buildSQLCondition("MaritimeCode", "'"
					+ maritimeCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(VMAUtils.buildSQLCondition("IMONumber", "'"
					+ imoNumber + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(VMAUtils.buildSQLCondition("CallSign", "'"
					+ callSign + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}
		// condition.append(" ORDER BY a.MaritimeCode ASC, a.DocumentYearIN DESC, a.MarkedAsArrival, a.MarkedAsDeparture ASC");

		return sql + condition.toString();
	}

	public static JSONObject findVmaItineraryByItineraryNo(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
					.findByitineraryNo(itineraryNo);
			result = VMAUtils.object2Json(vmaItinerary,
					VmaItinerary.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject updateShipPosition(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		int shipPosition = GetterUtil.getInteger(
				request.getParameter("shipPosition"), -1);

		VmaItinerary vmaItinerary = null;

		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(itineraryNo);
			if (shipPosition >= 2  ) {
				vmaItinerary.setShipPosition(shipPosition);
			}

			vmaItinerary = VmaItineraryLocalServiceUtil
					.updateVmaItinerary(vmaItinerary);

			result = VMAUtils.object2Json(vmaItinerary,
					VmaItinerary.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}
	public String PrintingDate(String fromDate, String slashOrDash) {
		String reportDateFrom = StringPool.BLANK;
		String reportDateTo = StringPool.BLANK;
		if (Validator.isNotNull(fromDate)) {
			Date f_date = null;
			
			try {
				if (fromDate.contains("/")) {
					f_date = FormatData.formatDDMMYYYY.parse(fromDate);
					
				} else if (fromDate.contains("-")) {
					f_date = FormatData.formatDateShort.parse(fromDate);
					
				} else {
					f_date = FormatData.formatDDMMYYY2.parse(fromDate);
					
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (f_date != null ) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(f_date);
								
				reportDateFrom = 						
						calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
						+ calendar.get(Calendar.YEAR);
				
				reportDateTo = 						
						calendar.get(Calendar.DATE) + StringPool.DASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.DASH
						+ calendar.get(Calendar.YEAR);
				if (slashOrDash.contains(StringPool.DASH)){
					return reportDateTo;
				} else if (slashOrDash.contains(StringPool.DASH)){
					return reportDateFrom;
				} else {
					return reportDateFrom;
				}
			} else {
				return StringPool.BLANK;
			}
				
		} else {
			return StringPool.BLANK;
		}
	
	}
	public JSONObject phieuBaoThuPhi(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest)
			throws NoSuchVmaItineraryException, SystemException,
			ParseException, IOException, JRException {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		int noticeShipType = ParamUtil.getInteger(request, "noticeShipType");
		String remarks = DanhMucUtils.getString(request, "remarks");
		long documentName = GetterUtil.getLong(request.getParameter("documentName"), -1);
		int documentYear = GetterUtil.getInteger(request.getParameter("documentYear"), -1);

		VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
				.findByitineraryNo(itineraryNo);

		String imoNumber = vmaItinerary.getImoNumber();
		String callSign = vmaItinerary.getCallSign();
		String registryNumber = vmaItinerary.getRegistryNumber();
		String nameOfShip = vmaItinerary.getNameOfShip();
		String flagStateOfShip = vmaItinerary.getFlagStateOfShip();
		String vrCode = vmaItinerary.getVrCode();
		String shipOperatorCode = vmaItinerary.getShipOperatorCode();
		String shipOperatorName = vmaItinerary.getShipOperatorName();
		String shipOwnerCode = vmaItinerary.getShipOwnerCode();
		String shipOwnerName = vmaItinerary.getShipOwnerName();
		String shipTypeCode = vmaItinerary.getShipTypeCode();
		String shipCaptain = vmaItinerary.getShipCaptain();
		String notificationType = "lượt vào";
		//String notificationCode = "lượt vào";
		try {
			// Neu luot vao luot roi khac tau thi phai lay theo vmascheduletransfer
			if(documentName > 0 && documentYear > 0){
				
				VmaItinerary itinerary = VmaItineraryLocalServiceUtil.fetchBydocumentNameOUT_documentYearOUT(documentName, documentYear);
				if(itinerary != null){
					noticeShipType = 1002;					
				}
			}
			

			if (vmaItinerary.getPayment2ItineraryStatus() == 0) {
				if ((noticeShipType == 1002) && (vmaItinerary.getPayment2DepartureStatus() > 0)) {
					notificationType = "lượt rời";
				} else if (vmaItinerary.getPayment2ArrivalStatus() > 0){
					notificationType = "lượt vào";
				}
			} else if (vmaItinerary.getPayment2ItineraryStatus() > 0) {
				notificationType = "02 lượt vào, rời";
			}
			
			
		} catch (Exception e) {
		}
		VmaScheduleTransfer vmaScheduleTransfer = null;
		if (noticeShipType == 1002) {
			List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo, 2);
			if (vmaScheduleTransfers != null && !vmaScheduleTransfers.isEmpty()) {
				vmaScheduleTransfer = vmaScheduleTransfers.get(0);
			}
			if (vmaScheduleTransfer != null) {
				imoNumber = vmaScheduleTransfer.getImoNumber();
				callSign = vmaScheduleTransfer.getCallSign();
				registryNumber = vmaScheduleTransfer.getRegistryNumber();
				nameOfShip = vmaScheduleTransfer.getShipName();
				flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();
				vrCode = vmaScheduleTransfer.getVrCode();
				shipOperatorCode = vmaScheduleTransfer.getShipOperatorCode();
				try {
					shipOperatorName = DmVmaShipOwnerLocalServiceUtil
							.fetchByShipOwnerCode(
									vmaScheduleTransfer.getShipOperatorCode())
							.getCompanyName();
				} catch (Exception e) {
				}
				shipOwnerCode = vmaScheduleTransfer.getShipOwnerCode();
				try {
					shipOwnerName = DmVmaShipOwnerLocalServiceUtil
							.fetchByShipOwnerCode(
									vmaScheduleTransfer.getShipOwnerCode())
							.getCompanyName();
				} catch (Exception e) {
				}
				shipTypeCode = vmaScheduleTransfer.getShipTypeCode();
				shipCaptain = vmaScheduleTransfer.getNameOfMaster();
			}
		}
		// ================
		VmaShip vmaShip = new VmaShip();
		try {
			if (vmaItinerary != null) {
				if (imoNumber.trim().length() >= 7) {
					// findBy IMO, CallSign together
					vmaShip = VmaShipLocalServiceUtil
							.fetchByIMONumber_CallSign(imoNumber, callSign);
				} else {
					// findBy CallSign only
					vmaShip = VmaShipLocalServiceUtil.fetchByCallSign(callSign);
					// Tim lai theo registryNumber
					if (!vmaShip.getShipName().contains(nameOfShip)) {
						vmaShip = VmaShipLocalServiceUtil
								.fetchByRegistryNumber(registryNumber);
					}
				}

			}
		} catch (Exception e) {
		}
		VmaItinerarySchedule vmaItineraryScheduleArrival = null;
		VmaItinerarySchedule vmaItineraryScheduleDeparture = null;
		vmaItineraryScheduleArrival = VmaItineraryScheduleLocalServiceUtil
				.findByItineraryNo_NoticeShipType(
						vmaItinerary.getItineraryNo(), 1);
		vmaItineraryScheduleDeparture = VmaItineraryScheduleLocalServiceUtil
				.findByItineraryNo_NoticeShipType(
						vmaItinerary.getItineraryNo(), 2);

		VmaItineraryRemark vmaItineraryRemarks = null;
		try {
			if (noticeShipType == 0) {
				noticeShipType = 1002; // reset Bao tinh phi luot roi.
			}
			vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo,
							noticeShipType).get(0);
		} catch (Exception e) {
		}

		if (vmaItineraryRemarks == null) {
			vmaItineraryRemarks = new VmaItineraryRemark();
			vmaItineraryRemarks.setRemarks(remarks);
			vmaItineraryRemarks.setNameOfShip(vmaShip.getShipName());
			vmaItineraryRemarks.setNoticeShipType(noticeShipType);
			vmaItineraryRemarks.setItineraryNo(itineraryNo);

			vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
					.addVmaItineraryRemarks(vmaItineraryRemarks);
		} else if (vmaItineraryRemarks != null && remarks.length() > 0) {
			vmaItineraryRemarks.setRemarks(remarks);

			vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
					.updateVmaItineraryRemarks(vmaItineraryRemarks);
		}

		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("itineraryNo", itineraryNo);
		result.put("NotificationCode", noticeShipType);
		result.put("NotificationType", notificationType);
		
		if (vmaScheduleTransfer == null) {
			result.put("nameOfMaster", shipCaptain);
			result.put("nameOfShip", nameOfShip);
			result.put("stateName",
					flagStateOfShip != null ? DmStateLocalServiceUtil
							.getByStateCode(flagStateOfShip).getStateName()
							: "");
			result.put("imo", imoNumber);
			result.put("registryNumber", registryNumber);
			result.put("callSign", callSign);
			result.put("stateCode", flagStateOfShip);
			result.put("shipOperatorCode", shipOperatorCode);
			result.put("shipOperatorName", shipOperatorName);
			result.put("shipOwnerCode", shipOwnerCode);
			result.put("shipOwnerName", shipOwnerName);
			result.put("vrCode", vrCode);
			result.put("shipTypeCode", shipTypeCode);
		} else {
			result.put(
					"nameOfMaster",
					(shipCaptain + " (Cũ: " + vmaItinerary.getShipCaptain() + ")"));
			result.put(
					"nameOfShip",
					(nameOfShip + " (Cũ: " + vmaItinerary.getNameOfShip() + ")"));
			result.put(
					"stateName",
					(flagStateOfShip != null && vmaItinerary
							.getFlagStateOfShip() != null) ? (DmStateLocalServiceUtil
							.getByStateCode(flagStateOfShip).getStateName()
							+ " (Cũ: "
							+ DmStateLocalServiceUtil.getByStateCode(
									vmaItinerary.getFlagStateOfShip())
									.getStateName() + ")")
							: "");
			result.put("imo",
					(imoNumber + " (Cũ: " + vmaItinerary.getImoNumber() + ")"));
			result.put("registryNumber", (registryNumber + " (Cũ: "
					+ vmaItinerary.getRegistryNumber() + ")"));
			result.put("callSign",
					callSign + " (Cũ: " + vmaItinerary.getCallSign() + ")");
			result.put(
					"stateCode",
					flagStateOfShip + " (Cũ: "
							+ vmaItinerary.getFlagStateOfShip() + ")");
			result.put("shipOperatorCode", shipOperatorCode + " (Cũ: "
					+ vmaItinerary.getShipOperatorCode() + ")");
			try {
				result.put(
						"shipOperatorName",
						shipOperatorName
								+ " (Cũ: "
								+ DmVmaShipOwnerLocalServiceUtil
										.fetchByShipOwnerCode(
												vmaItinerary
														.getShipOperatorCode())
										.getCompanyName() + ")");
			} catch (Exception e) {
			}
			result.put("shipOwnerCode",
					shipOwnerCode + " (Cũ: " + vmaItinerary.getShipOwnerCode()
							+ ")");
			try {
				result.put(
						"shipOwnerName",
						shipOwnerName
								+ " (Cũ: "
								+ DmVmaShipOwnerLocalServiceUtil
										.fetchByShipOwnerCode(
												vmaItinerary.getShipOwnerCode())
										.getCompanyName() + ")");
			} catch (Exception e) {
			}
			result.put("vrCode", vrCode + " (Cũ: " + vmaItinerary.getVrCode()
					+ ")");
			result.put("shipTypeCode",
					shipTypeCode + " (Cũ: " + vmaItinerary.getShipTypeCode()
							+ ")");
		}
		result.put("Seat", vmaShip.getSeat());
		result.put("Lies", vmaShip.getLies());
		result.put("Passengers", vmaShip.getPassengers());
		result.put("Craneload", vmaShip.getCraneload());
		
		double gt = vmaShip.getGt().doubleValue();
		double dwt = vmaShip.getDwt().doubleValue();
		if (gt > 0) {
			result.put("gt", gt);
		} else {
			result.put("Verify_GT", "Thiếu GT");
		}
		if (dwt > 0) {
			result.put("dwt", dwt);
		} else {
			result.put("Verify_DWT", "Thiếu DWT");
		}
		String arrivalShipAgencyCode = vmaItinerary.getArrivalShipAgencyCode();
		String departureShipAgencyCode = vmaItinerary
				.getDepartureShipAgencyCode();
		String departureShipAgency = vmaItinerary.getDepartureShipAgency();
		if ((shipOwnerCode == null) || (shipOwnerCode.equals(StringPool.BLANK))) {
			result.put("Verify_SHIP_OWNER", "Thiếu chủ tàu");
		}
		if ((shipOperatorCode == null) || (shipOperatorCode.equals(StringPool.BLANK))) {
			result.put("Verify_SHIP_OPERATOR", "Thiếu người khai thác");
		}
		if ((shipOwnerCode == null) || (shipOwnerCode.equals(StringPool.BLANK))
				&& (arrivalShipAgencyCode == null)
				|| (arrivalShipAgencyCode.equals(StringPool.BLANK))) {
			result.put("Verify_ARIVAL_AGENCY", "Thiếu chủ tàu/đại lý đến");
			// DanhMucUtils.encodeUTF8("Thiếu chủ tàu/đại lý đến"));
		}
		if ((shipOwnerCode == null) || (shipOwnerCode.equals(StringPool.BLANK))
				&& (departureShipAgencyCode == null)
				|| (departureShipAgencyCode.equals(StringPool.BLANK))) {
			result.put("Verify_DEPARTURE-AGENCY", "Thiếu chủ tàu/đại lý rời");
			// DanhMucUtils.encodeUTF8("Thiếu chủ tàu/đại lý rời"));
		}
		
		if (!departureShipAgencyCode.equals(StringPool.BLANK)
				&& !arrivalShipAgencyCode.equals(StringPool.BLANK)
				&& !arrivalShipAgencyCode.equals(departureShipAgencyCode)) {
			result.put("Verify_AGENCY", "Tách biên lai Đại lý đến, Đại lý rời");
			// DanhMucUtils.encodeUTF8("Tách biên lai Đại lý đến, Đại lý rời"));
		}
		result.put("shipAgencyName", vmaItinerary.getArrivalShipAgency());
		result.put("arrivalShipAgencyCode",
				vmaItinerary.getArrivalShipAgencyCode());
		result.put("arrivalShipAgencyName", vmaItinerary.getArrivalShipAgency());
		result.put("departureShipAgencyCode",
				vmaItinerary.getDepartureShipAgencyCode());
		if ((!arrivalShipAgencyCode.equals(departureShipAgencyCode)) 
				&& Validator.isNull(vmaScheduleTransfer)){
			result.put("departureShipAgencyName", departureShipAgency);
		}
		if ((!arrivalShipAgencyCode.equals(departureShipAgencyCode))
		&& Validator.isNotNull(vmaScheduleTransfer) && (noticeShipType == 1002)) {
			result.put("arrivalShipAgencyName", departureShipAgency); // Hien thi dai ly roi.
		}
		if (vmaItineraryScheduleArrival != null) {
			result.put("arrivalShipAgencyPhone", vmaItineraryScheduleArrival.getShipAgencyPhone() + "; Email: " + vmaItineraryScheduleArrival.getShipAgencyContactEmail());
		}
		if (vmaItineraryScheduleDeparture != null) {
			result.put("departureShipAgencyPhone", vmaItineraryScheduleDeparture.getShipAgencyPhone() + "; Email: " + vmaItineraryScheduleDeparture.getShipAgencyContactEmail());
		}
		// CVHH Hai Phong
		if (vmaItinerary.getMaritimeCode().equalsIgnoreCase("19") 
				&& Validator.isNotNull(vmaItinerary.getDepartureShipAgencyCode())) {
			result.put("departureShipAgencyName", departureShipAgency);
			if ((!arrivalShipAgencyCode.equals(departureShipAgencyCode))
					&& Validator.isNotNull(vmaScheduleTransfer) && (noticeShipType == 1002)) {
						result.put("arrivalShipAgencyName",  vmaItinerary.getArrivalShipAgency()); // Van phan biet dai ly vao.
					}
		}
		String arrivalPortCode = vmaItinerary.getArrivalPortCode();
		if (arrivalPortCode.equals(StringPool.BLANK)) {
			result.put("Verify_ARRIVALPORTCODE", "Thiếu cảng đến");
			// DanhMucUtils.encodeUTF8("Thiếu cảng đến"));
		} else {
			result.put("arrivalPortCode", arrivalPortCode);
		}
		try {
			result.put(
					"arrivalPortName",
					DmPortLocalServiceUtil.getByPortCode(
							vmaItinerary.getArrivalPortCode()).getPortName());
		} catch (Exception e) {
			// nothing to do
		}
		String lastPortCode = vmaItinerary.getLastPortCode();
		String lastPortName = StringPool.BLANK;
		if (lastPortCode.equals(StringPool.BLANK)) {
			if (vmaItineraryScheduleArrival != null
					&& vmaItineraryScheduleArrival.getLastProvinceCode() != null
					&& !vmaItineraryScheduleArrival.getLastProvinceCode()
							.isEmpty()) {
				result.put("lastPortCode",
						vmaItineraryScheduleArrival.getLastProvinceCode());
				lastPortName = vmaItineraryScheduleArrival
						.getLastProvinceCode();
			} else {
				result.put("Verify_LASTPORTCODE", "Thiếu cảng rời cuối cùng");
				// DanhMucUtils.encodeUTF8("Thiếu cảng rời"));
			}
		} else {
			if (lastPortCode.contains("ZZZ")) {
				if (vmaItineraryScheduleArrival != null
						&& vmaItineraryScheduleArrival.getLastProvinceCode() != null
						&& !vmaItineraryScheduleArrival.getLastProvinceCode()
								.isEmpty()) {
					result.put("lastPortCode",
							vmaItineraryScheduleArrival.getLastProvinceCode());
					lastPortName = vmaItineraryScheduleArrival
							.getLastProvinceCode();
				}
			} else {
				result.put("lastPortCode", lastPortCode);
			}
		}
		try {
			if (Validator.isNotNull(lastPortName)) {
				result.put("lastPortName",lastPortName);
			} else {
				result.put("lastPortName",
						DmPortLocalServiceUtil.getByPortCode(lastPortCode)
								.getPortName());
			}
			
			String lastPortType = DmPortLocalServiceUtil.getByPortCode(
					lastPortCode).getPortType();
			result.put("lastPortType", lastPortType);
			if (lastPortType.equals("0")) {
				result.put("Verify_ARRIVALPORTTYPE0", "Cảng rời: Cảng biển");
				// DanhMucUtils.encodeUTF8("Cảng rời: Cảng biển"));
			} else if (lastPortType.equals("2")) {
				result.put("Verify_ARRIVALPORTTYPE2",
						"Cảng rời: Cảng từ bờ ra đảo");
				// DanhMucUtils.encodeUTF8("Cảng rời: Cảng từ bờ ra đảo"));
			} else if (lastPortType.equals("1")) {
				result.put("Verify_ARRIVALPORTTYPE1",
						"Cảng rời: Cảng thủy nội địa");
				// DanhMucUtils.encodeUTF8("Cảng rời: Cảng thủy nội địa"));
			}
			result.put("lastPortTypeName", DanhMucUtils
					.encodeUTF8(getPortTypeName(DmPortLocalServiceUtil
							.getByPortCode(lastPortCode).getPortType())));
		} catch (Exception e) {
			// nothing to do
		}
		String nextPortCode = vmaItinerary.getNextPortCode();
		String nextPortName = StringPool.BLANK;
		if (nextPortCode.equals(StringPool.BLANK)) {
			if (vmaItineraryScheduleDeparture != null
					&& vmaItineraryScheduleDeparture.getNextProvinceCode() != null
					&& !vmaItineraryScheduleDeparture.getNextProvinceCode()
							.isEmpty()) {
				result.put("nextPortCode",
						vmaItineraryScheduleDeparture.getNextProvinceCode());
				nextPortName = vmaItineraryScheduleDeparture
						.getNextProvinceCode();
			} else {
				result.put("Verify_NEXTPORTCODE", "Thiếu cảng đến kế tiếp");
				// DanhMucUtils.encodeUTF8("Thiếu cảng đến kế tiếp"));
			}
		} else {
			if (nextPortCode.contains("ZZZ")) {
				if (vmaItineraryScheduleDeparture != null
						&& vmaItineraryScheduleDeparture.getNextProvinceCode() != null
						&& !vmaItineraryScheduleDeparture.getNextProvinceCode()
								.isEmpty()) {
					result.put("nextPortCode",
							vmaItineraryScheduleDeparture.getNextProvinceCode());
					nextPortName = vmaItineraryScheduleDeparture
							.getNextProvinceCode();
				}
			} else {
				result.put("nextPortCode", nextPortCode);
			}
		}
		try {
			if (Validator.isNotNull(nextPortName)) {
				result.put("nextPortName",nextPortName);
			} else {
				result.put("nextPortName",
						DmPortLocalServiceUtil.getByPortCode(nextPortCode)
								.getPortName());
			}
			
			String nextPortType = DmPortLocalServiceUtil.getByPortCode(
					nextPortCode).getPortType();
			result.put("nextPortType", nextPortType);
			if (nextPortType.equals("0")) {
				result.put("Verify_ARRIVALPORTTYPE0",
						"Cảng đến tiếp theo: Cảng biển");
				// DanhMucUtils.encodeUTF8("Cảng đến tiếp theo: Cảng biển"));
			} else if (nextPortType.equals("2")) {
				result.put("Verify_ARRIVALPORTTYPE2",
						"Cảng đến tiếp theo: Cảng từ bờ ra đảo");
				// DanhMucUtils.encodeUTF8("Cảng đến tiếp theo: Cảng từ bờ ra đảo"));
			} else if (nextPortType.equals("1")) {
				result.put("Verify_ARRIVALPORTTYPE1",
						"Cảng đến tiếp theo: Cảng thủy nội địa");
				// DanhMucUtils.encodeUTF8("Cảng đến tiếp theo: Cảng thủy nội địa"));
			}
			result.put("nextPortTypeName", DanhMucUtils
					.encodeUTF8(getPortTypeName(DmPortLocalServiceUtil
							.getByPortCode(nextPortCode).getPortType())));
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put("arrivalDate", FormatData.formatDateShort3
					.format(vmaItinerary.getTimeOfArrival()));
		} catch (Exception e) {
			result.put("Verify_TIMEOFARRIVAL", "Thiếu ngày đến");
			// DanhMucUtils.encodeUTF8("Thiếu ngày đến"));
		}
		try {
			result.put("departureDate", FormatData.formatDateShort3
					.format(vmaItinerary.getTimeOfDeparture()));
		} catch (Exception e) {
			result.put("Verify_TIMEOFDEPARTURE", "Thiếu ngày rời");
			// DanhMucUtils.encodeUTF8("Thiếu ngày rời"));
		}

		try {
			result.put(
					"cargoDescription",
					DmDataItemLocalServiceUtil.findByDataGroupId_Node2(124L,
							vmaItinerary.getCargoDescription()).getName());
		} catch (Exception e) {
		}
		result.put("power", vmaShip.getPower());
		result.put("shipBoat", vmaShip.getShipBoat());
		String shipTypeName = StringPool.BLANK;
		try {
			if (vmaShip.getShipBoat().equalsIgnoreCase("BOAT")) {
				if (vmaScheduleTransfer == null) {
					shipTypeName = DmVmaShipTypeLocalServiceUtil
							.fetchByShipTypeCode(shipTypeCode)
							.getShipTypeName();
				} else {
					shipTypeName = DmVmaShipTypeLocalServiceUtil
							.fetchByShipTypeCode(shipTypeCode)
							.getShipTypeName()
							+ " (Cũ: "
							+ DmVmaShipTypeLocalServiceUtil
									.fetchByShipTypeCode(
											vmaItinerary.getShipTypeCode())
									.getShipTypeName() + ")";
				}
			} else {
				if (vmaScheduleTransfer == null) {
					shipTypeName = DmShipTypeLocalServiceUtil
							.getByShipTypeCode(shipTypeCode).getShipTypeName();
				} else {
					shipTypeName = DmShipTypeLocalServiceUtil
							.getByShipTypeCode(shipTypeCode).getShipTypeName()
							+ " (Cũ: "
							+ DmShipTypeLocalServiceUtil.getByShipTypeCode(
									vmaItinerary.getShipTypeCode())
									.getShipTypeName() + ")";
				}
			}
		} catch (Exception e) {
		}
		if (shipTypeName.equals(StringPool.BLANK)) {
			result.put("Verify_SHIPTYPE", "Thiếu Loại tàu");
			// DanhMucUtils.encodeUTF8("Thiếu Loại tàu"));
		} else {
			result.put("shipTypeName", shipTypeName);
		}
		
		
		TempDebitnote tempDebitNote = null;
		try {
			if ((!itineraryNo.equals(StringPool.BLANK)) && documentName > 0
					&& documentYear > 0) {
				tempDebitNote = TempDebitNoteLocalServiceUtil
						.fetchByItineraryNo_DocumentName_DocumentYear(itineraryNo,
								documentName, documentYear);
				log.info("1.TempDebitNoteLocalServiceUtil.fetchByItineraryNo_DocumentName_DocumentYear" + "----"+ itineraryNo + "----"+documentName + "----"+documentYear + "----"+ "Validator.isNotNull(tempDebitNote)   " + Validator.isNotNull(tempDebitNote));
			} else if (itineraryNo.equals(StringPool.BLANK) && documentName > 0
					&& documentYear > 0) {
				tempDebitNote = TempDebitNoteLocalServiceUtil
						.findByDocumentNameAnddocumentYear(
								Long.valueOf(documentName),
								Integer.valueOf(documentYear));
				log.info("2.TempDebitNoteLocalServiceUtil.findByDocumentNameAnddocumentYear" + "----"+ itineraryNo + "----"+documentName + "----"+documentYear+ "----"+ "Validator.isNotNull(tempDebitNote)   " + Validator.isNotNull(tempDebitNote));
			} else {
				if (noticeShipType == 1002	) {
					tempDebitNote = TempDebitNoteLocalServiceUtil
							.fetchByItineraryNo_DocumentName_DocumentYear(itineraryNo,
									vmaItineraryScheduleDeparture.getDocumentName(), vmaItineraryScheduleDeparture.getDocumentYear());
					log.info("3.TempDebitNoteLocalServiceUtil.fetchByItineraryNo_DocumentName_DocumentYear" + "----"+ itineraryNo + "----"+vmaItineraryScheduleDeparture.getDocumentName() + "----"+vmaItineraryScheduleDeparture.getDocumentYear()+ "----"+ "Validator.isNotNull(tempDebitNote)   " + Validator.isNotNull(tempDebitNote));
				} else if (noticeShipType == 1001) {
					tempDebitNote = TempDebitNoteLocalServiceUtil
							.fetchByItineraryNo_DocumentName_DocumentYear(itineraryNo,
									vmaItineraryScheduleArrival.getDocumentName(), vmaItineraryScheduleArrival.getDocumentYear());
					log.info("4.TempDebitNoteLocalServiceUtil.fetchByItineraryNo_DocumentName_DocumentYear" + "----"+ itineraryNo + "----"+vmaItineraryScheduleArrival.getDocumentName() + "----"+vmaItineraryScheduleArrival.getDocumentYear()+ "----"+ "Validator.isNotNull(tempDebitNote)   " + Validator.isNotNull(tempDebitNote));
				} else {
					tempDebitNote = TempDebitNoteLocalServiceUtil
							.fetchByItineraryNo_DocumentName_DocumentYear(itineraryNo,
									documentName, documentYear);
					log.info("5.TempDebitNoteLocalServiceUtil.fetchByItineraryNo_DocumentName_DocumentYear" + "----"+ itineraryNo + "----"+documentName + "----"+documentYear+ "----"+ "Validator.isNotNull(tempDebitNote)   " + Validator.isNotNull(tempDebitNote));
				}
				if (Validator.isNull(tempDebitNote)) {
					//do nothing
				}
				
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(Validator.isNotNull(tempDebitNote) ? tempDebitNote.getReportdate() : new Date());
			String reportDateFrom = "Lập ngày "+ calendar.get(Calendar.DATE) + " tháng "+ (calendar.get(Calendar.MONTH) + 1) + " năm "
			+ calendar.get(Calendar.YEAR);
			if (Validator.isNotNull(tempDebitNote) && tempDebitNote.getReportby().contains("@")){
				if (tempDebitNote.getReportby().contains("kyso") || tempDebitNote.getReportby().contains("vanthu")) {
					result.put("reportBy", StringPool.BLANK);
					result.put("reportByFullName", StringPool.BLANK);
					result.put("reportDate", reportDateFrom);
					result.put("Verify_ReportBy", "Thiếu xác nhận của Bộ phận báo phí");
				} else {
					result.put("reportBy", tempDebitNote.getReportby());
					result.put("reportDate", reportDateFrom);
					result.put("reportByFullName", StringPool.BLANK);
					
					long urs = 0;
					urs = UserLocalServiceUtil.getUserIdByEmailAddress(10154, tempDebitNote.getReportby());
					log.info("=============userID ==== " + urs);
					if ((urs != 0) && vmaItinerary.getMaritimeCode().equalsIgnoreCase("7") ){
						User defaultUsr = UserLocalServiceUtil.getUserById(urs);
						result.put("reportByFullName", Validator.isNotNull(defaultUsr) ? defaultUsr.getFullName() : StringPool.BLANK);
					}
				}
			} else {
				result.put("reportBy", StringPool.BLANK);
				result.put("reportByFullName", StringPool.BLANK);
				result.put("reportDate", reportDateFrom);
			}
			
			if (Validator.isNotNull(tempDebitNote) && tempDebitNote.getFinancialaccountant().length() > 0){
				result.put("financialAccountant", tempDebitNote.getFinancialaccountant());
				result.put("financialAccountantFullName", StringPool.BLANK);
				long urs = 0;
				urs = UserLocalServiceUtil.getUserIdByEmailAddress(10154, tempDebitNote.getFinancialaccountant());
				log.info("=============userID ==== " + urs);
				if ((urs != 0) && vmaItinerary.getMaritimeCode().equalsIgnoreCase("7") ){
					User defaultUsr = UserLocalServiceUtil.getUserById(urs);
					result.put("financialAccountantFullName", Validator.isNotNull(defaultUsr) ? defaultUsr.getFullName() : StringPool.BLANK);
				}
			} else {
				result.put("financialAccountant", StringPool.BLANK);
			}
		} catch (Exception e) {
		
		}
		
		
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(vmaItinerary.getTimeOfDeparture());
			int toMonth = c.get(Calendar.MONTH) + 1;
			int toYear = c.get(Calendar.YEAR);
			String portRegionCode = StringPool.BLANK;
			if (vmaItineraryScheduleDeparture != null) {
				portRegionCode = vmaItineraryScheduleDeparture.getPortRegionCode();
			}
			long soChuyen = 0;
			soChuyen = VMAUtils.thuTuSoChuyen(portRegionCode, imoNumber, callSign,
					registryNumber, FormatData
							.parseDateToTringType3(vmaItinerary
									.getTimeOfDeparture()), toMonth, toYear);
			result.put("soChuyen", soChuyen);

		} catch (Exception e) {
			result.put("soChuyen", 0);
		}
		try {
			try {
				result.put("purposeCode", VmaItineraryScheduleLocalServiceUtil
						.findByItineraryNo_NoticeShipType(itineraryNo, 1)
						.getPurposeCode());
				result.put(
						"purposeName",
						DmArrivalPurposeLocalServiceUtil.getByPortCode(
								VmaItineraryScheduleLocalServiceUtil
										.findByItineraryNo_NoticeShipType(
												itineraryNo, 1)
										.getPurposeCode()).getPurposeNameVN());
				if (Validator.isNull(VmaItineraryScheduleLocalServiceUtil
						.findByItineraryNo_NoticeShipType(itineraryNo, 1)
						.getPurposeCode())) {
					result.put("Verify_PURPOSECODE_ARRIVAL", "Thiếu mục đích đến cảng");
				}

			} catch (Exception e) {
				result.put("purposeCode", VmaItineraryScheduleLocalServiceUtil
						.findByItineraryNo_NoticeShipType(itineraryNo, 2)
						.getPurposeCode());
				result.put(
						"purposeName",
						DmArrivalPurposeLocalServiceUtil.getByPortCode(
								VmaItineraryScheduleLocalServiceUtil
										.findByItineraryNo_NoticeShipType(
												itineraryNo, 2)
										.getPurposeCode()).getPurposeNameVN());
				result.put("Verify_PURPOSECODE_ARRIVAL", "Thiếu mục đích đến cảng");
			}
			try {
				if (Validator.isNotNull(VmaItineraryScheduleLocalServiceUtil
										.findByItineraryNo_NoticeShipType(
												itineraryNo, 2)
										.getPurposeCode())) {
					result.put("purposeCodeDeparture", VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo, 2)
							.getPurposeCode());
					result.put(
							"purposeNameDeparture",
							DmArrivalPurposeLocalServiceUtil.getByPortCode(
									VmaItineraryScheduleLocalServiceUtil
											.findByItineraryNo_NoticeShipType(
													itineraryNo, 2)
											.getPurposeCode()).getPurposeNameVN());
					if (Validator.isNull(VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo, 1)
							.getPurposeCode())) {
						result.put("Verify_PURPOSECODE_DEPARTURE", "Thiếu mục đích rời cảng");
					}
				}
				

			} catch (Exception e) {
				// do nothing
				result.put("Verify_PURPOSECODE_DEPARTURE", "Thiếu mục đích rời cảng");
			}
		} catch (Exception e) {
			result.put("Verify_PURPOSECODE", "Thiếu mục đích chính");
			// DanhMucUtils.encodeUTF8("Thiếu mục đích chính"));
		}
		result.put("domesticTransportCertificate",
				vmaItinerary.getDomesticTransportCertificate());
		result.put("domesticTransportCertificateName",
				getDomesticTransportCertificateName(vmaItinerary
						.getDomesticTransportCertificate()));
		try {
			List<VmaScheduleCargolist> cargolists = VmaScheduleCargolistLocalServiceUtil
					.findByItineraryNo(itineraryNo);
			StringBuilder cargoList = new StringBuilder();
			if (cargolists != null) {
				for (VmaScheduleCargolist vmaScheduleCargolist : cargolists) {
					try {
						DmDataitem dataItem = DmDataItemLocalServiceUtil
								.findByDataGroupIdAndCode0(119,
										vmaScheduleCargolist.getCargoCategory());
						cargoList.append(", " + dataItem.getName());
						if (!dataItem.getName().equals(StringPool.BLANK)) {
							cargoList.append(", "
									+ vmaScheduleCargolist.getQuantity() + ";");
						}
					} catch (Exception e) {
						// nothing to do
					}
				}
				if (!cargoList.toString().equals(StringPool.BLANK)) {
					cargoList.replace(0, 1, StringPool.BLANK);
					if (cargoList != null
							&& cargoList.toString().length() > 0
							&& cargoList.toString().charAt(
									cargoList.length() - 1) == ';') {
						cargoList.deleteCharAt(cargoList.length() - 1);
					}
					result.put("cargoList", cargoList.toString().trim());
				}
			}
		} catch (Exception e) {
			/*
			 * result.put( "Verify_CARGOLIST", DanhMucUtils .encodeUTF8(
			 * "Thiếu thông tin hàng hóa (hoặc tàu thuyền không chở hàng)"));
			 */
		}
		try {
			result.put(
					"documentTypeNameIN",
					DmDocTypeLocalServiceUtil.findByDocumentType(
							TempDocumentLocalServiceUtil
									.getByDocumentNameAndDocumentYear(
											vmaItinerary.getDocumentNameIN(),
											vmaItinerary.getDocumentYearIN())
									.getDocumentTypeCode())
							.getDocumentTypeName());
		} catch (Exception e) {
			result.put("Verify_DOCUMENTIN",
					"Không làm thủ tục điện tử lượt vào");
			// DanhMucUtils.encodeUTF8("Không làm thủ tục điện tử lượt vào"));
		}
		try {
			result.put(
					"documentTypeNameOUT",
					DmDocTypeLocalServiceUtil.findByDocumentType(
							TempDocumentLocalServiceUtil
									.getByDocumentNameAndDocumentYear(
											vmaItinerary.getDocumentNameOUT(),
											vmaItinerary.getDocumentYearOUT())
									.getDocumentTypeCode())
							.getDocumentTypeName());
		} catch (Exception e) {
			result.put("Verify_DOCUMENTOUT",
					"Không làm thủ tục điện tử lượt rời");
			// DanhMucUtils.encodeUTF8("Không làm thủ tục điện tử lượt rời"));
		}
		try {
			List<VmaItineraryProtest> vmaItineraryProtests = VmaItineraryProtestLocalServiceUtil
					.findByItineraryNo(itineraryNo);
			JSONArray arrVmaItineraryProtest = JSONFactoryUtil
					.createJSONArray();
			if (vmaItineraryProtests != null && !vmaItineraryProtests.isEmpty()) {

				for (VmaItineraryProtest vmaItineraryProtest : vmaItineraryProtests) {
					try {
						if (vmaItineraryProtest.getNoticeShipType() == 1) {
							result.put("isVmaItineraryProtest", 1);
						} else if (vmaItineraryProtest.getNoticeShipType() == 2) {
							result.put("isVmaItineraryProtestOut", 1);
						}
						JSONObject object = VMAUtils.object2Json(
								vmaItineraryProtest,
								VmaItineraryProtest.class);
						arrVmaItineraryProtest.put(object);
					} catch (Exception e) {
						// nothing to do
					}
				}
				result.put("vmaItineraryProtests", arrVmaItineraryProtest);
				int countProtest = VmaItineraryProtestLocalServiceUtil
						.countByItineraryNo(itineraryNo);
				result.put("totalVmaItineraryProtests", countProtest + " lượt.");
				result.put("Verify_PROTEST", "Có xác nhận kháng nghị hàng hải "
						+ countProtest + " lượt.");
				// DanhMucUtils.encodeUTF8("Có xác nhận kháng nghị hàng hải"));
			}
		} catch (Exception e) {
			result.put("totalVmaItineraryProtests", 0);
		}
		double anchorageDomesticDuration = 0, anchorageForeignDuration = 0, anchorageFreeDuration = 0;
		try {
			List<VmaScheduleAnchorage> vmaScheduleAnchorages = VmaScheduleAnchorageLocalServiceUtil
					.findByItineraryNo(itineraryNo);
			if (Validator.isNotNull(vmaScheduleAnchorages)
					&& vmaScheduleAnchorages.size() > 0) {
				JSONArray array = JSONFactoryUtil.createJSONArray();
				JSONObject arrayDeparture = JSONFactoryUtil.createJSONObject();
				
				JSONArray arrayAnchorageDeparture = JSONFactoryUtil.createJSONArray();
				JSONArray arrayAnchorageArrival = JSONFactoryUtil.createJSONArray();
				
				JSONArray arrayCargoListDeparture = JSONFactoryUtil.createJSONArray();
				JSONArray arrayCargoListArrival = JSONFactoryUtil.createJSONArray();
				
				Boolean flagInsertDepartureAnchorage = false;
				for (VmaScheduleAnchorage vmaScheduleAnchorage : vmaScheduleAnchorages) {
					JSONObject object = JSONFactoryUtil.createJSONObject();
					
					anchorageDomesticDuration += vmaScheduleAnchorage
							.getAnchorageDomesticDuration();
					anchorageForeignDuration += vmaScheduleAnchorage
							.getAnchorageForeignDuration();
					anchorageFreeDuration += vmaScheduleAnchorage
							.getAnchorageFreeDuration();

					try {
						object = VMAUtils.object2Json(vmaScheduleAnchorage,
								VmaScheduleAnchorage.class,
								"anchoringPortHarbourCode",
								"anchoringPortWharfCode",
								"anchoringPortRegionCode");
						
						if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode())) {
							
							String lydo = StringPool.BLANK;
							
							if (Validator.isNotNull(vmaScheduleAnchorage.getAnchorageFreeDurationRemarks()) && vmaScheduleAnchorage.getAnchorageFreeDurationRemarks().length() > 0) {
								lydo = "<br>(Ghi chú) " + vmaScheduleAnchorage.getAnchorageFreeDurationRemarks();
							}
							object.put("MainPurpose", DmArrivalPurposeLocalServiceUtil.getByPortCode(vmaScheduleAnchorage.getPurposeCode()).getPurposeNameVN() + lydo);
							
							
						} else if (Validator.isNotNull(vmaScheduleAnchorage.getAnchorageFreeDurationRemarks()) && vmaScheduleAnchorage.getAnchorageFreeDurationRemarks().length() > 0) {
							object.put("MainPurpose", "<br>(Ghi chú) " + vmaScheduleAnchorage.getAnchorageFreeDurationRemarks());
						}
					} catch (Exception e) {
						// nothing to do
					}

					try {
						// Canh bao: Tinh phi hay khong
						if (vmaScheduleAnchorage.getMakePayment() == 1) {
							object.put("Verify_MAKEPAYMENT", "Đã ghi phiếu thu");
						} else {
							object.put("Verify_MAKEPAYMENT",
									"Chưa ghi phiếu thu");
						}

						String portWharfCode = vmaScheduleAnchorage
								.getAnchoringPortWharfCode();
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
								.getByPortWharfCode(portWharfCode);
						if (dmPortWharf != null
								&& dmPortWharf.getPortWharfPayment() == 1) {
							object.put("Verify_PAYMENT", "Cảng vụ tính phí");
							
							if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode()) && 
									(vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C17") 
											|| vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C18")
											|| vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C19")
											|| vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C20")
											|| vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C30")
											|| vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C31") )) {
								// Các trường hợp quy định Không phải nộp phí neo đậu cho tàu thuyền
								object.put("Verify_PAYMENT", "Không tính phí");
							}	
						} else {
							object.put("Verify_PAYMENT", "Không tính phí");
						}
					} catch (Exception e) {
						// nothing to do
						object.put("Verify_PAYMENT", "Không tính phí");
					}
					
					// Xuat thong tin hang hoa

					List<VmaScheduleCargolist> vmaScheduleCargolists = VmaScheduleCargolistLocalServiceUtil
							.findByitineraryScheduleId(vmaScheduleAnchorage
									.getItineraryScheduleId());
					
					
					VmaScheduleCargolist vmaScheduleCargoShortlist = null;
					if (vmaScheduleCargolists != null
							&& !vmaScheduleCargolists.isEmpty()) {
						vmaScheduleCargoShortlist = vmaScheduleCargolists.get(0);
					}
					
					if (vmaScheduleCargoShortlist != null) {
						try {
							object.put(
									"cargoCategoryName",
									DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													119L,
													vmaScheduleCargoShortlist
															.getCargoCategory())
											.getName());
						} catch (Exception e) {
							// nothing to do
						}
						try {
							object.put(
									"cargoCodeName",
									DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargoShortlist
															.getCargoCode())
											.getName());
						} catch (Exception e) {
							// nothing to do
						}
						if (vmaScheduleCargoShortlist.getUnit().equalsIgnoreCase(
								"TEU")) {
							object.put("quantity",
									vmaScheduleCargoShortlist.getQuantity() + " "
											+ DanhMucUtils.encodeUTF8("Teu"));
						} else {
							object.put("quantity",
									vmaScheduleCargoShortlist.getQuantity());
						}						
					}
					
					if (Validator.isNotNull(vmaScheduleCargolists)) {
						for (VmaScheduleCargolist vmaScheduleCargolist: vmaScheduleCargolists) {
							JSONObject objectCargo = JSONFactoryUtil.createJSONObject();

							try {
								objectCargo = VMAUtils.object2Json(vmaScheduleAnchorage,
										VmaScheduleAnchorage.class,
										"anchoringPortHarbourCode",
										"anchoringPortWharfCode",
										"anchoringPortRegionCode");
								
								if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode())) {
									objectCargo.put("MainPurpose", DmArrivalPurposeLocalServiceUtil.getByPortCode(vmaScheduleAnchorage.getPurposeCode()).getPurposeNameVN());
								}
							} catch (Exception e) {
								// nothing to do
							}

							try {
								// Canh bao: Tinh phi hay khong
								if (vmaScheduleAnchorage.getMakePayment() == 1) {
									objectCargo.put("Verify_MAKEPAYMENT", "Đã ghi phiếu thu");
								} else {
									objectCargo.put("Verify_MAKEPAYMENT",
											"Chưa ghi phiếu thu");
								}

								String portWharfCode = vmaScheduleAnchorage
										.getAnchoringPortWharfCode();
								DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
										.getByPortWharfCode(portWharfCode);
								if (dmPortWharf != null
										&& dmPortWharf.getPortWharfPayment() == 1) {
									objectCargo.put("Verify_PAYMENT", "Cảng vụ tính phí");
								} else {
									objectCargo.put("Verify_PAYMENT", "Không tính phí");
								}
							} catch (Exception e) {
								// nothing to do
								objectCargo.put("Verify_PAYMENT", "Không tính phí");
							}
							
							try {
								objectCargo.put(
										"cargoCategoryName",
										DmDataItemLocalServiceUtil
												.findByDataGroupIdAndCode0(
														119L,
														vmaScheduleCargolist
																.getCargoCategory())
												.getName());
							} catch (Exception e) {
								// nothing to do
							}
							try {
								objectCargo.put(
										"cargoCodeName",
										DmDataItemLocalServiceUtil
												.findByDataGroupIdAndCode0(
														124L,
														vmaScheduleCargolist
																.getCargoCode())
												.getName());
							} catch (Exception e) {
								// nothing to do
							}
							if (vmaScheduleCargolist.getUnit().equalsIgnoreCase(
									"TEU")) {
								objectCargo.put("quantity",
										vmaScheduleCargolist.getQuantity() + " "
												+ DanhMucUtils.encodeUTF8("Teu"));
							} else {
								objectCargo.put("quantity",
										vmaScheduleCargolist.getQuantity());
							}

							if (vmaScheduleCargolist.getNoticeShipType() == 2) {
								arrayCargoListDeparture.put(objectCargo);
							} else if (vmaScheduleCargolist.getNoticeShipType() == 1) {
								arrayCargoListArrival.put(objectCargo);
							}
												
						
						}
					}
					
				
					

					if (vmaScheduleAnchorage.getNoticeShipType() == 2) {
						arrayDeparture = object;
						flagInsertDepartureAnchorage = true;
					} else {
						array.put(object);
					}

					if ((vmaScheduleAnchorage.getNoticeShipType() == 2)
					|| ((vmaScheduleAnchorage.getNoticeShipType() == 4) && (vmaScheduleAnchorage.getShipAgencyCode().equalsIgnoreCase(vmaItinerary.getDepartureShipAgencyCode())))){
						arrayAnchorageDeparture.put(object);
						
					} else {
						arrayAnchorageArrival.put(object);
					}
				}
				if (flagInsertDepartureAnchorage == true) {
					array.put(arrayDeparture); // Sap xep Khoang neo luot roi
												// duoc them sau cung.
					result.put("vmaScheduleAnchorages", array);
				}
				result.put("vmaScheduleAnchorages", array);
				result.put("vmaScheduleAnchoragesDeparture", arrayAnchorageDeparture);
				result.put("vmaScheduleAnchoragesArrival", arrayAnchorageArrival);
				result.put("vmaScheduleCargoListDeparture", arrayCargoListDeparture);
				result.put("vmaScheduleCargoListArrival", arrayCargoListArrival);
			}
		} catch (Exception e) {
		}

		if (vmaItineraryRemarks != null) {
			result.put("description", vmaItineraryRemarks.getRemarks());
		}
		result.put("anchorageDomesticDuration", anchorageDomesticDuration);
		result.put("anchorageForeignDuration", anchorageForeignDuration);
		result.put("anchorageFreeDuration", anchorageFreeDuration);
		if (anchorageDomesticDuration == 0 || anchorageForeignDuration == 0
				|| anchorageFreeDuration == 0) {
			/*
			 * result.put("Verify_ANCHORAGEDURATION",
			 * DanhMucUtils.encodeUTF8("Thiếu thông tin giờ neo tính phí"));
			 */
		}
		Double TrongTaiXaLan = 0.00;
		Double TongDungTichXaLan = 0.00;
		String TongDungTich = StringPool.BLANK;
		String TongTrongTai = StringPool.BLANK;
		try {
			String xalan = StringPool.BLANK;
			List<VmaScheduleMerging> lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo, 1);
			if (lstVmaScheduleMerging != null
					&& lstVmaScheduleMerging.size() > 0) {
				int i = 0;
				for (VmaScheduleMerging vmaScheduleMerging : lstVmaScheduleMerging) {
					if (vmaScheduleMerging.getItineraryScheduleId() == vmaItineraryScheduleArrival
							.getId()) {
						TrongTaiXaLan = TrongTaiXaLan + vmaScheduleMerging.getDwt().doubleValue();
						TongDungTichXaLan = TongDungTichXaLan + vmaScheduleMerging.getGt().doubleValue();
						i = i + 1;
						if (i == 1) {
							xalan = xalan + "Vào("
									+ vmaScheduleMerging.getShipLashName();
						} else if (i <= lstVmaScheduleMerging.size()) {
							xalan = xalan + " + "
									+ vmaScheduleMerging.getShipLashName();
						}

					}
				}
				if (i > 0) {
					xalan = xalan + ")";					
				}
				TongDungTich = "GT Vào: " + TongDungTichXaLan;
				TongTrongTai = "DWT Vào: " + TrongTaiXaLan;
				result.put("xalan", xalan);
			}
			lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo, 2);
			if (lstVmaScheduleMerging != null
					&& lstVmaScheduleMerging.size() > 0) {
				int i = 0;
				TrongTaiXaLan = 0.00;
				TongDungTichXaLan = 0.00;
				for (VmaScheduleMerging vmaScheduleMerging : lstVmaScheduleMerging) {
					if (vmaScheduleMerging.getItineraryScheduleId() == vmaItineraryScheduleDeparture
							.getId()) {
						TrongTaiXaLan = TrongTaiXaLan + vmaScheduleMerging.getDwt().doubleValue();
						i = i + 1;
						if (i == 1) {
							xalan = xalan + " Rời("
									+ vmaScheduleMerging.getShipLashName();
						} else if (i <= lstVmaScheduleMerging.size()) {
							xalan = xalan + " + "
									+ vmaScheduleMerging.getShipLashName();
						}
					}
				}
				if (i > 0) {
					xalan = xalan + ")";					
				}
				TongDungTich = TongDungTich + ", GT Rời: " + TongDungTichXaLan;
				TongTrongTai = TongTrongTai + ", DWT Rời: " + TrongTaiXaLan;
				
				result.put("xalan", xalan);
			}
			result.put("TrongTaiXaLan", TongDungTich + "; " + TongTrongTai);
		} catch (Exception e) {

		}
		result.put("maritimeCode",vmaItinerary.getMaritimeCode());
		result.put(
				"maritimeNameVN",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(
						UserPortLocalServiceUtil.findByUserId(
								themeDisplay.getUserId()).getPortCode())
						.getMaritimeNameVN());

		BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
		boolean hasDataBoolean = action.export2Report(result,
				"NoticeOfMaritimeDuesAndFees.jrxml",
				"NoticeOfMaritimeDuesAndFees.pdf", 2);

		String UrlFile = request.getContextPath() + "/export/"
				+ "NoticeOfMaritimeDuesAndFees.pdf";
		String UrlFileDownLoad = UrlFile.replace(".pdfs", ".pdf");

		JSONObject data = JSONFactoryUtil.createJSONObject();

		if (hasDataBoolean) {
			data.put("url", UrlFileDownLoad);
		} else {
			data.put("url", StringPool.BLANK);
		}

		return data;
	}

	private String getPortTypeName(String portType) {
		try {
			int param = Integer.valueOf(portType);
			switch (param) {
			case 0:
				return "Cảng biển";

			case 1:
				return "Cảng thủy nội địa";

			case 2:
				return "Cảng ngoài đảo (tuyến bờ ra đảo)";

			default:
				return StringPool.BLANK;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return StringPool.BLANK;
		}
	}

	private String getDomesticTransportCertificateName(
			int domesticTransportCertificate) {
		switch (domesticTransportCertificate) {
		case 0:
			return "Không xác định";
		case 1:
			return "Có giấy phép vận tải nội địa, còn hạn";
		case 2:
			return "Có giấy phép vận tải nội địa, hết hạn";

		default:
			return StringPool.BLANK;
		}
	}

	public static JSONObject noiChuyen(ResourceRequest resourceRequest)
			throws SystemException {
		HttpServletRequest request = resourceRequest;

		String shipPosition = ParamUtil.getString(request, "shipPosition",
				StringPool.BLANK);
		String imoNumber = ParamUtil.getString(request, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request, "registryNumber",
				StringPool.BLANK);
		String documentNameIN = ParamUtil.getString(request, "documentNameIN",
				StringPool.BLANK);
		String nameOfShip = DanhMucUtils.getString(request, "nameOfShip",
				StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(request, "timeOfArrival",
				StringPool.BLANK);
		int start = ParamUtil.getInteger(request, "start", -1);
		int end = ParamUtil.getInteger(request, "end", -1);

		JSONArray array = JSONFactoryUtil.createJSONArray();
		if (Validator.isNotNull(imoNumber) && !imoNumber.isEmpty()
				&& Validator.isNotNull(callSign) && !callSign.isEmpty()
				&& Validator.isNotNull(registryNumber)
				&& !registryNumber.isEmpty()) {
			array = VmaItineraryLocalServiceUtil.dsNoiChuyen(shipPosition,
					imoNumber, callSign, registryNumber, nameOfShip,
					documentNameIN, timeOfArrival, start, end);
		} else if (Validator.isNotNull(imoNumber) && !imoNumber.isEmpty()
				&& Validator.isNotNull(callSign) && !callSign.isEmpty()) {
			array = VmaItineraryLocalServiceUtil.dsNoiChuyen(shipPosition,
					imoNumber, callSign, null, nameOfShip, documentNameIN,
					timeOfArrival, start, end);
		} else if (Validator.isNotNull(registryNumber)
				&& !registryNumber.isEmpty()) {
			array = VmaItineraryLocalServiceUtil.dsNoiChuyen(shipPosition,
					null, null, registryNumber, nameOfShip, documentNameIN,
					timeOfArrival, start, end);
		} else {
			array = VmaItineraryLocalServiceUtil.dsNoiChuyen(shipPosition,
					imoNumber, callSign, registryNumber, nameOfShip,
					documentNameIN, timeOfArrival, start, end);
		}

		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put("data", array);

		return result;
	}

	public static boolean checkChanged(VmaItinerary sourceVmaItinerary,
			String nameOfShip, String flagStateOfShip, String imo,
			String callSign, String registryNumber) {

		if (!sourceVmaItinerary.getNameOfShip().equals(nameOfShip)) {
			return true;
		} else if (!sourceVmaItinerary.getFlagStateOfShip().equals(
				flagStateOfShip)) {
			return true;
		} else if (!sourceVmaItinerary.getImoNumber().equalsIgnoreCase(imo)) {
			return true;
		} else if (!sourceVmaItinerary.getCallSign().equalsIgnoreCase(callSign)) {
			return true;
		} /*
		 * else if(!sourceVmaItinerary.getRegistryNumber().equalsIgnoreCase(
		 * registryNumber)){ return true; }
		 */

		return false;
	}
}
