/**
 * 
 */
package vn.gt.tichhop.message.giaothong2haiquan;

import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.ResultCertificate;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.result.service.ResultCertificateLocalServiceUtil;
import vn.gt.tichhop.message.MessageFactory;
import vn.gt.utils.FormatData;
import vn.nsw.model.NoticeOfArrivalCancel;




/**
 * @author win_64
 */
@Slf4j
public class CreateMessageFactory {
	
	
	
	public static String createNoticeOfArrivalCancel(NoticeOfArrivalCancel noticeOfArrivalCancel) {
		return MessageFactory.convertObjectToXml(noticeOfArrivalCancel);
	}
	
	public static String createMessageReject(String rejectCode, String rejectDesc, String organization, String division, String name, Date rejectDate) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<Reject>");
		xmlResult.append("<RejectCode>").append(rejectCode).append("</RejectCode>");
		xmlResult.append("<RejectDesc>").append(rejectDesc).append("</RejectDesc>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("</Reject>");
		xmlResult.append("<RejectDate>");
		xmlResult.append(FormatData.parseDateToTring(rejectDate));
		xmlResult.append("</RejectDate>");
		
		return xmlResult.toString();
	}
	
	//27
	public static String createMessageModify(String rejectCode, String rejectDesc, String organization, String division, String name, Date rejectDate) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<Modify>");
		xmlResult.append("<ModifyCode>").append(rejectCode).append("</ModifyCode>");
		xmlResult.append("<ModifyDesc>").append(rejectDesc).append("</ModifyDesc>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<ModifyDate>");
		xmlResult.append(FormatData.parseDateToTring(rejectDate));
		xmlResult.append("</ModifyDate>");
		xmlResult.append("</Modify>");
		return xmlResult.toString();
	}
	public static String createMessageDelay(String rejectCode, String rejectDesc, String organization, String division, String name, Date rejectDate) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<Delay>");
		xmlResult.append("<DelayCode>").append(rejectCode).append("</DelayCode>");
		xmlResult.append("<DelayDesc>").append(rejectDesc).append("</DelayDesc>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");		
		xmlResult.append("</Delay>");
		xmlResult.append("<DelayDate>");
		xmlResult.append(FormatData.parseDateToTring(rejectDate));
		xmlResult.append("</DelayDate>");
		return xmlResult.toString();
	}
	//TODO Message-25-Yêu cầu bổ sung
	public static String createMessageRejectInland(String rejectCode, String rejectDesc, String organization, String division, String name, Date rejectDate) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<AdditionalRequirements>");
		xmlResult.append("<RequirementCode>").append(rejectCode).append("</RequirementCode>");
		xmlResult.append("<RequirementDesc>").append(rejectDesc).append("</RequirementDesc>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<RequirementDate>");
		xmlResult.append(FormatData.parseDateToTring(rejectDate));
		xmlResult.append("</RequirementDate>");
		xmlResult.append("</AdditionalRequirements>");
		
		return xmlResult.toString();
	}
	
	public static String createMessageHuyGiayPhepRoiCang(String organization, String division, String name, Date cancelDate, String reason,
			Integer isApprove) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<CancelDate>");
		xmlResult.append(FormatData.parseDateToTring(cancelDate));
		xmlResult.append("</CancelDate>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<Reason>").append(reason).append("</Reason>");
		xmlResult.append("<IsApprove>").append(isApprove).append("</IsApprove>");
		
		return xmlResult.toString();
	}
	
	//24
	public static String createMessageHuyHoSo(String organization, String division, String name, Date cancelDate, String reason) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<CancelDate>");
		xmlResult.append(FormatData.parseDateToTring(cancelDate));
		xmlResult.append("</CancelDate>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<Reason>").append(reason).append("</Reason>");
		
		return xmlResult.toString();
	}
	
	public static String createMessageHoanThanh(String receiveDate) {
		String xmlResult = "<ReceiveDate>" + receiveDate + "</ReceiveDate>";
		
		return xmlResult.toString();
	}
	
	public static String createMessageAccept(String organization, String division, String name, Date acceptDate) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<AcceptDate>");
		xmlResult.append(FormatData.parseDateToTring(acceptDate));
		xmlResult.append("</AcceptDate>");
		xmlResult.append("<Accepter>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("</Accepter>");
		
		return xmlResult.toString();
	}
	
	
	//TODO Message-29-Yeu cau Xuat trinh giay to
	public static String createMessageBoSungDinhKem(String lydoYeuCauXuatTrinh, String organization, String division, String name, Date rejectDate, TempDocument tempDocument) {
		StringBuilder xmlResult = new StringBuilder("");
		long docname = 0;
		int docyear  = 0;
		docname =  tempDocument.getDocumentName();
		docyear =  tempDocument.getDocumentYear();
		
		
		List<ResultCertificate> lstresultCertificate = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYear(docname, docyear);
		
		
		log.info("=======createMessageBoSungDinhKem=========== docname " + docname + " docyear " + docyear + "--Mandatory--" + lstresultCertificate.get(0).getMandatory());
		
		xmlResult.append("<RequestAttachment>");
		xmlResult.append("<RejectDesc>").append(lydoYeuCauXuatTrinh).append("</RejectDesc>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<RejectDate>").append(FormatData.parseDateToTring(rejectDate)).append("</RejectDate>");
		
		
		if (lstresultCertificate != null && lstresultCertificate.size() > 0) {
			log.info("=======AttachmentList=========== docname " + docname + " docyear " + docyear + "--size--" + lstresultCertificate.size());
			for (ResultCertificate resCer : lstresultCertificate) {				
				if (resCer.getMandatory() == 1) {
					xmlResult.append("<AttachmentList>");
					xmlResult.append("<AttachmentType>").append(resCer.getCertificateCode()).append("</AttachmentType>");
					xmlResult.append("<Comment>").append(resCer.getComment()).append("</Comment>");
					xmlResult.append("</AttachmentList>");
					}				
			}
			
			xmlResult.append("</RequestAttachment>");
		} else {
			xmlResult = null;
		}
		
		return xmlResult.toString();
	}
	
	
	public static String createMessageDongYDinhKem(String rejectDesc, String organization, String division, String name, Date rejectDate, TempDocument tempDocument) {
		StringBuilder xmlResult = new StringBuilder("");
		
		
		xmlResult.append("<AttachmentResult>");
		xmlResult.append("<IsPass>").append(1).append("</IsPass>");
		xmlResult.append("<Comment>").append("").append("</Comment>");
		xmlResult.append("</AttachmentResult>");
		
		return xmlResult.toString();
	}
	
	
	
	public static String createMessageTuChoiDinhKem(String rejectDesc, String organization, String division, String name, Date rejectDate, TempDocument tempDocument, String lydotuchoi) {
		StringBuilder xmlResult = new StringBuilder("");
		
		
		xmlResult.append("<AttachmentResult>");
		xmlResult.append("<IsPass>").append(0).append("</IsPass>");
		xmlResult.append("<Comment>").append(lydotuchoi).append("</Comment>");
		xmlResult.append("</AttachmentResult>");
		
		return xmlResult.toString();
	}
	
	// 28 - YCBS
	public static String createMessageRejectSystem(String rejectCode, String rejectDesc, String organization, String division, Date rejectDate) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<Reject>");
		xmlResult.append("<RejectCode>").append(rejectCode).append("</RejectCode>");
		xmlResult.append("<RejectDesc>").append(rejectDesc).append("</RejectDesc>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>SYSTEM</Name>");
		xmlResult.append("</Reject>");
		xmlResult.append("<RejectDate>");
		xmlResult.append(FormatData.parseDateToTring(rejectDate));
		xmlResult.append("</RejectDate>");
		
		return xmlResult.toString();
	}
	
	public static String createMessageHuyLenhDieuDong(String organization, String division, String name, String reason, Date cancelDate,
			Integer isApprove) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<CancelDate>");
		xmlResult.append(FormatData.parseDateToTring(cancelDate));
		xmlResult.append("</CancelDate>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<Reason>").append(reason).append("</Reason>");
		xmlResult.append("<IsApprove>").append(isApprove).append("</IsApprove>");
		
		return xmlResult.toString();
	}
	
	public static String createMessageHuyGiayPhepRoiCangInland(String organization, String division, String name, Date cancelDate, String reason,
			Integer isApprove) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<CancelDate>");
		xmlResult.append(FormatData.parseDateToTring(cancelDate));
		xmlResult.append("</CancelDate>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<Reason>").append(reason).append("</Reason>");
	//	xmlResult.append("<IsApprove>").append(isApprove).append("</IsApprove>");
		
		return xmlResult.toString();
	}
	
	public static String createMessageHuyGiayPhepRoiCang(String organization, String division, String name, String reason, Date cancelDate,
			Integer isApprove) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<CancelDate>");
		xmlResult.append(FormatData.parseDateToTring(cancelDate));
		xmlResult.append("</CancelDate>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<Reason>").append(name).append("</Reason>");
		xmlResult.append("<IsApprove>").append(isApprove).append("</IsApprove>");
		
		return xmlResult.toString();
	}
	
	public static String createMessageHuyGiayPhepQuaCanh(String organization, String division, String name, String reason, Date cancelDate,
			Integer isApprove) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<CancelDate>");
		xmlResult.append(FormatData.parseDateToTring(cancelDate));
		xmlResult.append("</CancelDate>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<Reason>").append(name).append("</Reason>");
		xmlResult.append("<IsApprove>").append(isApprove).append("</IsApprove>");
		
		return xmlResult.toString();
	}
	
	public static String createMessageKhaiHuyHoSo(String organization, String division, String name, String reason, Date cancelDate, Integer isApprove) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<CancelDate>");
		xmlResult.append(FormatData.parseDateToTring(cancelDate));
		xmlResult.append("</CancelDate>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<Reason>").append(name).append("</Reason>");
		xmlResult.append("<IsApprove>").append(isApprove).append("</IsApprove>");
		
		return xmlResult.toString();
	}
	
	public static String pheDuyetHoSoTuCacBo(int approvalStatus, String comment, String organization, String division, String name, Date approvalDate) {
		StringBuilder xmlResult = new StringBuilder("");
		xmlResult.append("<Approval>");
		xmlResult.append("<ApprovalStatus>");
		xmlResult.append(approvalStatus);
		xmlResult.append("</ApprovalStatus>");
		xmlResult.append("<Comment>");
		xmlResult.append(comment);
		xmlResult.append("</Comment>");
		xmlResult.append("<Organization>");
		xmlResult.append(organization);
		xmlResult.append("</Organization>");
		xmlResult.append("<Division>");
		xmlResult.append(division);
		xmlResult.append("</Division>");
		xmlResult.append("<Name>");
		xmlResult.append(name);
		xmlResult.append("</Name>");
		xmlResult.append("<ApprovalDate>");
		xmlResult.append(FormatData.parseDateToTring(approvalDate));
		xmlResult.append("</ApprovalDate>");
		xmlResult.append("</Approval>");
		
		return xmlResult.toString();
	}
}
