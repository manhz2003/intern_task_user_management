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



public class DichVuCongExport {

	

	public static DichVuCongModel getModel(String reportDate,
			String maritimeCode, String dateFrom, String dateTo) {

		DichVuCongModel result = new DichVuCongModel();
		DmHistoryMaritime historyMaritime = null;

		historyMaritime = DmHistoryMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode);
		if (historyMaritime != null) {
			result.setMaritimeNameVN(historyMaritime.getMaritimeNameVN());
			result.setMaritimeName(historyMaritime.getMaritimeName());
		}

		result.setStartDate(dateFrom.substring(0, 10));
		result.setToDate(dateTo.substring(0, 10));
		result.setReportDate(reportDate.substring(0, 10));

		long daNopValue = 0;
		long dangXuLyValue = 0;
		long chapNhanValue = 0; // lttai 9/7/2015
		long tuChoiValue = 0; // lttai 9/7/2015
		long yeuCauBoSungValue = 0;
		long daPheDuyetValue = 0;
		long daCapGiayPhepValue = 0;

		long daNopValueTC = 0;
		long dangXuLyValueTC = 0;
		long chapNhanValueTC = 0; // lttai 9/7/2015
		long tuChoiValueTC = 0; // lttai 9/7/2015
		long yeuCauBoSungValueTC = 0;
		long daPheDuyetValueTC = 0;
		long daCapGiayPhepValueTC = 0;

		// tau thuyen nhap canh (r1) dacapgiayphep = 0
		daNopValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'NC'", "11,14,15,16,12,27",
						"0 , 12, 13, 18, 19,10, 20");
		dangXuLyValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'NC'", "12,14,15,27", "0,12,13, 18,  20");
		chapNhanValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'NC'", "15", "12"); // lttai 9/7/2015 // old :
														// "15","12, 13, 18, 20"
		tuChoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'NC'", "13,16,15", "0,10"); // lttai 9/7/2015
																// // old :
																// "15",
																// "12, 13, 18, 20"
		yeuCauBoSungValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'NC'", "15,27", "13,0");
		daPheDuyetValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'NC'", "15", "19");
		// dangXuLyValue = dangXuLyValue + chapNhanValue + yeuCauBoSungValue; //
		// lttai 9/7/2015
		// daNopValue = dangXuLyValue + daPheDuyetValue + tuChoiValue; // lttai
		// 9/7/2015

		daNopValueTC = daNopValueTC + daNopValue;
		dangXuLyValueTC = dangXuLyValueTC + dangXuLyValue;
		chapNhanValueTC = chapNhanValueTC + chapNhanValue; // lttai 9/7/2015
		tuChoiValueTC = tuChoiValueTC + tuChoiValue; // lttai 9/7/2015
		yeuCauBoSungValueTC = yeuCauBoSungValueTC + yeuCauBoSungValue;
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetValue;

		result.setSoHsDaNop_NC(String.valueOf(daNopValue));
		result.setSoHsDangXuLy_NC(String.valueOf(dangXuLyValue));
		result.setSoHsChapNhan_NC(String.valueOf(chapNhanValue)); // lttai
																	// 9/7/2015
		result.setSoHsTuChoi_NC(String.valueOf(tuChoiValue)); // lttai 9/7/2015
		result.setSoHsYeuCauBoSung_NC(String.valueOf(yeuCauBoSungValue));
		result.setSoHsDaPheDuyet_NC(String.valueOf(daPheDuyetValue));

		// tau thuyen xuat canh (r2)

		// lttai 9/7/2015
		daNopValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'XC'", "11,12,27",
						"0 , 12, 13, 18, 19, 10, 20");
		dangXuLyValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'XC'", "12,27", "0 ,12,13, 18,20");
		chapNhanValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'XC'", "12", "12"); // lttai 9/7/2015 old:
														// "12",
														// "12, 13, 18, 20"
		tuChoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'XC'", "16,12,13", "0, 10"); // lttai 9/7/2015
																// old :
																// "12","12, 13, 18, 20"
		yeuCauBoSungValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'XC'", "12,27", "13,0"); // lttai 9/7/2015 old
															// : "12", "13"
		daPheDuyetValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'XC'", "12", "19");
		daCapGiayPhepValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssuePortClearanceForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'XC'", "12", "4", "19");

		daNopValueTC = daNopValueTC + daNopValue;
		dangXuLyValueTC = dangXuLyValueTC + dangXuLyValue;
		chapNhanValueTC = chapNhanValueTC + chapNhanValue; // lttai 9/7/2015
		tuChoiValueTC = tuChoiValueTC + tuChoiValue; // lttai 9/7/2015
		yeuCauBoSungValueTC = yeuCauBoSungValueTC + yeuCauBoSungValue;
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepValue;

		result.setSoHsDaNop_XC(String.valueOf(daNopValue));
		result.setSoHsDangXuLy_XC(String.valueOf(dangXuLyValue));
		result.setSoHsChapNhan_XC(String.valueOf(chapNhanValue)); // lttai
																	// 9/7/2015
		result.setSoHsTuChoi_XC(String.valueOf(tuChoiValue)); // lttai 9/7/2015
		result.setSoHsYeuCauBoSung_XC(String.valueOf(yeuCauBoSungValue));
		result.setSoHsDaPheDuyet_XC(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_XC(String.valueOf(daCapGiayPhepValue));

		// tau thuyen qua canh (r3)
		// lttai 9/7/2015
		daNopValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'QC'", "11,14,15,16,12,27",
						"0 , 12, 13, 18, 19,10, 20");
		dangXuLyValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'QC'", "12,14,15,27", "0 ,12,13, 18, 20");

		chapNhanValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'QC'", "15", "12"); // lttai 9/7/2015 // old :
														// "15","12, 13, 18, 20"
		tuChoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'QC'", "13,16,15", "0,10"); // lttai 9/7/2015
																// old : "15",
																// "12, 13, 18, 20"
		yeuCauBoSungValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'QC'", "15,27", "13 , 0"); // lttai 9/7/2015 //
															// old : "15", "13"
		daPheDuyetValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'QC'", "15", "19");
		// dangXuLyValue = dangXuLyValue + chapNhanValue + yeuCauBoSungValue; //
		// lttai 9/7/2015
		// daNopValue = dangXuLyValue + daPheDuyetValue + tuChoiValue; // lttai
		// 9/7/2015
		daCapGiayPhepValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssuePermissionTransitForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'QC'", "15", "4", "19");

		daNopValueTC = daNopValueTC + daNopValue;
		dangXuLyValueTC = dangXuLyValueTC + dangXuLyValue;
		chapNhanValueTC = chapNhanValueTC + chapNhanValue; // lttai 9/7/2015
		tuChoiValueTC = tuChoiValueTC + tuChoiValue; // lttai 9/7/2015
		yeuCauBoSungValueTC = yeuCauBoSungValueTC + yeuCauBoSungValue;
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepValue;

		result.setSoHsDaNop_QC(String.valueOf(daNopValue));
		result.setSoHsDangXuLy_QC(String.valueOf(dangXuLyValue));
		result.setSoHsChapNhan_QC(String.valueOf(chapNhanValue)); // lttai
																	// 9/7/2015
		result.setSoHsTuChoi_QC(String.valueOf(tuChoiValue)); // lttai 9/7/2015
		result.setSoHsYeuCauBoSung_QC(String.valueOf(yeuCauBoSungValue));
		result.setSoHsDaPheDuyet_QC(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_QC(String.valueOf(daCapGiayPhepValue));

		// Tau thuyen vao cang (DocumentTypeCode {4})
		//
		daNopValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'4'", "11,14,15,16,12,27",
						"0 , 12, 13, 18, 19,10, 20");
		dangXuLyValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'4'", "12,14,15,27", "0 ,12,13, 18, 20");

		chapNhanValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'4'", "15", "12"); // lttai 9/7/2015 // old :
													// "15","12, 13, 18, 20"
		tuChoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'4'", "13,16,15", "0,10"); // lttai 9/7/2015
															// old : "15",
															// "12, 13, 18, 20"
		yeuCauBoSungValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'4'", "15,27", "13 , 0"); // lttai 9/7/2015 //
															// old : "15", "13"
		daPheDuyetValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'4'", "15", "19");
		daCapGiayPhepValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssueShiftingOrderForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'4'", "15", "4", "19");

		daNopValueTC = daNopValueTC + daNopValue;
		dangXuLyValueTC = dangXuLyValueTC + dangXuLyValue;
		chapNhanValueTC = chapNhanValueTC + chapNhanValue;
		tuChoiValueTC = tuChoiValueTC + tuChoiValue;
		yeuCauBoSungValueTC = yeuCauBoSungValueTC + yeuCauBoSungValue;
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepValue;

		result.setSoHsDaNop_TauVao(String.valueOf(daNopValue));
		result.setSoHsDangXuLy_TauVao(String.valueOf(dangXuLyValue));
		result.setSoHsChapNhan_TauVao(String.valueOf(chapNhanValue));
		result.setSoHsTuChoi_TauVao(String.valueOf(tuChoiValue));
		result.setSoHsYeuCauBoSung_TauVao(String.valueOf(yeuCauBoSungValue));
		result.setSoHsDaPheDuyet_TauVao(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_TauVao(String.valueOf(daCapGiayPhepValue));

		// Tau thuyen roi cang (DocumentTypeCode {5})
		daNopValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'5'", "11,12,27", "0 , 12, 13, 18, 19, 10, 20");
		dangXuLyValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'5'", "12,27", "0 ,12,13, 18,20");
		chapNhanValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'5'", "12", "12"); // lttai 9/7/2015 old: "12",
													// "12, 13, 18, 20"
		tuChoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'5'", "16,12,13", "0, 10"); // lttai 9/7/2015
																// old :
																// "12","12, 13, 18, 20"
		yeuCauBoSungValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'5'", "12,27", "13,0"); // lttai 9/7/2015 old :
															// "12", "13"
		daPheDuyetValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'5'", "12", "19");
		daCapGiayPhepValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssuePortClearanceForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'5'", "12", "4", "19");

		daNopValueTC = daNopValueTC + daNopValue;
		dangXuLyValueTC = dangXuLyValueTC + dangXuLyValue;
		chapNhanValueTC = chapNhanValueTC + chapNhanValue;
		tuChoiValueTC = tuChoiValueTC + tuChoiValue;
		yeuCauBoSungValueTC = yeuCauBoSungValueTC + yeuCauBoSungValue;
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepValue;

		result.setSoHsDaNop_TauRoi(String.valueOf(daNopValue));
		result.setSoHsDangXuLy_TauRoi(String.valueOf(dangXuLyValue));
		result.setSoHsChapNhan_TauRoi(String.valueOf(chapNhanValue));
		result.setSoHsTuChoi_TauRoi(String.valueOf(tuChoiValue));
		result.setSoHsYeuCauBoSung_TauRoi(String.valueOf(yeuCauBoSungValue));
		result.setSoHsDaPheDuyet_TauRoi(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_TauRoi(String.valueOf(daCapGiayPhepValue));

		// PTTND vao cang (DocumentTypeCode {6})

		daNopValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'16'", "11,12,27",
						"0 , 12, 13, 18, 19, 10, 20");
		dangXuLyValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'16'", "12,27", "0 ,12,13, 18,20");
		chapNhanValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'16'", "12", "12"); // lttai 9/7/2015 old:
														// "12",
														// "12, 13, 18, 20"
		tuChoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'16'", "16,12,13", "0, 10"); // lttai 9/7/2015
																// old :
																// "12","12, 13, 18, 20"
		yeuCauBoSungValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'16'", "12,27", "13,0"); // lttai 9/7/2015 old
															// : "12", "13"
		daPheDuyetValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'16'", "12", "19");
		daCapGiayPhepValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssueAcceptanceForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'16'", "12", "4", "19");

		daNopValueTC = daNopValueTC + daNopValue;
		dangXuLyValueTC = dangXuLyValueTC + dangXuLyValue;
		chapNhanValueTC = chapNhanValueTC + chapNhanValue;
		tuChoiValueTC = tuChoiValueTC + tuChoiValue;
		yeuCauBoSungValueTC = yeuCauBoSungValueTC + yeuCauBoSungValue;
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepValue;

		result.setSoHsDaNop_PTTNDVaoCang(String.valueOf(daNopValue));
		result.setSoHsDangXuLy_PTTNDVaoCang(String.valueOf(dangXuLyValue));
		result.setSoHsChapNhan_PTTNDVaoCang(String.valueOf(chapNhanValue));
		result.setSoHsTuChoi_PTTNDVaoCang(String.valueOf(tuChoiValue));
		result.setSoHsYeuCauBoSung_PTTNDVaoCang(String
				.valueOf(yeuCauBoSungValue));
		result.setSoHsDaPheDuyet_PTTNDVaoCang(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_PTTNDVaoCang(String.valueOf(daCapGiayPhepValue));

		// PTTND roi cang (DocumentTypeCode {7})
		daNopValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'17'", "11,12,27",
						"0 , 12, 13, 18, 19, 10, 20");
		dangXuLyValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'17'", "12,27", "0 ,12,13, 18,20");
		chapNhanValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'17'", "12", "12"); // lttai 9/7/2015 old:
														// "12",
														// "12, 13, 18, 20"
		tuChoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'17'", "16,12,13", "0, 10"); // lttai 9/7/2015
																// old :
																// "12","12, 13, 18, 20"
		yeuCauBoSungValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'17'", "12,27", "13,0"); // lttai 9/7/2015 old
															// : "12", "13"
		daPheDuyetValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'17'", "12", "19");
		daCapGiayPhepValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssuePortClearanceForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'17'", "12", "4", "19");

		daNopValueTC = daNopValueTC + daNopValue;
		dangXuLyValueTC = dangXuLyValueTC + dangXuLyValue;
		chapNhanValueTC = chapNhanValueTC + chapNhanValue;
		tuChoiValueTC = tuChoiValueTC + tuChoiValue;
		yeuCauBoSungValueTC = yeuCauBoSungValueTC + yeuCauBoSungValue;
		daPheDuyetValueTC = daPheDuyetValueTC + daPheDuyetValue;
		daCapGiayPhepValueTC = daCapGiayPhepValueTC + daCapGiayPhepValue;

		result.setSoHsDaNop_PTTNDRoiCang(String.valueOf(daNopValue));
		result.setSoHsDangXuLy_PTTNDRoiCang(String.valueOf(dangXuLyValue));
		result.setSoHsChapNhan_PTTNDRoiCang(String.valueOf(chapNhanValue));
		result.setSoHsTuChoi_PTTNDRoiCang(String.valueOf(tuChoiValue));
		result.setSoHsYeuCauBoSung_PTTNDRoiCang(String
				.valueOf(yeuCauBoSungValue));
		result.setSoHsDaPheDuyet_PTTNDRoiCang(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_PTTNDRoiCang(String.valueOf(daCapGiayPhepValue));

		// Tong cong

		result.setSoHsDaNop_TC(String.valueOf(daNopValueTC));
		result.setSoHsDangXuLy_TC(String.valueOf(dangXuLyValueTC));
		result.setSoHsChapNhan_TC(String.valueOf(chapNhanValueTC)); // lttai
																	// 9/7/2015
		result.setSoHsTuChoi_TC(String.valueOf(tuChoiValueTC)); // lttai
																// 9/7/2015
		result.setSoHsYeuCauBoSung_TC(String.valueOf(yeuCauBoSungValueTC));
		result.setSoHsDaPheDuyet_TC(String.valueOf(daPheDuyetValueTC));
		result.setSoHsDaCapPhep_TC(String.valueOf(daCapGiayPhepValueTC));

		result.setReportDate("Ng\u00E0y " + reportDate.substring(0, 2)
				+ " th\u00E1ng " + reportDate.substring(3, 5) + " n\u0103m "
				+ reportDate.substring(6, 10));

		return result;
	}

	public boolean export2Report(String reportDate, String maritimeCode,
			String dateFrom, String dateTo) throws IOException, SystemException {

		ArrayList<DichVuCongModel> dataBeanList = getDataReport(reportDate,
				maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();

		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunctionModuleBaoCao(
				BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG_XML,
				BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG_EXPORT,
				beanColDataSource, parameters);

		return true;
	}

	public static ArrayList<DichVuCongModel> getDataReport(String reportDate,
			String maritimeCode, String dateFrom, String dateTo) {
		ArrayList<DichVuCongModel> dataBeanList = new ArrayList<DichVuCongModel>();

		dataBeanList.add(getModel(reportDate, maritimeCode, dateFrom, dateTo));
		return dataBeanList;
	}

	public boolean export2Excel(String reportDate, String maritimeCode,
			String dateFrom, String dateTo, String fileName)
			throws IOException, SystemException {

		ArrayList<DichVuCongModel> dataBeanList = getDataReport(reportDate,
				maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();

		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunctionExcel(
				BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG_XML, fileName,
				beanColDataSource, parameters);

		return true;
	}

}
