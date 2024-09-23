package vn.gt.tichhop.message.haiquan2giaothong.inland;

import java.util.Date;
import java.util.List;




import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempCrewDetailsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.inland.InlandCrewLists;
import vn.nsw.model.inland.InlandCrewLists.CrewList.Crew;

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
public class InlandCrewList2Temp {
	
	

	public boolean insert2Temp(InlandCrewLists crewList, Header header) throws Exception {

		try {

			insert2TempDocument(crewList, header);

			TempCrewList object = new TempCrewList();

			object.setRequestCode(header.getReference().getMessageId());
			object.setDocumentName(FormatData.convertToLong(crewList.getDocumentName()));
			object.setDocumentYear(FormatData.convertToInt(crewList.getDocumentYear()));
			object.setUserCreated(crewList.getUserCreated());
			object.setIsArrival(FormatData.convertToInt(crewList.getIsArrival()));
			object.setNameOfShip(crewList.getNameOfShip());
//			object.setIMONumber(crewList.getIMONumber());
//			object.setCallSign(crewList.getCallSign());
//			object.setVoyageNumber(crewList.getVoyageNumber());
//			object.setPortOfArrivalCode(crewList.getPortOfArrivalCode());
			// object.set(crewList.getAnchorageCode());
//			object.setDateOfArrival(FormatData.parseStringToDate(crewList.getDateOfArrival()));
//			object.setFlagStateOfShip(crewList.getFlagStateOfShip());
//			object.setLastPortOfCallCode(crewList.getLastPortOfCallCode());
			object.setSignPlace(crewList.getSignPlace());
			object.setSignDate(FormatData.parseStringToDate(crewList.getSignDate()));
			object.setMasterSigned(FormatData.convertToInt(crewList.getMasterSigned()));
			object.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));

			// object.setId(CounterLocalServiceUtil.increment(TEMP_CREW_LIST));

			TempCrewListLocalServiceUtil.addTempCrewList(object);

			List<Crew> crews = null;
			if (crewList.getCrewList() != null) {
				crews = crewList.getCrewList().getCrew();
			}
			if (crews != null && crews.size() > 0) {
				for (Crew item : crews) {
					TempCrewDetails details = new TempCrewDetails();
					details.setRequestCode(header.getReference().getMessageId());
					// details.setId(CounterLocalServiceUtil.increment(TEMP_CREW_DETAILS));
					details.setCrewCode(item.getCrewCode());
					details.setFamilyName(item.getFamilyName());
					details.setGivenName(item.getGivenName());
					details.setRankCode(item.getRankCode());
					details.setPassportNumber(item.getPassportNumber());
//					details.setNationality(item.getNationality());
//					details.setDateOfBirth(FormatData.parseStringToDate(item.getDateOfBirth()));
//					details.setPlaceOfBirth(item.getPlaceOfBirth());
//					details.setPassportNumber(item.getPassportNumber());
//					details.setPassportTypeCode(item.getPassportTypeCode());
					// details.setPassportExpiredDate(FormatData.parseStringToDate(item.get));

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

	public void insert2TempDocument(InlandCrewLists object, Header header) throws Exception {

		TempDocument tempDocument = new TempDocument();

		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));
		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentName(FormatData.convertToLong(object.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(object.getDocumentYear()));
		tempDocument.setUserCreated(object.getUserCreated());
		// tempDocument.set(object.getIsArrival());
		tempDocument.setShipName(object.getNameOfShip());
		tempDocument.setStateCode("VN");
//		tempDocument.setImo(object.getIMONumber());
//		tempDocument.setCallSign(object.getCallSign());

		tempDocument.setLastModifiedDate(new Date());

		BusinessUtils.insert2InlanTempDocument(tempDocument, header, object);
	}
}
