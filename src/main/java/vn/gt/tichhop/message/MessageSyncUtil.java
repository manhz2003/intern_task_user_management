package vn.gt.tichhop.message;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.kernel.exception.SystemException;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.ultility.Validator;

import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import com.fds.nsw.nghiepvu.model.DmHistoryPort;
import com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour;
import com.fds.nsw.nghiepvu.model.DmHistoryPortRegion;
import com.fds.nsw.nghiepvu.model.DmHistoryPortWharf;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPort;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmHistoryPilotCategoryLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPilotCompanyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPilotLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistorySecurityOfficeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryShipOwnerLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryShipyardLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryTugboatCompanyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryTugboatLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryVmaShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotCategoryLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotCompanyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaSecurityOfficeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipyardLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaTugboatCompanyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaTugboatLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmGtRouteConfig;
import vn.gt.dao.danhmucgt.service.DmGtRouteConfigLocalServiceUtil;
import vn.gt.tichhop.sendmessage.SendMessgaeFunctions;
import vn.gt.tichhop.threat.ObjectExcute;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.Header.To;
import vn.nsw.model.DmDataItemList;
import vn.nsw.model.DmHistoryMaritimeList;
import vn.nsw.model.DmHistoryPilotCategoryList;
import vn.nsw.model.DmHistoryPilotCategoryList.DmHistoryPilotCategory;
import vn.nsw.model.DmHistoryPilotCompanyList;
import vn.nsw.model.DmHistoryPilotCompanyList.DmHistoryPilotCompany;
import vn.nsw.model.DmHistoryPilotList;
import vn.nsw.model.DmHistoryPilotList.DmHistoryPilot;
import vn.nsw.model.DmHistoryPortHarbourList;
import vn.nsw.model.DmHistoryPortList;
import vn.nsw.model.DmHistoryPortRegionList;
import vn.nsw.model.DmHistoryPortWharfList;
import vn.nsw.model.DmHistorySecurityOfficeList;
import vn.nsw.model.DmHistorySecurityOfficeList.DmHistorySecurityOffice;
import vn.nsw.model.DmHistoryShipOwnerList;
import vn.nsw.model.DmHistoryShipOwnerList.DmHistoryShipOwner;
import vn.nsw.model.DmHistoryShipyardList;
import vn.nsw.model.DmHistoryShipyardList.DmHistoryShipyard;
import vn.nsw.model.DmHistoryTugboatCompanyList;
import vn.nsw.model.DmHistoryTugboatCompanyList.DmHistoryTugboatCompany;
import vn.nsw.model.DmHistoryTugboatList;
import vn.nsw.model.DmHistoryTugboatList.DmHistoryTugboat;
import vn.nsw.model.DmHistoryVmashipTypeList;
import vn.nsw.model.DmHistoryVmashipTypeList.DmHistoryVmashipType;
import vn.nsw.model.DmMaritimeList;
import vn.nsw.model.DmPortHarbourList;
import vn.nsw.model.DmPortRegionList;
import vn.nsw.model.DmPortWharfList;
import vn.nsw.model.DmVmaPilotCategoryList;
import vn.nsw.model.DmVmaPilotCategoryList.DmVmaPilotCategory;
import vn.nsw.model.DmVmaPilotCompanyList;
import vn.nsw.model.DmVmaPilotCompanyList.DmVmaPilotCompany;
import vn.nsw.model.DmVmaPilotList;
import vn.nsw.model.DmVmaPilotList.DmVmaPilot;
import vn.nsw.model.DmVmaSecurityOfficeList;
import vn.nsw.model.DmVmaSecurityOfficeList.DmVmaSecurityOffice;
import vn.nsw.model.DmVmaShipOwnerList;
import vn.nsw.model.DmVmaShipyardList;
import vn.nsw.model.DmVmaShipyardList.DmVmaShipyard;
import vn.nsw.model.DmVmaShipOwnerList.DmVmaShipOwner;
import vn.nsw.model.DmVmaShipTypeList;
import vn.nsw.model.DmVmaShipTypeList.DmVmaShipType;
import vn.nsw.model.DmVmaTugboatCompanyList;
import vn.nsw.model.DmVmaTugboatCompanyList.DmVmaTugboatCompany;
import vn.nsw.model.DmVmaTugboatList;
import vn.nsw.model.DmVmaTugboatList.DmVmaTugboat;
import vn.nsw.model.PortList;
import vn.nsw.model.PortList.Port;

@Slf4j
public class MessageSyncUtil {

	// Kiem tra header cua ban tin la gui tu cang vu hoac gui tu MTGateway
	public static boolean checkHeader(Header header) {
		if (header == null) {
			return false;
		}

		String fromIdentity = header.getFrom().getIdentity();

		// MTGateway
		if("BGTVT".equals(fromIdentity)) {
			return true;
		}

		// Ketoan VietDa
		if("ACCT".equals(fromIdentity)) {
			return true;
		}

		// Cang vu
		DmMaritime maritimeByReference = DmMaritimeLocalServiceUtil.getByMaritimeReference(fromIdentity);

		if (maritimeByReference != null) {
			return true;
		}

		// Cang vu
		DmMaritime maritimeByCode = DmMaritimeLocalServiceUtil.getByMaritimeCode(fromIdentity);

		if (maritimeByCode != null) {
			return true;
		}

		return false;
	}

	// Ham nhan xu ly msg dong bo tu cang vu hoac MTGateway
	public static boolean luuDanhMuc(ObjectExcute objectExcute, Header header) {
		try {
			// Kiem tra xem server dang nhan msg la MTGateway hay CV
			// Neu la CV nhan msg thi chi co truong hop dong bo tu cac cang vu khac sang MTGateway, roi MTGateway fw sang cac CV khac.
			// Vi the neu tai CV thi chi thuc hien saveDB va k dong goi MSG nua, neu khong se loop
			boolean isCangVu = false;

			// Check CV
			List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByLocCode(MessageType.BGTVT);
			if(routeConfigs != null && routeConfigs.size() > 0) {
				isCangVu = true;
			}

			String fromMaritimeCode = null;
			if(isCangVu) {
				fromMaritimeCode = MessageType.BGTVT;
			} else {
				String fromIdentity = header.getFrom().getIdentity();

				DmMaritime fromMaritime = DmMaritimeLocalServiceUtil.getByMaritimeReference(fromIdentity);
				fromMaritimeCode = fromMaritime != null ? "@" + fromMaritime.getMaritimeCode() : "---";
			}

			List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(objectExcute.getXmlData().trim());

			for(Object object : liObjects) {

				if (object instanceof DmVmaTugboatCompanyList) {
					DmVmaTugboatCompanyList list = (DmVmaTugboatCompanyList) object;
					for (DmVmaTugboatCompany obj : list.getDmVmaTugboatCompany()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmVmaTugboatCompanyLocalServiceUtil.updateVmaTugboatCompany(fromMaritimeCode, obj.getMaritimeCode(), obj.getTugboatCompanyCode(),
									obj.getTugboatCompanyName(), obj.getCompanyAddress(), obj.getContactEmail(), obj.getTelNo(),
									obj.getFaxNo(), obj.getRemarks(), obj.getSyncVersion(), obj.getCompanyShortName());

						} else {

							DmVmaTugboatCompanyLocalServiceUtil.deleteVmaTugboatCompany(fromMaritimeCode, obj.getTugboatCompanyCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmHistoryTugboatCompanyList) {
					DmHistoryTugboatCompanyList list = (DmHistoryTugboatCompanyList) object;
					for (DmHistoryTugboatCompany obj : list.getDmHistoryTugboatCompany()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmHistoryTugboatCompanyLocalServiceUtil.updateHistoryTugboatCompany(fromMaritimeCode, obj.getMaritimeCode(), obj.getTugboatCompanyCode(),
									obj.getTugboatCompanyName(), obj.getCompanyAddress(), obj.getContactEmail(), obj.getTelNo(),
									obj.getFaxNo(), obj.getRemarks(), obj.getSyncVersion(), obj.getCompanyShortName());

						} else {

							DmHistoryTugboatCompanyLocalServiceUtil.deleteHistoryTugboatCompany(fromMaritimeCode, obj.getTugboatCompanyCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmVmaTugboatList) {
					DmVmaTugboatList list = (DmVmaTugboatList) object;
					for (DmVmaTugboat obj : list.getDmVmaTugboat()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmVmaTugboatLocalServiceUtil.updateVmaTugboat(fromMaritimeCode, obj.getMaritimeCode(),
									obj.getTugboatCompanyCode(), obj.getTugboatCompanyName(), obj.getShipCode(),
									obj.getShipName(), GetterUtil.getDouble(obj.getPower()),
									GetterUtil.getDouble(obj.getLOA()), GetterUtil.getDouble(obj.getBreadth()),
									GetterUtil.getDouble(obj.getClearanceHeight()),
									GetterUtil.getDouble(obj.getDisplacement()), obj.getUnitPower(),
									GetterUtil.getDouble(obj.getVndUnitPrice()),
									GetterUtil.getDouble(obj.getUsdUnitPrice()), GetterUtil.getInteger(obj.getGT()),
									GetterUtil.getInteger(obj.getNT()), GetterUtil.getInteger(obj.getDWT()),
									obj.getUnitGRT(), obj.getUnitNT(), obj.getUnitDWT(), obj.getRemarks(),
									obj.getSyncVersion(), obj.getTugboatShortName(), FormatData.parseStringToDate(obj.getTugboatExpiredDate()));

						} else {

							DmVmaTugboatLocalServiceUtil.deleteVmaTugboat(fromMaritimeCode, obj.getShipCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmHistoryTugboatList) {
					DmHistoryTugboatList list = (DmHistoryTugboatList) object;
					for (DmHistoryTugboat obj : list.getDmHistoryTugboat()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmHistoryTugboatLocalServiceUtil.updateHistoryTugboat(fromMaritimeCode,
									obj.getMaritimeCode(), obj.getTugboatCompanyCode(), obj.getTugboatCompanyName(),
									obj.getShipCode(), obj.getShipName(), GetterUtil.getDouble(obj.getPower()),
									GetterUtil.getDouble(obj.getLOA()), GetterUtil.getDouble(obj.getBreadth()),
									GetterUtil.getDouble(obj.getClearanceHeight()),
									GetterUtil.getDouble(obj.getDisplacement()), obj.getRemarks(), obj.getUnitPower(),
									GetterUtil.getDouble(obj.getVndUnitPrice()),
									GetterUtil.getDouble(obj.getUsdUnitPrice()), GetterUtil.getInteger(obj.getGT()), GetterUtil.getInteger(obj.getNT()), GetterUtil.getInteger(obj.getDWT()),
									obj.getUnitGRT(), obj.getUnitNT(), obj.getUnitDWT(), obj.getSyncVersion(), obj.getTugboatShortName(), FormatData.parseStringToDate(obj.getTugboatExpiredDate()));

						} else {

							DmHistoryTugboatLocalServiceUtil.deleteHistoryTugboat(fromMaritimeCode, obj.getShipCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmVmaPilotCompanyList) {
					DmVmaPilotCompanyList list = (DmVmaPilotCompanyList) object;
					for (DmVmaPilotCompany obj : list.getDmVmaPilotCompany()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmVmaPilotCompanyLocalServiceUtil.updatePilotCompany(fromMaritimeCode, obj.getMaritimeCode(),
									obj.getPilotCompanyCode(), obj.getPilotCompanyName(), obj.getTelNo(), obj.getFaxNo(), obj.getContactEmail(), obj.getCompanyAddress(),
									obj.getRemarks(), obj.getSyncVersion(), obj.getCompanyShortName());

						} else {

							DmVmaPilotCompanyLocalServiceUtil.deletePilotCompany(fromMaritimeCode, obj.getPilotCompanyCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmHistoryPilotCompanyList) {
					DmHistoryPilotCompanyList list = (DmHistoryPilotCompanyList) object;
					for (DmHistoryPilotCompany obj : list.getDmHistoryPilotCompany()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmHistoryPilotCompanyLocalServiceUtil.updateHistoryPilotCompany(fromMaritimeCode, obj.getMaritimeCode(),
									obj.getPilotCompanyCode(), obj.getPilotCompanyName(), obj.getTelNo(), obj.getFaxNo(), obj.getContactEmail(), obj.getCompanyAddress(),
									obj.getRemarks(), obj.getSyncVersion(), obj.getCompanyShortName());

						} else {

							DmHistoryPilotCompanyLocalServiceUtil.deleteHistoryPilotCompany(fromMaritimeCode, obj.getPilotCompanyCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmVmaPilotList) {
					DmVmaPilotList list = (DmVmaPilotList) object;
					for (DmVmaPilot obj : list.getDmVmaPilot()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmVmaPilotLocalServiceUtil.updateVmaPilot(fromMaritimeCode, obj.getMaritimeCode(), obj.getPilotCompanyCode(),
									obj.getPilotCompanyName(), obj.getPilotBOD(), obj.getPilotNo(), obj.getPilotCode(), obj.getPilotName(), obj.getPilotCertificateNo(),
									obj.getPilotCategoryCode(), FormatData.parseStringToDate(obj.getPilotCertificateDate()),
									obj.getRemarks(), obj.getSyncVersion(), obj.getPilotShortName(), FormatData.parseStringToDate(obj.getPilotExpiredDate()));

						} else {

							DmVmaPilotLocalServiceUtil.deleteVmaPilot(fromMaritimeCode, obj.getPilotCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmHistoryPilotList) {
					DmHistoryPilotList list = (DmHistoryPilotList) object;
					for (DmHistoryPilot obj : list.getDmHistoryPilot()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmHistoryPilotLocalServiceUtil.updateHistoryPilot(fromMaritimeCode, obj.getMaritimeCode(), obj.getPilotCompanyCode(),
									obj.getPilotCompanyName(), obj.getPilotBOD(), obj.getPilotNo(), obj.getPilotCode(), obj.getPilotName(), obj.getPilotCertificateNo(),
									obj.getPilotCategoryCode(), FormatData.parseStringToDate(obj.getPilotCertificateDate()),
									obj.getRemarks(), obj.getSyncVersion(), obj.getPilotShortName(), FormatData.parseStringToDate(obj.getPilotExpiredDate()));

						} else {

							DmHistoryPilotLocalServiceUtil.deleteHistoryPilot(fromMaritimeCode, obj.getPilotCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmVmaPilotCategoryList) {
					DmVmaPilotCategoryList list = (DmVmaPilotCategoryList) object;
					for (DmVmaPilotCategory obj : list.getDmVmaPilotCategory()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmVmaPilotCategoryLocalServiceUtil.updateVmaPilotCategory(fromMaritimeCode,
									obj.getPilotCategoryCode(), obj.getPilotCategoryName(), obj.getMaxLength(), obj.getSafeTime(), obj.getRemarks(), obj.getGrossTonage(), obj.getSyncVersion());

						} else {

							DmVmaPilotCategoryLocalServiceUtil.deleteVmaPilotCategory(fromMaritimeCode, obj.getPilotCategoryCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmHistoryPilotCategoryList) {
					DmHistoryPilotCategoryList list = (DmHistoryPilotCategoryList) object;
					for (DmHistoryPilotCategory obj : list.getDmHistoryPilotCategory()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmHistoryPilotCategoryLocalServiceUtil.updateHistoryPilotCategory(fromMaritimeCode,
									obj.getPilotCategoryCode(), obj.getPilotCategoryName(), obj.getMaxLength(), obj.getSafeTime(), obj.getRemarks(), obj.getGrossTonage(), obj.getSyncVersion());

						} else {

							DmHistoryPilotCategoryLocalServiceUtil.deleteHistoryPilotCategory(fromMaritimeCode, obj.getPilotCategoryCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmVmaShipOwnerList) {
					DmVmaShipOwnerList list = (DmVmaShipOwnerList) object;
					for (DmVmaShipOwner obj : list.getDmVmaShipOwner()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmVmaShipOwnerLocalServiceUtil.updateVmaShipOwner(fromMaritimeCode, obj.getMaritimeCode(),
									obj.getShipOwnerCode(), obj.getTaxCode(), obj.getCompanyName(), obj.getCompanyAddress(),
									obj.getContactEmail(), obj.getTelNo(), obj.getFaxNo(),
									obj.getIsShipOwner(), obj.getIsShipOperator(), obj.getRemarks(), obj.getSyncVersion(),
									obj.getCompanyShortName(), obj.getIsOther());
						} else {
							DmVmaShipOwnerLocalServiceUtil.deleteVmaShipOwner(fromMaritimeCode, obj.getShipOwnerCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmHistoryShipOwnerList) {
					DmHistoryShipOwnerList list = (DmHistoryShipOwnerList) object;
					for (DmHistoryShipOwner obj : list.getDmHistoryShipOwner()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmHistoryShipOwnerLocalServiceUtil.updateHistoryShipOwner(fromMaritimeCode, obj.getMaritimeCode(),
									obj.getShipOwnerCode(), obj.getTaxCode(), obj.getCompanyName(), obj.getCompanyAddress(),
									obj.getContactEmail(), obj.getTelNo(), obj.getFaxNo(),
									obj.getIsShipOwner(), obj.getIsShipOperator(), obj.getRemarks(), obj.getSyncVersion(),
									obj.getCompanyShortName(), obj.getIsOther());
						} else {
							DmHistoryShipOwnerLocalServiceUtil.deleteHistoryShipOwner(fromMaritimeCode, obj.getShipOwnerCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmVmaShipyardList) {
					DmVmaShipyardList list = (DmVmaShipyardList) object;
					for (DmVmaShipyard obj : list.getDmVmaShipyard()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmVmaShipyardLocalServiceUtil.updateVmaShipYard(
									fromMaritimeCode, obj.getShipYardCode(),
									obj.getCompanyName(),
									obj.getCompanyAddress(),
									obj.getContactEmail(), obj.getFaxNo(),
									obj.getRemarks(), obj.getMaritimeCode(),
									obj.getTelNo(), obj.getTaxCode(),
									obj.getSyncVersion(), obj.getCompanyShortName(),
									obj.getMarkupMaintainane(), obj.getMarkupConstruction(),
									obj.getMarkupDeconstruction(), obj.getProfileMaintainane(),
									obj.getProfileConstruction(), obj.getProfileDeconstruction());
						} else {
							DmVmaShipyardLocalServiceUtil.deleteVmaShipYard(fromMaritimeCode, obj.getShipYardCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmHistoryShipyardList) {
					DmHistoryShipyardList list = (DmHistoryShipyardList) object;
					for (DmHistoryShipyard obj : list.getDmHistoryShipyard()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmHistoryShipyardLocalServiceUtil
									.updateHistoryShipYard(fromMaritimeCode,
											obj.getShipYardCode(),
											obj.getCompanyName(),
											obj.getCompanyAddress(),
											obj.getContactEmail(),
											obj.getFaxNo(), obj.getRemarks(),
											obj.getMaritimeCode(),
											obj.getTelNo(), obj.getTaxCode(),
											obj.getSyncVersion(),
											obj.getCompanyShortName(),
											obj.getMarkupMaintainane(), obj.getMarkupConstruction(),
											obj.getMarkupDeconstruction(), obj.getProfileMaintainane(),
											obj.getProfileConstruction(), obj.getProfileDeconstruction());
						} else {
							DmHistoryShipyardLocalServiceUtil.deleteHistoryShipYard(fromMaritimeCode, obj.getShipYardCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmVmaSecurityOfficeList) {
					DmVmaSecurityOfficeList list = (DmVmaSecurityOfficeList) object;
					for (DmVmaSecurityOffice obj : list.getDmVmaSecurityOffice()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmVmaSecurityOfficeLocalServiceUtil.updateVmaSecurityOffice(fromMaritimeCode,
									obj.getSecurityOfficeCode(), obj.getCompanyName(), obj.getCompanyAddress(), obj.getContactEmail(), obj.getFaxNo(), obj.getRemarks(),
									obj.getMaritimeCode(), obj.getTelNo(), obj.getSyncVersion());
						} else {
							DmVmaSecurityOfficeLocalServiceUtil.deleteVmaSecurityOffice(fromMaritimeCode, obj.getSecurityOfficeCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmHistorySecurityOfficeList) {
					DmHistorySecurityOfficeList list = (DmHistorySecurityOfficeList) object;
					for (DmHistorySecurityOffice obj : list.getDmHistorySecurityOffice()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmHistorySecurityOfficeLocalServiceUtil.updateHistorySecurityOffice(fromMaritimeCode,
									obj.getSecurityOfficeCode(), obj.getCompanyName(), obj.getCompanyAddress(), obj.getContactEmail(), obj.getFaxNo(), obj.getRemarks(),
									obj.getMaritimeCode(), obj.getTelNo(), obj.getSyncVersion());
						} else {
							DmHistorySecurityOfficeLocalServiceUtil.deleteHistorySecurityOffice(fromMaritimeCode, obj.getSecurityOfficeCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmVmaShipTypeList) {
					DmVmaShipTypeList list = (DmVmaShipTypeList) object;
					for (DmVmaShipType obj : list.getDmVmaShipType()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmVmaShipTypeLocalServiceUtil.updateVmaShipType(fromMaritimeCode, obj.getShipTypeCode(),
									obj.getShipTypeName(), GetterUtil.getInteger(obj.getApplyBoat()),
									GetterUtil.getInteger(obj.getApplyShip()), obj.getRemarks(),
									obj.getShipTypeRef(), obj.getSyncVersion());
						} else {
							DmVmaShipTypeLocalServiceUtil.deleteVmaShipType(fromMaritimeCode, obj.getShipTypeCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof DmHistoryVmashipTypeList) {
					DmHistoryVmashipTypeList list = (DmHistoryVmashipTypeList) object;
					for (DmHistoryVmashipType obj : list.getDmHistoryVmashipType()) {

						if(obj.getStatus() == 0) {
							// 0 la them moi/update
							DmHistoryVmaShipTypeLocalServiceUtil.updateHistoryVmaShipType(fromMaritimeCode, obj.getShipTypeCode(),
									obj.getShipTypeName(), GetterUtil.getInteger(obj.getApplyBoat()),
									GetterUtil.getInteger(obj.getApplyShip()), obj.getRemarks(),
									obj.getShipTypeRef(), obj.getSyncVersion());
						} else {
							DmHistoryVmaShipTypeLocalServiceUtil.deleteHistoryVmaShipType(fromMaritimeCode, obj.getShipTypeCode(), obj.getSyncVersion());
						}
					}
				} else if (object instanceof PortList) {
					PortList list = (PortList) object;
					for(Port obj : list.getPort()) {
						DanhMucBussinessUtils.updateDmPort(header, obj);
					}

				} else if (object instanceof DmHistoryPortList) {
					DmHistoryPortList list = (DmHistoryPortList) object;
					for(vn.nsw.model.DmHistoryPortList.DmHistoryPort obj : list.getDmHistoryPort()) {
						DanhMucBussinessUtils.updateDmHistoryPort(header, obj);

					}

				} else if (object instanceof DmPortRegionList) {

					DmPortRegionList list = (DmPortRegionList) object;
					for(vn.nsw.model.DmPortRegionList.DmPortRegion obj : list.getDmPortRegion()) {
						DanhMucBussinessUtils.updateDmPortRegion(header, obj);
					}
				} else if (object instanceof DmHistoryPortRegionList) {

					DmHistoryPortRegionList list = (DmHistoryPortRegionList) object;
					for(vn.nsw.model.DmHistoryPortRegionList.DmHistoryPortRegion obj : list.getDmHistoryPortRegion()) {
						DanhMucBussinessUtils.updateDmHistoryPortRegion(header, obj);
					}
				} else if (object instanceof DmPortWharfList) {
					DmPortWharfList list = (DmPortWharfList) object;
					for(vn.nsw.model.DmPortWharfList.DmPortWharf obj : list.getDmPortWharf()) {
						DanhMucBussinessUtils.updateDmPortWharf(header, obj);
					}
				} else if (object instanceof DmHistoryPortWharfList) {
					DmHistoryPortWharfList list = (DmHistoryPortWharfList) object;
					for(vn.nsw.model.DmHistoryPortWharfList.DmHistoryPortWharf obj : list.getDmHistoryPortWharf()) {
						DanhMucBussinessUtils.updateDmHistoryPortWharf(header, obj);
					}
				} else if (object instanceof DmPortHarbourList) {
					DmPortHarbourList list = (DmPortHarbourList) object;
					for(vn.nsw.model.DmPortHarbourList.DmPortHarbour obj : list.getDmPortHarbour()) {
						DanhMucBussinessUtils.updateDmPortHarbour(header, obj);
					}
				} else if (object instanceof DmHistoryPortHarbourList) {
					DmHistoryPortHarbourList list = (DmHistoryPortHarbourList) object;
					for(vn.nsw.model.DmHistoryPortHarbourList.DmHistoryPortHarbour obj : list.getDmHistoryPortHarbour()) {
						DanhMucBussinessUtils.updateDmHistoryPortHarbour(header, obj);
					}
				} else if (object instanceof DmMaritimeList) {
					DmMaritimeList list = (DmMaritimeList) object;
					for(vn.nsw.model.DmMaritimeList.DmMaritime obj : list.getDmMaritime()) {
						DanhMucBussinessUtils.updateDmMaritime(header, obj);
					}
				} else if (object instanceof DmHistoryMaritimeList) {
					DmHistoryMaritimeList list = (DmHistoryMaritimeList) object;
					for(vn.nsw.model.DmHistoryMaritimeList.DmHistoryMaritime obj : list.getDmHistoryMaritime()) {
						DanhMucBussinessUtils.updateDmHistoryMaritime(header, obj);
					}
				} else if (object instanceof DmDataItemList) {
					DmDataItemList list = (DmDataItemList) object;
					for(vn.nsw.model.DmDataItemList.DmDataitem obj : list.getDmDataItem()) {
						DanhMucBussinessUtils.updateDmDataItem(header, obj);
					}
				}
			}
			
			return true;
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		
		return false;
	}
	
	//Dong do danh muc giua cac cang vu
	public static void dongBoDanhMuc(String maritimeCode, Object object) throws SystemException {
		
		// Trong truong hop khong phai MTGateway thi chi dong goi msg va gui
		// khi maritimeCode nam trong danh sach ma cang vu cau hinh tren server, de tranh tinh trang gui loop len MTGateway
		if(!MessageType.BGTVT.equals(maritimeCode)) {
			try {
				int messageType = 0;
				String xmlData = null;
				Header header = null; 
				
				log.info("===dongBoDanhMuc===" + maritimeCode);
				if (object instanceof com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) {
					messageType = MessageType.DONG_BO_DM_VMA_TUGBOAT_COMPANY;
					
					DmVmaTugboatCompany item = new DmVmaTugboatCompanyList.DmVmaTugboatCompany();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getMaritimeCode());
					item.setTugboatCompanyCode(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getTugboatCompanyCode());
					item.setTugboatCompanyName(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getTugboatCompanyName());
					item.setCompanyAddress(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getCompanyAddress());
					item.setContactEmail(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getContactEmail());
					item.setTelNo(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getTelNo());
					item.setFaxNo(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getFaxNo());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getSyncVersion());
					item.setCompanyShortName(((com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany) object).getCompanyShortName());
					
					DmVmaTugboatCompanyList list = new DmVmaTugboatCompanyList();
					list.getDmVmaTugboatCompany().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
					
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) {
					messageType = MessageType.DONG_BO_DM_HISTORY_TUGBOAT_COMPANY;
					
					DmHistoryTugboatCompany item = new DmHistoryTugboatCompanyList.DmHistoryTugboatCompany();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getMaritimeCode());
					item.setTugboatCompanyCode(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getTugboatCompanyCode());
					item.setTugboatCompanyName(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getTugboatCompanyName());
					item.setCompanyAddress(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getCompanyAddress());
					item.setContactEmail(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getContactEmail());
					item.setTelNo(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getTelNo());
					item.setFaxNo(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getFaxNo());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getSyncVersion());
					item.setCompanyShortName(((com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany) object).getCompanyShortName());
					
					DmHistoryTugboatCompanyList list = new DmHistoryTugboatCompanyList();
					list.getDmHistoryTugboatCompany().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmVmaTugboat) {
					messageType = MessageType.DONG_BO_DM_VMA_TUGBOAT;
					
					DmVmaTugboat item = new DmVmaTugboatList.DmVmaTugboat();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getMaritimeCode());
					item.setTugboatCompanyCode(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getTugboatCompanyCode());
					item.setTugboatCompanyName(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getTugboatCompanyName());
					item.setShipCode(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getShipCode());
					item.setShipName(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getShipName());
					item.setPower(String.valueOf(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getPower()));
					item.setLOA(String.valueOf(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getLoa()));
					item.setBreadth(String.valueOf(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getBreadth()));
					item.setClearanceHeight(String.valueOf(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getClearanceHeight()));
					item.setDisplacement(String.valueOf(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getDisplacement()));
					item.setUnitPower(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getUnitPower());
					item.setVndUnitPrice(String.valueOf(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getVndUnitPrice()));
					item.setUsdUnitPrice(String.valueOf(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getUsdUnitPrice()));
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getSyncVersion());
					item.setTugboatShortName(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getTugboatShortName());
					item.setTugboatExpiredDate(FormatData.parseDateToTring(((com.fds.nsw.nghiepvu.model.DmVmaTugboat) object).getTugboatExpiredDate()));
					
					DmVmaTugboatList list = new DmVmaTugboatList();
					list.getDmVmaTugboat().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmHistoryTugboat) {
					messageType = MessageType.DONG_BO_DM_HISTORY_TUGBOAT;
					
					DmHistoryTugboat item = new DmHistoryTugboatList.DmHistoryTugboat();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getMaritimeCode());
					item.setTugboatCompanyCode(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getTugboatCompanyCode());
					item.setTugboatCompanyName(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getTugboatCompanyName());
					item.setShipCode(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getShipCode());
					item.setShipName(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getShipName());
					item.setPower(String.valueOf(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getPower()));
					item.setLOA(String.valueOf(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getLoa()));
					item.setBreadth(String.valueOf(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getBreadth()));
					item.setClearanceHeight(String.valueOf(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getClearanceHeight()));
					item.setDisplacement(String.valueOf(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getDisplacement()));
					item.setUnitPower(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getUnitPower());
					item.setVndUnitPrice(String.valueOf(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getVndUnitPrice()));
					item.setUsdUnitPrice(String.valueOf(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getUsdUnitPrice()));
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getSyncVersion());
					item.setTugboatShortName(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getTugboatShortName());
					item.setTugboatExpiredDate(FormatData.parseDateToTring(((com.fds.nsw.nghiepvu.model.DmHistoryTugboat) object).getTugboatExpiredDate()));
					
					DmHistoryTugboatList list = new DmHistoryTugboatList();
					list.getDmHistoryTugboat().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) {
					messageType = MessageType.DONG_BO_DM_VMA_PILOT_COMPANY;
					
					DmVmaPilotCompany item = new DmVmaPilotCompanyList.DmVmaPilotCompany();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getMaritimeCode());
					item.setPilotCompanyCode(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getPilotCompanyCode());
					item.setPilotCompanyName(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getPilotCompanyName());
					item.setCompanyAddress(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getCompanyAddress());
					item.setContactEmail(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getContactEmail());
					item.setTelNo(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getTelNo());
					item.setFaxNo(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getFaxNo());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getSyncVersion());
					item.setCompanyShortName(((com.fds.nsw.nghiepvu.model.DmVmaPilotCompany) object).getCompanyShortName());
					
					DmVmaPilotCompanyList list = new DmVmaPilotCompanyList();
					list.getDmVmaPilotCompany().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) {
					messageType = MessageType.DONG_BO_DM_HISTORY_PILOT_COMPANY;
					
					DmHistoryPilotCompany item = new DmHistoryPilotCompanyList.DmHistoryPilotCompany();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getMaritimeCode());
					item.setPilotCompanyCode(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getPilotCompanyCode());
					item.setPilotCompanyName(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getPilotCompanyName());
					item.setCompanyAddress(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getCompanyAddress());
					item.setContactEmail(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getContactEmail());
					item.setTelNo(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getTelNo());
					item.setFaxNo(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getFaxNo());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getSyncVersion());
					item.setCompanyShortName(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany) object).getCompanyShortName());
					
					DmHistoryPilotCompanyList list = new DmHistoryPilotCompanyList();
					list.getDmHistoryPilotCompany().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmVmaPilot) {
					messageType = MessageType.DONG_BO_DM_VMA_PILOT;
					
					DmVmaPilot item = new DmVmaPilotList.DmVmaPilot();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getMaritimeCode());
					item.setPilotCompanyCode(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotCompanyCode());
					item.setPilotCompanyName(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotCompanyName());
					item.setPilotCode(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotCode());
					item.setPilotName(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotName());
					item.setPilotBOD(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotBOD());
					item.setPilotNo(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotNo());
					item.setPilotCertificateNo(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotCertificateNo());
					item.setPilotCertificateDate(FormatData.parseDateToTring(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotCertificateDate()));
					item.setPilotCategoryCode(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotCategoryCode());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getSyncVersion());
					item.setPilotShortName(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotShortName());
					item.setPilotExpiredDate(FormatData.parseDateToTring(((com.fds.nsw.nghiepvu.model.DmVmaPilot) object).getPilotExpiredDate()));
					
					DmVmaPilotList list = new DmVmaPilotList();
					list.getDmVmaPilot().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmHistoryPilot) {
					messageType = MessageType.DONG_BO_DM_HISTORY_PILOT;
					
					DmHistoryPilot item = new DmHistoryPilotList.DmHistoryPilot();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getMaritimeCode());
					item.setPilotCompanyCode(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotCompanyCode());
					item.setPilotCompanyName(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotCompanyName());
					item.setPilotCode(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotCode());
					item.setPilotName(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotName());
					item.setPilotBOD(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotBOD());
					item.setPilotNo(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotNo());
					item.setPilotCertificateNo(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotCertificateNo());
					item.setPilotCertificateDate(FormatData.parseDateToTring(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotCertificateDate()));
					item.setPilotCategoryCode(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotCategoryCode());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getSyncVersion());
					item.setPilotShortName(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotShortName());
					item.setPilotExpiredDate(FormatData.parseDateToTring(((com.fds.nsw.nghiepvu.model.DmHistoryPilot) object).getPilotExpiredDate()));
					
					DmHistoryPilotList list = new DmHistoryPilotList();
					list.getDmHistoryPilot().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmVmaPilotCategory) {
					messageType = MessageType.DONG_BO_DM_VMA_PILOT_CATEGORY;
					
					DmVmaPilotCategory item = new DmVmaPilotCategoryList.DmVmaPilotCategory();
					
					item.setPilotCategoryCode(((com.fds.nsw.nghiepvu.model.DmVmaPilotCategory) object).getPilotCategoryCode());
					item.setPilotCategoryName(((com.fds.nsw.nghiepvu.model.DmVmaPilotCategory) object).getPilotCategoryName());
					item.setMaxLength(((com.fds.nsw.nghiepvu.model.DmVmaPilotCategory) object).getMaxLength());
					item.setGrossTonage(((com.fds.nsw.nghiepvu.model.DmVmaPilotCategory) object).getGrossTonage());
					item.setSafeTime(((com.fds.nsw.nghiepvu.model.DmVmaPilotCategory) object).getSafeTime());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmVmaPilotCategory) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmVmaPilotCategory) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmVmaPilotCategory) object).getSyncVersion());
					
					DmVmaPilotCategoryList list = new DmVmaPilotCategoryList();
					list.getDmVmaPilotCategory().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory) {
					messageType = MessageType.DONG_BO_DM_HISTORY_PILOT_CATEGORY;
					
					DmHistoryPilotCategory item = new DmHistoryPilotCategoryList.DmHistoryPilotCategory();
					
					item.setPilotCategoryCode(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory) object).getPilotCategoryCode());
					item.setPilotCategoryName(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory) object).getPilotCategoryName());
					item.setMaxLength(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory) object).getMaxLength());
					item.setGrossTonage(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory) object).getGrossTonage());
					item.setSafeTime(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory) object).getSafeTime());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory) object).getSyncVersion());
					
					DmHistoryPilotCategoryList list = new DmHistoryPilotCategoryList();
					list.getDmHistoryPilotCategory().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmVmaShipOwner) {
					messageType = MessageType.DONG_BO_DM_VMA_SHIP_OWNER;
					
					DmVmaShipOwner item = new DmVmaShipOwnerList.DmVmaShipOwner();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getMaritimeCode());
					item.setShipOwnerCode(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getShipOwnerCode());
					item.setTaxCode(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getTaxCode());
					item.setCompanyName(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getCompanyName());
					item.setCompanyAddress(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getCompanyAddress());
					item.setContactEmail(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getContactEmail());
					item.setTelNo(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getTelNo());
					item.setFaxNo(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getFaxNo());
					item.setCompanyShortName(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getCompanyShortName());
					item.setIsShipOwner(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getIsShipOwner());
					item.setIsShipOperator(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getIsShipOperator());
					item.setIsOther(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getIsOther());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmVmaShipOwner) object).getSyncVersion());
					
					DmVmaShipOwnerList list = new DmVmaShipOwnerList();
					list.getDmVmaShipOwner().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) {
					messageType = MessageType.DONG_BO_DM_HISTORY_SHIP_OWNER;
					
					DmHistoryShipOwner item = new DmHistoryShipOwnerList.DmHistoryShipOwner();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getMaritimeCode());
					item.setShipOwnerCode(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getShipOwnerCode());
					item.setTaxCode(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getTaxCode());
					item.setCompanyName(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getCompanyName());
					item.setCompanyAddress(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getCompanyAddress());
					item.setContactEmail(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getContactEmail());
					item.setTelNo(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getTelNo());
					item.setFaxNo(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getFaxNo());
					item.setCompanyShortName(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getCompanyShortName());
					item.setIsShipOwner(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getIsShipOwner());
					item.setIsShipOperator(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getIsShipOperator());
					item.setIsOther(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getIsOther());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmHistoryShipOwner) object).getSyncVersion());
					
					DmHistoryShipOwnerList list = new DmHistoryShipOwnerList();
					list.getDmHistoryShipOwner().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmVmaShipyard) {
					messageType = MessageType.DONG_BO_DM_VMA_SHIPYARD;
					
					DmVmaShipyard item = new DmVmaShipyardList.DmVmaShipyard();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getMaritimeCode());
					item.setShipYardCode(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getShipYardCode());
					item.setTaxCode(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getTaxCode());
					item.setCompanyName(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getCompanyName());
					item.setCompanyAddress(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getCompanyAddress());
					item.setContactEmail(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getContactEmail());
					item.setTelNo(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getTelNo());
					item.setFaxNo(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getFaxNo());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getIsDelete());
					item.setCompanyShortName(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getCompanyShortName());
					item.setProfileMaintainane(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getProfileMaintainane());
					item.setProfileConstruction(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getProfileConstruction());
					item.setProfileDeconstruction(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getProfileDeconstruction());
					item.setMarkupMaintainane(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getMarkupMaintainane());
					item.setMarkupConstruction(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getMarkupConstruction());
					item.setMarkupDeconstruction(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getMarkupDeconstruction());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmVmaShipyard) object).getSyncVersion());
					
					DmVmaShipyardList list = new DmVmaShipyardList();
					list.getDmVmaShipyard().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmHistoryShipyard) {
					messageType = MessageType.DONG_BO_DM_HISTORY_SHIPYARD;
					
					DmHistoryShipyard item = new DmHistoryShipyardList.DmHistoryShipyard();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getMaritimeCode());
					item.setShipYardCode(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getShipYardCode());
					item.setTaxCode(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getTaxCode());
					item.setCompanyName(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getCompanyName());
					item.setCompanyAddress(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getCompanyAddress());
					item.setContactEmail(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getContactEmail());
					item.setTelNo(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getTelNo());
					item.setFaxNo(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getFaxNo());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getIsDelete());
					item.setCompanyShortName(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getCompanyShortName());
					item.setProfileMaintainane(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getProfileMaintainane());
					item.setProfileConstruction(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getProfileConstruction());
					item.setProfileDeconstruction(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getProfileDeconstruction());
					item.setMarkupMaintainane(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getMarkupMaintainane());
					item.setMarkupConstruction(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getMarkupConstruction());
					item.setMarkupDeconstruction(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getMarkupDeconstruction());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmHistoryShipyard) object).getSyncVersion());
					
					DmHistoryShipyardList list = new DmHistoryShipyardList();
					list.getDmHistoryShipyard().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) {
					messageType = MessageType.DONG_BO_DM_VMA_SECURITY_OFFICE;
					
					DmVmaSecurityOffice item = new DmVmaSecurityOfficeList.DmVmaSecurityOffice();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) object).getMaritimeCode());
					item.setSecurityOfficeCode(((com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) object).getSecurityOfficeCode());
					item.setCompanyName(((com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) object).getCompanyName());
					item.setCompanyAddress(((com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) object).getCompanyAddress());
					item.setContactEmail(((com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) object).getContactEmail());
					item.setTelNo(((com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) object).getTelNo());
					item.setFaxNo(((com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) object).getFaxNo());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice) object).getSyncVersion());
					
					DmVmaSecurityOfficeList list = new DmVmaSecurityOfficeList();
					list.getDmVmaSecurityOffice().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) {
					messageType = MessageType.DONG_BO_DM_HISTORY_SECURITY_OFFICE;
					
					DmHistorySecurityOffice item = new DmHistorySecurityOfficeList.DmHistorySecurityOffice();
					
					item.setMaritimeCode(((com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) object).getMaritimeCode());
					item.setSecurityOfficeCode(((com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) object).getSecurityOfficeCode());
					item.setCompanyName(((com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) object).getCompanyName());
					item.setCompanyAddress(((com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) object).getCompanyAddress());
					item.setContactEmail(((com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) object).getContactEmail());
					item.setTelNo(((com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) object).getTelNo());
					item.setFaxNo(((com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) object).getFaxNo());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice) object).getSyncVersion());
					
					DmHistorySecurityOfficeList list = new DmHistorySecurityOfficeList();
					list.getDmHistorySecurityOffice().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmVmaShipType) {
					messageType = MessageType.DONG_BO_DM_VMA_SHIP_TYPE;
					
					DmVmaShipType item = new DmVmaShipTypeList.DmVmaShipType();
					
					item.setShipTypeCode(((com.fds.nsw.nghiepvu.model.DmVmaShipType) object).getShipTypeCode());
					item.setShipTypeName(((com.fds.nsw.nghiepvu.model.DmVmaShipType) object).getShipTypeName());
					item.setApplyShip(String.valueOf(((com.fds.nsw.nghiepvu.model.DmVmaShipType) object).getApplyShip()));
					item.setApplyBoat(String.valueOf(((com.fds.nsw.nghiepvu.model.DmVmaShipType) object).getApplyBoat()));
					item.setShipTypeRef(((com.fds.nsw.nghiepvu.model.DmVmaShipType) object).getShipTypeRef());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmVmaShipType) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmVmaShipType) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmVmaShipType) object).getSyncVersion());
					
					DmVmaShipTypeList list = new DmVmaShipTypeList();
					list.getDmVmaShipType().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				} else if (object instanceof com.fds.nsw.nghiepvu.model.DmHistoryShipType) {
					messageType = MessageType.DONG_BO_DM_HISTORY_VMASHIP_TYPE;
					
					DmHistoryVmashipType item = new DmHistoryVmashipTypeList.DmHistoryVmashipType();
					
					item.setShipTypeCode(((com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType) object).getShipTypeCode());
					item.setShipTypeName(((com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType) object).getShipTypeName());
					item.setApplyShip(String.valueOf(((com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType) object).getApplyShip()));
					item.setApplyBoat(String.valueOf(((com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType) object).getApplyBoat()));
					item.setShipTypeRef(((com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType) object).getShipTypeRef());
					item.setRemarks(((com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType) object).getRemarks());
					item.setStatus(((com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType) object).getIsDelete());
					item.setSyncVersion(((com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType) object).getSyncVersion());
					
					DmHistoryVmashipTypeList list = new DmHistoryVmashipTypeList();
					list.getDmHistoryVmashipType().add(item);
					
					header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH, 
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);
					
					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
						MessageFactory.convertObjectToXml2(list));
				}
				
				// SonVH sua doi ngay 01.05.2020. Stop dong bo danh muc nghiep vu giua cac cang vu.
				if(header != null && xmlData != null) {
					//insertDMSycnQueue(maritimeCode, header, xmlData);
				}
			} catch(Exception e) {
				throw new SystemException(e);
			}
		}
	}
	
	public static void insertCangVuTichHopQueue(DmGtRouteConfig routeConfig, Header header, String xmlData) {
			String locCode = routeConfig.getLocCode();
			
			String webservice = routeConfig.getLinkAddress();
			
			To to = header.getTo();
			to.setIdentity(locCode);
			to.setName(locCode);
			
			header.setTo(to);
			
			insertMessageQueue(header, xmlData, locCode, webservice);
	}
	
	public static void insertCanBoXuLyQueue(DmGtRouteConfig routeConfig, Header header, String xmlData) {
		String locCode = MessageType.NSW;
		String webservice = "-1";
		
		// Check IF CANGVU
		List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByLocCode(MessageType.BGTVT);
		if(routeConfigs != null && routeConfigs.size() > 0) {
			//Edit by Dungnv
			/*locCode = routeConfig.getLocCode();
			webservice = routeConfig.getLinkAddress();*/
			locCode = routeConfigs.get(0).getLocCode();
			webservice = routeConfigs.get(0).getLinkAddress();
		}
		
		To to = header.getTo();
		to.setIdentity(locCode);
		to.setName(locCode);
		
		header.setTo(to);
		
		insertMessageQueue(header, xmlData, locCode, webservice);
}
	
	/**
	 * TH la MTGateway thi se gui het theo danh sach trong bang config, ngoai tru martitimeCode vua gui len
	 * TH la CV chinh sua thi cung se gui het theo danh sach trong bang config, ma cu the chi la MTGateway.
	 * TH la CV nhan dong bo tu MTGateway thi se khoa lai khong gui nua. Neu khong se bi loop, gui nguoc lai MTGatewqy
	 * 
	 * @param maritimeCode
	 * @param header
	 * @param xmlData
	 */
	public static void insertDMSycnQueue(String maritimeCode, Header header, String xmlData) {
		List<DmGtRouteConfig> routeConfigList = DmGtRouteConfigLocalServiceUtil.findByIsDelete(0);
		
		for(DmGtRouteConfig routeConfig : routeConfigList) {
			String locCode = routeConfig.getLocCode();
			
			// Neu tu CV gui dong bo se loc CV day ra va k gui nua.
			//TODO: CHo truong hop dac biet neu maritimeCode co ky tu @ dau tien la tu CV gui dong bo va se loc ra k gui nua
			boolean flag = true;
			if(maritimeCode.indexOf("@") == 0) {
				maritimeCode = maritimeCode.substring(1, maritimeCode.length()); 
				
				if(maritimeCode.equals(locCode)) {
					flag = false;
				}
			}
			
			if(flag) {
				String webservice = routeConfig.getLinkAddress();
				
				To to = header.getTo();
				to.setIdentity(locCode);
				to.setName(locCode);
				
				header.setTo(to);
				
				//TODO: Khong gui sang CV TPHCM va Vung TAU
				if(!locCode.equals("21") && !locCode.equals("12")) {
					insertMessageQueue(header, xmlData, locCode, webservice);
				}
			}
		}
	}
	
	public static void insertDMSycnQueueLegacy(String maritimeCode, Header header, String xmlData, Object obj) throws IOException {
		List<DmGtRouteConfig> routeConfigList = DmGtRouteConfigLocalServiceUtil.findByIsDelete(0);
		
		for(DmGtRouteConfig routeConfig : routeConfigList) {
			String locCode = routeConfig.getLocCode();
			
			boolean flag = true;
			if(maritimeCode.indexOf("@") == 0) {
				maritimeCode = maritimeCode.substring(1, maritimeCode.length()); 
				
				if(maritimeCode.equals(locCode)) {
					flag = false;
				}
			}
			
			if(flag) {
				String webservice = routeConfig.getLinkAddress();
			
				To to = header.getTo();
				to.setIdentity(locCode);
				to.setName(locCode);
				
				header.setTo(to);
				
				if(locCode.equalsIgnoreCase(MessageType.NSW)) {
					if(xmlData != null && xmlData.length() > 0) {
						insertMessageQueue(header, xmlData, locCode, webservice);
					}
				} else if(obj != null) {
					String xmlContent1 = null;
					String xmlContent2 = null;
					
					if(obj instanceof DmDataitem) {
						DmDataItemList.DmDataitem item = new DmDataItemList.DmDataitem();
						
						item.setDataGroupId(String.valueOf(DmDataitem.class.cast(obj).getDatagroupid()));
						item.setCode0(DmDataitem.class.cast(obj).getCode());
						item.setNode1(DmDataitem.class.cast(obj).getNode1());
						item.setNode2(DmDataitem.class.cast(obj).getNode2());
						item.setNode3(DmDataitem.class.cast(obj).getNode3());
						item.setNode4(DmDataitem.class.cast(obj).getNode4());
						item.setLevel(DmDataitem.class.cast(obj).getLevel());
						item.setName(DmDataitem.class.cast(obj).getName());
						item.setDescription(DmDataitem.class.cast(obj).getDescription());
						item.setValidatedFrom(FormatData.parseDateToTring(DmDataitem.class.cast(obj).getValidatedfrom()));
						item.setValidatedTo(FormatData.parseDateToTring(DmDataitem.class.cast(obj).getValidatedto()));
						item.setStatus(DmDataitem.class.cast(obj).getStatus());
						item.setOrder(DmDataitem.class.cast(obj).getOrder());
						item.setShortName(DmDataitem.class.cast(obj).getShortName());
						
						DmDataItemList list = new DmDataItemList();
						list.getDmDataItem().add(item);
						
						xmlContent1 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
								MessageFactory.convertObjectToXml2(list));
					} else if(obj instanceof DmHistoryPort) {
						DmHistoryPortList.DmHistoryPort item = new DmHistoryPortList.DmHistoryPort();
						
						item.setPortCode(DmHistoryPort.class.cast(obj).getPortCode());
						item.setLoCode(DmHistoryPort.class.cast(obj).getLoCode());
						item.setPortName(DmHistoryPort.class.cast(obj).getPortName());
						item.setFullName(DmHistoryPort.class.cast(obj).getFullName());
						item.setFullNameVN(DmHistoryPort.class.cast(obj).getFullNameVN());
						item.setPortType(DmHistoryPort.class.cast(obj).getPortType());
						item.setPortOrder(String.valueOf(DmHistoryPort.class.cast(obj).getPortOrder()));
						item.setAddress(DmHistoryPort.class.cast(obj).getAddress());
						item.setAddressVN(DmHistoryPort.class.cast(obj).getAddressVN());
						item.setStateCode(DmHistoryPort.class.cast(obj).getStateCode());
						item.setCityCode(DmHistoryPort.class.cast(obj).getCityCode());
						item.setIsDelete(String.valueOf(DmHistoryPort.class.cast(obj).getIsDelete()));
						item.setMarkedAsDelete(String.valueOf(DmHistoryPort.class.cast(obj).getMarkedAsDelete()));
						item.setModifiedDate(FormatData.parseDateToTring(DmHistoryPort.class.cast(obj).getModifiedDate()));
						item.setRequestedDate(FormatData.parseDateToTring(DmHistoryPort.class.cast(obj).getRequestedDate()));
						item.setSyncVersion(DmHistoryPort.class.cast(obj).getSyncVersion());
						
						DmHistoryPortList list = new DmHistoryPortList();
						list.getDmHistoryPort().add(item);
						
						xmlContent1 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
								MessageFactory.convertObjectToXml2(list));
						
						DmPort dmPort = DmPortLocalServiceUtil.getByPortCode(DmHistoryPort.class.cast(obj).getPortCode());
						if (dmPort != null) {
							PortList.Port item2 = new PortList.Port();
							
							item2.setPortCode(dmPort.getPortCode());
							item2.setLoCode(dmPort.getLoCode());
							item2.setPortName(dmPort.getPortName());
							item2.setFullName(dmPort.getFullName());
							item2.setFullNameVN(dmPort.getFullNameVN());
							item2.setPortType(dmPort.getPortType());
							item2.setPortOrder(String.valueOf(dmPort.getPortOrder()));
							item2.setAddress(dmPort.getAddress());
							item2.setAddressVN(dmPort.getAddressVN());
							item2.setStateCode(dmPort.getStateCode());
							item2.setCityCode(dmPort.getCityCode());
							item2.setIsDelete(String.valueOf(dmPort.getIsDelete()));
							item2.setMarkedAsDelete(String.valueOf(dmPort.getMarkedAsDelete()));
							item2.setModifiedDate(FormatData.parseDateToTring(dmPort.getModifiedDate()));
							item2.setRequestedDate(FormatData.parseDateToTring(dmPort.getRequestedDate()));
							item2.setSyncVersion(dmPort.getSyncVersion());

							PortList list2 = new PortList();
							list2.getPort().add(item2);

							xmlContent2 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
									MessageFactory.convertObjectToXml2(list2));
						}
						
					} else if(obj instanceof DmHistoryPortWharf) {
						DmHistoryPortWharfList.DmHistoryPortWharf item = new DmHistoryPortWharfList.DmHistoryPortWharf();
						
						item.setPortWharfCode(DmHistoryPortWharf.class.cast(obj).getPortWharfCode());
						item.setPortWharfName(DmHistoryPortWharf.class.cast(obj).getPortWharfName());
						item.setPortWharfNameVN(DmHistoryPortWharf.class.cast(obj).getPortWharfNameVN());
						item.setPortWharfType(String.valueOf(DmHistoryPortWharf.class.cast(obj).getPortWharfType()));
						item.setPortCode(DmHistoryPortWharf.class.cast(obj).getPortCode());
						item.setPortRegionCode(DmHistoryPortWharf.class.cast(obj).getPortRegionCode());
						item.setPortHarbourCode(DmHistoryPortWharf.class.cast(obj).getPortHarbourCode());
						item.setNote(DmHistoryPortWharf.class.cast(obj).getNote());
						item.setIsDelete(String.valueOf(DmHistoryPortWharf.class.cast(obj).getIsDelete()));
						item.setMarkedAsDelete(String.valueOf(DmHistoryPortWharf.class.cast(obj).getMarkedAsDelete()));
						item.setModifiedDate(FormatData.parseDateToTring(DmHistoryPortWharf.class.cast(obj).getModifiedDate()));
						item.setRequestedDate(FormatData.parseDateToTring(DmHistoryPortWharf.class.cast(obj).getRequestedDate()));
						item.setSyncVersion(DmHistoryPortWharf.class.cast(obj).getSyncVersion());
						
						DmHistoryPortWharfList list = new DmHistoryPortWharfList();
						list.getDmHistoryPortWharf().add(item);
						
						xmlContent1 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
								MessageFactory.convertObjectToXml2(list));
						
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(DmHistoryPortWharf.class.cast(obj).getPortWharfCode());
						
						if(dmPortWharf != null) {
							DmPortWharfList.DmPortWharf item2 = new DmPortWharfList.DmPortWharf();
							
							item2.setPortWharfCode(dmPortWharf.getPortWharfCode());
							item2.setPortWharfName(dmPortWharf.getPortWharfName());
							item2.setPortWharfNameVN(dmPortWharf.getPortWharfNameVN());
							item2.setPortWharfType(String.valueOf(dmPortWharf.getPortWharfType()));
							item2.setPortCode(dmPortWharf.getPortCode());
							item2.setPortRegionCode(dmPortWharf.getPortRegionCode());
							item2.setPortHarbourCode(dmPortWharf.getPortHarbourCode());
							item2.setNote(dmPortWharf.getNote());
							item2.setIsDelete(String.valueOf(dmPortWharf.getIsDelete()));
							item2.setMarkedAsDelete(String.valueOf(dmPortWharf.getMarkedAsDelete()));
							item2.setModifiedDate(FormatData.parseDateToTring(dmPortWharf.getModifiedDate()));
							item2.setRequestedDate(FormatData.parseDateToTring(dmPortWharf.getRequestedDate()));
							item2.setSyncVersion(dmPortWharf.getSyncVersion());
							
							DmPortWharfList list2 = new DmPortWharfList();
							list2.getDmPortWharf().add(item2);
							
							xmlContent2 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
									MessageFactory.convertObjectToXml2(list2));
						}
						
					} else if(obj instanceof DmHistoryMaritime) {
						DmHistoryMaritimeList.DmHistoryMaritime item = new DmHistoryMaritimeList.DmHistoryMaritime();
						
						item.setMaritimeCode(DmHistoryMaritime.class.cast(obj).getMaritimeCode());
						item.setMaritimeReference(DmHistoryMaritime.class.cast(obj).getMaritimeReference());
						item.setMaritimeOrder(String.valueOf(DmHistoryMaritime.class.cast(obj).getMaritimeOrder()));
						item.setMaritimeName(DmHistoryMaritime.class.cast(obj).getMaritimeName());
						item.setMaritimeNameVN(DmHistoryMaritime.class.cast(obj).getMaritimeNameVN());
						item.setAddress(DmHistoryMaritime.class.cast(obj).getAddress());
						item.setAddressVN(DmHistoryMaritime.class.cast(obj).getAddressVN());
						item.setStateCode(DmHistoryMaritime.class.cast(obj).getStateCode());
						item.setCityCode(DmHistoryMaritime.class.cast(obj).getCityCode());
						item.setIsDelete(String.valueOf(DmHistoryMaritime.class.cast(obj).getIsDelete()));
						item.setMarkedAsDelete(String.valueOf(DmHistoryMaritime.class.cast(obj).getMarkedAsDelete()));
						item.setModifiedDate(FormatData.parseDateToTring(DmHistoryMaritime.class.cast(obj).getModifiedDate()));
						item.setRequestedDate(FormatData.parseDateToTring(DmHistoryMaritime.class.cast(obj).getRequestedDate()));
						item.setSyncVersion(DmHistoryMaritime.class.cast(obj).getSyncVersion());
						
						DmHistoryMaritimeList list = new DmHistoryMaritimeList();
						list.getDmHistoryMaritime().add(item);
						
						xmlContent1 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
								MessageFactory.convertObjectToXml2(list));
						
						DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(DmHistoryMaritime.class.cast(obj).getMaritimeCode());
						if(dmMaritime != null) {
							DmMaritimeList.DmMaritime item2 = new DmMaritimeList.DmMaritime();
							
							item2.setMaritimeCode(dmMaritime.getMaritimeCode());
							item2.setMaritimeReference(dmMaritime.getMaritimeReference());
							item2.setMaritimeOrder(String.valueOf(dmMaritime.getMaritimeOrder()));
							item2.setMaritimeName(dmMaritime.getMaritimeName());
							item2.setMaritimeNameVN(dmMaritime.getMaritimeNameVN());
							item2.setAddress(dmMaritime.getAddress());
							item2.setAddressVN(dmMaritime.getAddressVN());
							item2.setStateCode(dmMaritime.getStateCode());
							item2.setCityCode(dmMaritime.getCityCode());
							item2.setIsDelete(String.valueOf(dmMaritime.getIsDelete()));
							item2.setMarkedAsDelete(String.valueOf(dmMaritime.getMarkedAsDelete()));
							item2.setModifiedDate(FormatData.parseDateToTring(dmMaritime.getModifiedDate()));
							item2.setRequestedDate(FormatData.parseDateToTring(dmMaritime.getRequestedDate()));
							item2.setSyncVersion(dmMaritime.getSyncVersion());
							
							DmMaritimeList list2 = new DmMaritimeList();
							list2.getDmMaritime().add(item2);
							
							xmlContent2 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
									MessageFactory.convertObjectToXml2(list2));
						}
						
					} else if(obj instanceof DmHistoryPortHarbour) {
						DmHistoryPortHarbourList.DmHistoryPortHarbour item = new DmHistoryPortHarbourList.DmHistoryPortHarbour();
						
						item.setPortHarbourCode(DmHistoryPortHarbour.class.cast(obj).getPortHarbourCode());
						item.setPortHarbourName(DmHistoryPortHarbour.class.cast(obj).getPortHarbourName());
						item.setPortHarbourNameVN(DmHistoryPortHarbour.class.cast(obj).getPortHarbourNameVN());
						item.setPortHarbourType(String.valueOf(DmHistoryPortHarbour.class.cast(obj).getPortHarbourType()));
						item.setPortRegion(DmHistoryPortHarbour.class.cast(obj).getPortRegion());
						item.setPortCode(DmHistoryPortHarbour.class.cast(obj).getPortCode());
						item.setPortRegionCode(DmHistoryPortHarbour.class.cast(obj).getPortRegionCode());
						item.setNote(DmHistoryPortHarbour.class.cast(obj).getNote());
						item.setIsDelete(String.valueOf(DmHistoryPortHarbour.class.cast(obj).getIsDelete()));
						item.setMarkedAsDelete(String.valueOf(DmHistoryPortHarbour.class.cast(obj).getMarkedAsDelete()));
						item.setModifiedDate(FormatData.parseDateToTring(DmHistoryPortHarbour.class.cast(obj).getModifiedDate()));
						item.setRequestedDate(FormatData.parseDateToTring(DmHistoryPortHarbour.class.cast(obj).getRequestedDate()));
						item.setSyncVersion(DmHistoryPortHarbour.class.cast(obj).getSyncVersion());
						
						DmHistoryPortHarbourList list = new DmHistoryPortHarbourList();
						list.getDmHistoryPortHarbour().add(item);
						
						xmlContent1 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
								MessageFactory.convertObjectToXml2(list));
						
						DmPortHarbour portHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(DmHistoryPortHarbour.class.cast(obj).getPortHarbourCode());
					
						if(portHarbour != null) {
							DmPortHarbourList.DmPortHarbour item2 = new DmPortHarbourList.DmPortHarbour();
							
							item2.setPortHarbourCode(portHarbour.getPortHarbourCode());
							item2.setPortHarbourName(portHarbour.getPortHarbourName());
							item2.setPortHarbourNameVN(portHarbour.getPortHarbourNameVN());
							item2.setPortHarbourType(String.valueOf(portHarbour.getPortHarbourType()));
							item2.setPortRegion(portHarbour.getPortRegion());
							item2.setPortCode(portHarbour.getPortCode());
							item2.setPortRegionCode(portHarbour.getPortRegionCode());
							item2.setNote(portHarbour.getNote());
							item2.setIsDelete(String.valueOf(portHarbour.getIsDelete()));
							item2.setMarkedAsDelete(String.valueOf(portHarbour.getMarkedAsDelete()));
							item2.setModifiedDate(FormatData.parseDateToTring(portHarbour.getModifiedDate()));
							item2.setRequestedDate(FormatData.parseDateToTring(portHarbour.getRequestedDate()));
							item2.setSyncVersion(portHarbour.getSyncVersion());
							
							DmPortHarbourList list2 = new DmPortHarbourList();
							list2.getDmPortHarbour().add(item2);
							
							xmlContent2 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
									MessageFactory.convertObjectToXml2(list2));
						}
					} else if(obj instanceof DmHistoryPortRegion) {
						DmHistoryPortRegionList.DmHistoryPortRegion item = new DmHistoryPortRegionList.DmHistoryPortRegion();
						
						item.setPortRegionCode(DmHistoryPortRegion.class.cast(obj).getPortRegionCode());
						item.setPortRegionName(DmHistoryPortRegion.class.cast(obj).getPortRegionName());
						item.setPortRegionNameVN(DmHistoryPortRegion.class.cast(obj).getPortRegionNameVN());
						item.setPortRegionRef(DmHistoryPortRegion.class.cast(obj).getPortRegionRef());
						item.setPortCode(DmHistoryPortRegion.class.cast(obj).getPortCode());
						item.setPortCodeRef(DmHistoryPortRegion.class.cast(obj).getPortCodeRef());
						item.setIsDelete(String.valueOf(DmHistoryPortRegion.class.cast(obj).getIsDelete()));
						item.setMarkedAsDelete(String.valueOf(DmHistoryPortRegion.class.cast(obj).getMarkedAsDelete()));
						item.setModifiedDate(FormatData.parseDateToTring(DmHistoryPortRegion.class.cast(obj).getModifiedDate()));
						item.setRequestedDate(FormatData.parseDateToTring(DmHistoryPortRegion.class.cast(obj).getRequestedDate()));
						item.setSyncVersion(DmHistoryPortRegion.class.cast(obj).getSyncVersion());
						
						DmHistoryPortRegionList list = new DmHistoryPortRegionList();
						list.getDmHistoryPortRegion().add(item);
						
						xmlContent1 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
								MessageFactory.convertObjectToXml2(list));
						
						DmPortRegion portRegion = DmPortRegionLocalServiceUtil.getByPortRegionCode(DmHistoryPortRegion.class.cast(obj).getPortRegionCode());
					
						if(portRegion != null) {
							DmPortRegionList.DmPortRegion item2 = new DmPortRegionList.DmPortRegion();
							
							item2.setPortRegionCode(portRegion.getPortRegionCode());
							item2.setPortRegionName(portRegion.getPortRegionName());
							item2.setPortRegionNameVN(portRegion.getPortRegionNameVN());
							item2.setPortRegionRef(portRegion.getPortRegionRef());
							item2.setPortCode(portRegion.getPortCode());
							item2.setPortCodeRef(portRegion.getPortCodeRef());
							item2.setIsDelete(String.valueOf(portRegion.getIsDelete()));
							item2.setMarkedAsDelete(String.valueOf(portRegion.getMarkedAsDelete()));
							item2.setModifiedDate(FormatData.parseDateToTring(portRegion.getModifiedDate()));
							item2.setRequestedDate(FormatData.parseDateToTring(portRegion.getRequestedDate()));
							item2.setSyncVersion(portRegion.getSyncVersion());
							
							DmPortRegionList list2 = new DmPortRegionList();
							list2.getDmPortRegion().add(item2);
							
							xmlContent2 = BusinessUtils.createContentSendFromBGTVTToNSW(header, 
									MessageFactory.convertObjectToXml2(list2));
						}
					}
					
					//TODO: Khong gui sang CV TPHCM va Vung TAU
					if(!locCode.equals("21") && !locCode.equals("12")) {
						
						if(xmlContent1 != null && xmlContent1.length() > 0) {
							if (locCode.equalsIgnoreCase(MessageType.BGTVT) && (obj instanceof DmHistoryPort)) {
								// Khong gui nguoc lai Cong BGTVT
							} else {
								insertMessageQueue(header, xmlContent1, locCode, webservice);
							}							
						}
						
						if(xmlContent2 != null && xmlContent2.length() > 0) {
							if (locCode.equalsIgnoreCase(MessageType.BGTVT) && (obj instanceof DmHistoryPort)) {
								// Khong gui nguoc lai Cong BGTVT
							} else {
								insertMessageQueue(header, xmlContent2, locCode, webservice);
							}
						}
					}
				}
			}
		}
	}
	
	private static void insertMessageQueue(Header header, String xmlData, String locCode, String webservice) {
		if(xmlData != null && xmlData.length() > 0) {
			BusinessUtils.insertHistory(header, xmlData, locCode, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU,
					UUID.randomUUID().toString());
			
			SendMessgaeFunctions.insertMessageQueue(header, xmlData, locCode, null, webservice);
		}
	}
	
	public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


}
