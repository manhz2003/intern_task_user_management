package vn.gt.portlet.danhmuc;

import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.liferay.core.ResourceRequest;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryTugboat;
import com.fds.nsw.nghiepvu.model.DmVmaTugboat;
import com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany;
import vn.gt.dao.danhmuc.service.DmHistoryTugboatLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaTugboatCompanyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaTugboatLocalServiceUtil;
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
public class VmaTugboatUtils {


	

	public static long doCount(ResourceRequest resourceRequest) {
		HttpServletRequest request = resourceRequest;

		String shipName = DanhMucUtils.getString(request, "shipName").trim();
		String shipCodeGroup = DanhMucUtils.getString(request, "shipCode");
		String tugboatCompanyCode = DanhMucUtils.getString(request,
				"tugboatCompanyCode").trim();
		String maritimeCode = DanhMucUtils.getString(request, "maritimeCode")
				.trim();
		String isDelete = DanhMucUtils.getString(request, "isDelete").trim();

		return DmVmaTugboatLocalServiceUtil.countVmaTugboats(maritimeCode,
				tugboatCompanyCode, shipName, 0, 0, isDelete, shipCodeGroup);

	}

	public static JSONObject getVmaTugboats(long userId, String maritimeCode,
			String shipName, int power1, int power2, String tugboatCompanyCode,
			String isDelete, String shipCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmVmaTugboat> dmVmaTugboats = DmVmaTugboatLocalServiceUtil
				.findVmaTugboats(maritimeCode, tugboatCompanyCode, shipName,
						power1, power2, isDelete, shipCodeGroup, start, end);
		total = DmVmaTugboatLocalServiceUtil.countVmaTugboats(maritimeCode,
				tugboatCompanyCode, shipName, power1, power2, isDelete,
				shipCodeGroup);
		for (DmVmaTugboat dmVmaTugboat : dmVmaTugboats) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmVmaTugboat.getId());
			obj.put("maritimeCode", dmVmaTugboat.getMaritimeCode());
			obj.put("tugboatCompanyCode", dmVmaTugboat.getTugboatCompanyCode());
			obj.put("tugboatCompanyName", dmVmaTugboat.getTugboatCompanyName());
			obj.put("shipCode", dmVmaTugboat.getShipCode());
			obj.put("shipName", dmVmaTugboat.getShipName());
			obj.put("power", dmVmaTugboat.getPower());
			obj.put("loa", dmVmaTugboat.getLoa());
			obj.put("unitPower", dmVmaTugboat.getUnitPower());
			obj.put("vndUnitPrice", dmVmaTugboat.getVndUnitPrice());
			obj.put("usdUnitPrice", dmVmaTugboat.getUsdUnitPrice());
			obj.put("gt", dmVmaTugboat.getGt());
			obj.put("nt", dmVmaTugboat.getNt());
			obj.put("dwt", dmVmaTugboat.getDwt());
			obj.put("unitGRT", dmVmaTugboat.getUnitGRT());
			obj.put("unitNT", dmVmaTugboat.getUnitNT());
			obj.put("unitDWT", dmVmaTugboat.getUnitDWT());
			obj.put("breadth", dmVmaTugboat.getBreadth());
			obj.put("clearanceHeight", dmVmaTugboat.getClearanceHeight());
			obj.put("displacement", dmVmaTugboat.getDisplacement());
			obj.put("remarks", dmVmaTugboat.getRemarks());
			obj.put("isDelete", dmVmaTugboat.getIsDelete());
			obj.put("tugboatShortName", dmVmaTugboat.getTugboatShortName());
			obj.put("role",
					dmVmaTugboat.getMaritimeCode().equals("")
							|| dmVmaTugboat.getMaritimeCode().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")));
			if (dmVmaTugboat.getTugboatExpiredDate() != null) {
				obj.put("tugboatExpiredDate",
						dmVmaTugboat.getTugboatExpiredDate());
			} else {
				obj.put("tugboatExpiredDate", "");
			}
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailVmaTugboat(String shipCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmVmaTugboat dmVmaTugboat = DmVmaTugboatLocalServiceUtil
				.fetchByShipCode(shipCode);
		result.put("id", dmVmaTugboat.getId());
		result.put("maritimeCode", dmVmaTugboat.getMaritimeCode());
		result.put("tugboatCompanyCode", dmVmaTugboat.getTugboatCompanyCode());
		result.put("tugboatCompanyName", dmVmaTugboat.getTugboatCompanyName());
		result.put("shipCode", dmVmaTugboat.getShipCode());
		result.put("shipName", dmVmaTugboat.getShipName());
		result.put("power", dmVmaTugboat.getPower());
		result.put("loa", dmVmaTugboat.getLoa());
		result.put("vndUnitPrice", dmVmaTugboat.getVndUnitPrice());
		result.put("usdUnitPrice", dmVmaTugboat.getUsdUnitPrice());
		result.put("gt", dmVmaTugboat.getGt());
		result.put("nt", dmVmaTugboat.getNt());
		result.put("dwt", dmVmaTugboat.getDwt());
		result.put("unitGRT", dmVmaTugboat.getUnitGRT());
		result.put("unitNT", dmVmaTugboat.getUnitNT());
		result.put("unitDWT", dmVmaTugboat.getUnitDWT());
		result.put("breadth", dmVmaTugboat.getBreadth());
		result.put("clearanceHeight", dmVmaTugboat.getClearanceHeight());
		result.put("displacement", dmVmaTugboat.getDisplacement());
		result.put("remarks", dmVmaTugboat.getRemarks());
		result.put("modifiedDate", dmVmaTugboat.getModifiedDate());
		result.put("isDelete", dmVmaTugboat.getIsDelete());
		result.put("syncVersion", dmVmaTugboat.getSyncVersion());
		result.put("tugboatShortName", dmVmaTugboat.getTugboatShortName());
		if (dmVmaTugboat.getTugboatExpiredDate() != null) {
			result.put("tugboatExpiredDate",
					dmVmaTugboat.getTugboatExpiredDate());
		} else {
			result.put("tugboatExpiredDate", "");
		}

		return result;
	}

	public static void actionUpdateVmaTugboat(ThemeDisplay themeDisplay,
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
		String tugboatCompanyCode = DanhMucUtils.getString(actionRequest,
				"tugboatCompanyCode");
		String tugboatCompanyName = "";
		if (Validator.isNotNull(tugboatCompanyCode)
				&& !tugboatCompanyCode.isEmpty()) {
			DmVmaTugboatCompany dmVmaTugboatCompany = DmVmaTugboatCompanyLocalServiceUtil
					.fetchByTugboatCompanyCode(tugboatCompanyCode);
			if (dmVmaTugboatCompany == null) {
				return;
			} else {
				tugboatCompanyName = dmVmaTugboatCompany
						.getTugboatCompanyName();
			}
		}
		String shipCode = DanhMucUtils.getString(actionRequest, "_shipCode")
				.trim();
		String shipName = DanhMucUtils.getString(actionRequest, "_shipName")
				.trim();
		double power = ParamUtil.getDouble(actionRequest, "power", 0L);
		double loa = ParamUtil.getDouble(actionRequest, "loa", 0L);
		double breadth = ParamUtil.getDouble(actionRequest, "breadth", 0L);
		double clearanceHeight = ParamUtil.getDouble(actionRequest, "clearanceHeight", 0L);
		double displacement = ParamUtil.getDouble(actionRequest, "displacement", 0L);
		double vndUnitPrice = ParamUtil.getDouble(actionRequest, "vndUnitPrice", 0L);
		double usdUnitPrice = ParamUtil.getDouble(actionRequest, "usdUnitPrice", 0L);
		int gt = ParamUtil.getInteger(actionRequest, "gt");
		int nt = ParamUtil.getInteger(actionRequest, "nt");
		int dwt = ParamUtil.getInteger(actionRequest, "dwt");
		String unitGRT = DanhMucUtils.getString(actionRequest, "unitGRT");
		String unitNT = DanhMucUtils.getString(actionRequest, "unitNT");
		String unitDWT = DanhMucUtils.getString(actionRequest, "unitDWT");
		String unitPower = DanhMucUtils.getString(actionRequest, "unitPower")
				.trim();
		String remarks = DanhMucUtils.getString(actionRequest, "remarks")
				.trim();
		String syncVersion = DanhMucUtils.getString(actionRequest,
				"syncVersion");
		String tugboatExpiredDate = ParamUtil.getString(actionRequest,
				"tugboatExpiredDate");
		Date _tugboatExpiredDate = null;
		if (Validator.isNotNull(tugboatExpiredDate)
				&& !tugboatExpiredDate.isEmpty()) {
			try {
				_tugboatExpiredDate = FormatData.formatDateShort2
						.parse(tugboatExpiredDate);
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

		// add by TrungNT
		String tugboatShortName = DanhMucUtils.getString(actionRequest,
				"tugboatShortName");

		try {
			if (danhdauXoa.length() > 0) {
				log.info("messageType101  Xoa");
				DmVmaTugboat dmVmaTugboat = DmVmaTugboatLocalServiceUtil
						.deleteVmaTugboat(fromMaritimeCode, shipCode,
								DanhMucUtils.createNewSyncVersion(syncVersion));

				DmHistoryTugboat dmHistoryTugboat = DmHistoryTugboatLocalServiceUtil
						.deleteHistoryTugboat(fromMaritimeCode, shipCode,
								DanhMucUtils.getLastSyncVersion(dmVmaTugboat
										.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_vma_tugboat", dmVmaTugboat.getShipCode(),
						dmVmaTugboat.getShipName());

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_history_tugboat", dmHistoryTugboat.getShipCode(),
						dmHistoryTugboat.getShipName());
			} else if (capmoi.length() > 0) {
				log.info("messageType101 Them");
				long counterId = CounterLocalServiceUtil.increment("code#"
						+ "dm_vma_tugboat");
				shipCode = ReportsBusinessUtils.createCode(maritimeReference,
						"TUG", String.valueOf(counterId), 5);
				DmVmaTugboat dmVmaTugboat = DmVmaTugboatLocalServiceUtil
						.updateVmaTugboat(fromMaritimeCode, maritimeCode,
								tugboatCompanyCode, tugboatCompanyName,
								shipCode, shipName, power, loa, breadth,
								clearanceHeight, displacement, unitPower,
								vndUnitPrice, usdUnitPrice, gt, nt, dwt,
								unitGRT, unitNT, unitDWT, remarks, "1|",
								tugboatShortName, _tugboatExpiredDate);

				DmHistoryTugboat dmHistoryTugboat = DmHistoryTugboatLocalServiceUtil
						.updateHistoryTugboat(fromMaritimeCode, maritimeCode,
								tugboatCompanyCode, tugboatCompanyName,
								shipCode, shipName, power, loa, breadth,
								clearanceHeight, displacement, remarks,
								unitPower, vndUnitPrice, usdUnitPrice, gt, nt,
								dwt, unitGRT, unitNT, unitDWT, "1",
								tugboatShortName, _tugboatExpiredDate);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_vma_tugboat", dmVmaTugboat.getShipCode(),
						dmVmaTugboat.getShipName());

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_history_tugboat", dmHistoryTugboat.getShipCode(),
						dmHistoryTugboat.getShipName());
			} else if (capsua.length() > 0) {
				log.info("messageType101  Sua");
				DmVmaTugboat dmVmaTugboat = DmVmaTugboatLocalServiceUtil
						.updateVmaTugboat(fromMaritimeCode, maritimeCode,
								tugboatCompanyCode, tugboatCompanyName,
								shipCode, shipName, power, loa, breadth,
								clearanceHeight, displacement, unitPower,
								vndUnitPrice, usdUnitPrice, gt, nt, dwt,
								unitGRT, unitNT, unitDWT, remarks,
								DanhMucUtils.createNewSyncVersion(syncVersion),
								tugboatShortName, _tugboatExpiredDate);

				DmHistoryTugboat dmHistoryTugboat = DmHistoryTugboatLocalServiceUtil
						.updateHistoryTugboat(fromMaritimeCode, maritimeCode,
								tugboatCompanyCode, tugboatCompanyName,
								shipCode, shipName, power, loa, breadth,
								clearanceHeight, displacement, remarks,
								unitPower, vndUnitPrice, usdUnitPrice, gt, nt,
								dwt, unitGRT, unitNT, unitDWT, DanhMucUtils
										.getLastSyncVersion(dmVmaTugboat
												.getSyncVersion()),
								tugboatShortName, _tugboatExpiredDate);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_vma_tugboat", dmVmaTugboat.getShipCode(),
						dmVmaTugboat.getShipName());

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_history_tugboat", dmHistoryTugboat.getShipCode(),
						dmHistoryTugboat.getShipName());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		//todo tinh sau
	}
}
