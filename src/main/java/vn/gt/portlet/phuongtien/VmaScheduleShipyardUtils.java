package vn.gt.portlet.phuongtien;

import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaScheduleShipyard;


import vn.gt.dao.noticeandmessage.service.VmaScheduleShipyardLocalServiceUtil;
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
public class VmaScheduleShipyardUtils
 {
	

	public static JSONObject viewPDF(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			HttpServletRequest request = resourceRequest;
			long vmaScheduleShipyardId = ParamUtil.getLong(request,
					"vmaScheduleShipyardId");
			VmaScheduleShipyard vmaScheduleShipyard = VmaScheduleShipyardLocalServiceUtil
					.getVmaScheduleShipyard(vmaScheduleShipyardId);
			// save file
			long nanoTime = ReportsBusinessUtils
					.actionExport(vmaScheduleShipyard);

			String tenFileExport = nanoTime + "_"
					+ ReportConstant.VMA_SCHEDULE_SHIPYARD_EXPORT;

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
		long vmaScheduleShipyardId = ParamUtil.getLong(request,
				"vmaScheduleShipyardId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaScheduleShipyard vmaScheduleShipyard = VmaScheduleShipyardLocalServiceUtil
					.getVmaScheduleShipyard(vmaScheduleShipyardId);
			result = VMAUtils.object2Json(vmaScheduleShipyard,
					VmaScheduleShipyard.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo portofAuthority nameOfShip documentName documentYear
	 * noticeShipType shipYardCode shipYardCompanyName shipYardOfficialNo
	 * repairingFrom repairingTo repairingPlace repairingReason repaired
	 * modifiedDate
	 */
	public static VmaScheduleShipyard getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleShipyardId"), -1);
		VmaScheduleShipyard vmaScheduleShipyard = null;
		if (id > 0) {
			try {
				vmaScheduleShipyard = VmaScheduleShipyardLocalServiceUtil
						.getVmaScheduleShipyard(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleShipyard = new VmaScheduleShipyard();
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleShipyard.setItineraryNo(itineraryNo);
		}
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleShipyard.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleShipyard.setNameOfShip(nameOfShip);
		}
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		if (documentName >= 0) {
			vmaScheduleShipyard.setDocumentName(documentName);
		}
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		if (documentYear >= 0) {
			vmaScheduleShipyard.setDocumentYear(documentYear);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaScheduleShipyard.setNoticeShipType(noticeShipType);
		}
		String shipYardCode = ParamUtil.getString(actionRequest,
				"shipYardCode", StringPool.BLANK);
		if (Validator.isNotNull(shipYardCode)) {
			vmaScheduleShipyard.setShipYardCode(shipYardCode);
		}
		String shipYardCompanyName = VMAUtils.getString(actionRequest,
				"shipYardCompanyName", StringPool.BLANK);
		if (Validator.isNotNull(shipYardCompanyName)) {
			vmaScheduleShipyard.setShipYardCompanyName(shipYardCompanyName);
		}
		String shipYardOfficialNo = ParamUtil.getString(actionRequest,
				"shipYardOfficialNo", StringPool.BLANK);
		if (Validator.isNotNull(shipYardOfficialNo)) {
			vmaScheduleShipyard.setShipYardOfficialNo(shipYardOfficialNo);
		}
		String repairingFrom = ParamUtil.getString(actionRequest,
				"repairingFrom", StringPool.BLANK);
		if (Validator.isNotNull(repairingFrom)) {
			try {
				Date date = FormatData.formatDateShort3.parse(repairingFrom);
				vmaScheduleShipyard.setRepairingFrom(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String repairingTo = ParamUtil.getString(actionRequest, "repairingTo",
				StringPool.BLANK);
		if (Validator.isNotNull(repairingTo)) {
			try {
				Date date = FormatData.formatDateShort3.parse(repairingTo);
				vmaScheduleShipyard.setRepairingTo(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String repairingPlace = VMAUtils.getString(actionRequest,
				"repairingPlace", StringPool.BLANK);
		if (Validator.isNotNull(repairingPlace)) {
			vmaScheduleShipyard.setRepairingPlace(repairingPlace);
		}
		String repairingReason = VMAUtils.getString(actionRequest,
				"repairingReason", StringPool.BLANK);
		if (Validator.isNotNull(repairingReason)) {
			vmaScheduleShipyard.setRepairingReason(repairingReason);
		}
		int repaired = GetterUtil.getInteger(request.getParameter("repaired"),
				-1);
		if (repaired >= 0) {
			vmaScheduleShipyard.setRepaired(repaired);
		}
		

		String issueDate = ParamUtil.getString(actionRequest, "issueDate",
				StringPool.BLANK);
		if (Validator.isNotNull(issueDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(issueDate);
				vmaScheduleShipyard.setModifiedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String directorOfMaritime = ParamUtil.getString(actionRequest,
				"directorOfMaritime", StringPool.BLANK);
		if (Validator.isNotNull(directorOfMaritime)) {
			vmaScheduleShipyard.setDirectorOfMaritime(directorOfMaritime);
		}
		String certificateNo = ParamUtil.getString(actionRequest,
				"certificateNo", StringPool.BLANK);
		if (Validator.isNotNull(certificateNo)) {
			vmaScheduleShipyard.setCertificateNo(certificateNo);
		}
		int requestState = GetterUtil.getInteger(
				request.getParameter("requestState"), -1);
		if (requestState >= 0) {
			vmaScheduleShipyard.setRequestState(requestState);
		}
		String versionNo = ParamUtil.getString(actionRequest, "versionNo",
				StringPool.BLANK);
		if (Validator.isNotNull(versionNo)) {
			vmaScheduleShipyard.setVersionNo(versionNo);
		}
		int isApproval = GetterUtil.getInteger(
				request.getParameter("isApproval"), -1);
		if (isApproval >= 0) {
			vmaScheduleShipyard.setIsApproval(isApproval);
		}
		String approvalDate = GetterUtil.getString(
				request.getParameter("approvalDate"), StringPool.BLANK);
		if (Validator.isNotNull(approvalDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(approvalDate);
				vmaScheduleShipyard.setApprovalDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String approvalName = ParamUtil.getString(actionRequest,
				"approvalName", StringPool.BLANK);
		if (Validator.isNotNull(approvalName)) {
			vmaScheduleShipyard.setApprovalName(approvalName);
		}
		String remarks = VMAUtils.getString(actionRequest, "remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(remarks)) {
			vmaScheduleShipyard.setRemarks(remarks);
		}
		
		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaScheduleShipyard.setShipOwnerCode(shipOwnerCode);
		}
		String shipOwnersName = VMAUtils.getString(actionRequest,
				"shipOwnersName", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnersName)) {
			vmaScheduleShipyard.setShipOwnersName(shipOwnersName);
		}
		
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaScheduleShipyard.setShipOperatorCode(shipOperatorCode);
		}
		String shipOperatorName = VMAUtils.getString(actionRequest,
				"shipOperatorName", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorName)) {
			vmaScheduleShipyard.setShipOperatorName(shipOperatorName);
		}
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaScheduleShipyard.setShipAgencyCode(shipAgencyCode);
		}
		String shipAgencyName = VMAUtils.getString(actionRequest,
				"shipAgencyName", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyName)) {
			vmaScheduleShipyard.setShipAgencyName(shipAgencyName);
		}
		String shipAgencyAddress = VMAUtils.getString(actionRequest,
				"shipAgencyAddress", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyAddress)) {
			vmaScheduleShipyard.setShipAgencyAddress(shipAgencyAddress);
		}
		String shipAgencyContactEmail = ParamUtil.getString(actionRequest,
				"shipAgencyContactEmail", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyContactEmail)) {
			vmaScheduleShipyard
					.setShipAgencyContactEmail(shipAgencyContactEmail);
		}
		String shipAgencyPhone = ParamUtil.getString(actionRequest,
				"shipAgencyPhone", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyPhone)) {
			vmaScheduleShipyard.setShipAgencyPhone(shipAgencyPhone);
		}
		String shipAgencyFax = ParamUtil.getString(actionRequest,
				"shipAgencyFax", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyFax)) {
			vmaScheduleShipyard.setShipAgencyFax(shipAgencyFax);
		}
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaScheduleShipyard.setSecurityLevelCode(securityLevelCode);
		}
		
		String departmentCode = VMAUtils.getString(actionRequest,
				"departmentCode", StringPool.BLANK);
		if (Validator.isNotNull(departmentCode)) {
			vmaScheduleShipyard.setDepartmentCode(departmentCode);
		}
		
		String departmentName = VMAUtils.getString(actionRequest,
				"departmentName", StringPool.BLANK);
		if (Validator.isNotNull(departmentName)) {
			vmaScheduleShipyard.setDepartmentName(departmentName);
		}
		
		
		int isCancel = GetterUtil.getInteger(request.getParameter("isCancel"),
				-1);
		if (isCancel >= 0) {
			vmaScheduleShipyard.setIsCancel(isCancel);
		}
		String cancelDate = GetterUtil.getString(
				request.getParameter("cancelDate"), StringPool.BLANK);
		if (Validator.isNotNull(cancelDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(cancelDate);
				vmaScheduleShipyard.setCancelDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String cancelName = ParamUtil.getString(actionRequest, "cancelName",
				StringPool.BLANK);
		if (Validator.isNotNull(cancelName)) {
			vmaScheduleShipyard.setCancelName(cancelName);
		}
		String cancelNote = ParamUtil.getString(actionRequest, "cancelNote",
				StringPool.BLANK);
		if (Validator.isNotNull(cancelNote)) {
			vmaScheduleShipyard.setCancelNote(cancelNote);
		}
		String representative = ParamUtil.getString(actionRequest,
				"representative", StringPool.BLANK);
		if (Validator.isNotNull(representative)) {
			vmaScheduleShipyard.setRepresentative(representative);
		}
		
		int digitalAttachedFile = GetterUtil.getInteger(
				request.getParameter("digitalAttachedFile"), -1);
		if (digitalAttachedFile >= 0) {
			vmaScheduleShipyard.setDigitalAttachedFile(digitalAttachedFile);
		}
		String signName = VMAUtils.getString(actionRequest, "signName",
				StringPool.BLANK);
		if (Validator.isNotNull(signName)) {
			vmaScheduleShipyard.setSignName(signName);
		}
		String signTitle = VMAUtils.getString(actionRequest, "signTitle",
				StringPool.BLANK);
		if (Validator.isNotNull(signTitle)) {
			vmaScheduleShipyard.setSignTitle(signTitle);
		}
		String signDate = ParamUtil.getString(actionRequest, "signDate",
				StringPool.BLANK);
		if (Validator.isNotNull(signDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(signDate);
				vmaScheduleShipyard.setSignDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String signPlace = VMAUtils.getString(actionRequest, "signPlace",
				StringPool.BLANK);
		if (Validator.isNotNull(signPlace)) {
			vmaScheduleShipyard.setSignPlace(signPlace);
		}
		long attachedFile = GetterUtil.getLong(
				request.getParameter("attachedFile"), -1);
		if (attachedFile >= 0) {
			vmaScheduleShipyard.setAttachedFile(attachedFile);
		}
		int stampStatus = GetterUtil.getInteger(
				request.getParameter("stampStatus"), -1);
		if (stampStatus >= 0) {
			vmaScheduleShipyard.setStampStatus(stampStatus);
		}
		
		return vmaScheduleShipyard;
	}

	public static JSONObject addVmaScheduleShipyard(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleShipyard vmaScheduleShipyard = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleShipyard == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleShipyard = VmaScheduleShipyardLocalServiceUtil
					.addVmaScheduleShipyard(vmaScheduleShipyard);
			result = VMAUtils.object2Json(vmaScheduleShipyard,
					VmaScheduleShipyard.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleShipyard(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleShipyard vmaScheduleShipyard = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleShipyard == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleShipyard = VmaScheduleShipyardLocalServiceUtil
					.updateVmaScheduleShipyard(vmaScheduleShipyard);
			result = VMAUtils.object2Json(vmaScheduleShipyard,
					VmaScheduleShipyard.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleShipyard(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaScheduleShipyardId");
		if (id > 0) {
			try {
				VmaScheduleShipyardLocalServiceUtil
						.deleteVmaScheduleShipyard(id);
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
		String shipYardCode = ParamUtil.getString(resourceRequest,
				"shipYardCode", StringPool.BLANK);
		String shipYardCompanyName = ParamUtil.getString(resourceRequest,
				"shipYardCompanyName", StringPool.BLANK);
		String shipYardOfficialNo = ParamUtil.getString(resourceRequest,
				"shipYardOfficialNo", StringPool.BLANK);
		String repairingFrom = ParamUtil.getString(resourceRequest,
				"repairingFrom", StringPool.BLANK);
		String repairingTo = ParamUtil.getString(resourceRequest,
				"repairingTo", StringPool.BLANK);
		String repairingPlace = ParamUtil.getString(resourceRequest,
				"repairingPlace", StringPool.BLANK);
		String repairingReason = ParamUtil.getString(resourceRequest,
				"repairingReason", StringPool.BLANK);
		int repaired = GetterUtil.getInteger(request.getParameter("repaired"),
				-1);

		try {
			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, shipYardCode,
					shipYardCompanyName, shipYardOfficialNo, repairingFrom,
					repairingTo, repairingPlace, repairingReason, repaired);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, shipYardCode,
					shipYardCompanyName, shipYardOfficialNo, repairingFrom,
					repairingTo, repairingPlace, repairingReason, repaired);

			return VmaScheduleShipyardLocalServiceUtil.findVmaScheduleShipyard(
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
		String shipYardCode = ParamUtil.getString(resourceRequest,
				"shipYardCode", StringPool.BLANK);
		String shipYardCompanyName = ParamUtil.getString(resourceRequest,
				"shipYardCompanyName", StringPool.BLANK);
		String shipYardOfficialNo = ParamUtil.getString(resourceRequest,
				"shipYardOfficialNo", StringPool.BLANK);
		String repairingFrom = ParamUtil.getString(resourceRequest,
				"repairingFrom", StringPool.BLANK);
		String repairingTo = ParamUtil.getString(resourceRequest,
				"repairingTo", StringPool.BLANK);
		String repairingPlace = ParamUtil.getString(resourceRequest,
				"repairingPlace", StringPool.BLANK);
		String repairingReason = ParamUtil.getString(resourceRequest,
				"repairingReason", StringPool.BLANK);
		int repaired = GetterUtil.getInteger(request.getParameter("repaired"),
				-1);

		/*
		 * String[] headers = new String[] { "STT", "Tên tàu", "Thời gian đến",
		 * "Số quyết định", "Đơn vị sửa chữa", "Thời gian đi", "Lý do sửa",
		 * "Từ ngày", "Đến ngày" };
		 */

		String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
				"Th\u1EDDi gian \u0111\u1EBFn",
				"S\u1ED1 quy\u1EBFt \u0111\u1ECBnh",
				"\u0110\u01A1n v\u1ECB s\u1EEDa ch\u1EEFa",
				"Th\u1EDDi gian \u0111i", "L\u00FD do s\u1EEDa",
				"T\u1EEB ng\u00E0y", "\u0110\u1EBFn ng\u00E0y" };

		String[][] exportData = null;

		try {

			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, shipYardCode,
					shipYardCompanyName, shipYardOfficialNo, repairingFrom,
					repairingTo, repairingPlace, repairingReason, repaired);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, shipYardCode,
					shipYardCompanyName, shipYardOfficialNo, repairingFrom,
					repairingTo, repairingPlace, repairingReason, repaired);

			JSONObject objects = VmaScheduleShipyardLocalServiceUtil
					.findVmaScheduleShipyard(searchQuery, countQuery, -1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");

					repairingFrom = object.getString("repairingFrom");

					shipYardOfficialNo = object.getString("shipYardOfficialNo");

					shipYardCompanyName = object
							.getString("shipYardCompanyName");

					repairingTo = object.getString("repairingTo");

					repairingReason = object.getString("repairingReason");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = repairingFrom;
					exportData[i][3] = shipYardOfficialNo;
					exportData[i][4] = shipYardCompanyName;
					exportData[i][5] = repairingTo;

					exportData[i][6] = repairingReason;
					exportData[i][7] = repairingFrom;
					exportData[i][8] = repairingTo;

				}
			}

			String sheetName = "VMA-Schedule-Shipyard";

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
		/*
		 * int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		 * int end = GetterUtil.getInteger(request.getParameter("end"), 0); long
		 * vmaScheduleShipyardId = GetterUtil.getLong(
		 * request.getParameter("vmaScheduleShipyardId"), -1);
		 */
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
		String shipYardCode = ParamUtil.getString(resourceRequest,
				"shipYardCode", StringPool.BLANK);
		String shipYardCompanyName = ParamUtil.getString(resourceRequest,
				"shipYardCompanyName", StringPool.BLANK);
		String shipYardOfficialNo = ParamUtil.getString(resourceRequest,
				"shipYardOfficialNo", StringPool.BLANK);
		String repairingFrom = ParamUtil.getString(resourceRequest,
				"repairingFrom", StringPool.BLANK);
		String repairingTo = ParamUtil.getString(resourceRequest,
				"repairingTo", StringPool.BLANK);
		String repairingPlace = ParamUtil.getString(resourceRequest,
				"repairingPlace", StringPool.BLANK);
		String repairingReason = ParamUtil.getString(resourceRequest,
				"repairingReason", StringPool.BLANK);
		int repaired = GetterUtil.getInteger(request.getParameter("repaired"),
				-1);

		try {
			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, shipYardCode,
					shipYardCompanyName, shipYardOfficialNo, repairingFrom,
					repairingTo, repairingPlace, repairingReason, repaired);

			return VmaScheduleShipyardLocalServiceUtil
					.countVmaScheduleShipyard(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	private static String generateQuery(String cmd, String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String shipYardCode,
			String shipYardCompanyName, String shipYardOfficialNo,
			String repairingFrom, String repairingTo, String repairingPlace,
			String repairingReason, Integer repaired) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_schedule_shipyard as a";
		} else {
			sql = "SELECT a.* FROM vma_schedule_shipyard AS a";
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

		if (Validator.isNotNull(shipYardCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipYardCode", "'"
					+ shipYardCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shipYardCompanyName)) {
			condition.append(VMAUtils.buildSQLCondition("ShipYardCompanyName",
					"'" + shipYardCompanyName + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shipYardOfficialNo)) {
			condition.append(VMAUtils.buildSQLCondition("ShipYardOfficialNo",
					"'" + shipYardOfficialNo + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(repairingPlace)) {
			condition.append(VMAUtils.buildSQLCondition("RepairingPlace", "'"
					+ repairingPlace + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(repairingReason)) {
			condition.append(VMAUtils.buildSQLCondition("RepairingReason", "'"
					+ repairingReason + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(repairingFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(repairingFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("RepairingFrom",
						"'" + strDate + " 00:00:00'" + " AND '" + strDate
								+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(repairingTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(repairingTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("RepairingTo", "'"
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
