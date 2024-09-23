package vn.gt.tichhop.message.haiquan2giaothong.inland;

import java.util.Date;
import java.util.List;

import com.fds.nsw.kernel.exception.SystemException;




import vn.gt.constant.Constants;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.PageType;
import vn.nsw.Header;
import vn.nsw.model.inland.InlandGeneralDeclaration;
import vn.nsw.model.inland.InlandNoticeOfArrival;



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
public class InlandNoticeOfArrival2Temp {

	
	public boolean insert2Temp(InlandNoticeOfArrival noticeOfArrival, Header header) throws Exception {
		try {
			
			insert2TempDocument(noticeOfArrival, header);
			
			TempNoticeShipMessage object = new TempNoticeShipMessage();
			
			object.setRequestCode(header.getReference().getMessageId());
			object.setDocumentName(FormatData.convertToLong(noticeOfArrival.getDocumentName()));
			object.setDocumentYear(FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
			object.setUserCreated(noticeOfArrival.getUserCreated());
			object.setShipName(noticeOfArrival.getNameOfShip());
			object.setShipTypeCode(noticeOfArrival.getShipTypeCode());
			object.setStateCode("VN");
			object.setShipCaptain(noticeOfArrival.getNameOfMaster());
			object.setPurposeSpecified(noticeOfArrival.getOtherPersons());
//			object.setImo(noticeOfArrival.getIMONumber());
//			object.setGrt(FormatData.convertToDouble(noticeOfArrival.getGT()));
//			object.setDwt(FormatData.convertToDouble(noticeOfArrival.getDWT()));
//			object.setUnitGRT(noticeOfArrival.getGTUNIT());
//			object.setUnitDWT(noticeOfArrival.getDWTUNIT());
			object.setCallSign(noticeOfArrival.getCallSign());
			object.setArrivalDate(FormatData.parseStringToDate(noticeOfArrival.getTimeOfArrival()));
			object.setArrivalPortCode(noticeOfArrival.getPortOfArrivalCode());
			object.setPortHarbourCode(noticeOfArrival.getPortHarbourCode());
			object.setPortRegionCode(noticeOfArrival.getPortRegionCode());
			object.setPortWharfCode(noticeOfArrival.getPortWharfCode());
			object.setPortGoingToCode(noticeOfArrival.getLastPortOfCallCode());
//			object.setNameOfShipOwners(noticeOfArrival.getNameOfShipOwners());
//			object.setAddressOfShipOwners(noticeOfArrival.getAddressOfShipOwners());
//			object.setShipOwnerstateCode(noticeOfArrival.getShipOwnerStateCode());
//			object.setShipOwnerPhone(noticeOfArrival.getShipOwnerPhone());
//			object.setShipOwnerFax(noticeOfArrival.getShipOwnerFax());
//			object.setShipOwnerEmail(noticeOfArrival.getShipOwnerEmail());
//			object.setLoa(FormatData.convertToDouble(noticeOfArrival.getLOA()));
//			object.setBreadth(FormatData.convertToDouble(noticeOfArrival.getBreadth()));
			object.setClearanceHeight(FormatData.convertToDouble(noticeOfArrival.getClearanceHeight()));
			object.setShownDraftxF(FormatData.convertToDouble(noticeOfArrival.getShownDraftxF()));
			object.setShownDraftxA(FormatData.convertToDouble(noticeOfArrival.getShownDraftxA()));
//			object.setUnitLOA(noticeOfArrival.getLOAUNIT());
//			object.setUnitBreadth(noticeOfArrival.getBreadthUnit());
			object.setUnitClearanceHeight(noticeOfArrival.getClearanceHeightUnit());
			object.setUnitShownDraftxF(noticeOfArrival.getUnitShownDraftxF());
			object.setUnitShownDraftxA(noticeOfArrival.getUnitShownDraftxA());
//			object.setCertificateOfRegistryNumber(noticeOfArrival.getCertificateOfRegistryNumber());
//			object.setCertificateOfRegistryDate(FormatData.parseStringToDate(noticeOfArrival.getCertificateOfRegistryDate()));
//			object.setCertificateOfRegistryPortName(noticeOfArrival.getCertificateOfRegistryPortName());
			object.setShipAgencyAddress(noticeOfArrival.getShipAgencyAddress());
			object.setShipAgencyPhone(noticeOfArrival.getShipAgencyPhone());
			object.setShipAgencyFax(noticeOfArrival.getShipAgencyFax());
			object.setShipAgencyEmail(noticeOfArrival.getShipAgencyEmail());
			object.setPurposeCode(noticeOfArrival.getPurposeCode());
			object.setCrewNumber(FormatData.convertToLong(noticeOfArrival.getNumberOfCrew()));
			object.setPassengerNumber(FormatData.convertToLong(noticeOfArrival.getNumberOfPassengers()));
//			object.setQuantityAndTypeOfCargo(noticeOfArrival.getQuantityOfCargo());
			object.setUnitQuantityofCargo(noticeOfArrival.getUnitQuantityofCargo());
			object.setTypeOfCargo(noticeOfArrival.getTypeOfCargo());
		//	object.setOtherPersons(FormatData.convertToInt(noticeOfArrival.getOtherPersons()));
			object.setRemarks(noticeOfArrival.getRemarks());
			object.setSignPlace(noticeOfArrival.getSignPlace());
			object.setSignDate(FormatData.parseStringToDate(noticeOfArrival.getSignDate()));
			object.setMasterSigned(FormatData.convertToInt(noticeOfArrival.getMasterSigned()));
			object.setNameOfShipAgent(noticeOfArrival.getNameOfShipownersAgents());
			
			String cargoDescription = "";
			List<InlandNoticeOfArrival.CargoList> cargolist = noticeOfArrival.getCargoList();
			
			if (cargolist != null && cargolist.size() > 0) {
				for (InlandNoticeOfArrival.CargoList item : cargolist) {
					TempCargoItems details = new TempCargoItems();
					details.setRequestCode(header.getReference().getMessageId());
					details.setDocumentName(FormatData.convertToLong(noticeOfArrival.getDocumentName()));
					details.setDocumentYear(FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
					details.setCargoCategory(item.getCategory());
					details.setCargoType(item.getType());
					details.setCargoCode(item.getName());
					details.setDescription(item.getDescription());
					
					if (Validator.isNotNull(item.getQuantity())) {
						if (item.getQuantity().endsWith(".00")) {
							details.setQuantity(FormatData.convertToDouble(item.getQuantity().replace(".00", "")));
							log.info("---insert2TempCargoItems-----Quantity---get-1--" + item.getQuantity());
							log.info("---insert2TempCargoItems-----Quantity---get-12--" + item.getQuantity().replace(".00", ""));
						} else {
							details.setQuantity(FormatData.convertToDouble(item.getQuantity()));
							log.info("---insert2TempCargoItems-----Quantity---get-2--" + item.getQuantity());
						}
					}
					details.setUnit(item.getUnit());					

					
					try {
						if ((details.getCargoType().trim().length() > 0) & (details.getUnit().trim().length() > 0))
						{
							TempCargoItemsLocalServiceUtil.addTempCargoItems(details);
							
							// Chi hien thi hang hoa van chuyen tren tau							
							if ((details.getCargoCategory().trim().length() > 0 ) && (details.getCargoCategory().trim().equalsIgnoreCase("VC")))
							{
								if (cargoDescription.trim().length() > 0)
								{
									cargoDescription += "\n ";								
								}
								cargoDescription +=  DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName()
										+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, details.getCargoCode()).getName()
										+ "  " + details.getDescription()  + "  " + details.getQuantity()
										+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, details.getUnit()).getName();									
							}
						}
						else if (details.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
						{	
							TempCargoItemsLocalServiceUtil.addTempCargoItems(details);
							
							if (cargoDescription.trim().length() > 0)
							{
								cargoDescription += "\n ";								
							}
							if ((details.getCargoCategory().trim().length() > 0 ) && (details.getCargoCategory().trim().equalsIgnoreCase("VC")))
							{
								cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName();
							}
						}
					}catch (SystemException e) {
						e.printStackTrace();
						log.error(e.getMessage());
					};					
					
				}
			}
						
			if (cargoDescription.trim().length() > 0)
			{
//				object.setQuantityAndTypeOfCargo(cargoDescription);
			}
			
			
			if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_DEN_CANG) {
				object.setNoticeShipType(String.valueOf(PageType.TYPE_THONG_BAO_TAU_DEN_CANG));
				object.setConfirmTime(FormatData.convertToInt(header.getReference().getVersion()) + 1);
				
			} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_ROI_CANG) {
				object.setNoticeShipType(String.valueOf(PageType.TYPE_THONG_BAO_TAU_DEN_CANG));
				object.setConfirmTime(FormatData.convertToInt(header.getReference().getVersion()) + 1);
				
			} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.THONG_BAO_TAU_QUA_CANH) {
				object.setNoticeShipType(String.valueOf(PageType.TYPE_THONG_BAO_TAU_DEN_CANG));
				object.setConfirmTime(FormatData.convertToInt(header.getReference().getVersion()) + 1);
			
			} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG) {
				object.setNoticeShipType(String.valueOf(PageType.TYPE_XAC_BAO_TAU_DEN_CANG));
				object.setConfirmTime(FormatData.convertToInt(header.getReference().getVersion()) + 1);
				
			} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH) {
				object.setNoticeShipType(String.valueOf(PageType.TYPE_XAC_BAO_TAU_DEN_CANG));
				object.setConfirmTime(FormatData.convertToInt(header.getReference().getVersion()) + 1);
				
			} else {
				object.setNoticeShipType(String.valueOf(String.valueOf(PageType.TYPE_THONG_BAO_TAU_DEN_CANG)));
			}
			
			object.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			
			TempNoTiceShipMessageLocalServiceUtil.addTempNoTiceShipMessage(object);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	public void insert2TempDocument(InlandNoticeOfArrival noticeOfArrival, Header header) throws Exception {
		
		TempDocument tempDocument = new TempDocument();
		
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentName(FormatData.convertToLong(noticeOfArrival.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));
		tempDocument.setUserCreated(noticeOfArrival.getUserCreated());
		// tempDocument.setShipAgencyCode(noticeOfArrival.get());
		tempDocument.setShipName(noticeOfArrival.getNameOfShip());
		//tempDocument.setShipPosition(shipPosition);
		tempDocument.setShipTypeCode(noticeOfArrival.getShipTypeCode());
	//	tempDocument.setStateCode();
		tempDocument.setShipCaptain(noticeOfArrival.getNameOfMaster());
		tempDocument.setStateCode("VN");
//		tempDocument.setImo(noticeOfArrival.getIMONumber());
//		tempDocument.setGrt(FormatData.convertToLong(noticeOfArrival.getGT()));
		// tempDocument.setNT(noticeOfArrival.get());
//		tempDocument.setDwt(FormatData.convertToLong(noticeOfArrival.getDWT()));
//		tempDocument.setUnitGRT(noticeOfArrival.getGTUNIT());
		// tempDocument.setUnitNT(noticeOfArrival.get());
//		tempDocument.setUnitDWT(noticeOfArrival.getDWTUNIT());
		tempDocument.setCallSign(noticeOfArrival.getCallSign());

		// tempDocument.setPreDocumentName(noticeOfArrival.get());
		// tempDocument.setCreatedDate(noticeOfArrival.get());
		// tempDocument.setLastModifiedDate(noticeOfArrival.get());
		tempDocument.setLastModifiedDate(new Date());
		
		BusinessUtils.insert2InlanTempDocument(tempDocument, header, noticeOfArrival);
	}
}
