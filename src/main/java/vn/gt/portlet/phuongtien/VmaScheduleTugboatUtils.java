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

import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboat;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList;



import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatLocalServiceUtil;
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
public class VmaScheduleTugboatUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaScheduleTugboatId = ParamUtil.getLong(request,
				"vmaScheduleTugboatId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {

			VmaScheduleTugboat vmaScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
					.getVmaScheduleTugboat(vmaScheduleTugboatId);

			List<VmaScheduleTugboatList> vmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
					.findByItineraryNo_SequenceNo(
							vmaScheduleTugboat.getItineraryNo(),
							vmaScheduleTugboat.getSequenceNo());

			JSONArray tugboatList = JSONFactoryUtil.createJSONArray();

			if (vmaScheduleTugboatLists != null) {
				for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
					tugboatList.put(VMAUtils.object2Json(
							vmaScheduleTugboatList,
							VmaScheduleTugboatList.class));

				}
			}

			result = VMAUtils.object2Json(vmaScheduleTugboat,
					VmaScheduleTugboat.class);

			result.put("tugboatList", tugboatList);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo sequenceNo portofAuthority nameOfShip certificateNo
	 * noticeShipType tugDateFrom tugDateTo anchoringPortHarbourCode
	 * anchoringPortWharfCode shiftingPortRegionCode shiftingPortHarbourCode
	 * shiftingPortWharfCode modifiedDate
	 */
	public static VmaScheduleTugboat getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		HttpServletRequest request = actionRequest;

		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleTugboatId"), -1);

		VmaScheduleTugboat vmaScheduleTugboat = null;
		if (id > 0) {
			try {
				vmaScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
						.getVmaScheduleTugboat(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleTugboat = new VmaScheduleTugboat();
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleTugboat.setItineraryNo(itineraryNo);
		}
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1); if (sequenceNo >= 0) {
		 * vmaScheduleTugboat.setSequenceNo(sequenceNo); }
		 */
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleTugboat.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleTugboat.setNameOfShip(nameOfShip);
		}
		String certificateNo = ParamUtil.getString(actionRequest,
				"certificateNo", StringPool.BLANK);
		if (Validator.isNotNull(certificateNo)) {
			vmaScheduleTugboat.setCertificateNo(certificateNo);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaScheduleTugboat.setNoticeShipType(noticeShipType);
		}
		String tugDateFrom = ParamUtil.getString(actionRequest, "tugDateFrom",
				StringPool.BLANK);
		if (Validator.isNotNull(tugDateFrom)) {
			try {
				Date date = FormatData.formatDateShort3.parse(tugDateFrom);
				vmaScheduleTugboat.setTugDateFrom(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String tugDateTo = ParamUtil.getString(actionRequest, "tugDateTo",
				StringPool.BLANK);
		if (Validator.isNotNull(tugDateTo)) {
			try {
				Date date = FormatData.formatDateShort3.parse(tugDateTo);
				vmaScheduleTugboat.setTugDateTo(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String anchoringPortHarbourCode = ParamUtil.getString(actionRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			vmaScheduleTugboat
					.setAnchoringPortHarbourCode(anchoringPortHarbourCode);
		}
		String anchoringPortWharfCode = ParamUtil.getString(actionRequest,
				"anchoringPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortWharfCode)) {
			vmaScheduleTugboat
					.setAnchoringPortWharfCode(anchoringPortWharfCode);
		}
		String shiftingPortRegionCode = ParamUtil.getString(actionRequest,
				"shiftingPortRegionCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortRegionCode)) {
			vmaScheduleTugboat
					.setShiftingPortRegionCode(shiftingPortRegionCode);
		}
		String shiftingPortHarbourCode = ParamUtil.getString(actionRequest,
				"shiftingPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortHarbourCode)) {
			vmaScheduleTugboat
					.setShiftingPortHarbourCode(shiftingPortHarbourCode);
		}
		String shiftingPortWharfCode = ParamUtil.getString(actionRequest,
				"shiftingPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortWharfCode)) {
			vmaScheduleTugboat.setShiftingPortWharfCode(shiftingPortWharfCode);
		}
		/*
		 * String modifiedDate = ParamUtil.getString(actionRequest,
		 * "modifiedDate", StringPool.BLANK); if
		 * (Validator.isNotNull(modifiedDate)) { try { Date date =
		 * FormatData.formatDateShort3.parse(modifiedDate);
		 * vmaScheduleTugboat.setModifiedDate(date); } catch (ParseException e)
		 * { log.error(e.getMessage()); } }
		 */
		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);
		if (itineraryScheduleId >= 0) {
			vmaScheduleTugboat.setItineraryScheduleId(itineraryScheduleId);
		}

		return vmaScheduleTugboat;
	}

	@Deprecated
	public static JSONObject addVmaScheduleTugboat(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleTugboat vmaScheduleTugboat = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleTugboat == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
					.addVmaScheduleTugboat(vmaScheduleTugboat);
			result = VMAUtils.object2Json(vmaScheduleTugboat,
					VmaScheduleTugboat.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject addVmaScheduleTugboat_VmaScheduleTugboatList(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaScheduleTugboat vmaScheduleTugboat = getObjectFromRequest(
				themeDisplay, actionRequest);

		List<VmaScheduleTugboatList> vmaScheduleTugboatLists = null;
		
		try {
			vmaScheduleTugboatLists = VmaScheduleTugboatListUtils
					.getObjectsFromRequest(themeDisplay, actionRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (vmaScheduleTugboat == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			result = VmaScheduleTugboatLocalServiceUtil
					.addVmaScheduleTugboat_VmaScheduleTugboatLists(
							vmaScheduleTugboat, vmaScheduleTugboatLists);

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleTugboat_VmaScheduleTugboatList(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaScheduleTugboat vmaScheduleTugboat = getObjectFromRequest(
				themeDisplay, actionRequest);

		List<VmaScheduleTugboatList> vmaScheduleTugboatLists = null;

		try {
			vmaScheduleTugboatLists = VmaScheduleTugboatListUtils
					.getObjectsFromRequest(themeDisplay, actionRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (vmaScheduleTugboat == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			result = VmaScheduleTugboatLocalServiceUtil
					.updateVmaScheduleTugboat_VmaScheduleTugboatLists(
							vmaScheduleTugboat, vmaScheduleTugboatLists);

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleTugboat(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleTugboat vmaScheduleTugboat = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleTugboat == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
					.updateVmaScheduleTugboat(vmaScheduleTugboat);
			result = VMAUtils.object2Json(vmaScheduleTugboat,
					VmaScheduleTugboat.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleTugboat(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaScheduleTugboatId");
		if (id > 0) {
			try {
				VmaScheduleTugboatLocalServiceUtil
						.deleteVmaScheduleTugboat_VmaScheduleTugboatList(id);
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
		String nameOfShip = VMAUtils.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);

		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String tugDateFrom = ParamUtil.getString(resourceRequest,
				"tugDateFrom", StringPool.BLANK);
		String tugDateTo = ParamUtil.getString(resourceRequest, "tugDateTo",
				StringPool.BLANK);
		String anchoringPortHarbourCode = ParamUtil.getString(resourceRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		String anchoringPortWharfCode = ParamUtil.getString(resourceRequest,
				"anchoringPortWharfCode", StringPool.BLANK);
		String shiftingPortRegionCode = ParamUtil.getString(resourceRequest,
				"shiftingPortRegionCode", StringPool.BLANK);
		String shiftingPortHarbourCode = ParamUtil.getString(resourceRequest,
				"shiftingPortHarbourCode", StringPool.BLANK);
		String shiftingPortWharfCode = ParamUtil.getString(resourceRequest,
				"shiftingPortWharfCode", StringPool.BLANK);

		try {
			String searchQuery = generateQuery("search", itineraryNo,
					nameOfShip, noticeShipType >= 0 ? noticeShipType : null,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, tugDateFrom, tugDateTo);

			String countQuery = generateQuery("count", itineraryNo, nameOfShip,
					noticeShipType >= 0 ? noticeShipType : null,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, tugDateFrom, tugDateTo);

			return VmaScheduleTugboatLocalServiceUtil.findScheduleTugboat(
					searchQuery, countQuery, start, end);
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
		String nameOfShip = VMAUtils.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String tugDateFrom = ParamUtil.getString(resourceRequest,
				"tugDateFrom", StringPool.BLANK);
		String tugDateTo = ParamUtil.getString(resourceRequest, "tugDateTo",
				StringPool.BLANK);
		String anchoringPortHarbourCode = ParamUtil.getString(resourceRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		String anchoringPortWharfCode = ParamUtil.getString(resourceRequest,
				"anchoringPortWharfCode", StringPool.BLANK);
		String shiftingPortRegionCode = ParamUtil.getString(resourceRequest,
				"shiftingPortRegionCode", StringPool.BLANK);
		String shiftingPortHarbourCode = ParamUtil.getString(resourceRequest,
				"shiftingPortHarbourCode", StringPool.BLANK);
		String shiftingPortWharfCode = ParamUtil.getString(resourceRequest,
				"shiftingPortWharfCode", StringPool.BLANK);

		try {
			String countQuery = generateQuery("count", itineraryNo, nameOfShip,
					noticeShipType >= 0 ? noticeShipType : null,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, tugDateFrom, tugDateTo);

			return VmaScheduleTugboatLocalServiceUtil
					.countVmaScheduleTugboat(countQuery);
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

		String nameOfShip = VMAUtils.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);

		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String tugDateFrom = ParamUtil.getString(resourceRequest,
				"tugDateFrom", StringPool.BLANK);
		String tugDateTo = ParamUtil.getString(resourceRequest, "tugDateTo",
				StringPool.BLANK);
		String anchoringPortHarbourCode = ParamUtil.getString(resourceRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		String anchoringPortWharfCode = ParamUtil.getString(resourceRequest,
				"anchoringPortWharfCode", StringPool.BLANK);
		String shiftingPortRegionCode = ParamUtil.getString(resourceRequest,
				"shiftingPortRegionCode", StringPool.BLANK);
		String shiftingPortHarbourCode = ParamUtil.getString(resourceRequest,
				"shiftingPortHarbourCode", StringPool.BLANK);
		String shiftingPortWharfCode = ParamUtil.getString(resourceRequest,
				"shiftingPortWharfCode", StringPool.BLANK);

		String[] headers = new String[] { "STT", "T\u00E0u", "L\u01B0\u1EE3t",
				"B\u1EBFn c\u1EA3ng", "V\u1ECB tr\u00ED",
				"Th\u1EDDi gian b\u1EAFt \u0111\u1EA7u",
				"Th\u1EDDi gian k\u1EBFt th\u00FAc" };

		String[][] exportData = null;

		try {
			String searchQuery = generateQuery("search", itineraryNo,
					nameOfShip, noticeShipType >= 0 ? noticeShipType : null,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, tugDateFrom, tugDateTo);

			String countQuery = generateQuery("count", itineraryNo, nameOfShip,
					noticeShipType >= 0 ? noticeShipType : null,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, tugDateFrom, tugDateTo);

			JSONObject objects = VmaScheduleTugboatLocalServiceUtil
					.findScheduleTugboat(searchQuery, countQuery, -1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");

					noticeShipType = object.getInt("noticeShipType");

					String noticeShipTypeName = VMAUtils
							.getNoticeShipTypeName(noticeShipType);

					anchoringPortHarbourCode = object
							.getString("anchoringPortHarbourCode");

					DmPortHarbour portHarbour = DmPortHarbourLocalServiceUtil
							.getByPortHarbourCode(anchoringPortHarbourCode);

					String anchoringPortHarbourName = portHarbour != null ? portHarbour
							.getPortHarbourNameVN() : StringPool.BLANK;

					anchoringPortWharfCode = object
							.getString("anchoringPortWharfCode");

					DmPortWharf portWharf = DmPortWharfLocalServiceUtil
							.getByPortWharfCode(anchoringPortWharfCode);

					String anchoringPortWharfName = portWharf != null ? portWharf
							.getPortWharfNameVN() : StringPool.BLANK;

					tugDateFrom = object.getString("tugDateFrom");

					tugDateTo = object.getString("tugDateTo");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = noticeShipTypeName;
					exportData[i][3] = anchoringPortHarbourName;
					exportData[i][4] = anchoringPortWharfName;
					exportData[i][5] = tugDateFrom;
					exportData[i][6] = tugDateTo;
				}
			}

			String sheetName = "VMA-Schedule-Tugboat";

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
			String nameOfShip, Integer noticeShipType,
			String anchoringPortHarbourCode, String anchoringPortWharfCode,
			String shiftingPortRegionCode, String shiftingPortHarbourCode,
			String shiftingPortWharfCode, String tugDateFrom, String tugDateTo) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_schedule_tugboat as a";
		} else {
			sql = "SELECT a.* FROM vma_schedule_tugboat AS a";
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

		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"AnchoringPortHarbourCode", "'" + anchoringPortHarbourCode
							+ "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(anchoringPortWharfCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"AnchoringPortWharfCode", "'" + anchoringPortWharfCode
							+ "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shiftingPortRegionCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShiftingPortRegionCode", "'" + shiftingPortRegionCode
							+ "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shiftingPortHarbourCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShiftingPortHarbourCode", "'" + shiftingPortHarbourCode
							+ "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shiftingPortWharfCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShiftingPortWharfCode", "'" + shiftingPortWharfCode + "'",
					"AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(tugDateFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(tugDateFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("TugDateFrom", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(tugDateTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(tugDateTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("TugDateTo", "'"
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

	private static String generateQuery2(String cmd, String itineraryNo,
			String nameOfShip, Integer noticeShipType,
			String anchoringPortHarbourCode, String anchoringPortWharfCode,
			String shiftingPortRegionCode, String shiftingPortHarbourCode,
			String shiftingPortWharfCode, String tugDateFrom, String tugDateTo) {

		String sql = StringPool.BLANK;
		StringBuilder query = new StringBuilder();

		if (cmd.equals("count")) {
			query.append("SELECT count(*) AS total");
			query.append(" FROM vma_schedule_tugboat AS A1");
			query.append(" INNER JOIN vma_schedule_tugboat_list AS A2");
			query.append(" ON A1.ItineraryNo = A2.ItineraryNo");
		} else {
			query.append("SELECT A1.ItineraryNo,");
			query.append("A1.NameOfShip,");
			query.append("A1.CertificateNo,");
			query.append("A1.NoticeShipType,");
			query.append("A1.AnchoringPortHarbourCode,");
			query.append("A1.AnchoringPortWharfCode,");
			query.append("A1.ShiftingPortRegionCode,");
			query.append("A1.ShiftingPortHarbourCode,");
			query.append("A1.ShiftingPortWharfCode,");

			query.append("A2.TugboatCompanyCode,");
			query.append("A2.TugboatCompanyName,");
			query.append("A2.ShipCode,");
			query.append("A2.ShipName,");
			query.append("A2.Power,");
			query.append("A2.UnitPower,");
			query.append("A2.PortofAuthority");

			query.append(" FROM vma_schedule_tugboat AS A1");
			query.append(" INNER JOIN vma_schedule_tugboat_list AS A2");
			query.append(" ON A1.ItineraryNo = A2.ItineraryNo");
		}

		StringBuilder condition = new StringBuilder();

		condition.append(query);
		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("NameOfShip", "'%"
					+ nameOfShip + "%'", "AND", StringPool.LIKE));
		}

		if (noticeShipType != null) {
			condition.append(VMAUtils.buildSQLCondition("NoticeShipType",
					noticeShipType, "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"AnchoringPortHarbourCode", "'" + anchoringPortHarbourCode
							+ "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(anchoringPortWharfCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"AnchoringPortWharfCode", "'" + anchoringPortWharfCode
							+ "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shiftingPortRegionCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShiftingPortRegionCode", "'" + shiftingPortRegionCode
							+ "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shiftingPortHarbourCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShiftingPortHarbourCode", "'" + shiftingPortHarbourCode
							+ "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shiftingPortWharfCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShiftingPortWharfCode", "'" + shiftingPortWharfCode + "'",
					"AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(tugDateFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(tugDateFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("TugDateFrom", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(tugDateTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(tugDateTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("TugDateTo", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("A1.ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL));
		}

		return sql + condition.toString();
	}
}
