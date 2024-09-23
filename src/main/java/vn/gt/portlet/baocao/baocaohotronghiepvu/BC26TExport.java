package vn.gt.portlet.baocao.baocaohotronghiepvu;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;
@Slf4j
public class BC26TExport {

	

	public static JSONObject getModel(String maritimeCode, String pilotCode,
			String fromDate, String toDate, String createDate,
			String reportPeriod, String reportMadeBy) throws SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		result = DmVmaPilotLocalServiceUtil.getModelMau26_1T(maritimeCode,
				pilotCode);

		JSONArray array = JSONFactoryUtil.createJSONArray();
		try {
			array = VmaSchedulePilotLocalServiceUtil.getModelMau26_2T(
					maritimeCode, pilotCode, fromDate, toDate);
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

		return result;
	}
}