package vn.gt.portlet.phuongtien;

import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import vn.gt.dao.noticeandmessage.service.VmaConversionTypeLocalServiceUtil;

import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaConversionTypeUtils
 {

	

	public static JSONObject doFind(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);

		String functionType = ParamUtil.getString(resourceRequest,
				"functionType", StringPool.BLANK);
		int conversionSequence = GetterUtil.getInteger(
				request.getParameter("conversionSequence"), -1);
		String functionName = ParamUtil.getString(resourceRequest,
				"functionName", StringPool.BLANK);
		String conversionUnit = ParamUtil.getString(resourceRequest,
				"conversionUnit", StringPool.BLANK);

		try {

			String searchQuery = generateQuery("search", functionType,
					conversionSequence >= 0 ? conversionSequence : null,
					functionName, conversionUnit);

			String countQuery = generateQuery("count", functionType,
					conversionSequence >= 0 ? conversionSequence : null,
					functionName, conversionUnit);

			return VmaConversionTypeLocalServiceUtil.findVmaConversionType(
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

		String functionType = ParamUtil.getString(resourceRequest,
				"functionType", StringPool.BLANK);
		int conversionSequence = GetterUtil.getInteger(
				request.getParameter("conversionSequence"), -1);
		String functionName = ParamUtil.getString(resourceRequest,
				"functionName", StringPool.BLANK);
		String conversionUnit = ParamUtil.getString(resourceRequest,
				"conversionUnit", StringPool.BLANK);

		try {
			String countQuery = generateQuery("count", functionType,
					conversionSequence >= 0 ? conversionSequence : null,
					functionName, conversionUnit);

			return VmaConversionTypeLocalServiceUtil
					.countVmaConversionType(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	private static String generateQuery(String cmd, String functionType,
			Integer conversionSequence, String functionName,
			String conversionUnit) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_conversion_type as a";

		} else {
			sql = "SELECT a.* FROM vma_conversion_type AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(functionName)) {
			condition.append(VMAUtils.buildSQLCondition("FunctionName", "'%"
					+ functionName + "%'", "AND", StringPool.LIKE));
		}

		if (conversionSequence != null) {
			condition.append(VMAUtils.buildSQLCondition("ConversionSequence",
					"'" + conversionSequence + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(functionType)) {
			condition.append(VMAUtils.buildSQLCondition("FunctionType", "'"
					+ functionType + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(conversionUnit)) {
			condition.append(VMAUtils.buildSQLCondition("ConversionUnit", "'"
					+ conversionUnit + "'", "AND", StringPool.EQUAL));
		}

		return sql + condition.toString();
	}
}
