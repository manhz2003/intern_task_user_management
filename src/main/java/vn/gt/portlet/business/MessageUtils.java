//package vn.gt.portlet.business;
//
//import com.fds.nsw.liferay.core.ActionRequest;
//import com.fds.nsw.liferay.core.ActionResponse;
//
//import vn.gt.tichhop.message.MessageFactory;
//import vn.gt.tichhop.message.Messsage;
//
//import com.fds.nsw.kernel.exception.SystemException;
//import com.fds.nsw.liferay.core.ParamUtil;
//
//public class MessageUtils {
//
//	public String genMessages(ActionRequest resourceRequest,
//			ActionResponse httpReq) throws Exception {
//		Messsage messsage = fillData2Form(resourceRequest);
//
//
//		return MessageFactory.createMessageRequest(messsage);
//	}
//
//
//
//	public Messsage fillData2Form(ActionRequest actionrequest) throws SystemException {
//
//		Messsage result = new Messsage();
//
//
//		String ref_version = ParamUtil.getString(actionrequest, "ref_version")
//				.trim();
//		result.setRef_version(ref_version);
//
//		String ref_messageId = ParamUtil.getString(actionrequest,
//				"ref_messageId").trim();
//		result.setRef_messageId(ref_messageId);
//		// From
//		String frm_name = ParamUtil.getString(actionrequest, "frm_name").trim();
//		result.setFrm_name(frm_name);
//		String frm_identity = ParamUtil
//				.getString(actionrequest, "frm_identity").trim();
//		result.setFrm_identity(frm_identity);
//		// To
//		String to_name = ParamUtil.getString(actionrequest, "to_name").trim();
//		result.setTo_name(to_name);
//		String to_identity = ParamUtil.getString(actionrequest, "to_identity")
//				.trim();
//		result.setTo_identity(to_identity);
//		// Subject
//		String documentType = ParamUtil
//				.getString(actionrequest, "documentType").trim();
//		result.setDocumentType(documentType);
//		String type = ParamUtil.getString(actionrequest, "type").trim();
//		result.setType(type);
//		String function = ParamUtil.getString(actionrequest, "function").trim();
//		result.setFunction(function);
//		String reference = ParamUtil.getString(actionrequest, "reference")
//				.trim();
//		result.setReference(reference);
//		String preReference = ParamUtil
//				.getString(actionrequest, "preReference").trim();
//		result.setPreReference(preReference);
//		String documentYear = ParamUtil
//				.getString(actionrequest, "documentYear").trim();
//		result.setDocumentYear(documentYear);
//		String sendDate = ParamUtil.getString(actionrequest, "sendDate").trim();
//		result.setSendDate(sendDate);
//
////
//		return result;
//	}
//
//}
