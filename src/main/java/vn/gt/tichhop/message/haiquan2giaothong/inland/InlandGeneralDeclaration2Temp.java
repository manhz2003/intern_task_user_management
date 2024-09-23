package vn.gt.tichhop.message.haiquan2giaothong.inland;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fds.nsw.kernel.exception.SystemException;




import vn.gt.constant.Constants;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import vn.gt.dao.noticeandmessage.service.TempCrewDetailsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.PTTNDGeneralDeclaration;
import vn.nsw.model.PTTNDGeneralDeclaration.CargoList;
import vn.nsw.model.inland.InlandGeneralDeclaration;
import vn.gt.tichhop.message.MessageType;

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
public class InlandGeneralDeclaration2Temp {
	
	
	
	public boolean insert2Temp(InlandGeneralDeclaration generalDeclaration, Header header) throws SystemException {
		log.info("---------insert2--InlandGeneralDeclaration------------");
		try {
			insert2TempDocumentInland(generalDeclaration, header);
			
			TempGeneralDeclaration object = new TempGeneralDeclaration();
			
			object.setRequestCode(header.getReference().getMessageId());
			object.setDocumentName(FormatData.convertToLong(generalDeclaration.getDocumentName()));
			object.setDocumentYear(FormatData.convertToInt(generalDeclaration.getDocumentYear()));
			object.setUserCreated(generalDeclaration.getUserCreated());
			object.setIsArrival(FormatData.convertToInt(generalDeclaration.getIsArrival()));
			object.setNameOfShip(generalDeclaration.getNameOfShip());
			object.setShipTypeCode(generalDeclaration.getShipTypeCode());
			// object.setIMONumber(generalDeclaration.getIMONumber());
			object.setCallSign(generalDeclaration.getCallSign());
			// object.setVoyageNumber(generalDeclaration.getVoyageNumber());
			object.setPortOfArrivalCode(generalDeclaration.getPortOfArrivalCode());
			object.setDateOfArrival(FormatData.parseStringToDate(generalDeclaration.getDateOfArrival()));
			object.setPortHarbourCode(generalDeclaration.getPortHarbourCode());
			object.setPortRegionCode(generalDeclaration.getPortRegionCode());
			object.setPortWharfCode(generalDeclaration.getPortWharfCode());
			
			// object.setFlagStateOfShip(generalDeclaration.getFlagStateOfShip());
			object.setNameOfMaster(generalDeclaration.getNameOfMaster());
			object.setLastPortOfCallCode(generalDeclaration.getLastPortOfCallCode());
			// object.setCertificateOfRegistryNumber(generalDeclaration.getCertificateOfRegistryNumber());
			// object.setCertificateOfRegistryDate(FormatData.parseStringToDate(generalDeclaration.getCertificateOfRegistryDate()));
			// object.setCertificateOfRegistryPortName(generalDeclaration.getCertificateOfRegistryPortName());
			object.setTaxCodeOfShipownersAgents(generalDeclaration.getTaxCodeOfShipownersAgents().trim());
			object.setNameOfShipownersAgents(generalDeclaration.getNameOfShipownersAgents());
			object.setShipAgencyAddress(generalDeclaration.getShipAgencyAddress());
			object.setShipAgencyPhone(generalDeclaration.getShipAgencyPhone());
			object.setShipAgencyFax(generalDeclaration.getShipAgencyFax());
			object.setShipAgencyEmail(generalDeclaration.getShipAgencyEmail());
			
			// object.setGrossTonnage(FormatData.convertToDouble(generalDeclaration.getGrossTonnage()));
			
			if (generalDeclaration.getGrossTonnage().endsWith(".00")) {
				object.setGrossTonnage(FormatData.convertToDouble(generalDeclaration.getGrossTonnage().replace(".00", "")));
				log.info("---insert2Temp-----GrossTonnage---get-1--" + object.getGrossTonnage());
			} else {
				object.setGrossTonnage(FormatData.convertToDouble(generalDeclaration.getGrossTonnage()));
				log.info("---insert2Temp-----GrossTonnage---get-2--" + object.getGrossTonnage());
			}
			
			String cargoDescription = "";
			String cargoDescription_XEP = "";
			String cargoDescription_DO = "";
			String cargoDescription_VC = "";
			List<InlandGeneralDeclaration.CargoList> cargolist = generalDeclaration.getCargoList();
			
			if (cargolist != null && cargolist.size() > 0) {
				for (InlandGeneralDeclaration.CargoList item : cargolist) {
					TempCargoItems details = new TempCargoItems();
					details.setRequestCode(header.getReference().getMessageId());
					details.setDocumentName(FormatData.convertToLong(generalDeclaration.getDocumentName()));
					details.setDocumentYear(FormatData.convertToInt(generalDeclaration.getDocumentYear()));
					details.setCargoCategory(item.getCategory());
					details.setCargoType(item.getType());
					details.setCargoCode(item.getName());
					details.setDescription(item.getDescription());
					
					if (Validator.isNotNull(item.getQuantity())) {
						if (item.getQuantity().endsWith(".00")) {
							details.setQuantity(FormatData.convertToDouble(item.getQuantity().replace(".00", "")));
							log.info("---insert2TempCargoItems-----Quantity---get-1--" + item.getQuantity());
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
							
							if ((details.getCargoCategory().trim().length() > 0 ) && (details.getCargoCategory().trim().equalsIgnoreCase("XEP")))
							{
								if (cargoDescription_XEP.trim().length() > 0)
								{
									cargoDescription_XEP += "\n ";								
								}
								cargoDescription_XEP += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName()
										+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, details.getCargoCode()).getName()
										+ "  " + details.getDescription()  + "  " + details.getQuantity()
										+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, details.getUnit()).getName();
							}
							
							if ((details.getCargoCategory().trim().length() > 0 ) && (details.getCargoCategory().trim().equalsIgnoreCase("DO")))
							{
								if (cargoDescription_DO.trim().length() > 0)
								{
									cargoDescription_DO += "\n ";								
								}
								cargoDescription_DO += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName()
										+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, details.getCargoCode()).getName()
										+ "  " + details.getDescription()  + "  " + details.getQuantity()
										+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, details.getUnit()).getName();
							}
							
							if ((details.getCargoCategory().trim().length() > 0 ) && (details.getCargoCategory().trim().equalsIgnoreCase("VC")))
							{
								if (cargoDescription_VC.trim().length() > 0)
								{
									cargoDescription_VC += "\n ";								
								}
								cargoDescription_VC += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName()
										+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, details.getCargoCode()).getName()
										+ "  " + details.getDescription()  + "  " + details.getQuantity()
										+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, details.getUnit()).getName();
							}
						}
						else if (details.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
						{	
							TempCargoItemsLocalServiceUtil.addTempCargoItems(details);
							
							if ((details.getCargoCategory().trim().length() > 0 ) && (details.getCargoCategory().trim().equalsIgnoreCase("XEP")))
							{
								if (cargoDescription_XEP.trim().length() > 0)
								{
									cargoDescription_XEP += "\n ";								
								}
								cargoDescription_XEP += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName();									
							}
							
							if ((details.getCargoCategory().trim().length() > 0 ) && (details.getCargoCategory().trim().equalsIgnoreCase("DO")))
							{
								if (cargoDescription_DO.trim().length() > 0)
								{
									cargoDescription_DO += "\n ";								
								}
								cargoDescription_DO += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName();
							}
							
							if ((details.getCargoCategory().trim().length() > 0 ) && (details.getCargoCategory().trim().equalsIgnoreCase("VC")))
							{
								if (cargoDescription_VC.trim().length() > 0)
								{
									cargoDescription_VC += "\n ";								
								}
								cargoDescription_VC += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName();
							}
							
						}
					}catch (SystemException e) {
						
						log.error(e.getMessage());
					};					
					
				}
			}
			
			if (cargoDescription_XEP.trim().length() > 0)
			{				
				cargoDescription +=  DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "XEP").getName() + ": " + "\n" + cargoDescription_XEP;
						
			}
			
			if (cargoDescription_DO.trim().length() > 0)
			{				
				cargoDescription +=  "\n" + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "DO").getName() + ": " + "\n"  + cargoDescription_DO;
						
			}
			
			if (cargoDescription_VC.trim().length() > 0)
			{				
				cargoDescription +=  "\n" + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "VC").getName() + ": " + "\n"  + cargoDescription_VC;
						
			}
			
			if (cargoDescription.trim().length() > 0)
			{
				object.setBriefDescriptionOfTheCargo(cargoDescription);
			}
			else 
			{
				object.setBriefDescriptionOfTheCargo(generalDeclaration.getBriefDescriptionOfTheCargo());
			}
			object.setRemarks(generalDeclaration.getRemarks());
			
			
			object.setSignPlace(generalDeclaration.getSignPlace());
			object.setSignDate(FormatData.parseStringToDate(generalDeclaration.getSignDate()));
			object.setMasterSigned(FormatData.convertToInt(generalDeclaration.getMasterSigned()));
			
			object.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			
			TempGeneralDeclarationLocalServiceUtil.addTempGeneralDeclaration(object);
			
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			
		}
		return false;
	}
	//PTTND
	public boolean insert2TempPTTND(PTTNDGeneralDeclaration generalDeclaration, Header header) throws SystemException {
		log.info("---------insert2TempPTTNDGeneralDeclaration------------");
		try {
			insert2TempDocumentPTTND(generalDeclaration, header);
			
			TempGeneralDeclaration object = new TempGeneralDeclaration();
			
			object.setRequestCode(header.getReference().getMessageId());
			object.setDocumentName(FormatData.convertToLong(generalDeclaration.getDocumentName()));
			object.setDocumentYear(FormatData.convertToInt(generalDeclaration.getDocumentYear()));
			object.setUserCreated(generalDeclaration.getUserCreated());
			object.setIsArrival(FormatData.convertToInt(generalDeclaration.getIsArrival()));
			object.setNameOfShip(generalDeclaration.getNameOfShip());
			object.setShipTypeCode(generalDeclaration.getShipTypeCode());
			// object.setIMONumber(generalDeclaration.getIMONumber());
			object.setCallSign(generalDeclaration.getCallSign());
			// object.setVoyageNumber(generalDeclaration.getVoyageNumber());
			object.setNextProvinceCode(generalDeclaration.getNextProvinceCode());
			object.setPortOfArrivalCode(generalDeclaration.getPortOfArrivalCode());
			object.setDateOfArrival(FormatData.parseStringToDate(generalDeclaration.getDateOfArrival()));
			
			
			object.setMaritimePortCode(generalDeclaration.getMaritimePortCode());
			object.setPortRegionCode(generalDeclaration.getPortRegionCode());
			object.setPortHarbourCode(generalDeclaration.getPortHarbourCode());
			object.setPortWharfCode(generalDeclaration.getPortWharfCode());
			
			// object.setFlagStateOfShip(generalDeclaration.getFlagStateOfShip());
			object.setFlagStateOfShip("VN");
			object.setNameOfMaster(generalDeclaration.getNameOfMaster());
			object.setLastProvinceCode(generalDeclaration.getLastProvinceCode());
			object.setLastPortOfCallCode(generalDeclaration.getLastPortOfCallCode());
			// object.setCertificateOfRegistryNumber(generalDeclaration.getCertificateOfRegistryNumber());
			// object.setCertificateOfRegistryDate(FormatData.parseStringToDate(generalDeclaration.getCertificateOfRegistryDate()));
			// object.setCertificateOfRegistryPortName(generalDeclaration.getCertificateOfRegistryPortName());
			object.setTaxCodeOfShipownersAgents(generalDeclaration.getTaxCodeOfShipownersAgents().trim());
			object.setNameOfShipownersAgents(generalDeclaration.getNameOfShipownersAgents());
			object.setShipAgencyAddress(generalDeclaration.getShipAgencyAddress());
			object.setShipAgencyPhone(generalDeclaration.getShipAgencyPhone());
			object.setShipAgencyFax(generalDeclaration.getShipAgencyFax());
			object.setShipAgencyEmail(generalDeclaration.getShipAgencyEmail());
			
			object.setBriefParticularsOfVoyage(generalDeclaration.getBriefParticularsOfVoyage());
			object.setCargo(generalDeclaration.getCargo());
			object.setCargoOnBoard(generalDeclaration.getCargoOnBoard());
			object.setVolumeCargo(BigDecimal.valueOf(0));
			object.setCargoUnit(generalDeclaration.getCargoUnit());
			object.setCargoDescription(generalDeclaration.getCargoDescription());
			object.setCallSign(generalDeclaration.getCallSign());
			if (Validator.isNotNull(generalDeclaration.getNetTonnage())) {
				object.setNetTonnage(Double.valueOf(generalDeclaration.getNetTonnage()));
			}
			
			
			if (Validator.isNotNull(generalDeclaration.getGrossTonnage())) {	
				if (generalDeclaration.getGrossTonnage().endsWith(".00")) {
					object.setGrossTonnage(FormatData.convertToDouble(generalDeclaration.getGrossTonnage().replace(".00", "")));
					log.info("---insert2Temp-----GrossTonnage---get-1--" + object.getGrossTonnage());
				} else {
					object.setGrossTonnage(FormatData.convertToDouble(generalDeclaration.getGrossTonnage()));
					log.info("---insert2Temp-----GrossTonnage---get-2--" + object.getGrossTonnage());
				}
			}
			
			
			object.setPositionOfShipInPort(generalDeclaration.getPositionOfShipInPort());
			object.setBriefParticularsOfVoyage(generalDeclaration.getBriefParticularsOfVoyage());
			object.setBriefDescriptionOfTheCargo(generalDeclaration.getBriefDescriptionOfTheCargo());
			
			object.setRemarks(generalDeclaration.getRemarks());
			object.setRatedPower(generalDeclaration.getRatedPower());
			object.setSeatingCapacity(generalDeclaration.getSeatingCapacity());			
			object.setLyingCapacity(generalDeclaration.getLyingCapacity());
			
			
			object.setLastMaritimePortCode(generalDeclaration.getLastMaritimePortCode());
			object.setLastPortRegionCode(generalDeclaration.getLastPortRegionCode());
			object.setLastPortHarbourCode(generalDeclaration.getLastPortHarbourCode());
			object.setLastPortWharfCode(generalDeclaration.getLastPortWharfCode());
			object.setNextMaritimePortCode(generalDeclaration.getNextMaritimePortCode());
			object.setNextPortRegionCode(generalDeclaration.getNextPortRegionCode());
			object.setNextPortHarbourCode(generalDeclaration.getNextPortHarbourCode());
			object.setNextPortWharfCode(generalDeclaration.getNextPortWharfCode());
						
			object.setSignPlace(generalDeclaration.getSignPlace());
			object.setSignDate(FormatData.parseStringToDate(generalDeclaration.getSignDate()));
			object.setMasterSigned(FormatData.convertToInt(generalDeclaration.getMasterSigned()));
			
			object.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			
			TempGeneralDeclarationLocalServiceUtil.addTempGeneralDeclaration(object);
			
			
			String cargoDescription = "";
			List<CargoList> cargolist = generalDeclaration.getCargoList();

			if (cargolist != null && cargolist.size() > 0) {
				for (CargoList item : cargolist) {
					TempCargoItems details = new TempCargoItems();
					details.setRequestCode(header.getReference().getMessageId());
					details.setDocumentName(FormatData.convertToLong(generalDeclaration.getDocumentName()));
					details.setDocumentYear(FormatData.convertToInt(generalDeclaration.getDocumentYear()));
					details.setCargoCategory(item.getCategory());
					details.setCargoType(item.getType());
					details.setCargoCode(item.getName());
					details.setDescription(item.getDescription());
					
					if (Validator.isNotNull(item.getQuantity())) {
						if (item.getQuantity().endsWith(".00")) {
							details.setQuantity(FormatData.convertToDouble(item.getQuantity().replace(".00", "")));
							log.info("---insert2TempCargoItems-----Quantity---get-1--" + item.getQuantity());
						} else {
							details.setQuantity(FormatData.convertToDouble(item.getQuantity()));
							log.info("---insert2TempCargoItems-----Quantity---get-2--" + item.getQuantity());
						}
					}
					details.setUnit(item.getUnit());					
		
					
					try {
						if ((details.getCargoType().trim().length() > 0) & (details.getUnit().trim().length() > 0))
						{
							if (cargoDescription.trim().length() > 0)
							{
								cargoDescription += "\n ";								
							}
						cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName()
								+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, details.getCargoCode()).getName()
								+ "  " + details.getDescription()  + "  " + details.getQuantity()
								+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, details.getUnit()).getName();
							TempCargoItemsLocalServiceUtil.addTempCargoItems(details);
							
						}
						else if (details.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
						{
							if (cargoDescription.trim().length() > 0)
							{
								cargoDescription += "\n ";								
							}
							cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName()	;
							TempCargoItemsLocalServiceUtil.addTempCargoItems(details);	
						}
					}catch (SystemException e) {
						
						log.error(e.getMessage());
					};					
				}
			}
			
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			
		}
		return false;
	}
	public void insert2TempDocumentPTTND(PTTNDGeneralDeclaration object, Header header) throws Exception {
		
		TempDocument tempDocument = new TempDocument();
		
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));
		tempDocument.setDocumentName(FormatData.convertToLong(object.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(object.getDocumentYear()));
		tempDocument.setUserCreated(object.getUserCreated());
		
		tempDocument.setShipName(object.getNameOfShip());
		tempDocument.setShipTypeCode(object.getShipTypeCode());
		// tempDocument.setImo(object.getIMONumber());
		tempDocument.setCallSign(object.getCallSign());
		tempDocument.setStateCode("VN");
//		 tempDocument.setStateCode(object.getFlagStateOfShip());
		tempDocument.setShipCaptain(object.getNameOfMaster());
		tempDocument.setPortRegionCode(object.getPortRegionCode());
		tempDocument.setLastPortCode(object.getLastPortOfCallCode());
		tempDocument.setPortCode(object.getPortOfArrivalCode());
		if (Validator.isNotNull(object.getGrossTonnage())) {
			if (object.getGrossTonnage().endsWith(".00")) {
				tempDocument.setGrt(FormatData.convertToDouble(object.getGrossTonnage().replace(".00", "")));
				log.info("---insert2Temp-----GrossTonnage---get-1--" + object.getGrossTonnage());
			} else {
				tempDocument.setGrt(FormatData.convertToDouble(object.getGrossTonnage()));
				log.info("---insert2Temp-----GrossTonnage---get-2--" + object.getGrossTonnage());
			}
		}
		
		
		if (Validator.isNotNull(object.getNetTonnage())) {
			if (object.getNetTonnage().endsWith(".00")) {
				tempDocument.setDwt(FormatData.convertToDouble(object.getNetTonnage().replace(".00", "")));
				log.info("---insert2Temp-----NetTonnage---get-1--" + object.getNetTonnage());
			} else {
				tempDocument.setDwt(FormatData.convertToDouble(object.getNetTonnage()));
				log.info("---insert2Temp-----NetTonnage---get-2--" + object.getNetTonnage());
			}
		}
		
//		 tempDocument.setGrt(FormatData.convertToDouble(object.getGrossTonnage()));
//		 tempDocument.setUnitGRT(object.getGrossTonnageUnit());
//		 tempDocument.setDwt(FormatData.convertToDouble(object.getNetTonnage()));
//		 tempDocument.setUnitNT(object.getNetTonnageUnit());
		
		 
		 
		tempDocument.setLastModifiedDate(new Date());
		
		BusinessUtils.insert2InlanTempDocument(tempDocument, header, object);
	}
	public void insert2TempDocumentInland(InlandGeneralDeclaration object, Header header) throws Exception {
		
		TempDocument tempDocument = new TempDocument();
		
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));
		tempDocument.setDocumentName(FormatData.convertToLong(object.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(object.getDocumentYear()));
		tempDocument.setUserCreated(object.getUserCreated());
		
		tempDocument.setShipName(object.getNameOfShip());
		tempDocument.setShipTypeCode(object.getShipTypeCode());
		// tempDocument.setImo(object.getIMONumber());
		tempDocument.setCallSign(object.getCallSign());
		tempDocument.setStateCode("VN");
		// tempDocument.setStateCode(object.getFlagStateOfShip());
		tempDocument.setShipCaptain(object.getNameOfMaster());
		tempDocument.setPortRegionCode(object.getPortRegionCode());
		tempDocument.setLastPortCode(object.getLastPortOfCallCode());
		tempDocument.setPortCode(object.getPortOfArrivalCode());
		// tempDocument.setGrt(FormatData.convertToDouble(object.getGrossTonnage()));
		// tempDocument.setUnitGRT(object.getGrossTonnageUnit());
		// tempDocument.setNt(FormatData.convertToDouble(object.getNetTonnage()));
		// tempDocument.setUnitNT(object.getNetTonnageUnit());
		
		tempDocument.setLastModifiedDate(new Date());
		
		BusinessUtils.insert2InlanTempDocument(tempDocument, header, object);
	}
}
