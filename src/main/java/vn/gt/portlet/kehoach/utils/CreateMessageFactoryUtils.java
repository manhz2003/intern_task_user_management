/**
 * 
 */
package vn.gt.portlet.kehoach.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.ResultCertificate;
import vn.gt.dao.result.service.ResultCertificateLocalServiceUtil;
import vn.gt.tichhop.message.MessageFactory;
import vn.gt.tichhop.message.giaothong2haiquan.FeeApproved2Xml;
import vn.gt.tichhop.message.giaothong2haiquan.FeeNotice2Xml;
import vn.gt.utils.FormatData;
import vn.nsw.model.FeeApproved;
import vn.nsw.model.FeeNotice;
import vn.nsw.model.NoticeOfArrivalCancel;



import com.fds.flex.common.utility.string.StringPool;

/**
 * @author win_64
 */
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CreateMessageFactoryUtils {


	

	public static String createNoticeOfArrivalCancel(NoticeOfArrivalCancel noticeOfArrivalCancel) {
		return MessageFactory.convertObjectToXml(noticeOfArrivalCancel);
	}

	public static String create_22(String rejectCode, String rejectDesc, String organization, String division,
			String name, Date rejectDate) {
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

	// 27
	public static String create_27(String rejectCode, String rejectDesc, String organization, String division,
			String name, Date rejectDate) {
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

	public static String create_95(String rejectCode, String rejectDesc, String organization, String division,
			String name, Date rejectDate) {
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

	public static String create_71(String organization, String division, String name, Date cancelDate, String reason,
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

	// 24
	public static String create_24(String organization, String division, String name, Date cancelDate, String reason) {
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

	public static String create_23(String receiveDate) {
		String xmlResult = "<ReceiveDate>" + receiveDate + "</ReceiveDate>";

		return xmlResult.toString();
	}

	public static String create_21(String organization, String division, String name, Date acceptDate) {
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

	public static String create_05(String lydoYeuCauXuatTrinh, String organization, String division, String name,
			Date rejectDate, TempDocument tempDocument) {
		StringBuilder xmlResult = new StringBuilder("");
		long docname = 0;
		int docyear = 0;
		docname = tempDocument.getDocumentName();
		docyear = tempDocument.getDocumentYear();

		List<ResultCertificate> lstresultCertificate = new ArrayList<ResultCertificate>(
				ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYear(docname, docyear));
		xmlResult.append("<RequestAttachment>");
		xmlResult.append("<RejectDesc>").append(lydoYeuCauXuatTrinh).append("</RejectDesc>");
		xmlResult.append("<Organization>").append(organization).append("</Organization>");
		xmlResult.append("<Division>").append(division).append("</Division>");
		xmlResult.append("<Name>").append(name).append("</Name>");
		xmlResult.append("<RejectDate>").append(FormatData.parseDateToTring(rejectDate)).append("</RejectDate>");

		if (lstresultCertificate != null && lstresultCertificate.size() > 0) {
			log.info("=======AttachmentList=========== docname " + docname + " docyear " + docyear + "--size--"
					+ lstresultCertificate.size());
			log.info("=======createMessageBoSungDinhKem=========== docname " + docname + " docyear " + docyear
					+ "--Mandatory--" + lstresultCertificate.get(0).getMandatory());
			for (ResultCertificate resCer : lstresultCertificate) {
				if (resCer.getMandatory() == 1) {
					xmlResult.append("<AttachmentList>");
					xmlResult.append("<AttachmentType>").append(resCer.getCertificateCode())
							.append("</AttachmentType>");
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

	public static String create_65(String rejectDesc, int isPass, String organization, String division, String name,
			Date rejectDate, TempDocument tempDocument) {
		StringBuilder xmlResult = new StringBuilder("");

		xmlResult.append("<AttachmentResult>");
		xmlResult.append("<IsPass>").append(isPass).append("</IsPass>");
		xmlResult.append("<Comment>").append(rejectDesc).append("</Comment>");
		xmlResult.append("</AttachmentResult>");

		return xmlResult.toString();
	}

	// 28 - YCBS
	public static String create_28(String rejectCode, String rejectDesc, String organization, String division,
			Date rejectDate) {
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

	public static String create_26(int approvalStatus, String comment, String organization, String division,
			String name, Date approvalDate) {
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

	public static String create_30(long documentName, int documentYear) {
		String xmlResult = StringPool.BLANK;

		FeeNotice2Xml feeNotice2Xml = new FeeNotice2Xml();

		FeeNotice feeNotice;
		try {
			feeNotice = feeNotice2Xml.insert2FeeNotice(documentName, documentYear);

			xmlResult = MessageFactory.convertObjectToXml(feeNotice);

		} catch (Exception e) {
			xmlResult = StringPool.BLANK;
		}

		return xmlResult;
	}

	public static String create_31(long documentName, int documentYear) {
		String xmlResult = StringPool.BLANK;

		FeeApproved2Xml feeApproved2Xml = new FeeApproved2Xml();

		FeeApproved FeeApproved;
		try {
			FeeApproved = feeApproved2Xml.insert2FeeApproved(documentName, documentYear);

			xmlResult = MessageFactory.convertObjectToXml(FeeApproved);

		} catch (Exception e) {
			xmlResult = StringPool.BLANK;
		}

		return xmlResult;
	}
}
