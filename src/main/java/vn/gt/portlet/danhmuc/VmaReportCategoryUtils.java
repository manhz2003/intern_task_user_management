package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.VmaReportCategory;
import vn.gt.dao.danhmuc.service.VmaReportCategoryLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaReportCategoryUtils {

	public static JSONObject getVmaReportCategory(String rptGroup,
			String rptLevel, int start, int end) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		List<VmaReportCategory> vmaReportCategories = VmaReportCategoryLocalServiceUtil
				.getReportGroups(rptGroup, rptLevel, start, end);
		for (VmaReportCategory vmaReportCategory : vmaReportCategories) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("rptCode", vmaReportCategory.getRptCode());
			jsonObject.put("rptName", vmaReportCategory.getRptName());
			jsonObject.put("rptDescription",
					vmaReportCategory.getRptDescription());
			jsonObject.put("rptGroup", vmaReportCategory.getRptGroup());
			jsonObject.put("rptLevel", vmaReportCategory.getRptLevel());
			jsonObject.put("sequenceNo", vmaReportCategory.getSequenceNo());
			jsonObject.put("maritimeCodeSelect",
					vmaReportCategory.getMaritimeCodeSelect());
			jsonObject.put("portCodeSelect",
					vmaReportCategory.getPortCodeSelect());
			jsonObject.put("portRegionCodeSelect",
					vmaReportCategory.getPortRegionCodeSelect());
			jsonObject.put("portHarbourCodeSelect",
					vmaReportCategory.getPortHarbourCodeSelect());
			jsonObject.put("portWharfCodeSelect",
					vmaReportCategory.getPortWharfCodeSelect());
			jsonObject.put("channelCodeSelect",
					vmaReportCategory.getChannelCodeSelect());
			jsonObject.put("departmentCodeSelect",
					vmaReportCategory.getDepartmentCodeSelect());
			jsonObject.put("shipTypeCodeSelect",
					vmaReportCategory.getShipTypeCodeSelect());
			jsonObject.put("flagStateCodeSelect",
					vmaReportCategory.getFlagStateCodeSelect());
			jsonObject.put("grossTonnageSelect",
					vmaReportCategory.getGrossTonnageSelect());
			jsonObject.put("deadWeightSelect",
					vmaReportCategory.getDeadWeightSelect());
			jsonObject.put("cargoSelect", vmaReportCategory.getCargoSelect());
			jsonObject.put("cargoCategorySelect",
					vmaReportCategory.getCargoCategorySelect());
			jsonObject.put("cargoUploadingSelect",
					vmaReportCategory.getCargoUnloadingSelect());
			jsonObject.put("cargoGroupSelect",
					vmaReportCategory.getCargoGroupSelect());
			jsonObject.put("shipAgencyCodeSelect",
					vmaReportCategory.getShipAgencyCodeSelect());
			jsonObject.put("shipOwnerCodeSelect",
					vmaReportCategory.getShipOwnerCodeSelect());
			jsonObject.put("pilotCompanyCodeSelect",
					vmaReportCategory.getPilotCompanyCodeSelect());
			jsonObject.put("pilotCodeSelect",
					vmaReportCategory.getPilotCodeSelect());
			jsonObject.put("tugboatCompanyCodeSelect",
					vmaReportCategory.getTugboatCompanyCodeSelect());
			jsonObject.put("tugboatCodeSelect",
					vmaReportCategory.getTugboatCodeSelect());
			jsonObject.put("shipCodeSelect",
					vmaReportCategory.getShipCodeSelect());
			jsonObject.put("shipYardCodeSelect",
					vmaReportCategory.getShipYardCodeSelect());
			jsonObject.put("securityOfficeCodeSelect",
					vmaReportCategory.getSecurityOfficeCodeSelect());
			jsonObject.put("modifiedDate", vmaReportCategory.getModifiedDate());
			array.put(jsonObject);
		}
		result.put("total", VmaReportCategoryLocalServiceUtil
				.countReportGroups(rptGroup, rptLevel));
		result.put("data", array);
		return result;
	}
}
