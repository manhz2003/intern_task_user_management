package vn.gt.tichhop.message.haiquan2giaothong;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import vn.gt.constant.Constants;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempWasteList;



import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempWasteListLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.PageType;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.NoticeOfArrival;
import vn.nsw.model.NoticeOfArrival.BriefParticularsOfVoyage;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.kernel.exception.SystemException;


import com.fds.flex.common.utility.string.StringUtil;
import com.fds.flex.common.ultility.Validator;
@Slf4j
public class NoticeOfArrival2Temp {
	
	
	
	public boolean insert2Temp(NoticeOfArrival noticeOfArrival, Header header) throws Exception {
		try {
			
			insert2TempDocument(noticeOfArrival, header);
			
			TempNoticeShipMessage object = new TempNoticeShipMessage();
			
			object.setRequestCode(header.getReference().getMessageId());
			object.setDocumentName(FormatData.convertToLong(noticeOfArrival.getDocumentName()));
			object.setDocumentReference(FormatData.convertToLong(noticeOfArrival.getDocumentReference()));
			object.setDocumentYear(FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
			object.setUserCreated(noticeOfArrival.getUserCreated());
			object.setIsArrival(FormatData.convertToInt(noticeOfArrival.getIsArrival()));
			object.setShipName(noticeOfArrival.getNameOfShip());
			object.setShipTypeCode(noticeOfArrival.getShipTypeCode());
			object.setStateCode(noticeOfArrival.getFlagStateOfShip());
			object.setShipCaptain(noticeOfArrival.getNameOfMaster());
			object.setImo(noticeOfArrival.getIMONumber());
			object.setGrt(FormatData.convertToDouble(noticeOfArrival.getGT()));
			object.setDwt(FormatData.convertToDouble(noticeOfArrival.getDWT()));
			object.setUnitGRT(noticeOfArrival.getGTUNIT());
			object.setUnitDWT(noticeOfArrival.getDWTUNIT());
			object.setCallSign(noticeOfArrival.getCallSign());
			object.setVoyageNumber(noticeOfArrival.getVoyageNumber());
			object.setArrivalDate(FormatData.parseStringToDate(noticeOfArrival.getTimeOfArrival()));
			object.setArrivalPortCode(noticeOfArrival.getPortOfArrivalCode());
			object.setPortHarbourCode(noticeOfArrival.getPortHarbourCode());
			object.setPortRegionCode(noticeOfArrival.getPortRegionCode());
			object.setPortWharfCode(noticeOfArrival.getPortWharfCode());
			object.setPortGoingToCode(noticeOfArrival.getLastPortOfCallCode());
			object.setNameOfShipOwners(noticeOfArrival.getNameOfShipOwners());
			object.setAddressOfShipOwners(noticeOfArrival.getAddressOfShipOwners());
			object.setShipOwnerStateCode(noticeOfArrival.getShipOwnerStateCode());
			object.setShipOwnerPhone(noticeOfArrival.getShipOwnerPhone());
			object.setShipOwnerFax(noticeOfArrival.getShipOwnerFax());
			object.setShipOwnerEmail(noticeOfArrival.getShipOwnerEmail());
			object.setLoa(FormatData.convertToDouble(noticeOfArrival.getLOA()));
			object.setBreadth(FormatData.convertToDouble(noticeOfArrival.getBreadth()));
			object.setClearanceHeight(FormatData.convertToDouble(noticeOfArrival.getClearanceHeight()));
			object.setShownDraftxF(FormatData.convertToDouble(noticeOfArrival.getShownDraftxF()));
			object.setShownDraftxA(FormatData.convertToDouble(noticeOfArrival.getShownDraftxA()));
			object.setUnitLOA(noticeOfArrival.getLOAUNIT());
			object.setUnitBreadth(noticeOfArrival.getBreadthUnit());
			object.setUnitClearanceHeight(noticeOfArrival.getClearanceHeightUnit());
			object.setUnitShownDraftxF(noticeOfArrival.getUnitShownDraftxF());
			object.setUnitShownDraftxA(noticeOfArrival.getUnitShownDraftxA());
			object.setCertificateOfRegistryNumber(noticeOfArrival.getCertificateOfRegistryNumber());
			object.setCertificateOfRegistryDate(FormatData.parseStringToDate(noticeOfArrival.getCertificateOfRegistryDate()));
			object.setCertificateOfRegistryPortName(noticeOfArrival.getCertificateOfRegistryPortName());
			object.setShipAgencyCode(noticeOfArrival.getTaxCodeOfShipownersAgents());
			object.setShipAgencyAddress(noticeOfArrival.getShipAgencyAddress());
			object.setShipAgencyPhone(noticeOfArrival.getShipAgencyPhone());
			object.setShipAgencyFax(noticeOfArrival.getShipAgencyFax());
			object.setShipAgencyEmail(noticeOfArrival.getShipAgencyEmail());
			object.setPurposeCode(noticeOfArrival.getPurposeCode());
			object.setCrewNumber(FormatData.convertToLong(noticeOfArrival.getNumberOfCrew()));
			object.setPassengerNumber(FormatData.convertToLong(noticeOfArrival.getNumberOfPassengers()));
			object.setUnitQuantityofCargo(noticeOfArrival.getUnitQuantityofCargo());
			object.setTypeOfCargo(noticeOfArrival.getTypeOfCargo());
			object.setOtherPersons(FormatData.convertToInt(noticeOfArrival.getOtherPersons()));
			object.setRemarks(noticeOfArrival.getRemarks());
			object.setSignPlace(noticeOfArrival.getSignPlace());
			object.setSignDate(FormatData.parseStringToDate(noticeOfArrival.getSignDate()));
			object.setMasterSigned(FormatData.convertToInt(noticeOfArrival.getMasterSigned()));
			object.setNameOfShipAgent(noticeOfArrival.getNameOfShipownersAgents());
			
			object.setNetTonnage(FormatData.convertToDouble(noticeOfArrival.getNetTonnage()));
			object.setNetTonnageUnit(noticeOfArrival.getNetTonnageUnit());
			object.setPositionOfShipInPort(noticeOfArrival.getPositionOfShipInPort());
			BriefParticularsOfVoyage obj = noticeOfArrival.getBriefParticularsOfVoyage();
			if(obj != null) {
				object.setPreviousPortsOfCall(obj.getPreviousPortsOfCall());
				object.setSubsequentPortsOfCall(obj.getSubsequentPortsOfCall());
				object.setDischargedPorts(obj.getDischargedPorts());
			}
			object.setRemainingCargo(noticeOfArrival.getRemainingCargo());
			object.setShipRequirementsInTermsOfWaste(noticeOfArrival.getShipRequirementsInTermsOfWaste());
			object.setNumberCargoDeclaration(FormatData.convertToInt(noticeOfArrival.getNumberCargoDeclaration()));
			object.setNumberShipStoreDeclaration(FormatData.convertToInt(noticeOfArrival.getNumberShipStoreDeclaration()));
			object.setNumberCrewList(FormatData.convertToInt(noticeOfArrival.getNumberCrewList()));
			object.setNumberPassengerList(FormatData.convertToInt(noticeOfArrival.getNumberPassengerList()));
			object.setNumberCrewEffectsDeclaration(FormatData.convertToInt(noticeOfArrival.getNumberCrewEffectsDeclaration()));
			object.setNumberHealthMaritimeDeclaration(FormatData.convertToInt(noticeOfArrival.getNumberHealthMaritimeDeclaration()));
			object.setPortClearanceNo(noticeOfArrival.getPortClearanceNo());
			object.setDoField(noticeOfArrival.getDO());
			object.setFo(noticeOfArrival.getFO());
			object.setFw(noticeOfArrival.getFW());

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
			List<NoticeOfArrival.CargoList> cargolist = noticeOfArrival.getCargoList();
			
			if (cargolist != null && cargolist.size() > 0) {
				for (NoticeOfArrival.CargoList item : cargolist) {
					TempCargoItems details = new TempCargoItems();
					details.setRequestCode(header.getReference().getMessageId());
					details.setDocumentName(FormatData.convertToLong(noticeOfArrival.getDocumentName()));
					details.setDocumentYear(FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
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
				object.setQuantityOfCargo(cargoDescription);
			}
			else 
			{
				object.setQuantityOfCargo(noticeOfArrival.getQuantityOfCargo());
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
			
			}/* else if (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG) {
				object.setNoticeShipType(String.valueOf(PageType.TYPE_XAC_BAO_TAU_DEN_CANG));
				object.setConfirmTime(FormatData.convertToInt(header.getReference().getVersion()) + 1);
				
			} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH) {
				object.setNoticeShipType(String.valueOf(PageType.TYPE_XAC_BAO_TAU_DEN_CANG));
				object.setConfirmTime(FormatData.convertToInt(header.getReference().getVersion()) + 1);
				
			}*/ else {
				object.setNoticeShipType(String.valueOf(String.valueOf(PageType.TYPE_THONG_BAO_TAU_DEN_CANG)));
			}
			
			object.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			
			object.setTimeOfPORTArrival(FormatData.parseStringToDate(noticeOfArrival.getTimeOfPORTArrival()));
			object.setTimeOfPilotOnBoard(FormatData.parseStringToDate(noticeOfArrival.getTimeOfPilotOnBoard()));
			object.setTugBoat(noticeOfArrival.getTugBoat());
			
			object.setPlaceOfReception(noticeOfArrival.getPlaceOfReception());
			
			if(noticeOfArrival.getWaste01() != null) {
				insert2TempWasteList("waste01", ConfigurationManager.getStrProp("vn.gt.waste01"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(noticeOfArrival.getDocumentName()), 
						FormatData.convertToInt(noticeOfArrival.getDocumentYear()), 
						FormatData.convertToDouble(noticeOfArrival.getWaste01().getWeight()), 
						noticeOfArrival.getWaste01().getUnit());
			}
			
			if(noticeOfArrival.getWaste02() != null) {
				insert2TempWasteList("waste02", ConfigurationManager.getStrProp("vn.gt.waste02"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(noticeOfArrival.getDocumentName()), 
						FormatData.convertToInt(noticeOfArrival.getDocumentYear()), 
						FormatData.convertToDouble(noticeOfArrival.getWaste02().getWeight()), 
						noticeOfArrival.getWaste02().getUnit());
			}
			
			if(noticeOfArrival.getWaste03() != null) {
				insert2TempWasteList("waste03", ConfigurationManager.getStrProp("vn.gt.waste03"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(noticeOfArrival.getDocumentName()), 
						FormatData.convertToInt(noticeOfArrival.getDocumentYear()), 
						FormatData.convertToDouble(noticeOfArrival.getWaste03().getWeight()), 
						noticeOfArrival.getWaste03().getUnit());
			}
			
			if(noticeOfArrival.getWaste04() != null) {
				insert2TempWasteList("waste04", ConfigurationManager.getStrProp("vn.gt.waste04"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(noticeOfArrival.getDocumentName()), 
						FormatData.convertToInt(noticeOfArrival.getDocumentYear()), 
						FormatData.convertToDouble(noticeOfArrival.getWaste04().getWeight()), 
						noticeOfArrival.getWaste04().getUnit());
			}
			
			if(noticeOfArrival.getWaste05() != null) {
				insert2TempWasteList("waste05", ConfigurationManager.getStrProp("vn.gt.waste05"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(noticeOfArrival.getDocumentName()), 
						FormatData.convertToInt(noticeOfArrival.getDocumentYear()), 
						FormatData.convertToDouble(noticeOfArrival.getWaste05().getWeight()), 
						noticeOfArrival.getWaste05().getUnit());
			}
			
			if(noticeOfArrival.getWaste06() != null) {
				insert2TempWasteList("waste06", ConfigurationManager.getStrProp("vn.gt.waste06"), 
						header.getReference().getMessageId(), 
						FormatData.convertToLong(noticeOfArrival.getDocumentName()), 
						FormatData.convertToInt(noticeOfArrival.getDocumentYear()), 
						FormatData.convertToDouble(noticeOfArrival.getWaste06().getWeight()), 
						noticeOfArrival.getWaste06().getUnit());
			}
			
			TempNoTiceShipMessageLocalServiceUtil.addTempNoTiceShipMessage(object);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	
	public void insert2TempDocument(NoticeOfArrival noticeOfArrival, Header header) throws Exception {
		
		TempDocument tempDocument = new TempDocument();
		
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentName(FormatData.convertToLong(noticeOfArrival.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(noticeOfArrival.getDocumentYear()));
		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));
		tempDocument.setUserCreated(noticeOfArrival.getUserCreated());
		tempDocument.setShipAgencyCode(noticeOfArrival.getTaxCodeOfShipownersAgents());
		tempDocument.setShipName(noticeOfArrival.getNameOfShip());
		tempDocument.setShipTypeCode(noticeOfArrival.getShipTypeCode());
		tempDocument.setStateCode(noticeOfArrival.getFlagStateOfShip());
		tempDocument.setShipCaptain(noticeOfArrival.getNameOfMaster());
		tempDocument.setImo(noticeOfArrival.getIMONumber());
		tempDocument.setGrt(FormatData.convertToLong(noticeOfArrival.getGT()));
		tempDocument.setNt(FormatData.convertToLong(noticeOfArrival.getNetTonnage()));
		tempDocument.setDwt(FormatData.convertToLong(noticeOfArrival.getDWT()));
		tempDocument.setUnitGRT(noticeOfArrival.getGTUNIT());
		tempDocument.setUnitNT(noticeOfArrival.getNetTonnageUnit());
		tempDocument.setUnitDWT(noticeOfArrival.getDWTUNIT());
		tempDocument.setCallSign(noticeOfArrival.getCallSign());
		// tempDocument.setPreDocumentName(noticeOfArrival.get());
		// tempDocument.setCreatedDate(noticeOfArrival.get());
		// tempDocument.setLastModifiedDate(noticeOfArrival.get());
		tempDocument.setLastModifiedDate(new Date());
		tempDocument.setNameOfShipownersAgents(noticeOfArrival.getNameOfShipownersAgents());
		
		BusinessUtils.insert2TempDocument(tempDocument, header, noticeOfArrival);
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
