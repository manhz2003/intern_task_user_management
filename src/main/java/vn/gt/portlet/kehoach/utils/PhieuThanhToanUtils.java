package vn.gt.portlet.kehoach.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.kernel.exception.SystemException;


import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.core.ThemeDisplay;

import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDebitnote;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.portlet.kehoach.tt1.TT1TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt10.TT10TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt11.TT11TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt14.TT14TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt15.TT15TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt16.TT16TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt17.TT17TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt2.TT2TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt3.TT3TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt4.TT4TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt5.TT5TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt6.TT6TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt7.TT7TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt8.TT8TichHopMessageUtils;
import vn.gt.portlet.kehoach.tt9.TT9TichHopMessageUtils;
import vn.gt.portlet.phuongtien.VmaItineraryUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class PhieuThanhToanUtils {

	
	
	public TempDebitnote doTempDebitNote(long documentName, int documentYear, String corporationid,
			String debitnotenumber, String division, String financialaccountant, Date fromdate, int markasdeleted,
			String organization, int paymenttype, String reportby, Date reportdate, Date todate, long totalpayment,
			String transactionid, User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {
		TempDebitnote result = null;

		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName,
				documentYear);

		reportdate = new Date();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		debitnotenumber = timestamp.getTime() + StringPool.BLANK;
		corporationid = tempDocument.getMaritimeCode();
		
		String currency = ParamUtil.getString(uploadPortletRequest, "currency");
		//Add by TrungNT
		//String itineraryNo = ParamUtil.getString(uploadPortletRequest, "itineraryNo", "---");
		//Check active
		String itineraryNo = "---";
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);
		boolean isUpdateVmaItinerary = false;
		if(VmaItineraryUtils.checkActiveVma(themeDisplay)){
			itineraryNo = VmaItineraryUtils.findItineraryNoByDocumentName_DocumentYear(documentName, documentYear);
			isUpdateVmaItinerary = true;
		}
		
		String lstDocumentTypeCode = "XC QC 5 7 9 11 13 15";
		if(lstDocumentTypeCode.contains(tempDocument.getDocumentTypeCode().trim())){
			result = TempDebitNoteLocalServiceUtil.doTempDebitNote(documentName, documentYear, corporationid,
					debitnotenumber, division, financialaccountant, fromdate, markasdeleted, organization, paymenttype,
					reportby, reportdate, todate, totalpayment, transactionid, tempDocument.getMaritimeCode(), currency, itineraryNo, "");
		}

		//update itinerary
		if(isUpdateVmaItinerary && (!itineraryNo.equalsIgnoreCase("---"))){
			try {
				VmaItinerary itinerary = VmaItineraryLocalServiceUtil.fetchByitineraryNo(itineraryNo);
				if(itinerary.getMarkedAsArrival() > 0 && itinerary.getMarkedAsDeparture() == 0){
					if(itinerary.getPayment2ArrivalStatus() != 4){
						itinerary.setPayment2ArrivalStatus(1);
					}
				}else if(itinerary.getMarkedAsArrival() == 0 && itinerary.getMarkedAsDeparture() > 0){
					if(itinerary.getPayment2DepartureStatus() != 4){
						itinerary.setPayment2DepartureStatus(1);
					}
				}else if(itinerary.getMarkedAsArrival() > 0 && itinerary.getMarkedAsDeparture() > 0){
					if(itinerary.getArrivalShipAgencyCode().equals(itinerary.getDepartureShipAgencyCode())){
						
						if(itinerary.getPayment2ArrivalStatus() == 4 && itinerary.getPayment2DepartureStatus() != 4){
							itinerary.setPayment2DepartureStatus(1);
						}else if(itinerary.getPayment2ArrivalStatus() != 4 && itinerary.getPayment2DepartureStatus() != 4){
							itinerary.setPayment2ArrivalStatus(1);
							itinerary.setPayment2DepartureStatus(1);
							itinerary.setPayment2ItineraryStatus(1);

							if ((itinerary.getDocumentNameIN()>0) && (itinerary.getDocumentNameIN() == itinerary.getDocumentNameOUT()) && (itinerary.getDocumentYearIN() == itinerary.getDocumentYearOUT())) {
								itinerary.setPayment2ItineraryStatus(0); // Qua canh
								if ((itinerary.getPayment2DepartureStatus() == 1 || itinerary.getPayment2DepartureStatus() == 0 || itinerary
										.getPayment2DepartureStatus() == 6)
										&& itinerary.getMarkedAsDeparture() != 6
										&& itinerary.getMarkedAsDeparture() > 1) {
									itinerary.setPayment2DepartureStatus(1);
									if (itinerary.getPayment2ArrivalStatus() == 1 ) {
										itinerary.setPayment2ArrivalStatus(0);
									}

								}
							}

						}else if(itinerary.getPayment2ArrivalStatus() != 4 && itinerary.getPayment2DepartureStatus() == 4){
							itinerary.setPayment2ArrivalStatus(1);
						}
					}else{
						if(itinerary.getPayment2ArrivalStatus() != 4){
							itinerary.setPayment2ArrivalStatus(1);
						}
						if(itinerary.getPayment2DepartureStatus() != 4){
							itinerary.setPayment2DepartureStatus(1);
						}
						
						itinerary.setPayment2ItineraryStatus(0);
					}
				}
				
				VmaItineraryLocalServiceUtil.updateVmaItinerary(itinerary);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		// Tich hop send message 99-30\
		if (Validator.isNotNull(result) && (result.getTotalpayment().intValue() > 0 || result.getTotalforeignpayment().intValue() > 0 )) {
			if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT1TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT2TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT6TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT8TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT7TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT9TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT10TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT11TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT4TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT5TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT3TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT16TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT17TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT14TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH) && tempDocument.getDocumentStatusCode() == ChuyenDichTrangThaiUtils.THU_TUC_PHE_DUYET_HOAN_THANH_THU_TUC) {
				boolean resultMethod = TT15TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			}
		}
		
		return result;
	}

	public static TempDebitnote doTempDebitNoteTotalPayment2Currency(long id, double totalpayment, double totalforeignpayment, long documentName, int documentYear, 
			User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {
		
		String ykienReject = ParamUtil.getString(uploadPortletRequest, "ykienReject");
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName,
				documentYear);
		
		String currency = ParamUtil.getString(uploadPortletRequest, "currency");
		String foreigncurrency = ParamUtil.getString(uploadPortletRequest, "foreigncurrency");
		
		TempDebitnote result = null;
		try {
			result = TempDebitNoteLocalServiceUtil.fetchTempDebitNote(id);
			result.setTotalpayment(BigDecimal.valueOf(totalpayment));
			result.setComments(ykienReject);
			result.setCurrency(currency);
			
			result.setTotalforeignpayment(BigDecimal.valueOf(totalforeignpayment));
			result.setForeigncurrency(foreigncurrency);
			TempDebitNoteLocalServiceUtil.updateTempDebitNote(result);
			
		} catch (SystemException e) {
			e.printStackTrace();
		}

		// Tich hop send message 99-30\
		if (Validator.isNotNull(result) && (result.getTotalpayment().intValue() > 0 || result.getTotalforeignpayment().intValue() > 0 )) {
			if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH)) {
				boolean resultMethod = TT1TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH)) {
				boolean resultMethod = TT2TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
				boolean resultMethod = TT6TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
				boolean resultMethod = TT8TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
				boolean resultMethod = TT7TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
				boolean resultMethod = TT9TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
				boolean resultMethod = TT10TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
				boolean resultMethod = TT11TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
				boolean resultMethod = TT4TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
				boolean resultMethod = TT5TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
				boolean resultMethod = TT3TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
				boolean resultMethod = TT16TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
				boolean resultMethod = TT17TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)) {
				boolean resultMethod = TT14TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
				boolean resultMethod = TT15TichHopMessageUtils.message_99_30(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
			}
		}
		
		return result;
	}
	
	public static void xacNhanThanhToan(long documentName, int documentYear, int markasdeleted, User user, UploadPortletRequest uploadPortletRequest, ActionRequest actionRequest) {
		try {
			TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil.findByDocumentNameAnddocumentYear(documentName,
					documentYear);
			
			int paymenttype = ParamUtil.getInteger(uploadPortletRequest, "paymenttype", 0);

			tempDebitNote.setMarkasdeleted(1);
			tempDebitNote.setPaymenttype(paymenttype);
			tempDebitNote.setFinancialaccountant(user.getEmailAddress());
			tempDebitNote.setFromdate(new Date());
			tempDebitNote = TempDebitNoteLocalServiceUtil.updateTempDebitNote(tempDebitNote);
			
			if (tempDebitNote.getMarkasdeleted() == 1) {
				TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
				
				// Tich hop send message 99-30
				if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH)) {
					boolean resultMethod = TT1TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH)) {
					boolean resultMethod = TT2TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI)) {
					boolean resultMethod = TT6TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_VAO_CANG)) {
					boolean resultMethod = TT8TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.ROI_CANH_DAU_KHI)) {
					boolean resultMethod = TT7TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_ROI_CANG)) {
					boolean resultMethod = TT9TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_VAO)) {
					boolean resultMethod = TT10TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.CHUYEN_CANG_ROI)) {
					boolean resultMethod = TT11TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.NHAP_CANH_DAU_KHI)) {
					boolean resultMethod = TT4TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.XUAT_CANH_DAU_KHI)) {
					boolean resultMethod = TT5TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_QUA_CANH)) {
					boolean resultMethod = TT3TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)) {
					boolean resultMethod = TT16TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)) {
					boolean resultMethod = TT17TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH)) {
					boolean resultMethod = TT14TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				} else if (tempDocument.getDocumentTypeCode().equalsIgnoreCase(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH)) {
					boolean resultMethod = TT15TichHopMessageUtils.message_99_31(user.getEmailAddress(), uploadPortletRequest, actionRequest, documentName, documentYear);
				}
				
			}
			
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
