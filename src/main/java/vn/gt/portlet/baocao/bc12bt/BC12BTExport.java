package vn.gt.portlet.baocao.bc12bt;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;
@Slf4j
public class BC12BTExport {

	
	private final static int DIV = 2;

	public static JSONObject getModel(String maritimeCode, String fromDate,
			String toDate, String createDate, String reportPeriod,
			String reportMadeBy) throws ParseException, SystemException {
		Date _fromDate = FormatData.formatDDMMYYYY.parse(fromDate);
		Calendar fromCal = Calendar.getInstance();
		fromCal.setTime(_fromDate);
		int fromYear = fromCal.get(Calendar.YEAR);
		int fromMonth = fromCal.get(Calendar.MONTH) + 1;
		int fromDay = fromCal.get(Calendar.DAY_OF_MONTH);

		Date _toDate = FormatData.formatDDMMYYYY.parse(toDate);
		Calendar toCal = Calendar.getInstance();
		toCal.setTime(_toDate);
		int toYear = toCal.get(Calendar.YEAR);
		int toMonth = toCal.get(Calendar.MONTH) + 1;
		int toDay = toCal.get(Calendar.DAY_OF_MONTH);

		NumberFormat formatter = new DecimalFormat("#0.00");

		String thangBaoCao_startDate = String.valueOf(fromYear)
				+ ((fromMonth) < 10 ? ("-0" + String.valueOf(fromMonth)) : "-"
						+ String.valueOf(fromMonth)) + "-01";
		String thangBaoCao_endDate = String.valueOf(toYear)
				+ ((toMonth) < 10 ? ("-0" + String.valueOf(toMonth)) : "-"
						+ String.valueOf(toMonth))
				+ "-"
				+ String.valueOf(BaoCaoBussinessUtils.countDayByMonthAndYear(
						toMonth, toYear));

		String luyKeCungKyNamTruoc_startDate = String.valueOf(fromYear - 1)
				+ "-01-01";
		String luyKeCungKyNamTruoc_endDate = String.valueOf(toYear - 1)
				+ ((toMonth) < 10 ? ("-0" + String.valueOf(toMonth)) : "-"
						+ String.valueOf(toMonth))
				+ "-"
				+ String.valueOf(BaoCaoBussinessUtils.countDayByMonthAndYear(
						toMonth, toYear - 1));

		String thangBaoCaoNamTruoc_startDate = String.valueOf(fromYear - 1)
				+ ((fromMonth) < 10 ? ("-0" + String.valueOf(fromMonth)) : "-"
						+ String.valueOf(fromMonth)) + "-01";
		String thangBaoCaoNamTruoc_endDate = String.valueOf(toYear - 1)
				+ ((toMonth) < 10 ? ("-0" + String.valueOf(toMonth)) : "-"
						+ String.valueOf(toMonth))
				+ "-"
				+ String.valueOf(BaoCaoBussinessUtils.countDayByMonthAndYear(
						toMonth, toYear - 1));

		// =============================== Luot_HH
		BC12BTLuotHHModel thangBaoCao_bc12btLuotHHModel = VmaItineraryLocalServiceUtil
				.getModelMau12BTLuotHH(maritimeCode, thangBaoCao_startDate,
						thangBaoCao_endDate);
		BC12BTLuotHHModel thangBaoCaoNamTruoc_bc12btLuotHHModel = VmaItineraryLocalServiceUtil
				.getModelMau12BTLuotHH(maritimeCode,
						thangBaoCaoNamTruoc_startDate,
						thangBaoCaoNamTruoc_endDate);
		BC12BTLuotHHModel soVoiCungKy_bc12btLuotHHModel = calculate(
				thangBaoCaoNamTruoc_bc12btLuotHHModel,
				thangBaoCao_bc12btLuotHHModel, DIV);
		BC12BTLuotHHModel luyKeDauNamDenHetThangBC_bc12btLuotHHModel = VmaItineraryLocalServiceUtil
				.getModelMau12BTLuotHH(maritimeCode, String.valueOf(toYear)
						+ "-01-01", thangBaoCao_endDate);
		BC12BTLuotHHModel luyKeCungKyNamTruoc_bc12btLuotHHModel = VmaItineraryLocalServiceUtil
				.getModelMau12BTLuotHH(maritimeCode,
						luyKeCungKyNamTruoc_startDate,
						luyKeCungKyNamTruoc_endDate);
		BC12BTLuotHHModel luyKeSoVoiCungKy_bc12btLuotHHModel = calculate(
				luyKeCungKyNamTruoc_bc12btLuotHHModel,
				luyKeDauNamDenHetThangBC_bc12btLuotHHModel, DIV);

		// ========================== Khu_Cang
		List<BC12BTKhuCangModel> thangBaoCao_bc12btKhuCangModels = DmPortRegionLocalServiceUtil
				.getModelMau12BTKhuCang(maritimeCode, thangBaoCao_startDate,
						thangBaoCao_endDate);
		List<BC12BTKhuCangModel> thangBaoCaoNamTruoc_bc12btKhuCangModels = DmPortRegionLocalServiceUtil
				.getModelMau12BTKhuCang(maritimeCode,
						thangBaoCaoNamTruoc_startDate,
						thangBaoCaoNamTruoc_endDate);
		List<BC12BTKhuCangModel> soVoiCungKy_bc12btKhuCangModels = calculate2(
				thangBaoCaoNamTruoc_bc12btKhuCangModels,
				thangBaoCao_bc12btKhuCangModels, DIV);
		List<BC12BTKhuCangModel> luyKeDauNamDenHetThangBC_bc12btKhuCangModels = DmPortRegionLocalServiceUtil
				.getModelMau12BTKhuCang(maritimeCode, String.valueOf(toYear)
						+ "-01-01", thangBaoCao_endDate);
		List<BC12BTKhuCangModel> luyKeCungKyNamTruoc_bc12btKhuCangModels = DmPortRegionLocalServiceUtil
				.getModelMau12BTKhuCang(maritimeCode,
						luyKeCungKyNamTruoc_startDate,
						luyKeCungKyNamTruoc_endDate);
		List<BC12BTKhuCangModel> luyKeSoVoiCungKy_bc12btKhuCangModels = calculate2(
				luyKeCungKyNamTruoc_bc12btKhuCangModels,
				luyKeDauNamDenHetThangBC_bc12btKhuCangModels, DIV);

		// ========================== Vinalines
		List<BC12BTVinalinesModel> thangBaoCao_bc12btVinalinesModels = DmPortWharfLocalServiceUtil
				.getModelMau12BTVinalines(maritimeCode, thangBaoCao_startDate,
						thangBaoCao_endDate);
		List<BC12BTVinalinesModel> thangBaoCaoNamTruoc_bc12btVinalinesModels = DmPortWharfLocalServiceUtil
				.getModelMau12BTVinalines(maritimeCode,
						thangBaoCaoNamTruoc_startDate,
						thangBaoCaoNamTruoc_endDate);
		List<BC12BTVinalinesModel> soVoiCungKy_bc12btVinalinesModels = calculate3(
				thangBaoCaoNamTruoc_bc12btVinalinesModels,
				thangBaoCao_bc12btVinalinesModels, DIV);
		List<BC12BTVinalinesModel> luyKeDauNamDenHetThangBC_bc12btVinalinesModels = DmPortWharfLocalServiceUtil
				.getModelMau12BTVinalines(maritimeCode, String.valueOf(toYear)
						+ "-01-01", thangBaoCao_endDate);
		List<BC12BTVinalinesModel> luyKeCungKyNamTruoc_bc12btVinalinesModels = DmPortWharfLocalServiceUtil
				.getModelMau12BTVinalines(maritimeCode,
						luyKeCungKyNamTruoc_startDate,
						luyKeCungKyNamTruoc_endDate);
		List<BC12BTVinalinesModel> luyKeSoVoiCungKy_bc12btVinalinesModels = calculate3(
				luyKeCungKyNamTruoc_bc12btVinalinesModels,
				luyKeDauNamDenHetThangBC_bc12btVinalinesModels, DIV);
		// =====================================

		JSONObject c = object2Json(thangBaoCao_bc12btLuotHHModel, "C");
		JSONObject d = object2Json(thangBaoCaoNamTruoc_bc12btLuotHHModel, "D");
		JSONObject e = object2Json(soVoiCungKy_bc12btLuotHHModel, "E");
		JSONObject f = object2Json(luyKeDauNamDenHetThangBC_bc12btLuotHHModel,
				"F");
		JSONObject g = object2Json(luyKeCungKyNamTruoc_bc12btLuotHHModel, "G");
		JSONObject h = object2Json(luyKeSoVoiCungKy_bc12btLuotHHModel, "H");

		JSONObject result = BaoCaoBussinessUtils.mergeJson(c, d, e, f, g, h);

		// ====================================== khoiLuongHH
		// Neu khong cai nao co du lieu thi sao
		int sizeKhuCang[] = { thangBaoCao_bc12btKhuCangModels.size(),
				thangBaoCaoNamTruoc_bc12btKhuCangModels.size(),
				soVoiCungKy_bc12btKhuCangModels.size(),
				luyKeDauNamDenHetThangBC_bc12btKhuCangModels.size(),
				luyKeCungKyNamTruoc_bc12btKhuCangModels.size(),
				luyKeSoVoiCungKy_bc12btKhuCangModels.size() };
		int max = 0;
		for (int count = 0; count < sizeKhuCang.length; count++) {
			if (sizeKhuCang[count] > max) {
				max = sizeKhuCang[count];
			}
		}

		double tong_khoi_luong_hang_thangbc = 0, tong_khoi_luong_hang_thangbcnamtruoc = 0, tong_khoi_luong_hang_sovoicungky = 0, tong_khoi_luong_hang_luykedaunamdenhetthangbc = 0, tong_khoi_luong_hang_luykecungkynamtruoc = 0, tong_khoi_luong_hang_luykesovoicungky = 0;
		JSONArray arrayKhoiLuongHang = JSONFactoryUtil.createJSONArray();

		for (int count = 0; count < max; count++) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			try {
				obj.put("B",
						luyKeDauNamDenHetThangBC_bc12btKhuCangModels.get(count)
								.getPortRegionName());
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("C",
						thangBaoCao_bc12btKhuCangModels.get(count)
								.getKhoi_luong_hang() >= 0 ? formatter
								.format(thangBaoCao_bc12btKhuCangModels.get(
										count).getKhoi_luong_hang()) : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("D",
						thangBaoCaoNamTruoc_bc12btKhuCangModels.get(count)
								.getKhoi_luong_hang() >= 0 ? formatter
								.format(thangBaoCaoNamTruoc_bc12btKhuCangModels
										.get(count).getKhoi_luong_hang())
								: null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("E",
						soVoiCungKy_bc12btKhuCangModels.get(count)
								.getKhoi_luong_hang() >= 0 ? formatter
								.format(soVoiCungKy_bc12btKhuCangModels.get(
										count).getKhoi_luong_hang()) : null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("F",
						luyKeDauNamDenHetThangBC_bc12btKhuCangModels.get(count)
								.getKhoi_luong_hang() >= 0 ? formatter
								.format(luyKeDauNamDenHetThangBC_bc12btKhuCangModels
										.get(count).getKhoi_luong_hang())
								: null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("G",
						luyKeCungKyNamTruoc_bc12btKhuCangModels.get(count)
								.getKhoi_luong_hang() >= 0 ? formatter
								.format(luyKeCungKyNamTruoc_bc12btKhuCangModels
										.get(count).getKhoi_luong_hang())
								: null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("H",
						luyKeSoVoiCungKy_bc12btKhuCangModels.get(count)
								.getKhoi_luong_hang() >= 0 ? formatter
								.format(luyKeSoVoiCungKy_bc12btKhuCangModels
										.get(count).getKhoi_luong_hang())
								: null);
			} catch (Exception ex) {
				// nothing to do
			}
			tong_khoi_luong_hang_thangbc += thangBaoCao_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() >= 0 ? thangBaoCao_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() : 0;
			tong_khoi_luong_hang_thangbcnamtruoc += thangBaoCaoNamTruoc_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() >= 0 ? thangBaoCaoNamTruoc_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() : 0;
			tong_khoi_luong_hang_sovoicungky += soVoiCungKy_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() >= 0 ? soVoiCungKy_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() : 0;
			tong_khoi_luong_hang_luykedaunamdenhetthangbc += luyKeDauNamDenHetThangBC_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() >= 0 ? luyKeDauNamDenHetThangBC_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() : 0;
			tong_khoi_luong_hang_luykecungkynamtruoc += luyKeCungKyNamTruoc_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() >= 0 ? luyKeCungKyNamTruoc_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() : 0;
			tong_khoi_luong_hang_luykesovoicungky += luyKeSoVoiCungKy_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() >= 0 ? luyKeSoVoiCungKy_bc12btKhuCangModels
					.get(count).getKhoi_luong_hang() : 0;

			if (count == max) {
				result.put("C27",
						formatter.format(tong_khoi_luong_hang_thangbc));
				result.put("D27",
						formatter.format(tong_khoi_luong_hang_thangbcnamtruoc));
				result.put("E27",
						formatter.format(tong_khoi_luong_hang_sovoicungky));
				result.put("F27", formatter
						.format(tong_khoi_luong_hang_luykedaunamdenhetthangbc));
				result.put("G27", formatter
						.format(tong_khoi_luong_hang_luykecungkynamtruoc));
				result.put("H27",
						formatter.format(tong_khoi_luong_hang_luykesovoicungky));
			}

			arrayKhoiLuongHang.put(obj);
		}
		result.put("Bang1", arrayKhoiLuongHang);

		// ===================//============================================
		double luot_tau_thuyen_thangbc = 0, luot_tau_thuyen_daunamdenhetthangbc = 0, luot_tau_thuyen_thangbcnamtruoc = 0, luot_tau_thuyen_sovoicungky = 0, luot_tau_thuyen_luykedaunamdenhetthangbc = 0, luot_tau_thuyen_luykecungkynamtruoc = 0, luot_tau_thuyen_luykesovoicungky = 0;
		JSONArray arrayLuotTauThuyen = JSONFactoryUtil.createJSONArray();

		for (int count = 0; count < max; count++) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			try {
				obj.put("B",
						luyKeDauNamDenHetThangBC_bc12btKhuCangModels.get(count)
								.getPortRegionName());
			} catch (Exception ex) {

			}
			try {
				obj.put("C", thangBaoCao_bc12btKhuCangModels.get(count)
						.getLuot_tau() >= 0 ? thangBaoCao_bc12btKhuCangModels
						.get(count).getLuot_tau() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("D",
						thangBaoCaoNamTruoc_bc12btKhuCangModels.get(count)
								.getLuot_tau() >= 0 ? thangBaoCaoNamTruoc_bc12btKhuCangModels
								.get(count).getLuot_tau() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("E", soVoiCungKy_bc12btKhuCangModels.get(count)
						.getLuot_tau() >= 0 ? soVoiCungKy_bc12btKhuCangModels
						.get(count).getLuot_tau() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("F",
						luyKeDauNamDenHetThangBC_bc12btKhuCangModels.get(count)
								.getLuot_tau() >= 0 ? luyKeDauNamDenHetThangBC_bc12btKhuCangModels
								.get(count).getLuot_tau() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("G",
						luyKeCungKyNamTruoc_bc12btKhuCangModels.get(count)
								.getLuot_tau() >= 0 ? luyKeCungKyNamTruoc_bc12btKhuCangModels
								.get(count).getLuot_tau() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("H",
						luyKeSoVoiCungKy_bc12btKhuCangModels.get(count)
								.getLuot_tau() >= 0 ? luyKeSoVoiCungKy_bc12btKhuCangModels
								.get(count).getLuot_tau() : null);
			} catch (Exception ex) {

			}
			try {
				luot_tau_thuyen_thangbc += thangBaoCao_bc12btKhuCangModels.get(
						count).getLuot_tau() >= 0 ? thangBaoCao_bc12btKhuCangModels
						.get(count).getLuot_tau() : 0;
			} catch (Exception ex) {

			}
			try {
				luot_tau_thuyen_thangbcnamtruoc += thangBaoCaoNamTruoc_bc12btKhuCangModels
						.get(count).getLuot_tau() >= 0 ? thangBaoCaoNamTruoc_bc12btKhuCangModels
						.get(count).getLuot_tau() : 0;
			} catch (Exception ex) {

			}
			try {
				luot_tau_thuyen_sovoicungky += soVoiCungKy_bc12btKhuCangModels
						.get(count).getLuot_tau() >= 0 ? soVoiCungKy_bc12btKhuCangModels
						.get(count).getLuot_tau() : 0;
			} catch (Exception ex) {

			}
			try {
				luot_tau_thuyen_luykedaunamdenhetthangbc += luyKeDauNamDenHetThangBC_bc12btKhuCangModels
						.get(count).getLuot_tau() >= 0 ? luyKeDauNamDenHetThangBC_bc12btKhuCangModels
						.get(count).getLuot_tau() : 0;
			} catch (Exception ex) {

			}
			try {
				luot_tau_thuyen_luykecungkynamtruoc += luyKeCungKyNamTruoc_bc12btKhuCangModels
						.get(count).getLuot_tau() >= 0 ? luyKeCungKyNamTruoc_bc12btKhuCangModels
						.get(count).getLuot_tau() : 0;
			} catch (Exception ex) {

			}
			try {
				luot_tau_thuyen_luykesovoicungky += luyKeSoVoiCungKy_bc12btKhuCangModels
						.get(count).getLuot_tau() >= 0 ? luyKeSoVoiCungKy_bc12btKhuCangModels
						.get(count).getLuot_tau() : 0;
			} catch (Exception ex) {

			}

			if (count == max) {
				result.put("C28", luot_tau_thuyen_thangbc);
				result.put("D28", luot_tau_thuyen_thangbcnamtruoc);
				result.put("E28", luot_tau_thuyen_sovoicungky);
				result.put("F28", luot_tau_thuyen_luykedaunamdenhetthangbc);
				result.put("G28", luot_tau_thuyen_luykecungkynamtruoc);
				result.put("H28", luot_tau_thuyen_luykesovoicungky);
			}

			arrayLuotTauThuyen.put(obj);
		}
		result.put("Bang2", arrayLuotTauThuyen);

		// ========================= Vinalines =======================
		int sizeVinalines[] = { thangBaoCao_bc12btVinalinesModels.size(),
				thangBaoCaoNamTruoc_bc12btVinalinesModels.size(),
				soVoiCungKy_bc12btVinalinesModels.size(),
				luyKeDauNamDenHetThangBC_bc12btVinalinesModels.size(),
				luyKeCungKyNamTruoc_bc12btVinalinesModels.size(),
				luyKeSoVoiCungKy_bc12btVinalinesModels.size() };
		max = 0;
		for (int count = 0; count < sizeVinalines.length; count++) {
			if (sizeVinalines[count] > max) {
				max = sizeVinalines[count];
			}
		}

		JSONArray arrayHangVinalines = JSONFactoryUtil.createJSONArray();
		for (int count = 0; count < max; count++) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			try {
				obj.put("B1", luyKeDauNamDenHetThangBC_bc12btVinalinesModels
						.get(count).getTen_cang());
			} catch (Exception ex) {

			}
			try {
				obj.put("C2",
						thangBaoCao_bc12btVinalinesModels.get(count)
								.getHang_pttnd() >= 0 ? formatter
								.format(thangBaoCao_bc12btVinalinesModels.get(
										count).getHang_pttnd()) : null);
				obj.put("C3",
						thangBaoCao_bc12btVinalinesModels.get(count)
								.getHang_tau_bien() >= 0 ? formatter
								.format(thangBaoCao_bc12btVinalinesModels.get(
										count).getHang_tau_bien()) : null);
				double tong2 = BaoCaoBussinessUtils.sum1(
						thangBaoCao_bc12btVinalinesModels.get(count)
								.getHang_pttnd(),
						thangBaoCao_bc12btVinalinesModels.get(count)
								.getHang_tau_bien());
				obj.put("C1", tong2 >= 0 ? formatter.format(tong2) : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("D2",
						thangBaoCaoNamTruoc_bc12btVinalinesModels.get(count)
								.getHang_pttnd() >= 0 ? formatter
								.format(thangBaoCaoNamTruoc_bc12btVinalinesModels
										.get(count).getHang_pttnd())
								: null);
				obj.put("D3",
						thangBaoCaoNamTruoc_bc12btVinalinesModels.get(count)
								.getHang_tau_bien() >= 0 ? formatter
								.format(thangBaoCaoNamTruoc_bc12btVinalinesModels
										.get(count).getHang_tau_bien())
								: null);
				double tong3 = BaoCaoBussinessUtils.sum1(
						thangBaoCaoNamTruoc_bc12btVinalinesModels.get(count)
								.getHang_pttnd(),
						thangBaoCaoNamTruoc_bc12btVinalinesModels.get(count)
								.getHang_tau_bien());
				obj.put("D1", tong3 >= 0 ? formatter.format(tong3) : null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("E2",
						soVoiCungKy_bc12btVinalinesModels.get(count)
								.getHang_pttnd() >= 0 ? formatter
								.format(soVoiCungKy_bc12btVinalinesModels.get(
										count).getHang_pttnd()) : null);
				obj.put("E3",
						soVoiCungKy_bc12btVinalinesModels.get(count)
								.getHang_tau_bien() >= 0 ? formatter
								.format(soVoiCungKy_bc12btVinalinesModels.get(
										count).getHang_tau_bien()) : null);
				double tong4 = BaoCaoBussinessUtils.sum1(
						soVoiCungKy_bc12btVinalinesModels.get(count)
								.getHang_pttnd(),
						soVoiCungKy_bc12btVinalinesModels.get(count)
								.getHang_tau_bien());
				obj.put("E1", tong4 >= 0 ? formatter.format(tong4) : null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("F2",
						luyKeDauNamDenHetThangBC_bc12btVinalinesModels.get(
								count).getHang_pttnd() >= 0 ? formatter
								.format(luyKeDauNamDenHetThangBC_bc12btVinalinesModels
										.get(count).getHang_pttnd())
								: null);
				obj.put("F3",
						luyKeDauNamDenHetThangBC_bc12btVinalinesModels.get(
								count).getHang_tau_bien() >= 0 ? formatter
								.format(luyKeDauNamDenHetThangBC_bc12btVinalinesModels
										.get(count).getHang_tau_bien())
								: null);
				double tong5 = BaoCaoBussinessUtils.sum1(
						luyKeDauNamDenHetThangBC_bc12btVinalinesModels.get(
								count).getHang_pttnd(),
						luyKeDauNamDenHetThangBC_bc12btVinalinesModels.get(
								count).getHang_tau_bien());
				obj.put("F1", tong5 >= 0 ? formatter.format(tong5) : null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("G2",
						luyKeCungKyNamTruoc_bc12btVinalinesModels.get(count)
								.getHang_pttnd() >= 0 ? formatter
								.format(luyKeCungKyNamTruoc_bc12btVinalinesModels
										.get(count).getHang_pttnd())
								: null);
				obj.put("G3",
						luyKeCungKyNamTruoc_bc12btVinalinesModels.get(count)
								.getHang_tau_bien() >= 0 ? formatter
								.format(luyKeCungKyNamTruoc_bc12btVinalinesModels
										.get(count).getHang_tau_bien())
								: null);
				double tong6 = BaoCaoBussinessUtils.sum1(
						luyKeCungKyNamTruoc_bc12btVinalinesModels.get(count)
								.getHang_pttnd(),
						luyKeCungKyNamTruoc_bc12btVinalinesModels.get(count)
								.getHang_tau_bien());
				obj.put("G1", tong6 >= 0 ? formatter.format(tong6) : null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("H2",
						luyKeSoVoiCungKy_bc12btVinalinesModels.get(count)
								.getHang_pttnd() >= 0 ? formatter
								.format(luyKeSoVoiCungKy_bc12btVinalinesModels
										.get(count).getHang_pttnd()) : null);
				obj.put("H3",
						luyKeSoVoiCungKy_bc12btVinalinesModels.get(count)
								.getHang_tau_bien() >= 0 ? formatter
								.format(luyKeSoVoiCungKy_bc12btVinalinesModels
										.get(count).getHang_tau_bien()) : null);
				double tong7 = BaoCaoBussinessUtils.sum1(
						luyKeSoVoiCungKy_bc12btVinalinesModels.get(count)
								.getHang_pttnd(),
						luyKeSoVoiCungKy_bc12btVinalinesModels.get(count)
								.getHang_tau_bien());
				obj.put("H1", tong7 >= 0 ? formatter.format(tong7) : null);
			} catch (Exception ex) {
				// nothing to do
			}

			arrayHangVinalines.put(obj);
		}
		result.put("Bang3", arrayHangVinalines);

		// =================
		JSONArray arrayLuotTauVinalines = JSONFactoryUtil.createJSONArray();
		for (int count = 0; count < max; count++) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			try {
				obj.put("B1", luyKeDauNamDenHetThangBC_bc12btVinalinesModels
						.get(count).getTen_cang());
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("C2",
						thangBaoCao_bc12btVinalinesModels.get(count)
								.getLuot_pttnd() >= 0 ? thangBaoCao_bc12btVinalinesModels
								.get(count).getLuot_pttnd() : null);
				obj.put("C3",
						thangBaoCao_bc12btVinalinesModels.get(count)
								.getLuot_tau_bien() >= 0 ? thangBaoCao_bc12btVinalinesModels
								.get(count).getLuot_tau_bien() : null);
				int tong2 = BaoCaoBussinessUtils.sum2(
						thangBaoCao_bc12btVinalinesModels.get(count)
								.getLuot_pttnd(),
						thangBaoCao_bc12btVinalinesModels.get(count)
								.getLuot_tau_bien());
				obj.put("C1", tong2 >= 0 ? tong2 : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("D2",
						thangBaoCaoNamTruoc_bc12btVinalinesModels.get(count)
								.getLuot_pttnd() >= 0 ? thangBaoCaoNamTruoc_bc12btVinalinesModels
								.get(count).getLuot_pttnd() : null);
				obj.put("D3",
						thangBaoCaoNamTruoc_bc12btVinalinesModels.get(count)
								.getLuot_tau_bien() >= 0 ? thangBaoCaoNamTruoc_bc12btVinalinesModels
								.get(count).getLuot_tau_bien() : null);
				int tong3 = BaoCaoBussinessUtils.sum2(
						thangBaoCaoNamTruoc_bc12btVinalinesModels.get(count)
								.getLuot_pttnd(),
						thangBaoCaoNamTruoc_bc12btVinalinesModels.get(count)
								.getLuot_tau_bien());
				obj.put("D1", tong3 >= 0 ? tong3 : null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("E2",
						soVoiCungKy_bc12btVinalinesModels.get(count)
								.getLuot_pttnd() >= 0 ? soVoiCungKy_bc12btVinalinesModels
								.get(count).getLuot_pttnd() : null);
				obj.put("E3",
						soVoiCungKy_bc12btVinalinesModels.get(count)
								.getLuot_tau_bien() >= 0 ? soVoiCungKy_bc12btVinalinesModels
								.get(count).getLuot_tau_bien() : null);
				int tong4 = BaoCaoBussinessUtils.sum2(
						soVoiCungKy_bc12btVinalinesModels.get(count)
								.getLuot_pttnd(),
						soVoiCungKy_bc12btVinalinesModels.get(count)
								.getLuot_tau_bien());
				obj.put("E1", tong4 >= 0 ? tong4 : null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("F2",
						luyKeDauNamDenHetThangBC_bc12btVinalinesModels.get(
								count).getLuot_pttnd() >= 0 ? luyKeDauNamDenHetThangBC_bc12btVinalinesModels
								.get(count).getLuot_pttnd() : null);
				obj.put("F3",
						luyKeDauNamDenHetThangBC_bc12btVinalinesModels.get(
								count).getLuot_tau_bien() >= 0 ? luyKeDauNamDenHetThangBC_bc12btVinalinesModels
								.get(count).getLuot_tau_bien() : null);
				int tong5 = BaoCaoBussinessUtils.sum2(
						luyKeDauNamDenHetThangBC_bc12btVinalinesModels.get(
								count).getLuot_pttnd(),
						luyKeDauNamDenHetThangBC_bc12btVinalinesModels.get(
								count).getLuot_tau_bien());
				obj.put("F1", tong5 >= 0 ? tong5 : null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("G2",
						luyKeCungKyNamTruoc_bc12btVinalinesModels.get(count)
								.getLuot_pttnd() >= 0 ? luyKeCungKyNamTruoc_bc12btVinalinesModels
								.get(count).getLuot_pttnd() : null);
				obj.put("G3",
						luyKeCungKyNamTruoc_bc12btVinalinesModels.get(count)
								.getLuot_tau_bien() >= 0 ? luyKeCungKyNamTruoc_bc12btVinalinesModels
								.get(count).getLuot_tau_bien() : null);
				int tong6 = BaoCaoBussinessUtils.sum2(
						luyKeCungKyNamTruoc_bc12btVinalinesModels.get(count)
								.getLuot_pttnd(),
						luyKeCungKyNamTruoc_bc12btVinalinesModels.get(count)
								.getLuot_tau_bien());
				obj.put("G1", tong6 >= 0 ? tong6 : null);
			} catch (Exception ex) {
				// nothing to do
			}
			try {
				obj.put("H2",
						luyKeSoVoiCungKy_bc12btVinalinesModels.get(count)
								.getLuot_pttnd() >= 0 ? luyKeSoVoiCungKy_bc12btVinalinesModels
								.get(count).getLuot_pttnd() : null);
				obj.put("H3",
						luyKeSoVoiCungKy_bc12btVinalinesModels.get(count)
								.getLuot_tau_bien() >= 0 ? luyKeSoVoiCungKy_bc12btVinalinesModels
								.get(count).getLuot_tau_bien() : null);
				int tong7 = BaoCaoBussinessUtils.sum2(
						luyKeSoVoiCungKy_bc12btVinalinesModels.get(count)
								.getLuot_pttnd(),
						luyKeSoVoiCungKy_bc12btVinalinesModels.get(count)
								.getLuot_tau_bien());
				obj.put("H1", tong7 >= 0 ? tong7 : null);
			} catch (Exception ex) {
				// nothing to do
			}

			arrayLuotTauVinalines.put(obj);
		}
		result.put("Bang4", arrayLuotTauVinalines);

		result.put("reportMadeBy", reportMadeBy);
		result.put("reportPeriod", reportPeriod);
		result.put("fromDate", fromDate.replace(" 00:00", StringPool.BLANK));
		result.put("toDate", toDate.replace(" 00:00", StringPool.BLANK));
		result.put("reportMonth", fromMonth);
		result.put("reportYear", fromYear);
		result.put("maritimeNameVN", DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode).getMaritimeNameVN());
		result.put("signPlace",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode)
						.getCityCode());
		result.put("signDate", createDate.replace(" 00:00", StringPool.BLANK));

		log.info("==> " + result);

		return result;

	}

	public static JSONObject object2Json(BC12BTLuotHHModel obj, String objName) {
		NumberFormat formatter = new DecimalFormat("#0.00");

		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("maritimeCode", obj.getMaritimeCode());
		int sum_luot_tau_bien = BaoCaoBussinessUtils.sum2(
				obj.getLuot_tau_bien_nn(), obj.getLuot_tau_bien_vn(),
				obj.getLuot_tau_bien_vn_xnc(),
				obj.getLuot_tau_bien_vn_noi_dia());
		if (sum_luot_tau_bien >= 0) {
			result.put(objName + "1", sum_luot_tau_bien);
		}
		if (obj.getLuot_tau_bien_nn() >= 0) {
			result.put(objName + "2", obj.getLuot_tau_bien_nn());
		}
		if (obj.getLuot_tau_bien_vn() >= 0) {
			result.put(objName + "3", obj.getLuot_tau_bien_vn());
		}
		if (obj.getLuot_tau_bien_vn_xnc() >= 0) {
			result.put(objName + "4", obj.getLuot_tau_bien_vn_xnc());
		}
		if (obj.getLuot_tau_bien_vn_noi_dia() >= 0) {
			result.put(objName + "5", obj.getLuot_tau_bien_vn_noi_dia());
		}
		double sum_kl_hh = BaoCaoBussinessUtils.sum1(obj.getTong_xuat(),
				obj.getTong_nhap(), obj.getTong_noi_dia(),
				obj.getHang_long_tau_bien(), obj.getHang_kho_tau_bien(),
				obj.getHang_container_tau_bien());
		if (sum_kl_hh >= 0) {
			result.put(objName + "6", formatter.format(sum_kl_hh));
		}
		if (obj.getTong_xuat() >= 0) {
			result.put(objName + "7", formatter.format(obj.getTong_xuat()));
		}
		if (obj.getTong_nhap() >= 0) {
			result.put(objName + "8", formatter.format(obj.getTong_nhap()));
		}
		if (obj.getTong_noi_dia() >= 0) {
			result.put(objName + "9", formatter.format(obj.getTong_noi_dia()));
		}
		if (obj.getHang_container_tau_bien_teus() >= 0) {
			result.put(objName + "10",
					formatter.format(obj.getHang_container_tau_bien_teus()));
		}
		if (obj.getHang_container_tau_bien() >= 0) {
			result.put(objName + "11",
					formatter.format(obj.getHang_container_tau_bien()));
		}
		if (obj.getHang_long_tau_bien() >= 0) {
			result.put(objName + "12",
					formatter.format(obj.getHang_long_tau_bien()));
		}
		if (obj.getHang_kho_tau_bien() >= 0) {
			result.put(objName + "13",
					formatter.format(obj.getHang_kho_tau_bien()));
		}
		if (obj.getLuot_pttnd() >= 0) {
			result.put(objName + "14", obj.getLuot_pttnd());
		}
		if (obj.getLuot_pttnd_vrsb() >= 0) {
			result.put(objName + "15", obj.getLuot_pttnd_vrsb());
		}
		double sum_hh_pttnd = BaoCaoBussinessUtils.sum1(
				obj.getHang_kho_pttnd(), obj.getHang_long_pttnd(),
				obj.getHang_container_pttnd(),
				obj.getHang_container_pttnd_vrsb());
		if (sum_hh_pttnd >= 0) {
			result.put(objName + "16", formatter.format(sum_hh_pttnd));
		}
		if (obj.getHang_kho_pttnd() >= 0) {
			result.put(objName + "17",
					formatter.format(obj.getHang_kho_pttnd()));
		}
		if (obj.getHang_long_pttnd() >= 0) {
			result.put(objName + "18",
					formatter.format(obj.getHang_long_pttnd()));
		}
		if (obj.getHang_container_pttnd_teus() >= 0) {
			result.put(objName + "19",
					formatter.format(obj.getHang_container_pttnd_teus()));
		}
		if (obj.getHang_container_pttnd() >= 0) {
			result.put(objName + "20",
					formatter.format(obj.getHang_container_pttnd()));
		}
		if (obj.getHang_container_pttnd_vrsb() >= 0) {
			result.put(objName + "21",
					formatter.format(obj.getHang_container_pttnd_vrsb())); // Khong
			// biet
			// truong nao
		}
		if (obj.getHang_container_pttnd_vrsb_teus() >= 0) {
			result.put(objName + "22",
					formatter.format(obj.getHang_container_pttnd_vrsb_teus())); // Khong
			// biet
			// truong nao
		}
		if (obj.getTong_hang_qua_canh() >= 0) {
			result.put(objName + "23",
					formatter.format(obj.getTong_hang_qua_canh()));
		}
		double sum_hh_qua_cang = BaoCaoBussinessUtils.sum1(
				obj.getTong_hang_tau_bien(), obj.getTong_hang_pttnd());
		if (sum_hh_qua_cang >= 0) {
			result.put(objName + "24", formatter.format(sum_hh_qua_cang));
		}
		if (obj.getTong_hang_tau_bien() >= 0) {
			result.put(objName + "25",
					formatter.format(obj.getTong_hang_tau_bien()));
		}
		if (obj.getTong_hang_pttnd() >= 0) {
			result.put(objName + "26",
					formatter.format(obj.getTong_hang_pttnd()));
		}

		return result;
	}

	public static BC12BTLuotHHModel calculate(BC12BTLuotHHModel obj1,
			BC12BTLuotHHModel obj2, int operation) {

		BC12BTLuotHHModel bc12btLuotHHModel = new BC12BTLuotHHModel();

		bc12btLuotHHModel.setMaritimeCode(bc12btLuotHHModel.getMaritimeCode());
		bc12btLuotHHModel.setLuot_tau_bien(BaoCaoBussinessUtils.calculator(
				obj1.getLuot_tau_bien(), obj2.getLuot_tau_bien(), operation));
		bc12btLuotHHModel.setLuot_tau_bien_duoi_200(BaoCaoBussinessUtils
				.calculator(obj1.getLuot_tau_bien_duoi_200(),
						obj2.getLuot_tau_bien_duoi_200(), operation));
		bc12btLuotHHModel.setLuot_tau_bien_tren_200(BaoCaoBussinessUtils
				.calculator(obj1.getLuot_tau_bien_tren_200(),
						obj2.getLuot_tau_bien_tren_200(), operation));
		bc12btLuotHHModel.setLuot_tau_bien_nn(BaoCaoBussinessUtils.calculator(
				obj1.getLuot_tau_bien_nn(), obj2.getLuot_tau_bien_nn(),
				operation));
		bc12btLuotHHModel.setLuot_tau_bien_vn(BaoCaoBussinessUtils.calculator(
				obj1.getLuot_tau_bien_vn(), obj2.getLuot_tau_bien_vn(),
				operation));
		bc12btLuotHHModel.setLuot_tau_bien_vn_xnc(BaoCaoBussinessUtils
				.calculator(obj1.getLuot_tau_bien_vn_xnc(),
						obj2.getLuot_tau_bien_vn_xnc(), operation));
		bc12btLuotHHModel.setLuot_pttnd(BaoCaoBussinessUtils.calculator(
				obj1.getLuot_pttnd(), obj2.getLuot_pttnd(), operation));
		bc12btLuotHHModel
				.setLuot_pttnd_vrsb(BaoCaoBussinessUtils.calculator(
						obj1.getLuot_pttnd_vrsb(), obj2.getLuot_pttnd_vrsb(),
						operation));
		bc12btLuotHHModel.setLuot_tau_bien_vn_noi_dia(BaoCaoBussinessUtils
				.calculator(obj1.getLuot_tau_bien_vn_noi_dia(),
						obj2.getLuot_tau_bien_vn_noi_dia(), operation));
		bc12btLuotHHModel
				.setTong_luot_khach(BaoCaoBussinessUtils.calculator(
						obj1.getTong_luot_khach(), obj2.getTong_luot_khach(),
						operation));
		bc12btLuotHHModel.setLuot_khach_tau_bien_nn(BaoCaoBussinessUtils
				.calculator(obj1.getLuot_khach_tau_bien_nn(),
						obj2.getLuot_khach_tau_bien_nn(), operation));
		bc12btLuotHHModel.setLuot_khach_tau_bien_vn(BaoCaoBussinessUtils
				.calculator(obj1.getLuot_khach_tau_bien_vn(),
						obj2.getLuot_khach_tau_bien_vn(), operation));
		bc12btLuotHHModel.setLuot_khach_pttnd(BaoCaoBussinessUtils.calculator(
				obj1.getLuot_khach_pttnd(), obj2.getLuot_khach_pttnd(),
				operation));
		bc12btLuotHHModel.setKhach_tu_bo_ra_dao(BaoCaoBussinessUtils
				.calculator(obj1.getKhach_tu_bo_ra_dao(),
						obj2.getKhach_tu_bo_ra_dao(), operation));
		bc12btLuotHHModel
				.set_maritimeCode(bc12btLuotHHModel.get_maritimeCode());
		bc12btLuotHHModel.setTong_hang_tau_bien(BaoCaoBussinessUtils
				.calculator(obj1.getTong_hang_tau_bien(),
						obj2.getTong_hang_tau_bien(), operation));
		bc12btLuotHHModel.setTong_nhap(BaoCaoBussinessUtils.calculator(
				obj1.getTong_nhap(), obj2.getTong_nhap(), operation));
		bc12btLuotHHModel.setTong_xuat(BaoCaoBussinessUtils.calculator(
				obj1.getTong_xuat(), obj2.getTong_xuat(), operation));
		bc12btLuotHHModel.setTong_noi_dia(BaoCaoBussinessUtils.calculator(
				obj1.getTong_noi_dia(), obj2.getTong_noi_dia(), operation));
		bc12btLuotHHModel.setHang_container_tau_bien(BaoCaoBussinessUtils
				.calculator(obj1.getHang_container_tau_bien(),
						obj2.getHang_container_tau_bien(), operation));
		bc12btLuotHHModel.setHang_container_tau_bien_teus(BaoCaoBussinessUtils
				.calculator(obj1.getHang_container_tau_bien_teus(),
						obj2.getHang_container_tau_bien_teus(), operation));
		bc12btLuotHHModel.setHang_long_tau_bien(BaoCaoBussinessUtils
				.calculator(obj1.getHang_long_tau_bien(),
						obj2.getHang_long_tau_bien(), operation));
		bc12btLuotHHModel.setHang_kho_tau_bien(BaoCaoBussinessUtils.calculator(
				obj1.getHang_kho_tau_bien(), obj2.getHang_kho_tau_bien(),
				operation));
		bc12btLuotHHModel
				.setTong_hang_pttnd(BaoCaoBussinessUtils.calculator(
						obj1.getTong_hang_pttnd(), obj2.getTong_hang_pttnd(),
						operation));
		bc12btLuotHHModel.setHang_container_pttnd(BaoCaoBussinessUtils
				.calculator(obj1.getHang_container_pttnd(),
						obj2.getHang_container_pttnd(), operation));
		bc12btLuotHHModel.setHang_container_pttnd_teus(BaoCaoBussinessUtils
				.calculator(obj1.getHang_container_pttnd_teus(),
						obj2.getHang_container_pttnd_teus(), operation));
		bc12btLuotHHModel
				.setHang_long_pttnd(BaoCaoBussinessUtils.calculator(
						obj1.getHang_long_pttnd(), obj2.getHang_long_pttnd(),
						operation));
		bc12btLuotHHModel.setHang_kho_pttnd(BaoCaoBussinessUtils.calculator(
				obj1.getHang_kho_pttnd(), obj2.getHang_kho_pttnd(), operation));
		bc12btLuotHHModel.setHang_container_pttnd_vrsb(BaoCaoBussinessUtils
				.calculator(obj1.getHang_container_pttnd_vrsb(),
						obj2.getHang_container_pttnd_vrsb(), operation));
		bc12btLuotHHModel
				.setHang_container_pttnd_vrsb_teus(BaoCaoBussinessUtils
						.calculator(obj1.getHang_container_pttnd_vrsb_teus(),
								obj2.getHang_container_pttnd_vrsb_teus(),
								operation));
		bc12btLuotHHModel.setTong_hang_qua_canh(BaoCaoBussinessUtils
				.calculator(obj1.getTong_hang_qua_canh(),
						obj2.getTong_hang_qua_canh(), operation));
		bc12btLuotHHModel.setTong_hang(BaoCaoBussinessUtils.calculator(
				obj1.getTong_hang(), obj2.getTong_hang(), operation));

		return bc12btLuotHHModel;
	}

	public static List<BC12BTKhuCangModel> calculate2(
			List<BC12BTKhuCangModel> obj1, List<BC12BTKhuCangModel> obj2,
			int operation) {

		List<BC12BTKhuCangModel> bc12btKhuCangModels = new ArrayList<BC12BTKhuCangModel>();

		if (obj1 != null && !obj1.isEmpty() && obj2 != null && !obj2.isEmpty()) {
			int max = 0;
			if (obj1.size() > obj2.size()) {
				max = obj1.size();
			} else {
				max = obj2.size();
			}

			for (int i = 0; i < max; i++) {
				BC12BTKhuCangModel bc12btKhuCangModel = new BC12BTKhuCangModel();

				try {
					bc12btKhuCangModel.setMaritimeCode(obj1.get(i)
							.getMaritimeCode());
					bc12btKhuCangModel.setPortRegionCode(obj1.get(i)
							.getPortRegionCode());
					bc12btKhuCangModel.setPortRegionName(obj1.get(i)
							.getPortRegionName());
					bc12btKhuCangModel.setKhoi_luong_hang(BaoCaoBussinessUtils
							.calculator(obj1.get(i).getKhoi_luong_hang(), obj2
									.get(i).getKhoi_luong_hang(), operation));
					bc12btKhuCangModel.setLuot_tau(BaoCaoBussinessUtils
							.calculator(obj1.get(i).getLuot_tau(), obj2.get(i)
									.getLuot_tau(), operation));
					bc12btKhuCangModel.setLuot_tau_bien(BaoCaoBussinessUtils
							.calculator(obj1.get(i).getLuot_tau_bien(), obj2
									.get(i).getLuot_tau_bien(), operation));
					bc12btKhuCangModel.setLuot_pttnd(BaoCaoBussinessUtils
							.calculator(obj1.get(i).getLuot_pttnd(), obj2
									.get(i).getLuot_pttnd(), operation));
				} catch (Exception e) {

				}
				bc12btKhuCangModels.add(bc12btKhuCangModel);
			}
		}

		return bc12btKhuCangModels;
	}

	public static List<BC12BTVinalinesModel> calculate3(
			List<BC12BTVinalinesModel> obj1, List<BC12BTVinalinesModel> obj2,
			int operation) {

		List<BC12BTVinalinesModel> bc12btVinalinesModels = new ArrayList<BC12BTVinalinesModel>();

		if (obj1 != null && !obj1.isEmpty() && obj2 != null && !obj2.isEmpty()) {
			int max = 0;
			if (obj1.size() > obj2.size()) {
				max = obj1.size();
			} else {
				max = obj2.size();
			}

			for (int i = 0; i < max; i++) {
				BC12BTVinalinesModel bc12btVinalinesModel = new BC12BTVinalinesModel();

				try {
					bc12btVinalinesModel.setPortWharfCode(obj1.get(i)
							.getPortWharfCode());
					bc12btVinalinesModel.setTen_cang(obj1.get(i).getTen_cang());
					bc12btVinalinesModel
							.setKhoi_luong_hang(BaoCaoBussinessUtils
									.calculator(obj1.get(i)
											.getKhoi_luong_hang(), obj2.get(i)
											.getKhoi_luong_hang(), operation));
					bc12btVinalinesModel.setHang_tau_bien(BaoCaoBussinessUtils
							.calculator(obj1.get(i).getKhoi_luong_hang(), obj2
									.get(i).getKhoi_luong_hang(), operation));
					bc12btVinalinesModel.setHang_tau_bien(BaoCaoBussinessUtils
							.calculator(obj1.get(i).getHang_tau_bien(), obj2
									.get(i).getHang_tau_bien(), operation));
					bc12btVinalinesModel.setHang_pttnd(BaoCaoBussinessUtils
							.calculator(obj1.get(i).getHang_pttnd(), obj2
									.get(i).getHang_pttnd(), operation));
					bc12btVinalinesModel.setLuot_tau(BaoCaoBussinessUtils
							.calculator(obj1.get(i).getLuot_tau(), obj2.get(i)
									.getLuot_tau(), operation));
					bc12btVinalinesModel.setLuot_tau_bien(BaoCaoBussinessUtils
							.calculator(obj1.get(i).getLuot_tau_bien(), obj2
									.get(i).getLuot_tau_bien(), operation));
					bc12btVinalinesModel.setLuot_pttnd(BaoCaoBussinessUtils
							.calculator(obj1.get(i).getLuot_pttnd(), obj2
									.get(i).getLuot_pttnd(), operation));

					bc12btVinalinesModels.add(bc12btVinalinesModel);
				} catch (Exception e) {

				}
				bc12btVinalinesModels.add(bc12btVinalinesModel);
			}
		}

		return bc12btVinalinesModels;
	}
}
