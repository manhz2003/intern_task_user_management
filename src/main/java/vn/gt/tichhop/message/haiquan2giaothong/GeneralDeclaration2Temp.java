package vn.gt.tichhop.message.haiquan2giaothong;

import java.util.Date;
import java.util.List;

import com.fds.flex.common.utility.string.StringUtil;
import vn.gt.constant.Constants;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempWasteList;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempWasteListLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.GeneralDeclaration;
import vn.nsw.model.GeneralDeclaration.BriefParticularsOfVoyage;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.kernel.exception.SystemException;





import lombok.extern.slf4j.Slf4j;
import com.fds.flex.common.ultility.Validator;

@Slf4j
public class GeneralDeclaration2Temp {
	
	
	
	public boolean insert2Temp(GeneralDeclaration generalDeclaration, Header header) throws Exception {
	
		try {
			insert2TempDocument(generalDeclaration, header);
			
			TempGeneralDeclaration object = new TempGeneralDeclaration();
			
			object.setRequestCode(header.getReference().getMessageId());
			object.setDocumentName(FormatData.convertToLong(generalDeclaration.getDocumentName()));
			object.setDocumentReference(generalDeclaration.getDocumentReference());
			object.setDocumentYear(FormatData.convertToInt(generalDeclaration.getDocumentYear()));
			object.setUserCreated(generalDeclaration.getUserCreated());
			object.setIsArrival(FormatData.convertToInt(generalDeclaration.getIsArrival()));
			object.setNameOfShip(generalDeclaration.getNameOfShip());
			object.setShipTypeCode(generalDeclaration.getShipTypeCode());
			object.setImoNumber(generalDeclaration.getIMONumber());
			object.setCallSign(generalDeclaration.getCallSign());
			object.setVoyageNumber(generalDeclaration.getVoyageNumber());
			object.setPortOfArrivalCode(generalDeclaration.getPortOfArrivalCode());
			object.setDateOfArrival(FormatData.parseStringToDate(generalDeclaration.getDateOfArrival()));
			object.setPortHarbourCode(generalDeclaration.getPortHarbourCode());
			object.setPortRegionCode(generalDeclaration.getPortRegionCode());
			object.setPortWharfCode(generalDeclaration.getPortWharfCode());
			object.setFlagStateOfShip(generalDeclaration.getFlagStateOfShip());
			object.setNameOfMaster(generalDeclaration.getNameOfMaster());
			object.setLastPortOfCallCode(generalDeclaration.getLastPortOfCallCode());
			object.setCertificateOfRegistryNumber(generalDeclaration.getCertificateOfRegistryNumber());
			object.setCertificateOfRegistryDate(FormatData.parseStringToDate(generalDeclaration.getCertificateOfRegistryDate()));
			object.setCertificateOfRegistryPortName(generalDeclaration.getCertificateOfRegistryPortName());
			object.setTaxCodeOfShipownersAgents(generalDeclaration.getTaxCodeOfShipownersAgents().trim());
			object.setNameOfShipownersAgents(generalDeclaration.getNameOfShipownersAgents());
			object.setShipAgencyAddress(generalDeclaration.getShipAgencyAddress());
			object.setShipAgencyPhone(generalDeclaration.getShipAgencyPhone());
			object.setShipAgencyFax(generalDeclaration.getShipAgencyFax());
			object.setShipAgencyEmail(generalDeclaration.getShipAgencyEmail());
			
			object.setGrossTonnage(FormatData.convertToDouble(generalDeclaration.getGrossTonnage()));
			//object.setGrossTonnageUnit(generalDeclaration.getGrossTonnageUnit());
			
			object.setNetTonnage(FormatData.convertToDouble(generalDeclaration.getNetTonnage()));
			object.setPositionOfShipInPort(generalDeclaration.getPositionOfShipInPort());
			//object.setBriefParticularsOfVoyage(generalDeclaration.getBriefParticularsOfVoyage());
			object.setRemainingCargo(generalDeclaration.getRemainingCargo());
			object.setBriefDescriptionOfTheCargo(generalDeclaration.getBriefDescriptionOfTheCargo());
			object.setNumberOfCrew(FormatData.convertToInt(generalDeclaration.getNumberOfCrew()));
			object.setNumberOfPassengers(FormatData.convertToInt(generalDeclaration.getNumberOfPassengers()));
			object.setShipRequirementsInTermsOfWaste(generalDeclaration.getShipRequirementsInTermsOfWaste());
			object.setRemarks(generalDeclaration.getRemarks());
			object.setNumberCargoDeclaration(generalDeclaration.getNumberCargoDeclaration());
			object.setNumberShipStoreDeclaration(generalDeclaration.getNumberShipStoreDeclaration());
			object.setNumberCrewList(generalDeclaration.getNumberCrewList());
			object.setNumberPassengerList(generalDeclaration.getNumberPassengerList());
			object.setNumberCrewEffects(generalDeclaration.getNumberCrewEffectsDeclaration());
			object.setNumberHealthMaritime(generalDeclaration.getNumberHealthMaritimeDeclaration());
			object.setSignPlace(generalDeclaration.getSignPlace());
			object.setSignDate(FormatData.parseStringToDate(generalDeclaration.getSignDate()));
			object.setMasterSigned(FormatData.convertToInt(generalDeclaration.getMasterSigned()));
			
			BriefParticularsOfVoyage obj = generalDeclaration.getBriefParticularsOfVoyage();
			if(obj != null) {
				object.setPreviousPortsOfCall(obj.getPreviousPortsOfCall());
				object.setSubsequentPortsOfCall(obj.getSubsequentPortsOfCall());
				object.setDischargedPorts(obj.getDischargedPorts());
			}
			
			object.setLink(generalDeclaration.getLink());
			object.setQcCode(generalDeclaration.getQCCode());
			object.setPortClearanceNo(generalDeclaration.getNumberOfPortClearance());
			object.setLoa(FormatData.convertToDouble(generalDeclaration.getLOA()));
			object.setUnitLOA(generalDeclaration.getLOAUNIT());
			object.setBreadth(FormatData.convertToDouble(generalDeclaration.getBreadth()));
			object.setUnitBreadth(generalDeclaration.getBreadthUnit());
			object.setClearanceHeight(FormatData.convertToDouble(generalDeclaration.getClearanceHeight()));
			object.setUnitClearanceHeight(generalDeclaration.getClearanceHeightUnit());
			object.setShownDraftxA(FormatData.convertToDouble(generalDeclaration.getShownDraftxA()));
			object.setUnitShownDraftxA(generalDeclaration.getUnitShownDraftxA());
			object.setShownDraftxF(FormatData.convertToDouble(generalDeclaration.getShownDraftxF()));
			object.setUnitShownDraftxF(generalDeclaration.getUnitShownDraftxF());
			object.setDwt(FormatData.convertToDouble(generalDeclaration.getDWT()));
			object.setUnitDWT(generalDeclaration.getDWTUNIT());
			object.setTimeOfPilotOnBoard(FormatData.parseStringToDate(generalDeclaration.getTimeOfPilotOnBoard()));
			object.setTimeOfPORTArrival(FormatData.parseStringToDate(generalDeclaration.getTimeOfPORTArrival()));
			object.setTugBoat(generalDeclaration.getTugBoat());
			object.setDoField(generalDeclaration.getDO());
			object.setFo(generalDeclaration.getFO());
			object.setFw(generalDeclaration.getFW());
			
			object.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
						
			String cargoDescription = "";
			StringBuilder cargoDescription_C1_XEP = new StringBuilder();
			StringBuilder cargoDescription_C1_DO = new StringBuilder();
			StringBuilder cargoDescription_C1_VC = new StringBuilder();
			StringBuilder cargoDescription_C2_VC = new StringBuilder();
			StringBuilder cargoDescription_C2_DO = new StringBuilder();
			StringBuilder cargoDescription_C2_XEP = new StringBuilder();
			StringBuilder cargoDescription_C3_XEP = new StringBuilder();
			StringBuilder cargoDescription_C3_DO = new StringBuilder();
			StringBuilder cargoDescription_C3_VC = new StringBuilder();
			StringBuilder cargoDescription_C4 = new StringBuilder();
			StringBuilder cargoDescription_C5 = new StringBuilder();
			StringBuilder cargoDescription_C6_XEP = new StringBuilder();
			StringBuilder cargoDescription_C6_DO = new StringBuilder();
			StringBuilder cargoDescription_C6_VC = new StringBuilder();
			
			List<GeneralDeclaration.CargoList> cargolist = generalDeclaration.getCargoList();
			
			if (cargolist != null && cargolist.size() > 0) {
				for (GeneralDeclaration.CargoList item : cargolist) {
					TempCargoItems details = new TempCargoItems();
					details.setRequestCode(header.getReference().getMessageId());
					details.setDocumentName(FormatData.convertToLong(generalDeclaration.getDocumentName()));
					details.setDocumentYear(FormatData.convertToInt(generalDeclaration.getDocumentYear()));
					details.setCargoCategory(item.getCategory());
					details.setCargoType(item.getType());
					details.setCargoCode(item.getName());
					details.setDescription(item.getDescription());
					details.setModifiedDate(new Date());
					
					if (Validator.isNotNull(item.getQuantity())) {
						if (item.getQuantity().endsWith(".00")) {
							details.setQuantity(FormatData.convertToDouble(item.getQuantity().replace(".00", "")));
						} else {
							details.setQuantity(FormatData.convertToDouble(item.getQuantity()));
						}
					}
					details.setUnit(StringUtil.trim(item.getUnit()));

					try {
						String cargoCategory = details.getCargoCategory().trim();
						String tmp = "";
						
						if (Validator.isNotNull(details.getCargoType()) && Validator.isNotNull(details.getUnit()))
						{
							tmp = DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName()
								+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, details.getCargoCode()).getName()
								+ "  " + details.getDescription()  + "  " + details.getQuantity()
								+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, details.getUnit()).getName()
								+ " \n ";
						}
						else if (Validator.isNotNull(details.getCargoType()) && details.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
						{	
							tmp = DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName();
						}
						
						TempCargoItemsLocalServiceUtil.addTempCargoItems(details);
						
						if (cargoCategory.equalsIgnoreCase("C1_XEP"))
						{
							cargoDescription_C1_XEP.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C1_DO"))
						{
							cargoDescription_C1_DO.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C1_VC"))
						{
							cargoDescription_C1_VC.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C2_VC"))
						{
							cargoDescription_C2_VC.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C2_DO"))
						{
							cargoDescription_C2_DO.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C2_XEP"))
						{
							cargoDescription_C2_XEP.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C3_XEP"))
						{
							cargoDescription_C3_XEP.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C3_DO"))
						{
							cargoDescription_C3_DO.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C3_VC"))
						{
							cargoDescription_C3_VC.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C6_XEP"))
						{
							cargoDescription_C6_XEP.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C6_DO"))
						{
							cargoDescription_C6_DO.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C6_VC"))
						{
							cargoDescription_C6_VC.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C4"))
						{
							cargoDescription_C4.append(tmp);
						} else if (cargoCategory.equalsIgnoreCase("C5"))
						{
							cargoDescription_C5.append(tmp);
						}
					}catch (SystemException e) {
						log.error(e.getMessage());
					};
					
				}
			}
			
			StringBuilder cargoDescription_XK = new StringBuilder();
			StringBuilder cargoDescription_NK = new StringBuilder();
			StringBuilder cargoDescription_ND = new StringBuilder();
			StringBuilder cargoDescription_TC = new StringBuilder();
			StringBuilder cargoDescription_QC = new StringBuilder();
			
			if (Validator.isNotNull(cargoDescription_C1_XEP.toString()))
			{				
				cargoDescription_XK.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C1_XEP").getName() + ": " + "\n" + cargoDescription_C1_XEP.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C1_DO.toString()))
			{				
				cargoDescription_XK.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C1_DO").getName() + ": " + "\n" + cargoDescription_C1_DO.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C1_VC.toString()))
			{				
				cargoDescription_XK.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C1_VC").getName() + ": " + "\n" + cargoDescription_C1_VC.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C2_VC.toString()))
			{				
				cargoDescription_NK.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C2_VC").getName() + ": " + "\n" + cargoDescription_C2_VC.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C2_DO.toString()))
			{				
				cargoDescription_NK.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C2_DO").getName() + ": " + "\n" + cargoDescription_C2_DO.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C2_XEP.toString()))
			{				
				cargoDescription_NK.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C2_XEP").getName() + ": " + "\n" + cargoDescription_C2_XEP.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C3_XEP.toString()))
			{				
				cargoDescription_ND.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C3_XEP").getName() + ": " + "\n" + cargoDescription_C3_XEP.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C3_DO.toString()))
			{				
				cargoDescription_ND.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C3_DO").getName() + ": " + "\n" + cargoDescription_C3_DO.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C3_VC.toString()))
			{				
				cargoDescription_ND.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C3_VC").getName() + ": " + "\n" + cargoDescription_C3_VC.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C4.toString()))
			{	
				cargoDescription_QC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C4").getName() + "\n");
				cargoDescription_QC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C4").getDescription() + "\n");
				cargoDescription_QC.append(cargoDescription_C4.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C5.toString()))
			{				
				cargoDescription_QC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C5").getName() + "\n");
				cargoDescription_QC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C5").getDescription() + "\n");
				cargoDescription_QC.append(cargoDescription_C5.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C6_XEP.toString()))
			{				
				cargoDescription_TC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C6_XEP").getName() + ": " + "\n" + cargoDescription_C6_XEP.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C6_DO.toString()))
			{				
				cargoDescription_TC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C6_DO").getName() + ": " + "\n" + cargoDescription_C6_DO.toString() + "\n");
						
			}
			
			if (Validator.isNotNull(cargoDescription_C6_VC.toString()))
			{				
				cargoDescription_TC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C6_VC").getName() + ": " + "\n" + cargoDescription_C6_VC.toString() + "\n");
						
			}
			
			if(Validator.isNotNull(cargoDescription_XK.toString())) {
				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "XK").getName();
				cargoDescription += "\n";
				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "XK").getDescription();
				cargoDescription += "\n";
				cargoDescription += cargoDescription_XK;
				cargoDescription += "\n";
			}
			
			if(Validator.isNotNull(cargoDescription_NK.toString())) {
				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "NK").getName();
				cargoDescription += "\n";
				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "NK").getDescription();
				cargoDescription += "\n";
				cargoDescription += cargoDescription_NK;
				cargoDescription += "\n";
			}
			
			if(Validator.isNotNull(cargoDescription_ND.toString())) {
				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "ND").getName();
				cargoDescription += "\n";
				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "ND").getDescription();
				cargoDescription += "\n";
				cargoDescription += cargoDescription_ND;
				cargoDescription += "\n";
			}
			
			if(Validator.isNotNull(cargoDescription_TC.toString())) {
				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "TC").getName();
				cargoDescription += "\n";
				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "TC").getDescription();
				cargoDescription += "\n";
				cargoDescription += cargoDescription_TC;
				cargoDescription += "\n";
			}
			
			if(Validator.isNotNull(cargoDescription_QC.toString())) {
				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "QC").getName();
				cargoDescription += "\n";
				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "QC").getDescription();
				cargoDescription += "\n";
				cargoDescription += cargoDescription_QC;
				cargoDescription += "\n";
			}
			
			if (Validator.isNotNull(cargoDescription))
			{
				object.setBriefDescriptionOfTheCargo(cargoDescription);
			}
			else 
			{
				object.setBriefDescriptionOfTheCargo(generalDeclaration.getBriefDescriptionOfTheCargo());
			}
			
			object.setPlaceOfReception(generalDeclaration.getPlaceOfReception());
			object.setNameOfShipOwners(generalDeclaration.getNameOfShipOwners());
			object.setAddressOfShipOwners(generalDeclaration.getAddressOfShipOwners());
			
			if(generalDeclaration.getWaste01() != null) {
				insert2TempWasteList("waste01", ConfigurationManager.getStrProp("vn.gt.waste01"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(generalDeclaration.getDocumentName()), 
						FormatData.convertToInt(generalDeclaration.getDocumentYear()), 
						FormatData.convertToDouble(generalDeclaration.getWaste01().getWeight()), 
						generalDeclaration.getWaste01().getUnit());
			}
			
			if(generalDeclaration.getWaste02() != null) {
				insert2TempWasteList("waste02", ConfigurationManager.getStrProp("vn.gt.waste02"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(generalDeclaration.getDocumentName()), 
						FormatData.convertToInt(generalDeclaration.getDocumentYear()), 
						FormatData.convertToDouble(generalDeclaration.getWaste02().getWeight()), 
						generalDeclaration.getWaste02().getUnit());
			}
			
			if(generalDeclaration.getWaste03() != null) {
				insert2TempWasteList("waste03", ConfigurationManager.getStrProp("vn.gt.waste03"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(generalDeclaration.getDocumentName()), 
						FormatData.convertToInt(generalDeclaration.getDocumentYear()), 
						FormatData.convertToDouble(generalDeclaration.getWaste03().getWeight()), 
						generalDeclaration.getWaste03().getUnit());
			}
			
			if(generalDeclaration.getWaste04() != null) {
				insert2TempWasteList("waste04", ConfigurationManager.getStrProp("vn.gt.waste04"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(generalDeclaration.getDocumentName()), 
						FormatData.convertToInt(generalDeclaration.getDocumentYear()), 
						FormatData.convertToDouble(generalDeclaration.getWaste04().getWeight()), 
						generalDeclaration.getWaste04().getUnit());
			}
			
			if(generalDeclaration.getWaste05() != null) {
				insert2TempWasteList("waste05", ConfigurationManager.getStrProp("vn.gt.waste05"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(generalDeclaration.getDocumentName()), 
						FormatData.convertToInt(generalDeclaration.getDocumentYear()), 
						FormatData.convertToDouble(generalDeclaration.getWaste05().getWeight()), 
						generalDeclaration.getWaste05().getUnit());
			}
			
			if(generalDeclaration.getWaste06() != null) {
				insert2TempWasteList("waste06", ConfigurationManager.getStrProp("vn.gt.waste06"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(generalDeclaration.getDocumentName()), 
						FormatData.convertToInt(generalDeclaration.getDocumentYear()), 
						FormatData.convertToDouble(generalDeclaration.getWaste06().getWeight()), 
						generalDeclaration.getWaste06().getUnit());
			}
			
			TempGeneralDeclarationLocalServiceUtil.addTempGeneralDeclaration(object);
			
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return false;
	}
	
	public void insert2TempDocument(GeneralDeclaration object, Header header) throws Exception {
	
		TempDocument tempDocument = new TempDocument();
		
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));
		tempDocument.setDocumentName(FormatData.convertToLong(object.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(object.getDocumentYear()));
		tempDocument.setUserCreated(object.getUserCreated());
		
		tempDocument.setShipName(object.getNameOfShip());
		tempDocument.setShipTypeCode(object.getShipTypeCode());
		tempDocument.setImo(object.getIMONumber());
		tempDocument.setCallSign(object.getCallSign());
		
		tempDocument.setStateCode(object.getFlagStateOfShip());
		tempDocument.setShipCaptain(object.getNameOfMaster());
		
		tempDocument.setGrt(FormatData.convertToDouble(object.getGrossTonnage()));
		tempDocument.setUnitGRT(object.getGrossTonnageUnit());
		tempDocument.setNt(FormatData.convertToDouble(object.getNetTonnage()));
		tempDocument.setUnitNT(object.getNetTonnageUnit());
		
		tempDocument.setLastModifiedDate(new Date());
		
		tempDocument.setNameOfShipownersAgents(object.getNameOfShipownersAgents());
		
		BusinessUtils.insert2TempDocument(tempDocument, header, object);
	}
	
	private void insert2TempWasteList(String typeCode, String typeName, String requestCode, long documentName, 
			int documentYear, double weight, String unit) throws Exception {
		long id = CounterLocalServiceUtil.increment(TempWasteList.class.getName());
		TempWasteList waste = TempWasteListLocalServiceUtil.createTempWasteList(id);
		waste.setTypeCode(typeCode);
		waste.setTypeName(typeName);
		waste.setRequestCode(requestCode);
		waste.setDocumentName(documentName);
		waste.setDocumentYear(documentYear);
		waste.setWeight(weight);
		waste.setUnit(unit);
		
		TempWasteListLocalServiceUtil.updateTempWasteList(waste);
	}
	
}