package vn.gt.tichhop.message.haiquan2giaothong;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmPort;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import com.fds.nsw.nghiepvu.model.DmHistoryPort;
import com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour;
import com.fds.nsw.nghiepvu.model.DmHistoryPortRegion;
import com.fds.nsw.nghiepvu.model.DmHistoryPortWharf;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortLocalServiceUtil;



import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageFactory;
import vn.gt.tichhop.message.MessageSyncUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiHoSo;
import vn.gt.tichhop.sendmessage.MessgaePriorityLevels;
import vn.gt.tichhop.sendmessage.SendMessgaeFunctions;
import vn.nsw.Header;
import vn.nsw.model.PortList;
import vn.nsw.model.PortWharfList;
import vn.nsw.model.PortHarbourList;
import vn.nsw.model.PortRegionList;
import vn.nsw.model.PortOfAuthorityList;
import vn.nsw.model.ReferencePortList;



@Slf4j
public class DMListSyn {

	
	
	public String insert2DMPort(Header header, String dataInput, String data) throws Exception{		
			
	BusinessUtils businessUtils = new BusinessUtils();
	try {			
		// Insert history.
		String kq = BusinessUtils.insertHistory(header, dataInput,
				BusinessUtils.HqmcToBoGiaoThong,
				TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);
		
		if (kq != null && kq.length() > 0) {
			// Ban tin tra ve khi validate error.
			String xmlResultError = BusinessUtils.createReturnContentAfterValidationErrorFromNSW(header, kq);
			return xmlResultError;
		}
		// Insert temp table when NO error.			
		List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(data);
		
		//TODO: Thua, do ham ThreadpasssingMessage khong xu ly voi message dong bo 
		/*ThreadPassingMessage.init().add(
				new ObjectExcute(header, liObjects, dataInput));*/

		businessUtils.updateHistory(header.getReference().getMessageId(),
				TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);			
		log.info("messageType104 " + "  -- size "+ liObjects.size() + "-- obj "+ liObjects );
		
		// Gui msg dong bo danh muc sang CVHH

		
		for (Object obj : liObjects) {
			
			if (obj instanceof PortList) {
				DmHistoryPort historyPortSync = null;
				
				PortList lsport = ((PortList) obj);
				long iDataGroupId = 104;
				List<PortList.Port> dmport = null;
				if ((lsport.getPort() != null) && (lsport.getPort().size() > 0)) {
					dmport= lsport.getPort();
				}
				if (dmport != null && dmport.size() > 0){
					log.info("messageType104 -- iDataGroupId "+ iDataGroupId + "  -- size "+ dmport.size());
					for (PortList.Port item: dmport){
						DmPort details=new DmPort();
						DmHistoryPort history=new DmHistoryPort();
						String syncVersion = "1";
						
						details.setPortCode(item.getPortCode());
						details.setPortName(item.getPortName());
						
						history.setPortCode(item.getPortCode());
						history.setPortName(item.getPortName());
						
						boolean isDeleted = item.getStatus() != null ? item.getStatus().equals("0") : item.getIsDelete().equals("1");
						
						if (isDeleted) {
							log.info("messageType104  Xoa");
							DmPort dmPortitem = DmPortLocalServiceUtil.getByPortCode(item.getPortCode());
							dmPortitem.setIsDelete(1);
							dmPortitem.setMarkedAsDelete(1);
							// dmPortitem.setRequestedDate(new Date());
							dmPortitem.setModifiedDate(new Date());
							dmPortitem.setSyncVersion("1|");
							DmPortLocalServiceUtil.updateDmPort(dmPortitem);

							DmHistoryPort dmHistoryPortitem = DmHistoryPortLocalServiceUtil
									.findByPortCodeAndSyncVersion(item.getPortCode(), syncVersion);
							dmHistoryPortitem.setIsDelete(1);
							dmHistoryPortitem.setMarkedAsDelete(1);
							// dmPortitem.setRequestedDate(new Date());
							dmHistoryPortitem.setModifiedDate(new Date());
							dmHistoryPortitem.setSyncVersion("1");
							historyPortSync = DmHistoryPortLocalServiceUtil.updateDmHistoryPort(dmHistoryPortitem);

						} else {
							log.info("messageType104 Them");
							DmPort dmPortitem = DmPortLocalServiceUtil.getByPortCode(item.getPortCode());

							if (dmPortitem != null) {
								dmPortitem.setPortCode(item.getPortCode());
								dmPortitem.setPortName(item.getPortName());
								dmPortitem.setIsDelete(0);
								dmPortitem.setMarkedAsDelete(0);
								// dmPortitem.setRequestedDate(new Date());
								dmPortitem.setModifiedDate(new Date());
								dmPortitem.setSyncVersion("1|");
								DmPortLocalServiceUtil.updateDmPort(dmPortitem);
							} else {
								details.setIsDelete(0);
								details.setMarkedAsDelete(0);
								details.setRequestedDate(new Date());
								details.setModifiedDate(new Date());
								details.setSyncVersion("1|");
								DmPortLocalServiceUtil.addDmPort(details);
							}

							DmHistoryPort dmHistoryPortitem = DmHistoryPortLocalServiceUtil
									.findByPortCodeAndSyncVersion(item.getPortCode(), syncVersion);

							if (dmHistoryPortitem != null) {
								dmHistoryPortitem.setPortCode(item.getPortCode());
								dmHistoryPortitem.setPortName(item.getPortName());
								dmHistoryPortitem.setIsDelete(0);
								dmHistoryPortitem.setMarkedAsDelete(0);
								// dmPortitem.setRequestedDate(new Date());
								dmHistoryPortitem.setModifiedDate(new Date());
								dmHistoryPortitem.setSyncVersion("1");
								historyPortSync = DmHistoryPortLocalServiceUtil
										.updateDmHistoryPort(dmHistoryPortitem);
							} else {
								history.setIsDelete(0);
								history.setMarkedAsDelete(0);
								history.setRequestedDate(new Date());
								history.setModifiedDate(new Date());
								history.setSyncVersion("1");
								historyPortSync = DmHistoryPortLocalServiceUtil.addDmHistoryPort(history);
							}
						}

					}
				}
				
				MessageSyncUtil.insertDMSycnQueueLegacy(MessageType.NSW, header, data, historyPortSync);
				
			}
		}
		
			
		//}

	} catch (Exception e) {
		log.error(e.getMessage());
	}

	// Ban tin tra ve dung.
	String xmlResult = businessUtils
			.createReturnContentAfterParserDataReceiverFromNSWInland(header);
	BusinessUtils.insertHistory(header, xmlResult,
			BusinessUtils.BoGiaoThongToHqmc,
			TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

	return xmlResult;

	
	}
	
	public void synchronizePortWharfList(int messageType, DmHistoryPortWharf history, String status, String maritimeCode){
		try{
			BusinessUtils businessUtils = new BusinessUtils();
			Header header = null;
			String xmlData = "";
			
			// for Sync NSW
			
			PortWharfList.PortWharf portwharfItem = new PortWharfList.PortWharf();
			
			portwharfItem.setPortWharfCode(history.getPortWharfCode());
			portwharfItem.setPortWharfName(history.getPortWharfName());
			portwharfItem.setPortHarbourCode(history.getPortHarbourCode());
			portwharfItem.setPortRegionCode(history.getPortRegionCode());
			portwharfItem.setStatus(status);
			
			PortWharfList details = new PortWharfList();
			details.getPortWharf().add(portwharfItem);
			
			
			header = BusinessUtils.createHeaderSynchronization(
					MessageType.NHAP_CANH, MessageType.FUNCTION_KHAI_BAO,
					messageType, maritimeCode);
			header.getReference().setVersion(history.getSyncVersion());
			
			
			xmlData = businessUtils
					.createContentSendFromBGTVTToNSWInland(
							header,
							MessageFactory
									.convertObjectToXml(details));
//			insertDMSycnQueueLegacy(header, xmlData, true);
			MessageSyncUtil.insertDMSycnQueueLegacy(maritimeCode, header, xmlData, history);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
		
	public void synchronizePortHarbourList(int messageType, DmHistoryPortHarbour history, String status, String maritimeCode){
		try{
			BusinessUtils businessUtils = new BusinessUtils();
			Header header = null;
			String xmlData = "";
			
			PortHarbourList.PortHarbour portharbourItem = new PortHarbourList.PortHarbour();
			
			
			portharbourItem.setPortHarbourCode(history.getPortHarbourCode());
			portharbourItem.setPortHarbourName(history.getPortHarbourName());			
			portharbourItem.setPortRegionCode(history.getPortRegion());
			portharbourItem.setStatus(status);
			
			PortHarbourList details = new PortHarbourList();
			details.getPortHarbour().add(portharbourItem);
			
			
			header = BusinessUtils.createHeaderSynchronization(
					MessageType.NHAP_CANH, MessageType.FUNCTION_KHAI_BAO,
					messageType, maritimeCode);
			header.getReference().setVersion(history.getSyncVersion());
			
			
			xmlData = businessUtils
					.createContentSendFromBGTVTToNSWInland(
							header,
							MessageFactory
									.convertObjectToXml(details));
//			insertDMSycnQueueLegacy(header, xmlData, true);
			MessageSyncUtil.insertDMSycnQueueLegacy(maritimeCode, header, xmlData, history);
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public void synchronizePortRegionList(int messageType, DmHistoryPortRegion history, String status, String maritimeCode){
		try{
			BusinessUtils businessUtils = new BusinessUtils();
			Header header = null;
			String xmlData = "";
			
			PortRegionList.PortRegion portregionItem = new PortRegionList.PortRegion();
			
			portregionItem.setPortRegionCode(history.getPortRegionCode());
			portregionItem.setReferencePortCode(history.getPortRegionCode());
			portregionItem.setPortRegionName(history.getPortRegionName());						
			portregionItem.setStatus(status);
			
			PortRegionList details = new PortRegionList();
			details.getPortRegion().add(portregionItem);
			
			
			header = BusinessUtils.createHeaderSynchronization(
					MessageType.NHAP_CANH, MessageType.FUNCTION_KHAI_BAO,
					messageType, maritimeCode);
			header.getReference().setVersion(history.getSyncVersion());
			
			
			xmlData = businessUtils
					.createContentSendFromBGTVTToNSWInland(
							header,
							MessageFactory
									.convertObjectToXml(details));
			MessageSyncUtil.insertDMSycnQueueLegacy(maritimeCode, header, xmlData, history);
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public void synchronizePortOfAuthorityList(int messageType, DmHistoryMaritime history, String status, String maritimeCode){
		try{
			BusinessUtils businessUtils = new BusinessUtils();
			Header header = null;
			String xmlData = "";
			
			PortOfAuthorityList.PortOfAuthority portOfAuthorityItem = new PortOfAuthorityList.PortOfAuthority();
			
			portOfAuthorityItem.setPortOfAuthorityCode(history.getMaritimeReference());			
			portOfAuthorityItem.setPortOfAuthorityName(history.getMaritimeNameVN());						
			portOfAuthorityItem.setStatus(status);
			
			PortOfAuthorityList details = new PortOfAuthorityList();
			details.getPortOfAuthority().add(portOfAuthorityItem);
			
			
			header = BusinessUtils.createHeaderSynchronization(
					MessageType.NHAP_CANH, MessageType.FUNCTION_KHAI_BAO,
					messageType, maritimeCode);
			header.getReference().setVersion(history.getSyncVersion());
			
			
			xmlData = businessUtils
					.createContentSendFromBGTVTToNSWInland(
							header,
							MessageFactory
									.convertObjectToXml(details));
//			insertDMSycnQueueLegacy(header, xmlData, true);
			MessageSyncUtil.insertDMSycnQueueLegacy(maritimeCode, header, xmlData, history);
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public void synchronizeReferencePortList(int messageType, DmDataitem history, String status, String maritimeCode){
		try{
			BusinessUtils businessUtils = new BusinessUtils();
			Header header = null;
			String xmlData = "";
			
			ReferencePortList.ReferencePort referencePortItem = new ReferencePortList.ReferencePort();
			
			
			referencePortItem.setReferencePortCode(history.getNode2());
			referencePortItem.setReferencePortName(history.getName());						
			referencePortItem.setStatus(status);
			
			ReferencePortList details = new ReferencePortList();
			details.getReferencePort().add(referencePortItem);
			
			
			header = BusinessUtils.createHeaderSynchronization(
					MessageType.NHAP_CANH, MessageType.FUNCTION_KHAI_BAO,
					messageType, maritimeCode);
			header.getReference().setVersion("1");
			
			
			xmlData = businessUtils
					.createContentSendFromBGTVTToNSWInland(
							header,
							MessageFactory
									.convertObjectToXml(details));
//			insertDMSycnQueueLegacy(header, xmlData, true);
			MessageSyncUtil.insertDMSycnQueueLegacy(maritimeCode, header, xmlData, history);
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Deprecated
	private static void insertDMSycnQueue(Header header, String xmlData, boolean sendNSW) {
		
		if(sendNSW) {
			// Insert Queue for NSW
			BusinessUtils.insertHistory(header, xmlData,
					BusinessUtils.BoGiaoThongToHqmc,
					TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID()
							.toString());
			SendMessgaeFunctions
					.insertMessageQueue(
							header,
							xmlData,
							"OUT",
							null,
							MessgaePriorityLevels.HIGHT);
		}
		
		// Insert Queue for CVHH Vung Tau
		BusinessUtils.insertHistory(header, xmlData,
				MessageType.REQUEST_DIRECTION_OUT_CVHH,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID()
						.toString());
		SendMessgaeFunctions
				.insertMessageQueue(
						header,
						xmlData,
						MessageType.REQUEST_DIRECTION_OUT_CVHH,
						null,
						MessgaePriorityLevels.HIGHT);
	}
}
