package vn.gt.portlet.phuongtien;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaScheduleCargolist;
import com.fds.nsw.nghiepvu.model.VmaScheduleMerging;
import com.fds.nsw.nghiepvu.model.VmaShip;




import vn.gt.dao.noticeandmessage.service.VmaScheduleCargolistLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleMergingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaShipLocalServiceUtil;
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
public class VmaScheduleMergingUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaScheduleMergingId = ParamUtil.getLong(request,
				"vmaScheduleMergingId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaScheduleMerging vmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
					.getVmaScheduleMerging(vmaScheduleMergingId);
			result = VMAUtils.object2Json(vmaScheduleMerging,
					VmaScheduleMerging.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static List<VmaScheduleMerging> getObjectsFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
			throws JSONException {
		
		List<VmaScheduleMerging> vmaScheduleMergings = new ArrayList<VmaScheduleMerging>();
		String mergings = VMAUtils.getString(actionRequest, "json",
				StringPool.BLANK);
		log.debug("========>>>>>>>>>>>>>> JSON Data Mergings: " + mergings);
		
		JSONArray array = null;

		if (Validator.isNotNull(mergings)) {
			array = JSONFactoryUtil.createJSONArray(mergings);
		}
		if (array != null) {
			for (int i = 0; i < array.length(); i++) {
				VmaScheduleMerging vmaScheduleMerging = new VmaScheduleMerging();
				JSONObject object = array.getJSONObject(i);

				if (object.has("itineraryNo")) {
					String itineraryNo = object.getString("itineraryNo");
					vmaScheduleMerging.setItineraryNo(itineraryNo);
				}
				if (object.has("portofAuthority")) {
					String portofAuthority = object
							.getString("portofAuthority");
					vmaScheduleMerging.setPortofAuthority(portofAuthority);
				}
				if (object.has("documentName")) {
					long documentName = object.getLong("documentName");
					vmaScheduleMerging.setDocumentName(documentName);
				}
				if (object.has("documentYear")) {
					int documentYear = object.getInt("documentYear");
					vmaScheduleMerging.setDocumentYear(documentYear);
				}
				if (object.has("noticeShipType")) {
					int noticeShipType = object.getInt("noticeShipType");
					vmaScheduleMerging.setNoticeShipType(noticeShipType);
				}

				String imoNumber = StringPool.BLANK;
				String callSign = StringPool.BLANK;
				String registryNumber = StringPool.BLANK;
				if (object.has("shipLashIMONumber")) {
					String shipLashIMONumber = object
							.getString("shipLashIMONumber");
					vmaScheduleMerging.setShipLashIMONumber(shipLashIMONumber);
					imoNumber = shipLashIMONumber;
				}
				if (object.has("shipLashCallSign")) {
					String shipLashCallSign = object
							.getString("shipLashCallSign");
					vmaScheduleMerging.setShipLashCallSign(shipLashCallSign);
					callSign = shipLashCallSign;
				}
				if (object.has("shipLashFlagStateOfShip")) {
					String shipLashFlagStateOfShip = object
							.getString("shipLashFlagStateOfShip");
					vmaScheduleMerging
							.setShipLashFlagStateOfShip(shipLashFlagStateOfShip);
				}
				if (object.has("shipLashVRCode")) {
					String shipLashVRCode = object.getString("shipLashVRCode");
					vmaScheduleMerging.setShipLashVRCode(shipLashVRCode);
				}
				if (object.has("shipLashRegistryNumber")) {
					String shipLashRegistryNumber = object
							.getString("shipLashRegistryNumber");
					vmaScheduleMerging
							.setShipLashRegistryNumber(shipLashRegistryNumber);
					registryNumber = shipLashRegistryNumber;
				}
				if (object.has("arrivalPortCode")) {
					String arrivalPortCode = object
							.getString("arrivalPortCode");
					vmaScheduleMerging.setArrivalPortCode(arrivalPortCode);
				}
				if (object.has("portRegionCode")) {
					String portRegionCode = object.getString("portRegionCode");
					vmaScheduleMerging.setPortRegionCode(portRegionCode);
				}
				if (object.has("portHarbourCode")) {
					String portHarbourCode = object
							.getString("portHarbourCode");
					vmaScheduleMerging.setPortHarbourCode(portHarbourCode);
				}
				if (object.has("portWharfCode")) {
					String portWharfCode = object.getString("portWharfCode");
					vmaScheduleMerging.setPortWharfCode(portWharfCode);
				}
				if (object.has("purposeCode")) {
					String purposeCode = object.getString("purposeCode");
					vmaScheduleMerging.setPurposeCode(purposeCode);
				}
				if (object.has("purposeSpecified")) {
					String purposeSpecified = object
							.getString("purposeSpecified");
					vmaScheduleMerging.setPurposeSpecified(purposeSpecified);
				}
				if (object.has("crewNumber")) {
					int crewNumber = object.getInt("crewNumber");
					vmaScheduleMerging.setCrewNumber(BigDecimal.valueOf(crewNumber));
				}
				if (object.has("passengerNumber")) {
					int passengerNumber = object.getInt("passengerNumber");
					vmaScheduleMerging.setPassengerNumber(BigDecimal.valueOf(passengerNumber));
				}
				if (object.has("mergeStatus")) {
					String mergeStatus = object.getString("mergeStatus");
					vmaScheduleMerging.setMergeStatus(mergeStatus);
				}
				if (object.has("shipLashName")) {
					String shipLashName = object.getString("shipLashName");
					vmaScheduleMerging.setShipLashName(shipLashName);
				}
				if (object.has("mergeDateFrom")) {
					String mergeDateFrom = object.getString("mergeDateFrom");

					if (Validator.isNotNull(mergeDateFrom)) {
						try {
							Date date = FormatData.formatDateShort3
									.parse(mergeDateFrom);
							vmaScheduleMerging.setMergeDateFrom(date);
						} catch (ParseException e) {
							log.error(e.getMessage());
						}
					}
				}
				if (object.has("mergeDateTo")) {
					String mergeDateTo = object.getString("mergeDateTo");
					if (Validator.isNotNull(mergeDateTo)) {
						try {
							Date date = FormatData.formatDateShort3
									.parse(mergeDateTo);
							vmaScheduleMerging.setMergeDateTo(date);
						} catch (ParseException e) {
							log.error(e.getMessage());
						}
					}
				}
				if (object.has("nameOfShip")) {
					vmaScheduleMerging.setNameOfShip(object.getString("nameOfShip"));
				}
				VmaShip vmaShip = null;
				vmaShip = VmaShipLocalServiceUtil
						.fetchByIMONumber_CallSign_RegistryNumber(imoNumber,
								callSign, registryNumber);
				if (vmaShip == null) {
					try {
						vmaShip = VmaShipLocalServiceUtil
								.fetchByIMONumber_CallSign(imoNumber, callSign);
					} catch (Exception e) {
						vmaShip = null;
					}
					if (vmaShip == null) {
						try {
							vmaShip = VmaShipLocalServiceUtil
									.fetchByRegistryNumber(registryNumber);
						} catch (Exception e) {
						}
					}
				}
				if (vmaShip != null) {
					vmaScheduleMerging.setShipBoat(vmaShip.getShipBoat());
					
					vmaScheduleMerging.setShipOwnerCode(vmaShip
							.getShipOwnerCode());
					try {
						vmaScheduleMerging
								.setShipOwnersName(DmVmaShipOwnerLocalServiceUtil
										.fetchByShipOwnerCode(
												vmaShip.getShipOwnerCode())
										.getCompanyName());
					} catch (Exception e) {
					}
					vmaScheduleMerging.setShipOperatorCode(vmaShip
							.getShipOperatorCode());
					try {
						vmaScheduleMerging
								.setShipOperatorName(DmVmaShipOwnerLocalServiceUtil
										.fetchByShipOwnerCode(
												vmaShip.getShipOperatorCode())
										.getCompanyName());
					} catch (Exception e) {
					}
					vmaScheduleMerging.setShipAgencyCode(vmaShip
							.getShipAgencyCode());
					try {
						vmaScheduleMerging
								.setShipAgencyName(DmShipAgencyLocalServiceUtil
										.fetchByShipAgencyCode(
												vmaShip.getShipAgencyCode())
										.getShipAgencyName());
						vmaScheduleMerging
								.setShipAgencyAddress(DmShipAgencyLocalServiceUtil
										.fetchByShipAgencyCode(
												vmaShip.getShipAgencyCode())
										.getAddress());
						vmaScheduleMerging
								.setShipAgencyContactEmail(DmShipAgencyLocalServiceUtil
										.fetchByShipAgencyCode(
												vmaShip.getShipAgencyCode())
										.getEmail());
						vmaScheduleMerging
								.setShipAgencyPhone(DmShipAgencyLocalServiceUtil
										.fetchByShipAgencyCode(
												vmaShip.getShipAgencyCode())
										.getPhone());
						vmaScheduleMerging
								.setShipAgencyFax(DmShipAgencyLocalServiceUtil
										.fetchByShipAgencyCode(
												vmaShip.getShipAgencyCode())
										.getFax());
					} catch (Exception e) {
					}
					vmaScheduleMerging.setSecurityLevelCode(vmaShip
							.getSecurityLevelCode());
					vmaScheduleMerging.setGt(vmaShip.getGt());
					vmaScheduleMerging.setNt(vmaShip.getNt());
					vmaScheduleMerging.setDwt(vmaShip.getDwt());
					vmaScheduleMerging.setLoa(vmaShip.getLoa());
					vmaScheduleMerging.setBreadth(vmaShip.getBreadth());
					vmaScheduleMerging.setClearanceHeight(vmaShip
							.getClearanceHeight());
					vmaScheduleMerging.setPower(vmaShip.getPower());
					vmaScheduleMerging.setMaxDraft(vmaShip.getMaxDraft());
					vmaScheduleMerging.setShownDraftxF(vmaShip
							.getShownDraftxF());
					vmaScheduleMerging.setShownDraftxA(vmaShip
							.getShownDraftxA());
					String unitLOA = "MTR";
					vmaScheduleMerging.setUnitLOA(unitLOA);
					String unitBreadth = "MTR";
					vmaScheduleMerging.setUnitBreadth(unitBreadth);
					String unitClearanceHeight = "MTR";
					vmaScheduleMerging
							.setUnitClearanceHeight(unitClearanceHeight);
					String unitMaxDraft = "MTR";
					vmaScheduleMerging.setUnitMaxDraft(unitMaxDraft);
					String unitShownDraftxF = "MTR";
					vmaScheduleMerging.setUnitShownDraftxF(unitShownDraftxF);
					String unitShownDraftxA = "MTR";
					vmaScheduleMerging.setUnitShownDraftxA(unitShownDraftxA);
					String unitGRT = "GT";
					vmaScheduleMerging.setUnitGRT(unitGRT);
					String unitNT = "TNE";
					vmaScheduleMerging.setUnitNT(unitNT);
					String unitDWT = "TNE";
					vmaScheduleMerging.setUnitDWT(unitDWT);
					vmaScheduleMerging.setUnitPower(vmaShip.getUnitPower());

					vmaScheduleMergings.add(vmaScheduleMerging);
				}
			}
		}
		return vmaScheduleMergings;
	}

	public static VmaScheduleMerging getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleMergingId"), -1);
		VmaScheduleMerging vmaScheduleMerging = null;
		if (id > 0) {
			try {
				vmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
						.getVmaScheduleMerging(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleMerging = new VmaScheduleMerging();
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleMerging.setItineraryNo(itineraryNo);
		}

		// tu tang

		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1); if (sequenceNo >= 0) {
		 * vmaScheduleMerging.setSequenceNo(sequenceNo); }
		 */
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleMerging.setPortofAuthority(portofAuthority);
		}
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		if (documentName >= 0) {
			vmaScheduleMerging.setDocumentName(documentName);
		}
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		if (documentYear >= 0) {
			vmaScheduleMerging.setDocumentYear(documentYear);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaScheduleMerging.setNoticeShipType(noticeShipType);
		}
		String shipBoat = VMAUtils.getString(actionRequest, "shipBoat",
				StringPool.BLANK);
		if (Validator.isNotNull(shipBoat)) {
			vmaScheduleMerging.setShipBoat(shipBoat);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleMerging.setNameOfShip(nameOfShip);
		}
		String shipOwnerCode = VMAUtils.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaScheduleMerging.setShipOwnerCode(shipOwnerCode);
		}
		String shipOwnersName = VMAUtils.getString(actionRequest,
				"shipOwnersName", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnersName)) {
			vmaScheduleMerging.setShipOwnersName(shipOwnersName);
		}
		String shipOperatorCode = VMAUtils.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaScheduleMerging.setShipOperatorCode(shipOperatorCode);
		}
		String shipOperatorName = VMAUtils.getString(actionRequest,
				"shipOperatorName", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorName)) {
			vmaScheduleMerging.setShipOperatorName(shipOperatorName);
		}
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaScheduleMerging.setShipAgencyCode(shipAgencyCode);
		}
		String shipAgencyName = VMAUtils.getString(actionRequest,
				"shipAgencyName", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyName)) {
			vmaScheduleMerging.setShipAgencyName(shipAgencyName);
		}
		String shipAgencyAddress = VMAUtils.getString(actionRequest,
				"shipAgencyAddress", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyAddress)) {
			vmaScheduleMerging.setShipAgencyAddress(shipAgencyAddress);
		}
		String shipAgencyContactEmail = VMAUtils.getString(actionRequest,
				"shipAgencyContactEmail", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyContactEmail)) {
			vmaScheduleMerging
					.setShipAgencyContactEmail(shipAgencyContactEmail);
		}
		String shipAgencyPhone = VMAUtils.getString(actionRequest,
				"shipAgencyPhone", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyPhone)) {
			vmaScheduleMerging.setShipAgencyPhone(shipAgencyPhone);
		}
		String shipAgencyFax = VMAUtils.getString(actionRequest,
				"shipAgencyFax", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyFax)) {
			vmaScheduleMerging.setShipAgencyFax(shipAgencyFax);
		}
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaScheduleMerging.setSecurityLevelCode(securityLevelCode);
		}
		String arrivalPortCode = ParamUtil.getString(actionRequest,
				"arrivalPortCode", StringPool.BLANK);
		if (Validator.isNotNull(arrivalPortCode)) {
			vmaScheduleMerging.setArrivalPortCode(arrivalPortCode);
		}
		String portRegionCode = ParamUtil.getString(actionRequest,
				"portRegionCode", StringPool.BLANK);
		if (Validator.isNotNull(portRegionCode)) {
			vmaScheduleMerging.setPortRegionCode(portRegionCode);
		}
		String portHarbourCode = ParamUtil.getString(actionRequest,
				"portHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(portHarbourCode)) {
			vmaScheduleMerging.setPortHarbourCode(portHarbourCode);
		}
		String portWharfCode = ParamUtil.getString(actionRequest,
				"portWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(portWharfCode)) {
			vmaScheduleMerging.setPortWharfCode(portWharfCode);
		}

		String purposeCode = VMAUtils.getString(actionRequest, "purposeCode",
				StringPool.BLANK);
		if (Validator.isNotNull(purposeCode)) {
			vmaScheduleMerging.setPurposeCode(purposeCode);
		}
		String purposeSpecified = VMAUtils.getString(actionRequest,
				"purposeSpecified", StringPool.BLANK);
		if (Validator.isNotNull(purposeSpecified)) {
			vmaScheduleMerging.setPurposeSpecified(purposeSpecified);
		}
		int crewNumber = GetterUtil.getInteger(
				request.getParameter("crewNumber"), -1);
		if (crewNumber >= 0) {
			vmaScheduleMerging.setCrewNumber(BigDecimal.valueOf(crewNumber));
		}
		int passengerNumber = GetterUtil.getInteger(
				request.getParameter("passengerNumber"), -1);
		if (passengerNumber >= 0) {
			vmaScheduleMerging.setPassengerNumber(BigDecimal.valueOf(passengerNumber));
		}
		String mergeStatus = VMAUtils.getString(actionRequest, "mergeStatus",
				StringPool.BLANK);
		if (Validator.isNotNull(mergeStatus)) {
			vmaScheduleMerging.setMergeStatus(mergeStatus);
		}

		String shipLashRegistryNumber = VMAUtils.getString(actionRequest,
				"shipLashRegistryNumber", StringPool.BLANK);
		if (Validator.isNotNull(shipLashRegistryNumber)) {
			vmaScheduleMerging
					.setShipLashRegistryNumber(shipLashRegistryNumber);
		}
		String shipLashName = VMAUtils.getString(actionRequest, "shipLashName",
				StringPool.BLANK);
		if (Validator.isNotNull(shipLashName)) {
			vmaScheduleMerging.setShipLashName(shipLashName);
		}
		String mergeDateFrom = ParamUtil.getString(actionRequest,
				"mergeDateFrom", StringPool.BLANK);

		if (Validator.isNotNull(mergeDateFrom)) {
			try {
				Date date = FormatData.formatDateShort3.parse(mergeDateFrom);
				vmaScheduleMerging.setMergeDateFrom(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String mergeDateTo = ParamUtil.getString(actionRequest, "mergeDateTo",
				StringPool.BLANK);

		if (Validator.isNotNull(mergeDateTo)) {
			try {
				Date date = FormatData.formatDateShort3.parse(mergeDateTo);
				vmaScheduleMerging.setMergeDateTo(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		VmaShip vmaShipLash = new VmaShip();
		try {
			if (Validator.isNotNull(shipLashRegistryNumber)) {
				vmaShipLash = VmaShipLocalServiceUtil
						.fetchByRegistryNumber(shipLashRegistryNumber);
			}
			if (!vmaShipLash.getShipName().contains(shipLashName)) {
				vmaShipLash = new VmaShip();
			} else {
				double gt = vmaShipLash.getGt().doubleValue();
				if (gt >= 0) {
					vmaScheduleMerging.setGt(BigDecimal.valueOf(gt));
				}
				double nt = vmaShipLash.getNt().doubleValue();
				if (nt >= 0) {
					vmaScheduleMerging.setNt(BigDecimal.valueOf(nt));
				}
				double dwt = vmaShipLash.getDwt().doubleValue();
				if (dwt >= 0) {
					vmaScheduleMerging.setDwt(BigDecimal.valueOf(dwt));
				}
				double loa = vmaShipLash.getLoa();
				if (loa >= 0) {
					vmaScheduleMerging.setLoa(loa);
				}
				double breadth = vmaShipLash.getBreadth();
				if (breadth >= 0) {
					vmaScheduleMerging.setBreadth(breadth);
				}
				double clearanceHeight = vmaShipLash.getClearanceHeight();
				if (clearanceHeight >= 0) {
					vmaScheduleMerging.setClearanceHeight(clearanceHeight);
				}
				double power = vmaShipLash.getPower();
				if (power >= 0) {
					vmaScheduleMerging.setPower(power);
				}
				double maxDraft = vmaShipLash.getMaxDraft();
				if (maxDraft >= 0) {
					vmaScheduleMerging.setMaxDraft(maxDraft);
				}
				double shownDraftxF = vmaShipLash.getShownDraftxF();
				if (shownDraftxF >= 0) {
					vmaScheduleMerging.setShownDraftxF(shownDraftxF);
				}
				double shownDraftxA = vmaShipLash.getShownDraftxA();
				if (shownDraftxA >= 0) {
					vmaScheduleMerging.setShownDraftxA(shownDraftxA);
				}
				String unitLOA = "MTR";
				if (Validator.isNotNull(unitLOA)) {
					vmaScheduleMerging.setUnitLOA(unitLOA);
				}
				String unitBreadth = "MTR";
				if (Validator.isNotNull(unitBreadth)) {
					vmaScheduleMerging.setUnitBreadth(unitBreadth);
				}
				String unitClearanceHeight = "MTR";
				if (Validator.isNotNull(unitClearanceHeight)) {
					vmaScheduleMerging
							.setUnitClearanceHeight(unitClearanceHeight);
				}
				String unitMaxDraft = "MTR";
				if (Validator.isNotNull(unitMaxDraft)) {
					vmaScheduleMerging.setUnitMaxDraft(unitMaxDraft);
				}
				String unitShownDraftxF = "MTR";
				if (Validator.isNotNull(unitShownDraftxF)) {
					vmaScheduleMerging.setUnitShownDraftxF(unitShownDraftxF);
				}
				String unitShownDraftxA = "MTR";
				if (Validator.isNotNull(unitShownDraftxA)) {
					vmaScheduleMerging.setUnitShownDraftxA(unitShownDraftxA);
				}
				String unitGRT = "GT";
				if (Validator.isNotNull(unitGRT)) {
					vmaScheduleMerging.setUnitGRT(unitGRT);
				}
				String unitNT = "TNE";
				if (Validator.isNotNull(unitNT)) {
					vmaScheduleMerging.setUnitNT(unitNT);
				}
				String unitDWT = "TNE";
				if (Validator.isNotNull(unitDWT)) {
					vmaScheduleMerging.setUnitDWT(unitDWT);
				}
				String unitPower = vmaShipLash.getUnitPower();
				if (Validator.isNotNull(unitPower)) {
					vmaScheduleMerging.setUnitPower(unitPower);
				}

			}
		} catch (Exception e) {
		}

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);
		if (itineraryScheduleId >= 0) {
			vmaScheduleMerging.setItineraryScheduleId(itineraryScheduleId);
		}

		return vmaScheduleMerging;
	}

	public static JSONObject addVmaScheduleMerging(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleMerging vmaScheduleMerging = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleMerging == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
					.addVmaScheduleMerging(vmaScheduleMerging);
			result = VMAUtils.object2Json(vmaScheduleMerging,
					VmaScheduleMerging.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleMerging(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleMerging vmaScheduleMerging = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleMerging == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
					.updateVmaScheduleMerging(vmaScheduleMerging);
			result = VMAUtils.object2Json(vmaScheduleMerging,
					VmaScheduleMerging.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleMerging(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaScheduleMergingId");
		if (id > 0) {
			try {
				VmaScheduleMergingLocalServiceUtil.deleteVmaScheduleMerging(id);
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
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String nameOfShip = VMAUtils.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String shipOwnerCode = VMAUtils.getString(resourceRequest,
				"shipOwnerCode", StringPool.BLANK);
		String shipOwnersName = VMAUtils.getString(resourceRequest,
				"shipOwnersName", StringPool.BLANK);
		String shipOperatorCode = VMAUtils.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String shipOperatorName = VMAUtils.getString(resourceRequest,
				"shipOperatorName", StringPool.BLANK);
		String shipAgencyCode = VMAUtils.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);
		String shipAgencyName = VMAUtils.getString(resourceRequest,
				"shipAgencyName", StringPool.BLANK);

		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);
		double nt = GetterUtil.getDouble(request.getParameter("nt"), -1);
		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);
		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);

		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);
		String flagStateOfShip = VMAUtils.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);

		String imoNumber = VMAUtils.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);

		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		String vrCode = ParamUtil.getString(resourceRequest, "vrCode",
				StringPool.BLANK);

		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);
		
		String documentaryCode = ParamUtil
				.getString(request, "documentaryCode");
		String makePayment = ParamUtil.getString(request, "makePayment");
		
		try {
			return VmaScheduleMergingLocalServiceUtil.findVmaScheduleMerging(
					itineraryNo, portofAuthority,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, shipOwnerCode,
					shipOwnersName, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber, shipOperatorCode,
					shipOperatorName, shipAgencyCode, shipAgencyName,
					gt >= 0 ? gt : null, nt >= 0 ? nt : null,
					shownDraftxF >= 0 ? shownDraftxF : null,
					shownDraftxA >= 0 ? shownDraftxA : null, loa >= 0 ? loa
							: null, dwt >= 0 ? dwt : null, itineraryScheduleId,
					start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

	public static JSONObject findVmaScheduleMergings(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);
		String shipLashImoNumber = VMAUtils.getString(resourceRequest,
				"shipLashImoNumber", StringPool.BLANK);
		String shipLashCallSign = VMAUtils.getString(resourceRequest,
				"shipLashCallSign", StringPool.BLANK);
		String shipLashRegistryNumber = VMAUtils.getString(resourceRequest,
				"shipLashRegistryNumber", StringPool.BLANK);
		String shipLashFlagStateOfShip = VMAUtils.getString(resourceRequest,
				"shipLashFlagStateOfShip", StringPool.BLANK);
		String shipTypeCode = VMAUtils.getString(resourceRequest,
				"shipTypeCode", StringPool.BLANK);
		String shipTypeMT = VMAUtils.getString(resourceRequest, "shipTypeMT",
				StringPool.BLANK);
		String shipLashVRCode = VMAUtils.getString(resourceRequest,
				"shipLashVRCode", StringPool.BLANK);
		String makePayment = VMAUtils.getString(resourceRequest, "makePayment",
				StringPool.BLANK);
		String documentaryCode = VMAUtils.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);
		String routeLevelCode = VMAUtils.getString(resourceRequest,
				"routeLevelCode", StringPool.BLANK);
		
		String searchQuery = generateQuery("search", itineraryNo, nameOfShip,
				itineraryScheduleId, shipLashImoNumber, shipLashCallSign,
				shipLashRegistryNumber, shipLashFlagStateOfShip, shipTypeCode,
				shipTypeMT, shipLashVRCode, makePayment, documentaryCode, routeLevelCode);

		String countQuery = generateQuery("count", itineraryNo, nameOfShip,
				itineraryScheduleId, shipLashImoNumber, shipLashCallSign,
				shipLashRegistryNumber, shipLashFlagStateOfShip, shipTypeCode,
				shipTypeMT, shipLashVRCode, makePayment, documentaryCode, routeLevelCode);

		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			result = VmaScheduleMergingLocalServiceUtil.findVmaScheduleMerging(
					searchQuery, countQuery, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
		}

		return result;
	}

	private static String generateQuery(String cmd, String itineraryNo,
			String nameOfShip, long itineraryScheduleId,
			String shipLashImoNumber, String shipLashCallSign,
			String shipLashRegistryNumber, String shipLashFlagStateOfShip,
			String shipTypeCode, String shipTypeMT, String shipLashVRCode,
			String makePayment, String documentaryCode, String routeLevelCode) {
		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_schedule_merging as a LEFT JOIN vma_itinerary_schedule as b ON a.ItineraryScheduleId = b.ID ";

		} else {
			sql = "SELECT a.* FROM vma_schedule_merging AS a LEFT JOIN vma_itinerary_schedule as b ON a.ItineraryScheduleId = b.ID ";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(routeLevelCode)) {
			condition.append(VMAUtils.buildSQLCondition("RouteLevelCode", "'"
					+ routeLevelCode + "'", "AND", StringPool.EQUAL,
					new String[] { "b" }));
		}
		if (Validator.isNotNull(nameOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("NameOfShip", "'%"
					+ nameOfShip + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}
		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}
		if (Validator.isNotNull(itineraryScheduleId) && itineraryScheduleId > 0) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryScheduleId",
					"'" + itineraryScheduleId + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}
		if (Validator.isNotNull(shipLashImoNumber)) {
			condition.append(VMAUtils.buildSQLCondition("ShipLashIMONumber",
					"'" + shipLashImoNumber + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}
		if (Validator.isNotNull(shipLashCallSign)) {
			condition.append(VMAUtils.buildSQLCondition("ShipLashCallSign", "'"
					+ shipLashCallSign + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}
		if (Validator.isNotNull(shipLashRegistryNumber)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShipLashRegistryNumber", "'" + shipLashRegistryNumber
							+ "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}
		if (Validator.isNotNull(shipLashFlagStateOfShip)) {
			condition.append(VMAUtils.buildSQLCondition(
					"ShipLashFlagStateOfShip", "'" + shipLashFlagStateOfShip
							+ "'", "AND", StringPool.EQUAL,
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
		if (Validator.isNotNull(shipLashVRCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipLashVRCode", "'"
					+ shipLashVRCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}
		if (Validator.isNotNull(makePayment)) {
			condition.append(VMAUtils.buildSQLCondition("MakePayment", "'"
					+ makePayment + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}
		if (Validator.isNotNull(documentaryCode)) {
			condition.append(VMAUtils.buildSQLCondition("DocumentaryCode", "'"
					+ documentaryCode + "'", "AND", StringPool.EQUAL,
					new String[] { "a" }));
		}

		return sql + condition.toString();
	}

	public static void doExport(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String nameOfShip = VMAUtils.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String shipOwnerCode = VMAUtils.getString(resourceRequest,
				"shipOwnerCode", StringPool.BLANK);
		String shipOwnersName = VMAUtils.getString(resourceRequest,
				"shipOwnersName", StringPool.BLANK);
		String shipOperatorCode = VMAUtils.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String shipOperatorName = VMAUtils.getString(resourceRequest,
				"shipOperatorName", StringPool.BLANK);
		String shipAgencyCode = VMAUtils.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);
		String shipAgencyName = VMAUtils.getString(resourceRequest,
				"shipAgencyName", StringPool.BLANK);
		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);
		double nt = GetterUtil.getDouble(request.getParameter("nt"), -1);
		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);
		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);

		String flagStateOfShip = VMAUtils.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);

		String imoNumber = VMAUtils.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);

		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		String vrCode = ParamUtil.getString(resourceRequest, "vrCode",
				StringPool.BLANK);

		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);

		String[] headers = new String[] { "STT", "Tên tàu",
				"Qu\u1ED1c t\u1ECBch", "Hô hi\u1EC7u", "GT", "DWT",
				"Chi\u1EC1u dài", "M\u1EDBn n\u01B0\u1EDBc",
				"Lo\u1EA1i hàng hóa", "V\u1ECB trí neo \u0111\u1EADu t\u1EEB",
				"V\u1ECB trí neo \u0111\u1EADu \u0111\u1EBFn",
				"Gi\u1EDD r\u1EDDi", "\u0110\u1EA1i lý",
				"Tuy\u1EBFn lu\u1ED3ng" };

		String[][] exportData = null;

		try {
			JSONObject objects = VmaScheduleMergingLocalServiceUtil
					.findVmaScheduleMerging(itineraryNo, portofAuthority,
							documentName >= 0 ? documentName : null,
							documentYear >= 0 ? documentYear : null,
							noticeShipType >= 0 ? noticeShipType : null,
							shipOwnerCode, shipOwnersName, nameOfShip,
							flagStateOfShip, imoNumber, callSign, vrCode,
							registryNumber, shipOperatorCode, shipOperatorName,
							shipAgencyCode, shipAgencyName,
							gt >= 0 ? gt : null, nt >= 0 ? nt : null,
							shownDraftxF >= 0 ? shownDraftxF : null,
							shownDraftxA >= 0 ? shownDraftxA : null,
							loa >= 0 ? loa : null, dwt >= 0 ? dwt : null,
							itineraryScheduleId, -1, -1);
			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");

					flagStateOfShip = object.getString("flagStateOfShip");

					imoNumber = object.getString("imoNumber");

					gt = object.getDouble("gt");

					dwt = object.getDouble("dwt");

					loa = object.getDouble("loa");

					shownDraftxF = object.getDouble("shownDraftxF");

					shownDraftxA = object.getDouble("shownDraftxA");

					String commodity = object.getString("commodity");

					String anchoringPortHarbourCode = object
							.getString("anchoringPortHarbourCode");
					String anchoringPortWharfCode = object
							.getString("anchoringPortWharfCode");
					String shiftingPortHarbourCode = object
							.getString("shiftingPortHarbourCode");
					String shiftingPortWharfCode = object
							.getString("shiftingPortWharfCode");

					shipAgencyName = object.getString("shipAgencyName");

					String chanelName = object.getString("chanelName");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = flagStateOfShip;
					exportData[i][3] = imoNumber;
					exportData[i][4] = String.valueOf(gt);
					exportData[i][5] = String.valueOf(dwt);

					exportData[i][6] = String.valueOf(loa);
					exportData[i][7] = shownDraftxF + "-" + shownDraftxA;
					exportData[i][8] = commodity;
					exportData[i][9] = anchoringPortHarbourCode + "-"
							+ anchoringPortWharfCode;
					exportData[i][10] = shiftingPortHarbourCode + "-"
							+ shiftingPortWharfCode;
					exportData[i][11] = shipAgencyName;
					exportData[i][12] = "";
					exportData[i][13] = chanelName;

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

	public static long doCount(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String nameOfShip = VMAUtils.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String shipOwnerCode = VMAUtils.getString(resourceRequest,
				"shipOwnerCode", StringPool.BLANK);
		String shipOwnersName = VMAUtils.getString(resourceRequest,
				"shipOwnersName", StringPool.BLANK);
		String shipOperatorCode = VMAUtils.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String shipOperatorName = VMAUtils.getString(resourceRequest,
				"shipOperatorName", StringPool.BLANK);
		String shipAgencyCode = VMAUtils.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);
		String shipAgencyName = VMAUtils.getString(resourceRequest,
				"shipAgencyName", StringPool.BLANK);
		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);
		double nt = GetterUtil.getDouble(request.getParameter("nt"), -1);
		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);
		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);

		String flagStateOfShip = VMAUtils.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);

		String imoNumber = VMAUtils.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);

		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		String vrCode = ParamUtil.getString(resourceRequest, "vrCode",
				StringPool.BLANK);

		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);

		try {
			return VmaScheduleMergingLocalServiceUtil.countVmaScheduleMerging(
					itineraryNo, portofAuthority,
					documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, shipOwnerCode,
					shipOwnersName, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber, shipOperatorCode,
					shipOperatorName, shipAgencyCode, shipAgencyName,
					gt >= 0 ? gt : null, nt >= 0 ? nt : null,
					shownDraftxF >= 0 ? shownDraftxF : null,
					shownDraftxA >= 0 ? shownDraftxA : null, loa >= 0 ? loa
							: null, dwt >= 0 ? dwt : null, itineraryScheduleId);

		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	public static JSONObject updateVmaScheduleMergings(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		
		HttpServletRequest request = actionRequest;
		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);
		if (itineraryScheduleId < 0) {
			log.debug("========>>>>>>>>>>>>>> Thieu key itineraryScheduleId");
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<VmaScheduleMerging> vmaScheduleMergings = null;
		try {
			vmaScheduleMergings = getObjectsFromRequest(themeDisplay,
					actionRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (vmaScheduleMergings == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			result = VmaScheduleMergingLocalServiceUtil.updateVmaScheduleMergings(vmaScheduleMergings, itineraryScheduleId);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}
	
	public static JSONObject updateVmaScheduleMerging_MakePayment_DocumentaryCode(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray results = JSONFactoryUtil.createJSONArray();

		String documentaryCode = ParamUtil
				.getString(request, "documentaryCode");
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		String makePayment = ParamUtil.getString(request, "makePayment");
		String vmaScheduleMergingId = ParamUtil.getString(request,
				"vmaScheduleMergingId");
		String[] arrayId = vmaScheduleMergingId.split(",");
		if (arrayId != null && arrayId.length > 0) {
			for (int i = 0; i < arrayId.length; i++) {
				VmaScheduleMerging vmaScheduleMerging = null;
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				try {
					vmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
							.fetchVmaScheduleMerging(Long.valueOf(arrayId[i]));
					if (vmaScheduleMerging != null) {
						if (vmaScheduleMerging.getItineraryNo().equals(
								itineraryNo)) {
							vmaScheduleMerging
									.setDocumentaryCode(documentaryCode);
							vmaScheduleMerging.setMakePayment(Integer
									.valueOf(makePayment));

							vmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
									.updateVmaScheduleMerging(vmaScheduleMerging);
							obj = VMAUtils.object2Json(vmaScheduleMerging,
									VmaScheduleMerging.class);

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

}
