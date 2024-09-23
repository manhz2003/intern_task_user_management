package vn.gt.portlet.danhmuc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaPilotViolation;
import vn.gt.dao.danhmuc.service.DmVmaPilotLocalServiceUtil;
import vn.gt.dao.danhmuc.service.VmaPilotViolationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.utils.FormatData;
import vn.gt.utils.PageType;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
//import com.fds.nsw.kernel.dao.orm.Criterion;
//import com.fds.nsw.kernel.dao.orm.DynamicQuery;
//import com.fds.nsw.kernel.dao.orm.DynamicQueryFactoryUtil;
//import com.fds.nsw.kernel.dao.orm.RestrictionsFactoryUtil;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;




import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaPilotViolationUtils {

	

	public static JSONObject getVmaPilotViolations(long userId,
			String maritimeCode, Date violationDate1, Date violationDate2,
			int start, int end) throws SystemException {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<VmaPilotViolation> vmaPilotViolations = new ArrayList<VmaPilotViolation>();
		if (Validator.isNotNull(maritimeCode)) {
			//todo dynamic query
//			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
//					VmaPilotViolation.class, "vma_pilot_violation",
//					DanhMucRiengAction.class.getClassLoader());
//			Criterion criterion = null;
//			criterion = RestrictionsFactoryUtil.eq(
//					"vma_pilot_violation.MaritimeCode", maritimeCode);
			if (Validator.isNotNull(violationDate1)
					& Validator.isNotNull(violationDate2)) {
//				criterion = RestrictionsFactoryUtil.and(criterion,
//						RestrictionsFactoryUtil.between(
//								"vma_pilot_violation.ViolationDate",
//								violationDate1, violationDate2));
			}
			Object criterion = null;
			if (criterion != null) {
//				dynamicQuery.add(criterion);
//				vmaPilotViolations = VmaPilotViolationLocalServiceUtil
//						.dynamicQuery(dynamicQuery, start, end);
//				total = DmVmaPilotLocalServiceUtil
//						.dynamicQueryCount(dynamicQuery);
			} else {
				vmaPilotViolations = VmaPilotViolationLocalServiceUtil
						.findByMaritimeCode(maritimeCode, start, end);
				total = vmaPilotViolations.size();
			}
		} else {
			vmaPilotViolations = VmaPilotViolationLocalServiceUtil
					.getVmaPilotViolations(start, end);
			total = vmaPilotViolations.size();
		}

		for (VmaPilotViolation vmaPilotViolation : vmaPilotViolations) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", vmaPilotViolation.getId());
			obj.put("pilotCode", vmaPilotViolation.getPilotCode());
			obj.put("violationDate", vmaPilotViolation.getViolationDate());
			obj.put("violationDescription",
					vmaPilotViolation.getViolationDescription());
			obj.put("maritimeCode", vmaPilotViolation.getMaritimeCode());
			obj.put("troubleshooting", vmaPilotViolation.getTroubleshooting());
			obj.put("role",
					vmaPilotViolation.getMaritimeCode().equals("")
							|| vmaPilotViolation.getMaritimeCode().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")));
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailVmaPilotViolation(long id)
			throws PortalException, SystemException {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		VmaPilotViolation vmaPilotViolation = VmaPilotViolationLocalServiceUtil
				.getVmaPilotViolation(id);
		obj.put("id", vmaPilotViolation.getId());
		obj.put("pilotCode", vmaPilotViolation.getPilotCode());
		obj.put("violationDate", vmaPilotViolation.getViolationDate());
		obj.put("violationDescription",
				vmaPilotViolation.getViolationDescription());
		obj.put("maritimeCode", vmaPilotViolation.getMaritimeCode());
		obj.put("troubleshooting", vmaPilotViolation.getTroubleshooting());

		return obj;
	}

	public static JSONObject actionUpdateVmaPilotViolation(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
			throws Exception {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String maritimeCode = userPort.getPortCode();
		String fromMaritimeCode = maritimeCode;
		String maritimeCodeRequest = DanhMucUtils.getString(actionRequest,
				"maritimeCode");
		if (Validator.isNotNull(maritimeCodeRequest)
				& !maritimeCode.equals(maritimeCodeRequest)) {
			return null;
		}
		long id = ParamUtil.getLong(actionRequest, "id");
		String pilotCode = DanhMucUtils.getString(actionRequest, "pilotCode");
		String violationDate = DanhMucUtils.getString(actionRequest,
				"violationDate");
		String violationDescription = DanhMucUtils.getString(actionRequest,
				"violationDescription");
		String troubleshooting = DanhMucUtils.getString(actionRequest,
				"Troubleshooting");

		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);

		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaPilotViolation vmaPilotViolation = null;
		try {
			if (danhdauXoa.length() > 0) {
				log.info("messageType  Xoa");
				vmaPilotViolation = VmaPilotViolationLocalServiceUtil
						.deleteVmaPilotViolation(fromMaritimeCode, id);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"vma_pilot_violation",
						vmaPilotViolation.getPilotCode(), StringPool.BLANK);
			} else if (capmoi.length() > 0) {
				log.info("messageType Them");
				vmaPilotViolation = VmaPilotViolationLocalServiceUtil
						.updateVmaPilotViolation(
								fromMaritimeCode,
								id,
								maritimeCode,
								pilotCode,
								(Validator.isNotNull(violationDate) && !violationDate
										.isEmpty()) ? FormatData.formatDateShort2
										.parse(violationDate) : null,
								violationDescription, troubleshooting);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"vma_pilot_violation",
						vmaPilotViolation.getPilotCode(), StringPool.BLANK);
			} else if (capsua.length() > 0) {
				log.info("messageType Sua");
				pilotCode = String.valueOf(CounterLocalServiceUtil
						.increment("code#" + "vma_pilot_violation"));
				vmaPilotViolation = VmaPilotViolationLocalServiceUtil
						.updateVmaPilotViolation(
								fromMaritimeCode,
								id,
								maritimeCode,
								pilotCode,
								(Validator.isNotNull(violationDate) && !violationDate
										.isEmpty()) ? FormatData.formatDateShort2
										.parse(violationDate) : null,
								violationDescription, troubleshooting);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"vma_pilot_violation",
						vmaPilotViolation.getPilotCode(), StringPool.BLANK);
			}
			if (vmaPilotViolation != null) {
				result.put("id", vmaPilotViolation.getId());
				result.put("maritimeCode", vmaPilotViolation.getMaritimeCode());
				result.put("pilotCode", vmaPilotViolation.getPilotCode());
				result.put("violationDate",
						vmaPilotViolation.getViolationDate());
				result.put("violationDescription",
						vmaPilotViolation.getViolationDescription());
				result.put("troubleshooting",
						vmaPilotViolation.getTroubleshooting());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		//todo tinh sau
		return result;
	}
}
