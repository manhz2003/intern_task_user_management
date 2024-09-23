package vn.gt.portlet.baocao.baocaohotronghiepvu;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotCompanyLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotLocalServiceUtil;
import vn.gt.utils.FormatData;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;
@Slf4j
public class BC27TExport {

	

	public static JSONObject getModel(String maritimeCode,
			String pilotCompanyCode, String fromDate, String toDate,
			String createDate, String reportPeriod, String reportMadeBy) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray array = JSONFactoryUtil.createJSONArray();
		try {
			array = VmaSchedulePilotLocalServiceUtil.getModelMau27T(
					maritimeCode, pilotCompanyCode, fromDate, toDate);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		result.put("reportDetails", array);
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
		String pilotCompanyName = StringPool.BLANK;
		try {
			pilotCompanyName = DmVmaPilotCompanyLocalServiceUtil
					.fetchByPilotCompanyCode(pilotCompanyCode)
					.getPilotCompanyName();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		result.put("pilotCompanyName", pilotCompanyName);

		return result;
	}
}
