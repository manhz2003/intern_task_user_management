package vn.gt.utils;

import lombok.extern.slf4j.Slf4j;
//import org.tempuri.IMTService;
//import org.tempuri.MTServiceLocator;



import com.fds.flex.common.ultility.Validator;

import vn.dtt.duongbo.ws.MessageAndResponseImpl;
import vn.dtt.duongbo.ws.MessageAndResponseImplService;
import vn.dtt.duongbo.ws.MessageAndResponseImplServiceLocator;
import vn.gt.ws.util.WebserviceUtil;

@Slf4j
public class CallWs2HaiQuan {
//	public static IMTService getIMTSercice() {
//
//		return getIMTSercice(null);
//	}

	
//	public static IMTService getIMTSercice(String address) {
//
//		try {
//			if(Validator.isNull(address)) {
//				address = WebserviceUtil.getWebserviceHQURL();
//			}
//
//			MTServiceLocator mtService = new MTServiceLocator();
//			mtService.setBasicHttpBinding_IMTServiceEndpointAddress(address);
//
//			IMTService imtService = mtService.getBasicHttpBinding_IMTService();
//			return imtService;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//
//		return null;
//	}
//
	public static MessageAndResponseImpl getMessageAndResponseImpl() {
		try {
			MessageAndResponseImplService mtService = new MessageAndResponseImplServiceLocator();
			MessageAndResponseImpl imtService = mtService.getMessageAndResponseImpl();
			return imtService;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	
	
}
