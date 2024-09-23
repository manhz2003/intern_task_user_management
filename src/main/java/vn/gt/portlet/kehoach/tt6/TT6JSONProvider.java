package vn.gt.portlet.kehoach.tt6;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.Organization;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.core.ThemeDisplay;

import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmRepresentative;
import vn.gt.dao.danhmuc.service.DmRepresentativeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGTFunctionTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;

import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempAnimalQuarantine;
import com.fds.nsw.nghiepvu.model.TempCargoDeclaration;
import com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;
import com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempPassengerList;
import com.fds.nsw.nghiepvu.model.TempPlantQuarantine;
import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
import com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration;

import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempAnimalQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewEffectsDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDangerousGoodsNanifestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDeclarationOfHealthLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPassengerListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiAnNinhTauBienUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiBaoYTeHangHaiUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiChungUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiDuTruCuaTauUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiGiayPhepRoiCangBoGTVTUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiGiayPhepRoiCangCuoiCungUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiHangHoaUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiHanhLyThuyenVienUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiCacGiayToPhaiXuatTrinhUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiDanhSachHangHoaNguyHiemUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiDanhSachHanhKhachUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiDanhSachThuyenVienUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiKetQuaPheDuyetHoSoUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiKiemDichDongVatUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiKiemDichThucVatUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiLenhDieuDongUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiThongBaoUtils;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiXacBaoUtils;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.portlet.kehoach.utils.JSONProviderUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;
import vn.gt.utils.document.DocumentUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TT6JSONProvider {

	

	public static JSONArray getRoleFilterStatus(Locale locale, User user) {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		JSONArray childs = JSONFactoryUtil.createJSONArray();
		JSONObject object = JSONFactoryUtil.createJSONObject();
		JSONObject objectChilds = JSONFactoryUtil.createJSONObject();

		// get user orgs
		try {
			List<Organization> orgs = user.getOrganizations();
			for (Organization organization : orgs) {
				if (organization.getName().equalsIgnoreCase(LanguageUtil.get(locale, "ke-hoach"))) {
					// start json build ke hoach
					object.put("action", "folder");
					object.put("action_active", "folder_open");
					object.put("id", -1);
					object.put("id_active", "ke_hoach");
					object.put("type", -1);
					object.put("title", LanguageUtil.get(locale, "ke-hoach"));
					object.put("active", false);
					object.put("index", 0);

					// TODO order
					objectChilds = JSONFactoryUtil.createJSONObject();

					objectChilds.put("id", 4);
					objectChilds.put("code", 11);
					objectChilds.put("title", LanguageUtil.get(locale, "cho-tiep-nhan-tt"));
					objectChilds.put("type", -1);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();

					objectChilds.put("id", 30);
					objectChilds.put("code", 27);
					objectChilds.put("title", LanguageUtil.get(locale, "yeu-cau-sua-doi-bo-sung-tt"));
					objectChilds.put("type", -1);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();

					objectChilds.put("id", 7);
					objectChilds.put("code", 14);
					objectChilds.put("title", LanguageUtil.get(locale, "cho-cap-lenh-dieu-dong-tt"));
					objectChilds.put("type", -1);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();

					objectChilds.put("id", 41);
					objectChilds.put("code", 114);
					objectChilds.put("title", LanguageUtil.get(locale, "cho-sua-lenh-dieu-dong-tt"));
					objectChilds.put("type", -1);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();
					objectChilds.put("id", 8);
					objectChilds.put("code", 15);
					objectChilds.put("title", LanguageUtil.get(locale, "da-cap-lenh-dieu-dong-tt"));
					objectChilds.put("type", -1);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();
					objectChilds.put("id", 9);
					objectChilds.put("code", 16);
					objectChilds.put("title", LanguageUtil.get(locale, "da-huy-lenh-dieu-dong-tt"));
					objectChilds.put("type", -1);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();
					objectChilds.put("id", 2);
					objectChilds.put("code", 13);
					objectChilds.put("title", LanguageUtil.get(locale, "tu-choi-tiep-tt"));
					objectChilds.put("type", -1);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					result.put(object);
					// end build ke hoach
				} else if (organization.getName().equalsIgnoreCase(LanguageUtil.get(locale, "thu-tuc"))) {
					// start json build thu tuc
					childs = JSONFactoryUtil.createJSONArray();
					object = JSONFactoryUtil.createJSONObject();
					object.put("action", "folder");
					object.put("action_active", "folder_open");
					object.put("id", -2);
					object.put("id_active", "thu_tuc");
					object.put("type", -2);
					 object.put("title", LanguageUtil.get(locale, "thu-tuc"));
					object.put("active", false);
					object.put("index", 1);

					objectChilds = JSONFactoryUtil.createJSONObject();
					objectChilds.put("id", 11);
					objectChilds.put("code", 18);
					objectChilds.put("title", LanguageUtil.get(locale, "da-tiep-nhan-tt"));
					objectChilds.put("type", -2);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();
					objectChilds.put("id", 6);
					objectChilds.put("code", 13);
					objectChilds.put("title", LanguageUtil.get(locale, "yeu-cau-sua-doi-bo-sung-tt"));
					objectChilds.put("type", -2);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();
					objectChilds.put("id", 5);
					objectChilds.put("code", 12);
					objectChilds.put("title", LanguageUtil.get(locale, "cho-phe-duyet-hoan-thanh-thu-tuc-tt"));
					objectChilds.put("type", -2);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();
					objectChilds.put("id", 43);
					objectChilds.put("code", 25);
					objectChilds.put("title", LanguageUtil.get(locale, "tam-dung-lam-thu-tuc-dien-tu"));
					objectChilds.put("type", -2);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();
					objectChilds.put("id", 12);
					objectChilds.put("code", 19);
					objectChilds.put("title", LanguageUtil.get(locale, "phe-duyet-hoan-thanh-thu-tuc-tt"));
					objectChilds.put("type", -2);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					objectChilds = JSONFactoryUtil.createJSONObject();
					objectChilds.put("id", 13);
					objectChilds.put("code", 10);
					objectChilds.put("title", LanguageUtil.get(locale, "huy-thu-tuc-tt"));
					objectChilds.put("type", -2);
					objectChilds.put("counter", "_ _");
					childs.put(objectChilds);
					object.put("items", childs);

					result.put(object);
					// end build thu tuc
				}
			}
			
			result.put(JSONProviderUtils.createMenu(locale));
			
//			childs = JSONFactoryUtil.createJSONArray();
//			object = JSONFactoryUtil.createJSONObject();
//			
//			object.put("action", "folder");
//			object.put("action_active", "folder_open");
//			object.put("id", -1);
//			object.put("id_active", "quan_ly_tau");
//			object.put("type", -1);
//			object.put("title", "Quản lý tàu biển & PTTNĐ");
//			object.put("active", false);
//			object.put("index", 2);
//			
//			// TODO order
//			objectChilds = JSONFactoryUtil.createJSONObject();
//
//			objectChilds.put("id", 4);
//			objectChilds.put("code", 11);
//			objectChilds.put("title", "Tàu biển");
//			objectChilds.put("type", "DanhSachTauBien");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//
//			objectChilds = JSONFactoryUtil.createJSONObject();
//
//			objectChilds.put("id", 30);
//			objectChilds.put("code", 27);
//			objectChilds.put("title", "Phương tiện thủy nội địa");
//			objectChilds.put("type", "DanhSachPhuongTienThuyNoiDia");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//
//			objectChilds = JSONFactoryUtil.createJSONObject();
//
//			objectChilds.put("id", 7);
//			objectChilds.put("code", 14);
//			objectChilds.put("title", "Kế hoạch tàu rời cảng");
//			objectChilds.put("type", "DanhSachTauRoiCang");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//
//			objectChilds = JSONFactoryUtil.createJSONObject();
//
//			objectChilds.put("id", 41);
//			objectChilds.put("code", 114);
//			objectChilds.put("title", "Kế hoạch tàu đến cảng");
//			objectChilds.put("type", "DanhSachTauDenCang");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 8);
//			objectChilds.put("code", 15);
//			objectChilds.put("title", "Kế hoạch tàu di chuyển");
//			objectChilds.put("type", -1);
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//			
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 8);
//			objectChilds.put("code", 15);
//			objectChilds.put("title", "Tàu neo đậu");
//			objectChilds.put("type", "DanhSachNeoTau");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//			
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 8);
//			objectChilds.put("code", 15);
//			objectChilds.put("title", "Tàu lai hỗ trợ");
//			objectChilds.put("type", "DanhSachTauLaiHoTro");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//			
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 8);
//			objectChilds.put("code", 15);
//			objectChilds.put("title", "Hoa tiêu dẫn tàu");
//			objectChilds.put("type", "DanhSachHoaTieuDanTau");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 9);
//			objectChilds.put("code", 16);
//			objectChilds.put("title", "Xếp, dỡ hàng");
//			objectChilds.put("type", "DanhSachXepDoHang");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 2);
//			objectChilds.put("code", 13);
//			objectChilds.put("title", "Nhập tách đoàn");
//			objectChilds.put("type", -1);
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//			
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 2);
//			objectChilds.put("code", 13);
//			objectChilds.put("title", "Sửa chữa");
//			objectChilds.put("type", "DanhSachSuaChuaTau");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//			
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 2);
//			objectChilds.put("code", 13);
//			objectChilds.put("title", "Hạ xuồng");
//			objectChilds.put("type", "DanhSachHaXuong");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//			
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 2);
//			objectChilds.put("code", 13);
//			objectChilds.put("title", "Thử tàu");
//			objectChilds.put("type", "DanhSachThuTau");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//			
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 2);
//			objectChilds.put("code", 13);
//			objectChilds.put("title", "Giữ tàu");
//			objectChilds.put("type", "DanhSachDuTau");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//			
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 2);
//			objectChilds.put("code", 13);
//			objectChilds.put("title", "Ghi chú, cảnh báo");
//			objectChilds.put("type", "DanhSachGhiChuCanhBao");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//			
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 2);
//			objectChilds.put("code", 13);
//			objectChilds.put("title", "Kháng nghị hàng hải");
//			objectChilds.put("type", "DanhSachKhangNghiHangHai");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//			
//			objectChilds = JSONFactoryUtil.createJSONObject();
//			objectChilds.put("id", 2);
//			objectChilds.put("code", 13);
//			objectChilds.put("title", "Kế hoạch tàu chạy chuyên tuyến");
//			objectChilds.put("type", "DanhSachKeHoachChuyenTuyen");
//			objectChilds.put("counter", "_ _");
//			childs.put(objectChilds);
//			object.put("items", childs);
//
//			result.put(object);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}
	
	public static JSONArray getThanhPhanHoSo(ThemeDisplay themeDisplay, String documentType, long documentName,
			int documentYear, HttpServletRequest request) {
		JSONArray result = JSONFactoryUtil.createJSONArray();

		JSONObject thongBaoObject = BanKhaiThongBaoUtils.buildThanhPhan(themeDisplay, documentName, documentYear, MessageType.THONG_BAO_TAU_DEN_CANG);
		JSONObject xacBaoObject = BanKhaiXacBaoUtils.buildThanhPhan(themeDisplay, documentName, documentYear, MessageType.XAC_BAO_TAU_DEN_CANG);
		JSONObject lenhDieuDongObject = BanKhaiLenhDieuDongUtils.buildThanhPhan(themeDisplay, documentName, documentYear);
		
		if (thongBaoObject.getBoolean("available")/*  && xacBaoObject.getBoolean("available")*/) {
			lenhDieuDongObject.put("available", true);
		} else {
			lenhDieuDongObject.put("available", false);
		}

		List<IssueShiftingOrder> results = new ArrayList<IssueShiftingOrder>(
				IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(documentName,
						documentYear, KeyParams.ID, KeyParams.ORDER_BY_DESC));
		
		if (Validator.isNotNull(results) && results.size() > 0) {
			lenhDieuDongObject.put("available", true);
		}
		
//		result.put(BanKhaiAnNinhTauBienUtils.buildThanhPhan(themeDisplay, documentName, documentYear));
//		result.put(BanKhaiHangHoaUtils.buildThanhPhan(themeDisplay, documentName, documentYear));
		result.put(thongBaoObject);
		result.put(xacBaoObject);
//		result.put(BanKhaiGiayPhepRoiCangBoGTVTUtils.buildThanhPhan(themeDisplay, documentName, documentYear));
		result.put(lenhDieuDongObject);
		result.put(BanKhaiChungUtils.buildThanhPhan(themeDisplay, documentName, documentYear));
		result.put(BanKhaiDanhSachThuyenVienUtils.buildThanhPhan(themeDisplay, documentName, documentYear));
		result.put(BanKhaiDanhSachHanhKhachUtils.buildThanhPhan(themeDisplay, documentName, documentYear));
		
		
		// thu-tuc
		try {
			int roleUserFilterselectedType = ParamUtil.getInteger(request, "roleUserFilterselectedType", -1);

			boolean isThuTuc = false;
			List<Organization> orgs = themeDisplay.getUser().getOrganizations();
			for (Organization organization : orgs) {
				if (organization.getName()
						.equalsIgnoreCase(LanguageUtil.get(themeDisplay.getLocale(), "thu-tuc"))) {
					isThuTuc = true;
					break;
				} else if (organization.getName()
						.equalsIgnoreCase(LanguageUtil.get(themeDisplay.getLocale(), "lanh-dao"))) {
					isThuTuc = true;
					break;
				} else if (organization.getName()
						.equalsIgnoreCase(LanguageUtil.get(themeDisplay.getLocale(), "van-thu"))) {
					isThuTuc = true;
					break;
				} else if (organization.getName().equalsIgnoreCase(LanguageUtil.get(themeDisplay.getLocale(), "ke-toan"))) {
					isThuTuc = true;
					break;
				}
			}

			if (isThuTuc && roleUserFilterselectedType != -1) {
				result.put(BanKhaiCacGiayToPhaiXuatTrinhUtils.buildThanhPhan(themeDisplay, documentName, documentYear));
				result.put(BanKhaiKetQuaPheDuyetHoSoUtils.buildThanhPhan(themeDisplay, documentName, documentYear));
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		JSONObject lastLost = BanKhaiGiayPhepRoiCangCuoiCungUtils.buildThanhPhan(themeDisplay, documentName, documentYear);
		if (Validator.isNotNull(lastLost)) {
			result.put(lastLost);
		}
		
		return result;
	}

	public static JSONArray getFileThanhPhanVersionList(ThemeDisplay themeDisplay, int messageType, long documentName,
			int documentYear, HttpServletRequest request) {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		if (messageType == MessageType.THONG_BAO_TAU_DEN_CANG) {
			
			result = BanKhaiThongBaoUtils.getFileThanhPhanVersionList(themeDisplay, documentName, documentYear, messageType);

		} else if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
			
			result = BanKhaiXacBaoUtils.getFileThanhPhanVersionList(themeDisplay, documentName, documentYear, messageType);
			
		} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN) {
			
			result = BanKhaiDanhSachThuyenVienUtils.getFileThanhPhanVersionList(themeDisplay, documentName, documentYear, messageType);
			
		} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH) {
			
			result = BanKhaiDanhSachHanhKhachUtils.getFileThanhPhanVersionList(themeDisplay, documentName, documentYear, messageType);
			
		} else if (messageType == MessageType.LENH_DIEU_DONG) {
			
			result = BanKhaiLenhDieuDongUtils.getFileThanhPhanVersionList(themeDisplay, documentName, documentYear, messageType);
			
		} else if (messageType == MessageType.BAN_KHAI_CHUNG) {
			
			result = BanKhaiChungUtils.getFileThanhPhanVersionList(themeDisplay, documentName, documentYear, messageType);
			
		} else if (messageType == MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH) {
			
			result = BanKhaiGiayPhepRoiCangBoGTVTUtils.getFileThanhPhanVersionList(themeDisplay, documentName, documentYear, messageType);
			
		} else if (messageType == MessageType.GIAY_PHEP_ROI_CANG_CUOI_CUNG) {

			result = BanKhaiGiayPhepRoiCangCuoiCungUtils.getFileThanhPhanVersionList(themeDisplay, documentName, documentYear,
					messageType);

		}
		return result;
	}

	public static JSONObject exportPDFDetailThanhPhan(Locale locale, int documentName, int documentYear,
			int messageType, String requestCode, HttpServletRequest request) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		String tenFileExport = StringPool.BLANK;
		try {

			long nanoTime = ReportsBusinessUtils.actionExport(requestCode, documentName, documentYear, messageType,
					ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI);
			boolean flagGiayPhepRoiCangCC = false;
			IssueShiftingOrder shiftOrder = null;
			if (messageType == MessageType.LENH_DIEU_DONG) {
				shiftOrder = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCode);
			}
			
			if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
				tenFileExport = nanoTime + "_" + ReportConstant.XACBAO_NOTICE_OF_ARRIVAL_EXPORT;
			} else if (messageType == MessageType.THONG_BAO_TAU_DEN_CANG) {
				tenFileExport = nanoTime + "_" + ReportConstant.NOTICE_OF_ARRIVAL_EXPORT;
			} else if (messageType == MessageType.LENH_DIEU_DONG) {
				if ( Validator.isNotNull(shiftOrder) && shiftOrder.getAttachedFile() > 0 && DocumentUtils.existDLFileAbsPath(shiftOrder.getAttachedFile())) {
					tenFileExport = vn.gt.portlet.document.FileUtils.getFullFileURL(shiftOrder.getAttachedFile());
				} else {
					tenFileExport = nanoTime + "_" + ReportConstant.SHIFTING_ORDER_EXPORT;
				}
			} else if (messageType == MessageType.BAN_KHAI_CHUNG) {
				tenFileExport = nanoTime + "_" + ReportConstant.GENERAL_DECLARATION_EXPORT;
			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN) {
				tenFileExport = nanoTime + "_" + ReportConstant.CREW_LIST_EXPORT;
			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH) {
				tenFileExport = nanoTime + "_" + ReportConstant.PASSENGER_LIST_EXPORT;
			} else if (messageType == MessageType.GIAY_PHEP_ROI_CANG_CUOI_CUNG) {
				
				String portCleaneNo = StringPool.BLANK;
				
				// Bản khai chung -- > thông báo
				
				TempGeneralDeclaration tempGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
				
				if (Validator.isNotNull(tempGeneralDeclaration)) {
					portCleaneNo = tempGeneralDeclaration.getPortClearanceNo();
				} else {
					
					TempNoticeShipMessage tempNoTiceShipMessage = TempNoTiceShipMessageLocalServiceUtil.getThongBaoLastByDocumentNameAndDocumentYear(documentName, documentYear);
					
					if (Validator.isNotNull(tempNoTiceShipMessage)) {
						portCleaneNo = tempNoTiceShipMessage.getPortClearanceNo();
					}
				}
				IssuePortClearance issuePortClearance = IssuePortClearanceLocalServiceUtil.findLatestCertificateOfIssuePortClearance(null, null, portCleaneNo);
				
				if (Validator.isNotNull(issuePortClearance)) {
					if (issuePortClearance.getAttachedFile() > 0 && DocumentUtils.existDLFileAbsPath(issuePortClearance.getAttachedFile())) {
						tenFileExport = vn.gt.portlet.document.FileUtils.getFullFileURL(issuePortClearance.getAttachedFile());
						flagGiayPhepRoiCangCC = true;
					} else {
						long documentNameTemp = issuePortClearance.getDocumentName();
						long documentYearTemp = issuePortClearance.getDocumentYear();
						TempDocument temp = TempDocumentLocalServiceUtil.getByDocumentNameAndDocumentYear(documentNameTemp,
								Integer.valueOf(documentYearTemp + ""));
						nanoTime = ReportsBusinessUtils.actionExport(issuePortClearance.getRequestCode(), Integer.valueOf(documentNameTemp + ""), Integer.valueOf(documentYearTemp + ""), MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH,
								temp.getDocumentTypeCode());
						log.info("-------documentNameTemp+++++++++++" + documentNameTemp);
						log.info("-------documentYearTemp+++++++++++" + documentYearTemp);
						log.info("-------nanoTime+++++++++++" + nanoTime);
						log.info("-------getRequestCode+++++++++++" + issuePortClearance.getRequestCode());
						tenFileExport = nanoTime + "_" + ReportConstant.ISSUE_PORT_CLEARANCE_EXPORT;
						flagGiayPhepRoiCangCC = false;
					}
				}
				
			}

			String UrlFile = request.getContextPath() + "/export/" + tenFileExport;
			String UrlFileDowLoad = UrlFile.replace(".pdfs", ".pdf");

			if ( (Validator.isNotNull(shiftOrder) && shiftOrder.getAttachedFile() > 0 && DocumentUtils.existDLFileAbsPath(shiftOrder.getAttachedFile()) && messageType == MessageType.LENH_DIEU_DONG)) {
				result.put("url", tenFileExport);
				result.put("download", tenFileExport);
			} else if (flagGiayPhepRoiCangCC && Validator.isNotNull(tenFileExport) && messageType == MessageType.GIAY_PHEP_ROI_CANG_CUOI_CUNG) {
				result.put("url", tenFileExport);
				result.put("download", tenFileExport);
			} else {
				result.put("url", UrlFile);
				result.put("download", UrlFileDowLoad);
			}

		} catch (Exception e) {
			result.put("url", StringPool.BLANK);
			result.put("download", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject checkThanhPhanActionButton(ThemeDisplay themeDisplay, long documentName, int documentYear,
			int messageType, int roleType, String requestCode, HttpServletRequest actionRequest) {
		int resultShow = -1;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("data", -1);
		try {

			TempDocument temp = TempDocumentLocalServiceUtil.getByDocumentNameAndDocumentYear(documentName,
					documentYear);
			// role ke hoach vaitro = 1
			String vaiTro = StringPool.BLANK;

			if (roleType == -1) {
				vaiTro = PageType.KE_HOACH;
				if (messageType == 70) {

					UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay.getUserId());
					List<DmRepresentative> listRepres = DmRepresentativeLocalServiceUtil.findByMaritimeCode(userPort.getPortCode());
					
					JSONArray userSign = JSONFactoryUtil.createJSONArray();
					
					JSONObject userObj = JSONFactoryUtil.createJSONObject();
					
					userObj.put("representative", KeyParams.GIAM_DOC);
					
					userSign.put(userObj);
					
					for (DmRepresentative dmRepresentative : listRepres) {
						
						if (dmRepresentative.getRepLevel() == 1 || dmRepresentative.getRepLevel() == 2 || dmRepresentative.getRepLevel() == 4) {
							userObj = JSONFactoryUtil.createJSONObject();
							
							userObj.put("representative", dmRepresentative.getRepName());
							
							userSign.put(userObj);
						}
						
					}
					List<IssueShiftingOrder> lstShiftingOrder = IssueShiftingOrderLocalServiceUtil
							.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear,
									KeyParams.ID, KeyParams.ORDER_BY_DESC);

					if (lstShiftingOrder.size() > 0) {
						IssueShiftingOrder item = lstShiftingOrder.get(0);
//						for (IssueShiftingOrder item : lstShiftingOrder) {
							if (item.getRequestState() == TrangThaiBanKhaiNhapCanh.KHAI_SUA && temp.getRequestState() == ChuyenDichTrangThaiUtils.KE_HOACH_CHO_SUA_LENH_DIEU_DONG) {
								resultShow = 2;
//								break;
							}
//						}
						result.put("data", resultShow);
						result.put("userSigns", userSign);
						return result;
					} else {
						result.put("data", 2);
						result.put("userSigns", userSign);
						return result;
					}

				}
			} else if (roleType == -2) {
				vaiTro = PageType.THU_TUC;
			} else if (roleType == -3) {
				vaiTro = PageType.KE_HOACH;
			} else if (roleType == -4) {
				vaiTro = PageType.KE_HOACH;
			}

			resultShow = CheckBusinessUtil.checkRoleDisplayButton(ChuyenDichTrangThaiUtils.VAO_CANH_DAU_KHI, messageType, vaiTro, temp, requestCode,
					actionRequest);

			result.put("data", resultShow);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return result;
	}

}
