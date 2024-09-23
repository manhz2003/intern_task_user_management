package vn.gt.portlet.thutuc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;



import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.core.ThemeDisplay;
import com.fds.nsw.liferay.core.PortalUtil;

import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPort;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.portlet.TransportationMVCAction;
import vn.gt.portlet.kehoach.tt1.TT1ActionProvider;
import vn.gt.portlet.kehoach.tt1.TT1JSONProvider;
import vn.gt.portlet.kehoach.tt10.TT10ActionProvider;
import vn.gt.portlet.kehoach.tt10.TT10JSONProvider;
import vn.gt.portlet.kehoach.tt11.TT11ActionProvider;
import vn.gt.portlet.kehoach.tt11.TT11JSONProvider;
import vn.gt.portlet.kehoach.tt14.TT14ActionProvider;
import vn.gt.portlet.kehoach.tt14.TT14JSONProvider;
import vn.gt.portlet.kehoach.tt15.TT15ActionProvider;
import vn.gt.portlet.kehoach.tt15.TT15JSONProvider;
import vn.gt.portlet.kehoach.tt16.TT16ActionProvider;
import vn.gt.portlet.kehoach.tt16.TT16JSONProvider;
import vn.gt.portlet.kehoach.tt17.TT17ActionProvider;
import vn.gt.portlet.kehoach.tt17.TT17JSONProvider;
import vn.gt.portlet.kehoach.tt2.TT2ActionProvider;
import vn.gt.portlet.kehoach.tt2.TT2JSONProvider;
import vn.gt.portlet.kehoach.tt3.TT3ActionProvider;
import vn.gt.portlet.kehoach.tt3.TT3JSONProvider;
import vn.gt.portlet.kehoach.tt4.TT4ActionProvider;
import vn.gt.portlet.kehoach.tt4.TT4JSONProvider;
import vn.gt.portlet.kehoach.tt5.TT5ActionProvider;
import vn.gt.portlet.kehoach.tt5.TT5JSONProvider;
import vn.gt.portlet.kehoach.tt6.TT6ActionProvider;
import vn.gt.portlet.kehoach.tt6.TT6JSONProvider;
import vn.gt.portlet.kehoach.tt7.TT7ActionProvider;
import vn.gt.portlet.kehoach.tt7.TT7JSONProvider;
import vn.gt.portlet.kehoach.tt8.TT8ActionProvider;
import vn.gt.portlet.kehoach.tt8.TT8JSONProvider;
import vn.gt.portlet.kehoach.tt9.TT9ActionProvider;
import vn.gt.portlet.kehoach.tt9.TT9JSONProvider;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.portlet.kehoach.utils.JSONProviderUtils;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;

/**
 * Portlet implementation class ThuTucAction
 */
@Slf4j
@Service
public class VanThuAction extends TransportationMVCAction {

	private String realPath ;

	private String pathFileSub;

	public VanThuAction() {
		try{
			//todo handle file path
			realPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
			pathFileSub = realPath.substring(realPath.lastIndexOf(":"), realPath.lastIndexOf("/WEB-INF"))
					.replaceFirst(":", "") + "/export/";
		} catch (Exception e) {

		}

	}
	

	public void getComputerHash(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {
		log.info("-----------vao ------------getComputerHash thu tuc----------");
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);

			long groupId = themeDisplay.getScopeGroupId();

			HttpServletRequest request = resourceRequest;
			HttpServletRequest requestOrg = request;

			long documentName = ParamUtil.getLong(requestOrg, "documentName", 0);
			int documentYear = ParamUtil.getInteger(requestOrg, "documentYear", 0);
			int messageType = ParamUtil.getInteger(requestOrg, "messageType", 0);

			InputStream inputStream = null;

			String filePath = "";
			long fileEntryId = 0;
			String requestCode = StringPool.BLANK;
			String tenFileExport = StringPool.BLANK;

			if (messageType == 70) {

				List<IssueShiftingOrder> results = IssueShiftingOrderLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC);

				IssueShiftingOrder shiftingOrder = results.get(0);

				fileEntryId = shiftingOrder.getAttachedFile();

				requestCode = shiftingOrder.getRequestCode();

				tenFileExport = ReportConstant.SHIFTING_ORDER_EXPORT;

			} else if (messageType == 60) {

				List<IssuePortClearance> results = IssuePortClearanceLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC);
				IssuePortClearance issuePortClearance = results.get(0);

				fileEntryId = issuePortClearance.getAttachedFile();

				requestCode = issuePortClearance.getRequestCode();

				tenFileExport = ReportConstant.ISSUE_PORT_CLEARANCE_EXPORT;

			} else if (messageType == 90) {
				List<IssuePermissionForTransit> results = IssuePermissionForTransitLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC);
				IssuePermissionForTransit issuePortClearance = results.get(0);

				fileEntryId = issuePortClearance.getAttachedFile();

				requestCode = issuePortClearance.getRequestCode();

				tenFileExport = ReportConstant.PERMISSION_FOR_TRANSIT_EXPORT;

			}

			if (fileEntryId > 0) {
				filePath = SignatureUtil.saveAsPdfUpgrade(pathFileSub, fileEntryId);
			} else {

				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

				long nanoTime = ReportsBusinessUtils.actionExport(requestCode, (int) documentName, documentYear,
						messageType, tempDocument.getDocumentTypeCode());

				filePath = pathFileSub + nanoTime + "_" + tenFileExport;
			}

			if (messageType == 70) {

				SignatureUtil.genComputerHashByNoiDungHoSo(resourceRequest, resourceResponse,
						filePath, documentName, documentYear, messageType, groupId, 2);

			} else if (messageType == 60) {

				SignatureUtil.genComputerHashByNoiDungHoSo(resourceRequest, resourceResponse, filePath, documentName,
						documentYear, messageType, groupId, 2);
			} else if (messageType == 90) {

				SignatureUtil.genComputerHashByNoiDungHoSo(resourceRequest, resourceResponse, filePath, documentName,
						documentYear, messageType, groupId, 2);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public ResponseEntity<?> serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			 {
		try {
			HttpServletRequest request = resourceRequest;
			HttpServletRequest requestOrg = request;
			String resourceID = ParamUtil.getString(requestOrg, "p_p_resource_id", "");

			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);



			String documentType = ParamUtil.getString(requestOrg, "documentType", "");
			int messageType = ParamUtil.getInteger(requestOrg, "messageType", 0);
			int documentYear = ParamUtil.getInteger(requestOrg, "documentYear", 0);
			String shipName = ParamUtil.getString(requestOrg, "shipName", "");
			String daily = ParamUtil.getString(requestOrg, "daily");
			if (shipName.equalsIgnoreCase("undefined")) {
				shipName = StringPool.BLANK;
			}
			if (daily.equalsIgnoreCase("undefined")) {
				daily = StringPool.BLANK;
			}
			if (Validator.isNotNull(shipName)) {
				shipName = URLDecoder.decode(shipName, "UTF-8");
			}
			if (Validator.isNotNull(daily)) {
				daily = URLDecoder.decode(daily, "UTF-8");
			}
			
			if (resourceID.equals("getListPortWharf")) {

				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);
				String harbourCode = ParamUtil.getString(requestOrg, "harbourCode");

				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

				List<DmPortWharf> lstPortWharf = DmPortWharfLocalServiceUtil
						.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(tempDocument.getPortRegionCode(),
								harbourCode, KeyParams.ORDER_BY_ASC);

				JSONArray portsArrays = JSONFactoryUtil.createJSONArray();
				JSONObject port = null;
				for (DmPortWharf dmPort : lstPortWharf) {
					port = JSONFactoryUtil.createJSONObject();

					port.put("portWharfCode", dmPort.getPortWharfCode());
					port.put("portWharfName", dmPort.getPortWharfNameVN());

					portsArrays.put(port);
				}

				return writeJSON(resourceRequest, resourceResponse, portsArrays);

			} else if (resourceID.equals("getDetailThanhPhan")) {

				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);

				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

				JSONObject detailObject = JSONFactoryUtil
						.createJSONObject(JSONFactoryUtil.looseSerialize(tempDocument));

				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(tempDocument.getMaritimeCode());
				String maritimeName = Validator.isNotNull(dmMaritime) ? dmMaritime.getMaritimeNameVN()
						: StringPool.BLANK;

				detailObject.put("maritimeName", maritimeName);

				return writeJSON(resourceRequest, resourceResponse, detailObject);

			} else if (resourceID.equals("getDetailByDocumentName")) {

				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);

				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

				JSONObject detailObject = JSONFactoryUtil
						.createJSONObject(JSONFactoryUtil.looseSerialize(tempDocument));

				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(tempDocument.getMaritimeCode());
				String maritimeName = Validator.isNotNull(dmMaritime) ? dmMaritime.getMaritimeNameVN()
						: StringPool.BLANK;

				maritimeName = Validator.isNotNull(dmMaritime) ? dmMaritime.getCityCode() : StringPool.BLANK;
				String maritimeReferent = Validator.isNotNull(dmMaritime) ? dmMaritime.getMaritimeReference()
						: StringPool.BLANK;

				detailObject.put("maritimeName", maritimeName);
				detailObject.put("signLocation",
						Validator.isNotNull(dmMaritime) ? dmMaritime.getCityCode() : StringPool.BLANK);

				DmPort cangden = DmPortLocalServiceUtil.getByPortCode(tempDocument.getLastPortCode());

				detailObject.put("portName", Validator.isNotNull(cangden) ? cangden.getPortName() : StringPool.BLANK);

				detailObject.put("maritimeReferent", maritimeReferent);
				detailObject.put("canBoPheDuyet", themeDisplay.getUser().getEmailAddress());

				String ghichu = "";

				detailObject.put("ghichu", ghichu);
				return writeJSON(resourceRequest, resourceResponse, detailObject);

			} else if (resourceID.equals("getRoleFilterStatus")) {

				return writeJSON(resourceRequest, resourceResponse,
						JSONProvider.getRoleFilterStatus(themeDisplay.getLocale(), themeDisplay.getUser()));

			} else if (resourceID.equalsIgnoreCase("getComputerHash")) {

				getComputerHash(resourceRequest, resourceResponse);

			} else if (resourceID.equals("getDetailHoSo")) {

				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);

				return writeJSON(resourceRequest, resourceResponse,
						JSONProviderUtils.getDetailHoSo(themeDisplay, documentName, documentYear));

			} else if (resourceID.equals("getHistoryTable")) {

				String documentTypeCode = ParamUtil.getString(requestOrg, "documentTypeCode", "");
				String maritimeCode = ParamUtil.getString(requestOrg, "maritimeCode", "");
				String portCode = ParamUtil.getString(requestOrg, "portCode", "");
				String lastPortCode = ParamUtil.getString(requestOrg, "lastPortCode", "");
				String stateCode = ParamUtil.getString(requestOrg, "stateCode", "");
				String callSign = ParamUtil.getString(requestOrg, "callSign", "");
				String imo = ParamUtil.getString(requestOrg, "imo", "");
				String shipPosition = ParamUtil.getString(requestOrg, "shipPosition", "");
				String shipDateFromStart = ParamUtil.getString(requestOrg, "shipDateFromStart", "");
				String shipDateFromEnd = ParamUtil.getString(requestOrg, "shipDateFromEnd", "");
				String shipDateToStart = ParamUtil.getString(requestOrg, "shipDateToStart", "");
				String shipDateToEnd = ParamUtil.getString(requestOrg, "shipDateToEnd", "");
				String documentStatusCode = ParamUtil.getString(requestOrg, "documentStatusCode", "");
				String arrivalShipAgency = ParamUtil.getString(requestOrg, "arrivalShipAgency", "");
				String nameArrivalShipAgency = ParamUtil.getString(requestOrg, "nameArrivalShipAgency", "");
				String departureShipAgency = ParamUtil.getString(requestOrg, "departureShipAgency", "");
				String nameDepartureShipAgency = ParamUtil.getString(requestOrg, "nameDepartureShipAgency", "");
				String portRegionCode = ParamUtil.getString(requestOrg, "portRegionCode", "");
				String ngayLamThuTucFrom = ParamUtil.getString(requestOrg, "ngayLamThuTucFrom", "");
				String ngayLamThuTucTo = ParamUtil.getString(requestOrg, "ngayLamThuTucTo", "");
				String maBanKhai = ParamUtil.getString(requestOrg, "maBanKhai", "");

				int start = ParamUtil.getInteger(requestOrg, "start", 0);
				int end = ParamUtil.getInteger(requestOrg, "end", 15);

				String maritimeCodeOwner = ParamUtil.getString(requestOrg, "maritimeCodeOwner");
				String positionCodeAdv = ParamUtil.getString(requestOrg, "positionCode");
				String imoAdv = ParamUtil.getString(requestOrg, "imo");
				String stateCodeAdv = ParamUtil.getString(requestOrg, "stateCode");
				String maritimeCodeAdv = ParamUtil.getString(requestOrg, "maritimeCode");
				String maritimeCodeNext = ParamUtil.getString(requestOrg, "maritimeCodeNext");
				String timeShip = ParamUtil.getString(requestOrg, "timeShip");
				String timeSend = ParamUtil.getString(requestOrg, "timeSend");

				boolean advSearch = ParamUtil.getBoolean(requestOrg, "adv", false);
				boolean isDTND = ParamUtil.getBoolean(requestOrg, "isDTND", false);
				boolean isDTNDCam = ParamUtil.getBoolean(requestOrg, "isDTNDCam", false);
				
				//Edit by Dungnv
				String daKy = ParamUtil.getString(requestOrg, "daKy", StringPool.BLANK);
				
				if (advSearch) {

					String[] timeShipS = timeShip.replaceAll("%2F", "/").split(",");

					if (timeShipS.length > 0) {
						try {
							shipDateFromStart = FormatData.parseDateToTringType3(new Date(Long.valueOf(timeShipS[0])));
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							shipDateFromEnd = FormatData.parseDateToTringType3(new Date(Long.valueOf(timeShipS[1])));
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					
					String[] timeSendS = timeSend.replaceAll("%2F", "/").split(",");
					
					if (timeSendS.length > 0) {
						try {
							shipDateToStart = FormatData.parseDateToTringType3(new Date(Long.valueOf(timeSendS[0])));
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							shipDateToEnd = FormatData.parseDateToTringType3(new Date(Long.valueOf(timeSendS[1])));
						} catch (Exception e) {
							// TODO: handle exception
						}
					}

					return writeJSON(resourceRequest, resourceResponse,
							JSONProviderUtils.getHistoryTable(themeDisplay, documentTypeCode, maritimeCodeOwner,
									portCode, lastPortCode, shipName, stateCodeAdv, callSign, imoAdv, positionCodeAdv,
									shipDateFromStart, shipDateFromEnd, StringPool.BLANK, StringPool.BLANK,
									documentStatusCode, arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
									nameDepartureShipAgency, maritimeCodeAdv, shipDateToStart, shipDateToEnd,
									maBanKhai, daily, maritimeCodeNext, start, end, isDTND, isDTNDCam, daKy.trim()));

				} else {
					return writeJSON(resourceRequest, resourceResponse,
							JSONProviderUtils.getHistoryTable(themeDisplay, documentTypeCode, maritimeCode, portCode,
									lastPortCode, shipName, stateCode, callSign, imo, shipPosition, StringPool.BLANK,
									StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, documentStatusCode,
									arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
									nameDepartureShipAgency, portRegionCode, StringPool.BLANK, StringPool.BLANK,
									maBanKhai, daily, maritimeCodeNext, start, end, isDTND, isDTNDCam, daKy.trim()));
				}

			} else if (resourceID.equals("getPhanHoiTuCoQuanChuyenNganhTable")) {

				long documentName = ParamUtil.getLong(requestOrg, "documentName", 1604621);

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils
						.getPhanHoiTuCoQuanChuyenNganhTable(themeDisplay.getLocale(), documentName, documentYear));

			} else if (resourceID.equals("exportPDFDetail")) {

				long documentName = ParamUtil.getLong(requestOrg, "documentName", 1604621);
				String ministryCode = ParamUtil.getString(requestOrg, "ministryCode");

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.exportPDFDetail(themeDisplay.getLocale(),
						documentName, documentYear, ministryCode, request));

			} else if (resourceID.equals("exportPDFDetailThanhPhan")) {

				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);
				String requestCode = ParamUtil.getString(requestOrg, "requestCode", StringPool.BLANK);

				if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT1JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT2JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					return writeJSON(resourceRequest, resourceResponse, TT16JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					return writeJSON(resourceRequest, resourceResponse, TT17JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT3JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT4JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT5JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT6JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT7JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					return writeJSON(resourceRequest, resourceResponse, TT8JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					return writeJSON(resourceRequest, resourceResponse, TT9JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					return writeJSON(resourceRequest, resourceResponse, TT10JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					return writeJSON(resourceRequest, resourceResponse, TT11JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT14JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT15JSONProvider.exportPDFDetailThanhPhan(
							themeDisplay.getLocale(), documentName, documentYear, messageType, requestCode, request));
				}

			} else if (resourceID.equals("getThanhPhanHoSo")) {

				long documentName = ParamUtil.getLong(requestOrg, "documentName", 0);

				if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT1JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT2JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					return writeJSON(resourceRequest, resourceResponse, TT16JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					return writeJSON(resourceRequest, resourceResponse, TT17JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT3JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT4JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT5JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT6JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT7JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					return writeJSON(resourceRequest, resourceResponse, TT8JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					return writeJSON(resourceRequest, resourceResponse, TT9JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					return writeJSON(resourceRequest, resourceResponse, TT10JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					return writeJSON(resourceRequest, resourceResponse, TT11JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT14JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT15JSONProvider.getThanhPhanHoSo(themeDisplay,
							documentType, documentName, documentYear, requestOrg));
				}

			} else if (resourceID.equals("getFileThanhPhanVersionList")) {

				long documentName = ParamUtil.getLong(requestOrg, "documentName", 0);

				if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT1JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT2JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					return writeJSON(resourceRequest, resourceResponse, TT16JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					return writeJSON(resourceRequest, resourceResponse, TT17JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT3JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT4JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT5JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT6JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT7JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					return writeJSON(resourceRequest, resourceResponse, TT8JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					return writeJSON(resourceRequest, resourceResponse, TT9JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					return writeJSON(resourceRequest, resourceResponse, TT10JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					return writeJSON(resourceRequest, resourceResponse, TT11JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT14JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT15JSONProvider.getFileThanhPhanVersionList(
							themeDisplay, messageType, documentName, documentYear, request));
				}

			} else if (resourceID.equals("getThongTinKhaiBaoTable")) {

				long documentName = ParamUtil.getLong(requestOrg, "documentName", 0);

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getThongTinKhaiBaoTable(
						themeDisplay.getLocale(), documentType, documentName, documentYear, request, themeDisplay));

			} else if (resourceID.equals("getDocTypes")) {

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getDocTypes(themeDisplay));

			} else if (resourceID.equals("getFilterADVDataAPI")) {

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getFilterADVDataAPI(themeDisplay));

			} else if (resourceID.equals("checkThanhPhanActionButton")) {
				long documentName = ParamUtil.getLong(requestOrg, "documentName", 0);
				int roleType = ParamUtil.getInteger(requestOrg, "roleType");

				String requestCode = ParamUtil.getString(requestOrg, "REQUEST_CODE");

				if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT1JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT2JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					return writeJSON(resourceRequest, resourceResponse, TT16JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					return writeJSON(resourceRequest, resourceResponse, TT17JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT3JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT4JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT5JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT6JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					return writeJSON(resourceRequest, resourceResponse, TT7JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					return writeJSON(resourceRequest, resourceResponse, TT8JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					return writeJSON(resourceRequest, resourceResponse, TT9JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					return writeJSON(resourceRequest, resourceResponse, TT10JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					return writeJSON(resourceRequest, resourceResponse, TT11JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT14JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
					return writeJSON(resourceRequest, resourceResponse, TT15JSONProvider.checkThanhPhanActionButton(
							themeDisplay, documentName, documentYear, messageType, roleType, requestCode, request));
				}

			} else if (resourceID.equals("getGiayToXuatTrinhPhuongTien")) {
				String documentTypeCode = ParamUtil.getString(requestOrg, "documentTypeCode", "");
				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils
						.getGiayToXuatTrinhPhuongTien(themeDisplay, documentTypeCode, documentName, documentYear));
			} else if (resourceID.equals("getGiayToXuatTrinh_39")) {
				String documentTypeCode = ParamUtil.getString(requestOrg, "documentTypeCode", "");
				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getGiayToXuatTrinh_39(themeDisplay,
						documentTypeCode, documentName, documentYear));
			} else if (resourceID.equals("getGiayToXuatTrinh_40")) {
				String documentTypeCode = ParamUtil.getString(requestOrg, "documentTypeCode", "");
				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getGiayToXuatTrinh_40(themeDisplay,
						documentTypeCode, documentName, documentYear));
			} else if (resourceID.equals("getGiayToXuatTrinh_48")) {
				String documentTypeCode = ParamUtil.getString(requestOrg, "documentTypeCode", "");
				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getGiayToXuatTrinh_48(themeDisplay,
						documentTypeCode, documentName, documentYear));
			} else if (resourceID.equals("getGiayToXuatTrinh_49")) {
				String documentTypeCode = ParamUtil.getString(requestOrg, "documentTypeCode", "");
				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getGiayToXuatTrinh_49(themeDisplay,
						documentTypeCode, documentName, documentYear));
			} else if (resourceID.equals("getGiayToXuatTrinh_50")) {
				String documentTypeCode = ParamUtil.getString(requestOrg, "documentTypeCode", "");
				int documentName = ParamUtil.getInteger(requestOrg, "documentName", 0);

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getGiayToXuatTrinh_50(themeDisplay,
						documentTypeCode, documentName, documentYear));
			} else if (resourceID.equals("getMessageType23EXT")) {
				long documentName = ParamUtil.getLong(requestOrg, "documentName", 0);
				int roleType = ParamUtil.getInteger(requestOrg, "roleType");

				String requestCode = ParamUtil.getString(requestOrg, "REQUEST_CODE");

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getMessageType23EXT(themeDisplay,
						documentName, documentYear, messageType, roleType, requestCode, request));

			} else if (resourceID.equals("getLenhDieuDongEXT")) {
				long documentName = ParamUtil.getLong(requestOrg, "documentName", 0);

				return writeJSON(resourceRequest, resourceResponse,
						JSONProviderUtils.getLenhDieuDongEXT(themeDisplay, documentName, documentYear, request));

			} else if (resourceID.equals("getMessageType60EXT")) {
				long documentName = ParamUtil.getLong(requestOrg, "documentName", 0);
				int roleType = ParamUtil.getInteger(requestOrg, "roleType");

				String requestCode = ParamUtil.getString(requestOrg, "REQUEST_CODE");

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getMessageType60EXT(themeDisplay,
						documentName, documentYear, messageType, roleType, requestCode, request));

			} else if (resourceID.equals("getMessageType60EXTTable")) {
				long documentName = ParamUtil.getLong(requestOrg, "documentName", 0);
				int roleType = ParamUtil.getInteger(requestOrg, "roleType");

				String requestCode = ParamUtil.getString(requestOrg, "REQUEST_CODE");

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getMessageType60EXTTable(themeDisplay,
						documentName, documentYear, messageType, roleType, requestCode, request));

			} else if (resourceID.equals("getMessageType90EXT")) {
				long documentName = ParamUtil.getLong(requestOrg, "documentName", 0);
				int roleType = ParamUtil.getInteger(requestOrg, "roleType");

				String requestCode = ParamUtil.getString(requestOrg, "REQUEST_CODE");

				return writeJSON(resourceRequest, resourceResponse, JSONProviderUtils.getMessageType90EXT(themeDisplay,
						documentName, documentYear, messageType, roleType, requestCode, request));

			} else {



			}
		} catch (Exception e) {

			log.error(e.getMessage());

		}
		return super.serveResource(resourceRequest, resourceResponse);
	}

	public ResponseEntity<?> processAction(ActionRequest actionRequest, ActionResponse actionResponse)  {
		try {

			String actionName = ParamUtil.getString(actionRequest, ActionRequest.ACTION_NAME);
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);

			UploadPortletRequest requestUpload = (UploadPortletRequest)(actionRequest);

			String documentType = ParamUtil.getString(requestUpload, ChuyenDichTrangThaiUtils.DOCUMENT_TYPE, "");

			if (actionName.equals("actionKeHoach")) {

				if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH)) {
					return writeJSON(actionRequest, actionResponse,
							TT1ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH)) {
					return writeJSON(actionRequest, actionResponse,
							TT2ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					return writeJSON(actionRequest, actionResponse,
							TT16ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					return writeJSON(actionRequest, actionResponse,
							TT17ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					return writeJSON(actionRequest, actionResponse,
							TT3ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					return writeJSON(actionRequest, actionResponse,
							TT4ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					return writeJSON(actionRequest, actionResponse,
							TT5ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					return writeJSON(actionRequest, actionResponse,
							TT6ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					return writeJSON(actionRequest, actionResponse,
							TT7ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					return writeJSON(actionRequest, actionResponse,
							TT8ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					return writeJSON(actionRequest, actionResponse,
							TT9ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					return writeJSON(actionRequest, actionResponse,
							TT10ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					return writeJSON(actionRequest, actionResponse,
							TT11ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)) {
					return writeJSON(actionRequest, actionResponse,
							TT14ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
					return writeJSON(actionRequest, actionResponse,
							TT15ActionProvider.actionKeHoach(themeDisplay, requestUpload, actionRequest));
				}

			}
			if (actionName.equals("actionThuTuc")) {

				if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH)) {
					return writeJSON(actionRequest, actionResponse,
							TT1ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH)) {
					return writeJSON(actionRequest, actionResponse,
							TT2ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				}
				if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					return writeJSON(actionRequest, actionResponse,
							TT16ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					return writeJSON(actionRequest, actionResponse,
							TT17ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					return writeJSON(actionRequest, actionResponse,
							TT3ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					return writeJSON(actionRequest, actionResponse,
							TT4ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					return writeJSON(actionRequest, actionResponse,
							TT5ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					return writeJSON(actionRequest, actionResponse,
							TT6ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					return writeJSON(actionRequest, actionResponse,
							TT7ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					return writeJSON(actionRequest, actionResponse,
							TT8ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					return writeJSON(actionRequest, actionResponse,
							TT9ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					return writeJSON(actionRequest, actionResponse,
							TT10ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					return writeJSON(actionRequest, actionResponse,
							TT11ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)) {
					return writeJSON(actionRequest, actionResponse,
							TT14ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				} else if (documentType.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
					return writeJSON(actionRequest, actionResponse,
							TT15ActionProvider.actionThuTuc(themeDisplay, requestUpload, actionRequest));
				}

			} else {



			}
		} catch (Exception e) {

		}
		return super.processAction(actionRequest, actionResponse);
	}

}
