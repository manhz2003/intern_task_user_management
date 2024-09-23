package vn.gt.portlet.danhmuc;

import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryPilot;
import com.fds.nsw.nghiepvu.model.DmVmaPilot;
import com.fds.nsw.nghiepvu.model.DmVmaPilotCompany;
import vn.gt.dao.danhmuc.service.DmHistoryPilotLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotCategoryLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotCompanyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.FormatData;
import vn.gt.utils.PageType;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;




import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaPilotUtils {

	

	public static JSONObject getVmaPilots(long userId, String maritimeCode,
			String pilotCompanyCode, String pilotCategoryCode,
			String pilotName, String isDelete, String pilotCodeGroup,
			int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmVmaPilot> dmVmaPilots = DmVmaPilotLocalServiceUtil
				.findVmaPilots(maritimeCode, pilotCompanyCode,
						pilotCategoryCode, pilotName, isDelete, pilotCodeGroup,
						start, end);
		total = DmVmaPilotLocalServiceUtil.countVmaPilots(maritimeCode,
				pilotCompanyCode, pilotCategoryCode, pilotName, isDelete,
				pilotCodeGroup);
		for (DmVmaPilot dmVmaPilot : dmVmaPilots) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmVmaPilot.getId());
			obj.put("pilotCode", dmVmaPilot.getPilotCode());
			obj.put("pilotCategoryCode", dmVmaPilot.getPilotCategoryCode());
			try {
				obj.put("pilotCategoryName",
						DmVmaPilotCategoryLocalServiceUtil
								.fetchByPilotCategoryCode(
										dmVmaPilot.getPilotCategoryCode())
								.getPilotCategoryName());
			} catch (Exception e) {
				// nothing to do
			}
			obj.put("pilotName", dmVmaPilot.getPilotName());
			obj.put("pilotCompanyCode", dmVmaPilot.getPilotCompanyCode());
			obj.put("pilotBOD", dmVmaPilot.getPilotBOD());
			obj.put("pilotCompanyName", dmVmaPilot.getPilotCompanyName());
			obj.put("pilotNo", dmVmaPilot.getPilotNo());
			obj.put("pilotCertificateNo", dmVmaPilot.getPilotCertificateNo());
			if (dmVmaPilot.getPilotCertificateDate() != null) {
				obj.put("pilotCertificateDate",
						dmVmaPilot.getPilotCertificateDate());
			} else {
				obj.put("pilotCertificateDate", "");
			}
			obj.put("remarks", dmVmaPilot.getRemarks());
			obj.put("maritimeCode", dmVmaPilot.getMaritimeCode());
			obj.put("isDelete", dmVmaPilot.getIsDelete());
			obj.put("pilotShortName", dmVmaPilot.getPilotShortName());
			if (dmVmaPilot.getPilotExpiredDate() != null) {
				obj.put("pilotExpiredDate", FormatData.parseDateToTringType3(dmVmaPilot.getPilotExpiredDate()));
			} else {
				obj.put("pilotExpiredDate", "");
			}
			obj.put("role",
					dmVmaPilot.getMaritimeCode().equals("")
							|| dmVmaPilot.getMaritimeCode().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")));
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailVmaPilot(String pilotCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmVmaPilot dmVmaPilot = DmVmaPilotLocalServiceUtil
				.fetchbyPilotCode(pilotCode);
		result.put("id", dmVmaPilot.getId());
		result.put("pilotCode", dmVmaPilot.getPilotCode());
		result.put("pilotCategoryCode", dmVmaPilot.getPilotCategoryCode());
		result.put("pilotName", dmVmaPilot.getPilotName());
		result.put("pilotCompanyCode", dmVmaPilot.getPilotCompanyCode());
		result.put("pilotBOD", dmVmaPilot.getPilotBOD());
		result.put("pilotCompanyName", dmVmaPilot.getPilotCompanyName());
		result.put("pilotNo", dmVmaPilot.getPilotNo());
		result.put("pilotCertificateNo", dmVmaPilot.getPilotCertificateNo());
		if (dmVmaPilot.getPilotCertificateDate() != null) {
			result.put("pilotCertificateDate",
					dmVmaPilot.getPilotCertificateDate());
		} else {
			result.put("pilotCertificateDate", "");
		}
		result.put("remarks", dmVmaPilot.getRemarks());
		result.put("maritimeCode", dmVmaPilot.getMaritimeCode());
		result.put("modifiedDate", dmVmaPilot.getModifiedDate());
		result.put("isDelete", dmVmaPilot.getIsDelete());
		result.put("syncVersion", dmVmaPilot.getSyncVersion());
		result.put("pilotShortName", dmVmaPilot.getPilotShortName());
		if (dmVmaPilot.getPilotExpiredDate() != null) {
			result.put("pilotExpiredDate", dmVmaPilot.getPilotExpiredDate());
		} else {
			result.put("pilotExpiredDate", "");
		}
		try {
			result.put(
					"pilotCategoryName",
					DmVmaPilotCategoryLocalServiceUtil
							.fetchByPilotCategoryCode(
									dmVmaPilot.getPilotCategoryCode())
							.getPilotCategoryName());
		} catch (Exception e) {
			// nothing to do
		}

		return result;
	}

	public static void actionUpdateVmaPilot(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) throws Exception {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String maritimeCode = userPort.getPortCode();
		String maritimeReference = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode).getMaritimeReference();
		String fromMaritimeCode = maritimeCode;
		String maritimeCodeRequest = DanhMucUtils.getString(actionRequest,
				"maritimeCode");
		if (Validator.isNotNull(maritimeCodeRequest)
				& !maritimeCode.equals(maritimeCodeRequest)) {
			return;
		}
		String pilotCompanyCode = DanhMucUtils.getString(actionRequest,
				"pilotCompanyCode");
		String pilotCompanyName = "";
		if (Validator.isNotNull(pilotCompanyCode)
				&& !pilotCompanyCode.isEmpty()) {
			DmVmaPilotCompany dmVmaPilotCompany = DmVmaPilotCompanyLocalServiceUtil
					.fetchByPilotCompanyCode(pilotCompanyCode);
			if (dmVmaPilotCompany == null) {
				return;
			} else {
				pilotCompanyName = dmVmaPilotCompany.getPilotCompanyName();
			}
		}
		String pilotCode = DanhMucUtils.getString(actionRequest, "pilotCode");
		String pilotName = DanhMucUtils.getString(actionRequest, "pilotName");
		String pilotBOD = DanhMucUtils.getString(actionRequest, "pilotBOD");
		String pilotNo = DanhMucUtils.getString(actionRequest, "pilotNo");
		String pilotCertificateNo = DanhMucUtils.getString(actionRequest,
				"pilotCertificateNo");
		String pilotCertificateDate = DanhMucUtils.getString(actionRequest,
				"pilotCertificateDate");
		Date _pilotCertificateDate = null;
		if (Validator.isNotNull(pilotCertificateDate)
				&& !pilotCertificateDate.isEmpty()) {
			try {
				_pilotCertificateDate = FormatData.formatDateShort2
						.parse(pilotCertificateDate);
			} catch (Exception e) {
				// nothing to do
			}
		}
		String pilotCategoryCode = DanhMucUtils.getString(actionRequest,
				"pilotCategoryCode");
		if (Validator.isNotNull(pilotCategoryCode)
				& DmVmaPilotCategoryLocalServiceUtil
						.fetchByPilotCategoryCode(pilotCategoryCode) == null) {
			return;
		}
		String remarks = DanhMucUtils.getString(actionRequest, "remarks");
		String syncVersion = DanhMucUtils.getString(actionRequest,
				"syncVersion");
		String pilotExpiredDate = ParamUtil.getString(actionRequest,
				"pilotExpiredDate");
		Date _pilotExpiredDate = null;
		if (Validator.isNotNull(pilotExpiredDate)
				&& !pilotExpiredDate.isEmpty()) {
			try {
				_pilotExpiredDate = FormatData.formatDateShort2
						.parse(pilotExpiredDate);
			} catch (Exception e) {
				// nothing to do
			}
		}

		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);

		String pilotShortName = DanhMucUtils.getString(actionRequest,
				"pilotShortName");

		try {
			if (danhdauXoa.length() > 0) {
				log.info("messageType101  Xoa");
				DmVmaPilot dmVmaPilot = DmVmaPilotLocalServiceUtil
						.deleteVmaPilot(fromMaritimeCode, pilotCode,
								DanhMucUtils.createNewSyncVersion(syncVersion));

				DmHistoryPilot dmHistoryPilot = DmHistoryPilotLocalServiceUtil
						.deleteHistoryPilot(fromMaritimeCode, pilotCode,
								DanhMucUtils.getLastSyncVersion(dmVmaPilot
										.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_vma_pilot", dmVmaPilot.getPilotCode(), dmVmaPilot
								.getPilotName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_history_pilot", dmHistoryPilot.getPilotCode(),
						dmHistoryPilot.getPilotName());
			} else if (capmoi.length() > 0) {
				log.info("messageType101 Them");
				long counterId = CounterLocalServiceUtil.increment("code#"
						+ "dm_vma_pilot");
				pilotCode = ReportsBusinessUtils.createCode(maritimeReference,
						"PIL", String.valueOf(counterId), 5);
				DmVmaPilot dmVmaPilot = DmVmaPilotLocalServiceUtil
						.updateVmaPilot(fromMaritimeCode, maritimeCode,
								pilotCompanyCode, pilotCompanyName, pilotBOD,
								pilotNo, pilotCode, pilotName,
								pilotCertificateNo, pilotCategoryCode,
								_pilotCertificateDate, remarks, "1|",
								pilotShortName, _pilotExpiredDate);

				DmHistoryPilot dmHistoryPilot = DmHistoryPilotLocalServiceUtil
						.updateHistoryPilot(fromMaritimeCode, maritimeCode,
								pilotCompanyCode, pilotCompanyName, pilotBOD,
								pilotNo, pilotCode, pilotName,
								pilotCertificateNo, pilotCategoryCode,
								_pilotCertificateDate, remarks, "1",
								pilotShortName, _pilotExpiredDate);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_vma_pilot", dmVmaPilot.getPilotCode(), dmVmaPilot
								.getPilotName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_history_pilot", dmHistoryPilot.getPilotCode(),
						dmHistoryPilot.getPilotName());
			} else if (capsua.length() > 0) {
				log.info("messageType101  Sua");
				DmVmaPilot dmVmaPilot = DmVmaPilotLocalServiceUtil
						.updateVmaPilot(fromMaritimeCode, maritimeCode,
								pilotCompanyCode, pilotCompanyName, pilotBOD,
								pilotNo, pilotCode, pilotName,
								pilotCertificateNo, pilotCategoryCode,
								_pilotCertificateDate, remarks,
								DanhMucUtils.createNewSyncVersion(syncVersion),
								pilotShortName, _pilotExpiredDate);

				DmHistoryPilot dmHistoryPilot = DmHistoryPilotLocalServiceUtil
						.updateHistoryPilot(fromMaritimeCode, maritimeCode,
								pilotCompanyCode, pilotCompanyName, pilotBOD,
								pilotNo, pilotCode, pilotName,
								pilotCertificateNo, pilotCategoryCode,
								_pilotCertificateDate, remarks, DanhMucUtils
										.getLastSyncVersion(dmVmaPilot
												.getSyncVersion()),
								pilotShortName, _pilotExpiredDate);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_vma_pilot", dmVmaPilot.getPilotCode(), dmVmaPilot
								.getPilotName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_history_pilot", dmHistoryPilot.getPilotCode(),
						dmHistoryPilot.getPilotName());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		//todo tinh sau
	}
}
