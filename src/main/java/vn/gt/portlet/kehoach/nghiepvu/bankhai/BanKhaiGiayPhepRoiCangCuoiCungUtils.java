package vn.gt.portlet.kehoach.nghiepvu.bankhai;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.liferay.core.ActionRequest;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.core.ServiceContext;

import com.fds.nsw.liferay.core.ThemeDisplay;
import com.fds.nsw.liferay.core.PortalUtil;

import vn.gt.constant.Constants;
import com.fds.nsw.nghiepvu.model.UserPort;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGTFunctionTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.InterfaceRequest;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;


import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultCompletion;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import com.fds.nsw.nghiepvu.model.TempDebitnote;

import vn.gt.dao.result.service.ResultCompetionLocalServiceUtil;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.portlet.kehoach.tt1.TT1JSONProvider;
import vn.gt.portlet.kehoach.tt1.TT1XuLyNghiepVuUtils;
import vn.gt.portlet.kehoach.tt11.TT11TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt2.TT2TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt5.TT5TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt7.TT7TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt9.TT9TichHopMessageUtils;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.portlet.kehoach.utils.PhieuThanhToanUtils;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiQuaCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiXuatCanh;
import vn.gt.tichhop.message.giaothong2haiquan.PortClearance2Xml;
import vn.gt.tichhop.report.FileUploadUtils;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;
import vn.nsw.model.PortClearance;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BanKhaiGiayPhepRoiCangCuoiCungUtils {


	private String realPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();

	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"), realPath.lastIndexOf("/WEB-INF"))
			.replaceFirst(":", "") + "/export/";
	
	

	public static JSONObject buildThanhPhan(ThemeDisplay themeDisplay, long documentName, int documentYear) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		String portCleaneNo = StringPool.BLANK;
		
		// Bản khai chung -- > thông báo
		
		TempGeneralDeclaration tempGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		
		if (Validator.isNotNull(tempGeneralDeclaration)) {
			portCleaneNo = tempGeneralDeclaration.getPortClearanceNo();
		} else {
			
			TempNoticeShipMessage tempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil.getThongBaoLastByDocumentNameAndDocumentYear(documentName, documentYear);
			
			if (Validator.isNotNull(tempNoTiceShipMessage)) {
				portCleaneNo = tempNoTiceShipMessage.getPortClearanceNo();
			}
		}
				
		IssuePortClearance issuePortClearance = IssuePortClearanceLocalServiceUtil.findLatestCertificateOfIssuePortClearance(null, null, portCleaneNo);
		if (Validator.isNotNull(portCleaneNo) && Validator.isNotNull(issuePortClearance)) {
			result.put("code", "NC_2018");
			result.put("name", LanguageUtil.get(themeDisplay.getLocale(), "giay-phep-roi-cang-cuoi-cung"));
			boolean available = true;
			String requestCode = StringPool.BLANK;

			result.put("available", available);
			result.put("documentName", documentName);
			result.put("documentYear", documentYear);
			result.put("messageType", MessageType.GIAY_PHEP_ROI_CANG_CUOI_CUNG);
			result.put("requestCode", requestCode);

			result.put("state", 1);
			return result;
		} else {
			result.put("code", "NC_2018");
			result.put("name", LanguageUtil.get(themeDisplay.getLocale(), "giay-phep-roi-cang-cuoi-cung"));
			result.put("available", false);
			result.put("documentName", documentName);
			result.put("documentYear", documentYear);
			result.put("messageType", MessageType.GIAY_PHEP_ROI_CANG_CUOI_CUNG);
			return result;
		}
	}

	public static JSONArray getFileThanhPhanVersionList(ThemeDisplay themeDisplay, long documentName, int documentYear,
			int messageType) {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		try {
			JSONObject object;
			
			String portCleaneNo = StringPool.BLANK;
			
			// Bản khai chung -- > thông báo
			
			TempGeneralDeclaration tempGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
			
			if (Validator.isNotNull(tempGeneralDeclaration)) {
				portCleaneNo = tempGeneralDeclaration.getPortClearanceNo();
			} else {
				
				TempNoticeShipMessage tempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil.getThongBaoLastByDocumentNameAndDocumentYear(documentName, documentYear);
				
				if (Validator.isNotNull(tempNoTiceShipMessage)) {
					portCleaneNo = tempNoTiceShipMessage.getPortClearanceNo();
				}
			}
			IssuePortClearance issuePortClearance = IssuePortClearanceLocalServiceUtil.findLatestCertificateOfIssuePortClearance(null, null, portCleaneNo);
			if (Validator.isNotNull(portCleaneNo) && Validator.isNotNull(issuePortClearance)) {
				
				object = JSONFactoryUtil.createJSONObject();

				String phienBan = issuePortClearance.getVersionNo() + " - " + DmGTFunctionTypeLocalServiceUtil
						.findNameByFunctionTypeCode("0" + issuePortClearance.getRequestState());
				object.put("version", phienBan);
				object.put("status", DmGtStatusLocalServiceUtil
						.findNameByStatusCode(issuePortClearance.getRequestState(), messageType));
				object.put("sendDate", issuePortClearance.getIssueDate());

				object.put("requestCode", issuePortClearance.getRequestCode());

				result.put(object);
			} else {
				result.put(JSONFactoryUtil.createJSONObject());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

}
