package vn.gt.portlet.phuongtien;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing;


import vn.gt.dao.noticeandmessage.service.VmaScheduleXlineSailingLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.dao.orm.QueryUtil;
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
public class VmaScheduleXlineSailingUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaScheduleXlineSailingId = ParamUtil.getLong(request,
				"vmaScheduleXlineSailingId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaScheduleXlineSailing vmaScheduleXlineSailing = VmaScheduleXlineSailingLocalServiceUtil
					.getVmaScheduleXlineSailing(vmaScheduleXlineSailingId);
			result = VMAUtils.object2Json(vmaScheduleXlineSailing,
					VmaScheduleXlineSailing.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id portofAuthority shipOperatorCode scheduleMonth sequenceNo nameOfShip
	 * imoNumber callSign registryNumber voyageNo timeOfArrival timeOfDeparture
	 * stateCode provinceCode maritimePortCode portGoingToStateName
	 * portGoingToCode
	 */
	public static VmaScheduleXlineSailing getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleXlineSailingId"), -1);
		VmaScheduleXlineSailing vmaScheduleXlineSailing = null;
		if (id > 0) {
			try {
				vmaScheduleXlineSailing = VmaScheduleXlineSailingLocalServiceUtil
						.getVmaScheduleXlineSailing(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleXlineSailing = new VmaScheduleXlineSailing();
		}
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleXlineSailing.setPortofAuthority(portofAuthority);
		}
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaScheduleXlineSailing.setShipOperatorCode(shipOperatorCode);
		}
		int scheduleMonth = GetterUtil.getInteger(
				request.getParameter("scheduleMonth"), -1);
		if (scheduleMonth >= 0) {
			vmaScheduleXlineSailing.setScheduleMonth(scheduleMonth);
		}
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1); if (sequenceNo >= 0) {
		 * vmaScheduleXlineSailing.setSequenceNo(sequenceNo); }
		 */
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleXlineSailing.setNameOfShip(nameOfShip);
		}
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
				StringPool.BLANK);
		if (Validator.isNotNull(imoNumber)) {
			vmaScheduleXlineSailing.setImoNumber(imoNumber);
		}
		String callSign = VMAUtils.getString(actionRequest, "callSign",
				StringPool.BLANK);
		if (Validator.isNotNull(callSign)) {
			vmaScheduleXlineSailing.setCallSign(callSign);
		}
		String registryNumber = VMAUtils.getString(actionRequest,
				"registryNumber", StringPool.BLANK);
		if (Validator.isNotNull(registryNumber)) {
			vmaScheduleXlineSailing.setRegistryNumber(registryNumber);
		}
		String voyageNo = ParamUtil.getString(actionRequest, "voyageNo",
				StringPool.BLANK);
		if (Validator.isNotNull(voyageNo)) {
			vmaScheduleXlineSailing.setVoyageNo(voyageNo);
		}
		String timeOfArrival = ParamUtil.getString(actionRequest,
				"timeOfArrival", StringPool.BLANK);
		if (Validator.isNotNull(timeOfArrival)) {
			try {
				Date date = FormatData.formatDateShort3.parse(timeOfArrival);
				vmaScheduleXlineSailing.setTimeOfArrival(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String timeOfDeparture = ParamUtil.getString(actionRequest,
				"timeOfDeparture", StringPool.BLANK);
		if (Validator.isNotNull(timeOfDeparture)) {
			try {
				Date date = FormatData.formatDateShort3.parse(timeOfDeparture);
				vmaScheduleXlineSailing.setTimeOfDeparture(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String stateCode = ParamUtil.getString(actionRequest, "stateCode",
				StringPool.BLANK);
		if (Validator.isNotNull(stateCode)) {
			vmaScheduleXlineSailing.setStateCode(stateCode);
		}
		String provinceCode = ParamUtil.getString(actionRequest,
				"provinceCode", StringPool.BLANK);
		if (Validator.isNotNull(provinceCode)) {
			vmaScheduleXlineSailing.setProvinceCode(provinceCode);
		}
		String maritimePortCode = ParamUtil.getString(actionRequest,
				"maritimePortCode", StringPool.BLANK);
		if (Validator.isNotNull(maritimePortCode)) {
			vmaScheduleXlineSailing.setMaritimePortCode(maritimePortCode);
		}
		String portGoingToStateName = ParamUtil.getString(actionRequest,
				"portGoingToStateName", StringPool.BLANK);
		if (Validator.isNotNull(portGoingToStateName)) {
			vmaScheduleXlineSailing
					.setPortGoingToStateName(portGoingToStateName);
		}
		String portGoingToCode = ParamUtil.getString(actionRequest,
				"portGoingToCode", StringPool.BLANK);
		if (Validator.isNotNull(portGoingToCode)) {
			vmaScheduleXlineSailing.setPortGoingToCode(portGoingToCode);
		}

		int scheduleYear = GetterUtil.getInteger(
				request.getParameter("scheduleYear"), -1);
		if (scheduleYear >= 0) {
			vmaScheduleXlineSailing.setScheduleYear(scheduleYear);
		}

		return vmaScheduleXlineSailing;
	}

	public static JSONObject addVmaScheduleXlineSailing(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleXlineSailing vmaScheduleXlineSailing = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleXlineSailing == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleXlineSailing = VmaScheduleXlineSailingLocalServiceUtil
					.addVmaScheduleXlineSailing(vmaScheduleXlineSailing);
			result = VMAUtils.object2Json(vmaScheduleXlineSailing,
					VmaScheduleXlineSailing.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleXlineSailing(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleXlineSailing vmaScheduleXlineSailing = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleXlineSailing == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleXlineSailing = VmaScheduleXlineSailingLocalServiceUtil
					.updateVmaScheduleXlineSailing(vmaScheduleXlineSailing);
			result = VMAUtils.object2Json(vmaScheduleXlineSailing,
					VmaScheduleXlineSailing.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleXlineSailing(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaScheduleXlineSailingId");
		if (id > 0) {
			try {
				VmaScheduleXlineSailingLocalServiceUtil
						.deleteVmaScheduleXlineSailing(id);
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
		long vmaScheduleXlineSailingId = GetterUtil.getLong(
				request.getParameter("vmaScheduleXlineSailingId"), -1);
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		if (portofAuthority.equals(StringPool.BLANK)) {
			portofAuthority = ParamUtil.getString(resourceRequest,
					"maritimeCode", StringPool.BLANK);
		}
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		int scheduleMonth = GetterUtil.getInteger(
				request.getParameter("scheduleMonth"), -1);
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1);
		 */
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);
		String voyageNo = ParamUtil.getString(resourceRequest, "voyageNo",
				StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);
		String stateCode = ParamUtil.getString(resourceRequest, "stateCode",
				StringPool.BLANK);
		String provinceCode = ParamUtil.getString(resourceRequest,
				"provinceCode", StringPool.BLANK);
		String maritimePortCode = ParamUtil.getString(resourceRequest,
				"maritimePortCode", StringPool.BLANK);
		String portGoingToStateName = ParamUtil.getString(resourceRequest,
				"portGoingToStateName", StringPool.BLANK);
		String portGoingToCode = ParamUtil.getString(resourceRequest,
				"portGoingToCode", StringPool.BLANK);
		try {
			List<VmaScheduleXlineSailing> vmaScheduleXlineSailings = null;
			long total = 0;
			try {
				vmaScheduleXlineSailings = VmaScheduleXlineSailingLocalServiceUtil
						.findVmaScheduleXlineSailings(portofAuthority,
								nameOfShip, imoNumber, callSign, null,
								voyageNo, stateCode, provinceCode,
								maritimePortCode, portGoingToStateName,
								portGoingToCode, start, end);
				total = VmaScheduleXlineSailingLocalServiceUtil
						.countVmaScheduleXlineSailings(portofAuthority,
								nameOfShip, imoNumber, callSign, null,
								voyageNo, stateCode, provinceCode,
								maritimePortCode, portGoingToStateName,
								portGoingToCode);
			} catch (Exception e) {

			}

			if (vmaScheduleXlineSailings == null || vmaScheduleXlineSailings.isEmpty()) {
				vmaScheduleXlineSailings = VmaScheduleXlineSailingLocalServiceUtil
						.findVmaScheduleXlineSailings(portofAuthority,
								nameOfShip, null, null, registryNumber,
								voyageNo, stateCode, provinceCode,
								maritimePortCode, portGoingToStateName,
								portGoingToCode, start, end);
				total = VmaScheduleXlineSailingLocalServiceUtil
						.countVmaScheduleXlineSailings(portofAuthority,
								nameOfShip, null, null, registryNumber,
								voyageNo, stateCode, provinceCode,
								maritimePortCode, portGoingToStateName,
								portGoingToCode);
			}
			JSONObject result = JSONFactoryUtil.createJSONObject();
			JSONArray data = JSONFactoryUtil.createJSONArray();
			for (VmaScheduleXlineSailing vmaScheduleXlineSailing : vmaScheduleXlineSailings) {
				JSONObject obj = VMAUtils.object2Json(vmaScheduleXlineSailing,
						VmaScheduleXlineSailing.class,
						new String[] { "shipOperatorName" });

				data.put(obj);
			}

			result.put("total", total);
			result.put("data", data);

			return result;
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
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);
		long vmaScheduleXlineSailingId = GetterUtil.getLong(
				request.getParameter("vmaScheduleXlineSailingId"), -1);
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		if (portofAuthority.equals(StringPool.BLANK)) {
			portofAuthority = ParamUtil.getString(resourceRequest,
					"maritimeCode", StringPool.BLANK);
		}
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		int scheduleMonth = GetterUtil.getInteger(
				request.getParameter("scheduleMonth"), -1);
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1);
		 */
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);
		String voyageNo = ParamUtil.getString(resourceRequest, "voyageNo",
				StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);
		String stateCode = ParamUtil.getString(resourceRequest, "stateCode",
				StringPool.BLANK);
		String provinceCode = ParamUtil.getString(resourceRequest,
				"provinceCode", StringPool.BLANK);
		String maritimePortCode = ParamUtil.getString(resourceRequest,
				"maritimePortCode", StringPool.BLANK);
		String portGoingToStateName = ParamUtil.getString(resourceRequest,
				"portGoingToStateName", StringPool.BLANK);
		String portGoingToCode = ParamUtil.getString(resourceRequest,
				"portGoingToCode", StringPool.BLANK);
		try {
			long total = 0;
			try {
				total = VmaScheduleXlineSailingLocalServiceUtil
						.countVmaScheduleXlineSailings(portofAuthority,
								nameOfShip, imoNumber, callSign, null,
								voyageNo, stateCode, provinceCode,
								maritimePortCode, portGoingToStateName,
								portGoingToCode);
			} catch (Exception e) {

			}
			if (total == 0) {
				total = VmaScheduleXlineSailingLocalServiceUtil
						.countVmaScheduleXlineSailings(portofAuthority,
								nameOfShip, null, null, registryNumber,
								voyageNo, stateCode, provinceCode,
								maritimePortCode, portGoingToStateName,
								portGoingToCode);
			}

			return total;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	public static void doExport(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaScheduleXlineSailingId = GetterUtil.getLong(
				request.getParameter("vmaScheduleXlineSailingId"), -1);
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		if (portofAuthority.equals(StringPool.BLANK)) {
			portofAuthority = ParamUtil.getString(resourceRequest,
					"maritimeCode", StringPool.BLANK);
		}
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		int scheduleMonth = GetterUtil.getInteger(
				request.getParameter("scheduleMonth"), -1);
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1);
		 */
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);
		String voyageNo = ParamUtil.getString(resourceRequest, "voyageNo",
				StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);
		String stateCode = ParamUtil.getString(resourceRequest, "stateCode",
				StringPool.BLANK);
		String provinceCode = ParamUtil.getString(resourceRequest,
				"provinceCode", StringPool.BLANK);
		String maritimePortCode = ParamUtil.getString(resourceRequest,
				"maritimePortCode", StringPool.BLANK);
		String portGoingToStateName = ParamUtil.getString(resourceRequest,
				"portGoingToStateName", StringPool.BLANK);
		String portGoingToCode = ParamUtil.getString(resourceRequest,
				"portGoingToCode", StringPool.BLANK);

		// Loi font
		String[] headers = new String[] { "STT", "Mã người khai thác",
				"Kế hoạch năm", "Kế hoạch tháng", "Số thứ tự các lần chạy",
				"Mã cảng vụ", "Tên tàu", "Số IMO", "Hô hiệu",
				"Số đăng ký hành chính", "Số chuyến",
				"Thời gian đến dự kiến (ETA)", "Thời gian rời dự kiến (ETD)",
				"Mã quốc gia cảng bến cuối cùng",
				"Mã tỉnh thành cảng bến cuối cùng",
				"Ma cang bien hang hai (Cảng,bến rời cuối cùng)",
				"Cảng đến tiếp theo (tên quốc gia)", "Cảng đến tiếp theo",
				"Thời điểm cập nhật" };

		String[][] exportData = null;

		try {

			List<VmaScheduleXlineSailing> vmaScheduleXlineSailings = null;
			try {
				vmaScheduleXlineSailings = VmaScheduleXlineSailingLocalServiceUtil
						.findVmaScheduleXlineSailings(portofAuthority,
								nameOfShip, imoNumber, callSign, null,
								voyageNo, stateCode, provinceCode,
								maritimePortCode, portGoingToStateName,
								portGoingToCode, QueryUtil.ALL_POS,
								QueryUtil.ALL_POS);
			} catch (Exception e) {

			}
			if (vmaScheduleXlineSailings == null || vmaScheduleXlineSailings.isEmpty()) {
				vmaScheduleXlineSailings = VmaScheduleXlineSailingLocalServiceUtil
						.findVmaScheduleXlineSailings(portofAuthority,
								nameOfShip, null, null, registryNumber,
								voyageNo, stateCode, provinceCode,
								maritimePortCode, portGoingToStateName,
								portGoingToCode, QueryUtil.ALL_POS,
								QueryUtil.ALL_POS);
			}
			JSONObject objects = JSONFactoryUtil.createJSONObject();
			JSONArray array = JSONFactoryUtil.createJSONArray();
			for (VmaScheduleXlineSailing vmaScheduleXlineSailing : vmaScheduleXlineSailings) {
				JSONObject obj = VMAUtils.object2Json(vmaScheduleXlineSailing,
						VmaScheduleXlineSailing.class);

				array.put(obj);
			}
			objects.put("data", array);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;
					shipOperatorCode = object.getString("shipOperatorCode");
					int scheduleYear = object.getInt("scheduleYear");
					scheduleMonth = object.getInt("scheduleMonth");
					int sequenceNo = object.getInt("sequenceNo");
					portofAuthority = object.getString("portofAuthority");
					nameOfShip = object.getString("nameOfShip");
					imoNumber = object.getString("imoNumber");
					callSign = object.getString("callSign");
					registryNumber = object.getString("registryNumber");
					voyageNo = object.getString("voyageNo");
					timeOfArrival = object.getString("timeOfArrival");
					timeOfDeparture = object.getString("timeOfDeparture");
					stateCode = object.getString("stateCode");
					provinceCode = object.getString("provinceCode");
					maritimePortCode = object.getString("maritimePortCode");
					portGoingToStateName = object
							.getString("portGoingToStateName");
					portGoingToCode = object.getString("portGoingToCode");
					String modifiedDate = object.getString("modifiedDate");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = shipOperatorCode;
					exportData[i][2] = String.valueOf(scheduleYear);
					exportData[i][3] = String.valueOf(scheduleMonth);
					exportData[i][4] = String.valueOf(sequenceNo);
					exportData[i][5] = portofAuthority;
					exportData[i][6] = nameOfShip;
					exportData[i][7] = imoNumber;
					exportData[i][8] = callSign;
					exportData[i][9] = registryNumber;
					exportData[i][10] = voyageNo;
					exportData[i][11] = timeOfArrival;
					exportData[i][12] = timeOfDeparture;
					exportData[i][13] = stateCode;
					exportData[i][14] = provinceCode;
					exportData[i][15] = maritimePortCode;
					exportData[i][16] = portGoingToStateName;
					exportData[i][17] = modifiedDate;
				}
			}

			String sheetName = "VMA-Schedule-Xline-Sailing";

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
}
