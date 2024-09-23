package vn.gt.tichhop.message.giaothong2haiquan;

import java.util.List;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.SystemException;




import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssueAcceptance;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.IssueAcceptanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.portlet.document.FileUtils;
import vn.gt.tichhop.message.TrangThaiBanKhaiXuatCanh;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.nsw.model.PortClearance;
import vn.nsw.model.inland.InlandPortClearance;
import vn.nsw.model.inland.PTTNDAcceptance;
import vn.nsw.model.inland.PTTNDPortclearance;

@Slf4j
public class PortClearance2Xml {
	

	
	public PortClearance insert2PortClearance(long documentName, int documentYear) {
		
		PortClearance item = new PortClearance();
		
		IssuePortClearance object = null;
		List<IssuePortClearance> liIssuePortClearances = IssuePortClearanceLocalServiceUtil
				.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
						KeyParams.ORDER_BY_DESC);
		
//		List<IssuePortClearance> liIssuePortClearances = IssuePortClearanceLocalServiceUtil
//				.findIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName, documentYear, TrangThaiBanKhaiXuatCanh.KHAI_MOI);
		
		if (liIssuePortClearances != null && liIssuePortClearances.size() > 0) {
			object = liIssuePortClearances.get(0);
		}

		if (object != null) {
			item = convertPortClearance(object);
		}
		
		return item;
	}
	
	public PortClearance convertXMLPortClearance(String requestCode) {
		
		PortClearance item = new PortClearance();
		
		IssuePortClearance object = IssuePortClearanceLocalServiceUtil.getByRequestCode(requestCode);
		log.info("=================PortClearance===DATA GUI MESSAGE===RequestCode====" + requestCode);
		
		if (object != null) {
			item = convertPortClearance(object);
		}
		
		return item;
	}
	
	private PortClearance convertPortClearance(IssuePortClearance object) {
		PortClearance item = new PortClearance();
		
		if (object != null) {
			try {
				item.setNumberPortClearance(String.valueOf(object.getNumberPortClearance()));
				item.setDocumentName(String.valueOf(object.getDocumentName()));
				item.setDocumentYear(String.valueOf(object.getDocumentYear()));
				
				if (object.getPortofAuthority()!= null && object.getPortofAuthority().length() > 0) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(object.getPortofAuthority());
					if (dmMaritime != null) {
						object.setPortofAuthority(dmMaritime.getMaritimeReference());
					} 
				}
				
				item.setPortofAuthority(object.getPortofAuthority());
				item.setNameOfShip(String.valueOf(object.getNameOfShip()));
				item.setFlagStateOfShip(String.valueOf(object.getFlagStateOfShip()));
				item.setNumberOfCrews(String.valueOf(object.getNumberOfCrews()));
				item.setNumberOfPassengers(String.valueOf(object.getNumberOfPassengers()));
				item.setCallSign(String.valueOf(object.getCallSign()));
				item.setNameOfMaster(String.valueOf(object.getNameOfMaster()));
				if (object.getCargo().length() > 0)
				{
					item.setCargo(String.valueOf(object.getCargo()));
				}
				if (object.getCargo().length() > 0)
				{
					item.setVolumeCargo(String.valueOf(object.getVolumeCargo()));
				}
				if (object.getCargo().length() > 0)
				{
					item.setCargoUnit(String.valueOf(object.getCargoUnit()));
				}
				
				item.setTransitCargo(String.valueOf(object.getTransitCargo()));
				item.setVolumeTransitCargo(String.valueOf(object.getVolumeTransitCargo()));
				item.setTransitCargoUnit(String.valueOf(object.getTransitCargoUnit()));
				item.setTimeOfDeparture(FormatData.parseDateToTring(object.getTimeOfDeparture()));
				item.setNextPortOfCallCode(String.valueOf(object.getNextPortOfCallCode()));
				item.setValidUntil(FormatData.parseDateToTring(object.getValidUntil()));
				item.setIssueDate(FormatData.parseDateToTring(object.getIssueDate()));
				item.setDirectorOfMaritimeAdministration(String.valueOf(object.getDirectorOfMaritime()));
				item.setCertificateNo(object.getCertificateNo());
				item.setGT(String.valueOf(object.getGt()));
				item.setRepresentative(object.getRepresentative());
				
				vn.nsw.model.PortClearance.AttachedFile attachedFilePDFKy = new vn.nsw.model.PortClearance.AttachedFile();
				long kySoId = object.getAttachedFile();
				
				if (kySoId > 0) {
					attachedFilePDFKy.setAttachedTypeCode("60");
					attachedFilePDFKy.setAttachedTypeName("MAU_FILE_KY");
					attachedFilePDFKy.setAttachedDocName("MAU_FILE_KY");
					attachedFilePDFKy.setAttachedNote("MAU_FILE_KY");					
					//attachedFilePDFKy.setBase64FileContent(vn.gt.portlet.document.FileUtils.getBase64FileContent(kySoId));
					attachedFilePDFKy.setFileURL(vn.gt.portlet.document.FileUtils.getFullFileURL(kySoId));
					attachedFilePDFKy.setFullFileName(FileUtils.getNameByFileEntryId(kySoId));

					item.setAttachedFile(attachedFilePDFKy);
				}
				
				List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYear(object.getDocumentName(), object.getDocumentYear());
				
				if (items != null && items.size() > 0){
					
					for (TempCargoItems cargoItem: items) {
						
						if (object.getRequestCode().equalsIgnoreCase(cargoItem.getRequestCode())) {
							PortClearance.CargoList cargoSpec = new PortClearance.CargoList();
							cargoSpec.setCategory(cargoItem.getCargoCategory());
							cargoSpec.setType(cargoItem.getCargoType());
							cargoSpec.setName(cargoItem.getCargoCode());
							cargoSpec.setDescription(cargoItem.getDescription());
							cargoSpec.setUnit(cargoItem.getUnit());
							cargoSpec.setQuantity(String.valueOf(cargoItem.getQuantity()));
							
							item.getCargoList().add(cargoSpec);
						}
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		return item;
	}
	
	public PTTNDAcceptance insert2PTTNDAcceptance (long documentName, int documentYear) {
		
	PTTNDAcceptance item = new PTTNDAcceptance();
		
		IssueAcceptance object = null;
		List<IssueAcceptance> liIssueAcceptances = IssueAcceptanceLocalServiceUtil
				.findIssueAcceptanceByDocumentYearAndDocumentYearAndRequestState(documentName, documentYear, TrangThaiBanKhaiXuatCanh.KHAI_MOI);
		if (liIssueAcceptances != null && liIssueAcceptances.size() > 0) {
			object = liIssueAcceptances.get(0);
		}
		
		if (object != null) {
			try {
				item.setNumberPortClearance(String.valueOf(object.getNumberPortClearance()));
				item.setDocumentName(String.valueOf(object.getDocumentName()));
				item.setDocumentYear(String.valueOf(object.getDocumentYear()));
				item.setPortofAuthority(object.getPortofAuthority());
				item.setNameOfShip(String.valueOf(object.getNameOfShip()));
				item.setFlagStateOfShip(String.valueOf(object.getFlagStateOfShip()));
				item.setNumberOfCrews(String.valueOf(object.getNumberOfCrews()));
				item.setNumberOfPassengers(String.valueOf(object.getNumberOfPassengers()));
				item.setCallSign(String.valueOf(object.getCallSign()));
				item.setNameOfMaster(String.valueOf(object.getNameOfMaster()));
				item.setCargo(String.valueOf(object.getCargo()));
				item.setVolumeCargo(String.valueOf(object.getVolumeCargo()));
				item.setCargoUnit(String.valueOf(object.getCargoUnit()));
				item.setCargoDescription(String.valueOf(object.getCargoDescription()));
				item.setTransitCargo(String.valueOf(object.getTransitCargo()));
				item.setVolumeTransitCargo(String.valueOf(object.getVolumeTransitCargo()));
				item.setTransitCargoUnit(String.valueOf(object.getTransitCargoUnit()));
				item.setTimeOfDeparture(FormatData.parseDateToTring(object.getTimeOfDeparture()));
				item.setPortCode(String.valueOf(object.getPortCode()));
				item.setNextPortOfCallCode(String.valueOf(object.getPortHarbourCode()));
				
				item.setValidUntil(FormatData.parseDateToTring(object.getValidUntil()));
				item.setIssueDate(FormatData.parseDateToTring(object.getIssueDate()));
				item.setDirectorOfMaritimeAdministration(String.valueOf(object.getDirectorOfMaritime()));
				item.setCertificateNo(object.getCertificateNo());
				item.setGT(String.valueOf(object.getGt()));
				item.setDWT(String.valueOf(object.getDwt()));
				item.setRepresentative(object.getRepresentative());
				
				
				return item;
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return null;
	}
	
//	PTTNDAcceptance
	public PTTNDAcceptance convertXMLPTTNDAcceptance(String requestCode) throws SystemException {
		
		PTTNDAcceptance item = new PTTNDAcceptance();
		
		IssueAcceptance object = IssueAcceptanceLocalServiceUtil.fetchByRequestCode(requestCode);
		log.info("=================Acceptance===DATA GUI MESSAGE===RequestCode====" + requestCode);
		
		if (object != null) {
			try {
				item.setNumberPortClearance(String.valueOf(object.getNumberPortClearance()));
				item.setDocumentName(String.valueOf(object.getDocumentName()));
				item.setDocumentYear(String.valueOf(object.getDocumentYear()));
				item.setPortofAuthority(object.getPortofAuthority());
				item.setNameOfShip(String.valueOf(object.getNameOfShip()));
				item.setFlagStateOfShip(String.valueOf(object.getFlagStateOfShip()));
				item.setNumberOfCrews(String.valueOf(object.getNumberOfCrews()));
				item.setNumberOfPassengers(String.valueOf(object.getNumberOfPassengers()));
				item.setCallSign(String.valueOf(object.getCallSign()));
				item.setNameOfMaster(String.valueOf(object.getNameOfMaster()));
				item.setCargo(String.valueOf(object.getCargo()));
				item.setVolumeCargo(String.valueOf(object.getVolumeCargo()));
				item.setCargoUnit(String.valueOf(object.getCargoUnit()));
				item.setCargoDescription(String.valueOf(object.getCargoDescription()));
				item.setTransitCargo(String.valueOf(object.getTransitCargo()));
				item.setVolumeTransitCargo(String.valueOf(object.getVolumeTransitCargo()));
				item.setTransitCargoUnit(String.valueOf(object.getTransitCargoUnit()));
				item.setTimeOfDeparture(FormatData.parseDateToTring(object.getTimeOfDeparture()));
				
				item.setNextPortOfCallCode(String.valueOf(object.getPortHarbourCode()));
				item.setValidUntil(FormatData.parseDateToTring(object.getValidUntil()));
				item.setIssueDate(FormatData.parseDateToTring(object.getIssueDate()));
				item.setDirectorOfMaritimeAdministration(String.valueOf(object.getDirectorOfMaritime()));
				item.setCertificateNo(object.getCertificateNo());
				item.setGT(String.valueOf(object.getGt()));
				item.setDWT(String.valueOf(object.getDwt()));
				item.setRepresentative(object.getRepresentative());	
				
				// SMS
				log.info("=================Acceptance===DATA GUI MESSAGE===object.getPortCode()====" +object.getPortCode());
				TempDocument tempDocument = null;
				tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(object.getDocumentName(), object.getDocumentYear());
				if (tempDocument.getShipTypeCode().equalsIgnoreCase("SMS"))
				{

					//PortHarbour la ben cang,
					DmPortHarbour lstPortHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(object.getPortHarbourCode());
					DmPortWharf lstPortWharf=  DmPortWharfLocalServiceUtil.getByPortWharfCode(object.getPortWharfCode());
					if (Validator.isNotNull(lstPortHarbour))
					{
						item.setPortCode(lstPortHarbour.getPortHarbourNameVN()) ;					
					} 
					
					if ((Validator.isNotNull(lstPortHarbour)) && (Validator.isNotNull(lstPortHarbour)))
					{
						item.setPortCode(lstPortHarbour.getPortHarbourNameVN() + ", " + lstPortWharf.getPortWharfNameVN()) ;					
					}
					
					log.info("=================Acceptance===DATA GUI MESSAGE===item.setPortCode====" +item.getPortCode());
				}
				else
				{
					item.setPortCode(String.valueOf(object.getPortCode()));
				}
				vn.nsw.model.inland.PTTNDAcceptance.AttachedFile attachedFilePDFKy = new vn.nsw.model.inland.PTTNDAcceptance.AttachedFile();
				long kySoId = object.getAttachedFile();
				
				if (kySoId > 0) {
					attachedFilePDFKy.setAttachedTypeCode("81");
					attachedFilePDFKy.setAttachedTypeName("MAU_FILE_KY");
					attachedFilePDFKy.setAttachedDocName("MAU_FILE_KY");
					attachedFilePDFKy.setAttachedNote("MAU_FILE_KY");					
					attachedFilePDFKy.setBase64FileContent(vn.gt.portlet.document.FileUtils.getBase64FileContent(kySoId));
					//attachedFilePDFKy.setFileURL(vn.gt.portlet.document.FileUtils.getFullFileURL(kySoId));
					attachedFilePDFKy.setFullFileName(FileUtils.getNameByFileEntryId(kySoId));

					item.setAttachedFile(attachedFilePDFKy);
				}
				
				return item;
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return null;
	}
//	PTTNDPortclearance
	public PTTNDPortclearance convertXMLPTTNDPortclearance(String requestCode) throws SystemException {
		
		PTTNDPortclearance item = new PTTNDPortclearance();
		
		IssuePortClearance object = IssuePortClearanceLocalServiceUtil.findByRequestCode(requestCode);
		log.info("=================PortClearance===DATA GUI MESSAGE===RequestCode====" + requestCode);
		
		if (object != null) {
			try {
				item.setNumberPortClearance(String.valueOf(object.getNumberPortClearance()));
				item.setDocumentName(String.valueOf(object.getDocumentName()));
				item.setDocumentYear(String.valueOf(object.getDocumentYear()));
				item.setPortofAuthority(object.getPortofAuthority());
				item.setNameOfShip(String.valueOf(object.getNameOfShip()));
				item.setFlagStateOfShip(String.valueOf(object.getFlagStateOfShip()));
				item.setNumberOfCrews(String.valueOf(object.getNumberOfCrews()));
				item.setNumberOfPassengers(String.valueOf(object.getNumberOfPassengers()));
				item.setCallSign(String.valueOf(object.getCallSign()));
				item.setNameOfMaster(String.valueOf(object.getNameOfMaster()));
				item.setCargo(String.valueOf(object.getCargo()));
				item.setVolumeCargo(String.valueOf(object.getVolumeCargo()));
				item.setCargoDescription(String.valueOf(object.getCargoDescription()));
				item.setCargoUnit(String.valueOf(object.getCargoUnit()));
				item.setTransitCargo(String.valueOf(object.getTransitCargo()));
				item.setVolumeTransitCargo(String.valueOf(object.getVolumeTransitCargo()));
				item.setTransitCargoUnit(String.valueOf(object.getTransitCargoUnit()));
				item.setTimeOfDeparture(FormatData.parseDateToTring(object.getTimeOfDeparture()));
				item.setNextPortOfCallCode(String.valueOf(object.getNextPortOfCallCode()));
				
				item.setNextProvinceCode(String.valueOf(object.getNextProvinceCode()));
				item.setMaritimePortCode(String.valueOf(object.getMaritimePortCode()));
				item.setPortHarbourCode(String.valueOf(object.getPortHarbourCode()));
				item.setPortCode(String.valueOf(object.getPortCode()));
				item.setNextMaritimePortCode(String.valueOf(object.getNextMaritimePortCode()));
				item.setNextPortRegionCode(String.valueOf(object.getNextPortRegionCode()));
				item.setNextPortHarbourCode(String.valueOf(object.getNextPortHarbourCode()));
				item.setNextPortWharfCode(String.valueOf(object.getNextPortWharfCode()));
				
				item.setValidUntil(FormatData.parseDateToTring(object.getValidUntil()));
				item.setIssueDate(FormatData.parseDateToTring(object.getIssueDate()));
				item.setDirectorOfMaritimeAdministration(String.valueOf(object.getDirectorOfMaritime()));
				item.setCertificateNo(object.getCertificateNo());
				item.setGT(String.valueOf(object.getGt()));
				item.setDWT(String.valueOf(object.getDwt()));
				item.setRepresentative(object.getRepresentative());
				
				vn.nsw.model.inland.PTTNDPortclearance.AttachedFile attachedFilePDFKy = new vn.nsw.model.inland.PTTNDPortclearance.AttachedFile();
				long kySoId = object.getAttachedFile();
				
				if (kySoId > 0) {
					attachedFilePDFKy.setAttachedTypeCode("61");
					attachedFilePDFKy.setAttachedTypeName("MAU_FILE_KY");
					attachedFilePDFKy.setAttachedDocName("MAU_FILE_KY");
					attachedFilePDFKy.setAttachedNote("MAU_FILE_KY");					
					attachedFilePDFKy.setBase64FileContent(vn.gt.portlet.document.FileUtils.getBase64FileContent(kySoId));
					//attachedFilePDFKy.setFileURL(vn.gt.portlet.document.FileUtils.getFullFileURL(kySoId));
					attachedFilePDFKy.setFullFileName(FileUtils.getNameByFileEntryId(kySoId));

					item.setAttachedFile(attachedFilePDFKy);
				}
				return item;
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return null;
	}
	public InlandPortClearance convertXMLPortClearanceInland(String requestCode) {
			
			InlandPortClearance item = new InlandPortClearance();
			
			IssuePortClearance object = IssuePortClearanceLocalServiceUtil.getByRequestCode(requestCode);
			log.info("=================PortClearance===DATA GUI MESSAGE===RequestCode====" + requestCode);
			
			if (object != null) {
				try {
					//item.setNumberPortClearance(String.valueOf(object.getNumberPortClearance()));
					item.setDocumentName(String.valueOf(object.getDocumentName()));
					item.setDocumentYear(String.valueOf(object.getDocumentYear()));
					item.setPortofAuthority(object.getPortofAuthority());
					item.setNameOfShip(String.valueOf(object.getNameOfShip()));
					item.setFlagStateOfShip(String.valueOf(object.getFlagStateOfShip()));
					item.setNumberOfCrews(String.valueOf(object.getNumberOfCrews()));
					item.setNumberOfPassengers(String.valueOf(object.getNumberOfPassengers()));
					item.setCallSign(String.valueOf(object.getCallSign()));
					item.setNameOfMaster(String.valueOf(object.getNameOfMaster()));
					
					if (object.getCargo().length() > 0)
					{
						item.setCargo(String.valueOf(object.getCargo()));
					}
					if (object.getCargo().length() > 0)
					{
						item.setVolumeCargo(String.valueOf(object.getVolumeCargo()));
					}
					if (object.getCargo().length() > 0)
					{
						item.setCargoUnit(String.valueOf(object.getCargoUnit()));
					}
					//item.setTransitCargo(String.valueOf(object.getTransitCargo()));
					//item.setVolumeTransitCargo(String.valueOf(object.getVolumeTransitCargo()));
					//item.setTransitCargoUnit(String.valueOf(object.getTransitCargoUnit()));
					item.setTimeOfDeparture(FormatData.parseDateToTring(object.getTimeOfDeparture()));
					item.setNextPortOfCallCode(String.valueOf(object.getNextPortOfCallCode()));
					item.setValidUntil(FormatData.parseDateToTring(object.getValidUntil()));
					item.setIssueDate(FormatData.parseDateToTring(object.getIssueDate()));
					item.setDirectorOfMaritimeAdministration(String.valueOf(object.getDirectorOfMaritime()));
					item.setCertificateNo(object.getCertificateNo());
					item.setGT(String.valueOf(object.getGt()));
					item.setRepresentative(object.getRepresentative());
					
					String cargoDescription = "";
					
							
					List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYearAndRequestCode(object.getDocumentName(), object.getDocumentYear(), requestCode);
					if (items != null && items.size() > 0){
						
						for (TempCargoItems cargoItem: items) {
							
							if (object.getRequestCode().equalsIgnoreCase(cargoItem.getRequestCode())) {
								InlandPortClearance.CargoList cargoSpec = new InlandPortClearance.CargoList();
								cargoSpec.setCategory(cargoItem.getCargoCategory());
								cargoSpec.setType(cargoItem.getCargoType());
								cargoSpec.setName(cargoItem.getCargoCode());
								cargoSpec.setDescription(cargoItem.getDescription());
								cargoSpec.setUnit(cargoItem.getUnit());
								cargoSpec.setQuantity(String.valueOf(cargoItem.getQuantity()));
								
								item.getCargoList().add(cargoSpec);
							}
							
						}
					}
					

					vn.nsw.model.inland.InlandPortClearance.AttachedFile attachedFilePDFKy = new vn.nsw.model.inland.InlandPortClearance.AttachedFile();
					long kySoId = object.getAttachedFile();
					
					if (kySoId > 0) {
						attachedFilePDFKy.setAttachedTypeCode("60");
						attachedFilePDFKy.setAttachedTypeName("MAU_FILE_KY");
						attachedFilePDFKy.setAttachedDocName("MAU_FILE_KY");
						attachedFilePDFKy.setAttachedNote("MAU_FILE_KY");					
						//attachedFilePDFKy.setBase64FileContent(vn.gt.portlet.document.FileUtils.getBase64FileContent(kySoId));
						attachedFilePDFKy.setFileURL(vn.gt.portlet.document.FileUtils.getFullFileURL(kySoId));
						attachedFilePDFKy.setFullFileName(FileUtils.getNameByFileEntryId(kySoId));

						item.setAttachedFile(attachedFilePDFKy);
					}

					return item;
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
			return null;
		}	
}
