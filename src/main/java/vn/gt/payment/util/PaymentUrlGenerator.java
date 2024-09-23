package vn.gt.payment.util;

import java.net.URLEncoder;

import com.fds.nsw.nghiepvu.model.DmMaritime;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig;
import vn.gt.dao.noticeandmessage.service.TempMaritimePaymentConfigLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDebitnote;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.payment.keypay.model.KeyPay;
import vn.gt.payment.keypay.model.KeyPayV3;
import vn.gt.utils.config.ConfigurationManager;
import vn.gt.ws.util.WebserviceUtil;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.PwdGenerator;

@Slf4j
public class PaymentUrlGenerator {
	public static String generateKeyPayURL(TempDebitnote debitNote) {
		
		String url_redirect = StringPool.BLANK;
		
		if(Validator.isNotNull(debitNote)) {
		
			url_redirect = debitNote.getKeypayURL();
			
			if(Validator.isNull(url_redirect)) {
			
				TempMaritimePaymentConfig paymentConfig = TempMaritimePaymentConfigLocalServiceUtil.getPaymentConfig(
					String.valueOf(debitNote.getMariTimeCode()));
			
				if(paymentConfig != null) {
					long merchant_trans_id = _genetatorTransactionId();
			
					String good_code = generatorGoodCode(10);
					String net_cost = String.valueOf(debitNote.getTotalpayment()); //amount
					String ship_fee = "0";
					String tax = "0";
					String bank_code = StringPool.BLANK;
					String service_code = ConfigurationManager.getStrProp("vn.gt.keypay.service.code");
					String version = "1.0";
					String command = ConfigurationManager.getStrProp("vn.gt.keypay.command");
					String currency_code = ConfigurationManager.getStrProp("vn.gt.keypay.currency.code");
			
					String desc_1 = StringPool.BLANK;
					String desc_2 = StringPool.BLANK;
					String desc_3 = StringPool.BLANK;
					String desc_4 = StringPool.BLANK;
					String desc_5 = StringPool.BLANK;
			
					String xml_description = StringPool.BLANK;
					String current_locale = ConfigurationManager.getStrProp("vn.gt.keypay.current.locale");
					String country_code = ConfigurationManager.getStrProp("vn.gt.keypay.country.code");
					String internal_bank = ConfigurationManager.getStrProp("vn.gt.keypay.internal.bank");
			
					String merchant_code = paymentConfig.getMerchantCode();			// merchant code
					String merchant_secure_key = paymentConfig.getMerchantKey();	// merchant secure key
			
					String return_url = paymentConfig.getReturnUrl();
					
					try {
						KeyPay keypay = new KeyPay(String.valueOf(merchant_trans_id),
								merchant_code, good_code, net_cost, ship_fee, tax,
								bank_code, service_code, version, command, currency_code,
								desc_1, desc_2, desc_3, desc_4, desc_5, xml_description,
								current_locale, country_code, return_url, internal_bank,
								merchant_secure_key);
				
						StringBuffer param = new StringBuffer();
						param.append("merchant_code=")
								.append(URLEncoder.encode(keypay.getMerchant_code(),
										"UTF-8")).append(StringPool.AMPERSAND);
						param.append("bank_code=")
								.append(URLEncoder.encode(keypay.getBank_code(), "UTF-8"))
								.append(StringPool.AMPERSAND);
						param.append("internal_bank=")
								.append(URLEncoder.encode(keypay.getInternal_bank(),
										"UTF-8")).append(StringPool.AMPERSAND);
						param.append("merchant_trans_id=")
								.append(URLEncoder.encode(keypay.getMerchant_trans_id(),
										"UTF-8")).append(StringPool.AMPERSAND);
						param.append("good_code=")
								.append(URLEncoder.encode(keypay.getGood_code(), "UTF-8"))
								.append(StringPool.AMPERSAND);
						param.append("net_cost=")
								.append(URLEncoder.encode(keypay.getNet_cost(), "UTF-8"))
								.append(StringPool.AMPERSAND);
						param.append("ship_fee=")
								.append(URLEncoder.encode(keypay.getShip_fee(), "UTF-8"))
								.append(StringPool.AMPERSAND);
						param.append("tax=")
								.append(URLEncoder.encode(keypay.getTax(), "UTF-8"))
								.append(StringPool.AMPERSAND);
						
						param.append("version=")
								.append(URLEncoder.encode(keypay.getVersion(), "UTF-8"))
								.append(StringPool.AMPERSAND);
						param.append("command=")
								.append(URLEncoder.encode(keypay.getCommand(), "UTF-8"))
								.append(StringPool.AMPERSAND);
						param.append("current_locale=")
								.append(URLEncoder.encode(keypay.getCurrent_locale(),
										"UTF-8")).append(StringPool.AMPERSAND);
						param.append("currency_code=")
								.append(URLEncoder.encode(keypay.getCurrency_code(),
										"UTF-8")).append(StringPool.AMPERSAND);
						param.append("service_code=")
								.append(URLEncoder.encode(keypay.getService_code(), "UTF-8"))
								.append(StringPool.AMPERSAND);
						param.append("country_code=")
								.append(URLEncoder.encode(keypay.getCountry_code(), "UTF-8"))
								.append(StringPool.AMPERSAND);
						param.append("xml_description=")
								.append(URLEncoder.encode(keypay.getXml_description(),
										"UTF-8")).append(StringPool.AMPERSAND);
						param.append("secure_hash=").append(keypay.getSecure_hash()).append(StringPool.AMPERSAND);
						param.append("return_url=").append(URLEncoder.encode(keypay.getReturn_url(), "UTF-8"));
				
						url_redirect = paymentConfig.getKeypayDomain() + StringPool.QUESTION + param.toString();
						
						debitNote.setTransactionid(String.valueOf(merchant_trans_id));
						debitNote.setKeypayURL(url_redirect);
						
						TempDebitNoteLocalServiceUtil.updateTempDebitNote(debitNote);
					
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}
			}
		}
		
		return url_redirect;
	}
	
	public static String getConfigKeyPayURL(TempDebitnote debitNote) {
		
		String url_redirect = StringPool.BLANK;
		
		if(Validator.isNotNull(debitNote)) {
		
			url_redirect = debitNote.getKeypayURL();
			
			if(Validator.isNull(url_redirect)) {
			
				TempMaritimePaymentConfig paymentConfig = TempMaritimePaymentConfigLocalServiceUtil.getPaymentConfig(
					String.valueOf(debitNote.getMariTimeCode()));
			
				if(paymentConfig != null) {
					url_redirect = paymentConfig.getKeypayDomain() ;
				}
			}
		}
		return url_redirect;
	}
	
	private static long _genetatorTransactionId() {

		long transactionId = 0;
        transactionId = CounterLocalServiceUtil.increment("payment.keypay.genetatorTransactionId");
        return transactionId;
	}
	
	private static String generatorGoodCode(int length) {

		String tempGoodCode = PwdGenerator.getPassword(PwdGenerator.KEY1, length);

		String goodCode = StringPool.BLANK;

		goodCode = tempGoodCode;

		return goodCode;
	}
	
	public static JSONObject generatePaymentData(TempDebitnote debitNote) {
		JSONObject resultPaymentData = JSONFactoryUtil.createJSONObject();
		String url_redirect = StringPool.BLANK;
		
		if(Validator.isNotNull(debitNote)) {
		
			url_redirect = debitNote.getKeypayURL();
			
			if(Validator.isNull(url_redirect)) {
			
				TempMaritimePaymentConfig paymentConfig = TempMaritimePaymentConfigLocalServiceUtil.getPaymentConfig(
					String.valueOf(debitNote.getMariTimeCode()));
			
				if(paymentConfig != null) {
					long transaction_id = _genetatorTransactionId();
					String client_id = "CUC_HANG_HAI_TEST";
					
					String version = "3.0";
					String command = ConfigurationManager.getStrProp("vn.gt.keypay.command");
					String currency_code = ConfigurationManager.getStrProp("vn.gt.keypay.currency.code");
					String description = StringPool.BLANK;
					String current_locale = ConfigurationManager.getStrProp("vn.gt.keypay.current.locale");
					String country_code = ConfigurationManager.getStrProp("vn.gt.keypay.country.code");
					String environment = "1";
					String trans_amount = "0";
					String addition_fee = "0";
					String return_url = WebserviceUtil.getPaymentURL() + "/pay#/payment/" + debitNote.getDebitnotenumber() + "/" + debitNote.getDocumentName() + "/" + debitNote.getDocumentYear() + "/" + debitNote.getMariTimeCode() +"/success";
					String bill_info = "";
					String check_sum = "";
					String return_code  = "";
					String return_msg  = "";
					
					trans_amount = "1000000";
					currency_code = debitNote.getCurrency();
					
					KeyPayV3 keypay = new KeyPayV3(client_id,transaction_id+"",trans_amount,addition_fee,
							command,version,description,current_locale,currency_code,
							country_code,environment,bill_info, return_url,check_sum,
							return_code,return_msg);
					
					check_sum = KeyPayV3.getChecksum(keypay, "1");
					resultPaymentData.put("client_id", client_id);
					resultPaymentData.put("transaction_id", transaction_id);
					resultPaymentData.put("trans_amount", trans_amount);
					resultPaymentData.put("addition_fee", addition_fee);
					resultPaymentData.put("command", command);
					resultPaymentData.put("version", version);
					resultPaymentData.put("description", description);
					resultPaymentData.put("locale", current_locale);
					resultPaymentData.put("currency_code", currency_code);
					resultPaymentData.put("country_code", country_code);
					resultPaymentData.put("environment", 1); // Môi trường thanh toán 1-Web; 2-App
					resultPaymentData.put("return_url", return_url);
					resultPaymentData.put("check_sum", check_sum);
					
					JSONObject resultbill_info = JSONFactoryUtil.createJSONObject();
					
					
					resultbill_info.put("MaDichVu", 2);
					resultbill_info.put("TKThuHuong", paymentConfig.getMerchantCode());
					resultbill_info.put("MaNHThuHuong", paymentConfig.getMerchantKey());
					resultbill_info.put("TenTKThuHuong", paymentConfig.getMerchantName());
					
					try {
						resultbill_info.put("PhiLePhi", ""); // more
						if (Validator.isNotNull(debitNote.getForeigncurrency()) && debitNote.getForeigncurrency().equalsIgnoreCase("USD")) {
							
							
							resultbill_info.put("PhiNoiDia", debitNote.getTotalpayment());
							resultbill_info.put("DonViTien", "VND");
							resultbill_info.put("PhiNgoaiTe", debitNote.getTotalforeignpayment());
							resultbill_info.put("DonViNgoaiTe", "USD");
						} else {
							if (Validator.isNotNull(debitNote.getCurrency())){
								if (debitNote.getCurrency().equalsIgnoreCase("VND")) {
									resultbill_info.put("PhiNoiDia", debitNote.getTotalpayment());
									resultbill_info.put("DonViTien", "VND");
									resultbill_info.put("PhiNgoaiTe", debitNote.getTotalforeignpayment());
									resultbill_info.put("DonViNgoaiTe", "USD");
									
								} else if (debitNote.getCurrency().equalsIgnoreCase("USD")) {
									resultbill_info.put("PhiNoiDia", 0);
									resultbill_info.put("DonViTien", "VND");
									resultbill_info.put("PhiNgoaiTe", debitNote.getTotalpayment());
									resultbill_info.put("DonViNgoaiTe", "USD");
								}
							} else {
								resultbill_info.put("PhiNoiDia", debitNote.getTotalpayment());
								resultbill_info.put("DonViTien", "VND");
								resultbill_info.put("PhiNgoaiTe", debitNote.getTotalforeignpayment());
								resultbill_info.put("DonViNgoaiTe", "USD");
							}
						}
						
					} catch (Exception e) {
						
					}
					
					
					if (Validator.isNotNull(debitNote.getCorporationid())) {
						DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(debitNote.getCorporationid());						
						resultbill_info.put("MaDonVi", debitNote.getCorporationid());
						resultbill_info.put("TenDonVi", dmMaritime.getMaritimeNameVN());
					}
					resultbill_info.put("MaHoSo", debitNote.getDocumentName());
					resultbill_info.put("MaDVC", debitNote.getDocumentTypeCode());
					resultbill_info.put("TenDVC", debitNote.getDocumentTypeCode());
					resultbill_info.put("MaTTHC", debitNote.getDocumentTypeCode());
					resultbill_info.put("TenTTHC", debitNote.getDocumentTypeCode());
					resultbill_info.put("NoiDungThanhToan", "THU PHI TRONG TAI, CANG PHI, BĐHH, NEO");
					resultbill_info.put("MaLoaiHinhThuPhat", "");
					resultbill_info.put("TenLoaiHinhThuPhat", "");
					resultbill_info.put("HoTenNguoiNop", "");
					resultbill_info.put("SoCMNDNguoiNop", "");
					resultbill_info.put("DiaChiNguoiNop", "");
					resultbill_info.put("HuyenNguoiNop", "");
					resultbill_info.put("TinhNguoiNop", "");
					resultbill_info.put("MaCoQuanQD", "");
					resultbill_info.put("TenCoQuanQD", "");
					resultbill_info.put("KhoBac", "");
					resultbill_info.put("NgayQD", "");
					resultbill_info.put("SoQD", "");
					resultbill_info.put("ThoiGianViPham", "");
					resultbill_info.put("DiaDiemViPham", "");
					resultbill_info.put("TenNguoiViPham", "");
					resultbill_info.put("TaiKhoanThuNSNN", "");
					resultbill_info.put("DSKhoanNop", "");
						
					resultPaymentData.put("bill_info", resultbill_info);
					
					
				}
			}
		}
		return resultPaymentData;
	}
		
	
}
