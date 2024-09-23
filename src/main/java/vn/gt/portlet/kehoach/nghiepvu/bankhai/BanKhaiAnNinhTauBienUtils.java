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
import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;

import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.portlet.kehoach.tt1.TT1JSONProvider;
import vn.gt.portlet.kehoach.tt1.TT1TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt1.TT1XuLyNghiepVuUtils;
import vn.gt.portlet.kehoach.tt16.TT16TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt17.TT17TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt2.TT2TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt3.TT3TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt4.TT4TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt6.TT6TichHopMessageUtils;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BanKhaiAnNinhTauBienUtils {


	

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

		TempShipSecurityMessage results = TempShipSecurityMessageLocalServiceUtil
				.getLastByDocumentNameAndDocumentYear(documentName, documentYear);

		result.put("code", "NC_1");
		result.put("name", LanguageUtil.get(themeDisplay.getLocale(), "ban-khai-an-ninh-tau-bien"));
		boolean available = false;
		String requestCode = StringPool.BLANK;
		if (Validator.isNotNull(results)) {
			available = true;
			requestCode = results.getRequestCode();
		}
		result.put("available", available);
		result.put("documentName", documentName);
		result.put("documentYear", documentYear);
		result.put("messageType", MessageType.BAN_KHAI_AN_NINH_TAU_BIEN);
		result.put("requestCode", requestCode);

		String icon = checkDisplayIcon(documentName, documentYear, MessageType.BAN_KHAI_AN_NINH_TAU_BIEN);
		
		result.put("state", -1);
		if (icon.equals("icon_tich.png")) {
			result.put("state", 1);
		} else if (icon.equals("close.png")) {
			result.put("state", 0);
		}

		return result;
	}
	
	public static JSONArray getFileThanhPhanVersionList(ThemeDisplay themeDisplay, long documentName, int documentYear, int messageType) {
		
		JSONArray result = JSONFactoryUtil.createJSONArray();
		JSONObject object;
		
		InterfaceRequest interfaceRequest = null;

		List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil
				.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName, documentYear);
		
		for (int i = 0; i < results.size(); i++) {
			interfaceRequest = InterfaceRequestLocalServiceUtil.
					findByRequestCode(results.get(i).getRequestCode());
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

				object.put("remarks", interfaceRequest.getRemarks());
				result.put(object);
			}

		}

		return result;
	}

	public int _banKhaiAnNinhTauBien(String requestCode, long documentName, int documentYear, int messageType,
			int desStatus, User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {

		int result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;
		
		try {
			
			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
			
			String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (Validator.isNull(interfaceRequest)) {
				interfaceRequest = InterfaceRequestLocalServiceUtil.fetchByF_BY_documentNameRef(String.valueOf(documentName), String.valueOf(10));
			}
			
			ResultDeclaration resultDeclaration = null;
			
			List<ResultDeclaration> lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
					MessageType.BAN_KHAI_AN_NINH_TAU_BIEN, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
			if (lstDeclaration != null && lstDeclaration.size() > 0) {
				resultDeclaration = lstDeclaration.get(0);
			}
			List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil.findByRequestCode(requestCode);
			if (results != null && results.size() > 0) {
				TempShipSecurityMessage tempShipSecurityMessage = results.get(0);
				if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI) {

					resultDeclaration.setRemarks(BusinessUtils.getRemarkChapNhan(user.getEmailAddress()));
					
					ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
					
					BusinessUtils.updateResultDeclaration(messageType, documentName, documentYear,
							ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI);
					if (interfaceRequest != null) {
						interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(user.getEmailAddress()));
					}
					
					// Tich hop send message 10-21
					if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
						boolean resultMethod = TT1TichHopMessageUtils.message_10_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
						boolean resultMethod = TT2TichHopMessageUtils.message_10_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
						boolean resultMethod = TT16TichHopMessageUtils.message_10_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
						boolean resultMethod = TT17TichHopMessageUtils.message_10_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
						boolean resultMethod = TT6TichHopMessageUtils.message_10_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
						boolean resultMethod = TT4TichHopMessageUtils.message_10_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
						boolean resultMethod = TT3TichHopMessageUtils.message_10_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					}
					
				} else if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_TU_CHOI_BAN_KHAI) {

					resultDeclaration.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
					
					ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
					
					BusinessUtils.updateResultDeclaration(messageType, documentName, documentYear,
							ChuyenDichTrangThaiUtils.BAN_KHAI_TU_CHOI_BAN_KHAI);
					if (interfaceRequest != null) {
						interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
					}
					
					// Tich hop send message 10-22
					if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
						boolean resultMethod = TT1TichHopMessageUtils.message_10_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
						boolean resultMethod = TT2TichHopMessageUtils.message_10_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
						boolean resultMethod = TT16TichHopMessageUtils.message_10_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
						boolean resultMethod = TT17TichHopMessageUtils.message_10_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
						boolean resultMethod = TT6TichHopMessageUtils.message_10_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
						boolean resultMethod = TT4TichHopMessageUtils.message_10_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
						boolean resultMethod = TT3TichHopMessageUtils.message_10_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
					}

				}

				tempShipSecurityMessage.setRequestState(desStatus);
				TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(tempShipSecurityMessage);

				if (interfaceRequest != null) {
					InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
				}

			}
			result = TT1XuLyNghiepVuUtils.XU_LY_THANH_CONG;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;
		}
		
		return result;
	}
	
}
