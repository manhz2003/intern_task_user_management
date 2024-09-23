package vn.gt.tichhop.report.ShipSecurityNotification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
import com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityPortItemsLocalServiceUtil;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;

import com.fds.nsw.kernel.exception.SystemException;

public class ExportShipSecurityNotification {

	public long export_ShipSecurityNotification(String requestCode,int documentName, int documentYear, String sLoaiThuTuc) throws IOException, SystemException {
		
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<ShipSecurityNotificationModel> dataBeanList = getDataReport(requestCode, documentName, documentYear, sLoaiThuTuc);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);

		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils=new ReportsBusinessUtils();
		
		if (tempDocument.getUpgradeVersion() == 1) {

			exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.ShipSecurityNotification_TEMP, ReportConstant.ShipSecurityNotification_EXPORT, beanColDataSource, parameters);
			
		} else {

			exportFunction = reportUtils.exportFunction(ReportConstant.ShipSecurityNotification_TEMP, ReportConstant.ShipSecurityNotification_EXPORT, beanColDataSource, parameters);
			
		}
		
		return exportFunction;
		
	}

	public static ShipSecurityNotificationModel getModel(String requestCode,int documentName, int documentYear, String sLoaiThuTuc) {
		
		ShipSecurityNotificationModel model=new ShipSecurityNotificationModel();
		
		List<TempShipSecurityMessage> shipSecurityMessages = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) { 
				shipSecurityMessages = TempShipSecurityMessageLocalServiceUtil.findByRequestCode(requestCode);
			} else {
				shipSecurityMessages = TempShipSecurityMessageLocalServiceUtil.findBydocumentNameAndDocumentYear(documentName, documentYear, 0, 1);
			}
		} catch (Exception e) {
		}
		
		if (shipSecurityMessages != null && shipSecurityMessages.size() > 0) {
			TempShipSecurityMessage tempShipSecurityMessage = shipSecurityMessages.get(0);
			List<TempShipSecurityPortItems> tempCrewLists = null;
			try {
				tempCrewLists = TempShipSecurityPortItemsLocalServiceUtil.findByRequestCodeOrderByDateArrivalASC(tempShipSecurityMessage.getRequestCode());
			} catch (Exception e) {
			}
			
			int iLoaiThuTuc = ReportFunction.iLoaiThuTuc(sLoaiThuTuc);
			
			for (TempShipSecurityMessage temp : shipSecurityMessages) {
				temp.setId(Long.valueOf(iLoaiThuTuc));
			}
			
			for (TempShipSecurityPortItems item : tempCrewLists) {
				item.setId(Long.valueOf(iLoaiThuTuc));
			}
			model.setTempShipSecurityMessages(shipSecurityMessages);
			model.setTempCrewLists(tempCrewLists);

			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
			
			model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
			
		}
		return model;
	}
	
	public static ArrayList<ShipSecurityNotificationModel> getDataReport(String requestCode,int documentName, int documentYear, String sLoaiThuTuc) {
		ArrayList<ShipSecurityNotificationModel> dataBeanList = new ArrayList<ShipSecurityNotificationModel>();
		
		dataBeanList.add(getModel(requestCode, documentName, documentYear, sLoaiThuTuc));
		return dataBeanList;
	}
}
