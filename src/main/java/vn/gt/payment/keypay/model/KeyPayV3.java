package vn.gt.payment.keypay.model;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.noticeandmessage.service.TempMaritimePaymentConfigLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDebitnote;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.payment.security.HashFunction;



import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;

@Slf4j
public class KeyPayV3 {
	
	// Các tham số gửi đi KeyPay
	private String client_id;
	private String transaction_id;
	private String trans_amount;
	private String addition_fee;
	private String command;
	private String version;
	
	private String description;
	private String locale;
	private String currency_code;
	private String country_code;
	private String environment;
	//Thông tin chi tiết về đơn hàng, kiểu JSON
	private String bill_info;
	//Link keypay redirect về để trả kết quả giao dịch 
	private String return_url; 
	//Chuỗi mãhóa tạo ra dựa trên các trường dữ liệu 	
	private String check_sum;
	
	private String merchant_secure_key;
	
	// Các tham số trả về từ KeyPay
	private String payment_url;
	private String error ;
	private String msg;
	private String data;
	private String status ;
	private String payment_fee ;
	private String transaction_code ;
		
	// Dữ liệu trả về cho Keypay khi nhận callback	
	private String return_code ;
	private String return_msg ;
	
	public KeyPayV3() {

	}

	
	public KeyPayV3(
			String client_id,
			String transaction_id,
			String trans_amount,
			String addition_fee,
			String command,
			String version,	
			String description,
			String locale,
			String currency_code,
			String country_code,
			String environment,	
			String bill_info,
			String return_url, 
			String check_sum,
			String return_code ,
			String return_msg) {

		this.client_id = client_id;
		this.transaction_id = transaction_id;
		this.trans_amount = trans_amount;
		this.addition_fee = addition_fee;
		this.command = command;
		this.version = version;
		this.description = description;
		this.locale = locale;
		this.currency_code = currency_code;
		this.country_code = country_code;
		this.environment = environment;
		this.bill_info = bill_info;
		this.return_url = return_url;
		this.check_sum = check_sum;
		this.return_code = return_code;
		this.return_msg = return_msg;
		
		
	}
	
	


	/**
	 * Constructor - Lay du lieu tra ve tu KeyPay
	 *
	 * @param request
	 */
	public KeyPayV3(HttpServletRequest request) {

		try {
			this.client_id = request.getParameter("client_id");
			this.transaction_id = request.getParameter("transaction_id");
			this.trans_amount = request.getParameter("trans_amount");
			this.addition_fee = request.getParameter("addition_fee");
			this.command = request.getParameter("command");
			this.version = request.getParameter("version");
			this.description = new String(request.getParameter("description").getBytes("ISO-8859-1"), "UTF-8");
			this.locale = request.getParameter("locale");
			this.currency_code = request.getParameter("currency_code");
			this.country_code = request.getParameter("country_code");
			this.currency_code = request.getParameter("currency_code");
			this.environment = request.getParameter("environment");
			this.bill_info = request.getParameter("bill_info");
			this.return_url = request.getParameter("return_url");
			this.check_sum = request.getParameter("check_sum");
			this.return_code = request.getParameter("return_code");
			this.return_msg = request.getParameter("return_msg");
			this.merchant_secure_key = request.getParameter("merchant_secure_key");
			this.payment_url = request.getParameter("payment_url");
			this.error = request.getParameter("error");
			this.msg = request.getParameter("msg");
			this.data = request.getParameter("data");
			this.status = request.getParameter("status");
			this.payment_fee = request.getParameter("payment_fee");
			this.transaction_code = request.getParameter("transaction_code");
				
			
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	
	
	
	public static String getChecksum(KeyPayV3 keyPay, String markupClientKey) {

		Map<String, String> fields = new HashMap<String, String>();

		String addition_fee = keyPay.getAddition_fee();
		String client_id = keyPay.getClient_id();  
		String command = keyPay.getCommand();
		String trans_amount = keyPay.getTrans_amount();
		String transaction_id = keyPay.getTransaction_id();
		String version = keyPay.getVersion();

		String ClientKey1 = "MTY5ZTYwZDdlZWE2ZDI0NDkzZTAxMGU1NDQ0NTNlMTU="; 
		String ClientKey2 = "MWMwMWJlODNmNjI5ZjliMDA2OTYzMDJiNjY2NGY4NjE=";
		try{
			String secret ="MTY5ZTYwZDdlZWE2ZDI0NDkzZTAxMGU1NDQ0NTNlMTU="; // ClientKey1
			if (markupClientKey.contains("2")) {
				secret ="MWMwMWJlODNmNjI5ZjliMDA2OTYzMDJiNjY2NGY4NjE="; // ClientKey2
			}
			String message = addition_fee + "|"  + client_id  + "|" + command + "|" +  trans_amount + "|" + transaction_id + "|" + version;
			Mac sha256_HMAC =Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(),"HmacSHA256");
			sha256_HMAC.init(secret_key);
			byte[] hash = sha256_HMAC.doFinal(message.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			System.out.println(hash);
			System.out.println(sb);
			return sb.toString();
		} catch(Exception e){
				System.out.println("Error");
				return "";
		}
	}

	/**
	 * Kiem tra secure hash co dung khong
	 *
	 * @param keyPay
	 * @return
	 */
	public static boolean checkSecureHash(KeyPayV3 keyPay) {

		String merchantSig = KeyPayV3.getChecksum(keyPay, "ClientKey2");
		
		log.info("keyPay.merchantSig:" + merchantSig);
		log.info("keyPay.getSecure_hash():" + keyPay.getCheck_sum());

		if (keyPay.getCheck_sum().equalsIgnoreCase(merchantSig)) {
			return true;
		}
		else {
			log.info("keyPay.getCheck_sum(): NOT MATCH");
			return false;
		}
	}
	
	public String getMerchant_secure_key() {

		return merchant_secure_key;
	}

	public void setMerchant_secure_key(String merchant_secure_key) {

		this.merchant_secure_key = merchant_secure_key;
	}

	public String getClient_id() {

		return client_id;
	}

	public void setClient_id(String client_id) {

		this.client_id = client_id;
	}

	public String getTransaction_id() {

		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {

		this.transaction_id = transaction_id;
	}

	public String getTrans_amount() {

		return trans_amount;
	}

	public void setTrans_amount(String trans_amount) {

		this.trans_amount = trans_amount;
	}

	public String getAddition_fee() {

		return addition_fee;
	}

	public void setAddition_fee(String addition_fee) {

		this.addition_fee = addition_fee;
	}

	public String getVersion() {

		return version;
	}

	public void setVersion(String version) {

		this.version = version;
	}

	public String getCommand() {

		return command;
	}

	public void setCommand(String command) {

		this.command = command;
	}

	public String getCurrency_code() {

		return currency_code;
	}

	public void setCurrency_code(String currency_code) {

		this.currency_code = currency_code;
	}

	public String getCountry_code() {

		return country_code;
	}

	public void setCountry_code(String country_code) {

		this.country_code = country_code;
	}

	public String getReturn_url() {

		return return_url;
	}

	public void setReturn_url(String return_url) {

		this.return_url = return_url;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getLocale() {

		return locale;
	}

	public void setLocale(String locale) {

		this.locale = locale;
	}

	public String getEnvironment() {

		return environment;
	}

	public void setEnvironment(String environment) {

		this.environment = environment;
	}

	public String getBill_info() {

		return bill_info;
	}

	public void setBill_info(String bill_info) {

		this.bill_info = bill_info;
	}

	public String getCheck_sum() {

		return check_sum;
	}

	public void setCheck_sum(String check_sum) {

		this.check_sum = check_sum;
	}

	public String getReturn_code() {

		return return_code;
	}

	public void setReturn_code(String return_code) {

		this.return_code = return_code;
	}

	public String getReturn_msg() {

		return return_msg;
	}

	public void setReturn_msg(String return_msg) {

		this.return_msg = return_msg;
	}

	public String getPayment_url() {

		return payment_url;
	}

	public void setPayment_url(String payment_url) {

		this.payment_url = payment_url;
	}

	public String getError() {

		return error;
	}

	public void setError(String error) {

		this.error = error;
	}

	public String getMsg() {

		return msg;
	}

	public void setMsg(String msg) {

		this.msg = msg;
	}

	public String getData() {

		return data;
	}

	public void setData(String data) {

		this.data = data;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getPayment_fee() {

		return payment_fee;
	}

	public void setPayment_fee(String payment_fee) {

		this.payment_fee = payment_fee;
	}		

	public String getTransaction_code() {

		return transaction_code;
	}

	public void setTransaction_code(String transaction_code) {

		this.transaction_code = transaction_code;
	}

}
