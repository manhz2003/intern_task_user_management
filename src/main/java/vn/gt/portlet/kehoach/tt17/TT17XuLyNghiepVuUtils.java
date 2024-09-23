package vn.gt.portlet.kehoach.tt17;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.nghiepvu.model.InterfaceRequest;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiBaoYTeHangHaiUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiCacGiayToPhaiXuatTrinhUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiChungUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiDanhSachHangHoaNguyHiemUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiDanhSachHanhKhachUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiDanhSachThuyenVienUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiDuTruCuaTauUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiGiayPhepRoiCangBoGTVTUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiHanhLyThuyenVienUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiKetQuaPheDuyetHoSoUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiKiemDichDongVatUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiKiemDichThucVatUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiLenhDieuDongUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiThongBaoUtils;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiBanKhai;
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;

import com.fds.nsw.kernel.exception.SystemException;


import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TT17XuLyNghiepVuUtils {


	public static final int XU_LY_THAT_BAI = -1;
	public static final int XU_LY_THANH_CONG = 1;

	public static int doActionHoSo(long documentName, int documentYear, int actionType, int orginStatus,
			int orginDocumentStatusCode, int desStatus, User user, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, boolean isKeHoach) {
		int result = XU_LY_THAT_BAI;

		try {

			String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");
			
			if (desStatus == ChuyenDichTrangThaiUtils.KE_HOACH_DA_TIEP_NHAN) {
				// cap nhat trang thai toan bo ban khai
				result = thayDoiTrangThaiBanKhaiKeHoach(documentName, documentYear, orginStatus, orginDocumentStatusCode,
						desStatus, user, uploadPortletRequest, actionRequest, ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI);

				// chagne documentStatusCode to DA_TIEP_NHAN
				if (desStatus == ChuyenDichTrangThaiUtils.KE_HOACH_DA_TIEP_NHAN && orginDocumentStatusCode == 0) {
					TempDocument tempDocument = TempDocumentLocalServiceUtil
							.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);

					tempDocument.setDocumentStatusCode(ChuyenDichTrangThaiUtils.THU_TUC_DA_TIEP_NHAN);

					TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
				}
				
				// Tich hop send message 99-21
				TT17TichHopMessageUtils.message_99_21(user.getEmailAddress(), uploadPortletRequest, actionRequest,
						documentName, documentYear, yKienReject);
				
			} else if (desStatus == ChuyenDichTrangThaiUtils.KE_HOACH_TU_CHOI_TIEP_NHAN && isKeHoach) {
				// cap nhat trang thai toan bo ban khai
				result = thayDoiTrangThaiBanKhaiKeHoach(documentName, documentYear, orginStatus, orginDocumentStatusCode,
						desStatus, user, uploadPortletRequest, actionRequest, ChuyenDichTrangThaiUtils.BAN_KHAI_TU_CHOI_BAN_KHAI);

				// Tich hop send message 99-22
				boolean resultMethod = TT17TichHopMessageUtils.message_99_22(user.getEmailAddress(),
						uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);

				if (resultMethod == true) {

					BusinessUtils.insertOrUpdateResultMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
							BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));

					BusinessUtils.insertResultHistoryMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
							BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
					
					// ResultNotification
					BanKhaiLenhDieuDongUtils.insertOrUpdateResultNotification(documentName, documentYear, user.getEmailAddress(), yKienReject,
							MessageType.BO_SUNG_KE_HOACH);
				}
			} else if (desStatus == ChuyenDichTrangThaiUtils.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {

				if (desStatus == TrangThaiBanKhaiNhapCanh.CHO_TIEP_NHAN
						|| desStatus == TrangThaiBanKhaiNhapCanh.DA_TIEP_NHAN
						|| desStatus == TrangThaiBanKhaiNhapCanh.CHO_CAP_LENH_DIEU_DONG
						|| desStatus == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {

					if (yKienReject == null || yKienReject.length() == 0) {
						yKienReject = "System";
					}
					
					// Tich hop send message 99-27
					boolean resultMethod = TT17TichHopMessageUtils.message_99_27(user.getEmailAddress(),
							uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);

					BusinessUtils.insertOrUpdateResultMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_THONG_BAO_BO_XUNG,
							BusinessUtils.getRemarkBoSung(user.getEmailAddress(), yKienReject));

					BusinessUtils.insertResultHistoryMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_THONG_BAO_BO_XUNG,
							BusinessUtils.getRemarkBoSung(user.getEmailAddress(), yKienReject));

					// ResultNotification
					BanKhaiLenhDieuDongUtils.insertOrUpdateResultNotification(documentName, documentYear, user.getEmailAddress(), yKienReject,
							MessageType.BO_SUNG_KE_HOACH);
				}

			} else if (desStatus == ChuyenDichTrangThaiUtils.THU_TUC_YEU_CAU_SUA_DOI_BO_SUNG && !isKeHoach) {

				if (yKienReject == null || yKienReject.length() == 0) {
					yKienReject = "System";
				}

				// Tich hop send message 99-28
				boolean resultMethod = TT17TichHopMessageUtils.message_99_28(user.getEmailAddress(),
						uploadPortletRequest, actionRequest, documentName, documentYear, yKienReject);

				if (resultMethod == true) {
					BusinessUtils.insertOrUpdateResultMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_THONG_BAO_BO_XUNG,
							BusinessUtils.getRemarkBoSung(user.getEmailAddress(), yKienReject));

					BusinessUtils.insertResultHistoryMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_THONG_BAO_BO_XUNG,
							BusinessUtils.getRemarkBoSung(user.getEmailAddress(), yKienReject));

					// ResultNotification
					BanKhaiLenhDieuDongUtils.insertOrUpdateResultNotification(documentName, documentYear,
							user.getEmailAddress(), yKienReject, MessageType.BO_SUNG_THU_TUC);
				}

			} else if (desStatus == ChuyenDichTrangThaiUtils.THU_TUC_CHO_PHE_DUYET_HOAN_THANH_THU_TUC) {

				result = thayDoiTrangThaiBanKhaiThuTuc(documentName, documentYear, orginStatus, orginDocumentStatusCode,
						desStatus, user, uploadPortletRequest, actionRequest, ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI);
				
				// Tich hop send message 99-21
				TT17TichHopMessageUtils.message_99_21(user.getEmailAddress(), uploadPortletRequest, actionRequest,
						documentName, documentYear, yKienReject);

			} else if (desStatus == ChuyenDichTrangThaiUtils.THU_TUC_HUY_THU_TUC && actionType != MessageType.FUNCTION_TU_CHOI_HO_SO) {

				// Tich hop send message 99-24
				boolean resultMethod = TT17TichHopMessageUtils.message_99_24(user.getEmailAddress(), uploadPortletRequest, actionRequest,
						documentName, documentYear, yKienReject);

				if (resultMethod == true) {

					BusinessUtils.insertOrUpdateResultMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
							BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));

					BusinessUtils.insertResultHistoryMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
							BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
					
					// ResultNotification
					BanKhaiLenhDieuDongUtils.insertOrUpdateResultNotification(documentName, documentYear,
							user.getEmailAddress(), yKienReject, MessageType.BO_SUNG_THU_TUC);
				}
				
			} else if (desStatus == ChuyenDichTrangThaiUtils.THU_TUC_HUY_THU_TUC && actionType == MessageType.FUNCTION_TU_CHOI_HO_SO) {

				// Tich hop send message 99-22
				boolean resultMethod = TT17TichHopMessageUtils.message_99_22(user.getEmailAddress(), uploadPortletRequest, actionRequest,
						documentName, documentYear, yKienReject);

				if (resultMethod == true) {

					BusinessUtils.insertOrUpdateResultMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
							BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));

					BusinessUtils.insertResultHistoryMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
							BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
					
					// ResultNotification
					BanKhaiLenhDieuDongUtils.insertOrUpdateResultNotification(documentName, documentYear,
							user.getEmailAddress(), yKienReject, MessageType.BO_SUNG_THU_TUC);
				}
			} else if (desStatus == ChuyenDichTrangThaiUtils.THU_TUC_TAM_DUNG_LAM_THU_TUC_DIEN_TU) {

				// Tich hop send message 99-24 (99 - 25)
				boolean resultMethod = TT17TichHopMessageUtils.message_99_25(user.getEmailAddress(), uploadPortletRequest, actionRequest,
						documentName, documentYear, yKienReject);

				if (resultMethod == true) {

					BusinessUtils.insertOrUpdateResultMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_TAM_DUNG,
							BusinessUtils.getRemarkTamDung(user.getEmailAddress(), yKienReject));

					BusinessUtils.insertResultHistoryMinistry((int)documentName
, documentYear,
							MessageType.FUNCTION_TAM_DUNG,
							BusinessUtils.getRemarkTamDung(user.getEmailAddress(), yKienReject));
					
					// ResultNotification
					BanKhaiLenhDieuDongUtils.insertOrUpdateResultNotification(documentName, documentYear,
							user.getEmailAddress(), yKienReject, MessageType.BO_SUNG_THU_TUC);
				}
				
			} else if (desStatus == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {

				// Tich hop send message 99-23
				TT17TichHopMessageUtils.message_99_23(user.getEmailAddress(), uploadPortletRequest, actionRequest,
						documentName, documentYear, yKienReject);

			}
			result = XU_LY_THANH_CONG;
		} catch (Exception e) {
			log.error(e.getMessage());
			result = XU_LY_THAT_BAI;
		}

		return result;
	}

	public static int doActionBanKhai(long documentName, int documentYear, int actionType, int orginStatus,
			int orginDocumentStatusCode, int desStatus, User user, int messageType, String requestCode,
			boolean isKeHoach, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {
		int result = XU_LY_THAT_BAI;
		try {

			// Thay doi trang thai bang khai
			if (messageType == MessageType.THONG_BAO_TAU_ROI_CANG) {

				BanKhaiThongBaoUtils action = new BanKhaiThongBaoUtils();
				
				result = action._thongBao(requestCode, documentName, documentYear, messageType, desStatus, user, uploadPortletRequest,
						actionRequest);

			} else if (messageType == MessageType.BAN_KHAI_CHUNG) {
				
				BanKhaiChungUtils action = new BanKhaiChungUtils();
				
				result = action._banKhaiChung(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
				// Tich hop send message

			} else if (messageType == MessageType.XAC_NHAN_HOAN_THANH_THU_TUC) {
				
				BanKhaiKetQuaPheDuyetHoSoUtils action = new BanKhaiKetQuaPheDuyetHoSoUtils();
				
				result = action._xacNhanHoanThanhThuTuc(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
				// Tich hop send message

			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN) {
				
				BanKhaiDanhSachThuyenVienUtils action = new BanKhaiDanhSachThuyenVienUtils();
				
				result = action._banKhaiDanhSachThuyenVien(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
				// Tich hop send message

			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH) {
				
				BanKhaiDanhSachHanhKhachUtils action = new BanKhaiDanhSachHanhKhachUtils();
				
				result = action._banKhaiDanhSachHanhKhach(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
				// Tich hop send message

			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM) {
				
				BanKhaiDanhSachHangHoaNguyHiemUtils action = new BanKhaiDanhSachHangHoaNguyHiemUtils();
				
				result = action._banKhaiDanhSachHangHoaNguyHiem(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
				// Tich hop send message

			} else if (messageType == MessageType.BAN_KHAI_DU_TRU_CUA_TAU) {
				
				BanKhaiDuTruCuaTauUtils action = new BanKhaiDuTruCuaTauUtils();
				
				result = action._banKhaiDuTruCuaTau(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
				// Tich hop send message

			} else if (messageType == MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN) {
				
				BanKhaiHanhLyThuyenVienUtils action = new BanKhaiHanhLyThuyenVienUtils();
				
				result = action._banKhaiHanhLyThuyenVien(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
				// Tich hop send message

			} else if (messageType == MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI) {
				
				BanKhaiBaoYTeHangHaiUtils action = new BanKhaiBaoYTeHangHaiUtils();
				
				result = action._banKhaiBaoYTeHangHai(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
				// Tich hop send message

			} else if (messageType == MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT) {
				
				BanKhaiKiemDichThucVatUtils action = new BanKhaiKiemDichThucVatUtils();
				
				result = action._banKhaiKiemDichThucVat(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
				// Tich hop send message

			} else if (messageType == MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT) {
				
				BanKhaiKiemDichDongVatUtils action = new BanKhaiKiemDichDongVatUtils();
				
				result = action._banKhaiKiemDichDongVat(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
				// Tich hop send message

			} else if (messageType == MessageType.CAC_GIAY_TO_PHAI_XUAT_TRINH) {
				
				BanKhaiCacGiayToPhaiXuatTrinhUtils action = new BanKhaiCacGiayToPhaiXuatTrinhUtils();
				
				result = action._cacGiayToXuatTrinh(requestCode, documentName, documentYear, actionType, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
			} else if (messageType == MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH) {
				
				BanKhaiGiayPhepRoiCangBoGTVTUtils action = new BanKhaiGiayPhepRoiCangBoGTVTUtils();
				
				result = action._giayPhepRoiCangGTVT(requestCode, documentName, documentYear, messageType, desStatus, user,
						uploadPortletRequest, actionRequest);
			}

			if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_DA_DUYET) {
				BusinessUtils.updateResultDeclaration(messageType, documentName, documentYear,
						ChuyenDichTrangThaiUtils.BAN_KHAI_DA_DUYET);
			} else if (desStatus == ChuyenDichTrangThaiUtils.BAN_KHAI_TU_CHOI_BAN_KHAI) {
				BusinessUtils.updateResultDeclaration(messageType, documentName, documentYear,
						ChuyenDichTrangThaiUtils.BAN_KHAI_TU_CHOI_BAN_KHAI);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			result = XU_LY_THAT_BAI;
		}
		return result;
	}

	private static int thayDoiTrangThaiBanKhaiKeHoach(long documentName, int documentYear, int orginStatus,
			int orginDocumentStatusCode, int desStatus, User user, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, int trangThaiBanKhai) throws SystemException {
		int result = XU_LY_THAT_BAI;

		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName,
				documentYear);

		log.info("THAY DOI TRANG THAI TOAN BO BAN KHAI KHI TIEP NHAN HO SO: documentName= " + documentName
				+ " & documentYear=" + documentYear + " orginStatus: " + orginStatus);

		List<ResultDeclaration> lstDeclaration = null;
		
		lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				MessageType.THONG_BAO_TAU_ROI_CANG, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
		if (lstDeclaration != null && lstDeclaration.size() > 0) {
			ResultDeclaration resultDeclaration = lstDeclaration.get(0);
			TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
					.findTempNoTiceShipMessageByRequestCode(resultDeclaration.getRequestCode());
			
			BusinessUtils.updateResultDeclaration(MessageType.THONG_BAO_TAU_ROI_CANG,
					tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
					trangThaiBanKhai);
			
			if (Validator.isNotNull(tempNoticeShipMessage)) {

				InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
						.findInterfaceRequestByRequestCode(tempNoticeShipMessage.getRequestCode());
				
				if (interfaceRequest != null) {
					
					String yKienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");
					
					if (trangThaiBanKhai == ChuyenDichTrangThaiUtils.BAN_KHAI_TU_CHOI_BAN_KHAI) {
						interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(user.getEmailAddress(), yKienReject));
					} else {
						interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(user.getEmailAddress()));
					}
					InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
				}
				
				tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
				
			}
		}
		
		result = XU_LY_THANH_CONG;

		return result;
	}

	private static int thayDoiTrangThaiBanKhaiThuTuc(long documentName, int documentYear, int orginStatus,
			int orginDocumentStatusCode, int desStatus, User user, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, int trangThaiBanKhai) throws SystemException {
		int result = XU_LY_THAT_BAI;

//		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName,
//				documentYear);
//
//		log.info("THAY DOI TRANG THAI TOAN BO BAN KHAI KHI TIEP NHAN HO SO: documentName= " + documentName
//				+ " & documentYear=" + documentYear + " orginStatus: " + orginStatus);
//
//		List<ResultDeclaration> lstDeclaration = null;
//		
//		lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
//				MessageType.BAN_KHAI_CHUNG, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
//		
//		if (lstDeclaration != null && lstDeclaration.size() > 0) {
//			ResultDeclaration resultDeclaration = lstDeclaration.get(0);
//			
//			TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
//					.findTempNoTiceShipMessageByRequestCode(resultDeclaration.getRequestCode());
//			
//			BusinessUtils.updateResultDeclaration(MessageType.BAN_KHAI_CHUNG,
//					tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
//					trangThaiBanKhai);
//			
//			if (Validator.isNotNull(tempNoticeShipMessage)) {
//
//				tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
//				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
//				
//			}
//		}
//		
//		lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
//				MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
//		
//		if (lstDeclaration != null && lstDeclaration.size() > 0) {
//			ResultDeclaration resultDeclaration = lstDeclaration.get(0);
//			
//			TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
//					.findTempNoTiceShipMessageByRequestCode(resultDeclaration.getRequestCode());
//			
//			BusinessUtils.updateResultDeclaration(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN,
//					tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
//					trangThaiBanKhai);
//			
//			if (Validator.isNotNull(tempNoticeShipMessage)) {
//
//				tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
//				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
//				
//			}
//		}
//		
//		lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
//				MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
//		
//		if (lstDeclaration != null && lstDeclaration.size() > 0) {
//			ResultDeclaration resultDeclaration = lstDeclaration.get(0);
//			
//			TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
//					.findTempNoTiceShipMessageByRequestCode(resultDeclaration.getRequestCode());
//			
//			BusinessUtils.updateResultDeclaration(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH,
//					tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
//					trangThaiBanKhai);
//			
//			if (Validator.isNotNull(tempNoticeShipMessage)) {
//
//				tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
//				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
//				
//			}
//		}
//		
//		lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
//				MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
//		
//		if (lstDeclaration != null && lstDeclaration.size() > 0) {
//			ResultDeclaration resultDeclaration = lstDeclaration.get(0);
//			
//			TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
//					.findTempNoTiceShipMessageByRequestCode(resultDeclaration.getRequestCode());
//			
//			BusinessUtils.updateResultDeclaration(MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM,
//					tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
//					trangThaiBanKhai);
//			
//			if (Validator.isNotNull(tempNoticeShipMessage)) {
//
//				tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
//				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
//				
//			}
//		}
		
		result = XU_LY_THANH_CONG;

		return result;
	}
	


}
