package vn.gt.portlet.phuongtien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.DmCertificate;


import vn.gt.dao.danhmucgt.service.DmCertificateLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaCertificate;


import vn.gt.dao.noticeandmessage.service.VmaCertificateLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import org.json.JSONException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaCertificateUtils
 {

	

	public static JSONObject doFind(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws SystemException {
		HttpServletRequest request = resourceRequest;

		String nameOfShip = ParamUtil.getString(request, "nameOfShip",
				StringPool.BLANK);

		int start = GetterUtil.getInteger(request.getParameter("start"), 0);

		int end = GetterUtil.getInteger(request.getParameter("end"), 15);

		String certificateName = ParamUtil.getString(resourceRequest,
				"certificateName", StringPool.BLANK);

		String certificateExpiredDate = ParamUtil.getString(resourceRequest,
				"certificateExpiredDate", StringPool.BLANK);

		String certificateIssueDate = ParamUtil.getString(resourceRequest,
				"certificateIssueDate", StringPool.BLANK);

		String approvalName = ParamUtil.getString(resourceRequest,
				"approvalName", StringPool.BLANK);

		String isExamined = ParamUtil.getString(resourceRequest, "isExamined",
				StringPool.BLANK);

		String examinationDate = ParamUtil.getString(resourceRequest,
				"examinationDate", StringPool.BLANK);

		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);

		List<VmaCertificate> vmaCertificates = null;

		vmaCertificates = VmaCertificateLocalServiceUtil.findVmaCertificates(
				nameOfShip, certificateExpiredDate, certificateIssueDate,
				approvalName, isExamined, examinationDate, imoNumber, callSign,
				null, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (vmaCertificates == null || vmaCertificates.isEmpty()) {
			vmaCertificates = VmaCertificateLocalServiceUtil
					.findVmaCertificates(nameOfShip, certificateExpiredDate,
							certificateIssueDate, approvalName, isExamined,
							examinationDate, null, null, registryNumber,
							QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		List<DmCertificate> dmCertificates = DmCertificateLocalServiceUtil
				.findDmCertificates(certificateName, start, end);

		JSONArray data = JSONFactoryUtil.createJSONArray();

		if ((dmCertificates != null && !dmCertificates.isEmpty())
				|| (vmaCertificates != null && !vmaCertificates.isEmpty())) {
			for (DmCertificate dmCertificate : dmCertificates) {
				JSONObject object = JSONFactoryUtil.createJSONObject();
				object.put("certificateName",
						dmCertificate.getCertificateName());
				object.put("dmCertificateId", dmCertificate.getId());
				object.put("certificateCode",
						dmCertificate.getCertificateCode());
				for (VmaCertificate vmaCertificate : vmaCertificates) {
					if (dmCertificate.getCertificateCode().equals(
							vmaCertificate.getCertificateCode())) {
						try {
							object.put("certificateExpiredDate", FormatData
									.parseDateToTringType3(vmaCertificate
											.getCertificateExpiredDate()));
						} catch (Exception e) {
							// nothing to do
						}
						try {
							object.put("certificateIssueDate", FormatData
									.parseDateToTringType3(vmaCertificate
											.getCertificateIssueDate()));
						} catch (Exception e) {

						}
						try {
							object.put("examinationDate", FormatData
									.parseDateToTringType3(vmaCertificate
											.getExaminationDate()));
						} catch (Exception e) {

						}
						object.put("isExamined", vmaCertificate.getIsExamined());
						object.put("nameOfShip", vmaCertificate.getNameOfShip());
						object.put("vmaCertificateId",
								vmaCertificate.getId());
						object.put("imoNumber", vmaCertificate.getImoNumber());
						object.put("callSign", vmaCertificate.getCallSign());
						object.put("registryNumber",
								vmaCertificate.getRegistryNumber());
						break;
					}
				}

				data.put(object);
			}
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("data", data);
		result.put("total", DmCertificateLocalServiceUtil
				.countDmCertificates(certificateName));

		return result;

	}

	public static long doCount(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);

		String certificateExpiredDate = ParamUtil.getString(resourceRequest,
				"certificateExpiredDate", StringPool.BLANK);

		String certificateIssueDate = ParamUtil.getString(resourceRequest,
				"certificateIssueDate", StringPool.BLANK);

		String approvalName = ParamUtil.getString(resourceRequest,
				"approvalName", StringPool.BLANK);

		String isExamined = ParamUtil.getString(resourceRequest, "isExamined",
				StringPool.BLANK);

		String examinationDate = ParamUtil.getString(resourceRequest,
				"examinationDate", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);
		long total = 0;
		try {
			total = VmaCertificateLocalServiceUtil.countVmaCertificates(
					nameOfShip, certificateExpiredDate, certificateIssueDate,
					approvalName, isExamined, examinationDate, imoNumber,
					callSign, registryNumber);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return total;
	}

	private static List<VmaCertificate> getObjectsFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
			throws JSONException {

		String vmaCertificateList = ParamUtil.getString(actionRequest, "json");

		List<VmaCertificate> vmaCertificates = new ArrayList<VmaCertificate>();

		JSONArray array = null;

		if (Validator.isNotNull(vmaCertificateList)) {
			array = JSONFactoryUtil.createJSONArray(vmaCertificateList);
		}

		if (array != null) {
			for (int i = 0; i < array.length(); i++) {
				VmaCertificate vmaCertificate = new VmaCertificate();

				JSONObject object = array.getJSONObject(i);

				if (object.has("nameOfShip")) {
					String nameOfShip = object.getString("nameOfShip");

					vmaCertificate.setNameOfShip(nameOfShip);
				}

				if (object.has("certificateExpiredDate")) {
					String certificateExpiredDate = object
							.getString("certificateExpiredDate");

					if (Validator.isNotNull(certificateExpiredDate)) {
						Date date;
						try {
							date = FormatData.formatDateShort3
									.parse(certificateExpiredDate);
							vmaCertificate.setCertificateExpiredDate(date);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							log.error(e.getMessage());
						}

					}
				}

				if (object.has("certificateIssueDate")) {
					String certificateIssueDate = object
							.getString("certificateIssueDate");

					if (Validator.isNotNull(certificateIssueDate)) {
						Date date;
						try {
							date = FormatData.formatDateShort3
									.parse(certificateIssueDate);
							vmaCertificate.setCertificateIssueDate(date);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							log.error(e.getMessage());
						}

					}
				}

				if (object.has("examinationDate")) {
					String examinationDate = object
							.getString("examinationDate");

					if (Validator.isNotNull(examinationDate)) {
						Date date;
						try {
							date = FormatData.formatDateShort3
									.parse(examinationDate);
							vmaCertificate.setExaminationDate(date);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							log.error(e.getMessage());
						}

					}
				}

				if (object.has("approvalName")) {
					String approvalName = object.getString("approvalName");

					vmaCertificate.setApprovalName(approvalName);
				}

				if (object.has("isExamined")) {
					int isExamined = object.getInt("isExamined");

					vmaCertificate.setIsExamined(isExamined);
				}

				if (object.has("vmaCertificateId")) {
					int vmaCertificateId = object.getInt("vmaCertificateId");

					vmaCertificate.setId(vmaCertificateId);
				}

				if (object.has("certificateCode")) {
					String certificateCode = object
							.getString("certificateCode");

					vmaCertificate.setCertificateCode(certificateCode);
				}

				if (object.has("imoNumber")) {
					String imoNumber = object.getString("imoNumber");

					vmaCertificate.setImoNumber(imoNumber);
				}

				if (object.has("callSign")) {
					String callSign = object.getString("callSign");

					vmaCertificate.setCallSign(callSign);
				}

				if (object.has("registryNumber")) {
					String registryNumber = object.getString("registryNumber");

					vmaCertificate.setRegistryNumber(registryNumber);
				}

				vmaCertificates.add(vmaCertificate);
			}
		}

		return vmaCertificates;
	}

	private static List<DmCertificate> getObjectsFromRequest2(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
			throws JSONException {

		String dmCertificateList = ParamUtil.getString(actionRequest, "json");

		List<DmCertificate> dmCertificates = new ArrayList<DmCertificate>();

		JSONArray array = null;

		if (Validator.isNotNull(dmCertificateList)) {
			array = JSONFactoryUtil.createJSONArray(dmCertificateList);
		}

		if (array != null) {
			for (int i = 0; i < array.length(); i++) {
				DmCertificate dmCertificate = new DmCertificate();

				JSONObject object = array.getJSONObject(i);

				if (object.has("certificateCode")) {
					String certificateCode = object
							.getString("certificateCode");

					dmCertificate.setCertificateCode(certificateCode);
				}

				if (object.has("certificateName")) {
					String certificateName = object
							.getString("certificateName");

					dmCertificate.setCertificateName(certificateName);
				}

				if (object.has("dmCertificateId")) {
					long dmCertificateId = object.getLong("dmCertificateId");

					dmCertificate.setId(dmCertificateId);
				}

				dmCertificates.add(dmCertificate);
			}
		}

		return dmCertificates;
	}

	public static JSONObject updateVmaCertificate_DmCertificate(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
			throws JSONException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<VmaCertificate> vmaCertificates = getObjectsFromRequest(
				themeDisplay, actionRequest);

		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONArray array2 = JSONFactoryUtil.createJSONArray();

		if (vmaCertificates == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			for (VmaCertificate vmaCertificate : vmaCertificates) {
				VmaCertificate vmaCertificate2 = VmaCertificateLocalServiceUtil
						.updateVmaCertificate(vmaCertificate.getId(),
								vmaCertificate.getCertificateCode(),
								vmaCertificate.getNameOfShip(),
								vmaCertificate.getCertificateExpiredDate(),
								vmaCertificate.getCertificateIssueDate(),
								vmaCertificate.getExaminationDate(),
								vmaCertificate.getApprovalName(),
								vmaCertificate.getIsExamined(),
								vmaCertificate.getImoNumber(),
								vmaCertificate.getRegistryNumber(),
								vmaCertificate.getCallSign());
				/*
				 * JSONObject object = VMAUtils.object2Json(vmaCertificate2,
				 * VmaCertificate.class);
				 * 
				 * array.put(object);
				 */
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}

		result.put("vmaCertificates", array);
		result.put("dmCertificates", array2);

		return result;
	}
}
