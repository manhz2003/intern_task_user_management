package vn.gt.tichhop.report.CrewList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.fds.nsw.kernel.exception.SystemException;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempCrewDetailsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;

public class Export2CrewList {
	
	
	public long export2CrewList(String requestCode,int documentName, int documentYear, String loaiThuTuc) throws IOException,
	SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<CrewListModel> dataBeanList=getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);

		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils=new ReportsBusinessUtils();

		if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG) || loaiThuTuc.equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
			
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.CREW_LIST_TEMP, ReportConstant.CREW_LIST_EXPORT, beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.INLAND_CREW_LIST_TEMP, ReportConstant.CREW_LIST_EXPORT, beanColDataSource, parameters);
				
			}
			
		}else {
			
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.CREW_LIST_TEMP, ReportConstant.CREW_LIST_EXPORT, beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.CREW_LIST_TEMP, ReportConstant.CREW_LIST_EXPORT, beanColDataSource, parameters);
				
			}
			
		}
		return exportFunction;
		
	}
	
	public static CrewListModel  getModel(String requestCode,int documentName, int documentYear) {
		
		CrewListModel crewListModel = new CrewListModel();
		
		List<TempCrewList> crewLists = null;
		List<TempCrewDetails> tempCrewDetails = null;
		
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				crewLists = TempCrewListLocalServiceUtil.findByRequestCode(requestCode);
			} else {
				crewLists = TempCrewListLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear,
						0, 1);
			}
		} catch (Exception e) {}
		String signPlace = null;
		String signDate = null;
		if (crewLists != null && crewLists.size() > 0) {
			TempCrewList tempCrewList = crewLists.get(0);
			if (tempCrewList.getSignDate() != null) {
				signDate = ReportFunction.parserDateToString4(tempCrewList.getSignDate());
			}
			if (tempCrewList.getSignPlace() != null) {
				signPlace = tempCrewList.getSignPlace();
			}
			
			try {
				tempCrewDetails = TempCrewDetailsLocalServiceUtil.findByRequestCode(tempCrewList.getRequestCode());
			} catch (Exception e) {}
		}
		crewListModel.setTempCrewDetails(tempCrewDetails);
		crewListModel.setTempCrewLists(crewLists);
		crewListModel.setSignDate(signDate);
		crewListModel.setSignPlace(signPlace);

		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		crewListModel.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
		
		return crewListModel;
	}

	
	public static ArrayList<CrewListModel> getDataReport(String requestCode,int documentName, int documentYear){
		ArrayList<CrewListModel> dataBeanList = new ArrayList<CrewListModel>();
		
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}	
}
