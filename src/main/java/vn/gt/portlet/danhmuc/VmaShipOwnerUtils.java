package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryShipOwner;
import com.fds.nsw.nghiepvu.model.DmVmaShipOwner;
import vn.gt.dao.danhmuc.service.DmHistoryShipOwnerLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
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
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaShipOwnerUtils {

	

	public static JSONObject getVmaShipOwners(long userId, String maritimeCode,
			String taxCode, String companyName, String companyAddress,
			String contactEmail, String telNo, String isShipOwner,
			String isShipOperator, String isDelete, String shipOwnerCodeGroup,
			int isOther, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmVmaShipOwner> dmVmaShipOwners = DmVmaShipOwnerLocalServiceUtil
				.findVmaShipOwners(maritimeCode, taxCode, companyName,
						companyAddress, contactEmail, telNo, isShipOwner,
						isShipOperator, isDelete, shipOwnerCodeGroup, isOther,
						start, end);
		total = DmVmaShipOwnerLocalServiceUtil.countVmaShipOwners(maritimeCode,
				taxCode, companyName, companyAddress, contactEmail, telNo,
				isShipOwner, isShipOperator, isDelete, shipOwnerCodeGroup,
				isOther);
		for (DmVmaShipOwner dmVmaShipOwner : dmVmaShipOwners) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmVmaShipOwner.getId());
			obj.put("maritimeCode", dmVmaShipOwner.getMaritimeCode());
			obj.put("shipOwnerCode", dmVmaShipOwner.getShipOwnerCode());
			obj.put("companyName", dmVmaShipOwner.getCompanyName());
			obj.put("taxCode", dmVmaShipOwner.getTaxCode());
			obj.put("companyAddress", dmVmaShipOwner.getCompanyAddress());
			obj.put("telNo", dmVmaShipOwner.getTelNo());
			obj.put("faxNo", dmVmaShipOwner.getFaxNo());
			obj.put("contactEmail", dmVmaShipOwner.getContactEmail());
			obj.put("isShipOwner", dmVmaShipOwner.getIsShipOwner());
			obj.put("isShipOperator", dmVmaShipOwner.getIsShipOperator());
			obj.put("remarks", dmVmaShipOwner.getRemarks());
			obj.put("isDelete", dmVmaShipOwner.getIsDelete());
			obj.put("companyShortName", dmVmaShipOwner.getCompanyShortName());
			obj.put("isOther", dmVmaShipOwner.getIsOther());
			obj.put("role",
					dmVmaShipOwner.getMaritimeCode().equals("")
							|| dmVmaShipOwner.getMaritimeCode().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")));
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailVmaShipOwner(String shipOwnerCode) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		DmVmaShipOwner dmVmaShipOwner = DmVmaShipOwnerLocalServiceUtil
				.fetchByShipOwnerCode(shipOwnerCode);
		obj.put("id", dmVmaShipOwner.getId());
		obj.put("maritimeCode", dmVmaShipOwner.getMaritimeCode());
		obj.put("shipOwnerCode", dmVmaShipOwner.getShipOwnerCode());
		obj.put("companyName", dmVmaShipOwner.getCompanyName());
		obj.put("taxCode", dmVmaShipOwner.getTaxCode());
		obj.put("companyAddress", dmVmaShipOwner.getCompanyAddress());
		obj.put("telNo", dmVmaShipOwner.getTelNo());
		obj.put("faxNo", dmVmaShipOwner.getFaxNo());
		obj.put("contactEmail", dmVmaShipOwner.getContactEmail());
		obj.put("isShipOwner", dmVmaShipOwner.getIsShipOwner());
		obj.put("isShipOperator", dmVmaShipOwner.getIsShipOperator());
		obj.put("modifiedDate", dmVmaShipOwner.getModifiedDate());
		obj.put("isDelete", dmVmaShipOwner.getIsDelete());
		obj.put("remarks", dmVmaShipOwner.getRemarks());
		obj.put("syncVersion", dmVmaShipOwner.getSyncVersion());
		obj.put("companyShortName", dmVmaShipOwner.getCompanyShortName());
		obj.put("isOther", dmVmaShipOwner.getIsOther());

		return obj;
	}

	public static JSONObject actionUpdateVmaShipOwner(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
			throws Exception {

		JSONObject obj = JSONFactoryUtil.createJSONObject();

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
			return obj;
		}
		String shipOwnerCode = DanhMucUtils.getString(actionRequest,
				"shipOwnerCode");
		String taxCode = DanhMucUtils.getString(actionRequest, "taxCode");
		String companyName = DanhMucUtils.getString(actionRequest,
				"companyName");
		String companyAddress = DanhMucUtils.getString(actionRequest,
				"companyAddress");
		String contactEmail = ParamUtil
				.getString(actionRequest, "contactEmail");
		String telNo = DanhMucUtils.getString(actionRequest, "telNo");
		String faxNo = DanhMucUtils.getString(actionRequest, "faxNo");
		int isShipOwner = ParamUtil.getInteger(actionRequest, "isShipOwner");
		int isShipOperator = ParamUtil.getInteger(actionRequest,
				"isShipOperator");
		String remarks = DanhMucUtils.getString(actionRequest, "remarks");
		String syncVersion = DanhMucUtils.getString(actionRequest,
				"syncVersion");
		int isOther = ParamUtil.getInteger(actionRequest, "isOther");

		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);

		String companyShortName = DanhMucUtils.getString(actionRequest,
				"companyShortName");

		DmVmaShipOwner dmVmaShipOwner = null;
		DmHistoryShipOwner dmHistoryShipOwner = null;
		String logAction = StringPool.BLANK;
		try {
			if (danhdauXoa.length() > 0) {
				dmVmaShipOwner = DmVmaShipOwnerLocalServiceUtil
						.deleteVmaShipOwner(fromMaritimeCode, shipOwnerCode,
								DanhMucUtils.createNewSyncVersion(syncVersion));

				dmHistoryShipOwner = DmHistoryShipOwnerLocalServiceUtil
						.deleteHistoryShipOwner(fromMaritimeCode,
								shipOwnerCode, DanhMucUtils
										.getLastSyncVersion(dmVmaShipOwner
												.getSyncVersion()));
				logAction = LogsConstant.XOA;
			} else if (capmoi.length() > 0) {
				if (taxCode != null && !taxCode.isEmpty()) {
					DmVmaShipOwner tempShipOwner = DmVmaShipOwnerLocalServiceUtil
							.fetchByShipOwnerCode(taxCode);
					if (tempShipOwner != null) {
						return obj;
					} else {
						shipOwnerCode = taxCode;
					}
				} else if (taxCode == null || taxCode.isEmpty()) {
					shipOwnerCode = ReportsBusinessUtils.createCode(
							maritimeReference, "OWN", String
									.valueOf(CounterLocalServiceUtil
											.increment("code#"
													+ DmVmaShipOwner.class
															.getName())), 5);
				}
				dmVmaShipOwner = DmVmaShipOwnerLocalServiceUtil
						.updateVmaShipOwner(fromMaritimeCode, maritimeCode,
								shipOwnerCode, taxCode, companyName,
								companyAddress, contactEmail, telNo, faxNo,
								isShipOwner, isShipOperator, remarks, "1|",
								companyShortName, Integer.valueOf(isOther));

				dmHistoryShipOwner = DmHistoryShipOwnerLocalServiceUtil
						.updateHistoryShipOwner(fromMaritimeCode, maritimeCode,
								shipOwnerCode, taxCode, companyName,
								companyAddress, contactEmail, telNo, faxNo,
								isShipOwner, isShipOperator, remarks, "1",
								companyShortName, isOther);

				logAction = LogsConstant.THEM;
			} else if (capsua.length() > 0) {
				if (taxCode != null && !taxCode.isEmpty()) {
					DmVmaShipOwner tempShipOwner = DmVmaShipOwnerLocalServiceUtil
							.fetchByShipOwnerCode(taxCode);
					if (tempShipOwner != null) {
						return obj;
					}
				}
				dmVmaShipOwner = DmVmaShipOwnerLocalServiceUtil
						.updateVmaShipOwner(fromMaritimeCode, maritimeCode,
								shipOwnerCode, taxCode, companyName,
								companyAddress, contactEmail, telNo, faxNo,
								isShipOwner, isShipOperator, remarks,
								DanhMucUtils.createNewSyncVersion(syncVersion),
								companyShortName, isOther);
				dmHistoryShipOwner = DmHistoryShipOwnerLocalServiceUtil
						.updateHistoryShipOwner(fromMaritimeCode, maritimeCode,
								shipOwnerCode, taxCode, companyName,
								companyAddress, contactEmail, telNo, faxNo,
								isShipOwner, isShipOperator, remarks,
								DanhMucUtils.getLastSyncVersion(dmVmaShipOwner
										.getSyncVersion()), companyShortName,
								isOther);

				logAction = LogsConstant.SUA;
			}
			if (dmHistoryShipOwner != null && dmVmaShipOwner != null) {
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, logAction,
						"dm_vma_ship_owner", dmVmaShipOwner.getShipOwnerCode(),
						dmVmaShipOwner.getCompanyName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, logAction,
						"dm_history_ship_owner", dmHistoryShipOwner
								.getShipOwnerCode(), dmHistoryShipOwner
								.getCompanyName());

				obj.put("id", dmVmaShipOwner.getId());
				obj.put("maritimeCode", dmVmaShipOwner.getMaritimeCode());
				obj.put("shipOwnerCode", dmVmaShipOwner.getShipOwnerCode());
				obj.put("companyName", dmVmaShipOwner.getCompanyName());
				obj.put("taxCode", dmVmaShipOwner.getTaxCode());
				obj.put("companyAddress", dmVmaShipOwner.getCompanyAddress());
				obj.put("telNo", dmVmaShipOwner.getTelNo());
				obj.put("faxNo", dmVmaShipOwner.getFaxNo());
				obj.put("contactEmail", dmVmaShipOwner.getContactEmail());
				obj.put("isShipOwner", dmVmaShipOwner.getIsShipOwner());
				obj.put("isShipOperator", dmVmaShipOwner.getIsShipOperator());
				obj.put("remarks", dmVmaShipOwner.getRemarks());
				obj.put("isDelete", dmVmaShipOwner.getIsDelete());
				obj.put("companyShortName",
						dmVmaShipOwner.getCompanyShortName());
				obj.put("isOther", dmVmaShipOwner.getIsOther());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		//todo tinh sau

		return obj;
	}
}
