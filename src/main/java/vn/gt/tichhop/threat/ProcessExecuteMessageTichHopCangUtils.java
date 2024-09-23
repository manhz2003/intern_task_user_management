/**
 * 
 */
package vn.gt.tichhop.threat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fds.flex.common.ultility.array.ArrayUtil;
import com.fds.nsw.kernel.exception.SystemException;


import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;

import com.fds.nsw.nghiepvu.model.DmMaritime;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmGtRouteConfig;
import vn.gt.dao.danhmucgt.service.DmGtRouteConfigLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest;
import com.fds.nsw.nghiepvu.model.InterfaceRequest;
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempPassengerList;
import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
import vn.gt.dao.noticeandmessage.service.HistoryInterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDangerousGoodsNanifestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPassengerListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultCompletion;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import com.fds.nsw.nghiepvu.model.ResultNotification;
import com.fds.nsw.nghiepvu.model.TempDebitnote;


import vn.gt.dao.result.service.ResultCompetionLocalServiceUtil;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.dao.result.service.ResultNotificationLocalServiceUtil;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.portlet.kehoach.nghiepvu.bankhai.BanKhaiLenhDieuDongUtils;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.portlet.phuongtien.VMAUtils;
import vn.gt.portlet.phuongtien.VmaItineraryUtils;
import vn.gt.tichhop.message.AccepterMessage;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.tichhop.message.CangVuBussinessUtils;
import vn.gt.tichhop.message.DeNghiCapLaiGiayPhep;
import vn.gt.tichhop.message.DelayMessage;
import vn.gt.tichhop.message.MessageFactory;
import vn.gt.tichhop.message.MessageSyncUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.Modify;
import vn.gt.tichhop.message.RejectMessage;
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiQuaCanh;
import vn.gt.tichhop.message.TrangThaiBanKhaiXuatCanh;
import vn.gt.tichhop.message.TrangThaiHoSo;
import vn.gt.tichhop.message.VmaCangVuToBGTVTBusinessUtils;
import vn.gt.tichhop.message.XacNhanHuyThuTuc;
import vn.gt.tichhop.message.giaothong2haiquan.CreateMessageFactory;
import vn.gt.tichhop.message.giaothong2haiquan.FeeNotice2Xml;
import vn.gt.tichhop.sendmessage.MessgaePriorityLevels;
import vn.gt.tichhop.sendmessage.SendMessgaeFunctions;
import vn.gt.utils.ConstantCoQuanBanNganh;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.nsw.Header;
import vn.nsw.Header.Reference;
import vn.nsw.Header.Subject;
import vn.nsw.Header.To;
import vn.nsw.fac.ReadMessages;
import vn.nsw.model.FeeApproved;
import vn.nsw.model.FeeNotice;
import vn.nsw.model.PermissionForTransit;
import vn.nsw.model.PortClearance;
import vn.nsw.model.ShiftingOrder;
import vn.nsw.model.XacNhanHuyLenhDieuDong;

/**
 * @author win_64
 */
@Slf4j
public class ProcessExecuteMessageTichHopCangUtils {
	
	
	private BusinessUtils businessUtils = new BusinessUtils();
	private CangVuBussinessUtils cangvuBussionessUtils = new CangVuBussinessUtils();
	
	public ProcessExecuteMessageTichHopCangUtils() {
	}
	
	public static final String[] FUNCTION_VALUES = { "21", "22", "23", "24", "25", "27", "28", "29" };
	
	public static boolean checkFunction(String function) {
		return ArrayUtil.contains(FUNCTION_VALUES, function);
	}
	
	public String nhanThongTinCangVu(ObjectExcute objectExcute) {
		String dataXml = "";
		
		try {
			if (objectExcute != null) {
				if (objectExcute.getHeader() == null && objectExcute.getLiObjects() == null) {
					
					String data = businessUtils.replaceXML(objectExcute.getXmlData().trim());
					
					Header headerReceive = ReadMessages.readXMLMessagesHeader(data);
					
					String function = headerReceive.getSubject().getFunction();
					Integer messageType = headerReceive.getSubject().getType();
					String responseData = StringPool.BLANK;
					
					boolean validate = false;
					
					if(MessageSyncUtil.checkHeader(headerReceive)) {
						//TODO: MTGateway hoac CANG VU XU LY
						
						if (messageType == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
							validate = tichHop_BanKhaiAnNinh(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.BAN_KHAI_HANG_HOA) {
							validate = tichHop_BanKhaiHangHoa(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.THONG_BAO_TAU_DEN_CANG) {
							validate = tichHop_ThongBao_TauDenCang(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.XAC_BAO_TAU_DEN_CANG) {
							validate = tichHop_XacBao_TauDenCang(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.HO_SO) {
							validate = tichHop_HoSo(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.LENH_DIEU_DONG) {
							validate = tichHop_LenhDieuDong(objectExcute, headerReceive);
						
						} else if (messageType == MessageType.HUY_LENH_DIEU_DONG) {
							validate = huyLenhDieuDong(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.THONG_BAO_TAU_ROI_CANG) {
							validate = tichHop_ThongBao_TauRoiCang(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH) {
							validate = tichHop_GiayPhep_RoiCang(objectExcute, headerReceive);
						
						} else if (messageType == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG) {
							validate = yCauLuuLaiGiayPhepRoiCang(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.HUY_GIAY_PHEP_ROI_CANG) {
							validate = huyGiayPhepRoiCang(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.GIAY_PHEP_QUA_CANH) {
							validate = tichHop_GiayPhep_QuaCanh(objectExcute, headerReceive);
						
						} else if (messageType == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH) {
							validate = yCauLuuLaiGiayPhepQuaCanh(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.HUY_GIAY_PHEP_QUA_CANH) {
							validate = huyGiayPhepQuaCanh(objectExcute, headerReceive);							
						
						} else if (messageType == MessageType.THONG_BAO_TAU_QUA_CANH) {
							validate = tichHop_ThongBao_TauQuaCanh(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.XAC_BAO_TAU_QUA_CANH) {
							validate = tichHop_XacBao_TauQuaCanh(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.BAN_KHAI_KET_QUA_XEM_XET_CHUNG_TU_DINH_KEM) {
							validate = tichHop_KetQua_XemXetChungTuDinhKem(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.CHAM_PHE_DUYET_HOAN_THANH_THU_THUC) {
							validate = tichHop_ThongBao_ChamPheDuyetHTTT(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.BAN_KHAI_CHUNG) {
							validate = tichHop_BAN_KHAI_CHUNG(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN) {
							validate = tichHop_BAN_KHAI_DANH_SACH_THUYEN_VIEN(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH) {
							validate = tichHop_BAN_KHAI_DANH_SACH_HANH_KHACH(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM) {
							validate = tichHop_BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN) {
							validate = tichHop_GiayPhep_RoiCang(objectExcute, headerReceive);
						
						} else if (messageType == MessageType.PTTND_GIAY_PHEP_VAO_CANG) {
							validate = tichHop_GiayPhep_RoiCang(objectExcute, headerReceive);
						
						} else if (messageType == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG) {
							validate = yCauLuuLaiGiayPhepVaoCang(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.HUY_GIAY_PHEP_VAO_CANG) {
							validate = huyGiayPhepVaoCang(objectExcute, headerReceive);
							
						} else if (messageType == MessageType.TYPE_THONG_BAO_NOP_PHI) {
							validate = thongBaoNopPhi(objectExcute, headerReceive);
						
						} else if (messageType == MessageType.TYPE_THONG_BAO_XAC_NHAN_NOP_PHI) {
							validate = thongBaoXacNhanNopPhi(objectExcute, headerReceive);
						
						} else if(ArrayUtil.contains(MessageType.DONG_BO_DM_MESSAGE_TYPES, messageType)) {
							// DONG BO DANH MUC MOI							
							validate = MessageSyncUtil.luuDanhMuc(objectExcute, headerReceive);
						}  else if(ArrayUtil.contains(MessageType.DONG_BO_VMA_MESSAGE_TYPES, messageType)) {
							// DONG BO VMA							
							validate = VmaCangVuToBGTVTBusinessUtils.luuVMATables(objectExcute, headerReceive);
						} else if (messageType == MessageType.TYPE_TICH_HOP_KE_TOAN_VIET_DA) {
							// DONG BO BIEN LAI DIEN TU - KE TOAN VIET DA
							responseData = VmaCangVuToBGTVTBusinessUtils.dongBoBienLaiDienTu(objectExcute, headerReceive);						
						} 
						
						// Voi nhung MSG gui tu CV toi MTGateway va can dong bo sang NSW
						List<DmGtRouteConfig> routeConfigs = DmGtRouteConfigLocalServiceUtil.findByLocCode(MessageType.NSW);
						
						if(routeConfigs != null && routeConfigs.size() > 0) {
							if (function != null && checkFunction(function)) {
								sendMessage2HQ(objectExcute.getXmlData());
							} else {
								if (messageType == MessageType.GIAY_PHEP_QUA_CANH || messageType == MessageType.HUY_GIAY_PHEP_QUA_CANH || messageType == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH ||
										messageType == MessageType.CHAM_PHE_DUYET_HOAN_THANH_THU_THUC || messageType == MessageType.BAN_KHAI_KET_QUA_XEM_XET_CHUNG_TU_DINH_KEM ||
										messageType == MessageType.LENH_DIEU_DONG || messageType == MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH
										|| messageType == MessageType.HUY_LENH_DIEU_DONG || messageType == MessageType.HUY_GIAY_PHEP_ROI_CANG
										|| messageType == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG || messageType == MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN
										|| messageType == MessageType.PTTND_GIAY_PHEP_VAO_CANG || messageType == MessageType.HUY_GIAY_PHEP_VAO_CANG
										|| messageType == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG
										|| messageType == MessageType.TYPE_THONG_BAO_NOP_PHI || messageType == MessageType.TYPE_THONG_BAO_XAC_NHAN_NOP_PHI) {
									sendMessage2HQ(objectExcute.getXmlData());
								}
							}
						}
						
						if (!validate) {
							dataXml = createMessageSend2CangVu(headerReceive, false);
						} else {
							dataXml = createMessageSend2CangVu(headerReceive, true);
						}
						
						if (messageType == MessageType.TYPE_TICH_HOP_KE_TOAN_VIET_DA) {
							dataXml = responseData;
						}
					} else {
						// XU LY MSG TU NSW
						BusinessUtils businessUtils = new BusinessUtils();
						dataXml = businessUtils.receiveMessage(data);
					}
				}
				
			}
		} catch (Exception e) {
			log.error(e.getMessage());			
		}
		
		return dataXml;
	}
	

	private void sendMessage2HQ(String xmlData) {
		try {
			//IMTService imtService = CallWs2HaiQuan.getIMTSercice();
//			IMTextService imtService = CallWs2HaiQuan.getIMTextSercice();
			String xml = "";
			log.info("sendMessage2HQ  " + xmlData);
			
			xmlData = businessUtils.replaceXML(xmlData);
			Header header = ReadMessages.readXMLMessagesHeader(xmlData);
			header.getFrom().setIdentity(ConstantCoQuanBanNganh.BGTVT);
			header.getFrom().setName(ConstantCoQuanBanNganh.TEN_BGTVT);
			businessUtils.insertHistorySendMessage(xmlData);
			Object object = MessageFactory.convertXmltoObjectWithFunction(new Integer(header.getSubject().getFunction()), header.getSubject()
					.getType(), xmlData);
			log.info("object" + object +"/ object != null: " + (object != null));
			if (object != null) {
				log.info("obobject.getClass().getName()g"+object.getClass().getName());
				
				if (object instanceof RejectMessage) {
					RejectMessage rejectMessage = (RejectMessage) object;
					xml = CreateMessageFactory.createMessageReject(rejectMessage.getRejectCode(), rejectMessage.getRejectDesc(),
							rejectMessage.getOrganization(), rejectMessage.getDivision(), rejectMessage.getName(),
							FormatData.parseStringToDate(rejectMessage.getRejectDate()));
					// resultMinistry.set
				}else if (object instanceof DelayMessage) {
					DelayMessage delaytMessage = (DelayMessage) object;
					xml = CreateMessageFactory.createMessageReject(delaytMessage.getDelayCode(), delaytMessage.getDelayDesc(),
							delaytMessage.getOrganization(), delaytMessage.getDivision(), delaytMessage.getName(),
							FormatData.parseStringToDate(delaytMessage.getDelayDate()));
					// resultMinistry.set
				}
				else if (object instanceof Modify) {
					log.info("************************Modify");
					Modify modifyMessage = (Modify) object;
					xml = CreateMessageFactory.createMessageModify(modifyMessage.getModifyCode(), modifyMessage.getModifyDesc(),
							modifyMessage.getOrganization(), modifyMessage.getDivision(), modifyMessage.getName(),
							FormatData.parseStringToDate(modifyMessage.getModifyDate()));
					// resultMinistry.set
				}else if (object instanceof AccepterMessage) {
					AccepterMessage accepterMessage = (AccepterMessage) object;
					xml = CreateMessageFactory.createMessageAccept(accepterMessage.getOrganization(), accepterMessage.getDivision(),
							accepterMessage.getName(), new Date());
				} else if (object instanceof String) {
					String receiveDate = (String) object;
					xml = CreateMessageFactory.createMessageHoanThanh(receiveDate);
				} else if (object instanceof XacNhanHuyThuTuc) {
					XacNhanHuyThuTuc xacNhanHuyThuTuc = (XacNhanHuyThuTuc) object;
					xml = CreateMessageFactory.createMessageHuyHoSo(xacNhanHuyThuTuc.getOrganization(), xacNhanHuyThuTuc.getDivision(),
							xacNhanHuyThuTuc.getName(), FormatData.parseStringToDate(xacNhanHuyThuTuc.getCancelDate()), xacNhanHuyThuTuc.getReason());
				} else if (object instanceof ShiftingOrder) {
					ShiftingOrder shiftingOrder = (ShiftingOrder) object;
					xml = sendShiftingOrder(header, shiftingOrder, businessUtils);
					
				} else if (object instanceof PortClearance) {
					PortClearance portClearance = (PortClearance) object;
					
					if (portClearance.getPortofAuthority() != null && portClearance.getPortofAuthority().length() > 0) {
						DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portClearance.getPortofAuthority());
						log.debug("portClearance.getPortofAuthority()   " + portClearance.getPortofAuthority());
						if (dmMaritime != null) {
							log.debug("dmMaritime.getMaritimeReference()" + dmMaritime.getMaritimeReference());
							portClearance.setPortofAuthority(dmMaritime.getMaritimeReference());
						}
					} else {
						log.debug("khong co gi");
					}
					// portClearance.setPortofAuthority(t)
					xml = MessageFactory.convertObjectToXml(portClearance);
					// son_vh bo sung 20160119
				} else if (object instanceof PermissionForTransit) {
					PermissionForTransit permissionForTransit = (PermissionForTransit) object;
					
					if (permissionForTransit.getPortofAuthority() != null && permissionForTransit.getPortofAuthority().length() > 0) {
						DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(permissionForTransit.getPortofAuthority());
						log.debug("permissionForTransit.getPortofAuthority()   " + permissionForTransit.getPortofAuthority());
						if (dmMaritime != null) {
							log.debug("dmMaritime.getMaritimeReference()" + dmMaritime.getMaritimeReference());
							permissionForTransit.setPortofAuthority(dmMaritime.getMaritimeReference());
						}
					} else {
						log.debug("khong co gi");
					}
					xml = MessageFactory.convertObjectToXml(permissionForTransit);
					
				
					
				} else if (object instanceof FeeNotice) {
					FeeNotice feeNotice = (FeeNotice) object;
					
					TempDebitnote debitNote = TempDebitNoteLocalServiceUtil.getByNumberDebit(feeNotice.getInvoiceNo());
					
					if(debitNote != null) {
						String url = FeeNotice2Xml.buildURL(debitNote.getDocumentName(), debitNote.getDocumentYear(), 
								debitNote.getDebitnotenumber(), String.valueOf(debitNote.getMariTimeCode()));
						feeNotice.setInvoiceUrl(url);
					}
					
					xml = MessageFactory.convertObjectToXml(feeNotice);
					log.info("===FEENOTICE===XML===" + xml);
					
				}  else if (object instanceof FeeApproved) {
					FeeApproved feeApproved = (FeeApproved) object;
					
					xml = MessageFactory.convertObjectToXml(feeApproved);
					
				} else if (object instanceof String) {
					StringBuilder xmlResult = new StringBuilder("");
					xmlResult.append("<ReceiveDate>");
					xmlResult.append((String) object);
					xmlResult.append("</ReceiveDate>");
					xml = xmlResult.toString();
				}
			}
			
			if (xml != null && xml.length() > 0) {
				xml = BusinessUtils.createContentSendFromBGTVTToNSW(header, xml);
				//log.info(" Gui Hai Quan xml  " + xml);
				//xml = imtService.receiveResultFromMT(xml);
				//log.info("Nhan tu hai quan xml  " + xml);
				//businessUtils.insertHistoryReceiveMessageResponse(xml);
				SendMessgaeFunctions.insertMessageQueue(header, xml,
						MessageType.NSW, null, MessgaePriorityLevels.HIGHT);
			} else {
				log.error("====CVHH_DO_NOT_SENT_TO_HQ===");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private String createMessageSend2CangVu(Header headerReceive, boolean check) {
		String identity = Long.toString(System.currentTimeMillis());

		Reference ref = headerReceive.getReference();

		ref.setMessageId("BGTVT" + identity);

		Header.From from = headerReceive.getFrom();

		String name = from.getName();
		from.setName("BGTVT");
		from.setIdentity("SYSTEM");
		Subject subject = headerReceive.getSubject();
		
		if (check) {
			subject.setFunction(String.valueOf(MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST));
		} else {
			subject.setFunction(String.valueOf(MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_ERROR_VALIDATION));
		}
		
		subject.setSendDate(FormatData.parseDateToTring(new Date()));
		To to = headerReceive.getTo();
		String toIdentity = to.getIdentity();
		to.setIdentity(from.getIdentity());
		from.setIdentity(toIdentity);
		to.setName(name);
		BusinessUtils businessUtils = new BusinessUtils();
		return businessUtils.sendMessageHoSo(headerReceive, new Date());
	}
	
	private boolean tichHop_BanKhaiAnNinh(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempShipSecurityMessage> temps = TempShipSecurityMessageLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(temps) && temps.size() > 0) {
				
				TempShipSecurityMessage temp = temps.get(0);
				temp.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(temp);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				AccepterMessage accepterMessage = MessageFactory.convertXmltoAccepterMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_CHAP_NHAN_HO_SO, accepterMessage.getName(), "");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			// Gui ban tin sang HQMC: Function 21 messagetype 10
			// sendChapNhan(objectExcute);
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempShipSecurityMessage> temps = TempShipSecurityMessageLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(temps) && temps.size() > 0) {
				TempShipSecurityMessage temp = temps.get(0);
				temp.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(temp);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				RejectMessage rejectMessage = MessageFactory.convertXmltoRejectMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_TU_CHOI_HO_SO, rejectMessage.getName(),
						rejectMessage.getRejectDesc());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			// 4, Gui ban tin sang HQMC: Neu He thong KHoNG tu choi, ma do Can bo tu choi thi gui HQMC.
			// Function 22 messagetype 10 sendChapNhan(objectExcute);
		}
		
		return result;
	}
	
	private boolean tichHop_ThongBao_TauDenCang(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		// Trang thai request ban dau
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			TempNoticeShipMessage tempTBs = TempNoTiceShipMessageLocalServiceUtil.findTempNoTiceShipMessageTbByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(tempTBs)) {
				tempTBs.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempTBs);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			
			try {
				AccepterMessage accepterMessage = MessageFactory.convertXmltoAccepterMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_CHAP_NHAN_HO_SO, accepterMessage.getName(), "");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			// updateOrInsertInterfaceRequest(requestCodeOld,
			// MessageType.FUNCTION_CHAP_NHAN_HO_SO);
			// 4, GUI ban tin sang HQMC
			// sendChapNhan(objectExcute);
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			TempNoticeShipMessage tempTB = TempNoTiceShipMessageLocalServiceUtil.findTempNoTiceShipMessageTbByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(tempTB)) {
				tempTB.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempTB);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				RejectMessage rejectMessage = MessageFactory.convertXmltoRejectMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_TU_CHOI_HO_SO, rejectMessage.getName(),
						rejectMessage.getRejectDesc());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			// Gui ban tin sang HQMC: Neu He thong KHoNG tu choi, ma do Can bo
			// tu choi thi gui HQMC.
			// Function 22 messagetype 10
			// sendChapNhan(objectExcute);
		}
		return result;
	}
	
	private boolean tichHop_BanKhaiHangHoa(ObjectExcute objectExcute, Header headerReceive) {
		boolean result = true;
		
		return result;
	}
	
	private boolean tichHop_XacBao_TauDenCang(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(xmlData);
		
		result = businessUtils.validationData(headerReceive, liObjects, BusinessUtils.CangVuToBoGiaoThong);
		
		// Trang thai request ban dau
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		if (!result) {
			HistoryInterfaceRequest hiRequest = HistoryInterfaceRequestLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (hiRequest != null) {
				hiRequest.setRequestState(TrangThaiHoSo.HISTORY_REQUEST_STATE_KHONG_GHI_NHAN);
				HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(hiRequest);
			}
			return result;
		}
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			TempNoticeShipMessage tempXB = TempNoTiceShipMessageLocalServiceUtil.findTempNoTiceShipMessageXbByRequestCode(requestCodeOld);
			if (Validator.isNotNull(tempXB)) {
				tempXB.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempXB);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			TempNoticeShipMessage tempXB = TempNoTiceShipMessageLocalServiceUtil.findTempNoTiceShipMessageXbByRequestCode(requestCodeOld);
			if (Validator.isNotNull(tempXB)) {
				tempXB.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempXB);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			
		}
		return result;
	}
	
	private boolean tichHop_HoSo(ObjectExcute objectExcute, Header headerReceive) throws Exception {
		boolean result = true;
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(xmlData);
		
		result = businessUtils.validationData(headerReceive, liObjects, BusinessUtils.CangVuToBoGiaoThong);
		
		// Trang thai request ban dau
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		if (!result) {
			HistoryInterfaceRequest hiRequest = HistoryInterfaceRequestLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (hiRequest != null) {
				hiRequest.setRequestState(TrangThaiHoSo.HISTORY_REQUEST_STATE_KHONG_GHI_NHAN);
				HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(hiRequest);
			}
			
			return result;
		}
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
					.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
			
			if (documents != null && documents.size() > 0) {
				TempDocument document = documents.get(0);
				
				if (document.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
						|| document.getDocumentTypeCode().equals("5")
						|| document.getDocumentTypeCode().equals(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)
						|| document.getDocumentTypeCode().equals("9")
						|| document.getDocumentTypeCode().equals("11")
						|| document.getDocumentTypeCode().equals("13")) {
					
					if (document.getDocumentStatusCode() == TrangThaiHoSo.YEU_CAU_SUA_DOI_BO_SUNG
							|| document.getDocumentStatusCode() == TrangThaiHoSo.DA_TIEP_NHAP) {
						
						document.setDocumentStatusCode(TrangThaiHoSo.CHO_PHE_DUYET_HOAN_THANH_THU_TUC);
					}
					
					// --------
					if (document.getRequestState() == TrangThaiHoSo.TEMP_DOCUMENT_CHO_TIEP_NHAN
							|| document.getRequestState() == TrangThaiHoSo.KH_YEU_CAU_BO_SUNG) {
						
						document.setRequestState(TrangThaiHoSo.KE_HOACH_TIEP_NHAN);
						document.setDocumentStatusCode(TrangThaiHoSo.DA_TIEP_NHAP);
					}
					
					// huymq update 13/06/2018
					BusinessUtils.updateResultDeclaration(MessageType.THONG_BAO_TAU_QUA_CANH, document.getDocumentName(), document.getDocumentYear(),
							ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI);
					
					TempDocumentLocalServiceUtil.updateTempDocument(document);
					
				} else if (document.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
						|| document.getDocumentTypeCode().equals("4")
						|| document.getDocumentTypeCode().equals(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6)
						|| document.getDocumentTypeCode().equals("8")
						|| document.getDocumentTypeCode().equals("10")
						|| document.getDocumentTypeCode().equals("12")) {
					
					if (document.getDocumentStatusCode() == TrangThaiHoSo.DA_TIEP_NHAP
							|| document.getDocumentStatusCode() == TrangThaiHoSo.YEU_CAU_SUA_DOI_BO_SUNG) {
						
						document.setDocumentStatusCode(TrangThaiHoSo.CHO_PHE_DUYET_HOAN_THANH_THU_TUC);
						
					}
				
					if (document.getRequestState() == TrangThaiHoSo.TEMP_DOCUMENT_CHO_TIEP_NHAN
							|| document.getRequestState() == TrangThaiHoSo.KH_YEU_CAU_BO_SUNG) {
						
						document.setRequestState(TrangThaiHoSo.CHO_CAP_LENH_DIEU_DONG);
					}
					
					// huymq update 13/06/2018
					BusinessUtils.updateResultDeclaration(MessageType.THONG_BAO_TAU_DEN_CANG, document.getDocumentName(), document.getDocumentYear(),
					ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI);
					
					TempDocumentLocalServiceUtil.updateTempDocument(document);
				} // son_vh bo sung 20160119
					else if (document.getDocumentTypeCode().equals(MessageType.LOAT_THU_TUC_QUA_CANH)) {
					
					if (document.getDocumentStatusCode() == TrangThaiHoSo.DA_TIEP_NHAP
							|| document.getDocumentStatusCode() == TrangThaiHoSo.YEU_CAU_SUA_DOI_BO_SUNG) {
						
						document.setDocumentStatusCode(TrangThaiHoSo.CHO_PHE_DUYET_HOAN_THANH_THU_TUC);
						
					}
					
					if (document.getRequestState() == TrangThaiHoSo.TEMP_DOCUMENT_CHO_TIEP_NHAN
							|| document.getRequestState() == TrangThaiHoSo.KH_YEU_CAU_BO_SUNG) {
						
						document.setRequestState(TrangThaiHoSo.CHO_CAP_LENH_DIEU_DONG);
					}
					
					// huymq update 13/06/2018
					BusinessUtils.updateResultDeclaration(MessageType.THONG_BAO_TAU_QUA_CANH, document.getDocumentName(), document.getDocumentYear(),
							ChuyenDichTrangThaiUtils.BAN_KHAI_CHAP_NHAN_BAN_KHAI);
					
					TempDocumentLocalServiceUtil.updateTempDocument(document);
				}
				
			}
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
					.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
			
			if (documents != null && documents.size() > 0) {
				TempDocument temp = documents.get(0);
				if (temp.getDocumentStatusCode() == TrangThaiHoSo.DA_TIEP_NHAP) {
					temp.setDocumentStatusCode(TrangThaiHoSo.HUY_THU_TUC);
				}
				
				if (temp.getRequestState() == TrangThaiHoSo.TEMP_DOCUMENT_CHO_TIEP_NHAN) {
					temp.setRequestState(TrangThaiHoSo.KE_HOACH_TU_CHOI_TIEP_NHAN);
				}
				
				TempDocumentLocalServiceUtil.updateTempDocument(temp);
			}
			
			// 4, Gui ban tin sang HQMC:, Function 22 messagetype 99
			// sendChapNhan(objectExcute);
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_XAC_NHAN_HOAN_THANH_THU_TUC)) {
			
			long documentName = headerReceive.getSubject().getReference();
			int documentYear = headerReceive.getSubject().getDocumentYear();
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
					.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
			
			if (documents != null && documents.size() > 0) {
				TempDocument temp = documents.get(0);
				if (temp.getDocumentStatusCode() == TrangThaiHoSo.CHO_PHE_DUYET_HOAN_THANH_THU_TUC
						|| temp.getDocumentStatusCode() == TrangThaiHoSo.THU_TUC_TAM_DUNG_LAM_THU_TUC_DIEN_TU) {
					temp.setDocumentStatusCode(TrangThaiHoSo.PHE_DUYET_HOAN_THANH_THU_TUC);
				}
				updateOrInsertResultCompetion(temp, MessageType.DA_PHE_DUYET);
				TempDocumentLocalServiceUtil.updateTempDocument(temp);
				
				doiChieuBanKhaiChoPheDuyet(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN, documentName, documentYear);
				doiChieuBanKhaiChoPheDuyet(MessageType.BAN_KHAI_CHUNG, documentName, documentYear);
				doiChieuBanKhaiChoPheDuyet(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN, documentName, documentYear);
				doiChieuBanKhaiChoPheDuyet(MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM, documentName, documentYear);
				doiChieuBanKhaiChoPheDuyet(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH, documentName, documentYear);
			}  // son_vh bo sung BAN_KHAI_DANH_SACH_HANH_KHACH 20160119
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_XAC_NHAN_HUY_THU_TUC)) {
			
			result = xacNhanHuyThuTuc(objectExcute, headerReceive, result, xmlData);
			
		} else if (function.equalsIgnoreCase(String.valueOf(MessageType.FUNCTION_YEU_CAU_BO_SUNG_KE_HOACH))) {	// yc bo sung ke hoach
			
			long documentName = headerReceive.getSubject().getReference();
			int documentYear = headerReceive.getSubject().getDocumentYear();
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
					.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
			
			if (documents != null && documents.size() > 0) {
				TempDocument temp = documents.get(0);
				temp.setRequestState(TrangThaiHoSo.KH_YEU_CAU_BO_SUNG);
				TempDocumentLocalServiceUtil.updateTempDocument(temp);
				
				Modify modify = MessageFactory.convertXmltoModifyMessage(xmlData);
				
				BusinessUtils.insertOrUpdateResultMinistry((new Long(documentName)).intValue(), documentYear,
						MessageType.FUNCTION_THONG_BAO_BO_XUNG,
						BusinessUtils.getRemarkBoSung(modify.getName(), modify.getModifyDesc()));

				BusinessUtils.insertResultHistoryMinistry((new Long(documentName)).intValue(), documentYear,
						MessageType.FUNCTION_THONG_BAO_BO_XUNG,
						BusinessUtils.getRemarkBoSung(modify.getName(), modify.getModifyDesc()));

				// ResultNotification
				BanKhaiLenhDieuDongUtils.insertOrUpdateResultNotification(documentName, documentYear,
						modify.getName(), modify.getModifyDesc(), MessageType.BO_SUNG_KE_HOACH);
			}
			
		} else if (function.equalsIgnoreCase(String.valueOf(MessageType.FUNCTION_YEU_CAU_BO_SUNG_THU_TUC))) {	// yc bo sung thu tuc
			
			long documentName = headerReceive.getSubject().getReference();
			int documentYear = headerReceive.getSubject().getDocumentYear();
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
					.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
			
			if (documents != null && documents.size() > 0) {
				TempDocument temp = documents.get(0);
				temp.setDocumentStatusCode(TrangThaiHoSo.YEU_CAU_SUA_DOI_BO_SUNG);
				TempDocumentLocalServiceUtil.updateTempDocument(temp);
				
				RejectMessage rejectMessage = MessageFactory.convertXmltoRejectMessage(xmlData);
				
				BusinessUtils.insertOrUpdateResultMinistry((new Long(documentName)).intValue(), documentYear,
						MessageType.FUNCTION_THONG_BAO_BO_XUNG,
						BusinessUtils.getRemarkBoSung(rejectMessage.getName(), rejectMessage.getRejectDesc()));

				BusinessUtils.insertResultHistoryMinistry((new Long(documentName)).intValue(), documentYear,
						MessageType.FUNCTION_THONG_BAO_BO_XUNG,
						BusinessUtils.getRemarkBoSung(rejectMessage.getName(), rejectMessage.getRejectDesc()));

				// ResultNotification
				BanKhaiLenhDieuDongUtils.insertOrUpdateResultNotification(documentName, documentYear,
						rejectMessage.getName(), rejectMessage.getRejectDesc(), MessageType.BO_SUNG_KE_HOACH);
			}
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TAM_DUNG)) {
			
			List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
					.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
			
			if (documents != null && documents.size() > 0) {
				TempDocument document = documents.get(0);
				
				ChuyenDichTrangThaiUtils.doHoSo(document.getDocumentName(), document.getDocumentYear(), ChuyenDichTrangThaiUtils.THU_TUC_TAM_DUNG_LAM_THU_TUC_DIEN_TU, false);
			}
		}
		
		
		return result;
	}
	
	private boolean tichHop_ThongBao_TauRoiCang(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		log.debug("tichHop_ThongBao_TauRoiCang ");
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		log.debug("result " + result);
		// Trang thai request ban dau
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		log.debug("function " + function);
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			log.debug("MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU " + MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU);
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
					.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
			
			if (documents != null && documents.size() > 0) {
				TempDocument temp = documents.get(0);
				if (temp.getRequestState() == TrangThaiHoSo.TEMP_DOCUMENT_CHO_TIEP_NHAN) {
					
					TempDocumentLocalServiceUtil.updateTempDocument(temp);
				}
			}
			
			TempNoticeShipMessage tempTB = TempNoTiceShipMessageLocalServiceUtil.findTempNoTiceShipMessageTbByRequestCode(requestCodeOld);
			
			if (tempTB != null) {
				
				log.debug("(tempTB!=null ");
				tempTB.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempTB);
				
			} else {
				
				log.debug("(tempTB==null ");
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			
			try {
				AccepterMessage accepterMessage = MessageFactory.convertXmltoAccepterMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_CHAP_NHAN_HO_SO, accepterMessage.getName(), "");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			// 4, Gui ban tin sang HQMC: Function 21 messagetype 31
			// sendChapNhan(objectExcute);
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			log.debug("MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU " + MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU);
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			TempNoticeShipMessage tempTB = TempNoTiceShipMessageLocalServiceUtil.findTempNoTiceShipMessageTbByRequestCode(requestCodeOld);
			
			if (tempTB != null) {
				log.debug("(tempTB!=null ");
				tempTB.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempTB);
				
			} else {
				log.debug("(tempTB==null ");
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				RejectMessage rejectMessage = MessageFactory.convertXmltoRejectMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_TU_CHOI_HO_SO, rejectMessage.getName(),
						rejectMessage.getRejectDesc());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			// 4, Gui ban tin sang HQMC: Function 22 messagetype 31
			// sendChapNhan(objectExcute);
		}
		return result;
	}
	
	private boolean tichHop_LenhDieuDong(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		log.info("tichHop_LenhDieuDong");
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(xmlData);
		
		// Validation cang vu gui sang
		result = cangvuBussionessUtils.validationDataCangVuToHaiQuan(headerReceive, liObjects, BusinessUtils.CangVuToBoGiaoThong);
		
		// Trang thai request ban dau
		String requestCodeOld = headerReceive.getReference().getMessageId();
		log.info("requestCodeOld  " + requestCodeOld + " result  " + result);
		if (!result) {
			HistoryInterfaceRequest hiRequest = HistoryInterfaceRequestLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (hiRequest != null) {
				hiRequest.setRequestState(TrangThaiHoSo.HISTORY_REQUEST_STATE_KHONG_GHI_NHAN);
				HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(hiRequest);
			}
			return result;
		}
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_KHAI_BAO)) {
			log.info("MessageType.FUNCTION_KHAI_BAO  " + MessageType.FUNCTION_KHAI_BAO);
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
					.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
			
			if (documents != null && documents.size() > 0) {
				
				TempDocument document = documents.get(0);
				
				log.debug("document  " + MessageType.FUNCTION_KHAI_BAO);
				
				if (document.getRequestState() == TrangThaiHoSo.CHO_CAP_LENH_DIEU_DONG
						| document.getRequestState() == TrangThaiHoSo.TEMP_DOCUMENT_CHO_TIEP_NHAN) {
					
					document.setRequestState(TrangThaiHoSo.DA_CAP_LENH_DIEU_DONG);
					document.setDocumentStatusCode(TrangThaiHoSo.DA_TIEP_NHAP);
					document.setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
					
					TempDocumentLocalServiceUtil.updateTempDocument(document);
				}
				if (document.getRequestState() == TrangThaiHoSo.DA_CAP_LENH_DIEU_DONG){
					
					log.debug("document.getRequestState()  " + document.getRequestState());
					
					try {
						cangvuBussionessUtils.insertIssueTable(headerReceive, liObjects);
					} catch (Exception e) {
						log.error(e.getMessage());
					}
					
				}
			}
		}
		return result;
	}
	
	private boolean tichHop_GiayPhep_RoiCang(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(xmlData);
		
		result = cangvuBussionessUtils.validationDataCangVuToHaiQuan(headerReceive, liObjects, BusinessUtils.CangVuToBoGiaoThong);
		
		// Trang thai request ban dau
		String requestCodeOld = headerReceive.getReference().getMessageId();
		log.debug(" validationDataCangVuToHaiQuan  " + result + " requestCodeOld  " + requestCodeOld);
		if (!result) {
			HistoryInterfaceRequest hiRequest = HistoryInterfaceRequestLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (hiRequest != null) {
				hiRequest.setRequestState(TrangThaiHoSo.HISTORY_REQUEST_STATE_KHONG_GHI_NHAN);
				HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(hiRequest);
			}
			return result;
		}
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_KHAI_BAO)) {
			log.debug("MessageType.FUNCTION_KHAI_BAO  " + MessageType.FUNCTION_KHAI_BAO);
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
					.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
			
			if (documents != null && documents.size() > 0) {
				TempDocument document = documents.get(0);
				log.debug("document.getDocumentStatusCode()   " + document.getDocumentStatusCode());
				
				if (document.getDocumentStatusCode() == TrangThaiHoSo.CHO_PHE_DUYET_HOAN_THANH_THU_TUC
						|| document.getDocumentStatusCode() == TrangThaiHoSo.DA_TIEP_NHAP
						|| document.getDocumentStatusCode() == TrangThaiHoSo.YEU_CAU_SUA_DOI_BO_SUNG) {
					
					document.setDocumentStatusCode(TrangThaiHoSo.PHE_DUYET_HOAN_THANH_THU_TUC);
					TempDocumentLocalServiceUtil.updateTempDocument(document);
				}
				
				log.debug("document.getDocumentStatusCode()   " + document.getDocumentStatusCode());
				
				if (document.getDocumentStatusCode() == TrangThaiHoSo.PHE_DUYET_HOAN_THANH_THU_TUC
						|| document.getDocumentStatusCode() == TrangThaiHoSo.THU_TUC_DE_NGHI_CAP_GIAY_PHEP) {
					// insert IssuePortClearance
					try {
						cangvuBussionessUtils.insertIssueTable(headerReceive, liObjects);
					} catch (Exception e) {
						log.error(e.getMessage());
					}
					
				}
			}
		}
		
		return result;
	}
	
	private boolean huyLenhDieuDong(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		TempDocument temp = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(headerReceive.getSubject().getReference(),
				headerReceive.getSubject().getDocumentYear());
		
		temp.setRequestState(TrangThaiBanKhaiNhapCanh.DA_HUY_LENH_DIEU_DONG);
		temp.setDocumentStatusCode(TrangThaiBanKhaiNhapCanh.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH);
		TempDocumentLocalServiceUtil.updateTempDocument(temp);
		
		List<IssueShiftingOrder> lstShiftOrder = IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(headerReceive
				.getSubject().getReference(), headerReceive.getSubject().getDocumentYear(), KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
		for (IssueShiftingOrder item : lstShiftOrder) {
			item.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_HUY);
			IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(item);
		}
		try {
			
			Object obj = MessageFactory.convertXmltoObject(MessageType.HUY_LENH_DIEU_DONG, objectExcute.getXmlData());
			
			ResultNotification notification = ResultNotificationLocalServiceUtil.findByBusinessTypeCodeOrderbyLastestDate(
					MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG + "", headerReceive.getSubject().getReference(), headerReceive.getSubject()
							.getDocumentYear());
			if (notification == null) {
				notification = new ResultNotification();
				if (obj instanceof XacNhanHuyLenhDieuDong) {
					
					notification.setBusinessTypeCode(MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG);
					
					notification.setDocumentName(headerReceive.getSubject().getReference());
					notification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
					notification.setLatestDate(FormatData.parseStringToDate(headerReceive.getSubject().getSendDate()));
					notification.setRequestCode(headerReceive.getReference().getMessageId());
					if (temp != null) {
						notification.setMaritimeCode(temp.getMaritimeCode());
					}
					notification.setResponse(((XacNhanHuyLenhDieuDong) obj).getReason());
					
					notification.setResponseTime(FormatData.parseStringToDate(((XacNhanHuyLenhDieuDong) obj).getCancelDate()));
					notification.setOrganization(((XacNhanHuyLenhDieuDong) obj).getOrganization());
					notification.setOfficerName(((XacNhanHuyLenhDieuDong) obj).getName());
					notification.setDivision(((XacNhanHuyLenhDieuDong) obj).getDivision());
					notification.setRole(2);
					notification.setRequestState(new Integer(((XacNhanHuyLenhDieuDong) obj).getIsApprove()));
					notification.setIsReply(1);
					
				}
				
				ResultNotificationLocalServiceUtil.addResultNotification(notification);
			} else {
				if (obj instanceof XacNhanHuyLenhDieuDong) {
					
					notification.setBusinessTypeCode(MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG);
					
					notification.setDocumentName(headerReceive.getSubject().getReference());
					notification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
					if (temp != null) {
						notification.setMaritimeCode(temp.getMaritimeCode());
					}
					notification.setResponse(((XacNhanHuyLenhDieuDong) obj).getReason());
					notification.setResponseTime(FormatData.parseStringToDate(((XacNhanHuyLenhDieuDong) obj).getCancelDate()));
					notification.setOrganization(((XacNhanHuyLenhDieuDong) obj).getOrganization());
					notification.setOfficerName(((XacNhanHuyLenhDieuDong) obj).getName());
					notification.setDivision(((XacNhanHuyLenhDieuDong) obj).getDivision());
					notification.setRole(2);
					notification.setRequestState(new Integer(((XacNhanHuyLenhDieuDong) obj).getIsApprove()));
					notification.setIsReply(1);
					
				}
				ResultNotificationLocalServiceUtil.updateResultNotification(notification);
			}
			
		} catch (Exception e) {
			result = false;
			log.error(e.getMessage());
		}
		
		return result;
	}
	
	private boolean yCauLuuLaiGiayPhepRoiCang(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		try {
			TempDocument temp = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(headerReceive.getSubject().getReference(),
					headerReceive.getSubject().getDocumentYear());
			
			DeNghiCapLaiGiayPhep deNghiCapLaiGiayPhep = MessageFactory.convertXmltoDeNghiCapLaiGiayPhep(objectExcute.getXmlData());
			
			ResultNotification notification = ResultNotificationLocalServiceUtil.findByBusinessTypeCodeOrderbyLastestDate(
					MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG + "", headerReceive.getSubject().getReference(), headerReceive.getSubject()
							.getDocumentYear());
			if (notification == null) {
				notification = new ResultNotification();
				
				notification.setBusinessTypeCode(MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG);
				
				notification.setDocumentName(headerReceive.getSubject().getReference());
				notification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
				notification.setLatestDate(FormatData.parseStringToDate(headerReceive.getSubject().getSendDate()));
				notification.setRequestCode(headerReceive.getReference().getMessageId());
				if (temp != null) {
					notification.setMaritimeCode(temp.getMaritimeCode());
				}
				notification.setResponse(deNghiCapLaiGiayPhep.getReason());
				notification.setResponseTime(FormatData.parseStringToDate(deNghiCapLaiGiayPhep.getRenewDate()));
				notification.setOrganization(deNghiCapLaiGiayPhep.getOrganization());
				notification.setOfficerName(deNghiCapLaiGiayPhep.getName());
				notification.setDivision(deNghiCapLaiGiayPhep.getDivision());
				notification.setRole(4);
				notification.setRequestState(1);
				notification.setIsReply(1);
				
				ResultNotificationLocalServiceUtil.addResultNotification(notification);
			} else {
				
				notification.setBusinessTypeCode(MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG);
				
				notification.setDocumentName(headerReceive.getSubject().getReference());
				notification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
				notification.setRequestCode(headerReceive.getReference().getMessageId());
				if (temp != null) {
					notification.setMaritimeCode(temp.getMaritimeCode());
				}
				notification.setResponse(deNghiCapLaiGiayPhep.getReason());
				notification.setResponseTime(FormatData.parseStringToDate(deNghiCapLaiGiayPhep.getRenewDate()));
				notification.setOrganization(deNghiCapLaiGiayPhep.getOrganization());
				notification.setOfficerName(deNghiCapLaiGiayPhep.getName());
				notification.setDivision(deNghiCapLaiGiayPhep.getDivision());
				notification.setRole(4);
				notification.setRequestState(1);
				notification.setIsReply(1);
				
				ResultNotificationLocalServiceUtil.updateResultNotification(notification);
			}
			
		} catch (Exception e) {
			result = false;
			log.error(e.getMessage());
		}
		return result;
	}
	
	private boolean yCauLuuLaiGiayPhepVaoCang(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		try {
			TempDocument temp = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(headerReceive.getSubject().getReference(),
					headerReceive.getSubject().getDocumentYear());
			
			DeNghiCapLaiGiayPhep deNghiCapLaiGiayPhep = MessageFactory.convertXmltoDeNghiCapLaiGiayPhep(objectExcute.getXmlData());
			
			ResultNotification notification = ResultNotificationLocalServiceUtil.findByBusinessTypeCodeOrderbyLastestDate(
					MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG + "", headerReceive.getSubject().getReference(), headerReceive.getSubject()
							.getDocumentYear());
			if (notification == null) {
				notification = new ResultNotification();
				
				notification.setBusinessTypeCode(MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG);
				
				notification.setDocumentName(headerReceive.getSubject().getReference());
				notification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
				notification.setLatestDate(FormatData.parseStringToDate(headerReceive.getSubject().getSendDate()));
				notification.setRequestCode(headerReceive.getReference().getMessageId());
				if (temp != null) {
					notification.setMaritimeCode(temp.getMaritimeCode());
				}
				notification.setResponse(deNghiCapLaiGiayPhep.getReason());
				notification.setResponseTime(FormatData.parseStringToDate(deNghiCapLaiGiayPhep.getRenewDate()));
				notification.setOrganization(deNghiCapLaiGiayPhep.getOrganization());
				notification.setOfficerName(deNghiCapLaiGiayPhep.getName());
				notification.setDivision(deNghiCapLaiGiayPhep.getDivision());
				notification.setRole(4);
				notification.setRequestState(1);
				notification.setIsReply(1);
				
				ResultNotificationLocalServiceUtil.addResultNotification(notification);
			} else {
				
				notification.setBusinessTypeCode(MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG);
				
				notification.setDocumentName(headerReceive.getSubject().getReference());
				notification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
				notification.setRequestCode(headerReceive.getReference().getMessageId());
				if (temp != null) {
					notification.setMaritimeCode(temp.getMaritimeCode());
				}
				notification.setResponse(deNghiCapLaiGiayPhep.getReason());
				notification.setResponseTime(FormatData.parseStringToDate(deNghiCapLaiGiayPhep.getRenewDate()));
				notification.setOrganization(deNghiCapLaiGiayPhep.getOrganization());
				notification.setOfficerName(deNghiCapLaiGiayPhep.getName());
				notification.setDivision(deNghiCapLaiGiayPhep.getDivision());
				notification.setRole(4);
				notification.setRequestState(1);
				notification.setIsReply(1);
				
				ResultNotificationLocalServiceUtil.updateResultNotification(notification);
			}
			
		} catch (Exception e) {
			result = false;
			log.error(e.getMessage());
		}
		return result;
	}
	
	private boolean xacNhanHuyThuTuc(ObjectExcute objectExcute, Header headerReceive, boolean result, String xmlData) throws SystemException {
		BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
				.randomUUID().toString());
		
		List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive.getSubject()
				.getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
		
		if (documents != null && documents.size() > 0) {
			TempDocument temp = documents.get(0);
			
			if (temp.getDocumentStatusCode() == TrangThaiHoSo.CHO_PHE_DUYET_HOAN_THANH_THU_TUC
					|| temp.getDocumentStatusCode() == TrangThaiHoSo.YEU_CAU_SUA_DOI_BO_SUNG
					|| temp.getDocumentStatusCode() == TrangThaiHoSo.THU_TUC_TAM_DUNG_LAM_THU_TUC_DIEN_TU) {
				temp.setDocumentStatusCode(TrangThaiHoSo.HUY_THU_TUC);
			}
			updateOrInsertResultCompetion(temp, MessageType.TU_CHOI);
			TempDocumentLocalServiceUtil.updateTempDocument(temp);
		}
		try {
			TempDocument temp = documents.get(0);
			Object obj = MessageFactory.convertXmltoObject(MessageType.KHAI_HUY_HO_SO, objectExcute.getXmlData());
			
			ResultNotification reNotifi = ResultNotificationLocalServiceUtil.findByBusinessTypeCodeOrderbyLastestDate(
					MessageType.KHAI_HUY_HO_SO + "", headerReceive.getSubject().getReference(), headerReceive.getSubject().getDocumentYear());
			if (reNotifi == null) {
				reNotifi = new ResultNotification();
				if (obj instanceof XacNhanHuyThuTuc) {
					
					reNotifi.setBusinessTypeCode(MessageType.KHAI_HUY_HO_SO);
					
					reNotifi.setDocumentName(headerReceive.getSubject().getReference());
					reNotifi.setDocumentYear(headerReceive.getSubject().getDocumentYear());
					reNotifi.setLatestDate(FormatData.parseStringToDate(headerReceive.getSubject().getSendDate()));
					reNotifi.setRequestCode(headerReceive.getReference().getMessageId());
					if (temp != null) {
						reNotifi.setMaritimeCode(temp.getMaritimeCode());
					}
					reNotifi.setResponse(((XacNhanHuyThuTuc) obj).getReason());
					reNotifi.setResponseTime(FormatData.parseStringToDate(((XacNhanHuyThuTuc) obj).getCancelDate()));
					reNotifi.setOrganization(((XacNhanHuyThuTuc) obj).getOrganization());
					reNotifi.setOfficerName(((XacNhanHuyThuTuc) obj).getName());
					reNotifi.setDivision(((XacNhanHuyThuTuc) obj).getDivision());
					reNotifi.setRole(4);
					reNotifi.setRequestState(1);
					reNotifi.setIsReply(1);
					
				}
				ResultNotificationLocalServiceUtil.addResultNotification(reNotifi);
			} else {
				if (obj instanceof XacNhanHuyThuTuc) {
					
					reNotifi.setBusinessTypeCode(MessageType.KHAI_HUY_HO_SO);
					
					reNotifi.setDocumentName(headerReceive.getSubject().getReference());
					reNotifi.setDocumentYear(headerReceive.getSubject().getDocumentYear());
					reNotifi.setRequestCode(headerReceive.getReference().getMessageId());
					if (temp != null) {
						reNotifi.setMaritimeCode(temp.getMaritimeCode());
					}
					reNotifi.setResponse(((XacNhanHuyThuTuc) obj).getReason());
					reNotifi.setResponseTime(FormatData.parseStringToDate(((XacNhanHuyThuTuc) obj).getCancelDate()));
					reNotifi.setOrganization(((XacNhanHuyThuTuc) obj).getOrganization());
					reNotifi.setOfficerName(((XacNhanHuyThuTuc) obj).getName());
					reNotifi.setDivision(((XacNhanHuyThuTuc) obj).getDivision());
					reNotifi.setRole(4);
					reNotifi.setRequestState(1);
					reNotifi.setIsReply(1);
					
				}
				ResultNotificationLocalServiceUtil.updateResultNotification(reNotifi);
			}
			
		} catch (Exception e) {
			result = false;
			log.error(e.getMessage());
		}
		return result;
	}
	
	
	private boolean huyGiayPhepRoiCang(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		TempDocument temp = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(headerReceive.getSubject().getReference(),
				headerReceive.getSubject().getDocumentYear());
		
		temp.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
		TempDocumentLocalServiceUtil.updateTempDocument(temp);
		
		List<IssuePortClearance> issuePortClearance = IssuePortClearanceLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(headerReceive
				.getSubject().getReference(), headerReceive.getSubject().getDocumentYear(), KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
		for (IssuePortClearance item : issuePortClearance) {
			item.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_HUY);
			IssuePortClearanceLocalServiceUtil.updateIssuePortClearance(item);
		}
		try {
			
			Object obj = MessageFactory.convertXmltoObject(MessageType.HUY_GIAY_PHEP_ROI_CANG, objectExcute.getXmlData());
			
			ResultNotification reNotification = ResultNotificationLocalServiceUtil.findByBusinessTypeCodeOrderbyLastestDate(
					MessageType.HUY_GIAY_PHEP_ROI_CANG + "", headerReceive.getSubject().getReference(), headerReceive.getSubject().getDocumentYear());
			if (reNotification == null) {
				reNotification = new ResultNotification();
				if (obj instanceof XacNhanHuyThuTuc) {
					
					reNotification.setBusinessTypeCode(MessageType.HUY_GIAY_PHEP_ROI_CANG);
					
					reNotification.setDocumentName(headerReceive.getSubject().getReference());
					reNotification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
					reNotification.setLatestDate(FormatData.parseStringToDate(headerReceive.getSubject().getSendDate()));
					reNotification.setRequestCode(headerReceive.getReference().getMessageId());
					if (temp != null) {
						reNotification.setMaritimeCode(temp.getMaritimeCode());
					}
					reNotification.setResponse(((XacNhanHuyThuTuc) obj).getReason());
					
					reNotification.setResponseTime(FormatData.parseStringToDate(((XacNhanHuyThuTuc) obj).getCancelDate()));
					reNotification.setOrganization(((XacNhanHuyThuTuc) obj).getOrganization());
					reNotification.setOfficerName(((XacNhanHuyThuTuc) obj).getName());
					reNotification.setDivision(((XacNhanHuyThuTuc) obj).getDivision());
					reNotification.setRole(4);
					reNotification.setRequestState(new Integer(((XacNhanHuyThuTuc) obj).getIsApprove()));
					reNotification.setIsReply(1);
					
				}
				ResultNotificationLocalServiceUtil.addResultNotification(reNotification);
			} else {
				if (obj instanceof XacNhanHuyThuTuc) {
					
					reNotification.setBusinessTypeCode(MessageType.HUY_GIAY_PHEP_ROI_CANG);
					
					reNotification.setDocumentName(headerReceive.getSubject().getReference());
					reNotification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
					if (temp != null) {
						reNotification.setMaritimeCode(temp.getMaritimeCode());
					}
					reNotification.setResponse(((XacNhanHuyThuTuc) obj).getReason());
					
					reNotification.setResponseTime(FormatData.parseStringToDate(((XacNhanHuyThuTuc) obj).getCancelDate()));
					reNotification.setOrganization(((XacNhanHuyThuTuc) obj).getOrganization());
					reNotification.setOfficerName(((XacNhanHuyThuTuc) obj).getName());
					reNotification.setDivision(((XacNhanHuyThuTuc) obj).getDivision());
					reNotification.setRole(4);
					reNotification.setRequestState(new Integer(((XacNhanHuyThuTuc) obj).getIsApprove()));
					reNotification.setIsReply(1);
					
				}
				ResultNotificationLocalServiceUtil.updateResultNotification(reNotification);
			}
			
		} catch (Exception e) {
			result = false;
			log.error(e.getMessage());
		}
		
		return result;
	}
	
	private boolean huyGiayPhepVaoCang(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		TempDocument temp = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(headerReceive.getSubject().getReference(),
				headerReceive.getSubject().getDocumentYear());
		
		temp.setDocumentStatusCode(TrangThaiBanKhaiXuatCanh.HUY_THU_TUC_TAU_THUYEN_XUAT_CANH);
		TempDocumentLocalServiceUtil.updateTempDocument(temp);
		
		List<IssuePortClearance> issuePortClearance = IssuePortClearanceLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(headerReceive
				.getSubject().getReference(), headerReceive.getSubject().getDocumentYear(), KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
		for (IssuePortClearance item : issuePortClearance) {
			item.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_HUY);
			IssuePortClearanceLocalServiceUtil.updateIssuePortClearance(item);
		}
		try {
			
			Object obj = MessageFactory.convertXmltoObject(MessageType.HUY_GIAY_PHEP_VAO_CANG, objectExcute.getXmlData());
			
			ResultNotification reNotification = ResultNotificationLocalServiceUtil.findByBusinessTypeCodeOrderbyLastestDate(
					MessageType.HUY_GIAY_PHEP_VAO_CANG + "", headerReceive.getSubject().getReference(), headerReceive.getSubject().getDocumentYear());
			if (reNotification == null) {
				reNotification = new ResultNotification();
				if (obj instanceof XacNhanHuyThuTuc) {
					
					reNotification.setBusinessTypeCode(MessageType.HUY_GIAY_PHEP_VAO_CANG);
					
					reNotification.setDocumentName(headerReceive.getSubject().getReference());
					reNotification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
					reNotification.setLatestDate(FormatData.parseStringToDate(headerReceive.getSubject().getSendDate()));
					reNotification.setRequestCode(headerReceive.getReference().getMessageId());
					if (temp != null) {
						reNotification.setMaritimeCode(temp.getMaritimeCode());
					}
					reNotification.setResponse(((XacNhanHuyThuTuc) obj).getReason());
					
					reNotification.setResponseTime(FormatData.parseStringToDate(((XacNhanHuyThuTuc) obj).getCancelDate()));
					reNotification.setOrganization(((XacNhanHuyThuTuc) obj).getOrganization());
					reNotification.setOfficerName(((XacNhanHuyThuTuc) obj).getName());
					reNotification.setDivision(((XacNhanHuyThuTuc) obj).getDivision());
					reNotification.setRole(4);
					reNotification.setRequestState(new Integer(((XacNhanHuyThuTuc) obj).getIsApprove()));
					reNotification.setIsReply(1);
					
				}
				ResultNotificationLocalServiceUtil.addResultNotification(reNotification);
			} else {
				if (obj instanceof XacNhanHuyThuTuc) {
					
					reNotification.setBusinessTypeCode(MessageType.HUY_GIAY_PHEP_VAO_CANG);
					
					reNotification.setDocumentName(headerReceive.getSubject().getReference());
					reNotification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
					if (temp != null) {
						reNotification.setMaritimeCode(temp.getMaritimeCode());
					}
					reNotification.setResponse(((XacNhanHuyThuTuc) obj).getReason());
					
					reNotification.setResponseTime(FormatData.parseStringToDate(((XacNhanHuyThuTuc) obj).getCancelDate()));
					reNotification.setOrganization(((XacNhanHuyThuTuc) obj).getOrganization());
					reNotification.setOfficerName(((XacNhanHuyThuTuc) obj).getName());
					reNotification.setDivision(((XacNhanHuyThuTuc) obj).getDivision());
					reNotification.setRole(4);
					reNotification.setRequestState(new Integer(((XacNhanHuyThuTuc) obj).getIsApprove()));
					reNotification.setIsReply(1);
					
				}
				ResultNotificationLocalServiceUtil.updateResultNotification(reNotification);
			}
			
		} catch (Exception e) {
			result = false;
			log.error(e.getMessage());
		}
		
		return result;
	}
	
	
	public void updateOrInsertInterfaceRequest(String requestCodeOld, int responseStatus, String approvalName, String reason) {
		try {
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findInterfaceRequestByRequestCode(requestCodeOld);
			if (interfaceRequest != null) {
				// String approvalName = "Can Bo Cang Vu";
				
				if (responseStatus == MessageType.FUNCTION_CHAP_NHAN_HO_SO) {
					interfaceRequest.setRemarks(BusinessUtils.getRemarkChapNhan(approvalName));
				} else {
					interfaceRequest.setRemarks(BusinessUtils.getRemarkTuChoi(approvalName, reason));
				}
				InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public void updateOrInsertResultCompetion(TempDocument temp, int responseStatus) {
		
		ResultCompletion reCompetion = null;
		reCompetion = ResultCompetionLocalServiceUtil.findByDocumentNameAndDocumentYear(temp.getDocumentName(), temp.getDocumentYear());
		
		String maritimeCode = temp.getMaritimeCode();
		String nameOfShip = temp.getShipName();
		String nameOfMaster = temp.getShipCaptain();
		String portOfArrivalCode = temp.getPortCode();
		String certificateNo = "";
		String flagStateOfShip = temp.getStateCode();
		double grossTonnage = temp.getGrt();
		Date approvalTime = new Date();
		String approvalName = "Can Bo Cang Vu";
		
		try {
			
			if (reCompetion == null) {
				
				log.debug("========vao insert====xacNhanHoanThanhThuTucNhapCanhTruongHopHuy=====");
				reCompetion = new ResultCompletion();
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo);
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);
				
				reCompetion.setRequestState(TrangThaiHoSo.KE_HOACH_TIEP_NHAN);
				reCompetion.setResponseStatusCVHH(responseStatus);
				reCompetion.setResponseTimeCVHH(new Date());
				if (responseStatus == MessageType.DA_PHE_DUYET) {
					reCompetion.setResponseCVHH(BusinessUtils.getRemarkChapNhan(approvalName));
				} else {
					reCompetion.setResponseCVHH(BusinessUtils.getRemarkTuChoi(approvalName, "Tu choi tu cang vu"));
				}
				
				reCompetion.setDocumentName(temp.getDocumentName());
				reCompetion.setDocumentYear(temp.getDocumentYear());
				reCompetion.setRequestCode(UUID.randomUUID().toString());
				
				ResultCompetionLocalServiceUtil.addResultCompetion(reCompetion);
			} else {
				log.debug("xacNhanHoanThanhThuTucNhapCanhTruongHopHuy vao update");
				reCompetion.setMaritimeCode(maritimeCode);
				reCompetion.setNameOfShip(nameOfShip);
				reCompetion.setNameOfMaster(nameOfMaster);
				reCompetion.setPortOfArrivalCode(portOfArrivalCode);
				reCompetion.setCertificateNo(certificateNo);
				reCompetion.setFlagStateOfShip(flagStateOfShip);
				reCompetion.setGrossTonnage(BigDecimal.valueOf(grossTonnage));
				reCompetion.setApprovalTime(approvalTime);
				reCompetion.setApprovalName(approvalName);
				
				reCompetion.setRequestState(TrangThaiHoSo.KE_HOACH_TIEP_NHAN);
				reCompetion.setResponseStatusCVHH(responseStatus);
				reCompetion.setResponseTimeCVHH(new Date());
				if (responseStatus == MessageType.DA_PHE_DUYET) {
					reCompetion.setResponseCVHH(BusinessUtils.getRemarkChapNhan(approvalName));
				} else {
					reCompetion.setResponseCVHH(BusinessUtils.getRemarkTuChoi(approvalName, "Tu choi tu cang vu"));
				}
				
				ResultCompetionLocalServiceUtil.updateResultCompetion(reCompetion);
				log.debug("UPDATE XONG setRequestState" + reCompetion.getRequestState());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	public void doiChieuBanKhaiChoPheDuyet(int messageType, long documentName, int documentYear) throws SystemException {
		
		List<ResultDeclaration> resultList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(messageType,
				documentName, documentYear);
		
		String requestCode = "";
		
		if (Validator.isNotNull(resultList) && resultList.size() > 0) {
			ResultDeclaration obj = resultList.get(0);
			obj.setRequestState(MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
			ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
		}
		
		if (resultList.size() > 0) {
			requestCode = resultList.get(0).getRequestCode();
		}
		
		
		if (messageType == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
			List<TempShipSecurityMessage> tempSSMs = TempShipSecurityMessageLocalServiceUtil.findByRequestCode(requestCode);
			
			if (tempSSMs != null && tempSSMs.size() > 0) {
				TempShipSecurityMessage message = tempSSMs.get(0);
				message.setRequestState(MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				TempShipSecurityMessageLocalServiceUtil.updateTempShipSecurityMessage(message);
			}
			
		} else if (messageType == MessageType.BAN_KHAI_CHUNG) {
			List<TempGeneralDeclaration> tempGDs = TempGeneralDeclarationLocalServiceUtil.findByRequestCode(requestCode);
			
			if (tempGDs != null && tempGDs.size() > 0) {
				TempGeneralDeclaration declaration = tempGDs.get(0);
				declaration.setRequestState(MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				TempGeneralDeclarationLocalServiceUtil.updateTempGeneralDeclaration(declaration);
			}
			
		} else if (messageType == MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN) {
			List<TempCrewList> crews = TempCrewListLocalServiceUtil.findByRequestCode(requestCode);
			
			if (crews != null && crews.size() > 0) {
				TempCrewList crew = crews.get(0);
				crew.setRequestState(MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				TempCrewListLocalServiceUtil.updateTempCrewList(crew);
			}
			// son_vh them BAN_KHAI_DANH_SACH_HANH_KHACH
		}else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH) {
			List<TempPassengerList> Passengers = TempPassengerListLocalServiceUtil.findByRequestCode(requestCode);
			
			if (Passengers != null && Passengers.size() > 0) {
				TempPassengerList passenger = Passengers.get(0);
				passenger.setRequestState(MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				TempPassengerListLocalServiceUtil.updateTempPassengerList(passenger);
			}
		}
		else if (messageType == MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM) {   // son_vh sua cu la BAN_KHAI_HANG_HOA
			List<TempDangerousGoodsManifest> results = TempDangerousGoodsNanifestLocalServiceUtil.findByRequestCode(requestCode);
			
			if (results != null && results.size() > 0) {
				TempDangerousGoodsManifest goodsNanifest = results.get(0);
				goodsNanifest.setRequestState(MessageType.TRANG_THAI_BANG_KHAI_DOI_CHIEU);
				TempDangerousGoodsNanifestLocalServiceUtil.updateTempDangerousGoodsNanifest(goodsNanifest);
			}
		}
	}
	
	public String sendShiftingOrder(Header header, ShiftingOrder shiftingOrder, BusinessUtils businessUtils) {
		
		String xmlResult = "";
		try {
			
			if (shiftingOrder.getPortofAuthority() != null && shiftingOrder.getPortofAuthority().length() > 0) {
				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(shiftingOrder.getPortofAuthority());
				if (dmMaritime != null) {
					shiftingOrder.setPortofAuthority(dmMaritime.getMaritimeReference());
				}
			}
			// Sua lai truoc khi gui di
			// shiftingOrder.setShiftingPortWharfCode("XXXXX");
			xmlResult = MessageFactory.convertObjectToXml(shiftingOrder);
			
			// Insert history.
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return xmlResult;
	}
	
	private boolean tichHop_ThongBao_TauQuaCanh(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		// Trang thai request ban dau
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			TempNoticeShipMessage tempTBs = TempNoTiceShipMessageLocalServiceUtil.findTempNoTiceShipMessageTbByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(tempTBs)) {
				tempTBs.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempTBs);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			
			try {
				AccepterMessage accepterMessage = MessageFactory.convertXmltoAccepterMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_CHAP_NHAN_HO_SO, accepterMessage.getName(), "");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			TempNoticeShipMessage tempTB = TempNoTiceShipMessageLocalServiceUtil.findTempNoTiceShipMessageTbByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(tempTB)) {
				tempTB.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempTB);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				RejectMessage rejectMessage = MessageFactory.convertXmltoRejectMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_TU_CHOI_HO_SO, rejectMessage.getName(),
						rejectMessage.getRejectDesc());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			// Gui ban tin sang HQMC: Neu He thong KHoNG tu choi, ma do Can bo
			// tu choi thi gui HQMC.
			// Function 22 messagetype 10
			// sendChapNhan(objectExcute);
		}
		return result;
	}
	
	private boolean tichHop_XacBao_TauQuaCanh(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(xmlData);
		
		result = businessUtils.validationData(headerReceive, liObjects, BusinessUtils.CangVuToBoGiaoThong);
		
		// Trang thai request ban dau
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		if (!result) {
			HistoryInterfaceRequest hiRequest = HistoryInterfaceRequestLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (hiRequest != null) {
				hiRequest.setRequestState(TrangThaiHoSo.HISTORY_REQUEST_STATE_KHONG_GHI_NHAN);
				HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(hiRequest);
			}
			return result;
		}
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			TempNoticeShipMessage tempXB = TempNoTiceShipMessageLocalServiceUtil.findTempNoTiceShipMessageXbByRequestCode(requestCodeOld);
			if (Validator.isNotNull(tempXB)) {
				tempXB.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempXB);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			TempNoticeShipMessage tempXB = TempNoTiceShipMessageLocalServiceUtil.findTempNoTiceShipMessageXbByRequestCode(requestCodeOld);
			if (Validator.isNotNull(tempXB)) {
				tempXB.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				TempNoTiceShipMessageLocalServiceUtil.updateTempNoTiceShipMessage(tempXB);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			
		}
		return result;
	}
	
	private boolean tichHop_GiayPhep_QuaCanh(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
			
			boolean result = true;
			
			String function = headerReceive.getSubject().getFunction();
			
			String xmlData = objectExcute.getXmlData();
			
			List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(xmlData);
			
			result = cangvuBussionessUtils.validationDataCangVuToHaiQuan(headerReceive, liObjects, BusinessUtils.CangVuToBoGiaoThong);
			
			// Trang thai request ban dau
			String requestCodeOld = headerReceive.getReference().getMessageId();
			log.debug(" validationDataCangVuToHaiQuan  " + result + " requestCodeOld  " + requestCodeOld);
			if (!result) {
				HistoryInterfaceRequest hiRequest = HistoryInterfaceRequestLocalServiceUtil.findByRequestCode(requestCodeOld);
				
				if (hiRequest != null) {
					hiRequest.setRequestState(TrangThaiHoSo.HISTORY_REQUEST_STATE_KHONG_GHI_NHAN);
					HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(hiRequest);
				}
				return result;
			}
			
			if (function.equalsIgnoreCase(MessageType.FUNCTION_KHAI_BAO)) {
				log.debug("MessageType.FUNCTION_KHAI_BAO  " + MessageType.FUNCTION_KHAI_BAO);
				BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
						.randomUUID().toString());
				
				List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
						.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
				
				if (documents != null && documents.size() > 0) {
					TempDocument document = documents.get(0);
					log.debug("document.getDocumentStatusCode()   " + document.getDocumentStatusCode());
					
					if (document.getDocumentStatusCode() == TrangThaiHoSo.CHO_PHE_DUYET_HOAN_THANH_THU_TUC
							|| document.getDocumentStatusCode() == TrangThaiHoSo.DA_TIEP_NHAP
									|| document.getDocumentStatusCode() == TrangThaiHoSo.YEU_CAU_SUA_DOI_BO_SUNG) {
						
						document.setDocumentStatusCode(TrangThaiHoSo.PHE_DUYET_HOAN_THANH_THU_TUC);
						TempDocumentLocalServiceUtil.updateTempDocument(document);
					}
					
					log.debug("document.getDocumentStatusCode()   " + document.getDocumentStatusCode());
					
					if (document.getDocumentStatusCode() == TrangThaiHoSo.PHE_DUYET_HOAN_THANH_THU_TUC) {
						// insert IssuePermissionForTransit
						try {
							cangvuBussionessUtils.insertIssueTable(headerReceive, liObjects);
						} catch (Exception e) {
							log.error(e.getMessage());
						}
						
					}
				}
			}
			
			return result;
		}
	
	
	private boolean yCauLuuLaiGiayPhepQuaCanh(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		try {
			TempDocument temp = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(headerReceive.getSubject().getReference(),
					headerReceive.getSubject().getDocumentYear());
			
			DeNghiCapLaiGiayPhep deNghiCapLaiGiayPhep = MessageFactory.convertXmltoDeNghiCapLaiGiayPhep(objectExcute.getXmlData());
			
			ResultNotification notification = ResultNotificationLocalServiceUtil.findByBusinessTypeCodeOrderbyLastestDate(
					MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH + "", headerReceive.getSubject().getReference(), headerReceive.getSubject()
							.getDocumentYear());
			if (notification == null) {
				notification = new ResultNotification();
				
				notification.setBusinessTypeCode(MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH);
				
				notification.setDocumentName(headerReceive.getSubject().getReference());
				notification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
				notification.setLatestDate(FormatData.parseStringToDate(headerReceive.getSubject().getSendDate()));
				notification.setRequestCode(headerReceive.getReference().getMessageId());
				if (temp != null) {
					notification.setMaritimeCode(temp.getMaritimeCode());
				}
				notification.setResponse(deNghiCapLaiGiayPhep.getReason());
				notification.setResponseTime(FormatData.parseStringToDate(deNghiCapLaiGiayPhep.getRenewDate()));
				notification.setOrganization(deNghiCapLaiGiayPhep.getOrganization());
				notification.setOfficerName(deNghiCapLaiGiayPhep.getName());
				notification.setDivision(deNghiCapLaiGiayPhep.getDivision());
				notification.setRole(4);
				notification.setRequestState(1);
				notification.setIsReply(1);
				
				ResultNotificationLocalServiceUtil.addResultNotification(notification);
			} else {
				
				notification.setBusinessTypeCode(MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH);
				
				notification.setDocumentName(headerReceive.getSubject().getReference());
				notification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
				notification.setRequestCode(headerReceive.getReference().getMessageId());
				if (temp != null) {
					notification.setMaritimeCode(temp.getMaritimeCode());
				}
				notification.setResponse(deNghiCapLaiGiayPhep.getReason());
				notification.setResponseTime(FormatData.parseStringToDate(deNghiCapLaiGiayPhep.getRenewDate()));
				notification.setOrganization(deNghiCapLaiGiayPhep.getOrganization());
				notification.setOfficerName(deNghiCapLaiGiayPhep.getName());
				notification.setDivision(deNghiCapLaiGiayPhep.getDivision());
				notification.setRole(4);
				notification.setRequestState(1);
				notification.setIsReply(1);
				
				ResultNotificationLocalServiceUtil.updateResultNotification(notification);
			}
			
		} catch (Exception e) {
			result = false;
			log.error(e.getMessage());
		}
		return result;
	 }
	
	private boolean huyGiayPhepQuaCanh(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		TempDocument temp = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(headerReceive.getSubject().getReference(),
				headerReceive.getSubject().getDocumentYear());
		
		temp.setDocumentStatusCode(TrangThaiBanKhaiQuaCanh.HUY_THU_TUC_TAU_THUYEN_QUA_CANH);
		TempDocumentLocalServiceUtil.updateTempDocument(temp);
		
		List<IssuePermissionForTransit> issuePermissionForTransit = IssuePermissionForTransitLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(headerReceive
				.getSubject().getReference(), headerReceive.getSubject().getDocumentYear(), KeyParams.VERSION_NO, KeyParams.ORDER_BY_DESC);
		for (IssuePermissionForTransit item : issuePermissionForTransit) {
			item.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_HUY);
			IssuePermissionForTransitLocalServiceUtil.updateIssuePermissionForTransit(item);
		}
		try {
			
			Object obj = MessageFactory.convertXmltoObject(MessageType.HUY_GIAY_PHEP_QUA_CANH, objectExcute.getXmlData());
			
			ResultNotification reNotification = ResultNotificationLocalServiceUtil.findByBusinessTypeCodeOrderbyLastestDate(
					MessageType.HUY_GIAY_PHEP_QUA_CANH + "", headerReceive.getSubject().getReference(), headerReceive.getSubject().getDocumentYear());
			if (reNotification == null) {
				reNotification = new ResultNotification();
				if (obj instanceof XacNhanHuyThuTuc) {
					
					reNotification.setBusinessTypeCode(MessageType.HUY_GIAY_PHEP_QUA_CANH);
					
					reNotification.setDocumentName(headerReceive.getSubject().getReference());
					reNotification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
					reNotification.setLatestDate(FormatData.parseStringToDate(headerReceive.getSubject().getSendDate()));
					reNotification.setRequestCode(headerReceive.getReference().getMessageId());
					if (temp != null) {
						reNotification.setMaritimeCode(temp.getMaritimeCode());
					}
					reNotification.setResponse(((XacNhanHuyThuTuc) obj).getReason());
					
					reNotification.setResponseTime(FormatData.parseStringToDate(((XacNhanHuyThuTuc) obj).getCancelDate()));
					reNotification.setOrganization(((XacNhanHuyThuTuc) obj).getOrganization());
					reNotification.setOfficerName(((XacNhanHuyThuTuc) obj).getName());
					reNotification.setDivision(((XacNhanHuyThuTuc) obj).getDivision());
					reNotification.setRole(4);
					reNotification.setRequestState(new Integer(((XacNhanHuyThuTuc) obj).getIsApprove()));
					reNotification.setIsReply(1);
					
				}
				ResultNotificationLocalServiceUtil.addResultNotification(reNotification);
			} else {
				if (obj instanceof XacNhanHuyThuTuc) {
					
					reNotification.setBusinessTypeCode(MessageType.HUY_GIAY_PHEP_QUA_CANH);
					
					reNotification.setDocumentName(headerReceive.getSubject().getReference());
					reNotification.setDocumentYear(headerReceive.getSubject().getDocumentYear());
					if (temp != null) {
						reNotification.setMaritimeCode(temp.getMaritimeCode());
					}
					reNotification.setResponse(((XacNhanHuyThuTuc) obj).getReason());
					
					reNotification.setResponseTime(FormatData.parseStringToDate(((XacNhanHuyThuTuc) obj).getCancelDate()));
					reNotification.setOrganization(((XacNhanHuyThuTuc) obj).getOrganization());
					reNotification.setOfficerName(((XacNhanHuyThuTuc) obj).getName());
					reNotification.setDivision(((XacNhanHuyThuTuc) obj).getDivision());
					reNotification.setRole(4);
					reNotification.setRequestState(new Integer(((XacNhanHuyThuTuc) obj).getIsApprove()));
					reNotification.setIsReply(1);
					
				}
				ResultNotificationLocalServiceUtil.updateResultNotification(reNotification);
			}
			
		} catch (Exception e) {
			result = false;
			log.error(e.getMessage());
		}
		
		return result;
	}
	
	
	//
	
	private boolean tichHop_KetQua_XemXetChungTuDinhKem(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		try{
			
		} catch (Exception e) {
			result = false;
			log.error(e.getMessage());
		}
	
	
		return result;
		}
		
	
	private boolean tichHop_ThongBao_ChamPheDuyetHTTT(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		try{
			
		} catch (Exception e) {
			result = false;
			log.error(e.getMessage());
		}
	
	
		return result;
		}
	
	
	
	
	
	
	private boolean tichHop_BAN_KHAI_CHUNG(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempGeneralDeclaration> temps = TempGeneralDeclarationLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(temps) && temps.size() > 0) {
				
				TempGeneralDeclaration temp = temps.get(0);
				temp.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				TempGeneralDeclarationLocalServiceUtil.updateTempGeneralDeclaration(temp);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				AccepterMessage accepterMessage = MessageFactory.convertXmltoAccepterMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_CHAP_NHAN_HO_SO, accepterMessage.getName(), "");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			// Gui ban tin sang HQMC: Function 21 messagetype 10
			// sendChapNhan(objectExcute);
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempGeneralDeclaration> temps = TempGeneralDeclarationLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(temps) && temps.size() > 0) {
				TempGeneralDeclaration temp = temps.get(0);
				temp.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				TempGeneralDeclarationLocalServiceUtil.updateTempGeneralDeclaration(temp);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				RejectMessage rejectMessage = MessageFactory.convertXmltoRejectMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_TU_CHOI_HO_SO, rejectMessage.getName(),
						rejectMessage.getRejectDesc());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
		}
		
		return result;
	}
	
	private boolean tichHop_BAN_KHAI_DANH_SACH_THUYEN_VIEN(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempCrewList> temps = TempCrewListLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(temps) && temps.size() > 0) {
				
				TempCrewList temp = temps.get(0);
				temp.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				TempCrewListLocalServiceUtil.updateTempCrewList(temp);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				AccepterMessage accepterMessage = MessageFactory.convertXmltoAccepterMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_CHAP_NHAN_HO_SO, accepterMessage.getName(), "");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			// Gui ban tin sang HQMC: Function 21 messagetype 10
			// sendChapNhan(objectExcute);
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempCrewList> temps = TempCrewListLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(temps) && temps.size() > 0) {
				TempCrewList temp = temps.get(0);
				temp.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				TempCrewListLocalServiceUtil.updateTempCrewList(temp);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				RejectMessage rejectMessage = MessageFactory.convertXmltoRejectMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_TU_CHOI_HO_SO, rejectMessage.getName(),
						rejectMessage.getRejectDesc());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
		}
		
		return result;
	}
	
	private boolean tichHop_BAN_KHAI_DANH_SACH_HANH_KHACH(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempPassengerList> temps = TempPassengerListLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(temps) && temps.size() > 0) {
				
				TempPassengerList temp = temps.get(0);
				temp.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				TempPassengerListLocalServiceUtil.updateTempPassengerList(temp);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				AccepterMessage accepterMessage = MessageFactory.convertXmltoAccepterMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_CHAP_NHAN_HO_SO, accepterMessage.getName(), "");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			// Gui ban tin sang HQMC: Function 21 messagetype 10
			// sendChapNhan(objectExcute);
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempPassengerList> temps = TempPassengerListLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(temps) && temps.size() > 0) {
				TempPassengerList temp = temps.get(0);
				temp.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				TempPassengerListLocalServiceUtil.updateTempPassengerList(temp);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				RejectMessage rejectMessage = MessageFactory.convertXmltoRejectMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_TU_CHOI_HO_SO, rejectMessage.getName(),
						rejectMessage.getRejectDesc());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			// 4, Gui ban tin sang HQMC: Neu He thong KHoNG tu choi, ma do Can bo tu choi thi gui HQMC.
			// Function 22 messagetype 10 sendChapNhan(objectExcute);
		}
		
		return result;
	}
	
	private boolean tichHop_BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		String requestCodeOld = headerReceive.getReference().getMessageId();
		
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_CHAP_NHAN_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempDangerousGoodsManifest> temps = TempDangerousGoodsNanifestLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(temps) && temps.size() > 0) {
				
				TempDangerousGoodsManifest temp = temps.get(0);
				temp.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				TempDangerousGoodsNanifestLocalServiceUtil.updateTempDangerousGoodsNanifest(temp);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.CHAP_NHAN_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				AccepterMessage accepterMessage = MessageFactory.convertXmltoAccepterMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_CHAP_NHAN_HO_SO, accepterMessage.getName(), "");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			// Gui ban tin sang HQMC: Function 21 messagetype 10
			// sendChapNhan(objectExcute);
			
		} else if (function.equalsIgnoreCase(MessageType.FUNCTION_TU_CHOI_HO_SO_CHUNG_TU)) {
			
			BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
					.randomUUID().toString());
			
			List<TempDangerousGoodsManifest> temps = TempDangerousGoodsNanifestLocalServiceUtil.findByRequestCode(requestCodeOld);
			
			if (Validator.isNotNull(temps) && temps.size() > 0) {
				TempDangerousGoodsManifest temp = temps.get(0);
				temp.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				TempDangerousGoodsNanifestLocalServiceUtil.updateTempDangerousGoodsNanifest(temp);
			}
			
			int documentYear = headerReceive.getSubject().getDocumentYear();
			int type = headerReceive.getSubject().getType();
			int reference = (int) headerReceive.getSubject().getReference();
			
			List<ResultDeclaration> resultDeList = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(type,
					reference, documentYear);
			
			if (Validator.isNotNull(resultDeList) && resultDeList.size() > 0) {
				
				ResultDeclaration obj = resultDeList.get(0);
				obj.setRequestState(TrangThaiHoSo.TU_CHOI_BAN_KHAI);
				ResultDeclarationLocalServiceUtil.updateResultDeclaration(obj);
			}
			try {
				RejectMessage rejectMessage = MessageFactory.convertXmltoRejectMessage(xmlData);
				updateOrInsertInterfaceRequest(requestCodeOld, MessageType.FUNCTION_TU_CHOI_HO_SO, rejectMessage.getName(),
						rejectMessage.getRejectDesc());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
		}
		
		return result;
	}
	
	private boolean thongBaoNopPhi(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		// Trang thai request ban dau
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_THONG_BAO_NOP_PHI)) {
			
			try {
				BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
						.randomUUID().toString());
				
				List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
						.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
				
				if (documents != null && documents.size() > 0) {
					TempDocument document = documents.get(0);
				
					long documentName = headerReceive.getSubject().getReference();
					int documentYear = headerReceive.getSubject().getDocumentYear();
					
					String corporationid = document.getMaritimeCode();
					Date todate = null;
					String transactionid = StringPool.BLANK;
					
					String organization = document.getMaritimeCode();
					Date fromdate = null;
					int paymenttype = 0;
					Date reportdate = new Date();
					String reportby = "";
					String financialaccountant = StringPool.BLANK;
					int markasdeleted = 0;
					String division = "";
					
					FeeNotice feeNotice = null;
					List<Object> liObjects = MessageFactory
							.converXMLMessagesContentToObject(xmlData);
					if (liObjects != null && liObjects.size() > 0) {
						feeNotice = (FeeNotice)liObjects.get(0);
					}
					
					if(feeNotice != null) {
						double totalpayment = FormatData.convertToDouble(feeNotice.getTotalFee());
						String debitnotenumber = documentName + "-" + feeNotice.getInvoiceNo();
						
						TempDebitnote debitNote = TempDebitNoteLocalServiceUtil.getByNumberDebit(debitnotenumber);
						
						if(debitNote == null) {
							String itineraryNo = "---";
							if(VmaItineraryUtils.checkActiveVma(corporationid)){
								itineraryNo = VmaItineraryUtils.findItineraryNoByDocumentName_DocumentYear(documentName, documentYear);
							}
							TempDebitNoteLocalServiceUtil.doTempDebitNote(documentName, documentYear, corporationid, debitnotenumber, division, financialaccountant, 
									fromdate, markasdeleted, organization, paymenttype, reportby, reportdate, todate, totalpayment, transactionid, document.getMaritimeCode(), feeNotice.getCurrency(), itineraryNo, "");
						} else {
							result = false;
						}
					}
				}
			} catch (Exception e) {
				result = false;
				log.error(e.getMessage());
			}
		}
		
		return result;
	}
	
	private boolean thongBaoXacNhanNopPhi(ObjectExcute objectExcute, Header headerReceive) throws SystemException {
		boolean result = true;
		
		String function = headerReceive.getSubject().getFunction();
		
		String xmlData = objectExcute.getXmlData();
		
		// Trang thai request ban dau
		
		if (function.equalsIgnoreCase(MessageType.FUNCTION_THONG_BAO_XAC_NHAN_NOP_PHI)) {
			
			try {
				BusinessUtils.insertHistory(headerReceive, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
						.randomUUID().toString());
				
				List<TempDocument> documents = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear((new Long(headerReceive
						.getSubject().getReference())).intValue(), headerReceive.getSubject().getDocumentYear());
				
				if (documents != null && documents.size() > 0) {
					long documentName = headerReceive.getSubject().getReference();
					
					Date reportdate = new Date();
				
					FeeApproved feeApproved = null;
					List<Object> liObjects = MessageFactory
							.converXMLMessagesContentToObject(xmlData);
					if (liObjects != null && liObjects.size() > 0) {
						feeApproved = (FeeApproved)liObjects.get(0);
					}
					
					if(feeApproved != null) {
						String invoiceNo = feeApproved.getInvoiceNo();
						String debitnotenumber = documentName + "-" + invoiceNo;
						
						TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil.getByNumberDebit(debitnotenumber);
						tempDebitNote.setMarkasdeleted(1);
						tempDebitNote.setPaymenttype(11);
						tempDebitNote.setReportdate(reportdate);
						
						TempDebitNoteLocalServiceUtil.updateTempDebitNote(tempDebitNote);
					}
				}
			} catch (Exception e) {
				result = false;
				log.error(e.getMessage());
			}
		}
		
		return result;
	}

}