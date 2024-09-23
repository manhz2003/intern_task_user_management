package vn.gt.portlet.thutuc;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;

import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fds.nsw.liferay.core.JSONFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.WebKeys;
//import com.liferay.portal.security.auth.AuthTokenUtil;
import com.fds.nsw.liferay.core.ServiceContext;

import com.fds.nsw.liferay.core.ThemeDisplay;
import com.fds.nsw.liferay.core.PortalUtil;

import com.fds.nsw.nghiepvu.model.DmMaritime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDebitnote;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.payment.keypay.model.KeyPay;
import vn.gt.payment.util.PaymentMgtUtil;
import vn.gt.payment.util.PaymentUrlGenerator;
import vn.gt.portlet.TransportationMVCAction;
import vn.gt.tichhop.report.FileUploadUtils;

@Slf4j
@Component
public class PayGateAction extends TransportationMVCAction {

	@Override
	public ResponseEntity<?> serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse){
		try {

			String resourceID = resourceRequest.getParameter("callResourceId");
			String idDebit = resourceRequest.getParameter("idDebit");
			String documentname = resourceRequest.getParameter("documentname");
			String documentyear = resourceRequest.getParameter("documentyear");
			String maritimecode = resourceRequest.getParameter("maritimecode");
			
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);

			long groupId = themeDisplay.getScopeGroupId();
			
			if (resourceID.equals("renderURLInit")) {

				JSONObject tempDocumentOBJ = JSONFactoryUtil.createJSONObject();
				
				try {
					TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil.getByNumberDebit(idDebit);
					//todo AuthTokenUtil.getToken
//					tempDocumentOBJ.put("auth", AuthTokenUtil.getToken(PortalUtil.getHttpServletRequest(resourceRequest)));
					
					if (String.valueOf(tempDebitNote.getDocumentName()).equalsIgnoreCase(documentname) || 
							String.valueOf(tempDebitNote.getDocumentYear()).equalsIgnoreCase(documentyear)) {
						
						TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(tempDebitNote.getDocumentName(), tempDebitNote.getDocumentYear());
						
						System.out.println("PayGateAction.serveResource(tempDocument)" + tempDocument);
						
						tempDocumentOBJ.put("message", true);
						
						tempDocumentOBJ.put("documentName", tempDebitNote.getDocumentName());
						tempDocumentOBJ.put("documentYear", tempDebitNote.getDocumentYear());
						tempDocumentOBJ.put("userCreated", tempDocument.getUserCreated());
						tempDocumentOBJ.put("reportDate", tempDebitNote.getReportdate());
						tempDocumentOBJ.put("content", tempDebitNote.getComments());
						tempDocumentOBJ.put("debitnotenumber", tempDebitNote.getDebitnotenumber());		
						tempDocumentOBJ.put("idDebit", idDebit);		
						tempDocumentOBJ.put("maritimecode", maritimecode);
						
						try {
							Locale locale = new Locale("en", "UK");

							DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
							symbols.setDecimalSeparator(',');
							symbols.setGroupingSeparator('.');

							String pattern = "#,##0.###";
							DecimalFormat formatter = new DecimalFormat(pattern, symbols);
							String patternVND = "#,##0";
							DecimalFormat formatterVND = new DecimalFormat(patternVND, symbols);
							
							if (Validator.isNotNull(tempDebitNote.getForeigncurrency()) && tempDebitNote.getForeigncurrency().equalsIgnoreCase("USD")) {
								tempDocumentOBJ.put("fee", formatterVND
										.format(tempDebitNote.getTotalpayment()));
								tempDocumentOBJ.put("feeUSD", formatter.format(tempDebitNote.getTotalforeignpayment()));
							} else {
								if (Validator.isNotNull(tempDebitNote.getCurrency())){
									if (tempDebitNote.getCurrency().equalsIgnoreCase("VND")) {
										tempDocumentOBJ.put("fee", formatterVND.format(tempDebitNote.getTotalpayment()));
										tempDocumentOBJ.put("feeUSD", formatter.format(tempDebitNote.getTotalforeignpayment()));
									} else if (tempDebitNote.getCurrency().equalsIgnoreCase("USD")) {
										tempDocumentOBJ.put("fee", 0);
										tempDocumentOBJ.put("feeUSD", formatter.format(tempDebitNote.getTotalpayment()));
									}
								} else {
									tempDocumentOBJ.put("fee", formatterVND.format(tempDebitNote.getTotalpayment()));
									tempDocumentOBJ.put("feeUSD", formatter.format(tempDebitNote.getTotalforeignpayment()));
								}
							}
							if (Validator.isNotNull(maritimecode)) {
								DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimecode);
								tempDocumentOBJ.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
							}
						} catch (Exception e) {
							
						}
						
						String keyPayURL = PaymentUrlGenerator.getConfigKeyPayURL(tempDebitNote);						
						tempDocumentOBJ.put("keyPayURL", keyPayURL);
						
						JSONObject resultPaymentData = JSONFactoryUtil.createJSONObject();						
						resultPaymentData = PaymentUrlGenerator.generatePaymentData(tempDebitNote);
						tempDocumentOBJ.put("resultPaymentData", resultPaymentData);						
						
						if (tempDebitNote.getMarkasdeleted() == 1 || tempDebitNote.getMarkasdeleted() == 12) {
							tempDocumentOBJ.put("thanhToanDone", true);
						} else {
							tempDocumentOBJ.put("thanhToanDone", false);
						}
						
						
						
					} else {
						tempDocumentOBJ.put("message", false);
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					tempDocumentOBJ.put("message", false);
				}
//				api.put("user", user);
				
				return writeJSON(resourceRequest, resourceResponse, tempDocumentOBJ);

			} else {

				super.serveResource(resourceRequest, resourceResponse);

			}
		} catch (Exception e) {

			log.error(e.getMessage());

		}
		return super.serveResource(resourceRequest, resourceResponse);
	}

	public ResponseEntity<?> baoNopAction(ActionRequest actionRequest, ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);

		String idDebit = ParamUtil.getString(actionRequest, "idDebit", "");
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil.getByNumberDebit(idDebit);
			
			tempDebitNote.setMarkasdeleted(11);
			
			TempDebitNoteLocalServiceUtil.updateTempDebitNote(tempDebitNote);
			
			result.put("message", true);


		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		return writeJSON(actionRequest, actionResponse, result);

	}
	
	public ResponseEntity<?> paymentUpload(ActionRequest actionRequest, ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);


		String idDebit = ParamUtil.getString(actionRequest, "idDebit", "");
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			ServiceContext serviceContext = new ServiceContext();
			
			TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil.getByNumberDebit(idDebit);
			
			int ATTACHEDFILE = 0;
			UploadPortletRequest requestUpload = (UploadPortletRequest) actionRequest;
//todo response file in controller
//			String uploadFileName = actionRequest.getFileName("documentaryFile");
//			File uploadFile = requestUpload.getFile("documentaryFile");
//
//			DLFileEntry fileEntry = FileUploadUtils.uploadFile(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), ATTACHEDFILE, uploadFile,
//					uploadFile.getAbsolutePath(), null, null, serviceContext);
//
//			tempDebitNote.setATTACHEDFILE(fileEntry.getFileEntryId());
//			tempDebitNote.setMarkasdeleted(12);
			
			TempDebitNoteLocalServiceUtil.updateTempDebitNote(tempDebitNote);
			
			result.put("message", true);


		} catch (Exception e) {

		}
		return writeJSON(actionRequest, actionResponse, result);
	}

}
