package vn.gt.tichhop.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.fds.nsw.liferay.core.*;





import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.JRTemplatePrintText;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;

@Slf4j
public class ReportUtils
 {
	
	public static final String PDF_DEFAULT_FILE_NAME = "moh_report_temp.pdf";
	public static final String XLS_DEFAULT_FILE_NAME = "moh_report_temp.xls";
	
	
	
	public static String getTemplateReportFilePath(ResourceRequest resourceRequest, String fileName) {
		return resourceRequest.getPortletSession().getPortletContext().getRealPath("/").replace("/", File.separator)
				.replace(File.separator + ".", "") + "report" + File.separator + fileName;
	}

	public static String getTemplateReportFilePath(ActionRequest request) {
		return request.getPortletSession().getPortletContext().getRealPath("/").replace("/", File.separator).replace(File.separator + ".", "");
		
	}
	
	
	
	public static String getSubTemplatePath(ResourceRequest resourceRequest) {
		return resourceRequest.getPortletSession().getPortletContext().getRealPath("/").replace("/", File.separator)
				.replace(File.separator + ".", "") + "report" + File.separator ;
	}	
	
	public static String getTemplateReportFilePath(ResourceRequest request) {
		return request.getPortletSession().getPortletContext().getRealPath("/").replace("/", File.separator).replace(File.separator + ".", "");
		
	}
	
	
	public void exportBieuMau(ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			String tenBieuMauTemplate, String tenExport, JRDataSource dataSource, Map parameters) throws IOException {
		
		log.debug("---VAO 3 ---");
		
		/* default PDF*/
		
		//			String outputFileName = tenExport + "_" + System.currentTimeMillis() + ".pdf";
		//			
		//			// Tao resourceResponse
		//			resourceResponse.setContentType("application/pdf;charset=UTF-8;");
		//			resourceResponse.setCharacterEncoding("UTF-8");
		//			resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + outputFileName
		//					+ "\"");
		//			
		//			try {
		//				resourceResponse.flushBuffer();
		//			} catch (IOException e) {
		//				e.printStackTrace();
		//			}
		
		String outPdfFile = PDF_DEFAULT_FILE_NAME;
			
		try {
			log.debug("---FILE TEMP ---" + getTemplateReportFilePath(resourceRequest, tenBieuMauTemplate));
			
			InputStream inputStream = new FileInputStream(
					getTemplateReportFilePath(resourceRequest, tenBieuMauTemplate));
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			String subPath = getSubTemplatePath(resourceRequest);
			
			parameters.put("SUBREPORT_DIR", subPath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			JasperExportManager.exportReportToHtmlFile(jasperPrint, "vinh.html");
			log.debug("--EXPORT SERVER ---");
			JasperExportManager
					.exportReportToPdfFile(jasperPrint,
							"/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/export/export_jasper.pdf");
			log.debug("--END EXPORT SERVER ---");
			
			//				correctPageNumbersPDF(jasperPrint);
			
			//				JRPdfExporter exporter = new JRPdfExporter();
			//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outPdfFile);
			//				exporter.exportReport();
			//				
			//				FileInputStream fileInputStream = new FileInputStream(outPdfFile);
			//				
			//				OutputStream outStream = resourceResponse.getPortletOutputStream();
			//				
			//				int bytes;
			//				while ((bytes = fileInputStream.read()) != -1) {
			//					outStream.write(bytes);
			//				}
			//				outStream.flush();
			//				outStream.close();
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
			
	}
	
	public void correctPageNumbersPDF(JasperPrint jasperPrintMain) {
		
		List<JRPrintPage> listPages = jasperPrintMain.getPages();
		int numberOfPages = listPages.size();
		
		int currentPageIndex = 1;
		for (JRPrintPage currentPage : listPages) {
			
			List listElements = currentPage.getElements();
			
			for (Object element : listElements) {
				if (element instanceof JRTemplatePrintText) {
					JRTemplatePrintText templatePrintText = (JRTemplatePrintText) element;
					
					// set currrent page / set total number of pages
					if (templatePrintText.getKey() != null && templatePrintText.getKey().equalsIgnoreCase("textFieldCurrentPage")) {
						if (currentPageIndex > numberOfPages) {
							currentPageIndex = numberOfPages;
						}
						templatePrintText.setText(String.valueOf(currentPageIndex) + " / " + String.valueOf(numberOfPages));
						currentPageIndex++;
					}
				}
			}
		}
	}
}
