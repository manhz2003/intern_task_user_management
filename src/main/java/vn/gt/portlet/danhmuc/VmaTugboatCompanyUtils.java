package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany;
import com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany;
import vn.gt.dao.danhmuc.service.DmHistoryTugboatCompanyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaTugboatCompanyLocalServiceUtil;
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
public class VmaTugboatCompanyUtils {

	

	public static JSONObject getVmaTugboatCompanys(long userId,
			String maritimeCode, String tugboatCompanyName,
			String companyAddress, String contactEmail, String telNo,
			String isDelete, String tugboatCompanyCodeGroup, int start, int end) {

		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmVmaTugboatCompany> dmVmaTugboatCompanies = DmVmaTugboatCompanyLocalServiceUtil
				.findVmaTugboatCompanies(maritimeCode, tugboatCompanyName,
						companyAddress, contactEmail, telNo, isDelete,
						tugboatCompanyCodeGroup, start, end);
		total = DmVmaTugboatCompanyLocalServiceUtil.countVmaTugboatCompanies(
				maritimeCode, tugboatCompanyName, companyAddress, contactEmail,
				telNo, isDelete, tugboatCompanyCodeGroup);
		for (DmVmaTugboatCompany dmVmaTugboatCompanie : dmVmaTugboatCompanies) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmVmaTugboatCompanie.getId());
			obj.put("maritimeCode", dmVmaTugboatCompanie.getMaritimeCode());
			obj.put("companyAddress", dmVmaTugboatCompanie.getCompanyAddress());
			obj.put("contactEmail", dmVmaTugboatCompanie.getContactEmail());
			obj.put("telNo", dmVmaTugboatCompanie.getTelNo());
			obj.put("tugboatCompanyCode",
					dmVmaTugboatCompanie.getTugboatCompanyCode());
			obj.put("tugboatCompanyName",
					dmVmaTugboatCompanie.getTugboatCompanyName());
			obj.put("faxNo", dmVmaTugboatCompanie.getFaxNo());
			obj.put("remarks", dmVmaTugboatCompanie.getRemarks());
			obj.put("isDelete", dmVmaTugboatCompanie.getIsDelete());
			obj.put("companyShortName",
					dmVmaTugboatCompanie.getCompanyShortName());
			obj.put("role",
					dmVmaTugboatCompanie.getMaritimeCode().equals("")
							|| dmVmaTugboatCompanie.getMaritimeCode().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")));
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailVmaTugboatCompany(
			String tugboatCompanyCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmVmaTugboatCompany dmVmaTugboatCompany = DmVmaTugboatCompanyLocalServiceUtil
				.fetchByTugboatCompanyCode(tugboatCompanyCode);
		result.put("id", dmVmaTugboatCompany.getId());
		result.put("maritimeCode", dmVmaTugboatCompany.getMaritimeCode());
		result.put("companyAddress", dmVmaTugboatCompany.getCompanyAddress());
		result.put("contactEmail", dmVmaTugboatCompany.getContactEmail());
		result.put("telNo", dmVmaTugboatCompany.getTelNo());
		result.put("tugboatCompanyCode",
				dmVmaTugboatCompany.getTugboatCompanyCode());
		result.put("tugboatCompanyName",
				dmVmaTugboatCompany.getTugboatCompanyName());
		result.put("faxNo", dmVmaTugboatCompany.getFaxNo());
		result.put("remarks", dmVmaTugboatCompany.getRemarks());
		result.put("modifiedDate", dmVmaTugboatCompany.getModifiedDate());
		result.put("isDelete", dmVmaTugboatCompany.getIsDelete());
		result.put("syncVersion", dmVmaTugboatCompany.getSyncVersion());
		result.put("companyShortName",
				dmVmaTugboatCompany.getCompanyShortName());

		return result;
	}

	public static void actionUpdateTugboatCompany(ThemeDisplay themeDisplay,
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
		String tugboatCompanyName = DanhMucUtils.getString(actionRequest,
				"tugboatCompanyName");
		String tugboatCompanyCode = DanhMucUtils.getString(actionRequest,
				"tugboatCompanyCode");
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
				DmVmaTugboatCompany dmVmaTugboatCompany = DmVmaTugboatCompanyLocalServiceUtil
						.deleteVmaTugboatCompany(fromMaritimeCode,
								tugboatCompanyCode,
								DanhMucUtils.createNewSyncVersion(syncVersion));

				DmHistoryTugboatCompany dmHistoryTugboatCompany = DmHistoryTugboatCompanyLocalServiceUtil
						.deleteHistoryTugboatCompany(fromMaritimeCode,
								tugboatCompanyCode, DanhMucUtils
										.getLastSyncVersion(dmVmaTugboatCompany
												.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_vma_tugboat_company", dmVmaTugboatCompany
								.getTugboatCompanyCode(), dmVmaTugboatCompany
								.getTugboatCompanyName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_history_tugboat_company", dmHistoryTugboatCompany
								.getTugboatCompanyCode(),
						dmHistoryTugboatCompany.getTugboatCompanyName());
			} else if (capmoi.length() > 0) {
				log.info("messageType101 Them");
				tugboatCompanyCode = ReportsBusinessUtils.createCode(
						maritimeReference, "TUGC", String
								.valueOf(CounterLocalServiceUtil
										.increment("code#"
												+ DmVmaTugboatCompany.class
														.getName())), 5);
				DmVmaTugboatCompany dmVmaTugboatCompany = DmVmaTugboatCompanyLocalServiceUtil
						.updateVmaTugboatCompany(fromMaritimeCode,
								maritimeCode, tugboatCompanyCode,
								tugboatCompanyName, companyAddress,
								contactEmail, telNo, faxNo, remarks, "1|",
								companyShortName);

				DmHistoryTugboatCompany dmHistoryTugboatCompany = DmHistoryTugboatCompanyLocalServiceUtil
						.updateHistoryTugboatCompany(fromMaritimeCode,
								maritimeCode, tugboatCompanyCode,
								tugboatCompanyName, companyAddress,
								contactEmail, telNo, faxNo, remarks, "1",
								companyShortName);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_vma_tugboat_company", dmVmaTugboatCompany
								.getTugboatCompanyCode(), dmVmaTugboatCompany
								.getTugboatCompanyName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_history_tugboat_company", dmHistoryTugboatCompany
								.getTugboatCompanyCode(),
						dmHistoryTugboatCompany.getTugboatCompanyName());
			} else if (capsua.length() > 0) {
				log.info("messageType101  Sua");
				DmVmaTugboatCompany dmVmaTugboatCompany = DmVmaTugboatCompanyLocalServiceUtil
						.updateVmaTugboatCompany(fromMaritimeCode,
								maritimeCode, tugboatCompanyCode,
								tugboatCompanyName, companyAddress,
								contactEmail, telNo, faxNo, remarks,
								DanhMucUtils.createNewSyncVersion(syncVersion),
								companyShortName);

				DmHistoryTugboatCompany dmHistoryTugboatCompany = DmHistoryTugboatCompanyLocalServiceUtil
						.updateHistoryTugboatCompany(fromMaritimeCode,
								maritimeCode, tugboatCompanyCode,
								tugboatCompanyName, companyAddress,
								contactEmail, telNo, faxNo, remarks,
								DanhMucUtils
										.getLastSyncVersion(dmVmaTugboatCompany
												.getSyncVersion()),
								companyShortName);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_vma_tugboat_company", dmVmaTugboatCompany
								.getTugboatCompanyCode(), dmVmaTugboatCompany
								.getTugboatCompanyName());
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_history_tugboat_company", dmHistoryTugboatCompany
								.getTugboatCompanyCode(),
						dmHistoryTugboatCompany.getTugboatCompanyName());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		//todo tinh sau
	}
}
