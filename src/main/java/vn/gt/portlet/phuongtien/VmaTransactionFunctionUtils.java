package vn.gt.portlet.phuongtien;

import java.util.Calendar;
import java.util.Date;

import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaTransactionFunction;

import vn.gt.dao.noticeandmessage.service.VmaTransactionFunctionLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaTransactionFunctionUtils
 {

	

	public static JSONObject findByPortofAuthority_transactionTypeCode(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);

		String transactionTypeCode = ParamUtil.getString(resourceRequest,
				"transactionTypeCode", StringPool.BLANK);

		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaTransactionFunction vmaTransactionFunction = VmaTransactionFunctionLocalServiceUtil
					.findByPortofAuthority_transactionTypeCode(portofAuthority,
							transactionTypeCode);
			result = VMAUtils.object2Json(vmaTransactionFunction,
					VmaTransactionFunction.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject doFind(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		int start = GetterUtil.getInteger(request.getParameter("start"), -1);
		int end = GetterUtil.getInteger(request.getParameter("end"), -1);

		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String transactionTypeCode = ParamUtil.getString(resourceRequest,
				"transactionTypeCode", StringPool.BLANK);
		String functionCode = ParamUtil.getString(resourceRequest,
				"functionCode", StringPool.BLANK);
		String functionName = ParamUtil.getString(resourceRequest,
				"functionName", StringPool.BLANK);

		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);

		int forArrival = GetterUtil.getInteger(
				request.getParameter("forArrival"), -1);
		int forDeparture = GetterUtil.getInteger(
				request.getParameter("forDeparture"), -1);
		int chargeType = GetterUtil.getInteger(
				request.getParameter("chargeType"), -1);

		String functionNote = ParamUtil.getString(resourceRequest,
				"functionNote", StringPool.BLANK);

		String transactionNote = ParamUtil.getString(resourceRequest,
				"transactionNote", StringPool.BLANK);

		double chargeRate = ParamUtil.getDouble(resourceRequest, "chargeRate",
				-1);

		double discountRate = ParamUtil.getDouble(resourceRequest,
				"discountRate", -1);

		String chargeConditions = ParamUtil.getString(resourceRequest,
				"chargeConditions", StringPool.BLANK);

		int discountType1 = GetterUtil.getInteger(
				request.getParameter("discountType1"), -1);
		int discountType2 = GetterUtil.getInteger(
				request.getParameter("discountType2"), -1);
		int discountType3 = GetterUtil.getInteger(
				request.getParameter("discountType3"), -1);
		int discountType4 = GetterUtil.getInteger(
				request.getParameter("discountType4"), -1);
		int discountType5 = GetterUtil.getInteger(
				request.getParameter("discountType5"), -1);

		String applied = ParamUtil.getString(resourceRequest, "applied",
				StringPool.BLANK);

		String appliedFrom = ParamUtil.getString(resourceRequest,
				"appliedFrom", StringPool.BLANK);

		String appliedTo = ParamUtil.getString(resourceRequest, "appliedTo",
				StringPool.BLANK);

		try {

			String searchQuery = generateQuery("search", portofAuthority,
					transactionTypeCode, functionCode, functionName,
					makePayment >= 0 ? makePayment : null,
					forArrival >= 0 ? forArrival : null,
					forDeparture >= 0 ? forDeparture : null,
					chargeType >= 0 ? chargeType : null, functionNote,
					transactionNote, chargeRate >= 0 ? chargeRate : null,
					chargeConditions,
					discountType1 >= 0 ? discountType1 : null,
					discountType2 >= 0 ? discountType2 : null,
					discountType3 >= 0 ? discountType3 : null,
					discountType4 >= 0 ? discountType4 : null,
					discountType5 >= 0 ? discountType5 : null, applied,
					appliedFrom, appliedTo, discountRate >= 0 ? discountRate
							: null);

			String countQuery = generateQuery("count", portofAuthority,
					transactionTypeCode, functionCode, functionName,
					makePayment >= 0 ? makePayment : null,
					forArrival >= 0 ? forArrival : null,
					forDeparture >= 0 ? forDeparture : null,
					chargeType >= 0 ? chargeType : null, functionNote,
					transactionNote, chargeRate >= 0 ? chargeRate : null,
					chargeConditions,
					discountType1 >= 0 ? discountType1 : null,
					discountType2 >= 0 ? discountType2 : null,
					discountType3 >= 0 ? discountType3 : null,
					discountType4 >= 0 ? discountType4 : null,
					discountType5 >= 0 ? discountType5 : null, applied,
					appliedFrom, appliedTo, discountRate >= 0 ? discountRate
							: null);

			return VmaTransactionFunctionLocalServiceUtil
					.findVmaTransactionFunction(searchQuery, countQuery, start,
							end);
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

		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String transactionTypeCode = ParamUtil.getString(resourceRequest,
				"transactionTypeCode", StringPool.BLANK);
		String functionCode = ParamUtil.getString(resourceRequest,
				"functionCode", StringPool.BLANK);
		String functionName = ParamUtil.getString(resourceRequest,
				"functionName", StringPool.BLANK);

		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);

		int forArrival = GetterUtil.getInteger(
				request.getParameter("forArrival"), -1);
		int forDeparture = GetterUtil.getInteger(
				request.getParameter("forDeparture"), -1);
		int chargeType = GetterUtil.getInteger(
				request.getParameter("chargeType"), -1);

		String functionNote = ParamUtil.getString(resourceRequest,
				"functionNote", StringPool.BLANK);

		String transactionNote = ParamUtil.getString(resourceRequest,
				"transactionNote", StringPool.BLANK);

		double chargeRate = ParamUtil.getDouble(resourceRequest, "chargeRate",
				-1);

		double discountRate = ParamUtil.getDouble(resourceRequest,
				"discountRate", -1);

		String chargeConditions = ParamUtil.getString(resourceRequest,
				"chargeConditions", StringPool.BLANK);

		int discountType1 = GetterUtil.getInteger(
				request.getParameter("discountType1"), -1);
		int discountType2 = GetterUtil.getInteger(
				request.getParameter("discountType2"), -1);
		int discountType3 = GetterUtil.getInteger(
				request.getParameter("discountType3"), -1);
		int discountType4 = GetterUtil.getInteger(
				request.getParameter("discountType4"), -1);
		int discountType5 = GetterUtil.getInteger(
				request.getParameter("discountType5"), -1);

		String applied = ParamUtil.getString(resourceRequest, "applied",
				StringPool.BLANK);

		String appliedFrom = ParamUtil.getString(resourceRequest,
				"appliedFrom", StringPool.BLANK);

		String appliedTo = ParamUtil.getString(resourceRequest, "appliedTo",
				StringPool.BLANK);

		try {
			String countQuery = generateQuery("count", portofAuthority,
					transactionTypeCode, functionCode, functionName,
					makePayment >= 0 ? makePayment : null,
					forArrival >= 0 ? forArrival : null,
					forDeparture >= 0 ? forDeparture : null,
					chargeType >= 0 ? chargeType : null, functionNote,
					transactionNote, chargeRate >= 0 ? chargeRate : null,
					chargeConditions,
					discountType1 >= 0 ? discountType1 : null,
					discountType2 >= 0 ? discountType2 : null,
					discountType3 >= 0 ? discountType3 : null,
					discountType4 >= 0 ? discountType4 : null,
					discountType5 >= 0 ? discountType5 : null, applied,
					appliedFrom, appliedTo, discountRate >= 0 ? discountRate
							: null);

			return VmaTransactionFunctionLocalServiceUtil
					.countVmaTransactionFunction(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	private static String generateQuery(String cmd, String portofAuthority,
			String transactionTypeCode, String functionCode,
			String functionName, Integer makePayment, Integer forArrival,
			Integer forDeparture, Integer chargeType, String functionNote,
			String transactionNote, Double chargeRate, String chargeConditions,
			Integer discountType1, Integer discountType2,
			Integer discountType3, Integer discountType4,
			Integer discountType5, String applied, String appliedFrom,
			String appliedTo, Double discountRate) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_transaction_function as a";

		} else {
			sql = "SELECT a.* FROM vma_transaction_function AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortofAuthority", "'"
					+ portofAuthority + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(transactionTypeCode)) {
			condition.append(VMAUtils.buildSQLCondition("TransactionTypeCode",
					"'" + transactionTypeCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(functionCode)) {
			condition.append(VMAUtils.buildSQLCondition("FunctionCode", "'"
					+ functionCode + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(functionName)) {
			condition.append(VMAUtils.buildSQLCondition("FunctionName", "'%"
					+ functionName + "%'", "AND", StringPool.LIKE));
		}

		if (makePayment != null) {
			condition.append(VMAUtils.buildSQLCondition("MakePayment", "'%"
					+ makePayment + "%'", "AND", StringPool.LIKE));
		}

		if (forArrival != null) {
			condition.append(VMAUtils.buildSQLCondition("ForArrival", "'%"
					+ forArrival + "%'", "AND", StringPool.LIKE));
		}

		if (forDeparture != null) {
			condition.append(VMAUtils.buildSQLCondition("ForDeparture", "'%"
					+ forDeparture + "%'", "AND", StringPool.LIKE));
		}

		if (chargeType != null) {
			condition.append(VMAUtils.buildSQLCondition("ChargeType", "'%"
					+ chargeType + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(functionNote)) {
			condition.append(VMAUtils.buildSQLCondition("functionNote", "'%"
					+ functionNote + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(transactionNote)) {
			condition.append(VMAUtils.buildSQLCondition("TransactionNote", "'%"
					+ transactionNote + "%'", "AND", StringPool.LIKE));
		}

		if (chargeRate != null) {
			condition.append(VMAUtils.buildSQLCondition("ChargeRate", "'%"
					+ chargeRate + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(chargeConditions)) {
			condition.append(VMAUtils.buildSQLCondition("ChargeConditions",
					"'%" + chargeConditions + "%'", "AND", StringPool.LIKE));
		}

		if (discountType1 != null) {
			condition.append(VMAUtils.buildSQLCondition("DiscountType1", "'%"
					+ discountType1 + "%'", "AND", StringPool.LIKE));
		}

		if (discountType2 != null) {
			condition.append(VMAUtils.buildSQLCondition("DiscountType2", "'%"
					+ discountType2 + "%'", "AND", StringPool.LIKE));
		}

		if (discountType3 != null) {
			condition.append(VMAUtils.buildSQLCondition("DiscountType3", "'%"
					+ discountType3 + "%'", "AND", StringPool.LIKE));
		}

		if (discountType4 != null) {
			condition.append(VMAUtils.buildSQLCondition("DiscountType4", "'%"
					+ discountType4 + "%'", "AND", StringPool.LIKE));
		}

		if (discountType5 != null) {
			condition.append(VMAUtils.buildSQLCondition("DiscountType5", "'%"
					+ discountType5 + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(applied)) {
			condition.append(VMAUtils.buildSQLCondition("applied", "'"
					+ applied + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(appliedFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(appliedFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("appliedFrom", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(appliedTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(appliedTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("appliedTo", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		return sql + condition.toString();
	}
}
