//package vn.gt.portlet.business;
//
//import java.util.List;
//
//import com.fds.nsw.nghiepvu.model.DmEnterrise;
//import com.fds.nsw.nghiepvu.model.DmEnterriseSoap;
//import com.fds.nsw.nghiepvu.model.DmHistoryEnterrise;
//import com.fds.nsw.nghiepvu.model.DmHistoryState;
//import com.fds.nsw.nghiepvu.model.DmState;
//import com.fds.nsw.nghiepvu.model.DmStateSoap;
//import com.fds.nsw.nghiepvu.model.DmSyncCategory;
//import com.fds.nsw.nghiepvu.model.HistoryRmdcShip;
//import com.fds.nsw.nghiepvu.model.HistorySyncVersion;
//import com.fds.nsw.nghiepvu.model.RmdcShip;
//
//
//
//
//
//
//
//import vn.gt.dongbodanhmuc.ResponseDanhMuc;
//
//public class BsDanhMuc {
//  todo Ko tìm thấy table
//	public boolean dongBoDanhMuc(List<ResponseDanhMuc> liResponseDanhMucs) {
//
//		return false;
//	}
//
//	public HistoryRmdcShip copyHistoryRmdcShip2RmdcShip(RmdcShip danhMuc){
//		HistoryRmdcShip rmdcShip = new HistoryRmdcShip();
//		rmdcShip.setShipId(danhMuc.getShipId());
//		rmdcShip.setShipName(danhMuc.getShipName());
//		rmdcShip.setShipTypeId(danhMuc.getShipTypeId());
//		rmdcShip.setShipTypeCode(danhMuc.getShipTypeCode());
//		rmdcShip.setImo(danhMuc.getImo());
//		rmdcShip.setCallSign(danhMuc.getCallSign());
//		rmdcShip.setStateId(danhMuc.getStateId());
//		rmdcShip.setStateCode(danhMuc.getStateCode());
//		rmdcShip.setRegistryNumber(danhMuc.getRegistryNumber());
//		rmdcShip.setRegistryDate(danhMuc.getRegistryDate());
//		rmdcShip.setRegistryPortId(danhMuc.getRegistryPortId());
//		rmdcShip.setRegistryPortCode(danhMuc.getRegistryPortCode());
//		rmdcShip.setGt(danhMuc.getGt());
//		rmdcShip.setNrt(danhMuc.getNrt());
//		rmdcShip.setDwt(danhMuc.getDwt());
//		rmdcShip.setLength(danhMuc.getLength());
//		rmdcShip.setWidth(danhMuc.getWidth());
//		rmdcShip.setHeight(danhMuc.getHeight());
//		rmdcShip.setSailingSpeed(danhMuc.getSailingSpeed());
//		rmdcShip.setColor(danhMuc.getColor());
//		rmdcShip.setBoneCode(danhMuc.getBoneCode());
//		rmdcShip.setMachineCode(danhMuc.getMachineCode());
//		rmdcShip.setShipAgencyId(danhMuc.getShipAgencyId());
//		rmdcShip.setShipAgencyCode(danhMuc.getShipAgencyCode());
//		rmdcShip.setFishingBoatRegistration(danhMuc.getFishingBoatRegistration());
//		rmdcShip.setRoleShip(danhMuc.getRoleShip());
//
//		return rmdcShip;
//	}
//
//
//	public RmdcShip copyRmdcShip2ResponseDanhMuc(ResponseDanhMuc danhMuc){
//		RmdcShip rmdcShip = new RmdcShip();
//
//		rmdcShip.setShipId(danhMuc.getShipId());
//		rmdcShip.setShipName(danhMuc.getShipName());
//		rmdcShip.setShipTypeId(danhMuc.getShipTypeId());
//		rmdcShip.setShipTypeCode(danhMuc.getShipTypeCode());
//		rmdcShip.setImo(danhMuc.getImo());
//		rmdcShip.setCallSign(danhMuc.getCallSign());
//		rmdcShip.setStateId(danhMuc.getStateId());
//		rmdcShip.setStateCode(danhMuc.getStateCode());
//		rmdcShip.setRegistryNumber(danhMuc.getRegistryNumber());
//		rmdcShip.setRegistryDate(danhMuc.getRegistryDate());
//		rmdcShip.setRegistryPortId(danhMuc.getRegistryPortId());
//		rmdcShip.setRegistryPortCode(danhMuc.getRegistryPortCode());
//		rmdcShip.setGt(danhMuc.getGt());
//		rmdcShip.setNrt(danhMuc.getNrt());
//		rmdcShip.setDwt(danhMuc.getDwt());
//		rmdcShip.setLength(danhMuc.getLength());
//		rmdcShip.setWidth(danhMuc.getWidth());
//		rmdcShip.setHeight(danhMuc.getHeight());
//		rmdcShip.setSailingSpeed(danhMuc.getSailingSpeed());
//		rmdcShip.setColor(danhMuc.getColor());
//		rmdcShip.setBoneCode(danhMuc.getBoneCode());
//		rmdcShip.setMachineCode(danhMuc.getMachineCode());
//		rmdcShip.setShipAgencyId(danhMuc.getShipAgencyId());
//		rmdcShip.setShipAgencyCode(danhMuc.getShipAgencyCode());
//		rmdcShip.setFishingBoatRegistration(danhMuc.getFishingBoatRegistration());
//		rmdcShip.setRoleShip(danhMuc.getRole());
//
//
//		return rmdcShip;
//	}
//
//
//
//
//	public HistoryRmdcShip copyHistoryRmdcShip2ResponseDanhMuc(ResponseDanhMuc danhMuc){
//		HistoryRmdcShip rmdcShip = new HistoryRmdcShip();
//		rmdcShip.setShipId(danhMuc.getShipId());
//		rmdcShip.setShipName(danhMuc.getShipName());
//		rmdcShip.setShipTypeId(danhMuc.getShipTypeId());
//		rmdcShip.setShipTypeCode(danhMuc.getShipTypeCode());
//		rmdcShip.setImo(danhMuc.getImo());
//		rmdcShip.setCallSign(danhMuc.getCallSign());
//		rmdcShip.setStateId(danhMuc.getStateId());
//		rmdcShip.setStateCode(danhMuc.getStateCode());
//		rmdcShip.setRegistryNumber(danhMuc.getRegistryNumber());
//		rmdcShip.setRegistryDate(danhMuc.getRegistryDate());
//		rmdcShip.setRegistryPortId(danhMuc.getRegistryPortId());
//		rmdcShip.setRegistryPortCode(danhMuc.getRegistryPortCode());
//		rmdcShip.setGt(danhMuc.getGt());
//		rmdcShip.setNrt(danhMuc.getNrt());
//		rmdcShip.setDwt(danhMuc.getDwt());
//		rmdcShip.setLength(danhMuc.getLength());
//		rmdcShip.setWidth(danhMuc.getWidth());
//		rmdcShip.setHeight(danhMuc.getHeight());
//		rmdcShip.setSailingSpeed(danhMuc.getSailingSpeed());
//		rmdcShip.setColor(danhMuc.getColor());
//		rmdcShip.setBoneCode(danhMuc.getBoneCode());
//		rmdcShip.setMachineCode(danhMuc.getMachineCode());
//		rmdcShip.setShipAgencyId(danhMuc.getShipAgencyId());
//		rmdcShip.setShipAgencyCode(danhMuc.getShipAgencyCode());
//		rmdcShip.setFishingBoatRegistration(danhMuc.getFishingBoatRegistration());
//		rmdcShip.setRoleShip(danhMuc.getRole());
//
//		return rmdcShip;
//	}
//
//	public HistorySyncVersion copyHistorySyncVersion2DmSyncCategory(DmSyncCategory dmSyncCategory){
//		HistorySyncVersion historySyncVersion = new HistorySyncVersion();
//		historySyncVersion.setCategoryID(dmSyncCategory.getCategoryId());
//		historySyncVersion.setRequestedDate(dmSyncCategory.getModifiedDate());
////		historySyncVersion.setSyncUnit(dmSyncCategory.get)
//		//historySyncVersion.setSyncVersion(syncVersion)
//
//		return historySyncVersion;
//	}
//	public int genVersion(String syncVersion){
//	    if (syncVersion == null || syncVersion.trim().length() == 0) {
//	     return 1;
//	    } else {
//	     String data = syncVersion.trim().toLowerCase();
//	     data = data.substring(0, data.lastIndexOf(Constans.SYNC_VERSION_SEPERATE));
//	     String lastValue = data.substring(data.lastIndexOf(Constans.SYNC_VERSION_SEPERATE) + 1, data.length());
//	     int value = 0;
//	     try {
//	      value = Integer.parseInt(lastValue);
//	     } catch (Exception es) {
//	     }
//	     return value + 1;
//	    }
//	 }
//
//	// Begin DmState
//
//	public DmHistoryState copyDmHistoryState2DmState(DmState dmState){
//		DmHistoryState dmHistoryState = new DmHistoryState();
//		dmHistoryState.setId(dmState.getId());
//		dmHistoryState.setStateCode(dmState.getStateCode());
//		dmHistoryState.setStateName(dmState.getStateName());
//		dmHistoryState.setDescription(dmState.getDescription());
//		dmHistoryState.setIsDelete(dmState.getIsDelete());
//		dmHistoryState.setMarkedAsDelete(dmState.getMarkedAsDelete());
//		dmHistoryState.setModifiedDate(dmState.getModifiedDate());
//		dmHistoryState.setRequestedDate(dmState.getRequestedDate());
//		dmHistoryState.setSyncVersion(dmState.getSyncVersion());
//
//		return dmHistoryState;
//	}
//
//
//	public DmState copyDmState22DmStateSoap(DmStateSoap dmStateSoap){
//		DmState dmState = new DmState();
//		dmState.setId(dmStateSoap.getId());
//		dmState.setStateCode(dmStateSoap.getStateCode());
//		dmState.setStateName(dmStateSoap.getStateName());
//		dmState.setDescription(dmStateSoap.getDescription());
//		dmState.setIsDelete(dmStateSoap.getIsDelete());
//		dmState.setMarkedAsDelete(dmStateSoap.getMarkedAsDelete());
//		dmState.setModifiedDate(dmStateSoap.getModifiedDate());
//		dmState.setRequestedDate(dmStateSoap.getRequestedDate());
//		dmState.setSyncVersion(dmStateSoap.getSyncVersion());
//
//		return dmState;
//	}
//
//
//	// End DmState
//
//
//
//
//	// Begin DmEnterrise
//
//	public DmHistoryEnterrise copyDmHistoryEnterrise2DmEnterrise(DmEnterrise  dmEnterriseSoap){
//		DmHistoryEnterrise dmEnterrise = new DmHistoryEnterrise();
//		dmEnterrise.setId(dmEnterriseSoap.getId());
//		dmEnterrise.setEnterpriseCode(dmEnterriseSoap.getEnterpriseCode());
//		dmEnterrise.setEnterpriseTaxCode(dmEnterriseSoap.getEnterpriseTaxCode());
//		dmEnterrise.setEnterpriseName(dmEnterriseSoap.getEnterpriseName());
//		dmEnterrise.setEnterpriseShortName(dmEnterriseSoap.getEnterpriseShortName());
//		dmEnterrise.setEnterpriseForeignName(dmEnterriseSoap.getEnterpriseForeignName());
//		dmEnterrise.setEnterprisePerson(dmEnterriseSoap.getEnterprisePerson());
//		dmEnterrise.setEnterpriseHOAddress(dmEnterriseSoap.getEnterpriseHOAddress());
//		dmEnterrise.setStateCode(dmEnterriseSoap.getStateCode());
//		dmEnterrise.setCityCode(dmEnterriseSoap.getCityCode());
//		dmEnterrise.setDistrictCode(dmEnterriseSoap.getDistrictCode());
//		dmEnterrise.setWardCode(dmEnterriseSoap.getWardCode());
//		dmEnterrise.setTelephoneNo(dmEnterriseSoap.getTelephoneNo());
//		dmEnterrise.setRegistrationCode(dmEnterriseSoap.getRegistrationCode());
//		dmEnterrise.setRegistrationAddress(dmEnterriseSoap.getRegistrationAddress());
//		dmEnterrise.setRegistrationDate(dmEnterriseSoap.getRegistrationDate());
//		dmEnterrise.setIsDelete(dmEnterriseSoap.getIsDelete());
//		dmEnterrise.setMarkedAsDelete(dmEnterriseSoap.getMarkedAsDelete());
//		dmEnterrise.setModifiedDate(dmEnterriseSoap.getModifiedDate());
//		dmEnterrise.setRequestedDate(dmEnterriseSoap.getRequestedDate());
//		dmEnterrise.setSyncVersion(dmEnterriseSoap.getSyncVersion());
//		dmEnterrise.setModifiedUser(dmEnterriseSoap.getModifiedUser());
//
//
//		return dmEnterrise;
//	}
//
//
//	public DmEnterrise copyDmEnterrise2DmEnterriseSoap(DmEnterriseSoap dmEnterriseSoap){
//		DmEnterrise dmEnterrise = new DmEnterrise();
//		dmEnterrise.setId(dmEnterriseSoap.getId());
//		dmEnterrise.setEnterpriseCode(dmEnterriseSoap.getEnterpriseCode());
//		dmEnterrise.setEnterpriseTaxCode(dmEnterriseSoap.getEnterpriseTaxCode());
//		dmEnterrise.setEnterpriseName(dmEnterriseSoap.getEnterpriseName());
//		dmEnterrise.setEnterpriseShortName(dmEnterriseSoap.getEnterpriseShortName());
//		dmEnterrise.setEnterpriseForeignName(dmEnterriseSoap.getEnterpriseForeignName());
//		dmEnterrise.setEnterprisePerson(dmEnterriseSoap.getEnterprisePerson());
//		dmEnterrise.setEnterpriseHOAddress(dmEnterriseSoap.getEnterpriseHOAddress());
//		dmEnterrise.setStateCode(dmEnterriseSoap.getStateCode());
//		dmEnterrise.setCityCode(dmEnterriseSoap.getCityCode());
//		dmEnterrise.setDistrictCode(dmEnterriseSoap.getDistrictCode());
//		dmEnterrise.setWardCode(dmEnterriseSoap.getWardCode());
//		dmEnterrise.setTelephoneNo(dmEnterriseSoap.getTelephoneNo());
//		dmEnterrise.setRegistrationCode(dmEnterriseSoap.getRegistrationCode());
//		dmEnterrise.setRegistrationAddress(dmEnterriseSoap.getRegistrationAddress());
//		dmEnterrise.setRegistrationDate(dmEnterriseSoap.getRegistrationDate());
//		dmEnterrise.setIsDelete(dmEnterriseSoap.getIsDelete());
//		dmEnterrise.setMarkedAsDelete(dmEnterriseSoap.getMarkedAsDelete());
//		dmEnterrise.setModifiedDate(dmEnterriseSoap.getModifiedDate());
//		dmEnterrise.setRequestedDate(dmEnterriseSoap.getRequestedDate());
//		dmEnterrise.setSyncVersion(dmEnterriseSoap.getSyncVersion());
//		dmEnterrise.setModifiedUser(dmEnterriseSoap.getModifiedUser());
//
//
//		return dmEnterrise;
//	}
//
//
//	// End DmEnterrise
//
//}
