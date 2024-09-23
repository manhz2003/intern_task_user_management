package vn.gt.portlet.baocao.thongke.bc13;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.BaoCaoConstant;

import com.fds.nsw.kernel.exception.SystemException;



public class Bc13Export {
	
	
	
	public static Bc13Model getModel(String reportDate, String maritimeCode, String dateFrom, String dateTo) {
		
		Bc13Model result = new Bc13Model();
		
		return result;
	}
	
	public boolean export2Report(String reportDate, String maritimeCode, String dateFrom, String dateTo) throws IOException,
			SystemException {
		
		ArrayList<Bc13Model> dataBeanList = getDataReport(reportDate, maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		
		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunctionModuleBaoCao(BaoCaoConstant.BAO_CAO_13_XML, BaoCaoConstant.BAO_CAO_13_EXPORT,
				beanColDataSource, parameters);
		
		return true;
	}
	
	public static ArrayList<Bc13Model> getDataReport(String reportDate, String maritimeCode, String dateFrom, String dateTo) {
		ArrayList<Bc13Model> dataBeanList = new ArrayList<Bc13Model>();
		
		dataBeanList.add(getModel(reportDate, maritimeCode, dateFrom, dateTo));
		return dataBeanList;
	}
	
}
