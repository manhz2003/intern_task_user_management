package vn.gt.tichhop.report.PassengerList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.fds.nsw.kernel.exception.SystemException;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempPassengerDetails;
import com.fds.nsw.nghiepvu.model.TempPassengerList;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPassengerDetailsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPassengerListLocalServiceUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;

public class Export2PassengerList {

	
	public long export2PassengerList(String requestCode, int documentName, int documentYear, String loaiThuTuc) throws IOException,
			SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<PassengerListModel> dataBeanList = getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();

		if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG) || loaiThuTuc.equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
		 
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.PASSENGER_LIST_TEMP, ReportConstant.PASSENGER_LIST_EXPORT,
						beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.INLAND_PASSENGER_LIST_TEMP, ReportConstant.PASSENGER_LIST_EXPORT,
						beanColDataSource, parameters);
				
			}
			
		}else {
			
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.PASSENGER_LIST_TEMP, ReportConstant.PASSENGER_LIST_EXPORT,
						beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.PASSENGER_LIST_TEMP, ReportConstant.PASSENGER_LIST_EXPORT,
						beanColDataSource, parameters);
				
			}
			
		}
		return exportFunction;
	}
	
	public static PassengerListModel getModel(String requestCode,int documentName, int documentYear) {
		
		PassengerListModel model = new PassengerListModel();
		
		List<TempPassengerList> tempPassengerLists = null;
		List<TempPassengerDetails> tempPassengerDetails = null;
		
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				tempPassengerLists = TempPassengerListLocalServiceUtil.findByRequestCode(requestCode);
			} else {
				tempPassengerLists = TempPassengerListLocalServiceUtil.findBydocumentNameAndDocumentYear(documentName,
						documentYear, 0, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String signPlace = null;
		String signDate = null;
		if (tempPassengerLists != null && tempPassengerLists.size() > 0) {
			TempPassengerList tempPassengerList = tempPassengerLists.get(0);
			if (tempPassengerList.getSignDate() != null) {
				signDate = ReportFunction.parserDateToString4(tempPassengerList.getSignDate());
			}
			if (tempPassengerList.getSignPlace() != null) {
				signPlace = tempPassengerList.getSignPlace();
			}
			try {
				tempPassengerDetails = TempPassengerDetailsLocalServiceUtil.findByRequestCode(tempPassengerList
						.getRequestCode());
			} catch (Exception e) {}
		}
		model.setTempPassengerDetails(tempPassengerDetails);
		model.setTempPassengerLists(tempPassengerLists);
		model.setSignDate(signDate);
		model.setSignPlace(signPlace);

		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
		
		return model;
	}

	
	public static ArrayList<PassengerListModel> getDataReport(String requestCode, int documentName, int documentYear) {
		ArrayList<PassengerListModel> dataBeanList = new ArrayList<PassengerListModel>();
		
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}
	
}
