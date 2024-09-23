package vn.gt.tichhop.report.NoticeOfArrival;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempPersonList;
import com.fds.nsw.nghiepvu.model.TempWasteList;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPersonListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempWasteListLocalServiceUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.PageType;
import vn.gt.utils.config.ConfigurationManager;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.exception.SystemException;



@Slf4j
public class Export2NoticeOfArrival {
	
	
	
	private static String THONG_BAO_TAU_DEN_CANG = ConfigurationManager.getStrProp("vn.gt.thong.bao.tau.den.cang", "");
	private static String THONG_BAO_TAU_ROI_CANG = ConfigurationManager.getStrProp("vn.gt.thong.bao.tau.roi.cang", "");
	private static String THONG_BAO_TAU_QUA_CANH = ConfigurationManager.getStrProp("vn.gt.thong.bao.tau.qua.canh", "");
	
	private static String XAC_BAO_TAU_DEN_CANG = ConfigurationManager.getStrProp("vn.gt.xac.bao.tau.den.cang", "");
	private static String XAC_BAO_TAU_ROI_CANG = ConfigurationManager.getStrProp("vn.gt.xac.bao.tau.roi.cang", "");
	private static String XAC_BAO_TAU_QUA_CANH = ConfigurationManager.getStrProp("vn.gt.xac.bao.tau.qua.canh", "");

	
	public long export_NoticeOfArrival(String requestCode, int documentName, int documentYear, String thongBaoOrXacBao, 
			int trangThaiTau, String sLoaiThuTuc)
		throws IOException, SystemException {
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<NoticeOfArrivalModel> dataBeanList = getDataReport(requestCode, documentName, documentYear,
			thongBaoOrXacBao, trangThaiTau, sLoaiThuTuc);
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		
		long exportFunction = 0;
		
		if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_THONG_BAO_TAU_DEN_CANG)
				&& trangThaiTau == MessageType.THONG_BAO_TAU_DEN_CANG) {
			ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
			if (sLoaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG) || sLoaiThuTuc.equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
				
				if (tempDocument.getUpgradeVersion() == 1) {

					exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.NOTICE_OF_DEPARTURE_TEMP, ReportConstant.NOTICE_OF_DEPARTURE_EXPORT, beanColDataSource, parameters);
					
				} else {

					exportFunction = reportUtils.exportFunction(ReportConstant.INLAND_NOTICE_OF_ARRIVAL_TEMP, ReportConstant.NOTICE_OF_ARRIVAL_EXPORT, beanColDataSource, parameters);
					
				}

			}else {
				if (tempDocument.getUpgradeVersion() == 1) {

					exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.NOTICE_OF_DEPARTURE_TEMP, ReportConstant.NOTICE_OF_DEPARTURE_EXPORT, beanColDataSource, parameters);
					
				} else {

					exportFunction = reportUtils.exportFunction(ReportConstant.NOTICE_OF_ARRIVAL_TEMP, ReportConstant.NOTICE_OF_ARRIVAL_EXPORT, beanColDataSource, parameters);
					
				}
			}
				return exportFunction;
			
		} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_THONG_BAO_TAU_DEN_CANG)
					&& trangThaiTau == MessageType.THONG_BAO_TAU_ROI_CANG) {
			ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
			if (sLoaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG) || sLoaiThuTuc.equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
				
				if (tempDocument.getUpgradeVersion() == 1) {

					exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.NOTICE_OF_DEPARTURE_TEMP, ReportConstant.THONG_BAO_TAU_ROI_CANG_EXPORT, beanColDataSource, parameters);
					
				} else {

					exportFunction = reportUtils.exportFunction(ReportConstant.INLAND_NOTICE_OF_ARRIVAL_TEMP, ReportConstant.THONG_BAO_TAU_ROI_CANG_EXPORT, beanColDataSource, parameters);
					
				}
				
			} else {
				
				if (tempDocument.getUpgradeVersion() == 1) {

					exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.NOTICE_OF_DEPARTURE_TEMP, ReportConstant.THONG_BAO_TAU_ROI_CANG_EXPORT, beanColDataSource, parameters);
					
				} else {

					exportFunction = reportUtils.exportFunction(ReportConstant.NOTICE_OF_ARRIVAL_TEMP, ReportConstant.THONG_BAO_TAU_ROI_CANG_EXPORT, beanColDataSource, parameters);
					
				}
				
			}
				return exportFunction;
		
		} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_THONG_BAO_TAU_DEN_CANG)
					&& trangThaiTau == MessageType.THONG_BAO_TAU_QUA_CANH) {
			ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
			
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.NOTICE_OF_DEPARTURE_TEMP, ReportConstant.THONG_BAO_TAU_QUA_CANH_EXPORT, beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.NOTICE_OF_ARRIVAL_TEMP, ReportConstant.THONG_BAO_TAU_QUA_CANH_EXPORT, beanColDataSource, parameters);
				
			}
			
			return exportFunction;
		
		} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_XAC_BAO_TAU_DEN_CANG)
					&& trangThaiTau == MessageType.XAC_BAO_TAU_DEN_CANG) {
			ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
			
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.XACBAO_NOTICE_OF_ARRIVAL_TEMP,
						ReportConstant.XACBAO_NOTICE_OF_ARRIVAL_EXPORT, beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.XACBAO_NOTICE_OF_ARRIVAL_TEMP,
						ReportConstant.XACBAO_NOTICE_OF_ARRIVAL_EXPORT, beanColDataSource, parameters);
				
			}
			
			return exportFunction;
		
		} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_XAC_BAO_TAU_DEN_CANG) &&
					trangThaiTau == MessageType.XAC_BAO_TAU_ROI_CANG) {
			ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
			
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.XACBAO_NOTICE_OF_ARRIVAL_TEMP,
						ReportConstant.XAC_BAO_TAU_ROI_CANG_EXPORT, beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.XACBAO_NOTICE_OF_ARRIVAL_TEMP,
						ReportConstant.XAC_BAO_TAU_ROI_CANG_EXPORT, beanColDataSource, parameters);
				
			}
			
			return exportFunction;
		
		} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_XAC_BAO_TAU_DEN_CANG) &&
					trangThaiTau == MessageType.XAC_BAO_TAU_QUA_CANH) {
			ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
			
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.XACBAO_NOTICE_OF_ARRIVAL_TEMP,
						ReportConstant.XAC_BAO_TAU_QUA_CANH_EXPORT, beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.XACBAO_NOTICE_OF_ARRIVAL_TEMP,
						ReportConstant.XAC_BAO_TAU_QUA_CANH_EXPORT, beanColDataSource, parameters);
				
			}
			
			return exportFunction;
					
		
		}
		return exportFunction;
	}
	
	public static ArrayList<NoticeOfArrivalModel> getDataReport(String requestCode, int documentName, int documentYear,
			String thongBaoOrXacBao, int trangThaiTau, String sLoaiThuTuc) {
		
		ArrayList<NoticeOfArrivalModel> dataBeanList = new ArrayList<NoticeOfArrivalModel>();
		dataBeanList.add(getModel(requestCode, documentName, documentYear, thongBaoOrXacBao, trangThaiTau, sLoaiThuTuc));
		return dataBeanList;
	}
	
	public static NoticeOfArrivalModel getModel(String requestCode, int documentName, int documentYear,
		String thongBaoOrXacBao, int trangThaiTau, String sLoaiThuTuc) {
		
		NoticeOfArrivalModel model = new NoticeOfArrivalModel();
		List<TempNoticeShipMessage> noticeShipMegs = null;
		
		String signDate = null;
		String signPlace = null;
		
		try {
			if (requestCode != null && requestCode.trim().length() > 0 ) {
				noticeShipMegs = TempNoTiceShipMessageLocalServiceUtil.findByRequestCode(requestCode);
				if (Validator.isNotNull(noticeShipMegs) && noticeShipMegs.size() > 0) {
					TempNoticeShipMessage message = noticeShipMegs.get(0);
					if (Validator.isNotNull(message.getSignDate())) { signDate = ReportFunction.parserDateToString4(message.getSignDate()); }
					if (Validator.isNotNull(message.getSignPlace())) { signPlace = message.getSignPlace(); }
				}
			} else {
				
				if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_THONG_BAO_TAU_DEN_CANG)) {
					noticeShipMegs = TempNoTiceShipMessageLocalServiceUtil.findBydocumentNameAndDocumentYearAndNoticeShipType(documentName, documentYear, PageType.TYPE_THONG_BAO_TAU_DEN_CANG);
					if (Validator.isNotNull(noticeShipMegs) && noticeShipMegs.size() > 0) {
						TempNoticeShipMessage message = noticeShipMegs.get(0);
						if (Validator.isNotNull(message.getSignDate())) { signDate = ReportFunction.parserDateToString4(message.getSignDate()); }
						if (Validator.isNotNull(message.getSignPlace())) { signPlace = message.getSignPlace(); }
					}
				} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_XAC_BAO_TAU_DEN_CANG)) {
					noticeShipMegs = TempNoTiceShipMessageLocalServiceUtil.findBydocumentNameAndDocumentYearAndNoticeShipType(documentName, documentYear, PageType.TYPE_XAC_BAO_TAU_DEN_CANG);
					if (Validator.isNotNull(noticeShipMegs) && noticeShipMegs.size() > 0) {
						TempNoticeShipMessage message = noticeShipMegs.get(0);
						if (Validator.isNotNull(message.getSignDate())) { signDate = ReportFunction.parserDateToString4(message.getSignDate()); }
						if (Validator.isNotNull(message.getSignPlace())) { signPlace = message.getSignPlace(); }
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		int iLoaiThuTuc = ReportFunction.iLoaiThuTuc(sLoaiThuTuc);
		for (TempNoticeShipMessage temp : noticeShipMegs) {
			temp.setId(Long.valueOf(iLoaiThuTuc));
		}

		model.setId(iLoaiThuTuc);
		model.setTempNoTiceShipMessage(noticeShipMegs);
		model.setSignDate(signDate);
		model.setSignPlace(signPlace);
		
		if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_THONG_BAO_TAU_DEN_CANG) && trangThaiTau == MessageType.THONG_BAO_TAU_DEN_CANG) {
			model.setMessage(THONG_BAO_TAU_DEN_CANG);
			
		} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_THONG_BAO_TAU_DEN_CANG) && trangThaiTau == MessageType.THONG_BAO_TAU_ROI_CANG) {
			model.setMessage(THONG_BAO_TAU_ROI_CANG);
		
		} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_THONG_BAO_TAU_DEN_CANG) && trangThaiTau == MessageType.THONG_BAO_TAU_QUA_CANH) {
			model.setMessage(THONG_BAO_TAU_QUA_CANH);
		
		} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_XAC_BAO_TAU_DEN_CANG) && trangThaiTau == MessageType.XAC_BAO_TAU_DEN_CANG) {
			model.setMessage(XAC_BAO_TAU_DEN_CANG);
		
		} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_XAC_BAO_TAU_DEN_CANG) && trangThaiTau == MessageType.XAC_BAO_TAU_ROI_CANG) {
			model.setMessage(XAC_BAO_TAU_ROI_CANG);
		
		} else if (thongBaoOrXacBao.equalsIgnoreCase(PageType.TYPE_XAC_BAO_TAU_DEN_CANG) && trangThaiTau == MessageType.XAC_BAO_TAU_QUA_CANH) {
			model.setMessage(XAC_BAO_TAU_QUA_CANH);
		
		}
		
		// personList
		List<TempPersonList> listPersonList = new ArrayList<TempPersonList>();
		int countIllness = 0;
		int countDead = 0;
		int countRescued = 0;
		int countStow = 0;
		
		try {
			
			listPersonList = TempPersonListLocalServiceUtil.findBydocumentNameAnddocumentYearAndRequestCode(documentName, documentYear, requestCode);
			
			for (TempPersonList tempPersonList : listPersonList) {
				if (tempPersonList.getPersonType() == 1) {
					countIllness = countIllness + 1;
				} else if (tempPersonList.getPersonType() == 2) {
					countDead = countDead + 1;
				} else if (tempPersonList.getPersonType() == 3) {
					countRescued = countRescued + 1;
				} else if (tempPersonList.getPersonType() == 4) {
					countStow = countStow + 1;
				}
			}
			
		} catch (Exception e) {
		}
		model.setTempPersonLists(listPersonList);
		model.setCountIllness(countIllness);
		model.setCountDead(countDead);
		model.setCountRescued(countRescued);
		model.setCountStow(countStow);

		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
		
		return model;
	}
}
