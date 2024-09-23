package vn.gt.portlet.thutuc;

import java.text.SimpleDateFormat;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;

import com.fds.nsw.nghiepvu.model.ResultCertificate;

import vn.gt.dao.result.service.ResultCertificateLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.ParamUtil;


import com.fds.flex.common.ultility.Validator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ResultCertificate2DbUtils {

	

	public static void insertResultCertificateUpgrade(ActionResponse httpReq, ActionRequest resourceRequest, long documentName, int documentYear) throws SystemException {
		
	}
	
	public static void insertResultCertificate(ActionResponse httpReq, ActionRequest resourceRequest, long documentName, int documentYear) throws SystemException {
		
		int docCount = ParamUtil.getInteger(resourceRequest, "docCount");
		String hoTenLogin = ParamUtil.getString(resourceRequest, "hoTenLogin");
		String registerNo;
		String certificateCode;
		String certificateName;
		String certificateIssueDate;
		String certificateExpiredDate;
		String examinationDate;
		String checkboxValue = null;
		String isRender = "true";
		String comment;
		
		
		List<ResultCertificate> lstCertificate;
//todo setRenderParameter
		//httpReq.setRenderParameter("docCount", String.valueOf(docCount));
		//httpReq.setRenderParameter("hoTenLogin", hoTenLogin);
		//httpReq.setRenderParameter("isRender", isRender);

		//
		List<ResultCertificate> resultCertificates39 = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYearAndCertificateCode(/*1602597-documentName*/ documentName, documentYear, "39");
		for(ResultCertificate ettResult39: resultCertificates39){
			String examinedChecked = ParamUtil.getString(resourceRequest, "examinedChecked" + ettResult39.getId());
			String mandatoryChecked = ParamUtil.getString(resourceRequest, "mandatoryChecked_" + ettResult39.getId());
			ResultCertificate ettUpdate39 = ResultCertificateLocalServiceUtil.fetchResultCertificate(ettResult39.getId());
			if(examinedChecked.equalsIgnoreCase("on")){
				ettUpdate39.setIsExamined(1);
			}else{
				ettUpdate39.setIsExamined(0);
			}
			if(mandatoryChecked.equalsIgnoreCase("on")){
				ettUpdate39.setMandatory(1);
			}else{
				ettUpdate39.setMandatory(0);
			}
			ettUpdate39.setCertificateNo(ParamUtil.getString(resourceRequest, "registerNo_" + ettResult39.getId()));
			ettUpdate39.setComment(ParamUtil.getString(resourceRequest, "comment_" + ettResult39.getId()));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateIssueDate_" + ettResult39.getId())))ettUpdate39.setCertificateIssueDate(ParamUtil.getDate(resourceRequest, "certificateIssueDate_" + ettResult39.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + ettResult39.getId())))ettUpdate39.setCertificateExpiredDate(ParamUtil.getDate(resourceRequest, "certificateExpiredDate_" + ettResult39.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "examinationDate_" + ettResult39.getId())))ettUpdate39.setExaminationDate(ParamUtil.getDate(resourceRequest, "examinationDate_" + ettResult39.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdate39);
		}
		
		//
		List<ResultCertificate> resultCertificates40 = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYearAndCertificateCode(/*1602597-documentName*/ documentName, documentYear, "40");
		for(ResultCertificate ettResult40: resultCertificates40){
			String examinedChecked = ParamUtil.getString(resourceRequest, "examinedChecked" + ettResult40.getId());
			String mandatoryChecked = ParamUtil.getString(resourceRequest, "mandatoryChecked_" + ettResult40.getId());
			ResultCertificate ettUpdate40 = ResultCertificateLocalServiceUtil.fetchResultCertificate(ettResult40.getId());
			if(examinedChecked.equalsIgnoreCase("on")){
				ettUpdate40.setIsExamined(1);
			}else{
				ettUpdate40.setIsExamined(0);
			}
			if(mandatoryChecked.equalsIgnoreCase("on")){
				ettUpdate40.setMandatory(1);
			}else{
				ettUpdate40.setMandatory(0);
			}
			ettUpdate40.setCertificateNo(ParamUtil.getString(resourceRequest, "registerNo_" + ettResult40.getId()));
			ettUpdate40.setComment(ParamUtil.getString(resourceRequest, "comment_" + ettResult40.getId()));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateIssueDate_" + ettResult40.getId())))ettUpdate40.setCertificateIssueDate(ParamUtil.getDate(resourceRequest, "certificateIssueDate_" + ettResult40.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + ettResult40.getId())))ettUpdate40.setCertificateExpiredDate(ParamUtil.getDate(resourceRequest, "certificateExpiredDate_" + ettResult40.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "examinationDate_" + ettResult40.getId())))ettUpdate40.setExaminationDate(ParamUtil.getDate(resourceRequest, "examinationDate_" + ettResult40.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdate40);
		}
		
		
		
		//
		List<ResultCertificate> resultCertificates60 = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYearAndCertificateCode(/*1602597-documentName*/ documentName, documentYear, "60");
		for(ResultCertificate ettResult60: resultCertificates60){
			String examinedChecked = ParamUtil.getString(resourceRequest, "examinedChecked" + ettResult60.getId());
			String mandatoryChecked = ParamUtil.getString(resourceRequest, "mandatoryChecked_" + ettResult60.getId());
			ResultCertificate ettUpdate60 = ResultCertificateLocalServiceUtil.fetchResultCertificate(ettResult60.getId());
			if(examinedChecked.equalsIgnoreCase("on")){
				ettUpdate60.setIsExamined(1);
			}else{
				ettUpdate60.setIsExamined(0);
			}
			if(mandatoryChecked.equalsIgnoreCase("on")){
				ettUpdate60.setMandatory(1);
			}else{
				ettUpdate60.setMandatory(0);
			}
			ettUpdate60.setCertificateNo(ParamUtil.getString(resourceRequest, "registerNo_" + ettResult60.getId()));
			ettUpdate60.setComment(ParamUtil.getString(resourceRequest, "comment_" + ettResult60.getId()));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateIssueDate_" + ettResult60.getId())))ettUpdate60.setCertificateIssueDate(ParamUtil.getDate(resourceRequest, "certificateIssueDate_" + ettResult60.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + ettResult60.getId())))ettUpdate60.setCertificateExpiredDate(ParamUtil.getDate(resourceRequest, "certificateExpiredDate_" + ettResult60.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "examinationDate_" + ettResult60.getId())))ettUpdate60.setExaminationDate(ParamUtil.getDate(resourceRequest, "examinationDate_" + ettResult60.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdate60);
		}
		
		
		List<ResultCertificate> resultCertificates48 = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYearAndCertificateCode(/*1602597-documentName*/ documentName, documentYear, "48");
		for(ResultCertificate ettResult48: resultCertificates48){
			String examinedChecked = ParamUtil.getString(resourceRequest, "examinedChecked" + ettResult48.getId());
			String mandatoryChecked = ParamUtil.getString(resourceRequest, "mandatoryChecked_" + ettResult48.getId());
			ResultCertificate ettUpdate48 = ResultCertificateLocalServiceUtil.fetchResultCertificate(ettResult48.getId());
			if(examinedChecked.equalsIgnoreCase("on")){
				ettUpdate48.setIsExamined(1);
			}else{
				ettUpdate48.setIsExamined(0);
			}
			if(mandatoryChecked.equalsIgnoreCase("on")){
				ettUpdate48.setMandatory(1);
			}else{
				ettUpdate48.setMandatory(0);
			}
			ettUpdate48.setCertificateNo(ParamUtil.getString(resourceRequest, "registerNo_" + ettResult48.getId()));
			ettUpdate48.setComment(ParamUtil.getString(resourceRequest, "comment_" + ettResult48.getId()));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateIssueDate_" + ettResult48.getId())))ettUpdate48.setCertificateIssueDate(ParamUtil.getDate(resourceRequest, "certificateIssueDate_" + ettResult48.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + ettResult48.getId())))ettUpdate48.setCertificateExpiredDate(ParamUtil.getDate(resourceRequest, "certificateExpiredDate_" + ettResult48.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "examinationDate_" + ettResult48.getId())))ettUpdate48.setExaminationDate(ParamUtil.getDate(resourceRequest, "examinationDate_" + ettResult48.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdate48);
		}
		
		
		List<ResultCertificate> resultCertificates49 = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYearAndCertificateCode(/*1602597-documentName*/ documentName, documentYear, "49");
		for(ResultCertificate ettResult49: resultCertificates49){
			String examinedChecked = ParamUtil.getString(resourceRequest, "examinedChecked" + ettResult49.getId());
			String mandatoryChecked = ParamUtil.getString(resourceRequest, "mandatoryChecked_" + ettResult49.getId());
			ResultCertificate ettUpdate49 = ResultCertificateLocalServiceUtil.fetchResultCertificate(ettResult49.getId());
			if(examinedChecked.equalsIgnoreCase("on")){
				ettUpdate49.setIsExamined(1);
			}else{
				ettUpdate49.setIsExamined(0);
			}
			if(mandatoryChecked.equalsIgnoreCase("on")){
				ettUpdate49.setMandatory(1);
			}else{
				ettUpdate49.setMandatory(0);
			}
			ettUpdate49.setCertificateNo(ParamUtil.getString(resourceRequest, "registerNo_" + ettResult49.getId()));
			ettUpdate49.setComment(ParamUtil.getString(resourceRequest, "comment_" + ettResult49.getId()));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateIssueDate_" + ettResult49.getId())))ettUpdate49.setCertificateIssueDate(ParamUtil.getDate(resourceRequest, "certificateIssueDate_" + ettResult49.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + ettResult49.getId())))ettUpdate49.setCertificateExpiredDate(ParamUtil.getDate(resourceRequest, "certificateExpiredDate_" + ettResult49.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "examinationDate_" + ettResult49.getId())))ettUpdate49.setExaminationDate(ParamUtil.getDate(resourceRequest, "examinationDate_" + ettResult49.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdate49);
		}
		
		
		List<ResultCertificate> resultCertificates50 = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYearAndCertificateCode(/*1602597-documentName*/ documentName, documentYear, "50");
		for(ResultCertificate ettResult50: resultCertificates50){
			String examinedChecked = ParamUtil.getString(resourceRequest, "examinedChecked" + ettResult50.getId());
			String mandatoryChecked = ParamUtil.getString(resourceRequest, "mandatoryChecked_" + ettResult50.getId());
			ResultCertificate ettUpdate50 = ResultCertificateLocalServiceUtil.fetchResultCertificate(ettResult50.getId());
			if(examinedChecked.equalsIgnoreCase("on")){
				ettUpdate50.setIsExamined(1);
			}else{
				ettUpdate50.setIsExamined(0);
			}
			if(mandatoryChecked.equalsIgnoreCase("on")){
				ettUpdate50.setMandatory(1);
			}else{
				ettUpdate50.setMandatory(0);
			}
			ettUpdate50.setCertificateNo(ParamUtil.getString(resourceRequest, "registerNo_" + ettResult50.getId()));
			ettUpdate50.setComment(ParamUtil.getString(resourceRequest, "comment_" + ettResult50.getId()));
			ettUpdate50.setDescription(ParamUtil.getString(resourceRequest, "description_" + ettResult50.getId()));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateIssueDate_" + ettResult50.getId())))ettUpdate50.setCertificateIssueDate(ParamUtil.getDate(resourceRequest, "certificateIssueDate_" + ettResult50.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + ettResult50.getId())))ettUpdate50.setCertificateExpiredDate(ParamUtil.getDate(resourceRequest, "certificateExpiredDate_" + ettResult50.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			if(Validator.isNotNull(ParamUtil.getString(resourceRequest, "examinationDate_" + ettResult50.getId())))ettUpdate50.setExaminationDate(ParamUtil.getDate(resourceRequest, "examinationDate_" + ettResult50.getId(), new SimpleDateFormat("HH:mm dd/MM/yyyy")));
			ResultCertificateLocalServiceUtil.updateResultCertificate(ettUpdate50);
		}
		
		
		ResultCertificate certificate = null;
		for (int i = 1; i <= docCount; i++) {
			
			checkboxValue = ParamUtil.getString(resourceRequest, "checked_" + i);
			//httpReq.setRenderParameter("checked_" + i, checkboxValue);
			
			registerNo = ParamUtil.getString(resourceRequest, "registerNo_" + i);
			//httpReq.setRenderParameter("registerNo_" + i, registerNo);
			
			comment = ParamUtil.getString(resourceRequest, "comment_" + i);
			//httpReq.setRenderParameter("comment_" + i, comment);
			
			certificateCode = ParamUtil.getString(resourceRequest, "certificateCode_" + i);
			//httpReq.setRenderParameter("certificateCode_" + i, certificateCode);
			
			certificateName = ParamUtil.getString(resourceRequest, "certificateName_" + i);
			//httpReq.setRenderParameter("certificateName_" + i, certificateName);
			
			certificateIssueDate = ParamUtil.getString(resourceRequest, "certificateIssueDate_" + i);
			
			//httpReq.setRenderParameter("certificateIssueDate_" + i, certificateIssueDate);
			
			certificateExpiredDate = ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + i);
			//httpReq.setRenderParameter("certificateExpiredDate_" + i, certificateExpiredDate);
			
			examinationDate = ParamUtil.getString(resourceRequest, "examinationDate_" + i);
			//httpReq.setRenderParameter("examinationDate_" + i, examinationDate);
			
			ResultCertificate ettUpdate = ResultCertificateLocalServiceUtil.fetchByF_BY4(documentName, documentYear, certificateCode, registerNo);
			if(Validator.isNotNull(ettUpdate)){
				
			}
			
			lstCertificate = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYearAndCertificateCode(documentName, documentYear,
					certificateCode);
			
			
			if ((lstCertificate != null) && (lstCertificate.size() > 0) 
					&& (!(certificateCode.equals("60") 
							|| certificateCode.equals("39") 
							|| certificateCode.equals("40") 
							|| certificateCode.equals("48")
							|| certificateCode.equals("49")
							|| certificateCode.equals("50")))
									) {
				certificate = lstCertificate.get(0);
				certificate.setDocumentName(documentName);
				certificate.setDocumentYear(documentYear);
				certificate.setCertificateNo(registerNo);
				certificate.setComment(comment);
				certificate.setCertificateCode(certificateCode);
				if ((certificateIssueDate != null) && (!certificateIssueDate.trim().isEmpty())) {
					certificate.setCertificateIssueDate(FormatData.parseStringToDate(certificateIssueDate));
				}
				if ((certificateExpiredDate != null) && (!certificateExpiredDate.trim().isEmpty())) {
					certificate.setCertificateExpiredDate(FormatData.parseStringToDate(certificateExpiredDate));
				}
				if ((examinationDate != null) && (!examinationDate.trim().isEmpty())) {
					certificate.setExaminationDate(FormatData.parseStringToDate(examinationDate));
				}
				certificate.setApprovalName(hoTenLogin);
				if ((checkboxValue != null) && (checkboxValue.equalsIgnoreCase("on"))) {
					certificate.setIsExamined(1);
				} else {
					certificate.setIsExamined(0);
				}
				String examinedChecked = ParamUtil.getString(resourceRequest, "examinedChecked" + i);
				String mandatoryChecked = ParamUtil.getString(resourceRequest, "mandatoryChecked_" + i);
				if(examinedChecked.equalsIgnoreCase("on")){
					certificate.setIsExamined(1);
				}else{
					certificate.setIsExamined(0);
				}
				if(mandatoryChecked.equalsIgnoreCase("on")){
					certificate.setMandatory(1);
				}else{
					certificate.setMandatory(0);
				}
				try {
					ResultCertificateLocalServiceUtil.updateResultCertificate(certificate);
				} catch (SystemException e) {
					e.printStackTrace();
				}
			} else {
				certificate = new ResultCertificate();
				certificate.setDocumentName(documentName);
				certificate.setDocumentYear(documentYear);
				certificate.setCertificateNo(registerNo);
				certificate.setComment(comment);				
				certificate.setCertificateCode(certificateCode);
				
				if ((certificateIssueDate != null) && (!certificateIssueDate.trim().isEmpty())) {
					certificate.setCertificateIssueDate(FormatData.parseStringToDate(certificateIssueDate));
				}
				if ((certificateExpiredDate != null) && (!certificateExpiredDate.trim().isEmpty())) {
					certificate.setCertificateExpiredDate(FormatData.parseStringToDate(certificateExpiredDate));
				}
				if ((examinationDate != null) && (!examinationDate.trim().isEmpty())) {
					certificate.setExaminationDate(FormatData.parseStringToDate(examinationDate));
				}
				certificate.setApprovalName(hoTenLogin);
				
				if ((checkboxValue != null) && (checkboxValue.equalsIgnoreCase("on"))) {
					certificate.setIsExamined(1);
				} else {
					certificate.setIsExamined(0);
				}
				String examinedChecked = ParamUtil.getString(resourceRequest, "examinedChecked" + i);
				String mandatoryChecked = ParamUtil.getString(resourceRequest, "mandatoryChecked_" + i);
				if(examinedChecked.equalsIgnoreCase("on")){
					certificate.setIsExamined(1);
				}else{
					certificate.setIsExamined(0);
				}
				if(mandatoryChecked.equalsIgnoreCase("on")){
					certificate.setMandatory(1);
				}else{
					certificate.setMandatory(0);
				}
				try {
					ResultCertificateLocalServiceUtil.addResultCertificate(certificate);
				} catch (SystemException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	public static void __insertMandatoryResultCertificate(ActionResponse httpReq, ActionRequest resourceRequest, long documentName, int documentYear) {
		
		int docCount = ParamUtil.getInteger(resourceRequest, "docCount");
		String hoTenLogin = ParamUtil.getString(resourceRequest, "hoTenLogin");
		String registerNo;
		String certificateCode;
		String certificateName;
		String certificateIssueDate;
		String certificateExpiredDate;
		String examinationDate;		
		String mandatoryCheckboxValue = null;
		String isRender = "true";
		String comment;
		
		List<ResultCertificate> lstCertificate;
		List<ResultCertificate> lstCertificate39_40_59_60;
		
		//httpReq.setRenderParameter("docCount", String.valueOf(docCount));
		//httpReq.setRenderParameter("hoTenLogin", hoTenLogin);
		//httpReq.setRenderParameter("isRender", isRender);
		
		ResultCertificate certificate = null;
		for (int i = 1; i <= docCount; i++) {
			
			mandatoryCheckboxValue = ParamUtil.getString(resourceRequest, "mandatoryChecked_" + i);
			//httpReq.setRenderParameter("mandatoryChecked_" + i, mandatoryCheckboxValue);
			
		
			comment = ParamUtil.getString(resourceRequest, "comment_" + i);
			//httpReq.setRenderParameter("comment_" + i, comment);
			
			registerNo = ParamUtil.getString(resourceRequest, "registerNo_" + i);
			//httpReq.setRenderParameter("registerNo_" + i, registerNo);
			
			certificateCode = ParamUtil.getString(resourceRequest, "certificateCode_" + i);
			//httpReq.setRenderParameter("certificateCode_" + i, certificateCode);
			
			certificateName = ParamUtil.getString(resourceRequest, "certificateName_" + i);
			//httpReq.setRenderParameter("certificateName_" + i, certificateName);
			
			certificateIssueDate = ParamUtil.getString(resourceRequest, "certificateIssueDate_" + i);
			
			//httpReq.setRenderParameter("certificateIssueDate_" + i, certificateIssueDate);
			
			certificateExpiredDate = ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + i);
			//httpReq.setRenderParameter("certificateExpiredDate_" + i, certificateExpiredDate);
			
			examinationDate = ParamUtil.getString(resourceRequest, "examinationDate_" + i);
			//httpReq.setRenderParameter("examinationDate_" + i, examinationDate);
			
			lstCertificate = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYearAndCertificateCode(documentName, documentYear,
					certificateCode);			
			if ((lstCertificate != null) && (lstCertificate.size() > 0)) {
				certificate = lstCertificate.get(0);
				certificate.setDocumentName(documentName);
				certificate.setDocumentYear(documentYear);
				certificate.setCertificateNo(registerNo);
				certificate.setComment(comment);
				certificate.setCertificateCode(certificateCode);
				if ((certificateIssueDate != null) && (!certificateIssueDate.trim().isEmpty())) {
					certificate.setCertificateIssueDate(FormatData.parseStringToDate(certificateIssueDate));
				}
				if ((certificateExpiredDate != null) && (!certificateExpiredDate.trim().isEmpty())) {
					certificate.setCertificateExpiredDate(FormatData.parseStringToDate(certificateExpiredDate));
				}
				if ((examinationDate != null) && (!examinationDate.trim().isEmpty())) {
					certificate.setExaminationDate(FormatData.parseStringToDate(examinationDate));
				}
				certificate.setApprovalName(hoTenLogin);	
				if ((mandatoryCheckboxValue != null) && (mandatoryCheckboxValue.equalsIgnoreCase("on"))) {
					certificate.setMandatory(1);
				//	System.out.println("====VAO certificate.setMandatory(1);aaaaaaaaa");
				} else {
					certificate.setMandatory(0);
				}				
				try {
					ResultCertificateLocalServiceUtil.updateResultCertificate(certificate);
				//	System.out.println("update thanh cong;aaaaaaaaa");
				} catch (SystemException e) {
					e.printStackTrace();
				}
			} else {
				certificate = new ResultCertificate();
				certificate.setDocumentName(documentName);
				certificate.setDocumentYear(documentYear);
				certificate.setCertificateNo(registerNo);
				certificate.setCertificateCode(certificateCode);
				certificate.setComment(comment);
				if ((certificateIssueDate != null) && (!certificateIssueDate.trim().isEmpty())) {
					certificate.setCertificateIssueDate(FormatData.parseStringToDate(certificateIssueDate));
				}
				if ((certificateExpiredDate != null) && (!certificateExpiredDate.trim().isEmpty())) {
					certificate.setCertificateExpiredDate(FormatData.parseStringToDate(certificateExpiredDate));
				}
				if ((examinationDate != null) && (!examinationDate.trim().isEmpty())) {
					certificate.setExaminationDate(FormatData.parseStringToDate(examinationDate));
				}
				certificate.setApprovalName(hoTenLogin);				
				
				if ((mandatoryCheckboxValue != null) && (mandatoryCheckboxValue.equalsIgnoreCase("on"))) {
					certificate.setMandatory(1);
					
					//System.out.println("====VAO certificate.setMandatory(1);");
				} else {
					certificate.setMandatory(0);
				}
								
				try {
					ResultCertificateLocalServiceUtil.addResultCertificate(certificate);
				} catch (SystemException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		//		
		lstCertificate39_40_59_60 = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
		for (ResultCertificate resultCert: lstCertificate39_40_59_60)
		{
			if (resultCert.getCertificateCode().equals("39") || resultCert.getCertificateCode().equals("40") || resultCert.getCertificateCode().equals("60") )
			{
				mandatoryCheckboxValue = ParamUtil.getString(resourceRequest, "mandatoryChecked_" + resultCert.getId());
				//httpReq.setRenderParameter("mandatoryChecked_" + resultCert.getId(), mandatoryCheckboxValue);
				
			
				comment = ParamUtil.getString(resourceRequest, "comment_" + resultCert.getId());
				//httpReq.setRenderParameter("comment_" + resultCert.getId(), comment);
				
				registerNo = ParamUtil.getString(resourceRequest, "registerNo_" + resultCert.getId());
				//httpReq.setRenderParameter("registerNo_" + resultCert.getId(), registerNo);
				
				certificateCode = ParamUtil.getString(resourceRequest, "certificateCode_" + resultCert.getId());
				//httpReq.setRenderParameter("certificateCode_" + resultCert.getId(), certificateCode);
				
				certificateName = ParamUtil.getString(resourceRequest, "certificateName_" + resultCert.getId());
				//httpReq.setRenderParameter("certificateName_" + resultCert.getId(), certificateName);
				
				certificateIssueDate = ParamUtil.getString(resourceRequest, "certificateIssueDate_" + resultCert.getId());
				
				//httpReq.setRenderParameter("certificateIssueDate_" + resultCert.getId(), certificateIssueDate);
				
				certificateExpiredDate = ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + resultCert.getId());
				//httpReq.setRenderParameter("certificateExpiredDate_" + resultCert.getId(), certificateExpiredDate);
				
				examinationDate = ParamUtil.getString(resourceRequest, "examinationDate_" + resultCert.getId());
				//httpReq.setRenderParameter("examinationDate_" + resultCert.getId(), examinationDate);
				
				certificate.setDocumentName(documentName);
				certificate.setDocumentYear(documentYear);
				certificate.setCertificateNo(registerNo);
				certificate.setComment(comment);
				certificate.setCertificateCode(certificateCode);
				if ((certificateIssueDate != null) && (!certificateIssueDate.trim().isEmpty())) {
					certificate.setCertificateIssueDate(FormatData.parseStringToDate(certificateIssueDate));
				}
				if ((certificateExpiredDate != null) && (!certificateExpiredDate.trim().isEmpty())) {
					certificate.setCertificateExpiredDate(FormatData.parseStringToDate(certificateExpiredDate));
				}
				if ((examinationDate != null) && (!examinationDate.trim().isEmpty())) {
					certificate.setExaminationDate(FormatData.parseStringToDate(examinationDate));
				}
				certificate.setApprovalName(hoTenLogin);	
				if ((mandatoryCheckboxValue != null) && (mandatoryCheckboxValue.equalsIgnoreCase("on"))) {
					certificate.setMandatory(1);
				
				} else {
					certificate.setMandatory(0);
				}				
				try {
					ResultCertificateLocalServiceUtil.updateResultCertificate(certificate);
				
				} catch (SystemException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public static void __insertExaminedResultCertificate(ActionResponse httpReq, ActionRequest resourceRequest, long documentName, int documentYear) {
		
		int docCount = ParamUtil.getInteger(resourceRequest, "docCount");
		String hoTenLogin = ParamUtil.getString(resourceRequest, "hoTenLogin");
		String registerNo;
		String certificateCode;
		String certificateName;
		String certificateIssueDate;
		String certificateExpiredDate;
		String examinationDate;
		String examinedCheckboxValue = null;		
		String isRender = "true";
		String comment;
		
		List<ResultCertificate> lstCertificate;
		List<ResultCertificate> lstCertificate39_40_59_60;
		
		//httpReq.setRenderParameter("docCount", String.valueOf(docCount));
		//httpReq.setRenderParameter("hoTenLogin", hoTenLogin);
		//httpReq.setRenderParameter("isRender", isRender);
		
		ResultCertificate certificate = null;
		
		for (int i = 1; i <= docCount; i++) {
			
			examinedCheckboxValue = ParamUtil.getString(resourceRequest, "examniedChecked_" + i);
			//httpReq.setRenderParameter("examniedChecked_" + i, examinedCheckboxValue);			
			
			registerNo = ParamUtil.getString(resourceRequest, "registerNo_" + i);
			//httpReq.setRenderParameter("registerNo_" + i, registerNo);
			
			comment = ParamUtil.getString(resourceRequest, "comment_" + i);
			//httpReq.setRenderParameter("comment_" + i, comment);
			
			certificateCode = ParamUtil.getString(resourceRequest, "certificateCode_" + i);
			//httpReq.setRenderParameter("certificateCode_" + i, certificateCode);
			
			certificateName = ParamUtil.getString(resourceRequest, "certificateName_" + i);
			//httpReq.setRenderParameter("certificateName_" + i, certificateName);
			
			certificateIssueDate = ParamUtil.getString(resourceRequest, "certificateIssueDate_" + i);
			
			//httpReq.setRenderParameter("certificateIssueDate_" + i, certificateIssueDate);
			
			certificateExpiredDate = ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + i);
			//httpReq.setRenderParameter("certificateExpiredDate_" + i, certificateExpiredDate);
			
			examinationDate = ParamUtil.getString(resourceRequest, "examinationDate_" + i);
			//httpReq.setRenderParameter("examinationDate_" + i, examinationDate);
			
			lstCertificate = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYearAndCertificateCode(documentName, documentYear,
					certificateCode);
			if ((lstCertificate != null) && (lstCertificate.size() > 0)) {
				certificate = lstCertificate.get(0);
				certificate.setDocumentName(documentName);
				certificate.setDocumentYear(documentYear);
				certificate.setCertificateNo(registerNo);
				certificate.setComment(comment);
				certificate.setCertificateCode(certificateCode);
				if ((certificateIssueDate != null) && (!certificateIssueDate.trim().isEmpty())) {
					certificate.setCertificateIssueDate(FormatData.parseStringToDate(certificateIssueDate));
				}
				if ((certificateExpiredDate != null) && (!certificateExpiredDate.trim().isEmpty())) {
					certificate.setCertificateExpiredDate(FormatData.parseStringToDate(certificateExpiredDate));
				}
				if ((examinationDate != null) && (!examinationDate.trim().isEmpty())) {
					certificate.setExaminationDate(FormatData.parseStringToDate(examinationDate));
				}
				certificate.setApprovalName(hoTenLogin);
				if ((examinedCheckboxValue != null) && (examinedCheckboxValue.equalsIgnoreCase("on"))) {
					certificate.setIsExamined(1);
				} else {
					certificate.setIsExamined(0);
				}
				
				try {
					ResultCertificateLocalServiceUtil.updateResultCertificate(certificate);
				} catch (SystemException e) {
					e.printStackTrace();
				}
			} else {
				certificate = new ResultCertificate();
				certificate.setDocumentName(documentName);
				certificate.setDocumentYear(documentYear);
				certificate.setCertificateNo(registerNo);
				certificate.setCertificateCode(certificateCode);
				certificate.setComment(comment);
				
				if ((certificateIssueDate != null) && (!certificateIssueDate.trim().isEmpty())) {
					certificate.setCertificateIssueDate(FormatData.parseStringToDate(certificateIssueDate));
				}
				if ((certificateExpiredDate != null) && (!certificateExpiredDate.trim().isEmpty())) {
					certificate.setCertificateExpiredDate(FormatData.parseStringToDate(certificateExpiredDate));
				}
				if ((examinationDate != null) && (!examinationDate.trim().isEmpty())) {
					certificate.setExaminationDate(FormatData.parseStringToDate(examinationDate));
				}
				certificate.setApprovalName(hoTenLogin);
				
				if ((examinedCheckboxValue != null) && (examinedCheckboxValue.equalsIgnoreCase("on"))) {
					certificate.setIsExamined(1);
				} else {
					certificate.setIsExamined(0);
				}				
								
				try {
					ResultCertificateLocalServiceUtil.addResultCertificate(certificate);
				} catch (SystemException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		//		
		lstCertificate39_40_59_60 = ResultCertificateLocalServiceUtil.findByDocumentNameAndDocumentYear(documentName, documentYear);
		for (ResultCertificate resultCert: lstCertificate39_40_59_60)
		{
			if (resultCert.getCertificateCode().equals("39") || resultCert.getCertificateCode().equals("40") || resultCert.getCertificateCode().equals("60") )
			{
				comment = ParamUtil.getString(resourceRequest, "comment_" + resultCert.getId());
				//httpReq.setRenderParameter("comment_" + resultCert.getId(), comment);
				
				registerNo = ParamUtil.getString(resourceRequest, "registerNo_" + resultCert.getId());
				//httpReq.setRenderParameter("registerNo_" + resultCert.getId(), registerNo);
				
				certificateCode = ParamUtil.getString(resourceRequest, "certificateCode_" + resultCert.getId());
				//httpReq.setRenderParameter("certificateCode_" + resultCert.getId(), certificateCode);
				
				certificateName = ParamUtil.getString(resourceRequest, "certificateName_" + resultCert.getId());
				//httpReq.setRenderParameter("certificateName_" + resultCert.getId(), certificateName);
				
				certificateIssueDate = ParamUtil.getString(resourceRequest, "certificateIssueDate_" + resultCert.getId());
				
				//httpReq.setRenderParameter("certificateIssueDate_" + resultCert.getId(), certificateIssueDate);
				
				certificateExpiredDate = ParamUtil.getString(resourceRequest, "certificateExpiredDate_" + resultCert.getId());
				//httpReq.setRenderParameter("certificateExpiredDate_" + resultCert.getId(), certificateExpiredDate);
				
				examinationDate = ParamUtil.getString(resourceRequest, "examinationDate_" + resultCert.getId());
				//httpReq.setRenderParameter("examinationDate_" + resultCert.getId(), examinationDate);
				
				certificate.setDocumentName(documentName);
				certificate.setDocumentYear(documentYear);
				certificate.setCertificateNo(registerNo);
				certificate.setComment(comment);
				certificate.setCertificateCode(certificateCode);
				if ((certificateIssueDate != null) && (!certificateIssueDate.trim().isEmpty())) {
					certificate.setCertificateIssueDate(FormatData.parseStringToDate(certificateIssueDate));
				}
				if ((certificateExpiredDate != null) && (!certificateExpiredDate.trim().isEmpty())) {
					certificate.setCertificateExpiredDate(FormatData.parseStringToDate(certificateExpiredDate));
				}
				if ((examinationDate != null) && (!examinationDate.trim().isEmpty())) {
					certificate.setExaminationDate(FormatData.parseStringToDate(examinationDate));
				}
				certificate.setApprovalName(hoTenLogin);	
								
				try {
					ResultCertificateLocalServiceUtil.updateResultCertificate(certificate);
				
				} catch (SystemException e) {
					e.printStackTrace();
				}
			}
		}
		

	}
}
