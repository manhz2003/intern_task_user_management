package vn.gt.portlet.baocao.baocaohotronghiepvu;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaTugboatCompanyLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatLocalServiceUtil;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.utils.FormatData;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;
@Slf4j
public class BC25TExport {

	

	public static JSONObject getModel(String maritimeCode,
			String tugboatCompanyCode, String fromDate, String toDate,
			String createDate, String reportPeriod, String reportMadeBy) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray array = JSONFactoryUtil.createJSONArray();
		try {
			array = VmaScheduleTugboatLocalServiceUtil.getModelMau25T(
					maritimeCode, tugboatCompanyCode, fromDate, toDate);
		} catch (Exception e) {

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
		String tugboatCompanyName = StringPool.BLANK;
		try {
			tugboatCompanyName = DmVmaTugboatCompanyLocalServiceUtil
					.fetchByTugboatCompanyCode(tugboatCompanyCode)
					.getTugboatCompanyName();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		result.put("tugboatCompanyName",
				DanhMucUtils.encodeUTF8(tugboatCompanyName));

		return result;
	}
}
