package vn.gt.tichhop.message.haiquan2giaothong;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.kernel.exception.SystemException;





import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.core.PortalUtil;

import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.ModifyLocalServiceUtil;
import vn.gt.constant.Constants;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;

import com.fds.nsw.nghiepvu.model.IssueAcceptance;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;

import com.fds.nsw.nghiepvu.model.TempCargoItems;
import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueAcceptanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;

import vn.gt.dao.noticeandmessage.service.TempAnimalQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewEffectsDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDangerousGoodsNanifestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDeclarationOfHealthLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPassengerListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil;

import com.fds.nsw.nghiepvu.model.ResultNotification;
import vn.gt.dao.result.service.ResultCompetionLocalServiceUtil;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.dao.result.service.ResultNotificationLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageFactory;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiBanKhai;
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiQuaCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiXuatCanh;
import vn.gt.tichhop.message.TrangThaiHoSo;
import vn.gt.tichhop.message.giaothong2haiquan.PortClearance2Xml;
import vn.gt.tichhop.sendmessage.MessgaePriorityLevels;
import vn.gt.tichhop.sendmessage.SendMessgaeFunctions;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.PortClearance;
import vn.nsw.model.inland.InlandPortClearance;
import vn.nsw.model.inland.PTTNDAcceptance;
import vn.nsw.model.inland.PTTNDPortclearance;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.liferay.core.LanguageUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.UploadPortletRequest;
@Slf4j
public class XuatCanhHaiQuan2GiaoThongBusiness {


	public boolean xuLyXuatCanhRoleKeHoach(long documentName, int messageType,
			int documentYear, int actionType, String userName,
			String requestCode, ActionRequest request, ActionResponse httpReq) {
		boolean result = false;

		log.info("====call xuLyXuatCanhRoleKeHoach=====documentName=="
				+ documentName);
		log.info("====call xuLyXuatCanhRoleKeHoach=====messageType=="
				+ messageType);
		log.info("====call xuLyXuatCanhRoleKeHoach=====documentYear=="
				+ documentYear);
		log.info("====call xuLyXuatCanhRoleKeHoach=====actionType=="
				+ actionType);
		log.info("====call xuLyXuatCanhRoleKeHoach=====requestCode=="
				+ requestCode);

		// Kiem tra xem ban khai nao truyen len
		switch (messageType) {
		case MessageType.THONG_BAO_TAU_ROI_CANG:

			log.info("VAO THONG_BAO_TAU_ROI_CANG=================");
			result = banKhaiThongBaoTauRoiCang(documentName, messageType,
					documentYear, actionType, userName, requestCode, request);
			break;
		case PageType.THANH_PHAN_HO_SO:
			log.info("VAO PageType.THANH_PHAN_HO_SO=!!!!");
			result = toanBoHoSoKeHoach(documentName, messageType, documentYear,
					actionType, userName, requestCode, request, httpReq);
			break;
		}

		return result;
	}

	// sent message 29, 65-1, 65-0 (dung.handsome)
	/**
	 * btn:Yêu cầu xuất trình
	 */
	public void sentMessageKiemDuyetHoSoDinhKem(String userLogin, int pageType,
			long documentName, int documentYear, String requestCode,
			int messageType, ActionRequest request, ActionResponse httpReq,
			int btnActionType, String lyDoTuChoi, String lyDoYeuCauXuatTrinh) throws Exception {

		// log.info("dung.le MEssagety ======= " + messageType);
		TempDocument tempDocument = TempDocumentLocalServiceUtil
				.getByDocumentNameAndDocumentYear(documentName, documentYear);
		if (tempDocument != null) {
			// log.info("dung.le MEssagety ======= IF" + messageType);
			if (pageType == 4) {

				// gui message 29
				if (btnActionType == 1) {
					// log.info("sentMessageKiemDuyetHoSoDinhKem btnActionType:= "
					// + btnActionType);
					thuTucSentMessageYeuCauBoSungHoSoDinhKem(userLogin,
							tempDocument, messageType, requestCode, request, lyDoYeuCauXuatTrinh);

				} else if (btnActionType == 2) {

					// log.info("sentMessageKiemDuyetHoSoDinhKem btnActionType:= "
					// + btnActionType);
					thuTucSentMessageChapNhanHoSoDinhKem(userLogin,
							tempDocument, messageType, requestCode, request);

				} else if (btnActionType == 0) {
					thuTucSentMessageTuChoiHoSoDinhKem(userLogin, tempDocument,
							messageType, requestCode, request, lyDoTuChoi);
				}
			}

		}

	}

	/**
	 * btn:Yêu cầu xuất trình
	 */

	private void thuTucSentMessageTuChoiHoSoDinhKem(String userLogin,
			TempDocument tempDocument, int messageType, String requestCode,
			ActionRequest request, String lydotuchoi) throws RemoteException {

		log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====");
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = "";
//		IMTService imtService = CallWs2HaiQuan.getIMTSercice();

		Header header = null;
		String function = MessageType.FUNCTION_THONG_BAO_CHAP_NHAN_HO_SO_CHUNG_TU_KINH_KEM;

		if (messageType == Integer
				.valueOf(MessageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH)) {
			log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====MessageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH "
					+ MessageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH);

			requestCode = tempDocument.getRequestCode();
		}

		if (requestCode != null) {
			log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null");
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findByRequestCode(requestCode);
			if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_ROI_CANG)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_RA, function, 65, userLogin,
						interfaceRequest);
				xmlData = tuChoiHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request,
						lydotuchoi);

			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_VAO, function, 65, userLogin,
						interfaceRequest);
				xmlData = tuChoiHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request,
						lydotuchoi);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_RA_PTTND, function, 65, userLogin,
						interfaceRequest);
				xmlData = tuChoiHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request,
						lydotuchoi);

			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_VAO_PTTND, function, 65, userLogin,
						interfaceRequest);
				xmlData = tuChoiHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request,
						lydotuchoi);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_XUAT_CANH)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.XUAT_CANH, function, 65, userLogin,
						interfaceRequest);
				xmlData = tuChoiHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request,
						lydotuchoi);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_NHAP_CANH)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.NHAP_CANH, function, 65, userLogin,
						interfaceRequest);
				xmlData = tuChoiHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request,
						lydotuchoi);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_QUA_CANH)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.QUA_CANH, function, 65, userLogin,
						interfaceRequest);
				xmlData = tuChoiHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request,
						lydotuchoi);
			}

			if (xmlData != null && xmlData.length() > 0) {

				// Chua ro nghiepvu insert nhu the nao (dung.le)
				boolean insertHistorySendMessage = businessUtils
						.insertHistorySendMessage(xmlData);
				log.info("==thuTucNhapCanhTraMessageHaiQuan==insertHistorySendMessage=="
						+ insertHistorySendMessage);

				// String xml = imtService.receiveResultFromMT(xmlData);
				String xml = "";
				if (tempDocument.getDocumentTypeCode().equals(
						(MessageType.LOAT_THU_TUC_VAO_CANG))
						|| tempDocument.getDocumentTypeCode().equals(
								MessageType.LOAT_THU_TUC_ROI_CANG)) {
					log.info(" ----CAll receiveFromInland------- ");
					// resevice mess from our message!
					// xml = imtService.receiveFromInland(xmlData);

				} else {
					log.info(" ----CAll receiveResultFromMT------- ");
					// xml = imtService.receiveResultFromMT(xmlData);
				}

				// log.info("===du lieu gui hai quan== " + xmlData );
				// log.info("=======Du lieu NHAN  HQMC day ========" + xml);
				boolean insertHistoryReceiveMessageResponse = businessUtils
						.insertHistoryReceiveMessageResponse(xml);
				log.info("==thuTucNhapCanhTraMessageHaiQuan==insertHistoryReceiveMessageResponse=="
						+ insertHistoryReceiveMessageResponse);
			}
		}
	}

	/**
	 * btn:Yêu cầu xuất trình
	 */
	private void thuTucSentMessageChapNhanHoSoDinhKem(String userLogin,
			TempDocument tempDocument, int messageType, String requestCode,
			ActionRequest request) throws RemoteException {

		log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====");
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = "";
//		IMTService imtService = CallWs2HaiQuan.getIMTSercice();

		Header header = null;
		String function = MessageType.FUNCTION_THONG_BAO_CHAP_NHAN_HO_SO_CHUNG_TU_KINH_KEM;

		if (messageType == Integer
				.valueOf(MessageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH)) {
			log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====MessageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH "
					+ MessageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH);

			requestCode = tempDocument.getRequestCode();
		}

		if (requestCode != null) {
			// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null");
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findByRequestCode(requestCode);
			if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_ROI_CANG)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_RA, function, 65, userLogin,
						interfaceRequest);
				xmlData = chapNhanHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request);

			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_VAO, function, 65, userLogin,
						interfaceRequest);
				xmlData = chapNhanHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_RA_PTTND, function, 65, userLogin,
						interfaceRequest);
				xmlData = chapNhanHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request);

			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_VAO_PTTND, function, 65, userLogin,
						interfaceRequest);
				xmlData = chapNhanHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_XUAT_CANH)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.XUAT_CANH, function, 65, userLogin,
						interfaceRequest);
				xmlData = chapNhanHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_NHAP_CANH)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.NHAP_CANH, function, 65, userLogin,
						interfaceRequest);
				xmlData = chapNhanHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_QUA_CANH)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.QUA_CANH, function, 65, userLogin,
						interfaceRequest);
				xmlData = chapNhanHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request);
			}

			if (xmlData != null && xmlData.length() > 0) {

				// Chua ro nghiepvu insert nhu the nao (dung.le)
				boolean insertHistorySendMessage = businessUtils
						.insertHistorySendMessage(xmlData);
				log.info("==thuTucNhapCanhTraMessageHaiQuan==insertHistorySendMessage=="
						+ insertHistorySendMessage);

				// String xml = imtService.receiveResultFromMT(xmlData);
				String xml = "";
				if (tempDocument.getDocumentTypeCode().equals(
						(MessageType.LOAT_THU_TUC_VAO_CANG))
						|| tempDocument.getDocumentTypeCode().equals(
								MessageType.LOAT_THU_TUC_ROI_CANG)) {
					// log.info(" ----CAll receiveFromInland------- ");
					// resevice mess from our message!
					// xml = imtService.receiveFromInland(xmlData);

				} else {
					// log.info(" ----CAll receiveResultFromMT------- ");
					// xml = imtService.receiveResultFromMT(xmlData);
				}
				// log.info("===du lieu gui hai quan== " + xmlData );

				// log.info("=======Du lieu NHAN  HQMC day ========" + xml);
				boolean insertHistoryReceiveMessageResponse = businessUtils
						.insertHistoryReceiveMessageResponse(xml);
				log.info("==thuTucNhapCanhTraMessageHaiQuan==insertHistoryReceiveMessageResponse=="
						+ insertHistoryReceiveMessageResponse);
			}
		}
	}

	/**
	 * btn:Yêu cầu xuất trình
	 */
	private void thuTucSentMessageYeuCauBoSungHoSoDinhKem(String userLogin,
			TempDocument tempDocument, int messageType, String requestCode,
			ActionRequest request, String lyDoYeuCauXuatTrinh) throws RemoteException {

		log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====");
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = "";

		// IMTService imtService = CallWs2HaiQuan.getIMTSercice();

		Header header = null;
		String function = MessageType.FUNCTION_THONG_BAO_BO_SUNG_HO_SO_CHUNG_TU_KINH_KEM;

		if (messageType == Integer
				.valueOf(MessageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH)) {
			// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====MessageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH "
			// +
			// MessageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH);

			requestCode = tempDocument.getRequestCode();
		}

		if (requestCode != null) {
			// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null");
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findByRequestCode(requestCode);

			if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_ROI_CANG)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_RA, function, 99, userLogin,
						interfaceRequest);
				xmlData = yeuCauBoSungHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request, lyDoYeuCauXuatTrinh);

			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_VAO, function, 99, userLogin,
						interfaceRequest);
				xmlData = yeuCauBoSungHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request, lyDoYeuCauXuatTrinh);

			}
			if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_RA_PTTND, function, 99, userLogin,
						interfaceRequest);
				xmlData = yeuCauBoSungHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request, lyDoYeuCauXuatTrinh);

			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_VAO_PTTND, function, 99, userLogin,
						interfaceRequest);
				xmlData = yeuCauBoSungHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request, lyDoYeuCauXuatTrinh);

			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_NHAP_CANH)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.NHAP_CANH, function, 99, userLogin,
						interfaceRequest);
				xmlData = yeuCauBoSungHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request, lyDoYeuCauXuatTrinh);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_XUAT_CANH)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.XUAT_CANH, function, 99, userLogin,
						interfaceRequest);
				xmlData = yeuCauBoSungHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request, lyDoYeuCauXuatTrinh);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_QUA_CANH)) {
				// log.info("==VAO thuTucSentMessageYeuCauBoSungHoSoDinhKem====  requestCode!=null and getDocumentTypeCode()"
				// +
				// tempDocument.getDocumentTypeCode());
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.QUA_CANH, function, 99, userLogin,
						interfaceRequest);
				xmlData = yeuCauBoSungHoSoDinhKem(messageType, function, xmlData,
						businessUtils, header, tempDocument, request, lyDoYeuCauXuatTrinh);
			}

			if (xmlData != null && xmlData.length() > 0) {

				// Chua ro nghiepvu insert nhu the nao (dung.le)
				boolean insertHistorySendMessage = businessUtils
						.insertHistorySendMessage(xmlData);
				// log.info("==thuTucNhapCanhTraMessageHaiQuan==insertHistorySendMessage=="
				// + insertHistorySendMessage);

				// String xml = imtService.receiveResultFromMT(xmlData);
				// String xml = "";
				if (tempDocument.getDocumentTypeCode().equals(
						(MessageType.LOAT_THU_TUC_VAO_CANG))
						|| tempDocument.getDocumentTypeCode().equals(
								MessageType.LOAT_THU_TUC_ROI_CANG)) {
					// log.info(" ----CAll receiveFromInland------- ");
					// resevice mess from our message!
					// xml = imtService.receiveFromInland(xmlData);

				} else {
					// log.info(" ----CAll receiveResultFromMT------- ");
					// xml = imtService.receiveResultFromMT(xmlData);
				}

				// log.info("===du lieu gui hai quan== " + xmlData );

				// tach messsage tra ve lam gate nen buoc nay ko insstert vao dc
				// History
				// log.info("=======Du lieu NHAN  HQMC day ========" + xml);
				// boolean insertHistoryReceiveMessageResponse =
				// businessUtils.insertHistoryReceiveMessageResponse(xml);
				// log.info("==thuTucNhapCanhTraMessageHaiQuan==insertHistoryReceiveMessageResponse=="
				// + insertHistoryReceiveMessageResponse);
			}
		}
	}

	private String yeuCauBoSungHoSoDinhKem(int messageType, String function,
			String xmlData, BusinessUtils businessUtils, Header header,
			TempDocument tempDocument, ActionRequest resourceRequest, String lydoYeuCauXuatTrinh) {

		xmlData = businessUtils.SentMessageRejectDinhKem(header, lydoYeuCauXuatTrinh, BusinessUtils
				.getOrganizationFromUser(resourceRequest), businessUtils
				.getDivision(resourceRequest), header.getFrom().getName(),
				new Date(), tempDocument);
		return xmlData;
	}

	//
	private String chapNhanHoSoDinhKem(int messageType, String function,
			String xmlData, BusinessUtils businessUtils, Header header,
			TempDocument tempDocument, ActionRequest resourceRequest) {

		xmlData = businessUtils.SentMessageAcceptDinhKem(header, header
				.getReference().getMessageId(), BusinessUtils
				.getOrganizationFromUser(resourceRequest), businessUtils
				.getDivision(resourceRequest), header.getFrom().getName(),
				new Date(), tempDocument);
		return xmlData;
	}

	private String tuChoiHoSoDinhKem(int messageType, String function,
			String xmlData, BusinessUtils businessUtils, Header header,
			TempDocument tempDocument, ActionRequest resourceRequest,
			String lydotuchoi) {

		xmlData = businessUtils.SentMessageTucChoiDinhKem(header, header
				.getReference().getMessageId(), BusinessUtils
				.getOrganizationFromUser(resourceRequest), businessUtils
				.getDivision(resourceRequest), header.getFrom().getName(),
				new Date(), tempDocument, lydotuchoi);
		return xmlData;
	}

	public boolean xuLyXuatCanhRoleThuTuc(String userLogin, long documentName,
			int messageType, int documentYear, int actionType, String userName,
			String requestCode, ActionRequest request, ActionResponse httpReq)
			throws SystemException {

		log.info("==xuLyXuatCanhRoleThuTuc==actionType====actionType=="
				+ actionType + "==messageType==" + messageType);
		boolean result = false;

		// Kiem tra xem ban khai nao truyen len
		switch (messageType) {
		case PageType.THANH_PHAN_HO_SO:
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==THANH_PHAN_HO_SO");
			result = thanhPhanHoSoThucTuc(userLogin, documentName, messageType,
					documentYear, actionType, userName, requestCode, request,
					httpReq);
			break;

		case MessageType.BAN_KHAI_CHUNG:
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==BAN_KHAI_CHUNG");
			result = banKhaiChung(documentName, messageType, documentYear,
					actionType, userName, requestCode, request);
			break;

		case MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN:
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==BAN_KHAI_DANH_SACH_THUYEN_VIEN");
			result = banKhaiDanhSachThuyenVien(documentName, messageType,
					documentYear, actionType, userName, requestCode, request);
			break;

		case MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH:
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==BAN_KHAI_DANH_SACH_HANH_KHACH");
			result = banKhaiDanhSachHanhKhach(documentName, messageType,
					documentYear, actionType, userName, requestCode, request);
			break;

		case MessageType.BAN_KHAI_HANG_HOA:
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==BAN_KHAI_HANG_HOA");
			result = banKhaiDanhSachHangHoa(documentName, messageType,
					documentYear, actionType, userName, requestCode, request);
			break;

		case MessageType.BAN_KHAI_DU_TRU_CUA_TAU:
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==BAN_KHAI_DU_TRU_CUA_TAU");
			result = banKhaiDuTruCuaTau(documentName, messageType,
					documentYear, actionType, userName, requestCode, request);
			break;

		case MessageType.BAN_KHAI_HANH_LY_HANH_KHACH_ROI_TAU:
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==BAN_KHAI_HANH_LY_HANH_KHACH_ROI_TAU");
			result = banKhaiDanhSachHanhLyHanhKhachRoiTau(documentName,
					messageType, documentYear, actionType, userName,
					requestCode, request);
			break;

		case MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH:
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH");
			result = thuTucActionGiayPhepRoiCangChoTauDen(userLogin,
					documentName, messageType, documentYear, actionType,
					userName, requestCode, request, httpReq);
			break;

		case MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN:
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==GIAY_PHEP_ROI_CANG_CHO_TAU_DEN");
			result = thuTucActionGiayPhepRoiCangChoTauDen(userLogin,
					documentName, messageType, documentYear, actionType,
					userName, requestCode, request, httpReq);
			break;

		case MessageType.PTTND_GIAY_PHEP_VAO_CANG:
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==GIAY_PHEP_VAO_CANG");
			result = thuTucActionGiayPhepVaoCangPTTND(userLogin, documentName,
					messageType, documentYear, actionType, userName,
					requestCode, request, httpReq);
			break;

		case MessageType.XAC_NHAN_HOAN_THANH_THU_TUC:
			// ham, nay cho chuc nang thuc thi can ho hai quan gui messagage
			// thong bao den doanh nghiep, messageType = 23
			log.info("==xuLyXuatCanhRoleThuTuc==messageType==" + messageType
					+ "==XAC_NHAN_HOAN_THANH_THU_TUC");
			result = xacNhanHoanThanhThuTuc(documentName, messageType,
					documentYear, actionType, userName, requestCode, request,
					httpReq);
			break;
		}

		return result;
	}

	private boolean thuTucActionGiayPhepVaoCangPTTND(String userLogin,
			long documentName, int messageType, int documentYear,
			int actionType, String userName, String requestCodeAcceptance,
			ActionRequest request, ActionResponse response)
			throws SystemException {
		// TODO Auto-generated method stub
		boolean result = false;
		TempDocument tempDocument = null;
		InterfaceRequest interfaceRequest = null;
		Header header = null;
		BusinessUtils businessUtils = new BusinessUtils();

		// MessageFactory messageFactory = new MessageFactory();
		String xmlData = "";

		log.info("==thuTucActionGiayPhepVaoCangPTTND=messageType==="
				+ messageType + "====documentName===" + documentName
				+ "====requestCodeAcceptance====" + requestCodeAcceptance);

		String requestCodeIssueAcceptanceDuyet = "";
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		switch (actionType) {
		case PageType.ACTION_TYPE_TIEP_NHAN:

			log.info("===START====ACTION_TYPE_TIEP_NHAN====documentName==="
					+ documentName + "===messageType===" + messageType
					+ "===requestCodeAcceptance==="
					+ requestCodeAcceptance);

			taoGiayPhepVaoCang(documentName, messageType, documentYear,
					actionType, userName, requestCodeAcceptance, request,
					response);
			
			String capAcceptance = ParamUtil.getString(request,
					PageType.LAN_CAP_ISSUE_ACCEPTANCE);
			String lyDoCapLai = ParamUtil.getString(request,
					"lyDoCapLaiAcceptance");
			log.info("==thuTucActionGiayPhepVaoCang==lyDoCapLai==" + lyDoCapLai);

			InterfaceRequest interfaceAcceptance = InterfaceRequestLocalServiceUtil
					.findByRequestCode(requestCodeAcceptance);
			if (interfaceAcceptance != null && capAcceptance.equalsIgnoreCase(KeyParams.N_LAN)) {
				String issueDate = ParamUtil.getString(request, "issueDate");
				String remarkCapLai = BusinessUtils.getRemarkCapLai(userName,
						lyDoCapLai,
						FormatData.parseDateShort3StringToDate(issueDate));
				interfaceAcceptance.setRemarks(remarkCapLai);
				InterfaceRequestLocalServiceUtil
						.updateInterfaceRequest(interfaceAcceptance);
			}

			break;

		case PageType.ACTION_TYPE_SUA:

			log.info("===START====ACTION_TYPE_SUA====documentName==="
					+ documentName + "===messageType===" + messageType
					+ "===requestCodeAcceptance==="
					+ requestCodeAcceptance);

			taoGiayPhepVaoCang(documentName, messageType, documentYear,
					actionType, userName, requestCodeAcceptance, request,
					response);
			break;

		case PageType.ACTION_TYPE_DUYET:
			
			PortClearance2Xml acceptance2Xml = new PortClearance2Xml();
			tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);

			
			// --------------------------------------InlandAcceptance--------------------------------------
			log.info("--ACTION_TYPE_DUYET--DocumentTypeCode----"
					+ tempDocument.getDocumentTypeCode());
			if (tempDocument.getDocumentTypeCode().equals(
					MessageType.TAU_VAO_PTTND + "")) {

				PTTNDAcceptance acceptance = acceptance2Xml
						.convertXMLPTTNDAcceptance(requestCodeAcceptance);

				if (acceptance != null) {
					//
					if (acceptance.getPortofAuthority() != null
							&& acceptance.getPortofAuthority().length() > 0) {
						DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
								.getByMaritimeCode(acceptance
										.getPortofAuthority());
						if (dmMaritime != null) {
							acceptance.setPortofAuthority(dmMaritime
									.getMaritimeReference());
						}
					}

					if (tempDocument.getDocumentStatusCode() != TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC) {

						xacNhanHoanThanhThuTucXuatCanh(request, documentName,
								documentYear);

						tempDocument
								.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC);

						if (tempDocument != null) {
							TempDocumentLocalServiceUtil
									.updateTempDocument(tempDocument);
						}

					}

					IssueAcceptance acceptanceDuyet = IssueAcceptanceLocalServiceUtil
							.fetchByRequestCode(requestCodeAcceptance);

					if (Validator.isNotNull(acceptanceDuyet
							.getRequestState())) {

						// TODO Vao canh truong hop duyet
						acceptanceDuyet
								.setIsApproval(PageType.DUYET_PHE_CHUAN);
						acceptanceDuyet.setApprovalDate(new Date());
						acceptanceDuyet.setApprovalName(userName);

						acceptanceDuyet
								.setRequestState(TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI);
						IssueAcceptanceLocalServiceUtil
								.updateIssueAcceptance(acceptanceDuyet);

						// TODO gui giay phep roi cang
						requestCodeIssueAcceptanceDuyet = acceptanceDuyet
								.getRequestCode();
					}

					interfaceRequest = InterfaceRequestLocalServiceUtil
							.findInterfaceRequestByRequestCode(tempDocument
									.getRequestCode());
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_VAO_PTTND,
								MessageType.FUNCTION_KHAI_BAO,
								messageType, userName,
								interfaceRequest);					
					}else
					{
						header = BusinessUtils.createHeaderInland(tempDocument,
								MessageType.TAU_VAO_PTTND,
								MessageType.FUNCTION_KHAI_BAO, messageType,
								userName, interfaceRequest);
					}
					header.getReference().setVersion(
							acceptanceDuyet.getVersionNo());

					log.info("====ACTION_TYPE_DUYET_ GIAY PHEP VAO CANG PTTND====REQUEST_CODE===="
							+ requestCodeAcceptance
							+ "====getNextPortOfCallCode==="
							+ acceptance.getNextPortOfCallCode());
					

					// TODO Giay phep vao cang Gui thong tin di.
					xmlData = businessUtils
							.createContentSendFromBGTVTToNSWInland(header,
									MessageFactory
											.convertObjectToXml(acceptance));
				}

				// --------------------------------------IssueAcceptance--------------------------------------
			}

			// TODO GUI GIAY phep vao cang
			if (requestCodeIssueAcceptanceDuyet
					.length() > 0) {
				// Insert history.
				BusinessUtils.insertHistory(header, xmlData,
						BusinessUtils.BoGiaoThongToHqmc,
						TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID()
								.toString());
				SendMessgaeFunctions
						.insertMessageQueue(
								header,
								xmlData,
								"OUT",
								requestCodeIssueAcceptanceDuyet,
								MessgaePriorityLevels.HIGHT);
			}


			break;
		case PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET:
			IssueAcceptance acceptanceChoLanhDaoDuyet = IssueAcceptanceLocalServiceUtil.fetchByRequestCode(requestCodeAcceptance);

			if ((acceptanceChoLanhDaoDuyet != null) && Validator.isNotNull(acceptanceChoLanhDaoDuyet.getRequestState())
					&& (acceptanceChoLanhDaoDuyet.getStampStatus() == 0)) {
		
				// TODO Vao cang truong hop duyet
				acceptanceChoLanhDaoDuyet.setStampStatus(PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET);
				acceptanceChoLanhDaoDuyet.setIsApproval(PageType.KHONG_PHE_CHUAN);
				
				IssueAcceptanceLocalServiceUtil
						.updateIssueAcceptance(acceptanceChoLanhDaoDuyet);
				log.info("==Chuyen_lanh_dao_duyet_ky_giay_phep=");
				// TODO gui giay phep roi cang
				requestCodeIssueAcceptanceDuyet = acceptanceChoLanhDaoDuyet.getRequestCode();
			}
			break;
		case PageType.ACTION_TYPE_CHUYEN_TRA_HO_SO:
			IssueAcceptance acceptanceLanhDaoTraLai = IssueAcceptanceLocalServiceUtil.fetchByRequestCode(requestCodeAcceptance);

			if ((acceptanceLanhDaoTraLai != null) && Validator.isNotNull(acceptanceLanhDaoTraLai.getRequestState())) {		
				String lyDoTraLai = ParamUtil.getString(request, PageType.LY_DO_TRA_LAI_HO_SO);
				acceptanceLanhDaoTraLai.setStampStatus(PageType.KHONG_PHE_CHUAN);
				acceptanceLanhDaoTraLai.setIsApproval(PageType.KHONG_PHE_CHUAN);
				acceptanceLanhDaoTraLai.setIsCancel(PageType.KHONG_PHE_CHUAN);
				acceptanceLanhDaoTraLai.setCancelNote(lyDoTraLai);
				IssueAcceptanceLocalServiceUtil
						.updateIssueAcceptance(acceptanceLanhDaoTraLai);
				log.info("==Tra ve buoc truoc, sua noi dung giay phep==");	
				
				requestCodeIssueAcceptanceDuyet = acceptanceLanhDaoTraLai.getRequestCode();
				
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(documentName,
								documentYear);
				
				BusinessUtils.updateLyDoResultNotification(userLogin, lyDoTraLai, MessageType.Y_CAU_TRA_LAI_HO_SO_PTTND_GIAY_PHEP_VAO_CANG,
						tempDocument.getMaritimeCode(), documentName, documentYear);
			}
			break;	
		case PageType.ACTION_TYPE_KYSODUYET:

			log.info("=====xuLyGiayPhepVaocang==ACTION_PageType.ACTION_TYPE_KYSODUYET");
			tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);
			IssueAcceptance acceptanceDuyet = IssueAcceptanceLocalServiceUtil
					.fetchByRequestCode(requestCodeAcceptance);
			long addkyso = ParamUtil.getLong(request, "fileId");

			if (Validator.isNotNull(acceptanceDuyet)) {
				log.info("=====xuLyGiayPhepVaocang==ACTION_TYPE_KYSODUYET ");
				acceptanceDuyet
						.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_MOI);
				// perForTransitDuyetkyso.setIsApproval(PageType.DUYET_PHE_CHUAN);
				acceptanceDuyet.setApprovalDate(new Date());
				acceptanceDuyet.setApprovalName(userName);

				acceptanceDuyet.setStampStatus(1);
				String representative = acceptanceDuyet.getRepresentative();
				String portofAuthority = acceptanceDuyet.getPortofAuthority();				
				String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
				acceptanceDuyet.setSignTitle(signTitle);				
				acceptanceDuyet.setSignName(ParamUtil.getString(request,
						"signName"));
				acceptanceDuyet.setSignPlace(ParamUtil.getString(request,
						"signPlace"));
				acceptanceDuyet.setSignDate(ParamUtil.getDate(request,
						"signDate", FormatData.formatDateShort3));
				if (addkyso > 0) {
					acceptanceDuyet.setAttachedFile(ParamUtil.getLong(
							request, "fileId"));
				}

				IssueAcceptanceLocalServiceUtil
						.updateIssueAcceptance(acceptanceDuyet);
				tempDocument
						.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP);

				if (tempDocument != null) {
					TempDocumentLocalServiceUtil
							.updateTempDocument(tempDocument);
				}

				log.info("=====xuLyGiayPhepVaocang==tempDocument "
						+ tempDocument);

				// Kich ban du phong, Lanh dao ky so, khong co buoc Van thu dong dau.
				if (ConfigurationManager.getStrProp("vn.gt.yeu.cau.bo.qua.buoc.dong.dau", "").contains("1")) {
					thuTucActionGiayPhepVaoCangPTTND(userLogin, documentName, messageType, documentYear,
							PageType.ACTION_TYPE_DONGDAUDUYET, userName, requestCodeAcceptance, request, response);
				}
			}
			break;

		case PageType.ACTION_TYPE_DONGDAUDUYET:

			PortClearance2Xml acceptance2XmlDD = new PortClearance2Xml();
			PTTNDAcceptance acceptanceDD = acceptance2XmlDD
					.convertXMLPTTNDAcceptance(requestCodeAcceptance);

			if (acceptanceDD != null) {
				if (acceptanceDD.getPortofAuthority() != null
						&& acceptanceDD.getPortofAuthority().length() > 0) {
					log.info("==== chinh **************acceptance != null  7777*********** DUYET");
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
							.getByMaritimeCode(acceptanceDD
									.getPortofAuthority());
					if (dmMaritime != null) {
						log.info("==== chinh **************dmMaritime != null*********** DUYET");
						acceptanceDD.setPortofAuthority(dmMaritime
								.getMaritimeReference());
					}
				}

				log.info("=====xuLyGiayPhepVaocang==ACTION_TYPE_DONGDAUDUYET");
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				IssueAcceptance acceptanceDuyetDD = IssueAcceptanceLocalServiceUtil
						.fetchByRequestCode(requestCodeAcceptance);
				long adddongdau = ParamUtil.getLong(request, "fileId");
				if (Validator.isNotNull(acceptanceDuyetDD)) {

					acceptanceDuyetDD
							.setRequestState(TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI);
					acceptanceDuyetDD.setIsApproval(PageType.DUYET_PHE_CHUAN);
					//acceptanceDuyetDD.setApprovalDate(new Date());
					//acceptanceDuyetDD.setApprovalName(userName);

					acceptanceDuyetDD.setStampStatus(2);

					if (adddongdau > 0) {
						acceptanceDuyetDD.setAttachedFile(ParamUtil.getLong(
								request, "fileId"));
					}

					IssueAcceptanceLocalServiceUtil
							.updateIssueAcceptance(acceptanceDuyetDD);

					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC);

					if (tempDocument != null) {
						TempDocumentLocalServiceUtil
								.updateTempDocument(tempDocument);
					}

				}
				if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
				{
					header = BusinessUtils.createHeaderInlandSMS(tempDocument,
							MessageType.TAU_VAO_PTTND,
							MessageType.FUNCTION_KHAI_BAO,
							messageType, userName,
							interfaceRequest);					
				}else
				{
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_VAO_PTTND,
							MessageType.FUNCTION_KHAI_BAO, messageType, userName,
							interfaceRequest);
				}
				header.getReference().setVersion(
						acceptanceDuyetDD.getVersionNo());
				xmlData = businessUtils.createContentSendFromBGTVTToNSWInland(
						header,
						MessageFactory.convertObjectToXml(acceptanceDD));
			}
			break;

		case PageType.ACTION_TYPE_HUY:


			String lyDoHuyXC = ParamUtil.getString(request, PageType.HUY_HO_SO);

			if (lyDoHuyXC == null || lyDoHuyXC.length() == 0) {
				lyDoHuyXC = "lyDoHuyXC";
			}
			log.info("===========lyDoHuyXC========================="
					+ lyDoHuyXC);

			tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);
			tempDocument
					.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
			TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);

			List<IssueAcceptance> lstAcceptances = IssueAcceptanceLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.VERSION_NO,
							KeyParams.ORDER_BY_DESC);

			for (IssueAcceptance object : lstAcceptances) {
				object.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_HUY);
				IssueAcceptanceLocalServiceUtil
						.updateIssueAcceptance(object);
			}

			if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
				///SMS
				
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_VAO_PTTND,
						MessageType.FUNCTION_KHAI_HUY_HO_SO,
						MessageType.HUY_GIAY_PHEP_VAO_CANG, userName,
						interfaceRequest);
				
				if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
				{
					header = BusinessUtils.createHeaderInlandSMS(tempDocument,
							MessageType.TAU_VAO_PTTND,
							MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
							MessageType.TIN_NHAN_PTTND_VAOROI, userName,
							interfaceRequest);					
				}
			} 
			
			if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
			{
				xmlData = businessUtils.sendMessageHuyHoSoNew(header, BusinessUtils
						.getMaritimeShortNameFromUser(request), businessUtils
						.getDivision(request), header.getFrom().getName(),
						new Date(), lyDoHuyXC, tempDocument.getRequestCode());
			}
			else
			{
				xmlData = businessUtils.sendMessageHuyHoSoNew(header, BusinessUtils
						.getOrganizationFromUser(request), businessUtils
						.getDivision(request), header.getFrom().getName(),
						new Date(), lyDoHuyXC, tempDocument.getRequestCode());
			}

			// TODO update Result Notification
			BusinessUtils.updateLyDoResultNotification(userLogin, lyDoHuyXC,
					MessageType.HUY_GIAY_PHEP_VAO_CANG,
					tempDocument.getMaritimeCode(), documentName, documentYear);

			List<IssueAcceptance> lissueAcceptances = IssueAcceptanceLocalServiceUtil
					.findIssueAcceptanceByDocumentYearAndDocumentYear(
							documentName, documentYear);

			if (lissueAcceptances != null && lissueAcceptances.size() > 0) {

				for (IssueAcceptance issue : lissueAcceptances) {

					issue.setIsCancel(1);
					issue.setCancelName(userLogin);
					issue.setCancelNote(lyDoHuyXC);
					issue.setCancelDate(new Date());
					IssueAcceptanceLocalServiceUtil
							.updateIssueAcceptance(issue);
				}
			}
			break;
		}

		if (xmlData != null && xmlData.trim().length() > 0) {
			// guiBanTinSangHQMC(xmlData, businessUtils);
			try {
				String uid = "BGTVT"
						+ Long.toString(System.currentTimeMillis());
				businessUtils.insertHistorySendMessageThreeIssue(xmlData,
						userName, uid);
				log.info("==guiBanTinSangHQMC==xmlData==");
				log.info(xmlData);

				// IMTService imtService = CallWs2HaiQuan.getIMTSercice();
				String xmlReceive = "";

				

				businessUtils.insertHistoryReceiveMessageResponse(xmlReceive);
			} catch (Exception e) {
				log.error(e.getMessage());
				log.error(e.getMessage());
			}
		}

		return result;
	}

	// Anh The Anh lam tiep o day
	private boolean thanhPhanHoSoThucTuc(String userLogin, long documentName,
			int messageType, int documentYear, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest,
			ActionResponse httpReq) {
		log.info("==thanhPhanHoSoThucTuc==actionType==" + actionType);
		boolean result = true;
		try {
			String lyDoTuChoi = "";
			TempDocument tempDocument = null;
			InterfaceRequest interfaceRequest = null;
			Header header = null;
			BusinessUtils businessUtils = new BusinessUtils();

			tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);

			String xmlData = "";

			switch (actionType) {
			case PageType.ACTION_TYPE_TIEP_NHAN:
				log.info("==thanhPhanHoSoThucTuc==actionType==ACTION_TYPE_TIEP_NHAN");
				// Khi tiep nhan
				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA,
							MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					///SMS
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA_PTTND,
							MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
					
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_RA_PTTND,
								MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
								MessageType.TIN_NHAN_PTTND_VAOROI, userName,
								interfaceRequest);					
					}
					
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					///SMS
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_VAO_PTTND,
							MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
					
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_VAO_PTTND,
								MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
								MessageType.TIN_NHAN_PTTND_VAOROI, userName,
								interfaceRequest);					
					}
				} else {
					header = BusinessUtils.createHeader(tempDocument,
							MessageType.XUAT_CANH,
							MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
				}
								
				if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
				{
					xmlData = businessUtils.sendMessageAccept(header, BusinessUtils
							.getMaritimeShortNameFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(), tempDocument
									.getRequestCode());
				}
				else
				{
					xmlData = businessUtils.sendMessageAccept(header, BusinessUtils
							.getOrganizationFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(), tempDocument
									.getRequestCode());
				}

				if (tempDocument.getRequestState() == TrangThaiBanKhaiXuatCanh.CHO_TIEP_NHAN) {
					tempDocument
							.setRequestState(TrangThaiBanKhaiXuatCanh.DA_TIEP_NHAN);
					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.THU_TUC_DA_TIEP_NHAN);
					if (tempDocument.getDocumentTypeCode().equals(
							MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
						tempDocument
								.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
					}

				} else if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.THU_TUC_DA_TIEP_NHAN) {
					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC);

				} else if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.YEU_CAU_SUA_DOI_BO_SUNG) {
					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC);
				}

				break;
			case PageType.ACTION_TYPE_TU_CHOI:
				log.info("==thanhPhanHoSoThucTuc==actionType==ACTION_TYPE_TU_CHOI -  Thuc chat la nut yeu cau bo sung tren trang thanh phan ho so (dung.le)");
				// Khi YCBS
				lyDoTuChoi = ParamUtil.getString(resourceRequest,
						PageType.LY_DO_TU_CHOI);
				if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
					lyDoTuChoi = "System";
				}
				log.info("==thanhPhanHoSoThucTuc==lyDoTuChoi==" + lyDoTuChoi);
				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA,
							MessageType.FUNCTION_TU_SUA_DOI_BO_SUNG_THU_TUC,
							MessageType.HO_SO, userName, interfaceRequest);
					// Ho so Roi cang (DocumentTypeCode = 5) su dung message 99
					// - 25 de Can bo Thu tuc YCBS
					xmlData = businessUtils.sendMessageRejectInland(header,
							header.getReference().getMessageId(), lyDoTuChoi,
							BusinessUtils
									.getOrganizationFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(),
							tempDocument.getRequestCode());
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA_PTTND,
							MessageType.FUNCTION_TU_SUA_DOI_BO_SUNG_THU_TUC,
							MessageType.HO_SO, userName, interfaceRequest); // message
																			// 99
																			// -
																			// 25
																			// de
																			// Can
																			// bo
																			// Thu
																			// tuc
																			// YCBS

					xmlData = businessUtils.sendMessageRejectInland(header,
							header.getReference().getMessageId(), lyDoTuChoi,
							BusinessUtils
									.getOrganizationFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(),
							tempDocument.getRequestCode());
				}  else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_VAO_PTTND,
							MessageType.FUNCTION_TU_SUA_DOI_BO_SUNG_THU_TUC,
							MessageType.HO_SO, userName, interfaceRequest); // message
																			// 99
																			// -
																			// 25
																			// de
																			// Can
																			// bo
																			// Thu
																			// tuc
																			// YCBS

					xmlData = businessUtils.sendMessageRejectInland(header,
							header.getReference().getMessageId(), lyDoTuChoi,
							BusinessUtils
									.getOrganizationFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(),
							tempDocument.getRequestCode());
				} else {
					header = BusinessUtils.createHeader(tempDocument,
							MessageType.XUAT_CANH,
							MessageType.FUNCTION_TU_SUA_DOI_BO_XUNG_THU_TUC,
							MessageType.HO_SO, userName, interfaceRequest); // 99
																			// 28

					xmlData = businessUtils.sendMessageReject(header, header
							.getReference().getMessageId(), lyDoTuChoi,
							BusinessUtils
									.getOrganizationFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(),
							tempDocument.getRequestCode());
				}

				if (tempDocument.getRequestState() == TrangThaiBanKhaiXuatCanh.CHO_TIEP_NHAN) {
					tempDocument
							.setRequestState(TrangThaiBanKhaiXuatCanh.TU_CHOI_TIEP_NHAN);
					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);

				} else if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.THU_TUC_DA_TIEP_NHAN) {
					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.YEU_CAU_SUA_DOI_BO_SUNG);
				}

				log.info("FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH"
						+ MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH);
				// truong hop ho so gui yeu cau bo sung hoac, phe duyet hoan
				// thanh thu tuc
				if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.THUTUC_CHO_PHE_DUYET_HOAN_THANH_THU_TUC
						|| tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.THUTUC_YEU_CAU_SUA_DOI_BO_SUNG) {
					lyDoTuChoi = ParamUtil.getString(resourceRequest,
							PageType.LY_DO_TU_CHOI);
					insertOrUpdateResultNotification(tempDocument, userName,
							lyDoTuChoi, MessageType.BO_SUNG_THU_TUC);
					BusinessUtils
							.insertOrUpdateResultMinistry(
									(int)documentName,
									documentYear,
									MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH,
									BusinessUtils.getRemarkTuChoi(userName,
											lyDoTuChoi));
					BusinessUtils
							.insertResultHistoryMinistry(
									(int)documentName,
									documentYear,
									MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH,
									BusinessUtils.getRemarkTuChoi(userName,
											lyDoTuChoi));
				}

				break;
			
			case PageType.ACTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU:
				log.info("==thanhPhanHoSoThucTuc==actionType==ACTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU");
				// Khi YCBS
				lyDoTuChoi = ParamUtil.getString(resourceRequest,
						PageType.LY_DO_TU_CHOI);
				if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
					lyDoTuChoi = "System";
				}
				log.info("==thanhPhanHoSoThucTuc==lyDoTuChoi==" + lyDoTuChoi);
				tempDocument
				.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.TAM_DUNG_LAM_THU_TUC_DIEN_TU);
				// thanh thu tuc
				insertOrUpdateResultNotification(tempDocument, userName,
						lyDoTuChoi, Integer.valueOf(MessageType.FUNCTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU));
				BusinessUtils
						.insertOrUpdateResultMinistry(
								(int)documentName,
								documentYear,
								MessageType.FUNCTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU,
								BusinessUtils.getRemarkTuChoi(userName,
										lyDoTuChoi));
				BusinessUtils
						.insertResultHistoryMinistry(
								(int)documentName,
								documentYear,
								MessageType.FUNCTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU,
								BusinessUtils.getRemarkTuChoi(userName,
										lyDoTuChoi));

				break;
				
			case PageType.ACTION_TYPE_HOAN_THANH_THU_TUC:
				log.info("==thanhPhanHoSoThucTuc==actionType==ACTION_TYPE_HOAN_THANH_THU_TUC");
				tempDocument
				.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				// thanh thu tuc
				insertOrUpdateResultNotification(tempDocument, userName,
						lyDoTuChoi, Integer.valueOf(MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC));
				BusinessUtils
						.insertOrUpdateResultMinistry(
								(int)documentName,
								documentYear,
								MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC,
								BusinessUtils.getRemarkTuChoi(userName,
										lyDoTuChoi));
				BusinessUtils
						.insertResultHistoryMinistry(
								(int)documentName,
								documentYear,
								MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC,
								BusinessUtils.getRemarkTuChoi(userName,
										lyDoTuChoi));

				break;
				
			case PageType.ACTION_TYPE_HUY:
				log.info("==thanhPhanHoSoThucTuc==actionType==ACTION_TYPE_HUY");
				String lyDoHuyXC99 = ParamUtil.getString(resourceRequest,
						PageType.HUY_HO_SO);
				if (lyDoHuyXC99 == null || lyDoHuyXC99.length() == 0) {
					lyDoHuyXC99 = "lyDoHuyXC99";
				}

				BusinessUtils.insertOrUpdateResultMinistry((int)
						documentName
, documentYear,
						MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
						BusinessUtils.getRemarkHuy(userName, lyDoHuyXC99));
				BusinessUtils.insertResultHistoryMinistry((int)
						documentName
, documentYear,
						MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
						BusinessUtils.getRemarkHuy(userName, lyDoHuyXC99));
				log.info("===lyDoHuyXC99===" + lyDoHuyXC99);
				// Khi tu choi
				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA,
							MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
							MessageType.HO_SO, userName, interfaceRequest);
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					///SMS
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA_PTTND,
							MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
							MessageType.HO_SO, userName, interfaceRequest);
					
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_RA_PTTND,
								MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
								MessageType.TIN_NHAN_PTTND_VAOROI, userName,
								interfaceRequest);					
					}
					
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					///SMS
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_VAO_PTTND,
							MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
							MessageType.HO_SO, userName, interfaceRequest);
					
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_VAO_PTTND,
								MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
								MessageType.TIN_NHAN_PTTND_VAOROI, userName,
								interfaceRequest);					
					}
					
				} else {
					header = BusinessUtils.createHeader(tempDocument,
							MessageType.XUAT_CANH,
							MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
							MessageType.HO_SO, userName, interfaceRequest);
				}

				
				
				if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
				{
					xmlData = businessUtils.sendMessageHuyHoSo(header,
							BusinessUtils.getMaritimeShortNameFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(), lyDoHuyXC99,
							tempDocument.getRequestCode());
				}
				else
				{
					xmlData = businessUtils.sendMessageHuyHoSo(header,
							BusinessUtils.getOrganizationFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(), lyDoHuyXC99,
							tempDocument.getRequestCode());
				}

				if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC
				|| tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.THU_TUC_DA_TIEP_NHAN || tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.TU_CHOI_TIEP_NHAN
				|| tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.TAM_DUNG_LAM_THU_TUC_DIEN_TU)
				{
					xacNhanHoanThanhThuTucXuatCanhTruongHopHuy(resourceRequest,
							tempDocument.getDocumentName(),
							tempDocument.getDocumentYear(), lyDoHuyXC99);
					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
					BusinessUtils.updateLyDoResultNotification(userLogin,
							lyDoHuyXC99, MessageType.HO_SO,
							tempDocument.getMaritimeCode(), documentName,
							documentYear);
				}

				break;
			case PageType.ACTION_TYPE_SUA_DOI:
				log.info("==thanhPhanHoSoThucTuc==actionType==ACTION_TYPE_TU_CHOI -  Thuc chat la nut Tu choi tiep nhan tren trang thanh phan ho so (dung.le)");
				// Khi tu choi
				lyDoTuChoi = ParamUtil.getString(resourceRequest,
						PageType.LY_DO_SUADOI_BOSUNG);
				if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
					lyDoTuChoi = "System";
				}
				log.info("==thanhPhanHoSoThucTuc==lyDoTuChoi==" + lyDoTuChoi);
				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {

					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA,
							String.valueOf(MessageType.FUNCTION_TU_CHOI_HO_SO),
							MessageType.HO_SO, userName, interfaceRequest);

					xmlData = businessUtils.sendMessageRejectKH(header, header
							.getReference().getMessageId(), lyDoTuChoi,
							BusinessUtils
									.getOrganizationFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(),
							tempDocument.getRequestCode());
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					///SMS

					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA_PTTND,
							String.valueOf(MessageType.FUNCTION_TU_CHOI_HO_SO),
							MessageType.HO_SO, userName, interfaceRequest);
					
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_RA_PTTND,
								MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
								MessageType.TIN_NHAN_PTTND_VAOROI, userName,
								interfaceRequest);					
					}

									
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						xmlData = businessUtils.sendMessageRejectKH(header, header
								.getReference().getMessageId(), lyDoTuChoi,
								BusinessUtils
										.getMaritimeShortNameFromUser(resourceRequest),
								businessUtils.getDivision(resourceRequest), header
										.getFrom().getName(), new Date(),
								tempDocument.getRequestCode());
					}
					else
					{
						xmlData = businessUtils.sendMessageRejectKH(header, header
								.getReference().getMessageId(), lyDoTuChoi,
								BusinessUtils
										.getOrganizationFromUser(resourceRequest),
								businessUtils.getDivision(resourceRequest), header
										.getFrom().getName(), new Date(),
								tempDocument.getRequestCode());
					}
					
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					///SMS

					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_VAO_PTTND,
							String.valueOf(MessageType.FUNCTION_TU_CHOI_HO_SO),
							MessageType.HO_SO, userName, interfaceRequest);
					
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_VAO_PTTND,
								MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
								MessageType.TIN_NHAN_PTTND_VAOROI, userName,
								interfaceRequest);					
					}

					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						xmlData = businessUtils.sendMessageRejectKH(header, header
								.getReference().getMessageId(), lyDoTuChoi,
								BusinessUtils
										.getMaritimeShortNameFromUser(resourceRequest),
								businessUtils.getDivision(resourceRequest), header
										.getFrom().getName(), new Date(),
								tempDocument.getRequestCode());
					}
					else
					{
						xmlData = businessUtils.sendMessageRejectKH(header, header
								.getReference().getMessageId(), lyDoTuChoi,
								BusinessUtils
										.getOrganizationFromUser(resourceRequest),
								businessUtils.getDivision(resourceRequest), header
										.getFrom().getName(), new Date(),
								tempDocument.getRequestCode());
					}
					
				} else {
					header = BusinessUtils.createHeader(tempDocument,
							MessageType.XUAT_CANH,
							MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
							MessageType.HO_SO, userName, interfaceRequest);

					xmlData = businessUtils.sendMessageHuyHoSo(header,
							BusinessUtils
									.getOrganizationFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(),
							lyDoTuChoi, tempDocument.getRequestCode());
				}

				if (tempDocument.getRequestState() == TrangThaiBanKhaiXuatCanh.CHO_TIEP_NHAN
						|| tempDocument.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.THU_TUC_DA_TIEP_NHAN
						|| tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.THUTUC_YEU_CAU_SUA_DOI_BO_SUNG) {
					tempDocument
							.setRequestState(TrangThaiBanKhaiXuatCanh.TU_CHOI_TIEP_NHAN);
					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);

				}
				// else if (tempDocument.getDocumentStatusCode() ==
				// TrangThaiBanKhaiXuatCanh.THU_TUC_DA_TIEP_NHAN) {
				// tempDocument.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.YEU_CAU_SUA_DOI_BO_SUNG);
				// }

				// truong hop ho so gui yeu cau bo sung hoac, phe duyet hoan
				// thanh thu tuc
				if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH) {
					insertOrUpdateResultNotification(tempDocument, userName,
							lyDoTuChoi, MessageType.HO_SO);
					BusinessUtils
							.insertOrUpdateResultMinistry((int)
									documentName
, documentYear,
									MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
									BusinessUtils.getRemarkTuChoi(userName,
											lyDoTuChoi));
					BusinessUtils
							.insertResultHistoryMinistry(
									(int)documentName,
									documentYear,
									MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
									BusinessUtils.getRemarkTuChoi(userName,
											lyDoTuChoi));

				} else if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC) {

					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
					BusinessUtils.updateLyDoResultNotification(userName,
							lyDoTuChoi, MessageType.HO_SO,
							tempDocument.getMaritimeCode(), documentName,
							documentYear);
				}

				break;

			}
			System.out.println("XuatCanhHaiQuan2GiaoThongBusiness.thanhPhanHoSoThucTuc(update)");
			System.out.println("XuatCanhHaiQuan2GiaoThongBusiness.thanhPhanHoSoThucTuc()"+tempDocument);
			TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
			// SONVH commented 21.4.2016
			// if (xmlData != null && xmlData.trim().length() > 0) {
			// if
			// (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
			// ||
			// tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG))
			// {
			// guiBanTinSangHQMC_TauVaoRa(xmlData, businessUtils);
			// } else {
			// guiBanTinSangHQMC(xmlData, businessUtils);
			// }
			// }
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean toanBoHoSoKeHoach(long documentName, int messageType,
			int documentYear, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest,
			ActionResponse httpReq) {
		boolean result = true;
		try {

			TempDocument tempDocument = null;
			InterfaceRequest interfaceRequest = null;
			Header header = null;

			BusinessUtils businessUtils = new BusinessUtils();

			String lyDoTuChoi = ParamUtil.getString(resourceRequest,
					PageType.LY_DO_TU_CHOI);
			if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
				lyDoTuChoi = "System";
			}

			tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);

			String xmlData = "";

			switch (actionType) {
			case PageType.ACTION_TYPE_TIEP_NHAN:
				// Khi tiep nhan

				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA,
							MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					///SMS
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA_PTTND,
							MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
					
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_RA_PTTND,
								MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
								MessageType.TIN_NHAN_PTTND_VAOROI, userName,
								interfaceRequest);					
					}
										
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					///SMS
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_VAO_PTTND,
							MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
					
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_VAO_PTTND,
								MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
								MessageType.TIN_NHAN_PTTND_VAOROI, userName,
								interfaceRequest);					
					}
				} else {
					header = BusinessUtils.createHeader(tempDocument,
							MessageType.XUAT_CANH,
							MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
				}

				// xmlData = businessUtils.sendMessageAccept(header,
				// BusinessUtils.getOrganizationFromUser(resourceRequest),
				// businessUtils.getDivision(resourceRequest),
				// header.getFrom().getName(), new Date());

				List<ResultDeclaration> lstDeclarations = ResultDeclarationLocalServiceUtil
						.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
								MessageType.THONG_BAO_TAU_ROI_CANG,
								tempDocument.getDocumentName(),
								tempDocument.getDocumentYear());

				if (lstDeclarations != null && lstDeclarations.size() > 0) {
					ResultDeclaration declaration = lstDeclarations.get(0);

					TempNoticeShipMessage noticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
							.findTempNoTiceShipMessageByRequestCode(declaration
									.getRequestCode());

					if (noticeShipMessage != null
							&& noticeShipMessage.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN
							&& noticeShipMessage.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI) {

						noticeShipMessage
								.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);

						BusinessUtils
								.updateResultDeclaration(
										MessageType.THONG_BAO_TAU_ROI_CANG,
										tempDocument.getDocumentName(),
										tempDocument.getDocumentYear(),
										TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);

						TempNoTiceShipMessageLocalServiceUtil
								.updateTempNoTiceShipMessage(noticeShipMessage);

					}
				}

				if (tempDocument.getRequestState() == TrangThaiBanKhaiXuatCanh.CHO_TIEP_NHAN
						|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {

					tempDocument
							.setRequestState(TrangThaiBanKhaiXuatCanh.DA_TIEP_NHAN);
					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.THU_TUC_DA_TIEP_NHAN);

					if (tempDocument.getDocumentTypeCode().equals(
							MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
						tempDocument
								.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
					}

				}

				break;
			case PageType.ACTION_TYPE_TU_CHOI:

				BusinessUtils.insertOrUpdateResultMinistry((int)
						documentName
, documentYear,
						MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
						BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));

				BusinessUtils.insertResultHistoryMinistry((int)
						documentName
, documentYear,
						MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
						BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));

				// Khi tu choi
				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA,
							MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					///SMS
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA_PTTND,
							MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
					
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_RA_PTTND,
								MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
								MessageType.TIN_NHAN_PTTND_VAOROI, userName,
								interfaceRequest);					
					}
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					///SMS
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_VAO_PTTND,
							MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);
					
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_VAO_PTTND,
								MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
								MessageType.TIN_NHAN_PTTND_VAOROI, userName,
								interfaceRequest);					
					}
				} else {
					header = BusinessUtils.createHeader(tempDocument,
							MessageType.XUAT_CANH,
							MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
							MessageType.HO_SO, userName, interfaceRequest);

				}
				
				
				
				if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
				{
					xmlData = businessUtils.sendMessageRejectKH(header, header
							.getReference().getMessageId(), lyDoTuChoi,
							BusinessUtils.getMaritimeShortNameFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(), tempDocument
									.getRequestCode());
				}
				else
				{
					xmlData = businessUtils.sendMessageRejectKH(header, header
							.getReference().getMessageId(), lyDoTuChoi,
							BusinessUtils.getOrganizationFromUser(resourceRequest),
							businessUtils.getDivision(resourceRequest), header
									.getFrom().getName(), new Date(), tempDocument
									.getRequestCode());
				}

				if (tempDocument.getRequestState() == TrangThaiBanKhaiXuatCanh.CHO_TIEP_NHAN
						|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {
					tempDocument
							.setRequestState(TrangThaiBanKhaiXuatCanh.TU_CHOI_TIEP_NHAN);
					// tempDocument.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
				}
				break;
			case PageType.ACTION_TYPE_SUA_DOI:
				xmlData = suaDoiKeHoach(documentName, messageType,
						documentYear, userName, resourceRequest, tempDocument,
						interfaceRequest, businessUtils);
				break;

			case PageType.ACTION_TYPE_NOP_TRUC_TIEP:
				xmlData = thongBaoNopTrucTiep(documentName, messageType,
						documentYear, userName, resourceRequest, tempDocument,
						interfaceRequest, businessUtils);
				break;

			}

			log.info("==tempDocument===ShipTypeCode=="
					+ tempDocument.getShipTypeCode());
			log.info("==tempDocument===" + tempDocument);
			log.info("==tempDocument.getRequestState==="
					+ tempDocument.getRequestState());

			TempDocument document = TempDocumentLocalServiceUtil
					.updateTempDocument(tempDocument);
			log.info("==tempDocument.getRequestState=="
					+ document.getRequestState());

			// SONVH commented 21.04.2016
			// if (xmlData != null && xmlData.trim().length() > 0) {
			// if
			// (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
			// ||
			// tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG))
			// {
			// guiBanTinSangHQMC_TauVaoRa(xmlData, businessUtils);
			// } else {
			// guiBanTinSangHQMC(xmlData, businessUtils);
			// }
			// }
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}

	private String suaDoiKeHoach(long documentName, int messageType,
			int documentYear, String userName, ActionRequest resourceRequest,
			TempDocument tempDocument, InterfaceRequest interfaceRequest,
			BusinessUtils businessUtils) throws SystemException {
		String xmlData;
		if (tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_TIEP_NHAN
				|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {

			tempDocument
					.setRequestState(TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG);
			TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
			String lyDoSuaDoiBoSung = ParamUtil.getString(resourceRequest,
					PageType.LY_DO_SUADOI_BOSUNG);
			if (lyDoSuaDoiBoSung == null || lyDoSuaDoiBoSung.length() == 0) {
				lyDoSuaDoiBoSung = "System";
				log.info("LY_DO_SUADOI_BOSUNG"
						+ "=---System---Sai PageType.LY_DO_SUADOI_BOSUNG");
			}

			BusinessUtils.insertOrUpdateResultMinistry(
					(int)documentName, documentYear,
					MessageType.FUNCTION_THONG_BAO_BO_XUNG,
					BusinessUtils.getRemarkBoSung(userName, lyDoSuaDoiBoSung));

			BusinessUtils.insertResultHistoryMinistry(
					(int)documentName, documentYear,
					MessageType.FUNCTION_THONG_BAO_BO_XUNG,
					BusinessUtils.getRemarkBoSung(userName, lyDoSuaDoiBoSung));

			// ResultNotification

			insertOrUpdateResultNotification(tempDocument, userName,
					lyDoSuaDoiBoSung, MessageType.BO_SUNG_KE_HOACH);

		}
		xmlData = createXmlSuaDoiBoXung(messageType, tempDocument, userName,
				resourceRequest, businessUtils, interfaceRequest);
		return xmlData;
	}

	private String thongBaoNopTrucTiep(long documentName, int messageType,
			int documentYear, String userName, ActionRequest resourceRequest,
			TempDocument tempDocument, InterfaceRequest interfaceRequest,
			BusinessUtils businessUtils) throws SystemException {
		String xmlData;
		String lyDoSuaDoiBoSung = ParamUtil.getString(resourceRequest,
				PageType.LY_DO_NOP_TRUC_TIEP);
		if (lyDoSuaDoiBoSung == null || lyDoSuaDoiBoSung.length() == 0) {
			lyDoSuaDoiBoSung = "THONG_BAO_NOP_TRUC_TIEP";
			log.info("LY_DO_NOP_TRUC_TIEP"
					+ "=---System---Sai PageType.LY_DO_NOP_TRUC_TIEP");
		}
		if (tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_TIEP_NHAN
				|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {
			tempDocument.setRequestState(TrangThaiBanKhaiXuatCanh.DA_TIEP_NHAN);
			tempDocument
					.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.THU_TUC_DA_TIEP_NHAN);
			if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
				tempDocument
						.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
			}
			TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);

			BusinessUtils.insertOrUpdateResultMinistry(
					(int)documentName, documentYear,
					MessageType.FUNCTION_THONG_BAO_NOP_TRUC_TIEP,
					BusinessUtils.getRemarkBoSung(userName, lyDoSuaDoiBoSung));

			BusinessUtils.insertResultHistoryMinistry(
					(int)documentName, documentYear,
					MessageType.FUNCTION_THONG_BAO_NOP_TRUC_TIEP,
					BusinessUtils.getRemarkBoSung(userName, lyDoSuaDoiBoSung));

			// ResultNotification

			insertOrUpdateResultNotification(tempDocument, userName,
					lyDoSuaDoiBoSung, MessageType.YC_LAM_THU_TUC_TRUC_TIEP);

		}
		String function = MessageType.FUNCTION_THONG_BAO_NOP_TRUC_TIEP;

		if (interfaceRequest == null) {
			interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument
							.getRequestCode());
		}

		Header header = null;
		if (tempDocument.getDocumentTypeCode().equals(
				MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
			header = BusinessUtils.createHeaderInland(tempDocument,
					MessageType.TAU_RA_PTTND,
					MessageType.FUNCTION_THONG_BAO_NOP_TRUC_TIEP,
					MessageType.HO_SO, userName, interfaceRequest);
		} else if (tempDocument.getDocumentTypeCode().equals(
				MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
			header = BusinessUtils.createHeaderInland(tempDocument,
					MessageType.TAU_VAO_PTTND,
					MessageType.FUNCTION_THONG_BAO_NOP_TRUC_TIEP,
					MessageType.HO_SO, userName, interfaceRequest);
		}

		vn.gt.tichhop.message.Modify modify = new vn.gt.tichhop.message.Modify();
		modify.setDivision(businessUtils.getDivision(resourceRequest));
		modify.setOrganization(BusinessUtils
				.getOrganizationFromUser(resourceRequest));
		modify.setName(header.getFrom().getName());
		modify.setModifyDate(FormatData.parseDateToTring(new Date()));
		modify.setModifyDesc(lyDoSuaDoiBoSung);
		modify.setModifyCode("THONG_BAO_NOP_TRUC_TIEP");

		xmlData = businessUtils.sendMessageModify(header, modify,
				tempDocument.getRequestCode());

		return xmlData;
	}

	private boolean xacNhanHoanThanhThuTuc(long documentName, int messageType,
			int documentYear, int actionType, String userName,
			String requestCode, ActionRequest request, ActionResponse httpReq) {

		boolean result = true;

		log.info("===xacNhanHoanThanhThuTuc==actionType==" + actionType
				+ "==messageType==" + messageType);

		try {

			TempDocument tempDocument = null;
			InterfaceRequest interfaceRequest = null;
			Header header = null;
			BusinessUtils businessUtils = new BusinessUtils();

			String lyDoTuChoi = ParamUtil.getString(request,
					PageType.LY_DO_TU_CHOI);
			if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
				lyDoTuChoi = "System";
			}
			tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);

			String xmlData = "";

			switch (actionType) {

			case PageType.ACTION_TYPE_HOAN_THANH_THU_TUC:
				// doi Minh sua phan cap giay phep
				if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC) {
					String flagStateOfShip = ParamUtil.getString(request,
							"flagStateOfShip");
					double grossTonnage = ParamUtil.getDouble(request,
							"grossTonnage", 0L);

					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP);
					tempDocument.setStateCode(flagStateOfShip);
					tempDocument.setGrt(grossTonnage);
				}

				xacNhanHoanThanhThuTucXuatCanh(request, documentName,
						documentYear);
				break;
			case PageType.ACTION_TYPE_HUY:
				String reason = ParamUtil
						.getString(request, PageType.HUY_HO_SO);

				if (reason == null || reason.length() == 0) {
					reason = "System";
				}
				xacNhanHoanThanhThuTucXuatCanhTruongHopHuy(request,
						tempDocument.getDocumentName(),
						tempDocument.getDocumentYear(), reason);

				log.info("==reason==" + reason);
				// // Khi tu choi
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.XUAT_CANH,
						MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
						MessageType.HO_SO, userName, interfaceRequest);

				xmlData = businessUtils.sendMessageHuyHoSo(header,
						BusinessUtils.getOrganizationFromUser(request),
						businessUtils.getDivision(request), header.getFrom()
								.getName(), new Date(), reason, tempDocument
								.getRequestCode());

				if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC) {
					tempDocument
							.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
				}

				break;

			// can ho hai quan gui thong bao den doanh nghiep, anh The Anh
			case PageType.ACTION_TYPE_SUA_DOI:

				log.info("truong hop can bo hang hai gui thong diep sang bo giao thong, thong bao thieu ho so");
				log.info("actionType==" + actionType);
				lyDoTuChoi = ParamUtil.getString(request,
						PageType.LY_DO_SUADOI_BOSUNG);
				if (lyDoTuChoi.trim().length() == 0) {
					log.info("PageType.LY_DO_SUADOI_BOSUNG  sai");
					lyDoTuChoi = "System";
				}
				xmlData = createXmlSuaDoiBoXung(messageType, tempDocument,
						userName, request, businessUtils, interfaceRequest);
				insertOrUpdateResultNotification(tempDocument, userName,
						lyDoTuChoi, MessageType.BO_SUNG_KE_HOACH);
				BusinessUtils
						.insertOrUpdateResultMinistry(
								(int)documentName,
								documentYear,
								MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH,
								BusinessUtils.getRemarkTuChoi(userName,
										lyDoTuChoi));
				BusinessUtils
						.insertResultHistoryMinistry(
								(int)documentName,
								documentYear,
								MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH,
								BusinessUtils.getRemarkTuChoi(userName,
										lyDoTuChoi));

				break;
			}
			TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);

			// SONVH commented 21.04.2016
			// if (xmlData != null && xmlData.trim().length() > 0) {
			// if
			// (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
			// ||
			// tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG))
			// {
			// guiBanTinSangHQMC_TauVaoRa(xmlData, businessUtils);
			// } else {
			// guiBanTinSangHQMC(xmlData, businessUtils);
			// }
			// }
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean banKhaiDanhSachHangHoa(long documentName, int messageType,
			int documentYear, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest) {
		boolean result = false;
		TempCargoDeclaration tempCargoDeclaration = null;
		String _requestCode = requestCode;
		TempDocument tempDocument = null;
		InterfaceRequest interfaceRequest = null;
		Header header = null;
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = "";
		int trangThaiBanKhai = 0;

		switch (actionType) {
		case PageType.ACTION_TYPE_TIEP_NHAN:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_HANG_HOA);
			tempCargoDeclaration = TempCargoDeclarationLocalServiceUtil
					.findTempCargoDeclarationByRequestCode(_requestCode);

			if (tempCargoDeclaration != null) {
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				if (interfaceRequest != null) {
					try {
						interfaceRequest.setRemarks(BusinessUtils
								.getRemarkChapNhan(userName));

						InterfaceRequestLocalServiceUtil
								.updateInterfaceRequest(interfaceRequest);
					} catch (Exception e) {

					}
				}

				header = BusinessUtils.createHeader(
								tempDocument,
								MessageType.XUAT_CANH,
								FormatData
										.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
								messageType, userName, interfaceRequest);

				// Gui thong tin di.
				xmlData = businessUtils.sendMessageAccept(header,
						getOrganization(businessUtils, resourceRequest),
						getDevision(businessUtils, resourceRequest), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI;
			}
			break;
		case PageType.ACTION_TYPE_TU_CHOI:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_HANG_HOA);
			tempCargoDeclaration = TempCargoDeclarationLocalServiceUtil
					.findTempCargoDeclarationByRequestCode(_requestCode);

			if (tempCargoDeclaration != null) {
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				if (interfaceRequest != null) {
					try {
						interfaceRequest.setRemarks(BusinessUtils
								.getRemarkTuChoi(userName, ParamUtil
										.getString(resourceRequest,
												PageType.LY_DO_TU_CHOI)));

						InterfaceRequestLocalServiceUtil
								.updateInterfaceRequest(interfaceRequest);
					} catch (Exception e) {

					}
				}
				header = BusinessUtils.createHeader(
								tempDocument,
								MessageType.XUAT_CANH,
								FormatData
										.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
								messageType, userName, interfaceRequest);

				// Gui thong tin di.
				// xmlData = businessUtils.sendMessageAccept(header,
				// getOrganization(), getDevision(), userName, new Date());
				businessUtils.sendMessageReject(header, "", ParamUtil
						.getString(resourceRequest, PageType.LY_DO_TU_CHOI),
						getOrganization(businessUtils, resourceRequest),
						getDevision(businessUtils, resourceRequest), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.TU_CHOI_BAN_KHAI;
			}
			break;
		}

		if (trangThaiBanKhai > 0 || tempCargoDeclaration != null) {
			tempCargoDeclaration.setRequestState(trangThaiBanKhai);
			try {
				TempCargoDeclarationLocalServiceUtil
						.updateTempCargoDeclaration(tempCargoDeclaration);
			} catch (Exception e) {
				log.error(e.getMessage());
				log.equals(e);
			}
		}

		// SONVH commented 21.04.2016
		// if (xmlData != null && xmlData.trim().length() > 0) {
		// if
		// (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
		// ||
		// tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG))
		// {
		// guiBanTinSangHQMC_TauVaoRa(xmlData, businessUtils);
		// } else {
		// guiBanTinSangHQMC(xmlData, businessUtils);
		// }
		// }

		return result;
	}

	private boolean banKhaiDanhSachHanhKhach(long documentName,
			int messageType, int documentYear, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest) {
		boolean result = false;
		TempPassengerList tempPassengerList = null;
		String _requestCode = requestCode;
		TempDocument tempDocument = null;
		InterfaceRequest interfaceRequest = null;
		Header header = null;
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = "";
		int trangThaiBanKhai = 0;

		switch (actionType) {
		case PageType.ACTION_TYPE_TIEP_NHAN:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH);
			tempPassengerList = TempPassengerListLocalServiceUtil
					.findTempPassengerListByRequestCode(_requestCode);

			if (tempPassengerList != null) {
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				if (interfaceRequest != null) {
					try {
						interfaceRequest.setRemarks(BusinessUtils
								.getRemarkChapNhan(userName));

						InterfaceRequestLocalServiceUtil
								.updateInterfaceRequest(interfaceRequest);
					} catch (Exception e) {

					}
				}
				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_VAO_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				} else {
					header = BusinessUtils.createHeader(
									tempDocument,
									MessageType.XUAT_CANH,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				}

				// Gui thong tin di.
				xmlData = businessUtils.sendMessageAccept(header,
						getOrganization(businessUtils, resourceRequest),
						getDevision(businessUtils, resourceRequest), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI;
			}
			break;
		case PageType.ACTION_TYPE_TU_CHOI:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH);
			tempPassengerList = TempPassengerListLocalServiceUtil
					.findTempPassengerListByRequestCode(_requestCode);

			if (tempPassengerList != null) {
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				if (interfaceRequest != null) {
					try {
						interfaceRequest.setRemarks(BusinessUtils
								.getRemarkTuChoi(userName, ParamUtil
										.getString(resourceRequest,
												PageType.LY_DO_TU_CHOI)));

						InterfaceRequestLocalServiceUtil
								.updateInterfaceRequest(interfaceRequest);
					} catch (Exception e) {

					}
				}
				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									MessageType.HO_SO, userName,
									interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									MessageType.HO_SO, userName,
									interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_VAO_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									MessageType.HO_SO, userName,
									interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				} else {
					header = BusinessUtils.createHeader(
									tempDocument,
									MessageType.XUAT_CANH,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									messageType, userName, interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				}
				// Gui thong tin di.
				// xmlData = businessUtils.sendMessageAccept(header,
				// getOrganization(), getDevision(), userName, new Date());
				// xmlData = businessUtils.sendMessageReject(header, "",
				// ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI),
				// getOrganization(businessUtils, resourceRequest),
				// getDevision(businessUtils, resourceRequest), userName, new
				// Date());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.TU_CHOI_BAN_KHAI;
			}
			break;
		}

		if (trangThaiBanKhai > 0 || tempPassengerList != null) {
			tempPassengerList.setRequestState(trangThaiBanKhai);
			try {
				TempPassengerListLocalServiceUtil
						.updateTempPassengerList(tempPassengerList);
			} catch (Exception e) {
				log.error(e.getMessage());
				log.equals(e);
			}
		}

		// SONVH commented 21.04.2016
		// if (xmlData != null && xmlData.trim().length() > 0) {
		// if
		// (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
		// ||
		// tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG))
		// {
		// guiBanTinSangHQMC_TauVaoRa(xmlData, businessUtils);
		// } else {
		// guiBanTinSangHQMC(xmlData, businessUtils);
		// }
		// }

		return result;
	}

	private boolean banKhaiDanhSachThuyenVien(long documentName,
			int messageType, int documentYear, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest) {
		boolean result = false;
		TempCrewList tempCrewList = null;
		String _requestCode = requestCode;
		TempDocument tempDocument = null;
		InterfaceRequest interfaceRequest = null;
		Header header = null;
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = "";
		int trangThaiBanKhai = 0;
		tempDocument = TempDocumentLocalServiceUtil
				.findTemDocumentByDocumentNameDocumentYear(documentName,
						documentYear);

		switch (actionType) {
		case PageType.ACTION_TYPE_TIEP_NHAN:
			log.info("---banKhaiDanhSachThuyenVien---ACTION_TYPE_TIEP_NHAN");
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
			tempCrewList = TempCrewListLocalServiceUtil
					.findTempCrewListByRequestCode(_requestCode);

			if (tempCrewList != null) {

				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				if (interfaceRequest != null) {
					try {
						interfaceRequest.setRemarks(BusinessUtils
								.getRemarkChapNhan(userName));

						InterfaceRequestLocalServiceUtil
								.updateInterfaceRequest(interfaceRequest);
					} catch (Exception e) {

					}
				}
				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_VAO_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				} else {
					header = BusinessUtils.createHeader(
									tempDocument,
									MessageType.XUAT_CANH,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				}

				// Gui thong tin di.
				xmlData = businessUtils.sendMessageAccept(header,
						getOrganization(businessUtils, resourceRequest),
						getDevision(businessUtils, resourceRequest), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI;
			}
			break;
		case PageType.ACTION_TYPE_TU_CHOI:
			log.info("---banKhaiDanhSachThuyenVien---ACTION_TYPE_TU_CHOI");
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
			tempCrewList = TempCrewListLocalServiceUtil
					.findTempCrewListByRequestCode(_requestCode);

			if (tempCrewList != null) {
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				if (interfaceRequest != null) {
					try {
						interfaceRequest.setRemarks(BusinessUtils
								.getRemarkTuChoi(userName, ParamUtil
										.getString(resourceRequest,
												PageType.LY_DO_TU_CHOI)));

						InterfaceRequestLocalServiceUtil
								.updateInterfaceRequest(interfaceRequest);
					} catch (Exception e) {

					}
				}
				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									MessageType.HO_SO, userName,
									interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									MessageType.HO_SO, userName,
									interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_VAO_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									MessageType.HO_SO, userName,
									interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				} else {
					header = BusinessUtils.createHeader(
									tempDocument,
									MessageType.XUAT_CANH,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									messageType, userName, interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				}

				// Gui thong tin di.
				// xmlData = businessUtils.sendMessageAccept(header,
				// getOrganization(), getDevision(), userName, new Date());
				// xmlData = businessUtils.sendMessageReject(header, "",
				// ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI),
				// getOrganization(businessUtils, resourceRequest),
				// getDevision(businessUtils, resourceRequest), userName, new
				// Date());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.TU_CHOI_BAN_KHAI;
			}
			break;
		case PageType.ACTION_TYPE_DOI_CHIEU:
			log.info("---banKhaiDanhSachThuyenVien---ACTION_TYPE_DOI_CHIEU");
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
			tempCrewList = TempCrewListLocalServiceUtil
					.findTempCrewListByRequestCode(_requestCode);

			if (tempCrewList != null) {
				if (tempCrewList.getRequestState() != TrangThaiBanKhaiXuatCanh.DOI_CHIEU) {

					// Gui thong tin di.
					xmlData = null;
					trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.DOI_CHIEU;
				}
			}
			log.info("===Docname == " + tempDocument.getDocumentName()
					+ "==docYear==" + tempDocument.getDocumentYear()
					+ "==RequestCode==" + requestCode);
			if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG)
					|| tempDocument.getDocumentTypeCode().equals(
							MessageType.LOAT_THU_TUC_ROI_CANG)
					|| tempDocument.getDocumentTypeCode().equals(
							MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)
					|| tempDocument.getDocumentTypeCode().equals(
							MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
				ResultDeclaration resultDeclaration = ResultDeclarationLocalServiceUtil
						.findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
								MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN,
								documentName, documentYear, _requestCode);
				if (resultDeclaration != null) {
					resultDeclaration.setRemarks(BusinessUtils
							.getRemarkChapNhan(userName));
					try {
						ResultDeclarationLocalServiceUtil
								.updateResultDeclaration(resultDeclaration);
					} catch (SystemException e) {
						log.error(e.getMessage());
						log.error(e.getMessage());
					}
				} else {
					log.info("resultDeclaration is null");
				}
			}

			BusinessUtils.updateResultDeclaration(
					MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN, documentName,
					documentYear,
					TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			break;
		}

		if (trangThaiBanKhai > 0 || tempCrewList != null) {
			tempCrewList.setRequestState(trangThaiBanKhai);
			try {
				TempCrewListLocalServiceUtil.updateTempCrewList(tempCrewList);
			} catch (Exception e) {
				log.error(e.getMessage());
				log.equals(e);
			}
		}

		// SONVH commented 21.04.2016
		// if (xmlData != null && xmlData.trim().length() > 0) {
		// if
		// (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
		// ||
		// tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG))
		// {
		// guiBanTinSangHQMC_TauVaoRa(xmlData, businessUtils);
		// } else {
		// guiBanTinSangHQMC(xmlData, businessUtils);
		// }
		// }

		return result;
	}

	private boolean banKhaiDuTruCuaTau(long documentName, int messageType,
			int documentYear, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest) {
		boolean result = false;
		TempShipStoresDeclaration tempShipStoresDeclaration = null;
		String _requestCode = requestCode;
		TempDocument tempDocument = null;
		InterfaceRequest interfaceRequest = null;
		Header header = null;
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = "";
		int trangThaiBanKhai = 0;

		switch (actionType) {
		case PageType.ACTION_TYPE_TIEP_NHAN:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_DU_TRU_CUA_TAU);
			tempShipStoresDeclaration = TempShipStoresDeclarationLocalServiceUtil
					.findTempShipStoresDeclarationByRequestCode(_requestCode);

			if (tempShipStoresDeclaration != null) {
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				header = BusinessUtils.createHeader(
								tempDocument,
								MessageType.XUAT_CANH,
								FormatData
										.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
								messageType, userName, interfaceRequest);

				// Gui thong tin di.
				xmlData = businessUtils.sendMessageAccept(header,
						getOrganization(businessUtils, resourceRequest),
						getDevision(businessUtils, resourceRequest), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI;
			}
			break;
		case PageType.ACTION_TYPE_TU_CHOI:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_DU_TRU_CUA_TAU);
			tempShipStoresDeclaration = TempShipStoresDeclarationLocalServiceUtil
					.findTempShipStoresDeclarationByRequestCode(_requestCode);

			if (tempShipStoresDeclaration != null) {
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				header = BusinessUtils.createHeader(
								tempDocument,
								MessageType.XUAT_CANH,
								FormatData
										.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
								messageType, userName, interfaceRequest);

				// Gui thong tin di.
				// xmlData = businessUtils.sendMessageAccept(header,
				// getOrganization(), getDevision(), userName, new Date());
				xmlData = businessUtils.sendMessageReject(header, "", ParamUtil
						.getString(resourceRequest, PageType.LY_DO_TU_CHOI),
						getOrganization(businessUtils, resourceRequest),
						getDevision(businessUtils, resourceRequest), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.TU_CHOI_BAN_KHAI;
			}
			break;
		}

		if (trangThaiBanKhai > 0 || tempShipStoresDeclaration != null) {
			tempShipStoresDeclaration.setRequestState(trangThaiBanKhai);
			try {
				TempShipStoresDeclarationLocalServiceUtil
						.updateTempShipStoresDeclaration(tempShipStoresDeclaration);
			} catch (Exception e) {
				log.error(e.getMessage());
				log.equals(e);
			}
		}

		// SONVH commented 21.04.2016
		// if (xmlData != null && xmlData.trim().length() > 0) {
		// if
		// (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
		// ||
		// tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG))
		// {
		// guiBanTinSangHQMC_TauVaoRa(xmlData, businessUtils);
		// } else {
		// guiBanTinSangHQMC(xmlData, businessUtils);
		// }
		// }

		return result;
	}

	private boolean banKhaiDanhSachHanhLyHanhKhachRoiTau(long documentName,
			int messageType, int documentYear, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest) {
		boolean result = false;
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = null;
		String _requestCode = requestCode;
		TempDocument tempDocument = null;
		InterfaceRequest interfaceRequest = null;
		Header header = null;
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = "";
		int trangThaiBanKhai = 0;

		switch (actionType) {
		case PageType.ACTION_TYPE_TIEP_NHAN:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_HANG_HOA);
			tempCrewEffectsDeclaration = TempCrewEffectsDeclarationLocalServiceUtil
					.findTempCrewEffectsDeclarationByRequestCode(_requestCode);

			if (tempCrewEffectsDeclaration != null) {
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				header = BusinessUtils.createHeader(
								tempDocument,
								MessageType.XUAT_CANH,
								FormatData
										.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
								messageType, userName, interfaceRequest);

				// Gui thong tin di.
				xmlData = businessUtils.sendMessageAccept(header,
						getOrganization(businessUtils, resourceRequest),
						getDevision(businessUtils, resourceRequest), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI;
			}
			break;
		case PageType.ACTION_TYPE_TU_CHOI:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_HANG_HOA);
			tempCrewEffectsDeclaration = TempCrewEffectsDeclarationLocalServiceUtil
					.findTempCrewEffectsDeclarationByRequestCode(_requestCode);

			if (tempCrewEffectsDeclaration != null) {
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				header = BusinessUtils.createHeader(
								tempDocument,
								MessageType.XUAT_CANH,
								FormatData
										.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
								messageType, userName, interfaceRequest);

				// Gui thong tin di.
				// xmlData = businessUtils.sendMessageAccept(header,
				// getOrganization(), getDevision(), userName, new Date());
				xmlData = businessUtils.sendMessageReject(header, "", ParamUtil
						.getString(resourceRequest, PageType.LY_DO_TU_CHOI),
						getOrganization(businessUtils, resourceRequest),
						getDevision(businessUtils, resourceRequest), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.TU_CHOI_BAN_KHAI;
			}
			break;
		}

		if (trangThaiBanKhai > 0 || tempCrewEffectsDeclaration != null) {
			tempCrewEffectsDeclaration.setRequestState(trangThaiBanKhai);
			try {
				TempCrewEffectsDeclarationLocalServiceUtil
						.updateTempCrewEffectsDeclaration(tempCrewEffectsDeclaration);
			} catch (Exception e) {
				log.error(e.getMessage());
				log.equals(e);
			}
		}

		// SONVH commented 21.04.2016
		// if (xmlData != null && xmlData.trim().length() > 0) {
		// if
		// (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
		// ||
		// tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG))
		// {
		// guiBanTinSangHQMC_TauVaoRa(xmlData, businessUtils);
		// } else {
		// guiBanTinSangHQMC(xmlData, businessUtils);
		// }
		// }

		return result;
	}

	private boolean banKhaiThongBaoTauRoiCang(long documentName,
			int messageType, int documentYear, int actionType, String userName,
			String requestCodePortClearn, ActionRequest request) {

		boolean result = false;
		TempNoticeShipMessage tempNoTiceShipMessage = null;
		String _requestCode = requestCodePortClearn;
		TempDocument tempDocument = null;
		InterfaceRequest interfaceRequest = null;
		Header header = null;
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = "";
		int trangThaiBanKhai = 0;

		switch (actionType) {
		case PageType.ACTION_TYPE_TIEP_NHAN:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.THONG_BAO_TAU_ROI_CANG);
			interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(_requestCode);
			log.info("====call banKhaiThongBaoTauRoiCang=====_requestCode=="
					+ _requestCode);
			tempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil
					.findTempNoTiceShipMessageByRequestCode(_requestCode);
			log.info("====call banKhaiThongBaoTauRoiCang=====tempNoTiceShipMessage=="
					+ tempNoTiceShipMessage.toString());

			if (tempNoTiceShipMessage != null) {
				interfaceRequest.setRemarks(BusinessUtils
						.getRemarkChapNhan(userName));
				try {
					InterfaceRequestLocalServiceUtil
							.updateInterfaceRequest(interfaceRequest);
				} catch (SystemException e1) {

					e1.printStackTrace();
				}
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);

				header = BusinessUtils.createHeader(
								tempDocument,
								MessageType.XUAT_CANH,
								FormatData
										.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
								messageType, userName, interfaceRequest);

				// Gui thong tin di.
				xmlData = businessUtils.sendMessageAccept(header,
						getOrganization(businessUtils, request),
						getDevision(businessUtils, request), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI;
				BusinessUtils.updateResultDeclaration(
						MessageType.THONG_BAO_TAU_ROI_CANG,
						tempDocument.getDocumentName(),
						tempDocument.getDocumentYear(),
						TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
			}
			break;
		case PageType.ACTION_TYPE_TU_CHOI:
			String lyDoTuChoi = ParamUtil.getString(request,
					PageType.LY_DO_TU_CHOI);
			if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
				lyDoTuChoi = "System";
			}
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.THONG_BAO_TAU_ROI_CANG);
			interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(_requestCode);
			tempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil
					.findTempNoTiceShipMessageByRequestCode(_requestCode);

			if (tempNoTiceShipMessage != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(
						userName, lyDoTuChoi));
				try {
					InterfaceRequestLocalServiceUtil
							.updateInterfaceRequest(interfaceRequest);
				} catch (SystemException e1) {

					e1.printStackTrace();
				}
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);

				header = BusinessUtils.createHeader(
								tempDocument,
								MessageType.XUAT_CANH,
								FormatData
										.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
								messageType, userName, interfaceRequest);

				// Gui thong tin di.
				xmlData = businessUtils.sendMessageReject(header, "",
						ParamUtil.getString(request, PageType.LY_DO_TU_CHOI),
						getOrganization(businessUtils, request),
						getDevision(businessUtils, request), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.TU_CHOI_BAN_KHAI;
				BusinessUtils.updateResultDeclaration(
						MessageType.THONG_BAO_TAU_ROI_CANG,
						tempDocument.getDocumentName(),
						tempDocument.getDocumentYear(),
						TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
			}
			break;
		case PageType.ACTION_TYPE_HUY:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.THONG_BAO_TAU_ROI_CANG);
			tempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil
					.findTempNoTiceShipMessageByRequestCode(_requestCode);

			if (tempNoTiceShipMessage != null) {
				if (tempNoTiceShipMessage.getRequestState() == TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI) {
					tempDocument = TempDocumentLocalServiceUtil
							.findTemDocumentByDocumentNameDocumentYear(
									documentName, documentYear);
					// Gui thong tin di.
					xmlData = null;
					trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.KHAI_HUY;
				}
			}
			break;
		}

		if (trangThaiBanKhai > 0 || tempNoTiceShipMessage != null) {
			tempNoTiceShipMessage.setRequestState(trangThaiBanKhai);
			try {
				TempNoTiceShipMessageLocalServiceUtil
						.updateTempNoTiceShipMessage(tempNoTiceShipMessage);

			} catch (Exception e) {
				log.error(e.getMessage());
				log.equals(e);
			}

		}

		// SONVH commented 21.04.2016
		// if (xmlData != null && xmlData.trim().length() > 0) {
		// if
		// (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
		// ||
		// tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG))
		// {
		// guiBanTinSangHQMC_TauVaoRa(xmlData, businessUtils);
		// } else {
		// guiBanTinSangHQMC(xmlData, businessUtils);
		// }
		// }

		return result;
	}

	private boolean banKhaiChung(long documentName, int messageType,
			int documentYear, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest) {
		boolean result = false;
		TempGeneralDeclaration tempGeneralDeclaration = null;
		String _requestCode = requestCode;
		TempDocument tempDocument = null;
		InterfaceRequest interfaceRequest = null;
		Header header = null;
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = "";
		int trangThaiBanKhai = 0;
		tempDocument = TempDocumentLocalServiceUtil
				.findTemDocumentByDocumentNameDocumentYear(documentName,
						documentYear);
		log.info("====call banKhaiThongBaoTauRoiCang=====_requestCode=="
				+ _requestCode);
		switch (actionType) {
		case PageType.ACTION_TYPE_TIEP_NHAN:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_CHUNG);
			tempGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil
					.findTempGeneralDeclarationByRequestCode(_requestCode);
			log.info("====call tempGeneralDeclaration=====_requestCode=="
					+ tempGeneralDeclaration);

			if (tempGeneralDeclaration != null) {

				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);

				if (interfaceRequest != null) {
					try {
						interfaceRequest.setRemarks(BusinessUtils
								.getRemarkChapNhan(userName));
						InterfaceRequestLocalServiceUtil
								.updateInterfaceRequest(interfaceRequest);
					} catch (Exception e) {

					}
				}

				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_VAO_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				} else {
					header = BusinessUtils.createHeader(
									tempDocument,
									MessageType.XUAT_CANH,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_CHAP_NHAN),
									messageType, userName, interfaceRequest);
				}

				// Gui thong tin di.
				xmlData = businessUtils.sendMessageAccept(header,
						getOrganization(businessUtils, resourceRequest),
						getDevision(businessUtils, resourceRequest), userName,
						new Date(), tempDocument.getRequestCode());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI;
			}
			break;
		case PageType.ACTION_TYPE_TU_CHOI:
			// log.info("VAO TU CHOIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_CHUNG);
			tempGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil
					.findTempGeneralDeclarationByRequestCode(_requestCode);

			if (tempGeneralDeclaration != null) {
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
				interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(_requestCode);
				if (interfaceRequest != null) {
					try {
						interfaceRequest.setRemarks(BusinessUtils
								.getRemarkTuChoi(userName, ParamUtil
										.getString(resourceRequest,
												PageType.LY_DO_TU_CHOI)));

						InterfaceRequestLocalServiceUtil
								.updateInterfaceRequest(interfaceRequest);
					} catch (Exception e) {

					}
				}

				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									MessageType.HO_SO, userName,
									interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_RA_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									MessageType.HO_SO, userName,
									interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				} else if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
					header = BusinessUtils.createHeaderInland(
									tempDocument,
									MessageType.TAU_VAO_PTTND,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									MessageType.HO_SO, userName,
									interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				} else {
					header = BusinessUtils.createHeader(
									tempDocument,
									MessageType.XUAT_CANH,
									FormatData
											.formatIntToString(PageType.DOCUMENT_TYPE_TU_CHOI),
									messageType, userName, interfaceRequest);

					xmlData = businessUtils
							.sendMessageReject(
									header,
									"",
									ParamUtil.getString(resourceRequest,
											PageType.LY_DO_TU_CHOI),
									getOrganization(businessUtils,
											resourceRequest),
									getDevision(businessUtils, resourceRequest),
									userName, new Date(), tempDocument
											.getRequestCode());
				}

				// Gui thong tin di.
				// xmlData = businessUtils.sendMessageAccept(header,
				// getOrganization(), getDevision(), userName, new Date());
				// xmlData = businessUtils.sendMessageReject(header, "",
				// ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI),
				// getOrganization(businessUtils, resourceRequest),
				// getDevision(businessUtils, resourceRequest), userName, new
				// Date());
				trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.TU_CHOI_BAN_KHAI;
				BusinessUtils.updateResultDeclaration(MessageType.BAN_KHAI_CHUNG,
						documentName, documentYear,
						TrangThaiBanKhaiXuatCanh.TU_CHOI_BAN_KHAI);
			}
			break;
		case PageType.ACTION_TYPE_DOI_CHIEU:
			_requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
					(int) documentName, documentYear,
					MessageType.BAN_KHAI_CHUNG);
			tempGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil
					.findTempGeneralDeclarationByRequestCode(_requestCode);
			// log.info("PageType.ACTION_TYPE_DOI_CHIEU  " +
			// PageType.ACTION_TYPE_DOI_CHIEU);
			if (tempGeneralDeclaration != null) {
				if (tempGeneralDeclaration.getRequestState() != TrangThaiBanKhaiXuatCanh.DOI_CHIEU) {

					// Gui thong tin di.
					xmlData = null;
					trangThaiBanKhai = TrangThaiBanKhaiXuatCanh.DOI_CHIEU;
				}
			}
			log.info("===Docname == " + tempDocument.getDocumentName()
					+ "==docYear==" + tempDocument.getDocumentYear()
					+ "==RequestCode==" + requestCode);
			if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG)
					|| tempDocument.getDocumentTypeCode().equals(
							MessageType.LOAT_THU_TUC_ROI_CANG)
					|| tempDocument.getDocumentTypeCode().equals(
							MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)
					|| tempDocument.getDocumentTypeCode().equals(
							MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
				ResultDeclaration resultDeclaration = ResultDeclarationLocalServiceUtil
						.findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
								MessageType.BAN_KHAI_CHUNG, documentName,
								documentYear, _requestCode);

				if (resultDeclaration != null) {
					resultDeclaration.setRemarks(BusinessUtils
							.getRemarkChapNhan(userName));
					try {
						ResultDeclarationLocalServiceUtil
								.updateResultDeclaration(resultDeclaration);
					} catch (SystemException e) {
						log.error(e.getMessage());
						log.error(e.getMessage());
					}
				} else {
					log.info("resultDeclaration is null");
				}
			}

			BusinessUtils.updateResultDeclaration(MessageType.BAN_KHAI_CHUNG,
					documentName, documentYear,
					TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			break;
		}

		if (trangThaiBanKhai > 0 || tempGeneralDeclaration != null) {
			tempGeneralDeclaration.setRequestState(trangThaiBanKhai);
			try {
				TempGeneralDeclarationLocalServiceUtil
						.updateTempGeneralDeclaration(tempGeneralDeclaration);
			} catch (Exception e) {
				log.error(e.getMessage());
				log.equals(e);
			}
		}

		// SONVH commented 21.04.2016
		// if (xmlData != null && xmlData.trim().length() > 0) {
		// if
		// (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
		// ||
		// tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG))
		// {
		// guiBanTinSangHQMC_TauVaoRa(xmlData, businessUtils);
		// } else {
		// guiBanTinSangHQMC(xmlData, businessUtils);
		// }
		// }

		return result;
	}

	// private int checkFunction(InterfaceRequest interfaceRequest,
	// TempNoticeShipMessage tempNoTiceShipMessage) {
	// if (interfaceRequest == null) {
	// return TrangThaiBanKhaiXuatCanh.KHAI_MOI;
	// } else {
	// if (interfaceRequest.getRequestVersion() == null) {
	// return TrangThaiBanKhaiXuatCanh.KHAI_MOI;
	// } else {
	// if (tempNoTiceShipMessage.getRequestState() ==
	// TrangThaiBanKhaiXuatCanh.YEU_CAU_DUOC_GHI_VAO_HE_THONG) {
	// return TrangThaiBanKhaiXuatCanh.KHAI_MOI;
	// } else if (tempNoTiceShipMessage.getRequestState() ==
	// TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI) {
	// return TrangThaiBanKhaiXuatCanh.KHAI_SUA;
	// } else if (tempNoTiceShipMessage.getRequestState() ==
	// TrangThaiBanKhaiXuatCanh.TU_CHOI_BAN_KHAI) {
	// return TrangThaiBanKhaiXuatCanh.SUA_DOI_BO_XUNG;
	// }
	// }
	// }
	//
	// return TrangThaiBanKhaiXuatCanh.KHAI_MOI;
	// }

	private String getOrganization(BusinessUtils businessUtils,
			ActionRequest resourceRequest) {
		return BusinessUtils.getOrganizationFromUser(resourceRequest);
	}

	private String getDevision(BusinessUtils businessUtils,
			ActionRequest resourceRequest) {

		return businessUtils.getDivision(resourceRequest);
	}

	private boolean thuTucActionGiayPhepRoiCangChoTauDen(String userLogin,
			long documentName, int messageType, int documentYear,
			int actionType, String userName, String requestCodePortClearance,
			ActionRequest request, ActionResponse response)
			throws SystemException {

		boolean result = false;
		TempDocument tempDocument = null;
		InterfaceRequest interfaceRequest = null;
		Header header = null;
		BusinessUtils businessUtils = new BusinessUtils();
		String lCode = ParamUtil.getString(request, "lCode");
		// MessageFactory messageFactory = new MessageFactory();
		String xmlData = "";

		log.info("==thuTucActionGiayPhepRoiCangChoTauDen=messageType==="
				+ messageType + "====documentName===" + documentName
				+ "====requestCodePortClearance====" + requestCodePortClearance);

		String requestCodeInlandPortClearanceOrIssuePortClearanceDuyet = "";
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		log.info("actionType*********************" + actionType);
		switch (actionType) {
		case PageType.ACTION_TYPE_TIEP_NHAN:

			log.info("===START====ACTION_TYPE_TIEP_NHAN====documentName==="
					+ documentName + "===messageType===" + messageType
					+ "===requestCodePortClearance==="
					+ requestCodePortClearance);

			taoGiayPhepRoiCang(documentName, messageType, documentYear,
					actionType, userName, requestCodePortClearance, request,
					response);
			
			String capPortClearance = ParamUtil.getString(request,	PageType.LAN_CAP_PORT_CLEARANCE);
			String lyDoCapLai = ParamUtil.getString(request,
					"lyDoCapLaiPortClearance");
			log.info("==thuTucActionGiayPhepRoiCangChoTauDen==lyDoCapLai=="
					+ lyDoCapLai);

			InterfaceRequest interfacePortClearance = InterfaceRequestLocalServiceUtil
					.findByRequestCode(requestCodePortClearance);
			if (interfacePortClearance != null && capPortClearance.equalsIgnoreCase(KeyParams.N_LAN)) {
				String issueDate = ParamUtil.getString(request, "issueDate");
				String remarkCapLai = BusinessUtils.getRemarkCapLai(userName,
						lyDoCapLai,
						FormatData.parseDateShort3StringToDate(issueDate));
				interfacePortClearance.setRemarks(remarkCapLai);
				InterfaceRequestLocalServiceUtil
						.updateInterfaceRequest(interfacePortClearance);
			}

			break;

		case PageType.ACTION_TYPE_SUA:

			log.info("===START====ACTION_TYPE_SUA====documentName==="
					+ documentName + "===messageType===" + messageType
					+ "===requestCodePortClearance==="
					+ requestCodePortClearance);

			taoGiayPhepRoiCang(documentName, messageType, documentYear,
					actionType, userName, requestCodePortClearance, request,
					response);
			break;

		case PageType.ACTION_TYPE_DUYET:

			// _requestCode = CheckBusinessUtil.getRequestCode(requestCode,
			// (int) documentName, documentYear,
			// MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH);
			PortClearance2Xml portClearance2Xml = new PortClearance2Xml();
			tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);

			/**
			 * dm_gt_status '17', '4', 'Chấp nhận bản khai', '3', 'Trạng
			 * thái của bản khai', '0', '0', '2014-01-01', '2014-01-01',
			 * '1'
			 */

			// --------------------------------------InlandPortClearance--------------------------------------
			log.info("--ACTION_TYPE_DUYET--DocumentTypeCode----"
					+ tempDocument.getDocumentTypeCode());
			if (tempDocument.getDocumentTypeCode().equals(
					"" + MessageType.TAU_RA)) {

				InlandPortClearance portClearance = portClearance2Xml
						.convertXMLPortClearanceInland(requestCodePortClearance);
				// InlandPortClearance inlandPortClearance =

				if (portClearance != null) {
					//
					if (portClearance.getPortofAuthority() != null
							&& portClearance.getPortofAuthority().length() > 0) {
						DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
								.getByMaritimeCode(portClearance
										.getPortofAuthority());
						if (dmMaritime != null) {
							portClearance.setPortofAuthority(dmMaritime
									.getMaritimeReference());
						}
					}

					if (tempDocument.getDocumentStatusCode() != TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC) {

						xacNhanHoanThanhThuTucXuatCanh(request, documentName,
								documentYear);

						tempDocument
								.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC);

						if (tempDocument != null) {
							TempDocumentLocalServiceUtil
									.updateTempDocument(tempDocument);
						}

					}

					IssuePortClearance portClearanceDuyet = IssuePortClearanceLocalServiceUtil
							.getByRequestCode(requestCodePortClearance);

					if (Validator.isNotNull(portClearanceDuyet
							.getRequestState())) {

						// TODO Xuat canh truong hop duyet
						portClearanceDuyet
								.setIsApproval(PageType.DUYET_PHE_CHUAN);
						portClearanceDuyet.setApprovalDate(new Date());
						portClearanceDuyet.setApprovalName(userName);

						portClearanceDuyet
								.setRequestState(TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI);
						IssuePortClearanceLocalServiceUtil
								.updateIssuePortClearance(portClearanceDuyet);

						// TODO gui giay phep roi cang
						requestCodeInlandPortClearanceOrIssuePortClearanceDuyet = portClearanceDuyet
								.getRequestCode();
					}

					interfaceRequest = InterfaceRequestLocalServiceUtil
							.findInterfaceRequestByRequestCode(tempDocument
									.getRequestCode());
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA, MessageType.FUNCTION_KHAI_BAO,
							messageType, userName, interfaceRequest);
					header.getReference().setVersion(
							portClearanceDuyet.getVersionNo());

					log.info("====ACTION_TYPE_DUYET_ GIAY PHEP ROI CANG====REQUEST_CODE===="
							+ requestCodePortClearance
							+ "====getNextPortOfCallCode==="
							+ portClearance.getNextPortOfCallCode());

					// TODO Giay phep roi cang Gui thong tin di.
					xmlData = businessUtils
							.createContentSendFromBGTVTToNSWInland(header,
									MessageFactory
											.convertObjectToXml(portClearance));
				}

				// --------------------------------------IssuePortClearance--------------------------------------
			} else if (tempDocument.getDocumentTypeCode().equals(
					"" + MessageType.TAU_RA_PTTND)) {

				PTTNDPortclearance portClearance = portClearance2Xml.convertXMLPTTNDPortclearance(requestCodePortClearance);
				

				if (portClearance != null) {
					//
					if (portClearance.getPortofAuthority() != null
							&& portClearance.getPortofAuthority().length() > 0) {
						DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
								.getByMaritimeCode(portClearance
										.getPortofAuthority());
						if (dmMaritime != null) {
							portClearance.setPortofAuthority(dmMaritime
									.getMaritimeReference());
						}
					}

					if (tempDocument.getDocumentStatusCode() != TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC) {

						xacNhanHoanThanhThuTucXuatCanh(request, documentName,
								documentYear);

						tempDocument
								.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC);

						if (tempDocument != null) {
							TempDocumentLocalServiceUtil
									.updateTempDocument(tempDocument);
						}

					}

					IssuePortClearance portClearanceDuyet = IssuePortClearanceLocalServiceUtil
							.getByRequestCode(requestCodePortClearance);

					if (Validator.isNotNull(portClearanceDuyet
							.getRequestState())) {

						// TODO Xuat canh truong hop duyet
						portClearanceDuyet
								.setIsApproval(PageType.DUYET_PHE_CHUAN);
						portClearanceDuyet.setApprovalDate(new Date());
						portClearanceDuyet.setApprovalName(userName);

						portClearanceDuyet
								.setRequestState(TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI);
						IssuePortClearanceLocalServiceUtil
								.updateIssuePortClearance(portClearanceDuyet);

						// TODO gui giay phep roi cang
						requestCodeInlandPortClearanceOrIssuePortClearanceDuyet = portClearanceDuyet
								.getRequestCode();
					}

					interfaceRequest = InterfaceRequestLocalServiceUtil
							.findInterfaceRequestByRequestCode(tempDocument
									.getRequestCode());
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_RA_PTTND,
								MessageType.FUNCTION_KHAI_BAO,
								messageType, userName,
								interfaceRequest);					
					}else
					{
						header = BusinessUtils.createHeaderInland(tempDocument,
								MessageType.TAU_RA_PTTND,
								MessageType.FUNCTION_KHAI_BAO, messageType,
								userName, interfaceRequest);
					}
					header.getReference().setVersion(
							portClearanceDuyet.getVersionNo());

					log.info("====ACTION_TYPE_DUYET_ GIAY PHEP ROI CANG====REQUEST_CODE===="
							+ requestCodePortClearance
							+ "====getNextPortOfCallCode==="
							+ portClearance.getNextPortOfCallCode());

					// TODO Giay phep roi cang Gui thong tin di.
					xmlData = businessUtils
							.createContentSendFromBGTVTToNSWInland(header,
									MessageFactory
											.convertObjectToXml(portClearance));
				}

				// --------------------------------------IssuePortClearance--------------------------------------
			} else {

				PortClearance portClearance = portClearance2Xml
						.convertXMLPortClearance(requestCodePortClearance);
				

				if (portClearance != null) {
					if (portClearance.getPortofAuthority() != null
							&& portClearance.getPortofAuthority().length() > 0) {
						DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
								.getByMaritimeCode(portClearance
										.getPortofAuthority());
						if (dmMaritime != null) {
							portClearance.setPortofAuthority(dmMaritime
									.getMaritimeReference());
						}
					}

					// tempDocument =
					// TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName,
					// documentYear);

					if (tempDocument.getDocumentStatusCode() != TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC) {

						xacNhanHoanThanhThuTucXuatCanh(request, documentName,
								documentYear);

						tempDocument
								.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC);

						if (tempDocument != null) {
							TempDocumentLocalServiceUtil
									.updateTempDocument(tempDocument);
						}

					}

					IssuePortClearance portClearanceDuyet = IssuePortClearanceLocalServiceUtil
							.getByRequestCode(requestCodePortClearance);

					if (Validator.isNotNull(portClearanceDuyet
							.getRequestState())) {

						// TODO Xuat canh truong hop duyet
						portClearanceDuyet
								.setIsApproval(PageType.DUYET_PHE_CHUAN);
						portClearanceDuyet.setApprovalDate(new Date());
						portClearanceDuyet.setApprovalName(userName);

						portClearanceDuyet
								.setRequestState(TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI);
						IssuePortClearanceLocalServiceUtil
								.updateIssuePortClearance(portClearanceDuyet);

						requestCodeInlandPortClearanceOrIssuePortClearanceDuyet = portClearanceDuyet
								.getRequestCode();
					}

					interfaceRequest = InterfaceRequestLocalServiceUtil
							.findInterfaceRequestByRequestCode(tempDocument
									.getRequestCode());
					header = BusinessUtils.createHeader(tempDocument,
							MessageType.XUAT_CANH,
							MessageType.FUNCTION_KHAI_BAO, messageType,
							userName, interfaceRequest);
					header.getReference().setVersion(
							portClearanceDuyet.getVersionNo());

					// log.info("====ACTION_TYPE_DUYET_ GIAY PHEP ROI CANG====REQUEST_CODE===="
					// + requestCodePortClearance
					// + "====getNextPortOfCallCode===" +
					// portClearance.getNextPortOfCallCode());

					// TODO Giay phep roi cang Gui thong tin di.
					xmlData = businessUtils.createContentSendFromBGTVTToNSW(
							header,
							MessageFactory.convertObjectToXml(portClearance));
				}
			}

			// TODO GUI GIAY phep roi cang
			if (requestCodeInlandPortClearanceOrIssuePortClearanceDuyet
					.length() > 0) {
				// Insert history.
				BusinessUtils.insertHistory(header, xmlData,
						BusinessUtils.BoGiaoThongToHqmc,
						TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID()
								.toString());
				SendMessgaeFunctions
						.insertMessageQueue(
								header,
								xmlData,
								"OUT",
								requestCodeInlandPortClearanceOrIssuePortClearanceDuyet,
								MessgaePriorityLevels.HIGHT);
			}

			
			break;
		case PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET:

			log.info("==Chuyen_lanh_dao_duyet_ky_giay_phep_____requestCodePortClearance="+requestCodePortClearance);
			IssuePortClearance portClearanceChoLanhDaoDuyet = IssuePortClearanceLocalServiceUtil.getByRequestCode(requestCodePortClearance);
			log.info("==Chuyen_lanh_dao_duyet_ky_giay_phep_____requestCodePortClearance="+portClearanceChoLanhDaoDuyet);
			
			if ((portClearanceChoLanhDaoDuyet != null) && Validator.isNotNull(portClearanceChoLanhDaoDuyet.getRequestState())
					&& (portClearanceChoLanhDaoDuyet.getStampStatus() == 0)) {
				
				log.info("==Chuyen_lanh_dao_duyet_ky_giay_phep_____requestCodePortClearance______1=" + portClearanceChoLanhDaoDuyet);
				// TODO Vao truong hop cho lanh dao duyet
				portClearanceChoLanhDaoDuyet.setStampStatus(PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET);
				portClearanceChoLanhDaoDuyet.setIsApproval(PageType.KHONG_PHE_CHUAN);
				
				IssuePortClearanceLocalServiceUtil
						.updateIssuePortClearance(portClearanceChoLanhDaoDuyet);
				log.info("==Chuyen_lanh_dao_duyet_ky_giay_phep=");
				
			}
			break;
		case PageType.ACTION_TYPE_CHUYEN_TRA_HO_SO:
			log.info("=====Tra ve buoc truoc==ACTION_TYPE_CHUYEN_TRA_HO_SO");
			
			IssuePortClearance portClearanceLanhDaoTraLai = IssuePortClearanceLocalServiceUtil.getByRequestCode(requestCodePortClearance);

			if ((portClearanceLanhDaoTraLai != null) && Validator.isNotNull(portClearanceLanhDaoTraLai.getRequestState())) {
		
				String lyDoTraLai = ParamUtil.getString(request, PageType.LY_DO_TRA_LAI_HO_SO);		
				portClearanceLanhDaoTraLai.setStampStatus(PageType.KHONG_PHE_CHUAN);
				portClearanceLanhDaoTraLai.setIsApproval(PageType.KHONG_PHE_CHUAN);
				portClearanceLanhDaoTraLai.setIsCancel(PageType.KHONG_PHE_CHUAN);
				portClearanceLanhDaoTraLai.setCancelNote(lyDoTraLai);
				
				IssuePortClearanceLocalServiceUtil
						.updateIssuePortClearance(portClearanceLanhDaoTraLai);
				
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(documentName,
								documentYear);
						
				if (tempDocument.getDocumentTypeCode().equals(
						"" + MessageType.TAU_RA)) {
					log.info("==Tra ve buoc truoc, sua noi dung giay phep roi cang ==");	
					BusinessUtils.updateLyDoResultNotification(userLogin, lyDoTraLai, MessageType.Y_CAU_TRA_LAI_HO_SO_GIAY_PHEP_ROI_CANG_CHO_TAU_DEN,
							tempDocument.getMaritimeCode(), documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equals(
									"" + MessageType.TAU_RA_PTTND)) {
					log.info("==Tra ve buoc truoc, sua noi dung giay phep roi cang PTTND==");	
					BusinessUtils.updateLyDoResultNotification(userLogin, lyDoTraLai, MessageType.Y_CAU_TRA_LAI_HO_SO_GIAY_PHEP_ROI_CANG_CHO_TAU_DEN,
							tempDocument.getMaritimeCode(), documentName, documentYear);
				} else {
					log.info("==Tra ve buoc truoc, sua noi dung giay phep xuat canh==");	
					BusinessUtils.updateLyDoResultNotification(userLogin, lyDoTraLai, MessageType.Y_CAU_TRA_LAI_HO_SO_GP_XUAT_CANH,
							tempDocument.getMaritimeCode(), documentName, documentYear);
				}
				
				
			}
			break;
		case PageType.ACTION_TYPE_KYSODUYET:

			log.info("=====xuLyGiayPhepxuat canh==ACTION============PageType.ACTION_TYPE_KYSODUYET");
			tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);
			IssuePortClearance portClearanceDuyet = IssuePortClearanceLocalServiceUtil
					.getByRequestCode(requestCodePortClearance);
			long addkyso = ParamUtil.getLong(request, "fileId");

			if (Validator.isNotNull(portClearanceDuyet)) {
				log.info("=====xuLyGiayPhepxuat canh==ACTION_TYPE_KYSODUYET ");
				portClearanceDuyet
						.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_MOI);
				
				portClearanceDuyet.setApprovalDate(new Date());
				portClearanceDuyet.setApprovalName(userName);

				portClearanceDuyet.setStampStatus(1);
				String representative = portClearanceDuyet.getRepresentative();
				String portofAuthority = portClearanceDuyet.getPortofAuthority();				
				String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
				portClearanceDuyet.setSignTitle(signTitle);
				portClearanceDuyet.setSignName(ParamUtil.getString(request,
						"signName"));
				portClearanceDuyet.setSignPlace(ParamUtil.getString(request,
						"signPlace"));
				portClearanceDuyet.setSignDate(ParamUtil.getDate(request,
						"signDate", FormatData.formatDateShort3));
				
				if (addkyso > 0) {
					portClearanceDuyet.setAttachedFile(ParamUtil.getLong(
							request, "fileId"));
				}

				IssuePortClearanceLocalServiceUtil
						.updateIssuePortClearance(portClearanceDuyet);
				tempDocument
						.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP);

				if (tempDocument != null) {
					TempDocumentLocalServiceUtil
							.updateTempDocument(tempDocument);
				}

				log.info("=====xuLyGiayPhepxuat canh==tempDocument "
						+ tempDocument);
				
				// Kich ban du phong, Lanh dao ky so, khong co buoc Van thu dong dau.
				if (ConfigurationManager.getStrProp("vn.gt.yeu.cau.bo.qua.buoc.dong.dau", "").contains("1")) {
					thuTucActionGiayPhepRoiCangChoTauDen(userLogin,	documentName, messageType, documentYear,
						PageType.ACTION_TYPE_DONGDAUDUYET, userName, requestCodePortClearance, request, response);
				}
			}
			break;

		case PageType.ACTION_TYPE_DONGDAUDUYET:

			PortClearance2Xml portClearance2XmlDD = new PortClearance2Xml();
			if (lCode.equalsIgnoreCase(MessageType.TAU_RA_PTTND + "")) {
				
				IssuePortClearance portClearanceDuyetDD = IssuePortClearanceLocalServiceUtil
						.getByRequestCode(requestCodePortClearance);
				long adddongdau = ParamUtil.getLong(request, "fileId");
				if (Validator.isNotNull(portClearanceDuyetDD)) {
					log.info("=====xuLyGiayPhepRoiCang61==TAU_RA_PTTND==ACTION_TYPE_DONGDAUDUYET");
					tempDocument = TempDocumentLocalServiceUtil
							.findTemDocumentByDocumentNameDocumentYear(
									documentName, documentYear);			

						portClearanceDuyetDD
								.setRequestState(TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI);
						portClearanceDuyetDD.setIsApproval(PageType.DUYET_PHE_CHUAN);
						/*portClearanceDuyetDD.setApprovalDate(new Date());
						portClearanceDuyetDD.setApprovalName(userName);*/

						portClearanceDuyetDD.setStampStatus(2);

						if (adddongdau > 0) {
							portClearanceDuyetDD.setAttachedFile(ParamUtil
									.getLong(request, "fileId"));
						}

						IssuePortClearanceLocalServiceUtil
								.updateIssuePortClearance(portClearanceDuyetDD);
						requestCodeInlandPortClearanceOrIssuePortClearanceDuyet = portClearanceDuyetDD.getRequestCode();
						
						PTTNDPortclearance xmlPortClearanceDD = portClearance2XmlDD
								.convertXMLPTTNDPortclearance(requestCodePortClearance);

						if (xmlPortClearanceDD != null) {
							if (xmlPortClearanceDD.getPortofAuthority() != null
									&& xmlPortClearanceDD.getPortofAuthority().length() > 0) {
								log.info("==== chinh **************portClearance != null  7777*********** DUYET");
								DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
										.getByMaritimeCode(xmlPortClearanceDD
												.getPortofAuthority());
								if (dmMaritime != null) {
									log.info("==== chinh **************dmMaritime != null*********** DUYET");
									xmlPortClearanceDD.setPortofAuthority(dmMaritime
											.getMaritimeReference());
								}
							}

						tempDocument
								.setDocumentStatusCode(TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC);

						if (tempDocument != null) {
							TempDocumentLocalServiceUtil
									.updateTempDocument(tempDocument);
						}


						log.info("==guiBanTinSangHQMC==xmlReceive chinh 25/12/2015==");

					}
					if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
					{
						header = BusinessUtils.createHeaderInlandSMS(tempDocument,
								MessageType.TAU_RA_PTTND,
								MessageType.FUNCTION_KHAI_BAO,
								messageType, userName,
								interfaceRequest);					
					}else
					{
						header = BusinessUtils.createHeaderInland(tempDocument,
								MessageType.TAU_RA_PTTND,
								MessageType.FUNCTION_KHAI_BAO, messageType,
								userName, interfaceRequest);
					}
					header.getReference().setVersion(
							portClearanceDuyetDD.getVersionNo());
					xmlData = businessUtils
							.createContentSendFromBGTVTToNSWInland(
									header,
									MessageFactory
											.convertObjectToXml(xmlPortClearanceDD));
				}
			} else if (lCode.equalsIgnoreCase(MessageType.TAU_RA + "")) {
				IssuePortClearance portClearanceDuyetDD = IssuePortClearanceLocalServiceUtil
						.getByRequestCode(requestCodePortClearance);
				long adddongdau = ParamUtil.getLong(request, "fileId");
				if (Validator.isNotNull(portClearanceDuyetDD)) {
					log.info("=====xuLyGiayPhepRoiCang60==TAU_RA==ACTION_TYPE_DONGDAUDUYET");
					tempDocument = TempDocumentLocalServiceUtil
							.findTemDocumentByDocumentNameDocumentYear(
									documentName, documentYear);

					portClearanceDuyetDD
							.setRequestState(TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI);
					portClearanceDuyetDD.setIsApproval(PageType.DUYET_PHE_CHUAN);
					/*portClearanceDuyetDD.setApprovalDate(new Date());
					portClearanceDuyetDD.setApprovalName(userName);*/

					portClearanceDuyetDD.setStampStatus(2);

					if (adddongdau > 0) {
						portClearanceDuyetDD.setAttachedFile(ParamUtil
								.getLong(request, "fileId"));
					}

					IssuePortClearanceLocalServiceUtil
							.updateIssuePortClearance(portClearanceDuyetDD);
					
					requestCodeInlandPortClearanceOrIssuePortClearanceDuyet = portClearanceDuyetDD.getRequestCode();
					InlandPortClearance xmlPortClearanceDD = portClearance2XmlDD
							.convertXMLPortClearanceInland(requestCodePortClearance);

					if (xmlPortClearanceDD != null) {
						if (xmlPortClearanceDD.getPortofAuthority() != null
								&& xmlPortClearanceDD.getPortofAuthority().length() > 0) {
							log.info("==== chinh **************portClearance != null  7777*********** DUYET");
							DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
									.getByMaritimeCode(xmlPortClearanceDD
											.getPortofAuthority());
							if (dmMaritime != null) {
								log.info("==== chinh **************dmMaritime != null*********** DUYET");
								xmlPortClearanceDD.setPortofAuthority(dmMaritime
										.getMaritimeReference());
							}
						}

						tempDocument
								.setDocumentStatusCode(TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC);

						if (tempDocument != null) {
							TempDocumentLocalServiceUtil
									.updateTempDocument(tempDocument);
						}

						log.info("==guiBanTinSangHQMC==xmlReceive chinh 25/12/2015==");

					}
					header = BusinessUtils.createHeaderInland(tempDocument,
							MessageType.TAU_RA, MessageType.FUNCTION_KHAI_BAO,
							messageType, userName, interfaceRequest);
					header.getReference().setVersion(
							portClearanceDuyetDD.getVersionNo());
					xmlData = businessUtils
							.createContentSendFromBGTVTToNSWInland(
									header,
									MessageFactory
											.convertObjectToXml(xmlPortClearanceDD));
				}
			}else {
				
				IssuePortClearance portClearanceDuyetDD = IssuePortClearanceLocalServiceUtil
						.getByRequestCode(requestCodePortClearance);
				long adddongdau = ParamUtil.getLong(request, "fileId");
				if (Validator.isNotNull(portClearanceDuyetDD)) {
					log.info("=====xuLyGiayPhepRoiCang60==ACTION_TYPE_DONGDAUDUYET");
					tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								documentName, documentYear);
					portClearanceDuyetDD
							.setRequestState(TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI);
					portClearanceDuyetDD.setIsApproval(PageType.DUYET_PHE_CHUAN);
					/*portClearanceDuyetDD.setApprovalDate(new Date());
					portClearanceDuyetDD.setApprovalName(userName);*/

					portClearanceDuyetDD.setStampStatus(2);

					if (adddongdau > 0) {
						portClearanceDuyetDD.setAttachedFile(ParamUtil
								.getLong(request, "fileId"));
					}

					IssuePortClearanceLocalServiceUtil
							.updateIssuePortClearance(portClearanceDuyetDD);
					requestCodeInlandPortClearanceOrIssuePortClearanceDuyet = portClearanceDuyetDD.getRequestCode();
						
					PortClearance xmlPortClearanceDD = portClearance2XmlDD
							.convertXMLPortClearance(requestCodePortClearance);

					if (xmlPortClearanceDD != null) {
						if (xmlPortClearanceDD.getPortofAuthority() != null
								&& xmlPortClearanceDD.getPortofAuthority().length() > 0) {
							log.info("==== chinh **************portClearance != null  7777*********** DUYET");
							DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
									.getByMaritimeCode(xmlPortClearanceDD
											.getPortofAuthority());
							if (dmMaritime != null) {
								log.info("==== chinh **************dmMaritime != null*********** DUYET");
								xmlPortClearanceDD.setPortofAuthority(dmMaritime
										.getMaritimeReference());
							}
						}
						
						
						tempDocument
								.setDocumentStatusCode(TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC);

						if (tempDocument != null) {
							TempDocumentLocalServiceUtil
									.updateTempDocument(tempDocument);							
						}

						log.info("==guiBanTinSangHQMC==xmlReceive chinh 25/12/2015==");

					}
								
					
					header = BusinessUtils.createHeader(tempDocument,
							MessageType.XUAT_CANH,
							MessageType.FUNCTION_KHAI_BAO, messageType,
							userName, interfaceRequest);

					xmlData = businessUtils.createContentSendFromBGTVTToNSW(
							header,
							MessageFactory.convertObjectToXml(xmlPortClearanceDD));
				}
			}
			
			// TODO GUI GIAY phep roi cang
			if (requestCodeInlandPortClearanceOrIssuePortClearanceDuyet
					.length() > 0) {
				// Insert history.
				BusinessUtils.insertHistory(header, xmlData,
						BusinessUtils.BoGiaoThongToHqmc,
						TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID()
								.toString());
				SendMessgaeFunctions
						.insertMessageQueue(
								header,
								xmlData,
								"OUT",
								requestCodeInlandPortClearanceOrIssuePortClearanceDuyet,
								MessgaePriorityLevels.HIGHT);
			}

			break;

		case PageType.ACTION_TYPE_HUY:

			// _requestCode = CheckBusinessUtil.getRequestCode(_requestCode,
			// (int) documentName, documentYear,
			// MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH);
			String lyDoHuyXC = ParamUtil.getString(request, PageType.HUY_HO_SO);

			if (lyDoHuyXC == null || lyDoHuyXC.length() == 0) {
				lyDoHuyXC = "lyDoHuyXC";
			}
			log.info("===========lyDoHuyXC========================="
					+ lyDoHuyXC);

			tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);
			tempDocument
					.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
			TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);

			List<IssuePortClearance> lstPortClearances = IssuePortClearanceLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.VERSION_NO,
							KeyParams.ORDER_BY_DESC);

			for (IssuePortClearance object : lstPortClearances) {
				object.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_HUY);
				IssuePortClearanceLocalServiceUtil
						.updateIssuePortClearance(object);
			}

			// dung.le handsome edit
			if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_ROI_CANG)) {
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_RA,
						MessageType.FUNCTION_KHAI_HUY_HO_SO,
						MessageType.HUY_GIAY_PHEP_ROI_CANG, userName,
						interfaceRequest);
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
				///SMS
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_RA_PTTND,
						MessageType.FUNCTION_KHAI_HUY_HO_SO,
						MessageType.HUY_GIAY_PHEP_ROI_CANG, userName,
						interfaceRequest);
				
				if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
				{
					header = BusinessUtils.createHeaderInlandSMS(tempDocument,
							MessageType.TAU_RA_PTTND,
							MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
							MessageType.TIN_NHAN_PTTND_VAOROI, userName,
							interfaceRequest);					
				}
				
			} else if (tempDocument.getDocumentTypeCode().equals(
					MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
				///SMS
				header = BusinessUtils.createHeaderInland(tempDocument,
						MessageType.TAU_VAO_PTTND,
						MessageType.FUNCTION_KHAI_HUY_HO_SO,
						MessageType.HUY_GIAY_PHEP_VAO_CANG, userName,
						interfaceRequest);
				
				if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
				{
					header = BusinessUtils.createHeaderInlandSMS(tempDocument,
							MessageType.TAU_VAO_PTTND,
							MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
							MessageType.TIN_NHAN_PTTND_VAOROI, userName,
							interfaceRequest);					
				}
				
			} else {
				header = BusinessUtils.createHeader(tempDocument,
						MessageType.XUAT_CANH,
						MessageType.FUNCTION_KHAI_HUY_HO_SO,
						MessageType.HUY_GIAY_PHEP_ROI_CANG, userName,
						interfaceRequest);
			}
			
			if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
			{
				xmlData = businessUtils.sendMessageHuyHoSoNew(header, BusinessUtils
						.getMaritimeShortNameFromUser(request), businessUtils
						.getDivision(request), header.getFrom().getName(),
						new Date(), lyDoHuyXC, tempDocument.getRequestCode());
			}
			else
			{
				xmlData = businessUtils.sendMessageHuyHoSoNew(header, BusinessUtils
						.getOrganizationFromUser(request), businessUtils
						.getDivision(request), header.getFrom().getName(),
						new Date(), lyDoHuyXC, tempDocument.getRequestCode());
			}

			

			// TODO update Result Notification
			BusinessUtils.updateLyDoResultNotification(userLogin, lyDoHuyXC,
					MessageType.HUY_GIAY_PHEP_ROI_CANG,
					tempDocument.getMaritimeCode(), documentName, documentYear);

			List<IssuePortClearance> lissuePortClearances = IssuePortClearanceLocalServiceUtil
					.findIssuePortClearanceByDocumentYearAndDocumentYear(
							documentName, documentYear);

			if (lissuePortClearances != null && lissuePortClearances.size() > 0) {

				for (IssuePortClearance issue : lissuePortClearances) {

					issue.setIsCancel(1);
					issue.setCancelName(userLogin);
					issue.setCancelNote(lyDoHuyXC);
					issue.setCancelDate(new Date());
					IssuePortClearanceLocalServiceUtil
							.updateIssuePortClearance(issue);
				}
			}
			break;
		}

		if (xmlData != null && xmlData.trim().length() > 0) {
			// guiBanTinSangHQMC(xmlData, businessUtils);
			try {
				String uid = "BGTVT"
						+ Long.toString(System.currentTimeMillis());
				businessUtils.insertHistorySendMessageThreeIssue(xmlData,
						userName, uid);
				log.info("==guiBanTinSangHQMC==xmlData==");
				log.info(xmlData);

//				IMTService imtService = CallWs2HaiQuan.getIMTSercice();
				String xmlReceive = "";

				
				log.info("==guiBanTinSangHQMC==xmlReceive==");
				// log.info(xmlReceive);

				businessUtils.insertHistoryReceiveMessageResponse(xmlReceive);
			} catch (Exception e) {
				log.error(e.getMessage());
				log.error(e.getMessage());
			}
		}

		return result;
	}

	private IssuePortClearance taoGiayPhepRoiCang(long documentName,
			int messageType, int documentYear, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest,
			ActionResponse httpReq) {

		try {

			// Giay phep roi cang so
			String numberPortClearance = ParamUtil.getString(resourceRequest,
					"numberPortClearance");

			// Cang vu hang hai
			String portofAuthority = ParamUtil.getString(resourceRequest,
					"maritimeCode");

			// Ten taushipName
			String nameOfShip = ParamUtil.getString(resourceRequest,
					"nameOfship");

			// Quoc tich tau
			String flagStateOfShip = ParamUtil.getString(resourceRequest,
					"stateCode");

			// So luong thuyen vien
			int numberOfCrews = ParamUtil.getInteger(resourceRequest,
					"numberOfCrews");

			// So luong hanh khach
			int numberOfPassengers = ParamUtil.getInteger(resourceRequest,
					"numberOfPassengers");

			// Ho hieu
			String callSign = ParamUtil.getString(resourceRequest, "callSign");

			// Ten thuyen truong
			String nameOfMaster = ParamUtil.getString(resourceRequest,
					"nameOfMaster");

			// Loai hang hoa
			String cargo = ParamUtil.getString(resourceRequest, "typeOfCargo");

			// So luong hang hoa
			double volumeCargo = ParamUtil.getDouble(resourceRequest,
					"volumeCargo", 0L);

			// Don vi tinh loai hang hoa
			String cargoUnit = ParamUtil.getString(resourceRequest,
					"unitVolumeCargo");

			// Loai hang hoa qua canh
			String transitCargo = ParamUtil.getString(resourceRequest,
					"transitOfCargo");

			// So luong hang hoa qua canh
			Double volumeTransitCargo = ParamUtil.getDouble(resourceRequest,
					"volumeTransitCargo", 0L);

			// Don vi tinh hang hoa qua canh
			String transitCargoUnit = ParamUtil.getString(resourceRequest,
					"unitVolumeTransitCargo");

			// Thoi gian roi cang
			Date timeOfDeparture = ParamUtil.getDate(resourceRequest,
					"timeOfDeparture", FormatData.formatDateShort3);

			// Su dung tenCangDenThayThe neu nextPortOfCallCode chua ZZZ
			String tenCangDenThayThe = ParamUtil.getString(resourceRequest,
					"remarks");
			
			// Ma cang den
			String nextPortOfCallCode = ParamUtil.getString(resourceRequest,
					"nextPortOfCallCode");

			// Ma cang roi
			String PortCode = ParamUtil.getString(resourceRequest, "cangroi");
			// Co hieu luc
			Date validUntil = ParamUtil.getDate(resourceRequest,
					"timeOfValidUntil", FormatData.formatDateShort3);

			// Ngay cap
			Date issueDate = ParamUtil.getDate(resourceRequest, "issueDate",
					FormatData.formatDateShort3);
			if (Validator.isNull(issueDate)) {
				issueDate = new Date();
			}

			// Giam doc cang vu hang hai
			String directorOfMaritime = "GD";

			// String directorOfMaritimeAdministration = "GD";
			long urs = 0;
			urs = UserLocalServiceUtil.getUserIdByEmailAddress(10154, userName);
			// log.info("=============userID ==== " + urs);
			if (urs != 0) {
				UserPort defaultCode = UserPortLocalServiceUtil
						.findByUserId(urs);
				log.info("urs!=0");
				if (defaultCode != null) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
							.getByMaritimeCode(defaultCode.getPortCode());
					log.info("dmMaritime.getCityCode()== "
							+ dmMaritime.getCityCode());
					if (dmMaritime != null) {
						directorOfMaritime = dmMaritime.getCityCode();
					}
				}
			}
			// Giay phep so
			String certificateNo = ParamUtil.getString(resourceRequest,
					"certificateNo");

			String unitCertificate = ParamUtil.getString(resourceRequest,
					"unitCertificateNo");
			log.info("==cargo==" + cargo + "==volumeCargo==" + volumeCargo);
			// Dung tich toan phan
			double gt = ParamUtil.getDouble(resourceRequest, "grt", 0L);
			String versionNo = ParamUtil
					.getString(resourceRequest, "versionNo");

			long idPortClearance = ParamUtil.getLong(resourceRequest,
					"idPortClearance");
			String requestCodePortClearance = ParamUtil.getString(
					resourceRequest, "requestCodePortClearance");
			String representative = ParamUtil.getString(resourceRequest,
					"representative");
			String remarks = ParamUtil.getString(resourceRequest,
					PageType.LY_DO_TU_CHOI);

			
			
			
			
			// Loai hang hoa
			String cargoDescription = "";

			IssuePortClearance portClearance = new IssuePortClearance();

			portClearance.setNumberPortClearance(numberPortClearance);
			portClearance.setDocumentName(documentName);
			portClearance.setDocumentYear(documentYear);
			portClearance.setPortofAuthority(portofAuthority);
			portClearance.setNameOfShip(nameOfShip);
			portClearance.setFlagStateOfShip(flagStateOfShip);
			portClearance.setNumberOfCrews(numberOfCrews);
			portClearance.setNumberOfPassengers(numberOfPassengers);
			portClearance.setCallSign(callSign);
			portClearance.setNameOfMaster(nameOfMaster);
			portClearance.setCargo(cargo);
			portClearance.setVolumeCargo(volumeCargo);
			portClearance.setCargoUnit(cargoUnit);
			portClearance.setCargoDescription(cargoDescription);
			portClearance.setTransitCargo(transitCargo);
			portClearance.setVolumeTransitCargo(volumeTransitCargo);
			portClearance.setTransitCargoUnit(transitCargoUnit);
			portClearance.setTimeOfDeparture(timeOfDeparture);
			portClearance.setNextPortOfCallCode(nextPortOfCallCode);
			portClearance.setPortCode(PortCode);
			portClearance.setRemarks(tenCangDenThayThe);
			portClearance.setValidUntil(validUntil);
			portClearance.setIssueDate(issueDate);
			portClearance.setDirectorOfMaritime(directorOfMaritime);
			portClearance.setCertificateNo(certificateNo.trim() + "/"
					+ unitCertificate.trim());
			portClearance.setGt(gt);
			portClearance.setVersionNo(versionNo);
			
			portClearance.setRepresentative(representative);
			String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
			portClearance.setSignTitle(signTitle);	
			
			TempDocument TempDoc = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);
			if (Validator.isNotNull(TempDoc)) {
				portClearance.setDwt(TempDoc.getDwt());
				if ((TempDoc.getPortCode().trim().length() == 0) && !(TempDoc.getDocumentTypeCode().equals("" + MessageType.TAU_RA_PTTND))) {
					TempDoc.setPortCode(PortCode);
					TempDocumentLocalServiceUtil.updateTempDocument(TempDoc);
				}

			}
			
			if (TempDoc.getDocumentTypeCode().equals("" + MessageType.TAU_RA_PTTND))
			{
				// Ma cang den
				nextPortOfCallCode = ParamUtil.getString(resourceRequest,
						"CANG_BEN_2");
				portClearance.setNextPortOfCallCode(nextPortOfCallCode);
				// Ma tinh thanh cang den
				String nextProvinceCode = ParamUtil.getString(resourceRequest,
						"TINH_THANH_PHO");
				portClearance.setNextProvinceCode(nextProvinceCode);
				// Ma cang bien hang hai
				String maritimePortCode = ParamUtil.getString(resourceRequest,
						"maritimePortCode");
				portClearance.setMaritimePortCode(maritimePortCode);
				// Ma ben cang roi
				String portHarbourCode = ParamUtil.getString(resourceRequest,
						"holdPortHarbourCode");
				portClearance.setPortHarbourCode(portHarbourCode);
				// Ma cang ben ke tiep
				String nextMaritimePortCode = ParamUtil.getString(resourceRequest,
						"CANG_BEN");
				portClearance.setNextMaritimePortCode(nextMaritimePortCode);
				// Ma cang ben ke tiep
				String nextPortRegionCode = ParamUtil.getString(resourceRequest,
						"KHU_VUC_CANG");
				portClearance.setNextPortRegionCode(nextPortRegionCode);
				// Ma cang ben ke tiep
				String nextPortHarbourCode = ParamUtil.getString(resourceRequest,
						"BEN_CANG");
				portClearance.setNextPortHarbourCode(nextPortHarbourCode);
				// Ma cang ben ke tiep
				String nextPortWharfCode = ParamUtil.getString(resourceRequest,
						"NextPortWharfCode");
				portClearance.setNextPortWharfCode(nextPortWharfCode);
				double dwt = ParamUtil.getDouble(resourceRequest, "dwt", 0L);
				portClearance.setDwt(dwt);
				
			
			}
			// TODO xuat canh truong hop duyet
			// portClearance.setIsApproval(isApproval);
			// portClearance.setApprovalDate(approvalDate);
			// portClearance.setApprovalName(approvalName);

			// TODO xuat canh truong hop cap lai
			// portClearance.setRemarks(remarks);

			// TODO xuat canh truong hop huy?
			// portClearance.setIsCancel(isCancel);
			// portClearance.setCancelDate(cancelDate);
			// portClearance.setCancelName(cancelName);
			// portClearance.setCancelNote(cancelNote);

			

			String capPortClearance = ParamUtil.getString(resourceRequest,
					PageType.LAN_CAP_PORT_CLEARANCE);
			String suaPortClearance = ParamUtil.getString(resourceRequest,
					PageType.LAN_SUA_PORT_CLEARANCE);

			// log.info("====taoGiayPhepRoiCang====capPortClearance=====" +
			// capPortClearance + "=====suaPortClearance=====" +
			// suaPortClearance);
			
			if (capPortClearance.equalsIgnoreCase(KeyParams.MOT_LAN)) {

				portClearance.setRequestCode(UUID.randomUUID().toString());
				portClearance
						.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_MOI);
				
				if (TempDoc.getDocumentTypeCode().equals("" + MessageType.TAU_RA_PTTND)
						|| TempDoc.getDocumentTypeCode().equals("" + MessageType.TAU_RA)
						|| TempDoc.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_XUAT_CANH)) {
					cargoDescription = "";
					
					int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
					
					for(int index : indexs) {				    
					    
					    	TempCargoItems cargoItem = null;
					    	cargoItem = new TempCargoItems();
					    	cargoItem.setDocumentName(documentName);
					    	cargoItem.setDocumentYear(documentYear);
					    	cargoItem.setRequestCode(portClearance.getRequestCode());
					    	cargoItem.setCargoCategory("VC");
					    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
					    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
					    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
					    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
					    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
					    	cargoItem.setSequence(index);
					    	
							try {
								if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
								{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
										+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
										+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
										+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
								}
								else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
								{
									cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
									TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
								}
							}catch (SystemException e) {
								log.error(e.getMessage());
							};					
					     
					    
					   }				
					if (cargoDescription.trim().length() > 0)
					{						
						portClearance.setCargo(StringPool.BLANK);						
						portClearance.setCargoUnit(StringPool.BLANK);
						portClearance.setCargoDescription(cargoDescription);
					}
					

				}

				portClearance = IssuePortClearanceLocalServiceUtil
						.addIssuePortClearance(portClearance);
				log.info("===========MOT_LAN====" + "INSERT");

			} else if (suaPortClearance.equalsIgnoreCase(KeyParams.MOT_LAN)) {

				portClearance.setId(idPortClearance);
				portClearance.setRequestCode(requestCodePortClearance);
				portClearance
						.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_SUA);
				
				if (TempDoc.getDocumentTypeCode().equals("" + MessageType.TAU_RA_PTTND)
						|| TempDoc.getDocumentTypeCode().equals("" + MessageType.TAU_RA)
						|| TempDoc.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_XUAT_CANH)) {
					cargoDescription = "";
					
					List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYearAndRequestCode(documentName, documentYear, requestCodePortClearance);
					if (items != null && items.size() > 0){
						for (TempCargoItems carg: items) {
							TempCargoItemsLocalServiceUtil.deleteTempCargoItems(carg);
						}
					}
					
					int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
					
					for(int index : indexs) {				    
					    
					    	TempCargoItems cargoItem = null;
					    	cargoItem = new TempCargoItems();
					    	cargoItem.setDocumentName(documentName);
					    	cargoItem.setDocumentYear(documentYear);
					    	cargoItem.setRequestCode(portClearance.getRequestCode());
					    	cargoItem.setCargoCategory("VC");
					    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
					    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
					    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
					    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
					    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
					    	cargoItem.setSequence(index);
					    	
							try {
								if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
								{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
										+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
										+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
										+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
								}
								else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
								{
									cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
									TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
								}
							} catch (SystemException e) {
								log.error(e.getMessage());
							};					
					     
					    
					   }				
					
					if (cargoDescription.trim().length() > 0)
					{						
						portClearance.setCargo(StringPool.BLANK);						
						portClearance.setCargoUnit(StringPool.BLANK);
						portClearance.setCargoDescription(cargoDescription);
					}
					

				}

				portClearance = IssuePortClearanceLocalServiceUtil
						.updateIssuePortClearance(portClearance);
				log.info("===========MOT_LAN====id===" + idPortClearance
						+ "===UPDATE");

			} else if (capPortClearance.equalsIgnoreCase(KeyParams.N_LAN)) {

				portClearance.setRequestCode(UUID.randomUUID().toString());
				portClearance
						.setRequestState(TrangThaiBanKhaiXuatCanh.SUA_DOI_BO_XUNG);
				portClearance.setRemarks(remarks);
				
				if (TempDoc.getDocumentTypeCode().equals("" + MessageType.TAU_RA_PTTND)
						|| TempDoc.getDocumentTypeCode().equals("" + MessageType.TAU_RA)
						|| TempDoc.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_XUAT_CANH)) {
					cargoDescription = "";
					
					int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
					
					for(int index : indexs) {				    
					    
					    	TempCargoItems cargoItem = null;
					    	cargoItem = new TempCargoItems();
					    	cargoItem.setDocumentName(documentName);
					    	cargoItem.setDocumentYear(documentYear);
					    	cargoItem.setRequestCode(portClearance.getRequestCode());
					    	cargoItem.setCargoCategory("VC");
					    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
					    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
					    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
					    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
					    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
					    	cargoItem.setSequence(index);
					    	
							try {
								if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
								{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
										+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
										+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
										+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
								}
								else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
								{
									cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
									TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
								}
							} catch (SystemException e) {
								log.error(e.getMessage());
							};					
					     
					    
					   }				
					
					if (cargoDescription.trim().length() > 0)
					{						
						portClearance.setCargo(StringPool.BLANK);						
						portClearance.setCargoUnit(StringPool.BLANK);
						portClearance.setCargoDescription(cargoDescription);
					}

				}
				
				
				portClearance = IssuePortClearanceLocalServiceUtil
						.addIssuePortClearance(portClearance);
				log.info("===========N_LAN=====" + "INSERT");

			} else if (suaPortClearance.equalsIgnoreCase(KeyParams.N_LAN)) {

				portClearance.setId(idPortClearance);
				portClearance.setRequestCode(requestCodePortClearance);
				portClearance
						.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_SUA);
				
				
				if (TempDoc.getDocumentTypeCode().equals("" + MessageType.TAU_RA_PTTND)
						|| TempDoc.getDocumentTypeCode().equals("" + MessageType.TAU_RA)
						|| TempDoc.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_XUAT_CANH)) {
					cargoDescription = "";
					
					List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYearAndRequestCode(documentName, documentYear, requestCodePortClearance);
					if (items != null && items.size() > 0){
						for (TempCargoItems carg: items) {
							TempCargoItemsLocalServiceUtil.deleteTempCargoItems(carg);
						}
					}
					
					int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
					
					for(int index : indexs) {				    
					    
					    	TempCargoItems cargoItem = null;
					    	cargoItem = new TempCargoItems();
					    	cargoItem.setDocumentName(documentName);
					    	cargoItem.setDocumentYear(documentYear);
					    	cargoItem.setRequestCode(portClearance.getRequestCode());
					    	cargoItem.setCargoCategory("VC");
					    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
					    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
					    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
					    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
					    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
					    	cargoItem.setSequence(index);
					    	
							try {
								if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
								{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
										+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
										+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
										+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
								}
								else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
								{
									cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
									TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
								}
							} catch (SystemException e) {
								log.error(e.getMessage());
							};					
					     
					    
					   }				
					
					if (cargoDescription.trim().length() > 0)
					{						
						portClearance.setCargo(StringPool.BLANK);						
						portClearance.setCargoUnit(StringPool.BLANK);
						portClearance.setCargoDescription(cargoDescription);
					}

				}

				portClearance = IssuePortClearanceLocalServiceUtil
						.updateIssuePortClearance(portClearance);
				log.info("===========N_LAN===id===" + idPortClearance
						+ "=UPDATE");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getMessage());
		}
		return null;
	}

	// PTTND6
	private IssueAcceptance taoGiayPhepVaoCang(long documentName,
			int messageType, int documentYear, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest,
			ActionResponse httpReq) {

		try {

			// Giay phep vao cang so
			String numberAcceptance = ParamUtil.getString(resourceRequest,
					"numberAcceptance");

			// Cang vu hang hai
			String portofAuthority = ParamUtil.getString(resourceRequest,
					"maritimeCode");

			// Ten taushipName
			String nameOfShip = ParamUtil.getString(resourceRequest,
					"nameOfship");

			// Quoc tich tau
			String flagStateOfShip = ParamUtil.getString(resourceRequest,
					"stateCode");

			// So luong thuyen vien
			int numberOfCrews = ParamUtil.getInteger(resourceRequest,
					"numberOfCrews");

			// So luong hanh khach
			int numberOfPassengers = ParamUtil.getInteger(resourceRequest,
					"numberOfPassengers");

			// Ho hieu
			String callSign = ParamUtil.getString(resourceRequest, "callSign");

			// Ten thuyen truong
			String nameOfMaster = ParamUtil.getString(resourceRequest,
					"nameOfMaster");

			// Loai hang hoa
			String cargo = ParamUtil.getString(resourceRequest, "typeOfCargo");

			// So luong hang hoa
			Double volumeCargo = ParamUtil.getDouble(resourceRequest,
					"volumeCargo", 0L);

			// Don vi tinh loai hang hoa
			String cargoUnit = ParamUtil.getString(resourceRequest,
					"unitVolumeCargo");

			// Loai hang hoa qua canh
			String transitCargo = ParamUtil.getString(resourceRequest,
					"transitOfCargo");

			// So luong hang hoa qua canh
			Double volumeTransitCargo = ParamUtil.getDouble(resourceRequest,
					"volumeTransitCargo", 0L);

			// Don vi tinh hang hoa qua canh
			String transitCargoUnit = ParamUtil.getString(resourceRequest,
					"unitVolumeTransitCargo");

			// Thoi gian v�o cang DateFrom
			Date timeOfDeparture = ParamUtil.getDate(resourceRequest,
					"timeOfDeparture", FormatData.formatDateShort3);

			// Thoi gian vao cang DataTo
			Date validUntil = ParamUtil.getDate(resourceRequest,
					"timeOfValidUntil", FormatData.formatDateShort3);

			
			// Ma cang den
			String nextPortOfCallCode = ParamUtil.getString(resourceRequest,
					"nextPortOfCallCode");
			String portCode = ParamUtil.getString(resourceRequest,"portCode");
			// Ma ben cang den
			String portHarbourCode = ParamUtil.getString(resourceRequest,"portHarbourCode");
			
			// Ma cau cang den
			String portWharfCode = ParamUtil.getString(resourceRequest,"portWharfCode");
			
			
			// Ngay cap
			Date issueDate = ParamUtil.getDate(resourceRequest, "issueDate",
					FormatData.formatDateShort3);
			if (Validator.isNull(issueDate)) {
				issueDate = new Date();
			}

			// Giam doc cang vu hang hai
			String directorOfMaritime = "GD";

			// String directorOfMaritimeAdministration = "GD";
			long urs = 0;
			urs = UserLocalServiceUtil.getUserIdByEmailAddress(10154, userName);
			// log.info("=============userID ==== " + urs);
			if (urs != 0) {
				UserPort defaultCode = UserPortLocalServiceUtil
						.findByUserId(urs);
				log.info("urs!=0");
				if (defaultCode != null) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
							.getByMaritimeCode(defaultCode.getPortCode());
					log.info("dmMaritime.getCityCode()== "
							+ dmMaritime.getCityCode());
					if (dmMaritime != null) {
						directorOfMaritime = dmMaritime.getCityCode();
					}
				}
			}
			// Giay phep so
			String certificateNo = ParamUtil.getString(resourceRequest,
					"certificateNo");

			String unitCertificate = ParamUtil.getString(resourceRequest,
					"unitCertificateNo");
			log.info("==cargo==" + cargo + "==volumeCargo==" + volumeCargo);
			// Dung tich toan phan
			double gt = ParamUtil.getDouble(resourceRequest, "grt", 0L);
			double dwt = ParamUtil.getDouble(resourceRequest, "dwt", 0L);
			String versionNo = ParamUtil
					.getString(resourceRequest, "versionNo");

			long idIssueAcceptance = ParamUtil.getLong(resourceRequest,
					"idAcceptance");
			String requestCodeAcceptance = ParamUtil.getString(
					resourceRequest, "requestCodeAcceptance");
			String representative = ParamUtil.getString(resourceRequest,
					"representative");
			String remarks = ParamUtil.getString(resourceRequest,
					PageType.LY_DO_TU_CHOI);
			
			// Loai hang hoa
			String cargoDescription = "";

			IssueAcceptance specificAcceptance = new IssueAcceptance();

			specificAcceptance.setNumberPortClearance(numberAcceptance);
			specificAcceptance.setDocumentName(documentName);
			specificAcceptance.setDocumentYear(documentYear);
			specificAcceptance.setPortofAuthority(portofAuthority);
			specificAcceptance.setNameOfShip(nameOfShip);
			specificAcceptance.setFlagStateOfShip(flagStateOfShip);
			specificAcceptance.setNumberOfCrews(numberOfCrews);
			specificAcceptance.setNumberOfPassengers(numberOfPassengers);
			specificAcceptance.setCallSign(callSign);
			specificAcceptance.setNameOfMaster(nameOfMaster);
			specificAcceptance.setCargo(cargo);
			specificAcceptance.setVolumeCargo(volumeCargo);
			specificAcceptance.setCargoUnit(cargoUnit);
			specificAcceptance.setCargoDescription(cargoDescription);
			specificAcceptance.setTransitCargo(transitCargo);
			specificAcceptance.setVolumeTransitCargo(volumeTransitCargo);
			specificAcceptance.setTransitCargoUnit(transitCargoUnit);
			specificAcceptance.setTimeOfDeparture(timeOfDeparture);
			specificAcceptance.setNextPortOfCallCode(nextPortOfCallCode);
			specificAcceptance.setPortHarbourCode(portHarbourCode);
			specificAcceptance.setPortWharfCode(portWharfCode);
			specificAcceptance.setPortCode(portCode);
			specificAcceptance.setValidUntil(validUntil);
			specificAcceptance.setIssueDate(issueDate);
			specificAcceptance.setDirectorOfMaritime(directorOfMaritime);
			specificAcceptance.setCertificateNo(certificateNo.trim() + "/"
					+ unitCertificate.trim());
			specificAcceptance.setGt(gt);
			specificAcceptance.setDwt(dwt);
			specificAcceptance.setVersionNo(versionNo);		
			
			String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
			specificAcceptance.setSignTitle(signTitle);	
			specificAcceptance.setRepresentative(representative);

			String capIssueAcceptance = ParamUtil.getString(resourceRequest,
					PageType.LAN_CAP_ISSUE_ACCEPTANCE);
			String suaIssueAcceptance = ParamUtil.getString(resourceRequest,
					PageType.LAN_SUA_ISSUE_ACCEPTANCE);

			
			if (capIssueAcceptance.equalsIgnoreCase(KeyParams.MOT_LAN)) {

				specificAcceptance.setRequestCode(UUID.randomUUID().toString());
				specificAcceptance
						.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_MOI);
				cargoDescription = "";
				
				int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
				
				for(int index : indexs) {				    
				    
				    	TempCargoItems cargoItem = null;
				    	cargoItem = new TempCargoItems();
				    	cargoItem.setDocumentName(documentName);
				    	cargoItem.setDocumentYear(documentYear);
				    	cargoItem.setRequestCode(specificAcceptance.getRequestCode());
				    	cargoItem.setCargoCategory("VC");
				    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
				    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
				    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
				    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
				    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
				    	cargoItem.setSequence(index);
				    	
						try {
							if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
							{
							cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
									+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
									+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
									+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
							TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
							else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
							{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
						} catch (SystemException e) {
							log.error(e.getMessage());
						};					
				     
				    
				   }				
				
				if (cargoDescription.trim().length() > 0)
				{						
					specificAcceptance.setCargo(StringPool.BLANK);						
					specificAcceptance.setCargoUnit(StringPool.BLANK);
					specificAcceptance.setCargoDescription(cargoDescription);
				}
				
				
				specificAcceptance = IssueAcceptanceLocalServiceUtil
						.addIssueAcceptance(specificAcceptance);
				log.info("===========MOT_LAN====" + "INSERT");

			} else if (suaIssueAcceptance.equalsIgnoreCase(KeyParams.MOT_LAN)) {

				specificAcceptance.setId(idIssueAcceptance);
				specificAcceptance.setRequestCode(requestCodeAcceptance);
				specificAcceptance.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_SUA);
				
				cargoDescription = "";
				List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYearAndRequestCode(documentName, documentYear, requestCodeAcceptance);
				if (items != null && items.size() > 0){
					for (TempCargoItems carg: items) {
						TempCargoItemsLocalServiceUtil.deleteTempCargoItems(carg);
					}
				}
				
				int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
				
				for(int index : indexs) {				    
				    
				    	TempCargoItems cargoItem = null;
				    	cargoItem = new TempCargoItems();
				    	cargoItem.setDocumentName(documentName);
				    	cargoItem.setDocumentYear(documentYear);
				    	cargoItem.setRequestCode(specificAcceptance.getRequestCode());
				    	cargoItem.setCargoCategory("VC");
				    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
				    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
				    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
				    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
				    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
				    	cargoItem.setSequence(index);
				    	
						try {
							if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
							{
							cargoDescription += "\n" + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
									+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
									+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
									+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
							TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
							else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
							{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
						} catch (SystemException e) {
							log.error(e.getMessage());
						};					
				     
						
				   }
				
				if (cargoDescription.trim().length() > 0)
				{						
					specificAcceptance.setCargo(StringPool.BLANK);						
					specificAcceptance.setCargoUnit(StringPool.BLANK);
					specificAcceptance.setCargoDescription(cargoDescription);
				}
				specificAcceptance = IssueAcceptanceLocalServiceUtil
						.updateIssueAcceptance(specificAcceptance);
				log.info("===========MOT_LAN====id===" + idIssueAcceptance
						+ "===UPDATE");

			} else if (capIssueAcceptance.equalsIgnoreCase(KeyParams.N_LAN)) {

				specificAcceptance.setRequestCode(UUID.randomUUID().toString());
				specificAcceptance
						.setRequestState(TrangThaiBanKhaiXuatCanh.SUA_DOI_BO_XUNG);
				specificAcceptance.setRemarks(remarks);
				cargoDescription = "";
				
				int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
				
				for(int index : indexs) {				    
				    
				    	TempCargoItems cargoItem = null;
				    	cargoItem = new TempCargoItems();
				    	cargoItem.setDocumentName(documentName);
				    	cargoItem.setDocumentYear(documentYear);
				    	cargoItem.setRequestCode(specificAcceptance.getRequestCode());
				    	cargoItem.setCargoCategory("VC");
				    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
				    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
				    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
				    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
				    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
				    	cargoItem.setSequence(index);
				    	
						try {
							if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
							{
							cargoDescription += "\n" + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
									+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
									+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
									+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
							TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
							else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
							{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
						} catch (SystemException e) {
							log.error(e.getMessage());
						};
						
				   }
				
				if (cargoDescription.trim().length() > 0)
				{						
					specificAcceptance.setCargo(StringPool.BLANK);						
					specificAcceptance.setCargoUnit(StringPool.BLANK);
					specificAcceptance.setCargoDescription(cargoDescription);
				}
				specificAcceptance = IssueAcceptanceLocalServiceUtil
						.addIssueAcceptance(specificAcceptance);
				log.info("===========N_LAN=====" + "INSERT");

			} else if (suaIssueAcceptance.equalsIgnoreCase(KeyParams.N_LAN)) {

				specificAcceptance.setId(idIssueAcceptance);
				specificAcceptance.setRequestCode(requestCodeAcceptance);
				specificAcceptance
						.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_SUA);
				cargoDescription = "";
				
				List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYearAndRequestCode(documentName, documentYear, requestCodeAcceptance);
				if (items != null && items.size() > 0){
					for (TempCargoItems carg: items) {
						TempCargoItemsLocalServiceUtil.deleteTempCargoItems(carg);
					}
				}
				
				int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
				
				for(int index : indexs) {				    
				    
				    	TempCargoItems cargoItem = null;
				    	cargoItem = new TempCargoItems();
				    	cargoItem.setDocumentName(documentName);
				    	cargoItem.setDocumentYear(documentYear);
				    	cargoItem.setRequestCode(specificAcceptance.getRequestCode());
				    	cargoItem.setCargoCategory("VC");
				    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
				    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
				    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
				    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
				    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
				    	cargoItem.setSequence(index);
				    	
						try {
							if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
							{
							cargoDescription += "\n" + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
									+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
									+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
									+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
							TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
							else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
							{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
						} catch (SystemException e) {
							log.error(e.getMessage());
						};					
				     
				   }
				
				if (cargoDescription.trim().length() > 0)
				{						
					specificAcceptance.setCargo(StringPool.BLANK);						
					specificAcceptance.setCargoUnit(StringPool.BLANK);
					specificAcceptance.setCargoDescription(cargoDescription);
				}
				specificAcceptance = IssueAcceptanceLocalServiceUtil
						.updateIssueAcceptance(specificAcceptance);
				log.info("===========N_LAN===id===" + idIssueAcceptance
						+ "=UPDATE");
				
				
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getMessage());
		}
		return null;
	}

	public void xacNhanHoanThanhThuTucXuatCanhTruongHopHuy(
			ActionRequest resourceRequest, long documentName, int documentYear,
			String lyDoHuyXC99) {

		ResultCompletion resultCompetion = null;

		resultCompetion = ResultCompetionLocalServiceUtil
				.findByDocumentNameAndDocumentYear(documentName, documentYear);
		String maritimeCode = ParamUtil.getString(resourceRequest,
				"maritimeCode");
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip");
		String nameOfMaster = ParamUtil.getString(resourceRequest,
				"nameOfMaster");
		String portOfArrivalCode = ParamUtil.getString(resourceRequest,
				"portOfArrivalCode");
		String certificateNo = ParamUtil.getString(resourceRequest,
				"certificateNo");
		String maritimeReference = ParamUtil.getString(resourceRequest,
				"maritimeReference");
		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip");
		double grossTonnage = ParamUtil.getDouble(resourceRequest,
				"grossTonnage", 0L);

		log.info("===vao vao =grossTonnage=" + grossTonnage);

		Date approvalTime = ParamUtil.getDate(resourceRequest, "approvalTime",
				FormatData.formatDateShort3);
		String approvalName = ParamUtil.getString(resourceRequest,
				"approvalName");

		try {
			log.info("xacNhanHoanThanhThuTucQuaCanhTruongHopHuy "
					+ documentName + "  documentYear  " + documentYear);
			if (resultCompetion == null) {

				log.info("========vao insert====xacNhanHoanThanhThuTucQuaCanhTruongHopHuy=====");
				resultCompetion = new ResultCompletion();
				resultCompetion.setMaritimeCode(maritimeCode);
				resultCompetion.setNameOfShip(nameOfShip);
				resultCompetion.setNameOfMaster(nameOfMaster);
				resultCompetion.setPortOfArrivalCode(portOfArrivalCode);
				resultCompetion.setCertificateNo(certificateNo);
				resultCompetion.setCertificateNo(certificateNo.trim() + "/"
						+ maritimeReference.trim());
				resultCompetion.setFlagStateOfShip(flagStateOfShip);
				resultCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				resultCompetion.setApprovalTime(approvalTime);
				resultCompetion.setApprovalName(approvalName);

				resultCompetion
						.setRequestState(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
				resultCompetion.setResponseStatusCVHH(MessageType.TU_CHOI);
				resultCompetion.setResponseTimeCVHH(approvalTime);
				resultCompetion.setResponseCVHH(BusinessUtils
						.getRemarkTuChoiTB(PortalUtil.getUser(resourceRequest)
								.getEmailAddress(), lyDoHuyXC99, approvalTime));

				resultCompetion.setDocumentName(documentName);
				resultCompetion.setDocumentYear(documentYear);
				resultCompetion.setRequestCode(UUID.randomUUID().toString());

				ResultCompetionLocalServiceUtil
						.addResultCompetion(resultCompetion);
			} else {

				log.info("xacNhanHoanThanhThuTucQuaCanhTruongHopHuy vao update");
				resultCompetion
						.setRequestState(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
				resultCompetion.setResponseStatusCVHH(MessageType.TU_CHOI);
				resultCompetion.setResponseTimeCVHH(approvalTime);
				resultCompetion.setResponseCVHH(BusinessUtils
						.getRemarkTuChoiTB(PortalUtil.getUser(resourceRequest)
								.getEmailAddress(), lyDoHuyXC99, approvalTime));

				resultCompetion.setMaritimeCode(maritimeCode);
				resultCompetion.setNameOfShip(nameOfShip);
				resultCompetion.setNameOfMaster(nameOfMaster);
				resultCompetion.setPortOfArrivalCode(portOfArrivalCode);
				resultCompetion.setCertificateNo(certificateNo);
				resultCompetion.setCertificateNo(certificateNo.trim() + "/"
						+ maritimeReference.trim());
				resultCompetion.setFlagStateOfShip(flagStateOfShip);
				resultCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				resultCompetion.setApprovalTime(approvalTime);
				resultCompetion.setApprovalName(approvalName);

				ResultCompetionLocalServiceUtil
						.updateResultCompetion(resultCompetion);

				// log.info("UPDATE XONG setRequestState" +
				// resultCompetion.getRequestState());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	public void xacNhanHoanThanhThuTucXuatCanh(ActionRequest resourceRequest,
			long documentName, int documentYear) {
		try {
			log.info("===vao xacNhanHoanThanhThuTucXuatCanh=====documentName====="
					+ documentName + "=====documentYear=====" + documentYear);

			String maritimeCode = ParamUtil.getString(resourceRequest,
					"maritimeCode");
			String nameOfShip = ParamUtil.getString(resourceRequest,
					"nameOfShip");
			String nameOfMaster = ParamUtil.getString(resourceRequest,
					"nameOfMaster");
			String portOfArrivalCode = ParamUtil.getString(resourceRequest,
					"portOfArrivalCode");
			String certificateNo = ParamUtil.getString(resourceRequest,
					"certificateNo");
			String maritimeReference = ParamUtil.getString(resourceRequest,
					"maritimeReference");
			String flagStateOfShip = ParamUtil.getString(resourceRequest,
					"flagStateOfShip");
			double grossTonnage = ParamUtil.getDouble(resourceRequest,
					"grossTonnage", 0L);
			Date approvalTime = ParamUtil.getDate(resourceRequest,
					"approvalTime", FormatData.formatDateShort3);
			String approvalName = ParamUtil.getString(resourceRequest,
					"approvalName");

			ResultCompletion resultCompetion = null;
			resultCompetion = ResultCompetionLocalServiceUtil
					.findByDocumentNameAndDocumentYear(documentName,
							documentYear);

			if (resultCompetion == null) {

				log.info("========vao insert====xacNhanHoanThanhThuTucXuatCanh===== + flagStateOfShip"
						+ flagStateOfShip);
				resultCompetion = new ResultCompletion();
				resultCompetion.setMaritimeCode(maritimeCode);
				resultCompetion.setNameOfShip(nameOfShip);
				resultCompetion.setNameOfMaster(nameOfMaster);
				resultCompetion.setPortOfArrivalCode(portOfArrivalCode);
				resultCompetion.setCertificateNo(certificateNo);
				resultCompetion.setCertificateNo(certificateNo.trim() + "/"
						+ maritimeReference.trim());
				resultCompetion.setFlagStateOfShip(flagStateOfShip);
				resultCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				resultCompetion.setApprovalTime(approvalTime);
				resultCompetion.setApprovalName(approvalName);

				resultCompetion
						.setRequestState(TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				resultCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				resultCompetion.setResponseTimeCVHH(approvalTime);
				resultCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil
								.getUser(resourceRequest).getEmailAddress(),
								approvalTime));

				resultCompetion.setDocumentName(documentName);
				resultCompetion.setDocumentYear(documentYear);
				resultCompetion.setRequestCode(UUID.randomUUID().toString());

				ResultCompetionLocalServiceUtil
						.addResultCompetion(resultCompetion);
			} else {

				// log.info("========vao update===xacNhanHoanThanhThuTucXuatCanh======");
				resultCompetion.setMaritimeCode(maritimeCode);
				resultCompetion.setNameOfShip(nameOfShip);
				resultCompetion.setNameOfMaster(nameOfMaster);
				resultCompetion.setPortOfArrivalCode(portOfArrivalCode);

				resultCompetion
						.setRequestState(TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				resultCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				resultCompetion.setResponseTimeCVHH(approvalTime);
				resultCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil
								.getUser(resourceRequest).getEmailAddress(),
								approvalTime));

				resultCompetion.setCertificateNo(certificateNo);
				resultCompetion.setCertificateNo(certificateNo.trim() + "/"
						+ maritimeReference.trim());
				resultCompetion.setFlagStateOfShip(flagStateOfShip);
				resultCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				resultCompetion.setApprovalTime(approvalTime);
				resultCompetion.setApprovalName(approvalName);

				ResultCompetionLocalServiceUtil
						.updateResultCompetion(resultCompetion);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * @param tempDocument
	 * @param messageType
	 * @param actionType
	 * @param userName
	 * @return
	 */
	public boolean thayDoiTrangThaiToanBoBanKhai(TempDocument tempDocument,
			int messageType, int actionType, String userName, String requestCode) {
		boolean result = true;
		try {

			List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil
					.findByRequestCode(requestCode);
			if (results != null && results.size() > 0) {
				TempShipSecurityMessage tempShipSecurityMessage = results
						.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempShipSecurityMessage
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);

				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempShipSecurityMessage
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempShipSecurityMessage
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempShipSecurityMessage
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempShipSecurityMessageLocalServiceUtil
						.updateTempShipSecurityMessage(tempShipSecurityMessage);
			}

			List<TempCargoDeclaration> resultTempCargoDeclaration = TempCargoDeclarationLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultTempCargoDeclaration != null
					&& resultTempCargoDeclaration.size() > 0) {
				TempCargoDeclaration tempCargoDeclaration = resultTempCargoDeclaration
						.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempCargoDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempCargoDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempCargoDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempCargoDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempCargoDeclarationLocalServiceUtil
						.updateTempCargoDeclaration(tempCargoDeclaration);
			}

			List<TempNoticeShipMessage> resultTempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultTempNoTiceShipMessage != null
					&& resultTempNoTiceShipMessage.size() > 0) {
				TempNoticeShipMessage tempNoticeShipMessage = resultTempNoTiceShipMessage
						.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempNoticeShipMessage
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempNoticeShipMessage
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempNoticeShipMessage
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempNoticeShipMessage
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempNoTiceShipMessageLocalServiceUtil
						.updateTempNoTiceShipMessage(tempNoticeShipMessage);
			}

			List<TempGeneralDeclaration> resultGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultGeneralDeclaration != null
					&& resultGeneralDeclaration.size() > 0) {
				TempGeneralDeclaration tempGeneralDeclaration = resultGeneralDeclaration
						.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempGeneralDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempGeneralDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempGeneralDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempGeneralDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempGeneralDeclarationLocalServiceUtil
						.updateTempGeneralDeclaration(tempGeneralDeclaration);
			}

			List<TempCrewList> resultCrewList = TempCrewListLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultCrewList != null && resultCrewList.size() > 0) {
				TempCrewList tempCrewList = resultCrewList.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempCrewList
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempCrewList
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempCrewList
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempCrewList
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempCrewListLocalServiceUtil.updateTempCrewList(tempCrewList);
			}

			List<TempPassengerList> resultPassengerList = TempPassengerListLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultPassengerList != null && resultPassengerList.size() > 0) {
				TempPassengerList tempPassenger = resultPassengerList.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempPassenger
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempPassenger
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempPassenger
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempPassenger
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempPassengerListLocalServiceUtil
						.updateTempPassengerList(tempPassenger);
			}

			List<TempDangerousGoodsManifest> resultDangerousGoodsNanifests = TempDangerousGoodsNanifestLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultDangerousGoodsNanifests != null
					&& resultDangerousGoodsNanifests.size() > 0) {
				TempDangerousGoodsManifest tempDangerousGoodsNanifest = resultDangerousGoodsNanifests
						.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempDangerousGoodsNanifest
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempDangerousGoodsNanifest
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempDangerousGoodsNanifest
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempDangerousGoodsNanifest
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempDangerousGoodsNanifestLocalServiceUtil
						.updateTempDangerousGoodsNanifest(tempDangerousGoodsNanifest);
			}

			List<TempShipStoresDeclaration> resultTempShipStoresDeclarations = TempShipStoresDeclarationLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultTempShipStoresDeclarations != null
					&& resultTempShipStoresDeclarations.size() > 0) {
				TempShipStoresDeclaration tempShipStoresDeclaration = resultTempShipStoresDeclarations
						.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempShipStoresDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempShipStoresDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempShipStoresDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempShipStoresDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempShipStoresDeclarationLocalServiceUtil
						.updateTempShipStoresDeclaration(tempShipStoresDeclaration);
			}

			List<TempCrewEffectsDeclaration> resultTempCrewEffectsDeclarations = TempCrewEffectsDeclarationLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultTempCrewEffectsDeclarations != null
					&& resultTempCrewEffectsDeclarations.size() > 0) {
				TempCrewEffectsDeclaration tempCrewEffectsDeclaration = resultTempCrewEffectsDeclarations
						.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempCrewEffectsDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempCrewEffectsDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempCrewEffectsDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempCrewEffectsDeclaration
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempCrewEffectsDeclarationLocalServiceUtil
						.updateTempCrewEffectsDeclaration(tempCrewEffectsDeclaration);
			}

			List<TempDeclarationOfHealth> resultTempDeclarationOfHealths = TempDeclarationOfHealthLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultTempDeclarationOfHealths != null
					&& resultTempDeclarationOfHealths.size() > 0) {
				TempDeclarationOfHealth tempDeclarationOfHealth = resultTempDeclarationOfHealths
						.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempDeclarationOfHealth
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempDeclarationOfHealth
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempDeclarationOfHealth
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempDeclarationOfHealth
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempDeclarationOfHealthLocalServiceUtil
						.updateTempDeclarationOfHealth(tempDeclarationOfHealth);
			}

			List<TempAnimalQuarantine> resultAnimalQuarantines = TempAnimalQuarantineLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultAnimalQuarantines != null
					&& resultAnimalQuarantines.size() > 0) {
				TempAnimalQuarantine tempPlantQuarantine = resultAnimalQuarantines
						.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempPlantQuarantine
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempPlantQuarantine
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempPlantQuarantine
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempPlantQuarantine
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempAnimalQuarantineLocalServiceUtil
						.updateTempAnimalQuarantine(tempPlantQuarantine);
			}

			List<TempPlantQuarantine> resultTempPlantQuarantines = TempPlantQuarantineLocalServiceUtil
					.findByRequestCode(requestCode);
			if (resultTempPlantQuarantines != null
					&& resultTempPlantQuarantines.size() > 0) {
				TempPlantQuarantine tempPlantQuarantine = resultTempPlantQuarantines
						.get(0);
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					tempPlantQuarantine
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					tempPlantQuarantine
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					tempPlantQuarantine
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					tempPlantQuarantine
							.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
				}
				TempPlantQuarantineLocalServiceUtil
						.updateTempPlantQuarantine(tempPlantQuarantine);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}

	public void sendChapNhan(int messageType, String function,
			TempDocument tempDocument, String userName, String requestCode,
			ActionRequest resourceRequest) {
		try {
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findByRequestCode(requestCode);
			Header header = BusinessUtils.createHeader(tempDocument,
					MessageType.NHAP_CANH, function, messageType, userName,
					interfaceRequest);

			BusinessUtils businessUtils = new BusinessUtils();
//			IMTService imtService = CallWs2HaiQuan.getIMTSercice();
			String xmlData = businessUtils.sendMessageAccept(header,
					BusinessUtils.getOrganizationFromUser(resourceRequest),
					businessUtils.getDivision(resourceRequest), header
							.getFrom().getName(), new Date(), tempDocument
							.getRequestCode());
			if (xmlData != null && xmlData.length() > 0) {
				businessUtils.insertHistorySendMessage(xmlData);
				String xml = "";

				if (tempDocument.getDocumentTypeCode().equals(
						MessageType.LOAT_THU_TUC_VAO_CANG)
						|| tempDocument.getDocumentTypeCode().equals(
								MessageType.LOAT_THU_TUC_ROI_CANG)) {
					log.info(" ----CAll receiveFromInland------- ");
					// xml = imtService.receiveFromInland(xmlData);

				} else {
					log.info(" ----CAll receiveResultFromMT------- ");
					// xml = imtService.receiveResultFromMT(xmlData);
				}

				businessUtils.insertHistoryReceiveMessageResponse(xml);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private String createXmlSuaDoiBoXung(int messageType,
			TempDocument tempDocument, String userName,
			ActionRequest resourceRequest, BusinessUtils businessUtils,
			InterfaceRequest interfaceRequest) throws SystemException {

		String function = MessageType.FUNCTION_THONG_BAO_BO_XUNG;
		String lyDoSuaDoiBoSung = ParamUtil.getString(resourceRequest,
				PageType.LY_DO_SUADOI_BOSUNG);
		if (lyDoSuaDoiBoSung == null || lyDoSuaDoiBoSung.length() == 0) {
			lyDoSuaDoiBoSung = "System";
		}
		if (interfaceRequest == null) {
			interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument
							.getRequestCode());
		}

		Header header = null;
		if (tempDocument.getDocumentTypeCode().equals(
				MessageType.LOAT_THU_TUC_ROI_CANG)) {
			header = BusinessUtils.createHeaderInland(tempDocument,
					MessageType.TAU_RA, MessageType.FUNCTION_THONG_BAO_BO_XUNG,
					MessageType.HO_SO, userName, interfaceRequest);
		} else if (tempDocument.getDocumentTypeCode().equals(
				MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
			header = BusinessUtils.createHeaderInland(tempDocument,
					MessageType.TAU_RA_PTTND,
					MessageType.FUNCTION_THONG_BAO_BO_XUNG, MessageType.HO_SO,
					userName, interfaceRequest);
		} else if (tempDocument.getDocumentTypeCode().equals(
				MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
			header = BusinessUtils.createHeaderInland(tempDocument,
					MessageType.TAU_VAO_PTTND,
					MessageType.FUNCTION_THONG_BAO_BO_XUNG, MessageType.HO_SO,
					userName, interfaceRequest);
		} else {
			header = BusinessUtils.createHeader(tempDocument,
					MessageType.XUAT_CANH,
					MessageType.FUNCTION_THONG_BAO_BO_XUNG, MessageType.HO_SO,
					userName, interfaceRequest);
		}

		vn.gt.tichhop.message.Modify modify = new vn.gt.tichhop.message.Modify();
		modify.setDivision(businessUtils.getDivision(resourceRequest));
		modify.setOrganization(BusinessUtils
				.getOrganizationFromUser(resourceRequest));
		modify.setName(header.getFrom().getName());
		modify.setModifyDate(FormatData.parseDateToTring(new Date()));
		modify.setModifyDesc(lyDoSuaDoiBoSung);
		modify.setModifyCode("Bo Xung Ho So");
		// xmlData = MessageFactory.convertObjectToXml(modify);
		if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
			Modify modify2 = new Modify();
			modify2.setId(CounterLocalServiceUtil.increment(Modify.class
					.getName()));
			modify2.setDivision(modify.getDivision());
			modify2.setDocumentname(tempDocument.getDocumentName());
			modify2.setDocumentyear(tempDocument.getDocumentYear());
			modify2.setModifycode(modify.getModifyCode());
			modify2.setModifydesc(modify.getModifyDesc());
			modify2.setModifydate(new Date());
			ModifyLocalServiceUtil.addModify(modify2);
		}
		return businessUtils.sendMessageModify(header, modify,
				tempDocument.getRequestCode());
	}

	private void insertOrUpdateResultNotification(TempDocument temp,
			String userName, String lyDoTuChoi, int messageType) {
		try {
			// MessageType.HUY_LENH_DIEU_DONG
			ResultNotification resultNotification = ResultNotificationLocalServiceUtil
					.findByBusinessTypeCode(messageType,
							temp.getDocumentName(), temp.getDocumentYear());
			if (resultNotification == null) {
				// Them moi.
				resultNotification = new ResultNotification();
				resultNotification.setBusinessTypeCode(messageType);
				resultNotification.setDivision("System");
				resultNotification.setDocumentName(temp.getDocumentName());
				resultNotification.setDocumentYear(temp.getDocumentYear());
				resultNotification.setLatestDate(new Date());
				resultNotification.setRequestCode(UUID.randomUUID().toString());

				if (temp != null) {
					resultNotification.setMaritimeCode(temp.getMaritimeCode());
				}

				// resultNotification.setRemarks(userName);

				// setRole Thutuc - Kehoach
				if (messageType == MessageType.BO_SUNG_KE_HOACH  || messageType == MessageType.YC_LAM_THU_TUC_TRUC_TIEP)  {
					resultNotification.setRole(2);
				} else if (messageType == MessageType.HUY_GIAY_PHEP_ROI_CANG
						|| messageType == MessageType.HUY_GIAY_PHEP_VAO_CANG
						|| messageType == MessageType.BO_SUNG_THU_TUC
						) {
					resultNotification.setRole(4);
				} else {
					resultNotification.setRole(2);
				}
				resultNotification.setResponse(lyDoTuChoi);
				resultNotification.setRequestState(1);
				resultNotification.setResponseTime(new Date());
				resultNotification.setOfficerName(userName);
				resultNotification.setLatestDate(new Date());
				resultNotification.setIsReply(1);

				ResultNotificationLocalServiceUtil
						.addResultNotification(resultNotification);
			} else {
				// setRole Thutuc - Kehoach
				if (messageType == MessageType.BO_SUNG_KE_HOACH || messageType == MessageType.YC_LAM_THU_TUC_TRUC_TIEP) {
					resultNotification.setRole(2);
				} else if (messageType == MessageType.HUY_GIAY_PHEP_ROI_CANG
						|| messageType == MessageType.HUY_GIAY_PHEP_VAO_CANG
						|| messageType == MessageType.BO_SUNG_THU_TUC) {
					resultNotification.setRole(4);
				} else {
					resultNotification.setRole(2);
				}
				resultNotification.setResponse(lyDoTuChoi);
				resultNotification.setRequestState(1);
				if (temp != null) {
					resultNotification.setMaritimeCode(temp.getMaritimeCode());
				}
				resultNotification.setResponseTime(new Date());
				resultNotification.setOfficerName(userName);
				resultNotification.setLatestDate(new Date());
				resultNotification.setIsReply(1);

				ResultNotificationLocalServiceUtil
						.updateResultNotification(resultNotification);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getMessage());
		}
	}

}
