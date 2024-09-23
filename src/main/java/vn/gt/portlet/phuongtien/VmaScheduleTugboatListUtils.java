package vn.gt.portlet.phuongtien;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList;


import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatLocalServiceUtil;
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
public class VmaScheduleTugboatListUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaScheduleTugboatListId = ParamUtil.getLong(request,
				"vmaScheduleTugboatListId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaScheduleTugboatList vmaScheduleTugboatList = VmaScheduleTugboatListLocalServiceUtil
					.getVmaScheduleTugboatList(vmaScheduleTugboatListId);
			result = VMAUtils.object2Json(vmaScheduleTugboatList,
					VmaScheduleTugboatList.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo sequenceNo portofAuthority nameOfShip tugboatCompanyCode
	 * tugboatCompanyName shipCode shipName power modifiedDate
	 */
	public static VmaScheduleTugboatList getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleTugboatListId"), -1);
		VmaScheduleTugboatList vmaScheduleTugboatList = null;
		if (id > 0) {
			try {
				vmaScheduleTugboatList = VmaScheduleTugboatListLocalServiceUtil
						.getVmaScheduleTugboatList(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleTugboatList = new VmaScheduleTugboatList();
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleTugboatList.setItineraryNo(itineraryNo);
		}
		/*int sequenceNo = GetterUtil.getInteger(
				request.getParameter("sequenceNo"), -1);
		if (sequenceNo >= 0) {
			vmaScheduleTugboatList.setSequenceNo(sequenceNo);
		}*/
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleTugboatList.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleTugboatList.setNameOfShip(nameOfShip);
		}
		String tugboatCompanyCode = ParamUtil.getString(actionRequest,
				"tugboatCompanyCode", StringPool.BLANK);
		if (Validator.isNotNull(tugboatCompanyCode)) {
			vmaScheduleTugboatList.setTugboatCompanyCode(tugboatCompanyCode);
		}
		String tugboatCompanyName = VMAUtils.getString(actionRequest,
				"tugboatCompanyName", StringPool.BLANK);
		if (Validator.isNotNull(tugboatCompanyName)) {
			vmaScheduleTugboatList.setTugboatCompanyName(tugboatCompanyName);
		}
		String shipCode = ParamUtil.getString(actionRequest, "shipCode",
				StringPool.BLANK);
		if (Validator.isNotNull(shipCode)) {
			vmaScheduleTugboatList.setShipCode(shipCode);
		}
		String shipName = VMAUtils.getString(actionRequest, "shipName",
				StringPool.BLANK);
		if (Validator.isNotNull(shipName)) {
			vmaScheduleTugboatList.setShipName(shipName);
		}
		int power = GetterUtil.getInteger(request.getParameter("power"), -1);
		if (power >= 0) {
			vmaScheduleTugboatList.setPower((double) power);
		}
		/*String modifiedDate = ParamUtil.getString(actionRequest,
				"modifiedDate", StringPool.BLANK);
		if (Validator.isNotNull(modifiedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(modifiedDate);
				vmaScheduleTugboatList.setModifiedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}*/

		String documentaryCode = ParamUtil.getString(actionRequest,
				"documentaryCode", StringPool.BLANK);
		if (Validator.isNotNull(documentaryCode)) {
			vmaScheduleTugboatList.setDocumentaryCode(documentaryCode);
		}
		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);
		if (itineraryScheduleId >= 0) {
			vmaScheduleTugboatList.setItineraryScheduleId(itineraryScheduleId);
		}
		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);
		if (gt >= 0) {
			vmaScheduleTugboatList.setGt(BigDecimal.valueOf(gt));
		}
		String unitGRT = ParamUtil.getString(actionRequest, "unitGRT",
				StringPool.BLANK);
		if (Validator.isNotNull(unitGRT)) {
			vmaScheduleTugboatList.setUnitGRT(unitGRT);
		}
		String tugMode = ParamUtil.getString(actionRequest, "tugMode",
				StringPool.BLANK);
		if (Validator.isNotNull(tugMode)) {
			vmaScheduleTugboatList.setTugMode(tugMode);
		}
		String tugboatShortName = ParamUtil.getString(actionRequest,
				"tugboatShortName", StringPool.BLANK);
		if (Validator.isNotNull(tugboatShortName)) {
			vmaScheduleTugboatList.setTugboatShortName(tugboatShortName);
		}
		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);
		if (makePayment >= 0) {
			vmaScheduleTugboatList.setMakePayment(makePayment);
		}
		String invoiceDocumentaryCode = ParamUtil.getString(actionRequest,
				"invoiceDocumentaryCode", StringPool.BLANK);
		if (Validator.isNotNull(invoiceDocumentaryCode)) {
			vmaScheduleTugboatList
					.setInvoiceDocumentaryCode(invoiceDocumentaryCode);
		}

		String unitPower = ParamUtil.getString(actionRequest, "unitPower",
				StringPool.BLANK);
		if (Validator.isNotNull(unitPower)) {
			vmaScheduleTugboatList.setUnitPower(unitPower);
		}

		return vmaScheduleTugboatList;
	}

	public static List<VmaScheduleTugboatList> getObjectsFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
			throws JSONException {

		String tugboatList = ParamUtil.getString(actionRequest, "json",
				StringPool.BLANK);

		System.out.print("========>>>>>>>>>>>>>> JSON Data " + tugboatList);

		List<VmaScheduleTugboatList> vmaScheduleTugboatLists = new ArrayList<VmaScheduleTugboatList>();

		JSONArray array = null;

		if (Validator.isNotNull(tugboatList)) {
			array = JSONFactoryUtil.createJSONArray(tugboatList);
		}

		if (array != null) {

			for (int i = 0; i < array.length(); i++) {

				VmaScheduleTugboatList vmaScheduleTugboatList = new VmaScheduleTugboatList();

				JSONObject object = array.getJSONObject(i);

				if (object.has("itineraryNo")) {
					String itineraryNo = object.getString("itineraryNo");

					vmaScheduleTugboatList.setItineraryNo(itineraryNo);
				}

				/*if (object.has("sequenceNo")) {
					int sequenceNo = object.getInt("sequenceNo");

					vmaScheduleTugboatList.setSequenceNo(sequenceNo);
				}*/

				if (object.has("portofAuthority")) {
					String portofAuthority = object
							.getString("portofAuthority");

					vmaScheduleTugboatList.setPortofAuthority(portofAuthority);

				}

				if (object.has("nameOfShip")) {
					String nameOfShip = object.getString("nameOfShip");

					vmaScheduleTugboatList.setNameOfShip(DanhMucUtils
							.encodeUTF8(nameOfShip));

				}

				if (object.has("tugboatCompanyCode")) {
					String tugboatCompanyCode = object
							.getString("tugboatCompanyCode");

					vmaScheduleTugboatList
							.setTugboatCompanyCode(tugboatCompanyCode);
				}

				if (object.has("tugboatCompanyName")) {
					String tugboatCompanyName = object
							.getString("tugboatCompanyName");

					vmaScheduleTugboatList.setTugboatCompanyName(DanhMucUtils
							.encodeUTF8(tugboatCompanyName));
				}

				if (object.has("shipCode")) {
					String shipCode = object.getString("shipCode");

					vmaScheduleTugboatList.setShipCode(shipCode);
				}

				if (object.has("shipName")) {
					String shipName = object.getString("shipName");

					vmaScheduleTugboatList.setShipName(DanhMucUtils
							.encodeUTF8(shipName));

				}

				if (object.has("power")) {
					int power = object.getInt("power");

					vmaScheduleTugboatList.setPower((double) power);

				}

				String modifiedDate = object.getString("modifiedDate");

				if (Validator.isNotNull(modifiedDate)) {
					try {
						Date date = FormatData.formatDateShort3
								.parse(modifiedDate);
						vmaScheduleTugboatList.setModifiedDate(date);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}
				}

				if (object.has("documentaryCode")) {
					String documentaryCode = object
							.getString("documentaryCode");

					vmaScheduleTugboatList.setDocumentaryCode(documentaryCode);

				}

				if (object.has("itineraryScheduleId")) {
					long itineraryScheduleId = object
							.getLong("itineraryScheduleId");

					vmaScheduleTugboatList
							.setItineraryScheduleId(itineraryScheduleId);

				}

				if (object.has("gt")) {
					double gt = object.getDouble("gt");

					vmaScheduleTugboatList.setGt(BigDecimal.valueOf(gt));

				}

				if (object.has("unitGRT")) {
					String unitGRT = object.getString("unitGRT");

					vmaScheduleTugboatList.setUnitGRT(unitGRT);

				}

				if (object.has("tugMode")) {
					String tugMode = object.getString("tugMode");
					if (Validator.isNotNull(tugMode)) {
						vmaScheduleTugboatList.setTugMode(tugMode);
					}

				}

				if (object.has("tugboatShortName")) {

					String tugboatShortName = object
							.getString("tugboatShortName");
					if (Validator.isNotNull(tugboatShortName)) {
						vmaScheduleTugboatList
								.setTugboatShortName(tugboatShortName);
					}

				}

				if (object.has("makePayment")) {
					int makePayment = object.getInt("makePayment");
					vmaScheduleTugboatList.setMakePayment(makePayment);
				}

				if (object.has("invoiceDocumentaryCode")) {
					String invoiceDocumentaryCode = object
							.getString("invoiceDocumentaryCode");
					if (Validator.isNotNull(invoiceDocumentaryCode)) {
						vmaScheduleTugboatList
								.setInvoiceDocumentaryCode(invoiceDocumentaryCode);
					}
				}

				if (object.has("unitPower")) {

					String unitPower = object.getString("unitPower");
					if (Validator.isNotNull(unitPower)) {
						vmaScheduleTugboatList.setUnitPower(unitPower);
					}
				}

				vmaScheduleTugboatLists.add(vmaScheduleTugboatList);
			}
		}

		return vmaScheduleTugboatLists;
	}

	public static JSONObject addVmaScheduleTugboatList(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleTugboatList vmaScheduleTugboatList = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleTugboatList == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleTugboatList = VmaScheduleTugboatListLocalServiceUtil
					.addVmaScheduleTugboatList(vmaScheduleTugboatList);
			result = VMAUtils.object2Json(vmaScheduleTugboatList,
					VmaScheduleTugboatList.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleTugboatList(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleTugboatList vmaScheduleTugboatList = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleTugboatList == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleTugboatList = VmaScheduleTugboatListLocalServiceUtil
					.updateVmaScheduleTugboatList(vmaScheduleTugboatList);
			result = VMAUtils.object2Json(vmaScheduleTugboatList,
					VmaScheduleTugboatList.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleTugboatList(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaScheduleTugboatListId");
		if (id > 0) {
			try {
				VmaScheduleTugboatListLocalServiceUtil
						.deleteVmaScheduleTugboatList(id);
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
		long vmaScheduleTugboatListId = GetterUtil.getLong(
				request.getParameter("vmaScheduleTugboatListId"), -1);

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);

		/*int sequenceNo = GetterUtil.getInteger(
				request.getParameter("sequenceNo"), -1);*/

		String portofAuthority = ParamUtil.getString(request,
				"portofAuthority", StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(request, "nameOfShip",
				StringPool.BLANK);

		String tugboatCompanyCode = ParamUtil.getString(request,
				"tugboatCompanyCode", StringPool.BLANK);

		String tugboatCompanyName = ParamUtil.getString(request,
				"tugboatCompanyName", StringPool.BLANK);

		String shipCode = ParamUtil.getString(request, "shipCode",
				StringPool.BLANK);

		String shipName = ParamUtil.getString(request, "shipName",
				StringPool.BLANK);

		double power = GetterUtil.getDouble(request.getParameter("power"), -1);

		String unitPower = ParamUtil.getString(request, "unitPower",
				StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(request,
				"documentaryCode", StringPool.BLANK);

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);

		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);

		String unitGRT = ParamUtil.getString(request, "unitGRT",
				StringPool.BLANK);

		String tugMode = ParamUtil.getString(request, "tugMode",
				StringPool.BLANK);

		String tugboatShortName = ParamUtil.getString(request,
				"tugboatShortName", StringPool.BLANK);

		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);

		String invoiceDocumentaryCode = ParamUtil.getString(request,
				"invoiceDocumentaryCode", StringPool.BLANK);

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
					nameOfShip, portofAuthority,
					itineraryScheduleId >= 0 ? itineraryScheduleId : null,
					tugboatCompanyCode, tugboatCompanyName, shipCode, shipName,
					power >= 0 ? power : null, unitPower, gt >= 0 ? gt : null,
					unitGRT, tugMode, tugboatShortName,
					makePayment >= 0 ? makePayment : null, documentaryCode,
					invoiceDocumentaryCode,
					noticeShipType >= 0 ? noticeShipType : null,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, tugDateFrom, tugDateTo);

			String countQuery = generateQuery("count", itineraryNo, nameOfShip,
					portofAuthority,
					itineraryScheduleId >= 0 ? itineraryScheduleId : null,
					tugboatCompanyCode, tugboatCompanyName, shipCode, shipName,
					power >= 0 ? power : null, unitPower, gt >= 0 ? gt : null,
					unitGRT, tugMode, tugboatShortName,
					makePayment >= 0 ? makePayment : null, documentaryCode,
					invoiceDocumentaryCode,
					noticeShipType >= 0 ? noticeShipType : null,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, tugDateFrom, tugDateTo);

			return VmaScheduleTugboatListLocalServiceUtil
					.findScheduleTugboatList(getQueryColumnMap(), searchQuery,
							countQuery, start, end);
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

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);

		String portofAuthority = ParamUtil.getString(request,
				"portofAuthority", StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(request, "nameOfShip",
				StringPool.BLANK);

		String tugboatCompanyCode = ParamUtil.getString(request,
				"tugboatCompanyCode", StringPool.BLANK);

		String tugboatCompanyName = ParamUtil.getString(request,
				"tugboatCompanyName", StringPool.BLANK);

		String shipCode = ParamUtil.getString(request, "shipCode",
				StringPool.BLANK);

		String shipName = ParamUtil.getString(request, "shipName",
				StringPool.BLANK);

		double power = GetterUtil.getDouble(request.getParameter("power"), -1);

		String unitPower = ParamUtil.getString(request, "unitPower",
				StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(request,
				"documentaryCode", StringPool.BLANK);

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);

		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);

		String unitGRT = ParamUtil.getString(request, "unitGRT",
				StringPool.BLANK);

		String tugMode = ParamUtil.getString(request, "tugMode",
				StringPool.BLANK);

		String tugboatShortName = ParamUtil.getString(request,
				"tugboatShortName", StringPool.BLANK);

		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);

		String invoiceDocumentaryCode = ParamUtil.getString(request,
				"invoiceDocumentaryCode", StringPool.BLANK);

		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String tugDateFrom = ParamUtil.getString(request, "tugDateFrom",
				StringPool.BLANK);
		String tugDateTo = ParamUtil.getString(request, "tugDateTo",
				StringPool.BLANK);
		String anchoringPortHarbourCode = ParamUtil.getString(request,
				"anchoringPortHarbourCode", StringPool.BLANK);
		String anchoringPortWharfCode = ParamUtil.getString(request,
				"anchoringPortWharfCode", StringPool.BLANK);
		String shiftingPortRegionCode = ParamUtil.getString(request,
				"shiftingPortRegionCode", StringPool.BLANK);
		String shiftingPortHarbourCode = ParamUtil.getString(request,
				"shiftingPortHarbourCode", StringPool.BLANK);
		String shiftingPortWharfCode = ParamUtil.getString(request,
				"shiftingPortWharfCode", StringPool.BLANK);

		try {
			String countQuery = generateQuery("count", itineraryNo, nameOfShip,
					portofAuthority,
					itineraryScheduleId >= 0 ? itineraryScheduleId : null,
					tugboatCompanyCode, tugboatCompanyName, shipCode, shipName,
					power >= 0 ? power : null, unitPower, gt >= 0 ? gt : null,
					unitGRT, tugMode, tugboatShortName,
					makePayment >= 0 ? makePayment : null, documentaryCode,
					invoiceDocumentaryCode,
					noticeShipType >= 0 ? noticeShipType : null,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, tugDateFrom, tugDateTo);

			return VmaScheduleTugboatListLocalServiceUtil
					.countVmaScheduleTugboatList(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());

			return 0;
		}
	}

	private static LinkedHashMap<String, Class<?>> getQueryColumnMap() {
		LinkedHashMap<String, Class<?>> map = new LinkedHashMap<String, Class<?>>();

		map.put("itineraryNo", String.class);

		map.put("sequenceNo", int.class);

		map.put("portofAuthority", String.class);
		map.put("nameOfShip", String.class);

		map.put("itineraryScheduleId", long.class);
		map.put("tugboatCompanyCode", String.class);
		map.put("tugboatCompanyName", String.class);
		map.put("shipCode", String.class);
		map.put("shipName", String.class);
		map.put("power", double.class);
		map.put("unitPower", String.class);
		map.put("bt", double.class);
		map.put("unitGRT", String.class);
		map.put("tugMode", String.class);
		map.put("tugboatShortName", String.class);
		map.put("makePayment", int.class);
		map.put("documentaryCode", String.class);
		map.put("invoiceDocumentaryCode", String.class);
		map.put("certificateNo", String.class);
		map.put("noticeShipType", int.class);
		map.put("tugDateFrom", Date.class);
		map.put("tugDateTo", Date.class);
		map.put("anchoringPortHarbourCode", String.class);
		map.put("anchoringPortWharfCode", String.class);
		map.put("shiftingPortRegionCode", String.class);
		map.put("shiftingPortHarbourCode", String.class);
		map.put("shiftingPortWharfCode", String.class);
		map.put("vmaScheduleTugboatId", long.class);

		return map;
	}

	private static String generateQuery(String cmd, String itineraryNo,
			String nameOfShip, String portofAuthority,
			Long itineraryScheduleId, String tugboatCompanyCode,
			String tugboatCompanyName, String shipCode, String shipName,
			Double power, String unitPower, Double gt, String unitGRT,
			String tugMode, String tugboatShortName, Integer makePayment,
			String documentaryCode, String invoiceDocumentaryCode,
			Integer noticeShipType, String anchoringPortHarbourCode,
			String anchoringPortWharfCode, String shiftingPortRegionCode,
			String shiftingPortHarbourCode, String shiftingPortWharfCode,
			String tugDateFrom, String tugDateTo) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_schedule_tugboat_list AS a INNER JOIN  vma_schedule_tugboat as b ON a.itineraryScheduleId = b.itineraryScheduleId AND a.SequenceNo = b.SequenceNo"; //edit by dungnv, add 'AND a.SequenceNo = b.SequenceNo'
		} else {
			sql = "SELECT ";
			sql += "a.ItineraryNo,";
			sql += "a.SequenceNo,";
			sql += "a.PortofAuthority,";
			sql += "a.NameOfShip,";
			sql += "a.itineraryScheduleId,";
			sql += "a.TugboatCompanyCode,";
			sql += "a.TugboatCompanyName,";
			sql += "a.ShipCode,";
			sql += "a.ShipName,";
			sql += "a.Power,";
			sql += "a.UnitPower,";
			sql += "a.GT,";
			sql += "a.UnitGRT,";
			sql += "a.TugMode,";
			sql += "a.TugboatShortName,";
			sql += "a.MakePayment,";
			sql += "a.DocumentaryCode,";
			sql += "a.InvoiceDocumentaryCode,";
			sql += "b.CertificateNo,";
			sql += "b.NoticeShipType,";
			sql += "b.TugDateFrom,";
			sql += "b.TugDateTo,";
			sql += "b.AnchoringPortHarbourCode,";
			sql += "b.AnchoringPortWharfCode,";
			sql += "b.ShiftingPortRegionCode,";
			sql += "b.ShiftingPortHarbourCode,";
			sql += "b.ShiftingPortWharfCode,";
			sql += "b.id";

			sql += " FROM vma_schedule_tugboat_list AS a INNER JOIN  vma_schedule_tugboat as b ON";
			sql += " a.itineraryScheduleId = b.itineraryScheduleId AND a.SequenceNo = b.SequenceNo"; //edit by dungnv, add 'AND a.SequenceNo = b.SequenceNo'
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("NameOfShip", "'%"
					+ nameOfShip + "%'", "AND", StringPool.LIKE,
					new String[] { " a " }));
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortofAuthority", "'"
					+ portofAuthority + "'", "AND", StringPool.EQUAL,
					new String[] { " a " }));
		}

		if (itineraryScheduleId != null) {
			condition.append(VMAUtils.buildSQLCondition("itineraryScheduleId",
					itineraryScheduleId, "AND", StringPool.EQUAL,
					new String[] { " a " }));
		}

		if (Validator.isNotNull(tugboatCompanyCode)) {
			condition.append(VMAUtils.buildSQLCondition("TugboatCompanyCode",
					"'%" + tugboatCompanyCode + "%'", "AND", StringPool.LIKE,
					new String[] { " a " }));
		}

		if (Validator.isNotNull(tugboatCompanyName)) {
			condition.append(VMAUtils.buildSQLCondition("TugboatCompanyName",
					"'%" + tugboatCompanyName + "%'", "AND", StringPool.LIKE,
					new String[] { " a " }));
		}

		if (Validator.isNotNull(shipCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipCode", "'%"
					+ shipCode + "%'", "AND", StringPool.LIKE,
					new String[] { " a " }));
		}

		if (Validator.isNotNull(shipName)) {
			condition.append(VMAUtils.buildSQLCondition("ShipName", "'%"
					+ shipName + "%'", "AND", StringPool.LIKE,
					new String[] { " a " }));
		}

		if (power != null) {
			condition.append(VMAUtils.buildSQLCondition("Power", power, "AND",
					StringPool.EQUAL, new String[] { " a " }));
		}

		if (gt != null) {
			condition.append(VMAUtils.buildSQLCondition("GT", gt, "AND",
					StringPool.EQUAL, new String[] { " a " }));
		}

		if (Validator.isNotNull(tugMode)) {
			condition.append(VMAUtils.buildSQLCondition("TugMode", "'%"
					+ tugMode + "%'", "AND", StringPool.LIKE,
					new String[] { " a " }));
		}

		if (Validator.isNotNull(tugboatShortName)) {
			condition.append(VMAUtils.buildSQLCondition("TugboatShortName",
					"'%" + tugboatShortName + "%'", "AND", StringPool.LIKE,
					new String[] { " a " }));
		}

		if (Validator.isNotNull(documentaryCode)) {
			condition.append(VMAUtils.buildSQLCondition("DocumentaryCode", "'"
					+ documentaryCode + "'", "AND", StringPool.EQUAL,
					new String[] { " a " }));
		}

		if (Validator.isNotNull(invoiceDocumentaryCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"InvoiceDocumentaryCode", "'" + invoiceDocumentaryCode
							+ "'", "AND", StringPool.EQUAL,
					new String[] { " a " }));
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL,
					new String[] { " a " }));
		}

		if (makePayment != null) {
			condition.append(VMAUtils.buildSQLCondition("MakePayment",
					makePayment, "AND", StringPool.EQUAL,
					new String[] { " a " }));
		}

		if (noticeShipType != null) {
			condition.append(VMAUtils.buildSQLCondition("NoticeShipType",
					noticeShipType, "AND", StringPool.EQUAL,
					new String[] { " b " }));
		}

		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"AnchoringPortHarbourCode", "'" + anchoringPortHarbourCode
							+ "'", "AND", StringPool.EQUAL,
					new String[] { " b " }));
		}

		if (Validator.isNotNull(anchoringPortWharfCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"AnchoringPortWharfCode", "'" + anchoringPortWharfCode
							+ "'", "AND", StringPool.EQUAL,
					new String[] { " b " }));
		}

		if (Validator.isNotNull(shiftingPortRegionCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShiftingPortRegionCode", "'" + shiftingPortRegionCode
							+ "'", "AND", StringPool.EQUAL,
					new String[] { " b " }));
		}

		if (Validator.isNotNull(shiftingPortHarbourCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShiftingPortHarbourCode", "'" + shiftingPortHarbourCode
							+ "'", "AND", StringPool.EQUAL,
					new String[] { " b " }));
		}

		if (Validator.isNotNull(shiftingPortWharfCode)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShiftingPortWharfCode", "'" + shiftingPortWharfCode + "'",
					"AND", StringPool.EQUAL, new String[] { " b " }));
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
						+ " 23:59:59'", "AND", StringPool.BETWEEN,
						new String[] { " b " }));
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
						+ " 23:59:59'", "AND", StringPool.BETWEEN,
						new String[] { " b " }));
			}
		}

		return sql + condition.toString();
	}

}
