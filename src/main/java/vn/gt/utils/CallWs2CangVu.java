package vn.gt.utils;



import com.fds.flex.common.ultility.Validator;

import lombok.extern.slf4j.Slf4j;
//import vn.com.elcom.www.VNMWSLocator;
//import vn.com.elcom.www.VNMWSSoap;
import vn.gt.elcom.client.SendMessage2ElcomImpl;
import vn.gt.elcom.client.SendMessage2ElcomImplServiceLocator;
import vn.gt.ws.util.WebserviceUtil;

@Slf4j
public class CallWs2CangVu {
	public static SendMessage2ElcomImpl getSendMessageImpl(String address) {
		SendMessage2ElcomImpl sendMessage2ElcomImpl = null;
		
		if(Validator.isNotNull(address)) {
			try {
				
					SendMessage2ElcomImplServiceLocator locator = new SendMessage2ElcomImplServiceLocator();
					locator.setSendMessage2ElcomImplEndpointAddress(address);
					
					sendMessage2ElcomImpl = locator.getSendMessage2ElcomImpl();
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		return sendMessage2ElcomImpl;
	}
	
//	public static VNMWSSoap getVNMWS(String wsAddressCode) {
//		VNMWSSoap vnmwsSoap = null;
//
//		try {
//			String wsAddress = WebserviceUtil.getWebserviceELComURL(wsAddressCode);
//
//			if(Validator.isNotNull(wsAddress)) {
//				VNMWSLocator locator = new vn.com.elcom.www.VNMWSLocator();
//				locator.setVNMWSSoapEndpointAddress(wsAddress);
//
//				vnmwsSoap = locator.getVNMWSSoap();
//			}
//
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//
//		return vnmwsSoap;
//	}
//
	
}
