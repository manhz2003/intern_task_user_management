package vn.gt.tichhop.sendmessage;

import java.util.Date;
import java.util.List;

import com.fds.flex.common.ultility.array.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
//import org.tempuri.IMTService;

//import vn.com.elcom.www.VNMWSSoap;
import com.fds.nsw.nghiepvu.model.AswmsgMessagequeue;
import vn.gt.dao.asw.service.AswmsgMessageQueueLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest;
import com.fds.nsw.nghiepvu.model.InterfaceRequest;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.HistoryInterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.elcom.client.SendMessage2ElcomImpl;

import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiHoSo;
import vn.gt.utils.CallWs2CangVu;
import vn.gt.utils.CallWs2HaiQuan;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.gt.ws.util.WebserviceUtil;
import vn.nsw.Header;
import vn.nsw.fac.ReadMessages;

import com.fds.nsw.kernel.exception.SystemException;


import com.fds.flex.common.ultility.Validator;

@Slf4j
public class ProcessSendMessage implements Runnable {
	
	private static BusinessUtils businessUtils = new BusinessUtils();
	private Thread t;
	private int timeSleep = 900;
	private String threadName;

	public ProcessSendMessage(String name, int timeSleep) {
		this.threadName = name;
		this.timeSleep = timeSleep;
	}

	public void run() {
		int check = WebserviceUtil.checkGateway();//ConfigurationManager.getIntProp("vn.gt.coguihaykhong", 1);
		if (check > 0) {
			while (true) {
				try {
					Thread.sleep(timeSleep);
					
					//check in queue
					StringBuilder sb = new StringBuilder();
					for(AswmsgMessagequeue messageQueueTmp : ThreatSentMessage.sendMessageQueue ) {
						sb.append(messageQueueTmp.getId() + ";");
					}
					
					AswmsgMessagequeue messageQueue = ThreatSentMessage.sendMessageQueue.poll();
					
					if(messageQueue != null) {
						log.info("===messageQueue===ProcessSendMessage===" + messageQueue.getId() + "===" + sb.toString());
						if  ( (messageQueue.getType() == MessageType.DONG_BO_DM_CANG_VU_HANG_HAI) 
							|| (messageQueue.getType() == MessageType.DONG_BO_DM_CAU_CANG)
							|| (messageQueue.getType() == MessageType.DONG_BO_DM_BEN_CANG)
							|| (messageQueue.getType() == MessageType.DONG_BO_DM_KHU_VUC_CANG)
							|| (messageQueue.getType() == MessageType.DONG_BO_DM_CANG_BIEN_HANG_HAI)
							|| (messageQueue.getType() == MessageType.DONG_BO_DM_CANG_HAI_QUAN)
							|| (messageQueue.getType() == MessageType.DONG_BO_DM_HANG_HOA_HANG_HAI)							
							|| (ArrayUtil.contains(MessageType.DONG_BO_DM_MESSAGE_TYPES, messageQueue.getType())))
							{
//								sendMessageSynchronization(messageQueue);
							}else
							{
//								sendMessage(messageQueue);
							}
						
					}
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
	}

//	private void sendMessage(AswmsgMessagequeue messageQueue) {
//		int soLanGui = messageQueue.getSolangui();
//
//		long id = messageQueue.getId();
//
//		TempDocument tempDocument = null;
//
//		try {
//			//log.info("===messageQueue===AllContent===" + messageQueue.getAllContent());
//
//			AswmsgMessagequeue messageQueueCheck = AswmsgMessageQueueLocalServiceUtil.fetchAswmsgMessageQueue(messageQueue.getId());
//
//			// kiem tra xem message da duoc gui hay chua (da duoc xoa khoi AswmsgMessagequeue hay chua)
//			// Vi xay ra truong hop cung mot messageQueue nhung duoc add vao ThreatSentMessage.sendMessageQueue nhieu lan
//
//			if(messageQueueCheck != null) {
//				id = messageQueue.getId();
//
//				String data = businessUtils.replaceXML(messageQueue.getAllcontent());
//				Header headerRequest = BusinessUtils.readXmlMessageHeader(data);
//
//				List<TempDocument> liTempDocuments = TempDocumentLocalServiceUtil
//						.findTemDocumentByDocumentNameAndDocumentYear(headerRequest.getSubject().getReference(), headerRequest.getSubject().getDocumentYear());
//
//				if (liTempDocuments != null && liTempDocuments.size() > 0) {
//					tempDocument = liTempDocuments.get(0);
//				}
//
//				if (tempDocument != null) {
//					try {
//						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findInterfaceRequestByRequestCode(headerRequest
//								.getReference().getMessageId());
//
//						if (interfaceRequest != null) {
//							interfaceRequest.setRequestDirection(messageQueueCheck.getValidationcode());
//							interfaceRequest.setLocCode(tempDocument.getMaritimeCode());
//							interfaceRequest.setOrganizationCode("01");
//							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
//						}
//					} catch (Exception e) {
//						log.error(e.getMessage());
//					}
//
//					String returnXML = sendWS(messageQueue, tempDocument);
//
//					if (returnXML != null && returnXML.length() > 0) {
//						//log.info("===returnXML===" + returnXML);
//						AswmsgMessageQueueLocalServiceUtil.deleteAswmsgMessageQueue(id);
////						log.info("=======Du lieu NHAN  HQMC day ========");
//						businessUtils.insertHistoryReceiveMessageResponse(returnXML);
//					} else {
//						updateWithException(tempDocument, id, soLanGui);
//					}
//				}
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			// mr.The Anh Fix bug (nothing)
//			updateWithException(tempDocument, id, soLanGui);
//		}
//	}
	
//	private String sendWS(AswmsgMessagequeue messageQueue, TempDocument tempDocument) throws Exception {
//		String returnXML = null;
//
//		if (messageQueue != null && tempDocument != null
//				&& messageQueue.getAllcontent() != null
//				&& messageQueue.getAllcontent().length() > 0) {
//
//			String allContent = messageQueue.getAllcontent();
//
//			String data = businessUtils.replaceXML(allContent);
//			Header headerRequest = BusinessUtils.readXmlMessageHeader(data);
//
//			int documentType = headerRequest.getSubject().getDocumentType();
//			int messageType = headerRequest.getSubject().getType();
//
//			int[] thuTucMT = new int[]{
//					MessageType.NHAP_CANH, MessageType.XUAT_CANH,
//					MessageType.QUA_CANH,MessageType.NHAP_CANG_DAU_KHI,
//					MessageType.XUAT_CANG_DAU_KHI,MessageType.VAO_CANG_DAU_KHI,
//					MessageType.ROI_CANG_DAU_KHI};
//			//Them messagetype vao_cang, roi_cang
//			int[] thuTucInland = new int[] {MessageType.TAU_VAO, MessageType.TAU_RA, MessageType.CHUYEN_CANG_VAO, MessageType.CHUYEN_CANG_ROI, MessageType.VAO_CANG, MessageType.ROI_CANG};
//
//			int[] thuTuc67 = new int[]{MessageType.TAU_RA_PTTND,
//					MessageType.TAU_VAO_PTTND, MessageType.PTTND_NHAP_CANH, MessageType.PTTND_XUAT_CANH};
//
//			if(messageQueue.getValidationcode().equalsIgnoreCase(MessageType.REQUEST_DIRECTION_CV_TO_HQ)) {
//				// Gui message toi VNSW khi nhan duoc message tu CVHH
//
//				IMTService imtService = CallWs2HaiQuan.getIMTSercice();
//
//				returnXML = imtService.receiveResultFromMT(allContent);
//			} else if(messageQueue.getValidationcode().equalsIgnoreCase(MessageType.REQUEST_DIRECTION_OUT)
//					|| messageQueue.getValidationcode().equalsIgnoreCase(MessageType.NSW)) {
//				if (ArrayUtil.contains(thuTucMT, documentType)) {		// SEND VNSW
//
//					IMTService imtService = CallWs2HaiQuan.getIMTSercice();
//
//					returnXML = imtService.receiveResultFromMT(allContent);
//
//					log.info("===Process_Send_Messsage===returnXML===MT===" + returnXML);
//				} else if (ArrayUtil.contains(thuTucInland, documentType)) {	// SEND VNSW Inland
//
//					IMTService imtService = CallWs2HaiQuan.getIMTSercice();
//
//					returnXML = imtService.receiveFromInland(allContent);
//
//					log.info("===Process_Send_Messsage===returnXML===Inland===" + returnXML);
//				} else if (ArrayUtil.contains(thuTuc67, documentType)) {
//
//					IMTService imtService = CallWs2HaiQuan.getIMTSercice();
//
//					returnXML = imtService.receiveFromVIWA(allContent);
//
//					log.info("===Process_Send_Messsage===returnXML===VIWA===" + returnXML);
//				}
//
//			} else {
//				// Gui message toi CVHH
//
//				int[] messageTypeSendCVHH = new int[]{
//						MessageType.BAN_KHAI_AN_NINH_TAU_BIEN,
//						MessageType.BAN_KHAI_HANG_HOA,
//						MessageType.THONG_BAO_TAU_DEN_CANG,
//						MessageType.XAC_BAO_TAU_DEN_CANG,
//						MessageType.HO_SO,
//						MessageType.THONG_BAO_TAU_ROI_CANG,
//						MessageType.BO_NGANH_PHE_DUYET_HO_SO,
//						MessageType.HUY_GIAY_PHEP_ROI_CANG,
//						MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG,
//						MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG,
//						MessageType.XAC_NHAN_THONG_QUAN,
//						MessageType.THONG_BAO_TAU_QUA_CANH,
//						MessageType.XAC_BAO_TAU_QUA_CANH,
//						MessageType.BAN_KHAI_CHUNG,
//						MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN,
//						MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH,
//						MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM,
//						MessageType.LENH_DIEU_DONG,
//						MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH,
//						MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN,
//						MessageType.GIAY_PHEP_QUA_CANH,
//						MessageType.HUY_GIAY_PHEP_QUA_CANH,
//						MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH,
//						MessageType.BAN_KHAI_KET_QUA_XEM_XET_CHUNG_TU_DINH_KEM,
//						MessageType.BAN_KHAI_CHUNG_TU_DINH_KEM,
//						MessageType.CHAM_PHE_DUYET_HOAN_THANH_THU_THUC};
//
//				if (ArrayUtil.contains(messageTypeSendCVHH, messageType)) {
//
//					//log.info("===SEND_CVHH===" + allContent);
//					returnXML = sendCVHH(headerRequest, messageQueue, tempDocument);
//				} else {
//					// khong can phai gui di, xoa trong messagequeue
//					returnXML = String.valueOf(messageType);
//				}
//			}
//		}
//
//		return returnXML;
//	}
//
//	private String sendCVHH(Header header, AswmsgMessagequeue messageQueue, TempDocument tempDocument)
//			throws Exception {
//		String returnXML = "";
//		String allContent = messageQueue.getAllcontent();
//
//		if(tempDocument != null) {
//			log.info("===ELCOM_CMN===" + messageQueue.getId());
//
//			String address = messageQueue.getWebservice();
//			if(Validator.isNotNull(address) && !address.equals("-1")) {
//				SendMessage2ElcomImpl ws = CallWs2CangVu.getSendMessageImpl(address);
//
//				returnXML = ws.sendAndGetMessage(allContent);
//			} else if (Validator.isNull(address) || "-1".equals(address)) {
//				// legacy: send elcom
//				log.info("===gui=sang=elcom==cang_vu===" + messageQueue.getValidationcode() + "==="
//						+ header.getReference().getMessageId() + "=" + header.getSubject().getReference() + "="
//						+ header.getSubject().getType() + "=" + header.getSubject().getFunction());
//
//				String routeCode = "cvhh";
//
//				if(messageQueue.getValidationcode().equals(MessageType.REQUEST_DIRECTION_OUT_CVHH)) {
//					TempDocument document = TempDocumentLocalServiceUtil.getByDocumentNameAndDocumentYear(header.getSubject().getReference(), header.getSubject().getDocumentYear());
//
//					routeCode += document.getMaritimeCode();
//				} else {
//					routeCode = "cvhh" + messageQueue.getValidationcode();
//				}
//
//				VNMWSSoap vnmwsSoap = CallWs2CangVu.getVNMWS(routeCode);
//				if(vnmwsSoap != null) {
//					returnXML = "";
//
//					try {
//						returnXML = vnmwsSoap.sendMessage(allContent);
//					} catch(Exception e) {
//						log.info("===routeConfigCode===" + routeCode);
//						log.error("===SEND_CVHH_ERROR===" + e.getMessage());
//					}
//				} else {
//					log.info("NOT_CONNECTED_WEBSERVICE: VNMWSSoap is null");
//				}
//			}
//
////			List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByLocCode(
////					tempDocument.getMaritimeCode());
////
////			for(DmGtRouteConfig routeConfig : routeConfigs) {
////				log.info("===gui=sang=cang=vu===" +
////						header.getReference().getMessageId() + "=" +
////						header.getSubject().getReference() + "=" +
////						header.getSubject().getType() + "=" +
////						header.getSubject().getFunction());
////				log.debug("===gui=sang=cang=vu===allcontent===" + allContent);
////
////				VNMWSSoap vnmwsSoap = CallWs2CangVu.getVNMWS(routeConfig.getRouteCode());
////
////				if(vnmwsSoap != null) {
////					returnXML = "";
////
////					try {
////						returnXML = vnmwsSoap.sendMessage(allContent);
////					} catch(Exception e) {
////						log.info("===routeConfigCode===" + routeConfig.getRouteCode());
////						log.error("===SEND_CVHH_ERROR===" + e.getMessage());
////					}
////				} else {
////					log.info("NOT_CONNECTED_WEBSERVICE: VNMWSSoap is null");
////				}
////
////				log.debug("===returnXML===" + returnXML);
////
////				if(returnXML != null && returnXML.length() > 0) {
////					insertHistoryReceiveMessageResponseFromCVHH(returnXML);
////				}
////			}
//
//			if(returnXML != null && returnXML.length() > 0) {
//				insertHistoryReceiveMessageResponseFromCVHH(returnXML);
//			}
//		}
//
//		return returnXML;
//	}
//
	private void updateWithException(TempDocument tempDocument, long id, int soLanGui) {
		
		if(tempDocument != null) {
			int check = ConfigurationManager.getIntProp("solangui_message", 5);
			int soLanGuiSau = soLanGui + 1;
			if (soLanGui < check) {
				try {
					AswmsgMessagequeue aswmsgMessageQueue = AswmsgMessageQueueLocalServiceUtil.fetchAswmsgMessageQueue(id);
					if (aswmsgMessageQueue != null) {
						aswmsgMessageQueue.setSolangui(soLanGuiSau);
						aswmsgMessageQueue.setValidated(-1);	// tra lai trang thai cho xu ly
						aswmsgMessageQueue.setCreatedtime(new Date());
						AswmsgMessageQueueLocalServiceUtil.updateAswmsgMessageQueue(aswmsgMessageQueue);
					}
					
				} catch (SystemException e) {
					log.error(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Message dong bo danh muc voi NSW va Cang Vu
//	 * @param messageQueue
	 */
//	private void sendMessageSynchronization(AswmsgMessagequeue messageQueue) {
//		int soLanGui = messageQueue.getSolangui();
//
//		long id = messageQueue.getId();
//		TempDocument tempDocument = new TempDocument();
//
//		try {
//
//			AswmsgMessagequeue messageQueueCheck = AswmsgMessageQueueLocalServiceUtil.fetchAswmsgMessageQueue(messageQueue.getId());
//
//			// kiem tra xem message da duoc gui hay chua (da duoc xoa khoi AswmsgMessagequeue hay chua)
//			// Vi xay ra truong hop cung mot messageQueue nhung duoc add vao ThreatSentMessage.sendMessageQueue nhieu lan
//
//			if( messageQueueCheck != null
//				&& messageQueueCheck.getAllcontent() != null
//				&& messageQueueCheck.getAllcontent().length() > 0) {
//
//				String allContent = messageQueueCheck.getAllcontent();
//
//				id = messageQueue.getId();
////				int documentType = Integer.parseInt(messageQueue.getDocumentType());
//				String data = businessUtils.replaceXML(messageQueue.getAllcontent());
//				Header headerRequest = BusinessUtils.readXmlMessageHeader(data);
//
//				try {
//					InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findInterfaceRequestByRequestCode(headerRequest
//							.getReference().getMessageId());
//
//					if (interfaceRequest != null) {
//						interfaceRequest.setLocCode("---");
//						interfaceRequest.setOrganizationCode("01");
//						interfaceRequest.setRequestDirection(messageQueueCheck.getValidationcode());
//						InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
//					}
//				} catch (Exception e) {
//					log.error(e.getMessage());
//				}
//
//				String returnXML = "";
//
//				if(messageQueue.getValidationcode().equalsIgnoreCase(MessageType.REQUEST_DIRECTION_OUT)
//						|| messageQueue.getValidationcode().equalsIgnoreCase(MessageType.NSW)) {
//
//					IMTService imtService = CallWs2HaiQuan.getIMTSercice();
//					returnXML = imtService.receiveResultFromMT(allContent);
//				} else {
//					//TODO:
//
//					String address = messageQueue.getWebservice();
//					if(Validator.isNotNull(address) && !address.equals("-1")) {
//						SendMessage2ElcomImpl ws = CallWs2CangVu.getSendMessageImpl(address);
//
//						returnXML = ws.sendAndGetMessage(allContent);
//					}
//				}
//
//				if (returnXML != null && returnXML.length() > 0) {
//					businessUtils.insertHistoryReceiveMessageResponse(returnXML);
//				}
//
//				//TODO: OLD
////				if(messageQueue.getValidationCode().equalsIgnoreCase(MessageType.REQUEST_DIRECTION_OUT_CVHH)) {
////					List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByIsDelete(0);
////
////					// Dong bo danh muc sang Cang Vu
////					for(DmGtRouteConfig routeConfig : routeConfigs) {
////
////						//TODO: Hien config de gui sang CV Vung Tau
////						VNMWSSoap vnmwsSoap = CallWs2CangVu.getVNMWS(routeConfig.getRouteCode());
////
////						if(vnmwsSoap != null) {
////							try {
////								log.info("===gui=sang=cang=vu===" +
////										headerRequest.getReference().getMessageId() + "=" +
////										headerRequest.getSubject().getReference() + "=" +
////										headerRequest.getSubject().getType() + "=" +
////										headerRequest.getSubject().getFunction());
////								log.debug("===gui=sang=cang=vu===allcontent===" + allContent);
////
////								returnXML = vnmwsSoap.sendMessage(allContent);
////							} catch(Exception e) {
////								log.error("===SEND_CVHH_ERROR===" + e.getMessage());
////							}
////						}
////
////						log.debug("===returnXML===" + returnXML);
////					}
////
////					if (returnXML != null && returnXML.length() > 0) {
////						insertHistoryReceiveMessageResponseFromCVHH(returnXML);
////					}
////				} else {
////					// Dong bo danh muc sang NSW
////					IMTService imtService = CallWs2HaiQuan.getIMTSercice();
////					returnXML = imtService.receiveResultFromMT(allContent);
////
////					if (returnXML != null && returnXML.length() > 0) {
////						businessUtils.insertHistoryReceiveMessageResponse(returnXML);
////					}
////				}
//
//				if (returnXML != null && returnXML.length() > 0) {
//					AswmsgMessageQueueLocalServiceUtil.deleteAswmsgMessageQueue(id);
//				} else {
//					updateWithException(tempDocument, id, soLanGui);
//				}
//			}
//		} catch (Exception e) {
//
//			updateWithException(tempDocument, id, soLanGui);
//		}
//	}
//
	private void insertHistoryReceiveMessageResponseFromCVHH(String dataInput) {
		Header header = null;
		log.info("===insertHistoryReceiveMessageResponse==Thong tin nhan tu Cang Vu===" + dataInput);
		String data = businessUtils.replaceXML(dataInput.trim());
		
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			header = ReadMessages.readXMLMessagesHeader(data);
			log.info("insertHistoryReceiveMessageResponse  " + header.getSubject().getFunction());
			if (header.getSubject().getFunction().equals(MessageType.FUNCTION_THONG_BAO_DA_NHAN_DUOC)) {
				BusinessUtils.insertHistory(header, dataInput, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, header.getReference().getMessageId() + "_CV");
				
				String messageId = header.getReference().getMessageId();
				
				HistoryInterfaceRequest historyInterfaceRequest = HistoryInterfaceRequestLocalServiceUtil
						.findHistoryInterfaceRequestByRequestCode(messageId);
				
				
				if(historyInterfaceRequest != null) {
					historyInterfaceRequest.setSendingDate(FormatData.parseStringToDate(header.getSubject().getSendDate()));
					
					if (historyInterfaceRequest.getRequestState() == 1) {
						historyInterfaceRequest.setRequestState(2);
					} else if (historyInterfaceRequest.getRequestState() == 3 || historyInterfaceRequest.getRequestState() == 2) {
						historyInterfaceRequest.setRequestState(4);
					}
					
					HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(historyInterfaceRequest);
				}
			}
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
			log.info("===Starting-------------- " + t.getName());
		}
	}

}
