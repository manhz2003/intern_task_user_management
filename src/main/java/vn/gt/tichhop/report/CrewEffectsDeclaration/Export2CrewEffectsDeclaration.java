package vn.gt.tichhop.report.CrewEffectsDeclaration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.SystemException;


import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration;
import com.fds.nsw.nghiepvu.model.TempCrewEffectsItems;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempCrewEffectsDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewEffectsItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;

public class Export2CrewEffectsDeclaration {
	
	
	public long export2CrewEffectsDeclaration(String requestCode, int documentName, int documentYear, String loaiThuTuc)
			throws IOException, SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<CrewEffectsDeclarationModel> dataBeanList = getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
		
		if (tempDocument.getUpgradeVersion() == 1) {

			exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.CREW_EFFECTS_DECLARATION_TEMP, ReportConstant.CREW_EFFECTS_DECLARATION_EXPORT, beanColDataSource, parameters);
			
		} else {

			exportFunction = reportUtils.exportFunction(ReportConstant.CREW_EFFECTS_DECLARATION_TEMP, ReportConstant.CREW_EFFECTS_DECLARATION_EXPORT, beanColDataSource, parameters);
			
		}

		return exportFunction;
	}
	
	
	public static CrewEffectsDeclarationModel getModel(String requestCode, int documentName, int documentYear) {
	
		CrewEffectsDeclarationModel model = new CrewEffectsDeclarationModel();
		
		List<TempCrewEffectsDeclaration> tempCrews = null;
		List<TempCrewEffectsItems> tempCrewEffectsItems = null;
		
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				tempCrews = TempCrewEffectsDeclarationLocalServiceUtil.findByRequestCode(requestCode);
			} else {
				tempCrews = TempCrewEffectsDeclarationLocalServiceUtil
						.findBydocumentNameAnddocumentYear(documentName, documentYear, 0, 1);
			}
		} catch (Exception e) {
		}
		
		String signDate = null;
		String signPlace = null;
		if (tempCrews != null && tempCrews.size() > 0) {
			TempCrewEffectsDeclaration temp = tempCrews.get(0);
			try {
				tempCrewEffectsItems = TempCrewEffectsItemsLocalServiceUtil.findByRequestCode(temp.getRequestCode());
			} catch (Exception e) {
				tempCrewEffectsItems = new ArrayList<TempCrewEffectsItems>();
			}
			if (Validator.isNotNull(temp.getSignDate())) {
				signDate = ReportFunction.parserDateToString4(temp.getSignDate());
			}
			if (Validator.isNotNull(temp.getSignPlace())) {
				signPlace = temp.getSignPlace();
			}
			
		}
		
		model.setSignDate(signDate);
		model.setSignPlace(signPlace);
		
		model.setTempCrewEffectsDeclarations(tempCrews);
		model.setTempCrewEffectsItems(tempCrewEffectsItems);

		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
		
		return model;
	}
	
	public static ArrayList<CrewEffectsDeclarationModel> getDataReport(String requestCode, int documentName,
			int documentYear) {
		ArrayList<CrewEffectsDeclarationModel> dataBeanList = new ArrayList<CrewEffectsDeclarationModel>();
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}

}
