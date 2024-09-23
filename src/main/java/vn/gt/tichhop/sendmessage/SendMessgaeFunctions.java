package vn.gt.tichhop.sendmessage;

import java.util.Date;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.AswmsgMessagequeue;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.asw.service.AswmsgMessageQueueLocalServiceUtil;
import vn.nsw.Header;



@Slf4j
public class SendMessgaeFunctions {
	
	
	
	public static long insertMessageQueue(Header header, String data, String direction, String requestCode, String webservice) {
		return insertMessageQueue(header, data, direction, requestCode, webservice, MessgaePriorityLevels.HIGHT);
	}
	
	public static long insertMessageQueue(Header header, String data, String direction, String requestCode, int level) {
		return insertMessageQueue(header, data, direction, requestCode, "-1", level);
	}
	
	private static long insertMessageQueue(Header header, String data, String direction, String requestCode, String webservice, int level) {
		try {
			long id = CounterLocalServiceUtil.increment(SendMessgaeFunctions.class.toString());
			
			AswmsgMessagequeue aswmsgMessageQueue = AswmsgMessageQueueLocalServiceUtil.createAswmsgMessageQueue(id);
			
			if (requestCode == null) {
				aswmsgMessageQueue.setMessageid(header.getReference().getMessageId());
			} else {
				aswmsgMessageQueue.setMessageid(requestCode);
			}
			
			//TODO: HUYMQ FIX FOR INCRE PERFORMANCE QUEUE DO DONG QUEUE KHI GUI XUONG CANG VU
//			if(!webservice.equals("-1")) {
//				level = MessgaePriorityLevels.LOWER;
//			}
			
			aswmsgMessageQueue.setVersion(header.getReference().getVersion());
			aswmsgMessageQueue.setSendername(header.getFrom().getName());
			aswmsgMessageQueue.setSenderidentity(header.getFrom().getIdentity());
			aswmsgMessageQueue.setSendercountrycode(header.getFrom().getCountryCode());
			aswmsgMessageQueue.setSenderministrycode(header.getFrom().getMinistryCode());
			aswmsgMessageQueue.setSenderorganizationcode(header.getFrom().getOrganizationCode());
			aswmsgMessageQueue.setSenderunitcode(header.getFrom().getUnitCode());
			aswmsgMessageQueue.setPriority(level);
			aswmsgMessageQueue.setReceivername(header.getTo().getName());
			aswmsgMessageQueue.setReceiveridentity(header.getTo().getIdentity());
			aswmsgMessageQueue.setReceivercountrycode(header.getTo().getCountryCode());
			aswmsgMessageQueue.setReceiverministrycode(header.getTo().getMinistryCode());
			aswmsgMessageQueue.setReceiverorganizationcode(header.getTo().getOrganizationCode());
			aswmsgMessageQueue.setReceiverunitcode(header.getTo().getUnitCode());
			
			aswmsgMessageQueue.setDocumenttype(String.valueOf(header.getSubject().getDocumentType()));
			aswmsgMessageQueue.setType(header.getSubject().getType());
			aswmsgMessageQueue.setFunction(header.getSubject().getFunction());
			aswmsgMessageQueue.setReference(header.getSubject().getReference());
			aswmsgMessageQueue.setPrereference(header.getSubject().getPreReference());
			aswmsgMessageQueue.setDocumentyear(header.getSubject().getDocumentYear());
			aswmsgMessageQueue.setSenddate(vn.gt.utils.FormatData.parseStringToDate(header.getSubject().getSendDate()));
			aswmsgMessageQueue.setSignature("hashcode");
			aswmsgMessageQueue.setSystemsignature("hashcode");
			aswmsgMessageQueue.setAllcontent(data);
			aswmsgMessageQueue.setCreatedtime(new Date());
			aswmsgMessageQueue.setWebservice(webservice);
			aswmsgMessageQueue.setValidated(-1);
			aswmsgMessageQueue.setValidationcode(direction);
			
			//TODO: SONVH FIX FOR INCREASING PERFORMANCE QUEUE (NSW DELAY)
			if(webservice.equals("-1") 
					&& direction.equalsIgnoreCase("NSW") 
					&& (!aswmsgMessageQueue.getFunction().equals("27"))
					&& (!aswmsgMessageQueue.getFunction().equals("28"))
					) {
				level = MessgaePriorityLevels.LOWER;
				aswmsgMessageQueue.setPriority(level);

			}
			
			aswmsgMessageQueue = AswmsgMessageQueueLocalServiceUtil.updateAswmsgMessageQueue(aswmsgMessageQueue);
			
			return id;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return 0;
	}

}
