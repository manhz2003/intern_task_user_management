package vn.gt.portlet.baocao;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import vn.gt.portlet.baocao.kehoachdieudong.KeHoachDieuDongExport;
import vn.gt.portlet.baocao.thongke.bc12.Bc12Export;
import vn.gt.portlet.baocao.thongke.dvcong.CoCheMotCuaQuocGiaExport;
import vn.gt.portlet.baocao.thongke.dvcong.CoCheMotCuaQuocGiaNangCapExport;
import vn.gt.portlet.baocao.thongke.dvcong.DichVuCongExport;
import vn.gt.portlet.baocao.thongke.dvcong.HoanThanhThuTucMotCuaQuocGiaExport;
import vn.gt.portlet.baocao.thongke.dvcongtheophongban.DVCongTheoPhongBanExport;
import vn.gt.portlet.baocao.thongke.tinhhinhnophs.TinhHinhNopHoSoExport;
import vn.gt.portlet.baocao.thongtintau.kiemtrathongtintau.KiemTraThongTinTauExport;
import vn.gt.portlet.baocao.thongtintau.phanhoicuacqcn.PhanHoiCuaCqcnExport;
import vn.gt.portlet.baocao.tinhhinhdukienneodau.TinhHinhDuKienNeoDauExport;

import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.utility.string.StringPool;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BaoCaoBussinessUtils {


	
	private final static int SUM = 1, DIV = 2;
	private final static int EXCEL = 1, PDF = 2;
	// private String pathFileTemp =
	// "/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/report/baocao/";
	// private String pathFileSub =
	// "/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/export/";
	// private String pathFileTemp =
	// "/opt/dvc_hanghai/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/report/baocao/";
	// private String pathFileSub =
	// "/opt/dvc_hanghai/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/export/";
	private String realPath = this.getClass().getProtectionDomain()
			.getCodeSource().getLocation().toString();
	private String pathFileTemp = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/report/baocao/";
	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/export/";

	/*
	 * Function Export to Server
	 */

	public boolean actionBaoCaoThongKeExport(int reportCode,
			String maritimeCode, String ngayLap, String dateFrom, String dateTo)
			throws Exception {

		boolean result = true;

		if (reportCode == BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG) {
			log.info("********* Vao KeHoachDieuDongExport ********* ");
			KeHoachDieuDongExport action = new KeHoachDieuDongExport();
			result = action.export2Report(reportCode, maritimeCode, ngayLap,
					dateFrom, dateTo);
		} else if (reportCode == BaoCaoConstant.THONG_BAO_TINH_HINH_TAU_THUYEN_DU_KIEN_NEO_DAU_TAI_CANG) {
			log.info("********* Vao TinhHinhDuKienNeoDauExport ********* ");
			TinhHinhDuKienNeoDauExport action = new TinhHinhDuKienNeoDauExport();
			result = action.export2Report(reportCode, maritimeCode, ngayLap,
					dateFrom, dateTo);
		} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_TINH_HINH_NOP_HO_SO) {
			log.info("********* Vao TinhHinhNopHoSoExport ********* ");
			TinhHinhNopHoSoExport action = new TinhHinhNopHoSoExport();
			result = action.export2Report(ngayLap, maritimeCode, dateFrom,
					dateTo);
		} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_HO_SO_THEO_PHONG_BAN) {
			log.info("********* Vao DVCongTheoPhongBanExport ********* ");
			DVCongTheoPhongBanExport action = new DVCongTheoPhongBanExport();
			result = action.export2Report(ngayLap, maritimeCode, dateFrom,
					dateTo);
		} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG) {
			log.info("********* Vao DichVuCongExport ********* ");
			DichVuCongExport action = new DichVuCongExport();
			result = action.export2Report(ngayLap, maritimeCode, dateFrom,
					dateTo);
		} else if (reportCode == BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA) {
			log.debug("********* Vao CoCheMotCuaQuocGiaNangCapExport ********* ");
			CoCheMotCuaQuocGiaNangCapExport action = new CoCheMotCuaQuocGiaNangCapExport();
			result = action.export2Report(ngayLap, maritimeCode, dateFrom,
					dateTo);
		} else if (reportCode == BaoCaoConstant.BAO_CAO_KET_QUA_HOAN_THANH_THU_TUC) {
			log.debug("********* Vao HoanThanhThuTucMotCuaQuocGiaExport ********* ");
			HoanThanhThuTucMotCuaQuocGiaExport action = new HoanThanhThuTucMotCuaQuocGiaExport();
			result = action.export2Report(ngayLap, null, dateFrom, dateTo);
		}

		// else if (reportCode ==
		// BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA) {
		// log.info("********* Vao CoCheMotCuaQuocGiaExport ********* ");
		// CoCheMotCuaQuocGiaExport action = new CoCheMotCuaQuocGiaExport();
		// result = action.export2Report(ngayLap, maritimeCode, dateFrom,
		// dateTo);
		// }
		//
		else if (reportCode == BaoCaoConstant.BAO_CAO_12) {

			log.info("********* Vao BAO_CAO_12 ********* ");
			Bc12Export action = new Bc12Export();
			result = action.export2Report(ngayLap, maritimeCode, dateFrom,
					dateTo);

		}

		return result;
	}

	public boolean actionExportThongKeTinhHinhNopHoSo(String reportDate,
			String maritimeCode, String dateFrom, String dateTo)
			throws Exception {

		TinhHinhNopHoSoExport action = new TinhHinhNopHoSoExport();
		action.export2Report(reportDate, maritimeCode, dateFrom, dateTo);

		return true;
	}

	public boolean actionExportThongKeDVCongTheoPhongBan(String reportDate,
			String maritimeCode, String dateFrom, String dateTo)
			throws Exception {

		DVCongTheoPhongBanExport action = new DVCongTheoPhongBanExport();
		action.export2Report(reportDate, maritimeCode, dateFrom, dateTo);

		return true;
	}

	public boolean actionExportKetQuaThucHienCoCheMotCuaQuocGia(
			String reportDate, String maritimeCode, String dateFrom,
			String dateTo) throws Exception {

		CoCheMotCuaQuocGiaExport action = new CoCheMotCuaQuocGiaExport();
		action.export2Report(reportDate, maritimeCode, dateFrom, dateTo);

		return true;
	}

	public boolean actionExportThongKeDichVuCong(String reportDate,
			String maritimeCode, String dateFrom, String dateTo)
			throws Exception {

		DichVuCongExport action = new DichVuCongExport();
		action.export2Report(reportDate, maritimeCode, dateFrom, dateTo);

		return true;
	}

	public long actionExportThongTinTau(long documentName, int documentYear,
			String ministryCode) throws Exception {
		PhanHoiCuaCqcnExport action = new PhanHoiCuaCqcnExport();
		return action.export2Report(documentName, documentYear, ministryCode);
	}

	public boolean actionExportKiemTraThongTinTau(long documentName,
			int documentYear) throws Exception {

		KiemTraThongTinTauExport action = new KiemTraThongTinTauExport();
		action.export2Report(documentName, documentYear);

		return true;
	}

	/*
	 * Function Export to Server
	 */
	public long exportFunction(String tenFile_Template, String tenFile_Export,
			JRDataSource dataSource, Map<String, Object> parameters)
			throws IOException {
		long nanoTime = System.nanoTime();
		try {
			InputStream inputStream = new FileInputStream(pathFileTemp
					+ tenFile_Template);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);

			parameters.put("SUBREPORT_DIR", pathFileTemp);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, dataSource);

			JasperExportManager.exportReportToPdfFile(jasperPrint, pathFileSub
					+ nanoTime + "_" + tenFile_Export);
			return nanoTime;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void exportFunctionModuleBaoCao(String tenFile_Template,
			String tenFile_Export, JRDataSource dataSource,
			Map<String, Object> parameters) throws IOException {
		long nanoTime = System.nanoTime();
		try {
			InputStream inputStream = new FileInputStream(pathFileTemp
					+ tenFile_Template);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);

			parameters.put("SUBREPORT_DIR", pathFileTemp);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, dataSource);

			// Ham nay ko ap dung ghep nanotime vao file export
			// JasperExportManager.exportReportToPdfFile(jasperPrint,
			// pathFileSub + nanoTime + "_" + tenFile_Export);
			JasperExportManager.exportReportToPdfFile(jasperPrint, pathFileSub
					+ tenFile_Export);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Ham nay su dung khi pathFileTemp khac folder hien tai
	public boolean exportPDF_EXCEL_VMA(String pathTemplate,
			String tenFile_Export, JSONObject jsonDatasource,
			String pathParam, int typeExport) throws IOException {
		try {
			ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(
					jsonDatasource.toString().getBytes());
			JsonDataSource dataSource = new JsonDataSource(
					jsonDataStream);
			InputStream inputStream = new FileInputStream(pathTemplate);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("SUBREPORT_DIR", pathParam);
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, dataSource);
			
			if (typeExport == PDF) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, pathFileSub
						+ tenFile_Export);
				
				log.info("=====> DONE PDF");
			} else if (typeExport == EXCEL) {
				JRExporter exporter = new JRXlsExporter();
				exporter.setParameter(
						JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
						Boolean.TRUE);
				exporter.setParameter(
						JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
						Boolean.FALSE);
				exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
						Boolean.TRUE);
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
						Boolean.FALSE);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
						pathFileSub + tenFile_Export);
				exporter.exportReport();
				
				log.info("=====> DONE EXCEL");
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	//Ham nay su dung khi datasource là JSON - Chi xuat PDF
	public long exportFunctionPDF(String tenFile_Template,
			String tenFile_Export, JSONObject datasource) throws IOException {
		long nanoTime = System.nanoTime();
		try {

			ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(
					datasource.toString().getBytes());

			JsonDataSource beanColDataSource = new JsonDataSource(
					jsonDataStream);

			Map parameters = new HashMap();

			InputStream inputStream = new FileInputStream(pathFileTemp.replace(
					"baocao/", StringPool.BLANK) + tenFile_Template);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);

			parameters.put("SUBREPORT_DIR",
					pathFileTemp.replace("baocao/", StringPool.BLANK));

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, beanColDataSource);

			JasperExportManager.exportReportToPdfFile(jasperPrint, pathFileSub
					+ tenFile_Export);

			return nanoTime;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void exportFunctionExcel(String tenFile_Template,
			String tenFile_Export, JRDataSource dataSource,
			Map<String, Object> parameters) throws IOException{
		try {
			InputStream inputStream = new FileInputStream(pathFileTemp
					+ tenFile_Template);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);

			parameters.put("SUBREPORT_DIR", pathFileTemp);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, dataSource);
			JRExporter exporter = new JRXlsExporter();
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					pathFileSub + tenFile_Export);
			exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int countDayByMonthAndYear(int month, int year) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
				return 29;
			} else {
				return 28;
			}
		default:
			log.error("============ Thang nhap vao khong hop le.");
			return 0;
		}
	}

	public static double calculator(double num1, double num2, int operation) {
		if (operation == SUM) {
			if (num1 >= 0) {
				if (num2 >= 0) {
					return num1 + num2;
				} else {
					return num1;
				}
			} else if (num2 >= 0) {
				return num2;
			} else {
				return -1;
			}
		} else if (operation == DIV) {
			if (num1 > 0 && num2 > 0) {
				try {
					return num1 / num2;
				} catch (Exception e) {
					return -1;
				}
			} else {
				return -1;
			}
		}
		log.error("============= Sai phep tinh");
		return -1;
	}

	public static int calculator(int num1, int num2, int operation) {
		if (operation == SUM) {
			if (num1 >= 0) {
				if (num2 >= 0) {
					return num1 + num2;
				} else {
					return num1;
				}
			} else if (num2 >= 0) {
				return num2;
			} else {
				return -1;
			}
		} else if (operation == DIV) {
			if (num1 > 0 && num2 > 0) {
				try {
					return num1 / num2;
				} catch (Exception e) {
					return -1;
				}
			} else {
				return -1;
			}
		}
		log.error("============= Sai phep tinh");
		return -1;
	}

	public static double calculator(JSONObject num1, JSONObject num2,
			String key1, String key2, int operation) {
		if (operation == SUM) {
			if (num1.has(key1)) {
				if (num2.has(key2)) {
					return num1.getDouble(key1) + num2.getDouble(key2);
				} else {
					return num1.getDouble(key1);
				}
			} else if (num2.has(key2)) {
				return num2.getDouble(key2);
			} else {
				return -1;
			}
		} else if (operation == DIV) {
			if (num1.has(key1)) {
				if (num2.has(key2) && num1.getDouble(key1) != 0) {
					if (num2.getDouble(key2) != 0) {
						return num1.getDouble(key1) / num2.getDouble(key2);
					}
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		}
		return -1;
	}

	public static int calculator(JSONObject num1, JSONObject num2, String key1,
			String key2, int operation, int hanhkhach) {
		if (operation == SUM) {
			if (num1.has(key1)) {
				if (num2.has(key2)) {
					return num1.getInt(key1) + num2.getInt(key2);
				} else {
					return num1.getInt(key1);
				}
			} else if (num2.has(key2)) {
				return num2.getInt(key2);
			} else {
				return -1;
			}
		} else if (operation == DIV) {
			if (num1.has(key1)) {
				if (num2.has(key2) && num1.getInt(key1) != 0) {
					if (num2.getInt(key2) != 0) {
						return num1.getInt(key1) / num2.getInt(key2);
					}
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		}
		return -1;
	}

	public static double sum1(double... obj) {
		double sum = 0;
		if (obj != null) {
			for (double value : obj) {
				if (value >= 0) {
					sum += value;
				}
			}
			if (sum >= 0) {
				return sum;
			}
		}
		return -1;
	}

	public static int sum2(int... obj) {
		int sum = 0;
		if (obj != null) {
			for (int value : obj) {
				if (value >= 0) {
					sum += value;
				}
			}
			if (sum >= 0) {
				return sum;
			}
		}
		return -1;
	}

	public static JSONObject mergeJson(JSONObject... obj) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		for (JSONObject value : obj) {
			Iterator<String> keys = value.keys();
			if (keys.hasNext()) {
				while (keys.hasNext()) {
					String key = keys.next();
					if (value.has(key)) {
						result.put(key, value.getString(key));
					}
				}
			}
		}
		return result;
	}

	//Ham nay su dung khi dau vao la JSON
	public boolean export2Report(JSONObject jsonDatasource,
			String templateName, String exportName, int typeExport)
			throws SystemException, ParseException, IOException, JRException {
		try {
			ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(
					jsonDatasource.toString().getBytes());
			// Create json datasource from json stream
			JsonDataSource beanColDataSource = new JsonDataSource(
					jsonDataStream);

			Map parameters = new HashMap();

			if (typeExport == PDF) {
				exportFunctionModuleBaoCao(templateName, exportName,
						beanColDataSource, parameters);
			} else if (typeExport == EXCEL) {
				exportFunctionExcel(templateName, exportName,
						beanColDataSource, parameters);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}

		return true;
	}
}
