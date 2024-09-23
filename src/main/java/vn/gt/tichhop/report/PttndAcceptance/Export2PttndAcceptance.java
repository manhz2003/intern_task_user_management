package vn.gt.tichhop.report.PttndAcceptance;

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
import com.fds.nsw.nghiepvu.model.IssueAcceptance;
import com.fds.nsw.nghiepvu.model.TempDocument;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.noticeandmessage.service.IssueAcceptanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.tichhop.message.TrangThaiBanKhaiXuatCanh;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
@Slf4j
public class Export2PttndAcceptance {
	
	public long export2IssueAcceptance(String requestCode, int documentName, int documentYear, String loaiThuTuc) throws IOException, SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<IssueAcceptanceModel> dataBeanList = getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
		
		if (tempDocument.getUpgradeVersion() == 1) {

			exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.PTTND_ACCEPTANCE, ReportConstant.PTTND_ACCEPTANCE_EXPORT,
					beanColDataSource, parameters);
			
		} else {

			exportFunction = reportUtils.exportFunction(ReportConstant.PTTND_ACCEPTANCE, ReportConstant.PTTND_ACCEPTANCE_EXPORT,
					beanColDataSource, parameters);
			
		}
			 
		return exportFunction;
		
	}
	
	public static ArrayList<IssueAcceptanceModel> getDataReport(String requestCode, int documentName, int documentYear) {
		
		ArrayList<IssueAcceptanceModel> dataBeanList = new ArrayList<IssueAcceptanceModel>();
		
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}
	
	public static IssueAcceptanceModel getModel(String requestCode, int documentName, int documentYear) {
		IssueAcceptanceModel model = new IssueAcceptanceModel();
		IssueAcceptance issueAcceptance = null;
		List<IssueAcceptance> issueObjs = new ArrayList<IssueAcceptance>();
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				issueAcceptance = IssueAcceptanceLocalServiceUtil.findIssueAcceptanceByRequestCode(requestCode);
				if  (Validator.isNotNull(issueAcceptance))
				{
					issueObjs.add(issueAcceptance);
				}
				
			} else {
				issueObjs = IssueAcceptanceLocalServiceUtil.
					findIssueAcceptanceByDocumentYearAndDocumentYearAndRequestState(
						documentName, documentYear, TrangThaiBanKhaiXuatCanh.KHAI_MOI);
			}
		} catch (Exception e) {
		}
		
		log.info("--BEGIN EXPORT ACTION --IssueAcceptanceModel----requestCode=="+requestCode);
		log.info("----issueObjs size: "+issueObjs.size());
		
		String maritimeNameVN = null;
		String maritimeName = null;
		String arrivalPortName = null;
		
		if (Validator.isNotNull(issueObjs) && issueObjs.size() > 0) {
			IssueAcceptance item = issueObjs.get(0);
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
			model.setDwt(item.getDwt());
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
			model.setDirectorOfMaritime(item.getDirectorOfMaritime());
			
			model.setPortWharfCode(item.getPortWharfCode());
			model.setPortHarbourCode(item.getPortHarbourCode());
			model.setPortCode(item.getPortCode());
		}
		
		
		//du lieu lay tu, Temp_Document.getPortCode
				TempDocument tempDoc = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
				if (Validator.isNull(tempDoc)) { 
					arrivalPortName = tempDoc.getPortCode(); 
					}
				model.setArrivalPortName(arrivalPortName); 
				model.setMaritimeName(maritimeName);
				model.setMaritimeNameVN(maritimeNameVN);
				
				log.info("----maritimeName: "+maritimeName);
				log.info("----maritimeNameVN: "+maritimeNameVN);
				log.info("----ArrivalPortName: "+arrivalPortName);
		return model;
	}
}
