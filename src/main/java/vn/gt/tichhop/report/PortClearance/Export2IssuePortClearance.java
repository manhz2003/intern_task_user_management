package vn.gt.tichhop.report.PortClearance;

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
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.TempDocument;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiBanKhaiXuatCanh;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
@Slf4j
public class Export2IssuePortClearance {
	
	public long export2PortClearance(String requestCode, int documentName, int documentYear, String loaiThuTuc) throws IOException, SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<IssuePortClearanceModel> dataBeanList = getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();

		//System.out.println("loaiThuTuc  "+loaiThuTuc);
		if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG) || loaiThuTuc.equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.ISSUE_PORT_CLEARANCE_TEMP, ReportConstant.ISSUE_PORT_CLEARANCE_EXPORT,
						beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.INLAND_ISSUE_PORT_CLEARANCE_TEMP, ReportConstant.ISSUE_PORT_CLEARANCE_EXPORT,
						beanColDataSource, parameters);
				
			}
		 
		} else if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.ISSUE_PORT_CLEARANCE_TEMP, ReportConstant.ISSUE_PORT_CLEARANCE_EXPORT,
						beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.PTTND_PORTCLEARANCE, ReportConstant.PTTND_PORTCLEARANCE_EXPORT,
						beanColDataSource, parameters);
				
			}
			 
		} else if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.PTTND_ACCEPTANCE, ReportConstant.PTTND_ACCEPTANCE_EXPORT,
						beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.PTTND_ACCEPTANCE, ReportConstant.PTTND_ACCEPTANCE_EXPORT,
						beanColDataSource, parameters);
				
			}
			 
		} else {
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.ISSUE_PORT_CLEARANCE_TEMP, ReportConstant.ISSUE_PORT_CLEARANCE_EXPORT,
						beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.ISSUE_PORT_CLEARANCE_TEMP, ReportConstant.ISSUE_PORT_CLEARANCE_EXPORT,
						beanColDataSource, parameters);
				
			}
		}
		return exportFunction;
		
	}
	
	public static ArrayList<IssuePortClearanceModel> getDataReport(String requestCode, int documentName, int documentYear) {
		
		ArrayList<IssuePortClearanceModel> dataBeanList = new ArrayList<IssuePortClearanceModel>();
		
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}
	
	public static IssuePortClearanceModel getModel(String requestCode, int documentName, int documentYear) {
	
		IssuePortClearanceModel model = new IssuePortClearanceModel();
		
		List<IssuePortClearance> issueObjs = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				issueObjs = IssuePortClearanceLocalServiceUtil.findIssuePortClearanceByRequestCode(requestCode);
			} else {
				issueObjs = IssuePortClearanceLocalServiceUtil.
					findIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
						documentName, documentYear, TrangThaiBanKhaiXuatCanh.KHAI_MOI);
			}
		} catch (Exception e) {
		}
		
		log.info("--BEGIN EXPORT ACTION --IssuePortClearanceModel----requestCode=="+requestCode);
		log.info("----issueObjs size: "+issueObjs.size());
		
		String maritimeNameVN = null;
		String maritimeName = null;
		
		if (Validator.isNotNull(issueObjs) && issueObjs.size() > 0) {
			IssuePortClearance item = issueObjs.get(0);
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
			
			model.setNameOfShip(item.getNameOfShip());
			model.setFlagStateOfShip(item.getFlagStateOfShip());
			model.setCallSign(item.getCallSign());
			model.setGt(item.getGt());
			model.setNameOfMaster(item.getNameOfMaster());
			model.setNumberOfCrews(item.getNumberOfCrews());
			model.setNumberOfPassengers(item.getNumberOfPassengers());
			
			model.setCargo(item.getCargo());
			model.setCargoUnit(item.getCargoUnit());
			model.setVolumeCargo(item.getVolumeCargo());
			
			model.setCargoDescription(item.getCargoDescription());
			
			model.setVolumeTransitCargo(item.getVolumeTransitCargo());			
			model.setTransitCargoUnit(item.getTransitCargoUnit());
			model.setTransitCargo(item.getTransitCargo());
			
			model.setTimeOfDeparture(item.getTimeOfDeparture());
			model.setNextPortOfCallCode(item.getNextPortOfCallCode());
			model.setValidUntil(item.getValidUntil());
			model.setCertificateNo(item.getCertificateNo());
			model.setIssueDate(item.getIssueDate());
			model.setRepresentative(item.getRepresentative());
			model.setSignName(item.getSignName());
			model.setSignTitle(item.getSignTitle());
			
			// Using Remarks incase of setNextPortOfCallCode contains ZZZ
			model.setRemarks(item.getRemarks().trim());
			
		}
		
		
		model.setMaritimeName(maritimeName);
		model.setMaritimeNameVN(maritimeNameVN);
		log.info("----maritimeName: "+maritimeName);
		log.info("----maritimeNameVN: "+maritimeNameVN);

		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
		
		return model;
	}
}
