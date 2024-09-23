/**
 * 
 */

package vn.gt.tichhop.message;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import com.fds.nsw.liferay.core.PortalUtil;

import com.fds.nsw.liferay.core.ActionRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.model.Organization;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.OrganizationLocalServiceUtil;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.core.ThemeDisplay;


import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.UserPort;
import vn.gt.dao.common.service.LogMessageValidationLocalServiceUtil;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPort;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import com.fds.nsw.nghiepvu.model.DmPortWharf;


import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmGtOrganization;
import vn.gt.dao.danhmucgt.service.DmGtOrganizationLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest;
import com.fds.nsw.nghiepvu.model.InterfaceRequest;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import vn.gt.dao.noticeandmessage.service.HistoryInterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderChanelLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import com.fds.nsw.nghiepvu.model.ResultHistoryMinistry;
import com.fds.nsw.nghiepvu.model.ResultMinistry;
import com.fds.nsw.nghiepvu.model.ResultNotification;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.dao.result.service.ResultHistoryMinistryLocalServiceUtil;
import vn.gt.dao.result.service.ResultMinistryLocalServiceUtil;
import vn.gt.dao.result.service.ResultNotificationLocalServiceUtil;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.tichhop.message.congbgt2giaothong.PTTNDPassengerList2Temp;
import vn.gt.tichhop.message.giaothong2haiquan.AcceptanceForTransit2Xml;
import vn.gt.tichhop.message.giaothong2haiquan.CreateMessageFactory;
import vn.gt.tichhop.message.giaothong2haiquan.PermissionForTransit2Xml;
import vn.gt.tichhop.message.giaothong2haiquan.PortClearance2Xml;
import vn.gt.tichhop.message.giaothong2haiquan.PortEntryPermit2Xml2;
import vn.gt.tichhop.message.giaothong2haiquan.ShiftingOrder2Xml;
import vn.gt.tichhop.message.haiquan2giaothong.CargoDeclaration2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.ConfirmationOfArrival2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.CrewEffectsDeclaration2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.CrewList2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.DMListSyn;
import vn.gt.tichhop.message.haiquan2giaothong.DangerousGoodsManifest2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.DeclarationForAnimalQuarantine2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.DeclarationForPlantQuarantine2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.GeneralDeclaration2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.HealthQuanrantineDeclare2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.InterfaceRequest2TempUtils;
import vn.gt.tichhop.message.haiquan2giaothong.NoticeOfArrival2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.PassengerList2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.ShipSecurityNotification2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.ShipsStoresDeclaration2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.inland.Attachment2ResultCertificate;
import vn.gt.tichhop.message.haiquan2giaothong.inland.InlandCrewCallCenter2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.inland.InlandCrewList2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.inland.InlandGeneralDeclaration2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.inland.InlandNoticeOfArrival2Temp;
import vn.gt.tichhop.message.haiquan2giaothong.inland.InlandPassengerList2Temp;
import vn.gt.tichhop.sendmessage.MessgaePriorityLevels;
import vn.gt.tichhop.threat.ObjectExcute;
import vn.gt.tichhop.threat.ThreadPassingMessage;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.ConstantCoQuanBanNganh;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;
import vn.gt.utils.config.ConfigurationManager;
import vn.gt.utils.validation.CargoDeclarationValidation;
import vn.gt.utils.validation.CrewEffectsDeclarationValidation;
import vn.gt.utils.validation.CrewListValidation;
import vn.gt.utils.validation.DangerousGoodsManifestValidation;
import vn.gt.utils.validation.DeclarationForAnimalQuarantineValidation;
import vn.gt.utils.validation.DeclarationForPlantQuarantineValidation;
import vn.gt.utils.validation.GeneralDeclarationValidation;
import vn.gt.utils.validation.HeaderValidation;
import vn.gt.utils.validation.HealthQuanrantineDeclareValidation;
import vn.gt.utils.validation.NoticeOfArrivalValidation;
import vn.gt.utils.validation.PassengerListValidation;
import vn.gt.utils.validation.ShipSecurityNotificationValidation;
import vn.gt.utils.validation.ShipsStoresDeclarationValidation;
import vn.gt.utils.validation.inland.AttachmentValidation;
import vn.gt.utils.validation.inland.InlandCrewCallCenterValidation;
import vn.gt.utils.validation.inland.InlandCrewListValidation;
import vn.gt.utils.validation.inland.InlandNoticeOfArrivalValidation;
import vn.gt.utils.validation.inland.InlandPassengerListValidation;
import vn.gt.utils.validation.inland.InlandPortClearanceValidation;
import vn.gt.utils.validation.inland.InlandShiftingOrderValidation;
import vn.gt.utils.validation.inland.PTTNDGeneralDeclarationValidation;
import vn.gt.utils.validation.inland.PTTNDPassengerListValidation;
import vn.nsw.Header;
import vn.nsw.Header.From;
import vn.nsw.Header.Reference;
import vn.nsw.Header.Subject;
import vn.nsw.Header.To;
import vn.nsw.ObjectFactory;
import vn.nsw.fac.ReadMessages;
import vn.nsw.model.CargoDeclaration;
import vn.nsw.model.ConfirmationOfArrival;
import vn.nsw.model.CrewEffectsDeclaration;
import vn.nsw.model.CrewLists;
import vn.nsw.model.DangerousGoodsManifest;
import vn.nsw.model.DeclarationForAnimalQuarantine;
import vn.nsw.model.DeclarationForPlantQuarantine;
import vn.nsw.model.GeneralDeclaration;
import vn.nsw.model.HealthQuanrantineDeclare;
import vn.nsw.model.NoticeOfArrival;
import vn.nsw.model.NoticeOfArrivalCancel;
import vn.nsw.model.PTTNDGeneralDeclaration;
import vn.nsw.model.PassengerList;
import vn.nsw.model.ShiftingOrder;
import vn.nsw.model.ShipSecurityNotification;
import vn.nsw.model.ShipsStoresDeclaration;
import vn.nsw.model.inland.Attachment;
import vn.nsw.model.inland.InlandCrewCallCenter;
import vn.nsw.model.inland.InlandCrewLists;
import vn.nsw.model.inland.InlandGeneralDeclaration;
import vn.nsw.model.inland.InlandNoticeOfArrival;
import vn.nsw.model.inland.InlandPassengerList;
import vn.nsw.model.inland.InlandPortClearance;
import vn.nsw.model.inland.InlandShiftingOrder;
import vn.nsw.model.inland.PTTNDPassengerList;

/**
 * @author win_64
 */
@Slf4j
public class BusinessUtils {
	

	
	public static final String HqmcToBoGiaoThong = "IN";
	public static final String CangVuToBoGiaoThong = "IN_CV";
	public static final String BoGiaoThongToHqmc = "OUT";


	public String decodeUrlXml(String input) {
		try {
			String start = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"><s:Body><sendAndGetMessage xmlns=\"http://facade.dk.gt.dtt.vn\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\"><requestMessage>";
			String end = "</requestMessage></sendAndGetMessage></s:Body></s:Envelope>";
			input = input.replace(start, "").replace(end, "");

			return StringEscapeUtils.unescapeXml(input);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String endoceUrlXml(String input) {
		try {
			String start = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
					"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
					"    <soapenv:Body>\n" +
					"        <sendAndGetMessageResponse xmlns=\"http://facade.dk.gt.dtt.vn\">\n" +
					"            <ns1:sendAndGetMessageReturn xmlns:ns1=\"http://ws.gt.vn\">";
			String end = "</ns1:sendAndGetMessageReturn>\n" +
					"        </sendAndGetMessageResponse>\n" +
					"    </soapenv:Body>\n" +
					"</soapenv:Envelope>";
			return start + StringEscapeUtils.escapeXml11(input) + end;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	// Ham phan tich du lieu nhan tu NSW.
	public String receiveMessage(String dataInput) {
		try {
			Header header = null;
			String data = replaceXML(dataInput.trim());
			if(data.length()<1) return StringPool.BLANK;
			
			header = ReadMessages.readXMLMessagesHeader(data);
			
//			log.info("===header--To--Identity----------" + header.getTo().getIdentity());
//			log.info("===--------To--MinistryCode------" + header.getTo().getMinistryCode());
//			log.info("===========To--Name--------------" + header.getTo().getName());
//			log.info("===========To--OrganizationCode--" + header.getTo().getOrganizationCode());
			// String reason =
			
			if (header != null) {
				// Nhan ket qua xu ly ban nganh.
				log.info("==receiveMessage==FROM=" + header.getFrom().getName() + "===TO===" + header.getTo().getName());
				log.info("==receiveMessage==Type=" + header.getSubject().getType());
				
				if (NhanKetQuaCoQuanBanNganhUtils.checkCoQuanBanNganh(header)) {
					log.info("==receiveMessage==Nhan ket qua xu ly ban nganh==Type------" + header.getSubject().getType());
					log.info("==receiveMessage==Nhan ket qua xu ly ban nganh==Function--" + header.getSubject().getFunction());
					
					// truong hop bussiness phan HOI CUA CO QUAN BAN NHGANH
					insertHistory(header, dataInput, HqmcToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);
					
					String messageId = header.getReference().getMessageId();
					long documentName = header.getSubject().getReference();
					int documentYear = header.getSubject().getDocumentYear();
					
					// ---------gui qua ben Cang Vu
					List<Object> objects = new ArrayList<Object>();
					objects.add(header);
					
					ThreadPassingMessage.init().add(new ObjectExcute(header, objects, dataInput));
					
					updateHistory(header.getReference().getMessageId(), TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
					// ---------ket thuc gui cang vu
					
					// TODO Thá»±c hiá»‡n logic cÃ¡c báº£n tin gá»­i sang
					String xmlResult = NhanKetQuaCoQuanBanNganhUtils.executeKetQuaBanNganh(header, data);
					
					// Cap nhat trang thai la tiep nhan voi truong hop da tiep nhan ban khai.
					if (documentName > 0) {
						List<TempDocument> tempDocuments = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear(documentName,
								documentYear);
						if (tempDocuments != null && tempDocuments.size() > 0) {
							String locCode = tempDocuments.get(0).getMaritimeCode();
							updateInterFaceRequestForCoQuanBanNganh(messageId, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, locCode);
						}
					}
					
					// Insert history truoc khi gui di.
					insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
					
					return xmlResult;
					
				} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.HUY_LENH_DIEU_DONG
						&& header.getSubject().getFunction().equals(MessageType.FUNCTION_KHAI_HUY_HO_SO)) {
					
					log.info("==receiveMessage==HuyLenhDieuDong==Type=" + header.getSubject().getType() + "==Function=="
							+ header.getSubject().getFunction());
					
					NhanThongBaoHuy huyLenhDieuDongUtils = new NhanThongBaoHuy();
					return huyLenhDieuDongUtils.huyLenhDieuDong(header, dataInput, data);
					
				} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.HUY_GIAY_PHEP_VAO_CANG
						&& header.getSubject().getFunction().equals(MessageType.FUNCTION_KHAI_HUY_HO_SO)) {
					NhanThongBaoHuy nhanThongBaoHuy = new NhanThongBaoHuy();
					return nhanThongBaoHuy.huyGiayPhepVaoCang(header, dataInput, data);
					
				} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.HUY_GIAY_PHEP_ROI_CANG
						&& header.getSubject().getFunction().equals(MessageType.FUNCTION_KHAI_HUY_HO_SO)) {
					NhanThongBaoHuy nhanThongBaoHuy = new NhanThongBaoHuy();
					return nhanThongBaoHuy.huyGiayPhepRoiCang(header, dataInput, data);
					
				} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.HUY_GIAY_PHEP_QUA_CANH
						&& header.getSubject().getFunction().equals(MessageType.FUNCTION_KHAI_HUY_HO_SO)) {
					NhanThongBaoHuy nhanThongBaoHuy = new NhanThongBaoHuy();
					return nhanThongBaoHuy.huyGiayPhepQuaCanh(header, dataInput, data);
					
				} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.KHAI_HUY_HO_SO
						&& header.getSubject().getFunction().equals(MessageType.FUNCTION_KHAI_HUY_HO_SO)) {
					NhanThongBaoHuy nhanThongBaoHuy = new NhanThongBaoHuy();
					return nhanThongBaoHuy.khaiHuyHoSo(header, dataInput, data);
					
				} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG
						&& header.getSubject().getFunction().equals(MessageType.FUNCTION_DE_NGHI_CAP_LAI)) {
					log.info("VAO Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG && FUNCTION_DE_NGHI_CAP_LAI");					
					NhanThongBaoHuy nhanThongBaoHuy = new NhanThongBaoHuy();
					return nhanThongBaoHuy.deNghiCapLaiGiayPhepVaoCang(header, dataInput, data);
					
				} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG
						&& header.getSubject().getFunction().equals(MessageType.FUNCTION_DE_NGHI_CAP_LAI)) {
					log.info("VAO Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG && FUNCTION_DE_NGHI_CAP_LAI");
					
					NhanThongBaoHuy nhanThongBaoHuy = new NhanThongBaoHuy();
					return nhanThongBaoHuy.deNghiCapLaiGiayPhepRoiCang(header, dataInput, data);
					
				} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH
						&& header.getSubject().getFunction().equals(MessageType.FUNCTION_DE_NGHI_CAP_LAI)) {
					NhanThongBaoHuy nhanThongBaoHuy = new NhanThongBaoHuy();
					return nhanThongBaoHuy.deNghiCapLaiGiayPhepQuaCanh(header, dataInput, data);
					
				} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.TIN_NHAN_PTTND_VAOROI
						&& header.getSubject().getFunction().equals(MessageType.FUNCTION_KHAI_BAO)) {
					log.info("==Starting==nhanTinNhan==MessageId==" + header.getReference().getMessageId() + "==SoDienThoaiGuiTinNhan=="
							+ header.getSubject().getReference());
					log.info("==receiveMessage==deNghiLamThuTucQuaTinNhan==Type=" + header.getSubject().getType() + "==Function=="
							+ header.getSubject().getFunction());
					
					NhanTinNhanTuTongDai nhanTinNhan = new NhanTinNhanTuTongDai();
					return nhanTinNhan.deNghiLamThuTucQuaTinNhan(header, dataInput, data);
					
				} else if (header.getSubject() != null && header.getSubject().getType() == MessageType.TIN_NHAN_PTTND_VAOROI
						&& header.getSubject().getFunction().equals(MessageType.FUNCTION_KHAI_HUY_HO_SO)) {
					log.info("==Starting==nhanTinNhan==MessageId==" + header.getReference().getMessageId() + "==SoDienThoaiGuiTinNhan=="
							+ header.getSubject().getReference());
					log.info("==receiveMessage==khaiHuyHoSoQuaTinNhan==Type=" + header.getSubject().getType() + "==Function=="
							+ header.getSubject().getFunction());
					
					NhanTinNhanTuTongDai nhanTinNhan = new NhanTinNhanTuTongDai();
					return nhanTinNhan.khaiHuyHoSoQuaTinNhan(header, dataInput, data);
					
				}	else if (header.getSubject() != null && header.getSubject().getType() == MessageType.DONG_BO_DM_CANG_HAI_QUAN	) {					
					log.info("==Starting==dongbodanhmuc==MessageId==Type=" + header.getSubject().getType() + "==Function==" + header.getSubject().getFunction());					
					DMListSyn synch = new DMListSyn();
					return synch.insert2DMPort(header, dataInput, data);					
				}				
				else {
					// Insert cac ban khai vao db, khi co ban khai gui den
					log.info("==receiveMessage==Insert cac ban khai vao db, khi co ban khai gui den==type=" + header.getSubject().getType());
					log.info("==receiveMessage==Insert cac ban khai vao db, khi co ban khai gui den==function==" + header.getSubject().getFunction());
					return nhanBanKhai(header, dataInput, data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return "";
	}
	
	/** Ham phan tich du lieu cac ban khai nhan tu NSW. */
	private String nhanBanKhai(Header header, String dataInput, String data) {
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			
			log.info("==Starting==nhanBanKhai==MessageId==" + header.getReference().getMessageId());
			
			// Insert history.
			String kq = insertHistory(header, dataInput, HqmcToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);
			if (kq != null && kq.length() > 0) {
				
				// Ban tin tra ve khi validate error.
				String xmlResultError = createReturnContentAfterValidationErrorFromNSW(header, kq);
				
				// insertHistory(header, xmlResultError, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
				log.info("==End==nhanBanKhai====MessageId=======" + header.getReference().getMessageId());
				return xmlResultError;
			} else {
				// Insert temp table.
				int documentType = header.getSubject().getDocumentType();
				List<Object> liObjects;
				log.info("===nhanBanKhai===documentType===" + documentType);
				/*if (documentType == MessageType.TAU_RA_PTTND || documentType == MessageType.TAU_VAO_PTTND) {
					//6,7 //pttnd
					liObjects = MessageFactory.converXMLMessagesContentToInLandObject(data);
				} else {*/
					//1,2,3,4,5,8,9,10,11,12,13
					
					liObjects = MessageFactory.converXMLMessagesContentToObject(data);
				/*}*/
				
				if (validationData(header, liObjects, HqmcToBoGiaoThong)) {
					String reason = MessageFactory.getReason2Content(data);
					if (reason == null || reason.length() == 0) {
						reason = "NIL";
					}
					
					log.info("==Reason==" + reason + "==Function==" + header.getSubject().getFunction() + "==Type==" + header.getSubject().getType()
							+ "DocumentType" + header.getSubject().getDocumentType());
					
					
					
					if ((header.getSubject().getFunction().equals(MessageType.FUNCTION_BO_SUNG_CHUNG_TU) && header.getSubject().getType() == MessageType.HO_SO)
							|| header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG
							|| header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH
					
					) {
						
						ResultNotification notification = new ResultNotification();
						// public static final String FUNCTION_BO_SUNG_CHUNG_TU = "05";
						if (header.getSubject().getType() == MessageType.HO_SO) {
							notification.setBusinessTypeCode(5);
						} else {
							notification.setBusinessTypeCode(header.getSubject().getType());
						}
						
						notification.setDocumentName(header.getSubject().getReference());
						notification.setDocumentYear(header.getSubject().getDocumentYear());
						notification.setLatestDate(FormatData.parseStringToDate(header.getSubject().getSendDate()));
						notification.setRequestCode(header.getReference().getMessageId());
						
						TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject()
								.getReference(), header.getSubject().getDocumentYear());
						
						if (tempDocument != null) {
							notification.setMaritimeCode(tempDocument.getMaritimeCode());
						}
						if (reason != null) {
							notification.setRemarks(reason);
						}
						notification.setRole(0);
						notification.setRequestState(1);
						notification.setIsReply(0);
						ResultNotificationLocalServiceUtil.addResultNotification(notification);
					}
					
					// TODO Buoc nay insert du lieu cac ban vao db
					ThreadPassingMessage.init().add(new ObjectExcute(header, liObjects, dataInput));
					
					updateHistory(header.getReference().getMessageId(), TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
					
					/**
					 * //Check co ban khai chung ko Th1: BKC gá»­i trÆ°á»›c khi cáº¥p LDD - TGD cáº­p nháº­t theo thá»© tá»± Æ°u tiÃªn: 1. LDD; 2. XB; 3. TB; 4. BKAN
					 */
					// checkShipDateFormOrShipDateTo(liObjects, header);
					
				} else {
					// TODO Tra ve ban tin validation error va update khong ghi nhan yeu cau.
					updateHistory(header.getReference().getMessageId(), TrangThaiHoSo.HISTORY_REQUEST_STATE_KHONG_GHI_NHAN);
					String error = "";
					List<LogMessageValidation> logMessageValidations = LogMessageValidationLocalServiceUtil.findByDocumentNameDocumentYear(header
							.getSubject().getReference(), header.getSubject().getDocumentYear());
					
					if (logMessageValidations != null && logMessageValidations.size() > 0) {
						LogMessageValidation logMessageValidation = logMessageValidations.get(0);
						error = logMessageValidation.getTagProperty() + " "+ logMessageValidation.getDataValidation();
					}
					log.info("==ERROR==" + error);
					
					// Ban tin tra ve khi validate error.
					String xmlResultError = createReturnContentAfterValidationErrorFromNSW(header, error);
					
					insertHistory(header, xmlResultError, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
					log.info("==End==ERROR==nhanBanKhai====MessageId=======" + header.getReference().getMessageId());
					return xmlResultError;
				}
			}
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
		// Ban tin tra ve dung.
		int documentType = header.getSubject().getDocumentType();
		String xmlResult = createReturnContentAfterParserDataReceiverFromNSW(header);
		
		//TODO: HUYMQ: HOT FIX, khong mo comment do se bi loi request state khi thread processexcutemessage dong vao DB
		
		//header.getSubject().setFunction(MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST + "#TEST");
		insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		
		log.info("==End nhanBanKhai====MessageId=======" + header.getReference().getMessageId());
		return xmlResult;
	}
	
	// Ham phan tich du lieu response nhan tu NSW.
	public boolean insertHistoryReceiveMessageResponse(String dataInput) {
		boolean result = false;
		
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			Header header = null;
			log.info("===insertHistoryReceiveMessageResponse==" + dataInput);
			String data = replaceXML(dataInput.trim());
			if(data.length()<1)return Boolean.FALSE;
			
			header = ReadMessages.readXMLMessagesHeader(data);
			
			if (header != null) {
				int type = header.getSubject().getType();
				String function = header.getSubject().getFunction();
				// Insert history.
				String resultMethod = insertHistory(header, dataInput, HqmcToBoGiaoThong, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID()
						.toString());
				if (resultMethod == null) {
					// log.info("TRUEEEEEEEEEEEEEEEEEEE==============resultMethod " + function);
					result = true;
					// insert thanh cong HistoryInterfaceRequest, InterfaceRequest
					// if (FormatData.convertToInt(function) == MessageType.FUNCTION_TU_CHOI_HO_SO) {
					// log.info("FALSEEEEEEEEEEEEEEEEEEE==============resultMethod");
					// log.info("===insertHistoryReceiveMessageResponse==Thong tin nhan tu HQMC===Type==" + type + "==Function==" + function +
					// "==FUNCTION_TU_CHOI_HO_SO==");
					// result = false;
					// }
				} else {
					result = false;
				}
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}
	
	// Ham phan tich du lieu response nhan tu NSW.
	public boolean insertHistorySendMessage(String dataInput) {
		boolean result = false;
		try {
			Header header = null;
			String data = replaceXML(dataInput.trim());
			if(data.length()<1)return Boolean.FALSE;
			// Tao ban tin tra ve bao da nhan thong tin.
			header = ReadMessages.readXMLMessagesHeader(data);
			
			// Insert history.
			String resultMethod = insertHistory(header, dataInput, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
			if (resultMethod == null) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
			result = false;
		}
		return result;
	}
	
	// Ham phan tich du lieu gui sang tu BGTVT.
	/**
	 * Input: Version of PortClearance2Xml or ShiftingOrder2Xml or PermissionForTransit2Xml Output:
	 */
	public String sendMessageResult(Header header, String version, String requestCode) {
		int mesageType = 0;
		String functionType = "00";
		Object obj = null;
		String xmlResult = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			
			// Insert temp table.
			mesageType = header.getSubject().getType();
			functionType = header.getSubject().getFunction();
			
			// if (functionType == 1) {
			if (functionType.equals(MessageType.FUNCTION_KHAI_BAO)) {
				if (MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH == mesageType) {
					// Giay phep roi cang
					PortClearance2Xml portClearance2Xml = new PortClearance2Xml();
					obj = portClearance2Xml.insert2PortClearance(header.getSubject().getReference(), header.getSubject().getDocumentYear());
				} else if (MessageType.LENH_DIEU_DONG == mesageType) {
					
					// Lenh dieu dong.
					ShiftingOrder2Xml shiftingOrder2Xml = new ShiftingOrder2Xml();
					obj = shiftingOrder2Xml.insert2ShiftingOrder(header.getSubject().getReference(), header.getSubject().getDocumentYear());
				} else if (MessageType.THONG_BAO_CHO_PHEP_TAU_QUA_CANH == mesageType) {
					
					// Thong bao cho phep tau qua canh
					AcceptanceForTransit2Xml acceptanceForTransit2Xml = new AcceptanceForTransit2Xml();
					obj = acceptanceForTransit2Xml.insert2AcceptanceForTransit(header.getSubject().getReference(), header.getSubject()
							.getDocumentYear());
					
				} else if (MessageType.GIAY_PHEP_QUA_CANH == mesageType) {
					
					// Giay phep qua canh.
					PermissionForTransit2Xml permissionForTransit2Xml = new PermissionForTransit2Xml();
					obj = permissionForTransit2Xml.insert2PerForTransitByVersion(header.getSubject().getReference(), header.getSubject()
							.getDocumentYear(), version);
					
				} else if (MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN == mesageType) {
					
					// Giay phep PTTND roi cang
					PortClearance2Xml portClearance2Xml = new PortClearance2Xml();
					obj = portClearance2Xml.insert2PortClearance(header.getSubject().getReference(), header.getSubject().getDocumentYear());
				}  else if (MessageType.PTTND_GIAY_PHEP_VAO_CANG == mesageType) {
					
					// Giay phep PTTND vao cang
					PortClearance2Xml portClearance2Xml = new PortClearance2Xml();
					obj = portClearance2Xml.insert2PTTNDAcceptance(header.getSubject().getReference(), header.getSubject().getDocumentYear());
				}   else if (MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH_VIET_CAM == mesageType) {
					
					// Giay phep PTTND vao cang
					PortEntryPermit2Xml2 portEntryPermit2Xml2 = new PortEntryPermit2Xml2();
					obj = portEntryPermit2Xml2.insert2PortEntryPermit(header.getSubject().getReference(), header.getSubject().getDocumentYear());
				} 
			}
			if (obj != null) {
				xmlResult = MessageFactory.convertObjectToXml(obj);
				// log.info("=====Thong tin cua BGTVT chuan bi gui sang NSW==="
				// + xmlResult);
				
				// Insert history.
				insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
			} else {
				return "";
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		data = createContentSendFromBGTVTToNSW(header, xmlResult);
		insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
		return createContentSendFromBGTVTToNSW(header, xmlResult);
	}
	
	public String sendMessageRejectKH(Header header, String rejectCode, String rejectDesc, String organization, String division, String name,
			Date rejectDate, String requestCode) {
		String xmlTagReject = "";
		String data = "";
		
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		try {
			
			xmlTagReject = CreateMessageFactory.createMessageReject(rejectCode, rejectDesc, organization, division, name, rejectDate);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlTagReject);
			insertMessageQueue(header, data, BusinessUtils.BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlTagReject);
			
		} else {
			data = createContentSendFromBGTVTToNSWInland(header, xmlTagReject);
			insertMessageQueue(header, data, BusinessUtils.BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlTagReject);
		}
	}
	
	// Ham gui ban tin tu choi tu BGTVT sang NSW
	public String sendMessageReject(Header header, String rejectCode, String rejectDesc, String organization, String division, String name,
			Date rejectDate, String requestCode) {
		String xmlTagReject = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		try {
			
			if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
				xmlTagReject = CreateMessageFactory.createMessageRejectInland(rejectCode, rejectDesc, organization, division, name, rejectDate);
				
			} else {
				xmlTagReject = CreateMessageFactory.createMessageReject(rejectCode, rejectDesc, organization, division, name, rejectDate);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlTagReject);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlTagReject);
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlTagReject);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlTagReject);
		}
	}
	
	public String sendMessageRejectInland(Header header, String rejectCode, String rejectDesc, String organization, String division, String name,
			Date rejectDate, String requestCode) {
		String xmlTagReject = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		try {
			xmlTagReject = CreateMessageFactory.createMessageRejectInland(rejectCode, rejectDesc, organization, division, name, rejectDate);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlTagReject);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlTagReject);
			
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlTagReject);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlTagReject);
		}
	}
	
	public String sendMessageModify(Header header, Modify modify, String requestCode) {
		String xmlResult = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		try {
			xmlResult = MessageFactory.convertObjectToXml(modify);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info("==sendMessageModify==xmlResult==");
		// log.info(xmlResult);
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlResult);
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlResult);
		}
	}
	
	// Ham gui ban tin tu choi tu BGTVT sang NSW
	public String sendMessageHuyHoSo(Header header, String organization, String division, String name, Date canceDate, String reason,
			String requestCode) {
		String xmlResult = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		try {
			
			if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
				xmlResult = CreateMessageFactory.createMessageHuyGiayPhepRoiCangInland(organization, division, name, canceDate, reason, 1);
			} else {
				xmlResult = CreateMessageFactory.createMessageHuyGiayPhepRoiCang(organization, division, name, canceDate, reason, 1);
			}
			
			// Insert history.
			insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlResult);
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlResult);
		}
	}
	
	public String sendMessageHuyHoSoNew(Header header, String organization, String division, String name, Date canceDate, String reason,
			String requestCode) {
		String xmlResult = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		try {
			
			xmlResult = CreateMessageFactory.createMessageHuyGiayPhepRoiCang(organization, division, name, canceDate, reason, 1);
			
			// Insert history.
			insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlResult);
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlResult);
		}
	}
	
	// Ham gui ban tin tu choi tu BGTVT sang NSW
	public String sendMessageHuyHoLenhDieuDong(Header header, String organization, String division, String name, String reason, Date canceDate,
			Integer isApprove, String requestCode) {
		String xmlResult = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			
			xmlResult = CreateMessageFactory.createMessageHuyLenhDieuDong(organization, division, name, reason, canceDate, isApprove);
			
			// Insert history.
			insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlResult);
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlResult);
		}
	}
	
	public String sendMessageHuyGiayPhepRoiCang(Header header, String organization, String division, String name, String reason, Date canceDate,
			Integer isApprove, String requestCode) {
		String xmlResult = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		log.info("==sendMessageHuyGiayPhepRoiCang==");
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			xmlResult = CreateMessageFactory.createMessageHuyGiayPhepRoiCang(organization, division, name, reason, canceDate, isApprove);
			
			// Insert history.
			insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		data = createContentSendFromBGTVTToNSW(header, xmlResult);
		insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
		return createContentSendFromBGTVTToNSW(header, xmlResult);
	}
	
	public String sendMessageHuyGiayPhepQuaCanh(Header header, String organization, String division, String name, String reason, Date canceDate,
			Integer isApprove, String requestCode) {
		String xmlResult = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		log.info("==sendMessageHuyGiayPhepQuaCanh==");
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			
			xmlResult = CreateMessageFactory.createMessageHuyGiayPhepQuaCanh(organization, division, name, reason, canceDate, isApprove);
			
			// Insert history.
			insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		data = createContentSendFromBGTVTToNSW(header, xmlResult);
		insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
		return createContentSendFromBGTVTToNSW(header, xmlResult);
	}
	
	public String sendMessageKhaiHuyHoSo(Header header, String organization, String division, String name, String reason, Date canceDate,
			Integer isApprove, String requestCode) {
		String xmlResult = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		log.info("==sendMessageKhaiHuyHoSo==");
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			
			xmlResult = CreateMessageFactory.createMessageKhaiHuyHoSo(organization, division, name, reason, canceDate, isApprove);
			
			// Insert history.
			insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		data = createContentSendFromBGTVTToNSW(header, xmlResult);
		insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
		return createContentSendFromBGTVTToNSW(header, xmlResult);
	}
	
	// Ham gui ban tin tu choi tu BGTVT sang NSW
	public String sendShiftingOrder(Header header, TempDocument tempDocument) {
		log.info("==sendShiftingOrder=");
		
		String xmlResult = "";
		String data = "";
		try {
			ShiftingOrder2Xml shiftingOrder2Xml = new ShiftingOrder2Xml();
			ShiftingOrder shiftingOrder = shiftingOrder2Xml.insert2ShiftingOrderByVersion(tempDocument.getDocumentName(),
					tempDocument.getDocumentYear(), header.getReference().getVersion());
			
			if (shiftingOrder.getPortofAuthority() != null && shiftingOrder.getPortofAuthority().length() > 0) {
				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(shiftingOrder.getPortofAuthority());
				if (dmMaritime != null) {
					shiftingOrder.setPortofAuthority(dmMaritime.getMaritimeReference());
				}
			}
			// Sua lai truoc khi gui di
			// TODO sendShiftingOrder XXXXX
			// shiftingOrder.setShiftingPortWharfCode("XXXXX");
			xmlResult = MessageFactory.convertObjectToXml(shiftingOrder);
			
			// Insert history.
			insertHistory(header, xmlResult, BusinessUtils.BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		data = createContentSendFromBGTVTToNSW(header, xmlResult);
		insertMessageQueue(header, data, BoGiaoThongToHqmc, tempDocument.getRequestCode(), MessgaePriorityLevels.HIGHT);
		return createContentSendFromBGTVTToNSW(header, xmlResult);
	}
	
	// Ham gui ban tin tu choi tu BGTVT sang NSW
	public String sendShiftingOrderInland(Header header, TempDocument tempDocument) {
		log.info("==sendShiftingOrder=");
		String xmlResult = "";
		String data = "";
		try {
			ShiftingOrder2Xml shiftingOrder2Xml = new ShiftingOrder2Xml();
			InlandShiftingOrder shiftingOrder = shiftingOrder2Xml.insert2ShiftingOrderByVersionInland(tempDocument.getDocumentName(),
					tempDocument.getDocumentYear(), header.getReference().getVersion());
			
			if (shiftingOrder.getPortofAuthority() != null && shiftingOrder.getPortofAuthority().length() > 0) {
				DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(shiftingOrder.getPortofAuthority());
				if (dmMaritime != null) {
					shiftingOrder.setPortofAuthority(dmMaritime.getMaritimeReference());
				}
			}
			// / Sua lai truoc khi gui di
			// TODO sendShiftingOrder XXXXX
			// shiftingOrder.setShiftingPortWharfCode("XXXXX");
			xmlResult = MessageFactory.convertObjectToXml(shiftingOrder);
			
			// Insert history.
			insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		data = createContentSendFromBGTVTToNSWInland(header, xmlResult);
		insertMessageQueue(header, data, BoGiaoThongToHqmc, tempDocument.getRequestCode(), MessgaePriorityLevels.HIGHT);
		return createContentSendFromBGTVTToNSWInland(header, xmlResult);
	}
	
	public String sendNoticeOfArrivalCancel(Header header, TempDocument tempDocument, String rejectCode, String rejectDesc, String organization,
			String division, String name, Date rejectDate) {
		
		String xmlResult = "";
		String data = "";
		log.info("==sendNoticeOfArrivalCancel==");
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			
			NoticeOfArrivalCancel noticeOfArrivalCancel = new NoticeOfArrivalCancel();
			noticeOfArrivalCancel.setCallSign(tempDocument.getCallSign());
			noticeOfArrivalCancel.setCancelDate(FormatData.parseDateToTring(rejectDate));
			noticeOfArrivalCancel.setDivision(division);
			noticeOfArrivalCancel.setDocumentName(tempDocument.getDocumentName() + "");
			noticeOfArrivalCancel.setFlagStateOfShip(tempDocument.getStateCode());
			noticeOfArrivalCancel.setIMONumber(tempDocument.getImo());
			noticeOfArrivalCancel.setName(name);
			noticeOfArrivalCancel.setNameOfMaster(tempDocument.getShipCaptain());
			noticeOfArrivalCancel.setNameOfShip(tempDocument.getShipName());
			noticeOfArrivalCancel.setOrganization(organization);
			noticeOfArrivalCancel.setShipTypeCode(tempDocument.getShipTypeCode());
			noticeOfArrivalCancel.setUserCreated(tempDocument.getUserCreated());
			
			xmlResult = CreateMessageFactory.createNoticeOfArrivalCancel(noticeOfArrivalCancel);
			
			// Insert history.
			insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		data = createContentSendFromBGTVTToNSW(header, xmlResult);
		insertMessageQueue(header, data, BoGiaoThongToHqmc, tempDocument.getRequestCode(), MessgaePriorityLevels.HIGHT);
		return createContentSendFromBGTVTToNSW(header, xmlResult);
	}
	
	// Ham gui ban tin chap nhan tu BGTVT sang NSW
	public String sendMessageAccept(Header header, String organization, String division, String name, Date acceptDate, String requestCode) {
		log.info("==sendMessageAccept==");
		String xmlResult = "";
		String data = "";
		// log.info("====header.getSubject().getDocumentType()=====" + header.getReference().getMessageId()+ "header.getSubject().getDocumentYear()" +
		// header.getSubject().getDocumentYear());
		// TempDocument tempDocument =
		// TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(Long.valueOf(header.getReference().getMessageId()),
		// header.getSubject().getDocumentYear());
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			
			xmlResult = CreateMessageFactory.createMessageAccept(organization, division, name, acceptDate);
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlResult);
			
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			
			return createContentSendFromBGTVTToNSWInland(header, xmlResult);
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlResult);
		}
		
	}
	
	// dungle them message SentMessageRejectDinhKem
	public String SentMessageRejectDinhKem(Header header, String lydoYeuCauXuatTrinh, String organization, String division, String name, Date rejectDate,
			TempDocument tempDocument) {
		log.info("==Them SentMessageRejectDinhKem==");
		String xmlResult = "";
		String data = "";
		// Tao ban tin tra ve bao da nhan thong tin.
		// nhap lai ly do yeu cau xuat trinh
		//rejectDesc =  vn.dtt.thutuc.yeucauxuattrinh
		try {
			
			xmlResult = CreateMessageFactory.createMessageBoSungDinhKem(lydoYeuCauXuatTrinh, organization, division, name, rejectDate, tempDocument);
			if (xmlResult.length() == 0 )
			{
				return "Chua co giay to dinh kem.";				
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlResult);
			
			insertMessageQueue(header, data, BoGiaoThongToHqmc, tempDocument.getRequestCode(), MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlResult);
			
			
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, tempDocument.getRequestCode(), MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlResult);
		}
	}
	
	public String SentMessageAcceptDinhKem(Header header, String rejectDesc, String organization, String division, String name, Date rejectDate,
			TempDocument tempDocument) {
		log.info("==Them SentMessageAcceptDinhKem==");
		String xmlResult = "";
		String data = "";
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			
			xmlResult = CreateMessageFactory.createMessageDongYDinhKem(rejectDesc, organization, division, name, rejectDate, tempDocument);
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			
			data = createContentSendFromBGTVTToNSWInland(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, tempDocument.getRequestCode(), MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlResult);
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, tempDocument.getRequestCode(), MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlResult);
		}
	}
	
	public String SentMessageTucChoiDinhKem(Header header, String rejectDesc, String organization, String division, String name, Date rejectDate,
			TempDocument tempDocument, String lydotuchoi) {
		log.info("==Them SentMessageTucChoiDinhKem==");
		String xmlResult = "";
		String data = "";
		
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			
			xmlResult = CreateMessageFactory.createMessageTuChoiDinhKem(rejectDesc, organization, division, name, rejectDate, tempDocument,
					lydotuchoi);
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, tempDocument.getRequestCode(), MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlResult);
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, tempDocument.getRequestCode(), MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlResult);
		}
	}
	
	// Ham gui ban tin xac nhan tu BGTVT sang NSW.
	public String sendMessageConformed(Header header, Date acceptDate, String requestCode) {
		
		String xmlResult = "";
		String data = "";
		// TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(header.getSubject().getDocumentType(),
		// header.getSubject().getDocumentYear());
		log.info("==sendMessageConformed==");
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			xmlResult = "<ReceiveDate>" + FormatData.parseDateToTring(new Date()) + "</ReceiveDate>";
			
			// Insert history.
			insertHistory(header, xmlResult, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
		if (header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND || header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			data = createContentSendFromBGTVTToNSWInland(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSWInland(header, xmlResult);
		} else {
			data = createContentSendFromBGTVTToNSW(header, xmlResult);
			insertMessageQueue(header, data, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
			return createContentSendFromBGTVTToNSW(header, xmlResult);
		}
		
	}
	
	/**
	 * @param header
	 * @param liObjects
	 * @param requestDirection
	 * @return true: la du lieu dung, false la du lieu sai.
	 */
	public boolean validationData(Header header, List<Object> liObjects, String requestDirection) {
		boolean resultStatus = true;
		try {
			HeaderValidation headerValidation = new HeaderValidation();
			if (!headerValidation.validation(header, requestDirection)) {
				resultStatus = false;
			}
			for (Object object : liObjects) {
				if (object instanceof ShipSecurityNotification) {
					ShipSecurityNotificationValidation securityNotificationValidation = new ShipSecurityNotificationValidation();
					if (!securityNotificationValidation.validation(ShipSecurityNotification.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof CargoDeclaration) {
					CargoDeclarationValidation cargoDeclarationValidation = new CargoDeclarationValidation();
					if (!cargoDeclarationValidation.validation(CargoDeclaration.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof CrewLists) {
					CrewListValidation crewListValidation = new CrewListValidation();
					if (!crewListValidation.validation(CrewLists.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof DangerousGoodsManifest) {
					DangerousGoodsManifestValidation dangerousGoodsManifestValidation = new DangerousGoodsManifestValidation();
					if (!dangerousGoodsManifestValidation.validation(DangerousGoodsManifest.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof CrewEffectsDeclaration) {
					CrewEffectsDeclarationValidation crewEffectsDeclarationValidation = new CrewEffectsDeclarationValidation();
					if (!crewEffectsDeclarationValidation.validation(CrewEffectsDeclaration.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof NoticeOfArrival) {
					NoticeOfArrivalValidation noticeOfArrivalValidation = new NoticeOfArrivalValidation();
					if (!noticeOfArrivalValidation.validation(NoticeOfArrival.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof PassengerList) {
					PassengerListValidation passengerListValidation = new PassengerListValidation();
					if (!passengerListValidation.validation(PassengerList.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof ShipsStoresDeclaration) {
					ShipsStoresDeclarationValidation shipsStoresDeclarationValidation = new ShipsStoresDeclarationValidation();
					if (!shipsStoresDeclarationValidation.validation(ShipsStoresDeclaration.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof DeclarationForAnimalQuarantine) {
					DeclarationForAnimalQuarantineValidation declarationForAnimalQuarantineValidation = new DeclarationForAnimalQuarantineValidation();
					if (!declarationForAnimalQuarantineValidation.validation(DeclarationForAnimalQuarantine.class.cast(object), header,
							requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof DeclarationForPlantQuarantine) {
					DeclarationForPlantQuarantineValidation declarationForPlantQuarantineValidation = new DeclarationForPlantQuarantineValidation();
					if (!declarationForPlantQuarantineValidation.validation(DeclarationForPlantQuarantine.class.cast(object), header,
							requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof GeneralDeclaration) {
					GeneralDeclarationValidation generalDeclarationValidation = new GeneralDeclarationValidation();
					if (!generalDeclarationValidation.validation(GeneralDeclaration.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof HealthQuanrantineDeclare) {
					HealthQuanrantineDeclareValidation healthQuanrantineDeclareValidation = new HealthQuanrantineDeclareValidation();
					if (!healthQuanrantineDeclareValidation.validation(HealthQuanrantineDeclare.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				}
				/*
				 * Inland
				 */
				else if (object instanceof InlandShiftingOrder) {
					InlandShiftingOrderValidation inlandShiftingOrderValidation = new InlandShiftingOrderValidation();
					if (!inlandShiftingOrderValidation.validation(InlandShiftingOrder.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
					
				} else if (object instanceof InlandCrewLists) {
					InlandCrewListValidation inlandCrewListValidation = new InlandCrewListValidation();
					if (!inlandCrewListValidation.validation(InlandCrewLists.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof InlandPassengerList) {
					InlandPassengerListValidation inlandPassengerList = new InlandPassengerListValidation();
					if (!inlandPassengerList.validation(InlandPassengerList.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof InlandPortClearance) {
					InlandPortClearanceValidation inlandPortClearanceValidation = new InlandPortClearanceValidation();
					if (!inlandPortClearanceValidation.validation(InlandPortClearance.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof InlandNoticeOfArrival) {
					InlandNoticeOfArrivalValidation inlandNoticeOfArrivalValidation = new InlandNoticeOfArrivalValidation();
					if (!inlandNoticeOfArrivalValidation.validation(InlandNoticeOfArrival.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof Attachment) {
					AttachmentValidation attachmentValidation = new AttachmentValidation();
					if (!attachmentValidation.validation(Attachment.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if(object instanceof PTTNDPassengerList) {
					PTTNDPassengerListValidation objectValidation = new PTTNDPassengerListValidation();
					if (!objectValidation.validation(PTTNDPassengerList.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof PTTNDGeneralDeclaration) {
					PTTNDGeneralDeclarationValidation generalDeclarationValidation = new PTTNDGeneralDeclarationValidation();
					if (!generalDeclarationValidation.validation(PTTNDGeneralDeclaration.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				} else if (object instanceof InlandCrewCallCenter) {
					InlandCrewCallCenterValidation crewCallCenterValidation = new InlandCrewCallCenterValidation();
					if (!crewCallCenterValidation.validation(InlandCrewCallCenter.class.cast(object), header, requestDirection)) {
						resultStatus = false;
					}
				}
			}
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return resultStatus;
	}
	
	// TODO insertTempTable: insert data vao db
	public String insertTempTable(Header header, List<Object> liObjects) {
		// boolean result = true;
		log.info("==insertTempTable==");
		try {
			boolean validateDocument = CheckBusinessUtil.checkValidateDocument(
					header, liObjects);
			if (validateDocument != true) {
				return "ERR: Inconsistent data.";
			}
			for (Object object : liObjects) {
				log.info("==insertTempTable=="+object.getClass().getName());
				if (object instanceof ShipSecurityNotification) {
					ShipSecurityNotification2Temp securityNotification2Temp = new ShipSecurityNotification2Temp();
					securityNotification2Temp.insert2Temp(ShipSecurityNotification.class.cast(object), header);
					
				} else if (object instanceof CargoDeclaration) {
					CargoDeclaration2Temp cargoDeclaration2Temp = new CargoDeclaration2Temp();
					cargoDeclaration2Temp.insert2Temp(CargoDeclaration.class.cast(object), header);
					
				} else if (object instanceof CrewLists) {
					
					CrewList2Temp crewList2Temp = new CrewList2Temp();
					crewList2Temp.insert2Temp(CrewLists.class.cast(object), header);
					
				} else if (object instanceof DangerousGoodsManifest) {
					DangerousGoodsManifest2Temp dangerousGoodsManifest2Temp = new DangerousGoodsManifest2Temp();
					dangerousGoodsManifest2Temp.insert2Temp(DangerousGoodsManifest.class.cast(object), header);
					
				} else if (object instanceof CrewEffectsDeclaration) {
					CrewEffectsDeclaration2Temp crewEffectsDeclaration2Temp = new CrewEffectsDeclaration2Temp();
					crewEffectsDeclaration2Temp.insert2Temp(CrewEffectsDeclaration.class.cast(object), header);
					
				} else if (object instanceof NoticeOfArrival) {
					NoticeOfArrival2Temp noticeOfArrival2Temp = new NoticeOfArrival2Temp();
					noticeOfArrival2Temp.insert2Temp(NoticeOfArrival.class.cast(object), header);
					
				} else if (object instanceof PassengerList) {
					PassengerList2Temp passengerList2Temp = new PassengerList2Temp();
					passengerList2Temp.insert2Temp(PassengerList.class.cast(object), header);
					
				} else if (object instanceof ShipsStoresDeclaration) {
					ShipsStoresDeclaration2Temp shipsStoresDeclaration2Temp = new ShipsStoresDeclaration2Temp();
					shipsStoresDeclaration2Temp.insert2Temp(ShipsStoresDeclaration.class.cast(object), header);
				} else if (object instanceof DeclarationForAnimalQuarantine) {
					DeclarationForAnimalQuarantine2Temp declarationForAnimalQuarantine2Temp = new DeclarationForAnimalQuarantine2Temp();
					declarationForAnimalQuarantine2Temp.insert2Temp(DeclarationForAnimalQuarantine.class.cast(object), header);
					
				} else if (object instanceof DeclarationForPlantQuarantine) {
					DeclarationForPlantQuarantine2Temp declarationForPlantQuarantine2Temp = new DeclarationForPlantQuarantine2Temp();
					declarationForPlantQuarantine2Temp.insert2Temp(DeclarationForPlantQuarantine.class.cast(object), header);
					
				} else if (object instanceof GeneralDeclaration) {
					GeneralDeclaration2Temp generalDeclaration2Temp = new GeneralDeclaration2Temp();
					generalDeclaration2Temp.insert2Temp(GeneralDeclaration.class.cast(object), header);
					
				} else if (object instanceof HealthQuanrantineDeclare) {
					HealthQuanrantineDeclare2Temp healthQuanrantineDeclare2Temp = new HealthQuanrantineDeclare2Temp();
					healthQuanrantineDeclare2Temp.insert2Temp(HealthQuanrantineDeclare.class.cast(object), header);
				} else if (object instanceof ConfirmationOfArrival) {
					ConfirmationOfArrival2Temp confirmationOfArrival2Temp = new ConfirmationOfArrival2Temp();
					confirmationOfArrival2Temp.insert2Temp(ConfirmationOfArrival.class.cast(object), header);
				}
				else if (object instanceof InlandGeneralDeclaration) {
					log.info("==insertTempTable==  InlandGeneralDeclaration");
					InlandGeneralDeclaration2Temp inlandGeneralDeclaration2Temp = new InlandGeneralDeclaration2Temp();
					inlandGeneralDeclaration2Temp.insert2Temp(InlandGeneralDeclaration.class.cast(object), header);
				}
				else if (object instanceof InlandPassengerList) {
					log.info("==insertTempTable==  InlandPassengerList");
					InlandPassengerList2Temp inlandPassengerList2Temp = new InlandPassengerList2Temp();
					inlandPassengerList2Temp.insert2TempInland(InlandPassengerList.class.cast(object), header);
					
				} else if (object instanceof InlandNoticeOfArrival) {
					log.info("==insertTempTable==  InlandNoticeOfArrival");
					InlandNoticeOfArrival2Temp inlandNoticeOfArrival2Temp = new InlandNoticeOfArrival2Temp();
					inlandNoticeOfArrival2Temp.insert2Temp(InlandNoticeOfArrival.class.cast(object), header);
					
				} else if (object instanceof InlandCrewLists) {
					log.info("==insertTempTable==  InlandCrewLists");
					InlandCrewList2Temp inlandCrewList2Temp = new InlandCrewList2Temp();
					inlandCrewList2Temp.insert2Temp(InlandCrewLists.class.cast(object), header);
					
				} else if (object instanceof Attachment) {
					log.info("==insertResultCertificate==  Attachment");
					Attachment2ResultCertificate attachment2ResultCertificate = new Attachment2ResultCertificate();
					attachment2ResultCertificate.insert2ResultCertificate(Attachment.class.cast(object), header);
				}
				else if (object instanceof PTTNDPassengerList) {
					log.info("==PTTNDPassengerList==  PTTNDPassengerList");
					PTTNDPassengerList2Temp.insert2DB(PTTNDPassengerList.class.cast(object), header);					
				}
				else if (object instanceof PTTNDGeneralDeclaration) {
					log.info("==PTTNDGeneralDeclaration==  PTTNDGeneralDeclaration");
					InlandGeneralDeclaration2Temp PTTNDGeneralDeclaration2Temp = new InlandGeneralDeclaration2Temp();
					PTTNDGeneralDeclaration2Temp.insert2TempPTTND(PTTNDGeneralDeclaration.class.cast(object), header);
				}
				else if (object instanceof InlandCrewCallCenter) {
					log.info("==InlandCrewCallCenter==  InlandCrewCallCenter");
					InlandCrewCallCenter2Temp CrewCallCenter2Temp = new InlandCrewCallCenter2Temp();
					CrewCallCenter2Temp.insert2TempCallCenterPTTND(InlandCrewCallCenter.class.cast(object), header);
				}
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
		return null;
	}
	
	public static String insertHistory(Header header, String data, String direction, int requestState, String uuid) {
		String kq = null;
		log.info("====insertHistory====" + header.getSubject().getType() + "=" + header.getTo().getIdentity());
		try {
			
			InterfaceRequest interfaceRequest = new InterfaceRequest();
			// interfaceRequest.setRemarks(remarks)
			String documentNameRef = String.valueOf(header.getSubject().getReference());
			interfaceRequest.setDocumentNameRef(documentNameRef);
			interfaceRequest.setBusinessType(String.valueOf(header.getSubject().getType()));
			interfaceRequest.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
			interfaceRequest.setFunctionType(header.getSubject().getFunction());
			
			interfaceRequest.setReceiverIdentify(header.getTo().getIdentity());
			interfaceRequest.setRequestVersion(header.getReference().getVersion());
			interfaceRequest.setReceiverName(header.getTo().getName());
			
			interfaceRequest.setSenderIdentify(header.getFrom().getIdentity());
			interfaceRequest.setSenderName(header.getFrom().getName());
			
			if (uuid == null) {
				interfaceRequest.setRequestCode(header.getReference().getMessageId());
			} else {
				interfaceRequest.setRequestCode(uuid);
			}
			// Bo? vao day, nhung HistoryInterfaceRequest van de?
			// interfaceRequest.setRequestContent(data);
			interfaceRequest.setRequestDate(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			interfaceRequest.setRequestDirection(direction);// HqmcToBoGiaoThong
			interfaceRequest.setRequestedDate(new Date());
			interfaceRequest.setRequestResponseTime(new Date());
			interfaceRequest.setRequestState(requestState);// TrangThaiHoSo.DA_GHI_NHO_YEU_CAU
			
			InterfaceRequestLocalServiceUtil.addInterfaceRequest(interfaceRequest);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			kq = e.getMessage();
		}
		
		// History
		HistoryInterfaceRequest historyInterfaceRequest = null;
		try {
			
			historyInterfaceRequest = new HistoryInterfaceRequest();

			
			historyInterfaceRequest.setBusinessType(String.valueOf(header.getSubject().getType()));
			historyInterfaceRequest.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
			historyInterfaceRequest.setFunctionType(header.getSubject().getFunction());
			
			historyInterfaceRequest.setReceiverIdentify(header.getTo().getIdentity());
			historyInterfaceRequest.setRequestVersion(header.getReference().getVersion());
			historyInterfaceRequest.setReceiverName(header.getTo().getName());
			
			historyInterfaceRequest.setSenderIdentify(header.getFrom().getIdentity());
			historyInterfaceRequest.setSenderName(header.getFrom().getName());
			
			if (uuid == null) {
				historyInterfaceRequest.setRequestCode(header.getReference().getMessageId());
			} else {
				historyInterfaceRequest.setRequestCode(uuid);
			}
			
			historyInterfaceRequest.setRequestContent(data);
			historyInterfaceRequest.setRequestDate(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			historyInterfaceRequest.setRequestDirection(direction);
			historyInterfaceRequest.setRequestedDate(new Date());
			historyInterfaceRequest.setRequestResponseTime(new Date());
			historyInterfaceRequest.setRequestState(requestState);
			
			HistoryInterfaceRequestLocalServiceUtil.addHistoryInterfaceRequest(historyInterfaceRequest);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			kq = e.getMessage();
		}
		
		return kq;
	}
	
	public String insertHistoryThreeIssue(Header header, String data, String direction, int requestState, String uuid, String userName) {
		String kq = null;
		try {
			
			
			InterfaceRequest interfaceRequest = new InterfaceRequest();
			String documentNameRef = String.valueOf(header.getSubject().getReference());
			interfaceRequest.setDocumentNameRef(documentNameRef);
			interfaceRequest.setRemarks(getRemarkChapNhan(userName));
			
			interfaceRequest.setBusinessType(String.valueOf(header.getSubject().getType()));
			interfaceRequest.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
			interfaceRequest.setFunctionType(header.getSubject().getFunction());
			
			interfaceRequest.setReceiverIdentify(header.getTo().getIdentity());
			interfaceRequest.setRequestVersion(header.getReference().getVersion());
			interfaceRequest.setReceiverName(header.getTo().getName());
			
			interfaceRequest.setSenderIdentify(header.getFrom().getIdentity());
			interfaceRequest.setSenderName(header.getFrom().getName());
			
			if (uuid == null) {
				interfaceRequest.setRequestCode(header.getReference().getMessageId());
			} else {
				interfaceRequest.setRequestCode(uuid);
			}
			
			interfaceRequest.setRequestDate(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			interfaceRequest.setRequestDirection(direction);// HqmcToBoGiaoThong
			interfaceRequest.setRequestedDate(new Date());
			interfaceRequest.setRequestResponseTime(new Date());
			interfaceRequest.setRequestState(requestState);// TrangThaiHoSo.DA_GHI_NHO_YEU_CAU
			
			InterfaceRequestLocalServiceUtil.addInterfaceRequest(interfaceRequest);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			kq = e.getMessage();
		}
		
		// History
		HistoryInterfaceRequest historyInterfaceRequest = null;
		try {
			
			historyInterfaceRequest = new HistoryInterfaceRequest();
			
			historyInterfaceRequest.setBusinessType(String.valueOf(header.getSubject().getType()));
			historyInterfaceRequest.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
			historyInterfaceRequest.setFunctionType(header.getSubject().getFunction());
			
			historyInterfaceRequest.setReceiverIdentify(header.getTo().getIdentity());
			historyInterfaceRequest.setRequestVersion(header.getReference().getVersion());
			historyInterfaceRequest.setReceiverName(header.getTo().getName());
			
			historyInterfaceRequest.setSenderIdentify(header.getFrom().getIdentity());
			historyInterfaceRequest.setSenderName(header.getFrom().getName());
			
			if (uuid == null) {
				historyInterfaceRequest.setRequestCode(header.getReference().getMessageId());
			} else {
				historyInterfaceRequest.setRequestCode(uuid);
			}
			
			historyInterfaceRequest.setRequestContent(data);
			historyInterfaceRequest.setRequestDate(FormatData.parseStringToDate(header.getSubject().getSendDate()));
			historyInterfaceRequest.setRequestDirection(direction);
			historyInterfaceRequest.setRequestedDate(new Date());
			historyInterfaceRequest.setRequestResponseTime(new Date());
			historyInterfaceRequest.setRequestState(requestState);
			historyInterfaceRequest.setRemarks(getRemarkChapNhan(userName));
			
			HistoryInterfaceRequestLocalServiceUtil.addHistoryInterfaceRequest(historyInterfaceRequest);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			
			kq = e.getMessage();
		}
		return kq;
	}
	
	public void updateHistory(String requestCode, int requestState) {
		try {
			log.info("==updateHistory==(RequestCode)=messageId==" + requestCode);
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findInterfaceRequestByRequestCode(requestCode);
			interfaceRequest.setRequestState(requestState);
			InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
		// History
		try {
			// HistoryInterfaceRequest historyInterfaceRequest =
			// HistoryInterfaceRequestLocalServiceUtil.findHistoryInterfaceRequestByRequestCode(requestCode);
			// historyInterfaceRequest.setRequestState(requestState);
			// HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(historyInterfaceRequest);
			HistoryInterfaceRequest field = HistoryInterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);
			if (Validator.isNotNull(field)) {
				Hashtable<String, String> hashSql = new Hashtable<String, String>();
				hashSql.put(InterfaceRequest2TempUtils.RequestState, requestState + "");
				String sqlUpateById = InterfaceRequest2TempUtils.sqlUpateById(hashSql, field.getId());
				log.info("==updateHistory==sqlUpateById==" + sqlUpateById);
				int updateHistoryInterfaceRequest = HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(sqlUpateById);
				log.info("==updateHistory==sqlUpateById==" + updateHistoryInterfaceRequest);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
	}
	
	public void updateInterFaceRequestForCoQuanBanNganh(String requestCode, int requestState, String locCode) {
		try {
			log.info("==updateInterFaceRequestForCoQuanBanNganh==RequestCode==" + requestCode);
			InterfaceRequest interfaceRequest = InterfaceRequestLocalServiceUtil.findInterfaceRequestByRequestCode(requestCode);
			interfaceRequest.setRequestState(requestState);
			interfaceRequest.setOrganizationCode("01");
			interfaceRequest.setLocCode(locCode);
			InterfaceRequestLocalServiceUtil.updateInterfaceRequest(interfaceRequest);
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
		// History
		try {
			// HistoryInterfaceRequest historyInterfaceRequest =
			// HistoryInterfaceRequestLocalServiceUtil.findHistoryInterfaceRequestByRequestCode(requestCode);
			// historyInterfaceRequest.setRequestState(requestState);
			// historyInterfaceRequest.setOrganizationCode("01");
			// historyInterfaceRequest.setLocCode(locCode);
			// HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(historyInterfaceRequest);
			
			HistoryInterfaceRequest field = HistoryInterfaceRequestLocalServiceUtil.findByRequestCode(requestCode);
			if (Validator.isNotNull(field)) {
				Hashtable<String, String> hashSql = new Hashtable<String, String>();
				hashSql.put(InterfaceRequest2TempUtils.RequestState, requestState + "");
				hashSql.put(InterfaceRequest2TempUtils.OrganizationCode, "01");
				hashSql.put(InterfaceRequest2TempUtils.LocCode, locCode);
				
				String sqlUpateById = InterfaceRequest2TempUtils.sqlUpateById(hashSql, field.getId());
				log.info("==updateInterFaceRequestForCoQuanBanNganh==sqlUpateById==" + sqlUpateById);
				int updateHistoryInterfaceRequest = HistoryInterfaceRequestLocalServiceUtil.updateHistoryInterfaceRequest(sqlUpateById);
				log.info("==updateInterFaceRequestForCoQuanBanNganh==sqlUpateById==" + updateHistoryInterfaceRequest);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
	}
	
	public static boolean insertTempDocument(String requestCode, String requestState, String documentName, String documentYear,
			String documentTypeCode, String userCreated, String shipAgencyCode, String shipName, String shipTypeCode, String stateCode,
			String shipCaptain, String imo, String grt, String nt, String dwt, String unitGRT, String unitNT, String unitDWT, String callSign,
			long preDocumentName) {
		try {
			List<TempDocument> liTempDocuments = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear(
					FormatData.convertToLong(documentName), FormatData.convertToInt(documentYear));
			if (liTempDocuments == null || liTempDocuments.size() == 0) {
				TempDocument tempDocument = new TempDocument();
				
				tempDocument.setRequestCode(requestCode);
				tempDocument.setRequestState(TrangThaiHoSo.TEMP_DOCUMENT_CHO_TIEP_NHAN);
				tempDocument.setDocumentName(preDocumentName);
				tempDocument.setDocumentYear(FormatData.convertToInt(documentYear));
				tempDocument.setUserCreated(userCreated);
				tempDocument.setShipAgencyCode(shipAgencyCode);
				tempDocument.setShipName(shipName);
				tempDocument.setShipTypeCode(shipTypeCode);
				tempDocument.setStateCode(stateCode);
				tempDocument.setShipCaptain(shipCaptain);
				tempDocument.setImo(imo);
				tempDocument.setGrt(FormatData.convertToLong(grt));
				tempDocument.setNt(FormatData.convertToLong(nt));
				tempDocument.setDwt(FormatData.convertToLong(dwt));
				tempDocument.setMaritimeCode("19"); // Tam theo yeu cau BA.
				tempDocument.setUnitDWT(unitDWT);
				tempDocument.setUnitGRT(unitGRT);
				tempDocument.setUnitNT(unitNT);
				tempDocument.setCallSign(callSign);
				tempDocument.setPreDocumentName(String.valueOf(preDocumentName));
				tempDocument.setCreatedDate(new Date());
				tempDocument.setLastModifiedDate(new Date());
				tempDocument.setUpgradeVersion(1);
				
				TempDocumentLocalServiceUtil.addTempDocument(tempDocument);
			}
			return true;
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return false;
	}
	
	public static synchronized boolean insert2TempDocument(TempDocument tempDocument, Header header, Object obj) {
		try {
			List<TempDocument> liTempDocuments = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear(
					tempDocument.getDocumentName(), tempDocument.getDocumentYear());
			
			if (liTempDocuments != null && liTempDocuments.size() > 0) {
				tempDocument = liTempDocuments.get(0);
				
				// SUA 05/07/2018: CAP NHAT TRANG THAI HO SO KHI GUI BO SUNG TU NSW
				if(tempDocument.getRequestState() == TrangThaiHoSo.KH_YEU_CAU_BO_SUNG) {
					tempDocument.setRequestState(TrangThaiHoSo.TEMP_DOCUMENT_CHO_TIEP_NHAN);
				}
				
				if(tempDocument.getDocumentStatusCode() == TrangThaiHoSo.YEU_CAU_SUA_DOI_BO_SUNG) {
					tempDocument.setDocumentStatusCode(TrangThaiHoSo.DA_TIEP_NHAP);
				}
			} else {
				tempDocument.setRequestState(TrangThaiHoSo.TEMP_DOCUMENT_CHO_TIEP_NHAN);
			}
			
			tempDocument.setPreDocumentName(String.valueOf(header.getSubject().getPreReference()));
			
			// TODO=TempDocumnt -- Tinh ShipDateFrom
			if (header.getSubject().getType() == MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST) {
				
				if (obj instanceof GeneralDeclaration) {
					tempDocument.setShipDateFrom(FormatData.parseStringToDate(GeneralDeclaration.class.cast(obj).getDateOfArrival()));
					
					if (header.getSubject().getDocumentType() == MessageType.XUAT_CANH
							|| header.getSubject().getDocumentType() == 5
							|| header.getSubject().getDocumentType() == Integer.valueOf(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)
							|| header.getSubject().getDocumentType() == 9
							|| header.getSubject().getDocumentType() == 11
							|| header.getSubject().getDocumentType() == 13) {
						tempDocument.setShipDateTo(FormatData.parseStringToDate(GeneralDeclaration.class.cast(obj).getDateOfArrival()));
					}
					
					tempDocument.setGrt(FormatData.convertToDouble(GeneralDeclaration.class.cast(obj).getGrossTonnage()));
					tempDocument.setUnitGRT(GeneralDeclaration.class.cast(obj).getGrossTonnageUnit());
					tempDocument.setUnitNT(GeneralDeclaration.class.cast(obj).getNetTonnageUnit());
					tempDocument.setNt(FormatData.convertToDouble(GeneralDeclaration.class.cast(obj).getNetTonnage()));
					
					tempDocument.setShipName(GeneralDeclaration.class.cast(obj).getNameOfShip());
					tempDocument.setShipAgencyCode(GeneralDeclaration.class.cast(obj).getTaxCodeOfShipownersAgents());
					tempDocument.setArrivalShipAgency(GeneralDeclaration.class.cast(obj).getTaxCodeOfShipownersAgents());
					tempDocument.setDepartureShipAgency(GeneralDeclaration.class.cast(obj).getTaxCodeOfShipownersAgents());
					tempDocument.setStateCode(GeneralDeclaration.class.cast(obj).getFlagStateOfShip());
					tempDocument.setShipCaptain(GeneralDeclaration.class.cast(obj).getNameOfMaster());
					tempDocument.setShipTypeCode(GeneralDeclaration.class.cast(obj).getShipTypeCode());
					tempDocument.setNameOfShipownersAgents(GeneralDeclaration.class.cast(obj).getNameOfShipownersAgents());
					
					// SONVH them 5/3/2019
					tempDocument.setShipName(GeneralDeclaration.class.cast(obj)
							.getNameOfShip());
					tempDocument.setImo(GeneralDeclaration.class.cast(obj)
							.getIMONumber());
					tempDocument.setCallSign(GeneralDeclaration.class.cast(obj)
							.getCallSign());
					
					// BinhNT edit with Hong Son, 10-10-2014, 23h
					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil.getByPortRegionCode(GeneralDeclaration.class.cast(obj)
							.getPortRegionCode());
					if (dmPortRegion != null) {
						tempDocument.setMaritimeCode(dmPortRegion.getPortRegionRef());
					} else {
						DmPort dmPort = DmPortLocalServiceUtil.getByPortCode(GeneralDeclaration.class.cast(obj).getPortOfArrivalCode());
						if (dmPort != null) {
							tempDocument.setMaritimeCode(dmPort.getLoCode());
						} else {
							DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(GeneralDeclaration.class.cast(obj)
									.getPortRegionCode());
							if (dmPortWharf != null) {
								DmPortRegion dmPortRegion12 = DmPortRegionLocalServiceUtil.getByPortRegionCode(dmPortWharf.getPortRegionCode());
								if (dmPortRegion12 != null) {
									tempDocument.setMaritimeCode(dmPortRegion12.getPortRegionRef());
								}
							} else {
								DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(GeneralDeclaration.class.cast(obj)
										.getPortHarbourCode());
								if (dmPortHarbour != null) {
									tempDocument.setMaritimeCode(dmPortHarbour.getPortRegionCode());
								} else {
									tempDocument.setMaritimeCode("19");
								}
							}
						}
					}
					
					// BinhNT Add 2014-10-10
					try {
						// Gia tri mac dinh neu ben HQMC khong truyen du lieu
						// sang.
						if (tempDocument.getMaritimeCode() == null
								|| (tempDocument.getMaritimeCode() != null && tempDocument.getMaritimeCode().trim().length() == 0)) {
							tempDocument.setMaritimeCode("19");
						}
					} catch (Exception e) {
						
						log.error(e.getMessage());
					}
				}
			} else {
				if (header.getSubject().getType() == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
					tempDocument.setShipDateFrom(FormatData.parseStringToDate(ShipSecurityNotification.class.cast(obj).getETA()));
					
					tempDocument.setGrt(FormatData.convertToDouble(ShipSecurityNotification.class.cast(obj).getGrossTonnage()));
					tempDocument.setUnitGRT(ShipSecurityNotification.class.cast(obj).getGrossTonnageUnit());
					
					tempDocument.setShipAgencyCode(ShipSecurityNotification.class.cast(obj).getTaxCodeOfShipAgent());
					tempDocument.setArrivalShipAgency(ShipSecurityNotification.class.cast(obj).getTaxCodeOfShipAgent());
					tempDocument.setDepartureShipAgency(ShipSecurityNotification.class.cast(obj).getTaxCodeOfShipAgent());
					
					tempDocument.setPortRegionCode(ShipSecurityNotification.class.cast(obj).getPortRegionCode());
					tempDocument.setNameOfShipownersAgents(ShipSecurityNotification.class.cast(obj).getNameOfShipAgent());
					tempDocument.setShipName(ShipSecurityNotification.class.cast(obj).getNameOfShip());
					
					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil.getByPortRegionCode(ShipSecurityNotification.class.cast(obj)
							.getPortRegionCode());
					if (dmPortRegion != null) {
						tempDocument.setMaritimeCode(dmPortRegion.getPortRegionRef());
					} else {
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(ShipSecurityNotification.class.cast(obj)
								.getPortRegionCode());
						if (dmPortWharf != null) {
							DmPortRegion dmPortRegion1 = DmPortRegionLocalServiceUtil.getByPortRegionCode(dmPortWharf.getPortRegionCode());
							if (dmPortRegion1 != null) {
								tempDocument.setMaritimeCode(dmPortRegion1.getPortRegionRef());
							} else {
								tempDocument.setMaritimeCode("19");
							}
						}
					}
					// BinhNT Add 2014-10-10
					try {
						// Gia tri mac dinh neu ben HQMC khong truyen du lieu sang.
						if (tempDocument.getMaritimeCode() == null
								|| (tempDocument.getMaritimeCode() != null && tempDocument.getMaritimeCode().trim().length() == 0)) {
							tempDocument.setMaritimeCode("19");
						}
					} catch (Exception e) {
						
						log.error(e.getMessage());
					}
				} else if (header.getSubject().getType() == MessageType.THONG_BAO_TAU_DEN_CANG
						|| header.getSubject().getType() == MessageType.THONG_BAO_TAU_ROI_CANG
						|| header.getSubject().getType() == MessageType.THONG_BAO_TAU_QUA_CANH) {
					tempDocument.setShipDateFrom(FormatData.parseStringToDate(NoticeOfArrival.class.cast(obj).getTimeOfArrival()));
					if (header.getSubject().getDocumentType() == MessageType.XUAT_CANH
							|| header.getSubject().getDocumentType() == 5
							|| header.getSubject().getDocumentType() == Integer.valueOf(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)
							|| header.getSubject().getDocumentType() == 9
							|| header.getSubject().getDocumentType() == 11
							|| header.getSubject().getDocumentType() == 13) {
						tempDocument.setShipDateTo(FormatData.parseStringToDate(NoticeOfArrival.class.cast(obj).getTimeOfArrival()));
					}
					
					tempDocument.setShipName(NoticeOfArrival.class.cast(obj)
							.getNameOfShip());
					tempDocument.setImo(NoticeOfArrival.class.cast(obj)
							.getIMONumber());
					tempDocument.setCallSign(NoticeOfArrival.class.cast(obj)
							.getCallSign());
					
					tempDocument.setGrt(FormatData.convertToDouble(NoticeOfArrival.class.cast(obj).getGT()));
					tempDocument.setUnitGRT(NoticeOfArrival.class.cast(obj).getGTUNIT());
					tempDocument.setUnitDWT(NoticeOfArrival.class.cast(obj).getDWTUNIT());
					tempDocument.setDwt(FormatData.convertToDouble(NoticeOfArrival.class.cast(obj).getDWT()));
					
					tempDocument.setShipAgencyCode(NoticeOfArrival.class.cast(obj).getTaxCodeOfShipownersAgents());
					tempDocument.setArrivalShipAgency(NoticeOfArrival.class.cast(obj).getTaxCodeOfShipownersAgents());
					tempDocument.setDepartureShipAgency(NoticeOfArrival.class.cast(obj).getTaxCodeOfShipownersAgents());
					
					tempDocument.setNameOfShipownersAgents(NoticeOfArrival.class.cast(obj).getNameOfShipownersAgents());
					tempDocument.setShipName(NoticeOfArrival.class.cast(obj).getNameOfShip());
					
					if (NoticeOfArrival.class.cast(obj).getPortOfArrivalCode() != null) {
						tempDocument.setPortCode(NoticeOfArrival.class.cast(obj).getPortOfArrivalCode());
					}
					
					if (NoticeOfArrival.class.cast(obj).getPortRegionCode() != null) {
						tempDocument.setPortRegionCode(NoticeOfArrival.class.cast(obj).getPortRegionCode());
					}
					
					if (NoticeOfArrival.class.cast(obj).getLastPortOfCallCode() != null) {
						tempDocument.setLastPortCode(NoticeOfArrival.class.cast(obj).getLastPortOfCallCode());
					}
					
					// BinhNT edit with Hong Son, 10-10-2014, 22h33
					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil.getByPortRegionCode(NoticeOfArrival.class.cast(obj).getPortRegionCode());
					if (dmPortRegion != null) {
						tempDocument.setMaritimeCode(dmPortRegion.getPortRegionRef());
					} else {
						DmPort dmPort = DmPortLocalServiceUtil.getByPortCode(NoticeOfArrival.class.cast(obj).getPortOfArrivalCode());
						if (dmPort != null) {
							tempDocument.setMaritimeCode(dmPort.getLoCode());
						} else {
							DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(NoticeOfArrival.class.cast(obj)
									.getPortRegionCode());
							if (dmPortWharf != null) {
								DmPortRegion dmPortRegion12 = DmPortRegionLocalServiceUtil.getByPortRegionCode(dmPortWharf.getPortRegionCode());
								if (dmPortRegion12 != null) {
									tempDocument.setMaritimeCode(dmPortRegion12.getPortRegionRef());
								}
							} else {
								DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(NoticeOfArrival.class.cast(obj)
										.getPortHarbourCode());
								if (dmPortHarbour != null) {
									tempDocument.setMaritimeCode(dmPortHarbour.getPortRegionCode());
								} else {
									tempDocument.setMaritimeCode("19");
								}
							}
						}
					}
					
					// BinhNT Add 2014-10-10
					try {
						// Gia tri mac dinh neu ben HQMC khong truyen du lieu
						// sang.
						if (tempDocument.getMaritimeCode() == null
								|| (tempDocument.getMaritimeCode() != null && tempDocument.getMaritimeCode().trim().length() == 0)) {
							tempDocument.setMaritimeCode("19");
						}
					} catch (Exception e) {
						
						log.error(e.getMessage());
					}
				} else if (header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG
						|| header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH) {
					tempDocument.setShipDateFrom(FormatData.parseStringToDate(ConfirmationOfArrival.class.cast(obj).getTimeOfArrival()));
					
					if (ConfirmationOfArrival.class.cast(obj).getPortOfArrivalCode() != null) {
						tempDocument.setPortCode(ConfirmationOfArrival.class.cast(obj).getPortOfArrivalCode());
					}
					
					if (ConfirmationOfArrival.class.cast(obj).getPortRegionCode() != null) {
						tempDocument.setPortRegionCode(ConfirmationOfArrival.class.cast(obj).getPortRegionCode());
					}
					
					tempDocument.setShipName(ConfirmationOfArrival.class.cast(obj).getNameOfShip());
					
					// BinhNT edit with Hong Son, 10-10-2014, 22h33
					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil.getByPortRegionCode(ConfirmationOfArrival.class.cast(obj).getPortRegionCode());
					if (dmPortRegion != null) {
						tempDocument.setMaritimeCode(dmPortRegion.getPortRegionRef());
					} else {
						DmPort dmPort = DmPortLocalServiceUtil.getByPortCode(ConfirmationOfArrival.class.cast(obj).getPortOfArrivalCode());
						if (dmPort != null) {
							tempDocument.setMaritimeCode(dmPort.getLoCode());
						} else {
							DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(ConfirmationOfArrival.class.cast(obj)
									.getPortRegionCode());
							if (dmPortWharf != null) {
								DmPortRegion dmPortRegion12 = DmPortRegionLocalServiceUtil.getByPortRegionCode(dmPortWharf.getPortRegionCode());
								if (dmPortRegion12 != null) {
									tempDocument.setMaritimeCode(dmPortRegion12.getPortRegionRef());
								}
							} else {
								DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(ConfirmationOfArrival.class.cast(obj)
										.getPortHarbourCode());
								if (dmPortHarbour != null) {
									tempDocument.setMaritimeCode(dmPortHarbour.getPortRegionCode());
								} else {
									tempDocument.setMaritimeCode("19");
								}
							}
						}
					}
					
					// BinhNT Add 2014-10-10
					try {
						// Gia tri mac dinh neu ben HQMC khong truyen du lieu
						// sang.
						if (tempDocument.getMaritimeCode() == null
								|| (tempDocument.getMaritimeCode() != null && tempDocument.getMaritimeCode().trim().length() == 0)) {
							tempDocument.setMaritimeCode("19");
						}
					} catch (Exception e) {
						
						log.error(e.getMessage());
					}
				}
			}
			
			tempDocument.setLastModifiedDate(new Date());
			
			if (liTempDocuments == null || liTempDocuments.size() == 0) {
				
				if (tempDocument.getDocumentTypeCode() == null || tempDocument.getDocumentTypeCode().trim().length() == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_NHAP_CANH)) == 0) {
					tempDocument.setDocumentTypeCode(MessageType.LOAT_THU_TUC_NHAP_CANH);
					
				} else if (tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_XUAT_CANH)) == 0) {
					
					tempDocument.setDocumentTypeCode(MessageType.LOAT_THU_TUC_XUAT_CANH);
					
				} else if (tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_QUA_CANH)) == 0) {
					
					tempDocument.setDocumentTypeCode(MessageType.LOAT_THU_TUC_QUA_CANH);
					
				}
				
				if (tempDocument.getDocumentTypeCode() == null || tempDocument.getDocumentTypeCode().trim().length() == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_NHAP_CANH)) == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(MessageType.TAU_VAO)) == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(MessageType.TAU_VAO_PTTND)) == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(MessageType.NHAP_CANG_DAU_KHI)) == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(MessageType.VAO_CANG_DAU_KHI)) == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(MessageType.CHUYEN_CANG_VAO)) == 0) {
					
					tempDocument.setShipPosition(PageType.POSITION_SHIP_NHAP_CANH);
				} else if (tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_XUAT_CANH)) == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(MessageType.TAU_RA)) == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(MessageType.TAU_RA_PTTND)) == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(MessageType.XUAT_CANG_DAU_KHI)) == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(MessageType.ROI_CANG_DAU_KHI)) == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(MessageType.CHUYEN_CANG_ROI)) == 0) {
					
					
					tempDocument.setShipPosition(PageType.POSITION_SHIP_XUAT_CANH);
				} else if (tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_QUA_CANH)) == 0) {
					
					
					tempDocument.setShipPosition(PageType.POSITION_SHIP_NHAP_CANH);
				}
				
				tempDocument.setCreatedDate(FormatData.parseStringToDate(header.getSubject().getSendDate()));
				tempDocument.setUpgradeVersion(1);
				TempDocumentLocalServiceUtil.addTempDocument(tempDocument);
			} else {
				TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
			}
			return true;
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return false;
	}
	
	public static synchronized boolean insert2InlanTempDocument(TempDocument tempDocument, Header header, Object obj) {
		/*try {
			List<TempDocument> liTempDocuments = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear(
					tempDocument.getDocumentName(), tempDocument.getDocumentYear());
			
			if (liTempDocuments != null && liTempDocuments.size() > 0) {
				tempDocument = liTempDocuments.get(0);
			} else {
				tempDocument.setRequestState(TrangThaiHoSo.TEMP_DOCUMENT_CHO_TIEP_NHAN);
			}
			
			tempDocument.setPreDocumentName(String.valueOf(header.getSubject().getPreReference()));
			
			if (header.getSubject().getType() == MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST) {
				if (obj instanceof InlandGeneralDeclaration) {
					InlandGeneralDeclaration inlandGeneralDeclaration = InlandGeneralDeclaration.class.cast(obj);
					tempDocument.setShipDateFrom(FormatData.parseStringToDate(inlandGeneralDeclaration.getDateOfArrival()));
					
					if (header.getSubject().getDocumentType() == MessageType.TAU_RA || 
							header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
						tempDocument.setShipDateTo(FormatData.parseStringToDate(inlandGeneralDeclaration.getDateOfArrival()));
						
					}
					
					tempDocument.setShipAgencyCode(inlandGeneralDeclaration.getTaxCodeOfShipownersAgents());
					tempDocument.setArrivalShipAgency(inlandGeneralDeclaration.getTaxCodeOfShipownersAgents());
					tempDocument.setDepartureShipAgency(inlandGeneralDeclaration.getTaxCodeOfShipownersAgents());
					
					// BinhNT edit with Hong Son, 10-10-2014, 23h
					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil.getByPortRegionCode(inlandGeneralDeclaration.getPortRegionCode());
					if (dmPortRegion != null) {
						tempDocument.setMaritimeCode(dmPortRegion.getPortRegionRef());
					} else {
						DmPort dmPort = DmPortLocalServiceUtil.getByPortCode(inlandGeneralDeclaration.getPortOfArrivalCode());
						if (dmPort != null) {
							tempDocument.setMaritimeCode(dmPort.getLoCode());
						} else {
							DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(inlandGeneralDeclaration.getPortRegionCode());
							if (dmPortWharf != null) {
								DmPortRegion dmPortRegion12 = DmPortRegionLocalServiceUtil.getByPortRegionCode(dmPortWharf.getPortRegionCode());
								if (dmPortRegion12 != null) {
									tempDocument.setMaritimeCode(dmPortRegion12.getPortRegionRef());
								}
							} else {
								DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(inlandGeneralDeclaration
										.getPortHarbourCode());
								if (dmPortHarbour != null) {
									tempDocument.setMaritimeCode(dmPortHarbour.getPortRegionCode());
								} else {
									tempDocument.setMaritimeCode("19");
								}
							}
						}
					}
					
					// BinhNT Add 2014-10-10
					try {
						// Gia tri mac dinh neu ben HQMC khong truyen du lieu
						// sang.
						if (tempDocument.getMaritimeCode() == null
								|| (tempDocument.getMaritimeCode() != null && tempDocument.getMaritimeCode().trim().length() == 0)) {
							tempDocument.setMaritimeCode("19");
						}
					} catch (Exception e) {
						
						log.error(e.getMessage());
					}
				}else if (obj instanceof PTTNDGeneralDeclaration) {
					PTTNDGeneralDeclaration inlandGeneralDeclaration = PTTNDGeneralDeclaration.class.cast(obj);
					tempDocument.setShipDateFrom(FormatData.parseStringToDate(inlandGeneralDeclaration.getDateOfArrival()));
					tempDocument.setStateCode("VN");
					if (header.getSubject().getDocumentType() == MessageType.TAU_RA || 
							header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
						tempDocument.setShipDateTo(FormatData.parseStringToDate(inlandGeneralDeclaration.getDateOfArrival()));
						
					}
					if(header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND || 
							header.getSubject().getDocumentType() == MessageType.TAU_VAO_PTTND){
						 tempDocument.setGrt(FormatData.convertToDouble(inlandGeneralDeclaration.getGrossTonnage()));
						 tempDocument.setDwt(FormatData.convertToDouble(inlandGeneralDeclaration.getNetTonnage()));
						 tempDocument.setCallSign(inlandGeneralDeclaration.getCallSign());
						 tempDocument.setShipTypeCode(inlandGeneralDeclaration.getShipTypeCode());
						 tempDocument.setShipCaptain(inlandGeneralDeclaration.getNameOfMaster());
						 tempDocument.setPortRegionCode(inlandGeneralDeclaration.getPortRegionCode());
						 tempDocument.setPortCode(inlandGeneralDeclaration.getPortOfArrivalCode());
						 tempDocument.setLastPortCode(inlandGeneralDeclaration.getLastPortOfCallCode());
						 tempDocument.setShipOwnerCode(inlandGeneralDeclaration.getBriefParticularsOfVoyage());
					}
					
					tempDocument.setShipAgencyCode(inlandGeneralDeclaration.getTaxCodeOfShipownersAgents());
					tempDocument.setArrivalShipAgency(inlandGeneralDeclaration.getTaxCodeOfShipownersAgents());
					tempDocument.setDepartureShipAgency(inlandGeneralDeclaration.getTaxCodeOfShipownersAgents());
					
					// BinhNT edit with Hong Son, 10-10-2014, 23h
					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil.getByPortRegionCode(inlandGeneralDeclaration.getPortRegionCode());
					if (dmPortRegion != null) {
						tempDocument.setMaritimeCode(dmPortRegion.getPortRegionRef());
					} else {
						DmPort dmPort = DmPortLocalServiceUtil.getByPortCode(inlandGeneralDeclaration.getPortOfArrivalCode());
						if (dmPort != null) {
							tempDocument.setMaritimeCode(dmPort.getLoCode());
						} else {
							DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(inlandGeneralDeclaration.getPortRegionCode());
							if (dmPortWharf != null) {
								DmPortRegion dmPortRegion12 = DmPortRegionLocalServiceUtil.getByPortRegionCode(dmPortWharf.getPortRegionCode());
								if (dmPortRegion12 != null) {
									tempDocument.setMaritimeCode(dmPortRegion12.getPortRegionRef());
								}
							} else {
								DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(inlandGeneralDeclaration
										.getPortHarbourCode());
								if (dmPortHarbour != null) {
									tempDocument.setMaritimeCode(dmPortHarbour.getPortRegionCode());
								} else {
									tempDocument.setMaritimeCode("19");
								}
							}
						}
					}
					
					// BinhNT Add 2014-10-10
					try {
						// Gia tri mac dinh neu ben HQMC khong truyen du lieu
						// sang.
						if (tempDocument.getMaritimeCode() == null
								|| (tempDocument.getMaritimeCode() != null && tempDocument.getMaritimeCode().trim().length() == 0)) {
							tempDocument.setMaritimeCode("19");
						}
					} catch (Exception e) {
						
						log.error(e.getMessage());
					}
				}
			} else {
				if (header.getSubject().getType() == MessageType.BAN_KHAI_AN_NINH_TAU_BIEN) {
					tempDocument.setShipDateFrom(FormatData.parseStringToDate(ShipSecurityNotification.class.cast(obj).getETA()));
					
					tempDocument.setGrt(FormatData.convertToDouble(ShipSecurityNotification.class.cast(obj).getGrossTonnage()));
					tempDocument.setUnitGRT(ShipSecurityNotification.class.cast(obj).getGrossTonnageUnit());
					
					tempDocument.setShipAgencyCode(ShipSecurityNotification.class.cast(obj).getTaxCodeOfShipAgent());
					tempDocument.setArrivalShipAgency(ShipSecurityNotification.class.cast(obj).getTaxCodeOfShipAgent());
					tempDocument.setDepartureShipAgency(ShipSecurityNotification.class.cast(obj).getTaxCodeOfShipAgent());
					
					tempDocument.setPortRegionCode(ShipSecurityNotification.class.cast(obj).getPortRegionCode());
					
					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil.getByPortRegionCode(ShipSecurityNotification.class.cast(obj)
							.getPortRegionCode());
					if (dmPortRegion != null) {
						tempDocument.setMaritimeCode(dmPortRegion.getPortRegionRef());
					} else {
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(ShipSecurityNotification.class.cast(obj)
								.getPortRegionCode());
						if (dmPortWharf != null) {
							DmPortRegion dmPortRegion1 = DmPortRegionLocalServiceUtil.getByPortRegionCode(dmPortWharf.getPortRegionCode());
							if (dmPortRegion1 != null) {
								tempDocument.setMaritimeCode(dmPortRegion1.getPortRegionRef());
							} else {
								tempDocument.setMaritimeCode("19");
							}
						}
					}
					// BinhNT Add 2014-10-10
					try {
						// Gia tri mac dinh neu ben HQMC khong truyen du lieu sang.
						if (tempDocument.getMaritimeCode() == null
								|| (tempDocument.getMaritimeCode() != null && tempDocument.getMaritimeCode().trim().length() == 0)) {
							tempDocument.setMaritimeCode("19");
						}
					} catch (Exception e) {
						
						log.error(e.getMessage());
					}
				} else if (header.getSubject().getType() == MessageType.THONG_BAO_TAU_DEN_CANG
						|| header.getSubject().getType() == MessageType.THONG_BAO_TAU_ROI_CANG
						|| header.getSubject().getType() == MessageType.XAC_BAO_TAU_DEN_CANG
						|| header.getSubject().getType() == MessageType.THONG_BAO_TAU_QUA_CANH
						|| header.getSubject().getType() == MessageType.XAC_BAO_TAU_QUA_CANH) {
					
					InlandNoticeOfArrival inlandNoticeOfArrival = InlandNoticeOfArrival.class.cast(obj);
					tempDocument.setShipDateFrom(FormatData.parseStringToDate(inlandNoticeOfArrival.getTimeOfArrival()));
					if (header.getSubject().getDocumentType() == MessageType.XUAT_CANH) {
						tempDocument.setShipDateTo(FormatData.parseStringToDate(inlandNoticeOfArrival.getTimeOfArrival()));
					}
					
					tempDocument.setShipAgencyCode(inlandNoticeOfArrival.getTaxCodeOfShipownersAgents());
					tempDocument.setArrivalShipAgency(inlandNoticeOfArrival.getTaxCodeOfShipownersAgents());
					tempDocument.setDepartureShipAgency(inlandNoticeOfArrival.getTaxCodeOfShipownersAgents());
					
					if (inlandNoticeOfArrival.getPortOfArrivalCode() != null) {
						tempDocument.setPortCode(inlandNoticeOfArrival.getPortOfArrivalCode());
					}
					
					if (inlandNoticeOfArrival.getPortRegionCode() != null) {
						tempDocument.setPortRegionCode(inlandNoticeOfArrival.getPortRegionCode());
					}
					
					if (inlandNoticeOfArrival.getLastPortOfCallCode() != null) {
						tempDocument.setLastPortCode(inlandNoticeOfArrival.getLastPortOfCallCode());
					}
					
					// BinhNT edit with Hong Son, 10-10-2014, 22h33
					DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil.getByPortRegionCode(inlandNoticeOfArrival.getPortRegionCode());
					if (dmPortRegion != null) {
						tempDocument.setMaritimeCode(dmPortRegion.getPortRegionRef());
					} else {
						DmPort dmPort = DmPortLocalServiceUtil.getByPortCode(inlandNoticeOfArrival.getPortOfArrivalCode());
						if (dmPort != null) {
							tempDocument.setMaritimeCode(dmPort.getLoCode());
						} else {
							DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(inlandNoticeOfArrival.getPortRegionCode());
							if (dmPortWharf != null) {
								DmPortRegion dmPortRegion12 = DmPortRegionLocalServiceUtil.getByPortRegionCode(dmPortWharf.getPortRegionCode());
								if (dmPortRegion12 != null) {
									tempDocument.setMaritimeCode(dmPortRegion12.getPortRegionRef());
								}
							} else {
								DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(inlandNoticeOfArrival
										.getPortHarbourCode());
								if (dmPortHarbour != null) {
									tempDocument.setMaritimeCode(dmPortHarbour.getPortRegionCode());
								} else {
									tempDocument.setMaritimeCode("19");
								}
							}
						}
					}
					
					// BinhNT Add 2014-10-10
					try {
						// Gia tri mac dinh neu ben HQMC khong truyen du lieu sang.
						if (tempDocument.getMaritimeCode() == null
								|| (tempDocument.getMaritimeCode() != null && tempDocument.getMaritimeCode().trim().length() == 0)) {
							tempDocument.setMaritimeCode("19");
						}
					} catch (Exception e) {
						
						log.error(e.getMessage());
					}
				}
			}
			
			tempDocument.setLastModifiedDate(new Date());
			
			// TODO inland create ho so
			if (liTempDocuments == null || liTempDocuments.size() == 0) {
				
				if (tempDocument.getDocumentTypeCode() == null || tempDocument.getDocumentTypeCode().trim().length() == 0
						|| tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_NHAP_CANH)) == 0) {
					
					tempDocument.setDocumentTypeCode(MessageType.LOAT_THU_TUC_NHAP_CANH);
					tempDocument.setShipPosition(PageType.POSITION_SHIP_NHAP_CANH);
					
				} else if (tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_XUAT_CANH)) == 0) {
					
					tempDocument.setDocumentTypeCode(MessageType.LOAT_THU_TUC_XUAT_CANH);
					tempDocument.setShipPosition(PageType.POSITION_SHIP_XUAT_CANH);
					
				} else if (tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_QUA_CANH)) == 0) {
					
					tempDocument.setDocumentTypeCode(MessageType.LOAT_THU_TUC_QUA_CANH);
					tempDocument.setShipPosition(PageType.POSITION_SHIP_NHAP_CANH);
					
				} else if (tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_VAO_CANG)) == 0) {
					
					tempDocument.setDocumentTypeCode(MessageType.LOAT_THU_TUC_VAO_CANG);
					tempDocument.setShipPosition(PageType.POSITION_SHIP_VAO_CANG);
					
				} else if (tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_RA_CANG)) == 0) {
					
					tempDocument.setDocumentTypeCode(MessageType.LOAT_THU_TUC_ROI_CANG);
					tempDocument.setShipPosition(PageType.POSITION_SHIP_RA_CANG);
				} else if (tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_VAO_CANG_PTTND)) == 0) {
					
					tempDocument.setDocumentTypeCode(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND);
					tempDocument.setShipPosition(PageType.POSITION_SHIP_VAO_CANG);
					
				} else if (tempDocument.getDocumentTypeCode().trim().compareTo(String.valueOf(PageType.DOCUMENT_TYPE_RA_CANG_PTTND)) == 0) {
					
					tempDocument.setDocumentTypeCode(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND);
					tempDocument.setShipPosition(PageType.POSITION_SHIP_RA_CANG);
				}
				tempDocument.setCreatedDate(FormatData.parseStringToDate(header.getSubject().getSendDate()));
				
				tempDocument.setUpgradeVersion(1);
				TempDocumentLocalServiceUtil.addTempDocument(tempDocument);
			} else {
				
				TempDocumentLocalServiceUtil.updateTempDocument(tempDocument);
			}
			return true;
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}*/
		return false;
	}
	
	public String createReturnContentAfterParserDataReceiverFromNSW(Header header) {
		Messsage messsage = new Messsage(String.valueOf(header.getReference().getVersion()), UUID.randomUUID().toString(),
				header.getFrom().getName(), String.valueOf(header.getFrom().getIdentity()), header.getTo().getName(), String.valueOf(header.getTo()
						.getIdentity()), String.valueOf(header.getSubject().getDocumentType()), FormatData.formatIntToString(header.getSubject()
						.getType()), String.valueOf(MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST), String.valueOf(header
						.getSubject().getReference()), String.valueOf(header.getSubject().getPreReference()), String.valueOf(header.getSubject()
						.getDocumentYear()), header.getSubject().getSendDate());
		
		String xmlResult = MessageFactory.createMessageRequest(messsage);
		
		xmlResult = xmlResult.replace(MessageFactory.NOI_DUNG_TRA_VE, "<ReceiveDate>" + FormatData.parseDateToTring(new Date()) + "</ReceiveDate>");
		log.info("==createReturnContentAfterParserDataReceiverFromNSW===Thong tin tra ve HQMC===");
//		log.info(xmlResult);
		return xmlResult;
	}
	
	// dung.le handsome clone
	public String createReturnContentAfterParserDataReceiverFromNSWInland(Header header) {
		
		MesssageInland messsageinland = new MesssageInland(
			String.valueOf(header.getReference().getVersion()),
			UUID.randomUUID().toString(),
			header.getTo().getName(),
			String.valueOf(header.getTo().getIdentity()),
			header.getFrom().getName(),
			String.valueOf(header.getFrom().getIdentity()),
			String.valueOf(header.getSubject().getDocumentType()),
			FormatData.formatIntToString(header.getSubject().getType()),
			String.valueOf(MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_RECEIVE_REQUEST),
			String.valueOf(header.getSubject().getReference()), 
			String.valueOf(header.getSubject().getPreReference()), 
			String.valueOf(header.getSubject().getDocumentYear()), 
			header.getSubject().getSendDate(), 
			String.valueOf(header.getTo().getCountryCode()), String.valueOf(header
				.getTo().getMinistryCode()), String.valueOf(header
				.getTo().getOrganizationCode()), String.valueOf(header
				.getTo().getUnitCode()), String.valueOf(header
				.getFrom().getCountryCode()), String.valueOf(header
				.getFrom().getMinistryCode()), String.valueOf(header
				.getFrom().getOrganizationCode()), String
				.valueOf(header.getFrom().getUnitCode()));
		
		String xmlResult = MessageFactory.createMessageRequestInland(messsageinland);
		
		xmlResult = xmlResult.replace(MessageFactory.NOI_DUNG_TRA_VE, "<ReceiveDate>" + FormatData.parseDateToTring(new Date()) + "</ReceiveDate>");
		log.info("==createReturnContentAfterParserDataReceiverInland===Thong tin tra ve HQMC===");
		log.info(xmlResult);
		return xmlResult;
	}
	
	public static String createReturnContentAfterValidationErrorFromNSW(Header header, String lyDo) {
		String xmlResult = "";
		if (lyDo == null || lyDo.length() == 0) {
			lyDo = "Validation error.";
		}
		header.getSubject().setFunction(String.valueOf(MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_ERROR_VALIDATION));
		/*if (String.valueOf(header.getSubject().getDocumentType()).equals(MessageType.LOAT_THU_TUC_ROI_CANG)
				|| String.valueOf(header.getSubject().getDocumentType()).equals(MessageType.LOAT_THU_TUC_VAO_CANG)
				|| String.valueOf(header.getSubject().getDocumentType()).equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)
				|| String.valueOf(header.getSubject().getDocumentType()).equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
			
			MesssageInland messsageinland = new MesssageInland(String.valueOf(header.getReference().getVersion()), UUID.randomUUID().toString(),
					header.getTo().getName(), String.valueOf(header.getTo().getIdentity()), header.getFrom().getName(), String.valueOf(header.getFrom()
							.getIdentity()), String.valueOf(header.getSubject().getDocumentType()), FormatData.formatIntToString(header.getSubject()
							.getType()), String.valueOf(MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_ERROR_VALIDATION), String.valueOf(header
							.getSubject().getReference()), String.valueOf(header.getSubject().getPreReference()), String.valueOf(header.getSubject().getDocumentYear()), header.getSubject().getSendDate(),
							String.valueOf(header.getTo().getCountryCode()), String.valueOf(header.getTo().getMinistryCode()), String.valueOf(header.getTo().getOrganizationCode()), String.valueOf(header.getTo().getUnitCode()),
							String.valueOf(header.getFrom().getCountryCode()), String.valueOf(header.getFrom().getMinistryCode()), String.valueOf(header.getFrom().getOrganizationCode()), String.valueOf(header.getFrom().getUnitCode())
							);
					
			xmlResult = MessageFactory.createMessageRequestInland(messsageinland);
		} else  {*/
			Messsage messsage = new Messsage(String.valueOf(header.getReference().getVersion()), UUID.randomUUID().toString(),
					header.getTo().getName(), String.valueOf(header.getTo().getIdentity()), header.getFrom().getName(), String.valueOf(header.getFrom()
							.getIdentity()), String.valueOf(header.getSubject().getDocumentType()), FormatData.formatIntToString(header.getSubject()
							.getType()), String.valueOf(MessageType.FUNCTION_TYPE_MESSAGETYPE_RESPONSE_WHEN_ERROR_VALIDATION), String.valueOf(header
							.getSubject().getReference()), String.valueOf(header.getSubject().getPreReference()), String.valueOf(header.getSubject()
							.getDocumentYear()), header.getSubject().getSendDate());
			
			xmlResult = MessageFactory.createMessageRequest(messsage);
		/*}*/

		xmlResult = xmlResult.replace(MessageFactory.NOI_DUNG_TRA_VE,
				CreateMessageFactory.createMessageRejectSystem(header.getReference().getMessageId(), lyDo, "System", "System", new Date()));
		log.info("==createReturnContentAfterValidationErrorFromNSW===Thong tin tra ve HQMC===");
		return xmlResult;
	}
	
	public static String createContentSendFromBGTVTToNSW(Header header, String xmlInnerTagContent) {
		String xmlResult = null;
		try {
			
			String ref_version = String.valueOf(header.getReference().getVersion());
			String ref_messageId = UUID.randomUUID().toString();
			String frm_name = header.getFrom().getName();
			String frm_identity = String.valueOf(header.getFrom().getIdentity());
			String to_name = header.getTo().getName();
			
			String to_identity = String.valueOf(header.getTo().getIdentity());
			String documentType = String.valueOf(header.getSubject().getDocumentType());
			String type = FormatData.formatIntToString(header.getSubject().getType());
			String function = String.valueOf(header.getSubject().getFunction());
			String reference = String.valueOf(header.getSubject().getReference());
			String preReference = String.valueOf(header.getSubject().getPreReference());
			String documentYear = String.valueOf(header.getSubject().getDocumentYear());
			String sendDate = header.getSubject().getSendDate();
			
			Messsage messsage = new Messsage(ref_version, ref_messageId, frm_name, frm_identity, to_name, to_identity, documentType, type, function,
					reference, preReference, documentYear, sendDate);
			
			xmlResult = MessageFactory.createMessageRequest(messsage);
			xmlResult = xmlResult.replace(MessageFactory.NOI_DUNG_TRA_VE, xmlInnerTagContent);
			
//			log.info("==createContentSendFromBGTVTToNSW===Thong tin BGTVT gui sang HQMC===");
			// log.info(xmlResult);
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
		return xmlResult;
	}
	
	public String createContentSendFromBGTVTToNSWInland(Header header, String xmlInnerTagContent) {
		return createContentSendFromBGTVTToNSW(header, xmlInnerTagContent);
	}
	
	public String replaceXML(String data) {
		try {
			//log.info("----replaceXML---" + data);
			if(data.length()<1)return StringPool.BLANK;
			String dataPrefix = data.substring(0, data.indexOf("?>") + 2).toLowerCase();
			if (dataPrefix.contains("version") && dataPrefix.contains("encoding")) {
				return data.substring(data.indexOf("?>") + 2, data.length());
			}
			return data;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return data;
	}
	
	public static Header createHeader(TempDocument tempDocument, int documentType, String function, int type, String userName,
			InterfaceRequest interfaceRequest) {
		try {
			Header header = new Header();
			Subject subject = new Subject();
			
			if (Validator.isNotNull(tempDocument)) {
				int typeCode = 0;
				if (tempDocument.getDocumentTypeCode().equals("NC")) {
					typeCode = 1;
				} else if (tempDocument.getDocumentTypeCode().equals("QC")) {
					typeCode = 2;
				} else if (tempDocument.getDocumentTypeCode().equals("XC")) {
					typeCode = 3;
				} else {
					typeCode = Integer.valueOf(tempDocument.getDocumentTypeCode());
				}
				subject.setDocumentType(typeCode);
			} else {
				subject.setDocumentType(documentType);
			}
			
			if (tempDocument != null) {
				subject.setDocumentYear(tempDocument.getDocumentYear());
				subject.setPreReference(tempDocument.getDocumentName());
				subject.setReference(tempDocument.getDocumentName());
			}
			
			subject.setFunction(function);
			subject.setSendDate(FormatData.parseDateToTring(new Date()));
			subject.setType(type);
			
			From from = new From();
			from.setIdentity("BGTVT");// chua biet lay dau
			from.setName(userName);
			
			To to = new To();
			to.setIdentity("BTC");
			
			Reference ref = new Reference();
			ref.setMessageId("BGTVT" + Long.toString(System.currentTimeMillis()));
			
			if (interfaceRequest != null) {
				to.setName(interfaceRequest.getSenderName());
				ref.setVersion(interfaceRequest.getRequestVersion());
			} else {
				to.setName("System");
				ref.setVersion("1.0");
			}
			
			header.setFrom(from);
			header.setTo(to);
			header.setReference(ref);
			header.setSubject(subject);
			
			return header;
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return null;
	}
	
	public static Header createHeaderInland(TempDocument tempDocument, int documentType, String function, int type, String userName,
			InterfaceRequest interfaceRequest) {
		try {
			Header header = new Header();
			Subject subject = new Subject();
			subject.setDocumentType(documentType);
			
			if (tempDocument != null) {
				subject.setDocumentYear(tempDocument.getDocumentYear());
				subject.setPreReference(tempDocument.getDocumentName());
				subject.setReference(tempDocument.getDocumentName());
			}
			
			subject.setFunction(function);
			subject.setSendDate(FormatData.parseDateToTring(new Date()));
			subject.setType(type);
			
			From from = new From();
			from.setIdentity("BGTVT");// chua biet lay dau
			from.setName(userName);
			from.setCountryCode("VN");
			from.setMinistryCode("BGTVT");
			from.setOrganizationCode("CHH");
			from.setUnitCode("CHH");
			
			To to = new To();
			to.setIdentity("BTC");
			to.setCountryCode("VN");
			to.setMinistryCode("BTC");
			to.setOrganizationCode("BTC");
			to.setUnitCode("BTC");
			
			Reference ref = new Reference();
			ref.setMessageId("BGTVT" + Long.toString(System.currentTimeMillis()));
			
			if (interfaceRequest != null) {
				to.setName(interfaceRequest.getSenderName());
				
				ref.setVersion(interfaceRequest.getRequestVersion());
			} else {
				to.setName("System");
				ref.setVersion("1.0");
			}
			
			header.setFrom(from);
			header.setTo(to);
			header.setReference(ref);
			header.setSubject(subject);
			
			return header;
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return null;
	}
	
	public static Header createHeaderInlandSMS(TempDocument tempDocument, int documentType, String function, int type, String userName,
			InterfaceRequest interfaceRequest) {
		try {
			Header header = new Header();
			Subject subject = new Subject();
			subject.setDocumentType(documentType);
			
			if (tempDocument != null) {
				subject.setDocumentYear(tempDocument.getDocumentYear());
				subject.setPreReference(tempDocument.getDocumentName());
				subject.setReference(tempDocument.getDocumentName());
			}
			
			subject.setFunction(function);
			subject.setSendDate(FormatData.parseDateToTring(new Date()));
			subject.setType(type);
			
			From from = new From();
			from.setIdentity("BGTVT");
			from.setName(userName);
			from.setCountryCode("VN");
			from.setMinistryCode("BGTVT");
			from.setOrganizationCode("CHHVN");
			from.setUnitCode("CHHVN");
			
			To to = new To();
			to.setIdentity("SMS");
			to.setCountryCode("VN");
			to.setMinistryCode("BGTVT");
			to.setOrganizationCode(tempDocument.getCallSign());
			if (tempDocument.getShipOwnerCode().length() > 0 )
			{
				to.setUnitCode(tempDocument.getShipOwnerCode());
			}else
			{
				to.setUnitCode("BTC");
			}
			
			to.setName(tempDocument.getUserCreated());
			
			Reference ref = new Reference();
			ref.setMessageId("BGTVT" + Long.toString(System.currentTimeMillis()));
			
			if (interfaceRequest != null) {
				//to.setName(interfaceRequest.getSenderName());
				
				ref.setVersion(interfaceRequest.getRequestVersion());
			} else {
				//to.setName("System");
				ref.setVersion("1.0");
			}
			
			header.setFrom(from);
			header.setTo(to);
			header.setReference(ref);
			header.setSubject(subject);
			
			return header;
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return null;
	}

	public static Header createHeaderSynchronization(int documentType, String messagefunction, int messagetype, String maritimeCode) {
		
		String fromIdentity = "BGTVT";
		String fromName = "---";
		
		if(Validator.isNotNull(maritimeCode)) {
			//TODO: CHo truong hop dac biet neu maritimeCode co ky tu @ dau tien thi bo di ky tu @
			if(maritimeCode.indexOf("@") == 0) {
				maritimeCode = maritimeCode.substring(1, maritimeCode.length()); 
			}
			
			try {
				DmMaritime maritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode);
				
				if(maritime != null) {
					fromIdentity = maritime.getMaritimeReference();
					fromName =  maritime.getMaritimeNameVN();
				}
			} catch(Exception e) {
				log.error(e.getMessage());
			}
		}
		
		try {
			Header header = new Header();
			Subject subject = new Subject();
			subject.setDocumentType(documentType);
			
			Date date = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			
			subject.setDocumentYear(year);
			subject.setPreReference(messagetype);
			subject.setReference(messagetype);
			
			subject.setFunction(messagefunction);
			subject.setSendDate(FormatData.parseDateToTring(new Date()));
			subject.setType(messagetype);
			
			From from = new From();
			from.setIdentity(fromIdentity);
			from.setName(fromName);
			
			To to = new To();
			to.setIdentity("NSW");
			to.setName("NSW");
			
			Reference ref = new Reference();
			ref.setMessageId("BGTVT" + Long.toString(System.currentTimeMillis()));
			
			ref.setVersion("1.0");
			
			header.setFrom(from);
			header.setTo(to);
			header.setReference(ref);
			header.setSubject(subject);
			
			return header;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	
public static Header createHeaderSynchronization(String toIdentity, int documentType, String messagefunction, int messagetype, String maritimeCode) {
		
		String fromName = "BGTVT";
		String fromIdentity = maritimeCode;
		
		if(Validator.isNotNull(maritimeCode)) {
			//TODO: CHo truong hop dac biet neu maritimeCode co ky tu @ dau tien thi bo di ky tu @
			if(maritimeCode.indexOf("@") == 0) {
				maritimeCode = maritimeCode.substring(1, maritimeCode.length()); 
			}
			/*
			try {
				DmMaritime maritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode);
				
				if(maritime != null) {
					fromIdentity = maritime.getMaritimeReference();
					fromName =  maritime.getMaritimeNameVN();
				}
			} catch(Exception e) {
				log.error(e.getMessage());
			}*/
		}
		
		try {
			Header header = new Header();
			Subject subject = new Subject();
			subject.setDocumentType(documentType);
			
			Date date = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			
			subject.setDocumentYear(year);
			subject.setPreReference(messagetype);
			subject.setReference(messagetype);
			
			subject.setFunction(messagefunction);
			subject.setSendDate(FormatData.parseDateToTring(new Date()));
			subject.setType(messagetype);
			
			From from = new From();
			from.setIdentity(fromIdentity);
			from.setName(fromName);
			
			To to = new To();
			to.setIdentity(toIdentity);
			to.setName(toIdentity);
			
			Reference ref = new Reference();
			ref.setMessageId("BGTVT" + Long.toString(System.currentTimeMillis()));
			
			ref.setVersion("1.0");
			
			header.setFrom(from);
			header.setTo(to);
			header.setReference(ref);
			header.setSubject(subject);
			
			return header;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}

	public IssueShiftingOrder lenhDieuDongUpgrade(UploadPortletRequest actionRequest, long documentName, int documentYear, String username, String requestCode, long requestState) {
		IssueShiftingOrder shiftOrder = null;
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);

			boolean isAdd = false;
			
			TempDocument tempDocument = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
			
			UserPort portDefault = UserPortLocalServiceUtil.findByUserId(themeDisplay.getUserId());
			
			List<IssueShiftingOrder> results = new ArrayList<IssueShiftingOrder>(
					IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(documentName,
							documentYear, KeyParams.ID, KeyParams.ORDER_BY_DESC));
			
			List<IssueShiftingOrder> listIssueShiftingOrder = null;

			String reasonToShift = StringPool.BLANK;
			
			if (results.size() > 0) {
				try {
					reasonToShift = IssueShiftingOrderLocalServiceUtil.findByRequestCode(results.get(0).getRequestCode()).get(0).getReasonToShift();
				} catch (Exception e) {
				}
			}
			
			if (requestState != ChuyenDichTrangThaiUtils.KE_HOACH_CHO_SUA_LENH_DIEU_DONG && results.size() > 0) {
				listIssueShiftingOrder = IssueShiftingOrderLocalServiceUtil
						.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear, KeyParams.ID,
								KeyParams.ORDER_BY_DESC);
			}
			
			if (Validator.isNotNull(listIssueShiftingOrder) && listIssueShiftingOrder.size() > 0) {
				shiftOrder = listIssueShiftingOrder.get(0);
			} else {
				shiftOrder = new IssueShiftingOrder();
				isAdd = true;
			}
			
			// Ngay cap
			Date issueDate = ParamUtil.getDate(actionRequest, "issueDate", FormatData.formatDateShort3);
			
			System.out.println("BusinessUtils.lenhDieuDongUpgrade(shiftingDate): " + ParamUtil.getString(actionRequest, "shiftingDate"));
			
			if (Validator.isNull(issueDate)) {
				issueDate = new Date();
			}
			Random random = new Random();
			String numberShiftingOrder = String.valueOf(random.nextInt(100000));
			String representative = ParamUtil.getString(actionRequest, "representative");
			Date shiftingDate = ParamUtil.getDate(actionRequest, "shiftingDate", FormatData.formatDateShort3);
			String tugBoat = ParamUtil.getString(actionRequest, "tugBoat");
			String nameOfShip = ParamUtil.getString(actionRequest, "nameOfShip");
			double shownDraftxF = ParamUtil.getDouble(actionRequest, "shownDraftxF", 0L);
			double shownDraftxA = ParamUtil.getDouble(actionRequest, "shownDraftxA", 0L);
			
			String from = ParamUtil.getString(actionRequest, "from");
			double dwt = ParamUtil.getDouble(actionRequest, "dwt", 0L);
			double loa = ParamUtil.getDouble(actionRequest, "loa", 0L);
			String anchoringPortCode = ParamUtil.getString(actionRequest, "anchoringPortCode");
			String portHarbourCode = ParamUtil.getString(actionRequest, "portHarbourCode");
			String shiftingPortWharfCode = ParamUtil.getString(actionRequest, "shiftingPortWharfCode");
			
			JSONObject anchoringPortCodeOBJ = null;
			
			try {
				anchoringPortCodeOBJ = JSONFactoryUtil.createJSONObject(anchoringPortCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			JSONObject portHarbourCodeOBJ = null;
			
			try {
				portHarbourCodeOBJ = JSONFactoryUtil.createJSONObject(portHarbourCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			JSONObject shiftingPortWharfCodeOBJ = null;
			
			try {
				shiftingPortWharfCodeOBJ = JSONFactoryUtil.createJSONObject(shiftingPortWharfCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil.findBydocumentNameAndDocumentYearAndNoticeShipType(documentName, documentYear, PageType.TYPE_THONG_BAO_TAU_DEN_CANG);
			
			if (tempNoTiceShipMessages != null && tempNoTiceShipMessages.size() > 0) {
				TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages.get(0);
				
				if (Validator.isNotNull(listIssueShiftingOrder) && listIssueShiftingOrder.size() > 0) {
					
				} else {
					
					shiftOrder.setFlagStateOfShip(tempDocument.getStateCode());
					shiftOrder.setTaxCodeOfShipownersAgents(tempNoTiceShipMessage.getShipAgencyCode());
					shiftOrder.setNameOfShipownersAgents(tempNoTiceShipMessage.getNameOfShipAgent());
					shiftOrder.setShipAgencyAddress(tempNoTiceShipMessage.getShipAgencyAddress());
					shiftOrder.setShipAgencyPhone(tempNoTiceShipMessage.getShipAgencyPhone());
					shiftOrder.setShipAgencyFax(tempNoTiceShipMessage.getShipAgencyFax());
					shiftOrder.setShipAgencyEmail(tempNoTiceShipMessage.getShipAgencyEmail());
					shiftOrder.setTugBoat(tempNoTiceShipMessage.getTugBoat());
					shiftOrder.setShownDraftxF(tempNoTiceShipMessage.getShownDraftxF());
					shiftOrder.setLoa(tempNoTiceShipMessage.getLoa());
					shiftOrder.setDwt(tempNoTiceShipMessage.getDwt());
					shiftOrder.setLoaunit(tempNoTiceShipMessage.getUnitLOA());
					shiftOrder.setDwtunit(tempNoTiceShipMessage.getUnitDWT());
					
					shiftOrder.setUnitShownDraftxF(tempNoTiceShipMessage.getUnitShownDraftxF());
					shiftOrder.setUnitShownDraftxA(tempNoTiceShipMessage.getUnitShownDraftxA());
					
					shiftOrder.setNameOfShip(tempNoTiceShipMessage.getShipName());
					shiftOrder.setFrom(tempNoTiceShipMessage.getArrivalPortCode());
					shiftOrder.setTo(tempNoTiceShipMessage.getPortWharfCode());
					
					shiftOrder.setAnchoringPortCode(tempNoTiceShipMessage.getArrivalPortCode());
					shiftOrder.setAnchoringPortWharfCode(tempNoTiceShipMessage.getPortWharfCode());
					
					shiftOrder.setPortHarbourCode(tempNoTiceShipMessage.getPortHarbourCode());
					shiftOrder.setShiftingPortWharfCode(tempNoTiceShipMessage.getPortGoingToCode());
					

					shiftOrder.setShownDraftxA(tempNoTiceShipMessage.getShownDraftxA());
					
				}
				
			}
			
			
			tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
					.findBydocumentNameAndDocumentYearAndNoticeShipType(documentName, documentYear,
							PageType.TYPE_XAC_BAO_TAU_DEN_CANG);
			
			if (tempNoTiceShipMessages != null && tempNoTiceShipMessages.size() > 0) {
				TempNoticeShipMessage tempNoTiceShipMessage = tempNoTiceShipMessages.get(0);
				
				shiftOrder.setShiftingDate(tempNoTiceShipMessage.getArrivalDate());

				shiftOrder.setAnchoringPortCode(tempNoTiceShipMessage.getArrivalPortCode());
				
				shiftOrder.setPortHarbourCode(tempNoTiceShipMessage.getPortHarbourCode());
				
				shiftOrder.setShiftingPortWharfCode(tempNoTiceShipMessage.getPortWharfCode());
				
				DmPort dmPortArrival = DmPortLocalServiceUtil.fetchByPortCodeLoCode(tempNoTiceShipMessage.getArrivalPortCode(), portDefault.getPortCode());
				
				String arrivalPortName = StringPool.BLANK;
				
				if (Validator.isNotNull(dmPortArrival)) {
					
					arrivalPortName = dmPortArrival.getPortName();
					
				}
				
				shiftOrder.setFrom(arrivalPortName);

			}
			
			// giam doc cang vu hang hai
			String directorOfMaritimeAdministration = "GD";
			long urs = 0;
			urs = UserLocalServiceUtil.getUserIdByEmailAddress(10154, username);
			log.info("=============userID ==== " + urs);
			if (urs != 0) {
				UserPort defaultCode = UserPortLocalServiceUtil.findByUserId(urs);
				log.info("urs!=0");
				if (defaultCode != null) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(defaultCode.getPortCode());
					log.info("dmMaritime.getCityCode()== " + dmMaritime.getCityCode());
					if (dmMaritime != null) {
						directorOfMaritimeAdministration = dmMaritime.getCityCode();
					}
				}
			}
				
			shiftOrder.setDirectorOfMaritime(directorOfMaritimeAdministration);
			
			shiftOrder.setNumberShiftingOrder(numberShiftingOrder);
			shiftOrder.setDocumentName(documentName);
			shiftOrder.setDocumentYear(documentYear);
			shiftOrder.setPortofAuthority(tempDocument.getMaritimeCode());
			
			
			// form lenh dieu dong
			shiftOrder.setLoa(loa);
			shiftOrder.setRepresentative(representative);
			shiftOrder.setShiftingDate(shiftingDate);
			shiftOrder.setTugBoat(tugBoat);
			shiftOrder.setNameOfShip(nameOfShip);
			shiftOrder.setShownDraftxF(shownDraftxF);
			shiftOrder.setShownDraftxA(shownDraftxA);
			shiftOrder.setFrom(from);
			shiftOrder.setAnchoringPortCode(Validator.isNotNull(anchoringPortCodeOBJ)? anchoringPortCodeOBJ.getString("portCode"):anchoringPortCode.replaceAll("\"", StringPool.BLANK));
			
			String portHarborCode = Validator.isNotNull(portHarbourCodeOBJ)?portHarbourCodeOBJ.getString("portHarbourCode"):portHarbourCode.replaceAll("\"", StringPool.BLANK);
			String portPortWarf = Validator.isNotNull(shiftingPortWharfCodeOBJ)?shiftingPortWharfCodeOBJ.getString("portWharfCode"):shiftingPortWharfCode.replaceAll("\"", StringPool.BLANK);
			
			shiftOrder.setPortHarbourCode(portHarborCode);
			shiftOrder.setShiftingPortWharfCode(portPortWarf);
			
			DmMaritime maritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(tempDocument.getMaritimeCode());
			if (Validator.isNull(maritime)) { maritime = new DmMaritime(); }
			
			String cangBien = maritime.getCityCode();
			
			DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil.getByPortRegionCode(tempDocument.getPortRegionCode());
			if (Validator.isNull(dmPortRegion)) { dmPortRegion = new DmPortRegion(); }
			
			String portRegin = dmPortRegion.getPortRegionNameVN();
			
			String portHarbourName = "";
			
			try {
				DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(portHarborCode);
				portHarbourName = dmPortHarbour.getPortHarbourNameVN();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String portPortWarfName = "";
			
			try {
				DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(portPortWarf);
				portPortWarfName = dmPortWharf.getPortWharfNameVN();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
//			cang bien + portRegin + portHauboru + ShiftingPortWharfCode
			
			shiftOrder.setTo(cangBien + ", " + portRegin + ", " + portHarbourName + ", " + portPortWarfName);
			shiftOrder.setDwt(dwt);
			
			String chanel = ParamUtil.getString(actionRequest, "chanel");
			
			JSONArray chanelArray = JSONFactoryUtil.createJSONArray(chanel);
			
			JSONObject chanelObject = null;
			
			log.info("==representative====" + representative);
			
			if (isAdd) {

				try {
					String portofAuthority = shiftOrder.getPortofAuthority();
					String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
					shiftOrder.setSignTitle(signTitle);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				shiftOrder.setRequestCode(UUID.randomUUID().toString());
				shiftOrder.setReasonToShift(reasonToShift);
				shiftOrder.setIssueDate(issueDate);
				shiftOrder.setVersionNo( (results.size() == 0) ? String.valueOf(1): String.valueOf(results.size() + 1));
				shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_MOI);
				shiftOrder = IssueShiftingOrderLocalServiceUtil.addIssueShiftingOrder(shiftOrder);
				
				log.info("===> Da tao moi issueshiftingorder: ");
				log.info("===> documentName: " + documentName);
				log.info("===> documentYear: " + documentYear);
				log.info("===> shiftOrder: " + shiftOrder);
			} else {
				shiftOrder.setAttachedFile(0L);
				shiftOrder.setIssueDate(issueDate);
				shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_SUA);
				shiftOrder = IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
				
				log.info("===> Da cap nhat issueshiftingorder: ");
				log.info("===> documentName: " + documentName);
				log.info("===> documentYear: " + documentYear);
				log.info("===> shiftOrder: " + shiftOrder);
			}
			
			results = new ArrayList<IssueShiftingOrder>(
					IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(documentName,
							documentYear, KeyParams.ID, KeyParams.ORDER_BY_DESC));
			
			if (results.size() > 0) {
				shiftOrder = results.get(0);
			}
			
			List<IssueShiftingOrderChanel> chanels = IssueShiftingOrderChanelLocalServiceUtil.getShiftingOrderChanel(shiftOrder.getId());
			
			for (IssueShiftingOrderChanel issueShiftingOrderChanel : chanels) {
				IssueShiftingOrderChanelLocalServiceUtil.deleteIssueShiftingOrderChanel(issueShiftingOrderChanel);
			}
			
			for (int i = 0; i < chanelArray.length(); i++) {
				chanelObject = chanelArray.getJSONObject(i);
				
				IssueShiftingOrderChanel issueShiftingOrderChanel = new IssueShiftingOrderChanel();
				
				issueShiftingOrderChanel.getId().setShiftingOrderId(shiftOrder.getId());
				issueShiftingOrderChanel.getId().setChanelCode(chanelObject.getString("code0"));
				issueShiftingOrderChanel.setChanelName(chanelObject.getString("name"));
				issueShiftingOrderChanel.setOrder(i + 1);
				
				IssueShiftingOrderChanelLocalServiceUtil.addIssueShiftingOrderChanel(issueShiftingOrderChanel);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return shiftOrder;
	}
	
	public IssueShiftingOrder lenhDieuDong(ActionRequest resourceRequest, long documentName, int documentYear, String username) {
		
		try {
			Random random = new Random();
			
			long id = ParamUtil.getLong(resourceRequest, "idIssueShiftingOrder");
			
			// Lenh dieu dong so??????
			String numberShiftingOrder = String.valueOf(random.nextInt(100000));
			
			// Cang vu hang hai null
			String portofAuthority = ParamUtil.getString(resourceRequest, "maritimeCode");
			log.info("==Cang vu hang hai==maritimeCode=" + portofAuthority);
			
			// Ten taushipName
			String nameOfShip = ParamUtil.getString(resourceRequest, "shipName");
			log.info("==Ten taushipName==nameOfShip=" + nameOfShip);
			
			// Quoc tich tau
			String flagStateOfShip = ParamUtil.getString(resourceRequest, "stateCode");
			log.info("==Quoc tich tau==flagStateOfShip=" + flagStateOfShip);
			
			String anchoringPortCode = ParamUtil.getString(resourceRequest, "arrivalPortCode");// Ma cang neo dau, cang toi
			
			// TODO dinhminh 1000 ban ghi, ko lay dc, du lieu null
			// Cau cang dang neo dau
			String anchoringPortWharfCode = ParamUtil.getString(resourceRequest, "portWharfCode");// di chuyen toi
			log.info("==Cau cang dang neo dau==portWharfCode=" + anchoringPortWharfCode);
			
			// Ma cau cang di chuyen toi
			String shiftingPortWharfCode = anchoringPortWharfCode;
			
			// Thoi gian dieu dong
			Date shiftingDate = ParamUtil.getDate(resourceRequest, "purposeDate", FormatData.formatDateShort3);
			log.info("==Thoi gian dieu dong==purposeDate=" + shiftingDate);
			
			// muc dich neo dau
			String purposeName = ParamUtil.getString(resourceRequest, "purposeName");
			log.info("==muc dich neo dau==purposeName=" + purposeName);
			
			// ly do dieu dong
			String reasonToShift = ParamUtil.getString(resourceRequest, "reasonToShift");
			log.info("==ly do dieu dong==reasonToShift=" + reasonToShift);
			
			if (reasonToShift.length() == 0) {
				reasonToShift = purposeName;
			}
			
			// ngay cap
			Date issueDate = ParamUtil.getDate(resourceRequest, "issueDate", FormatData.formatDateShort3);
			
			// giam doc cang vu hang hai
			String directorOfMaritimeAdministration = "GD";
			long urs = 0;
			urs = UserLocalServiceUtil.getUserIdByEmailAddress(10154, username);
			log.info("=============userID ==== " + urs);
			if (urs != 0) {
				UserPort defaultCode = UserPortLocalServiceUtil.findByUserId(urs);
				log.info("urs!=0");
				if (defaultCode != null) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(defaultCode.getPortCode());
					log.info("dmMaritime.getCityCode()== " + dmMaritime.getCityCode());
					if (dmMaritime != null) {
						directorOfMaritimeAdministration = dmMaritime.getCityCode();
					}
				}
			}
			
			String requestCode = ParamUtil.getString(resourceRequest, "requestCodeShifOrder");
			
			// giay phep so
			String certificateNo = ParamUtil.getString(resourceRequest, "certificateNo");
			String unitCertificateNo = ParamUtil.getString(resourceRequest, "unitCertificateNo");
			
			String representative = ParamUtil.getString(resourceRequest, "representative");
			String versionNo = ParamUtil.getString(resourceRequest, "versionNo");
			String portHarbourCode = ParamUtil.getString(resourceRequest, "portHarbourCode");
			String remarks = ParamUtil.getString(resourceRequest, PageType.LY_DO_TU_CHOI);
			
			log.info("==lenhDieuDong==documentName===" + documentName + "==documentYear==" + documentYear + "===ID==" + id + "==requestCode=="
					+ requestCode);
			log.info("==lenhDieuDong==representative=" + representative);
			
			IssueShiftingOrder shiftOrder = new IssueShiftingOrder();
			
			shiftOrder.setNumberShiftingOrder(numberShiftingOrder);
			shiftOrder.setDocumentName(documentName);
			shiftOrder.setDocumentYear(documentYear);
			shiftOrder.setPortofAuthority(portofAuthority);
			shiftOrder.setNameOfShip(nameOfShip);
			shiftOrder.setFlagStateOfShip(flagStateOfShip);
			shiftOrder.setAnchoringPortCode(anchoringPortCode);
			shiftOrder.setAnchoringPortWharfCode(anchoringPortWharfCode);
			shiftOrder.setPortHarbourCode(portHarbourCode);
			shiftOrder.setShiftingPortWharfCode(shiftingPortWharfCode);
			shiftOrder.setShiftingDate(shiftingDate);
			shiftOrder.setReasonToShift(reasonToShift);
			shiftOrder.setIssueDate(issueDate);
			shiftOrder.setDirectorOfMaritime(directorOfMaritimeAdministration);
			shiftOrder.setCertificateNo(certificateNo.trim() + "/" + unitCertificateNo.trim());			
			shiftOrder.setVersionNo(versionNo);
			shiftOrder.setRepresentative(representative);

			String signTitle = CheckBusinessUtil.getSignTitle(representative, portofAuthority);
			shiftOrder.setSignTitle(signTitle);
			
			String capLenhDieuDong = ParamUtil.getString(resourceRequest, PageType.LAN_CAP_LENH_DIEU_DONG);
			String suaLenhDieuDong = ParamUtil.getString(resourceRequest, PageType.LAN_SUA_LENH_DIEU_DONG);
			
			log.info("==cap_LenhDieuDong====" + capLenhDieuDong + "====sua_LenhDieuDong====" + suaLenhDieuDong);
			
			if (capLenhDieuDong.equalsIgnoreCase(KeyParams.MOT_LAN)) {
				
				shiftOrder.setRequestCode(UUID.randomUUID().toString());
				shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_MOI);
				shiftOrder = IssueShiftingOrderLocalServiceUtil.addIssueShiftingOrder(shiftOrder);
				log.info("===========MOT_LAN=id=" + id + "INSERT");
				
			} else if (suaLenhDieuDong.equalsIgnoreCase(KeyParams.MOT_LAN)) {
				
				shiftOrder.setId(id);
				shiftOrder.setRequestCode(requestCode);
				shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_SUA);
				shiftOrder = IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
				log.info("===========MOT_LAN=id=" + id + "=UPDATE");
				
			} else if (capLenhDieuDong.equalsIgnoreCase(KeyParams.N_LAN)) {
				
				// TODO Nhap Canh truong hop cap lai
				shiftOrder.setRequestCode(UUID.randomUUID().toString());
				shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.SUA_DOI_BO_XUNG);
				shiftOrder.setRemarks(remarks);
				
				shiftOrder = IssueShiftingOrderLocalServiceUtil.addIssueShiftingOrder(shiftOrder);
				log.info("===========N_LAN==" + "INSERT");
				
			} else if (suaLenhDieuDong.equalsIgnoreCase(KeyParams.N_LAN)) {
				
				shiftOrder.setId(id);
				shiftOrder.setRequestCode(requestCode);
				shiftOrder.setRequestState(TrangThaiBanKhaiNhapCanh.SUA_DOI_BO_XUNG);
				shiftOrder = IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
				log.info("===========N_LAN=id=" + id + "=UPDATE");
			}
			return shiftOrder;
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Lay cang cua user dang nhap
	 * 
	 * @param userName
	 * @return
	 */
	public static String getOrganizationFromUser(ActionRequest resourceRequest) {
		String result = null;
		try {
			
			// log.info("======getOrganizationFromUser resourceRequest ===" + resourceRequest);
			
			User user = PortalUtil.getUser(resourceRequest);
			UserPort userPort = UserPortLocalServiceUtil.findByUserId(user.getUserId());
			
			// log.info("======getOrganizationFromUser user.getUserId() ===" + user.getUserId());
			if (userPort != null) {
				result = userPort.getPortCode();
				DmGtOrganization dmGtOrganization = DmGtOrganizationLocalServiceUtil.findByOrganizationCode(result);
				if (dmGtOrganization != null) {
					result = dmGtOrganization.getOrganizationName();
				} else {
					DmMaritime maritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(userPort.getPortCode());
					if (Validator.isNotNull(maritime)) {
						result = maritime.getMaritimeNameVN();
					}
				}
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return result;
	}
	
	public static String getMaritimeShortNameFromUser(ActionRequest resourceRequest) {
		String result = null;
		try {
			
			// log.info("======getOrganizationFromUser resourceRequest ===" + resourceRequest);
			
			User user = PortalUtil.getUser(resourceRequest);
			UserPort userPort = UserPortLocalServiceUtil.findByUserId(user.getUserId());
						
			if (userPort != null) {
				result = userPort.getPortCode();		
				
				DmMaritime maritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(userPort.getPortCode());
				if (Validator.isNotNull(maritime)) {
					result = maritime.getCityCode();
				}
				
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return result;
	}
	
	public String getDivision(ActionRequest resourceRequest) {
		String result = "Ke Hoach";
		try {
			
			String organizationId =  ParamUtil.getString(resourceRequest,PageType.ORGANIZATION_TYPE);
			if (organizationId != null && organizationId.length() > 0) {
				Organization organization = OrganizationLocalServiceUtil.fetchOrganization(Long.valueOf(organizationId));
				if (organization != null) {
					result = organization.getName();
				}
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		return result;
	}
	
	// TODO minhhandsome issue = 109
	public static boolean updateResultDeclaration(int messageType, long documentName, int documentYear, int status) {
		boolean result = true;
		try {
			
			// log.info("=============VAO updateResultDeclaration===============");
			List<ResultDeclaration> resultDeclarations = ResultDeclarationLocalServiceUtil.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
					messageType, documentName, documentYear);
			if (resultDeclarations != null && resultDeclarations.size() > 0) {
				
				ResultDeclaration resultDeclaration = resultDeclarations.get(0);
				if (resultDeclaration.getRequestState() == 9) {
					
					// log.info("=============VAO updateResultDeclaration===============9");
					
				} else {
					resultDeclaration.setRequestState(status);
					ResultDeclarationLocalServiceUtil.updateResultDeclaration(resultDeclaration);
				}
				
			}
		} catch (Exception e) {
			result = false;
			
			log.error(e.getMessage());
		}
		return result;
	}
	
	public String sendMessageHoSo(Header header, Date acceptDate) {
		
		String xmlResult = "";
		// Tao ban tin tra ve bao da nhan thong tin.
		try {
			xmlResult = "<ReceiveDate>" + FormatData.parseDateToTring(new Date()) + "</ReceiveDate>";
		} catch (Exception e) {
			
		}
		return createContentSendFromBGTVTToNSW(header, xmlResult);
	}
	
	public static String getRemarkPheDuyet(String userName) {
		String result = userName;
		result = "[" + userName + "] - " + ConfigurationManager.getStrProp("pheDuyet", " - Phe Duyet - ") + "["
				+ FormatData.parseDateToTringType3(new Date()) + "]";
		return result;
	}
	
	public static String getRemarkPheDuyetTB(String userName, Date approvalTime) {
		String result = userName;
		result = "[" + userName + "] - " + ConfigurationManager.getStrProp("pheDuyet", " - Phe Duyet - ") + "["
				+ FormatData.parseDateToTringType3(approvalTime) + "]";
		return result;
	}
	
	public static String getRemarkChapNhan(String userName) {
		String result = userName;
		result = "[" + userName + "] - " + ConfigurationManager.getStrProp("chapNhan", " - Chap Nhan - ") + "["
				+ FormatData.parseDateToTringType3(new Date()) + "]";
		return result;
	}
	
	public static String getRemarkTamDung(String userName, String lyDoTuChoi) {
		if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
			lyDoTuChoi = "Tu Choi";
		}
		String result = userName;
		result = "[" + userName + "] - " + ConfigurationManager.getStrProp("tamDung", " - Tam Dung - ") + "["
				+ FormatData.parseDateToTringType3(new Date()) + "] " + ConfigurationManager.getStrProp("lydo", " - Ly  Do - ") + "[" + lyDoTuChoi
				+ "]";
		return result;
	}
	
	public static String getRemarkTuChoi(String userName, String lyDoTuChoi) {
		if (lyDoTuChoi == null || lyDoTuChoi.length() == 0) {
			lyDoTuChoi = "Tu Choi";
		}
		String result = userName;
		result = "[" + userName + "] - " + ConfigurationManager.getStrProp("tuchoi", " - Tu Choi - ") + "["
				+ FormatData.parseDateToTringType3(new Date()) + "] " + ConfigurationManager.getStrProp("lydo", " - Ly  Do - ") + "[" + lyDoTuChoi
				+ "]";
		return result;
	}
	
	public static String getRemarkBoSung(String userName, String lyDoBoSung) {
		if (lyDoBoSung == null || lyDoBoSung.length() == 0) {
			lyDoBoSung = "Bo sung";
		}
		String result = userName;
		result = "[" + userName + "] - " + ConfigurationManager.getStrProp("lydobosung", " - Bo sung - ") + "["
				+ FormatData.parseDateToTringType3(new Date()) + "] " + ConfigurationManager.getStrProp("lydo", " - Ly  Do - ") + "[" + lyDoBoSung
				+ "]";
		return result;
	}
	
	public static String getRemarkHuy(String userName, String lyDoHuy) {
		if (lyDoHuy == null || lyDoHuy.length() == 0) {
			lyDoHuy = "Huy";
		}
		String result = userName;
		result = "[" + userName + "] - " + ConfigurationManager.getStrProp("lydohuy", " - Huy - ") + "["
				+ FormatData.parseDateToTringType3(new Date()) + "] " + ConfigurationManager.getStrProp("lydo", " - Ly  Do - ") + "[" + lyDoHuy + "]";
		return result;
	}
	
	public static String getRemarkTuChoiTB(String userName, String lyDoTuChoi, Date approvalTime) {
		String result = userName;
		result = "[" + userName + "] - " + ConfigurationManager.getStrProp("tuchoi", " - Tu Choi - ") + "["
				+ FormatData.parseDateToTringType3(approvalTime) + "] " + ConfigurationManager.getStrProp("lydo", " - Ly  Do - ") + "[" + lyDoTuChoi
				+ "]";
		return result;
	}
	
	public static String getRemarkCapLai(String userName, String lydoCapLai, Date ngayCapLai) {
		String result = userName;
		result = "[" + userName + "] - " + ConfigurationManager.getStrProp("caplai", " - Cap lai - ") + "["
				+ FormatData.parseDateToTringType3(ngayCapLai) + "] " + ConfigurationManager.getStrProp("lydo", " - Ly  Do - ") + "[" + lydoCapLai
				+ "]";
		return result;
	}
	
	public static String getRemarkTraLaiHoSo(String userName, String lydoTraLai, Date ngayTraLai) {
		String result = userName;
		result = "[" + userName + "] - " + ConfigurationManager.getStrProp("tralai", " - Tra lai - ") + "["
				+ FormatData.parseDateToTringType3(ngayTraLai) + "] " + ConfigurationManager.getStrProp("lydo", " - Ly  Do - ") + "[" + lydoTraLai
				+ "]";
		return result;
	}
	
	public static void insertOrUpdateResultMinistry(int documentName, int documentYear, String function, String remarks) {
		try {
			ResultMinistry resultMinistry = null;
			if (resultMinistry == null) {
				resultMinistry = new ResultMinistry();
				setValue2ResultMinistry(resultMinistry, documentName, documentYear, function, remarks);
				ResultMinistryLocalServiceUtil.addResultMinistry(resultMinistry);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
	}
	
	public static void insertResultHistoryMinistry(int documentName, int documentYear, String function, String remarks) {
		try {
			log.info("==insertResultHistoryMinistry==");
			ResultHistoryMinistry resultMinistry = new ResultHistoryMinistry();
			
			resultMinistry.setBusinessTypeCode(MessageType.HO_SO);
			resultMinistry.setDocumentName((long) documentName);
			resultMinistry.setDocumentYear(documentYear);
			resultMinistry.setLatestDate(new Date());
			resultMinistry.setRequestCode(Long.toString(System.currentTimeMillis()));
			resultMinistry.setResponse(function);
			resultMinistry.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_MOI);
			resultMinistry.setMinistryCode(ConstantCoQuanBanNganh.BGTVT);
			resultMinistry.setRemarks(remarks);
			
			ResultHistoryMinistryLocalServiceUtil.addResultHistoryMinistry(resultMinistry);
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
	}
	
	private static void setValue2ResultMinistry(ResultMinistry resultMinistry, int documentName, int documentYear, String function, String remarks) {
		resultMinistry.setBusinessTypeCode(MessageType.HO_SO);
		resultMinistry.setDocumentName((long) documentName);
		resultMinistry.setDocumentYear(documentYear);
		resultMinistry.setLatestDate(new Date());
		resultMinistry.setRequestCode(Long.toString(System.currentTimeMillis()));
		resultMinistry.setResponse(function);
		resultMinistry.setRequestState(TrangThaiBanKhai.TRANG_THAI_BANG_KHAI_MOI);
		resultMinistry.setMinistryCode(ConstantCoQuanBanNganh.BGTVT);
		resultMinistry.setRemarks(remarks);
	}
	
	public static boolean updateLyDoResultNotification(String userlogin, String lydo, int businessTypeCode, String maritimeCode, long documentName,
			int documentYear) {
		log.info("===updateLyDoResultNotification===");
		boolean result = true;
		try {
			ResultNotification notification = null;
			notification = ResultNotificationLocalServiceUtil.findByBusinessTypeCode(businessTypeCode, documentName, documentYear);
			if (notification == null) {
				log.info("========vao insert====ResultNotification=====");
				
				notification = new ResultNotification();
				
				notification.setRequestCode(UUID.randomUUID().toString());
				notification.setDocumentYear(documentYear);
				notification.setDocumentName(documentName);
				notification.setMaritimeCode(maritimeCode);
				notification.setRequestState(1);
				notification.setResponse(lydo);
				notification.setResponseTime(new Date());
				notification.setRole(4);
				notification.setOfficerName(userlogin);
				notification.setLatestDate(new Date());
				notification.setBusinessTypeCode(businessTypeCode);
				notification.setIsReply(1);
				
				ResultNotificationLocalServiceUtil.addResultNotification(notification);
			} else {
				log.info("========vao update====ResultNotification=====");
				
				notification.setMaritimeCode(maritimeCode);
				notification.setRequestState(1);
				notification.setResponse(lydo);
				notification.setResponseTime(new Date());
				notification.setRole(4);
				notification.setOfficerName(userlogin);
				notification.setBusinessTypeCode(businessTypeCode);
				notification.setIsReply(1);
				ResultNotificationLocalServiceUtil.updateResultNotification(notification);
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
			result = false;
		}
		
		return result;
	}
	
	/** Ham su dung de insert vao InterfaceRequest cho 3 ban khai, lenhdieudong, giaypheproicang, giayphepquacanh */
	public boolean insertHistorySendMessageThreeIssue(String xmlData, String userName, String requestCodeIssue) {
		boolean result = false;
		try {
			Header header = null;
			String data = replaceXML(xmlData.trim());
			if(data.length()<1)return Boolean.FALSE;
			// Tao ban tin tra ve bao da nhan thong tin.
			header = ReadMessages.readXMLMessagesHeader(data);
			
			// Insert history.
			String resultMethod = insertHistoryThreeIssue(header, xmlData, BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, requestCodeIssue,
					userName);
			if (resultMethod == null) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			
			result = false;
			log.error(e.getMessage());
		}
		return result;
	}
	
	public static Header readXmlMessageHeader(String xmlString) throws Exception {
		
		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "Envelope/Header";// "//Body/Content";//
		
		DOMSource source = null;
		ByteArrayInputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		
		Node node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
		source = new DOMSource(node);
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.setOutputProperty("indent", "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		
		transformer.transform(source, result);
		// log.info(sw.toString());
		
		StringReader reader = new StringReader(sw.toString());
		
		// create a JAXBContext capable of handling classes generated into
		// Document doc1 =XmlUtils.parseXmlFile("C://header.xml", false);
		
		JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
		
		// create an Unmarshaller
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		String xmlHeader = sw.toString();
		// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
		
		ByteArrayInputStream input = new ByteArrayInputStream(xmlHeader.getBytes("UTF-8"));
		
		// unmarshal a po instance document into a tree of Java content
		// objects composed of classes from the primer.po package.
		
		Header header = (Header) unmarshaller.unmarshal(input);
		return header;
		
	}
	
	public String _processMessage(Header header, String requestCode, String data) {
		
//		BusinessUtils businessUtils = new BusinessUtils();
		
		String xmlResult = BusinessUtils.createContentSendFromBGTVTToNSW(header, data);

		insertMessageQueue(header, xmlResult, BoGiaoThongToHqmc, requestCode, MessgaePriorityLevels.HIGHT);
		
		if (data != null && data.length() > 0) {

//			businessUtils.insertHistorySendMessage(xmlResult);

		}
		
		return xmlResult;
	}
	
	// Gui message khi can bo xu ly
	private static void insertMessageQueue(Header header, String xmlResult, String direction, String requestCode, int priority) {
//		SendMessgaeFunctions.insertMessageQueue(header, xmlResult, direction, requestCode, MessgaePriorityLevels.HIGHT);
		MessageSyncUtil.insertCanBoXuLyQueue(null, header, xmlResult);
	}
}
