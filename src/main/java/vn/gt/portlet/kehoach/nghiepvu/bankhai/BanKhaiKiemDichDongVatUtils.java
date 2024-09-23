package vn.gt.portlet.kehoach.nghiepvu.bankhai;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.core.ThemeDisplay;

import vn.gt.dao.danhmucgt.service.DmGTFunctionTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.InterfaceRequest;

import com.fds.nsw.nghiepvu.model.TempAnimalQuarantine;
import com.fds.nsw.nghiepvu.model.TempCargoDeclaration;
import com.fds.nsw.nghiepvu.model.TempPlantQuarantine;
import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
import com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration;

import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempAnimalQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.portlet.kehoach.tt1.TT1JSONProvider;
import vn.gt.portlet.kehoach.tt1.TT1XuLyNghiepVuUtils;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BanKhaiKiemDichDongVatUtils {


	

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

		TempAnimalQuarantine results = TempAnimalQuarantineLocalServiceUtil
				.getLastByDocumentNameAndDocumentYear(documentName, documentYear);

		result.put("code", "NC_14");
		result.put("name", LanguageUtil.get(themeDisplay.getLocale(), "ban-khai-kiem-dich-dong-vat"));
		boolean available = false;
		String requestCode = StringPool.BLANK;
		if (Validator.isNotNull(results)) {
			available = true;
			requestCode = results.getRequestCode();
		}
		result.put("available", available);
		result.put("documentName", documentName);
		result.put("documentYear", documentYear);
		result.put("messageType", MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT);
		result.put("requestCode", requestCode);

		String icon = checkDisplayIcon(documentName, documentYear, MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT);
		
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

		List<TempAnimalQuarantine> results = TempAnimalQuarantineLocalServiceUtil
				.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName, documentYear);

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

				object.put("remarks", interfaceRequest.getRemarks());
				result.put(object);
			}

		}
		return result;
	}

	public int _banKhaiKiemDichDongVat(String requestCode, long documentName, int documentYear, int messageType,
			int desStatus, User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {

		int result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;
		
		try {
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			List<TempAnimalQuarantine> results = TempAnimalQuarantineLocalServiceUtil.findByRequestCode(requestCode);
			if (results != null && results.size() > 0) {
				TempAnimalQuarantine tempPlantQuarantine = results.get(0);

				if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI) {
					interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(user.getEmailAddress()));
				} else if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_TU_CHOI_BAN_KHAI) {
					interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), "Tu Choi"));
				} else if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_KHAI_HUY) {
					interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), "Huy"));
				}
				tempPlantQuarantine.setRequestState(desStatus);
				TempAnimalQuarantineLocalServiceUtil.updateTempAnimalQuarantine(tempPlantQuarantine);
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
