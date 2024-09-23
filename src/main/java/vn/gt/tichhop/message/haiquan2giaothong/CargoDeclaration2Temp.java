package vn.gt.tichhop.message.haiquan2giaothong;

import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.model.TempCargoDeclaration;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGoodsItems;
import vn.gt.dao.noticeandmessage.service.TempCargoDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGoodsItemsLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.CargoDeclaration;
import vn.nsw.model.CargoDeclaration.ListGoods.GoodItem;

import com.fds.nsw.kernel.exception.SystemException;



import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CargoDeclaration2Temp {
	
	
	
	public boolean insert2Temp(CargoDeclaration cargoDeclaration, Header header) throws SystemException {
	
		try {
			
			//insert2TempDocument(cargoDeclaration, header);
			TempCargoDeclaration tempCargoDeclaration = new TempCargoDeclaration();
			//ResultDeclarationLocalServiceUtil.addResultDeclaration(resultDeclaration)			
			tempCargoDeclaration.setRequestCode(header.getReference().getMessageId());
			tempCargoDeclaration.setDocumentName(FormatData.convertToLong(cargoDeclaration.getDocumentName()));
			tempCargoDeclaration.setDocumentYear(FormatData.convertToInt(cargoDeclaration.getDocumentYear()));
			tempCargoDeclaration.setUserCreated(cargoDeclaration.getUserCreated());
			tempCargoDeclaration.setIsArrival(FormatData.convertToInt(cargoDeclaration.getIsArrival()));
			tempCargoDeclaration.setNameOfShip(cargoDeclaration.getNameOfShip());
			tempCargoDeclaration.setImoNumber(cargoDeclaration.getIMONumber());
			tempCargoDeclaration.setCallsign(cargoDeclaration.getCallsign());
			tempCargoDeclaration.setVoyageNumber(cargoDeclaration.getVoyageNumber());
			tempCargoDeclaration.setPortReport(cargoDeclaration.getPortReport());
			tempCargoDeclaration.setFlagStateOfShip(cargoDeclaration.getFlagStateOfShip());
			tempCargoDeclaration.setNameOfMaster(cargoDeclaration.getNameOfMaster());
			tempCargoDeclaration.setPortOfLoading(cargoDeclaration.getPortOfLoading());
			tempCargoDeclaration.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
//			tempCargoDeclaration.setSignDate(signDate)
//			tempCargoDeclaration.setSignPlace(cargoDeclaration.getS)
			if (cargoDeclaration.getListGoods() != null && cargoDeclaration.getListGoods().getGoodItem() != null) {
			tempCargoDeclaration.setListGoods(cargoDeclaration.getListGoods().getGoodItem().size());
			}
			
			TempCargoDeclarationLocalServiceUtil.addTempCargoDeclaration(tempCargoDeclaration);
			
			List<GoodItem> lists = cargoDeclaration.getListGoods().getGoodItem();
			for (GoodItem item : lists) {
				try {
					TempGoodsItems tempGoodsItem = new TempGoodsItems();
					
					tempGoodsItem.setRequestCode(tempCargoDeclaration.getRequestCode());
					tempGoodsItem.setBillOfLadingNo(item.getBillOfLadingNo());
					tempGoodsItem.setGoodItemCode(item.getGoodItemCode());
					tempGoodsItem.setGoodItemDescription(item.getGoodItemDescription());
					tempGoodsItem.setNumberOfPackage(FormatData.convertToLong(item.getNumberOfPackage()));
					tempGoodsItem.setKindOfPackages(item.getKindOfPackages());
					tempGoodsItem.setMarksandNosofGoods(item.getMarksAndNoOfGoods());
					tempGoodsItem.setGrossWeight(FormatData.convertToDouble(item.getGrossWeight()));
					tempGoodsItem.setGrossWeightUnit(item.getGrossWeightUnit());
					tempGoodsItem.setMeasurement(FormatData.convertToDouble(item.getMeasurement()));
					tempGoodsItem.setMeasurementUnit(item.getMeasurementUnit());
					
					TempGoodsItemsLocalServiceUtil.addTempGoodsItems(tempGoodsItem);
				} catch (Exception e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	public void insert2TempDocument(CargoDeclaration cargoDeclaration, Header header) throws SystemException {
		
		TempDocument tempDocument = new TempDocument();
		
		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentName(FormatData.convertToLong(cargoDeclaration.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(cargoDeclaration.getDocumentYear()));
		tempDocument.setUserCreated(cargoDeclaration.getUserCreated());
//		tempDocument.set(cargoDeclaration.getisArrival());
		tempDocument.setShipName(cargoDeclaration.getNameOfShip());
		tempDocument.setImo(cargoDeclaration.getIMONumber());
		tempDocument.setCallSign(cargoDeclaration.getCallsign());
//		tempDocument.set(cargoDeclaration.getVoyageNumber());
//		tempDocument.set(cargoDeclaration.getPortReport());
		tempDocument.setStateCode(cargoDeclaration.getFlagStateOfShip());
		tempDocument.setShipCaptain(cargoDeclaration.getNameOfMaster());
//		tempDocument.set(cargoDeclaration.getPortOfLoading());
		tempDocument.setLastModifiedDate(new Date());
		
		BusinessUtils.insert2TempDocument(tempDocument,header, cargoDeclaration);
	}
}
