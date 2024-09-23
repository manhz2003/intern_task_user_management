package vn.gt.tichhop.message.haiquan2giaothong;

import java.util.Date;

import com.fds.nsw.nghiepvu.model.TempAnimalQuarantine;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempAnimalQuarantine;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempAnimalQuarantineLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.DeclarationForAnimalQuarantine;




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
public class DeclarationForAnimalQuarantine2Temp {
	
	
	
	public boolean insert2Temp(DeclarationForAnimalQuarantine animalQuarantine, Header header) throws Exception{
		
		try{
			
			insert2TempDocument(animalQuarantine, header);
			
			TempAnimalQuarantine object=new TempAnimalQuarantine();
			
			// chua build service bang temp
			
			object.setDocumentName(FormatData.convertToLong(animalQuarantine.getDocumentName()));
			object.setDocumentYear(FormatData.convertToInt(animalQuarantine.getDocumentYear()));
//			object.set(animalQuarantine.getDocumentYear());
			object.setUserCreated(animalQuarantine.getUserCreated());
			object.setNameOfShip(animalQuarantine.getNameOfShip());
			object.setFlagStateOfShip(animalQuarantine.getFlagStateOfShip());
			object.setNumberOfCrew(FormatData.convertToInt(animalQuarantine.getNumberOfCrew()));
			object.setNumberOfPassengers(FormatData.convertToInt(animalQuarantine.getNumberOfPassengers()));
			object.setLastPortOfCallCode(animalQuarantine.getLastPortOfCallCode());
			object.setNextPortOfCallCode(animalQuarantine.getNextPortOfCallCode());
			object.setAnimalNameFirst(animalQuarantine.getAnimalNameFirst());
			object.setAnimalNameBetween(animalQuarantine.getAnimalNameBetween());
			object.setAnimalNameThis(animalQuarantine.getAnimalNameThis());
			object.setNameOfMaster(animalQuarantine.getNameOfMaster());
			object.setSignPlace(animalQuarantine.getSignPlace());
			object.setSignDate(FormatData.parseStringToDate(animalQuarantine.getSignDate()));
			object.setMasterSigned(FormatData.convertToInt(animalQuarantine.getMasterSigned()));
			object.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));

//			object.setId(CounterLocalServiceUtil.increment(TEMP_ANIMAL_QUARANTINE));
			object.setRequestCode(header.getReference().getMessageId());
			
			TempAnimalQuarantineLocalServiceUtil.addTempAnimalQuarantine(object);
			
			return true;
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	public void insert2TempDocument(DeclarationForAnimalQuarantine object, Header header) throws Exception{
		
		TempDocument tempDocument = new TempDocument();
		tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));

		tempDocument.setRequestCode(header.getReference().getMessageId());
		tempDocument.setDocumentName(FormatData.convertToLong(object.getDocumentName()));
		tempDocument.setDocumentYear(FormatData.convertToInt(object.getDocumentYear()));
		tempDocument.setUserCreated(object.getUserCreated());
		tempDocument.setShipName(object.getNameOfShip());
		tempDocument.setStateCode(object.getFlagStateOfShip());
		tempDocument.setShipCaptain(object.getNameOfMaster());
		
		tempDocument.setLastModifiedDate(new Date());
		
		BusinessUtils.insert2TempDocument(tempDocument, header, object);
	}
	
	
}
