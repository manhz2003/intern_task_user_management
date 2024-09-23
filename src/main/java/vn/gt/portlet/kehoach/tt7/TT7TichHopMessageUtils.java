package vn.gt.portlet.kehoach.tt7;

import java.util.Date;

import com.fds.nsw.liferay.core.ActionRequest;



import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;

import com.fds.nsw.nghiepvu.model.InterfaceRequest;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.portlet.kehoach.utils.CreateMessageFactoryUtils;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.utils.FormatData;
import vn.nsw.Header;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TT7TichHopMessageUtils {




	public static boolean message_99_21(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 99 - 21 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function, MessageType.HO_SO,
					userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_21(BusinessUtils.getOrganizationFromUser(actionRequest),
					businessUtils.getDivision(actionRequest), header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 99 - 21 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_99_22(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 99 - 22 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function, MessageType.HO_SO,
					userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_22(tempDocument.getRequestCode(), yKienReject,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 99 - 22 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_99_23(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 99 - 23 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function, MessageType.HO_SO,
					userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_23(FormatData.parseDateToTring(new Date()));

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 99 - 23 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_99_24(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 99 - 24 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function, MessageType.HO_SO,
					userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_24(BusinessUtils.getOrganizationFromUser(actionRequest),
					businessUtils.getDivision(actionRequest), header.getFrom().getName(), new Date(), yKienReject);

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 99 - 24 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_99_25(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 99 - 25 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_TAM_DUNG;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function, MessageType.HO_SO,
					userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_24(BusinessUtils.getOrganizationFromUser(actionRequest),
					businessUtils.getDivision(actionRequest), header.getFrom().getName(), new Date(), yKienReject);

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 99 - 25 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_99_27(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 99 - 27 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_THONG_BAO_BO_XUNG;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function, MessageType.HO_SO,
					userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_27(tempDocument.getRequestCode(), yKienReject,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 99 - 27 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_99_28(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 99 - 28 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_TU_SUA_DOI_BO_XUNG_THU_TUC;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function, MessageType.HO_SO,
					userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_28(tempDocument.getRequestCode(), yKienReject,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 99 - 28 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_99_30(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear) {
		String yKienReject = ParamUtil.getString(uploadPortletRequest, "yKienReject");
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 99 - 30 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_THONG_BAO_NOP_PHI;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function, MessageType.TYPE_THONG_BAO_NOP_PHI,
					userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_30(documentName, documentYear);

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 99 - 30 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_99_31(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear) {
		String yKienReject = ParamUtil.getString(uploadPortletRequest, "yKienReject");
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 99 - 31 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_THONG_BAO_XAC_NHAN_NOP_PHI;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function, MessageType.TYPE_THONG_BAO_XAC_NHAN_NOP_PHI,
					userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_31(documentName, documentYear);

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 99 - 31 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_10_21(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 10 - 21 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.BAN_KHAI_AN_NINH_TAU_BIEN, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_21(BusinessUtils.getOrganizationFromUser(actionRequest),
					businessUtils.getDivision(actionRequest), header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 10 - 21 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_10_22(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 10 - 22 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, yKienReject));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.BAN_KHAI_AN_NINH_TAU_BIEN, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_22(requestCode, yKienReject,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 10 - 22 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_30_21(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 31 - 21 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.THONG_BAO_TAU_ROI_CANG, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_21(BusinessUtils.getOrganizationFromUser(actionRequest),
					businessUtils.getDivision(actionRequest), header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 31 - 21 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_30_22(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 31 - 22 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, yKienReject));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.THONG_BAO_TAU_ROI_CANG, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_22(requestCode, yKienReject,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 31 - 22 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_50_22(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 50 - 22 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkBoSung(userName, yKienReject));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_TU_SUA_DOI_BO_XUNG_THU_TUC;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.HO_SO, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_22(requestCode, yKienReject,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 50 - 22 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_51_22(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 51 - 22 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkBoSung(userName, yKienReject));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_TU_SUA_DOI_BO_XUNG_THU_TUC;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.HO_SO, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_22(requestCode, yKienReject,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 51 - 22 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_52_22(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 52 - 22 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkBoSung(userName, yKienReject));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_TU_SUA_DOI_BO_XUNG_THU_TUC;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.HO_SO, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_22(requestCode, yKienReject,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 52 - 22 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_53_22(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 53 - 22 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkBoSung(userName, yKienReject));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_TU_SUA_DOI_BO_XUNG_THU_TUC;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.HO_SO, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_22(requestCode, yKienReject,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date());

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 53 - 22 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_65_05(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 65 - 05 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_THONG_BAO_BO_SUNG_HO_SO_CHUNG_TU_KINH_KEM;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.HO_SO, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_05(yKienReject,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date(), tempDocument);

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 65 - 05 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_65_00(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 65 - 00 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_KHAI_BAO;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.BAN_KHAI_KET_QUA_XEM_XET_CHUNG_TU_DINH_KEM, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_65(yKienReject, 0,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date(), tempDocument);

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 65 - 00 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_65_01(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 65 - 01 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_KHAI_BAO;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.BAN_KHAI_KET_QUA_XEM_XET_CHUNG_TU_DINH_KEM, userName, interfaceRequest);

			xmlData = CreateMessageFactoryUtils.create_65(StringPool.BLANK, 1,
					BusinessUtils.getOrganizationFromUser(actionRequest), businessUtils.getDivision(actionRequest),
					header.getFrom().getName(), new Date(), tempDocument);

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 65 - 01 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_60_01(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode,
			int isApprove, String versionNo) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 60 - 01 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_KHAI_BAO;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH, userName, interfaceRequest);
			header.getReference().setVersion(versionNo);
			log.info(requestCode);
			xmlData = businessUtils.sendMessageResult(header, versionNo, requestCode);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 60 - 01 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_62_03(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode,
			int isApprove) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 62 - 03 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_KHAI_HUY_HO_SO;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.HUY_GIAY_PHEP_ROI_CANG, userName, interfaceRequest);

			// NSW - HOA TRAN YEU CAU isApprove = 1
			
			isApprove = 1;
						
			xmlData = CreateMessageFactoryUtils.create_71(BusinessUtils.getOrganizationFromUser(actionRequest),
					businessUtils.getDivision(actionRequest), header.getFrom().getName(), new Date(), yKienReject,
					isApprove);

			xmlData = businessUtils._processMessage(header, tempDocument.getRequestCode(), xmlData);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 62 - 03 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_63_01(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode,
			String versionNo) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 63 - 01 ===============================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== BOT BOT BOT BOT ===============================================");
		log.info("=============================================== " + new Date()
				+ " ==============================================");
		log.info("======================================userName= " + userName
				+ " ================================================");
		log.info("==================================documentName= " + documentName
				+ " ============================================");
		log.info("==================================documentYear= " + documentYear
				+ " ============================================");
		log.info("===================================yKienReject= " + yKienReject
				+ " =============================================");
		log.info(
				"===============================================================================================================");

		boolean result = false;
		String xmlData = StringPool.BLANK;
		String xml = StringPool.BLANK;
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			BusinessUtils businessUtils = new BusinessUtils();

			String function = MessageType.FUNCTION_KHAI_BAO;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.ROI_CANG_DAU_KHI, function,
					MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG, userName, interfaceRequest);

			xmlData = businessUtils.sendMessageResult(header, versionNo, requestCode);

			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(xmlData);
		log.info(
				"=============================================== ------xmlData------ =============================================");
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(xml);
		log.info(
				"=============================================== --------xml-------- =============================================");
		log.info(
				"=============================================== MESSAGE 63 - 01 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

}