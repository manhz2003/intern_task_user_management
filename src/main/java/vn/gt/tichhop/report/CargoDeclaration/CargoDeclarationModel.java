package vn.gt.tichhop.report.CargoDeclaration;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempCargoDeclaration;
import com.fds.nsw.nghiepvu.model.TempGoodsItems;

public class CargoDeclarationModel {
	
	private List<TempCargoDeclaration> tempCargoDeclarations;
	private List<TempGoodsItems> tempGoodsItems;
	private String signDate;
	private String signPlace;
	private String documentTypeCode;
	
	public CargoDeclarationModel(List<TempCargoDeclaration> tempCargoDeclarations, List<TempGoodsItems> tempGoodsItems,
			String signDate, String signPlace) {
		super();
		this.tempCargoDeclarations = tempCargoDeclarations;
		this.tempGoodsItems = tempGoodsItems;
		this.signDate = signDate;
		this.signPlace = signPlace;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public CargoDeclarationModel(){
	}
	
	public List<TempCargoDeclaration> getTempCargoDeclarations() {
		return tempCargoDeclarations;
	}
	public void setTempCargoDeclarations(
			List<TempCargoDeclaration> tempCargoDeclarations) {
		this.tempCargoDeclarations = tempCargoDeclarations;
	}
	public List<TempGoodsItems> getTempGoodsItems() {
		return tempGoodsItems;
	}
	public void setTempGoodsItems(List<TempGoodsItems> tempGoodsItems) {
		this.tempGoodsItems = tempGoodsItems;
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
