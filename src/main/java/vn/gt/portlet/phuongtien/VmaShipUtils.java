package vn.gt.portlet.phuongtien;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaCertificate;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaScheduleMerging;
import com.fds.nsw.nghiepvu.model.VmaShip;



import vn.gt.dao.noticeandmessage.service.VmaCertificateLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleMergingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaShipLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
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
public class VmaShipUtils
 {

	/*
	 * id mtgateway maritimeCode shipName shipPreviousName shipTypeMT
	 * shipTypeCode shipBoat hasTugBoat tugBoatName nameOfMaster chiefOfEngineer
	 * imoNumber callSign flagStateOfShip vrCode registryNumber registryDate
	 * registryPortCode gt nt dwt loa breadth clearanceHeight power maxDraft
	 * shownDraftxF shownDraftxA productionCountry productionYear shipOwnerCode
	 * shipOperatorCode shipAgencyCode expiredDate remarks isDelete modifiedDate
	 * markedAsDelete requestedDate syncVersion
	 */
	public static VmaShip getObjectFromRequest(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(request.getParameter("vmaShipId"), -1);
		VmaShip vmaShip = null;
		if (id > 0) {
			try {
				vmaShip = VmaShipLocalServiceUtil.getVmaShip(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaShip = new VmaShip();
		}

		int mtgateway = GetterUtil.getInteger(
				request.getParameter("mtgateway"), -1);
		if (mtgateway >= 0) {
			vmaShip.setMtgateway(mtgateway);
		}
		String maritimeCode = ParamUtil.getString(actionRequest,
				"maritimeCode", StringPool.BLANK);
		if (Validator.isNotNull(maritimeCode)) {
			vmaShip.setMaritimeCode(maritimeCode);
		}
		String shipName = VMAUtils.getString(actionRequest, "shipName",
				StringPool.BLANK);
		if (Validator.isNotNull(shipName)) {
			vmaShip.setShipName(shipName);
		}
		String shipPreviousName = VMAUtils.getString(actionRequest,
				"shipPreviousName", StringPool.BLANK);
		if (Validator.isNotNull(shipPreviousName)) {
			vmaShip.setShipPreviousName(shipPreviousName);
		}
		String shipTypeMT = ParamUtil.getString(actionRequest, "shipTypeMT",
				StringPool.BLANK);
		if (Validator.isNotNull(shipTypeMT)) {
			vmaShip.setShipTypeMT(shipTypeMT);
		}
		String shipTypeCode = ParamUtil.getString(actionRequest,
				"shipTypeCode", StringPool.BLANK);
		if (Validator.isNotNull(shipTypeCode)) {
			vmaShip.setShipTypeCode(shipTypeCode);
		}
		String shipBoat = ParamUtil.getString(actionRequest, "shipBoat",
				StringPool.BLANK);
		if (Validator.isNotNull(shipBoat)) {
			vmaShip.setShipBoat(shipBoat);
		}
		int hasTugBoat = GetterUtil.getInteger(
				request.getParameter("hasTugBoat"), -1);
		if (hasTugBoat >= 0) {
			vmaShip.setHasTugBoat(hasTugBoat);
		}
		String tugBoatName = VMAUtils.getString(actionRequest, "tugBoatName",
				StringPool.BLANK);
		if (Validator.isNotNull(tugBoatName)) {
			vmaShip.setTugBoatName(tugBoatName);
		}
		String nameOfMaster = VMAUtils.getString(actionRequest, "nameOfMaster",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfMaster)) {
			vmaShip.setNameOfMaster(nameOfMaster);
		}
		String chiefOfEngineer = VMAUtils.getString(actionRequest,
				"chiefOfEngineer", StringPool.BLANK);
		if (Validator.isNotNull(chiefOfEngineer)) {
			vmaShip.setChiefOfEngineer(chiefOfEngineer);
		}
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
				StringPool.BLANK);
		if (Validator.isNotNull(imoNumber)) {
			vmaShip.setImoNumber(imoNumber);
		}
		String callSign = VMAUtils.getString(actionRequest, "callSign",
				StringPool.BLANK);
		if (Validator.isNotNull(callSign)) {
			vmaShip.setCallSign(callSign);
		}
		String flagStateOfShip = ParamUtil.getString(actionRequest,
				"flagStateOfShip", StringPool.BLANK);
		if (Validator.isNotNull(flagStateOfShip)) {
			vmaShip.setFlagStateOfShip(flagStateOfShip);
		}
		String vrCode = ParamUtil.getString(actionRequest, "vrCode",
				StringPool.BLANK);
		if (Validator.isNotNull(vrCode)) {
			vmaShip.setVrCode(vrCode);
		}
		String registryNumber = VMAUtils.getString(actionRequest,
				"registryNumber", StringPool.BLANK);
		if (Validator.isNotNull(registryNumber)) {
			vmaShip.setRegistryNumber(registryNumber);
		}
		String registryDate = ParamUtil.getString(actionRequest,
				"registryDate", StringPool.BLANK);
		if (Validator.isNotNull(registryDate)) {
			// TODO parse String to Date
		}
		String registryPortCode = ParamUtil.getString(actionRequest,
				"registryPortCode", StringPool.BLANK);
		if (Validator.isNotNull(registryPortCode)) {
			vmaShip.setRegistryPortCode(registryPortCode);
		}
		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);
		if (gt >= 0) {
			vmaShip.setGt(BigDecimal.valueOf(gt));
		}
		double nt = GetterUtil.getDouble(request.getParameter("nt"), -1);
		if (nt >= 0) {
			vmaShip.setNt(BigDecimal.valueOf(nt));
		}
		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);
		if (dwt >= 0) {
			vmaShip.setDwt(BigDecimal.valueOf(dwt));
		}
		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);
		if (loa >= 0) {
			vmaShip.setLoa(loa);
		}
		double breadth = GetterUtil.getDouble(request.getParameter("breadth"),
				-1);
		if (breadth >= 0) {
			vmaShip.setBreadth(breadth);
		}
		double clearanceHeight = GetterUtil.getDouble(
				request.getParameter("clearanceHeight"), -1);
		if (clearanceHeight >= 0) {
			vmaShip.setClearanceHeight(clearanceHeight);
		}
		double power = GetterUtil.getDouble(request.getParameter("power"), -1);
		if (power >= 0) {
			vmaShip.setPower(power);
		}
		double maxDraft = GetterUtil.getDouble(
				request.getParameter("maxDraft"), -1);
		if (maxDraft >= 0) {
			vmaShip.setMaxDraft(maxDraft);
		}
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		if (shownDraftxF >= 0) {
			vmaShip.setShownDraftxF(shownDraftxF);
		}
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);
		if (shownDraftxA >= 0) {
			vmaShip.setShownDraftxA(shownDraftxA);
		}
		String unitPower = ParamUtil.getString(actionRequest, "unitPower", "HP");
		if (Validator.isNotNull(unitPower)) {
			vmaShip.setUnitPower(unitPower);
		}
		String productionCountry = VMAUtils.getString(actionRequest,
				"productionCountry", StringPool.BLANK);
		if (Validator.isNotNull(productionCountry)) {
			vmaShip.setProductionCountry(productionCountry);
		}
		String productionYear = ParamUtil.getString(actionRequest,
				"productionYear", StringPool.BLANK);
		if (Validator.isNotNull(productionYear)) {
			vmaShip.setProductionYear(productionYear);
		}
		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaShip.setShipOwnerCode(shipOwnerCode);
		}
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaShip.setShipOperatorCode(shipOperatorCode);
		}
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaShip.setShipAgencyCode(shipAgencyCode);
		}
		String expiredDate = ParamUtil.getString(actionRequest, "expiredDate",
				StringPool.BLANK);
		if (Validator.isNotNull(expiredDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(expiredDate);
				vmaShip.setRequestedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String remarks = VMAUtils.getString(actionRequest, "remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(remarks)) {
			vmaShip.setRemarks(remarks);
		}
		int isDelete = GetterUtil.getInteger(request.getParameter("isDelete"),
				-1);
		if (isDelete >= 0) {
			vmaShip.setIsDelete(isDelete);
		}
		/*
		 * String modifiedDate = ParamUtil.getString(actionRequest,
		 * "modifiedDate", StringPool.BLANK); if
		 * (Validator.isNotNull(modifiedDate)) { try { Date date =
		 * FormatData.formatDateShort3.parse(modifiedDate);
		 * vmaShip.setRequestedDate(date); } catch (ParseException e) {
		 * log.error(e.getMessage()); } }
		 */
		int markedAsDelete = GetterUtil.getInteger(
				request.getParameter("markedAsDelete"), -1);
		if (markedAsDelete >= 0) {
			vmaShip.setMarkedAsDelete(markedAsDelete);
		}
		String requestedDate = ParamUtil.getString(actionRequest,
				"requestedDate", StringPool.BLANK);
		if (Validator.isNotNull(requestedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(requestedDate);
				vmaShip.setRequestedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String syncVersion = ParamUtil.getString(actionRequest, "syncVersion",
				StringPool.BLANK);
		if (Validator.isNotNull(syncVersion)) {
			vmaShip.setSyncVersion(syncVersion);
		}
		String certificateOfMaster = VMAUtils.getString(actionRequest,
				"certificateOfMaster", StringPool.BLANK);
		if (Validator.isNotNull(certificateOfMaster)) {
			vmaShip.setCertificateOfMaster(certificateOfMaster);
		}
		String certificateChiefOfEngineer = VMAUtils.getString(actionRequest,
				"certificateChiefOfEngineer", StringPool.BLANK);
		if (Validator.isNotNull(certificateChiefOfEngineer)) {
			vmaShip.setCertificateChiefOfEngineer(certificateChiefOfEngineer);
		}
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaShip.setSecurityLevelCode(securityLevelCode);
		}
		int violated = GetterUtil.getInteger(request.getParameter("violated"),
				-1);
		if (violated >= 0) {
			vmaShip.setViolated(violated);
		}
		int seat = GetterUtil.getInteger(request.getParameter("seat"), -1);
		if (seat >= 0) {
			vmaShip.setSeat(seat);
		}
		int lies = GetterUtil.getInteger(request.getParameter("lies"), -1);
		if (lies >= 0) {
			vmaShip.setLies(lies);
		}
		
		int passengers = GetterUtil.getInteger(request.getParameter("passengers"), -1);
		if (passengers >= 0) {
			vmaShip.setPassengers(passengers);
		}
		
		int craneload = GetterUtil.getInteger(request.getParameter("craneload"), -1);
		if (craneload >= 0) {
			vmaShip.setCraneload(BigDecimal.valueOf(craneload));
		}
		
		String UnitCraneload = ParamUtil.getString(actionRequest,
				"unitCraneload", "MTs");
		if (Validator.isNotNull(UnitCraneload)) {
			vmaShip.setUnitCraneload(UnitCraneload);
		}
		
		int deconstructed = GetterUtil.getInteger(
				request.getParameter("deconstructed"), -1);
		if (deconstructed >= 0) {
			vmaShip.setDeconstructed(deconstructed);
		}

		String constructionShipyardCode = ParamUtil.getString(actionRequest,
				"constructionShipyardCode", StringPool.BLANK);
		if (Validator.isNotNull(constructionShipyardCode)) {
			vmaShip.setConstructionShipyardCode(constructionShipyardCode);
		}
		String deconstructionShipyardCode = ParamUtil.getString(actionRequest,
				"deconstructionShipyardCode", StringPool.BLANK);
		if (Validator.isNotNull(deconstructionShipyardCode)) {
			vmaShip.setDeconstructionShipyardCode(deconstructionShipyardCode);
		}

		return vmaShip;
	}

	public static JSONObject addVmaShip(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaShip vmaShip = getObjectFromRequest(themeDisplay, actionRequest);
		if (vmaShip == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaShip = VmaShipLocalServiceUtil.addVmaShip(vmaShip);
			result = VMAUtils.object2Json(vmaShip, VmaShip.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaShip(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaShip vmaShip = getObjectFromRequest(themeDisplay, actionRequest);
		if (vmaShip == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaShip = VmaShipLocalServiceUtil.updateVmaShip(vmaShip);
			result = VMAUtils.object2Json(vmaShip, VmaShip.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaShip(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaShipId");
		if (id > 0) {
			try {
				VmaShipLocalServiceUtil.deleteVmaShip(id);
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

	/*
	 * public static JSONObject doFind(ResourceRequest resourceRequest,
	 * ResourceResponse resourceResponse) { HttpServletRequest request =
	 * PortalUtil .getHttpServletRequest(resourceRequest); int start =
	 * GetterUtil.getInteger(request.getParameter("start"), 0); int end =
	 * GetterUtil.getInteger(request.getParameter("end"), 0); long vmaShipId =
	 * GetterUtil.getLong(request.getParameter("vmaShipId"), -1); int mtgateway
	 * = GetterUtil.getInteger( request.getParameter("mtgateway"), -1); String
	 * maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode",
	 * StringPool.BLANK); String shipName = ParamUtil.getString(resourceRequest,
	 * "shipName", StringPool.BLANK); String shipPreviousName =
	 * ParamUtil.getString(resourceRequest, "shipPreviousName",
	 * StringPool.BLANK); String shipTypeMT =
	 * ParamUtil.getString(resourceRequest, "shipTypeMT", StringPool.BLANK);
	 * String shipTypeCode = ParamUtil.getString(resourceRequest,
	 * "shipTypeCode", StringPool.BLANK); String shipBoat =
	 * ParamUtil.getString(resourceRequest, "shipBoat", StringPool.BLANK); int
	 * hasTugBoat = GetterUtil.getInteger( request.getParameter("hasTugBoat"),
	 * -1); String tugBoatName = ParamUtil.getString(resourceRequest,
	 * "tugBoatName", StringPool.BLANK); String nameOfMaster =
	 * ParamUtil.getString(resourceRequest, "nameOfMaster", StringPool.BLANK);
	 * String chiefOfEngineer = ParamUtil.getString(resourceRequest,
	 * "chiefOfEngineer", StringPool.BLANK); String imoNumber =
	 * ParamUtil.getString(resourceRequest, "imoNumber", StringPool.BLANK);
	 * String callSign = ParamUtil.getString(resourceRequest, "callSign",
	 * StringPool.BLANK); String flagStateOfShip =
	 * ParamUtil.getString(resourceRequest, "flagStateOfShip",
	 * StringPool.BLANK); String vrCode = ParamUtil.getString(resourceRequest,
	 * "vrCode", StringPool.BLANK); String registryNumber =
	 * ParamUtil.getString(resourceRequest, "registryNumber", StringPool.BLANK);
	 * String registryDate = ParamUtil.getString(resourceRequest,
	 * "registryDate", StringPool.BLANK); String registryPortCode =
	 * ParamUtil.getString(resourceRequest, "registryPortCode",
	 * StringPool.BLANK); double gt =
	 * GetterUtil.getDouble(request.getParameter("gt"), -1); double nt =
	 * GetterUtil.getDouble(request.getParameter("nt"), -1); double dwt =
	 * GetterUtil.getDouble(request.getParameter("dwt"), -1); double loa =
	 * GetterUtil.getDouble(request.getParameter("loa"), -1); double breadth =
	 * GetterUtil.getDouble(request.getParameter("breadth"), -1); double
	 * clearanceHeight = GetterUtil.getDouble(
	 * request.getParameter("clearanceHeight"), -1); double power =
	 * GetterUtil.getDouble(request.getParameter("power"), -1); double maxDraft
	 * = GetterUtil.getDouble( request.getParameter("maxDraft"), -1); double
	 * shownDraftxF = GetterUtil.getDouble(
	 * request.getParameter("shownDraftxF"), -1); double shownDraftxA =
	 * GetterUtil.getDouble( request.getParameter("shownDraftxA"), -1); String
	 * productionCountry = ParamUtil.getString(resourceRequest,
	 * "productionCountry", StringPool.BLANK); String productionYear =
	 * ParamUtil.getString(resourceRequest, "productionYear", StringPool.BLANK);
	 * String shipOwnerCode = ParamUtil.getString(resourceRequest,
	 * "shipOwnerCode", StringPool.BLANK); String shipOperatorCode =
	 * ParamUtil.getString(resourceRequest, "shipOperatorCode",
	 * StringPool.BLANK); String shipAgencyCode =
	 * ParamUtil.getString(resourceRequest, "shipAgencyCode", StringPool.BLANK);
	 * String expiredDate = ParamUtil.getString(resourceRequest, "expiredDate",
	 * StringPool.BLANK); String remarks = ParamUtil.getString(resourceRequest,
	 * "remarks", StringPool.BLANK); int isDelete =
	 * GetterUtil.getInteger(request.getParameter("isDelete"), -1); String
	 * modifiedDate = ParamUtil.getString(resourceRequest, "modifiedDate",
	 * StringPool.BLANK); int markedAsDelete = GetterUtil.getInteger(
	 * request.getParameter("markedAsDelete"), -1); String requestedDate =
	 * ParamUtil.getString(resourceRequest, "requestedDate", StringPool.BLANK);
	 * String syncVersion = ParamUtil.getString(resourceRequest, "syncVersion",
	 * StringPool.BLANK); try { return null; } catch (Exception e) {
	 * log.error(e.getMessage()); JSONObject result = JSONFactoryUtil.createJSONObject();
	 * result.put("total", 0); result.put("data",
	 * JSONFactoryUtil.createJSONArray()); return result; } }
	 */

	public static JSONObject findVmaShip(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		int start = GetterUtil.getInteger(request.getParameter("start"), 0);

		int end = GetterUtil.getInteger(request.getParameter("end"), 0);

		String shipBoat = ParamUtil.getString(resourceRequest, "shipBoat");

		String shipName = VMAUtils.getString(resourceRequest, "shipName", null);

		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);

		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode");

		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip");

		String callSign = VMAUtils.getString(resourceRequest, "callSign",StringPool.BLANK);

		String shipOwnerCode = ParamUtil.getString(resourceRequest,
				"shipOwnerCode");

		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode");

		String shipTypeMT = ParamUtil.getString(resourceRequest, "shipTypeMT");

		String shipTypeCode = ParamUtil.getString(resourceRequest,
				"shipTypeCode");

		String shipOperatorName = ParamUtil.getString(resourceRequest,
				"shipOperatorName");

		String shipOwnerName = ParamUtil.getString(resourceRequest,
				"shipOwnerName");

		String tugBoatName = ParamUtil
				.getString(resourceRequest, "tugBoatName");

		String vrCode = ParamUtil.getString(resourceRequest, "vrCode");

		String nameOfMaster = ParamUtil.getString(resourceRequest,
				"nameOfMaster");

		String chiefOfEngineer = ParamUtil.getString(resourceRequest,
				"chiefOfEngineer");

		String shipOwnerAddress = ParamUtil.getString(resourceRequest,
				"shipOwnerAddress");

		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);

		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);

		double nt = GetterUtil.getDouble(request.getParameter("nt"), -1);

		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);

		double breadth = GetterUtil.getDouble(request.getParameter("breadth"),
				-1);
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);
		double clearanceHeight = GetterUtil.getDouble(
				request.getParameter("clearanceHeight"), -1);
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		double maxDraft = GetterUtil.getDouble(
				request.getParameter("maxDraft"), -1);
		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode");
		double power = GetterUtil.getDouble(request.getParameter("power"), -1);

		int violated = GetterUtil.getInteger(request.getParameter("violated"),
				-1);
		int deconstructed = GetterUtil.getInteger(
				request.getParameter("deconstructed"), -1);

		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber",StringPool.BLANK);

		if (shipBoat.equals("SHIP")) {
			try {
				String searchQuery = generateQuery1("search", shipName,
						flagStateOfShip, imoNumber, callSign, shipTypeCode,
						shipTypeMT, gt, nt, dwt, loa, breadth, shownDraftxA,
						clearanceHeight, shownDraftxF, maxDraft, shipBoat,
						shipAgencyCode, shipOwnerCode, shipOwnerName,
						shipOperatorCode, shipOperatorName, maritimeCode,
						violated, deconstructed, registryNumber, vrCode);

				String countQuery = generateQuery1("count", shipName,
						flagStateOfShip, imoNumber, callSign, shipTypeCode,
						shipTypeMT, gt, nt, dwt, loa, breadth, shownDraftxA,
						clearanceHeight, shownDraftxF, maxDraft, shipBoat,
						shipAgencyCode, shipOwnerCode, shipOwnerName,
						shipOperatorCode, shipOperatorName, maritimeCode,
						violated, deconstructed, registryNumber, vrCode);

				return VmaShipLocalServiceUtil.findVmaShip(searchQuery,
						countQuery, start, end);

			} catch (Exception e) {
				log.error(e.getMessage());
				JSONObject result = JSONFactoryUtil.createJSONObject();
				result.put("total", 0);
				result.put("data", JSONFactoryUtil.createJSONArray());
				return result;
			}
		} else {
			try {
				String searchQuery = generateQuery2("search", shipName,
						shipTypeCode, vrCode, tugBoatName, gt, dwt, loa,
						breadth, power, shipOwnerCode, shipOwnerName,
						shipOwnerAddress, nameOfMaster, chiefOfEngineer,
						shipBoat, maritimeCode, violated, deconstructed,
						registryNumber);

				String countQuery = generateQuery2("count", shipName,
						shipTypeCode, vrCode, tugBoatName, gt, dwt, loa,
						breadth, power, shipOwnerCode, shipOwnerName,
						shipOwnerAddress, nameOfMaster, chiefOfEngineer,
						shipBoat, maritimeCode, violated, deconstructed,
						registryNumber);

				return VmaShipLocalServiceUtil.findVmaShip(searchQuery,
						countQuery, start, end);
			} catch (Exception e) {
				log.error(e.getMessage());
				JSONObject result = JSONFactoryUtil.createJSONObject();
				result.put("total", 0);
				result.put("data", JSONFactoryUtil.createJSONArray());
				return result;
			}
		}

	}

	public static void exportVmaShip(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		String shipBoat = ParamUtil.getString(resourceRequest, "shipBoat");

		String shipName = ParamUtil.getString(resourceRequest, "shipName");

		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);

		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode");

		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip");

		String callSign = VMAUtils.getString(resourceRequest, "callSign",StringPool.BLANK);

		String shipOwnerCode = ParamUtil.getString(resourceRequest,
				"shipOwnerCode");

		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode");

		String shipTypeMT = ParamUtil.getString(resourceRequest, "shipTypeMT");

		String shipTypeCode = ParamUtil.getString(resourceRequest,
				"shipTypeCode");

		String shipOperatorName = ParamUtil.getString(resourceRequest,
				"shipOperatorName");

		String shipOwnerName = ParamUtil.getString(resourceRequest,
				"shipOwnerName");

		String tugBoatName = ParamUtil
				.getString(resourceRequest, "tugBoatName");

		String vrCode = ParamUtil.getString(resourceRequest, "vrCode");

		String nameOfMaster = ParamUtil.getString(resourceRequest,
				"nameOfMaster");

		String chiefOfEngineer = ParamUtil.getString(resourceRequest,
				"chiefOfEngineer");

		String shipOwnerAddress = ParamUtil.getString(resourceRequest,
				"shipOwnerAddress");

		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);

		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);

		double nt = GetterUtil.getDouble(request.getParameter("nt"), -1);

		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);

		double breadth = GetterUtil.getDouble(request.getParameter("breadth"),
				-1);
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);
		double clearanceHeight = GetterUtil.getDouble(
				request.getParameter("clearanceHeight"), -1);
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		double maxDraft = GetterUtil.getDouble(
				request.getParameter("maxDraft"), -1);
		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode");
		double power = GetterUtil.getDouble(request.getParameter("power"), -1);

		int violated = GetterUtil.getInteger(request.getParameter("violated"),
				-1);
		int deconstructed = GetterUtil.getInteger(
				request.getParameter("deconstructed"), -1);

		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber",StringPool.BLANK);

		//String sql = StringPool.BLANK;

		String[][] exportData = null;

		if (shipBoat.equals("SHIP")) {

			/*
			 * String[] headers = new String[] { "STT", "Tên tàu", "Quốc tịch",
			 * "IMO", "Hô hiệu", "Loại tàu", "Công dụng", "GT", "NT", "DWT",
			 * "Loa (m)", "Breadth (m)", "Mớn nước t.kế (m)",
			 * "Chiều cao t.không (m)", "Cấp độ an ninh", "Đại lý", "Chủ tàu",
			 * "Người khai thác" };
			 */

			String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
					"Qu\u1ED1c t\u1ECBch", "IMO", "H\u00F4 hi\u1EC7u",
					"Lo\u1EA1i t\u00E0u", "C\u00F4ng d\u1EE5ng", "GT", "NT",
					"DWT", "Loa (m)", "Breadth (m)",
					"M\u1EDBn n\u01B0\u1EDBc t.k\u1EBF (m)",
					"Chi\u1EC1u cao t.kh\u00F4ng (m)",
					"C\u1EA5p \u0111\u1ED9 an ninh", "\u0110\u1EA1i l\u00FD",
					"Ch\u1EE7 t\u00E0u", "Ng\u01B0\u1EDDi khai th\u00E1c" };

			try {

				String searchQuery = generateQuery1("search", shipName,
						flagStateOfShip, imoNumber, callSign, shipTypeCode,
						shipTypeMT, gt, nt, dwt, loa, breadth, shownDraftxA,
						clearanceHeight, shownDraftxF, maxDraft, shipBoat,
						shipAgencyCode, shipOwnerCode, shipOwnerName,
						shipOperatorCode, shipOperatorName, maritimeCode,
						violated, deconstructed, registryNumber, vrCode);

				String countQuery = generateQuery1("count", shipName,
						flagStateOfShip, imoNumber, callSign, shipTypeCode,
						shipTypeMT, gt, nt, dwt, loa, breadth, shownDraftxA,
						clearanceHeight, shownDraftxF, maxDraft, shipBoat,
						shipAgencyCode, shipOwnerCode, shipOwnerName,
						shipOperatorCode, shipOperatorName, maritimeCode,
						violated, deconstructed, registryNumber, vrCode);

				JSONObject objects = VmaShipLocalServiceUtil.findVmaShip(
						searchQuery, countQuery, -1, -1);

				JSONArray data = objects.getJSONArray("data");

				if (data != null && data.length() > 0) {
					exportData = new String[data.length()][headers.length];
					for (int i = 0; i < data.length(); i++) {
						JSONObject object = data.getJSONObject(i);

						int stt = i + 1;

						shipName = object.getString("shipName");

						String stateName = object.getString("stateName");

						imoNumber = object.getString("imoNumber");

						// callSign = object.getString("callSign");

						String shipTypeName = object.getString("shipTypeName");

						String shipTypeMTName = object
								.getString("shipTypeMTName");

						gt = object.getDouble("gt");

						nt = object.getDouble("nt");

						dwt = object.getDouble("dwt");

						loa = object.getDouble("loa");

						breadth = object.getDouble("breadth");

						maxDraft = object.getDouble("maxDraft");

						clearanceHeight = object.getDouble("clearanceHeight");

						String securityLevelCode = object
								.getString("securityLevelCode");

						String shipAgencyName = object
								.getString("shipAgencyName");

						shipOwnerName = object.getString("shipOwnerName");

						shipOperatorName = object.getString("shipOperatorName");

						exportData[i][0] = String.valueOf(stt);
						exportData[i][1] = shipName;
						exportData[i][2] = stateName;
						exportData[i][3] = imoNumber;
						exportData[i][4] = callSign;
						exportData[i][5] = shipTypeName;
						exportData[i][6] = shipTypeMTName;
						exportData[i][7] = String.valueOf(gt);
						exportData[i][8] = String.valueOf(nt);
						exportData[i][9] = String.valueOf(dwt);
						exportData[i][10] = String.valueOf(loa);
						exportData[i][11] = String.valueOf(breadth);
						exportData[i][12] = String.valueOf(maxDraft);
						exportData[i][13] = String.valueOf(clearanceHeight);
						exportData[i][14] = securityLevelCode;
						exportData[i][15] = shipAgencyName;
						exportData[i][16] = shipOwnerName;
						exportData[i][17] = shipOperatorName;
					}
				}

				String sheetName = "VMA-Ship";

				String fileName = sheetName + "-" + System.currentTimeMillis();

				String filePath = VMAUtils.doExportExcelFile(request,
						resourceResponse, sheetName, fileName, headers,
						exportData);
//todo response file in controller
//				resourceResponse
//						.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//				resourceResponse.setProperty("Content-Disposition",
//						"attachment; filename= " + fileName + ".xls");
//				resourceResponse.setCharacterEncoding("UTF-8");
//				// resourceResponse.setProperty("filename", fileName + ".xls");
//				resourceResponse.addProperty("FILE-NAME", fileName + ".xls");
//
//				resourceResponse.getPortletOutputStream().write(
//						VMAUtils.readFileToByteArray(new File(filePath)));

			} catch (Exception e) {
				log.error(e.getMessage());

			}
		} else {

			/*
			 * String[] headers = new String[] { "STT", "Tên phương tiện",
			 * "Loại tàu", "Cấp PT", "Tàu kéo", "GT", "DWT", "LOA(m)",
			 * "Breadth(m)", "Công suất máy(HP)", "Chủ tàu", "Địa chỉ",
			 * "Thuyền trưởng", "Hạng bằng TT", "Máy trưởng", "Hạng bằng MT" };
			 */

			String[] headers = new String[] { "STT",
					"T\u00EAn ph\u01B0\u01A1ng ti\u1EC7n",
					"Lo\u1EA1i t\u00E0u", "C\u1EA5p PT", "T\u00E0u k\u00E9o",
					"GT", "DWT", "LOA(m)", "Breadth(m)",
					"C\u00F4ng su\u1EA5t m\u00E1y(HP)", "Ch\u1EE7 t\u00E0u",
					"\u0110\u1ECBa ch\u1EC9", "Thuy\u1EC1n tr\u01B0\u1EDFng",
					"H\u1EA1ng b\u1EB1ng TT", "M\u00E1y tr\u01B0\u1EDFng",
					"H\u1EA1ng b\u1EB1ng MT" };

			try {
				String searchQuery = generateQuery2("search", shipName,
						shipTypeCode, vrCode, tugBoatName, gt, dwt, loa,
						breadth, power, shipOwnerCode, shipOwnerName,
						shipOwnerAddress, nameOfMaster, chiefOfEngineer,
						shipBoat, maritimeCode, violated, deconstructed,
						registryNumber);

				String countQuery = generateQuery2("count", shipName,
						shipTypeCode, vrCode, tugBoatName, gt, dwt, loa,
						breadth, power, shipOwnerCode, shipOwnerName,
						shipOwnerAddress, nameOfMaster, chiefOfEngineer,
						shipBoat, maritimeCode, violated, deconstructed,
						registryNumber);

				JSONObject objects = VmaShipLocalServiceUtil.findVmaShip(
						searchQuery, countQuery, -1, -1);

				JSONArray data = objects.getJSONArray("data");

				if (data != null && data.length() > 0) {
					exportData = new String[data.length()][headers.length];
					for (int i = 0; i < data.length(); i++) {
						JSONObject object = data.getJSONObject(i);

						int stt = i + 1;

						shipName = object.getString("shipName");

						String shipTypeName = object.getString("shipTypeName");

						vrCode = object.getString("vrCode");

						tugBoatName = object.getString("tugBoatName");

						gt = object.getDouble("gt");

						dwt = object.getDouble("dwt");

						loa = object.getDouble("loa");

						breadth = object.getDouble("breadth");

						power = object.getDouble("power");

						shipOwnerName = object.getString("shipOwnerName");

						shipOwnerAddress = object.getString("shipOwnerAddress");

						nameOfMaster = object.getString("nameOfMaster");

						chiefOfEngineer = object.getString("chiefOfEngineer");

						exportData[i][0] = String.valueOf(stt);
						exportData[i][1] = shipName;
						exportData[i][2] = shipTypeName;
						exportData[i][3] = vrCode;
						exportData[i][4] = tugBoatName;
						exportData[i][5] = String.valueOf(gt);
						exportData[i][6] = String.valueOf(dwt);
						exportData[i][7] = String.valueOf(loa);
						exportData[i][8] = String.valueOf(breadth);
						exportData[i][9] = String.valueOf(power);
						exportData[i][10] = shipOwnerName;
						exportData[i][11] = shipOwnerAddress;
						exportData[i][12] = nameOfMaster;
						exportData[i][13] = nameOfMaster;
						exportData[i][14] = chiefOfEngineer;
						exportData[i][15] = chiefOfEngineer;
					}
				}

				String sheetName = "VMA-Ship";

				String fileName = sheetName + "-" + System.currentTimeMillis();

				String filePath = VMAUtils.doExportExcelFile(request,
						resourceResponse, sheetName, fileName, headers,
						exportData);
				//todo response file in controller
//
//				resourceResponse
//						.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//				resourceResponse.setProperty("Content-Disposition",
//						"attachment; filename= " + fileName + ".xls");
//				resourceResponse.setCharacterEncoding("UTF-8");
//				// resourceResponse.setProperty("filename", fileName + ".xls");
//				resourceResponse.addProperty("FILE-NAME", fileName + ".xls");
//
//				resourceResponse.getPortletOutputStream().write(
//						VMAUtils.readFileToByteArray(new File(filePath)));

			} catch (Exception e) {
				log.error(e.getMessage());

			}
		}

	}

	public static long countVmaShip(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		String shipBoat = ParamUtil.getString(resourceRequest, "shipBoat");

		String shipName = ParamUtil.getString(resourceRequest, "shipName");

		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);

		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode");

		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip");

		String callSign = VMAUtils.getString(resourceRequest, "callSign",StringPool.BLANK);

		String shipOwnerCode = ParamUtil.getString(resourceRequest,
				"shipOwnerCode");

		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode");

		String shipTypeMT = ParamUtil.getString(resourceRequest, "shipTypeMT");

		String shipTypeCode = ParamUtil.getString(resourceRequest,
				"shipTypeCode");

		String shipOperatorName = ParamUtil.getString(resourceRequest,
				"shipOperatorName");

		String shipOwnerName = ParamUtil.getString(resourceRequest,
				"shipOwnerName");

		String tugBoatName = ParamUtil
				.getString(resourceRequest, "tugBoatName");

		String vrCode = ParamUtil.getString(resourceRequest, "vrCode");

		String nameOfMaster = ParamUtil.getString(resourceRequest,
				"nameOfMaster");

		String chiefOfEngineer = ParamUtil.getString(resourceRequest,
				"chiefOfEngineer");

		String shipOwnerAddress = ParamUtil.getString(resourceRequest,
				"shipOwnerAddress");

		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);

		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);

		double nt = GetterUtil.getDouble(request.getParameter("nt"), -1);

		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);

		double breadth = GetterUtil.getDouble(request.getParameter("breadth"),
				-1);
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);
		double clearanceHeight = GetterUtil.getDouble(
				request.getParameter("clearanceHeight"), -1);
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		double maxDraft = GetterUtil.getDouble(
				request.getParameter("maxDraft"), -1);
		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode");
		double power = GetterUtil.getDouble(request.getParameter("power"), -1);

		int violated = GetterUtil.getInteger(request.getParameter("violated"),
				-1);
		int deconstructed = GetterUtil.getInteger(
				request.getParameter("deconstructed"), -1);

		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber",StringPool.BLANK);

		String sql = StringPool.BLANK;
		if (shipBoat.equals("SHIP")) {

			try {
				sql = generateQuery1("count", shipName, flagStateOfShip,
						imoNumber, callSign, shipTypeCode, shipTypeMT, gt, nt,
						dwt, loa, breadth, shownDraftxA, clearanceHeight,
						shownDraftxF, maxDraft, shipBoat, shipAgencyCode,
						shipOwnerCode, shipOwnerName, shipOperatorCode,
						shipOperatorName, maritimeCode, violated,
						deconstructed, registryNumber, vrCode);

				return VmaShipLocalServiceUtil.countVmaShip(sql);
			} catch (Exception e) {
				log.error(e.getMessage());

				return 0;
			}
		} else {
			try {
				sql = generateQuery2("count", shipName, shipTypeCode, vrCode,
						tugBoatName, gt, dwt, loa, breadth, power,
						shipOwnerCode, shipOwnerName, shipOwnerAddress,
						nameOfMaster, chiefOfEngineer, shipBoat, maritimeCode,
						violated, deconstructed, registryNumber);

				return VmaShipLocalServiceUtil.countVmaShip(sql);
			} catch (Exception e) {
				log.error(e.getMessage());

				return 0;
			}
		}

	}

	public static JSONObject findVmaShipById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		long vmaShipId = ParamUtil.getLong(request, "vmaShipId");

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {

			VmaShip vmaShip = VmaShipLocalServiceUtil.getVmaShip(vmaShipId);

			result = VMAUtils.object2Json(vmaShip, VmaShip.class);
		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return result;
	}

	private static String generateQuery2(String cmd, String shipName,
			String shipTypeCode, String vrCode, String tugBoatName, double gt,
			double dwt, double loa, double breadth, double power,
			String shipOwnerCode, String shipOwnerName,
			String shipOwnerAddress, String nameOfMaster,
			String chiefOfEngineer, String shipBoat, String maritimeCode,
			int violated, int deconstructed, String registryNumber) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_ship as a";

		} else {
			sql = "SELECT a.* FROM vma_ship AS a";
		}

		if (Validator.isNotNull(shipOwnerName)
				|| Validator.isNotNull(shipOwnerAddress)) {
			sql += " INNER JOIN dm_vma_ship_owner as b ON a.shipOwnerCode = b.shipOwnerCode";
			if (Validator.isNotNull(shipOwnerName)) {
				sql += "AND b.CompanyName LIKE '%" + shipOwnerName
						+ "%' AND b.IsShipOwner = 1";
			}

			if (Validator.isNotNull(shipOwnerAddress)) {
				sql += "AND b.CompanyAddress LIKE '%" + shipOwnerAddress
						+ "%' AND b.IsShipOwner = 1";
			}
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(shipName)) {
			condition.append(VMAUtils.buildSQLCondition("ShipName", "'%"
					+ shipName + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(shipTypeCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipTypeCode", "'"
					+ shipTypeCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(vrCode)) {
			condition.append(VMAUtils.buildSQLCondition("VRCode", "'%" + vrCode
					+ "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(tugBoatName)) {
			condition.append(VMAUtils.buildSQLCondition("TugBoatName", "'%"
					+ tugBoatName + "%'", "AND", StringPool.LIKE));
		}

		if (gt >= 0) {
			condition.append(VMAUtils.buildSQLCondition("GT", gt, "AND",
					StringPool.EQUAL));
		}

		if (dwt >= 0) {
			condition.append(VMAUtils.buildSQLCondition("DWT", dwt, "AND",
					StringPool.EQUAL));
		}

		if (loa >= 0) {
			condition.append(VMAUtils.buildSQLCondition("LOA", loa, "AND",
					StringPool.EQUAL));
		}

		if (breadth >= 0) {
			condition.append(VMAUtils.buildSQLCondition("Breadth", breadth,
					"AND", StringPool.EQUAL));
		}

		if (power >= 0) {
			condition.append(VMAUtils.buildSQLCondition("Power", power, "AND",
					StringPool.EQUAL));
		}

		if (Validator.isNotNull(shipBoat)) {
			condition.append(VMAUtils.buildSQLCondition("ShipBoat", "'"
					+ shipBoat + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shipOwnerCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipOwnerCode", "'"
					+ shipOwnerCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(nameOfMaster)) {
			condition.append(VMAUtils.buildSQLCondition("NameOfMaster", "'%"
					+ nameOfMaster + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(chiefOfEngineer)) {
			condition.append(VMAUtils.buildSQLCondition("ChiefOfEngineer", "'%"
					+ chiefOfEngineer + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(maritimeCode)) {
			condition.append(VMAUtils.buildSQLCondition("MaritimeCode", "'"
					+ maritimeCode + "'", "AND", StringPool.EQUAL));
		}

		if (violated >= 0) {
			condition.append(VMAUtils.buildSQLCondition("Violated", violated,
					"AND", StringPool.EQUAL, new String[] { "a" }));
		}

		if (deconstructed >= 0) {
			condition.append(VMAUtils.buildSQLCondition("Deconstructed",
					deconstructed, "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(registryNumber)) {
			condition.append(VMAUtils.buildSQLCondition("RegistryNumber", "'%"
					+ registryNumber + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		return sql + condition.toString();
	}

	private static String generateQuery1(String cmd, String shipName,
			String flagStateOfShip, String imoNumber, String callSign,
			String shipTypeCode, String shipTypeMT, double gt, double nt,
			double dwt, double loa, double breadth, double shownDraftxA,
			double clearanceHeight, double shownDraftxF, double maxDraft,
			String shipBoat, String shipAgencyCode, String shipOwnerCode,
			String shipOwnerName, String shipOperatorCode,
			String shipOperatorName, String maritimeCode, int violated,
			int deconstructed, String registryNumber, String vrCode) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_ship as a";

		} else {
			sql = "SELECT a.* FROM vma_ship AS a";
		}

		if (Validator.isNotNull(shipOwnerName)
				|| Validator.isNotNull(shipOperatorCode)) {
			sql += " INNER JOIN dm_vma_ship_owner as b ON a.shipOwnerCode = b.shipOwnerCode";
			if (Validator.isNotNull(shipOwnerName)) {
				sql += "AND b.CompanyName LIKE '%" + shipOwnerName
						+ "%' AND b.IsShipOwner = 1";
			}

			if (Validator.isNotNull(shipOperatorCode)) {
				sql += "AND b.CompanyName LIKE '%" + shipOperatorName
						+ "%' AND b.IsShipOperator = 1";
			}
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if(Validator.isNotNull(vrCode)){
			condition.append(" AND a.VrCode = \'" + vrCode + "\'");
		}
		if (Validator.isNotNull(shipName)) {
			condition.append(VMAUtils.buildSQLCondition("ShipName", "'%"
					+ shipName + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(flagStateOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("FlagStateOfShip", "'"
					+ flagStateOfShip + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(VMAUtils.buildSQLCondition("IMONumber", "'%"
					+ imoNumber + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(VMAUtils.buildSQLCondition("CallSign", "'%"
					+ callSign + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(shipBoat)) {
			condition.append(VMAUtils.buildSQLCondition("ShipBoat", "'"
					+ shipBoat + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(shipTypeCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipTypeCode", "'"
					+ shipTypeCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(shipTypeMT)) {
			condition.append(VMAUtils.buildSQLCondition("ShipTypeMT", "'"
					+ shipTypeMT + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (gt >= 0) {
			condition.append(VMAUtils.buildSQLCondition("GT", gt, "AND",
					StringPool.EQUAL, new String[] { "a" }));
		}

		if (nt >= 0) {
			condition.append(VMAUtils.buildSQLCondition("NT", nt, "AND",
					StringPool.EQUAL, new String[] { "a" }));
		}

		if (dwt >= 0) {
			condition.append(VMAUtils.buildSQLCondition("DWT", dwt, "AND",
					StringPool.EQUAL, new String[] { "a" }));
		}

		if (loa >= 0) {
			condition.append(VMAUtils.buildSQLCondition("LOA", loa, "AND",
					StringPool.EQUAL, new String[] { "a" }));
		}

		if (breadth >= 0) {
			condition.append(VMAUtils.buildSQLCondition("Breadth", breadth,
					"AND", StringPool.EQUAL, new String[] { "a" }));
		}

		if (shownDraftxA >= 0) {
			condition.append(VMAUtils
					.buildSQLCondition("ShownDraftxA", shownDraftxA, "AND",
							StringPool.EQUAL, new String[] { "a" }));
		}

		if (clearanceHeight >= 0) {
			condition.append(VMAUtils.buildSQLCondition("ClearanceHeight",
					clearanceHeight, "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (shownDraftxF >= 0) {
			condition.append(VMAUtils
					.buildSQLCondition("ShownDraftxF", shownDraftxF, "AND",
							StringPool.EQUAL, new String[] { "a" }));
		}

		if (maxDraft >= 0) {
			condition.append(VMAUtils.buildSQLCondition("MaxDraft", maxDraft,
					"AND", StringPool.EQUAL, new String[] { "a" }));
		}

		if (Validator.isNotNull(shipAgencyCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipAgencyCode", "'"
					+ shipAgencyCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(shipOwnerCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipOwnerCode", "'"
					+ shipOwnerCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(shipOperatorCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipOperatorCode", "'"
					+ shipOperatorCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(maritimeCode)) {
			condition.append(VMAUtils.buildSQLCondition("MaritimeCode", "'"
					+ maritimeCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (violated >= 0) {
			condition.append(VMAUtils.buildSQLCondition("Violated", violated,
					"AND", StringPool.EQUAL, new String[] { "a" }));
		}

		if (deconstructed >= 0) {
			condition.append(VMAUtils.buildSQLCondition("Deconstructed",
					deconstructed, "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(registryNumber)) {
			condition.append(VMAUtils.buildSQLCondition("RegistryNumber", "'%"
					+ registryNumber + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		return sql + condition.toString();
	}
	
	public static VmaShip updateVmaShip_ItinerarySchedule(ThemeDisplay themeDisplay,
			ActionRequest actionRequest,
			VmaItinerarySchedule vmaItinerarySchedule, VmaItinerary vmaItinerary) throws SystemException{
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(request.getParameter("vmaShipId"), -1);
		VmaShip vmaShip =  new VmaShip();
		try {
			if (id > 0) {
				
					vmaShip = VmaShipLocalServiceUtil.getVmaShip(id);
				
			} else {
				vmaShip = new VmaShip();
				if (vmaItinerary.getImoNumber().trim().length() >= 7) {
					// findBy IMO, CallSign together
					vmaShip = VmaShipLocalServiceUtil
							.fetchByIMONumber_CallSign(vmaItinerary.getImoNumber(),
									vmaItinerary.getCallSign());
				} else {
					// findBy CallSign only
					vmaShip = VmaShipLocalServiceUtil.fetchByCallSign(vmaItinerary
							.getCallSign());
					// Tim lai theo registryNumber
					if (!vmaShip.getShipName().contains(vmaItinerary.getNameOfShip())) {
						vmaShip = VmaShipLocalServiceUtil.fetchByRegistryNumber(vmaItinerary.getRegistryNumber());
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		// Tiep tuc cap nhat VmaShip			
		log.info("Tiep tuc cap nhat VmaShip");
		int mtgateway = GetterUtil.getInteger(
				request.getParameter("mtgateway"), -1);
		if (mtgateway >= 0) {
			vmaShip.setMtgateway(mtgateway);
		}
		String maritimeCode = ParamUtil.getString(actionRequest,
				"maritimeCode", StringPool.BLANK);
		if (Validator.isNotNull(maritimeCode)) {
			vmaShip.setMaritimeCode(maritimeCode);
		}
		
		String shipTypeMT = ParamUtil.getString(actionRequest, "shipTypeMT",
				StringPool.BLANK);
		if (Validator.isNotNull(shipTypeMT)) {
			vmaShip.setShipTypeMT(shipTypeMT);
		}
		String shipTypeCode = ParamUtil.getString(actionRequest,
				"shipTypeCode", StringPool.BLANK);
		if (Validator.isNotNull(shipTypeCode)) {
			vmaShip.setShipTypeCode(shipTypeCode);
		}
		String shipBoat = ParamUtil.getString(actionRequest, "shipBoat",
				StringPool.BLANK);
		if (Validator.isNotNull(shipBoat)) {
			vmaShip.setShipBoat(shipBoat);
		}
		int hasTugBoat = GetterUtil.getInteger(
				request.getParameter("hasTugBoat"), -1);
		if (hasTugBoat >= 0) {
			vmaShip.setHasTugBoat(hasTugBoat);
		}
		String tugBoatName = VMAUtils.getString(actionRequest, "tugBoatName",
				StringPool.BLANK);
		if (Validator.isNotNull(tugBoatName)) {
			vmaShip.setTugBoatName(tugBoatName);
		}
		String nameOfMaster = VMAUtils.getString(actionRequest, "nameOfMaster",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfMaster)) {
			vmaShip.setNameOfMaster(nameOfMaster);
		}
		String chiefOfEngineer = VMAUtils.getString(actionRequest,
				"chiefOfEngineer", StringPool.BLANK);
		if (Validator.isNotNull(chiefOfEngineer)) {
			vmaShip.setChiefOfEngineer(chiefOfEngineer);
		}
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
				StringPool.BLANK);
		if (Validator.isNotNull(imoNumber)) {
			vmaShip.setImoNumber(imoNumber);
		}
		String callSign = VMAUtils.getString(actionRequest, "callSign",
				StringPool.BLANK);
		if (Validator.isNotNull(callSign)) {
			vmaShip.setCallSign(callSign);
		}
		String flagStateOfShip = ParamUtil.getString(actionRequest,
				"flagStateOfShip", StringPool.BLANK);
		if (Validator.isNotNull(flagStateOfShip)) {
			vmaShip.setFlagStateOfShip(flagStateOfShip);
		}
		String vrCode = ParamUtil.getString(actionRequest, "vrCode",
				StringPool.BLANK);
		if (Validator.isNotNull(vrCode)) {
			vmaShip.setVrCode(vrCode);
		}		
		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);
		if (gt >= 0) {
			vmaShip.setGt(BigDecimal.valueOf(gt));
		}
		double nt = GetterUtil.getDouble(request.getParameter("nt"), -1);
		if (nt >= 0) {
			vmaShip.setNt(BigDecimal.valueOf(nt));
		}
				
		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);
		if (loa >= 0) {
			vmaShip.setLoa(loa);
		}
		double breadth = GetterUtil.getDouble(request.getParameter("breadth"),
				-1);
		if (breadth >= 0) {
			vmaShip.setBreadth(breadth);
		}
		double clearanceHeight = GetterUtil.getDouble(
				request.getParameter("clearanceHeight"), -1);
		if (clearanceHeight >= 0) {
			vmaShip.setClearanceHeight(clearanceHeight);
		}
		double power = GetterUtil.getDouble(request.getParameter("power"), -1);
		if (power >= 0) {
			vmaShip.setPower(power);
		}
		double maxDraft = GetterUtil.getDouble(
				request.getParameter("maxDraft"), -1);
		if (maxDraft >= 0) {
			vmaShip.setMaxDraft(maxDraft);
		}
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		if (shownDraftxF >= 0) {
			vmaShip.setShownDraftxF(shownDraftxF);
		}
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);
		if (shownDraftxA >= 0) {
			vmaShip.setShownDraftxA(shownDraftxA);
		}
		String unitPower = ParamUtil.getString(actionRequest, "unitPower",
				"HP");
		if (Validator.isNotNull(unitPower)) {
			vmaShip.setUnitPower(unitPower);
		}
		String productionCountry = VMAUtils.getString(actionRequest,
				"productionCountry", StringPool.BLANK);
		if (Validator.isNotNull(productionCountry)) {
			vmaShip.setProductionCountry(productionCountry);
		}
		String productionYear = ParamUtil.getString(actionRequest,
				"productionYear", StringPool.BLANK);
		if (Validator.isNotNull(productionYear)) {
			vmaShip.setProductionYear(productionYear);
		}
		// Ngay het han dang kiem
		String expiredDate = ParamUtil.getString(actionRequest, "expiredDate",
				StringPool.BLANK);
		if (Validator.isNotNull(expiredDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(expiredDate);
				vmaShip.setExpiredDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String remarks = VMAUtils.getString(actionRequest, "remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(remarks)) {
			vmaShip.setRemarks(remarks);
		}
		int isDelete = GetterUtil.getInteger(request.getParameter("isDelete"),
				-1);
		if (isDelete >= 0) {
			vmaShip.setIsDelete(isDelete);
		}
		
		int markedAsDelete = GetterUtil.getInteger(
				request.getParameter("markedAsDelete"), -1);
		if (markedAsDelete >= 0) {
			vmaShip.setMarkedAsDelete(markedAsDelete);
		}
		String requestedDate = ParamUtil.getString(actionRequest,
				"requestedDate", StringPool.BLANK);
		if (Validator.isNotNull(requestedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(requestedDate);
				vmaShip.setRequestedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String syncVersion = ParamUtil.getString(actionRequest, "syncVersion",
				StringPool.BLANK);
		if (Validator.isNotNull(syncVersion)) {
			vmaShip.setSyncVersion(syncVersion);
		}
		String certificateOfMaster = VMAUtils.getString(actionRequest,
				"certificateOfMaster", StringPool.BLANK);
		if (Validator.isNotNull(certificateOfMaster)) {
			vmaShip.setCertificateOfMaster(certificateOfMaster);
		}
		String certificateChiefOfEngineer = VMAUtils.getString(actionRequest,
				"certificateChiefOfEngineer", StringPool.BLANK);
		if (Validator.isNotNull(certificateChiefOfEngineer)) {
			vmaShip.setCertificateChiefOfEngineer(certificateChiefOfEngineer);
		}
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaShip.setSecurityLevelCode(securityLevelCode);
		}
		int violated = GetterUtil.getInteger(request.getParameter("violated"),
				-1);
		if (violated >= 0) {
			vmaShip.setViolated(violated);
		}
		int seat = GetterUtil.getInteger(request.getParameter("seat"), -1);
		if (seat >= 0) {
			vmaShip.setSeat(seat);
		}
		int lies = GetterUtil.getInteger(request.getParameter("lies"), -1);
		if (lies >= 0) {
			vmaShip.setLies(lies);
		}
		

		int passengers = GetterUtil.getInteger(request.getParameter("passengers"), -1);
		if (passengers >= 0) {
			vmaShip.setPassengers(passengers);
		}
		
		int craneload = GetterUtil.getInteger(request.getParameter("craneload"), -1);
		if (craneload >= 0) {
			vmaShip.setCraneload(BigDecimal.valueOf(craneload));
		}
		
		String UnitCraneload = ParamUtil.getString(actionRequest,
				"unitCraneload", "MTs");
		if (Validator.isNotNull(UnitCraneload)) {
			vmaShip.setUnitCraneload(UnitCraneload);
		}
		
		// Phai kiem tra dieu kien; neu co Tau keo + Xa lan thi bo qua cap nhat Trong tai toan phan
		try {
			String xalan= StringPool.BLANK;
			List<VmaScheduleMerging> lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil.
					findByItineraryNo_NoticeShipType(vmaItinerarySchedule.getItineraryNo(), vmaItinerarySchedule.getNoticeShipType());
			if (lstVmaScheduleMerging!= null && lstVmaScheduleMerging.size() > 0) {
				int i = 0;
				for (VmaScheduleMerging vmaScheduleMerging : lstVmaScheduleMerging) {	
					if (vmaScheduleMerging.getItineraryScheduleId() == vmaItinerarySchedule.getId()) {
						i = i+1;						
					}					
				}
				if (i>0) {
					// do nothing
				} else {
					double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);
					if (dwt >= 0) {
						vmaShip.setDwt(BigDecimal.valueOf(dwt));
					}
				}
				
			}
		} catch (Exception e) {

		}
		
		// Tinh trang pha do tau
		if (vmaItinerarySchedule.getDeconstructed() == 1) {					
			vmaShip.setDeconstructed(1);
		}		
		
		// Ten tau
		if (!(vmaShip.getShipName().equalsIgnoreCase(vmaItinerarySchedule.getNameOfShip()))){
			vmaShip.setShipPreviousName(vmaShip.getShipName());
			vmaShip.setShipName(vmaItinerarySchedule.getNameOfShip());
		}
		// Chu tau
		if ((!(vmaShip.getShipOwnerCode().equalsIgnoreCase(vmaItinerarySchedule.getShipOwnerCode()))) 
		&& (vmaItinerarySchedule.getShipOwnerCode()!= null && vmaItinerarySchedule.getShipOwnerCode().length() > 0 ) ){
			vmaShip.setShipOwnerCode(vmaItinerarySchedule.getShipOwnerCode());
		}					
		// Nguoi khai thac
		if ((!(vmaShip.getShipOperatorCode().equalsIgnoreCase(vmaItinerarySchedule.getShipOperatorCode()))) 
				&& (vmaItinerarySchedule.getShipOperatorCode()!= null && vmaItinerarySchedule.getShipOperatorCode().length() > 0 ) ){
					vmaShip.setShipOperatorCode(vmaItinerarySchedule.getShipOperatorCode());
				}
		// Dai ly
		if ((!(vmaShip.getShipAgencyCode().equalsIgnoreCase(vmaItinerarySchedule.getShipAgencyCode()))) 
				&& (vmaItinerarySchedule.getShipAgencyCode()!= null && vmaItinerarySchedule.getShipAgencyCode().length() > 0 ) ){
					vmaShip.setShipAgencyCode(vmaItinerarySchedule.getShipAgencyCode());
				}		
		
		vmaShip = VmaShipLocalServiceUtil.updateVmaShip(vmaShip);
		
		
		// 68	Giấy chứng nhận khả năng chuyên môn, chứng chỉ chuyên môn thuyền viên (TNĐ)
		try {
			String pilotCertificateNo = VMAUtils.getString(actionRequest,
					"pilotCertificateNo", StringPool.BLANK);			
			int flagUpdateCertificate = 0; 
			List<VmaCertificate> objVmaCertificate = VmaCertificateLocalServiceUtil.
				findVmaCertificates(vmaShip.getShipName(), null, null, null, null, null, vmaShip.getImoNumber(), vmaShip.getCallSign(), vmaShip.getRegistryNumber(), -1, -1);
			if (Validator.isNotNull(objVmaCertificate) && objVmaCertificate.size() > 0 ) {
				for (int i = 0; i < objVmaCertificate.size(); i++) {
					if (objVmaCertificate.get(i).getCertificateCode().equalsIgnoreCase("68")) {
						VmaCertificate lastVmaCertificate = objVmaCertificate.get(i);
						lastVmaCertificate.setCertificateNo(pilotCertificateNo);
						lastVmaCertificate.setDescription(nameOfMaster);
						VmaCertificateLocalServiceUtil.updateVmaCertificate(lastVmaCertificate);
						flagUpdateCertificate = 1;
					}
				}
			}
			
			if (Validator.isNotNull(pilotCertificateNo) && (flagUpdateCertificate == 0)) {
				VmaCertificate lastVmaCertificate = new VmaCertificate();
				lastVmaCertificate.setCertificateCode("68");
				lastVmaCertificate.setCertificateNo(pilotCertificateNo);
				lastVmaCertificate.setDescription(nameOfMaster);
				lastVmaCertificate.setNameOfShip(vmaItinerary.getNameOfShip());
				lastVmaCertificate.setImoNumber(imoNumber);
				lastVmaCertificate.setCallSign(callSign);
				lastVmaCertificate.setRegistryNumber(vmaShip.getRegistryNumber());
				VmaCertificateLocalServiceUtil.addVmaCertificate(lastVmaCertificate);
			}
		} catch (Exception e) {
			// nothing to do
		}		
		return vmaShip;
	}
}
