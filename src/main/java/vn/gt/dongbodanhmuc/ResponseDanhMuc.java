
package vn.gt.dongbodanhmuc;

import java.io.Serializable;
import java.util.Date;

public class ResponseDanhMuc implements Serializable {

	private int shipId;
	private String shipName;
	private int shipTypeId;
	private String shipTypeCode;
	private String imo;
	private String callSign;
	private String stateId;
	private String stateCode;
	private String registryNumber;
	private Date registryDate;
	private int registryPortId;
	private String registryPortCode;
	private int gt;
	private int nrt;
	private int dwt;
	private long length;
	private long width;
	private long height;
	private long sailingSpeed;
	private String color;
	private String boneCode;
	private String machineCode;
	private int shipAgencyId;
	private String shipAgencyCode;
	private String fishingBoatRegistration;
	private int role;
	private boolean isDelete;
	
	public ResponseDanhMuc() {
	}

	/**
	 * @return the shipId
	 */
	public int getShipId() {
		return shipId;
	}
	/**
	 * @param shipId the shipId to set
	 */
	public void setShipId(int shipId) {
		this.shipId = shipId;
	}
	/**
	 * @return the shipName
	 */
	public String getShipName() {
		return shipName;
	}
	/**
	 * @param shipName the shipName to set
	 */
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	/**
	 * @return the shipTypeId
	 */
	public int getShipTypeId() {
		return shipTypeId;
	}
	/**
	 * @param shipTypeId the shipTypeId to set
	 */
	public void setShipTypeId(int shipTypeId) {
		this.shipTypeId = shipTypeId;
	}
	/**
	 * @return the shipTypeCode
	 */
	public String getShipTypeCode() {
		return shipTypeCode;
	}
	/**
	 * @param shipTypeCode the shipTypeCode to set
	 */
	public void setShipTypeCode(String shipTypeCode) {
		this.shipTypeCode = shipTypeCode;
	}
	/**
	 * @return the iMO
	 */
	public String getImo() {
		return imo;
	}
	/**
	 * @param iMO the iMO to set
	 */
	public void setImo(String iMO) {
		this.imo = iMO;
	}
	/**
	 * @return the callSign
	 */
	public String getCallSign() {
		return callSign;
	}
	/**
	 * @param callSign the callSign to set
	 */
	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}
	/**
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return the registryNumber
	 */
	public String getRegistryNumber() {
		return registryNumber;
	}
	/**
	 * @param registryNumber the registryNumber to set
	 */
	public void setRegistryNumber(String registryNumber) {
		this.registryNumber = registryNumber;
	}
	/**
	 * @return the registryDate
	 */
	public Date getRegistryDate() {
		return registryDate;
	}
	/**
	 * @param registryDate the registryDate to set
	 */
	public void setRegistryDate(Date registryDate) {
		this.registryDate = registryDate;
	}
	/**
	 * @return the registryPortId
	 */
	public int getRegistryPortId() {
		return registryPortId;
	}
	/**
	 * @param registryPortId the registryPortId to set
	 */
	public void setRegistryPortId(int registryPortId) {
		this.registryPortId = registryPortId;
	}
	/**
	 * @return the registryPortCode
	 */
	public String getRegistryPortCode() {
		return registryPortCode;
	}
	/**
	 * @param registryPortCode the registryPortCode to set
	 */
	public void setRegistryPortCode(String registryPortCode) {
		this.registryPortCode = registryPortCode;
	}
	/**
	 * @return the gT
	 */
	public int getGt() {
		return gt;
	}
	/**
	 * @param gT the gT to set
	 */
	public void setGt(int gT) {
		this.gt = gT;
	}

	public int getNrt() {
		return nrt;
	}

	public void setNrt(int nrt) {
		this.nrt = nrt;
	}

	public int getDwt() {
		return dwt;
	}

	public void setDwt(int dwt) {
		this.dwt = dwt;
	}

	/**
	 * @return the length
	 */
	public long getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(long length) {
		this.length = length;
	}
	/**
	 * @return the width
	 */
	public long getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(long width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public long getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(long height) {
		this.height = height;
	}
	/**
	 * @return the sailingSpeed
	 */
	public long getSailingSpeed() {
		return sailingSpeed;
	}
	/**
	 * @param sailingSpeed the sailingSpeed to set
	 */
	public void setSailingSpeed(long sailingSpeed) {
		this.sailingSpeed = sailingSpeed;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the boneCode
	 */
	public String getBoneCode() {
		return boneCode;
	}
	/**
	 * @param boneCode the boneCode to set
	 */
	public void setBoneCode(String boneCode) {
		this.boneCode = boneCode;
	}
	/**
	 * @return the machineCode
	 */
	public String getMachineCode() {
		return machineCode;
	}
	/**
	 * @param machineCode the machineCode to set
	 */
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	/**
	 * @return the shipAgencyId
	 */
	public int getShipAgencyId() {
		return shipAgencyId;
	}
	/**
	 * @param shipAgencyId the shipAgencyId to set
	 */
	public void setShipAgencyId(int shipAgencyId) {
		this.shipAgencyId = shipAgencyId;
	}
	/**
	 * @return the shipAgencyCode
	 */
	public String getShipAgencyCode() {
		return shipAgencyCode;
	}
	/**
	 * @param shipAgencyCode the shipAgencyCode to set
	 */
	public void setShipAgencyCode(String shipAgencyCode) {
		this.shipAgencyCode = shipAgencyCode;
	}
	/**
	 * @return the fishingBoatRegistration
	 */
	public String getFishingBoatRegistration() {
		return fishingBoatRegistration;
	}
	/**
	 * @param fishingBoatRegistration the fishingBoatRegistration to set
	 */
	public void setFishingBoatRegistration(String fishingBoatRegistration) {
		this.fishingBoatRegistration = fishingBoatRegistration;
	}
	/**
	 * @return the role
	 */
	public int getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}
	/**
	 * @return the isDelete
	 */
	public boolean isDelete() {
		return isDelete;
	}
	/**
	 * @param isDelete the isDelete to set
	 */
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
}
