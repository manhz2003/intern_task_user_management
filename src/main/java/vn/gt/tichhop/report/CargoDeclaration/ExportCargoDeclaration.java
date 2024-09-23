package vn.gt.tichhop.report.CargoDeclaration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.SystemException;


import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempCargoDeclaration;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGoodsItems;
import vn.gt.dao.noticeandmessage.service.TempCargoDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGoodsItemsLocalServiceUtil;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;

public class ExportCargoDeclaration {
	
	public long export_CargoDeclaration(String requestCode,int documentName, int documentYear, String loaiThuTuc) throws IOException,
	SystemException {

		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<CargoDeclarationModel> dataBeanList=getDataReport(requestCode,documentName,documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);

		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils=new ReportsBusinessUtils();
		
		if (tempDocument.getUpgradeVersion() == 1) {

			exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.CargoDeclaration_TEMP, ReportConstant.CargoDeclaration_EXPORT, beanColDataSource, parameters);
			
		} else {

			exportFunction = reportUtils.exportFunction(ReportConstant.CargoDeclaration_TEMP, ReportConstant.CargoDeclaration_EXPORT, beanColDataSource, parameters);
			
		}

		return exportFunction;
	}

	public static ArrayList<CargoDeclarationModel> getDataReport(String requestCode,int documentName, int documentYear){
		ArrayList<CargoDeclarationModel> dataBeanList = new ArrayList<CargoDeclarationModel>();
		
		dataBeanList.add(getModel(requestCode,documentName,documentYear));
		return dataBeanList;
	}
	
	public static CargoDeclarationModel getModel(String requestCode,int documentName, int documentYear){
	
		CargoDeclarationModel model = new CargoDeclarationModel();
		
		List<TempCargoDeclaration> tempCargoDeclarations = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				tempCargoDeclarations = TempCargoDeclarationLocalServiceUtil.findByRequestCode(requestCode);
			} else {
				tempCargoDeclarations = TempCargoDeclarationLocalServiceUtil.findBydocumentNameAnddocumentYear(
						documentName, documentYear, 0, 1);
			}
		} catch (Exception e) {}
		
		if (tempCargoDeclarations != null && tempCargoDeclarations.size() > 0) {
			TempCargoDeclaration temp = tempCargoDeclarations.get(0);
			
			
			
			List<TempGoodsItems> items = null;
			try {
				items = TempGoodsItemsLocalServiceUtil.findByRequestCode(temp.getRequestCode());
			} catch (Exception e) {}
			
			model.setTempCargoDeclarations(tempCargoDeclarations);
			model.setTempGoodsItems(items);
			
			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
			
			model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
			
			if (Validator.isNotNull(temp.getSignDate())) {
				model.setSignDate(ReportFunction.parserDateToString4(temp.getSignDate()));
			} else {
				model.setSignDate(null);
			}
			
			if (Validator.isNotNull(temp.getSignPlace())) {
				model.setSignPlace(temp.getSignPlace());
			} else {
				model.setSignPlace(null);
			}
		}
		
		return model;
	}
	
}
