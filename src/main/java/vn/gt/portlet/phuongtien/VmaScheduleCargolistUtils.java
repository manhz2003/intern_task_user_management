package vn.gt.portlet.phuongtien;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage;
import com.fds.nsw.nghiepvu.model.VmaScheduleCargolist;



import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleAnchorageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleCargolistLocalServiceUtil;
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
public class VmaScheduleCargolistUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaScheduleCargolistId = ParamUtil.getLong(request,
				"vmaScheduleCargolistId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaScheduleCargolist vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
					.getVmaScheduleCargolist(vmaScheduleCargolistId);
			result = VMAUtils.object2Json(vmaScheduleCargolist,
					VmaScheduleCargolist.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo sequenceNo portofAuthority documentName documentYear
	 * noticeShipType shipBoat nameOfShip shipOwnerCode shipOwnersName
	 * shipOperatorCode shipOperatorName shipAgencyCode shipAgencyName
	 * shipAgencyAddress shipAgencyContactEmail shipAgencyPhone shipAgencyFax
	 * securityLevelCode arrivalPortCode portRegionCode portHarbourCode
	 * portWharfCode gt nt dwt loa breadth clearanceHeight power maxDraft
	 * shownDraftxF shownDraftxA unitLOA unitBreadth unitClearanceHeight
	 * unitShownDraftxF unitShownDraftxA unitGRT unitNT unitDWT unitPower
	 * unitMaxDraft purposeCode purposeSpecified crewNumber passengerNumber
	 * mergeStatus shipLashRegistryNumber shipLashName mergeDateFrom
	 * modifiedDate
	 */
	public static VmaScheduleCargolist getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		VmaScheduleCargolist vmaScheduleCargolist = null;
		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleCargolistId"), -1);
		if (id > 0) {
			try {
				vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
						.getVmaScheduleCargolist(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleCargolist = new VmaScheduleCargolist();
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleCargolist.setItineraryNo(itineraryNo);
		}
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1); if (sequenceNo >= 0) {
		 * vmaScheduleCargolist.setSequenceNo(sequenceNo); }
		 */
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleCargolist.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleCargolist.setNameOfShip(nameOfShip);
		}
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		if (documentName >= 0) {
			vmaScheduleCargolist.setDocumentName(documentName);
		}
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		if (documentYear >= 0) {
			vmaScheduleCargolist.setDocumentYear(documentYear);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaScheduleCargolist.setNoticeShipType(noticeShipType);
		}
		
		int dangerCargo = GetterUtil.getInteger(
				request.getParameter("dangerCargo"), -1);
		if (dangerCargo >= 0) {
			vmaScheduleCargolist.setDangerCargo(dangerCargo);
		}
		
		String portRegionCode = ParamUtil.getString(actionRequest,
				"portRegionCode", StringPool.BLANK);
		if (Validator.isNotNull(portRegionCode)) {
			vmaScheduleCargolist.setPortRegionCode(portRegionCode);
		}
		String portHarbourCode = ParamUtil.getString(actionRequest,
				"portHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(portHarbourCode)) {
			vmaScheduleCargolist.setPortHarbourCode(portHarbourCode);
		}
		String portWharfCode = ParamUtil.getString(actionRequest,
				"portWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(portWharfCode)) {
			vmaScheduleCargolist.setPortWharfCode(portWharfCode);
		}
		String unloadingDate = ParamUtil.getString(actionRequest,
				"unloadingDate", StringPool.BLANK);
		if (Validator.isNotNull(unloadingDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(unloadingDate);
				vmaScheduleCargolist.setUnloadingDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String cargoCategory = VMAUtils.getString(actionRequest,
				"cargoCategory", StringPool.BLANK);
		if (Validator.isNotNull(cargoCategory)) {
			vmaScheduleCargolist.setCargoCategory(cargoCategory);
		}
		String cargoType = VMAUtils.getString(actionRequest, "cargoType",
				StringPool.BLANK);
		if (Validator.isNotNull(cargoType)) {
			vmaScheduleCargolist.setCargoType(cargoType);
		}
		String cargoCode = VMAUtils.getString(actionRequest, "cargoCode",
				StringPool.BLANK);
		if (Validator.isNotNull(cargoCode)) {
			vmaScheduleCargolist.setCargoCode(cargoCode);
		}
		String description = VMAUtils.getString(actionRequest, "description",
				StringPool.BLANK);
		if (Validator.isNotNull(description)) {
			vmaScheduleCargolist.setDescription(description);
		}
		double quantity = GetterUtil.getDouble(
				request.getParameter("quantity"), -1);
		if (quantity >= 0) {
			vmaScheduleCargolist.setQuantity(BigDecimal.valueOf(quantity));
		}
		String unit = VMAUtils.getString(actionRequest, "unit",
				StringPool.BLANK);
		if (Validator.isNotNull(unit)) {
			vmaScheduleCargolist.setUnit(unit);
		}
		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);
		if (makePayment >= 0) {
			vmaScheduleCargolist.setMakePayment(makePayment);
		}
		String teusQuantity = ParamUtil
				.getString(actionRequest, "teusQuantity");
		if (!teusQuantity.isEmpty() && Validator.isNotNull(teusQuantity)) {
			vmaScheduleCargolist.setTeusQuantity(BigDecimal.valueOf(Double.valueOf(teusQuantity)));
		}

		String documentaryCode = ParamUtil.getString(actionRequest,
				"documentaryCode", StringPool.BLANK);
		if (Validator.isNotNull(documentaryCode)) {
			vmaScheduleCargolist.setDocumentaryCode(documentaryCode);
		}
		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);

		if (itineraryScheduleId >= 0) {
			vmaScheduleCargolist.setItineraryScheduleId(itineraryScheduleId);
		}
		int contQuantity = GetterUtil.getInteger(
				request.getParameter("contQuantity"), -1);

		if (itineraryScheduleId >= 0) {
			vmaScheduleCargolist.setContQuantity(BigDecimal.valueOf(contQuantity));
		}

		return vmaScheduleCargolist;
	}

	public static JSONObject addVmaScheduleCargolist(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleCargolist vmaScheduleCargolist = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleCargolist == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
					.addVmaScheduleCargolist(vmaScheduleCargolist);
			result = VMAUtils.object2Json(vmaScheduleCargolist,
					VmaScheduleCargolist.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleCargolist(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleCargolist vmaScheduleCargolist = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleCargolist == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
					.updateVmaScheduleCargolist(vmaScheduleCargolist);
			result = VMAUtils.object2Json(vmaScheduleCargolist,
					VmaScheduleCargolist.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleCargolist(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaScheduleCargolistId");
		if (id > 0) {
			try {
				VmaScheduleCargolist curCargolist = VmaScheduleCargolistLocalServiceUtil
						.getVmaScheduleCargolist(id);
				if (curCargolist.getMakePayment() == 0) {
					VmaScheduleCargolistLocalServiceUtil
							.deleteVmaScheduleCargolist(curCargolist);
					return VMAUtils.createResponseMessage(LanguageUtil.get(
							themeDisplay.getLocale(), "success"), "",
							StringPool.BLANK);
				} else
					return VMAUtils.createResponseMessage(LanguageUtil.get(
							themeDisplay.getLocale(), "system_error"),
							"system_error", "Makepayment not 0");

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

	public static JSONObject autoFillCargolist(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray data = JSONFactoryUtil.createJSONArray();

		result.put("total", data.length());
		result.put("data", data);

		try {
			VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(itineraryNo);
			List<VmaScheduleCargolist> vmaScheduleCargolists = VmaScheduleCargolistLocalServiceUtil
					.findByItineraryNo(itineraryNo);

			if (vmaScheduleCargolists != null) {
				for (VmaScheduleCargolist vmaScheduleCargolist : vmaScheduleCargolists) {
					VmaScheduleCargolistLocalServiceUtil
							.delete(vmaScheduleCargolist.getId());
				}
			}

			String requestCodeIN = StringPool.BLANK;
			String requestCodeOUT = StringPool.BLANK;

			long documentNameIN = vmaItinerary.getDocumentNameIN();
			long documentYearIN = vmaItinerary.getDocumentYearIN();

			long documentNameOUT = vmaItinerary.getDocumentNameOUT();
			long documentYearOUT = vmaItinerary.getDocumentYearOUT();

			TempGeneralDeclaration generalDeclarationIN = TempGeneralDeclarationLocalServiceUtil
					.getLastByDocumentNameAndDocumentYear(documentNameIN,
							(int) documentYearIN);

			log.info("autoFillCargolist >>>>>>>>>>>> " + itineraryNo
					+ "|" + documentNameIN + "|" + documentYearIN + "|"
					+ documentNameOUT + "|" + documentYearOUT);

			if (generalDeclarationIN != null) {
				requestCodeIN = generalDeclarationIN.getRequestCode();
			}

			TempGeneralDeclaration generalDeclarationOUT = TempGeneralDeclarationLocalServiceUtil
					.getLastByDocumentNameAndDocumentYear(documentNameOUT,
							(int) documentYearOUT);

			if (generalDeclarationOUT != null) {
				requestCodeOUT = generalDeclarationOUT.getRequestCode();
			}

			List<TempCargoItems> tempCargoItemsIN = TempCargoItemsLocalServiceUtil
					.findBydocumentNameAnddocumentYearAndRequestCode(
							documentNameIN, (int) documentYearIN, requestCodeIN);

			List<TempCargoItems> tempCargoItemsOUT = TempCargoItemsLocalServiceUtil
					.findBydocumentNameAnddocumentYearAndRequestCode(
							documentNameOUT, (int) documentYearOUT,
							requestCodeOUT);

			boolean flag_C4 = false, flag_C5 = false;

			if (tempCargoItemsIN != null) {
				for (TempCargoItems tempCargoItems : tempCargoItemsIN) {
					VmaScheduleCargolist vmaScheduleCargolist = buildObject(
							vmaItinerary, tempCargoItems);
					vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
							.addVmaScheduleCargolist(vmaScheduleCargolist);

					JSONObject item = VMAUtils.object2Json(
							vmaScheduleCargolist,
							VmaScheduleCargolist.class, new String[] {
									"timeOfArrival", "timeOfDeparture" });

					data.put(item);

					if (tempCargoItems.getCargoCategory().contains("C4")) {
						flag_C4 = true;
					}
					if (tempCargoItems.getCargoCategory().contains("C5")) {
						flag_C5 = true;
					}
				}
			}

			// Ediy by Dungnv
			if (tempCargoItemsOUT != null) {
				for (TempCargoItems tempCargoItems : tempCargoItemsOUT) {
					VmaScheduleCargolist vmaScheduleCargolist = buildObject(
							vmaItinerary, tempCargoItems);
					if (tempCargoItems.getCargoCategory().contains("C4")
							&& flag_C4) {
						continue;
					} else if (tempCargoItems.getCargoCategory().contains("C5")
							&& flag_C5) {
						continue;
					} else {
						vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
								.addVmaScheduleCargolist(vmaScheduleCargolist);
						JSONObject item = VMAUtils.object2Json(
								vmaScheduleCargolist,
								VmaScheduleCargolist.class,
								new String[] { "timeOfArrival",
										"timeOfDeparture" });
						data.put(item);
					}
				}
			}

			result.put("total", data.length());
			result.put("data", data);

			// TODO update requestCodeIN, requestCodeOUT to vmaItinerary

			vmaItinerary.setInRequestCodeGeneralDeclaration(requestCodeIN);
			vmaItinerary.setOutRequestCodeGeneralDeclaration(requestCodeOUT);

			VmaItineraryLocalServiceUtil.updateVmaItinerary(vmaItinerary);

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}
	
	public static JSONObject autoFillCargolistAfterGeneralDeclarationUpdate(long documentName, int documentYear, int isArrivalFlag) {
		 
		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray data = JSONFactoryUtil.createJSONArray();

		result.put("total", data.length());
		result.put("data", data);
		String itineraryNo = StringPool.BLANK;
		try {
			VmaItinerary vmaItinerary = null;
			if (isArrivalFlag == 1) {
				vmaItinerary = VmaItineraryLocalServiceUtil
						.fetchBydocumentNameIN_documentYearIN(documentName,
								documentYear);
				
			} else if (isArrivalFlag == 0) {

				vmaItinerary = VmaItineraryLocalServiceUtil
						.fetchBydocumentNameOUT_documentYearOUT(documentName,
								documentYear);
				
			}
			
			if (Validator.isNotNull(vmaItinerary)) {
				itineraryNo = vmaItinerary.getItineraryNo();
			} else {
				log.info("Not found vmaItinerary, suspend autoFillCargolist >>>>>>>>>>>> " );
				return result;
			}

			List<VmaScheduleCargolist> vmaScheduleCargolists = VmaScheduleCargolistLocalServiceUtil
					.findByItineraryNo(itineraryNo);

			if (vmaScheduleCargolists != null) {
				for (VmaScheduleCargolist vmaScheduleCargolist : vmaScheduleCargolists) {
					VmaScheduleCargolistLocalServiceUtil
							.delete(vmaScheduleCargolist.getId());
				}
			}

			String requestCodeIN = StringPool.BLANK;
			String requestCodeOUT = StringPool.BLANK;

			long documentNameIN = vmaItinerary.getDocumentNameIN();
			long documentYearIN = vmaItinerary.getDocumentYearIN();

			long documentNameOUT = vmaItinerary.getDocumentNameOUT();
			long documentYearOUT = vmaItinerary.getDocumentYearOUT();

			TempGeneralDeclaration generalDeclarationIN = TempGeneralDeclarationLocalServiceUtil
					.getLastByDocumentNameAndDocumentYear(documentNameIN,
							(int) documentYearIN);

			log.info("autoFillCargolist >>>>>>>>>>>> " + itineraryNo
					+ "|" + documentNameIN + "|" + documentYearIN + "|"
					+ documentNameOUT + "|" + documentYearOUT);

			if (generalDeclarationIN != null) {
				requestCodeIN = generalDeclarationIN.getRequestCode();
			}

			TempGeneralDeclaration generalDeclarationOUT = TempGeneralDeclarationLocalServiceUtil
					.getLastByDocumentNameAndDocumentYear(documentNameOUT,
							(int) documentYearOUT);

			if (generalDeclarationOUT != null) {
				requestCodeOUT = generalDeclarationOUT.getRequestCode();
			}

			List<TempCargoItems> tempCargoItemsIN = TempCargoItemsLocalServiceUtil
					.findBydocumentNameAnddocumentYearAndRequestCode(
							documentNameIN, (int) documentYearIN, requestCodeIN);

			List<TempCargoItems> tempCargoItemsOUT = TempCargoItemsLocalServiceUtil
					.findBydocumentNameAnddocumentYearAndRequestCode(
							documentNameOUT, (int) documentYearOUT,
							requestCodeOUT);

			boolean flag_C4 = false, flag_C5 = false;

			if (tempCargoItemsIN != null) {
				for (TempCargoItems tempCargoItems : tempCargoItemsIN) {
					VmaScheduleCargolist vmaScheduleCargolist = buildObject(
							vmaItinerary, tempCargoItems);
					vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
							.addVmaScheduleCargolist(vmaScheduleCargolist);

					JSONObject item = VMAUtils.object2Json(
							vmaScheduleCargolist,
							VmaScheduleCargolist.class, new String[] {
									"timeOfArrival", "timeOfDeparture" });

					data.put(item);

					if (tempCargoItems.getCargoCategory().contains("C4")) {
						flag_C4 = true;
					}
					if (tempCargoItems.getCargoCategory().contains("C5")) {
						flag_C5 = true;
					}
				}
			}

			// Ediy by Dungnv
			if (tempCargoItemsOUT != null) {
				for (TempCargoItems tempCargoItems : tempCargoItemsOUT) {
					VmaScheduleCargolist vmaScheduleCargolist = buildObject(
							vmaItinerary, tempCargoItems);
					if (tempCargoItems.getCargoCategory().contains("C4")
							&& flag_C4) {
						continue;
					} else if (tempCargoItems.getCargoCategory().contains("C5")
							&& flag_C5) {
						continue;
					} else {
						vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
								.addVmaScheduleCargolist(vmaScheduleCargolist);
						JSONObject item = VMAUtils.object2Json(
								vmaScheduleCargolist,
								VmaScheduleCargolist.class,
								new String[] { "timeOfArrival",
										"timeOfDeparture" });
						data.put(item);
					}
				}
			}

			result.put("total", data.length());
			result.put("data", data);

			// TODO update requestCodeIN, requestCodeOUT to vmaItinerary

			vmaItinerary.setInRequestCodeGeneralDeclaration(requestCodeIN);
			vmaItinerary.setOutRequestCodeGeneralDeclaration(requestCodeOUT);

			VmaItineraryLocalServiceUtil.updateVmaItinerary(vmaItinerary);

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}

	public static VmaScheduleCargolist buildObject(VmaItinerary vmaItinerary,
			TempCargoItems tempCargoItems) {
		VmaScheduleCargolist vmaScheduleCargolist = new VmaScheduleCargolist();
		vmaScheduleCargolist
				.setCargoCategory(tempCargoItems.getCargoCategory());
		vmaScheduleCargolist.setCargoCode(tempCargoItems.getCargoCode());
		vmaScheduleCargolist.setCargoType(tempCargoItems.getCargoType());
		vmaScheduleCargolist.setDescription(tempCargoItems.getDescription());
		vmaScheduleCargolist.setDocumentName(tempCargoItems.getDocumentName());
		vmaScheduleCargolist.setDocumentYear(tempCargoItems.getDocumentYear());
		vmaScheduleCargolist.setItineraryNo(vmaItinerary.getItineraryNo());
		// vmaScheduleCargolist.setMakePayment(makePayment);
		vmaScheduleCargolist.setNameOfShip(vmaItinerary.getNameOfShip());
		// Edit by Dungnv
		if (tempCargoItems.getDocumentName() == vmaItinerary
				.getDocumentNameIN()) {
			vmaScheduleCargolist.setNoticeShipType(1);
			VmaItinerarySchedule vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
					.findByDocumentName_DocumentYear_NoticeShipType(
							vmaItinerary.getDocumentNameIN(),
							vmaItinerary.getDocumentYearIN(), 1);
			if (vmaItinerarySchedule != null) {
				vmaScheduleCargolist.setPortHarbourCode(vmaItinerarySchedule
						.getPortHarbourCode());
				vmaScheduleCargolist.setPortRegionCode(vmaItinerarySchedule
						.getPortRegionCode());
				vmaScheduleCargolist.setPortWharfCode(vmaItinerarySchedule
						.getPortWharfCode());
				vmaScheduleCargolist
						.setItineraryScheduleId(vmaItinerarySchedule.getId());
				VmaScheduleAnchorage vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
						.fetchByitineraryScheduleId(vmaItinerarySchedule
								.getId());
				if(vmaScheduleAnchorage != null){
					vmaScheduleCargolist.setScheduleAnchorageId(vmaScheduleAnchorage.getId());
				}
			}
		} else if (tempCargoItems.getDocumentName() == vmaItinerary
				.getDocumentNameOUT()) {
			vmaScheduleCargolist.setNoticeShipType(2);
			VmaItinerarySchedule vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
					.findByDocumentName_DocumentYear_NoticeShipType(
							vmaItinerary.getDocumentNameOUT(),
							vmaItinerary.getDocumentYearOUT(), 2);
			if (vmaItinerarySchedule != null) {
				vmaScheduleCargolist.setPortHarbourCode(vmaItinerarySchedule
						.getPortHarbourCode());
				vmaScheduleCargolist.setPortRegionCode(vmaItinerarySchedule
						.getPortRegionCode());
				vmaScheduleCargolist.setPortWharfCode(vmaItinerarySchedule
						.getPortWharfCode());
				vmaScheduleCargolist
						.setItineraryScheduleId(vmaItinerarySchedule.getId());
				VmaScheduleAnchorage vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
						.fetchByitineraryScheduleId(vmaItinerarySchedule
								.getId());
				if(vmaScheduleAnchorage != null){
					vmaScheduleCargolist.setScheduleAnchorageId(vmaScheduleAnchorage.getId());
				}
			}
		}
		// vmaScheduleCargolist.setPortHarbourCode(portHarbourCode);
		vmaScheduleCargolist.setPortofAuthority(vmaItinerary.getMaritimeCode());
		// vmaScheduleCargolist.setPortRegionCode(portRegionCode);
		// vmaScheduleCargolist.setPortWharfCode(portWharfCode);
		vmaScheduleCargolist.setQuantity(BigDecimal.valueOf(tempCargoItems.getQuantity()));
		vmaScheduleCargolist.setUnit(tempCargoItems.getUnit());
		// vmaScheduleCargolist.setUnloadingDate(unloadingDate);

		return vmaScheduleCargolist;
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

		String portRegionCode = ParamUtil.getString(resourceRequest,
				"portRegionCode", StringPool.BLANK);

		String portHarbourCode = ParamUtil.getString(resourceRequest,
				"portHarbourCode", StringPool.BLANK);

		String portWharfCode = ParamUtil.getString(resourceRequest,
				"portWharfCode", StringPool.BLANK);

		String unloadingDate = ParamUtil.getString(resourceRequest,
				"unloadingDate", StringPool.BLANK);

		String cargoCategory = ParamUtil.getString(resourceRequest,
				"cargoCategory", StringPool.BLANK);

		String cargoType = ParamUtil.getString(resourceRequest, "cargoType",
				StringPool.BLANK);

		String cargoCode = ParamUtil.getString(resourceRequest, "cargoCode",
				StringPool.BLANK);

		String description = ParamUtil.getString(resourceRequest,
				"description", StringPool.BLANK);

		double quantity = GetterUtil.getDouble(
				request.getParameter("quantity"), -1);

		String unit = ParamUtil.getString(resourceRequest, "unit",
				StringPool.BLANK);

		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);

		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);

		try {
			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, documentName > 0 ? documentName : null,
					documentYear > 0 ? documentYear : null, nameOfShip,
					unloadingDate, cargoCategory, portHarbourCode,
					portWharfCode, cargoCode, cargoType,
					quantity > 0 ? quantity : null, null, null, null, null,
					timeOfArrival, timeOfDeparture, description, makePayment,
					documentaryCode, itineraryScheduleId);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, documentName > 0 ? documentName : null,
					documentYear > 0 ? documentYear : null, nameOfShip,
					unloadingDate, cargoCategory, portHarbourCode,
					portWharfCode, cargoCode, cargoType,
					quantity > 0 ? quantity : null, null, null, null, null,
					timeOfArrival, timeOfDeparture, description, makePayment,
					documentaryCode, itineraryScheduleId);
			return VmaScheduleCargolistLocalServiceUtil
					.findVmaScheduleCargolist(searchQuery, countQuery, start,
							end);
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		} finally {
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

		String portRegionCode = ParamUtil.getString(resourceRequest,
				"portRegionCode", StringPool.BLANK);

		String portHarbourCode = ParamUtil.getString(resourceRequest,
				"portHarbourCode", StringPool.BLANK);

		String portWharfCode = ParamUtil.getString(resourceRequest,
				"portWharfCode", StringPool.BLANK);

		String unloadingDate = ParamUtil.getString(resourceRequest,
				"unloadingDate", StringPool.BLANK);

		String cargoCategory = ParamUtil.getString(resourceRequest,
				"cargoCategory", StringPool.BLANK);

		String cargoType = ParamUtil.getString(resourceRequest, "cargoType",
				StringPool.BLANK);

		String cargoCode = ParamUtil.getString(resourceRequest, "cargoCode",
				StringPool.BLANK);

		String description = ParamUtil.getString(resourceRequest,
				"description", StringPool.BLANK);

		double quantity = GetterUtil.getDouble(
				request.getParameter("quantity"), -1);

		String unit = ParamUtil.getString(resourceRequest, "unit",
				StringPool.BLANK);

		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);

		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);

		/*
		 * String[] headers =
		 * "STT","Tên tàu","Thời gian đến","Thời gian đi","Thời gian làm hàng"
		 * ,"Hình thức H.H qua cảng"
		 * ,"Bến cảng làm hàng","Vị trí làm hàng","Hàng hóa"
		 * ,"Số container","Số TEU"
		 * ,"Số TEU rỗng","Số tấn","Có hàng nguy hiểm","Ghi chú" };
		 */

		String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
				"Th\u1EDDi gian \u0111\u1EBFn", "Th\u1EDDi gian \u0111i",
				"Th\u1EDDi gian l\u00E0m h\u00E0ng",
				"H\u00ECnh th\u1EE9c H.H qua c\u1EA3ng",
				"B\u1EBFn c\u1EA3ng l\u00E0m h\u00E0ng",
				"V\u1ECB tr\u00ED l\u00E0m h\u00E0ng", "H\u00E0ng h\u00F3a",
				"S\u1ED1 container", "S\u1ED1 TEU", "S\u1ED1 TEU r\u1ED7ng",
				"S\u1ED1 t\u1EA5n", "C\u00F3 h\u00E0ng nguy hi\u1EC3m",
				"Ghi ch\u00FA" };

		String[][] exportData = null;

		try {
			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, documentName > 0 ? documentName : null,
					documentYear > 0 ? documentYear : null, nameOfShip,
					unloadingDate, cargoCategory, portHarbourCode,
					portWharfCode, cargoCode, cargoType,
					quantity > 0 ? quantity : null, null, null, null, null,
					timeOfArrival, timeOfDeparture, description, makePayment,
					documentaryCode, itineraryScheduleId);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, documentName > 0 ? documentName : null,
					documentYear > 0 ? documentYear : null, nameOfShip,
					unloadingDate, cargoCategory, portHarbourCode,
					portWharfCode, cargoCode, cargoType,
					quantity > 0 ? quantity : null, null, null, null, null,
					timeOfArrival, timeOfDeparture, description, makePayment,
					documentaryCode, itineraryScheduleId);

			JSONObject objects = VmaScheduleCargolistLocalServiceUtil
					.findVmaScheduleCargolist(searchQuery, countQuery, -1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");

					timeOfArrival = object.getString("timeOfArrival");

					timeOfDeparture = object.getString("timeOfDeparture");

					unloadingDate = object.getString("unloadingDate");

					cargoCategory = object.getString("cargoCategory");

					String portHarbourName = object
							.getString("portHarbourName");
					String portWharfName = object.getString("portWharfName");

					cargoType = object.getString("cargoType");

					quantity = object.getDouble("quantity");

					String numberOfTEU = StringPool.BLANK;

					String numberOfEmptyTEU = StringPool.BLANK;

					String dwt = StringPool.BLANK;

					String dangerCargo = StringPool.BLANK;

					description = object.getString("description");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = timeOfArrival;
					exportData[i][3] = timeOfDeparture;
					exportData[i][4] = unloadingDate;
					exportData[i][5] = cargoCategory;
					exportData[i][6] = portHarbourName;
					exportData[i][7] = portWharfName;
					exportData[i][8] = cargoType;
					exportData[i][9] = String.valueOf(quantity);
					exportData[i][10] = numberOfTEU;
					exportData[i][11] = numberOfEmptyTEU;
					exportData[i][12] = String.valueOf(dwt);
					exportData[i][13] = dangerCargo;
					exportData[i][14] = description;

				}
			}

			String sheetName = "VMA-Schedule-Cargo";

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

		String portRegionCode = ParamUtil.getString(resourceRequest,
				"portRegionCode", StringPool.BLANK);

		String portHarbourCode = ParamUtil.getString(resourceRequest,
				"portHarbourCode", StringPool.BLANK);

		String portWharfCode = ParamUtil.getString(resourceRequest,
				"portWharfCode", StringPool.BLANK);

		String unloadingDate = ParamUtil.getString(resourceRequest,
				"unloadingDate", StringPool.BLANK);

		String cargoCategory = ParamUtil.getString(resourceRequest,
				"cargoCategory", StringPool.BLANK);

		String cargoType = ParamUtil.getString(resourceRequest, "cargoType",
				StringPool.BLANK);

		String cargoCode = ParamUtil.getString(resourceRequest, "cargoCode",
				StringPool.BLANK);

		String description = ParamUtil.getString(resourceRequest,
				"description", StringPool.BLANK);

		double quantity = GetterUtil.getDouble(
				request.getParameter("quantity"), -1);

		String unit = ParamUtil.getString(resourceRequest, "unit",
				StringPool.BLANK);

		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);

		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);

		try {
			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, documentName > 0 ? documentName : null,
					documentYear > 0 ? documentYear : null, nameOfShip,
					unloadingDate, cargoCategory, portHarbourCode,
					portWharfCode, cargoCode, cargoType,
					quantity > 0 ? quantity : null, null, null, null, null,
					timeOfArrival, timeOfDeparture, description, makePayment,
					documentaryCode, itineraryScheduleId);
			return VmaScheduleCargolistLocalServiceUtil
					.countVmaScheduleCargolist(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());

			return 0;
		}
	}

	private static String generateQuery(String cmd, String itineraryNo,
			String portofAuthority, Long documentName, Integer documentYear,
			String nameOfShip, String unloadingDate, String cargoCategory,
			String portHarbourCode, String portWharfCode, String cargoCode,
			String cargoType, Double quantity, Double numberOfTEU,
			Double numberOfEmptyTEU, Double dwt, Boolean dangerCargo,
			String timeOfArrival, String timeOfDeparture, String description,
			Integer makePayment, String documentaryCode,
			long itineraryScheduleId) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_schedule_cargolist as a";
		} else {
			sql = "SELECT a.* FROM vma_schedule_cargolist AS a";
		}

		if (Validator.isNotNull(timeOfArrival)
				|| Validator.isNotNull(timeOfDeparture)) {
			sql += " INNER JOIN vma_itinerary as b ON a.itineraryNo = b.itineraryNo";
			if (Validator.isNotNull(timeOfArrival)) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(timeOfArrival);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					sql += VMAUtils.buildSQLCondition("b.TimeOfArrival", "'"
							+ strDate + " 00:00:00'" + " AND '" + strDate
							+ " 23:59:59'", "AND", StringPool.BETWEEN);
				}
			}

			if (Validator.isNotNull(timeOfDeparture)) {

				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(timeOfDeparture);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					sql += VMAUtils.buildSQLCondition("b.TimeOfDeparture", "'"
							+ strDate + " 00:00:00'" + " AND '" + strDate
							+ " 23:59:59'", "AND", StringPool.BETWEEN);
				}

			}
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("NameOfShip", "'%"
					+ nameOfShip + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(cargoCategory)) {
			condition.append(VMAUtils.buildSQLCondition("CargoCategory", "'%"
					+ cargoCategory + "%'", "AND", StringPool.LIKE));
		}

		/*
		 * if (numberOfTEU != null) {
		 * condition.append(VMAUtils.buildSQLCondition("numberOfTEU",
		 * numberOfTEU, "AND", StringPool.EQUAL)); }
		 * 
		 * if (numberOfEmptyTEU != null) {
		 * condition.append(VMAUtils.buildSQLCondition("numberOfEmptyTEU",
		 * numberOfEmptyTEU, "AND", StringPool.EQUAL)); }
		 * 
		 * if (dangerCargo != null) {
		 * condition.append(VMAUtils.buildSQLCondition("dangerCargo",
		 * dangerCargo, "AND", StringPool.EQUAL)); }
		 */

		if (quantity != null) {
			condition.append(VMAUtils.buildSQLCondition("Quantity", quantity,
					"AND", StringPool.EQUAL));
		}

		/*
		 * if (dwt != null) { condition.append(VMAUtils.buildSQLCondition("DWT",
		 * dwt, "AND", StringPool.EQUAL)); }
		 */

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

		if (Validator.isNotNull(portHarbourCode)) {
			condition.append(VMAUtils.buildSQLCondition("PortHarbourCode", "'"
					+ portHarbourCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(portWharfCode)) {
			condition.append(VMAUtils.buildSQLCondition("PortWharfCode", "'"
					+ portWharfCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(cargoCode)) {
			condition.append(VMAUtils.buildSQLCondition("CargoCode", "'"
					+ cargoCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(cargoType)) {
			condition.append(VMAUtils.buildSQLCondition("CargoType", "'"
					+ cargoType + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(unloadingDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(unloadingDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("TestingFrom", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL));
		}

		if (makePayment > -1) {
			condition.append(VMAUtils.buildSQLCondition("MakePayment",
					makePayment, "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(documentaryCode)) {
			condition.append(VMAUtils.buildSQLCondition("DocumentaryCode", "'"
					+ documentaryCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(itineraryScheduleId)
				&& itineraryScheduleId >= 0) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryScheduleId",
					"'" + itineraryScheduleId + "'", "AND", StringPool.EQUAL));
		}

		return sql + condition.toString();
	}

	public static JSONObject updateVmaScheduleCargolist_MakePayment_DocumentaryCode(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray results = JSONFactoryUtil.createJSONArray();

		String documentaryCode = ParamUtil
				.getString(request, "documentaryCode");
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		String makePayment = ParamUtil.getString(request, "makePayment");
		String vmaScheduleCargolistId = ParamUtil.getString(request,
				"vmaScheduleCargolistId");
		String[] arrayId = vmaScheduleCargolistId.split(",");
		if (arrayId != null && arrayId.length > 0) {
			for (int i = 0; i < arrayId.length; i++) {
				VmaScheduleCargolist vmaScheduleCargolist = null;
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				try {
					vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
							.fetchVmaScheduleCargolist(Long.valueOf(arrayId[i]));
					if (vmaScheduleCargolist != null) {
						if (vmaScheduleCargolist.getItineraryNo().equals(
								itineraryNo)) {
							vmaScheduleCargolist
									.setDocumentaryCode(documentaryCode);
							vmaScheduleCargolist.setMakePayment(Integer
									.valueOf(makePayment));

							vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
									.updateVmaScheduleCargolist(vmaScheduleCargolist);
							obj = VMAUtils.object2Json(vmaScheduleCargolist,
									VmaScheduleCargolist.class);

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

	public static JSONObject updateVmaScheduleCargolistByDocumentaryCode_ItineraryNo(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray results = JSONFactoryUtil.createJSONArray();

		String documentaryCode = ParamUtil
				.getString(request, "documentaryCode");
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		String makePayment = ParamUtil.getString(request, "makePayment");

		List<VmaScheduleCargolist> vmaScheduleCargolists = VmaScheduleCargolistLocalServiceUtil
				.findByItineraryNo_documentaryCode(itineraryNo, documentaryCode);
		if (vmaScheduleCargolists != null && !vmaScheduleCargolists.isEmpty()) {
			for (VmaScheduleCargolist vmaScheduleCargolist : vmaScheduleCargolists) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				try {
					vmaScheduleCargolist.setMakePayment(Integer
							.valueOf(makePayment));
					vmaScheduleCargolist = VmaScheduleCargolistLocalServiceUtil
							.updateVmaScheduleCargolist(vmaScheduleCargolist);

					obj = VMAUtils.object2Json(vmaScheduleCargolist,
							VmaScheduleCargolist.class);

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
