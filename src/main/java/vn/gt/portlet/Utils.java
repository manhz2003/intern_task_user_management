package vn.gt.portlet;

import java.util.ArrayList;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;

import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempPassengerList;
import vn.gt.dao.noticeandmessage.service.TempAnimalQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewEffectsDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDangerousGoodsNanifestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDeclarationOfHealthLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPassengerListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.utils.FormatData;
import vn.gt.utils.PageType;


import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.core.ThemeDisplay;

public class Utils {
	
	public static final String PARAM_VIEW_TYPE = "viewType";
	public static final String VIEW_TRANSACTION_LIST = "transactionList";
	public static final String VIEW_TRANSACTION_DETAILS = "transactionDetails";
	
	public static final String START_PAGINATION = "startPagination";
	public static final String TRANSACTION_ID = "transactionId";
	
	// params send to other Portlet
	public static final String PARAM_PORTLET_NAME = "portletName";
	public static final String PARAM_ID = "id";
	public static final String PARAM_ID_QUY_TRINH = "idQuyTrinh";
	
	public static final String CONFIG_TRANS_TO_BRMS = "vn.dtt.cmon.transToBRMS";
	public static final String CONFIG_SCHEMA_BRMS = "vn.dtt.cmon.schema.BRMS";
	
	public static final String PROCESS_STATUS_CANCELLED = "Cancelled";
	public static final String PROCESS_STATUS_STOP = "Stopped";
	public static final String PROCESS_STATUS_FAILED = "Failed";
	public static final String PROCESS_STATUS_RUNNING = "Running";
	public static final String PROCESS_STATUS_COMPLETED = "Completed";
	
	public static final String PROCESS_OFFLINEDOCUMENT_DEFAULT = "-1";
	public static final String PROCESS_OFFLINEDOCUMENT_CODITION = "0";
	public static final String PROCESS_OFFLINEDOCUMENT_CODITION_INVALID = "2";
	
	public static String getNguoiDungbyEmail(String email) {
		String tenNguoiDung = "";
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tenNguoiDung;
	}
	
	public static String getPhongbanEmail(String email) {
		String tenPhongBan = "";
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tenPhongBan;
	}
	
	public static boolean isNotNullNotEmptyNotWhiteSpace(final String string) {
		return string != null && !string.isEmpty() && !string.trim().isEmpty();
	}
	
	public static boolean hasPermissionThisTask(String curUser, String userList) {
		
		String[] endpoints = userList.split(";");
		
		for (int i = 0; i < endpoints.length; i++) {
			
			if (endpoints[i].equalsIgnoreCase(curUser)) {
				return true;
			}
		}
		return false;
	}
	
	public static User getUser(ActionRequest resourceRequest, ActionResponse httpReq) {
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);
		return themeDisplay.getUser();
	}
	
	public static Long getUserId(ActionRequest resourceRequest, ActionResponse httpReq) {
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);
		Long userId = themeDisplay.getUser().getUserId();
		
		return userId;
		
	}
	
	public static List<User> getListUser(List<User> users, List<UserPort> userPorts) {
		List<User> users2 = new ArrayList<User>();
		for (int i = 0; i < users.size(); i++) {
			boolean check = false;
			User user = users.get(i);
			for (int j = 0; j < userPorts.size(); j++) {
				UserPort userPort = userPorts.get(j);
				if (userPort.getUserId() == user.getUserId()) {
					check = true;
					break;
				}
			}
			if (!check) {
				users2.add(user);
			}
		}
		
		return users2;
	}
	
	public static List<User> getListUserWithEdit(List<User> users, List<UserPort> userPorts, User userEdit) {
		List<User> users2 = new ArrayList<User>();
		for (int i = 0; i < users.size(); i++) {
			boolean check = false;
			User user = users.get(i);
			for (int j = 0; j < userPorts.size(); j++) {
				UserPort userPort = userPorts.get(j);
				if (userPort.getUserId() == user.getUserId()) {
					check = true;
					break;
				}
			}
			if (!check) {
				users2.add(user);
			}
		}
		users2.add(userEdit);
		return users2;
	}
	
	public static String checkLoaiThuTuc(String data) {
		if (data == null || data.trim().length() == 0) {
			return MessageType.LOAT_THU_TUC_NHAP_CANH;
		}
		
		return data.trim();
	}
	
	public static int countBanKhai(String documentName, int documentYear, int businessType) {
		
		try {
			if (MessageType.BAN_KHAI_AN_NINH_TAU_BIEN == businessType) {
				int count = TempShipSecurityMessageLocalServiceUtil.countBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName), documentYear);
				return count;
				
			} else if (MessageType.BAN_KHAI_HANG_HOA == businessType) {
				int count = TempCargoDeclarationLocalServiceUtil.countBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName), documentYear);
				return count;
				
			} else if (MessageType.THONG_BAO_TAU_DEN_CANG == businessType) {
				int count = TempNoTiceShipMessageLocalServiceUtil.countBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName), documentYear);
				return count;
				
			} else if (MessageType.THONG_BAO_TAU_ROI_CANG == businessType) {
				int count = TempNoTiceShipMessageLocalServiceUtil.countBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName), documentYear);
				return count;
				
			} else if (MessageType.THONG_BAO_TAU_QUA_CANH == businessType) {
				int count = TempNoTiceShipMessageLocalServiceUtil.countBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName), documentYear);
				return count;
				
			} else if (MessageType.XAC_BAO_TAU_DEN_CANG == businessType) {
				List<TempNoticeShipMessage> resultsXacBaoTauQuaCanh = TempNoTiceShipMessageLocalServiceUtil
						.findBydocumentNameAndDocumentYearAndNoticeShipType(FormatData.convertToLong(documentName), documentYear,
								PageType.TYPE_XAC_BAO_TAU_DEN_CANG);
				return resultsXacBaoTauQuaCanh.size();
				
			} else if (MessageType.XAC_BAO_TAU_QUA_CANH == businessType) {
				List<TempNoticeShipMessage> resultsXacBaoTauQuaCanh = TempNoTiceShipMessageLocalServiceUtil
						.findBydocumentNameAndDocumentYearAndNoticeShipType(FormatData.convertToLong(documentName), documentYear,
								PageType.TYPE_XAC_BAO_TAU_DEN_CANG);
				return resultsXacBaoTauQuaCanh.size();
				
			} else if (MessageType.BAN_KHAI_CHUNG == businessType) {
				int count = TempGeneralDeclarationLocalServiceUtil.countByDocumentNameAndDocumentYear(FormatData.convertToLong(documentName),
						documentYear);
				return count;
				
			} else if (MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN == businessType) {
				List<TempCrewList> resultsDsThuyenvien2 = TempCrewListLocalServiceUtil.findBydocumentNameAnddocumentYear(
						FormatData.convertToLong(documentName), documentYear);
				return resultsDsThuyenvien2.size();
				
			} else if (MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH == businessType) {
				List<TempPassengerList> resultsDsHanhKhach2 = TempPassengerListLocalServiceUtil.findBydocumentNameAnddocumentYear(
						FormatData.convertToLong(documentName), documentYear);
				return resultsDsHanhKhach2.size();
				
			} else if (MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM == businessType) {
				List<TempDangerousGoodsManifest> resultsHangHoaNguyHiem = TempDangerousGoodsNanifestLocalServiceUtil
						.findBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName), documentYear);
				return resultsHangHoaNguyHiem.size();
				
			} else if (MessageType.BAN_KHAI_DU_TRU_CUA_TAU == businessType) {
				int count = TempShipStoresDeclarationLocalServiceUtil.countBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName),
						documentYear);
				return count;
				
			} else if (MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN == businessType) {
				int count = TempCrewEffectsDeclarationLocalServiceUtil.countBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName),
						documentYear);
				return count;
				
			} else if (MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI == businessType) {
				int count = TempDeclarationOfHealthLocalServiceUtil.countBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName),
						documentYear);
				return count;
				
			} else if (MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT == businessType) {
				int count = TempPlantQuarantineLocalServiceUtil.countBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName),
						documentYear);
				return count;
				
			} else if (MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT == businessType) {
				int count = TempAnimalQuarantineLocalServiceUtil.countBydocumentNameAnddocumentYear(FormatData.convertToLong(documentName),
						documentYear);
				return count;
			}
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
