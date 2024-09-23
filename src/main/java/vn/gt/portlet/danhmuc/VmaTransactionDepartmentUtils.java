package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaTransactionDepartment;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.VmaTransactionDepartmentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.PageType;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaTransactionDepartmentUtils {

	

	public static JSONObject getVmaTransactionDepartments(long userId,
			String portOfAuthority, String departmentName, int start, int end)
			throws SystemException {
		List<VmaTransactionDepartment> vmaTransactionDepartments = VmaTransactionDepartmentLocalServiceUtil
				.findVmaTransactionDepartments(portOfAuthority, departmentName,
						start, end);
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();

		for (VmaTransactionDepartment vmaTransactionDepartment : vmaTransactionDepartments) {
			JSONObject object = JSONFactoryUtil.createJSONObject();
			object.put("id", vmaTransactionDepartment.getId());
			object.put("portOfAuthority",
					vmaTransactionDepartment.getPortofAuthority());
			object.put("departmentCode",
					vmaTransactionDepartment.getDepartmentCode());
			object.put("departmentName",
					vmaTransactionDepartment.getDepartmentName());
			object.put("sequenceNo", vmaTransactionDepartment.getSequenceNo());
			object.put("transactionTypeVND",
					vmaTransactionDepartment.getTransactionTypeVND());
			object.put("transactionTypeUSD",
					vmaTransactionDepartment.getTransactionTypeUSD());
			object.put("modifiedDate",
					vmaTransactionDepartment.getModifiedDate());
			object.put("transactionSettlement",
					vmaTransactionDepartment.getTransactionSettlement());
			object.put(
					"role",
					vmaTransactionDepartment.getPortofAuthority().equals("")
							|| vmaTransactionDepartment.getPortofAuthority()
									.equals(DanhMucUtils.getMaritimeCurrent(
											userId).getString("maritimeCode")));

			array.put(object);
		}
		result.put("total", VmaTransactionDepartmentLocalServiceUtil
				.coutVmaTransactionDepartments(portOfAuthority, departmentName));
		result.put("data", array);
		return result;
	}

	public static JSONObject getDetailVmaTransactionDepartment(
			String departmentCode) {
		VmaTransactionDepartment vmaTransactionDepartment = VmaTransactionDepartmentLocalServiceUtil
				.fetchVmaTransactionDepartment(departmentCode);
		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put("id", vmaTransactionDepartment.getId());
		result.put("portOfAuthority",
				vmaTransactionDepartment.getPortofAuthority());
		result.put("departmentCode",
				vmaTransactionDepartment.getDepartmentCode());
		result.put("departmentName",
				vmaTransactionDepartment.getDepartmentName());
		result.put("sequenceNo", vmaTransactionDepartment.getSequenceNo());
		result.put("transactionTypeVND",
				vmaTransactionDepartment.getTransactionTypeVND());
		result.put("transactionTypeUSD",
				vmaTransactionDepartment.getTransactionTypeUSD());
		result.put("modifiedDate", vmaTransactionDepartment.getModifiedDate());
		result.put("transactionSettlement",
				vmaTransactionDepartment.getTransactionSettlement());

		return result;
	}

	public static void actionUpdateVmaTransactionDepartment(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String maritimeCode = userPort.getPortCode();
		String maritimeReference = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode).getMaritimeReference();
		String departmentCode = DanhMucUtils.getString(actionRequest,
				"departmentCode");
		String departmentName = DanhMucUtils.getString(actionRequest,
				"departmentName");
		String portOfAuthority = maritimeCode;
		String transactionTypeVND = DanhMucUtils.getString(actionRequest,
				"transactionTypeVND");
		String transactionTypeUSD = DanhMucUtils.getString(actionRequest,
				"transactionTypeUSD");
		int sequenceNo = 0;

		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);
		try {
			VmaTransactionDepartment vmaTransactionDepartment = null;
			if (capmoi.length() > 0) {
				do {
					sequenceNo = (int) ReportsBusinessUtils.taoGiaTriThamSo(
							"VmaTransactionDepartment", maritimeReference);
					departmentCode = maritimeReference
							+ String.valueOf(sequenceNo);

					vmaTransactionDepartment = VmaTransactionDepartmentLocalServiceUtil
							.fetchVmaTransactionDepartment(departmentCode);
				} while (vmaTransactionDepartment != null);
				if (sequenceNo != 0) {
					log.info("======= Them moi VmaTransactionDepartment =========");
					vmaTransactionDepartment = VmaTransactionDepartmentLocalServiceUtil
							.updateVmaTransactionDepartment(portOfAuthority,
									departmentCode, departmentName, sequenceNo,
									transactionTypeVND, transactionTypeUSD);

					VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
							.getUserId(),
							userSign != null ? userSign.getModifyuser()
									: StringPool.BLANK, LogsConstant.THEM,
							"vma_transaction_department",
							vmaTransactionDepartment.getDepartmentCode(),
							vmaTransactionDepartment.getDepartmentName());
				}
			} else if (capsua.length() > 0) {
				log.info("======= Sua VmaTransactionDepartment =========");
				vmaTransactionDepartment = VmaTransactionDepartmentLocalServiceUtil
						.updateVmaTransactionDepartment(portOfAuthority,
								departmentCode, departmentName, sequenceNo,
								transactionTypeVND, transactionTypeUSD);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"vma_transaction_department", vmaTransactionDepartment
								.getDepartmentCode(), vmaTransactionDepartment
								.getDepartmentName());
			} else if (danhdauXoa.length() > 0) {
				log.info("======= Khong cho xoa VmaTransactionDepartment =========");
				/*
				 * VmaTransactionDepartmentLocalServiceUtil
				 * .deleteVmaTransactionDepartment(id);
				 */
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static JSONObject getDepartmentCodeByUserId(long userId) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		UserPort userPort = UserPortLocalServiceUtil.findByUserId(userId);
		try {
			result.put("departmentCode", userPort.getDepartmentCode());
		} catch (Exception e) {
			// nothing to do
		}
		return result;
	}
}
