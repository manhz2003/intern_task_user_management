package vn.gt.portlet.baocao.bcpttnd;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.flex.common.utility.string.StringPool;

public class BC77TExport {

	public static JSONObject getModel(String maritimeCode, String nameOfShip,
			String imoNumber, String registryNumber, String vrCode,
			String flagStateOfShip, String from_gt, String to_gt,
			String from_dwt, String to_dwt, String from_loa, String to_loa,
			String lastPortCode, String nextPortCode, String arrivalShipAgency,
			String departureShipAgency, String cargoType, String cargoCategory,
			String callSign, String anchoringPortHarbourCode,
			String anchoringPortWharfCode, String shiftingPortHarbourCode,
			String shiftingPortWharfCode, String fromDate, String toDate,
			String createDate, String reportPeriod, String reportMadeBy)
			throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray array = DmStateLocalServiceUtil.getModelMau77T(maritimeCode,
				nameOfShip, imoNumber, registryNumber, vrCode, flagStateOfShip,
				from_gt, to_gt, from_dwt, to_dwt, from_loa, to_loa,
				lastPortCode, nextPortCode, arrivalShipAgency,
				departureShipAgency, cargoType, cargoCategory, callSign,
				anchoringPortHarbourCode, anchoringPortWharfCode,
				shiftingPortHarbourCode, shiftingPortWharfCode, fromDate,
				toDate);

		result.put("Bang1", array);

		result.put("reportMadeBy", reportMadeBy);
		result.put("reportPeriod", reportPeriod);
		try {
			result.put("fromDate", FormatData
					.parseDateToTringDDMMYYY(FormatData.formatDateShort
							.parse(fromDate)));
			result.put("toDate", FormatData
					.parseDateToTringDDMMYYY(FormatData.formatDateShort
							.parse(toDate)));
		} catch (Exception e) {
			// nothing to do
		}
		result.put("reportMonth", fromDate.split(" ")[0].split("-")[1]);
		result.put("reportYear", fromDate.split(" ")[0].split("-")[0]);
		result.put("maritimeNameVN", DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode).getMaritimeNameVN());
		result.put("signPlace",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode)
						.getCityCode());
		result.put("signDate", createDate.replace(" 00:00", StringPool.BLANK));

		return result;
	}
}
