package vn.gt.portlet.phuongtien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import com.fds.nsw.nghiepvu.model.*;
import jakarta.servlet.http.HttpServletRequest;

import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmCertificateLocalServiceUtil;


import vn.gt.dao.noticeandmessage.service.VmaShipCertificateLocalServiceUtil;
import vn.gt.dao.result.service.ResultCertificateLocalServiceUtil;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
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
public class VmaShipCertificateUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaShipCertificateId = ParamUtil.getLong(request,
				"vmaShipCertificateId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaShipCertificate vmaShipCertificate = VmaShipCertificateLocalServiceUtil
					.getVmaShipCertificate(vmaShipCertificateId);
			result = VMAUtils.object2Json(vmaShipCertificate,
					VmaShipCertificate.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id mtgateway maritimeCode imoNumber callSign vrCode registryNumber
	 * certificateCode certificateName certificateOrder certificateNo
	 * description certificateIssueDate certificateExpiredDate examinationDate
	 * comment isExamined mandatory
	 */
	public static VmaShipCertificate getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		long id = GetterUtil.getLong(
				request.getParameter("vmaShipCertificateId"), -1);
		VmaShipCertificate vmaShipCertificate = null;
		if (id > 0) {
			try {
				vmaShipCertificate = VmaShipCertificateLocalServiceUtil
						.getVmaShipCertificate(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaShipCertificate = new VmaShipCertificate();
		}
		int mtgateway = GetterUtil.getInteger(
				request.getParameter("mtgateway"), -1);
		if (mtgateway >= 0) {
			vmaShipCertificate.setMtgateway(mtgateway);
		}
		String maritimeCode = ParamUtil.getString(actionRequest,
				"maritimeCode", StringPool.BLANK);
		if (Validator.isNotNull(maritimeCode)) {
			vmaShipCertificate.setMaritimeCode(maritimeCode);
		}
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
				StringPool.BLANK);
		if (Validator.isNotNull(imoNumber)) {
			vmaShipCertificate.setImoNumber(imoNumber);
		}
		String callSign = VMAUtils.getString(actionRequest, "callSign",
				StringPool.BLANK);
		if (Validator.isNotNull(callSign)) {
			vmaShipCertificate.setCallSign(callSign);
		}
		String vrCode = ParamUtil.getString(actionRequest, "vrCode",
				StringPool.BLANK);
		if (Validator.isNotNull(vrCode)) {
			vmaShipCertificate.setVrCode(vrCode);
		}
		String registryNumber = VMAUtils.getString(actionRequest,
				"registryNumber", StringPool.BLANK);
		if (Validator.isNotNull(registryNumber)) {
			vmaShipCertificate.setRegistryNumber(registryNumber);
		}
		String certificateCode = ParamUtil.getString(actionRequest,
				"certificateCode", StringPool.BLANK);
		if (Validator.isNotNull(certificateCode)) {
			vmaShipCertificate.setCertificateCode(certificateCode);
		}
		String certificateName = VMAUtils.getString(actionRequest,
				"certificateName", StringPool.BLANK);
		if (Validator.isNotNull(certificateName)) {
			vmaShipCertificate.setCertificateName(certificateName);
		}
		int certificateOrder = GetterUtil.getInteger(
				request.getParameter("certificateOrder"), -1);
		if (certificateOrder >= 0) {
			vmaShipCertificate.setCertificateOrder(certificateOrder);
		}
		String certificateNo = ParamUtil.getString(actionRequest,
				"certificateNo", StringPool.BLANK);
		if (Validator.isNotNull(certificateNo)) {
			vmaShipCertificate.setCertificateNo(certificateNo);
		}
		String description = VMAUtils.getString(actionRequest, "description",
				StringPool.BLANK);
		if (Validator.isNotNull(description)) {
			vmaShipCertificate.setDescription(description);
		}
		String certificateIssueDate = ParamUtil.getString(actionRequest,
				"certificateIssueDate", StringPool.BLANK);
		if (Validator.isNotNull(certificateIssueDate)) {
			try {
				Date date = FormatData.formatDateShort3
						.parse(certificateIssueDate);
				vmaShipCertificate.setCertificateIssueDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String certificateExpiredDate = ParamUtil.getString(actionRequest,
				"certificateExpiredDate", StringPool.BLANK);
		if (Validator.isNotNull(certificateExpiredDate)) {
			try {
				Date date = FormatData.formatDateShort3
						.parse(certificateExpiredDate);
				vmaShipCertificate.setCertificateExpiredDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String examinationDate = ParamUtil.getString(actionRequest,
				"examinationDate", StringPool.BLANK);
		if (Validator.isNotNull(examinationDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(examinationDate);
				vmaShipCertificate.setExaminationDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String comment = VMAUtils.getString(actionRequest, "comment",
				StringPool.BLANK);
		if (Validator.isNotNull(comment)) {
			vmaShipCertificate.setComment(comment);
		}
		int isExamined = GetterUtil.getInteger(
				request.getParameter("isExamined"), -1);
		if (isExamined >= 0) {
			vmaShipCertificate.setIsExamined(isExamined);
		}
		int mandatory = GetterUtil.getInteger(
				request.getParameter("mandatory"), -1);
		if (mandatory >= 0) {
			vmaShipCertificate.setMandatory(mandatory);
		}
		
		long shipId = GetterUtil.getLong(request.getParameter("shipId"), -1);
		if(shipId > 0){
			vmaShipCertificate.setShipId((int) shipId);
		}
		
		return vmaShipCertificate;
	}

	public static JSONObject addVmaShipCertificate(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaShipCertificate vmaShipCertificate = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaShipCertificate == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaShipCertificate = VmaShipCertificateLocalServiceUtil
					.addVmaShipCertificate(vmaShipCertificate);
			result = VMAUtils.object2Json(vmaShipCertificate,
					VmaShipCertificate.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaShipCertificate(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaShipCertificate vmaShipCertificate = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaShipCertificate == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaShipCertificate = VmaShipCertificateLocalServiceUtil
					.updateVmaShipCertificate(vmaShipCertificate);
			result = VMAUtils.object2Json(vmaShipCertificate,
					VmaShipCertificate.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaShipCertificate(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest, "vmaShipCertificateId");
		if (id > 0) {
			try {
				VmaShipCertificateLocalServiceUtil.deleteVmaShipCertificate(id);
				return VMAUtils.createResponseMessage(
						LanguageUtil.get(themeDisplay.getLocale(), "success"),
						"", StringPool.BLANK);
			} catch (Exception e) {
				return VMAUtils.createResponseMessage(LanguageUtil.get(
						themeDisplay.getLocale(), "system_error"),
						"system_error", StringPool.BLANK);
			}
		} else {
			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "incorrect_identifier"),
					"incorrect_identifier", StringPool.BLANK);
		}
	}

	public static JSONObject doFind(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);
		long vmaShipCertificateId = GetterUtil.getLong(
				request.getParameter("vmaShipCertificateId"), -1);
		int mtgateway = GetterUtil.getInteger(
				request.getParameter("mtgateway"), -1);
		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);
		String vrCode = ParamUtil.getString(resourceRequest, "vrCode",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(resourceRequest,
				"registryNumber", StringPool.BLANK);
		String certificateCode = ParamUtil.getString(resourceRequest,
				"certificateCode", StringPool.BLANK);
		String certificateName = ParamUtil.getString(resourceRequest,
				"certificateName", StringPool.BLANK);
		int certificateOrder = GetterUtil.getInteger(
				request.getParameter("certificateOrder"), -1);
		String certificateNo = ParamUtil.getString(resourceRequest,
				"certificateNo", StringPool.BLANK);
		String description = ParamUtil.getString(resourceRequest,
				"description", StringPool.BLANK);
		String certificateIssueDate = ParamUtil.getString(resourceRequest,
				"certificateIssueDate", StringPool.BLANK);
		String certificateExpiredDate = ParamUtil.getString(resourceRequest,
				"certificateExpiredDate", StringPool.BLANK);
		String examinationDate = ParamUtil.getString(resourceRequest,
				"examinationDate", StringPool.BLANK);
		String comment = ParamUtil.getString(resourceRequest, "comment",
				StringPool.BLANK);
		int isExamined = GetterUtil.getInteger(
				request.getParameter("isExamined"), -1);
		int mandatory = GetterUtil.getInteger(
				request.getParameter("mandatory"), -1);
		try {
			return JSONFactoryUtil.createJSONObject();
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

	public static JSONObject getVmaShipCertificateByImoNumber_CallSign(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		JSONObject result = JSONFactoryUtil.createJSONObject();
		String imoNumber = ParamUtil.getString(request, "imoNumber");
		String callSign = VMAUtils.getString(request, "callSign", StringPool.BLANK);

		try {
			List<VmaShipCertificate> vmaShipCertificates = VmaShipCertificateLocalServiceUtil
					.findByIMONumber_CallSign(imoNumber, callSign);

			result.put("data", VMAUtils.object2Json(vmaShipCertificates,
					VmaShipCertificate.class));

			result.put("total", VmaShipCertificateLocalServiceUtil
					.countByIMONumber_CallSign(imoNumber, callSign));
		} catch (Exception e) {
			result.put("data", JSONFactoryUtil.createJSONObject());
			result.put("total", 0);
		}

		return result;
	}

	public static JSONObject addVmaShipCertificateFromResultCertificate(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
			throws SystemException, JSONException {

		HttpServletRequest request = actionRequest;

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());

		String imoNumber = ParamUtil.getString(request, "imoNumber");
		String callSign = VMAUtils.getString(request, "callSign", StringPool.BLANK);

		List<ResultCertificate> resultCertificates = ResultCertificateLocalServiceUtil
				.findResultCertificates(imoNumber, callSign, -1, -1);
		List<VmaShipCertificate> vmaShipCertificates = new ArrayList<VmaShipCertificate>();
		for (int i = resultCertificates.size() - 1; i >= 0; i--) {
			VmaShipCertificate vmaShipCertificate = new VmaShipCertificate();
			vmaShipCertificate.setCertificateCode(resultCertificates.get(i)
					.getCertificateCode());
			vmaShipCertificate.setCertificateNo(resultCertificates.get(i)
					.getCertificateNo());
			vmaShipCertificate.setCertificateOrder(resultCertificates.get(i)
					.getCertificateOrder());
			vmaShipCertificate.setCertificateIssueDate(resultCertificates
					.get(i).getCertificateIssueDate());
			vmaShipCertificate.setCertificateExpiredDate(resultCertificates
					.get(i).getCertificateExpiredDate());
			vmaShipCertificate.setExaminationDate(resultCertificates.get(i)
					.getExaminationDate());
			vmaShipCertificate.setMtgateway(1);
			vmaShipCertificate.setImoNumber(imoNumber);
			vmaShipCertificate.setCallSign(callSign);
			vmaShipCertificate.setIsExamined(0);
			vmaShipCertificate.setMandatory(0);
			vmaShipCertificate.setMaritimeCode(userPort.getPortCode());

			vmaShipCertificate = VmaShipCertificateLocalServiceUtil
					.addVmaShipCertificate(vmaShipCertificate);
			vmaShipCertificates.add(vmaShipCertificate);
		}

		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("data", VMAUtils.object2Json(vmaShipCertificates,
				VmaShipCertificate.class));

		return result;
	}

	public static JSONObject addVmaItineraryRemarks(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) throws SystemException, JSONException {
		HttpServletRequest request = actionRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		String imoNumber = ParamUtil.getString(request, "imoNumber");
		String callSign = VMAUtils.getString(request, "callSign", StringPool.BLANK);
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		String documentName = ParamUtil.getString(request, "documentName");
		String documentYear = ParamUtil.getString(request, "documentYear");
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);

		List<VmaShipCertificate> vmaShipCertificates = VmaShipCertificateLocalServiceUtil
				.findVmaShipCertificate(imoNumber, callSign, -1, -1);
		List<VmaItineraryRemark> itineraryRemarks = new ArrayList<VmaItineraryRemark>();
		if (vmaShipCertificates != null && !vmaShipCertificates.isEmpty()) {
			String E02 = DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(
					100, "E02").getName();
			for (int i = vmaShipCertificates.size() - 1; i >= 0; i--) {
				VmaItineraryRemark vmaItineraryRemarks = new VmaItineraryRemark();
				String certificateName = "";
				try {
					certificateName += DmCertificateLocalServiceUtil
							.findByCertificateCode(
									vmaShipCertificates.get(i)
											.getCertificateCode()).get(0)
							.getCertificateName();
				} catch (Exception e) {
					// nothing to do
				}

				vmaItineraryRemarks.setItineraryNo(itineraryNo);
				vmaItineraryRemarks.setSequenceNo(1);
				vmaItineraryRemarks.setDocumentName(Long.valueOf(documentName));
				vmaItineraryRemarks.setDocumentYear(Integer
						.valueOf(documentYear));
				vmaItineraryRemarks.setPortofAuthority(userPort.getPortCode());
				vmaItineraryRemarks.setNameOfShip(nameOfShip);
				vmaItineraryRemarks.setNoticeShipType(1);
				vmaItineraryRemarks.setRequestDate(new Date());
				vmaItineraryRemarks.setRequestPerson("HE THONG");
				vmaItineraryRemarks.setRemarks(DanhMucUtils.encodeUTF8(E02
						+ " "
						+ certificateName
						+ " "
						+ vmaShipCertificates.get(i).getCertificateNo()
						+ " ngày cấp "
						+ vmaShipCertificates.get(i).getCertificateIssueDate()
						+ " ngày hết hạn "
						+ vmaShipCertificates.get(i)
								.getCertificateExpiredDate() + "."));
				vmaItineraryRemarks.setMarkedAsPending(0);
				vmaItineraryRemarks.setModifiedDate(new Date());

				itineraryRemarks.add(vmaItineraryRemarks);
			}
		}
		result.put("data", VMAUtils.object2Json(itineraryRemarks,
				VmaItinerary.class));

		return result;
	}
}
