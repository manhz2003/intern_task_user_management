package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType;
import com.fds.nsw.nghiepvu.model.DmVmaShipType;
import vn.gt.dao.danhmuc.service.DmHistoryVmaShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipTypeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.PageType;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;




import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaShipTypeUtils {

	

	public static JSONObject getVmaShipTypes(String shipTypeName,
			String applyShip, String applyBoat, String isDelete,
			String shipTypeCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmVmaShipType> dmVmaShipTypes = DmVmaShipTypeLocalServiceUtil
				.findVmaShipTypes(shipTypeName, applyShip, applyBoat, isDelete,
						shipTypeCodeGroup, start, end);
		total = DmVmaShipTypeLocalServiceUtil.countVmaShipTypes(shipTypeName,
				applyShip, applyBoat, isDelete, shipTypeCodeGroup);
		for (DmVmaShipType dmVmaShipType : dmVmaShipTypes) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmVmaShipType.getId());
			obj.put("shipTypeCode", dmVmaShipType.getShipTypeCode());
			obj.put("shipTypeName", dmVmaShipType.getShipTypeName());
			obj.put("shipTypeRef", dmVmaShipType.getShipTypeRef());
			obj.put("applyBoat", dmVmaShipType.getApplyBoat());
			obj.put("applyShip", dmVmaShipType.getApplyShip());
			obj.put("remarks", dmVmaShipType.getRemarks());
			obj.put("isDelete", dmVmaShipType.getIsDelete());
			obj.put("role", true);
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailVmaShipType(String shipTypeCode) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		DmVmaShipType dmVmaShipType = DmVmaShipTypeLocalServiceUtil
				.fetchByShipTypeCode(shipTypeCode);

		obj.put("id", dmVmaShipType.getId());
		obj.put("shipTypeCode", dmVmaShipType.getShipTypeCode());
		obj.put("shipTypeName", dmVmaShipType.getShipTypeName());
		obj.put("shipTypeRef", dmVmaShipType.getShipTypeRef());
		obj.put("applyBoat", dmVmaShipType.getApplyBoat());
		obj.put("applyShip", dmVmaShipType.getApplyShip());
		obj.put("remarks", dmVmaShipType.getRemarks());
		obj.put("modifiedDate", dmVmaShipType.getModifiedDate());
		obj.put("isDelete", dmVmaShipType.getIsDelete());
		obj.put("syncVersion", dmVmaShipType.getSyncVersion());
		return obj;
	}

	public static void actionUpdateVmaShipType(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) throws Exception {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String fromMaritimeCode = userPort.getPortCode();
		String shipTypeCode = ParamUtil
				.getString(actionRequest, "shipTypeCode");
		String shipTypeName = DanhMucUtils.getString(actionRequest,
				"shipTypeName");
		int applyShip = ParamUtil.getInteger(actionRequest, "applyShip");
		int applyBoat = ParamUtil.getInteger(actionRequest, "applyBoat");
		String remarks = DanhMucUtils.getString(actionRequest, "remarks");
		String shipTypeRef = DanhMucUtils.getString(actionRequest,
				"shipTypeRef");
		String syncVersion = DanhMucUtils.getString(actionRequest,
				"syncVersion");

		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);
		if (capmoi.length() > 0 || capsua.length() > 0) {
			if ((applyShip == 1 && applyBoat == 1)
					|| (applyShip == 0 && applyBoat == 0)) {
				log.info("ApplyShip va ApplyBoat khong the trung nhau");
				return;
			}
		}
		try {
			if (danhdauXoa.length() > 0) {
				log.info("messageType101  Xoa");
				DmVmaShipType dmVmaShipType = DmVmaShipTypeLocalServiceUtil
						.deleteVmaShipType(fromMaritimeCode, shipTypeCode,
								DanhMucUtils.createNewSyncVersion(syncVersion));

				DmHistoryVmaShipType dmHistoryVmaShipType = DmHistoryVmaShipTypeLocalServiceUtil
						.deleteHistoryVmaShipType(fromMaritimeCode,
								shipTypeCode, DanhMucUtils
										.getLastSyncVersion(dmVmaShipType
												.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_vma_ship_type", dmVmaShipType.getShipTypeCode(),
						dmVmaShipType.getShipTypeName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_history_vmaship_type", dmHistoryVmaShipType
								.getShipTypeCode(), dmHistoryVmaShipType
								.getShipTypeName());
			} else if (capmoi.length() > 0) {
				log.info("messageType101 Them");
				shipTypeCode = ReportsBusinessUtils.createCode("", "",
						String.valueOf(CounterLocalServiceUtil
								.increment("code#" + "dm_vma_ship_type")), 5);
				DmVmaShipType dmVmaShipType = DmVmaShipTypeLocalServiceUtil
						.updateVmaShipType(fromMaritimeCode, shipTypeCode,
								shipTypeName, applyBoat, applyShip, remarks,
								shipTypeRef, "1|");
				DmHistoryVmaShipType dmHistoryVmaShipType = DmHistoryVmaShipTypeLocalServiceUtil
						.updateHistoryVmaShipType(fromMaritimeCode,
								shipTypeCode, shipTypeName, applyBoat,
								applyShip, remarks, shipTypeRef, "1");

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_vma_ship_type", dmVmaShipType.getShipTypeCode(),
						dmVmaShipType.getShipTypeName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_history_vmaship_type", dmHistoryVmaShipType
								.getShipTypeCode(), dmHistoryVmaShipType
								.getShipTypeName());
			} else if (capsua.length() > 0) {
				log.info("messageType101  Sua");
				DmVmaShipType dmVmaShipType = DmVmaShipTypeLocalServiceUtil
						.updateVmaShipType(fromMaritimeCode, shipTypeCode,
								shipTypeName, applyBoat, applyShip, remarks,
								shipTypeRef,
								DanhMucUtils.createNewSyncVersion(syncVersion));
				DmHistoryVmaShipType dmHistoryVmaShipType = DmHistoryVmaShipTypeLocalServiceUtil
						.updateHistoryVmaShipType(fromMaritimeCode,
								shipTypeCode, shipTypeName, applyBoat,
								applyShip, remarks, shipTypeRef, DanhMucUtils
										.getLastSyncVersion(dmVmaShipType
												.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_vma_ship_type", dmVmaShipType.getShipTypeCode(),
						dmVmaShipType.getShipTypeName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_history_vmaship_type", dmHistoryVmaShipType
								.getShipTypeCode(), dmHistoryVmaShipType
								.getShipTypeName());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		//todo tinh sau
	}
}
