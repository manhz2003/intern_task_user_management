package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory;
import com.fds.nsw.nghiepvu.model.DmVmaPilotCategory;
import vn.gt.dao.danhmuc.service.DmHistoryPilotCategoryLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotCategoryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.PageType;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;




import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaPilotCategoryUtils {

	

	public static JSONObject getVmaPilotCategorys(String pilotCategoryName,
			String isDelete, String pilotCategoryCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmVmaPilotCategory> dmVmaPilotCategories = DmVmaPilotCategoryLocalServiceUtil
				.findPilotCategoryies(pilotCategoryName, isDelete,
						pilotCategoryCodeGroup, start, end);
		total = DmVmaPilotCategoryLocalServiceUtil.countPilotCategoryies(
				pilotCategoryName, isDelete, pilotCategoryCodeGroup);

		for (DmVmaPilotCategory dmVmaPilotCategorie : dmVmaPilotCategories) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmVmaPilotCategorie.getId());
			obj.put("pilotCategoryCode",
					dmVmaPilotCategorie.getPilotCategoryCode());
			obj.put("pilotCategoryName",
					dmVmaPilotCategorie.getPilotCategoryName());
			obj.put("maxLength", dmVmaPilotCategorie.getMaxLength());
			obj.put("grossTonage", dmVmaPilotCategorie.getGrossTonage());
			obj.put("safeTime", dmVmaPilotCategorie.getSafeTime());
			obj.put("remarks", dmVmaPilotCategorie.getRemarks());
			obj.put("isDelete", dmVmaPilotCategorie.getIsDelete());
			obj.put("role", true);

			array.put(obj);
		}

		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailVmaPilotCategory(String pilotCategoryCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmVmaPilotCategory dmVmaPilotCategory = DmVmaPilotCategoryLocalServiceUtil
				.fetchByPilotCategoryCode(pilotCategoryCode);
		result.put("id", dmVmaPilotCategory.getId());
		result.put("pilotCategoryCode",
				dmVmaPilotCategory.getPilotCategoryCode());
		result.put("pilotCategoryName",
				dmVmaPilotCategory.getPilotCategoryName());
		result.put("maxLength", dmVmaPilotCategory.getMaxLength());
		result.put("grossTonage", dmVmaPilotCategory.getGrossTonage());
		result.put("safeTime", dmVmaPilotCategory.getSafeTime());
		result.put("remarks", dmVmaPilotCategory.getRemarks());
		result.put("modifiedDate", dmVmaPilotCategory.getModifiedDate());
		result.put("isDelete", dmVmaPilotCategory.getIsDelete());
		result.put("syncVersion", dmVmaPilotCategory.getSyncVersion());

		return result;
	}

	public static void actionUpdatePilotCategory(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) throws Exception {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String fromMaritimeCode = userPort.getPortCode();
		String pilotCategoryCode = DanhMucUtils.getString(actionRequest,
				"pilotCategoryCode");
		String pilotCategoryName = DanhMucUtils.getString(actionRequest,
				"pilotCategoryName");
		String maxLength = DanhMucUtils.getString(actionRequest, "maxLength");
		String safeTime = DanhMucUtils.getString(actionRequest, "safeTime");
		String remarks = DanhMucUtils.getString(actionRequest, "remarks");
		String grossTonage = DanhMucUtils.getString(actionRequest,
				"grossTonage");
		String syncVersion = DanhMucUtils.getString(actionRequest,
				"syncVersion");

		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);

		try {
			if (danhdauXoa.length() > 0) {
				log.info("messageType101  Xoa");
				DmVmaPilotCategory dmVmaPilotCategory = DmVmaPilotCategoryLocalServiceUtil
						.deleteVmaPilotCategory(fromMaritimeCode,
								pilotCategoryCode,
								DanhMucUtils.createNewSyncVersion(syncVersion));
				DmHistoryPilotCategory dmHistoryPilotCategory = DmHistoryPilotCategoryLocalServiceUtil
						.deleteHistoryPilotCategory(fromMaritimeCode,
								pilotCategoryCode, DanhMucUtils
										.getLastSyncVersion(dmVmaPilotCategory
												.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_vma_pilot_category", dmVmaPilotCategory
								.getPilotCategoryCode(), dmVmaPilotCategory
								.getPilotCategoryName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_history_pilot_category", dmHistoryPilotCategory
								.getPilotCategoryCode(), dmHistoryPilotCategory
								.getPilotCategoryName());
			} else if (capmoi.length() > 0) {
				log.info("messageType101 Them");
				pilotCategoryCode = ReportsBusinessUtils.createCode("", "",
						String.valueOf(CounterLocalServiceUtil
								.increment("code#" + "dm_vma_pilot_category")),
						5);
				DmVmaPilotCategory dmVmaPilotCategory = DmVmaPilotCategoryLocalServiceUtil
						.updateVmaPilotCategory(fromMaritimeCode,
								pilotCategoryCode, pilotCategoryName,
								maxLength, safeTime, remarks, grossTonage, "1|");
				DmHistoryPilotCategory dmHistoryPilotCategory = DmHistoryPilotCategoryLocalServiceUtil
						.updateHistoryPilotCategory(fromMaritimeCode,
								pilotCategoryCode, pilotCategoryName,
								maxLength, safeTime, remarks, grossTonage, "1");

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_vma_pilot_category", dmVmaPilotCategory
								.getPilotCategoryCode(), dmVmaPilotCategory
								.getPilotCategoryName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_history_pilot_category", dmHistoryPilotCategory
								.getPilotCategoryCode(), dmHistoryPilotCategory
								.getPilotCategoryName());
			} else if (capsua.length() > 0) {
				log.info("messageType101  Sua");
				DmVmaPilotCategory dmVmaPilotCategory = DmVmaPilotCategoryLocalServiceUtil
						.updateVmaPilotCategory(fromMaritimeCode,
								pilotCategoryCode, pilotCategoryName,
								maxLength, safeTime, remarks, grossTonage,
								DanhMucUtils.createNewSyncVersion(syncVersion));
				DmHistoryPilotCategory dmHistoryPilotCategory = DmHistoryPilotCategoryLocalServiceUtil
						.updateHistoryPilotCategory(fromMaritimeCode,
								pilotCategoryCode, pilotCategoryName,
								maxLength, safeTime, remarks, grossTonage,
								DanhMucUtils
										.getLastSyncVersion(dmVmaPilotCategory
												.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_vma_pilot_category", dmVmaPilotCategory
								.getPilotCategoryCode(), dmVmaPilotCategory
								.getPilotCategoryName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_history_pilot_category", dmHistoryPilotCategory
								.getPilotCategoryCode(), dmHistoryPilotCategory
								.getPilotCategoryName());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		//todo tinh sau
	}
}
