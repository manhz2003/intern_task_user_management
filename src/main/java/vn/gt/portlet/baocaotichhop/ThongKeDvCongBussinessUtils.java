package vn.gt.portlet.baocaotichhop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.portlet.baocaotichhop.thongke.dvcong.DichVuCongFlowChart;
import vn.gt.portlet.baocaotichhop.thongke.dvcong.DichVuCongModel;
import vn.gt.portlet.baocaotichhop.thongke.dvcong.FlowChartDataByDate;
import vn.gt.portlet.baocaotichhop.thongke.dvcong.HoSoDichVuCongDetail;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.liferay.core.LanguageUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.UploadPortletRequest;
@Slf4j
public class ThongKeDvCongBussinessUtils
 {
	
	public static List<String> getAllMonthOfCurrentYear() {
		
		List<String> result = new ArrayList<String>();
		String month = null;
		Calendar currentDate = Calendar.getInstance();
		int year = currentDate.get(Calendar.YEAR);
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				month = "0" + i + "/" + year;
			} else {
				month = i + "/" + year;
			}
			result.add(month);
		}
		return result;
	}
	
	public static String getCurentMonth() {
		
		String result = null;
		Calendar currentDate = Calendar.getInstance();
		int year = currentDate.get(Calendar.YEAR);
		int month = currentDate.get(Calendar.MONTH);
		
		if (month < 10) {
			result = "0" + month + "/" + year;
		} else {
			result = month + "/" + year;
		}
		
		return result;
	}
	
	public static String getEndDateOfMonth(String month) {
		String result = null;
		try {
			if (month != null) {
				Date date;
				
				DateFormat formatIn = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat formatDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String dateString = "01/" + month;
				
				date = formatIn.parse(dateString);
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				
				String lastDate = String.valueOf(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				lastDate = lastDate + "/" + month;
				
				date = formatIn.parse(lastDate);
				result = formatDateOut.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getCurrentDate() {
		String result = null;
		Date date;
		
		DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		date = new Date();
		
		result = formatDate.format(date);
		
		return result;
	}
	
	public static String getStartDateOfMonth(String month) {
		String result = null;
		try {
			if (month != null) {
				Date date;
				
				DateFormat formatIn = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat formatDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String dateString = "01/" + month;
				date = formatIn.parse(dateString);
				result = formatDateOut.format(date);
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getStartDateOfCurrentMonth() {
		
		String result = null;
		
		SimpleDateFormat formatDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.set(Calendar.DATE, 1);
		
		Date date = aCalendar.getTime();
		result = formatDateOut.format(date);
		
		return result;
		
	}
	
	public static String getStartDateOfPreviousMonth() {
		
		String result = null;
		
		SimpleDateFormat formatDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.add(Calendar.MONTH, -1);
		aCalendar.set(Calendar.DATE, 1);
		
		Date date = aCalendar.getTime();
		result = formatDateOut.format(date);
		
		return result;
		
	}
	
	public static String getEndDateOfPreviousMonth() {
		
		String result = null;
		
		SimpleDateFormat formatDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.add(Calendar.MONTH, -1);
		aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		Date date = aCalendar.getTime();
		result = formatDateOut.format(date);
		
		return result;
		
	}
	
	public static String getEndDateOfAppointMonth(String startDate) {
		
		String result = null;
		try {
			if (startDate != null) {
				SimpleDateFormat formatDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date date;
				
				date = formatDateOut.parse(startDate);
				
				Calendar aCalendar = Calendar.getInstance();
				aCalendar.setTime(date);
				aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				
				result = formatDateOut.format(aCalendar.getTime());
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static String getStartDateOfAppointMonth(String startDate) {
		
		String result = null;
		try {
			if (startDate != null) {
				SimpleDateFormat formatDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date date;
				
				date = formatDateOut.parse(startDate);
				
				Calendar aCalendar = Calendar.getInstance();
				aCalendar.setTime(date);
				aCalendar.set(Calendar.DATE, 1);
				
				result = formatDateOut.format(aCalendar.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static String getSelectMonth(String date) {
		
		String result = null;
		if (date != null) {
			result = date.substring(3, 10);
		}
		return result;
	}
	
	public static List<DichVuCongModel> thongKeHosoDichvuCong(String dateFrom, String dateTo) throws Exception {
		
		List<DichVuCongModel> result = null;
		List<DmHistoryMaritime> allMaritime = DmHistoryMaritimeLocalServiceUtil.findAllDmHistoryMaritimeOrderAsc();
		
		long daNopNCValue = 0;
		long daNopXCValue = 0;
		long daNopQCValue = 0;
		
		if ((allMaritime != null) && (allMaritime.size() > 0)) {
			DmHistoryMaritime dmHistoryMaritime = null;
			DichVuCongModel dichVuCongModel = null;
			HoSoDichVuCongDetail hoSoDichVuCongDetail = null;
			for (int i = 0; i < allMaritime.size(); i++) {
				dmHistoryMaritime = allMaritime.get(i);
				// Nhap canh
				daNopNCValue = TempDocumentLocalServiceUtil.countTempDocumentForStatisticsReport(dmHistoryMaritime.getMaritimeCode(), dateFrom,
						dateTo, "'NC'", "11,12,14,15", "12, 13, 18, 19,0");
				// Xuat Canh
				daNopXCValue = TempDocumentLocalServiceUtil.countTempDocumentForStatisticsReport(dmHistoryMaritime.getMaritimeCode(), dateFrom,
						dateTo, "'XC'", "11,12", "12, 13, 18, 19, 20, 0");
				// Qua Canh
				daNopQCValue = TempDocumentLocalServiceUtil.countTempDocumentForStatisticsReport(dmHistoryMaritime.getMaritimeCode(), dateFrom,
						dateTo, "'QC'", "11,12,14,15", "12, 13, 18, 19, 20, 0");
				if ((daNopNCValue != 0) || (daNopXCValue != 0) || (daNopQCValue != 0)) {
					dichVuCongModel = new DichVuCongModel();
					dichVuCongModel.setMaritimeNameVN(dmHistoryMaritime.getMaritimeNameVN());
					dichVuCongModel.setMaritimeName(dmHistoryMaritime.getMaritimeName());
					dichVuCongModel.setMaritimeCode(dmHistoryMaritime.getMaritimeCode());
					
					hoSoDichVuCongDetail = new HoSoDichVuCongDetail();
					hoSoDichVuCongDetail.setNhapCanhName(BaoCaoTichHopConstant.TAU_NHAP_CANH);
					hoSoDichVuCongDetail.setXuatCanhName(BaoCaoTichHopConstant.TAU_XUAT_CANH);
					hoSoDichVuCongDetail.setQuaCanhName(BaoCaoTichHopConstant.TAU_QUA_CANH);
					hoSoDichVuCongDetail.setSoHSNhapCanh(String.valueOf(daNopNCValue));
					hoSoDichVuCongDetail.setSoHSXuatCanh(String.valueOf(daNopXCValue));
					hoSoDichVuCongDetail.setSoHSQuaCanh(String.valueOf(daNopQCValue));
					hoSoDichVuCongDetail.setTongSoHoSo(String.valueOf(daNopNCValue + daNopXCValue + daNopQCValue));
					
					dichVuCongModel.setHoSoDichVuCongDetail(hoSoDichVuCongDetail);
					if (result == null) {
						result = new ArrayList<DichVuCongModel>();
					}
					
					result.add(dichVuCongModel);
				}
			}
		}
		
		return result;
	}
	
//	public static List<DichVuCongFlowChart> getDataForFlowChart(String dateFrom, String dateTo) throws Exception {
//
//		List<DichVuCongFlowChart> result = null;
//		List<DmHistoryMaritime> allMaritime = DmHistoryMaritimeLocalServiceUtil.findAllDmHistoryMaritimeOrderAsc();
//
//		List<FlowChartDataByDate> listNhapCanh = null;
//		List<FlowChartDataByDate> listXuatCanh = null;
//		List<FlowChartDataByDate> listQuaCanh = null;
//		if ((allMaritime != null) && (allMaritime.size() > 0)) {
//			DichVuCongFlowChart dichVuCongFlowChart = null;
//
//			// System.out.println("*******************vao business*********************:");
//			// Nhap canh
//			listNhapCanh = TempDocumentLocalServiceUtil.countTempDocumentForStatisticsByDate(null, dateFrom, dateTo, "'NC'", "11,12,14,15",
//					"12, 13, 18, 19, 0");
//
//			// Xuat Canh
//			listXuatCanh = TempDocumentLocalServiceUtil.countTempDocumentForStatisticsByDate(null, dateFrom, dateTo, "'XC'", "11,12",
//					"12, 13, 18, 19, 20, 0");
//
//			// Qua Canh
//			listQuaCanh = TempDocumentLocalServiceUtil.countTempDocumentForStatisticsByDate(null, dateFrom, dateTo, "'QC'", "11,12,14,15",
//					"12, 13, 18, 19, 20, 0");
//
//			if ((listNhapCanh != null) || (listXuatCanh != null) || (listQuaCanh != null)) {
//				dichVuCongFlowChart = new DichVuCongFlowChart();
//				dichVuCongFlowChart.setDateFrom(dateFrom);
//				dichVuCongFlowChart.setDateTo(dateTo);
//				dichVuCongFlowChart.setListHoSoNhapCanh(listNhapCanh);
//				dichVuCongFlowChart.setListHoSoXuatCanh(listXuatCanh);
//				dichVuCongFlowChart.setListHoSoQuaCanh(listQuaCanh);
//
//				if (result == null) {
//					result = new ArrayList<DichVuCongFlowChart>();
//				}
//
//				result.add(dichVuCongFlowChart);
//			}
//
//		}
//
//		return result;
//	}
}
