package vn.gt.tichhop.report.ShipStoresDeclaration;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration;
import com.fds.nsw.nghiepvu.model.TempShipStoresItems;


public class ShipStoresDeclarationModel {
	
	private List<TempShipStoresDeclaration> tempShipStoresDeclarationes;
	private List<TempShipStoresItems> tempShipStoresItems;
	private String signDate;
	private String signPlace;
	private String documentTypeCode;
	
	public ShipStoresDeclarationModel() {};

	public ShipStoresDeclarationModel(List<TempShipStoresDeclaration> tempShipStoresDeclarationes,
			List<TempShipStoresItems> tempShipStoresItems, String signDate, String signPlace) {
		super();
		this.tempShipStoresDeclarationes = tempShipStoresDeclarationes;
		this.tempShipStoresItems = tempShipStoresItems;
		this.signDate = signDate;
		this.signPlace = signPlace;
	}

	public List<TempShipStoresDeclaration> getTempShipStoresDeclarationes() {
		return tempShipStoresDeclarationes;
	}
	
	public void setTempShipStoresDeclarationes(List<TempShipStoresDeclaration> tempShipStoresDeclarationes) {
		this.tempShipStoresDeclarationes = tempShipStoresDeclarationes;
	}
	
	public List<TempShipStoresItems> getTempShipStoresItems() {
		return tempShipStoresItems;
	}
	
	public void setTempShipStoresItems(List<TempShipStoresItems> tempShipStoresItems) {
		this.tempShipStoresItems = tempShipStoresItems;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
}
