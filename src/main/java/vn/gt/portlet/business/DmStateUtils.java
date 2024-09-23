package vn.gt.portlet.business;

public class DmStateUtils {

//	public List<ResponseDanhMuc> syncDmState(List<DmStateSoap> dmStateSoaps,
//			DmSyncCategory dmSyncCategory) {
//		List<ResponseDanhMuc> result = new ArrayList<ResponseDanhMuc>();
//		
//		try {
//			String syncVersion = null;
//
//			BsDanhMuc danhMucBs = new BsDanhMuc();
//
//			if (dmStateSoaps != null && dmStateSoaps.size() > 0) {
//				int lastIdVersion = HistorySyncVersionLocalServiceUtil
//						.maxHistorySyncVersion();
//				String olderVerSion = null;
//				
//				HistorySyncVersion historySyncVersion = HistorySyncVersionLocalServiceUtil
//						.fetchHistorySyncVersion(lastIdVersion);
//
//				int syncVersionInt = 0;
//				if(historySyncVersion != null){
//					syncVersionInt =  danhMucBs.genVersion(historySyncVersion
//						.getSyncVersion());
//					olderVerSion = historySyncVersion.getSyncVersion();
//				}
//				if(syncVersionInt==0){
//					syncVersion = 1 + Constans.SYNC_VERSION_SEPERATE;
//				}else{
//					syncVersion = olderVerSion
//							+ syncVersionInt + Constans.SYNC_VERSION_SEPERATE;
//				}
//
//				HistorySyncVersion historySyncVersion2 = danhMucBs
//						.copyHistorySyncVersion2DmSyncCategory(dmSyncCategory);
//				historySyncVersion2.setSyncVersion(syncVersion);
//				historySyncVersion2.setLatestValueDate(dmSyncCategory.getModifiedDate());
//				HistorySyncVersionLocalServiceUtil
//						.addHistorySyncVersion(historySyncVersion2);
//
//				// / Chua ghi log: bao nhieu records tuong dung danh muc nao
//				// / Chua ghi log: bao nhieu records tuong dung danh muc nao
//				// / Chua ghi log: bao nhieu records tuong dung danh muc nao
//				// / Chua ghi log: bao nhieu records tuong dung danh muc nao
//			} else {
//				return null;
//			}
//
//			
//			List<RmdcShip> olderRmdcShips = RmdcShipLocalServiceUtil.findAll();
//			for (int i = 0; i < dmStateSoaps.size(); i++) {
//				DmStateSoap danhMuc = dmStateSoaps.get(i);
//				RmdcShip rmdcShip = null;
//				List<RmdcShip> rmdcShips = RmdcShipLocalServiceUtil
//						.findByShipId(danhMuc.getShipId());
//				if (rmdcShips != null && rmdcShips.size() > 0) {
//					rmdcShip = rmdcShips.get(0);
//				}
//				boolean check = false;
//				
//
//				if (null != rmdcShip) {
//
//					RmdcShip ship = danhMucBs
//							.copyRmdcShip2ResponseDanhMuc(danhMuc);
//					ship.setSyncVersion(syncVersion);
//					ship.setId(rmdcShip.getId());
//					ship = RmdcShipLocalServiceUtil.updateRmdcShip(ship);
//					if (null != ship) {
//						check = false;
//					} else {
//						check = true;
//					}
//
//				} else {
//
//					RmdcShip ship = danhMucBs
//							.copyRmdcShip2ResponseDanhMuc(danhMuc);
//					ship.setSyncVersion(syncVersion);
//					ship = RmdcShipLocalServiceUtil.addRmdcShip(ship);
//					if (null != ship) {
//						check = false;
//					} else {
//						check = true;
//					}
//					
//				}
//			
//				if (!check) {
//					result.add(danhMuc);
//				} 
//				for (int j = 0; j < olderRmdcShips.size(); j++) {
//					HistoryRmdcShip historyRmdcShip = danhMucBs
//							.copyHistoryRmdcShip2RmdcShip(olderRmdcShips.get(j));
//					historyRmdcShip.setSyncVersion(syncVersion);
//					HistoryRmdcShipLocalServiceUtil
//					.addHistoryRmdcShip(historyRmdcShip);
//				}
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}


}
