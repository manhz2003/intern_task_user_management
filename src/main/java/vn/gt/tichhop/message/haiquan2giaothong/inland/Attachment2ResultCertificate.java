package vn.gt.tichhop.message.haiquan2giaothong.inland;

import java.util.ArrayList;
import java.util.List;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.nghiepvu.model.ResultCertificate;

import lombok.extern.slf4j.Slf4j;

import com.fds.nsw.nghiepvu.model.ResultCertificate;
import vn.gt.dao.result.service.ResultCertificateLocalServiceUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.inland.Attachment;
import vn.nsw.model.inland.Attachment.AttachmentList;




@Slf4j
public class Attachment2ResultCertificate {
	
	
	
	public boolean insert2ResultCertificate(Attachment attachment, Header header) throws Exception {
		
		try {
			log.info("====insert2ResultCertificate===ROI CANG====");
			long documentName = header.getSubject().getReference();
			int documentYear = header.getSubject().getDocumentYear();
			int documentType = header.getSubject().getDocumentType();
			int order = 0;
			
			List<AttachmentList> attachmentLists = new ArrayList<AttachmentList>();
			if (attachment.getAttachmentList() != null) {
				attachmentLists = attachment.getAttachmentList();
				log.info("attachment.getAttachmentList().size() " + attachment.getAttachmentList().size());
			}
			
			
			log.info("attachmentLists.size() " + attachmentLists.size());
			
			if (attachmentLists != null && attachmentLists.size() > 0) {
				for (AttachmentList item : attachmentLists) {
					
					String certificateCode = item.getAttachmentType();
					String certificateNo = "";
					String crewName = "";
					
					order = order + 1;
					ResultCertificate ettUpdate = null;
					ResultCertificate ettUpdateByCrewName = null;
					/*if ((documentType == MessageType.NHAP_CANH) || (documentType == MessageType.XUAT_CANH) || (documentType == MessageType.QUA_CANH))
					{
						
					} else if ((documentType == MessageType.TAU_VAO) || (documentType == MessageType.TAU_RA) || (documentType == MessageType.TAU_VAO_PTTND) || (documentType == MessageType.TAU_RA_PTTND))
					{*/
						if (Validator.isNotNull(item.getCertificateNo())) {
							certificateNo = item.getCertificateNo();							
						}						
					/*}*/
					
					if (certificateCode.equalsIgnoreCase("39") || certificateCode.equalsIgnoreCase("40") || certificateCode.equalsIgnoreCase("48") || 
							certificateCode.equalsIgnoreCase("49") || certificateCode.equalsIgnoreCase("50") || certificateCode.equalsIgnoreCase("60") )
					{							
						if ((Validator.isNotNull(item.getComment())) && (item.getComment().trim().length() > 0)) {
							crewName = item.getComment();
							ettUpdateByCrewName = ResultCertificateLocalServiceUtil.findByCrewNameAndCertificateCode(documentName, documentYear, certificateCode, crewName);
						}
					}
					ettUpdate = ResultCertificateLocalServiceUtil.fetchByF_BY4(documentName, documentYear, certificateCode, certificateNo);
					
					
					if(Validator.isNotNull(ettUpdate)){
						log.info("---update attachment--ROI CANG---");

						ettUpdate.setDocumentName(documentName);
						ettUpdate.setDocumentYear(documentYear);
						ettUpdate.setCertificateCode(item.getAttachmentType());
						ettUpdate.setCertificateNo(certificateNo);
											
						if ((Validator.isNotNull(item.getExpirationDate())) && (item.getExpirationDate().trim().length() > 0)) {
							ettUpdate.setCertificateExpiredDate(FormatData.parseStringToDate(item.getExpirationDate()));							
						}
						
						if ((Validator.isNotNull(item.getDateOfPeriodicInspection())) && (item.getDateOfPeriodicInspection().trim().length() > 0)) {
							ettUpdate.setExaminationDate(FormatData.parseStringToDate(item.getDateOfPeriodicInspection()));							
						}
						
						if ((Validator.isNotNull(item.getProvidedDate())) && (item.getProvidedDate().trim().length() > 0)) {
							ettUpdate.setCertificateIssueDate(FormatData.parseStringToDate(item.getProvidedDate()));							
						}
						
						if ((Validator.isNotNull(item.getOrder())) && (item.getOrder().trim().length() > 0)) {
							ettUpdate.setCertificateOrder(Integer.valueOf(item.getOrder()));							
						}
						else 
						{
							ettUpdate.setCertificateOrder(order);
						}
						if ((Validator.isNotNull(item.getComment())) && (item.getComment().trim().length() > 0)) {
							ettUpdate.setComment(item.getComment());							
						}
						if ((Validator.isNotNull(item.getDescription())) && (item.getDescription().trim().length() > 0)) {
							ettUpdate.setDescription(item.getDescription());							
						}
						ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdate);
						
					} else if(Validator.isNotNull(ettUpdateByCrewName)){
						log.info("---update attachment--ROI CANG---");

						ettUpdateByCrewName.setDocumentName(documentName);
						ettUpdateByCrewName.setDocumentYear(documentYear);
						ettUpdateByCrewName.setCertificateCode(item.getAttachmentType());
						ettUpdateByCrewName.setCertificateNo(certificateNo);
											
						if ((Validator.isNotNull(item.getExpirationDate())) && (item.getExpirationDate().trim().length() > 0)) {
							ettUpdateByCrewName.setCertificateExpiredDate(FormatData.parseStringToDate(item.getExpirationDate()));							
						}
						
						if ((Validator.isNotNull(item.getDateOfPeriodicInspection())) && (item.getDateOfPeriodicInspection().trim().length() > 0)) {
							ettUpdateByCrewName.setExaminationDate(FormatData.parseStringToDate(item.getDateOfPeriodicInspection()));							
						}
						
						if ((Validator.isNotNull(item.getProvidedDate())) && (item.getProvidedDate().trim().length() > 0)) {
							ettUpdateByCrewName.setCertificateIssueDate(FormatData.parseStringToDate(item.getProvidedDate()));							
						}
						
						if ((Validator.isNotNull(item.getOrder())) && (item.getOrder().trim().length() > 0)) {
							ettUpdateByCrewName.setCertificateOrder(Integer.valueOf(item.getOrder()));							
						}
						else 
						{
							ettUpdateByCrewName.setCertificateOrder(order);
						}
						if ((Validator.isNotNull(item.getComment())) && (item.getComment().trim().length() > 0)) {
							ettUpdateByCrewName.setComment(item.getComment());							
						}
						if ((Validator.isNotNull(item.getDescription())) && (item.getDescription().trim().length() > 0)) {
							ettUpdateByCrewName.setDescription(item.getDescription());							
						}
						ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdateByCrewName);
						
					} else{
						ResultCertificate rs = new ResultCertificate();
						rs.setDocumentName(documentName);
						rs.setDocumentYear(documentYear);
						rs.setCertificateCode(item.getAttachmentType());
						rs.setCertificateNo(certificateNo);
						
						
						if ((Validator.isNotNull(item.getExpirationDate())) && (item.getExpirationDate().trim().length() > 0)) {
							rs.setCertificateExpiredDate(FormatData.parseStringToDate(item.getExpirationDate()));							
						}
						
						if ((Validator.isNotNull(item.getDateOfPeriodicInspection())) && (item.getDateOfPeriodicInspection().trim().length() > 0)) {
							rs.setExaminationDate(FormatData.parseStringToDate(item.getDateOfPeriodicInspection()));							
						}
						
						if ((Validator.isNotNull(item.getProvidedDate())) && (item.getProvidedDate().trim().length() > 0)) {
							rs.setCertificateIssueDate(FormatData.parseStringToDate(item.getProvidedDate()));							
						}
						
						if ((Validator.isNotNull(item.getOrder())) && (item.getOrder().trim().length() > 0)) {
							rs.setCertificateOrder(Integer.valueOf(item.getOrder()));							
						}
						else 
						{
							rs.setCertificateOrder(order);
						}
						if ((Validator.isNotNull(item.getComment())) && (item.getComment().trim().length() > 0)) {
							rs.setComment(item.getComment());							
						}
						if ((Validator.isNotNull(item.getDescription())) && (item.getDescription().trim().length() > 0)) {
							rs.setDescription(item.getDescription());							
						}
						try {
							ResultCertificateLocalServiceUtil.addResultCertificate(rs);
							log.info("---addd attachment--ROI CANG-");
						} catch (Exception e) {
							log.error(e.getMessage());
						}
					}
					
					
				}
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	
}
