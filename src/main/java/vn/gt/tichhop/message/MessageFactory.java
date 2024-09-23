package vn.gt.tichhop.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import vn.gt.tichhop.message.xml.StringUtils;
import vn.gt.tichhop.message.xml.XmlUtils;
import vn.gt.utils.FormatData;
import vn.nsw.fac.RequestInvoiceFactory;
import vn.nsw.fac.RequestTransactionListFactory;
import vn.nsw.fac.ResponseInvoiceFactory;
import vn.nsw.fac.ResponseTransactionListFactory;
import vn.nsw.fac.ResultFactory;
import vn.nsw.fac.AcceptanceForTransitFactory;
import vn.nsw.fac.CargoDeclarationFactory;
import vn.nsw.fac.ConfirmationOfArrivalFactory;
import vn.nsw.fac.CrewEffectsDeclarationFactory;
import vn.nsw.fac.CrewListsFactory;
import vn.nsw.fac.DangerousGoodsManifestFactory;
import vn.nsw.fac.DeclarationForAnimalQuarantineFactory;
import vn.nsw.fac.DeclarationForPlantQuarantineFactory;
import vn.nsw.fac.DmDataItemListFactory;
import vn.nsw.fac.DmHistoryMaritimeListFactory;
import vn.nsw.fac.DmHistoryPilotCategoryListFactory;
import vn.nsw.fac.DmHistoryPilotCompanyListFactory;
import vn.nsw.fac.DmHistoryPilotListFactory;
import vn.nsw.fac.DmHistoryPortHarbourListFactory;
import vn.nsw.fac.DmHistoryPortListFactory;
import vn.nsw.fac.DmHistoryPortRegionListFactory;
import vn.nsw.fac.DmHistoryPortWharfListFactory;
import vn.nsw.fac.DmHistorySecurityOfficeListFactory;
import vn.nsw.fac.DmHistoryShipOwnerListFactory;
import vn.nsw.fac.DmHistoryShipyardListFactory;
import vn.nsw.fac.DmHistoryTugboatCompanyListFactory;
import vn.nsw.fac.DmHistoryTugboatListFactory;
import vn.nsw.fac.DmHistoryVmashipTypeListFactory;
import vn.nsw.fac.DmMaritimeListFactory;
import vn.nsw.fac.DmPortHarbourListFactory;
import vn.nsw.fac.DmPortRegionListFactory;
import vn.nsw.fac.DmPortWharfListFactory;
import vn.nsw.fac.DmShipAgencyListFactory;
import vn.nsw.fac.DmVmaPilotCategoryListFactory;
import vn.nsw.fac.DmVmaPilotCompanyListFactory;
import vn.nsw.fac.DmVmaPilotListFactory;
import vn.nsw.fac.DmVmaSecurityOfficeListFactory;
import vn.nsw.fac.DmVmaShipOwnerListFactory;
import vn.nsw.fac.DmVmaShipTypeListFactory;
import vn.nsw.fac.DmVmaShipyardListFactory;
import vn.nsw.fac.DmVmaTugboatCompanyListFactory;
import vn.nsw.fac.DmVmaTugboatListFactory;
import vn.nsw.fac.FeeApprovedFactory;
import vn.nsw.fac.FeeNoticeFactory;
import vn.nsw.fac.GeneralDeclarationFactory;
import vn.nsw.fac.HealthQuanrantineDeclareFactory;
import vn.nsw.fac.NoticeOfArrivalFactory;
import vn.nsw.fac.PTTNDPassengerListFactory;
import vn.nsw.fac.PTTNDPortclearanceFactory;
import vn.nsw.fac.PassengerListFactory;
import vn.nsw.fac.PermissionForTransitFactory;
import vn.nsw.fac.PortClearanceFactory;
import vn.nsw.fac.ShiftingOrderFactory;
import vn.nsw.fac.ShipSecurityNotificationFactory;
import vn.nsw.fac.ShipsStoresDeclarationFactory;
import vn.nsw.fac.VmaFactory;
import vn.nsw.fac.inland.AttachmentFactory;
import vn.nsw.fac.inland.InlandCrewListsFactory;
import vn.nsw.fac.inland.InlandGeneralDeclarationFactory;
import vn.nsw.fac.inland.InlandNoticeOfArrivalFactory;
import vn.nsw.fac.inland.InlandPassengerListFactory;
import vn.nsw.fac.inland.InlandCrewCallCenterFactory;
import vn.nsw.model.AcceptanceForTransit;
import vn.nsw.model.ArrayOfInvoiceDataWS;
import vn.nsw.model.CargoDeclaration;
import vn.nsw.model.ConfirmationOfArrival;
import vn.nsw.model.CrewEffectsDeclaration;
import vn.nsw.model.CrewLists;
import vn.nsw.model.Customers;
import vn.nsw.model.DangerousGoodsManifest;
import vn.nsw.model.DeclarationForAnimalQuarantine;
import vn.nsw.model.DeclarationForPlantQuarantine;
import vn.nsw.model.FeeApproved;
import vn.nsw.model.FeeNotice;
import vn.nsw.model.GeneralDeclaration;
import vn.nsw.model.HealthQuanrantineDeclare;
import vn.nsw.model.Invoices;
import vn.nsw.model.NoticeOfArrival;
import vn.nsw.model.NoticeOfArrivalCancel;
import vn.nsw.model.PTTNDGeneralDeclaration;
import vn.nsw.model.PassengerList;
import vn.nsw.model.PermissionForTransit;
import vn.nsw.model.PortClearance;
import vn.nsw.model.PortEntryPermit;
import vn.nsw.model.RequestInvoice;
import vn.nsw.model.RequestTransactionList;
import vn.nsw.model.ResponseInvoice;
import vn.nsw.model.ResponseTransactionList;
import vn.nsw.model.ShiftingOrder;
import vn.nsw.model.ShipSecurityNotification;
import vn.nsw.model.ShipsStoresDeclaration;
import vn.nsw.model.XacNhanHuyLenhDieuDong;
import vn.nsw.model.XacNhanThongQuan;
import vn.nsw.model.inland.Attachment;
import vn.nsw.model.inland.InlandCrewLists;
import vn.nsw.model.inland.InlandGeneralDeclaration;
import vn.nsw.model.inland.InlandNoticeOfArrival;
import vn.nsw.model.inland.InlandPassengerList;
import vn.nsw.model.inland.InlandPortClearance;
import vn.nsw.model.inland.InlandShiftingOrder;
import vn.nsw.model.inland.PTTNDAcceptance;
import vn.nsw.model.inland.PTTNDPassengerList;
import vn.nsw.model.inland.PTTNDPortclearance;
import vn.nsw.model.inland.InlandCrewCallCenter;
import vn.nsw.model.PortList;
import vn.nsw.fac.PortListFactory;
import vn.nsw.model.PortHarbourList;
import vn.nsw.model.PortOfAuthorityList;
import vn.nsw.model.PortRegionList;
import vn.nsw.model.PortWharfList;
import vn.nsw.model.ReferencePortList;
import vn.nsw.model.ReferenceGoodsList;
import vn.nsw.model.CommandData;
import vn.nsw.model.Result;



import com.fds.flex.common.ultility.Validator;

@Slf4j
public class MessageFactory {

	// Noi dung gui tra.
	public static final String NOI_DUNG_TRA_VE = "NOI_DUNG_TRA_VE";
	

	public static String createMessageRequest(Messsage messsage) {
		
		Document doc = XmlUtils.getNewDocument();
		Node envelope = doc.createElement("Envelope");
		Node header = doc.createElement("Header");
		envelope.appendChild(header);
		
		Node reference = doc.createElement("Reference");
		header.appendChild(reference);
		
		Node refversion = doc.createElement("version");
		refversion.appendChild(doc.createTextNode(messsage.getRef_version()));
		reference.appendChild(refversion);
		
		Node refmessageId = doc.createElement("messageId");
		refmessageId.appendChild(doc.createTextNode(messsage.getRef_messageId()));
		reference.appendChild(refmessageId);
		
		Node from = doc.createElement("From");
		
		Node frmName = doc.createElement("name");
		frmName.appendChild(doc.createTextNode(messsage.getFrm_name()));
		from.appendChild(frmName);
		
		Node frmIdentity = doc.createElement("identity");
		frmIdentity.appendChild(doc.createTextNode(messsage.getFrm_identity()));
		from.appendChild(frmIdentity);
		
		header.appendChild(from);
		
		Node to = doc.createElement("To");
		
		Node toName = doc.createElement("name");
		toName.appendChild(doc.createTextNode(messsage.getTo_name()));
		to.appendChild(toName);
		
		Node toIdentity = doc.createElement("identity");
		toIdentity.appendChild(doc.createTextNode(messsage.getTo_identity()));
		to.appendChild(toIdentity);
		
		header.appendChild(to);
		
		Node subject = doc.createElement("Subject");
		
		Node documentType = doc.createElement("documentType");
		documentType.appendChild(doc.createTextNode(messsage.getDocumentType()));
		subject.appendChild(documentType);
		
		Node type = doc.createElement("type");
		type.appendChild(doc.createTextNode(messsage.getType()));
		subject.appendChild(type);
		
		Node nfunction = doc.createElement("function");
		nfunction.appendChild(doc.createTextNode(messsage.getFunction()));
		subject.appendChild(nfunction);
		
		Node nreference = doc.createElement("reference");
		nreference.appendChild(doc.createTextNode(messsage.getReference()));
		subject.appendChild(nreference);
		
		Node preReference = doc.createElement("preReference");
		preReference.appendChild(doc.createTextNode(messsage.getPreReference()));
		subject.appendChild(preReference);
		
		Node documentYear = doc.createElement("documentYear");
		documentYear.appendChild(doc.createTextNode(messsage.getDocumentYear()));
		subject.appendChild(documentYear);
		
		Node sendDate = doc.createElement("sendDate");
		sendDate.appendChild(doc.createTextNode(messsage.getSendDate()));
		subject.appendChild(sendDate);
		
		header.appendChild(subject);
		
		Node body = doc.createElement("Body");
		envelope.appendChild(body);
		
		Node content = doc.createElement("Content");
		// if(messsage.getContent() != null &&
		// messsage.getContent().length()>0){
		// content.setTextContent(NOI_DUNG_TRA_VE);
		// }else{
		//
		// content.setTextContent(NOI_DUNG_TRA_VE);
		// }
		
		content.setTextContent(NOI_DUNG_TRA_VE);
		body.appendChild(content);
		doc.appendChild(envelope);
		return getStringFromDocument(doc);
	}
	
	
	//dung.le header new
	public static String createMessageRequestInland(MesssageInland messsage) {
		
		Document doc = XmlUtils.getNewDocument();
		Node envelope = doc.createElement("Envelope");
		Node header = doc.createElement("Header");
		envelope.appendChild(header);
		
		Node reference = doc.createElement("Reference");
		header.appendChild(reference);
		
		Node refversion = doc.createElement("version");
		refversion.appendChild(doc.createTextNode(messsage.getRef_version()));
		reference.appendChild(refversion);
		
		Node refmessageId = doc.createElement("messageId");
		refmessageId.appendChild(doc.createTextNode(messsage.getRef_messageId()));
		reference.appendChild(refmessageId);
		
		
		
		
		Node from = doc.createElement("From");
		
		Node frmName = doc.createElement("name");
		frmName.appendChild(doc.createTextNode(messsage.getFrm_name()));
		from.appendChild(frmName);
		
		Node frmIdentity = doc.createElement("identity");
		frmIdentity.appendChild(doc.createTextNode(messsage.getFrm_identity()));
		from.appendChild(frmIdentity);
		
		if (messsage.getDocumentType().equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)  || messsage.getDocumentType().equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) ){
			Node frmCountryCode = doc.createElement("countryCode");
			frmCountryCode.appendChild(doc.createTextNode(messsage.getFrm_CountryCode()));
			from.appendChild(frmCountryCode);
			
			Node frmMinistryCode = doc.createElement("ministryCode");
			frmMinistryCode.appendChild(doc.createTextNode(messsage.getFrm_MinistryCode()));
			from.appendChild(frmMinistryCode);
			
			Node frmOrganizationCode = doc.createElement("organizationCode");
			frmOrganizationCode.appendChild(doc.createTextNode(messsage.getFrm_OrganizationCode()));
			from.appendChild(frmOrganizationCode);
			
			Node frmUnitCode = doc.createElement("unitCode");
			frmUnitCode.appendChild(doc.createTextNode(messsage.getFrm_UnitCode()));
			from.appendChild(frmUnitCode);
		}
		else 
		{
			Node frmCountryCode = doc.createElement("countrycode");
			frmCountryCode.appendChild(doc.createTextNode(messsage.getFrm_CountryCode()));
			from.appendChild(frmCountryCode);
			
			Node frmMinistryCode = doc.createElement("ministrycode");
			frmMinistryCode.appendChild(doc.createTextNode(messsage.getFrm_MinistryCode()));
			from.appendChild(frmMinistryCode);
			
			Node frmOrganizationCode = doc.createElement("organizationcode");
			frmOrganizationCode.appendChild(doc.createTextNode(messsage.getFrm_OrganizationCode()));
			from.appendChild(frmOrganizationCode);
			
			Node frmUnitCode = doc.createElement("unitcode");
			frmUnitCode.appendChild(doc.createTextNode(messsage.getFrm_UnitCode()));
			from.appendChild(frmUnitCode);
		
		}
		
		
		header.appendChild(from);
		
		Node to = doc.createElement("To");
		
		Node toName = doc.createElement("name");
		toName.appendChild(doc.createTextNode(messsage.getTo_name()));
		to.appendChild(toName);
		
		Node toIdentity = doc.createElement("identity");
		toIdentity.appendChild(doc.createTextNode(messsage.getTo_identity()));
		to.appendChild(toIdentity);
		
		if (messsage.getDocumentType().equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)  || messsage.getDocumentType().equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) ){
			Node toCountryCode = doc.createElement("countryCode");
			toCountryCode.appendChild(doc.createTextNode(messsage.getTo_CountryCode()));
			to.appendChild(toCountryCode);
			
			Node toMinistryCode = doc.createElement("ministryCode");
			toMinistryCode.appendChild(doc.createTextNode(messsage.getTo_MinistryCode()));
			to.appendChild(toMinistryCode);
			
			Node toOrganizationCode = doc.createElement("organizationCode");
			toOrganizationCode.appendChild(doc.createTextNode(messsage.getTo_OrganizationCode()));
			to.appendChild(toOrganizationCode);
			
			Node toUnitCode = doc.createElement("unitCode");
			toUnitCode.appendChild(doc.createTextNode(messsage.getTo_UnitCode()));
			to.appendChild(toUnitCode);
		} else {
			Node toCountryCode = doc.createElement("countrycode");
			toCountryCode.appendChild(doc.createTextNode(messsage.getTo_CountryCode()));
			to.appendChild(toCountryCode);
			
			Node toMinistryCode = doc.createElement("ministrycode");
			toMinistryCode.appendChild(doc.createTextNode(messsage.getTo_MinistryCode()));
			to.appendChild(toMinistryCode);
			
			Node toOrganizationCode = doc.createElement("organizationcode");
			toOrganizationCode.appendChild(doc.createTextNode(messsage.getTo_OrganizationCode()));
			to.appendChild(toOrganizationCode);
			
			Node toUnitCode = doc.createElement("unitcode");
			toUnitCode.appendChild(doc.createTextNode(messsage.getTo_UnitCode()));
			to.appendChild(toUnitCode);
		}
		
		
		header.appendChild(to);
		
		Node subject = doc.createElement("Subject");
		
		Node documentType = doc.createElement("documentType");
		documentType.appendChild(doc.createTextNode(messsage.getDocumentType()));
		subject.appendChild(documentType);
		
		Node type = doc.createElement("type");
		type.appendChild(doc.createTextNode(messsage.getType()));
		subject.appendChild(type);
		
		Node nfunction = doc.createElement("function");
		nfunction.appendChild(doc.createTextNode(messsage.getFunction()));
		subject.appendChild(nfunction);
		
		Node nreference = doc.createElement("reference");
		nreference.appendChild(doc.createTextNode(messsage.getReference()));
		subject.appendChild(nreference);
		
		Node preReference = doc.createElement("preReference");
		preReference.appendChild(doc.createTextNode(messsage.getPreReference()));
		subject.appendChild(preReference);
		
		Node documentYear = doc.createElement("documentYear");
		documentYear.appendChild(doc.createTextNode(messsage.getDocumentYear()));
		subject.appendChild(documentYear);
		
		Node sendDate = doc.createElement("sendDate");
		sendDate.appendChild(doc.createTextNode(messsage.getSendDate()));
		subject.appendChild(sendDate);
		
		header.appendChild(subject);
		
		Node body = doc.createElement("Body");
		envelope.appendChild(body);
		
		Node content = doc.createElement("Content");
		// if(messsage.getContent() != null &&
		// messsage.getContent().length()>0){
		// content.setTextContent(NOI_DUNG_TRA_VE);
		// }else{
		//
		// content.setTextContent(NOI_DUNG_TRA_VE);
		// }
		
		content.setTextContent(NOI_DUNG_TRA_VE);
		body.appendChild(content);
		doc.appendChild(envelope);
		return getStringFromDocument(doc);
	}

	// method to convert Document to String
	public static String getStringFromDocument(Document doc) {
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public static ShipSecurityNotification convertXmltoShipSecurityNotification(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(ShipSecurityNotificationFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			ShipSecurityNotification shipSecurityNotification = (ShipSecurityNotification) unmarshaller
					.unmarshal(input);

			return shipSecurityNotification;

		}
		return null;
	}

	public static CargoDeclaration convertXmltoCargoDeclaration(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(CargoDeclarationFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			CargoDeclaration cargoDeclaration = (CargoDeclaration) unmarshaller
					.unmarshal(input);

			return cargoDeclaration;

		}
		return null;
	}

	public static NoticeOfArrival convertXmltoNoticeOfArrival(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(NoticeOfArrivalFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			NoticeOfArrival noticeOfArrival = (NoticeOfArrival) unmarshaller
					.unmarshal(input);

			return noticeOfArrival;

		}
		return null;
	}
	
	public static InlandNoticeOfArrival convertXmltoNoticeOfInlandArrival(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(InlandNoticeOfArrivalFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			InlandNoticeOfArrival inlandNoticeOfArrival = (InlandNoticeOfArrival) unmarshaller
					.unmarshal(input);

			return inlandNoticeOfArrival;

		}
		return null;
	}

	public static GeneralDeclaration convertXmltoGeneralDeclaration(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(GeneralDeclarationFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			GeneralDeclaration generalDeclaration = (GeneralDeclaration) unmarshaller
					.unmarshal(input);

			return generalDeclaration;

		}
		return null;
	}

	public static InlandGeneralDeclaration convertXmltoInLandGeneralDeclaration(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(InlandGeneralDeclarationFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			InlandGeneralDeclaration generalDeclaration = (InlandGeneralDeclaration) unmarshaller
					.unmarshal(input);

			return generalDeclaration;

		}
		return null;
	}	
	public static CrewLists convertXmltoCrewList(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext.newInstance(CrewListsFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			CrewLists crewList = (CrewLists) unmarshaller.unmarshal(input);

			return crewList;

		}
		return null;
	}
	
	
	//inland crewlist
	public static InlandCrewLists convertXmltoInlandCrewList(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext.newInstance(InlandCrewListsFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			InlandCrewLists InlandCrewList = (InlandCrewLists) unmarshaller.unmarshal(input);

			return InlandCrewList;

		}
		return null;
	}

	public static PassengerList convertXmltoPassengerList(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(PassengerListFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			PassengerList passengerList = (PassengerList) unmarshaller
					.unmarshal(input);

			return passengerList;

		}
		return null;
	}
	
	public static PTTNDGeneralDeclaration convertXmltoPTTNDGeneralDeclaration(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(PTTNDGeneralDeclaration.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			PTTNDGeneralDeclaration passengerList = (PTTNDGeneralDeclaration) unmarshaller
					.unmarshal(input);

			return passengerList;

		}
		return null;
	}
	//add pttnd
	public static PTTNDAcceptance convertXmltoPTTNDAcceptance(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(PTTNDAcceptance.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			PTTNDAcceptance passengerList = (PTTNDAcceptance) unmarshaller
					.unmarshal(input);

			return passengerList;

		}
		return null;
	}
	public static PTTNDPassengerList convertXmltoPTTNDPassengerList(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(PTTNDPassengerListFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			PTTNDPassengerList passengerList = (PTTNDPassengerList) unmarshaller
					.unmarshal(input);

			return passengerList;

		}
		return null;
	}
	
	public static InlandPassengerList convertXmltoInlandPassengerList(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(InlandPassengerListFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			InlandPassengerList inlandPassengerList = (InlandPassengerList) unmarshaller
					.unmarshal(input);

			return inlandPassengerList;

		}
		return null;
	}
	
	public static InlandCrewCallCenter convertXmltoInlandCrewCallCenter(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(InlandCrewCallCenterFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			InlandCrewCallCenter inlandCrewCC = (InlandCrewCallCenter) unmarshaller
					.unmarshal(input);

			return inlandCrewCC;

		}
		return null;
	}

	public static DangerousGoodsManifest convertXmltoDangerousGoodsManifest(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(DangerousGoodsManifestFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			DangerousGoodsManifest dangerousGoodsManifest = (DangerousGoodsManifest) unmarshaller
					.unmarshal(input);

			return dangerousGoodsManifest;

		}
		return null;
	}

	public static ShipsStoresDeclaration convertXmltoShipsStoresDeclaration(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(ShipsStoresDeclarationFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			ShipsStoresDeclaration shipsStoresDeclaration = (ShipsStoresDeclaration) unmarshaller
					.unmarshal(input);

			return shipsStoresDeclaration;

		}
		return null;
	}

	public static CrewEffectsDeclaration convertXmltoCrewEffectsDeclaration(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(CrewEffectsDeclarationFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			CrewEffectsDeclaration crewEffectsDeclaration = (CrewEffectsDeclaration) unmarshaller
					.unmarshal(input);

			return crewEffectsDeclaration;

		}
		return null;
	}

	public static DeclarationForPlantQuarantine convertXmltoDeclarationForPlantQuarantine(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(DeclarationForPlantQuarantineFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			DeclarationForPlantQuarantine declarationForPlantQuarantine = (DeclarationForPlantQuarantine) unmarshaller
					.unmarshal(input);

			return declarationForPlantQuarantine;

		}
		return null;
	}

	public static DeclarationForAnimalQuarantine convertXmltoDeclarationForAnimalQuarantine(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(DeclarationForAnimalQuarantineFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			DeclarationForAnimalQuarantine declarationForAnimalQuarantine = (DeclarationForAnimalQuarantine) unmarshaller
					.unmarshal(input);

			return declarationForAnimalQuarantine;

		}
		return null;
	}

	public static HealthQuanrantineDeclare convertXmltoHealthQuanrantineDeclare(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(HealthQuanrantineDeclareFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			HealthQuanrantineDeclare healthQuanrantineDeclare = (HealthQuanrantineDeclare) unmarshaller
					.unmarshal(input);

			return healthQuanrantineDeclare;

		}
		return null;
	}

	public static ShiftingOrder convertXmltoShiftingOrder(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(ShiftingOrderFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			ShiftingOrder shiftingOrder = (ShiftingOrder) unmarshaller
					.unmarshal(input);

			return shiftingOrder;

		}
		return null;
	}
	
	public static PortClearance convertXmltoPortClearance(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(PortClearanceFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			PortClearance portClearance = (PortClearance) unmarshaller
					.unmarshal(input);

			return portClearance;

		}
		return null;
	}
	
	public static PTTNDPortclearance convertXmltoPTTNDPortClearance(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(PTTNDPortclearanceFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			PTTNDPortclearance object = (PTTNDPortclearance) unmarshaller
					.unmarshal(input);

			return object;

		}
		return null;
	}

	public static PermissionForTransit convertXmltoPermissionForTransit(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(PermissionForTransitFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			PermissionForTransit permissionForTransit = (PermissionForTransit) unmarshaller
					.unmarshal(input);

			return permissionForTransit;

		}
		return null;
	}

	public static AcceptanceForTransit convertXmltoAcceptanceForTransit(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(AcceptanceForTransitFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			AcceptanceForTransit acceptanceForTransit = (AcceptanceForTransit) unmarshaller
					.unmarshal(input);

			return acceptanceForTransit;

		}
		return null;
	}

	public static Attachment convertXmltoAttachment(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(AttachmentFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			Attachment attachment = (Attachment) unmarshaller
					.unmarshal(input);

			return attachment;

		}
		return null;
	}
	
	public static PortList convertXmltoPortList(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(PortListFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			PortList lsPort = (PortList) unmarshaller
					.unmarshal(input);

			return lsPort;

		}
		return null;
	}
	
	public static List<Object> converXMLMessagesContentToObject(String xmlString) {
		List<Object> objects = null;
		String content = null;
		String objectName = null;
		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "//Body/Content";
		DOMSource source = null;

		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(
					xmlString.getBytes("UTF-8"));

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(stream);

			NodeList nodeList;
			Node node1 = (Node) xPath.compile(expression).evaluate(doc,
					XPathConstants.NODE);

			objects = new ArrayList<Object>();
			if (null != node1) {
				nodeList = node1.getChildNodes();
				for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
					Node nod = nodeList.item(i);
					
					if (nod.getNodeType() == Node.ELEMENT_NODE) objectName = nodeList.item(i).getNodeName();
					
					if (nod instanceof Element) {
						source = new DOMSource(nod);
						
						// Set up the transformer to write the output string
						TransformerFactory tFactory = TransformerFactory.newInstance();
						Transformer transformer = tFactory.newTransformer();
						transformer.setOutputProperty("indent", "yes");
						transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
						StringWriter sw = new StringWriter();
						StreamResult result = new StreamResult(sw);
						
						// Do the transformation and output
						transformer.transform(source, result);
						content = sw.toString();

						if (MessageType.SHIPSECURITY_NOTIFICATION.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoShipSecurityNotification(content));
						} else if (MessageType.CARGO_DECLARATION.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoCargoDeclaration(content));
						} else if (MessageType.NOTICE_OF_ARRIVAL.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoNoticeOfArrival(content));
						} else if (MessageType.GENERAL_DECLARATION.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoGeneralDeclaration(content));
						} else if (MessageType.CREW_LIST.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoCrewList(content));
						} else if (MessageType.PASSENGER_LIST.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoPassengerList(content));
						} else if (MessageType.DANGEROUS_GOODS_MANIFEST.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoDangerousGoodsManifest(content));
						} else if (MessageType.SHIPS_STORES_DECLARATION.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoShipsStoresDeclaration(content));
						} else if (MessageType.CREW_EFFECTS_DECLARATION.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoCrewEffectsDeclaration(content));
						} else if (MessageType.DECLARATION_FOR_PLANT_QUARANTINE.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoDeclarationForPlantQuarantine(content));
						} else if (MessageType.DECLARATION_FOR_ANIMAL_QUARANTINE.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoDeclarationForAnimalQuarantine(content));
						} else if (MessageType.HEALTH_QUANRANTINE_DECLARE.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoHealthQuanrantineDeclare(content));
						} else if (MessageType.SHIFTING_ORDER.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoShiftingOrder(content));
						} else if (MessageType.PORT_CLEARANCE.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoPortClearance(content));
						} else if (MessageType.PERMISSION_FOR_TRANSIT.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoPermissionForTransit(content));
						} else if (MessageType.ACCEPTANCE_FOR_TRANSIT.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoAcceptanceForTransit(content));
						} else if (MessageType.ATTACHMENT.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoAttachment(content));
						} else if (MessageType.PTTNDPortClearance.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoPortClearance(content));
						} else if (MessageType.PTTNDAcceptance.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoPortClearance(content));
						} else if (MessageType.FUNCTION_DONG_BO_DM_CANG_HAI_QUAN.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoPortList(content));
						} else if (MessageType.CONFIRMATION_OF_ARRIVAL.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoConfirmationOfArrival(content));
						} else if (MessageType.FEE_NOTICE.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoFeeNotice(content));
						} else if (MessageType.FEE_APPROVED.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoFeeApproved(content));
						}  else if (MessageType.SyncRequestInvoice.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoRequestInvoice(content));
						} else if (MessageType.SyncRequestTransactionList.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoRequestTransactionList(content));
						} else if (MessageType.SyncResponseInvoice.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoResponseInvoice(content));
						} else if (MessageType.SynchResponseTransactionList.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.convertXmltoResponseTransactionList(content));
						} else if ("DmHistoryPilotList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryPilotListFactory()));
						} else if ("DmHistoryPilotCategoryList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryPilotCategoryListFactory()));
						} else if ("DmHistoryPilotCompanyList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryPilotCompanyListFactory()));
						} else if ("DmHistorySecurityOfficeList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistorySecurityOfficeListFactory()));
						} else if ("DmHistoryShipOwnerList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryShipOwnerListFactory()));
						} else if ("DmHistoryShipyardList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryShipyardListFactory()));
						} else if ("DmHistoryTugboatList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryTugboatListFactory()));
						} else if ("DmHistoryTugboatCompanyList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryTugboatCompanyListFactory()));
						} else if ("DmHistoryVmashipTypeList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryVmashipTypeListFactory()));
						} else if ("DmShipAgencyList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmShipAgencyListFactory()));
						} else if ("DmVmaPilotList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmVmaPilotListFactory()));
						} else if ("DmVmaPilotCategoryList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmVmaPilotCategoryListFactory()));
						} else if ("DmVmaPilotCompanyList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmVmaPilotCompanyListFactory()));
						} else if ("DmVmaSecurityOfficeList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmVmaSecurityOfficeListFactory()));
						} else if ("DmVmaShipOwnerList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmVmaShipOwnerListFactory()));
						} else if ("DmVmaShipTypeList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmVmaShipTypeListFactory()));
						} else if ("DmVmaShipyardList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmVmaShipyardListFactory()));
						} else if ("DmVmaTugboatList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmVmaTugboatListFactory()));
						} else if ("DmVmaTugboatCompanyList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmVmaTugboatCompanyListFactory()));
						}else if ("DmHistoryPortList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryPortListFactory()));
						} else if ("DmHistoryPortWharfList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryPortWharfListFactory()));
						} else if ("DmPortWharfList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmPortWharfListFactory()));
						} else if ("DmHistoryPortRegionList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryPortRegionListFactory()));
						} else if ("DmPortRegionList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmPortRegionListFactory()));
						} else if ("DmHistoryPortHarbourList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryPortHarbourListFactory()));
						} else if ("DmPortHarbourList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmPortHarbourListFactory()));
						} else if ("DmHistoryMaritimeList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmHistoryMaritimeListFactory()));
						} else if ("DmMaritimeList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmMaritimeListFactory()));
						} else if ("DmDataItemList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, new DmDataItemListFactory()));
						} else if ("VmaAccidentList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaAccidentList()));
						} else if ("VmaAdministrativeViolation".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaAdministrativeViolation()));
						} else if ("VmaItinerary".equalsIgnoreCase(objectName)) {
							objects.add(
									MessageFactory.converXmlToObject(content, new VmaFactory().createVmaItinerary()));
						} else if ("VmaItineraryProtest".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaItineraryProtest()));
						} else if ("VmaItineraryRemarks".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaItineraryRemarks()));
						} else if ("VmaItinerarySchedule".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaItinerarySchedule()));
						} else if ("VmaPaymentInvoice".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaPaymentInvoice()));
						} else if ("VmaScheduleAnchorage".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleAnchorage()));
						} else if ("VmaScheduleCargolist".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleCargolist()));
						} else if ("VmaScheduleLaunching".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleLaunching()));
						} else if ("VmaScheduleMerging".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleMerging()));
						} else if ("VmaSchedulePilot".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaSchedulePilot()));
						} else if ("VmaSchedulePilotList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaSchedulePilotList()));
						} else if ("VmaScheduleSecurity".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleSecurity()));
						} else if ("VmaScheduleShifting".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleShifting()));
						} else if ("VmaScheduleShipyard".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleShipyard()));
						} else if ("VmaScheduleTesting".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleTesting()));
						} else if ("VmaScheduleTugboat".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleTugboat()));
						} else if ("VmaScheduleTugboatList".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleTugboatList()));
						} else if ("VmaScheduleXline".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleXline()));
						} else if ("VmaScheduleXlineSailing".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaScheduleXlineSailing()));
						} else if ("VmaShip".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content, 
									new VmaFactory().createVmaShip()));
						} else if ("VmaShipCertificate".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaShipCertificate()));
						} else if ("VmaTransactionBalance".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaTransactionBalance()));
						} else if ("VmaTransactionConversion".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaTransactionConversion()));
						} else if ("VmaTransactionDepartment".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaTransactionDepartment()));
						} else if ("VmaTransactionFunction".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaTransactionFunction()));
						} else if ("VmaTransactionSlip".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaTransactionSlip()));
						} else if ("VmaTransactionType".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaTransactionType()));
						} else if ("VmaTugboatCondition".equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory.converXmlToObject(content,
									new VmaFactory().createVmaTugboatCondition()));
						}
					}
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return objects;

	}

	public static List<Object> converXMLMessagesContentToInLandObject(String xmlString) {
		List<Object> objects = null;
		String content = null;
		String objectName = null;
		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "//Body/Content";
		DOMSource source = null;

		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(
					xmlString.getBytes("UTF-8"));

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(stream);

			NodeList nodeList;
			Node node1 = (Node) xPath.compile(expression).evaluate(doc,
					XPathConstants.NODE);

			objects = new ArrayList<Object>();
			if (null != node1) {
				nodeList = node1.getChildNodes();
				for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
					Node nod = nodeList.item(i);
					if (nod.getNodeType() == Node.ELEMENT_NODE)
						objectName = nodeList.item(i).getNodeName();
					// System.out.println(nodeList.item(i).getNodeName() + " : "
					// + nod.getFirstChild().getNodeValue());
					if (nod instanceof Element) {
						source = new DOMSource(nod);
						// Set up the transformer to write the output string
						TransformerFactory tFactory = TransformerFactory
								.newInstance();
						Transformer transformer = tFactory.newTransformer();
						transformer.setOutputProperty("indent", "yes");
						transformer.setOutputProperty(OutputKeys.ENCODING,
								"UTF-8");
						StringWriter sw = new StringWriter();
						StreamResult result = new StreamResult(sw);
						// Do the transformation and output
						transformer.transform(source, result);
						content = sw.toString();

						if (MessageType.SHIPSECURITY_NOTIFICATION
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoShipSecurityNotification(content));
						}
						if (MessageType.CARGO_DECLARATION
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoCargoDeclaration(content));
						}
						if (MessageType.INLANDNOTICEARRIVAL
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoNoticeOfInlandArrival(content));
						}
						if (MessageType.InLandGeneralDeclaration
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoInLandGeneralDeclaration(content));
						}
						if (MessageType.InlandCrewLists.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoInlandCrewList(content));
						}
						if (MessageType.InlandPassengerList
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoInlandPassengerList(content));
						}
						if (MessageType.DANGEROUS_GOODS_MANIFEST
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoDangerousGoodsManifest(content));
						}
						if (MessageType.SHIPS_STORES_DECLARATION
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoShipsStoresDeclaration(content));
						}
						if (MessageType.CREW_EFFECTS_DECLARATION
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoCrewEffectsDeclaration(content));
						}
						if (MessageType.DECLARATION_FOR_PLANT_QUARANTINE
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoDeclarationForPlantQuarantine(content));
						}
						if (MessageType.DECLARATION_FOR_ANIMAL_QUARANTINE
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoDeclarationForAnimalQuarantine(content));
						}
						if (MessageType.HEALTH_QUANRANTINE_DECLARE
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoHealthQuanrantineDeclare(content));
						}
						if (MessageType.SHIFTING_ORDER
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoShiftingOrder(content));
						}
						if (MessageType.PORT_CLEARANCE
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoPortClearance(content));
						}
						if (MessageType.PTTNDPortClearance
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoPTTNDPortClearance(content));
						}
						if (MessageType.PERMISSION_FOR_TRANSIT
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoPermissionForTransit(content));
						}
						if (MessageType.ACCEPTANCE_FOR_TRANSIT
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoAcceptanceForTransit(content));
						}
						if (MessageType.ATTACHMENT
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoAttachment(content));
						}
						if (MessageType.PTTNDPassengerList
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoPTTNDPassengerList(content));
						}
						if (MessageType.PTTNDGeneralDeclaration
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoPTTNDGeneralDeclaration(content));
						}
						if (MessageType.PTTNDAcceptance
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoPTTNDAcceptance(content));
						}
						if (MessageType.InlandCrewCallCenter
								.equalsIgnoreCase(objectName)) {
							objects.add(MessageFactory
									.convertXmltoInlandCrewCallCenter(content));
						}
						
					}
				}
			}

		} catch (Exception e) {
			log.error("Parser warning: " + e.getMessage());
		}

		return objects;

	}	
	
	public static String convertAcceptanceForTransitToXml(
			AcceptanceForTransit acceptanceForTransit) {

		JAXBContext jaxbCtx = null;
		StringWriter xmlWriter = null;
		String xmlString = null;
		try {
			jaxbCtx = JAXBContext.newInstance(AcceptanceForTransit.class);
			xmlWriter = new StringWriter();
			jaxbCtx.createMarshaller().marshal(acceptanceForTransit, xmlWriter);

			ByteArrayInputStream stream = new ByteArrayInputStream(xmlWriter
					.toString().getBytes("UTF-8"));

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(stream);
			xmlString = MessageFactory.getStringFromDocument(doc);
			xmlString = xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();

		} catch (Exception e) {
			log.error("Parser warning: AcceptanceForTransit to XML has error "
					+ e.getMessage());

		}
		return xmlString;
	}

	public static String convertObjectToXml(Object object) {

		JAXBContext jaxbCtx = null;
		StringWriter xmlWriter = null;
		String xmlString = null;
		xmlWriter = new StringWriter();
		try {

			if (object instanceof ShiftingOrder
					|| object instanceof InlandShiftingOrder
					|| object instanceof InlandPortClearance
					|| object instanceof PTTNDPortclearance
					|| object instanceof PortClearance
					|| object instanceof PermissionForTransit
					|| object instanceof AcceptanceForTransit
					|| object instanceof NoticeOfArrivalCancel
					|| object instanceof XacNhanThongQuan
					|| object instanceof Modify
					|| object instanceof PortOfAuthorityList
					|| object instanceof PortWharfList
					|| object instanceof PortHarbourList
					|| object instanceof PortRegionList
					|| object instanceof ReferencePortList
					|| object instanceof ReferenceGoodsList
					|| object instanceof PTTNDAcceptance
					|| object instanceof FeeNotice
					|| object instanceof FeeApproved
					|| object instanceof PortEntryPermit
					|| object instanceof CommandData
					|| object instanceof ArrayOfInvoiceDataWS
					|| object instanceof Customers
					|| object instanceof Invoices
					|| object instanceof Result) {
				
				if (object instanceof ShiftingOrder) {
					jaxbCtx = JAXBContext.newInstance(ShiftingOrder.class);
				}  else if (object instanceof InlandShiftingOrder) {
					jaxbCtx = JAXBContext.newInstance(InlandShiftingOrder.class);
				}else if (object instanceof InlandPortClearance) {
					jaxbCtx = JAXBContext.newInstance(InlandPortClearance.class);
				}else if (object instanceof PTTNDPortclearance) {
					jaxbCtx = JAXBContext.newInstance(PTTNDPortclearance.class);
				}else if (object instanceof PortClearance) {
					jaxbCtx = JAXBContext.newInstance(PortClearance.class);
				} else if (object instanceof PermissionForTransit) {
					jaxbCtx = JAXBContext.newInstance(PermissionForTransit.class);					
				} else if (object instanceof AcceptanceForTransit) {
					jaxbCtx = JAXBContext.newInstance(AcceptanceForTransit.class);
				} else if (object instanceof NoticeOfArrivalCancel) {
					jaxbCtx = JAXBContext.newInstance(NoticeOfArrivalCancel.class);
				} else if (object instanceof XacNhanThongQuan) {
					jaxbCtx = JAXBContext.newInstance(XacNhanThongQuan.class);
				} else if (object instanceof Modify) {
					jaxbCtx = JAXBContext.newInstance(Modify.class);
				} else if (object instanceof PTTNDAcceptance) {
					jaxbCtx = JAXBContext.newInstance(PTTNDAcceptance.class);
				} else if (object instanceof PortWharfList) {
					jaxbCtx = JAXBContext.newInstance(PortWharfList.class);
				} else if (object instanceof PortHarbourList) {
					jaxbCtx = JAXBContext.newInstance(PortHarbourList.class);
				} else if (object instanceof PortRegionList) {
					jaxbCtx = JAXBContext.newInstance(PortRegionList.class);
				} else if (object instanceof PortOfAuthorityList) {
					jaxbCtx = JAXBContext.newInstance(PortOfAuthorityList.class);
				} else if (object instanceof ReferencePortList) {
					jaxbCtx = JAXBContext.newInstance(ReferencePortList.class);
				} else if (object instanceof ReferenceGoodsList) {
					jaxbCtx = JAXBContext.newInstance(ReferenceGoodsList.class);
				} else if (object instanceof FeeNotice) {
					jaxbCtx = JAXBContext.newInstance(FeeNotice.class);
				} else if (object instanceof FeeApproved) {
					jaxbCtx = JAXBContext.newInstance(FeeApproved.class);
				} else if (object instanceof PortEntryPermit) {
					jaxbCtx = JAXBContext.newInstance(PortEntryPermit.class);
				} else if (object instanceof CommandData) {
					jaxbCtx = JAXBContext.newInstance(CommandData.class);
				} else if (object instanceof ArrayOfInvoiceDataWS) {
					jaxbCtx = JAXBContext.newInstance(ArrayOfInvoiceDataWS.class);
				} else if (object instanceof Customers) {
					jaxbCtx = JAXBContext.newInstance(Customers.class);
				} else if (object instanceof Invoices) {
					jaxbCtx = JAXBContext.newInstance(Invoices.class);
				} else if (object instanceof Result) {
					jaxbCtx = JAXBContext.newInstance(Result.class);
				}
				
				jaxbCtx.createMarshaller().marshal(object, xmlWriter);
				ByteArrayInputStream stream = new ByteArrayInputStream(xmlWriter.toString().getBytes("UTF-8"));
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(stream);
				xmlString = MessageFactory.getStringFromDocument(doc);
				xmlString = xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
			}

		} catch (Exception e) {
			log.error("Parser warning: Object to XML has error "
					+ e.getMessage());

		}
		return xmlString;
	}

	public static String getContentFromXML(String xmContent) throws Exception {
		if (!StringUtils.isEmpty(xmContent)) {

			// Get content value
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/";// "//Body/Content";//
			DOMSource source = null;

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmContent));
			Document doc = builder.parse(inputSource);

			Node node = (Node) xPath.compile(expression).evaluate(doc,
					XPathConstants.NODE);
			source = new DOMSource(node);

			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty("indent", "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);

			transformer.transform(source, result);
			// End of Get content value
			return sw.toString();
		}
		return "";
	}

	public static Result convertXmltoXacNhanKetQuaXuatHoaDon(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {

			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			Result xacNhanKetQua = new Result();
			xacNhanKetQua
					.setStatus(getTextValue(document, "Status"));
			xacNhanKetQua.setObject(getTextValue(document,
					"Object"));			

			return xacNhanKetQua;

		}
		return null;
	}
	
	public static XacNhanHuyLenhDieuDong convertXmltoXacNhanHuyLDD(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {

			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			XacNhanHuyLenhDieuDong xacNhanHuyLenhDieuDong = new XacNhanHuyLenhDieuDong();
			xacNhanHuyLenhDieuDong.setCancelDate(getTextValue(document,
					"CancelDate"));
			xacNhanHuyLenhDieuDong.setOrganization(getTextValue(document,
					"Organization"));
			xacNhanHuyLenhDieuDong.setDivision(getTextValue(document,
					"Division"));
			xacNhanHuyLenhDieuDong.setName(getTextValue(document, "Name"));
			xacNhanHuyLenhDieuDong.setReason(getTextValue(document, "Reason"));
			xacNhanHuyLenhDieuDong.setIsApprove(getTextValue(document,
					"IsApprove"));

			return xacNhanHuyLenhDieuDong;

		}
		return null;
	}

	public static XacNhanHuyThuTuc convertXmltoXacNhanHuyThuTuc(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {

			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			XacNhanHuyThuTuc xacNhanHuyThuyTuc = new XacNhanHuyThuTuc();
			xacNhanHuyThuyTuc
					.setCancelDate(getTextValue(document, "CancelDate"));
			xacNhanHuyThuyTuc.setOrganization(getTextValue(document,
					"Organization"));
			xacNhanHuyThuyTuc.setDivision(getTextValue(document, "Division"));
			xacNhanHuyThuyTuc.setName(getTextValue(document, "Name"));
			xacNhanHuyThuyTuc.setIsApprove(getTextValue(document, "IsApprove"));
			xacNhanHuyThuyTuc.setReason(getTextValue(document, "Reason"));

			return xacNhanHuyThuyTuc;

		}
		return null;
	}

	public static DeNghiCapLaiGiayPhep convertXmltoDeNghiCapLaiGiayPhep(
			String xmContent) throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {

			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			DeNghiCapLaiGiayPhep xacNhanHuyThuyTuc = new DeNghiCapLaiGiayPhep();
			xacNhanHuyThuyTuc.setRenewDate(getTextValue(document, "RenewDate"));
			xacNhanHuyThuyTuc.setOrganization(getTextValue(document,
					"Organization"));
			xacNhanHuyThuyTuc.setDivision(getTextValue(document, "Division"));
			xacNhanHuyThuyTuc.setName(getTextValue(document, "Name"));
			xacNhanHuyThuyTuc.setIsApprove(getTextValue(document, "IsApprove"));
			xacNhanHuyThuyTuc.setReason(getTextValue(document, "Reason"));

			return xacNhanHuyThuyTuc;

		}
		return null;
	}

	public static PheDuyetHoSoTuCacBoNganh convertXmltoPheDuyetHoSoTuCacBoNganh(
			String xmContent) throws Exception {
//System.out.println("PheDuyetHoSoTuCacBoNganh");
		if (!StringUtils.isEmpty(xmContent)) {
	//		System.out.println("!StringUtils.isEmpty(xmContent) ");
			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			PheDuyetHoSoTuCacBoNganh pheDuyetHoSoTuCacBoNganh = new PheDuyetHoSoTuCacBoNganh();
			pheDuyetHoSoTuCacBoNganh.setApprovalStatus(getTextValue(document,
					"ApprovalStatus"));
			String comment = getTextValue(document, "Comment");
			if (comment != null && comment.length() > 0) {
				pheDuyetHoSoTuCacBoNganh.setComment(comment);
			}
			pheDuyetHoSoTuCacBoNganh.setDivision(getTextValue(document,
					"Division"));
			pheDuyetHoSoTuCacBoNganh.setName(getTextValue(document, "Name"));
			pheDuyetHoSoTuCacBoNganh.setOrganization(getTextValue(document,
					"Organization"));
			pheDuyetHoSoTuCacBoNganh.setApprovalDate(getTextValue(document,
					"ApprovalDate"));
			log.info("!StringUtils.isEmpty(xmContent) "+pheDuyetHoSoTuCacBoNganh.getApprovalDate());
			return pheDuyetHoSoTuCacBoNganh;

		}
		
		log.info("Null");
		return null;
	}

	public static RejectMessage convertXmltoRejectMessage(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {

			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			RejectMessage rejectMessage = new RejectMessage();
			rejectMessage.setRejectCode(getTextValue(document, "RejectCode"));
			rejectMessage.setRejectDesc(getTextValue(document, "RejectDesc"));
			rejectMessage
					.setOrganization(getTextValue(document, "Organization"));
			rejectMessage.setDivision(getTextValue(document, "Division"));
			rejectMessage.setName(getTextValue(document, "Name"));
			rejectMessage.setRejectDate(getTextValue(document, "RejectDate"));

			return rejectMessage;

		}
		return null;
	}
	
	public static Modify convertXmltoModifyMessage(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {

			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			Modify ModifyMessage = new Modify();
			ModifyMessage.setModifyCode(getTextValue(document, "ModifyCode"));
			ModifyMessage.setModifyDesc(getTextValue(document, "ModifyDesc"));
			ModifyMessage
					.setOrganization(getTextValue(document, "Organization"));
			ModifyMessage.setDivision(getTextValue(document, "Division"));
			ModifyMessage.setName(getTextValue(document, "Name"));
			ModifyMessage.setModifyDate(getTextValue(document, "ModifyDate"));

			return ModifyMessage;

		}
		return null;
	}

	public static AccepterMessage convertXmltoAccepterMessage(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {

			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			AccepterMessage accepterMessage = new AccepterMessage();

			accepterMessage.setOrganization(getTextValue(document,
					"Organization"));
			accepterMessage.setDivision(getTextValue(document, "Division"));
			accepterMessage.setName(getTextValue(document, "Name"));

			return accepterMessage;

		}
		return null;
	}

	public static XacNhanThongQuan convertXmltoXacNhanThongQuan(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {

			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			XacNhanThongQuan xacNhanThongQuan = new XacNhanThongQuan();
			xacNhanThongQuan
					.setAcceptDate(getTextValue(document, "AcceptDate"));
			xacNhanThongQuan.setFlag(getTextValue(document, "Flag"));
			xacNhanThongQuan.setNotification(getTextValue(document,
					"Notification"));

			return xacNhanThongQuan;

		}
		return null;
	}

	public static String convertXmltoXacNhanHoanThanhThuTuc(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {

			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			return getTextValue(document, "ReceiveDate");

		}
		return null;
	}

	public static KetQuaThongQuan convertXmltoKetQuaThongQuan(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {

			String contentValue = getContentFromXML(xmContent);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contentValue));
			Document document = documentBuilder.parse(is);

			KetQuaThongQuan resultNotification = new KetQuaThongQuan();
			resultNotification.setLatestDate(getTextValue(document,
					"AcceptDate"));
			String flag = getTextValue(document, "Flag");

			resultNotification.setIsReply(FormatData.convertToInt(flag));
			resultNotification
					.setRemarks(getTextValue(document, "Notification"));
			return resultNotification;

		}
		return null;
	}

	public static String getReason2Content(String xmlString) {
		String content = null;
		if (!StringUtils.isEmpty(xmlString)) {
			try {
				XPath xPath = XPathFactory.newInstance().newXPath();
				String expression = "//Body/Content/Reason";
				ByteArrayInputStream stream = new ByteArrayInputStream(
						xmlString.getBytes("UTF-8"));

				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(stream);

				Node node1 = (Node) xPath.compile(expression).evaluate(doc,
						XPathConstants.NODE);
				if (node1 != null) {

					content = node1.getTextContent();

					log.info("node1.getNodeValue()   "
							+ node1.getNodeValue()
							+ "   node1.getTextContent()  "
							+ node1.getTextContent());
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}

		}
		return content;

	}

	public static String getTextValue(Document document, String string) {
		try{
		String textVal = null;
		NodeList nl = document.getElementsByTagName(string);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;

	}

	public static Object convertXmltoObject(int messageType, String xmContent)
			throws Exception {

		Object object = new Object();
		switch (messageType) {
		case MessageType.XAC_NHAN_HUY_LENH_DIEU_DONG:
			object = convertXmltoXacNhanHuyLDD(xmContent);
			break;
		case MessageType.HUY_GIAY_PHEP_ROI_CANG:
			object = convertXmltoXacNhanHuyThuTuc(xmContent);
			break;
		case MessageType.HUY_GIAY_PHEP_QUA_CANH:
			object = convertXmltoXacNhanHuyThuTuc(xmContent);
			break;
		case MessageType.KHAI_HUY_HO_SO:
			object = convertXmltoXacNhanHuyThuTuc(xmContent);
			break;
		case MessageType.HUY_GIAY_PHEP_VAO_CANG:
			object = convertXmltoXacNhanHuyThuTuc(xmContent);
			break;
		case MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_VAO_CANG:
			object = convertXmltoXacNhanHuyThuTuc(xmContent);
			break;
		case MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_ROI_CANG:
			object = convertXmltoXacNhanHuyThuTuc(xmContent);
			break;
		case MessageType.Y_CAU_CAP_LAI_GIAY_PHEP_QUA_CANH:
			object = convertXmltoXacNhanHuyThuTuc(xmContent);
			break;
		case MessageType.XAC_NHAN_THONG_QUAN:
			object = convertXmltoXacNhanThongQuan(xmContent);
			break;
			

		}

		return object;
	}

	public static Object convertXmltoObjectWithFunction(int function, int type,
			String xmContent) {
		//System.out.println("function  +  " + "  xmContent   " + xmContent);

		Object object = new Object();
		try {
			switch (function) {
				case MessageType.FUNCTION_CHAP_NHAN_HO_SO:
					object = convertXmltoAccepterMessage(xmContent);
					break;
				case MessageType.FUNCTION_YEU_CAU_BO_SUNG_KE_HOACH:
					object = convertXmltoModifyMessage(xmContent);
					break;
				case MessageType.FUNCTION_YEU_CAU_BO_SUNG_THU_TUC:
					object = convertXmltoRejectMessage(xmContent);
					break;
				case MessageType.FUNCTION_YC_BO_SUNG:
					// chua lam
					
					break;
					
				case MessageType.FUNCTION_TU_CHOI_HO_SO:
					object = convertXmltoRejectMessage(xmContent);
					break;
				case MessageType.FUNCTION_XAC_NHAN_HOAN_THANH:
					if (type != MessageType.XAC_NHAN_THONG_QUAN) {
						object = convertXmltoXacNhanHoanThanhThuTuc(xmContent);
					} else {
						object = convertXmltoKetQuaThongQuan(xmContent);
					}
					break;
				case MessageType.FUNCTION_XAC_NHAN_HUY:
					object = convertXmltoXacNhanHuyThuTuc(xmContent);
					break;
				case MessageType.FUNCTION_KHAI_HUY_THU_TUC:
					object = convertXmltoXacNhanHuyThuTuc(xmContent);
					break;
				case MessageType.FUNCTION_BO_NGANH_PHE_DUYET:
					object = convertXmltoPheDuyetHoSoTuCacBoNganh(xmContent);
					break;
				case MessageType.FUNCTION_LENH_DIEU_DONG_GIAY_PHEP_ROI_CANG:
					if (type == MessageType.LENH_DIEU_DONG) {
						List<Object> liObjects = MessageFactory
								.converXMLMessagesContentToObject(xmContent);
						if (liObjects != null && liObjects.size() > 0) {
							object = liObjects.get(0);
						}
					} else if (type == MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH) {
						List<Object> liObjects = MessageFactory
								.converXMLMessagesContentToObject(xmContent);
						if (liObjects != null && liObjects.size() > 0) {
							object = liObjects.get(0);
						}
					} else if (type == MessageType.GIAY_PHEP_QUA_CANH){
						List<Object> liObjects = MessageFactory
								.converXMLMessagesContentToObject(xmContent);
						if (liObjects != null && liObjects.size() > 0) {
							object = liObjects.get(0);
						}
					} else if (type == MessageType.PTTND_GIAY_PHEP_VAO_CANG) {
						List<Object> liObjects = MessageFactory
								.converXMLMessagesContentToObject(xmContent);
						if (liObjects != null && liObjects.size() > 0) {
							object = liObjects.get(0);
						}
					} else if (type == MessageType.PTTND_GIAY_PHEP_VAO_CANG) {
						List<Object> liObjects = MessageFactory
								.converXMLMessagesContentToObject(xmContent);
						if (liObjects != null && liObjects.size() > 0) {
							object = liObjects.get(0);
						}
					} 
	
					break;
			}
			
			if(type == MessageType.TYPE_THONG_BAO_NOP_PHI) {
				List<Object> liObjects = MessageFactory
						.converXMLMessagesContentToObject(xmContent);
				if (liObjects != null && liObjects.size() > 0) {
					object = liObjects.get(0);
				}
			} else if(type == MessageType.TYPE_THONG_BAO_XAC_NHAN_NOP_PHI) {
				List<Object> liObjects = MessageFactory
						.converXMLMessagesContentToObject(xmContent);
				if (liObjects != null && liObjects.size() > 0) {
					object = liObjects.get(0);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return object;
	}
	
	public static ConfirmationOfArrival convertXmltoConfirmationOfArrival(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(ConfirmationOfArrivalFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			ConfirmationOfArrival object = (ConfirmationOfArrival) unmarshaller
					.unmarshal(input);

			return object;

		}
		return null;
	}
	
	public static FeeNotice convertXmltoFeeNotice(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(FeeNoticeFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			FeeNotice object = (FeeNotice) unmarshaller
					.unmarshal(input);

			return object;

		}
		return null;
	}
	
	public static FeeApproved convertXmltoFeeApproved(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(FeeApprovedFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			FeeApproved object = (FeeApproved) unmarshaller
					.unmarshal(input);

			return object;

		}
		return null;
	}
	
	
	public static RequestInvoice convertXmltoRequestInvoice(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(RequestInvoiceFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			RequestInvoice requestInvoice = (RequestInvoice) unmarshaller
					.unmarshal(input);

			return requestInvoice;

		}
		return null;
	}
	
	
	public static RequestTransactionList convertXmltoRequestTransactionList(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(RequestTransactionListFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			RequestTransactionList requestTransactionList = (RequestTransactionList) unmarshaller
					.unmarshal(input);

			return requestTransactionList;

		}
		return null;
	}
	
	
	public static ResponseInvoice convertXmltoResponseInvoice(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(ResponseInvoiceFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			ResponseInvoice responseInvoice = (ResponseInvoice) unmarshaller
					.unmarshal(input);

			return responseInvoice;

		}
		return null;
	}
	
	
	public static ResponseTransactionList convertXmltoResponseTransactionList(String xmContent)
			throws Exception {

		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext
					.newInstance(ResponseTransactionListFactory.class);

			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>",
			// "").trim();

			ByteArrayInputStream input = new ByteArrayInputStream(
					xmContent.getBytes("UTF-8"));

			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.

			ResponseTransactionList responseTransactionList = (ResponseTransactionList) unmarshaller
					.unmarshal(input);

			return responseTransactionList;

		}
		return null;
	}
	
	
	public static <T> T converXmlToObject(String content, T t) throws IOException {
		if (Validator.isNotNull(content)) {
			ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes("UTF-8"));
			try {
				JAXBContext jc = JAXBContext.newInstance(t.getClass());

				// create an Unmarshaller
				Unmarshaller unmarshaller = jc.createUnmarshaller();

				T obj = (T) unmarshaller.unmarshal(input);

				return obj;
			} catch (Exception e) {
				log.error(e.getMessage());
			} finally {
				input.close();
			}
		}
		
		return null;
	}
	
	public static <T> String convertObjectToXml2(T t) throws IOException {

		if (t != null) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			try {
				JAXBContext jc = JAXBContext.newInstance(t.getClass());

				// create an Marshaller
				Marshaller marshaller = jc.createMarshaller();
				// Loai bo formatted theo dinh dang indent
				// marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
				// true);
				marshaller.marshal(t, output);
				String toXml = new String(output.toByteArray(), "UTF-8");
				return replaceXML(toXml);
			} catch (Exception e) {
				log.error(e.getMessage());
			} finally {
				output.close();
			}
		}

		return "";
	}
	
	public static String replaceXML(String data) {
		try {
			String dataPrefix = data.substring(0, data.indexOf("?>") + 2).toLowerCase();
			
			if (dataPrefix.contains("version") && dataPrefix.contains("encoding")) {
				return data.substring(data.indexOf("?>") + 2, data.length());
				// TODO Truong hop khac
			} else if (dataPrefix.contains("version") | dataPrefix.contains("encoding")) {
				return data.substring(data.indexOf("?>") + 2, data.length());
			}
			
			return data;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return data;
	}

}
