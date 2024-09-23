package vn.gt.portlet.phuongtien;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import vn.gt.constant.Constants;
import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmUnitGeneralLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryScheduleException;
import com.fds.nsw.nghiepvu.model.VmaCertificate;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage;
import com.fds.nsw.nghiepvu.model.VmaScheduleCargolist;
import com.fds.nsw.nghiepvu.model.VmaScheduleMerging;
import com.fds.nsw.nghiepvu.model.VmaSchedulePilot;
import com.fds.nsw.nghiepvu.model.VmaSchedulePilotList;
import com.fds.nsw.nghiepvu.model.VmaScheduleTransfer;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboat;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList;
import com.fds.nsw.nghiepvu.model.VmaShip;







import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaCertificateLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleAnchorageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleCargolistLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleMergingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTransferLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaShipLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.tichhop.message.DongBoVMABussinessUtils;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;

import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import org.json.JSONException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.core.ThemeDisplay;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaItineraryScheduleUtils
 {
	

	// Xac dinh thu tu chuyen di so may trong thang
	public static long getNumberOfArrivalDepartureInMonth(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(request, "itineraryNo");

		VmaItinerarySchedule vmaItinerarySchedule = null;

		int noticeShipType = ParamUtil.getInteger(request, "noticeShipType", 2);

		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);

		String imoNumber = ParamUtil.getString(request, "imoNumber",
				StringPool.BLANK);

		int toMonth = 0;
		int toYear = 0;
		Date date = null;
		long soChuyen = 0;

		VmaItinerary vmaItinerary = null;

		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.findByitineraryNo(itineraryNo);

			if (vmaItinerary != null) {
				date = vmaItinerary.getTimeOfDeparture();
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			toMonth = c.get(Calendar.MONTH) + 1;
			toYear = c.get(Calendar.YEAR);

			VmaItinerarySchedule vmaItineraryScheduleDeparture = null;			
			vmaItineraryScheduleDeparture = VmaItineraryScheduleLocalServiceUtil
					.findByItineraryNo_NoticeShipType(
							vmaItinerary.getItineraryNo(), 2);
			String portRegionCode = StringPool.BLANK;
			if (vmaItineraryScheduleDeparture != null) {
				portRegionCode = vmaItineraryScheduleDeparture.getPortRegionCode();
			}
			
			soChuyen = VMAUtils.thuTuSoChuyen(portRegionCode, imoNumber, callSign,
					vmaItinerary.getRegistryNumber(), FormatData
							.parseDateToTringType3(vmaItinerary
									.getTimeOfDeparture()), toMonth, toYear);
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return soChuyen;
	}

	/*
	 * private static String generateQuery(String imoNumber, String callSign,
	 * String portRegionCode, int month, int year, Date date) {
	 * 
	 * String sql =
	 * "SELECT count(DISTINCT a.ItineraryNo) as total FROM vma_itinerary as a INNER JOIN vma_itinerary_schedule as b ON a.itineraryNo = b.itineraryNo AND b.noticeShipType IN (1,2)"
	 * ;
	 * 
	 * StringBuilder condition = new StringBuilder();
	 * 
	 * condition.append(" WHERE 1 = 1 ");
	 * 
	 * if (Validator.isNotNull(portRegionCode)) {
	 * condition.append(VMAUtils.buildSQLCondition("PortRegionCode", "'" +
	 * portRegionCode + "'", "AND", StringPool.EQUAL, new String[] { "b" })); }
	 * 
	 * if (Validator.isNotNull(imoNumber)) {
	 * condition.append(VMAUtils.buildSQLCondition("IMONumber", "'" + imoNumber
	 * + "'", "AND", StringPool.EQUAL, new String[] { "a" })); }
	 * 
	 * if (Validator.isNotNull(callSign)) {
	 * condition.append(VMAUtils.buildSQLCondition("CallSign", "'" + callSign +
	 * "'", "AND", StringPool.EQUAL, new String[] { "a" })); }
	 * 
	 * if (Validator.isNotNull(month)) {
	 * condition.append(" AND Month(a.timeOfArrival) = " + month); }
	 * 
	 * if (Validator.isNotNull(year)) {
	 * condition.append(" AND Year(a.timeOfArrival) = " + year); }
	 * 
	 * if (Validator.isNotNull(month)) {
	 * condition.append(" AND Month(a.timeOfDeparture) = " + month); }
	 * 
	 * if (Validator.isNotNull(year)) {
	 * condition.append(" AND Year(a.timeOfDeparture) = " + year); }
	 * 
	 * if (Validator.isNotNull(date)) { Calendar calendar =
	 * Calendar.getInstance(); calendar.setTime(date); String fromDate =
	 * calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) +
	 * "-1"; String toDate = FormatData.parseDateToTring(date);
	 * condition.append(" AND a.timeOfArrival BETWEEN '" + fromDate + "' AND '"
	 * + toDate + "'"); }
	 * 
	 * condition.append(VMAUtils.buildSQLCondition("markedAsArrival", 6, "AND",
	 * "!=", new String[] { "a" }));
	 * 
	 * condition.append(VMAUtils.buildSQLCondition("markedAsDeparture", 6,
	 * "AND", "!=", new String[] { "a" }));
	 * 
	 * return sql + condition.toString(); }
	 */

	public static JSONObject initItinerarySchedule(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		/*
		 * ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest
		 * .getAttribute(WebKeys.THEME_DISPLAY);
		 * 
		 * JSONObject result = JSONFactoryUtil.createJSONObject();
		 */

		long documentName = ParamUtil.getLong(request, "documentName");
		int documentYear = ParamUtil.getInteger(request, "documentYear");
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");

		try {
			return DongBoVMABussinessUtils.setKhoiTaoKeHoach(documentName,
					documentYear, itineraryNo);
		} catch (Exception e) {

			log.error(e.getMessage());

			return JSONFactoryUtil.createJSONObject();
		}
	}

	public static JSONObject getNotifyItinerary(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);

		long documentName = ParamUtil.getLong(request, "documentName");
		int documentYear = ParamUtil.getInteger(request, "documentYear");
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");

		try {
			return DongBoVMABussinessUtils.getCanhBaoLenhDieuDongLuotVaoRoi(
					themeDisplay, documentName, documentYear, itineraryNo,
					request);
		} catch (Exception e) {

			log.error(e.getMessage());

			return JSONFactoryUtil.createJSONObject();
		}
	}

	public static JSONObject findByItineraryNo_NoticeShipType(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);

		JSONObject result = JSONFactoryUtil.createJSONObject();

		String itineraryNo = ParamUtil.getString(request, "itineraryNo");

		int noticeShipType = ParamUtil.getInteger(request, "noticeShipType");

		VmaItinerary vmaItinerary = null;

		VmaItinerarySchedule vmaItinerarySchedule = null;

		if (Validator.isNotNull(itineraryNo)) {
			try {
				if (noticeShipType != 4) {
					vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo,
									noticeShipType);
				} else {
					// Get last
					List<VmaItinerarySchedule> lstvmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo(itineraryNo);
					long max = 0;
					for (VmaItinerarySchedule itinerarySchedule : lstvmaItinerarySchedule) {
						if (itinerarySchedule.getId() > max
								&& itinerarySchedule.getNoticeShipType() == noticeShipType) {
							max = itinerarySchedule.getId();
							vmaItinerarySchedule = itinerarySchedule;
						}
					}
				}

			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		if (vmaItinerarySchedule == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(itineraryNo);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (vmaItinerary == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {

			result = VMAUtils.object2Json(vmaItinerary,
					VmaItinerary.class);

			JSONObject tmp = VMAUtils.object2Json(vmaItinerarySchedule,
					VmaItinerarySchedule.class);

			result = VMAUtils.mergeJSON(result, tmp,
					VmaItinerarySchedule.class);
			
			int countMerging = VmaScheduleMergingLocalServiceUtil
					.countByItineraryNo(vmaItinerary.getItineraryNo());
			if (countMerging > 0) {
				result.put("payment2Merging", 1);
			} else {
				result.put("payment2Merging", 0);
			}
			
			int TINHPHI_TAUBIEN = 1;
			int TINHPHI_TAUBIEN_VAO = 0;
			int TINHPHI_TAUBIEN_ROI = 0;
			int TINHPHI_PTTND = 0;
			int TINHPHI_PTTND_VAO = 0;
			int TINHPHI_PTTND_ROI = 0;
			int TINHPHI_DOANLAI = 0;	
			
			if (countMerging > 0) {
				TINHPHI_TAUBIEN = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN",
						vmaItinerary, null );
				TINHPHI_TAUBIEN_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN_VAO",
						vmaItinerary, null );
				TINHPHI_TAUBIEN_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN_ROI",
						vmaItinerary, null );
				TINHPHI_PTTND = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND",
						vmaItinerary, null );
				TINHPHI_PTTND_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND_VAO",
						vmaItinerary, null );
				TINHPHI_PTTND_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND_ROI",
						vmaItinerary, null );
				TINHPHI_DOANLAI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_DOANLAI",
						vmaItinerary, null );
				
			} else {
				
				TINHPHI_TAUBIEN = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN",
						vmaItinerary, null, null );
				TINHPHI_TAUBIEN_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN_VAO",
						vmaItinerary, null, null );
				TINHPHI_TAUBIEN_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN_ROI",
						vmaItinerary, null, null );
				TINHPHI_PTTND = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND",
						vmaItinerary, null, null );
				TINHPHI_PTTND_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND_VAO",
						vmaItinerary, null, null );
				TINHPHI_PTTND_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND_ROI",
						vmaItinerary, null, null );
				
			}
			result.put("TINHPHI_PTTND", TINHPHI_PTTND);
			result.put("TINHPHI_PTTND_VAO", TINHPHI_PTTND_VAO);
			result.put("TINHPHI_PTTND_ROI", TINHPHI_PTTND_ROI);
			result.put("TINHPHI_TAUBIEN", TINHPHI_TAUBIEN);
			result.put("TINHPHI_TAUBIEN_VAO", TINHPHI_TAUBIEN_VAO);
			result.put("TINHPHI_TAUBIEN_ROI", TINHPHI_TAUBIEN_ROI);
			result.put("TINHPHI_DOANLAI", TINHPHI_DOANLAI);
			
			
			//Khi luot vao, luot roi khac nhau thi su dung VmaScheduleTransfer
			List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
			VmaScheduleTransfer vmaScheduleTransfer = null;
			if (vmaScheduleTransfers != null && vmaScheduleTransfers.size() > 0){
				vmaScheduleTransfer = vmaScheduleTransfers.get(0);
			}
			if(result.length() > 0 & result.getInt("noticeShipType") == 2 & vmaScheduleTransfer != null){
				result.put("nameOfShip", vmaScheduleTransfer.getShipName());
				result.put("flagStateOfShip", vmaScheduleTransfer.getFlagStateOfShip());
				result.put("callSign", vmaScheduleTransfer.getCallSign());
				result.put("imoNumber", vmaScheduleTransfer.getImoNumber());
				result.put("registryNumber", vmaScheduleTransfer.getRegistryNumber());
				result.put("vrCode", vmaScheduleTransfer.getVrCode());
				result.put("shipOperatorCode", vmaScheduleTransfer.getShipOperatorCode());
				try {
					result.put("shipOperatorName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOperatorCode()).getCompanyName());
				} catch(Exception e){
				}
				result.put("shipOwnerCode", vmaScheduleTransfer.getShipOwnerCode());
				try {
					result.put("shipOwnerName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOwnerCode()).getCompanyName());
				} catch(Exception e){
				}
				result.put("shipCaptain", vmaScheduleTransfer.getNameOfMaster());
				result.put("vmaShipTypeCode", vmaScheduleTransfer.getShipTypeMT());
				result.put("shipTypeCode", vmaScheduleTransfer.getShipTypeCode());
				result.remove("payment2ArrivalStatus");
				result.put("payment2ArrivalStatus", 0); // Bo qua Trang thai Tinh phi luot vao khi Ban giao tau; chi lam luot roi
			}
			if (noticeShipType == 1) {
				result.remove("payment2DepartureStatus");
				result.put("payment2DepartureStatus", 0); // Bo qua Trang thai Tinh phi luot roi; chi lam luot vao
			}
/*			if ((vmaItinerarySchedule != null && vmaItinerarySchedule
					.getShipBoat().equalsIgnoreCase("BOAT"))
					|| vmaItinerary.getMtgateway() == 0) {
*/
			if ((vmaItinerarySchedule != null )){
				VmaShip vmaShip = new VmaShip();
				if (vmaItinerary.getImoNumber().trim().length() >= 7) {
					// findBy IMO, CallSign together
					vmaShip = VmaShipLocalServiceUtil
							.fetchByIMONumber_CallSign(
									vmaItinerary.getImoNumber(),
									vmaItinerary.getCallSign());
				} else {
					// findBy CallSign only
					vmaShip = VmaShipLocalServiceUtil
							.fetchByCallSign(vmaItinerary.getCallSign());
					// Tim lai theo registryNumber
					if (!vmaShip.getShipName().contains(
							vmaItinerary.getNameOfShip())) {
						vmaShip = VmaShipLocalServiceUtil
								.fetchByRegistryNumber(vmaItinerary
										.getRegistryNumber());
					}
				}
				if (vmaShip != null && vmaScheduleTransfer == null) {
					JSONObject tmpVmaShip = VMAUtils.object2Json(vmaShip,
							VmaShip.class);
					result = VMAUtils.mergeJSON(result, tmpVmaShip,
							VmaShip.class);
					// reset MaxDraft
					result.remove("maxDraft");
					result.put("maxDraft", vmaShip.getMaxDraft());
				} else if (vmaScheduleTransfer != null && noticeShipType == 2){
					JSONObject jsonVmaScheduleTransfer = VMAUtils.object2Json(vmaScheduleTransfer,
							VmaScheduleTransfer.class);
					result = VMAUtils.mergeJSON(result, jsonVmaScheduleTransfer,
							VmaScheduleTransfer.class);
					// reset MaxDraft
					result.remove("maxDraft");
					result.put("maxDraft", vmaScheduleTransfer.getMaxDraft());
				}

			}
			String taxCodeOfShipownersAgents = vmaItinerary
					.getArrivalShipAgencyCode();
			result.remove("taxCodeOfShipownersAgents");
			result.put("taxCodeOfShipownersAgents", taxCodeOfShipownersAgents);

			
			try {
				Double dwt_xalan = 0.0;
				Double gt_xalan = 0.0;
				String sdk_xalan = StringPool.BLANK;
				String xalan = StringPool.BLANK;
				List<VmaScheduleMerging> lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
						.findByItineraryNo_NoticeShipType(itineraryNo, noticeShipType);
				if (lstVmaScheduleMerging != null
						&& lstVmaScheduleMerging.size() > 0) {
					int i = 0;
					for (VmaScheduleMerging vmaScheduleMerging : lstVmaScheduleMerging) {
						if (vmaScheduleMerging.getItineraryScheduleId() == vmaItinerarySchedule
								.getId()) {
							
							dwt_xalan = dwt_xalan + vmaScheduleMerging.getDwt().doubleValue();
							gt_xalan = gt_xalan + vmaScheduleMerging.getGt().doubleValue();
							i = i + 1;
							if (i == 1) {
								xalan = xalan + " ("
										+ vmaScheduleMerging.getShipLashName();
								sdk_xalan = sdk_xalan + " ("
										+ vmaScheduleMerging.getShipLashRegistryNumber();
							} else if (i <= lstVmaScheduleMerging.size()) {
								xalan = xalan + " + "
										+ vmaScheduleMerging.getShipLashName();
								sdk_xalan = sdk_xalan + " + "
										+ vmaScheduleMerging.getShipLashRegistryNumber();
							}
						}
					}
					if (i > 0) {
						xalan = xalan + ")";
						sdk_xalan = sdk_xalan + ")";
					}
					
					
					result.put("nameOfShip", vmaItinerarySchedule.getNameOfShip()
							+ xalan);
					result.put("callSign", vmaItinerary.getCallSign() + sdk_xalan);
					result.put("registryNumber", vmaItinerary.getRegistryNumber() + sdk_xalan);
				}
			} catch (Exception e) {

			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject viewPDF(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			HttpServletRequest request = resourceRequest;
			long vmaItineraryScheduleId = ParamUtil.getLong(request,
					"vmaItineraryScheduleId");
			VmaItinerarySchedule vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
					.getVmaItinerarySchedule(vmaItineraryScheduleId);
			// save file
			long nanoTime = ReportsBusinessUtils
					.actionExport(vmaItinerarySchedule);

			String tenFileExport = nanoTime + "_"
					+ ReportConstant.PTTND_PORTCLEARANCE_EXPORT;

			String urlFile = request.getContextPath() + "/export/"
					+ tenFileExport;

			result.put("url", urlFile);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);

		JSONObject result = JSONFactoryUtil.createJSONObject();

		long vmaItineraryScheduleId = ParamUtil.getLong(request,
				"vmaItineraryScheduleId");

		VmaItinerary vmaItinerary = null;

		VmaItinerarySchedule vmaItinerarySchedule = null;

		if (vmaItineraryScheduleId > 0) {
			try {
				vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
						.getVmaItinerarySchedule(vmaItineraryScheduleId);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		if (vmaItinerarySchedule == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(vmaItinerarySchedule.getItineraryNo());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (vmaItinerary == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		try {

			result = VMAUtils.object2Json(vmaItinerary,
					VmaItinerary.class);

			JSONObject tmp = VMAUtils.object2Json(vmaItinerarySchedule,
					VmaItinerarySchedule.class);

			result = VMAUtils.mergeJSON(result, tmp,
					VmaItinerarySchedule.class);
			
			VmaScheduleTransfer vmaScheduleTransfer = VmaScheduleTransferLocalServiceUtil.findByItineraryScheduleId(vmaItineraryScheduleId);
			if(result.length() > 0 & result.getInt("noticeShipType") == 2 & vmaScheduleTransfer != null){
				result.put("nameOfShip", vmaScheduleTransfer.getShipName());
				result.put("flagStateOfShip", vmaScheduleTransfer.getFlagStateOfShip());
				result.put("callSign", vmaScheduleTransfer.getCallSign());
				result.put("imoNumber", vmaScheduleTransfer.getImoNumber());
				result.put("registryNumber", vmaScheduleTransfer.getRegistryNumber());
				result.put("vrCode", vmaScheduleTransfer.getVrCode());
				result.put("shipOperatorCode", vmaScheduleTransfer.getShipOperatorCode());
				try {
					result.put("shipOperatorName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOperatorCode()).getCompanyName());
				} catch(Exception e){
				}
				result.put("shipOwnerCode", vmaScheduleTransfer.getShipOwnerCode());
				try {
					result.put("shipOwnerName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOwnerCode()).getCompanyName());
				} catch(Exception e){
				}
				result.put("shipCaptain", vmaScheduleTransfer.getNameOfMaster());
				result.put("vmaShipTypeCode", vmaScheduleTransfer.getShipTypeMT());
				result.put("shipTypeCode", vmaScheduleTransfer.getShipTypeCode());
			}
			if ((vmaItinerarySchedule != null && vmaItinerarySchedule
					.getShipBoat().equalsIgnoreCase("BOAT"))
					|| vmaItinerary.getMtgateway() == 0) {
				VmaShip vmaShip = new VmaShip();
				if (vmaItinerary.getImoNumber().trim().length() >= 7) {
					// findBy IMO, CallSign together
					vmaShip = VmaShipLocalServiceUtil
							.fetchByIMONumber_CallSign(
									vmaItinerary.getImoNumber(),
									vmaItinerary.getCallSign());
				} else {
					// findBy CallSign only
					vmaShip = VmaShipLocalServiceUtil
							.fetchByCallSign(vmaItinerary.getCallSign());
					// Tim lai theo registryNumber
					if (!vmaShip.getShipName().contains(
							vmaItinerary.getNameOfShip())) {
						vmaShip = VmaShipLocalServiceUtil
								.fetchByRegistryNumber(vmaItinerary
										.getRegistryNumber());
					}
				}
				if (vmaShip != null && vmaScheduleTransfer == null) {
					JSONObject tmpVmaShip = VMAUtils.object2Json(vmaShip,
							VmaShip.class);
					result = VMAUtils.mergeJSON(result, tmpVmaShip,
							VmaShip.class);
				} else if (vmaScheduleTransfer != null && result.getInt("noticeShipType") == 2){
					JSONObject jsonVmaScheduleTransfer = VMAUtils.object2Json(vmaScheduleTransfer,
							VmaScheduleTransfer.class);
					result = VMAUtils.mergeJSON(result, jsonVmaScheduleTransfer,
							VmaScheduleTransfer.class);
				}

				// 68 Giấy chứng nhận khả năng chuyên môn, chứng chỉ chuyên môn
				// thuyền viên (TNĐ)
				String pilotCertificateNo = StringPool.BLANK;
				try {
					List<VmaCertificate> objVmaCertificate = VmaCertificateLocalServiceUtil
							.findVmaCertificates(vmaShip.getShipName(), null,
									null, null, null, null,
									vmaShip.getImoNumber(),
									vmaShip.getCallSign(),
									vmaShip.getRegistryNumber(), -1, -1);
					if (Validator.isNotNull(objVmaCertificate)
							&& objVmaCertificate.size() > 0) {
						for (int i = 0; i < objVmaCertificate.size(); i++) {
							if (objVmaCertificate.get(i).getCertificateCode()
									.equalsIgnoreCase("68")) {
								pilotCertificateNo = objVmaCertificate.get(i)
										.getCertificateNo();
							}
						}
					}
				} catch (Exception e) {
					// nothing to do
				}
				result.remove("pilotCertificateNo");
				result.put("pilotCertificateNo", pilotCertificateNo);
			}

			String timeOfPORTArrival = StringPool.BLANK;
			try {
				timeOfPORTArrival = FormatData
						.parseDateToTringType3(vmaItinerarySchedule
								.getTimeOfPORTArrival());
			} catch (Exception e) {
				// nothing to do
			}
			result.remove("timeOfPORTArrival");
			result.put("timeOfPORTArrival", timeOfPORTArrival);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo documentName documentYear noticeShipType shipBoat
	 * nameOfShip shipOwnerCode shipOwnersName shipOperatorCode shipOperatorName
	 * shipAgencyCode shipAgencyName shipAgencyAddress shipAgencyContactEmail
	 * shipAgencyPhone shipAgencyFax securityLevelCode arrivalPortCode
	 * portRegionCode portHarbourCode portWharfCode gt nt dwt loa breadth
	 * clearanceHeight power maxDraft shownDraftxF shownDraftxA unitLOA
	 * unitBreadth unitClearanceHeight unitShownDraftxF unitShownDraftxA unitGRT
	 * unitNT unitDWT unitPower unitMaxDraft purposeCode purposeSpecified
	 * crewNumber passengerNumber asPerManifest containerNumber numberTEU
	 * numberTNE timeOfDeparture timeOfArrival timeOfPORTArrival
	 * timeOfPilotOnBoard timeOfApproval tugBoat do_ fo_ fw_ oilWater
	 * quantityOfCargo remainingCargo shipRequirementsInTermsOfWaste
	 * previousPortsOfCall subsequentPortsOfCall dischargedPorts
	 * portGoingToStateName portGoingToCode lastPortOfCallCode lastProvinceCode
	 * lastMaritimePortCode lastPortRegionCode lastPortHarbourCode
	 * lastPortWharfCode nextProvinceCode nextMaritimePortCode
	 * nextPortRegionCode nextPortHarbourCode nextPortWharfCode chanelCodeList
	 * chanelName remarks requestCodeNoticeShipMessage
	 * requestCodeGeneralDeclaration requestCodeShipSecurityMessage modifiedDate
	 */
	public static VmaItinerarySchedule getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;

		long id = GetterUtil.getLong(
				request.getParameter("vmaItineraryScheduleId"), -1);

		VmaItinerarySchedule vmaItinerarySchedule = null;
		if (id > 0) {
			try {
				vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
						.getVmaItinerarySchedule(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			vmaItinerarySchedule = new VmaItinerarySchedule();
			try {
				int tempNoticeShipType = GetterUtil.getInteger(
						request.getParameter("noticeShipType"), -1);
				String tempItineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
						StringPool.BLANK);
				if ((Validator.isNotNull(vmaItinerarySchedule)) && ((tempNoticeShipType == 1) || (tempNoticeShipType == 2))) {
					vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo_NoticeShipType(
									tempItineraryNo,
									tempNoticeShipType);
				}
				
				if (Validator.isNull(vmaItinerarySchedule)) {
					vmaItinerarySchedule = new VmaItinerarySchedule();
				}
			} catch (Exception e) {
				vmaItinerarySchedule = new VmaItinerarySchedule();
			}
		}
		
			
		

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaItinerarySchedule.setItineraryNo(itineraryNo);
		}
		String routeLevelCode = ParamUtil.getString(actionRequest,
				"routeLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(routeLevelCode)) {
			vmaItinerarySchedule.setRouteLevelCode(routeLevelCode);
		}
		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);
		if (documentName >= 0) {
			vmaItinerarySchedule.setDocumentName(documentName);
		}
		int documentYear = GetterUtil.getInteger(
				request.getParameter("documentYear"), -1);
		if (documentYear >= 0) {
			vmaItinerarySchedule.setDocumentYear(documentYear);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaItinerarySchedule.setNoticeShipType(noticeShipType);
		}
		String shipBoat = ParamUtil.getString(actionRequest, "shipBoat",
				StringPool.BLANK);
		if (Validator.isNotNull(shipBoat)) {
			vmaItinerarySchedule.setShipBoat(shipBoat);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaItinerarySchedule.setNameOfShip(nameOfShip);
		}
		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaItinerarySchedule.setShipOwnerCode(shipOwnerCode);
		}
		String shipOwnersName = VMAUtils.getString(actionRequest,
				"shipOwnersName", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnersName)) {
			vmaItinerarySchedule.setShipOwnersName(shipOwnersName);
		}
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaItinerarySchedule.setShipOperatorCode(shipOperatorCode);
		}
		String shipOperatorName = VMAUtils.getString(actionRequest,
				"shipOperatorName", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorName)) {
			vmaItinerarySchedule.setShipOperatorName(shipOperatorName);
		}
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaItinerarySchedule.setShipAgencyCode(shipAgencyCode);
		}
		String shipAgencyName = VMAUtils.getString(actionRequest,
				"shipAgencyName", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyName)) {
			vmaItinerarySchedule.setShipAgencyName(shipAgencyName);
		}
		String shipAgencyAddress = VMAUtils.getString(actionRequest,
				"shipAgencyAddress", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyAddress)) {
			vmaItinerarySchedule.setShipAgencyAddress(shipAgencyAddress);
		}
		String shipAgencyContactEmail = ParamUtil.getString(actionRequest,
				"shipAgencyContactEmail", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyContactEmail)) {
			vmaItinerarySchedule
					.setShipAgencyContactEmail(shipAgencyContactEmail);
		}
		String shipAgencyPhone = ParamUtil.getString(actionRequest,
				"shipAgencyPhone", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyPhone)) {
			vmaItinerarySchedule.setShipAgencyPhone(shipAgencyPhone);
		}
		String shipAgencyFax = ParamUtil.getString(actionRequest,
				"shipAgencyFax", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyFax)) {
			vmaItinerarySchedule.setShipAgencyFax(shipAgencyFax);
		}
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaItinerarySchedule.setSecurityLevelCode(securityLevelCode);
		}
		String arrivalPortCode = ParamUtil.getString(actionRequest,
				"arrivalPortCode", StringPool.BLANK);
		if (Validator.isNotNull(arrivalPortCode)) {
			vmaItinerarySchedule.setArrivalPortCode(arrivalPortCode);
		}
		String portRegionCode = ParamUtil.getString(actionRequest,
				"portRegionCode", StringPool.BLANK);
		if (Validator.isNotNull(portRegionCode)) {
			vmaItinerarySchedule.setPortRegionCode(portRegionCode);
		}
		String portHarbourCode = ParamUtil.getString(actionRequest,
				"portHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(portHarbourCode)) {
			vmaItinerarySchedule.setPortHarbourCode(portHarbourCode);
		}
		String portWharfCode = ParamUtil.getString(actionRequest,
				"portWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(portWharfCode)) {
			vmaItinerarySchedule.setPortWharfCode(portWharfCode);
		}
		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);
		if (gt >= 0) {
			vmaItinerarySchedule.setGt(BigDecimal.valueOf(gt));
		}
		double nt = GetterUtil.getDouble(request.getParameter("nt"), -1);
		if (nt >= 0) {
			vmaItinerarySchedule.setNt(BigDecimal.valueOf(nt));
		}
		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);
		if (dwt >= 0) {
			vmaItinerarySchedule.setDwt(BigDecimal.valueOf(dwt));
		}
		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);
		if (loa >= 0) {
			vmaItinerarySchedule.setLoa(loa);
		}
		double breadth = GetterUtil.getDouble(request.getParameter("breadth"),
				-1);
		if (breadth >= 0) {
			vmaItinerarySchedule.setBreadth(breadth);
		}
		double clearanceHeight = GetterUtil.getDouble(
				request.getParameter("clearanceHeight"), -1);
		if (clearanceHeight >= 0) {
			vmaItinerarySchedule.setClearanceHeight(clearanceHeight);
		}
		double power = GetterUtil.getDouble(request.getParameter("power"), -1);
		if (power >= 0) {
			vmaItinerarySchedule.setPower(power);
		}
		double maxDraft = GetterUtil.getDouble(
				request.getParameter("maxDraft"), -1);
		if (maxDraft >= 0) {
			vmaItinerarySchedule.setMaxDraft(maxDraft);
		}
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		if (shownDraftxF >= 0) {
			vmaItinerarySchedule.setShownDraftxF(shownDraftxF);
		}
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);
		if (shownDraftxA >= 0) {
			vmaItinerarySchedule.setShownDraftxA(shownDraftxA);
		}
		String unitLOA = VMAUtils.getString(actionRequest, "unitLOA",
				StringPool.BLANK);
		if (Validator.isNotNull(unitLOA)) {
			vmaItinerarySchedule.setUnitLOA(unitLOA);
		}
		String unitBreadth = VMAUtils.getString(actionRequest, "unitBreadth",
				StringPool.BLANK);
		if (Validator.isNotNull(unitBreadth)) {
			vmaItinerarySchedule.setUnitBreadth(unitBreadth);
		}
		String unitClearanceHeight = VMAUtils.getString(actionRequest,
				"unitClearanceHeight", StringPool.BLANK);
		if (Validator.isNotNull(unitClearanceHeight)) {
			vmaItinerarySchedule.setUnitClearanceHeight(unitClearanceHeight);
		}
		String unitShownDraftxF = VMAUtils.getString(actionRequest,
				"unitShownDraftxF", StringPool.BLANK);
		if (Validator.isNotNull(unitShownDraftxF)) {
			vmaItinerarySchedule.setUnitShownDraftxF(unitShownDraftxF);
		}
		String unitShownDraftxA = VMAUtils.getString(actionRequest,
				"unitShownDraftxA", StringPool.BLANK);
		if (Validator.isNotNull(unitShownDraftxA)) {
			vmaItinerarySchedule.setUnitShownDraftxA(unitShownDraftxA);
		}
		String unitGRT = VMAUtils.getString(actionRequest, "unitGRT",
				StringPool.BLANK);
		if (Validator.isNotNull(unitGRT)) {
			vmaItinerarySchedule.setUnitGRT(unitGRT);
		}
		String unitNT = VMAUtils.getString(actionRequest, "unitNT",
				StringPool.BLANK);
		if (Validator.isNotNull(unitNT)) {
			vmaItinerarySchedule.setUnitNT(unitNT);
		}
		String unitDWT = VMAUtils.getString(actionRequest, "unitDWT",
				StringPool.BLANK);
		if (Validator.isNotNull(unitDWT)) {
			vmaItinerarySchedule.setUnitDWT(unitDWT);
		}
		String unitPower = VMAUtils.getString(actionRequest, "unitPower",
				StringPool.BLANK);
		if (Validator.isNotNull(unitPower)) {
			vmaItinerarySchedule.setUnitPower(unitPower);
		}
		String unitMaxDraft = VMAUtils.getString(actionRequest, "unitMaxDraft",
				StringPool.BLANK);
		if (Validator.isNotNull(unitMaxDraft)) {
			vmaItinerarySchedule.setUnitMaxDraft(unitMaxDraft);
		}
		String purposeCode = VMAUtils.getString(actionRequest, "purposeCode",
				StringPool.BLANK);
		if (Validator.isNotNull(purposeCode)) {
			vmaItinerarySchedule.setPurposeCode(purposeCode);
		}
		String purposeSpecified = VMAUtils.getString(actionRequest,
				"purposeSpecified", StringPool.BLANK);
		if (Validator.isNotNull(purposeSpecified)) {
			vmaItinerarySchedule.setPurposeSpecified(purposeSpecified);
		}
		int crewNumber = GetterUtil.getInteger(
				request.getParameter("crewNumber"), -1);
		if (crewNumber >= 0) {
			vmaItinerarySchedule.setCrewNumber(BigDecimal.valueOf(crewNumber));
		}
		int passengerNumber = GetterUtil.getInteger(
				request.getParameter("passengerNumber"), -1);
		if (passengerNumber >= 0) {
			vmaItinerarySchedule.setPassengerNumber(BigDecimal.valueOf(passengerNumber));
		}
		int asPerManifest = GetterUtil.getInteger(
				request.getParameter("asPerManifest"), -1);
		if (asPerManifest >= 0) {
			vmaItinerarySchedule.setAsPerManifest(asPerManifest);
		}
		int containerNumber = GetterUtil.getInteger(
				request.getParameter("containerNumber"), -1);
		if (containerNumber >= 0) {
			vmaItinerarySchedule.setContainerNumber(BigDecimal.valueOf(containerNumber));
		}
		int numberTEU = GetterUtil.getInteger(
				request.getParameter("numberTEU"), -1);
		if (numberTEU >= 0) {
			vmaItinerarySchedule.setNumberTEU(BigDecimal.valueOf(numberTEU));
		}
		int numberTNE = GetterUtil.getInteger(
				request.getParameter("numberTNE"), -1);
		if (numberTNE >= 0) {
			vmaItinerarySchedule.setNumberTNE(BigDecimal.valueOf(numberTNE));
		}
		String timeOfDeparture = ParamUtil.getString(actionRequest,
				"timeOfDeparture", StringPool.BLANK);
		if (Validator.isNotNull(timeOfDeparture)) {
			try {
				Date date = FormatData.formatDateShort3.parse(timeOfDeparture);
				vmaItinerarySchedule.setTimeOfDeparture(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String timeOfArrival = ParamUtil.getString(actionRequest,
				"timeOfArrival", StringPool.BLANK);
		if (Validator.isNotNull(timeOfArrival)) {
			try {
				Date date = FormatData.formatDateShort3.parse(timeOfArrival);
				vmaItinerarySchedule.setTimeOfArrival(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String timeOfPORTArrival = ParamUtil.getString(actionRequest,
				"timeOfPORTArrival", StringPool.BLANK);
		if (Validator.isNotNull(timeOfPORTArrival)) {
			try {
				Date date = FormatData.formatDateShort3
						.parse(timeOfPORTArrival);
				vmaItinerarySchedule.setTimeOfPORTArrival(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String timeOfPilotOnBoard = ParamUtil.getString(actionRequest,
				"timeOfPilotOnBoard", StringPool.BLANK);
		if (Validator.isNotNull(timeOfPilotOnBoard)) {
			try {
				Date date = FormatData.formatDateShort3
						.parse(timeOfPilotOnBoard);
				vmaItinerarySchedule.setTimeOfPilotOnBoard(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String timeOfApproval = ParamUtil.getString(actionRequest,
				"timeOfApproval", StringPool.BLANK);
		if (Validator.isNotNull(timeOfApproval)) {
			try {
				Date date = FormatData.formatDateShort3.parse(timeOfApproval);
				vmaItinerarySchedule.setTimeOfApproval(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String tugBoat = VMAUtils.getString(actionRequest, "tugBoat",
				StringPool.BLANK);
		if (Validator.isNotNull(tugBoat)) {
			vmaItinerarySchedule.setTugBoat(tugBoat);
		}
		String do_ = VMAUtils.getString(actionRequest, "do_", StringPool.BLANK);
		if (Validator.isNotNull(do_)) {
			vmaItinerarySchedule.setDoField(do_);
		}
		String fo_ = VMAUtils.getString(actionRequest, "fo_", StringPool.BLANK);
		if (Validator.isNotNull(fo_)) {
			vmaItinerarySchedule.setFo(fo_);
		}
		String fw_ = VMAUtils.getString(actionRequest, "fw_", StringPool.BLANK);
		if (Validator.isNotNull(fw_)) {
			vmaItinerarySchedule.setFw(fw_);
		}
		String oilWater = VMAUtils.getString(actionRequest, "oilWater",
				StringPool.BLANK);
		if (Validator.isNotNull(oilWater)) {
			vmaItinerarySchedule.setOilWater(oilWater);
		}
		String quantityOfCargo = VMAUtils.getString(actionRequest,
				"quantityOfCargo", StringPool.BLANK);
		if (Validator.isNotNull(quantityOfCargo)) {
			vmaItinerarySchedule.setQuantityOfCargo(quantityOfCargo);
		}
		String remainingCargo = VMAUtils.getString(actionRequest,
				"remainingCargo", StringPool.BLANK);
		if (Validator.isNotNull(remainingCargo)) {
			vmaItinerarySchedule.setRemainingCargo(remainingCargo);
		}
		String shipRequirementsInTermsOfWaste = ParamUtil.getString(
				actionRequest, "shipRequirementsInTermsOfWaste",
				StringPool.BLANK);
		if (Validator.isNotNull(shipRequirementsInTermsOfWaste)) {
			vmaItinerarySchedule
					.setShipRequirementsInTermsOfWaste(shipRequirementsInTermsOfWaste);
		}
		String previousPortsOfCall = VMAUtils.getString(actionRequest,
				"previousPortsOfCall", StringPool.BLANK);
		if (Validator.isNotNull(previousPortsOfCall)) {
			vmaItinerarySchedule.setPreviousPortsOfCall(previousPortsOfCall);
		}
		String subsequentPortsOfCall = VMAUtils.getString(actionRequest,
				"subsequentPortsOfCall", StringPool.BLANK);
		if (Validator.isNotNull(subsequentPortsOfCall)) {
			vmaItinerarySchedule
					.setSubsequentPortsOfCall(subsequentPortsOfCall);
		}
		String dischargedPorts = VMAUtils.getString(actionRequest,
				"dischargedPorts", StringPool.BLANK);
		if (Validator.isNotNull(dischargedPorts)) {
			vmaItinerarySchedule.setDischargedPorts(dischargedPorts);
		}
		String portGoingToStateName = VMAUtils.getString(actionRequest,
				"portGoingToStateName", StringPool.BLANK);
		if (Validator.isNotNull(portGoingToStateName)) {
			vmaItinerarySchedule.setPortGoingToStateName(portGoingToStateName);
		}
		String portGoingToCode = ParamUtil.getString(actionRequest,
				"portGoingToCode", StringPool.BLANK);
		if (Validator.isNotNull(portGoingToCode)) {
			vmaItinerarySchedule.setPortGoingToCode(portGoingToCode);
		}
		String lastPortOfCallCode = ParamUtil.getString(actionRequest,
				"lastPortOfCallCode", StringPool.BLANK);
		if (Validator.isNotNull(lastPortOfCallCode)) {
			vmaItinerarySchedule.setLastPortOfCallCode(lastPortOfCallCode);
		}
		String lastProvinceCode = VMAUtils.getString(actionRequest,
				"lastProvinceCode", StringPool.BLANK);
		if (Validator.isNotNull(lastProvinceCode)) {
			vmaItinerarySchedule.setLastProvinceCode(lastProvinceCode);
		}
		String lastMaritimePortCode = ParamUtil.getString(actionRequest,
				"lastMaritimePortCode", StringPool.BLANK);
		if (Validator.isNotNull(lastMaritimePortCode)) {
			vmaItinerarySchedule.setLastMaritimePortCode(lastMaritimePortCode);
		}
		String lastPortRegionCode = ParamUtil.getString(actionRequest,
				"lastPortRegionCode", StringPool.BLANK);
		if (Validator.isNotNull(lastPortRegionCode)) {
			vmaItinerarySchedule.setLastPortRegionCode(lastPortRegionCode);
		}
		String lastPortHarbourCode = ParamUtil.getString(actionRequest,
				"lastPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(lastPortHarbourCode)) {
			vmaItinerarySchedule.setLastPortHarbourCode(lastPortHarbourCode);
		}
		String lastPortWharfCode = ParamUtil.getString(actionRequest,
				"lastPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(lastPortWharfCode)) {
			vmaItinerarySchedule.setLastPortWharfCode(lastPortWharfCode);
		}
		String nextProvinceCode = VMAUtils.getString(actionRequest,
				"nextProvinceCode", StringPool.BLANK);
		if (Validator.isNotNull(nextProvinceCode)) {
			vmaItinerarySchedule.setNextProvinceCode(nextProvinceCode);
		}
		String nextMaritimePortCode = ParamUtil.getString(actionRequest,
				"nextMaritimePortCode", StringPool.BLANK);
		if (Validator.isNotNull(nextMaritimePortCode)) {
			vmaItinerarySchedule.setNextMaritimePortCode(nextMaritimePortCode);
		}
		String nextPortRegionCode = ParamUtil.getString(actionRequest,
				"nextPortRegionCode", StringPool.BLANK);
		if (Validator.isNotNull(nextPortRegionCode)) {
			vmaItinerarySchedule.setNextPortRegionCode(nextPortRegionCode);
		}
		String nextPortHarbourCode = ParamUtil.getString(actionRequest,
				"nextPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(nextPortHarbourCode)) {
			vmaItinerarySchedule.setNextPortHarbourCode(nextPortHarbourCode);
		}
		String nextPortWharfCode = ParamUtil.getString(actionRequest,
				"nextPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(nextPortWharfCode)) {
			vmaItinerarySchedule.setNextPortWharfCode(nextPortWharfCode);
		}
		String chanelCodeList = VMAUtils.getString(actionRequest,
				"chanelCodeList", StringPool.BLANK);
		if (Validator.isNotNull(chanelCodeList)) {
			vmaItinerarySchedule.setChanelCodeList(chanelCodeList);

			vmaItinerarySchedule.setChanelName(VMAUtils
					.getChanelName(chanelCodeList));

		} else {
			vmaItinerarySchedule.setChanelCodeList(StringPool.BLANK);
			vmaItinerarySchedule.setChanelName(StringPool.BLANK);
		}
		/*
		 * String chanelName = VMAUtils.getString(actionRequest, "chanelName",
		 * StringPool.BLANK); if (Validator.isNotNull(chanelNam  e)) {
		 * vmaItinerarySchedule.setChanelName(chanelName); }
		 */
		String remarks = VMAUtils.getString(actionRequest, "remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(remarks)) {
			System.out
					.println("==================================>>>>>>>>>>>>remarks "
							+ remarks);
			vmaItinerarySchedule.setRemarks(remarks);
		}
		String requestCodeNoticeShipMessage = ParamUtil
				.getString(actionRequest, "requestCodeNoticeShipMessage",
						StringPool.BLANK);
		if (Validator.isNotNull(requestCodeNoticeShipMessage)) {
			vmaItinerarySchedule
					.setRequestCodeNoticeShipMessage(requestCodeNoticeShipMessage);
		}
		String requestCodeGeneralDeclaration = ParamUtil.getString(
				actionRequest, "requestCodeGeneralDeclaration",
				StringPool.BLANK);
		if (Validator.isNotNull(requestCodeGeneralDeclaration)) {
			vmaItinerarySchedule
					.setRequestCodeGeneralDeclaration(requestCodeGeneralDeclaration);
		}
		String requestCodeShipSecurityMessage = ParamUtil.getString(
				actionRequest, "requestCodeShipSecurityMessage",
				StringPool.BLANK);
		if (Validator.isNotNull(requestCodeShipSecurityMessage)) {
			vmaItinerarySchedule
					.setRequestCodeShipSecurityMessage(requestCodeShipSecurityMessage);
		}
		/*
		 * String modifiedDate = ParamUtil.getString(actionRequest,
		 * "modifiedDate", StringPool.BLANK); if
		 * (Validator.isNotNull(modifiedDate)) { try { Date date =
		 * FormatData.formatDateShort3.parse(modifiedDate);
		 * vmaItinerarySchedule.setModifiedDate(date); } catch (ParseException
		 * e) { log.error(e.getMessage()); } }
		 */

		double anchorageFreeDuration = GetterUtil.getDouble(
				request.getParameter("anchorageFreeDuration"), -1);
		if (anchorageFreeDuration >= 0) {
			vmaItinerarySchedule
					.setAnchorageFreeDuration(anchorageFreeDuration);
		} else {
			vmaItinerarySchedule.setAnchorageFreeDuration((double) 0);
		}
		String cargoType = VMAUtils.getString(actionRequest, "cargoType",
				StringPool.BLANK);
		if (Validator.isNotNull(cargoType)) {
			vmaItinerarySchedule.setCargoType(cargoType);
		}
		String departmentCode = ParamUtil.getString(actionRequest,
				"departmentCode", StringPool.BLANK);
		if (Validator.isNotNull(departmentCode)) {
			vmaItinerarySchedule.setDepartmentCode(departmentCode);
		}

		String departmentName = VMAUtils.getString(actionRequest,
				"departmentName", StringPool.BLANK);
		if (Validator.isNotNull(departmentName)) {
			vmaItinerarySchedule.setDepartmentName(departmentName);
		}

		int checkNoticeApproval = GetterUtil.getInteger(
				request.getParameter("checkNoticeApproval"), -1);
		if (checkNoticeApproval >= 0) {
			vmaItinerarySchedule.setCheckNoticeApproval(checkNoticeApproval);
		}
		int checkBerthPlan = GetterUtil.getInteger(
				request.getParameter("checkBerthPlan"), -1);
		if (checkBerthPlan >= 0) {
			vmaItinerarySchedule.setCheckBerthPlan(checkBerthPlan);
		}

		int checkPilotPlan = GetterUtil.getInteger(
				request.getParameter("checkPilotPlan"), -1);
		if (checkPilotPlan >= 0) {
			vmaItinerarySchedule.setCheckPilotPlan(checkPilotPlan);
		}
		int checkTugboatPlan = GetterUtil.getInteger(
				request.getParameter("checkTugboatPlan"), -1);

		if (checkTugboatPlan >= 0) {
			vmaItinerarySchedule.setCheckTugboatPlan(checkTugboatPlan);
		}
		int violated = GetterUtil.getInteger(request.getParameter("violated"),
				-1);
		if (violated > 0) {
			vmaItinerarySchedule.setViolated(violated);
		}

		String maritimeRemarks = VMAUtils.getString(actionRequest,
				"maritimeRemarks", StringPool.BLANK);

		if (Validator.isNotNull(maritimeRemarks)) {
			vmaItinerarySchedule.setMaritimeRemarks(maritimeRemarks);
		}

		int deconstructed = GetterUtil.getInteger(
				request.getParameter("deconstructed"), -1);
		if (deconstructed >= 0) {
			vmaItinerarySchedule.setDeconstructed(deconstructed);
		}
		String mergedShip = VMAUtils.getString(actionRequest, "mergedShip",
				StringPool.BLANK);
		if (Validator.isNotNull(mergedShip)) {
			vmaItinerarySchedule.setMergedShip(mergedShip);
		}
		String certificateNo = ParamUtil.getString(actionRequest,
				"certificateNo", StringPool.BLANK);
		if (Validator.isNotNull(certificateNo)) {
			vmaItinerarySchedule.setCertificateNo(certificateNo);
		}

		String shipPreviousName = VMAUtils.getString(actionRequest,
				"shipPreviousName", StringPool.BLANK);
		if (Validator.isNotNull(shipPreviousName)) {
			vmaItinerarySchedule.setShipPreviousName(shipPreviousName);
		}
		String initFrom = ParamUtil.getString(actionRequest, "initFrom",
				StringPool.BLANK);
		if (Validator.isNotNull(initFrom)) {
			vmaItinerarySchedule.setInitFrom(initFrom);
		}

		String unitGrt = ParamUtil.getString(actionRequest, "unitGrt",
				StringPool.BLANK);
		if (Validator.isNotNull(unitGrt)) {
			vmaItinerarySchedule.setUnitGRT(unitGrt);
		}
		String unitNt = ParamUtil.getString(actionRequest, "unitNt",
				StringPool.BLANK);
		if (Validator.isNotNull(unitNt)) {
			vmaItinerarySchedule.setUnitNT(unitNt);
		}
		String unitDwt = ParamUtil.getString(actionRequest, "unitDwt",
				StringPool.BLANK);
		if (Validator.isNotNull(unitDwt)) {
			vmaItinerarySchedule.setUnitDWT(unitDwt);
		}

		/*
		 * String chanelName = ParamUtil.getString(actionRequest, "chanelName",
		 * StringPool.BLANK); if (Validator.isNotNull(chanelName)) {
		 * vmaItinerarySchedule.setChanelName(chanelName); }
		 */

		return vmaItinerarySchedule;
	}

	@Deprecated
	public static JSONObject addVmaItinerarySchedule(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaItinerarySchedule vmaItinerarySchedule = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaItinerarySchedule == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
					.addVmaItinerarySchedule(vmaItinerarySchedule);

			result = VMAUtils.object2Json(vmaItinerarySchedule,
					VmaItinerarySchedule.class);

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	@Deprecated
	public static JSONObject updateVmaItinerarySchedule(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaItinerarySchedule vmaItinerarySchedule = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaItinerarySchedule == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
					.updateVmaItinerarySchedule(vmaItinerarySchedule);
			result = VMAUtils.object2Json(vmaItinerarySchedule,
					VmaItinerarySchedule.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaItinerarySchedule_VmaItinerary(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());

		HttpServletRequest request = actionRequest;

		int markedAsArrival = GetterUtil.getInteger(
				request.getParameter("markedAsArrival"), -1);

		int markedAsDeparture = GetterUtil.getInteger(
				request.getParameter("markedAsDeparture"), -1);

		int markedAsTransmit = GetterUtil.getInteger(
				request.getParameter("markedAsTransmit"), -1);

		int markedAsVoyage = GetterUtil.getInteger(
				request.getParameter("markedAsVoyage"), -1);

		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(actionRequest, "callSign",
				StringPool.BLANK);
		String flagStateOfShip = VMAUtils.getString(actionRequest,
				"flagStateOfShip", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(actionRequest,
				"registryNumber", StringPool.BLANK);

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaItinerarySchedule vmaItinerarySchedule = getObjectFromRequest(
				themeDisplay, actionRequest);

		VmaItinerary vmaItinerary = null;
		int preMarkedAsArrival = -2, preMarkedAsDeparture = -2;
		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(vmaItinerarySchedule.getItineraryNo());

			preMarkedAsArrival = vmaItinerary.getMarkedAsArrival();
			preMarkedAsDeparture = vmaItinerary.getMarkedAsDeparture();
		} catch (SystemException e) {
			log.error(e.getMessage());
		}

		if (vmaItinerarySchedule == null || vmaItinerary == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		vmaItinerary = VmaItineraryUtils.getObjectFromRequest(actionRequest,
				vmaItinerary);

		try {
			result = VmaItineraryScheduleLocalServiceUtil
					.updateVmaItinerarySchedule_VmaItinerary(
							markedAsArrival > 0 ? markedAsArrival : null,
							markedAsDeparture > 0 ? markedAsDeparture : null,
							markedAsTransmit > 0 ? markedAsTransmit : null,
							markedAsVoyage > 0 ? markedAsVoyage : null,
							vmaItinerarySchedule, vmaItinerary);
			// Them ban ghi vao vmascheduletranfer khi co su thay doi thong
			// tin tau luot vao luot roi
			if (VmaItineraryUtils.checkChanged(vmaItinerary, nameOfShip,
					flagStateOfShip, imoNumber, callSign, registryNumber)) {
				long itineraryScheduleId = vmaItinerarySchedule.getId();
				if (itineraryScheduleId > 0) {
					VmaScheduleTransfer vmaScheduleTransfer = null;
					try {
						vmaScheduleTransfer = VmaScheduleTransferLocalServiceUtil
								.findByItineraryScheduleId(itineraryScheduleId);
						vmaScheduleTransfer = VmaScheduleTransferUtils
								.getObjectFromRequest(vmaScheduleTransfer,
										themeDisplay, actionRequest);
						vmaScheduleTransfer
								.setItineraryScheduleId(itineraryScheduleId);
						
						// Hạn chế tạo khi Sự thay đổi không rõ ràng
						if (Validator.isNotNull(vmaScheduleTransfer.getShipName()) && Validator.isNotNull(vmaScheduleTransfer.getFlagStateOfShip()) && Validator.isNotNull(vmaScheduleTransfer.getNameOfMaster())) {
							vmaScheduleTransfer = VmaScheduleTransferUtils
									.updateVmaScheduleTransfer(vmaScheduleTransfer);
						}	
						
					} catch (Exception e) {
					}
					
				}
			} else {
				// SonVH bo sung --- Tiep tuc cap nhat VmaShip luot vao, roi la
				// giong nhau
				log.info("Tiep tuc cap nhat VmaShip");
				VmaShipUtils.updateVmaShip_ItinerarySchedule(themeDisplay,
						actionRequest, vmaItinerarySchedule, vmaItinerary);
			}
			// Write log - add by Dungnv
			if (userPort != null) {
				int afterMarkedAsArrival = result.has("markedAsArrival") ? result.getInt("markedAsArrival"): -1;
				int afterMarkedAsDeparture = result.has("markedAsDeparture") ?result.getInt("markedAsDeparture"): -1;
				if (vmaItinerarySchedule.getNoticeShipType() == 1) {
					if (preMarkedAsArrival > 0 && afterMarkedAsArrival > 0) {
						if (preMarkedAsArrival == afterMarkedAsArrival) {
							VmaAuditLogLocalServiceUtil.addVmaAuditLog(
									userPort.getUserId(),
									userSign != null ? userSign.getModifyuser()
											: StringPool.BLANK,
									LogsConstant.SUA,
									"vma_itinerary, vma_itinerary_schedule",
									vmaItinerary.getItineraryNo(),
									"vmaItineraryScheduleId: "
											+ vmaItinerarySchedule.getId()
											+ StringPool.COMMA
											+ "shipBoat: "
											+ result.getString("shipBoat")
											+ StringPool.COMMA
											+ "noticeShipType: "
											+ vmaItinerarySchedule
													.getNoticeShipType()
											+ StringPool.COMMA + "nameOfShip: "
											+ vmaItinerary.getNameOfShip());
						} else {
							if (vmaItinerary.getId() <= 0) {
								if (vmaItinerary.getMarkedAsArrival() == 2) {
									VmaAuditLogLocalServiceUtil
											.addVmaAuditLog(
													userPort.getUserId(),
													userSign != null ? userSign
															.getModifyuser()
															: StringPool.BLANK,
													LogsConstant.THEM,
													"vma_itinerary, vma_itinerary_schedule",
													vmaItinerary
															.getItineraryNo(),
													"vmaItineraryScheduleId: "
															+ result.getLong("vmaItineraryScheduleId")
															+ StringPool.COMMA
															+ "shipBoat: "
															+ result.getString("shipBoat")
															+ StringPool.COMMA
															+ "noticeShipType: "
															+ vmaItinerarySchedule
																	.getNoticeShipType()
															+ StringPool.COMMA
															+ "nameOfShip: "
															+ vmaItinerary
																	.getNameOfShip());
								}
							} else {
								if (vmaItinerary.getMarkedAsArrival() == 5) {
									VmaAuditLogLocalServiceUtil
											.addVmaAuditLog(
													userPort.getUserId(),
													userSign != null ? userSign
															.getModifyuser()
															: StringPool.BLANK,
													LogsConstant.DUYET,
													"vma_itinerary, vma_itinerary_schedule",
													vmaItinerary
															.getItineraryNo(),
													"vmaItineraryScheduleId: "
															+ vmaItinerarySchedule
																	.getId()
															+ StringPool.COMMA
															+ "shipBoat: "
															+ result.getString("shipBoat")
															+ StringPool.COMMA
															+ "noticeShipType: "
															+ vmaItinerarySchedule
																	.getNoticeShipType()
															+ StringPool.COMMA
															+ "nameOfShip: "
															+ vmaItinerary
																	.getNameOfShip());
								} else if (vmaItinerary.getMarkedAsArrival() == 2) {
									VmaAuditLogLocalServiceUtil
											.addVmaAuditLog(
													userPort.getUserId(),
													userSign != null ? userSign
															.getModifyuser()
															: StringPool.BLANK,
													LogsConstant.HUY,
													"vma_itinerary, vma_itinerary_schedule",
													vmaItinerary
															.getItineraryNo(),
													"vmaItineraryScheduleId: "
															+ vmaItinerarySchedule
																	.getId()
															+ StringPool.COMMA
															+ "shipBoat: "
															+ result.getString("shipBoat")
															+ StringPool.COMMA
															+ "noticeShipType: "
															+ vmaItinerarySchedule
																	.getNoticeShipType()
															+ StringPool.COMMA
															+ "nameOfShip: "
															+ vmaItinerary
																	.getNameOfShip());
								}
							}
						}
					}
				} else if (vmaItinerarySchedule.getNoticeShipType() == 2) {
					if (preMarkedAsDeparture > 0 && preMarkedAsDeparture > 0) {
						if (preMarkedAsDeparture == afterMarkedAsDeparture) {
							VmaAuditLogLocalServiceUtil.addVmaAuditLog(
									userPort.getUserId(),
									userSign != null ? userSign.getModifyuser()
											: StringPool.BLANK,
									LogsConstant.SUA,
									"vma_itinerary, vma_itinerary_schedule",
									vmaItinerary.getItineraryNo(),
									"vmaItineraryScheduleId: "
											+ vmaItinerarySchedule.getId()
											+ StringPool.COMMA
											+ "shipBoat: "
											+ result.getString("shipBoat")
											+ StringPool.COMMA
											+ "noticeShipType: "
											+ vmaItinerarySchedule
													.getNoticeShipType()
											+ StringPool.COMMA + "nameOfShip: "
											+ vmaItinerary.getNameOfShip());
						} else {
							if (vmaItinerary.getId() <= 0) {
								if (vmaItinerary.getMarkedAsDeparture() == 2) {
									VmaAuditLogLocalServiceUtil
											.addVmaAuditLog(
													userPort.getUserId(),
													userSign != null ? userSign
															.getModifyuser()
															: StringPool.BLANK,
													LogsConstant.THEM,
													"vma_itinerary, vma_itinerary_schedule",
													vmaItinerary
															.getItineraryNo(),
													"vmaItineraryScheduleId: "
															+ result.getLong("vmaItineraryScheduleId")
															+ StringPool.COMMA
															+ "shipBoat: "
															+ result.getString("shipBoat")
															+ StringPool.COMMA
															+ "noticeShipType: "
															+ vmaItinerarySchedule
																	.getNoticeShipType()
															+ StringPool.COMMA
															+ "nameOfShip: "
															+ vmaItinerary
																	.getNameOfShip());
								}
							} else {
								if (vmaItinerary.getMarkedAsDeparture() == 5) {
									VmaAuditLogLocalServiceUtil
											.addVmaAuditLog(
													userPort.getUserId(),
													userSign != null ? userSign
															.getModifyuser()
															: StringPool.BLANK,
													LogsConstant.DUYET,
													"vma_itinerary, vma_itinerary_schedule",
													vmaItinerary
															.getItineraryNo(),
													"vmaItineraryScheduleId: "
															+ vmaItinerarySchedule
																	.getId()
															+ StringPool.COMMA
															+ "shipBoat: "
															+ result.getString("shipBoat")
															+ StringPool.COMMA
															+ "noticeShipType: "
															+ vmaItinerarySchedule
																	.getNoticeShipType()
															+ StringPool.COMMA
															+ "nameOfShip: "
															+ vmaItinerary
																	.getNameOfShip());
								} else if (vmaItinerary.getMarkedAsDeparture() == 2) {
									VmaAuditLogLocalServiceUtil
											.addVmaAuditLog(
													userPort.getUserId(),
													userSign != null ? userSign
															.getModifyuser()
															: StringPool.BLANK,
													LogsConstant.HUY,
													"vma_itinerary, vma_itinerary_schedule",
													vmaItinerary
															.getItineraryNo(),
													"vmaItineraryScheduleId: "
															+ vmaItinerarySchedule
																	.getId()
															+ StringPool.COMMA
															+ "shipBoat: "
															+ result.getString("shipBoat")
															+ StringPool.COMMA
															+ "noticeShipType: "
															+ vmaItinerarySchedule
																	.getNoticeShipType()
															+ StringPool.COMMA
															+ "nameOfShip: "
															+ vmaItinerary
																	.getNameOfShip());
								}
							}
						}
					}
				}
				// Chi xac nhan hoac duyet moi duoc tao Vi tri Neo Dau -
				// VmaScheduleAnChorage
				/*
				 * if (result != null && result.length() > 0 && (
				 * vmaItinerarySchedule.getNoticeShipType() == 1 ||
				 * vmaItinerarySchedule.getNoticeShipType() == 2 )) {
				 * VmaScheduleAnchorage vmaScheduleAnchorage =
				 * VmaScheduleAnchorageLocalServiceUtil
				 * .findByitineraryScheduleId(vmaItinerarySchedule .getId()); if
				 * (vmaScheduleAnchorage == null) { vmaScheduleAnchorage =
				 * VMAUtils .updateVmaScheduleAnchorage( vmaScheduleAnchorage,
				 * vmaItinerary, vmaItinerarySchedule);
				 * VmaScheduleAnchorageLocalServiceUtil
				 * .addVmaScheduleAnchorage(vmaScheduleAnchorage); } else {
				 * vmaScheduleAnchorage = VMAUtils .updateVmaScheduleAnchorage(
				 * vmaScheduleAnchorage, vmaItinerary, vmaItinerarySchedule);
				 * VmaScheduleAnchorageLocalServiceUtil
				 * .updateVmaScheduleAnchorage(vmaScheduleAnchorage); } }
				 */
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaItinerarySchedule(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());

		long id = ParamUtil.getLong(actionRequest, "vmaItineraryScheduleId");
		if (id > 0) {
			String desciption = "";
			try {
				// VmaItinerarySchedule
				VmaItinerarySchedule curItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
						.getVmaItinerarySchedule(id);

				String itineraryNo = curItinerarySchedule.getItineraryNo();
				int noticeShipType = curItinerarySchedule.getNoticeShipType();

				// Lấy danh sách VmaScheduleMerging
				try {
					List<VmaScheduleMerging> lstScheduleMerging = VmaScheduleMergingLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo,
									noticeShipType);
					if (lstScheduleMerging != null) {
						// Xóa danh sách
						for (VmaScheduleMerging curScheduleMerging : lstScheduleMerging) {
							VmaScheduleMergingLocalServiceUtil
									.deleteVmaScheduleMerging(curScheduleMerging);
						}
					}
				} catch (Exception e) {
					desciption += e.getMessage();
				}

				try {
					// VmaScheduleTugboat
					List<VmaScheduleTugboat> lstScheduleTugboats = VmaScheduleTugboatLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo,
									noticeShipType);
					// VmaScheduleTugboatList
					if (lstScheduleTugboats != null) {
						for (VmaScheduleTugboat curScheduleTugboat : lstScheduleTugboats) {
							List<VmaScheduleTugboatList> lstScheduleTugboatList = VmaScheduleTugboatListLocalServiceUtil
									.findByItineraryNo_SequenceNo(itineraryNo,
											curScheduleTugboat.getSequenceNo());
							// Delete Tugboat + Tugboatlist
							VmaScheduleTugboatLocalServiceUtil
									.deleteVmaScheduleTugboat(curScheduleTugboat);
							if (lstScheduleTugboatList != null) {
								for (VmaScheduleTugboatList curScheduleTugboatList : lstScheduleTugboatList) {
									VmaScheduleTugboatListLocalServiceUtil
											.deleteVmaScheduleTugboatList(curScheduleTugboatList);
								}
							}
						}
						desciption += "\n Xoa Tugboat thanh cong";
					} else
						desciption += "Khong tim thay VmaScheduleTugboat";
				} catch (Exception e) {
					desciption += "\n" + e.getMessage();
				}

				try {
					// VmaSchedulePilot
					List<VmaSchedulePilot> lstSchedulePilot = VmaSchedulePilotLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo,
									noticeShipType);
					// VmaSchedulePilotList
					if (lstSchedulePilot != null) {
						for (VmaSchedulePilot curSchedulePilot : lstSchedulePilot) {
							List<VmaSchedulePilotList> lstSchedulePilotList = VmaSchedulePilotListLocalServiceUtil
									.findByItineraryNo_SequenceNo(itineraryNo,
											curSchedulePilot.getSequenceNo());
							// Delete Pilot + Pilotlist
							VmaSchedulePilotLocalServiceUtil
									.deleteVmaSchedulePilot(curSchedulePilot);
							if (lstSchedulePilotList != null) {
								for (VmaSchedulePilotList curVmaSchedulePilotList : lstSchedulePilotList) {
									VmaSchedulePilotListLocalServiceUtil
											.deleteVmaSchedulePilotList(curVmaSchedulePilotList);
								}
							}
						}
						desciption += "\n Xoa list Pilot thanh cong";
					} else
						desciption += "Khong tim thay VmaSchedulePilot";
				} catch (Exception e) {
					desciption += "\n" + e.getMessage();
				}

				String nameOfShip = curItinerarySchedule.getNameOfShip();

				if (curItinerarySchedule.getId() > 0) {
					// Xoa VmaScheduleAnchorage
					VmaScheduleAnchorage vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
							.fetchByitineraryScheduleId(curItinerarySchedule
									.getId());
					if (vmaScheduleAnchorage != null) {
						String anchorageNameOfShip = vmaScheduleAnchorage
								.getNameOfShip();
						String anchorageItineraryNo = vmaScheduleAnchorage
								.getItineraryNo();

						VmaScheduleAnchorageLocalServiceUtil
								.deleteVmaScheduleAnchorage(vmaScheduleAnchorage);

						// Write logs - add by Dungnv
						if (userPort != null) {
							VmaAuditLogLocalServiceUtil.addVmaAuditLog(
									userPort.getUserId(),
									userSign != null ? userSign.getModifyuser()
											: StringPool.BLANK,
									LogsConstant.XOA,
									"vma_schedule_anchorage",
									anchorageItineraryNo,
									"Delete from vma_itinerary_schedule NameOfShip: "
											+ anchorageNameOfShip
											+ ", ItinerarayScheduleId"
											+ curItinerarySchedule
													.getId());
						}
					}
				}

				// Xoa vmaScheduleTransfer
				VmaScheduleTransfer vmaScheduleTransfer = null;
				try {
					vmaScheduleTransfer = VmaScheduleTransferLocalServiceUtil
							.findByItineraryScheduleId(curItinerarySchedule
									.getId());
					VmaScheduleTransferLocalServiceUtil
							.deleteVmaScheduleTransfer(vmaScheduleTransfer);
				} catch (Exception e) {
				}

				// Xóa đối tượng gốc
				VmaItineraryScheduleLocalServiceUtil
						.deleteVmaItinerarySchedule(curItinerarySchedule);

				// Write logs - add by Dungnv
				if (userPort != null) {
					VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
							.getUserId(),
							userSign != null ? userSign.getModifyuser()
									: StringPool.BLANK, LogsConstant.XOA,
							"vma_itinerary_schedule", String.valueOf(id),
							"Delete " + nameOfShip);
				}

				return VMAUtils.createResponseMessage(
						LanguageUtil.get(themeDisplay.getLocale(), "success"),
						"", desciption);
			} catch (Exception e) {
				return VMAUtils.createResponseMessage(LanguageUtil.get(
						themeDisplay.getLocale(), "system_error"),
						"system_error", desciption);
			}
		} else {
			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "incorrect_identifier"),
					"incorrect_identifier", StringPool.BLANK);
		}
	}

	public static JSONObject doFind2(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);

		try {
			String searchQuery = "SELECT * FROM vma_itinerary_schedule WHERE ItineraryNo = '"
					+ itineraryNo
					+ "' ORDER BY CASE (NoticeShipType) WHEN 1 THEN 1 WHEN 2 THEN 3 WHEN 4 THEN 2 END ASC, NoticeShipType, ID ASC";
			List<VmaItinerarySchedule> vmaItinerarySchedules = VmaItineraryScheduleLocalServiceUtil
					.findVmaItinerarySchedule(searchQuery, -1, -1);



			JSONObject result = JSONFactoryUtil.createJSONObject();

			JSONArray data = JSONFactoryUtil.createJSONArray();


			for (VmaItinerarySchedule vmaItinerarySchedule : vmaItinerarySchedules) {
				JSONObject object = VMAUtils.object2Json(vmaItinerarySchedule,
						VmaItinerarySchedule.class, new String[] {
								"portRegionCode_VN", "portHarbourCode_VN",
								"portWharfCode_VN" });

				data.put(object);
			}

			result.put("total", vmaItinerarySchedules.size());

			result.put("data", data);

			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}

	}

	public static JSONObject doFind(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = VMAUtils.getString(request,
				"itineraryNo", StringPool.BLANK);

		int start = GetterUtil.getInteger(request.getParameter("start"), 0);

		int end = GetterUtil.getInteger(request.getParameter("end"), 15);

		String timeOfArrival = ParamUtil.getString(request,
				"timeOfArrival", StringPool.BLANK);
		
		String timeOfDeparture = ParamUtil.getString(request,
				"timeOfDeparture", StringPool.BLANK);

		String maritimeCode = VMAUtils.getString(request,
				"maritimeCode", StringPool.BLANK);

		String shipPosition = VMAUtils.getString(request,
				"shipPosition", StringPool.BLANK);

		String markedAsArrival = StringPool.BLANK;
		String markedAsDeparture = StringPool.BLANK;
		String noticeShipType = ParamUtil.getString(request,
				"noticeShipType", StringPool.BLANK);
		markedAsArrival = ParamUtil.getString(request, "markedAsArrival",
				StringPool.BLANK);
		markedAsDeparture = ParamUtil.getString(request, "markedAsDeparture",
				StringPool.BLANK);

		String nameOfShip = VMAUtils.getString(request, "nameOfShip",
				StringPool.BLANK);
		String flagStateOfShip = VMAUtils.getString(request,
				"flagStateOfShip", StringPool.BLANK);

		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request,
				"registryNumber", StringPool.BLANK);

		double gt = GetterUtil.getInteger(request.getParameter("gt"), -1);
		double dwt = GetterUtil.getInteger(request.getParameter("dwt"), -1);
		double loa = GetterUtil.getInteger(request.getParameter("loa"), -1);

		double maxDraft = GetterUtil.getInteger(
				request.getParameter("maxDraft"), -1);
		double shownDraftxF = GetterUtil.getInteger(
				request.getParameter("shownDraftxF"), -1);
		double shownDraftxA = GetterUtil.getInteger(
				request.getParameter("shownDraftxA"), -1);

		String routeLevelCode = VMAUtils.getString(request,
				"routeLevelCode", StringPool.BLANK);

		String chanelCodeList = VMAUtils.getString(request,
				"chanelCodeList", StringPool.BLANK);

		String vrCode = VMAUtils.getString(request, "vrCode",
				StringPool.BLANK);

		String shipBoat = VMAUtils.getString(request, "shipBoat",
				StringPool.BLANK);
		
		String itineraryType = ParamUtil.getString(request,
				"itineraryType", StringPool.BLANK);
		
		if (itineraryType.equals("departure") || itineraryType.equals("arrival") ) {
			if (shipBoat.equalsIgnoreCase("SHIP")) {
				shipBoat = StringPool.BLANK;
			}
		}

		String portRegionCode = VMAUtils.getString(request,
				"portRegionCode", StringPool.BLANK);
		String portHarbourCode = VMAUtils.getString(request,
				"portHarbourCode", StringPool.BLANK);
		String portWharfCode = VMAUtils.getString(request,
				"anchoringPortWharfCode", StringPool.BLANK);

		String cargoType = VMAUtils.getString(request, "cargoType",
				StringPool.BLANK);

		try {
			JSONObject result = VmaItineraryScheduleLocalServiceUtil
					.findVmaItinerary_VmaItinerarySchedule(itineraryNo,
							maritimeCode, shipPosition, markedAsArrival,
							markedAsDeparture, noticeShipType, shipBoat,
							portRegionCode, portHarbourCode, portWharfCode, 
							cargoType, vrCode, routeLevelCode, chanelCodeList, 
							timeOfArrival, timeOfDeparture, nameOfShip, 
							flagStateOfShip, callSign, registryNumber,
							gt, dwt, loa, maxDraft, shownDraftxF, shownDraftxA, start, end);
			//Khi luot vao, luot roi khac nhau thi su dung VmaScheduleTransfer
			result = VMAUtils.transferVmaItinerarys(result);
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

	public static void doExport(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = VMAUtils.getString(request,
				"itineraryNo", StringPool.BLANK);

		String itineraryType = ParamUtil.getString(request,
				"itineraryType", StringPool.BLANK);

		String timeOfArrival = ParamUtil.getString(request,
				"timeOfArrival", StringPool.BLANK);
		
		String timeOfDeparture = ParamUtil.getString(request,
				"timeOfDeparture", StringPool.BLANK);

		String maritimeCode = VMAUtils.getString(request,
				"maritimeCode", StringPool.BLANK);
		
		String shipPosition = VMAUtils.getString(request,
				"shipPosition", StringPool.BLANK);

		String markedAsArrival = StringPool.BLANK;
		String markedAsDeparture = StringPool.BLANK;
		String noticeShipType = VMAUtils.getString(request,
				"noticeShipType", StringPool.BLANK);
		markedAsArrival = ParamUtil.getString(request, "markedAsArrival",
				StringPool.BLANK);
		markedAsDeparture = ParamUtil.getString(request, "markedAsDeparture",
				StringPool.BLANK);

		String nameOfShip = VMAUtils.getString(request, "nameOfShip",
				StringPool.BLANK);
		String flagStateOfShip = VMAUtils.getString(request,
				"flagStateOfShip", StringPool.BLANK);

		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request,
				"registryNumber", StringPool.BLANK);

		double gt = GetterUtil.getInteger(request.getParameter("gt"), -1);
		double dwt = GetterUtil.getInteger(request.getParameter("dwt"), -1);
		double loa = GetterUtil.getInteger(request.getParameter("loa"), -1);

		double maxDraft = GetterUtil.getInteger(
				request.getParameter("maxDraft"), -1);
		double shownDraftxF = GetterUtil.getInteger(
				request.getParameter("shownDraftxF"), -1);
		double shownDraftxA = GetterUtil.getInteger(
				request.getParameter("shownDraftxA"), -1);

		String routeLevelCode = VMAUtils.getString(request,
				"routeLevelCode", StringPool.BLANK);

		String chanelCodeList = VMAUtils.getString(request,
				"chanelCodeList", StringPool.BLANK);

		String vrCode = VMAUtils.getString(request, "vrCode",
				StringPool.BLANK);

		String shipBoat = VMAUtils.getString(request, "shipBoat",
				StringPool.BLANK);
		if (itineraryType.equals("departure") || itineraryType.equals("arrival") ) {
			if (shipBoat.equalsIgnoreCase("SHIP")) {
				shipBoat = StringPool.BLANK;
			}
		}

		String portRegionCode = VMAUtils.getString(request,
				"portRegionCode", StringPool.BLANK);
		String portHarbourCode = VMAUtils.getString(request,
				"portHarbourCode", StringPool.BLANK);
		String portWharfCode = VMAUtils.getString(request,
				"anchoringPortWharfCode", StringPool.BLANK);

		String cargoType = VMAUtils.getString(request, "cargoType",
				StringPool.BLANK);

		
		String[][] exportData = null;

		if (itineraryType.equals("departure")) {
			/*
			 * String[] headers = new String[] { "STT", "Tên tàu", "Quốc tịch",
			 * "Hô hiệu", "GT", "DWT", "Chiều dài", "Mớn nước", "Loại hàng hóa",
			 * "Vị trí neo đậu", "Dự kiến rời SG", "Đại lý", "Tuyến luồng" }
			 */;
			String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
					"Qu\u1ED1c t\u1ECBch", "H\u00F4 hi\u1EC7u", "GT", "DWT",
					"Chi\u1EC1u d\u00E0i", "M\u1EDBn n\u01B0\u1EDBc",
					"Lo\u1EA1i h\u00E0ng h\u00F3a",
					"V\u1ECB tr\u00ED neo \u0111\u1EADu",
					"D\u1EF1 ki\u1EBFn r\u1EDDi SG", "\u0110\u1EA1i l\u00FD",
					"Tuy\u1EBFn lu\u1ED3ng" };
			try {
				JSONObject objects = VmaItineraryScheduleLocalServiceUtil
						.findVmaItinerary_VmaItinerarySchedule(itineraryNo,
								maritimeCode, shipPosition, markedAsArrival,
								markedAsDeparture, noticeShipType, shipBoat,
								portRegionCode, portHarbourCode, portWharfCode, 
								cargoType, vrCode, routeLevelCode, chanelCodeList, 
								timeOfArrival, timeOfDeparture, nameOfShip, 
								flagStateOfShip, callSign, registryNumber,
								gt, dwt, loa, maxDraft, shownDraftxF, shownDraftxA, -1, -1);
				//Khi luot vao, luot roi khac nhau thi su dung VmaScheduleTransfer
				objects = VMAUtils.transferVmaItinerarys(objects);

				JSONArray data = objects.getJSONArray("data");

				if (data != null && data.length() > 0) {
					exportData = new String[data.length()][headers.length];
					for (int i = 0; i < data.length(); i++) {
						JSONObject object = data.getJSONObject(i);

						int stt = i + 1;

						nameOfShip = object.getString("nameOfShip");

						flagStateOfShip = object.getString("flagStateOfShip");

						callSign = object.getString("callSign");

						gt = object.getDouble("gt");
						dwt = object.getDouble("dwt");
						loa = object.getDouble("loa");
						shownDraftxA = object.getDouble("shownDraftxA");
						shownDraftxF = object.getDouble("shownDraftxF");

						String loaihang = "";
						String vitrineodau = "";
						String timeOfDeparture_ = object
								.getString("timeOfDeparture");
						String daily = "";
						chanelCodeList = object.getString("chanelCodeList");

						exportData[i][0] = String.valueOf(stt);
						exportData[i][1] = nameOfShip;
						exportData[i][2] = flagStateOfShip;
						exportData[i][3] = callSign;
						exportData[i][4] = String.valueOf(gt);
						exportData[i][5] = String.valueOf(dwt);

						exportData[i][6] = shownDraftxA + "-" + shownDraftxF;
						exportData[i][7] = loaihang;
						exportData[i][8] = vitrineodau;

						exportData[i][9] = timeOfDeparture_;
						exportData[i][10] = daily;
						exportData[i][11] = chanelCodeList;

					}
				}

				String sheetName = "VMA-Schedule-Departure";

				String fileName = sheetName + "-" + System.currentTimeMillis();

				String filePath = VMAUtils.doExportExcelFile(request,
						resourceResponse, sheetName, fileName, headers,
						exportData);
//todo response file in controller
//				resourceResponse
//						.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//				resourceResponse.setProperty("Content-Disposition",
//						"attachment; filename= " + fileName + ".xls");
//				resourceResponse.setCharacterEncoding("UTF-8");
//				// resourceResponse.setProperty("filename", fileName + ".xls");
//				resourceResponse.addProperty("FILE-NAME", fileName + ".xls");
//
//				resourceResponse.getPortletOutputStream().write(
//						VMAUtils.readFileToByteArray(new File(filePath)));

			} catch (Exception e) {
				log.error(e.getMessage());

			}
		} else if (itineraryType.equals("arrival")) {
			/*
			 * String[] headers = new String[] { "STT", "Tên tàu", "Quốc tịch",
			 * "Hô hiệu", "GT", "DWT", "Chiều dài", "Mớn nước", "Loại hàng hóa",
			 * "Vị trí neo đậu", "Dự kiến đến VT", "Thời gian đến VT", "Đại lý",
			 * "Tuyến luồng" };
			 */

			String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
					"Qu\u1ED1c t\u1ECBch", "H\u00F4 hi\u1EC7u", "GT", "DWT",
					"Chi\u1EC1u d\u00E0i", "M\u1EDBn n\u01B0\u1EDBc",
					"Lo\u1EA1i h\u00E0ng h\u00F3a",
					"V\u1ECB tr\u00ED neo \u0111\u1EADu",
					"D\u1EF1 ki\u1EBFn \u0111\u1EBFn VT",
					"Th\u1EDDi gian \u0111\u1EBFn VT", "\u0110\u1EA1i l\u00FD",
					"Tuy\u1EBFn lu\u1ED3ng" };

			try {
				JSONObject objects = VmaItineraryScheduleLocalServiceUtil
						.findVmaItinerary_VmaItinerarySchedule(itineraryNo,
								maritimeCode, shipPosition, markedAsArrival,
								markedAsDeparture, noticeShipType, shipBoat,
								portRegionCode, portHarbourCode, portWharfCode, 
								cargoType, vrCode, routeLevelCode, chanelCodeList, 
								timeOfArrival, timeOfDeparture, nameOfShip, 
								flagStateOfShip, callSign, registryNumber,
								gt, dwt, loa, maxDraft, shownDraftxF, shownDraftxA, -1, -1);

				JSONArray data = objects.getJSONArray("data");

				if (data != null && data.length() > 0) {
					exportData = new String[data.length()][headers.length];
					for (int i = 0; i < data.length(); i++) {
						JSONObject object = data.getJSONObject(i);

						int stt = i + 1;

						nameOfShip = object.getString("nameOfShip");

						flagStateOfShip = object.getString("flagStateOfShip");

						callSign = object.getString("callSign");

						gt = object.getDouble("gt");
						dwt = object.getDouble("dwt");
						loa = object.getDouble("loa");
						shownDraftxA = object.getDouble("shownDraftxA");
						shownDraftxF = object.getDouble("shownDraftxF");

						String loaihang = "";
						String vitrineodau = "";
						String timeOfDeparture_ = object
								.getString("timeOfDeparture");
						String daily = "";
						chanelCodeList = object.getString("chanelCodeList");

						exportData[i][0] = String.valueOf(stt);
						exportData[i][1] = nameOfShip;
						exportData[i][2] = flagStateOfShip;
						exportData[i][3] = callSign;
						exportData[i][4] = String.valueOf(gt);
						exportData[i][5] = String.valueOf(dwt);

						exportData[i][6] = shownDraftxA + "-" + shownDraftxF;
						exportData[i][7] = loaihang;
						exportData[i][8] = vitrineodau;

						exportData[i][9] = timeOfDeparture_;
						exportData[i][10] = daily;
						exportData[i][11] = chanelCodeList;

					}
				}

				String sheetName = "VMA-Schedule-Departure";

				String fileName = sheetName + "-" + System.currentTimeMillis();

				String filePath = VMAUtils.doExportExcelFile(request,
						resourceResponse, sheetName, fileName, headers,
						exportData);
//todo response file in controller
//				resourceResponse
//						.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//				resourceResponse.setProperty("Content-Disposition",
//						"attachment; filename= " + fileName + ".xls");
//				resourceResponse.setCharacterEncoding("UTF-8");
//				// resourceResponse.setProperty("filename", fileName + ".xls");
//				resourceResponse.addProperty("FILE-NAME", fileName + ".xls");
//
//				resourceResponse.getPortletOutputStream().write(
//						VMAUtils.readFileToByteArray(new File(filePath)));

			} catch (Exception e) {
				log.error(e.getMessage());

			}
		}

	}

	public static long doCount(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		
		String itineraryNo = VMAUtils.getString(request,
				"itineraryNo", StringPool.BLANK);

		String itineraryType = VMAUtils.getString(request,
				"itineraryType", StringPool.BLANK);

		String timeOfArrival = ParamUtil.getString(request,
				"timeOfArrival", StringPool.BLANK);
		
		String timeOfDeparture = ParamUtil.getString(request,
				"timeOfDeparture", StringPool.BLANK);

		String maritimeCode = ParamUtil.getString(request,
				"maritimeCode", StringPool.BLANK);

		// String shipPosition = StringPool.BLANK;
		String shipPosition = ParamUtil.getString(request,
				"shipPosition", StringPool.BLANK);

		String markedAsArrival = StringPool.BLANK;
		String markedAsDeparture = StringPool.BLANK;
		String noticeShipType = VMAUtils.getString(request,
				"noticeShipType", StringPool.BLANK);
		markedAsArrival = VMAUtils.getString(request, "markedAsArrival",
				StringPool.BLANK);
		markedAsDeparture = VMAUtils.getString(request, "markedAsDeparture",
				StringPool.BLANK);

		String nameOfShip = VMAUtils.getString(request, "nameOfShip",
				StringPool.BLANK);
		String flagStateOfShip = VMAUtils.getString(request,
				"flagStateOfShip", StringPool.BLANK);

		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request,
				"registryNumber", StringPool.BLANK);

		double gt = GetterUtil.getInteger(request.getParameter("gt"), -1);
		double dwt = GetterUtil.getInteger(request.getParameter("dwt"), -1);
		double loa = GetterUtil.getInteger(request.getParameter("loa"), -1);

		double maxDraft = GetterUtil.getInteger(
				request.getParameter("maxDraft"), -1);
		double shownDraftxF = GetterUtil.getInteger(
				request.getParameter("shownDraftxF"), -1);
		double shownDraftxA = GetterUtil.getInteger(
				request.getParameter("shownDraftxA"), -1);

		String routeLevelCode = VMAUtils.getString(request,
				"routeLevelCode", StringPool.BLANK);

		String chanelCodeList = VMAUtils.getString(request,
				"chanelCodeList", StringPool.BLANK);

		String vrCode = VMAUtils.getString(request, "vrCode",
				StringPool.BLANK);

		String shipBoat = VMAUtils.getString(request, "shipBoat",
				StringPool.BLANK);
		if (itineraryType.equals("departure") || itineraryType.equals("arrival") ) {
			if (shipBoat.equalsIgnoreCase("SHIP")) {
				shipBoat = StringPool.BLANK;
			}
		}
		String portRegionCode = VMAUtils.getString(request,
				"portRegionCode", StringPool.BLANK);
		String portHarbourCode = VMAUtils.getString(request,
				"portHarbourCode", StringPool.BLANK);
		String portWharfCode = VMAUtils.getString(request,
				"anchoringPortWharfCode", StringPool.BLANK);

		String cargoType = VMAUtils.getString(request, "cargoType",
				StringPool.BLANK);

		try {
			return VmaItineraryScheduleLocalServiceUtil
					.countVmaItinerary_VmaItinerarySchedule(itineraryNo,
							maritimeCode, shipPosition, markedAsArrival,
							markedAsDeparture, noticeShipType, shipBoat,
							portRegionCode, portHarbourCode, portWharfCode, 
							cargoType, vrCode, routeLevelCode, chanelCodeList, 
							timeOfArrival, timeOfDeparture, nameOfShip, 
							flagStateOfShip, callSign, registryNumber,
							gt, dwt, loa, maxDraft, shownDraftxF, shownDraftxA);
		} catch (Exception e) {
			log.error(e.getMessage());

			return 0;
		}
	}

	
	
	public static JSONObject getLastVmaItinerarySchedule(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws JSONException {
		HttpServletRequest request = resourceRequest;

		long documentName = ParamUtil.getLong(request, "documentName");
		int documentYear = ParamUtil.getInteger(request, "documentYear");
		List<VmaItinerarySchedule> vmaItinerarySchedules = VmaItineraryScheduleLocalServiceUtil
				.findByDocumentName_DocumentYear(documentName, documentYear);
		VmaItinerarySchedule vmaItinerarySchedule = new VmaItinerarySchedule();
		long max = 0;
		for (VmaItinerarySchedule itinerarySchedule : vmaItinerarySchedules) {
			if (itinerarySchedule.getId() > max) {
				max = itinerarySchedule.getId();
				vmaItinerarySchedule = itinerarySchedule;
			}
		}
		if (vmaItinerarySchedule != null) {
			return VMAUtils.object2Json(vmaItinerarySchedule,
					VmaItinerarySchedule.class);
		}
		return JSONFactoryUtil.createJSONObject();
	}

	public static JSONObject findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws SystemException {

		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 15);
		String timeOfArrival = ParamUtil.getString(request, "timeOfArrival",
				StringPool.BLANK);
		String maritimeCode = ParamUtil.getString(request, "maritimeCode",
				StringPool.BLANK);
		String shipPosition = ParamUtil.getString(request, "shipPosition",
				StringPool.BLANK);
		String markedAsArrival = StringPool.BLANK;
		String markedAsDeparture = StringPool.BLANK;
		String noticeShipType = ParamUtil.getString(request, "noticeShipType",
				StringPool.BLANK);
		String requestState = StringPool.BLANK;
		markedAsArrival = ParamUtil.getString(request, "markedAsArrival",
				StringPool.BLANK);
		markedAsDeparture = ParamUtil.getString(request, "markedAsDeparture",
				StringPool.BLANK);
		requestState = ParamUtil.getString(request, "requestState",
				StringPool.BLANK);

		/*
		 * if (noticeShipType.contains("1")) { markedAsArrival =
		 * ParamUtil.getString(request, "markedAsArrival", StringPool.BLANK); }
		 * else if (noticeShipType.contains("2")) { log.info("=== vao day");
		 * markedAsDeparture = ParamUtil.getString(request, "markedAsDeparture",
		 * StringPool.BLANK); log.info("=== vao day markedAsDeparture " +
		 * markedAsDeparture);
		 * 
		 * } else if (noticeShipType.contains("4") ||
		 * noticeShipType.contains("5")) { requestState =
		 * ParamUtil.getString(request, "requestState", StringPool.BLANK); }
		 * else if (noticeShipType.contains(StringPool.BLANK)) { markedAsArrival
		 * = ParamUtil.getString(request, "markedAsArrival", StringPool.BLANK);
		 * markedAsDeparture = ParamUtil.getString(request, "markedAsDeparture",
		 * StringPool.BLANK); requestState = ParamUtil.getString(request,
		 * "requestState", StringPool.BLANK); }
		 */

		String shipBoat = ParamUtil.getString(request, "shipBoat",
				StringPool.BLANK);
		String portRegionCode = ParamUtil.getString(request, "portRegionCode",
				StringPool.BLANK);
		String certificateNo = ParamUtil.getString(request, "certificateNo",
				StringPool.BLANK);

		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray array = VmaItineraryScheduleLocalServiceUtil
				.findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting(
						itineraryNo, maritimeCode, shipPosition,
						markedAsArrival, markedAsDeparture, noticeShipType,
						shipBoat, timeOfArrival, portRegionCode, certificateNo,
						requestState, false, null, null, start, end);

		result.put("data", array);

		return result;
	}

	public static String genCertificateNo(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws SystemException {

		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);

		long documentName = ParamUtil.getLong(request, "documentName");

		long documentYear = ParamUtil.getLong(request, "documentYear");

		String maritimeCode = ParamUtil.getString(request, "maritimeCode",
				StringPool.BLANK);

		return VmaItineraryScheduleLocalServiceUtil.genCertificateNo(
				documentName, documentYear, maritimeCode, itineraryNo);
	}

	public static String genDocumentNameVoy(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws SystemException {

		HttpServletRequest request = resourceRequest;

		String maritimeCode = ParamUtil.getString(request, "maritimeCode",
				StringPool.BLANK);

		return VmaItineraryScheduleLocalServiceUtil
				.genDocumentNameVoy(maritimeCode);
	}

	// SonVH tam thoi khong su dung, noi dung khong dung
	public JSONObject export2excel(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest)
			throws NoSuchVmaItineraryScheduleException, SystemException,
			ParsePropertyException, InvalidFormatException, IOException,
			NoSuchVmaItineraryException, ParseException, 
			JRException {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		int printing = ParamUtil.getInteger(request, "printing");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaItinerarySchedule vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
				.findByItineraryNo_NoticeShipType(itineraryNo, 2);
		VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
				.findByitineraryNo(vmaItinerarySchedule.getItineraryNo());
		String maritimeReference = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(
						UserPortLocalServiceUtil.findByUserId(
								themeDisplay.getUserId()).getPortCode())
				.getMaritimeReference();
		JSONObject data = JSONFactoryUtil.createJSONObject();

		long nanoTime = System.nanoTime();
		String tenFileExport = nanoTime + ".xls";

		result.put("printing", printing);
		result.put(
				"maritimeNameVN",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(
						UserPortLocalServiceUtil.findByUserId(
								themeDisplay.getUserId()).getPortCode())
						.getMaritimeNameVN());
		result.put(
				"maritimeName",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(
						UserPortLocalServiceUtil.findByUserId(
								themeDisplay.getUserId()).getPortCode())
						.getMaritimeName());
		result.put("certificateNo", vmaItinerarySchedule.getCertificateNo());
		result.put("nameOfShip", vmaItinerarySchedule.getNameOfShip());
		try {
			result.put(
					"nameOfMaster",
					VmaShipLocalServiceUtil.findByIMONumber_CallSign(
							vmaItinerary.getImoNumber(),
							vmaItinerary.getCallSign()).getNameOfMaster());
		} catch (Exception e) {

		}
		result.put("callSign", vmaItinerary.getCallSign());
		result.put("registryNumber", vmaItinerary.getRegistryNumber());
		result.put("gt", vmaItinerarySchedule.getGt());
		result.put("dwt", vmaItinerarySchedule.getDwt());
		result.put("dwtUnit", vmaItinerarySchedule.getUnitDWT());
		result.put("numberOfPassengers",
				vmaItinerarySchedule.getPassengerNumber());
		result.put("numberOfCrews", vmaItinerarySchedule.getCrewNumber());
		result.put("timeOfDeparture", vn.gt.tichhop.report.ReportFunction
				.parserDateToString3(vmaItinerarySchedule.getTimeOfDeparture()));
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(vmaItinerarySchedule.getTimeOfDeparture());
			int hours = calendar.get(Calendar.HOUR_OF_DAY);
			String sHours = "";
			if (hours < 10) {
				sHours = "0" + hours;
			} else {
				sHours = hours + "";
			}
			int minutes = calendar.get(Calendar.MINUTE);
			String sMinutes = "";
			if (minutes < 10) {
				sMinutes = "0" + minutes;
			} else {
				sMinutes = minutes + "";
			}
			int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
			String sDay = "";
			if (day < 10) {
				sDay = "0" + day;
			} else {
				sDay = day + "";
			}
			int month = calendar.get(Calendar.MONTH) + 1;
			String sMonth = "";
			if (month < 10) {
				sMonth = "0" + month;
			} else {
				sMonth = month + "";
			}
			int year = calendar.get(Calendar.YEAR);

			result.put("validUnit", hours + ":" + minutes + StringPool.SPACE
					+ NGAY + StringPool.SPACE + sDay + StringPool.SPACE + THANG
					+ StringPool.SPACE + sMonth + StringPool.SPACE + NAM
					+ StringPool.BLANK + year);
		} catch (Exception e) {
		}

		if ("VR-SI VR-SII VR-SB-SI VR-SB-SII VR-SB-SI-SII VR-SI-SII ---"
				.contains(vmaItinerary.getVrCode().trim()) && !vmaItinerary.getVrCode().trim().equalsIgnoreCase("VR")) {
			List<VmaScheduleCargolist> vmaScheduleCargolists = VmaScheduleCargolistLocalServiceUtil
					.findByItineraryNo(vmaItinerarySchedule.getItineraryNo());
			StringBuilder cargoUnloadingDescription = new StringBuilder();
			StringBuilder cargoLoadingDescription = new StringBuilder();
			double cargoUnloading_Quantity = 0;
			int cargoUnloading_TeusQuantity = 0;
			double cargoLoading_Quantity = 0;
			int cargoLoading_TeusQuantity = 0;
			for (int i = 0; i < vmaScheduleCargolists.size(); i++) {
				if ("C2_DO C1_DO C3_DO C6_DO".contains(vmaScheduleCargolists
						.get(i).getCargoCategory())) {
					try {
						if (i > 0) {
							cargoUnloadingDescription.append(", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						} else {
							cargoUnloadingDescription
									.append(DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						}
					} catch (Exception e) {
					}
					cargoUnloading_Quantity += vmaScheduleCargolists.get(i)
							.getQuantity().doubleValue();
					cargoUnloading_TeusQuantity += vmaScheduleCargolists.get(i)
							.getTeusQuantity().doubleValue();
				} else if ("C2_XEP C1_XEP C3_XEP C6_XEP"
						.contains(vmaScheduleCargolists.get(i)
								.getCargoCategory())) {
					try {
						if (i > 0) {
							cargoLoadingDescription.append(", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						} else {
							cargoLoadingDescription
									.append(DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						}
					} catch (Exception e) {
					}
					cargoLoading_Quantity += vmaScheduleCargolists.get(i)
							.getQuantity().doubleValue();
					cargoLoading_TeusQuantity += vmaScheduleCargolists.get(i)
							.getTeusQuantity().doubleValue();
				}
			}
			result.put("cargoLoading_Quantity", cargoLoading_Quantity);
			result.put("cargoLoading_TeusQuantity", cargoLoading_TeusQuantity);
			result.put("cargoUnloading_Quantity", cargoUnloading_Quantity);
			result.put("cargoUnloading_TeusQuantity",
					cargoUnloading_TeusQuantity);
			result.put("cargoUnloadingDescription",
					cargoUnloadingDescription.toString());
			result.put("cargoLoadingDescription",
					cargoLoadingDescription.toString());


			BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
			File file = new File(pathFileTemp + "report_CV/"
					+ maritimeReference + StringPool.UNDERLINE
					+ "vma_Pttnd_PortClearance.jrxml");
			boolean hasDataBoolean = false;
			if (file.exists()) {
				hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
						+ "report_CV/" + maritimeReference
						+ StringPool.UNDERLINE
						+ "vma_Pttnd_PortClearance.jrxml", tenFileExport,
						result, pathFileTemp + "report_CV/", 1);

				log.info("=====> " + "report_CV/" + maritimeReference
						+ StringPool.UNDERLINE
						+ "vma_Pttnd_PortClearance.jrxml");
			} else {
				hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
						+ "vma_Pttnd_PortClearance.jrxml", tenFileExport,
						result, pathFileTemp, 1);

				log.info("=====> " + "vma_Pttnd_PortClearance.jrxml");
			}

			String UrlFile = request.getContextPath() + tenFileExport;

			if (hasDataBoolean) {
				data.put("urlDownload", UrlFile);
			} else {
				data.put("urlDownload", StringPool.BLANK);
			}
		} else {
			List<VmaScheduleCargolist> vmaScheduleCargolists = VmaScheduleCargolistLocalServiceUtil
					.findByItineraryNo(vmaItinerarySchedule.getItineraryNo());
			StringBuilder cargoOnboadDescription = new StringBuilder();
			StringBuilder cargoTransitDescription = new StringBuilder();
			double cargoOnboad_Quantity = 0;
			int cargoOnboad_TeusQuantity = 0;
			double cargoTransit_Quantity = 0;
			int cargoTransit_TeusQuantity = 0;
			for (int i = 0; i < vmaScheduleCargolists.size(); i++) {
				if ("C2_VC C1_VC C3_VC C6_VC C5".contains(vmaScheduleCargolists
						.get(i).getCargoCategory())) {
					try {
						if (i > 0) {
							cargoOnboadDescription.append(", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						} else {
							cargoOnboadDescription
									.append(DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						}
					} catch (Exception e) {
					}
					cargoOnboad_Quantity += vmaScheduleCargolists.get(i)
							.getQuantity().doubleValue();
					cargoOnboad_TeusQuantity += vmaScheduleCargolists.get(i)
							.getTeusQuantity().doubleValue();
				} else if ("C5 C4".contains(vmaScheduleCargolists.get(i)
						.getCargoCategory())) {
					try {
						if (i > 0) {
							cargoTransitDescription.append(", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						} else {
							cargoTransitDescription
									.append(DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						}
					} catch (Exception e) {
					}
					cargoTransit_Quantity += vmaScheduleCargolists.get(i)
							.getQuantity().doubleValue();
					cargoTransit_TeusQuantity += vmaScheduleCargolists.get(i)
							.getTeusQuantity().doubleValue();
				}
			}
			result.put("cargoOnboad_Quantity", cargoOnboad_Quantity);
			result.put("cargoOnboad_TeusQuantity", cargoOnboad_TeusQuantity);
			result.put("cargoTransit_Quantity", cargoTransit_Quantity);
			result.put("cargoTransit_TeusQuantity", cargoTransit_TeusQuantity);
			result.put("cargoOnboadDescription",
					cargoOnboadDescription.toString());
			result.put("cargoTransitDescription",
					cargoTransitDescription.toString());


			BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
			File file = new File(pathFileTemp + "report_CV/"
					+ maritimeReference + StringPool.UNDERLINE
					+ "vma_PortClearance.jrxml");
			boolean hasDataBoolean = false;
			if (file.exists()) {
				hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
						+ "report_CV/" + maritimeReference
						+ StringPool.UNDERLINE + "vma_PortClearance.jrxml",
						tenFileExport, result, pathFileTemp + "report_CV/", 1);

				log.info("=====> " + "report_CV/" + maritimeReference
						+ StringPool.UNDERLINE + "vma_PortClearance.jrxml");
			} else {
				hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
						+ "vma_PortClearance.jrxml", tenFileExport, result,
						pathFileTemp, 1);

				log.info("=====> " + "vma_PortClearance.jrxml");
			}

			String UrlFile = request.getContextPath() + tenFileExport;

			if (hasDataBoolean) {
				data.put("urlDownload", UrlFile);
			} else {
				data.put("urlDownload", StringPool.BLANK);
			}
		}
		return data;
	}

	public static JSONObject keHoachDieuDongExport(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest)
			throws NoSuchVmaItineraryScheduleException, SystemException,
			ParseException, IOException, JRException {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaItinerarySchedule vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
				.findByItineraryNo_NoticeShipType(itineraryNo, 1);
		result.put(
				"maritimeNameVN",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(
						UserPortLocalServiceUtil.findByUserId(
								themeDisplay.getUserId()).getPortCode())
						.getMaritimeNameVN());
		result.put(
				"maritimeName",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(
						UserPortLocalServiceUtil.findByUserId(
								themeDisplay.getUserId()).getPortCode())
						.getMaritimeName());
		result.put("shiftingDate", vn.gt.tichhop.report.ReportFunction
				.parserDateToString3LT(vmaItinerarySchedule.getTimeOfArrival()));
		result.put("timeOfArrival", vn.gt.tichhop.report.ReportFunction
				.parserDateToString3LT(vmaItinerarySchedule.getTimeOfArrival()));
		result.put("timeOfApproval",
				vn.gt.tichhop.report.ReportFunction
						.parserDateToString3LT(vmaItinerarySchedule
								.getTimeOfApproval()));
		result.put("nameOfShip", vmaItinerarySchedule.getNameOfShip());
		result.put("shownDraftxF", vmaItinerarySchedule.getShownDraftxF());
		result.put("unitShownDraftxF",
				vmaItinerarySchedule.getUnitShownDraftxF());
		result.put("shownDraftxA", vmaItinerarySchedule.getShownDraftxA());
		result.put("unitShownDraftxA",
				vmaItinerarySchedule.getUnitShownDraftxA());
		result.put("loa", vmaItinerarySchedule.getLoa());
		result.put("loaUnit", vmaItinerarySchedule.getUnitLOA());
		result.put("dwt", vmaItinerarySchedule.getDwt());
		result.put("dwtUnit", vmaItinerarySchedule.getUnitDWT());
		result.put("tugBoat", vmaItinerarySchedule.getTugBoat());
		result.put("chanel", vmaItinerarySchedule.getChanelName());
		result.put("initFrom", vmaItinerarySchedule.getInitFrom());
		result.put("arrivalPortCode", vmaItinerarySchedule.getArrivalPortCode());
		result.put("portRegionCode", vmaItinerarySchedule.getPortRegionCode());
		result.put("portHarbourCode", vmaItinerarySchedule.getPortHarbourCode());
		result.put("portWharfCode", vmaItinerarySchedule.getPortWharfCode());
		try {
			result.put(
					"arrivalPortName",
					DmPortLocalServiceUtil.getByPortCode(
							vmaItinerarySchedule.getArrivalPortCode())
							.getPortName());
		} catch (Exception e) {

		}
		try {
			result.put(
					"portRegionNameVN",
					DmPortRegionLocalServiceUtil.getByPortRegionCode(
							vmaItinerarySchedule.getPortRegionCode())
							.getPortRegionNameVN());
		} catch (Exception e) {

		}
		try {
			result.put(
					"portHarbourNameVN",
					DmPortHarbourLocalServiceUtil.getByPortHarbourCode(
							vmaItinerarySchedule.getPortHarbourCode())
							.getPortHarbourNameVN());
		} catch (Exception e) {

		}
		try {
			result.put(
					"portWharfNameVN",
					DmPortWharfLocalServiceUtil.getByPortWharfCode(
							vmaItinerarySchedule.getPortWharfCode())
							.getPortWharfNameVN());
		} catch (Exception e) {

		}
		result.put("shipOwnersName", vmaItinerarySchedule.getShipOwnersName());
		result.put("shipAgencyName", vmaItinerarySchedule.getShipAgencyName());

		// SonVH sua ten tau = tau keo+ ds xa lan
		
		try {
			Double dwt_xalan = 0.0;
			Double gt_xalan = 0.0;
			String sdk_xalan = StringPool.BLANK;
			String xalan = StringPool.BLANK;
			List<VmaScheduleMerging> lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo, 1);
			if (lstVmaScheduleMerging != null
					&& lstVmaScheduleMerging.size() > 0) {
				int i = 0;
				for (VmaScheduleMerging vmaScheduleMerging : lstVmaScheduleMerging) {
					if (vmaScheduleMerging.getItineraryScheduleId() == vmaItinerarySchedule
							.getId()) {
						
						dwt_xalan = dwt_xalan + vmaScheduleMerging.getDwt().doubleValue();
						gt_xalan = gt_xalan + vmaScheduleMerging.getGt().doubleValue();
						i = i + 1;
						if (i == 1) {
							xalan = xalan + " ("
									+ vmaScheduleMerging.getShipLashName();
							sdk_xalan = sdk_xalan + " ("
									+ vmaScheduleMerging.getShipLashRegistryNumber();
						} else if (i <= lstVmaScheduleMerging.size()) {
							xalan = xalan + " + "
									+ vmaScheduleMerging.getShipLashName();
							sdk_xalan = sdk_xalan + " + "
									+ vmaScheduleMerging.getShipLashRegistryNumber();
						}
					}
				}
				if (i > 0) {
					xalan = xalan + ")";
					sdk_xalan = sdk_xalan + ")";
				}
				result.put("nameOfShip", vmaItinerarySchedule.getNameOfShip()
						+ xalan);				
				result.put("dwt", vmaItinerarySchedule.getDwt().doubleValue() + dwt_xalan);
			}
		} catch (Exception e) {

		}

		try {
			result.put("SignDate", FormatData
					.parseDateToTringDDMMYYY(vmaItinerarySchedule
							.getTimeOfArrival()));
		} catch (Exception e) {
		}
		result.put("SignTitle", DanhMucUtils.encodeUTF8("GIÁM ĐỐC"));

		BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
		boolean hasDataBoolean = action.export2Report(result,
				"vma_schedule_shifting_order_upgrade.jrxml",
				"vma_schedule_shifting_order_upgrade.pdf", 2);

		String UrlFile = request.getContextPath() + "/export/"
				+ "vma_schedule_shifting_order_upgrade.pdf";
		String UrlFileDownLoad = UrlFile.replace(".pdfs", ".pdf");

		JSONObject data = JSONFactoryUtil.createJSONObject();

		if (hasDataBoolean) {
			data.put("url", UrlFileDownLoad);
		} else {
			data.put("url", StringPool.BLANK);
		}

		return data;
	}

	private static String NGAY = ConfigurationManager.getStrProp(
			"vn.gt.label.report.ngay", "");
	private static String THANG = ConfigurationManager.getStrProp(
			"vn.gt.label.report.thang", "");
	private static String NAM = ConfigurationManager.getStrProp(
			"vn.gt.label.report.nam", "");

	public JSONObject yeuCauGiayPhepVaoRoi(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest)
			throws NoSuchVmaItineraryScheduleException, SystemException,
			NoSuchVmaItineraryException, ParseException, IOException, JRException {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		int printing = ParamUtil.getInteger(request, "printing");
		int noticeShipType = ParamUtil.getInteger(request, "noticeShipType");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaItinerarySchedule vmaItinerarySchedule = new VmaItinerarySchedule();
		VmaItinerarySchedule vmaItineraryScheduleIn = new VmaItinerarySchedule();
		VmaItinerary vmaItinerary = new VmaItinerary();
		try {
			vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo,
							noticeShipType);
			if (vmaItinerarySchedule == null) {
				vmaItinerarySchedule = new VmaItinerarySchedule();
			}
			vmaItineraryScheduleIn = VmaItineraryScheduleLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo, 1);
			if (vmaItineraryScheduleIn == null) {
				vmaItineraryScheduleIn = new VmaItinerarySchedule();
			}
			vmaItinerary = VmaItineraryLocalServiceUtil
					.findByitineraryNo(itineraryNo);
		} catch (Exception e) {

		}
		DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(UserPortLocalServiceUtil.findByUserId(
						themeDisplay.getUserId()).getPortCode());
		String maritimeReference = dmMaritime.getMaritimeReference();
		JSONObject data = JSONFactoryUtil.createJSONObject();

		long nanoTime = System.nanoTime();
		VmaShip objShip = new VmaShip();
		try {
			if (vmaItinerary.getImoNumber().trim().length() >= 7) {
				// findBy IMO, CallSign together
				objShip = VmaShipLocalServiceUtil
						.fetchByIMONumber_CallSign(vmaItinerary.getImoNumber(),
								vmaItinerary.getCallSign());
			} else {
				// findBy CallSign only
				objShip = VmaShipLocalServiceUtil.fetchByCallSign(vmaItinerary
						.getCallSign());
				// Tim lai theo registerNumber
				if (!objShip.getShipName().contains(
						vmaItinerary.getNameOfShip())) {
					objShip = VmaShipLocalServiceUtil
							.fetchByRegistryNumber(vmaItinerary
									.getRegistryNumber());
				}
			}
			result.put("nameOfMaster", objShip.getNameOfMaster());

		} catch (Exception e) {

		}
		String donviTan = DmUnitGeneralLocalServiceUtil.getByUnitCode("TNE")
				.getUnitName();
		try {
			result.put("flagStateOfShip", DmStateLocalServiceUtil
					.getByStateCode(vmaItinerary.getFlagStateOfShip())
					.getStateName());
		} catch (Exception e) {

		}
		result.put("callSign", vmaItinerary.getCallSign());
		result.put("registryNumber", vmaItinerary.getRegistryNumber());
		result.put("gt", vmaItinerarySchedule.getGt());
		result.put("dwt", vmaItinerarySchedule.getDwt());
		result.put("dwtUnit", donviTan);
		
		
		
		result.put("noticeShipType", noticeShipType);
		result.put("printing", printing);
		result.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
		result.put("maritimeName", dmMaritime.getMaritimeName());
		result.put("cityCode", dmMaritime.getCityCode());
		result.put("certificateNo", vmaItinerarySchedule.getCertificateNo());
		result.put("nameOfShip", vmaItinerarySchedule.getNameOfShip());
		// SonVH sua ten tau = tau keo+ ds xa lan
		try {
			Double dwt_xalan = 0.0;
			Double gt_xalan = 0.0;
			String sdk_xalan = StringPool.BLANK;
			String xalan = StringPool.BLANK;
			List<VmaScheduleMerging> lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo,
							noticeShipType);
			if (lstVmaScheduleMerging != null
					&& lstVmaScheduleMerging.size() > 0) {
				int i = 0;
				for (VmaScheduleMerging vmaScheduleMerging : lstVmaScheduleMerging) {
					if (vmaScheduleMerging.getItineraryScheduleId() == vmaItinerarySchedule
							.getId()) {
						
						dwt_xalan = dwt_xalan + vmaScheduleMerging.getDwt().doubleValue();
						gt_xalan = gt_xalan + vmaScheduleMerging.getGt().doubleValue();
						i = i + 1;
						if (i == 1) {
							xalan = xalan + " ("
									+ vmaScheduleMerging.getShipLashName();
							sdk_xalan = sdk_xalan + " ("
									+ vmaScheduleMerging.getShipLashRegistryNumber();
						} else if (i <= lstVmaScheduleMerging.size()) {
							xalan = xalan + " + "
									+ vmaScheduleMerging.getShipLashName();
							sdk_xalan = sdk_xalan + " + "
									+ vmaScheduleMerging.getShipLashRegistryNumber();
						}
					}
				}
				if (i > 0) {
					xalan = xalan + ")";
					sdk_xalan = sdk_xalan + ")";
				}
				result.put("nameOfShip", vmaItinerarySchedule.getNameOfShip()
						+ xalan);
				result.put("callSign", vmaItinerary.getCallSign() + sdk_xalan);
				result.put("registryNumber", vmaItinerary.getRegistryNumber() + sdk_xalan);
				result.put("dwt", vmaItinerarySchedule.getDwt().doubleValue() + dwt_xalan);
				result.put("gt", vmaItinerarySchedule.getGt().doubleValue() + gt_xalan);
			}
		} catch (Exception e) {

		}
		

		
		result.put("numberOfPassengers",
				vmaItinerarySchedule.getPassengerNumber());
		result.put("numberOfCrews", vmaItinerarySchedule.getCrewNumber());

		// Vao cang
		result.put("arrivalPortCode",
				vmaItineraryScheduleIn.getArrivalPortCode());
		result.put("portRegionCode", vmaItineraryScheduleIn.getPortRegionCode());
		result.put("portHarbourCode",
				vmaItineraryScheduleIn.getPortHarbourCode());
		result.put("portWharfCode", vmaItineraryScheduleIn.getPortWharfCode());
		try {
			result.put(
					"arrivalPortName",
					DmPortLocalServiceUtil.getByPortCode(
							vmaItineraryScheduleIn.getArrivalPortCode())
							.getPortName());
		} catch (Exception e) {

		}
		try {
			result.put(
					"portRegionNameVN",
					DmPortRegionLocalServiceUtil.getByPortRegionCode(
							vmaItineraryScheduleIn.getPortRegionCode())
							.getPortRegionNameVN());
		} catch (Exception e) {

		}
		try {
			result.put(
					"portHarbourNameVN",
					DmPortHarbourLocalServiceUtil.getByPortHarbourCode(
							vmaItineraryScheduleIn.getPortHarbourCode())
							.getPortHarbourNameVN());
		} catch (Exception e) {

		}
		try {
			result.put(
					"portWharfNameVN",
					DmPortWharfLocalServiceUtil.getByPortWharfCode(
							vmaItineraryScheduleIn.getPortWharfCode())
							.getPortWharfNameVN());
		} catch (Exception e) {

		}
		if (vmaItineraryScheduleIn.getTimeOfArrival() != null) {
			result.put("timeOfArrival", FormatData
					.parseDateToTringType3(vmaItineraryScheduleIn
							.getTimeOfArrival()));
		}
		// Cap man tau
		result.put("mergedShip", vmaItineraryScheduleIn.getMergedShip());

		if (vmaItinerarySchedule.getTimeOfDeparture() != null) {
			result.put("timeOfDeparture", FormatData
					.parseDateToTringType3(vmaItinerarySchedule
							.getTimeOfDeparture()));
		}
		// Cang den ke tiep
		if (vmaItinerarySchedule.getNextProvinceCode().length() > 0) {
			result.put("nextPortOfCallCodeName",
					vmaItinerarySchedule.getNextProvinceCode());
		} else if (vmaItinerarySchedule.getPortGoingToCode().length() > 0) {
			result.put("nextPortOfCallCodeName", DmPortLocalServiceUtil
					.getByPortCode(vmaItinerarySchedule.getPortGoingToCode())
					.getPortName());
		}
		// Ngay cap
		try {
			if (vmaItinerarySchedule.getTimeOfApproval() != null) {
				result.put("SignDate", FormatData
						.parseDateToTringDDMMYYY(vmaItinerarySchedule
								.getTimeOfApproval()));
				result.put("issueDate", FormatData
						.parseDateToTringType3(vmaItinerarySchedule
								.getTimeOfApproval()));
			} else {
				result.put("SignDate",
						FormatData.parseDateToTringDDMMYYY(new Date()));
				result.put("issueDate",
						FormatData.parseDateToTringType3(new Date()));
			}
		} catch (Exception e) {
		}
		result.put("SignTitle", DanhMucUtils.encodeUTF8("GIÁM ĐỐC"));
		try {			
			result.put("validUntil", FormatData.congNgay(vmaItinerarySchedule.getTimeOfDeparture()));
			
		} catch (Exception e) {
		}

		if ("VR VRH VRH1 VRH2 VRH3 VR200 VR-NA VR-SB"
				.contains(vmaItinerary.getVrCode().trim())
				|| (("VR-SB-SI VR-SB-SII VR-SB-SI-SII ---"
						.contains(vmaItinerary.getVrCode().trim())) && (vmaItinerarySchedule
						.getRouteLevelCode().contains("1")))) {
			List<VmaScheduleCargolist> vmaScheduleCargolists = VmaScheduleCargolistLocalServiceUtil
					.findByItineraryNo(vmaItinerarySchedule.getItineraryNo());
			StringBuilder cargoOnboadDescription = new StringBuilder();
			StringBuilder cargoOnboadVC = new StringBuilder();
			StringBuilder cargoTransitDescription = new StringBuilder();

			for (VmaScheduleCargolist tempCargoItems2 : vmaScheduleCargolists) {
				if ((tempCargoItems2.getCargoCategory().endsWith("_VC")
						|| tempCargoItems2.getCargoCategory().equalsIgnoreCase(
								"VC")
						|| tempCargoItems2.getCargoCategory().endsWith("_XEP")
						|| tempCargoItems2.getCargoCategory().equalsIgnoreCase(
								"XEP") || tempCargoItems2.getCargoCategory()
						.equalsIgnoreCase("C5"))) {

					VmaScheduleCargolist details = tempCargoItems2;

					try {
						String tmp = "";
						String tmpVC = "";

						if (Validator.isNotNull(details.getCargoType())
								&& Validator.isNotNull(details.getUnit())) {
							tmp = DmDataItemLocalServiceUtil
									.findByDataGroupIdAndCode0(
											Constants.DM_NHOM_HANG_HOA,
											details.getCargoType()).getName()
									+ ", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_TEN_HANG_HOA,
													details.getCargoCode())
											.getName()
									+ "  "
									+ details.getDescription()
									+ "  "
									+ details.getQuantity()
									+ " "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_DON_VI_TINH,
													details.getUnit())
											.getName()

									+ " ("
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_NHOM_HANG_HOA,
													details.getCargoType())
											.getDescription()
									+ ", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_TEN_HANG_HOA,
													details.getCargoCode())
											.getDescription()
									+ "  "
									+ details.getQuantity()
									+ " "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_DON_VI_TINH,
													details.getUnit())
											.getDescription() + ") "

									+ " \n ";
							tmpVC = DmDataItemLocalServiceUtil
									.findByDataGroupIdAndCode0(
											Constants.DM_NHOM_HANG_HOA,
											details.getCargoType()).getName()
									+ ", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_TEN_HANG_HOA,
													details.getCargoCode())
											.getName()
									+ "  "
									+ details.getDescription()
									+ "  "
									+ details.getQuantity()
									+ " "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_DON_VI_TINH,
													details.getUnit())
											.getName() + " \n ";
						} else if (Validator.isNotNull(details.getCargoType())
								&& details.getCargoType()
										.equalsIgnoreCase("11")) // NIL-KHONG
																	// CHO HANG
						{
							tmp = DmDataItemLocalServiceUtil
									.findByDataGroupIdAndCode0(
											Constants.DM_NHOM_HANG_HOA,
											details.getCargoType()).getName()
									+ " ("
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_NHOM_HANG_HOA,
													details.getCargoType())
											.getDescription() + ") " + " \n ";
							tmpVC = tmp;
						}

						cargoOnboadDescription.append(tmp);
						cargoOnboadVC.append(tmpVC);

					} catch (Exception e) {
						log.error(e.getMessage());
					}
					;

				}
			}
			Double volumeTransitCargo = 0.00;
			for (VmaScheduleCargolist tempCargoItems2 : vmaScheduleCargolists) {
				if ((tempCargoItems2.getCargoCategory().equalsIgnoreCase("C5"))) {

					VmaScheduleCargolist details = tempCargoItems2;

					try {
						String tmp = "";

						if (Validator.isNotNull(details.getCargoType())
								&& Validator.isNotNull(details.getUnit())) {
							tmp = DmDataItemLocalServiceUtil
									.findByDataGroupIdAndCode0(
											Constants.DM_NHOM_HANG_HOA,
											details.getCargoType()).getName()
									+ ", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_TEN_HANG_HOA,
													details.getCargoCode())
											.getName()
									+ "  "
									+ details.getDescription()
									+ "  "
									+ details.getQuantity()
									+ " "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_DON_VI_TINH,
													details.getUnit())
											.getName()

									+ " ("
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_NHOM_HANG_HOA,
													details.getCargoType())
											.getDescription()
									+ ", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_TEN_HANG_HOA,
													details.getCargoCode())
											.getDescription()
									+ "  "
									+ details.getQuantity()
									+ " "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_DON_VI_TINH,
													details.getUnit())
											.getDescription() + ") "

									+ " \n ";

							volumeTransitCargo += details.getQuantity().doubleValue();
						} else if (Validator.isNotNull(details.getCargoType())
								&& details.getCargoType()
										.equalsIgnoreCase("11")) // NIL-KHONG
																	// CHO HANG
						{
							tmp = DmDataItemLocalServiceUtil
									.findByDataGroupIdAndCode0(
											Constants.DM_NHOM_HANG_HOA,
											details.getCargoType()).getName()
									+ " ("
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													Constants.DM_NHOM_HANG_HOA,
													details.getCargoType())
											.getDescription() + ") " + " \n ";
						}

						cargoTransitDescription.append(tmp);

					} catch (Exception e) {
						log.error(e.getMessage());
					}
					;

				}
			}
			// Loai hang hoa
			String cargoDescription = cargoOnboadDescription.toString();
			String transitCargo = cargoTransitDescription.toString();
			String cargoOnboard = cargoOnboadVC.toString();
			if (transitCargo.length() == 0
					|| transitCargo.toUpperCase().contains("NIL")) {
				transitCargo = "NIL";
			}
			if (volumeTransitCargo == 0) {
				result.put("volumeTransitCargo", transitCargo.toUpperCase()
						.contains("NIL") ? "NIL" : "As per Manifest");
			} else {
				result.put("volumeTransitCargo", "");
			}
			result.put("cargoDescription", cargoDescription.toString());
			result.put("transitCargo", transitCargo.toString());
			result.put("cargoOnboard", cargoOnboard.toString());

			// Cap nhat lai Ngay ky gprc
			if (vmaItinerarySchedule.getTimeOfDeparture() != null) {
				result.put("SignDate", FormatData
						.parseDateToTringDDMMYYY(vmaItinerarySchedule
								.getTimeOfDeparture()));
				result.put("issueDate", FormatData
						.parseDateToTringType3(vmaItinerarySchedule
								.getTimeOfDeparture()));
			}
			
			log.info("=====> " + "vma_PortClearance.jrxml");

			BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
			File file = new File(pathFileTemp + "report_CV/"
					+ maritimeReference + StringPool.UNDERLINE
					+ "vma_PortClearance.jrxml");
			boolean hasDataBoolean = false;
			if (file.exists()) {
				hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
						+ "report_CV/" + maritimeReference
						+ StringPool.UNDERLINE + "vma_PortClearance.jrxml",
						"vma_PortClearance" + StringPool.UNDERLINE + nanoTime
								+ ".pdf", result, pathFileTemp + "report_CV/",
						2);
			} else {
				hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
						+ "vma_PortClearance.jrxml", "vma_PortClearance"
						+ StringPool.UNDERLINE + nanoTime + ".pdf", result,
						pathFileTemp, 2);
			}

			String UrlFile = request.getContextPath() + "/export/"
					+ "vma_PortClearance" + StringPool.UNDERLINE + nanoTime
					+ ".pdf";
			String UrlFileDownLoad = UrlFile.replace(".pdfs", ".pdf");

			if (hasDataBoolean) {
				data.put("url", UrlFileDownLoad);
			} else {
				data.put("url", StringPool.BLANK);
			}
		} else if ("VR-SI VR-SII VR-SI-SII".contains(vmaItinerary.getVrCode().trim())
				|| (("VR-SB-SI VR-SB-SII VR-SB-SI-SII ---"
						.contains(vmaItinerary.getVrCode().trim()))
						&& !vmaItinerary.getVrCode().trim().equalsIgnoreCase("VR-SB") && !vmaItinerary.getVrCode().trim().equalsIgnoreCase("VR")
						&& !(vmaItinerarySchedule
						.getRouteLevelCode().contains("1")))) {
			List<VmaScheduleCargolist> vmaScheduleCargolists = VmaScheduleCargolistLocalServiceUtil
					.findByItineraryNo(vmaItinerarySchedule.getItineraryNo());
			StringBuilder cargoUnloadingDescription = new StringBuilder();
			StringBuilder cargoLoadingDescription = new StringBuilder();
			String cargoLoading_TanTeusUnit = StringPool.BLANK;
			String cargoUnloading_TanTeusUnit = StringPool.BLANK;
			double cargoUnloading_Quantity = 0;
			int cargoUnloading_TeusQuantity = 0;
			double cargoLoading_Quantity = 0;
			int cargoLoading_TeusQuantity = 0;
			for (int i = 0; i < vmaScheduleCargolists.size(); i++) {
				if ("C2_DO C1_DO C3_DO C6_DO".contains(vmaScheduleCargolists
						.get(i).getCargoCategory())) {
					try {
						if (i > 0) {
							cargoUnloadingDescription.append(", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						} else {
							cargoUnloadingDescription
									.append(DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						}
					} catch (Exception e) {
					}
					cargoUnloading_Quantity += vmaScheduleCargolists.get(i)
							.getQuantity().doubleValue();
					cargoUnloading_TeusQuantity += vmaScheduleCargolists.get(i)
							.getTeusQuantity().doubleValue();
					cargoUnloading_TanTeusUnit = vmaScheduleCargolists.get(i)
							.getUnit();
				} else if ("C2_XEP C1_XEP C3_XEP C6_XEP"
						.contains(vmaScheduleCargolists.get(i)
								.getCargoCategory())) {
					try {
						if (i > 0) {
							cargoLoadingDescription.append(", "
									+ DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						} else {
							cargoLoadingDescription
									.append(DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(
													124L,
													vmaScheduleCargolists
															.get(i)
															.getCargoCode())
											.getName());
						}
					} catch (Exception e) {
					}
					cargoLoading_Quantity += vmaScheduleCargolists.get(i)
							.getQuantity().doubleValue();
					cargoLoading_TeusQuantity += vmaScheduleCargolists.get(i)
							.getTeusQuantity().doubleValue();
					cargoLoading_TanTeusUnit = vmaScheduleCargolists.get(i)
							.getUnit();
				}
			}
			result.put(
					"cargoLoading_Quantity",
					(cargoLoading_Quantity > 0) ? cargoLoading_Quantity + ""
							: (cargoLoading_TeusQuantity > 0) ? cargoLoading_TeusQuantity
									+ ""
									: "NIL");
			result.put(
					"cargoLoading_TanTeusQuantity",
					(cargoLoading_Quantity > 0) ? (cargoLoading_TanTeusUnit
							.equalsIgnoreCase("TNE") ? donviTan
							: cargoLoading_TanTeusUnit.equalsIgnoreCase("TEU") ? DanhMucUtils
									.encodeUTF8("Teu") : "")
							: (cargoLoading_TeusQuantity > 0) ? DanhMucUtils
									.encodeUTF8("Teu") : "");
			result.put(
					"cargoUnloading_Quantity",
					(cargoUnloading_Quantity > 0) ? cargoUnloading_Quantity
							+ ""
							: (cargoUnloading_TeusQuantity > 0) ? cargoUnloading_TeusQuantity
									+ ""
									: "NIL");
			result.put(
					"cargoUnloading_TanTeusQuantity",
					(cargoUnloading_Quantity > 0) ? (cargoUnloading_TanTeusUnit
							.equalsIgnoreCase("TNE") ? donviTan
							: cargoUnloading_TanTeusUnit
									.equalsIgnoreCase("TEU") ? DanhMucUtils
									.encodeUTF8("Teu") : "")
							: (cargoUnloading_TeusQuantity > 0) ? DanhMucUtils
									.encodeUTF8("Teu") : "");
			result.put("cargoUnloadingDescription", (cargoUnloadingDescription
					.length() > 0) ? cargoUnloadingDescription.toString()
					: "NIL");
			result.put("cargoLoadingDescription", (cargoLoadingDescription
					.length() > 0) ? cargoLoadingDescription.toString() : "NIL");


			BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
			File file = new File(pathFileTemp + "report_CV/"
					+ maritimeReference + StringPool.UNDERLINE
					+ "vma_Pttnd_PortClearance.jrxml");
			boolean hasDataBoolean = false;
			if (file.exists()) {
				hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
						+ "report_CV/" + maritimeReference
						+ StringPool.UNDERLINE
						+ "vma_Pttnd_PortClearance.jrxml",
						"vma_Pttnd_PortClearance" + StringPool.UNDERLINE
								+ nanoTime + ".pdf", result, pathFileTemp
								+ "report_CV/", 2);
			} else {
				hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
						+ "vma_Pttnd_PortClearance.jrxml",
						"vma_Pttnd_PortClearance" + StringPool.UNDERLINE
								+ nanoTime + ".pdf", result, pathFileTemp, 2);
			}

			String UrlFile = request.getContextPath() + "/export/"
					+ "vma_Pttnd_PortClearance" + StringPool.UNDERLINE
					+ nanoTime + ".pdf";
			String UrlFileDownLoad = UrlFile.replace(".pdfs", ".pdf");

			if (hasDataBoolean) {
				data.put("url", UrlFileDownLoad);
			} else {
				data.put("url", StringPool.BLANK);
			}
		}  else {
			log.info("ERR: Thieu phan cap tau.");
		}
		return data;
	}

	public JSONObject exportKHDD(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest) throws FileNotFoundException,
			IOException, SystemException, ParsePropertyException,
			InvalidFormatException {
		HttpServletRequest request = resourceRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(request, "timeOfArrival",
				StringPool.BLANK);
		String maritimeCode = ParamUtil.getString(request, "maritimeCode",
				StringPool.BLANK);
		String shipPosition = ParamUtil.getString(request, "shipPosition",
				StringPool.BLANK);
		String markedAsArrival = StringPool.BLANK;
		String markedAsDeparture = StringPool.BLANK;
		String noticeShipType = ParamUtil.getString(request, "noticeShipType",
				StringPool.BLANK);
		String requestState = StringPool.BLANK;
		markedAsArrival = ParamUtil.getString(request, "markedAsArrival",
				StringPool.BLANK);
		markedAsDeparture = ParamUtil.getString(request, "markedAsDeparture",
				StringPool.BLANK);
		requestState = ParamUtil.getString(request, "requestState",
				StringPool.BLANK);
		String shipBoat = ParamUtil.getString(request, "shipBoat",
				StringPool.BLANK);
		String portRegionCode = ParamUtil.getString(request, "portRegionCode",
				StringPool.BLANK);
		String certificateNo = ParamUtil.getString(request, "certificateNo",
				StringPool.BLANK);
		String fromDate = ParamUtil.getString(request, "fromDate",
				StringPool.BLANK);
		String toDate = ParamUtil
				.getString(request, "toDate", StringPool.BLANK);
		String exportType = ParamUtil.getString(request, "exportType",
				StringPool.BLANK);
		
		
		JSONArray array = VmaItineraryScheduleLocalServiceUtil
				.findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting(
						itineraryNo, maritimeCode, shipPosition,
						markedAsArrival, markedAsDeparture, noticeShipType,
						shipBoat, timeOfArrival, portRegionCode, certificateNo,
						requestState, true, fromDate, toDate,
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		HashMap<String, String> portRegionCodes = new HashMap<String, String>();
		List<DmPortRegion> lstPortRegion = DmPortRegionLocalServiceUtil.findPortRegionByPortRegionRef(maritimeCode);
		for (DmPortRegion dm_port_region: lstPortRegion) {
			portRegionCodes.put(dm_port_region.getPortRegionCode(), dm_port_region.getPortRegionNameVN());
		}
		
		try {
			long nanoTime = System.nanoTime();
			BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
			JSONObject object = JSONFactoryUtil.createJSONObject();
			object.put("Bang1", array);
			Calendar fromCal = Calendar.getInstance();
			fromCal.setTime(new Date());
			int fromYear = fromCal.get(Calendar.YEAR);
			int fromMonth = fromCal.get(Calendar.MONTH) + 1;
			int fromDay = fromCal.get(Calendar.DAY_OF_MONTH);
			object.put("reportMonth", fromMonth);
			object.put("reportYear", fromYear);
			object.put("maritimeNameVN", DmMaritimeLocalServiceUtil
					.getByMaritimeCode(maritimeCode).getMaritimeNameVN());
			object.put("signPlace",
					DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode)
							.getCityCode());
			object.put("SignDate",
					FormatData.parseDateToTringDDMMYYY(new Date()));
			object.put("SignTitle", DanhMucUtils.encodeUTF8("GIÁM ĐỐC"));

			object.put("maritimeName", DmMaritimeLocalServiceUtil
					.getByMaritimeCode(maritimeCode).getMaritimeName());
			object.put("documentTypeCode", StringPool.BLANK);
			object.put("portofAuthority", maritimeCode);
			boolean hasDataBoolean = false;
			if (exportType.contains("PDF")) {
				hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
						+ "vma_schedule_shifting_order_dailyexport.jrxml", "KeHoachDieuDong"+ StringPool.UNDERLINE+nanoTime
						+ StringPool.UNDERLINE + fromDay + StringPool.UNDERLINE + fromMonth + ".pdf", object,
						pathFileTemp, 2);
			} else {
				hasDataBoolean = action.exportPDF_EXCEL_VMA(pathFileTemp
						+ "vma_schedule_shifting_order_dailyexport.jrxml", "KeHoachDieuDong"+ StringPool.UNDERLINE+nanoTime
								+ StringPool.UNDERLINE + fromDay + StringPool.UNDERLINE + fromMonth + ".xls", object,
						pathFileTemp, 1);
			}

			String UrlFile = request.getContextPath() + "/export/"
					+ "KeHoachDieuDong" + StringPool.UNDERLINE + nanoTime
					+ StringPool.UNDERLINE + fromDay + StringPool.UNDERLINE + fromMonth + (exportType.contains("PDF") ? ".pdf" : ".xls") ;
			String UrlFileDownLoad = UrlFile.replace(".pdfs", ".pdf");

			if (hasDataBoolean) {
				result.put("urlDownload", UrlFileDownLoad);
			} else {
				result.put("urlDownload", StringPool.BLANK);
			}
					
		} catch (Exception e) {
		}
				
				
		return result;
	}


	public JSONObject exportKHDD_cu(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest) throws FileNotFoundException,
			IOException, SystemException, ParsePropertyException,
			InvalidFormatException {
		HttpServletRequest request = resourceRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(request, "timeOfArrival",
				StringPool.BLANK);
		String maritimeCode = ParamUtil.getString(request, "maritimeCode",
				StringPool.BLANK);
		String shipPosition = ParamUtil.getString(request, "shipPosition",
				StringPool.BLANK);
		String markedAsArrival = StringPool.BLANK;
		String markedAsDeparture = StringPool.BLANK;
		String noticeShipType = ParamUtil.getString(request, "noticeShipType",
				StringPool.BLANK);
		String requestState = StringPool.BLANK;
		markedAsArrival = ParamUtil.getString(request, "markedAsArrival",
				StringPool.BLANK);
		markedAsDeparture = ParamUtil.getString(request, "markedAsDeparture",
				StringPool.BLANK);
		requestState = ParamUtil.getString(request, "requestState",
				StringPool.BLANK);
		String shipBoat = ParamUtil.getString(request, "shipBoat",
				StringPool.BLANK);
		String portRegionCode = ParamUtil.getString(request, "portRegionCode",
				StringPool.BLANK);
		String certificateNo = ParamUtil.getString(request, "certificateNo",
				StringPool.BLANK);
		String fromDate = ParamUtil.getString(request, "fromDate",
				StringPool.BLANK);
		String toDate = ParamUtil
				.getString(request, "toDate", StringPool.BLANK);

		
		
		JSONArray array = VmaItineraryScheduleLocalServiceUtil
				.findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting(
						itineraryNo, maritimeCode, shipPosition,
						markedAsArrival, markedAsDeparture, noticeShipType,
						shipBoat, timeOfArrival, portRegionCode, certificateNo,
						requestState, true, fromDate, toDate,
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		HashMap<String, String> portRegionCodes = new HashMap<String, String>();
		List<DmPortRegion> lstPortRegion = DmPortRegionLocalServiceUtil.findPortRegionByPortRegionRef(maritimeCode);
		for (DmPortRegion dm_port_region: lstPortRegion) {
			portRegionCodes.put(dm_port_region.getPortRegionCode(), dm_port_region.getPortRegionNameVN());
		}
		// SonVH comment 28.05.2020
		/*for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			if (obj.has("portRegionCode")) {
				if (!obj.getString("portRegionCode").equals(StringPool.BLANK)) {
					portRegionCodes.put(obj.getString("portRegionCode"),
							obj.getString("portRegionName"));
				}
			}
		}*/

		String[] cellFields = new String[] { "timeOfArrival", "nameOfShip",
				"maxDraft", "loa", "dwt", "tugboatList", "initFrom",
				"portHarbourName", "pilotList", "shipAgencyName",
				"maritimeRemarks" };

		HSSFWorkbook workbook = new HSSFWorkbook();
		if (workbook != null) {
			HSSFSheet sheet = workbook.createSheet("Kế hoạch điều động tàu thuyền");
			if (sheet != null) {
				HSSFRow header = sheet.createRow(0);
				HSSFCell cellHeader = header.createCell(5);
				cellHeader.setCellValue("Kế hoạch điều động tàu ngày "+fromDate);
				int rowNumber = 2, cellNumber = 0;
				int count = 1;
				for (String _portRegionCode : portRegionCodes.keySet()) {
					HSSFRow row = sheet.createRow(rowNumber);
					HSSFCell cell = row.createCell(cellNumber);
					cell.setCellValue("${A" + count + ".portRegionName}");
					rowNumber++;
					HSSFRow rowField_1 = sheet.createRow(rowNumber);
					for (int i = 0; i < cellFields.length; i++) {
						HSSFCell cellField = rowField_1.createCell(i);
						cellField.setCellValue(getHeader(i));
					}
					rowNumber++;
					HSSFRow rowField_2 = sheet.createRow(rowNumber);
					for (int i = 0; i < cellFields.length; i++) {
						HSSFCell cellField = rowField_2.createCell(i);
						cellField.setCellValue("${A" + count + "."
								+ cellFields[i] + "}");
					}
					rowNumber++;
					count++;
				}

				workbook.write(new FileOutputStream(new File(pathFileTemp
						+ "KeHoachDieuDongTheoNgayTemplate.xls")));

				Map dataBeans = new HashMap();

				count = 1;
				for (String _portRegionCode : portRegionCodes.keySet()) {
					List<Map> lstCellData = new ArrayList<Map>();
					for (int i = 0; i < array.length(); i++) {
						JSONObject obj = array.getJSONObject(i);
						Map cellData = new HashMap();
						if (obj.has("portRegionCode")) {
							String temp = obj.getString("portRegionCode");
							if (!temp.equals(StringPool.BLANK)
									& temp.equals(_portRegionCode)) {
								for (int j = 0; j < cellFields.length; j++) {
									String value = StringPool.BLANK;
									int _noticeShipType = obj
											.getInt("noticeShipType");
									if (cellFields[j].equals("timeOfArrival")) {
										String _timeOfArrival = obj
												.getString("timeOfArrival");
										String _timeOfDeparture = obj
												.getString("timeOfDeparture");
										String _timeOfApproval = obj
												.getString("timeOfApproval");
										if (!_timeOfArrival
												.equals(StringPool.BLANK)
												&& _timeOfArrival != null) {
											value = _timeOfArrival;
										} else if (!_timeOfDeparture
												.equals(StringPool.BLANK)
												&& _timeOfDeparture != null) {
											value = _timeOfDeparture;
										} else {
											value = _timeOfApproval;
										}
									} else {
										value = obj.getString(cellFields[j]);
									}
									if (_noticeShipType == 2) {
										if (cellFields[j].equals("initFrom")) {
											String _portHarbourName = obj
													.getString("portHarbourName");
											String _portWharfName = obj
													.getString("portWharfName");
											if (!_portHarbourName
													.equals(StringPool.BLANK)
													&& _portHarbourName != null) {
												value = _portHarbourName;
												if (!_portWharfName
														.equals(StringPool.BLANK)
														&& _portWharfName != null) {
													value += ", "
															+ _portWharfName;
												}
											} else if (!_portWharfName
													.equals(StringPool.BLANK)
													&& _portWharfName != null) {
												value = _portWharfName;
											}
										} else if (cellFields[j]
												.equals("portHarbourName")) {
											value = obj.getString("initFrom");
										}
									} else {
										if (cellFields[j].equals("initFrom")) {
											value = obj.getString("initFrom");
										} else if (cellFields[j]
												.equals("portHarbourName")) {
											String _portHarbourName = obj
													.getString("portHarbourName");
											String _portWharfName = obj
													.getString("portWharfName");
											if (!_portHarbourName
													.equals(StringPool.BLANK)
													&& _portHarbourName != null) {
												value = _portHarbourName;
												if (!_portWharfName
														.equals(StringPool.BLANK)
														&& _portWharfName != null) {
													value += ", "
															+ _portWharfName;
												}
											} else if (!_portWharfName
													.equals(StringPool.BLANK)
													&& _portWharfName != null) {
												value = _portWharfName;
											}
										}
									}
									if (value == null || value.equals("null")) {
										if (cellFields[j].equals("initFrom")
												|| cellFields[j]
														.equals("portHarbourName")) {
											value = "P/S";
										}
										value = StringPool.BLANK;
									}
									cellData.put(cellFields[j], value);
								}
								lstCellData.add(cellData);
							}
						}
					}
					Map portRegionName = new HashMap();
					portRegionName.put("portRegionName", String
							.valueOf(portRegionCodes.get(_portRegionCode)));
					lstCellData.add(portRegionName);
					dataBeans.put("A" + count, lstCellData);
					count++;
				}
				dataBeans.put("date", timeOfArrival);

				String urlDownload = request.getContextPath() + "/export/"
						+ "KeHoachDieuDong.xls";
				XLSTransformer transformer = new XLSTransformer();

				transformer.transformXLS(pathFileTemp
						+ "KeHoachDieuDongTheoNgayTemplate.xls", dataBeans,
						pathFileSub + "KeHoachDieuDong.xls");

				result.put("urlDownload", urlDownload);
			}
		}
		return result;
	}

	private String getHeader(int cellNumber) {
		switch (cellNumber) {
		case 0:
			return "Thời gian";
		case 1:
			return "Tên tàu";
		case 2:
			return "Mớn nước";
		case 3:
			return "Chiều dài";
		case 4:
			return "Trọng tải";
		case 5:
			return "Tàu lai";
		case 6:
			return "Từ";
		case 7:
			return "Đến";
		case 8:
			return "Hoa tiêu";
		case 9:
			return "Đại lý";
		case 10:
			return "Ghi chú";

		default:
			return StringPool.BLANK;
		}
	}

	private String realPath = this.getClass().getProtectionDomain()
			.getCodeSource().getLocation().toString();
	private String pathFileTemp = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/report/baocao/";
	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/export/";
}
