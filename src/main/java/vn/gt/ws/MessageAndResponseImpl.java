package vn.gt.ws;




import lombok.extern.slf4j.Slf4j;
import vn.gt.facade.IMessageAndResponse;
import vn.gt.tichhop.message.BusinessUtils;

/**
 * 
 * Soap service nhan msg tu HQ
 * 
 * @author huymq
 *
 */
@Slf4j
public class MessageAndResponseImpl implements IMessageAndResponse {


	public MessageAndResponseImpl(){
	}

	public String sendAndGetMessage(String requestMessage) {

		try {
			return executeBusiness(requestMessage);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.info("============Truyen=sai=xml================" + requestMessage);
		
		return "Can not parse xml";
	}

	private String executeBusiness(String requestMessage) {
		BusinessUtils businessUtils = new BusinessUtils();
		log.info("=====Thong=tin=nhan=tu=HQMC===" + requestMessage);
		String xmlReturn = businessUtils.receiveMessage(requestMessage);
		log.info("============Tra=du=lieu=hai=quan================" + xmlReturn);
		return xmlReturn;
	}
}
