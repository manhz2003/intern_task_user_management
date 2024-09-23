package vn.gt.portlet.phuongtien;

import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaScheduleLaunching;


import vn.gt.dao.noticeandmessage.service.VmaScheduleLaunchingLocalServiceUtil;
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
public class VmaScheduleLaunchingUtils
 {
	

	public static JSONObject viewPDF(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			HttpServletRequest request = resourceRequest;
			long vmaScheduleLaunchingId = ParamUtil.getLong(request,
					"vmaScheduleLaunchingId");
			VmaScheduleLaunching vmaScheduleLaunching = VmaScheduleLaunchingLocalServiceUtil
					.getVmaScheduleLaunching(vmaScheduleLaunchingId);
			
			// save file
			long nanoTime = ReportsBusinessUtils
					.actionExport(vmaScheduleLaunching);

			String tenFileExport = nanoTime + "_"
					+ ReportConstant.VMA_SCHEDULE_LAUNCHING_EXPORT;

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
		long vmaScheduleLaunchingId = ParamUtil.getLong(request,
				"vmaScheduleLaunchingId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaScheduleLaunching vmaScheduleLaunching = VmaScheduleLaunchingLocalServiceUtil
					.getVmaScheduleLaunching(vmaScheduleLaunchingId);
			result = VMAUtils.object2Json(vmaScheduleLaunching,
					VmaScheduleLaunching.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo portofAuthority nameOfShip documentName documentYear
	 * noticeShipType launchingFrom launchingTo launchingPlace launchingReason
	 * modifiedDate
	 */
	public static VmaScheduleLaunching getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleLaunchingId"), -1);
		VmaScheduleLaunching vmaScheduleLaunching = null;
		if (id > 0) {
			try {
				vmaScheduleLaunching = VmaScheduleLaunchingLocalServiceUtil
						.getVmaScheduleLaunching(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleLaunching = new VmaScheduleLaunching();
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleLaunching.setItineraryNo(itineraryNo);
		}
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleLaunching.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleLaunching.setNameOfShip(nameOfShip);
		}
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		if (documentName >= 0) {
			vmaScheduleLaunching.setDocumentName(documentName);
		}
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		if (documentYear >= 0) {
			vmaScheduleLaunching.setDocumentYear(documentYear);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaScheduleLaunching.setNoticeShipType(noticeShipType);
		}
		String launchingFrom = ParamUtil.getString(actionRequest,
				"launchingFrom", StringPool.BLANK);
		if (Validator.isNotNull(launchingFrom)) {
			try {
				Date date = FormatData.formatDateShort3.parse(launchingFrom);
				vmaScheduleLaunching.setLaunchingFrom(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String launchingTo = ParamUtil.getString(actionRequest, "launchingTo",
				StringPool.BLANK);
		if (Validator.isNotNull(launchingTo)) {
			try {
				Date date = FormatData.formatDateShort3.parse(launchingTo);
				vmaScheduleLaunching.setLaunchingTo(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String launchingPlace = VMAUtils.getString(actionRequest,
				"launchingPlace", StringPool.BLANK);
		if (Validator.isNotNull(launchingPlace)) {
			vmaScheduleLaunching.setLaunchingPlace(launchingPlace);
		}
		String launchingReason = VMAUtils.getString(actionRequest,
				"launchingReason", StringPool.BLANK);
		if (Validator.isNotNull(launchingReason)) {
			vmaScheduleLaunching.setLaunchingReason(launchingReason);
		}
		/*String modifiedDate = ParamUtil.getString(actionRequest,
				"modifiedDate", StringPool.BLANK);
		if (Validator.isNotNull(modifiedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(modifiedDate);
				vmaScheduleLaunching.setModifiedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}*/

		String issueDate = ParamUtil.getString(actionRequest, "issueDate",
				StringPool.BLANK);
		if (Validator.isNotNull(issueDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(issueDate);
				vmaScheduleLaunching.setModifiedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String directorOfMaritime = ParamUtil.getString(actionRequest,
				"directorOfMaritime", StringPool.BLANK);
		if (Validator.isNotNull(directorOfMaritime)) {
			vmaScheduleLaunching.setDirectorOfMaritime(directorOfMaritime);
		}
		String certificateNo = ParamUtil.getString(actionRequest,
				"certificateNo", StringPool.BLANK);
		if (Validator.isNotNull(certificateNo)) {
			vmaScheduleLaunching.setCertificateNo(certificateNo);
		}
		int requestState = GetterUtil.getInteger(
				request.getParameter("requestState"), -1);
		if (requestState >= 0) {
			vmaScheduleLaunching.setRequestState(requestState);
		}
		String versionNo = ParamUtil.getString(actionRequest, "versionNo",
				StringPool.BLANK);
		if (Validator.isNotNull(versionNo)) {
			vmaScheduleLaunching.setVersionNo(versionNo);
		}
		int isApproval = GetterUtil.getInteger(
				request.getParameter("isApproval"), -1);
		if (isApproval >= 0) {
			vmaScheduleLaunching.setIsApproval(isApproval);
		}
		String approvalDate = GetterUtil.getString(
				request.getParameter("approvalDate"), StringPool.BLANK);
		if (Validator.isNotNull(approvalDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(approvalDate);
				vmaScheduleLaunching.setApprovalDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String approvalName = ParamUtil.getString(actionRequest,
				"approvalName", StringPool.BLANK);
		if (Validator.isNotNull(approvalName)) {
			vmaScheduleLaunching.setApprovalName(approvalName);
		}
		String remarks = ParamUtil.getString(actionRequest, "remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(remarks)) {
			vmaScheduleLaunching.setRemarks(remarks);
		}
		

		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaScheduleLaunching.setShipOwnerCode(shipOwnerCode);
		}
		String shipOwnersName = VMAUtils.getString(actionRequest,
				"shipOwnersName", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnersName)) {
			vmaScheduleLaunching.setShipOwnersName(shipOwnersName);
		}
		
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaScheduleLaunching.setShipOperatorCode(shipOperatorCode);
		}
		String shipOperatorName = VMAUtils.getString(actionRequest,
				"shipOperatorName", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorName)) {
			vmaScheduleLaunching.setShipOperatorName(shipOperatorName);
		}
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaScheduleLaunching.setShipAgencyCode(shipAgencyCode);
		}
		String shipAgencyName = VMAUtils.getString(actionRequest,
				"shipAgencyName", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyName)) {
			vmaScheduleLaunching.setShipAgencyName(shipAgencyName);
		}
		String shipAgencyAddress = VMAUtils.getString(actionRequest,
				"shipAgencyAddress", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyAddress)) {
			vmaScheduleLaunching.setShipAgencyAddress(shipAgencyAddress);
		}
		String shipAgencyContactEmail = ParamUtil.getString(actionRequest,
				"shipAgencyContactEmail", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyContactEmail)) {
			vmaScheduleLaunching
					.setShipAgencyContactEmail(shipAgencyContactEmail);
		}
		String shipAgencyPhone = ParamUtil.getString(actionRequest,
				"shipAgencyPhone", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyPhone)) {
			vmaScheduleLaunching.setShipAgencyPhone(shipAgencyPhone);
		}
		String shipAgencyFax = ParamUtil.getString(actionRequest,
				"shipAgencyFax", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyFax)) {
			vmaScheduleLaunching.setShipAgencyFax(shipAgencyFax);
		}
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaScheduleLaunching.setSecurityLevelCode(securityLevelCode);
		}
		
		String departmentCode = VMAUtils.getString(actionRequest,
				"departmentCode", StringPool.BLANK);
		if (Validator.isNotNull(departmentCode)) {
			vmaScheduleLaunching.setDepartmentCode(departmentCode);
		}
		
		String departmentName = VMAUtils.getString(actionRequest,
				"departmentName", StringPool.BLANK);
		if (Validator.isNotNull(departmentName)) {
			vmaScheduleLaunching.setDepartmentName(departmentName);
		}
		
				
		int isCancel = GetterUtil.getInteger(request.getParameter("isCancel"),
				-1);
		if (isCancel >= 0) {
			vmaScheduleLaunching.setIsCancel(isCancel);
		}
		String cancelDate = GetterUtil.getString(
				request.getParameter("cancelDate"), StringPool.BLANK);
		if (Validator.isNotNull(cancelDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(cancelDate);
				vmaScheduleLaunching.setCancelDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String cancelName = ParamUtil.getString(actionRequest, "cancelName",
				StringPool.BLANK);
		if (Validator.isNotNull(cancelName)) {
			vmaScheduleLaunching.setCancelName(cancelName);
		}
		String cancelNote = ParamUtil.getString(actionRequest, "cancelNote",
				StringPool.BLANK);
		if (Validator.isNotNull(cancelNote)) {
			vmaScheduleLaunching.setCancelNote(cancelNote);
		}
		String representative = ParamUtil.getString(actionRequest,
				"representative", StringPool.BLANK);
		if (Validator.isNotNull(representative)) {
			vmaScheduleLaunching.setRepresentative(representative);
		}
		
		int digitalAttachedFile = GetterUtil.getInteger(
				request.getParameter("digitalAttachedFile"), -1);
		if (digitalAttachedFile >= 0) {
			vmaScheduleLaunching.setDigitalAttachedFile(digitalAttachedFile);
		}
		String signName = VMAUtils.getString(actionRequest, "signName",
				StringPool.BLANK);
		if (Validator.isNotNull(signName)) {
			vmaScheduleLaunching.setSignName(signName);
		}
		String signTitle = VMAUtils.getString(actionRequest, "signTitle",
				StringPool.BLANK);
		if (Validator.isNotNull(signTitle)) {
			vmaScheduleLaunching.setSignTitle(signTitle);
		}
		String signDate = ParamUtil.getString(actionRequest, "signDate",
				StringPool.BLANK);
		if (Validator.isNotNull(signDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(signDate);
				vmaScheduleLaunching.setSignDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String signPlace = VMAUtils.getString(actionRequest, "signPlace",
				StringPool.BLANK);
		if (Validator.isNotNull(signPlace)) {
			vmaScheduleLaunching.setSignPlace(signPlace);
		}
		long attachedFile = GetterUtil.getLong(
				request.getParameter("attachedFile"), -1);
		if (attachedFile >= 0) {
			vmaScheduleLaunching.setAttachedFile(attachedFile);
		}
		int stampStatus = GetterUtil.getInteger(
				request.getParameter("stampStatus"), -1);
		if (stampStatus >= 0) {
			vmaScheduleLaunching.setStampStatus(stampStatus);
		}
		
		return vmaScheduleLaunching;
	}

	public static JSONObject addVmaScheduleLaunching(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleLaunching vmaScheduleLaunching = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleLaunching == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleLaunching = VmaScheduleLaunchingLocalServiceUtil
					.addVmaScheduleLaunching(vmaScheduleLaunching);
			result = VMAUtils.object2Json(vmaScheduleLaunching,
					VmaScheduleLaunching.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleLaunching(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleLaunching vmaScheduleLaunching = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleLaunching == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleLaunching = VmaScheduleLaunchingLocalServiceUtil
					.updateVmaScheduleLaunching(vmaScheduleLaunching);
			result = VMAUtils.object2Json(vmaScheduleLaunching,
					VmaScheduleLaunching.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleLaunching(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaScheduleLaunchingId");
		if (id > 0) {
			try {
				VmaScheduleLaunchingLocalServiceUtil
						.deleteVmaScheduleLaunching(id);
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
		String launchingFrom = ParamUtil.getString(resourceRequest,
				"launchingFrom", StringPool.BLANK);
		String launchingTo = ParamUtil.getString(resourceRequest,
				"launchingTo", StringPool.BLANK);
		String launchingPlace = ParamUtil.getString(resourceRequest,
				"launchingPlace", StringPool.BLANK);
		String launchingReason = ParamUtil.getString(resourceRequest,
				"launchingReason", StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		try {
			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, launchingFrom,
					launchingTo, launchingPlace, launchingReason,
					timeOfArrival, timeOfDeparture);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, launchingFrom,
					launchingTo, launchingPlace, launchingReason,
					timeOfArrival, timeOfDeparture);

			return VmaScheduleLaunchingLocalServiceUtil
					.findVmaScheduleLaunching(searchQuery, countQuery, start,
							end);
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
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
		String launchingFrom = ParamUtil.getString(resourceRequest,
				"launchingFrom", StringPool.BLANK);
		String launchingTo = ParamUtil.getString(resourceRequest,
				"launchingTo", StringPool.BLANK);
		String launchingPlace = ParamUtil.getString(resourceRequest,
				"launchingPlace", StringPool.BLANK);
		String launchingReason = ParamUtil.getString(resourceRequest,
				"launchingReason", StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);
		try {
			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, launchingFrom,
					launchingTo, launchingPlace, launchingReason,
					timeOfArrival, timeOfDeparture);

			return VmaScheduleLaunchingLocalServiceUtil
					.countVmaScheduleLaunching(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());

			return 0;
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
		String launchingFrom = ParamUtil.getString(resourceRequest,
				"launchingFrom", StringPool.BLANK);
		String launchingTo = ParamUtil.getString(resourceRequest,
				"launchingTo", StringPool.BLANK);
		String launchingPlace = ParamUtil.getString(resourceRequest,
				"launchingPlace", StringPool.BLANK);
		String launchingReason = ParamUtil.getString(resourceRequest,
				"launchingReason", StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);
		/*
		 * String[] headers = new String[] { "STT", "Tên tàu", "Thời gian đến",
		 * "Thời gian đi", "Thời gian bắt đầu thả xuồng",
		 * "Thời gian kết thúc thả xuồng", "Mục đích thả xuồng",
		 * "Khu vực thả xuồng" };
		 */

		String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
				"Th\u1EDDi gian \u0111\u1EBFn", "Th\u1EDDi gian \u0111i",
				"Th\u1EDDi gian b\u1EAFt \u0111\u1EA7u th\u1EA3 xu\u1ED3ng",
				"Th\u1EDDi gian k\u1EBFt th\u00FAc th\u1EA3 xu\u1ED3ng",
				"M\u1EE5c \u0111\u00EDch th\u1EA3 xu\u1ED3ng",
				"Khu v\u1EF1c th\u1EA3 xu\u1ED3ng" };
		String[][] exportData = null;

		try {

			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, launchingFrom,
					launchingTo, launchingPlace, launchingReason,
					timeOfArrival, timeOfDeparture);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, launchingFrom,
					launchingTo, launchingPlace, launchingReason,
					timeOfArrival, timeOfDeparture);

			JSONObject objects = VmaScheduleLaunchingLocalServiceUtil
					.findVmaScheduleLaunching(searchQuery, countQuery, -1, -1);
			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");

					timeOfArrival = object.getString("timeOfArrival");

					timeOfDeparture = object.getString("timeOfDeparture");

					launchingFrom = object.getString("launchingFrom");

					launchingTo = object.getString("launchingTo");

					launchingReason = object.getString("launchingReason");

					launchingPlace = object.getString("launchingPlace");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = timeOfArrival;
					exportData[i][3] = timeOfDeparture;
					exportData[i][4] = launchingFrom;
					exportData[i][5] = launchingTo;

					exportData[i][6] = launchingReason;
					exportData[i][7] = launchingPlace;
				}
			}

			String sheetName = "VMA-Schedule-Launching";

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

	private static String generateQuery(String cmd, String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String launchingFrom,
			String launchingTo, String launchingPlace, String launchingReason,
			String timeOfArrival, String timeOfDeparture) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_schedule_launching as a";
		} else {
			sql = "SELECT a.* FROM vma_schedule_launching AS a";
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
					+ nameOfShip + "%'", "AND", StringPool.LIKE));
		}

		if (noticeShipType != null) {
			condition.append(VMAUtils.buildSQLCondition("NoticeShipType",
					noticeShipType, "AND", StringPool.EQUAL));
		}

		if (documentYear != null) {
			condition.append(VMAUtils.buildSQLCondition("DocumentYear",
					documentYear, "AND", StringPool.EQUAL));
		}

		if (documentName != null) {
			condition.append(VMAUtils.buildSQLCondition("DocumentName",
					documentName, "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortofAuthority", "'"
					+ portofAuthority + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(launchingPlace)) {
			condition.append(VMAUtils.buildSQLCondition("LaunchingPlace", "'%"
					+ launchingPlace + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(launchingReason)) {
			condition.append(VMAUtils.buildSQLCondition("LaunchingReason", "'%"
					+ launchingReason + "%'", "AND", StringPool.LIKE));

		}

		if (Validator.isNotNull(launchingFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(launchingFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("LaunchingFrom",
						"'" + strDate + " 00:00:00'" + " AND '" + strDate
								+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(launchingTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(launchingTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("LaunchingTo", "'"
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
