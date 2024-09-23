package vn.gt.portlet.baocao.bc15t;

import java.util.Date;

public class BC15TModel {
	private String maritimeCode;
	private String portRegionCode;
	private String portRegionName;
	private String portHarbourCode;
	private String portHarbourName;
	private int level;
	private double hang_container;
	private double hang_container_teus;
	private double hang_kho;
	private double hang_long;
	private double qua_canh_xep_do;
	private double qua_canh_khong_xep_do;
	private Date ngay;
	private String portRegionCode_;
	private String portHarbourCode_;

	public BC15TModel() {

	}

	public String getMaritimeCode() {
		return maritimeCode;
	}

	public void setMaritimeCode(String maritimeCode) {
		this.maritimeCode = maritimeCode;
	}

	public String getPortRegionCode() {
		return portRegionCode;
	}

	public void setPortRegionCode(String portRegionCode) {
		this.portRegionCode = portRegionCode;
	}

	public String getPortRegionName() {
		return portRegionName;
	}

	public void setPortRegionName(String portRegionName) {
		this.portRegionName = portRegionName;
	}

	public String getPortHarbourName() {
		return portHarbourName;
	}

	public void setPortHarbourName(String portHarbourName) {
		this.portHarbourName = portHarbourName;
	}

	public String getPortHarbourCode() {
		return portHarbourCode;
	}

	public void setPortHarbourCode(String portHarbourCode) {
		this.portHarbourCode = portHarbourCode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getHang_container() {
		return hang_container;
	}

	public void setHang_container(double hang_container) {
		this.hang_container = hang_container;
	}

	public double getHang_container_teus() {
		return hang_container_teus;
	}

	public void setHang_container_teus(double hang_container_teus) {
		this.hang_container_teus = hang_container_teus;
	}

	public double getHang_kho() {
		return hang_kho;
	}

	public void setHang_kho(double hang_kho) {
		this.hang_kho = hang_kho;
	}

	public double getHang_long() {
		return hang_long;
	}

	public void setHang_long(double hang_long) {
		this.hang_long = hang_long;
	}

	public double getQua_canh_xep_do() {
		return qua_canh_xep_do;
	}

	public void setQua_canh_xep_do(double qua_canh_xep_do) {
		this.qua_canh_xep_do = qua_canh_xep_do;
	}

	public double getQua_canh_khong_xep_do() {
		return qua_canh_khong_xep_do;
	}

	public void setQua_canh_khong_xep_do(double qua_canh_khong_xep_do) {
		this.qua_canh_khong_xep_do = qua_canh_khong_xep_do;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public String getPortRegionCode_() {
		return portRegionCode_;
	}

	public void setPortRegionCode_(String portRegionCode_) {
		this.portRegionCode_ = portRegionCode_;
	}

	public String getPortHarbourCode_() {
		return portHarbourCode_;
	}

	public void setPortHarbourCode_(String portHarbourCode_) {
		this.portHarbourCode_ = portHarbourCode_;
	}

}
