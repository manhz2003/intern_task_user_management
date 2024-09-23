package vn.gt.portlet.kehoach.nghiepvu.bankhai;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import vn.gt.dao.danhmucgt.service.DmGTFunctionTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.portlet.kehoach.tt1.TT1JSONProvider;
import vn.gt.tichhop.message.MessageType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BanKhaiGiayPhepQuaCangCuoiCungUtils {


	private String realPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();

	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"), realPath.lastIndexOf("/WEB-INF"))
			.replaceFirst(":", "") + "/export/";
	
	

	public static JSONObject buildThanhPhan(ThemeDisplay themeDisplay, long documentName, int documentYear) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		TempNoticeShipMessage tempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil.getThongBaoLastByDocumentNameAndDocumentYear(documentName, documentYear);
		
		if (Validator.isNotNull(tempNoTiceShipMessage) && Validator.isNotNull(tempNoTiceShipMessage.getPortClearanceNo())) {
			result.put("code", "NC_2018");
			result.put("name", LanguageUtil.get(themeDisplay.getLocale(), "giay-phep-qua-cang-cuoi-cung"));
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
			return null;
		}
		
	}

	public static JSONArray getFileThanhPhanVersionList(ThemeDisplay themeDisplay, long documentName, int documentYear,
			int messageType) {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		try {
			JSONObject object;

			TempNoticeShipMessage tempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil.getThongBaoLastByDocumentNameAndDocumentYear(documentName, documentYear);
			
			IssuePermissionForTransit issuePortClearance = IssuePermissionForTransitLocalServiceUtil.findByF_LAST_numberPortClearance(documentName, documentYear, tempNoTiceShipMessage.getPortClearanceNo());
			
			object = JSONFactoryUtil.createJSONObject();

			String phienBan = issuePortClearance.getVersionNo() + " - " + DmGTFunctionTypeLocalServiceUtil
					.findNameByFunctionTypeCode("0" + issuePortClearance.getRequestState());
			object.put("version", phienBan);
			object.put("status", DmGtStatusLocalServiceUtil
					.findNameByStatusCode(issuePortClearance.getRequestState(), messageType));
			object.put("sendDate", issuePortClearance.getApprovalDate());

			object.put("requestCode", issuePortClearance.getRequestCode());

			result.put(object);
		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

}
