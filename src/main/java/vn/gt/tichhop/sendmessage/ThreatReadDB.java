package vn.gt.tichhop.sendmessage;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import vn.gt.utils.config.ConfigurationManager;



@Slf4j
public class ThreatReadDB {
	
	private static ThreatReadDB threatReadDB = null;
	
	private static List<ProcessReadDB> listThrea = new ArrayList<ProcessReadDB>();
	private int numberProcess = 1;
	private int timeSleep = 9000;
	
	public static ThreatReadDB init() {
//		log.info("====Go to ThreatReadDB init()====  ");
		try {
            if (threatReadDB == null) {
            	int numberProcess = ConfigurationManager.getIntProp("vn.gt.count.processreaddb", 1);
            	int timeSleep = ConfigurationManager.getIntProp("vn.gt.time.sleep.process.readerdb", 9000);
            	threatReadDB = new ThreatReadDB(numberProcess, timeSleep);
            	threatReadDB.startProcess();
            }
            return threatReadDB;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	
	private ThreatReadDB(int numberProcess, int timeSleep) {
		this.numberProcess = numberProcess;
		this.timeSleep = timeSleep;
//		log.debug("==========Initing ThreatSentMessage with numberProcess:" + this.numberProcess + "=======timeSleep===" + this.timeSleep);
	}
	
	private void startProcess() {
		ProcessReadDB processReadDb = null;
		
		if (listThrea != null && listThrea.size() == 0) {
		
			for (int i = 1; i <= this.numberProcess; i++) {
				processReadDb = new ProcessReadDB("===ProcessReadDB===" + i, this.timeSleep);
				processReadDb.start();
				listThrea.add(processReadDb);
			}
		}
	}
}
