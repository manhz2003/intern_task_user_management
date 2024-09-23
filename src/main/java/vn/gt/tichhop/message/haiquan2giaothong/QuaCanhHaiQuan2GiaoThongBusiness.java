package vn.gt.tichhop.message.haiquan2giaothong;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;

import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.ModifyLocalServiceUtil;
//import org.tempuri.IMTService;

import vn.gt.constant.Constants;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;

import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;

import com.fds.nsw.nghiepvu.model.TempCargoItems;
import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;

import vn.gt.dao.noticeandmessage.service.TempCargoDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
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

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.kernel.exception.SystemException;





import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.core.PortalUtil;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.liferay.core.LanguageUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.UploadPortletRequest;
@Slf4j
public class QuaCanhHaiQuan2GiaoThongBusiness {
	
	
	
	public boolean xuLyQuaCanhRoleKeHoach(long documentName, int type, int documentYear, int actionType, String userName, ActionRequest resourceRequest, ActionResponse httpReq) {
		try {
			// Date approvalTime = ParamUtil.getDate(resourceRequest, "approvalTime", FormatData.formatDateShort3);
			List<TempDocument> tempDocuments = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear(documentName, documentYear);
			TempDocument tempDoc = null;
			
			if (tempDocuments != null && tempDocuments.size() > 0) {
				tempDoc = tempDocuments.get(0);
			}
			
			String requestCode = ParamUtil.getString(resourceRequest, PageType.REQUEST_CODE);
			
			//Click vao ban? khai
			if (type != PageType.THANH_PHAN_HO_SO) {
				
				// Truong hop tiep nhan truong hop Qua Canh
				if (type != MessageType.LENH_DIEU_DONG) {
					if (requestCode == null || requestCode.length() == 0) {
						requestCode = CheckBusinessUtil.getRequestCode(requestCode, Integer.valueOf(documentName + ""), documentYear, type);
					}
					thayDoiTrangThaiVaTraMessageHaiQuanTruongHopQuaCanh(type, actionType, tempDoc, userName, requestCode, resourceRequest,
							httpReq);
					
					// Thay doi trang thai bang khai
					thayDoiTrangThaiBanKhaiQuaCanhRoleKeHoach(tempDoc, type, actionType, userName, resourceRequest, httpReq, requestCode);
					
				} else if (type == MessageType.LENH_DIEU_DONG) {
					// TODO - minhhandsome, XU ly lenh dieu dong qua canh
					log.info("====XU_LY_LENH_DIEU_DONG==QuaCanh==requestCode==" + requestCode);
					
					keHoachXuLyLenhDieuDong(tempDoc, actionType, resourceRequest, httpReq, userName, requestCode);
					// sendMessageLenhDieuDong(actionType, tempDocument, userName, requestCode, resourceRequest, httpReq);
				}
				
			} else {
			//Click vao thanh phan ho so
				requestCode = tempDoc.getRequestCode();
				// Thay doi trang thai toan bo bang khai
				if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					if (tempDoc.getRequestState() == TrangThaiBanKhaiQuaCanh.CHO_TIEP_NHAN ||
						tempDoc.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {
						
						tempDoc.setRequestState(TrangThaiBanKhaiQuaCanh.CHO_CAP_LENH_DIEU_DONG);
						
						thayDoiTrangThaiToanBoBanKhaiKeHoach(tempDoc, actionType, userName, resourceRequest);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDoc);
						
					}
					
				} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					if (tempDoc.getRequestState() == TrangThaiBanKhaiQuaCanh.CHO_TIEP_NHAN ||
						tempDoc.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_TIEP_NHAN ||
						tempDoc.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_CAP_LENH_DIEU_DONG ||
						tempDoc.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {
						
						tempDoc.setRequestState(TrangThaiBanKhaiQuaCanh.TU_CHOI_TIEP_NHAN);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDoc);
						
						String lyDoTuChoi = ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI);
						if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
							lyDoTuChoi = "System";
						}
						BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear,
								MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU, BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
						BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear,
								MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU, BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
						sendMessageTuChoiHoSo(userName, resourceRequest, tempDoc, requestCode, lyDoTuChoi);
						
					}
					
				} else if (actionType == PageType.ACTION_TYPE_SUA_DOI) {
					//Thong bao huong dan DN bo sung
//					suaDoiHoSoKeHoach(documentName, documentYear, userName, resourceRequest, tempDoc);
				}
				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	
	
	public boolean xuLyQuaCanhRoleThuTuc(String userLogin, long documentName, int messageType, int documentYear, int actionType, String userName,
			ActionRequest request, ActionResponse httpReq) {
		try {
			log.info("==xuLyQuaCanhRoleThuTuc==messageType==" + messageType + "==actionType==" + actionType);
			List<TempDocument> tempDocuments = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear(documentName, documentYear);
			TempDocument tempDocument = null;
			
			if (tempDocuments != null && tempDocuments.size() > 0) {
				tempDocument = tempDocuments.get(0);
			} else {
				return false;
			}
			
			String requestCode = ParamUtil.getString(request, PageType.REQUEST_CODE);
			
			log.info("==xuLyQuaCanhRoleThuTuc==actionType==" + actionType + "==tempDocument.getRequestState()==" + tempDocument.getRequestState());
			// Truong hop tiep nhan truong hop Nhap Canh, CLICK vao tung ban khai
			if (messageType != PageType.THANH_PHAN_HO_SO) {
				
				if (actionType == PageType.ACTION_TYPE_SUA_DOI) {
					
					log.info("==xuLyQuaCanhRoleThuTuc==truong hop can bo hang hai gui thong diep sang bo giao thong, thong bao thieu ho so");
					log.info("==xuLyQuaCanhRoleThuTuc==ActionType==" + actionType + "==PageType==" + messageType);
					
					BusinessUtils businessUtils = new BusinessUtils();
					String lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_SUADOI_BOSUNG);
					insertOrUpdateResultNotification(tempDocument, userName, lyDoTuChoi, MessageType.BO_SUNG_THU_TUC);
					BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear,
							MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH, BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
					BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear,
							MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH, BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));

					String xmlData = createXmlSuaDoiBoXung(tempDocument, userName, request, businessUtils);
//					IMTService imtService = CallWs2HaiQuan.getIMTSercice();
					
					if (xmlData != null && xmlData.length() > 0) {
						businessUtils.insertHistorySendMessage(xmlData);
						log.info("==xuLyQuaCanhRoleThuTuc==DATA GUI DI==xmlData==");
						log.info(xmlData);
						
						String  xml = "";
						
						if( tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG) || tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)){
							log.info(" ----CAll receiveFromInland------- ");
						//	xml = imtService.receiveFromInland(xmlData);
							
						}else{
							log.info(" ----CAll receiveResultFromMT------- ");
						//	xml = imtService.receiveResultFromMT(xmlData);
						}
						log.info("==xuLyQuaCanhRoleThuTuc==Du lieu NHAN  HQMC day==xml==");
						log.info(xml);
						
						businessUtils.insertHistoryReceiveMessageResponse(xml);
					}
				} else if (actionType == PageType.ACTION_TYPE_CHUYEN_TRA_HO_SO) {
					if (messageType == MessageType.LENH_DIEU_DONG) {
						if (requestCode == null || requestCode.length() == 0) {
							requestCode = CheckBusinessUtil.getRequestCode(requestCode, Integer.valueOf(documentName + ""), documentYear, messageType);
						}
						
						IssueShiftingOrder shiftOrderLanhDaoTraLai = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCode);
						
						if (shiftOrderLanhDaoTraLai != null) {
							// TODO Nhap Canh truong hop cho duyet
							String lyDoTraLai = ParamUtil.getString(request, PageType.LY_DO_TRA_LAI_HO_SO);
							shiftOrderLanhDaoTraLai.setStampStatus(PageType.KHONG_PHE_CHUAN);
							shiftOrderLanhDaoTraLai.setIsApproval(PageType.KHONG_PHE_CHUAN);
							shiftOrderLanhDaoTraLai.setIsCancel(PageType.KHONG_PHE_CHUAN);
							shiftOrderLanhDaoTraLai.setCancelNote(lyDoTraLai);
							IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrderLanhDaoTraLai);
							log.info("==Tra ve buoc truoc, sua noi dung lenh dieu dong==");		
							
							
							BusinessUtils.updateLyDoResultNotification(userLogin, lyDoTraLai, MessageType.Y_CAU_TRA_LAI_HO_SO_LENH_DIEU_DONG,
									tempDocument.getMaritimeCode(), documentName, documentYear);
						}
						
					} else if (messageType == MessageType.GIAY_PHEP_QUA_CANH) {
						// function = MessageType.FUNCTION_KHAI_BAO;
						thuTucBusinnessGiayPhepQuaCanh(userLogin, actionType, userName, request, httpReq, tempDocument, requestCode);
					}
										
					
				} else {
					
					log.info("==xuLyQuaCanhRoleThuTuc==truong hop day du ho so==" + "PageType==" + messageType);
					
					if (requestCode == null || requestCode.length() == 0) {
						requestCode = CheckBusinessUtil.getRequestCode(requestCode, Integer.valueOf(documentName + ""), documentYear, messageType);
					}
					if (messageType != MessageType.GIAY_PHEP_QUA_CANH) {
						// Thay doi trang thai bang khai
						thayDoiTrangThaiBanKhaiQuaCanhRoleThuTuc(tempDocument, messageType, actionType, userName, requestCode, request);
						traMessageHaiQuanTruongHopQuaCanhThuTuc(messageType, actionType, tempDocument, userName, requestCode, request, httpReq);
					} else if (messageType == MessageType.GIAY_PHEP_QUA_CANH) {
						// function = MessageType.FUNCTION_KHAI_BAO;
						thuTucBusinnessGiayPhepQuaCanh(userLogin, actionType, userName, request, httpReq, tempDocument, requestCode);
					}
				}
			} else {
				//CLICK VAO THANH THAN SO HO
				thanhPhanHoSoThuTuc(userLogin, documentName, documentYear, actionType, userName, request, httpReq, tempDocument);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean thayDoiTrangThaiToanBoBanKhaiKeHoach(TempDocument tempDoc, int actionType, String userName, ActionRequest resourceRequest) {
		boolean result = true;
		try {
			
			List<ResultDeclaration> resultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
					MessageType.BAN_KHAI_AN_NINH_TAU_BIEN, tempDoc.getDocumentName(), tempDoc.getDocumentYear());
			if (resultDeclarations != null && resultDeclarations.size() > 0) {
				ResultDeclaration resultDeclaration = resultDeclarations.get(0);
				List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil.findByRequestCode(resultDeclaration.getRequestCode());
				
				if (results != null && results.size() > 0) {
					TempShipSecurityMessage tempShipSecurityMessage = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN
							&& tempShipSecurityMessage.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN
							&& tempShipSecurityMessage.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI) {
						
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						BusinessUtils.updateResultDeclaration(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN, tempDoc.getDocumentName(),
								tempDoc.getDocumentYear(), TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						
						TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(tempShipSecurityMessage);
						sendMessageChapNhan(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN, MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU, tempDoc, userName,
								resultDeclaration.getRequestCode(), resourceRequest);
					}
				}
			}
			
			resultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
					MessageType.THONG_BAO_TAU_QUA_CANH, tempDoc.getDocumentName(), tempDoc.getDocumentYear());
			if (resultDeclarations != null && resultDeclarations.size() > 0) {
				ResultDeclaration resultDeclaration = resultDeclarations.get(0);
				TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
						.findTempNoTiceShipMessageByRequestCode(resultDeclaration.getRequestCode());
				if (tempNoticeShipMessage != null && tempNoticeShipMessage.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN
						&& tempNoticeShipMessage.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI) {
					
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						BusinessUtils.updateResultDeclaration(MessageType.THONG_BAO_TAU_QUA_CANH, tempDoc.getDocumentName(),
								tempDoc.getDocumentYear(), TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						
						TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
						sendMessageChapNhan(MessageType.THONG_BAO_TAU_QUA_CANH, MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU, tempDoc, userName,
								resultDeclaration.getRequestCode(), resourceRequest);
					}
				}
			}
			
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				resultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
						MessageType.XAC_BAO_TAU_QUA_CANH, tempDoc.getDocumentName(), tempDoc.getDocumentYear());
				
				if (resultDeclarations != null && resultDeclarations.size() > 0) {
					ResultDeclaration resultDeclaration = resultDeclarations.get(0);
					TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
							.findTempNoTiceShipMessageByRequestCode(resultDeclaration.getRequestCode());
					
					if (tempNoticeShipMessage != null &&
						tempNoticeShipMessage.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN &&
						tempNoticeShipMessage.getRequestState() != TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI) {
						
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						
						BusinessUtils.updateResultDeclaration(MessageType.XAC_BAO_TAU_QUA_CANH, tempDoc.getDocumentName(),
								tempDoc.getDocumentYear(), TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						
						TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
						sendMessageChapNhan(MessageType.XAC_BAO_TAU_QUA_CANH, MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU, tempDoc, userName,
								resultDeclaration.getRequestCode(), resourceRequest);
					}
					
				}
			}
			// thayDoiTrangThaiToanBoBanKhaiThuTuc(actionType, requestCode);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getMessage());
		}
		
		return result;
	}
	
	public boolean thayDoiTrangThaiBanKhaiQuaCanhRoleKeHoach(TempDocument tempDocument, int messageType, int actionType, String userName,
			ActionRequest resourceRequest, ActionResponse httpReq, String requestCode) {
		boolean result = true;
		try {
			String lyDoTuChoi = ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI);
			if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
				lyDoTuChoi = "System";
			}
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);
			if (messageType == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
				List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempShipSecurityMessage tempShipSecurityMessage = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
								TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
								TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
						}
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(tempShipSecurityMessage);
				}
				
			} else if (messageType == MessageType.BAN_KHAI_HANG_HOA) {
				List<TempCargoDeclaration> results = TempCargoDeclarationLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempCargoDeclaration tempCargoDeclaration = results.get(0);
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
			} else if (messageType == MessageType.THONG_BAO_TAU_QUA_CANH) {
				TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
						.findTempNoTiceShipMessageByRequestCode(requestCode);
				if (tempNoticeShipMessage != null) {
					
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
								TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
								TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
						}
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
					log.info(" Co du lieu empNoTiceShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil ");
				} else {
					log.info(" Ko du lieu empNoTiceShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil ");
				}
			} else if (messageType == MessageType.XAC_BAO_TAU_QUA_CANH) {
				TempNoticeShipMessage tempNoticeShipMessage = TempNoTiceShipMessageLocalServiceUtil
						.findTempNoTiceShipMessageByRequestCode(requestCode);
				if (tempNoticeShipMessage != null) {
					
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
						}
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempNoticeShipMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
						if (interfaceRequest != null) {
							interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
						}
					}
					TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempNoticeShipMessage);
				}
			}
			if (interfaceRequest != null) {
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getMessage());
		}
		
		return result;
	}
	
	
	
	public boolean thayDoiTrangThaiBanKhaiQuaCanhRoleThuTuc(TempDocument tempDocument, int messageType, int actionType, String userName,
			String requestCode, ActionRequest resourceRequest) {
		boolean result = true;
		try {
			if (messageType == MessageType.BAN_KHAI_CHUNG) {
				List<TempGeneralDeclaration> results = TempGeneralDeclarationLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempGeneralDeclaration tempGeneralDeclaration = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempGeneralDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempGeneralDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempGeneralDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempGeneralDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempGeneralDeclarationLocalServiceUtil.updateTempGeneralDeclaration(tempGeneralDeclaration);
				}
			} else if (messageType == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
				if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
					List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil.findByRequestCode(requestCode);
					if (results != null && results.size() > 0) {
						TempShipSecurityMessage tempShipSecurityMessage = results.get(0);
						
						tempShipSecurityMessage.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
						TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(tempShipSecurityMessage);
						
					}
				}
				
			} else if (messageType == MessageType.XAC_NHAN_HOAN_THANH_THU_TUC) {
				xacNhanHoanThanhThuTucQuaCanh(resourceRequest, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
				
				// tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				tempDocument.setDocumentStatusCode(TrangThaiBanKhaiQuaCanh.DE_NGHI_CAP_GIAY_PHEP);
				TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN) {
				List<TempCrewList> results = TempCrewListLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempCrewList tempCrewList = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempCrewList.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempCrewList.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempCrewList.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempCrewList.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempCrewListLocalServiceUtil.updateTempCrewList(tempCrewList);
				}
			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH) {
				List<TempPassengerList> results = TempPassengerListLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempPassengerList tempPassenger = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempPassenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempPassenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempPassenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempPassenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempPassengerListLocalServiceUtil.updateTempPassengerList(tempPassenger);
				}
			} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM) {
				List<TempDangerousGoodsManifest> results = TempDangerousGoodsNanifestLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempDangerousGoodsManifest tempDangerousGoodsNanifest = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempDangerousGoodsNanifest.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempDangerousGoodsNanifest.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempDangerousGoodsNanifest.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempDangerousGoodsNanifest.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempDangerousGoodsNanifestLocalServiceUtil.updateTempDangerousGoodsNanifest(tempDangerousGoodsNanifest);
				}
			} else if (messageType == MessageType.BAN_KHAI_DU_TRU_CUA_TAU) {
				List<TempShipStoresDeclaration> results = TempShipStoresDeclarationLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempShipStoresDeclaration tempShipStoresDeclaration = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempShipStoresDeclarationLocalServiceUtil.updateTempShipStoresDeclaration(tempShipStoresDeclaration);
				}
			} else if (messageType == MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN) {
				List<TempCrewEffectsDeclaration> results = TempCrewEffectsDeclarationLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempCrewEffectsDeclaration tempCrewEffectsDeclaration = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempCrewEffectsDeclarationLocalServiceUtil.updateTempCrewEffectsDeclaration(tempCrewEffectsDeclaration);
				}
			} else if (messageType == MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI) {
				List<TempDeclarationOfHealth> results = TempDeclarationOfHealthLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempDeclarationOfHealth tempDeclarationOfHealth = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempDeclarationOfHealthLocalServiceUtil.updateTempDeclarationOfHealth(tempDeclarationOfHealth);
				}
			} else if (messageType == MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT) {
				List<TempShipSecurityMessage> results = TempShipSecurityMessageLocalServiceUtil.findByRequestCode(requestCode);
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
			} else if (messageType == MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT) {
				List<TempPlantQuarantine> results = TempPlantQuarantineLocalServiceUtil.findByRequestCode(requestCode);
				if (results != null && results.size() > 0) {
					TempPlantQuarantine tempPlantQuarantine = results.get(0);
					if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
						tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
					} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
						tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
					} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
						tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
					} else if (actionType == PageType.ACTION_TYPE_HUY) {
						tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
					}
					TempPlantQuarantineLocalServiceUtil.updateTempPlantQuarantine(tempPlantQuarantine);
				}
			}
			if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				BusinessUtils.updateResultDeclaration(messageType, tempDocument.getDocumentName(), tempDocument.getDocumentYear(),
						TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return result;
	}
	
	public boolean thayDoiTrangThaiVaTraMessageHaiQuanTruongHopQuaCanh(int messageType, int actionType, TempDocument tempDocument, String userName,
			String requestCode, ActionRequest resourceRequest, ActionResponse httpReq) {
		boolean result = true;
		try {
			log.info("thayDoiTrangThaiVaTraMessageHaiQuanTruongHopQuaCanh  Giay phep qua canh " + messageType + "  actionType " + actionType
					+ "  requestCode " + requestCode);
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
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, messageType, userName, interfaceRequest);
					
					xmlData = tuChoiHoSo(messageType, function, xmlData, businessUtils, header, tempDocument, resourceRequest);
					
				} else if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					String function = MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU;
					
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, messageType, userName, interfaceRequest);
					
					xmlData = tiepNhanHoSo(messageType, function, xmlData, businessUtils, header, tempDocument, resourceRequest);
					if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
						tempDocument.setRequestState(TrangThaiBanKhaiQuaCanh.CHO_CAP_LENH_DIEU_DONG);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					}
					
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					
					xmlData = createXmlHuyHoSo(messageType, xmlData, businessUtils, tempDocument, userName, interfaceRequest, resourceRequest);
					if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
						tempDocument.setRequestState(TrangThaiBanKhaiQuaCanh.DUNG_CAP_LENH_DIEU_DONG);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					}
					
				} else if (actionType == PageType.ACTION_TYPE_HOAN_THANH_THU_TUC) {
					String function = MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC;
					if (messageType == MessageType.XAC_NHAN_HOAN_THANH_THU_TUC) {
						messageType = MessageType.HO_SO;
						xacNhanHoanThanhThuTucQuaCanh(resourceRequest, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
					}
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, messageType, userName, interfaceRequest);
					
					xmlData = tiepNhanHoSo(messageType, function, xmlData, businessUtils, header, tempDocument, resourceRequest);
					
				}
			}
			if (xmlData != null && xmlData.trim().length() > 0) {
				log.info("=======Du lieu Gui HAi Quan ========" + xmlData);
				businessUtils.insertHistorySendMessage(xmlData);
				String  xml = "";
				if(MessageType.LOAT_THU_TUC_VAO_CANG.equals(tempDocument.getDocumentTypeCode()) ||MessageType.LOAT_THU_TUC_ROI_CANG.equals(tempDocument.getDocumentTypeCode())){
					log.info("----Call receiveFromInland--- ");
				//	xml = imtService.receiveFromInland(xmlData);
					
				}else{
					log.info("----Call receiveResultFromMT--- ");
			//		xml = imtService.receiveResultFromMT(xmlData);
				}				
				log.info("=======Du lieu NHAN  HQMC day ========" + xml);
				
				businessUtils.insertHistoryReceiveMessageResponse(xml);
				
				// TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}
	
	public void xacNhanHoanThanhThuTucQuaCanhTruongHopHuy(ActionRequest resourceRequest, long documentName, int documentYear) {
		ResultCompletion resultCompetion = null;
		resultCompetion = ResultCompetionLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
		String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip");
		String nameOfMaster = ParamUtil.getString(resourceRequest, "nameOfMaster");
		String portOfArrivalCode = ParamUtil.getString(resourceRequest, "portOfArrivalCode");
		int requestState = ParamUtil.getInteger(resourceRequest, "requestState");
		String certificateNo = ParamUtil.getString(resourceRequest, "certificateNo");
		String maritimeReference = ParamUtil.getString(resourceRequest, "maritimeReference");
		String flagStateOfShip = ParamUtil.getString(resourceRequest, "flagStateOfShip");
		double grossTonnage = ParamUtil.getDouble(resourceRequest, "grossTonnage", 0L);
		log.info("===vao vao =grossTonnage=" + grossTonnage);
		Date approvalTime = ParamUtil.getDate(resourceRequest, "approvalTime", FormatData.formatDateShort3);
		String approvalName = ParamUtil.getString(resourceRequest, "approvalName");
		
		try {
			log.info("xacNhanHoanThanhThuTucQuaCanhTruongHopHuy " + documentName + "  documentYear  " + documentYear);
			if (resultCompetion == null) {
				
				log.info("========vao insert====xacNhanHoanThanhThuTucQuaCanhTruongHopHuy=====");
				resultCompetion = new ResultCompletion();
				resultCompetion.setMaritimeCode(maritimeCode);
				resultCompetion.setNameOfShip(nameOfShip);
				resultCompetion.setNameOfMaster(nameOfMaster);
				resultCompetion.setPortOfArrivalCode(portOfArrivalCode);
				resultCompetion.setRequestState(requestState);
				resultCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				resultCompetion.setFlagStateOfShip(flagStateOfShip);
				resultCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				resultCompetion.setApprovalTime(approvalTime);
				resultCompetion.setApprovalName(approvalName);
				
				resultCompetion.setRequestState(TrangThaiBanKhaiQuaCanh.HUY_THU_TUC_TAU_THUYEN_QUA_CANH);
				resultCompetion.setResponseStatusCVHH(MessageType.TU_CHOI);
				resultCompetion.setResponseTimeCVHH(approvalTime);
				resultCompetion.setResponseCVHH(BusinessUtils.getRemarkTuChoiTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), "Huy ho so",
						approvalTime));
				
				resultCompetion.setDocumentName(documentName);
				resultCompetion.setDocumentYear(documentYear);
				resultCompetion.setRequestCode(UUID.randomUUID().toString());
				
				ResultCompetionLocalServiceUtil.addResultCompetion(resultCompetion);
			} else {
				log.info("xacNhanHoanThanhThuTucQuaCanhTruongHopHuy vao update");
				resultCompetion.setRequestState(TrangThaiBanKhaiQuaCanh.HUY_THU_TUC_TAU_THUYEN_QUA_CANH);
				resultCompetion.setResponseStatusCVHH(MessageType.TU_CHOI);
				resultCompetion.setResponseTimeCVHH(approvalTime);
				resultCompetion.setResponseCVHH(BusinessUtils.getRemarkTuChoiTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), "Huy ho so",
						approvalTime));
				
				resultCompetion.setMaritimeCode(maritimeCode);
				resultCompetion.setNameOfShip(nameOfShip);
				resultCompetion.setNameOfMaster(nameOfMaster);
				resultCompetion.setPortOfArrivalCode(portOfArrivalCode);
				resultCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				resultCompetion.setFlagStateOfShip(flagStateOfShip);
				resultCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				resultCompetion.setApprovalTime(approvalTime);
				resultCompetion.setApprovalName(approvalName);
				
				ResultCompetionLocalServiceUtil.updateResultCompetion(resultCompetion);
				log.info("UPDATE XONG setRequestState" + resultCompetion.getRequestState());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	public void xacNhanHoanThanhThuTucQuaCanh(ActionRequest resourceRequest, long documentName, int documentYear) {
		try {
			log.info("===vao xacNhanHoanThanhThuTucQuaCanh vao =documentName=" + documentName + "----documentYear---" + documentYear);
			
			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip");
			String nameOfMaster = ParamUtil.getString(resourceRequest, "nameOfMaster");
			String portOfArrivalCode = ParamUtil.getString(resourceRequest, "portOfArrivalCode");
			String certificateNo = ParamUtil.getString(resourceRequest, "certificateNo");
			String maritimeReference = ParamUtil.getString(resourceRequest, "maritimeReference");
			String flagStateOfShip = ParamUtil.getString(resourceRequest, "flagStateOfShip");
			double grossTonnage = ParamUtil.getDouble(resourceRequest, "grossTonnage", 0L);
			log.info("===vao vao =grossTonnage=" + grossTonnage);
			Date approvalTime = ParamUtil.getDate(resourceRequest, "approvalTime", FormatData.formatDateShort3);
			String approvalName = ParamUtil.getString(resourceRequest, "approvalName");
			
			ResultCompletion resultCompetion = null;
			resultCompetion = ResultCompetionLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
			
			if (resultCompetion == null) {
				
				log.info("========vao insert====xacNhanHoanThanhThuTucQuaCanh=====");
				resultCompetion = new ResultCompletion();
				resultCompetion.setMaritimeCode(maritimeCode);
				resultCompetion.setNameOfShip(nameOfShip);
				resultCompetion.setNameOfMaster(nameOfMaster);
				resultCompetion.setPortOfArrivalCode(portOfArrivalCode);
				resultCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				resultCompetion.setFlagStateOfShip(flagStateOfShip);
				resultCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				resultCompetion.setApprovalTime(approvalTime);
				resultCompetion.setApprovalName(approvalName);
				
				resultCompetion.setRequestState(TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				resultCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				resultCompetion.setResponseTimeCVHH(approvalTime);
				resultCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), approvalTime));
				
				resultCompetion.setDocumentName(documentName);
				resultCompetion.setDocumentYear(documentYear);
				resultCompetion.setRequestCode(UUID.randomUUID().toString());
				
				ResultCompetionLocalServiceUtil.addResultCompetion(resultCompetion);
			} else {
				log.info("========vao update===xacNhanHoanThanhThuTucQuaCanh======");
				resultCompetion.setMaritimeCode(maritimeCode);
				resultCompetion.setNameOfShip(nameOfShip);
				resultCompetion.setNameOfMaster(nameOfMaster);
				resultCompetion.setPortOfArrivalCode(portOfArrivalCode);
				resultCompetion.setCertificateNo(certificateNo.trim() + "/" + maritimeReference.trim());
				resultCompetion.setFlagStateOfShip(flagStateOfShip);
				resultCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				resultCompetion.setApprovalTime(approvalTime);
				resultCompetion.setApprovalName(approvalName);
				
				resultCompetion.setRequestState(TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
				resultCompetion.setResponseStatusCVHH(MessageType.DA_PHE_DUYET);
				resultCompetion.setResponseTimeCVHH(approvalTime);
				resultCompetion.setResponseCVHH(BusinessUtils
						.getRemarkPheDuyetTB(PortalUtil.getUser(resourceRequest).getEmailAddress(), approvalTime));
				
				ResultCompetionLocalServiceUtil.updateResultCompetion(resultCompetion);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	
	private void thanhPhanHoSoThuTuc(String userLogin, long documentName, int documentYear, int actionType, String userName,
			ActionRequest request, ActionResponse httpReq, TempDocument tempDocument) throws SystemException {
		
		log.info("==thanhPhanHoSoThuTuc==documentYear==" + documentYear + "==documentName==" + documentName + "==actionType==" + actionType);
		
		String requestCode;
		requestCode = tempDocument.getRequestCode();
		
		if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
			log.info("==thanhPhanHoSoThuTuc==actionType==ACTION_TYPE_TIEP_NHAN");
			tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.CHO_PHE_DUYET_HOAN_THANH_THU_TUC);
			BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear, MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU, BusinessUtils.getRemarkPheDuyet(userName));
		
		} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
			log.info("==thanhPhanHoSoThuTuc==actionType==ACTION_TYPE_TU_CHOI");
			
			tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.TU_CHOI_TIEP_NHAN);
			String lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_TU_CHOI);
			if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
				lyDoTuChoi = "System";
			}
			
			//Truong hop gui yeu cau bo sung, hoac tham bao cham hoan thanh thu tuc
			if (tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.THUTUC_CHO_PHE_DUYET_HOAN_THANH_THU_TUC || tempDocument.getDocumentStatusCode() == TrangThaiBanKhai.THUTUC_YEU_CAU_SUA_DOI_BO_SUNG) {
				lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_TU_CHOI);
				insertOrUpdateResultNotification(tempDocument, userName, lyDoTuChoi, MessageType.BO_SUNG_THU_TUC);
			}
			
			BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear, 
					MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH, BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
			BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear,
					MessageType.FUNCTION_KET_QUA_PHE_DUYET_TU_CO_QUAN_BAN_NGANH, BusinessUtils.getRemarkTuChoi(userName, lyDoTuChoi));
			
		} else if (actionType == PageType.ACTION_TYPE_HOAN_THANH_THU_TUC) {
			log.info("==thanhPhanHoSoThuTuc==actionType==ACTION_TYPE_HOAN_THANH_THU_TUC");
			xacNhanHoanThanhThuTucQuaCanh(request, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
			tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
			
		} else if (actionType == PageType.ACTION_TYPE_HUY) {
			log.info("==thanhPhanHoSoThuTuc==actionType==ACTION_TYPE_HUY");
			String lyDoHuyQC99 = ParamUtil.getString(request, PageType.HUY_HO_SO);
			if (lyDoHuyQC99 == null || lyDoHuyQC99.length() == 0) {
				lyDoHuyQC99 = "lyDoHuyQC99";
			}
			log.info("==thanhPhanHoSoThuTuc==lyDoHuyQC99==" + lyDoHuyQC99);
			BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear, MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
					BusinessUtils.getRemarkHuy(userName, lyDoHuyQC99));
			BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear, MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC,
					BusinessUtils.getRemarkHuy(userName, lyDoHuyQC99));
			// update ResultNotification
			BusinessUtils.updateLyDoResultNotification(userLogin, lyDoHuyQC99, MessageType.HO_SO, tempDocument.getMaritimeCode(), documentName, documentYear);
			
			xacNhanHoanThanhThuTucQuaCanhTruongHopHuy(request, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
			tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
			
		}
		
		// Thay doi trang thai bang khai
		traMessageHaiQuanTruongHopQuaCanhThuTuc(MessageType.HO_SO, actionType, tempDocument, userName, requestCode, request, httpReq);
		TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
	}
	
	private void thuTucBusinnessGiayPhepQuaCanh(String userLogin, int actionType, String userName, ActionRequest request, ActionResponse response,
			TempDocument temp, String requestCodeGiayPhepQuaCanh) throws SystemException {
		
		log.info("=====thuTucXuLyGiayPhepQuaCanh===ACTION_TYPE==" + actionType);
		
		switch (actionType) {
			case PageType.ACTION_TYPE_TIEP_NHAN:
				
				log.info("=====xuLyGiayPhepQuaCanh==ACTION_TYPE_TIEP_NHAN");
				
				thuTucTaoGiayPhepQuaCanh(temp.getDocumentName(), MessageType.GIAY_PHEP_QUA_CANH, temp.getDocumentYear(), actionType, userName,
						requestCodeGiayPhepQuaCanh, request, response);
				
				InterfaceRequest interfaceOldLast = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCodeGiayPhepQuaCanh);
				if (Validator.isNotNull(interfaceOldLast)) {
					
					String lydoCapLai = ParamUtil.getString(request, "lyDoCapLaiPermissionForTransit");
					String sNgayCapLai = ParamUtil.getString(request, "dateOfSign");
					
					log.info("==thuTucBusinnessGiayPhepQuaCanh==LydoCapLai==" + lydoCapLai);
					
					String remarkCapLai = BusinessUtils.getRemarkCapLai(userName, lydoCapLai, FormatData.parseStringToDate(sNgayCapLai));
					interfaceOldLast.setRemarks(remarkCapLai);
					log.info("==thuTucBusinnessGiayPhepQuaCanh==remarkCapLai===" + remarkCapLai);
					
					InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceOldLast);
				}
				break;
			
			case PageType.ACTION_TYPE_HUY:
				String lyDoHuyQC = ParamUtil.getString(request, PageType.HUY_HO_SO);
				
				if (lyDoHuyQC == null || lyDoHuyQC.length() == 0) {
					lyDoHuyQC = "SystemAS";
				}
				log.info("==thuTucXuLyGiayPhepQuaCanh==ACTION_TYPE_HUY");
				log.info("==thuTucXuLyGiayPhepQuaCanh===lyDoTuChoi====" + lyDoHuyQC);
				
				List<IssuePermissionForTransit> lstPerForTransitHuy = IssuePermissionForTransitLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(temp.getDocumentName(), temp.getDocumentYear(), KeyParams.VERSION_NO,
								KeyParams.ORDER_BY_DESC);
				
				for (IssuePermissionForTransit obj : lstPerForTransitHuy) {
					obj.setRequestState(TrangThaiBanKhaiQuaCanh.KHAI_HUY);
					IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(obj);
				}
				
				temp.setDocumentStatusCode(TrangThaiBanKhai.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
				TempDocumentLocalServiceUtil.updateTempDocument(temp);
				
				// TODO sendMessageGiayPhepQuaCanh
				thuTucSendMessageGiayPhepQuaCanh(MessageType.HUY_GIAY_PHEP_QUA_CANH, "", actionType, temp, userName, requestCodeGiayPhepQuaCanh, request, response);
				
				// update Result notification
				BusinessUtils.updateLyDoResultNotification(userLogin, lyDoHuyQC, MessageType.HUY_GIAY_PHEP_QUA_CANH, temp.getMaritimeCode(),
						temp.getDocumentName(), temp.getDocumentYear());
				// update Issue Permisson for transit
				IssuePermissionForTransit issuePermissionForTransit = null;
				List<IssuePermissionForTransit> lstPerForTransit = IssuePermissionForTransitLocalServiceUtil
						.findIssuePermissionForTransitByDocumentYearAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
				
				if (lstPerForTransit != null && lstPerForTransit.size() > 0) {
					for (IssuePermissionForTransit issue : lstPerForTransit) {
						issue.setIsCancel(1);
						issue.setCancelName(userLogin);
						issue.setCancelNote(lyDoHuyQC);
						issue.setCancelDate(new Date());
						IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(issuePermissionForTransit);
					}
				}
				
				break;
			
			case PageType.ACTION_TYPE_DUYET:
				
				log.info("=====xuLyGiayPhepQuaCanh==ACTION_TYPE_DUYET");
				
				IssuePermissionForTransit perForTransitDuyet = IssuePermissionForTransitLocalServiceUtil.getByrequestCode(requestCodeGiayPhepQuaCanh);
				
				if (Validator.isNotNull(perForTransitDuyet)) {
					
					perForTransitDuyet.setRequestState(TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI);
					perForTransitDuyet.setIsApproval(PageType.DUYET_PHE_CHUAN);
					perForTransitDuyet.setApprovalDate(new Date());
					perForTransitDuyet.setApprovalName(userName);
					IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransitDuyet);
					
					temp.setDocumentStatusCode(TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
					TempDocumentLocalServiceUtil.updateTempDocument(temp);
					
					thuTucSendMessageGiayPhepQuaCanh(MessageType.GIAY_PHEP_QUA_CANH, perForTransitDuyet.getVersionNo(), actionType, temp, userName, requestCodeGiayPhepQuaCanh, request, response);
					log.info("GUI=====GIAY_PHEP_QUA_CANH=====requestCode====" + requestCodeGiayPhepQuaCanh);
				}
				break;
			case PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET:
				
				log.info("=====xuLyGiayPhepQuaCanh==ACTION_TYPE_DUYET");
				
				IssuePermissionForTransit TransitChoLanhDaoDuyet = IssuePermissionForTransitLocalServiceUtil.getByrequestCode(requestCodeGiayPhepQuaCanh);
				
				if ((TransitChoLanhDaoDuyet != null) && Validator.isNotNull(TransitChoLanhDaoDuyet.getRequestState())
						&& (TransitChoLanhDaoDuyet.getStampStatus() == 0)) {
					
					// TODO Vao truong hop cho lanh dao duyet
					TransitChoLanhDaoDuyet.setStampStatus(PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET);
					TransitChoLanhDaoDuyet.setIsApproval(PageType.KHONG_PHE_CHUAN);
					
					IssuePermissionForTransitLocalServiceUtil
							.updateIssuePermissionForTransit(TransitChoLanhDaoDuyet);
					log.info("==Chuyen_lanh_dao_duyet_ky_giay_phep=");
					
				}
				break;
			case PageType.ACTION_TYPE_CHUYEN_TRA_HO_SO:
				
				log.info("=====Tra ve buoc truoc==ACTION_TYPE_CHUYEN_TRA_HO_SO");
				
				IssuePermissionForTransit transitLanhDaoTraLai = IssuePermissionForTransitLocalServiceUtil.getByrequestCode(requestCodeGiayPhepQuaCanh);
				
				if ((transitLanhDaoTraLai != null) && Validator.isNotNull(transitLanhDaoTraLai.getRequestState())) {
					
					// TODO Vao truong hop lanh dao tra lai
					String lyDoTraLai = ParamUtil.getString(request, PageType.LY_DO_TRA_LAI_HO_SO);
					transitLanhDaoTraLai.setStampStatus(PageType.KHONG_PHE_CHUAN);
					transitLanhDaoTraLai.setIsApproval(PageType.KHONG_PHE_CHUAN);
					transitLanhDaoTraLai.setIsCancel(PageType.KHONG_PHE_CHUAN);
					transitLanhDaoTraLai.setCancelNote(lyDoTraLai);
					IssuePermissionForTransitLocalServiceUtil
							.updateIssuePermissionForTransit(transitLanhDaoTraLai);
					log.info("==Tra ve buoc truoc, sua noi dung giay phep==");					
					
					
					BusinessUtils.updateLyDoResultNotification(userLogin, lyDoTraLai, MessageType.Y_CAU_TRA_LAI_HO_SO_GP_QUA_CANH,
							temp.getMaritimeCode(), temp.getDocumentName(), temp.getDocumentYear());
				}
				break;	
				
			case PageType.ACTION_TYPE_KYSODUYET:
				
				log.info("=====xuLyGiayPhepQuaCanh==ACTION_TYPE_KYSODUYET");
				
				IssuePermissionForTransit perForTransitDuyetkyso = IssuePermissionForTransitLocalServiceUtil.getByrequestCode(requestCodeGiayPhepQuaCanh);
				long addkyso = ParamUtil.getLong(request, "fileId");
				
				if (Validator.isNotNull(perForTransitDuyetkyso)) {
					
					perForTransitDuyetkyso.setRequestState(TrangThaiBanKhaiQuaCanh.KHAI_MOI);
				//	perForTransitDuyetkyso.setIsApproval(PageType.DUYET_PHE_CHUAN);
					perForTransitDuyetkyso.setApprovalDate(new Date());
					perForTransitDuyetkyso.setApprovalName(userName);
					
					perForTransitDuyetkyso.setStampStatus(1);
					String representative = perForTransitDuyetkyso.getRepresentative();
					String portofAuthority = perForTransitDuyetkyso.getPortofAuthority();
					String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
					perForTransitDuyetkyso.setSignTitle(signTitle);
					perForTransitDuyetkyso.setSignName(ParamUtil.getString(request, "signName"));
					perForTransitDuyetkyso.setSignPlace(ParamUtil.getString(request, "signPlace"));
					perForTransitDuyetkyso.setSignDate(ParamUtil.getDate(request, "signDate", FormatData.formatDateShort3));
					
					if (addkyso > 0){
						perForTransitDuyetkyso.setAttachedFile(ParamUtil.getLong(request, "fileId"));
					}
					
					
					IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransitDuyetkyso);
					
					temp.setDocumentStatusCode(TrangThaiBanKhaiQuaCanh.DE_NGHI_CAP_GIAY_PHEP);
					TempDocumentLocalServiceUtil.updateTempDocument(temp);
					
					// Kich ban du phong, Lanh dao ky so, khong co buoc Van thu dong dau.
					if (ConfigurationManager.getStrProp("vn.gt.yeu.cau.bo.qua.buoc.dong.dau", "").contains("1")) {
						thuTucBusinnessGiayPhepQuaCanh(userLogin, PageType.ACTION_TYPE_DONGDAUDUYET, userName, request, response, temp, requestCodeGiayPhepQuaCanh);
					}

				}
				break;
				
			case PageType.ACTION_TYPE_DONGDAUDUYET:
				
				log.info("=====xuLyGiayPhepQuaCanh==ACTION_TYPE_DONGDAUDUYET");
				
				IssuePermissionForTransit perForTransitDuyetdongdau = IssuePermissionForTransitLocalServiceUtil.getByrequestCode(requestCodeGiayPhepQuaCanh);
				
				long adddongdau = ParamUtil.getLong(request, "fileId");
				
				if (Validator.isNotNull(perForTransitDuyetdongdau)) {
					
					perForTransitDuyetdongdau.setRequestState(TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI);
					perForTransitDuyetdongdau.setIsApproval(PageType.DUYET_PHE_CHUAN);
					//perForTransitDuyetdongdau.setApprovalDate(new Date());
					//perForTransitDuyetdongdau.setApprovalName(userName);
					
					if (adddongdau > 0 ){
						perForTransitDuyetdongdau.setAttachedFile(ParamUtil.getLong(request, "fileId"));
					}
					perForTransitDuyetdongdau.setStampStatus(2);
					
					
					IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransitDuyetdongdau);
					
					temp.setDocumentStatusCode(TrangThaiBanKhaiQuaCanh.PHE_DUYET_HOAN_THANH_THU_TUC);
					TempDocumentLocalServiceUtil.updateTempDocument(temp);
		
					thuTucSendMessageGiayPhepQuaCanh(MessageType.GIAY_PHEP_QUA_CANH, perForTransitDuyetdongdau.getVersionNo(), actionType, temp, userName,
							requestCodeGiayPhepQuaCanh, request, response);
					log.info("GUI=====GIAY_PHEP_QUA_CANH=====requestCode====" + requestCodeGiayPhepQuaCanh);
				}
				break;
				
				
				
			case PageType.ACTION_TYPE_SUA:
				log.info("=====xuLyGiayPhepQuaCanh==ACTION_TYPE_SUA");
				
				thuTucTaoGiayPhepQuaCanh(temp.getDocumentName(), MessageType.GIAY_PHEP_QUA_CANH, temp.getDocumentYear(), actionType, userName,
						requestCodeGiayPhepQuaCanh, request, response);
				
				break;
		}
	}
	
	private void keHoachXuLyLenhDieuDong(TempDocument document, int actionType, ActionRequest request, ActionResponse response, String userName,
			String requestCodeShiftingOrder) throws SystemException {
		
		BusinessUtils businessUtils = new BusinessUtils();
		if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
			
			String lanCapLDD = ParamUtil.getString(request, PageType.LAN_CAP_LENH_DIEU_DONG);
			
			log.info("==ACTION_TYPE_TIEP_NHAN==" + actionType + "==requestState==" + document.getRequestState() + "==CAP_LenhDieuDong==" + lanCapLDD);
			
			if ((document.getRequestState() != TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG) |
				(document.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG && lanCapLDD.equalsIgnoreCase(KeyParams.N_LAN)) |
				(document.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_CAP_LENH_DIEU_DONG && lanCapLDD.equalsIgnoreCase(KeyParams.MOT_LAN))) {
				
				log.info("=====ACTION_TYPE_TIEP_NHAN=====insert moi=====");
				
				String lyDoCapLaiShifOrder = ParamUtil.getString(request, "lyDoCapLaiShifOrder");
				InterfaceRequest interfaceShiftOrder = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCodeShiftingOrder);
				if (interfaceShiftOrder != null) {
					String issueDate = ParamUtil.getString(request, "issueDate");
					String remarkCapLai = BusinessUtils.getRemarkCapLai(userName, lyDoCapLaiShifOrder, FormatData.parseDateShort3StringToDate(issueDate));
					interfaceShiftOrder.setRemarks(remarkCapLai);
					InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceShiftOrder);
					
				}
				
				
				businessUtils.lenhDieuDong(request, document.getDocumentName(), document.getDocumentYear(), userName);
			}
			
		} else if (actionType == PageType.ACTION_TYPE_HUY) {
			
			log.info("==xuLyLenhDieuDong==actionType==ACTION_TYPE_HUY==");
			
			String lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_TU_CHOI);
			
			document.setRequestState(TrangThaiBanKhaiNhapCanh.DA_HUY_LENH_DIEU_DONG);
			document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
			
			TempDocumentLocalServiceUtil.updateTempDocument(document);
			
			List<IssueShiftingOrder> lstShiftOrder = IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(
					document.getDocumentName(), document.getDocumentYear(), KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
			
			for (IssueShiftingOrder item : lstShiftOrder) {
				
				item.setIsCancel(1);
				item.setCancelDate(new Date());
				item.setCancelName(userName);
				item.setCancelNote(lyDoTuChoi);
				
				item.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_HUY);
				IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(item);
			}
			
			try {
				ResultNotification notification = ResultNotificationLocalServiceUtil.findByBusinessTypeCode(MessageType.HUY_LENH_DIEU_DONG, document.getDocumentName(), document.getDocumentYear());
				if (notification == null) {
					// Them moi.
					notification = new ResultNotification();
					notification.setBusinessTypeCode(MessageType.HUY_LENH_DIEU_DONG);
					notification.setDivision("System");
					notification.setDocumentName(document.getDocumentName());
					notification.setDocumentYear(document.getDocumentYear());
					notification.setLatestDate(new Date());
					notification.setRequestCode(UUID.randomUUID().toString());
					
					if (document != null) {
						notification.setMaritimeCode(document.getMaritimeCode());
					}
					
//					String lydoHuy = ParamUtil.getString(request, "LY_DO_TU_CHOI");
//					if (lydoHuy.length() == 0) {
//						lydoHuy = "System";
//					}
//					
//					notification.setRemarks(lydoHuy);
//					
					notification.setRole(2);
					notification.setResponse(lyDoTuChoi);
					notification.setRequestState(1);
					notification.setResponseTime(new Date());
					notification.setOfficerName(userName);
					notification.setLatestDate(new Date());
					notification.setIsReply(1);
					
					ResultNotificationLocalServiceUtil.addResultNotification(notification);
				} else {
					
//					String lydoHuy = ParamUtil.getString(request, "LY_DO_TU_CHOI");
//					if (lydoHuy.length() == 0) {
//						lydoHuy = "System";
//					}
					
//					notification.setRemarks(lydoHuy);
					
					notification.setRole(2);
					notification.setResponse(lyDoTuChoi);
					notification.setRequestState(1);
					if (document != null) {
						notification.setMaritimeCode(document.getMaritimeCode());
					}
					notification.setResponseTime(new Date());
					notification.setOfficerName(userName);
					notification.setLatestDate(new Date());
					notification.setIsReply(1);
					
					ResultNotificationLocalServiceUtil.updateResultNotification(notification);
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				log.error(e.getMessage());
			}
			
			sendMessageLenhDieuDong(actionType, document, userName, requestCodeShiftingOrder, request, "");
			
		} else if (actionType == PageType.ACTION_TYPE_SUA) {
			
			String suaLenhDieuDong = ParamUtil.getString(request, PageType.LAN_SUA_LENH_DIEU_DONG);
			
			log.info("=====ACTION_TYPE_TIEP_NHAN=====" + actionType + "===requestState=====" + document.getRequestState() + "==SUA_LenhDieuDong=="
					+ suaLenhDieuDong);
			
			if ((document.getRequestState() == TrangThaiBanKhai.CHO_CAP_LENH_DIEU_DONG && suaLenhDieuDong.compareTo(KeyParams.MOT_LAN) == 0)
					| (document.getRequestState() == TrangThaiBanKhai.DA_CAP_LENH_DIEU_DONG && suaLenhDieuDong.compareTo(KeyParams.N_LAN) == 0)) {
				
				log.info("=====ACTION_TYPE_TIEP_NHAN=====update moi=====");
				businessUtils.lenhDieuDong(request, document.getDocumentName(), document.getDocumentYear(), userName);
			}
			
		} else if (actionType == PageType.ACTION_TYPE_DUYET) {
			
			String isDuyetAndGui = ParamUtil.getString(request, PageType.DUYET_VA_GUI_LENH_DIEU_DONG);
			
			if (isDuyetAndGui.length() > 0) {
				
				String duyetNLan = ParamUtil.getString(request, KeyParams.N_LAN);
				String duyenMotLan = ParamUtil.getString(request, KeyParams.MOT_LAN);
				
				String requestCodeShiftOrder = ParamUtil.getString(request, PageType.REQUEST_CODE);
				
				log.info("==QUA CANH==xuLyLenhDieuDong====Duyet_And_Gui===REQUEST_CODE==" + requestCodeShiftOrder);
				log.info("==QUA CANH==xuLyLenhDieuDong====duyet_N_Lan===================" + duyetNLan);
				log.info("==QUA CANH==xuLyLenhDieuDong====duyen_Mot_Lan=================" + duyenMotLan);
				
				IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCodeShiftOrder);
				//List<TempGeneralDeclaration> lstGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil.findByDocumentNameAndDocumentYear(document.getDocumentName(), document.getDocumentYear());
						
				
				/**Th1: BKC gá»­i trÆ°á»›c khi cáº¥p LDD
				- TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. LDD; 2. XB; 3. TB; 4. BKAN
				*/
				//----------duyenMotLan---------
				if (duyenMotLan.length() > 0) {
					document.setRequestState(TrangThaiBanKhaiNhapCanh.DA_CAP_LENH_DIEU_DONG);
					document.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
					document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.THU_TUC_DA_TIEP_NHAN);
					
					//Issues 40: Thá»�i gian tÃ u Ä‘áº¿n cáº£ng sau khi cáº¥p lá»‡nh Ä‘iá»�u Ä‘á»™ng chÆ°a cáº­p nháº­t theo thá»�i gian lá»‡nh Ä‘iá»�u Ä‘á»™ng
					//if (shiftOrder != null && Validator.isNotNull(lstGeneralDeclaration) && lstGeneralDeclaration.size() > 0) {
					if (shiftOrder != null) {
						document.setShipDateFrom(shiftOrder.getShiftingDate());
					}
					TempDocumentLocalServiceUtil.updateTempDocument(document);
				}
				
				//-------duyetNLan-----
				if (duyetNLan.length() > 0) {
					//if (shiftOrder != null && Validator.isNotNull(lstGeneralDeclaration) && lstGeneralDeclaration.size() > 0) {
					if (shiftOrder != null) {
						//Issues 40
						document.setShipDateFrom(shiftOrder.getShiftingDate());
						TempDocumentLocalServiceUtil.updateTempDocument(document);
					}
				}
				
				/**Th2: BKC gá»­i sau LDD
				- TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. BKC; 2. LDD; 3. XB; 4. TB; 5. BKAN.
				*/
				
				/**
				 * ChÃº Ã½: Viá»‡c cáº¥p láº¡i LDD cÅ©ng náº±m trong cÃ¡c trÆ°á»�ng há»£p trÃªn (cáº¥p láº¡i coi nhÆ° chÆ°a cáº¥p LDD).
				 * */
				
				
				if (shiftOrder != null) {
					// TODO Nhap Canh truong hop duyet
					shiftOrder.setIsApproval(PageType.DUYET_PHE_CHUAN);
					shiftOrder.setApprovalDate(new Date());
					shiftOrder.setApprovalName(userName);
					shiftOrder.setRequestState(TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI);
					
					IssueShiftingOrder update = IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
					
					log.info("==shiftOrder != null===update==RequestState==" + update.getRequestState());
					log.info("==isDuyetAndGui===shiftOrder===DocumentName==" + update.getDocumentName() + "===versonNo==" + update.getVersionNo());
				}
				
				log.info("==shiftOrder===documentName=====" + document.getDocumentName() + "==documentYear==" + document.getDocumentYear());
				log.info("==shiftOrder===version" + shiftOrder.getVersionNo());
				
				// XU LY Gui lenh dieu dong
				
				if (document.getRequestState() == TrangThaiBanKhaiQuaCanh.DA_CAP_LENH_DIEU_DONG &&
					Validator.isNotNull(shiftOrder.getRequestState()) && (shiftOrder.getRequestState() == TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI)) {
					log.info("==GUI_lenh dieu dong=");
					sendMessageLenhDieuDong(actionType, document, userName, requestCodeShiftingOrder, request, shiftOrder.getVersionNo());
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
				
				IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCodeShiftOrder);
				
				//List<TempGeneralDeclaration> lstGeneralDeclaratiSon = TempGeneralDeclarationLocalServiceUtil.findByDocumentNameAndDocumentYear(document.getDocumentName(), document.getDocumentYear());
				/**Th1: BKC gá»­i trÆ°á»›c khi cáº¥p LDD - TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. LDD; 2. XB; 3. TB; 4. BKAN
				*/
				
				if ((shiftOrder != null) && (shiftOrder.getStampStatus() == 0)){
					// TODO Nhap Canh truong hop cho duyet
					shiftOrder.setStampStatus(PageType.ACTION_TYPE_CHO_LANH_DAO_DUYET);
					shiftOrder.setIsApproval(PageType.KHONG_PHE_CHUAN);					
					IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
					log.info("==Chuyen_lanh_dao_duyet_ky_lenh_dieu_dong=");					
				}
			}
		}  else if (actionType == PageType.ACTION_TYPE_CHUYEN_TRA_HO_SO) {
			log.info("=====Tra ve buoc truoc==ACTION_TYPE_CHUYEN_TRA_HO_SO");
			String requestCodeShiftOrder = ParamUtil.getString(request, PageType.REQUEST_CODE);
			
			IssueShiftingOrder shiftOrderLanhDaoTraLai = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCodeShiftOrder);
			
			if (shiftOrderLanhDaoTraLai != null) {
				// TODO Nhap Canh truong hop cho duyet
				String lyDoTraLai = ParamUtil.getString(request, PageType.LY_DO_TRA_LAI_HO_SO);
				shiftOrderLanhDaoTraLai.setStampStatus(PageType.KHONG_PHE_CHUAN);
				shiftOrderLanhDaoTraLai.setIsApproval(PageType.KHONG_PHE_CHUAN);
				shiftOrderLanhDaoTraLai.setIsCancel(PageType.KHONG_PHE_CHUAN);
				shiftOrderLanhDaoTraLai.setCancelNote(lyDoTraLai);
				IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrderLanhDaoTraLai);
				log.info("==Tra ve buoc truoc, sua noi dung lenh dieu dong==");			
				
				
				BusinessUtils.updateLyDoResultNotification(userName, lyDoTraLai, MessageType.Y_CAU_TRA_LAI_HO_SO_LENH_DIEU_DONG,
						document.getMaritimeCode(), document.getDocumentName(), document.getDocumentYear());
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
					shiftOrder.setRequestState(TrangThaiBanKhaiQuaCanh.KHAI_MOI);
					shiftOrder.setStampStatus(1);
					
					String representative = shiftOrder.getRepresentative();
					String portofAuthority = shiftOrder.getPortofAuthority();
					String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
					shiftOrder.setSignTitle(signTitle);							
					shiftOrder.setSignName(ParamUtil.getString(request, "signName"));
					shiftOrder.setSignPlace(ParamUtil.getString(request, "signPlace"));
					shiftOrder.setSignDate(ParamUtil.getDate(request, "signDate", FormatData.formatDateShort3));
					
					if (addkyso > 0 ){
						shiftOrder.setAttachedFile(ParamUtil.getLong(request, "fileId"));
					}
					IssueShiftingOrder update = IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
					
					
					log.info("==shiftOrder != null===update==" + update.getRequestState() + "===versonNo==" + update.getVersionNo());
					log.info("==isDuyetAndGui===shiftOrder===DocumentName==" + update.getDocumentName() + "===versonNo==" + update.getVersionNo());
					
					// Kich ban du phong, Lanh dao ky so, khong co buoc Van thu dong dau.
					if (ConfigurationManager.getStrProp("vn.gt.yeu.cau.bo.qua.buoc.dong.dau", "").contains("1")) {
						keHoachXuLyLenhDieuDong(document, PageType.ACTION_TYPE_DONGDAU, request, response, userName, requestCodeShiftingOrder);
					}
				}
				
				log.info("==shiftOrder==documentName==" + document.getDocumentName() + "==documentYear==" + document.getDocumentYear());
				log.info("==shiftOrder==version=======" + shiftOrder.getVersionNo());
				

					
//					}
				}else if (actionType == PageType.ACTION_TYPE_DONGDAU) {
								String requestCodeShiftOrder = ParamUtil.getString(request, PageType.REQUEST_CODE);
								
								log.info("==NHAP CANH===ky so====Duyet_And_Gui===REQUEST_CODE==" + requestCodeShiftOrder);
//								log.info("==NHAP CANH===ky so====duyet_N_Lan===================" + duyetNLan);
//								log.info("==NHAP CANH===ky so====duyen_Mot_Lan=================" + duyenMotLan);							
								
								
								IssueShiftingOrder shiftOrder = IssueShiftingOrderLocalServiceUtil.getByRequestCode(requestCodeShiftOrder);
								log.info("==shiftOrder==version=======" + shiftOrder.getVersionNo());
								String versionduyet = shiftOrder.getVersionNo();
								if (versionduyet.length() > 0) {
									if (FormatData.convertToInt(versionduyet) == 1) {
										document.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);										
										document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.THU_TUC_DA_TIEP_NHAN);
									} else if (FormatData.convertToInt(versionduyet) > 1){
										// do not setDocumentStatusCode va setShipPosition
									}
								}else{				
									document.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);									
									document.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.THU_TUC_DA_TIEP_NHAN);
								}
								document.setRequestState(TrangThaiBanKhai.DA_CAP_LENH_DIEU_DONG);
								document.setShipDateFrom(shiftOrder.getShiftingDate());
								TempDocumentLocalServiceUtil.updateTempDocument(document);
								
								long adddongdau = ParamUtil.getLong(request, "fileId");
								if (shiftOrder != null) {									
									shiftOrder.setIsApproval(PageType.DUYET_PHE_CHUAN);
									//shiftOrder.setApprovalDate(new Date());
									//shiftOrder.setApprovalName(userName);
									shiftOrder.setRequestState(TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI);
									shiftOrder.setStampStatus(2);									
								
									if (adddongdau > 0) {
										shiftOrder.setAttachedFile(ParamUtil.getLong(request, "fileId"));
									}
									IssueShiftingOrder update = IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
									
									
									log.info("==shiftOrder != null===update==" + update.getRequestState() + "===versonNo==" + update.getVersionNo());
									log.info("==isDuyetAndGui===shiftOrder===DocumentName==" + update.getDocumentName() + "===versonNo==" + update.getVersionNo());
								}
								
								log.info("==shiftOrder==documentName==" + document.getDocumentName() + "==documentYear==" + document.getDocumentYear());
								log.info("==shiftOrder==version=======" + shiftOrder.getVersionNo());
								
							
								
								if (document.getRequestState() == TrangThaiBanKhaiQuaCanh.DA_CAP_LENH_DIEU_DONG
										&& Validator.isNotNull(shiftOrder.getRequestState())
										&& (shiftOrder.getRequestState() == TrangThaiBanKhaiQuaCanh.CHAP_NHAN_BAN_KHAI)) {
									log.info("==GUI_lenh dieu dong=");
									sendMessageLenhDieuDong(actionType, document, userName, requestCodeShiftingOrder, request, shiftOrder.getVersionNo());
								} else {
									log.info("==KHONG_gui_lenh_dieu_dong=");
								}
						}
		

	}
	
	private void thuTucTaoGiayPhepQuaCanh(long documentName, int messageType, int documentYear, int actionType, String userName, String requestCode,
			ActionRequest resourceRequest, ActionResponse resqonse) {
		
		try {
			
			log.info("===vao=taoGiayPhepQuaCanhThuTuc=======documentName=" + documentName);
			// Giay phep roi cang so hieu
			String numberPermissionForTransit = ParamUtil.getString(resourceRequest, "numberPermissionForTransit");
			
			String portofAuthority = ParamUtil.getString(resourceRequest, "portofAuthority");
			
			String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfship");
			String flagStateOfShip = ParamUtil.getString(resourceRequest, "stateCode");
			double gt = ParamUtil.getDouble(resourceRequest, "grt", 0L);
			int numberOfCrews = ParamUtil.getInteger(resourceRequest, "numberOfCrews");
			int numberOfPassengers = ParamUtil.getInteger(resourceRequest, "passengerNumber");
			String callSign = ParamUtil.getString(resourceRequest, "callSign");
			String nameOfMaster = ParamUtil.getString(resourceRequest, "nameOfMaster");
			
			// Loai hang hoa
			String cargoDescription = "";
			// Loai hang hoa qua canh
			String transitCargo = ParamUtil.getString(resourceRequest, "transitOfCargo");
			// So luong hang hoa qua canh
			Double volumeCargo = ParamUtil.getDouble(resourceRequest, "volumeTransitCargo", 0L);
			// Don vi tinh hang hoa qua canh
			String cargoUnit = ParamUtil.getString(resourceRequest, "unitVolumeTransitCargo");
			
			String permittedTransitFrom = ParamUtil.getString(resourceRequest, "permittedTransitFrom");
			String permittedTransitTo = ParamUtil.getString(resourceRequest, "permittedTransitTo");
			
			Date timeOfDeparture = ParamUtil.getDate(resourceRequest, "timeOfDeparture", FormatData.formatDateShort3);
			Date validUntil = ParamUtil.getDate(resourceRequest, "validUntil", FormatData.formatDateShort3);
			Date dateOfSign = ParamUtil.getDate(resourceRequest, "dateOfSign", FormatData.formatDateShort3);
			
			// String userCreated = ParamUtil.getString(resourceRequest, param);
			String userCreated = "NT";// TODO hashcode
			
			// String directorOfMaritime = ParamUtil.getString(resourceRequest,
			// param);
			String directorOfMaritime = "GT";// TODO hashcode
			
			long urs = 0;
			urs = UserLocalServiceUtil.getUserIdByEmailAddress(10154, userName);
			// log.info("=============userID ==== " + urs);
			if (urs != 0) {
				UserPort defaultCode = UserPortLocalServiceUtil
						.findByUserId(urs);
				log.info("urs!=0");
				if (defaultCode != null) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
							.getByMaritimeCode(defaultCode.getPortCode());
					log.info("dmMaritime.getCityCode()== "
							+ dmMaritime.getCityCode());
					if (dmMaritime != null) {
						directorOfMaritime = dmMaritime.getCityCode();
					}
				}
			}

			Date createdDate = new Date();
			
			String certificateNo = ParamUtil.getString(resourceRequest, "certificateNo");
			
			String unitCertificateNo = ParamUtil.getString(resourceRequest, "unitCertificateNo");
			
			String requestCodePerForTransit = ParamUtil.getString(resourceRequest, "requestCodePerForTransit");
			
			long idPerForTransit = ParamUtil.getLong(resourceRequest, "idPerForTransit");
			String versionNo = ParamUtil.getString(resourceRequest, "versionNo");
			String representative = ParamUtil.getString(resourceRequest, "representative");
			String remarks = ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI);
									
			IssuePermissionForTransit perForTransit = new IssuePermissionForTransit();
			
			// perForTransit.setRequestCode(requestCodePerForTransit);
			perForTransit.setNumberPermissionForTransit(numberPermissionForTransit);
			perForTransit.setDocumentName(documentName);
			perForTransit.setDocumentYear(documentYear);
			perForTransit.setPortofAuthority(portofAuthority);
			perForTransit.setNameOfShip(nameOfShip);
			perForTransit.setFlagStateOfShip(flagStateOfShip);
			perForTransit.setGt(gt);
			perForTransit.setNumberOfCrews(numberOfCrews);
			perForTransit.setNumberOfPassengers(numberOfPassengers);
			perForTransit.setCallSign(callSign);
			perForTransit.setNameOfMaster(nameOfMaster);
			perForTransit.setTransitCargo(transitCargo);
			perForTransit.setVolumeCargo(volumeCargo);
			perForTransit.setCargoUnit(cargoUnit);
			perForTransit.setPermittedTransitFrom(permittedTransitFrom);
			perForTransit.setPermittedTransitTo(permittedTransitTo);
			perForTransit.setTimeOfDeparture(timeOfDeparture);
			perForTransit.setValidUntil(validUntil);
			perForTransit.setDateOfSign(dateOfSign);
			perForTransit.setUserCreated(userCreated);
			perForTransit.setDirectorOfMaritime(directorOfMaritime);
			perForTransit.setCreatedDate(createdDate);
			perForTransit.setCertificateNo(certificateNo.trim() + "/" + unitCertificateNo.trim());
			perForTransit.setVersionNo(versionNo);
			
			String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
			perForTransit.setSignTitle(signTitle);
			perForTransit.setRepresentative(representative);
			
			String capPerForTransit = ParamUtil.getString(resourceRequest, PageType.LAN_CAP_PERMISSION_FOR_TRANSIT);
			String suaPerForTransit = ParamUtil.getString(resourceRequest, PageType.LAN_SUA_PERMISSION_FOR_TRANSIT);
			
			log.info("====taoGiayPhepQuaCanhThuTuc====capPerForTransit=====" + capPerForTransit + "=====suaPerForTransit=====" + suaPerForTransit);
			
			if (capPerForTransit.equalsIgnoreCase(KeyParams.MOT_LAN)) {
				
				perForTransit.setRequestCode(UUID.randomUUID().toString());
				perForTransit.setRequestState(TrangThaiBanKhaiQuaCanh.KHAI_MOI);
				
				cargoDescription = "";
				
				int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
				
				for(int index : indexs) {				    
				    
				    	TempCargoItems cargoItem = null;
				    	cargoItem = new TempCargoItems();
				    	cargoItem.setDocumentName(documentName);
				    	cargoItem.setDocumentYear(documentYear);
				    	cargoItem.setRequestCode(perForTransit.getRequestCode());
				    	cargoItem.setCargoCategory("VC");
				    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
				    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
				    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
				    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
				    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
				    	cargoItem.setSequence(index);
				    	
						try {
							if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
							{
							cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
									+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
									+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
									+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
							TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
							else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
							{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
						} catch (SystemException e) {
							log.error(e.getMessage());
						};					
				     
				    
				   }				
				
				if (cargoDescription.trim().length() > 0)
				{						
					perForTransit.setTransitCargo(StringPool.BLANK);						
					perForTransit.setCargoUnit(StringPool.BLANK);
					perForTransit.setCargoDescription(cargoDescription);
				}
				
				perForTransit = IssuePermissionForTransitLocalServiceUtil.addIssuePermissionForTransit(perForTransit);
				log.info("===========MOT_LAN==" + "INSERT");
				
			} else if (suaPerForTransit.equalsIgnoreCase(KeyParams.MOT_LAN)) {
				
				perForTransit.setId(idPerForTransit);
				perForTransit.setRequestCode(requestCodePerForTransit);
				perForTransit.setRequestState(TrangThaiBanKhaiQuaCanh.KHAI_SUA);
				
				cargoDescription = "";
				
				List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYearAndRequestCode(documentName, documentYear, requestCodePerForTransit);
				if (items != null && items.size() > 0){
					for (TempCargoItems carg: items) {
						TempCargoItemsLocalServiceUtil.deleteTempCargoItems(carg);
					}
				}
				
				int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
				
				for(int index : indexs) {				    
				    
				    	TempCargoItems cargoItem = null;
				    	cargoItem = new TempCargoItems();
				    	cargoItem.setDocumentName(documentName);
				    	cargoItem.setDocumentYear(documentYear);
				    	cargoItem.setRequestCode(perForTransit.getRequestCode());
				    	cargoItem.setCargoCategory("VC");
				    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
				    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
				    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
				    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
				    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
				    	cargoItem.setSequence(index);
				    	
						try {
							if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
							{
							cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
									+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
									+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
									+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
							TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
							else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
							{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
						} catch (SystemException e) {
							log.error(e.getMessage());
						};					
				     
				    
				   }				
				
				if (cargoDescription.trim().length() > 0)
				{						
					perForTransit.setTransitCargo(StringPool.BLANK);						
					perForTransit.setCargoUnit(StringPool.BLANK);
					perForTransit.setCargoDescription(cargoDescription);
				}
				
				perForTransit = IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransit);
				log.info("===========MOT_LAN=id=" + idPerForTransit + "=UPDATE");
				
			} else if (capPerForTransit.equalsIgnoreCase(KeyParams.N_LAN)) {
				
				perForTransit.setRequestCode(UUID.randomUUID().toString());
				perForTransit.setRequestState(TrangThaiBanKhaiQuaCanh.SUA_DOI_BO_XUNG);
				perForTransit.setRemarks(remarks);
				
				cargoDescription = "";
				
				int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
				
				for(int index : indexs) {				    
				    
				    	TempCargoItems cargoItem = null;
				    	cargoItem = new TempCargoItems();
				    	cargoItem.setDocumentName(documentName);
				    	cargoItem.setDocumentYear(documentYear);
				    	cargoItem.setRequestCode(perForTransit.getRequestCode());
				    	cargoItem.setCargoCategory("VC");
				    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
				    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
				    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
				    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
				    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
				    	cargoItem.setSequence(index);
				    	
						try {
							if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
							{
							cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
									+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
									+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
									+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
							TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
							else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
							{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
						} catch (SystemException e) {
							log.error(e.getMessage());
						};					
				     
				    
				   }				
				
				if (cargoDescription.trim().length() > 0)
				{						
					perForTransit.setTransitCargo(StringPool.BLANK);						
					perForTransit.setCargoUnit(StringPool.BLANK);
					perForTransit.setCargoDescription(cargoDescription);
				}
			
				perForTransit = IssuePermissionForTransitLocalServiceUtil.addIssuePermissionForTransit(perForTransit);
				log.info("===========N_LAN==" + "INSERT");
				
			} else if (suaPerForTransit.equalsIgnoreCase(KeyParams.N_LAN)) {
				
				perForTransit.setId(idPerForTransit);
				perForTransit.setRequestCode(requestCodePerForTransit);
				perForTransit.setRequestState(TrangThaiBanKhaiNhapCanh.SUA_DOI_BO_XUNG);
				
				cargoDescription = "";
				
				List<TempCargoItems> items = TempCargoItemsLocalServiceUtil.findBydocumentNameAnddocumentYearAndRequestCode(documentName, documentYear, requestCodePerForTransit);
				if (items != null && items.size() > 0){
					for (TempCargoItems carg: items) {
						TempCargoItemsLocalServiceUtil.deleteTempCargoItems(carg);
					}
				}
				
				int[] indexs = ParamUtil.getIntegerValues(resourceRequest, "rowIndex");
				
				for(int index : indexs) {				    
				    
				    	TempCargoItems cargoItem = null;
				    	cargoItem = new TempCargoItems();
				    	cargoItem.setDocumentName(documentName);
				    	cargoItem.setDocumentYear(documentYear);
				    	cargoItem.setRequestCode(perForTransit.getRequestCode());
				    	cargoItem.setCargoCategory("VC");
				    	cargoItem.setCargoType(ParamUtil.getString(resourceRequest, "CARGO_TYPE" + index));
				    	cargoItem.setCargoCode(ParamUtil.getString(resourceRequest, "CARGO_NAME" + index));
				    	cargoItem.setDescription(ParamUtil.getString(resourceRequest, "CARGO_DESCRIPTION" + index));
				    	cargoItem.setQuantity(FormatData.convertToDouble(ParamUtil.getString(resourceRequest, "CARGO_QUANTITY" + index)));
				    	cargoItem.setUnit(ParamUtil.getString(resourceRequest, "CARGO_UNIT" + index));
				    	cargoItem.setSequence(index);
				    	
						try {
							if ((cargoItem.getCargoType().trim().length() > 0) & (cargoItem.getUnit().trim().length() > 0))
							{
							cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName()									
									+ ", " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_TEN_HANG_HOA, cargoItem.getCargoCode()).getName()
									+ "  " + cargoItem.getDescription()  + "  " + cargoItem.getQuantity()
									+ " " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_DON_VI_TINH, cargoItem.getUnit()).getName();
							TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
							else if (cargoItem.getCargoType().equalsIgnoreCase("11"))  // NIL-KHONG CHO HANG
							{
								cargoDescription += "\n " + DmDataItemLocalServiceUtil.findByDataGroupIdAndCode0(Constants.DM_NHOM_HANG_HOA, cargoItem.getCargoType()).getName();
								TempCargoItemsLocalServiceUtil.addTempCargoItems(cargoItem);
							}
						} catch (SystemException e) {
							log.error(e.getMessage());
						};					
				     
				    
				   }				
				
				if (cargoDescription.trim().length() > 0)
				{						
					perForTransit.setTransitCargo(StringPool.BLANK);						
					perForTransit.setCargoUnit(StringPool.BLANK);
					perForTransit.setCargoDescription(cargoDescription);
				}
				perForTransit = IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(perForTransit);
				log.info("===========N_LAN=id=" + idPerForTransit + "=UPDATE");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getMessage());
		}
	}
	
	
	
	public boolean traMessageHaiQuanTruongHopQuaCanhThuTuc(int messageType, int actionType, TempDocument tempDocument, String userName,
			String requestCode, ActionRequest resourceRequest, ActionResponse httpReq) {
		boolean result = true;
		try {
			log.info("==traMessageHaiQuanTruongHopQuaCanhThuTuc==Giay phep qua canh==" + messageType + "==actionType==" + actionType + "==requestCode==" + requestCode);
			String xmlData = "";
			
//			IMTService imtService = CallWs2HaiQuan.getIMTSercice();
			BusinessUtils businessUtils = new BusinessUtils();
			
			if (messageType == MessageType.LENH_DIEU_DONG) {
				requestCode = tempDocument.getRequestCode();
			}
			if (requestCode != null) {
				InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);
				
				if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					String function = MessageType.FUNCTION_TU_SUA_DOI_BO_XUNG_THU_TUC;
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, messageType, userName, interfaceRequest);
					
					xmlData = tuChoiHoSo(messageType, function, xmlData, businessUtils, header, tempDocument, resourceRequest);
					
				} else if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
					String function = MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU;
					// if (messageType == MessageType.GIAY_PHEP_QUA_CANH) {
					// function = MessageType.FUNCTION_KHAI_BAO;
					// taoGiayPhepQuaCanhThuTuc(
					// tempDocument.getDocumentName(), messageType,
					// tempDocument.getDocumentYear(), actionType,
					// userName, requestCode, resourceRequest, httpReq);
					// }
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, messageType, userName, interfaceRequest);
					
					xmlData = tiepNhanHoSo(messageType, function, xmlData, businessUtils, header, tempDocument, resourceRequest);
					if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
						tempDocument.setRequestState(TrangThaiBanKhaiQuaCanh.CHO_CAP_LENH_DIEU_DONG);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					}
					
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					
					xmlData = createXmlHuyHoSo(messageType, xmlData, businessUtils, tempDocument, userName, interfaceRequest, resourceRequest);
					if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
						tempDocument.setRequestState(TrangThaiBanKhaiQuaCanh.DUNG_CAP_LENH_DIEU_DONG);
						TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
					}
					
				} else if (actionType == PageType.ACTION_TYPE_HOAN_THANH_THU_TUC) {
					String function = MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC;
					if (messageType == MessageType.XAC_NHAN_HOAN_THANH_THU_TUC) {
						messageType = MessageType.HO_SO;
						xacNhanHoanThanhThuTucQuaCanh(resourceRequest, tempDocument.getDocumentName(), tempDocument.getDocumentYear());
					}
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, messageType, userName, interfaceRequest);
					
					xmlData = tiepNhanHoSo(messageType, function, xmlData, businessUtils, header, tempDocument, resourceRequest);
					
				}
			}
			if (xmlData != null && xmlData.trim().length() > 0) {
				log.info("=======Du lieu Gui HAi Quan ==xmlData==");
				log.info(xmlData);
				businessUtils.insertHistorySendMessage(xmlData);
				
				String  xml = "";
				
				if( tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG) || tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)){
					log.info(" ----CAll receiveFromInland------- ");
				//	xml = imtService.receiveFromInland(xmlData);
					
				}else{
					log.info(" ----CAll receiveResultFromMT------- ");
				//	xml = imtService.receiveResultFromMT(xmlData);
				}
				log.info(xml);
				businessUtils.insertHistoryReceiveMessageResponse(xml);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}
	
	public boolean thuTucSendMessageGiayPhepQuaCanh(int messageType, String versionPermissionForTransit, int actionType, TempDocument tempDocument, String userName,
			String requestCodeGiayPhepQuaCanh, ActionRequest request, ActionResponse response) {
		boolean result = true;
		try {
			log.info("====sendMessageGiayPhepQuaCanh==" + messageType + "==actionType==" + actionType + "==requestCode==" + requestCodeGiayPhepQuaCanh);
			log.info("====sendMessageGiayPhepQuaCanh==versionPortClean==" + versionPermissionForTransit);
			String xmlData = "";
			
//			IMTService imtService = CallWs2HaiQuan.getIMTSercice();
			BusinessUtils businessUtils = new BusinessUtils();
			
			if (requestCodeGiayPhepQuaCanh != null) {
				//lay interfaceRequest cua ban khai
				InterfaceRequest interfaceRequest = null;
				try {
					interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(tempDocument.getRequestCode());
				} catch (Exception e) {
					
				}
				
				
				if (actionType == PageType.ACTION_TYPE_HUY) {
					log.info("===HUY GIAY PHEP qua canh===" + "ACTION_TYPE_HUY");
					
					String function = MessageType.FUNCTION_KHAI_HUY_HO_SO;
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, messageType, userName, interfaceRequest);
					
					String lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_TU_CHOI);
					if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
						lyDoTuChoi = "System";
					}
					Integer isApprove = null;
					IssuePermissionForTransit byrequestCode = IssuePermissionForTransitLocalServiceUtil.getByrequestCode(requestCodeGiayPhepQuaCanh);
					if (byrequestCode != null) {
						isApprove = byrequestCode.getIsApproval();
					} else {
						isApprove = 0;
					}
					
					xmlData = businessUtils.sendMessageHuyGiayPhepQuaCanh(header, BusinessUtils.getOrganizationFromUser(request),
							businessUtils.getDivision(request), header.getFrom().getName(), lyDoTuChoi, new Date(), isApprove, tempDocument.getRequestCode());
					
				} else if (actionType == PageType.ACTION_TYPE_DUYET) {
					log.info("====ACTION_TYPE_DUYET" + "===version====" + versionPermissionForTransit);
					String function = MessageType.FUNCTION_KHAI_BAO;
					
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, messageType, userName, interfaceRequest);
					header.getReference().setVersion(versionPermissionForTransit);
					
					xmlData = businessUtils.sendMessageResult(header, versionPermissionForTransit, tempDocument.getRequestCode());
					
				} else if (actionType == PageType.ACTION_TYPE_DONGDAUDUYET) {
					log.info("====ACTION_TYPE_DONGDAUDUYET" + "===version====" + versionPermissionForTransit);
					String function = MessageType.FUNCTION_KHAI_BAO;
					
					Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, messageType, userName, interfaceRequest);
					header.getReference().setVersion(versionPermissionForTransit);
					
					xmlData = businessUtils.sendMessageResult(header, versionPermissionForTransit, tempDocument.getRequestCode());
					
				}
			}
			
			log.info("=======Du_lieu_Gui_HAi_Quan ========" + xmlData);
			if (xmlData != null && xmlData.trim().length() > 0) {
				
				//businessUtils.insertHistorySendMessage(xmlData);
				businessUtils.insertHistorySendMessageThreeIssue(xmlData, userName, requestCodeGiayPhepQuaCanh);
				
				String  xml = "";
				log.info("=======Du lieu NHAN  HQMC day ========" + xml);
				
				businessUtils.insertHistoryReceiveMessageResponse(xml);
				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}
	
	public boolean sendMessageLenhDieuDong(int actionType, TempDocument document, String userName, String requestCodeShiftOrder,
			ActionRequest request, String versionShiftOrder) {
		boolean result = true;
		try {
			log.info("sendMessageLenhDieuDong==Thu tuc qua canh==" + MessageType.LENH_DIEU_DONG + "==actionType==" + actionType + "==requestCodeShiftOrder==" + requestCodeShiftOrder);
			
			String xmlData = "";
			
//			IMTService imtService = CallWs2HaiQuan.getIMTSercice();
			BusinessUtils businessUtils = new BusinessUtils();
			
			String requestCodeDoc = document.getRequestCode();
			
			if (requestCodeShiftOrder != null) {
				InterfaceRequest interfaceDocument = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCodeDoc);
				
				if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
					String function = MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU;
					Header header = BusinessUtils.createHeader(document, MessageType.QUA_CANH, function, MessageType.LENH_DIEU_DONG, userName,
							interfaceDocument);
					
					String lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_TU_CHOI);
					if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
						lyDoTuChoi = "System";
					}
					xmlData = businessUtils.sendMessageReject(header, header.getReference().getMessageId(), lyDoTuChoi, BusinessUtils
							.getOrganizationFromUser(request), businessUtils.getDivision(request), header.getFrom().getName(),
							new Date(), document.getRequestCode());
					
				} else if (actionType == PageType.ACTION_TYPE_DUYET) {
					String function = MessageType.FUNCTION_KHAI_BAO;
					
					Header header = BusinessUtils.createHeader(document, MessageType.QUA_CANH, function, MessageType.LENH_DIEU_DONG, userName,
							interfaceDocument);
					//
					header.getReference().setVersion(versionShiftOrder);
					xmlData = businessUtils.sendShiftingOrder(header, document);
					
				}  else if (actionType == PageType.ACTION_TYPE_DONGDAU) {
					
					log.info("==chinh =  ACTION_TYPE_DONGDAU=");
					String function = MessageType.FUNCTION_KHAI_BAO;
					Header header = BusinessUtils.createHeader(document, MessageType.QUA_CANH, function, MessageType.LENH_DIEU_DONG, userName,
							interfaceDocument);
					// TODO tang version InterfaceRequest
					header.getReference().setVersion(versionShiftOrder);
					log.info("==versionShiftOrder==" + header.getReference().getVersion());
					
					xmlData = businessUtils.sendShiftingOrder(header, document);
					
				} else if (actionType == PageType.ACTION_TYPE_HUY) {
					
					String reason = ParamUtil.getString(request, PageType.HUY_HO_SO);
					if (reason == null || reason.length() == 0) {
						reason = "System";
					}
					String lyDoTuChoi = ParamUtil.getString(request, PageType.LY_DO_TU_CHOI);
					if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
						lyDoTuChoi = reason;
					}
					log.info("======sendMessageLenhDieuDong===lyDoTuChoilyDoTuChoi====" + lyDoTuChoi);
					Header header = BusinessUtils.createHeader(document, MessageType.QUA_CANH, MessageType.FUNCTION_HUY_LENH_DIEU_DONG,
							MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG, userName, interfaceDocument);
					
					// truong hop gui di chua su ly
					xmlData = businessUtils.sendMessageHuyHoLenhDieuDong(header, BusinessUtils.getOrganizationFromUser(request),
							businessUtils.getDivision(request), header.getFrom().getName(), lyDoTuChoi, new Date(), 1, document.getRequestCode());
					
				}
			}
			if (xmlData != null && xmlData.trim().length() > 0) {
				log.info("=======Du lieu Gui HAi Quan ========" + xmlData);
				//businessUtils.insertHistorySendMessage(xmlData);
				businessUtils.insertHistorySendMessageThreeIssue(xmlData, userName, requestCodeShiftOrder);
				
				
				String  xml = "";
				if( document.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG) || document.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)){
					log.info(" ----CAll receiveFromInland------- ");
				//	xml = imtService.receiveFromInland(xmlData);
					
				}else{
					log.info(" ----CAll receiveResultFromMT------- ");
				//	xml = imtService.receiveResultFromMT(xmlData);
				}				log.info("=======Du lieu NHAN  HQMC day ========" + xml);
				
				businessUtils.insertHistoryReceiveMessageResponse(xml);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
			log.error(e.getMessage());
		}
		return result;
	}
	
	public void sendMessageTuChoiHoSo(String userName, ActionRequest request, TempDocument document, String requestCode, String lyDoTuChoi) throws RemoteException {
		
		InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findInterfaceRequestByRequestCode(requestCode);
		String function = MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU;
		Header header = BusinessUtils.createHeader(document, MessageType.QUA_CANH, function, MessageType.HO_SO, userName, interfaceRequest);
		
		if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
			lyDoTuChoi = "System";
		}
		BusinessUtils businessUtils = new BusinessUtils();
		String xmlData = businessUtils.sendMessageReject(header, header.getReference().getMessageId(), lyDoTuChoi,
				BusinessUtils.getOrganizationFromUser(request), businessUtils.getDivision(request), header.getFrom().getName(),
				new Date(), document.getRequestCode());
		xmlData = businessUtils.createContentSendFromBGTVTToNSW(header, xmlData);
		
//		IMTService imtService = CallWs2HaiQuan.getIMTSercice();
		if (xmlData != null && xmlData.length() > 0) {
			businessUtils.insertHistorySendMessage(xmlData);
			
			log.info("==sendMessageTuChoiHoSo==xml gui di");
			log.info(xmlData);
			
			String  xml = "";
			if( document.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG) || document.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)){
				log.info(" ----CAll receiveFromInland------- ");
			//	xml = imtService.receiveFromInland(xmlData);
				
			}else{
				log.info(" ----CAll receiveResultFromMT------- ");
			//	xml = imtService.receiveResultFromMT(xmlData);
			}
			log.info("=======Du lieu NHAN  HQMC day ========" + xml);
			businessUtils.insertHistoryReceiveMessageResponse(xml);
		}
	}
	
	public void sendMessageChapNhan(int messageType, String function, TempDocument document, String userName, String requestCode, ActionRequest request) {
		try {
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);
			
			if (interfaceRequest != null) {
				interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(userName));
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}
			Header header = BusinessUtils.createHeader(document, MessageType.QUA_CANH, function, messageType, userName, interfaceRequest);
			
			BusinessUtils businessUtils = new BusinessUtils();
//			IMTService imtService = CallWs2HaiQuan.getIMTSercice();
			String xmlData = businessUtils.sendMessageAccept(header, BusinessUtils.getOrganizationFromUser(request),
					businessUtils.getDivision(request), header.getFrom().getName(), new Date(), document.getRequestCode());
			if (xmlData != null && xmlData.length() > 0) {
				
				businessUtils.insertHistorySendMessage(xmlData);
				String xml = "";
		
				
				if( document.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG) || document.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)){
					log.info(" ----CAll receiveFromInland------- ");
				//	xml = imtService.receiveFromInland(xmlData);
					
				}else{
					log.info(" ----CAll receiveResultFromMT------- ");
				//	xml = imtService.receiveResultFromMT(xmlData);
				}
				
				businessUtils.insertHistoryReceiveMessageResponse(xml);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getMessage());
		}
	}
	
	
	private String createXmlHuyHoSo(int messageType, String xmlData, BusinessUtils businessUtils, TempDocument tempDocument, String userName,
			InterfaceRequest interfaceRequest, ActionRequest resourceRequest) {
		try {
			log.info("==createXmlHuyHoSo==MessageType==" + messageType + "==huyHoSo");
			String function = MessageType.FUNCTION_KHAI_HUY_XAC_BAO;
			if (messageType == MessageType.HO_SO) {
				function = MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC;
			}
			
			if (messageType == MessageType.XAC_BAO_TAU_QUA_CANH) {
				String lyDoTuChoi = ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI);
				if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
					lyDoTuChoi = "System";
				}
				// tu choi ho so, chung tu
				Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, MessageType.THONG_BAO_HUY_XAC_BAO, userName, interfaceRequest);
				xmlData = businessUtils.sendNoticeOfArrivalCancel(header, tempDocument, header.getReference().getMessageId(), lyDoTuChoi,
						BusinessUtils.getOrganizationFromUser(resourceRequest), businessUtils.getDivision(resourceRequest), header.getFrom().getName(), new Date());
				
			} else if (messageType == MessageType.LENH_DIEU_DONG) {
				
				String reason = ParamUtil.getString(resourceRequest, PageType.HUY_HO_SO);
				if (reason == null || reason.length() == 0) {
					reason = "System";
				}
				// tempDocument.setRequestState(TrangThaiBanKhaiNhapCanh.DA_HUY_LENH_DIEU_DONG);
				// tempDocument.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
				// tempDocument.setShipPosition(MessageType.SHIP_POSSITION_DEN_CANG);
				// TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
				// List<IssueShiftingOrder> issueShiftingOrders = IssueShiftingOrderLocalServiceUtil.findIssueShiftingOrderByDocumentYearAndDocumentYear(
				// tempDocument.getDocumentName(), tempDocument.getDocumentYear(), TrangThaiBanKhaiQuaCanh.KHAI_MOI);
				// if (issueShiftingOrders != null && issueShiftingOrders.size() > 0) {
				// IssueShiftingOrder issueShiftingOrder = issueShiftingOrders.get(0);
				// issueShiftingOrder.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_HUY);
				// IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(issueShiftingOrder);
				// }
				
				Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, MessageType.FUNCTION_HUY_LENH_DIEU_DONG,
						MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG, userName, interfaceRequest);
				// truong hop gui di chua su ly
				xmlData = businessUtils.sendMessageHuyHoLenhDieuDong(header, BusinessUtils.getOrganizationFromUser(resourceRequest),
						businessUtils.getDivision(resourceRequest), header.getFrom().getName(), reason, new Date(), 1, tempDocument.getRequestCode());
			}
			
			else {
				String reason = ParamUtil.getString(resourceRequest, PageType.HUY_HO_SO);
				if (reason == null || reason.length() == 0) {
					reason = "System";
				}
				Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, MessageType.HO_SO, userName, interfaceRequest);
				xmlData = businessUtils.sendMessageHuyHoSo(header, BusinessUtils.getOrganizationFromUser(resourceRequest),
						businessUtils.getDivision(resourceRequest), header.getFrom().getName(), new Date(), reason, tempDocument.getRequestCode());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return xmlData;
	}
	
	private String tuChoiHoSo(int messageType, String function, String xmlData, BusinessUtils businessUtils, Header header,
			TempDocument tempDocument, ActionRequest resourceRequest) {
		
		String lyDoTuChoi = ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI);
		if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
			lyDoTuChoi = "System";
		}
		xmlData = businessUtils.sendMessageReject(header, header.getReference().getMessageId(), lyDoTuChoi,
				BusinessUtils.getOrganizationFromUser(resourceRequest), businessUtils.getDivision(resourceRequest), header.getFrom().getName(),
				new Date(), tempDocument.getRequestCode());
		
		return xmlData;
	}
	
	private String tiepNhanHoSo(int messageType, String function, String xmlData, BusinessUtils businessUtils, Header header,
			TempDocument tempDocument, ActionRequest resourceRequest) {
		// function = function.trim();
		log.info("tiepNhanHoSo " + "  messageType:= " + messageType + "  function =  " + function);
		if (function.equals(MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC) && messageType == MessageType.HO_SO) {
			// chap nhan ho so, chung tu
			// xmlData = businessUtils.sendMessageConformed(header, new Date());
			xmlData = "";
			// Thong bao tau den cang
		} else if (function.equals(MessageType.FUNCTION_KHAI_BAO) && messageType == MessageType.GIAY_PHEP_QUA_CANH) {
			// chap nhan ho so, chung tu
			// xmlData = businessUtils.sendMessageConformed(header, new Date());
			xmlData = businessUtils.sendMessageResult(header, "", tempDocument.getRequestCode());
			// Thong bao tau den cang
		}
		// Chap nhan hang hoa nguy hiem
		// else if (function.equals(MessageType.FUNCTION_KHAI_BAO)
		// && messageType == MessageType.LENH_DIEU_DONG) {
		// // xac nhan hoan thanh thu tuc
		// xmlData = businessUtils.sendShiftingOrder(header, tempDocument);
		//
		// // } else if (function
		// // .equals(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)
		// // && messageType == MessageType.HO_SO) {
		// }
		else {
			// chap nhan ho so, chung tu
			xmlData = businessUtils.sendMessageAccept(header, BusinessUtils.getOrganizationFromUser(resourceRequest),
					businessUtils.getDivision(resourceRequest), header.getFrom().getName(), new Date(), tempDocument.getRequestCode());
			// Thong bao tau den cang
		}
		return xmlData;
	}
	
	
	
	
	
	private String createXmlSuaDoiBoXung(TempDocument tempDocument, String userName, ActionRequest resourceRequest, BusinessUtils businessUtils)
			throws SystemException {
		
		String xmlData;
		String function = MessageType.FUNCTION_THONG_BAO_BO_XUNG;
		String lyDoSuaDoiBoSung = ParamUtil.getString(resourceRequest, PageType.LY_DO_SUADOI_BOSUNG);
		if (lyDoSuaDoiBoSung == null || lyDoSuaDoiBoSung.length() == 0) {
			lyDoSuaDoiBoSung = "System";
		}
		InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findInterfaceRequestByRequestCode(tempDocument.getRequestCode());
		Header header = BusinessUtils.createHeader(tempDocument, MessageType.QUA_CANH, function, MessageType.HO_SO, userName, interfaceRequest);
		vn.gt.tichhop.message.Modify modify = new vn.gt.tichhop.message.Modify();
		modify.setDivision(businessUtils.getDivision(resourceRequest));
		modify.setOrganization(BusinessUtils.getOrganizationFromUser(resourceRequest));
		modify.setName(header.getFrom().getName());
		modify.setModifyDate(FormatData.parseDateToTring(new Date()));
		modify.setModifyDesc(lyDoSuaDoiBoSung);
		modify.setModifyCode("Bo Xung Ho So");
		xmlData = businessUtils.sendMessageModify(header, modify, tempDocument.getRequestCode());
		
		Modify modify2 = new Modify();
		modify2.setId(CounterLocalServiceUtil.increment(Modify.class.getName()));
		modify2.setDivision(modify.getDivision());
		modify2.setDocumentname(tempDocument.getDocumentName());
		modify2.setDocumentyear(tempDocument.getDocumentYear());
		modify2.setModifycode(modify.getModifyCode());
		modify2.setModifydesc(modify.getModifyDesc());
		modify2.setModifydate(new Date());
		ModifyLocalServiceUtil.addModify(modify2);
		
		return xmlData;
	}
	
	
	
//	private void suaDoiHoSoKeHoach(long documentName, int documentYear, String userName, ActionRequest resourceRequest, TempDocument tempDocument)
//			throws SystemException, RemoteException {
//		if (tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_TIEP_NHAN
//				|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.DA_TIEP_NHAN
//				|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.CHO_CAP_LENH_DIEU_DONG
//				|| tempDocument.getRequestState() == TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG) {
//
//			tempDocument.setRequestState(TrangThaiBanKhaiNhapCanh.KE_HOACH_YEU_CAU_SUA_DOI_BO_SUNG);
//			TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
//			String lyDoSuaDoiBoSung = ParamUtil.getString(resourceRequest, PageType.LY_DO_SUADOI_BOSUNG);
//			if (lyDoSuaDoiBoSung == null || lyDoSuaDoiBoSung.length() == 0) {
//				lyDoSuaDoiBoSung = "System";
//			}
//			BusinessUtils.insertOrUpdateResultMinistry((int)documentName, documentYear, MessageType.FUNCTION_THONG_BAO_BO_XUNG,
//					BusinessUtils.getRemarkBoSung(userName, lyDoSuaDoiBoSung));
//
//			BusinessUtils.insertResultHistoryMinistry((int)documentName, documentYear, MessageType.FUNCTION_THONG_BAO_BO_XUNG,
//					BusinessUtils.getRemarkBoSung(userName, lyDoSuaDoiBoSung));
//
//			// ResultNotification
//
//			insertOrUpdateResultNotification(tempDocument, userName, lyDoSuaDoiBoSung, MessageType.BO_SUNG_KE_HOACH);
//
//		}
//
//		BusinessUtils businessUtils = new BusinessUtils();
//		String xmlData = createXmlSuaDoiBoXung(tempDocument, userName, resourceRequest, businessUtils);
//		IMTService imtService = CallWs2HaiQuan.getIMTSercice();
////		IMTextService imtService = CallWs2HaiQuan.getIMTextSercice();
//		if (xmlData != null && xmlData.length() > 0) {
//			businessUtils.insertHistorySendMessage(xmlData);
//			// InterfaceRequest interfaceRequest =
//			// InterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);
//			// interfaceRequest.setRequestState(0);
//			// InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
//			log.info("xmlData==" + xmlData);
//			String  xml = "";
//
//			/*if( tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_VAO_CANG) || tempDocument.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_ROI_CANG)){
//				log.info(" ----CAll receiveFromInland------- ");
////				xml = imtService.receiveFromInland(xmlData);
//				xml = imtService.receiveResultFromMT(xmlData);
//			}else{
//				log.info(" ----CAll receiveResultFromMT------- ");
////				xml = imtService.receiveResultFromMT(xmlData);
//				xml = imtService.receiveResultFromMT(xmlData);
//			}*/
//			xml = imtService.receiveResultFromMT(xmlData);
//			log.info("xml======" + xml);
//			// if((xml!= null) && (xml.trim() != "")){
//			// interfaceRequest.setRequestState(1);
//			// InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
//			// }
//			log.info("=======Du lieu NHAN  HQMC day ========" + xml);
//
//			businessUtils.insertHistoryReceiveMessageResponse(xml);
//		}
//	}
	
	private void insertOrUpdateResultNotification(TempDocument temp, String userName, String lyDoTuChoi, int messageType) {
		try {
			// MessageType.HUY_LENH_DIEU_DONG
			ResultNotification resultNotification = ResultNotificationLocalServiceUtil.findByBusinessTypeCode(messageType, temp.getDocumentName(),
					temp.getDocumentYear());
			if (resultNotification == null) {
				// Them moi.
				resultNotification = new ResultNotification();
				resultNotification.setBusinessTypeCode(messageType);
				resultNotification.setDivision("System");
				resultNotification.setDocumentName(temp.getDocumentName());
				resultNotification.setDocumentYear(temp.getDocumentYear());
				resultNotification.setLatestDate(new Date());
				resultNotification.setRequestCode(UUID.randomUUID().toString());
				
				if (temp != null) {
					resultNotification.setMaritimeCode(temp.getMaritimeCode());
				}
				
				// resultNotification.setRemarks(userName);
				
				// setRole Thutuc - Kehoach
				if (messageType == MessageType.BO_SUNG_THU_TUC || messageType == MessageType.HUY_GIAY_PHEP_QUA_CANH ) {
					resultNotification.setRole(4);
				} else if (messageType == MessageType.HUY_LENH_DIEU_DONG
							|| messageType ==  MessageType.BO_SUNG_KE_HOACH	) {
					resultNotification.setRole(2);
				} else {
					resultNotification.setRole(2);
				}
				resultNotification.setResponse(lyDoTuChoi);
				resultNotification.setRequestState(1);
				resultNotification.setResponseTime(new Date());
				resultNotification.setOfficerName(userName);
				resultNotification.setLatestDate(new Date());
				resultNotification.setIsReply(1);
				
				ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
			} else {
				// setRole Thutuc - Kehoach
				if (messageType == MessageType.BO_SUNG_THU_TUC || messageType == MessageType.HUY_GIAY_PHEP_QUA_CANH ) {
					resultNotification.setRole(4);
				} else if (messageType == MessageType.HUY_LENH_DIEU_DONG
							|| messageType ==  MessageType.BO_SUNG_KE_HOACH	) {
					resultNotification.setRole(2);
				} else {
					resultNotification.setRole(2);
				}
				resultNotification.setRole(2);
				resultNotification.setResponse(lyDoTuChoi);
				resultNotification.setRequestState(1);
				if (temp != null) {
					resultNotification.setMaritimeCode(temp.getMaritimeCode());
				}
				resultNotification.setResponseTime(new Date());
				resultNotification.setOfficerName(userName);
				resultNotification.setLatestDate(new Date());
				resultNotification.setIsReply(1);
				
				ResultNotificationLocalServiceUtil.updateResultNotification(resultNotification);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/*private void thayDoiTrangThaiToanBoBanKhaiThuTuc(int actionType, String requestCode) throws SystemException {
		TempGeneralDeclaration tempGeneralDeclaration = TempGeneralDeclarationLocalServiceUtil.findTempGeneralDeclarationByRequestCode(requestCode);
		if (tempGeneralDeclaration != null) {
			
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				tempGeneralDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
			} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				tempGeneralDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				tempGeneralDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
			} else if (actionType == PageType.ACTION_TYPE_HUY) {
				tempGeneralDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
			}
			TempGeneralDeclarationLocalServiceUtil.updateTempGeneralDeclaration(tempGeneralDeclaration);
		}
		
		TempCrewList tempCrewList = TempCrewListLocalServiceUtil.findTempCrewListByRequestCode(requestCode);
		if (tempCrewList != null) {
			
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				tempCrewList.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
			} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				tempCrewList.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				tempCrewList.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
			} else if (actionType == PageType.ACTION_TYPE_HUY) {
				tempCrewList.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
			}
			TempCrewListLocalServiceUtil.updateTempCrewList(tempCrewList);
		}
		
		TempPassengerList tempPassenger = TempPassengerListLocalServiceUtil.findTempPassengerListByRequestCode(requestCode);
		if (tempPassenger != null) {
			
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				tempPassenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
			} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				tempPassenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				tempPassenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
			} else if (actionType == PageType.ACTION_TYPE_HUY) {
				tempPassenger.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
			}
			TempPassengerListLocalServiceUtil.updateTempPassengerList(tempPassenger);
		}
		
		List<TempDangerousGoodsManifest> resultDangerousGoodsNanifests = TempDangerousGoodsNanifestLocalServiceUtil.findByRequestCode(requestCode);
		if (resultDangerousGoodsNanifests != null && resultDangerousGoodsNanifests.size() > 0) {
			TempDangerousGoodsManifest tempDangerousGoodsNanifest = resultDangerousGoodsNanifests.get(0);
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				tempDangerousGoodsNanifest.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
			} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				tempDangerousGoodsNanifest.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				tempDangerousGoodsNanifest.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
			} else if (actionType == PageType.ACTION_TYPE_HUY) {
				tempDangerousGoodsNanifest.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
			}
			TempDangerousGoodsNanifestLocalServiceUtil.updateTempDangerousGoodsNanifest(tempDangerousGoodsNanifest);
		}
		
		List<TempShipStoresDeclaration> resultTempShipStoresDeclarations = TempShipStoresDeclarationLocalServiceUtil.findByRequestCode(requestCode);
		if (resultTempShipStoresDeclarations != null && resultTempShipStoresDeclarations.size() > 0) {
			TempShipStoresDeclaration tempShipStoresDeclaration = resultTempShipStoresDeclarations.get(0);
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
			} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
			} else if (actionType == PageType.ACTION_TYPE_HUY) {
				tempShipStoresDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
			}
			TempShipStoresDeclarationLocalServiceUtil.updateTempShipStoresDeclaration(tempShipStoresDeclaration);
		}
		
		List<TempCrewEffectsDeclaration> resultTempCrewEffectsDeclarations = TempCrewEffectsDeclarationLocalServiceUtil
				.findByRequestCode(requestCode);
		if (resultTempCrewEffectsDeclarations != null && resultTempCrewEffectsDeclarations.size() > 0) {
			TempCrewEffectsDeclaration tempCrewEffectsDeclaration = resultTempCrewEffectsDeclarations.get(0);
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
			} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
			} else if (actionType == PageType.ACTION_TYPE_HUY) {
				tempCrewEffectsDeclaration.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
			}
			TempCrewEffectsDeclarationLocalServiceUtil.updateTempCrewEffectsDeclaration(tempCrewEffectsDeclaration);
		}
		
		List<TempDeclarationOfHealth> resultTempDeclarationOfHealths = TempDeclarationOfHealthLocalServiceUtil.findByRequestCode(requestCode);
		if (resultTempDeclarationOfHealths != null && resultTempDeclarationOfHealths.size() > 0) {
			TempDeclarationOfHealth tempDeclarationOfHealth = resultTempDeclarationOfHealths.get(0);
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
			} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
			} else if (actionType == PageType.ACTION_TYPE_HUY) {
				tempDeclarationOfHealth.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
			}
			TempDeclarationOfHealthLocalServiceUtil.updateTempDeclarationOfHealth(tempDeclarationOfHealth);
		}
		
		List<TempAnimalQuarantine> resultAnimalQuarantines = TempAnimalQuarantineLocalServiceUtil.findByRequestCode(requestCode);
		if (resultAnimalQuarantines != null && resultAnimalQuarantines.size() > 0) {
			TempAnimalQuarantine tempPlantQuarantine = resultAnimalQuarantines.get(0);
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
			} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
			} else if (actionType == PageType.ACTION_TYPE_HUY) {
				tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
			}
			TempAnimalQuarantineLocalServiceUtil.updateTempAnimalQuarantine(tempPlantQuarantine);
		}
		
		List<TempPlantQuarantine> resultTempPlantQuarantines = TempPlantQuarantineLocalServiceUtil.findByRequestCode(requestCode);
		if (resultTempPlantQuarantines != null && resultTempPlantQuarantines.size() > 0) {
			TempPlantQuarantine tempPlantQuarantine = resultTempPlantQuarantines.get(0);
			if (actionType == PageType.ACTION_TYPE_TIEP_NHAN) {
				tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TIEP_NHAN);
			} else if (actionType == PageType.ACTION_TYPE_DOI_CHIEU) {
				tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			} else if (actionType == PageType.ACTION_TYPE_TU_CHOI) {
				tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_TU_CHOI);
			} else if (actionType == PageType.ACTION_TYPE_HUY) {
				tempPlantQuarantine.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_HUY);
			}
			TempPlantQuarantineLocalServiceUtil.updateTempPlantQuarantine(tempPlantQuarantine);
		}
	}*/
	
}
