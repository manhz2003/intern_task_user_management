package vn.gt.portlet.phuongtien;

import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaItineraryRemark;


import vn.gt.dao.noticeandmessage.service.VmaItineraryRemarksLocalServiceUtil;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.utils.FormatData;

import org.json.JSONArray;
import org.json.JSONException;
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
public class VmaItineraryRemarksUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		long vmaItineraryRemarksId = ParamUtil.getLong(request,
				"vmaItineraryRemarksId");

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			VmaItineraryRemark vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
					.getVmaItineraryRemarks(vmaItineraryRemarksId);
			result = VMAUtils.object2Json(vmaItineraryRemarks,
					VmaItineraryRemark.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static VmaItineraryRemark getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		HttpServletRequest request = actionRequest;

		long id = GetterUtil.getLong(
				request.getParameter("vmaItineraryRemarksId"), -1);

		VmaItineraryRemark vmaItineraryRemarks = null;

		if (id > 0) {
			try {
				vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
						.getVmaItineraryRemarks(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaItineraryRemarks = new VmaItineraryRemark();
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		if (Validator.isNotNull(itineraryNo)) {
			vmaItineraryRemarks.setItineraryNo(itineraryNo);
		}

		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaItineraryRemarks.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaItineraryRemarks.setNameOfShip(nameOfShip);
		}
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		if (documentName >= 0) {
			vmaItineraryRemarks.setDocumentName(documentName);
		}
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		if (documentYear >= 0) {
			vmaItineraryRemarks.setDocumentYear(documentYear);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaItineraryRemarks.setNoticeShipType(noticeShipType);
		}
		String requestDate = ParamUtil.getString(actionRequest, "requestDate",
				StringPool.BLANK);
		if (Validator.isNotNull(requestDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(requestDate);
				vmaItineraryRemarks.setRequestDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String requestPerson = VMAUtils.getString(actionRequest,
				"requestPerson", StringPool.BLANK);
		if (Validator.isNotNull(requestPerson)) {
			vmaItineraryRemarks.setRequestPerson(requestPerson);
		}
		String remarks = VMAUtils.getString(actionRequest, "remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(remarks)) {
			vmaItineraryRemarks.setRemarks(remarks);
		}
		int markedAsPending = GetterUtil.getInteger(
				request.getParameter("markedAsPending"), -1);
		if (markedAsPending >= 0) {
			vmaItineraryRemarks.setMarkedAsPending(markedAsPending);
		}
		/*
		 * String modifiedDate = ParamUtil.getString(actionRequest,
		 * "modifiedDate", StringPool.BLANK); if
		 * (Validator.isNotNull(modifiedDate)) { try { Date date =
		 * FormatData.formatDateShort3.parse(modifiedDate);
		 * vmaItineraryRemarks.setModifiedDate(date); } catch (ParseException e)
		 * { log.error(e.getMessage()); } }
		 */

		return vmaItineraryRemarks;
	}

	public static JSONObject addVmaItineraryRemarks(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaItineraryRemark vmaItineraryRemarks = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaItineraryRemarks == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
					.addVmaItineraryRemarks(vmaItineraryRemarks);
			result = VMAUtils.object2Json(vmaItineraryRemarks,
					VmaItineraryRemark.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaItineraryRemarks(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaItineraryRemark vmaItineraryRemarks = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaItineraryRemarks == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
					.updateVmaItineraryRemarks(vmaItineraryRemarks);
			result = VMAUtils.object2Json(vmaItineraryRemarks,
					VmaItineraryRemark.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaItineraryRemarks(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaItineraryRemarksId");
		if (id > 0) {
			try {
				VmaItineraryRemarksLocalServiceUtil
						.deleteVmaItineraryRemarks(id);
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
		String requestDate = ParamUtil.getString(resourceRequest,
				"requestDate", StringPool.BLANK);
		String requestPerson = ParamUtil.getString(resourceRequest,
				"requestPerson", StringPool.BLANK);
		String remarks = ParamUtil.getString(resourceRequest, "remarks",
				StringPool.BLANK);
		int markedAsPending = GetterUtil.getInteger(
				request.getParameter("markedAsPending"), -1);
		try {
			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType > 0 ? noticeShipType : null, requestDate,
					requestPerson, remarks, markedAsPending);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType > 0 ? noticeShipType : null, requestDate,
					requestPerson, remarks, markedAsPending);

			return VmaItineraryRemarksLocalServiceUtil.findVmaItineraryRemarks(
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
		String requestDate = ParamUtil.getString(resourceRequest,
				"requestDate", StringPool.BLANK);
		String requestPerson = ParamUtil.getString(resourceRequest,
				"requestPerson", StringPool.BLANK);
		String remarks = ParamUtil.getString(resourceRequest, "remarks",
				StringPool.BLANK);
		int markedAsPending = GetterUtil.getInteger(
				request.getParameter("markedAsPending"), -1);

		/*
		 * String[] headers = new String[] { "STT", "Ghi chú tàu",
		 * "Có hiển thị cảnh báo", "Ngày ghi chú", "Ngày hết hiệu lực ghi chú",
		 * "Không giải quyết rời cảng" };
		 */

		String[] headers = new String[] { "STT", "Ghi ch\u00FA t\u00E0u",
				"C\u00F3 hi\u1EC3n th\u1ECB c\u1EA3nh b\u00E1o",
				"Ng\u00E0y ghi ch\u00FA",
				"Ng\u00E0y h\u1EBFt hi\u1EC7u l\u1EF1c ghi ch\u00FA",
				"Kh\u00F4ng gi\u1EA3i quy\u1EBFt r\u1EDDi c\u1EA3ng" };

		String[][] exportData = null;

		try {

			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType > 0 ? noticeShipType : null, requestDate,
					requestPerson, remarks, markedAsPending);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType > 0 ? noticeShipType : null, requestDate,
					requestPerson, remarks, markedAsPending);

			JSONObject objects = VmaItineraryRemarksLocalServiceUtil
					.findVmaItineraryRemarks(searchQuery, countQuery, -1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					remarks = object.getString("remarks");

					// TODO ignore
					String allowAlert = object.getString("allowAlert");
					requestDate = object.getString("requestDate");
					// TODO ignore
					String requestExpireDate = object
							.getString("requestExpireDate");

					markedAsPending = object.getInt("markedAsPending");

					String markedAsPendingLabel = VMAUtils
							.getMarkedAsPendingLabel(markedAsPending);

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = remarks;
					exportData[i][2] = allowAlert;
					exportData[i][3] = requestDate;
					exportData[i][4] = requestExpireDate;
					exportData[i][5] = markedAsPendingLabel;

				}
			}

			String sheetName = "VMA-Schedule-Remarks";

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
		String requestDate = ParamUtil.getString(resourceRequest,
				"requestDate", StringPool.BLANK);
		String requestPerson = ParamUtil.getString(resourceRequest,
				"requestPerson", StringPool.BLANK);
		String remarks = ParamUtil.getString(resourceRequest, "remarks",
				StringPool.BLANK);
		int markedAsPending = GetterUtil.getInteger(
				request.getParameter("markedAsPending"), -1);
		try {
			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType > 0 ? noticeShipType : null, requestDate,
					requestPerson, remarks, markedAsPending);

			return VmaItineraryRemarksLocalServiceUtil
					.countVmaItineraryRemarks(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	private static String generateQuery(String cmd, String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String requestDate,
			String requestPerson, String remarks, Integer markedAsPending) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_itinerary_remarks as a";
		} else {
			sql = "SELECT a.* FROM vma_itinerary_remarks AS a";
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

		if (markedAsPending != null && markedAsPending >= 0) {
			condition.append(VMAUtils.buildSQLCondition("MarkedAsPending",
					markedAsPending, "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (documentName != null) {
			condition.append(VMAUtils
					.buildSQLCondition("DocumentName", documentName, "AND",
							StringPool.EQUAL, new String[] { "a" }));
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortofAuthority",
					portofAuthority, "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(requestPerson)) {
			condition.append(VMAUtils.buildSQLCondition("RequestPerson", "'%"
					+ requestPerson + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(remarks)) {
			condition.append(VMAUtils.buildSQLCondition("Remarks", "'%"
					+ remarks + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(requestDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(requestDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("RequestDate", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN,
						new String[] { "a" }));
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		return sql + condition.toString();
	}

	public static JSONObject getVmaitineraryRemarks(
			ResourceRequest resourceRequest) throws JSONException {
		HttpServletRequest request = resourceRequest;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		int noticeShipType = ParamUtil.getInteger(request, "noticeShipType");
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");

		VmaItineraryRemark vmaItineraryRemarks = null;
		try {
			vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo,
							noticeShipType).get(0);
		} catch (Exception e) {
		}

		if (vmaItineraryRemarks != null) {
			result = VMAUtils.object2Json(vmaItineraryRemarks,
					VmaItineraryRemark.class);
		}

		return result;
	}
}
