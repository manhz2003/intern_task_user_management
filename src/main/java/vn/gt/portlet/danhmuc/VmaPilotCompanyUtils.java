package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany;
import com.fds.nsw.nghiepvu.model.DmVmaPilotCompany;
import vn.gt.dao.danhmuc.service.DmHistoryPilotCompanyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotCompanyLocalServiceUtil;
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
public class VmaPilotCompanyUtils {

	

	public static JSONObject getVmaPilotCompanys(long userId,
			String maritimeCode, String pilotCompanyName,
			String companyAddress, String contactEmail, String telNo,
			String isDelete, String pilotCompanyCodeGroup, int start, int end) {

		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmVmaPilotCompany> dmVmaPilotCompanies = DmVmaPilotCompanyLocalServiceUtil
				.findPilotCompanies(maritimeCode, pilotCompanyName,
						companyAddress, contactEmail, telNo, isDelete,
						pilotCompanyCodeGroup, start, end);
		total = DmVmaPilotCompanyLocalServiceUtil.countPilotCompanies(
				maritimeCode, pilotCompanyName, companyAddress, contactEmail,
				telNo, isDelete, pilotCompanyCodeGroup);
		for (DmVmaPilotCompany dmVmaPilotCompany : dmVmaPilotCompanies) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmVmaPilotCompany.getId());
			obj.put("maritimeCode", dmVmaPilotCompany.getMaritimeCode());
			obj.put("companyAddress", dmVmaPilotCompany.getCompanyAddress());
			obj.put("contactEmail", dmVmaPilotCompany.getContactEmail());
			obj.put("telNo", dmVmaPilotCompany.getTelNo());
			obj.put("pilotCompanyCode", dmVmaPilotCompany.getPilotCompanyCode());
			obj.put("pilotCompanyName", dmVmaPilotCompany.getPilotCompanyName());
			obj.put("faxNo", dmVmaPilotCompany.getFaxNo());
			obj.put("remarks", dmVmaPilotCompany.getRemarks());
			obj.put("isDelete", dmVmaPilotCompany.getIsDelete());
			obj.put("companyShortName", dmVmaPilotCompany.getCompanyShortName());
			obj.put("role",
					dmVmaPilotCompany.getMaritimeCode().equals("")
							|| dmVmaPilotCompany.getMaritimeCode().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")));

			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailVmaPilotCompany(String pilotCompanyCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmVmaPilotCompany dmVmaPilotCompany = DmVmaPilotCompanyLocalServiceUtil
				.fetchByPilotCompanyCode(pilotCompanyCode);
		result.put("id", dmVmaPilotCompany.getId());
		result.put("maritimeCode", dmVmaPilotCompany.getMaritimeCode());
		result.put("companyAddress", dmVmaPilotCompany.getCompanyAddress());
		result.put("contactEmail", dmVmaPilotCompany.getContactEmail());
		result.put("telNo", dmVmaPilotCompany.getTelNo());
		result.put("pilotCompanyCode", dmVmaPilotCompany.getPilotCompanyCode());
		result.put("pilotCompanyName", dmVmaPilotCompany.getPilotCompanyName());
		result.put("faxNo", dmVmaPilotCompany.getFaxNo());
		result.put("remarks", dmVmaPilotCompany.getRemarks());
		result.put("modifiedDate", dmVmaPilotCompany.getModifiedDate());
		result.put("isDelete", dmVmaPilotCompany.getIsDelete());
		result.put("syncVersion", dmVmaPilotCompany.getSyncVersion());
		result.put("companyShortName", dmVmaPilotCompany.getCompanyShortName());

		return result;
	}

	public static void actionUpdatePilotCompany(ThemeDisplay themeDisplay,
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
		String pilotCompanyName = DanhMucUtils.getString(actionRequest,
				"pilotCompanyName");
		String pilotCompanyCode = DanhMucUtils.getString(actionRequest,
				"pilotCompanyCode");
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

		// Add by TrungNT

		String companyShortName = DanhMucUtils.getString(actionRequest,
				"companyShortName");

		try {
			if (danhdauXoa.length() > 0) {
				log.info("messageType101  Xoa");
				DmVmaPilotCompany dmVmaPilotCompany = DmVmaPilotCompanyLocalServiceUtil
						.deletePilotCompany(fromMaritimeCode, pilotCompanyCode,
								DanhMucUtils.createNewSyncVersion(syncVersion));

				DmHistoryPilotCompany dmHistoryPilotCompany = DmHistoryPilotCompanyLocalServiceUtil
						.deleteHistoryPilotCompany(fromMaritimeCode,
								pilotCompanyCode, DanhMucUtils
										.getLastSyncVersion(dmVmaPilotCompany
												.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_vma_pilot_company", dmVmaPilotCompany
								.getPilotCompanyCode(), dmVmaPilotCompany
								.getPilotCompanyName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_history_pilot_company", dmHistoryPilotCompany
								.getPilotCompanyCode(), dmHistoryPilotCompany
								.getPilotCompanyName());
			} else if (capmoi.length() > 0) {
				log.info("messageType101 Them");
				pilotCompanyCode = ReportsBusinessUtils.createCode(
						maritimeReference, "PILC", String
								.valueOf(CounterLocalServiceUtil
										.increment("code#"
												+ DmVmaPilotCompany.class
														.getName())), 5);
				DmVmaPilotCompany dmVmaPilotCompany = DmVmaPilotCompanyLocalServiceUtil
						.updatePilotCompany(fromMaritimeCode, maritimeCode,
								pilotCompanyCode, pilotCompanyName, telNo,
								faxNo, contactEmail, companyAddress, remarks,
								"1|", companyShortName);

				DmHistoryPilotCompany dmHistoryPilotCompany = DmHistoryPilotCompanyLocalServiceUtil
						.updateHistoryPilotCompany(fromMaritimeCode,
								maritimeCode, pilotCompanyCode,
								pilotCompanyName, telNo, faxNo, contactEmail,
								companyAddress, remarks, "1", companyShortName);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_vma_pilot_company", dmVmaPilotCompany
								.getPilotCompanyCode(), dmVmaPilotCompany
								.getPilotCompanyName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_history_pilot_company", dmHistoryPilotCompany
								.getPilotCompanyCode(), dmHistoryPilotCompany
								.getPilotCompanyName());
			} else if (capsua.length() > 0) {
				log.info("messageType101  Sua");
				DmVmaPilotCompany dmVmaPilotCompany = DmVmaPilotCompanyLocalServiceUtil
						.updatePilotCompany(fromMaritimeCode, maritimeCode,
								pilotCompanyCode, pilotCompanyName, telNo,
								faxNo, contactEmail, companyAddress, remarks,
								DanhMucUtils.createNewSyncVersion(syncVersion),
								companyShortName);

				DmHistoryPilotCompany dmHistoryPilotCompany = DmHistoryPilotCompanyLocalServiceUtil
						.updateHistoryPilotCompany(fromMaritimeCode,
								maritimeCode, pilotCompanyCode,
								pilotCompanyName, telNo, faxNo, contactEmail,
								companyAddress, remarks, DanhMucUtils
										.getLastSyncVersion(dmVmaPilotCompany
												.getSyncVersion()),
								companyShortName);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_vma_pilot_company", dmVmaPilotCompany
								.getPilotCompanyCode(), dmVmaPilotCompany
								.getPilotCompanyName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_history_pilot_company", dmHistoryPilotCompany
								.getPilotCompanyCode(), dmHistoryPilotCompany
								.getPilotCompanyName());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		//todo tinh sau
	}
}
