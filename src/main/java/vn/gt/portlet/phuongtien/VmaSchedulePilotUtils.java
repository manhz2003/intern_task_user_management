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
import com.fds.nsw.nghiepvu.model.VmaSchedulePilot;
import com.fds.nsw.nghiepvu.model.VmaSchedulePilotList;



import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotLocalServiceUtil;
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
public class VmaSchedulePilotUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		long vmaSchedulePilotId = ParamUtil.getLong(request,
				"vmaSchedulePilotId");

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			VmaSchedulePilot vmaSchedulePilot = VmaSchedulePilotLocalServiceUtil
					.getVmaSchedulePilot(vmaSchedulePilotId);

			List<VmaSchedulePilotList> vmaSchedulePilotLists = VmaSchedulePilotListLocalServiceUtil
					.findByItineraryNo_SequenceNo(
							vmaSchedulePilot.getItineraryNo(),
							vmaSchedulePilot.getSequenceNo());

			JSONArray pilotList = JSONFactoryUtil.createJSONArray();

			if (vmaSchedulePilotLists != null) {
				for (VmaSchedulePilotList vmaSchedulePilotList : vmaSchedulePilotLists) {
					pilotList.put(VMAUtils.object2Json(vmaSchedulePilotList,
							VmaSchedulePilotList.class));

				}
			}

			result = VMAUtils.object2Json(vmaSchedulePilot,
					VmaSchedulePilot.class);

			result.put("pilotList", pilotList);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo sequenceNo portofAuthority nameOfShip certificateNo
	 * noticeShipType pilotDateFrom pilotDateTo anchoringPortHarbourCode
	 * anchoringPortWharfCode shiftingPortRegionCode shiftingPortHarbourCode
	 * shiftingPortWharfCode modifiedDate
	 */
	public static VmaSchedulePilot getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		HttpServletRequest request = actionRequest;

		long id = GetterUtil.getLong(
				request.getParameter("vmaSchedulePilotId"), -1);

		VmaSchedulePilot vmaSchedulePilot = null;

		if (id > 0) {

			try {
				vmaSchedulePilot = VmaSchedulePilotLocalServiceUtil
						.getVmaSchedulePilot(id);

			} catch (Exception e) {
				return null;
			}
		} else {
			vmaSchedulePilot = new VmaSchedulePilot();
		}

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		if (Validator.isNotNull(itineraryNo)) {
			vmaSchedulePilot.setItineraryNo(itineraryNo);
		}

		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaSchedulePilot.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaSchedulePilot.setNameOfShip(nameOfShip);
		}
		String certificateNo = ParamUtil.getString(actionRequest,
				"certificateNo", StringPool.BLANK);
		if (Validator.isNotNull(certificateNo)) {
			vmaSchedulePilot.setCertificateNo(certificateNo);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaSchedulePilot.setNoticeShipType(noticeShipType);
		}
		String pilotDateFrom = ParamUtil.getString(actionRequest,
				"pilotDateFrom", StringPool.BLANK);
		if (Validator.isNotNull(pilotDateFrom)) {
			try {
				Date date = FormatData.formatDateShort3.parse(pilotDateFrom);
				vmaSchedulePilot.setPilotDateFrom(date);
			} catch (ParseException e) {
				
			}
		}
		String pilotDateTo = ParamUtil.getString(actionRequest, "pilotDateTo",
				StringPool.BLANK);
		if (Validator.isNotNull(pilotDateTo)) {
			try {
				Date date = FormatData.formatDateShort3.parse(pilotDateTo);
				vmaSchedulePilot.setPilotDateTo(date);
			} catch (ParseException e) {
				
			}
		}
		String anchoringPortHarbourCode = ParamUtil.getString(actionRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			vmaSchedulePilot
					.setAnchoringPortHarbourCode(anchoringPortHarbourCode);
		}
		String anchoringPortWharfCode = ParamUtil.getString(actionRequest,
				"anchoringPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortWharfCode)) {
			vmaSchedulePilot.setAnchoringPortWharfCode(anchoringPortWharfCode);
		}
		String shiftingPortRegionCode = ParamUtil.getString(actionRequest,
				"shiftingPortRegionCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortRegionCode)) {
			vmaSchedulePilot.setShiftingPortRegionCode(shiftingPortRegionCode);
		}
		String shiftingPortHarbourCode = ParamUtil.getString(actionRequest,
				"shiftingPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortHarbourCode)) {
			vmaSchedulePilot
					.setShiftingPortHarbourCode(shiftingPortHarbourCode);
		}
		String shiftingPortWharfCode = ParamUtil.getString(actionRequest,
				"shiftingPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortWharfCode)) {
			vmaSchedulePilot.setShiftingPortWharfCode(shiftingPortWharfCode);
		}
		/*
		 * String modifiedDate = ParamUtil.getString(actionRequest,
		 * "modifiedDate", StringPool.BLANK); if
		 * (Validator.isNotNull(modifiedDate)) { try { Date date =
		 * FormatData.formatDateShort3.parse(modifiedDate);
		 * vmaSchedulePilot.setModifiedDate(date); } catch (ParseException e) {
		 * log.error(e.getMessage()); } }
		 */

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);
		if (itineraryScheduleId >= 0) {
			vmaSchedulePilot.setItineraryScheduleId(itineraryScheduleId);
		}

		return vmaSchedulePilot;
	}

	@Deprecated
	public static JSONObject addVmaSchedulePilot(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaSchedulePilot vmaSchedulePilot = getObjectFromRequest(themeDisplay,
				actionRequest);
		if (vmaSchedulePilot == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaSchedulePilot = VmaSchedulePilotLocalServiceUtil
					.addVmaSchedulePilot(vmaSchedulePilot);
			result = VMAUtils.object2Json(vmaSchedulePilot,
					VmaSchedulePilot.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject addVmaSchedulePilot_VmaSchedulePilotList(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaSchedulePilot vmaSchedulePilot = getObjectFromRequest(themeDisplay,
				actionRequest);

		List<VmaSchedulePilotList> vmaSchedulePilotLists = null;

		try {
			vmaSchedulePilotLists = VmaSchedulePilotListUtils
					.getObjectsFromRequest(themeDisplay, actionRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (vmaSchedulePilot == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			result = VmaSchedulePilotLocalServiceUtil
					.addVmaSchedulePilot_VmaSchedulePilotLists(
							vmaSchedulePilot, vmaSchedulePilotLists);

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaSchedulePilot_VmaSchedulePilotList(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaSchedulePilot vmaSchedulePilot = getObjectFromRequest(themeDisplay,
				actionRequest);

		List<VmaSchedulePilotList> vmaSchedulePilotLists = null;

		try {
			vmaSchedulePilotLists = VmaSchedulePilotListUtils
					.getObjectsFromRequest(themeDisplay, actionRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (vmaSchedulePilot == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			result = VmaSchedulePilotLocalServiceUtil
					.updateVmaSchedulePilot_VmaSchedulePilotLists(
							vmaSchedulePilot, vmaSchedulePilotLists);

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	@Deprecated
	public static JSONObject updateVmaSchedulePilot(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaSchedulePilot vmaSchedulePilot = getObjectFromRequest(themeDisplay,
				actionRequest);
		if (vmaSchedulePilot == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaSchedulePilot = VmaSchedulePilotLocalServiceUtil
					.updateVmaSchedulePilot(vmaSchedulePilot);
			result = VMAUtils.object2Json(vmaSchedulePilot,
					VmaSchedulePilot.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaSchedulePilot(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaSchedulePilotId");
		if (id > 0) {
			try {
				VmaSchedulePilotLocalServiceUtil.deleteVmaSchedulePilot(id);
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
		if (portofAuthority.equals(StringPool.BLANK)) {
			portofAuthority = ParamUtil.getString(resourceRequest,
					"maritimeCode", StringPool.BLANK);
		}
		String nameOfShip = VMAUtils.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String certificateNo = ParamUtil.getString(resourceRequest,
				"certificateNo", StringPool.BLANK);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String pilotDateFrom = ParamUtil.getString(resourceRequest,
				"pilotDateFrom", StringPool.BLANK);
		String pilotDateTo = ParamUtil.getString(resourceRequest,
				"pilotDateTo", StringPool.BLANK);
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
		long itineraryScheduleId = GetterUtil.getLong(request.getParameter("itineraryScheduleId"), -1);
		try {
			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip, certificateNo, noticeShipType,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, pilotDateFrom, pilotDateTo, itineraryScheduleId);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip, certificateNo, noticeShipType,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, pilotDateFrom, pilotDateTo, itineraryScheduleId);

			return VmaSchedulePilotLocalServiceUtil.findVmaSchedulePilot(
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
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String nameOfShip = VMAUtils.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String certificateNo = ParamUtil.getString(resourceRequest,
				"certificateNo", StringPool.BLANK);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String pilotDateFrom = ParamUtil.getString(resourceRequest,
				"pilotDateFrom", StringPool.BLANK);
		String pilotDateTo = ParamUtil.getString(resourceRequest,
				"pilotDateTo", StringPool.BLANK);
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
		long itineraryScheduleId = GetterUtil.getLong(request.getParameter("itineraryScheduleId"), -1);
		try {

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip, certificateNo, noticeShipType,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, pilotDateFrom, pilotDateTo, itineraryScheduleId);

			return VmaSchedulePilotLocalServiceUtil
					.countVmaSchedulePilot(countQuery);
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
		String nameOfShip = VMAUtils.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String certificateNo = ParamUtil.getString(resourceRequest,
				"certificateNo", StringPool.BLANK);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String pilotDateFrom = ParamUtil.getString(resourceRequest,
				"pilotDateFrom", StringPool.BLANK);
		String pilotDateTo = ParamUtil.getString(resourceRequest,
				"pilotDateTo", StringPool.BLANK);
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
		long itineraryScheduleId = GetterUtil.getLong(request.getParameter("itineraryScheduleId"), -1);

		/*
		 * String[] headers = new String[] { "STT", "Tàu", "Lượt", "Hoa tiêu",
		 * "Bến cảng lên ", "Vị trí lên tàu", "Bến cảng rời tàu",
		 * "Vị trí rời tàu", "Thời gian lên tàu", "Thời gian rời tàu" };
		 */

		String[] headers = new String[] { "STT", "T\u00E0u", "L\u01B0\u1EE3t",
				"Hoa ti\u00EAu", "B\u1EBFn c\u1EA3ng l\u00EAn ",
				"V\u1ECB tr\u00ED l\u00EAn t\u00E0u",
				"B\u1EBFn c\u1EA3ng r\u1EDDi t\u00E0u",
				"V\u1ECB tr\u00ED r\u1EDDi t\u00E0u",
				"Th\u1EDDi gian l\u00EAn t\u00E0u",
				"Th\u1EDDi gian r\u1EDDi t\u00E0u" };

		String[][] exportData = null;

		try {
			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, nameOfShip, certificateNo, noticeShipType,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, pilotDateFrom, pilotDateTo,itineraryScheduleId);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, nameOfShip, certificateNo, noticeShipType,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, pilotDateFrom, pilotDateTo,itineraryScheduleId);

			JSONObject objects = VmaSchedulePilotLocalServiceUtil
					.findVmaSchedulePilot(searchQuery, countQuery, -1, -1);

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
					JSONArray lstpilot = object.getJSONArray("pilotList");

					String pilotName = "";
					if (lstpilot.length() > 0) {
						for (int index = 0; index < lstpilot.length(); index++) {
							JSONObject pilotList = lstpilot
									.getJSONObject(index);
							pilotName = pilotName
									+ pilotList.getString("pilotName") + " - "
									+ pilotList.getString("pilotCompanyName")
									+ "\n";
						}
					}

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

					shiftingPortHarbourCode = object
							.getString("shiftingPortHarbourCode");

					portHarbour = DmPortHarbourLocalServiceUtil
							.getByPortHarbourCode(shiftingPortHarbourCode);

					String shiftingPortHarbourName = portHarbour != null ? portHarbour
							.getPortHarbourNameVN() : StringPool.BLANK;

					shiftingPortWharfCode = object
							.getString("shiftingPortWharfCode");

					portWharf = DmPortWharfLocalServiceUtil
							.getByPortWharfCode(shiftingPortWharfCode);

					String shiftingPortWharfName = portWharf != null ? portWharf
							.getPortWharfNameVN() : StringPool.BLANK;

					pilotDateFrom = object.getString("pilotDateFrom");

					pilotDateTo = object.getString("pilotDateTo");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = noticeShipTypeName;
					exportData[i][3] = pilotName;
					exportData[i][4] = anchoringPortHarbourName;
					exportData[i][5] = anchoringPortWharfName;

					exportData[i][6] = shiftingPortHarbourName;
					exportData[i][7] = shiftingPortWharfName;
					exportData[i][8] = pilotDateFrom;
					exportData[i][9] = pilotDateTo;
				}
			}

			String sheetName = "VMA-Schedule-Pilot";

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
			String portofAuthority, String nameOfShip, String certificateNo,
			Integer noticeShipType, String anchoringPortHarbourCode,
			String anchoringPortWharfCode, String shiftingPortRegionCode,
			String shiftingPortHarbourCode, String shiftingPortWharfCode,
			String pilotDateFrom, String pilotDateTo, Long itineraryScheduleId) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_schedule_pilot as a";
		} else {
			sql = "SELECT a.* FROM vma_schedule_pilot AS a";
		}
		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("NameOfShip", "'%"
					+ nameOfShip + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortOfAuthority", "'"
					+ portofAuthority + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(certificateNo)) {
			condition.append(VMAUtils.buildSQLCondition("CertificateNo", "'"
					+ certificateNo + "'", "AND", StringPool.EQUAL));
		}

		if (noticeShipType != null && noticeShipType > 0) {
			condition.append(VMAUtils.buildSQLCondition("NoticeShipType",
					noticeShipType, "AND", StringPool.EQUAL));
		}
		
		if (itineraryScheduleId != null && itineraryScheduleId > 0) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryScheduleId",
					itineraryScheduleId, "AND", StringPool.EQUAL));
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

		if (Validator.isNotNull(pilotDateFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(pilotDateFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("PilotDateFrom",
						"'" + strDate + " 00:00:00'" + " AND '" + strDate
								+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(pilotDateTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(pilotDateTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("PilotDateTo", "'"
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
