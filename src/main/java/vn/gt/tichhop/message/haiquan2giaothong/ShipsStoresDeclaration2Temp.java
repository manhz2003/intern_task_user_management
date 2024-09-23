package vn.gt.tichhop.message.haiquan2giaothong;

import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration;
import com.fds.nsw.nghiepvu.model.TempShipStoresItems;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration;
import com.fds.nsw.nghiepvu.model.TempShipStoresItems;
import vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipStoresItemsLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.ShipsStoresDeclaration;
import vn.nsw.model.ShipsStoresDeclaration.ListShipsStores.ShipsStoreItem;




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
public class ShipsStoresDeclaration2Temp {

//	private static String TEMP_SHIP_STORES_DECLARATION = "TEMP_SHIP_STORES_DECLARATION";
//	private static String TEMP_SHIP_STORES_ITEMS = "TEMP_SHIP_STORES_ITEMS";
	
	
	
	public boolean insert2Temp(ShipsStoresDeclaration shipsStoresDeclaration, Header header) throws Exception{
		
		try{
			insert2TempDocument(shipsStoresDeclaration, header);
			
			TempShipStoresDeclaration object=new TempShipStoresDeclaration();
			
			object.setRequestCode(header.getReference().getMessageId());
			object.setDocumentName(FormatData.convertToLong(shipsStoresDeclaration.getDocumentName()));
			object.setDocumentYear(FormatData.convertToInt(shipsStoresDeclaration.getDocumentYear()));
			object.setUserCreated(shipsStoresDeclaration.getUserCreated());
			object.setIsArrival(FormatData.convertToInt(shipsStoresDeclaration.getIsArrival()));
			object.setNameOfShip(shipsStoresDeclaration.getNameOfShip());
			object.setIMONumber(shipsStoresDeclaration.getIMONumber());
			object.setCallsign(shipsStoresDeclaration.getCallsign());
			object.setVoyageNumber(shipsStoresDeclaration.getVoyageNumber());
			object.setNationalityOfShip(shipsStoresDeclaration.getNationalityOfShip());
			object.setPortOfArrivalCode(shipsStoresDeclaration.getPortOfArrivalCode());
			object.setDateOfArrival(FormatData.parseStringToDate(shipsStoresDeclaration.getDateOfArrival()));
			object.setLastPortOfCallCode(shipsStoresDeclaration.getLastPortOfCallCode());
			object.setNumberOfPersonsOnBoat(FormatData.convertToInt(shipsStoresDeclaration.getNumberOfPersonsOnBoat()));
			object.setPeriodOfStay(shipsStoresDeclaration.getPeriodOfStay());
			object.setPeriodOfStayUnit(shipsStoresDeclaration.getPeriodOfStayUnit());
			object.setNameOfMaster(shipsStoresDeclaration.getNameOfMaster());
			object.setSignPlace(shipsStoresDeclaration.getSignPlace());
			object.setSignDate(FormatData.parseStringToDate(shipsStoresDeclaration.getSignDate()));
			object.setMasterSigned(FormatData.convertToInt(shipsStoresDeclaration.getMasterSigned()));
			object.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));

			TempShipStoresDeclarationLocalServiceUtil.addTempShipStoresDeclaration(object);
			
			List<ShipsStoreItem> storeItems=shipsStoresDeclaration.getListShipsStores().getShipsStoreItem();
			if (storeItems!=null && storeItems.size()>0){
				for (ShipsStoreItem item: storeItems){
					TempShipStoresItems shipStoresItem=new TempShipStoresItems();
					
					shipStoresItem.setRequestCode(header.getReference().getMessageId());
					shipStoresItem.setShipsStoreItemCode(item.getShipsStoreItemCode());
					shipStoresItem.setNameOfArticle(item.getNameOfArticle());
					shipStoresItem.setQuantity(item.getQuantity());
					shipStoresItem.setQuantityUnit(item.getQuantityUnit());
					shipStoresItem.setLocationOnBoat(item.getLocationOnBoat());
					shipStoresItem.setUseInBoat(FormatData.convertToInt(item.getUseInBoat()));

					try{
						TempShipStoresItemsLocalServiceUtil.addTempShipStoresItems(shipStoresItem);
					}catch (Exception e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
			
			return true;
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public void insert2TempDocument(ShipsStoresDeclaration object, Header header) throws Exception{
		
		TempDocument tempDocument = new TempDocument();
		
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));
		tempDocument.setDocumentName(FormatData.convertToLong(object.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(object.getDocumentYear()));
		tempDocument.setUserCreated(object.getUserCreated());
		tempDocument.setShipName(object.getNameOfShip());
		tempDocument.setImo(object.getIMONumber());
		tempDocument.setCallSign(object.getCallsign());
		tempDocument.setStateCode(object.getNationalityOfShip());
		
		tempDocument.setLastModifiedDate(new Date());
		
		BusinessUtils.insert2TempDocument(tempDocument, header, object);
	}
		
}
