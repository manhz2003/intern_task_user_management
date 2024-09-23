package vn.gt.portlet.danhmuc;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.*;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmSyncCategoryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.TransportationMVCAction;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.portlet.phuongtien.VmaTransactionSlipUtils;
import vn.gt.utils.FormatData;

import org.json.JSONArray;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.Organization;
import com.fds.nsw.liferay.model.Role;
import com.fds.nsw.liferay.model.User;

import static com.fds.nsw.liferay.core.Constants.WAR_DANHMUCRIENG_ACTION;

@Slf4j
@Service
public class DanhMucRiengAction extends TransportationMVCAction {

	

	public JSONObject getDmSyncCategories(String categoryIdGroup, int start,
			int end) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = DmSyncCategoryLocalServiceUtil.findDmSyncCategorys(
				categoryIdGroup, start, end);
		long total = DmSyncCategoryLocalServiceUtil
				.countDmSyncCategorys(categoryIdGroup);
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	@Override
	public ResponseEntity<?> serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)  {
		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);

			HttpServletRequest request = resourceRequest;
			HttpServletRequest requestOrg = request;
			String resourceID = ParamUtil.getString(requestOrg, "p_p_resource_id", "");

			UserPort userPort = UserPortLocalServiceUtil
					.findByUserId(themeDisplay.getUserId());
			long userId = 0L;
			String portCodeDefault = StringPool.BLANK; // == maritimeCode
			if (userPort != null) {
				userId = userPort.getUserId();
				portCodeDefault = userPort.getPortCode();
			}
			UserSign userSign = UserSignLocalServiceUtil.getByUserId(userId);
			String maritimeCode = DanhMucUtils.getString(requestOrg,
					"maritimeCode").trim();
			int stt = 0;
			if (Validator.isNull(maritimeCode)) {
				stt = 1;
				maritimeCode = portCodeDefault;
			}

			int start = ParamUtil.getInteger(requestOrg, "start");
			int end = ParamUtil.getInteger(requestOrg, "end");

			if (Validator.isNull(start) || Validator.isNull(end) || end == 0) {
				start = 0;
				end = 20;
			}

			if (resourceID.equals("getURLInit")) {
				JSONObject object = JSONFactoryUtil.createJSONObject();
				User user = themeDisplay.getUser();
				boolean flagDT = false;
				boolean flagDTCam = false;

				JSONObject userObject = JSONFactoryUtil.createJSONObject();

				userObject.put("userName", user.getFullName());
				userObject.put("firstName", user.getFirstName());
				userObject.put("lastName", user.getLastName());

				userObject.put("userEmail", user.getEmailAddress());
				userObject.put("userId", user.getUserId());
				userObject.put("defaultUser", user.getDefaultUser());
				userObject.put("isKeHoach", false);
				userObject.put("isThuTuc", false);
				userObject.put("isLanhDao", false);
				userObject.put("isVanThu", false);
				userObject.put("isDTND", false);
				userObject.put("isDTNDCam", false);
				userObject.put("isBoos", false);
				userObject.put("isDongBo", false);
				userObject.put("isKeToan", false);

				UserPort portDefault = UserPortLocalServiceUtil
						.findByUserId(user.getUserId());

				if (Validator.isNotNull(portDefault)) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
							.getByMaritimeCode(portDefault.getPortCode());
					userObject.put("cangVuName", dmMaritime.getCityCode());
				} else {
					userObject.put("cangVuName", "");
				}
				List<Organization> orgs = user.getOrganizations();
				for (Organization organization : orgs) {
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"ke-hoach"))) {
						userObject.put("isKeHoach", true);
					}
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"thu-tuc"))) {
						userObject.put("isThuTuc", true);
					}
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"lanh-dao"))) {
						userObject.put("isLanhDao", true);
					}
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"van-thu"))) {
						userObject.put("isVanThu", true);
					}
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"dong-bo"))) {
						userObject.put("isDongBo", true);
					}
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"ke-toan"))) {
						userObject.put("isKeToan", true);
					}
				}
				for (Role role : user.getRoles()) {
					if (role.getName().equals("DTND_ROLE")) {
						flagDT = true;
					}
					if (role.getName().trim().equals("DTND_ROLE_CAMPUCHIA")) {
						flagDTCam = true;
					}
				}
				userObject.put("isDTND", flagDT);
				userObject.put("isDTNDCam", flagDTCam);

				object.put("user", userObject);

				// GET
				ResourceURL myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getDmSyncCategories");
				object.put("getDmSyncCategories", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getDetailDmCategories");
				object.put("getDetailDmCategories", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getMaritimes");
				object.put("getMaritimes", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getMaritimeCurrent");
				object.put("getMaritimeCurrent", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("reportExel");
				object.put("reportExel", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getDetail");
				object.put("getDetail", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("checkMST");
				object.put("checkMST", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getUsers");
				object.put("getUsers", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getVmaTransactionTypes");
				object.put("getVmaTransactionTypes", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getVmaReportCategory");
				object.put("getVmaReportCategory", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("warningPortWharf");
				object.put("warningPortWharf", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("warningTugboat");
				object.put("warningTugboat", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("warningPilot");
				object.put("warningPilot", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getVmaAccidentList");
				object.put("getVmaAccidentList", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getDetailVmaAccidentList");
				object.put("getDetailVmaAccidentList", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getVmaPilotViolations");
				object.put("getVmaPilotViolations", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getDetailVmaPilotViolation");
				object.put("getVmaPilotViolation", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getVmaAdministrativeViolations");
				object.put("getVmaAdministrativeViolations",
						myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL
						.setResourceID("getDetailVmaAdministrativeViolation");
				object.put("getDetailVmaAdministrativeViolation",
						myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getDataItems");
				object.put("getDataItems", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("excelBienLaiThuPhi");
				object.put("excelBienLaiThuPhi", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getDepartmentCodeByUserId");
				object.put("getDepartmentCodeByUserId", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getVmaHoatDongNaoVets");
				object.put("getVmaHoatDongNaoVets", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getDetailVmaHoatDongNaoVets");
				object.put("getDetailVmaHoatDongNaoVets",
						myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getVmaAnNinhCangBiens");
				object.put("getVmaAnNinhCangBiens", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getDetailVmaAnNinhCangBiens");
				object.put("getDetailVmaAnNinhCangBiens",
						myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getVmaServicePorts");
				object.put("getVmaServicePorts", myReourceURL.toString());

				myReourceURL = resourceResponse.createResourceURL(WAR_DANHMUCRIENG_ACTION);
				myReourceURL.setResourceID("getDetailVmaServicePorts");
				object.put("getDetailVmaServicePorts", myReourceURL.toString());

				// POST
				PortletURL actionUrl =  PortletURLFactoryUtil.create(request, themeDisplay.getPortletDisplay().getId(),
						themeDisplay.getPlid(), "ACTION_PHASE");
				actionUrl.setWindowState(LiferayWindowState.EXCLUSIVE);
				actionUrl.setPortletMode(LiferayPortletMode.VIEW);
				actionUrl.setParameter("javax.portlet.action",
						"actionUpdateDanhmuc");
				object.put("actionUpdateDanhmuc", actionUrl.toString());

				actionUrl
						.setParameter("javax.portlet.action", "updateUserPort");
				object.put("updateUserPort", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaAccidentList");
				object.put("updateVmaAccidentList", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaPilotViolation");
				object.put("updateVmaPilotViolation", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaAdministrativeViolation");
				object.put("updateVmaAdministrativeViolation",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaHoatDongNaoVet");
				object.put("updateVmaHoatDongNaoVet", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaAnNinhCangBien");
				object.put("updateVmaAnNinhCangBien", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaServicePort");
				object.put("updateVmaServicePort", actionUrl.toString());

				return writeJSON(resourceRequest, resourceResponse, object);
			}
			if (resourceID.equals("getDmSyncCategories")) {
				String categoryIdGroup = DanhMucUtils.getString(requestOrg,
						"categoryIdGroup");
				return writeJSON(resourceRequest, resourceResponse,
						getDmSyncCategories(categoryIdGroup, start, end));
			}
			if (resourceID.equals("getDetailDmCategories")) {
				String categoryId = DanhMucUtils.getString(requestOrg,
						"categoryId");
				String isDelete = ParamUtil.getString(requestOrg, "isDelete")
						.trim();
				
				if (categoryId.equals("DM_ROUTE")) {
					return writeJSON(resourceRequest, resourceResponse,
							DmRouteUtils.getRoutes(start, end));
				}
				if (categoryId.equals("DM_VRCODE")) {
					String shipBoat = ParamUtil.getString(requestOrg,
							"shipBoat");
					if (Validator.isNotNull(shipBoat)) {
						return writeJSON(resourceRequest, resourceResponse,
								DmVRCodeUtils.getVRCodes(shipBoat, start, end));
					} else {
						return writeJSON(resourceRequest, resourceResponse,
								DmVRCodeUtils.getAllVRCodes(start, end));
					}
					
				}
				if (categoryId.equals("DM_GOODS_TYPE")) {
					String goodsTypeNameVN = DanhMucUtils.getString(requestOrg,
							"goodsTypeNameVN").trim();
					String goodsTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "goodsTypeCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmGoodTypeUtils.getGoodsTypes(goodsTypeNameVN,
									isDelete, goodsTypeCodeGroup, start, end));
				}
				if (categoryId.equals("DM_GOODS")) {
					String goodsItemName = DanhMucUtils.getString(requestOrg,
							"goodsItemName").trim();
					String goodsItemCodeGroup = DanhMucUtils.getString(
							requestOrg, "goodsItemCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmGoodUtils.getGoods(goodsItemName, isDelete,
									goodsItemCodeGroup, start, end));
				}
				if (categoryId.equals("DM_PACKAGE")) {
					String packageNameVN = DanhMucUtils.getString(requestOrg,
							"packageNameVN").trim();
					String packageCode = DanhMucUtils.getString(requestOrg,
							"packageCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmPackageUtils.getPackgages(packageCode,
									packageNameVN, isDelete, start, end));
				}
				if (categoryId.equals("DM_RANK_RATING")) {
					String rankNameVN = DanhMucUtils.getString(requestOrg,
							"rankNameVN").trim();
					String rankName = DanhMucUtils.getString(requestOrg,
							"rankName").trim();
					String rankCodeGroup = DanhMucUtils.getString(requestOrg,
							"rankCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmRankRatingUtils.getRankRatings(rankNameVN,
									rankName, isDelete, rankCodeGroup, start,
									end));
				}
				if (categoryId.equals("DM_REPRESENTATIVE")) {
					String repNameVN = DanhMucUtils.getString(requestOrg,
							"repNameVN").trim();
					int repLevel = ParamUtil.getInteger(requestOrg, "repLevel");
					String repCodeGroup = DanhMucUtils.getString(requestOrg,
							"repCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmRepresentativeUtils.getRepresentatives(repNameVN,
									repLevel, maritimeCode, isDelete,
									repCodeGroup, start, end));
				}
				if (categoryId.equals("DM_UNIT_GENERAL")) {
					String unitName = DanhMucUtils.getString(requestOrg,
							"unitName").trim();
					String unitCodeGroup = DanhMucUtils.getString(requestOrg,
							"unitCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmUnitGeneralUtils.getUnitGenerals(unitName,
									isDelete, unitCodeGroup, start, end));
				}
				if (categoryId.equals("DM_DATAITEM_GROUP124")) {
					String name = DanhMucUtils.getString(requestOrg, "name")
							.trim();
					String codeGroup = DanhMucUtils.getString(requestOrg,
							"code");
					String status = DanhMucUtils
							.getString(requestOrg, "status").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmDataItemUtils.getDataItemGroup124(name, status,
									codeGroup, start, end));
				}
				if (categoryId.equals("DM_STATE")) {
					String stateName = DanhMucUtils.getString(requestOrg,
							"stateName").trim();
					String stateCodeGroup = DanhMucUtils.getString(requestOrg,
							"stateCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmStateUtils.getStates(stateName, isDelete,
									stateCodeGroup, start, end));
				}
				if (categoryId.equals("DM_PORT")) {
					String portName = DanhMucUtils.getString(requestOrg,
							"portName").trim();
					String portCodeGroup = DanhMucUtils.getString(requestOrg,
							"portCode");
					// add by TrungNT
					String portType = DanhMucUtils.getString(requestOrg,
							"portType");
					String stateCode = ParamUtil.getString(requestOrg,
							"stateCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmPortUtils.getPorts(portName, isDelete,
									portCodeGroup, portType, stateCode, start,
									end));
				}
				if (categoryId.equals("DM_ARRIVAL_PURPOSE")) {
					String purposeName = DanhMucUtils.getString(requestOrg,
							"purposeName").trim();
					String purposeCodeGroup = DanhMucUtils.getString(
							requestOrg, "purposeCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmArrivalPurposeUtils.getArrivalPurposes(
									purposeName, isDelete, purposeCodeGroup,
									start, end));
				}
				if (categoryId.equals("DM_SHIP_AGENCY")) {
					String shipAgencyNameVN = DanhMucUtils.getString(
							requestOrg, "shipAgencyNameVN").trim();
					String taxCode = DanhMucUtils.getString(requestOrg,
							"taxCode").trim();
					String fax = DanhMucUtils.getString(requestOrg, "fax")
							.trim();
					String addressVN = DanhMucUtils.getString(requestOrg,
							"addressVN").trim();
					String shipAgencyCodeGroup = DanhMucUtils.getString(
							requestOrg, "shipAgencyCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmShipAgencyUtils.getShipAgencys(shipAgencyNameVN,
									addressVN, fax, taxCode, isDelete,
									shipAgencyCodeGroup, start, end));
				}
				if (categoryId.equals("DM_SHIP_TYPE")) {
					String shipTypeNameVN = DanhMucUtils.getString(requestOrg,
							"shipTypeNameVN").trim();
					String shipTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "shipTypeCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmShipTypeUtils.getShipTypes(shipTypeNameVN,
									isDelete, shipTypeCodeGroup, start, end));
				}
				if (categoryId.equals("DM_DOC_TYPE")) {
					String documentTypeName = DanhMucUtils.getString(
							requestOrg, "documentTypeName").trim();
					String documentTypeCode = DanhMucUtils.getString(
							requestOrg, "documentTypeCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmDocTypeUtils.getDocTypes(documentTypeName,
									isDelete, documentTypeCode, start, end));
				}
				if (categoryId.equals("DM_PASSPORT_TYPE")) {
					String passportTypeNameVN = DanhMucUtils.getString(
							requestOrg, "passportTypeNameVN").trim();
					String passportTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "passportTypeCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmPassportTypeUtils.getPassPorts(
									passportTypeNameVN, isDelete,
									passportTypeCodeGroup, start, end));
				}
				if (categoryId.equals("DM_SECURITY_LEVEL")) {
					return writeJSON(resourceRequest, resourceResponse,
							DmSecurityLevelUtils.getSecurityLevels(start, end));
				}
				if (categoryId.equals("DM_PORT_REGION")) {
					String portRegionNameVN = DanhMucUtils.getString(
							requestOrg, "portRegionNameVN").trim();
					String portCode = DanhMucUtils.getString(requestOrg,
							"portCode").trim();
					String portRegionCodeGroup = DanhMucUtils.getString(
							requestOrg, "portRegionCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmPortRegionUtils.getPortRegions(userId,
									maritimeCode, portRegionNameVN, portCode,
									isDelete, portRegionCodeGroup, start, end));
				}
				if (categoryId.equals("DM_PORT_WHARF")) {
					String holdPortRegionCode = DanhMucUtils.getString(
							requestOrg, "holdPortRegionCode").trim();
					String holdPortHarbourCode = DanhMucUtils.getString(
							requestOrg, "holdPortHarbourCode").trim();
					String portWharfNameVN = DanhMucUtils.getString(requestOrg,
							"portWharfNameVN").trim();
					String portWharfCodeGroup = DanhMucUtils.getString(
							requestOrg, "portWharfCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmPortWharfUtils.getPortWharfs(userId,
									maritimeCode, holdPortRegionCode,
									holdPortHarbourCode, portWharfNameVN,
									isDelete, portWharfCodeGroup, start, end));
				}
				if (categoryId.equals("DM_PORT_HARBOUR")) {
					String portRegion = DanhMucUtils.getString(requestOrg,
							"portRegion").trim();
					String portHarbourName = DanhMucUtils.getString(requestOrg,
							"portHarbourName").trim();
					String portHarbourCodeGroup = DanhMucUtils.getString(
							requestOrg, "portHarbourCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmPortHarbourUtils.getPortHarbours(userId,
									maritimeCode, portRegion, portHarbourName,
									isDelete, portHarbourCodeGroup, start, end));
				}
				if (categoryId.equals("DM_VMA_TUGBOAT_COMPANY")) {
					String tugboatCompanyName = DanhMucUtils.getString(
							requestOrg, "tugboatCompanyName").trim();
					String companyAddress = DanhMucUtils.getString(requestOrg,
							"companyAddress").trim();
					String contactEmail = DanhMucUtils.getString(requestOrg,
							"contactEmail").trim();
					String telNo = DanhMucUtils.getString(requestOrg, "telNo");
					String tugboatCompanyCodeGroup = DanhMucUtils.getString(
							requestOrg, "tugboatCompanyCode");
					return writeJSON(resourceRequest, resourceResponse,
							VmaTugboatCompanyUtils.getVmaTugboatCompanys(
									userId, maritimeCode, tugboatCompanyName,
									companyAddress, contactEmail, telNo,
									isDelete, tugboatCompanyCodeGroup, start,
									end));
				}
				if (categoryId.equals("DM_VMA_TUGBOAT")) {
					String _shipName = DanhMucUtils.getString(requestOrg,
							"_shipName").trim();
					String shipCodeGroup = DanhMucUtils.getString(requestOrg,
							"shipCode");
					String tugboatCompanyCode = DanhMucUtils.getString(
							requestOrg, "tugboatCompanyCode").trim();
					int power1 = ParamUtil.getInteger(requestOrg, "power1");
					int power2 = ParamUtil.getInteger(requestOrg, "power2");
					return writeJSON(resourceRequest, resourceResponse,
							VmaTugboatUtils.getVmaTugboats(userId,
									maritimeCode, _shipName, power1, power2,
									tugboatCompanyCode, isDelete,
									shipCodeGroup, start, end));
				}
				if (categoryId.equals("DM_VMA_SHIP_OWNER")) {
					String taxCode = DanhMucUtils.getString(requestOrg,
							"taxCode").trim();
					String companyName = DanhMucUtils.getString(requestOrg,
							"companyName").trim();
					String companyAddress = DanhMucUtils.getString(requestOrg,
							"companyAddress").trim();
					String contactEmail = DanhMucUtils.getString(requestOrg,
							"contactEmail").trim();
					String telNo = DanhMucUtils.getString(requestOrg, "telNo")
							.trim();
					String isShipOwner = DanhMucUtils.getString(requestOrg,
							"isShipOwner");
					String isShipOperator = DanhMucUtils.getString(requestOrg,
							"isShipOperator");
					String shipOwnerCodeGroup = DanhMucUtils.getString(
							requestOrg, "shipOwnerCode");
					int isOther = ParamUtil
							.getInteger(requestOrg, "isOther", 0);
					return writeJSON(resourceRequest, resourceResponse,
							VmaShipOwnerUtils.getVmaShipOwners(userId,
									maritimeCode, taxCode, companyName,
									companyAddress, contactEmail, telNo,
									isShipOwner, isShipOperator, isDelete,
									shipOwnerCodeGroup, isOther, start, end));
				}
				if (categoryId.equals("DM_VMA_PILOT_CATEGORY")) {
					String pilotCategoryName = DanhMucUtils.getString(
							requestOrg, "pilotCategoryName").trim();
					String pilotCategoryCodeGroup = DanhMucUtils.getString(
							requestOrg, "pilotCategoryCode");
					return writeJSON(resourceRequest, resourceResponse,
							VmaPilotCategoryUtils.getVmaPilotCategorys(
									pilotCategoryName, isDelete,
									pilotCategoryCodeGroup, start, end));
				}
				if (categoryId.equals("DM_VMA_PILOT")) {
					String pilotCompanyCode = DanhMucUtils.getString(
							requestOrg, "pilotCompanyCode").trim();
					String pilotCategoryCode = DanhMucUtils.getString(
							requestOrg, "pilotCategoryCode").trim();
					String pilotName = DanhMucUtils.getString(requestOrg,
							"pilotName").trim();
					String pilotCodeGroup = DanhMucUtils.getString(requestOrg,
							"pilotCode");
					return writeJSON(resourceRequest, resourceResponse,
							VmaPilotUtils.getVmaPilots(userId, maritimeCode,
									pilotCompanyCode, pilotCategoryCode,
									pilotName, isDelete, pilotCodeGroup, start,
									end));
				}
				if (categoryId.equals("DM_VMA_PILOT_COMPANY")) {
					String pilotCompanyName = DanhMucUtils.getString(
							requestOrg, "pilotCompanyName").trim();
					String companyAddress = DanhMucUtils.getString(requestOrg,
							"companyAddress").trim();
					String contactEmail = DanhMucUtils.getString(requestOrg,
							"contactEmail").trim();
					String pilotCompanyCodeGroup = DanhMucUtils.getString(
							requestOrg, "pilotCompanyCode");
					String telNo = DanhMucUtils.getString(requestOrg, "telNo");
					return writeJSON(resourceRequest, resourceResponse,
							VmaPilotCompanyUtils
									.getVmaPilotCompanys(userId, maritimeCode,
											pilotCompanyName, companyAddress,
											contactEmail, telNo, isDelete,
											pilotCompanyCodeGroup, start, end));
				}
				if (categoryId.equals("DM_CARGO_ON_BOARD")) {
					String goodsTypeNameVN = DanhMucUtils.getString(requestOrg,
							"goodsTypeNameVN");
					String goodsTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "goodsTypeCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmCargoOnBoardUtils.getCargoOnBoards(
									goodsTypeNameVN, isDelete,
									goodsTypeCodeGroup, start, end));

				}
				if (categoryId.equals("DM_MARITIME")) {
					return writeJSON(resourceRequest, resourceResponse,
							DmMaritimeUtils.getMaritimes(userId, maritimeCode,
									isDelete, start, end));
				}
				if (categoryId.equals("DM_DATAITEM_GROUP107")) {
					String status = DanhMucUtils
							.getString(requestOrg, "status").trim();
					String codeGroup = DanhMucUtils.getString(requestOrg,
							"code");
					return writeJSON(resourceRequest, resourceResponse,
							DmDataItemUtils
									.getDataItemGroup107(userId, maritimeCode,
											status, codeGroup, start, end));
				}
				if (categoryId.equals("DM_TUYEN_LUONG")) {
					String name = DanhMucUtils.getString(requestOrg, "name")
							.trim();
					String codeGroup = DanhMucUtils.getString(requestOrg,
							"code");
					String status = DanhMucUtils
							.getString(requestOrg, "status").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmDataItemUtils
									.getDataItems(130, name, userId,
											maritimeCode, status, codeGroup,
											start, end));
				}
				if (categoryId.equals("DM_VMA_SHIPYARD")) {
					String companyName = DanhMucUtils.getString(requestOrg,
							"companyName").trim();
					String companyAddress = DanhMucUtils.getString(requestOrg,
							"companyAddress").trim();
					String contactEmail = DanhMucUtils.getString(requestOrg,
							"contactEmail").trim();
					String telNo = DanhMucUtils.getString(requestOrg, "telNo")
							.trim();
					String taxCode = DanhMucUtils.getString(requestOrg,
							"taxCode").trim();
					String shipYardCodeGroup = DanhMucUtils.getString(
							requestOrg, "shipYardCode");
					return writeJSON(resourceRequest, resourceResponse,
							VmaShipyardUtils.getVmaShipYards(userId,
									maritimeCode, companyName, companyAddress,
									contactEmail, telNo, taxCode, isDelete,
									shipYardCodeGroup, start, end));
				}
				if (categoryId.equals("DM_VMA_SECURITY_OFFICE")) {
					String companyName = DanhMucUtils.getString(requestOrg,
							"companyName").trim();
					String companyAddress = DanhMucUtils.getString(requestOrg,
							"companyAddress").trim();
					String contactEmail = DanhMucUtils.getString(requestOrg,
							"contactEmail").trim();
					String telNo = DanhMucUtils.getString(requestOrg, "telNo")
							.trim();
					String securityOfficeCodeGroup = DanhMucUtils.getString(
							requestOrg, "securityOfficeCode");
					return writeJSON(resourceRequest, resourceResponse,
							VmaSecurityOfficeUtils.getVmaSecurityOffices(
									userId, maritimeCode, companyName,
									companyAddress, contactEmail, telNo,
									isDelete, securityOfficeCodeGroup, start,
									end));
				}
				if (categoryId.equals("DM_VMA_SHIP_TYPE")) {
					String shipTypeName = DanhMucUtils.getString(requestOrg,
							"shipTypeName").trim();
					String applyShip = DanhMucUtils.getString(requestOrg,
							"applyShip");
					String applyBoat = DanhMucUtils.getString(requestOrg,
							"applyBoat");
					String shipTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "shipTypeCode");
					return writeJSON(resourceRequest, resourceResponse,
							VmaShipTypeUtils.getVmaShipTypes(shipTypeName,
									applyShip, applyBoat, isDelete,
									shipTypeCodeGroup, start, end));
				}
				if (categoryId.equals("VMA_TRANSACTION_DEPARTMENT")) {
					String portOfAuthority = DanhMucUtils.getString(requestOrg,
							"portOfAuthority");
					String departmentName = DanhMucUtils.getString(requestOrg,
							"departmentName");
					return writeJSON(resourceRequest, resourceResponse,
							VmaTransactionDepartmentUtils
									.getVmaTransactionDepartments(userId,
											portOfAuthority, departmentName,
											start, end));
				}
			}
			if (resourceID.equals("getVmaTransactionTypes")) {
				String portOfAuthority = DanhMucUtils.getString(requestOrg,
						"portOfAuthority");
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionTypeUtils
								.getVmaTransactionTypes(portOfAuthority));
			}
			if (resourceID.equals("getUsers")) {
				String departmentCode = DanhMucUtils.getString(requestOrg,
						"departmentCode");
				return writeJSON(resourceRequest, resourceResponse,
						UserPortUtils.getUserPorts(departmentCode, start, end));
			}
			if (resourceID.equals("getMaritimeCurrent")) {
				return writeJSON(resourceRequest, resourceResponse,
						DanhMucUtils.getMaritimeCurrent(userId));
			}
			if (resourceID.equals("reportExel")) {
				String reportId = DanhMucUtils
						.getString(requestOrg, "reportId").trim();
				String isDelete = DanhMucUtils
						.getString(requestOrg, "isDelete").trim();
				String nameExcel = "";
				HSSFWorkbook workbook = new HSSFWorkbook();
				if (reportId.equals("DM_PORT_REGION")) {
					String portRegionNameVN = DanhMucUtils.getString(request,
							"portRegionNameVN");
					String portRegionCodeGroup = DanhMucUtils.getString(
							request, "portRegionCode");
					String portCode = DanhMucUtils.getString(request,
							"portCode");
					String[] headers = new String[] { "Mã khu vực hàng hải",
							"Tên khu vực hàng hải", "Cảng biển hàng hải",
							"Tỉnh/Thành phố", "Trạng thái" };
					String[] keys = new String[] { "portRegionCode",
							"portRegionNameVN", "portCodeCB", "cityCode",
							"isDelete" };
					nameExcel = "Bảng danh mục danh sách khu vực hàng hải";
					workbook = DanhMucUtils.reportExcel(DmPortRegionUtils
							.getPortRegions(userId, maritimeCode,
									portRegionNameVN, portCode, isDelete,
									portRegionCodeGroup, -1, -1), headers,
							keys, nameExcel);
				} else if (reportId.equals("DM_PORT_WHARF")) {
					String holdPortRegionCode = DanhMucUtils.getString(request,
							"holdPortRegionCode");
					String portWharfNameVN = DanhMucUtils.getString(request,
							"portWharfNameVN");
					String portWharfCodeGroup = DanhMucUtils.getString(request,
							"portWharfCodeGroup");
					String holdPortHarbourCode = DanhMucUtils.getString(
							request, "holdPortHarbourCode");
					String[] headers = new String[] { "Mã cầu cảng",
							"Tên cầu cảng", "Mã bến cảng", "Bến cảng",
							"Mã khu vực hàng hải", "Khu vực hàng hải",
							"Ghi chú", "Trạng thái" };
					String[] keys = new String[] { "portWharfCode",
							"portWharfNameVN", "portHarbourCode", "portCodeBC",
							"portRegionCode", "portRegionNameVN", "node",
							"isDelete" };
					nameExcel = "Bảng danh mục danh sách cầu cảng";
					workbook = DanhMucUtils.reportExcel(DmPortWharfUtils
							.getPortWharfs(userId, maritimeCode,
									holdPortRegionCode, holdPortHarbourCode,
									portWharfNameVN, isDelete,
									portWharfCodeGroup, -1, -1), headers, keys,
							nameExcel);
				} else if (reportId.equals("DM_PORT_HARBOUR")) {
					String portRegion = DanhMucUtils.getString(request,
							"portRegion");
					String portHarbourName = DanhMucUtils.getString(request,
							"portHarbourName").trim();
					String portHarbourCodeGroup = DanhMucUtils.getString(
							requestOrg, "portHarbourCode").trim();
					nameExcel = "Bảng danh mục danh sách bến cảng";
					String[] headers = new String[] { "Mã cầu cảng",
							"Tên cầu cảng", "Cảng biển hàng hải",
							"Khu vực hàng hải", "Trạng thái" };
					String[] keys = new String[] { "portHarbourCode",
							"portHarbourNameVN", "portCodeCB",
							"portRegionNameVN", "isDelete" };
					workbook = DanhMucUtils.reportExcel(DmPortHarbourUtils
							.getPortHarbours(userId, maritimeCode, portRegion,
									portHarbourName, isDelete,
									portHarbourCodeGroup, -1, -1), headers,
							keys, nameExcel);
				} else if (reportId.equals("DM_SHIP_TYPE")) {
					String shipTypeNameVN = DanhMucUtils.getString(requestOrg,
							"shipTypeNameVN").trim();
					String shipTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "shipTypeCode");
					String headers[] = new String[] { "Mã tàu thuyền",
							"Tên tàu thuyền", "Trạng thái" };
					String keys[] = new String[] { "shipTypeCode",
							"shipTypeNameVN", "isDelete" };
					nameExcel = "Bảng danh mục danh sách tàu thuyền";
					workbook = DanhMucUtils.reportExcel(DmShipTypeUtils
							.getShipTypes(shipTypeNameVN, isDelete,
									shipTypeCodeGroup, -1, -1), headers, keys,
							nameExcel);
				} else if (reportId.equals("DM_GOODS_TYPE")) {
					String goodsTypeNameVN = DanhMucUtils.getString(request,
							"goodsTypeNameVN");
					String goodsTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "goodsTypeCode");
					String headers[] = new String[] { "Mã loại hàng hóa",
							"Tên loại hàng hóa(tiếng Anh)",
							"Tên loại hàng hóa", "Trạng thái", };
					String keys[] = new String[] { "stateCode", "stateName",
							"stateNameVN", "isDelete" };
					nameExcel = "Bảng danh mục loại hàng hóa";
					workbook = DanhMucUtils.reportExcel(DmGoodTypeUtils
							.getGoodsTypes(goodsTypeNameVN, isDelete,
									goodsTypeCodeGroup, -1, -1), headers, keys,
							nameExcel);
				} else if (reportId.equals("DM_STATE")) {
					String stateName = DanhMucUtils.getString(requestOrg,
							"stateName");
					String stateCodeGroup = DanhMucUtils.getString(requestOrg,
							"stateCode").trim();
					String[] headers = new String[] { "Mã quốc gia",
							"Tên quốc gia", "Mô tả", "Trạng thái" };
					String[] keys = new String[] { "stateCode", "stateName",
							"description", "isDelete" };
					nameExcel = "Bảng danh mục danh sách quốc gia";
					workbook = DanhMucUtils.reportExcel(DmStateUtils.getStates(
							stateName, isDelete, stateCodeGroup, -1, -1),
							headers, keys, nameExcel);
				} else if (reportId.equals("DM_PORT")) {
					String portName = DanhMucUtils.getString(requestOrg,
							"portName").trim();
					String portCodeGroup = DanhMucUtils.getString(requestOrg,
							"portCode");
					// add by TrungNT
					String portType = DanhMucUtils.getString(requestOrg,
							"portType");
					String stateCode = ParamUtil.getString(requestOrg,
							"stateCode");
					String headers[] = new String[] { "Mã cảng biển",
							"Tên cảng biển", "Địa chỉ", "Tỉnh/Thành phố",
							"Trạng thái" };
					String keys[] = new String[] { "portCode", "portName",
							"addressVN", "cityCode", "isDelete" };
					nameExcel = "Bảng danh mục danh sách cảng biển";

					workbook = DanhMucUtils.reportExcel(DmPortUtils.getPorts(
							portName, isDelete, portCodeGroup, portType,
							stateCode, -1, -1), headers, keys, nameExcel);
				} else if (reportId.equals("DM_ARRIVAL_PURPOSE")) {
					String purposeName = DanhMucUtils.getString(requestOrg,
							"purposeName").trim();
					String purposeCodeGroup = DanhMucUtils.getString(
							requestOrg, "purposeCode");
					String headers[] = new String[] { "Mã mục đích đến cảng",
							"Tên mục đích đến cảng", "Trạng thái" };
					String keys[] = new String[] { "purposeCode",
							"purposeName", "isDelete" };
					nameExcel = "Bảng danh mục danh sách mục đích đến cảng";
					workbook = DanhMucUtils.reportExcel(DmArrivalPurposeUtils
							.getArrivalPurposes(purposeName, isDelete,
									purposeCodeGroup, -1, -1), headers, keys,
							nameExcel);
				} else if (reportId.equals("DM_SHIP_AGENCY")) {
					String shipAgencyNameVN = DanhMucUtils.getString(
							requestOrg, "shipAgencyNameVN").trim();
					String addressVN = DanhMucUtils.getString(requestOrg,
							"addressVN").trim();
					String fax = DanhMucUtils.getString(requestOrg, "fax")
							.trim();
					String taxCode = DanhMucUtils.getString(requestOrg,
							"taxCode").trim();
					String shipAgencyCodeGroup = DanhMucUtils.getString(
							requestOrg, "shipAgencyCode").trim();
					String headers[] = new String[] { "Mã đại lý",
							"Tên dại lý", "Mã số thuế", "Địa chỉ",
							"Số điện thoại", "Email", "Tỉnh/Thành phố",
							"Mã quốc gia", "Trạng thái" };
					String keys[] = new String[] { "shipAgencyCode",
							"shipAgencyNameVN", "taxCode", "addressVN", "fax",
							"email", "cityCode", "stateCode", "isDelete" };
					nameExcel = "Bảng danh mục danh sách đại lý";
					workbook = DanhMucUtils.reportExcel(DmShipAgencyUtils
							.getShipAgencys(shipAgencyNameVN, addressVN, fax,
									taxCode, isDelete, shipAgencyCodeGroup, -1,
									-1), headers, keys, nameExcel);
				} else if (reportId.equals("DM_MARITIME")) {
					String headers[] = new String[] { "Mã cảng vụ", "Ký hiệu",
							"Tên cảng vụ", "Tên cảng vụ(tiếng Anh)",
							"Trạng thái" };
					String keys[] = new String[] { "maritimeCode",
							"maritimeReference", "maritimeNameVN",
							"maritimeName", "isDelete" };
					nameExcel = "Bảng danh mục cảng vụ hàng hải";
					workbook = DanhMucUtils.reportExcel(DmMaritimeUtils
							.getMaritimes(userId, maritimeCode, isDelete, -1,
									-1), headers, keys, nameExcel);
				} else if (reportId.equals("DM_DATAITEM_GROUP107")) {
					String status = DanhMucUtils
							.getString(requestOrg, "status").trim();
					String codeGroup = DanhMucUtils.getString(requestOrg,
							"code");
					String headers[] = new String[] { "Mã cảng biển",
							"Tên cảng biển", "Cảng vụ hàng hải quản lý",
							"Trạng thái" };
					String keys[] = new String[] { "code", "name",
							"maritimeNameVN", "status" };
					nameExcel = "Bảng danh mục cảng biển hàng hải";
					workbook = DanhMucUtils.reportExcel(DmDataItemUtils
							.getDataItemGroup107(userId, maritimeCode, status,
									codeGroup, -1, -1), headers, keys,
							nameExcel);
				} else if (reportId.equals("DM_DOC_TYPE")) {
					String documentTypeName = DanhMucUtils.getString(
							requestOrg, "documentTypeName").trim();
					String documentTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "documentTypeCode");
					String headers[] = new String[] { "Loại hồ sơ", "Mã hồ sơ",
							"Tên hồ sơ", "Trạng thái" };
					String keys[] = new String[] { "documentType",
							"documentCode", "documentName", "isDelete" };
					nameExcel = "Bảng danh mục danh sách hồ sơ";
					workbook = DanhMucUtils.reportExcel(DmDocTypeUtils
							.getDocTypes(documentTypeName, isDelete,
									documentTypeCodeGroup, -1, -1), headers,
							keys, nameExcel);
				} else if (reportId.equals("DM_PASSPORT_TYPE")) {
					String passportTypeNameVN = DanhMucUtils.getString(
							requestOrg, "passportTypeNameVN").trim();
					String passportTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "passportTypeCode");
					String headers[] = new String[] { "Mã loại hộ chiếu",
							"Mã hộ chiếu", "Tên hộ chiếu", "Trạng thái" };
					String keys[] = new String[] { "passportType",
							"passportTypeCode", "passportTypeNameVN",
							"isDelete" };
					nameExcel = "Bảng danh mục danh sách loại hộ chiếu";
					workbook = DanhMucUtils.reportExcel(DmPassportTypeUtils
							.getPassPorts(passportTypeNameVN, isDelete,
									passportTypeCodeGroup, -1, -1), headers,
							keys, nameExcel);
				} else if (reportId.equals("DM_SECURITY_LEVEL")) {
					String headers[] = new String[] { "Cấp độ an ninh",
							"Mã cấp độ", "Tên cấp độ", "Trạng thái" };
					String keys[] = new String[] { "securityLevel",
							"securityLevelCode", "securityLevelName",
							"isDelete" };
					nameExcel = "Bảng danh mục danh sách cấp độ an ninh";
					workbook = DanhMucUtils.reportExcel(
							DmSecurityLevelUtils.getSecurityLevels(-1, -1),
							headers, keys, nameExcel);
				} else if (reportId.equals("DM_GOODS")) {
					String goodsItemName = DanhMucUtils.getString(requestOrg,
							"goodsItemName").trim();
					String goodsItemCodeGroup = DanhMucUtils.getString(
							requestOrg, "goodsItemCode");
					String headers[] = new String[] { "Mã hàng hóa",
							"Tên hàng hóa(tiếng Anh)", "Tên hàng hóa",
							"Trạng thái" };
					String keys[] = new String[] { "goodsItemCode",
							"goodsItemName", "goodsItemNameVN", "isDelete" };
					nameExcel = "Bảng danh mục danh sách hàng hóa";
					workbook = DanhMucUtils.reportExcel(DmGoodUtils
							.getGoods(goodsItemName, isDelete,
									goodsItemCodeGroup, -1, -1), headers, keys,
							nameExcel);
				} else if (reportId.equals("DM_PACKAGE")) {
					String packageCode = DanhMucUtils.getString(requestOrg,
							"packgageCode").trim();
					String packageNameVN = DanhMucUtils.getString(requestOrg,
							"packgageNameVN").trim();
					String headers[] = new String[] { "Mã loại bao kiện",
							"Tên loại bao kiện", "Trạng thái" };
					String keys[] = new String[] { "code", "name", "isDelete" };
					nameExcel = "Bảng danh mục danh sách loại bao kiện";
					workbook = DanhMucUtils.reportExcel(DmPackageUtils
							.getPackgages(packageCode, packageNameVN, isDelete,
									-1, -1), headers, keys, nameExcel);
				} else if (reportId.equals("DM_RANK_RATING")) {
					String rankNameVN = DanhMucUtils.getString(requestOrg,
							"rankNameVN").trim();
					String rankName = DanhMucUtils.getString(requestOrg,
							"rankName").trim();
					String rankCodeGroup = DanhMucUtils.getString(requestOrg,
							"rankCode");
					String headers[] = new String[] { "Mã chức danh",
							"Tên chức danh(tiếng Anh)", "Tên chức danh",
							"Trạng thái" };
					String keys[] = new String[] { "rankCode", "rankName",
							"rankNameVN", "isDelete" };
					nameExcel = "Bảng danh mục danh sách chức danh thuyền viên";
					workbook = DanhMucUtils.reportExcel(DmRankRatingUtils
							.getRankRatings(rankNameVN, rankName, isDelete,
									rankCodeGroup, -1, -1), headers, keys,
							nameExcel);
				} else if (reportId.equals("DM_CARGO_ON_BOARD")) {
					String goodsTypeNameVN = DanhMucUtils.getString(requestOrg,
							"goodsTypeNameVN").trim();
					String goodsTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "goodsTypeCode");
					String headers[] = new String[] { "Mã hàng hóa",
							"Tên hàng hóa(tiếng Anh)", "Tên hàng hóa",
							"Trạng thái" };
					String keys[] = new String[] { "goodsTypeCode",
							"goodsTypeName", "goodsTypeNameVN", "isDelete" };
					nameExcel = "Bảng danh mục danh sách loại hàng hóa xếp dỡ";
					workbook = DanhMucUtils.reportExcel(DmCargoOnBoardUtils
							.getCargoOnBoards(goodsTypeNameVN, isDelete,
									goodsTypeCodeGroup, -1, -1), headers, keys,
							nameExcel);
				} else if (reportId.equals("DM_REPRESENTATIVE")) {
					String repNameVN = DanhMucUtils.getString(requestOrg,
							"repNameVN").trim();
					String repCodeGroup = DanhMucUtils.getString(requestOrg,
							"repCode");
					int repLevel = ParamUtil.getInteger(requestOrg, "repLevel");
					String headers[] = new String[] { "Mã đại diện", "Chức vụ",
							"Tên đại diện(tiếng Anh)", "Tên đại diện",
							"Cảng vụ", "Trạng thái" };
					String keys[] = new String[] { "repCode", "repLevel",
							"repName", "repNameVN", "maritimeNameVN",
							"isDelete" };
					nameExcel = "Bảng danh mục danh sách đại diện cảng vụ";
					workbook = DanhMucUtils.reportExcel(DmRepresentativeUtils
							.getRepresentatives(repNameVN, repLevel,
									maritimeCode, isDelete, repCodeGroup, -1,
									-1), headers, keys, nameExcel);
				} else if (reportId.equals("DM_UNIT_GENERAL")) {
					String unitName = DanhMucUtils.getString(requestOrg,
							"unitName").trim();
					String unitCodeGroup = DanhMucUtils.getString(requestOrg,
							"unitCode");
					String headers[] = new String[] { "Mã đơn vị tính",
							"Tên đơn vị tính", "Trạng thái" };
					String keys[] = new String[] { "unitCode", "unitName",
							"isDelete" };
					nameExcel = "Bảng danh mục danh sách đơn vị tính";
					workbook = DanhMucUtils.reportExcel(DmUnitGeneralUtils
							.getUnitGenerals(unitName, isDelete, unitCodeGroup,
									-1, -1), headers, keys, nameExcel);
				} else if (reportId.equals("DM_DATAITEM_GROUP124")) {
					String name = DanhMucUtils.getString(requestOrg, "name")
							.trim();
					String codeGroup = DanhMucUtils.getString(requestOrg,
							"code");
					String headers[] = new String[] { "Mã hàng hóa",
							"Tên hàng hóa", "Trạng thái" };
					String keys[] = new String[] { "code0", "name", "status" };
					nameExcel = "Bảng dang mục danh sách tên hàng hóa vận chuyển đường thủy nội địa";
					workbook = DanhMucUtils.reportExcel(DmDataItemUtils
							.getDataItemGroup124(name, isDelete, codeGroup, -1,
									-1), headers, keys, nameExcel);
				} else if (reportId.equals("DM_TUYEN_LUONG")) {
					String name = DanhMucUtils.getString(requestOrg, "name")
							.trim();
					String codeGroup = DanhMucUtils.getString(requestOrg,
							"code");
					String status = DanhMucUtils
							.getString(requestOrg, "status").trim();
					String headers[] = new String[] { "Mã tuyến luồng",
							"Tên tuyến luồng", "Tên cảng vụ", "Trạng thái" };
					String keys[] = new String[] { "code", "name",
							"maritimeNameVN", "status" };
					nameExcel = "Bảng danh mục danh sách tuyến luồng";
					workbook = DanhMucUtils.reportExcel(DmDataItemUtils
							.getDataItems(130, name, userId, maritimeCode,
									status, codeGroup, -1, -1), headers, keys,
							nameExcel);
				} else if (reportId.equals("DM_VMA_TUGBOAT_COMPANY")) {
					String tugboatCompanyName = DanhMucUtils.getString(
							requestOrg, "tugboatCompanyName").trim();
					String companyAddress = DanhMucUtils.getString(requestOrg,
							"companyAddress").trim();
					String contactEmail = DanhMucUtils.getString(requestOrg,
							"contactEmail").trim();
					String telNo = DanhMucUtils.getString(requestOrg, "telNo");
					String tugboatCompanyCodeGroup = DanhMucUtils.getString(
							requestOrg, "tugboatCompanyCode");
					String headers[] = new String[] { "Mã công ty tàu lai",
							"Tên công ty tàu lai", "Địa chỉ", "Thư điện tử",
							"Số điện thoại", "Số fax", "Mã cảng vụ", "Ghi chú",
							"Trạng thái" };
					String keys[] = new String[] { "tugboatCompanyCode",
							"tugboatCompanyName", "companyAddress",
							"contactEmail", "telNo", "faxNo", "maritimeCode",
							"Ghi chú", "isDelete" };
					nameExcel = "Bảng danh mục danh sách công ty tàu lai";
					workbook = DanhMucUtils.reportExcel(VmaTugboatCompanyUtils
							.getVmaTugboatCompanys(userId, maritimeCode,
									tugboatCompanyName, companyAddress,
									contactEmail, telNo, isDelete,
									tugboatCompanyCodeGroup, -1, -1), headers,
							keys, nameExcel);
				} else if (reportId.equals("DM_VMA_TUGBOAT")) {
					String _shipName = DanhMucUtils.getString(requestOrg,
							"_shipName").trim();
					String tugboatCompanyCode = DanhMucUtils.getString(
							requestOrg, "tugboatCompanyCode").trim();
					String shipCodeGroup = DanhMucUtils.getString(requestOrg,
							"shipCode");
					int power1 = ParamUtil.getInteger(requestOrg, "power1");
					int power2 = ParamUtil.getInteger(requestOrg, "power2");
					String headers[] = new String[] { "Mã công ty tàu lai",
							"Tên công ty tàu lai", "Mã tàu lai", "Tên tàu lai",
							"Công suất máy", "Chiều dài lớn nhất",
							"Chiều rộng lớn nhất", "Chiều cao tĩnh không",
							"Lượng chiếm nước", "Đơn vị công suất máy",
							"Đơn giá (VND)", "Đơn giá (USD)", "Mã cảng vụ",
							"Ghi chú", "Tình trạng" };
					String keys[] = new String[] { "tugboatCompanyCode",
							"tugbaotCompanyName", "shipCode", "shipName",
							"power", "loa", "breadth", "cleranceHeight",
							"displacement", "unitPower", "vndUnitPrice",
							"usdUnitPrice", "maritimeCode", "remarks",
							"isDelete" };
					nameExcel = "Bảng danh mục danh sách tài lai";
					workbook = DanhMucUtils.reportExcel(VmaTugboatUtils
							.getVmaTugboats(userId, maritimeCode, _shipName,
									power1, power2, tugboatCompanyCode,
									isDelete, shipCodeGroup, -1, -1), headers,
							keys, nameExcel);
				} else if (reportId.equals("DM_VMA_PILOT_COMPANY")) {
					String pilotCompanyName = DanhMucUtils.getString(
							requestOrg, "pilotCompanyName").trim();
					String companyAddress = DanhMucUtils.getString(requestOrg,
							"companyAddress").trim();
					String contactEmail = DanhMucUtils.getString(requestOrg,
							"contactEmail").trim();
					String telNo = DanhMucUtils.getString(requestOrg, "telNo");
					String pilotCompanyCodeGroup = DanhMucUtils.getString(
							requestOrg, "pilotCompanyCode");
					String headers[] = new String[] { "Mã công ty hoa tiêu",
							"Tên công ty hoa tiêu", "Địa chỉ", "Thư điện tử",
							"Số điện thoại", "Số fax", "Mã cảng vụ", "Ghi chú",
							"Trạng thái" };
					String keys[] = new String[] { "pilotCompanyCode",
							"pilotCompanyName", "companyAddress",
							"contactEmail", "telNo", "faxNo", "maritimeCode",
							"Ghi chú", "isDelete" };
					nameExcel = "Bảng danh mục danh sách công ty hoa tiêu";
					workbook = DanhMucUtils.reportExcel(VmaPilotCompanyUtils
							.getVmaPilotCompanys(userId, maritimeCode,
									pilotCompanyName, companyAddress,
									contactEmail, telNo, isDelete,
									pilotCompanyCodeGroup, -1, -1), headers,
							keys, nameExcel);
				} else if (reportId.equals("DM_VMA_PILOT")) {
					String pilotCompanyCode = DanhMucUtils.getString(
							requestOrg, "pilotCompanyCode").trim();
					String pilotCategoryCode = DanhMucUtils.getString(
							requestOrg, "pilotCategoryCode").trim();
					String pilotName = DanhMucUtils.getString(requestOrg,
							"pilotName").trim();
					String pilotCode = DanhMucUtils.getString(requestOrg,
							"pilotCode");
					String headers[] = new String[] { "Mã công ty hoa tiêu",
							"Tên công ty hoa tiêu", "Mã hoa tiêu",
							"Tên hoa tiêu", "Ngày sinh", "Số hiệu", "Số bằng",
							"Ngày cấp bằng", "Mã hạng hoa tiêu", "Mã cảng vụ",
							"Ghi chú", "Trạng thái" };
					String keys[] = new String[] { "pilotCompanyCode",
							"pilotCompanyName", "pilotCode", "pilotName",
							"pilotBOD", "pilotNo", "pilotCertificateNo",
							"pilotCertificateDate", "pilotCategoryCode",
							"maritimeCode", "remarks", "isDelete" };
					nameExcel = "Bảng danh mục danh sách hoa tiêu";
					workbook = DanhMucUtils.reportExcel(VmaPilotUtils
							.getVmaPilots(userId, maritimeCode,
									pilotCompanyCode, pilotCategoryCode,
									pilotName, isDelete, pilotCode, -1, -1),
							headers, keys, nameExcel);
				} else if (reportId.equals("DM_VMA_PILOT_CATEGORY")) {
					String pilotCategoryName = DanhMucUtils.getString(
							requestOrg, "pilotCategoryName").trim();
					String pilotCategoryCodeGroup = DanhMucUtils.getString(
							requestOrg, "pilotCategoryCode");
					String headers[] = new String[] { "Mã hạng hoa tiêu",
							"Tên hạng hoa tiêu", "Giới hạn chiều dài tối đa",
							"Giới hạn trọng tải đăng ký",
							"Ghi chú thời gian dẫn tàu", "Ghi chú",
							"Trạng thái" };
					String keys[] = new String[] { "pilotCategoryCode",
							"pilotCategoryName", "maxLength", "grossTonage",
							"safeTime", "remarks", "isDelete" };
					nameExcel = "Bảng danh mục danh sách hạng hoa tiêu";
					workbook = DanhMucUtils.reportExcel(VmaPilotCategoryUtils
							.getVmaPilotCategorys(pilotCategoryName, isDelete,
									pilotCategoryCodeGroup, -1, -1), headers,
							keys, nameExcel);
				} else if (reportId.equals("DM_VMA_SHIP_OWNER")) {
					String taxCode = DanhMucUtils.getString(requestOrg,
							"taxCode").trim();
					String shipOwnerCodeGroup = DanhMucUtils.getString(
							requestOrg, "shipOwnerCode");
					String companyName = DanhMucUtils.getString(requestOrg,
							"companyName").trim();
					String companyAddress = DanhMucUtils.getString(requestOrg,
							"companyAddress").trim();
					String contactEmail = DanhMucUtils.getString(requestOrg,
							"contactEmail").trim();
					String telNo = DanhMucUtils.getString(requestOrg, "telNo")
							.trim();
					String isShipOwner = DanhMucUtils.getString(requestOrg,
							"isShipOwner");
					String isShipOperator = DanhMucUtils.getString(requestOrg,
							"isShipOperator");
					int isOther = ParamUtil
							.getInteger(requestOrg, "isOther", 0);

					String headers[] = new String[0];
					String keys[] = new String[0];
					if (isOther == 0) {
						headers = new String[] { "Mã chủ tàu", "Mã số thuế",
								"Tên chủ tàu", "Địa chỉ", "Thư điện tử",
								"Số điện thoại", "Số Fax", "Là chủ tàu",
								"Là người khai thác", "Ghi chú", "Trạng thái" };
						keys = new String[] { "shipOwnerCode", "taxCode",
								"companyName", "companyAddress",
								"contactEmail", "telNo", "faxNo",
								"isShipOwner", "isShipOperator", "remarks",
								"isDelete" };
						nameExcel = "Bảng danh mục danh sách chủ tàu";
					} else if (isOther == 1) {
						headers = new String[] { "Mã chủ hàng", "Mã số thuế",
								"Tên chủ hàng", "Địa chỉ", "Thư điện tử",
								"Số điện thoại", "Số Fax", "Ghi chú",
								"Trạng thái" };
						keys = new String[] { "shipOwnerCode", "taxCode",
								"companyName", "companyAddress",
								"contactEmail", "telNo", "faxNo", "remarks",
								"isDelete" };
						nameExcel = "Bảng danh mục danh sách chủ hàng";
					}

					workbook = DanhMucUtils.reportExcel(VmaShipOwnerUtils
							.getVmaShipOwners(userId, maritimeCode, taxCode,
									companyName, companyAddress, contactEmail,
									telNo, isShipOwner, isShipOperator,
									isDelete, shipOwnerCodeGroup, isOther, -1,
									-1), headers, keys, nameExcel);
				} else if (reportId.equals("DM_VMA_SHIPYARD")) {
					String companyName = DanhMucUtils.getString(requestOrg,
							"companyName").trim();
					String shipYardCodeGroup = DanhMucUtils.getString(
							requestOrg, "shipYardCodeGroup");
					String companyAddress = DanhMucUtils.getString(requestOrg,
							"companyAddress").trim();
					String contactEmail = DanhMucUtils.getString(requestOrg,
							"contactEmail").trim();
					String telNo = DanhMucUtils.getString(requestOrg, "telNo")
							.trim();
					String taxCode = DanhMucUtils.getString(requestOrg,
							"taxCode").trim();
					String headers[] = new String[] {
							"Mã công ty sửa chữa tàu", "MST",
							"Tên công ty sửa chữa tùa", "Địa chỉ",
							"Thư điện tử", "Số điện thoại", "Số fax",
							"Mã cảng vụ", "Ghi chú", "Trạng thái" };
					String keys[] = new String[] { "shipYardCode", "taxCode",
							"companyName", "companyAddress", "contactEmail",
							"telNo", "faxNo", "maritimeCode", "remarks",
							"isDelete" };
					nameExcel = "Bảng danh mục danh sách công ty sửa chữa tàu";
					workbook = DanhMucUtils.reportExcel(VmaShipyardUtils
							.getVmaShipYards(userId, maritimeCode, companyName,
									companyAddress, contactEmail, telNo,
									taxCode, isDelete, shipYardCodeGroup, -1,
									-1), headers, keys, nameExcel);
				} else if (reportId.equals("DM_VMA_SECURITY_OFFICE")) {
					String companyName = DanhMucUtils.getString(requestOrg,
							"companyName").trim();
					String companyAddress = DanhMucUtils.getString(requestOrg,
							"companyAddress").trim();
					String contactEmail = DanhMucUtils.getString(requestOrg,
							"contactEmail").trim();
					String telNo = DanhMucUtils.getString(requestOrg, "telNo")
							.trim();
					String securityOfficeCodeGroup = DanhMucUtils.getString(
							requestOrg, "securityOfficeCode");
					String headers[] = new String[] { "Mã cơ quan",
							"Tên cơ quan", "Địa chỉ", "Thư điện tử",
							"Số điện thoại", "Số fax", "Mã cảng vụ", "Ghi chú",
							"Trạng thái" };
					String keys[] = new String[] { "securityOfficeCode",
							"companyName", "companyAddress", "contactEmail",
							"telNo", "faxNo", "maritimeCode", "remarks",
							"isDelete" };
					nameExcel = "Bảng danh mục danh sách cơ quan ra quyết định bắt giữ";
					workbook = DanhMucUtils.reportExcel(VmaSecurityOfficeUtils
							.getVmaSecurityOffices(userId, maritimeCode,
									companyName, companyAddress, contactEmail,
									telNo, isDelete, securityOfficeCodeGroup,
									-1, -1), headers, keys, nameExcel);
				} else if (reportId.equals("DM_VMA_SHIP_TYPE")) {
					String shipTypeName = DanhMucUtils.getString(requestOrg,
							"shipTypeName").trim();
					String applyShip = DanhMucUtils.getString(requestOrg,
							"applyShip");
					String applyBoat = DanhMucUtils.getString(requestOrg,
							"applyBoat");
					String shipTypeCodeGroup = DanhMucUtils.getString(
							requestOrg, "shipTypeCode");
					String headers[] = new String[] { "Mã công dụng tàu",
							"Loại và công dụng tàu", "Công dụng của tàu biển",
							"Công dụng của PTTNĐ", "Mã loại tàu", "Ghi chú",
							"Trạng thái" };
					String keys[] = new String[] { "shipTypeCode",
							"shipTypeName", "applyShip", "applyBoat",
							"shipTypeRef", "remarks", "isDelete" };
					nameExcel = "Bảng danh mục loại và công dụng tàu";
					workbook = DanhMucUtils.reportExcel(VmaShipTypeUtils
							.getVmaShipTypes(shipTypeName, applyShip,
									applyBoat, isDelete, shipTypeCodeGroup, -1,
									-1), headers, keys, nameExcel);
				} else if (reportId.equals("VMA_TRANSACTION_DEPARTMENT")) {
					String portOfAuthority = DanhMucUtils.getString(requestOrg,
							"portOfAuthority");
					String departmentName = DanhMucUtils.getString(requestOrg,
							"departmentName");
					String headers[] = new String[] { "Mã cảng vụ",
							"Mã phòng ban", "Tên phòng ban", "Số thứ tự",
							"Giao dịch thanh toán tiền VNĐ",
							"Giao dịch thanh toán tiền USD", "Ngày cập nhật",
							"Trạng thái" };
					String keys[] = new String[] { "portOfAuthority",
							"departmentCode", "departmentName", "sequenceNo",
							"transactionTypeVND", "transactionTypeUSD",
							"modifiedDate" };
					nameExcel = "Danh sách phòng ban";
					workbook = DanhMucUtils.reportExcel(
							VmaTransactionDepartmentUtils
									.getVmaTransactionDepartments(userId,
											portOfAuthority, departmentName,
											-1, -1), headers, keys, nameExcel);
				} else if (reportId.equals("VMA_ACCIDENT_LIST")) {
					String portofAuthority = DanhMucUtils.getString(requestOrg,
							"portofAuthority");
					String accidentTime = DanhMucUtils.getString(requestOrg,
							"accidentTime");
					String accidentType = DanhMucUtils.getString(requestOrg,
							"accidentType");
					String accidentCriticalType = DanhMucUtils.getString(
							requestOrg, "accidentCriticalType");
					String nameOfShip = DanhMucUtils.getString(requestOrg,
							"nameOfShip");
					String IMONumber = DanhMucUtils.getString(requestOrg,
							"imoNumber");
					String callSign = DanhMucUtils.getString(requestOrg,
							"callSign");
					String flagStateOfShip = DanhMucUtils.getString(requestOrg,
							"flagStateOfShip");
					String registryNumber = DanhMucUtils.getString(requestOrg,
							"registryNumber");
					String numberOfDead = DanhMucUtils.getString(requestOrg,
							"numberOfDead");
					String numberOfMissed = DanhMucUtils.getString(requestOrg,
							"numberOfMissed");
					String numberOfInjured = DanhMucUtils.getString(requestOrg,
							"numberOfInjured");
					String accidentOfficialDate = DanhMucUtils.getString(
							requestOrg, "accidentOfficialDate");
					String pilotOnBoad = DanhMucUtils.getString(requestOrg,
							"pilotOnBoad");
					String makeInvestigation = DanhMucUtils.getString(
							requestOrg, "makeInvestigation");
					String investigationDate = DanhMucUtils.getString(
							requestOrg, "investigationDate");
					String headers[] = new String[] { "Mã cảng vụ",
							"Mã vụ tai nạn", "Ngày, giờ xảy ra tai nạn",
							"Địa điểm", "Tóm tắt vụ tai nạn",
							"Kết luận xử lý và khuyến nghị", "Loại tai nạn",
							"Phân loại mức độ", "Tên tàu", "Số IMO", "Hô hiệu",
							"Quốc tịch tàu", "Số đăng ký hành chính",
							"Liên quan tàu trong nước",
							"Liên quan tàu nước ngoài", "Tổn thất về người",
							"Số người chết", "Số người mất tích",
							"Số người bị thương", "Tổn thất về hàng hóa",
							"Chi tiết tổn thất về hàng hóa",
							"Tổn thất về phương tiện",
							"Chi tiết tổn thất về phương tiện",
							"Tổn thát về môi trường",
							"Chi tiết tổn thất về môi trường",
							"Ngưng trệ hoạt động hàng hải",
							"Chi tiết về ngưng trệ hoạt động hàng hải",
							"Số báo cáo điều tra tai nạn", "Ngày báo cáo",
							"Có hoa tiêu dẫn tàu", "Có điều tra tai nạn",
							"Ngày điều tra tai nạn", "Đơn vị điều tra tai nạn",
							"File đính kèm báo cáo điều tra tai nạn",
							"File đính kèm báo cáo khẩn cấp",
							"File đính kèm báo cáo chi tiết",
							"File đính kèm bộ hồ sơ", "Thời điểm cập nhật" };
					String keys[] = new String[] { "portofAuthority",
							"accidentCode", "accidentTime", "accidentRegion",
							"accidentBrief", "accidentConclusion",
							"accidentType", "accidentCriticalType",
							"nameOfShip", "imoNumber", "callSign",
							"flagStateOfShip", "registryNumber",
							"domesticShip", "internationalShip",
							"damageHumanLife", "numberOfDead",
							"numberOfMissed", "numberOfInjured",
							"damageOfCargo", "remarksOfCargo", "damageToShip",
							"remarksOfShip", "damageToEnvironment",
							"remarksOfEnvironment", "damageToMarineActivity",
							"remarksOfMarineActivity", "accidentOfficialNo",
							"accidentOfficialDate", "pilotOnBoad",
							"makeInvestigation", "investigationDate",
							"investigationOffice", "f1AttachedReport",
							"f2AttachedReport", "f3AttachedReport",
							"f4AttachedReport", "modifiedDate" };
					nameExcel = "Danh sách tàu gặp nạn";
					workbook = DanhMucUtils
							.reportExcel(
									VmaAccidentListUtils.getVmaAccidentLists(
											nameOfShip,
											flagStateOfShip,
											callSign,
											IMONumber,
											registryNumber,
											(Validator.isNotNull(accidentTime) && !accidentTime
													.isEmpty()) ? FormatData.formatDateShort3
													.parse(accidentTime) : null,
											accidentType,
											accidentCriticalType,
											numberOfDead,
											numberOfMissed,
											numberOfInjured,
											pilotOnBoad,
											makeInvestigation,
											(Validator
													.isNotNull(investigationDate) && !investigationDate
													.isEmpty()) ? FormatData.formatDateShort3
													.parse(investigationDate)
													: null,
											portofAuthority,
											(Validator
													.isNotNull(accidentOfficialDate) && !accidentOfficialDate
													.isEmpty()) ? FormatData.formatDateShort3
													.parse(accidentOfficialDate)
													: null, -1, -1), headers,
									keys, nameExcel);
				}
				if (workbook != null) {
					VmaAuditLogLocalServiceUtil.addVmaAuditLog(userId,
							userSign != null ? userSign.getModifyuser()
									: StringPool.BLANK, LogsConstant.EXPORT,
							reportId, StringPool.BLANK, StringPool.BLANK);
					//todo auto Download file in DanhMucRiengAction
//					resourceResponse
//							.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//					resourceResponse.setProperty("Content-Disposition",
//							"attachment; filename= " + nameExcel + ".xls");
//					resourceResponse.setCharacterEncoding("UTF-8");
//					workbook.write(resourceResponse.getPortletOutputStream());
				}
			}
			if (resourceID.equals("getDetail")) {
				String categoryId = DanhMucUtils.getString(requestOrg,
						"categoryId");
				if (categoryId.equals("DM_VRCODE")) {
					Integer id = ParamUtil.getInteger(requestOrg, "id", -1);
					return writeJSON(resourceRequest, resourceResponse,
							DmVRCodeUtils.getDetailPort(id));
				}
				if (categoryId.equals("DM_ROUTE")) {
					Integer id = ParamUtil.getInteger(requestOrg, "id", -1);
					return writeJSON(resourceRequest, resourceResponse,
							DmRouteUtils.getDetailPort(id));
				}
				if (categoryId.equals("DM_PORT_REGION")) {
					String portRegionCode = DanhMucUtils.getString(requestOrg,
							"portRegionCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmPortRegionUtils
									.getDetailPortRegion(portRegionCode));
				} else if (categoryId.equals("DM_PORT_WHARF")) {
					String portWharfCode = DanhMucUtils.getString(requestOrg,
							"portWharfCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmPortWharfUtils.getDetailPortWharf(portWharfCode));
				} else if (categoryId.equals("DM_PORT_HARBOUR")) {
					String portHarbourCode = DanhMucUtils.getString(requestOrg,
							"portHarbourCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmPortHarbourUtils
									.getDetailPortHarbour(portHarbourCode));
				} else if (categoryId.equals("DM_MARITIME")) {
					return writeJSON(resourceRequest, resourceResponse,
							DmMaritimeUtils.getDetailMaritime(maritimeCode));
				} else if (categoryId.equals("DM_DATAITEM_GROUP107")) {
					String node2 = DanhMucUtils.getString(requestOrg, "node2");
					return writeJSON(resourceRequest, resourceResponse,
							DmDataItemUtils.getDetailDataItem107(node2));
				} else if (categoryId.equals("DM_TUYEN_LUONG")) {
					long dataItemId = ParamUtil.getLong(requestOrg,
							"dataitemId");
					return writeJSON(resourceRequest, resourceResponse,
							DmDataItemUtils.getDetailDataItem(dataItemId));
				} else if (categoryId.equals("DM_SHIP_AGENCY")) {
					String shipAgencyCode = DanhMucUtils.getString(requestOrg,
							"shipAgencyCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmShipAgencyUtils
									.getDetailShipAgency(shipAgencyCode));
				} else if (categoryId.equals("DM_STATE")) {
					String stateCode = DanhMucUtils.getString(requestOrg,
							"stateCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmStateUtils.getDetailState(stateCode));
				} else if (categoryId.equals("DM_SHIP_TYPE")) {
					String shipTypeCode = DanhMucUtils.getString(requestOrg,
							"shipTypeCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmShipTypeUtils.getDetailShipType(shipTypeCode));
				} else if (categoryId.equals("DM_PORT")) {
					int portId = ParamUtil.getInteger(requestOrg, "portId");
					return writeJSON(resourceRequest, resourceResponse,
							DmPortUtils.getDetailPort(portId));
				} else if (categoryId.equals("DM_ARRIVAL_PURPOSE")) {
					String purposeCode = DanhMucUtils.getString(requestOrg,
							"purposeCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmArrivalPurposeUtils
									.getDetailArrivalPurpose(purposeCode));
				} else if (categoryId.equals("DM_DOC_TYPE")) {
					String documentTypeCode = DanhMucUtils.getString(
							requestOrg, "documentTypeCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmDocTypeUtils.getDetailDocType(documentTypeCode));
				} else if (categoryId.equals("DM_PASSPORT_TYPE")) {
					String passportTypeCode = DanhMucUtils.getString(
							requestOrg, "passportTypeCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmPassportTypeUtils
									.getDetailPassportType(passportTypeCode));
				} else if (categoryId.equals("DM_SECURITY_LEVEL")) {
					String securityLevelCode = DanhMucUtils.getString(
							requestOrg, "securityLevelCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmSecurityLevelUtils
									.getDetailSecurityLevel(securityLevelCode));
				} else if (categoryId.equals("DM_GOODS_TYPE")) {
					String goodsTypeCode = DanhMucUtils.getString(requestOrg,
							"goodsTypeCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmGoodTypeUtils.getDetailGoodsType(goodsTypeCode));
				} else if (categoryId.equals("DM_GOODS")) {
					String goodsItemCode = DanhMucUtils.getString(requestOrg,
							"goodsItemCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmGoodUtils.getDetailGoods(goodsItemCode));
				} else if (categoryId.equals("DM_PACKAGE")) {
					String packageCode = DanhMucUtils.getString(requestOrg,
							"packageCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmPackageUtils.getDetailPackage(packageCode));
				} else if (categoryId.equals("DM_RANK_RATING")) {
					String rankCode = DanhMucUtils.getString(requestOrg,
							"rankCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmRankRatingUtils.getDetailRankRating(rankCode));
				} else if (categoryId.equals("DM_REPRESENTATIVE")) {
					String representativeCode = DanhMucUtils.getString(
							requestOrg, "repCode").trim();
					return writeJSON(
							resourceRequest,
							resourceResponse,
							DmRepresentativeUtils
									.getDetailRepresentative(representativeCode));
				} else if (categoryId.equals("DM_UNIT_GENERAL")) {
					String unitCode = DanhMucUtils.getString(requestOrg,
							"unitCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							DmUnitGeneralUtils.getDetailUnitGeneral(unitCode));
				} else if (categoryId.equals("DM_DATAITEM_GROUP124")) {
					long dataItemId = ParamUtil.getLong(requestOrg,
							"dataitemId");
					return writeJSON(resourceRequest, resourceResponse,
							DmDataItemUtils.getDetailDataItem124(dataItemId));
				} else if (categoryId.equals("DM_CARGO_ON_BOARD")) {
					String goodsTypeCode = DanhMucUtils.getString(requestOrg,
							"goodsTypeCode");
					return writeJSON(resourceRequest, resourceResponse,
							DmCargoOnBoardUtils
									.getDetailCargoOnBoard(goodsTypeCode));
				} else if (categoryId.equals("DM_VMA_TUGBOAT_COMPANY")) {
					String tugboatCompanyCode = DanhMucUtils.getString(
							requestOrg, "tugboatCompanyCode").trim();
					return writeJSON(
							resourceRequest,
							resourceResponse,
							VmaTugboatCompanyUtils
									.getDetailVmaTugboatCompany(tugboatCompanyCode));
				} else if (categoryId.equals("DM_VMA_TUGBOAT")) {
					String _shipCode = DanhMucUtils.getString(requestOrg,
							"_shipCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							VmaTugboatUtils.getDetailVmaTugboat(_shipCode));
				} else if (categoryId.equals("DM_VMA_PILOT_COMPANY")) {
					String pilotCompanyCode = DanhMucUtils.getString(
							requestOrg, "pilotCompanyCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							VmaPilotCompanyUtils
									.getDetailVmaPilotCompany(pilotCompanyCode));
				} else if (categoryId.equals("DM_VMA_PILOT")) {
					String pilotCode = DanhMucUtils.getString(requestOrg,
							"pilotCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							VmaPilotUtils.getDetailVmaPilot(pilotCode));
				} else if (categoryId.equals("DM_VMA_PILOT_CATEGORY")) {
					String pilotCategoryCode = DanhMucUtils.getString(
							requestOrg, "pilotCategoryCode").trim();
					return writeJSON(
							resourceRequest,
							resourceResponse,
							VmaPilotCategoryUtils
									.getDetailVmaPilotCategory(pilotCategoryCode));
				} else if (categoryId.equals("DM_VMA_SHIP_OWNER")) {
					String shipOwnerCode = DanhMucUtils.getString(requestOrg,
							"shipOwnerCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							VmaShipOwnerUtils
									.getDetailVmaShipOwner(shipOwnerCode));
				} else if (categoryId.equals("DM_VMA_SHIPYARD")) {
					String shipYardCode = DanhMucUtils.getString(requestOrg,
							"shipYardCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							VmaShipyardUtils.getDetailVmaShipYard(shipYardCode));
				} else if (categoryId.equals("DM_VMA_SECURITY_OFFICE")) {
					String securityOfficeCode = DanhMucUtils.getString(
							requestOrg, "securityOfficeCode").trim();
					return writeJSON(
							resourceRequest,
							resourceResponse,
							VmaSecurityOfficeUtils
									.getDetailVmaSecurityOffice(securityOfficeCode));
				} else if (categoryId.equals("DM_VMA_SHIP_TYPE")) {
					String shipTypeCode = DanhMucUtils.getString(requestOrg,
							"shipTypeCode").trim();
					return writeJSON(resourceRequest, resourceResponse,
							VmaShipTypeUtils.getDetailVmaShipType(shipTypeCode));
				} else if (categoryId.equals("VMA_TRANSACTION_DEPARTMENT")) {
					String departmentCode = DanhMucUtils.getString(requestOrg,
							"departmentCode").trim();
					return writeJSON(
							resourceRequest,
							resourceResponse,
							VmaTransactionDepartmentUtils
									.getDetailVmaTransactionDepartment(departmentCode));
				}
			}
			if (resourceID.equals("getMaritimes")) {
				String maritimeCodeBus = DanhMucUtils.getString(requestOrg,
						"maritimeCode");
				String isDelete = DanhMucUtils
						.getString(requestOrg, "isDelete");
				return writeJSON(resourceRequest, resourceResponse,
						DmMaritimeUtils.getMaritimes(userId, maritimeCodeBus,
								isDelete, start, end));
			}
			if (resourceID.equals("checkMST")) {
				String taxCode = DanhMucUtils.getString(requestOrg, "taxCode")
						.trim();
				String type = DanhMucUtils.getString(requestOrg, "type").trim();
				return writeJSON(resourceRequest, resourceResponse,
						DanhMucUtils.checkTaxCode(type, taxCode));
			}
			if (resourceID.equals("getVmaReportCategory")) {
				String rptGroup = DanhMucUtils
						.getString(requestOrg, "rptGroup");
				String rptLevel = DanhMucUtils
						.getString(requestOrg, "rptLevel");
				return writeJSON(resourceRequest, resourceResponse,
						VmaReportCategoryUtils.getVmaReportCategory(rptGroup,
								rptLevel, start, end));
			}
			if (resourceID.equals("warningPortWharf")) {
				String portWharfCode = DanhMucUtils.getString(requestOrg,
						"portWharfCode");
				Integer dwt = ParamUtil.getInteger(requestOrg, "dwt", 0);
				Double loa = ParamUtil.getDouble(requestOrg, "loa", 0);
				Double shownDraftxA = ParamUtil.getDouble(requestOrg,
						"shownDraftxA", 0);
				return writeJSON(resourceRequest, resourceResponse,
						DanhMucUtils.warningPortWharf(portWharfCode, dwt, loa,
								shownDraftxA));
			}
			if (resourceID.equals("warningTugboat")) {
				String imoNumber = DanhMucUtils.getString(requestOrg,
						"imoNumber");
				String callSign = DanhMucUtils
						.getString(requestOrg, "callSign");
				String itineraryNo = DanhMucUtils.getString(requestOrg,
						"itineraryNo");
				if (stt == 0) {
					return writeJSON(resourceRequest, resourceResponse,
							DanhMucUtils.warningTugboat(maritimeCode,
									imoNumber, callSign, itineraryNo));
				}
			}
			if (resourceID.equals("warningPilot")) {
				String imoNumber = DanhMucUtils.getString(requestOrg,
						"imoNumber");
				String callSign = DanhMucUtils
						.getString(requestOrg, "callSign");
				String itineraryNo = DanhMucUtils.getString(requestOrg,
						"itinerartNo");
				return writeJSON(resourceRequest, resourceResponse,
						DanhMucUtils.warningPilot(imoNumber, callSign,
								itineraryNo));
			}
			if (resourceID.equals("getDetailVmaAccidentList")) {
				long id = ParamUtil.getLong(requestOrg, "id");
				return writeJSON(resourceRequest, resourceResponse,
						VmaAccidentListUtils.getDetailVmaAccidentList(id));
			}
			if (resourceID.equals("getVmaAccidentList")) {
				String portofAuthority = DanhMucUtils.getString(requestOrg,
						"portofAuthority");
				String accidentTime = DanhMucUtils.getString(requestOrg,
						"accidentTime");
				String accidentType = DanhMucUtils.getString(requestOrg,
						"accidentType");
				String accidentCriticalType = DanhMucUtils.getString(
						requestOrg, "accidentCriticalType");
				String nameOfShip = DanhMucUtils.getString(requestOrg,
						"nameOfShip");
				String IMONumber = DanhMucUtils.getString(requestOrg,
						"imoNumber");
				String callSign = DanhMucUtils
						.getString(requestOrg, "callSign");
				String flagStateOfShip = DanhMucUtils.getString(requestOrg,
						"flagStateOfShip");
				String registryNumber = DanhMucUtils.getString(requestOrg,
						"registryNumber");
				String numberOfDead = DanhMucUtils.getString(requestOrg,
						"numberOfDead");
				String numberOfMissed = DanhMucUtils.getString(requestOrg,
						"numberOfMissed");
				String numberOfInjured = DanhMucUtils.getString(requestOrg,
						"numberOfInjured");
				String accidentOfficialDate = DanhMucUtils.getString(
						requestOrg, "accidentOfficialDate");
				String pilotOnBoad = DanhMucUtils.getString(requestOrg,
						"pilotOnBoad");
				String makeInvestigation = DanhMucUtils.getString(requestOrg,
						"makeInvestigation");
				String investigationDate = DanhMucUtils.getString(requestOrg,
						"investigationDate");
				return writeJSON(
						resourceRequest,
						resourceResponse,
						VmaAccidentListUtils.getVmaAccidentLists(
								nameOfShip,
								flagStateOfShip,
								callSign,
								IMONumber,
								registryNumber,
								(Validator.isNotNull(accidentTime) && !accidentTime
										.isEmpty()) ? FormatData.formatDateShort3
										.parse(accidentTime) : null,
								accidentType,
								accidentCriticalType,
								numberOfDead,
								numberOfMissed,
								numberOfInjured,
								pilotOnBoad,
								makeInvestigation,
								(Validator.isNotNull(investigationDate) && !investigationDate
										.isEmpty()) ? FormatData.formatDateShort3
										.parse(investigationDate) : null,
								portofAuthority,
								(Validator.isNotNull(accidentOfficialDate) && !accidentOfficialDate
										.isEmpty()) ? FormatData.formatDateShort3
										.parse(accidentOfficialDate) : null,
								start, end));
			} else if (resourceID.equals("getVmaPilotViolations")) {
				String violationDate1 = DanhMucUtils.getString(requestOrg,
						"violationDate1");
				String violationDate2 = DanhMucUtils.getString(requestOrg,
						"violationDate2");
				return writeJSON(
						resourceRequest,
						resourceResponse,
						VmaPilotViolationUtils.getVmaPilotViolations(
								userId,
								maritimeCode,
								(Validator.isNotNull(violationDate1) && !violationDate1
										.isEmpty()) ? FormatData.formatDateShort2
										.parse(violationDate1) : null,
								(Validator.isNotNull(violationDate2) && !violationDate2
										.isEmpty()) ? FormatData.formatDateShort2
										.parse(violationDate2) : null, start,
								end));
			} else if (resourceID.equals("getDetailVmaPilotViolation")) {
				long id = ParamUtil.getLong(requestOrg, "id");
				return writeJSON(resourceRequest, resourceResponse,
						VmaPilotViolationUtils.getDetailVmaPilotViolation(id));
			} else if (resourceID.equals("getVmaAdministrativeViolations")) {
				String portofAuthority = DanhMucUtils.getString(requestOrg,
						"portofAuthority");
				String violationDate = DanhMucUtils.getString(requestOrg,
						"violationDate");
				String notViolationDate = DanhMucUtils.getString(requestOrg,
						"notViolationDate");
				return writeJSON(resourceRequest, resourceResponse,
						VmaAdministrativeViolationUtils
								.getVmaAdministrativeViolations(
										portofAuthority, violationDate,
										notViolationDate, start, end));
			} else if (resourceID.equals("getDetailVmaAdministrativeViolation")) {
				long id = ParamUtil.getLong(requestOrg, "id");
				return writeJSON(resourceRequest, resourceResponse,
						VmaAdministrativeViolationUtils
								.getDetailVmaAdministrativeViolations(id));
			} else if (resourceID.equals("getDataItems")) {
				long dataGroupId = ParamUtil.getLong(requestOrg, "dataGroupId");
				String name = DanhMucUtils.getString(requestOrg, "name").trim();
				String codeGroup = DanhMucUtils.getString(requestOrg, "code");
				String status = DanhMucUtils.getString(requestOrg, "status")
						.trim();
				String maritimeCodeTemp = DanhMucUtils.getString(requestOrg,
						"maritimeCode");
				return writeJSON(resourceRequest, resourceResponse,
						DmDataItemUtils
								.getDataItems(dataGroupId, name, userId,
										maritimeCodeTemp, status, codeGroup,
										start, end));
			} else if (resourceID.equals("excelBienLaiThuPhi")) {
				String itineraryNo = DanhMucUtils.getString(requestOrg,
						"itineraryNo");
				String documentaryCode = DanhMucUtils.getString(requestOrg,
						"documentaryCode");
				JSONObject data = JSONFactoryUtil.createJSONObject();
				String fileName = "BienLaiThuPhiLePhiHangHai_"
						+ (new Date()).getTime() + ".xls";
				String UrlFile = request.getContextPath() + "/export/"
						+ fileName;
				boolean hasDataBoolean = false;
				int printing = ParamUtil.getInteger(requestOrg, "printing");
				VmaTransactionSlipUtils vmaTransactionSlipUtils = new VmaTransactionSlipUtils();
//				hasDataBoolean = vmaTransactionSlipUtils.export2Excel(
//						itineraryNo, documentaryCode, fileName, userId, printing);
				if (hasDataBoolean) {
					data.put("url", UrlFile);
					data.put("download", UrlFile);
				} else {
					data.put("url", StringPool.BLANK);
					data.put("download", StringPool.BLANK);
				}
				return writeJSON(resourceRequest, resourceResponse, data);
			} else if (resourceID.equals("getDepartmentCodeByUserId")) {
				long _userId = ParamUtil.getLong(requestOrg, "userId");
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionDepartmentUtils
								.getDepartmentCodeByUserId(_userId));
			} else if (resourceID.equals("getVmaHoatDongNaoVets")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaHoatDongNaoVetUtils.getHoatDongNaoVets(start, end));
			} else if (resourceID.equals("getDetailVmaHoatDongNaoVets")) {
				long id = ParamUtil.getLong(requestOrg, "ID");
				return writeJSON(resourceRequest, resourceResponse,
						VmaHoatDongNaoVetUtils.getDetailVmaHoatDongNaoVets(id));
			} else if (resourceID.equals("getVmaAnNinhCangBiens")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaAnNinhCangBienUtils.getAnNinhCangBiens(start, end));
			} else if (resourceID.equals("getDetailVmaAnNinhCangBiens")) {
				long id = ParamUtil.getLong(requestOrg, "ID");
				return writeJSON(resourceRequest, resourceResponse,
						VmaAnNinhCangBienUtils.getDetailVmaAnNinhCangBiens(id));
			} else if (resourceID.equals("getVmaServicePorts")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaServicePortUtils.getServicePorts(start, end));
			} else if (resourceID.equals("getDetailVmaServicePorts")) {
				long id = ParamUtil.getLong(requestOrg, "ID");
				return writeJSON(resourceRequest, resourceResponse,
						VmaServicePortUtils.getDetailVmaServicePorts(id));
			} else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		} catch (Exception e) {
			try {
				throw new Exception(e.getMessage());
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return super.serveResource(resourceRequest, resourceResponse);

	}

	@Override
	public ResponseEntity<?> processAction(ActionRequest actionRequest,
										   ActionResponse actionResponse)  {
		try {
			String actionName = DanhMucUtils.getString(actionRequest,
					ActionRequest.ACTION_NAME);
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);

			if (actionName.equals("actionUpdateDanhmuc")) {
				String categoryId = DanhMucUtils.getString(actionRequest,
						"categoryId");
				if (categoryId.equals("DM_PORT_REGION")) {
					DmPortRegionUtils.actionUpdatePortRegion(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_PORT_WHARF")) {
					DmPortWharfUtils.actionUpdatePortWharf(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_PORT_HARBOUR")) {
					DmPortHarbourUtils.actionUpdatePortHarbour(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_MARITIME")) {
					DmMaritimeUtils.actionUpdateMaritime(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_DATAITEM_GROUP107")) {
					DmDataItemUtils.actionUpdateDataItemGroup107(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_TUYEN_LUONG")) {
					DmDataItemUtils.actionUpdateDataItem(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_VMA_TUGBOAT_COMPANY")) {
					VmaTugboatCompanyUtils.actionUpdateTugboatCompany(
							themeDisplay, actionRequest);
				} else if (categoryId.equals("DM_VMA_TUGBOAT")) {
					VmaTugboatUtils.actionUpdateVmaTugboat(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_VMA_PILOT_COMPANY")) {
					VmaPilotCompanyUtils.actionUpdatePilotCompany(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_VMA_PILOT")) {
					VmaPilotUtils.actionUpdateVmaPilot(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_VMA_PILOT_CATEGORY")) {
					VmaPilotCategoryUtils.actionUpdatePilotCategory(
							themeDisplay, actionRequest);
				} else if (categoryId.equals("DM_VMA_SHIP_OWNER")) {
					return writeJSON(actionRequest, actionResponse,
							VmaShipOwnerUtils.actionUpdateVmaShipOwner(
									themeDisplay, actionRequest));
				} else if (categoryId.equals("DM_VMA_SHIP_TYPE")) {
					VmaShipTypeUtils.actionUpdateVmaShipType(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_VMA_SHIPYARD")) {
					VmaShipyardUtils.actionUpdateShipYard(themeDisplay,
							actionRequest);
				} else if (categoryId.equals("DM_VMA_SECURITY_OFFICE")) {
					VmaSecurityOfficeUtils.actionUpdateVmaSecurityOffice(
							themeDisplay, actionRequest);
				} else if (categoryId.equals("VMA_TRANSACTION_DEPARTMENT")) {
					VmaTransactionDepartmentUtils
							.actionUpdateVmaTransactionDepartment(themeDisplay,
									actionRequest);
				} else if (categoryId.equals("DM_SHIP_AGENCY")) {
					DmShipAgencyUtils.actionUpdateDmShipAgency(themeDisplay,
							actionRequest);
					String shipAgencyCode = DanhMucUtils.getString(actionRequest,
							"shipAgencyCode");
					return writeJSON(actionRequest, actionResponse,
							DmShipAgencyUtils
									.getDetailShipAgency(shipAgencyCode));

				} else if (categoryId.equals("DM_PORT")) {
					return writeJSON(actionRequest, actionResponse,
							DmPortUtils.updateDmPort(themeDisplay,
									actionRequest));
				}
			} else if (actionName.equals("updateUserPort")) {
				UserPortUtils.updateUserPort(themeDisplay, actionRequest);
			} else if (actionName.equals("updateVmaAccidentList")) {
				return writeJSON(actionRequest, actionResponse,
						VmaAccidentListUtils.actionUpdateVmaAccidentList(
								themeDisplay, actionRequest));
			} else if (actionName.equals("updateVmaPilotViolation")) {
				return writeJSON(actionRequest, actionResponse,
						VmaPilotViolationUtils.actionUpdateVmaPilotViolation(
								themeDisplay, actionRequest));
			} else if (actionName.equals("updateVmaAdministrativeViolation")) {
				return writeJSON(actionRequest, actionResponse,
						VmaAdministrativeViolationUtils
								.actionUpdateVmaAdministrativeViolation(
										themeDisplay, actionRequest));
			} else if (actionName.equals("updateVmaHoatDongNaoVet")) {
				return writeJSON(actionRequest, actionResponse,
						VmaHoatDongNaoVetUtils.actionUpdateVmaHoatDongNaoVet(
								themeDisplay, actionRequest));
			} else if (actionName.equals("updateVmaAnNinhCangBien")) {
				return writeJSON(actionRequest, actionResponse,
						VmaAnNinhCangBienUtils.actionUpdateVmaAnNinhCangBien(
								themeDisplay, actionRequest));
			} else if (actionName.equals("updateVmaServicePort")) {
				return writeJSON(actionRequest, actionResponse,
						VmaServicePortUtils.actionUpdateVmaServicePort(
								themeDisplay, actionRequest));
			} else {
				super.processAction(actionRequest, actionResponse);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return super.processAction(actionRequest, actionResponse);
	}
}