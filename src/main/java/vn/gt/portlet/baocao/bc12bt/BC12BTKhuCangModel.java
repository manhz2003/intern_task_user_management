package vn.gt.portlet.baocao.bc12bt;

public class BC12BTKhuCangModel {

	private String maritimeCode;
	private String portRegionCode;
	private String portRegionName;
	private double khoi_luong_hang;
	private int luot_tau;
	private int luot_tau_bien;
	private int luot_pttnd;

	public BC12BTKhuCangModel() {

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

	public double getKhoi_luong_hang() {
		return khoi_luong_hang;
	}

	public void setKhoi_luong_hang(double khoi_luong_hang) {
		this.khoi_luong_hang = khoi_luong_hang;
	}

	public int getLuot_tau() {
		return luot_tau;
	}

	public void setLuot_tau(int luot_tau) {
		this.luot_tau = luot_tau;
	}

	public int getLuot_tau_bien() {
		return luot_tau_bien;
	}

	public void setLuot_tau_bien(int luot_tau_bien) {
		this.luot_tau_bien = luot_tau_bien;
	}

	public int getLuot_pttnd() {
		return luot_pttnd;
	}

	public void setLuot_pttnd(int luot_pttnd) {
		this.luot_pttnd = luot_pttnd;
	}
}
