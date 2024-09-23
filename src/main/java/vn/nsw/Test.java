package vn.nsw;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;


import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import vn.gt.tichhop.message.MessageFactory;
import vn.nsw.Header;
import vn.nsw.model.NoticeOfArrivalCancel;
import vn.nsw.model.PortClearance;
import vn.nsw.model.ShiftingOrder;




public class Test {

	
	
	 public static void main(String[] args) throws Exception
	    {
	        XPath xPath =  XPathFactory.newInstance().newXPath();
	        String expression = "Envelope/Body/Content";//"//Body/Content";//
	        DOMSource source = null;
	        
	        Document doc =vn.gt.tichhop.message.xml.XmlUtils.parseXmlFile("C://ShipSecurityNotification.xml", false);
	        InputStream inputStream= new FileInputStream("C://ShipSecurity.xml");
            InputStreamReader inputReader = new InputStreamReader(inputStream,"UTF-16");
            InputSource inputSource = new InputSource(inputReader);       
            inputSource.setEncoding("UTF-8");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc1 = builder.parse(inputSource);

	        
	        Node node = (Node) xPath.compile(expression).evaluate(doc1, XPathConstants.NODE);
			source = new DOMSource(node);
			
	        TransformerFactory tFactory = TransformerFactory.newInstance();
	        Transformer transformer = tFactory.newTransformer();
	        transformer.setOutputProperty("indent", "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        StringWriter sw = new StringWriter();
	        StreamResult result = new StreamResult(sw);

	        transformer.transform(source, result);
	        
	        StringReader reader = new StringReader(sw.toString());
	        
	        // create a JAXBContext capable of handling classes generated into
	       // Document doc1 =XmlUtils.parseXmlFile("C://header.xml", false);
	    	
	    	JAXBContext jc = JAXBContext.newInstance( ObjectFactory.class );

	    	// create an Unmarshaller
	    	Unmarshaller unmarshaller = jc.createUnmarshaller();
	    	String xmlString = sw.toString();
	    	//xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
	    	
	    	MessageFactory.convertXmltoXacNhanHuyLDD(xmlString);
	    	
	    	ByteArrayInputStream input = new ByteArrayInputStream (xmlString.getBytes("UTF-8"));

	    	// unmarshal a po instance document into a tree of Java content
	    	// objects composed of classes from the primer.po package.
	    	//System.out.println(xmlString);
	    	//Header header = (Header) unmarshaller.unmarshal(input);
	    	//ShipSecurityNotification po = (ShipSecurityNotification)poElement.getValue();
	    	//System.out.println(xmlString);
	        
	    }
	 
	/* public static void main() throws Exception
	 {
	     XPath xPath =  XPathFactory.newInstance().newXPath();
	     String expression = "Envelope/Header";//"//Body/Content";//
	     DOMSource source = null;
	     
	     Document doc =vn.gt.tichhop.message.xml.XmlUtils.parseXmlFile("/opt/liferay/ShipSecurityNotification.xml", false);
	     InputStream inputStream= new FileInputStream("/opt/liferay/ShipSecurityNotification.xml");
	     InputStreamReader inputReader = new InputStreamReader(inputStream,"UTF-8");
	     InputSource inputSource = new InputSource(inputReader);       
	     inputSource.setEncoding("UTF-8");
	     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder builder = factory.newDocumentBuilder();
	     Document doc1 = builder.parse(inputStream);

	     
	     Node node = (Node) xPath.compile(expression).evaluate(doc1, XPathConstants.NODE);
	 	source = new DOMSource(node);
	 	
	     TransformerFactory tFactory = TransformerFactory.newInstance();
	     Transformer transformer = tFactory.newTransformer();
	     transformer.setOutputProperty("indent", "yes");
	     transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	     StringWriter sw = new StringWriter();
	     StreamResult result = new StreamResult(sw);

	     transformer.transform(source, result);
	     
	     StringReader reader = new StringReader(sw.toString());
	     
	     // create a JAXBContext capable of handling classes generated into
	    // Document doc1 =XmlUtils.parseXmlFile("C://header.xml", false);
	 	
	 	JAXBContext jc = JAXBContext.newInstance( ObjectFactory.class );

	 	// create an Unmarshaller
	 	Unmarshaller unmarshaller = jc.createUnmarshaller();
	 	String xmlString = sw.toString();
	 	//xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
	 	
	 	
	 	
	 	ByteArrayInputStream input = new ByteArrayInputStream (xmlString.getBytes());

	 	// unmarshal a po instance document into a tree of Java content
	 	// objects composed of classes from the primer.po package.
	 	System.out.println(" ====1===========BinhNT===" + xmlString);
	 	Header header = (Header) unmarshaller.unmarshal(input);
	 	//ShipSecurityNotification po = (ShipSecurityNotification)poElement.getValue();
	 	System.out.println(" ====2===========BinhNT===" + xmlString);
	 }*/
	
/*	public static void main(String[] args) throws Exception{
		NoticeOfArrivalCancel object = new NoticeOfArrivalCancel();
		ShiftingOrder order = new ShiftingOrder();
		PortClearance clearance =new PortClearance();
		clearance.setDocumentYear("2014");
		clearance.setNameOfMaster("HUNGVQ");
		order.setCertificateNo("AAAAAAA");
		object.setName("HUNGVq");
		//IssuePortClearance clearance = new IssuePortClearanceModel();
		System.out.println(MessageFactory.convertObjectToXml(clearance));
	}
*/	
	}

