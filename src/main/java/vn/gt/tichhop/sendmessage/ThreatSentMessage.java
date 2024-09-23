package vn.gt.tichhop.sendmessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;




import com.fds.nsw.nghiepvu.model.AswmsgMessagequeue;
import lombok.extern.slf4j.Slf4j;
import vn.gt.utils.config.ConfigurationManager;

@Slf4j
public class ThreatSentMessage {
	
	
	
	private static ThreatSentMessage threadPassingMessage = null;
	
	public static ConcurrentLinkedQueue<AswmsgMessagequeue> sendMessageQueue = new ConcurrentLinkedQueue<AswmsgMessagequeue>();
	private static List<ProcessSendMessage> listThrea = new ArrayList<ProcessSendMessage>();
	private int numberProcess = 2;
	private int timeSleep = 900;
	
	public static ThreatSentMessage init() {
		try {
			if (threadPassingMessage == null) {
				int numberProcess = ConfigurationManager.getIntProp("vn.gt.number.process.sendmessage", 1);
				int timeSleep = ConfigurationManager.getIntProp("vn.gt.time.sleep.process.sendmessage", 900);
				threadPassingMessage = new ThreatSentMessage(numberProcess, timeSleep);
				threadPassingMessage.startProcess();
			}
			
			return threadPassingMessage;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	
	private ThreatSentMessage(int numberProcess, int timeSleep) {
		this.numberProcess = numberProcess;
		this.timeSleep = timeSleep;
//		log.debug("==========Initing ThreatSentMessage with numberProcess:" + this.numberProcess + "=======timeSleep===" + this.timeSleep);
	}
	
	private void startProcess() {
		ProcessSendMessage processExecuteMessage = null;
		
		if (listThrea != null && listThrea.size() == 0) {
		
			for (int i = 1; i <= this.numberProcess; i++) {
//				log.info("==go to start process==");
				processExecuteMessage = new ProcessSendMessage("===ProcessSendMessage===" + i, this.timeSleep);
				processExecuteMessage.start();
				listThrea.add(processExecuteMessage);
				
			}
		}
	}
}
