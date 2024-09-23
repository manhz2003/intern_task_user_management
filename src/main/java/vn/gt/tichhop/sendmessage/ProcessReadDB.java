package vn.gt.tichhop.sendmessage;

import java.util.List;

import com.fds.nsw.nghiepvu.model.AswmsgMessagequeue;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.asw.service.AswmsgMessageQueueLocalServiceUtil;
import vn.gt.utils.config.ConfigurationManager;
import vn.gt.ws.util.WebserviceUtil;



@Slf4j
public class ProcessReadDB implements Runnable {

	private Thread t;
	private int timeSleep = 900;
	private String threadName;
	
	public ProcessReadDB(String name, int timeSleep) {
		this.threadName = name;
		this.timeSleep = timeSleep;
	}
	
	public void run() {
		int count = ConfigurationManager.getIntProp("vn.gt.count.sendmessage", 20);
		
		int check = WebserviceUtil.checkGateway();//ConfigurationManager.getIntProp("vn.gt.coguihaykhong", 1);
		
		if (check > 0) {
			//cho nay la day het cac message vao DB queue
			while (true) {
				int size = ThreatSentMessage.sendMessageQueue.size();
				
				if (size == 0) {
					try {
						List<AswmsgMessagequeue> aswmsgMessageQueues = AswmsgMessageQueueLocalServiceUtil.getListMessage2Queue(count);
						
						if (aswmsgMessageQueues != null && aswmsgMessageQueues.size() > 0) {
							//check in queue
							StringBuilder sb = new StringBuilder();
							
							for(AswmsgMessagequeue messageQueueTmp : aswmsgMessageQueues) {
								//danh dau trang thai dang duoc xu ly
								messageQueueTmp.setValidated(0);
								AswmsgMessageQueueLocalServiceUtil.updateAswmsgMessageQueue(messageQueueTmp);
								
								ThreatSentMessage.sendMessageQueue.add(messageQueueTmp);
								
								sb.append(messageQueueTmp.getId() + ";");
							}
							
							log.info("===messageQueue===ProcessReadDB===" + sb.toString());
						}
						
						Thread.sleep(timeSleep);
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}
			}
		}
	}
	
	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
			log.info("===Starting-------------- " + t.getName());
		}
	}
	
}
