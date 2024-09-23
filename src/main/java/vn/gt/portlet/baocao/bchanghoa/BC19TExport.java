package vn.gt.portlet.baocao.bchanghoa;

import java.text.ParseException;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;

public class BC19TExport {
	

	public static JSONObject getModel(String maritimeCode, String fromDate,
			String toDate, String createDate, String reportPeriod,
			String reportMadeBy) throws SystemException, ParseException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		result = VmaItineraryLocalServiceUtil.getModelMau19T(maritimeCode,
				fromDate, toDate);

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