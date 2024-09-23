package vn.gt.payment.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fds.nsw.nghiepvu.model.TempDebitnote;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.payment.util.PaymentUrlGenerator;

import org.json.JSONException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.ultility.Validator;

import com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig;
import vn.gt.dao.noticeandmessage.service.TempMaritimePaymentConfigLocalServiceUtil;
@Slf4j
public class RedirectPayment extends HttpServlet {

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest resourceRequest, HttpServletResponse response)
		throws IOException {

		JSONObject tempDocumentOBJ = JSONFactoryUtil.createJSONObject();
		String idDebit = resourceRequest.getParameter("idDebit");
		String documentyear = resourceRequest.getParameter("documentyear");
		String documentname = resourceRequest.getParameter("documentname");
		String debitnotenumber = resourceRequest.getParameter("debitnotenumber");
		String maritimecode = resourceRequest.getParameter("maritimecode");
		String keypayDomain = "";
		try {
			TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil.getByNumberDebit(idDebit);
			
			String keyPayURL = PaymentUrlGenerator.getConfigKeyPayURL(tempDebitNote);						
			tempDocumentOBJ.put("keyPayURL", keyPayURL);
			
			JSONObject resultPaymentData = JSONFactoryUtil.createJSONObject();						
			resultPaymentData = PaymentUrlGenerator.generatePaymentData(tempDebitNote);
			tempDocumentOBJ.put("resultPaymentData", resultPaymentData);
		
			TempMaritimePaymentConfig paymentConfig = TempMaritimePaymentConfigLocalServiceUtil.getPaymentConfig(
					String.valueOf(maritimecode));
			
				if(paymentConfig != null) {
					keypayDomain = paymentConfig.getKeypayDomain();
				}
			JSONObject resultData = POST(resultPaymentData.toString(), keypayDomain, "");
			if (Validator.isNotNull(resultData) && Validator.isNotNull(resultData.getString("payment_url")) &&  resultData.getString("error").equalsIgnoreCase("0")) {
				String keypayRedirectUrl = resultData.getString("payment_url");
								
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(resultData.toString());
			    log.info("error =====" + resultData.getString("error") + "=======payment_url=====" + resultData.getString("payment_url") );
			} else {
				log.info("error =====" + resultData.getString("error") + "=======payment_url=====" + resultData.getString("payment_url") );
				resultData.put("error",  resultData.getString("error"));
				resultData.put("msg",  resultData.getString("msg"));
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(resultData.toString());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject resultData = JSONFactoryUtil.createJSONObject();
			resultData.put("error",  resultData.getString("error"));
			resultData.put("msg",  resultData.getString("msg"));
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest resourceRequest, HttpServletResponse resourceResponse)
		throws  IOException {

		log.info("=====RedirectPayment===doPost():" + resourceRequest);
		
			
	}
	
	public static JSONObject POST(String body, String keypayDomain) throws IOException, JSONException, InterruptedException {

	    log.info("====START POST RedirectPayment==== ");
	    JSONObject result = JSONFactoryUtil.createJSONObject();

	    Process p = Runtime
	            .getRuntime()
	            .exec("curl --insecure --request POST "
	                + keypayDomain
	                + " --header Content-Type:application/json --data-urlencode "
	                + body);

	        InputStream is = p.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader((is)));
	        StringBuilder results = new StringBuilder();
	        String output;
	        while ((output = br.readLine()) != null) {
	          results.append(output);
	          results.append(System.getProperty("line.separator"));
	        }

	        br.close();
	        p.waitFor();
	        p.destroy();
	        
	    	    log.info("========body=========="+body);
	    	    log.info("====results==== "+results);
	    	    
	    	    result = JSONFactoryUtil.createJSONObject(results.toString());
	    	    return result;
	  }

	      
	public static JSONObject POST(String body, String keypayDomain, String st) {

	    log.info("====START POST ES==== ");
	    JSONObject result = JSONFactoryUtil.createJSONObject();

	    try {

	    	// Create a trust manager that does not validate certificate chains
	    	TrustManager[] trustAllCerts = new TrustManager[]{
	    	    new X509TrustManager() {
	    	        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	    	            return null;
	    	        }
	    	        public void checkClientTrusted(
	    	            java.security.cert.X509Certificate[] certs, String authType) {
	    	        }
	    	        public void checkServerTrusted(
	    	            java.security.cert.X509Certificate[] certs, String authType) {
	    	        }
	    	    }
	    	};

	    	// Install the all-trusting trust manager
	    	try {
	    	    SSLContext sc = SSLContext.getInstance("SSL");
	    	    sc.init(null, trustAllCerts, new java.security.SecureRandom());
	    	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	    	} catch (Exception e) {
	    	}

	    	// Now you can access an https URL without having the certificate in the truststore
	    	/*try {
	    	    URL url = new URL("https://hostname/index.html");
	    	} catch (MalformedURLException e) {
	    	}*/
	      URL url = new URL(keypayDomain);

	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	      conn.setRequestMethod("POST");
	      conn.setRequestProperty("Accept", "Content-Type: application/json");
	      conn.setRequestProperty("Content-Type", "application/json");
	      conn.setDoInput(true);
	      conn.setDoOutput(true);
	      
	      try{
	    	  OutputStream os = conn.getOutputStream(); 
	    	    byte[] input = body.getBytes("utf-8");
	    	    os.write(input, 0, input.length);		
	    	    os.flush();
	    	    os.close();
	    	} catch (Exception e) {
	    		
	    	}
	      /*OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
	      osw.write(body);
	      osw.flush();
	      osw.close();*/

	      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

	      String output;

	      StringBuilder sb = new StringBuilder();

	      while ((output = br.readLine()) != null) {
	        sb.append(output);
	      }

	      result = JSONFactoryUtil.createJSONObject(sb.toString());
	      log.info("====END POST ES==== ");
	      log.info("========body=========="+body);
  	    	log.info("====results==== "+result);
	    } catch (Exception e) {
	    }

	    return result;
	  }
}
