package vn.gt.portlet.baocao.thongke.dvcongtheophongban;

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

public class DVCongTheoPhongBanExport {

	public static DVCongTheoPhongBanModel getModel(String reportDate,
			String maritimeCode, String dateFrom, String dateTo) {

		DVCongTheoPhongBanModel result = new DVCongTheoPhongBanModel();
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
		long chapNhanValue = 0;
		long tuChoiValue = 0;
		long yeuCauBoSungValue = 0;
		long daPheDuyetValue = 0;
		long daCapGiayPhepRCValue = 0;
		long daCapGiayPhepQCValue = 0;

		// Ke hoach (r1) dacapgiayphep = 0
		// lttai 9/7/2015
		daNopValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, null, "11,14,15,16,12,27", null);// 11,12,13,14,15,16
		// dangXuLyValue =
		// TempDocumentLocalServiceUtil.countTempDocumentForStatisticsReport(maritimeCode,
		// dateFrom, dateTo, null, "11,14,27",null);//12,13,14,15,16

		chapNhanValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, null, "14", null);
		tuChoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, null, "13,16", null);
		yeuCauBoSungValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, null, "27", null);
		// daPheDuyetValue =
		// TempDocumentLocalServiceUtil.countTempDocumentForStatisticsReport(maritimeCode,
		// dateFrom, dateTo, null, "12,15", null);
		dangXuLyValue = chapNhanValue + yeuCauBoSungValue; // lttai 9/7/2015
		// daNopValue = dangXuLyValue + tuChoiValue; // lttai 9/7/2015

		result.setSoHsDaNop_KH(String.valueOf(daNopValue));
		result.setSoHsDangXuLy_KH(String.valueOf(dangXuLyValue));
		result.setSoHsChapNhan_KH(String.valueOf(chapNhanValue));
		result.setSoHsTuChoi_KH(String.valueOf(tuChoiValue));
		result.setSoHsYeuCauBoSung_KH(String.valueOf(yeuCauBoSungValue));
		// result.setSoHsDaPheDuyet_KH(String.valueOf(daPheDuyetValue));

		// Thu tuc (r2)
		// lttai 9/7/2015
		daNopValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, null, "15,12", "12,13,18,19,10,20");
		dangXuLyValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, null, "15,12", "20");

		chapNhanValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, null, "15,12", "12");// chap nhan: cho phe duyet
		tuChoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, null, "15,12", "10");
		yeuCauBoSungValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, null, "15,12", "13");
		daPheDuyetValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, null, "15,12", "19");
		daCapGiayPhepRCValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssuePortClearanceForStatisticsReport(
						maritimeCode, dateFrom, dateTo, null, "12", "4", "19");
		daCapGiayPhepQCValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssuePermissionTransitForStatisticsReport(
						maritimeCode, dateFrom, dateTo, null, "15", "4", "19");
		dangXuLyValue = dangXuLyValue + chapNhanValue + yeuCauBoSungValue; // lttai
																			// 9/7/2015
		// daNopValue = dangXuLyValue + daPheDuyetValue + tuChoiValue; // lttai
		// 9/7/2015

		result.setSoHsDaNop_TT(String.valueOf(daNopValue));
		result.setSoHsDangXuLy_TT(String.valueOf(dangXuLyValue));
		result.setSoHsChapNhan_TT(String.valueOf(chapNhanValue));
		result.setSoHsTuChoi_TT(String.valueOf(tuChoiValue));
		result.setSoHsYeuCauBoSung_TT(String.valueOf(yeuCauBoSungValue));
		result.setSoHsDaPheDuyet_TT(String.valueOf(daPheDuyetValue));
		result.setSoHsDaCapPhep_TT(String.valueOf(daCapGiayPhepRCValue
				+ daCapGiayPhepQCValue));

		result.setReportDate("Ng\u00E0y " + reportDate.substring(0, 2)
				+ " th\u00E1ng " + reportDate.substring(3, 5) + " n\u0103m "
				+ reportDate.substring(6, 10));

		return result;
	}

	public boolean export2Report(String reportDate, String maritimeCode,
			String dateFrom, String dateTo) throws IOException, SystemException {

		ArrayList<DVCongTheoPhongBanModel> dataBeanList = getDataReport(
				reportDate, maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();

		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils
				.exportFunctionModuleBaoCao(
						BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG_THEO_PHONG_BAN_XML,
						BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG_THEO_PHONG_BAN_EXPORT,
						beanColDataSource, parameters);

		return true;
	}

	public static ArrayList<DVCongTheoPhongBanModel> getDataReport(
			String reportDate, String maritimeCode, String dateFrom,
			String dateTo) {
		ArrayList<DVCongTheoPhongBanModel> dataBeanList = new ArrayList<DVCongTheoPhongBanModel>();

		dataBeanList.add(getModel(reportDate, maritimeCode, dateFrom, dateTo));
		return dataBeanList;
	}

	public boolean export2Excel(String reportDate, String maritimeCode,
			String dateFrom, String dateTo, String fileName)
			throws IOException, SystemException {

		ArrayList<DVCongTheoPhongBanModel> dataBeanList = getDataReport(
				reportDate, maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();

		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils
				.exportFunctionExcel(
						BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG_THEO_PHONG_BAN_XML,
						fileName, beanColDataSource, parameters);

		return true;
	}
}
