package vn.gt.portlet.phuongtien;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaSchedulePilot;
import com.fds.nsw.nghiepvu.model.VmaSchedulePilotList;
import com.fds.nsw.nghiepvu.model.VmaScheduleTesting;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboat;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList;


import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTestingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatLocalServiceUtil;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
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
public class VmaScheduleTestingUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaScheduleTestingId = ParamUtil.getLong(request,
				"vmaScheduleTestingId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaScheduleTesting vmaScheduleTesting = VmaScheduleTestingLocalServiceUtil
					.getVmaScheduleTesting(vmaScheduleTestingId);
			result = VMAUtils.object2Json(vmaScheduleTesting,
					VmaScheduleTesting.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo portofAuthority nameOfShip documentName documentYear
	 * noticeShipType testingFrom testingTo flagStateOfShip imoNumber callSign
	 * vrCode registryNumber shipCaptain anchoringPortHarbourCode
	 * anchoringPortWharfCode shiftingPortHarbourCode shiftingPortWharfCode
	 * issueDate directorOfMaritime certificateNo requestState versionNo
	 * isApproval approvalDate approvalName remarks isCancel cancelDate
	 * cancelName cancelNote representative digitalAttachedFile signName
	 * signTitle signDate signPlace attachedFile stampStatus shownDraftxF
	 * unitShownDraftxF shownDraftxA unitShownDraftxA loa loaUnit dwt dwtUnit
	 * tugBoat from_ to_ chanelCodeList chanelName modifiedDate
	 */
	public static VmaScheduleTesting getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleTestingId"), -1);
		VmaScheduleTesting vmaScheduleTesting = null;
		if (id > 0) {
			try {
				vmaScheduleTesting = VmaScheduleTestingLocalServiceUtil
						.getVmaScheduleTesting(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaScheduleTesting = new VmaScheduleTesting();
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleTesting.setItineraryNo(itineraryNo);
		}
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleTesting.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleTesting.setNameOfShip(nameOfShip);
		}
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		if (documentName >= 0) {
			vmaScheduleTesting.setDocumentName(documentName);
		}
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		if (documentYear >= 0) {
			vmaScheduleTesting.setDocumentYear(documentYear);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaScheduleTesting.setNoticeShipType(noticeShipType);
		}
		String testingFrom = ParamUtil.getString(actionRequest, "testingFrom",
				StringPool.BLANK);
		if (Validator.isNotNull(testingFrom)) {
			try {
				Date date = FormatData.formatDateShort3.parse(testingFrom);
				vmaScheduleTesting.setTestingFrom(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String testingTo = ParamUtil.getString(actionRequest, "testingTo",
				StringPool.BLANK);
		if (Validator.isNotNull(testingTo)) {
			try {
				Date date = FormatData.formatDateShort3.parse(testingTo);
				vmaScheduleTesting.setTestingTo(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String flagStateOfShip = VMAUtils.getString(actionRequest,
				"flagStateOfShip", StringPool.BLANK);
		if (Validator.isNotNull(flagStateOfShip)) {
			vmaScheduleTesting.setFlagStateOfShip(flagStateOfShip);
		}
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
				StringPool.BLANK);
		if (Validator.isNotNull(imoNumber)) {
			vmaScheduleTesting.setImoNumber(imoNumber);
		}
		String callSign = VMAUtils.getString(actionRequest, "callSign",
				StringPool.BLANK);
		if (Validator.isNotNull(callSign)) {
			vmaScheduleTesting.setCallSign(callSign);
		}
		String vrCode = ParamUtil.getString(actionRequest, "vrCode",
				StringPool.BLANK);
		if (Validator.isNotNull(vrCode)) {
			vmaScheduleTesting.setVrCode(vrCode);
		}
		String registryNumber = VMAUtils.getString(actionRequest,
				"registryNumber", StringPool.BLANK);
		if (Validator.isNotNull(registryNumber)) {
			vmaScheduleTesting.setRegistryNumber(registryNumber);
		}
		String shipCaptain = VMAUtils.getString(actionRequest, "shipCaptain",
				StringPool.BLANK);
		if (Validator.isNotNull(shipCaptain)) {
			vmaScheduleTesting.setShipCaptain(shipCaptain);
		}
		String anchoringPortHarbourCode = ParamUtil.getString(actionRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			vmaScheduleTesting
					.setAnchoringPortHarbourCode(anchoringPortHarbourCode);
		}
		String anchoringPortWharfCode = ParamUtil.getString(actionRequest,
				"anchoringPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortWharfCode)) {
			vmaScheduleTesting
					.setAnchoringPortWharfCode(anchoringPortWharfCode);
		}
		String shiftingPortHarbourCode = ParamUtil.getString(actionRequest,
				"shiftingPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortHarbourCode)) {
			vmaScheduleTesting
					.setShiftingPortHarbourCode(shiftingPortHarbourCode);
		}
		String shiftingPortWharfCode = ParamUtil.getString(actionRequest,
				"shiftingPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(shiftingPortWharfCode)) {
			vmaScheduleTesting.setShiftingPortWharfCode(shiftingPortWharfCode);
		}

		int requestState = GetterUtil.getInteger(
				request.getParameter("requestState"), -1);
		if (requestState >= 0) {
			vmaScheduleTesting.setRequestState(requestState);
		}

		String issueDate = ParamUtil.getString(actionRequest, "issueDate",
				StringPool.BLANK);
		if (Validator.isNotNull(issueDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(issueDate);
				vmaScheduleTesting.setIssueDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		} else {
			if (requestState == 4) {
				vmaScheduleTesting.setIssueDate(new Date());
			}

		}

		String approvalDate = ParamUtil
				.getString(actionRequest, "approvalDate");
		if (Validator.isNotNull(approvalDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(approvalDate);
				vmaScheduleTesting.setApprovalDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		} else {
			if (requestState == 4) {
				vmaScheduleTesting.setApprovalDate(new Date());
			}
		}

		String directorOfMaritime = VMAUtils.getString(actionRequest,
				"directorOfMaritime", StringPool.BLANK);
		if (Validator.isNotNull(directorOfMaritime)) {
			vmaScheduleTesting.setDirectorOfMaritime(directorOfMaritime);
		}
		String certificateNo = ParamUtil.getString(actionRequest,
				"certificateNo", StringPool.BLANK);
		if (Validator.isNotNull(certificateNo)) {
			vmaScheduleTesting.setCertificateNo(certificateNo);
		}

		String versionNo = ParamUtil.getString(actionRequest, "versionNo",
				StringPool.BLANK);
		if (Validator.isNotNull(versionNo)) {
			vmaScheduleTesting.setVersionNo(versionNo);
		}
		int isApproval = GetterUtil.getInteger(
				request.getParameter("isApproval"), -1);
		if (isApproval >= 0) {
			vmaScheduleTesting.setIsApproval(isApproval);
		}

		String approvalName = VMAUtils.getString(actionRequest, "approvalName",
				StringPool.BLANK);
		if (Validator.isNotNull(approvalName)) {
			vmaScheduleTesting.setApprovalName(approvalName);
		}
		String remarks = VMAUtils.getString(actionRequest, "remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(remarks)) {
			vmaScheduleTesting.setRemarks(remarks);
		}
		
		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaScheduleTesting.setShipOwnerCode(shipOwnerCode);
		}
		String shipOwnersName = VMAUtils.getString(actionRequest,
				"shipOwnersName", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnersName)) {
			vmaScheduleTesting.setShipOwnersName(shipOwnersName);
		}
		
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaScheduleTesting.setShipOperatorCode(shipOperatorCode);
		}
		String shipOperatorName = VMAUtils.getString(actionRequest,
				"shipOperatorName", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorName)) {
			vmaScheduleTesting.setShipOperatorName(shipOperatorName);
		}
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaScheduleTesting.setShipAgencyCode(shipAgencyCode);
		}
		String shipAgencyName = VMAUtils.getString(actionRequest,
				"shipAgencyName", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyName)) {
			vmaScheduleTesting.setShipAgencyName(shipAgencyName);
		}
		String shipAgencyAddress = VMAUtils.getString(actionRequest,
				"shipAgencyAddress", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyAddress)) {
			vmaScheduleTesting.setShipAgencyAddress(shipAgencyAddress);
		}
		String shipAgencyContactEmail = ParamUtil.getString(actionRequest,
				"shipAgencyContactEmail", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyContactEmail)) {
			vmaScheduleTesting
					.setShipAgencyContactEmail(shipAgencyContactEmail);
		}
		String shipAgencyPhone = ParamUtil.getString(actionRequest,
				"shipAgencyPhone", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyPhone)) {
			vmaScheduleTesting.setShipAgencyPhone(shipAgencyPhone);
		}
		String shipAgencyFax = ParamUtil.getString(actionRequest,
				"shipAgencyFax", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyFax)) {
			vmaScheduleTesting.setShipAgencyFax(shipAgencyFax);
		}
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaScheduleTesting.setSecurityLevelCode(securityLevelCode);
		}
		
		String purposeCode = VMAUtils.getString(actionRequest, "purposeCode",
				StringPool.BLANK);
		if (Validator.isNotNull(purposeCode)) {
			vmaScheduleTesting.setPurposeCode(purposeCode);
		}
		String purposeSpecified = VMAUtils.getString(actionRequest,
				"purposeSpecified", StringPool.BLANK);
		if (Validator.isNotNull(purposeSpecified)) {
			vmaScheduleTesting.setPurposeSpecified(purposeSpecified);
		}
		String departmentCode = VMAUtils.getString(actionRequest,
				"departmentCode", StringPool.BLANK);
		if (Validator.isNotNull(departmentCode)) {
			vmaScheduleTesting.setDepartmentCode(departmentCode);
		}
		
		String departmentName = VMAUtils.getString(actionRequest,
				"departmentName", StringPool.BLANK);
		if (Validator.isNotNull(departmentName)) {
			vmaScheduleTesting.setDepartmentName(departmentName);
		}
		int isCancel = GetterUtil.getInteger(request.getParameter("isCancel"),
				-1);
		if (isCancel >= 0) {
			vmaScheduleTesting.setIsCancel(isCancel);
		}
		
		String cancelDate = GetterUtil.getString(
				request.getParameter("cancelDate"), StringPool.BLANK);
		if (Validator.isNotNull(cancelDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(cancelDate);
				vmaScheduleTesting.setCancelDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String cancelName = VMAUtils.getString(actionRequest, "cancelName",
				StringPool.BLANK);
		if (Validator.isNotNull(cancelName)) {
			vmaScheduleTesting.setCancelName(cancelName);
		}
		String cancelNote = VMAUtils.getString(actionRequest, "cancelNote",
				StringPool.BLANK);
		if (Validator.isNotNull(cancelNote)) {
			vmaScheduleTesting.setCancelNote(cancelNote);
		}
		String representative = VMAUtils.getString(actionRequest,
				"representative", StringPool.BLANK);
		if (Validator.isNotNull(representative)) {
			vmaScheduleTesting.setRepresentative(representative);
		}
		int digitalAttachedFile = GetterUtil.getInteger(
				request.getParameter("digitalAttachedFile"), -1);
		if (digitalAttachedFile >= 0) {
			vmaScheduleTesting.setDigitalAttachedFile(digitalAttachedFile);
		}
		String signName = VMAUtils.getString(actionRequest, "signName",
				StringPool.BLANK);
		if (Validator.isNotNull(signName)) {
			vmaScheduleTesting.setSignName(signName);
		}
		String signTitle = VMAUtils.getString(actionRequest, "signTitle",
				StringPool.BLANK);
		if (Validator.isNotNull(signTitle)) {
			vmaScheduleTesting.setSignTitle(signTitle);
		}
		String signDate = ParamUtil.getString(actionRequest, "signDate",
				StringPool.BLANK);
		if (Validator.isNotNull(signDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(signDate);
				vmaScheduleTesting.setSignDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String signPlace = VMAUtils.getString(actionRequest, "signPlace",
				StringPool.BLANK);
		if (Validator.isNotNull(signPlace)) {
			vmaScheduleTesting.setSignPlace(signPlace);
		}
		long attachedFile = GetterUtil.getLong(
				request.getParameter("attachedFile"), -1);
		if (attachedFile >= 0) {
			vmaScheduleTesting.setAttachedFile(attachedFile);
		}
		int stampStatus = GetterUtil.getInteger(
				request.getParameter("stampStatus"), -1);
		if (stampStatus >= 0) {
			vmaScheduleTesting.setStampStatus(stampStatus);
		}
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		if (shownDraftxF >= 0) {
			vmaScheduleTesting.setShownDraftxF(shownDraftxF);
		}
		String unitShownDraftxF = VMAUtils.getString(actionRequest,
				"unitShownDraftxF", StringPool.BLANK);
		if (Validator.isNotNull(unitShownDraftxF)) {
			vmaScheduleTesting.setUnitShownDraftxF(unitShownDraftxF);
		}
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);
		if (shownDraftxA >= 0) {
			vmaScheduleTesting.setShownDraftxA(shownDraftxA);
		}
		String unitShownDraftxA = VMAUtils.getString(actionRequest,
				"unitShownDraftxA", StringPool.BLANK);
		if (Validator.isNotNull(unitShownDraftxA)) {
			vmaScheduleTesting.setUnitShownDraftxA(unitShownDraftxA);
		}
		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);
		if (loa >= 0) {
			vmaScheduleTesting.setLoa(loa);
		}
		String loaUnit = VMAUtils.getString(actionRequest, "loaUnit",
				StringPool.BLANK);
		if (Validator.isNotNull(loaUnit)) {
			vmaScheduleTesting.setLoaUnit(loaUnit);
		}
		int dwt = GetterUtil.getInteger(request.getParameter("dwt"), -1);
		if (dwt >= 0) {
			vmaScheduleTesting.setDwt(BigDecimal.valueOf(dwt));
		}
		String dwtUnit = VMAUtils.getString(actionRequest, "dwtUnit",
				StringPool.BLANK);
		if (Validator.isNotNull(dwtUnit)) {
			vmaScheduleTesting.setDwtUnit(dwtUnit);
		}
		String tugBoat = VMAUtils.getString(actionRequest, "tugBoat",
				StringPool.BLANK);
		if (Validator.isNotNull(tugBoat)) {
			vmaScheduleTesting.setTugBoat(tugBoat);
		}
		/*
		 * String from_ = VMAUtils.getString(actionRequest, "from_",
		 * StringPool.BLANK); if (Validator.isNotNull(from_)) {
		 * vmaScheduleTesting.setFrom_(from_); } String to_ =
		 * VMAUtils.getString(actionRequest, "to_", StringPool.BLANK); if
		 * (Validator.isNotNull(to_)) { vmaScheduleTesting.setTo_(to_); }
		 */

		String from_ = VMAUtils.getFrom_To(anchoringPortHarbourCode,
				anchoringPortWharfCode);
		if (Validator.isNotNull(from_)) {
			vmaScheduleTesting.setFrom(from_);
		}

		String to_ = VMAUtils.getFrom_To(shiftingPortHarbourCode,
				anchoringPortWharfCode);
		if (Validator.isNotNull(to_)) {
			vmaScheduleTesting.setTo(to_);
		}

		String chanelCodeList = VMAUtils.getString(actionRequest,
				"chanelCodeList", StringPool.BLANK);
		if (Validator.isNotNull(chanelCodeList)) {
			vmaScheduleTesting.setChanelCodeList(chanelCodeList);
			vmaScheduleTesting.setChanelName(VMAUtils
					.getChanelName(chanelCodeList));
		}
		/*
		 * String chanelName = VMAUtils.getString(actionRequest, "chanelName",
		 * StringPool.BLANK); if (Validator.isNotNull(chanelName)) {
		 * vmaScheduleTesting.setChanelName(chanelName); }
		 */
		/*
		 * String modifiedDate = ParamUtil.getString(actionRequest,
		 * "modifiedDate", StringPool.BLANK); if
		 * (Validator.isNotNull(modifiedDate)) { try { Date date =
		 * FormatData.formatDateShort3.parse(modifiedDate);
		 * vmaScheduleTesting.setModifiedDate(date); } catch (ParseException e)
		 * { log.error(e.getMessage()); } }
		 */

		String mergedShip = VMAUtils.getString(actionRequest, "mergedShip",
				StringPool.BLANK);
		if (Validator.isNotNull(mergedShip)) {
			vmaScheduleTesting.setMergedShip(mergedShip);
		}

		/*
		 * String chanelName = ParamUtil.getString(actionRequest, "chanelName",
		 * StringPool.BLANK); if (Validator.isNotNull(chanelName)) {
		 * vmaScheduleTesting.setChanelName(chanelName); }
		 */

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);
		if (itineraryScheduleId >= 0) {
			vmaScheduleTesting.setItineraryScheduleId(itineraryScheduleId);
		}
		return vmaScheduleTesting;
	}

	public static JSONObject addVmaScheduleTesting(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleTesting vmaScheduleTesting = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleTesting == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		VmaScheduleTesting _tmp = null;

		try {
			_tmp = VmaScheduleTestingLocalServiceUtil
					.fetchByitineraryNo_noticeShipType_certificateNo(
							vmaScheduleTesting.getItineraryNo(),
							vmaScheduleTesting.getNoticeShipType(),
							vmaScheduleTesting.getCertificateNo());
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (_tmp != null) {
			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "duplicate_entry"),
					"duplicate_entry", StringPool.BLANK);
		}

		try {
			vmaScheduleTesting = VmaScheduleTestingLocalServiceUtil
					.addVmaScheduleTesting(vmaScheduleTesting);
			result = VMAUtils.object2Json(vmaScheduleTesting,
					VmaScheduleTesting.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleTesting(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleTesting vmaScheduleTesting = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleTesting == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleTesting = VmaScheduleTestingLocalServiceUtil
					.updateVmaScheduleTesting(vmaScheduleTesting);
			result = VMAUtils.object2Json(vmaScheduleTesting,
					VmaScheduleTesting.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaScheduleTesting(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaScheduleTestingId");
		if (id > 0) {
			String desciption = "";
			try {
				// Lấy đối tượng gốc VmaScheduleTesting
				VmaScheduleTesting curScheduleTesting = VmaScheduleTestingLocalServiceUtil
						.getVmaScheduleTesting(id);
				String itineraryNo = curScheduleTesting.getItineraryNo();
				String certificateNo = curScheduleTesting.getCertificateNo();
				int noticeShipType = 5;

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

				// Xóa đối tượng gốc
				VmaScheduleTestingLocalServiceUtil
						.deleteVmaScheduleTesting(curScheduleTesting);

				return VMAUtils.createResponseMessage(
						LanguageUtil.get(themeDisplay.getLocale(), "success"),
						"", desciption);
			} catch (Exception e) {
				return VMAUtils.createResponseMessage(LanguageUtil.get(
						themeDisplay.getLocale(), "system_error"), "ERROR",
						desciption);
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
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		String testingFrom = ParamUtil.getString(resourceRequest,
				"testingFrom", StringPool.BLANK);
		String testingTo = ParamUtil.getString(resourceRequest, "testingTo",
				StringPool.BLANK);
		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);
		String vrCode = ParamUtil.getString(resourceRequest, "vrCode",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);

		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);

		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);

		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);

		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);

		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		try {
			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, testingFrom,
					testingTo, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber,
					shownDraftxF >= 0 ? shownDraftxF : null,
					shownDraftxA >= 0 ? shownDraftxA : null, loa >= 0 ? loa
							: null, dwt >= 0 ? dwt : null, timeOfArrival,
					timeOfDeparture);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, testingFrom,
					testingTo, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber,
					shownDraftxF >= 0 ? shownDraftxF : null,
					shownDraftxA >= 0 ? shownDraftxA : null, loa >= 0 ? loa
							: null, dwt >= 0 ? dwt : null, timeOfArrival,
					timeOfDeparture);

			return VmaScheduleTestingLocalServiceUtil.findVmaScheduleTesting(
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
		String testingFrom = ParamUtil.getString(resourceRequest,
				"testingFrom", StringPool.BLANK);
		String testingTo = ParamUtil.getString(resourceRequest, "testingTo",
				StringPool.BLANK);
		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);
		String vrCode = ParamUtil.getString(resourceRequest, "vrCode",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);

		String certificateNo = ParamUtil.getString(resourceRequest,
				"certificateNo", StringPool.BLANK);

		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);

		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);

		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);

		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);

		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		/*
		 * String[] headers = new String[] { "STT", "Tên tàu", "Thời gian đến",
		 * "Thời gian đi", "Số thuyền viên", "Thời gian bắt đầu", "Hô hiệu",
		 * "Thời gian kết thúc", "Trọng tải", "Chiều dài", "Thuyền trưởng",
		 * "Mớn nước mũi", "Mớn nước lái", "Từ vị trí", "Đến vị trí",
		 * "Số người theo tàu", "Số giấy phép" };
		 */

		String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
				"Th\u1EDDi gian \u0111\u1EBFn", "Th\u1EDDi gian \u0111i",
				"S\u1ED1 thuy\u1EC1n vi\u00EAn",
				"Th\u1EDDi gian b\u1EAFt \u0111\u1EA7u", "H\u00F4 hi\u1EC7u",
				"Th\u1EDDi gian k\u1EBFt th\u00FAc", "Tr\u1ECDng t\u1EA3i",
				"Chi\u1EC1u d\u00E0i", "Thuy\u1EC1n tr\u01B0\u1EDFng",
				"M\u1EDBn n\u01B0\u1EDBc m\u0169i",
				"M\u1EDBn n\u01B0\u1EDBc l\u00E1i", "T\u1EEB v\u1ECB tr\u00ED",
				"\u0110\u1EBFn v\u1ECB tr\u00ED",
				"S\u1ED1 ng\u01B0\u1EDDi theo t\u00E0u",
				"S\u1ED1 gi\u1EA5y ph\u00E9p" };

		String[][] exportData = null;

		try {
			String searchQuery = generateQuery("search", itineraryNo,
					portofAuthority, documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, testingFrom,
					testingTo, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber,
					shownDraftxF >= 0 ? shownDraftxF : null,
					shownDraftxA >= 0 ? shownDraftxA : null, loa >= 0 ? loa
							: null, dwt >= 0 ? dwt : null, timeOfArrival,
					timeOfDeparture);

			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, testingFrom,
					testingTo, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber,
					shownDraftxF >= 0 ? shownDraftxF : null,
					shownDraftxA >= 0 ? shownDraftxA : null, loa >= 0 ? loa
							: null, dwt >= 0 ? dwt : null, timeOfArrival,
					timeOfDeparture);

			JSONObject objects = VmaScheduleTestingLocalServiceUtil
					.findVmaScheduleTesting(searchQuery, countQuery, -1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");

					timeOfArrival = object.getString("timeOfArrival");

					timeOfDeparture = object.getString("timeOfDeparture");

					// ignore
					int crewNumber = object.getInt("crewNumber");

					testingFrom = object.getString("testingFrom");

					callSign = object.getString("callSign");

					testingTo = object.getString("testingTo");

					dwt = object.getDouble("dwt");

					loa = object.getDouble("loa");

					// TODO get from vmaItinerary
					String shipCaptain = object.getString("shipCaptain");

					shownDraftxA = object.getDouble("shownDraftxA");

					shownDraftxF = object.getDouble("shownDraftxF");

					// from_ = object.getString("from_");

					// to_ = object.getString("to_");

					String anchoringPortHarbourName = object
							.getString("anchoringPortHarbourName");
					String anchoringPortWharfName = object
							.getString("anchoringPortWharfName");
					String shiftingPortHarbourName = object
							.getString("shiftingPortHarbourName");
					String shiftingPortWharfName = object
							.getString("shiftingPortWharfName");

					// ignore
					int numberOfPersons = object.getInt("crewNumber");

					certificateNo = object.getString("certificateNo");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = timeOfArrival;
					exportData[i][3] = timeOfDeparture;
					exportData[i][4] = String.valueOf(crewNumber);
					exportData[i][5] = testingFrom;
					exportData[i][6] = callSign;
					exportData[i][7] = testingTo;
					exportData[i][8] = String.valueOf(dwt);
					exportData[i][9] = String.valueOf(loa);
					exportData[i][10] = shipCaptain;
					exportData[i][11] = String.valueOf(shownDraftxA);
					exportData[i][12] = String.valueOf(shownDraftxF);
					// exportData[i][13] = from_;
					// exportData[i][14] = to_;
					exportData[i][13] = anchoringPortHarbourName
							+ StringPool.DASH + anchoringPortWharfName;
					exportData[i][14] = shiftingPortHarbourName
							+ StringPool.DASH + shiftingPortWharfName;
					exportData[i][15] = String.valueOf(numberOfPersons);
					exportData[i][16] = certificateNo;

				}
			}

			String sheetName = "VMA-Schedule-Testing";

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
		String testingFrom = ParamUtil.getString(resourceRequest,
				"testingFrom", StringPool.BLANK);
		String testingTo = ParamUtil.getString(resourceRequest, "testingTo",
				StringPool.BLANK);
		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);
		String vrCode = ParamUtil.getString(resourceRequest, "vrCode",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);

		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);

		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);

		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);

		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);

		String timeOfArrival = ParamUtil.getString(resourceRequest,
				"timeOfArrival", StringPool.BLANK);

		String timeOfDeparture = ParamUtil.getString(resourceRequest,
				"timeOfDeparture", StringPool.BLANK);

		try {
			String countQuery = generateQuery("count", itineraryNo,
					portofAuthority, documentName >= 0 ? documentName : null,
					documentYear >= 0 ? documentYear : null,
					noticeShipType >= 0 ? noticeShipType : null, testingFrom,
					testingTo, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber,
					shownDraftxF >= 0 ? shownDraftxF : null,
					shownDraftxA >= 0 ? shownDraftxA : null, loa >= 0 ? loa
							: null, dwt >= 0 ? dwt : null, timeOfArrival,
					timeOfDeparture);
			return VmaScheduleTestingLocalServiceUtil
					.countVmaScheduleTesting(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());

			return 0;
		}
	}

	private static String generateQuery(String cmd, String itineraryNo,
			String portofAuthority, Long documentName, Integer documentYear,
			Integer noticeShipType, String testingFrom, String testingTo,
			String nameOfShip, String flagStateOfShip, String imoNumber,
			String callSign, String vrCode, String registryNumber,
			Double shownDraftxF, Double shownDraftxA, Double loa, Double dwt,
			String timeOfArrival, String timeOfDeparture) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_schedule_testing as a";
		} else {
			sql = "SELECT a.* FROM vma_schedule_testing AS a";
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

		if (Validator.isNotNull(flagStateOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("FlagStateofShip", "'%"
					+ flagStateOfShip + "%'", "AND", StringPool.LIKE));
		}

		if (noticeShipType != null) {
			condition.append(VMAUtils.buildSQLCondition("NoticeShipType",
					noticeShipType, "AND", StringPool.EQUAL));
		}

		if (shownDraftxF != null) {
			condition.append(VMAUtils.buildSQLCondition("ShownDraftxF",
					shownDraftxF, "AND", StringPool.EQUAL));
		}

		if (shownDraftxA != null) {
			condition.append(VMAUtils.buildSQLCondition("ShownDraftxA",
					shownDraftxA, "AND", StringPool.EQUAL));
		}

		if (loa != null) {
			condition.append(VMAUtils.buildSQLCondition("LOA", loa, "AND",
					StringPool.EQUAL));
		}

		if (dwt != null) {
			condition.append(VMAUtils.buildSQLCondition("DWT", dwt, "AND",
					StringPool.EQUAL));
		}

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

		if (Validator.isNotNull(callSign)) {
			condition.append(VMAUtils.buildSQLCondition("CallSign", "'%"
					+ callSign + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(vrCode)) {
			condition.append(VMAUtils.buildSQLCondition("VRCode", "'%" + vrCode
					+ "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(registryNumber)) {
			condition.append(VMAUtils.buildSQLCondition("RegistryNumber", "'%"
					+ registryNumber + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(VMAUtils.buildSQLCondition("IMONumber", "'%"
					+ imoNumber + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(testingFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(testingFrom);
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

		if (Validator.isNotNull(testingTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(testingTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("TestingTo", "'"
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

	public static JSONObject viewPDF(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			HttpServletRequest request = resourceRequest;
			long vmaScheduleTestingId = ParamUtil.getLong(request,
					"vmaScheduleTestingId");
			VmaScheduleTesting vmaScheduleTesting = VmaScheduleTestingLocalServiceUtil
					.fetchVmaScheduleTesting(vmaScheduleTestingId);
			// save file
			long nanoTime = ReportsBusinessUtils
					.actionExport(vmaScheduleTesting);

			String tenFileExport = nanoTime + "_"
					+ ReportConstant.VMA_SCHEDULE_TESTING_EXPORT;

			String urlFile = request.getContextPath() + "/export/"
					+ tenFileExport;

			result.put("url", urlFile);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

}
