package vn.gt.tichhop.report.ShipStoresDeclaration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.SystemException;




import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration;
import com.fds.nsw.nghiepvu.model.TempShipStoresItems;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipStoresItemsLocalServiceUtil;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;

@Slf4j
public class Export2ShipStoresDeclaration {
	
	
	
	public long exportShipStoresDeclaration(String requestCode, int documentName, int documentYear, String loaiThuTuc) throws IOException, SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<ShipStoresDeclarationModel> dataBeanList = getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
		
		if (tempDocument.getUpgradeVersion() == 1) {

			
			exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.SHIP_STORES_DECLARATION_TEMP, ReportConstant.SHIP_STORES_DECLARATION_EXPORT, beanColDataSource, parameters);
		} else {

			exportFunction = reportUtils.exportFunction(ReportConstant.SHIP_STORES_DECLARATION_TEMP, ReportConstant.SHIP_STORES_DECLARATION_EXPORT, beanColDataSource, parameters);
			
		}

		return exportFunction;
		
	}
	
	public static ArrayList<ShipStoresDeclarationModel> getDataReport(String requestCode, int documentName, int documentYear) {
		ArrayList<ShipStoresDeclarationModel> dataBeanList = new ArrayList<ShipStoresDeclarationModel>();
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}
	
	public static ShipStoresDeclarationModel getModel(String requestCode, int documentName, int documentYear) {
		
		ShipStoresDeclarationModel model = new ShipStoresDeclarationModel();
		
		List<TempShipStoresDeclaration> tempShips = null;
		
		try {
			if (requestCode != null && requestCode.trim().length() > 0 ) {
				tempShips = TempShipStoresDeclarationLocalServiceUtil.findByRequestCode(requestCode);
			} else {
				tempShips = TempShipStoresDeclarationLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		List<TempShipStoresItems> storesItems = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				storesItems = TempShipStoresItemsLocalServiceUtil.findTempShipStoresItemsByRequestCode(requestCode);
			} else {
				storesItems = new ArrayList<TempShipStoresItems>();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		String signDate = null;
		String signPlace = null;
		
		if (Validator.isNotNull(tempShips) && tempShips.size() > 0) {
			TempShipStoresDeclaration temp = tempShips.get(0);
			if (Validator.isNotNull(temp.getSignDate())) {
				signDate = ReportFunction.parserDateToString4(temp.getSignDate());
			}
			if (Validator.isNotNull(temp.getSignPlace())) {
				signPlace = temp.getSignPlace();
			}
		}
		model.setSignDate(signDate);
		model.setSignPlace(signPlace);
		
		model.setTempShipStoresDeclarationes(tempShips);
		model.setTempShipStoresItems(storesItems);
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
		
		return model;
	}
}
