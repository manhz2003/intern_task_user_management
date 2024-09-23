package vn.gt.portlet.kehoach.nghiepvu.bankhai;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
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
import com.fds.nsw.liferay.core.PortalUtil;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.service.DLFileEntryLocalServiceUtil;

import vn.gt.constant.Constants;
import com.fds.nsw.nghiepvu.model.UserPort;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGTFunctionTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.InterfaceRequest;
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempDocument;


import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultCompletion;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;

import vn.gt.dao.result.service.ResultCompetionLocalServiceUtil;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
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
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiQuaCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiXuatCanh;
import vn.gt.tichhop.report.FileUploadUtils;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BanKhaiGiayPhepQuaCangBoGTVTUtils {


	private String realPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();

	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"), realPath.lastIndexOf("/WEB-INF"))
			.replaceFirst(":", "") + "/export/";
	
	

	private static String checkDisplayIcon(long documentName, int documentYear, int businessTypeCode) {
		List<ResultDeclaration> lstResult = ResultDeclarationLocalServiceUtil
				.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode, documentName, documentYear);
		int state = 0;
		if (lstResult != null && lstResult.size() > 0) {
			state = lstResult.get(0).getRequestState();
			// if (state == MessageType.TRANG_THAI_BANG_KHAI_TIEP_NHAN
			// || state == MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
			// return "icon_tich.png";
			// } else if (state == MessageType.TRANG_THAI_BANG_KHAI_TU_CHOI) {
			// return "close.png";
			// }
		}

		return "";
	}

	public static JSONObject buildThanhPhan(ThemeDisplay themeDisplay, long documentName, int documentYear) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<IssuePermissionForTransit> results = IssuePermissionForTransitLocalServiceUtil
				.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
						KeyParams.ORDER_BY_DESC);

		result.put("code", "NC_25");
		result.put("name", LanguageUtil.get(themeDisplay.getLocale(), "giay-phep-qua-cang"));
		boolean available = false;
		String requestCode = StringPool.BLANK;
		if (Validator.isNotNull(results) && results.size() > 0) {
			available = true;
			requestCode = results.get(0).getRequestCode();
		}

		result.put("available", available);
		result.put("documentName", documentName);
		result.put("documentYear", documentYear);
		result.put("messageType", MessageType.GIAY_PHEP_QUA_CANH);
		result.put("requestCode", requestCode);

		String icon = checkDisplayIcon(documentName, documentYear, MessageType.GIAY_PHEP_QUA_CANH);

		result.put("state", -1);
		if (icon.equals("icon_tich.png")) {
			result.put("state", 1);
		} else if (icon.equals("close.png")) {
			result.put("state", 0);
		}

		return result;
	}

	public static JSONArray getFileThanhPhanVersionList(ThemeDisplay themeDisplay, long documentName, int documentYear,
			int messageType) {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		try {
			JSONObject object;

			List<IssuePermissionForTransit> results = IssuePermissionForTransitLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
							KeyParams.ORDER_BY_DESC);

			for (IssuePermissionForTransit issuePortClearance : results) {
				object = JSONFactoryUtil.createJSONObject();

				String phienBan = issuePortClearance.getVersionNo() + " - " + 
						DmGtStatusLocalServiceUtil.findNameByStatusCode(issuePortClearance.getRequestState(), 3);
				object.put("version", phienBan);
				object.put("status", DmGtStatusLocalServiceUtil
						.findNameByStatusCode(issuePortClearance.getRequestState(), messageType));
				object.put("sendDate", issuePortClearance.getApprovalDate());

				object.put("requestCode", issuePortClearance.getRequestCode());

				object.put("remarks", issuePortClearance.getRemarks());
				
				result.put(object);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public int _giayPhepQuaCangGTVT(String requestCode, long documentName, int documentYear, int messageType,
			int desStatus, User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {

		int resultD = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;

		//Add by TrungNT
		int markedAsArrival = 0;
		int markedAsDeparture = 0;
		int markedAsTransmit = 0;
		int markedAsVoyage =  0;
		
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);

			long groupId = themeDisplay.getScopeGroupId();

			ServiceContext serviceContext = new ServiceContext();
			
			List<IssuePermissionForTransit> results = IssuePermissionForTransitLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
							KeyParams.ORDER_BY_DESC);

			if (results.size() > 0) {
				requestCode = results.get(0).getRequestCode();
			}

			String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");
			int actionType = ParamUtil.getInteger(uploadPortletRequest, ChuyenDichTrangThaiUtils.ACTION_TYPE);

			TempDocument tempDocument = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

			IssuePermissionForTransit perForTransitDuyet = IssuePermissionForTransitLocalServiceUtil
					.getByrequestCode(requestCode);

			switch (actionType) {
			case PageType.ACTION_TYPE_TIEP_NHAN:

				taoGiayPhepRoiCang(documentName, messageType, documentYear, actionType, user.getEmailAddress(),
						requestCode, user, uploadPortletRequest, actionRequest, tempDocument.getDocumentStatusCode());

				//Add by TrungNT
				markedAsTransmit = ChuyenDichTrangThaiUtils.TAO_GIAY_PHEP_QUA_CANH;
				markedAsDeparture = ChuyenDichTrangThaiUtils.TAO_GIAY_PHEP_QUA_CANH;
				InterfaceRequest interfacePortClearance = InterfaceRequestLocalServiceUtil
						.findByRequestCode(requestCode);

				if (interfacePortClearance != null) {
					String issueDate = ParamUtil.getString(uploadPortletRequest, "issueDate");
					String remarkCapLai = BusinessUtils.getRemarkCapLai(user.getEmailAddress(), yKienReject,
							FormatData.parseDateShort3StringToDate(issueDate));
					interfacePortClearance.setRemarks(remarkCapLai);
					InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfacePortClearance);
				}

				if (tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_DE_NGHI_SUA_GIAY_PHEP) {
					ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear,
							ChuyenDichTrangThaiUtils.THU_TUC_DE_NGHI_CAP_GIAY_PHEP, Boolean.FALSE);
				}

				break;

			case PageType.ACTION_TYPE_CAP_LAI_GIAY_PHEP:

				ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear,
						ChuyenDichTrangThaiUtils.THU_TUC_DE_NGHI_SUA_GIAY_PHEP, Boolean.FALSE);
//				perForTransitDuyet.setRequestState(ChuyenDichTrangThaiUtils.BAN_KHAI_KHAI_SUA);
//				perForTransitDuyet.setAttachedFile(0L);
				IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransitDuyet);
				
				//Add by TrungNT
				markedAsTransmit = ChuyenDichTrangThaiUtils.TAO_GIAY_PHEP_QUA_CANH;
				markedAsDeparture = ChuyenDichTrangThaiUtils.TAO_GIAY_PHEP_QUA_CANH;
//				boolean resultMethod = TT3TichHopMessageUtils.message_90_01(user.getEmailAddress(),
//						uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode,
//						0, perForTransitDuyet.getVersionNo());

				break;

			case PageType.ACTION_TYPE_DUYET:

				if (Validator.isNotNull(perForTransitDuyet)) {

					perForTransitDuyet.setRequestState(TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI);
					perForTransitDuyet.setIsApproval(PageType.DUYET_PHE_CHUAN);
					perForTransitDuyet.setApprovalDate(new Date());
					perForTransitDuyet.setApprovalName(user.getEmailAddress());
					perForTransitDuyet.setDateOfSign(new Date());
					
					try {
						
						long fileEntryId = perForTransitDuyet.getAttachedFile();
						// save file to shifting order
						long nanoTime = ReportsBusinessUtils.actionExport(requestCode, (int) documentName, documentYear,
								messageType, tempDocument.getDocumentTypeCode());
						
						String tenFileExport = nanoTime + "_" + ReportConstant.PERMISSION_FOR_TRANSIT_EXPORT;
						File file = new File(pathFileSub + tenFileExport);
						DLFileEntry fileEntry = FileUploadUtils.uploadFile(user.getUserId(), groupId, fileEntryId, file,
								pathFileSub + tenFileExport, null, null, serviceContext);
						perForTransitDuyet.setAttachedFile(fileEntry.getFileEntryId());
						
					} catch (Exception e) {
						log.error(e.getMessage());
					}
					
					IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransitDuyet);
					
					//Add by TrungNT
					markedAsTransmit = ChuyenDichTrangThaiUtils.DANH_DAU_DA_DUYET;
					markedAsDeparture = ChuyenDichTrangThaiUtils.DANH_DAU_DA_DUYET;
					tempDocument.setDocumentStatusCode(TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
					TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					

					xacNhanHoanThanhThuTucUpgrade(uploadPortletRequest, documentName, documentYear);

					tempDocument.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC);

					if (tempDocument != null) {
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					}

					TT3TichHopMessageUtils.message_90_01(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject,
							requestCode, 0, perForTransitDuyet.getVersionNo());


				}

				break;
			case PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET:

				if (perForTransitDuyet != null) {

					// TODO Vao truong hop cho lanh dao duyet
					perForTransitDuyet.setStampStatus(PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET);
					perForTransitDuyet.setIsApproval(PageType.KHONG_PHE_CHUAN);

					try {
						
						long fileEntryId = perForTransitDuyet.getAttachedFile();
						// save file to shifting order
						long nanoTime = ReportsBusinessUtils.actionExport(requestCode, (int) documentName, documentYear,
								messageType, tempDocument.getDocumentTypeCode());
						
						String tenFileExport = nanoTime + "_" + ReportConstant.PERMISSION_FOR_TRANSIT_EXPORT;
						File file = new File(pathFileSub + tenFileExport);
						DLFileEntry fileEntry = FileUploadUtils.uploadFile(user.getUserId(), groupId, fileEntryId, file,
								pathFileSub + tenFileExport, null, null, serviceContext);
						perForTransitDuyet.setAttachedFile(fileEntry.getFileEntryId());
						
					} catch (Exception e) {
						log.error(e.getMessage());
					}

					IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransitDuyet);
					
					//Add by TrungNT
					markedAsTransmit = ChuyenDichTrangThaiUtils.DANH_DAU_CHO_LANH_DAO_KY_BCY;
					markedAsDeparture = ChuyenDichTrangThaiUtils.DANH_DAU_CHO_LANH_DAO_KY_BCY;
				}
				break;
			case PageType.ACTION_TYPE_CHUYEN_TRA_HO_SO:

				if ((perForTransitDuyet != null) && Validator.isNotNull(perForTransitDuyet.getRequestState())) {

					perForTransitDuyet.setStampStatus(PageType.KHONG_PHE_CHUAN);
					perForTransitDuyet.setIsApproval(PageType.KHONG_PHE_CHUAN);
					perForTransitDuyet.setIsCancel(PageType.KHONG_PHE_CHUAN);
					perForTransitDuyet.setCancelNote(yKienReject);
					perForTransitDuyet.setAttachedFile(0L);

					perForTransitDuyet.setRemarks(yKienReject);
					
					IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransitDuyet);

					//Add by TrungNT
					markedAsTransmit = ChuyenDichTrangThaiUtils.DANH_DAU_LANH_DAO_TRA_LAI;
					markedAsDeparture = ChuyenDichTrangThaiUtils.DANH_DAU_LANH_DAO_TRA_LAI;
					tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName,
							documentYear);

					BusinessUtils.updateLyDoResultNotification(user.getEmailAddress(), yKienReject,
							MessageType.Y_CAU_TRA_LAI_HO_SO_GP_QUA_CANH, tempDocument.getMaritimeCode(), documentName,
							documentYear);

					ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear,
							ChuyenDichTrangThaiUtils.THU_TUC_DE_NGHI_SUA_GIAY_PHEP, Boolean.FALSE);
					
					InterfaceRequest interfaceShifOrder = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);

					if (interfaceShifOrder != null) {
						String remarkCapLai = BusinessUtils.getRemarkCapLai(user.getEmailAddress(), yKienReject,
								new Date());

						interfaceShifOrder.setRemarks(remarkCapLai);
						InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceShifOrder);
					}

				}
				break;
			case PageType.ACTION_TYPE_KYSO:

				long fileEntryId = FileUploadUtil.kySoFile(uploadPortletRequest, perForTransitDuyet.getAttachedFile(), "KY_SO", serviceContext);
				
				if (Validator.isNotNull(perForTransitDuyet)) {
					perForTransitDuyet.setRequestState(ChuyenDichTrangThaiUtils.BAN_KHAI_KHAI_MOI);

					perForTransitDuyet.setApprovalDate(new Date());
					perForTransitDuyet.setApprovalName(user.getEmailAddress());

					perForTransitDuyet.setStampStatus(PageType.ACTION_TYPE_TIEP_NHAN);
					String representative = perForTransitDuyet.getRepresentative();
					String portofAuthority = perForTransitDuyet.getPortofAuthority();
					String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
					perForTransitDuyet.setSignTitle(signTitle);
					perForTransitDuyet.setSignName(signTitle);
					perForTransitDuyet.setSignPlace(ParamUtil.getString(uploadPortletRequest, "signPlace"));
					perForTransitDuyet.setSignDate(new Date());
					perForTransitDuyet.setDateOfSign(new Date());

					String signLocation = ParamUtil.getString(uploadPortletRequest, "signLocation");
					
					perForTransitDuyet.setSignPlace(signLocation);
					
					perForTransitDuyet.setAttachedFile(fileEntryId);
					
					perForTransitDuyet.setIsApproval(PageType.DUYET_PHE_CHUAN);

					IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransitDuyet);
					
					//Add by TrungNT
					markedAsTransmit = ChuyenDichTrangThaiUtils.DANH_DAU_CHO_DONG_DAU_BCY;
					markedAsDeparture = ChuyenDichTrangThaiUtils.DANH_DAU_CHO_DONG_DAU_BCY;
					// TODO
					tempDocument.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP);

					if (tempDocument != null) {
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					}
					
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
						
						markedAsTransmit = dongDau(requestCode, documentName, documentYear, messageType, desStatus, user,
								uploadPortletRequest, actionRequest, perForTransitDuyet, tempDocument, fileEntry.getFileEntryId());
						markedAsDeparture = markedAsTransmit;
					}

				}
				break;

			case PageType.ACTION_TYPE_DONGDAU:

				fileEntryId = FileUploadUtil.kySoFile(uploadPortletRequest, perForTransitDuyet.getAttachedFile(), "DONG_DAU", serviceContext);
				
				markedAsTransmit = dongDau(requestCode, documentName, documentYear, messageType, desStatus, user, uploadPortletRequest,
						actionRequest, perForTransitDuyet, tempDocument, fileEntryId);
				markedAsDeparture = markedAsTransmit;
				
				break;

			case PageType.ACTION_TYPE_HUY:

				tempDocument.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
				TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);

				List<IssuePermissionForTransit> lstPortClearances = IssuePermissionForTransitLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC);

				for (IssuePermissionForTransit object : lstPortClearances) {
					object.setRequestState(ChuyenDichTrangThaiUtils.BAN_KHAI_KHAI_HUY);
					object.setIsCancel(1);
					object.setCancelName(user.getEmailAddress());
					object.setCancelNote(yKienReject);
					object.setCancelDate(new Date());
					object.setRemarks(yKienReject);
					IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(object);
				}
				
				//Add by TrungNT
				markedAsTransmit = ChuyenDichTrangThaiUtils.DANH_DAU_DA_HUY;
				markedAsDeparture = ChuyenDichTrangThaiUtils.DANH_DAU_DA_HUY;
				// TODO update Result Notification
				BusinessUtils.updateLyDoResultNotification(user.getEmailAddress(), yKienReject,
						MessageType.HUY_GIAY_PHEP_QUA_CANH, tempDocument.getMaritimeCode(), documentName, documentYear);

				if (lstPortClearances != null && lstPortClearances.size() > 0) {

					TT3TichHopMessageUtils.message_93_03(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode,
							0);

					ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear,
							ChuyenDichTrangThaiUtils.THU_TUC_HUY_THU_TUC, Boolean.FALSE);
				}
				break;
			}
			
			resultD = tempDocument.getDocumentStatusCode();
			
		} catch (Exception e) {
			log.error(e.getMessage());
			resultD = TT1XuLyNghiepVuUtils.XU_LY_THAT_BAI;
		}

		// check active
		boolean active = VmaItineraryUtils.checkActiveVma(user.getUserId());

		if (active) {
			log.info("BanKhaiGiayPhepQuaCangUtils >>>>>>>> "
					+ documentName + "|" + documentYear + "|" + markedAsArrival
					+ "|" + markedAsDeparture + "|" + markedAsTransmit + "|"
					+ markedAsVoyage);
			VmaItineraryLocalServiceUtil.updateVmaItinerary(documentName,
					documentYear, markedAsArrival > 0 ? markedAsArrival : null,
					markedAsDeparture > 0 ? markedAsDeparture : null,
					markedAsTransmit > 0 ? markedAsTransmit : null,
					markedAsVoyage > 0 ? markedAsVoyage : null);
			}
		
		return resultD;
	}

	private static void xacNhanHoanThanhThuTucUpgrade(UploadPortletRequest resourceRequest, long documentName,
			int documentYear) {
		try {
			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip");
			String nameOfMaster = ParamUtil.getString(resourceRequest, "nameOfMaster");
			String portOfArrivalCode = ParamUtil.getString(resourceRequest, "portOfArrivalCode");
			String certificateNo = ParamUtil.getString(resourceRequest, "certificateNo");
			String maritimeReference = ParamUtil.getString(resourceRequest, "maritimeReference");
			String flagStateOfShip = ParamUtil.getString(resourceRequest, "flagStateOfShip");
			double grossTonnage = ParamUtil.getDouble(resourceRequest, "grossTonnage", 0L);
			Date approvalTime = ParamUtil.getDate(resourceRequest, "approvalTime", FormatData.formatDateShort3);
			String approvalName = ParamUtil.getString(resourceRequest, "approvalName");

			ResultCompletion reCompetion = ResultCompetionLocalServiceUtil
					.findByDocumentNameAndDocumentYear(documentName, documentYear);
			if (reCompetion == null) {
				reCompetion = new ResultCompletion();
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				reCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), approvalTime));

				reCompetion.setDocumentName(documentName);
				reCompetion.setDocumentYear(documentYear);
				reCompetion.setRequestCode(UUID.randomUUID().toString());

				ResultCompetionLocalServiceUtil.addResultCompetion(reCompetion);
			} else {
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				reCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), approvalTime));

				ResultCompetionLocalServiceUtil.updateResultCompetion(reCompetion);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private IssuePermissionForTransit taoGiayPhepRoiCang(long documentName, int messageType, int documentYear,
			int actionType, String emailAddress, String requestCode, User user, UploadPortletRequest resourceRequest,
			ActionRequest actionRequest, int currentStatus) {
		try {

			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName,
					documentYear);

			// Su dung tenCangDenThayThe neu nextPortOfCallCode chua ZZZ
			String tenCangDenThayThe = ParamUtil.getString(resourceRequest, "remarks");

			// Ngay cap
			Date issueDate = ParamUtil.getDate(resourceRequest, "issueDate", FormatData.formatDateShortZone);
			if (Validator.isNull(issueDate)) {
				issueDate = new Date();
			}

			// Giam doc cang vu hang hai
			String directorOfMaritime = "GD";

			// String directorOfMaritimeAdministration = "GD";
			UserPort defaultCode = UserPortLocalServiceUtil.findByUserId(user.getUserId());
			String maritimeCode = StringPool.BLANK;

			if (defaultCode != null) {
				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(defaultCode.getPortCode());
				if (dmMaritime != null) {
					directorOfMaritime = dmMaritime.getCityCode();
					maritimeCode = dmMaritime.getMaritimeCode();
				}
			}
			// Loai hang hoa
			String cargoDescription = "";

			List<IssuePermissionForTransit> results = IssuePermissionForTransitLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
							KeyParams.ORDER_BY_DESC);

			IssuePermissionForTransit portClearance = null;

			boolean isAdd = false;
			int versionNo = 0;

			if ((currentStatus != ChuyenDichTrangThaiUtils.THU_TUC_DE_NGHI_SUA_GIAY_PHEP && results.size() > 0)
					|| results.size() > 0) {
				portClearance = results.get(0);
			}

			if (currentStatus == ChuyenDichTrangThaiUtils.THU_TUC_DE_NGHI_SUA_GIAY_PHEP) {
				portClearance = null;
			}
			
			if (Validator.isNull(portClearance)) {
				portClearance = new IssuePermissionForTransit();
				isAdd = true;
			}

			String detail_clearance_str = ParamUtil.getString(resourceRequest, "detail_clearance");

			JSONObject detail_clearance = JSONFactoryUtil.createJSONObject(detail_clearance_str);

			String numberPortClearance = detail_clearance.getString("certificateNo");
			String portofAuthority = maritimeCode;
			String nameOfShip = detail_clearance.getString("nameOfship");
			String flagStateOfShip = tempDocument.getStateCode();
			int numberOfCrews = detail_clearance.getInt("numberOfCrews");
			int numberOfPassengers = detail_clearance.getInt("numberOfPassengers");
			String callSign = detail_clearance.getString("callSign");
			String nameOfMaster = detail_clearance.getString("nameOfMaster");
			String cargo = detail_clearance.getString("transitOfCargo");
			double volumeCargo = detail_clearance.getDouble("volumeTransitCargo");
			String cargoUnit = StringPool.BLANK;
			try {
				cargoUnit = detail_clearance.getJSONObject("unitCode").getString("code0");
			} catch (Exception e) {
				cargoUnit = detail_clearance.getString("unitCode");
			}
			String transitCargo = detail_clearance.getString("transitOfCargo");
			double volumeTransitCargo = detail_clearance.getDouble("volumeTransitCargo");
			String transitCargoUnit = detail_clearance.getString("unitVolumeTransitCargo");
			Date timeOfDeparture = ParamUtil.getDate(resourceRequest, "timeOfDeparture", FormatData.formatDateShortZone);
			String nextPortOfCallCode = detail_clearance.getString("portName");
			String PortCode = detail_clearance.getString("portCode");
			Date validUntil = ParamUtil.getDate(resourceRequest, "timeOfValidUntil", FormatData.formatDateShortZone);
			String certificateNo = detail_clearance.getString("certificateNo");
			String unitCertificate = detail_clearance.getString("unitCertificateNo");
			double gt = detail_clearance.getDouble("grt");

			String permittedTransitFrom = detail_clearance.getString("cangRoi");
			String permittedTransitTo = detail_clearance.getString("cangDen");
			
			portClearance.setPermittedTransitFrom(permittedTransitFrom);
			portClearance.setPermittedTransitTo(permittedTransitTo);
			 portClearance.setNumberPermissionForTransit(numberPortClearance);
			portClearance.setPortofAuthority(portofAuthority);
			portClearance.setNameOfShip(nameOfShip);
			portClearance.setFlagStateOfShip(flagStateOfShip);
			portClearance.setNumberOfCrews(numberOfCrews);
			portClearance.setNumberOfPassengers(numberOfPassengers);
			portClearance.setCallSign(callSign);
			portClearance.setNameOfMaster(nameOfMaster);
			// portClearance.setCargo(cargo);
			portClearance.setVolumeCargo(volumeCargo);
			portClearance.setCargoUnit(cargoUnit);
			
			// a Son set null transitCargo
			portClearance.setTransitCargo(transitCargo);
			// portClearance.setVolumeTransitCargo(volumeTransitCargo);
//			 portClearance.setTransitCargoUnit(transitCargoUnit);
			portClearance.setTimeOfDeparture(timeOfDeparture);
			// portClearance.setNextPortOfCallCode(nextPortOfCallCode);
			// portClearance.setPortCode(PortCode);
			portClearance.setUserCreated(tempDocument.getUserCreated());
			portClearance.setValidUntil(validUntil);
			portClearance.setCertificateNo(certificateNo.trim() + "/" + unitCertificate.trim());
			portClearance.setGt(gt);

			String representative = ParamUtil.getString(resourceRequest, "representative");

			portClearance.setRepresentative(representative);
			String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
			portClearance.setSignTitle(signTitle);

			// if (Validator.isNotNull(TempDoc)) {
			// portClearance.setDwt(TempDoc.getDwt());
			// }
			portClearance.setApprovalDate(issueDate);
			portClearance.setDirectorOfMaritime(directorOfMaritime);
			portClearance.setRemarks(tenCangDenThayThe);
			portClearance.setDocumentName(documentName);
			portClearance.setDocumentYear(documentYear);
			
			portClearance.setCargoDescription(cargoDescription);

			if (cargoDescription.trim().length() > 0) {
				// portClearance.setCargo(StringPool.BLANK);
				portClearance.setCargoUnit(StringPool.BLANK);
				portClearance.setCargoDescription(cargoDescription);
			}

			String uuidStr = "";
			
			if (isAdd) {
				
				uuidStr = UUID.randomUUID().toString();
						
				portClearance.setCreatedDate(new Date());
				portClearance.setVersionNo((results.size() == 0) ? String.valueOf(1): String.valueOf(results.size() + 1));
				portClearance.setRequestCode(uuidStr);
				portClearance.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_MOI);
				portClearance = IssuePermissionForTransitLocalServiceUtil.addIssuePermissionForTransit(portClearance);
				log.info("===========MOT_LAN====" + "INSERT");
			} else {
				
				uuidStr = portClearance.getRequestCode();
				
				portClearance.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_SUA);
				portClearance.setAttachedFile(0L);
				portClearance = IssuePermissionForTransitLocalServiceUtil
						.updateIssuePermissionForTransit(portClearance);
				log.info("===========MOT_LAN====" + "UPDATE");
			}

			String detail_clearance_data = ParamUtil.getString(resourceRequest, "detail_clearance_data");

			JSONArray detail_clearance_array = JSONFactoryUtil.createJSONArray(detail_clearance_data);
			JSONObject current = null;
			for (int i = 0; i < detail_clearance_array.length(); i++) {
				current = detail_clearance_array.getJSONObject(i);
				if (Validator.isNull(current.getString("requestCode")) && current.getLong("idXoa") == 0) {
					TempCargoItems cargoItem = null;
					cargoItem = new TempCargoItems();
					cargoItem.setDocumentName(documentName);
					cargoItem.setDocumentYear(documentYear);
					cargoItem.setRequestCode(portClearance.getRequestCode());
					cargoItem.setCargoCategory("C1_VC");
					try {
						cargoItem.setCargoType(current.getJSONObject("cargoType").getString("code0"));
					} catch (Exception e) {
						cargoItem.setCargoType(current.getString("cargoType"));
					}
					try {
						cargoItem.setCargoCode(current.getJSONObject("cargoCode").getString("code0"));
					} catch (Exception e) {
						cargoItem.setCargoCode(current.getString("cargoCode"));
					}
					
					cargoItem.setDescription(current.getString("description"));
					cargoItem.setQuantity(current.getDouble("quantity"));
					try {
						cargoItem.setUnit(current.getJSONObject("unit").getString("code0"));
					} catch (Exception e) {
						cargoItem.setUnit(current.getString("unit"));
					}

					cargoItem.setSequence(i + 1);
					cargoItem.setModifiedDate(new Date());

//					if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0)) {
//						cargoDescription += "\n "
//								+ DmDataItemLocalServiceUtil
//										.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType())
//										.getName()
//								+ ", "
//								+ DmDataItemLocalServiceUtil
//										.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode())
//										.getName()
//								+ " " + cargoItem.getDescription() + " " + cargoItem.getQuantity() + " "
//								+ DmDataItemLocalServiceUtil
//										.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit())
//										.getName();
//
//						TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
//					} else if (cargoItem.getCargoType().equalsIgnoreCase("11")) {
//						cargoDescription += "\n " + DmDataItemLocalServiceUtil
//								.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType())
//								.getName();
//						TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
//					}
					
					TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
				} else {
					log.info("UPDATE TempCargoItems");
					if (current.getLong("idXoa") == 1) {
						TempCargoItemsLocalServiceUtil.deleteTempCargoItems(current.getLong("id"));
					} else {
						
						TempCargoItems cargoItem = TempCargoItemsLocalServiceUtil
								.fetchTempCargoItems(current.getLong("id"));

						try {
							cargoItem.setCargoType(current.getJSONObject("cargoType").getString("code0"));
						} catch (Exception e) {
							cargoItem.setCargoType(current.getString("cargoType"));
						}
						try {
							cargoItem.setCargoCode(current.getJSONObject("cargoCode").getString("code0"));
						} catch (Exception e) {
							cargoItem.setCargoCode(current.getString("cargoCode"));
						}

						cargoItem.setDescription(current.getString("description"));
						cargoItem.setQuantity(current.getDouble("quantity"));
						try {
							cargoItem.setUnit(current.getJSONObject("unit").getString("code0"));
						} catch (Exception e) {
							cargoItem.setUnit(current.getString("unit"));
						}
						cargoItem.setModifiedDate(new Date());
						cargoItem.setRequestCode(portClearance.getRequestCode());
						TempCargoItemsLocalServiceUtil.updateTempCargoItems(cargoItem);

					}
					
				}

			}
			

			// update cagodecs

			StringBuilder cargoDescription_C1_XEP = new StringBuilder();
//			StringBuilder cargoDescription_C1_DO = new StringBuilder();
//			StringBuilder cargoDescription_C1_VC = new StringBuilder();
//			StringBuilder cargoDescription_C2_VC = new StringBuilder();
//			StringBuilder cargoDescription_C2_DO = new StringBuilder();
//			StringBuilder cargoDescription_C3_XEP = new StringBuilder();
//			StringBuilder cargoDescription_C3_DO = new StringBuilder();
//			StringBuilder cargoDescription_C3_VC = new StringBuilder();
//			StringBuilder cargoDescription_C4 = new StringBuilder();
//			StringBuilder cargoDescription_C5 = new StringBuilder();
//			StringBuilder cargoDescription_C6_XEP = new StringBuilder();
//			StringBuilder cargoDescription_C6_DO = new StringBuilder();
//			StringBuilder cargoDescription_C6_VC = new StringBuilder();
			
			List<TempCargoItems> tempCargoItemsOrg = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear);
			List<TempCargoItems> tempCargoItems = new ArrayList<TempCargoItems>();
			
			
			for (TempCargoItems tempCargoItems2 : tempCargoItemsOrg) {
				if ((tempCargoItems2.getCargoCategory().endsWith("_VC") || tempCargoItems2.getCargoCategory().equalsIgnoreCase("VC") || tempCargoItems2.getCargoCategory().equalsIgnoreCase("C5")) && 
						portClearance.getRequestCode().equals(tempCargoItems2.getRequestCode())) {

					TempCargoItems details = tempCargoItems2;
					
					try {
						String cargoCategory = details.getCargoCategory().trim();
						String tmp = "";
						
						if (Validator.isNotNull(details.getCargoType()) && Validator.isNotNull(details.getUnit()))
						{
							tmp = DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName()
								+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, details.getCargoCode()).getName()
								+ "  " + details.getDescription()  + "  " + details.getQuantity()
								+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, details.getUnit()).getName()
								
								+ " (" +
								DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getDescription()
								+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, details.getCargoCode()).getDescription()
								+ "  "  + details.getQuantity()
								+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, details.getUnit()).getDescription()
								+ ") "
								
								+ " \n ";
						}
						else if (Validator.isNotNull(details.getCargoType()) && details.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
						{	
							tmp = DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getName() 
									+ " (" +
									DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, details.getCargoType()).getDescription()
									+ ") "
									+ " \n ";
						}
						cargoDescription_C1_XEP.append(tmp);
//						if (cargoCategory.equalsIgnoreCase("C1_XEP"))
//						{
//							cargoDescription_C1_XEP.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C1_DO"))
//						{
//							cargoDescription_C1_DO.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C1_VC"))
//						{
//							cargoDescription_C1_VC.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C2_VC"))
//						{
//							cargoDescription_C2_VC.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C2_DO"))
//						{
//							cargoDescription_C2_DO.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C3_XEP"))
//						{
//							cargoDescription_C3_XEP.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C3_DO"))
//						{
//							cargoDescription_C3_DO.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C3_VC"))
//						{
//							cargoDescription_C3_VC.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C6_XEP"))
//						{
//							cargoDescription_C6_XEP.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C6_DO"))
//						{
//							cargoDescription_C6_DO.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C6_VC"))
//						{
//							cargoDescription_C6_VC.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C4"))
//						{
//							cargoDescription_C4.append(tmp);
//						} else if (cargoCategory.equalsIgnoreCase("C5"))
//						{
//							cargoDescription_C5.append(tmp);
//						}
					}catch (Exception e) {
						log.error(e.getMessage());
					};
					
				}
			}
			
//			StringBuilder cargoDescription_XK = new StringBuilder();
//			StringBuilder cargoDescription_NK = new StringBuilder();
//			StringBuilder cargoDescription_ND = new StringBuilder();
//			StringBuilder cargoDescription_TC = new StringBuilder();
//			StringBuilder cargoDescription_QC = new StringBuilder();
//			
//			if (Validator.isNotNull(cargoDescription_C1_XEP.toString()))
//			{				
//				cargoDescription_XK.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C1_XEP").getName() + ": " + "\n" + cargoDescription_C1_XEP.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C1_DO.toString()))
//			{				
//				cargoDescription_XK.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C1_DO").getName() + ": " + "\n" + cargoDescription_C1_DO.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C1_VC.toString()))
//			{				
//				cargoDescription_XK.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C1_VC").getName() + ": " + "\n" + cargoDescription_C1_VC.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C2_VC.toString()))
//			{				
//				cargoDescription_NK.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C2_VC").getName() + ": " + "\n" + cargoDescription_C2_VC.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C3_XEP.toString()))
//			{				
//				cargoDescription_ND.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C3_XEP").getName() + ": " + "\n" + cargoDescription_C3_XEP.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C3_DO.toString()))
//			{				
//				cargoDescription_ND.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C3_DO").getName() + ": " + "\n" + cargoDescription_C3_DO.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C3_VC.toString()))
//			{				
//				cargoDescription_ND.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C3_VC").getName() + ": " + "\n" + cargoDescription_C3_VC.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C4.toString()))
//			{	
//				cargoDescription_QC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C4").getName() + "\n");
//				cargoDescription_QC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C4").getDescription() + "\n");
//				cargoDescription_QC.append(cargoDescription_C4.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C5.toString()))
//			{				
//				cargoDescription_QC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C5").getName() + "\n");
//				cargoDescription_QC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C5").getDescription() + "\n");
//				cargoDescription_QC.append(cargoDescription_C5.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C6_XEP.toString()))
//			{				
//				cargoDescription_TC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C6_XEP").getName() + ": " + "\n" + cargoDescription_C6_XEP.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C6_DO.toString()))
//			{				
//				cargoDescription_TC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C6_DO").getName() + ": " + "\n" + cargoDescription_C6_DO.toString() + "\n");
//						
//			}
//			
//			if (Validator.isNotNull(cargoDescription_C6_VC.toString()))
//			{				
//				cargoDescription_TC.append(DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "C6_VC").getName() + ": " + "\n" + cargoDescription_C6_VC.toString() + "\n");
//						
//			}
			
			cargoDescription += "\n" + cargoDescription_C1_XEP.toString();
			
//			if(Validator.isNotNull(cargoDescription_XK.toString())) {
//				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "XK").getName();
//				cargoDescription += "\n";
//				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "XK").getDescription();
//				cargoDescription += "\n";
//				cargoDescription += cargoDescription_XK;
////				cargoDescription += "\n";
//			}
//			
//			if(Validator.isNotNull(cargoDescription_NK.toString())) {
//				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "NK").getName();
//				cargoDescription += "\n";
//				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "NK").getDescription();
//				cargoDescription += "\n";
//				cargoDescription += cargoDescription_NK;
////				cargoDescription += "\n";
//			}
//			
//			if(Validator.isNotNull(cargoDescription_ND.toString())) {
//				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "ND").getName();
//				cargoDescription += "\n";
//				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "ND").getDescription();
//				cargoDescription += "\n";
//				cargoDescription += cargoDescription_ND;
////				cargoDescription += "\n";
//			}
//			
//			if(Validator.isNotNull(cargoDescription_TC.toString())) {
//				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "TC").getName();
//				cargoDescription += "\n";
//				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "TC").getDescription();
//				cargoDescription += "\n";
//				cargoDescription += cargoDescription_TC;
////				cargoDescription += "\n";
//			}
//			
//			if(Validator.isNotNull(cargoDescription_QC.toString())) {
//				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "QC").getName();
//				cargoDescription += "\n";
//				cargoDescription += DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_LOAI_HANG_HOA, "QC").getDescription();
//				cargoDescription += "\n";
//				cargoDescription += cargoDescription_QC;
////				cargoDescription += "\n";
//			}
			
			log.info("cargoDescriptioncargoDescriptioncargoDescriptioncargoDescription: " + cargoDescription);
			
			IssuePermissionForTransit portClearanceDesc = IssuePermissionForTransitLocalServiceUtil.getByrequestCode(uuidStr);
			
			portClearanceDesc.setCargoDescription(cargoDescription);

			if (cargoDescription.trim().length() > 0) {
				portClearanceDesc.setCargoUnit(StringPool.BLANK);
			}
			
			IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(portClearanceDesc);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;

	}
	
	private static int dongDau(
			String requestCode, long documentName, int documentYear, int messageType, int desStatus,
			User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest, IssuePermissionForTransit perForTransitDuyet, 
			TempDocument document, long fileEntryId) throws SystemException {
		
		String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");
		
		int markedAsTransmit = 0;
		
		if (Validator.isNotNull(perForTransitDuyet)) {

			perForTransitDuyet.setRequestState(ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI);
			perForTransitDuyet.setIsApproval(PageType.DUYET_PHE_CHUAN);

			perForTransitDuyet.setStampStatus(2);

			String signLocation = ParamUtil.getString(uploadPortletRequest, "signLocation");
			
			perForTransitDuyet.setSignPlace(signLocation);
			
			perForTransitDuyet.setAttachedFile(fileEntryId);
			
			IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransitDuyet);
			
			//Add by TrungNT
			markedAsTransmit = ChuyenDichTrangThaiUtils.DANH_DAU_DA_KY_DUYET;

			ChuyenDichTrangThaiUtils.doHoSo(documentName, documentYear,
					ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC, Boolean.FALSE);
			// Tich hop send message 99-23
			TT3TichHopMessageUtils.message_90_01(user.getEmailAddress(),
					uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject, requestCode,
					0, perForTransitDuyet.getVersionNo());
			
		}
		
		return markedAsTransmit;
	}

}
