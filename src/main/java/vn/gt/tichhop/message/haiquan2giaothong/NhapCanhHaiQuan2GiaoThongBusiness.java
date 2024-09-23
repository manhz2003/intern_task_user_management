package vn.gt.tichhop.message.haiquan2giaothong;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.liferay.core.*;


import com.fds.nsw.nghiepvu.model.*;

import com.fds.nsw.nghiepvu.noticeandmessage.service.ModifyLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueAcceptanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;

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

import com.fds.nsw.nghiepvu.model.ResultNotification;
import vn.gt.dao.result.service.ResultCompetionLocalServiceUtil;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.dao.result.service.ResultNotificationLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiBanKhai;
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiQuaCanh;
import vn.gt.utils.CallWs2HaiQuan;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;

import com.fds.nsw.kernel.exception.SystemException;


import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;

@Slf4j
public class NhapCanhHaiQuan2GiaoThongBusiness {

	// public static int _messageType; // ~> edit by dunghansome

	

	public boolean xuLyNhapCanhRoleKeHoach(long documentName, int type, int documentYear, int actionType,
			String userName, UploadPortletRequest uploadPortletRequest, ActionRequest request, ActionResponse httpReq) {
		try {
			log.info("actionType*********************" + actionType);
			// Date approvalTime = ParamUtil.getDate(resourceRequest,
			// "approvalTime", FormatData.formatDateShort3);
			List<TempDocument> tempDocuments = TempDocumentLocalServiceUtil
					.findTemDocumentByDocumentNameAndDocumentYear(documentName, documentYear);
			TempDocument tempDocument = null;

			if (tempDocuments != null && tempDocuments.size() > 0) {
				tempDocument = tempDocuments.get(0);
			} else {
				return false;
			}

			String requestCode = ParamUtil.getString(request, PageType.REQUEST_CODE);

			log.info("====xuLyNhapCanhRoleKeHoach==RequestCode==" + requestCode + "==MessageType==" + type);

			// ----------------------Click vao tung ban
			// khai----------------------
			if (type != PageType.THANH_PHAN_HO_SO) {
				// Truong hop tiep nhan truong hop Nhap Canh
				if (requestCode == null || requestCode.length() == 0) {
					requestCode = CheckBusinessUtil.getRequestCode(requestCode, Integer.valueOf(documentName + ""),
							documentYear, type);

				}
				// Thay doi trang thai bang khai
				if (type != MessageType.LENH_DIEU_DONG) {

					log.info("====type != MessageType.LENH_DIEU_DONG==requestCode==" + requestCode);

					boolean keHoachNhapCanhThayDoiTrangThaiBanKhai = keHoachNhapCanhThayDoiTrangThaiBanKhai(
							tempDocument, type, actionType, userName, request, httpReq, requestCode);
					log.info("==result==keHoachNhapCanhThayDoiTrangThaiBanKhai=="
							+ keHoachNhapCanhThayDoiTrangThaiBanKhai);

					boolean keHoachNhapCanhThayDoiTrangThaiVaTraMessageHaiQuan = keHoachNhapCanhThayDoiTrangThaiVaTraMessageHaiQuan(
							type, actionType, tempDocument, userName, requestCode, request);
					log.info("==result==keHoachNhapCanhThayDoiTrangThaiVaTraMessageHaiQuan=="
							+ keHoachNhapCanhThayDoiTrangThaiVaTraMessageHaiQuan);
				} else {

					log.info("====XU_LY_LENH_DIEU_DONG==requestCode==" + requestCode + "//" + actionType);
					log.info("actionType*********************" + actionType);
					xuLyLenhDieuDong(tempDocument, actionType, uploadPortletRequest, request, httpReq, userName);
				}

				// ----------------------Click chap nhan toan bo ho so THANH
				// PHAN HO SO----------------------
			} else {

				requestCode = tempDocument.getRequestCode();

				// Thay doi trang thai toan bo bang khai
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					log.info("~~~VAO ACTION TIEP NHAN!!!!~~~~~");

					if (tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_TIEP_NHAN || tempDocument
							.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {

						log.info("~~~VAO ACTION TIEP NHAN!!!!~~~~~TYPE  " + type);

						tempDocument.setRequestState(TrangThaiBanKhaiNhapCanh.CHO_CAP_LENH_DIEU_DONG);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);

						keHoachThayDoiTrangThaiToanBoBanKhai(tempDocument, actionType, userName, request);

						// BusinessUtils.insertOrUpdateResultMinistry((new
						// Long(documentName)).intValue(), documentYear,
						// MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
						// BusinessUtils.getPheDuyetRemark(userName));
						// BusinessUtils.insertResultHistoryMinistry((new
						// Long(documentName)).intValue(), documentYear,
						// MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
						// BusinessUtils.getPheDuyetRemark(userName));
					}

				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					log.info("~~~VAO ACTION ACTION_TYPE_TU_CHOI!!!!~~~~~");
					if (tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_TIEP_NHAN
							|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_CAP_LENH_DIEU_DONG
							|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_TIEP_NHAN || tempDocument
									.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {

						tempDocument.setRequestState(TrangThaiBanKhaiNhapCanh.TU_CHOI_TIEP_NHAN);

						// OLD String lyDoTuChoi =
						// ParamUtil.getString(resourceRequest,
						// PageType.LY_DO_SUADOI_BOSUNG);
						String lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_TU_CHOI);
						if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
							lyDoTuChoi = "System";
						}
						log.info("~~~VAO ACTION ACTION_TYPE_TU_CHOI!!!!~~~~~sendMessageTuChoiHoSoKeHoach");
						boolean resultMethod = sendMessageTuChoiHoSoKeHoach(userName, request, tempDocument,
								requestCode, lyDoTuChoi);

						log.info("==sendMessageTuChoiHoSo==method==" + resultMethod);

						if (resultMethod == true) {
							TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);

							log.info("====UPDATE SUCCESSSS============");

							BusinessUtils.insertOrUpdateResultMinistry((int)documentName,
									documentYear, MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
									BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));

							BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear,
									MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU,
									BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
						}

					}
				} else if (actionType == PageType.ACTION_TYPE_SUA_DOI) {
					suaDoiHoSoKeHoach(documentName, documentYear, userName, request, tempDocument);
				}
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Business dÃ¹ng cho cáº£ Thá»§ tá»¥c: NHAP Cáº¢NH & VÃ€O Cáº¢NG
	 */
	public boolean xuLyNhapCanhRoleThuTuc(String userlogin, long documentName, int messageType, int documentYear,
			int actionType, String userName, UploadPortletRequest requestUpload, ActionRequest request, ActionResponse httpReq) {
		try {

			// _messageType = messageType; // ~> dunghandsome them dong nay
			String requestCode = ParamUtil.getString(request, PageType.REQUEST_CODE);

			log.info("==xuLyNhapCanhRoleThuTuc==RequestCode==" + requestCode + "==ActionType==" + actionType);

			TempDocument tempDocument = TempDocumentLocalServiceUtil.getByDocumentNameAndDocumentYear(documentName,
					documentYear);
			if (tempDocument == null) {
				return false;
			}

			log.info("===THU TUC ATIONTYPE====" + actionType);

			// Truong hop tiep nhan truong hop Nhap Canh
			if (messageType != PageType.THANH_PHAN_HO_SO) {

				// chuc nang moi ACTION_TYPE_SUA_DOI, can bo hai quan thong bao
				// den doanh nhiep, BO SUNG HO SO
				if (actionType == PageType.ACTION_TYPE_SUA_DOI) {
					log.info("==============ACTIONTYE THU TUC====== SDBS" + actionType);

					// log.info("==ACTION_TYPE_SUA_DOI==truong hop can bo hang
					// hai gui thong diep sang bo giao thong, thong bao thieu ho
					// so==" +
					// "MessageType==" + messageType);
					thuTucSendMessageSuaDoi(userName, request, tempDocument);

					// boolean resultThayDoiTrangThaiBanKhai =
					// thuTucNhapCanhThayDoiTrangThaiResultDeclarationLuongSuaDoiBoSung(tempDocument,
					// messageType, actionType, userName, requestCode, request);
					// log.info("==result==thuTucNhapCanhThayDoiTrangThaiBanKhai==Luong
					// Sua Doi Bo Sung==" + resultThayDoiTrangThaiBanKhai);

				} else if (actionType == PageType.ACTION_TYPE_CHUYEN_TRA_HO_SO) {
					log.info("==============ACTION_TYPE_CHUYEN_TRA_HO_SO====== " + actionType);

					if (requestCode == null || requestCode.length() == 0) {
						requestCode = CheckBusinessUtil.getRequestCode(requestCode, Integer.valueOf(documentName + ""),
								documentYear, messageType);
					}

					IssueShiftingOrder shiftOrderLanhDaoTraLai = IssueShiftingOrderLocalServiceUtil
							.getByRequestCode(requestCode);

					if (shiftOrderLanhDaoTraLai != null) {
						// TODO Nhap Canh truong hop cho duyet
						String lyDoTraLai = ParamUtil.getString(request, PageType.LY_DO_TRA_LAI_HO_SO);
						shiftOrderLanhDaoTraLai.setStampStatus(PageType.KHONG_PHE_CHUAN);
						shiftOrderLanhDaoTraLai.setIsApproval(PageType.KHONG_PHE_CHUAN);
						shiftOrderLanhDaoTraLai.setIsCancel(PageType.KHONG_PHE_CHUAN);
						shiftOrderLanhDaoTraLai.setCancelNote(lyDoTraLai);
						IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrderLanhDaoTraLai);
						log.info("==Tra ve buoc truoc, sua noi dung lenh dieu dong==");

						BusinessUtils.updateLyDoResultNotification(userName, lyDoTraLai,
								MessageType.Y_CAU_TRA_LAI_HO_SO_LENH_DIEU_DONG, tempDocument.getMaritimeCode(),
								tempDocument.getDocumentName(), tempDocument.getDocumentYear());

					}

				} else {

					log.info("==============ACTIONTYE THU TUC====== DDHS" + actionType);
					log.info("truong hop can bo co day du ho so==PageType==" + messageType);
					// truong hop khac (cu~)
					if (requestCode == null || requestCode.length() == 0) {
						requestCode = CheckBusinessUtil.getRequestCode(requestCode, Integer.valueOf(documentName + ""),
								documentYear, messageType);
					}

					boolean resultMethod = thuTucNhapCanhTraMessageHaiQuan(messageType, actionType, tempDocument,
							userName, requestCode, request);
					log.info("==result==thuTucNhapCanhTraMessageHaiQuan==" + resultMethod);
					// Thay doi trang thai bang khai:
					// - TIEP NHAN,
					// - DOI CHIEU,
					// - TU CHOI,
					// - HUY?

					boolean resultThayDoiTrangThaiBanKhai = thuTucNhapCanhThayDoiTrangThaiBanKhai(tempDocument,
							messageType, actionType, userName, requestCode, request, requestUpload);
					log.info("==result==thuTucNhapCanhThayDoiTrangThaiBanKhai==" + resultThayDoiTrangThaiBanKhai);
				}
			} else {
				log.info("VAO TU CHON THU TUC");
				// TODO - minhhandsome xuLyNhapCanhRoleThuTuc Click menu Thanh
				// phan ho so
				thuTucThanhPhanHoSo(userlogin, documentName, documentYear, actionType, userName, request, tempDocument);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean keHoachThayDoiTrangThaiToanBoBanKhai(TempDocument tempDocument, int actionType, String userName,
			ActionRequest resourceRequest) {
		boolean result = true;
		try {
			log.info("===thayDoiTrangThaiToanBoBanKhaiKeHoach==ActionType" + actionType);
			List<ResultDeclaration> lstDeclaration = ResultDeclarationLocalServiceUtil
					.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN,
							tempDocument.getDocumentName(), tempDocument.getDocumentYear());

			if (lstDeclaration != null && lstDeclaration.size() > 0) {
				ResultDeclaration reDeclaration = lstDeclaration.get(0);
				List<TempShipSecurityMessage> ltTempShipSercurity = TempShipSecurityMessageLocalServiceUtil
						.findByRequestCode(reDeclaration.getRequestCode());

				if (ltTempShipSercurity != null && ltTempShipSercurity.size() > 0) {
					TempShipSecurityMessage tempShipSecurityMessage = ltTempShipSercurity.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN
							&& tempShipSecurityMessage
									.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN
							&& tempShipSecurityMessage
									.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI) {

						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);

						BusinessUtils.updateResultDeclaration(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN,
								tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
								TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);

						TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(tempShipSecurityMessage);

						sendChapNhan(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN,
								MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU, tempDocument, userName,
								reDeclaration.getRequestCode(), resourceRequest);
					}
				}
			}
			lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
					MessageType.THONG_BAO_TAU_DEN_CANG, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
			if (lstDeclaration != null && lstDeclaration.size() > 0) {
				ResultDeclaration resultDeclaration = lstDeclaration.get(0);
				TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
						.findTempNoTiceShipMessageByRequestCode(resultDeclaration.getRequestCode());
				if (tempNoticeShipMessage != null
						&& tempNoticeShipMessage.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN
						&& tempNoticeShipMessage.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI) {

					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						BusinessUtils.updateResultDeclaration(MessageType.THONG_BAO_TAU_DEN_CANG,
								tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
								TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);

						TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
						sendChapNhan(MessageType.THONG_BAO_TAU_DEN_CANG, MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
								tempDocument, userName, resultDeclaration.getRequestCode(), resourceRequest);
					}
				}
			}
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				lstDeclaration = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
						MessageType.XAC_BAO_TAU_DEN_CANG, tempDocument.getDocumentName(),
						tempDocument.getDocumentYear());
				if (lstDeclaration != null && lstDeclaration.size() > 0) {
					ResultDeclaration resultDeclaration = lstDeclaration.get(0);
					TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
							.findTempNoTiceShipMessageByRequestCode(resultDeclaration.getRequestCode());

					if (tempNoticeShipMessage != null
							&& tempNoticeShipMessage
									.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN
							&& tempNoticeShipMessage
									.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						BusinessUtils.updateResultDeclaration(MessageType.XAC_BAO_TAU_DEN_CANG,
								tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
								TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);

						TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
						sendChapNhan(MessageType.XAC_BAO_TAU_DEN_CANG, MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU,
								tempDocument, userName, resultDeclaration.getRequestCode(), resourceRequest);
					}
				}

			}
			// thayDoiTrangThaiToanBoBanKhaiThuTuc(actionType, requestCode);

		} catch (Exception e) {
			
			log.error(e.getMessage());
		}

		return result;
	}

	public boolean keHoachNhapCanhThayDoiTrangThaiBanKhai(TempDocument tempDocument, int messageType, int actionType,
			String userName, ActionRequest resourceRequest, ActionResponse httpReq, String requestCode) {
		boolean result = true;
		try {
			String lyDoTuChoi = ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI);
			if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
				lyDoTuChoi = "System";
			}

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);
			// InterfaceRequest interfaceRequest =
			// InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);
			// String remarks = "";
			// if (interfaceRequest != null) {
			// remarks = interfaceRequest.getRemarks();
			// }

			if (messageType == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
				List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil
						.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempShipSecurityMessage tempShipSecurityMessage = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(),
								tempDocument.getDocumentYear(), TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							// remarks =
							// BusinessUtils.getChapNhanRemark(userName);
						}

					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);

					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(),
								tempDocument.getDocumentYear(), TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
							// remarks = BusinessUtils.getTuChoiRemark(userName,
							// lyDoTuChoi);
						}

					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(tempShipSecurityMessage);

				}

			} else if (messageType == MessageType.BAN_KHAI_HANG_HOA) {
				TempCargoDeclaration tempCargoDeclaration = TempCargoDeclarationLocalServiceUtil
						.findTempCargoDeclarationByRequestCode(requestCode);
				if (tempCargoDeclaration != null) {

					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempCargoDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempCargoDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempCargoDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);

					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempCargoDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempCargoDeclarationLocalServiceUtil.updateTempCargoDeclaration(tempCargoDeclaration);
				}
			} else if (messageType == MessageType.THONG_BAO_TAU_DEN_CANG) {
				TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
						.findTempNoTiceShipMessageByRequestCode(requestCode);
				if (tempNoticeShipMessage != null) {

					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(),
								tempDocument.getDocumentYear(), TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							// remarks =
							// BusinessUtils.getChapNhanRemark(userName);
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(),
								tempDocument.getDocumentYear(), TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
							// remarks = BusinessUtils.getTuChoiRemark(userName,
							// lyDoTuChoi);
						}
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);

				}
			} else if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
				TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
						.findTempNoTiceShipMessageByRequestCode(requestCode);
				if (tempNoticeShipMessage != null) {

					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							// remarks =
							// BusinessUtils.getChapNhanRemark(userName);
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
							// remarks = BusinessUtils.getTuChoiRemark(userName,
							// lyDoTuChoi);
						}
					}
					TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
				}
			}
			if (interfaceRequest != null) {
				// Hashtable<String, String> hashtable = new Hashtable<String,
				// String>();
				// hashtable.put(InterfaceRequest2TempUtils.Remarks, remarks);
				// String sqlUpateById =
				// InterfaceRequest2TempUtils.sqlUpateById(hashtable,
				// interfaceRequest.getId());
				// int updateInterfaceRequest =
				// InterfaceRequestLocalServiceUtil.updateInterfaceRequest(sqlUpateById);
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
				// log.info("==thayDoiTrangThaiBanKhaiNhapCanhRoleKeHoach==updateInterfaceRequest=="
				// + updateInterfaceRequest);
			}

		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return result;
	}

	public void xacNhanHoanThanhThuTucNhapCanhTruongHopHuy(ActionRequest resourceRequest, long documentName,
			int documentYear) {
		ResultCompletion reCompetion = null;
		reCompetion = ResultCompetionLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
		String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip");
		String nameOfMaster = ParamUtil.getString(resourceRequest, "nameOfMaster");
		String portOfArrivalCode = ParamUtil.getString(resourceRequest, "portOfArrivalCode");
		String certificateNo = ParamUtil.getString(resourceRequest, "certificateNo");
		String maritimeReference = ParamUtil.getString(resourceRequest, "maritimeReference");
		String flagStateOfShip = ParamUtil.getString(resourceRequest, "flagStateOfShip");
		double grossTonnage = ParamUtil.getDouble(resourceRequest, "grossTonnage", 0L);
		Date approvalTime = ParamUtil.getDate(resourceRequest, "approvalTime", FormatData.formatDateShort3);
		String approvalName = ParamUtil.getString(resourceRequest, "approvalName");

		try {
			log.info("xacNhanHoanThanhThuTucNhapCanhTruongHopHuy " + documentName + "  documentYear  " + documentYear);
			if (reCompetion == null) {
				log.info("========vao insert====xacNhanHoanThanhThuTucNhapCanhTruongHopHuy=====");
				reCompetion = new ResultCompletion();
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
				reCompetion.setResponseStatusCVHH(MessageType.TU_CHOI);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils.getRemarkTuChoiTB(
						PortalUtil.getUser(resourceRequest).getEmailAddress(), "Huy ho so", approvalTime));

				reCompetion.setDocumentName(documentName);
				reCompetion.setDocumentYear(documentYear);
				reCompetion.setRequestCode(UUID.randomUUID().toString());

				ResultCompetionLocalServiceUtil.addResultCompetion(reCompetion);
			} else {
				log.info("xacNhanHoanThanhThuTucNhapCanhTruongHopHuy vao update");
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
				reCompetion.setResponseStatusCVHH(MessageType.TU_CHOI);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils.getRemarkTuChoiTB(
						PortalUtil.getUser(resourceRequest).getEmailAddress(), "Huy ho so", approvalTime));

				ResultCompetionLocalServiceUtil.updateResultCompetion(reCompetion);
				log.info("UPDATE XONG setRequestState" + reCompetion.getRequestState());
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}

	}

	public void xacNhanHoanThanhThuTucNhapCanhTruongHopHuyUpgrade(UploadPortletRequest resourceRequest, long documentName,
			int documentYear) {
		ResultCompletion reCompetion = null;
		reCompetion = ResultCompetionLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
		String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip");
		String nameOfMaster = ParamUtil.getString(resourceRequest, "nameOfMaster");
		String portOfArrivalCode = ParamUtil.getString(resourceRequest, "portOfArrivalCode");
		String certificateNo = ParamUtil.getString(resourceRequest, "certificateNo");
		String maritimeReference = ParamUtil.getString(resourceRequest, "maritimeReference");
		String flagStateOfShip = ParamUtil.getString(resourceRequest, "flagStateOfShip");
		double grossTonnage = ParamUtil.getDouble(resourceRequest, "grossTonnage", 0L);
		Date approvalTime = ParamUtil.getDate(resourceRequest, "approvalTime", FormatData.formatDateShort3);
		String approvalName = ParamUtil.getString(resourceRequest, "approvalName");

		try {
			log.info("xacNhanHoanThanhThuTucNhapCanhTruongHopHuy " + documentName + "  documentYear  " + documentYear);
			if (reCompetion == null) {
				log.info("========vao insert====xacNhanHoanThanhThuTucNhapCanhTruongHopHuy=====");
				reCompetion = new ResultCompletion();
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
				reCompetion.setResponseStatusCVHH(MessageType.TU_CHOI);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils.getRemarkTuChoiTB(
						PortalUtil.getUser(resourceRequest).getEmailAddress(), "Huy ho so", approvalTime));

				reCompetion.setDocumentName(documentName);
				reCompetion.setDocumentYear(documentYear);
				reCompetion.setRequestCode(UUID.randomUUID().toString());

				ResultCompetionLocalServiceUtil.addResultCompetion(reCompetion);
			} else {
				log.info("xacNhanHoanThanhThuTucNhapCanhTruongHopHuy vao update");
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
				reCompetion.setResponseStatusCVHH(MessageType.TU_CHOI);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils.getRemarkTuChoiTB(
						PortalUtil.getUser(resourceRequest).getEmailAddress(), "Huy ho so", approvalTime));

				ResultCompetionLocalServiceUtil.updateResultCompetion(reCompetion);
				log.info("UPDATE XONG setRequestState" + reCompetion.getRequestState());
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}

	}
	
	public void xacNhanHoanThanhThuTucNhapCanhUpgrade(UploadPortletRequest resourceRequest, long documentName, int documentYear) {
		try {
			log.info("===xacNhanHoanThanhThuTucNhapCanh==documentName=" + documentName + "----documentYear---"
					+ documentYear);
			
			// add payment request
			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip");
			String nameOfMaster = ParamUtil.getString(resourceRequest, "nameOfMaster");
			String portOfArrivalCode = ParamUtil.getString(resourceRequest, "portOfArrivalCode");
			String certificateNo = ParamUtil.getString(resourceRequest, "certificateNo");
			String maritimeReference = ParamUtil.getString(resourceRequest, "maritimeReference");
			String flagStateOfShip = ParamUtil.getString(resourceRequest, "flagStateOfShip");
			double grossTonnage = ParamUtil.getDouble(resourceRequest, "grossTonnage", 0L);
			Date approvalTime = ParamUtil.getDate(resourceRequest, "approvalTime", FormatData.formatDateShort3);
			String approvalName = ParamUtil.getString(resourceRequest, "approvalName");

			ResultCompletion reCompetion = ResultCompetionLocalServiceUtil
					.findByDocumentNameAndDocumentYear(documentName, documentYear);
			if (reCompetion == null) {
				log.info("========vao insert====xacNhanHoanThanhThuTucNhapCanh=====");
				reCompetion = new ResultCompletion();
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				reCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), approvalTime));

				reCompetion.setDocumentName(documentName);
				reCompetion.setDocumentYear(documentYear);
				reCompetion.setRequestCode(UUID.randomUUID().toString());

				ResultCompetionLocalServiceUtil.addResultCompetion(reCompetion);
			} else {
				log.info("========vao update===xacNhanHoanThanhThuTucNhapCanh======");
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				reCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), approvalTime));

				ResultCompetionLocalServiceUtil.updateResultCompetion(reCompetion);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
	}
	public void xacNhanHoanThanhThuTucNhapCanh(ActionRequest resourceRequest, long documentName, int documentYear) {
		try {
			log.info("===xacNhanHoanThanhThuTucNhapCanh==documentName=" + documentName + "----documentYear---"
					+ documentYear);

			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip");
			String nameOfMaster = ParamUtil.getString(resourceRequest, "nameOfMaster");
			String portOfArrivalCode = ParamUtil.getString(resourceRequest, "portOfArrivalCode");
			String certificateNo = ParamUtil.getString(resourceRequest, "certificateNo");
			String maritimeReference = ParamUtil.getString(resourceRequest, "maritimeReference");
			String flagStateOfShip = ParamUtil.getString(resourceRequest, "flagStateOfShip");
			double grossTonnage = ParamUtil.getDouble(resourceRequest, "grossTonnage", 0L);
			Date approvalTime = ParamUtil.getDate(resourceRequest, "approvalTime", FormatData.formatDateShort3);
			String approvalName = ParamUtil.getString(resourceRequest, "approvalName");

			ResultCompletion reCompetion = ResultCompetionLocalServiceUtil
					.findByDocumentNameAndDocumentYear(documentName, documentYear);
			if (reCompetion == null) {
				log.info("========vao insert====xacNhanHoanThanhThuTucNhapCanh=====");
				reCompetion = new ResultCompletion();
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				reCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), approvalTime));

				reCompetion.setDocumentName(documentName);
				reCompetion.setDocumentYear(documentYear);
				reCompetion.setRequestCode(UUID.randomUUID().toString());

				ResultCompetionLocalServiceUtil.addResultCompetion(reCompetion);
			} else {
				log.info("========vao update===xacNhanHoanThanhThuTucNhapCanh======");
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);

				reCompetion.setRequestState(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				reCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				reCompetion.setResponseTimeCVHH(approvalTime);
				reCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), approvalTime));

				ResultCompetionLocalServiceUtil.updateResultCompetion(reCompetion);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
	}

	private void thuTucSendMessageSuaDoi(String userName, ActionRequest request, TempDocument tempDocument)
			throws SystemException, RemoteException {
		log.info("==sendMessageSuaDoi==userName==" + userName);

		try {
			BusinessUtils businessUtils = new BusinessUtils();

			// TODO - minhhandsome issue 98
			String lydo = ParamUtil.getString(request, PageType.LY_DO_SUADOI_BOSUNG);
			int businessTypeCode = MessageType.BO_SUNG_THU_TUC;
			BusinessUtils.updateLyDoResultNotification(userName, lydo, businessTypeCode, tempDocument.getMaritimeCode(),
					tempDocument.getDocumentName(), tempDocument.getDocumentYear());

			String xmlData = createXmlSuaDoiBoXung(tempDocument, userName, request, businessUtils);
//			IMTService imtService = CallWs2HaiQuan.getIMTSercice();
			if (xmlData != null && xmlData.length() > 0) {
				businessUtils.insertHistorySendMessage(xmlData);

				String xml = "";
				if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
						|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
					// log.info(" ----CAll receiveFromInland------- ");
					// xml = imtService.receiveFromInland(xmlData);

				} else {
					// log.info(" ----CAll receiveResultFromMT------- ");
					// xml = imtService.receiveResultFromMT(xmlData);
				}

				// log.info("==sendMessageSuaDoi==Du lieu NHAN HQMC day==" +
				// xml);
				businessUtils.insertHistoryReceiveMessageResponse(xml);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	public boolean thuTucNhapCanhThayDoiTrangThaiBanKhai(TempDocument tempDocument, int messageType, int actionType,
			String userName, String requestCode, ActionRequest request, UploadPortletRequest requestUpload) {
		boolean result = true;
		try {
			log.info("==thayDoiTrangThaiBanKhaiNhapCanhRoleThuTuc==actionType==" + actionType);
			if (messageType == MessageType.BAN_KHAI_CHUNG) {
				List<TempGeneralDeclaration> lstGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil
						.findByRequestCode(requestCode);
				if (lstGeneralDeclaration != null && lstGeneralDeclaration.size() > 0) {
					TempGeneralDeclaration declaration = lstGeneralDeclaration.get(0);

					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						declaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest==Remarks==" + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							log.info("interfaceRequest==Remarks==" + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}

					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						declaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);

						log.info("===Docname == " + tempDocument.getDocumentName() + "==docYear=="
								+ tempDocument.getDocumentYear() + "==RequestCode==" + requestCode);
						if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
								|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)
								|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)
								|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {

							ResultDeclaration resultDeclaration = ResultDeclarationLocalServiceUtil
									.findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
											MessageType.BAN_KHAI_CHUNG, tempDocument.getDocumentName(),
											tempDocument.getDocumentYear(), requestCode);
							if (resultDeclaration != null) {
								resultDeclaration.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
								try {
									ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
									log.info(" Updated");
								} catch (SystemException e) {
									log.info("Catch Update");
									
								}
							} else {
								log.info("resultDeclaration is null");
							}
						}

					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						declaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest==Remarks==" + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Tu Choi"));
							log.info("interfaceRequest==Remarks==" + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}

					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						declaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest==Remarks==" + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Tu Choi"));
							log.info("interfaceRequest==Remarks==" + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					}
					TempGeneralDeclarationLocalServiceUtil.updateTempGeneralDeclaration(declaration);
				}
			} else if (messageType == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
				if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil
							.findByRequestCode(requestCode);
					if (results != null && results.size() > 0) {
						TempShipSecurityMessage tempShipSecurityMessage = results.get(0);

						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
						TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(tempShipSecurityMessage);
					}
				}

			} else if (messageType == MessageType.XAC_NHAN_HOAN_THANH_THU_TUC) {
				if (actionType == PageType.ACTION_TYPE_HOAN_THANH_THU_TUC) {
					xacNhanHoanThanhThuTucNhapCanhUpgrade(requestUpload, tempDocument.getDocumentName(),
							tempDocument.getDocumentYear());
					tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					xacNhanHoanThanhThuTucNhapCanhTruongHopHuyUpgrade(requestUpload, tempDocument.getDocumentName(),
							tempDocument.getDocumentYear());
					tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);

				}
				// xacNhanHoanThanhThuTucNhapCanh(resourceRequest,
				// tempDocument.getDocumentName(),
				// tempDocument.getDocumentYear());
				// tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);

			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN) {

				List<TempCrewList> crewListes = TempCrewListLocalServiceUtil.findByRequestCode(requestCode);
				if (crewListes != null && crewListes.size() > 0) {
					TempCrewList crew = crewListes.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						crew.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						crew.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);

						log.info("===Docname == " + tempDocument.getDocumentName() + "==docYear=="
								+ tempDocument.getDocumentYear() + "==RequestCode==" + requestCode);
						if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
								|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)
								|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)
								|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
							ResultDeclaration resultDeclaration = ResultDeclarationLocalServiceUtil
									.findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
											MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN, tempDocument.getDocumentName(),
											tempDocument.getDocumentYear(), requestCode);
							if (resultDeclaration != null) {
								resultDeclaration.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
								try {
									ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
									log.info(" Updated");
								} catch (SystemException e) {
									log.info("Catch Update");
									
								}
							} else {
								log.info("resultDeclaration is null");
							}
						}

					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						crew.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						crew.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempCrewListLocalServiceUtil.updateTempCrewList(crew);
				}
			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH) {
				List<TempPassengerList> passengerListes = TempPassengerListLocalServiceUtil
						.findByRequestCode(requestCode);
				if (passengerListes != null && passengerListes.size() > 0) {
					TempPassengerList passenger = passengerListes.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						passenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							// log.info("interfaceRequest.getRemarks() " +
							// interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							// log.info("interfaceRequest.getRemarks() " +
							// interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						passenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						passenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Tu Choi"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						passenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);

						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Huy"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					}
					TempPassengerListLocalServiceUtil.updateTempPassengerList(passenger);
				}
			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM) {
				// TODO bo truong hop check DOI CHIEU -
				// BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM
				List<TempDangerousGoodsManifest> results = TempDangerousGoodsNanifestLocalServiceUtil
						.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempDangerousGoodsManifest dangerous = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						dangerous.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);

						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);
						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						dangerous.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						dangerous.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Tu Choi"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						dangerous.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Huy"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					}
					TempDangerousGoodsNanifestLocalServiceUtil.updateTempDangerousGoodsNanifest(dangerous);
				}
			} else if (messageType == MessageType.BAN_KHAI_DU_TRU_CUA_TAU) {
				List<TempShipStoresDeclaration> results = TempShipStoresDeclarationLocalServiceUtil
						.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempShipStoresDeclaration tempShipStoresDeclaration = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Tu Choi"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Huy"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					}
					TempShipStoresDeclarationLocalServiceUtil
							.updateTempShipStoresDeclaration(tempShipStoresDeclaration);
				}
			} else if (messageType == MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN) {
				List<TempCrewEffectsDeclaration> results = TempCrewEffectsDeclarationLocalServiceUtil
						.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempCrewEffectsDeclaration tempCrewEffectsDeclaration = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);

						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Tu Choi"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Huy"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					}
					TempCrewEffectsDeclarationLocalServiceUtil
							.updateTempCrewEffectsDeclaration(tempCrewEffectsDeclaration);
				}
			} else if (messageType == MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI) {
				List<TempDeclarationOfHealth> results = TempDeclarationOfHealthLocalServiceUtil
						.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempDeclarationOfHealth tempDeclarationOfHealth = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							// log.info("interfaceRequest.getRemarks() " +
							// interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Tu Choi"));
							// log.info("interfaceRequest.getRemarks() " +
							// interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							// log.info("interfaceRequest.getRemarks() " +
							// interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Huy"));
							// log.info("interfaceRequest.getRemarks() " +
							// interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					}
					TempDeclarationOfHealthLocalServiceUtil.updateTempDeclarationOfHealth(tempDeclarationOfHealth);
				}
			} else if (messageType == MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT) {
				List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil
						.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempShipSecurityMessage tempShipSecurityMessage = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							// log.info("interfaceRequest.getRemarks() " +
							// interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							// log.info("interfaceRequest.getRemarks() " +
							// interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Tu Choi"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Huy"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					}
					TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(tempShipSecurityMessage);
				}
			} else if (messageType == MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT) {
				List<TempPlantQuarantine> results = TempPlantQuarantineLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempPlantQuarantine tempPlantQuarantine = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Tu choi"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
						InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
								.findByRequestCode(requestCode);

						if (interfaceRequest != null) {
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, "Huy"));
							log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
							InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
						}
					}
					TempPlantQuarantineLocalServiceUtil.updateTempPlantQuarantine(tempPlantQuarantine);
				}
			} else if (messageType == MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN) {
				List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil
						.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempShipSecurityMessage tempShipSecurityMessage = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(tempShipSecurityMessage);
				}
			}
			if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(),
						tempDocument.getDocumentYear(), TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			}

		} catch (Exception e) {
			
			log.error(e.getMessage());
		}

		return result;
	}

	private void thuTucThanhPhanHoSo(String userlogin, long documentName, int documentYear, int actionType,
			String userName, ActionRequest resourceRequest, TempDocument tempDocument) throws SystemException {

		String requestCode = tempDocument.getRequestCode();

		log.info("==thanhPhanHoSo==ActionType==" + actionType);
		log.info("==thanhPhanHoSo==ActionType==actionType == PageType.ACTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU=" + (actionType == PageType.ACTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU));

		if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {

			tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC);
			BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear,
					MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU, BusinessUtils.getRemarkPheDuyet(userName));

			BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear,
					MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU, BusinessUtils.getRemarkPheDuyet(userName));

		} else if (actionType == PageType.ACTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU) {

			String lyDoTuChoi = ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI);
			if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
				lyDoTuChoi = "System";
			}
			System.out.println("NhapCanhHaiQuan2GiaoThongBusiness.thuTucThanhPhanHoSo()"+tempDocument);
			tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.TAM_DUNG_LAM_THU_TUC_DIEN_TU);
			BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear,
					MessageType.FUNCTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU, BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));

			BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear,
					MessageType.FUNCTION_TAM_DUNG_LAM_THU_TUC_DIEN_TU, BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
			System.out.println("NhapCanhHaiQuan2GiaoThongBusiness.thuTucThanhPhanHoSo()"+tempDocument);
		} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
			// action tu choi, lai su dung yeu cau bo xung, trong cung message
			// reject
			log.info("VAO  PageType.ACTION_TYPE_TU_CHOI");
			tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.TU_CHOI_TIEP_NHAN);

			String lyDoTuChoi = ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI);
			if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
				lyDoTuChoi = "System";
			}
			BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear,
					MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH,
					BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
			BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear,
					MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH,
					BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));

			insertOrUpdateResultNotification(tempDocument, userName, lyDoTuChoi, MessageType.BO_SUNG_THU_TUC);

		} else if (actionType == PageType.ACTION_TYPE_HOAN_THANH_THU_TUC) {
			xacNhanHoanThanhThuTucNhapCanh(resourceRequest, tempDocument.getDocumentName(),
					tempDocument.getDocumentYear());
			tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
		} else if (actionType == PageType.ACTION_TYPE_HUY) {

			log.info("==ACTION_TYPE_HUY==");
			String lyDoHuyNC99 = ParamUtil.getString(resourceRequest, PageType.HUY_HO_SO);
			if (lyDoHuyNC99 == null || lyDoHuyNC99.length() == 0) {
				lyDoHuyNC99 = "lyDoHuyNC99";
			}
			log.info("==lyDoHuyNC99==" + lyDoHuyNC99);
			xacNhanHoanThanhThuTucNhapCanhTruongHopHuy(resourceRequest, tempDocument.getDocumentName(),
					tempDocument.getDocumentYear());
			tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
			BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear,
					MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC, BusinessUtils.getRemarkHuy(userName, lyDoHuyNC99));
			BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear,
					MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC, BusinessUtils.getRemarkHuy(userName, lyDoHuyNC99));

			// update ResultNotification
			BusinessUtils.updateLyDoResultNotification(userlogin, lyDoHuyNC99, MessageType.HO_SO,
					tempDocument.getMaritimeCode(), documentName, documentYear);

		}
		thuTucNhapCanhTraMessageHaiQuan(MessageType.HO_SO, actionType, tempDocument, userName, requestCode,
				resourceRequest);

		// Thay doi trang thai bang khai
		System.out.println("NhapCanhHaiQuan2GiaoThongBusiness.thuTucThanhPhanHoSo()"+tempDocument);
		TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
		log.info("VAO THAY DOI TT BAN KHAi");
	}

	public boolean thuTucNhapCanhTraMessageHaiQuan(int messageType, int actionType, TempDocument tempDocument,
			String userName, String requestCode, ActionRequest request) {
		boolean result = true;
		try {

			log.info("==thuTucNhapCanhTraMessageHaiQuan==messageType==" + messageType + "==actionType==" + actionType
					+ "==DocumentStatusCode==" + tempDocument.getDocumentStatusCode());

			String xmlData = "";
			// String xmlDataThem = "";

			// IMTService imtService = CallWs2HaiQuan.getIMTSercice();

			BusinessUtils businessUtils = new BusinessUtils();

			if (messageType == MessageType.LENH_DIEU_DONG) {
				requestCode = tempDocument.getRequestCode();
			}

			if (requestCode != null) {
				InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);

				if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					log.info("==traMessageHaiQuanTruongHopNhapCanhThuTuc==ACTION_TYPE_TU_CHOI==");

					String function = MessageType.FUNCTION_TU_SUA_DOI_BO_XUNG_THU_TUC;
					String functionNC = MessageType.FUNCTION_TU_SUA_DOI_BO_SUNG_THU_TUC;
					// log.info("------_messageType==========" + _messageType);

					// --------------------------
					if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_NHAP_CANH)) {

						Header header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function,
								messageType, userName, interfaceRequest);
						log.info("=======go to dung.le Sent Message VAO CANG====================");
						xmlData = tuChoiHoSo(messageType, function, xmlData, businessUtils, header, tempDocument,
								request);

						// -------------------------
					} else if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
						Header header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO, functionNC,
								messageType, userName, interfaceRequest);

						log.info("=======go to dung.le Sent Message TAU VAO====================");

						xmlData = tuChoiHoSo(messageType, functionNC, xmlData, businessUtils, header, tempDocument,
								request);
					}

				} else if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					log.info("==traMessageHaiQuanTruongHopNhapCanhThuTuc==ACTION_TYPE_TIEP_NHAN==");

					String function = MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU;
					// String function1=
					// MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU_THEM;

					// -----------------------------------
					if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_NHAP_CANH)) {
						log.info(
								"=====================VAO ==LOAT_THU_TUC_NHAP_CANH---CÃ¡c giáº¥y tá»� pháº£i xuáº¥t trÃ¬nh---");
						Header header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function,
								messageType, userName, interfaceRequest);
						xmlData = tiepNhanHoSo(messageType, function, xmlData, businessUtils, header, tempDocument,
								request);

						// -------------------------
					} else if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
						// log.info("=====================VAO
						// traMessageHaiQuanTruongHopNhapCanhThuTuc==ACTION_TYPE_TIEP_NHAN=
						// 444444 ");

						Header header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO, function,
								messageType, userName, interfaceRequest);

						xmlData = tiepNhanHoSo(messageType, function, xmlData, businessUtils, header, tempDocument,
								request);
						// Header header1 =
						// BusinessUtils.createHeader(tempDocument,
						// MessageType.TAU_VAO, function1, messageType,
						// userName,
						// interfaceRequest);
						// xmlDataThem = tiepNhanHoSoThem(messageType,
						// function1, xmlDataThem, businessUtils, header1,
						// tempDocument, request);
					}

					if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
						// log.info("=====================VAO
						// traMessageHaiQuanTruongHopNhapCanhThuTuc==ACTION_TYPE_TIEP_NHAN=
						// XAC_BAO_TAU_DEN_CANG ");
						tempDocument.setRequestState(TrangThaiBanKhaiNhapCanh.CHO_CAP_LENH_DIEU_DONG);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					}

				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					// log.info("==traMessageHaiQuanTruongHopNhapCanhThuTuc==ACTION_TYPE_HUY==");

					xmlData = huyHoSo(messageType, xmlData, businessUtils, tempDocument, userName, interfaceRequest,
							request);

					if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
						tempDocument.setRequestState(TrangThaiBanKhaiNhapCanh.DUNG_CAP_LENH_DIEU_DONG);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					}

				} else if (actionType == PageType.ACTION_TYPE_HOAN_THANH_THU_TUC) {
					// log.info("==traMessageHaiQuanTruongHopNhapCanhThuTuc==ACTION_TYPE_HOAN_THANH_THU_TUC==");

					String function = MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC;

					if (messageType == MessageType.XAC_NHAN_HOAN_THANH_THU_TUC) {
						messageType = MessageType.HO_SO;
						xacNhanHoanThanhThuTucNhapCanh(request, tempDocument.getDocumentName(),
								tempDocument.getDocumentYear());
					}

					Header header = null;
					if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
						header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO, function,
								messageType, userName, interfaceRequest);
						xmlData = tiepNhanHoSo(messageType, function, xmlData, businessUtils, header, tempDocument,
								request);
						// log.info("XAC_NHAN_HOAN_THANH_THU_TUC VAO CANG");
					} else {
						header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function, messageType,
								userName, interfaceRequest);
						xmlData = tiepNhanHoSo(messageType, function, xmlData, businessUtils, header, tempDocument,
								request);
						// log.info("XAC_NHAN_HOAN_THANH_THU_TUC NHAP CANH");
					}

				}
			}

			// log.info("==Du lieu Gui HAi Quan==");
			// log.info(xmlData);
			// log.info("==Du lieu Gui HAi Quan THEM==");
			// log.info(xmlDataThem);
			if (xmlData != null && xmlData.length() > 0) {

				// Chua ro nghiepvu insert nhu the nao (dung.le)
				boolean insertHistorySendMessage = businessUtils.insertHistorySendMessage(xmlData);
				// log.info("==thuTucNhapCanhTraMessageHaiQuan==insertHistorySendMessage=="
				// + insertHistorySendMessage);

				// String xml = imtService.receiveResultFromMT(xmlData);
				String xml = "";
				if (tempDocument.getDocumentTypeCode().equals((MessageType.LOAT_THU_TUC_VAO_CANG))
						|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
					// log.info(" ----CAll receiveFromInland------- ");
					// resevice mess from our message!
					// xml = imtService.receiveFromInland(xmlData);

				} else {
					// log.info(" ----CAll receiveResultFromMT------- ");
					// xml = imtService.receiveResultFromMT(xmlData);
				}

				// log.info("=======Du lieu NHAN HQMC day ========" + xml);
				boolean insertHistoryReceiveMessageResponse = businessUtils.insertHistoryReceiveMessageResponse(xml);
				// log.info("==thuTucNhapCanhTraMessageHaiQuan==insertHistoryReceiveMessageResponse=="
				// + insertHistoryReceiveMessageResponse);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}

	// TODO Yeu cau bo sung co cac ban khai
	public void thuTucGuiBanTinYeuCauBoSungBanKhai(long documentName, int documentYear, int messageType, int actionType,
			ActionRequest request) {

		log.info("===thuTucGuiBanTinYeuCauBoSungBanKhai====documentName==" + documentName + "==documentYear=="
				+ documentYear);
		log.info("===thuTucGuiBanTinYeuCauBoSungBanKhai====messageType===" + messageType + "==actionType=="
				+ actionType);

		List<TempDocument> lstTempDocument = TempDocumentLocalServiceUtil
				.findTemDocumentByDocumentNameAndDocumentYear(documentName, documentYear);
		TempDocument tempDocument = null;

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		String requestCode = ParamUtil.getString(request, PageType.REQUEST_CODE);
		String userName = themeDisplay.getUser().getEmailAddress();
		String lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_YEU_CAU_BO_SUNG);

		if (lstTempDocument != null && lstTempDocument.size() > 0) {
			// Insert bang.
			ResultNotification notification = new ResultNotification();

			notification.setMaritimeCode(lstTempDocument.get(0).getMaritimeCode());
			notification.setRequestState(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);

			if (messageType == MessageType.BAN_KHAI_CHUNG || messageType == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN
					|| messageType == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH
					|| messageType == MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM
					|| messageType == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
				notification.setBusinessTypeCode(messageType);
			} else {
				notification.setBusinessTypeCode(5); // Yeu cau bo sung
			}

			notification.setRole(4);
			notification.setResponse(lyDoTuChoi);
			notification.setDocumentName(documentName);
			notification.setDocumentYear(documentYear);
			notification.setResponseTime(new Date());
			notification.setOfficerName(userName);
			notification.setLatestDate(new Date());
			notification.setIsReply(0);
			notification.setRequestCode(UUID.randomUUID().toString());

			try {
				ResultNotificationLocalServiceUtil.addResultNotification(notification);
			} catch (Exception e) {
				
				log.error(e.getMessage());
			}

			// Gui ban tin
			tempDocument = lstTempDocument.get(0);
			if (requestCode == null || requestCode.length() == 0) {
				requestCode = CheckBusinessUtil.getRequestCode(requestCode, Integer.valueOf(documentName + ""),
						documentYear, messageType);
			}
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);

			String xmlData = "";
			// IMTService imtService = CallWs2HaiQuan.getIMTSercice();
			BusinessUtils businessUtils = new BusinessUtils();
			log.info("LOAT_THU_TUC----" + tempDocument.getDocumentTypeCode());
			int loatThuTuc = MessageType.NHAP_CANH;

			if (tempDocument.getDocumentTypeCode().compareToIgnoreCase(MessageType.LOAT_THU_TUC_NHAP_CANH) == 0) {
				loatThuTuc = MessageType.NHAP_CANH;
			} else if (tempDocument.getDocumentTypeCode()
					.compareToIgnoreCase(MessageType.LOAT_THU_TUC_XUAT_CANH) == 0) {
				loatThuTuc = MessageType.XUAT_CANH;
			} else if (tempDocument.getDocumentTypeCode().compareToIgnoreCase(MessageType.LOAT_THU_TUC_QUA_CANH) == 0) {
				loatThuTuc = MessageType.QUA_CANH;
			} else if (tempDocument.getDocumentTypeCode().compareToIgnoreCase(MessageType.LOAT_THU_TUC_ROI_CANG) == 0) {
				loatThuTuc = MessageType.TAU_RA;
			} else if (tempDocument.getDocumentTypeCode().compareToIgnoreCase(MessageType.LOAT_THU_TUC_VAO_CANG) == 0) {
				loatThuTuc = MessageType.TAU_VAO;
			} else if (tempDocument.getDocumentTypeCode()
					.compareToIgnoreCase(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND) == 0) {
				loatThuTuc = MessageType.TAU_RA_PTTND;
			} else if (tempDocument.getDocumentTypeCode()
					.compareToIgnoreCase(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) == 0) {
				loatThuTuc = MessageType.TAU_VAO_PTTND;
			}

			if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				String function = MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU;
				String function25 = MessageType.FUNCTION_THONG_BAO_BO_SUNG;
				Header header = null;
				if (messageType == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {

					log.info("VAO BO sung ---------------BAN_KHAI_AN_NINH_TAU_BIEN");

					header = BusinessUtils.createHeader(tempDocument, loatThuTuc, function, messageType, userName,
							interfaceRequest);
				} else {

					// if (tempDocument.getDocumentTypeCode().equals("5") ||
					if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)
							|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
							|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)
							|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {

						log.info("VAO BO LOAT_THU_TUC_ROI_CANG---LOAT_THU_TUC_VAO_CANG");
						header = BusinessUtils.createHeaderInland(tempDocument, loatThuTuc, function25,
								MessageType.HO_SO, userName, interfaceRequest);

					} else {
						// log.info("VAO BO SUNGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
						// ELSEEEEEEEEEEEEEEE");
						header = BusinessUtils.createHeader(tempDocument, loatThuTuc, function, MessageType.HO_SO,
								userName, interfaceRequest);

					}
					xmlData = yeuCauBoSungHoSo(messageType, function, xmlData, businessUtils, header, tempDocument,
							request);

				}

			}

			try {
				if (messageType == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
					TempShipSecurityMessage shipSecurity = null;
					if (requestCode != null && requestCode.trim().length() > 0) {
						shipSecurity = TempShipSecurityMessageLocalServiceUtil.getByRequestCode(requestCode);
					} else {
						requestCode = CheckBusinessUtil.getRequestCode(requestCode, (int) documentName, documentYear,
								messageType);
						if (requestCode.trim().length() > 0) {
							List<TempShipSecurityMessage> lstShipSecurity = TempShipSecurityMessageLocalServiceUtil
									.findByRequestCode(requestCode);
							if (lstShipSecurity != null && lstShipSecurity.size() > 0) {
								shipSecurity = lstShipSecurity.get(0);
							}
						}
					}
					if (shipSecurity != null) {
						shipSecurity.setRequestState(TrangThaiBanKhaiNhapCanh.SUA_DOI_BO_XUNG);
						TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(shipSecurity);
					}

					try {
						String remarks = BusinessUtils.getRemarkBoSung(userName, lyDoTuChoi);
						interfaceRequest.setRemarks(remarks);
						InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
					} catch (Exception e) {
						
						log.error(e.getMessage());
					}
				}

				// Ban khai hanh khach.
				if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH) {
					TempPassengerList tempPassengerList = null;
					if (requestCode != null && requestCode.trim().length() > 0) {
						List<TempPassengerList> liTempShipSecurityMessages = TempPassengerListLocalServiceUtil
								.findByRequestCode(requestCode);
						if (liTempShipSecurityMessages != null && liTempShipSecurityMessages.size() > 0) {
							tempPassengerList = liTempShipSecurityMessages.get(0);
						}
					} else {
						requestCode = CheckBusinessUtil.getRequestCode(requestCode, (int) documentName, documentYear,
								messageType);
						if (requestCode.trim().length() > 0) {
							List<TempPassengerList> liTempShipSecurityMessages = TempPassengerListLocalServiceUtil
									.findByRequestCode(requestCode);
							if (liTempShipSecurityMessages != null && liTempShipSecurityMessages.size() > 0) {
								tempPassengerList = liTempShipSecurityMessages.get(0);
							}
						}
					}
					if (tempPassengerList != null) {
						tempPassengerList.setRequestState(TrangThaiBanKhaiNhapCanh.SUA_DOI_BO_XUNG);
						TempPassengerListLocalServiceUtil.updateTempPassengerList(tempPassengerList);
					}
				}

				// Danh sach thuyen vien
				if (messageType == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN) {
					TempCrewList tempCrewList = null;
					if (requestCode != null && requestCode.trim().length() > 0) {
						List<TempCrewList> liTempCrewLists = TempCrewListLocalServiceUtil
								.findByRequestCode(requestCode);
						if (liTempCrewLists != null && liTempCrewLists.size() > 0) {
							tempCrewList = liTempCrewLists.get(0);
						}
					} else {
						requestCode = CheckBusinessUtil.getRequestCode(requestCode, (int) documentName, documentYear,
								messageType);
						if (requestCode.trim().length() > 0) {
							List<TempCrewList> liTempCrewLists = TempCrewListLocalServiceUtil
									.findByRequestCode(requestCode);
							if (liTempCrewLists != null && liTempCrewLists.size() > 0) {
								tempCrewList = liTempCrewLists.get(0);
							}
						}
					}
					if (tempCrewList != null) {
						tempCrewList.setRequestState(TrangThaiBanKhaiNhapCanh.SUA_DOI_BO_XUNG);
						TempCrewListLocalServiceUtil.updateTempCrewList(tempCrewList);
					}
					// (BKC, DSTV, BKHH nguy hiem, 3 cai nay gui chung voi nhau
					// trong message
					// try {
					// String remarks = BusinessUtils.getBoSungRemark(userName,
					// lyDoTuChoi);
					// interfaceRequest.setRemarks(remarks);
					// InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
					// } catch (Exception e) {
					// 
					// log.error(e.getMessage());
					// }
				}

				// Ban khai chung
				if (messageType == MessageType.BAN_KHAI_CHUNG) {
					TempGeneralDeclaration tempGeneralDeclaration = null;
					if (requestCode != null && requestCode.trim().length() > 0) {
						List<TempGeneralDeclaration> listGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil
								.findByRequestCode(requestCode);
						if (listGeneralDeclaration != null && listGeneralDeclaration.size() > 0) {
							tempGeneralDeclaration = listGeneralDeclaration.get(0);
						}
					} else {
						requestCode = CheckBusinessUtil.getRequestCode(requestCode, (int) documentName, documentYear,
								messageType);
						if (requestCode.trim().length() > 0) {
							List<TempGeneralDeclaration> listGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil
									.findByRequestCode(requestCode);
							if (listGeneralDeclaration != null && listGeneralDeclaration.size() > 0) {
								tempGeneralDeclaration = listGeneralDeclaration.get(0);
							}
						}
					}
					if (tempGeneralDeclaration != null) {
						tempGeneralDeclaration.setRequestState(TrangThaiBanKhaiNhapCanh.SUA_DOI_BO_XUNG);
						TempGeneralDeclarationLocalServiceUtil.updateTempGeneralDeclaration(tempGeneralDeclaration);
					}

				}

				// Ban khai hang hoa nguy hiem
				if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM) {
					TempDangerousGoodsManifest tempDangerousGoodsNanifest = null;
					if (requestCode != null && requestCode.trim().length() > 0) {
						List<TempDangerousGoodsManifest> listTempDangerousGoodsNanifest = TempDangerousGoodsNanifestLocalServiceUtil
								.findByRequestCode(requestCode);
						if (listTempDangerousGoodsNanifest != null && listTempDangerousGoodsNanifest.size() > 0) {
							tempDangerousGoodsNanifest = listTempDangerousGoodsNanifest.get(0);
						}
					} else {
						requestCode = CheckBusinessUtil.getRequestCode(requestCode, (int) documentName, documentYear,
								messageType);
						if (requestCode.trim().length() > 0) {
							List<TempDangerousGoodsManifest> lstDangerousGoods = TempDangerousGoodsNanifestLocalServiceUtil
									.findByRequestCode(requestCode);
							if (lstDangerousGoods != null && lstDangerousGoods.size() > 0) {
								tempDangerousGoodsNanifest = lstDangerousGoods.get(0);
							}
						}
					}
					if (tempDangerousGoodsNanifest != null) {
						tempDangerousGoodsNanifest.setRequestState(TrangThaiBanKhaiNhapCanh.SUA_DOI_BO_XUNG);
						TempDangerousGoodsNanifestLocalServiceUtil
								.updateTempDangerousGoodsNanifest(tempDangerousGoodsNanifest);
					}
				}
			} catch (Exception e) {
				
				log.error(e.getMessage());
			}

			// Thay doi trang thai ban khai, trong mot action type tu choi
			if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(),
						tempDocument.getDocumentYear(), TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_SUA_DOI_BO_SUNG);
			}

			// log.info("===thuTucGuiBanTinYeuCauBoSungHoSo====Du lieu Gui HAi
			// Quan ========" + xmlData);
			try {
				if (xmlData != null && xmlData.length() > 0) {
					businessUtils.insertHistorySendMessage(xmlData);

					String xml = "";
					if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
							|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
						// log.info("----Call receiveFromInland--- ");
						// xml = imtService.receiveFromInland(xmlData);

					} else {
						// log.info("----Call receiveResultFromMT--- ");
						// xml = imtService.receiveResultFromMT(xmlData);
					}

					// log.info("===thuTucGuiBanTinYeuCauBoSungHoSo====Du lieu
					// NHAN HQMC day ========" + xml);
					businessUtils.insertHistoryReceiveMessageResponse(xml);
				}
			} catch (Exception e) {
				
				log.error(e.getMessage());
			}
		}
	}

	public boolean keHoachNhapCanhThayDoiTrangThaiVaTraMessageHaiQuan(int messageType, int actionType,
			TempDocument tempDocument, String userName, String requestCode, ActionRequest resourceRequest) {
		boolean result = true;
		try {

			log.info("==thayDoiTrangThaiVaTraMessageHaiQuanTruongHopNhapCanh==messageType==" + messageType
					+ "==actionType==" + actionType + "==DocumentStatusCode==" + tempDocument.getDocumentStatusCode());
			String xmlData = "";

//			IMTService imtService = CallWs2HaiQuan.getIMTSercice();
			BusinessUtils businessUtils = new BusinessUtils();

			if (messageType == MessageType.LENH_DIEU_DONG) {

				requestCode = tempDocument.getRequestCode();

			}
			if (requestCode != null) {
				InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);

				if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					String function = MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU;
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function,
							messageType, userName, interfaceRequest);

					xmlData = tuChoiHoSo(messageType, function, xmlData, businessUtils, header, tempDocument,
							resourceRequest);

				} else if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					String function = MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU;

					Header header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function,
							messageType, userName, interfaceRequest);

					xmlData = tiepNhanHoSo(messageType, function, xmlData, businessUtils, header, tempDocument,
							resourceRequest);
					if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
						tempDocument.setRequestState(TrangThaiBanKhaiNhapCanh.CHO_CAP_LENH_DIEU_DONG);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					}

				} else if (actionType == PageType.ACTION_TYPE_HUY) {

					xmlData = huyHoSo(messageType, xmlData, businessUtils, tempDocument, userName, interfaceRequest,
							resourceRequest);
					if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
						tempDocument.setRequestState(TrangThaiBanKhaiNhapCanh.DUNG_CAP_LENH_DIEU_DONG);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);

					}

				} else if (actionType == PageType.ACTION_TYPE_HOAN_THANH_THU_TUC) {
					String function = MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC;

					if (messageType == MessageType.XAC_NHAN_HOAN_THANH_THU_TUC) {
						messageType = MessageType.HO_SO;
						xacNhanHoanThanhThuTucNhapCanh(resourceRequest, tempDocument.getDocumentName(),
								tempDocument.getDocumentYear());
					}
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function,
							messageType, userName, interfaceRequest);

					xmlData = tiepNhanHoSo(messageType, function, xmlData, businessUtils, header, tempDocument,
							resourceRequest);

				}
			}

			// log.info("=======Du lieu Gui HAi Quan ========" + xmlData);
			if (xmlData != null && xmlData.length() > 0) {
				boolean insertHistorySendMessage = businessUtils.insertHistorySendMessage(xmlData);
				log.info("==thayDoiTrangThaiVaTraMessageHaiQuanTruongHopNhapCanh==insertHistorySendMessage=="
						+ insertHistorySendMessage);

				// String xml = imtService.receiveResultFromMT(xmlData);
				String xml = "";
				if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
						|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
					// log.info("----Call receiveFromInland--- ");
					// xml = imtService.receiveFromInland(xmlData);

				} else {
					// log.info("----Call receiveResultFromMT--- ");
					// xml = imtService.receiveResultFromMT(xmlData);
				}
				// log.info("=======Du lieu NHAN HQMC day ========" + xml);

				boolean insertHistoryReceiveMessageResponse = businessUtils.insertHistoryReceiveMessageResponse(xml);
				log.info("==thayDoiTrangThaiVaTraMessageHaiQuanTruongHopNhapCanh==insertHistoryReceiveMessageResponse=="
						+ insertHistoryReceiveMessageResponse);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}

	private String createXmlSuaDoiBoXung(TempDocument tempDocument, String userName, ActionRequest resourceRequest,
			BusinessUtils businessUtils) throws SystemException {
		String xmlData = "";
		try {

			String function = MessageType.FUNCTION_THONG_BAO_BO_XUNG;
			String function25 = MessageType.FUNCTION_THONG_BAO_BO_SUNG;
			String lyDoSuaDoiBoSung = ParamUtil.getString(resourceRequest, PageType.LY_DO_SUADOI_BOSUNG);

			if (lyDoSuaDoiBoSung == null || lyDoSuaDoiBoSung.length() == 0) {
				lyDoSuaDoiBoSung = "System";
			}
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());

			// sua doi bo sung vao cang
			// if() {
			//
			// }

			if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
				// sua thanh 30-25 (dung handsome!!!!)
				Header header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO, function25,
						MessageType.THONG_BAO_TAU_DEN_CANG, userName, interfaceRequest);
				// Header header = BusinessUtils.createHeader(tempDocument,
				// MessageType.NHAP_CANH, function, MessageType.HO_SO, userName,
				// interfaceRequest);
				vn.gt.tichhop.message.Modify modify = new vn.gt.tichhop.message.Modify();
				modify.setDivision(businessUtils.getDivision(resourceRequest));
				modify.setOrganization(BusinessUtils.getOrganizationFromUser(resourceRequest));
				modify.setName(header.getFrom().getName());
				modify.setModifyDate(FormatData.parseDateToTring(new Date()));
				modify.setModifyDesc(lyDoSuaDoiBoSung);
				modify.setModifyCode("Bo Xung Ho So");
				// doan nay da su dung ham moi 30 -25 roi
				xmlData = businessUtils.sendMessageRejectInland(header, header.getReference().getMessageId(),
						lyDoSuaDoiBoSung, BusinessUtils.getOrganizationFromUser(resourceRequest),
						businessUtils.getDivision(resourceRequest), header.getFrom().getName(), new Date(),
						tempDocument.getRequestCode());

				// insert MessageSuaDoiBoXung: thong tin gui di vao db
				Modify modify2 = new Modify();
				modify2.setId(CounterLocalServiceUtil.increment(Modify.class.getName()));
				modify2.setDivision(modify.getDivision());
				modify2.setDocumentname(tempDocument.getDocumentName());
				modify2.setDocumentyear(tempDocument.getDocumentYear());
				modify2.setModifycode(modify.getModifyCode());
				modify2.setModifydesc(modify.getModifyDesc());
				modify2.setModifydate(new Date());

				ModifyLocalServiceUtil.addModify(modify2);

			} else if (tempDocument.getDocumentTypeCode().equals("NC")) {
				Header header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function,
						MessageType.HO_SO, userName, interfaceRequest);
				vn.gt.tichhop.message.Modify modify = new vn.gt.tichhop.message.Modify();
				modify.setDivision(businessUtils.getDivision(resourceRequest));
				modify.setOrganization(BusinessUtils.getOrganizationFromUser(resourceRequest));
				modify.setName(header.getFrom().getName());
				modify.setModifyDate(FormatData.parseDateToTring(new Date()));
				modify.setModifyDesc(lyDoSuaDoiBoSung);
				modify.setModifyCode("Bo Xung Ho So");

				xmlData = businessUtils.sendMessageModify(header, modify, tempDocument.getRequestCode());

				// insert MessageSuaDoiBoXung: thong tin gui di vao db
				Modify modify2 = new Modify();
				modify2.setId(CounterLocalServiceUtil.increment(Modify.class.getName()));
				modify2.setDivision(modify.getDivision());
				modify2.setDocumentname(tempDocument.getDocumentName());
				modify2.setDocumentyear(tempDocument.getDocumentYear());
				modify2.setModifycode(modify.getModifyCode());
				modify2.setModifydesc(modify.getModifyDesc());
				modify2.setModifydate(new Date());

				ModifyLocalServiceUtil.addModify(modify2);
			}

			// vn.gt.tichhop.message.Modify modify = new
			// vn.gt.tichhop.message.Modify();
			// modify.setDivision(businessUtils.getDivision(resourceRequest));
			// modify.setOrganization(BusinessUtils.getOrganizationFromUser(resourceRequest));
			// modify.setName(header.getFrom().getName());
			// modify.setModifyDate(FormatData.parseDateToTring(new Date()));
			// modify.setModifyDesc(lyDoSuaDoiBoSung);
			// modify.setModifyCode("Bo Xung Ho So");
			//
			// xmlData = businessUtils.sendMessageModify(header, modify);
			//
			// //insert MessageSuaDoiBoXung: thong tin gui di vao db
			// Modify modify2 = new Modify();
			// modify2.setId(CounterLocalServiceUtil.increment(Modify.class.getName()));
			// modify2.setDivision(modify.getDivision());
			// modify2.setDocumentname(tempDocument.getDocumentName());
			// modify2.setDocumentyear(tempDocument.getDocumentYear());
			// modify2.setModifycode(modify.getModifyCode());
			// modify2.setModifydesc(modify.getModifyDesc());
			// modify2.setModifydate(new Date());
			//
			// ModifyLocalServiceUtil.addModify(modify2);
			//
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return xmlData;
	}

	private String huyHoSo(int messageType, String xmlData, BusinessUtils businessUtils, TempDocument tempDocument,
			String userName, InterfaceRequest interfaceRequest, ActionRequest request) {

		log.info("==huyHoSo==MessageType==" + messageType);
		try {
			String function = MessageType.FUNCTION_KHAI_HUY_XAC_BAO;

			if (messageType == MessageType.HO_SO) {
				function = MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC;
			}

			if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
				Header header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function,
						MessageType.THONG_BAO_HUY_XAC_BAO, userName, interfaceRequest);

				String lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_TU_CHOI);
				if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
					lyDoTuChoi = "System";
				}
				xmlData = businessUtils.sendNoticeOfArrivalCancel(header, tempDocument,
						header.getReference().getMessageId(), lyDoTuChoi,
						BusinessUtils.getOrganizationFromUser(request), businessUtils.getDivision(request),
						header.getFrom().getName(), new Date());

			} else if (messageType == MessageType.LENH_DIEU_DONG) {

				String reason = ParamUtil.getString(request, PageType.HUY_HO_SO);
				if (reason == null || reason.length() == 0) {
					reason = "System";
				}
				Header header = null;
				if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
					log.info("VAO INLANNNNNNNNNNNNNNNNNNNNNNNNN");
					header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO,
							MessageType.FUNCTION_HUY_LENH_DIEU_DONG, MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG, userName,
							interfaceRequest);
				} else {
					header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH,
							MessageType.FUNCTION_HUY_LENH_DIEU_DONG, MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG, userName,
							interfaceRequest);
				}

				// truong hop gui di chua su ly
				xmlData = businessUtils.sendMessageHuyHoLenhDieuDong(header,
						BusinessUtils.getOrganizationFromUser(request), businessUtils.getDivision(request),
						header.getFrom().getName(), reason, new Date(), 1, tempDocument.getRequestCode());
			} else {
				String reason = ParamUtil.getString(request, PageType.HUY_HO_SO);
				if (reason == null || reason.length() == 0) {
					reason = "System";
				}

				Header header = null;
				if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
					header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO, function,
							MessageType.HO_SO, userName, interfaceRequest);
				} else {
					header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function,
							MessageType.HO_SO, userName, interfaceRequest);
				}

				xmlData = businessUtils.sendMessageHuyHoSo(header, BusinessUtils.getOrganizationFromUser(request),
						businessUtils.getDivision(request), header.getFrom().getName(), new Date(), reason,
						tempDocument.getRequestCode());
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return xmlData;
	}

	private String tuChoiHoSo(int messageType, String function, String xmlData, BusinessUtils businessUtils,
			Header header, TempDocument tempDocument, ActionRequest resourceRequest) {

		String lyDoTuChoi = ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI);
		if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
			lyDoTuChoi = "System";
		}

		if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
			xmlData = businessUtils.sendMessageRejectInland(header, header.getReference().getMessageId(), lyDoTuChoi,
					BusinessUtils.getOrganizationFromUser(resourceRequest), businessUtils.getDivision(resourceRequest),
					header.getFrom().getName(), new Date(), tempDocument.getRequestCode());
		} else {
			xmlData = businessUtils.sendMessageReject(header, header.getReference().getMessageId(), lyDoTuChoi,
					BusinessUtils.getOrganizationFromUser(resourceRequest), businessUtils.getDivision(resourceRequest),
					header.getFrom().getName(), new Date(), tempDocument.getRequestCode());
		}

		return xmlData;
	}

	private String tiepNhanHoSo(int messageType, String function, String xmlData, BusinessUtils businessUtils,
			Header header, TempDocument tempDocument, ActionRequest resourceRequest) {
		// function = function.trim();
		log.info("tiepNhanHoSo " + "  messageType:= " + messageType + "  function =  " + function);
		if (function.equals(MessageType.FUNCTION_KHAI_BAO) && messageType == MessageType.HO_SO) {
			// chap nhan ho so, chung tu
			// xmlData = businessUtils.sendMessageConformed(header, new Date());
			xmlData = businessUtils.sendMessageResult(header, "", tempDocument.getRequestCode());
			log.info("vao FUNCTION_KHAI_BAO && HO_SO");
			// Thong bao tau den cang
		} else if (function.equals(MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC)
				&& messageType == MessageType.HO_SO) {

			// chap nhan ho so, chung tu
			xmlData = businessUtils.sendMessageConformed(header, new Date(), tempDocument.getRequestCode());
			log.info("vao FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC");
			// Thong bao tau den cang
		} else if (function.equals(MessageType.FUNCTION_KHAI_BAO) && messageType == MessageType.LENH_DIEU_DONG) {
			// xac nhan hoan thanh thu tuc

			xmlData = businessUtils.sendShiftingOrder(header, tempDocument);
			log.info("vao FUNCTION_KHAI_BAO && LENH_DIEU_DONG");

		} else {
			// chap nhan ho so, chung tu
			// xmlData = businessUtils.sendMessageConformed(header, new Date());
			//
			// dung.le

			log.info("vao ELSE@#$R@#$");
			if (tempDocument.getDocumentTypeCode().equals("NC")) {
				xmlData = businessUtils.sendMessageAccept(header,
						BusinessUtils.getOrganizationFromUser(resourceRequest),
						businessUtils.getDivision(resourceRequest), header.getFrom().getName(), new Date(),
						tempDocument.getRequestCode());
			} else if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
				log.info("~~~~!!!VAO DAY~~~!!");
				xmlData = businessUtils.sendMessageAccept(header,
						BusinessUtils.getOrganizationFromUser(resourceRequest),
						businessUtils.getDivision(resourceRequest), header.getFrom().getName(), new Date(),
						tempDocument.getRequestCode());
				// xmlData = businessUtils.SentMessageAcceptDinhKem(header,null
				// ,BusinessUtils.getOrganizationFromUser(resourceRequest),
				// businessUtils.getDivision(resourceRequest),
				// header.getFrom().getName(), new Date());
			}

			// Thong bao tau den cang
		}
		return xmlData;
	}

	// dungle clone
	// private String tiepNhanHoSoThem(int messageType, String function, String
	// xmlData, BusinessUtils businessUtils, Header header,
	// TempDocument tempDocument, ActionRequest resourceRequest) {
	// // function = function.trim();
	// log.info("tiepNhanHoSo " + " messageType:= " + messageType + " function =
	// " + function);
	// if (function.equals(MessageType.FUNCTION_KHAI_BAO) && messageType ==
	// MessageType.HO_SO) {
	// // chap nhan ho so, chung tu
	// // xmlData = businessUtils.sendMessageConformed(header, new Date());
	// xmlData = businessUtils.sendMessageResult(header, "");
	// // Thong bao tau den cang
	// } else if
	// (function.equals(MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC) &&
	// messageType == MessageType.HO_SO) {
	//
	// // chap nhan ho so, chung tu
	// xmlData = businessUtils.sendMessageConformed(header, new Date());
	// // Thong bao tau den cang
	// } else if (function.equals(MessageType.FUNCTION_KHAI_BAO) && messageType
	// == MessageType.LENH_DIEU_DONG) {
	// // xac nhan hoan thanh thu tuc
	//
	// xmlData = businessUtils.sendShiftingOrder(header, tempDocument);
	//
	// } else {
	// // chap nhan ho so, chung tu
	// // xmlData = businessUtils.sendMessageConformed(header, new Date());
	// //
	// //dung.le
	// if(tempDocument.getDocumentTypeCode().equals("NC"))
	// {
	// xmlData = businessUtils.sendMessageAccept(header,
	// BusinessUtils.getOrganizationFromUser(resourceRequest),
	// businessUtils.getDivision(resourceRequest), header.getFrom().getName(),
	// new Date());
	// } else if(tempDocument.getDocumentTypeCode().equals("4")) {
	// log.info("~~~~!!!VAO DAY~~~!!");
	// // xmlData = businessUtils.sendMessageAccept(header,
	// BusinessUtils.getOrganizationFromUser(resourceRequest),
	// // businessUtils.getDivision(resourceRequest),
	// header.getFrom().getName(), new Date());
	// xmlData = businessUtils.SentMessageAcceptDinhKem(header,null
	// ,BusinessUtils.getOrganizationFromUser(resourceRequest),
	// businessUtils.getDivision(resourceRequest), header.getFrom().getName(),
	// new Date());
	// }
	//
	// // Thong bao tau den cang
	// }
	// return xmlData;
	// }

	private void sendChapNhan(int messageType, String function, TempDocument tempDocument, String userName,
			String requestCode, ActionRequest request) {
		try {

			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);

			if (interfaceRequest != null) {
				log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				log.info("interfaceRequest.getRemarks()  " + interfaceRequest.getRemarks());
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}

			if (tempDocument.getDocumentTypeCode().equals("NC")) {
				Header header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function, messageType,
						userName, interfaceRequest);

				BusinessUtils businessUtils = new BusinessUtils();
//				IMTService imtService = CallWs2HaiQuan.getIMTSercice();
				String xmlData = businessUtils.sendMessageAccept(header, BusinessUtils.getOrganizationFromUser(request),
						businessUtils.getDivision(request), header.getFrom().getName(), new Date(),
						tempDocument.getRequestCode());

				if (xmlData != null && xmlData.length() > 0) {
					businessUtils.insertHistorySendMessage(xmlData);
					// String xml = imtService.receiveResultFromMT(xmlData);
					String xml = "";
					if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
							|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
						// log.info("----Call receiveFromInland--- ");
						// xml = imtService.receiveFromInland(xmlData);

					} else {
						// log.info("----Call receiveResultFromMT--- ");
						// xml = imtService.receiveResultFromMT(xmlData);
						// log.info("----XML!!!!!: --- " + xml);
					}
					businessUtils.insertHistoryReceiveMessageResponse(xml);
				}
			}
			// dung.le
			else if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
				Header header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO, function,
						MessageType.THONG_BAO_TAU_DEN_CANG, userName, interfaceRequest);
				log.info("VAO====tempDocument.getDocumentTypeCode()========" + tempDocument.getDocumentTypeCode());
				BusinessUtils businessUtils = new BusinessUtils();
//				IMTService imtService = CallWs2HaiQuan.getIMTSercice();
				String xmlData = businessUtils.sendMessageAccept(header, BusinessUtils.getOrganizationFromUser(request),
						businessUtils.getDivision(request), header.getFrom().getName(), new Date(),
						tempDocument.getRequestCode());

				// String xmlData1 =
				// businessUtils.SentMessageAcceptDinhKem(header, null ,
				// BusinessUtils.getOrganizationFromUser(request),
				// businessUtils.getDivision(request),
				// header.getFrom().getName(), new Date());

				if (xmlData != null
						&& xmlData.length() > 0 /*
												 * && xmlData1 != null &&
												 * xmlData1.length() > 0
												 */) {
					// businessUtils.insertHistorySendMessage(xmlData1);
					// Kiem tra lai nghiep vu
					log.info(
							"=====================vao xmlData != null && xmlData.length() > 0=================!!!!!!!");
					businessUtils.insertHistorySendMessage(xmlData);
					String xml = "";
					// log.info("----Call receiveFromInland--- ");
					// xml = imtService.receiveFromInland(xmlData);
					// log.info("----XML!!!!!: --- " + xml);

					businessUtils.insertHistoryReceiveMessageResponse(xml);
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private boolean sendMessageLenhDieuDong(int actionType, TempDocument tempDocument, String userName,
			String requestCodeShiftOrder, ActionRequest request, String versionShiftOrder, String canceNote) {
		boolean result = true;
		try {

			log.info("==sendMessageLenhDieuDong==messageType=========" + MessageType.LENH_DIEU_DONG + "==actionType=="
					+ actionType);
			log.info("==sendMessageLenhDieuDong==DocumentStatusCode==" + tempDocument.getDocumentStatusCode()
					+ "==versionShiftOrder==" + versionShiftOrder);

			String xmlData = "";

//			IMTService imtService = CallWs2HaiQuan.getIMTSercice();
			BusinessUtils businessUtils = new BusinessUtils();

			String requestCodeDocument = tempDocument.getRequestCode();

			if (requestCodeDocument != null) {
				InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
						.findByRequestCode(requestCodeDocument);

				if (actionType == PageType.ACTION_TYPE_DUYET) {
					String function = MessageType.FUNCTION_KHAI_BAO;
					Header header = null;
					if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
						header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO, function,
								MessageType.LENH_DIEU_DONG, userName, interfaceRequest);

						header.getReference().setVersion(versionShiftOrder);
						log.info("==versionShiftOrder==" + header.getReference().getVersion());

						xmlData = businessUtils.sendShiftingOrderInland(header, tempDocument);
					} else {
						header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function,
								MessageType.LENH_DIEU_DONG, userName, interfaceRequest);
						header.getReference().setVersion(versionShiftOrder);
						log.info("==versionShiftOrder==" + header.getReference().getVersion());

						xmlData = businessUtils.sendShiftingOrder(header, tempDocument);
					}

					// TODO tang version InterfaceRequest

				} else if (actionType == PageType.ACTION_TYPE_DONGDAU) {

					log.info("==chinh =  ACTION_TYPE_DONGDAU=");
					String function = MessageType.FUNCTION_KHAI_BAO;
					Header header = null;
					if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
						header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO, function,
								MessageType.LENH_DIEU_DONG, userName, interfaceRequest);

						header.getReference().setVersion(versionShiftOrder);
						log.info("==versionShiftOrder==" + header.getReference().getVersion());

						xmlData = businessUtils.sendShiftingOrderInland(header, tempDocument);
					} else {
						header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function,
								MessageType.LENH_DIEU_DONG, userName, interfaceRequest);
						// TODO tang version InterfaceRequest
						header.getReference().setVersion(versionShiftOrder);
						log.info("==versionShiftOrder==" + header.getReference().getVersion());

						xmlData = businessUtils.sendShiftingOrder(header, tempDocument);
					}

				} else if (actionType == PageType.ACTION_TYPE_HUY) {

					String reason = ParamUtil.getString(request, PageType.HUY_HO_SO);
					log.info("====sendMessageLenhDieuDong==reason===" + reason);
					if (reason == null || reason.length() == 0) {
						reason = "System";
					}
					if (canceNote != null && canceNote.trim().length() > 0) {
						reason = canceNote;
					}
					log.info("==sendMessageLenhDieuDong==reason===" + reason);
					Header header = null;
					if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
						header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO,
								MessageType.FUNCTION_HUY_LENH_DIEU_DONG, MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG,
								userName, interfaceRequest);
					} else {
						header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH,
								MessageType.FUNCTION_HUY_LENH_DIEU_DONG, MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG,
								userName, interfaceRequest);
					}

					// truong hop gui di chua su ly
					xmlData = businessUtils.sendMessageHuyHoLenhDieuDong(header,
							BusinessUtils.getOrganizationFromUser(request), businessUtils.getDivision(request),
							header.getFrom().getName(), reason, new Date(), 1, tempDocument.getRequestCode());

				}
			}

			// log.info("=======Du lieu Gui HAi Quan lenh dieu dong ========");
			// log.info(xmlData);

			if (xmlData != null && xmlData.length() > 0) {

				// businessUtils.insertHistorySendMessage(xmlData);
				businessUtils.insertHistorySendMessageThreeIssue(xmlData, userName, requestCodeShiftOrder);

				String xml = "";
				if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
						|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
					// log.info("----Call receiveFromInland--- ");
					// xml = imtService.receiveFromInland(xmlData);

				} else {
					// log.info("----Call receiveResultFromMT--- ");
					// xml = imtService.receiveResultFromMT(xmlData);
				}

				// log.info("=======Du lieu NHAN HQMC day ========" + xml);
				businessUtils.insertHistoryReceiveMessageResponse(xml);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean sendMessageTuChoiHoSoKeHoach(String userName, ActionRequest resourceRequest,
			TempDocument tempDocument, String requestCode, String lyDoTuChoi) {
		boolean resultSendMessageTuChoiHoSo = true;
		try {
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil
					.findInterfaceRequestByRequestCode(requestCode);

			String function = MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU;
			Header header;

			// if(tempDocument.getDocumentTypeCode().equals("4")) {
			// header = BusinessUtils.createHeader(tempDocument,
			// MessageType.TAU_VAO, function, MessageType.HO_SO, userName,
			// interfaceRequest);
			// } else {
			//
			// }

			if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
				header = BusinessUtils.createHeaderInland(tempDocument, MessageType.TAU_VAO, function,
						MessageType.THONG_BAO_TAU_DEN_CANG, userName, interfaceRequest);
			} else {
				header = BusinessUtils.createHeader(tempDocument, MessageType.NHAP_CANH, function, MessageType.HO_SO,
						userName, interfaceRequest);
			}

			if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
				lyDoTuChoi = "System";
			}

			// --------gui messsage-------------
			BusinessUtils businessUtils = new BusinessUtils();
			String xmlData = businessUtils.sendMessageRejectKH(header, header.getReference().getMessageId(), lyDoTuChoi,
					BusinessUtils.getOrganizationFromUser(resourceRequest), businessUtils.getDivision(resourceRequest),
					header.getFrom().getName(), new Date(), tempDocument.getRequestCode());

			if (BusinessUtils.getOrganizationFromUser(resourceRequest) != null) {
				log.info("========BusinessUtils.getOrganizationFromUser(resourceRequest)========="
						+ BusinessUtils.getOrganizationFromUser(resourceRequest));
			} else {
				log.info("========BusinessUtils.getOrganizationFromUser(resourceRequest)========= is NULLLLL");
			}

			// xmlData = businessUtils.createContentSendFromBGTVTToNSW(header,
			// xmlData);
			// IMTService imtService = CallWs2HaiQuan.getIMTSercice();

			if (xmlData != null && xmlData.length() > 0) {

				boolean resultMethodInsertHistorySendMessage = businessUtils.insertHistorySendMessage(xmlData);

				log.info("===sendMessageTuChoiHoSo====resultMethodInsertHistorySendMessage==="
						+ resultMethodInsertHistorySendMessage);
				log.info("===sendMessageTuChoiHoSo====Du lieu GUI  HQMC day TU CHOI HO SO====");
				log.info(xmlData);

				if (resultMethodInsertHistorySendMessage == true) {
					String xml = "";
					if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)) {
						// xml = imtService.receiveFromInland(xmlData);
						// log.info("===sendMessageTuChoiHoSo==== INLANDDDDDDD
						// ========" + xml);
					} else {
						// xml = imtService.receiveResultFromMT(xmlData);
						// log.info("===sendMessageTuChoiHoSo====Du lieu NHAN
						// HQMC day ========" + xml);
						log.info(xml);
					}

					// TODO code theo kieu message queue, nek ko co respone o
					// day
					// boolean resultMethodHistoryResponse =
					// businessUtils.insertHistoryReceiveMessageResponse(xml);
					boolean resultMethodHistoryResponse = true;

					resultSendMessageTuChoiHoSo = resultMethodHistoryResponse;
					log.info("===sendMessageTuChoiHoSo====resultMethodInsertHistoryReceiveMessageResponse==="
							+ resultMethodHistoryResponse);
				} else {
					resultSendMessageTuChoiHoSo = false;
				}
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
			resultSendMessageTuChoiHoSo = false;
		}
		return resultSendMessageTuChoiHoSo;
	}

	//
	private String yeuCauBoSungHoSo(int messageType, String function, String xmlData, BusinessUtils businessUtils,
			Header header, TempDocument tempDocument, ActionRequest request) {
		try {

			String lyYeuCauBoSung = ParamUtil.getString(request, PageType.LY_DO_YEU_CAU_BO_SUNG);
			log.info("===yeuCauBoSungHoSo====lyDoTuChoi========" + lyYeuCauBoSung);
			if (lyYeuCauBoSung == null || lyYeuCauBoSung.length() == 0) {
				lyYeuCauBoSung = "System";
			}

			xmlData = businessUtils.sendMessageReject(header, header.getReference().getMessageId(), lyYeuCauBoSung,
					BusinessUtils.getOrganizationFromUser(request), businessUtils.getDivision(request),
					header.getFrom().getName(), new Date(), tempDocument.getRequestCode());
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return xmlData;
	}

	private void xuLyLenhDieuDong(TempDocument document, int actionType, UploadPortletRequest request, ActionRequest actionRequest, ActionResponse response,
			String userName) throws SystemException {
		log.info("actionType*********************" + actionType);
		BusinessUtils businessUtils = new BusinessUtils();
		log.info("actionType*********************" + actionType);
		if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {

			String lanCapLDD = ParamUtil.getString(request, PageType.LAN_CAP_LENH_DIEU_DONG);

			log.info("==xuLyLenhDieuDong==ACTION_TYPE_TIEP_NHAN==" + actionType + "==RequestState=="
					+ document.getRequestState() + "==CAP_LenhDieuDong==" + lanCapLDD);

			if ((document.getRequestState() != TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG)
					| (document.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG
							&& lanCapLDD.equalsIgnoreCase(KeyParams.N_LAN))
					| (document.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_CAP_LENH_DIEU_DONG
							&& lanCapLDD.equalsIgnoreCase(KeyParams.MOT_LAN))) {

				log.info("=====ACTION_TYPE_TIEP_NHAN=====insert moi=====");

//				businessUtils.lenhDieuDongUpgrade(request, document.getDocumentName(), document.getDocumentYear(),
//						userName);

				String lyDoCapLai = ParamUtil.getString(request, "lyDoCapLaiShifOrder");
				String requestCodeShifOrderLast = ParamUtil.getString(request, PageType.REQUEST_CODE);

				log.info("==xuLyLenhDieuDong==lyDoCapLai==" + lyDoCapLai);
				log.info("==xuLyLenhDieuDong==REQUEST_CODE==" + requestCodeShifOrderLast);

				InterfaceRequest interfaceShifOrder = InterfaceRequestLocalServiceUtil
						.findByRequestCode(requestCodeShifOrderLast);

				if (interfaceShifOrder != null) {
					String sIssueDate = ParamUtil.getString(request, "issueDate");
					String remarkCapLai = BusinessUtils.getRemarkCapLai(userName, lyDoCapLai,
							FormatData.parseDateShort3StringToDate(sIssueDate));

					interfaceShifOrder.setRemarks(remarkCapLai);
					InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceShifOrder);
				}

			}

		} else if (actionType == PageType.ACTION_TYPE_HUY) {

			log.info("=====xuLyLenhDieuDong==actionType==ACTION_TYPE_HUY");

			String lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_TU_CHOI);

			document.setRequestState(TrangThaiBanKhaiNhapCanh.DA_HUY_LENH_DIEU_DONG);
			document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);

			TempDocumentLocalServiceUtil.updateTempDocument(document);

			List<IssueShiftingOrder> lstShiftOrder = IssueShiftingOrderLocalServiceUtil
					.findByDocumentYearAndDocumentYearOrderByColumn(document.getDocumentName(),
							document.getDocumentYear(), KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);

			for (IssueShiftingOrder item : lstShiftOrder) {

				item.setIsCancel(1);
				item.setCancelDate(new Date());
				item.setCancelName(userName);
				item.setCancelNote(lyDoTuChoi);

				item.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_HUY);
				IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(item);
			}

			insertOrUpdateResultNotification(document, userName, lyDoTuChoi, MessageType.HUY_LENH_DIEU_DONG);

			sendMessageLenhDieuDong(actionType, document, userName, UUID.randomUUID().toString(), actionRequest,
					lstShiftOrder.get(0).getVersionNo(), lyDoTuChoi);

		} else if (actionType == PageType.ACTION_TYPE_SUA) {

			String suaLenhDieuDong = ParamUtil.getString(request, PageType.LAN_SUA_LENH_DIEU_DONG);

			log.info("=====ACTION_TYPE_TIEP_NHAN=====" + actionType + "===requestState====="
					+ document.getRequestState() + "==SUA_LenhDieuDong==" + suaLenhDieuDong);

			if ((document.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG
					&& suaLenhDieuDong.compareTo(KeyParams.MOT_LAN) == 0)
					| (document.getRequestState() == TrangThaiBanKhai.DA_CAP_LENH_DIEU_DONG
							&& suaLenhDieuDong.compareTo(KeyParams.N_LAN) == 0)) {

				log.info("=====ACTION_TYPE_TIEP_NHAN=====update moi=====");
//				businessUtils.lenhDieuDongUpgrade(request, document.getDocumentName(), document.getDocumentYear(),
//						userName);
			}

		} else if (actionType == PageType.ACTION_TYPE_DUYET) {

			String isDuyetAndGui = ParamUtil.getString(request, PageType.DUYET_VA_GUI_LENH_DIEU_DONG);

			if (isDuyetAndGui.length() > 0) {

				String duyetNLan = ParamUtil.getString(request, KeyParams.N_LAN);
				String duyenMotLan = ParamUtil.getString(request, KeyParams.MOT_LAN);

				String requestCodeShiftOrder = ParamUtil.getString(request, PageType.REQUEST_CODE);

				log.info("==NHAP CANH===xuLyLenhDieuDong====Duyet_And_Gui===REQUEST_CODE==" + requestCodeShiftOrder);
				log.info("==NHAP CANH===xuLyLenhDieuDong====duyet_N_Lan===================" + duyetNLan);
				log.info("==NHAP CANH===xuLyLenhDieuDong====duyen_Mot_Lan=================" + duyenMotLan);

				IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil
						.getByRequestCode(requestCodeShiftOrder);

				// List<TempGeneralDeclaration> lstGeneralDeclaratiSon =
				// TempGeneralDeclarationLocalServiceUtil.findByDocumentNameAndDocumentYear(document.getDocumentName(),
				// document.getDocumentYear());
				/**
				 * Th1: BKC gá»­i trÆ°á»›c khi cáº¥p LDD - TGD cáº­p nháº­t theo
				 * thá»© tá»± Æ°u tiÃªn: 1. LDD; 2. XB; 3. TB; 4. BKAN
				 */

				// ----------duyenMotLan---------
				if (duyenMotLan.length() > 0) {
					document.setRequestState(TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG);
					document.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
					document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.THU_TUC_DA_TIEP_NHAN);

					// Issues 40: Thá»�i gian tÃ u Ä‘áº¿n cáº£ng sau khi cáº¥p
					// lá»‡nh Ä‘iá»�u Ä‘á»™ng chÆ°a cáº­p nháº­t theo thá»�i
					// gian lá»‡nh Ä‘iá»�u Ä‘á»™ng
					// if (shiftOrder != null &&
					// Validator.isNotNull(lstGeneralDeclaration) &&
					// lstGeneralDeclaration.size() > 0) {
					if (shiftOrder != null) {
						document.setShipDateFrom(shiftOrder.getShiftingDate());
					}
					TempDocumentLocalServiceUtil.updateTempDocument(document);
				}

				// ----------duyetNLan-----
				if (duyetNLan.length() > 0) {
					// if (shiftOrder != null &&
					// Validator.isNotNull(lstGeneralDeclaration) &&
					// lstGeneralDeclaration.size() > 0) {
					if (shiftOrder != null) {
						// Issues 40: Thá»�i gian tÃ u Ä‘áº¿n cáº£ng sau khi
						// cáº¥p lá»‡nh Ä‘iá»�u Ä‘á»™ng chÆ°a cáº­p nháº­t theo
						// thá»�i gian lá»‡nh Ä‘iá»�u Ä‘á»™ng
						document.setShipDateFrom(shiftOrder.getShiftingDate());
						TempDocumentLocalServiceUtil.updateTempDocument(document);
					}
				}

				/**
				 * Th2: BKC gá»­i sau LDD - TGD cáº­p nháº­t theo thá»© tá»± Æ°u
				 * tiÃªn: 1. BKC; 2. LDD; 3. XB; 4. TB; 5. BKAN.
				 */

				/**
				 * ChÃº Ã½: Viá»‡c cáº¥p láº¡i LDD cÅ©ng náº±m trong cÃ¡c
				 * trÆ°á»�ng há»£p trÃªn (cáº¥p láº¡i coi nhÆ° chÆ°a cáº¥p LDD).
				 */

				if (shiftOrder != null) {
					// TODO Nhap Canh truong hop duyet
					shiftOrder.setIsApproval(PageType.DUYET_PHE_CHUAN);
					shiftOrder.setApprovalDate(new Date());
					shiftOrder.setApprovalName(userName);

					shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.CHAP_NHAN_BAN_KHAI);

					IssueShiftingOrder update = IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);

					log.info("==shiftOrder != null===update==" + update.getRequestState() + "===versonNo=="
							+ update.getVersionNo());
					log.info("==isDuyetAndGui===shiftOrder===DocumentName==" + update.getDocumentName()
							+ "===versonNo==" + update.getVersionNo());
				}

				log.info("==shiftOrder==documentName==" + document.getDocumentName() + "==documentYear=="
						+ document.getDocumentYear());
				log.info("==shiftOrder==version=======" + shiftOrder.getVersionNo());

				// XU LY Gui lenh dieu dong
				if (document.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG
						&& Validator.isNotNull(shiftOrder.getRequestState())
						&& (shiftOrder.getRequestState() == TrangThaiBanKhaiNhapCanh.CHAP_NHAN_BAN_KHAI)) {

					log.info("==GUI_lenh dieu dong==request_Code_Shift_Order==" + requestCodeShiftOrder
							+ "==wVERSION_SIFHTING_ORDER==" + shiftOrder.getVersionNo());
					sendMessageLenhDieuDong(actionType, document, userName, requestCodeShiftOrder, actionRequest,
							shiftOrder.getVersionNo(), "");
				} else {
					log.info("==KHONG_gui_lenh_dieu_dong=");
				}
			}
		} else if (actionType == PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET) {

			String isDuyetAndGui = ParamUtil.getString(request, PageType.DUYET_VA_GUI_LENH_DIEU_DONG);

			if (isDuyetAndGui.length() > 0) {

				String duyetNLan = ParamUtil.getString(request, KeyParams.N_LAN);
				String duyenMotLan = ParamUtil.getString(request, KeyParams.MOT_LAN);

				String requestCodeShiftOrder = ParamUtil.getString(request, PageType.REQUEST_CODE);

				IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil
						.getByRequestCode(requestCodeShiftOrder);

				if ((shiftOrder != null) && (shiftOrder.getStampStatus() == 0)) {
					// TODO Nhap Canh truong hop cho duyet
					shiftOrder.setStampStatus(PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET);
					shiftOrder.setIsApproval(PageType.KHONG_PHE_CHUAN);
					IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
					log.info("==Chuyen_lanh_dao_duyet_ky_lenh_dieu_dong=");
				}
			}
		} else if (actionType == PageType.ACTION_TYPE_CHUYEN_TRA_HO_SO) {
			log.info("=====Tra ve buoc truoc==ACTION_TYPE_CHUYEN_TRA_HO_SO");

			String requestCodeShiftOrder = ParamUtil.getString(request, PageType.REQUEST_CODE);

			IssueShiftingOrder shiftOrderLanhDaoTraLai = IssueShiftingOrderLocalServiceUtil
					.getByRequestCode(requestCodeShiftOrder);

			if (shiftOrderLanhDaoTraLai != null) {
				// TODO Nhap Canh truong hop cho duyet
				String lyDoTraLai = ParamUtil.getString(request, PageType.LY_DO_TRA_LAI_HO_SO);
				shiftOrderLanhDaoTraLai.setStampStatus(PageType.KHONG_PHE_CHUAN);
				shiftOrderLanhDaoTraLai.setIsApproval(PageType.KHONG_PHE_CHUAN);
				shiftOrderLanhDaoTraLai.setIsCancel(PageType.KHONG_PHE_CHUAN);
				shiftOrderLanhDaoTraLai.setCancelNote(lyDoTraLai);
				IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrderLanhDaoTraLai);
				log.info("==Tra ve buoc truoc, sua noi dung lenh dieu dong==");

				BusinessUtils.updateLyDoResultNotification(userName, lyDoTraLai,
						MessageType.Y_CAU_TRA_LAI_HO_SO_LENH_DIEU_DONG, document.getMaritimeCode(),
						document.getDocumentName(), document.getDocumentYear());
			}

		} else if (actionType == PageType.ACTION_TYPE_KYSO) {
			String requestCodeShiftOrder = ParamUtil.getString(request, PageType.REQUEST_CODE);

			log.info("==NHAP CANH===ky so====Duyet_And_Gui===REQUEST_CODE==" + requestCodeShiftOrder);
			IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCodeShiftOrder);

			document.setRequestState(TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG);
			TempDocumentLocalServiceUtil.updateTempDocument(document);

			long addkyso = ParamUtil.getLong(request, "fileId");

			if (shiftOrder != null) {
				// TODO Nhap Canh truong hop duyet
				shiftOrder.setApprovalDate(new Date());
				shiftOrder.setApprovalName(userName);
				shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_MOI);
				shiftOrder.setStampStatus(1);

				String representative = shiftOrder.getRepresentative();
				String portofAuthority = shiftOrder.getPortofAuthority();
				String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
				shiftOrder.setSignTitle(signTitle);
				shiftOrder.setSignName(ParamUtil.getString(request, "signName"));
				shiftOrder.setSignPlace(ParamUtil.getString(request, "signPlace"));
				shiftOrder.setSignDate(ParamUtil.getDate(request, "signDate", FormatData.formatDateShort3));

				if (addkyso > 0) {
					shiftOrder.setAttachedFile(ParamUtil.getLong(request, "fileId"));
				}

				IssueShiftingOrder update = IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);

				log.info("==shiftOrder != null===update==" + update.getRequestState() + "===versonNo=="
						+ update.getVersionNo());
				log.info("==isDuyetAndGui===shiftOrder===DocumentName==" + update.getDocumentName() + "===versonNo=="
						+ update.getVersionNo());

				// Kich ban du phong, Lanh dao ky so, khong co buoc Van thu dong
				// dau.
				if (ConfigurationManager.getStrProp("vn.gt.yeu.cau.bo.qua.buoc.dong.dau", "").contains("1")) {
					xuLyLenhDieuDong(document, PageType.ACTION_TYPE_DONGDAU, request, actionRequest, response, userName);
				}
				log.info("==shiftOrder==version=======" + shiftOrder.getVersionNo());
			}

			log.info("==shiftOrder==documentName==" + document.getDocumentName() + "==documentYear=="
					+ document.getDocumentYear());
			

			// }
		} else if (actionType == PageType.ACTION_TYPE_DONGDAU) {

			String requestCodeShiftOrder = ParamUtil.getString(request, PageType.REQUEST_CODE);

			log.info("==NHAP CANH===ky so====Duyet_And_Gui===REQUEST_CODE==" + requestCodeShiftOrder);

			IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCodeShiftOrder);

			long adddongdau = ParamUtil.getLong(request, "fileId");

			if (shiftOrder != null) {

				shiftOrder.setIsApproval(PageType.DUYET_PHE_CHUAN);
				/*
				 * shiftOrder.setApprovalDate(new Date());
				 * shiftOrder.setApprovalName(userName);
				 */

				shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.CHAP_NHAN_BAN_KHAI);
				shiftOrder.setStampStatus(2);

				if (adddongdau > 0) {
					shiftOrder.setAttachedFile(ParamUtil.getLong(request, "fileId"));
				}

				IssueShiftingOrder update = IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);

				log.info("==shiftOrder != null===update==" + update.getRequestState() + "===versonNo=="
						+ update.getVersionNo());
				log.info("==isDuyetAndGui===shiftOrder===DocumentName==" + update.getDocumentName() + "===versonNo=="
						+ update.getVersionNo());
			}

			log.info("==shiftOrder==version=======" + shiftOrder.getVersionNo());
			String versionduyet = shiftOrder.getVersionNo();
			if (versionduyet.length() > 0) {
				if (FormatData.convertToInt(versionduyet) == 1) {
					document.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
					document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.THU_TUC_DA_TIEP_NHAN);
				} else if (FormatData.convertToInt(versionduyet) > 1) {
					// do not setDocumentStatusCode and setShipPosition
				}
			} else {
				document.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
				document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.THU_TUC_DA_TIEP_NHAN);
			}

			document.setRequestState(TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG);
			document.setShipDateFrom(shiftOrder.getShiftingDate());
			TempDocumentLocalServiceUtil.updateTempDocument(document);

			if (document.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG
					&& Validator.isNotNull(shiftOrder.getRequestState())
					&& (shiftOrder.getRequestState() == TrangThaiBanKhaiNhapCanh.CHAP_NHAN_BAN_KHAI)) {

				log.info("==GUI_lenh dieu dong==request_Code_Shift_Order==" + requestCodeShiftOrder
						+ "==VERSION_SIFHTING_ORDER==" + shiftOrder.getVersionNo());
				sendMessageLenhDieuDong(actionType, document, userName, requestCodeShiftOrder, actionRequest,
						shiftOrder.getVersionNo(), "");
			} else {
				log.info("==KHONG_gui_lenh_dieu_dong=");
			}
		}

	}

	private void insertOrUpdateResultNotification(TempDocument document, String userName, String lyDoTuChoi,
			int messageType) {
		log.info("==insertOrUpdateResultNotification==MessageType==" + messageType);
		try {
			// MessageType.HUY_LENH_DIEU_DONG
			ResultNotification reNotification = ResultNotificationLocalServiceUtil.findByBusinessTypeCode(messageType,
					document.getDocumentName(), document.getDocumentYear());

			if (reNotification == null) {
				// Them moi.
				reNotification = new ResultNotification();
				reNotification.setBusinessTypeCode(messageType);
				reNotification.setDivision("System");
				reNotification.setDocumentName(document.getDocumentName());
				reNotification.setDocumentYear(document.getDocumentYear());
				reNotification.setLatestDate(new Date());
				reNotification.setRequestCode(UUID.randomUUID().toString());

				if (document != null) {
					reNotification.setMaritimeCode(document.getMaritimeCode());
				}

				// resultNotification.setRemarks(userName);
				// setRole Thutuc - Kehoach
				if (messageType == MessageType.BO_SUNG_THU_TUC) {
					reNotification.setRole(4);
				} else if (messageType == MessageType.HUY_LENH_DIEU_DONG
						|| messageType == MessageType.BO_SUNG_KE_HOACH) {
					reNotification.setRole(2);
				} else {
					reNotification.setRole(2);
				}

				reNotification.setResponse(lyDoTuChoi);
				reNotification.setRequestState(1);
				reNotification.setResponseTime(new Date());
				reNotification.setOfficerName(userName);
				reNotification.setLatestDate(new Date());
				reNotification.setIsReply(1);

				ResultNotificationLocalServiceUtil.addResultNotification(reNotification);
			} else {
				// setRole Thutuc - Kehoach
				if (messageType == MessageType.BO_SUNG_THU_TUC) {
					reNotification.setRole(4);
				} else if (messageType == MessageType.HUY_LENH_DIEU_DONG
						|| messageType == MessageType.BO_SUNG_KE_HOACH) {
					reNotification.setRole(2);
				} else {
					reNotification.setRole(2);
				}
				reNotification.setResponse(lyDoTuChoi);
				reNotification.setRequestState(1);
				if (document != null) {
					reNotification.setMaritimeCode(document.getMaritimeCode());
				}
				reNotification.setResponseTime(new Date());
				reNotification.setOfficerName(userName);
				reNotification.setLatestDate(new Date());
				reNotification.setIsReply(1);

				ResultNotificationLocalServiceUtil.updateResultNotification(reNotification);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
	}

	private void suaDoiHoSoKeHoach(long documentName, int documentYear, String userName, ActionRequest resourceRequest,
			TempDocument tempDocument) throws SystemException, RemoteException {
		try {
			if (tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_TIEP_NHAN
					|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_TIEP_NHAN
					|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_CAP_LENH_DIEU_DONG
					|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {

				tempDocument.setRequestState(TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG);
				TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);

				String lyDoSuaDoiBoSung = ParamUtil.getString(resourceRequest, PageType.LY_DO_SUADOI_BOSUNG);
				if (lyDoSuaDoiBoSung == null || lyDoSuaDoiBoSung.length() == 0) {
					lyDoSuaDoiBoSung = "System";
				}

				BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear,
						MessageType.FUNCTION_THONG_BAO_BO_XUNG,
						BusinessUtils.getRemarkBoSung(userName, lyDoSuaDoiBoSung));

				BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear,
						MessageType.FUNCTION_THONG_BAO_BO_XUNG,
						BusinessUtils.getRemarkBoSung(userName, lyDoSuaDoiBoSung));

				// ResultNotification
				insertOrUpdateResultNotification(tempDocument, userName, lyDoSuaDoiBoSung,
						MessageType.BO_SUNG_KE_HOACH);
			}

			BusinessUtils businessUtils = new BusinessUtils();
			String xmlData = createXmlSuaDoiBoXung(tempDocument, userName, resourceRequest, businessUtils);
			// IMTService imtService = CallWs2HaiQuan.getIMTSercice();
			if (xmlData != null && xmlData.length() > 0) {
				businessUtils.insertHistorySendMessage(xmlData);

				// log.info("==suaDoiHoSo==xmlData==Message gui di==");
				// log.info(xmlData);

				String xml = "";
				if (tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG)
						|| tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)) {
					// log.info("----Call receiveFromInland--- ");
					// xml = imtService.receiveFromInland(xmlData);

				} else {
					// log.info("----Call receiveResultFromMT--- ");
					// xml = imtService.receiveResultFromMT(xmlData);
				}

				// log.info("==suaDoiHoSo==Du lieu NHAN HQMC day ========");
				// log.info(xml);
				businessUtils.insertHistoryReceiveMessageResponse(xml);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
	}
}
