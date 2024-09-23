package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet;
import vn.gt.dao.danhmuc.service.VmaHoatDongNaoVetLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.utils.FormatData;
import vn.gt.utils.PageType;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;




import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaHoatDongNaoVetUtils {

	

	public static JSONObject getHoatDongNaoVets(int start, int end)
			throws SystemException {
		List<VmaHoatDongNaoVet> lstHoatDongNaoVets = VmaHoatDongNaoVetLocalServiceUtil
				.getVmaHoatDongNaoVets(start, end);
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = VmaHoatDongNaoVetLocalServiceUtil.countVmaHoatDongNaoVet();
		if (lstHoatDongNaoVets != null) {
			for (VmaHoatDongNaoVet vmaHoatDongNaoVet : lstHoatDongNaoVets) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				obj.put("ID", vmaHoatDongNaoVet.getId());
				obj.put("MaritimeCode", vmaHoatDongNaoVet.getMaritimeCode());
				obj.put("ma_hoat_dong_nao_vet",
						vmaHoatDongNaoVet.getMaHoatDongNaoVet());
				obj.put("ten_cong_trinh", vmaHoatDongNaoVet.getTenCongTrinh());
				obj.put("chu_dau_tu", vmaHoatDongNaoVet.getChuDauTu());
				obj.put("nha_thau_thi_cong",
						vmaHoatDongNaoVet.getNhaThauThiCong());
				obj.put("tu_van_giam_sat", vmaHoatDongNaoVet.getTuVanGiamSat());
				obj.put("quyet_dinh_phe_duyet",
						vmaHoatDongNaoVet.getQuyetDinhPheDuyet());
				obj.put("vi_tri_do", vmaHoatDongNaoVet.getViTriDo());
				obj.put("ten_phuong_tien", vmaHoatDongNaoVet.getTenPhuongTien());
				obj.put("so_dkhc", vmaHoatDongNaoVet.getSoDkhc());
				obj.put("cap_phuong_tien", vmaHoatDongNaoVet.getCapPhuongTien());
				obj.put("suc_cho", vmaHoatDongNaoVet.getSucCho());
				obj.put("cong_dung", vmaHoatDongNaoVet.getCongDung());
				obj.put("so_dang_kiem", vmaHoatDongNaoVet.getSoDangKiem());

				try {
					obj.put("ngay_cap_dang_kiem",
							vmaHoatDongNaoVet.getNgayCapDangKiem());
				} catch (Exception e) {
					obj.put("ngay_cap_dang_kiem", StringPool.BLANK);
				}
				try {
					obj.put("ngay_het_han_dang_kiem",
							vmaHoatDongNaoVet.getNgayHetHanDangKiem());
				} catch (Exception e) {
					obj.put("ngay_het_han_dang_kiem", StringPool.BLANK);
				}
				try {
					obj.put("ngay_den_cang", vmaHoatDongNaoVet.getNgayDenCang());
				} catch (Exception e) {
					obj.put("ngay_den_cang", StringPool.BLANK);
				}
				try {
					obj.put("ngay_roi_cang", vmaHoatDongNaoVet.getNgayRoiCang());
				} catch (Exception e) {
					obj.put("ngay_roi_cang", StringPool.BLANK);
				}
				try {
					obj.put("ngay_cap_phep_hoat_dong_thi_cong",
							vmaHoatDongNaoVet.getNgayCapPhepHoatDongThiCong());
				} catch (Exception e) {
					obj.put("ngay_cap_phep_hoat_dong_thi_cong",
							StringPool.BLANK);
				}
				try {
					obj.put("han_cap_phep_hoat_dong",
							vmaHoatDongNaoVet.getHanCapPhepHoatDong());
				} catch (Exception e) {
					obj.put("han_cap_phep_hoat_dong", StringPool.BLANK);
				}

				obj.put("so_giay_phep_tau_den",
						vmaHoatDongNaoVet.getSoGiayPhepTauDen());
				obj.put("so_giay_phep_roi",
						vmaHoatDongNaoVet.getSoGiayPhepRoi());
				obj.put("tong_thoi_gian_thi_cong",
						vmaHoatDongNaoVet.getTongThoiGianThiCong());
				obj.put("so_luot_hoat_dong",
						vmaHoatDongNaoVet.getSoLuotHoatDong());
				obj.put("khoi_luong_thi_cong",
						vmaHoatDongNaoVet.getKhoiLuongThiCong());
				obj.put("MarkedAsDelete", vmaHoatDongNaoVet.getMarkedAsDelete());
				obj.put("ModifiedDate", vmaHoatDongNaoVet.getModifiedDate());
				array.put(obj);
			}
			result.put("total", total);
			result.put("data", array);

			return result;
		}
		return null;
	}

	public static JSONObject actionUpdateVmaHoatDongNaoVet(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String maritimeCode = userPort.getPortCode();
		String maHoatDongNaoVet = DanhMucUtils.getString(actionRequest,
				"ma_hoat_dong_nao_vet");
		String tenCongTrinh = DanhMucUtils.getString(actionRequest,
				"ten_cong_trinh");
		String chuDauTu = DanhMucUtils.getString(actionRequest, "chu_dau_tu");
		String nhaThauThiCong = DanhMucUtils.getString(actionRequest,
				"nha_thau_thi_cong");
		String tuVanGiamSat = DanhMucUtils.getString(actionRequest,
				"tu_van_giam_sat");
		String quyetDinhPheDuyet = DanhMucUtils.getString(actionRequest,
				"quyet_dinh_phe_duyet");
		String viTriDo = DanhMucUtils.getString(actionRequest, "vi_tri_do");
		String tenPhuongTien = DanhMucUtils.getString(actionRequest,
				"ten_phuong_tien");
		String soDkhc = DanhMucUtils.getString(actionRequest, "so_dkhc");
		String capPhuongTien = DanhMucUtils.getString(actionRequest,
				"cap_phuong_tien");
		String sucCho = DanhMucUtils.getString(actionRequest, "suc_cho");
		String congDung = DanhMucUtils.getString(actionRequest, "cong_dung");
		String soDangKiem = DanhMucUtils.getString(actionRequest,
				"so_dang_kiem");
		String ngayCapDangKiem = DanhMucUtils.getString(actionRequest,
				"ngay_cap_dang_kiem");
		String ngayHetHanDangKiem = DanhMucUtils.getString(actionRequest,
				"ngay_het_han_dang_kiem");
		String ngayDenCang = DanhMucUtils.getString(actionRequest,
				"ngay_den_cang");
		String soGiayPhepTauDen = DanhMucUtils.getString(actionRequest,
				"so_giay_phep_tau_den");
		String ngayRoiCang = DanhMucUtils.getString(actionRequest,
				"ngay_roi_cang");
		String soGiayPhepRoi = DanhMucUtils.getString(actionRequest,
				"so_giay_phep_roi");
		String ngayCapPhepHoatDongThiCong = DanhMucUtils.getString(
				actionRequest, "ngay_cap_phep_hoat_dong_thi_cong");
		String hanCapPhepHoatDong = DanhMucUtils.getString(actionRequest,
				"han_cap_phep_hoat_dong");
		String tongThoiGianThiCong = DanhMucUtils.getString(actionRequest,
				"tong_thoi_gian_thi_cong");
		String soLuotHoatDong = DanhMucUtils.getString(actionRequest,
				"so_luot_hoat_dong");
		String khoiLuongThiCong = DanhMucUtils.getString(actionRequest,
				"khoi_luong_thi_cong");

		int status = 0;
		String message = "";

		String iscreate = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String isedit = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String isdelete = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);
		try {
			if (isdelete.length() > 0) {
				long id = ParamUtil.getLong(actionRequest, "ID");
				VmaHoatDongNaoVetLocalServiceUtil.deleteVmaHoatDongNaoVet(id);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"vma_hoatdong_naovet", String.valueOf(id),
						StringPool.BLANK);
				status = 1;
				message += "Xoa Hoat Dong Nao Vet thanh cong";
			} else if (iscreate.length() > 0) {
				log.info("==============  Them: VmaHoatDongNaoVet");
				VmaHoatDongNaoVet vmaHoatDongNaoVet = new VmaHoatDongNaoVet();
				vmaHoatDongNaoVet.setMaritimeCode(maritimeCode);
				vmaHoatDongNaoVet.setMaHoatDongNaoVet(maHoatDongNaoVet);
				vmaHoatDongNaoVet.setTenCongTrinh(tenCongTrinh);
				vmaHoatDongNaoVet.setChuDauTu(chuDauTu);
				vmaHoatDongNaoVet.setNhaThauThiCong(nhaThauThiCong);
				vmaHoatDongNaoVet.setTuVanGiamSat(tuVanGiamSat);
				vmaHoatDongNaoVet.setQuyetDinhPheDuyet(quyetDinhPheDuyet);
				vmaHoatDongNaoVet.setViTriDo(viTriDo);
				vmaHoatDongNaoVet.setTenPhuongTien(tenPhuongTien);
				vmaHoatDongNaoVet.setSoDkhc(soDkhc);
				vmaHoatDongNaoVet.setCapPhuongTien(capPhuongTien);
				vmaHoatDongNaoVet.setSucCho(sucCho);
				vmaHoatDongNaoVet.setCongDung(congDung);
				vmaHoatDongNaoVet.setSoDangKiem(soDangKiem);
				vmaHoatDongNaoVet.setNgayCapDangKiem((Validator
						.isNotNull(ngayCapDangKiem) && !ngayCapDangKiem
						.isEmpty()) ? FormatData.formatDateShort3
						.parse(DanhMucUtils.timeStamp2Date(Long
								.valueOf(ngayCapDangKiem))) : null);

				vmaHoatDongNaoVet.setNgayHetHanDangKiem((Validator
						.isNotNull(ngayHetHanDangKiem) && !ngayHetHanDangKiem
						.isEmpty()) ? FormatData.formatDateShort3
						.parse(DanhMucUtils.timeStamp2Date(Long
								.valueOf(ngayHetHanDangKiem))) : null);

				vmaHoatDongNaoVet
						.setNgayDenCang((Validator.isNotNull(ngayDenCang) && !ngayDenCang
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(ngayDenCang))) : null);

				vmaHoatDongNaoVet.setSoGiayPhepTauDen(soGiayPhepTauDen);
				vmaHoatDongNaoVet
						.setNgayRoiCang((Validator.isNotNull(ngayRoiCang) && !ngayRoiCang
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(ngayRoiCang))) : null);

				vmaHoatDongNaoVet.setSoGiayPhepRoi(soGiayPhepRoi);

				vmaHoatDongNaoVet
						.setNgayCapPhepHoatDongThiCong((Validator
								.isNotNull(ngayCapPhepHoatDongThiCong) && !ngayCapPhepHoatDongThiCong
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(ngayCapPhepHoatDongThiCong)))
								: null);

				vmaHoatDongNaoVet.setHanCapPhepHoatDong((Validator
						.isNotNull(hanCapPhepHoatDong) && !hanCapPhepHoatDong
						.isEmpty()) ? FormatData.formatDateShort3
						.parse(DanhMucUtils.timeStamp2Date(Long
								.valueOf(hanCapPhepHoatDong))) : null);

				vmaHoatDongNaoVet.setTongThoiGianThiCong(tongThoiGianThiCong);
				vmaHoatDongNaoVet.setSoLuotHoatDong(soLuotHoatDong);
				vmaHoatDongNaoVet.setKhoiLuongThiCong(khoiLuongThiCong);

				VmaHoatDongNaoVet vmaHoatDongNaoVet2 = VmaHoatDongNaoVetLocalServiceUtil
						.addVmaHoatDongNaoVet(vmaHoatDongNaoVet);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"vma_hoatdong_naovet", String
								.valueOf(vmaHoatDongNaoVet2.getId()),
						StringPool.BLANK);
				status = 1;
				message += "Them VmaHoatDongNaoVet thanh cong";
			} else if (isedit.length() > 0) {
				long id = ParamUtil.getLong(actionRequest, "ID");
				VmaHoatDongNaoVet vmaHoatDongNaoVet = VmaHoatDongNaoVetLocalServiceUtil
						.getVmaHoatDongNaoVet(id);

				vmaHoatDongNaoVet.setMaritimeCode(maritimeCode);
				vmaHoatDongNaoVet.setMaHoatDongNaoVet(maHoatDongNaoVet);
				vmaHoatDongNaoVet.setTenCongTrinh(tenCongTrinh);
				vmaHoatDongNaoVet.setChuDauTu(chuDauTu);
				vmaHoatDongNaoVet.setNhaThauThiCong(nhaThauThiCong);
				vmaHoatDongNaoVet.setTuVanGiamSat(tuVanGiamSat);
				vmaHoatDongNaoVet.setQuyetDinhPheDuyet(quyetDinhPheDuyet);
				vmaHoatDongNaoVet.setViTriDo(viTriDo);
				vmaHoatDongNaoVet.setTenPhuongTien(tenPhuongTien);
				vmaHoatDongNaoVet.setSoDkhc(soDkhc);
				vmaHoatDongNaoVet.setCapPhuongTien(capPhuongTien);
				vmaHoatDongNaoVet.setSucCho(sucCho);
				vmaHoatDongNaoVet.setCongDung(congDung);
				vmaHoatDongNaoVet.setSoDangKiem(soDangKiem);
				vmaHoatDongNaoVet.setNgayCapDangKiem((Validator
						.isNotNull(ngayCapDangKiem) && !ngayCapDangKiem
						.isEmpty()) ? FormatData.formatDateShort3
						.parse(DanhMucUtils.timeStamp2Date(Long
								.valueOf(ngayCapDangKiem))) : null);

				vmaHoatDongNaoVet.setNgayHetHanDangKiem((Validator
						.isNotNull(ngayHetHanDangKiem) && !ngayHetHanDangKiem
						.isEmpty()) ? FormatData.formatDateShort3
						.parse(DanhMucUtils.timeStamp2Date(Long
								.valueOf(ngayHetHanDangKiem))) : null);

				vmaHoatDongNaoVet
						.setNgayDenCang((Validator.isNotNull(ngayDenCang) && !ngayDenCang
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(ngayDenCang))) : null);

				vmaHoatDongNaoVet.setSoGiayPhepTauDen(soGiayPhepTauDen);
				vmaHoatDongNaoVet
						.setNgayRoiCang((Validator.isNotNull(ngayRoiCang) && !ngayRoiCang
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(ngayRoiCang))) : null);

				vmaHoatDongNaoVet.setSoGiayPhepRoi(soGiayPhepRoi);

				vmaHoatDongNaoVet
						.setNgayCapPhepHoatDongThiCong((Validator
								.isNotNull(ngayCapPhepHoatDongThiCong) && !ngayCapPhepHoatDongThiCong
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(ngayCapPhepHoatDongThiCong)))
								: null);

				vmaHoatDongNaoVet.setHanCapPhepHoatDong((Validator
						.isNotNull(hanCapPhepHoatDong) && !hanCapPhepHoatDong
						.isEmpty()) ? FormatData.formatDateShort3
						.parse(DanhMucUtils.timeStamp2Date(Long
								.valueOf(hanCapPhepHoatDong))) : null);

				vmaHoatDongNaoVet.setTongThoiGianThiCong(tongThoiGianThiCong);
				vmaHoatDongNaoVet.setSoLuotHoatDong(soLuotHoatDong);
				vmaHoatDongNaoVet.setKhoiLuongThiCong(khoiLuongThiCong);

				VmaHoatDongNaoVet vmaHoatDongNaoVet2 = VmaHoatDongNaoVetLocalServiceUtil
						.updateVmaHoatDongNaoVet(vmaHoatDongNaoVet);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"vma_hoatdong_naovet", String
								.valueOf(vmaHoatDongNaoVet2.getId()),
						StringPool.BLANK);
				status = 1;
				message += "Sua VmaHoatDongNaoVet thanh cong";
			}
			//todo tinh sau
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("status", status);
		result.put("message", DanhMucUtils.encodeUTF8(message));
		return result;
	}

	public static JSONObject getDetailVmaHoatDongNaoVets(long id)
			throws PortalException, SystemException {
		VmaHoatDongNaoVet vmaHoatDongNaoVet = VmaHoatDongNaoVetLocalServiceUtil
				.getVmaHoatDongNaoVet(id);
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put("ID", vmaHoatDongNaoVet.getId());
		obj.put("MaritimeCode", vmaHoatDongNaoVet.getMaritimeCode());
		obj.put("ma_hoat_dong_nao_vet", vmaHoatDongNaoVet.getMaHoatDongNaoVet());
		obj.put("ten_cong_trinh", vmaHoatDongNaoVet.getTenCongTrinh());
		obj.put("chu_dau_tu", vmaHoatDongNaoVet.getChuDauTu());
		obj.put("nha_thau_thi_cong", vmaHoatDongNaoVet.getNhaThauThiCong());
		obj.put("tu_van_giam_sat", vmaHoatDongNaoVet.getTuVanGiamSat());
		obj.put("quyet_dinh_phe_duyet",
				vmaHoatDongNaoVet.getQuyetDinhPheDuyet());
		obj.put("vi_tri_do", vmaHoatDongNaoVet.getViTriDo());
		obj.put("ten_phuong_tien", vmaHoatDongNaoVet.getTenPhuongTien());
		obj.put("so_dkhc", vmaHoatDongNaoVet.getSoDkhc());
		obj.put("cap_phuong_tien", vmaHoatDongNaoVet.getCapPhuongTien());
		obj.put("suc_cho", vmaHoatDongNaoVet.getSucCho());
		obj.put("cong_dung", vmaHoatDongNaoVet.getCongDung());
		obj.put("so_dang_kiem", vmaHoatDongNaoVet.getSoDangKiem());

		try {
			obj.put("ngay_cap_dang_kiem",
					vmaHoatDongNaoVet.getNgayCapDangKiem());
		} catch (Exception e) {
			obj.put("ngay_cap_dang_kiem", StringPool.BLANK);
		}
		try {
			obj.put("ngay_het_han_dang_kiem",
					vmaHoatDongNaoVet.getNgayHetHanDangKiem());
		} catch (Exception e) {
			obj.put("ngay_het_han_dang_kiem", StringPool.BLANK);
		}
		try {
			obj.put("ngay_den_cang", vmaHoatDongNaoVet.getNgayDenCang());
		} catch (Exception e) {
			obj.put("ngay_den_cang", StringPool.BLANK);
		}
		try {
			obj.put("ngay_roi_cang", vmaHoatDongNaoVet.getNgayRoiCang());
		} catch (Exception e) {
			obj.put("ngay_roi_cang", StringPool.BLANK);
		}
		try {
			obj.put("ngay_cap_phep_hoat_dong_thi_cong",
					vmaHoatDongNaoVet.getNgayCapPhepHoatDongThiCong());
		} catch (Exception e) {
			obj.put("ngay_cap_phep_hoat_dong_thi_cong", StringPool.BLANK);
		}
		try {
			obj.put("han_cap_phep_hoat_dong",
					vmaHoatDongNaoVet.getHanCapPhepHoatDong());
		} catch (Exception e) {
			obj.put("han_cap_phep_hoat_dong", StringPool.BLANK);
		}

		obj.put("so_giay_phep_tau_den", vmaHoatDongNaoVet.getSoGiayPhepTauDen());
		obj.put("so_giay_phep_roi", vmaHoatDongNaoVet.getSoGiayPhepRoi());
		obj.put("tong_thoi_gian_thi_cong",
				vmaHoatDongNaoVet.getTongThoiGianThiCong());
		obj.put("so_luot_hoat_dong", vmaHoatDongNaoVet.getSoLuotHoatDong());
		obj.put("khoi_luong_thi_cong", vmaHoatDongNaoVet.getKhoiLuongThiCong());
		obj.put("MarkedAsDelete", vmaHoatDongNaoVet.getMarkedAsDelete());
		obj.put("ModifiedDate", vmaHoatDongNaoVet.getModifiedDate());
		return obj;
	}

}
