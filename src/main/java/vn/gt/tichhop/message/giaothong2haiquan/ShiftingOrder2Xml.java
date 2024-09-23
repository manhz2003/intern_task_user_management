package vn.gt.tichhop.message.giaothong2haiquan;

import java.util.List;

import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderChanelLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.tichhop.message.TrangThaiBanKhaiXuatCanh;
import vn.gt.utils.FormatData;
import vn.nsw.model.ShiftingOrder;
import vn.nsw.model.inland.InlandShiftingOrder;
import vn.gt.portlet.document.FileUtils;



@Slf4j
public class ShiftingOrder2Xml {
	
	
	
	public ShiftingOrder insert2ShiftingOrder(long documentName, int  documentYear) throws Exception {
	
		ShiftingOrder item = new ShiftingOrder();
		
		IssueShiftingOrder object = null;
		List<IssueShiftingOrder> lIssueShiftingOrders = IssueShiftingOrderLocalServiceUtil.findIssueShiftingOrderByDocumentYearAndDocumentYear(documentName, documentYear, TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI);
		if (lIssueShiftingOrders != null && lIssueShiftingOrders.size() > 0) {
			object = lIssueShiftingOrders.get(0);
		}
		
		if (object != null) {
			try {
				item.setNumberShiftingOrder(object.getNumberShiftingOrder());
				item.setDocumentName(String.valueOf(object.getDocumentName()));
				item.setDocumentYear(String.valueOf(object.getDocumentYear()));
				item.setPortofAuthority(object.getPortofAuthority());
				item.setNameOfShip(object.getNameOfShip());
				
				item.setAnchoringPortCode(object.getAnchoringPortCode());
				item.setAnchoringPortWharfCode(object.getAnchoringPortWharfCode());
				
				if(object.getShiftingDate() != null) {
					item.setShiftingDate(FormatData.parseDateToTring(object.getShiftingDate()));
				}
				
				item.setReasonToShift(object.getReasonToShift());
				item.setPortHarbourCode(object.getPortHarbourCode());
				item.setRepresentative(object.getRepresentative());
				item.setIssueDate(FormatData.parseDateToTring(object.getIssueDate()));
				item.setDirectorOfMaritimeAdministration(object.getDirectorOfMaritime());
				item.setCertificateNo(object.getCertificateNo());
				
				vn.nsw.model.ShiftingOrder.AttachedFile attachedFilePDFKy = new vn.nsw.model.ShiftingOrder.AttachedFile();
				long kySoId = object.getAttachedFile();
				
				if (kySoId > 0) {
					attachedFilePDFKy.setAttachedTypeCode("70");
					attachedFilePDFKy.setAttachedTypeName("MAU_FILE_KY");
					attachedFilePDFKy.setAttachedDocName("MAU_FILE_KY");
					attachedFilePDFKy.setAttachedNote("MAU_FILE_KY");					
					//attachedFilePDFKy.setBase64FileContent(vn.gt.portlet.document.FileUtils.getBase64FileContent(kySoId));
					attachedFilePDFKy.setFileURL(vn.gt.portlet.document.FileUtils.getFullFileURL(kySoId));
					attachedFilePDFKy.setFullFileName(FileUtils.getNameByFileEntryId(kySoId));

					item.setAttachedFile(attachedFilePDFKy);
				}
				
				item.setShownDraftxF(object.getShownDraftxF());
				item.setUnitShownDraftxF(object.getUnitShownDraftxF());
				item.setShownDraftxA(object.getShownDraftxA());
				item.setUnitShownDraftxA(object.getUnitShownDraftxA());
				item.setLOA(object.getLoa());
				item.setLOAUNIT(object.getLoaunit());
				item.setDWT(object.getDwt());
				item.setDWTUNIT(object.getDwtunit());
				item.setTugBoat(object.getTugBoat());
				item.setFrom(object.getFrom());
				item.setTo(object.getTo());
				item.setFlagStateOfShip(object.getFlagStateOfShip());
				item.setTaxCodeOfShipownersAgents(object.getTaxCodeOfShipownersAgents());
				item.setNameOfShipownersAgents(object.getNameOfShipownersAgents());
				item.setShipAgencyAddress(object.getShipAgencyAddress());
				item.setShipAgencyPhone(object.getShipAgencyPhone());
				item.setShipAgencyFax(object.getShipAgencyFax());
				item.setShipAgencyEmail(object.getShipAgencyEmail());
				
				List<IssueShiftingOrderChanel> listShiftingOrderChanels = IssueShiftingOrderChanelLocalServiceUtil.getShiftingOrderChanel(object.getId());
				
				if(listShiftingOrderChanels != null) {
					for (IssueShiftingOrderChanel shiftingOrderChanel : listShiftingOrderChanels) {
						vn.nsw.model.ShiftingOrder.Chanel chanel = new vn.nsw.model.ShiftingOrder.Chanel();
						chanel.setChanelCode(shiftingOrderChanel.getId().getChanelCode());
						chanel.setChanelName(shiftingOrderChanel.getChanelName());
						
						item.getChanel().add(chanel);
					}
				}
				
				return item;
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		return null;
	}
	
	/**Ham nay su dung truong hop gui lenh dieu dong n lan*/
	public ShiftingOrder insert2ShiftingOrderByVersion(long documentName, int  documentYear, String versionNo) throws Exception {
		
		ShiftingOrder item = new ShiftingOrder();
		
		IssueShiftingOrder object = IssueShiftingOrderLocalServiceUtil.getByDocumentNameAndDocumentYearAndVersionNo(documentName, documentYear, versionNo);
		
		if (object != null) {
			try {
				item.setNumberShiftingOrder(object.getNumberShiftingOrder());
				item.setDocumentName(String.valueOf(object.getDocumentName()));
				item.setDocumentYear(String.valueOf(object.getDocumentYear()));
				item.setPortofAuthority(object.getPortofAuthority());
				item.setNameOfShip(object.getNameOfShip());
				item.setFlagStateOfShip(object.getFlagStateOfShip());
				
				item.setAnchoringPortCode(object.getAnchoringPortCode());
				item.setAnchoringPortWharfCode(object.getAnchoringPortWharfCode());
				//item.setShiftingPortWharfCode(object.getShiftingPortWharfCode());
				if (object.getShiftingDate() != null)
					item.setShiftingDate(FormatData.parseDateToTring(object.getShiftingDate()));
				item.setReasonToShift(object.getReasonToShift());
				item.setPortHarbourCode(object.getPortHarbourCode());
				item.setRepresentative(object.getRepresentative());
				item.setIssueDate(FormatData.parseDateToTring(object.getIssueDate()));
				item.setDirectorOfMaritimeAdministration(object.getDirectorOfMaritime());
				item.setCertificateNo(object.getCertificateNo());
				
				vn.nsw.model.ShiftingOrder.AttachedFile attachedFilePDFKy = new vn.nsw.model.ShiftingOrder.AttachedFile();
				long kySoId = object.getAttachedFile();
				
				if (kySoId > 0) {
					attachedFilePDFKy.setAttachedTypeCode("70");
					attachedFilePDFKy.setAttachedTypeName("MAU_FILE_KY");
					attachedFilePDFKy.setAttachedDocName("MAU_FILE_KY");
					attachedFilePDFKy.setAttachedNote("MAU_FILE_KY");					
					//attachedFilePDFKy.setBase64FileContent(vn.gt.portlet.document.FileUtils.getBase64FileContent(kySoId));
					attachedFilePDFKy.setFileURL(vn.gt.portlet.document.FileUtils.getFullFileURL(kySoId));
					attachedFilePDFKy.setFullFileName(FileUtils.getNameByFileEntryId(kySoId));

					item.setAttachedFile(attachedFilePDFKy);
				}
				
				item.setShownDraftxF(object.getShownDraftxF());
				item.setUnitShownDraftxF(object.getUnitShownDraftxF());
				item.setShownDraftxA(object.getShownDraftxA());
				item.setUnitShownDraftxA(object.getUnitShownDraftxA());
				item.setLOA(object.getLoa());
				item.setLOAUNIT(object.getLoaunit());
				item.setDWT(object.getDwt());
				item.setDWTUNIT(object.getDwtunit());
				item.setTugBoat(object.getTugBoat());
				item.setFrom(object.getFrom());
				item.setTo(object.getTo());
				item.setFlagStateOfShip(object.getFlagStateOfShip());
				item.setTaxCodeOfShipownersAgents(object.getTaxCodeOfShipownersAgents());
				item.setNameOfShipownersAgents(object.getNameOfShipownersAgents());
				item.setShipAgencyAddress(object.getShipAgencyAddress());
				item.setShipAgencyPhone(object.getShipAgencyPhone());
				item.setShipAgencyFax(object.getShipAgencyFax());
				item.setShipAgencyEmail(object.getShipAgencyEmail());
				
				List<IssueShiftingOrderChanel> listShiftingOrderChanels = IssueShiftingOrderChanelLocalServiceUtil.getShiftingOrderChanel(object.getId());
				
				if(listShiftingOrderChanels != null) {
					for (IssueShiftingOrderChanel shiftingOrderChanel : listShiftingOrderChanels) {
						vn.nsw.model.ShiftingOrder.Chanel chanel = new vn.nsw.model.ShiftingOrder.Chanel();
						chanel.setChanelCode(shiftingOrderChanel.getId().getChanelCode());
						chanel.setChanelName(shiftingOrderChanel.getChanelName());
						
						item.getChanel().add(chanel);
					}
				}
				
				return item;
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return null;
	}
	
	/**Ham nay su dung truong hop gui lenh dieu dong n lan*/
	public InlandShiftingOrder insert2ShiftingOrderByVersionInland(long documentName, int  documentYear, String versionNo) throws Exception {
		
		InlandShiftingOrder item = new InlandShiftingOrder();
		
		IssueShiftingOrder object = IssueShiftingOrderLocalServiceUtil.getByDocumentNameAndDocumentYearAndVersionNo(documentName, documentYear, versionNo);
		
		if (object != null) {
			try {
				//item.setNumberShiftingOrder(object.getNumberShiftingOrder());
				item.setDocumentName(String.valueOf(object.getDocumentName()));
				item.setDocumentYear(String.valueOf(object.getDocumentYear()));
				item.setPortofAuthority(object.getPortofAuthority());
				item.setNameOfShip(object.getNameOfShip());
				//item.setFlagStateOfShip(object.getFlagStateOfShip());
				
				item.setAnchoringPortCode(object.getAnchoringPortCode());
				item.setAnchoringPortWharfCode(object.getAnchoringPortWharfCode());
				//item.setShiftingPortWharfCode(object.getShiftingPortWharfCode());
				if (object.getShiftingDate() != null)
					item.setShiftingDate(FormatData.parseDateToTring(object.getShiftingDate()));
				//item.setReasonToShift(object.getReasonToShift());
				item.setPortHarbourCode(object.getPortHarbourCode());
				item.setRepresentative(object.getRepresentative());
				item.setIssueDate(FormatData.parseDateToTring(object.getIssueDate()));
				item.setDirectorOfMaritimeAdministration(object.getDirectorOfMaritime());
				item.setCertificateNo(object.getCertificateNo());
							

				vn.nsw.model.inland.InlandShiftingOrder.AttachedFile attachedFilePDFKy = new vn.nsw.model.inland.InlandShiftingOrder.AttachedFile();
				long kySoId = object.getAttachedFile();
				
				if (kySoId > 0) {
					attachedFilePDFKy.setAttachedTypeCode("70");
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
