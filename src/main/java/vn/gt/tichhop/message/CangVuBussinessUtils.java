package vn.gt.tichhop.message;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.kernel.exception.SystemException;


import com.fds.flex.common.utility.string.StringUtil;
import com.fds.flex.common.ultility.Validator;

import vn.gt.constant.Constants;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
// son_vh bo sung 20160119
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempDocument;




import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderChanelLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultHistoryMinistry;
import com.fds.nsw.nghiepvu.model.ResultMinistry;


import vn.gt.dao.result.service.ResultHistoryMinistryLocalServiceUtil;
import vn.gt.dao.result.service.ResultMinistryLocalServiceUtil;
import vn.gt.portlet.business.Constans;
import vn.gt.utils.FormatData;
import vn.gt.utils.document.DocumentUtils;
import vn.gt.utils.validation.HeaderValidation;
import vn.gt.utils.validation.PermissionForTransitValidation;
import vn.gt.utils.validation.PortClearanceValidation;
import vn.gt.utils.validation.ShiftingOrderValidation;
import vn.nsw.Header;
import vn.nsw.model.PermissionForTransit;
import vn.nsw.model.PortClearance;
import vn.nsw.model.ShiftingOrder;
import vn.nsw.model.ShiftingOrder.Chanel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CangVuBussinessUtils {

	
	
	public static void insertOrUpdateResultMinistry(TempDocument tempDocument, String function, String name, String division, String organization,
			String remarks) {
		try {
			log.debug("===insertOrUpdateResultMinistry===function===" + function);
			List<ResultMinistry> resultMinistrys = ResultMinistryLocalServiceUtil.findByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(
					(new Long(tempDocument.getDocumentName())).intValue(), tempDocument.getDocumentYear(), tempDocument.getRequestCode(),
					MessageType.HO_SO);
			ResultMinistry resultMinistry = null;
			if (resultMinistrys != null && resultMinistrys.size() > 0) {
				resultMinistry = resultMinistrys.get(0);
			}
			if (resultMinistry == null) {
				log.debug("insertOrUpdateResultMinistry ");
				resultMinistry = new ResultMinistry();
				setValue2ResultMinistry(tempDocument, resultMinistry, function, name, division, organization, remarks);
				ResultMinistryLocalServiceUtil.addResultMinistry(resultMinistry);
			} else {
				setValue2ResultMinistry(tempDocument, resultMinistry, function, name, division, organization, remarks);
				ResultMinistryLocalServiceUtil.updateResultMinistry(resultMinistry);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static void setValue2ResultMinistry(TempDocument tempDocument, ResultMinistry resultMinistry, String function, String name,
			String division, String organization, String remarks) {
		
		resultMinistry.setBusinessTypeCode(MessageType.HO_SO);
		
		resultMinistry.setDocumentName(tempDocument.getDocumentName());
		resultMinistry.setDocumentYear(tempDocument.getDocumentYear());
		resultMinistry.setLatestDate(new Date());
		resultMinistry.setRequestCode(tempDocument.getRequestCode());
		resultMinistry.setResponse(function);
		resultMinistry.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_MOI);
		resultMinistry.setMinistryCode(Constans.BGTVT);
		
		resultMinistry.setOfficerName(name);
		resultMinistry.setDivision(division);
		resultMinistry.setOrganization(organization);
		resultMinistry.setRemarks(remarks);
		
	}
	
	public static void setValue2HistoryMinistry(TempDocument tempDocument, ResultHistoryMinistry resultMinistry, String function, String name,
			String division, String organization, String remarks) {
		resultMinistry.setBusinessTypeCode(MessageType.HO_SO);
		
		resultMinistry.setDocumentName(tempDocument.getDocumentName());
		resultMinistry.setDocumentYear(tempDocument.getDocumentYear());
		resultMinistry.setLatestDate(new Date());
		resultMinistry.setRequestCode(tempDocument.getRequestCode());
		resultMinistry.setResponse(function);
		resultMinistry.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_MOI);
		resultMinistry.setMinistryCode(Constans.BGTVT);
		
		resultMinistry.setOfficerName(name);
		resultMinistry.setDivision(division);
		resultMinistry.setOrganization(organization);
		resultMinistry.setRemarks(remarks);
	}
	
	public static void insertResultHistoryMinistry(TempDocument tempDocument, String function, String name, String division, String organization,
			String remarks) {
		try {
			log.debug("insertOrUpdateResultMinistry ");
			
			ResultHistoryMinistry resultMinistry = new ResultHistoryMinistry();
			resultMinistry.setBusinessTypeCode(MessageType.HO_SO);
			resultMinistry.setDocumentName(tempDocument.getDocumentName());
			resultMinistry.setDocumentYear(tempDocument.getDocumentYear());
			resultMinistry.setLatestDate(new Date());
			// resultMinistry.setRemarks(remarks)
			resultMinistry.setRequestCode(tempDocument.getRequestCode());
			resultMinistry.setResponse(function);
			// resultMinistry.setOfficerName(officerName)
			setValue2HistoryMinistry(tempDocument, resultMinistry, function, name, division, organization, remarks);
			ResultHistoryMinistryLocalServiceUtil.addResultHistoryMinistry(resultMinistry);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public boolean validationDataCangVuToHaiQuan(Header header, List<Object> liObjects, String requestDirection) {
		boolean resultStatus = true;
		try {
			HeaderValidation headerValidation = new HeaderValidation();
			if (!headerValidation.validation(header, requestDirection)) {
				resultStatus = false;
			}
			for (Object object : liObjects) {
				if (object instanceof ShiftingOrder) {
					ShiftingOrderValidation shiftingOrder = new ShiftingOrderValidation();
					if (!shiftingOrder.validation(ShiftingOrder.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
					
				} else if (object instanceof PortClearance) {
					PortClearanceValidation portClearance = new PortClearanceValidation();
					if (!portClearance.validation(PortClearance.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
					
				} else if (object instanceof PermissionForTransit) {
					PermissionForTransitValidation permissionForTransit = new PermissionForTransitValidation();
					if (!permissionForTransit.validation(PermissionForTransit.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return resultStatus;
	}
	
	public boolean insert2ShiftingOrder(ShiftingOrder shiftingOrder, Header header) throws Exception {
		try {
			
			String uuid = UUID.randomUUID().toString();
			
			IssueShiftingOrder issue = new IssueShiftingOrder();
			
			issue.setRequestCode(uuid);
			issue.setNumberShiftingOrder(shiftingOrder.getNumberShiftingOrder());
			issue.setDocumentName(FormatData.convertToLong(shiftingOrder.getDocumentName()));
			issue.setDocumentYear(FormatData.convertToInt(shiftingOrder.getDocumentYear()));
			//Add by Dungnv
			if(shiftingOrder.getPortofAuthority().length() >= 3){
				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeReference(shiftingOrder.getPortofAuthority());
				if(dmMaritime != null){
					issue.setPortofAuthority(dmMaritime.getMaritimeCode());
				}
			}else{
				issue.setPortofAuthority(shiftingOrder.getPortofAuthority());
			}
			//Edit by Dungnv
			//issue.setPortofAuthority(shiftingOrder.getPortofAuthority());
			issue.setNameOfShip(shiftingOrder.getNameOfShip());
			issue.setFlagStateOfShip(shiftingOrder.getFlagStateOfShip());
			issue.setPortHarbourCode(shiftingOrder.getPortHarbourCode());
			issue.setRepresentative(shiftingOrder.getRepresentative());
			issue.setAnchoringPortCode(shiftingOrder.getAnchoringPortCode());
			issue.setAnchoringPortWharfCode(shiftingOrder.getAnchoringPortWharfCode());
					
			// issue.setShiftingPortWharfCode(shiftingOrder.getShiftingPortWharfCode());
			issue.setShiftingDate(FormatData.parseStringToDate(shiftingOrder.getShiftingDate()));
			
			//Add by Dungnv
			issue.setIsApproval(1);
			
			issue.setReasonToShift(shiftingOrder.getReasonToShift());
			issue.setIssueDate(FormatData.parseStringToDate(shiftingOrder.getIssueDate()));
			issue.setDirectorOfMaritime(shiftingOrder.getDirectorOfMaritimeAdministration());
			issue.setCertificateNo(shiftingOrder.getCertificateNo());
			issue.setRequestState(MessageType.CHAP_NHAN_BAN_KHAI);
			// son_vh sua doi 20160119
			issue.setVersionNo(header.getReference().getVersion());
			
			issue.setShownDraftxF(shiftingOrder.getShownDraftxF());
			issue.setUnitShownDraftxF(shiftingOrder.getUnitShownDraftxF());
			issue.setShownDraftxA(shiftingOrder.getShownDraftxA());
			issue.setUnitShownDraftxA(shiftingOrder.getUnitShownDraftxA());
			issue.setLoa(shiftingOrder.getLOA());
			issue.setLoaunit(shiftingOrder.getLOAUNIT());
			issue.setDwt(shiftingOrder.getDWT());
			issue.setDwtunit(shiftingOrder.getDWTUNIT());
			issue.setTugBoat(shiftingOrder.getTugBoat());
			issue.setFrom(shiftingOrder.getFrom());
			issue.setTo(shiftingOrder.getTo());
			issue.setFlagStateOfShip(shiftingOrder.getFlagStateOfShip());
			issue.setTaxCodeOfShipownersAgents(shiftingOrder.getTaxCodeOfShipownersAgents());
			issue.setNameOfShipownersAgents(shiftingOrder.getNameOfShipownersAgents());
			issue.setShipAgencyAddress(shiftingOrder.getShipAgencyAddress());
			issue.setShipAgencyPhone(shiftingOrder.getShipAgencyPhone());
			issue.setShipAgencyFax(shiftingOrder.getShipAgencyFax());
			issue.setShipAgencyEmail(shiftingOrder.getShipAgencyEmail());
			
			if(shiftingOrder.getAttachedFile() != null 
					&& Validator.isNotNull(shiftingOrder.getAttachedFile().getFileURL())) {
				
				long fileId = DocumentUtils.uploadFileEntryFromURL(shiftingOrder.getAttachedFile().getFileURL());
				issue.setAttachedFile(fileId);
				issue.setStampStatus(2);
			}
			
			IssueShiftingOrderLocalServiceUtil.addIssueShiftingOrder(issue);
			
			List<IssueShiftingOrder> issueShiftingOrders = IssueShiftingOrderLocalServiceUtil.findByRequestCode(uuid);
			
			IssueShiftingOrder issueShiftingOrder = null;
			if(issueShiftingOrders != null && issueShiftingOrders.size() > 0) {
				issueShiftingOrder = issueShiftingOrders.get(0);
			}
			
			if(issueShiftingOrder != null) {
				long issueShiftingOrderId = issueShiftingOrder.getId();
				
				List<Chanel> chanels = shiftingOrder.getChanel();
				
				if(chanels != null && chanels.size() > 0) {
					int cnt = 1;
					for(Chanel chanel : chanels) {
						IssueShiftingOrderChanelLocalServiceUtil
								.addShiftingOrderChanel(issueShiftingOrderId,
										chanel.getChanelCode(), chanel.getChanelName(), cnt);
						cnt++;
					}
				}
			}
			
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	
	public void insertIssueTable(Header header, List<Object> liObjects) {
		try {
			log.debug("insertIssueTable   ");
			
			for (Object object : liObjects) {
				if (object instanceof ShiftingOrder) {					
					insert2ShiftingOrder(ShiftingOrder.class.cast(object), header);
					log.debug("insertIssueTable  ShiftingOrder ");
				}
				
				if (object instanceof PortClearance) {
					insert2PortClearance(PortClearance.class.cast(object), header);
					log.debug("insertIssueTable  PortClearance ");
				}
				// son_vh bo sung 20160119
				if (object instanceof PermissionForTransit) {
					insert2PermissionForTransit(PermissionForTransit.class.cast(object), header);
					log.debug("insertIssueTable  PermissionForTransit ");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	// son_vh bo sung 20160119
	public boolean insert2PermissionForTransit(PermissionForTransit permissionForTransit, Header header) throws Exception {
		try {
			log.debug("insert2PortClearance  " + permissionForTransit.getCallSign());
			IssuePermissionForTransit issue = new IssuePermissionForTransit();
			
			String requestCode = UUID.randomUUID().toString();
			
			issue.setRequestCode(requestCode);
			issue.setNumberPermissionForTransit(permissionForTransit.getNumberPermissionForTransit());
			issue.setDocumentName(FormatData.convertToLong(permissionForTransit.getDocumentName()));
			issue.setDocumentYear(FormatData.convertToInt(permissionForTransit.getDocumentYear()));
			//Add by Dungnv
			if(permissionForTransit.getPortofAuthority().length() >= 3){
				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeReference(permissionForTransit.getPortofAuthority());
				if(dmMaritime != null){
					issue.setPortofAuthority(dmMaritime.getMaritimeCode());
				}
			}else{
				issue.setPortofAuthority(permissionForTransit.getPortofAuthority());
			}
			//Edit by Dungnv
			//issue.setPortofAuthority(permissionForTransit.getPortofAuthority());
			issue.setNameOfShip(permissionForTransit.getNameOfShip());
			issue.setFlagStateOfShip(permissionForTransit.getFlagStateOfShip());
			issue.setGt(FormatData.convertToDouble(permissionForTransit.getGT()));
			issue.setNumberOfCrews(FormatData.convertToInt(permissionForTransit.getNumberOfCrews()));
			issue.setNumberOfPassengers(FormatData.convertToInt(permissionForTransit.getNumberOfPassengers()));
			issue.setCallSign(permissionForTransit.getCallSign());
			issue.setNameOfMaster(permissionForTransit.getNameOfMaster());
			issue.setRepresentative(permissionForTransit.getRepresentative());
			
			//Add by Dungnv
			issue.setIsApproval(1);
			
			issue.setTransitCargo(permissionForTransit.getTransitCargo());
			issue.setCargoUnit(permissionForTransit.getCargoUnit());
			issue.setVolumeCargo(FormatData.convertToDouble(permissionForTransit.getVolumeCargo()));
						
			issue.setPermittedTransitFrom(permissionForTransit.getPermittedTransitFrom());
			issue.setPermittedTransitTo(permissionForTransit.getPermittedTransitTo());
			issue.setTimeOfDeparture(FormatData.parseStringToDate(permissionForTransit.getTimeOfDeparture()));
						
			issue.setValidUntil(FormatData.parseStringToDate(permissionForTransit.getValidUntil()));
			issue.setDateOfSign(FormatData.parseStringToDate(permissionForTransit.getDateOfSign()));
			issue.setDirectorOfMaritime(permissionForTransit.getDirectorOfMaritimeAdministration());
			issue.setCertificateNo(permissionForTransit.getCertificateNo());
			issue.setVersionNo(header.getReference().getVersion());
			issue.setRequestState(MessageType.CHAP_NHAN_BAN_KHAI);
			
			if(permissionForTransit.getAttachedFile() != null 
					&& Validator.isNotNull(permissionForTransit.getAttachedFile().getFileURL())) {
				
				long fileId = DocumentUtils.uploadFileEntryFromURL(permissionForTransit.getAttachedFile().getFileURL());
				issue.setAttachedFile(fileId);
				issue.setStampStatus(2);
			}
			
			String cargoDescription = "";
			StringBuilder cargoDescription_C1_XEP = new StringBuilder();
			StringBuilder cargoDescription_C1_DO = new StringBuilder();
			StringBuilder cargoDescription_C1_VC = new StringBuilder();
			StringBuilder cargoDescription_C2_VC = new StringBuilder();
			StringBuilder cargoDescription_C3_XEP = new StringBuilder();
			StringBuilder cargoDescription_C3_DO = new StringBuilder();
			StringBuilder cargoDescription_C3_VC = new StringBuilder();
			StringBuilder cargoDescription_C4 = new StringBuilder();
			StringBuilder cargoDescription_C5 = new StringBuilder();
			StringBuilder cargoDescription_C6_XEP = new StringBuilder();
			StringBuilder cargoDescription_C6_DO = new StringBuilder();
			StringBuilder cargoDescription_C6_VC = new StringBuilder();
			
			List<PermissionForTransit.CargoList> cargolist = permissionForTransit.getCargoList();
			
			if (cargolist != null && cargolist.size() > 0) {
				for (PermissionForTransit.CargoList item : cargolist) {
					TempCargoItems details = new TempCargoItems();
					details.setRequestCode(requestCode);
					details.setDocumentName(FormatData.convertToLong(permissionForTransit.getDocumentName()));
					details.setDocumentYear(FormatData.convertToInt(permissionForTransit.getDocumentYear()));
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
				issue.setCargoDescription(cargoDescription);
			}
			
			IssuePermissionForTransitLocalServiceUtil.addIssuePermissionForTransit(issue);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	
	public boolean insert2PortClearance(PortClearance portClearance, Header header) throws Exception {
		try {
			log.debug("insert2PortClearance  " + portClearance.getCallSign());
			IssuePortClearance issue = new IssuePortClearance();
			
			String requestCode = UUID.randomUUID().toString();
			
			issue.setRequestCode(requestCode);
			issue.setNumberPortClearance(portClearance.getNumberPortClearance());
			issue.setDocumentName(FormatData.convertToLong(portClearance.getDocumentName()));
			issue.setDocumentYear(FormatData.convertToInt(portClearance.getDocumentYear()));
			//Add by Dungnv
			if(portClearance.getPortofAuthority().length() >= 3){
				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeReference(portClearance.getPortofAuthority());
				if(dmMaritime != null){
					issue.setPortofAuthority(dmMaritime.getMaritimeCode());
				}
			}else{
				issue.setPortofAuthority(portClearance.getPortofAuthority());
			}
			//Edit by Dungnv
			//issue.setPortofAuthority(portClearance.getPortofAuthority());
			issue.setNameOfShip(portClearance.getNameOfShip());
			issue.setFlagStateOfShip(portClearance.getFlagStateOfShip());
			issue.setNumberOfCrews(FormatData.convertToInt(portClearance.getNumberOfCrews()));
			issue.setNumberOfPassengers(FormatData.convertToInt(portClearance.getNumberOfPassengers()));
			issue.setCallSign(portClearance.getCallSign());
			issue.setNameOfMaster(portClearance.getNameOfMaster());
			issue.setCargo(portClearance.getCargo());
			issue.setVolumeCargo(FormatData.convertToDouble(portClearance.getVolumeCargo()));
			issue.setRepresentative(portClearance.getRepresentative());
			issue.setCargoUnit(portClearance.getCargoUnit());
			issue.setTransitCargo(portClearance.getTransitCargo());
			
			issue.setVolumeTransitCargo(FormatData.convertToDouble(portClearance.getVolumeTransitCargo()));
			issue.setTransitCargoUnit(portClearance.getTransitCargoUnit());
			
			issue.setTimeOfDeparture(FormatData.parseStringToDate(portClearance.getTimeOfDeparture()));
			
			issue.setNextPortOfCallCode(portClearance.getNextPortOfCallCode());
			
			issue.setValidUntil(FormatData.parseStringToDate(portClearance.getValidUntil()));
			issue.setIssueDate(FormatData.parseStringToDate(portClearance.getIssueDate()));
			issue.setSignDate(FormatData.parseStringToDate(portClearance.getIssueDate()));
			issue.setDirectorOfMaritime(portClearance.getDirectorOfMaritimeAdministration());
			
			//add by Dungnv
			issue.setIsApproval(1);
						
			issue.setCertificateNo(portClearance.getCertificateNo());
			issue.setGt(FormatData.convertToDouble(portClearance.getGT()));
			issue.setRequestState(MessageType.CHAP_NHAN_BAN_KHAI);
			// son_vh sua doi 20160119
			issue.setVersionNo(header.getReference().getVersion());
			
			if(portClearance.getAttachedFile() != null 
					&& Validator.isNotNull(portClearance.getAttachedFile().getFileURL())) {
				
				long fileId = DocumentUtils.uploadFileEntryFromURL(portClearance.getAttachedFile().getFileURL());
				issue.setAttachedFile(fileId);
				issue.setIsApproval(1);
				issue.setStampStatus(2);
			}
			
			String cargoDescription = "";
			StringBuilder cargoDescription_C1_XEP = new StringBuilder();
			StringBuilder cargoDescription_C1_DO = new StringBuilder();
			StringBuilder cargoDescription_C1_VC = new StringBuilder();
			StringBuilder cargoDescription_C2_VC = new StringBuilder();
			StringBuilder cargoDescription_C3_XEP = new StringBuilder();
			StringBuilder cargoDescription_C3_DO = new StringBuilder();
			StringBuilder cargoDescription_C3_VC = new StringBuilder();
			StringBuilder cargoDescription_C4 = new StringBuilder();
			StringBuilder cargoDescription_C5 = new StringBuilder();
			StringBuilder cargoDescription_C6_XEP = new StringBuilder();
			StringBuilder cargoDescription_C6_DO = new StringBuilder();
			StringBuilder cargoDescription_C6_VC = new StringBuilder();
			
			List<PortClearance.CargoList> cargolist = portClearance.getCargoList();
			
			if (cargolist != null && cargolist.size() > 0) {
				for (PortClearance.CargoList item : cargolist) {
					TempCargoItems details = new TempCargoItems();
					details.setRequestCode(requestCode);
					details.setDocumentName(FormatData.convertToLong(portClearance.getDocumentName()));
					details.setDocumentYear(FormatData.convertToInt(portClearance.getDocumentYear()));
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
								//Add by Dungnv
								+ " (" +
								DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getDescription()
								+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, details.getCargoCode()).getDescription()
								+ "  "  + details.getQuantity()
								+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, details.getUnit()).getDescription()
								+ ") "
								
								+ " \n ";
						}
						else if (Validator.isNotNull(details.getCargoType()) && details.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
						{	
							tmp = DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName()
								//Add by Dungnv
								+ " (" 
								+ DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getDescription()
								+ ") "
								+ " \n ";
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
					}
					
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
				issue.setCargoDescription(cargoDescription);
			}
						
			IssuePortClearanceLocalServiceUtil.addIssuePortClearance(issue);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return false;
	}
}