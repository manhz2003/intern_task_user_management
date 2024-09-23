package vn.gt.portlet.baocao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipLocalServiceUtil;
import vn.gt.portlet.TransportationMVCAction;
import vn.gt.portlet.baocao.baocaohotronghiepvu.BC24TExport;
import vn.gt.portlet.baocao.baocaohotronghiepvu.BC25TExport;
import vn.gt.portlet.baocao.baocaohotronghiepvu.BC26TExport;
import vn.gt.portlet.baocao.baocaohotronghiepvu.BC27TExport;
import vn.gt.portlet.baocao.baocaohotronghiepvu.BC36TExport;
import vn.gt.portlet.baocao.bc12bt.BC12BTExport;
import vn.gt.portlet.baocao.bc15t.BC15TExport;
import vn.gt.portlet.baocao.bchanghoa.BC11BTExport;
import vn.gt.portlet.baocao.bchanghoa.BC11TExport;
import vn.gt.portlet.baocao.bchanghoa.BC12SSTExport;
import vn.gt.portlet.baocao.bchanghoa.BC12TExport;
import vn.gt.portlet.baocao.bchanghoa.BC14TExport;
import vn.gt.portlet.baocao.bchanghoa.BC16TExport;
import vn.gt.portlet.baocao.bchanghoa.BC19TExport;
import vn.gt.portlet.baocao.bchanghoa.BC20TExport;
import vn.gt.portlet.baocao.bchanghoa.BC21TExport;
import vn.gt.portlet.baocao.bchanghoa.MauBC15Export;
import vn.gt.portlet.baocao.bcpttnd.BC46_47_48_52TExport;
import vn.gt.portlet.baocao.bcpttnd.BC49_50_51_55TExport;
import vn.gt.portlet.baocao.bcpttnd.BC53_54TExport;
import vn.gt.portlet.baocao.bcpttnd.BC70_78TExport;
import vn.gt.portlet.baocao.bcpttnd.BC71_72_73_79TExport;
import vn.gt.portlet.baocao.bcpttnd.BC77TExport;
import vn.gt.portlet.baocao.bctaubien.BC13TExport;
import vn.gt.portlet.baocao.bctaubien.BC58SExport;
import vn.gt.portlet.baocao.bctaubien.BC58TExport;
import vn.gt.portlet.baocao.bctaubien.BC59TExport;
import vn.gt.portlet.baocao.bctaubien.BC60TExport;
import vn.gt.portlet.baocao.bctaubien.BC65TExport;
import vn.gt.portlet.baocao.bctaubien.BC66TExport;
import vn.gt.portlet.baocao.bctaubien.BC67TExport;
import vn.gt.portlet.baocao.kehoachdieudong.KeHoachDieuDongExport;
import vn.gt.portlet.baocao.thongke.bc12.Bc12Export;
import vn.gt.portlet.baocao.thongke.dvcong.CoCheMotCuaQuocGiaNangCapExport;
import vn.gt.portlet.baocao.thongke.dvcong.DichVuCongExport;
import vn.gt.portlet.baocao.thongke.dvcong.HoanThanhThuTucMotCuaQuocGiaExport;
import vn.gt.portlet.baocao.thongke.dvcongtheophongban.DVCongTheoPhongBanExport;
import vn.gt.portlet.baocao.thongke.tinhhinhnophs.TinhHinhNopHoSoExport;
import vn.gt.portlet.baocao.tinhhinhdukienneodau.TinhHinhDuKienNeoDauExport;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.portlet.phuongtien.VMAUtils;
import vn.gt.portlet.phuongtien.VmaItineraryUtils;
import vn.gt.utils.FormatData;

import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.core.ThemeDisplay;



@Slf4j
@Component
public class BaoCaoPMNVAction extends TransportationMVCAction {

	
	private static final int EXCEL = 1, PDF = 2;

	@Override
	public ResponseEntity<?> serveResource(ResourceRequest resourceRequest,
										   ResourceResponse resourceResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);
		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());

		if (VmaItineraryUtils.checkActiveVma(themeDisplay, resourceRequest,
				resourceResponse)) {
			try {

				HttpServletRequest request = resourceRequest;
				HttpServletRequest requestOrg = request;
				
				String resourceID = ParamUtil.getString(requestOrg, "p_p_resource_id", "");

			

				JSONObject data = JSONFactoryUtil.createJSONObject();

				int reportCode = ParamUtil.getInteger(requestOrg, "reportCode");
				String fromDate = ParamUtil.getString(resourceRequest,
						"fromDate");
				String toDate = ParamUtil.getString(requestOrg, "toDate");
				String createDate = ParamUtil.getString(requestOrg,
						"createDate");
				String maritimeCode = ParamUtil.getString(requestOrg,
						"maritimeCode");
				String reportPeriod = ParamUtil.getString(requestOrg,
						"reportPeriod");
				String reportMadeBy = DanhMucUtils.getString(requestOrg,
						"reportMadeBy", StringPool.BLANK);
				String reportUser = DanhMucUtils.getString(requestOrg,
						"reportMadeBy", StringPool.BLANK);
				String nameOfShip = DanhMucUtils.getString(requestOrg,
						"nameOfShip", StringPool.BLANK);
				String imoNumber = DanhMucUtils.getString(requestOrg,
						"imoNumber", StringPool.BLANK);
				String registryNumber = DanhMucUtils.getString(requestOrg,
						"registryNumber", StringPool.BLANK);
				String from_gt = DanhMucUtils.getString(requestOrg, "from_gt",
						StringPool.BLANK);
				String vrCode = DanhMucUtils.getString(requestOrg, "vrCode",
						StringPool.BLANK);
				String flagStateOfShip = DanhMucUtils.getString(requestOrg,
						"flagStateOfShip", StringPool.BLANK);
				String to_gt = DanhMucUtils.getString(requestOrg, "to_gt",
						StringPool.BLANK);
				String from_dwt = DanhMucUtils.getString(requestOrg,
						"from_dwt", StringPool.BLANK);
				String to_dwt = DanhMucUtils.getString(requestOrg, "to_dwt",
						StringPool.BLANK);
				String from_loa = DanhMucUtils.getString(requestOrg,
						"from_loa", StringPool.BLANK);
				String to_loa = DanhMucUtils.getString(requestOrg, "to_loa",
						StringPool.BLANK);
				String arrivalShipAgency = DanhMucUtils.getString(requestOrg,
						"arrivalShipAgency", StringPool.BLANK);
				String lastPortCode = DanhMucUtils.getString(requestOrg,
						"lastPortCode", StringPool.BLANK);
				String nextPortCode = DanhMucUtils.getString(requestOrg,
						"nextPortCode", StringPool.BLANK);
				String departureShipAgency = DanhMucUtils.getString(requestOrg,
						"departureShipAgency", StringPool.BLANK);
				String cargoCategory = DanhMucUtils.getString(requestOrg,
						"cargoCategory", StringPool.BLANK);
				String cargoType = DanhMucUtils.getString(requestOrg,
						"cargoType", StringPool.BLANK);
				String callSign = DanhMucUtils.getString(requestOrg,
						"callSign", StringPool.BLANK);
				String anchoringPortHarbourCode = DanhMucUtils.getString(
						requestOrg, "anchoringPortHarbourCode",
						StringPool.BLANK);
				String anchoringPortWharfCode = DanhMucUtils.getString(
						requestOrg, "anchoringPortWharfCode", StringPool.BLANK);
				String shiftingPortHarbourCode = DanhMucUtils
						.getString(requestOrg, "shiftingPortHarbourCode",
								StringPool.BLANK);
				String shiftingPortWharfCode = DanhMucUtils.getString(
						requestOrg, "shiftingPortWharfCode", StringPool.BLANK);
				String portHarbourCode = ParamUtil.getString(requestOrg,
						"portHarbourCode", StringPool.BLANK);
				String shipCode = ParamUtil.getString(requestOrg, "tugboatCode",
						StringPool.BLANK);
				String tugboatCompanyCode = ParamUtil.getString(requestOrg,
						"tugboatCompanyCode", StringPool.BLANK);
				String pilotCode = ParamUtil.getString(requestOrg, "pilotCode",
						StringPool.BLANK);
				String pilotCompanyCode = ParamUtil.getString(requestOrg,
						"pilotCompanyCode", StringPool.BLANK);

				String paymentName = ParamUtil.getString(requestOrg,
						"paymentName", StringPool.BLANK);
				
				createDate = createDate + " 00:00";
				

				String strFromDate = "", strToDate = "";
				if (reportPeriod.equals("1") || reportPeriod.equals("2")) {
					if (reportPeriod.equals("1")) {
						fromDate = fromDate + " 00:00:00";
						toDate = toDate + " 23:59:59";
					} else if (reportPeriod.equals("2")) {
						fromDate = fromDate + ":00";
						toDate = toDate + ":59";
					}
					try {
						Date dFromDate = new SimpleDateFormat(
								"dd/MM/yyy HH:mm:ss").parse(fromDate);
						strFromDate = FormatData.parseDateToTring(dFromDate);
						Date dToDate = new SimpleDateFormat(
								"dd/MM/yyy HH:mm:ss").parse(toDate);
						strToDate = FormatData.parseDateToTring(dToDate);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}
				} else if (reportPeriod.equals("3") || reportPeriod.equals("4")) {
					strFromDate = fromDate + " 00:00:00";
					strToDate = toDate + " 23:59:59";
				}
				
				String reportYear = StringPool.BLANK;
				String reportMonth = StringPool.BLANK;
				if (Validator.isNotNull(strFromDate) && strFromDate.length() >= 10){
					reportYear = strFromDate.substring(0,4);
					reportMonth = strFromDate.substring(5,7);
					log.info("reportYear = " +reportYear+ " reportMonth = " + reportMonth);
				}
				
				JSONObject data2 = JSONFactoryUtil.createJSONObject();
				boolean hasDataBoolean = false;
				String UrlFile = StringPool.BLANK;
				String tenFileExport = StringPool.BLANK;
				// ?p_p_id=baocaopmnvaction_WAR_TichHopGiaoThongportlet&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_resource_id=getReportPDF&p_p_cacheability=cacheLevelPage&p_p_col_id=column-1&p_p_col_count=1
				if (resourceID.equals("getReportPDF")) {
					if (reportCode != 0 && Validator.isNotNull(maritimeCode)) {
						if (reportCode == BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG) {
							log.info("********* Vao KeHoachDieuDongExport ********* ");
							KeHoachDieuDongExport action = new KeHoachDieuDongExport();
							hasDataBoolean = action.export2Report(reportCode,
									maritimeCode, createDate, fromDate, toDate);
						} else if (reportCode == BaoCaoConstant.THONG_BAO_TINH_HINH_TAU_THUYEN_DU_KIEN_NEO_DAU_TAI_CANG) {
							log.info("********* Vao TinhHinhDuKienNeoDauExport ********* ");
							TinhHinhDuKienNeoDauExport action = new TinhHinhDuKienNeoDauExport();
							hasDataBoolean = action.export2Report(reportCode,
									maritimeCode, createDate, fromDate, toDate);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_TINH_HINH_NOP_HO_SO) {
							log.info("********* Vao TinhHinhNopHoSoExport ********* ");
							TinhHinhNopHoSoExport action = new TinhHinhNopHoSoExport();
							hasDataBoolean = action.export2Report(createDate,
									maritimeCode, fromDate, toDate);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_HO_SO_THEO_PHONG_BAN) {
							log.info("********* Vao DVCongTheoPhongBanExport ********* ");
							DVCongTheoPhongBanExport action = new DVCongTheoPhongBanExport();
							hasDataBoolean = action.export2Report(createDate,
									maritimeCode, fromDate, toDate);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG) {
							log.info("********* Vao DichVuCongExport ********* ");
							DichVuCongExport action = new DichVuCongExport();
							hasDataBoolean = action.export2Report(createDate,
									maritimeCode, fromDate, toDate);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA) {
							log.debug("********* Vao CoCheMotCuaQuocGiaNangCapExport ********* ");
							CoCheMotCuaQuocGiaNangCapExport action = new CoCheMotCuaQuocGiaNangCapExport();
							hasDataBoolean = action.export2Report(createDate,
									maritimeCode, fromDate, toDate);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KET_QUA_HOAN_THANH_THU_TUC) {
							log.debug("********* Vao HoanThanhThuTucMotCuaQuocGiaExport ********* ");
							HoanThanhThuTucMotCuaQuocGiaExport action = new HoanThanhThuTucMotCuaQuocGiaExport();
							hasDataBoolean = action.export2Report(createDate,
									null, fromDate, toDate);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_12) {
							log.info("********* Vao BAO_CAO_12 ********* ");
							Bc12Export action = new Bc12Export();
							hasDataBoolean = action.export2Report(createDate,
									maritimeCode, fromDate, toDate);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN) {
							log.info("=============== BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC12TExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_XML,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_EXPORT,
											PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BEN_CANG_KHU_CHUYEN_TAI) {
							log.info("=============== BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BEN_CANG_KHU_CHUYEN_TAI ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC15TExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BEN_CANG_KHU_CHUYEN_TAI_XML,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BEN_CANG_KHU_CHUYEN_TAI_EXPORT,
											PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BANG_DOI_TAU_BIEN) {
							log.info("=============== BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BANG_DOI_TAU_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC14TExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BANG_DOI_TAU_BIEN_XML,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BANG_DOI_TAU_BIEN_EXPORT,
											PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN) {
							log.info("=============== BAO_CAO_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							data2 = BC12BTExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN_XML,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN_EXPORT,
											PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_TAU_THUYEN_RA_VAO_CANG_BIEN) {
							log.info("=============== BAO_CAO_TAU_THUYEN_RA_VAO_CANG_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC11TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_TAU_THUYEN_RA_VAO_CANG_BIEN_XML,
											BaoCaoConstant.BAO_CAO_TAU_THUYEN_RA_VAO_CANG_BIEN_EXPORT,
											PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_PTTND_RA_VAO_CANG_BIEN) {
							log.info("=============== BAO_CAO_PTTND_RA_VAO_CANG_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC11BTExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_PTTND_RA_VAO_CANG_BIEN_XML,
											BaoCaoConstant.BAO_CAO_PTTND_RA_VAO_CANG_BIEN_EXPORT,
											PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_19) {
							log.info("=============== BAO_CAO_19 ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC19TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_PTTND_XML,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_PTTND_EXPORT,
											PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_THU_TRUONG_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN) {
							log.info("=============== BAO_CAO_THU_TRUONG_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC12SSTExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);

							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_12SST_XML,
									BaoCaoConstant.BAO_CAO_12SST_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG_HANG_NGAY) {
							log.info("=============== KE HOACH DIEU DONG TAU HANG NGAY - BC58 ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC58TExport.getModel58T(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG_HANG_NGAY_XML,
											BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG_HANG_NGAY_EXPORT,
											PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_59) {
							log.info("====== BAO CAO 59 ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC59TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_59_XML,
									BaoCaoConstant.BAO_CAO_59_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_60) {
							log.info("===== bao cao danh sach tau trong cang - bc60 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC60TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_60_XML,
									BaoCaoConstant.BAO_CAO_60_EXPORT, PDF);
						} else if (reportCode == 111) {
							log.info("===== bc58s ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC58SExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									"Mau-58s.jrxml", "Mau-58s.pdf", PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_LUOT_TAU_THUYEN_RA_VAO_CANG_BIEN) {
							log.info("===== BAO CAO 13 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC13TExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_LUOT_TAU_THUYEN_RA_VAO_CANG_BIEN_XML,
											BaoCaoConstant.BAO_CAO_LUOT_TAU_THUYEN_RA_VAO_CANG_BIEN_EXPORT,
											PDF);
						} else if (reportCode == BaoCaoConstant.THONG_KE_SAN_LUONG_TUY_BIEN) {
							log.info("===== THONG_KE_SAN_LUONG_TUY_BIEN - BAO CAO 16 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC16TExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.THONG_KE_SAN_LUONG_TUY_BIEN_XML,
											BaoCaoConstant.THONG_KE_SAN_LUONG_TUY_BIEN_EXPORT,
											PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_20) {
							log.info("===== BAO_CAO_20 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC20TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_20_XML,
									BaoCaoConstant.BAO_CAO_20_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_21) {
							log.info("===== BAO_CAO_21 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC21TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_21_XML,
									BaoCaoConstant.BAO_CAO_21_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_46) {
							log.info("===== BAO_CAO_46 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC46_47_48_52TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_46_XML,
									BaoCaoConstant.BAO_CAO_46_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_47) {
							log.info("===== BAO_CAO_47 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC46_47_48_52TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_47_XML,
									BaoCaoConstant.BAO_CAO_47_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_48) {
							log.info("===== BAO_CAO_48 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC46_47_48_52TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_48_XML,
									BaoCaoConstant.BAO_CAO_48_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_52) {
							log.info("===== BAO_CAO_52 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC46_47_48_52TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_52_XML,
									BaoCaoConstant.BAO_CAO_52_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_71) {
							log.info("===== BAO_CAO_71 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_71_XML,
									BaoCaoConstant.BAO_CAO_71_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_72) {
							log.info("===== BAO_CAO_72 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_72_XML,
									BaoCaoConstant.BAO_CAO_72_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_73) {
							log.info("===== BAO_CAO_73 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_73_XML,
									BaoCaoConstant.BAO_CAO_73_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_79) {
							log.info("===== BAO_CAO_79 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_79_XML,
									BaoCaoConstant.BAO_CAO_79_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_65) {
							log.info("===== BAO_CAO_65 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC65TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_65_XML,
									BaoCaoConstant.BAO_CAO_65_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_66) {
							log.info("===== BAO_CAO_66 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC66TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_66_XML,
									BaoCaoConstant.BAO_CAO_66_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_67) {
							log.info("===== BAO_CAO_67 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC67TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_67_XML,
									BaoCaoConstant.BAO_CAO_67_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_74) {
							log.info("===== BAO_CAO_74 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_74_XML,
									BaoCaoConstant.BAO_CAO_74_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_75) {
							log.info("===== BAO_CAO_75 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_75_XML,
									BaoCaoConstant.BAO_CAO_75_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_76) {
							log.info("===== BAO_CAO_76 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_76_XML,
									BaoCaoConstant.BAO_CAO_76_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_49) {
							log.info("===== BAO_CAO_49 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC49_50_51_55TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_49_XML,
									BaoCaoConstant.BAO_CAO_49_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_50) {
							log.info("===== BAO_CAO_50 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC49_50_51_55TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_50_XML,
									BaoCaoConstant.BAO_CAO_50_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_51) {
							log.info("===== BAO_CAO_51 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC49_50_51_55TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_51_XML,
									BaoCaoConstant.BAO_CAO_51_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_55) {
							log.info("===== BAO_CAO_55 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC49_50_51_55TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_55_XML,
									BaoCaoConstant.BAO_CAO_55_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_53) {
							log.info("===== BAO_CAO_53 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC53_54TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_53_XML,
									BaoCaoConstant.BAO_CAO_53_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_54) {
							log.info("===== BAO_CAO_54 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC53_54TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_54_XML,
									BaoCaoConstant.BAO_CAO_54_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_70) {
							log.info("===== BAO_CAO_70 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC70_78TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									anchoringPortHarbourCode,
									anchoringPortWharfCode,
									shiftingPortHarbourCode,
									shiftingPortWharfCode, strFromDate,
									strToDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_70_XML,
									BaoCaoConstant.BAO_CAO_70_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_78) {
							log.info("===== BAO_CAO_78 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC70_78TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									anchoringPortHarbourCode,
									anchoringPortWharfCode,
									shiftingPortHarbourCode,
									shiftingPortWharfCode, strFromDate,
									strToDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_78_XML,
									BaoCaoConstant.BAO_CAO_78_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_77) {
							log.info("===== BAO_CAO_77 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC77TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									anchoringPortHarbourCode,
									anchoringPortWharfCode,
									shiftingPortHarbourCode,
									shiftingPortWharfCode, strFromDate,
									strToDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_77_XML,
									BaoCaoConstant.BAO_CAO_77_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_MAU15) {
							log.info("===== BAO_CAO_MAU15 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = MauBC15Export.getModel(maritimeCode,
									portHarbourCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_MAU15_XML,
									BaoCaoConstant.BAO_CAO_MAU15_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_24) {
							log.info("===== BAO_CAO_24 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							/*data2 = BC24TExport.getModel(maritimeCode,
									shipCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);*/
							data2 = VmaItineraryScheduleLocalServiceUtil.findSoLuotLaiDat_VmaItinerarySchedule_VmaScheduleShifting(
									maritimeCode, tugboatCompanyCode, shipCode, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_24_XML,
									BaoCaoConstant.BAO_CAO_24_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_25) {
							log.info("===== BAO_CAO_25 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							/*data2 = BC25TExport.getModel(maritimeCode,
									tugboatCompanyCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);*/
							data2 = VmaItineraryScheduleLocalServiceUtil.findSoLuotLaiDat_VmaItinerarySchedule_VmaScheduleShifting(
									maritimeCode, tugboatCompanyCode, null, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_25_XML,
									BaoCaoConstant.BAO_CAO_25_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_26) {
							log.info("===== BAO_CAO_26 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							/*data2 = BC26TExport.getModel(maritimeCode,
									pilotCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);*/
							data2 = VmaItineraryScheduleLocalServiceUtil.findSoLuotHoaTieuDanTau_VmaItinerarySchedule_VmaScheduleShifting(
									maritimeCode, pilotCompanyCode, pilotCode, null, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_26_XML,
									BaoCaoConstant.BAO_CAO_26_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_27) {
							log.info("===== BAO_CAO_27 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							/*data2 = BC27TExport.getModel(maritimeCode,
									pilotCompanyCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);*/
							data2 = VmaItineraryScheduleLocalServiceUtil.findSoLuotHoaTieuDanTau_VmaItinerarySchedule_VmaScheduleShifting(
									maritimeCode, pilotCompanyCode, null, null, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_27_XML,
									BaoCaoConstant.BAO_CAO_27_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_36) {
							log.info("===== BAO_CAO_36 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC36TExport.getModel(maritimeCode,
									pilotCompanyCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_36_XML,
									BaoCaoConstant.BAO_CAO_36_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_81) {
							log.info("===== BAO_CAO_81 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_81_XML,
									BaoCaoConstant.BAO_CAO_81_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_82) {
							log.info("===== BAO_CAO_82 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_82_XML,
									BaoCaoConstant.BAO_CAO_82_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_83) {
							log.info("===== BAO_CAO_83 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_83_XML,
									BaoCaoConstant.BAO_CAO_83_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_84) {
							log.info("===== BAO_CAO_84 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_84_XML,
									BaoCaoConstant.BAO_CAO_84_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_85) {
							log.info("===== BAO_CAO_85 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_85_XML,
									BaoCaoConstant.BAO_CAO_85_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_86) {
							log.info("===== BAO_CAO_86 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_86_XML,
									BaoCaoConstant.BAO_CAO_86_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_87) {
							log.info("===== BAO_CAO_87 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_87_XML,
									BaoCaoConstant.BAO_CAO_87_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_88) {
							log.info("===== BAO_CAO_88 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_88_XML,
									BaoCaoConstant.BAO_CAO_88_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_89) {
							log.info("===== BAO_CAO_89 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_89_XML,
									BaoCaoConstant.BAO_CAO_89_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_90) {
							log.info("===== BAO_CAO_90 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_90_XML,
									BaoCaoConstant.BAO_CAO_90_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_91) {
							log.info("===== BAO_CAO_91 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_91_XML,
									BaoCaoConstant.BAO_CAO_91_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_92) {
							log.info("===== BAO_CAO_92 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_92_XML,
									BaoCaoConstant.BAO_CAO_92_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_93) {
							log.info("===== BAO_CAO_93 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_93_XML,
									BaoCaoConstant.BAO_CAO_93_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_94) {
							log.info("===== BAO_CAO_94 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_94_XML,
									BaoCaoConstant.BAO_CAO_94_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_95) {
							log.info("===== BAO_CAO_95 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_95_XML,
									BaoCaoConstant.BAO_CAO_95_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_96) {
							log.info("===== BAO_CAO_96 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_96_XML,
									BaoCaoConstant.BAO_CAO_96_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_97) {
							log.info("===== BAO_CAO_97 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_97_XML,
									BaoCaoConstant.BAO_CAO_97_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_98) {
							log.info("===== BAO_CAO_98 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_98_XML,
									BaoCaoConstant.BAO_CAO_98_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_99) {
							log.info("===== BAO_CAO_99 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_99_XML,
									BaoCaoConstant.BAO_CAO_99_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_100) {
							log.info("===== BAO_CAO_100 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_100_XML,
									BaoCaoConstant.BAO_CAO_100_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_101) {
							log.info("===== BAO_CAO_101 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_101_XML,
									BaoCaoConstant.BAO_CAO_101_EXPORT, PDF);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_105) {
							log.info("===== BAO_CAO_105 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai105(maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_105_XML,
									BaoCaoConstant.BAO_CAO_105_EXPORT, PDF);
						}
						
					}

					if (reportCode == BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG) {
						tenFileExport = BaoCaoConstant.KE_HOACH_DIEU_DONG_EXPORT;
					} else if (reportCode == BaoCaoConstant.THONG_BAO_TINH_HINH_TAU_THUYEN_DU_KIEN_NEO_DAU_TAI_CANG) {
						tenFileExport = BaoCaoConstant.THONG_BAO_TINH_HINH_DU_KIEN_NEO_DAU_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_TINH_HINH_NOP_HO_SO) {
						tenFileExport = BaoCaoConstant.BAO_CAO_THONG_KE_TINH_HINH_NOP_HS_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_HO_SO_THEO_PHONG_BAN) {
						tenFileExport = BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG_THEO_PHONG_BAN_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG) {
						tenFileExport = BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA) {
						tenFileExport = BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA_NANG_CAP_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_12) {
						tenFileExport = BaoCaoConstant.BAO_CAO_12_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_KET_QUA_HOAN_THANH_THU_TUC) {
						tenFileExport = BaoCaoConstant.BAO_CAO_KET_QUA_HOAN_THANH_THU_TUC_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN) { // <=========================
						tenFileExport = BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BEN_CANG_KHU_CHUYEN_TAI) {
						tenFileExport = BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BEN_CANG_KHU_CHUYEN_TAI_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BANG_DOI_TAU_BIEN) {
						tenFileExport = BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BANG_DOI_TAU_BIEN_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_TAU_THUYEN_RA_VAO_CANG_BIEN) {
						tenFileExport = BaoCaoConstant.BAO_CAO_TAU_THUYEN_RA_VAO_CANG_BIEN_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_PTTND_RA_VAO_CANG_BIEN) {
						tenFileExport = BaoCaoConstant.BAO_CAO_PTTND_RA_VAO_CANG_BIEN_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN) {
						tenFileExport = BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG_HANG_NGAY) {
						tenFileExport = BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG_HANG_NGAY_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_19) {
						tenFileExport = BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_PTTND_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_59) {
						tenFileExport = BaoCaoConstant.BAO_CAO_59_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_60) {
						tenFileExport = BaoCaoConstant.BAO_CAO_60_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_LUOT_TAU_THUYEN_RA_VAO_CANG_BIEN) {
						tenFileExport = BaoCaoConstant.BAO_CAO_LUOT_TAU_THUYEN_RA_VAO_CANG_BIEN_EXPORT;
					} else if (reportCode == BaoCaoConstant.THONG_KE_SAN_LUONG_TUY_BIEN) {
						tenFileExport = BaoCaoConstant.THONG_KE_SAN_LUONG_TUY_BIEN_EXPORT;
					} else if (reportCode == 111) {
						tenFileExport = "Mau-58s.pdf";
					} else if (reportCode == BaoCaoConstant.BAO_CAO_20) {
						tenFileExport = BaoCaoConstant.BAO_CAO_20_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_21) {
						tenFileExport = BaoCaoConstant.BAO_CAO_21_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_46) {
						tenFileExport = BaoCaoConstant.BAO_CAO_46_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_47) {
						tenFileExport = BaoCaoConstant.BAO_CAO_47_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_48) {
						tenFileExport = BaoCaoConstant.BAO_CAO_48_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_52) {
						tenFileExport = BaoCaoConstant.BAO_CAO_52_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_71) {
						tenFileExport = BaoCaoConstant.BAO_CAO_71_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_72) {
						tenFileExport = BaoCaoConstant.BAO_CAO_72_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_73) {
						tenFileExport = BaoCaoConstant.BAO_CAO_73_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_79) {
						tenFileExport = BaoCaoConstant.BAO_CAO_79_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_65) {
						tenFileExport = BaoCaoConstant.BAO_CAO_65_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_66) {
						tenFileExport = BaoCaoConstant.BAO_CAO_66_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_67) {
						tenFileExport = BaoCaoConstant.BAO_CAO_67_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_74) {
						tenFileExport = BaoCaoConstant.BAO_CAO_74_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_75) {
						tenFileExport = BaoCaoConstant.BAO_CAO_75_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_76) {
						tenFileExport = BaoCaoConstant.BAO_CAO_76_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_49) {
						tenFileExport = BaoCaoConstant.BAO_CAO_49_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_50) {
						tenFileExport = BaoCaoConstant.BAO_CAO_50_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_51) {
						tenFileExport = BaoCaoConstant.BAO_CAO_51_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_55) {
						tenFileExport = BaoCaoConstant.BAO_CAO_55_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_53) {
						tenFileExport = BaoCaoConstant.BAO_CAO_53_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_54) {
						tenFileExport = BaoCaoConstant.BAO_CAO_54_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_70) {
						tenFileExport = BaoCaoConstant.BAO_CAO_70_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_78) {
						tenFileExport = BaoCaoConstant.BAO_CAO_78_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_77) {
						tenFileExport = BaoCaoConstant.BAO_CAO_77_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_MAU15) {
						tenFileExport = BaoCaoConstant.BAO_CAO_MAU15_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_24) {
						tenFileExport = BaoCaoConstant.BAO_CAO_24_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_25) {
						tenFileExport = BaoCaoConstant.BAO_CAO_25_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_26) {
						tenFileExport = BaoCaoConstant.BAO_CAO_26_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_27) {
						tenFileExport = BaoCaoConstant.BAO_CAO_27_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_36) {
						tenFileExport = BaoCaoConstant.BAO_CAO_36_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_THU_TRUONG_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN) {
						tenFileExport = BaoCaoConstant.BAO_CAO_12SST_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_81) {
						tenFileExport = BaoCaoConstant.BAO_CAO_81_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_82) {
						tenFileExport = BaoCaoConstant.BAO_CAO_82_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_83) {
						tenFileExport = BaoCaoConstant.BAO_CAO_83_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_84) {
						tenFileExport = BaoCaoConstant.BAO_CAO_84_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_85) {
						tenFileExport = BaoCaoConstant.BAO_CAO_85_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_86) {
						tenFileExport = BaoCaoConstant.BAO_CAO_86_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_87) {
						tenFileExport = BaoCaoConstant.BAO_CAO_87_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_88) {
						tenFileExport = BaoCaoConstant.BAO_CAO_88_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_89) {
						tenFileExport = BaoCaoConstant.BAO_CAO_89_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_90) {
						tenFileExport = BaoCaoConstant.BAO_CAO_90_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_91) {
						tenFileExport = BaoCaoConstant.BAO_CAO_91_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_92) {
						tenFileExport = BaoCaoConstant.BAO_CAO_92_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_93) {
						tenFileExport = BaoCaoConstant.BAO_CAO_93_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_94) {
						tenFileExport = BaoCaoConstant.BAO_CAO_94_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_95) {
						tenFileExport = BaoCaoConstant.BAO_CAO_95_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_96) {
						tenFileExport = BaoCaoConstant.BAO_CAO_96_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_97) {
						tenFileExport = BaoCaoConstant.BAO_CAO_97_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_98) {
						tenFileExport = BaoCaoConstant.BAO_CAO_98_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_99) {
						tenFileExport = BaoCaoConstant.BAO_CAO_99_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_100) {
						tenFileExport = BaoCaoConstant.BAO_CAO_100_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_101) {
						tenFileExport = BaoCaoConstant.BAO_CAO_101_EXPORT;
					} else if (reportCode == BaoCaoConstant.BAO_CAO_105) {
						tenFileExport = BaoCaoConstant.BAO_CAO_105_EXPORT;
					}

					UrlFile = request.getContextPath() + "/export/"
							+ tenFileExport;
					String UrlFileDownLoad = UrlFile.replace(".pdfs", ".pdf");

					if (hasDataBoolean) {
						data.put("url", UrlFile);
						data.put("download", UrlFileDownLoad);
						data.put("data", data2);
					} else {
						data.put("url", StringPool.BLANK);
						data.put("download", StringPool.BLANK);
					}
					return writeJSON(resourceRequest, resourceResponse, data);
				}
				// ?p_p_id=baocaopmnvaction_WAR_TichHopGiaoThongportlet&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_resource_id=getReportEXCEL&p_p_cacheability=cacheLevelPage&p_p_col_id=column-1&p_p_col_count=1
				else if (resourceID.equals("getReportEXCEL")) {
					if (reportCode != 0 && Validator.isNotNull(maritimeCode)) {
						long time = new Date().getTime();
						tenFileExport = time + ".xls";

						if (reportCode == BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG) {
							log.info("********* Vao KeHoachDieuDongExport ********* ");
							KeHoachDieuDongExport action = new KeHoachDieuDongExport();
							hasDataBoolean = action.export2Excel(reportCode,
									maritimeCode, createDate, fromDate, toDate,
									tenFileExport);
						} else if (reportCode == BaoCaoConstant.THONG_BAO_TINH_HINH_TAU_THUYEN_DU_KIEN_NEO_DAU_TAI_CANG) {
							log.info("********* Vao TinhHinhDuKienNeoDauExport ********* ");
							TinhHinhDuKienNeoDauExport action = new TinhHinhDuKienNeoDauExport();
							hasDataBoolean = action.export2Excel(reportCode,
									maritimeCode, createDate, fromDate, toDate,
									tenFileExport);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_TINH_HINH_NOP_HO_SO) {
							log.info("********* Vao TinhHinhNopHoSoExport ********* ");
							TinhHinhNopHoSoExport action = new TinhHinhNopHoSoExport();
							hasDataBoolean = action.export2Excel(createDate,
									maritimeCode, fromDate, toDate,
									tenFileExport);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_HO_SO_THEO_PHONG_BAN) {
							log.info("********* Vao DVCongTheoPhongBanExport ********* ");
							DVCongTheoPhongBanExport action = new DVCongTheoPhongBanExport();
							hasDataBoolean = action.export2Excel(createDate,
									maritimeCode, fromDate, toDate,
									tenFileExport);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_THONG_KE_DICH_VU_CONG) {
							log.info("********* Vao DichVuCongExport ********* ");
							DichVuCongExport action = new DichVuCongExport();
							hasDataBoolean = action.export2Excel(createDate,
									maritimeCode, fromDate, toDate,
									tenFileExport);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KET_QUA_THUC_HIEN_CO_CHE_MOT_CUA_QUOC_GIA) {
							log.debug("********* Vao CoCheMotCuaQuocGiaNangCapExport ********* ");
							CoCheMotCuaQuocGiaNangCapExport action = new CoCheMotCuaQuocGiaNangCapExport();
							hasDataBoolean = action.export2Excel(createDate,
									maritimeCode, fromDate, toDate,
									tenFileExport);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KET_QUA_HOAN_THANH_THU_TUC) {
							log.debug("********* Vao HoanThanhThuTucMotCuaQuocGiaExport ********* ");
							HoanThanhThuTucMotCuaQuocGiaExport action = new HoanThanhThuTucMotCuaQuocGiaExport();
							hasDataBoolean = action
									.export2Excel(maritimeCode, createDate,
											fromDate, toDate, tenFileExport);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_12) {
							log.info("********* Vao BAO_CAO_12 ********* ");
							Bc12Export action = new Bc12Export();
							hasDataBoolean = action.export2Excel(createDate,
									maritimeCode, fromDate, toDate,
									tenFileExport);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN) {
							log.info("=============== BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC12TExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_XML,
											tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BEN_CANG_KHU_CHUYEN_TAI) {
							log.info("=============== BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BEN_CANG_KHU_CHUYEN_TAI ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC15TExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BEN_CANG_KHU_CHUYEN_TAI_XML,
											tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BANG_DOI_TAU_BIEN) {
							log.info("=============== BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BANG_DOI_TAU_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC14TExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_BANG_DOI_TAU_BIEN_XML,
											tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN) {
							log.info("=============== BAO_CAO_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							data2 = BC12BTExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN_XML,
											tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_TAU_THUYEN_RA_VAO_CANG_BIEN) {
							log.info("=============== BAO_CAO_TAU_THUYEN_RA_VAO_CANG_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC11TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_TAU_THUYEN_RA_VAO_CANG_BIEN_XML,
											tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_PTTND_RA_VAO_CANG_BIEN) {
							log.info("=============== BAO_CAO_PTTND_RA_VAO_CANG_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC11BTExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_PTTND_RA_VAO_CANG_BIEN_XML,
											tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_19) {
							log.info("=============== BAO_CAO_19 ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC19TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KHOI_LUONG_HANG_HOA_HANH_KHACH_THONG_QUA_CANG_BIEN_PTTND_XML,
											tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_THU_TRUONG_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN) {
							log.info("=============== BAO_CAO_THU_TRUONG_KHOI_LUONG_HANG_HOA_LUOT_TAU_THUYEN_QUA_CANG_BIEN ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC12SSTExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);

							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_12SST_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG_HANG_NGAY) {
							log.info("=============== KE HOACH DIEU DONG TAU HANG NGAY - BC58 ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC58TExport.getModel58T(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_KE_HOACH_DIEU_DONG_HANG_NGAY_XML,
											tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_59) {
							log.info("====== BAO CAO 59 ============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC59TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_59_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_60) {
							log.info("===== bao cao danh sach tau trong cang - bc60 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC60TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_60_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == 111) {
							log.info("===== bc58s ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC58SExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									"Mau-58s.jrxml", tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_LUOT_TAU_THUYEN_RA_VAO_CANG_BIEN) {
							log.info("===== BAO CAO 13 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC13TExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.BAO_CAO_LUOT_TAU_THUYEN_RA_VAO_CANG_BIEN_XML,
											tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.THONG_KE_SAN_LUONG_TUY_BIEN) {
							log.info("===== THONG_KE_SAN_LUONG_TUY_BIEN - BAO CAO 16 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC16TExport.getModel(maritimeCode,
									fromDate, toDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action
									.export2Report(
											data2,
											BaoCaoConstant.THONG_KE_SAN_LUONG_TUY_BIEN_XML,
											tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_20) {
							log.info("===== BAO_CAO_20 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC20TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_20_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_21) {
							log.info("===== BAO_CAO_21 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC21TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_21_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_46) {
							log.info("===== BAO_CAO_46 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC46_47_48_52TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_46_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_47) {
							log.info("===== BAO_CAO_47 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC46_47_48_52TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_47_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_48) {
							log.info("===== BAO_CAO_48 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC46_47_48_52TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_48_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_52) {
							log.info("===== BAO_CAO_52 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC46_47_48_52TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_52_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_71) {
							log.info("===== BAO_CAO_71 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_71_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_72) {
							log.info("===== BAO_CAO_72 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_72_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_73) {
							log.info("===== BAO_CAO_73 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_73_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_79) {
							log.info("===== BAO_CAO_79 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_79_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_65) {
							log.info("===== BAO_CAO_65 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC65TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_65_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_66) {
							log.info("===== BAO_CAO_66 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC66TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_66_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_67) {
							log.info("===== BAO_CAO_67 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC67TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_67_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_74) {
							log.info("===== BAO_CAO_74 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_74_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_75) {
							log.info("===== BAO_CAO_75 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_75_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_76) {
							log.info("===== BAO_CAO_76 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC71_72_73_79TExport.getModel(maritimeCode,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_76_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_49) {
							log.info("===== BAO_CAO_49 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC49_50_51_55TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_49_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_50) {
							log.info("===== BAO_CAO_50 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC49_50_51_55TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_50_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_51) {
							log.info("===== BAO_CAO_51 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC49_50_51_55TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_51_XML,
									BaoCaoConstant.BAO_CAO_51_EXPORT, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_55) {
							log.info("===== BAO_CAO_55 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC49_50_51_55TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_55_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_53) {
							log.info("===== BAO_CAO_53 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC53_54TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_53_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_54) {
							log.info("===== BAO_CAO_54 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC53_54TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									strFromDate, strToDate, createDate,
									reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_54_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_70) {
							log.info("===== BAO_CAO_70 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC70_78TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									anchoringPortHarbourCode,
									anchoringPortWharfCode,
									shiftingPortHarbourCode,
									shiftingPortWharfCode, strFromDate,
									strToDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_70_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_78) {
							log.info("===== BAO_CAO_78 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC70_78TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									anchoringPortHarbourCode,
									anchoringPortWharfCode,
									shiftingPortHarbourCode,
									shiftingPortWharfCode, strFromDate,
									strToDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_78_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_77) {
							log.info("===== BAO_CAO_77 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC77TExport.getModel(maritimeCode,
									nameOfShip, imoNumber, registryNumber,
									vrCode, flagStateOfShip, from_gt, to_gt,
									from_dwt, to_dwt, from_loa, to_loa,
									lastPortCode, nextPortCode,
									arrivalShipAgency, departureShipAgency,
									cargoType, cargoCategory, callSign,
									anchoringPortHarbourCode,
									anchoringPortWharfCode,
									shiftingPortHarbourCode,
									shiftingPortWharfCode, strFromDate,
									strToDate, createDate, reportPeriod,
									reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_77_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_MAU15) {
							log.info("===== BAO_CAO_MAU15 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = MauBC15Export.getModel(maritimeCode,
									portHarbourCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_MAU15_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_24) {
							log.info("===== BAO_CAO_24 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							/*data2 = BC24TExport.getModel(maritimeCode,
									shipCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);*/
							data2 = VmaItineraryScheduleLocalServiceUtil.findSoLuotLaiDat_VmaItinerarySchedule_VmaScheduleShifting(
									maritimeCode, tugboatCompanyCode, shipCode, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_24_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_25) {
							log.info("===== BAO_CAO_25 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							/*data2 = BC25TExport.getModel(maritimeCode,
									tugboatCompanyCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);*/
							data2 = VmaItineraryScheduleLocalServiceUtil.findSoLuotLaiDat_VmaItinerarySchedule_VmaScheduleShifting(
									maritimeCode, tugboatCompanyCode, null, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_25_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_26) {
							log.info("===== BAO_CAO_26 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							/*data2 = BC26TExport.getModel(maritimeCode,
									pilotCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);*/
							data2 = VmaItineraryScheduleLocalServiceUtil.findSoLuotHoaTieuDanTau_VmaItinerarySchedule_VmaScheduleShifting(
									maritimeCode, pilotCompanyCode, pilotCode, null, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_26_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_27) {
							log.info("===== BAO_CAO_27 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							/*data2 = BC27TExport.getModel(maritimeCode,
									pilotCompanyCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);*/
							data2 = VmaItineraryScheduleLocalServiceUtil.findSoLuotHoaTieuDanTau_VmaItinerarySchedule_VmaScheduleShifting(
									maritimeCode, pilotCompanyCode, null, null, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_27_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_36) {
							log.info("===== BAO_CAO_36 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();

							data2 = BC36TExport.getModel(maritimeCode,
									pilotCompanyCode, strFromDate, strToDate,
									createDate, reportPeriod, reportMadeBy);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_36_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_81) {
							log.info("===== BAO_CAO_81 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_81_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_82) {
							log.info("===== BAO_CAO_82 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_82_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_83) {
							log.info("===== BAO_CAO_83 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_83_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_84) {
							log.info("===== BAO_CAO_84 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_84_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_85) {
							log.info("===== BAO_CAO_85 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_85_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_86) {
							log.info("===== BAO_CAO_86 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_86_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_87) {
							log.info("===== BAO_CAO_87 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_87_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_88) {
							log.info("===== BAO_CAO_88 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_88_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_89) {
							log.info("===== BAO_CAO_89 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_89_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_90) {
							log.info("===== BAO_CAO_90 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_90_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_91) {
							log.info("===== BAO_CAO_91 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_91_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_92) {
							log.info("===== BAO_CAO_92 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_92_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_93) {
							log.info("===== BAO_CAO_93 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_93_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_94) {
							log.info("===== BAO_CAO_94 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_94_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_95) {
							log.info("===== BAO_CAO_95 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_95_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_96) {
							log.info("===== BAO_CAO_96 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_96_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_97) {
							log.info("===== BAO_CAO_97 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_97_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_98) {
							log.info("===== BAO_CAO_98 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_98_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_99) {
							log.info("===== BAO_CAO_99 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_99_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_100) {
							log.info("===== BAO_CAO_100 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_100_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_101) {
							log.info("===== BAO_CAO_101 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai81(reportCode, paymentName, maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_101_XML,
									tenFileExport, EXCEL);
						} else if (reportCode == BaoCaoConstant.BAO_CAO_105) {
							log.info("===== BAO_CAO_105 ==============");
							BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
							
							data2 = VmaTransactionSlipLocalServiceUtil.findThongKeBienLai105(maritimeCode, createDate, reportUser, reportPeriod, reportYear, reportMonth, strFromDate, strToDate, -1, -1);
							hasDataBoolean = action.export2Report(data2,
									BaoCaoConstant.BAO_CAO_105_XML,
									tenFileExport, EXCEL);
						}
					}

					UrlFile = request.getContextPath() + "/export/"
							+ tenFileExport;

					if (hasDataBoolean) {
						data.put("url", UrlFile);
						data.put("download", UrlFile);

						if (userPort != null) {
							VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
									.getUserId(),
									userSign != null ? userSign.getModifyuser()
											: StringPool.BLANK,
									LogsConstant.EXPORT, StringPool.BLANK,
									String.valueOf(reportCode), tenFileExport);
						}
					} else {
						data.put("url", StringPool.BLANK);
						data.put("download", StringPool.BLANK);
					}
					return writeJSON(resourceRequest, resourceResponse, data);
				} else {
					super.serveResource(resourceRequest, resourceResponse);
				}

			} catch (Exception e) {
				try {
					throw new Exception(e.getMessage());
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}
		} else {
			log.info("========== Permission Denied: Không cấu hình phần mềm nghiệp vụ.");
		}
		return super.serveResource(resourceRequest, resourceResponse);
	}

}
