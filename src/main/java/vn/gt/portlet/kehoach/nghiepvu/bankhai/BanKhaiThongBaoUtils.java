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
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;

import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
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
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.utils.PageType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BanKhaiThongBaoUtils {


	

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

	public static JSONObject buildThanhPhan(ThemeDisplay themeDisplay, long documentName, int documentYear,
			int messageType) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		TempNoticeShipMessage results = TempNoTiceShipMessageLocalServiceUtil
				.getThongBaoLastByDocumentNameAndDocumentYear(documentName, documentYear);

		result.put("code", "NC_3");
		result.put("name", LanguageUtil.get(themeDisplay.getLocale(), "thong-bao"));
		boolean available = false;
		String requestCode = StringPool.BLANK;
		if (Validator.isNotNull(results)) {
			available = true;
			requestCode = results.getRequestCode();
		}

		result.put("available", available);
		result.put("documentName", documentName);
		result.put("documentYear", documentYear);
		result.put("messageType", messageType);
		result.put("requestCode", requestCode);

		String icon = checkDisplayIcon(documentName, documentYear, messageType);

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
		try {
			JSONObject object;

			InterfaceRequest interfaceRequest = null;

			List<TempNoticeShipMessage> results;

			results = TempNoTiceShipMessageLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName,
					documentYear);

			for (int i = 0; i < results.size(); i++) {
				if (results.get(i).getNoticeShipType().equals(PageType.TYPE_THONG_BAO_TAU_DEN_CANG)) {
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

						object.put("remarks", interfaceRequest.getRemarks());
						result.put(object);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public int _thongBao(String requestCode, long documentName, int documentYear, int messageType, int desStatus,
			User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {

		int result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;

		try {

			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
					.findTempNoTiceShipMessageByRequestCode(requestCode);

			if (tempNoticeShipMessage != null) {

				if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI) {

					BusinessUtils.updateResultDeclaration(messageType, documentName, documentYear,
							ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI);
					if (interfaceRequest != null) {
						interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(user.getEmailAddress()));
					}
					// Tich hop send message 30-21
					if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
						boolean resultMethod = TT1TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
						boolean resultMethod = TT2TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
						boolean resultMethod = TT16TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
						boolean resultMethod = TT17TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
						boolean resultMethod = TT6TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
						boolean resultMethod = TT8TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
						boolean resultMethod = TT7TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
						boolean resultMethod = TT9TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
						boolean resultMethod = TT10TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
						boolean resultMethod = TT11TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
						boolean resultMethod = TT4TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
						boolean resultMethod = TT5TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
						boolean resultMethod = TT3TichHopMessageUtils.message_30_21(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					}

				} else if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_TU_CHOI_BAN_KHAI) {
					BusinessUtils.updateResultDeclaration(messageType, documentName, documentYear,
							ChuyenDichTrangThaiUtils.BAN_KHAI_TU_CHOI_BAN_KHAI);
					if (interfaceRequest != null) {
						interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
					}
					// Tich hop send message 30-22
					if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
						boolean resultMethod = TT1TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
						boolean resultMethod = TT2TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					}if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
						boolean resultMethod = TT6TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
						boolean resultMethod = TT17TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
						boolean resultMethod = TT6TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
						boolean resultMethod = TT8TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
						boolean resultMethod = TT7TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
						boolean resultMethod = TT9TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
						boolean resultMethod = TT10TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
						boolean resultMethod = TT11TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
						boolean resultMethod = TT4TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
						boolean resultMethod = TT5TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					} else if (tempDocument.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
						boolean resultMethod = TT3TichHopMessageUtils.message_30_22(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode);
					}
					
					ResultDeclaration resultDeclaration = ResultDeclarationLocalServiceUtil
							.findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(messageType, documentName,
									documentYear, requestCode);
					
					if (Validator.isNotNull(resultDeclaration)) {
						resultDeclaration.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
						ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
					}
					
				}

				tempNoticeShipMessage.setRequestState(desStatus);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);

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
