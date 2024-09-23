package vn.nsw.fac;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
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




import vn.gt.tichhop.message.MessageFactory;
import vn.gt.tichhop.message.xml.StringUtils;
import vn.gt.tichhop.message.xml.XmlUtils;
import vn.nsw.Header;
import vn.nsw.ObjectFactory;
import vn.nsw.model.ShipSecurityNotification;
@Slf4j
public class ReadMessages {
	
	
	
	public static Header readXMLMessagesHeader(String xmlString) throws Exception {
		
//		log.info("---readXMLMessagesHeader--");
		
		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "Envelope/Header";// "//Body/Content";//
		DOMSource source = null;
		ByteArrayInputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		
		Node node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
		source = new DOMSource(node);
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.setOutputProperty("indent", "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		
		transformer.transform(source, result);
		// log.info(sw.toString());
		
//		StringReader reader = new StringReader(sw.toString());
		
		// create a JAXBContext capable of handling classes generated into
		// Document doc1 =XmlUtils.parseXmlFile("C://header.xml", false);
		
		JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
		
		// create an Unmarshaller
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		String xmlHeader = sw.toString();
		// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
		
		ByteArrayInputStream input = new ByteArrayInputStream(xmlHeader.getBytes("UTF-8"));
		
		// unmarshal a po instance document into a tree of Java content
		// objects composed of classes from the primer.po package.
		
		return (Header) unmarshaller.unmarshal(input);
	}
	
	public static String readXMLMessagesContent(String xmlString) throws Exception {
		String content = null;
		
		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "//Body/Content";
		DOMSource source = null;
		ByteArrayInputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		
		NodeList nodeList;
		Node node1 = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
		
		if (null != node1) {
			nodeList = node1.getChildNodes();
			for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
				Node nod = nodeList.item(i);
				if (nod.getNodeType() == Node.ELEMENT_NODE)
					log.info(nodeList.item(i).getNodeName() + " : " + nod.getFirstChild().getNodeValue());
				if (nod instanceof Element) {
					source = new DOMSource(nod);
					break;
				}
			}
		}
		
		// Set up the transformer to write the output string
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.setOutputProperty("indent", "yes");
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		
		// Do the transformation and output
		transformer.transform(source, result);
		content = sw.toString();
		return content;
		
	}
	
	public static boolean isBlank(String tobetested) {
		if ((tobetested != null) && (!tobetested.trim().equals(""))) {
			return false;
		}
		return true;
	}
	
	public static ShipSecurityNotification convertXmltoShipSecurityNotification(String xmContent) throws Exception {
		
		if (!StringUtils.isEmpty(xmContent)) {
			JAXBContext jc = JAXBContext.newInstance(ShipSecurityNotificationFactory.class);
			
			// create an Unmarshaller
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			// xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
			
			ByteArrayInputStream input = new ByteArrayInputStream(xmContent.getBytes("UTF-8"));
			
			// unmarshal a po instance document into a tree of Java content
			// objects composed of classes from the primer.po package.
			
			ShipSecurityNotification shipSecurityNotification = (ShipSecurityNotification) unmarshaller.unmarshal(input);
			
			return shipSecurityNotification;
			
		}
		return null;
	}
	
	/*
	 * public static void main(String[] args) throws Exception{ //Parse the input document DocumentBuilderFactory factory =
	 * DocumentBuilderFactory.newInstance(); XPath xPath = XPathFactory.newInstance().newXPath(); String expression = "//Body/Content"; DOMSource
	 * source = null; //Document doc =vn.gt.tichhop.message.xml.XmlUtils.parseXmlFile("C://ShipSecurity.xml", false); //Document doc
	 * =vn.gt.tichhop.message.xml.XmlUtils.parseXmlFile("C://ShipSecurity.xml", false); InputStream inputStream= new
	 * FileInputStream("C://ShipSecurity.xml"); InputStreamReader inputReader = new InputStreamReader(inputStream,"UTF-16"); InputSource inputSource =
	 * new InputSource(inputReader); inputSource.setEncoding("UTF-8"); DocumentBuilder builder = factory.newDocumentBuilder(); Document doc =
	 * builder.parse(inputSource); NodeList nodeList; Node node1 = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE); if(null !=
	 * node1) { nodeList = node1.getChildNodes(); for (int i = 0;null!=nodeList && i < nodeList.getLength(); i++) { Node nod = nodeList.item(i);
	 * if(nod.getNodeType() == Node.ELEMENT_NODE) log.info(nodeList.item(i).getNodeName() + " : " + nod.getFirstChild().getNodeValue());
	 * if(nod instanceof Element) { source = new DOMSource(nod); break; } } } //Set up the transformer to write the output string TransformerFactory
	 * tFactory = TransformerFactory.newInstance(); Transformer transformer = tFactory.newTransformer(); transformer.setOutputProperty("indent",
	 * "yes"); transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); StringWriter sw = new StringWriter(); StreamResult result = new
	 * StreamResult(sw); //Do the transformation and output transformer.transform(source, result); JAXBContext jc = JAXBContext.newInstance(
	 * ShipSecurityNotificationFactory.class ); // create an Unmarshaller Unmarshaller unmarshaller = jc.createUnmarshaller(); String xmlString =
	 * sw.toString(); //xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim(); ByteArrayInputStream input = new ByteArrayInputStream
	 * (xmlString.getBytes("UTF-8")); // unmarshal a po instance document into a tree of Java content // objects composed of classes from the
	 * primer.po package. ShipSecurityNotification shipSecurityNotification = (ShipSecurityNotification) unmarshaller.unmarshal(input); Object object
	 * = new Object(); object=MessageFactory.convertXmltoShipSecurityNotification(xmlString); log.info(sw.toString()); }
	 */
	
	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "//Body/Content/";
		DOMSource source1 = null;
		
		Document doc = XmlUtils.parseXmlFile("C://ShipSecurityNotification.xml", false);
		// log.info(MessageFactory.getStringFromDocument(doc));
		String xmlString = MessageFactory.getStringFromDocument(doc);
		Header header = readXMLMessagesHeader(xmlString);
		log.info(header.toString());
		log.info(xmlString);
		List<Object> objects = new ArrayList<Object>();
		objects = MessageFactory.converXMLMessagesContentToObject(xmlString);
		/*
		 * BusinessUtils businessUtils = new BusinessUtils(); Header header = ReadXMLMessagesHeader(xmlString); businessUtils.insertTempTable(header,
		 * xmlString);
		 */log.info("a");
		
	}
}
