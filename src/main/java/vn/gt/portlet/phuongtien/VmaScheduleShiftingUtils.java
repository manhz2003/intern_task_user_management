package vn.gt.portlet.phuongtien;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryScheduleException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleShiftingException;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage;
import com.fds.nsw.nghiepvu.model.VmaScheduleMerging;
import com.fds.nsw.nghiepvu.model.VmaSchedulePilot;
import com.fds.nsw.nghiepvu.model.VmaSchedulePilotList;
import com.fds.nsw.nghiepvu.model.VmaScheduleShifting;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboat;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList;



import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleAnchorageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleMergingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleShiftingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
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
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.core.ThemeDisplay;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaScheduleShiftingUtils
 {
	

	public static JSONObject viewPDF(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			HttpServletRequest request = resourceRequest;
			long vmaScheduleShiftingId = ParamUtil.getLong(request,
					"vmaScheduleShiftingId");
			VmaScheduleShifting vmaScheduleShifting = VmaScheduleShiftingLocalServiceUtil
					.getVmaScheduleShifting(vmaScheduleShiftingId);
			// save file
			long nanoTime = ReportsBusinessUtils
					.actionExport(vmaScheduleShifting);

			String tenFileExport = nanoTime + "_"
					+ ReportConstant.VMA_SCHEDULE_SHIFTING_EXPORT;

			String urlFile = request.getContextPath() + "/export/"
					+ tenFileExport;

			result.put("url", urlFile);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);

		JSONObject result = JSONFactoryUtil.createJSONObject();

		long vmaScheduleShiftingId = ParamUtil.getLong(request,
				"vmaScheduleShiftingId");

		VmaItinerary vmaItinerary = null;

		VmaScheduleShifting vmaScheduleShifting = null;

		if (vmaScheduleShiftingId > 0) {
			try {
				vmaScheduleShifting = VmaScheduleShiftingLocalServiceUtil
						.getVmaScheduleShifting(vmaScheduleShiftingId);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		if (vmaScheduleShifting == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(vmaScheduleShifting.getItineraryNo());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (vmaItinerary == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {

			result = VMAUtils.object2Json(vmaItinerary,
					VmaItinerary.class);

			JSONObject tmp = VMAUtils.object2Json(vmaScheduleShifting,
					VmaScheduleShifting.class);

			result = VMAUtils.mergeJSON(result, tmp,
					VmaScheduleShifting.class);

			// Edit by Dungnv
			String shiftingDate_ = StringPool.BLANK;
			try {
				shiftingDate_ = FormatData
						.parseDateToTringType3(vmaScheduleShifting
								.getShiftingDate());
			} catch (Exception e) {
				// nothing to do
			}
			result.remove("shiftingDate");
			result.put("shiftingDate", shiftingDate_);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo portofAuthority nameOfShip flagStateOfShip
	 * anchoringPortHarbourCode anchoringPortWharfCode shiftingPortHarbourCode
	 * shiftingPortWharfCode shiftingDate reasonToShift issueDate
	 * directorOfMaritime certificateNo requestState versionNo isApproval
	 * approvalDate approvalName remarks isCancel cancelDate cancelName
	 * cancelNote representative digitalAttachedFile signName signTitle signDate
	 * signPlace attachedFile stampStatus shownDraftxF unitShownDraftxF
	 * shownDraftxA unitShownDraftxA loa loaUnit Dwt DwtUnit tugBoat from_ to_
	 * taxCodeOfShipownersAgents nameOfShipownersAgents shipAgencyAddress
	 * shipAgencyPhone shipAgencyFax shipAgencyEmail chanelCodeList chanelName
	 * purposeCode modifiedDate
	 */

	public static VmaScheduleShifting getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleShiftingId"), -1);
		VmaScheduleShifting vmaScheduleShifting = null;
		if (id > 0) {
			try {
				vmaScheduleShifting = VmaScheduleShiftingLocalServiceUtil
						.getVmaScheduleShifting(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleShifting = new VmaScheduleShifting();
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleShifting.setItineraryNo(itineraryNo);
		}
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleShifting.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleShifting.setNameOfShip(nameOfShip);
		}
		String flagStateOfShip = VMAUtils.getString(actionRequest,
				"flagStateOfShip", StringPool.BLANK);
		if (Validator.isNotNull(flagStateOfShip)) {
			vmaScheduleShifting.setFlagStateOfShip(flagStateOfShip);
		}
		String anchoringPortHarbourCode = ParamUtil.getString(actionRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			vmaScheduleShifting
					.setAnchoringPortHarbourCode(anchoringPortHarbourCode);
		}
		String anchoringPortWharfCode = ParamUtil.getString(actionRequest,
				"anchoringPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortWharfCode)) {
			vmaScheduleShifting
					.setAnchoringPortWharfCode(anchoringPortWharfCode);
		}
		String shiftingPortHarbourCode = ParamUtil.getString(actionRequest,
				"shiftingPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortHarbourCode)) {
			vmaScheduleShifting
					.setShiftingPortHarbourCode(shiftingPortHarbourCode);
		}
		String shiftingPortWharfCode = ParamUtil.getString(actionRequest,
				"shiftingPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortWharfCode)) {
			vmaScheduleShifting.setShiftingPortWharfCode(shiftingPortWharfCode);
		}
		String shiftingDate = ParamUtil
				.getString(actionRequest, "shiftingDate");
		if (Validator.isNotNull(shiftingDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(shiftingDate);
				vmaScheduleShifting.setShiftingDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String reasonToShift = VMAUtils.getString(actionRequest,
				"reasonToShift", StringPool.BLANK);
		if (Validator.isNotNull(reasonToShift)) {
			vmaScheduleShifting.setReasonToShift(reasonToShift);
		}

		String directorOfMaritime = VMAUtils.getString(actionRequest,
				"directorOfMaritime", StringPool.BLANK);
		if (Validator.isNotNull(directorOfMaritime)) {
			vmaScheduleShifting.setDirectorOfMaritime(directorOfMaritime);
		}
		String certificateNo = ParamUtil.getString(actionRequest,
				"certificateNo", StringPool.BLANK);
		if (Validator.isNotNull(certificateNo)) {
			vmaScheduleShifting.setCertificateNo(certificateNo);
		}
		// edit 19/10/2019 - Dungnv
		int requestState = GetterUtil.getInteger(
				request.getParameter("requestState"), 1);
		if (requestState >= 0) {
			vmaScheduleShifting.setRequestState(requestState);
		}

		String issueDate = ParamUtil.getString(actionRequest, "issueDate",
				StringPool.BLANK);
		if (Validator.isNotNull(issueDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(issueDate);
				vmaScheduleShifting.setIssueDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		} else {
			if (requestState == 4) {
				vmaScheduleShifting.setIssueDate(new Date());
			}

		}

		String approvalDate = ParamUtil
				.getString(actionRequest, "approvalDate");
		if (Validator.isNotNull(approvalDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(approvalDate);
				vmaScheduleShifting.setApprovalDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		} else {
			if (requestState == 4) {
				vmaScheduleShifting.setApprovalDate(new Date());
			}
		}

		String versionNo = ParamUtil.getString(actionRequest, "versionNo",
				StringPool.BLANK);
		if (Validator.isNotNull(versionNo)) {
			vmaScheduleShifting.setVersionNo(versionNo);
		}
		int isApproval = GetterUtil.getInteger(
				request.getParameter("isApproval"), -1);
		if (isApproval >= 0) {
			vmaScheduleShifting.setIsApproval(isApproval);
		}

		String approvalName = VMAUtils.getString(actionRequest, "approvalName",
				StringPool.BLANK);
		if (Validator.isNotNull(approvalName)) {
			vmaScheduleShifting.setApprovalName(approvalName);
		}
		String remarks = VMAUtils.getString(actionRequest, "remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(remarks)) {
			vmaScheduleShifting.setRemarks(remarks);
		}
		int isCancel = GetterUtil.getInteger(request.getParameter("isCancel"),
				-1);
		if (isCancel >= 0) {
			vmaScheduleShifting.setIsCancel(isCancel);
		}
		String cancelDate = ParamUtil.getString(actionRequest, "cancelDate");
		if (Validator.isNotNull(cancelDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(cancelDate);
				vmaScheduleShifting.setCancelDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String cancelName = VMAUtils.getString(actionRequest, "cancelName",
				StringPool.BLANK);
		if (Validator.isNotNull(cancelName)) {
			vmaScheduleShifting.setCancelName(cancelName);
		}
		String cancelNote = VMAUtils.getString(actionRequest, "cancelNote",
				StringPool.BLANK);
		if (Validator.isNotNull(cancelNote)) {
			vmaScheduleShifting.setCancelNote(cancelNote);
		}
		String representative = VMAUtils.getString(actionRequest,
				"representative", StringPool.BLANK);
		if (Validator.isNotNull(representative)) {
			vmaScheduleShifting.setRepresentative(representative);
		}
		int digitalAttachedFile = GetterUtil.getInteger(
				request.getParameter("digitalAttachedFile"), -1);
		if (digitalAttachedFile >= 0) {
			vmaScheduleShifting.setDigitalAttachedFile(digitalAttachedFile);
		}
		String signName = VMAUtils.getString(actionRequest, "signName",
				StringPool.BLANK);
		if (Validator.isNotNull(signName)) {
			vmaScheduleShifting.setSignName(signName);
		}
		String signTitle = VMAUtils.getString(actionRequest, "signTitle",
				StringPool.BLANK);
		if (Validator.isNotNull(signTitle)) {
			vmaScheduleShifting.setSignTitle(signTitle);
		}
		String signDate = ParamUtil.getString(actionRequest, "signDate");
		if (Validator.isNotNull(signDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(signDate);
				vmaScheduleShifting.setSignDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String signPlace = VMAUtils.getString(actionRequest, "signPlace",
				StringPool.BLANK);
		if (Validator.isNotNull(signPlace)) {
			vmaScheduleShifting.setSignPlace(signPlace);
		}
		long attachedFile = GetterUtil.getLong(
				request.getParameter("attachedFile"), -1);
		if (attachedFile >= 0) {
			vmaScheduleShifting.setAttachedFile(attachedFile);
		}
		int stampStatus = GetterUtil.getInteger(
				request.getParameter("stampStatus"), -1);
		if (stampStatus >= 0) {
			vmaScheduleShifting.setStampStatus(stampStatus);
		}
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		if (shownDraftxF >= 0) {
			vmaScheduleShifting.setShownDraftxF(shownDraftxF);
		}
		String unitShownDraftxF = VMAUtils.getString(actionRequest,
				"unitShownDraftxF", StringPool.BLANK);
		if (Validator.isNotNull(unitShownDraftxF)) {
			vmaScheduleShifting.setUnitShownDraftxF(unitShownDraftxF);
		}
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);
		if (shownDraftxA >= 0) {
			vmaScheduleShifting.setShownDraftxA(shownDraftxA);
		}
		String unitShownDraftxA = VMAUtils.getString(actionRequest,
				"unitShownDraftxA", StringPool.BLANK);
		if (Validator.isNotNull(unitShownDraftxA)) {
			vmaScheduleShifting.setUnitShownDraftxA(unitShownDraftxA);
		}
		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);
		if (loa >= 0) {
			vmaScheduleShifting.setLoa(loa);
		}
		String loaUnit = VMAUtils.getString(actionRequest, "loaUnit",
				StringPool.BLANK);
		if (Validator.isNotNull(loaUnit)) {
			vmaScheduleShifting.setLoaunit(loaUnit);
		}
		int Dwt = GetterUtil.getInteger(request.getParameter("Dwt"), -1);
		if (Dwt >= 0) {
			vmaScheduleShifting.setDwt(BigDecimal.valueOf(Dwt));
		}
		String DwtUnit = VMAUtils.getString(actionRequest, "DwtUnit",
				StringPool.BLANK);
		if (Validator.isNotNull(DwtUnit)) {
			vmaScheduleShifting.setDwtunit(DwtUnit);
		}
		String tugBoat = VMAUtils.getString(actionRequest, "tugBoat",
				StringPool.BLANK);
		if (Validator.isNotNull(tugBoat)) {
			vmaScheduleShifting.setTugBoat(tugBoat);
		}

		String from_ = VMAUtils.getFrom_To(anchoringPortHarbourCode,
				anchoringPortWharfCode);
		if (Validator.isNotNull(from_)) {
			vmaScheduleShifting.setFrom(from_);
		}

		String to_ = VMAUtils.getFrom_To(shiftingPortHarbourCode,
				shiftingPortWharfCode);
		if (Validator.isNotNull(to_)) {
			vmaScheduleShifting.setTo(to_);
		}

		String taxCodeOfShipownersAgents = VMAUtils.getString(actionRequest,
				"taxCodeOfShipownersAgents", StringPool.BLANK);
		if (Validator.isNotNull(taxCodeOfShipownersAgents)) {
			vmaScheduleShifting
					.setTaxCodeOfShipownersAgents(taxCodeOfShipownersAgents);
		}
		String nameOfShipownersAgents = VMAUtils.getString(actionRequest,
				"nameOfShipownersAgents", StringPool.BLANK);
		if (Validator.isNotNull(nameOfShipownersAgents)) {
			vmaScheduleShifting
					.setNameOfShipownersAgents(nameOfShipownersAgents);
		}
		String shipAgencyAddress = VMAUtils.getString(actionRequest,
				"shipAgencyAddress", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyAddress)) {
			vmaScheduleShifting.setShipAgencyAddress(shipAgencyAddress);
		}
		String shipAgencyPhone = ParamUtil.getString(actionRequest,
				"shipAgencyPhone", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyPhone)) {
			vmaScheduleShifting.setShipAgencyPhone(shipAgencyPhone);
		}
		String shipAgencyFax = ParamUtil.getString(actionRequest,
				"shipAgencyFax", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyFax)) {
			vmaScheduleShifting.setShipAgencyFax(shipAgencyFax);
		}
		String shipAgencyEmail = ParamUtil.getString(actionRequest,
				"shipAgencyEmail", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyEmail)) {
			vmaScheduleShifting.setShipAgencyEmail(shipAgencyEmail);
		}
		String chanelCodeList = VMAUtils.getString(actionRequest,
				"chanelCodeList", StringPool.BLANK);
		if (Validator.isNotNull(chanelCodeList)) {
			vmaScheduleShifting.setChanelCodeList(chanelCodeList);
			vmaScheduleShifting.setChanelName(VMAUtils
					.getChanelName(chanelCodeList));
		}
		/*
		 * String chanelName = VMAUtils.getString(actionRequest, "chanelName",
		 * StringPool.BLANK); if (Validator.isNotNull(chanelName)) {
		 * vmaScheduleShifting.setChanelName(chanelName); }
		 */
		String purposeCode = VMAUtils.getString(actionRequest, "purposeCode",
				StringPool.BLANK);
		if (Validator.isNotNull(purposeCode)) {
			vmaScheduleShifting.setPurposeCode(purposeCode);
		}
		String modifiedDate = ParamUtil
				.getString(actionRequest, "modifiedDate");
		if (Validator.isNotNull(modifiedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(modifiedDate);
				vmaScheduleShifting.setModifiedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		// edit by Dungnv
		int gt = ParamUtil.getInteger(actionRequest, "gt");
		if (gt > 0) {
			vmaScheduleShifting.setGt(BigDecimal.valueOf(gt));
		}
		int nt = ParamUtil.getInteger(actionRequest, "nt");
		if (nt > 0) {
			vmaScheduleShifting.setNt(BigDecimal.valueOf(nt));
		}
		String unitGRT = ParamUtil.getString(actionRequest, "unitGRT",
				StringPool.BLANK);
		if (Validator.isNotNull(unitGRT)) {
			vmaScheduleShifting.setUnitGRT(unitGRT);
		}
		String unitNT = ParamUtil.getString(actionRequest, "unitNT",
				StringPool.BLANK);
		if (Validator.isNotNull(unitNT)) {
			vmaScheduleShifting.setUnitNT(unitNT);
		}

		String mergedShip = VMAUtils.getString(actionRequest, "mergedShip",
				StringPool.BLANK);
		if (Validator.isNotNull(mergedShip)) {
			vmaScheduleShifting.setMergedShip(mergedShip);
		}

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);
		if (itineraryScheduleId >= 0) {
			vmaScheduleShifting.setItineraryScheduleId(itineraryScheduleId);
		}
		String shiftingPortRegionCode = ParamUtil.getString(actionRequest,
				"shiftingPortRegionCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortRegionCode)) {
			vmaScheduleShifting
					.setShiftingPortRegionCode(shiftingPortRegionCode);
		} else {
			try {
				if (Validator.isNotNull(shiftingPortHarbourCode)) {
				DmPortHarbour objDmPortHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(shiftingPortHarbourCode);
				vmaScheduleShifting
					.setShiftingPortRegionCode(Validator.isNotNull(objDmPortHarbour) ? objDmPortHarbour.getPortRegion(): StringPool.BLANK);
				}
			} catch (Exception e) {
				
			}
		}

		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);
		if (dwt >= 0) {
			vmaScheduleShifting.setDwt(BigDecimal.valueOf(dwt));
		}
		String dwtUnit = ParamUtil.getString(actionRequest, "dwtUnit",
				StringPool.BLANK);
		if (Validator.isNotNull(dwtUnit)) {
			vmaScheduleShifting.setDwtunit(dwtUnit);
		}
		
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaScheduleShifting.setSecurityLevelCode(securityLevelCode);
		}

		String cargoCode = VMAUtils.getString(actionRequest,
				"cargoCode", StringPool.BLANK);
		if (Validator.isNotNull(cargoCode)) {
			vmaScheduleShifting.setCargoCode(cargoCode);
		}
		
		String quantityOfCargo = VMAUtils.getString(actionRequest,
				"quantityOfCargo", StringPool.BLANK);
		if (Validator.isNotNull(quantityOfCargo)) {
			vmaScheduleShifting.setQuantityOfCargo(quantityOfCargo);
		}
		
		String departmentCode = VMAUtils.getString(actionRequest,
				"departmentCode", StringPool.BLANK);
		if (Validator.isNotNull(departmentCode)) {
			vmaScheduleShifting.setDepartmentCode(departmentCode);
		}
		
		String departmentName = VMAUtils.getString(actionRequest,
				"departmentName", StringPool.BLANK);
		if (Validator.isNotNull(departmentName)) {
			vmaScheduleShifting.setDepartmentName(departmentName);
		}

		return vmaScheduleShifting;
	}

	@Deprecated
	public static JSONObject addVmaScheduleShifting(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleShifting vmaScheduleShifting = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleShifting == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleShifting = VmaScheduleShiftingLocalServiceUtil
					.addVmaScheduleShifting(vmaScheduleShifting);
			result = VMAUtils.object2Json(vmaScheduleShifting,
					VmaScheduleShifting.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	@Deprecated
	public static JSONObject updateVmaScheduleShifting(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleShifting vmaScheduleShifting = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleShifting == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleShifting = VmaScheduleShiftingLocalServiceUtil
					.updateVmaScheduleShifting(vmaScheduleShifting);
			result = VMAUtils.object2Json(vmaScheduleShifting,
					VmaScheduleShifting.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleShifting_VmaItinerary(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());

		HttpServletRequest request = actionRequest;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleShiftingId"), -1);

		int preRequestState = -1, afterRequestState = -1;
		try {
			VmaScheduleShifting preVmaScheduleShifting = VmaScheduleShiftingLocalServiceUtil
					.fetchVmaScheduleShifting(id);

			preRequestState = preVmaScheduleShifting.getRequestState();
		} catch (SystemException e1) {
			// nothing to do
		}
		VmaScheduleShifting vmaScheduleShifting = getObjectFromRequest(
				themeDisplay, actionRequest);
		double crewNumber = ParamUtil.getDouble(request, "crewNumber", 0);
		double passengerNumber = ParamUtil.getDouble(request,
				"passengerNumber", 0);
		afterRequestState = vmaScheduleShifting.getRequestState();

		int markedAsArrival = GetterUtil.getInteger(
				request.getParameter("markedAsArrival"), -1);

		int markedAsDeparture = GetterUtil.getInteger(
				request.getParameter("markedAsDeparture"), -1);

		int markedAsTransmit = GetterUtil.getInteger(
				request.getParameter("markedAsTransmit"), -1);

		int markedAsVoyage = GetterUtil.getInteger(
				request.getParameter("markedAsVoyage"), -1);

		VmaItinerary vmaItinerary = null;

		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(vmaScheduleShifting.getItineraryNo());
		} catch (SystemException e) {
			log.error(e.getMessage());
		}

		if (vmaScheduleShifting == null || vmaItinerary == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		vmaItinerary = VmaItineraryUtils.getObjectFromRequest(actionRequest,
				vmaItinerary);

		try {
			result = VmaScheduleShiftingLocalServiceUtil
					.updateVmaScheduleShifting_VmaItinerary(
							markedAsArrival > 0 ? markedAsArrival : null,
							markedAsDeparture > 0 ? markedAsDeparture : null,
							markedAsTransmit > 0 ? markedAsTransmit : null,
							markedAsVoyage > 0 ? markedAsVoyage : null,
							crewNumber, passengerNumber, vmaScheduleShifting,
							vmaItinerary);

			// Write logs - add by Dungnv
			if (userPort != null) {
				if (preRequestState > 0 && afterRequestState > 0) {
					if (preRequestState == afterRequestState) {
						VmaAuditLogLocalServiceUtil.addVmaAuditLog(
								userPort.getUserId(),
								userSign != null ? userSign.getModifyuser()
										: StringPool.BLANK,
								LogsConstant.SUA,
								"vma_itinerary, vma_schedule_shifting",
								vmaItinerary.getItineraryNo(),
								"vmaScheduleShiftingId: "
										+ vmaScheduleShifting.getId()
										+ StringPool.COMMA
										+ "nameOfShip: "
										+ vmaScheduleShifting.getNameOfShip()
										+ StringPool.COMMA
										+ "itineraryScheduleId: "
										+ vmaScheduleShifting
												.getItineraryScheduleId());
					} else {
						if (vmaScheduleShifting.getId() <= 0) {
							VmaAuditLogLocalServiceUtil
									.addVmaAuditLog(
											userPort.getUserId(),
											userSign != null ? userSign
													.getModifyuser()
													: StringPool.BLANK,
											LogsConstant.THEM,
											"vma_itinerary, vma_schedule_shifting",
											vmaItinerary.getItineraryNo(),
											"vmaScheduleShiftingId: "
													+ result.getLong("vmaScheduleShiftingId")
													+ StringPool.COMMA
													+ "nameOfShip: "
													+ vmaScheduleShifting
															.getNameOfShip()
													+ StringPool.COMMA
													+ "itineraryScheduleId: "
													+ vmaScheduleShifting
															.getItineraryScheduleId());
						} else {
							if (vmaScheduleShifting.getRequestState() == 4) {
								VmaAuditLogLocalServiceUtil.addVmaAuditLog(
										userPort.getUserId(),
										userSign != null ? userSign
												.getModifyuser()
												: StringPool.BLANK,
										LogsConstant.DUYET,
										"vma_itinerary, vma_schedule_shifting",
										vmaItinerary.getItineraryNo(),
										"vmaScheduleShiftingId: "
												+ vmaScheduleShifting.getId()
												+ StringPool.COMMA
												+ "nameOfShip: "
												+ vmaScheduleShifting
														.getNameOfShip());
							} else if (vmaScheduleShifting.getRequestState() == 6) {
								VmaAuditLogLocalServiceUtil
										.addVmaAuditLog(
												userPort.getUserId(),
												userSign != null ? userSign
														.getModifyuser()
														: StringPool.BLANK,
												LogsConstant.HUY,
												"vma_itinerary, vma_schedule_shifting",
												vmaItinerary.getItineraryNo(),
												"vmaScheduleShiftingId: "
														+ vmaScheduleShifting
																.getId()
														+ StringPool.COMMA
														+ "nameOfShip: "
														+ vmaScheduleShifting
																.getNameOfShip()
														+ StringPool.COMMA
														+ "itineraryScheduleId: "
														+ vmaScheduleShifting
																.getItineraryScheduleId());
							} else if (vmaScheduleShifting.getRequestState() == 1) {
								VmaAuditLogLocalServiceUtil
										.addVmaAuditLog(
												userPort.getUserId(),
												userSign != null ? userSign
														.getModifyuser()
														: StringPool.BLANK,
												LogsConstant.GO_BO,
												"vma_itinerary, vma_schedule_shifting",
												vmaItinerary.getItineraryNo(),
												"vmaScheduleShiftingId: "
														+ vmaScheduleShifting
																.getId()
														+ StringPool.COMMA
														+ "nameOfShip: "
														+ vmaScheduleShifting
																.getNameOfShip()
														+ StringPool.COMMA
														+ "itineraryScheduleId: "
														+ vmaScheduleShifting
																.getItineraryScheduleId());
							}
						}
					}
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleShifting(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());

		long id = ParamUtil.getLong(actionRequest, "vmaScheduleShiftingId");
		if (id > 0) {
			String desciption = "";
			try {
				// Lấy đối tượng gốc VmaScheduleShifting
				VmaScheduleShifting curScheduleShifting = VmaScheduleShiftingLocalServiceUtil
						.getVmaScheduleShifting(id);
				String itineraryNo = curScheduleShifting.getItineraryNo();
				String certificateNo = curScheduleShifting.getCertificateNo();
				int noticeShipType = 4;

				// VmaItinerarySchedule
				VmaItinerarySchedule curItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
						.findByitineraryNo_certificateNo_noticeShipType(
								itineraryNo, noticeShipType, certificateNo);
				if (curItinerarySchedule != null)
					VmaItineraryScheduleLocalServiceUtil
							.deleteVmaItinerarySchedule(curItinerarySchedule);
				else
					desciption += "Khong tim thay VmaItinerarySchedule";

				try {
					// VmaScheduleTugboat
					VmaScheduleTugboat curScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
							.findByItineraryNo_NoticeShipType_CertificateNo(
									itineraryNo, noticeShipType, certificateNo);
					// VmaScheduleTugboatList
					if (curScheduleTugboat != null) {
						List<VmaScheduleTugboatList> lstScheduleTugboatList = VmaScheduleTugboatListLocalServiceUtil
								.findByItineraryNo_SequenceNo(itineraryNo,
										curScheduleTugboat.getSequenceNo());
						// Delete Tugboat + Tugboatlist
						VmaScheduleTugboatLocalServiceUtil
								.deleteVmaScheduleTugboat(curScheduleTugboat);
						if (lstScheduleTugboatList != null) {
							for (VmaScheduleTugboatList curScheduleTugboatList : lstScheduleTugboatList) {
								VmaScheduleTugboatListLocalServiceUtil
										.deleteVmaScheduleTugboatList(curScheduleTugboatList);
							}
							desciption += "\n Xoa Tugboat thanh cong";
						}
					} else
						desciption += "Khong tim thay VmaScheduleTugboat";
				} catch (Exception e) {
					desciption += "\n" + e.getMessage();
				}

				try {
					// VmaSchedulePilot
					VmaSchedulePilot curSchedulePilot = VmaSchedulePilotLocalServiceUtil
							.findByItineraryNo_NoticeShipType_CertificateNo(
									itineraryNo, noticeShipType, certificateNo);
					// VmaSchedulePilotList
					if (curSchedulePilot != null) {
						List<VmaSchedulePilotList> lstSchedulePilotList = VmaSchedulePilotListLocalServiceUtil
								.findByItineraryNo_SequenceNo(itineraryNo,
										curSchedulePilot.getSequenceNo());
						// Delete Pilot + Pilotlist
						VmaSchedulePilotLocalServiceUtil
								.deleteVmaSchedulePilot(curSchedulePilot);
						if (lstSchedulePilotList != null) {
							for (VmaSchedulePilotList curVmaSchedulePilotList : lstSchedulePilotList) {
								VmaSchedulePilotListLocalServiceUtil
										.deleteVmaSchedulePilotList(curVmaSchedulePilotList);
							}
							desciption += "\n Xoa Pilot thanh cong";
						}
					} else
						desciption += "Khong tim thay VmaSchedulePilot";
				} catch (Exception e) {
					desciption += "\n" + e.getMessage();
				}

				// Add by Dungnv
				// Delete VmaScheduleAnchorage
				try {
					long anchorageItinerarayScheduleId = curScheduleShifting
							.getItineraryScheduleId();
					log.info("xoa anchorage shifting anchorageItinerarayScheduleId: "
							+ anchorageItinerarayScheduleId);
					if (anchorageItinerarayScheduleId > 0) {
						VmaScheduleAnchorage vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
								.findByitineraryScheduleId(anchorageItinerarayScheduleId);

						String anchorageNameOfShip = vmaScheduleAnchorage
								.getNameOfShip();
						String anchorageItineraryNo = vmaScheduleAnchorage
								.getItineraryNo();

						VmaScheduleAnchorageLocalServiceUtil
								.deleteVmaScheduleAnchorage(vmaScheduleAnchorage);

						// Write logs - add by Dungnv
						if (userPort != null) {
							VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
									.getUserId(),
									userSign != null ? userSign.getModifyuser()
											: StringPool.BLANK,
									LogsConstant.XOA, "vma_schedule_anchorage",
									anchorageItineraryNo,
									"Delete from vma_schedule_shifting NameOfShip: "
											+ anchorageNameOfShip
											+ ", ItinerarayScheduleId"
											+ anchorageItinerarayScheduleId);
						}
					}
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				String nameOfShip = curScheduleShifting.getNameOfShip();

				// DELTE
				VmaScheduleShiftingLocalServiceUtil
						.deleteVmaScheduleShifting(curScheduleShifting);

				// Write logs - add by Dungnv
				if (userPort != null) {
					VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
							.getUserId(),
							userSign != null ? userSign.getModifyuser()
									: StringPool.BLANK, LogsConstant.XOA,
							"vma_schedule_shifting", String.valueOf(id),
							"Delete " + nameOfShip);
				}

				return VMAUtils.createResponseMessage(
						LanguageUtil.get(themeDisplay.getLocale(), "success"),
						"", desciption);
			} catch (Exception e) {
				return VMAUtils.createResponseMessage(LanguageUtil.get(
						themeDisplay.getLocale(), "system_error"),
						"system_error", desciption);
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

		String shiftingDate = ParamUtil.getString(resourceRequest,
				"shiftingDate", StringPool.BLANK);
		
		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);
		
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);

		String requestState = ParamUtil.getString(resourceRequest,
				"requestState", StringPool.BLANK);

		try {
			if (Validator.isNull(shiftingDate) && Validator.isNotNull(timeOfDeparture)) {
				shiftingDate = timeOfDeparture;
			}
			return VmaScheduleShiftingLocalServiceUtil
					.findVmaItinerary_VmaScheduleShifting(itineraryNo,
							portofAuthority, shiftingDate, requestState, start,
							end);
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

		String shiftingDate = ParamUtil.getString(resourceRequest,
				"shiftingDate", StringPool.BLANK);
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);

		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		double gt = GetterUtil.getInteger(request.getParameter("gt"), -1);
		double nt = GetterUtil.getInteger(request.getParameter("nt"), -1);
		double dwt = GetterUtil.getInteger(request.getParameter("dwt"), -1);
		double loa = GetterUtil.getInteger(request.getParameter("loa"), -1);
		double breadth = GetterUtil.getInteger(request.getParameter("breadth"),
				-1);
		double clearanceHeight = GetterUtil.getInteger(
				request.getParameter("clearanceHeight"), -1);
		double power = GetterUtil.getInteger(request.getParameter("power"), -1);
		double maxDraft = GetterUtil.getInteger(
				request.getParameter("maxDraft"), -1);

		double shownDraftxF = GetterUtil.getInteger(
				request.getParameter("shownDraftxF"), -1);
		double shownDraftxA = GetterUtil.getInteger(
				request.getParameter("shownDraftxA"), -1);

		String chanelCodeList = ParamUtil.getString(resourceRequest,
				"chanelCodeList", StringPool.BLANK);
		String requestState = ParamUtil.getString(resourceRequest,
				"requestState", StringPool.BLANK);
		/*
		 * long vmaScheduleShiftingId = GetterUtil.getLong(
		 * request.getParameter("vmaScheduleShiftingId"), -1);
		 * 
		 * 
		 * 
		 * String anchoringPortHarbourCode =
		 * ParamUtil.getString(resourceRequest, "anchoringPortHarbourCode",
		 * StringPool.BLANK); String anchoringPortWharfCode =
		 * ParamUtil.getString(resourceRequest, "anchoringPortWharfCode",
		 * StringPool.BLANK); String shiftingPortHarbourCode =
		 * ParamUtil.getString(resourceRequest, "shiftingPortHarbourCode",
		 * StringPool.BLANK); String shiftingPortWharfCode =
		 * ParamUtil.getString(resourceRequest, "shiftingPortWharfCode",
		 * StringPool.BLANK);
		 * 
		 * String reasonToShift = ParamUtil.getString(resourceRequest,
		 * "reasonToShift", StringPool.BLANK); String issueDate =
		 * ParamUtil.getString(resourceRequest, "issueDate", StringPool.BLANK);
		 * String directorOfMaritime = ParamUtil.getString(resourceRequest,
		 * "directorOfMaritime", StringPool.BLANK); String certificateNo =
		 * ParamUtil.getString(resourceRequest, "certificateNo",
		 * StringPool.BLANK); int requestState = GetterUtil.getInteger(
		 * request.getParameter("requestState"), -1); String versionNo =
		 * ParamUtil.getString(resourceRequest, "versionNo", StringPool.BLANK);
		 * int isApproval = GetterUtil.getInteger(
		 * request.getParameter("isApproval"), -1); long approvalDate =
		 * GetterUtil.getLong( request.getParameter("approvalDate"), -1); String
		 * approvalName = ParamUtil.getString(resourceRequest, "approvalName",
		 * StringPool.BLANK); String remarks =
		 * ParamUtil.getString(resourceRequest, "remarks", StringPool.BLANK);
		 * int isCancel =
		 * GetterUtil.getInteger(request.getParameter("isCancel"), -1); long
		 * cancelDate = GetterUtil.getLong( request.getParameter("cancelDate"),
		 * -1); String cancelName = ParamUtil.getString(resourceRequest,
		 * "cancelName", StringPool.BLANK); String cancelNote =
		 * ParamUtil.getString(resourceRequest, "cancelNote", StringPool.BLANK);
		 * String representative = ParamUtil.getString(resourceRequest,
		 * "representative", StringPool.BLANK); int digitalAttachedFile =
		 * GetterUtil.getInteger( request.getParameter("digitalAttachedFile"),
		 * -1); String signName = ParamUtil.getString(resourceRequest,
		 * "signName", StringPool.BLANK); String signTitle =
		 * ParamUtil.getString(resourceRequest, "signTitle", StringPool.BLANK);
		 * String signDate = ParamUtil.getString(resourceRequest, "signDate",
		 * StringPool.BLANK); String signPlace =
		 * ParamUtil.getString(resourceRequest, "signPlace", StringPool.BLANK);
		 * long attachedFile = GetterUtil.getLong(
		 * request.getParameter("attachedFile"), -1); int stampStatus =
		 * GetterUtil.getInteger( request.getParameter("stampStatus"), -1);
		 * double shownDraftxF = GetterUtil.getDouble(
		 * request.getParameter("shownDraftxF"), -1); String unitShownDraftxF =
		 * ParamUtil.getString(resourceRequest, "unitShownDraftxF",
		 * StringPool.BLANK); double shownDraftxA = GetterUtil.getDouble(
		 * request.getParameter("shownDraftxA"), -1); String unitShownDraftxA =
		 * ParamUtil.getString(resourceRequest, "unitShownDraftxA",
		 * StringPool.BLANK); double loa =
		 * GetterUtil.getDouble(request.getParameter("loa"), -1); String loaUnit
		 * = ParamUtil.getString(resourceRequest, "loaUnit", StringPool.BLANK);
		 * double Dwt = GetterUtil.getDouble(request.getParameter("Dwt"), -1);
		 * String DwtUnit = ParamUtil.getString(resourceRequest, "DwtUnit",
		 * StringPool.BLANK); String tugBoat =
		 * ParamUtil.getString(resourceRequest, "tugBoat", StringPool.BLANK);
		 * String from_ = ParamUtil.getString(resourceRequest, "from_",
		 * StringPool.BLANK); String to_ = ParamUtil.getString(resourceRequest,
		 * "to_", StringPool.BLANK); String taxCodeOfShipownersAgents =
		 * ParamUtil.getString(resourceRequest, "taxCodeOfShipownersAgents",
		 * StringPool.BLANK); String nameOfShipownersAgents =
		 * ParamUtil.getString(resourceRequest, "nameOfShipownersAgents",
		 * StringPool.BLANK); String shipAgencyAddress =
		 * ParamUtil.getString(resourceRequest, "shipAgencyAddress",
		 * StringPool.BLANK); String shipAgencyPhone =
		 * ParamUtil.getString(resourceRequest, "shipAgencyPhone",
		 * StringPool.BLANK); String shipAgencyFax =
		 * ParamUtil.getString(resourceRequest, "shipAgencyFax",
		 * StringPool.BLANK); String shipAgencyEmail =
		 * ParamUtil.getString(resourceRequest, "shipAgencyEmail",
		 * StringPool.BLANK); String chanelCodeList =
		 * ParamUtil.getString(resourceRequest, "chanelCodeList",
		 * StringPool.BLANK); String chanelName =
		 * ParamUtil.getString(resourceRequest, "chanelName", StringPool.BLANK);
		 * String purposeCode = ParamUtil.getString(resourceRequest,
		 * "purposeCode", StringPool.BLANK); String modifiedDate =
		 * ParamUtil.getString(resourceRequest, "modifiedDate",
		 * StringPool.BLANK);
		 */
		/*
		 * String[] headers = new String[] { "STT", "Tên tàu", "Quốc tịch",
		 * "Hô hiệu", "GT", "DWT", "Chiều dài", "Mớn nước", "Loại hàng hóa",
		 * "Vị trí neo đậu từ", "Vị trí neo đậu đến", "Giờ rời", "Đại lý",
		 * "Tuyến luồng" };
		 */

		String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
				"Qu\u1ED1c t\u1ECBch", "H\u00F4 hi\u1EC7u", "GT", "DWT",
				"Chi\u1EC1u d\u00E0i", "M\u1EDBn n\u01B0\u1EDBc",
				"Lo\u1EA1i h\u00E0ng h\u00F3a",
				"V\u1ECB tr\u00ED neo \u0111\u1EADu t\u1EEB",
				"V\u1ECB tr\u00ED neo \u0111\u1EADu \u0111\u1EBFn",
				"Gi\u1EDD r\u1EDDi", "\u0110\u1EA1i l\u00FD",
				"Tuy\u1EBFn lu\u1ED3ng" };

		String[][] exportData = null;

		try {
			JSONObject objects = VmaScheduleShiftingLocalServiceUtil
					.findVmaItinerary_VmaScheduleShifting(itineraryNo,
							portofAuthority, shiftingDate, requestState, -1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");

					flagStateOfShip = object.getString("flagStateOfShip");

					callSign = object.getString("callSign");

					gt = object.getDouble("gt");
					dwt = object.getDouble("dwt");
					loa = object.getDouble("loa");
					shownDraftxA = object.getDouble("shownDraftxA");
					shownDraftxF = object.getDouble("shownDraftxF");

					String loaihang = "";
					String vitrineodautu = "";
					String vitrineodauden = "";
					shiftingDate = object.getString("shiftingDate");
					chanelCodeList = object.getString("chanelCodeList");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = flagStateOfShip;
					exportData[i][3] = callSign;
					exportData[i][4] = String.valueOf(gt);
					exportData[i][5] = String.valueOf(dwt);
					exportData[i][6] = String.valueOf(loa);
					exportData[i][7] = shownDraftxA + "-" + shownDraftxF;
					exportData[i][8] = loaihang;
					exportData[i][9] = vitrineodautu;
					exportData[i][10] = vitrineodauden;
					exportData[i][11] = shiftingDate;
					exportData[i][12] = chanelCodeList;

				}
			}

			String sheetName = "VMA-Schedule-Shifting";

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

		String shiftingDate = ParamUtil.getString(resourceRequest,
				"shiftingDate", StringPool.BLANK);
		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String requestState = ParamUtil.getString(resourceRequest,
				"requestState", StringPool.BLANK);

		try {
			return VmaScheduleShiftingLocalServiceUtil
					.countVmaItinerary_VmaScheduleShifting(itineraryNo,
							portofAuthority, shiftingDate, requestState);
		} catch (Exception e) {
			log.error(e.getMessage());

			return 0;
		}
	}

	public JSONObject export2excel(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
			throws NoSuchVmaItineraryScheduleException, SystemException,
			ParsePropertyException, InvalidFormatException, IOException,
			NoSuchVmaScheduleShiftingException, NoSuchVmaItineraryException,
			ParseException, JRException {
		HttpServletRequest request = resourceRequest;
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);
		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(userPort.getPortCode());
		String maritimeNameVN = dmMaritime.getMaritimeNameVN();
		String maritimeName = dmMaritime.getMaritimeName();
		String maritimeReference = dmMaritime.getMaritimeReference();
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		String certificateNo = ParamUtil.getString(request, "certificateNo");
		long vmaScheduleShiftingId = ParamUtil.getLong(request,
				"vmaScheduleShiftingId");
		int printing = ParamUtil.getInteger(request, "printing");
		
		VmaScheduleShifting vmaScheduleShifting = (vmaScheduleShiftingId == 0) ? 
				VmaScheduleShiftingLocalServiceUtil.findByItineraryNo_CertificateNo(itineraryNo, certificateNo) 
				: VmaScheduleShiftingLocalServiceUtil.fetchVmaScheduleShifting(vmaScheduleShiftingId);
		VmaItinerarySchedule vmaItinerarySchedule = null;
		try {
			vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
					.fetchVmaItinerarySchedule(vmaScheduleShifting
							.getItineraryScheduleId());
		} catch (Exception e) {
		}
		VmaItinerary vmaItinerary = null;
		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.findByitineraryNo(vmaScheduleShifting.getItineraryNo());
		} catch (Exception e) {
		}
		long nanoTime = System.nanoTime();

		JSONObject dataBeans = JSONFactoryUtil.createJSONObject();
		dataBeans.put("printing", printing);
		dataBeans.put("maritimeName", maritimeName);
		dataBeans.put("maritimeNameVN", maritimeNameVN);
		dataBeans.put("anchoringPortWharfCode",
				vmaScheduleShifting.getAnchoringPortWharfCode());
		dataBeans.put("approvalDate", FormatData
				.parseDateToTringType3(vmaScheduleShifting.getApprovalDate()));
		dataBeans.put("approvalName", vmaScheduleShifting.getApprovalName());
		dataBeans.put("cancelDate", FormatData
				.parseDateToTringType3(vmaScheduleShifting.getCancelDate()));
		dataBeans.put("cancelName", vmaScheduleShifting.getCancelName());
		dataBeans.put("cancelNote", vmaScheduleShifting.getCancelNote());
		dataBeans.put("certificateNo", vmaScheduleShifting.getCertificateNo());
		dataBeans.put("directorOfMaritime",
				vmaScheduleShifting.getDirectorOfMaritime());
		dataBeans.put("flagStateOfShip",
				vmaScheduleShifting.getFlagStateOfShip());
		dataBeans.put("isApproval", vmaScheduleShifting.getIsApproval());
		dataBeans.put("isCancel", vmaScheduleShifting.getIsCancel());
		try {
			dataBeans.put("issueDate", FormatData
					.parseDateToTringType3(vmaScheduleShifting.getIssueDate()));
		} catch (Exception e) {
		}
		try {
			dataBeans.put("shiftingDate", FormatData
					.parseDateToTringType3(vmaScheduleShifting
							.getShiftingDate()));
		} catch (Exception e) {
		}
		dataBeans.put("shiftingFrom", vmaScheduleShifting.getFrom());
		dataBeans.put("shiftingTo", vmaScheduleShifting.getTo());
		dataBeans.put("nameOfShip", vmaScheduleShifting.getNameOfShip());
		dataBeans.put("portHarbourCode",
				vmaScheduleShifting.getAnchoringPortHarbourCode());
		dataBeans.put("portofAuthority",
				vmaScheduleShifting.getPortofAuthority());
		dataBeans.put("reasonToShift", vmaScheduleShifting.getReasonToShift());
		dataBeans.put("shiftingPortWharfCode",
				vmaScheduleShifting.getShiftingPortWharfCode());
		if (vmaItinerarySchedule != null) {
			dataBeans.put("documentName",
					vmaItinerarySchedule.getDocumentName());
			dataBeans.put("documentYear",
					vmaItinerarySchedule.getDocumentYear());
			dataBeans.put("GT", vmaItinerarySchedule.getGt());
			dataBeans
					.put("numberOfCrews", vmaItinerarySchedule.getCrewNumber());
			dataBeans.put("numberOfPassengers",
					vmaItinerarySchedule.getPassengerNumber());
		}
		if (vmaItinerary != null) {
			dataBeans.put("flagStateOfShip", DmStateLocalServiceUtil
					.getByStateCode(vmaItinerary.getFlagStateOfShip())
					.getStateName());
		}
		// SonVH sua ten tau = tau keo+ ds xa lan
				try {
					String xalan= StringPool.BLANK;
					List<VmaScheduleMerging> lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 4);
					if (lstVmaScheduleMerging!= null && lstVmaScheduleMerging.size() > 0) {
						int i = 0;
						for (VmaScheduleMerging vmaScheduleMerging : lstVmaScheduleMerging) {	
							if (vmaScheduleMerging.getItineraryScheduleId() == vmaScheduleShifting.getItineraryScheduleId()) {
								i = i+1;
								if (i == 1 ) {
									xalan = xalan + " (" + vmaScheduleMerging.getShipLashName();
								} else if (i <= lstVmaScheduleMerging.size()) {
									xalan = xalan + " + " + vmaScheduleMerging.getShipLashName();
								}
							}							
						}
						if (i > 0) {
							xalan = xalan + ")";
						}
						dataBeans.put("nameOfShip", vmaItinerarySchedule.getNameOfShip() + xalan);
					}
				} catch (Exception e) {
				}
		try {
			dataBeans.put("SignDate", FormatData
					.parseDateToTringDDMMYYY(vmaScheduleShifting.getIssueDate()));
		} catch (Exception e) {
		}
		dataBeans.put("SignTitle", DanhMucUtils.encodeUTF8("GIÁM ĐỐC"));
		log.info("===> data jasper: " + dataBeans);

		// SonVH tam comment, khong su dung ham export excel
		
//		BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
//		String tenFileExport = nanoTime + ".xls";
//		File file = new File(pathFileTemp + "report_CV/" + maritimeReference
//				+ StringPool.UNDERLINE + "vma_shifting_order.jrxml");
//		boolean hasDataBoolean = false;
//		if (file.exists()) {
//			hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
//					+ "report_CV/" + maritimeReference + StringPool.UNDERLINE
//					+ "vma_shifting_order.jrxml", tenFileExport, dataBeans,
//					pathFileTemp + "report_CV/", 1);
//		} else {
//			hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
//					+ "vma_shifting_order.jrxml", tenFileExport, dataBeans,
//					pathFileTemp, 1);
//		}
//
//		String UrlFile = request.getContextPath() + "/export/" + tenFileExport;
//
//		JSONObject data = JSONFactoryUtil.createJSONObject();
//		
//		if (hasDataBoolean) {
//			data.put("urlDownload", UrlFile);
//		} else {
//			data.put("urlDownload", StringPool.BLANK);
//		}
		
		// Viet lai ham in pdf
		BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
		File file = new File(pathFileTemp + "report_CV/" + maritimeReference
				+ StringPool.UNDERLINE + "vma_shifting_order.jrxml");
		boolean hasDataBoolean = false;
		if (file.exists()) {
			log.info(" Vao ham tren");
			hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
					+ "report_CV/" + maritimeReference + StringPool.UNDERLINE
					+ "vma_shifting_order.jrxml", "vma_shifting_order"
					+ StringPool.UNDERLINE + nanoTime + ".pdf", dataBeans,
					pathFileTemp + "report_CV/", 2);
		} else {
			log.info(" Vao ham duoi exportPDF_EXCEL_VMA");
			hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
					+ "vma_shifting_order.jrxml", "vma_shifting_order"
					+ StringPool.UNDERLINE + nanoTime + ".pdf", dataBeans,
					pathFileTemp, 2);		
		}

		String UrlFile = request.getContextPath() + "/export/"
				+ "vma_shifting_order" + StringPool.UNDERLINE + nanoTime
				+ ".pdf";		
		String UrlFileDownLoad = UrlFile.replace(".pdfs", ".pdf");
		JSONObject data = JSONFactoryUtil.createJSONObject();
		if (hasDataBoolean) {
			data.put("urlDownload", UrlFileDownLoad);
		} else {
			data.put("urlDownload", StringPool.BLANK);
		}

		return data;
	}

	public JSONObject lenhDieuDong(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
			throws NoSuchVmaScheduleShiftingException, SystemException,
			ParseException, IOException, JRException {
		HttpServletRequest request = resourceRequest;
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);
		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(userPort.getPortCode());
		String maritimeNameVN = dmMaritime.getMaritimeNameVN();
		String maritimeName = dmMaritime.getMaritimeName();
		String maritimeReference = dmMaritime.getMaritimeReference();
		// String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		// String certificateNo = ParamUtil.getString(request, "certificateNo");
		long vmaScheduleShiftingId = ParamUtil.getLong(request,
				"vmaScheduleShiftingId");
		int printing = ParamUtil.getInteger(request, "printing");
		VmaScheduleShifting vmaScheduleShifting = VmaScheduleShiftingLocalServiceUtil
				.fetchVmaScheduleShifting(vmaScheduleShiftingId);
		VmaItinerarySchedule vmaItinerarySchedule = null;
		try {
			vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
					.fetchVmaItinerarySchedule(vmaScheduleShifting
							.getItineraryScheduleId());
		} catch (Exception e) {
		}
		VmaItinerary vmaItinerary = null;
		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.findByitineraryNo(vmaScheduleShifting.getItineraryNo());
		} catch (Exception e) {
		}
		long nanoTime = System.nanoTime();

		JSONObject dataBeans = JSONFactoryUtil.createJSONObject();
		dataBeans.put("printing", printing);
		dataBeans.put("maritimeName", maritimeName);
		dataBeans.put("maritimeNameVN", maritimeNameVN);
		dataBeans.put("anchoringPortWharfCode",
				vmaScheduleShifting.getAnchoringPortWharfCode());
		dataBeans.put("approvalDate", FormatData
				.parseDateToTringType3(vmaScheduleShifting.getApprovalDate()));
		dataBeans.put("approvalName", vmaScheduleShifting.getApprovalName());
		dataBeans.put("cancelDate", FormatData
				.parseDateToTringType3(vmaScheduleShifting.getCancelDate()));
		dataBeans.put("cancelName", vmaScheduleShifting.getCancelName());
		dataBeans.put("cancelNote", vmaScheduleShifting.getCancelNote());
		dataBeans.put("certificateNo", vmaScheduleShifting.getCertificateNo());
		dataBeans.put("directorOfMaritime",
				vmaScheduleShifting.getDirectorOfMaritime());
		dataBeans.put("flagStateOfShip",
				vmaScheduleShifting.getFlagStateOfShip());
		dataBeans.put("isApproval", vmaScheduleShifting.getIsApproval());
		dataBeans.put("isCancel", vmaScheduleShifting.getIsCancel());
		try {
			dataBeans.put("issueDate", FormatData
					.parseDateToTringType3(vmaScheduleShifting.getIssueDate()));
		} catch (Exception e) {
		}
		try {
			dataBeans.put("shiftingDate", FormatData
					.parseDateToTringType3(vmaScheduleShifting
							.getShiftingDate()));
		} catch (Exception e) {
		}
		dataBeans.put("shiftingFrom", vmaScheduleShifting.getFrom());
		dataBeans.put("shiftingTo", vmaScheduleShifting.getTo());
		dataBeans.put("nameOfShip", vmaScheduleShifting.getNameOfShip());
		dataBeans.put("portHarbourCode",
				vmaScheduleShifting.getAnchoringPortHarbourCode());
		dataBeans.put("portofAuthority",
				vmaScheduleShifting.getPortofAuthority());
		dataBeans.put("reasonToShift", vmaScheduleShifting.getReasonToShift());
		dataBeans.put("shiftingPortWharfCode",
				vmaScheduleShifting.getShiftingPortWharfCode());
		if (vmaItinerarySchedule != null) {
			dataBeans.put("documentName",
					vmaItinerarySchedule.getDocumentName());
			dataBeans.put("documentYear",
					vmaItinerarySchedule.getDocumentYear());
			dataBeans.put("GT", vmaItinerarySchedule.getGt());
			dataBeans
					.put("numberOfCrews", vmaItinerarySchedule.getCrewNumber());
			dataBeans.put("numberOfPassengers",
					vmaItinerarySchedule.getPassengerNumber());
		}
		if (vmaItinerary != null) {
			dataBeans.put("flagStateOfShip", DmStateLocalServiceUtil
					.getByStateCode(vmaItinerary.getFlagStateOfShip())
					.getStateName());
		}
		// SonVH sua ten tau = tau keo+ ds xa lan
		try {
			String xalan= StringPool.BLANK;
			List<VmaScheduleMerging> lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil.findByItineraryNo_NoticeShipType(vmaScheduleShifting.getItineraryNo(), 4);
			if (lstVmaScheduleMerging!= null && lstVmaScheduleMerging.size() > 0) {
				int i = 0;
				for (VmaScheduleMerging vmaScheduleMerging : lstVmaScheduleMerging) {	
					if (vmaScheduleMerging.getItineraryScheduleId() == vmaScheduleShifting.getItineraryScheduleId()) {
						i = i+1;
						if (i == 1 ) {
							xalan = xalan + " (" + vmaScheduleMerging.getShipLashName();
						} else if (i <= lstVmaScheduleMerging.size()) {
							xalan = xalan + " + " + vmaScheduleMerging.getShipLashName();
						}
					}							
				}
				if (i > 0) {
					xalan = xalan + ")";
				}
				dataBeans.put("nameOfShip", vmaItinerarySchedule.getNameOfShip() + xalan);
			}
		} catch (Exception e) {
		}
		try {
			dataBeans.put("SignDate", FormatData
					.parseDateToTringDDMMYYY(vmaScheduleShifting.getIssueDate()));
		} catch (Exception e) {
		}
		dataBeans.put("SignTitle", DanhMucUtils.encodeUTF8("GIÁM ĐỐC"));
		log.info("===> data jasper: " + dataBeans);

		BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
		File file = new File(pathFileTemp + "report_CV/" + maritimeReference
				+ StringPool.UNDERLINE + "vma_shifting_order.jrxml");
		boolean hasDataBoolean = false;
		if (file.exists()) {
			log.info(" Vao ham tren");
			hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
					+ "report_CV/" + maritimeReference + StringPool.UNDERLINE
					+ "vma_shifting_order.jrxml", "vma_shifting_order"
					+ StringPool.UNDERLINE + nanoTime + ".pdf", dataBeans,
					pathFileTemp + "report_CV/", 2);
		} else {
			log.info(" Vao ham duoi exportPDF_EXCEL_VMA");
			hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
					+ "vma_shifting_order.jrxml", "vma_shifting_order"
					+ StringPool.UNDERLINE + nanoTime + ".pdf", dataBeans,
					pathFileTemp, 2);		
		}

		String UrlFile = request.getContextPath() + "/export/"
				+ "vma_shifting_order" + StringPool.UNDERLINE + nanoTime
				+ ".pdf";		
		String UrlFileDownLoad = UrlFile.replace(".pdfs", ".pdf");

		JSONObject data = JSONFactoryUtil.createJSONObject();

		log.info(" ====> hasDataBoolean: " + hasDataBoolean);
		if (hasDataBoolean) {
			data.put("url", UrlFileDownLoad);
		} else {
			data.put("url", StringPool.BLANK);
		}

		return data;
	}

	private String realPath = this.getClass().getProtectionDomain()
			.getCodeSource().getLocation().toString();
	private String pathFileTemp = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/report/baocao/";
	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/export/";
}
