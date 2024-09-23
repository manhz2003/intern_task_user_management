/**
 * 
 */
package com.fds.nsw.nghiepvu.util;

/**
 * @author win_64
 */
public class MessageType {
	
	public static final int CHAM_PHE_DUYET_HOAN_THANH_THU_THUC = 95;
	public static final int BAN_KHAI_CHUNG_TU_DINH_KEM = 64;
	public static final int BAN_KHAI_KET_QUA_XEM_XET_CHUNG_TU_DINH_KEM = 65;
	 
	public static final int NHAP_CANH = 1;
	public static final int XUAT_CANH = 2;
	public static final int QUA_CANH = 3;
	public static final int TAU_VAO = 4;
	public static final int TAU_RA = 5;
	public static final int TAU_VAO_PTTND = 16;
	public static final int TAU_RA_PTTND = 17;
	public static final int NHAP_CANG_DAU_KHI = 8;
	public static final int XUAT_CANG_DAU_KHI = 9;
	public static final int VAO_CANG_DAU_KHI = 10;
	public static final int ROI_CANG_DAU_KHI = 11;
	public static final int CHUYEN_CANG_VAO = 12;
	public static final int CHUYEN_CANG_ROI = 13;
	public static final int PTTND_NHAP_CANH = 14;
	public static final int PTTND_XUAT_CANH = 15;
	
	// Danh sach document type he thon xu ly
	public static final int[] DOCUMENT_TYPE = new int[]{NHAP_CANH, XUAT_CANH, QUA_CANH, TAU_VAO, TAU_RA, NHAP_CANG_DAU_KHI, 
			XUAT_CANG_DAU_KHI, VAO_CANG_DAU_KHI, ROI_CANG_DAU_KHI, CHUYEN_CANG_VAO, CHUYEN_CANG_ROI, TAU_VAO_PTTND, TAU_RA_PTTND, 
			PTTND_NHAP_CANH, PTTND_XUAT_CANH};
	
	public static final int CHAP_NHAN_BAN_KHAI = 4;
	
	public static final int DA_RUYET = 9;
	
	public static final int SHIP_POSSITION_DEN_CANG = 1;// Tau den Cang
	public static final int SHIP_POSSITION_TRONG_CANG = 2;// Tau trong Cang
	public static final int SHIP_POSSITION_ROI_CANG = 3;// Tau roi Cang
	
	public static final int BO_NGANH_PHE_DUYET_HO_SO = 26; // NoticeShipType = 1
	
	public static final int BAN_KHAI_AN_NINH_TAU_BIEN = 10;
	public static final int BAN_KHAI_HANG_HOA = 20;
	
	
	public static final int THONG_BAO_TAU_DEN_CANG = 30; // NoticeShipType = 1
	public static final int THONG_BAO_TAU_ROI_CANG = 31; // NoticeShipType = 1
	public static final int THONG_BAO_TAU_QUA_CANH = 32; // NoticeShipType = 1
	
	public static final int XAC_BAO_TAU_DEN_CANG = 40; // NoticeShipType = 2
	public static final int XAC_BAO_TAU_ROI_CANG = 41; // NoticeShipType = 2, ko co xac bao tau roi cang
	public static final int XAC_BAO_TAU_QUA_CANH = 42; // NoticeShipType = 2
	
	public static final int HO_SO = 99;
	public static final int BAN_KHAI_CHUNG = 50;
	public static final int BAN_KHAI_DANH_SACH_THUYEN_VIEN = 51;
	public static final int BAN_KHAI_DANH_SACH_HANH_KHACH = 52;
	public static final int BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM = 53;
	public static final int BAN_KHAI_DU_TRU_CUA_TAU = 54;
	public static final int BAN_KHAI_HANH_LY_THUYEN_VIEN = 55;
	public static final int BAN_KHAI_BAO_Y_TE_HANG_HAI = 56;
	public static final int BAN_KHAI_KIEM_DICH_THUC_VAT = 57;
	public static final int BAN_KHAI_KIEM_DICH_DONG_VAT = 58;
	
	public static final int BAN_KHAI_HANH_LY_HANH_KHACH_ROI_TAU = 59;
	public static final int GIAY_PHEP_ROI_CANG_CHO_TAU_DEN = 61;
	public static final int PTTND_GIAY_PHEP_VAO_CANG = 81;
	public static final int GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH = 60;
	public static final int GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH_VIET_CAM = 72;
	public static final int HUY_GIAY_PHEP_ROI_CANG = 62;
	public static final int HUY_GIAY_PHEP_VAO_CANG = 82;
	
	public static final int CAC_CHUNG_TU_DINH_KEM = 64;
	public static final int TIN_NHAN_PTTND_VAOROI = 66; 

	public static final int GIAY_PHEP_ROI_CANG_CUOI_CUNG = 2018;
	public static final int CAC_GIAY_TO_PHAI_XUAT_TRINH = 2014;
	public static final int LENH_DIEU_DONG = 70;
	public static final int HUY_LENH_DIEU_DONG = 71;
	public static final int THONG_BAO_CHO_PHEP_TAU_QUA_CANH = 80;
	public static final int GIAY_PHEP_QUA_CANH = 90;
	public static final int THONG_BAO_HUY_XAC_BAO = 92;
	public static final int XAC_NHAN_THONG_QUAN = 91;
	public static final int PHE_DUYET_TU_CO_QUAN_BAN_NGANH = 26;
	public static final int XAC_NHAN_HUY_LENH_DIEU_DONG = 71;
	public static final int HUY_GIAY_PHEP_QUA_CANH = 93;
	public static final int KHAI_HUY_HO_SO = 99;
	public static final int Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG = 83;
	public static final int Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG = 63;
	public static final int Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH = 94;
	public static final int Y_CAU_TRA_LAI_HO_SO = 84;
	public static final int Y_CAU_TRA_LAI_HO_SO_LENH_DIEU_DONG = 9970;
	public static final int Y_CAU_TRA_LAI_HO_SO_GP_QUA_CANH = 9990;
	public static final int Y_CAU_TRA_LAI_HO_SO_GP_XUAT_CANH = 9960;
	public static final int Y_CAU_TRA_LAI_HO_SO_PTTND_GIAY_PHEP_VAO_CANG = 9981;
	public static final int Y_CAU_TRA_LAI_HO_SO_GIAY_PHEP_ROI_CANG_CHO_TAU_DEN = 9961;
	
	// public static final int THONG_BAO_DA_NHAN_BAN_TIN = 99;
	public static final String SHIPSECURITY_NOTIFICATION = "ShipSecurityNotification";
	public static final String INLANDNOTICEARRIVAL = "InlandNoticeOfArrival";
	
	public static final String CARGO_DECLARATION = "CargoDeclaration";
	public static final String NOTICE_OF_ARRIVAL = "NoticeOfArrival";
	public static final String GENERAL_DECLARATION = "GeneralDeclaration";
	public static final String InLandGeneralDeclaration = "InLandGeneralDeclaration";
	public static final String CONFIRMATION_OF_ARRIVAL = "ConfirmationOfArrival";
	public static final String FEE_NOTICE = "FeeNotice";
	public static final String FEE_APPROVED = "FeeApproved";
	
	public static final String CREW_LIST = "CrewLists";
	public static final String InlandCrewLists = "InlandCrewLists";
	
	public static final String PASSENGER_LIST = "PassengerList";
	public static final String InlandPassengerList = "InlandPassengerList";
	public static final String PTTNDPassengerList = "PTTNDPassengerList";
	public static final String PTTNDGeneralDeclaration = "PTTNDGeneralDeclaration";
	public static final String PTTNDAcceptance = "PTTNDAcceptance";
	public static final String InlandCrewCallCenter = "InlandCrewCallCenter";
	
	public static final String DANGEROUS_GOODS_MANIFEST = "DangerousGoodsManifest";
	public static final String SHIPS_STORES_DECLARATION = "ShipsStoresDeclaration";
	public static final String CREW_EFFECTS_DECLARATION = "CrewEffectsDeclaration";
	public static final String DECLARATION_FOR_PLANT_QUARANTINE = "DeclarationForPlantQuarantine";
	public static final String DECLARATION_FOR_ANIMAL_QUARANTINE = "DeclarationForAnimalQuarantine";
	public static final String HEALTH_QUANRANTINE_DECLARE = "HealthQuanrantineDeclare";
	public static final String SHIFTING_ORDER = "ShiftingOrder";
	public static final String PORT_CLEARANCE = "PortClearance";
	public static final String PTTNDPortClearance = "PTTNDPortClearance";
	public static final String PERMISSION_FOR_TRANSIT = "PermissionForTransit";
	public static final String ACCEPTANCE_FOR_TRANSIT = "AcceptanceForTransit";
	public static final String ATTACHMENT = "Attachment";
	
	public static final int FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST = 99;
	public static final int FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_ERROR_VALIDATION = 22;
	
	public static final String LOAT_THU_TUC_NHAP_CANH = "NC";
	public static final String LOAT_THU_TUC_QUA_CANH = "QC";
	public static final String LOAT_THU_TUC_XUAT_CANH = "XC";

	public static final String LOAT_THU_TUC_CHO_DONG_DAU = "LOAT_THU_TUC_CHO_DONG_DAU"; 
	public static final String LOAT_THU_TUC_DANG_DI_CHUYEN = "LOAT_THU_TUC_DANG_DI_CHUYEN";
	public static final String LOAT_THU_TUC_CHO_KY_SO = "LOAT_THU_TUC_CHO_KY_SO"; 
	public static final String LOAT_THU_TUC = "LOAT_THU_TUC";
	public static final String LOAT_THU_TUC_TEN = "LOAT_THU_TUC_TEN";
	public static final String LOAT_THU_TUC_VAO_CANG = "4";
	public static final String LOAT_THU_TUC_ROI_CANG = "5";
	public static final String LOAT_THU_TUC_NHAP_CANH_PTTND = "14";
	public static final String LOAT_THU_TUC_XUAT_CANH_PTTND = "15";
	public static final String LOAT_THU_TUC_VAO_CANG_PTTND = "16";
	public static final String LOAT_THU_TUC_ROI_CANG_PTTND = "17";
	public static final String LOAT_THU_TUC_8 = "8";
	public static final String LOAT_THU_TUC_10 = "10";
	public static final String LOAT_THU_TUC_12 = "12";
	public static final String LOAT_THU_TUC_9 = "9";
	public static final String LOAT_THU_TUC_11 = "11";
	public static final String LOAT_THU_TUC_13 = "13";
	
	public static final String FUNCTION_KHAI_BAO = "01";
	public static final String FUNCTION_KHAI_SUA = "02";
	public static final String FUNCTION_BO_SUNG_CHUNG_TU = "05";
	public static final String FUNCTION_KHAI_HUY_HO_SO = "03";
	public static final String FUNCTION_DE_NGHI_CAP_LAI = "04";
	public static final String FUNCTION_KHAI_HUY_HO_SO_THU_TUC = "10";
	
	public static final String FUNCTION_KHAI_HUY_XAC_BAO = "04";
	public static final String FUNCTION_HUY_LENH_DIEU_DONG = "03";
	public static final String FUNCTION_KHAI_HOI_KET_QUA = "11";
	public static final String FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU = "21";
	public static final String FUNCTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU = "25";
	public static final String FUNCTION_THONG_BAO_BO_SUNG_HO_SO_CHUNG_TU_KINH_KEM = "29";
	public static final String FUNCTION_THONG_BAO_CHAP_NHAN_HO_SO_CHUNG_TU_KINH_KEM = "01";
	public static final String FUNCTION_THONG_BAO_NOP_TRUC_TIEP = "20";
	public static final String FUNCTION_TU_CHOI_HO_SO_CHUNG_TU = "22";
	public static final String FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC = "23";
	public static final String FUNCTION_XAC_NHAN_HUY_THU_TUC = "24";
	public static final String FUNCTION_TAM_DUNG = "35";
	public static final String FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH = "26";
	public static final String FUNCTION_THONG_BAO_DA_NHAN_DUOC = "99";
	public static final String FUNCTION_THONG_BAO_BO_XUNG = "27";
	public static final String FUNCTION_THONG_BAO_BO_SUNG = "25";
	public static final String FUNCTION_TU_SUA_DOI_BO_XUNG_THU_TUC = "28";
	public static final String FUNCTION_TU_SUA_DOI_BO_SUNG_THU_TUC = "25";
	public static final String FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH = "2014";
	public static final int TYPE_THONG_BAO_NOP_PHI = 120;
	public static final int TYPE_THONG_BAO_XAC_NHAN_NOP_PHI = 121;
	
	public static final String FUNCTION_THONG_BAO_NOP_PHI = "30";
	public static final String FUNCTION_THONG_BAO_XAC_NHAN_NOP_PHI = "31";
	
	public static final String REQUEST_DIRECTION_IN = "IN";
	public static final String REQUEST_DIRECTION_OUT = "OUT";
	
	public static final String REQUEST_DIRECTION_OUT_CVHH = "OUTCV";
	
	public static final String REQUEST_DIRECTION_CV_TO_HQ = "CVHQ";
	
	// Binh confirm rang thay doi trang thai bang khai
	public static final int XAC_NHAN_HOAN_THANH_THU_TUC = 23;
	// Tiepnhan =4
	public static final int TRANG_THAI_BANG_KHAI_TIEP_NHAN = 4;
	// Doi chieu =9
	public static final int TRANG_THAI_BANG_KHAI_DOI_CHIEU = 9;
	
	public static final int TRANG_THAI_BANG_KHAI_TU_CHOI = 5;
	
	public static final int TRANG_THAI_BANG_KHAI_HUY = 6;
	
	// Cho phe duyet hoan thanh thu tuc = 12
	public static final int TRANG_THAI_BANG_KHAI_CHO_PHE_DUYET_HOAN_THANH_THU_TUC = 12;
	public static final int TRANG_THAI_BANG_SUA_DOI_BO_SUNG = 3;
	
	// Loai thu tuc
	public static final int ROLE_KE_HOACH = 2;
	public static final int ROLE_THU_TUC = 4;
	public static final int TRANG_THAI_BAN_KHAI = 3;
	
	// Xac nhan thong quan
	public static final String FLAG_XANH = "0";
	public static final String FLAG_DO = "1";
	
	public static final int CHO_PHE_DUYET = 0;
	public static final int DA_PHE_DUYET = 1;
	public static final int TU_CHOI = 2;
	
	public static final int FUNCTION_CHAP_NHAN_HO_SO = 21;
	public static final int FUNCTION_TU_CHOI_HO_SO = 22;
	public static final int FUNCTION_XAC_NHAN_HOAN_THANH = 23;
	public static final int FUNCTION_XAC_NHAN_HUY = 24;
	public static final int FUNCTION_KHAI_HUY_THU_TUC = 3;
	public static final int FUNCTION_BO_NGANH_PHE_DUYET = 26;
	public static final int FUNCTION_LENH_DIEU_DONG_GIAY_PHEP_ROI_CANG = 1;
	public static final int BO_SUNG_KE_HOACH = 9927;
	public static final int BO_SUNG_THU_TUC = 9928;
	public static final int YC_LAM_THU_TUC_TRUC_TIEP = 9920;
	public static final int FUNCTION_YEU_CAU_BO_SUNG_KE_HOACH = 27;
	public static final int FUNCTION_YC_BO_SUNG = 25;
	public static final int FUNCTION_YEU_CAU_BO_SUNG_THU_TUC = 28;
	
	public static final String FUNCTION_DONG_BO_DM_CANG_HAI_QUAN = "PortList";
	
	public static final int NHOM_DM_CANG_BIEN_HANG_HAI = 107;
	
	public static final int DONG_BO_DM_QUOC_GIA = 137;
	public static final int DONG_BO_DM_TINH_THANH = 138;
	public static final int DONG_BO_DM_QUAN_HUYEN = 139;
	public static final int DONG_BO_DM_CANG_BEN = 140;
	public static final int DONG_BO_DM_KHU_VUC_CANG = 101;
	public static final int DONG_BO_DM_BEN_CANG = 102;
	public static final int DONG_BO_DM_CAU_CANG = 103;
	public static final int DONG_BO_DM_CANG_HAI_QUAN = 104;
	public static final int DONG_BO_DM_CANG_BIEN_HANG_HAI = 105;
	public static final int DONG_BO_DM_HANG_HOA_HANG_HAI = 106;
	public static final int DONG_BO_DM_CANG_VU_HANG_HAI = 100;
	
}
