package vn.gt.tichhop.report.GeneralDeclaration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.SystemException;




import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;
@Slf4j
public class Export2GeneralDeclaration {
	
	
	
	public long export2GeneralDeclaration(String requestCode, int documentName, int documentYear, String sloaiThuTuc) throws IOException, SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<TempGeneralDeclaration> dataBeanList = getDataReport(requestCode, documentName, documentYear, sloaiThuTuc);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();

		if (sloaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG) || sloaiThuTuc.equals(MessageType.LOAT_THU_TUC_ROI_CANG)){
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.GENERAL_DECLARATION_TEMP, ReportConstant.GENERAL_DECLARATION_EXPORT, beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.INLAND_GENERAL_DECLARATION_TEMP, ReportConstant.GENERAL_DECLARATION_EXPORT, beanColDataSource, parameters);
				
			}
		 
		}else {
			
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.GENERAL_DECLARATION_TEMP, ReportConstant.GENERAL_DECLARATION_EXPORT, beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.GENERAL_DECLARATION_TEMP, ReportConstant.GENERAL_DECLARATION_EXPORT, beanColDataSource, parameters);
				
			}
			
		}
		return exportFunction;
		
	}
	
	public static ArrayList<TempGeneralDeclaration> getDataReport(String requestCode, int documentName, int documentYear, String sloaiThuTuc) {
		
		ArrayList<TempGeneralDeclaration> dataBeanList = new ArrayList<TempGeneralDeclaration>();
		
		dataBeanList.add(getModel(requestCode, documentName, documentYear, sloaiThuTuc));
		return dataBeanList;
	}
	
	public static TempGeneralDeclaration getModel(String requestCode, int documentName, int documentYear, String sLoaiThuTuc) {
		
		List<TempGeneralDeclaration> tempGeneralDeclas = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				log.info("==getModel==requestCode=1==" + requestCode);
				tempGeneralDeclas = TempGeneralDeclarationLocalServiceUtil.findByRequestCode(requestCode);
				log.info("==getModel==requestCode=2==" + requestCode);
			} else {
				log.info("==getModel==requestCode=1==nulllllll");
				tempGeneralDeclas = TempGeneralDeclarationLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
				log.info("==getModel==requestCode=2==nulllllll");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		int iLoaiThuTuc = ReportFunction.iLoaiThuTuc(sLoaiThuTuc);
		if (Validator.isNull(tempGeneralDeclas)) {
			tempGeneralDeclas = new ArrayList<TempGeneralDeclaration>();
		}
		
		log.info("==getModel==tempGeneralDeclas==size==" + tempGeneralDeclas.size());
		
		for (TempGeneralDeclaration temp : tempGeneralDeclas) {
			
			temp.setId(Long.valueOf(iLoaiThuTuc));
			if (iLoaiThuTuc == MessageType.TAU_VAO || iLoaiThuTuc == MessageType.TAU_VAO_PTTND) {
				temp.setIsArrival(1);
			}else if (iLoaiThuTuc == MessageType.TAU_RA || iLoaiThuTuc == MessageType.TAU_RA_PTTND) {
				temp.setIsArrival(0);
			}
			
		}
		return (tempGeneralDeclas.size() > 0 ? tempGeneralDeclas.get(0) : null);
	}
}
