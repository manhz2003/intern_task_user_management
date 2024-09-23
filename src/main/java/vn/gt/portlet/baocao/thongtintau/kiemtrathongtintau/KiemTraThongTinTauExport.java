package vn.gt.portlet.baocao.thongtintau.kiemtrathongtintau;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultCertificate;
import vn.gt.dao.result.service.ResultCertificateLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.BaoCaoConstant;

import com.fds.nsw.kernel.exception.SystemException;

public class KiemTraThongTinTauExport {
	
	public static KiemTraThongTinTauModel getModel(long documentName, int documentYear) {
		
		KiemTraThongTinTauModel result = new KiemTraThongTinTauModel();
		boolean hasData = true;
		List<TempDocument> allTempDocument = null;
		List<ResultCertificate> allResultCertificate = null;
		
		DmHistoryMaritime historyMaritime = null;
		TempDocument tempDocument = null;
		tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		if (tempDocument != null) {
			if (allTempDocument == null) {
				allTempDocument = new ArrayList<TempDocument>();
				allTempDocument.add(tempDocument);
			}
			historyMaritime = DmHistoryMaritimeLocalServiceUtil.getByMaritimeCode(tempDocument.getMaritimeCode());
			if (historyMaritime != null) {
				result.setMaritimeNameVN(historyMaritime.getMaritimeNameVN());
				result.setMaritimeName(historyMaritime.getMaritimeName());
			}
		}
		
		allResultCertificate = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
		
		if (allTempDocument == null) {
			hasData = false;
		}
		
		result.setAllTemDocument(allTempDocument);
		result.setAllResultCertificate(allResultCertificate);
		result.setHasData(hasData);
		
		return result;
	}
	
	public void export2Report(long documentName, int documentYear) throws IOException, SystemException {
		
		ArrayList<KiemTraThongTinTauModel> dataBeanList = getDataReport(documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		
		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunctionModuleBaoCao(BaoCaoConstant.BAO_CAO_KIEM_TRA_THONG_TIN_TAU_XML, BaoCaoConstant.BAO_CAO_KIEM_TRA_THONG_TIN_TAU_EXPORT,
				beanColDataSource, parameters);
	}
	
	public static ArrayList<KiemTraThongTinTauModel> getDataReport(long documentName, int documentYear) {
		ArrayList<KiemTraThongTinTauModel> dataBeanList = new ArrayList<KiemTraThongTinTauModel>();
		
		dataBeanList.add(getModel(documentName, documentYear));
		return dataBeanList;
	}
	
}
