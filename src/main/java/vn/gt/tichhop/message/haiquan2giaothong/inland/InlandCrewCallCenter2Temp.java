package vn.gt.tichhop.message.haiquan2giaothong.inland;

import java.util.Date;
import java.util.List;

import com.fds.nsw.kernel.exception.SystemException;




import vn.gt.constant.Constants;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import com.fds.nsw.nghiepvu.model.TempCargoItems;
import vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import vn.gt.dao.noticeandmessage.service.TempCrewDetailsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.PTTNDGeneralDeclaration;
import vn.nsw.model.PTTNDGeneralDeclaration.CargoList;
import vn.nsw.model.inland.InlandGeneralDeclaration;
import vn.gt.tichhop.message.MessageType;
import vn.nsw.model.inland.InlandCrewCallCenter;
import vn.nsw.model.inland.InlandCrewCallCenter.Crew;



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
public class InlandCrewCallCenter2Temp {
	
	
	
	
	public boolean insert2TempCallCenterPTTND(InlandCrewCallCenter generalDeclaration, Header header) throws SystemException {
		log.info("---------insert2TempCallCenterPTTND------------");
		try {
			insert2TempDocumentCallCenterPTTND(generalDeclaration, header);
			
			TempGeneralDeclaration object = new TempGeneralDeclaration();
			
			object.setRequestCode(header.getReference().getMessageId());
			object.setDocumentName(header.getSubject().getReference());
			object.setDocumentYear(header.getSubject().getDocumentYear());
			object.setUserCreated(generalDeclaration.getUserCreated());
			object.setIsArrival(generalDeclaration.getIsArrival());
			object.setNameOfShip(generalDeclaration.getNameOfShip());
			object.setNameOfShip("---");
			//object.setShipTypeCode("");
			// object.setIMONumber(generalDeclaration.getIMONumber());
			object.setCallSign(generalDeclaration.getCallSign());
			// object.setVoyageNumber(generalDeclaration.getVoyageNumber());
//			object.setNextProvinceCode(generalDeclaration.getNextProvinceCode());
//			object.setPortOfArrivalCode(generalDeclaration.getPortOfArrivalCode());
			object.setDateOfArrival(FormatData.parseStringToDate(generalDeclaration.getTimeOfDeparture()));
			
			// phai sua lay getByMaritimeReference
			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeReference(object.getMaritimePortCode().toUpperCase());
			if (dmMaritime != null) {
				object.setMaritimePortCode(dmMaritime.getMaritimeCode());
			}
			//object.setMaritimePortCode(generalDeclaration.getMaritimePortCode());
			object.setPortRegionCode("---");
//			object.setPortRegionCode(generalDeclaration.getPortRegionCode());
//			object.setPortHarbourCode(generalDeclaration.getPortHarbourCode());
//			object.setPortWharfCode(generalDeclaration.getPortWharfCode());
			
			
			object.setFlagStateOfShip("VN");
			object.setNameOfMaster(generalDeclaration.getNameOfMaster());
//			object.setLastProvinceCode(generalDeclaration.getLastProvinceCode());
//			object.setLastPortOfCallCode(generalDeclaration.getLastPortOfCallCode());
//			 object.setCertificateOfRegistryNumber(generalDeclaration.getCertificateOfRegistryNumber());
//			 object.setCertificateOfRegistryDate(FormatData.parseStringToDate(generalDeclaration.getCertificateOfRegistryDate()));
//			 object.setCertificateOfRegistryPortName(generalDeclaration.getCertificateOfRegistryPortName());
//			object.setTaxCodeOfShipownersAgents(generalDeclaration.getTaxCodeOfShipownersAgents().trim());
//			object.setNameOfShipownersAgents(generalDeclaration.getNameOfShipownersAgents());
//			object.setShipAgencyAddress(generalDeclaration.getShipAgencyAddress());
			object.setShipAgencyPhone(generalDeclaration.getUserCreated());
//			object.setShipAgencyFax(generalDeclaration.getShipAgencyFax());
//			object.setShipAgencyEmail(generalDeclaration.getShipAgencyEmail());
			
			
//			object.setBriefParticularsOfVoyage(generalDeclaration.getBriefParticularsOfVoyage());
			object.setPositionOfShipInPort(generalDeclaration.getPortName());
//			object.setBriefParticularsOfVoyage(generalDeclaration.getBriefParticularsOfVoyage());
//			object.setBriefDescriptionOfTheCargo(generalDeclaration.getBriefDescriptionOfTheCargo());
//			
			object.setRemarks("");

			object.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
			
			TempGeneralDeclarationLocalServiceUtil.addTempGeneralDeclaration(object);
			
			
			TempCrewList objCrew = new TempCrewList();

			objCrew.setRequestCode(header.getReference().getMessageId());
			objCrew.setDocumentName(header.getSubject().getReference());
			objCrew.setDocumentYear(header.getSubject().getDocumentYear());
			objCrew.setUserCreated(generalDeclaration.getUserCreated());
			objCrew.setIsArrival(generalDeclaration.getIsArrival());
			objCrew.setNameOfShip("---");
//			objCrew.setNameOfShip(generalDeclaration.getNameOfShip());			
//			object.setIMONumber(crewList.getIMONumber());
			objCrew.setCallSign(generalDeclaration.getCallSign());
//			object.setVoyageNumber(crewList.getVoyageNumber());
//			object.setPortOfArrivalCode(crewList.getPortOfArrivalCode());
			// object.set(crewList.getAnchorageCode());
//			object.setDateOfArrival(FormatData.parseStringToDate(crewList.getDateOfArrival()));
//			object.setFlagStateOfShip(crewList.getFlagStateOfShip());
//			object.setLastPortOfCallCode(crewList.getLastPortOfCallCode());
//			object.setSignPlace(crewList.getSignPlace());
//			object.setSignDate(FormatData.parseStringToDate(crewList.getSignDate()));
//			object.setMasterSigned(FormatData.convertToInt(crewList.getMasterSigned()));
			objCrew.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));

			// object.setId(CounterLocalServiceUtil.increment(TEMP_CREW_LIST));

			TempCrewListLocalServiceUtil.addTempCrewList(objCrew);
			
			TempCrewDetails masterdetails = new TempCrewDetails();
			masterdetails.setRequestCode(header.getReference().getMessageId());					
			masterdetails.setCrewCode("0");
			masterdetails.setGivenName(generalDeclaration.getNameOfMaster());			
			masterdetails.setRankCode("R01");
			masterdetails.setPassportNumber(generalDeclaration.getCertificateNumber());
			masterdetails.setNationality("VN");
			TempCrewDetailsLocalServiceUtil.addTempCrewDetails(masterdetails);

			List<Crew> crews = generalDeclaration.getCrew();
			
			if (crews != null && crews.size() > 0) {
				for (Crew item : crews) {
					TempCrewDetails details = new TempCrewDetails();
					details.setRequestCode(header.getReference().getMessageId());					
					details.setCrewCode(item.getCrewCode());
					//details.setFamilyName(item.getFamilyName());
					//details.setGivenName(item.getFullName());
					details.setGivenName("---");
					details.setRankCode("");
					details.setPassportNumber(item.getCrewNumber());
					details.setNationality("VN");
//					details.setDateOfBirth(FormatData.parseStringToDate(item.getDateOfBirth()));
//					details.setPlaceOfBirth(item.getPlaceOfBirth());
//					details.setPassportNumber(item.getPassportNumber());
//					details.setPassportTypeCode(item.getPassportTypeCode());
//					details.setPassportExpiredDate(FormatData.parseStringToDate(item.get));

					try {
						TempCrewDetailsLocalServiceUtil.addTempCrewDetails(details);
					} catch (Exception e) {
						e.printStackTrace();
						log.error(e.getMessage());
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
	public void insert2TempDocumentCallCenterPTTND(InlandCrewCallCenter object, Header header) throws Exception {
		
		TempDocument tempDocument = new TempDocument();
		
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));
		tempDocument.setDocumentName(header.getSubject().getReference());
		tempDocument.setDocumentYear(header.getSubject().getDocumentYear());
		tempDocument.setUserCreated(object.getUserCreated());
		
		// tempDocument.setShipName(object.getNameOfShip());
		// tempDocument.setPortRegionCode(object.getPortName());
		tempDocument.setShipName("---");
		tempDocument.setPortRegionCode("---");
		tempDocument.setShipTypeCode("SMS");		
		tempDocument.setCallSign(object.getCallSign());
		tempDocument.setStateCode("VN");

		tempDocument.setShipCaptain(object.getNameOfMaster());
		
		tempDocument.setShipOwnerCode(header.getFrom().getUnitCode());		 
		
		// phai sua lay getByMaritimeReference
		DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeReference(object.getMaritimePortCode().toUpperCase());
		if (dmMaritime != null) {
			tempDocument.setMaritimeCode(dmMaritime.getMaritimeCode());
		}
		
		tempDocument.setLastModifiedDate(new Date());
		if (header.getSubject().getDocumentType() == MessageType.TAU_RA_PTTND) {
			tempDocument.setShipDateTo(FormatData.parseStringToDate(object.getTimeOfDeparture()));			
		}else
		{
			tempDocument.setShipDateFrom(FormatData.parseStringToDate(object.getTimeOfDeparture()));
		}
		
		BusinessUtils.insert2InlanTempDocument(tempDocument, header, object);
	}
	
}
