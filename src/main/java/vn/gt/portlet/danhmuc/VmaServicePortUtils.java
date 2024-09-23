package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaServicePort;
import vn.gt.dao.danhmuc.service.VmaServicePortLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.utils.PageType;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;




import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaServicePortUtils {

	

	public static JSONObject getServicePorts(int start, int end)
			throws SystemException {
		List<VmaServicePort> lstServicePorts = VmaServicePortLocalServiceUtil
				.getVmaServicePorts(start, end);
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = VmaServicePortLocalServiceUtil.countVmaServicePort();
		if (lstServicePorts != null) {
			for (VmaServicePort vmaServicePort : lstServicePorts) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				obj.put("ID", vmaServicePort.getId());
				obj.put("MaritimeCode", vmaServicePort.getMaritimeCode());
				obj.put("ServicePortCompanyCode",
						vmaServicePort.getServicePortCompanyCode());
				obj.put("ServicePortCompanyName",
						vmaServicePort.getServicePortCompanyName());
				obj.put("CompanyAddress", vmaServicePort.getCompanyAddress());
				obj.put("ContactEmail", vmaServicePort.getContactEmail());
				obj.put("TelNo", vmaServicePort.getTelNo());
				obj.put("FaxNo", vmaServicePort.getFaxNo());
				obj.put("Remarks", vmaServicePort.getRemarks());
				obj.put("IsDelete", vmaServicePort.getIsDelete());
				obj.put("MarkedAsDelete", vmaServicePort.getMarkedAsDelete());
				try {
					obj.put("ModifiedDate", vmaServicePort.getModifiedDate());
				} catch (Exception e) {
					obj.put("ModifiedDate", StringPool.BLANK);
				}
				try {
					obj.put("RequestedDate", vmaServicePort.getRequestedDate());
				} catch (Exception e) {
					obj.put("RequestedDate", StringPool.BLANK);
				}
				obj.put("SyncVersion", vmaServicePort.getSyncVersion());
				array.put(obj);
			}
			result.put("total", total);
			result.put("data", array);

			return result;
		}
		return null;
	}

	public static JSONObject actionUpdateVmaServicePort(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String maritimeCode = userPort.getPortCode();
		String servicePortCompanyCode = DanhMucUtils.getString(actionRequest,
				"ServicePortCompanyCode");
		String servicePortCompanyName = DanhMucUtils.getString(actionRequest,
				"ServicePortCompanyName");
		String companyAddress = DanhMucUtils.getString(actionRequest,
				"CompanyAddress");
		String contactEmail = DanhMucUtils.getString(actionRequest,
				"ContactEmail");
		String telNo = DanhMucUtils.getString(actionRequest, "TelNo");
		String faxNo = DanhMucUtils.getString(actionRequest, "FaxNo");
		String remarks = DanhMucUtils.getString(actionRequest, "Remarks");
		String syncVersion = DanhMucUtils.getString(actionRequest,
				"SyncVersion");

		int status = 0;
		String message = "";

		String iscreate = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String isedit = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String isdelete = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);
		try {
			if (isdelete.length() > 0) {
				long id = ParamUtil.getLong(actionRequest, "ID");
				VmaServicePortLocalServiceUtil.deleteVmaServicePort(id);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"vma_service_port", StringPool.BLANK,
						"XOA VmaServicePort ID = " + id);
				status = 1;
				message += "Xoa VmaServicePort thanh cong";
			} else if (iscreate.length() > 0) {
				log.info("==============  Them: VmaServicePort");
				VmaServicePort vmaServicePort = new VmaServicePort();

				vmaServicePort.setMaritimeCode(maritimeCode);
				vmaServicePort
						.setServicePortCompanyCode(servicePortCompanyCode);
				vmaServicePort
						.setServicePortCompanyName(servicePortCompanyName);
				vmaServicePort.setCompanyAddress(companyAddress);
				vmaServicePort.setContactEmail(contactEmail);
				vmaServicePort.setTelNo(telNo);
				vmaServicePort.setFaxNo(faxNo);
				vmaServicePort.setRemarks(remarks);

				VmaServicePort vmaServicePort2 = VmaServicePortLocalServiceUtil
						.addVmaServicePort(vmaServicePort);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"vma_service_port", vmaServicePort2
								.getServicePortCompanyCode(), vmaServicePort2
								.getServicePortCompanyName());

				status = 1;
				message += "Them VmaServicePort thanh cong";
			} else if (isedit.length() > 0) {
				long id = ParamUtil.getLong(actionRequest, "ID");
				VmaServicePort vmaServicePort = VmaServicePortLocalServiceUtil
						.getVmaServicePort(id);

				vmaServicePort.setMaritimeCode(maritimeCode);
				vmaServicePort
						.setServicePortCompanyCode(servicePortCompanyCode);
				vmaServicePort
						.setServicePortCompanyName(servicePortCompanyName);
				vmaServicePort.setCompanyAddress(companyAddress);
				vmaServicePort.setContactEmail(contactEmail);
				vmaServicePort.setTelNo(telNo);
				vmaServicePort.setFaxNo(faxNo);
				vmaServicePort.setRemarks(remarks);

				vmaServicePort.setSyncVersion(DanhMucUtils
						.createNewSyncVersion(syncVersion));

				VmaServicePort vmaServicePort2 = VmaServicePortLocalServiceUtil
						.updateVmaServicePort(vmaServicePort);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"vma_service_port", vmaServicePort2
								.getServicePortCompanyCode(), vmaServicePort2
								.getServicePortCompanyName());
				status = 1;
				message += "Sua VmaServicePort thanh cong";
			}
			//todo tinh sau
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("status", status);
		result.put("message", DanhMucUtils.encodeUTF8(message));
		return result;
	}

	public static JSONObject getDetailVmaServicePorts(long id)
			throws PortalException, SystemException {
		VmaServicePort vmaServicePort = VmaServicePortLocalServiceUtil
				.getVmaServicePort(id);

		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put("ID", vmaServicePort.getId());
		obj.put("MaritimeCode", vmaServicePort.getMaritimeCode());
		obj.put("ServicePortCompanyCode",
				vmaServicePort.getServicePortCompanyCode());
		obj.put("ServicePortCompanyName",
				vmaServicePort.getServicePortCompanyName());
		obj.put("CompanyAddress", vmaServicePort.getCompanyAddress());
		obj.put("ContactEmail", vmaServicePort.getContactEmail());
		obj.put("TelNo", vmaServicePort.getTelNo());
		obj.put("FaxNo", vmaServicePort.getFaxNo());
		obj.put("Remarks", vmaServicePort.getRemarks());
		obj.put("IsDelete", vmaServicePort.getIsDelete());
		obj.put("MarkedAsDelete", vmaServicePort.getMarkedAsDelete());
		try {
			obj.put("ModifiedDate", vmaServicePort.getModifiedDate());
		} catch (Exception e) {
			obj.put("ModifiedDate", StringPool.BLANK);
		}
		try {
			obj.put("RequestedDate", vmaServicePort.getRequestedDate());
		} catch (Exception e) {
			obj.put("RequestedDate", StringPool.BLANK);
		}
		obj.put("SyncVersion", vmaServicePort.getSyncVersion());

		return obj;
	}

}