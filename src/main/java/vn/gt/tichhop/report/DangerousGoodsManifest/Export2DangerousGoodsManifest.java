package vn.gt.tichhop.report.DangerousGoodsManifest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.SystemException;


import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems;
import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempDangerousGoodsItemsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDangerousGoodsNanifestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;


public class Export2DangerousGoodsManifest {
	
	public long export2DangerousGoodsManifest(String requestCode, int documentName, int documentYear, String loaiThuTuc)
		throws IOException, SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<DangerousGoodsManifestModel> dataBeanList = getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
		if (tempDocument.getUpgradeVersion() == 1) {

			exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.DANGEROUS_GOODS_MANIFEST_TEMP, ReportConstant.DANGEROUS_GOODS_MANIFEST_EXPORT,
					beanColDataSource, parameters);
			
		} else {

			exportFunction = reportUtils.exportFunction(ReportConstant.DANGEROUS_GOODS_MANIFEST_TEMP, ReportConstant.DANGEROUS_GOODS_MANIFEST_EXPORT,
					beanColDataSource, parameters);
			
		}
		return exportFunction;
		
	}
	
	public static ArrayList<DangerousGoodsManifestModel> getDataReport(String requestCode, int documentName, int documentYear) {
		
		ArrayList<DangerousGoodsManifestModel> dataBeanList = new ArrayList<DangerousGoodsManifestModel>();
		
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}
	
	public static DangerousGoodsManifestModel getModel(String requestCode, int documentName, int documentYear) {
	
		DangerousGoodsManifestModel model = new DangerousGoodsManifestModel();
		
		List<TempDangerousGoodsManifest> tempNanifests = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				tempNanifests = TempDangerousGoodsNanifestLocalServiceUtil.findByRequestCode(requestCode);
			} else {
				tempNanifests = TempDangerousGoodsNanifestLocalServiceUtil.findBydocumentNameAnddocumentYear(documentName, documentYear);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//if (Validator.isNull(tempNanifests)) { tempNanifests = new ArrayList<TempDangerousGoodsManifest>(); }
		
		model.setTempDangerousGoodsNanifestsTop(tempNanifests);
		
		TempDangerousGoodsManifest temp = null;
		if (Validator.isNotNull(tempNanifests) && tempNanifests.size() > 0) {
			temp = tempNanifests.get(0);
			model.setAdditionalRemark(temp.getAdditionalRemark());
			model.setMasterSigned(temp.getMasterSigned());
			model.setSignPlace(temp.getSignPlace());
			model.setSignDate(ReportFunction.parserDateToString4(temp.getSignDate()));
		} else {
			model.setAdditionalRemark(null);
			model.setMasterSigned(null);
			model.setSignPlace(null);
			model.setSignDate(null);
		}
		
		List<TempDangerousGoodsItems> tempItemses = null;
		try {
			if (tempNanifests != null && tempNanifests.size() > 0) {
				tempItemses = TempDangerousGoodsItemsLocalServiceUtil.findByRequestCode(tempNanifests.get(0).getRequestCode());
			} else {
				//tempItemses =  new ArrayList<TempDangerousGoodsItems>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setTempDangerousGoodsItemses(tempItemses);

		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
		
		return model;
	}
}
