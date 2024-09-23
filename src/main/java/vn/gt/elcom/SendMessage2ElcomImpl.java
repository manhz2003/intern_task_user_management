package vn.gt.elcom;




import lombok.extern.slf4j.Slf4j;
import vn.gt.tichhop.threat.ObjectExcute;
import vn.gt.tichhop.threat.ProcessExecuteMessageTichHopCangUtils;

/**
 * 
 * Soap service nhan msg tu cang vu
 * 
 * @author huymq
 *
 */
@Slf4j
public class SendMessage2ElcomImpl implements ISendMessage2Elcom {
	public SendMessage2ElcomImpl() {

	}

	@Override
	public String sendAndGetMessage(String requestMessage) {
		log.info("===Message==from==CangVu==="+requestMessage);
		
		ProcessExecuteMessageTichHopCangUtils processExecuteMessageTichHopCangUtils = new ProcessExecuteMessageTichHopCangUtils();
		String xml =processExecuteMessageTichHopCangUtils.nhanThongTinCangVu(new ObjectExcute(null, null, requestMessage));
	
		log.info("===Message===tra===lai===CangVu==="+xml);
		return xml;

	}
	
	

}
