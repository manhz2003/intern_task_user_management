package vn.gt.portlet.kehoach.tt16;

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
import vn.gt.utils.PageType;
import vn.nsw.Header;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TT16TichHopMessageUtils {


	

	public static boolean sendMessageLenhDieuDong(int actionType, TempDocument tempDocument, String userName,
			String requestCodeShiftOrder, ActionRequest request, String versionShiftOrder, String yKienReject,
			UploadPortletRequest uploadPortletRequest, String requestCode) {
		boolean result = true;
		try {

			log.info("==sendMessageLenhDieuDong==messageType=========" + MessageType.LENH_DIEU_DONG + "==actionType=="
					+ actionType);
			log.info("==sendMessageLenhDieuDong==DocumentStatusCode==" + tempDocument.getDocumentStatusCode()
					+ "==versionShiftOrder==" + versionShiftOrder);

			String xmlData = "";

			BusinessUtils businessUtils = new BusinessUtils();

			String requestCodeDocument = tempDocument.getRequestCode();

			if (requestCodeDocument != null) {
				InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
						.findByRequestCode(requestCodeDocument);

				if (actionType == PageType.ACTION_TYPE_DUYET) {
					String function = MessageType.FUNCTION_KHAI_BAO;
					Header header = null;
					header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
							MessageType.LENH_DIEU_DONG, userName, interfaceRequest);
					header.getReference().setVersion(versionShiftOrder);
					log.info("==versionShiftOrder==" + header.getReference().getVersion());

					xmlData = businessUtils.sendShiftingOrder(header, tempDocument);

					// TODO tang version InterfaceRequest

				} else if (actionType == PageType.ACTION_TYPE_DONGDAU) {

					log.info("==chinh =  ACTION_TYPE_DONGDAU=");
					String function = MessageType.FUNCTION_KHAI_BAO;
					Header header = null;
					header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
							MessageType.LENH_DIEU_DONG, userName, interfaceRequest);
					// TODO tang version InterfaceRequest
					header.getReference().setVersion(versionShiftOrder);
					log.info("==versionShiftOrder==" + header.getReference().getVersion());

					xmlData = businessUtils.sendShiftingOrder(header, tempDocument);

				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					Header header = null;
					header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND,
							MessageType.FUNCTION_HUY_LENH_DIEU_DONG, MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG, userName,
							interfaceRequest);

					// truong hop gui di chua su ly
					xmlData = businessUtils.sendMessageHuyHoLenhDieuDong(header,
							BusinessUtils.getOrganizationFromUser(request), businessUtils.getDivision(request),
							header.getFrom().getName(), yKienReject, new Date(), 1, tempDocument.getRequestCode());

				}
			}

			if (xmlData != null && xmlData.length() > 0) {

				// businessUtils.insertHistorySendMessage(xmlData);
				businessUtils.insertHistorySendMessageThreeIssue(xmlData, userName, requestCodeShiftOrder);

				String xml = "";

				// log.info("=======Du lieu NHAN HQMC day ========" + xml);
				businessUtils.insertHistoryReceiveMessageResponse(xml);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}

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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function, MessageType.HO_SO,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function, MessageType.HO_SO,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function, MessageType.HO_SO,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function, MessageType.HO_SO,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function, MessageType.HO_SO,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function, MessageType.HO_SO,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function, MessageType.HO_SO,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function, MessageType.TYPE_THONG_BAO_NOP_PHI,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function, MessageType.TYPE_THONG_BAO_XAC_NHAN_NOP_PHI,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
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
				"=============================================== MESSAGE 30 - 21 ===============================================");
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
					MessageType.THONG_BAO_TAU_DEN_CANG, userName, interfaceRequest);

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
				"=============================================== MESSAGE 30 - 21 ===============================================");
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
				"=============================================== MESSAGE 30 - 22 ===============================================");
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
					MessageType.THONG_BAO_TAU_DEN_CANG, userName, interfaceRequest);

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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
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

	public static boolean message_71_03(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode,
			int isApprove) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 71 - 03 ===============================================");
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

			String function = MessageType.FUNCTION_HUY_LENH_DIEU_DONG;

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
					MessageType.HUY_LENH_DIEU_DONG, userName, interfaceRequest);

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
				"=============================================== MESSAGE 71 - 03 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}

	public static boolean message_70_01(String userName, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, long documentName, int documentYear, String yKienReject, String requestCode,
			int isApprove, String versionShiftOrder) {
		log.info(
				"=============================================== -----START----- ================================================");
		log.info(
				"===============================================================================================================");
		log.info(
				"=============================================== MESSAGE 70 - 01 ===============================================");
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

			Header header = BusinessUtils.createHeader(tempDocument, MessageType.TAU_VAO_PTTND, function,
					MessageType.LENH_DIEU_DONG, userName, interfaceRequest);
			header.getReference().setVersion(versionShiftOrder);

			xmlData = businessUtils.sendShiftingOrder(header, tempDocument);

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
				"=============================================== MESSAGE 70 - 01 ===============================================");
		log.info(
				"=============================================== ------END------ =============================================");

		return result;
	}
}
