package vn.gt.tichhop.message;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.nghiepvu.model.TempDocument;
import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultNotification;
import com.fds.nsw.nghiepvu.model.ResultNotification;
import vn.gt.dao.result.service.ResultNotificationLocalServiceUtil;
import vn.gt.tichhop.threat.ObjectExcute;
import vn.gt.tichhop.threat.ThreadPassingMessage;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.XacNhanHuyLenhDieuDong;



@Slf4j
public class NhanThongBaoHuy {

	

	public String huyLenhDieuDong(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput,
					BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(
					MessageType.HUY_LENH_DIEU_DONG, data);
			
			List<Object> objects = new ArrayList<Object>();
			objects.add(obj);
			ThreadPassingMessage.init().add(
					new ObjectExcute(header, objects, dataInput));

			businessUtils.updateHistory(header.getReference().getMessageId(),
					TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			
			if (obj instanceof XacNhanHuyLenhDieuDong) {
				ResultNotification resultNotification = new ResultNotification();
				resultNotification
						.setBusinessTypeCode(MessageType.HUY_LENH_DIEU_DONG);
				resultNotification.setDivision(((XacNhanHuyLenhDieuDong) obj)
						.getDivision());
				resultNotification.setDocumentName(header.getSubject()
						.getReference());
				resultNotification.setDocumentYear(header.getSubject()
						.getDocumentYear());
				resultNotification.setLatestDate(FormatData.parseStringToDate(header.getSubject()
						.getSendDate()));
				resultNotification.setRequestCode(header.getReference().getMessageId());
				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(header
								.getSubject().getReference(), header
								.getSubject().getDocumentYear());
				if (tempDocument != null) {
					resultNotification.setMaritimeCode(tempDocument
							.getMaritimeCode());
				}
				//resultNotification.setOrganization(organization)
				resultNotification.setRemarks(((XacNhanHuyLenhDieuDong) obj).getReason());
				
				resultNotification.setRole(0);
				resultNotification.setRequestState(1);
				ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
				
			}
			// Cap nhat trang thai la tiep nhan voi truong hop da tiep nhan ban
			// khai.
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils
				.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult,
				BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}

	public String huyGiayPhepVaoCang(Header header, String dataInput,
			String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput,
					BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(
					MessageType.HUY_GIAY_PHEP_VAO_CANG, data);
			List<Object> objects = new ArrayList<Object>();
			objects.add(obj);
			ThreadPassingMessage.init().add(
					new ObjectExcute(header, objects, dataInput));

			businessUtils.updateHistory(header.getReference().getMessageId(),
					TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			if (obj instanceof XacNhanHuyThuTuc) {
				ResultNotification resultNotification = new ResultNotification();
				resultNotification
						.setBusinessTypeCode(MessageType.HUY_GIAY_PHEP_VAO_CANG);
				resultNotification.setDivision(((XacNhanHuyThuTuc) obj)
						.getDivision());
				resultNotification.setDocumentName(header.getSubject()
						.getReference());
				resultNotification.setDocumentYear(header.getSubject()
						.getDocumentYear());
				resultNotification.setLatestDate(FormatData.parseStringToDate(header.getSubject()
						.getSendDate()));
				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(header
								.getSubject().getReference(), header
								.getSubject().getDocumentYear());
				if (tempDocument != null) {
					resultNotification.setMaritimeCode(tempDocument
							.getMaritimeCode());
				}
				//resultNotification.setOrganization(organization)
				resultNotification.setRequestCode(header.getReference().getMessageId());
				resultNotification.setRemarks(((XacNhanHuyThuTuc) obj).getReason());
				
				resultNotification.setRole(0);
				resultNotification.setRequestState(1);
				ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
				
			}

		} catch (Exception e) {
			
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils
				.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult,
				BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}


	public String huyGiayPhepRoiCang(Header header, String dataInput,
			String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput,
					BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(
					MessageType.HUY_GIAY_PHEP_ROI_CANG, data);
			List<Object> objects = new ArrayList<Object>();
			objects.add(obj);
			ThreadPassingMessage.init().add(
					new ObjectExcute(header, objects, dataInput));

			businessUtils.updateHistory(header.getReference().getMessageId(),
					TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			if (obj instanceof XacNhanHuyThuTuc) {
				ResultNotification resultNotification = new ResultNotification();
				resultNotification
						.setBusinessTypeCode(MessageType.HUY_GIAY_PHEP_ROI_CANG);
				resultNotification.setDivision(((XacNhanHuyThuTuc) obj)
						.getDivision());
				resultNotification.setDocumentName(header.getSubject()
						.getReference());
				resultNotification.setDocumentYear(header.getSubject()
						.getDocumentYear());
				resultNotification.setLatestDate(FormatData.parseStringToDate(header.getSubject()
						.getSendDate()));
				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(header
								.getSubject().getReference(), header
								.getSubject().getDocumentYear());
				if (tempDocument != null) {
					resultNotification.setMaritimeCode(tempDocument
							.getMaritimeCode());
				}
				//resultNotification.setOrganization(organization)
				resultNotification.setRequestCode(header.getReference().getMessageId());
				resultNotification.setRemarks(((XacNhanHuyThuTuc) obj).getReason());
				
				resultNotification.setRole(0);
				resultNotification.setRequestState(1);
				ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
				
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils
				.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult,
				BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}

	public String huyGiayPhepQuaCanh(Header header, String dataInput,
			String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput,
					BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(
					MessageType.HUY_GIAY_PHEP_QUA_CANH, data);
			List<Object> objects = new ArrayList<Object>();
			objects.add(obj);
			ThreadPassingMessage.init().add(
					new ObjectExcute(header, objects, dataInput));

			businessUtils.updateHistory(header.getReference().getMessageId(),
					TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			if (obj instanceof XacNhanHuyThuTuc) {
				ResultNotification resultNotification = new ResultNotification();
				resultNotification
						.setBusinessTypeCode(MessageType.HUY_GIAY_PHEP_QUA_CANH);
				resultNotification.setDivision(((XacNhanHuyThuTuc) obj)
						.getDivision());
				resultNotification.setDocumentName(header.getSubject()
						.getReference());
				resultNotification.setDocumentYear(header.getSubject()
						.getDocumentYear());
				resultNotification.setLatestDate(FormatData.parseStringToDate(header.getSubject()
						.getSendDate()));
				resultNotification.setRequestCode(header.getReference().getMessageId());
				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(header
								.getSubject().getReference(), header
								.getSubject().getDocumentYear());
				if (tempDocument != null) {
					resultNotification.setMaritimeCode(tempDocument
							.getMaritimeCode());
				}
				//resultNotification.setOrganization(organization)
				resultNotification.setRemarks(((XacNhanHuyThuTuc) obj).getReason());
				
				resultNotification.setRole(0);
				resultNotification.setRequestState(1);
				ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
				
			}
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils
				.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult,
				BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}

	public String khaiHuyHoSo(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput,
					BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(
					MessageType.KHAI_HUY_HO_SO, data);
			List<Object> objects = new ArrayList<Object>();
			objects.add(obj);
			ThreadPassingMessage.init().add(
					new ObjectExcute(header, objects, dataInput));

			businessUtils.updateHistory(header.getReference().getMessageId(),
					TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			if (obj instanceof XacNhanHuyThuTuc) {
				ResultNotification resultNotification = new ResultNotification();
				resultNotification
						.setBusinessTypeCode(MessageType.KHAI_HUY_HO_SO);
				resultNotification.setDivision(((XacNhanHuyThuTuc) obj)
						.getDivision());
				resultNotification.setDocumentName(header.getSubject()
						.getReference());
				resultNotification.setDocumentYear(header.getSubject()
						.getDocumentYear());
				resultNotification.setLatestDate(FormatData.parseStringToDate(header.getSubject()
						.getSendDate()));
				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(header
								.getSubject().getReference(), header
								.getSubject().getDocumentYear());
				if (tempDocument != null) {
					resultNotification.setMaritimeCode(tempDocument
							.getMaritimeCode());
				}
				//resultNotification.setOrganization(organization)
				resultNotification.setRequestCode(header.getReference().getMessageId());
				resultNotification.setRemarks(((XacNhanHuyThuTuc) obj).getReason());
				
				resultNotification.setRole(0);
				resultNotification.setRequestState(1);
				ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
				
			}

		} catch (Exception e) {
			
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils
				.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult,
				BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}
	public String deNghiCapLaiGiayPhepVaoCang(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput,
					BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(
					MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG, data);
			List<Object> objects = new ArrayList<Object>();
			objects.add(obj);
			ThreadPassingMessage.init().add(
					new ObjectExcute(header, objects, dataInput));

			businessUtils.updateHistory(header.getReference().getMessageId(),
					TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			//if (obj instanceof DeNghiCapLaiGiayPhep) {
				ResultNotification resultNotification = new ResultNotification();
				resultNotification
						.setBusinessTypeCode(MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG);
				resultNotification.setDivision(((XacNhanHuyThuTuc) obj)
						.getDivision());
				resultNotification.setDocumentName(header.getSubject()
						.getReference());
				resultNotification.setDocumentYear(header.getSubject()
						.getDocumentYear());
				resultNotification.setLatestDate(FormatData.parseStringToDate(header.getSubject()
						.getSendDate()));
				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(header
								.getSubject().getReference(), header
								.getSubject().getDocumentYear());
				if (tempDocument != null) {
					resultNotification.setMaritimeCode(tempDocument
							.getMaritimeCode());
				}
				//resultNotification.setOrganization(organization)
				resultNotification.setRequestCode(header.getReference().getMessageId());
				resultNotification.setRemarks(((XacNhanHuyThuTuc) obj).getReason());
				
				
				resultNotification.setRole(0);
				resultNotification.setRequestState(1);
				ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
				
			//}

		} catch (Exception e) {
			
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils
				.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult,
				BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}


	
	public String deNghiCapLaiGiayPhepRoiCang(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput,
					BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(
					MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG, data);
			List<Object> objects = new ArrayList<Object>();
			objects.add(obj);
			ThreadPassingMessage.init().add(
					new ObjectExcute(header, objects, dataInput));

			businessUtils.updateHistory(header.getReference().getMessageId(),
					TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			//if (obj instanceof DeNghiCapLaiGiayPhep) {
				ResultNotification resultNotification = new ResultNotification();
				resultNotification
						.setBusinessTypeCode(MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG);
				resultNotification.setDivision(((XacNhanHuyThuTuc) obj)
						.getDivision());
				resultNotification.setDocumentName(header.getSubject()
						.getReference());
				resultNotification.setDocumentYear(header.getSubject()
						.getDocumentYear());
				resultNotification.setLatestDate(FormatData.parseStringToDate(header.getSubject()
						.getSendDate()));
				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(header
								.getSubject().getReference(), header
								.getSubject().getDocumentYear());
				if (tempDocument != null) {
					resultNotification.setMaritimeCode(tempDocument
							.getMaritimeCode());
				}
				//resultNotification.setOrganization(organization)
				resultNotification.setRequestCode(header.getReference().getMessageId());
				resultNotification.setRemarks(((XacNhanHuyThuTuc) obj).getReason());
				
				System.out.println("VAO ((XacNhanHuyThuTuc) obj).getReason()"+ ((XacNhanHuyThuTuc) obj).getReason());
				
				resultNotification.setRole(0);
				resultNotification.setRequestState(1);
				ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
				
			//}

		} catch (Exception e) {
			
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils
				.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult,
				BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}

	
	public String deNghiCapLaiGiayPhepQuaCanh(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput,
					BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(
					MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH, data);
			List<Object> objects = new ArrayList<Object>();
			objects.add(obj);
			ThreadPassingMessage.init().add(
					new ObjectExcute(header, objects, dataInput));

			businessUtils.updateHistory(header.getReference().getMessageId(),
					TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			if (obj instanceof DeNghiCapLaiGiayPhep) {
				ResultNotification resultNotification = new ResultNotification();
				resultNotification
						.setBusinessTypeCode(MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH);
				resultNotification.setDivision(((XacNhanHuyThuTuc) obj)
						.getDivision());
				resultNotification.setDocumentName(header.getSubject()
						.getReference());
				resultNotification.setDocumentYear(header.getSubject()
						.getDocumentYear());
				resultNotification.setLatestDate(FormatData.parseStringToDate(header.getSubject()
						.getSendDate()));
				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(header
								.getSubject().getReference(), header
								.getSubject().getDocumentYear());
				if (tempDocument != null) {
					resultNotification.setMaritimeCode(tempDocument
							.getMaritimeCode());
				}
				//resultNotification.setOrganization(organization)
				resultNotification.setRequestCode(header.getReference().getMessageId());
				resultNotification.setRemarks(((XacNhanHuyThuTuc) obj).getReason());
				
				resultNotification.setRole(0);
				resultNotification.setRequestState(1);
				ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
				
			}

		} catch (Exception e) {
			
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils
				.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult,
				BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}
}
