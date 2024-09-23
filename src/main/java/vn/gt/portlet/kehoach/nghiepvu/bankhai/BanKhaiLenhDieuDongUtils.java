package vn.gt.portlet.kehoach.nghiepvu.bankhai;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.core.ServiceContext;

import com.fds.nsw.liferay.core.ThemeDisplay;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.service.DLFileEntryLocalServiceUtil;

import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.InterfaceRequest;

import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempDocument;

import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultNotification;

import vn.gt.dao.result.service.ResultNotificationLocalServiceUtil;
import vn.gt.portlet.kehoach.tt1.TT1JSONProvider;
import vn.gt.portlet.kehoach.tt1.TT1TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt1.TT1XuLyNghiepVuUtils;
import vn.gt.portlet.kehoach.tt10.TT10TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt16.TT16TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt3.TT3TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt4.TT4TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt6.TT6TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt8.TT8TichHopMessageUtils;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.portlet.kehoach.utils.FileUploadUtil;
import vn.gt.portlet.phuongtien.VmaItineraryUtils;
import vn.gt.portlet.thutuc.SignatureUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiBanKhai;
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;
import vn.gt.tichhop.report.FileUploadUtils;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BanKhaiLenhDieuDongUtils {


	private String realPath = this.getClass().getProtectionDomain()
			.getCodeSource().getLocation().toString();

	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/export/";



	private static String checkDisplayIcon(int state) {
		String result = "";
		if (state == TrangThaiBanKhaiNhapCanh.CHAP_NHAN_BAN_KHAI
				|| state == TrangThaiBanKhaiNhapCanh.SUA_DOI_BO_XUNG) {
			result = "icon_tich.png";
		} else if (state == TrangThaiBanKhaiNhapCanh.KHAI_HUY) {
			result = "close.png";
		}
		return result;
	}

	public static JSONObject buildThanhPhan(ThemeDisplay themeDisplay,
			long documentName, int documentYear) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<IssueShiftingOrder> results = new ArrayList<IssueShiftingOrder>(
				IssueShiftingOrderLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(
								documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC));

		result.put("code", "lenh_dieu_dong");
		result.put("name",
				LanguageUtil.get(themeDisplay.getLocale(), "lenh-dieu-dong"));
		boolean available = false;
		String requestCode = StringPool.BLANK;
		String icon = StringPool.BLANK;
		if (Validator.isNotNull(results) && results.size() > 0) {
			available = true;
			requestCode = results.get(0).getRequestCode();
			icon = checkDisplayIcon(results.get(0).getRequestState());
		}
		result.put("available", available);
		result.put("documentName", documentName);
		result.put("documentYear", documentYear);
		result.put("messageType", MessageType.LENH_DIEU_DONG);
		result.put("requestCode", requestCode);

		result.put("state", -1);
		if (icon.equals("icon_tich.png")) {
			result.put("state", 1);
		} else if (icon.equals("close.png")) {
			result.put("state", 0);
		}

		return result;
	}

	public static JSONArray getFileThanhPhanVersionList(
			ThemeDisplay themeDisplay, long documentName, int documentYear,
			int messageType) {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		JSONObject object;

		InterfaceRequest interfaceRequest = null;
		TempDocument tempDocument = TempDocumentLocalServiceUtil
				.findTemDocumentByDocumentNameDocumentYear(documentName,
						documentYear);
		List<IssueShiftingOrder> results = IssueShiftingOrderLocalServiceUtil
				.findByDocumentYearAndDocumentYearOrderByColumn(documentName,
						documentYear, KeyParams.ID, KeyParams.ORDER_BY_DESC);

		for (int i = 0; i < results.size(); i++) {
			interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(results.get(i).getRequestCode());

			if (interfaceRequest == null) {
				interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(tempDocument.getRequestCode());
				interfaceRequest.setRemarks("");
			}
			object = JSONFactoryUtil.createJSONObject();
			if (Validator.isNotNull(interfaceRequest)) {

				String phienBan = results.get(i).getVersionNo()
						+ " - "
						+ DmGtStatusLocalServiceUtil.findNameByStatusCode(
								results.get(i).getRequestState(), 3);
				object.put("version", phienBan);
				object.put("status", DmGtStatusLocalServiceUtil
						.findNameByStatusCode(results.get(i).getRequestState(),
								messageType));
				object.put("sendDate", interfaceRequest.getRequestedDate());

				object.put("requestCode", results.get(i).getRequestCode());

				object.put("remarks", results.get(i).getRemarks());
				result.put(object);
			}

		}
		return result;
	}

	public int _lenhDieuDong(String requestCode, long documentName, int documentYear, int messageType, int desStatus,
			User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {
		//Add by TrungNT
		int markedAsArrival = 0;
		int markedAsDeparture = 0;
		int markedAsTransmit = 0;
		int markedAsVoyage =  0;
		
		int result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);

			long groupId = themeDisplay.getScopeGroupId();

			ServiceContext serviceContext = new ServiceContext();

			List<IssueShiftingOrder> results = new ArrayList<IssueShiftingOrder>(
					IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(documentName,
							documentYear, KeyParams.ID, KeyParams.ORDER_BY_DESC));

			if (results.size() > 0) {
				requestCode = results.get(0).getRequestCode();
			}

			TempDocument document = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName,
					documentYear);

			String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");
			int actionType = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.ACTION_TYPE);

			BusinessUtils businessUtils = new BusinessUtils();

			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {

				businessUtils.lenhDieuDongUpgrade(uploadPortletRequest, document.getDocumentName(),
						document.getDocumentYear(), user.getEmailAddress(), requestCode, document.getRequestState());
				
				InterfaceRequest interfaceShifOrder = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);

				if (interfaceShifOrder != null) {
					String remarkCapLai = BusinessUtils.getRemarkCapLai(user.getEmailAddress(), yKienReject,
							new Date());

					interfaceShifOrder.setRemarks(remarkCapLai);
					InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceShifOrder);
					
					
				}

				if (document.getRequestState() == ChuyenDichTrangThaiUtils.KE_HOACH_CHO_SUA_LENH_DIEU_DONG) {
					ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear,
							ChuyenDichTrangThaiUtils.KE_HOACH_CHO_CAP_LENH_DIEU_DONG, Boolean.TRUE);
				}
				
				List<IssueShiftingOrder> lstIssueShiftingOrder = new ArrayList<IssueShiftingOrder>(
						IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(documentName,
								documentYear, KeyParams.ID, KeyParams.ORDER_BY_DESC));
				
				if (Validator.isNotNull(lstIssueShiftingOrder) && lstIssueShiftingOrder.size() > 0) {
					//Add by TrungNT
					markedAsArrival = ChuyenDichTrangThaiUtils.LAP_KE_HOACH_DIEU_DONG;
				}
				
			} else if (actionType == PageType.ACTION_TYPE_HUY) {

				document.setRequestState(TrangThaiBanKhaiNhapCanh.DA_HUY_LENH_DIEU_DONG);
				document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);

				TempDocumentLocalServiceUtil.updateTempDocument(document);

				List<IssueShiftingOrder> lstShiftOrder = IssueShiftingOrderLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(document.getDocumentName(),
								document.getDocumentYear(), KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);

				for (IssueShiftingOrder item : lstShiftOrder) {

					item.setIsCancel(1);
					item.setCancelDate(new Date());
					item.setCancelName(user.getEmailAddress());
					item.setCancelNote(yKienReject);

					item.setRemarks(yKienReject);
					
					item.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_HUY);
					IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(item);
				}
				
				//add by TrungNT
				markedAsArrival = ChuyenDichTrangThaiUtils.DANH_DAU_DA_HUY;

				insertOrUpdateResultNotification(documentName, documentYear, user.getEmailAddress(), yKienReject,
						messageType);

				// Tich hop send message 71-03
				if (document.getDocumentTypeCode().equalsIgnoreCase("NC")) {
					TT1TichHopMessageUtils.message_71_03(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode,
							0);
				} else if (document.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					TT16TichHopMessageUtils.message_71_03(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode,
							0);
				} else if (document.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					TT6TichHopMessageUtils.message_71_03(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode,
							0);
				} else if (document.getDocumentTypeCode()
						.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					TT8TichHopMessageUtils.message_71_03(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode,
							0);
				} else if (document.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					TT10TichHopMessageUtils.message_71_03(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode,
							0);
				} else if (document.getDocumentTypeCode()
						.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					TT4TichHopMessageUtils.message_71_03(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode,
							0);
				} else if (document.getDocumentTypeCode()
						.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					TT3TichHopMessageUtils.message_71_03(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode,
							0);
				}

			} else if (actionType == PageType.ACTION_TYPE_CAP_LAI_LENH_DIEU_DONG) {

				List<IssueShiftingOrder> listIssueShiftingOrder = IssueShiftingOrderLocalServiceUtil
						.findByRequestCode(requestCode);

				if (Validator.isNotNull(listIssueShiftingOrder) && listIssueShiftingOrder.size() > 0) {
					IssueShiftingOrder shiftOrder = listIssueShiftingOrder.get(0);
					shiftOrder.setReasonToShift(yKienReject);

					shiftOrder.setRemarks(yKienReject);
					
//					shiftOrder.setAttachedFile(0L);
					shiftOrder = IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
					//Add by TrungNT
					markedAsArrival = ChuyenDichTrangThaiUtils.LAP_KE_HOACH_DIEU_DONG;
				}

				ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear,
						ChuyenDichTrangThaiUtils.KE_HOACH_CHO_SUA_LENH_DIEU_DONG, Boolean.TRUE);

			} else if (actionType == PageType.ACTION_TYPE_DUYET) {

				IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCode);

				// ----------duyenMotLan---------
				document.setRequestState(TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG);
				document.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
				document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.THU_TUC_DA_TIEP_NHAN);

				if (shiftOrder != null) {
					document.setShipDateFrom(shiftOrder.getShiftingDate());
				}
				TempDocumentLocalServiceUtil.updateTempDocument(document);

				if (shiftOrder != null) {
					// TODO Nhap Canh truong hop duyet
					shiftOrder.setIsApproval(PageType.DUYET_PHE_CHUAN);
					shiftOrder.setApprovalDate(new Date());
					shiftOrder.setApprovalName(user.getEmailAddress());

					try {

						long fileEntryId = shiftOrder.getAttachedFile();
						// save file to shifting order
						long nanoTime = ReportsBusinessUtils.actionExport(requestCode, (int) documentName, documentYear,
								messageType, document.getDocumentTypeCode());

						String tenFileExport = nanoTime + "_" + ReportConstant.SHIFTING_ORDER_EXPORT;
						File file = new File(pathFileSub + tenFileExport);
						DLFileEntry fileEntry = FileUploadUtils.uploadFile(user.getUserId(), groupId, fileEntryId, file,
								pathFileSub + tenFileExport, null, null, serviceContext);
						shiftOrder.setAttachedFile(fileEntry.getFileEntryId());

					} catch (Exception e) {
						log.error(e.getMessage());
					}

					shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.CHAP_NHAN_BAN_KHAI);

					IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
					
					//Add by TrungNT
					markedAsArrival = ChuyenDichTrangThaiUtils.DANH_DAU_DA_DUYET;

				}

				// XU LY Gui lenh dieu dong
				if (document.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG
						&& shiftOrder != null) {

					// Tich hop send message 70-01
					if (document.getDocumentTypeCode().equalsIgnoreCase("NC")) {
						TT1TichHopMessageUtils.message_70_01(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode, 0, shiftOrder.getVersionNo());
					} else if (document.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
						TT16TichHopMessageUtils.message_70_01(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode, 0, shiftOrder.getVersionNo());
					} else if (document.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
						TT6TichHopMessageUtils.message_70_01(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode, 0, shiftOrder.getVersionNo());
					} else if (document.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
						TT8TichHopMessageUtils.message_70_01(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode, 0, shiftOrder.getVersionNo());
					} else if (document.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
						TT10TichHopMessageUtils.message_70_01(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode, 0, shiftOrder.getVersionNo());
					} else if (document.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
						TT4TichHopMessageUtils.message_70_01(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode, 0, shiftOrder.getVersionNo());
					} else if (document.getDocumentTypeCode()
							.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
						TT3TichHopMessageUtils.message_70_01(user.getEmailAddress(),
								uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
								requestCode, 0, shiftOrder.getVersionNo());
					}

				} else {
					log.info("==KHONG_gui_lenh_dieu_dong=");
				}
				
				
				
			} else if (actionType == PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET) {

				IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCode);

				if ((shiftOrder != null) && (shiftOrder.getStampStatus() == 0)) {

					document.setRequestState(ChuyenDichTrangThaiUtils.KE_HOACH_CHO_CAP_LENH_DIEU_DONG);

					TempDocumentLocalServiceUtil.updateTempDocument(document);

					String representative = shiftOrder.getRepresentative();
					String portofAuthority = shiftOrder.getPortofAuthority();
					String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
					shiftOrder.setSignTitle(signTitle);
					
					// TODO Nhap Canh truong hop cho duyet
					shiftOrder.setStampStatus(PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET);
					shiftOrder.setIsApproval(PageType.KHONG_PHE_CHUAN);
					IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
					
					try {

						long fileEntryId = shiftOrder.getAttachedFile();

						// save file to shifting order
						long nanoTime = ReportsBusinessUtils.actionExport(requestCode, (int) documentName, documentYear,
								messageType, document.getDocumentTypeCode());

						String tenFileExport = nanoTime + "_" + ReportConstant.SHIFTING_ORDER_EXPORT;
						File file = new File(pathFileSub + tenFileExport);
						DLFileEntry fileEntry = FileUploadUtils.uploadFile(user.getUserId(), groupId, fileEntryId, file,
								pathFileSub + tenFileExport, null, null, serviceContext);

						shiftOrder.setAttachedFile(fileEntry.getFileEntryId());

					} catch (Exception e) {
						log.error(e.getMessage());
					}
					IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
					log.info("==Chuyen_lanh_dao_duyet_ky_lenh_dieu_dong=");
					
					//Add by TrungNT
					markedAsArrival = ChuyenDichTrangThaiUtils.DANH_DAU_CHO_LANH_DAO_KY_BCY;
				}

				
				
			} else if (actionType == PageType.ACTION_TYPE_CHUYEN_TRA_HO_SO) {
				log.info("=====Tra ve buoc truoc==ACTION_TYPE_CHUYEN_TRA_HO_SO");

				IssueShiftingOrder shiftOrderLanhDaoTraLai = IssueShiftingOrderLocalServiceUtil
						.getByRequestCode(requestCode);

				if (shiftOrderLanhDaoTraLai != null) {
					// TODO Nhap Canh truong hop cho duyet
					shiftOrderLanhDaoTraLai.setStampStatus(PageType.KHONG_PHE_CHUAN);
					shiftOrderLanhDaoTraLai.setIsApproval(PageType.KHONG_PHE_CHUAN);
					shiftOrderLanhDaoTraLai.setIsCancel(PageType.KHONG_PHE_CHUAN);
					shiftOrderLanhDaoTraLai.setCancelNote(yKienReject);
					shiftOrderLanhDaoTraLai.setRemarks(yKienReject);
					shiftOrderLanhDaoTraLai.setAttachedFile(0L);
					shiftOrderLanhDaoTraLai.setCancelDate(new Date());
					IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrderLanhDaoTraLai);
					log.info("==Tra ve buoc truoc, sua noi dung lenh dieu dong==");

					BusinessUtils.updateLyDoResultNotification(user.getEmailAddress(), yKienReject,
							MessageType.Y_CAU_TRA_LAI_HO_SO_LENH_DIEU_DONG, document.getMaritimeCode(),
							document.getDocumentName(), document.getDocumentYear());

					// reject change status to KE_HOACH_CHO_SUA_LENH_DIEU_DONG
					TempDocument tempDocument = TempDocumentLocalServiceUtil
							.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

					tempDocument.setRequestState(ChuyenDichTrangThaiUtils.KE_HOACH_CHO_SUA_LENH_DIEU_DONG);
					TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					
					InterfaceRequest interfaceRequestShiftOrder = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);

					if (interfaceRequestShiftOrder != null) {
						String remarkCapLai = BusinessUtils.getRemarkCapLai(user.getEmailAddress(), yKienReject,
								new Date());

						interfaceRequestShiftOrder.setRemarks(remarkCapLai);
						InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequestShiftOrder);
					}
					//Add by TrungNT
					markedAsArrival = ChuyenDichTrangThaiUtils.DANH_DAU_LANH_DAO_TRA_LAI;

				}
				
				
			} else if (actionType == PageType.ACTION_TYPE_KYSO) {

				IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCode);

				long fileEntryId = FileUploadUtil.kySoFile(uploadPortletRequest, shiftOrder.getAttachedFile(), "KY_SO", serviceContext);

				document.setRequestState(TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG);
				
				TempDocumentLocalServiceUtil.updateTempDocument(document);

				if (shiftOrder != null) { // TODO Nhap Canh truong hop duyet
					shiftOrder.setApprovalDate(new Date());
					shiftOrder.setApprovalName(user.getEmailAddress());
					shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_MOI);
					shiftOrder.setStampStatus(1);
					String signLocation = ParamUtil.getString(uploadPortletRequest, "signLocation");
					
					shiftOrder.setSignPlace(signLocation);
					
					String representative = shiftOrder.getRepresentative();
					String portofAuthority = shiftOrder.getPortofAuthority();
					String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
					shiftOrder.setSignTitle(signTitle);
					shiftOrder.setSignName(ParamUtil.getString(uploadPortletRequest, "signName"));
					shiftOrder.setSignDate(
							ParamUtil.getDate(uploadPortletRequest, "signDate", FormatData.formatDateShort3));

					shiftOrder.setAttachedFile(fileEntryId);

					IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
					
					//Add by TrungNT
					markedAsArrival = ChuyenDichTrangThaiUtils.DANH_DAU_CHO_DONG_DAU_BCY;
					
					// TODO: Check neu su dung ky so hsm 
					boolean finish = ParamUtil.getBoolean(uploadPortletRequest, "finish");
					if(finish) {
						String dongDauSigned = SignatureUtil.completeDongDauHSM(fileEntryId, themeDisplay.getUserId(), messageType);
						
						DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
								.getDLFileEntry(fileEntryId);
						
						InputStream inputStream = new FileInputStream(dongDauSigned);
						
						DLFileEntry fileEntry = FileUploadUtil.updateFile(dlFileEntry.getUserId(),
								dlFileEntry.getCompanyId(), dlFileEntry.getGroupId(), fileEntryId, inputStream,
								dongDauSigned, dlFileEntry.getMimeType(), inputStream.available(), StringPool.BLANK, "",
								serviceContext);
						
						markedAsArrival = dongDau(requestCode, documentName, documentYear, messageType, desStatus, user,
								uploadPortletRequest, actionRequest, shiftOrder, document, fileEntry.getFileEntryId());
					}
				}

			} else if (actionType == PageType.ACTION_TYPE_DONGDAU) {
				IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCode);
				
				long fileEntryId = FileUploadUtil.kySoFile(uploadPortletRequest, shiftOrder.getAttachedFile(), "DONG_DAU", serviceContext);
			
				markedAsArrival = dongDau(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest, shiftOrder, document, fileEntryId);
			}
			result = TT1XuLyNghiepVuUtils.XU_LY_THANH_CONG;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;
		}
		
		//check active
		boolean active = VmaItineraryUtils.checkActiveVma(user.getUserId());
		
		if(active){
			log.info("BanKhaiLenhDieuDongUtils >>>>>>>> "
					+ documentName + "|" + documentYear + "|" + markedAsArrival
					+ "|" + markedAsDeparture + "|" + markedAsTransmit + "|"
					+ markedAsVoyage);
			VmaItineraryLocalServiceUtil.updateVmaItinerary(documentName, documentYear,
					markedAsArrival > 0 ? markedAsArrival : null, markedAsDeparture > 0 ? markedAsDeparture : null,
					markedAsTransmit > 0 ? markedAsTransmit : null, markedAsVoyage > 0 ? markedAsVoyage : null);
		}
		
		
		return result;
	}

	public static void insertOrUpdateResultNotification(long documentName,
			int documentYear, String userName, String yKienReject,
			int messageType) {
		try {

			TempDocument document = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);

			// MessageType.HUY_LENH_DIEU_DONG
			ResultNotification reNotification = ResultNotificationLocalServiceUtil
					.findByBusinessTypeCode(messageType,
							document.getDocumentName(),
							document.getDocumentYear());

			System.out
					.println("BanKhaiLenhDieuDongUtils.insertOrUpdateResultNotification()"
							+ reNotification);
			if (reNotification == null) {
				// Them moi.
				reNotification = new ResultNotification();
				reNotification.setBusinessTypeCode(messageType);
				reNotification.setDivision("System");
				reNotification.setDocumentName(document.getDocumentName());
				reNotification.setDocumentYear(document.getDocumentYear());
				reNotification.setLatestDate(new Date());
				reNotification.setRequestCode(UUID.randomUUID().toString());

				if (document != null) {
					reNotification.setMaritimeCode(document.getMaritimeCode());
				}

				// resultNotification.setRemarks(userName);
				// setRole Thutuc - Kehoach
				if (messageType == MessageType.BO_SUNG_THU_TUC) {
					reNotification.setRole(4);
				} else if (messageType == MessageType.HUY_LENH_DIEU_DONG
						|| messageType == MessageType.BO_SUNG_KE_HOACH) {
					reNotification.setRole(2);
				} else {
					reNotification.setRole(2);
				}

				reNotification.setResponse(yKienReject);
				reNotification.setRequestState(1);
				reNotification.setResponseTime(new Date());
				reNotification.setOfficerName(userName);
				reNotification.setLatestDate(new Date());
				reNotification.setIsReply(1);

				ResultNotificationLocalServiceUtil
						.addResultNotification(reNotification);
			} else {
				// setRole Thutuc - Kehoach
				if (messageType == MessageType.BO_SUNG_THU_TUC) {
					reNotification.setRole(4);
				} else if (messageType == MessageType.HUY_LENH_DIEU_DONG
						|| messageType == MessageType.BO_SUNG_KE_HOACH) {
					reNotification.setRole(2);
				} else {
					reNotification.setRole(2);
				}
				reNotification.setResponse(yKienReject);
				reNotification.setRequestState(1);
				if (document != null) {
					reNotification.setMaritimeCode(document.getMaritimeCode());
				}
				reNotification.setResponseTime(new Date());
				reNotification.setOfficerName(userName);
				reNotification.setLatestDate(new Date());
				reNotification.setIsReply(1);

				ResultNotificationLocalServiceUtil
						.updateResultNotification(reNotification);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private static int dongDau(
			String requestCode, long documentName, int documentYear, int messageType, int desStatus,
			User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest, IssueShiftingOrder shiftOrder, 
			TempDocument document, long fileEntryId) throws SystemException {
		
		String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");
		
		int markedAsArrival = 0;
		if (shiftOrder != null) {

			shiftOrder.setIsApproval(PageType.DUYET_PHE_CHUAN);

			shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.CHAP_NHAN_BAN_KHAI);
			shiftOrder.setStampStatus(2);
			String signLocation = ParamUtil.getString(uploadPortletRequest, "signLocation");
			
			shiftOrder.setSignPlace(signLocation);
			shiftOrder.setAttachedFile(fileEntryId);
			
			IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
			
			//Add by TrungNT
			markedAsArrival = ChuyenDichTrangThaiUtils.DANH_DAU_DA_KY_DUYET;
		}

		String versionduyet = shiftOrder.getVersionNo();
		if (versionduyet.length() > 0) {
//			if (FormatData.convertToInt(versionduyet) == 1) {
				document.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
				document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.THU_TUC_DA_TIEP_NHAN);
//			} else if (FormatData.convertToInt(versionduyet) > 1) {
//				// do not setDocumentStatusCode and setShipPosition
//			}
		} else {
			document.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
			document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.THU_TUC_DA_TIEP_NHAN);
		}

		document.setRequestState(TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG);
		document.setShipDateFrom(shiftOrder.getShiftingDate());
		TempDocumentLocalServiceUtil.updateTempDocument(document);

		if (document.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG
				&& Validator.isNotNull(shiftOrder.getRequestState())
				&& (shiftOrder.getRequestState() == TrangThaiBanKhaiNhapCanh.CHAP_NHAN_BAN_KHAI)) {

			// Tich hop send message 70-01
			if (document.getDocumentTypeCode().equalsIgnoreCase("NC")) {
				TT1TichHopMessageUtils.message_70_01(user.getEmailAddress(),
						uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
						requestCode, 0, shiftOrder.getVersionNo());
			} else if (document.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
				TT16TichHopMessageUtils.message_70_01(user.getEmailAddress(),
						uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
						requestCode, 0, shiftOrder.getVersionNo());
			} else if (document.getDocumentTypeCode()
					.equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
				TT6TichHopMessageUtils.message_70_01(user.getEmailAddress(),
						uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
						requestCode, 0, shiftOrder.getVersionNo());
			} else if (document.getDocumentTypeCode()
					.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
				TT8TichHopMessageUtils.message_70_01(user.getEmailAddress(),
						uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
						requestCode, 0, shiftOrder.getVersionNo());
			} else if (document.getDocumentTypeCode()
					.equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
				TT10TichHopMessageUtils.message_70_01(user.getEmailAddress(),
						uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
						requestCode, 0, shiftOrder.getVersionNo());
			} else if (document.getDocumentTypeCode()
					.equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
				TT4TichHopMessageUtils.message_70_01(user.getEmailAddress(),
						uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
						requestCode, 0, shiftOrder.getVersionNo());
			} else if (document.getDocumentTypeCode()
					.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
				TT3TichHopMessageUtils.message_70_01(user.getEmailAddress(),
						uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
						requestCode, 0, shiftOrder.getVersionNo());
			}

		} else {
			log.info("==KHONG_gui_lenh_dieu_dong=");
		}
		
		return markedAsArrival;
	}
}
