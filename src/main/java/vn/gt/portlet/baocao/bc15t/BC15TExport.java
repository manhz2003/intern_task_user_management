package vn.gt.portlet.baocao.bc15t;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;

public class BC15TExport {

	

	public static JSONObject getModel(String maritimeCode, String fromDate,
			String toDate, String createDate, String reportPeriod,
			String reportMadeBy) throws ParseException, SystemException {
		Date _fromDate = FormatData.formatDateShort3.parse(fromDate);
		Calendar fromCal = Calendar.getInstance();
		fromCal.setTime(_fromDate);
		int fromYear = fromCal.get(Calendar.YEAR);
		int fromMonth = fromCal.get(Calendar.MONTH) + 1;
		int fromDay = fromCal.get(Calendar.DAY_OF_MONTH);

		Date _toDate = FormatData.formatDateShort3.parse(toDate);
		Calendar toCal = Calendar.getInstance();
		toCal.setTime(_toDate);
		int toYear = toCal.get(Calendar.YEAR);
		int toMonth = toCal.get(Calendar.MONTH) + 1;
		int toDay = toCal.get(Calendar.DAY_OF_MONTH);

		String thangBaoCao_startDate = String.valueOf(fromYear)
				+ ((fromMonth) < 10 ? ("-0" + String.valueOf(fromMonth)) : "-"
						+ String.valueOf(fromMonth)) + "-01";
		String thangBaoCao_endDate = String.valueOf(toYear)
				+ ((toMonth) < 10 ? ("-0" + String.valueOf(toMonth)) : "-"
						+ String.valueOf(toMonth))
				+ "-"
				+ String.valueOf(BaoCaoBussinessUtils.countDayByMonthAndYear(
						toMonth, toYear));

		List<BC15TModel> thangBaoCao_bc15tModels = new ArrayList<BC15TModel>();
		List<BC15TModel> luyKe_bc15tModels = new ArrayList<BC15TModel>();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		thangBaoCao_bc15tModels = DmPortRegionLocalServiceUtil.getModelMau15T(
				maritimeCode, thangBaoCao_startDate, thangBaoCao_endDate);
		luyKe_bc15tModels = DmPortRegionLocalServiceUtil.getModelMau15T(
				maritimeCode, "2019-01-01", thangBaoCao_endDate);

		for (int i = 0; i < luyKe_bc15tModels.size(); i++) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			try {
				obj.put("B", luyKe_bc15tModels.get(i).getPortHarbourName()
						+ ", " + luyKe_bc15tModels.get(i).getPortRegionName());
			} catch (Exception ex) {

			}
			try {
				obj.put("C",
						thangBaoCao_bc15tModels.get(i).getHang_container() >= 0 ? thangBaoCao_bc15tModels
								.get(i).getHang_container() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("D",
						thangBaoCao_bc15tModels.get(i).getHang_container_teus() >= 0 ? thangBaoCao_bc15tModels
								.get(i).getHang_container_teus() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("E",
						luyKe_bc15tModels.get(i).getHang_container() >= 0 ? luyKe_bc15tModels
								.get(i).getHang_container() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("F",
						luyKe_bc15tModels.get(i).getHang_container_teus() >= 0 ? luyKe_bc15tModels
								.get(i).getHang_container_teus() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("G",
						thangBaoCao_bc15tModels.get(i).getHang_kho() >= 0 ? thangBaoCao_bc15tModels
								.get(i).getHang_kho() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("H",
						luyKe_bc15tModels.get(i).getHang_kho() >= 0 ? luyKe_bc15tModels
								.get(i).getHang_kho() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("I",
						thangBaoCao_bc15tModels.get(i).getHang_long() >= 0 ? thangBaoCao_bc15tModels
								.get(i).getHang_long() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("K",
						luyKe_bc15tModels.get(i).getHang_long() >= 0 ? luyKe_bc15tModels
								.get(i).getHang_long() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("L", thangBaoCao_bc15tModels.get(i)
						.getQua_canh_xep_do() >= 0 ? thangBaoCao_bc15tModels
						.get(i).getQua_canh_xep_do() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("M",
						luyKe_bc15tModels.get(i).getQua_canh_xep_do() >= 0 ? luyKe_bc15tModels
								.get(i).getQua_canh_xep_do() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("N",
						thangBaoCao_bc15tModels.get(i)
								.getQua_canh_khong_xep_do() >= 0 ? thangBaoCao_bc15tModels
								.get(i).getQua_canh_khong_xep_do() : null);
			} catch (Exception ex) {

			}
			try {
				obj.put("O", luyKe_bc15tModels.get(i)
						.getQua_canh_khong_xep_do() >= 0 ? luyKe_bc15tModels
						.get(i).getQua_canh_khong_xep_do() : null);
			} catch (Exception ex) {

			}

			array.put(obj);
		}

		JSONObject sumJson = JSONFactoryUtil.createJSONObject();
		double thangbc_hang_container = 0, thangbc_hang_container_teus = 0, thangbc_hang_kho = 0, thangbc_hang_long = 0, thangbc_qua_canh_xep_do = 0, thangbc_qua_canh_khong_xep_do = 0;
		double luyke_hang_container = 0, luyke_hang_container_teus = 0, luyke_hang_kho = 0, luyke_hang_long = 0, luyke_qua_canh_xep_do = 0, luyke_qua_canh_khong_xep_do = 0;

		for (BC15TModel bc15tModel : thangBaoCao_bc15tModels) {
			thangbc_hang_container += bc15tModel.getHang_container() >= 0 ? bc15tModel
					.getHang_container() : 0;
			thangbc_hang_container_teus += bc15tModel.getHang_container_teus() >= 0 ? bc15tModel
					.getHang_container_teus() : 0;
			thangbc_hang_kho += bc15tModel.getHang_kho() >= 0 ? bc15tModel
					.getHang_kho() : 0;
			thangbc_hang_long += bc15tModel.getHang_long() >= 0 ? bc15tModel
					.getHang_long() : 0;
			thangbc_qua_canh_xep_do += bc15tModel.getQua_canh_xep_do() >= 0 ? bc15tModel
					.getQua_canh_xep_do() : 0;
			thangbc_qua_canh_khong_xep_do += bc15tModel
					.getQua_canh_khong_xep_do() >= 0 ? bc15tModel
					.getQua_canh_khong_xep_do() : 0;
		}

		for (BC15TModel bc15tModel : luyKe_bc15tModels) {
			luyke_hang_container += bc15tModel.getHang_container() >= 0 ? bc15tModel
					.getHang_container() : 0;
			luyke_hang_container_teus += bc15tModel.getHang_container_teus() >= 0 ? bc15tModel
					.getHang_container_teus() : 0;
			luyke_hang_kho += bc15tModel.getHang_kho() >= 0 ? bc15tModel
					.getHang_kho() : 0;
			luyke_hang_long += bc15tModel.getHang_long() >= 0 ? bc15tModel
					.getHang_long() : 0;
			luyke_qua_canh_xep_do += bc15tModel.getQua_canh_xep_do() >= 0 ? bc15tModel
					.getQua_canh_xep_do() : 0;
			luyke_qua_canh_khong_xep_do += bc15tModel
					.getQua_canh_khong_xep_do() >= 0 ? bc15tModel
					.getQua_canh_khong_xep_do() : 0;
		}

		sumJson.put("B", DanhMucUtils.encodeUTF8("Tổng cộng"));
		sumJson.put("C", thangbc_hang_container);
		sumJson.put("D", luyke_hang_container);
		sumJson.put("E", thangbc_hang_container_teus);
		sumJson.put("F", luyke_hang_container_teus);
		sumJson.put("G", thangbc_hang_kho);
		sumJson.put("H", luyke_hang_kho);
		sumJson.put("I", thangbc_hang_long);
		sumJson.put("K", luyke_hang_long);
		sumJson.put("L", thangbc_qua_canh_xep_do);
		sumJson.put("M", luyke_qua_canh_xep_do);
		sumJson.put("N", thangbc_qua_canh_khong_xep_do);
		sumJson.put("O", luyke_qua_canh_khong_xep_do);

		array.put(sumJson);

		result.put("Bang1", array);
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
		
		return result;
	}
}
