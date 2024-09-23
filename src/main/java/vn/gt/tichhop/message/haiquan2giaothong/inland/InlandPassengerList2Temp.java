package vn.gt.tichhop.message.haiquan2giaothong.inland;

import java.util.Date;
import java.util.List;

import com.fds.nsw.kernel.exception.SystemException;


import vn.nsw.Header;

import vn.gt.utils.FormatData;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempPassengerDetails;
import com.fds.nsw.nghiepvu.model.TempPassengerList;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempPassengerDetails;
import com.fds.nsw.nghiepvu.model.TempPassengerList;
import vn.gt.dao.noticeandmessage.service.TempPassengerDetailsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPassengerListLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.nsw.model.inland.InlandPassengerList;
import vn.nsw.model.inland.InlandPassengerList.ListPassengers.Passenger;



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
public class InlandPassengerList2Temp {
	
	
	public boolean insert2TempInland(InlandPassengerList passengerList, Header header) throws SystemException {
		
		try {
			insert2TempDocumentInland(passengerList, header);
			
			TempPassengerList tempPassengerList = new TempPassengerList();
			
			tempPassengerList.setRequestCode(((header).getReference().getMessageId()));
			tempPassengerList.setDocumentName(FormatData.convertToLong(passengerList.getDocumentName()));
			tempPassengerList.setDocumentYear(FormatData.convertToInt(passengerList.getDocumentYear()));
			tempPassengerList.setUserCreated(passengerList.getUserCreated());
			tempPassengerList.setIsArrival(FormatData.convertToInt(passengerList.getIsArrival()));
			tempPassengerList.setNameOfShip(passengerList.getNameOfShip());
//			tempPassengerList.setPortOfArrivalCode(passengerList.getPortOfArrivalCode());
			//tempPassengerList.setAnchorageCode(passengerList.getAnchorageCode());
//			tempPassengerList.setDateOfArrival(FormatData.parseStringToDate(passengerList.getDateOfArrival()));
//			tempPassengerList.setIMONumber(passengerList.getIMONumber());
//			tempPassengerList.setCallSign(passengerList.getCallSign());
//			tempPassengerList.setVoyageNumber(passengerList.getVoyageNumber());
//			tempPassengerList.setFlagStateOfShip(passengerList.getFlagStateOfShip());
			
			tempPassengerList.setSignPlace(passengerList.getSignPlace());
			tempPassengerList.setSignDate(FormatData.parseStringToDate(passengerList.getSignDate()));
			tempPassengerList.setMasterSigned(FormatData.convertToInt(passengerList.getMasterSigned()));
			tempPassengerList.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			
			TempPassengerListLocalServiceUtil.addTempPassengerList(tempPassengerList);
			List<Passenger> passengers = passengerList.getListPassengers().getPassenger();
			if (passengers != null && passengers.size() > 0) {
				for (Passenger item : passengers) {
					try {
						TempPassengerDetails passenger = new TempPassengerDetails();
						
						passenger.setRequestCode(tempPassengerList.getRequestCode());
						passenger.setPassengerCode(item.getPassengerCode());
						passenger.setFamilyName(item.getFamilyName());
						passenger.setGivenName(item.getGivenName());
						//passenger.setNationality(item.getNationality());
						passenger.setBirthDay(FormatData.parseStringToDate(item.getBirthDay()));
						passenger.setBirthPlace(item.getBirthPlace());
						passenger.setTypeOfIdentity(item.getTypeOfIdentity());
						passenger.setSerialNumberOfIdentity(item.getSerialNumberOfIdentity());
//						passenger.setPortOfEmbarkation(item.getPortOfEmbarkation());
//						passenger.setPortOfDisembarkation(item.getPortOfDisembarkation());
//						passenger.setIsTransit(FormatData.convertToInt(item.getIsTransit()));
						
						TempPassengerDetailsLocalServiceUtil.addTempPassengerDetails(passenger);
					} catch (Exception e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	public void insert2TempDocumentInland(InlandPassengerList passengerList, Header header) throws SystemException {
		TempDocument tempDocument = new TempDocument();
		
		tempDocument.setDocumentTypeCode((String.valueOf(header.getSubject().getDocumentType())));
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentName(FormatData.convertToLong(passengerList.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(passengerList.getDocumentYear()));
		tempDocument.setUserCreated(passengerList.getUserCreated());
//		tempDocument.set(passengerList.getisArrival());
		tempDocument.setShipName(passengerList.getNameOfShip());
//		tempDocument.set(passengerList.getPortOfArrivalCode());
//		tempDocument.set(passengerList.getAnchorageCode());
//		tempDocument.set(passengerList.getDateOfArrival());
		//tempDocument.setImo(passengerList.getIMONumber());
	//	tempDocument.setCallSign(passengerList.getCallSign());
//		tempDocument.set(passengerList.getVoyageNumber());
		//tempDocument.setStateCode(passengerList.getFlagStateOfShip());

		
		tempDocument.setLastModifiedDate(new Date());
		
		BusinessUtils.insert2InlanTempDocument(tempDocument, header, passengerList);
	}
}
