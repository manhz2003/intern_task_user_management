package vn.gt.portlet.danhmuc;

import java.text.ParseException;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation;

import vn.gt.dao.noticeandmessage.service.VmaAdministrativeViolationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.utils.FormatData;
import vn.gt.utils.PageType;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaAdministrativeViolationUtils {

	

	public static JSONObject getVmaAdministrativeViolations(
			String portofAuthority, String violationDate,
			String notViolationDate, int start, int end) throws SystemException {
		List<VmaAdministrativeViolation> vmaAdministrativeViolations = null;
		long total = 0;
		vmaAdministrativeViolations = VmaAdministrativeViolationLocalServiceUtil
				.findVmaAdministrativeViolations(portofAuthority,
						violationDate, notViolationDate, start, end);
		total = VmaAdministrativeViolationLocalServiceUtil
				.countVmaAdministrativeViolation(portofAuthority,
						violationDate, notViolationDate);
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();
		for (VmaAdministrativeViolation vmaAdministrativeViolation : vmaAdministrativeViolations) {
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("id", vmaAdministrativeViolation.getId());
			result.put("portofAuthority",
					vmaAdministrativeViolation.getPortofAuthority());
			result.put("violationCode",
					vmaAdministrativeViolation.getViolationCode());
			result.put("violationBrief",
					vmaAdministrativeViolation.getViolationBrief());
			result.put("damagedPartName",
					vmaAdministrativeViolation.getDamagedPartName());
			result.put("conclusion", vmaAdministrativeViolation.getConclusion());
			result.put("metaData", vmaAdministrativeViolation.getMetaData());
			result.put("paymentAmount",
					vmaAdministrativeViolation.getPaymentAmount());
			result.put("amountInWords",
					vmaAdministrativeViolation.getAmountInWords());
			result.put("documentaryNo",
					vmaAdministrativeViolation.getDocumentaryNo());
			result.put("documentaryDate",
					vmaAdministrativeViolation.getDocumentaryDate());
			result.put("paymentDate",
					vmaAdministrativeViolation.getPaymentDate());
			result.put("nameOfShip", vmaAdministrativeViolation.getNameOfShip());
			result.put("imoNumber", vmaAdministrativeViolation.getImoNumber());
			result.put("callSign", vmaAdministrativeViolation.getCallSign());
			result.put("flagStateOfShip",
					vmaAdministrativeViolation.getFlagStateOfShip());
			result.put("registryNumber",
					vmaAdministrativeViolation.getRegistryNumber());
			result.put("decisionNo", vmaAdministrativeViolation.getDecisionNo());
			result.put("decisionDate",
					vmaAdministrativeViolation.getDecisionDate());
			result.put("decisionOrganiztion",
					vmaAdministrativeViolation.getDecisionOrganization());
			result.put("officialNo", vmaAdministrativeViolation.getOfficialNo());
			result.put("officialDate",
					vmaAdministrativeViolation.getOfficialDate());
			result.put("officialPlace",
					vmaAdministrativeViolation.getOfficialPlace());
			result.put("violationDate",
					vmaAdministrativeViolation.getViolationDate());
			result.put("violationPartCode",
					vmaAdministrativeViolation.getViolationPartCode());
			result.put("issueDate", vmaAdministrativeViolation.getIssueDate());
			result.put("issueBy", vmaAdministrativeViolation.getIssueBy());
			result.put("violationPartName",
					vmaAdministrativeViolation.getViolationPartName());
			result.put("violationPartAddress",
					vmaAdministrativeViolation.getViolationPartAddress());
			result.put("representative",
					vmaAdministrativeViolation.getRepresentative());
			result.put("representativeTitle",
					vmaAdministrativeViolation.getRepresentativeTitle());
			result.put("f1AttachedReport",
					vmaAdministrativeViolation.getF1AttachedReport());
			result.put("f2AttachedReport",
					vmaAdministrativeViolation.getF2AttachedReport());
			result.put("modifiedDate",
					vmaAdministrativeViolation.getModifiedDate());
			array.put(result);
		}
		obj.put("total", total);
		obj.put("data", array);
		return obj;
	}

	public static JSONObject getDetailVmaAdministrativeViolations(long id)
			throws SystemException {
		VmaAdministrativeViolation vmaAdministrativeViolation = VmaAdministrativeViolationLocalServiceUtil
				.fetchVmaAdministrativeViolation(id);
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("id", vmaAdministrativeViolation.getId());
		result.put("portofAuthority",
				vmaAdministrativeViolation.getPortofAuthority());
		result.put("violationCode",
				vmaAdministrativeViolation.getViolationCode());
		result.put("violationBrief",
				vmaAdministrativeViolation.getViolationBrief());
		result.put("damagedPartName",
				vmaAdministrativeViolation.getDamagedPartName());
		result.put("conclusion", vmaAdministrativeViolation.getConclusion());
		result.put("metaData", vmaAdministrativeViolation.getMetaData());
		result.put("paymentAmount",
				vmaAdministrativeViolation.getPaymentAmount());
		result.put("amountInWords",
				vmaAdministrativeViolation.getAmountInWords());
		result.put("documentaryNo",
				vmaAdministrativeViolation.getDocumentaryNo());
		result.put("documentaryDate",
				vmaAdministrativeViolation.getDocumentaryDate());
		result.put("paymentDate", vmaAdministrativeViolation.getPaymentDate());
		result.put("nameOfShip", vmaAdministrativeViolation.getNameOfShip());
		result.put("imoNumber", vmaAdministrativeViolation.getImoNumber());
		result.put("callSign", vmaAdministrativeViolation.getCallSign());
		result.put("flagStateOfShip",
				vmaAdministrativeViolation.getFlagStateOfShip());
		result.put("registryNumber",
				vmaAdministrativeViolation.getRegistryNumber());
		result.put("decisionNo", vmaAdministrativeViolation.getDecisionNo());
		result.put("decisionDate", vmaAdministrativeViolation.getDecisionDate());
		result.put("decisionOrganiztion",
				vmaAdministrativeViolation.getDecisionOrganization());
		result.put("officialNo", vmaAdministrativeViolation.getOfficialNo());
		result.put("officialDate", vmaAdministrativeViolation.getOfficialDate());
		result.put("officialPlace",
				vmaAdministrativeViolation.getOfficialPlace());
		result.put("violationDate",
				vmaAdministrativeViolation.getViolationDate());
		result.put("violationPartCode",
				vmaAdministrativeViolation.getViolationPartCode());
		result.put("issueDate", vmaAdministrativeViolation.getIssueDate());
		result.put("issueBy", vmaAdministrativeViolation.getIssueBy());
		result.put("violationPartName",
				vmaAdministrativeViolation.getViolationPartName());
		result.put("violationPartAddress",
				vmaAdministrativeViolation.getViolationPartAddress());
		result.put("representative",
				vmaAdministrativeViolation.getRepresentative());
		result.put("representativeTitle",
				vmaAdministrativeViolation.getRepresentativeTitle());
		result.put("f1AttachedReport",
				vmaAdministrativeViolation.getF1AttachedReport());
		result.put("f2AttachedReport",
				vmaAdministrativeViolation.getF2AttachedReport());
		result.put("modifiedDate", vmaAdministrativeViolation.getModifiedDate());

		return result;
	}

	public static JSONObject actionUpdateVmaAdministrativeViolation(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
			throws ParseException {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String portofAuthority = userPort.getPortCode();
		String violationCode = ParamUtil.getString(actionRequest,
				"violationCode");
		String violationBrief = ParamUtil.getString(actionRequest,
				"violationBrief");
		String damagedPartName = ParamUtil.getString(actionRequest,
				"damagedPartName");
		String conclusion = ParamUtil.getString(actionRequest, "conclusion");
		String metaData = ParamUtil.getString(actionRequest, "metaData");
		double paymentAmount = ParamUtil.getDouble(actionRequest, "paymentAmount", 0L);
		String amountInWords = ParamUtil.getString(actionRequest,
				"amountInWords");
		String documentaryNo = ParamUtil.getString(actionRequest,
				"documentaryNo");
		String documentaryDate = ParamUtil.getString(actionRequest,
				"documentaryDate");
		String paymentDate = ParamUtil.getString(actionRequest, "paymentDate");
		String nameOfShip = ParamUtil.getString(actionRequest, "nameOfShip");
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber");
		String callSign = ParamUtil.getString(actionRequest, "signCall");
		String flagStateOfShip = ParamUtil.getString(actionRequest,
				"flagStateOfShip");
		String registryNumber = ParamUtil.getString(actionRequest,
				"registryNumber");
		String decisionNo = ParamUtil.getString(actionRequest, "decisionNo");
		String decisionDate = ParamUtil
				.getString(actionRequest, "decisionDate");
		String decisionOrganization = ParamUtil.getString(actionRequest,
				"decisionOrganization");
		String officialNo = ParamUtil.getString(actionRequest, "officialNo");
		String officialPlace = ParamUtil.getString(actionRequest,
				"officialPlace");
		String violationDate = ParamUtil.getString(actionRequest,
				"violationDate");
		String violationPartCode = ParamUtil.getString(actionRequest,
				"violationPartCode");
		String issueDate = ParamUtil.getString(actionRequest, "issueDate");
		String issueBy = ParamUtil.getString(actionRequest, "issueBy");
		String violationPartName = ParamUtil.getString(actionRequest,
				"violationPartName");
		String violationPartAddress = ParamUtil.getString(actionRequest,
				"violationPartAddress");
		String representativeTitle = ParamUtil.getString(actionRequest,
				"representativeTitle");
		String f1AttachedReport = ParamUtil.getString(actionRequest,
				"f1AttachedReport");
		String f2AttachedReport = ParamUtil.getString(actionRequest,
				"f2AttachedReport");
		String representative = ParamUtil.getString(actionRequest,
				"representative");
		String officialDate = ParamUtil
				.getString(actionRequest, "officialDate");

		String capmoi = ParamUtil.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = ParamUtil.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = ParamUtil.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);

		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaAdministrativeViolation vmaAdministrativeViolation = null;
		try {
			if (danhdauXoa.length() > 0) {
				log.info("Xoa VmaAdministrativeViolation");
				long id = ParamUtil.getLong(actionRequest, "id");
				vmaAdministrativeViolation = VmaAdministrativeViolationLocalServiceUtil
						.delete(id);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"vma_administrative_violation",
						vmaAdministrativeViolation.getViolationCode(),
						vmaAdministrativeViolation.getNameOfShip());
			} else if (capmoi.length() > 0) {
				log.info("Them moi VmaAdministrativeViolation");
				violationCode = String.valueOf(CounterLocalServiceUtil
						.increment("code#" + "vma_administrative_violation"));
				VmaAdministrativeViolation vmaAdministrativeViolationImpl = new VmaAdministrativeViolation();

				vmaAdministrativeViolationImpl.setViolationCode(violationCode);
				if (Validator.isNotNull(violationBrief)
						& !violationBrief.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setViolationBrief(violationBrief);
				}
				if (Validator.isNotNull(violationDate)
						& !violationDate.isEmpty()) {
					vmaAdministrativeViolationImpl.setViolationDate((Validator
							.isNotNull(violationDate) && !violationDate
							.isEmpty()) ? FormatData.formatDDMMYYYY
							.parse(violationDate) : null);
				}
				if (Validator.isNotNull(portofAuthority)
						& !portofAuthority.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setPortofAuthority(portofAuthority);
				}
				if (Validator.isNotNull(damagedPartName)
						& !damagedPartName.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setDamagedPartName(damagedPartName);
				}
				if (Validator.isNotNull(conclusion) & !conclusion.isEmpty()) {
					vmaAdministrativeViolationImpl.setConclusion(conclusion);
				}
				if (Validator.isNotNull(metaData) & !metaData.isEmpty()) {
					vmaAdministrativeViolationImpl.setMetaData(metaData);
				}
				if (Validator.isNotNull(paymentAmount) & paymentAmount != 0) {
					vmaAdministrativeViolationImpl
							.setPaymentAmount(String.valueOf(paymentAmount));
				}
				if (Validator.isNotNull(amountInWords)
						& !amountInWords.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setAmountInWords(amountInWords);
				}
				if (Validator.isNotNull(documentaryNo)
						& !documentaryNo.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setDocumentaryNo(documentaryNo);
				}
				if (Validator.isNotNull(documentaryDate)
						& !documentaryDate.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setDocumentaryDate((Validator
									.isNotNull(documentaryDate) && !documentaryDate
									.isEmpty()) ? FormatData.formatDDMMYYYY
									.parse(documentaryDate) : null);
				}
				if (Validator.isNotNull(paymentDate) & !paymentDate.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setPaymentDate((Validator.isNotNull(paymentDate) && !paymentDate
									.isEmpty()) ? FormatData.formatDDMMYYYY
									.parse(paymentDate) : null);
				}
				if (Validator.isNotNull(nameOfShip) & !nameOfShip.isEmpty()) {
					vmaAdministrativeViolationImpl.setNameOfShip(nameOfShip);
				}
				if (Validator.isNotNull(imoNumber) & !imoNumber.isEmpty()) {
					vmaAdministrativeViolationImpl.setImoNumber(imoNumber);
				}
				if (Validator.isNotNull(callSign) & !callSign.isEmpty()) {
					vmaAdministrativeViolationImpl.setCallSign(callSign);
				}
				if (Validator.isNotNull(flagStateOfShip)
						& !flagStateOfShip.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setFlagStateOfShip(flagStateOfShip);
				}
				if (Validator.isNotNull(registryNumber)
						& !registryNumber.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setRegistryNumber(registryNumber);
				}
				if (Validator.isNotNull(decisionNo) & !decisionNo.isEmpty()) {
					vmaAdministrativeViolationImpl.setDecisionNo(decisionNo);
				}
				if (Validator.isNotNull(decisionDate) & !decisionDate.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setDecisionDate((Validator.isNotNull(decisionDate) && !decisionDate
									.isEmpty()) ? FormatData.formatDDMMYYYY
									.parse(decisionDate) : null);
				}
				if (Validator.isNotNull(decisionOrganization)
						& !decisionOrganization.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setDecisionOrganization(decisionOrganization);
				}
				if (Validator.isNotNull(officialNo) & !officialNo.isEmpty()) {
					vmaAdministrativeViolationImpl.setOfficialNo(officialNo);
				}
				if (Validator.isNotNull(officialDate) & !officialDate.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setOfficialDate((Validator.isNotNull(officialDate) && !officialDate
									.isEmpty()) ? FormatData.formatDDMMYYYY
									.parse(officialDate) : null);
				}
				if (Validator.isNotNull(officialPlace)
						& !officialPlace.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setOfficialPlace(officialPlace);
				}
				if (Validator.isNotNull(violationPartCode)
						& !violationPartCode.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setViolationPartCode(violationPartCode);
				}
				if (Validator.isNotNull(issueDate) & !issueDate.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setIssueDate((Validator.isNotNull(issueDate) && !issueDate
									.isEmpty()) ? FormatData.formatDDMMYYYY
									.parse(issueDate) : null);
				}
				if (Validator.isNotNull(issueBy) & !issueBy.isEmpty()) {
					vmaAdministrativeViolationImpl.setIssueBy(issueBy);
				}
				if (Validator.isNotNull(violationPartName)
						& !violationPartName.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setViolationPartName(violationPartName);
				}
				if (Validator.isNotNull(violationPartAddress)
						& !violationPartAddress.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setViolationPartAddress(violationPartAddress);
				}
				if (Validator.isNotNull(representative)
						& !representative.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setRepresentative(representative);
				}
				if (Validator.isNotNull(representativeTitle)
						& !representativeTitle.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setRepresentativeTitle(representativeTitle);
				}
				if (Validator.isNotNull(f1AttachedReport)
						& !f1AttachedReport.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setF1AttachedReport(f1AttachedReport);
				}
				if (Validator.isNotNull(f2AttachedReport)
						& !f2AttachedReport.isEmpty()) {
					vmaAdministrativeViolationImpl
							.setF2AttachedReport(f2AttachedReport);
				}

				vmaAdministrativeViolation = VmaAdministrativeViolationLocalServiceUtil
						.addVmaAdministrativeViolation(vmaAdministrativeViolationImpl);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"vma_administrative_violation",
						vmaAdministrativeViolation.getViolationCode(),
						vmaAdministrativeViolation.getNameOfShip());
			} else if (capsua.length() > 0) {
				log.info("Sua VmaAdministrativeViolation");
				long id = ParamUtil.getLong(actionRequest, "id");
				vmaAdministrativeViolation = VmaAdministrativeViolationLocalServiceUtil
						.fetchVmaAdministrativeViolation(id);

				if (Validator.isNotNull(violationBrief)
						& !violationBrief.isEmpty()) {
					vmaAdministrativeViolation
							.setViolationBrief(violationBrief);
				}
				if (Validator.isNotNull(violationDate)
						& !violationDate.isEmpty()) {
					vmaAdministrativeViolation.setViolationDate((Validator
							.isNotNull(violationDate) && !violationDate
							.isEmpty()) ? FormatData.formatDDMMYYYY
							.parse(violationDate) : null);
				}
				if (Validator.isNotNull(portofAuthority)
						& !portofAuthority.isEmpty()) {
					vmaAdministrativeViolation
							.setPortofAuthority(portofAuthority);
				}
				if (Validator.isNotNull(damagedPartName)
						& !damagedPartName.isEmpty()) {
					vmaAdministrativeViolation
							.setDamagedPartName(damagedPartName);
				}
				if (Validator.isNotNull(conclusion) & !conclusion.isEmpty()) {
					vmaAdministrativeViolation.setConclusion(conclusion);
				}
				if (Validator.isNotNull(metaData) & !metaData.isEmpty()) {
					vmaAdministrativeViolation.setMetaData(metaData);
				}
				if (Validator.isNotNull(paymentAmount) & paymentAmount != 0) {
					vmaAdministrativeViolation.setPaymentAmount(String.valueOf(paymentAmount));
				}
				if (Validator.isNotNull(amountInWords)
						& !amountInWords.isEmpty()) {
					vmaAdministrativeViolation.setAmountInWords(amountInWords);
				}
				if (Validator.isNotNull(documentaryNo)
						& !documentaryNo.isEmpty()) {
					vmaAdministrativeViolation.setDocumentaryNo(documentaryNo);
				}
				if (Validator.isNotNull(documentaryDate)
						& !documentaryDate.isEmpty()) {
					vmaAdministrativeViolation.setDocumentaryDate((Validator
							.isNotNull(documentaryDate) && !documentaryDate
							.isEmpty()) ? FormatData.formatDDMMYYYY
							.parse(documentaryDate) : null);
				}
				if (Validator.isNotNull(paymentDate) & !paymentDate.isEmpty()) {
					vmaAdministrativeViolation
							.setPaymentDate((Validator.isNotNull(paymentDate) && !paymentDate
									.isEmpty()) ? FormatData.formatDDMMYYYY
									.parse(paymentDate) : null);
				}
				if (Validator.isNotNull(nameOfShip) & !nameOfShip.isEmpty()) {
					vmaAdministrativeViolation.setNameOfShip(nameOfShip);
				}
				if (Validator.isNotNull(imoNumber) & !imoNumber.isEmpty()) {
					vmaAdministrativeViolation.setImoNumber(imoNumber);
				}
				if (Validator.isNotNull(callSign) & !callSign.isEmpty()) {
					vmaAdministrativeViolation.setCallSign(callSign);
				}
				if (Validator.isNotNull(flagStateOfShip)
						& !flagStateOfShip.isEmpty()) {
					vmaAdministrativeViolation
							.setFlagStateOfShip(flagStateOfShip);
				}
				if (Validator.isNotNull(registryNumber)
						& !registryNumber.isEmpty()) {
					vmaAdministrativeViolation
							.setRegistryNumber(registryNumber);
				}
				if (Validator.isNotNull(decisionNo) & !decisionNo.isEmpty()) {
					vmaAdministrativeViolation.setDecisionNo(decisionNo);
				}
				if (Validator.isNotNull(decisionDate) & !decisionDate.isEmpty()) {
					vmaAdministrativeViolation
							.setDecisionDate((Validator.isNotNull(decisionDate) && !decisionDate
									.isEmpty()) ? FormatData.formatDDMMYYYY
									.parse(decisionDate) : null);
				}
				if (Validator.isNotNull(decisionOrganization)
						& !decisionOrganization.isEmpty()) {
					vmaAdministrativeViolation
							.setDecisionOrganization(decisionOrganization);
				}
				if (Validator.isNotNull(officialNo) & !officialNo.isEmpty()) {
					vmaAdministrativeViolation.setOfficialNo(officialNo);
				}
				if (Validator.isNotNull(officialDate) & !officialDate.isEmpty()) {
					vmaAdministrativeViolation
							.setOfficialDate((Validator.isNotNull(officialDate) && !officialDate
									.isEmpty()) ? FormatData.formatDDMMYYYY
									.parse(officialDate) : null);
				}
				if (Validator.isNotNull(officialPlace)
						& !officialPlace.isEmpty()) {
					vmaAdministrativeViolation.setOfficialPlace(officialPlace);
				}
				if (Validator.isNotNull(violationPartCode)
						& !violationPartCode.isEmpty()) {
					vmaAdministrativeViolation
							.setViolationPartCode(violationPartCode);
				}
				if (Validator.isNotNull(issueDate) & !issueDate.isEmpty()) {
					vmaAdministrativeViolation
							.setIssueDate((Validator.isNotNull(issueDate) && !issueDate
									.isEmpty()) ? FormatData.formatDDMMYYYY
									.parse(issueDate) : null);
				}
				if (Validator.isNotNull(issueBy) & !issueBy.isEmpty()) {
					vmaAdministrativeViolation.setIssueBy(issueBy);
				}
				if (Validator.isNotNull(violationPartName)
						& !violationPartName.isEmpty()) {
					vmaAdministrativeViolation
							.setViolationPartName(violationPartName);
				}
				if (Validator.isNotNull(violationPartAddress)
						& !violationPartAddress.isEmpty()) {
					vmaAdministrativeViolation
							.setViolationPartAddress(violationPartAddress);
				}
				if (Validator.isNotNull(representative)
						& !representative.isEmpty()) {
					vmaAdministrativeViolation
							.setRepresentative(representative);
				}
				if (Validator.isNotNull(representativeTitle)
						& !representativeTitle.isEmpty()) {
					vmaAdministrativeViolation
							.setRepresentativeTitle(representativeTitle);
				}
				if (Validator.isNotNull(f1AttachedReport)
						& !f1AttachedReport.isEmpty()) {
					vmaAdministrativeViolation
							.setF1AttachedReport(f1AttachedReport);
				}
				if (Validator.isNotNull(f2AttachedReport)
						& !f2AttachedReport.isEmpty()) {
					vmaAdministrativeViolation
							.setF2AttachedReport(f2AttachedReport);
				}

				vmaAdministrativeViolation = VmaAdministrativeViolationLocalServiceUtil
						.updateVmaAdministrativeViolation(vmaAdministrativeViolation);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"vma_administrative_violation",
						vmaAdministrativeViolation.getViolationCode(),
						vmaAdministrativeViolation.getNameOfShip());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		if (vmaAdministrativeViolation != null) {
			result.put("id", vmaAdministrativeViolation.getId());
			result.put("portofAuthority",
					vmaAdministrativeViolation.getPortofAuthority());
			result.put("violationCode",
					vmaAdministrativeViolation.getViolationCode());
			result.put("violationBrief",
					vmaAdministrativeViolation.getViolationBrief());
			result.put("damagedPartName",
					vmaAdministrativeViolation.getDamagedPartName());
			result.put("conclusion", vmaAdministrativeViolation.getConclusion());
			result.put("metaData", vmaAdministrativeViolation.getMetaData());
			result.put("paymentAmount",
					vmaAdministrativeViolation.getPaymentAmount());
			result.put("amountInWords",
					vmaAdministrativeViolation.getAmountInWords());
			result.put("documentaryNo",
					vmaAdministrativeViolation.getDocumentaryNo());
			result.put("documentaryDate",
					vmaAdministrativeViolation.getDocumentaryDate());
			result.put("paymentDate",
					vmaAdministrativeViolation.getPaymentDate());
			result.put("nameOfShip", vmaAdministrativeViolation.getNameOfShip());
			result.put("imoNumber", vmaAdministrativeViolation.getImoNumber());
			result.put("callSign", vmaAdministrativeViolation.getCallSign());
			result.put("flagStateOfShip",
					vmaAdministrativeViolation.getFlagStateOfShip());
			result.put("registryNumber",
					vmaAdministrativeViolation.getRegistryNumber());
			result.put("decisionNo", vmaAdministrativeViolation.getDecisionNo());
			result.put("decisionDate",
					vmaAdministrativeViolation.getDecisionDate());
			result.put("decisionOrganiztion",
					vmaAdministrativeViolation.getDecisionOrganization());
			result.put("officialNo", vmaAdministrativeViolation.getOfficialNo());
			result.put("officialDate",
					vmaAdministrativeViolation.getOfficialDate());
			result.put("officialPlace",
					vmaAdministrativeViolation.getOfficialPlace());
			result.put("violationDate",
					vmaAdministrativeViolation.getViolationDate());
			result.put("violationPartCode",
					vmaAdministrativeViolation.getViolationPartCode());
			result.put("issueDate", vmaAdministrativeViolation.getIssueDate());
			result.put("issueBy", vmaAdministrativeViolation.getIssueBy());
			result.put("violationPartName",
					vmaAdministrativeViolation.getViolationPartName());
			result.put("violationPartAddress",
					vmaAdministrativeViolation.getViolationPartAddress());
			result.put("representative",
					vmaAdministrativeViolation.getRepresentative());
			result.put("representativeTitle",
					vmaAdministrativeViolation.getRepresentativeTitle());
			result.put("f1AttachedReport",
					vmaAdministrativeViolation.getF1AttachedReport());
			result.put("f2AttachedReport",
					vmaAdministrativeViolation.getF2AttachedReport());
			result.put("modifiedDate",
					vmaAdministrativeViolation.getModifiedDate());
		}

		return result;
	}

}
