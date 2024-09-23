package vn.gt.portlet.phuongtien;

import java.util.List;

import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaScheduleTransfer;
import com.fds.nsw.nghiepvu.model.VmaShip;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTransferLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaShipLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionConversionLocalServiceUtil;

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
public class VmaTransactionConversionUtils
 {

	

	public static JSONObject getConversionData(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);
		HttpServletRequest request = resourceRequest;
		
		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);

		VmaItinerary vmaItinerary = null;

		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(itineraryNo);

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "not_found_vma_itinerary"),
					"not_found_vma_itinerary", StringPool.BLANK);
		}

		VmaShip vmaShip = null;

		String imoNumber = vmaItinerary.getImoNumber();

		String callSign = vmaItinerary.getCallSign();
		
		String registryNumber = vmaItinerary.getRegistryNumber();
		
		//Khi luot vao luot roi la 2 tau khac nhau
		if(noticeShipType == 2){
			List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
			VmaScheduleTransfer vmaScheduleTransfer = null;
			if(vmaScheduleTransfers != null && !vmaScheduleTransfers.isEmpty()){
				vmaScheduleTransfer = vmaScheduleTransfers.get(0);
			}
			if(vmaScheduleTransfer != null){
				imoNumber = vmaScheduleTransfer.getImoNumber();
				callSign = vmaScheduleTransfer.getCallSign();
				registryNumber = vmaScheduleTransfer.getRegistryNumber();
			}
		}
		//========================================
		try {
			if (imoNumber.trim().length() >= 7) {

				vmaShip = VmaShipLocalServiceUtil.fetchByIMONumber_CallSign(
						imoNumber, callSign);
			} else {

				vmaShip = VmaShipLocalServiceUtil.fetchByCallSign(callSign);
				// Tim lai theo registryNumber
				if (!vmaShip.getShipName().contains(vmaItinerary.getNameOfShip())) {
					vmaShip = VmaShipLocalServiceUtil.fetchByRegistryNumber(registryNumber);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());

			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "not_found_vma_ship"),
					"not_found_vma_ship", StringPool.BLANK);
		}

		return VmaTransactionConversionLocalServiceUtil
				.findTransactionConversionData(vmaShip);
	}

	public static JSONObject doFind(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);

		String shipTypeMT = ParamUtil.getString(resourceRequest, "shipTypeMT",
				StringPool.BLANK);
		String shipTypeCode = ParamUtil.getString(resourceRequest,
				"shipTypeCode", StringPool.BLANK);
		String functionType = ParamUtil.getString(resourceRequest,
				"functionType", StringPool.BLANK);
		double conversionRate = GetterUtil.getDouble(
				request.getParameter("conversionRate"), -1);
		String functionName = ParamUtil.getString(resourceRequest,
				"functionName", StringPool.BLANK);
		String conversionUnit = ParamUtil.getString(resourceRequest,
				"conversionUnit", StringPool.BLANK);

		try {

			String searchQuery = generateQuery("search", shipTypeMT,
					shipTypeCode, functionType,
					conversionRate >= 0 ? conversionRate : null, functionName,
					conversionUnit);

			String countQuery = generateQuery("count", shipTypeMT,
					shipTypeCode, functionType,
					conversionRate >= 0 ? conversionRate : null, functionName,
					conversionUnit);

			return VmaTransactionConversionLocalServiceUtil
					.findVmaTransactionConversion(searchQuery, countQuery,
							start, end);
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

		String shipTypeMT = ParamUtil.getString(resourceRequest, "shipTypeMT",
				StringPool.BLANK);
		String shipTypeCode = ParamUtil.getString(resourceRequest,
				"shipTypeCode", StringPool.BLANK);
		String functionType = ParamUtil.getString(resourceRequest,
				"functionType", StringPool.BLANK);
		double conversionRate = GetterUtil.getDouble(
				request.getParameter("conversionRate"), -1);
		String functionName = ParamUtil.getString(resourceRequest,
				"functionName", StringPool.BLANK);
		String conversionUnit = ParamUtil.getString(resourceRequest,
				"conversionUnit", StringPool.BLANK);

		try {
			String countQuery = generateQuery("count", shipTypeMT,
					shipTypeCode, functionType,
					conversionRate >= 0 ? conversionRate : null, functionName,
					conversionUnit);

			return VmaTransactionConversionLocalServiceUtil
					.countVmaTransactionConversion(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	private static String generateQuery(String cmd, String shipTypeMT,
			String shipTypeCode, String functionType, Double conversionRate,
			String functionName, String conversionUnit) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_transaction_conversion as a";

		} else {
			sql = "SELECT a.* FROM vma_transaction_conversion AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(functionName)) {
			condition.append(VMAUtils.buildSQLCondition("FunctionName", "'%"
					+ functionName + "%'", "AND", StringPool.LIKE));
		}

		if (conversionRate != null) {
			condition.append(VMAUtils.buildSQLCondition("ConversionRate", "'"
					+ conversionRate + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shipTypeMT)) {
			condition.append(VMAUtils.buildSQLCondition("ShipTypeMT", "'"
					+ shipTypeMT + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(shipTypeCode)) {
			condition.append(VMAUtils.buildSQLCondition("ShipTypeCode", "'"
					+ shipTypeCode + "'", "AND", StringPool.EQUAL));
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
