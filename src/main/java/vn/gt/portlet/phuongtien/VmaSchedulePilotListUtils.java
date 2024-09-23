package vn.gt.portlet.phuongtien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaSchedulePilotList;


import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotListLocalServiceUtil;
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
public class VmaSchedulePilotListUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaSchedulePilotListId = ParamUtil.getLong(request,
				"vmaSchedulePilotListId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaSchedulePilotList vmaSchedulePilotList = VmaSchedulePilotListLocalServiceUtil
					.getVmaSchedulePilotList(vmaSchedulePilotListId);
			result = VMAUtils.object2Json(vmaSchedulePilotList,
					VmaSchedulePilotList.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo sequenceNo portofAuthority nameOfShip pilotCompanyCode
	 * pilotCompanyName pilotCode pilotName pilotCategoryCode modifiedDate
	 */
	public static VmaSchedulePilotList getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;

		long id = GetterUtil.getLong(
				request.getParameter("vmaSchedulePilotListId"), -1);

		VmaSchedulePilotList vmaSchedulePilotList = null;

		if (id > 0) {
			try {
				vmaSchedulePilotList = VmaSchedulePilotListLocalServiceUtil
						.getVmaSchedulePilotList(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaSchedulePilotList = new VmaSchedulePilotList();
		}

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		if (Validator.isNotNull(itineraryNo)) {
			vmaSchedulePilotList.setItineraryNo(itineraryNo);
		}

		// lay tu VmaSchedulePilot
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1); if (sequenceNo >= 0) {
		 * vmaSchedulePilotList.setSequenceNo(sequenceNo); }
		 */

		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaSchedulePilotList.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaSchedulePilotList.setNameOfShip(nameOfShip);
		}
		String pilotCompanyCode = ParamUtil.getString(actionRequest,
				"pilotCompanyCode", StringPool.BLANK);
		if (Validator.isNotNull(pilotCompanyCode)) {
			vmaSchedulePilotList.setPilotCompanyCode(pilotCompanyCode);
		}
		String pilotCompanyName = VMAUtils.getString(actionRequest,
				"pilotCompanyName", StringPool.BLANK);
		if (Validator.isNotNull(pilotCompanyName)) {
			vmaSchedulePilotList.setPilotCompanyName(pilotCompanyName);
		}
		String pilotCode = ParamUtil.getString(actionRequest, "pilotCode",
				StringPool.BLANK);
		if (Validator.isNotNull(pilotCode)) {
			vmaSchedulePilotList.setPilotCode(pilotCode);
		}
		String pilotName = VMAUtils.getString(actionRequest, "pilotName",
				StringPool.BLANK);
		if (Validator.isNotNull(pilotName)) {
			vmaSchedulePilotList.setPilotName(pilotName);
		}
		String pilotCategoryCode = ParamUtil.getString(actionRequest,
				"pilotCategoryCode", StringPool.BLANK);
		if (Validator.isNotNull(pilotCategoryCode)) {
			vmaSchedulePilotList.setPilotCategoryCode(pilotCategoryCode);
		}
		/*String modifiedDate = ParamUtil.getString(actionRequest,
				"modifiedDate", StringPool.BLANK);
		if (Validator.isNotNull(modifiedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(modifiedDate);
				vmaSchedulePilotList.setModifiedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}*/

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);
		if (itineraryScheduleId >= 0) {
			vmaSchedulePilotList.setItineraryScheduleId(itineraryScheduleId);
		}
		
	
		return vmaSchedulePilotList;
	}

	public static List<VmaSchedulePilotList> getObjectsFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
			throws JSONException {

		String json = ParamUtil.getString(actionRequest, "json",
				StringPool.BLANK);

		List<VmaSchedulePilotList> vmaSchedulePilotLists = new ArrayList<VmaSchedulePilotList>();

		JSONArray array = JSONFactoryUtil.createJSONArray(json);

		if (array != null) {

			for (int i = 0; i < array.length(); i++) {

				VmaSchedulePilotList vmaSchedulePilotList = new VmaSchedulePilotList();

				JSONObject object = array.getJSONObject(i);

				String itineraryNo = object.getString("itineraryNo");
				vmaSchedulePilotList.setItineraryNo(itineraryNo);

				int sequenceNo = object.getInt("sequenceNo");
				vmaSchedulePilotList.setSequenceNo(sequenceNo);

				String portofAuthority = object.getString("portofAuthority");
				vmaSchedulePilotList.setPortofAuthority(portofAuthority);

				String nameOfShip = object.getString("nameOfShip");
				vmaSchedulePilotList.setNameOfShip(DanhMucUtils
						.encodeUTF8(nameOfShip));

				String pilotCompanyCode = object.getString("pilotCompanyCode");
				vmaSchedulePilotList.setPilotCompanyCode(pilotCompanyCode);

				String pilotCompanyName = object.getString("pilotCompanyName");
				vmaSchedulePilotList.setPilotCompanyName(DanhMucUtils
						.encodeUTF8(pilotCompanyName));

				String pilotCode = object.getString("pilotCode");
				vmaSchedulePilotList.setPilotCode(pilotCode);

				String pilotName = object.getString("pilotName");
				vmaSchedulePilotList.setPilotName(DanhMucUtils
						.encodeUTF8(pilotName));

				String pilotCategoryCode = object
						.getString("pilotCategoryCode");
				vmaSchedulePilotList.setPilotCategoryCode(pilotCategoryCode);

				/*String modifiedDate = ParamUtil.getString(actionRequest,
						"modifiedDate", StringPool.BLANK);

				if (Validator.isNotNull(modifiedDate)) {
					try {
						Date date = FormatData.formatDateShort3
								.parse(modifiedDate);
						vmaSchedulePilotList.setModifiedDate(date);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}
				}*/

				long itineraryScheduleId = GetterUtil.getLong(
						actionRequest.getParameter("itineraryScheduleId"), -1);
				if (itineraryScheduleId >= 0) {
					vmaSchedulePilotList
							.setItineraryScheduleId(itineraryScheduleId);
				}
				vmaSchedulePilotLists.add(vmaSchedulePilotList);
			}
		}

		return vmaSchedulePilotLists;
	}

	public static JSONObject addVmaSchedulePilotList(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaSchedulePilotList vmaSchedulePilotList = getObjectFromRequest(
				themeDisplay, actionRequest);

		if (vmaSchedulePilotList == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {

			vmaSchedulePilotList = VmaSchedulePilotListLocalServiceUtil
					.addVmaSchedulePilotList(vmaSchedulePilotList);

			result = VMAUtils.object2Json(vmaSchedulePilotList,
					VmaSchedulePilotList.class);

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaSchedulePilotList(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaSchedulePilotList vmaSchedulePilotList = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaSchedulePilotList == null) {

			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaSchedulePilotList = VmaSchedulePilotListLocalServiceUtil
					.updateVmaSchedulePilotList(vmaSchedulePilotList);
			result = VMAUtils.object2Json(vmaSchedulePilotList,
					VmaSchedulePilotList.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaSchedulePilotList(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		long id = ParamUtil.getLong(actionRequest, "vmaSchedulePilotListId");

		if (id > 0) {
			try {
				VmaSchedulePilotListLocalServiceUtil
						.deleteVmaSchedulePilotList(id);
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
		long vmaSchedulePilotListId = GetterUtil.getLong(
				request.getParameter("vmaSchedulePilotListId"), -1);
		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		/*int sequenceNo = GetterUtil.getInteger(
				request.getParameter("sequenceNo"), -1);*/
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String pilotCompanyCode = ParamUtil.getString(resourceRequest,
				"pilotCompanyCode", StringPool.BLANK);
		String pilotCompanyName = ParamUtil.getString(resourceRequest,
				"pilotCompanyName", StringPool.BLANK);
		String pilotCode = ParamUtil.getString(resourceRequest, "pilotCode",
				StringPool.BLANK);
		String pilotName = ParamUtil.getString(resourceRequest, "pilotName",
				StringPool.BLANK);
		String pilotCategoryCode = ParamUtil.getString(resourceRequest,
				"pilotCategoryCode", StringPool.BLANK);
		/*String modifiedDate = ParamUtil.getString(resourceRequest,
				"modifiedDate", StringPool.BLANK);*/
		try {
			List<VmaSchedulePilotList> vmaSchedulePilotLists = VmaSchedulePilotListLocalServiceUtil
					.findByItineraryNo(itineraryNo);

			return VMAUtils.object2Json(vmaSchedulePilotLists,
					VmaSchedulePilotList.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

}
