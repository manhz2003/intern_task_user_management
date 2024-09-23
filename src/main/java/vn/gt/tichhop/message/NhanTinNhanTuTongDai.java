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
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.inland.InlandCrewCallCenter;
import vn.nsw.model.XacNhanHuyLenhDieuDong;



@Slf4j
public class NhanTinNhanTuTongDai {



	
	public String khaiHuyHoSoQuaTinNhan(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			
			businessUtils.insertHistory(header, dataInput,
					BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			List<Object> objects = MessageFactory.converXMLMessagesContentToInLandObject(data);
			
//			ThreadPassingMessage.init().add(
//					new ObjectExcute(header, objects, dataInput));
			
			businessUtils.updateHistory(header.getReference().getMessageId(),
					TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			
			
			for (Object obj : objects) {
			
			if (obj instanceof InlandCrewCallCenter) {
				System.out.println("VAO ((InlandCrewCallCenter) obj).getNameOfShip()"+ ((InlandCrewCallCenter) obj).getNameOfShip());
				String nameOfShip = "---";

				List <TempDocument> tempDoc = TempDocumentLocalServiceUtil.findByTempDocumentByShipNameSMSAndDocumentTypeCode(
						nameOfShip, ((InlandCrewCallCenter) obj).getCallSign(), header.getFrom().getIdentity(), header
						.getSubject().getDocumentYear(), String.valueOf(header.getSubject().getDocumentType()));
				
				
				if (tempDoc != null && tempDoc.size() > 0)
				{
					for (TempDocument tempDocument: tempDoc)
					{
						ResultNotification resultNotification = new ResultNotification();
						resultNotification
								.setBusinessTypeCode(MessageType.KHAI_HUY_HO_SO);
						resultNotification.setDivision("THUTUC");
						resultNotification.setDocumentName(tempDocument.getDocumentName());
						resultNotification.setDocumentYear(tempDocument.getDocumentYear());
						resultNotification.setLatestDate(FormatData.parseStringToDate(header.getSubject()
								.getSendDate()));
						
						
						System.out.println("VAO ((InlandCrewCallCenter) obj).getNameOfShip()------"+ ((InlandCrewCallCenter) obj).getNameOfShip() 
								+ "-----tempDocument.getDocumentStatusCode()------" + tempDocument.getDocumentStatusCode());
						//resultNotification.setOrganization(organization)
						resultNotification.setRequestCode(header.getReference().getMessageId());
						resultNotification.setRemarks("Tong dai gui tin nhan HUY de nghi lam thu tuc tu so dien thoai: " +header.getSubject().getReference());
						
						resultNotification.setRole(0);
						resultNotification.setRequestState(1);
						if (tempDocument != null) {
							resultNotification.setMaritimeCode(tempDocument
									.getMaritimeCode());
						}
						if ((tempDocument != null) && (tempDocument.getDocumentStatusCode() != TrangThaiBanKhai.TU_CHOI_TIEP_NHAN) && 
								(tempDocument.getDocumentStatusCode() != TrangThaiBanKhai.PHE_DUYET_HOAN_THANH_THU_TUC) &&
								(tempDocument.getDocumentStatusCode() != TrangThaiBanKhai.HUY_THU_TUC_TAU_THUYEN_NHAP_CANH)) {
							
							ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
						}
						
					}

				}
								
				
			}
			
		}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils
				.createReturnContentAfterParserDataReceiverFromNSWInland(header);
		header.getSubject().setFunction("99");
		businessUtils.insertHistory(header, xmlResult,
				BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}
	public String deNghiLamThuTucQuaTinNhan(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {			
			// Insert history.
			String kq = BusinessUtils.insertHistory(header, dataInput,
					BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);
			
			if (kq != null && kq.length() > 0) {
				// Ban tin tra ve khi validate error.
				String xmlResultError = BusinessUtils.createReturnContentAfterValidationErrorFromNSW(header, kq);
				return xmlResultError;
			}
			// Insert temp table when NO error.			
			List<Object> liObjects = MessageFactory.converXMLMessagesContentToInLandObject(data);
			
			ThreadPassingMessage.init().add(
					new ObjectExcute(header, liObjects, dataInput));

			businessUtils.updateHistory(header.getReference().getMessageId(),
					TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);			
			
			for (Object obj : liObjects) {
				if (obj instanceof InlandCrewCallCenter) {
					ResultNotification resultNotification = new ResultNotification();
					resultNotification
							.setBusinessTypeCode(MessageType.TIN_NHAN_PTTND_VAOROI);
					resultNotification.setDivision("THUTUC");
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
					
					resultNotification.setRequestCode(header.getReference().getMessageId());
					resultNotification.setRemarks("Tong dai gui tin nhan lam thu tuc tu so dien thoai: " +((InlandCrewCallCenter) obj).getUserCreated());
					
					System.out.println("VAO ((InlandCrewCallCenter) obj).getUserCreated()"+ ((InlandCrewCallCenter) obj).getUserCreated());
					
					resultNotification.setRole(0);
					resultNotification.setRequestState(1);
					ResultNotificationLocalServiceUtil.addResultNotification(resultNotification);
					
				}
			}
			
				
			//}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils
				.createReturnContentAfterParserDataReceiverFromNSWInland(header);
		businessUtils.insertHistory(header, xmlResult,
				BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}


	
	
}
