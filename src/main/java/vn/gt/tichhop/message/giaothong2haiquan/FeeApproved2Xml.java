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
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.model.FeeApproved;





@Slf4j
public class FeeApproved2Xml {
	
	
	
	public FeeApproved insert2FeeApproved(long documentName, int  documentYear) throws Exception {
	
		FeeApproved item = null;

		try {
			item = new FeeApproved();
			
			TempDebitnote object = TempDebitNoteLocalServiceUtil
					.findByDocumentNameAnddocumentYear(documentName, documentYear);
			
			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
			
			if (object != null) {
				item.setDocumentName(String.valueOf(documentName));
				item.setDocumentYear(String.valueOf(documentYear));
				item.setUserCreated(tempDocument.getUserCreated());
				
				if(object.getPaymenttype() == 2) {
					item.setPaymentType(ConfigurationManager.getStrProp("vn.gt.payment.method.M2"));
				} else if(object.getPaymenttype() == 1) {
					item.setPaymentType(ConfigurationManager.getStrProp("vn.gt.payment.method.M1"));
				} else if(object.getPaymenttype() == 0) {
					item.setPaymentType(ConfigurationManager.getStrProp("vn.gt.payment.method.M0"));
				}
				
				//TODO:
				//item.setPaymentName(tempDocument.get);
				//item.setPaymentDate(FormatData.parseDateToTring(value));
				
				String paymentPlace = "";
				
				if(Validator.isNotNull(object.getMariTimeCode())) {
					DmMaritime maritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(String.valueOf(object.getMariTimeCode()));
					
					if(maritime != null) {
						paymentPlace = maritime.getMaritimeNameVN();
					}
				}
				
				item.setPaymentPlace(paymentPlace);
				
				item.setComments(object.getComments());
				item.setInvoiceNo(object.getDebitnotenumber());
				item.setTotalFee(String.valueOf(object.getTotalpayment()));
				
				if(Validator.isNotNull(object.getCurrency())) {
					item.setCurrency(object.getCurrency());
				} else {
					item.setCurrency("VND");
				}
				
				item.setPaymentName(tempDocument.getUserCreated());
				item.setPaymentDate(FormatData.parseDateToTring(object.getReportdate()));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return item;
	}
}
