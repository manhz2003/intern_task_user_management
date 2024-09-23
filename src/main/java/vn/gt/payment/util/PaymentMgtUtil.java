
package vn.gt.payment.util;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fds.nsw.nghiepvu.model.TempDebitnote;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.payment.keypay.model.KeyPay;
import vn.gt.payment.keypay.model.KeyPayV3;
import vn.gt.ws.util.WebserviceUtil;

import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.ultility.Validator;

/**
 * @author trungdk
 */
@Slf4j
public class PaymentMgtUtil {

	public static final int PAYMENT_STATUS_KEYPAY_OK = 1;
	public static final int PAYMENT_STATUS_KEYPAY_PENDING = 0;

	public static void runKeyPayGateData(HttpServletRequest request, HttpServletResponse response, KeyPay keyPay) 
			throws IOException {
		
		String keypayRedirectUrl = WebserviceUtil.getKeypayRedirectUrl();
		String result = "error";
		
		try {
			TempDebitnote debitNote = null;

			boolean isVerify = KeyPay.checkSecureHash(keyPay);

			if (keyPay.getMerchant_trans_id().trim().length() > 0) {

				try {
					debitNote = TempDebitNoteLocalServiceUtil.getDebitNoteByTransactionId(keyPay.getMerchant_trans_id().trim());
				} catch (Exception e) {

				}
			}
			
			if (Validator.isNull(debitNote)) {
				keypayRedirectUrl += "/0";
				keypayRedirectUrl += "/0";
				keypayRedirectUrl += "/0";
				keypayRedirectUrl += "/0";
			}
			
			if (isVerify && Validator.isNotNull(debitNote)) {
				
				keypayRedirectUrl += "/" + debitNote.getDebitnotenumber();
				keypayRedirectUrl += "/" + debitNote.getDocumentName();
				keypayRedirectUrl += "/" + debitNote.getDocumentYear();
				keypayRedirectUrl += "/" + debitNote.getMariTimeCode();

				if (Validator.isNotNull(debitNote)
						&& (debitNote.getMarkasdeleted() != PaymentMgtUtil.PAYMENT_STATUS_KEYPAY_OK)) {

					debitNote.setMarkasdeleted(PaymentMgtUtil.PAYMENT_STATUS_KEYPAY_OK);
					debitNote.setPaymenttype(2);
				} else {
					debitNote.setPaymentGateStatusCode(keyPay.getService_code());
				}

				JSONObject jsonData = JSONFactoryUtil.createJSONObject();

				jsonData.put("command", keyPay.getCommand());
				jsonData.put("merchant_trans_id", keyPay.getMerchant_trans_id());
				jsonData.put("merchant_code", keyPay.getMerchant_code());
				jsonData.put("response_code", keyPay.getResponse_code());
				jsonData.put("trans_id", keyPay.getTrans_id());
				jsonData.put("good_code", keyPay.getGood_code());
				jsonData.put("net_cost", keyPay.getNet_cost());
				jsonData.put("ship_fee", keyPay.getShip_fee());
				jsonData.put("tax", keyPay.getTax());
				jsonData.put("service_code", keyPay.getService_code());
				jsonData.put("currency_code", keyPay.getCurrency_code());
				jsonData.put("bank_code", keyPay.getBank_code());
				jsonData.put("secure_hash", keyPay.getSecure_hash());
				jsonData.put("desc_1", keyPay.getDesc_1());
				jsonData.put("desc_2", keyPay.getDesc_2());
				jsonData.put("desc_3", keyPay.getDesc_3());
				jsonData.put("desc_4", keyPay.getDesc_4());
				jsonData.put("desc_5", keyPay.getDesc_5());

				debitNote.setKeypayGoodCode(keyPay.getGood_code());
				debitNote.setPaymentGateStatusCode(keyPay.getService_code());
				debitNote.setPaymentGateResponseData(jsonData.toString());

				TempDebitNoteLocalServiceUtil.updateTempDebitNote(debitNote);
				
				result = "success";
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		keypayRedirectUrl += "/" + result;
		
		response.sendRedirect(keypayRedirectUrl);
	}

	public static void runKeyPayGateDataV3(HttpServletRequest request, HttpServletResponse response, KeyPayV3 keyPay) {
		
		String keypayRedirectUrl = WebserviceUtil.getKeypayRedirectUrl();
		String result = "error";
		
		try {
			TempDebitnote debitNote = null;

			boolean isVerify = KeyPayV3.checkSecureHash(keyPay);

			if (keyPay.getClient_id().trim().length() > 0 && keyPay.getTransaction_id().trim().length() > 0 &&  keyPay.getTransaction_code().trim().length() > 0) {

				try {
					debitNote = TempDebitNoteLocalServiceUtil.getDebitNoteByTransactionId(keyPay.getTransaction_id().trim());
				} catch (Exception e) {

				}
			}
			
			if (Validator.isNull(debitNote)) {
				keypayRedirectUrl += "/0";
				keypayRedirectUrl += "/0";
				keypayRedirectUrl += "/0";
				keypayRedirectUrl += "/0";
			}
			JSONObject resultData = JSONFactoryUtil.createJSONObject();
			
			if (isVerify && Validator.isNotNull(debitNote)) {
				
				keypayRedirectUrl += "/" + debitNote.getDebitnotenumber();
				keypayRedirectUrl += "/" + debitNote.getDocumentName();
				keypayRedirectUrl += "/" + debitNote.getDocumentYear();
				keypayRedirectUrl += "/" + debitNote.getMariTimeCode();

				if (Validator.isNotNull(debitNote)
						&& (debitNote.getMarkasdeleted() != PaymentMgtUtil.PAYMENT_STATUS_KEYPAY_OK)) {

					debitNote.setMarkasdeleted(PaymentMgtUtil.PAYMENT_STATUS_KEYPAY_OK);
					debitNote.setPaymenttype(2);
				} else {
					debitNote.setPaymentGateStatusCode(keyPay.getStatus());
				}

				JSONObject jsonData = JSONFactoryUtil.createJSONObject();

				jsonData.put("command", keyPay.getCommand());
				jsonData.put("transaction_id", keyPay.getTransaction_id());
				jsonData.put("transaction_code", keyPay.getTransaction_code());
				jsonData.put("status", keyPay.getStatus());
				jsonData.put("data", keyPay.getData());
				jsonData.put("msg", keyPay.getMsg());
				jsonData.put("error", keyPay.getError());
				jsonData.put("payment_fee", keyPay.getPayment_fee());
				jsonData.put("trans_amount", keyPay.getTrans_amount());
				jsonData.put("addition_fee", keyPay.getAddition_fee());
				jsonData.put("currency_code", keyPay.getCurrency_code());
				jsonData.put("return_url ", keyPay.getReturn_url ());
				jsonData.put("check_sum", keyPay.getCheck_sum());
				jsonData.put("version", keyPay.getVersion());
				jsonData.put("description", keyPay.getDescription());
				jsonData.put("locale", keyPay.getLocale());
				jsonData.put("country_code", keyPay.getCountry_code());
				jsonData.put("environment", keyPay.getEnvironment());
				jsonData.put("bill_info", keyPay.getBill_info());

				debitNote.setKeypayGoodCode(keyPay.getTransaction_code());
				debitNote.setPaymentGateStatusCode(keyPay.getStatus());
				debitNote.setPaymentGateResponseData(jsonData.toString());

				TempDebitNoteLocalServiceUtil.updateTempDebitNote(debitNote);
				
				
				resultData.put("return_code", 0);
				resultData.put("return_msg", "success");
				result = "success";
				keypayRedirectUrl += "/" + result;				
				
				response.sendRedirect(keypayRedirectUrl);
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(resultData.toString());
			} else {				
				resultData.put("return_code", -1);
				resultData.put("return_msg", "failed");
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(resultData.toString());
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject resultData = JSONFactoryUtil.createJSONObject();
			resultData.put("return_code", -1);
			resultData.put("return_msg", "failed");
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				response.getWriter().write(resultData.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			
		}
		
	}
	
}
