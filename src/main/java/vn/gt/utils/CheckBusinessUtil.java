package vn.gt.utils;



import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.nghiepvu.model.DmGtStatus;
import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.IssueAcceptance;
import com.fds.nsw.nghiepvu.model.TempCargoDeclaration;
import com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;
import com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempPassengerList;
import com.fds.nsw.nghiepvu.model.TempPlantQuarantine;
import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
import com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration;





import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueAcceptanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewEffectsDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDangerousGoodsNanifestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDeclarationOfHealthLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPassengerListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmRepresentativeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmPort;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import com.fds.nsw.nghiepvu.model.DmRepresentative;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiBanKhai;
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiQuaCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiXuatCanh;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.ConfirmationOfArrival;
import vn.nsw.model.ShipSecurityNotification;
import vn.nsw.model.GeneralDeclaration;
import vn.nsw.model.NoticeOfArrival;

import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.exception.SystemException;


import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;

@Slf4j
public class CheckBusinessUtil {
	
	
	
		
	// Kiem tra hien thi cac chuc nang.
	public static int THAO_TAC_BAN_KHAI_XEM = 1;
	
	public static int THAO_TAC_BAN_DONG_DAU_XONG = 45;
	
	public static int THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU = 42;
	
	public static int THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_QC = 43;
	public static int THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_XC = 44;
	public static int THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_VC = 41;
	
	
	public static int THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO = 32;	
	public static int THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_QC = 33;
	public static int THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_XC = 34;
	public static int THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_VC = 31;
	public static int THAO_TAC_BAN_KY_SO_XONG_CHO_DONG_DAU = 35;	
	
	public static int THAO_TAC_THU_TUC_DUET_KYSO = 111;
	
	public static int THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI = 2;
	public static int THAO_TAC_BAN_KHAI_DOI_CHIEU = 3;
	public static int THAO_TAC_BAN_KHAI_CAP_BAN_KHAI = 4;
	public static int THAO_TAC_BAN_KHAI_OTHER = 5;
	public static int THAO_TAC_BAN_KHAI_HOAN_THANH = 6;
	public static int THAO_TAC_KIEM_DUYET_HO_SO = 7;
	public static int THAO_TAC_BAN_KHAI_SUA = 8;
	public static int THAO_TAC_BAN_KHAI_CAP_LAI = 9;
	public static int THAO_TAC_BAN_KHAI_CAP_LAI_LAN_THU_N = 10;
	public static int THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP = 11;
	public static int THAO_TAC_BAN_KHAI_CAP_LAI_OR_HUY = 12;
	public static int THAO_TAC_CHUYEN_GIAO_DIEN_INSERT_LAN_THU_N = 13;
	public static int THAO_TAC_CHUYEN_GIAO_DIEN_UPDATE_LAN_THU_N = 14;
	public static int THAO_TAC_BAN_KHAI_SUADOI_BOSUNG = 15;
	
	//TODO KE HOACH checkRoleDisplayButton
	/**
	 * KE HOACH checkRoleDisplayButton
	 * */
	public static int checkRoleDisplayButton(String loaiThuTuc, int messageType, String vaiTro, TempDocument temp, String requestCode,
			HttpServletRequest actionRequest) {
		//
		if (vaiTro != null && PageType.VAN_THU.compareTo(vaiTro.trim()) == 0) {
			return checkVanThuDongDau(loaiThuTuc, messageType, vaiTro, temp, requestCode, actionRequest);
		}else if (vaiTro != null && PageType.LANH_DAO.compareTo(vaiTro.trim()) == 0) {
			return checkLanhDaoKySo(loaiThuTuc, messageType, vaiTro, temp, requestCode, actionRequest);
		} else if (MessageType.LOAT_THU_TUC_NHAP_CANH.compareTo(loaiThuTuc.trim()) == 0 
				|| ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI.compareTo(loaiThuTuc.trim()) == 0
				|| ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO.compareTo(loaiThuTuc.trim()) == 0
				|| ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI.compareTo(loaiThuTuc.trim()) == 0) {
			return checkNhapCanh(loaiThuTuc, messageType, vaiTro, temp, requestCode, actionRequest);
		}else if (MessageType.LOAT_THU_TUC_VAO_CANG.compareTo(loaiThuTuc.trim()) == 0) { //inland
			return checkVaoCang(loaiThuTuc, messageType, vaiTro, temp, requestCode, actionRequest);
			
		} else if (MessageType.LOAT_THU_TUC_XUAT_CANH.compareTo(loaiThuTuc.trim()) == 0
				|| ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI.compareTo(loaiThuTuc.trim()) == 0
				|| ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI.compareTo(loaiThuTuc.trim()) == 0
				|| ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI.compareTo(loaiThuTuc.trim()) == 0) {
			return checkXuatCanh(loaiThuTuc, messageType, vaiTro, temp, requestCode, actionRequest);
			
		} else if (MessageType.LOAT_THU_TUC_QUA_CANH.compareTo(loaiThuTuc.trim()) == 0) {
			return checkQuaCanh(loaiThuTuc, messageType, vaiTro, temp, requestCode, actionRequest);
		} else if (MessageType.LOAT_THU_TUC_ROI_CANG.compareTo(loaiThuTuc.trim()) == 0){
			return checkRoiCang(loaiThuTuc, messageType, vaiTro, temp, requestCode, actionRequest);
		} else if (MessageType.LOAT_THU_TUC_ROI_CANG_PTTND.compareTo(loaiThuTuc.trim()) == 0){
			return checkRoiCang(loaiThuTuc, messageType, vaiTro, temp, requestCode, actionRequest);
		} else if (MessageType.LOAT_THU_TUC_VAO_CANG_PTTND.compareTo(loaiThuTuc.trim()) == 0){
			return checkVaoCangPTTND(loaiThuTuc, messageType, vaiTro, temp, requestCode, actionRequest);
		}
		

		
		return THAO_TAC_BAN_KHAI_XEM;
	}
	
	private static int checkVanThuDongDau(String loaiThuTuc, int messageType, String vaiTro, TempDocument tempDocument, String requestCode,
			HttpServletRequest actionRequest) {
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		log.info("=====checkVanThuDongDau==VAITRO==" + vaiTro + "==REQUEST_CODE==" + requestCode + "==MESSAGE_TYPE==" + messageType);
		
		if (vaiTro != null && PageType.VAN_THU.compareTo(vaiTro.trim()) == 0) {			
			switch (messageType) {
			
				case MessageType.LENH_DIEU_DONG:
					log.info("====checkVanThuDongDau====LENH DIEU DONG====");
					result = checkLenhDieuDong(tempDocument, requestCode, actionRequest);
					break;
				
				case MessageType.PTTND_GIAY_PHEP_VAO_CANG:
					log.info("====checkVanThuDongDau====PTTND_GIAY_PHEP_VAO_CANG 81====");
					result = checkGiayPhepVaoCang(tempDocument, requestCode, actionRequest);
					break;

				case MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN:
					log.info("====checkVanThuDongDau====GIAY_PHEP_ROI_CANG_CHO_TAU_DEN 61====");
					result = checkGiayPhepRoiCang(tempDocument, requestCode, actionRequest);
					break;
				case MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH:
					log.info("====checkVanThuDongDau====GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH 60====");
					result = checkGiayPhepRoiCang(tempDocument, requestCode, actionRequest);
					break;
				case MessageType.GIAY_PHEP_QUA_CANH:
					log.info("====checkVanThuDongDau====GIAY_PHEP_QUA_CANH====");
					result = checkGiayPhepQuaCanh(tempDocument, requestCode, actionRequest);
					break;
				
			}
		} 

		if ((result == THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU)
				|| (result == THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_QC) 
				|| (result == THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_XC)
				|| (result == THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_VC)) {
			// du dieu kien dong dau
		} else {
			result = THAO_TAC_BAN_KHAI_XEM;
		}
		
		return result;
	}
	
	private static int checkLanhDaoKySo(String loaiThuTuc, int messageType, String vaiTro, TempDocument tempDocument, String requestCode,
			HttpServletRequest actionRequest) {
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		log.info("=====checkLanhDaoKySo==VAITRO==" + vaiTro + "==REQUEST_CODE==" + requestCode + "==MESSAGE_TYPE==" + messageType);
		
		if (vaiTro != null && PageType.LANH_DAO.compareTo(vaiTro.trim()) == 0) {			
			switch (messageType) {
			
				case MessageType.LENH_DIEU_DONG:
					log.info("====checkLanhDaoKySo====LENH DIEU DONG====");
					result = checkKySoLenhDieuDong(tempDocument, requestCode, actionRequest);
					break;
				
				case MessageType.PTTND_GIAY_PHEP_VAO_CANG:
					log.info("====checkLanhDaoKySo====PTTND_GIAY_PHEP_VAO_CANG 81====");
					result = checkKySoGiayPhepVaoCang(tempDocument, requestCode, actionRequest);
					break;

				case MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN:
					log.info("====checkLanhDaoKySo====GIAY_PHEP_ROI_CANG_CHO_TAU_DEN 61====");
					result = checkKySoGiayPhepRoiCang(tempDocument, requestCode, actionRequest);
					break;
				case MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH:
					log.info("====checkLanhDaoKySo====GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH 60====");
					result = checkKySoGiayPhepRoiCang(tempDocument, requestCode, actionRequest);
					break;
				case MessageType.GIAY_PHEP_QUA_CANH:
					log.info("====checkLanhDaoKySo====GIAY_PHEP_QUA_CANH====");
					result = checkKySoGiayPhepQuaCanh(tempDocument, requestCode, actionRequest);
					break;
				
			}
		} 

		if ((result == THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO)
				|| (result == THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_QC) 
				|| (result == THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_XC)
				|| (result == THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_VC)) {
			// du dieu kien ky so
		} else {
			result = THAO_TAC_BAN_KHAI_XEM;
		}
		
		return result;
	}
	
	public static int checkRoleDisplayButtonDoiChieuHsBoSung(String loaiThuTuc, int messageType, String vaiTro, TempDocument temp, String requestCode) throws SystemException {
		
		if (temp == null) { temp = new TempDocument(); }
		
		if (loaiThuTuc == null || loaiThuTuc.trim().length() == 0 || MessageType.LOAT_THU_TUC_NHAP_CANH.compareTo(loaiThuTuc.trim()) == 0) {
			return checkNhapCanhDoiChieuHsBoSung(loaiThuTuc, messageType, vaiTro, temp, requestCode);
			
		} else if (MessageType.LOAT_THU_TUC_XUAT_CANH.compareTo(loaiThuTuc.trim()) == 0) {
			return checkXuatCanhDoiChieuHsBoSung(loaiThuTuc, messageType, vaiTro, temp, requestCode);
			
		} else if (MessageType.LOAT_THU_TUC_QUA_CANH.compareTo(loaiThuTuc.trim()) == 0) {
			return checkQuaCanhDoiChieuHsBoSung(loaiThuTuc, messageType, vaiTro, temp, requestCode);
		}		
		return THAO_TAC_BAN_KHAI_XEM;
	}
	
	private static int checkNhapCanhDoiChieuHsBoSung(String loaiThuTuc, int messageType, String vaiTro, TempDocument temp, String requestCode) throws SystemException {
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		log.info("=====checkNhapCanhDoiChieuHsBoSung==VAITRO==" + vaiTro + "==REQUEST_CODE==" + requestCode + "==MESSAGE_TYPE==" + messageType);
		if (vaiTro != null && PageType.THU_TUC.compareTo(vaiTro.trim()) == 0) {
			
			//check truong hop doi chieu ho so
			switch (messageType) {
				case MessageType.BAN_KHAI_AN_NINH_TAU_BIEN:
					TempShipSecurityMessage shipSecurity = TempShipSecurityMessageLocalServiceUtil.getLastByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
					if (shipSecurity != null && shipSecurity.getRequestState() == 3) {
						result = THAO_TAC_BAN_KHAI_SUADOI_BOSUNG;
					}
					break;
				case MessageType.BAN_KHAI_CHUNG:
					TempGeneralDeclaration general = TempGeneralDeclarationLocalServiceUtil.getLastByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
					if (general != null && general.getRequestState() == 3) {
						result = THAO_TAC_BAN_KHAI_SUADOI_BOSUNG;
					}
					break;
				case MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN:
					TempCrewList crew = TempCrewListLocalServiceUtil.getLastByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
					if (crew != null && crew.getRequestState() == 3) {
						result = THAO_TAC_BAN_KHAI_SUADOI_BOSUNG;
					}
					break;
				case MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM:
					TempDangerousGoodsManifest good = TempDangerousGoodsNanifestLocalServiceUtil.getLastByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
					if (good != null && good.getRequestState() == 3) {
						result = THAO_TAC_BAN_KHAI_SUADOI_BOSUNG;
					}
					break;
			}
		}
		return result;
	}
	
	//TODO button Ä�á»‘i chiáº¿u há»“ sÆ¡
	private static int checkXuatCanhDoiChieuHsBoSung(String loaiThuTuc, int messageType, String vaiTro, TempDocument temp, String requestCode) throws SystemException {
		int result = THAO_TAC_BAN_KHAI_XEM;
		if (vaiTro != null && PageType.THU_TUC.compareTo(vaiTro.trim()) == 0) {
			// Nhin moi ban khai tru ban khai chung.
			switch (messageType) {
				case MessageType.BAN_KHAI_CHUNG:
					TempGeneralDeclaration general = TempGeneralDeclarationLocalServiceUtil.getLastByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
					if (general != null && general.getRequestState() == 3) {
						result = THAO_TAC_BAN_KHAI_SUADOI_BOSUNG;
					}
					break;
				case MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN:
					TempCrewList crew = TempCrewListLocalServiceUtil.getLastByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
					if (crew != null && crew.getRequestState() == 3) {
						result = THAO_TAC_BAN_KHAI_SUADOI_BOSUNG;
					}
					break;
			}
		}
		return result;
	}
	
	private static int checkQuaCanhDoiChieuHsBoSung(String loaiThuTuc, int messageType, String vaiTro, TempDocument temp, String requestCode) throws SystemException {
		int result = THAO_TAC_BAN_KHAI_XEM;
		if (vaiTro != null && PageType.THU_TUC.compareTo(vaiTro.trim()) == 0) {
			// Nhin moi ban khai tru ban khai chung.
			switch (messageType) {
				case MessageType.BAN_KHAI_AN_NINH_TAU_BIEN:
					TempShipSecurityMessage shipSecurity = TempShipSecurityMessageLocalServiceUtil.getLastByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
					if (shipSecurity != null && shipSecurity.getRequestState() == 3) {
						result = THAO_TAC_BAN_KHAI_SUADOI_BOSUNG;
					}
					break;
				case MessageType.BAN_KHAI_CHUNG:
					TempGeneralDeclaration general = TempGeneralDeclarationLocalServiceUtil.getLastByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
					if (general != null && general.getRequestState() == 3) {
						result = THAO_TAC_BAN_KHAI_SUADOI_BOSUNG;
					}
					break;
				case MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN:
					TempCrewList crew = TempCrewListLocalServiceUtil.getLastByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
					if (crew != null && crew.getRequestState() == 3) {
						result = THAO_TAC_BAN_KHAI_SUADOI_BOSUNG;
					}
					break;
				case MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH:
					TempPassengerList passenger = TempPassengerListLocalServiceUtil.getLastByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
					if (passenger != null && passenger.getRequestState() == 3) {
						result = THAO_TAC_BAN_KHAI_SUADOI_BOSUNG;
					}
					break;
			}
		}
		return result;
	}


	private static int checkNhapCanh(String loaiThuTuc, int messageType, String vaiTro, TempDocument tempDocument, String requestCode,
			HttpServletRequest actionRequest) {
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		log.info("=====checkNhapCanh==VAITRO==" + vaiTro + "==REQUEST_CODE==" + requestCode + "==MESSAGE_TYPE==" + messageType);
		
		if (vaiTro != null && PageType.KE_HOACH.compareTo(vaiTro.trim()) == 0) {
			// Chi co the dung ban thong bao chung.
			switch (messageType) {
				case MessageType.THONG_BAO_TAU_DEN_CANG:
					result = checkBanKhaiThongBao(tempDocument, requestCode, messageType);
					break;
					
				case MessageType.BAN_KHAI_AN_NINH_TAU_BIEN:
					result = checkBanKhaiAnNinh(tempDocument, requestCode);
					break;
					
				case MessageType.XAC_BAO_TAU_DEN_CANG:
					result = checkBanKhaiXacBao(tempDocument, requestCode, MessageType.XAC_BAO_TAU_DEN_CANG);
					break;
					
				case MessageType.LENH_DIEU_DONG:
					log.info("====NHAP CANH====LENH DIEU DONG====");
					result = checkLenhDieuDong(tempDocument, requestCode, actionRequest);
					break;
					
				case PageType.THANH_PHAN_HO_SO:
					log.info("====NHAP CANH========THANH_PHAN_HO_SO");
					result = checkThanhPhanHoSoKeHoach(tempDocument, requestCode);
					break;
			}
		} else if (vaiTro != null && PageType.THU_TUC.compareTo(vaiTro.trim()) == 0) {
			
			//check truong hop doi chieu ho so
			switch (messageType) {
				case MessageType.BAN_KHAI_AN_NINH_TAU_BIEN:
					result = checkBanKhaiAnNinh(tempDocument, requestCode);
					break;
				case PageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC:
					result = checkHoanThanh(tempDocument, requestCode);
					break;
				case PageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH:
					result = checkCacGiayToPhaiXuatTrinh(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_CHUNG:
					result = checkBanKhaiChung(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN:
					result = checkBanKhaiDanhSachThuyenVien(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				case MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM:
					result = checkBanKhaiDanhSachHangHoaNguyHiem(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DU_TRU_CUA_TAU:
					result = checkBanKhaiDuTruTau(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN:
					result = checkBanKhaiHanhLyThuyenVien(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI:
					result = checkBanKhaiYTeHangHai(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT:
					result = checkBanKhaiKiemDichThucVat(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT:
					result = checkBanKhaiKiemDichDongVat(tempDocument, requestCode);
					break;
					
				case PageType.THANH_PHAN_HO_SO:
					result = checkThanhPhanHoSoThuTuc(tempDocument, requestCode);
					break;
			}
		}
		
		return result;
	}
	
	
	//TODO inland checkVaoCang
	private static int checkVaoCang(String loaiThuTuc, int messageType, String vaiTro, TempDocument tempDocument, String requestCode,
			HttpServletRequest actionRequest) {  
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		log.info("=====checkVaoCang==VAITRO==" + vaiTro + "==REQUEST_CODE==" + requestCode + "==MESSAGE_TYPE==" + messageType);
		
		if (vaiTro != null && PageType.KE_HOACH.compareTo(vaiTro.trim()) == 0) {
			log.info("=============vao checkVaoCang KEHOACH======= IF");
			switch (messageType) {
				case MessageType.THONG_BAO_TAU_DEN_CANG:
					result = checkBanKhaiThongBao(tempDocument, requestCode, messageType);
					break;
					
				case MessageType.BAN_KHAI_AN_NINH_TAU_BIEN:
					result = checkBanKhaiAnNinh(tempDocument, requestCode);
					break;
					
				case MessageType.LENH_DIEU_DONG:
					log.info("====VAO CANG====LENH DIEU DONG====");
					result = checkLenhDieuDong(tempDocument, requestCode, actionRequest);
					log.info("=============vao LENH_DIEU_DONG======= IF");
					break;
					
				case PageType.THANH_PHAN_HO_SO:
					result = checkThanhPhanHoSoKeHoach(tempDocument, requestCode);
					log.info("=============vao THANH_PHAN_HO_SO======= IF");
					break;
			}
		} else if (vaiTro != null && PageType.THU_TUC.compareTo(vaiTro.trim()) == 0) {
			
			//check truong hop doi chieu ho so
			switch (messageType) {
				case MessageType.BAN_KHAI_AN_NINH_TAU_BIEN:
					result = checkBanKhaiAnNinh(tempDocument, requestCode);
					break;
				case PageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC:
					result = checkHoanThanh(tempDocument, requestCode);
					break;
				case PageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH:
					result = checkCacGiayToPhaiXuatTrinh(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_CHUNG:
					result = checkBanKhaiChung(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN:
					result = checkBanKhaiDanhSachThuyenVien(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				case MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM:
					result = checkBanKhaiDanhSachHangHoaNguyHiem(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DU_TRU_CUA_TAU:
					result = checkBanKhaiDuTruTau(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN:
					result = checkBanKhaiHanhLyThuyenVien(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI:
					result = checkBanKhaiYTeHangHai(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT:
					result = checkBanKhaiKiemDichThucVat(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT:
					result = checkBanKhaiKiemDichDongVat(tempDocument, requestCode);
					break;
					
				case PageType.THANH_PHAN_HO_SO:
					result = checkThanhPhanHoSoThuTuc(tempDocument, requestCode);
					break;
			}
		}
		
		return result;
	}
	
	private static int checkVaoCangPTTND(String loaiThuTuc, int messageType, String vaiTro, TempDocument tempDocument, String requestCode,
			HttpServletRequest actionRequest) {
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		log.info("====checkPTTNDVaoCang====" + loaiThuTuc + "====MESSAGE_TYPE====" + messageType + "==VAI_TRO==" + vaiTro + "====");
		
		if (vaiTro != null && PageType.KE_HOACH.compareTo(vaiTro.trim()) == 0) {
			// Chi co the dung ban thong bao chung.
			if (messageType == MessageType.THONG_BAO_TAU_ROI_CANG) {
				result = checkBanKhaiThongBao(tempDocument, requestCode, messageType);
			} else if (messageType == PageType.THANH_PHAN_HO_SO) {
				result = checkThanhPhanHoSoKeHoach(tempDocument, requestCode);
			}
		} else if (vaiTro != null && PageType.THU_TUC.compareTo(vaiTro.trim()) == 0) {
			// Nhin moi ban khai tru ban khai chung.
			switch (messageType) {
				case PageType.THANH_PHAN_HO_SO:
					result = checkThanhPhanHoSoThuTuc(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_CHUNG:
					result = checkBanKhaiChung(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN:
					result = checkBanKhaiDanhSachThuyenVien(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				
				case MessageType.PTTND_GIAY_PHEP_VAO_CANG:
					result = checkGiayPhepVaoCang(tempDocument, requestCode, actionRequest);
					break;
				case PageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH:
					result = checkCacGiayToPhaiXuatTrinh(tempDocument, requestCode);
					break;
				case PageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC:
					result = checkHoanThanh(tempDocument, requestCode);
					break;
			}
		}
		return result;
	}
	
	private static int checkRoiCang(String loaiThuTuc, int messageType, String vaiTro, TempDocument tempDocument, String requestCode,
			HttpServletRequest actionRequest) {
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		log.info("====checkPTTNDRoiCang====" + loaiThuTuc + "====MESSAGE_TYPE====" + messageType + "==VAI_TRO==" + vaiTro + "====");
		
		if (vaiTro != null && PageType.KE_HOACH.compareTo(vaiTro.trim()) == 0) {
			// Chi co the dung ban thong bao chung.
			if (messageType == MessageType.THONG_BAO_TAU_ROI_CANG) {
				result = checkBanKhaiThongBao(tempDocument, requestCode, messageType);
			} else if (messageType == PageType.THANH_PHAN_HO_SO) {
				result = checkThanhPhanHoSoKeHoach(tempDocument, requestCode);
			}
		} else if (vaiTro != null && PageType.THU_TUC.compareTo(vaiTro.trim()) == 0) {
			// Nhin moi ban khai tru ban khai chung.
			switch (messageType) {
				case PageType.THANH_PHAN_HO_SO:
					result = checkThanhPhanHoSoThuTuc(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_CHUNG:
					result = checkBanKhaiChung(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN:
					result = checkBanKhaiDanhSachThuyenVien(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				case MessageType.BAN_KHAI_HANG_HOA:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				case MessageType.BAN_KHAI_DU_TRU_CUA_TAU:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				case MessageType.BAN_KHAI_HANH_LY_HANH_KHACH_ROI_TAU:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				case MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH:
					result = checkGiayPhepRoiCang(tempDocument, requestCode, actionRequest);
					break;
				case MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN:
					result = checkGiayPhepRoiCang(tempDocument, requestCode, actionRequest);
					break;				
				case PageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH:
					result = checkCacGiayToPhaiXuatTrinh(tempDocument, requestCode);
					break;
				case PageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC:
					result = checkHoanThanh(tempDocument, requestCode);
					break;
			}
		}
		return result;
	}
	
	//TODO KE HOACH QUA CANH checkRoleDisplayButton 
	/**
	 * KE HOACH QUA CANH checkRoleDisplayButton 
	 * */
	private static int checkQuaCanh(String loaiThuTuc, int messageType, String vaiTro, TempDocument tempDocument, String requestCode,
			HttpServletRequest actionRequest) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		if (vaiTro != null && PageType.KE_HOACH.compareTo(vaiTro.trim()) == 0) {
			//Chi co the dung ban thong bao chung.
			//hien thi nut: Tiáº¿p nháº­n báº£n khai OR Tá»« chá»‘i báº£n khai
			switch (messageType) {
				case MessageType.BAN_KHAI_AN_NINH_TAU_BIEN:
					result = checkBanKhaiAnNinh(tempDocument, requestCode);
					break;
				case MessageType.THONG_BAO_TAU_QUA_CANH:
					result = checkBanKhaiThongBao(tempDocument, requestCode, messageType);
					break;
				case MessageType.XAC_BAO_TAU_QUA_CANH:
					result = checkBanKhaiXacBao(tempDocument, requestCode, MessageType.XAC_BAO_TAU_QUA_CANH);
					break;
				case PageType.THANH_PHAN_HO_SO:
					result = checkThanhPhanHoSoKeHoach(tempDocument, requestCode);
					break;
				case MessageType.LENH_DIEU_DONG:
					log.info("====QUA CANH====LENH_DIEU_DONG====");
					result = checkLenhDieuDong(tempDocument, requestCode, actionRequest);
					break;
			}
		} else if (vaiTro != null && PageType.THU_TUC.compareTo(vaiTro.trim()) == 0) {
			// Nhin moi ban khai tru ban khai chung.
			switch (messageType) {
			
				case PageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH:
					result = checkCacGiayToPhaiXuatTrinh(tempDocument, requestCode);
					break;
				case PageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC:
					result = checkHoanThanh(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_AN_NINH_TAU_BIEN:
					result = checkBanKhaiAnNinh(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_CHUNG:
					result = checkBanKhaiChung(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN:
					result = checkBanKhaiDanhSachThuyenVien(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH:
					result = checkBanKhaiDanhSachHanhKhach(tempDocument, requestCode);
					break;
				case PageType.THANH_PHAN_HO_SO:
					result = checkThanhPhanHoSoThuTuc(tempDocument, requestCode);
					break;
				case MessageType.GIAY_PHEP_QUA_CANH:
					result = checkGiayPhepQuaCanh(tempDocument, requestCode, actionRequest);// THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
					break;
			}
		}
		
		return result;
	}
	
	private static int checkXuatCanh(String loaiThuTuc, int messageType, String vaiTro, TempDocument tempDocument, String requestCode,
			HttpServletRequest actionRequest) {
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		log.info("====checkXuatCanh====" + loaiThuTuc + "====MESSAGE_TYPE====" + messageType + "==VAI_TRO==" + vaiTro + "====");
		
		if (vaiTro != null && PageType.KE_HOACH.compareTo(vaiTro.trim()) == 0) {
			// Chi co the dung ban thong bao chung.
			if (messageType == MessageType.THONG_BAO_TAU_ROI_CANG) {
				result = checkBanKhaiThongBao(tempDocument, requestCode, messageType);
			} else if (messageType == PageType.THANH_PHAN_HO_SO) {
				result = checkThanhPhanHoSoKeHoach(tempDocument, requestCode);
			}
		} else if (vaiTro != null && PageType.THU_TUC.compareTo(vaiTro.trim()) == 0) {
			// Nhin moi ban khai tru ban khai chung.
			switch (messageType) {
				case PageType.THANH_PHAN_HO_SO:
					result = checkThanhPhanHoSoThuTuc(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_CHUNG:
					result = checkBanKhaiChung(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN:
					result = checkBanKhaiDanhSachThuyenVien(tempDocument, requestCode);
					break;
				case MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				case MessageType.BAN_KHAI_HANG_HOA:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				case MessageType.BAN_KHAI_DU_TRU_CUA_TAU:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				case MessageType.BAN_KHAI_HANH_LY_HANH_KHACH_ROI_TAU:
					result = THAO_TAC_BAN_KHAI_XEM;
					break;
				case MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH:
					result = checkGiayPhepRoiCang(tempDocument, requestCode, actionRequest);
					break;
				case MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN:
					result = checkGiayPhepRoiCang(tempDocument, requestCode, actionRequest);
					break;
				case PageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH:
					result = checkCacGiayToPhaiXuatTrinh(tempDocument, requestCode);
					break;
				case PageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC:
					result = checkHoanThanh(tempDocument, requestCode);
					break;
			}
		}
		return result;
	}
	
	public static int checkTrangThaiBanKhai(Header header) {
		if (header != null) {
			log.info("===header_getSubject_getFunction===" + header.getSubject().getFunction()+"===");
			log.info("===header_getSubject_getFunction_compareTo_MessageType.FUNCTION_KHAI_SUA===messageId===" + header.getReference().getMessageId());
			log.info("===header_getSubject_getFunction_compareTo_MessageType.FUNCTION_KHAI_SUA===getReference===" + header.getSubject().getReference() + "===" + header.getSubject().getDocumentYear() + "===" + header.getSubject().getDocumentType());
			
			if (header.getSubject().getFunction().equalsIgnoreCase(MessageType.FUNCTION_KHAI_BAO)) {
				
				return TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_MOI;
			} else if (header.getSubject().getFunction().equalsIgnoreCase(MessageType.FUNCTION_KHAI_SUA)) {
				
				return TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA;
			} else if (header.getSubject().getFunction().equalsIgnoreCase(MessageType.FUNCTION_BO_SUNG_CHUNG_TU)) {
				
				return TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA;
			}
		}
		return TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_MOI;
	}
	
	/**
	 * NHAP Cáº¢NH (ke hoach, thu tuc), QUA CANH (ke hoach, thu tuc)
	 * */
	public static int checkBanKhaiAnNinh(TempDocument document, String requestCode) {
		log.info("==checkBanKhaiAnNinh==requestCode==" + requestCode);
		
		int count = ResultDeclarationLocalServiceUtil.countByRequestCodeAndBusinessTypeCode(requestCode, MessageType.BAN_KHAI_AN_NINH_TAU_BIEN);
		log.info("==checkBanKhaiAnNinh==count==" + count);
		
		if (count < 1) { return THAO_TAC_BAN_KHAI_XEM; }
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		try {
			if (requestCode == null || requestCode.length() == 0) {
				return THAO_TAC_BAN_KHAI_XEM;
			}
			List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil.findByRequestCode(requestCode);
			if (results != null && results.size() > 0) {
				TempShipSecurityMessage shipSecurityMessage = results.get(0);
				
				log.info("==tempShipSecurityMessage==RequestState==" + shipSecurityMessage.getRequestState() + "==result==" + result);
				
				switch (shipSecurityMessage.getRequestState()) {
					case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN:
						result = THAO_TAC_BAN_KHAI_XEM;
						if ((document.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG) && (document.getDocumentStatusCode() == TrangThaiBanKhaiNhapCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC)) {
							result = THAO_TAC_BAN_KHAI_DOI_CHIEU;
						}
						log.info("checkBanKhaiAnNinh.TRANG_THAI_BANG_KHAI_TIEP_NHAN==" + TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN + "==result==" + result);
						break;
						
					case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_MOI:
						if ((
							//Cho tiep nhan, ko qua buoc yeu cau sua doi bo xung
							document.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_TIEP_NHAN) || (document.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG)
							
							//TODO checkBanKhaiAnNinh trang thai cho tiep nhan, roi an yeu cau sua doi bo xung - > van chap nhan, tu choi ban khai
							|| document.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG
							) {
							result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
							
						} else if ((document.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG) && (document.getDocumentStatusCode() == TrangThaiBanKhaiNhapCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC)) {
							result = THAO_TAC_BAN_KHAI_DOI_CHIEU;
						}
						log.info("checkBanKhaiAnNinh.TRANG_THAI_BANG_KHAI_MOI==" + TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_MOI + "==result==" + result);
						break;
						
					case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA:
						if ((document.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_TIEP_NHAN)
								|| (document.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG)
								|| document.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG
								) {
							result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
						} else if ((document.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG)
								&& (document.getDocumentStatusCode() == TrangThaiBanKhaiNhapCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC)) {
							result = THAO_TAC_BAN_KHAI_DOI_CHIEU;
						}
						log.info("checkBanKhaiAnNinh.TRANG_THAI_BANG_KHAI_SUA==" + TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA + "==result==" + result);
						break;
						
					case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA_DOI_BO_SUNG:
						if ((document.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_TIEP_NHAN) ||
							(document.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG)
							|| document.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG
								) {
							result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
							
						} else if ((document.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG) &&
									(document.getDocumentStatusCode() == TrangThaiBanKhaiNhapCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC)) {
							result = THAO_TAC_BAN_KHAI_DOI_CHIEU;
						}
						log.info("checkBanKhaiAnNinh.TRANG_THAI_BANG_KHAI_SUA_DOI_BO_SUNG==" + TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA_DOI_BO_SUNG + "==result==" + result);
						break;
				}
				
			} else {
				result = THAO_TAC_BAN_KHAI_XEM;
			}
		} catch (Exception e) {
			e.printStackTrace();log.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 * */
	public static int checkBanKhaiThongBao(TempDocument document, String requestCode, int messageType) {
		
		log.info("==checkBanKhaiThongBao==requestCode==" + requestCode + "==messageType==" + messageType);
		
		if (requestCode == null || requestCode.length() == 0) {
			return THAO_TAC_BAN_KHAI_XEM;
		}
		int count = ResultDeclarationLocalServiceUtil.countByRequestCodeAndBusinessTypeCode(requestCode, messageType);
		
		log.info("==checkBanKhaiThongBao==count==" + count);
		
		if (count < 1) { return THAO_TAC_BAN_KHAI_XEM; }
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		if (
			//ke hoach
			(document.getRequestState() == TrangThaiBanKhai.CHO_TIEP_NHAN) ||
			(document.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG) ||
			//TODO checkBanKhaiThongBao, ke hoach yeu cau sua doi bo sung
			(document.getRequestState() == TrangThaiBanKhai.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) ||
			//thu tuc
			(document.getDocumentStatusCode() == TrangThaiBanKhai.TU_CHOI_TIEP_NHAN) ||
			(document.getDocumentStatusCode() == TrangThaiBanKhai.THU_TUC_DA_TIEP_NHAN)) {
			
			try {
				List<TempNoticeShipMessage> results = TempNoTiceShipMessageLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempNoticeShipMessage thongBao = results.get(0);
					
					log.info("===checkBanKhaiThongBao==RequestState==" + thongBao.getRequestState());
					
					switch (thongBao.getRequestState()) {
						case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_MOI:
							result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
							break;
						case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA:
							result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
							break;
						case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA_DOI_BO_SUNG:
							result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
							break;
					}
					
				} else {
					return THAO_TAC_BAN_KHAI_XEM;
				}
			} catch (Exception e) {
				e.printStackTrace();log.error(e.getMessage());
			}
		}
		return result;
	}
	
	public static int checkBanKhaiXacBao(TempDocument document, String requestCode, int messageType) {
		
//		log.info("==checkBanKhaiXacBao==requestCode==" + requestCode + "==messageType==" + messageType);
//		
//		if (requestCode == null || requestCode.length() == 0) {
//			return THAO_TAC_BAN_KHAI_XEM;
//		}
//		int count = ResultDeclarationLocalServiceUtil.countByRequestCodeAndBusinessTypeCode(requestCode, messageType);
//		
//		log.info("==checkBanKhaiThongBao==count==" + count);
//		
//		if (count < 1) { return THAO_TAC_BAN_KHAI_XEM; }
//		
//		int result = THAO_TAC_BAN_KHAI_XEM;
//		
//		if (
//			//ke hoach
//			(document.getRequestState() == TrangThaiBanKhai.CHO_TIEP_NHAN) ||
//			(document.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG) ||
//			//TODO checkBanKhaiThongBao, ke hoach yeu cau sua doi bo sung
//			//(document.getRequestState() == TrangThaiBanKhai.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) ||
//			
//			//thu tuc
//			(document.getDocumentStatusCode() == TrangThaiBanKhai.TU_CHOI_TIEP_NHAN) ||
//			(document.getDocumentStatusCode() == TrangThaiBanKhai.THU_TUC_DA_TIEP_NHAN)) {
//			
//			try {
//				List<TempNoticeShipMessage> results = TempNoTiceShipMessageLocalServiceUtil.findByRequestCode(requestCode);
//				if (results != null && results.size() > 0) {
//					TempNoticeShipMessage thongBao = results.get(0);
//					
//					log.info("===checkBanKhaiThongBao==RequestState==" + thongBao.getRequestState());
//					
//					switch (thongBao.getRequestState()) {
//						case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_MOI:
//							result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
//							break;
//						case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA:
//							result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
//							break;
//						case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA_DOI_BO_SUNG:
//							result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
//							break;
//					}
//					
//				} else {
//					return THAO_TAC_BAN_KHAI_XEM;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();log.error(e.getMessage());
//			}
//		}
//		return result;
		log.info("==checkBanKhaiXacBao==requestCode==" + requestCode);
		
		
		// day la cai cu
		return THAO_TAC_BAN_KHAI_XEM;
		// if (requestCode == null || requestCode.length() == 0) {
		// return THAO_TAC_BAN_KHAI_XEM;
		// }
		// int count = ResultDeclarationLocalServiceUtil.countByRequestCodeAndBusinessTypeCode(requestCode,messageType);
		// if (count < 1) {
		// return THAO_TAC_BAN_KHAI_XEM;
		// }
		// int result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
		// try {
		// List<TempNoticeShipMessage> results = TempNoTiceShipMessageLocalServiceUtil.findByRequestCode(requestCode);
		// if (results != null && results.size() > 0) {
		// TempNoticeShipMessage tempNoticeShipMessage = results.get(0);
		// switch (tempNoticeShipMessage.getRequestState()) {
		// case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN:
		// result = THAO_TAC_BAN_KHAI_XEM;
		// break;
		// case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI:
		// result = THAO_TAC_BAN_KHAI_XEM;
		// break;
		// case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY:
		// result = THAO_TAC_BAN_KHAI_XEM;
		// break;
		// case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU:
		// result = THAO_TAC_BAN_KHAI_XEM;
		// break;
		// }
		//
		// } else {
		// return THAO_TAC_BAN_KHAI_XEM;
		// }
		// } catch (Exception e) {
		// e.printStackTrace();log.error(e.getMessage());
		// }
		// return result;
	}
	
	public static int checkLenhDieuDong(TempDocument tempDoc, String requestCode, HttpServletRequest actionRequest) {
		log.info("==checkLenhDieuDong==requestCode==" + requestCode);
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		List<IssueShiftingOrder> lst = IssueShiftingOrderLocalServiceUtil.findByRequestCode(requestCode);
		String isSuaLenhDieuDong = ParamUtil.getString(actionRequest, PageType.SUA_LENH_DIEU_DONG);
		

		IssueShiftingOrder order = null;
		//chinh 17/12
		if (lst != null && lst.size() > 0) {
			order = lst.get(0);
		} else {
			order = new IssueShiftingOrder();
		}
		
		log.info("====isSuaLenhDieuDong====" + isSuaLenhDieuDong);
		// // 1 LAN
		if (tempDoc.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG && (lst != null && lst.size() > 0) && isSuaLenhDieuDong.length() > 0) {
			
			result = THAO_TAC_BAN_KHAI_CAP_LAI;
			log.info("====button CAP NHAP====mot lan==result===" + result);
			
		}
		else if (tempDoc.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG && (lst != null && lst.size() > 0) && order.getStampStatus() == 11  && order.getIsApproval() == 0 ) {
			log.info("==chinh" + result);
			result = THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO;
			log.info("==chinh   ==button kyso ==result===" + result);
			
		}
		else if (tempDoc.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG && order.getStampStatus() == 1) {
			log.info("==chinh" + result);
			result = THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU;
			log.info("==chinh   ==button dong dau==result===" + result);
			
		} else if (tempDoc.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG && (lst != null && lst.size() > 0)
				&& isSuaLenhDieuDong.length() == 0) {
			
			result = THAO_TAC_BAN_KHAI_SUA;
			log.info("====button DUYET hoac SUA====mot lan==result===" + result);
			
			// /// N LAN
		} else if (tempDoc.getRequestState() == TrangThaiBanKhai.DA_CAP_LENH_DIEU_DONG && (lst != null && lst.size() > 0) && order.getStampStatus() == 11  && order.getIsApproval() == 0 ) {
			log.info("==chinh" + result);
			result = THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO;
			log.info("==chinh   ==button kyso lan N==result===" + result);
			
		} else if (tempDoc.getRequestState() == TrangThaiBanKhai.DA_CAP_LENH_DIEU_DONG && order.getStampStatus() == 1) {
			log.info("==chinh" + result);
			result = THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU;
			log.info("==chinh   ==button dong dau lan N==result===" + result);
			
		} else if (tempDoc.getRequestState() == TrangThaiBanKhai.DA_CAP_LENH_DIEU_DONG && (lst != null && lst.size() > 0)
				&& isSuaLenhDieuDong.length() > 0) {
			
			result = THAO_TAC_BAN_KHAI_CAP_LAI_LAN_THU_N;
			log.info("====button CAP NHAP====n lan==result===" + result);
			
		} else if (tempDoc.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG ||
					(tempDoc.getRequestState() == TrangThaiBanKhai.DA_CAP_LENH_DIEU_DONG &&
					(tempDoc.getDocumentStatusCode() == TrangThaiBanKhai.THU_TUC_DA_TIEP_NHAN || tempDoc.getDocumentStatusCode() == TrangThaiBanKhai.TU_CHOI_TIEP_NHAN))) {
			
			result = THAO_TAC_BAN_KHAI_CAP_BAN_KHAI;
			log.info("====button CAP_BAN_KHAI lan dau==result===" + result);
		}
		
		else if (tempDoc.getRequestState() == TrangThaiBanKhai.DA_CAP_LENH_DIEU_DONG && order.getStampStatus() == 2) {
			
			result = THAO_TAC_BAN_DONG_DAU_XONG;
			log.info("====button chinh 4444444   CAP_BAN_KHAI lan dau==result===" + result);
			
			//chinh 17/12/2015
		}
		
		log.info("==check hien thi button====REQUEST_STATE=" + tempDoc.getRequestState() + "====DOCUMENT_STATUS_CODE="
				+ tempDoc.getDocumentStatusCode());
		return result;
	}
	
	public static int checkKySoLenhDieuDong(TempDocument tempDoc, String requestCode, HttpServletRequest actionRequest) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		try {
			result = checkLenhDieuDong(tempDoc, requestCode, actionRequest);
			
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("=========THAO_TAC_BAN_KHAI_XEM=======khong hien thi nut j ca");
		return result;

	}
	public static int checkBanKhaiHangHoa(TempDocument tempDocument, String requestCode) {
		return THAO_TAC_BAN_KHAI_XEM;
		// if (requestCode == null || requestCode.length() == 0) {
		// return THAO_TAC_BAN_KHAI_XEM;
		// }
		// int count = ResultDeclarationLocalServiceUtil
		// .countByRequestCodeAndBusinessTypeCode(requestCode,
		// MessageType.BAN_KHAI_HANG_HOA);
		// if (count < 1) {
		// return THAO_TAC_BAN_KHAI_XEM;
		// }
		// int result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
		// try {
		// List<TempCargoDeclaration> results =
		// TempCargoDeclarationLocalServiceUtil
		// .findByRequestCode(requestCode);
		// if (results != null && results.size() > 0) {
		// TempCargoDeclaration tempNoticeShipMessage = results.get(0);
		// switch (tempNoticeShipMessage.getRequestState()) {
		// case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN:
		// result = THAO_TAC_BAN_KHAI_XEM;
		// break;
		// case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI:
		// result = THAO_TAC_BAN_KHAI_XEM;
		// break;
		// case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY:
		// result = THAO_TAC_BAN_KHAI_XEM;
		// break;
		// case TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU:
		// result = THAO_TAC_BAN_KHAI_XEM;
		// break;
		// }
		//
		// } else {
		// return THAO_TAC_BAN_KHAI_XEM;
		// }
		// } catch (Exception e) {
		// e.printStackTrace();log.error(e.getMessage());
		// }
		// return result;
	}
	
	
	
	public static int checkBanKhaiChung(TempDocument tempDocument, String requestCode) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		int count = ResultDeclarationLocalServiceUtil.countByRequestCodeAndBusinessTypeCode(requestCode, MessageType.BAN_KHAI_CHUNG);
		if (count < 1) {
			return THAO_TAC_BAN_KHAI_XEM;
		}
		if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.CHO_PHE_DUYET_HOAN_THANH_THU_TUC) {
			List<TempGeneralDeclaration> tempGeneralDeclarations = TempGeneralDeclarationLocalServiceUtil.findByRequestCode(requestCode);
			if (tempGeneralDeclarations != null && tempGeneralDeclarations.size() > 0) {
				if (tempGeneralDeclarations.get(0).getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
					result = THAO_TAC_BAN_KHAI_DOI_CHIEU;
				}
			}
		}
		return result;
	}
	
	public static int checkBanKhaiDanhSachThuyenVien(TempDocument tempDocument, String requestCode) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		int count = ResultDeclarationLocalServiceUtil.countByRequestCodeAndBusinessTypeCode(requestCode, MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN);
		if (count < 1) {
			return THAO_TAC_BAN_KHAI_XEM;
		}
		if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.CHO_PHE_DUYET_HOAN_THANH_THU_TUC) {
			try {
				List<TempCrewList> crewLists = TempCrewListLocalServiceUtil.findByRequestCode(requestCode);
				if (crewLists != null && crewLists.size() > 0) {
					if (crewLists.get(0).getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
						result = THAO_TAC_BAN_KHAI_DOI_CHIEU;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();log.error(e.getMessage());
			}
		}
		
		return result;
	}
	
	public static int checkBanKhaiDanhSachHanhKhach(TempDocument tempDocument, String requestCode) {
		int count = ResultDeclarationLocalServiceUtil.countByRequestCodeAndBusinessTypeCode(requestCode, MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH);
		if (count < 1) {
			return THAO_TAC_BAN_KHAI_XEM;
		}
		int result = THAO_TAC_BAN_KHAI_XEM;
		if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.CHO_PHE_DUYET_HOAN_THANH_THU_TUC) {
			try {
				List<TempPassengerList> passengerLists = TempPassengerListLocalServiceUtil.findByRequestCode(requestCode);
				if (passengerLists != null && passengerLists.size() > 0) {
					if (passengerLists.get(0).getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
						result = THAO_TAC_BAN_KHAI_DOI_CHIEU;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();log.error(e.getMessage());
			}
		}
		
		return result;
	}
	
	public static int checkBanKhaiDuTruTau(TempDocument tempDocument, String requestCode) {
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		return result;
	}
	
	public static int checkHoanThanh(TempDocument tempDocument, String requestCode) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhaiNhapCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC) {
			result = THAO_TAC_BAN_KHAI_HOAN_THANH;
		}
		return result;
	}
	
	public static int checkDuyetHoSo(TempDocument tempDocument, String requestCode) {
		int result = THAO_TAC_KIEM_DUYET_HO_SO;
		
		return result;
	}
	
	public static int checkBanKhaiHanhLyThuyenVien(TempDocument tempDocument, String requestCode) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		return result;
	}
	
	public static int checkGiayPhepQuaCanh(TempDocument temp, String requestCode, HttpServletRequest actionRequest) {
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		try {
			IssuePermissionForTransit perForTranSit = IssuePermissionForTransitLocalServiceUtil.getByrequestCode(requestCode);
			if (Validator.isNull(perForTranSit)) {
				perForTranSit = new IssuePermissionForTransit();
			}
			;
			
			String slanCapPerForTransit = ParamUtil.getString(actionRequest, PageType.LAN_CAP_PERMISSION_FOR_TRANSIT);
			String slanSuaPerForTransit = ParamUtil.getString(actionRequest, PageType.LAN_SUA_PERMISSION_FOR_TRANSIT);
			
			int actionType = ParamUtil.getInteger(actionRequest, PageType.ACTION_TYPE);
			log.info("===checkGiayPhepQuaCanh===ACTION_TYPE=====" + actionType);
			
			String scapNhapPerForTranSit = ParamUtil.getString(actionRequest, PageType.CHUYEN_GIAO_DIEN_CAP_NHAP_PERMISSION_FOR_TRANSIT);
			
			String scapLaiDuyetOrSua = ParamUtil.getString(actionRequest, PageType.CHUYEN_GIAO_DIEN_DUYET_OR_SUA_PERMISSION_FOR_TRANSIT);
			
			String schuyenGiaoDienLanThu_N = ParamUtil.getString(actionRequest, PageType.CHUYEN_GIAO_DIEN_CAP_NHAP_PERMISSION_FOR_TRANSIT_LAN_THU_N);
			
			// lan n
			List<IssuePermissionForTransit> lstPerForTransit = IssuePermissionForTransitLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(temp.getDocumentName(), temp.getDocumentYear(), KeyParams.VERSION_NO,
							KeyParams.ORDER_BY_DESC);
			
			boolean isDuyetHetPerForTransit = true;
			for (IssuePermissionForTransit item : lstPerForTransit) {
				if (item.getRequestState() != TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI) {
					isDuyetHetPerForTransit = false;
					break;
				}
			}
			
			boolean isMoiNhatPerForTransit = (lstPerForTransit.size() == 0) ? false : (lstPerForTransit.get(0).getRequestCode()
					.compareTo(requestCode) == 0 ? true : false);
			
			Date currentValidDate = Validator.isNotNull(perForTranSit.getValidUntil()) ? perForTranSit.getValidUntil() : new Date();
			// convert date to calendar
	        Calendar c = Calendar.getInstance();
	        c.setTime(currentValidDate);
	        // manipulate date
	        c.add(Calendar.DATE, 30); 
	        // convert calendar to date
	        Date currentValidDateAfterOneMonth = c.getTime();
	        
			// da duyet hay chua
			boolean isDaDuyet = Validator.isNotNull(perForTranSit.getRequestState()) ? (perForTranSit.getRequestState() == 4 ? true : false) : false;
						
			// ngay hieu luc > ngay he thong
			boolean isNgayHieuLuc = Validator.isNotNull(perForTranSit.getValidUntil()) ? (currentValidDateAfterOneMonth.after(new Date()) ? true
					: false) : false;

	        
			log.info("======checkGiayPhepQuaCanh====REQUESTCODE====" + requestCode + "====RequestState====" + perForTranSit.getRequestState());
			
			log.info("======LAN_CAP_PERMISSION_FOR_TRANSIT===============================" + slanCapPerForTransit);
			log.info("======LAN_SUA_PERMISSION_FOR_TRANSIT===============================" + slanSuaPerForTransit);
			log.info("======CHUYEN_GIAO_DIEN_CAP_NHAP_PERMISSION_FOR_TRANSIT=============" + scapNhapPerForTranSit);
			log.info("======CHUYEN_GIAO_DIEN_DUYET_OR_SUA_PERMISSION_FOR_TRANSIT=========" + scapLaiDuyetOrSua);
			log.info("======CHUYEN_GIAO_DIEN_CAP_NHAP_PERMISSION_FOR_TRANSIT_LAN_THU_N===" + schuyenGiaoDienLanThu_N);
			
			log.info("======isMoi_Nhat_PerForTransit ====================================" + isMoiNhatPerForTransit);
			log.info("======isDaDuyet====================================================" + isDaDuyet);
			log.info("======isNgayHieuLuc================================================" + isNgayHieuLuc);
			
			// /////////////////LAN 1
			
			if (temp.getDocumentStatusCode() == TrangThaiBanKhaiQuaCanh.DE_NGHI_CAP_GIAY_PHEP && scapNhapPerForTranSit.length() > 0
					&& actionType == PageType.ACTION_CHUYEN_GIAO_DIEN) {
				
				log.info("==THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP====THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP====RESULT===" + result
						+ "====NUT Cap nhap lan 1===lan 1");
				return THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP;
				
			}
			// Ky so thu tuc qua canh 
			else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiQuaCanh.DE_NGHI_CAP_GIAY_PHEP  && Validator.isNotNull(perForTranSit) 
					&& perForTranSit.getRequestCode().length()> 0 && perForTranSit.getStampStatus() == 11 && perForTranSit.getIsApproval() == 0) {	
				return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_QC;
			}
			// Dong dau thu tuc qua canh 
			else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiQuaCanh.DE_NGHI_CAP_GIAY_PHEP   && Validator.isNotNull(perForTranSit) && perForTranSit.getStampStatus() == 1) {	
				return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_QC;
			} 
			
			else if ((temp.getDocumentStatusCode() == TrangThaiBanKhaiQuaCanh.DE_NGHI_CAP_GIAY_PHEP && slanCapPerForTransit
					.compareTo(KeyParams.MOT_LAN) == 0)
					||
					
					(temp.getDocumentStatusCode() == TrangThaiBanKhaiQuaCanh.DE_NGHI_CAP_GIAY_PHEP && Validator.isNotNull(perForTranSit)
							&& perForTranSit.getRequestState() == TrangThaiBanKhaiQuaCanh.KHAI_SUA && slanSuaPerForTransit
							.compareTo(KeyParams.MOT_LAN) == 0 
							&& perForTranSit.getStampStatus() != 11 ) ||
					
					// bam menu left, dang la ban khai moi nhat, va trang thai
					// khai sua, sua lan 1
					(temp.getDocumentStatusCode() == TrangThaiBanKhaiQuaCanh.DE_NGHI_CAP_GIAY_PHEP && Validator.isNotNull(perForTranSit)
							&& perForTranSit.getRequestState() == TrangThaiBanKhaiQuaCanh.KHAI_SUA && isMoiNhatPerForTransit == true
							&& perForTranSit.getStampStatus() != 11 ) ||
					
					(scapLaiDuyetOrSua.length() > 0)) {
				
				log.info("==========THAO_TAC_BAN_KHAI_CAP_LAI====RESULT==="
						+ "====NUT DUYET_OR_SUA====an nut==CAP BAN KHAI, lan dau tien, sau khi ban cap");
				
				return THAO_TAC_BAN_KHAI_CAP_LAI;
				
				// ///////////////LAN N
				// truong hop ko vao ban khai moi nhat, va chua duyet
			} else if ((isMoiNhatPerForTransit == false && isDaDuyet == false && isDuyetHetPerForTransit == false)
					|| (isMoiNhatPerForTransit == false && isDaDuyet == true && isDuyetHetPerForTransit == false)
					|| (isDaDuyet == true && isNgayHieuLuc == false)) {
				
				log.info("xem ban khai:" + "dang thao tac ban khai nay, thao tac ban khai khac");
				
				return THAO_TAC_BAN_KHAI_XEM;
				
			} else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC && isDaDuyet == true
					&& isNgayHieuLuc == true && schuyenGiaoDienLanThu_N.length() == 0) {
				
				log.info("==========THAO_TAC_BAN_KHAI_CAP_LAI_OR_HUY====RESULT===" + result
						+ "====CAP LAI or HUY CAU==RequestState=4 && before(new Date())");
				
				return THAO_TAC_BAN_KHAI_CAP_LAI_OR_HUY;
				
			} else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC
					&& (Validator.isNotNull(perForTranSit.getRequestState()) && perForTranSit.getRequestState() != 4)
					&& isMoiNhatPerForTransit == true && schuyenGiaoDienLanThu_N.length() == 0) {
				
				log.info("==========THAO_TAC_BAN_KHAI_CAP_LAI_LAN_THU_N====RESULT===" + result
						+ "===nut DUYET va SUA===CAP LAI LAN_THU_N===khi portClearance.getRequestState() != 4 && dang la ban khai moi nhat");
				// Ky so thu tuc qua canh lan N
				if (temp.getDocumentStatusCode() == TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC  && Validator.isNotNull(perForTranSit) 
						&& perForTranSit.getRequestCode().length()> 0 && perForTranSit.getStampStatus() == 11 && perForTranSit.getIsApproval() == 0) {	
					return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_QC;
				}
				// Dong dau thu tuc qua canh lan N
				else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC   && Validator.isNotNull(perForTranSit) && perForTranSit.getStampStatus() == 1) {	
					return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_QC;
				} 
				else 
				{
					return THAO_TAC_BAN_KHAI_CAP_LAI_LAN_THU_N;
				}
				
				
				// hien thi nut update du lieu lan n
			} else if ((isMoiNhatPerForTransit == true && schuyenGiaoDienLanThu_N.length() > 0 && slanSuaPerForTransit.length() > 0) ||
			
			(isDaDuyet == true && isNgayHieuLuc == true && schuyenGiaoDienLanThu_N.length() > 0) && slanSuaPerForTransit.length() > 0
					&& actionType == PageType.ACTION_CHUYEN_GIAO_DIEN) {
				
				log.info("==========THAO_TAC_CHUYEN_GIAO_DIEN_UPDATE_LAN_THU_N====RESULT====" + result + "====nut UPDATE===");
				
				return THAO_TAC_CHUYEN_GIAO_DIEN_UPDATE_LAN_THU_N;
				
				// hien thi nut insert du lieu lan n
			} else if ((isMoiNhatPerForTransit == true && schuyenGiaoDienLanThu_N.length() > 0 && slanCapPerForTransit.compareTo(KeyParams.N_LAN) == 0)
					||
					
					(isDaDuyet == true && isNgayHieuLuc == true && schuyenGiaoDienLanThu_N.length() > 0 && slanCapPerForTransit
							.compareTo(KeyParams.N_LAN) == 0)) {
				log.info("==========THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP_LAN_THU_N====RESULT====" + result + "====NUT insert===");
				
				return THAO_TAC_CHUYEN_GIAO_DIEN_INSERT_LAN_THU_N;
				
				// //////////////
			} else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiQuaCanh.DE_NGHI_CAP_GIAY_PHEP) {
				
				log.info("==checkGiayPhepQuaCanh====THAO_TAC_BAN_KHAI_CAP_BAN_KHAI====RESULT===" + result
						+ "==hien thi NUT CAP_BAN_KHAI MOI lan dau");
				
				return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI;
				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("=========THAO_TAC_BAN_KHAI_XEM=======khong hien thi nut j ca");
		return result;
	}
	
	public static int checkKySoGiayPhepQuaCanh(TempDocument temp, String requestCode, HttpServletRequest actionRequest) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		try {
			result = checkGiayPhepQuaCanh(temp, requestCode, actionRequest);
			
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("=========THAO_TAC_BAN_KHAI_XEM=======khong hien thi nut j ca");
		return result;
	}
	
	public static int checkGiayPhepRoiCang(TempDocument temp, String requestCode, HttpServletRequest actionRequest) {
		try {
			int result = THAO_TAC_BAN_KHAI_XEM;
			
			IssuePortClearance portClearance = IssuePortClearanceLocalServiceUtil.getByRequestCode(requestCode);
			if (Validator.isNull(portClearance)) {
				portClearance = new IssuePortClearance();
			}
			
			String sLanCapPortClearance = ParamUtil.getString(actionRequest, PageType.LAN_CAP_PORT_CLEARANCE);
			String sLanSuaPortClearance = ParamUtil.getString(actionRequest, PageType.LAN_SUA_PORT_CLEARANCE);
			
			int actionType = ParamUtil.getInteger(actionRequest, PageType.ACTION_TYPE);
			log.info("===checkGiayPhepRoiCang===ACTION_TYPE=====" + actionType);
			
			String sCapNhapPortClearance = ParamUtil.getString(actionRequest, PageType.CHUYEN_GIAO_DIEN_CAP_NHAP_PORT_CLEARANCE);
			
			String sCapLaiDuyetOrSua = ParamUtil.getString(actionRequest, PageType.CHUYEN_GIAO_DIEN_DUYET_OR_SUA_PORT_CLEARANCE);
			
			String sChuyenGiaoDienLanThu_N = ParamUtil.getString(actionRequest, PageType.CHUYEN_GIAO_DIEN_CAP_NHAP_PORT_CLEARANCE_LAN_THU_N);
			
			List<IssuePortClearance> lstPortClearances = IssuePortClearanceLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(
					temp.getDocumentName(), temp.getDocumentYear(), KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
			
			boolean isDuyetHetPortClean = true;
			for (IssuePortClearance item : lstPortClearances) {
				if (item.getRequestState() != TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI) {
					isDuyetHetPortClean = false;
					break;
				}
			}
			
			// kiem tra co phai ban khai, giay phep roi cang moi nhat, giay phep
			// qua canh moi nhat
			boolean isMoiNhatPortClearances = (lstPortClearances.size() == 0) ? false : (lstPortClearances.get(0).getRequestCode()
					.compareTo(requestCode) == 0 ? true : false);
			
			Date currentValidDate = Validator.isNotNull(portClearance.getValidUntil()) ? portClearance.getValidUntil() : new Date();
			// convert date to calendar
	        Calendar c = Calendar.getInstance();
	        c.setTime(currentValidDate);
	        // manipulate date
	        c.add(Calendar.DATE, 30); 
	        // convert calendar to date
	        Date currentValidDateAfterOneMonth = c.getTime();
	        
			// da duyet hay chua
			boolean isDaDuyet = Validator.isNotNull(portClearance.getRequestState()) ? (portClearance.getRequestState() == 4 ? true : false) : false;
			
			// ngay hieu luc > ngay he thong
			boolean isNgayHieuLuc = Validator.isNotNull(portClearance.getValidUntil()) ? (currentValidDateAfterOneMonth.after(new Date()) ? true
					: false) : false;
			
			log.info("======checkGiayPhepRoiCang====REQUEST_CODE====" + requestCode + "====Request_State====" + portClearance.getRequestState());
			log.info("======LAN_CAP_PORT_CLEARANCE=======================================" + sLanCapPortClearance);
			log.info("======LAN_SUA_PORT_CLEARANCE=======================================" + sLanSuaPortClearance);
			log.info("======CHUYEN_GIAO_DIEN_CAP_NHAP_PORT_CLEARANCE=====================" + sCapNhapPortClearance);
			log.info("======CHUYEN_GIAO_DIEN_DUYET_OR_SUA_PORT_CLEARANCE=================" + sCapLaiDuyetOrSua);
			log.info("======CHUYEN_GIAO_DIEN_CAP_NHAP_PORT_CLEARANCE_LAN_THU_N===========" + sChuyenGiaoDienLanThu_N);
			
			log.info("======isMoi_Nhat_PortClearances====================================" + isMoiNhatPortClearances);
			log.info("======isDaDuyet====================================================" + isDaDuyet);
			log.info("======isNgayHieuLuc================================================" + isNgayHieuLuc);
			
			// ////////////lan mot
			
			if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP && sCapNhapPortClearance.length() > 0
					&& actionType == PageType.ACTION_CHUYEN_GIAO_DIEN) {
				
				log.info("==========THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP====RESULT===" + result + "====NUT Cap nhap lan 1===lan 1");
				return THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP;
				
			} else if ((temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP && sLanCapPortClearance
					.compareTo(KeyParams.MOT_LAN) == 0)
					||
					
					(temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP && Validator.isNotNull(portClearance)
							&& portClearance.getRequestState() == TrangThaiBanKhaiXuatCanh.KHAI_MOI && sLanSuaPortClearance
							.compareTo(KeyParams.MOT_LAN) == 0) ||
					
					// bam menu left, dang la ban khai moi nhat, va trang thai
					// khai sua, sua lan 1
					(temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP && Validator.isNotNull(portClearance)
							&& portClearance.getRequestState() == TrangThaiBanKhaiXuatCanh.KHAI_SUA && isMoiNhatPortClearances == true   
							&& portClearance.getStampStatus() != 11 ) ||
					
					(sCapLaiDuyetOrSua.length() > 0)) {
				log.info("==========THAO_TAC_BAN_KHAI_CAP_LAI====RESULT===" + result
						+ "====NUT DUYET_OR_SUA====an nut==CAP BAN KHAI, lan dau tien, sau khi ban cap");
				
				return THAO_TAC_BAN_KHAI_CAP_LAI;
				
				// //////////////////////// lan n
				
				// truong hop ko vao ban khai moi nhat, va chua duyet
			} else if (isMoiNhatPortClearances == false && isDaDuyet == false && isDuyetHetPortClean == false ||
			
			(isMoiNhatPortClearances == false && isDaDuyet == true && isDuyetHetPortClean == false) ||
			
			(isDaDuyet == true && isNgayHieuLuc == false)) {
				
				log.info("xem ban khai:" + "dang thao tac ban khai nay, thao tac ban khai khac");
				
				return THAO_TAC_BAN_KHAI_XEM;
				
			} else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC && isDaDuyet == true
					&& isNgayHieuLuc == true && sChuyenGiaoDienLanThu_N.length() == 0) {
				log.info("==========THAO_TAC_BAN_KHAI_CAP_LAI_OR_HUY====RESULT===" + result
						+ "====CAP LAI OR HUY CAU==RequestState=4 && before(new Date())");
				
				return THAO_TAC_BAN_KHAI_CAP_LAI_OR_HUY;
				
			} else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC
					&& (Validator.isNotNull(portClearance.getRequestState()) && portClearance.getRequestState() != 4)
					&& isMoiNhatPortClearances == true && sChuyenGiaoDienLanThu_N.length() == 0) {
				log.info("==========THAO_TAC_BAN_KHAI_CAP_LAI_LAN_THU_N====RESULT===" + result
						+ "===hien thi nut duyet va sua=CAP LAI LAN_THU_N===khi portClearance.getRequestState() != 4 && dang la ban khai moi nhat");
				//Ky so Giay phep xuat canh lan N
				if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC  && Validator.isNotNull(portClearance) 
						&& portClearance.getRequestCode().length()> 0 && portClearance.getStampStatus() == 11 && portClearance.getIsApproval() == 0) {
					log.info("==========vao day ==TrangThaiBanKhaiXuatCanh==KySo ");
					log.info("==========THAO_TAC_BAN_KHAI_CAP_BAN_KHAI====THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_XC====" + result);
					
					return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_XC;
				}				
				else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC  && Validator.isNotNull(portClearance) && portClearance.getStampStatus() == 1) {
					log.info("==========vao day ==TrangThaiBanKhaiXuatCanh==DONGDAU");
					log.info("==========THAO_TAC_BAN_KHAI_CAP_BAN_KHAI====DONGDAU====" + result);
					
					return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_XC;
				} 
				else 
				{
					return THAO_TAC_BAN_KHAI_CAP_LAI_LAN_THU_N;
				}				
				
				// hien thi nut update du lieu lan n
			} else if ((isMoiNhatPortClearances == true && sChuyenGiaoDienLanThu_N.length() > 0 && sLanSuaPortClearance.length() > 0) ||
			
			(isDaDuyet == true && isNgayHieuLuc == true && sChuyenGiaoDienLanThu_N.length() > 0) && sLanSuaPortClearance.length() > 0
					&& actionType == PageType.ACTION_CHUYEN_GIAO_DIEN) {
				
				log.info("==========THAO_TAC_CHUYEN_GIAO_DIEN_UPDATE_LAN_THU_N====RESULT====" + result + "====NUT update===");
				return THAO_TAC_CHUYEN_GIAO_DIEN_UPDATE_LAN_THU_N;
				
				// hien thi nut insert du lieu lan n
			} else if ((isMoiNhatPortClearances == true && sChuyenGiaoDienLanThu_N.length() > 0 && sLanCapPortClearance.compareTo(KeyParams.N_LAN) == 0)
					||
					
					(isDaDuyet == true && isNgayHieuLuc == true && sChuyenGiaoDienLanThu_N.length() > 0 && sLanCapPortClearance
							.compareTo(KeyParams.N_LAN) == 0)) {
				log.info("==========THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP_LAN_THU_N====RESULT====" + result + "====NUT insert===");
				
				return THAO_TAC_CHUYEN_GIAO_DIEN_INSERT_LAN_THU_N;
				
				// //////
			}
			//Ky so Giay phep xuat canh
			else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP  && Validator.isNotNull(portClearance) 
					&& portClearance.getRequestCode().length()> 0 && portClearance.getStampStatus() == 11 && portClearance.getIsApproval() == 0) {
				log.info("==========vao day ==TrangThaiBanKhaiXuatCanh==KySo ");
				log.info("==========THAO_TAC_BAN_KHAI_CAP_BAN_KHAI====THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_XC====" + result);
				
				return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_XC;
			}
			//chinh **********************************************
			else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP  && Validator.isNotNull(portClearance) && portClearance.getStampStatus() == 1) {
				log.info("==========vao day ==TrangThaiBanKhaiXuatCanh==DONGDAU");
				log.info("==========THAO_TAC_BAN_KHAI_CAP_BAN_KHAI====DONGDAU====" + result);
				
				return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_XC;
			}
			else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP) {
				
				log.info("==========THAO_TAC_BAN_KHAI_CAP_BAN_KHAI====DE_NGHI_CAP_GIAY_PHEP====" + result + "==hien thi NUT CAP_BAN_KHAI MOI lan dau");
				return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI;
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("==========THAO_TAC_BAN_KHAI_XEM========");
		return THAO_TAC_BAN_KHAI_XEM;
	}
	
	public static int checkKySoGiayPhepRoiCang(TempDocument temp, String requestCode, HttpServletRequest actionRequest) {		
		int result = THAO_TAC_BAN_KHAI_XEM;
		try {
			result = checkGiayPhepRoiCang(temp, requestCode, actionRequest);
			
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("=========THAO_TAC_BAN_KHAI_XEM=======khong hien thi nut j ca");
		return result;
	}
	public static int checkGiayPhepVaoCang(TempDocument temp, String requestCode, HttpServletRequest actionRequest) {
		try {
			int result = THAO_TAC_BAN_KHAI_XEM;
			
			IssueAcceptance issueAcceptance = IssueAcceptanceLocalServiceUtil.getByRequestCode(requestCode);
			if (Validator.isNull(issueAcceptance)) {
				issueAcceptance = new IssueAcceptance();
			}
			
			String sLanCapIssueAcceptance = ParamUtil.getString(actionRequest, PageType.LAN_CAP_ISSUE_ACCEPTANCE);
			String sLanSuaIssueAcceptance = ParamUtil.getString(actionRequest, PageType.LAN_SUA_ISSUE_ACCEPTANCE);
			
			int actionType = ParamUtil.getInteger(actionRequest, PageType.ACTION_TYPE);
			log.info("===checkGiayPhepVaoCang===ACTION_TYPE=====" + actionType);
			
			String sCapNhapIssueAcceptance = ParamUtil.getString(actionRequest, PageType.CHUYEN_GIAO_DIEN_CAP_NHAP_ISSUE_ACCEPTANCE);
			
			String sCapLaiDuyetOrSua = ParamUtil.getString(actionRequest, PageType.CHUYEN_GIAO_DIEN_DUYET_OR_SUA_ISSUE_ACCEPTANCE);
			
			String sChuyenGiaoDienLanThu_N = ParamUtil.getString(actionRequest, PageType.CHUYEN_GIAO_DIEN_CAP_NHAP_ISSUE_ACCEPTANCE_LAN_THU_N);
			
			List<IssueAcceptance> lstIssueAcceptances = IssueAcceptanceLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(
					temp.getDocumentName(), temp.getDocumentYear(), KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
			
			boolean isDuyetHetIssueAcceptance = true;
			for (IssueAcceptance item : lstIssueAcceptances) {
				if (item.getRequestState() != TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI) {
					isDuyetHetIssueAcceptance = false;
					break;
				}
			}
			
			// kiem tra co phai ban khai, giay phep vao cang moi nhat
			boolean isMoiNhatIssueAcceptances = (lstIssueAcceptances.size() == 0) ? false : (lstIssueAcceptances.get(0).getRequestCode()
					.compareTo(requestCode) == 0 ? true : false);
			
			
			Date currentValidDate = Validator.isNotNull(issueAcceptance.getValidUntil()) ? issueAcceptance.getValidUntil() : new Date();
			// convert date to calendar
	        Calendar c = Calendar.getInstance();
	        c.setTime(currentValidDate);
	        // manipulate date
	        c.add(Calendar.DATE, 30); 
	        // convert calendar to date
	        Date currentValidDateAfterOneMonth = c.getTime();
	        	        
			// da duyet hay chua
			boolean isDaDuyet = Validator.isNotNull(issueAcceptance.getRequestState()) ? (issueAcceptance.getRequestState() == 4 ? true : false) : false;
			
			// ngay hieu luc > ngay he thong
			boolean isNgayHieuLuc = Validator.isNotNull(issueAcceptance.getValidUntil()) ? (currentValidDateAfterOneMonth.after(new Date()) ? true
					: false) : false;
			
			log.info("======checkGiayPhepVaoCang====REQUEST_CODE====" + requestCode + "====Request_State====" + issueAcceptance.getRequestState());
			log.info("======LAN_CAP_ISSUE_ACCEPTANCE=======================================" + sLanCapIssueAcceptance);
			log.info("======LAN_SUA_ISSUE_ACCEPTANCE=======================================" + sLanSuaIssueAcceptance);
			log.info("======CHUYEN_GIAO_DIEN_CAP_NHAP_ISSUE_ACCEPTANCE=====================" + sCapNhapIssueAcceptance);
			log.info("======CHUYEN_GIAO_DIEN_DUYET_OR_SUA_ISSUE_ACCEPTANCE=================" + sCapLaiDuyetOrSua);
			log.info("======CHUYEN_GIAO_DIEN_CAP_NHAP_ISSUE_ACCEPTANCE_LAN_THU_N===========" + sChuyenGiaoDienLanThu_N);
			
			log.info("======isMoiNhatIssueAcceptances====================================" + isMoiNhatIssueAcceptances);
			log.info("======isDaDuyet====================================================" + isDaDuyet);
			log.info("======isNgayHieuLuc================================================" + isNgayHieuLuc);
			
			// ////////////lan mot
			
			if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP && sCapNhapIssueAcceptance.length() > 0
					&& actionType == PageType.ACTION_CHUYEN_GIAO_DIEN) {
				
				log.info("==========THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP====RESULT===" + result + "====NUT Cap nhap lan 1===lan 1");
				return THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP;
				
			} else if ((temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP && sLanCapIssueAcceptance
					.compareTo(KeyParams.MOT_LAN) == 0)
					||
					
					(temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP && Validator.isNotNull(issueAcceptance)
							&& issueAcceptance.getRequestState() == TrangThaiBanKhaiXuatCanh.KHAI_MOI && sLanSuaIssueAcceptance
							.compareTo(KeyParams.MOT_LAN) == 0) ||
					
					// bam menu left, dang la ban khai moi nhat, va trang thai
					// khai sua, sua lan 1
					(temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP && Validator.isNotNull(issueAcceptance)
							&& issueAcceptance.getRequestState() == TrangThaiBanKhaiXuatCanh.KHAI_SUA && isMoiNhatIssueAcceptances == true
							 && issueAcceptance.getStampStatus() != 11) ||
					
					(sCapLaiDuyetOrSua.length() > 0)) {
				log.info("==========THAO_TAC_BAN_KHAI_CAP_LAI====RESULT===" + result
						+ "====NUT DUYET_OR_SUA====an nut==CAP BAN KHAI, lan dau tien, sau khi ban cap");
				
				return THAO_TAC_BAN_KHAI_CAP_LAI;
				
				// //////////////////////// lan n
				
				// truong hop ko vao ban khai moi nhat, va chua duyet
			} else if (isMoiNhatIssueAcceptances == false && isDaDuyet == false && isDuyetHetIssueAcceptance == false ||
			
			(isMoiNhatIssueAcceptances == false && isDaDuyet == true && isDuyetHetIssueAcceptance == false) ||
			
			(isDaDuyet == true && isNgayHieuLuc == false)) {
				
				log.info("xem ban khai:" + "dang thao tac ban khai nay, thao tac ban khai khac");
				
				return THAO_TAC_BAN_KHAI_XEM;
				
			} else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC && isDaDuyet == true
					 && sChuyenGiaoDienLanThu_N.length() == 0) {
				log.info("==========THAO_TAC_BAN_KHAI_CAP_LAI_OR_HUY====RESULT===" + result
						+ "====CAP LAI OR HUY CAU==RequestState=4 && before(new Date())");
				
				return THAO_TAC_BAN_KHAI_CAP_LAI_OR_HUY;
				
			} else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC
					&& (Validator.isNotNull(issueAcceptance.getRequestState()) && issueAcceptance.getRequestState() != 4)
					&& isMoiNhatIssueAcceptances == true && sChuyenGiaoDienLanThu_N.length() == 0) {
				log.info("==========THAO_TAC_BAN_KHAI_CAP_LAI_LAN_THU_N====RESULT===" + result
						+ "===hien thi nut duyet va sua=CAP LAI LAN_THU_N===khi portClearance.getRequestState() != 4 && dang la ban khai moi nhat");
				// Ky so Giay phep vao cang Lan N
				if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC  && Validator.isNotNull(issueAcceptance) 
						&& issueAcceptance.getRequestCode().length() > 0 && issueAcceptance.getStampStatus() == 11 && issueAcceptance.getIsApproval() == 0) {
					log.info("==========vao day ==TrangThaiGPVaoCang==KySo");
					log.info("==========THAO_TAC_BAN_KHAI_CAP_BAN_KHAI====THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_VC====" + result);
					
					return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_VC;
				}
				// Dong dau Giay phep vao cang
				else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.PHE_DUYET_HOAN_THANH_THU_TUC  && Validator.isNotNull(issueAcceptance) && issueAcceptance.getStampStatus() == 1) {
					log.info("==========vao day ==TrangThaiGPVaoCang==DONGDAU");
					log.info("==========THAO_TAC_BAN_KHAI_CAP_BAN_KHAI====THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_VC====" + result);
					
					return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_VC;
				}
				else
				{
					return THAO_TAC_BAN_KHAI_CAP_LAI_LAN_THU_N;
				}
				// hien thi nut update du lieu lan n  
			} else if ((isMoiNhatIssueAcceptances == true && sChuyenGiaoDienLanThu_N.length() > 0 && sLanSuaIssueAcceptance.length() > 0) ||
			
			(isDaDuyet == true && isNgayHieuLuc == true && sChuyenGiaoDienLanThu_N.length() > 0) && sLanSuaIssueAcceptance.length() > 0
					&& actionType == PageType.ACTION_CHUYEN_GIAO_DIEN) {
				
				log.info("==========THAO_TAC_CHUYEN_GIAO_DIEN_UPDATE_LAN_THU_N====RESULT====" + result + "====NUT update===");
				return THAO_TAC_CHUYEN_GIAO_DIEN_UPDATE_LAN_THU_N;
				
				// hien thi nut insert du lieu lan n
			} else if ((isMoiNhatIssueAcceptances == true && sChuyenGiaoDienLanThu_N.length() > 0 && sLanCapIssueAcceptance.compareTo(KeyParams.N_LAN) == 0)
					||
					
					(isDaDuyet == true && isNgayHieuLuc == true && sChuyenGiaoDienLanThu_N.length() > 0 && sLanCapIssueAcceptance
							.compareTo(KeyParams.N_LAN) == 0)) {
				log.info("==========THAO_TAC_CHUYEN_GIAO_DIEN_CAP_NHAP_LAN_THU_N====RESULT====" + result + "====NUT insert===");
				
				return THAO_TAC_CHUYEN_GIAO_DIEN_INSERT_LAN_THU_N;
				
				// //////
			}
			// Ky so Giay phep vao cang
			else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP  && Validator.isNotNull(issueAcceptance) 
					&& issueAcceptance.getRequestCode().length() > 0 && issueAcceptance.getStampStatus() == 11 && issueAcceptance.getIsApproval() == 0) {
				log.info("==========vao day ==TrangThaiGPVaoCang==KySo");
				log.info("==========THAO_TAC_BAN_KHAI_CAP_BAN_KHAI====THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_VC====" + result);
				
				return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_KY_SO_THU_TUC_VC;
			}
			// Dong dau Giay phep vao cang
			else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP  && Validator.isNotNull(issueAcceptance) && issueAcceptance.getStampStatus() == 1) {
				log.info("==========vao day ==TrangThaiGPVaoCang==DONGDAU");
				log.info("==========THAO_TAC_BAN_KHAI_CAP_BAN_KHAI====THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_VC====" + result);
				
				return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI_DONG_DAU_THU_TUC_VC;
			}			
			else if (temp.getDocumentStatusCode() == TrangThaiBanKhaiXuatCanh.DE_NGHI_CAP_GIAY_PHEP) {
				
				log.info("==========THAO_TAC_BAN_KHAI_CAP_BAN_KHAI========" + result + "==hien thi NUT CAP_BAN_KHAI MOI lan dau");
				return THAO_TAC_BAN_KHAI_CAP_BAN_KHAI;
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("==========THAO_TAC_BAN_KHAI_XEM====khÃ´ng hiá»ƒn thá»‹ j cáº£====");
		return THAO_TAC_BAN_KHAI_XEM;
	}
	
	public static int checkKySoGiayPhepVaoCang(TempDocument temp, String requestCode, HttpServletRequest actionRequest) {
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		try {
			result = checkGiayPhepVaoCang(temp, requestCode, actionRequest);
			
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("=========THAO_TAC_BAN_KHAI_XEM=======khong hien thi nut j ca");
		return result;
	}
	
	public static int checkBanKhaiYTeHangHai(TempDocument tempDocument, String requestCode) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		return result;
	}
	
	public static int checkCacGiayToPhaiXuatTrinh(TempDocument tempDocument, String requestCode) {
		
		int result = THAO_TAC_BAN_KHAI_XEM;
		if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.CHO_PHE_DUYET_HOAN_THANH_THU_TUC) {
			result = THAO_TAC_KIEM_DUYET_HO_SO;
		}
		return result;
	}
	
	public static int checkBanKhaiKiemDichThucVat(TempDocument tempDocument, String requestCode) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		return result;
	}
	
	public static int checkBanKhaiKiemDichDongVat(TempDocument tempDocument, String requestCode) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		
		return result;
	}
	
	public static int checkThanhPhanHoSoThuTuc(TempDocument tempDocument, String requestCode) {
		int result = THAO_TAC_BAN_KHAI_XEM;
		try {
			
			if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.TU_CHOI_TIEP_NHAN
					|| tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.THU_TUC_DA_TIEP_NHAN) {
				result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
			}
			
		} catch (Exception e) {
			e.printStackTrace();log.error(e.getMessage());
		}
		return result;
	}
	
	public static int checkThanhPhanHoSoKeHoach(TempDocument tempDocument, String requestCode) {
		log.info("==checkThanhPhanHoSoKeHoach==requestCode==" + requestCode);
		int result = THAO_TAC_BAN_KHAI_CHAP_NHAN_TU_CHOI;
		try {
			if (tempDocument.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG) {
				return result;
			}
			
			if (tempDocument.getRequestState() != TrangThaiBanKhai.CHO_TIEP_NHAN
					&& tempDocument.getRequestState() != TrangThaiBanKhai.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {
				result = THAO_TAC_BAN_KHAI_XEM;
			}
			
		} catch (Exception e) {
			e.printStackTrace();log.error(e.getMessage());
		}
		return result;
	}
	
	//TODO check Ban Khai Danh Sach Hang Hoa Nguy Hiem
	public static int checkBanKhaiDanhSachHangHoaNguyHiem(TempDocument tempDocument, String requestCode) {
		int count = ResultDeclarationLocalServiceUtil.countByRequestCodeAndBusinessTypeCode(requestCode, MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM);
		if (count < 1) {
			return THAO_TAC_BAN_KHAI_XEM;
		}
		int result = THAO_TAC_BAN_KHAI_XEM;
		if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.CHO_PHE_DUYET_HOAN_THANH_THU_TUC) {
			try {
				List<TempDangerousGoodsManifest> lst = TempDangerousGoodsNanifestLocalServiceUtil.findByRequestCode(requestCode);
				if (lst != null && lst.size() > 0) {
					if (lst.get(0).getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
						result = THAO_TAC_BAN_KHAI_DOI_CHIEU;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();log.error(e.getMessage());
			}
		}
		return result;
	}
	
	public static String checkDisplayColumn(String requestCode, String requestCodeKeHoachDetail, int index) {
		log.info("==checkDisplayColumn==requestCode==" + requestCode + "==requestCodeKeHoachDetail==" + requestCodeKeHoachDetail);
		if (requestCode != null && requestCodeKeHoachDetail != null && requestCodeKeHoachDetail.trim().length() > 0) {
			if (requestCode.trim().compareTo(requestCodeKeHoachDetail.trim()) == 0) {
				return "class=\"tr_ativer\"";
			}
		} else if (requestCode == null && requestCodeKeHoachDetail != null) {
			if (index == 1) {
				return "class=\"tr_ativer\"";
			}
		}
		
		return "";
	}
	
	public static String checkDisplayColumnLichSu(String requestCode, String requestCodeDetail, int index, int size) {
		log.info("==checkDisplayColumnLichSu==requestCode==" + requestCode + "==requestCodeDetail==" + requestCodeDetail);
		if (requestCode != null && requestCodeDetail != null && requestCodeDetail.trim().length() > 0) {
			if (requestCode.trim().compareTo(requestCodeDetail.trim()) == 0) {
				return "class=\"tr_ativer\"";
			}
		} else if (requestCode == null && requestCodeDetail != null) {
			if (index == size - 1) {
				return "class=\"tr_ativer\"";
			}
		}
		return "";
	}
	
	public static boolean checkDisplay(int state) {
		if (state == MessageType.TRANG_THAI_BANG_KHAI_TIEP_NHAN || state == MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
			return true;
		}
		return false;
	}
	
	public static String checkDisplayIcon(long documentName, int documentYear, int businessTypeCode) {
		//log.info("==checkDisplayIcon==DocumentName==" + documentName + "==DocumentYear==" + documentYear + "==BusinessTypeCode==" + businessTypeCode);
		
		List<ResultDeclaration> lstResult = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode, documentName, documentYear);
		int state = 0;
		if (lstResult != null && lstResult.size() > 0) {
			state = lstResult.get(0).getRequestState();
			if (state == MessageType.TRANG_THAI_BANG_KHAI_TIEP_NHAN || state == MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
				return "icon_tich.png";
			//} else if (businessTypeCode == MessageType.THONG_BAO_TAU_DEN_CANG || businessTypeCode == MessageType.XAC_BAO_TAU_DEN_CANG) {
			//truong hop: thong bao, xac bao da xu? ly ben ke hoach, thi hien mac dinh xu ly ben thu tuc
			//return "icon_tich.png";
			} else if (state == MessageType.TRANG_THAI_BANG_KHAI_TU_CHOI) {
				return "close.png";
			}
		}
		
		return "";
	}
	
	public static String checkDisplayIconXuatCanh(long documentName, int documentYear, int businessTypeCode) {
		List<ResultDeclaration> listResultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				businessTypeCode, documentName, documentYear);
		int state = 0;
		if (listResultDeclarations != null && listResultDeclarations.size() > 0) {
			state = listResultDeclarations.get(0).getRequestState();
			if (state == MessageType.TRANG_THAI_BANG_KHAI_TIEP_NHAN || state == MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU) {
				return "icon_tich.png";
			} else if (state == MessageType.TRANG_THAI_BANG_KHAI_TU_CHOI) {
				return "close.png";
			}
		}
		
		return "";
	}
	
	public static String checkDisplayIcon(int state) {
		if (state == TrangThaiBanKhaiXuatCanh.KHAI_MOI) {
			return "icon_tich.png";
		} else if (state == TrangThaiBanKhaiXuatCanh.KHAI_HUY) {
			return "close.png";
		}
		return "";
	}
	
	public static String checkDisplayIconPerForTransit(int state) {
		if (state == TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI) {
			return "icon_tich.png";
		} else if (state == TrangThaiBanKhaiQuaCanh.KHAI_HUY) {
			return "close.png";
		}
		return "";
	}
	
	public static String checkDisplayIconPortClearance(int state) {
		if (state == TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI) {
			return "icon_tich.png";
		} else if (state == TrangThaiBanKhaiXuatCanh.KHAI_HUY) {
			return "close.png";
		}
		return "";
	}
	
	public static String checkDisplayIconShiftOrder(int state) {
		if (state == TrangThaiBanKhaiNhapCanh.CHAP_NHAN_BAN_KHAI | state == TrangThaiBanKhaiNhapCanh.SUA_DOI_BO_XUNG) {
			return "icon_tich.png";
		} else if (state == TrangThaiBanKhaiNhapCanh.KHAI_HUY || state == TrangThaiBanKhaiQuaCanh.KHAI_HUY) {
			return "close.png";
		}
		return "";
	}
	
	public static String checkDisplayIconIssueAcceptance(int state) {
		if (state == TrangThaiBanKhaiXuatCanh.CHAP_NHAN_BAN_KHAI) {
			return "icon_tich.png";
		} else if (state == TrangThaiBanKhaiXuatCanh.KHAI_HUY) {
			return "close.png";
		}
		return "";
	}
	
	
	//Ham nay ko su dung, se~ delete
	/*public static List<DmGTShipPosition> getListDmGTShipPosition(int roleCanBo, String loaiThuTuc) throws SystemException {
		try {
			DynamicQuery query = DynamicQueryFactoryUtil.forClass(DmGTShipPosition.class);
			// This is a test line for display all request if there's no search
			// criteria
			String value = "";
			Collection strings = new ArrayList<String>();
			
			if (roleCanBo == MessageType.ROLE_KE_HOACH) {
				if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_NHAP_CANH)) {
					strings.add("1,2");
				} else if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_QUA_CANH)) {
					strings.add("1,2");
				} else if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_XUAT_CANH)) {
					strings.add("3");
				} else {
					strings.add("1,2,3");
				}
			}
			
			if (roleCanBo == MessageType.ROLE_THU_TUC) {
				if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_NHAP_CANH)) {
					strings.add("2");
				} else if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_QUA_CANH)) {
					strings.add("2");
				} else if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_XUAT_CANH)) {
					strings.add("3");
				} else {
					strings.add("1,2,3");
				}
			}
			log.info("---------getListDmGTShipPosition-----" + strings);
			
			Criterion criterionTrangThaiHoSo = RestrictionsFactoryUtil.in("positionCode", strings);
			// Define search criteria
			query = query.add(criterionTrangThaiHoSo);
			
			return DmGTShipPositionLocalServiceUtil.dynamicQuery(query);
		} catch (Exception e) {
		}
		return new ArrayList<DmGTShipPosition>();
	}*/
	
	
	
	public static String checkColorKeHoach(int status) {
		String result = "";
		switch (status) {
		// case 11:
		// result = "class=\"wd-label-color-orange\"";
		// break;
			case 12:
				// Da tiep nhan - KE HOACH,
				result = "class=\"wd-label-color-light-green\"";
				break;
			case 14:
				result = "class=\"wd-label-color-tim\"";
				break;
			case 15:
				result = "class=\"wd-label-color-dark-blue\"";
				break;
			case 13:
				result = "class=\"wd-label-color-red\"";
				break;
			case 16:
				result = "class=\"wd-label-color-red\"";
				break;
			case 27:
				// yeu cau sua doi so sung - KE HOACH
				result = "style='background-color: rgb(236, 233, 18);'";
				break;
		}
		
		return result;
	}
	
	public static String checkColorThuTuc(int status) {
		String result = "";
		switch (status) {
			case 10:
				// huy? thu? tuc THU TUC
				result = "class=\"wd-label-color-red\"";
				break;
			case 12:
				// cho phe duyet hoan thanh thu tuc - THU TUC
				result = "class=\"wd-label-color-orange\"";
				break;
			case 13:
				// yeu cau sua doi bo sung - THU TUC
				result = "style='background-color: rgb(236, 233, 18);'";
				break;
			case 18:
				// da tiep nhan - THU TUC
				result = "class=\"wd-label-color-light-green\"";
				break;
			case 19:
				// phe duyet hoan thanh thu tuc - THUC TUC
				result = "class=\"wd-label-color-dark-blue\"";
				break;
			case 20:
				// de nghi cap giay phep
				result = "class=\"wd-label-color-xanh\"";  
				break;
		}
		return result;
	}
	
	public static String checkColorDongdauKyso(int status) {
		String result = "";
		switch (status) {
			case 10:
				// huy? thu? tuc THU TUC
				result = "class=\"wd-label-color-red\"";
				break;
			case 12:
				// cho phe duyet hoan thanh thu tuc - THU TUC
				result = "class=\"wd-label-color-orange\"";
				break;
			case 13:
				// yeu cau sua doi bo sung - THU TUC
				result = "style='background-color: rgb(236, 233, 18);'";
				break;
			case 18:
				// da tiep nhan - THU TUC
				result = "class=\"wd-label-color-light-green\"";
				break;
			case 19:
				// phe duyet hoan thanh thu tuc - THUC TUC
				result = "class=\"wd-label-color-dark-blue\"";
				break;
			case 20:
				// de nghi cap giay phep
				result = "class=\"wd-label-color-xanh\"";  
				break;
			case 0:
				result = "class=\"wd-label-color-tim\"";
				break;
			case 14:
				// Cho dong dau
				result = "class=\"wd-label-color-light-green\"";
				break;	
		}
		return result;
	}
	
	public static boolean checkDuyetHoSoNhapCanh(long documentName, int documentYear) {
		List<TempGeneralDeclaration> lstGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
		if (lstGeneralDeclaration == null || lstGeneralDeclaration.size() == 0) {
			return false;
		}
		List<TempNoticeShipMessage> lstNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear);
		if (lstNoticeShipMessage == null || lstNoticeShipMessage.size() == 0) {
			return false;
		}
		List<Integer> arrayBusinessTypeCode = Arrays.asList(10, 50, 51, 53);
		try {
			List<ResultDeclaration> resultType = ResultDeclarationLocalServiceUtil.findResultDeclarationByDocumentNameAndDocumentYearNXDoiChieu(documentName, documentYear);
			if (resultType != null && resultType.size() > 0) {
				for (ResultDeclaration resultDeclaType : resultType) {
					if (arrayBusinessTypeCode.contains(resultDeclaType.getBusinessTypeCode()) && resultDeclaType.getRequestState() != 9) {
						return false;
					}
				}
			}
		} catch (Exception e) {
		}
		return true;
	}
	
	//inland nhap canh
	public static boolean checkDuyetHoSoVaoCang(long documentName, int documentYear) {
		List<TempGeneralDeclaration> lstGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
		if (lstGeneralDeclaration == null || lstGeneralDeclaration.size() == 0) {
			return false;
		}
		List<TempNoticeShipMessage> lstNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear);
		if (lstNoticeShipMessage == null || lstNoticeShipMessage.size() == 0) {
			return false;
		}
		List<Integer> arrayBusinessTypeCode = Arrays.asList(10, 50, 51, 53);
		try {
			List<ResultDeclaration> resultType = ResultDeclarationLocalServiceUtil.findResultDeclarationByDocumentNameAndDocumentYearNXDoiChieu(documentName, documentYear);
			if (resultType != null && resultType.size() > 0) {
				for (ResultDeclaration resultDeclaType : resultType) {
					if (arrayBusinessTypeCode.contains(resultDeclaType.getBusinessTypeCode()) && resultDeclaType.getRequestState() != 9) {
						return false;
					}
				}
			}
		} catch (Exception e) {
		}
		return true;
	}
	
	
	
	public static boolean checkDuyetHoSoQuaCanh(long documentName, int documentYear) {
		List<TempGeneralDeclaration> tempGeneralDeclarations = TempGeneralDeclarationLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
		if (tempGeneralDeclarations == null || tempGeneralDeclarations.size() == 0) {
			return false;
		}
		List<TempNoticeShipMessage> noticeShipMessages = TempNoTiceShipMessageLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear);
		if (noticeShipMessages == null || noticeShipMessages.size() == 0) {
			return false;
		}
		List<Integer> arrayBusinessTypeCode = Arrays.asList(10, 50, 51, 52, 53);
		try {
			List<ResultDeclaration> resultType = ResultDeclarationLocalServiceUtil.findResultDeclarationByDocumentNameAndDocumentYearQCDoiChieu(documentName, documentYear);
			if (resultType != null && resultType.size() > 0) {
				for (ResultDeclaration resultDeclaType : resultType) {
					if (arrayBusinessTypeCode.contains(resultDeclaType.getBusinessTypeCode()) && resultDeclaType.getRequestState() != 9) {
						return false;
					}
				}
			}
		} catch (Exception e) {
		}
		return true;
	}
	
	public static boolean checkDuyetHoSoXuatCanh(long documentName, int documentYear) {
		List<TempGeneralDeclaration> tempGeneralDeclarations = TempGeneralDeclarationLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName,
				documentYear);
		if (tempGeneralDeclarations == null || tempGeneralDeclarations.size() == 0) {
			return false;
		}
		List<TempNoticeShipMessage> noticeShipMessages = TempNoTiceShipMessageLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName,
				documentYear);
		if (noticeShipMessages == null || noticeShipMessages.size() == 0) {
			return false;
		}
		List<Integer> arrayBusinessTypeCode = Arrays.asList(50, 51);
		try {
			List<ResultDeclaration> resultType = ResultDeclarationLocalServiceUtil.findResultDeclarationByDocumentNameAndDocumentYearNXDoiChieu(
					documentName, documentYear);
			if (resultType != null && resultType.size() > 0) {
				for (ResultDeclaration resultDeclaType : resultType) {
					if (arrayBusinessTypeCode.contains(resultDeclaType.getBusinessTypeCode()) && resultDeclaType.getRequestState() != 9) {
						return false;
					}
				}
			}
		} catch (Exception e) {
		}
		return true;
	}
	
	public static boolean checkDuyetHoSoRoiCang(long documentName, int documentYear) {
		log.info("=====checkDuyetHoSoRoiCang=====    =====documentName: " + documentName + "========documentYear: " + documentYear);
		List<TempGeneralDeclaration> tempGeneralDeclarations = TempGeneralDeclarationLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName,
				documentYear);
		if (tempGeneralDeclarations == null || tempGeneralDeclarations.size() == 0) {			
			return false;
		}
		
		List<Integer> arrayBusinessTypeCode = Arrays.asList(50, 51);
		try {
			List<ResultDeclaration> resultType = ResultDeclarationLocalServiceUtil.findResultDeclarationByDocumentNameAndDocumentYearNXDoiChieu(
					documentName, documentYear);
			if (resultType != null && resultType.size() > 0) {
				for (ResultDeclaration resultDeclaType : resultType) {
					if (arrayBusinessTypeCode.contains(resultDeclaType.getBusinessTypeCode()) && resultDeclaType.getRequestState() != 9) {						
						return false;
					}
				}
			}
		} catch (Exception e) {
		}
		return true;
	}
	
	public static int thaoTacCapBanKhai(TempDocument tempDocument, String requestCode) {
		int result = THAO_TAC_BAN_KHAI_CAP_BAN_KHAI;
		
		return result;
	}
	
	public static String getRequestCode(String requestCode, int documentName, int documentYear, int messageTypeReport) {
		
		try {
			
		//	log.info("VAO getRequestCode=========================== ");
			
			if (requestCode != null && requestCode.trim().length() > 0) {
//				log.info("TON TAI REQUEST CODE: requestCode==" + requestCode);
				return requestCode;
			} else {
				if (messageTypeReport == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
					List<TempShipSecurityMessage> shipSecurityMessages = TempShipSecurityMessageLocalServiceUtil.findBydocumentNameAnddocumentYear(
							documentName, documentYear);
					
					if (shipSecurityMessages != null && shipSecurityMessages.size() > 0) {
						return shipSecurityMessages.get(0).getRequestCode();
					}
				} else if (messageTypeReport == MessageType.THONG_BAO_TAU_DEN_CANG || messageTypeReport == MessageType.THONG_BAO_TAU_ROI_CANG
						|| messageTypeReport == MessageType.THONG_BAO_TAU_QUA_CANH) {
					List<TempNoticeShipMessage> noticeShipMegs = TempNoTiceShipMessageLocalServiceUtil
							.findBydocumentNameAndDocumentYearAndNoticeShipType(documentName, documentYear, PageType.TYPE_THONG_BAO_TAU_DEN_CANG);
					
					if (noticeShipMegs != null && noticeShipMegs.size() > 0) {
						return noticeShipMegs.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.XAC_BAO_TAU_DEN_CANG || messageTypeReport == MessageType.XAC_BAO_TAU_ROI_CANG
						|| messageTypeReport == MessageType.XAC_BAO_TAU_QUA_CANH) {
					List<TempNoticeShipMessage> noticeShipMegs = TempNoTiceShipMessageLocalServiceUtil
							.findBydocumentNameAndDocumentYearAndNoticeShipType(documentName, documentYear, PageType.TYPE_XAC_BAO_TAU_DEN_CANG);
					
					if (noticeShipMegs != null && noticeShipMegs.size() > 0) {
						return noticeShipMegs.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.BAN_KHAI_HANG_HOA) {
					List<TempCargoDeclaration> tempCargoDeclarations = TempCargoDeclarationLocalServiceUtil.findBydocumentNameAnddocumentYear(
							documentName, documentYear);
					
					if (tempCargoDeclarations != null && tempCargoDeclarations.size() > 0) {
						return tempCargoDeclarations.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN) {
					
					List<TempCrewList> crewLists = TempCrewListLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear);
					if (crewLists != null && crewLists.size() > 0) {
						return crewLists.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH) {
					List<TempPassengerList> tempPassengerLists = TempPassengerListLocalServiceUtil.findBydocumentNameAndDocumentYear(documentName,
							documentYear, 0, 1);
					
					if (tempPassengerLists != null && tempPassengerLists.size() > 0) {
						return tempPassengerLists.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT) {
					List<TempPlantQuarantine> resultsKiemDichThucVat = TempPlantQuarantineLocalServiceUtil.findBydocumentNameAnddocumentYear(
							documentName, documentYear);
					
					if (resultsKiemDichThucVat != null && resultsKiemDichThucVat.size() > 0) {
						return resultsKiemDichThucVat.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN) {
					// TODO Ban khai hanh ly thuyen vien Xem lai
					List<TempCrewEffectsDeclaration> resultsDsThuyenvien1 = TempCrewEffectsDeclarationLocalServiceUtil
							.findBydocumentNameAnddocumentYear(documentName, documentYear);
					
					if (resultsDsThuyenvien1 != null && resultsDsThuyenvien1.size() > 0) {
						return resultsDsThuyenvien1.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM) {
					List<TempDangerousGoodsManifest> nanifests = TempDangerousGoodsNanifestLocalServiceUtil.findBydocumentNameAnddocumentYear(
							documentName, documentYear);
					
					if (nanifests != null && nanifests.size() > 0) {
						return nanifests.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.LENH_DIEU_DONG) {
					List<IssueShiftingOrder> lIssueShiftingOrders = IssueShiftingOrderLocalServiceUtil
							.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
					
					if (lIssueShiftingOrders != null && lIssueShiftingOrders.size() > 0) {
						return lIssueShiftingOrders.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.THONG_BAO_TAU_ROI_CANG) {
					List<TempNoticeShipMessage> resultsThongBaoTauRoiCang = TempNoTiceShipMessageLocalServiceUtil.findBydocumentNameAnddocumentYear(
							documentName, documentYear);
					
					if (resultsThongBaoTauRoiCang != null && resultsThongBaoTauRoiCang.size() > 0) {
						return resultsThongBaoTauRoiCang.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT) {

					
				} else if (messageTypeReport == MessageType.BAN_KHAI_CHUNG) {
					List<TempGeneralDeclaration> resultsBanKhaiChung1 = TempGeneralDeclarationLocalServiceUtil.findByDocumentNameAndDocumentYear(
							documentName, documentYear);
					
					if (resultsBanKhaiChung1 != null && resultsBanKhaiChung1.size() > 0) {
						return resultsBanKhaiChung1.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.BAN_KHAI_DU_TRU_CUA_TAU) {
					List<TempShipStoresDeclaration> resultsBanKhaiDuTru = TempShipStoresDeclarationLocalServiceUtil
							.findBydocumentNameAnddocumentYear(documentName, documentYear);
					
					if (resultsBanKhaiDuTru != null && resultsBanKhaiDuTru.size() > 0) {
						return resultsBanKhaiDuTru.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.BAN_KHAI_HANH_LY_HANH_KHACH_ROI_TAU) {
					List<TempCrewEffectsDeclaration> resultsHanhLyHanhKhach = TempCrewEffectsDeclarationLocalServiceUtil
							.findBydocumentNameAnddocumentYear(documentName, documentYear);
					
					if (resultsHanhLyHanhKhach != null && resultsHanhLyHanhKhach.size() > 0) {
						return resultsHanhLyHanhKhach.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN) {
					List<IssuePortClearance> lstportClearances = IssuePortClearanceLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
					if (lstportClearances != null && lstportClearances.size() > 0) {
						return lstportClearances.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH) {
					List<IssuePortClearance> lstportClearances = IssuePortClearanceLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(
							documentName, documentYear, KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
					
					if (lstportClearances != null && lstportClearances.size() > 0) {
						return lstportClearances.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI) {
					List<TempDeclarationOfHealth> healths = TempDeclarationOfHealthLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName,
							documentYear);
					
					if (healths != null && healths.size() > 0) {
						return healths.get(0).getRequestCode();
					}
					
				} else if (messageTypeReport == MessageType.GIAY_PHEP_QUA_CANH) {
					List<IssuePermissionForTransit> lstPerForTransit = IssuePermissionForTransitLocalServiceUtil
							.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
					
					if (lstPerForTransit != null && lstPerForTransit.size() > 0) {
						return lstPerForTransit.get(0).getRequestCode();
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();log.error(e.getMessage());
		}
		return "";
	}
	
	public static String getTrangThaiHoSoDefaultForm(int typeCanBo) {
		List<DmGtStatus> gtStatus = DmGtStatusLocalServiceUtil.findByType(typeCanBo);
		if (gtStatus != null && gtStatus.size() > 0) {
			StringBuilder trangThai = new StringBuilder();
			for (DmGtStatus item : gtStatus) {
				trangThai.append(item.getStatusCode() + ",");
			}
			trangThai.append("0");
			return trangThai.toString().replace(",0", "");
		}
		return "";
	}
	
	public static String getTrangThaiHoSoChoKySoDongDau(int typeCanBo) {
		List<DmGtStatus> gtStatus = DmGtStatusLocalServiceUtil.findByType(typeCanBo);
		if (gtStatus != null && gtStatus.size() > 0) {
			StringBuilder trangThai = new StringBuilder();
			for (DmGtStatus item : gtStatus) {
				trangThai.append(item.getStatusCode() + ",");
			}
			trangThai.append("0");
			return trangThai.toString();
		}
		return "";
	}
	
	public static String getResponseStatus(int status) {
		String result = "";
		if (status == MessageType.CHO_PHE_DUYET) {
			result = ConfigurationManager.getStrProp("vn.gt.chopheduyet", "");
		} else if (status == MessageType.DA_PHE_DUYET) {
			result = ConfigurationManager.getStrProp("vn.gt.dapheduyet", "");
		} else if (status == MessageType.TU_CHOI) {
			result = ConfigurationManager.getStrProp("vn.gt.tuchoi", "");
		}
		return result;
	}
	
	public static String getListShipPositionCode(int roleCanBo, String loaiThuTuc) throws SystemException {
		
		String value = "";
		
		if (roleCanBo == MessageType.ROLE_KE_HOACH) {
			if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_NHAP_CANH)) {
				value = "1,2";
			} else if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_QUA_CANH)) {
				value = "1,2";
			}else if(loaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG) || loaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) ){
				value = "1,2";
			} else if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_XUAT_CANH) || MessageType.LOAT_THU_TUC_ROI_CANG.equals(loaiThuTuc) ||
					MessageType.LOAT_THU_TUC_ROI_CANG_PTTND.equals(loaiThuTuc)) {
				value = "3";
			}
		}
		
		if (roleCanBo == MessageType.ROLE_THU_TUC) {
			if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_NHAP_CANH) || loaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG)
					|| loaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) ) {
				value = "2";
			} else if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_QUA_CANH) ) {
				value = "2";
			} else if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_XUAT_CANH) || MessageType.LOAT_THU_TUC_ROI_CANG.equals(loaiThuTuc) ||
					MessageType.LOAT_THU_TUC_ROI_CANG_PTTND.equals(loaiThuTuc)) {
				value = "3";
			}
		}
		log.info("---------getListShipPositionCode-----" + value);
		
		return value;
	}
	
	public static boolean isChoPheDuyet(int status) {
		boolean result = false;
		if (status == MessageType.CHO_PHE_DUYET) {
			result = true;
		}
		return result;
	}
	
	public static String getSignTitle(String representative, String maritimeCode) {
		log.info("=====getSignTitle=====    =====representative: " + representative );
		String result = "";
		List<DmRepresentative> lstRepresentative = new ArrayList<DmRepresentative>();
		
		
		try {
			if (Validator.isNotNull(maritimeCode)) {
				lstRepresentative = DmRepresentativeLocalServiceUtil.findByMaritimeCode(maritimeCode);
			}
			
			if (lstRepresentative == null || lstRepresentative.size() == 0) {			
				lstRepresentative = DmRepresentativeLocalServiceUtil.getDmRepresentatives(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			}
			if (KeyParams.GIAM_DOC.equalsIgnoreCase(representative)){
				result = KeyParams.GIAM_DOC;
			}
			else {
				for (DmRepresentative item : lstRepresentative) {
					if (item.getRepName().equalsIgnoreCase(representative)) {
						switch (item.getRepLevel()) {
							case 1 :
							      // Pho Giam doc Cang vu Hang hai TP.
								  result = ConfigurationManager.getStrProp("vn.gt.label.representative.kythay.giam.doc", "") + "<br>";
								  result = result + ConfigurationManager.getStrProp("vn.gt.label.representative.pho.giam.doc", "");
								  result = result.toUpperCase();							   	  
							      break; // optional
							      
						   
						   case 2 :
							      // Truong dai dien Cang vu Hang hai TP.
							      result = ConfigurationManager.getStrProp("vn.gt.label.representative.thuauyquyen.giam.doc", "") + "<br>";							   	
							   	  result = result + ConfigurationManager.getStrProp("vn.gt.label.representative.truong.dai.dien", "") + "<br>";
							   	  result = result + representative.toUpperCase().replace(ConfigurationManager.getStrProp("vn.gt.label.representative.truong.dai.dien", ""), "");
							      break; // optional
						   
						   case 3 :
							      // Pho Truong dai dien Cang vu Hang hai TP.
							   	  result = ConfigurationManager.getStrProp("vn.gt.label.representative.thuauyquyen.giam.doc", "") + "<br>";							   	
							   	  result = result + ConfigurationManager.getStrProp("vn.gt.label.representative.pho.truong.dai.dien", "") + "<br>";
							   	  result = result + representative.toUpperCase().replace(ConfigurationManager.getStrProp("vn.gt.label.representative.pho.truong.dai.dien", ""), "");							   	  
							      break; // optional
							
						   case 4 :
							      // Truong phong Phap che - Cang vu Hang hai TP.
							      result = ConfigurationManager.getStrProp("vn.gt.label.representative.thuauyquyen.giam.doc", "") + "<br>";
							   	  result = result + representative.toUpperCase();
							      break; // optional
							   
						   case 5 :
							      // Pho Truong phong Phap che - Cang vu Hang hai TP.
							      result = ConfigurationManager.getStrProp("vn.gt.label.representative.thuauyquyen.giam.doc", "") + "<br>";
							   	  result = result + representative.toUpperCase();
							      /*
							      result = ConfigurationManager.getStrProp("vn.gt.label.representative.thualenh.giam.doc", "") + "<br>";
							   	  result = result + representative.toUpperCase().replace(ConfigurationManager.getStrProp("vn.gt.label.representative.pho.truong.phong", ""), ConfigurationManager.getStrProp("vn.gt.label.representative.kythay.truong.phong", ""));
							   	  result = result  + "<br>" + ConfigurationManager.getStrProp("vn.gt.label.representative.pho.truong.phong", "");
							   	  */
							      break; // optional  
						   
						   // You can have any number of case statements.
						   default : // Optional
						      result = representative.toUpperCase();
						      if (representative.length() == 0) {
						    	  result = KeyParams.GIAM_DOC;
						      }
						}
						
						
					}
				}
			}
			
			
		} catch (Exception e) {
			result = "";
			return result;
		}
		return result;
	}
	
	public static boolean checkValidateDocument(Header header,
			List<Object> liObjects) {
		log.debug("==checkValidateDocument==NoticeOfArrival, GeneralDeclaration, ShipSecurityNotification");
		// SONVH them 5/3/2019
		log.info("==checkValidateDocument==liObjects==========" + liObjects.toString());
		try {
			long documentName = 0;
			int documentYear = 0;
			String imo = StringPool.BLANK;
			String maritimeCode = StringPool.BLANK;
			String ShipAgencyCode = StringPool.BLANK;

			for (Object object : liObjects) {
				boolean flagCheckValidate = false;
				if (object instanceof ShipSecurityNotification) {
					log.info("====checkValidateDocument==NoticeOfArrival, GeneralDeclaration, ShipSecurityNotification==********* "
							+ object.getClass().getName());
					flagCheckValidate = true;
					documentName = Long.valueOf(ShipSecurityNotification.class
							.cast(object).getDocumentName() + "");
					documentYear = Integer.valueOf(ShipSecurityNotification.class
							.cast(object).getDocumentYear() + "");
					imo = ShipSecurityNotification.class.cast(object)
							.getIMONumber();
					maritimeCode = StringPool.BLANK;
					ShipAgencyCode = ShipSecurityNotification.class
							.cast(object).getTaxCodeOfShipAgent();
					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
							.getByPortRegionCode(ShipSecurityNotification.class
									.cast(object).getPortRegionCode());
					if (dmPortRegion != null) {
						maritimeCode = dmPortRegion.getPortRegionRef();
					} else {
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
								.getByPortWharfCode(ShipSecurityNotification.class
										.cast(object).getPortRegionCode());
						if (dmPortWharf != null) {
							DmPortRegion dmPortRegion1 = DmPortRegionLocalServiceUtil
									.getByPortRegionCode(dmPortWharf
											.getPortRegionCode());
							if (dmPortRegion1 != null) {
								maritimeCode = dmPortRegion1.getPortRegionRef();
							}
						}
					}

				} else if (object instanceof NoticeOfArrival) {
					log.info("====checkValidateDocument==NoticeOfArrival, GeneralDeclaration, ShipSecurityNotification==********* "
							+ object.getClass().getName());
					flagCheckValidate = true;
					documentName = Long.valueOf(NoticeOfArrival.class.cast(
							object).getDocumentName()
							+ "");
					documentYear = Integer.valueOf(NoticeOfArrival.class.cast(
							object).getDocumentYear()
							+ "");
					imo = NoticeOfArrival.class.cast(object).getIMONumber();
					maritimeCode = StringPool.BLANK;
					ShipAgencyCode = NoticeOfArrival.class.cast(object)
							.getTaxCodeOfShipownersAgents();

					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
							.getByPortRegionCode(NoticeOfArrival.class.cast(
									object).getPortRegionCode());
					if (dmPortRegion != null) {
						maritimeCode = dmPortRegion.getPortRegionRef();
					} else {
						DmPort dmPort = DmPortLocalServiceUtil
								.getByPortCode(NoticeOfArrival.class.cast(
										object).getPortOfArrivalCode());
						if (dmPort != null) {
							maritimeCode = dmPort.getLoCode();
						} else {
							DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
									.getByPortWharfCode(NoticeOfArrival.class
											.cast(object).getPortRegionCode());
							if (dmPortWharf != null) {
								DmPortRegion dmPortRegion12 = DmPortRegionLocalServiceUtil
										.getByPortRegionCode(dmPortWharf
												.getPortRegionCode());
								if (dmPortRegion12 != null) {
									maritimeCode = dmPortRegion12
											.getPortRegionRef();
								}
							} else {
								DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
										.getByPortHarbourCode(NoticeOfArrival.class
												.cast(object)
												.getPortHarbourCode());
								if (dmPortHarbour != null) {
									maritimeCode = dmPortHarbour
											.getPortRegionCode();
								}
							}
						}
					}

				} else if (object instanceof ConfirmationOfArrival) {
					log.info("====checkValidateDocument==ConfirmationOfArrival, GeneralDeclaration, ShipSecurityNotification==********* "
							+ object.getClass().getName());
					flagCheckValidate = true;
					documentName = Long.valueOf(ConfirmationOfArrival.class.cast(
							object).getDocumentName()
							+ "");
					documentYear = Integer.valueOf(ConfirmationOfArrival.class.cast(
							object).getDocumentYear()
							+ "");
					imo = ConfirmationOfArrival.class.cast(object).getIMONumber();
					maritimeCode = StringPool.BLANK;
					ShipAgencyCode = ConfirmationOfArrival.class.cast(object)
							.getUserCreated();

					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
							.getByPortRegionCode(ConfirmationOfArrival.class.cast(
									object).getPortRegionCode());
					if (dmPortRegion != null) {
						maritimeCode = dmPortRegion.getPortRegionRef();
					} else {
						DmPort dmPort = DmPortLocalServiceUtil
								.getByPortCode(ConfirmationOfArrival.class.cast(
										object).getPortOfArrivalCode());
						if (dmPort != null) {
							maritimeCode = dmPort.getLoCode();
						} else {
							DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
									.getByPortWharfCode(ConfirmationOfArrival.class
											.cast(object).getPortRegionCode());
							if (dmPortWharf != null) {
								DmPortRegion dmPortRegion12 = DmPortRegionLocalServiceUtil
										.getByPortRegionCode(dmPortWharf
												.getPortRegionCode());
								if (dmPortRegion12 != null) {
									maritimeCode = dmPortRegion12
											.getPortRegionRef();
								}
							} else {
								DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
										.getByPortHarbourCode(ConfirmationOfArrival.class
												.cast(object)
												.getPortHarbourCode());
								if (dmPortHarbour != null) {
									maritimeCode = dmPortHarbour
											.getPortRegionCode();
								}
							}
						}
					}

				} else if (object instanceof TempCargoDeclaration) {
					log.info("====checkValidateDocument==TempCargoDeclaration, GeneralDeclaration, ShipSecurityNotification==********* "
							+ object.getClass().getName());
					flagCheckValidate = true;
					documentName = Long.valueOf(TempCargoDeclaration.class.cast(
							object).getDocumentName()
							+ "");
					documentYear = Integer.valueOf(TempCargoDeclaration.class.cast(
							object).getDocumentYear()
							+ "");
					imo = TempCargoDeclaration.class.cast(object).getImoNumber();
					maritimeCode = StringPool.BLANK;
					ShipAgencyCode = TempCargoDeclaration.class.cast(object)
							.getUserCreated();

				} else if (object instanceof GeneralDeclaration) {

					log.info("====checkValidateDocument==NoticeOfArrival, GeneralDeclaration, ShipSecurityNotification==********* "
							+ object.getClass().getName());
					flagCheckValidate = true;
					documentName = Long.valueOf(GeneralDeclaration.class.cast(
							object).getDocumentName()
							+ "");
					documentYear = Integer.valueOf(GeneralDeclaration.class.cast(
							object).getDocumentYear()
							+ "");
					imo = GeneralDeclaration.class.cast(object).getIMONumber();
					maritimeCode = StringPool.BLANK;
					ShipAgencyCode = GeneralDeclaration.class.cast(object)
							.getTaxCodeOfShipownersAgents();

					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
							.getByPortRegionCode(GeneralDeclaration.class.cast(
									object).getPortRegionCode());
					if (dmPortRegion != null) {
						maritimeCode = dmPortRegion.getPortRegionRef();
					} else {
						DmPort dmPort = DmPortLocalServiceUtil
								.getByPortCode(GeneralDeclaration.class.cast(
										object).getPortOfArrivalCode());
						if (dmPort != null) {
							maritimeCode = dmPort.getLoCode();
						} else {
							DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
									.getByPortWharfCode(GeneralDeclaration.class
											.cast(object).getPortRegionCode());
							if (dmPortWharf != null) {
								DmPortRegion dmPortRegion12 = DmPortRegionLocalServiceUtil
										.getByPortRegionCode(dmPortWharf
												.getPortRegionCode());
								if (dmPortRegion12 != null) {
									maritimeCode = dmPortRegion12
											.getPortRegionRef();
								}
							} else {
								DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
										.getByPortHarbourCode(GeneralDeclaration.class
												.cast(object)
												.getPortHarbourCode());
								if (dmPortHarbour != null) {
									maritimeCode = dmPortHarbour
											.getPortRegionCode();
								}
							}
						}
					}

				}

				if (flagCheckValidate == true) {
					if (maritimeCode == null
							|| (maritimeCode != null && maritimeCode.trim()
									.length() == 0)) {
						maritimeCode = StringPool.BLANK;
					}
					boolean result = TempDocumentLocalServiceUtil
							.isValid_ShipAgencyCode_IMO_MaritimeCode(
									documentName, documentYear, imo,
									maritimeCode, ShipAgencyCode);
					
					if (result == false) {
						return false;
					}
				}

			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

}
