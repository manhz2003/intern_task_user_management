package vn.gt.tichhop.message.giaothong2haiquan;

import java.util.List;




import com.fds.nsw.nghiepvu.model.DmMaritime;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.portlet.document.FileUtils;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.nsw.model.PortEntryPermit;

@Slf4j
public class PortEntryPermit2Xml2 {
	
	
	
	public PortEntryPermit insert2PortEntryPermit(long documentName, int documentYear) {
		
		PortEntryPermit item = new PortEntryPermit();
		
		IssuePortClearance object = null;
		List<IssuePortClearance> liIssuePortClearances = IssuePortClearanceLocalServiceUtil
				.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
						KeyParams.ORDER_BY_DESC);
		
		if (liIssuePortClearances != null && liIssuePortClearances.size() > 0) {
			object = liIssuePortClearances.get(0);
		}

		if (object != null) {
			item = convertPortEntryPermit(object);
		}
		
		return item;
	}
	
	public PortEntryPermit convertXMLPortEntryPermit(String requestCode) {
		
		PortEntryPermit item = new PortEntryPermit();
		
		IssuePortClearance object = IssuePortClearanceLocalServiceUtil.getByRequestCode(requestCode);
		log.info("=================PortClearance===DATA GUI MESSAGE===RequestCode====" + requestCode);
		
		if (object != null) {
			item = convertPortEntryPermit(object);
		}
		
		return item;
	}
	
	private PortEntryPermit convertPortEntryPermit(IssuePortClearance object) {
		PortEntryPermit item = new PortEntryPermit();
		
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
				
				vn.nsw.model.PortEntryPermit.AttachedFile attachedFilePDFKy = new vn.nsw.model.PortEntryPermit.AttachedFile();
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
							PortEntryPermit.CargoList cargoSpec = new PortEntryPermit.CargoList();
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
}
