package vn.gt.tichhop.message.haiquan2giaothong;

import java.util.Date;


import com.fds.nsw.nghiepvu.model.TempDocument;

import com.fds.nsw.nghiepvu.model.TempDocument;

import vn.gt.tichhop.message.BusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.DeclarationForPlantQuarantine;

import com.fds.nsw.kernel.exception.SystemException;



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
public class DeclarationForPlantQuarantine2Temp {
	
	
	
	public boolean insert2Temp(DeclarationForPlantQuarantine plantQuarantine, Header header) throws SystemException {
	
//		try {
//			
//			insert2TempDocument(plantQuarantine, header);
//			
//			TempDeclarationForPlantQuarantine tempPlantQuarantine = new TempDeclarationForPlantQuarantine();
//			tempPlantQuarantine.setRequestCode(header.getReference().getMessageId());
//			
//			tempPlantQuarantine.setDocumentName(FormatData.convertToLong(plantQuarantine.getDocumentName()));
//			tempPlantQuarantine.setDocumentYear(FormatData.convertToInt(plantQuarantine.getDocumentYear()));
//			tempPlantQuarantine.setUserCreated(plantQuarantine.getUserCreated());
//			tempPlantQuarantine.setNameOfShip(plantQuarantine.getNameOfShip());
//			tempPlantQuarantine.setFlagStateOfShip(plantQuarantine.getFlagStateOfShip());
//			tempPlantQuarantine.setNameOfMaster(plantQuarantine.getNameOfMaster());
//			tempPlantQuarantine.setDoctorName(plantQuarantine.getDoctorName());
//			tempPlantQuarantine.setNumberOfCrew(FormatData.convertToInt(plantQuarantine.getNumberOfCrew()));
//			tempPlantQuarantine.setNumberOfPassengers(FormatData.convertToInt(plantQuarantine.getNumberOfPassengers()));
//			tempPlantQuarantine.setLastPortOfCallCode(plantQuarantine.getLastPortOfCallCode());
//			tempPlantQuarantine.setNextPortOfCallCode(plantQuarantine.getNextPortOfCallCode());
//			tempPlantQuarantine.setFirstPortOfLoadingCode(plantQuarantine.getFirstPortOfLoadingCode());
//			tempPlantQuarantine.setDateOfdeparture(FormatData.parseStringToDate(plantQuarantine.getDateOfDeparture()));
//			tempPlantQuarantine.setPlantNameFirst(plantQuarantine.getPlantNameFirst());
//			tempPlantQuarantine.setPlantNameBetween(plantQuarantine.getPlantNameBetween());
//			tempPlantQuarantine.setPlantNameThis(plantQuarantine.getPlantNameThis());
//			tempPlantQuarantine.setSignPlace(plantQuarantine.getSignPlace());
//			tempPlantQuarantine.setSignDate(FormatData.parseStringToDate(plantQuarantine.getSignDate()));
//			tempPlantQuarantine.setMasterSigned(FormatData.convertToInt(plantQuarantine.getMasterSigned()));
//			tempPlantQuarantine.setRequestState(CheckBusinessUtil.checkTrangThaiBanKhai(header));
//			
//			TempDeclarationForPlantQuarantineLocalServiceUtil.addTempDeclarationForPlantQuarantine(tempPlantQuarantine);
//			return true;
//			
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			e.printStackTrace();
//		}
		return false;
	}
		public void insert2TempDocument(DeclarationForPlantQuarantine plantQuarantine, Header header) throws SystemException {
			TempDocument tempDocument = new TempDocument();
			tempDocument.setDocumentTypeCode(String.valueOf(header.getSubject().getDocumentType()));

			tempDocument.setRequestCode(header.getReference().getMessageId());
			tempDocument.setDocumentName(FormatData.convertToLong(plantQuarantine.getDocumentName()));
			tempDocument.setDocumentYear(FormatData.convertToInt(plantQuarantine.getDocumentYear()));
			tempDocument.setUserCreated(plantQuarantine.getUserCreated());
			tempDocument.setShipName(plantQuarantine.getNameOfShip());
			tempDocument.setStateCode(plantQuarantine.getFlagStateOfShip());
			tempDocument.setShipCaptain(plantQuarantine.getNameOfMaster());
			
			tempDocument.setLastModifiedDate(new Date());
			
			BusinessUtils.insert2TempDocument(tempDocument, header, plantQuarantine);
	}
}
