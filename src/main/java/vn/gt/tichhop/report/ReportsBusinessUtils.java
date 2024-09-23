package vn.gt.tichhop.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.nsw.liferay.core.ActionRequest;


import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import com.fds.nsw.nghiepvu.model.DmMaritime;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssueAcceptance;
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaScheduleLaunching;
import com.fds.nsw.nghiepvu.model.VmaScheduleSecurity;
import com.fds.nsw.nghiepvu.model.VmaScheduleShifting;
import com.fds.nsw.nghiepvu.model.VmaScheduleShipyard;
import com.fds.nsw.nghiepvu.model.VmaScheduleTesting;






import vn.gt.dao.noticeandmessage.service.IssueAcceptanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.phuongtien.VMAUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.report.CargoDeclaration.ExportCargoDeclaration;
import vn.gt.tichhop.report.CrewEffectsDeclaration.Export2CrewEffectsDeclaration;
import vn.gt.tichhop.report.CrewList.Export2CrewList;
import vn.gt.tichhop.report.DangerousGoodsManifest.Export2DangerousGoodsManifest;
import vn.gt.tichhop.report.DeclarationForPlantQuarantine.Export2DeclarationForPlantQuarantine;
import vn.gt.tichhop.report.DeclarationOfHealth.Export2DeclarationOfHealth;
import vn.gt.tichhop.report.GeneralDeclaration.Export2GeneralDeclaration;
import vn.gt.tichhop.report.NoticeOfArrival.Export2NoticeOfArrival;
import vn.gt.tichhop.report.PassengerList.Export2PassengerList;
import vn.gt.tichhop.report.PermissionForTransit.Export2PermissionForTransit;
import vn.gt.tichhop.report.PortClearance.Export2IssuePortClearance;
import vn.gt.tichhop.report.PttndAcceptance.Export2PttndAcceptance;
import vn.gt.tichhop.report.PttndCrewList.Export2PttndCrewList;
import vn.gt.tichhop.report.PttndGeneralDeclaration.Export2PttndGeneralDeclaration;
import vn.gt.tichhop.report.PttndPassengerList.Export2PttndPassengerList;
import vn.gt.tichhop.report.PttndPortClearance.Export2PttndPortClearance;
import vn.gt.tichhop.report.ShiftingOrder.Export2ShiftingOrder;
import vn.gt.tichhop.report.ShipSecurityNotification.ExportShipSecurityNotification;
import vn.gt.tichhop.report.ShipStoresDeclaration.Export2ShipStoresDeclaration;
import vn.gt.tichhop.report.vma.VmaItineraryScheduleModel;
import vn.gt.tichhop.report.vma.VmaScheduleLaunchingModel;
import vn.gt.tichhop.report.vma.VmaScheduleSecurityModel;
import vn.gt.tichhop.report.vma.VmaScheduleShiftingModel;
import vn.gt.tichhop.report.vma.VmaScheduleShipyardModel;
import vn.gt.tichhop.report.vma.VmaScheduleTestingModel;
import vn.gt.utils.PageType;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONObject;


import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;

@Slf4j
public class ReportsBusinessUtils {

	private String realPath = this.getClass().getProtectionDomain()
			.getCodeSource().getLocation().toString();
	private String pathFileTemp = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/report/";
	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/export/";

	private String pathFileTemp_upgrade_1 = realPath.substring(
			realPath.lastIndexOf(":"), realPath.lastIndexOf("/WEB-INF"))
			.replaceFirst(":", "")
			+ "/report_upgrade_1/";

	// private String
	// pathFileTemp="/home/binhth/MEGAsync/SERVER/liferay-portal-6.1.0-ce-ga1/tomcat-7.0.23/webapps/TichHopGiaoThong-portlet/report/";
	// private String
	// pathFileSub="/home/binhth/MEGAsync/SERVER/liferay-portal-6.1.0-ce-ga1/tomcat-7.0.23/webapps/TichHopGiaoThong-portlet/export/";
	public static Long actionExport(String requestCode, int documentName,
			int documentYear, int messageTypeReport, String loaiThuTuc)
			throws Exception {

		log.info("--BEGIN EXPORT ACTION ------requestCode==" + requestCode
				+ "=======messageTypeReport=" + messageTypeReport
				+ "------documentName==" + documentName
				+ "=======documentYear=" + documentYear
				+ "================loaiThuTuc" + loaiThuTuc);

		long nanoTimePDF = 0;

		if (messageTypeReport == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
			ExportShipSecurityNotification action = new ExportShipSecurityNotification();
			nanoTimePDF = action.export_ShipSecurityNotification(requestCode,
					documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.THONG_BAO_TAU_DEN_CANG
				|| messageTypeReport == MessageType.THONG_BAO_TAU_ROI_CANG
				|| messageTypeReport == MessageType.THONG_BAO_TAU_QUA_CANH) {

			Export2NoticeOfArrival action = new Export2NoticeOfArrival();
			nanoTimePDF = action.export_NoticeOfArrival(requestCode,
					documentName, documentYear,
					PageType.TYPE_THONG_BAO_TAU_DEN_CANG, messageTypeReport,
					loaiThuTuc);

		} else if (messageTypeReport == MessageType.XAC_BAO_TAU_DEN_CANG
				|| messageTypeReport == MessageType.XAC_BAO_TAU_ROI_CANG
				|| messageTypeReport == MessageType.XAC_BAO_TAU_QUA_CANH) {

			Export2NoticeOfArrival action = new Export2NoticeOfArrival();
			nanoTimePDF = action.export_NoticeOfArrival(requestCode,
					documentName, documentYear,
					PageType.TYPE_XAC_BAO_TAU_DEN_CANG, messageTypeReport,
					loaiThuTuc);

		} else if (messageTypeReport == MessageType.BAN_KHAI_HANG_HOA) {
			ExportCargoDeclaration action = new ExportCargoDeclaration();
			nanoTimePDF = action.export_CargoDeclaration(requestCode,
					documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN) {
			if (loaiThuTuc.equalsIgnoreCase(MessageType.TAU_VAO_PTTND + "")
					|| loaiThuTuc.equalsIgnoreCase(MessageType.TAU_RA_PTTND
							+ "")) {
				Export2PttndCrewList action = new Export2PttndCrewList();
				nanoTimePDF = action.Export2PttndCrewList(requestCode,
						documentName, documentYear, loaiThuTuc);
			} else {
				Export2CrewList action = new Export2CrewList();
				nanoTimePDF = action.export2CrewList(requestCode, documentName,
						documentYear, loaiThuTuc);
			}

		} else if (messageTypeReport == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH) {
			if (loaiThuTuc.equalsIgnoreCase(MessageType.TAU_VAO_PTTND + "")
					|| loaiThuTuc.equalsIgnoreCase(MessageType.TAU_RA_PTTND
							+ "")) {
				Export2PttndPassengerList action = new Export2PttndPassengerList();
				nanoTimePDF = action.Export2PttndPassengerList(requestCode,
						documentName, documentYear, loaiThuTuc);
			} else {
				Export2PassengerList action = new Export2PassengerList();
				nanoTimePDF = action.export2PassengerList(requestCode,
						documentName, documentYear, loaiThuTuc);
			}

		} else if (messageTypeReport == MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT) {
			Export2DeclarationForPlantQuarantine action = new Export2DeclarationForPlantQuarantine();
			nanoTimePDF = action.export2DeclarationForPlantQuarantine(
					requestCode, documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN) {
			Export2CrewEffectsDeclaration action = new Export2CrewEffectsDeclaration();
			nanoTimePDF = action.export2CrewEffectsDeclaration(requestCode,
					documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM) {
			Export2DangerousGoodsManifest action = new Export2DangerousGoodsManifest();
			nanoTimePDF = action.export2DangerousGoodsManifest(requestCode,
					documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT) {
//			Export2DeclarationForAnimalQuarantine action = new Export2DeclarationForAnimalQuarantine();
//			nanoTimePDF = action.export2DeclarationForAnimalQuarantine(
//					requestCode, documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.BAN_KHAI_CHUNG) {
			if (loaiThuTuc.equalsIgnoreCase(MessageType.TAU_VAO_PTTND + "")
					|| loaiThuTuc.equalsIgnoreCase(MessageType.TAU_RA_PTTND
							+ "")) {
				Export2PttndGeneralDeclaration action = new Export2PttndGeneralDeclaration();
				nanoTimePDF = action.Export2PttndGeneralDeclaration(
						requestCode, documentName, documentYear, loaiThuTuc);
			} else {
				Export2GeneralDeclaration action = new Export2GeneralDeclaration();
				nanoTimePDF = action.export2GeneralDeclaration(requestCode,
						documentName, documentYear, loaiThuTuc);
			}

		} else if (messageTypeReport == MessageType.LENH_DIEU_DONG) {
			Export2ShiftingOrder action = new Export2ShiftingOrder();
			nanoTimePDF = action.export2ShiftingOrder(requestCode,
					documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.BAN_KHAI_DU_TRU_CUA_TAU) {
			Export2ShipStoresDeclaration action = new Export2ShipStoresDeclaration();
			nanoTimePDF = action.exportShipStoresDeclaration(requestCode,
					documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI) {
			Export2DeclarationOfHealth action = new Export2DeclarationOfHealth();
			nanoTimePDF = action.export2DeclarationOfHealth(requestCode,
					documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.GIAY_PHEP_QUA_CANH) {
			Export2PermissionForTransit action = new Export2PermissionForTransit();
			nanoTimePDF = action.export2PermissionForTransit(requestCode,
					documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH) {
			// if (documentType!=null &&documentType.equals("XC")) {
			Export2IssuePortClearance action = new Export2IssuePortClearance();
			nanoTimePDF = action.export2PortClearance(requestCode,
					documentName, documentYear, loaiThuTuc);

		} else if (messageTypeReport == MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN) {
			log.info("PTTND   GIAY_PHEP_ROI_CANG_CHO_TAU_DEN");
			Export2PttndPortClearance action = new Export2PttndPortClearance();
			nanoTimePDF = action.export2PortClearance(requestCode,
					documentName, documentYear, loaiThuTuc);
		} else if (messageTypeReport == MessageType.PTTND_GIAY_PHEP_VAO_CANG) {
			log.info("PTTND_GIAY_PHEP_VAO_CANG");
			Export2PttndAcceptance action = new Export2PttndAcceptance();
			nanoTimePDF = action.export2IssueAcceptance(requestCode,
					documentName, documentYear, loaiThuTuc);
		}

		return nanoTimePDF;
	}

	// add by TrungNT

	public static List<Object> getDataReport(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof VmaScheduleTestingModel) {
			VmaScheduleTestingModel vmaScheduleTestingModel = (VmaScheduleTestingModel) object;

			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(vmaScheduleTestingModel
							.getPortofAuthority());
			if (dmMaritime != null) {

				vmaScheduleTestingModel.setMaritimeName(dmMaritime
						.getMaritimeName());
				vmaScheduleTestingModel.setMaritimeNameVN(dmMaritime
						.getMaritimeNameVN());

			}

			result.add(vmaScheduleTestingModel);

		} else if (object instanceof VmaScheduleShiftingModel) {
			VmaScheduleShiftingModel vmaScheduleShiftingModel = (VmaScheduleShiftingModel) object;
			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(vmaScheduleShiftingModel
							.getPortofAuthority());
			if (dmMaritime != null) {

				vmaScheduleShiftingModel.setMaritimeName(dmMaritime
						.getMaritimeName());
				vmaScheduleShiftingModel.setMaritimeNameVN(dmMaritime
						.getMaritimeNameVN());

			}
			result.add(vmaScheduleShiftingModel);

		} else if (object instanceof VmaScheduleLaunchingModel) {
			VmaScheduleLaunchingModel vmaScheduleLaunchingModel = (VmaScheduleLaunchingModel) object;
			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(vmaScheduleLaunchingModel
							.getPortofAuthority());
			if (dmMaritime != null) {

				vmaScheduleLaunchingModel.setMaritimeName(dmMaritime
						.getMaritimeName());
				vmaScheduleLaunchingModel.setMaritimeNameVN(dmMaritime
						.getMaritimeNameVN());

			}
			result.add(vmaScheduleLaunchingModel);

		} else if (object instanceof VmaScheduleShipyardModel) {
			VmaScheduleShipyardModel vmaScheduleShipyardModel = (VmaScheduleShipyardModel) object;
			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(vmaScheduleShipyardModel
							.getPortofAuthority());
			if (dmMaritime != null) {

				vmaScheduleShipyardModel.setMaritimeName(dmMaritime
						.getMaritimeName());
				vmaScheduleShipyardModel.setMaritimeNameVN(dmMaritime
						.getMaritimeNameVN());

			}
			result.add(vmaScheduleShipyardModel);

		} else if (object instanceof VmaScheduleSecurityModel) {
			VmaScheduleSecurityModel vmaScheduleSecurityModel = (VmaScheduleSecurityModel) object;
			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(vmaScheduleSecurityModel
							.getPortofAuthority());
			if (dmMaritime != null) {

				vmaScheduleSecurityModel.setMaritimeName(dmMaritime
						.getMaritimeName());
				vmaScheduleSecurityModel.setMaritimeNameVN(dmMaritime
						.getMaritimeNameVN());

			}
			result.add(vmaScheduleSecurityModel);

		} else if (object instanceof VmaItineraryScheduleModel) {
			VmaItineraryScheduleModel vmaItineraryScheduleModel = (VmaItineraryScheduleModel) object;
			try {
				VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
						.fetchByitineraryNo(vmaItineraryScheduleModel
								.getItineraryNo());

				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
						.getByMaritimeCode(vmaItinerary.getPortofAuthority());
				if (dmMaritime != null) {

					vmaItineraryScheduleModel.setMaritimeName(dmMaritime
							.getMaritimeName());
					vmaItineraryScheduleModel.setMaritimeNameVN(dmMaritime
							.getMaritimeNameVN());

				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			result.add(vmaItineraryScheduleModel);
		}
		return result;
	}

	//TODO: com.fds.nsw.nghiepvu.model.impl.VmaScheduleShiftingImpl cannot be cast to vn.gt.tichhop.report.vma.VmaScheduleShiftingModel
	public static Long actionExport(Object object, int... type)
			throws Exception {
		ReportsBusinessUtils reportUtils = new ReportsBusinessUtils();
		long nanoTimePDF = 0;
		BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
		if (object instanceof VmaScheduleTesting) {

			VmaScheduleTesting vmaScheduleTesting = (VmaScheduleTesting) object;
			log.info("--BEGIN EXPORT VmaScheduleTesting ======================>>>>> "
					+ vmaScheduleTesting.getNameOfShip());
			JSONObject jsonObject = VMAUtils.object2Json(vmaScheduleTesting, VmaScheduleTesting.class);
			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(vmaScheduleTesting.getPortofAuthority());
			jsonObject.put("maritimeName", dmMaritime.getMaritimeName());
			jsonObject.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
			log.info("--BEGIN EXPORT VmaScheduleTesting ======================>>>>> "
					+ jsonObject);
			/*VmaScheduleTestingModel vmaScheduleTestingModel = (VmaScheduleTestingModel) object;
			List<Object> dataBeanList = getDataReport(vmaScheduleTestingModel);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					dataBeanList);
			nanoTimePDF = reportUtils.exportFunction(
					ReportConstant.VMA_SCHEDULE_TESTING,
					ReportConstant.VMA_SCHEDULE_TESTING_EXPORT,
					beanColDataSource, new HashMap());*/
			nanoTimePDF = action.exportFunctionPDF(ReportConstant.VMA_SCHEDULE_TESTING, ReportConstant.VMA_SCHEDULE_TESTING_EXPORT, jsonObject);
		} else if (object instanceof VmaScheduleShifting) {
			VmaScheduleShifting vmaScheduleShifting = (VmaScheduleShifting) object;
			log.info("--BEGIN EXPORT vmaScheduleShifting ======================>>>>> "
					+ vmaScheduleShifting.getNameOfShip());
			/*VmaScheduleShiftingModel vmaScheduleShiftingModel = (VmaScheduleShiftingModel) object;
			List<Object> dataBeanList = getDataReport(vmaScheduleShiftingModel);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					dataBeanList);
			nanoTimePDF = reportUtils.exportFunction(
					ReportConstant.VMA_SCHEDULE_SHIFTING,
					ReportConstant.VMA_SCHEDULE_SHIFTING_EXPORT,
					beanColDataSource, new HashMap());*/
			JSONObject jsonObject = VMAUtils.object2Json(vmaScheduleShifting, VmaScheduleShifting.class);
			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(vmaScheduleShifting.getPortofAuthority());
			jsonObject.put("maritimeName", dmMaritime.getMaritimeName());
			jsonObject.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
			log.info("--BEGIN EXPORT vmaScheduleShifting ======================>>>>> "
					+ jsonObject);
			nanoTimePDF = action.exportFunctionPDF(ReportConstant.VMA_SCHEDULE_SHIFTING, ReportConstant.VMA_SCHEDULE_SHIFTING_EXPORT, jsonObject);
		} else if (object instanceof VmaScheduleLaunching) {
			VmaScheduleLaunching vmaScheduleLaunching = (VmaScheduleLaunching) object;
			log.info("--BEGIN EXPORT vmaScheduleLaunching ======================>>>>> "
					+ vmaScheduleLaunching.getNameOfShip());
			/*VmaScheduleLaunchingModel vmaScheduleLaunchingModel = (VmaScheduleLaunchingModel) object;
			List<Object> dataBeanList = getDataReport(vmaScheduleLaunchingModel);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					dataBeanList);
			nanoTimePDF = reportUtils.exportFunction(
					ReportConstant.VMA_SCHEDULE_LAUNCHING,
					ReportConstant.VMA_SCHEDULE_LAUNCHING_EXPORT,
					beanColDataSource, new HashMap());*/
			JSONObject jsonObject = VMAUtils.object2Json(vmaScheduleLaunching, VmaScheduleLaunching.class);
			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(vmaScheduleLaunching.getPortofAuthority());
			jsonObject.put("maritimeName", dmMaritime.getMaritimeName());
			jsonObject.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
			log.info("--BEGIN EXPORT vmaScheduleLaunching ======================>>>>> "
					+ jsonObject);
			nanoTimePDF = action.exportFunctionPDF(ReportConstant.VMA_SCHEDULE_LAUNCHING, ReportConstant.VMA_SCHEDULE_LAUNCHING_EXPORT, jsonObject);
		} else if (object instanceof VmaScheduleShipyard) {
			VmaScheduleShipyard vmaScheduleShipyard = (VmaScheduleShipyard) object;
			log.info("--BEGIN EXPORT vmaScheduleShipyard ======================>>>>> "
					+ vmaScheduleShipyard.getNameOfShip());

			/*VmaScheduleShipyardModel vmaScheduleShipyardModel = (VmaScheduleShipyardModel) object;
			List<Object> dataBeanList = getDataReport(vmaScheduleShipyardModel);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					dataBeanList);

			nanoTimePDF = reportUtils.exportFunction(
					ReportConstant.VMA_SCHEDULE_SHIPYARD,
					ReportConstant.VMA_SCHEDULE_SHIPYARD_EXPORT,
					beanColDataSource, new HashMap());*/
			JSONObject jsonObject = VMAUtils.object2Json(vmaScheduleShipyard, VmaScheduleShipyard.class);
			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(vmaScheduleShipyard.getPortofAuthority());
			jsonObject.put("maritimeName", dmMaritime.getMaritimeName());
			jsonObject.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
			log.info("--BEGIN EXPORT vmaScheduleShipyard ======================>>>>> "
					+ jsonObject);
			nanoTimePDF = action.exportFunctionPDF(ReportConstant.VMA_SCHEDULE_SHIPYARD, ReportConstant.VMA_SCHEDULE_SHIPYARD_EXPORT, jsonObject);
		} else if (object instanceof VmaScheduleSecurity) {
			VmaScheduleSecurity vmaScheduleSecurity = (VmaScheduleSecurity) object;
			log.info("--BEGIN EXPORT vmaScheduleSecurity ======================>>>>> "
					+ vmaScheduleSecurity.getNameOfShip());
			JSONObject jsonObject = VMAUtils.object2Json(vmaScheduleSecurity, VmaScheduleSecurity.class);
			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(vmaScheduleSecurity.getPortofAuthority());
			jsonObject.put("maritimeName", dmMaritime.getMaritimeName());
			jsonObject.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
			log.info("--BEGIN EXPORT vmaScheduleShipyard ======================>>>>> "
					+ jsonObject);
			if (type != null && type.length > 0) {
				if (type[0] == 1) {
					nanoTimePDF = action.exportFunctionPDF(ReportConstant.VMA_SCHEDULE_SECURITY, ReportConstant.VMA_SCHEDULE_SECURITY_EXPORT, jsonObject);
				} else if (type[0] == 2) {
					nanoTimePDF = action.exportFunctionPDF(ReportConstant.VMA_SCHEDULE_EVACUATE, ReportConstant.VMA_SCHEDULE_EVACUATE_EXPORT, jsonObject);
				}

			}
		} else if (object instanceof VmaItinerarySchedule) {
			VmaItinerarySchedule vmaItinerarySchedule = (VmaItinerarySchedule) object;
			log.info("--BEGIN EXPORT vmaItinerarySchedule ======================>>>>> "
					+ vmaItinerarySchedule.getNameOfShip());

			/*VmaItineraryScheduleModel vmaItineraryScheduleModel = (VmaItineraryScheduleModel) object;
			List<Object> dataBeanList = getDataReport(vmaItineraryScheduleModel);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					dataBeanList);
			nanoTimePDF = reportUtils.exportFunction(
					ReportConstant.PTTND_PORTCLEARANCE,
					ReportConstant.PTTND_PORTCLEARANCE_EXPORT,
					beanColDataSource, new HashMap());*/
			JSONObject jsonObject = VMAUtils.object2Json(vmaItinerarySchedule, VmaItinerarySchedule.class);
			VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(vmaItinerarySchedule.getItineraryNo());

			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(vmaItinerary.getPortofAuthority());
			jsonObject.put("maritimeName", dmMaritime.getMaritimeName());
			jsonObject.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
			log.info("--BEGIN EXPORT vmaScheduleShipyard ======================>>>>> "
					+ jsonObject);
			nanoTimePDF = action.exportFunctionPDF(ReportConstant.PTTND_PORTCLEARANCE, ReportConstant.PTTND_PORTCLEARANCE_EXPORT, jsonObject);
		} else {
			log.info("--BEGIN EXPORT Object ======================>>>>> None");
		}

		return nanoTimePDF;
	}

	public static Long checkDigitalAttachedFile(User userLogin,
			String requestCode, int messageTypeReport, String loaiThuTuc)
			throws Exception {

		log.info("--BEGIN checkDigitalAttachedFile---requestCode=="
				+ requestCode + "==MessageTypeReport=" + messageTypeReport);

		long digitalAttachedFile = 0;

		if (messageTypeReport == MessageType.LENH_DIEU_DONG) {
			log.info("--BEGIN checkDigitalAttachedFile---requestCode==");
			IssueShiftingOrder digitalAttached = IssueShiftingOrderLocalServiceUtil
					.getByRequestCode(requestCode);
			if (Validator.isNotNull(digitalAttached)) {
				if (Validator.isNotNull(digitalAttached.getAttachedFile())) {
					log.info("--BEGIN LENH_DIEU_DONG- getAttachedFile--requestCode=="
							+ digitalAttached.getAttachedFile());
					return digitalAttached.getAttachedFile();

				}
			}
		} else if (messageTypeReport == MessageType.GIAY_PHEP_QUA_CANH) {
			IssuePermissionForTransit forTransit = IssuePermissionForTransitLocalServiceUtil
					.getByrequestCode(requestCode);
			if (Validator.isNotNull(forTransit)) {
				if (Validator.isNotNull(forTransit.getAttachedFile())) {
					return forTransit.getAttachedFile();
				}
			}

		} else if (messageTypeReport == MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH) {
			IssuePortClearance portClearance = IssuePortClearanceLocalServiceUtil
					.getByRequestCode(requestCode);
			if (Validator.isNotNull(portClearance)) {
				if (Validator.isNotNull(portClearance.getAttachedFile())) {
					return portClearance.getAttachedFile();
				}
			}
		} else if (messageTypeReport == MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN) {
			IssuePortClearance portClearance = IssuePortClearanceLocalServiceUtil
					.getByRequestCode(requestCode);
			if (Validator.isNotNull(portClearance)) {
				if (Validator.isNotNull(portClearance.getAttachedFile())) {
					return portClearance.getAttachedFile();
				}
			}
		} else if (messageTypeReport == MessageType.PTTND_GIAY_PHEP_VAO_CANG) {
			IssueAcceptance issueAcceptance = IssueAcceptanceLocalServiceUtil
					.getByRequestCode(requestCode);
			if (Validator.isNotNull(issueAcceptance)) {
				if (Validator.isNotNull(issueAcceptance.getAttachedFile())) {
					return issueAcceptance.getAttachedFile();
				}
			}
		}

		return digitalAttachedFile;
	}

	/*
	 * Function Export to Server
	 */
	public long exportFunction(String tenFile_Template, String tenFile_Export,
			JRDataSource dataSource, Map parameters) throws IOException {
		long nanoTime = System.nanoTime();
		try {
			log.info("----- END EXPORT SERVER ---" + pathFileSub);
			log.info("----- END EXPORT SERVER ---" + pathFileTemp + "..."
					+ tenFile_Template);
			InputStream inputStream = new FileInputStream(pathFileTemp
					+ tenFile_Template);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			
			if(tenFile_Template.contains("Shifting_Order.jrxml")) {
				log.info("===jasperDesign.getPageWidth()===" + jasperDesign.getPageWidth());
				log.info("===jasperDesign.getPageHeight()===" + jasperDesign.getPageHeight());
				jasperDesign.setOrientation(OrientationEnum.PORTRAIT);
			}
			
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);

			parameters.put("SUBREPORT_DIR", pathFileTemp);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, dataSource);

			JasperExportManager.exportReportToPdfFile(jasperPrint, pathFileSub
					+ nanoTime + "_" + tenFile_Export);

			return nanoTime;
		} catch (Exception e) {
			log.error("---LOI EXPORT ---" + e);
			return 0;
		}
	}

	public long exportFunctionUpgrade(String tenFile_Template,
			String tenFile_Export, JRDataSource dataSource, Map parameters)
			throws IOException {
		long nanoTime = System.nanoTime();
		try {
			log.info("----- END EXPORT SERVER ---" + pathFileSub);
			log.info("----- END EXPORT SERVER ---" + pathFileTemp_upgrade_1
					+ "..." + tenFile_Template);
			InputStream inputStream = new FileInputStream(
					pathFileTemp_upgrade_1 + tenFile_Template);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			
			if(tenFile_Template.contains("Shifting_Order.jrxml")) {
				log.info("===jasperDesign.getPageWidth()===" + jasperDesign.getPageWidth());
				log.info("===jasperDesign.getPageHeight()===" + jasperDesign.getPageHeight());
				jasperDesign.setOrientation(OrientationEnum.PORTRAIT);
			}
			
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);

			parameters.put("SUBREPORT_DIR", pathFileTemp_upgrade_1);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, dataSource);
			
			JasperExportManager.exportReportToPdfFile(jasperPrint, pathFileSub
					+ nanoTime + "_" + tenFile_Export);

			return nanoTime;
		} catch (Exception e) {
			log.error("---LOI EXPORT ---" + e);
			return 0;
		}
	}

	public String getTemplateReportFilePath(ActionRequest actionRequest,
			String fileName) {
		return actionRequest.getPortletSession().getPortletContext()
				.getRealPath("/").replace("/", File.separator)
				.replace(File.separator + ".", "")
				+ "report" + File.separator + fileName;
	}

	public String getSubTemplatePath(ActionRequest actionRequest) {
		return actionRequest.getPortletSession().getPortletContext()
				.getRealPath("/").replace("/", File.separator)
				.replace(File.separator + ".", "")
				+ "report" + File.separator;
	}

	public static String taoSo(String keyData, int maxChar) {

		String taoSo = "";
		try {

			long count = taoGiaTriThamSo(keyData);
			taoSo = count + "";
			// int VR_SIZE_SO_DANG_KY_KIEM_TRA =
			// DKConfigurationManager.getIntProp("VR_SIZE_SO_DANG_KY_KIEM_TRA",
			// 6);
			int size = maxChar - taoSo.length();
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					taoSo = "0" + taoSo;
				}
			} else if (size < 0) {

//				ThamSoHeThong thamSoHeThong = ThamSoHeThongLocalServiceUtil
//						.findByKeyData(keyData);
//				taoSo = "1";
//				thamSoHeThong.setValueData(taoSo);
//				ThamSoHeThongLocalServiceUtil
//						.updateThamSoHeThong(thamSoHeThong);
//				for (int i = 1; i < maxChar; i++) {
//					taoSo = "0" + taoSo;
//				}
//				taoSo = "0" + taoSo;

			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return taoSo;
	}

	public static String taoSo(String keyData, String description, int maxChar) {

		String taoSo = "";
		try {

			long count = taoGiaTriThamSo(keyData, description);
			taoSo = count + "";

			int size = maxChar - taoSo.length();
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					taoSo = "0" + taoSo;
				}
			} else if (size < 0) {

//				ThamSoHeThong thamSoHeThong = ThamSoHeThongLocalServiceUtil
//						.findByKeyDataAndDescription(keyData, description);
//				taoSo = "1";
//				thamSoHeThong.setValueData(taoSo);
//				ThamSoHeThongLocalServiceUtil
//						.updateThamSoHeThong(thamSoHeThong);
//				for (int i = 1; i < maxChar; i++) {
//					taoSo = "0" + taoSo;
//				}
//				taoSo = "0" + taoSo;

			}

			taoSo = description + taoSo;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return taoSo;
	}

	public static long taoGiaTriThamSo(String keyData) throws SystemException {
//		ThamSoHeThong thamSoHeThong = ThamSoHeThongLocalServiceUtil
//				.findByKeyData(keyData);
		long count = 1;
//		if (thamSoHeThong == null) {
//			thamSoHeThong = new ThamSoHeThong();
//			thamSoHeThong.setDescription("");
//			thamSoHeThong.setKeyData(keyData);
//			thamSoHeThong.setValueData(count + "");
//
//			ThamSoHeThongLocalServiceUtil.addThamSoHeThong(thamSoHeThong);
//
//		} else {
//			count = Long.valueOf(thamSoHeThong.getValueData()) + 1;
//			thamSoHeThong.setValueData(count + "");
//			ThamSoHeThongLocalServiceUtil.updateThamSoHeThong(thamSoHeThong);
//		}
		return count;
	}

	public static long taoGiaTriThamSo(String keyData, String description)
			throws SystemException {
//		ThamSoHeThong thamSoHeThong = ThamSoHeThongLocalServiceUtil
//				.findByKeyDataAndDescription(keyData, description);
		long count = 1;
//		if (thamSoHeThong == null) {
//			thamSoHeThong = new ThamSoHeThong();
//			thamSoHeThong.setDescription(description);
//			thamSoHeThong.setKeyData(keyData);
//			thamSoHeThong.setValueData(count + "");
//
//			ThamSoHeThongLocalServiceUtil.addThamSoHeThong(thamSoHeThong);
//
//		} else {
//			count = Long.valueOf(thamSoHeThong.getValueData()) + 1;
//			thamSoHeThong.setValueData(count + "");
//			ThamSoHeThongLocalServiceUtil.updateThamSoHeThong(thamSoHeThong);
//		}
		return count;
	}

	public static String taoGiaTriThamSo(String keyData, String description, String syncDateyyyyMMddHHmmss)
			throws SystemException {
//		ThamSoHeThong thamSoHeThong = ThamSoHeThongLocalServiceUtil
//				.findByKeyDataAndDescription(keyData, description);
		
//		if (thamSoHeThong == null) {
//			thamSoHeThong = new ThamSoHeThong();
//			thamSoHeThong.setDescription(description);
//			thamSoHeThong.setKeyData(keyData);
//			thamSoHeThong.setValueData(syncDateyyyyMMddHHmmss);
//
//			ThamSoHeThongLocalServiceUtil.addThamSoHeThong(thamSoHeThong);
//
//		} else {
//			thamSoHeThong.setValueData(syncDateyyyyMMddHHmmss);
//			ThamSoHeThongLocalServiceUtil.updateThamSoHeThong(thamSoHeThong);
//		}
		return syncDateyyyyMMddHHmmss;
	}
	
	public static String createCode(String prefix, String key, String oldCode,
			int maxChar) {
		int size = maxChar - oldCode.length();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				oldCode = "0" + oldCode;
			}
		}
		String newCode = "";
		if (key != null) {
			newCode = prefix + key + oldCode;
		} else {
			newCode = prefix + oldCode;
		}
		return newCode;
	}
}
