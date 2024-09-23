package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice;
import com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice;
import vn.gt.dao.danhmuc.service.DmHistorySecurityOfficeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaSecurityOfficeLocalServiceUtil;
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
public class VmaSecurityOfficeUtils {


	

	public static JSONObject getVmaSecurityOffices(long userId,
			String maritimeCode, String companyName, String companyAddress,
			String contactEmail, String telNo, String isDelete,
			String securityOfficeCodeGroup, int start, int end) {

		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmVmaSecurityOffice> dmVmaSecurityOffices = DmVmaSecurityOfficeLocalServiceUtil
				.findVmaSecurityOffice(maritimeCode, companyName,
						companyAddress, contactEmail, telNo, isDelete,
						securityOfficeCodeGroup, start, end);
		total = DmVmaSecurityOfficeLocalServiceUtil.countVmaSecurityOffice(
				maritimeCode, companyName, companyAddress, contactEmail, telNo,
				isDelete, securityOfficeCodeGroup);
		for (DmVmaSecurityOffice dmVmaSecurityOffice : dmVmaSecurityOffices) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmVmaSecurityOffice.getId());
			obj.put("maritimeCode", dmVmaSecurityOffice.getMaritimeCode());
			obj.put("securityOfficeCode",
					dmVmaSecurityOffice.getSecurityOfficeCode());
			obj.put("contactEmail", dmVmaSecurityOffice.getContactEmail());
			obj.put("telNo", dmVmaSecurityOffice.getTelNo());
			obj.put("companyName", dmVmaSecurityOffice.getCompanyName());
			obj.put("companyAddress", dmVmaSecurityOffice.getCompanyAddress());
			obj.put("faxNo", dmVmaSecurityOffice.getFaxNo());
			obj.put("remarks", dmVmaSecurityOffice.getRemarks());
			obj.put("isDelete", dmVmaSecurityOffice.getIsDelete());
			obj.put("role",
					dmVmaSecurityOffice.getMaritimeCode().equals("")
							|| dmVmaSecurityOffice.getMaritimeCode().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")));
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailVmaSecurityOffice(
			String securityOfficeCode) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		DmVmaSecurityOffice dmVmaSecurityOffice = DmVmaSecurityOfficeLocalServiceUtil
				.fetchBySecurityOfficeCode(securityOfficeCode);

		obj.put("id", dmVmaSecurityOffice.getId());
		obj.put("maritimeCode", dmVmaSecurityOffice.getMaritimeCode());
		obj.put("securityOfficeCode",
				dmVmaSecurityOffice.getSecurityOfficeCode());
		obj.put("contactEmail", dmVmaSecurityOffice.getContactEmail());
		obj.put("telNo", dmVmaSecurityOffice.getTelNo());
		obj.put("companyName", dmVmaSecurityOffice.getCompanyName());
		obj.put("companyAddress", dmVmaSecurityOffice.getCompanyAddress());
		obj.put("faxNo", dmVmaSecurityOffice.getFaxNo());
		obj.put("remarks", dmVmaSecurityOffice.getRemarks());
		obj.put("modifiedDate", dmVmaSecurityOffice.getModifiedDate());
		obj.put("isDelete", dmVmaSecurityOffice.getIsDelete());
		obj.put("syncVersion", dmVmaSecurityOffice.getSyncVersion());

		return obj;
	}

	public static void actionUpdateVmaSecurityOffice(ThemeDisplay themeDisplay,
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
		String securityOfficeCode = DanhMucUtils.getString(actionRequest,
				"securityOfficeCode");
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

		try {
			if (danhdauXoa.length() > 0) {
				log.info("messageType101  Xoa");
				DmVmaSecurityOffice dmVmaSecurityOffice = DmVmaSecurityOfficeLocalServiceUtil
						.deleteVmaSecurityOffice(fromMaritimeCode,
								securityOfficeCode,
								DanhMucUtils.createNewSyncVersion(syncVersion));

				DmHistorySecurityOffice dmHistorySecurityOffice = DmHistorySecurityOfficeLocalServiceUtil
						.deleteHistorySecurityOffice(fromMaritimeCode,
								securityOfficeCode, DanhMucUtils
										.getLastSyncVersion(dmVmaSecurityOffice
												.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_vma_security_office", dmVmaSecurityOffice
								.getSecurityOfficeCode(), StringPool.BLANK);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"dm_history_security_office", dmHistorySecurityOffice
								.getSecurityOfficeCode(), StringPool.BLANK);
			} else if (capmoi.length() > 0) {
				log.info("messageType101 Them");
				securityOfficeCode = ReportsBusinessUtils.createCode(
						maritimeReference, "SEC", String
								.valueOf(CounterLocalServiceUtil
										.increment("code#"
												+ DmVmaSecurityOffice.class
														.getName())), 5);
				DmVmaSecurityOffice dmVmaSecurityOffice = DmVmaSecurityOfficeLocalServiceUtil
						.updateVmaSecurityOffice(fromMaritimeCode,
								securityOfficeCode, companyName,
								companyAddress, contactEmail, faxNo, remarks,
								maritimeCode, telNo, "1|");
				DmHistorySecurityOffice dmHistorySecurityOffice = DmHistorySecurityOfficeLocalServiceUtil
						.updateHistorySecurityOffice(fromMaritimeCode,
								dmVmaSecurityOffice.getSecurityOfficeCode(),
								companyName, companyAddress, contactEmail,
								faxNo, remarks, maritimeCode, telNo, "1");

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_vma_security_office", dmVmaSecurityOffice
								.getSecurityOfficeCode(), StringPool.BLANK);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"dm_history_security_office", dmHistorySecurityOffice
								.getSecurityOfficeCode(), StringPool.BLANK);
			} else if (capsua.length() > 0) {
				log.info("messageType101  Sua");
				DmVmaSecurityOffice dmVmaSecurityOffice = DmVmaSecurityOfficeLocalServiceUtil
						.updateVmaSecurityOffice(fromMaritimeCode,
								securityOfficeCode, companyName,
								companyAddress, contactEmail, faxNo, remarks,
								maritimeCode, telNo,
								DanhMucUtils.createNewSyncVersion(syncVersion));
				DmHistorySecurityOffice dmHistorySecurityOffice = DmHistorySecurityOfficeLocalServiceUtil
						.updateHistorySecurityOffice(fromMaritimeCode,
								securityOfficeCode, companyName,
								companyAddress, contactEmail, faxNo, remarks,
								maritimeCode, telNo, DanhMucUtils
										.getLastSyncVersion(dmVmaSecurityOffice
												.getSyncVersion()));

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_vma_security_office", dmVmaSecurityOffice
								.getSecurityOfficeCode(), StringPool.BLANK);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"dm_history_security_office", dmHistorySecurityOffice
								.getSecurityOfficeCode(), StringPool.BLANK);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		//todo tinh sau
	}
}
