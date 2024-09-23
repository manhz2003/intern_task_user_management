package vn.gt.tichhop.message.haiquan2giaothong;



import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems;
import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;
import com.fds.nsw.nghiepvu.model.TempDocument;


import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.noticeandmessage.service.TempDangerousGoodsItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDangerousGoodsNanifestLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.DangerousGoodsManifest;
import vn.nsw.model.DangerousGoodsManifest.ListDangerousGoods.DangerousGoodsItem;



@Slf4j
public class DangerousGoodsManifest2Temp {

	
	
	public boolean insert2Temp(DangerousGoodsManifest dangerousGoodsManifest,Header header) throws Exception {
	try{
		
		insert2TempDocument(dangerousGoodsManifest, header);

		TempDangerousGoodsManifest object = new TempDangerousGoodsManifest(); 
		
		object.setDocumentName(FormatData.convertToLong(dangerousGoodsManifest.getDocumentName()));
		object.setDocumentYear(FormatData.convertToInt(dangerousGoodsManifest.getDocumentYear()));
		object.setUserCreated(dangerousGoodsManifest.getUserCreated());
		object.setNameOfShip(dangerousGoodsManifest.getNameOfShip());
		object.setFlagStateOfShip(dangerousGoodsManifest.getFlagStateOfShip());
		object.setMasterName(dangerousGoodsManifest.getMasterName());
		object.setImoNumber(dangerousGoodsManifest.getIMONumber());
		object.setCallsign(dangerousGoodsManifest.getCallsign());
		object.setVoyageNumber(dangerousGoodsManifest.getVoyageNumber());
		object.setPortOfLoadingCode(dangerousGoodsManifest.getPortOfLoadingCode());
		object.setPortOfDischargeCode(dangerousGoodsManifest.getPortOfDischargeCode());
		object.setShippingAgent(dangerousGoodsManifest.getShippingAgent());
		object.setAdditionalRemark(dangerousGoodsManifest.getAdditionalRemark());
		object.setSignPlace(dangerousGoodsManifest.getSignPlace());
		object.setSignDate(FormatData.parseStringToDate(dangerousGoodsManifest.getSignDate()));
		object.setMasterSigned(FormatData.convertToInt(dangerousGoodsManifest.getMasterSigned()));
		object.setRequestCode(header.getReference().getMessageId());
		
		object.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));

		List<DangerousGoodsItem> dangerousGoodsItem = dangerousGoodsManifest.getListDangerousGoods().getDangerousGoodsItem();
		if(dangerousGoodsItem != null && dangerousGoodsItem.size()>0){
			for(DangerousGoodsItem item : dangerousGoodsItem){
				TempDangerousGoodsItems details = new TempDangerousGoodsItems();
				details.setDangerousGoodItemCode(item.getDangerousGoodItemCode());
				details.setRefNumber(item.getRefNumber());
				details.setMarksContainer(item.getMarksContainer());
				details.setNumberContainer(item.getNumberContainer());
				details.setNumberOfPackage(item.getNumberOfPackage());
				details.setKindOfPackages(item.getKindOfPackages());
				details.setProperShippingName(item.getProperShippingName());
				details.setGoodClass(item.getGoodClass());
				details.setUnNumber(item.getUNNumber());
				details.setPackingGroup(item.getPackingGroup());
				details.setSubsidiaryRisk(item.getSubsidiaryRisk());
				details.setFlashPoint(item.getFlashPoint());
				details.setMarinePollutant(item.getMarinePollutant());
				details.setGrossWeight(FormatData.convertToDouble(item.getGrossWeight()));
				details.setEms(item.getEms());
				
				details.setRequestCode(object.getRequestCode());
				
				try{
					TempDangerousGoodsItemsLocalServiceUtil.addTempDangerousGoodsItems(details);
				}catch (Exception e) {
					log.error(e.getMessage());
				}
				
			}
			
			object.setListDangerousGoods(dangerousGoodsItem.size());
//			TempDangerousGoodsNanifestLocalServiceUtil.updateTempDangerousGoodsNanifest(object);
		}
		
		TempDangerousGoodsNanifestLocalServiceUtil.addTempDangerousGoodsNanifest(object);
		
		return true;
	}catch (Exception e) {
		log.error(e.getMessage());
	}
		return false;
	}
	public void insert2TempDocument(DangerousGoodsManifest dangerousGoodsManifest,Header header) throws Exception {
		TempDocument tempDocument = new TempDocument();
		
		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentName(FormatData.convertToLong(dangerousGoodsManifest.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(dangerousGoodsManifest.getDocumentYear()));
		tempDocument.setUserCreated(dangerousGoodsManifest.getUserCreated());
		tempDocument.setShipName(dangerousGoodsManifest.getNameOfShip());
//		tempDocument.set(dangerousGoodsManifest.getFlagStateOfShip());
		tempDocument.setShipCaptain(dangerousGoodsManifest.getMasterName());
		tempDocument.setImo(dangerousGoodsManifest.getIMONumber());
		tempDocument.setCallSign(dangerousGoodsManifest.getCallsign());
//		tempDocument.set(dangerousGoodsManifest.getVoyageNumber());
//		tempDocument.set(dangerousGoodsManifest.getPortOfLoadingCode());
//		tempDocument.set(dangerousGoodsManifest.getPortOfDischargeCode());
//		tempDocument.set(dangerousGoodsManifest.getShippingAgent());
//		tempDocument.set(dangerousGoodsManifest.getAdditionalRemark());
//		tempDocument.set(dangerousGoodsManifest.getSignPlace());
//		tempDocument.set(dangerousGoodsManifest.getSignDate());
//		tempDocument.set(dangerousGoodsManifest.getMasterSigned());
		tempDocument.setLastModifiedDate(new Date());
		
		BusinessUtils.insert2TempDocument(tempDocument, header, dangerousGoodsManifest);

	}
}