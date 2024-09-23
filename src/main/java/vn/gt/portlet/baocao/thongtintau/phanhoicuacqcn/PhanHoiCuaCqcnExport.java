package vn.gt.portlet.baocao.thongtintau.phanhoicuacqcn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMinistry;
import vn.gt.dao.danhmucgt.service.DmMinistryLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultHistoryMinistry;
import com.fds.nsw.nghiepvu.model.ResultMinistry;
import vn.gt.dao.result.service.ResultHistoryMinistryLocalServiceUtil;
import vn.gt.dao.result.service.ResultMinistryLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.BaoCaoConstant;

import com.fds.nsw.kernel.exception.SystemException;


@Slf4j
public class PhanHoiCuaCqcnExport {
	
	
	
	public long export2Report(long documentName, int documentYear, String ministryCode) throws IOException, SystemException {
		
		log.info("====Cho nhan de alter====ministryCodeministryCode===" + ministryCode);
		ArrayList<PhanHoiCuaCqcnModel> dataBeanList = getDataReport(documentName, documentYear, ministryCode);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		
		String ministryName = null;
		if (ministryCode != null && ministryCode.trim() != "") {
			DmMinistry dmMinistry = DmMinistryLocalServiceUtil.findByMinistryCode(ministryCode.trim());
			
			if (dmMinistry != null && dmMinistry.getMinistryNameVN() != null) {
				ministryName = dmMinistry.getMinistryNameVN();
			} else if (dmMinistry != null && dmMinistry.getMinistryName() != null) {
				ministryName = dmMinistry.getMinistryName();
			} else {
				ministryName = ministryCode;
			}
		}
		
		parameters.put("MINISTRY_NAME", ministryName);
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		if (tempDocument != null) {
			parameters.put("DOCUMENT_TYPE", tempDocument.getDocumentTypeCode());
			log.info("====parameters.put(DOCUMENT_TYPE, tempDocument.getDocumentTypeCode())===" + tempDocument.getDocumentTypeCode());
		}
		
		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		return reportUtils.exportFunction(BaoCaoConstant.BAO_CAO_PHAN_HOI_TU_CQCN_XML, BaoCaoConstant.BAO_CAO_PHAN_HOI_TU_CQCN_EXPORT,
				beanColDataSource, parameters);
	}
	
	
	public static ArrayList<PhanHoiCuaCqcnModel> getDataReport(long documentName, int documentYear, String ministryCode) {
		ArrayList<PhanHoiCuaCqcnModel> dataBeanList = new ArrayList<PhanHoiCuaCqcnModel>();
		
		dataBeanList.add(getModel(documentName, documentYear, ministryCode));
		return dataBeanList;
	}
	
	public static PhanHoiCuaCqcnModel getModel(long documentName, int documentYear, String ministryCode) {
		log.debug("==documentName==" + documentName + "==documentYear==" + documentYear + "==ministryCode==" + ministryCode);
		
		PhanHoiCuaCqcnModel result = new PhanHoiCuaCqcnModel();
		
		List<ResultMinistry> lstMinistry = null;
		
		DmHistoryMaritime historyMaritime = null;
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		if (tempDocument != null) {
			historyMaritime = DmHistoryMaritimeLocalServiceUtil.getByMaritimeCode(tempDocument.getMaritimeCode());
			if (historyMaritime != null) {
				result.setMaritimeNameVN(historyMaritime.getMaritimeNameVN());
				result.setMaritimeName(historyMaritime.getMaritimeName());
			}
			result.setShipName(tempDocument.getShipName());
			result.setRequestCode(tempDocument.getRequestCode());
			result.setStateCode(tempDocument.getStateCode());
			result.setCallSign(tempDocument.getCallSign());
			result.setGrt(tempDocument.getGrt());
			result.setShipCaptain(tempDocument.getShipCaptain());
		}
		
		lstMinistry = ResultMinistryLocalServiceUtil.findDocNameAndDocYearAndMinistryCode((int)documentName, documentYear, ministryCode);
		result.setAllResultHistoryMinistrie(lstMinistry);
		return result;
	}
	
}
