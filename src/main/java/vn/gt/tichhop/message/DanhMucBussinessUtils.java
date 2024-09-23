package vn.gt.tichhop.message;

import java.util.List;



import com.fds.flex.common.ultility.GetterUtil;

import com.fds.nsw.nghiepvu.model.DmPort;











import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmGtRouteConfig;
import vn.gt.dao.danhmucgt.service.DmGtRouteConfigLocalServiceUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour;
import com.fds.nsw.nghiepvu.model.DmHistoryPort;
import com.fds.nsw.nghiepvu.model.DmHistoryPortRegion;
import com.fds.nsw.nghiepvu.model.DmHistoryPortWharf;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import com.fds.nsw.nghiepvu.model.DmPort;

import lombok.extern.slf4j.Slf4j;
import vn.nsw.model.*;

@Slf4j
public class DanhMucBussinessUtils {

	
	
	public static void updateDmDataItem(Header header, DmDataItemList.DmDataitem obj) {
		try {
			List<com.fds.nsw.nghiepvu.model.DmDataitem> items = DmDataItemLocalServiceUtil.findByDataGroupIdAndShortName(GetterUtil.getLong(obj.getDataGroupId()), obj.getShortName());
			
			com.fds.nsw.nghiepvu.model.DmDataitem item = null;
			if (items != null && items.size() > 0) {
				item = items.get(0);
			}
			
			if(item == null) {
				item = new DmDataitem();
				
				item.setDatagroupid(Integer.parseInt(obj.getDataGroupId()));
				item.setCode(obj.getCode0());
				item.setNode1(obj.getNode1());
				item.setNode2(obj.getNode2());
				item.setNode3(obj.getNode3());
				item.setNode4(obj.getNode4());
				item.setLevel(obj.getLevel());
				item.setName(obj.getName());
				item.setDescription(obj.getDescription());
				item.setValidatedfrom(FormatData.parseStringToDate(obj.getValidatedFrom()));
				item.setValidatedto(FormatData.parseStringToDate(obj.getValidatedTo()));
				item.setStatus(obj.getStatus());
				item.setOrder(obj.getOrder());
				item.setShortName(obj.getShortName());
				
				DmDataItemLocalServiceUtil.addDmDataitem(item);
			} else {
				
				item.setDatagroupid(Integer.parseInt(obj.getDataGroupId()));
				item.setCode(obj.getCode0());
				item.setNode1(obj.getNode1());
				item.setNode2(obj.getNode2());
				item.setNode3(obj.getNode3());
				item.setNode4(obj.getNode4());
				item.setLevel(obj.getLevel());
				item.setName(obj.getName());
				item.setDescription(obj.getDescription());
				item.setValidatedfrom(FormatData.parseStringToDate(obj.getValidatedFrom()));
				item.setValidatedto(FormatData.parseStringToDate(obj.getValidatedTo()));
				item.setStatus(obj.getStatus());
				item.setOrder(obj.getOrder());
				item.setShortName(obj.getShortName());
				
				DmDataItemLocalServiceUtil.updateDmDataItem(item);
			}
			
			// Check MtGateway
			List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByLocCode(MessageType.NSW);
			if(routeConfigs != null && routeConfigs.size() > 0) {
				int messageType = MessageType.DONG_BO_DM_CANG_BIEN_HANG_HAI;
				
				Header toHeader = BusinessUtils.createHeaderSynchronization(
						MessageType.NHAP_CANH, MessageType.FUNCTION_KHAI_BAO,
						messageType, header.getFrom().getIdentity());
				header.getReference().setVersion("1");
				
				MessageSyncUtil.insertDMSycnQueueLegacy(header.getFrom().getIdentity(), toHeader, null, item);
			}
			
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static void updateDmPortRegion(Header header, DmPortRegionList.DmPortRegion obj){
		try {
			com.fds.nsw.nghiepvu.model.DmPortRegion item = DmPortRegionLocalServiceUtil.getByPortRegionCode(obj.getPortRegionCode());
			
			if(item == null) {
				item = new DmPortRegion();
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setPortRegionName(obj.getPortRegionName());
				item.setPortRegionNameVN(obj.getPortRegionNameVN());
				item.setPortRegionRef(obj.getPortRegionRef());
				item.setPortCode(obj.getPortCode());
				item.setPortCodeRef(obj.getPortCodeRef());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				DmPortRegionLocalServiceUtil.addDmPortRegion(item);
			} else {
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setPortRegionName(obj.getPortRegionName());
				item.setPortRegionNameVN(obj.getPortRegionNameVN());
				item.setPortRegionRef(obj.getPortRegionRef());
				item.setPortCode(obj.getPortCode());
				item.setPortCodeRef(obj.getPortCodeRef());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmPortRegionLocalServiceUtil.updateDmPortRegion(item);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static void updateDmHistoryPortRegion(Header header, DmHistoryPortRegionList.DmHistoryPortRegion obj){
		try {
			com.fds.nsw.nghiepvu.model.DmHistoryPortRegion item = DmHistoryPortRegionLocalServiceUtil.findByPortRegionCodeAndSyncVersion(obj.getPortRegionCode(), obj.getSyncVersion());
			
			if(item == null) {
				item = new DmHistoryPortRegion();
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setPortRegionName(obj.getPortRegionName());
				item.setPortRegionNameVN(obj.getPortRegionNameVN());
				item.setPortRegionRef(obj.getPortRegionRef());
				item.setPortCode(obj.getPortCode());
				item.setPortCodeRef(obj.getPortCodeRef());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmHistoryPortRegionLocalServiceUtil.addDmHistoryPortRegion(item);
			} else {
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setPortRegionName(obj.getPortRegionName());
				item.setPortRegionNameVN(obj.getPortRegionNameVN());
				item.setPortRegionRef(obj.getPortRegionRef());
				item.setPortCode(obj.getPortCode());
				item.setPortCodeRef(obj.getPortCodeRef());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmHistoryPortRegionLocalServiceUtil.updateDmHistoryPortRegion(item);
			}
			
			// Check MtGateway
			List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByLocCode(MessageType.NSW);
			if(routeConfigs != null && routeConfigs.size() > 0) {
				int messageType = MessageType.DONG_BO_DM_KHU_VUC_CANG;
				
				Header toHeader = BusinessUtils.createHeaderSynchronization(
						MessageType.NHAP_CANH, MessageType.FUNCTION_KHAI_BAO,
						messageType, header.getFrom().getIdentity());
				header.getReference().setVersion("1");
				
				MessageSyncUtil.insertDMSycnQueueLegacy(header.getFrom().getIdentity(), toHeader, null, item);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static void updateDmPortWharf(Header header, DmPortWharfList.DmPortWharf obj) {
		try {
			com.fds.nsw.nghiepvu.model.DmPortWharf item = DmPortWharfLocalServiceUtil.getByPortWharfCode(obj.getPortWharfCode());
			
			if(item == null) {
				item = new DmPortWharf();
				
				item.setPortWharfCode(obj.getPortWharfCode());
				item.setPortWharfName(obj.getPortWharfName());
				item.setPortWharfNameVN(obj.getPortWharfNameVN());
				item.setPortWharfType(GetterUtil.getInteger(obj.getPortWharfType()));
				item.setPortCode(obj.getPortCode());
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setPortHarbourCode(obj.getPortHarbourCode());
				item.setNote(obj.getNote());
				
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmPortWharfLocalServiceUtil.addDmPortWharf(item);
			} else {
				item.setPortWharfCode(obj.getPortWharfCode());
				item.setPortWharfName(obj.getPortWharfName());
				item.setPortWharfNameVN(obj.getPortWharfNameVN());
				item.setPortWharfType(GetterUtil.getInteger(obj.getPortWharfType()));
				item.setPortCode(obj.getPortCode());
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setPortHarbourCode(obj.getPortHarbourCode());
				item.setNote(obj.getNote());
				
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmPortWharfLocalServiceUtil.updateDmPortWharf(item);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}	
	}
	
	public static void updateDmHistoryPortWharf(Header header, DmHistoryPortWharfList.DmHistoryPortWharf obj) {
		try {
			com.fds.nsw.nghiepvu.model.DmHistoryPortWharf item = DmHistoryPortWharfLocalServiceUtil.findByPortWharfCodeAndSyncVersion(obj.getPortWharfCode(), obj.getSyncVersion());
			
			if(item == null) {
				item = new DmHistoryPortWharf();
				
				item.setPortWharfCode(obj.getPortWharfCode());
				item.setPortWharfName(obj.getPortWharfName());
				item.setPortWharfNameVN(obj.getPortWharfNameVN());
				item.setPortWharfType(GetterUtil.getInteger(obj.getPortWharfType()));
				item.setPortCode(obj.getPortCode());
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setPortHarbourCode(obj.getPortHarbourCode());
				item.setNote(obj.getNote());
				
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmHistoryPortWharfLocalServiceUtil.addDmHistoryPortWharf(item);
			} else {
				item.setPortWharfCode(obj.getPortWharfCode());
				item.setPortWharfName(obj.getPortWharfName());
				item.setPortWharfNameVN(obj.getPortWharfNameVN());
				item.setPortWharfType(GetterUtil.getInteger(obj.getPortWharfType()));
				item.setPortCode(obj.getPortCode());
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setPortHarbourCode(obj.getPortHarbourCode());
				item.setNote(obj.getNote());
				
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmHistoryPortWharfLocalServiceUtil.updateDmHistoryPortWharf(item);
			}
			
			// Check MtGateway
			List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByLocCode(MessageType.NSW);
			if(routeConfigs != null && routeConfigs.size() > 0) {
				int messageType = MessageType.DONG_BO_DM_CAU_CANG;
				
				Header toHeader = BusinessUtils.createHeaderSynchronization(
						MessageType.NHAP_CANH, MessageType.FUNCTION_KHAI_BAO,
						messageType, header.getFrom().getIdentity());
				header.getReference().setVersion("1");
				
				MessageSyncUtil.insertDMSycnQueueLegacy(header.getFrom().getIdentity(), toHeader, null, item);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}	
	}
	
	public static void updateDmPortHarbour(Header header, DmPortHarbourList.DmPortHarbour obj) {
		try {
			com.fds.nsw.nghiepvu.model.DmPortHarbour item = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(obj.getPortHarbourCode());
			
			if(item == null) {
				item = new DmPortHarbour();
				
				item.setPortHarbourCode(obj.getPortHarbourCode());
				item.setPortHarbourName(obj.getPortHarbourName());
				item.setPortHarbourNameVN(obj.getPortHarbourNameVN());
				item.setPortHarbourType(GetterUtil.getInteger(obj.getPortHarbourType()));
				item.setPortRegion(obj.getPortRegion());
				item.setPortCode(obj.getPortCode());
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setNote(obj.getNote());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmPortHarbourLocalServiceUtil.addDmPortHarbour(item);
			} else {
				
				item.setPortHarbourCode(obj.getPortHarbourCode());
				item.setPortHarbourName(obj.getPortHarbourName());
				item.setPortHarbourNameVN(obj.getPortHarbourNameVN());
				item.setPortHarbourType(GetterUtil.getInteger(obj.getPortHarbourType()));
				item.setPortRegion(obj.getPortRegion());
				item.setPortCode(obj.getPortCode());
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setNote(obj.getNote());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmPortHarbourLocalServiceUtil.updateDmPortHarbour(item);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static void updateDmHistoryPortHarbour(Header header, DmHistoryPortHarbourList.DmHistoryPortHarbour obj) {
		try {
			com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour item = DmHistoryPortHarbourLocalServiceUtil.findByPortHarbourCodeAndSyncVersion(obj.getPortHarbourCode(), obj.getSyncVersion());
			
			if(item == null) {
				item = new DmHistoryPortHarbour();
				
				item.setPortHarbourCode(obj.getPortHarbourCode());
				item.setPortHarbourName(obj.getPortHarbourName());
				item.setPortHarbourNameVN(obj.getPortHarbourNameVN());
				item.setPortHarbourType(GetterUtil.getInteger(obj.getPortHarbourType()));
				item.setPortRegion(obj.getPortRegion());
				item.setPortCode(obj.getPortCode());
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setNote(obj.getNote());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmHistoryPortHarbourLocalServiceUtil.addDmHistoryPortHarbour(item);
			} else {
				
				item.setPortHarbourCode(obj.getPortHarbourCode());
				item.setPortHarbourName(obj.getPortHarbourName());
				item.setPortHarbourNameVN(obj.getPortHarbourNameVN());
				item.setPortHarbourType(GetterUtil.getInteger(obj.getPortHarbourType()));
				item.setPortRegion(obj.getPortRegion());
				item.setPortCode(obj.getPortCode());
				item.setPortRegionCode(obj.getPortRegionCode());
				item.setNote(obj.getNote());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmHistoryPortHarbourLocalServiceUtil.updateDmHistoryPortHarbour(item);
			}
			
			// Check MtGateway
			List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByLocCode(MessageType.NSW);
			if(routeConfigs != null && routeConfigs.size() > 0) {
				int messageType = MessageType.DONG_BO_DM_BEN_CANG;
				
				Header toHeader = BusinessUtils.createHeaderSynchronization(
						MessageType.NHAP_CANH, MessageType.FUNCTION_KHAI_BAO,
						messageType, header.getFrom().getIdentity());
				header.getReference().setVersion("1");
				
				MessageSyncUtil.insertDMSycnQueueLegacy(header.getFrom().getIdentity(), toHeader, null, item);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static void updateDmMaritime(Header header, DmMaritimeList.DmMaritime obj) {
		try {
			com.fds.nsw.nghiepvu.model.DmMaritime item = DmMaritimeLocalServiceUtil.getByMaritimeCode(obj.getMaritimeCode());
			
			if(item == null) {
				item = new DmMaritime();
				
				item.setMaritimeCode(obj.getMaritimeCode());
				item.setMaritimeReference(obj.getMaritimeReference());
				item.setMaritimeOrder(GetterUtil.getInteger(obj.getMaritimeOrder()));
				item.setMaritimeName(obj.getMaritimeName());
				item.setMaritimeNameVN(obj.getMaritimeNameVN());
				item.setAddress(obj.getAddress());
				item.setAddressVN(obj.getAddressVN());
				item.setStateCode(obj.getStateCode());
				item.setCityCode(obj.getCityCode());
				
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmMaritimeLocalServiceUtil.addDmMaritime(item);
			} else {
				
				item.setMaritimeCode(obj.getMaritimeCode());
				item.setMaritimeReference(obj.getMaritimeReference());
				item.setMaritimeOrder(GetterUtil.getInteger(obj.getMaritimeOrder()));
				item.setMaritimeName(obj.getMaritimeName());
				item.setMaritimeNameVN(obj.getMaritimeNameVN());
				item.setAddress(obj.getAddress());
				item.setAddressVN(obj.getAddressVN());
				item.setStateCode(obj.getStateCode());
				item.setCityCode(obj.getCityCode());
				
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmMaritimeLocalServiceUtil.updateDmMaritime(item);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static void updateDmHistoryMaritime(Header header, DmHistoryMaritimeList.DmHistoryMaritime obj) {
		try {
			com.fds.nsw.nghiepvu.model.DmHistoryMaritime item = DmHistoryMaritimeLocalServiceUtil.getByMaritimeCodeAndSyncVersion(obj.getMaritimeCode(), obj.getSyncVersion());
			
			if(item == null) {
				item = new DmHistoryMaritime();
				
				item.setMaritimeCode(obj.getMaritimeCode());
				item.setMaritimeReference(obj.getMaritimeReference());
				item.setMaritimeOrder(GetterUtil.getInteger(obj.getMaritimeOrder()));
				item.setMaritimeName(obj.getMaritimeName());
				item.setMaritimeNameVN(obj.getMaritimeNameVN());
				item.setAddress(obj.getAddress());
				item.setAddressVN(obj.getAddressVN());
				item.setStateCode(obj.getStateCode());
				item.setCityCode(obj.getCityCode());
				
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmHistoryMaritimeLocalServiceUtil.addDmHistoryMaritime(item);
			} else {
				
				item.setMaritimeCode(obj.getMaritimeCode());
				item.setMaritimeReference(obj.getMaritimeReference());
				item.setMaritimeOrder(GetterUtil.getInteger(obj.getMaritimeOrder()));
				item.setMaritimeName(obj.getMaritimeName());
				item.setMaritimeNameVN(obj.getMaritimeNameVN());
				item.setAddress(obj.getAddress());
				item.setAddressVN(obj.getAddressVN());
				item.setStateCode(obj.getStateCode());
				item.setCityCode(obj.getCityCode());
				
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmHistoryMaritimeLocalServiceUtil.updateDmHistoryMaritime(item);
			}
			
			// Check MtGateway
			List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByLocCode(MessageType.NSW);
			if(routeConfigs != null && routeConfigs.size() > 0) {
				int messageType = MessageType.DONG_BO_DM_CANG_VU_HANG_HAI;
				
				Header toHeader = BusinessUtils.createHeaderSynchronization(
						MessageType.NHAP_CANH, MessageType.FUNCTION_KHAI_BAO,
						messageType, header.getFrom().getIdentity());
				header.getReference().setVersion("1");
				
				MessageSyncUtil.insertDMSycnQueueLegacy(header.getFrom().getIdentity(), toHeader, null, item);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}

	public static void updateDmPort(Header header, PortList.Port obj) {
		try {
			DmPort item = DmPortLocalServiceUtil.getByPortCode(obj.getPortCode());
			if(item == null) {
				item = new DmPort();
				item.setPortCode(obj.getPortCode());
				item.setLoCode(obj.getLoCode());
				item.setPortName(obj.getPortName());
				item.setFullName(obj.getFullName());
				item.setFullNameVN(obj.getFullNameVN());
				item.setPortType(obj.getPortType());
				item.setPortOrder(GetterUtil.getInteger(obj.getPortOrder()));
				item.setAddress(obj.getAddress());
				item.setAddressVN(obj.getAddressVN());
				item.setStateCode(obj.getStateCode());
				item.setCityCode(obj.getCityCode());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmPortLocalServiceUtil.addDmPort(item);
			} else if(item != null) {
				item.setLoCode(obj.getLoCode());
				item.setPortName(obj.getPortName());
				item.setFullName(obj.getFullName());
				item.setFullNameVN(obj.getFullNameVN());
				item.setPortType(obj.getPortType());
				item.setPortOrder(GetterUtil.getInteger(obj.getPortOrder()));
				item.setAddress(obj.getAddress());
				item.setAddressVN(obj.getAddressVN());
				item.setStateCode(obj.getStateCode());
				item.setCityCode(obj.getCityCode());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmPortLocalServiceUtil.updateDmPort(item);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static void updateDmHistoryPort(Header header, DmHistoryPortList.DmHistoryPort obj) {
		try {
			com.fds.nsw.nghiepvu.model.DmHistoryPort item = DmHistoryPortLocalServiceUtil.findByPortCodeAndSyncVersion(obj.getPortCode(), obj.getSyncVersion());
			if(item == null) {
				item = new DmHistoryPort();
				item.setPortCode(obj.getPortCode());
				item.setLoCode(obj.getLoCode());
				item.setPortName(obj.getPortName());
				item.setFullName(obj.getFullName());
				item.setFullNameVN(obj.getFullNameVN());
				item.setPortType(obj.getPortType());
				item.setPortOrder(GetterUtil.getInteger(obj.getPortOrder()));
				item.setAddress(obj.getAddress());
				item.setAddressVN(obj.getAddressVN());
				item.setStateCode(obj.getStateCode());
				item.setCityCode(obj.getCityCode());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				item.setSyncVersion(obj.getSyncVersion());
				
				DmHistoryPortLocalServiceUtil.addDmHistoryPort(item);
			} else {
				item.setLoCode(obj.getLoCode());
				item.setPortName(obj.getPortName());
				item.setFullName(obj.getFullName());
				item.setFullNameVN(obj.getFullNameVN());
				item.setPortType(obj.getPortType());
				item.setPortOrder(GetterUtil.getInteger(obj.getPortOrder()));
				item.setAddress(obj.getAddress());
				item.setAddressVN(obj.getAddressVN());
				item.setStateCode(obj.getStateCode());
				item.setCityCode(obj.getCityCode());
				item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
				item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
				item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
				item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
				
				DmHistoryPortLocalServiceUtil.updateDmHistoryPort(item);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
}