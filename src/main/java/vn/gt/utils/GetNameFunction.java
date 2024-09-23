package vn.gt.utils;

import java.util.Date;
import java.util.List;
import vn.gt.constant.Constants;
import com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose;
import com.fds.nsw.nghiepvu.model.DmHistoryGoods;
import com.fds.nsw.nghiepvu.model.DmHistoryGoodsType;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import com.fds.nsw.nghiepvu.model.DmHistoryPackage;
import com.fds.nsw.nghiepvu.model.DmHistoryPassportType;
import com.fds.nsw.nghiepvu.model.DmHistoryPort;
import com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour;
import com.fds.nsw.nghiepvu.model.DmHistoryPortRegion;
import com.fds.nsw.nghiepvu.model.DmHistoryPortWharf;
import com.fds.nsw.nghiepvu.model.DmHistoryRankRating;
import com.fds.nsw.nghiepvu.model.DmHistoryRepresentative;
import com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel;
import com.fds.nsw.nghiepvu.model.DmHistoryShipAgency;
import com.fds.nsw.nghiepvu.model.DmHistoryShipType;
import com.fds.nsw.nghiepvu.model.DmHistoryState;
import com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import com.fds.nsw.nghiepvu.model.DmUnitGeneral;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryArrivalPurposeException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryGoodsTypeException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPassportTypeException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPortException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPortHarbourException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPortRegionException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPortWharfException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryShipAgencyException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryShipTypeException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryStateException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmMaritimeException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmUnitGeneralException;

import lombok.extern.slf4j.Slf4j;

import com.fds.nsw.nghiepvu.model.DmCertificate;
import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmGtBusinessType;

import vn.gt.dao.danhmuc.service.DmHistoryArrivalPurposeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryGoodsLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryGoodsTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPackageLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPassportTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryRankRatingLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryRepresentativeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistorySecurityLevelLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryUnitGeneralLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmUnitGeneralLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.HistorySyncVersionLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmGtReportTemplate;
import com.fds.nsw.nghiepvu.model.DmGtStatus;
import vn.gt.dao.danhmucgt.service.DmCertificateLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGTBusinessTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGTFunctionTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtReportTemplateLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoConstant;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.SystemException;



@Slf4j
public class GetNameFunction {
	
	public static final String DMDC_SHIP = "DMDC_SHIP";
	public static final String DM_STATE = "DM_STATE";
	public static final String DM_PORT = "DM_PORT";
	public static final String DM_ARRIVAL_PURPOSE = "DM_ARRIVAL_PURPOSE";
	public static final String DM_SHIP_AGENCY = "DM_SHIP_AGENCY";
	public static final String DM_SHIP_TYPE = "DM_SHIP_TYPE";
	public static final String DM_PORT_REGION = "DM_PORT_REGION";
	public static final String DM_PORT_WHARF = "DM_PORT_WHARF";
	public static final String DM_PORT_HARBOUR = "DM_PORT_HARBOUR";
	public static final String DM_MARITIME = "DM_MARITIME";
	public static final String DM_UNIT_GENERAL = "DM_UNIT_GENERAL";
	public static final String DM_DOC_TYPE = "DM_DOC_TYPE";
	public static final String DM_PASSPORT_TYPE = "DM_PASSPORT_TYPE";
	public static final String DM_SECURITY_LEVEL = "DM_SECURITY_LEVEL";
	public static final String DM_GOODS_TYPE = "DM_GOODS_TYPE";// Loai hang hoa
	public static final String DM_GOODS = "DM_GOODS";// Loai hang hoa qua canh
	public static final String DM_PACKAGE = "DM_PACKAGE";
	public static final String DM_RANK_RATING = "DM_RANK_RATING";
	public static final String DM_REPRESENTATIVE = "DM_REPRESENTATIVE";
	
	
	
	/**
	 * getSyncVersion
	 */
	
	/*public static String getSyncVersion(String requestCode, String categoryID) {
		Date interfaceRequestDate = InterfaceRequestLocalServiceUtil.getRequestedDateByRequestCode(requestCode);
		if (interfaceRequestDate != null) {
			try {
				return HistorySyncVersionLocalServiceUtil.getsyncVersion(interfaceRequestDate, categoryID);
			} catch (SystemException e) {
				e.printStackTrace();
			}
		}
		return "1";
	}*/
	
	public static String getSyncVersionBanKhai(Date signDate, String categoryID) {
		try {
			if (Validator.isNotNull(signDate)) {
				return HistorySyncVersionLocalServiceUtil.getsyncVersion(signDate, categoryID);
			} else {
				return HistorySyncVersionLocalServiceUtil.getsyncVersion(new Date(), categoryID);
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return "1";
	}
	
	/**
	 * getDmUnitGeneralName FROM: DM_HISTORY_PORT_WHARF PARAM : requestCode; portWharfCode categoryID=
	 */
	public static String getCertificateName(String certificateCode) throws SystemException, NoSuchDmUnitGeneralException {
		log.debug("==certificateCode==" + certificateCode);
		
		if (Validator.isNotNull(certificateCode) && certificateCode.length() > 0) {
			List<DmCertificate> allCertificates = DmCertificateLocalServiceUtil.findByCertificateCode(certificateCode);
			if (allCertificates != null && allCertificates.size() > 0) {
				return allCertificates.get(0).getCertificateName();
			} else {
				return certificateCode;
			}
		}
		return "";
	}
	
	/**
	 * Ten CANG VU HANG HAI FROM: DM_MARITIME PARAM : MaritimeCode
	 */
	public static String getMaritimeName(String maritimeCode) throws NoSuchDmMaritimeException, SystemException {
		log.debug("==maritimeCode==" + maritimeCode);
		if (Validator.isNotNull(maritimeCode) && Validator.isNotNull(maritimeCode) && maritimeCode.length() > 0) {
			DmMaritime dmMaritime = null;
			try {
				dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (dmMaritime != null) {
				return dmMaritime.getMaritimeNameVN();
			} else {
				return maritimeCode;
			}
		}
		return "";
	}
	
	public static String getCityCode(Date signDate, String portofAuthority) {
		log.debug("==requestCode==" + signDate + "==portofAuthority==" + portofAuthority);
		if (Validator.isNotNull(portofAuthority) && Validator.isNotNull(portofAuthority) && portofAuthority.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_MARITIME);
			
			DmHistoryMaritime maritime = null;
			try {
				maritime = DmHistoryMaritimeLocalServiceUtil.getByMaritimeCodeAndSyncVersion(portofAuthority, version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (maritime != null) {
				return maritime.getCityCode();
			} else {
				return portofAuthority;
			}
		}
		return "";
	}
	
	/**
	 */
	public static String getReportName(int reportCode) throws NoSuchDmMaritimeException, SystemException {
		log.debug("==reportCode==" + reportCode);
		if (Validator.isNotNull(reportCode) && Validator.isNotNull(reportCode)) {
			DmGtReportTemplate gtReportTemplate = DmGtReportTemplateLocalServiceUtil.findByReportCode(reportCode);
			if (gtReportTemplate != null) {
				return gtReportTemplate.getReportName().toUpperCase();
			}
		}
		return "";
	}
	
	/**
	 * getDmUnitGeneralName FROM: DM_Unit General Name PARAM : requestCode; portWharfCode categoryID=
	 */
	public static String getDmUnitGeneralName(String unitCode) throws SystemException, NoSuchDmUnitGeneralException {
		log.debug("==unitCode==" + unitCode);
		if (Validator.isNotNull(unitCode) && unitCode.length() > 0) {
			DmUnitGeneral item = null;
			try {
				item = DmUnitGeneralLocalServiceUtil.getByUnitCode(unitCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getUnitName();
			} else {
				return unitCode;
			}
		}
		return "";
	}
	
	
	public static String getStatusName(String statusCode) throws NoSuchDmHistoryStateException, SystemException {
		log.debug("==statusCode==" + statusCode + "==HO_SO_CQCN_TRA_VE==" + 5);
		if (Validator.isNotNull(statusCode) && statusCode.length() > 0) {
			DmGtStatus dmGtStatus = DmGtStatusLocalServiceUtil.findByStatusCode(Integer.parseInt(statusCode), BaoCaoConstant.HO_SO_CQCN_TRA_VE);
			if (dmGtStatus != null) {
				return dmGtStatus.getStatusName();
			} else {
				return statusCode;
			}
		}
		return "";
	}
	
	/**
	 * Ten Trang thai FROM: DM_HISTORY_STATE PARAM : requestCode; StateCode categoryID=DM_STATE
	 */
	public static String getProvinceAndPortName(Date signDate, String portCode) throws SystemException, NoSuchDmHistoryPortException {
		log.debug("==PortCode==" + portCode + "==Cang ben thuy noi dia");
		if (Validator.isNotNull(signDate) && Validator.isNotNull(portCode) && portCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PORT);
			DmHistoryPort item = null;
			try {
				item = DmHistoryPortLocalServiceUtil.findByPortCodeAndSyncVersion(portCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getAddressVN() + " - " +  item.getPortName();
			} else {
				return portCode;
			}
		}
		log.debug("==getPortName==signDate=" + signDate + "==portCode==" + portCode);
		return "";
	}
	
	
	/**
	 * Ten Trang thai FROM: Interface Request PARAM : requestCode
	 */
	public static String getFunctionNameByRequestCode(String requestCode) throws NoSuchDmHistoryStateException, SystemException {
		log.debug("getFunctionNameByRequestCode==requestCode==" + requestCode);
		String functionTypeName = null;
		if (Validator.isNotNull(requestCode) && Validator.isNotNull(requestCode) && requestCode.length() > 0) {
			String functionType = InterfaceRequestLocalServiceUtil.getFunctionTypeByRequestCode(requestCode);
			if ((functionType != null) && (functionType.length() > 0)) {
				functionTypeName = DmGTFunctionTypeLocalServiceUtil.findNameByFunctionTypeCode(functionType);
			}
			if (functionTypeName != null) {
				return functionTypeName;
			}
		}
		return "";
	}
	
	/**
	 * Ten BusinessType FROM: DM_HISTORY_STATE PARAM : requestCode; StateCode categoryID=DM_STATE
	 */
	public static String getBusinessTypeName(int businessTypeCode) throws NoSuchDmHistoryStateException, SystemException {
		log.debug("getBusinessTypeName==businessTypeCode==start==" + businessTypeCode);
		if (Validator.isNotNull(businessTypeCode)) {
			DmGtBusinessType lstDmGtBusinessType = DmGTBusinessTypeLocalServiceUtil.getByBusinessTypeCode(businessTypeCode);
			if (Validator.isNotNull(lstDmGtBusinessType)) {
				return lstDmGtBusinessType.getBusinessTypeNameVN();
			} else {
				return businessTypeCode + "";
			}
		}
		log.debug("getBusinessTypeName==businessTypeCode==end==" + businessTypeCode);
		return "";
	}
	
	/**
	 * Ten Quoc GIA FROM: DM_HISTORY_STATE PARAM : requestCode; StateCode categoryID=DM_STATE
	 */
	public static String getStateName(Date signDate, String stateCode) throws NoSuchDmHistoryStateException, SystemException {
		log.debug("=getStateName==start=signDate=" + signDate + "==stateCode==" + stateCode);
		String result = "";
		if (Validator.isNotNull(signDate) && Validator.isNotNull(stateCode) && stateCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_STATE);
			DmHistoryState state = null;
			try {
				state = DmHistoryStateLocalServiceUtil.findByStateCodeAndSyncVersion(stateCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (state != null) {
				result = state.getStateName();
			} else {
				result = stateCode;
			}
		}
		log.debug("=getStateName==end=signDate=" + signDate + "==stateCode==" + stateCode);
		return result;
	}
	
	/**
	 * TEN CANG FROM: DM_HISTORY_PORT PARAM : requestCode; PortCode categoryID=
	 */
	public static String getFullNameVN(Date signDate, String portCode) throws NoSuchDmHistoryPortException, SystemException {
		log.debug("==getFullNameVN=signDate=" + signDate + "==portCode==" + portCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(portCode) && portCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PORT);
			DmHistoryPort dmHistoryPort = null;
			try {
				dmHistoryPort = DmHistoryPortLocalServiceUtil.findByPortCodeAndSyncVersion(portCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (dmHistoryPort != null) {
				return dmHistoryPort.getFullName();
			} else {
				return portCode;
			}
		}
		return "";
	}
	
	/**
	 * Muc dich DEN CANG FROM: DM_HISTORY_ARRIVAL_PURPOSE PARAM : requestCode; PortCode categoryID=
	 */
	public static String getPurposeNameVN(Date signDate, String purposeCode) throws SystemException, NoSuchDmHistoryArrivalPurposeException {
		log.debug("==getPurposeNameVN==signDate=" + signDate + "==purposeCode==" + purposeCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(purposeCode) && purposeCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_ARRIVAL_PURPOSE);
			DmHistoryArrivalPurpose item = null;
			try {
				item = DmHistoryArrivalPurposeLocalServiceUtil.findByPurposeCodeAndSyncVersion(purposeCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getPurposeName();
			} else {
				return purposeCode;
			}
		}
		return "";
	}
	
	/**
	 * dai ly CHU TAU FROM: DM_HISTORY_SHIP_AGENCY PARAM : requestCode; ShipAgencyCode categoryID=
	 */
	public static String getShipAgencyNameVN(Date signDate, String shipAgencyCode) throws SystemException, NoSuchDmHistoryShipAgencyException {
		log.debug("==getShipAgencyNameVN==signDate=" + signDate + "==shipAgencyCode==" + shipAgencyCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(shipAgencyCode) && shipAgencyCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_SHIP_AGENCY);
			DmHistoryShipAgency item = null;
			try {
				item = DmHistoryShipAgencyLocalServiceUtil.findByPurposeCodeAndSyncVersion(shipAgencyCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getShipAgencyName();
			} else {
				return shipAgencyCode;
			}
		}
		return "";
	}
	
	/**
	 * Lich su LOAI TAU FROM: DM_HISTORY_SHIP_TYPE PARAM : requestCode; shipTypeCode categoryID=
	 */
	public static String getShipTypeNameVN(Date signDate, String shipTypeCode) throws SystemException, NoSuchDmHistoryShipTypeException {
		log.debug("==getShipTypeNameVN==signDate=" + signDate + "==shipTypeCode==" + shipTypeCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(shipTypeCode) && shipTypeCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_SHIP_TYPE);
			DmHistoryShipType item = null;
			try {
				item = DmHistoryShipTypeLocalServiceUtil.findByShipTypeCodeAndSyncVersion(shipTypeCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getShipTypeName();
			} else {
				return shipTypeCode;
			}
		}
		log.debug("==getShipTypeNameVN==signDate=" + signDate + "==shipTypeCode==" + shipTypeCode);
		return "";
	}
	
	/**
	 * Linh su danh muc CANG From: dm_history_port Param: requestCode; portCode categoryID =
	 */
	public static String getPortName(Date signDate, String portCode) throws SystemException, NoSuchDmHistoryPortException {
		log.debug("==getPortName==signDate=" + signDate + "==portCode==" + portCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(portCode) && portCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PORT);
			DmHistoryPort item = null;
			try {
				item = DmHistoryPortLocalServiceUtil.findByPortCodeAndSyncVersion(portCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getPortName();
			} else {
				return portCode;
			}
		}
		log.debug("==getPortName==signDate=" + signDate + "==portCode==" + portCode);
		return "";
	}

	/**
	 * Linh su danh muc CANG From: dm_history_port Param: requestCode; portCode categoryID =
	 */
	public static String getPortNameInland(Date signDate, String portCode) throws SystemException, NoSuchDmHistoryPortException {
		log.debug("==getPortName==signDate=" + signDate + "==portCode==" + portCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(portCode) && portCode.length() > 0) {
			//String version = getSyncVersionBanKhai(signDate, DM_PORT);
			DmPortWharf item = null;
			try {
				item = DmPortWharfLocalServiceUtil.getByPortWharfCode(portCode) ;
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getPortCode() +",  " + item.getPortWharfNameVN();
			} else {
				return portCode;
			}
		}
		log.debug("==getPortName==signDate=" + signDate + "==portCode==" + portCode);
		return "";
	}
	
	/**
	 * lich su danh muc Khu VUC CANG FROM: DM_HISTORY_PORT_REGION PARAM : requestCode; portRegionCode categoryID=
	 */
	public static String getPortRegionNameVN(Date signDate, String portRegionCode) throws SystemException, NoSuchDmHistoryPortRegionException {
		log.debug("==getPortRegionNameVN==signDate=" + signDate + "==portRegionCode==" + portRegionCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(portRegionCode) && portRegionCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PORT_REGION);
			DmHistoryPortRegion item = null;
			try {
				item = DmHistoryPortRegionLocalServiceUtil.findByPortRegionCodeAndSyncVersion(portRegionCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getPortRegionName();
			} else {
				return portRegionCode;
			}
		}
		return "";
	}
	public static String getPortWharfNameVN_Inland(Date signDate, String portWharfCode) throws SystemException, NoSuchDmHistoryPortWharfException {
		log.debug("==signDate=" + signDate + "==portWharfCode==" + portWharfCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(portWharfCode) && portWharfCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PORT_WHARF);
			DmHistoryPortWharf item = null;
			try {
				item = DmHistoryPortWharfLocalServiceUtil.findByPortWharfCodeAndSyncVersion(portWharfCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getPortWharfName();
			} else {
				return portWharfCode;
			}
		}
		return "";
	}
	
	
	/**
	 * Lich su danh muc CAU CANG FROM: DM_HISTORY_PORT_WHARF PARAM : requestCode; portWharfCode categoryID=
	 */
	public static String getPortWharfNameVN(Date signDate, String portWharfCode) throws SystemException, NoSuchDmHistoryPortWharfException {
		log.debug("==signDate=" + signDate + "==portWharfCode==" + portWharfCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(portWharfCode) && portWharfCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PORT_WHARF);
			DmHistoryPortWharf item = null;
			try {
				item = DmHistoryPortWharfLocalServiceUtil.findByPortWharfCodeAndSyncVersion(portWharfCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getPortWharfNameVN();
			} else {
				return portWharfCode;
			}
		}
		return "";
	}
	/*
	 * Lich su danh muc CAU CANG FROM: DM_HISTORY_PORT_WHARF PARAM : requestCode; portWharfCode categoryID=
	 */
	public static String getPortWharfNameVNInland(Date signDate, String portWharfCode) throws SystemException, NoSuchDmHistoryPortWharfException {
		log.debug("==signDate=" + signDate + "==portWharfCode==" + portWharfCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(portWharfCode) && portWharfCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PORT_WHARF);
			DmHistoryPortWharf item = null;
			try {
				item = DmHistoryPortWharfLocalServiceUtil.findByPortWharfCodeAndSyncVersion(portWharfCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getPortWharfNameVN();
			} else {
				return portWharfCode;
			}
		}
		return "";
	}	
	
	/**
	 * lich su danh muc BEN CANG FROM: DM_HISTORY_PORT_HARBOUR PARAM : requestCode; portHarbourCode categoryID=
	 */
	public static String getPortHarbourNameVN(Date signDate, String portHarbourCode) throws SystemException, NoSuchDmHistoryPortHarbourException {
		log.debug("==getPortHarbourNameVN==signDate=" + signDate + "==portHarbourCode==" + portHarbourCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(portHarbourCode) && portHarbourCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PORT_HARBOUR);
			DmHistoryPortHarbour item = null;
			try {
				item = DmHistoryPortHarbourLocalServiceUtil.findByPortHarbourCodeAndSyncVersion(portHarbourCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getPortHarbourName();
			} else {
				return portHarbourCode;
			}
		}
		log.debug("==getPortHarbourNameVN==signDate=" + signDate + "==portHarbourCode==" + portHarbourCode);
		return "";
	}
	
	/**
	 * lich su danh muc LOAI HANG HOA FROM: DM_HISTORY_GOODS_TYPE PARAM : requestCode; goodsTypeCode categoryID=
	 */
	public static String getGoodsTypeNameVN(Date signDate, String goodsTypeCode) throws SystemException, NoSuchDmHistoryGoodsTypeException {
		log.info("==getGoodsTypeNameVN==signDate=" + signDate + "==goodsTypeCode==" + goodsTypeCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(goodsTypeCode) && goodsTypeCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_GOODS_TYPE);
			DmHistoryGoodsType item = null;
			try {
				item = DmHistoryGoodsTypeLocalServiceUtil.findByGoodsTypeCodeAndSyncVersion(goodsTypeCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				if (Validator.isNotNull(item.getGoodsTypeNameVN()) && item.getGoodsTypeNameVN().length() > 0) {
					return item.getGoodsTypeNameVN();
				} else {
					return item.getGoodsTypeName();
				}
			} else {
				return goodsTypeCode;
			}
		}
		log.info("==getGoodsTypeNameVN==signDate=" + signDate + "==goodsTypeCode==" + goodsTypeCode);
		return "";
	}
	
	/**
	 * Lich su danh muc LOAI HO CHIEU FROM: DM_HISTORY_PASSPORT_TYPE PARAM : requestCode; passportTypeCode categoryID=
	 */
	public static String getPassportTypeNameVN(Date signDate, String passportTypeCode) throws NoSuchDmHistoryPassportTypeException, SystemException {
		log.debug("==getPassportTypeNameVN==signDate=" + signDate + "==passportTypeCode==" + passportTypeCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(passportTypeCode) && passportTypeCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PASSPORT_TYPE);
			DmHistoryPassportType item = null;
			try {
				item = DmHistoryPassportTypeLocalServiceUtil.findByPassportTypeCodeAndSyncVersion(passportTypeCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				return item.getPassportTypeName();
			} else {
				return passportTypeCode;
			}
		}
		log.debug("==getPassportTypeNameVN==signDate=" + signDate + "==passportTypeCode==" + passportTypeCode);
		return "";
	}
	
	/**
	 * 
	 * */
	public static String getCallSignByTempDocument(long documentName, int documentYear) {
		log.debug("==getCallSignByTempDocument==documentName=" + documentName + "==documentYear==" + documentYear);
		if (Validator.isNotNull(documentName) && documentName > 0 && Validator.isNotNull(documentYear) && documentYear > 0) {
			String callSign = TempDocumentLocalServiceUtil.getCallSignByDocumentNameAndDocumentYear(documentName, documentYear);
			
			if (Validator.isNotNull(callSign)) {
				return callSign;
			} else {
				return "";
			}
		}
		log.debug("==getCallSignByTempDocument==documentName=" + documentName + "==documentYear==" + documentYear);
		return "";
	}
	
	/**
	 * Chuc danh thuyen vien
	 */
	public static String getRankName(Date signDate, String rankCode) throws NoSuchDmHistoryPassportTypeException, SystemException {
		log.debug("==getRankName==signDate=" + signDate + "==rankCode==" + rankCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(rankCode) && rankCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PASSPORT_TYPE);
			DmHistoryRankRating item = null;
			try {
				item = DmHistoryRankRatingLocalServiceUtil.getByRankCodeAndSyncVersion(rankCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (item != null) {
				if (Validator.isNotNull(item.getRankNameVN()) && item.getRankNameVN().length() > 0) {
					return item.getRankNameVN();
				} else {
					return item.getRankName();
				}
			} else {
				return rankCode;
			}
		}
		log.debug("==getRankName==signDate=" + signDate + "==rankCode==" + rankCode);
		return "";
	}
	
	/**
	 * Loai GOI HANG DM_HISTORY_PACKAGE
	 */
	public static String getPackage(Date signDate, String packageCode) {
		log.debug("==getPackage==signDate=" + signDate + "==packageCode==" + packageCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(packageCode) && packageCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_PACKAGE);
			DmHistoryPackage item = null;
			try {
				item = DmHistoryPackageLocalServiceUtil.getHistoryPackageByPackageCodeAndSyncVersion(packageCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if (Validator.isNotNull(item)) {
				if (Validator.isNotNull(item.getPackageNameVN()) && item.getPackageNameVN().length() > 0) {
					return item.getPackageNameVN();
				} else {
					return item.getPackageName();
				}
			} else {
				return packageCode;
			}
		}
		log.debug("==getPackage==signDate=" + signDate + "==packageCode==" + packageCode);
		return "";
	}
	
	/**
	 * Lich su danh muc LOAI HANG HOA QUA CANH FROM: ASW.DM_HISTORY_GOODS PARAM : requestCode; passportTypeCode categoryID=
	 */
	public static String getNameGoods(Date signDate, String goodsItemCode) {
		log.debug("==getNameGoods==signDate=" + signDate + "==goodsItemCode==" + goodsItemCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(goodsItemCode) && goodsItemCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_GOODS);
			DmHistoryGoods item = null;
			try {
				item = DmHistoryGoodsLocalServiceUtil.findByGoodsItemCodeAndSyncVersion(goodsItemCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (Validator.isNotNull(item)) {
				return item.getGoodsItemName();
			} else {
				return goodsItemCode;
			}
		}
		log.debug("==getNameGoods==signDate=" + signDate + "==goodsItemCode==" + goodsItemCode);
		return "";
	}
	
	/**
	 * Don vi of so luong dm_history_unit_general
	 */
	public static String getUnitGeneralName(Date signDate, String unitCode) {
		log.debug("==getUnitGeneralName==signDate=" + signDate + "==unitCode==" + unitCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(unitCode) && unitCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_UNIT_GENERAL);
			DmHistoryUnitGeneral item = null;
			try {
				item = DmHistoryUnitGeneralLocalServiceUtil.getByUnitCodeAndSyncVersion(unitCode.trim(), version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (Validator.isNotNull(item)) {
				return item.getUnitName();
			} else {
				return unitCode;
			}
		}
		log.debug("==getUnitGeneralName==signDate=" + signDate + "==unitCode==" + unitCode);
		return "";
	}
	
	/**
	 * Cap do an ninh DmHistorySecurityLevel
	 */
	public static String getSecurityLevelName(Date signDate, String securityLevelCode) {
		log.debug("==getSecurityLevelName==signDate=" + signDate + "==securityLevelCode==" + securityLevelCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(securityLevelCode) && securityLevelCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_SECURITY_LEVEL);
			DmHistorySecurityLevel item = null;
			try {
				item = DmHistorySecurityLevelLocalServiceUtil.getBySecurityLevelCodeAndSyncVersion(securityLevelCode, version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (Validator.isNotNull(item)) {
				return item.getSecurityLevelName();
			} else {
				return securityLevelCode;
			}
		}
		log.debug("==getSecurityLevelName==signDate=" + signDate + "==securityLevelCode==" + securityLevelCode);
		return "";
	}
	
	/**
	 * danh muc dai dien
	 */
	public static String getRepresentativeName(Date signDate, String repCode) {
		log.debug("==getRepresentativeName==signDate=" + signDate + "==repCode==" + repCode);
		if (Validator.isNotNull(signDate) && Validator.isNotNull(repCode) && repCode.length() > 0) {
			String version = getSyncVersionBanKhai(signDate, DM_REPRESENTATIVE);
			DmHistoryRepresentative item = null;
			try {
				item = DmHistoryRepresentativeLocalServiceUtil.getByRepCodeAndSyncVersion(repCode, version);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (Validator.isNotNull(item)) {
				return item.getRepNameVN();
			} else {
				return repCode;
			}
		}
		log.debug("==getRepresentativeName==signDate=" + signDate + "==repCode==" + repCode);
		return "";
	}
	/**
	 * Thong tin cang den/roi
	 */
	public static String getPortArrivalDepartureInland(Date signDate, String portRegionCode, String arrivalPortCode, String portWharfCode, String portHarbourCode) {
		try {
			log.debug("start==getPortArrivalDeparture==signDate==" + signDate + "==portRegionCode=" + portRegionCode);
			String sPortRegion = getPortRegionNameVN(signDate, portRegionCode);
			String sArrivalPort = getPortName(signDate, arrivalPortCode);
			String sPortwhart = getPortWharfNameVNInland(signDate, portWharfCode);
			String sPortHarbour = getPortHarbourNameVN(signDate, portHarbourCode);
			
			int lengthPortRegion = sPortRegion.length();
			int lengthArrivalPort = sArrivalPort.length();
			int lengthPortwhart = sPortwhart.length();
			int lengthPortHarbour = sPortHarbour.length();
			
			if (lengthPortRegion > 0 && lengthArrivalPort > 0 && lengthPortwhart > 0 && lengthPortHarbour > 0) {
				return sPortRegion + ",  " + sPortHarbour + ",  " + sPortwhart ;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort == 0 && lengthPortwhart == 0 && lengthPortHarbour == 0) {
				return sPortRegion;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort > 0 && lengthPortwhart == 0 && lengthPortHarbour == 0) {
				return sArrivalPort;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort == 0 && lengthPortwhart > 0 && lengthPortHarbour == 0) {
				return sPortwhart;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort == 0 && lengthPortwhart == 0 && lengthPortHarbour > 0) {
				return sPortHarbour;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort > 0 && lengthPortwhart == 0 && lengthPortHarbour == 0) {
				return sPortRegion + ",  " + sArrivalPort;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort == 0 && lengthPortwhart > 0 && lengthPortHarbour == 0) {
				return sPortRegion + ",  " + sPortwhart;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort == 0 && lengthPortwhart == 0 && lengthPortHarbour > 0) {
				return sPortRegion + ",  " + sPortHarbour;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort > 0 && lengthPortwhart > 0 && lengthPortHarbour == 0) {
				//return sArrivalPort + ",  " + sPortwhart;
				return sPortwhart;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort > 0 && lengthPortwhart == 0 && lengthPortHarbour > 0) {
				//return sArrivalPort + ",  " + sPortHarbour;
				return sPortHarbour;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort == 0 && lengthPortwhart > 0 && lengthPortHarbour > 0) {
				return sPortHarbour+ ",  " + sPortwhart ;
				
				// group 3
			} else if (lengthPortRegion > 0 && lengthArrivalPort > 0 && lengthPortwhart > 0 && lengthPortHarbour == 0) {
				return sPortRegion + ",  " + sPortwhart;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort > 0 && lengthPortwhart == 0 && lengthPortHarbour > 0) {
				return sPortRegion + ",  " + sPortHarbour;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort > 0 && lengthPortwhart > 0 && lengthPortHarbour > 0) {
				//return sArrivalPort  + ",  " + sPortHarbour + ",  " + sPortwhart;
				return sPortHarbour + ",  " + sPortwhart;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort == 0 && lengthPortwhart > 0 && lengthPortHarbour > 0) {
				return sPortRegion + ",  " + sPortHarbour + ",  " + sPortwhart;
			} else {
				return "";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("end==getPortArrivalDeparture==signDate==" + signDate + "==portRegionCode=" + portRegionCode);
		return "";
	}	
	/**
	 * Thong tin cang den/roi
	 */
	public static String getPortArrivalDeparture(Date signDate, String portRegionCode, String arrivalPortCode, String portWharfCode, String portHarbourCode) {
		try {
			log.debug("start==getPortArrivalDeparture==signDate==" + signDate + "==portRegionCode=" + portRegionCode);
			String sPortRegion = getPortRegionNameVN(signDate, portRegionCode);
			String sArrivalPort = getPortName(signDate, arrivalPortCode);
			String sPortWharf = getPortWharfNameVN(signDate, portWharfCode);
			String sPortHarbour = getPortHarbourNameVN(signDate, portHarbourCode);
			
			int lengthPortRegion = sPortRegion.length();
			int lengthArrivalPort = sArrivalPort.length();
			int lengthPortwhart = sPortWharf.length();
			int lengthPortHarbour = sPortHarbour.length();
			
			if (lengthPortRegion > 0 && lengthArrivalPort > 0 && lengthPortwhart > 0 && lengthPortHarbour > 0) {
				//return sPortRegion + ",  " + sArrivalPort + ",  " + sPortwhart + ",  " + sPortHarbour;
				return sPortRegion + ",  " + sPortHarbour + ",  " + sPortWharf;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort == 0 && lengthPortwhart == 0 && lengthPortHarbour == 0) {
				return sPortRegion;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort > 0 && lengthPortwhart == 0 && lengthPortHarbour == 0) {
				return sArrivalPort;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort == 0 && lengthPortwhart > 0 && lengthPortHarbour == 0) {
				return sPortWharf;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort == 0 && lengthPortwhart == 0 && lengthPortHarbour > 0) {
				return sPortHarbour;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort > 0 && lengthPortwhart == 0 && lengthPortHarbour == 0) {
				return sPortRegion + ",  " + sArrivalPort;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort == 0 && lengthPortwhart > 0 && lengthPortHarbour == 0) {
				return sPortRegion + ",  " + sPortWharf;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort == 0 && lengthPortwhart == 0 && lengthPortHarbour > 0) {
				return sPortRegion + ",  " + sPortHarbour;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort > 0 && lengthPortwhart > 0 && lengthPortHarbour == 0) {
				//return sArrivalPort + ",  " + sPortWharf;
				return sPortWharf;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort > 0 && lengthPortwhart == 0 && lengthPortHarbour > 0) {
				//return sArrivalPort + ",  " + sPortHarbour;
				return sPortHarbour;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort == 0 && lengthPortwhart > 0 && lengthPortHarbour > 0) {
				return sPortHarbour+ ",  " + sPortWharf ;
				
				// group 3
			} else if (lengthPortRegion > 0 && lengthArrivalPort > 0 && lengthPortwhart > 0 && lengthPortHarbour == 0) {
				//return sPortRegion + ",  " + sArrivalPort + ",  " + sPortWharf;
				return sPortRegion + ",  " + sPortWharf;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort > 0 && lengthPortwhart == 0 && lengthPortHarbour > 0) {
				//return sPortRegion + ",  " + sArrivalPort + ",  " + sPortHarbour;
				return sPortRegion + ",  " + sPortHarbour;
				
			} else if (lengthPortRegion == 0 && lengthArrivalPort > 0 && lengthPortwhart > 0 && lengthPortHarbour > 0) {
				//return sArrivalPort + ",  " + sPortHarbour + ",  " + sPortWharf ;
				return sPortHarbour + ",  " + sPortWharf ;
				
			} else if (lengthPortRegion > 0 && lengthArrivalPort == 0 && lengthPortwhart > 0 && lengthPortHarbour > 0) {
				return sPortRegion + ",  " + sPortHarbour  + ",  " + sPortWharf ;
			} else {
				return "";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("end==getPortArrivalDeparture==signDate==" + signDate + "==portRegionCode=" + portRegionCode);
		return "";
	}
}
