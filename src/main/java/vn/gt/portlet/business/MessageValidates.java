//package vn.gt.portlet.business;
//
//import java.util.Enumeration;
//
//import com.fds.nsw.liferay.core.ActionRequest;
//
//import vn.gt.utils.ActionUtils;
//
//import com.liferay.portal.kernel.servlet.SessionErrors;
//import com.fds.nsw.liferay.core.ParamUtil;
//
//public class MessageValidates {
//
//	public static boolean validateMessages(ActionRequest actionrequest) {
//		boolean valid = true;
//
//		String ref_version = ParamUtil.getString(actionrequest, "ref_version")
//				.trim();
//		if (ref_version.length() == 0
//				|| !ActionUtils.checkIfIsLong(ref_version)) {
//			SessionErrors.add(actionrequest, "err.ref_version");
//			valid = false;
//		}
//
//		String ref_messageId = ParamUtil.getString(actionrequest,
//				"ref_messageId").trim();
//		if (ref_messageId.length() == 0
//				|| !ActionUtils.checkIfIsLong(ref_messageId)) {
//			SessionErrors.add(actionrequest, "err.ref_messageId");
//			valid = false;
//		}
//		// From
//		String frm_name = ParamUtil.getString(actionrequest, "frm_name").trim();
//		if (frm_name.length() == 0 || !ActionUtils.checkIfIsLong(frm_name)) {
//			SessionErrors.add(actionrequest, "err.frm_name");
//			valid = false;
//		}
//		String frm_identity = ParamUtil
//				.getString(actionrequest, "frm_identity").trim();
//		if (frm_identity.length() == 0
//				|| !ActionUtils.checkIfIsLong(frm_identity)) {
//			SessionErrors.add(actionrequest, "err.frm_identity");
//			valid = false;
//		}
//		// To
//		String to_name = ParamUtil.getString(actionrequest, "to_name").trim();
//		if (to_name.length() == 0 || !ActionUtils.checkIfIsLong(to_name)) {
//			SessionErrors.add(actionrequest, "err.to_name");
//			valid = false;
//		}
//		String to_identity = ParamUtil.getString(actionrequest, "to_identity")
//				.trim();
//		if (to_identity.length() == 0
//				|| !ActionUtils.checkIfIsLong(to_identity)) {
//			SessionErrors.add(actionrequest, "err.to_identity");
//			valid = false;
//		}
//		// Subject
//		String documentType = ParamUtil
//				.getString(actionrequest, "documentType").trim();
//		if (documentType.length() == 0
//				|| !ActionUtils.checkIfIsLong(documentType)) {
//			SessionErrors.add(actionrequest, "err.documentType");
//			valid = false;
//		}
//		String type = ParamUtil.getString(actionrequest, "type").trim();
//		if (type.length() == 0 || !ActionUtils.checkIfIsLong(type)) {
//			SessionErrors.add(actionrequest, "err.type");
//			valid = false;
//		}
//		String function = ParamUtil.getString(actionrequest, "function").trim();
//		if (function.length() == 0 || !ActionUtils.checkIfIsLong(function)) {
//			SessionErrors.add(actionrequest, "err.function");
//			valid = false;
//		}
//		String reference = ParamUtil.getString(actionrequest, "reference")
//				.trim();
//		if (reference.length() == 0 || !ActionUtils.checkIfIsLong(reference)) {
//			SessionErrors.add(actionrequest, "err.reference");
//			valid = false;
//		}
//		String preReference = ParamUtil
//				.getString(actionrequest, "preReference").trim();
//		if (preReference.length() == 0
//				|| !ActionUtils.checkIfIsLong(preReference)) {
//			SessionErrors.add(actionrequest, "err.preReference");
//			valid = false;
//		}
//		String documentYear = ParamUtil
//				.getString(actionrequest, "documentYear").trim();
//		if (documentYear.length() == 0
//				|| !ActionUtils.checkIfIsLong(documentYear)) {
//			SessionErrors.add(actionrequest, "err.coQuanQuanLyId");
//			valid = false;
//		}
//		String sendDate = ParamUtil.getString(actionrequest, "sendDate").trim();
//		if (sendDate.length() == 0 || !ActionUtils.checkIfIsLong(sendDate)) {
//			SessionErrors.add(actionrequest, "err.coQuanQuanLyId");
//			valid = false;
//		}
//
//		if (!valid) {
//			setParams(actionrequest);
//		}
//
//		return valid;
//	}
//
//
//
//	public static void setParams(ActionRequest resourceRequest) {
//		Enumeration<String> listName = resourceRequest.getParameterNames();
//		String maTaiLieu = "";
//		while (listName.hasMoreElements()) {
//			maTaiLieu = listName.nextElement();
//			resourceRequest.setAttribute(maTaiLieu,
//					ParamUtil.getString(resourceRequest, maTaiLieu));
//		}
//	}
//
//
//}
