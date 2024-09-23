package vn.gt.portlet.kehoach.utils;

import com.fds.nsw.kernel.exception.SystemException;


import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.flex.common.ultility.Validator;

import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ChuyenDichTrangThaiUtils {


	public static final int GIAY_TO_YEU_CAU_XUAT_TRINH = 1991;
	public static final int GIAY_TO_YEU_CAU_BO_SUNG = 1992;
	public static final int GIAY_TO_CHAP_NHAN = 1993;
	public static final int GIAY_TO_TU_CHOI = 1994;
	
	public static final int THANH_PHAN_HO_SO = 1985;
	
	public static final String DOCUMENT_NAME = "DOCUMENT_NAME";
	public static final String DOCUMENT_YEAR = "DOCUMENT_YEAR";
	public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";
	public static final String MESSAGE_TYPE = "MESSAGE_TYPE";
	
	public static final String DES_STATUS = "DES_STATUS";
	public static final String REQUEST_STATE = "REQUEST_STATE";
	public static final String DOCUMENT_STATUS_CODE = "DOCUMENT_STATUS_CODE";
	public static final String REQUEST_CODE = "REQUEST_CODE";
	public static final String ACTION_TYPE = "ACTION_TYPE";
	
	public static final int CHUYEN_DICH_THAT_BAI = -1;
	
	public static final int KE_HOACH_CHO_TIEP_NHAN = 11;
	public static final int KE_HOACH_CHO_CAP_LENH_DIEU_DONG = 14;
	public static final int KE_HOACH_DA_CAP_LENH_DIEU_DONG = 15;
	public static final int KE_HOACH_DA_HUY_LENH_DIEU_DONG = 16;
	public static final int KE_HOACH_DA_TIEP_NHAN = 12;
	public static final int KE_HOACH_TU_CHOI_TIEP_NHAN = 13;
	public static final int KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG = 27;
	public static final int KE_HOACH_CHO_SUA_LENH_DIEU_DONG = 114;
	
	public static final int THU_TUC_CHO_PHE_DUYET_HOAN_THANH_THU_TUC = 12;
	public static final int THU_TUC_YEU_CAU_SUA_DOI_BO_SUNG = 13;
	public static final int THU_TUC_DA_TIEP_NHAN = 18;
	public static final int THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC = 19;
	public static final int THU_TUC_HUY_THU_TUC = 10;
	public static final int THU_TUC_DE_NGHI_CAP_GIAY_PHEP = 20;
	public static final int THU_TUC_DE_NGHI_SUA_GIAY_PHEP = 120;
	public static final int THU_TUC_TAM_DUNG_LAM_THU_TUC_DIEN_TU = 25;
	
	public static final int BAN_KHAI_KHAI_MOI = 1;
	public static final int BAN_KHAI_KHAI_SUA = 2;
	public static final int BAN_KHAI_SUA_BO_SUNG = 3;
	public static final int BAN_KHAI_CHAP_NHAN_BAN_KHAI = 4;
	public static final int BAN_KHAI_TU_CHOI_BAN_KHAI = 5;
	public static final int BAN_KHAI_KHAI_HUY = 6;
	public static final int BAN_KHAI_DA_DUYET = 9;
	
	public static final String TAU_THUYEN_NHAP_CANH = "NC";
	public static final String TAU_THUYEN_XUAT_CANH = "XC";
	public static final String TAU_THUYEN_NHAP_CANH_6 = "16";
	public static final String TAU_THUYEN_XUAT_CANH_7 = "17";
	public static final String TAU_THUYEN_QUA_CANH = "QC";
	public static final String NHAP_CANH_DAU_KHI = "8";
	public static final String XUAT_CANH_DAU_KHI = "9";
	public static final String VAO_CANH_DAU_KHI = "10";
	public static final String ROI_CANH_DAU_KHI = "11";
	public static final String CHUYEN_CANG_VAO = "12";
	public static final String CHUYEN_CANG_ROI = "13";
	public static final String VIET_CAM_NHAP_CANH = "14";
	public static final String VIET_CAM_XUAT_CANH = "15";
	public static final String TAU_THUYEN_VAO_CANG = "4";
	public static final String TAU_THUYEN_ROI_CANG = "5";
	
	public static final int DANH_DAU_VAO_ROI = 1;
	public static final int DANH_DAU_LUU_NHAP = 2;
	public static final int LAP_KE_HOACH_DIEU_DONG = 3;
	public static final int TAO_GIAY_PHEP_ROI_CANG = 3;
	public static final int TAO_GIAY_PHEP_QUA_CANH = 3;
	public static final int DANH_DAU_CHO_LANH_DAO_KY_BCY = 4;
	public static final int DANH_DAU_CHO_DONG_DAU_BCY = 5;
	public static final int DANH_DAU_DA_HUY = 6;
	public static final int DANH_DAU_LANH_DAO_TRA_LAI = 7;
	public static final int DANH_DAU_DA_DUYET = 8;
	public static final int DANH_DAU_DA_KY_DUYET = 9;
	public static final int DANH_DAU_KHONG_CO = 0;
	
	public static int doHoSo(long documentName, int documentYear, int desStatus, boolean isKeHoach) {
		int result = CHUYEN_DICH_THAT_BAI;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		if (Validator.isNotNull(tempDocument)) {
			
			log.info("CHUYEN_DICH_TRANG_THAI_HO_SO BAT DAU VS HO SO: documentName= " + documentName + " & documentYear=" + documentYear);
			
			if (isKeHoach) {
				tempDocument.setRequestState(desStatus);
			} else {
				tempDocument.setDocumentStatusCode(desStatus);
			}
			
			try {
				
				tempDocument = TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
				result = desStatus;
				
			} catch (SystemException e) {
				log.error(e.getMessage());
			}
			log.info("CHUYEN_DICH_TRANG_THAI_HO_SO SUCCESS!");
			log.info("CHUYEN_DICH_TRANG_THAI_HO_SO TO: " + desStatus);
		} else {
			log.info("CHUYEN_DICH_TRANG_THAI_HO_SO THAT BAI! ---------- KHONG TIM THAY TEMP_DOCUMENT BY: documentName= " + documentName + " & documentYear=" + documentYear);
		}
		
		return result;
	}

	public static int doBanKhai(UploadPortletRequest uploadPortletRequest, long documentName, int documentYear, int desStatus) {
		int result = CHUYEN_DICH_THAT_BAI;
		
		return result;
	}
	
	
}
