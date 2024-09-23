package vn.gt.portlet.phuongtien;

import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaItineraryProtest;


import vn.gt.dao.noticeandmessage.service.VmaItineraryProtestLocalServiceUtil;
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
public class VmaItineraryProtestUtils
 {

	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		long vmaItineraryProtestId = ParamUtil.getLong(request,
				"vmaItineraryProtestId");

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			VmaItineraryProtest vmaItineraryProtest = VmaItineraryProtestLocalServiceUtil
					.getVmaItineraryProtest(vmaItineraryProtestId);
			result = VMAUtils.object2Json(vmaItineraryProtest,
					VmaItineraryProtest.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo sequenceNo portofAuthority nameOfShip documentName
	 * documentYear noticeShipType shipOwnerCode shipOperatorCode shipAgencyCode
	 * protestDate protestRemarks makePayment modifiedDate
	 */
	public static VmaItineraryProtest getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		HttpServletRequest request = actionRequest;

		long id = GetterUtil.getLong(
				request.getParameter("vmaItineraryProtestId"), -1);

		VmaItineraryProtest vmaItineraryProtest = null;

		if (id > 0) {
			try {
				vmaItineraryProtest = VmaItineraryProtestLocalServiceUtil
						.getVmaItineraryProtest(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaItineraryProtest = new VmaItineraryProtest();
		}

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaItineraryProtest.setItineraryNo(itineraryNo);
		}

		// tu tang
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1); if (sequenceNo >= 0) {
		 * vmaItineraryProtest.setSequenceNo(sequenceNo); }
		 */
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaItineraryProtest.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaItineraryProtest.setNameOfShip(nameOfShip);
		}
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		if (documentName >= 0) {
			vmaItineraryProtest.setDocumentName(documentName);
		}
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		if (documentYear >= 0) {
			vmaItineraryProtest.setDocumentYear(documentYear);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaItineraryProtest.setNoticeShipType(noticeShipType);
		}
		
		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaItineraryProtest.setShipOwnerCode(shipOwnerCode);
		}
		String shipOwnersName = VMAUtils.getString(actionRequest,
				"shipOwnersName", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnersName)) {
			vmaItineraryProtest.setShipOwnersName(shipOwnersName);
		}
				
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaItineraryProtest.setShipOperatorCode(shipOperatorCode);
		}
		String shipOperatorName = VMAUtils.getString(actionRequest,
				"shipOperatorName", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorName)) {
			vmaItineraryProtest.setShipOperatorName(shipOperatorName);
		}
		
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaItineraryProtest.setShipAgencyCode(shipAgencyCode);
		}
		String shipAgencyName = VMAUtils.getString(actionRequest,
				"shipAgencyName", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyName)) {
			vmaItineraryProtest.setShipAgencyName(shipAgencyName);
		}
		String shipAgencyAddress = VMAUtils.getString(actionRequest,
				"shipAgencyAddress", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyAddress)) {
			vmaItineraryProtest.setShipAgencyAddress(shipAgencyAddress);
		}
		String shipAgencyContactEmail = ParamUtil.getString(actionRequest,
				"shipAgencyContactEmail", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyContactEmail)) {
			vmaItineraryProtest
					.setShipAgencyContactEmail(shipAgencyContactEmail);
		}
		String shipAgencyPhone = ParamUtil.getString(actionRequest,
				"shipAgencyPhone", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyPhone)) {
			vmaItineraryProtest.setShipAgencyPhone(shipAgencyPhone);
		}
		String shipAgencyFax = ParamUtil.getString(actionRequest,
				"shipAgencyFax", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyFax)) {
			vmaItineraryProtest.setShipAgencyFax(shipAgencyFax);
		}
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaItineraryProtest.setSecurityLevelCode(securityLevelCode);
		}
		
		String departmentCode = VMAUtils.getString(actionRequest,
				"departmentCode", StringPool.BLANK);
		if (Validator.isNotNull(departmentCode)) {
			vmaItineraryProtest.setDepartmentCode(departmentCode);
		}
		
		String departmentName = VMAUtils.getString(actionRequest,
				"departmentName", StringPool.BLANK);
		if (Validator.isNotNull(departmentName)) {
			vmaItineraryProtest.setDepartmentName(departmentName);
		}
		String protestDate = ParamUtil.getString(actionRequest, "protestDate",
				StringPool.BLANK);
		if (Validator.isNotNull(protestDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(protestDate);
				vmaItineraryProtest.setProtestDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String protestRemarks = VMAUtils.getString(actionRequest,
				"protestRemarks", StringPool.BLANK);
		if (Validator.isNotNull(protestRemarks)) {
			vmaItineraryProtest.setProtestRemarks(protestRemarks);
		}
		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);
		if (makePayment >= 0) {
			vmaItineraryProtest.setMakePayment(makePayment);
		}
		String modifiedDate = ParamUtil.getString(actionRequest,
				"modifiedDate", StringPool.BLANK);
		if (Validator.isNotNull(modifiedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(modifiedDate);
				vmaItineraryProtest.setModifiedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}

		String troubleShootingDate = ParamUtil.getString(actionRequest,
				"troubleShootingDate", StringPool.BLANK);
		if (Validator.isNotNull(troubleShootingDate)) {
			try {
				Date date = FormatData.formatDateShort3
						.parse(troubleShootingDate);
				vmaItineraryProtest.setTroubleShootingDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String finishedDate = ParamUtil.getString(actionRequest,
				"finishedDate", StringPool.BLANK);
		if (Validator.isNotNull(finishedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(finishedDate);
				vmaItineraryProtest.setFinishedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}

		String documentaryCode = ParamUtil.getString(actionRequest,
				"documentaryCode", StringPool.BLANK);
		if (Validator.isNotNull(documentaryCode)) {
			vmaItineraryProtest.setDocumentaryCode(documentaryCode);
		}
		
		return vmaItineraryProtest;
	}

	public static JSONObject addVmaItineraryProtest(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaItineraryProtest vmaItineraryProtest = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaItineraryProtest == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaItineraryProtest = VmaItineraryProtestLocalServiceUtil
					.addVmaItineraryProtest(vmaItineraryProtest);
			result = VMAUtils.object2Json(vmaItineraryProtest,
					VmaItineraryProtest.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaItineraryProtest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaItineraryProtest vmaItineraryProtest = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaItineraryProtest == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaItineraryProtest = VmaItineraryProtestLocalServiceUtil
					.updateVmaItineraryProtest(vmaItineraryProtest);
			result = VMAUtils.object2Json(vmaItineraryProtest,
					VmaItineraryProtest.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	//TODO: Check lai
	public static JSONObject deleteVmaItineraryProtest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaItineraryProtestId");
		if (id > 0) {
			try {
				VmaItineraryProtest curProtest = VmaItineraryProtestLocalServiceUtil
						.getVmaItineraryProtest(id);
				if (curProtest.getMakePayment() == 0) {
					VmaItineraryProtestLocalServiceUtil
							.deleteVmaItineraryProtest(curProtest);

					return VMAUtils.createResponseMessage(LanguageUtil.get(
							themeDisplay.getLocale(), "success"), "",
							StringPool.BLANK);
				} else
					return VMAUtils.createResponseMessage(LanguageUtil.get(
							themeDisplay.getLocale(),
							"Failed Makepayment khác 0"),
							"Failed Makepayment khác 0", StringPool.BLANK);

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
		String nameOfShip = VMAUtils.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);
		String protestDate = ParamUtil.getString(resourceRequest,
				"protestDate", StringPool.BLANK);
		String protestRemarks = VMAUtils.getString(resourceRequest,
				"protestRemarks", StringPool.BLANK);
		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);
		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);
		try {

			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null,
					shipOperatorCode, shipAgencyCode, protestDate,
					protestRemarks, makePayment >= 0 ? makePayment : null,
					documentaryCode);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null,
					shipOperatorCode, shipAgencyCode, protestDate,
					protestRemarks, makePayment >= 0 ? makePayment : null,
					documentaryCode);

			return VmaItineraryProtestLocalServiceUtil.findVmaItineraryProtest(
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
		/*
		 * int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		 * int end = GetterUtil.getInteger(request.getParameter("end"), 0); long
		 * vmaItineraryProtestId = GetterUtil.getLong(
		 * request.getParameter("vmaItineraryProtestId"), -1);
		 */

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1);
		 */
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
		/*
		 * String shipOwnerCode = ParamUtil.getString(resourceRequest,
		 * "shipOwnerCode", StringPool.BLANK);
		 */
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);
		String protestDate = ParamUtil.getString(resourceRequest,
				"protestDate", StringPool.BLANK);
		String protestRemarks = ParamUtil.getString(resourceRequest,
				"protestRemarks", StringPool.BLANK);
		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);
		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);
		/*
		 * String modifiedDate = ParamUtil.getString(resourceRequest,
		 * "modifiedDate", StringPool.BLANK);
		 */
		/*
		 * String[] headers = new String[] { "STT", "Tên tàu", "Nội dung",
		 * "Thời gian nhận kháng cáo", "Đại lý", "Chủ tàu", "Khai thác" };
		 */

		String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
				"N\u1ED9i dung",
				"Th\u1EDDi gian nh\u1EADn kh\u00E1ng c\u00E1o",
				"\u0110\u1EA1i l\u00FD", "Ch\u1EE7 t\u00E0u", "Khai th\u00E1c" };

		String[][] exportData = null;

		try {

			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null,
					shipOperatorCode, shipAgencyCode, protestDate,
					protestRemarks, makePayment >= 0 ? makePayment : null,
					documentaryCode);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null,
					shipOperatorCode, shipAgencyCode, protestDate,
					protestRemarks, makePayment >= 0 ? makePayment : null,
					documentaryCode);

			JSONObject objects = VmaItineraryProtestLocalServiceUtil
					.findVmaItineraryProtest(searchQuery, countQuery, -1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");
					protestRemarks = object.getString("protestRemarks");
					protestDate = object.getString("protestDate");
					String shipAgencyName = object.getString("shipAgencyName");
					String shipOwnerName = object.getString("shipOwnerName");
					String compamyName = object.getString("shipOperatorName");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = protestRemarks;
					exportData[i][3] = protestDate;
					exportData[i][4] = shipAgencyName;
					exportData[i][5] = shipOwnerName;
					exportData[i][6] = compamyName;
				}
			}

			String sheetName = "VMA-Schedule-Protest";

			String fileName = sheetName + "-" + System.currentTimeMillis();

			String filePath = VMAUtils.doExportExcelFile(request,
					resourceResponse, sheetName, fileName, headers, exportData);
//todo response file in controller
//			resourceResponse
//					.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//			resourceResponse.setProperty("Content-Disposition",
//					"attachment; filename= " + fileName + ".xls");
//			resourceResponse.setCharacterEncoding("UTF-8");
//			// resourceResponse.setProperty("filename", fileName + ".xls");
//			resourceResponse.addProperty("FILE-NAME", fileName + ".xls");
//
//			resourceResponse.getPortletOutputStream().write(
//					VMAUtils.readFileToByteArray(new File(filePath)));

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
		 * vmaItineraryProtestId = GetterUtil.getLong(
		 * request.getParameter("vmaItineraryProtestId"), -1);
		 */

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1);
		 */
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
		/*
		 * String shipOwnerCode = ParamUtil.getString(resourceRequest,
		 * "shipOwnerCode", StringPool.BLANK);
		 */
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);
		String protestDate = ParamUtil.getString(resourceRequest,
				"protestDate", StringPool.BLANK);
		String protestRemarks = ParamUtil.getString(resourceRequest,
				"protestRemarks", StringPool.BLANK);
		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);

		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);
		/*
		 * String modifiedDate = ParamUtil.getString(resourceRequest,
		 * "modifiedDate", StringPool.BLANK);
		 */
		try {
			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null,
					shipOperatorCode, shipAgencyCode, protestDate,
					protestRemarks, makePayment >= 0 ? makePayment : null,
					documentaryCode);

			return VmaItineraryProtestLocalServiceUtil
					.countVmaItineraryProtest(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	private static String generateQuery(String cmd, String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType,
			String shipOperatorCode, String shipAgencyCode, String protestDate,
			String protestRemarks, Integer makePayment, String documentaryCode) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_itinerary_protest as a";

		} else {
			sql = "SELECT a.* FROM vma_itinerary_protest AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("NameOfShip", "'%"
					+ nameOfShip + "%'", "AND", StringPool.LIKE));
		}

		if (noticeShipType != null) {
			condition.append(VMAUtils.buildSQLCondition("NoticeShipType", "'"
					+ noticeShipType + "'", "AND", StringPool.EQUAL));
		}

		if (documentYear != null) {
			condition.append(VMAUtils.buildSQLCondition("DocumentYear",
					documentYear, "AND", StringPool.EQUAL));
		}

		if (documentYear != null) {
			condition.append(VMAUtils.buildSQLCondition("DocumentYear",
					documentYear, "AND", StringPool.EQUAL));
		}

		if (makePayment != null) {
			condition.append(VMAUtils.buildSQLCondition("MakePayment",
					makePayment, "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shipOperatorCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipOperatorCode", "'"
					+ shipOperatorCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shipAgencyCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipAgencyCode", "'"
					+ shipAgencyCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(protestRemarks)) {
			condition.append(VMAUtils.buildSQLCondition("ProtestRemarks", "'%"
					+ protestRemarks + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortofAuthority", "'"
					+ portofAuthority + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(protestDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(protestDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("ProtestDate", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(documentaryCode)) {
			condition.append(VMAUtils.buildSQLCondition("DocumentaryCode", "'"
					+ documentaryCode + "'", "AND", StringPool.EQUAL));
		}

		return sql + condition.toString();
	}

	public static JSONObject updateVmaItineraryProtest_MakePayment_DocumentaryCode(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray results = JSONFactoryUtil.createJSONArray();

		String documentaryCode = ParamUtil
				.getString(request, "documentaryCode");
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		String makePayment = ParamUtil.getString(request, "makePayment");
		String vmaItineraryProtestId = ParamUtil.getString(request,
				"vmaItineraryProtestId");
		String[] arrayId = vmaItineraryProtestId.split(",");
		if (arrayId != null && arrayId.length > 0) {
			for (int i = 0; i < arrayId.length; i++) {
				VmaItineraryProtest vmaItineraryProtest = null;
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				try {
					vmaItineraryProtest = VmaItineraryProtestLocalServiceUtil
							.fetchVmaItineraryProtest(Long.valueOf(arrayId[i]));
					if (vmaItineraryProtest != null) {
						if (vmaItineraryProtest.getItineraryNo().equals(
								itineraryNo)) {
							vmaItineraryProtest
									.setDocumentaryCode(documentaryCode);
							vmaItineraryProtest.setMakePayment(Integer
									.valueOf(makePayment));

							vmaItineraryProtest = VmaItineraryProtestLocalServiceUtil
									.updateVmaItineraryProtest(vmaItineraryProtest);
							obj = VMAUtils.object2Json(vmaItineraryProtest,
									VmaItineraryProtest.class);

							results.put(obj);
						}
					}
				} catch (Exception e) {
					// nothing to do
				}
			}
		}
		result.put("data", results);

		return result;
	}

	public static JSONObject updateVmaItineraryProtestByDocumentaryCode_ItineraryNo(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray results = JSONFactoryUtil.createJSONArray();

		String documentaryCode = ParamUtil
				.getString(request, "documentaryCode");
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		String makePayment = ParamUtil.getString(request, "makePayment");

		List<VmaItineraryProtest> vmaItineraryProtests = VmaItineraryProtestLocalServiceUtil
				.findByItineraryNo_documentaryCode(itineraryNo, documentaryCode);
		if (vmaItineraryProtests != null && !vmaItineraryProtests.isEmpty()) {
			for (VmaItineraryProtest vmaItineraryProtest : vmaItineraryProtests) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				try {
					vmaItineraryProtest.setMakePayment(Integer
							.valueOf(makePayment));
					vmaItineraryProtest = VmaItineraryProtestLocalServiceUtil
							.updateVmaItineraryProtest(vmaItineraryProtest);

					obj = VMAUtils.object2Json(vmaItineraryProtest,
							VmaItineraryProtest.class);

					results.put(obj);
				} catch (Exception e) {
					// nothing to do
				}
			}
		}

		result.put("data", results);
		return result;
	}
}
