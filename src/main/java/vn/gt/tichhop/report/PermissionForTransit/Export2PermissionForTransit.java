package vn.gt.tichhop.report.PermissionForTransit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.SystemException;


import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;

public class Export2PermissionForTransit {
	
	public long export2PermissionForTransit(String requestCode,int documentName, int documentYear, String loaiThuTuc) throws IOException,
			SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<PermissionForTransitModel> dataBeanList = getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
		
		if (tempDocument.getUpgradeVersion() == 1) {

			exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.PERMISSION_FOR_TRANSIT_TEMP, ReportConstant.PERMISSION_FOR_TRANSIT_EXPORT, beanColDataSource, parameters);
			
		} else {

			exportFunction = reportUtils.exportFunction(ReportConstant.PERMISSION_FOR_TRANSIT_TEMP, ReportConstant.PERMISSION_FOR_TRANSIT_EXPORT, beanColDataSource, parameters);
			
		}
		return exportFunction;
		
	}

	public static PermissionForTransitModel getModel(String requestCode,int documentName, int documentYear) {
		PermissionForTransitModel model = new PermissionForTransitModel();
		try {
			
			List<IssuePermissionForTransit> issueObjs = null;
			try {
				if (requestCode != null && requestCode.trim().length() > 0) {
					issueObjs = IssuePermissionForTransitLocalServiceUtil.findByrequestCode(requestCode);
				} else {
					issueObjs = IssuePermissionForTransitLocalServiceUtil
							.findBydocumentNameAndDocumentYear(documentName, documentYear, 0, 1);
				}
			} catch (Exception e) {}
			
			String maritimeNameVN = null;
			String maritimeName = null;
			
			if (Validator.isNotNull(issueObjs) && issueObjs.size() > 0) {
				IssuePermissionForTransit item = issueObjs.get(0);
				if (Validator.isNotNull(item.getPortofAuthority())) {
					DmMaritime maritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(item.getPortofAuthority());
					if (Validator.isNotNull(maritime)) {
						if (Validator.isNotNull(maritime.getMaritimeNameVN())) {
							maritimeNameVN = maritime.getMaritimeNameVN();
						}
						maritimeName = maritime.getMaritimeName();
					}
				}
				
				model.setRequestCode(item.getRequestCode());
				
				model.setNumberPermissionForTransit(item.getNumberPermissionForTransit());
				model.setDocumentName(item.getDocumentName());
				model.setDocumentYear(item.getDocumentYear());
				model.setPortofAuthority(item.getPortofAuthority());
				
				model.setPortofAuthority(item.getPortofAuthority());
				model.setNameOfShip(item.getNameOfShip());
				model.setFlagStateOfShip(item.getFlagStateOfShip());
				model.setGt(item.getGt());
				model.setCertificateNo(item.getCertificateNo());
				model.setNumberOfCrews(item.getNumberOfCrews());
				model.setNumberOfPassengers(item.getNumberOfPassengers());
				model.setCallSign(item.getCallSign());
				model.setNameOfMaster(item.getNameOfMaster());
				model.setTransitCargo(item.getTransitCargo());
				model.setVolumeCargo(item.getVolumeCargo());
				model.setCargoUnit(item.getCargoUnit());
				model.setCargoDescription(item.getCargoDescription());
				
				
				model.setPermittedTransitFrom(item.getPermittedTransitFrom());
				model.setPermittedTransitTo(item.getPermittedTransitTo());
				
				model.setTimeOfDeparture(item.getTimeOfDeparture());
				model.setValidUntil(item.getValidUntil());
				model.setDateOfSign(item.getDateOfSign());
				model.setUserCreated(item.getUserCreated());
				model.setDirectorOfMaritime(item.getDirectorOfMaritime());
				model.setCreatedDate(item.getCreatedDate());
				model.setRepresentative(item.getRepresentative());
				model.setSignName(item.getSignName());
				model.setSignTitle(item.getSignTitle());
				model.setRemarks(item.getRemarks().trim());
			}
			
			model.setMaritimeName(maritimeName);
			model.setMaritimeNameVN(maritimeNameVN);
			

			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
			
			model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return model;
	}
	
	public static ArrayList<PermissionForTransitModel> getDataReport(String requestCode,int documentName, int documentYear){
		ArrayList<PermissionForTransitModel> dataBeanList = new ArrayList<PermissionForTransitModel>();
		
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}	
}
