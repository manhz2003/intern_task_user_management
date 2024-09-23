package vn.gt.portlet.phuongtien;

import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import vn.gt.dao.noticeandmessage.service.VmaTransactionTypeLocalServiceUtil;

import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaTransactionTypeUtils
 {

	

	public static JSONObject doFind(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);

		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);
		String transactionTypeCode = ParamUtil.getString(resourceRequest,
				"transactionTypeCode", StringPool.BLANK);
		String transactionTypeName = ParamUtil.getString(resourceRequest,
				"transactionTypeName", StringPool.BLANK);
		int transactionLevel = GetterUtil.getInteger(
				request.getParameter("transactionLevel"), -1);
		String transactionTypeShortName = ParamUtil.getString(resourceRequest,
				"transactionTypeShortName", StringPool.BLANK);

		try {

			String searchQuery = generateQuery("search", portofAuthority,
					transactionTypeCode, transactionTypeName,
					transactionLevel >= 0 ? transactionLevel : null,
					transactionTypeShortName);

			String countQuery = generateQuery("count", portofAuthority,
					transactionTypeCode, transactionTypeName,
					transactionLevel >= 0 ? transactionLevel : null,
					transactionTypeShortName);

			return VmaTransactionTypeLocalServiceUtil.findVmaTransactionType(
					searchQuery, countQuery, start, end);
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
		String transactionTypeName = ParamUtil.getString(resourceRequest,
				"transactionTypeName", StringPool.BLANK);
		int transactionLevel = GetterUtil.getInteger(
				request.getParameter("transactionLevel"), -1);
		String transactionTypeShortName = ParamUtil.getString(resourceRequest,
				"transactionTypeShortName", StringPool.BLANK);

		try {
			String countQuery = generateQuery("count", portofAuthority,
					transactionTypeCode, transactionTypeName,
					transactionLevel >= 0 ? transactionLevel : null,
					transactionTypeShortName);

			return VmaTransactionTypeLocalServiceUtil
					.countVmaTransactionType(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	private static String generateQuery(String cmd, String portofAuthority,
			String transactionTypeCode, String transactionTypeName,
			Integer transactionLevel, String transactionTypeShortName) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_transaction_type as a";

		} else {
			sql = "SELECT a.* FROM vma_transaction_type AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(transactionTypeName)) {
			condition.append(VMAUtils.buildSQLCondition("TransactionTypeName",
					"'%" + transactionTypeName + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(transactionTypeShortName)) {
			condition.append(VMAUtils.buildSQLCondition(
					"TransactionTypeShortName", "'%" + transactionTypeShortName
							+ "%'", "AND", StringPool.LIKE));
		}

		if (transactionLevel != null) {
			condition.append(VMAUtils.buildSQLCondition("TransactionLevel", "'"
					+ transactionLevel + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortofAuthority", "'"
					+ portofAuthority + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(transactionTypeCode)) {
			condition.append(VMAUtils.buildSQLCondition("TransactionTypeCode",
					"'" + transactionTypeCode + "'", "AND", StringPool.EQUAL));
		}

		return sql + condition.toString();
	}
}
