package vn.gt.tichhop.report.ShiftingOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.flex.common.ultility.Validator;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.kernel.exception.SystemException;





import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPortWharfException;

import lombok.extern.slf4j.Slf4j;

import com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderChanelLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.GetNameFunction;
@Slf4j
public class Export2ShiftingOrder {
	
	
	
	public long export2ShiftingOrder(String requestCode, int documentName, int documentYear, String loaiThuTuc) throws IOException, SystemException {
		long exportFunction = 0;
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		ArrayList<ShiftingOrderModel> dataBeanList = getDataReport(requestCode, documentName, documentYear);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		
		Map parameters = new HashMap();
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();

		if (loaiThuTuc.equals(MessageType.LOAT_THU_TUC_VAO_CANG) || loaiThuTuc.equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.SHIFTING_ORDER_TEMP, ReportConstant.SHIFTING_ORDER_EXPORT, beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.INLAND_SHIFTING_ORDER_TEMP, ReportConstant.SHIFTING_ORDER_EXPORT, beanColDataSource, parameters);
				
			}
			
		}else {
			if (tempDocument.getUpgradeVersion() == 1) {

				exportFunction = reportUtils.exportFunctionUpgrade(ReportConstant.SHIFTING_ORDER_TEMP, ReportConstant.SHIFTING_ORDER_EXPORT, beanColDataSource, parameters);
				
			} else {

				exportFunction = reportUtils.exportFunction(ReportConstant.SHIFTING_ORDER_TEMP, ReportConstant.SHIFTING_ORDER_EXPORT, beanColDataSource, parameters);
				
			}
			
		}
		return exportFunction;
		
	}
	
	public static ArrayList<ShiftingOrderModel> getDataReport(String requestCode, int documentName, int documentYear) {
		ArrayList<ShiftingOrderModel> dataBeanList = new ArrayList<ShiftingOrderModel>();
		dataBeanList.add(getModel(requestCode, documentName, documentYear));
		return dataBeanList;
	}
	
	public static ShiftingOrderModel getModel(String requestCode, int documentName, int documentYear) {
		
		ShiftingOrderModel model = new ShiftingOrderModel();
		
		List<IssueShiftingOrder> issueObjs = null;
		try {
			if (requestCode != null && requestCode.trim().length() > 0) {
				issueObjs = IssueShiftingOrderLocalServiceUtil.findByRequestCode(requestCode);
			} else {
				issueObjs = IssueShiftingOrderLocalServiceUtil.findIssueShiftingOrderByDocumentYearAndDocumentYear(documentName, documentYear);
			}
		} catch (Exception e) {
		}
		
		
		log.info("issueObjs---: " +issueObjs);

		String maritimeNameVN = null;
		String maritimeName = null;
		
		if (Validator.isNotNull(issueObjs) && issueObjs.size() > 0) {
			IssueShiftingOrder item = issueObjs.get(0);
			if (Validator.isNotNull(item.getPortofAuthority())) {
				DmMaritime maritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(item.getPortofAuthority());
				if (Validator.isNotNull(maritime)) {
					if (Validator.isNotNull(maritime.getMaritimeNameVN())) {
						maritimeNameVN = maritime.getMaritimeNameVN();
					}
					maritimeName = maritime.getMaritimeName();
				}
			}
			
			log.info(" item.getAnchoringPortWharfCode()---" +item.getAnchoringPortWharfCode());
			try {
				log.info(" item.getAnchoringPortWharfCode() ---:"+ GetNameFunction.getPortWharfNameVN(new Date(), item.getAnchoringPortWharfCode()));
				//System.out.println(" Name ---:"+ GetNameFunction.getPortNameInland(new Date(), item.getAnchoringPortCode()));
			} catch (NoSuchDmHistoryPortWharfException e) {
				log.error(e.getMessage());
			} catch (SystemException e) {
				log.error(e.getMessage());
			}
			
			model.setId(item.getId());
			model.setNameOfShip(item.getNameOfShip());
			model.setPortofAuthority(item.getPortofAuthority());
			model.setAnchoringPortCode(item.getAnchoringPortCode());
			model.setPortHarbourCode(item.getPortHarbourCode());
			model.setShiftingDate(item.getShiftingDate());
			model.setReasonToShift(item.getReasonToShift());
			model.setCertificateNo(item.getCertificateNo());
			model.setIssueDate(item.getIssueDate());
			model.setRepresentative(item.getRepresentative());
			model.setSignName(item.getSignName());
			model.setSignTitle(item.getSignTitle());
			model.setRequestCode(item.getRequestCode());
			model.setAnchoringPortWharfCode(item.getAnchoringPortWharfCode());
			model.setShiftingPortWharfCode(item.getShiftingPortWharfCode());
			
			model.setShownDraftxF(item.getShownDraftxF());
			model.setUnitShownDraftxF(item.getUnitShownDraftxF());
			model.setShownDraftxA(item.getShownDraftxA());
			model.setUnitShownDraftxA(item.getUnitShownDraftxA());
			model.setLoa(item.getLoa());
			model.setLoaunit(item.getLoaunit());
			model.setDwt(item.getDwt());
			model.setDwtunit(item.getDwtunit());
			model.setTugBoat(item.getTugBoat());
			model.setFrom(item.getFrom());
			model.setTo(item.getTo());
			model.setFlagStateOfShip(item.getFlagStateOfShip());
			model.setTaxCodeOfShipownersAgents(item.getTaxCodeOfShipownersAgents());
			model.setNameOfShipownersAgents(item.getNameOfShipownersAgents());
			model.setShipAgencyAddress(item.getShipAgencyAddress());
			model.setShipAgencyPhone(item.getShipAgencyPhone());
			model.setShipAgencyFax(item.getShipAgencyFax());
			model.setShipAgencyEmail(item.getShipAgencyEmail());
			
			List<IssueShiftingOrderChanel> chanels = new ArrayList<IssueShiftingOrderChanel>(); 
			
			try {
				chanels = IssueShiftingOrderChanelLocalServiceUtil.getShiftingOrderChanel(model.getId());
			} catch(Exception e) {
				log.error(e.getMessage());
			}
			
			String chanelStr = StringPool.BLANK;
			
			for(IssueShiftingOrderChanel chanelModel : chanels) {
				chanelStr +=  ", " + chanelModel.getChanelName();
			}
			model.setChanel(chanelStr.replaceFirst(", ", StringPool.BLANK));
		}
		model.setMaritimeName(maritimeName);
		model.setMaritimeNameVN(maritimeNameVN);
		model.setDocumentName(documentName);
		model.setDocumentYear(documentYear);
		
		TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		
		String portRegionCode = tempDocument.getPortRegionCode();
		
		model.setPortRegionCode(portRegionCode);

		model.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
		
		return model;
	}
}
