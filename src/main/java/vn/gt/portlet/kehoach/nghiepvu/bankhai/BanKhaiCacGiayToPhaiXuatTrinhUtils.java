package vn.gt.portlet.kehoach.nghiepvu.bankhai;

import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import org.json.JSONException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.core.ThemeDisplay;

import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultCertificate;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;

import vn.gt.dao.result.service.ResultCertificateLocalServiceUtil;
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
import vn.gt.utils.FormatData;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BanKhaiCacGiayToPhaiXuatTrinhUtils {


	

	private static String checkDisplayIcon(long documentName, int documentYear, int businessTypeCode) {
		List<ResultDeclaration> lstResult = ResultDeclarationLocalServiceUtil
				.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode, documentName, documentYear);
		int state = 0;
		if (lstResult != null && lstResult.size() > 0) {
			state = lstResult.get(0).getRequestState();
			if (state == MessageType.TRANG_THAI_BANG_KHAI_TIEP_NHAN || state == MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
				return "icon_tich.png";
			} else if (state == MessageType.TRANG_THAI_BANG_KHAI_TU_CHOI) {
				return "close.png";
			}
		}

		return "";
	}

	public static JSONObject buildThanhPhan(ThemeDisplay themeDisplay, long documentName, int documentYear) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		TempNoticeShipMessage results = TempNoTiceShipMessageLocalServiceUtil
				.getXacBaoLastByDocumentNameAndDocumentYear(documentName, documentYear);

		result.put("code", "NC_2014");
		result.put("name", LanguageUtil.get(themeDisplay.getLocale(), "cac-giay-to-phai-xuat-trinh"));
		boolean available = false;
		String requestCode = StringPool.BLANK;
		if (Validator.isNotNull(results)) {
			available = true;
			requestCode = results.getRequestCode();
		}
		result.put("available", available);
		result.put("documentName", documentName);
		result.put("documentYear", documentYear);
		result.put("messageType", MessageType.CAC_GIAY_TO_PHAI_XUAT_TRINH);
		result.put("requestCode", requestCode);

		String icon = checkDisplayIcon(documentName, documentYear, MessageType.CAC_GIAY_TO_PHAI_XUAT_TRINH);

		result.put("state", -1);
		if (icon.equals("icon_tich.png")) {
			result.put("state", 1);
		} else if (icon.equals("close.png")) {
			result.put("state", 0);
		}

		return result;
	}

	public int _cacGiayToXuatTrinh(String requestCode, long documentName, int documentYear, int actionType,
			int messageType, int desStatus, User user, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest) {

		int result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;

		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
			String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");

			insertResultCertificate(uploadPortletRequest, actionRequest, documentName, documentYear);
			
			if (actionType == ChuyenDichTrangThaiUtils.GIAY_TO_YEU_CAU_XUAT_TRINH) {
				
				// Tich hop send message 65-05
				if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
					boolean resultMethod = TT1TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
					boolean resultMethod = TT2TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					boolean resultMethod = TT16TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					boolean resultMethod = TT17TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					boolean resultMethod = TT6TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					boolean resultMethod = TT8TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					boolean resultMethod = TT7TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					boolean resultMethod = TT9TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					boolean resultMethod = TT10TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					boolean resultMethod = TT11TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					boolean resultMethod = TT4TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					boolean resultMethod = TT5TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					boolean resultMethod = TT3TichHopMessageUtils.message_65_05(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				}
				
			} else if (actionType == ChuyenDichTrangThaiUtils.GIAY_TO_YEU_CAU_BO_SUNG) {
				// Tich hop send message 65-00
				if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
					boolean resultMethod = TT1TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
					boolean resultMethod = TT2TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					boolean resultMethod = TT16TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					boolean resultMethod = TT17TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					boolean resultMethod = TT6TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					boolean resultMethod = TT8TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					boolean resultMethod = TT7TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					boolean resultMethod = TT9TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					boolean resultMethod = TT10TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					boolean resultMethod = TT11TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					boolean resultMethod = TT4TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					boolean resultMethod = TT5TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					boolean resultMethod = TT3TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				}
				
			} else if (actionType == ChuyenDichTrangThaiUtils.GIAY_TO_CHAP_NHAN) {
				
				BusinessUtils.updateResultDeclaration(messageType, documentName, documentYear,
						ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI);
				
				// Tich hop send message 65-01
				if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
					boolean resultMethod = TT1TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
					boolean resultMethod = TT2TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					boolean resultMethod = TT16TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					boolean resultMethod = TT17TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					boolean resultMethod = TT6TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					boolean resultMethod = TT8TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					boolean resultMethod = TT7TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					boolean resultMethod = TT9TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					boolean resultMethod = TT10TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					boolean resultMethod = TT11TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					boolean resultMethod = TT4TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					boolean resultMethod = TT5TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					boolean resultMethod = TT3TichHopMessageUtils.message_65_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				}

			} else if (actionType == ChuyenDichTrangThaiUtils.GIAY_TO_TU_CHOI) {
				// Tich hop send message 65-00
				if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
					boolean resultMethod = TT1TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
					boolean resultMethod = TT2TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					boolean resultMethod = TT16TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					boolean resultMethod = TT17TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					boolean resultMethod = TT6TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					boolean resultMethod = TT8TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					boolean resultMethod = TT7TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					boolean resultMethod = TT9TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					boolean resultMethod = TT10TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					boolean resultMethod = TT11TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					boolean resultMethod = TT4TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					boolean resultMethod = TT5TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					boolean resultMethod = TT3TichHopMessageUtils.message_65_00(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode);
				}
			}

			result = TT1XuLyNghiepVuUtils.XU_LY_THANH_CONG;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;
		}

		return result;
	}

	public static void insertResultCertificate(UploadPortletRequest requestUpload, ActionRequest resourceRequest,
			long documentName, int documentYear) throws SystemException {

		String data = ParamUtil.getString(requestUpload, "itemsGiayToXuatTrinhChoPhuongTien");
		String data39 = ParamUtil.getString(requestUpload, "itemsGiayToXuatTrinh_39");
		String data40 = ParamUtil.getString(requestUpload, "itemsGiayToXuatTrinh_40");

		try {
			JSONArray dataArray = JSONFactoryUtil.createJSONArray(data);
			JSONArray data39Array = JSONFactoryUtil.createJSONArray(data39);
			JSONArray data40Array = JSONFactoryUtil.createJSONArray(data40);

			JSONObject current = null;
			for (int i = 0; i < dataArray.length(); i++) {
				current = dataArray.getJSONObject(i);

				List<ResultCertificate> lstCertificate = ResultCertificateLocalServiceUtil
						.findByDocumentNameAndDocumentYearAndCertificateCode(documentName, documentYear,
								current.getString("certificateCode"));
				ResultCertificate ettUpdateData = null;
				if ((lstCertificate != null) && (lstCertificate.size() > 0)) {
					ettUpdateData = lstCertificate.get(0);

					ettUpdateData.setIsExamined(current.getBoolean("daKiemTra") ? 1 : 0);
					ettUpdateData.setMandatory(current.getBoolean("yeuCauXuatTrinh") ? 1 : 0);
					ettUpdateData.setCertificateNo(current.getString("giayChungNhan"));
					ettUpdateData.setComment(current.getString("typeText"));
					
					ettUpdateData.setCertificateIssueDate(Validator.isNotNull(current.getString("ngayCap"))
							? FormatData.parseDateShort3StringToDate(current.getString("ngayCap")) : null);
					ettUpdateData.setCertificateExpiredDate(Validator.isNotNull(current.getString("ngayHetHan"))
							? FormatData.parseDateShort3StringToDate(current.getString("ngayHetHan")) : null);
					ettUpdateData.setExaminationDate(new Date());

					ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdateData);
				} else {
					ettUpdateData = new ResultCertificate();
					ettUpdateData.setDocumentName(documentName);
					ettUpdateData.setDocumentYear(documentYear);
					ettUpdateData.setCertificateCode(current.getString("certificateCode"));

					ettUpdateData.setIsExamined(current.getBoolean("daKiemTra") ? 1 : 0);
					ettUpdateData.setMandatory(current.getBoolean("yeuCauXuatTrinh") ? 1 : 0);
					ettUpdateData.setCertificateNo(current.getString("giayChungNhan"));
					ettUpdateData.setComment(current.getString("typeText"));
					ettUpdateData.setCertificateIssueDate(Validator.isNotNull(current.getString("ngayCap"))
							? FormatData.parseStringToDate(current.getString("ngayCap")) : null);
					ettUpdateData.setCertificateExpiredDate(Validator.isNotNull(current.getString("ngayHetHan"))
							? FormatData.parseStringToDate(current.getString("ngayHetHan")) : null);
					ettUpdateData.setExaminationDate(new Date());
					try {
						ResultCertificateLocalServiceUtil.addResultCertificate(ettUpdateData);
						log.info("---addd dataArray-");
					} catch (Exception e) {
						e.printStackTrace();
						log.error(e.getMessage());
					}
				}
			}

			for (int i = 0; i < data39Array.length(); i++) {
				current = data39Array.getJSONObject(i);
				if (current.getLong("id") > 0) {
					ResultCertificate ettUpdate39 = ResultCertificateLocalServiceUtil
							.fetchResultCertificate(current.getLong("id"));

					ettUpdate39.setIsExamined(current.getBoolean("daKiemTra") ? 1 : 0);
					ettUpdate39.setMandatory(current.getBoolean("yeuCauXuatTrinh") ? 1 : 0);
					ettUpdate39.setCertificateNo(current.getString("giayChungNhan"));
					ettUpdate39.setComment(current.getString("typeText"));
					ettUpdate39.setCertificateIssueDate(Validator.isNotNull(current.getString("ngayCap"))
							? FormatData.parseStringToDate(current.getString("ngayCap")) : null);
					ettUpdate39.setCertificateExpiredDate(Validator.isNotNull(current.getString("ngayHetHan"))
							? FormatData.parseStringToDate(current.getString("ngayHetHan")) : null);
					ettUpdate39.setExaminationDate(new Date());

					ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdate39);
				} else {
					ResultCertificate ettUpdate39 = new ResultCertificate();

					ettUpdate39.setDocumentName(documentName);
					ettUpdate39.setDocumentYear(documentYear);
					ettUpdate39.setCertificateCode(String.valueOf(39));

					ettUpdate39.setIsExamined(current.getBoolean("daKiemTra") ? 1 : 0);
					ettUpdate39.setMandatory(current.getBoolean("yeuCauXuatTrinh") ? 1 : 0);
					ettUpdate39.setCertificateNo(current.getString("giayChungNhan"));
					ettUpdate39.setComment(current.getString("typeText"));
					ettUpdate39.setCertificateIssueDate(Validator.isNotNull(current.getString("ngayCap"))
							? FormatData.parseDateShort3StringToDate(current.getString("ngayCap")) : null);
					ettUpdate39.setCertificateExpiredDate(Validator.isNotNull(current.getString("ngayHetHan"))
							? FormatData.parseDateShort3StringToDate(current.getString("ngayHetHan")) : null);
					ettUpdate39.setExaminationDate(new Date());

					try {
						ResultCertificateLocalServiceUtil.addResultCertificate(ettUpdate39);
						log.info("---addd dataArray-");
					} catch (Exception e) {
						e.printStackTrace();
						log.error(e.getMessage());
					}
				}

			}

			for (int i = 0; i < data40Array.length(); i++) {
				current = data40Array.getJSONObject(i);
				if (current.getLong("id") > 0) {
					ResultCertificate ettUpdate40 = ResultCertificateLocalServiceUtil
							.fetchResultCertificate(current.getLong("id"));

					ettUpdate40.setIsExamined(current.getBoolean("daKiemTra") ? 1 : 0);
					ettUpdate40.setMandatory(current.getBoolean("yeuCauXuatTrinh") ? 1 : 0);
					ettUpdate40.setCertificateNo(current.getString("giayChungNhan"));
					ettUpdate40.setComment(current.getString("typeText"));
					ettUpdate40.setCertificateIssueDate(Validator.isNotNull(current.getString("ngayCap"))
							? FormatData.parseDateShort3StringToDate(current.getString("ngayCap")) : null);
					ettUpdate40.setCertificateExpiredDate(Validator.isNotNull(current.getString("ngayHetHan"))
							? FormatData.parseDateShort3StringToDate(current.getString("ngayHetHan")) : null);
					ettUpdate40.setExaminationDate(new Date());

					ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdate40);
				} else {
					ResultCertificate ettUpdate40 = new ResultCertificate();

					ettUpdate40.setDocumentName(documentName);
					ettUpdate40.setDocumentYear(documentYear);
					ettUpdate40.setCertificateCode(String.valueOf(40));

					ettUpdate40.setIsExamined(current.getBoolean("daKiemTra") ? 1 : 0);
					ettUpdate40.setMandatory(current.getBoolean("yeuCauXuatTrinh") ? 1 : 0);
					ettUpdate40.setCertificateNo(current.getString("giayChungNhan"));
					ettUpdate40.setComment(current.getString("typeText"));
					ettUpdate40.setCertificateIssueDate(Validator.isNotNull(current.getString("ngayCap"))
							? FormatData.parseDateShort3StringToDate(current.getString("ngayCap")) : null);
					ettUpdate40.setCertificateExpiredDate(Validator.isNotNull(current.getString("ngayHetHan"))
							? FormatData.parseDateShort3StringToDate(current.getString("ngayHetHan")) : null);
					ettUpdate40.setExaminationDate(new Date());
					try {
						ResultCertificateLocalServiceUtil.addResultCertificate(ettUpdate40);
						log.info("---addd data40Array-");
					} catch (Exception e) {
						e.printStackTrace();
						log.error(e.getMessage());
					}
				}
			}
		} catch (JSONException e) {
			log.error(e.getMessage());
		}

	}

}
