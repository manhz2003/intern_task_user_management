package vn.gt.portlet.phuongtien;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaScheduleXline;
import com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing;


import vn.gt.dao.noticeandmessage.service.VmaScheduleXlineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleXlineSailingLocalServiceUtil;
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
public class VmaScheduleXlineUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaScheduleXlineId = ParamUtil.getLong(request,
				"vmaScheduleXlineId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaScheduleXline vmaScheduleXline = VmaScheduleXlineLocalServiceUtil
					.getVmaScheduleXline(vmaScheduleXlineId);
			result = VMAUtils.object2Json(vmaScheduleXline,
					VmaScheduleXline.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id portofAuthority shipOperatorCode companyName routeDescription
	 * scheduleMonth voyageNumber modifiedDate
	 */
	public static VmaScheduleXline getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleXlineId"), -1);
		VmaScheduleXline vmaScheduleXline = null;
		if (id > 0) {
			try {
				vmaScheduleXline = VmaScheduleXlineLocalServiceUtil
						.getVmaScheduleXline(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleXline = new VmaScheduleXline();
		}
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleXline.setPortofAuthority(portofAuthority);
		}
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaScheduleXline.setShipOperatorCode(shipOperatorCode);
		}
		String companyName = VMAUtils.getString(actionRequest, "companyName",
				StringPool.BLANK);
		if (Validator.isNotNull(companyName)) {
			vmaScheduleXline.setCompanyName(companyName);
		}
		String routeDescription = VMAUtils.getString(actionRequest,
				"routeDescription", StringPool.BLANK);
		if (Validator.isNotNull(routeDescription)) {
			vmaScheduleXline.setRouteDescription(routeDescription);
		}
		int scheduleMonth = GetterUtil.getInteger(
				request.getParameter("scheduleMonth"), -1);
		if (scheduleMonth >= 0) {
			vmaScheduleXline.setScheduleMonth(scheduleMonth);
		}
		int voyageNumber = ParamUtil.getInteger(actionRequest, "voyageNumber",
				-1);
		if (voyageNumber >= 0) {
			vmaScheduleXline.setVoyageNumber(voyageNumber);
		}
		String modifiedDate = ParamUtil.getString(actionRequest,
				"modifiedDate", StringPool.BLANK);
		if (Validator.isNotNull(modifiedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(modifiedDate);
				vmaScheduleXline.setModifiedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		
		int scheduleYear = GetterUtil.getInteger(request.getParameter("scheduleYear"), -1);
		if(scheduleYear >= 0){
			vmaScheduleXline.setScheduleYear(scheduleYear);
		}
		
		return vmaScheduleXline;
	}

	public static JSONObject addVmaScheduleXline(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleXline vmaScheduleXline = getObjectFromRequest(themeDisplay,
				actionRequest);
		if (vmaScheduleXline == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleXline = VmaScheduleXlineLocalServiceUtil
					.addVmaScheduleXline(vmaScheduleXline);
			result = VMAUtils.object2Json(vmaScheduleXline,
					VmaScheduleXline.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleXline(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleXline vmaScheduleXline = getObjectFromRequest(themeDisplay,
				actionRequest);
		if (vmaScheduleXline == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleXline = VmaScheduleXlineLocalServiceUtil
					.updateVmaScheduleXline(vmaScheduleXline);
			result = VMAUtils.object2Json(vmaScheduleXline,
					VmaScheduleXline.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleXline(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaScheduleXlineId");
		if (id > 0) {
			String description = "";
			try {
				VmaScheduleXline curScheduleXline = VmaScheduleXlineLocalServiceUtil
						.getVmaScheduleXline(id);
				String shipOperatorCode = curScheduleXline.getShipOperatorCode();
				int scheduleYear = curScheduleXline.getScheduleYear();
				int scheduleMonth = curScheduleXline.getScheduleMonth();
				try{
					List<VmaScheduleXlineSailing> lstScheduleXlineSailing = VmaScheduleXlineSailingLocalServiceUtil
							.findByshipOperatorCode_scheduleYear_scheduleMonth(shipOperatorCode, scheduleYear, scheduleMonth);
					
					if (lstScheduleXlineSailing != null){
						for (VmaScheduleXlineSailing curScheduleXlineSailing : lstScheduleXlineSailing){
							VmaScheduleXlineSailingLocalServiceUtil.deleteVmaScheduleXlineSailing(curScheduleXlineSailing);
						}
					}
				}
				catch (Exception e){
					description += e.getMessage();
				}
				
				return VMAUtils.createResponseMessage(
						LanguageUtil.get(themeDisplay.getLocale(), "success"),
						"", description);
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
		/*
		 * long vmaScheduleXlineId = GetterUtil.getLong(
		 * request.getParameter("vmaScheduleXlineId"), -1);
		 */
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String companyName = ParamUtil.getString(resourceRequest,
				"companyName", StringPool.BLANK);
		String routeDescription = ParamUtil.getString(resourceRequest,
				"routeDescription", StringPool.BLANK);
		int scheduleMonth = GetterUtil.getInteger(
				request.getParameter("scheduleMonth"), -1);
		int scheduleYear = GetterUtil.getInteger(
				request.getParameter("scheduleYear"), -1);
		int voyageNumber = GetterUtil.getInteger(
				request.getParameter("voyageNumber"), -1);
		/*
		 * String modifiedDate = ParamUtil.getString(resourceRequest,
		 * "modifiedDate", StringPool.BLANK);
		 */
		try {
			String searchQuery = generateQuery("search", portofAuthority,
					shipOperatorCode, companyName, routeDescription,
					scheduleYear >= 0 ? scheduleYear : null,
					scheduleMonth >= 0 ? scheduleMonth : null,
					voyageNumber >= 0 ? voyageNumber : null);

			String countQuery = generateQuery("count", portofAuthority,
					shipOperatorCode, companyName, routeDescription,
					scheduleYear >= 0 ? scheduleYear : null,
					scheduleMonth >= 0 ? scheduleMonth : null,
					voyageNumber >= 0 ? voyageNumber : null);

			return VmaScheduleXlineLocalServiceUtil.findVmaScheduleXline(
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
		 * vmaScheduleXlineId = GetterUtil.getLong(
		 * request.getParameter("vmaScheduleXlineId"), -1);
		 */
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String companyName = ParamUtil.getString(resourceRequest,
				"companyName", StringPool.BLANK);
		String routeDescription = ParamUtil.getString(resourceRequest,
				"routeDescription", StringPool.BLANK);
		int scheduleMonth = GetterUtil.getInteger(
				request.getParameter("scheduleMonth"), -1);
		int scheduleYear = GetterUtil.getInteger(
				request.getParameter("scheduleYear"), -1);
		int voyageNumber = GetterUtil.getInteger(
				request.getParameter("voyageNumber"), -1);
		/*
		 * String modifiedDate = ParamUtil.getString(resourceRequest,
		 * "modifiedDate", StringPool.BLANK);
		 */
		/*
		 * String[] headers = new String[] {
		 * "STT","Đơn vị khai thác","Tháng","Số chuyến trong tháng"};
		 */

		String[] headers = new String[] { "STT",
				"\u0110\u01A1n v\u1ECB khai th\u00E1c", "Th\u00E1ng",
				"S\u1ED1 chuy\u1EBFn trong th\u00E1ng" };

		String[][] exportData = null;

		try {
			String searchQuery = generateQuery("search", portofAuthority,
					shipOperatorCode, companyName, routeDescription,
					scheduleYear >= 0 ? scheduleYear : null,
					scheduleMonth >= 0 ? scheduleMonth : null,
					voyageNumber >= 0 ? voyageNumber : null);

			String countQuery = generateQuery("count", portofAuthority,
					shipOperatorCode, companyName, routeDescription,
					scheduleYear >= 0 ? scheduleYear : null,
					scheduleMonth >= 0 ? scheduleMonth : null,
					voyageNumber >= 0 ? voyageNumber : null);

			JSONObject objects = VmaScheduleXlineLocalServiceUtil.findVmaScheduleXline(searchQuery, countQuery,
					-1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;
					companyName = object.getString("companyName");
					scheduleMonth = object.getInt("scheduleMonth");
					scheduleYear = object.getInt("scheduleYear");
					voyageNumber = object.getInt("voyageNumber");
					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = companyName;
					exportData[i][2] = String.valueOf(scheduleMonth);
					exportData[i][3] = String.valueOf(voyageNumber);

				}
			}

			String sheetName = "VMA-Schedule-Xline";

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
		 * vmaScheduleXlineId = GetterUtil.getLong(
		 * request.getParameter("vmaScheduleXlineId"), -1);
		 */
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String companyName = ParamUtil.getString(resourceRequest,
				"companyName", StringPool.BLANK);
		String routeDescription = ParamUtil.getString(resourceRequest,
				"routeDescription", StringPool.BLANK);
		int scheduleMonth = GetterUtil.getInteger(
				request.getParameter("scheduleMonth"), -1);
		int scheduleYear = GetterUtil.getInteger(
				request.getParameter("scheduleYear"), -1);
		int voyageNumber = GetterUtil.getInteger(
				request.getParameter("voyageNumber"), -1);
		/*
		 * String modifiedDate = ParamUtil.getString(resourceRequest,
		 * "modifiedDate", StringPool.BLANK);
		 */
		try {
			String countQuery = generateQuery("count", portofAuthority,
					shipOperatorCode, companyName, routeDescription,
					scheduleYear >= 0 ? scheduleYear : null,
					scheduleMonth >= 0 ? scheduleMonth : null,
					voyageNumber >= 0 ? voyageNumber : null);

			return VmaScheduleXlineLocalServiceUtil
					.countVmaScheduleXline(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());

			return 0;
		}
	}

	private static String generateQuery(String cmd, String portofAuthority,
			String shipOperatorCode, String companyName,
			String routeDescription, Integer scheduleYear,
			Integer scheduleMonth, Integer voyageNumber) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_schedule_xline as a";

		} else {
			sql = "SELECT a.* FROM vma_schedule_xline AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(companyName)) {
			condition.append(VMAUtils.buildSQLCondition("CompanyName", "'%"
					+ companyName + "%'", "AND", StringPool.LIKE));
		}

		if (voyageNumber != null) {
			condition.append(VMAUtils.buildSQLCondition("VoyageNumber",
					voyageNumber, "AND", StringPool.EQUAL));
		}

		if (scheduleMonth != null) {
			condition.append(VMAUtils.buildSQLCondition("ScheduleMonth",
					scheduleMonth, "AND", StringPool.EQUAL));
		}

		if (scheduleYear != null) {
			condition.append(VMAUtils.buildSQLCondition("ScheduleYear",
					scheduleYear, "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shipOperatorCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipOperatorCode", "'"
					+ shipOperatorCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(routeDescription)) {
			condition.append(VMAUtils.buildSQLCondition("RouteDescription",
					"'%" + routeDescription + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortofAuthority", "'"
					+ portofAuthority + "'", "AND", StringPool.EQUAL));
		}

		return sql + condition.toString();
	}
}
