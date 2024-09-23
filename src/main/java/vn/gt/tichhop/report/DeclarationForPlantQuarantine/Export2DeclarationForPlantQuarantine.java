package vn.gt.tichhop.report.DeclarationForPlantQuarantine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.fds.nsw.kernel.exception.SystemException;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempPlantQuarantine;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalServiceUtil;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;

public class Export2DeclarationForPlantQuarantine {

	public long export2DeclarationForPlantQuarantine(String requestCode,int documentName, int documentYear
			, String loaiThuTuc) throws IOException,
	SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<DeclarationForPlantQuarantineModel> dataBeanList=getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);

		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils=new ReportsBusinessUtils();
		
		if (tempDocument.getUpgradeVersion() == 1) {

			exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.DECLARATION_FOR_PLANT_QUARANTINE_TEMP, ReportConstant.DECLARATION_FOR_PLANT_QUARANTINE_EXPORT, beanColDataSource, parameters);
			
		} else {

			exportFunction = reportUtils.exportFunction(ReportConstant.DECLARATION_FOR_PLANT_QUARANTINE_TEMP, ReportConstant.DECLARATION_FOR_PLANT_QUARANTINE_EXPORT, beanColDataSource, parameters);
			
		}

		return exportFunction;
		
	}
	
	public static ArrayList<DeclarationForPlantQuarantineModel> getDataReport(String requestCode,int documentName, int documentYear){
		ArrayList<DeclarationForPlantQuarantineModel> dataBeanList = new ArrayList<DeclarationForPlantQuarantineModel>();
		
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}	
	
	public static DeclarationForPlantQuarantineModel getModel(String requestCode,int documentName, int documentYear){
		
		DeclarationForPlantQuarantineModel model = new DeclarationForPlantQuarantineModel();
		
		List<TempPlantQuarantine> tempPlantQuarantines = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				tempPlantQuarantines = TempPlantQuarantineLocalServiceUtil.findByRequestCode(requestCode);
			} else {
				tempPlantQuarantines = TempPlantQuarantineLocalServiceUtil.findBydocumentNameAnddocumentYear(
						documentName, documentYear, 0, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setTempPlantQuarantines(tempPlantQuarantines);

		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
		
		return model;
	}
}
