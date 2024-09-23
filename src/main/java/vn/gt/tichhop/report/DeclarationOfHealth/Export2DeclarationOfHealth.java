package vn.gt.tichhop.report.DeclarationOfHealth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.SystemException;




import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList;
import com.fds.nsw.nghiepvu.model.TempHealthQuestion;
import vn.gt.dao.noticeandmessage.service.TempDeclarationOfHealthLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempHealthCrewPassengerListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempHealthQuestionLocalServiceUtil;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;


public class Export2DeclarationOfHealth {
	

	
	public long export2DeclarationOfHealth(String requestCode, int documentName, int documentYear, String loaiThuTuc) throws IOException, SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<DeclarationOfHealthModel> dataBeanList = getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
		
		if (tempDocument.getUpgradeVersion() == 1) {

			exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.DECLARATION_OF_HEALTH_TEMP, ReportConstant.DECLARATION_OF_HEALTH_EXPORT, beanColDataSource, parameters);
			
		} else {

			exportFunction = reportUtils.exportFunction(ReportConstant.DECLARATION_OF_HEALTH_TEMP, ReportConstant.DECLARATION_OF_HEALTH_EXPORT, beanColDataSource, parameters);
			
		}

		return exportFunction;
		
	}
	
	public static ArrayList<DeclarationOfHealthModel> getDataReport(String requestCode, int documentName, int documentYear) {
		ArrayList<DeclarationOfHealthModel> dataBeanList = new ArrayList<DeclarationOfHealthModel>();
		
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}
	
	public static DeclarationOfHealthModel getModel(String requestCode, int documentName, int documentYear) {
	
		DeclarationOfHealthModel model = new DeclarationOfHealthModel();
		String masterName = null;
		String doctorName = null;
		String signDate= null;
		
		List<TempDeclarationOfHealth> tempDeclarationOfHealths = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				tempDeclarationOfHealths = TempDeclarationOfHealthLocalServiceUtil.findByRequestCode(requestCode);
			} else {
				tempDeclarationOfHealths = TempDeclarationOfHealthLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear);
			}
		} catch (Exception e) {
		}
		
		model.setTempDeclarationOfHealths(tempDeclarationOfHealths);
		
		if (Validator.isNotNull(tempDeclarationOfHealths) && tempDeclarationOfHealths.size() > 0) {
			TempDeclarationOfHealth temp = tempDeclarationOfHealths.get(0);
			
			if(Validator.isNotNull(temp.getMasterName())) {
				model.setMasterName(temp.getMasterName());
			}
			
			if (Validator.isNotNull(temp.getDoctorName())) {
				model.setDoctorName(temp.getDoctorName());
			}
			
			if (Validator.isNotNull(temp.getSignDate())) {
				model.setSignDate(ReportFunction.parserDateToString3LT(temp.getSignDate()));
			}
		}

		List<TempHealthCrewPaxList> tempCrewPassengers = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				tempCrewPassengers = TempHealthCrewPassengerListLocalServiceUtil.findByRequestCode(requestCode);
			}
			//else {
			//	tempCrewPassengers = TempHealthCrewPassengerListLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear);
			//}
		} catch (Exception e) {
		}
		model.setTempHealthCrewPassengerLists(tempCrewPassengers);
		
		List<TempHealthQuestion> tempHealthQuestions = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				tempHealthQuestions = TempHealthQuestionLocalServiceUtil.findByRequestCode(requestCode);
			}
			//else {
			//	tempHealthQuestions = TempHealthQuestionLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear);
			//}
		} catch (Exception e) {
		}
		model.setTempHealthQuestions(tempHealthQuestions);
		model.setMasterName(masterName);
		model.setDoctorName(doctorName);
		model.setSignDate(signDate);
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
		
		return model;
	}
}
