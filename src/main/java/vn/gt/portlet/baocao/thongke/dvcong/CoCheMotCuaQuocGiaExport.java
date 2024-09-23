package vn.gt.portlet.baocao.thongke.dvcong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.BaoCaoConstant;

import com.fds.nsw.kernel.exception.SystemException;



public class CoCheMotCuaQuocGiaExport {
	
	
	
	public static CoCheMotCuaQuocGiaModel getModel(String reportDate, String maritimeCode, String dateFrom, String dateTo) {
		
		CoCheMotCuaQuocGiaModel result = new CoCheMotCuaQuocGiaModel();
		DmHistoryMaritime historyMaritime = null;

		historyMaritime = DmHistoryMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode);
		if (historyMaritime != null) {
			result.setMaritimeNameVN(historyMaritime.getMaritimeNameVN());
			result.setMaritimeName(historyMaritime.getMaritimeName());
			result.setReportDate(historyMaritime.getCityCode() + ", ");
		}
		
		result.setStartDate(dateFrom.substring(0, 10));
		result.setToDate(dateTo.substring(0, 10));
		result.setMonthOfPeriod(dateTo.substring(3, 5));
		result.setYearOfPeriod(dateTo.substring(6, 10));		
		result.setReportDate(result.getReportDate() + "ng\u00E0y " + reportDate.substring(0, 2) + " th\u00E1ng " + reportDate.substring(3, 5) + " n\u0103m "
				+ reportDate.substring(6, 10));
		long daPheDuyetValue = 0;
		long daCapGiayPhepValue = 0;
		long daPheDuyetCapGiayValue = 0;
		long daCapGiayPhepKySoDienTuValue = 0;
		long daPheDuyetValueTC = 0;
		long daCapGiayPhepValueTC = 0;
		
		// Tau thuyen nhap canh (r1) dacapgiayphep = 0
		daPheDuyetValue = TempDocumentLocalServiceUtil.countTempDocumentCompletionForStatisticsReport(maritimeCode, dateFrom, dateTo, "'NC'", "15", "19");
		
		result.setSoHsDaPheDuyet_NC(String.valueOf(daPheDuyetValue));
		
		
		daCapGiayPhepKySoDienTuValue = TempDocumentLocalServiceUtil.countTempDocumentSignedIssueShiftingOrderForStatisticsReport(maritimeCode, dateFrom, dateTo,
				"'NC'", "15", "4", "19");
		daPheDuyetCapGiayValue = daPheDuyetValue - daCapGiayPhepKySoDienTuValue;
		if (daPheDuyetCapGiayValue < 0) {
			daPheDuyetCapGiayValue = 0;
		}
		//log.info("=======daPheDuyetCapGiayValue==Tau thuyen nhap canh (r1)=====" + daPheDuyetCapGiayValue);
		result.setsoHsDaCapPhepKySoDienTu_NC(String.valueOf(daCapGiayPhepKySoDienTuValue));
		result.setsoHsDaPheDuyetCapGiay_NC(String.valueOf(daPheDuyetCapGiayValue));
		
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetCapGiayValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepKySoDienTuValue;
		
		// Tau thuyen xuat canh (r2)
		
		daPheDuyetValue = TempDocumentLocalServiceUtil.countTempDocumentCompletionForStatisticsReport(maritimeCode, dateFrom, dateTo, "'XC'", "12", "19");
		daCapGiayPhepValue = TempDocumentLocalServiceUtil.countTempDocumentJoinIssuePortClearanceForStatisticsReport(maritimeCode, dateFrom, dateTo,
				"'XC'", "12", "4", "19");
		result.setSoHsDaPheDuyet_XC(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_XC(String.valueOf(daCapGiayPhepValue));
		
		
		daCapGiayPhepKySoDienTuValue = TempDocumentLocalServiceUtil.countTempDocumentSignedIssuePortClearanceForStatisticsReport(maritimeCode, dateFrom, dateTo,
				"'XC'", "12", "4", "19");
		daPheDuyetCapGiayValue = daPheDuyetValue - daCapGiayPhepKySoDienTuValue;
		if (daPheDuyetCapGiayValue < 0) {
			daPheDuyetCapGiayValue = 0;
		}
		//log.info("=======daPheDuyetCapGiayValue==Tau thuyen xuat canh (r2)=====" + daPheDuyetCapGiayValue);
		result.setsoHsDaCapPhepKySoDienTu_XC(String.valueOf(daCapGiayPhepKySoDienTuValue));
		result.setsoHsDaPheDuyetCapGiay_XC(String.valueOf(daPheDuyetCapGiayValue));
		
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetCapGiayValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepKySoDienTuValue;
		
		
		
		// Tau thuyen qua canh (r3)
		daPheDuyetValue = TempDocumentLocalServiceUtil.countTempDocumentCompletionForStatisticsReport(maritimeCode, dateFrom, dateTo, "'QC'", "15", "19");
		daCapGiayPhepValue = TempDocumentLocalServiceUtil.countTempDocumentJoinIssuePermissionTransitForStatisticsReport(maritimeCode, dateFrom,
				dateTo, "'QC'", "15", "4", "19");
		result.setSoHsDaPheDuyet_QC(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_QC(String.valueOf(daCapGiayPhepValue));
		
		
		daCapGiayPhepKySoDienTuValue = TempDocumentLocalServiceUtil.countTempDocumentSignedIssuePermissionTransitForStatisticsReport(maritimeCode, dateFrom,
				dateTo, "'QC'", "15", "4", "19");
		daPheDuyetCapGiayValue = daPheDuyetValue - daCapGiayPhepKySoDienTuValue;
		if (daPheDuyetCapGiayValue < 0) {
			daPheDuyetCapGiayValue = 0;
		}
		//log.info("=======daPheDuyetCapGiayValue==Tau thuyen qua canh (r3)=====" + daPheDuyetCapGiayValue);
		result.setsoHsDaCapPhepKySoDienTu_QC(String.valueOf(daCapGiayPhepKySoDienTuValue));
		result.setsoHsDaPheDuyetCapGiay_QC(String.valueOf(daPheDuyetCapGiayValue));
		
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetCapGiayValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepKySoDienTuValue;
		
		
		
		// Tau thuyen vao cang (DocumentTypeCode {4})
		daPheDuyetValue = TempDocumentLocalServiceUtil.countTempDocumentCompletionForStatisticsReport(maritimeCode, dateFrom, dateTo, "'4'", "15", "19");		
		daCapGiayPhepValue = TempDocumentLocalServiceUtil.countTempDocumentJoinIssueShiftingOrderForStatisticsReport(maritimeCode, dateFrom, 
				dateTo, "'4'", "15", "4", "19");

		result.setSoHsDaPheDuyet_TauVao(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_TauVao(String.valueOf(daCapGiayPhepValue));
		
		
		daCapGiayPhepKySoDienTuValue = TempDocumentLocalServiceUtil.countTempDocumentSignedIssueShiftingOrderForStatisticsReport(maritimeCode, dateFrom, 
				dateTo, "'4'", "15", "4", "19");
		daPheDuyetCapGiayValue = daPheDuyetValue - daCapGiayPhepKySoDienTuValue;
		if (daPheDuyetCapGiayValue < 0) {
			daPheDuyetCapGiayValue = 0;
		}
		//log.info("=======daPheDuyetCapGiayValue==Tau thuyen vao cang (DocumentTypeCode {4}=====" + daPheDuyetCapGiayValue);
		result.setsoHsDaCapPhepKySoDienTu_TauVao(String.valueOf(daCapGiayPhepKySoDienTuValue));
		result.setsoHsDaPheDuyetCapGiay_TauVao(String.valueOf(daPheDuyetCapGiayValue));
		
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetCapGiayValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepKySoDienTuValue;
		
		
		// Tau thuyen roi cang (DocumentTypeCode {5})
		daPheDuyetValue = TempDocumentLocalServiceUtil.countTempDocumentCompletionForStatisticsReport(maritimeCode, dateFrom, dateTo, "'5'", "12", "19");
		daCapGiayPhepValue = TempDocumentLocalServiceUtil.countTempDocumentJoinIssuePortClearanceForStatisticsReport(maritimeCode, dateFrom, 
				dateTo, "'5'", "12", "4", "19");

		result.setSoHsDaPheDuyet_TauRoi(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_TauRoi(String.valueOf(daCapGiayPhepValue));
		
		
		daCapGiayPhepKySoDienTuValue = TempDocumentLocalServiceUtil.countTempDocumentSignedIssuePortClearanceForStatisticsReport(maritimeCode, dateFrom, 
				dateTo, "'5'", "12", "4", "19");
		daPheDuyetCapGiayValue = daPheDuyetValue - daCapGiayPhepKySoDienTuValue;
		if (daPheDuyetCapGiayValue < 0) {
			daPheDuyetCapGiayValue = 0;
		}
		//log.info("=======daPheDuyetCapGiayValue==Tau thuyen roi cang (DocumentTypeCode {5}=====" + daPheDuyetCapGiayValue);
		result.setsoHsDaCapPhepKySoDienTu_TauRoi(String.valueOf(daCapGiayPhepKySoDienTuValue));
		result.setsoHsDaPheDuyetCapGiay_TauRoi(String.valueOf(daPheDuyetCapGiayValue));
		
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetCapGiayValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepKySoDienTuValue;
		
		// PTTND vao cang (DocumentTypeCode {6})
		daPheDuyetValue = TempDocumentLocalServiceUtil.countTempDocumentCompletionForStatisticsReport(maritimeCode, dateFrom, dateTo, "'16'", "12", "19");
		daCapGiayPhepValue = TempDocumentLocalServiceUtil.countTempDocumentJoinIssueAcceptanceForStatisticsReport(maritimeCode, dateFrom, 
				dateTo,"'16'", "12", "4", "19");
		

		result.setSoHsDaPheDuyet_PTTNDVaoCang(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_PTTNDVaoCang(String.valueOf(daCapGiayPhepValue));
		
		
		daCapGiayPhepKySoDienTuValue = TempDocumentLocalServiceUtil.countTempDocumentSignedIssueAcceptanceForStatisticsReport(maritimeCode, dateFrom, 
				dateTo,"'16'", "12", "4", "19");
		daPheDuyetCapGiayValue = daPheDuyetValue - daCapGiayPhepKySoDienTuValue;
		if (daPheDuyetCapGiayValue < 0) {
			daPheDuyetCapGiayValue = 0;
		}
		//log.info("=======daPheDuyetCapGiayValue==PTTND vao cang (DocumentTypeCode {6}=====" + daPheDuyetCapGiayValue);
		result.setsoHsDaCapPhepKySoDienTu_PTTNDVaoCang(String.valueOf(daCapGiayPhepKySoDienTuValue));
		result.setsoHsDaPheDuyetCapGiay_PTTNDVaoCang(String.valueOf(daPheDuyetCapGiayValue));
		
		/*daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetCapGiayValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepKySoDienTuValue;
		*/
		
		// PTTND roi cang (DocumentTypeCode {7})
		daPheDuyetValue = TempDocumentLocalServiceUtil.countTempDocumentCompletionForStatisticsReport(maritimeCode, dateFrom, dateTo, "'17'", "12", "19");
		daCapGiayPhepValue = TempDocumentLocalServiceUtil.countTempDocumentJoinIssuePortClearanceForStatisticsReport(maritimeCode, dateFrom, 
				dateTo, "'17'", "12", "4", "19");
		

		result.setSoHsDaPheDuyet_PTTNDRoiCang(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_PTTNDRoiCang(String.valueOf(daCapGiayPhepValue));
		
		
		daCapGiayPhepKySoDienTuValue = TempDocumentLocalServiceUtil.countTempDocumentSignedIssuePortClearanceForStatisticsReport(maritimeCode, dateFrom, 
				dateTo, "'17'", "12", "4", "19");
		daPheDuyetCapGiayValue = daPheDuyetValue - daCapGiayPhepKySoDienTuValue;
		if (daPheDuyetCapGiayValue < 0) {
			daPheDuyetCapGiayValue = 0;
		}
		//log.info("=======daPheDuyetCapGiayValue==PTTND roi cang (DocumentTypeCode {7}=====" + daPheDuyetCapGiayValue);
		result.setsoHsDaCapPhepKySoDienTu_PTTNDRoiCang(String.valueOf(daCapGiayPhepKySoDienTuValue));
		result.setsoHsDaPheDuyetCapGiay_PTTNDRoiCang(String.valueOf(daPheDuyetCapGiayValue));
		
		/*daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetCapGiayValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepKySoDienTuValue;
		*/
		
		// Tong cong

		result.setSoHsDaPheDuyet_TongCong(String.valueOf(daPheDuyetValueTC));
		result.setSoHsDaCapPhep_TongCong(String.valueOf(daCapGiayPhepValueTC));
		
		
		
		return result;
	}
	
	public boolean export2Report(String reportDate, String maritimeCode, String dateFrom, String dateTo) throws IOException,
			SystemException {
		
		ArrayList<CoCheMotCuaQuocGiaModel> dataBeanList = getDataReport(reportDate, maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		
		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunctionModuleBaoCao(BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA_XML, BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA_EXPORT,
				beanColDataSource, parameters);
		
		return true;
	}
	
	public static ArrayList<CoCheMotCuaQuocGiaModel> getDataReport(String reportDate, String maritimeCode, String dateFrom, String dateTo) {
		ArrayList<CoCheMotCuaQuocGiaModel> dataBeanList = new ArrayList<CoCheMotCuaQuocGiaModel>();
		
		dataBeanList.add(getModel(reportDate, maritimeCode, dateFrom, dateTo));
		return dataBeanList;
	}
	
}
