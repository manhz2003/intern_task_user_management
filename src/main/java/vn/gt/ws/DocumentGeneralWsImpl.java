//package vn.gt.ws;
//
//import javax.jws.WebService;
//
//import vn.gt.dao.nhapcanh.model.CrewList;
//import vn.gt.dao.nhapcanh.model.CrewListSoap;
//import vn.gt.dao.nhapcanh.model.Document;
//import vn.gt.dao.nhapcanh.model.DocumentGeneral;
//import vn.gt.dao.nhapcanh.model.DocumentGeneralSoap;
//import vn.gt.dao.nhapcanh.model.DocumentSoap;
//import vn.gt.dao.nhapcanh.model.PassengerList;
//import vn.gt.dao.nhapcanh.model.PassengerListSoap;
//import vn.gt.dao.nhapcanh.model.ResponseBoSoap;
//import vn.gt.dao.nhapcanh.model.impl.CrewListImpl;
//import vn.gt.dao.nhapcanh.model.impl.DocumentGeneralImpl;
//import vn.gt.dao.nhapcanh.model.impl.DocumentImpl;
//import vn.gt.dao.nhapcanh.model.impl.PassengerListImpl;
//import vn.gt.dao.nhapcanh.service.CrewListLocalServiceUtil;
//import vn.gt.dao.nhapcanh.service.DocumentGeneralLocalServiceUtil;
//import vn.gt.dao.nhapcanh.service.DocumentLocalServiceUtil;
//import vn.gt.dao.nhapcanh.service.PassengerListLocalServiceUtil;
//
//import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
//
//todo ko thay table
//
//
//@WebService(targetNamespace = "http://ws.gt.vn/", endpointInterface = "vn.gt.ws.IDocumentGeneral", portName = "DocumentGeneralWsImplPort", serviceName = "DocumentGeneralWsImplService")
//public class DocumentGeneralWsImpl implements IDocumentGeneral {
//	private static final String success = "Success";
//	private static final String error = "Error";
//
//	@Override
//	public ResponseBoSoap addDocument(DocumentSoap documentSoap) {
//		ResponseBoSoap ResponseBoSoap = new ResponseBoSoap();
//		try {
//
//			Document document = DocumentGeneralWsImpl.toModel(documentSoap);
//			Long id = CounterLocalServiceUtil.increment(Document.class
//					.getName());
//			document.setId(id);
//			DocumentLocalServiceUtil.addDocument(document);
//			ResponseBoSoap.setIsSuccess(1);
//			ResponseBoSoap.setErrorMessage(success);
//			return ResponseBoSoap;
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			ResponseBoSoap.setIsSuccess(0);
//			ResponseBoSoap.setErrorMessage(error);
//			return ResponseBoSoap;
//		}
//
//	}
//
//
//	@Override
//	public ResponseBoSoap updateDocument(DocumentSoap documentSoap) {
//		ResponseBoSoap ResponseBoSoap = new ResponseBoSoap();
//		try {
//
//			Document document = DocumentGeneralWsImpl.toModel(documentSoap);
//
//			DocumentLocalServiceUtil.updateDocument(document);
//			ResponseBoSoap.setIsSuccess(1);
//			ResponseBoSoap.setErrorMessage(success);
//			return ResponseBoSoap;
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			ResponseBoSoap.setIsSuccess(0);
//			ResponseBoSoap.setErrorMessage(error);
//
//			return ResponseBoSoap;
//		}
//	}
//
//	@Override
//	public ResponseBoSoap deleteDocument(DocumentSoap documentSoap) {
//		ResponseBoSoap ResponseBoSoap = new ResponseBoSoap();
//		try {
//
//			Document document = DocumentGeneralWsImpl.toModel(documentSoap);
//
//			DocumentLocalServiceUtil.deleteDocument(document);
//			ResponseBoSoap.setIsSuccess(1);
//			ResponseBoSoap.setErrorMessage(success);
//			return ResponseBoSoap;
//		} catch (Exception e) {
//			e.printStackTrace();
//			ResponseBoSoap.setIsSuccess(0);
//			ResponseBoSoap.setErrorMessage(error);
//			return ResponseBoSoap;
//		}
//	}
//
//	@Override
//	public ResponseBoSoap addDocumentGeneral(DocumentGeneralSoap documentGeneralSoap) {
//		ResponseBoSoap ResponseBoSoap = new ResponseBoSoap();
//		try {
//			DocumentGeneral documentGeneral = DocumentGeneralImpl
//					.toModel(documentGeneralSoap);
//			Long id = CounterLocalServiceUtil.increment(DocumentGeneral.class
//					.getName());
//			documentGeneral.setId(id);
//			DocumentGeneralLocalServiceUtil.addDocumentGeneral(documentGeneral);
//			ResponseBoSoap.setErrorMessage(success);
//			ResponseBoSoap.setIsSuccess(1);
//			return ResponseBoSoap;
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			ResponseBoSoap.setIsSuccess(0);
//			ResponseBoSoap.setErrorMessage(error);
//			return ResponseBoSoap;
//		}
//
//	}
//
//	@Override
//	public ResponseBoSoap addCrewList(CrewListSoap crewListSoap) {
//		ResponseBoSoap ResponseBoSoap = new ResponseBoSoap();
//		try {
//
//			CrewList crewList = DocumentGeneralWsImpl.toModel(crewListSoap);
//			Long id = CounterLocalServiceUtil.increment(CrewList.class
//					.getName());
//			crewList.setId(id);
//			CrewListLocalServiceUtil.addCrewList(crewList);
//			ResponseBoSoap.setIsSuccess(1);
//			ResponseBoSoap.setErrorMessage(success);
//			return ResponseBoSoap;
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			ResponseBoSoap.setIsSuccess(0);
//			ResponseBoSoap.setErrorMessage(error);
//			return ResponseBoSoap;
//		}
//
//	}
//
//	@Override
//	public ResponseBoSoap addPassengerList(PassengerListSoap passengerListSoap) {
//		ResponseBoSoap ResponseBoSoap = new ResponseBoSoap();
//		try {
//
//			PassengerList passengerList = DocumentGeneralWsImpl.toModel(passengerListSoap);
//			Long id = CounterLocalServiceUtil.increment(PassengerList.class
//					.getName());
//			passengerList.setId(id);
//			PassengerListLocalServiceUtil.addPassengerList(passengerList);
//			ResponseBoSoap.setIsSuccess(1);
//			ResponseBoSoap.setErrorMessage(success);
//			return ResponseBoSoap;
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			ResponseBoSoap.setIsSuccess(0);
//			ResponseBoSoap.setErrorMessage(error);
//			return ResponseBoSoap;
//		}
//	}
//
//	public static PassengerList toModel(PassengerListSoap  model ) {
//		PassengerList soapModel = new PassengerList();
//		soapModel.setId(model.getId());
//		soapModel.setPassengerCode(model.getPassengerCode());
//		soapModel.setDocumentNo(model.getDocumentNo());
//		soapModel.setFullName(model.getFullName());
//		soapModel.setStateCode(model.getStateCode());
//		soapModel.setBirthDay(model.getBirthDay());
//		soapModel.setBirthPlace(model.getBirthPlace());
//		soapModel.setPassportTypeCode(model.getPassportTypeCode());
//		soapModel.setPassPortNo(model.getPassPortNo());
//		soapModel.setPortCheckInCode(model.getPortCheckInCode());
//		soapModel.setPortCheckOutCode(model.getPortCheckOutCode());
//		soapModel.setIsTransit(model.getIsTransit());
//		soapModel.setCreateDate(model.getCreateDate());
//		soapModel.setModifyDate(model.getModifyDate());
//
//		return soapModel;
//	}
//
//	public static CrewList toModel(CrewListSoap  model ) {
//		CrewList soapModel = new CrewList();
//		soapModel.setId(model.getId());
//		soapModel.setCrewCode(model.getCrewCode());
//		soapModel.setDocumentNo(model.getDocumentNo());
//		soapModel.setStateCode(model.getStateCode());
//		soapModel.setBirthDay(model.getBirthDay());
//		soapModel.setBirthPlace(model.getBirthPlace());
//		soapModel.setFamilyName(model.getFamilyName());
//		soapModel.setFullName(model.getFullName());
//		soapModel.setGivenName(model.getGivenName());
//		soapModel.setPassportNumber(model.getPassportNumber());
//		soapModel.setPassportType(model.getPassportType());
//		soapModel.setRankCode(model.getRankCode());
//		soapModel.setCreateDate(model.getCreateDate());
//		soapModel.setModifyDate(model.getModifyDate());
//
//		return soapModel;
//	}
//
//	public static Document toModel(DocumentSoap soapModel) {
//		Document model = new Document();
//
//		model.setId(soapModel.getId());
//		model.setDocumentName(soapModel.getDocumentName());
//		model.setUserCreated(soapModel.getUserCreated());
//		model.setDocumentTypeCode(soapModel.getDocumentTypeCode());
//		model.setCallSign(soapModel.getCallSign());
//		model.setPreDocumentName(soapModel.getPreDocumentName());
//		model.setCreatedDate(soapModel.getCreatedDate());
//		model.setLastModifiedDate(soapModel.getLastModifiedDate());
//
//		return model;
//	}
//
//}
