package vn.gt.portlet.phuongtien;

import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaScheduleSecurity;


import vn.gt.dao.noticeandmessage.service.VmaScheduleSecurityLocalServiceUtil;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.FormatData;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaScheduleSecurityUtils
 {
	

	public static JSONObject viewPDF(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			HttpServletRequest request = resourceRequest;
			long vmaScheduleSecurityId = ParamUtil.getLong(request,
					"vmaScheduleSecurityId");
			int exportType = ParamUtil.getInteger(request, "exportType");
			VmaScheduleSecurity vmaScheduleSecurity = VmaScheduleSecurityLocalServiceUtil
					.getVmaScheduleSecurity(vmaScheduleSecurityId);

			// save file
			long nanoTime = ReportsBusinessUtils.actionExport(
					vmaScheduleSecurity, exportType);

			String tenFileExport = nanoTime
					+ "_"
					+ (exportType == 1 ? ReportConstant.VMA_SCHEDULE_SECURITY_EXPORT
							: ReportConstant.VMA_SCHEDULE_EVACUATE_EXPORT);

			String urlFile = request.getContextPath() + "/export/"
					+ tenFileExport;

			result.put("url", urlFile);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaScheduleSecurityId = ParamUtil.getLong(request,
				"vmaScheduleSecurityId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaScheduleSecurity vmaScheduleSecurity = VmaScheduleSecurityLocalServiceUtil
					.getVmaScheduleSecurity(vmaScheduleSecurityId);
			result = VMAUtils.object2Json(vmaScheduleSecurity,
					VmaScheduleSecurity.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo portofAuthority nameOfShip documentName documentYear
	 * noticeShipType securityOfficeCode securityCompanyName securityOfficialNo
	 * securityDate securityPlace securityReason evacuated evacuateOfficialCode
	 * evacuateCompanyName evacuateOfficialNo evacuateDate evacuateReason
	 * modifiedDate
	 */
	public static VmaScheduleSecurity getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;

		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleSecurityId"), -1);

		VmaScheduleSecurity vmaScheduleSecurity = null;

		if (id > 0) {
			try {
				vmaScheduleSecurity = VmaScheduleSecurityLocalServiceUtil
						.getVmaScheduleSecurity(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleSecurity = new VmaScheduleSecurity();
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleSecurity.setItineraryNo(itineraryNo);
		}
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleSecurity.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleSecurity.setNameOfShip(nameOfShip);
		}
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		if (documentName >= 0) {
			vmaScheduleSecurity.setDocumentName(documentName);
		}
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		if (documentYear >= 0) {
			vmaScheduleSecurity.setDocumentYear(documentYear);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaScheduleSecurity.setNoticeShipType(noticeShipType);
		}
		String securityOfficeCode = ParamUtil.getString(actionRequest,
				"securityOfficeCode", StringPool.BLANK);
		if (Validator.isNotNull(securityOfficeCode)) {
			vmaScheduleSecurity.setSecurityOfficeCode(securityOfficeCode);
		}
		String securityCompanyName = VMAUtils.getString(actionRequest,
				"securityCompanyName", StringPool.BLANK);
		if (Validator.isNotNull(securityCompanyName)) {
			vmaScheduleSecurity.setSecurityCompanyName(securityCompanyName);
		}
		String securityOfficialNo = VMAUtils.getString(actionRequest,
				"securityOfficialNo", StringPool.BLANK);
		if (Validator.isNotNull(securityOfficialNo)) {
			vmaScheduleSecurity.setSecurityOfficialNo(securityOfficialNo);
		}
		String securityDate = ParamUtil.getString(actionRequest,
				"securityDate", StringPool.BLANK);
		if (Validator.isNotNull(securityDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(securityDate);
				vmaScheduleSecurity.setSecurityDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String securityPlace = VMAUtils.getString(actionRequest,
				"securityPlace", StringPool.BLANK);
		if (Validator.isNotNull(securityPlace)) {
			vmaScheduleSecurity.setSecurityPlace(securityPlace);
		}
		String securityReason = VMAUtils.getString(actionRequest,
				"securityReason", StringPool.BLANK);
		if (Validator.isNotNull(securityReason)) {
			vmaScheduleSecurity.setSecurityReason(securityReason);
		}
		int evacuated = GetterUtil.getInteger(
				request.getParameter("evacuated"), -1);
		if (evacuated >= 0) {
			vmaScheduleSecurity.setEvacuated(evacuated);
		}
		String evacuateOfficialCode = ParamUtil.getString(actionRequest,
				"evacuateOfficialCode", StringPool.BLANK);
		if (Validator.isNotNull(evacuateOfficialCode)) {
			vmaScheduleSecurity.setEvacuateOfficialCode(evacuateOfficialCode);
		}
		String evacuateCompanyName = VMAUtils.getString(actionRequest,
				"evacuateCompanyName", StringPool.BLANK);
		if (Validator.isNotNull(evacuateCompanyName)) {
			vmaScheduleSecurity.setEvacuateCompanyName(evacuateCompanyName);
		}
		String evacuateOfficialNo = VMAUtils.getString(actionRequest,
				"evacuateOfficialNo", StringPool.BLANK);
		if (Validator.isNotNull(evacuateOfficialNo)) {
			vmaScheduleSecurity.setEvacuateOfficialNo(evacuateOfficialNo);
		}
		String evacuateDate = ParamUtil.getString(actionRequest,
				"evacuateDate", StringPool.BLANK);
		if (Validator.isNotNull(evacuateDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(evacuateDate);
				vmaScheduleSecurity.setEvacuateDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String evacuateReason = VMAUtils.getString(actionRequest,
				"evacuateReason", StringPool.BLANK);
		if (Validator.isNotNull(evacuateReason)) {
			vmaScheduleSecurity.setEvacuateReason(evacuateReason);
		}
		/*String modifiedDate = ParamUtil.getString(actionRequest,
				"modifiedDate", StringPool.BLANK);
		if (Validator.isNotNull(modifiedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(modifiedDate);
				vmaScheduleSecurity.setModifiedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}*/
		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaScheduleSecurity.setShipOwnerCode(shipOwnerCode);
		}
		String shipOwnersName = VMAUtils.getString(actionRequest,
				"shipOwnersName", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnersName)) {
			vmaScheduleSecurity.setShipOwnersName(shipOwnersName);
		}
		
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaScheduleSecurity.setShipOperatorCode(shipOperatorCode);
		}
		String shipOperatorName = VMAUtils.getString(actionRequest,
				"shipOperatorName", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorName)) {
			vmaScheduleSecurity.setShipOperatorName(shipOperatorName);
		}
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaScheduleSecurity.setShipAgencyCode(shipAgencyCode);
		}
		String shipAgencyName = VMAUtils.getString(actionRequest,
				"shipAgencyName", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyName)) {
			vmaScheduleSecurity.setShipAgencyName(shipAgencyName);
		}
		String shipAgencyAddress = VMAUtils.getString(actionRequest,
				"shipAgencyAddress", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyAddress)) {
			vmaScheduleSecurity.setShipAgencyAddress(shipAgencyAddress);
		}
		String shipAgencyContactEmail = ParamUtil.getString(actionRequest,
				"shipAgencyContactEmail", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyContactEmail)) {
			vmaScheduleSecurity
					.setShipAgencyContactEmail(shipAgencyContactEmail);
		}
		String shipAgencyPhone = ParamUtil.getString(actionRequest,
				"shipAgencyPhone", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyPhone)) {
			vmaScheduleSecurity.setShipAgencyPhone(shipAgencyPhone);
		}
		String shipAgencyFax = ParamUtil.getString(actionRequest,
				"shipAgencyFax", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyFax)) {
			vmaScheduleSecurity.setShipAgencyFax(shipAgencyFax);
		}
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaScheduleSecurity.setSecurityLevelCode(securityLevelCode);
		}
		
		String departmentCode = VMAUtils.getString(actionRequest,
				"departmentCode", StringPool.BLANK);
		if (Validator.isNotNull(departmentCode)) {
			vmaScheduleSecurity.setDepartmentCode(departmentCode);
		}
		
		String departmentName = VMAUtils.getString(actionRequest,
				"departmentName", StringPool.BLANK);
		if (Validator.isNotNull(departmentName)) {
			vmaScheduleSecurity.setDepartmentName(departmentName);
		}
		
		String representative = VMAUtils.getString(actionRequest,
				"representative", StringPool.BLANK);
		if (Validator.isNotNull(representative)) {
			vmaScheduleSecurity.setRepresentative(representative);
		}
		int digitalAttachedFile = GetterUtil.getInteger(
				request.getParameter("digitalAttachedFile"), -1);
		if (digitalAttachedFile >= 0) {
			vmaScheduleSecurity.setDigitalAttachedFile(digitalAttachedFile);
		}
		String signName = VMAUtils.getString(actionRequest, "signName",
				StringPool.BLANK);
		if (Validator.isNotNull(signName)) {
			vmaScheduleSecurity.setSignName(signName);
		}
		String signTitle = VMAUtils.getString(actionRequest, "signTitle",
				StringPool.BLANK);
		if (Validator.isNotNull(signTitle)) {
			vmaScheduleSecurity.setSignTitle(signTitle);
		}
		String signDate = ParamUtil.getString(actionRequest, "signDate",
				StringPool.BLANK);
		if (Validator.isNotNull(signDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(signDate);
				vmaScheduleSecurity.setSignDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String signPlace = VMAUtils.getString(actionRequest, "signPlace",
				StringPool.BLANK);
		if (Validator.isNotNull(signPlace)) {
			vmaScheduleSecurity.setSignPlace(signPlace);
		}
		long attachedFile = GetterUtil.getLong(
				request.getParameter("attachedFile"), -1);
		if (attachedFile >= 0) {
			vmaScheduleSecurity.setAttachedFile(attachedFile);
		}
		int stampStatus = GetterUtil.getInteger(
				request.getParameter("stampStatus"), -1);
		if (stampStatus >= 0) {
			vmaScheduleSecurity.setStampStatus(stampStatus);
		}
		
		
		return vmaScheduleSecurity;
	}

	public static JSONObject addVmaScheduleSecurity(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaScheduleSecurity vmaScheduleSecurity = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleSecurity == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleSecurity = VmaScheduleSecurityLocalServiceUtil
					.addVmaScheduleSecurity(vmaScheduleSecurity);
			result = VMAUtils.object2Json(vmaScheduleSecurity,
					VmaScheduleSecurity.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleSecurity(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleSecurity vmaScheduleSecurity = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleSecurity == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleSecurity = VmaScheduleSecurityLocalServiceUtil
					.updateVmaScheduleSecurity(vmaScheduleSecurity);
			result = VMAUtils.object2Json(vmaScheduleSecurity,
					VmaScheduleSecurity.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleSecurity(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaScheduleSecurityId");
		if (id > 0) {
			try {
				VmaScheduleSecurityLocalServiceUtil
						.deleteVmaScheduleSecurity(id);
				return VMAUtils.createResponseMessage(
						LanguageUtil.get(themeDisplay.getLocale(), "success"),
						"", StringPool.BLANK);
			} catch (Exception e) {
				return VMAUtils.createResponseMessage(LanguageUtil.get(
						themeDisplay.getLocale(), "system_error"),
						"system_error", StringPool.BLANK);
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
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String securityOfficeCode = ParamUtil.getString(resourceRequest,
				"securityOfficeCode", StringPool.BLANK);
		String securityCompanyName = VMAUtils.getString(resourceRequest,
				"securityCompanyName", StringPool.BLANK);
		String securityOfficialNo = VMAUtils.getString(resourceRequest,
				"securityOfficialNo", StringPool.BLANK);
		String securityDate = ParamUtil.getString(resourceRequest,
				"securityDate", StringPool.BLANK);
		String securityPlace = ParamUtil.getString(resourceRequest,
				"securityPlace", StringPool.BLANK);
		String securityReason = VMAUtils.getString(resourceRequest,
				"securityReason", StringPool.BLANK);
		int evacuated = GetterUtil.getInteger(
				request.getParameter("evacuated"), -1);
		String evacuateOfficialCode = ParamUtil.getString(resourceRequest,
				"evacuateOfficialCode", StringPool.BLANK);
		String evacuateCompanyName = VMAUtils.getString(resourceRequest,
				"evacuateCompanyName", StringPool.BLANK);
		String evacuateOfficialNo = VMAUtils.getString(resourceRequest,
				"evacuateOfficialNo", StringPool.BLANK);
		String evacuateDate = ParamUtil.getString(resourceRequest,
				"evacuateDate", StringPool.BLANK);
		String evacuateReason = VMAUtils.getString(resourceRequest,
				"evacuateReason", StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		try {
			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null,
					securityOfficeCode, securityCompanyName,
					securityOfficialNo, securityDate, securityPlace,
					securityReason, evacuated >= 0 ? evacuated : null,
					evacuateOfficialCode, evacuateCompanyName,
					evacuateOfficialNo, evacuateDate, evacuateReason,
					timeOfArrival, timeOfDeparture);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null,
					securityOfficeCode, securityCompanyName,
					securityOfficialNo, securityDate, securityPlace,
					securityReason, evacuated >= 0 ? evacuated : null,
					evacuateOfficialCode, evacuateCompanyName,
					evacuateOfficialNo, evacuateDate, evacuateReason,
					timeOfArrival, timeOfDeparture);

			return VmaScheduleSecurityLocalServiceUtil.findVmaScheduleSecurity(
					searchQuery, countQuery, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

	public static void doExport(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String securityOfficeCode = ParamUtil.getString(resourceRequest,
				"securityOfficeCode", StringPool.BLANK);
		String securityCompanyName = VMAUtils.getString(resourceRequest,
				"securityCompanyName", StringPool.BLANK);
		String securityOfficialNo = VMAUtils.getString(resourceRequest,
				"securityOfficialNo", StringPool.BLANK);
		String securityDate = ParamUtil.getString(resourceRequest,
				"securityDate", StringPool.BLANK);
		String securityPlace = VMAUtils.getString(resourceRequest,
				"securityPlace", StringPool.BLANK);
		String securityReason = ParamUtil.getString(resourceRequest,
				"securityReason", StringPool.BLANK);
		int evacuated = GetterUtil.getInteger(
				request.getParameter("evacuated"), -1);
		String evacuateOfficialCode = ParamUtil.getString(resourceRequest,
				"evacuateOfficialCode", StringPool.BLANK);
		String evacuateCompanyName = VMAUtils.getString(resourceRequest,
				"evacuateCompanyName", StringPool.BLANK);
		String evacuateOfficialNo = VMAUtils.getString(resourceRequest,
				"evacuateOfficialNo", StringPool.BLANK);
		String evacuateDate = ParamUtil.getString(resourceRequest,
				"evacuateDate", StringPool.BLANK);
		String evacuateReason = VMAUtils.getString(resourceRequest,
				"evacuateReason", StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);

		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);
		/*
		 * String[] headers = new String[] { "STT", "Tên tàu", "Thời gian đến",
		 * "Thời gian đi", "Ngày bắt giữ", "Ngày giải tỏa", "Lý do bắt giữ",
		 * "Cơ quan ra quyết định bắt giữ", "Địa điểm bắt dữ",
		 * "Cơ quan ra quyết định giải tỏa", "SQĐ bắt giữ", "SQĐ giải tỏa" };
		 */

		String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
				"Th\u1EDDi gian \u0111\u1EBFn", "Th\u1EDDi gian \u0111i",
				"Ng\u00E0y b\u1EAFt gi\u1EEF", "Ng\u00E0y gi\u1EA3i t\u1ECFa",
				"L\u00FD do b\u1EAFt gi\u1EEF",
				"C\u01A1 quan ra quy\u1EBFt \u0111\u1ECBnh b\u1EAFt gi\u1EEF",
				"\u0110\u1ECBa \u0111i\u1EC3m b\u1EAFt d\u1EEF",
				"C\u01A1 quan ra quy\u1EBFt \u0111\u1ECBnh gi\u1EA3i t\u1ECFa",
				"SQ\u0110 b\u1EAFt gi\u1EEF", "SQ\u0110 gi\u1EA3i t\u1ECFa" };

		String[][] exportData = null;

		try {

			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null,
					securityOfficeCode, securityCompanyName,
					securityOfficialNo, securityDate, securityPlace,
					securityReason, evacuated >= 0 ? evacuated : null,
					evacuateOfficialCode, evacuateCompanyName,
					evacuateOfficialNo, evacuateDate, evacuateReason,
					timeOfArrival, timeOfDeparture);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null,
					securityOfficeCode, securityCompanyName,
					securityOfficialNo, securityDate, securityPlace,
					securityReason, evacuated >= 0 ? evacuated : null,
					evacuateOfficialCode, evacuateCompanyName,
					evacuateOfficialNo, evacuateDate, evacuateReason,
					timeOfArrival, timeOfDeparture);

			JSONObject objects = VmaScheduleSecurityLocalServiceUtil
					.findVmaScheduleSecurity(searchQuery, countQuery, -1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");

					timeOfArrival = object.getString("timeOfArrival");

					timeOfDeparture = object.getString("timeOfDeparture");

					securityDate = object.getString("securityDate");

					evacuateDate = object.getString("evacuateDate");

					securityReason = object.getString("securityReason");

					securityCompanyName = object
							.getString("securityCompanyName");

					securityPlace = object.getString("securityPlace");

					evacuateCompanyName = object
							.getString("evacuateCompanyName");

					securityOfficialNo = object.getString("securityOfficialNo");

					evacuateOfficialNo = object.getString("evacuateOfficialNo");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = timeOfArrival;
					exportData[i][3] = timeOfDeparture;
					exportData[i][4] = securityDate;
					exportData[i][5] = evacuateDate;

					exportData[i][6] = securityReason;
					exportData[i][7] = securityCompanyName;
					exportData[i][8] = securityPlace;

					exportData[i][9] = evacuateCompanyName;
					exportData[i][10] = securityOfficialNo;
					exportData[i][11] = evacuateOfficialNo;
				}
			}

			String sheetName = "VMA-Schedule-Security";

			String fileName = sheetName + "-" + System.currentTimeMillis();

			String filePath = VMAUtils.doExportExcelFile(request,
					resourceResponse, sheetName, fileName, headers, exportData);

		//todo response file in controller
//		resourceResponse
//				.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//		resourceResponse.setProperty("Content-Disposition",
//				"attachment; filename= " + fileName + ".xls");
//		resourceResponse.setCharacterEncoding("UTF-8");
//		// resourceResponse.setProperty("filename", fileName + ".xls");
//		resourceResponse.addProperty("FILE-NAME", fileName + ".xls");
//
//		resourceResponse.getPortletOutputStream().write(
//				VMAUtils.readFileToByteArray(new File(filePath)));

		} catch (Exception e) {
			log.error(e.getMessage());

		}
	}

	public static long doCount(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String securityOfficeCode = ParamUtil.getString(resourceRequest,
				"securityOfficeCode", StringPool.BLANK);
		String securityCompanyName = VMAUtils.getString(resourceRequest,
				"securityCompanyName", StringPool.BLANK);
		String securityOfficialNo = VMAUtils.getString(resourceRequest,
				"securityOfficialNo", StringPool.BLANK);
		String securityDate = ParamUtil.getString(resourceRequest,
				"securityDate", StringPool.BLANK);
		String securityPlace = VMAUtils.getString(resourceRequest,
				"securityPlace", StringPool.BLANK);
		String securityReason = VMAUtils.getString(resourceRequest,
				"securityReason", StringPool.BLANK);
		int evacuated = GetterUtil.getInteger(
				request.getParameter("evacuated"), -1);
		String evacuateOfficialCode = ParamUtil.getString(resourceRequest,
				"evacuateOfficialCode", StringPool.BLANK);
		String evacuateCompanyName = VMAUtils.getString(resourceRequest,
				"evacuateCompanyName", StringPool.BLANK);
		String evacuateOfficialNo = VMAUtils.getString(resourceRequest,
				"evacuateOfficialNo", StringPool.BLANK);
		String evacuateDate = ParamUtil.getString(resourceRequest,
				"evacuateDate", StringPool.BLANK);
		String evacuateReason = VMAUtils.getString(resourceRequest,
				"evacuateReason", StringPool.BLANK);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);

		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		try {
			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null,
					securityOfficeCode, securityCompanyName,
					securityOfficialNo, securityDate, securityPlace,
					securityReason, evacuated >= 0 ? evacuated : null,
					evacuateOfficialCode, evacuateCompanyName,
					evacuateOfficialNo, evacuateDate, evacuateReason,
					timeOfArrival, timeOfDeparture);

			return VmaScheduleSecurityLocalServiceUtil
					.countVmaScheduleSecurity(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());

			return 0;
		}
	}

	private static String generateQuery(String cmd, String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType,
			String securityOfficeCode, String securityCompanyName,
			String securityOfficialNo, String securityDate,
			String securityPlace, String securityReason, Integer evacuated,
			String evacuateOfficialCode, String evacuateCompanyName,
			String evacuateOfficialNo, String evacuateDate,
			String evacuateReason, String timeOfArrival, String timeOfDeparture) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_schedule_security as a";
		} else {
			sql = "SELECT a.* FROM vma_schedule_security AS a";
		}

		if (Validator.isNotNull(timeOfArrival)
				|| Validator.isNotNull(timeOfDeparture)) {
			sql += " INNER JOIN vma_itinerary as b ON a.itineraryNo = b.itineraryNo";
			if (Validator.isNotNull(timeOfArrival)) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(timeOfArrival);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					sql += VMAUtils.buildSQLCondition("b.TimeOfArrival", "'"
							+ strDate + " 00:00:00'" + " AND '" + strDate
							+ " 23:59:59'", "AND", StringPool.BETWEEN);
				}
			}

			if (Validator.isNotNull(timeOfDeparture)) {

				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(timeOfDeparture);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					sql += VMAUtils.buildSQLCondition("b.TimeOfDeparture", "'"
							+ strDate + " 00:00:00'" + " AND '" + strDate
							+ " 23:59:59'", "AND", StringPool.BETWEEN);
				}

			}
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("NameOfShip", "'%"
					+ nameOfShip + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (noticeShipType != null) {
			condition.append(VMAUtils.buildSQLCondition("NoticeShipType",
					noticeShipType, "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (documentYear != null) {
			condition.append(VMAUtils
					.buildSQLCondition("DocumentYear", documentYear, "AND",
							StringPool.EQUAL, new String[] { "a" }));
		}

		if (documentName != null) {
			condition.append(VMAUtils
					.buildSQLCondition("DocumentName", documentName, "AND",
							StringPool.EQUAL, new String[] { "a" }));
		}

		if (evacuated != null) {
			condition.append(VMAUtils.buildSQLCondition("Evacuated", evacuated,
					"AND", StringPool.EQUAL, new String[] { "a" }));
		}

		if (Validator.isNotNull(evacuateOfficialCode)) {
			condition.append(VMAUtils.buildSQLCondition("EvacuateOfficialCode",
					"'" + evacuateOfficialCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(evacuateOfficialNo)) {
			condition.append(VMAUtils.buildSQLCondition("EvacuateOfficialNo",
					"'" + evacuateOfficialNo + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(evacuateReason)) {
			condition.append(VMAUtils.buildSQLCondition("EvacuateReason", "'%"
					+ evacuateReason + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(evacuateCompanyName)) {
			condition.append(VMAUtils.buildSQLCondition("EvacuateCompanyName",
					"'%" + evacuateCompanyName + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortofAuthority", "'"
					+ portofAuthority + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(securityOfficeCode)) {
			condition.append(VMAUtils.buildSQLCondition("SecurityOfficeCode",
					"'" + securityOfficeCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(securityCompanyName)) {
			condition.append(VMAUtils.buildSQLCondition("SecurityCompanyName",
					"'%" + securityCompanyName + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(securityOfficialNo)) {
			condition.append(VMAUtils.buildSQLCondition("SecurityOfficialNo",
					"'" + securityOfficialNo + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(securityPlace)) {
			condition.append(VMAUtils.buildSQLCondition("SecurityPlace", "'%"
					+ securityPlace + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(securityReason)) {
			condition.append(VMAUtils.buildSQLCondition("SecurityReason", "'%"
					+ securityReason + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(securityDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(securityDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("SecurityDate", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN,
						new String[] { "a" }));
			}
		}

		if (Validator.isNotNull(evacuateDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(evacuateDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("EvacuateDate", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL));
		}

		return sql + condition.toString();
	}
}
