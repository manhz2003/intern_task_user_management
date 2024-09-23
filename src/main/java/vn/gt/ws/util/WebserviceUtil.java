package vn.gt.ws.util;


import com.fds.nsw.nghiepvu.model.UserPort;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmGtReportCategory;
import vn.gt.dao.danhmucgt.service.DmGtReportCategoryLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig;
import vn.gt.dao.noticeandmessage.service.VmaVnptServiceConfigLocalServiceUtil;
import vn.gt.utils.config.ConfigurationManager;



import com.fds.flex.common.ultility.GetterUtil;
//import com.liferay.portal.kernel.util.PropsUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.core.ThemeDisplay;
//todo load PropsUtil by .properties in spring
@Slf4j
public class WebserviceUtil {
	public static String getWebserviceELComURL(String wsAddressCode) {
		String key = "ws.elcom.url";
		
		if(Validator.isNotNull(wsAddressCode)) {
			key += "." + wsAddressCode.toLowerCase();
		}
		
//		String url = PropsUtil.get(key);
		String url = "PropsUtil.get(key)";

		if(Validator.isNull(url)) {
			url = ConfigurationManager.getStrProp(key);
		}
		
		log.info("===ws.elcom.url===" + key + "===" + url);
		
		return url;
	}
	
	public static String getWebserviceHQURL() {
		String key ="ws.hq.url";
		String url = "PropsUtil.get(key)";
//		String url = PropsUtil.get(key);

		if(Validator.isNull(url)) {
			url = ConfigurationManager.getStrProp(key);
		}
		
		return url;
	}
	
	public static String getWebserviceHQv2URL() {
		String key ="ws.hq.v2.url";
		String url = "PropsUtil.get(key)";
//		String url = PropsUtil.get(key);

		if(Validator.isNull(url)) {
			url = ConfigurationManager.getStrProp(key);
		}
		
		return url;
	}
	
	public static String getWebserviceCongBGTVTURL() {
		String key ="ws.congbogtvt.url";
		String url = "PropsUtil.get(key);";
//		String url = PropsUtil.get(key);

		if(Validator.isNull(url)) {
			url = ConfigurationManager.getStrProp(key);
		}
		
		return url;
	}

	public static int checkGateway() {
		String key = "vn.gt.gateway";
		
//		String gateway = PropsUtil.get("vn.gt.gateway");

		String gateway= "";
		if(Validator.isNull(gateway)) {
			gateway = ConfigurationManager.getStrProp(key);
		}
		
		return GetterUtil.getInteger(gateway);
	}
	
	public static String getPaymentURL() {
		String key = "vn.gt.payment.url";
		
//		String paymentURL = PropsUtil.get("vn.gt.payment.url");

		String paymentURL = "";
		if(Validator.isNull(paymentURL)) {
			paymentURL = ConfigurationManager.getStrProp(key);
		}
		
		return paymentURL;
	}
	
	public static String getKeypayRedirectUrl() {
		String key = "vn.gt.keypay.redirect.url";
		//todo load PropsUtil by .properties in spring
//		String paymentURL = PropsUtil.get("vn.gt.keypay.redirect.url");
		String paymentURL = "";

		if(Validator.isNull(paymentURL)) {
			paymentURL = ConfigurationManager.getStrProp(key);
		}
		
		return paymentURL;
	}
	
	public static String getWebserviceE_Receipt() {
		String url = "";
		
		int testMode = 9;		
		VmaVnptServiceConfig ProdserviceConf = VmaVnptServiceConfigLocalServiceUtil.findByTestMode(testMode);
		
		
		if (Validator.isNotNull(ProdserviceConf)) {
			String maritimeCode = ProdserviceConf.getMaritimeCode();		
			
			DmGtReportCategory dmGtReportCategory = DmGtReportCategoryLocalServiceUtil
					.findByOrganizationCode(maritimeCode);
			
			if (Validator.isNotNull(ProdserviceConf) && ProdserviceConf.getTestMode() >= 9 && Validator.isNotNull(dmGtReportCategory)) {				
				url = ProdserviceConf.getPublishServiceSoapAddress();
			}
		} else {
			testMode = 1;
			VmaVnptServiceConfig serviceConf = VmaVnptServiceConfigLocalServiceUtil.findByTestMode(testMode);
			if (Validator.isNotNull(serviceConf))  {
				String maritimeCode = serviceConf.getMaritimeCode();		
				
				DmGtReportCategory dmGtReportCategory = DmGtReportCategoryLocalServiceUtil
						.findByOrganizationCode(maritimeCode);
				
				if (Validator.isNotNull(serviceConf) && serviceConf.getTestMode() == 1 && Validator.isNotNull(dmGtReportCategory)) {
					url = serviceConf.getTestSoapAddress();			
				}
			} else if (serviceConf == null && ProdserviceConf == null){
				log.info("Chua cau hinh tham so he thong Bien lai dien tu");
			} 
			} 
		
			
		return url;
	}
	
	
}
