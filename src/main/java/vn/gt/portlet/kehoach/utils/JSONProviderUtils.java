package vn.gt.portlet.kehoach.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import org.json.JSONException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.Role;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.core.ThemeDisplay;

import vn.gt.constant.Constants;
import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmDocType;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPort;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import com.fds.nsw.nghiepvu.model.DmState;
import com.fds.nsw.nghiepvu.model.DmUnitGeneral;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmDocTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmUnitGeneralLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmCertificate;
import com.fds.nsw.nghiepvu.model.DmGtBusinessType;
import com.fds.nsw.nghiepvu.model.DmGtShipPosition;
import com.fds.nsw.nghiepvu.model.DmGtStatus;
import com.fds.nsw.nghiepvu.model.DmMinistry;
import vn.gt.dao.danhmucgt.service.DmCertificateLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGTBusinessTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGTShipPositionLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmMinistryLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.InterfaceRequest;
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderChanelLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewDetailsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultCertificate;
import com.fds.nsw.nghiepvu.model.ResultCompletion;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import com.fds.nsw.nghiepvu.model.ResultMinistry;
import com.fds.nsw.nghiepvu.model.ResultNotification;
import com.fds.nsw.nghiepvu.model.TempDebitnote;

import vn.gt.dao.result.service.ResultCertificateLocalServiceUtil;
import vn.gt.dao.result.service.ResultCompetionLocalServiceUtil;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.dao.result.service.ResultMinistryLocalServiceUtil;
import vn.gt.dao.result.service.ResultNotificationLocalServiceUtil;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.portlet.Utils;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.BaoCaoConstant;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiBanKhai;
import vn.gt.tichhop.message.TrangThaiBanKhaiQuaCanh;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.ConvertUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class JSONProviderUtils {


	

	public static JSONObject getNotifications(ThemeDisplay themeDisplay) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			UserPort portDefault = UserPortLocalServiceUtil
					.findByUserId(themeDisplay.getUserId());

			String maritimeCode = StringPool.BLANK;

			if (Validator.isNotNull(portDefault)
					&& Validator.isNull(maritimeCode)) {
				maritimeCode = portDefault.getPortCode();
			}

			List<ResultNotification> lstNotifications = new ArrayList<ResultNotification>();

			lstNotifications = ResultNotificationLocalServiceUtil
					.findByMaritimeCodeOrderbyLastestDate(maritimeCode);

			List<ResultNotification> lstNotificationResults = new ArrayList<ResultNotification>();

			for (ResultNotification resultNotification : lstNotifications) {

				if (resultNotification.getRemarks().length() > 0) {
					lstNotificationResults.add(resultNotification);
				}

			}

			JSONArray arrayData = JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil
							.looseSerialize(lstNotificationResults));

			for (int i = 0; i < arrayData.length(); i++) {
				JSONObject currentObject = arrayData.getJSONObject(i);
				DmGtBusinessType businessType = DmGTBusinessTypeLocalServiceUtil
						.getByBusinessTypeCode(currentObject
								.getInt("businessTypeCode"));
				if (Validator.isNotNull(businessType)) {
					currentObject.put("nameThanhPhan",
							businessType.getBusinessTypeNameVN());
				} else {
					currentObject.put("nameThanhPhan", StringPool.BLANK);
				}

				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								currentObject.getLong("documentName"),
								currentObject.getInt("documentYear"));

				currentObject.put("documentTypeCode",
						tempDocument.getDocumentTypeCode());
				currentObject.put("documentRequestState",
						tempDocument.getRequestState());
				currentObject.put("documentStatusCode",
						tempDocument.getDocumentStatusCode());

			}

			result.put("data", arrayData);

		} catch (JSONException e) {
			result.put("total", 0);
		}
		return result;
	}

	public static JSONObject getHoSoKeHoachTable(ThemeDisplay themeDisplay,
			String requestState, String documentTypeCode, String maritimeCode,
			String maritimeCodeNext, String shipName, String positionCode,
			String portRegionCode, String mabankhai, String stateCode,
			String shipDateFromStart, String shipDateFromEnd,
			String shipDateToStart, String shipDateToEnd, String callSign,
			String imo, String ngayLamThuTucFrom, String ngayLamThuTucTo,
			String nameOfShipownersAgents, int start, int end) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			if (documentTypeCode.equals("0")) {
				documentTypeCode = StringPool.BLANK;
			}

			String maritimeName = "";
			String maritimeReferent = "";
			UserPort portDefault = UserPortLocalServiceUtil
					.findByUserId(themeDisplay.getUserId());

			if (Validator.isNotNull(portDefault)
					&& Validator.isNull(maritimeCode)) {
				maritimeCode = portDefault.getPortCode();
				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
						.getByMaritimeCode(maritimeCode);
				maritimeName = Validator.isNotNull(dmMaritime) ? dmMaritime
						.getMaritimeNameVN() : StringPool.BLANK;
				maritimeReferent = Validator.isNotNull(dmMaritime) ? dmMaritime
						.getMaritimeReference() : StringPool.BLANK;
			}

			List<TempDocument> data = TempDocumentLocalServiceUtil
					.findTempDocumentByKeHoach(requestState, documentTypeCode,
							maritimeCode, shipName, positionCode,
							portRegionCode, mabankhai, stateCode,
							shipDateFromStart, shipDateFromEnd,
							shipDateToStart, shipDateToEnd, callSign, imo,
							ngayLamThuTucFrom, ngayLamThuTucTo,
							nameOfShipownersAgents, maritimeCodeNext, start,
							end);

			long total = TempDocumentLocalServiceUtil
					.countTempDocumentByKeHoach(requestState, documentTypeCode,
							maritimeCode, shipName, positionCode,
							portRegionCode, mabankhai, stateCode,
							shipDateFromStart, shipDateFromEnd,
							shipDateToStart, shipDateToEnd, callSign, imo,
							ngayLamThuTucFrom, ngayLamThuTucTo,
							nameOfShipownersAgents, maritimeCodeNext);

			String ghichu = "";

			JSONArray dataArray = JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(data));
			JSONObject currentObject = null;

			for (int i = 0; i < dataArray.length(); i++) {

				currentObject = dataArray.getJSONObject(i);
				ghichu = "";

				if (currentObject.getInt("requestState") == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG
						|| currentObject.getInt("requestState") == TrangThaiBanKhai.DA_CAP_LENH_DIEU_DONG) {

					List<IssueShiftingOrder> issueShiftingOrders = new ArrayList<IssueShiftingOrder>(
							IssueShiftingOrderLocalServiceUtil
									.findByDocumentYearAndDocumentYearOrderByColumn(
											currentObject
													.getLong("documentName"),
											currentObject
													.getInt("documentYear"),
											KeyParams.ID,
											KeyParams.ORDER_BY_DESC));

					if (Validator.isNotNull(issueShiftingOrders)
							&& (issueShiftingOrders.size() > 0)) {

						IssueShiftingOrder issueShiftingOrder = issueShiftingOrders
								.get(0);

						if (issueShiftingOrder.getRequestCode().length() > 0
								&& issueShiftingOrder.getStampStatus() == 11
								&& issueShiftingOrder.getIsApproval() == 0) {
							if (currentObject.getInt("requestState") == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG) {
								ghichu = "\u0110\u00E3 chuy\u1EC3n l\u00E3nh \u0111\u1EA1o, ch\u1EDD k\u00FD.";
							} else if (currentObject.getInt("requestState") == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG) {
								ghichu = "L\u1EC7nh \u0111i\u1EC1u \u0111\u1ED9ng c\u1EA5p l\u1EA1i \u0111\u00E3 chuy\u1EC3n l\u00E3nh \u0111\u1EA1o, ch\u1EDD k\u00FD.";
							}
						} else if (issueShiftingOrder.getRequestCode().length() > 0
								&& issueShiftingOrder.getStampStatus() == 1) {
							if (currentObject.getInt("requestState") == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG) {
								ghichu = "\u0110\u00E3 chuy\u1EC3n v\u0103n th\u01B0, ch\u1EDD \u0111\u00F3ng d\u1EA5u.";
							} else if (currentObject.getInt("requestState") == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG) {
								ghichu = "L\u1EC7nh \u0111i\u1EC1u \u0111\u1ED9ng c\u1EA5p l\u1EA1i \u0111\u00E3 chuy\u1EC3n v\u0103n th\u01B0, ch\u1EDD \u0111\u00F3ng d\u1EA5u.";
							}
						} else if (issueShiftingOrder.getRequestCode().length() > 0
								&& issueShiftingOrder.getStampStatus() == 0
								&& issueShiftingOrder.getIsCancel() == 0
								&& issueShiftingOrder.getCancelNote().length() > 0) {
							ghichu = "L\u00FD do tr\u1EA3 l\u1EA1i: "
									+ issueShiftingOrder.getCancelNote();
						} else {
							ghichu = "";
						}

					} else {
						ghichu = "";
					}
					currentObject.put("ghichu", ghichu);
				} else {
					currentObject.put("ghichu", StringPool.BLANK);
				}

				DmPort dmPort = DmPortLocalServiceUtil.fetchByPortCodeLoCode(
						Validator.isNotNull(currentObject.getString("portCode")) ? currentObject.getString("portCode") : "",
						currentObject.getString("maritimeCode"));

				if (Validator.isNotNull(dmPort)) {
					currentObject.put("portName", dmPort.getPortName());
				} else {
					currentObject.put("portName",
							Validator.isNotNull(currentObject.getString("portCode")) ? currentObject.getString("portCode") : "");
				}

			}

			result.put("data", dataArray);
			result.put("total", total);
			result.put("maritimeName", maritimeName);
			result.put("maritimeReferent", maritimeReferent);
			result.put("canBoPheDuyet", themeDisplay.getUser()
					.getEmailAddress());

		} catch (JSONException e) {
			result.put("total", 0);
		}
		return result;
	}

	public static JSONObject counterHoSoKeHoachTable(ThemeDisplay themeDisplay,
			String requestState, String documentTypeCode, String maritimeCode,
			String shipName, String positionCode, String portRegionCode,
			String mabankhai, String stateCode, String shipDateFromStart,
			String shipDateFromEnd, String shipDateToStart,
			String shipDateToEnd, String callSign, String imo,
			String ngayLamThuTucFrom, String ngayLamThuTucTo, int start,
			int end, int rootIndex, int childIndex) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {

			if (documentTypeCode.equals("0")) {
				documentTypeCode = StringPool.BLANK;
			}

			UserPort portDefault = UserPortLocalServiceUtil
					.findByUserId(themeDisplay.getUserId());

			if (Validator.isNotNull(portDefault)
					&& Validator.isNull(maritimeCode)) {
				maritimeCode = portDefault.getPortCode();
			}

			long total = TempDocumentLocalServiceUtil
					.countTempDocumentByKeHoach(requestState, documentTypeCode,
							maritimeCode, shipName, positionCode,
							portRegionCode, mabankhai, stateCode,
							shipDateFromStart, shipDateFromEnd,
							shipDateToStart, shipDateToEnd, callSign, imo,
							ngayLamThuTucFrom, ngayLamThuTucTo);

			result.put("total", total);
			result.put("rootIndex", rootIndex);
			result.put("childIndex", childIndex);

		} catch (Exception e) {

			result.put("total", "_ _");
			result.put("rootIndex", rootIndex);
			result.put("childIndex", childIndex);
		}
		return result;
	}

	public static JSONObject counterHoSoThuTucTable(ThemeDisplay themeDisplay,
			String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode,
			String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd,
			String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency,
			String nameArrivalShipAgency, String departureShipAgency,
			String nameDepartureShipAgency, String portRegionCode,
			String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
			int start, int end, int rootIndex, int childIndex, boolean isDTND,
			boolean isDTNDCam) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {

			if (documentTypeCode.equals("0")) {
				documentTypeCode = StringPool.BLANK;
			}

			UserPort portDefault = UserPortLocalServiceUtil
					.findByUserId(themeDisplay.getUserId());

			if (Validator.isNotNull(portDefault)
					&& Validator.isNull(maritimeCode)) {
				maritimeCode = portDefault.getPortCode();
			}

			long total = TempDocumentLocalServiceUtil
					.countDanhSachHoSoRoleThuTuc(documentTypeCode,
							maritimeCode, portCode, lastPortCode, shipName,
							stateCode, callSign, imo, shipPosition,
							shipDateFromStart, shipDateFromEnd,
							shipDateToStart, shipDateToEnd, documentStatusCode,
							arrivalShipAgency, nameArrivalShipAgency,
							departureShipAgency, nameDepartureShipAgency,
							portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo,
							maBanKhai, isDTND, isDTNDCam);

			result.put("total", total);
			result.put("rootIndex", rootIndex);
			result.put("childIndex", childIndex);

		} catch (Exception e) {

			result.put("total", "_ _");
			result.put("rootIndex", rootIndex);
			result.put("childIndex", childIndex);
		}
		return result;
	}

	public static JSONObject getHoSoThuTucTable(ThemeDisplay themeDisplay,
			String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode,
			String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd,
			String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency,
			String nameArrivalShipAgency, String departureShipAgency,
			String nameDepartureShipAgency, String portRegionCode,
			String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
			String nameOfShipownersAgents, String maritimeCodeNext, int start,
			int end, boolean isDTND, boolean isDTNDCam) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {

			if (documentTypeCode.equals("0")) {
				documentTypeCode = StringPool.BLANK;
			}

			String maritimeName = "";
			String maritimeReferent = "";
			UserPort portDefault = UserPortLocalServiceUtil
					.findByUserId(themeDisplay.getUserId());

			if (Validator.isNotNull(portDefault)
					&& Validator.isNull(maritimeCode)) {
				maritimeCode = portDefault.getPortCode();
				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
						.getByMaritimeCode(maritimeCode);
				maritimeName = Validator.isNotNull(dmMaritime) ? dmMaritime
						.getMaritimeNameVN() : StringPool.BLANK;
				maritimeReferent = Validator.isNotNull(dmMaritime) ? dmMaritime
						.getMaritimeReference() : StringPool.BLANK;
			}
			List<TempDocument> data = TempDocumentLocalServiceUtil
					.findDanhSachHoSoRoleThuTuc(documentTypeCode, maritimeCode,
							portCode, lastPortCode, shipName, stateCode,
							callSign, imo, shipPosition, shipDateFromStart,
							shipDateFromEnd, shipDateToStart, shipDateToEnd,
							documentStatusCode, arrivalShipAgency,
							nameArrivalShipAgency, departureShipAgency,
							nameDepartureShipAgency, portRegionCode,
							ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai,
							nameOfShipownersAgents, maritimeCodeNext, start,
							end, isDTND, isDTNDCam);

			long total = TempDocumentLocalServiceUtil
					.countDanhSachHoSoRoleThuTuc(documentTypeCode,
							maritimeCode, portCode, lastPortCode, shipName,
							stateCode, callSign, imo, shipPosition,
							shipDateFromStart, shipDateFromEnd,
							shipDateToStart, shipDateToEnd, documentStatusCode,
							arrivalShipAgency, nameArrivalShipAgency,
							departureShipAgency, nameDepartureShipAgency,
							portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo,
							maBanKhai, nameOfShipownersAgents,
							maritimeCodeNext, isDTND, isDTNDCam);

			String ghichu = "";

			JSONArray dataArray = JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(data));
			JSONObject currentObject = null;

			for (int i = 0; i < dataArray.length(); i++) {

				currentObject = dataArray.getJSONObject(i);
				ghichu = "";

				if (currentObject.getInt("documentStatusCode") == TrangThaiBanKhaiQuaCanh.DE_NGHI_CAP_GIAY_PHEP
						|| currentObject.getInt("documentStatusCode") == TrangThaiBanKhai.PHE_DUYET_HOAN_THANH_THU_TUC) {

					if (currentObject.getString("documentTypeCode").equals(
							MessageType.LOAT_THU_TUC_QUA_CANH)) {

						List<IssuePermissionForTransit> issuePermissionForTransits = IssuePermissionForTransitLocalServiceUtil
								.findByDocumentYearAndDocumentYearOrderByColumn(
										currentObject.getLong("documentName"),
										currentObject.getInt("documentYear"),
										KeyParams.ID, KeyParams.ORDER_BY_DESC);
						if (Validator.isNotNull(issuePermissionForTransits)
								&& (issuePermissionForTransits.size() > 0)) {

							IssuePermissionForTransit issuePermissionForTransit = issuePermissionForTransits
									.get(0);

							if (issuePermissionForTransit.getRequestCode()
									.length() > 0
									&& issuePermissionForTransit
											.getStampStatus() == 11
									&& issuePermissionForTransit
											.getIsApproval() == 0) {
								ghichu = "\u0110\u00E3 chuy\u1EC3n l\u00E3nh \u0111\u1EA1o, ch\u1EDD k\u00FD.";
							} else if (issuePermissionForTransit
									.getRequestCode().length() > 0
									&& issuePermissionForTransit
											.getStampStatus() == 1) {
								ghichu = "\u0110\u00E3 chuy\u1EC3n v\u0103n th\u01B0, ch\u1EDD \u0111\u00F3ng d\u1EA5u.";
							} else if (issuePermissionForTransit
									.getRequestCode().length() > 0
									&& issuePermissionForTransit
											.getStampStatus() == 0
									&& issuePermissionForTransit.getIsCancel() == 0
									&& issuePermissionForTransit
											.getCancelNote().length() > 0) {
								ghichu = "L\u00FD do tr\u1EA3 l\u1EA1i: "
										+ issuePermissionForTransit
												.getCancelNote();
							} else {
								ghichu = "";
							}

						} else {
							ghichu = "";
						}
						currentObject.put("ghichu", ghichu);
					} else {

						List<IssuePortClearance> issuePortClearances = IssuePortClearanceLocalServiceUtil
								.findByDocumentYearAndDocumentYearOrderByColumn(
										currentObject.getLong("documentName"),
										currentObject.getInt("documentYear"),
										KeyParams.ID, KeyParams.ORDER_BY_DESC);

						if (Validator.isNotNull(issuePortClearances)
								&& (issuePortClearances.size() > 0)) {

							IssuePortClearance issuePortClearance = issuePortClearances
									.get(0);

							if (issuePortClearance.getRequestCode().length() > 0
									&& issuePortClearance.getStampStatus() == 11
									&& issuePortClearance.getIsApproval() == 0) {
								ghichu = "\u0110\u00E3 chuy\u1EC3n l\u00E3nh \u0111\u1EA1o, ch\u1EDD k\u00FD.";
							} else if (issuePortClearance.getRequestCode()
									.length() > 0
									&& issuePortClearance.getStampStatus() == 1) {
								ghichu = "\u0110\u00E3 chuy\u1EC3n v\u0103n th\u01B0, ch\u1EDD \u0111\u00F3ng d\u1EA5u.";
							} else if (issuePortClearance.getRequestCode()
									.length() > 0
									&& issuePortClearance.getStampStatus() == 0
									&& issuePortClearance.getIsCancel() == 0
									&& issuePortClearance.getCancelNote()
											.length() > 0) {
								ghichu = "L\u00FD do tr\u1EA3 l\u1EA1i: "
										+ issuePortClearance.getCancelNote();
							} else {
								ghichu = "";
							}

						} else {
							ghichu = "";
						}
						currentObject.put("ghichu", ghichu);

					}

				} else {
					currentObject.put("ghichu", StringPool.BLANK);
				}

				DmPort dmPort = DmPortLocalServiceUtil.fetchByPortCodeLoCode(
						currentObject.getString("portCode"),
						currentObject.getString("maritimeCode"));

				if (Validator.isNotNull(dmPort)) {
					currentObject.put("portName", dmPort.getPortName());
				} else {
					currentObject.put("portName",
							currentObject.getString("portCode"));
				}

			}

			result.put("data", dataArray);
			result.put("total", total);
			result.put("maritimeName", maritimeName);
			result.put("maritimeReferent", maritimeReferent);
			result.put("canBoPheDuyet", themeDisplay.getUser()
					.getEmailAddress());

		} catch (Exception e) {

			result.put("total", 0);
		}
		return result;
	}

	public static JSONObject getPhanHoiTuCoQuanChuyenNganhTable(Locale locale,
			long documentName, int documentYear) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {

			List<ResultMinistry> data = ResultMinistryLocalServiceUtil
					.findDistinctMinistryCode(documentName, documentYear);

			String ministry = StringPool.BLANK;
			JSONArray dataArray = JSONFactoryUtil.createJSONArray();
			JSONObject dataObject;
			log.info("data: " + data);
			for (int i = 0; i < data.size(); i++) {

				dataObject = JSONFactoryUtil.createJSONObject();

				ministry = String.valueOf(data.get(i));
				DmMinistry dmMinistry = DmMinistryLocalServiceUtil
						.findByMinistryCode(ministry);
				ResultMinistry instance2 = ResultMinistryLocalServiceUtil
						.findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCodePhanHoi(
								documentName, documentYear, ministry, "99, 26");
				String status = StringPool.BLANK;
				if (instance2 != null
						&& instance2.getResponse().trim().equals("21")) {
					status = "Ch\u1ea5p nh\u1eadn";
				} else if (instance2 != null
						&& instance2.getResponse().trim().equals("20")) {
					status = "Th\u00F4ng b\u00E1o l\u00E0m th\u1EE7 t\u1EE5c tr\u1EF1c ti\u1EBFp";
				} else if (instance2 != null
						&& instance2.getResponse().trim().equals("22")) {
					status = "T\u1eeb ch\u1ed1i";
				} else if (instance2 != null
						&& instance2.getResponse().trim().equals("23")) {
					status = "Ph\u00ea duy\u1ec7t";
				} else if (instance2 != null
						&& instance2.getResponse().trim().equals("24")) {
					status = "H\u1ee7y th\u1ee7 t\u1ee5c";
				} else if (instance2 != null
						&& (instance2.getResponse().trim().equals("26")
								|| instance2.getResponse().trim().equals("27")
								|| instance2.getResponse().trim().equals("28")
								|| instance2.getResponse().trim().equals("29") || instance2
								.getResponse().trim().equals("25"))) {
					status = "Y\u00eau c\u1ea7u b\u1ed5 sung";
				} else if (instance2 != null
						&& (instance2.getResponse().trim().equals("35"))) {
					status = "D\u1EEBng th\u1EE7 t\u1EE5c \u0111i\u1EC7n t\u1EED \u0111\u1EC3 l\u00E0m gi\u1EA5y";
				}
				dataObject.put(
						"orgName",
						Validator.isNotNull(dmMinistry) ? dmMinistry
								.getMinistryNameVN() : StringPool.BLANK);
				dataObject.put("status", status);
				dataObject.put("documentName", documentName);
				dataObject.put("documentYear", documentYear);
				dataObject.put("ministryCode", URLEncoder.encode(ministry));

				dataArray.put(dataObject);
			}
			result.put("data", dataArray);
			result.put("total", dataArray.length());

		} catch (Exception e) {

			result.put("total", 0);
		}
		return result;
	}

	public static JSONObject exportPDFDetail(Locale locale, long documentName,
			int documentYear, String ministryCode, HttpServletRequest request) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
		String tenFileExport = StringPool.BLANK;
		try {
			if (Validator.isNotNull(ministryCode)) {

				long nanoTime = action.actionExportThongTinTau(documentName,
						documentYear, ministryCode);
				tenFileExport = nanoTime + "_"
						+ BaoCaoConstant.BAO_CAO_PHAN_HOI_TU_CQCN_EXPORT;
			}

			String UrlFile = request.getContextPath() + "/export/"
					+ tenFileExport;
			String UrlFileDowLoad = UrlFile.replace(".pdfs", ".pdf");

			result.put("url", UrlFile);
			result.put("download", UrlFileDowLoad);

		} catch (Exception e) {

			result.put("url", StringPool.BLANK);
			result.put("download", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject getThongTinKhaiBaoTable(Locale locale,
			String documentType, long documentName, int documentYear,
			HttpServletRequest request, ThemeDisplay themeDisplay) {
		// TODO Auto-generated method stub
		JSONObject resultData = JSONFactoryUtil.createJSONObject();
		JSONArray result = JSONFactoryUtil.createJSONArray();
		JSONObject object;
		List<ResultDeclaration> allResultDeclaration = ResultDeclarationLocalServiceUtil
				.findByDocumentNameAndDocumentYearOrderByBusinessOrder(
						documentName, documentYear);
		DmGtBusinessType dmGtBusinessType = null;
		DmGtStatus dmGtStatus = null;
		String remarks = "";

		// TempDocument tempDocument =
		// TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName,
		// documentYear);

		// //remove result notification
		// List<ResultNotification> resultNotifications =
		// ResultNotificationLocalServiceUtil.findByDocumentNameAnddocumentYear(documentName,
		// documentYear);
		//
		// for (ResultNotification resultNotification : resultNotifications) {
		// try {
		// ResultNotificationLocalServiceUtil.deleteResultNotification(resultNotification);
		// } catch (SystemException e) {
		// // TODO Auto-generated catch block
		// log.error(e.getMessage());
		// }
		// }

		for (ResultDeclaration resultDeclaration : allResultDeclaration) {

			dmGtBusinessType = DmGTBusinessTypeLocalServiceUtil
					.getByBusinessTypeCode(resultDeclaration
							.getBusinessTypeCode());

			int count = Utils.countBanKhai(
					String.valueOf(resultDeclaration.getDocumentName()),
					resultDeclaration.getDocumentYear(),
					dmGtBusinessType.getBusinessTypeCode());
			if (count == 0) {
				continue;
			}

			dmGtStatus = DmGtStatusLocalServiceUtil.findByStatusCode(
					resultDeclaration.getRequestState(), 3);

			// TODO
			if (Validator.isNotNull(resultDeclaration)
					&& (resultDeclaration.getBusinessTypeCode() == 50
							|| resultDeclaration.getBusinessTypeCode() == 51
							|| resultDeclaration.getBusinessTypeCode() == 52
							|| resultDeclaration.getBusinessTypeCode() == 53
							|| resultDeclaration.getBusinessTypeCode() == 32
							|| resultDeclaration.getBusinessTypeCode() == 42
							|| resultDeclaration.getBusinessTypeCode() == 54
							|| resultDeclaration.getBusinessTypeCode() == 55
							|| resultDeclaration.getBusinessTypeCode() == 56
							|| resultDeclaration.getBusinessTypeCode() == 57
							|| resultDeclaration.getBusinessTypeCode() == 58
							|| resultDeclaration.getBusinessTypeCode() == 20 || resultDeclaration
							.getBusinessTypeCode() == 10)) {
				// 54, 55, 56
				remarks = resultDeclaration.getRemarks();
			} else {
				remarks = InterfaceRequestLocalServiceUtil
						.getRemarksByRequestCode(resultDeclaration
								.getRequestCode());

				InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(resultDeclaration
								.getRequestCode());

				if (Validator.isNull(interfaceRequest)) {
					interfaceRequest = InterfaceRequestLocalServiceUtil
							.fetchByF_BY_documentNameRef(
									String.valueOf(documentName),
									String.valueOf(10));
				}

				if (Validator.isNull(interfaceRequest)) {
					if (Validator.isNotNull(resultDeclaration
							.getRemainingTime())) {
						if (resultDeclaration.getRemainingTime()
								.startsWith("-")) {
							remarks = "G\u1EEDi mu\u1ED9n";
						}
					}
				} else {
					if (Validator.isNull(remarks)) {
						remarks = resultDeclaration.getRemarks();
					}
				}
			}

			object = JSONFactoryUtil.createJSONObject();

			object.put("thoi_gian_khai_bao",
					resultDeclaration.getDeclarationTime());
			object.put(
					"ban_khai",
					Validator.isNotNull(dmGtBusinessType) ? dmGtBusinessType
							.getBusinessTypeNameVN() : StringPool.BLANK);
			object.put("lan_gui_cuoi", Validator.isNotNull(resultDeclaration
					.getLatestSend()) ? resultDeclaration.getLatestSend() : 0);
			object.put("so_gio_con_lai", Validator.isNotNull(resultDeclaration
					.getRemainingTime()) ? resultDeclaration.getRemainingTime()
					: StringPool.BLANK);

			if (Validator.isNotNull(dmGtStatus)
					&& dmGtStatus.getStatusCode() == 5) {
				object.put("trang_thai",
						"Y\u00EAu c\u1EA7u s\u1EEDa \u0111\u1ED5i b\u1ED5 sung");
			} else {
				object.put(
						"trang_thai",
						Validator.isNotNull(dmGtStatus) ? dmGtStatus
								.getStatusName() : StringPool.BLANK);
			}

			if (remarks.contains("] -")) {
				String[] userEmail = remarks.split("] -");
				if (userEmail.length > 0) {

					String userNameEmail = userEmail[0].substring(1);
					try {
						User user = UserLocalServiceUtil.getUserByEmailAddress(
								themeDisplay.getCompanyId(), userNameEmail);
						remarks = remarks.replaceAll(userNameEmail,
								user.getFullName());

					} catch (PortalException e) {
					} catch (SystemException e) {
					}
				}
			}

			object.put("ghi_chu", remarks);

			result.put(object);
		}

		ResultNotification notification = ResultNotificationLocalServiceUtil
				.findByBusinessTypeCodeOrderbyLastestDate(
						"05, 99, 9927, 9928, 9929, 9925, 9920", documentName,
						documentYear);

		resultData.put("data", result);
		resultData.put("noti_respone_time", "");
		resultData.put("noti_latest_date", "");

		result = JSONFactoryUtil.createJSONArray();
		object = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(notification)
				&& Validator.isNotNull(notification.getResponseTime())) {
			resultData
					.put("noti_respone_time",
							"PHẢN HỒI TỪ CẢNG VỤ: "
									+ (Validator.isNotNull(notification
											.getOfficerName()) ? notification
											.getOfficerName()
											: StringPool.BLANK)
									+ " - "
									+ (Validator.isNotNull(notification
											.getResponseTime()) ? FormatData
											.parseDateToTringType3(notification
													.getResponseTime())
											: StringPool.BLANK)
									+ " - "
									+ (Validator.isNotNull(notification
											.getResponse()) ? notification
											.getResponse() : StringPool.BLANK));
		}

		if (notification != null
				&& (notification.getBusinessTypeCode() == 05
						|| notification.getBusinessTypeCode() == 9927
						|| notification.getBusinessTypeCode() == 9928 || notification
						.getBusinessTypeCode() == 9929)) {
			if (notification.getLatestDate() != null
					&& notification.getRemarks().length() > 0) {
				resultData
						.put("noti_latest_date",
								"DOANH NGHIỆP GỬI YÊU CẦU SỬA ĐỔI BỔ SUNG: "
										+ (Validator.isNotNull(notification
												.getLatestDate()) ? ReportFunction
												.parserDateToString3LT(notification
																.getLatestDate())
												: StringPool.BLANK)
										+ " - "
										+ (Validator.isNotNull(notification
												.getRemarks()) ? (notification
												.getRemarks().equalsIgnoreCase(
														"NIL") ? "Mới gửi"
												: notification.getRemarks())
												: StringPool.BLANK));
			}
		} else if (notification != null
				&& notification.getBusinessTypeCode() == 99) {
			if (notification.getLatestDate() != null
					&& notification.getRemarks().length() > 0) {
				resultData
						.put("noti_latest_date",
								"DOANH NGHIỆP GỬI YÊU CẦU HỦY THỦ TỤC: "
										+ (Validator.isNotNull(notification
												.getLatestDate()) ? ReportFunction
												.parserDateToString3LT(notification
																.getLatestDate())
												: StringPool.BLANK)
										+ " - "
										+ (Validator.isNotNull(notification
												.getRemarks()) ? (notification
												.getRemarks().equalsIgnoreCase(
														"NIL") ? "Mới gửi"
												: notification.getRemarks())
												: StringPool.BLANK));
			}

		} else if (notification != null
				&& notification.getBusinessTypeCode() == 9920) {
			if (notification.getLatestDate() != null
					&& notification.getRemarks().length() > 0) {
				resultData
						.put("noti_latest_date",
								"DOANH NGHIỆP XUẤT TRÌNH: "
										+ (Validator.isNotNull(notification
												.getLatestDate()) ? ReportFunction
												.parserDateToString3LT(notification
																.getLatestDate())
												: StringPool.BLANK)
										+ " - "
										+ (Validator.isNotNull(notification
												.getRemarks()) ? (notification
												.getRemarks().equalsIgnoreCase(
														"NIL") ? "Mới gửi"
												: notification.getRemarks())
												: StringPool.BLANK));
			}
		}

		return resultData;
	}

	public static JSONArray getDocTypes(ThemeDisplay themeDisplay) {
		// TODO Auto-generated method stub
		JSONArray result = JSONFactoryUtil.createJSONArray();

		try {

			List<DmDocType> dmDocTypes = DmDocTypeLocalServiceUtil
					.getDmDocTypes(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			JSONObject object;

			User user = themeDisplay.getUser();

			boolean flagDT = false;

			boolean flagDTCam = false;

			for (Role role : user.getRoles()) {

				if (role.getName().equals("DTND_ROLE")) {
					flagDT = true;
				}

				if (role.getName().equals("DTND_ROLE_CAMPUCHIA")) {
					flagDTCam = true;
				}

			}

			for (DmDocType dmDocType : dmDocTypes) {

				object = JSONFactoryUtil.createJSONObject();

				object.put("typeCode", dmDocType.getDocumentTypeCode());
				object.put("code", dmDocType.getDocumentType());
				object.put("name", dmDocType.getDocumentTypeName());

				if (flagDT) {
					if (dmDocType.getDocumentType().equalsIgnoreCase(
							ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)
							|| dmDocType
									.getDocumentType()
									.equalsIgnoreCase(
											ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
						result.put(object);
					}
				}
				if (flagDTCam) {
					if (dmDocType.getDocumentType().equalsIgnoreCase(
							ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)
							|| dmDocType
									.getDocumentType()
									.equalsIgnoreCase(
											ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
						result.put(object);
					}
				}
				if (!flagDTCam && !flagDT) {
					if (!dmDocType.getDocumentType().equalsIgnoreCase(
							ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)
							&& !dmDocType
									.getDocumentType()
									.equalsIgnoreCase(
											ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
						result.put(object);
					}
				}
			}

		} catch (Exception e) {

			log.error(e.getMessage());
		}

		return result;
	}

	public static JSONObject getFilterADVDataAPI(ThemeDisplay themeDisplay) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		boolean checkRoleBGTVT = false;
		UserPort portDefault = UserPortLocalServiceUtil
				.findByUserId(themeDisplay.getUserId());

		String holdMaritimeCode = StringPool.BLANK;

		if (Validator.isNotNull(portDefault)) {
			holdMaritimeCode = portDefault.getPortCode();
			UserPort userPort = UserPortLocalServiceUtil
					.findByUserId(portDefault.getUserId());
			if (userPort == null) {
				checkRoleBGTVT = true;
			}
		}

		DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(holdMaritimeCode);

		if (Validator.isNotNull(dmMaritime)) {
			result.put("cang_vu_label", dmMaritime.getCityCode());
		} else {
			result.put("cang_vu_label", "");
		}
		result.put("roleBGTVT", checkRoleBGTVT);

		result.put("cang_vu_list", JSONFactoryUtil.createJSONArray());
		result.put("cang_tiep_theo", JSONFactoryUtil.createJSONArray());
		result.put("tau_den_roi_list", JSONFactoryUtil.createJSONArray());
		result.put("quoc_tich_list", JSONFactoryUtil.createJSONArray());

		try {
			List<DmMaritime> maritimes = DmMaritimeLocalServiceUtil.getAll();
			List<DmGtShipPosition> dmGTShipPositions = DmGTShipPositionLocalServiceUtil
					.getDmGTShipPositions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			List<DmState> states = DmStateLocalServiceUtil.getDmStates(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			JSONObject object;

			List<DmPortRegion> listGegions = DmPortRegionLocalServiceUtil
					.findPortRegionByPortRegionRef(portDefault.getPortCode());

			JSONArray arrayData = JSONFactoryUtil.createJSONArray();
			for (DmPortRegion maritime : listGegions) {
				object = JSONFactoryUtil.createJSONObject();

				object.put("code", maritime.getPortRegionCode());
				object.put("name", maritime.getPortRegionNameVN());

				arrayData.put(object);
			}
			result.put("cang_vu_list", arrayData);

			List<DmPort> dmPorts = DmPortLocalServiceUtil
					.findByLoCode(portDefault.getPortCode());

			arrayData = JSONFactoryUtil.createJSONArray();
			for (DmPort maritime : dmPorts) {
				object = JSONFactoryUtil.createJSONObject();

				object.put("code", maritime.getPortCode());
				object.put("name", maritime.getPortName());

				arrayData.put(object);
			}
			result.put("cang_tiep_theo", arrayData);

			arrayData = JSONFactoryUtil.createJSONArray();
			for (DmGtShipPosition dmGTShipPosition : dmGTShipPositions) {
				object = JSONFactoryUtil.createJSONObject();

				object.put("code", dmGTShipPosition.getPositionCode());
				object.put("name", dmGTShipPosition.getPositionName());

				arrayData.put(object);
			}

			result.put("tau_den_roi_list", arrayData);

			arrayData = JSONFactoryUtil.createJSONArray();
			for (DmState state : states) {
				object = JSONFactoryUtil.createJSONObject();

				object.put("code", state.getStateCode());
				object.put("name", state.getStateName());

				arrayData.put(object);
			}
			result.put("quoc_tich_list", arrayData);

		} catch (Exception e) {

			log.error(e.getMessage());
		}

		return result;
	}

	public static JSONObject getDetailHoSo(ThemeDisplay themeDisplay,
			long documentName, int documentYear) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {

			String maritimeName = "";
			String maritimeReferent = "";

			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.getByDocumentNameAndDocumentYear(documentName,
							documentYear);

			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(tempDocument.getMaritimeCode());

			maritimeName = Validator.isNotNull(dmMaritime) ? dmMaritime.getCityCode() : StringPool.BLANK;
			maritimeReferent = Validator.isNotNull(dmMaritime) ? dmMaritime
					.getMaritimeReference() : StringPool.BLANK;

			result = JSONFactoryUtil.createJSONObject(JSONFactoryUtil
					.looseSerialize(tempDocument));

			result.put("maritimeName", maritimeName);
			result.put("maritimeReferent", maritimeReferent);

			String documentType = tempDocument.getDocumentTypeCode();

			String canbopheduyet = themeDisplay.getUser().getEmailAddress();
			if (documentType.equalsIgnoreCase("QC")) {
				List<IssuePermissionForTransit> results = IssuePermissionForTransitLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(
								documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC);

				if (Validator.isNotNull(results) && results.size() > 0) {
					IssuePermissionForTransit issuePermissionForTransit = results
							.get(0);
					if (issuePermissionForTransit.getAttachedFile() > 0) {
						canbopheduyet = Validator
								.isNotNull(issuePermissionForTransit
										.getSignName()) ? issuePermissionForTransit
								.getSignName() : issuePermissionForTransit
								.getSignTitle();
					}
				}
			} else {
				List<IssuePortClearance> results = IssuePortClearanceLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(
								documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC);

				if (Validator.isNotNull(results) && results.size() > 0) {
					IssuePortClearance issuePortClearance = results.get(0);
					if (issuePortClearance.getAttachedFile() > 0) {
						canbopheduyet = Validator.isNotNull(issuePortClearance
								.getSignName()) ? issuePortClearance
								.getSignName() : issuePortClearance
								.getSignTitle();
					}
				}
			}

			result.put("canBoPheDuyet", canbopheduyet);

		} catch (Exception e) {
			log.error(e.getMessage());
			return result;
		}

		return result;
	}

	public static JSONObject getHistoryTable(ThemeDisplay themeDisplay,
			String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode,
			String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd,
			String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency,
			String nameArrivalShipAgency, String departureShipAgency,
			String nameDepartureShipAgency, String portRegionCode,
			String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
			String nameOfShipownersAgents, String maritimeCodeNext, int start,
			int end, boolean isDTND, boolean isDTNDCam, String daKy) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			if (documentTypeCode.equals("0")) {
				documentTypeCode = StringPool.BLANK;
			}
			// TODO TEMP
			documentStatusCode = CheckBusinessUtil
					.getTrangThaiHoSoChoKySoDongDau(MessageType.ROLE_THU_TUC);
			log.info("********************************************"
					+ documentStatusCode);
			UserSign userSignDefault = UserSignLocalServiceUtil
					.getByUserId(themeDisplay.getUserId());
			// END TEMP

			UserPort portDefault = UserPortLocalServiceUtil
					.findByUserId(themeDisplay.getUserId());

			maritimeCode = portDefault.getPortCode();

			String userTitle = Validator.isNotNull(userSignDefault) ? userSignDefault
					.getTitle() : StringPool.BLANK;
			String representative = Validator.isNotNull(userSignDefault) ? userSignDefault
					.getRepresentative() : StringPool.BLANK;
			if (userTitle.isEmpty() || userTitle == null
					|| representative.isEmpty() || representative == null
					|| userSignDefault.getFilechungthusoid() == 0
//					|| userSignDefault.getFilechungthusoid() == 0
//					|| userSignDefault.getFilechukyid() == 0) {
					|| userSignDefault.getFilechukyid() == 0) {
				userTitle = "C\u1EA5u h\u00ECnh th\u00F4ng tin k\u00FD s\u1ED1 ch\u01B0a \u0111\u1EA7y \u0111\u1EE7 !";
				representative = "---";
			}

			log.info("********************************************"
					+ documentTypeCode);
			if (documentTypeCode.equals("document_sign")) {
				documentTypeCode = StringPool.BLANK;
				List<TempDocument> data = TempDocumentLocalServiceUtil
						.findDanhSachHoSoLanhDaoChoKySo(documentTypeCode,
								maritimeCode, portCode, lastPortCode, shipName,
								stateCode, callSign, imo, shipPosition,
								shipDateFromStart, shipDateFromEnd,
								shipDateToStart, shipDateToEnd,
								documentStatusCode, arrivalShipAgency,
								nameArrivalShipAgency, departureShipAgency,
								nameDepartureShipAgency, portRegionCode,
								ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai,
								representative, nameOfShipownersAgents,
								maritimeCodeNext, daKy, start, end);

				long total = TempDocumentLocalServiceUtil
						.countDanhSachHoSoLanhDaoChoKySo(documentTypeCode,
								maritimeCode, portCode, lastPortCode, shipName,
								stateCode, callSign, imo, shipPosition,
								shipDateFromStart, shipDateFromEnd,
								shipDateToStart, shipDateToEnd,
								documentStatusCode, arrivalShipAgency,
								nameArrivalShipAgency, departureShipAgency,
								nameDepartureShipAgency, portRegionCode,
								ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai,
								representative, nameOfShipownersAgents,
								maritimeCodeNext, daKy);
				log.info("********************************************total: "
						+ total);
				result.put("data", JSONFactoryUtil
						.createJSONArray(JSONFactoryUtil.looseSerialize(data)));
				result.put("total", total);
			} else if (documentTypeCode.equals("document_history")) {
				documentTypeCode = StringPool.BLANK;
				List<TempDocument> data = TempDocumentLocalServiceUtil
						.findDanhSachHoSoRoleThuTuc(documentTypeCode,
								maritimeCode, portCode, lastPortCode, shipName,
								stateCode, callSign, imo, shipPosition,
								shipDateFromStart, shipDateFromEnd,
								shipDateToStart, shipDateToEnd,
								documentStatusCode, arrivalShipAgency,
								nameArrivalShipAgency, departureShipAgency,
								nameDepartureShipAgency, portRegionCode,
								ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai,
								start, end, isDTND, isDTNDCam);

				long total = TempDocumentLocalServiceUtil
						.countDanhSachHoSoRoleThuTuc(documentTypeCode,
								maritimeCode, portCode, lastPortCode, shipName,
								stateCode, callSign, imo, shipPosition,
								shipDateFromStart, shipDateFromEnd,
								shipDateToStart, shipDateToEnd,
								documentStatusCode, arrivalShipAgency,
								nameArrivalShipAgency, departureShipAgency,
								nameDepartureShipAgency, portRegionCode,
								ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai,
								isDTND, isDTNDCam);
				log.info("********************************************total: "
						+ total);
				result.put("data", JSONFactoryUtil
						.createJSONArray(JSONFactoryUtil.looseSerialize(data)));
				result.put("total", total);
			} else if (documentTypeCode.equals("document_approve")) {
				documentTypeCode = StringPool.BLANK;
				List<TempDocument> data = TempDocumentLocalServiceUtil
						.findDanhSachHoSoVanThuChoDongDau(documentTypeCode,
								maritimeCode, portCode, lastPortCode, shipName,
								stateCode, callSign, imo, shipPosition,
								shipDateFromStart, shipDateFromEnd,
								shipDateToStart, shipDateToEnd,
								documentStatusCode, arrivalShipAgency,
								nameArrivalShipAgency, departureShipAgency,
								nameDepartureShipAgency, portRegionCode,
								ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai,
								nameOfShipownersAgents, maritimeCodeNext,
								start, end);

				long total = TempDocumentLocalServiceUtil
						.countDanhSachHoSoVanThuChoDongDau(documentTypeCode,
								maritimeCode, portCode, lastPortCode, shipName,
								stateCode, callSign, imo, shipPosition,
								shipDateFromStart, shipDateFromEnd,
								shipDateToStart, shipDateToEnd,
								documentStatusCode, arrivalShipAgency,
								nameArrivalShipAgency, departureShipAgency,
								nameDepartureShipAgency, portRegionCode,
								ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai,
								nameOfShipownersAgents, maritimeCodeNext);
				log.info("********************************************total: "
						+ total);
				result.put("data", JSONFactoryUtil
						.createJSONArray(JSONFactoryUtil.looseSerialize(data)));
				result.put("total", total);
			}

		} catch (Exception e) {

			result.put("total", 0);
		}
		return result;
	}

	public static JSONArray counterHoSoAll(ThemeDisplay themeDisplay,
			HttpServletRequest request) {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		JSONObject object = JSONFactoryUtil.createJSONObject();
		try {

			String shipName = ParamUtil.getString(request, "shipName");
			String documentName = ParamUtil.getString(request, "documentName");
			String callSign = ParamUtil.getString(request, "callSign");
			if (shipName.equalsIgnoreCase("undefined")) {
				shipName = StringPool.BLANK;
			}
			if (Validator.isNotNull(shipName)) {
				try {
					shipName = URLDecoder.decode(shipName, "UTF-8");
				} catch (UnsupportedEncodingException e) {
				}
			}
			UserPort portDefault = UserPortLocalServiceUtil
					.findByUserId(themeDisplay.getUserId());

			String portCode = StringPool.BLANK;

			if (Validator.isNotNull(portDefault)) {
				portCode = portDefault.getPortCode();
			}

			List<DmDocType> docTypes = DmDocTypeLocalServiceUtil.getDmDocTypes(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			long total = 0;
			for (DmDocType dmDocType : docTypes) {
				object = JSONFactoryUtil.createJSONObject();

				total = TempDocumentLocalServiceUtil
						.countByF_documentTypeCode_maritimeCodeFinder(
								dmDocType.getDocumentType(), portCode,
								shipName, documentName, callSign);

				object.put("code", dmDocType.getDocumentType());
				object.put("total", total);
				result.put(object);
			}

		} catch (SystemException e) {

			log.error(e.getMessage());
		}

		return result;
	}

	public static JSONArray getGiayToXuatTrinhPhuongTien(
			ThemeDisplay themeDisplay, String documentTypeCode,
			int documentName, int documentYear) {
		// TODO Auto-generated method stub
		JSONArray result = JSONFactoryUtil.createJSONArray();

		try {
			List<ResultCertificate> resultCertificates = ResultCertificateLocalServiceUtil
					.findByDocumentNameAndDocumentYear(documentName,
							documentYear);
			List<DmCertificate> allDmCertificate = DmCertificateLocalServiceUtil
					.getDmCertificates(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			for (DmCertificate dmCertificate : allDmCertificate) {

				boolean isCompared = false;
				ResultCertificate resultData = null;
				for (ResultCertificate resultCertificate : resultCertificates) {
					if (resultCertificate.getCertificateCode()
							.equalsIgnoreCase(
									dmCertificate.getCertificateCode())) {
						isCompared = true;
						resultData = resultCertificate;
						break;
					}
				}

				if (documentTypeCode.equals("4")
						|| documentTypeCode.equals("5")
						|| documentTypeCode.equals("10")
						|| documentTypeCode.equals("11")) {
					if (/* isCompared && */(FormatData
							.convertToInt(dmCertificate.getCertificateCode()) >= 37 && FormatData
							.convertToInt(dmCertificate.getCertificateCode()) <= 47)
							|| FormatData.convertToInt(dmCertificate
									.getCertificateCode()) == 48
							|| FormatData.convertToInt(dmCertificate
									.getCertificateCode()) == 49
							|| FormatData.convertToInt(dmCertificate
									.getCertificateCode()) == 50) {

						jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("id", dmCertificate.getId());
						jsonObject.put("certificateCode",
								dmCertificate.getCertificateCode());

						jsonObject.put("typeText",
								dmCertificate.getCertificateNameVN());
						jsonObject.put(
								"giayChungNhan",
								Validator.isNotNull(resultData) ? resultData
										.getCertificateNo() : StringPool.BLANK);
						jsonObject
								.put("ngayCap",
										(Validator.isNotNull(resultData) && Validator.isNotNull(resultData
												.getCertificateIssueDate())) ? FormatData
												.parseDateToTringType3(resultData
														.getCertificateIssueDate())
												: StringPool.BLANK);
						jsonObject
								.put("ngayHetHan",
										(Validator.isNotNull(resultData) && Validator.isNotNull(resultData
												.getCertificateExpiredDate())) ? FormatData
												.parseDateToTringType3(resultData
														.getCertificateExpiredDate())
												: StringPool.BLANK);
						jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
								.getFullName());
						if (Validator.isNotNull(resultData)) {
							jsonObject.put("yeuCauXuatTrinh", resultData
									.getMandatory() == 1 ? true : false);
						} else {
							jsonObject.put("yeuCauXuatTrinh", true);
						}
						jsonObject.put("daKiemTra", (Validator
								.isNotNull(resultData) && resultData
								.getIsExamined() == 1) ? true : false);

						if (FormatData.convertToInt(dmCertificate
								.getCertificateCode()) != 39
								&& FormatData.convertToInt(dmCertificate
										.getCertificateCode()) != 40) {
							result.put(jsonObject);
						}

					}
				} else if (documentTypeCode.equals("14")
						|| documentTypeCode.equals("15")) {
					if ((FormatData.convertToInt(dmCertificate
							.getCertificateCode()) >= 63 && FormatData
							.convertToInt(dmCertificate.getCertificateCode()) <= 67)) {

						jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("id", dmCertificate.getId());
						jsonObject.put("certificateCode",
								dmCertificate.getCertificateCode());

						jsonObject.put("typeText",
								dmCertificate.getCertificateNameVN());
						jsonObject.put(
								"giayChungNhan",
								Validator.isNotNull(resultData) ? resultData
										.getCertificateNo() : StringPool.BLANK);
						jsonObject
								.put("ngayCap",
										(Validator.isNotNull(resultData) && Validator.isNotNull(resultData
												.getCertificateIssueDate())) ? FormatData
												.parseDateToTringType3(resultData
														.getCertificateIssueDate())
												: StringPool.BLANK);
						jsonObject
								.put("ngayHetHan",
										(Validator.isNotNull(resultData) && Validator.isNotNull(resultData
												.getCertificateExpiredDate())) ? FormatData
												.parseDateToTringType3(resultData
														.getCertificateExpiredDate())
												: StringPool.BLANK);
						jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
								.getFullName());
						if (Validator.isNotNull(resultData)) {
							jsonObject.put("yeuCauXuatTrinh", resultData
									.getMandatory() == 1 ? true : false);
						} else {
							jsonObject.put("yeuCauXuatTrinh", true);
						}
						jsonObject.put("daKiemTra", (Validator
								.isNotNull(resultData) && resultData
								.getIsExamined() == 1) ? true : false);

						result.put(jsonObject);
					}
				} else {
					if (/* isCompared && */FormatData
							.convertToInt(dmCertificate.getCertificateCode()) >= 1
							&& FormatData.convertToInt(dmCertificate
									.getCertificateCode()) <= 36) {

						jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("id", dmCertificate.getId());
						jsonObject.put("certificateCode",
								dmCertificate.getCertificateCode());

						jsonObject.put("typeText",
								dmCertificate.getCertificateNameVN());
						jsonObject.put(
								"giayChungNhan",
								Validator.isNotNull(resultData) ? resultData
										.getCertificateNo() : StringPool.BLANK);
						jsonObject
								.put("ngayCap",
										(Validator.isNotNull(resultData) && Validator.isNotNull(resultData
												.getCertificateIssueDate())) ? FormatData
												.parseDateToTringType3(resultData
														.getCertificateIssueDate())
												: StringPool.BLANK);
						jsonObject
								.put("ngayHetHan",
										(Validator.isNotNull(resultData) && Validator.isNotNull(resultData
												.getCertificateExpiredDate())) ? FormatData
												.parseDateToTringType3(resultData
														.getCertificateExpiredDate())
												: StringPool.BLANK);
						jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
								.getFullName());
						if (Validator.isNotNull(resultData)) {
							jsonObject.put("yeuCauXuatTrinh", resultData
									.getMandatory() == 1 ? true : false);
						} else {
							jsonObject.put("yeuCauXuatTrinh", true);
						}
						jsonObject.put("daKiemTra", (Validator
								.isNotNull(resultData) && resultData
								.getIsExamined() == 1) ? true : false);

						result.put(jsonObject);
					}
				}

			}

		} catch (Exception e) {

			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONArray getGiayToXuatTrinh_39(ThemeDisplay themeDisplay,
			String documentTypeCode, int documentName, int documentYear) {
		// TODO Auto-generated method stub
		JSONArray result = JSONFactoryUtil.createJSONArray();

		try {
			log.info("documentTypeCode.equals(14): "
					+ documentTypeCode.equals("14"));
			log.info("documentTypeCode.equals(15): "
					+ documentTypeCode.equals("15"));
			List<ResultCertificate> resultCertificates39 = new ArrayList<ResultCertificate>();

			if (documentTypeCode.equals("14") || documentTypeCode.equals("15")) {
				resultCertificates39 = ResultCertificateLocalServiceUtil
						.findByDocumentNameAndDocumentYearAndCertificateCode(
								documentName, documentYear, String.valueOf(68));
			} else {
				resultCertificates39 = ResultCertificateLocalServiceUtil
						.findByDocumentNameAndDocumentYearAndCertificateCode(
								documentName, documentYear, String.valueOf(39));
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if (Validator.isNull(resultCertificates39)
					|| (Validator.isNotNull(resultCertificates39) && resultCertificates39
							.size() == 0)) {

				TempCrewList tmpCrewList = TempCrewListLocalServiceUtil
						.getLastByDocumentNameAndDocumentYear(documentName,
								documentYear);

				if (Validator.isNotNull(tmpCrewList)) {
					List<TempCrewDetails> lstTempCrewDetails = TempCrewDetailsLocalServiceUtil
							.findByRequestCode(tmpCrewList.getRequestCode());

					for (TempCrewDetails tempCrewDetails : lstTempCrewDetails) {
						jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("id", 0);
						jsonObject.put("certificateCode", StringPool.BLANK);
						jsonObject.put("typeText",
								tempCrewDetails.getFamilyName() + " "
										+ tempCrewDetails.getGivenName());
						jsonObject.put("giayChungNhan", StringPool.BLANK);
						jsonObject.put("ngayCap", StringPool.BLANK);
						jsonObject.put("ngayHetHan", StringPool.BLANK);
						jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
								.getFullName());
						jsonObject.put("yeuCauXuatTrinh", true);
						jsonObject.put("daKiemTra", false);

						result.put(jsonObject);
					}
				}

			} else {
				for (ResultCertificate resultCertificate : resultCertificates39) {

					jsonObject = JSONFactoryUtil.createJSONObject();
					jsonObject.put("id", resultCertificate.getId());
					jsonObject.put("certificateCode",
							resultCertificate.getCertificateCode());
					jsonObject.put("typeText", resultCertificate.getComment());
					jsonObject
							.put("giayChungNhan",
									Validator.isNotNull(resultCertificate) ? resultCertificate
											.getCertificateNo()
											: StringPool.BLANK);
					jsonObject
							.put("ngayCap",
									(Validator.isNotNull(resultCertificate) && Validator.isNotNull(resultCertificate
											.getCertificateExpiredDate())) ? FormatData
											.parseDateToTringType3(resultCertificate
													.getCertificateIssueDate())
											: StringPool.BLANK);
					jsonObject
							.put("ngayHetHan",
									(Validator.isNotNull(resultCertificate) && Validator.isNotNull(resultCertificate
											.getCertificateExpiredDate())) ? FormatData
											.parseDateToTringType3(resultCertificate
													.getCertificateExpiredDate())
											: StringPool.BLANK);
					jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
							.getFullName());
					jsonObject.put("yeuCauXuatTrinh", resultCertificate
							.getMandatory() == 1 ? true : false);
					jsonObject.put("daKiemTra", resultCertificate
							.getIsExamined() == 1 ? true : false);

					result.put(jsonObject);
				}
			}

		} catch (Exception e) {

			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONArray getGiayToXuatTrinh_40(ThemeDisplay themeDisplay,
			String documentTypeCode, int documentName, int documentYear) {
		// TODO Auto-generated method stub
		JSONArray result = JSONFactoryUtil.createJSONArray();

		try {
			List<ResultCertificate> resultCertificates40 = ResultCertificateLocalServiceUtil
					.findByDocumentNameAndDocumentYearAndCertificateCode(
							documentName, documentYear, String.valueOf(40));

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if (Validator.isNull(resultCertificates40)
					|| (Validator.isNotNull(resultCertificates40) && resultCertificates40
							.size() == 0)) {

				TempCrewList tmpCrewList = TempCrewListLocalServiceUtil
						.getLastByDocumentNameAndDocumentYear(documentName,
								documentYear);

				if (Validator.isNotNull(tmpCrewList)) {
					List<TempCrewDetails> lstTempCrewDetails = TempCrewDetailsLocalServiceUtil
							.findByRequestCode(tmpCrewList.getRequestCode());

					for (TempCrewDetails tempCrewDetails : lstTempCrewDetails) {
						jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("id", 0);
						jsonObject.put("certificateCode", StringPool.BLANK);
						jsonObject.put("typeText",
								tempCrewDetails.getFamilyName() + " "
										+ tempCrewDetails.getGivenName());
						jsonObject.put("giayChungNhan", StringPool.BLANK);
						jsonObject.put("ngayCap", StringPool.BLANK);
						jsonObject.put("ngayHetHan", StringPool.BLANK);
						jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
								.getFullName());
						jsonObject.put("yeuCauXuatTrinh", true);
						jsonObject.put("daKiemTra", false);

						result.put(jsonObject);
					}
				}

			} else {
				for (ResultCertificate resultCertificate : resultCertificates40) {

					jsonObject = JSONFactoryUtil.createJSONObject();
					jsonObject.put("id", resultCertificate.getId());
					jsonObject.put("certificateCode",
							resultCertificate.getCertificateCode());
					jsonObject.put("typeText", resultCertificate.getComment());
					jsonObject
							.put("giayChungNhan",
									Validator.isNotNull(resultCertificate) ? resultCertificate
											.getCertificateNo()
											: StringPool.BLANK);
					jsonObject
							.put("ngayCap",
									(Validator.isNotNull(resultCertificate) && Validator.isNotNull(resultCertificate
											.getCertificateExpiredDate())) ? FormatData
											.parseDateToTringType3(resultCertificate
													.getCertificateIssueDate())
											: StringPool.BLANK);
					jsonObject
							.put("ngayHetHan",
									(Validator.isNotNull(resultCertificate) && Validator.isNotNull(resultCertificate
											.getCertificateExpiredDate())) ? FormatData
											.parseDateToTringType3(resultCertificate
													.getCertificateExpiredDate())
											: StringPool.BLANK);
					jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
							.getFullName());
					jsonObject.put("yeuCauXuatTrinh", resultCertificate
							.getMandatory() == 1 ? true : false);
					jsonObject.put("daKiemTra", resultCertificate
							.getIsExamined() == 1 ? true : false);

					result.put(jsonObject);
				}
			}

		} catch (Exception e) {

			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONArray getGiayToXuatTrinh_48(ThemeDisplay themeDisplay,
			String documentTypeCode, int documentName, int documentYear) {
		// TODO Auto-generated method stub
		JSONArray result = JSONFactoryUtil.createJSONArray();

		try {
			List<ResultCertificate> resultCertificates48 = ResultCertificateLocalServiceUtil
					.findByDocumentNameAndDocumentYearAndCertificateCode(
							documentName, documentYear, String.valueOf(48));

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if ((Validator.isNull(resultCertificates48) || resultCertificates48
					.size() == 0)
					&& (documentTypeCode.equals("4")
							|| documentTypeCode.equals("5")
							|| documentTypeCode.equals("10") || documentTypeCode
								.equals("11"))) {

				TempCrewList tmpCrewList = TempCrewListLocalServiceUtil
						.getLastByDocumentNameAndDocumentYear(documentName,
								documentYear);
				if (Validator.isNotNull(tmpCrewList)) {
					List<TempCrewDetails> lstTempCrewDetails = TempCrewDetailsLocalServiceUtil
							.findByRequestCode(tmpCrewList.getRequestCode());

					for (TempCrewDetails tempCrewDetails : lstTempCrewDetails) {
						jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("id", 0);
						jsonObject.put("certificateCode", StringPool.BLANK);
						jsonObject.put("typeText",
								tempCrewDetails.getFamilyName() + " "
										+ tempCrewDetails.getGivenName());
						jsonObject.put("giayChungNhan", StringPool.BLANK);
						jsonObject.put("ngayCap", StringPool.BLANK);
						jsonObject.put("ngayHetHan", StringPool.BLANK);
						jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
								.getFullName());
						jsonObject.put("yeuCauXuatTrinh", true);
						jsonObject.put("daKiemTra", false);

						result.put(jsonObject);
					}
				}

			} else {
				for (ResultCertificate resultCertificate : resultCertificates48) {

					jsonObject = JSONFactoryUtil.createJSONObject();
					jsonObject.put("id", resultCertificate.getId());
					jsonObject.put("certificateCode",
							resultCertificate.getCertificateCode());
					jsonObject.put("typeText", resultCertificate.getComment());
					jsonObject
							.put("giayChungNhan",
									Validator.isNotNull(resultCertificate) ? resultCertificate
											.getCertificateNo()
											: StringPool.BLANK);
					jsonObject
							.put("ngayCap",
									(Validator.isNotNull(resultCertificate) && Validator.isNotNull(resultCertificate
											.getCertificateExpiredDate())) ? FormatData
											.parseDateToTringType3(resultCertificate
													.getCertificateIssueDate())
											: StringPool.BLANK);
					jsonObject
							.put("ngayHetHan",
									(Validator.isNotNull(resultCertificate) && Validator.isNotNull(resultCertificate
											.getCertificateExpiredDate())) ? FormatData
											.parseDateToTringType3(resultCertificate
													.getCertificateExpiredDate())
											: StringPool.BLANK);
					jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
							.getFullName());
					jsonObject.put("yeuCauXuatTrinh", resultCertificate
							.getMandatory() == 1 ? true : false);
					jsonObject.put("daKiemTra", resultCertificate
							.getIsExamined() == 1 ? true : false);

					result.put(jsonObject);
				}
			}

		} catch (Exception e) {

			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONArray getGiayToXuatTrinh_49(ThemeDisplay themeDisplay,
			String documentTypeCode, int documentName, int documentYear) {
		// TODO Auto-generated method stub
		JSONArray result = JSONFactoryUtil.createJSONArray();

		try {
			List<ResultCertificate> resultCertificates49 = ResultCertificateLocalServiceUtil
					.findByDocumentNameAndDocumentYearAndCertificateCode(
							documentName, documentYear, String.valueOf(49));

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if ((Validator.isNull(resultCertificates49) || resultCertificates49
					.size() == 0)
					&& (documentTypeCode.equals("4")
							|| documentTypeCode.equals("5")
							|| documentTypeCode.equals("10") || documentTypeCode
								.equals("11"))) {

				TempCrewList tmpCrewList = TempCrewListLocalServiceUtil
						.getLastByDocumentNameAndDocumentYear(documentName,
								documentYear);
				if (Validator.isNotNull(tmpCrewList)) {
					List<TempCrewDetails> lstTempCrewDetails = TempCrewDetailsLocalServiceUtil
							.findByRequestCode(tmpCrewList.getRequestCode());

					for (TempCrewDetails tempCrewDetails : lstTempCrewDetails) {
						jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("id", 0);
						jsonObject.put("certificateCode", StringPool.BLANK);
						jsonObject.put("typeText",
								tempCrewDetails.getFamilyName() + " "
										+ tempCrewDetails.getGivenName());
						jsonObject.put("giayChungNhan", StringPool.BLANK);
						jsonObject.put("ngayCap", StringPool.BLANK);
						jsonObject.put("ngayHetHan", StringPool.BLANK);
						jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
								.getFullName());
						jsonObject.put("yeuCauXuatTrinh", true);
						jsonObject.put("daKiemTra", false);

						result.put(jsonObject);
					}
				}

			} else {
				for (ResultCertificate resultCertificate : resultCertificates49) {

					jsonObject = JSONFactoryUtil.createJSONObject();
					jsonObject.put("id", resultCertificate.getId());
					jsonObject.put("certificateCode",
							resultCertificate.getCertificateCode());
					jsonObject.put("typeText", resultCertificate.getComment());
					jsonObject
							.put("giayChungNhan",
									Validator.isNotNull(resultCertificate) ? resultCertificate
											.getCertificateNo()
											: StringPool.BLANK);
					jsonObject
							.put("ngayCap",
									(Validator.isNotNull(resultCertificate) && Validator.isNotNull(resultCertificate
											.getCertificateExpiredDate())) ? FormatData
											.parseDateToTringType3(resultCertificate
													.getCertificateIssueDate())
											: StringPool.BLANK);
					jsonObject
							.put("ngayHetHan",
									(Validator.isNotNull(resultCertificate) && Validator.isNotNull(resultCertificate
											.getCertificateExpiredDate())) ? FormatData
											.parseDateToTringType3(resultCertificate
													.getCertificateExpiredDate())
											: StringPool.BLANK);
					jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
							.getFullName());
					jsonObject.put("yeuCauXuatTrinh", resultCertificate
							.getMandatory() == 1 ? true : false);
					jsonObject.put("daKiemTra", resultCertificate
							.getIsExamined() == 1 ? true : false);

					result.put(jsonObject);
				}
			}

		} catch (Exception e) {

			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONArray getGiayToXuatTrinh_50(ThemeDisplay themeDisplay,
			String documentTypeCode, int documentName, int documentYear) {
		// TODO Auto-generated method stub
		JSONArray result = JSONFactoryUtil.createJSONArray();

		try {
			List<ResultCertificate> resultCertificates50 = ResultCertificateLocalServiceUtil
					.findByDocumentNameAndDocumentYearAndCertificateCode(
							documentName, documentYear, String.valueOf(50));

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if ((Validator.isNull(resultCertificates50) || resultCertificates50
					.size() == 0)
					&& (documentTypeCode.equals("4")
							|| documentTypeCode.equals("5")
							|| documentTypeCode.equals("10") || documentTypeCode
								.equals("11"))) {

				TempCrewList tmpCrewList = TempCrewListLocalServiceUtil
						.getLastByDocumentNameAndDocumentYear(documentName,
								documentYear);
				if (Validator.isNotNull(tmpCrewList)) {
					List<TempCrewDetails> lstTempCrewDetails = TempCrewDetailsLocalServiceUtil
							.findByRequestCode(tmpCrewList.getRequestCode());

					for (TempCrewDetails tempCrewDetails : lstTempCrewDetails) {
						jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("id", 0);
						jsonObject.put("certificateCode", StringPool.BLANK);
						jsonObject.put("typeText",
								tempCrewDetails.getFamilyName() + " "
										+ tempCrewDetails.getGivenName());
						jsonObject.put("giayChungNhan", StringPool.BLANK);
						jsonObject.put("ngayCap", StringPool.BLANK);
						jsonObject.put("ngayHetHan", StringPool.BLANK);
						jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
								.getFullName());
						jsonObject.put("yeuCauXuatTrinh", true);
						jsonObject.put("daKiemTra", false);

						result.put(jsonObject);
					}
				}

			} else {
				for (ResultCertificate resultCertificate : resultCertificates50) {

					jsonObject = JSONFactoryUtil.createJSONObject();
					jsonObject.put("id", resultCertificate.getId());
					jsonObject.put("certificateCode",
							resultCertificate.getCertificateCode());
					jsonObject.put("typeText", resultCertificate.getComment());
					jsonObject
							.put("giayChungNhan",
									Validator.isNotNull(resultCertificate) ? resultCertificate
											.getCertificateNo()
											: StringPool.BLANK);
					jsonObject
							.put("ngayCap",
									(Validator.isNotNull(resultCertificate) && Validator.isNotNull(resultCertificate
											.getCertificateExpiredDate())) ? FormatData
											.parseDateToTringType3(resultCertificate
													.getCertificateIssueDate())
											: StringPool.BLANK);
					jsonObject
							.put("ngayHetHan",
									(Validator.isNotNull(resultCertificate) && Validator.isNotNull(resultCertificate
											.getCertificateExpiredDate())) ? FormatData
											.parseDateToTringType3(resultCertificate
													.getCertificateExpiredDate())
											: StringPool.BLANK);
					jsonObject.put("nguoiKiemDuyet", themeDisplay.getUser()
							.getFullName());
					jsonObject.put("yeuCauXuatTrinh", resultCertificate
							.getMandatory() == 1 ? true : false);
					jsonObject.put("daKiemTra", resultCertificate
							.getIsExamined() == 1 ? true : false);

					result.put(jsonObject);
				}
			}

		} catch (Exception e) {

			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject getLenhDieuDongEXT(ThemeDisplay themeDisplay,
			long documentName, int documentYear, HttpServletRequest request) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<IssueShiftingOrder> results = new ArrayList<IssueShiftingOrder>(
				IssueShiftingOrderLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(
								documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC));

		UserPort portDefault = UserPortLocalServiceUtil
				.findByUserId(themeDisplay.getUserId());
		List<DmPort> ports = null;

		if (portDefault != null) {
			ports = DmPortLocalServiceUtil.findByLoCode(portDefault
					.getPortCode());
		}

		if (ports == null) {
			ports = new ArrayList<DmPort>();
		}

		JSONArray portsArrays = JSONFactoryUtil.createJSONArray();
		JSONObject port = null;
		for (DmPort dmPort : ports) {
			port = JSONFactoryUtil.createJSONObject();

			port.put("portCode", dmPort.getPortCode());
			port.put("portName", dmPort.getPortName());

			portsArrays.put(port);
		}
		result.put("ports", portsArrays);

		TempDocument tempDoc = TempDocumentLocalServiceUtil
				.findTemDocumentByDocumentNameDocumentYear(documentName,
						documentYear);

		DmMaritime maritime = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(tempDoc.getMaritimeCode());
		if (Validator.isNull(maritime)) {
			maritime = new DmMaritime();
		}

		result.put("maritimeCityName", maritime.getCityCode());

		DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
				.getByPortRegionCode(tempDoc.getPortRegionCode());

		result.put("portRegionName", dmPortRegion.getPortRegionNameVN());

		// PortHarbour la ben cang,
		List<DmPortHarbour> lstPortHarbour = DmPortHarbourLocalServiceUtil
				.findByPortRegionCode(tempDoc.getMaritimeCode());
		if (Validator.isNull(lstPortHarbour)) {
			lstPortHarbour = new ArrayList<DmPortHarbour>();
		}

		portsArrays = JSONFactoryUtil.createJSONArray();
		port = null;
		for (DmPortHarbour dmPort : lstPortHarbour) {
			port = JSONFactoryUtil.createJSONObject();

			port.put("portHarbourCode", dmPort.getPortHarbourCode());
			port.put("portHarbourName", dmPort.getPortHarbourNameVN());

			portsArrays.put(port);
		}
		result.put("lstPortHarbour", portsArrays);
		// //PortRegirn la ben cang,
		// List<DmPortRegion> lstPortRegin =
		// DmPortRegionLocalServiceUtil.findPortRegionByPortCodeName(maritime.getMaritimeCode());
		// if (Validator.isNull(lstPortRegin)) { lstPortRegin = new
		// ArrayList<DmPortRegion>(); }
		//
		// portsArrays = JSONFactoryUtil.createJSONArray();
		// port = null;
		// for (DmPortRegion dmPort : lstPortRegin) {
		// port = JSONFactoryUtil.createJSONObject();
		//
		// port.put("portRegionCode", dmPort.getPortRegionCode());
		// port.put("portPortRegionNameVN", dmPort.getPortRegionNameVN());
		//
		// portsArrays.put(port);
		// }
		// result.put("lstPortRegin", portsArrays);
		// PortWharf la cau cang

		String portHarborCode = lstPortHarbour.get(0).getPortHarbourCode();

		List<DmPortWharf> lstPortWharf = new ArrayList<DmPortWharf>();

		if (result != null && results.size() > 0) {
			try {
				result.put("detail", JSONFactoryUtil
						.createJSONObject(JSONFactoryUtil
								.looseSerialize(results.get(0))));

				List<IssueShiftingOrderChanel> lstChanel = IssueShiftingOrderChanelLocalServiceUtil
						.findByShiftingOrderId(results.get(0).getId());

				portsArrays = JSONFactoryUtil.createJSONArray();
				port = null;
				for (IssueShiftingOrderChanel issueShiftingOrderChanel : lstChanel) {
					port = JSONFactoryUtil.createJSONObject();

					port.put("code0", issueShiftingOrderChanel.getId().getChanelCode());
					port.put("name", issueShiftingOrderChanel.getChanelName());

					portsArrays.put(port);
				}

				result.put("chanel", portsArrays);

				try {
					List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
							.findBydocumentNameAndDocumentYearAndNoticeShipType(
									documentName, documentYear,
									PageType.TYPE_THONG_BAO_TAU_DEN_CANG);

					if (tempNoTiceShipMessages != null
							&& tempNoTiceShipMessages.size() > 0) {
						TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
								.get(0);
						lstPortWharf = new ArrayList<DmPortWharf>();

						lstPortWharf = DmPortWharfLocalServiceUtil
								.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
										tempNoTiceShipMessage
												.getPortRegionCode(),
										tempNoTiceShipMessage
												.getPortHarbourCode(),
										KeyParams.ORDER_BY_ASC);

						portsArrays = JSONFactoryUtil.createJSONArray();
						port = null;
						for (DmPortWharf dmPort : lstPortWharf) {
							port = JSONFactoryUtil.createJSONObject();

							port.put("portWharfCode", dmPort.getPortWharfCode());
							port.put("portWharfName",
									dmPort.getPortWharfNameVN());

							portsArrays.put(port);
						}
						result.put("lstPortWharf", portsArrays);

					}

					tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
							.findBydocumentNameAndDocumentYearAndNoticeShipType(
									documentName, documentYear,
									PageType.TYPE_XAC_BAO_TAU_DEN_CANG);

					if (tempNoTiceShipMessages != null
							&& tempNoTiceShipMessages.size() > 0) {
						TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
								.get(0);

						lstPortWharf = new ArrayList<DmPortWharf>();

						lstPortWharf = DmPortWharfLocalServiceUtil
								.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
										tempNoTiceShipMessage
												.getPortRegionCode(),
										tempNoTiceShipMessage
												.getPortHarbourCode(),
										KeyParams.ORDER_BY_ASC);

						portsArrays = JSONFactoryUtil.createJSONArray();
						port = null;
						for (DmPortWharf dmPort : lstPortWharf) {
							port = JSONFactoryUtil.createJSONObject();

							port.put("portWharfCode", dmPort.getPortWharfCode());
							port.put("portWharfName",
									dmPort.getPortWharfNameVN());

							portsArrays.put(port);
						}
						result.put("lstPortWharf", portsArrays);

					}

				} catch (Exception e) {
					log.error(e.getMessage());
				}

			} catch (JSONException e) {
				log.error(e.getMessage());
			}
		} else {
			IssueShiftingOrder issueShiftingOrder = new IssueShiftingOrder();

			try {
				List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
						.findBydocumentNameAndDocumentYearAndNoticeShipType(
								documentName, documentYear,
								PageType.TYPE_THONG_BAO_TAU_DEN_CANG);

				if (tempNoTiceShipMessages != null
						&& tempNoTiceShipMessages.size() > 0) {
					TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
							.get(0);

					issueShiftingOrder
							.setTaxCodeOfShipownersAgents(tempNoTiceShipMessage
									.getShipOwnerStateCode());
					issueShiftingOrder
							.setNameOfShipownersAgents(tempNoTiceShipMessage
									.getNameOfShipOwners());
					issueShiftingOrder
							.setShipAgencyAddress(tempNoTiceShipMessage
									.getShipAgencyAddress());
					issueShiftingOrder.setShipAgencyPhone(tempNoTiceShipMessage
							.getShipAgencyPhone());
					issueShiftingOrder.setShipAgencyFax(tempNoTiceShipMessage
							.getShipAgencyFax());
					issueShiftingOrder.setShipAgencyEmail(tempNoTiceShipMessage
							.getShipAgencyEmail());
					issueShiftingOrder.setTugBoat(tempNoTiceShipMessage
							.getTugBoat());
					issueShiftingOrder.setShownDraftxA(tempNoTiceShipMessage
							.getShownDraftxA());
					issueShiftingOrder.setShownDraftxF(tempNoTiceShipMessage
							.getShownDraftxF());
					issueShiftingOrder.setLoa(tempNoTiceShipMessage.getLoa());
					issueShiftingOrder.setDwt(tempNoTiceShipMessage.getDwt());
					issueShiftingOrder.setNameOfShip(tempNoTiceShipMessage
							.getShipName());

					// DmPort dmPortArrival =
					// DmPortLocalServiceUtil.fetchByPortCodeLoCode(tempNoTiceShipMessage.getArrivalPortCode(),
					// portDefault.getPortCode());

					// String arrivalPortName = StringPool.BLANK;
					//
					// if (Validator.isNotNull(dmPortArrival)) {
					//
					// arrivalPortName = dmPortArrival.getPortName();
					//
					// }
					//
					// issueShiftingOrder.setTo(arrivalPortName);

					DmPort dmPortArrival = DmPortLocalServiceUtil
							.getByPortCode(tempNoTiceShipMessage
									.getPortGoingToCode());

					issueShiftingOrder.setFrom(Validator
							.isNotNull(dmPortArrival) ? dmPortArrival
							.getPortName() : StringPool.BLANK);

					issueShiftingOrder
							.setRepresentative("GI\u00C1M \u0110\u1ED0C");

					issueShiftingOrder
							.setAnchoringPortCode(tempNoTiceShipMessage
									.getArrivalPortCode());

					issueShiftingOrder.setPortHarbourCode(tempNoTiceShipMessage
							.getPortHarbourCode());

					issueShiftingOrder
							.setShiftingPortWharfCode(tempNoTiceShipMessage
									.getPortWharfCode());

					// issueShiftingOrder.setAnchoringPortWharfCode(tempNoTiceShipMessage.getPortWharfCode());

					issueShiftingOrder.setShiftingDate(tempNoTiceShipMessage
							.getArrivalDate());

					lstPortWharf = new ArrayList<DmPortWharf>();

					lstPortWharf = DmPortWharfLocalServiceUtil
							.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
									tempNoTiceShipMessage.getPortRegionCode(),
									tempNoTiceShipMessage.getPortHarbourCode(),
									KeyParams.ORDER_BY_ASC);

					portsArrays = JSONFactoryUtil.createJSONArray();
					port = null;
					for (DmPortWharf dmPort : lstPortWharf) {
						port = JSONFactoryUtil.createJSONObject();

						port.put("portWharfCode", dmPort.getPortWharfCode());
						port.put("portWharfName", dmPort.getPortWharfNameVN());

						portsArrays.put(port);
					}
					result.put("lstPortWharf", portsArrays);

				}

				tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
						.findBydocumentNameAndDocumentYearAndNoticeShipType(
								documentName, documentYear,
								PageType.TYPE_XAC_BAO_TAU_DEN_CANG);

				if (tempNoTiceShipMessages != null
						&& tempNoTiceShipMessages.size() > 0) {
					TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
							.get(0);

					lstPortWharf = new ArrayList<DmPortWharf>();

					lstPortWharf = DmPortWharfLocalServiceUtil
							.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
									tempNoTiceShipMessage.getPortRegionCode(),
									tempNoTiceShipMessage.getPortHarbourCode(),
									KeyParams.ORDER_BY_ASC);

					portsArrays = JSONFactoryUtil.createJSONArray();
					port = null;
					for (DmPortWharf dmPort : lstPortWharf) {
						port = JSONFactoryUtil.createJSONObject();

						port.put("portWharfCode", dmPort.getPortWharfCode());
						port.put("portWharfName", dmPort.getPortWharfNameVN());

						portsArrays.put(port);
					}
					result.put("lstPortWharf", portsArrays);

					issueShiftingOrder.setShiftingDate(tempNoTiceShipMessage
							.getArrivalDate());

					issueShiftingOrder
							.setAnchoringPortCode(tempNoTiceShipMessage
									.getArrivalPortCode());

					issueShiftingOrder.setPortHarbourCode(tempNoTiceShipMessage
							.getPortHarbourCode());

					issueShiftingOrder
							.setShiftingPortWharfCode(tempNoTiceShipMessage
									.getPortWharfCode());
					//
					// DmPort dmPortArrival =
					// DmPortLocalServiceUtil.getByPortCode(tempNoTiceShipMessage.getPortGoingToCode());
					//
					// issueShiftingOrder.setFrom(Validator.isNotNull(dmPortArrival)
					// ? dmPortArrival.getPortName() : StringPool.BLANK);
					//
				}

				result.put("detail", JSONFactoryUtil
						.createJSONObject(JSONFactoryUtil
								.looseSerialize(issueShiftingOrder)));
				result.put("chanel", JSONFactoryUtil.createJSONArray());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		List<DmDataitem> changels = DmDataItemLocalServiceUtil
				.findByDataGroupIdAndNode1(Constants.DM_TUYEN_LUONG,
						tempDoc.getMaritimeCode());

		if (Validator.isNull(changels)) {
			changels = new ArrayList<DmDataitem>();
		}

		try {
			result.put("chanels", JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(changels)));
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("chanels", JSONFactoryUtil.createJSONArray());
		}

		return result;
	}

	public static JSONObject getMessageType23EXT(ThemeDisplay themeDisplay,
			long documentName, int documentYear, int messageType, int roleType,
			String requestCode, HttpServletRequest request) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			String soChungNhan = documentName+""; //SonVH sua gia tri mac dinh StringPool.BLANK;
			String approveName = StringPool.BLANK;
			Date ngayPheDuyet = new Date();
			double lePhi = 0;

			ResultCompletion resultCompetion = ResultCompetionLocalServiceUtil
					.findByDocumentNameAndDocumentYear(documentName,
							documentYear);
			if (resultCompetion == null) {
				resultCompetion = new ResultCompletion();
			}
			if (resultCompetion.getCertificateNo() != null
					&& resultCompetion.getCertificateNo().length() > 0) {
				String[] resultsGet = resultCompetion.getCertificateNo().trim()
						.split("/");
				if (resultsGet != null && resultsGet.length > 0) {
					soChungNhan = resultsGet[0];
				}
				approveName = resultCompetion.getApprovalName();
			}

			if (resultCompetion.getResponseTimeCVHH() != null) {
				ngayPheDuyet = resultCompetion.getResponseTimeCVHH();
			}

			TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
					.findByDocumentNameAnddocumentYear(documentName,
							documentYear);

			if (Validator.isNotNull(tempDebitNote)) {
				lePhi = tempDebitNote.getTotalpayment().doubleValue();
			}
			result.put("soChungNhan", soChungNhan);
			result.put("approveName", approveName);
			result.put("ngayPheDuyet", ngayPheDuyet.getTime());
			result.put("lePhi", lePhi);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return result;
	}

	public static JSONObject getMessageType60EXT(ThemeDisplay themeDisplay,
			long documentName, int documentYear, int messageType, int roleType,
			String requestCode, HttpServletRequest request) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);

			TempNoticeShipMessage thongBaoLast = TempNoTiceShipMessageLocalServiceUtil
					.getThongBaoLastByDocumentNameAndDocumentYear(documentName,
							documentYear);
			TempGeneralDeclaration banKhaiChungLast = TempGeneralDeclarationLocalServiceUtil
					.getLastByDocumentNameAndDocumentYear(documentName,
							documentYear);

			DmState dmState = DmStateLocalServiceUtil.getByStateCode(Validator
					.isNotNull(banKhaiChungLast) ? banKhaiChungLast
					.getFlagStateOfShip() : tempDocument.getStateCode());
			String maStCode = "";
			if (dmState == null) {
				maStCode = "";
			} else {
				maStCode = dmState.getStateCode();
			}

			String stateName = StringPool.BLANK;

			List<DmState> lstState = DmStateLocalServiceUtil.getDmStates(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			if (lstState != null && lstState.size() > 0) {
				for (DmState state : lstState) {
					if (maStCode.equals(state.getStateCode())) {
						stateName = state.getStateName();
					}
				}
			}
			Date sTimeOfDeparture = new Date();
			List<IssuePortClearance> results = IssuePortClearanceLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.ID,
							KeyParams.ORDER_BY_DESC);
			IssuePortClearance portClearanceEdit = null;
			if (results != null && results.size() > 0) {
				portClearanceEdit = results.get(0);

				if (Validator.isNotNull(portClearanceEdit.getTimeOfDeparture())) {
					sTimeOfDeparture = portClearanceEdit.getTimeOfDeparture();
				} else {
					sTimeOfDeparture = Validator.isNotNull(banKhaiChungLast) ? banKhaiChungLast
							.getDateOfArrival() : new Date();
				}

			}
			Date gprc = new Date();

			if (results != null && results.size() == 0) {
				gprc = tempDocument.getShipDateTo();
			} else {
				gprc = sTimeOfDeparture;
			}

			if (Validator.isNull(gprc)) {
				gprc = new Date();
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(gprc);
			cal.add(Calendar.DATE, 1);
			DmPort cangden = null;
			if (tempDocument.getDocumentTypeCode().toString().equals("14")) {
				String portOffArrivalCode = StringPool.BLANK;

				if (Validator.isNotNull(banKhaiChungLast)) {
					portOffArrivalCode = banKhaiChungLast
							.getPortOfArrivalCode();
				}
				cangden = DmPortLocalServiceUtil
						.getByPortCode(portOffArrivalCode);
			} else {
				cangden = DmPortLocalServiceUtil.getByPortCode(Validator
						.isNotNull(banKhaiChungLast) ? banKhaiChungLast
						.getLastPortOfCallCode() : tempDocument
						.getLastPortCode());
			}

			if (Validator.isNull(cangden)) {
				cangden = new DmPort();
			}

			DmMaritime maritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(tempDocument.getMaritimeCode());
			if (Validator.isNull(maritime)) {
				maritime = new DmMaritime();
			}

			boolean containZZZ = false;
			if (cangden != null && cangden.getPortCode().contains("ZZZ")) {
				containZZZ = true;
			} else {
				containZZZ = false;
			}

			String portOffArrivalCode = StringPool.BLANK;

			if (Validator.isNotNull(banKhaiChungLast)) {
				portOffArrivalCode = banKhaiChungLast.getPortOfArrivalCode();
			}
			DmPort cangRoiLast = null;
			log.info("----------------tempDocument.getDocumentTypeCode()++++++++++++++++"
					+ tempDocument.getDocumentTypeCode());
			log.info("----------------Check type 14++++++++++++++++"
					+ tempDocument.getDocumentTypeCode().equals(14));
			if (tempDocument.getDocumentTypeCode().toString().equals("14")) {
				cangRoiLast = DmPortLocalServiceUtil.getByPortCode(Validator
						.isNotNull(banKhaiChungLast) ? banKhaiChungLast
						.getLastPortOfCallCode() : tempDocument
						.getLastPortCode());
			} else {
				cangRoiLast = DmPortLocalServiceUtil
						.getByPortCode(portOffArrivalCode);
			}
			if (Validator.isNull(cangRoiLast)) {
				cangRoiLast = new DmPort();
			}

			List<DmUnitGeneral> units = DmUnitGeneralLocalServiceUtil
					.getDmUnitGenerals(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			if (Validator.isNull(units)) {
				units = new ArrayList<DmUnitGeneral>();
			}

			result.put(
					"representative",
					Validator.isNotNull(portClearanceEdit) ? portClearanceEdit
							.getRepresentative() : "GI\u00C1M \u0110\u1ED0C");
			result.put("nameOfship", tempDocument.getShipName());
			result.put("stateName", stateName);
			result.put("callSign", tempDocument.getCallSign());
			result.put("grt", tempDocument.getGrt());
			result.put("nt", tempDocument.getNt());
			result.put("dwt", tempDocument.getDwt());
			result.put("nameOfMaster", tempDocument.getShipCaptain());
			result.put("numberOfCrews", banKhaiChungLast.getNumberOfCrew());
			result.put("numberOfPassengers",
					banKhaiChungLast.getNumberOfPassengers());

			result.put(
					"transitOfCargo",
					Validator.isNotNull(portClearanceEdit) ? portClearanceEdit
							.getTransitCargo() : "NIL");
			result.put(
					"timeOfDeparture",
					Validator.isNotNull(portClearanceEdit)
							&& Validator.isNotNull(portClearanceEdit
									.getTimeOfDeparture()) ? portClearanceEdit
							.getTimeOfDeparture().getTime() : gprc.getTime());
			result.put("timeOfValidUntil", cal.getTime().getTime());

			result.put("containZZZ", containZZZ);
			result.put(
					"remarks",
					Validator.isNotNull(portClearanceEdit) ? portClearanceEdit
							.getRemarks() : "");
			result.put("portName", cangden.getPortName());
			result.put("portNameCode", cangden.getPortCode());
			result.put(
					"issueDate",
					Validator.isNotNull(portClearanceEdit)
							&& Validator.isNotNull(portClearanceEdit
									.getIssueDate()) ? portClearanceEdit
							.getIssueDate().getTime() : new Date().getTime());
			result.put(
					"certificateNo",
					Validator.isNotNull(portClearanceEdit) ? ConvertUtil
							.getValueFromStringList(
									portClearanceEdit.getCertificateNo(), "/",
									0) : StringPool.BLANK);

			result.put("cangRoi", cangRoiLast.getPortName());
			result.put(
					"volumeTransitCargo",
					Validator.isNotNull(portClearanceEdit) ? String
							.valueOf(portClearanceEdit.getVolumeTransitCargo())
							: "0");

			String sUnitVolumeTransitCargo = Validator
					.isNotNull(portClearanceEdit) ? portClearanceEdit
					.getTransitCargoUnit() : "";

			JSONArray unitsArrays = JSONFactoryUtil.createJSONArray();
			JSONObject unit = null;
			for (DmUnitGeneral dmUnitGeneral : units) {
				unit = JSONFactoryUtil.createJSONObject();

				unit.put("unitCode", dmUnitGeneral.getUnitCode());
				unit.put("unitName", dmUnitGeneral.getUnitName());

				unitsArrays.put(unit);
			}
			result.put("unitVolumeTransitCargoItems", unitsArrays);
			result.put("unitVolumeTransitCargo", sUnitVolumeTransitCargo);
			String displayCertificateNoUnit = Validator
					.isNotNull(portClearanceEdit) ? ConvertUtil
					.getValueFromStringList(
							portClearanceEdit.getCertificateNo(), "/", 1)
					: StringPool.BLANK;
			String maritimeReference = Validator.isNotNull(maritime
					.getMaritimeReference()) ? maritime.getMaritimeReference()
					: StringPool.BLANK;
			result.put(
					"unitCertificateNo",
					!StringPool.BLANK.equals(displayCertificateNoUnit) ? displayCertificateNoUnit
							: maritimeReference);

			// list hang hoa nguy hiem
			List<DmDataitem> cargoType = DmDataItemLocalServiceUtil
					.findByDataGroupId(Constants.DM_NHOM_HANG_HOA);
			if (Validator.isNull(cargoType)) {
				cargoType = new ArrayList<DmDataitem>();
			}

			List<DmDataitem> cargoCode = DmDataItemLocalServiceUtil
					.findByDataGroupId(Constants.DM_TEN_HANG_HOA);
			if (Validator.isNull(cargoCode)) {
				cargoCode = new ArrayList<DmDataitem>();
			}

			List<DmDataitem> unitCargo = DmDataItemLocalServiceUtil
					.findByDataGroupId(Constants.DM_DON_VI_TINH);
			if (Validator.isNull(unitCargo)) {
				unitCargo = new ArrayList<DmDataitem>();
			}

			result.put("cargoType", JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(cargoType)));
			result.put("cargoCode", JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(cargoCode)));
			result.put("unitCargo", JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(unitCargo)));
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return result;
	}

	public static JSONObject getMessageType60EXTTable(
			ThemeDisplay themeDisplay, long documentName, int documentYear,
			int messageType, int roleType, String requestCode,
			HttpServletRequest request) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			// TODO
			// TempNoticeShipMessage results =
			// TempNoTiceShipMessageLocalServiceUtil
			// .getThongBaoLastByDocumentNameAndDocumentYear(documentName,
			// documentYear);

			List<TempCargoItems> tempCargoItemsOrg = TempCargoItemsLocalServiceUtil
					.findBydocumentNameAnddocumentYear(documentName,
							documentYear);
			List<TempCargoItems> tempCargoItems = new ArrayList<TempCargoItems>();
			StringBuilder cargoDescription_C1_XEP = new StringBuilder();
			// IssuePortClearance issuePortClearance =
			// IssuePortClearanceLocalServiceUtil.findByRequestCode(requestCode);

			List<IssuePortClearance> results = IssuePortClearanceLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.ID,
							KeyParams.ORDER_BY_DESC);

			IssuePortClearance issuePortClearance = null;

			if (Validator.isNotNull(results) && results.size() > 0) {
				issuePortClearance = results.get(0);
			}

			if (Validator.isNotNull(issuePortClearance)) {
				for (TempCargoItems tempCargoItems2 : tempCargoItemsOrg) {
					if ((tempCargoItems2.getCargoCategory().endsWith("_VC")
							|| tempCargoItems2.getCargoCategory()
									.equalsIgnoreCase("VC") || tempCargoItems2
							.getCargoCategory().equalsIgnoreCase("C5"))
							&& issuePortClearance.getRequestCode().equals(
									tempCargoItems2.getRequestCode())) {
						tempCargoItems2.setRequestCode(issuePortClearance
								.getRequestCode());
						tempCargoItems.add(tempCargoItems2);
					}
				}
				result.put("tempCargoItemsDesc",
						issuePortClearance.getTransitCargo());
				if (tempCargoItems.size() == 0) {
					TempGeneralDeclaration reDeclaration = TempGeneralDeclarationLocalServiceUtil
							.getLastByDocumentNameAndDocumentYear(documentName,
									documentYear);
					if (Validator.isNotNull(reDeclaration)) {
						for (TempCargoItems tempCargoItems2 : tempCargoItemsOrg) {
							if ((tempCargoItems2.getCargoCategory().endsWith(
									"_VC")
									|| tempCargoItems2.getCargoCategory()
											.equalsIgnoreCase("VC") || tempCargoItems2
									.getCargoCategory().equalsIgnoreCase("C5"))
									&& reDeclaration.getRequestCode().equals(
											tempCargoItems2.getRequestCode())) {
								tempCargoItems2
										.setRequestCode(StringPool.BLANK);
								TempCargoItems details = tempCargoItems2;
								String tmp = "";
								if (Validator.isNotNull(details.getCargoType())
										&& Validator.isNotNull(details
												.getUnit())) {
									tmp = DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_NHOM_HANG_HOA,
													details.getCargoType())
											.getName()
											+ ", "
											+ DmDataItemLocalServiceUtil
													.findByDataGroupIdAndCode0(
															Constants.DM_TEN_HANG_HOA,
															details.getCargoCode())
													.getName()
											+ "  "
											+ details.getQuantity()
											+ " "
											+ DmDataItemLocalServiceUtil
													.findByDataGroupIdAndCode0(
															Constants.DM_DON_VI_TINH,
															details.getUnit())
													.getName() + " \n ";
								} else if (Validator.isNotNull(details
										.getCargoType())
										&& details.getCargoType()
												.equalsIgnoreCase("11")) // NIL-KHONG
																			// CHO
																			// HANG
								{
									tmp = DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_NHOM_HANG_HOA,
													details.getCargoType())
											.getName()
											+ " \n ";
								}
								if (tempCargoItems2.getCargoCategory()
										.equalsIgnoreCase("C5")) {
									cargoDescription_C1_XEP.append(tmp);
								}

								tempCargoItems.add(tempCargoItems2);
							}
						}
					}
					result.put("tempCargoItemsDesc",
							cargoDescription_C1_XEP.toString());
				}
			} else {
				TempGeneralDeclaration reDeclaration = TempGeneralDeclarationLocalServiceUtil
						.getLastByDocumentNameAndDocumentYear(documentName,
								documentYear);
				if (Validator.isNotNull(reDeclaration)) {
					for (TempCargoItems tempCargoItems2 : tempCargoItemsOrg) {
						if ((tempCargoItems2.getCargoCategory().endsWith("_VC")
								|| tempCargoItems2.getCargoCategory()
										.equalsIgnoreCase("VC") || tempCargoItems2
								.getCargoCategory().equalsIgnoreCase("C5"))
								&& reDeclaration.getRequestCode().equals(
										tempCargoItems2.getRequestCode())) {
							tempCargoItems2.setRequestCode(StringPool.BLANK);
							TempCargoItems details = tempCargoItems2;
							String tmp = "";
							if (Validator.isNotNull(details.getCargoType())
									&& Validator.isNotNull(details.getUnit())) {
								tmp = DmDataItemLocalServiceUtil
										.findByDataGroupIdAndCode0(
												Constants.DM_NHOM_HANG_HOA,
												details.getCargoType())
										.getName()
										+ ", "
										+ DmDataItemLocalServiceUtil
												.findByDataGroupIdAndCode0(
														Constants.DM_TEN_HANG_HOA,
														details.getCargoCode())
												.getName()
										+ "  "
										+ details.getQuantity()
										+ " "
										+ DmDataItemLocalServiceUtil
												.findByDataGroupIdAndCode0(
														Constants.DM_DON_VI_TINH,
														details.getUnit())
												.getName() + " \n ";
							} else if (Validator.isNotNull(details
									.getCargoType())
									&& details.getCargoType().equalsIgnoreCase(
											"11")) // NIL-KHONG CHO HANG
							{
								tmp = DmDataItemLocalServiceUtil
										.findByDataGroupIdAndCode0(
												Constants.DM_NHOM_HANG_HOA,
												details.getCargoType())
										.getName()
										+ " \n ";
							}
							if (tempCargoItems2.getCargoCategory()
									.equalsIgnoreCase("C5")) {
								cargoDescription_C1_XEP.append(tmp);
							}

							tempCargoItems.add(tempCargoItems2);
						}
					}
				}

				result.put("tempCargoItemsDesc",
						cargoDescription_C1_XEP.toString());
			}

			JSONArray arrayData = JSONFactoryUtil.createJSONArray();

			if (Validator.isNotNull(tempCargoItems)
					&& tempCargoItems.size() > 0) {
				arrayData = JSONFactoryUtil.createJSONArray(JSONFactoryUtil
						.looseSerialize(tempCargoItems));
			}

			result.put("tempCargoItems", arrayData);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return result;
	}

	public static JSONObject getMessageType60EXTTableQC(
			ThemeDisplay themeDisplay, long documentName, int documentYear,
			int messageType, int roleType, String requestCode,
			HttpServletRequest request) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			// TODO
			// TempNoticeShipMessage results =
			// TempNoTiceShipMessageLocalServiceUtil
			// .getThongBaoLastByDocumentNameAndDocumentYear(documentName,
			// documentYear);

			List<TempCargoItems> tempCargoItemsOrg = TempCargoItemsLocalServiceUtil
					.findBydocumentNameAnddocumentYear(documentName,
							documentYear);
			List<TempCargoItems> tempCargoItems = new ArrayList<TempCargoItems>();
			StringBuilder cargoDescription_C1_XEP = new StringBuilder();
			// IssuePortClearance issuePortClearance =
			// IssuePortClearanceLocalServiceUtil.findByRequestCode(requestCode);

			List<IssuePermissionForTransit> results = IssuePermissionForTransitLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.ID,
							KeyParams.ORDER_BY_DESC);
			//
			IssuePermissionForTransit issuePortClearance = null;

			if (Validator.isNotNull(results) && results.size() > 0) {
				issuePortClearance = results.get(0);
			}

			if (Validator.isNotNull(issuePortClearance)) {
				for (TempCargoItems tempCargoItems2 : tempCargoItemsOrg) {
					if ((tempCargoItems2.getCargoCategory().endsWith("_VC")
							|| tempCargoItems2.getCargoCategory()
									.equalsIgnoreCase("VC") || tempCargoItems2
							.getCargoCategory().equalsIgnoreCase("C5"))
							&& issuePortClearance.getRequestCode().equals(
									tempCargoItems2.getRequestCode())) {
						tempCargoItems2.setRequestCode(issuePortClearance
								.getRequestCode());
						tempCargoItems.add(tempCargoItems2);
					}
				}
				result.put("tempCargoItemsDesc",
						issuePortClearance.getTransitCargo());
				if (tempCargoItems.size() == 0) {
					TempGeneralDeclaration reDeclaration = TempGeneralDeclarationLocalServiceUtil
							.getLastByDocumentNameAndDocumentYear(documentName,
									documentYear);
					if (Validator.isNotNull(reDeclaration)) {
						for (TempCargoItems tempCargoItems2 : tempCargoItemsOrg) {
							if ((tempCargoItems2.getCargoCategory().endsWith(
									"_VC")
									|| tempCargoItems2.getCargoCategory()
											.equalsIgnoreCase("VC") || tempCargoItems2
									.getCargoCategory().equalsIgnoreCase("C5"))
									&& reDeclaration.getRequestCode().equals(
											tempCargoItems2.getRequestCode())) {
								tempCargoItems2
										.setRequestCode(StringPool.BLANK);
								TempCargoItems details = tempCargoItems2;
								String tmp = "";
								if (Validator.isNotNull(details.getCargoType())
										&& Validator.isNotNull(details
												.getUnit())) {
									tmp = DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_NHOM_HANG_HOA,
													details.getCargoType())
											.getName()
											+ ", "
											+ DmDataItemLocalServiceUtil
													.findByDataGroupIdAndCode0(
															Constants.DM_TEN_HANG_HOA,
															details.getCargoCode())
													.getName()
											+ "  "
											+ details.getQuantity()
											+ " "
											+ DmDataItemLocalServiceUtil
													.findByDataGroupIdAndCode0(
															Constants.DM_DON_VI_TINH,
															details.getUnit())
													.getName() + " \n ";
								} else if (Validator.isNotNull(details
										.getCargoType())
										&& details.getCargoType()
												.equalsIgnoreCase("11")) // NIL-KHONG
																			// CHO
																			// HANG
								{
									tmp = DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_NHOM_HANG_HOA,
													details.getCargoType())
											.getName()
											+ " \n ";
								}
								if (tempCargoItems2.getCargoCategory()
										.equalsIgnoreCase("C5")) {
									cargoDescription_C1_XEP.append(tmp);
								}

								tempCargoItems2.setDescription(tmp);
								tempCargoItems.add(tempCargoItems2);
							}
						}
					}
					result.put("tempCargoItemsDesc",
							cargoDescription_C1_XEP.toString());
				}
			} else {
				TempGeneralDeclaration reDeclaration = TempGeneralDeclarationLocalServiceUtil
						.getLastByDocumentNameAndDocumentYear(documentName,
								documentYear);
				if (Validator.isNotNull(reDeclaration)) {
					for (TempCargoItems tempCargoItems2 : tempCargoItemsOrg) {
						if ((tempCargoItems2.getCargoCategory().endsWith("_VC")
								|| tempCargoItems2.getCargoCategory()
										.equalsIgnoreCase("VC") || tempCargoItems2
								.getCargoCategory().equalsIgnoreCase("C5"))
								&& Validator.isNotNull(reDeclaration)
								&& reDeclaration.getRequestCode().equals(
										tempCargoItems2.getRequestCode())) {
							tempCargoItems2.setRequestCode(StringPool.BLANK);
							TempCargoItems details = tempCargoItems2;
							String tmp = "";
							if (Validator.isNotNull(details.getCargoType())
									&& Validator.isNotNull(details.getUnit())) {
								tmp = DmDataItemLocalServiceUtil
										.findByDataGroupIdAndCode0(
												Constants.DM_NHOM_HANG_HOA,
												details.getCargoType())
										.getName()
										+ ", "
										+ DmDataItemLocalServiceUtil
												.findByDataGroupIdAndCode0(
														Constants.DM_TEN_HANG_HOA,
														details.getCargoCode())
												.getName()
										+ "  "
										+ details.getQuantity()
										+ " "
										+ DmDataItemLocalServiceUtil
												.findByDataGroupIdAndCode0(
														Constants.DM_DON_VI_TINH,
														details.getUnit())
												.getName() + " \n ";
							} else if (Validator.isNotNull(details
									.getCargoType())
									&& details.getCargoType().equalsIgnoreCase(
											"11")) // NIL-KHONG CHO HANG
							{
								tmp = DmDataItemLocalServiceUtil
										.findByDataGroupIdAndCode0(
												Constants.DM_NHOM_HANG_HOA,
												details.getCargoType())
										.getName()
										+ " \n ";
							}
							if (tempCargoItems2.getCargoCategory()
									.equalsIgnoreCase("C5")) {
								cargoDescription_C1_XEP.append(tmp);
							}
							tempCargoItems.add(tempCargoItems2);
						}
					}
				}
				result.put("tempCargoItemsDesc",
						cargoDescription_C1_XEP.toString());
			}

			JSONArray arrayData = JSONFactoryUtil.createJSONArray();

			if (Validator.isNotNull(tempCargoItems)
					&& tempCargoItems.size() > 0) {
				arrayData = JSONFactoryUtil.createJSONArray(JSONFactoryUtil
						.looseSerialize(tempCargoItems));
			}

			result.put("tempCargoItems", arrayData);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return result;
	}

	public static JSONObject getMessageType90EXT(ThemeDisplay themeDisplay,
			long documentName, int documentYear, int messageType, int roleType,
			String requestCode, HttpServletRequest request) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {

			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);

			TempNoticeShipMessage thongBaoLast = TempNoTiceShipMessageLocalServiceUtil
					.getThongBaoLastByDocumentNameAndDocumentYear(documentName,
							documentYear);
			TempGeneralDeclaration banKhaiChungLast = TempGeneralDeclarationLocalServiceUtil
					.getLastByDocumentNameAndDocumentYear(documentName,
							documentYear);

			DmState dmState = DmStateLocalServiceUtil.getByStateCode(Validator
					.isNotNull(banKhaiChungLast) ? banKhaiChungLast
					.getFlagStateOfShip() : tempDocument.getStateCode());

			String maStCode = "";
			if (dmState == null) {
				maStCode = "";
			} else {
				maStCode = dmState.getStateCode();
			}

			String stateName = StringPool.BLANK;

			List<DmState> lstState = DmStateLocalServiceUtil.getDmStates(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			JSONArray stateArrays = JSONFactoryUtil.createJSONArray();
			JSONObject stateObj = null;
			if (lstState != null && lstState.size() > 0) {
				for (DmState state : lstState) {
					stateObj = JSONFactoryUtil.createJSONObject();

					if (maStCode.equals(state.getStateCode())) {
						stateName = state.getStateName();
					}
					stateObj.put("stateCode", state.getStateCode());
					stateObj.put("stateName", state.getStateName());

					stateArrays.put(stateObj);
				}
			}

			result.put("lstState", stateArrays);

			Date sTimeOfDeparture = new Date();
			List<IssuePermissionForTransit> results = IssuePermissionForTransitLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.ID,
							KeyParams.ORDER_BY_DESC);
			IssuePermissionForTransit portClearanceEdit = null;
			if (results != null && results.size() > 0) {
				portClearanceEdit = results.get(0);

				if (Validator.isNotNull(portClearanceEdit.getTimeOfDeparture())) {
					sTimeOfDeparture = portClearanceEdit.getTimeOfDeparture();
				} else {
					sTimeOfDeparture = Validator.isNotNull(banKhaiChungLast) ? banKhaiChungLast
							.getDateOfArrival() : new Date();
				}

			}
			Date gprc = new Date();

			if (results != null && results.size() == 0) {
				gprc = tempDocument.getShipDateTo();
			} else {
				gprc = sTimeOfDeparture;
			}

			if (Validator.isNull(gprc)) {
				gprc = new Date();
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(gprc);
			cal.add(Calendar.DATE, 1);

			DmMaritime maritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(tempDocument.getMaritimeCode());
			if (Validator.isNull(maritime)) {
				maritime = new DmMaritime();
			}

			List<DmUnitGeneral> units = DmUnitGeneralLocalServiceUtil
					.getDmUnitGenerals(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			if (Validator.isNull(units)) {
				units = new ArrayList<DmUnitGeneral>();
			}

			result.put(
					"representative",
					Validator.isNotNull(portClearanceEdit) ? portClearanceEdit
							.getRepresentative() : "GI\u00C1M \u0110\u1ED0C");
			result.put("nameOfship", tempDocument.getShipName());
			result.put("stateName", stateName);
			result.put("callSign", tempDocument.getCallSign());
			result.put("grt", tempDocument.getGrt());
			result.put("nt", tempDocument.getNt());
			result.put("dwt", tempDocument.getDwt());
			result.put("nameOfMaster", tempDocument.getShipCaptain());
			result.put("numberOfCrews", banKhaiChungLast.getNumberOfCrew());
			result.put("numberOfPassengers",
					banKhaiChungLast.getNumberOfPassengers());

			result.put(
					"transitOfCargo",
					Validator.isNotNull(portClearanceEdit) ? portClearanceEdit
							.getTransitCargo() : "As per Manifest");
			result.put(
					"timeOfDeparture",
					Validator.isNotNull(portClearanceEdit)
							&& Validator.isNotNull(portClearanceEdit
									.getTimeOfDeparture()) ? portClearanceEdit
							.getTimeOfDeparture().getTime() : gprc.getTime());
			result.put("timeOfValidUntil", cal.getTime().getTime());

			String sPermittedTransitTo = Validator.isNotNull(portClearanceEdit) ? portClearanceEdit
					.getPermittedTransitTo() : lstState.get(0).getStateCode();

			result.put("cangDen", sPermittedTransitTo);
			result.put("issueDate", new Date().getTime());
			result.put(
					"issueDate",
					Validator.isNotNull(portClearanceEdit)
							&& Validator.isNotNull(portClearanceEdit
									.getApprovalDate()) ? portClearanceEdit
							.getApprovalDate().getTime() : new Date().getTime());
			result.put(
					"certificateNo",
					Validator.isNotNull(portClearanceEdit) ? ConvertUtil
							.getValueFromStringList(
									portClearanceEdit.getCertificateNo(), "/",
									0) : StringPool.BLANK);

			List<DmPortRegion> lstRegion = DmPortRegionLocalServiceUtil
					.getDmPortRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			String sPermittedTransitFrom = Validator
					.isNotNull(portClearanceEdit) ? portClearanceEdit
					.getPermittedTransitFrom() : lstRegion.get(0)
					.getPortRegionCode();

			result.put("cangRoi", sPermittedTransitFrom);
			result.put(
					"volumeTransitCargo",
					Validator.isNotNull(portClearanceEdit) ? String
							.valueOf(portClearanceEdit.getVolumeCargo()) : "0");

			String sUnitVolumeTransitCargo = Validator
					.isNotNull(portClearanceEdit) ? portClearanceEdit
					.getCargoUnit() : "";

			JSONArray unitsArrays = JSONFactoryUtil.createJSONArray();
			JSONObject unit = null;
			for (DmUnitGeneral dmUnitGeneral : units) {
				unit = JSONFactoryUtil.createJSONObject();

				unit.put("unitCode", dmUnitGeneral.getUnitCode());
				unit.put("unitName", dmUnitGeneral.getUnitName());

				unitsArrays.put(unit);
			}
			result.put("unitVolumeTransitCargoItems", unitsArrays);
			result.put("unitVolumeTransitCargo", sUnitVolumeTransitCargo);
			String displayCertificateNoUnit = Validator
					.isNotNull(portClearanceEdit) ? ConvertUtil
					.getValueFromStringList(
							portClearanceEdit.getCertificateNo(), "/", 1)
					: StringPool.BLANK;
			String maritimeReference = Validator.isNotNull(maritime) ? maritime
					.getMaritimeReference() : StringPool.BLANK;
			result.put(
					"unitCertificateNo",
					!StringPool.BLANK.equals(displayCertificateNoUnit) ? displayCertificateNoUnit
							: maritimeReference);

			// list hang hoa nguy hiem
			List<DmDataitem> cargoType = DmDataItemLocalServiceUtil
					.findByDataGroupId(Constants.DM_NHOM_HANG_HOA);
			if (Validator.isNull(cargoType)) {
				cargoType = new ArrayList<DmDataitem>();
			}

			List<DmDataitem> cargoCode = DmDataItemLocalServiceUtil
					.findByDataGroupId(Constants.DM_TEN_HANG_HOA);
			if (Validator.isNull(cargoCode)) {
				cargoCode = new ArrayList<DmDataitem>();
			}

			List<DmDataitem> unitCargo = DmDataItemLocalServiceUtil
					.findByDataGroupId(Constants.DM_DON_VI_TINH);
			if (Validator.isNull(unitCargo)) {
				unitCargo = new ArrayList<DmDataitem>();
			}

			JSONArray regionArrays = JSONFactoryUtil.createJSONArray();
			JSONObject region = null;
			for (DmPortRegion dmPortRegion : lstRegion) {
				region = JSONFactoryUtil.createJSONObject();

				region.put("portRegionCode", dmPortRegion.getPortRegionCode());
				region.put("portRegionNameVN",
						dmPortRegion.getPortRegionNameVN());

				regionArrays.put(region);
			}

			result.put("cargoType", JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(cargoType)));
			result.put("cargoCode", JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(cargoCode)));
			result.put("unitCargo", JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(unitCargo)));
			result.put("lstRegion", regionArrays);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject createMenu(Locale locale) {
		JSONObject object = JSONFactoryUtil.createJSONObject();
		JSONArray childs = JSONFactoryUtil.createJSONArray();

		object.put("action", "folder");
		object.put("action_active", "folder_open");
		object.put("id", -3);
		object.put("id_active", "quan_ly_tau");
		object.put("type", -3);
		object.put("title", LanguageUtil.get(locale, "quan_ly_tau_bien_pttnd"));
		object.put("active", false);
		object.put("index", 3);

		// TODO order
		JSONObject objectChilds = JSONFactoryUtil.createJSONObject();

		objectChilds.put("id", 30);
		objectChilds.put("code", 300);
		objectChilds.put("title", LanguageUtil.get(locale, "tau_bien"));
		objectChilds.put("type", "DanhSachTauBien");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();

		objectChilds.put("id", 31);
		objectChilds.put("code", 301);
		objectChilds.put("title", LanguageUtil.get(locale, "phuong_tien_tnd"));
		objectChilds.put("type", "DanhSachPhuongTienThuyNoiDia");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);
		
		objectChilds = JSONFactoryUtil.createJSONObject();

		objectChilds.put("id", 32);
		objectChilds.put("code", 302);
		objectChilds.put("title", LanguageUtil.get(locale, "vi_tri_neo_dau_tai_cang"));
		objectChilds.put("type", "DanhSachViTriNeoDauTaiCang");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();

		objectChilds.put("id", 33);
		objectChilds.put("code", 303);
		objectChilds.put("title", LanguageUtil.get(locale, "ke_hoach_tau_roi_cang"));
		objectChilds.put("type", "DanhSachTauRoiCang");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();

		objectChilds.put("id", 34);
		objectChilds.put("code", 304);
		objectChilds.put("title", LanguageUtil.get(locale, "ke_hoach_tau_den_cang"));
		objectChilds.put("type", "DanhSachTauDenCang");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 35);
		objectChilds.put("code", 305);
		objectChilds.put("title", LanguageUtil.get(locale, "ke_hoach_tau_dich_chuyen"));
		objectChilds.put("type", "DanhSachTauDiChuyen");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 36);
		objectChilds.put("code", 306);
		objectChilds.put("title", LanguageUtil.get(locale, "tau_neo_dau"));
		objectChilds.put("type", "DanhSachNeoTau");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 37);
		objectChilds.put("code", 307);
		objectChilds.put("title", LanguageUtil.get(locale, "tau_lai_ho_tro"));
		objectChilds.put("type", "DanhSachTauLaiHoTro");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 38);
		objectChilds.put("code", 308);
		objectChilds.put("title", LanguageUtil.get(locale, "hoa_tieu_dan_tau"));
		objectChilds.put("type", "DanhSachHoaTieuDanTau");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 39);
		objectChilds.put("code", 309);
		objectChilds.put("title", LanguageUtil.get(locale, "xep_do_hang"));
		objectChilds.put("type", "DanhSachXepDoHang");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 40);
		objectChilds.put("code", 400);
		objectChilds.put("title", LanguageUtil.get(locale, "nhap_tach_doan"));
		objectChilds.put("type", "DanhSachNhapTachDoan");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 41);
		objectChilds.put("code", 401);
		objectChilds.put("title", LanguageUtil.get(locale, "sua_chua"));
		objectChilds.put("type", "DanhSachSuaChuaTau");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 42);
		objectChilds.put("code", 402);
		objectChilds.put("title", LanguageUtil.get(locale, "ha_xuong"));
		objectChilds.put("type", "DanhSachHaXuong");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 43);
		objectChilds.put("code", 403);
		objectChilds.put("title", LanguageUtil.get(locale, "thu_tau"));
		objectChilds.put("type", "DanhSachThuTau");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 44);
		objectChilds.put("code", 404);
		objectChilds.put("title", LanguageUtil.get(locale, "giu_tau"));
		objectChilds.put("type", "DanhSachDuTau");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 45);
		objectChilds.put("code", 405);
		objectChilds.put("title", LanguageUtil.get(locale, "ghi_chu_canh_bao"));
		objectChilds.put("type", "DanhSachGhiChuCanhBao");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 46);
		objectChilds.put("code", 406);
		objectChilds.put("title", LanguageUtil.get(locale, "khang_nghi_hang_hai"));
		objectChilds.put("type", "DanhSachKhangNghiHangHai");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		objectChilds = JSONFactoryUtil.createJSONObject();
		objectChilds.put("id", 47);
		objectChilds.put("code", 407);
		objectChilds.put("title", LanguageUtil.get(locale, "kh_tau_chay_chuyen_tuyen"));
		objectChilds.put("type", "DanhSachKeHoachChuyenTuyen");
		objectChilds.put("counter", "_ _");
		childs.put(objectChilds);
		object.put("items", childs);

		return object;
	}
/*
	public static JSONObject getVMA_LenhDieuDongDiChuyenTrongVungNuoc(ThemeDisplay themeDisplay,
			long documentName, int documentYear, HttpServletRequest request) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			TempDocument tempDoc = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);
			
		} catch (Exception e) {
			result.put("ItineraryNo", 0);
		}
		
		return result;
	}

	public static JSONObject getVMA_Notify_LenhDieuDongLuotVaoRoi(ThemeDisplay themeDisplay,
			long documentName, int documentYear, String ItineraryNo, int LuotVaoRoi, HttpServletRequest request) {
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			TempDocument tempDoc = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);
			
		} catch (Exception e) {
			result.put("ItineraryNo", 0);
		}
		
		return result;
	}	
	*/

	public static JSONObject getVMA_LenhDieuDongLuotVaoRoi(ThemeDisplay themeDisplay,
			long documentName, int documentYear, String ItineraryNo, int LuotVaoRoi, HttpServletRequest request) {
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		// 1. Bo sung ham lay VmaItinerary theo DocumentName, DocumentYear;
		// 2. Bo sung ham lay VmaItinerarySchedule theo luot vao, roi
		
		VmaItinerary objItinerary = new VmaItinerary();
		// Bo sung ham lay VmaItinerary theo DocumentName, DocumentYear;
		// VmaItineraryLocalServiceUtil.
		List<VmaItinerarySchedule> lstItinerarySchedule = new ArrayList<VmaItinerarySchedule>();
		// Bo sung ham lay VmaItinerarySchedule theo luot vao, roi
		// VmaItineraryScheduleLocalServiceUtil
		VmaItinerarySchedule objItinerarySchedule = new VmaItinerarySchedule();
		
		List<IssueShiftingOrder> results = new ArrayList<IssueShiftingOrder>(
				IssueShiftingOrderLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(
								documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC));

		UserPort portDefault = UserPortLocalServiceUtil
				.findByUserId(themeDisplay.getUserId());
		List<DmPort> ports = null;

		if (portDefault != null) {
			ports = DmPortLocalServiceUtil.findByLoCode(portDefault
					.getPortCode());
		}

		if (ports == null) {
			ports = new ArrayList<DmPort>();
		}

		JSONArray portsArrays = JSONFactoryUtil.createJSONArray();
		JSONObject port = null;
		for (DmPort dmPort : ports) {
			port = JSONFactoryUtil.createJSONObject();

			port.put("portCode", dmPort.getPortCode());
			port.put("portName", dmPort.getPortName());

			portsArrays.put(port);
		}
		result.put("ports", portsArrays);

		TempDocument tempDoc = TempDocumentLocalServiceUtil
				.findTemDocumentByDocumentNameDocumentYear(documentName,
						documentYear);

		DmMaritime maritime = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(tempDoc.getMaritimeCode());
		if (Validator.isNull(maritime)) {
			maritime = new DmMaritime();
		}

		result.put("maritimeCityName", maritime.getCityCode());

		DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
				.getByPortRegionCode(tempDoc.getPortRegionCode());

		result.put("portRegionName", dmPortRegion.getPortRegionNameVN());

		// PortHarbour la ben cang,
		List<DmPortHarbour> lstPortHarbour = DmPortHarbourLocalServiceUtil
				.findByPortRegionCode(tempDoc.getMaritimeCode());
		if (Validator.isNull(lstPortHarbour)) {
			lstPortHarbour = new ArrayList<DmPortHarbour>();
		}

		portsArrays = JSONFactoryUtil.createJSONArray();
		port = null;
		for (DmPortHarbour dmPort : lstPortHarbour) {
			port = JSONFactoryUtil.createJSONObject();

			port.put("portHarbourCode", dmPort.getPortHarbourCode());
			port.put("portHarbourName", dmPort.getPortHarbourNameVN());

			portsArrays.put(port);
		}
		result.put("lstPortHarbour", portsArrays);
	
		String portHarborCode = lstPortHarbour.get(0).getPortHarbourCode();

		List<DmPortWharf> lstPortWharf = new ArrayList<DmPortWharf>();

		if (result != null && results.size() > 0) {
			try {
				result.put("detail", JSONFactoryUtil
						.createJSONObject(JSONFactoryUtil
								.looseSerialize(results.get(0))));

				List<IssueShiftingOrderChanel> lstChanel = IssueShiftingOrderChanelLocalServiceUtil
						.findByShiftingOrderId(results.get(0).getId());

				portsArrays = JSONFactoryUtil.createJSONArray();
				port = null;
				for (IssueShiftingOrderChanel issueShiftingOrderChanel : lstChanel) {
					port = JSONFactoryUtil.createJSONObject();

					port.put("code0", issueShiftingOrderChanel.getId().getChanelCode());
					port.put("name", issueShiftingOrderChanel.getChanelName());

					portsArrays.put(port);
				}

				result.put("chanel", portsArrays);

				try {
					List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
							.findBydocumentNameAndDocumentYearAndNoticeShipType(
									documentName, documentYear,
									PageType.TYPE_THONG_BAO_TAU_DEN_CANG);

					if (tempNoTiceShipMessages != null
							&& tempNoTiceShipMessages.size() > 0) {
						TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
								.get(0);
						lstPortWharf = new ArrayList<DmPortWharf>();

						lstPortWharf = DmPortWharfLocalServiceUtil
								.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
										tempNoTiceShipMessage
												.getPortRegionCode(),
										tempNoTiceShipMessage
												.getPortHarbourCode(),
										KeyParams.ORDER_BY_ASC);

						portsArrays = JSONFactoryUtil.createJSONArray();
						port = null;
						for (DmPortWharf dmPort : lstPortWharf) {
							port = JSONFactoryUtil.createJSONObject();

							port.put("portWharfCode", dmPort.getPortWharfCode());
							port.put("portWharfName",
									dmPort.getPortWharfNameVN());

							portsArrays.put(port);
						}
						result.put("lstPortWharf", portsArrays);

					}

					tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
							.findBydocumentNameAndDocumentYearAndNoticeShipType(
									documentName, documentYear,
									PageType.TYPE_XAC_BAO_TAU_DEN_CANG);

					if (tempNoTiceShipMessages != null
							&& tempNoTiceShipMessages.size() > 0) {
						TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
								.get(0);

						lstPortWharf = new ArrayList<DmPortWharf>();

						lstPortWharf = DmPortWharfLocalServiceUtil
								.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
										tempNoTiceShipMessage
												.getPortRegionCode(),
										tempNoTiceShipMessage
												.getPortHarbourCode(),
										KeyParams.ORDER_BY_ASC);

						portsArrays = JSONFactoryUtil.createJSONArray();
						port = null;
						for (DmPortWharf dmPort : lstPortWharf) {
							port = JSONFactoryUtil.createJSONObject();

							port.put("portWharfCode", dmPort.getPortWharfCode());
							port.put("portWharfName",
									dmPort.getPortWharfNameVN());

							portsArrays.put(port);
						}
						result.put("lstPortWharf", portsArrays);

					}

				} catch (Exception e) {
					log.error(e.getMessage());
				}

			} catch (JSONException e) {
				log.error(e.getMessage());
			}
		} else {
			IssueShiftingOrder issueShiftingOrder = new IssueShiftingOrder();

			try {
				List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
						.findBydocumentNameAndDocumentYearAndNoticeShipType(
								documentName, documentYear,
								PageType.TYPE_THONG_BAO_TAU_DEN_CANG);

				if (tempNoTiceShipMessages != null
						&& tempNoTiceShipMessages.size() > 0) {
					TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
							.get(0);

					issueShiftingOrder
							.setTaxCodeOfShipownersAgents(tempNoTiceShipMessage
									.getShipOwnerStateCode());
					issueShiftingOrder
							.setNameOfShipownersAgents(tempNoTiceShipMessage
									.getNameOfShipOwners());
					issueShiftingOrder
							.setShipAgencyAddress(tempNoTiceShipMessage
									.getShipAgencyAddress());
					issueShiftingOrder.setShipAgencyPhone(tempNoTiceShipMessage
							.getShipAgencyPhone());
					issueShiftingOrder.setShipAgencyFax(tempNoTiceShipMessage
							.getShipAgencyFax());
					issueShiftingOrder.setShipAgencyEmail(tempNoTiceShipMessage
							.getShipAgencyEmail());
					issueShiftingOrder.setTugBoat(tempNoTiceShipMessage
							.getTugBoat());
					issueShiftingOrder.setShownDraftxA(tempNoTiceShipMessage
							.getShownDraftxA());
					issueShiftingOrder.setShownDraftxF(tempNoTiceShipMessage
							.getShownDraftxF());
					issueShiftingOrder.setLoa(tempNoTiceShipMessage.getLoa());
					issueShiftingOrder.setDwt(tempNoTiceShipMessage.getDwt());
					issueShiftingOrder.setNameOfShip(tempNoTiceShipMessage
							.getShipName());

					// DmPort dmPortArrival =
					// DmPortLocalServiceUtil.fetchByPortCodeLoCode(tempNoTiceShipMessage.getArrivalPortCode(),
					// portDefault.getPortCode());

					// String arrivalPortName = StringPool.BLANK;
					//
					// if (Validator.isNotNull(dmPortArrival)) {
					//
					// arrivalPortName = dmPortArrival.getPortName();
					//
					// }
					//
					// issueShiftingOrder.setTo(arrivalPortName);

					DmPort dmPortArrival = DmPortLocalServiceUtil
							.getByPortCode(tempNoTiceShipMessage
									.getPortGoingToCode());

					issueShiftingOrder.setFrom(Validator
							.isNotNull(dmPortArrival) ? dmPortArrival
							.getPortName() : StringPool.BLANK);

					issueShiftingOrder
							.setRepresentative("GI\u00C1M \u0110\u1ED0C");

					issueShiftingOrder
							.setAnchoringPortCode(tempNoTiceShipMessage
									.getArrivalPortCode());

					issueShiftingOrder.setPortHarbourCode(tempNoTiceShipMessage
							.getPortHarbourCode());

					issueShiftingOrder
							.setShiftingPortWharfCode(tempNoTiceShipMessage
									.getPortWharfCode());

					// issueShiftingOrder.setAnchoringPortWharfCode(tempNoTiceShipMessage.getPortWharfCode());

					issueShiftingOrder.setShiftingDate(tempNoTiceShipMessage
							.getArrivalDate());

					lstPortWharf = new ArrayList<DmPortWharf>();

					lstPortWharf = DmPortWharfLocalServiceUtil
							.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
									tempNoTiceShipMessage.getPortRegionCode(),
									tempNoTiceShipMessage.getPortHarbourCode(),
									KeyParams.ORDER_BY_ASC);

					portsArrays = JSONFactoryUtil.createJSONArray();
					port = null;
					for (DmPortWharf dmPort : lstPortWharf) {
						port = JSONFactoryUtil.createJSONObject();

						port.put("portWharfCode", dmPort.getPortWharfCode());
						port.put("portWharfName", dmPort.getPortWharfNameVN());

						portsArrays.put(port);
					}
					result.put("lstPortWharf", portsArrays);

				}

				tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
						.findBydocumentNameAndDocumentYearAndNoticeShipType(
								documentName, documentYear,
								PageType.TYPE_XAC_BAO_TAU_DEN_CANG);

				if (tempNoTiceShipMessages != null
						&& tempNoTiceShipMessages.size() > 0) {
					TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages
							.get(0);

					lstPortWharf = new ArrayList<DmPortWharf>();

					lstPortWharf = DmPortWharfLocalServiceUtil
							.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
									tempNoTiceShipMessage.getPortRegionCode(),
									tempNoTiceShipMessage.getPortHarbourCode(),
									KeyParams.ORDER_BY_ASC);

					portsArrays = JSONFactoryUtil.createJSONArray();
					port = null;
					for (DmPortWharf dmPort : lstPortWharf) {
						port = JSONFactoryUtil.createJSONObject();

						port.put("portWharfCode", dmPort.getPortWharfCode());
						port.put("portWharfName", dmPort.getPortWharfNameVN());

						portsArrays.put(port);
					}
					result.put("lstPortWharf", portsArrays);

					issueShiftingOrder.setShiftingDate(tempNoTiceShipMessage
							.getArrivalDate());

					issueShiftingOrder
							.setAnchoringPortCode(tempNoTiceShipMessage
									.getArrivalPortCode());

					issueShiftingOrder.setPortHarbourCode(tempNoTiceShipMessage
							.getPortHarbourCode());

					issueShiftingOrder
							.setShiftingPortWharfCode(tempNoTiceShipMessage
									.getPortWharfCode());
					//
					// DmPort dmPortArrival =
					// DmPortLocalServiceUtil.getByPortCode(tempNoTiceShipMessage.getPortGoingToCode());
					//
					// issueShiftingOrder.setFrom(Validator.isNotNull(dmPortArrival)
					// ? dmPortArrival.getPortName() : StringPool.BLANK);
					//
				}

				result.put("detail", JSONFactoryUtil
						.createJSONObject(JSONFactoryUtil
								.looseSerialize(issueShiftingOrder)));
				result.put("chanel", JSONFactoryUtil.createJSONArray());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		List<DmDataitem> changels = DmDataItemLocalServiceUtil
				.findByDataGroupIdAndNode1(Constants.DM_TUYEN_LUONG,
						tempDoc.getMaritimeCode());

		if (Validator.isNull(changels)) {
			changels = new ArrayList<DmDataitem>();
		}

		try {
			result.put("chanels", JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(changels)));
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("chanels", JSONFactoryUtil.createJSONArray());
		}

		return result;
	}
}
