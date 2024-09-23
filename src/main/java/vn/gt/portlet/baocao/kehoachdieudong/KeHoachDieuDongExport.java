package vn.gt.portlet.baocao.kehoachdieudong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPortWharfException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryStateException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmUnitGeneralException;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.BaoCaoConstant;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.utils.GetNameFunction;

import com.fds.nsw.kernel.exception.SystemException;



public class KeHoachDieuDongExport {

	private String realPath = this.getClass().getProtectionDomain()
			.getCodeSource().getLocation().toString();
	private String pathFileTemp = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/report/baocao/";
	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/export/";

	public static KeHoachDieuDongModel getModel(int reportCode,
			String maritimeCode, String ngayLap, String dateFrom, String dateTo) {

		KeHoachDieuDongModel result = new KeHoachDieuDongModel();

		List<TempNoticeShipMessage> allNoticeShipMessagesArival = null;
		List<TempNoticeShipMessage> allNoticeShipMessagesLeave = null;

		TempNoticeShipMessage tempNoTiceShipMessage = null;
		List<TempDocument> allTmpDoc = null;
		List<ResultDeclaration> allResultDeclaration = null;

		String maritimeName = null;
		TempDocument tempDocument = null;
		ResultDeclaration resultDeclaration = null;
		String createDate = null;
		String createTime = null;
		boolean hasData = false;

		// Chuan bi du lieu tau den

		try {
			if (maritimeCode != null && maritimeCode.trim().length() > 0) {

				maritimeName = GetNameFunction.getMaritimeName(maritimeCode);
				result.setMaritimeName(maritimeName);

				allTmpDoc = TempDocumentLocalServiceUtil
						.findTempDocumentArivalByMaritimeCodeAndDateFromAndDateTo(
								maritimeCode, dateFrom, dateTo, "'NC', 'QC'",
								"16", "10");

				if ((allTmpDoc != null) && (allTmpDoc.size() > 0)) {
					for (int i = 0; i < allTmpDoc.size(); i++) {
						tempDocument = allTmpDoc.get(i);
						allResultDeclaration = ResultDeclarationLocalServiceUtil
								.findResultDeclarationByDocumentNameAndDocumentYearNcQcReport(
										tempDocument.getDocumentName(),
										tempDocument.getDocumentYear());
						if ((allResultDeclaration != null)
								&& (allResultDeclaration.size() > 0)) {
							for (int j = 0; j < allResultDeclaration.size(); j++) {
								resultDeclaration = allResultDeclaration.get(j);
								tempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil
										.findTempNoTiceShipMessageXbByRequestCode(resultDeclaration
												.getRequestCode());
								if (tempNoTiceShipMessage != null) {
									if (allNoticeShipMessagesArival == null) {
										allNoticeShipMessagesArival = new ArrayList<TempNoticeShipMessage>();
									}
									allNoticeShipMessagesArival
											.add(tempNoTiceShipMessage);
								}
							}
						}

					}

				}

				// Chuan bi du lieu tau di

				allTmpDoc = TempDocumentLocalServiceUtil
						.findTempDocumentLeaveByMaritimeCodeAndDateFromAndDateTo(
								maritimeCode, dateFrom, dateTo, "'XC'", "16",
								"10");
				if ((allTmpDoc != null) && (allTmpDoc.size() > 0)) {
					for (int i = 0; i < allTmpDoc.size(); i++) {
						tempDocument = allTmpDoc.get(i);
						allResultDeclaration = ResultDeclarationLocalServiceUtil
								.findResultDeclarationByDocumentNameAndDocumentYearXcReport(
										tempDocument.getDocumentName(),
										tempDocument.getDocumentYear());
						if ((allResultDeclaration != null)
								&& (allResultDeclaration.size() > 0)) {
							for (int j = 0; j < allResultDeclaration.size(); j++) {
								resultDeclaration = allResultDeclaration.get(j);
								tempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil
										.findTempNoTiceShipMessageTbByRequestCode(resultDeclaration
												.getRequestCode());
								if (tempNoTiceShipMessage != null) {
									if (allNoticeShipMessagesLeave == null) {
										allNoticeShipMessagesLeave = new ArrayList<TempNoticeShipMessage>();
									}
									allNoticeShipMessagesLeave
											.add(tempNoTiceShipMessage);
								}
							}
						}

					}

				}
				if ((allNoticeShipMessagesArival != null && allNoticeShipMessagesArival
						.size() >= 0)
						|| (allNoticeShipMessagesLeave != null && allNoticeShipMessagesLeave
								.size() >= 0)) {
					hasData = true;
				}
			}

			createDate = ngayLap.substring(0, 10);
			// createTime = ngayLap.substring(11, 13) + "h" +
			// ngayLap.substring(14);
			createTime = "00h00";
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.setAllNoticeShipMessagesArival(allNoticeShipMessagesArival);
		result.setAllNoticeShipMessagesLeave(allNoticeShipMessagesLeave);
		result.setCreateDate(createDate);
		result.setCreateTime(createTime);
		result.setReportCode(reportCode);
		result.setHasData(hasData);

		return result;
	}

	public boolean export2Report(int reportCode, String maritimeCode,
			String ngayLap, String dateFrom, String dateTo) throws IOException, SystemException {
		boolean result = false;

		ArrayList<KeHoachDieuDongModel> dataBeanList = getDataReport(
				reportCode, maritimeCode, ngayLap, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);
		if ((dataBeanList != null) && (dataBeanList.size() > 0)) {
			KeHoachDieuDongModel keHoachDieuDongModel = dataBeanList.get(0);
			result = keHoachDieuDongModel.getHasData();
		}

		Map parameters = new HashMap();
		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunctionModuleBaoCao(
				BaoCaoConstant.KE_HOACH_DIEU_DONG_XML,
				BaoCaoConstant.KE_HOACH_DIEU_DONG_EXPORT, beanColDataSource,
				parameters);
		return result;
	}

	public void export2Report_bk(int reportCode, String maritimeCode,
			String ngayLap, String dateFrom, String dateTo) throws IOException, SystemException {
		boolean result = false;

		ArrayList<KeHoachDieuDongModel> dataBeanList = getDataReport(
				reportCode, maritimeCode, ngayLap, dateFrom, dateTo);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);
		if ((dataBeanList != null) && (dataBeanList.size() > 0)) {
			KeHoachDieuDongModel keHoachDieuDongModel = dataBeanList.get(0);
			result = keHoachDieuDongModel.getHasData();
		}

		Map parameters = new HashMap();
		BaoCaoBussinessUtils reportUtils = new BaoCaoBussinessUtils();
		reportUtils.exportFunction(BaoCaoConstant.KE_HOACH_DIEU_DONG_XML,
				BaoCaoConstant.KE_HOACH_DIEU_DONG_EXPORT, beanColDataSource,
				parameters);
	}

	public static ArrayList<KeHoachDieuDongModel> getDataReport(int mauBaoCao,
			String maritimeCode, String ngayLap, String dateFrom, String dateTo) {
		ArrayList<KeHoachDieuDongModel> dataBeanList = new ArrayList<KeHoachDieuDongModel>();

		dataBeanList.add(getModel(mauBaoCao, maritimeCode, ngayLap, dateFrom,
				dateTo));
		return dataBeanList;
	}

	public boolean export2Excel(int reportCode, String maritimeCode,
			String ngayLap, String dateFrom, String dateTo, String fileName)
			throws IOException, ParsePropertyException,
			InvalidFormatException, NoSuchDmHistoryStateException,
			NoSuchDmUnitGeneralException, NoSuchDmHistoryPortWharfException,
			SystemException {

		boolean result = false;
		ArrayList<KeHoachDieuDongModel> dataBeanList = getDataReport(
				reportCode, maritimeCode, ngayLap, dateFrom, dateTo);
		KeHoachDieuDongModel keHoachDieuDongModel = null;
		if ((dataBeanList != null) && (dataBeanList.size() > 0)) {
			keHoachDieuDongModel = dataBeanList.get(0);
			result = keHoachDieuDongModel.getHasData();
		}
		List<Map> lstArrival = new ArrayList<Map>();
		List<Map> lstLeave = new ArrayList<Map>();
		Map dataBeans = new HashMap();
		int stt = 1;
		for (TempNoticeShipMessage tempNoTiceShipMessage : keHoachDieuDongModel
				.getAllNoticeShipMessagesArival()) {
			lstArrival
					.add(setTempNoTiceShipMessage(tempNoTiceShipMessage, stt));
			stt++;
		}
		stt = 1;
		for (TempNoticeShipMessage tempNoTiceShipMessage : keHoachDieuDongModel
				.getAllNoticeShipMessagesLeave()) {
			lstLeave.add(setTempNoTiceShipMessage(tempNoTiceShipMessage, stt));
			stt++;
		}
		dataBeans.put("arrival", lstArrival);
		dataBeans.put("leave", lstLeave);
		dataBeans.put("createDate", keHoachDieuDongModel.getCreateDate());
		dataBeans.put("createTime", keHoachDieuDongModel.getCreateTime());
		dataBeans.put("maritimeName", keHoachDieuDongModel.getMaritimeName());

		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS(pathFileTemp + "KeHoachDieuDongTemplate.xls",
				dataBeans, pathFileSub + fileName);
		return result;
	}

	private Map setTempNoTiceShipMessage(
			TempNoticeShipMessage tempNoTiceShipMessage, int stt)
			throws NoSuchDmHistoryStateException, SystemException,
			NoSuchDmUnitGeneralException, NoSuchDmHistoryPortWharfException {
		if (tempNoTiceShipMessage != null) {
			Map tempNoTiceShipMessageMap = new HashMap();
			tempNoTiceShipMessageMap.put("stt", stt);
			tempNoTiceShipMessageMap.put("shipName",
					tempNoTiceShipMessage.getShipName());
			tempNoTiceShipMessageMap.put("stateName", GetNameFunction
					.getStateName(new Date(),
							tempNoTiceShipMessage.getStateCode()));
			tempNoTiceShipMessageMap.put("callSign",
					tempNoTiceShipMessage.getCallSign());
			tempNoTiceShipMessageMap.put("grt", tempNoTiceShipMessage.getGrt());
			tempNoTiceShipMessageMap.put("dwt", tempNoTiceShipMessage.getDwt());
			tempNoTiceShipMessageMap.put("loa", tempNoTiceShipMessage.getLoa());
			tempNoTiceShipMessageMap.put("shownDraft",
					tempNoTiceShipMessage.getShownDraftxF() + "/"
							+ tempNoTiceShipMessage.getShownDraftxA());
			tempNoTiceShipMessageMap
					.put("unitName",
							GetNameFunction
									.getDmUnitGeneralName(tempNoTiceShipMessage
											.getUnitQuantityofCargo())
									+ " "
									+ tempNoTiceShipMessage
											.getQuantityOfCargo());
			tempNoTiceShipMessageMap.put("portWharfNameVN", GetNameFunction
					.getPortWharfNameVN(new Date(),
							tempNoTiceShipMessage.getPortWharfCode()));
			tempNoTiceShipMessageMap.put(
					"arrDate",
					ReportFunction.parserDateToString3LT(tempNoTiceShipMessage
							.getArrivalDate()) == null ? "" : ReportFunction
							.parserDateToString3LT(tempNoTiceShipMessage
									.getArrivalDate()));
			tempNoTiceShipMessageMap.put("shipAgencyName",
					tempNoTiceShipMessage.getNameOfShipAgent());

			return tempNoTiceShipMessageMap;
		}
		return null;
	}

	
}
