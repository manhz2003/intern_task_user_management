package vn.gt.portlet.baocao.thongke.dvcong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc;
import vn.gt.dao.noticeandmessage.service.ViewHoanThanhThuTucLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.BaoCaoConstant;

import com.fds.nsw.kernel.exception.SystemException;



public class CoCheMotCuaQuocGiaNangCapExport {

	

	public static CoCheMotCuaQuocGiaNangCapModel getModel(String reportDate,
			String maritimeCode, String dateFrom, String dateTo) {

		CoCheMotCuaQuocGiaNangCapModel result = new CoCheMotCuaQuocGiaNangCapModel();
		DmHistoryMaritime historyMaritime = null;

		historyMaritime = DmHistoryMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode);
		if (historyMaritime != null) {
			result.setMaritimeNameVN(historyMaritime.getMaritimeNameVN());
			result.setMaritimeName(historyMaritime.getMaritimeName());
			result.setReportDate(historyMaritime.getCityCode() + ", ");
		}
		System.out
				.println("HoanThanhThuTucMotCuaQuocGiaExport.getModel(historyMaritime)"
						+ historyMaritime);
		result.setStartDate(dateFrom.substring(0, 10));
		result.setToDate(dateTo.substring(0, 10));
		result.setMonthOfPeriod(dateTo.substring(3, 5));
		result.setYearOfPeriod(dateTo.substring(6, 10));
		result.setReportDate(result.getReportDate() + "ng\u00E0y "
				+ reportDate.substring(0, 2) + " th\u00E1ng "
				+ reportDate.substring(3, 5) + " n\u0103m "
				+ reportDate.substring(6, 10));
		List<ViewHoanThanhThuTuc> viewHoanThanhThuTuc = null;
		viewHoanThanhThuTuc = ViewHoanThanhThuTucLocalServiceUtil
				.findByKetQuaHoanThanhThuTuc(maritimeCode, dateFrom, dateTo);
		if (viewHoanThanhThuTuc != null && viewHoanThanhThuTuc.size() > 0) {
			result.setViewHoanThanhThuTuc(viewHoanThanhThuTuc);
			System.out
					.println("HoanThanhThuTucMotCuaQuocGiaExport.getModel(viewHoanThanhThuTuc)"
							+ viewHoanThanhThuTuc.size());
		}

		System.out
				.println("HoanThanhThuTucMotCuaQuocGiaExport.getModel(dateFrom)"
						+ dateFrom);
		System.out
				.println("HoanThanhThuTucMotCuaQuocGiaExport.getModel(dateTo)"
						+ dateTo);

		return result;
	}

	public boolean export2Report(String reportDate, String maritimeCode,
			String dateFrom, String dateTo) throws IOException, SystemException {

		ArrayList<CoCheMotCuaQuocGiaNangCapModel> dataBeanList = getDataReport(
				reportDate, maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();

		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils
				.exportFunctionModuleBaoCao(
						BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA_NANG_CAP_XML,
						BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA_NANG_CAP_EXPORT,
						beanColDataSource, parameters);

		return true;
	}

	public static ArrayList<CoCheMotCuaQuocGiaNangCapModel> getDataReport(
			String reportDate, String maritimeCode, String dateFrom,
			String dateTo) {
		ArrayList<CoCheMotCuaQuocGiaNangCapModel> dataBeanList = new ArrayList<CoCheMotCuaQuocGiaNangCapModel>();

		dataBeanList.add(getModel(reportDate, maritimeCode, dateFrom, dateTo));
		return dataBeanList;
	}

	public boolean export2Excel(String reportDate, String maritimeCode,
			String dateFrom, String dateTo, String fileName)
			throws IOException, SystemException {

		ArrayList<CoCheMotCuaQuocGiaNangCapModel> dataBeanList = getDataReport(
				reportDate, maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();

		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils
				.exportFunctionExcel(
						BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA_NANG_CAP_XML,
						fileName, beanColDataSource, parameters);

		return true;
	}
}
