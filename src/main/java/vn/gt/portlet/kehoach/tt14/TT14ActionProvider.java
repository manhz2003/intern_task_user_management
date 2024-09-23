package vn.gt.portlet.kehoach.tt14;

import java.io.File;
import java.util.Date;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ServiceContext;
import com.fds.nsw.liferay.core.ThemeDisplay;

import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDebitnote;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.portlet.kehoach.utils.PhieuThanhToanUtils;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.report.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TT14ActionProvider {



	public static JSONObject actionKeHoach(ThemeDisplay themeDisplay, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		// START
		// PARAM TO BUILD CHUYEN DICH TRANG THAI
		int documentName = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.DOCUMENT_NAME);
		int documentYear = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.DOCUMENT_YEAR);
		int messageType = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.MESSAGE_TYPE);
		int desStatus = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.DES_STATUS);
		int requestState = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.REQUEST_STATE);
		int documentStatusCode = ParamUtil.getInteger(uploadPortletRequest,
				ChuyenDichTrangThaiUtils.DOCUMENT_STATUS_CODE);
		String requestCode = ParamUtil.getString(uploadPortletRequest, ChuyenDichTrangThaiUtils.REQUEST_CODE);
		int actionType = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.ACTION_TYPE);
		
		// END
		log.info("documentName: " + documentName);
		log.info("documentYear: " + documentYear);
		log.info("messageType: " + messageType);
		log.info("desStatus: " + desStatus);
		log.info("requestState: " + requestState);
		log.info("documentStatusCode: " + documentStatusCode);
		
		int chuyenDichStatus = 0;
		if (messageType == ChuyenDichTrangThaiUtils.THANH_PHAN_HO_SO) {
			chuyenDichStatus = ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear, desStatus, Boolean.TRUE);
		}

		log.info("chuyenDichStatus: " + chuyenDichStatus);

		if (chuyenDichStatus != ChuyenDichTrangThaiUtils.CHUYEN_DICH_THAT_BAI) {
			// CHUYEN DICH TRANG THAI THANH CONG
			// Xu ly nghiep vu sau khi chuyen dich
			if (messageType == ChuyenDichTrangThaiUtils.THANH_PHAN_HO_SO) {
				int state = TT14XuLyNghiepVuUtils.doActionHoSo(documentName, documentYear, actionType, requestState,
						documentStatusCode, desStatus, themeDisplay.getUser(), uploadPortletRequest, actionRequest,
						Boolean.TRUE);
				// rollback neu xu ly nghiep vu xay ra loi
				if (state == TT14XuLyNghiepVuUtils.XU_LY_THAT_BAI) {
					ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear, requestState, Boolean.TRUE);
				}
			} else {
				TT14XuLyNghiepVuUtils.doActionBanKhai(documentName, documentYear, actionType, requestState,
						documentStatusCode, desStatus, themeDisplay.getUser(), messageType, requestCode, Boolean.TRUE,
						uploadPortletRequest, actionRequest);
			}
			
//			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
//			
//			if (tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_DA_TIEP_NHAN) {
//
//				_doCreatePhieuThanhToan(uploadPortletRequest, actionRequest, themeDisplay, documentName,
//						documentYear);
//
//			}
			
		}

		return result;
	}

	public static JSONObject actionThuTuc(ThemeDisplay themeDisplay, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		// START
		// PARAM TO BUILD CHUYEN DICH TRANG THAI
		int documentName = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.DOCUMENT_NAME);
		int documentYear = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.DOCUMENT_YEAR);
		int messageType = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.MESSAGE_TYPE);
		int desStatus = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.DES_STATUS);
		int requestState = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.REQUEST_STATE);
		int documentStatusCode = ParamUtil.getInteger(uploadPortletRequest,
				ChuyenDichTrangThaiUtils.DOCUMENT_STATUS_CODE);
		String requestCode = ParamUtil.getString(uploadPortletRequest, ChuyenDichTrangThaiUtils.REQUEST_CODE);
		int actionType = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.ACTION_TYPE);
		
		// END
		log.info("documentName: " + documentName);
		log.info("documentYear: " + documentYear);
		log.info("messageType: " + messageType);
		log.info("desStatus: " + desStatus);
		log.info("requestState: " + requestState);
		log.info("documentStatusCode: " + documentStatusCode);
		
		int chuyenDichStatus = 0;
		if (messageType == ChuyenDichTrangThaiUtils.THANH_PHAN_HO_SO) {
			chuyenDichStatus = ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear, desStatus, Boolean.FALSE);
		}

		log.info("chuyenDichStatus: " + chuyenDichStatus);

		if (chuyenDichStatus != ChuyenDichTrangThaiUtils.CHUYEN_DICH_THAT_BAI) {
			// CHUYEN DICH TRANG THAI THANH CONG
			// Xu ly nghiep vu sau khi chuyen dich
			if (messageType == ChuyenDichTrangThaiUtils.THANH_PHAN_HO_SO) {
				int state = TT14XuLyNghiepVuUtils.doActionHoSo(documentName, documentYear, actionType, requestState,
						documentStatusCode, desStatus, themeDisplay.getUser(), uploadPortletRequest, actionRequest,
						Boolean.FALSE);
				// rollback neu xu ly nghiep vu xay ra loi
				if (state == TT14XuLyNghiepVuUtils.XU_LY_THAT_BAI) {
					ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear, documentStatusCode, Boolean.FALSE);
				}
			} else {
				chuyenDichStatus = TT14XuLyNghiepVuUtils.doActionBanKhai(documentName, documentYear, actionType,
						requestState, documentStatusCode, desStatus, themeDisplay.getUser(), messageType, requestCode,
						Boolean.FALSE, uploadPortletRequest, actionRequest);
			}
			
			long totalpayment = ParamUtil.getLong(uploadPortletRequest, "lePhi");
			// create Phieu danh toan
			if (chuyenDichStatus == ChuyenDichTrangThaiUtils.THU_TUC_DE_NGHI_CAP_GIAY_PHEP) {
//				if (totalpayment > 0) {
					_doCreatePhieuThanhToan(uploadPortletRequest, actionRequest, themeDisplay, documentName,
							documentYear);
//				}
			} else if (chuyenDichStatus == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {

//				if (totalpayment > 0) {
					_doCreatePhieuThanhToan(uploadPortletRequest, actionRequest, themeDisplay, documentName,
							documentYear);
//				}

			} else if (chuyenDichStatus == ChuyenDichTrangThaiUtils.THU_TUC_HUY_THU_TUC) {

//				if (totalpayment > 0) {
//					_doCreatePhieuThanhToan(uploadPortletRequest, actionRequest, themeDisplay, documentName,
//							documentYear);
//				}

				TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
						.findByDocumentNameAnddocumentYear(documentName, documentYear);

				if (Validator.isNotNull(tempDebitNote) && tempDebitNote.getTotalpayment().doubleValue() > 0) {
					// Tich hop send message 99-30
					boolean resultMethod = TT14TichHopMessageUtils.message_99_30(
							themeDisplay.getUser().getEmailAddress(), uploadPortletRequest, actionRequest, documentName,
							documentYear);
				}
			}
		}

		return result;
	}

	private static void _doCreatePhieuThanhToan(UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest,
			ThemeDisplay themeDisplay, long documentName, int documentYear) {
		PhieuThanhToanUtils action = new PhieuThanhToanUtils();
		BusinessUtils businessUtils = new BusinessUtils();

		Date todate = null;
		String transactionid = StringPool.BLANK;
		long totalpayment = ParamUtil.getLong(uploadPortletRequest, "lePhi", 0);
		String corporationid = StringPool.BLANK;
		String organization = BusinessUtils.getOrganizationFromUser(actionRequest);
		Date fromdate = null;
		int paymenttype = 0;
		Date reportdate = new Date();
		String reportby = themeDisplay.getUser().getEmailAddress();
		String financialaccountant = StringPool.BLANK;
		int markasdeleted = 0;
		String division = businessUtils.getDivision(actionRequest);
		String debitnotenumber = StringPool.BLANK;

		action.doTempDebitNote(documentName, documentYear, corporationid, debitnotenumber, division,
				financialaccountant, fromdate, markasdeleted, organization, paymenttype, reportby, reportdate, todate,
				totalpayment, transactionid, themeDisplay.getUser(), uploadPortletRequest, actionRequest);
	}

}
