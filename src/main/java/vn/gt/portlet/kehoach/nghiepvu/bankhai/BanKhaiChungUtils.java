package vn.gt.portlet.kehoach.nghiepvu.bankhai;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.core.ThemeDisplay;

import vn.gt.dao.danhmucgt.service.DmGTFunctionTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.InterfaceRequest;

import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;

import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.portlet.kehoach.tt1.TT1JSONProvider;
import vn.gt.portlet.kehoach.tt1.TT1TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt1.TT1XuLyNghiepVuUtils;
import vn.gt.portlet.kehoach.tt10.TT10TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt11.TT11TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt16.TT16TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt17.TT17TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt2.TT2TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt3.TT3TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt4.TT4TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt5.TT5TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt6.TT6TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt7.TT7TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt8.TT8TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt9.TT9TichHopMessageUtils;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.portlet.phuongtien.VmaItineraryUtils;
import vn.gt.portlet.phuongtien.VmaScheduleCargolistUtils;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BanKhaiChungUtils {


	

	private static String checkDisplayIcon(long documentName, int documentYear, int businessTypeCode) {
		List<ResultDeclaration> lstResult = ResultDeclarationLocalServiceUtil
				.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode, documentName, documentYear);
		int state = 0;
		if (lstResult != null && lstResult.size() > 0) {
			state = lstResult.get(0).getRequestState();
			if (state == MessageType.TRANG_THAI_BANG_KHAI_TIEP_NHAN
					|| state == MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
				return "icon_tich.png";
			} else if (state == MessageType.TRANG_THAI_BANG_KHAI_TU_CHOI) {
				return "close.png";
			}
		}

		return "";
	}

	public static JSONObject buildThanhPhan(ThemeDisplay themeDisplay, long documentName, int documentYear) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		TempGeneralDeclaration results = TempGeneralDeclarationLocalServiceUtil.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
//		TempeCargoDeclaration results = TempCargoDeclarationLoc.alServiceUtil
//				.getLastByDocumentNameAndDocumentYear(documentName, documentYear);

		result.put("code", "NC_6");
		result.put("name", LanguageUtil.get(themeDisplay.getLocale(), "ban-khai-chung"));
		boolean available = false;
		String requestCode = StringPool.BLANK;
		if (Validator.isNotNull(results)) {
			available = true;
			requestCode = results.getRequestCode();
		}
		result.put("available", available);
		result.put("documentName", documentName);
		result.put("documentYear", documentYear);
		result.put("messageType", MessageType.BAN_KHAI_CHUNG);
		result.put("requestCode", requestCode);

		String icon = checkDisplayIcon(documentName, documentYear, MessageType.BAN_KHAI_CHUNG);
		
		result.put("state", -1);
		if (icon.equals("icon_tich.png")) {
			result.put("state", 1);
		} else if (icon.equals("close.png")) {
			result.put("state", 0);
		}

		return result;
	}

	public static JSONArray getFileThanhPhanVersionList(ThemeDisplay themeDisplay, long documentName, int documentYear,
			int messageType) {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		JSONObject object;
		
		InterfaceRequest interfaceRequest = null;

		List<TempGeneralDeclaration> results = TempGeneralDeclarationLocalServiceUtil
				.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName, documentYear);

		List<ResultDeclaration> lstDeclaration = null;
		
		lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.BAN_KHAI_CHUNG, documentName, documentYear);
		
		String remarks = StringPool.BLANK;
		
		if (lstDeclaration != null && lstDeclaration.size() > 0) {
			ResultDeclaration resultDeclaration = lstDeclaration.get(0);
			remarks = resultDeclaration.getRemarks();
		}
		
		for (int i = 0; i < results.size(); i++) {
			interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(results.get(i).getRequestCode());
			
			object = JSONFactoryUtil.createJSONObject();
			if (Validator.isNotNull(interfaceRequest)) {

				String phienBan = interfaceRequest.getRequestVersion() + " - "
						+ (Validator.isNotNull(interfaceRequest) ? DmGTFunctionTypeLocalServiceUtil
								.findNameByFunctionTypeCode(interfaceRequest.getFunctionType()) : "");
				object.put("version", phienBan);
				object.put("status", DmGtStatusLocalServiceUtil
						.findNameByStatusCode(results.get(i).getRequestState(), messageType));
				object.put("sendDate", interfaceRequest.getRequestedDate());

				object.put("requestCode", results.get(i).getRequestCode());

				object.put("remarks", remarks);
				result.put(object);
			}

		}
		return result;
	}

	public int _banKhaiChung(String requestCode, long documentName, int documentYear, int messageType, int desStatus,
			User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {

		int result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;
		int isArrivalFlag = 0;
		try {
			
			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
			
			String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			List<TempGeneralDeclaration> lstGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil
					.findByRequestCode(requestCode);

			if (lstGeneralDeclaration != null && lstGeneralDeclaration.size() > 0) {
				TempGeneralDeclaration declaration = lstGeneralDeclaration.get(0);

				if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI || 
						desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_DA_DUYET) {
					if (interfaceRequest != null) {
						interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(user.getEmailAddress()));
					}
					ResultDeclaration resultDeclaration = ResultDeclarationLocalServiceUtil
							.findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(messageType, documentName,
									documentYear, requestCode);
					
					if (Validator.isNotNull(resultDeclaration)) {
						resultDeclaration.setRemarks(BusinessUtils.getRemarkChapNhan(user.getEmailAddress()));
						ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
					}
				} else if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_TU_CHOI_BAN_KHAI) {
					if (interfaceRequest != null) {
						interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
					}
					// Tich hop send message 50-22
					if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
						boolean resultMethod = TT1TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
						boolean resultMethod = TT2TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
						boolean resultMethod = TT16TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
						boolean resultMethod = TT17TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
						boolean resultMethod = TT6TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
						boolean resultMethod = TT8TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
						boolean resultMethod = TT7TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
						boolean resultMethod = TT9TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
						boolean resultMethod = TT10TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
						boolean resultMethod = TT11TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
						boolean resultMethod = TT4TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
						boolean resultMethod = TT5TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
						boolean resultMethod = TT3TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					}
					

					ResultDeclaration resultDeclaration = ResultDeclarationLocalServiceUtil
							.findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(messageType, documentName,
									documentYear, requestCode);
					
					if (Validator.isNotNull(resultDeclaration)) {
						resultDeclaration.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
						ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
					}
				} else if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_KHAI_HUY) {
					if (interfaceRequest != null) {
						interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
					}
					if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
						boolean resultMethod = TT1TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
						boolean resultMethod = TT2TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
						boolean resultMethod = TT16TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
						boolean resultMethod = TT17TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					}  else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
						boolean resultMethod = TT6TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
						boolean resultMethod = TT8TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
						boolean resultMethod = TT7TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
						boolean resultMethod = TT9TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
						boolean resultMethod = TT10TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
						boolean resultMethod = TT11TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
						boolean resultMethod = TT4TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
						boolean resultMethod = TT5TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
						boolean resultMethod = TT3TichHopMessageUtils.message_50_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					}
					ResultDeclaration resultDeclaration = ResultDeclarationLocalServiceUtil
							.findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(messageType, documentName,
									documentYear, requestCode);
					
					if (Validator.isNotNull(resultDeclaration)) {
						resultDeclaration.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
						ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
					}
				}

				declaration.setRequestState(desStatus);
				TempGeneralDeclarationLocalServiceUtil.updateTempGeneralDeclaration(declaration);
				isArrivalFlag = declaration.getIsArrival();
				if (interfaceRequest != null) {
					InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
				}
			}
			result = TT1XuLyNghiepVuUtils.XU_LY_THANH_CONG;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;
		}
		try {
			//check active
			boolean active = VmaItineraryUtils.checkActiveVma(user.getUserId());

			if ((active) && (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI ||
					desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_DA_DUYET) ){
				log.info("BanKhaiChungUtils >>>>>>>> ");
				VmaScheduleCargolistUtils.autoFillCargolistAfterGeneralDeclarationUpdate(documentName, documentYear, isArrivalFlag);
			}

		} catch (Exception e) {
			// do nothing
		}

		return result;
	}

}
