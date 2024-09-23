package vn.gt.portlet.phuongtien;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.DmShipAgency;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaPaymentAdvance;
import com.fds.nsw.nghiepvu.model.VmaTransactionBalance;
import com.fds.nsw.nghiepvu.model.VmaTransactionSlip;


import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaPaymentAdvanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionBalanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipLocalServiceUtil;

import com.fds.nsw.kernel.exception.SystemException;
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
public class VmaPaymentAdvanceUtils
 {
	

	public static VmaPaymentAdvance getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		VmaPaymentAdvance vmaPaymentAdvance = null;
		long id = GetterUtil.getLong(request.getParameter("id"), -1);
		if (id > 0) {
			try {
				vmaPaymentAdvance = VmaPaymentAdvanceLocalServiceUtil
						.getVmaPaymentAdvance(id);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} else {
			vmaPaymentAdvance = new VmaPaymentAdvance();
		}
		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaPaymentAdvance.setPortofAuthority(portofAuthority);
		}
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaPaymentAdvance.setShipAgencyCode(shipAgencyCode);
		}
		String shipAgencyName = ParamUtil.getString(actionRequest,
				"shipAgencyName", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyName)) {
			vmaPaymentAdvance.setShipAgencyName(shipAgencyName);
		}
		String shipCaptain = ParamUtil.getString(actionRequest, "shipCaptain",
				StringPool.BLANK);
		if (Validator.isNotNull(shipCaptain)) {
			vmaPaymentAdvance.setShipCaptain(shipCaptain);
		}
		String shipOwnerName = ParamUtil.getString(actionRequest,
				"shipOwnerName", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerName)) {
			vmaPaymentAdvance.setShipOwnerName(shipOwnerName);
		}
		String nameOfShip = ParamUtil.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaPaymentAdvance.setNameOfShip(nameOfShip);
		}
		double amount = GetterUtil
				.getDouble(request.getParameter("amount"), -1);
		if (amount >= 0) {
			vmaPaymentAdvance.setAmount(amount);
		}
		int AmtRate = GetterUtil
				.getInteger(request.getParameter("AmtRate"), -1);
		vmaPaymentAdvance.setAmtRate(AmtRate);
		
		int paymentType = GetterUtil.getInteger(
				request.getParameter("paymentType"), -1);
		if (paymentType >= 0) {
			vmaPaymentAdvance.setPaymentType(paymentType);
		}
		String transactionTypeCode = ParamUtil.getString(actionRequest,
				"transactionTypeCode", StringPool.BLANK);
		if (Validator.isNotNull(transactionTypeCode)) {
			vmaPaymentAdvance.setTransactionTypeCode(transactionTypeCode);
		}

		return vmaPaymentAdvance;
	}

	public static JSONObject sotienconphaitra(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);

		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);

		String transactionTypeCode = ParamUtil.getString(resourceRequest,
				"transactionTypeCode", StringPool.BLANK);

		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);

		VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
				.fetchByitineraryNo_documentaryCode(itineraryNo,
						documentaryCode);

		VmaTransactionBalance vmaTransactionBalance = VmaTransactionBalanceLocalServiceUtil
				.findByportofAuthority_shipAgencyCode_transactionTypeCode(
						portofAuthority, shipAgencyCode, transactionTypeCode);

		if (vmaTransactionBalance == null || vmaTransactionBalance == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
		double paymentAmount = vmaTransactionSlip.getPaymentAmount();
		double paidAdvanceAmount = vmaTransactionSlip.getPaidAdvanceAmount();
		double remainingAmount = paymentAmount - paidAdvanceAmount;
		if (remainingAmount < 0) {
			paidAdvanceAmount = 0;
		}

		result.put("sttlmtAmount", sttlmtAmount);
		result.put("paymentAmount", paymentAmount);
		result.put("paidAdvanceAmount", paidAdvanceAmount);
		result.put("remainingAmount", remainingAmount);

		return result;
	}

	public static JSONObject napquy_dunodauky(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaPaymentAdvance vmaPaymentAdvance = getObjectFromRequest(
				themeDisplay, actionRequest);

		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);

		String transactionTypeCode = ParamUtil.getString(actionRequest,
				"transactionTypeCode", StringPool.BLANK);

		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);

		VmaTransactionBalance vmaTransactionBalance = VmaTransactionBalanceLocalServiceUtil
				.findByportofAuthority_shipAgencyCode_transactionTypeCode(
						portofAuthority, shipAgencyCode, transactionTypeCode);

		if (vmaTransactionBalance == null) {
			try {
				DmShipAgency dmShipAgency = DmShipAgencyLocalServiceUtil
						.getByShipAgencyCode(shipAgencyCode);
				VmaTransactionBalanceLocalServiceUtil
						.autoInitVmaTransactionBalance(
								portofAuthority,
								shipAgencyCode,
								dmShipAgency != null ? dmShipAgency
										.getShipAgencyNameVN()
										: StringPool.BLANK);

				vmaTransactionBalance = VmaTransactionBalanceLocalServiceUtil
						.findByportofAuthority_shipAgencyCode_transactionTypeCode(
								portofAuthority, shipAgencyCode,
								transactionTypeCode);
			} catch (Exception e) {
				log.error(e.getMessage());
				return VMAUtils.createResponseMessage(LanguageUtil.get(
						themeDisplay.getLocale(), "system_error"),
						"system_error", StringPool.BLANK);
			}
		}

		if (vmaTransactionBalance == null || vmaPaymentAdvance == null) {

			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {
			vmaPaymentAdvance = VmaPaymentAdvanceLocalServiceUtil
					.addVmaPaymentAdvance_updateVmaTransactionBalance(
							vmaPaymentAdvance, vmaTransactionBalance);
			result = VMAUtils.object2Json(vmaPaymentAdvance,
					VmaPaymentAdvance.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject rutquy(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaPaymentAdvance vmaPaymentAdvance = getObjectFromRequest(
				themeDisplay, actionRequest);

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(actionRequest,
				"documentaryCode", StringPool.BLANK);

		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);

		String transactionTypeCode = ParamUtil.getString(actionRequest,
				"transactionTypeCode", StringPool.BLANK);

		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);

		String departmentCode = ParamUtil.getString(actionRequest,
				"departmentCode", StringPool.BLANK);

		VmaTransactionBalance vmaTransactionBalance = VmaTransactionBalanceLocalServiceUtil
				.findByportofAuthority_shipAgencyCode_transactionTypeCode(
						portofAuthority, shipAgencyCode, transactionTypeCode);

		if (vmaTransactionBalance == null || vmaPaymentAdvance == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {
			boolean flag = VmaPaymentAdvanceLocalServiceUtil.rutquy(
					vmaPaymentAdvance, itineraryNo, documentaryCode,
					portofAuthority, transactionTypeCode, shipAgencyCode,
					departmentCode);
			return result.put("message", flag);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}

	}

	public static JSONObject huytinhphi(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		/*VmaPaymentAdvance vmaPaymentAdvance = getObjectFromRequest(
				themeDisplay, actionRequest);*/

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(actionRequest,
				"documentaryCode", StringPool.BLANK);

		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);

		String transactionTypeCode = ParamUtil.getString(actionRequest,
				"transactionTypeCode", StringPool.BLANK);

		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);

		String departmentCode = ParamUtil.getString(actionRequest,
				"departmentCode", StringPool.BLANK);

		VmaTransactionBalance vmaTransactionBalance = VmaTransactionBalanceLocalServiceUtil
				.findByportofAuthority_shipAgencyCode_transactionTypeCode(
						portofAuthority, shipAgencyCode, transactionTypeCode);

		if (vmaTransactionBalance == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {
			boolean flag = VmaPaymentAdvanceLocalServiceUtil.huytinhphi(
					itineraryNo, documentaryCode, portofAuthority,
					transactionTypeCode, shipAgencyCode, departmentCode);
			return result.put("message", flag);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}

	}

	public static JSONObject noptien(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaPaymentAdvance vmaPaymentAdvance = getObjectFromRequest(
				themeDisplay, actionRequest);

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(actionRequest,
				"documentaryCode", StringPool.BLANK);

		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);

		String transactionTypeCode = ParamUtil.getString(actionRequest,
				"transactionTypeCode", StringPool.BLANK);

		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);

		String departmentCode = ParamUtil.getString(actionRequest,
				"departmentCode", StringPool.BLANK);

		VmaTransactionBalance vmaTransactionBalance = VmaTransactionBalanceLocalServiceUtil
				.findByportofAuthority_shipAgencyCode_transactionTypeCode(
						portofAuthority, shipAgencyCode, transactionTypeCode);

		if (vmaTransactionBalance == null || vmaPaymentAdvance == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {
			boolean flag = VmaPaymentAdvanceLocalServiceUtil.noptien(
					vmaPaymentAdvance, itineraryNo, documentaryCode,
					portofAuthority, transactionTypeCode, shipAgencyCode,
					departmentCode);
			return result.put("message", flag);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}

	}

	public static JSONObject xacnhanthanhtoan(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaPaymentAdvance vmaPaymentAdvance = getObjectFromRequest(
				themeDisplay, actionRequest);

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(actionRequest,
				"documentaryCode", StringPool.BLANK);

		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);

		String transactionTypeCode = ParamUtil.getString(actionRequest,
				"transactionTypeCode", StringPool.BLANK);

		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);

		String departmentCode = ParamUtil.getString(actionRequest,
				"departmentCode", StringPool.BLANK);

		VmaTransactionBalance vmaTransactionBalance = VmaTransactionBalanceLocalServiceUtil
				.findByportofAuthority_shipAgencyCode_transactionTypeCode(
						portofAuthority, shipAgencyCode, transactionTypeCode);

		if (vmaTransactionBalance == null || vmaPaymentAdvance == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {
			boolean flag = VmaPaymentAdvanceLocalServiceUtil.xacnhanthanhtoan(
					vmaPaymentAdvance, itineraryNo, documentaryCode,
					portofAuthority, transactionTypeCode, shipAgencyCode,
					departmentCode);
			return result.put("message", flag);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}

	}

	public static JSONObject xacnhantinhphi(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(actionRequest,
				"documentaryCode", StringPool.BLANK);

		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);

		String transactionTypeCode = ParamUtil.getString(actionRequest,
				"transactionTypeCode", StringPool.BLANK);

		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);

		String departmentCode = ParamUtil.getString(actionRequest,
				"departmentCode", StringPool.BLANK);

		VmaTransactionBalance vmaTransactionBalance = VmaTransactionBalanceLocalServiceUtil
				.findByportofAuthority_shipAgencyCode_transactionTypeCode(
						portofAuthority, shipAgencyCode, transactionTypeCode);

		if (vmaTransactionBalance == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {
			boolean flag = VmaPaymentAdvanceLocalServiceUtil.xacnhantinhphi(
					itineraryNo, documentaryCode, portofAuthority,
					transactionTypeCode, shipAgencyCode, departmentCode);
			return result.put("message", flag);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}

	}
}
