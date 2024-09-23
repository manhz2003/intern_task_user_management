package vn.gt.portlet.kehoach.nghiepvu.bankhai;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.nghiepvu.model.*;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.core.ThemeDisplay;
import com.fds.nsw.liferay.core.PortalUtil;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;

import vn.gt.dao.result.service.ResultCompetionLocalServiceUtil;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.portlet.kehoach.tt1.TT1JSONProvider;
import vn.gt.portlet.kehoach.tt1.TT1TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt1.TT1XuLyNghiepVuUtils;
import vn.gt.portlet.kehoach.tt10.TT10TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt11.TT11TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt14.TT14TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt15.TT15TichHopMessageUtils;
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
import vn.gt.tichhop.message.*;
import vn.gt.utils.FormatData;

import lombok.extern.slf4j.Slf4j;
import vn.gt.utils.KeyParams;

@Slf4j
public class BanKhaiKetQuaPheDuyetHoSoUtils {


	

	private static String checkDisplayIcon(long documentName, int documentYear, int businessTypeCode) {
		List<ResultDeclaration> lstResult = ResultDeclarationLocalServiceUtil
				.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode, documentName, documentYear);
		int state = 0;
		if (lstResult != null && lstResult.size() > 0) {
			state = lstResult.get(0).getRequestState();
			// if (state == MessageType.TRANG_THAI_BANG_KHAI_TIEP_NHAN
			// || state == MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
			// return "icon_tich.png";
			// } else if (state == MessageType.TRANG_THAI_BANG_KHAI_TU_CHOI) {
			// return "close.png";
			// }
		}

		return "";
	}

	public static JSONObject buildThanhPhan(ThemeDisplay themeDisplay, long documentName, int documentYear) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		TempNoticeShipMessage results = TempNoTiceShipMessageLocalServiceUtil
				.getXacBaoLastByDocumentNameAndDocumentYear(documentName, documentYear);

		result.put("code", "NC_23");
		result.put("name", LanguageUtil.get(themeDisplay.getLocale(), "ket-qua-phe-duyet-ho-so"));
		boolean available = false;
		String requestCode = StringPool.BLANK;
		if (Validator.isNotNull(results)) {
			available = true;
			requestCode = results.getRequestCode();
		}
		result.put("available", available);
		result.put("documentName", documentName);
		result.put("documentYear", documentYear);
		result.put("messageType", MessageType.XAC_NHAN_HOAN_THANH_THU_TUC);
		result.put("requestCode", requestCode);

		String icon = checkDisplayIcon(documentName, documentYear, MessageType.XAC_NHAN_HOAN_THANH_THU_TUC);

		result.put("state", -1);
		if (icon.equals("icon_tich.png")) {
			result.put("state", 1);
		} else if (icon.equals("close.png")) {
			result.put("state", 0);
		}

		return result;
	}

	public int _xacNhanHoanThanhThuTuc(String requestCode, long documentName, int documentYear, int messageType,
			int desStatus, User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {

		int result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;

		try {

			String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");

			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			if (desStatus == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				xacNhanHoanThanhThuTucUpgrade(uploadPortletRequest, tempDocument.getDocumentName(),
						tempDocument.getDocumentYear());
				// Tich hop send message 99-23
				if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
					boolean resultMethod = TT1TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
					boolean resultMethod = TT2TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)) {
					boolean resultMethod = TT14TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
					boolean resultMethod = TT15TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);

				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					boolean resultMethod = TT16TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					boolean resultMethod = TT17TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					boolean resultMethod = TT6TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					boolean resultMethod = TT8TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					boolean resultMethod = TT7TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					boolean resultMethod = TT9TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					boolean resultMethod = TT10TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					boolean resultMethod = TT11TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					boolean resultMethod = TT4TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					boolean resultMethod = TT5TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					boolean resultMethod = TT3TichHopMessageUtils.message_99_23(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				}
			} else if (desStatus == ChuyenDichTrangThaiUtils.THU_TUC_DE_NGHI_CAP_GIAY_PHEP) {
				xacNhanHoanThanhThuTucUpgrade(uploadPortletRequest, tempDocument.getDocumentName(),
						tempDocument.getDocumentYear());
				// TODO Chuyen tiep desStatus sang ChuyenDichTrangThaiUtils.THU_TUC_DE_NGHI_SUA_GIAY_PHEP.
				int flagChuyentiep = 0;
				if ((tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC"))
						|| (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH))
						|| (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7))
						|| (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI))
						|| (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI))
						|| (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI))
						|| (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG))
				){
					List<IssuePortClearance> lstPortClearances = IssuePortClearanceLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
					if (Validator.isNotNull(lstPortClearances) && lstPortClearances.size() > 0)
					{
						IssuePortClearance item = lstPortClearances.get(0);
						if (item.getRequestState() == TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI) {
							flagChuyentiep = 1;
						}
					}
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					List<IssuePermissionForTransit> lstPerForTransit = IssuePermissionForTransitLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
					if (Validator.isNotNull(lstPerForTransit) && lstPerForTransit.size() > 0)
					{
						IssuePermissionForTransit item = lstPerForTransit.get(0);
						if (item.getRequestState() == TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI) {
							flagChuyentiep = 1;
						}
					}
				}
				if (flagChuyentiep == 1) {
					desStatus = ChuyenDichTrangThaiUtils.THU_TUC_DE_NGHI_SUA_GIAY_PHEP;
				}


			} else if (desStatus == ChuyenDichTrangThaiUtils.THU_TUC_HUY_THU_TUC) {
				xacNhanHoanThanhThuTucTruongHopHuyUpgrade(uploadPortletRequest, tempDocument.getDocumentName(),
						tempDocument.getDocumentYear());
				// Tich hop send message 99-24
				if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("NC")) {
					boolean resultMethod = TT1TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase("XC")) {
					boolean resultMethod = TT2TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)) {
					boolean resultMethod = TT14TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
					boolean resultMethod = TT15TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);

				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					boolean resultMethod = TT16TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					boolean resultMethod = TT17TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					boolean resultMethod = TT6TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					boolean resultMethod = TT8TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					boolean resultMethod = TT7TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					boolean resultMethod = TT9TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					boolean resultMethod = TT10TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					boolean resultMethod = TT11TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					boolean resultMethod = TT4TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					boolean resultMethod = TT5TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					boolean resultMethod = TT3TichHopMessageUtils.message_99_24(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);
				}
			}

			if (tempDocument != null) {
				tempDocument.setDocumentStatusCode(desStatus);
				TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
				result = desStatus;
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;
		}

		return result;
	}

	private static void xacNhanHoanThanhThuTucUpgrade(UploadPortletRequest resourceRequest, long documentName,
			int documentYear) {
		try {
			String certificateNo = ParamUtil.getString(resourceRequest, "soChungNhan");
			Date approvalTime = ParamUtil.getDate(resourceRequest, "ngayPheDuyet", FormatData.formatDateShortZone);

			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
			
			String maritimeReference = StringPool.BLANK;
			String portOfArrivalCode = StringPool.BLANK;
			
			if (Validator.isNotNull(tempDocument.getMaritimeCode())) {
				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(tempDocument.getMaritimeCode());
				portOfArrivalCode = Validator.isNotNull(dmMaritime) ? dmMaritime.getMaritimeCode() : StringPool.BLANK;
				maritimeReference = Validator.isNotNull(dmMaritime) ? dmMaritime.getMaritimeReference() : StringPool.BLANK;
			}
			
			ResultCompletion reCompetion = ResultCompetionLocalServiceUtil
					.findByDocumentNameAndDocumentYear(documentName, documentYear);
			if (reCompetion == null) {
				reCompetion = new ResultCompletion();
				reCompetion.setMaritimeCode(tempDocument.getMaritimeCode());
				reCompetion.setNameOfShip(tempDocument.getShipName());
				reCompetion.setNameOfMaster(tempDocument.getShipCaptain());
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
//				reCompetion.setFlagStateOfShip(flagStateOfShip);
//				reCompetion.setGrossTonnage(grossTonnage);
				reCompetion.setApprovalName(PortalUtil.getUser(resourceRequest).getEmailAddress());

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				reCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);

				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), approvalTime));
//				reCompetion.setCertificateNo(certificateNo);
				reCompetion.setDocumentName(documentName);
				reCompetion.setDocumentYear(documentYear);
				reCompetion.setRequestCode(UUID.randomUUID().toString());

				ResultCompetionLocalServiceUtil.addResultCompetion(reCompetion);
			} else {
				reCompetion.setMaritimeCode(tempDocument.getMaritimeCode());
				reCompetion.setNameOfShip(tempDocument.getShipName());
				reCompetion.setNameOfMaster(tempDocument.getShipCaptain());
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
//				reCompetion.setFlagStateOfShip(flagStateOfShip);
//				reCompetion.setGrossTonnage(grossTonnage);
				reCompetion.setApprovalName(PortalUtil.getUser(resourceRequest).getEmailAddress());

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				reCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), approvalTime));

//				reCompetion.setCertificateNo(certificateNo);
				ResultCompetionLocalServiceUtil.updateResultCompetion(reCompetion);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private static void xacNhanHoanThanhThuTucTruongHopHuyUpgrade(UploadPortletRequest resourceRequest,
			long documentName, int documentYear) {
		ResultCompletion reCompetion = null;
		reCompetion = ResultCompetionLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
		String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip");
		String nameOfMaster = ParamUtil.getString(resourceRequest, "nameOfMaster");
		String portOfArrivalCode = ParamUtil.getString(resourceRequest, "portOfArrivalCode");
		String certificateNo = ParamUtil.getString(resourceRequest, "certificateNo");
		String maritimeReference = ParamUtil.getString(resourceRequest, "maritimeReference");
		String flagStateOfShip = ParamUtil.getString(resourceRequest, "flagStateOfShip");
		double grossTonnage = ParamUtil.getDouble(resourceRequest, "grossTonnage", 0L);
		Date approvalTime = ParamUtil.getDate(resourceRequest, "approvalTime", FormatData.formatDateShort3);
		String approvalName = ParamUtil.getString(resourceRequest, "approvalName");

		try {
			if (reCompetion == null) {
				reCompetion = new ResultCompletion();
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
				reCompetion.setResponseStatusCVHH(MessageType.TU_CHOI);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils.getRemarkTuChoiTB(
						PortalUtil.getUser(resourceRequest).getEmailAddress(), "Huy ho so", approvalTime));

				reCompetion.setDocumentName(documentName);
				reCompetion.setDocumentYear(documentYear);
				reCompetion.setRequestCode(UUID.randomUUID().toString());

				ResultCompetionLocalServiceUtil.addResultCompetion(reCompetion);
			} else {
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
				reCompetion.setResponseStatusCVHH(MessageType.TU_CHOI);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils.getRemarkTuChoiTB(
						PortalUtil.getUser(resourceRequest).getEmailAddress(), "Huy ho so", approvalTime));

				ResultCompetionLocalServiceUtil.updateResultCompetion(reCompetion);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

}
