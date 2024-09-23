package vn.gt.portlet.baocao.thongke.dvcong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc;
import vn.gt.dao.noticeandmessage.service.ViewHoanThanhThuTucLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.BaoCaoConstant;

import com.fds.nsw.kernel.exception.SystemException;


import com.fds.flex.common.ultility.Validator;

public class HoanThanhThuTucMotCuaQuocGiaExport {
	
	private String realPath = this.getClass().getProtectionDomain()
			.getCodeSource().getLocation().toString();
	private String pathFileTemp = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/report/baocao/";
	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/export/";
	
	
	
	public static CoCheMotCuaQuocGiaNangCapModel getModel(String reportDate, String maritimeCode, String dateFrom, String dateTo) {
		
		CoCheMotCuaQuocGiaNangCapModel result = new CoCheMotCuaQuocGiaNangCapModel();
		DmHistoryMaritime historyMaritime = null;

		historyMaritime = DmHistoryMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode);
		if (historyMaritime != null) {
			result.setMaritimeNameVN(historyMaritime.getMaritimeNameVN());
			result.setMaritimeName(historyMaritime.getMaritimeName());
			result.setReportDate(historyMaritime.getCityCode() + ", ");
		}
		
		System.out.println("HoanThanhThuTucMotCuaQuocGiaExport.getModel(historyMaritime)" + historyMaritime);
		
		result.setStartDate(dateFrom.substring(0, 10));
		result.setToDate(dateTo.substring(0, 10));
		result.setMonthOfPeriod(dateTo.substring(3, 5));
		result.setYearOfPeriod(dateTo.substring(6, 10));		
		result.setReportDate(result.getReportDate() + "ng\u00E0y " + reportDate.substring(0, 2) + " th\u00E1ng " + reportDate.substring(3, 5) + " n\u0103m "
				+ reportDate.substring(6, 10));
		List<ViewHoanThanhThuTuc> viewHoanThanhThuTuc = null;
		viewHoanThanhThuTuc = ViewHoanThanhThuTucLocalServiceUtil.findByKetQuaHoanThanhThuTuc(maritimeCode, dateFrom, dateTo);
		if (viewHoanThanhThuTuc != null && viewHoanThanhThuTuc.size() > 0){
			result.setViewHoanThanhThuTuc(viewHoanThanhThuTuc);
		}
		
		System.out.println("HoanThanhThuTucMotCuaQuocGiaExport.getModel(dateFrom)" + dateFrom);
		System.out.println("HoanThanhThuTucMotCuaQuocGiaExport.getModel(dateTo)" + dateTo);
		System.out.println("HoanThanhThuTucMotCuaQuocGiaExport.getModel(viewHoanThanhThuTuc)" + viewHoanThanhThuTuc);
		
		return result;
	}
	
	public boolean export2Report(String reportDate, String maritimeCode, String dateFrom, String dateTo) throws IOException,
			SystemException {
		
		ArrayList<CoCheMotCuaQuocGiaNangCapModel> dataBeanList = getDataReport(reportDate, maritimeCode, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		
		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunctionModuleBaoCao(BaoCaoConstant.BAO_CAO_KET_QUA_HOAN_THANH_THU_TUC_XML, BaoCaoConstant.BAO_CAO_KET_QUA_HOAN_THANH_THU_TUC_EXPORT,
				beanColDataSource, parameters);
		
		return true;
	}
	
	public static ArrayList<CoCheMotCuaQuocGiaNangCapModel> getDataReport(String reportDate, String maritimeCode, String dateFrom, String dateTo) {
		ArrayList<CoCheMotCuaQuocGiaNangCapModel> dataBeanList = new ArrayList<CoCheMotCuaQuocGiaNangCapModel>();
		
		dataBeanList.add(getModel(reportDate, maritimeCode, dateFrom, dateTo));
		return dataBeanList;
	}
	
	public boolean export2Excel(String maritimeCode, String createDate, String fromDate, String toDate, String fileName) throws ParsePropertyException, InvalidFormatException, IOException{
		boolean result = false;
		ArrayList<CoCheMotCuaQuocGiaNangCapModel> dataBeanList = getDataReport(
				createDate, maritimeCode, fromDate, toDate);
		if(!dataBeanList.isEmpty() && Validator.isNotNull(dataBeanList)){
			List<Map> listHTTT = new ArrayList<Map>();
			Map dataBeans = new HashMap();
			int stt = 1;
			int sumnckyso = 0, sumxckyso = 0, sumqckyso = 0, sumvckyso = 0, sumrckyso = 0, sumccvkyso = 0, sumccrkyso = 0, sumvcdkkyso = 0, sumrcdkkyso = 0, sumncdkkyso = 0, sumxcdkkyso = 0,
				sumvctndkyso = 0, sumrctndkyso = 0, sumncduyet = 0, sumxcduyet = 0, sumqcduyet = 0, sumvcduyet = 0, sumrcduyet = 0, sumccvduyet = 0, sumccrduyet = 0, sumvcdkduyet = 0,
				sumrcdkduyet = 0, sumncdkduyet = 0, sumxcdkduyet = 0, sumvctndduyet = 0, sumrctndduyet = 0, sumncpttndduyet = 0, sumxcpttndduyet  = 0, sumncpttndkyso = 0, sumxcpttndkyso = 0, sumndkyso = 0,
				sumndkyduyet = 0, sumxnckyso = 0, sumxnckyduyet = 0, sumnd = 0, sumxnc = 0;
			
			for (CoCheMotCuaQuocGiaNangCapModel coCheMotCuaQuocGiaNangCapModel : dataBeanList) {
				for (ViewHoanThanhThuTuc viewHoanThanhThuTuc : coCheMotCuaQuocGiaNangCapModel
						.getViewHoanThanhThuTuc()) {
					listHTTT.add(setViewHoanThanhThuTucModel(viewHoanThanhThuTuc,
							stt, viewHoanThanhThuTuc.getCvhh()));
					stt++;
					sumnckyso += Integer.parseInt(viewHoanThanhThuTuc.getNC_KYSO());
					sumxckyso += Integer.parseInt(viewHoanThanhThuTuc.getXC_KYSO());
					sumqckyso += Integer.parseInt(viewHoanThanhThuTuc.getQC_KYSO());
					sumvckyso += Integer.parseInt(viewHoanThanhThuTuc.getVC_KYSO());
					sumrckyso += Integer.parseInt(viewHoanThanhThuTuc.getRC_KYSO());
					sumccvkyso += Integer.parseInt(viewHoanThanhThuTuc.getCCV_KYSO());
					sumccrkyso += Integer.parseInt(viewHoanThanhThuTuc.getCCR_KYSO());
					sumvcdkkyso += Integer.parseInt(viewHoanThanhThuTuc.getVCDK_KYSO());
					sumrcdkkyso += Integer.parseInt(viewHoanThanhThuTuc.getRCDK_KYSO());
					sumncdkkyso += Integer.parseInt(viewHoanThanhThuTuc.getNCDK_KYSO());
					sumxcdkkyso += Integer.parseInt(viewHoanThanhThuTuc.getXCDK_KYSO());
					sumvctndkyso += Integer.parseInt(viewHoanThanhThuTuc.getVCTND_KYSO());
					sumrctndkyso += Integer.parseInt(viewHoanThanhThuTuc.getRCTND_KYSO());
					sumncduyet += Integer.parseInt(viewHoanThanhThuTuc.getNC_DUYET());
					sumxcduyet += Integer.parseInt(viewHoanThanhThuTuc.getXC_DUYET());
					sumqcduyet += Integer.parseInt(viewHoanThanhThuTuc.getQC_DUYET());
					sumvcduyet += Integer.parseInt(viewHoanThanhThuTuc.getVC_DUYET());
					sumrcduyet += Integer.parseInt(viewHoanThanhThuTuc.getCCV_DUYET());
					sumccrduyet += Integer.parseInt(viewHoanThanhThuTuc.getCCR_DUYET());
					sumvcdkduyet += Integer.parseInt(viewHoanThanhThuTuc.getVCDK_DUYET());
					sumrcdkduyet += Integer.parseInt(viewHoanThanhThuTuc.getRCDK_DUYET());
					sumncdkduyet += Integer.parseInt(viewHoanThanhThuTuc.getNCDK_DUYET());
					sumxcdkduyet += Integer.parseInt(viewHoanThanhThuTuc.getXCDK_DUYET());
					sumvctndduyet += Integer.parseInt(viewHoanThanhThuTuc.getVCTND_DUYET());
					sumrctndduyet += Integer.parseInt(viewHoanThanhThuTuc.getRCTND_DUYET());
					sumncpttndduyet += Integer.parseInt(viewHoanThanhThuTuc.getNCPTTND_DUYET());
					sumxcpttndduyet += Integer.parseInt(viewHoanThanhThuTuc.getXCPTTND_DUYET());
					sumncpttndkyso += Integer.parseInt(viewHoanThanhThuTuc.getNCPTTND_KYSO());
					sumxcpttndkyso += Integer.parseInt(viewHoanThanhThuTuc.getXCPTTND_KYSO());
				}
			}
			sumndkyso = sumrckyso + sumvckyso;
			sumndkyduyet = sumvcduyet + sumrcduyet;
			sumxnckyso = sumnckyso + sumxckyso + sumqckyso;
			sumxnckyduyet = sumncduyet + sumxcduyet + sumqcduyet;
			sumnd = sumndkyso + sumndkyduyet;
			sumxnc = sumxnckyduyet + sumxnckyso;
			
			dataBeans.put("HTTT", listHTTT);
			dataBeans.put("createDate", dataBeanList.get(0).getReportDate());
			dataBeans.put("SUM_NC_KYSO", sumnckyso);
			dataBeans.put("SUM_XC_KYSO", sumxckyso);
			dataBeans.put("SUM_QC_KYSO", sumqckyso);
			dataBeans.put("SUM_VC_KYSO", sumvckyso);
			dataBeans.put("SUM_RC_KYSO", sumrckyso);
			dataBeans.put("SUM_CCV_KYSO", sumccvkyso);
			dataBeans.put("SUM_CCR_KYSO", sumccrkyso);
			dataBeans.put("SUM_VCDK_KYSO", sumvcdkkyso);
			dataBeans.put("SUM_RCDK_KYSO", sumrcdkkyso);
			dataBeans.put("SUM_NCDK_KYSO", sumncdkkyso);
			dataBeans.put("SUM_XCDK_KYSO", sumxcdkkyso);
			dataBeans.put("SUM_VCTND_KYSO", sumvctndkyso);
			dataBeans.put("SUM_RCTND_KYSO", sumrctndkyso);
			dataBeans.put("SUM_NC_DUYET", sumncduyet);
			dataBeans.put("SUM_XC_DUYET", sumxcduyet);
			dataBeans.put("SUM_QC_DUYET", sumqcduyet);
			dataBeans.put("SUM_VC_DUYET", sumvcduyet);
			dataBeans.put("SUM_RC_DUYET", sumrcduyet);
			dataBeans.put("SUM_CCV_DUYET", sumccvduyet);
			dataBeans.put("SUM_CCR_DUYET", sumccrduyet);
			dataBeans.put("SUM_VCDK_DUYET", sumvcdkduyet);
			dataBeans.put("SUM_RCDK_DUYET", sumrcdkduyet);
			dataBeans.put("SUM_NCDK_DUYET", sumncdkduyet);
			dataBeans.put("SUM_XCDK_DUYET", sumxcdkduyet);
			dataBeans.put("SUM_VCTND_DUYET", sumvctndduyet);
			dataBeans.put("SUM_RCTND_DUYET", sumrctndduyet);
			dataBeans.put("SUM_NCPTTND_DUYET", sumncpttndduyet);
			dataBeans.put("SUM_XCPTTND_DUYET", sumxcpttndduyet);
			dataBeans.put("SUM_NCPTTND_KYSO", sumncpttndkyso);
			dataBeans.put("SUM_XCPTTND_KYSO", sumxcpttndkyso);
			dataBeans.put("SUM_ND_KYSO", sumndkyso);
			dataBeans.put("SUM_ND_KYDUYET", sumndkyduyet);
			dataBeans.put("SUM_XNC_KYSO", sumxnckyso);
			dataBeans.put("SUM_XNC_KYDUYET", sumxnckyduyet);
			dataBeans.put("SUM_ND", sumnd);
			dataBeans.put("SUM_XNC", sumxnc);
			
			XLSTransformer transformer = new XLSTransformer();
			transformer.transformXLS(pathFileTemp + "BaoCaoKetQuaHoanThanhThuTucTemplate.xls",
					dataBeans, pathFileSub + fileName);
			
			result = true;
		}
		return result;
	}

	private Map setViewHoanThanhThuTucModel(ViewHoanThanhThuTuc viewHoanThanhThuTuc, int stt, String cvhh){
		if(viewHoanThanhThuTuc != null){
			Map mapViewHoanThanhThuTuc = new HashMap();
			mapViewHoanThanhThuTuc.put("stt", stt);
			mapViewHoanThanhThuTuc.put("NC_KYSO", viewHoanThanhThuTuc.getNC_KYSO());
			mapViewHoanThanhThuTuc.put("XC_KYSO", viewHoanThanhThuTuc.getXC_KYSO());
			mapViewHoanThanhThuTuc.put("QC_KYSO", viewHoanThanhThuTuc.getQC_KYSO());
			mapViewHoanThanhThuTuc.put("VC_KYSO", viewHoanThanhThuTuc.getVC_KYSO());
			mapViewHoanThanhThuTuc.put("RC_KYSO", viewHoanThanhThuTuc.getRC_KYSO());
			mapViewHoanThanhThuTuc.put("CCV_KYSO", viewHoanThanhThuTuc.getCCV_KYSO());
			mapViewHoanThanhThuTuc.put("CCR_KYSO", viewHoanThanhThuTuc.getCCR_KYSO());
			mapViewHoanThanhThuTuc.put("VCDK_KYSO", viewHoanThanhThuTuc.getVCDK_KYSO());
			mapViewHoanThanhThuTuc.put("RCDK_KYSO", viewHoanThanhThuTuc.getRCDK_KYSO());
			mapViewHoanThanhThuTuc.put("NCDK_KYSO", viewHoanThanhThuTuc.getNCDK_KYSO());
			mapViewHoanThanhThuTuc.put("XCDK_KYSO", viewHoanThanhThuTuc.getXCDK_KYSO());
			mapViewHoanThanhThuTuc.put("VCTND_KYSO", viewHoanThanhThuTuc.getVCTND_KYSO());
			mapViewHoanThanhThuTuc.put("RCTND_KYSO", viewHoanThanhThuTuc.getRCTND_KYSO());
			mapViewHoanThanhThuTuc.put("NC_DUYET", viewHoanThanhThuTuc.getNC_DUYET());
			mapViewHoanThanhThuTuc.put("XC_DUYET", viewHoanThanhThuTuc.getXC_DUYET());
			mapViewHoanThanhThuTuc.put("QC_DUYET", viewHoanThanhThuTuc.getQC_DUYET());
			mapViewHoanThanhThuTuc.put("VC_DUYET", viewHoanThanhThuTuc.getVC_DUYET());
			mapViewHoanThanhThuTuc.put("RC_DUYET", viewHoanThanhThuTuc.getRC_DUYET());
			mapViewHoanThanhThuTuc.put("CCV_DUYET", viewHoanThanhThuTuc.getCCV_DUYET());
			mapViewHoanThanhThuTuc.put("CCR_DUYET", viewHoanThanhThuTuc.getCCR_DUYET());
			mapViewHoanThanhThuTuc.put("VCDK_DUYET", viewHoanThanhThuTuc.getVCDK_DUYET());
			mapViewHoanThanhThuTuc.put("RCDK_DUYET", viewHoanThanhThuTuc.getRCDK_DUYET());
			mapViewHoanThanhThuTuc.put("NCDK_DUYET", viewHoanThanhThuTuc.getNCDK_DUYET());
			mapViewHoanThanhThuTuc.put("XCDK_DUYET", viewHoanThanhThuTuc.getXCDK_DUYET());
			mapViewHoanThanhThuTuc.put("VCTND_DUYET", viewHoanThanhThuTuc.getVCTND_DUYET());
			mapViewHoanThanhThuTuc.put("RCTND_DUYET", viewHoanThanhThuTuc.getRCTND_DUYET());
			mapViewHoanThanhThuTuc.put("NCPTTND_DUYET", viewHoanThanhThuTuc.getNCPTTND_DUYET());
			mapViewHoanThanhThuTuc.put("XCPTTND_DUYET", viewHoanThanhThuTuc.getXCPTTND_DUYET());
			mapViewHoanThanhThuTuc.put("NCPTTND_KYSO", viewHoanThanhThuTuc.getNCPTTND_KYSO());
			mapViewHoanThanhThuTuc.put("XCPTTND_KYSO", viewHoanThanhThuTuc.getXCPTTND_KYSO());
			mapViewHoanThanhThuTuc.put("CVHH", cvhh);
			return mapViewHoanThanhThuTuc;
		}
		return null;
	}
}
