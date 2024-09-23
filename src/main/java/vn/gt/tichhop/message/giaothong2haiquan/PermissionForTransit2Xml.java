package vn.gt.tichhop.message.giaothong2haiquan;

import java.util.List;




import com.fds.nsw.nghiepvu.model.DmMaritime;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.portlet.document.FileUtils;
import vn.gt.tichhop.message.TrangThaiBanKhaiQuaCanh;
import vn.gt.utils.FormatData;
import vn.nsw.model.PermissionForTransit;
import vn.nsw.model.inland.InlandPortClearance;

@Slf4j
public class PermissionForTransit2Xml {
	
	
	
	public PermissionForTransit insert2PermissionForTransit(long documentName, int  documentYear) throws Exception {
	
		PermissionForTransit item = new PermissionForTransit();
		IssuePermissionForTransit object = IssuePermissionForTransitLocalServiceUtil.findIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(documentName, documentYear, TrangThaiBanKhaiQuaCanh.KHAI_MOI);
		if (object != null) {
			try {
				item.setNumberPermissionForTransit(object.getNumberPermissionForTransit());
				item.setDocumentName(String.valueOf(object.getDocumentName()));
				item.setDocumentYear(String.valueOf(object.getDocumentYear()));
				if (object.getPortofAuthority()!= null && object.getPortofAuthority().length() > 0) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
							.getByMaritimeCode(object.getPortofAuthority());
					if (dmMaritime != null) {
						object.setPortofAuthority(dmMaritime.getMaritimeReference());
					} 
				}
				item.setPortofAuthority(object.getPortofAuthority());
				item.setNameOfShip(object.getNameOfShip());
				item.setFlagStateOfShip(object.getFlagStateOfShip());
				item.setGT(String.valueOf(object.getGt()));
				item.setCertificateNo(object.getCertificateNo());
				item.setNumberOfCrews(String.valueOf(object.getNumberOfCrews()));
				item.setNumberOfPassengers(String.valueOf(object.getNumberOfPassengers()));
				item.setCallSign(object.getCallSign());
				item.setNameOfMaster(object.getNameOfMaster());
				item.setTransitCargo(object.getTransitCargo());
				item.setVolumeCargo(String.valueOf(object.getVolumeCargo()));
				item.setCargoUnit(object.getCargoUnit());				
				item.setPermittedTransitFrom((object.getPermittedTransitFrom()));
				item.setPermittedTransitTo((object.getPermittedTransitTo()));
				item.setTimeOfDeparture(FormatData.parseDateToTring(object.getTimeOfDeparture()));
				item.setValidUntil(FormatData.parseDateToTring(object.getValidUntil()));
				item.setDateOfSign(FormatData.parseDateToTring(object.getDateOfSign()));
				item.setUserCreated(object.getUserCreated());
				item.setDirectorOfMaritimeAdministration(object.getDirectorOfMaritime());
				item.setCreatedDate(FormatData.parseDateToTring(object.getCreatedDate()));
				item.setRepresentative(object.getRepresentative());
				return item;
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public PermissionForTransit insert2PerForTransitByVersion(long documentName, int documentYear, String versionPerForTransit) throws Exception {
		
		PermissionForTransit item = new PermissionForTransit();
		IssuePermissionForTransit object = IssuePermissionForTransitLocalServiceUtil.getByDocumentNameAndDocumentYearAndVersionNo(documentName, documentYear, versionPerForTransit);
		if (object != null) {
			try {
				item.setNumberPermissionForTransit(object.getNumberPermissionForTransit());
				item.setDocumentName(String.valueOf(object.getDocumentName()));
				item.setDocumentYear(String.valueOf(object.getDocumentYear()));
				
				if (object.getPortofAuthority()!= null && object.getPortofAuthority().length() > 0) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(object.getPortofAuthority());
					if (dmMaritime != null) {
						object.setPortofAuthority(dmMaritime.getMaritimeReference());
					} 
				}
				
				item.setPortofAuthority(object.getPortofAuthority());
				item.setNameOfShip(object.getNameOfShip());
				item.setFlagStateOfShip(object.getFlagStateOfShip());
				item.setGT(String.valueOf(object.getGt()));
				item.setCertificateNo(object.getCertificateNo());
				item.setNumberOfCrews(String.valueOf(object.getNumberOfCrews()));
				item.setNumberOfPassengers(String.valueOf(object.getNumberOfPassengers()));
				item.setCallSign(object.getCallSign());
				item.setNameOfMaster(object.getNameOfMaster());
				if (object.getTransitCargo().length() > 0)
				{
					item.setTransitCargo(object.getTransitCargo());
				}
				if (object.getTransitCargo().length() > 0)
				{
					item.setVolumeCargo(String.valueOf(object.getVolumeCargo()));
				}
				if (object.getTransitCargo().length() > 0)
				{
					item.setCargoUnit(object.getCargoUnit());
				}				
				item.setPermittedTransitFrom((object.getPermittedTransitFrom()));
				item.setPermittedTransitTo((object.getPermittedTransitTo()));
				item.setTimeOfDeparture(FormatData.parseDateToTring(object.getTimeOfDeparture()));
				item.setValidUntil(FormatData.parseDateToTring(object.getValidUntil()));
				item.setDateOfSign(FormatData.parseDateToTring(object.getDateOfSign()));
				item.setUserCreated(object.getUserCreated());
				item.setDirectorOfMaritimeAdministration(object.getDirectorOfMaritime());
				item.setCreatedDate(FormatData.parseDateToTring(object.getCreatedDate()));
				item.setRepresentative(object.getRepresentative());
				
//				List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYearAndRequestCode(object.getDocumentName(), object.getDocumentYear(), object.getRequestCode());
				List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYear(object.getDocumentName(), object.getDocumentYear());
				if (items != null && items.size() > 0){
					
					for (TempCargoItems cargoItem: items) {
						PermissionForTransit.CargoList cargoSpec = new PermissionForTransit.CargoList();
						cargoSpec.setCategory(cargoItem.getCargoCategory());
						cargoSpec.setType(cargoItem.getCargoType());
						cargoSpec.setName(cargoItem.getCargoCode());
						cargoSpec.setDescription(cargoItem.getDescription());
						cargoSpec.setUnit(cargoItem.getUnit());
						cargoSpec.setQuantity(String.valueOf(cargoItem.getQuantity()));
						
						item.getCargoList().add(cargoSpec);
					}
				}
				

				vn.nsw.model.PermissionForTransit.AttachedFile attachedFilePDFKy = new vn.nsw.model.PermissionForTransit.AttachedFile();
				long kySoId = object.getAttachedFile();
				
				if (kySoId > 0) {
					attachedFilePDFKy.setAttachedTypeCode("90");
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
	
	public PermissionForTransit convertXMLPermissionForTransit(String requestCode) throws Exception {
		
		PermissionForTransit item = new PermissionForTransit();
		IssuePermissionForTransit object = IssuePermissionForTransitLocalServiceUtil.getByrequestCode(requestCode);
		log.info("=========convertXMLPermissionForTransit===RequestCode===" + requestCode + "===" + object);
		if (object != null) {
			try {
				item.setNumberPermissionForTransit(object.getNumberPermissionForTransit());
				item.setDocumentName(String.valueOf(object.getDocumentName()));
				item.setDocumentYear(String.valueOf(object.getDocumentYear()));
				
				if (object.getPortofAuthority()!= null && object.getPortofAuthority().length() > 0) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(object.getPortofAuthority());
					
					if (dmMaritime != null) {
						object.setPortofAuthority(dmMaritime.getMaritimeReference());
					} 
				}
				
				item.setPortofAuthority(object.getPortofAuthority());
				item.setNameOfShip(object.getNameOfShip());
				item.setFlagStateOfShip(object.getFlagStateOfShip());
				item.setGT(String.valueOf(object.getGt()));
				item.setCertificateNo(object.getCertificateNo());
				item.setNumberOfCrews(String.valueOf(object.getNumberOfCrews()));
				item.setNumberOfPassengers(String.valueOf(object.getNumberOfPassengers()));
				item.setCallSign(object.getCallSign());
				item.setNameOfMaster(object.getNameOfMaster());
				item.setTransitCargo(object.getTransitCargo());
				item.setVolumeCargo(String.valueOf(object.getVolumeCargo()));
				item.setCargoUnit(object.getCargoUnit());
				item.setPermittedTransitFrom(object.getPermittedTransitFrom());
				item.setPermittedTransitTo(object.getPermittedTransitTo());
				item.setTimeOfDeparture(FormatData.parseDateToTring(object.getTimeOfDeparture()));
				item.setValidUntil(FormatData.parseDateToTring(object.getValidUntil()));
				item.setDateOfSign(FormatData.parseDateToTring(object.getDateOfSign()));
				item.setUserCreated(object.getUserCreated());
				item.setDirectorOfMaritimeAdministration(object.getDirectorOfMaritime());
				item.setCreatedDate(FormatData.parseDateToTring(object.getCreatedDate()));
				item.setRepresentative(object.getRepresentative());
				
				String cargoDescription = "";
				
				
				List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYearAndRequestCode(object.getDocumentName(), object.getDocumentYear(), requestCode);
				if (items != null && items.size() > 0){
					
					for (TempCargoItems cargoItem: items) {
						PermissionForTransit.CargoList cargoSpec = new PermissionForTransit.CargoList();
						cargoSpec.setCategory(cargoItem.getCargoCategory());
						cargoSpec.setType(cargoItem.getCargoType());
						cargoSpec.setName(cargoItem.getCargoCode());
						cargoSpec.setDescription(cargoItem.getDescription());
						cargoSpec.setUnit(cargoItem.getUnit());
						cargoSpec.setQuantity(String.valueOf(cargoItem.getQuantity()));
						
						item.getCargoList().add(cargoSpec);
					}
				}
				

				vn.nsw.model.PermissionForTransit.AttachedFile attachedFilePDFKy = new vn.nsw.model.PermissionForTransit.AttachedFile();
				long kySoId = object.getAttachedFile();
				
				if (kySoId > 0) {
					attachedFilePDFKy.setAttachedTypeCode("90");
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
				e.printStackTrace();
			}
		}
		return null;
	}
}
