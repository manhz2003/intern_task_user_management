package vn.gt.payment.servlet;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.gt.payment.keypay.model.KeyPay;
import vn.gt.payment.keypay.model.KeyPayV3;
import vn.gt.payment.util.PaymentMgtUtil;

import org.json.JSONObject;



public class PaymentCallback extends HttpServlet {

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {


		//KeyPay keyPay = new KeyPay(request);
				
		//PaymentMgtUtil.runKeyPayGateData(request, response, keyPay);
		
		KeyPayV3 keyPay = new KeyPayV3(request);
		PaymentMgtUtil.runKeyPayGateDataV3(request, response, keyPay);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest resourceRequest, HttpServletResponse resourceResponse)
		throws  IOException {


		//KeyPay keyPay = new KeyPay(request);
		
		//PaymentMgtUtil.runKeyPayGateData(request, response, keyPay);
		
		KeyPayV3 keyPay = new KeyPayV3(resourceRequest);
		PaymentMgtUtil.runKeyPayGateDataV3(resourceRequest, resourceResponse, keyPay);		
	}
}
