package vn.gt.portlet.baocao.thongke.tinhhinhnophs;

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
import vn.gt.portlet.baocao.thongke.dvcong.DichVuCongExport;

import com.fds.nsw.kernel.exception.SystemException;



public class TinhHinhNopHoSoExport {
	

	public static TinhHinhNopHoSoModel getModel(String reportDate,
			String maritimeCode, String dateFrom, String dateTo) {

		TinhHinhNopHoSoModel result = new TinhHinhNopHoSoModel();
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

		long ncValue = 0;
		long xcValue = 0;
		long qcValue = 0;
		long tcValue = 0;

		long tauVaoValue = 0;
		long tauRoiValue = 0;
		long pttndVaoCangValue = 0;
		long pttndRoiCangValue = 0;

		// So lan tiep nhan Hs moi (r1)
		ncValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'NC'", "12,15,14", "0, 12, 13, 18, 19, 10, 20");
		xcValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'XC'", "12", "0, 12, 13, 18, 19, 10, 20");
		qcValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'QC'", "12,15,14", "0, 12, 13, 18, 19, 10, 20");
		tauVaoValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'4'", "12,15,14", "0, 12, 13, 18, 19, 10, 20");
		tauRoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'5'", "12", "0, 12, 13, 18, 19, 10, 20");
		pttndVaoCangValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'16'", "12", "0, 12, 13, 18, 19, 10, 20");
		pttndRoiCangValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'17'", "12", "0, 12, 13, 18, 19, 10, 20");
		tcValue = xcValue + qcValue + ncValue + tauVaoValue + tauRoiValue
				+ pttndVaoCangValue + pttndRoiCangValue;

		result.setSoLanNhanHSMoi_NC(String.valueOf(ncValue));
		result.setSoLanNhanHSMoi_XC(String.valueOf(xcValue));
		result.setSoLanNhanHSMoi_QC(String.valueOf(qcValue));
		result.setSoLanNhanHSMoi_TauVao(String.valueOf(tauVaoValue));
		result.setSoLanNhanHSMoi_TauRoi(String.valueOf(tauRoiValue));
		result.setSoLanNhanHSMoi_PTTNDVaoCang(String.valueOf(pttndVaoCangValue));
		result.setSoLanNhanHSMoi_PTTNDRoiCang(String.valueOf(pttndRoiCangValue));
		result.setSoLanNhanHSMoi_TC(String.valueOf(tcValue));

		// ltai 9/7/2015
		// So lan tiep nhan ho so bo xung (r2)
		ncValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'NC'", "27,15", "0,13");
		xcValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'XC'", "12,27", "13,0");
		qcValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'QC'", "27,15", "0,13");
		tauVaoValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'4'", "27,15", "0,13");
		tauRoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'5'", "12,27", "13,0");
		pttndVaoCangValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'16'", "12,27", "13,0");
		pttndRoiCangValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'17'", "12,27", "13,0");
		tcValue = xcValue + qcValue + ncValue + tauVaoValue + tauRoiValue
				+ pttndVaoCangValue + pttndRoiCangValue;

		result.setSoLanNhanHSBoXung_NC(String.valueOf(ncValue));
		result.setSoLanNhanHSBoXung_XC(String.valueOf(xcValue));
		result.setSoLanNhanHSBoXung_QC(String.valueOf(qcValue));
		result.setSoLanNhanHSBoXung_TauVao(String.valueOf(tauVaoValue));
		result.setSoLanNhanHSBoXung_TauRoi(String.valueOf(tauRoiValue));
		result.setSoLanNhanHSBoXung_PTTNDVaoCang(String
				.valueOf(pttndVaoCangValue));
		result.setSoLanNhanHSBoXung_PTTNDRoiCang(String
				.valueOf(pttndRoiCangValue));
		result.setSoLanNhanHSBoXung_TC(String.valueOf(tcValue));

		// ltai 9/7/2015
		// So lan tu choi ho so (r3)
		ncValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'NC'", "13,16,15", "0,10");
		xcValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'XC'", "12,13", "10,0");
		qcValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'QC'", "15,13", "10,0");
		tauVaoValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'4'", "13,16,15", "0,10");
		tauRoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'5'", "12,13", "10,0");
		pttndVaoCangValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'16'", "12,13", "10,0");
		pttndRoiCangValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'17'", "12,13", "10,0");
		tcValue = xcValue + qcValue + ncValue + tauVaoValue + tauRoiValue
				+ pttndVaoCangValue + pttndRoiCangValue;

		result.setSoLanTuChoiHS_NC(String.valueOf(ncValue));
		result.setSoLanTuChoiHS_XC(String.valueOf(xcValue));
		result.setSoLanTuChoiHS_QC(String.valueOf(qcValue));
		result.setSoLanTuChoiHS_TauVao(String.valueOf(tauVaoValue));
		result.setSoLanTuChoiHS_TauRoi(String.valueOf(tauRoiValue));
		result.setSoLanTuChoiHS_PTTNDVaoCang(String.valueOf(pttndVaoCangValue));
		result.setSoLanTuChoiHS_PTTNDRoiCang(String.valueOf(pttndRoiCangValue));
		result.setSoLanTuChoiHS_TC(String.valueOf(tcValue));

		// So lan gui thong bao chap nhan (r4)
		ncValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'NC'", "12,14,15", "0, 12, 18, 19, 20");
		xcValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'XC'", "12", "12, 18, 19, 20");
		qcValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'QC'", "12,14,15", "0,12, 18, 19, 20");
		tauVaoValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'4'", "12,14,15", "0, 12, 18, 19, 20");
		tauRoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'5'", "12", "12, 18, 19, 20");
		pttndVaoCangValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'16'", "12", "12, 18, 19, 20");
		pttndRoiCangValue = TempDocumentLocalServiceUtil
				.countTempDocumentForStatisticsReport(maritimeCode, dateFrom,
						dateTo, "'17'", "12", "12, 18, 19, 20");
		tcValue = xcValue + qcValue + ncValue + tauVaoValue + tauRoiValue
				+ pttndVaoCangValue + pttndRoiCangValue;

		result.setSoLanGuiThongBaoChapNhan_NC(String.valueOf(ncValue));
		result.setSoLanGuiThongBaoChapNhan_XC(String.valueOf(xcValue));
		result.setSoLanGuiThongBaoChapNhan_QC(String.valueOf(qcValue));
		result.setSoLanGuiThongBaoChapNhan_TauVao(String.valueOf(tauVaoValue));
		result.setSoLanGuiThongBaoChapNhan_TauRoi(String.valueOf(tauRoiValue));
		result.setSoLanGuiThongBaoChapNhan_PTTNDVaoCang(String
				.valueOf(pttndVaoCangValue));
		result.setSoLanGuiThongBaoChapNhan_PTTNDRoiCang(String
				.valueOf(pttndRoiCangValue));
		result.setSoLanGuiThongBaoChapNhan_TC(String.valueOf(tcValue));

		// So lan cap lenh dieu dong (r5)
		// ncValue =
		// TempDocumentLocalServiceUtil.countTempDocumentJoinIssuePortClearanceForStatisticsReport(maritimeCode,
		// dateFrom, dateTo, "'NC'",
		// "15", "4", " 10 , 12 , 13 , 15 , 18 , 19 , 20 ");
		// qcValue =
		// TempDocumentLocalServiceUtil.countTempDocumentJoinIssuePortClearanceForStatisticsReport(maritimeCode,
		// dateFrom, dateTo, "'QC'",
		// "15", "4", " 10 , 12 , 13 , 15 , 18 , 19 , 20 ");
		ncValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssueShiftingOrderForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'NC'", "15", "4", null);
		qcValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssueShiftingOrderForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'QC'", "15", "4", null);
		tauVaoValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssueShiftingOrderForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'4'", "15", "4", null);
		tcValue = qcValue + ncValue + tauVaoValue;

		result.setSoLanCapLenhDieuDong_TauVao(String.valueOf(tauVaoValue));
		result.setSoLanCapLenhDieuDong_NC(String.valueOf(ncValue));
		result.setSoLanCapLenhDieuDong_QC(String.valueOf(qcValue));
		result.setSoLanCapLenhDieuDong_TC(String.valueOf(tcValue));

		// So lan cap giay phep (r6)
		xcValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssuePortClearanceForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'XC'", "12", "4", "19");
		qcValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssuePermissionTransitForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'QC'", "15", "4", "19");

		tauRoiValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssuePortClearanceForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'5'", "12", "4", "19");
		pttndVaoCangValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssueAcceptanceForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'16'", "12", "4", "19");
		pttndRoiCangValue = TempDocumentLocalServiceUtil
				.countTempDocumentJoinIssuePortClearanceForStatisticsReport(
						maritimeCode, dateFrom, dateTo, "'17'", "12", "4", "19");
		tcValue = xcValue + qcValue + tauRoiValue + pttndVaoCangValue
				+ pttndRoiCangValue;

		result.setSoLanCapGiayPhep_XC(String.valueOf(xcValue));
		result.setSoLanCapGiayPhep_QC(String.valueOf(qcValue));
		result.setSoLanCapGiayPhep_TauRoi(String.valueOf(tauRoiValue));
		result.setSoLanCapGiayPhep_PTTNDVaoCang(String
				.valueOf(pttndVaoCangValue));
		result.setSoLanCapGiayPhep_PTTNDRoiCang(String
				.valueOf(pttndRoiCangValue));

		result.setSoLanCapGiayPhep_TC(String.valueOf(tcValue));

		result.setReportDate("Ng\u00E0y " + reportDate.substring(0, 2)
				+ " th\u00E1ng " + reportDate.substring(3, 5) + " n\u0103m "
				+ reportDate.substring(6, 10));

		return result;
	}

	public boolean export2Report(String reportDate, String maritimeCode,
			String dateFrom, String dateTo) throws IOException, SystemException {

		ArrayList<TinhHinhNopHoSoModel> dataBeanList = getDataReport(
				reportDate, maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();

		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunctionModuleBaoCao(
				BaoCaoConstant.BAO_CAO_THONG_KE_TINH_HINH_NOP_HS_XML,
				BaoCaoConstant.BAO_CAO_THONG_KE_TINH_HINH_NOP_HS_EXPORT,
				beanColDataSource, parameters);

		return true;
	}

	public void export2Report_bk(String reportDate, String maritimeCode,
			String dateFrom, String dateTo) throws IOException, SystemException {

		ArrayList<TinhHinhNopHoSoModel> dataBeanList = getDataReport(
				reportDate, maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();

		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunctionModuleBaoCao(
				BaoCaoConstant.BAO_CAO_THONG_KE_TINH_HINH_NOP_HS_XML,
				BaoCaoConstant.BAO_CAO_THONG_KE_TINH_HINH_NOP_HS_EXPORT,
				beanColDataSource, parameters);
	}

	public static ArrayList<TinhHinhNopHoSoModel> getDataReport(
			String reportDate, String maritimeCode, String dateFrom,
			String dateTo) {
		ArrayList<TinhHinhNopHoSoModel> dataBeanList = new ArrayList<TinhHinhNopHoSoModel>();

		dataBeanList.add(getModel(reportDate, maritimeCode, dateFrom, dateTo));
		return dataBeanList;
	}

	public boolean export2Excel(String reportDate, String maritimeCode,
			String dateFrom, String dateTo, String fileName)
			throws IOException, SystemException {

		ArrayList<TinhHinhNopHoSoModel> dataBeanList = getDataReport(
				reportDate, maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();

		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunctionExcel(
				BaoCaoConstant.BAO_CAO_THONG_KE_TINH_HINH_NOP_HS_XML, fileName,
				beanColDataSource, parameters);

		return true;
	}
}
