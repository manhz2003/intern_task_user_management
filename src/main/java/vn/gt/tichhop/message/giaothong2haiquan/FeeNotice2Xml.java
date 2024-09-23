package vn.gt.tichhop.message.giaothong2haiquan;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDebitnote;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.utils.FormatData;
import vn.gt.ws.util.WebserviceUtil;
import vn.nsw.model.FeeNotice;




@Slf4j
public class FeeNotice2Xml {
	
	
	
	public FeeNotice insert2FeeNotice(long documentName, int  documentYear) throws Exception {
	
		FeeNotice item = null;

		try {
			item = new FeeNotice();
			
			TempDebitnote object = TempDebitNoteLocalServiceUtil
					.findByDocumentNameAnddocumentYear(documentName, documentYear);
			
			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
			
			if (object != null) {
				item.setDocumentName(String.valueOf(documentName));
				item.setDocumentYear(String.valueOf(documentYear));
				item.setUserCreated(tempDocument.getUserCreated());
				
				if(Validator.isNotNull(object.getMariTimeCode())) {
					DmMaritime maritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(String.valueOf(object.getMariTimeCode()));
					
					if(maritime != null) {
						item.setDeptCode(String.valueOf(object.getMariTimeCode()));
						item.setDeptName(maritime.getMaritimeNameVN());
					}
				}
				
				item.setProcessName(object.getReportby());
				item.setProcessDate(FormatData.parseDateToTring(object.getReportdate()));
				item.setComments(Validator.isNotNull(object.getComments()) ? object.getComments() : "---");
				item.setInvoiceNo(object.getDebitnotenumber());
				item.setTotalFee(String.valueOf(object.getTotalpayment()));
				item.setInvoiceUrl(buildURL(documentName, documentYear, object.getDebitnotenumber(), item.getDeptCode()));
				if(Validator.isNotNull(object.getCurrency())) {
					item.setCurrency(object.getCurrency());
				} else {
					item.setCurrency("VND");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return item;
	}
	
	public static String buildURL(long documentName, int documentYear, String debitNoteNumbrer, String maritimeCode) {

		String url_redirect = WebserviceUtil.getPaymentURL() + "/pay#/payment/" + debitNoteNumbrer + "/" + documentName + "/" + documentYear + "/" + maritimeCode;
		
		/*try {
			Map<String, String> fields = new HashMap<String, String>();

			fields.put("documentname", String.valueOf(documentName));
			fields.put("documentyear", String.valueOf(documentYear));
			fields.put("debitnotenumber", debitNoteNumbrer);

			HashFunction hash = new HashFunction();
			
			String secure_hash = hash.hashAllFields(fields, ConfigurationManager.getStrProp("vn.gt.payment.secure.hash"));
			
			Iterator i = fields.keySet().iterator();
			
			String param = "";
			
			while (i.hasNext()) {
				String key = (String) i.next();
				String value = (String) fields.get(key);
				param += key + "=" + URLEncoder.encode(value, "UTF-8") + "&";
			}
			
			url_redirect += param + "secure_hash=" + secure_hash;
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}*/
		
		return url_redirect;
	}
}
