//package vn.gt.ws;
//
//import javax.jws.WebService;
//import javax.xml.ws.RequestWrapper;
//import javax.xml.ws.ResponseWrapper;
//
//import vn.gt.dao.nhapcanh.model.CrewListSoap;
//import vn.gt.dao.nhapcanh.model.DocumentGeneralSoap;
//import vn.gt.dao.nhapcanh.model.DocumentSoap;
//import vn.gt.dao.nhapcanh.model.PassengerListSoap;
//import vn.gt.dao.nhapcanh.model.ResponseBoSoap;
//
//
//
//
//@WebService(name = "IDocumentGeneral", targetNamespace = "http://ws.gt.vn/")
//public interface IDocumentGeneral {
//
//	@RequestWrapper(className = "vn.gt.ws.jaxws.AddDocumentGeneral", localName = "addDocumentGeneral", targetNamespace = "http://ws.gt.vn/")
//	@ResponseWrapper(className = "vn.gt.ws.jaxws.AddDocumentGeneralResponse", localName = "addDocumentGeneralResponse", targetNamespace = "http://ws.gt.vn/")
//	public ResponseBoSoap addDocumentGeneral(DocumentGeneralSoap documentGeneralSoap);
//
//	@RequestWrapper(className = "vn.gt.ws.jaxws.AddDocument", localName = "addDocument", targetNamespace = "http://ws.gt.vn/")
//	@ResponseWrapper(className = "vn.gt.ws.jaxws.AddDocumentResponse", localName = "addDocumentResponse", targetNamespace = "http://ws.gt.vn/")
//	public ResponseBoSoap addDocument(DocumentSoap documentSoap);
//
//	@RequestWrapper(className = "vn.gt.ws.jaxws.UpdateDocument", localName = "updateDocument", targetNamespace = "http://ws.gt.vn/")
//	@ResponseWrapper(className = "vn.gt.ws.jaxws.UpdateDocumentResponse", localName = "updateDocumentResponse", targetNamespace = "http://ws.gt.vn/")
//	public ResponseBoSoap updateDocument(DocumentSoap documentSoap);
//
//	@RequestWrapper(className = "vn.gt.ws.jaxws.DeleteDocument", localName = "deleteDocument", targetNamespace = "http://ws.gt.vn/")
//	@ResponseWrapper(className = "vn.gt.ws.jaxws.DeleteDocumentResponse", localName = "deleteDocumentResponse", targetNamespace = "http://ws.gt.vn/")
//	public ResponseBoSoap deleteDocument(DocumentSoap documentSoap);
//
//
//	@RequestWrapper(className = "vn.gt.ws.jaxws.AddCrewList", localName = "addCrewList", targetNamespace = "http://ws.gt.vn/")
//	@ResponseWrapper(className = "vn.gt.ws.jaxws.AddCrewListResponse", localName = "addCrewListResponse", targetNamespace = "http://ws.gt.vn/")
//	public ResponseBoSoap addCrewList(CrewListSoap crewListSoap);
//
//	@RequestWrapper(className = "vn.gt.ws.jaxws.AddPassengerList", localName = "addPassengerList", targetNamespace = "http://ws.gt.vn/")
//	@ResponseWrapper(className = "vn.gt.ws.jaxws.AddPassengerListResponse", localName = "addPassengerListResponse", targetNamespace = "http://ws.gt.vn/")
//	public ResponseBoSoap addPassengerList(PassengerListSoap passengerListSoap);
//
//}
