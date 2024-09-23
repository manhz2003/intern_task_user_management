package vn.gt.tichhop.report.ShipSecurityNotification;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
import com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems;

public class ShipSecurityNotificationModel {
	
	private List<TempShipSecurityMessage> tempShipSecurityMessages;
	private List<TempShipSecurityPortItems> tempCrewLists;
	private String documentTypeCode;
	
	public ShipSecurityNotificationModel(List<TempShipSecurityMessage> tempShipSecurityMessages, List<TempShipSecurityPortItems> tempCrewLists) {
		this.tempShipSecurityMessages = tempShipSecurityMessages;
		this.tempCrewLists = tempCrewLists;
	}
	
	public ShipSecurityNotificationModel() {
		
	}
	
	public List<TempShipSecurityMessage> getTempShipSecurityMessages() {
		return tempShipSecurityMessages;
	}
	
	public void setTempShipSecurityMessages(List<TempShipSecurityMessage> tempShipSecurityMessages) {
		this.tempShipSecurityMessages = tempShipSecurityMessages;
	}
	
	public List<TempShipSecurityPortItems> getTempCrewLists() {
		return tempCrewLists;
	}
	
	public void setTempCrewLists(List<TempShipSecurityPortItems> tempCrewLists) {
		this.tempCrewLists = tempCrewLists;
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
	
}
