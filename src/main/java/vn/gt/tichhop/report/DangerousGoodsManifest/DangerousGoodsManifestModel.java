package vn.gt.tichhop.report.DangerousGoodsManifest;

import java.util.List;

import com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems;
import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;

public class DangerousGoodsManifestModel {
	
	private List<TempDangerousGoodsManifest> tempDangerousGoodsNanifestsTop;
	private List<TempDangerousGoodsItems> tempDangerousGoodsItemses;
	private String additionalRemark;
	private Integer masterSigned;
	private String signPlace;
	private String signDate;
	private String documentTypeCode;
	
	public DangerousGoodsManifestModel(List<TempDangerousGoodsManifest> tempDangerousGoodsNanifestsTop,
			List<TempDangerousGoodsItems> tempDangerousGoodsItemses, String additionalRemark, Integer masterSigned,
			String signPlace, String signDate) {
		super();
		this.tempDangerousGoodsNanifestsTop = tempDangerousGoodsNanifestsTop;
		this.tempDangerousGoodsItemses = tempDangerousGoodsItemses;
		this.additionalRemark = additionalRemark;
		this.masterSigned = masterSigned;
		this.signPlace = signPlace;
		this.signDate = signDate;
	}
	
	public DangerousGoodsManifestModel() { }
	
	public List<TempDangerousGoodsManifest> getTempDangerousGoodsNanifestsTop() {
		return tempDangerousGoodsNanifestsTop;
	}
	
	public void setTempDangerousGoodsNanifestsTop(List<TempDangerousGoodsManifest> tempDangerousGoodsNanifestsTop) {
		this.tempDangerousGoodsNanifestsTop = tempDangerousGoodsNanifestsTop;
	}
	
	public List<TempDangerousGoodsItems> getTempDangerousGoodsItemses() {
		return tempDangerousGoodsItemses;
	}
	
	public void setTempDangerousGoodsItemses(List<TempDangerousGoodsItems> tempDangerousGoodsItemses) {
		this.tempDangerousGoodsItemses = tempDangerousGoodsItemses;
	}
	
	public String getAdditionalRemark() {
		return additionalRemark;
	}
	
	public void setAdditionalRemark(String additionalRemark) {
		this.additionalRemark = additionalRemark;
	}
	
	public Integer getMasterSigned() {
		return masterSigned;
	}
	
	public void setMasterSigned(Integer masterSigned) {
		this.masterSigned = masterSigned;
	}
	
	public String getSignPlace() {
		return signPlace;
	}
	
	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}
	
	public String getSignDate() {
		return signDate;
	}
	
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
}
