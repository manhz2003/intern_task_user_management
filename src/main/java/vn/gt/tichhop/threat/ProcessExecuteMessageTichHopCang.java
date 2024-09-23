/**
 * 
 */
package vn.gt.tichhop.threat;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
//import vn.com.elcom.www.VNMWSSoap;
import com.fds.nsw.nghiepvu.model.DmGtRouteConfig;
import vn.gt.dao.danhmucgt.service.DmGtRouteConfigLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.HistoryInterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiHoSo;
import vn.gt.utils.CallWs2CangVu;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.fac.ReadMessages;




/**
 * @author win_64
 */
@Slf4j
public class ProcessExecuteMessageTichHopCang implements Runnable {
	
	
	private Thread t;
	private int timeSleep = 1000;
	private String threadName;
	private BusinessUtils businessUtils = new BusinessUtils();
	
	public ProcessExecuteMessageTichHopCang(String name, int timeSleep) {
		threadName = name;
		this.timeSleep = timeSleep;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				ObjectExcute objectExcute = ThreadPassingMessageTichHopCangVu.init().getObjectExcute();
				
				if (objectExcute != null) {
					if (objectExcute.getHeader() != null && objectExcute.getLiObjects() != null) {
						
						// La ban tin gui di Cang vu .
						int messageType = objectExcute.getHeader().getSubject().getType();
						log.info("==check co gui ket qua hai quan sang cang vu ko messageType==" + messageType);
						
						if (messageType == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN |
								messageType == MessageType.BAN_KHAI_HANG_HOA |
								messageType == MessageType.THONG_BAO_TAU_DEN_CANG |
								messageType == MessageType.XAC_BAO_TAU_DEN_CANG |
								messageType == MessageType.HO_SO |
								messageType == MessageType.THONG_BAO_TAU_ROI_CANG |
								messageType == MessageType.BO_NGANH_PHE_DUYET_HO_SO |
								messageType == MessageType.HUY_GIAY_PHEP_ROI_CANG |
								messageType == MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG |
								messageType == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG |
								messageType == MessageType.XAC_NHAN_THONG_QUAN |
								
								// son_vh bo sung 20160119
								messageType == MessageType.THONG_BAO_TAU_QUA_CANH |
								messageType == MessageType.XAC_BAO_TAU_QUA_CANH |
								messageType == MessageType.BAN_KHAI_CHUNG |
								messageType == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN |
								messageType == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH |
								messageType == MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM |
								messageType == MessageType.LENH_DIEU_DONG |
								messageType == MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH |
								messageType == MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN |
								messageType == MessageType.GIAY_PHEP_QUA_CANH |
								messageType == MessageType.HUY_GIAY_PHEP_QUA_CANH |
								messageType == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH |
								messageType == MessageType.CHAM_PHE_DUYET_HOAN_THANH_THU_THUC |
								messageType == MessageType.BAN_KHAI_KET_QUA_XEM_XET_CHUNG_TU_DINH_KEM |
								messageType == MessageType.BAN_KHAI_CHUNG_TU_DINH_KEM
								
								) {
							
//							sendChapNhan(objectExcute);
						}
					}
				}
				
				Thread.sleep(timeSleep);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
	
	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
			log.info("===Starting-------------- " + t.getName());
		}
	}
	
//	private void sendChapNhan(ObjectExcute objectExcute) {
//		try {
//			List<TempDocument> liTempDocuments = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear(objectExcute.getHeader()
//					.getSubject().getReference(), objectExcute.getHeader().getSubject().getDocumentYear());
//
//			TempDocument temDocument = null;
//
//			if(liTempDocuments != null && liTempDocuments.size() > 0) {
//				temDocument = liTempDocuments.get(0);
//			}
//
//			if(temDocument != null) {
//				List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByLocCode(temDocument.getMaritimeCode());
//
//				for(DmGtRouteConfig routeConfig : routeConfigs) {
//					log.info("=============gui  sang cang vu=========== " + objectExcute.getXmlData());
//
//					VNMWSSoap vnmwsSoap = CallWs2CangVu.getVNMWS(routeConfig.getRouteCode());
//					String vinamarineResponse = "NOT_CONNECTED_WEBSERVICE: VNMWSSoap is null";
//
//					if(vnmwsSoap != null) {
//						vinamarineResponse = vnmwsSoap.sendMessage(objectExcute.getXmlData());
//
//						insertHistoryReceiveMessageResponse(vinamarineResponse);
//					}
//
//					log.info("======================================Nhan ve tu cang vu================= " + vinamarineResponse);
//				}
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//	}
	
	private void insertHistoryReceiveMessageResponse(String dataInput) {
		Header header = null;
		log.info("===insertHistoryReceiveMessageResponse==Thong tin nhan tu Cang Vu===" + dataInput);
		String data = businessUtils.replaceXML(dataInput.trim());
		
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			header = ReadMessages.readXMLMessagesHeader(data);
			log.info("insertHistoryReceiveMessageResponse  " + header.getSubject().getFunction());
			if (header.getSubject().getFunction().equals(MessageType.FUNCTION_THONG_BAO_DA_NHAN_DUOC)) {
				log.info("insertHistoryReceiveMessageResponse  " + header.getSubject().getFunction());
				BusinessUtils.insertHistory(header, dataInput, BusinessUtils.HqmcToBoGiaoThong, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID()
						.toString());
				
				try {
					String messageId = header.getReference().getMessageId();
					HistoryInterfaceRequest historyInterfaceRequest = HistoryInterfaceRequestLocalServiceUtil
							.findHistoryInterfaceRequestByRequestCode(messageId);
					historyInterfaceRequest.setSendingDate(FormatData.parseStringToDate(header.getSubject().getSendDate()));
					if (historyInterfaceRequest.getRequestState() == 1) {
						historyInterfaceRequest.setRequestState(2);
					} else if (historyInterfaceRequest.getRequestState() == 3 || historyInterfaceRequest.getRequestState() == 2) {
						historyInterfaceRequest.setRequestState(4);
					}
					HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(historyInterfaceRequest);
				} catch (Exception e) {
					log.info(e.getMessage());
				}
			}
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
}