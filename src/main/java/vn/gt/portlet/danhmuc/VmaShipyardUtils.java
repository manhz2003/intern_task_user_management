package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryShipyard;
import com.fds.nsw.nghiepvu.model.DmVmaShipyard;
import vn.gt.dao.danhmuc.service.DmHistoryShipyardLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipyardLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.PageType;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;



import com.fds.flex.common.ultility.GetterUtil;

import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaShipyardUtils {

	

	public static JSONObject getVmaShipYards(long userId, String maritimeCode,
			String companyName, String companyAddress, String contactEmail,
			String telNo, String taxCode, String isDelete,
			String shipYardCodeGroup, int start, int end) {

		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmVmaShipyard> dmVmaShipyards = DmVmaShipyardLocalServiceUtil
				.findVmaShipyards(maritimeCode, companyName, companyAddress,
						contactEmail, telNo, taxCode, isDelete,
						shipYardCodeGroup, start, end);
		total = DmVmaShipyardLocalServiceUtil.countVmaShipyards(maritimeCode,
				companyName, companyAddress, contactEmail, telNo, taxCode,
				isDelete, shipYardCodeGroup);
		for (DmVmaShipyard dmVmaShipyard : dmVmaShipyards) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmVmaShipyard.getId());
			obj.put("maritimeCode", dmVmaShipyard.getMaritimeCode());
			obj.put("shipYardCode", dmVmaShipyard.getShipYardCode());
			obj.put("contactEmail", dmVmaShipyard.getContactEmail());
			obj.put("telNo", dmVmaShipyard.getTelNo());
			obj.put("companyName", dmVmaShipyard.getCompanyName());
			obj.put("companyAddress", dmVmaShipyard.getCompanyAddress());
			obj.put("taxCode", dmVmaShipyard.getTaxCode());
			obj.put("faxNo", dmVmaShipyard.getFaxNo());
			obj.put("remarks", dmVmaShipyard.getRemarks());
			obj.put("isDelete", dmVmaShipyard.getIsDelete());
			obj.put("companyShortName", dmVmaShipyard.getCompanyShortName());
			obj.put("markupMaintainane", dmVmaShipyard.getMarkupMaintainane());
			obj.put("markupConstruction", dmVmaShipyard.getMarkupConstruction());
			obj.put("markupDeconstruction",
					dmVmaShipyard.getMarkupDeconstruction());
			obj.put("profileMaintainane", dmVmaShipyard.getProfileMaintainane());
			obj.put("profileConstruction",
					dmVmaShipyard.getProfileConstruction());
			obj.put("profileDeconstruction",
					dmVmaShipyard.getProfileDeconstruction());
			obj.put("companyShortName", dmVmaShipyard.getCompanyShortName());

			obj.put("role",
					dmVmaShipyard.getMaritimeCode().equals("")
							|| dmVmaShipyard.getMaritimeCode().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")));
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailVmaShipYard(String shipYardCode) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		DmVmaShipyard dmVmaShipyard = DmVmaShipyardLocalServiceUtil
				.fetchByShipYardCode(shipYardCode);

		obj.put("id", dmVmaShipyard.getId());
		obj.put("maritimeCode", dmVmaShipyard.getMaritimeCode());
		obj.put("shipYardCode", dmVmaShipyard.getShipYardCode());
		obj.put("contactEmail", dmVmaShipyard.getContactEmail());
		obj.put("telNo", dmVmaShipyard.getTelNo());
		obj.put("companyName", dmVmaShipyard.getCompanyName());
		obj.put("companyAddress", dmVmaShipyard.getCompanyAddress());
		obj.put("taxCode", dmVmaShipyard.getTaxCode());
		obj.put("faxNo", dmVmaShipyard.getFaxNo());
		obj.put("remarks", dmVmaShipyard.getRemarks());
		obj.put("modifiedDate", dmVmaShipyard.getModifiedDate());
		obj.put("isDelete", dmVmaShipyard.getIsDelete());
		obj.put("syncVersion", dmVmaShipyard.getSyncVersion());
		obj.put("companyShortName", dmVmaShipyard.getCompanyShortName());
		obj.put("markupMaintainane", dmVmaShipyard.getMarkupMaintainane());
		obj.put("markupConstruction", dmVmaShipyard.getMarkupConstruction());
		obj.put("markupDeconstruction", dmVmaShipyard.getMarkupDeconstruction());
		obj.put("profileMaintainane", dmVmaShipyard.getProfileMaintainane());
		obj.put("profileConstruction", dmVmaShipyard.getProfileConstruction());
		obj.put("profileDeconstruction",
				dmVmaShipyard.getProfileDeconstruction());
		obj.put("companyShortName", dmVmaShipyard.getCompanyShortName());

		return obj;
	}

	public static void actionUpdateShipYard(ThemeDisplay themeDisplay,
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
		String shipYardCode = ParamUtil
				.getString(actionRequest, "shipYardCode");
		String taxCode = DanhMucUtils.getString(actionRequest, "taxCode");
		String companyName = DanhMucUtils.getString(actionRequest,
				"companyName");
		String companyAddress = DanhMucUtils.getString(actionRequest,
				"companyAddress");
		String contactEmail = ParamUtil
				.getString(actionRequest, "contactEmail");
		String telNo = DanhMucUtils.getString(actionRequest, "telNo");
		String faxNo = DanhMucUtils.getString(actionRequest, "faxNo");
		String remarks = DanhMucUtils.getString(actionRequest, "remarks");
		String syncVersion = DanhMucUtils.getString(actionRequest,
				"syncVersion");

		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);

		String companyShortName = DanhMucUtils.getString(actionRequest,
				"companyShortName");
		int markupMaintainane = GetterUtil.getInteger(
				ParamUtil.getString(actionRequest, "markupMaintainane"), 0);
		int markupConstruction = GetterUtil.getInteger(
				ParamUtil.getString(actionRequest, "markupConstruction"), 0);
		int markupDeconstruction = GetterUtil.getInteger(
				ParamUtil.getString(actionRequest, "markupDeconstruction"), 0);
		String profileMaintainane = DanhMucUtils.getString(actionRequest,
				"profileMaintainane");
		String profileConstruction = DanhMucUtils.getString(actionRequest,
				"profileConstruction");
		String profileDeconstruction = DanhMucUtils.getString(actionRequest,
				"profileDeconstruction");

		try {
			if (danhdauXoa.length() > 0) {
				log.info("messageType101  Xoa");
				DmVmaShipyard dmVmaShipyard = DmVmaShipyardLocalServiceUtil
						.deleteVmaShipYard(fromMaritimeCode, shipYardCode,
								DanhMucUtils.createNewSyncVersion(syncVersion));

				DmHistoryShipyard dmHistoryShipyard = DmHistoryShipyardLocalServiceUtil
						.deleteHistoryShipYard(fromMaritimeCode, shipYardCode,
								DanhMucUtils.getLastSyncVersion(dmVmaShipyard
										.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_vma_shipyard", dmVmaShipyard.getShipYardCode(),
						dmVmaShipyard.getCompanyName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_history_shipyard", dmHistoryShipyard
								.getShipYardCode(), dmHistoryShipyard
								.getCompanyName());
			} else if (capmoi.length() > 0) {
				log.info("messageType101 Them");
				if (taxCode != null && !taxCode.isEmpty()) {
					DmVmaShipyard tempShipYard = DmVmaShipyardLocalServiceUtil
							.fetchByShipYardCode(taxCode);
					if (tempShipYard != null) {
						return;
					} else {
						shipYardCode = taxCode;
					}
				} else if (taxCode == null || taxCode.isEmpty()) {
					shipYardCode = ReportsBusinessUtils.createCode(
							maritimeReference, "YAR", String
									.valueOf(CounterLocalServiceUtil
											.increment("code#"
													+ DmVmaShipyard.class
															.getName())), 5);
				}
				DmVmaShipyard dmVmaShipyard = DmVmaShipyardLocalServiceUtil
						.updateVmaShipYard(fromMaritimeCode, shipYardCode,
								companyName, companyAddress, contactEmail,
								faxNo, remarks, maritimeCode, telNo, taxCode,
								"1|", companyShortName, markupMaintainane,
								markupConstruction, markupDeconstruction,
								profileMaintainane, profileConstruction,
								profileDeconstruction);
				DmHistoryShipyard dmHistoryShipyard = DmHistoryShipyardLocalServiceUtil
						.updateHistoryShipYard(fromMaritimeCode,
								dmVmaShipyard.getShipYardCode(), companyName,
								companyAddress, contactEmail, faxNo, remarks,
								maritimeCode, telNo, taxCode, "1",
								companyShortName, markupMaintainane,
								markupConstruction, markupDeconstruction,
								profileMaintainane, profileConstruction,
								profileDeconstruction);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_vma_shipyard", dmVmaShipyard.getShipYardCode(),
						dmVmaShipyard.getCompanyName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_history_shipyard", dmHistoryShipyard
								.getShipYardCode(), dmHistoryShipyard
								.getCompanyName());
			} else if (capsua.length() > 0) {
				log.info("messageType101  Sua");
				if (taxCode != null && !taxCode.isEmpty()) {
					DmVmaShipyard tempShipYard = DmVmaShipyardLocalServiceUtil
							.fetchByShipYardCode(taxCode);
					if (tempShipYard != null) {
						return;
					}
				}
				DmVmaShipyard dmVmaShipyard = DmVmaShipyardLocalServiceUtil
						.updateVmaShipYard(fromMaritimeCode, shipYardCode,
								companyName, companyAddress, contactEmail,
								faxNo, remarks, maritimeCode, telNo, taxCode,
								DanhMucUtils.createNewSyncVersion(syncVersion),
								companyShortName, markupMaintainane,
								markupConstruction, markupDeconstruction,
								profileMaintainane, profileConstruction,
								profileDeconstruction);
				DmHistoryShipyard dmHistoryShipyard = DmHistoryShipyardLocalServiceUtil
						.updateHistoryShipYard(fromMaritimeCode, shipYardCode,
								companyName, companyAddress, contactEmail,
								faxNo, remarks, maritimeCode, telNo, taxCode,
								DanhMucUtils.getLastSyncVersion(dmVmaShipyard
										.getSyncVersion()), companyShortName,
								markupMaintainane, markupConstruction,
								markupDeconstruction, profileMaintainane,
								profileConstruction, profileDeconstruction);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_vma_shipyard", dmVmaShipyard.getShipYardCode(),
						dmVmaShipyard.getCompanyName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_history_shipyard", dmHistoryShipyard
								.getShipYardCode(), dmHistoryShipyard
								.getCompanyName());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		//todo tinh sau
	}
}
