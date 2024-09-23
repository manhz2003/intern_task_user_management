/**
 * 
 */
package vn.gt.tichhop.threat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.fds.nsw.nghiepvu.model.DmGtRouteConfig;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmucgt.service.DmGtRouteConfigLocalServiceUtil;
import vn.gt.utils.config.ConfigurationManager;




/**
 * @author win_64
 */
@Slf4j
public class ThreadPassingMessage {
	
	private static ThreadPassingMessage threadPassingMessage = null;
	public static ConcurrentLinkedQueue<ObjectExcute> listData = new ConcurrentLinkedQueue<ObjectExcute>();
	private int numberProcess = 5;
	private int timeSleep = 1000;
	public static List<DmGtRouteConfig> liConfigs = new ArrayList<DmGtRouteConfig>();
	private static List<ProcessExecuteMessage> listThrea = new ArrayList<ProcessExecuteMessage>();
	
	public static ThreadPassingMessage init() {
		try {
			if (threadPassingMessage == null) {
				int numberProcess = ConfigurationManager.getIntProp("vn.gt.number.process", 5);
				int timeSleep = ConfigurationManager.getIntProp("vn.gt.time.sleep.process", 4000);
				liConfigs = DmGtRouteConfigLocalServiceUtil.findByIsDelete(0);
				threadPassingMessage = new ThreadPassingMessage(numberProcess, timeSleep);
				threadPassingMessage.startProcess();
			}
			return threadPassingMessage;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public void add(ObjectExcute excute) {
		listData.add(excute);
	}
	
	private ThreadPassingMessage(int numberProcess, int timeSleep) {
		this.numberProcess = numberProcess;
		this.timeSleep = timeSleep;
		log.info("==========Initing ThreadPassingMessage with numberProcess:" + this.numberProcess + "=======timeSleep===" + this.timeSleep);
	}
	
	private void startProcess() {
		ProcessExecuteMessage processExecuteMessage = null;
		
		if (listThrea != null && listThrea.size() == 0) {
			
			for (int i = 1; i <= this.numberProcess; i++) {
				processExecuteMessage = new ProcessExecuteMessage("===ProcessPassingMessage===" + i, this.timeSleep);
				processExecuteMessage.start();
				listThrea.add(processExecuteMessage);
			}
		}
	}
}
