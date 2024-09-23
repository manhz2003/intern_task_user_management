/**
 * 
 */
package vn.gt.utils.validation;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;
import vn.nsw.Header;
import vn.nsw.model.HealthQuanrantineDeclare;
import vn.nsw.model.HealthQuanrantineDeclare.AttachmentDeclarationHealth.Attachment;
import vn.nsw.model.HealthQuanrantineDeclare.DeclarationOfHealth;
import vn.nsw.model.HealthQuanrantineDeclare.HealthCrewPassengerList.HealthCrewPassenger;
import vn.nsw.model.HealthQuanrantineDeclare.HealthQuestion;

/**
 * @author win_64
 *
 */
public class HealthQuanrantineDeclareValidation {
	public boolean validation(HealthQuanrantineDeclare healthQuanrantineDeclare, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		int [] maLoi4={5,6};
		int [] maLoi5={3,4};
		boolean status = true;
		//SignPlace
				if(healthQuanrantineDeclare.getSignPlace() != null && healthQuanrantineDeclare.getSignPlace().length() > 0){
					status = ValidationUtils.checkValidation(healthQuanrantineDeclare.getSignPlace(), logMessageValidation, "56", "SignPlace", "75", maLoi4, 500, status);
				}
		//SignDate
				if (healthQuanrantineDeclare.getSignDate() != null && !FormatData.isThisDateValid(healthQuanrantineDeclare.getSignDate())) {
					status = false;
					logMessageValidation.setTagProperty("SignDate");
					logMessageValidation.setDataValidation("N56776: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007",""));
					ValidationUtils.addLogMessageValidation(logMessageValidation);
				}
				
		//MasterSigned
				if(healthQuanrantineDeclare.getMasterSigned() != null && healthQuanrantineDeclare.getMasterSigned().length() >0){
					status = ValidationUtils.checkValidationNumber(healthQuanrantineDeclare.getMasterSigned(), logMessageValidation, "56", "MasterSigned", "77", maLoi5, 0, 9, 0, status);
				}
		return status;
	}
	
	private boolean validation(DeclarationOfHealth declarationOfHealth, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
		int [] maLoi1={1,3,4};
		int [] maLoi2={1,5,6};
		int [] maLoi3={1,7};
		int [] maLoi4={5,6};
		int [] maLoi6={1,2,5,6};
//DocumentName
		status = ValidationUtils.checkValidationNumber(declarationOfHealth.getDocumentName(), logMessageValidation, "56", "DocumentName", "03", maLoi1, 0, 9, 0, status);
			 
//DocumentYear
		status = ValidationUtils.checkValidationNumber(declarationOfHealth.getDocumentYear(), logMessageValidation, "56", "DocumentYear", "04", maLoi1, 0, 9, 0, status);
		
//UserCreated
		status = ValidationUtils.checkValidation(declarationOfHealth.getUserCreated(), logMessageValidation, "56", "UserCreated", "05", maLoi2, 14, status);
	
//SubmittedPortCode
		status = ValidationUtils.checkValidation(declarationOfHealth.getSubmittedPortCode(), logMessageValidation, "56", "SubmittedPortCode", "06", maLoi2, 5, status);

//DateSubmitted
		status = ValidationUtils.checkValidation(declarationOfHealth.getDateSubmitted(), logMessageValidation, "56", "DateSubmitted", "07", maLoi3, 100, status);

//NameOfShip
		status = ValidationUtils.checkValidation(declarationOfHealth.getNameOfShip(), logMessageValidation, "56", "NameOfShip", "08", maLoi2, 100, status);
		
//Registration
		status = ValidationUtils.checkValidation(declarationOfHealth.getRegistration(), logMessageValidation, "56", "Registration", "09", maLoi2, 250, status);
		
//IMONumber
		status = ValidationUtils.checkValidation(declarationOfHealth.getIMONumber(), logMessageValidation, "56", "IMONumber", "10", maLoi2, 7, status);

//ArrivingFrom
		status = ValidationUtils.checkValidationDanhMuc(declarationOfHealth.getArrivingFrom(), logMessageValidation, "56", "ArrivingFrom", "11", maLoi6, 5, 11, status);

//SailingTo
		status = ValidationUtils.checkValidationDanhMuc(declarationOfHealth.getSailingTo(), logMessageValidation, "56", "SailingTo", "12", maLoi6, 5, 11, status);

//Nationality
		status = ValidationUtils.checkValidationDanhMuc(declarationOfHealth.getNationality(), logMessageValidation, "56", "Nationality", "13", maLoi6, 2, 11, status);

//MasterName
		status = ValidationUtils.checkValidation(declarationOfHealth.getMasterName(), logMessageValidation, "56", "MasterName", "14", maLoi2, 150, status);
		
//GrossTonnage
		status = ValidationUtils.checkValidationNumber(declarationOfHealth.getGrossTonnage(), logMessageValidation, "56", "GrossTonnage", "15", maLoi1, 1, 6, 2, status);

//GrossTonnageUnit
		if(declarationOfHealth.getGrossTonnageUnit() != null && declarationOfHealth.getGrossTonnageUnit().length() >0){
		status = ValidationUtils.checkValidation(declarationOfHealth.getGrossTonnageUnit(), logMessageValidation, "56", "GrossTonnageUnit", "16", maLoi4, 4, status);
		}
//Tonnage
		status = ValidationUtils.checkValidationNumber(declarationOfHealth.getTonnage(), logMessageValidation, "56", "Tonnage", "17", maLoi1, 1, 6, 2, status);

//TonnageUnit
		if(declarationOfHealth.getTonnageUnit() != null && declarationOfHealth.getTonnageUnit().length() >0){
		status = ValidationUtils.checkValidation(declarationOfHealth.getTonnageUnit(), logMessageValidation, "56", "TonnageUnit", "18", maLoi4, 4, status);
		}
//CertificateCarried
		status = ValidationUtils.checkValidationNumber(declarationOfHealth.getCertificateCarried(), logMessageValidation, "56", "CertificateCarried", "19", maLoi1, 0, 1, 0, status);

//IssuedAt
		status = ValidationUtils.checkValidation(declarationOfHealth.getIssuedAt(), logMessageValidation, "56", "IssuedAt", "20", maLoi2, 150, status);
		
//IssueDate
		status = ValidationUtils.checkValidation(declarationOfHealth.getIssueDate(), logMessageValidation, "56", "IssueDate", "21", maLoi3, 150, status);

//ReInspectionRequired
		status = ValidationUtils.checkValidationNumber(declarationOfHealth.getReInspectionRequired(), logMessageValidation, "56", "ReInspectionRequired", "22", maLoi1, 0, 1, 0, status);

//IsShipVisitedWHO
		status = ValidationUtils.checkValidationNumber(declarationOfHealth.getIsShipVisitedWHO(), logMessageValidation, "56", "IsShipVisitedWHO", "23", maLoi1, 0, 1, 0, status);

//VisitedWHOPortCode
		status = ValidationUtils.checkValidationDanhMuc(declarationOfHealth.getVisitedWHOPortCode(), logMessageValidation, "56", "VisitedWHOPortCode", "24", maLoi6, 2, 9, status);

//DateOfVisitedWHO
		status = ValidationUtils.checkValidation(declarationOfHealth.getDateOfVisitedWHO(), logMessageValidation, "56", "DateOfVisitedWHO", "25", maLoi3, 150, status);

//ListPortName
		status = ValidationUtils.checkValidation(declarationOfHealth.getListPortName(), logMessageValidation, "56", "ListPortName", "26", maLoi2, 500, status);
		
//DoctorName
		if(declarationOfHealth.getDoctorName() != null && declarationOfHealth.getDoctorName().length() >0){
		status = ValidationUtils.checkValidation(declarationOfHealth.getDoctorName(), logMessageValidation, "56", "DoctorName", "27", maLoi4, 100, status);
		}
//DoctorSignDate
		if(declarationOfHealth.getDoctorSignDate() != null && !FormatData.isThisDateValid(declarationOfHealth.getDoctorSignDate())){
			status = false;
			logMessageValidation.setTagProperty("DoctorSignDate");
			logMessageValidation.setDataValidation("N56728: "+ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007",""));
			ValidationUtils.addLogMessageValidation(logMessageValidation);
		}
		
		return status;
	}		
		


	
	private boolean validationlist(HealthCrewPassenger healthCrewPassenger, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
		int [] maLoi1={1,3,4};
		int [] maLoi2={1,5,6};
		int [] maLoi3={1,7};
		int [] maLoi6={1,2,5,6};

//HealthCrewPassengerCode
		status = ValidationUtils.checkValidation(healthCrewPassenger.getHealthCrewPassengerCode(), logMessageValidation, "56", "HealthCrewPassengerCode", "31", maLoi2, 12, status);

//PassengerOrCrewCode
		status = ValidationUtils.checkValidation(healthCrewPassenger.getPassengerOrCrewCode(), logMessageValidation, "56", "PassengerOrCrewCode", "32", maLoi2, 12, status);

//PassengerOrCrewName
		status = ValidationUtils.checkValidation(healthCrewPassenger.getPassengerOrCrewCode(), logMessageValidation, "56", "PassengerOrCrewName", "32a", maLoi2, 50, status);
		
//ClassOrRating
		status = ValidationUtils.checkValidationNumber(healthCrewPassenger.getClassOrRating(), logMessageValidation, "56", "ClassOrRating", "33", maLoi1, 0, 1, 0, status);

//StateVisitedCode
		status = ValidationUtils.checkValidationDanhMuc(healthCrewPassenger.getStateVisitedCode(), logMessageValidation, "56", "StateVisitedCode", "34", maLoi6, 2, 11, status);

//PortVisitedCode
		status = ValidationUtils.checkValidationDanhMuc(healthCrewPassenger.getPortVisitedCode(), logMessageValidation, "56", "PortVisitedCode", "35", maLoi6, 5, 9, status);

//FromDate
		status = ValidationUtils.checkValidation(healthCrewPassenger.getFromDate(), logMessageValidation, "56", "FromDate", "36", maLoi3, 150, status);

//ToDate
		status = ValidationUtils.checkValidation(healthCrewPassenger.getToDate(), logMessageValidation, "56", "ToDate", "37", maLoi3, 150, status);

		return status;
	}
	
	
	private boolean validationlist(HealthQuestion healthQuestion, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
		int [] maLoi1={1,3,4};
		int [] maLoi2={1,5,6};
		int [] maLoi4={5,6};
		int [] maLoi5={3,4};

//HealthQuestionCode
		status = ValidationUtils.checkValidation(healthQuestion.getHealthQuestionCode(), logMessageValidation, "56", "HealthQuestionCode", "39", maLoi2, 12, status);

//PersonDied
		status = ValidationUtils.checkValidationNumber(healthQuestion.getPersonDied(), logMessageValidation, "56", "PersonDied", "40", maLoi1, 0, 1, 0, status);

//PersonDiedNo		
		status = ValidationUtils.checkValidationNumber(healthQuestion.getPersonDiedNo(), logMessageValidation, "56", "PersonDiedNo", "41", maLoi1, 0, 5, 0, status);

//PersonDiedReport
		if(healthQuestion.getPersonDiedReport() != null && healthQuestion.getPersonDiedReport().length() >0){
		status = ValidationUtils.checkValidation(healthQuestion.getPersonDiedReport(), logMessageValidation, "56", "PersonDiedReport", "42", maLoi4, 500, status);
		}
//IsInfectious
		status = ValidationUtils.checkValidationNumber(healthQuestion.getIsInfectious(), logMessageValidation, "56", "IsInfectious", "43", maLoi1, 0, 1, 0, status);

//InfectiousReport
		if(healthQuestion.getInfectiousReport() != null && healthQuestion.getInfectiousReport().length() >0){
		status = ValidationUtils.checkValidation(healthQuestion.getInfectiousReport(), logMessageValidation, "56", "InfectiousReport", "44", maLoi4, 500, status);
		}
//IllPassengersGreaterNomal
		status = ValidationUtils.checkValidationNumber(healthQuestion.getIllPassengersGreaterNomal(), logMessageValidation, "56", "IllPassengersGreaterNomal", "45", maLoi1, 0, 1, 0, status);

//IllPassengersNo
		status = ValidationUtils.checkValidationNumber(healthQuestion.getIllPassengersNo(), logMessageValidation, "56", "IllPassengersNo", "46", maLoi1, 0, 9, 0, status);

//IllPersonsOnBoard
		status = ValidationUtils.checkValidationNumber(healthQuestion.getIllPersonsOnBoard(), logMessageValidation, "56", "IllPersonsOnBoard", "47", maLoi1, 0, 1, 0, status);

//IllPersonsReport
		if(healthQuestion.getIllPersonsReport() != null && healthQuestion.getIllPersonsReport().length() >0){
		status = ValidationUtils.checkValidationNumber(healthQuestion.getIllPersonsReport(), logMessageValidation, "56", "IllPersonsReport", "48", maLoi5, 0, 1, 0, status);
		}
//MedicalPractitionerConsulted
		status = ValidationUtils.checkValidationNumber(healthQuestion.getMedicalPractitionerConsulted(), logMessageValidation, "56", "MedicalPractitionerConsulted", "49", maLoi1, 0, 1, 0, status);

//MedicalTreatmentOrAdvice
		status = ValidationUtils.checkValidation(healthQuestion.getMedicalTreatmentOrAdvice(), logMessageValidation, "56", "MedicalTreatmentOrAdvice", "50", maLoi2, 500, status);

//InfectionOrSpreadOfDisease
		status = ValidationUtils.checkValidationNumber(healthQuestion.getInfectionOrSpreadOfDisease(), logMessageValidation, "56", "InfectionOrSpreadOfDisease", "51", maLoi1, 0, 1, 0, status);

//InfectionsReport
		if(healthQuestion.getInfectionsReport() != null && healthQuestion.getInfectionsReport().length() >0){
		status = ValidationUtils.checkValidation(healthQuestion.getInfectionsReport(), logMessageValidation, "56", "InfectionsReport", "52", maLoi4, 500, status);
		}
//IsSanitary
		status = ValidationUtils.checkValidationNumber(healthQuestion.getIsSanitary(), logMessageValidation, "56", "IsSanitary", "53", maLoi1, 0, 1, 0, status);

//SanitaryDes
		status = ValidationUtils.checkValidation(healthQuestion.getSanitaryDes(), logMessageValidation, "56", "SanitaryDes", "54", maLoi2, 500, status);

//IsStowaways
		status = ValidationUtils.checkValidationNumber(healthQuestion.getIsStowaways(), logMessageValidation, "56", "IsStowaways", "55", maLoi1, 0, 1, 0, status);

//JoinShip
		status = ValidationUtils.checkValidation(healthQuestion.getJoinShip(), logMessageValidation, "56", "JoinShip", "56", maLoi2, 250, status);

//IsAnimal
		status = ValidationUtils.checkValidationNumber(healthQuestion.getIsAnimal(), logMessageValidation, "56", "IsAnimal", "57", maLoi1, 0, 1, 0, status);

		return status;
	}
	
	private boolean validationlist(Attachment attachment, Header header, String requestDirection) throws Exception {
		LogMessageValidation logMessageValidation = new LogMessageValidation();
		logMessageValidation.setRequestCode(header.getReference().getMessageId());
		logMessageValidation.setRequestDirection(requestDirection);
		logMessageValidation.setDocumentType(String.valueOf(header.getSubject().getDocumentType()));
		logMessageValidation.setDocumentName(header.getSubject().getReference());
		logMessageValidation.setDocumentYear(header.getSubject().getDocumentYear());
		boolean status = true;
		int [] maLoi1={1,3,4};
		int [] maLoi2={1,5,6};
		int [] maLoi3={1,7};
		int [] maLoi6={1,2,5,6};

//AttachmentCode
		status = ValidationUtils.checkValidation(attachment.getAttachmentCode(), logMessageValidation, "56", "AttachmentCode", "60", maLoi2, 12, status);

//PassengerOrCrewCode
		status = ValidationUtils.checkValidation(attachment.getPassengerOrCrewCode(), logMessageValidation, "56", "PassengerOrCrewCode", "61", maLoi2, 12, status);

//Name
		status = ValidationUtils.checkValidation(attachment.getName(), logMessageValidation, "56", "Name", "62", maLoi2, 100, status);

//Age
		status = ValidationUtils.checkValidation(attachment.getAge(), logMessageValidation, "56", "Age", "63", maLoi2, 9, status);

//Sex
		status = ValidationUtils.checkValidationNumber(attachment.getSex(), logMessageValidation, "56", "Sex", "64", maLoi1, 0, 1, 0, status);

//Nationality
		status = ValidationUtils.checkValidationDanhMuc(attachment.getNationality(), logMessageValidation, "56", "Nationality", "65", maLoi6, 2, 11, status);
		
//PortJoinVesselCode
		status = ValidationUtils.checkValidationDanhMuc(attachment.getPortJoinVesselCode(), logMessageValidation, "56", "PortJoinVesselCode", "66", maLoi6, 5, 9, status);
		
//DateJoinVessel
		status = ValidationUtils.checkValidation(attachment.getDateJoinVessel(), logMessageValidation, "56", "DateJoinVessel", "67", maLoi3, 150, status);

//ClassOrRating
		status = ValidationUtils.checkValidationNumber(attachment.getClassOrRating(), logMessageValidation, "56", "ClassOrRating", "68", maLoi1, 0, 1, 0, status);

//NatureOfIllness
		status = ValidationUtils.checkValidation(attachment.getNatureOfIllness(), logMessageValidation, "56", "NatureOfIllness", "69", maLoi2, 150, status);

//DateOfOnsetOfSymptom
		status = ValidationUtils.checkValidation(attachment.getDateOfOnsetOfSymptom(), logMessageValidation, "56", "DateOfOnsetOfSymptom", "70", maLoi3, 150, status);

//ReportedMedicalOfficer
		status = ValidationUtils.checkValidationNumber(attachment.getReportedMedicalOfficer(), logMessageValidation, "56", "ReportedMedicalOfficer", "71", maLoi1, 0, 1, 0, status);

//DisposalOfCase
		status = ValidationUtils.checkValidation(attachment.getDisposalOfCase(), logMessageValidation, "56", "DisposalOfCase", "72", maLoi2, 150, status);

//MedicinesOrOther
		status = ValidationUtils.checkValidation(attachment.getMedicinesOrOther(), logMessageValidation, "56", "MedicinesOrOther", "73", maLoi2, 150, status);

//Comments
		status = ValidationUtils.checkValidation(attachment.getComments(), logMessageValidation, "56", "Comments", "74", maLoi2, 500, status);

		return status;
	}
}

