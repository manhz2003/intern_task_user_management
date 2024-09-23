package vn.gt.portlet.baocao.bcpttnd;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.flex.common.utility.string.StringPool;

public class BC49_50_51_55TExport {

	public static JSONObject getModel(String maritimeCode, String nameOfShip,
			String imoNumber, String registryNumber, String vrCode,
			String flagStateOfShip, String from_gt, String to_gt,
			String from_dwt, String to_dwt, String from_loa, String to_loa,
			String lastPortCode, String nextPortCode, String arrivalShipAgency,
			String departureShipAgency, String cargoType, String cargoCategory,
			String callSign, String startDate, String endDate,
			String createDate, String reportPeriod, String reportMadeBy)
			throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray array = VmaItineraryLocalServiceUtil.getModelMau49_50_51T(
				maritimeCode, nameOfShip, imoNumber, registryNumber, vrCode,
				flagStateOfShip, from_gt, to_gt, from_dwt, to_dwt, from_loa,
				to_loa, lastPortCode, nextPortCode, arrivalShipAgency,
				departureShipAgency, cargoType, cargoCategory, callSign,
				startDate, endDate);

		result.put("Bang1", array);

		result.put("reportMadeBy", reportMadeBy);
		result.put("reportPeriod", reportPeriod);
		try {
			result.put("fromDate", FormatData
					.parseDateToTringDDMMYYY(FormatData.formatDateShort
							.parse(startDate)));
			result.put("toDate", FormatData
					.parseDateToTringDDMMYYY(FormatData.formatDateShort
							.parse(endDate)));
		} catch (Exception e) {
			// nothing to do
		}
		result.put("reportMonth", startDate.split(" ")[0].split("-")[1]);
		result.put("reportYear", startDate.split(" ")[0].split("-")[0]);
		result.put("maritimeNameVN", DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode).getMaritimeNameVN());
		result.put("signPlace",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode)
						.getCityCode());
		result.put("signDate", createDate.replace(" 00:00", StringPool.BLANK));

		return result;
	}
}
