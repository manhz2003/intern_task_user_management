package vn.gt.portlet.phuongtien;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;


import java.util.Locale;


//import org.tempuri.PublishServiceSoap;
//import org.tempuri.PublishServiceSoapProxy;
//import org.tempuri.PublishServiceSoapProxyNode;
//import org.tempuri.PublishServiceSoapProxyNodeHG;
//import org.tempuri.WSPublicEHoaDonSoap;
//import org.tempuri.WSPublicEHoaDonSoapProxy;



import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;

import com.fds.nsw.nghiepvu.model.UserPort;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmShipAgency;
import com.fds.nsw.nghiepvu.model.DmVmaShipOwner;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmGtReportCategory;
import vn.gt.dao.danhmucgt.service.DmGtReportCategoryLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaTransactionReceipt;
import com.fds.nsw.nghiepvu.model.VmaTransactionSlip;
import com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails;
import com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionReceiptLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipDetailsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaVnptServiceConfigLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDebitnote;

import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.tichhop.message.MessageFactory;
import vn.gt.utils.FormatData;
import vn.nsw.model.ArrayOfInvoiceDataWS;
import vn.nsw.model.Customers;
import vn.nsw.model.CommandData;
import vn.nsw.model.Invoices;
import vn.nsw.model.Result;

import com.security.AES;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CallWebserviceClient {
	
	
	public static Customers exportCustomers = new Customers();
	public static Invoices exportInvoices = new Invoices();
	
	public static CommandData exportBKAVInvoices = new CommandData();
	  
	public static String wsAcctName = StringPool.BLANK;
	public static String wsAcctPass = StringPool.BLANK;
	public static String wsUserName = StringPool.BLANK;
	public static String wsPassword = StringPool.BLANK;
	public static String wsPattern = StringPool.BLANK;
	public static String wsSerial = StringPool.BLANK;
	public static String phongtaivuEmail = StringPool.BLANK;
	
	public static String systemAccountant = StringPool.BLANK;
	public static String vnptAccountant = StringPool.BLANK;

	
//	public PublishServiceSoap getPublishServiceSoap(){
//		  return (new PublishServiceSoapProxy()).getPublishServiceSoap();
//	  }
//
//	public static String partnerGUID = StringPool.BLANK;
//	public static String encryptedCommandData = StringPool.BLANK;
//
//	public WSPublicEHoaDonSoap getWSPublicEHoaDonSoap() {
//		return (new WSPublicEHoaDonSoapProxy()).getWSPublicEHoaDonSoap();
//	}
//
//	public PublishServiceSoap getPublishServiceSoapNewNode(){
//		  return (new PublishServiceSoapProxyNode()).getPublishServiceSoap();
//	  }
//
//	public PublishServiceSoap getPublishServiceSoapNewNodeHG(){
//		  return (new PublishServiceSoapProxyNodeHG()).getPublishServiceSoap();
//	  }
//
//	public static int updateCus(Customers customers) throws IOException,
//		RemoteException {
//		CallWebserviceClient callWebserviceClient = new CallWebserviceClient();
//		  PublishServiceSoap publishServiceSoap = callWebserviceClient.getPublishServiceSoap();
//		  String	xml = MessageFactory.convertObjectToXml(customers);
//		  //log.info(xml);
//		  //int test1= publishServiceSoap.updateCus(xml, "cangvuhatinhservice", "Einv@oi@vn#pt20", 0);
//		  int test1 = publishServiceSoap.updateCus(xml, wsUserName, wsPassword, 0);
//
//
//		return test1;
//	  }

//	  public static String importAndPublishInv(Invoices invoices)
//				throws IOException, RemoteException {
//			CallWebserviceClient callWebserviceClient = new CallWebserviceClient();
//			  PublishServiceSoap publishServiceSoap = callWebserviceClient.getPublishServiceSoap();
//			  String	xml2 = MessageFactory.convertObjectToXml(invoices);
//			  //log.info(xml2);
//			  //String test2= publishServiceSoap.importAndPublishInv("cangvuhatinhadmin", "Einv@oi@vn#pt20", xml2, "cangvuhatinhservice","Einv@oi@vn#pt20",  "01BLP0-001", "BL-20E", 0);
//			  String test2 = publishServiceSoap.importAndPublishInv(wsAcctName, wsAcctPass, xml2, wsUserName, wsPassword,  wsPattern, wsSerial, 0);
//
//			  return test2;
//	  }
//
//	  public static String importInv(Invoices invoices)
//				throws IOException, RemoteException {
//			CallWebserviceClient callWebserviceClient = new CallWebserviceClient();
//			  PublishServiceSoap publishServiceSoap = callWebserviceClient.getPublishServiceSoap();
//			  String	xml2 = MessageFactory.convertObjectToXml(invoices);
//			  String test2 = publishServiceSoap.importInv(xml2, wsUserName, wsPassword,  0, Validator.isNotNull(vnptAccountant) ? vnptAccountant: wsAcctName);
//
//			  return test2;
//	  }
//
//	  public static int updateCusNewNode(Customers customers) throws IOException,
//		RemoteException {
//		CallWebserviceClient callWebserviceClient = new CallWebserviceClient();
//		  PublishServiceSoap publishServiceSoap = callWebserviceClient.getPublishServiceSoapNewNode();
//		  String	xml = MessageFactory.convertObjectToXml(customers);
//		  //log.info(xml);
//		  //int test1= publishServiceSoap.updateCus(xml, "cangvuhatinhservice", "Einv@oi@vn#pt20", 0);
//		  int test1 = publishServiceSoap.updateCus(xml, wsUserName, wsPassword, 0);
//
//
//		return test1;
//	  }
//
//	  public static String importAndPublishInvNewNode(Invoices invoices)
//				throws IOException, RemoteException {
//			CallWebserviceClient callWebserviceClient = new CallWebserviceClient();
//			  PublishServiceSoap publishServiceSoap = callWebserviceClient.getPublishServiceSoapNewNode();
//			  String	xml2 = MessageFactory.convertObjectToXml(invoices);
//			  //log.info(xml2);
//			  //String test2= publishServiceSoap.importAndPublishInv("cangvuhatinhadmin", "Einv@oi@vn#pt20", xml2, "cangvuhatinhservice","Einv@oi@vn#pt20",  "01BLP0-001", "BL-20E", 0);
//			  String test2 = publishServiceSoap.importAndPublishInv(wsAcctName, wsAcctPass, xml2, wsUserName, wsPassword,  wsPattern, wsSerial, 0);
//
//			  return test2;
//	  }
//
//	  public static String importInvNewNode(Invoices invoices)
//				throws IOException, RemoteException {
//			CallWebserviceClient callWebserviceClient = new CallWebserviceClient();
//			  PublishServiceSoap publishServiceSoap = callWebserviceClient.getPublishServiceSoapNewNode();
//			  String	xml2 = MessageFactory.convertObjectToXml(invoices);
//			  String test2 = publishServiceSoap.importInv(xml2, wsUserName, wsPassword,  0, Validator.isNotNull(vnptAccountant) ? vnptAccountant: wsAcctName);
//
//			  return test2;
//	  }
//
//
//	  public static String importInvNewNodeHG(Invoices invoices)
//				throws IOException, RemoteException {
//			CallWebserviceClient callWebserviceClient = new CallWebserviceClient();
//			  PublishServiceSoap publishServiceSoap = callWebserviceClient.getPublishServiceSoapNewNodeHG();
//			  String	xml2 = MessageFactory.convertObjectToXml(invoices);
//			  String test2 = publishServiceSoap.importInv(xml2, wsUserName, wsPassword,  0, Validator.isNotNull(vnptAccountant) ? vnptAccountant: wsAcctName);
//
//			  return test2;
//	  }
//
//	  public static int updateCusNewNodeHG(Customers customers) throws IOException,
//		RemoteException {
//		CallWebserviceClient callWebserviceClient = new CallWebserviceClient();
//		  PublishServiceSoap publishServiceSoap = callWebserviceClient.getPublishServiceSoapNewNodeHG();
//		  String	xml = MessageFactory.convertObjectToXml(customers);
//		  log.info(xml);
//		  //int test1= publishServiceSoap.updateCus(xml, "cangvuhatinhservice", "Einv@oi@vn#pt20", 0);
//		  int test1 = publishServiceSoap.updateCus(xml, wsUserName, wsPassword, 0);
//
//
//		return test1;
//	  }
//
//	  public static String importAndPublishInvNewNodeHG(Invoices invoices)
//				throws IOException, RemoteException {
//			CallWebserviceClient callWebserviceClient = new CallWebserviceClient();
//			  PublishServiceSoap publishServiceSoap = callWebserviceClient.getPublishServiceSoapNewNodeHG();
//			  String	xml2 = MessageFactory.convertObjectToXml(invoices);
//			  //log.info(xml2);
//			  //String test2= publishServiceSoap.importAndPublishInv("cangvuhatinhadmin", "Einv@oi@vn#pt20", xml2, "cangvuhatinhservice","Einv@oi@vn#pt20",  "01BLP0-001", "BL-20E", 0);
//			  String test2 = publishServiceSoap.importAndPublishInv(wsAcctName, wsAcctPass, xml2, wsUserName, wsPassword,  wsPattern, wsSerial, 0);
//
//			  return test2;
//	  }
	  
	  
	  public static String escapeXml(String s) {
		    return s.replaceAll("&", "&amp;").replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
		}
	  
//	  public static String encodeXML(CharSequence s) {
//		    StringBuilder sb = new StringBuilder();
//		    int len = s.length();
//		    for (int i=0;i<len;i++) {
//		        int c = s.charAt(i);
//		        if (c >= 0xd800 && c <= 0xdbff && i + 1 < len) {
//		            c = ((c-0xd7c0)<<10) | (s.charAt(++i)&0x3ff);    // UTF16 decode
//		        }
//		        if (c < 0x80) {      // ASCII range: test most common case first
//		            if (c < 0x20 && (c != '\t' && c != '\r' && c != '\n')) {
//		                // Illegal XML character, even encoded. Skip or substitute
//		                sb.append("&#xfffd;");   // Unicode replacement character
//		            } else {
//		                switch(c) {
//		                  case '&':  sb.append("&amp;"); break;
//		                  case '>':  sb.append("&gt;"); break;
//		                  case '<':  sb.append("&lt;"); break;
//		                  // Uncomment next two if encoding for an XML attribute
//		                  case '\'':  sb.append("&apos;"); break;
//		                  case '\"':  sb.append("&quot;"); break;
//		                  // Uncomment next three if you prefer, but not required
//		                  case '\n':  sb.append("&#10;"); break;
//		                  case '\r':  sb.append("&#13;"); break;
//		                  case '\t':  sb.append("&#9;"); break;
//
//		                  default:   sb.append((char)c);
//		                }
//		            }
//		        } else if ((c >= 0xd800 && c <= 0xdfff) || c == 0xfffe || c == 0xffff) {
//		            // Illegal XML character, even encoded. Skip or substitute
//		            sb.append("&#xfffd;");   // Unicode replacement character
//		        } else {
//		            sb.append("&#x");
//		            sb.append(Integer.toHexString(c));
//		            sb.append(';');
//		        }
//		    }
//		    return sb.toString();
//	  }
//	  public static void exportCustomersAndInvoicesToVNPT_Mode1(String itineraryNo, String documentaryCode, long userId) {
//			// 1. Tao du lieu khach hang --- for updateCus
//			// 2. Tao du lieu hoa don --- for importAndPublishInv
//			try {
//
//				VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
//						.fetchByitineraryNo_documentaryCode(itineraryNo,
//								documentaryCode);
//				VmaTransactionSlipDetails vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
//						.fetchByItineraryNo_DocumentaryCode(itineraryNo,
//								documentaryCode);
//
//				// Kiem tra cau hinh he thong Bien lai dien tu. Neu khong thay thi thoat khoi ham nay.
//				int testMode = 9;
//				VmaVnptServiceConfig ProdserviceConf = VmaVnptServiceConfigLocalServiceUtil.findByTestMode(testMode);
//
//				if (Validator.isNotNull(ProdserviceConf)) {
//					String maritimeCode = ProdserviceConf.getMaritimeCode();
//
//					DmGtReportCategory dmGtReportCategory = DmGtReportCategoryLocalServiceUtil
//							.findByOrganizationCode(maritimeCode);
//
//					if (Validator.isNotNull(ProdserviceConf) && ProdserviceConf.getTestMode() >= 9 && Validator.isNotNull(dmGtReportCategory)) {
//						wsAcctName = ProdserviceConf.getInvoiceAccount();
//						wsAcctPass = ProdserviceConf.getInvoiceACpass();
//						wsUserName = ProdserviceConf.getRemoteUsername();
//						wsPassword = ProdserviceConf.getWebservicePassword();
//						wsPattern = ProdserviceConf.getPatternCode();
//						wsSerial = ProdserviceConf.getSerialCode();
//						if (!(ProdserviceConf.getSyncVersion().equalsIgnoreCase("1|"))) {
//							phongtaivuEmail = ProdserviceConf.getSyncVersion();
//						}
////						partnerGUID = ProdserviceConf.getInvoiceAccount();
//					}
//
//					if (ProdserviceConf.getMaritimeCode().equalsIgnoreCase("16") ||
//							ProdserviceConf.getMaritimeCode().equalsIgnoreCase("16TV") ||
//							ProdserviceConf.getMaritimeCode().equalsIgnoreCase("16HG")
//							|| ProdserviceConf.getMaritimeCode().equalsIgnoreCase("16ST")) {
//						VmaItinerarySchedule vmaItineraryScheduleArrival = null;
//						VmaItinerarySchedule vmaItineraryScheduleDeparture = null;
//						vmaItineraryScheduleArrival = VmaItineraryScheduleLocalServiceUtil
//								.findByItineraryNo_NoticeShipType(itineraryNo, 1);
//						vmaItineraryScheduleDeparture = VmaItineraryScheduleLocalServiceUtil
//								.findByItineraryNo_NoticeShipType(itineraryNo, 2);
//
//
//						if (
//						(vmaItineraryScheduleArrival != null
//								&& vmaItineraryScheduleArrival.getArrivalPortCode() != null
//								&& vmaItineraryScheduleArrival.getPortRegionCode() != null
//								&& vmaItineraryScheduleArrival.getArrivalPortCode().equalsIgnoreCase("VNDTV")
//								&& vmaItineraryScheduleArrival.getPortRegionCode().equalsIgnoreCase("080"))
//						||
//						(vmaItineraryScheduleDeparture != null
//						&& vmaItineraryScheduleDeparture.getArrivalPortCode() != null
//						&& vmaItineraryScheduleDeparture.getPortRegionCode() != null
//						&& vmaItineraryScheduleDeparture.getArrivalPortCode().equalsIgnoreCase("VNDTV")
//						&& vmaItineraryScheduleDeparture.getPortRegionCode().equalsIgnoreCase("080"))
//						){ // Duyen hai Tra Vinh
//
//							testMode = 10;
//							VmaVnptServiceConfig CNProdserviceConf = VmaVnptServiceConfigLocalServiceUtil.findByTestMode(testMode);
//							if (Validator.isNotNull(CNProdserviceConf)) {
//								maritimeCode = CNProdserviceConf.getMaritimeCode();
//
//								dmGtReportCategory = DmGtReportCategoryLocalServiceUtil
//										.findByOrganizationCode(maritimeCode);
//
//								if (Validator.isNotNull(CNProdserviceConf) && CNProdserviceConf.getTestMode() >= 9 && Validator.isNotNull(dmGtReportCategory)) {
//									wsAcctName = CNProdserviceConf.getInvoiceAccount();
//									wsAcctPass = CNProdserviceConf.getInvoiceACpass();
//									wsUserName = CNProdserviceConf.getRemoteUsername();
//									wsPassword = CNProdserviceConf.getWebservicePassword();
//									wsPattern = CNProdserviceConf.getPatternCode();
//									wsSerial = CNProdserviceConf.getSerialCode();
//									if (!(CNProdserviceConf.getSyncVersion().equalsIgnoreCase("1|"))) {
//										phongtaivuEmail = CNProdserviceConf.getSyncVersion();
//									}
////									partnerGUID = CNProdserviceConf.getInvoiceAccount();
//
//									log.info("---------Push invoices --------VNPT: Duyen hai Tra Vinh" + "---------RemoteUser---------"+CNProdserviceConf.getRemoteUsername());
//								}
//							}
//						}
//
//						else if (vmaTransactionSlip != null && vmaTransactionSlip.getDepartmentCode().equalsIgnoreCase("CTO3"))
//							 /*|| (vmaItineraryScheduleArrival != null
//										&& vmaItineraryScheduleArrival.getArrivalPortCode() != null
//										&& vmaItineraryScheduleArrival.getPortRegionCode() != null
//										&& (vmaItineraryScheduleArrival.getArrivalPortCode().equalsIgnoreCase("VNCHG") || vmaItineraryScheduleArrival.getArrivalPortCode().equalsIgnoreCase("VN093"))
//										&& (vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC598") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC614") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC607") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC582") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC494") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC365") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC216"))
//										&& vmaItineraryScheduleArrival.getPortRegionCode().equalsIgnoreCase("021"))
//								||
//								(vmaItineraryScheduleDeparture != null
//								&& vmaItineraryScheduleDeparture.getArrivalPortCode() != null
//								&& vmaItineraryScheduleDeparture.getPortRegionCode() != null
//								&& (vmaItineraryScheduleDeparture.getArrivalPortCode().equalsIgnoreCase("VNCHG") || vmaItineraryScheduleDeparture.getArrivalPortCode().equalsIgnoreCase("VN093"))
//								&& (vmaItineraryScheduleDeparture.getPortHarbourCode().equalsIgnoreCase("BC598") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC614") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC607") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC582") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC494") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC365") || vmaItineraryScheduleArrival.getPortHarbourCode().equalsIgnoreCase("BC216"))
//								&& vmaItineraryScheduleDeparture.getPortRegionCode().equalsIgnoreCase("021"))*/
//								{ // Hau Giang
//
//									testMode = 11;
//									VmaVnptServiceConfig CNProdserviceConf = VmaVnptServiceConfigLocalServiceUtil.findByTestMode(testMode);
//									if (Validator.isNotNull(CNProdserviceConf)) {
//										maritimeCode = CNProdserviceConf.getMaritimeCode();
//
//										dmGtReportCategory = DmGtReportCategoryLocalServiceUtil
//												.findByOrganizationCode(maritimeCode);
//
//										if (Validator.isNotNull(CNProdserviceConf) && CNProdserviceConf.getTestMode() >= 9 && Validator.isNotNull(dmGtReportCategory)) {
//											wsAcctName = CNProdserviceConf.getInvoiceAccount();
//											wsAcctPass = CNProdserviceConf.getInvoiceACpass();
//											wsUserName = CNProdserviceConf.getRemoteUsername();
//											wsPassword = CNProdserviceConf.getWebservicePassword();
//											wsPattern = CNProdserviceConf.getPatternCode();
//											wsSerial = CNProdserviceConf.getSerialCode();
//											if (!(CNProdserviceConf.getSyncVersion().equalsIgnoreCase("1|"))) {
//												phongtaivuEmail = CNProdserviceConf.getSyncVersion();
//											}
////											partnerGUID = CNProdserviceConf.getInvoiceAccount();
//
//											log.info("---------Push invoices --------VNPT: Hau Giang" + "---------RemoteUser---------"+CNProdserviceConf.getRemoteUsername());
//										}
//									}
//								}
//					}
//				} else {
//					testMode = 1;
//					VmaVnptServiceConfig serviceConf = VmaVnptServiceConfigLocalServiceUtil.findByTestMode(testMode);
//					if (Validator.isNotNull(serviceConf))  {
//						String maritimeCode = serviceConf.getMaritimeCode();
//
//						DmGtReportCategory dmGtReportCategory = DmGtReportCategoryLocalServiceUtil
//								.findByOrganizationCode(maritimeCode);
//
//						if (Validator.isNotNull(serviceConf) && serviceConf.getTestMode() == 1 && Validator.isNotNull(dmGtReportCategory)) {
//							wsAcctName = serviceConf.getTestInvoiceAccount();
//							wsAcctPass = serviceConf.getTestInvoiceACpass();
//							wsUserName = serviceConf.getTestRemoteUsername();
//							wsPassword = serviceConf.getTestWebservicePassword();
//							wsPattern = serviceConf.getPatternCode();
//							wsSerial = serviceConf.getSerialCode();
//
////							partnerGUID = serviceConf.getTestInvoiceAccount();
//							phongtaivuEmail = StringPool.BLANK;
//						}
//					} else if (serviceConf == null && ProdserviceConf == null){
//						//log.info("Chua cau hinh, khong xuat bien lai dien tu");
//						return;
//					}
//				}
//
//
//
//
//
//
//				String maritimeName = StringPool.BLANK;
//				String cityCode = StringPool.BLANK;
//				String maritimeNameVN = StringPool.BLANK;
//				String maritimeCode  = StringPool.BLANK;
//
//				try {
//					UserPort userPort = UserPortLocalServiceUtil.findByUserId(userId);
//					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
//							.getByMaritimeCode(userPort.getPortCode());
//					maritimeName = dmMaritime.getMaritimeName();
//					cityCode = dmMaritime.getCityCode();
//					maritimeNameVN = dmMaritime.getMaritimeNameVN();
//					maritimeCode = dmMaritime.getMaritimeCode();
//					User accountUser = UserLocalServiceUtil.fetchUser(userId);
//					systemAccountant = accountUser.getFirstName();
//					vnptAccountant = accountUser.getLastName();
//				} catch (Exception e) {
//					log.error(e.getMessage());
//				}
//
//				exportCustomers = null;
//				exportInvoices = null;
//				exportBKAVInvoices =  null;
//
//				 // Tạo dữ liệu biên lai BKAV - pushInvoice
//
//				  ArrayOfInvoiceDataWS invoiceArray = new ArrayOfInvoiceDataWS();
//
//				  ArrayOfInvoiceDataWS.InvoiceDataWS invoiceDataws = new ArrayOfInvoiceDataWS.InvoiceDataWS();
//				  ArrayOfInvoiceDataWS.InvoiceDataWS.Invoice invoiceBkav = new ArrayOfInvoiceDataWS.InvoiceDataWS.Invoice();
//				  ArrayOfInvoiceDataWS.InvoiceDataWS.Invoice.UserDefine invoiceBkavUserDefine = new ArrayOfInvoiceDataWS.InvoiceDataWS.Invoice.UserDefine();
//				  ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS invoiceDataListDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS();
//				  ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//				  ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//				  ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceAttachFileWS invoiceDataListAttach = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceAttachFileWS();
//
//				  invoiceBkav.setInvoiceTypeID("7");
//				  invoiceBkav.setInvoiceDate(FormatData.formatDDMMYYYY.format(new Date()));
//				  invoiceBkav.setInvoiceSerial(wsSerial);
//				  invoiceBkav.setInvoiceNo("0");
//				  invoiceBkav.setInvoiceForm(wsPattern);
//
//				  invoiceBkav.setBuyerName(vmaTransactionSlip.getItineraryNo());
//				  invoiceBkav.setBuyerTaxCode(vmaTransactionSlip.getShipAgencyCode());
//
//				// Export here
//				// Tao du lieu khach hang --- for updateCus
//				  Customers customers = new Customers();
//				  Customers.Customer customer = new Customers.Customer();
//				  customer.setCode(vmaTransactionSlip.getItineraryNo());
//				  customer.setTaxCode(vmaTransactionSlip.getShipAgencyCode());
//
//				  if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("3")) {
//					  customer.setName(vmaTransactionSlip.getPaymentName());
//					  customer.setTaxCode("----TT----");
//					  invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getPaymentName());
//				  } else if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("2")){
//					  customer.setName(vmaTransactionSlip.getShipOwnerName());
//					  invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getShipOwnerName());
//					  try {
//						  VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil.fetchByitineraryNo(itineraryNo);
//
//						  if (Validator.isNotNull(vmaItinerary.getShipOwnerCode())) {
//							  DmVmaShipOwner dmVmaShipOwner = DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaItinerary.getShipOwnerCode());
//							  if (Validator.isNotNull(dmVmaShipOwner) && Validator.isNotNull(dmVmaShipOwner.getTaxCode())) {
//								  customer.setCode(dmVmaShipOwner.getTaxCode());
//								  customer.setTaxCode(dmVmaShipOwner.getTaxCode());
//								  invoiceBkav.setBuyerTaxCode(dmVmaShipOwner.getTaxCode());
//							  }
//						  }
//						} catch (Exception e) {
//
//						}
//					  if (Validator.isNull(vmaTransactionSlip.getShipOwnerName())
//							  || (vmaTransactionSlip.getShipOwnerName().trim().length() <= 1)
//							  || (vmaTransactionSlip.getShipOwnerName().equalsIgnoreCase("0"))) {
//						  customer.setName(vmaTransactionSlip.getPaymentName());
//						  customer.setTaxCode("----TT----");
//						  invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getPaymentName());
//					  }
//				  } else if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("1")){
//
//					  if (Validator.isNull(vmaTransactionSlip.getShipAgencyName())) {
//						  customer.setName(vmaTransactionSlip.getPaymentName()); // lay don vi thanh toan neu khong co dai ly
//						  invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getPaymentName());
//					  } else {
//						  customer.setName(vmaTransactionSlip.getShipAgencyName());
//						  customer.setCode(vmaTransactionSlip.getShipAgencyCode());
//						  customer.setTaxCode(vmaTransactionSlip.getShipAgencyCode());
//						  invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getShipAgencyName());
//						  invoiceBkav.setBuyerTaxCode(vmaTransactionSlip.getShipAgencyCode());
//					  }
//				  } else {
//					  customer.setName(vmaTransactionSlip.getNameOfShip());
//					  invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getNameOfShip());
//				  }
//
//
//				  invoiceBkav.setBuyerAddress("---");
//				  invoiceBkav.setBuyerBankAccount("");
//				  invoiceBkav.setPayMethodID("3");
//				  invoiceBkav.setReceiveTypeID("");
//				  invoiceBkav.setReceiverName("");
//				  invoiceBkav.setReceiverEmail("dvc.bgtvt@gmail.com");
//				  if (Validator.isNotNull(vmaTransactionSlip.getEmailRecipients())) {
//					  invoiceBkav.setReceiverEmail(vmaTransactionSlip.getEmailRecipients());
//				  } else if (Validator.isNotNull(phongtaivuEmail )) {
//					  invoiceBkav.setReceiverEmail(phongtaivuEmail);
//				  }
//				  invoiceBkav.setReceiverAddress("");
//				  invoiceBkav.setReceiverMobile("");
//				  invoiceBkav.setNote(Validator.isNotNull(systemAccountant) ? systemAccountant : "system");
//				  invoiceBkav.setBillCode("");
//				  invoiceBkav.setCurrencyID("VND");
//				  invoiceBkav.setExchangeRate(String.valueOf(vmaTransactionSlip.getExchangeRate()));
//
//
//				  if (Validator.isNull(customer.getTaxCode())) {
//					  customer.setTaxCode("----TT----");
//				  }
//
//				  if (Validator.isNull(invoiceBkav.getBuyerTaxCode())) {
//					  invoiceBkav.setBuyerTaxCode("----TT----");
//				  }
//				  if (Validator.isNull(customer.getCode())) {
//					  customer.setCode(vmaTransactionSlip.getItineraryNo());
//				  }
//				  customer.setAddress("---");
//				  customer.setBankAccountName("");
//				  customer.setBankName("");
//				  customer.setBankNumber("");
//				  customer.setEmail("dvc.bgtvt@gmail.com");
//				  if (Validator.isNotNull(vmaTransactionSlip.getEmailRecipients())) {
//					  customer.setEmail(vmaTransactionSlip.getEmailRecipients());
//				  } else if (Validator.isNotNull(phongtaivuEmail )) {
//					  customer.setEmail(phongtaivuEmail);
//				  }
//				  customer.setFax("");
//				  customer.setPhone("");
//				  customer.setContactPerson("");
//				  customer.setRepresentPerson("");
//
//				  if(isCustomDoanhNghiep(customer.getName(),customer.getTaxCode())){
//
//					  customer.setCusType("1");
//				  } else
//				  {
//					  customer.setCusType("0");
//				  }
//				if (vmaTransactionSlip.getPaymentName().toString().trim().contains("TU TUC")
//					|| vmaTransactionSlip.getPaymentName().toString().trim().equalsIgnoreCase("TT")
//						  ) {
//					  customer.setCusType("0");
//				  }
//				  customers.getCustomer().add(customer);
//				  log.info("---------customers--------TaxCode:" + customer.getTaxCode());
//
//
//				// Tao du lieu bien lai dien tu --- importAndPublishInv
//				  Invoices invoices = new Invoices();
//				  Invoices.Inv inv = new Invoices.Inv();
//
//				  inv.setKey(vmaTransactionSlip.getId() + "_" + vmaTransactionSlip.getDocumentaryCode().toString());
//				// inv.setKey(vmaTransactionSlip.getDocumentaryCode().toString());
//
//				  Invoices.Inv.Invoice invoiceDetails = new Invoices.Inv.Invoice();
//				  invoiceDetails.setShipName(vmaTransactionSlip.getNameOfShip().toString()); // Tên tàu
//				  invoiceDetails.setShipFlag(vmaTransactionSlip
//							.getFlagStateOfShip() != null ? DmStateLocalServiceUtil
//									.getByStateCode(vmaTransactionSlip.getFlagStateOfShip())
//									.getStateName() : ""); // Quốc tịch
//				  invoiceDetails.setExtra3(vmaTransactionSlip.getCallSign()); // Hô hiệu
//				  if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("4")) {
//					  if (Validator.isNull(vmaTransactionSlip.getShipOperationName())
//							  || (vmaTransactionSlip.getShipOperationName().trim().length() <= 1)
//							  || (vmaTransactionSlip.getShipOperationName().equalsIgnoreCase("0"))) {  // Người khai thác
//						  invoiceDetails.setShipOwner(vmaTransactionSlip.getPaymentName());
//						  invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getPaymentName());
//					  } else {
//						  invoiceDetails.setShipOwner(vmaTransactionSlip.getShipOperationName());
//						  invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getShipOperationName());
//					  }
//				  } else if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("3")) {
//					  invoiceDetails.setShipOwner(vmaTransactionSlip.getPaymentName()); // Người thanh toán Khác
//					  invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getPaymentName());
//				  } else if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("2")) {
//					  invoiceDetails.setShipOwner(vmaTransactionSlip.getShipOwnerName()); // Chủ tàu hoặc chủ hàng
//					  invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getShipOwnerName());
//					  if (Validator.isNull(vmaTransactionSlip.getShipOwnerName())
//							  || (vmaTransactionSlip.getShipOwnerName().trim().length() <= 1)
//							  || (vmaTransactionSlip.getShipOwnerName().equalsIgnoreCase("0"))) {
//						  invoiceDetails.setShipOwner(vmaTransactionSlip.getPaymentName());
//						  invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getPaymentName());
//					  }
//				  } else {
//					  invoiceDetails.setShipOwner(vmaTransactionSlip.getShipOwnerName());
//					  if (Validator.isNull(vmaTransactionSlip.getShipOwnerName())
//							  || (vmaTransactionSlip.getShipOwnerName().trim().length() <= 1)
//							  || (vmaTransactionSlip.getShipOwnerName().equalsIgnoreCase("0"))) {
//						  invoiceDetails.setShipOwner("");
//						  invoiceBkavUserDefine.setOwner("");
//					  }
//
//					// Duy nhat CVHH Hải Phòng muốn hiển thị Người khai thác ở mục Chủ tàu
//					  if  (maritimeCode.equalsIgnoreCase("19")
//							  && (!(Validator.isNull(vmaTransactionSlip.getShipOperationName())
//									  || (vmaTransactionSlip.getShipOperationName().trim().length() <= 1)
//									  || (vmaTransactionSlip.getShipOperationName().equalsIgnoreCase("0"))))
//									  ){
//						  invoiceDetails.setShipOwner(vmaTransactionSlip.getShipOperationName());
//						  invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getShipOperationName());
//					  }
//				  }
//				  if (Validator.isNotNull(vmaTransactionSlip.getShipAgencyCode())) {
//					  invoiceDetails.setCusName(customer.getName()); // Đại lý
//				  } else {
//					  invoiceDetails.setCusName("---"); // Không truyền vào
//				  }
//
//				  invoiceDetails.setCusCode(customer.getCode());
//				  if (maritimeCode.equalsIgnoreCase("11") || maritimeCode.equalsIgnoreCase("2")
//						  || maritimeCode.equalsIgnoreCase("4") || maritimeCode.equalsIgnoreCase("24")) { // Sửa riêng cho CV chạy chính thức bldt
//					  invoiceDetails.setCusEmail("dvc.bgtvt@gmail.com");
//					  if (Validator.isNotNull(vmaTransactionSlip.getEmailRecipients())) {
//						  invoiceDetails.setCusEmail(vmaTransactionSlip.getEmailRecipients());
//					  } else if (Validator.isNotNull(phongtaivuEmail )) {
//						  invoiceDetails.setCusEmail(phongtaivuEmail);
//					  }
//				  }
//
//				  invoiceBkavUserDefine.setNameOfShip(vmaTransactionSlip.getNameOfShip().toString()); // Tên tàu
//				  invoiceBkavUserDefine.setNationalOfShip(vmaTransactionSlip
//							.getFlagStateOfShip() != null ? DmStateLocalServiceUtil
//									.getByStateCode(vmaTransactionSlip.getFlagStateOfShip())
//									.getStateName() : ""); // Quốc tịch
//				  invoiceBkavUserDefine.setGRT(String.valueOf(vmaTransactionSlip.getGt()));
//				  invoiceBkavUserDefine.setDWT(String.valueOf(vmaTransactionSlip.getDwt())); //DWT
//				  invoiceBkavUserDefine.setCallsign(vmaTransactionSlip.getCallSign()); // Hô hiệu
//				  invoiceBkavUserDefine.setCargo(vmaTransactionSlip.getCargoDescription()); // Hàng hóa xếp, dỡ
//				  invoiceBkavUserDefine.setAgent(vmaTransactionSlip.getShipAgencyName()); // Đại lý
//
//				  invoiceBkavUserDefine.setArrivalPort(vmaTransactionSlip.getArrivalPortName()); // Cảng đến
//				  invoiceBkavUserDefine.setLastPort(vmaTransactionSlip.getLastPortName()); // Cảng rời
//				  invoiceBkavUserDefine.setNextPort(vmaTransactionSlip.getNextPortName()); // Cảng đến tiếp theo
//				  invoiceBkavUserDefine.setIsShowInwordsEN("0");
//				  try {
//					  invoiceBkavUserDefine.setArrivalDate(FormatData.formatDDMMYYYY
//								.format(vmaTransactionSlip.getArrivalDate())); // Ngày đến
//					} catch (Exception e) {
//						invoiceBkavUserDefine.setArrivalDate(StringPool.BLANK);
//					}
//					try {
//						invoiceBkavUserDefine.setDepartureDate(FormatData.formatDDMMYYYY.format(vmaTransactionSlip
//								.getDepartureDate())); // Ngày rời
//					} catch (Exception e) {
//						invoiceBkavUserDefine.setDepartureDate(StringPool.BLANK);
//					}
//
//
//				  invoiceDetails.setCusAddress("---");
//				  invoiceDetails.setCusPhone("");
//
//				  if(customer.getCusType().equals("1")){
//					  //for cong ty
//					  invoiceDetails.setCusTaxCode(vmaTransactionSlip.getShipAgencyCode().toString());
//				  }else{
//					  //for ca nhan
//					  invoiceDetails.setCusTaxCode("");
//				  }
//
//				  invoiceDetails.setArisingDate(FormatData.formatDDMMYYYY.format(new Date())); // Ngày xuất biên lai
//				  try {
//					  invoiceDetails.setArrivalDate(FormatData.formatDDMMYYYY
//								.format(vmaTransactionSlip.getArrivalDate())); // Ngày đến
//					} catch (Exception e) {
//						invoiceDetails.setArrivalDate(StringPool.BLANK);
//					}
//				  try {
//						invoiceDetails.setDepatureDate(FormatData.formatDDMMYYYY.format(vmaTransactionSlip
//								.getDepartureDate())); // Ngày rời
//					} catch (Exception e) {
//						invoiceDetails.setDepatureDate(StringPool.BLANK);
//					}
//
//				  invoiceDetails.setShipGRT(String.valueOf(vmaTransactionSlip.getGt()));
//				  invoiceDetails.setShipGT(String.valueOf(vmaTransactionSlip.getDwt())); //DWT
//				  invoiceDetails.setShipLOA(vmaTransactionSlip.getArrivalPortName()); // Cảng đến
//				  invoiceDetails.setBerth(vmaTransactionSlip.getLastPortName()); // Cảng rời
//				  invoiceDetails.setExtra2(vmaTransactionSlip.getNextPortName()); // Cảng đến tiếp theo
//				  invoiceDetails.setCusBankName(vmaTransactionSlip.getCargoDescription()); // Hàng hóa xếp, dỡ
//
//
//				  invoiceDetails.setPaymentMethod("Chuyển khoản");
//				  invoiceBkav.setPayMethodID("2"); // Chuyển khoản
//				  if (vmaTransactionSlip.getPaymentType() == 2)
//				  {
//					  invoiceDetails.setPaymentMethod("Tiền mặt");
//					  invoiceBkav.setPayMethodID("1"); // Tiền mặt
//				  } else if (vmaTransactionSlip.getPaymentType() == 3)
//				  {
//					  invoiceDetails.setPaymentMethod("Ký quỹ");
//					  invoiceBkav.setPayMethodID("13"); // Trả trước
//				  }
//				  invoiceDetails.setPaymentStatus("1");
//				  invoiceDetails.setKindOfService(FormatData.getYear(vmaTransactionSlip.getDocumentaryIssued()) + FormatData.getMonth(vmaTransactionSlip.getDocumentaryIssued()));
//				  Locale locale = new Locale("en", "UK");
//
//				  DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
//				  symbols.setDecimalSeparator(',');
//				  symbols.setGroupingSeparator('.');
//
//				  String pattern = "#,##0.###";
//				  DecimalFormat formatter = new DecimalFormat(pattern, symbols);
//				  String patternVND = "#,##0";
//				  DecimalFormat formatterVND = new DecimalFormat(patternVND, symbols);
//
//				  // Chi tiet tung dong ghi tren hoa don
//				  Invoices.Inv.Invoice.Products products = new Invoices.Inv.Invoice.Products();
//				  List<VmaTransactionReceipt> listVmaTransactionReceipt = VmaTransactionReceiptLocalServiceUtil
//						  .findByMaritimeCode(vmaTransactionSlip.getPortofAuthority());
//
//				  for (VmaTransactionReceipt objVmaTransactionReceipt : listVmaTransactionReceipt) {
//
//
//				 // Chuan bi list thong tin phi le phi
//				  int stt = 0;
//				  String CacKhoanPhi = StringPool.BLANK;
//				  Invoices.Inv.Invoice.Products.Product product = new Invoices.Inv.Invoice.Products.Product();
//
//				  // <!-- Các khoản phí -->
//				  String ProdName = StringPool.BLANK;
//
//				  // <!-- Số tiền bằng số của đồng Đôla Mỹ USD  -->
//				  Double Extra1_ThanhTienDongUSD = 0.00;
//
//				  // <!-- Đơn giá -->
//				  long ProdPrice = 0;
//
//				  // <!-- total Thành tiền bằng số Việt nam đồng-->
//				  long Total_ThanhTienVietNamDong = 0;
//				  long Extra1_ThanhTienDongUSDQuyDoi = 0;
//
//				  // <!-- Ghi chú tính phí, lệ phí -->
//				  String Remark = StringPool.BLANK;
//				  String GTRemarks = vmaTransactionSlip.getGtRemarks();
//
//				  boolean flagChangeSymbolReceipt = false;
//					if (vmaTransactionSlip.getItineraryNo().contains("HPG")) {
//						flagChangeSymbolReceipt = true;
//					}
//
//					if (Validator.isNotNull(GTRemarks)) {
//						if (flagChangeSymbolReceipt == true) {
//							GTRemarks = StringUtil.replace(GTRemarks, "*", "x");
//						}
//					}
//
//				  stt = 1;
//				  CacKhoanPhi = "";
//				  if (CacKhoanPhi.equalsIgnoreCase("GtRemarks")){
//					  product.setProdName(ProdName);
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark(GTRemarks);
//					  products.getProduct().add(product);
//
//					  // BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("1");
//					  invoiceDetail.setItemName(ProdName);
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//
//				  }
//
//				  CacKhoanPhi = objVmaTransactionReceipt.getTransactionTypeCode().toString();
//
//				  stt = 2;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "PortDues"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("PortDues")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("I. Phí cảng vụ (Port dues)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark(GTRemarks);
//					  if  (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hải Phòng
//						  product.setRemark("");
//						  if (vmaTransactionSlipDetails.getGtConversion() == vmaTransactionSlip.getGt().doubleValue()) {
//								// do nothing
//							} else {
//								invoiceDetails.setShipGRT(invoiceDetails.getShipGRT()+ " GTQĐ: "+ vmaTransactionSlipDetails.getGtConversion());
//							}
//
//						if ((vmaTransactionSlip.getUsdTotalAmount() > 0) &&
//							  (maritimeCode.equalsIgnoreCase("19"))){
//						  product.setRemark("TG: "+ formatterVND
//									.format(vmaTransactionSlip.getExchangeRate()) );
//						  if ((vmaTransactionSlip.getPrintedConvertVND() == 1) ){
//								// Trường hợp In quy đổi VNĐ từ USD,
//						  } else if ((vmaTransactionSlip.getHideExchangeRate() == 0) && (vmaTransactionSlip.getPrintedConvertVND() == 0) ) {
//							  	// Trường hợp In quy đổi VNĐ từ USD,
//						  } else {
//							 product.setRemark("");
//						  }
//						}
//					  }
//					  if  (maritimeCode.equalsIgnoreCase("2")){ // Duy nhất CVHH Thanh Hóa
//						  product.setRemark(invoiceDetails.getPaymentMethod() + " " + GTRemarks);
//					  }
//
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("2");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks(GTRemarks);
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 3;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "TonnageFee"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("TonnageFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("1. Phí trọng tải (Tonnage fee)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("3");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 4;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "InwardTonnageFee";
//				  if (CacKhoanPhi.equalsIgnoreCase("InwardTonnageFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Lượt vào (Inward)");
//					  log.info("vmaTransactionSlipDetails.getInF1311Vnd()======" + vmaTransactionSlipDetails.getInF1311Vnd());
//					  Remark = "";
//					  ProdPrice = 0;
////					  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1311Vnd()).longValue());
////					  if (vmaTransactionSlipDetails.getInF1313Vnd() > 0) {
////						  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1313Vnd()).longValue());
////					  }
////					  Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getInF1312Usd()));
////					  Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1312Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
////
//					  if (vmaTransactionSlipDetails.getInF1311Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1311Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1311Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//							} else {
//								product.setRemark(vmaTransactionSlipDetails.getF1311Remarks());
//							}
//					  } else if (vmaTransactionSlipDetails.getInF1312Usd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1312Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1312Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//							} else {
//								product.setRemark(vmaTransactionSlipDetails.getF1312Remarks());
//							}
//					  } else if (vmaTransactionSlipDetails.getInF1313Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getInF1313Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getInF1313Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//							} else {
//								product.setRemark(vmaTransactionSlipDetails.getInF1313Remarks());
//							}
//					  } else {
//						  product.setRemark("");
//					  }
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//					  }
//
//					// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//						if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//							product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//						  }
//
//					if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
//						// Trường hợp In quy đổi VNĐ từ USD,
//						product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						product.setExtra1("0");
//
//						if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//							product.setAmount("0");
//						}
//					}
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("4");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  invoiceDetailDefined.setRemarks(product.getRemark());
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 5;			product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "OutwardTonnageFee";
//				  if (CacKhoanPhi.equalsIgnoreCase("OutwardTonnageFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Lượt rời (Outward)");
//
//					  Remark = "";
//					  ProdPrice = 0;
//					  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getOutF1311Vnd()).longValue());
//					  if (vmaTransactionSlipDetails.getOutF1313Vnd() > 0) {
//						  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getOutF1313Vnd()).longValue());
//					  }
//					  Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getOutF1312Usd()));
//					  Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getOutF1312Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//
//					  if (vmaTransactionSlipDetails.getOutF1311Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1311Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1311Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//							} else {
//								product.setRemark(vmaTransactionSlipDetails.getF1311Remarks());
//							}
//					  } else if (vmaTransactionSlipDetails.getOutF1312Usd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1312Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1312Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//							} else {
//								product.setRemark(vmaTransactionSlipDetails.getF1312Remarks());
//							}
//					  } else if (vmaTransactionSlipDetails.getOutF1313Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getInF1313Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getInF1313Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//							} else {
//								product.setRemark(vmaTransactionSlipDetails.getInF1313Remarks());
//							}
//					  } else {
//						  product.setRemark("");
//					  }
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//					  }
//					// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//						if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//							product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//						  }
//
//						if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
//							// Trường hợp In quy đổi VNĐ từ USD,
//							product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setExtra1("0");
//
//							if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//								product.setAmount("0");
//							}
//						}
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("5");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  invoiceDetailDefined.setRemarks(product.getRemark());
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 6;			product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "AnchorageFee"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("AnchorageFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("2. Phí neo đậu (Anchorage fee)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("6");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 7;			product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "OnShipAnchorageFee";
//				  if (CacKhoanPhi.equalsIgnoreCase("OnShipAnchorageFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Đối với phương tiện (On ship)");
//
//					  Remark = "";
//					  ProdPrice = 0;
//					  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1321Vnd()).longValue());
//					  Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getInF1322Usd()));
//					  Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1322Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//
//					  if ((vmaTransactionSlipDetails.getInF1321Vnd() > 0) && (vmaTransactionSlipDetails.getInF1322Usd() > 0)) {
//						  ProdPrice = 0;
//
//						  product.setRemark(vmaTransactionSlipDetails.getF1321Remarks());
//						  product.setRemark(product.getRemark() + "; " + vmaTransactionSlipDetails.getF1322Remarks());
//						  if (Validator.isNotNull(product.getRemark()) && (flagChangeSymbolReceipt == true)) {
//							  product.setRemark(StringUtil.replace(product.getRemark(), "*", "x"));
//						  }
//					  } else if (vmaTransactionSlipDetails.getInF1321Vnd() > 0)  {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1321Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1321Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//						  } else {
//							  product.setRemark(vmaTransactionSlipDetails.getF1321Remarks());
//						  }
//					  } else if (vmaTransactionSlipDetails.getInF1322Usd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1322Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1322Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//						  } else {
//							  product.setRemark(vmaTransactionSlipDetails.getF1322Remarks());
//						  }
//					  } else {
//						  product.setRemark("");
//					  }
//
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//					  }
//					// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//						if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//							product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//						  }
//
//						if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
//							// Trường hợp In quy đổi VNĐ từ USD,
//							product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setExtra1("0");
//
//							if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//								product.setAmount("0");
//							}
//						}
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("7");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  invoiceDetailDefined.setRemarks(product.getRemark());
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 8;			product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "OnCargoAnchorageFee";
//				  if (CacKhoanPhi.equalsIgnoreCase("OnCargoAnchorageFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Đối với hàng hóa (On cargo)");
//
//					  Remark = "";
//					  ProdPrice = 0;
//					  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1331Vnd()).longValue());
//					  Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getInF1332Usd()));
//					  Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1332Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//
//					  if ((vmaTransactionSlipDetails.getInF1331Vnd() > 0) && (vmaTransactionSlipDetails.getInF1332Usd() > 0)) {
//						  ProdPrice = 0;
//						  product.setRemark(vmaTransactionSlipDetails.getF1331Remarks());
//						  product.setRemark(product.getRemark() + "; " + vmaTransactionSlipDetails.getF1332Remarks());
//						  if (Validator.isNotNull(product.getRemark()) && (flagChangeSymbolReceipt == true)) {
//							  product.setRemark(StringUtil.replace(product.getRemark(), "*", "x"));
//
//						  }
//					  } else if (vmaTransactionSlipDetails.getInF1331Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1331Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1331Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//						  } else {
//							  product.setRemark(vmaTransactionSlipDetails.getF1331Remarks());
//						  }
//					  } else if (vmaTransactionSlipDetails.getInF1332Usd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1332Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1332Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//						  } else {
//							  product.setRemark(vmaTransactionSlipDetails.getF1332Remarks());
//						  }
//					  } else {
//						  product.setRemark("");
//					  }
//
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//					  }
//					// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//						if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//							product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//						  }
//						if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
//							// Trường hợp In quy đổi VNĐ từ USD,
//							product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setExtra1("0");
//
//							if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//								product.setAmount("0");
//							}
//						}
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("8");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  invoiceDetailDefined.setRemarks(product.getRemark());
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 9;			product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "WharfFee"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("WharfFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("3. Phí sử dụng cầu, bến, phao neo (Buoy/Wharf fee)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("9");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 10;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "OnShipWharfFee"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("OnShipWharfFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Đối với phương tiện (On ship)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("10");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 11;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "OnCargoWharfFee"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("OnCargoWharfFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Đối với hàng hóa (On cargo)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("11");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 12;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "OnPassengerWharfFee"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("OnPassengerWharfFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Đối với hành khách (On passenger)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("12");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//
//				  stt = 13;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "ClearanceFee";
//				  if (CacKhoanPhi.equalsIgnoreCase("ClearanceFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("4. Lệ phí ra, vào cảng biển (Clearance fee)");
//
//					  Remark = "";
//					  ProdPrice = 0;
//					  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlip.getF1361Vnd()).longValue());
//					  if (vmaTransactionSlip.getF1363Vnd() > 0) {
//						  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlip.getF1363Vnd()).longValue());
//					  }
//					  Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlip.getF1362Usd()));
//					  Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlip.getF1362Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//
//					  if ((vmaTransactionSlip.getF1361Vnd() > 0) && (vmaTransactionSlip.getF1362Usd() > 0)) {
//						  ProdPrice = 0;
//						  product.setRemark(vmaTransactionSlip.getF1361Remarks());
//						  product.setRemark(product.getRemark() + "; " + vmaTransactionSlip.getF1362Remarks());
//						  if (Validator.isNotNull(product.getRemark()) && (flagChangeSymbolReceipt == true)) {
//							  product.setRemark(StringUtil.replace(product.getRemark(), "*", "x"));
//						  }
//					  } else if (vmaTransactionSlip.getF1361Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1361Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1361Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//						  } else {
//							  product.setRemark(vmaTransactionSlip.getF1361Remarks());
//						  }
//					  } else if (vmaTransactionSlip.getF1362Usd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1362Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1362Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//						  } else {
//							  product.setRemark(vmaTransactionSlip.getF1362Remarks());
//						  }
//					  } else if (vmaTransactionSlip.getF1363Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1363Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1363Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//						  } else {
//							  product.setRemark(vmaTransactionSlip.getF1363Remarks());
//						  }
//					  } else {
//						  product.setRemark("");
//					  }
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//					  }
//					// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//						if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//							product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//						  }
//						if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
//							// Trường hợp In quy đổi VNĐ từ USD,
//							product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setExtra1("0");
//
//							if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//								product.setAmount("0");
//							}
//						}
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("13");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  invoiceDetailDefined.setRemarks(product.getRemark());
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//
//				  stt = 14;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "SeaProtestFee";
//				  if (CacKhoanPhi.equalsIgnoreCase("SeaProtestFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("5. Lệ phí chứng thực (Kháng nghị hàng hải) (Sea protest fee)");
//
//					  Remark = "";
//					  ProdPrice = 0;
//					  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlip.getF1371Vnd()).longValue());
//					  if (vmaTransactionSlip.getF1373Vnd() > 0) {
//						  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlip.getF1373Vnd()).longValue());
//					  }
//					  Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlip.getF1372Usd()));
//					  Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlip.getF1372Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//
//					  if ((vmaTransactionSlip.getF1371Vnd() > 0) && (vmaTransactionSlip.getF1372Usd() > 0)) {
//						  ProdPrice = 0;
//						  product.setRemark(vmaTransactionSlip.getF1371Remarks());
//						  product.setRemark(product.getRemark() + "; " + vmaTransactionSlip.getF1372Remarks());
//						  if (Validator.isNotNull(product.getRemark()) && (flagChangeSymbolReceipt == true)) {
//							  product.setRemark(StringUtil.replace(product.getRemark(), "*", "x"));
//						  }
//					  } else if (vmaTransactionSlip.getF1371Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1371Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1371Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//						  } else {
//							  product.setRemark(vmaTransactionSlip.getF1371Remarks());
//						  }
//					  } else if (vmaTransactionSlip.getF1372Usd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1372Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1372Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//						  } else {
//							  product.setRemark(vmaTransactionSlip.getF1372Remarks());
//						  }
//					  } else if (vmaTransactionSlip.getF1373Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1373Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1373Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//						  } else {
//							  product.setRemark(vmaTransactionSlip.getF1373Remarks());
//						  }
//					  } else {
//						  product.setRemark("");
//					  }
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//					  }
//					// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//						if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//							product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//						  }
//						if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
//							// Trường hợp In quy đổi VNĐ từ USD,
//							product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setExtra1("0");
//
//							if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//								product.setAmount("0");
//							}
//						}
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("14");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  invoiceDetailDefined.setRemarks(product.getRemark());
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 14;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "SeaProtestFee1"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("SeaProtestFee1")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("(Sea protest fee)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("14");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 15;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "NavigationFee"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("NavigationFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("II. Phí bảo đảm hàng hải (Aids to navigation fee)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("15");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 16;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "InwardNavigationFee";
//				  if (CacKhoanPhi.equalsIgnoreCase("InwardNavigationFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Lượt vào (Inward)");
//
//					  Remark = "";
//					  ProdPrice = 0;
//					  Total_ThanhTienVietNamDong = vmaTransactionSlipDetails.getInF1351Vnd().longValue();
//					  Extra1_ThanhTienDongUSD = vmaTransactionSlipDetails.getInF1352Usd();
//					  Extra1_ThanhTienDongUSDQuyDoi = vmaTransactionSlipDetails.getInF1352Usd().longValue() * vmaTransactionSlip.getExchangeRate().longValue();
//
//					  if (vmaTransactionSlipDetails.getInF1351Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1351Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1351Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//							} else {
//								product.setRemark(vmaTransactionSlipDetails.getF1351Remarks());
//							}
//					  } else if (vmaTransactionSlipDetails.getInF1352Usd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1352Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1352Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//							} else {
//								product.setRemark(vmaTransactionSlipDetails.getF1352Remarks());
//							}
//					  } else {
//						  product.setRemark("");
//					  }
//
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//					  }
//					// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//						if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//							product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//						  }
//						if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
//							// Trường hợp In quy đổi VNĐ từ USD,
//							product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setExtra1("0");
//
//							if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//								product.setAmount("0");
//							}
//						}
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("16");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  invoiceDetailDefined.setRemarks(product.getRemark());
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 17;			product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "OutwardNavigationFee";
//				  if (CacKhoanPhi.equalsIgnoreCase("OutwardNavigationFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Lượt rời (Outward)");
//
//					  Remark = "";
//					  ProdPrice = 0;
//					  Total_ThanhTienVietNamDong = vmaTransactionSlipDetails.getOutF1351Vnd().longValue();
//					  Extra1_ThanhTienDongUSD = vmaTransactionSlipDetails.getOutF1352Usd();
//					  Extra1_ThanhTienDongUSDQuyDoi = vmaTransactionSlipDetails.getOutF1352Usd().longValue() * vmaTransactionSlip.getExchangeRate().longValue();
//
//					  if (vmaTransactionSlipDetails.getOutF1351Vnd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1351Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1351Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//							} else {
//								product.setRemark(vmaTransactionSlipDetails.getF1351Remarks());
//							}
//					  } else if (vmaTransactionSlipDetails.getOutF1352Usd() > 0) {
//						  ProdPrice = 0;
//						  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1352Remarks()) && (flagChangeSymbolReceipt == true)) {
//								String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1352Remarks(), "*", "x");
//								product.setRemark(specRemarks);
//							} else {
//								product.setRemark(vmaTransactionSlipDetails.getF1352Remarks());
//							}
//					  } else {
//						  product.setRemark("");
//					  }
//
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//					  }
//					// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//						if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//							product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//						  }
//						if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
//							// Trường hợp In quy đổi VNĐ từ USD,
//							product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setExtra1("0");
//
//							if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//								product.setAmount("0");
//							}
//						}
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("17");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  invoiceDetailDefined.setRemarks(product.getRemark());
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 18;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "PilotFee"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("PilotFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("III. Phí hoa tiêu (Pilot fee)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("18");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 19;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "PilotFeeFromTo1"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("PilotFeeFromTo1")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("   Từ (from) ……………. đến (to) ……………….");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("19");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 20;		product = new Invoices.Inv.Invoice.Products.Product();
//				  // CacKhoanPhi = "PilotFeeFromTo2"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("PilotFeeFromTo2")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("   Từ (from) ……………. đến (to) ……………….");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("20");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 21;		product = new Invoices.Inv.Invoice.Products.Product();
//				  // CacKhoanPhi = "AddingFee"; // fixed
//				  if (CacKhoanPhi.equalsIgnoreCase("AddingFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("Phí bổ sung (Adding fee)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("21");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 22;		product = new Invoices.Inv.Invoice.Products.Product();
//				  // CacKhoanPhi = "ClearanceFee1"; // fixed CVHH Quang Tri
//				  if (CacKhoanPhi.equalsIgnoreCase("ClearanceFee1")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("III. Lệ phí ra, vào cảng biển (Clearance fee))");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("22");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//
//				  stt = 23;		product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "InwardClearanceFee";
//				  if (CacKhoanPhi.equalsIgnoreCase("InwardClearanceFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Lượt vào (Inward)");
//
//					  Remark = "";
//					  ProdPrice = 0;
//					  Total_ThanhTienVietNamDong = vmaTransactionSlipDetails.getInF1361Vnd().longValue();
//					  if (vmaTransactionSlipDetails.getInF1363Vnd() > 0) {
//						  Total_ThanhTienVietNamDong = vmaTransactionSlipDetails.getInF1363Vnd().longValue();
//						  if ((vmaTransactionSlipDetails.getOutF1363Vnd() == 0) && (vmaTransactionSlip.getPaymentCategory() == 1)) {
//							  // Chia đôi phí
//							  Total_ThanhTienVietNamDong = vmaTransactionSlipDetails.getInF1363Vnd().longValue() / 2;
//						  }
//					  }
//					  Extra1_ThanhTienDongUSD = vmaTransactionSlipDetails.getInF1362Usd();
//					  Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1362Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//
//					  product.setRemark("");
//					  if ((vmaTransactionSlipDetails.getInF1362Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
//						  product.setRemark("$"+vmaTransactionSlipDetails.getInF1362Usd());
//					  }
//					  /*if (vmaTransactionSlipDetails.getInF1361Vnd() > 0) {
//						  ProdPrice = 0;
//						  product.setRemark(vmaTransactionSlipDetails.getF1361Remarks());
//					  } else if (vmaTransactionSlipDetails.getInF1362Usd() > 0) {
//						  ProdPrice = 0;
//						  product.setRemark(vmaTransactionSlipDetails.getF1362Remarks());
//					  } else if (vmaTransactionSlipDetails.getInF1363Vnd() > 0) {
//						  ProdPrice = 0;
//						  product.setRemark(vmaTransactionSlipDetails.getF1363Remarks());
//					  } else {
//						  product.setRemark("");
//					  }*/
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//					  }
//					// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//						if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//							product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//						  }
//						if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
//							// Trường hợp In quy đổi VNĐ từ USD,
//							product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setExtra1("0");
//
//							if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//								product.setAmount("0");
//							}
//						}
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("23");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  invoiceDetailDefined.setRemarks(product.getRemark());
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 24;			product = new Invoices.Inv.Invoice.Products.Product();
//				  //CacKhoanPhi = "OutwardClearanceFee";
//				  if (CacKhoanPhi.equalsIgnoreCase("OutwardClearanceFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Lượt rời (Outward)");
//
//					  Remark = "";
//					  ProdPrice = 0;
//					  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getOutF1361Vnd()).longValue());
//					  if (vmaTransactionSlipDetails.getOutF1363Vnd() > 0) {
//						  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getOutF1363Vnd()).longValue());
//					  } else {
//						  if ((vmaTransactionSlipDetails.getInF1363Vnd() > 0) && (vmaTransactionSlip.getPaymentCategory() == 1)) {
//							  // Chia đôi phí
//							  Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1363Vnd() / 2).longValue());
//						  }
//					  }
//					  Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getOutF1362Usd()));
//					  Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getOutF1362Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//
//					  product.setRemark("");
//					  if ((vmaTransactionSlipDetails.getOutF1362Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
//						  product.setRemark("$"+vmaTransactionSlipDetails.getOutF1362Usd());
//					  }
//					  /*if (vmaTransactionSlipDetails.getOutF1361Vnd() > 0) {
//						  ProdPrice = 0;
//						  product.setRemark(vmaTransactionSlipDetails.getF1361Remarks());
//					  } else if (vmaTransactionSlipDetails.getOutF1362Usd() > 0) {
//						  ProdPrice = 0;
//						  product.setRemark(vmaTransactionSlipDetails.getF1362Remarks());
//					  } else if (vmaTransactionSlipDetails.getOutF1363Vnd() > 0) {
//						  ProdPrice = 0;
//						  product.setRemark(vmaTransactionSlipDetails.getF1363Remarks());
//					  } else {
//						  product.setRemark("");
//					  }*/
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(ProdPrice));
//					  product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
//					  product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//					  if ((Extra1_ThanhTienDongUSD > 0) &&
//							  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
//						  product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//						  product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//					  }
//					// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//					if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//						product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
//						product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  }
//					  products.getProduct().add(product);
//					  if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
//							// Trường hợp In quy đổi VNĐ từ USD,
//							product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
//							product.setExtra1("0");
//
//							if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
//								product.setAmount("0");
//							}
//						}
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("24");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
//					  invoiceDetailDefined.setRemarks(product.getRemark());
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 25;		product = new Invoices.Inv.Invoice.Products.Product();
//				  // CacKhoanPhi = "SecurityFee"; // fixed  CVHH Quang Tri
//				  if (CacKhoanPhi.equalsIgnoreCase("SecurityFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("IV. Phí thẩm định, phê duyệt đánh giá an ninh cảng biển)");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("25");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//				  stt = 26;		product = new Invoices.Inv.Invoice.Products.Product();
//				  // CacKhoanPhi = "PeriodSecurityFee"; // fixed CVHH Quang Tri
//				  if (CacKhoanPhi.equalsIgnoreCase("PeriodSecurityFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Lần đầu hoặc định kỳ 5 năm");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("26");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//				  stt = 27;		product = new Invoices.Inv.Invoice.Products.Product();
//				  // CacKhoanPhi = "AddingSecurityFee"; // fixed CVHH Quang Tri
//				  if (CacKhoanPhi.equalsIgnoreCase("AddingSecurityFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("  - Bổ sung");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("27");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//				  stt = 28;		product = new Invoices.Inv.Invoice.Products.Product();
//				  // CacKhoanPhi = "CrewBookFee"; // fixed  CVHH Quang Binh, Thai Binh
//				  if (CacKhoanPhi.equalsIgnoreCase("CrewBookFee")){
//					  product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  //product.setProdName("IV. Lệ phí đăng ký và cấp sổ thuyền viên");
//					  product.setCode(stt+"");
//					  product.setProdUnit("Lần");
//					  product.setProdQuantity(String.valueOf(0));
//					  product.setProdPrice(String.valueOf(0));
//					  product.setTotal(String.valueOf(0));
//					  product.setExtra1(String.valueOf(0));
//					  product.setAmount("0");
//					  product.setVATRate("0");
//					  product.setVATAmount("0");
//					  product.setRemark("");
//					  products.getProduct().add(product);
//
//					// BKAV
//					  invoiceDetail = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS();
//					  invoiceDetail.setItemCode("28");
//					  invoiceDetail.setItemName(objVmaTransactionReceipt.getTransactionFunctionNote());
//					  invoiceDetail.setUnitName("Lần");
//					  invoiceDetail.setQty("0");
//					  invoiceDetail.setPrice("0");
//					  invoiceDetail.setAmount("0");
//					  invoiceDetail.setTaxRateID("0");
//					  invoiceDetail.setTaxRate("0");
//					  invoiceDetail.setTaxAmount("0");
//					  invoiceDetail.setIsDiscount("false");
//					  invoiceDetailDefined = new ArrayOfInvoiceDataWS.InvoiceDataWS.ListInvoiceDetailsWS.InvoiceDetailsWS.UserDefineDetails();
//					  invoiceDetailDefined.setAmount("0");
//					  invoiceDetailDefined.setRemarks("");
//					  invoiceDetailDefined.setFromDate("");
//					  invoiceDetailDefined.setToDate("");
//					  invoiceDetail.setUserDefineDetails(invoiceDetailDefined);
//					  invoiceDataListDetail.getInvoiceDetailsWS().add(invoiceDetail);
//				  }
//
//			}
//			  invoiceDetails.setProducts(products);
//
//			  invoiceDetails.setTotal("0");
//			  invoiceDetails.setAmount("0");
//			  invoiceDetails.setVATRate("0");
//			  invoiceDetails.setVATAmount("0");
//
//			  int VND = 1, USD = 2;
//			  String inWordUSD = DanhMucUtils.convert(
//						vmaTransactionSlip.getUsdTotalAmount(), USD) + " ./.";
//
//			  String inWordVND = DanhMucUtils.convert(
//						vmaTransactionSlip.getVndTotalAmount(), VND) + " ./.";
//
//			  invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
//			  invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));
//
//			  invoiceDetails.setExtra4(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));
//				// <!-- Số tiền bằng số của đồng Đôla Mỹ USD  -->
//
//			  invoiceDetails.setExtra5(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
//				// <!-- Số tiền bằng số Việt nam đồng-->
//
//			  if (vmaTransactionSlip.getUsdTotalAmount() > 0 ) {
//				  invoiceDetails.setExtra6(inWordUSD);
//					// <!-- Tổng tiền (bằng chữ) của đồng Đôla Mỹ USD  -->
//			  } else {
//				  invoiceDetails.setExtra6("///");
//			  }
//
//			if (vmaTransactionSlip.getVndTotalAmount() > 0 ) {
//				invoiceDetails.setAmountInWords(inWordVND);
//				// <!-- Tổng tiền (bằng chữ) của Việt nam đồng  -->
//			} else {
//				invoiceDetails.setAmountInWords("///");
//			}
//
//
//			// Sửa lỗi tách biệt nghiệp vụ CVHH Thua Thien Hue
//			if ((vmaTransactionSlip.getVndTotalAmount() > 0) &&
//					  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//				  invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
//				  invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
//				  invoiceDetails.setAmountInWords(inWordVND);
//			  }
//
//
//			if ((vmaTransactionSlip.getUsdTotalAmount() > 0) &&
//					  (maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
//				  invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));
//				  invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));
//				  invoiceDetails.setAmountInWords(inWordUSD);
//			  }
//
//			if ((vmaTransactionSlip.getUsdTotalAmount() > 0) &&
//					  (maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh
//				invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
//				invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
//				inWordVND = DanhMucUtils.convert(
//						vmaTransactionSlip.getPaymentAmount(), VND) + " ./.";
//				invoiceDetails.setAmountInWords(inWordVND);
//			}
//
//			if (vmaTransactionSlip.getUsdTotalAmount() > 0){
//				invoiceDetails.setExchangeRate(String.valueOf(vmaTransactionSlip.getExchangeRate()));
//				invoiceDetails.setCurrencyUnit("USD");
//
//				if ((vmaTransactionSlip.getHideExchangeRate() == 1) && (vmaTransactionSlip.getPrintedConvertVND() == 0)) {
//					invoiceDetails.setExchangeRate("0");
//				}
//			} else {
//				invoiceDetails.setExchangeRate("0");
//				invoiceDetails.setCurrencyUnit("VND");
//			}
//
//			// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
//			if ((vmaTransactionSlip.getVndTotalAmount() > 0 || vmaTransactionSlip.getUsdTotalAmount() > 0) &&
//					  (maritimeCode.equalsIgnoreCase("19"))){ // Duy nhat CVHH Hai Phong
//				  invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
//				  invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));
//				  invoiceDetails.setAmountInWords(inWordVND);
//				  invoiceDetails.setExtra1(inWordUSD);
//			  }
//			if ((vmaTransactionSlip.getVndTotalAmount() > 0) &&
//					  (maritimeCode.equalsIgnoreCase("19"))){
//
//				invoiceDetails.setCurrencyUnit("VND");
//				if ((vmaTransactionSlip.getUsdTotalAmount() > 0) &&
//						  (maritimeCode.equalsIgnoreCase("19"))){
//					invoiceDetails.setCurrencyUnit("VND_USD");
//				}
//			} else if ((vmaTransactionSlip.getUsdTotalAmount() > 0) &&
//					  (maritimeCode.equalsIgnoreCase("19"))){
//				invoiceDetails.setCurrencyUnit("USD");
//			}
//
//			if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (vmaTransactionSlip.getUsdTotalAmount() > 0) ){
//				// Trường hợp In quy đổi VNĐ từ USD, Cập nhật lại
//				invoiceDetails.setTotal("0");
//				invoiceDetails.setAmount("0");
//
//				invoiceDetails.setExchangeRate(String.valueOf(vmaTransactionSlip.getExchangeRate()));
//				invoiceDetails.setCurrencyUnit("VND");
//
//				inWordVND = DanhMucUtils.convert(vmaTransactionSlip.getPaymentAmount(), VND) + " ./.";
//				invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
//				invoiceDetails.setAmountInWords(inWordVND);
//				invoiceDetails.setExtra1("///");
//				invoiceDetails.setExtra4("0");
//				invoiceDetails.setExtra5(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
//				invoiceDetails.setExtra6("///");
//
//				if ((maritimeCode.equalsIgnoreCase("22")) || (maritimeCode.equalsIgnoreCase("7"))){
//					// CVHH Thua Thien Hue, CVHH Thai Binh
//					invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
//					invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
//				}
//
//			}
//
//			invoiceDetails.setConvertedAmount(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
//			invoiceDetails.setReferenceNo(Validator.isNotNull(systemAccountant) ? systemAccountant : "system");
//
//			inv.getInvoice().add(invoiceDetails);
//
//			invoices.getInv().add(inv);
//			log.info("---------Mau1_invoices--------DebitNoteNumber:" + inv.getKey());
//
//
//			// Neu xuat hoa don BKAV
//
//			invoiceBkavUserDefine.setIsShowInwordsEN("true");
//
//			if (vmaTransactionSlip.getVndTotalAmount() > 0 ) {
//				invoiceBkavUserDefine.setVNDInwords(inWordVND);
//				// <!-- Tổng tiền (bằng chữ) của Việt nam đồng  -->
//			} else {
//				invoiceBkavUserDefine.setVNDInwords("///");
//			}
//
//			invoiceBkavUserDefine.setVNDSumPayment(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
//			// <!-- Số tiền bằng số Việt nam đồng-->
//
//			if (vmaTransactionSlip.getUsdTotalAmount() > 0 ) {
//				invoiceBkavUserDefine.setUSDInwords(inWordUSD);
//					// <!-- Tổng tiền (bằng chữ) của đồng Đôla Mỹ USD  -->
//			  } else {
//				  invoiceBkavUserDefine.setUSDInwords("///");
//			  }
//
//			invoiceBkavUserDefine.setUSDSumPayment(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));
//			// <!-- Số tiền bằng số của đồng Đôla Mỹ USD  -->
//			invoiceBkav.setUserDefine(invoiceBkavUserDefine);
//
//			invoiceDataws.setInvoice(invoiceBkav);
//			invoiceDataws.setPartnerInvoiceID(vmaTransactionSlip.getDocumentaryCode().toString());
//			invoiceDataws.setPartnerInvoiceStringID(vmaTransactionSlip.getDocumentaryCode().toString());
//			invoiceDataws.setListInvoiceAttachFileWS(invoiceDataListAttach);
//			invoiceDataws.setListInvoiceDetailsWS(invoiceDataListDetail);
//			invoiceArray.getInvoiceDataWS().add(invoiceDataws);
//
//			CommandData commandData = new CommandData();
//			commandData.setCmdType(CommandTypes.CreateInvoiceMT+"");
//
//			commandData.setCommandObject(MessageFactory.convertObjectToXml(invoiceArray));
//
//
//
//			exportCustomers = customers;
//			exportInvoices = invoices;
//			exportBKAVInvoices = commandData;
//
//			VmaVnptServiceConfig serviceConf = VmaVnptServiceConfigLocalServiceUtil.findByTestMode(1);
//			if (Validator.isNotNull(serviceConf))  {
//
//				if ((Validator.isNotNull(serviceConf) && serviceConf.getTestMode() == 1) &&
//						(serviceConf.getMaritimeCode().equalsIgnoreCase("25") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("15") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("5") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("6") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("7") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("2") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("3") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("11") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("19") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("16") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("16TV") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("16HG") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("16ST") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("20") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("22") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("23") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("24") )){
//					String	xml2 = MessageFactory.convertObjectToXml(invoices);
//					vmaTransactionSlip.setF1302Remarks(xml2);
//					VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//				}
//
//			}
//
//			if (maritimeCode.equalsIgnoreCase("16") && (testMode == 10)) {
//				// CVHH CAN THO chi nhanh TRA VINH (Duyen hai Tra Vinh)
//				xuatBienLaiDienTuVNPT_NewNode(itineraryNo, documentaryCode, userId);
//
//			}  else if (maritimeCode.equalsIgnoreCase("16") && (testMode == 11)) {
//				// CVHH CAN THO chi nhanh HAU GIANG
//				xuatBienLaiDienTuVNPT_NewNodeHG(itineraryNo, documentaryCode, userId);
//
//			} else if (maritimeCode.equalsIgnoreCase("4") || maritimeCode.equalsIgnoreCase("24")
//					|| maritimeCode.equalsIgnoreCase("2")
//					|| maritimeCode.equalsIgnoreCase("3")
//					|| maritimeCode.equalsIgnoreCase("5")
//					|| maritimeCode.equalsIgnoreCase("6")
//					|| maritimeCode.equalsIgnoreCase("7")
//					|| maritimeCode.equalsIgnoreCase("11")
//					|| maritimeCode.equalsIgnoreCase("16")
//					|| maritimeCode.equalsIgnoreCase("20")
//					|| maritimeCode.equalsIgnoreCase("22")
//					|| maritimeCode.equalsIgnoreCase("23")
//					|| maritimeCode.equalsIgnoreCase("19")
//					) {
//				// CVHH Ha Tinh, Binh Thuan, Thanh Hoa, Nghệ An, Quảng Bình, Quảng Trị, Huế, Đồng Nai, Cần Thơ, Quảng Ninh
//				// Thái Bình, Kiên Giang, Hải Phòng
//				// chi xuat bien lai dien tu sang he thong VNPT, khong phat hanh truc tiep
//				xuatBienLaiDienTuVNPT(itineraryNo, documentaryCode, userId);
//			} else if (maritimeCode.equalsIgnoreCase("8") || maritimeCode.equalsIgnoreCase("9")
//					|| maritimeCode.equalsIgnoreCase("18") || maritimeCode.equalsIgnoreCase("25")) {
//				// CVHH Quảng Ngãi, Quy Nhơn, Đà Nẵng, Quảng Nam
//				if ((Validator.isNotNull(serviceConf) && serviceConf.getTestMode() == 1) &&
//						(serviceConf.getMaritimeCode().equalsIgnoreCase("8") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("9") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("18") ||
//								serviceConf.getMaritimeCode().equalsIgnoreCase("25"))){
//
//
//					String	xml3 = MessageFactory.convertObjectToXml(commandData);
//					vmaTransactionSlip.setF1302Remarks(xml3);
//					VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//				}
//				// chi xuat bien lai dien tu sang he thong BKAV
//				xuatBienLaiDienTuBKAV(itineraryNo, documentaryCode, userId);
//			} else {
//
//				// Phat hanh truc tiep bien lai dien tu
//				thanhtoanBienLaiDienTuVNPT(itineraryNo, documentaryCode, userId);
//			}
//
//			// Lưu lại email xuất biên lai điện tử theo danh mục Đại lý, Chủ tàu
//			/*Chọn đơn vị thanh toán
//			1. Đại lý (mặc định)
//			2. Chủ tàu;
//			3. Chủ hàng
//			4. Đơn vị khai thác
//			5. Cá nhân, khác*/
//
//			String emailrecipients = vmaTransactionSlip.getEmailRecipients();
//
//			if (Validator.isNotNull(emailrecipients)) {
//				if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("3")
//						|| vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("2")
//						|| vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("4")) {
//
//
//					  try {
//						  VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil.fetchByitineraryNo(vmaTransactionSlip.getItineraryNo());
//
//						  if (Validator.isNotNull(vmaItinerary.getShipOwnerCode())) {
//							  DmVmaShipOwner dmVmaShipOwner = DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaItinerary.getShipOwnerCode());
//							  if (Validator.isNotNull(dmVmaShipOwner) && Validator.isNotNull(dmVmaShipOwner.getCompanyName())) {
//								  dmVmaShipOwner.setEmailRecipients(emailrecipients);
//								  DmVmaShipOwnerLocalServiceUtil.updateDmVmaShipOwner(dmVmaShipOwner);
//							  }
//						  }
//						} catch (Exception e) {
//
//						}
//
//				  } else if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("1")){
//					  try {
//						  if (Validator.isNull(vmaTransactionSlip.getShipAgencyCode())) {
//							  // do nothing
//						  } else {
//							  DmShipAgency dmShipAgency = DmShipAgencyLocalServiceUtil.getByShipAgencyCode(
//										vmaTransactionSlip.getShipAgencyCode());
//							  if (Validator.isNotNull(dmShipAgency) && Validator.isNotNull(dmShipAgency.getShipAgencyName())) {
//								  dmShipAgency.setEmailRecipients(emailrecipients);
//								  DmShipAgencyLocalServiceUtil.updateDmShipAgency(dmShipAgency);
//							  }
//						  }
//					  } catch (Exception e) {
//
//						}
//				  } else {
//					  // do nothing
//				  }
//			}
//
//			} catch (Exception e) {
//				log.error(e.getMessage());
//				exportCustomers = null;
//				exportInvoices = null;
//				exportBKAVInvoices = null;
//			}
//		}
//
//	  private static boolean  isCustomDoanhNghiep(String CustomerName,String taxCode){
//			if(CustomerName == null ||CustomerName.trim().length()<=0){
//				return false;
//			}
//
//			String sLoaiHinhDoanhNghiep = "CÔNG TY";// DKConfigurationManager.getStrProp("cong.ty","cong.ty");
//
//
//			CustomerName = CustomerName.toUpperCase();
//
//			if((taxCode != null && taxCode.trim().length()==10) || (taxCode != null && taxCode.trim().length()==13)) {
//				return true;
//			}
//
//			if (CustomerName.contains(sLoaiHinhDoanhNghiep.toUpperCase())){
//				return true;
//			}
//
//			if(CustomerName.indexOf("TNHH") != -1
//					|| CustomerName.indexOf("CTY") != -1){
//				return true;
//			}
//			if(CustomerName.indexOf("LTD") != -1
//					|| CustomerName.indexOf("CTY") != -1){
//				return true;
//			}
//
//			return false;
//		}

//	  public static boolean thanhtoanBienLaiDienTuVNPT(String itineraryNo, String documentaryCode, long userId)
//			  throws com.fds.nsw.kernel.exception.SystemException {
//		  try {
//
//			  VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
//						.fetchByitineraryNo_documentaryCode(itineraryNo,
//								documentaryCode);
//
//		  log.info("---------Export invoices/customers--------VNPT: BienLaiDienTu");
//		    if (vmaTransactionSlip.getReportdate() != null) {
//				log.info("---------Da cap HDDT; Bo qua Export invoices/customers--------VNPT:");
//
//			} else if ((exportCustomers != null ) && (exportInvoices != null))
//			{
//				TempDebitnote debitNote = new TempDebitnote();
//				debitNote = TempDebitNoteLocalServiceUtil.fetchTempDebitNote(vmaTransactionSlip.getDebitnoteid());
//				int test1 = vn.gt.portlet.phuongtien.CallWebserviceClient.updateCus(exportCustomers);
//				log.info("test1 -- Exported Customers -- " +test1);
//
//				String test2 = vn.gt.portlet.phuongtien.CallWebserviceClient.importAndPublishInv(exportInvoices);
//				log.info("test2 -- Published Invoices -- " +test2);
//				if (test2.contains("OK")){
//
//					debitNote.setReportby(test2 + ";" + new Date()); // Ngay xac nhan cap moi hoa don dien tu
//					TempDebitNoteLocalServiceUtil.updateTempDebitNote(debitNote);
//
//					vmaTransactionSlip.setStampStatus(2);
//					vmaTransactionSlip.setReportdate(new Date());
//					vmaTransactionSlip.setReportby(test2);
//					VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//					try {
//						String bldt = (test2.contains("_") ? test2.split("_")[0] : test2);
//						String eReceiptNo = (bldt.contains("-") ? bldt.substring(bldt.lastIndexOf("-")+1) : bldt);
//						vmaTransactionSlip.setEReceiptNo(eReceiptNo);
//						vmaTransactionSlip.setEReceiptDate(new Date());
//
//						VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//					} catch (Exception e) {
//						log.error(e.getMessage());
//				  }
//				}
//			}
//			else
//			{
//				log.info("---------Export invoices"  + "---------Export customers--------VNPT: NULL");
//			}
//		  } catch (Exception e) {
//				log.error(e.getMessage());
//		  }
//			  return true;
//		  }
//
//	  public static boolean xuatBienLaiDienTuVNPT(String itineraryNo, String documentaryCode, long userId)
//			  throws com.fds.nsw.kernel.exception.SystemException {
//		  try {
//
//			  VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
//						.fetchByitineraryNo_documentaryCode(itineraryNo,
//								documentaryCode);
//
//		  log.info("---------Export invoices/customers--------VNPT: BienLaiDienTu");
//		    if (vmaTransactionSlip.getReportdate() != null) {
//				log.info("---------Da cap HDDT; Bo qua Export invoices/customers--------VNPT:");
//
//			} else if ((exportCustomers != null ) && (exportInvoices != null))
//			{
//				TempDebitnote debitNote = new TempDebitnote();
//				debitNote = TempDebitNoteLocalServiceUtil.fetchTempDebitNote(vmaTransactionSlip.getDebitnoteid());
//				int test1 = vn.gt.portlet.phuongtien.CallWebserviceClient.updateCus(exportCustomers);
//				log.info("test1 -- Exported Customers -- " +test1);
//
//				String test2 = vn.gt.portlet.phuongtien.CallWebserviceClient.importInv(exportInvoices);
//				log.info("testImportInv -- Imported Invoices -- " +test2);
//				if (test2.contains("OK")){
//
//					debitNote.setReportby(test2 + ";" + new Date()); // Ngay xac nhan cap moi hoa don dien tu
//					TempDebitNoteLocalServiceUtil.updateTempDebitNote(debitNote);
//
//					vmaTransactionSlip.setStampStatus(2);
//					vmaTransactionSlip.setReportdate(new Date());
//					vmaTransactionSlip.setReportby(test2);
//					VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//					try {
//						String bldt = (test2.contains("_") ? test2.split("_")[0] : test2);
//						String eReceiptNo = (bldt.contains("-") ? bldt.substring(bldt.lastIndexOf("-")+1) : bldt);
//						vmaTransactionSlip.setEReceiptNo(eReceiptNo);
//						vmaTransactionSlip.setEReceiptDate(new Date());
//
//						VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//					} catch (Exception e) {
//						log.error(e.getMessage());
//				  }
//				}
//			}
//			else
//			{
//				log.info("---------Export invoices"  + "---------Export customers--------VNPT: NULL");
//			}
//		  } catch (Exception e) {
//				log.error(e.getMessage());
//		  }
//			  return true;
//		  }

//	  public static boolean xuatBienLaiDienTuBKAV(String itineraryNo, String documentaryCode, long userId)
//				  throws com.fds.nsw.kernel.exception.SystemException {
//			  try {
//				  VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
//							.fetchByitineraryNo_documentaryCode(itineraryNo,
//									documentaryCode);
//
//			  log.info("---------Export invoices/customers--------BKAV: BienLaiDienTu");
//			    if (vmaTransactionSlip.getReportdate() != null) {
//					log.info("---------Da cap HDDT; Bo qua Export invoices/customers--------BKAV:");
//
//				} else if (exportBKAVInvoices != null)
//				{
//					TempDebitnote debitNote = new TempDebitnote();
//					debitNote = TempDebitNoteLocalServiceUtil.fetchTempDebitNote(vmaTransactionSlip.getDebitnoteid());
//
//
//
//					String decode = vn.gt.portlet.phuongtien.CallWebserviceClient.pushInvoice(exportBKAVInvoices);
//					try {
//						String xmContent = AES.DecodingBase64(decode);
//
//						Result result = MessageFactory.convertXmltoXacNhanKetQuaXuatHoaDon(xmContent);
//						String test3= "";
//						if (Validator.isNotNull(result)) {
//							test3 = result.getStatus() + " -- " + result.getObject();
//							log.info("testPushInvoice -- Push Invoices -- " + test3);
//							test3 = result.getStatus();
//						}
//
//						log.info("testPushInvoice -- Push Invoices -- " + test3);
//						int SUCCESSFUL = 0;
//						if (test3.contains(SUCCESSFUL+"")){
//
//							debitNote.setReportby(test3 + ";" + new Date()); // Ngay xac nhan cap moi hoa don dien tu
//							TempDebitNoteLocalServiceUtil.updateTempDebitNote(debitNote);
//
//							vmaTransactionSlip.setStampStatus(2);
//							vmaTransactionSlip.setReportdate(new Date());
//							vmaTransactionSlip.setReportby("BKAV");
//							VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//						}
//					} catch (Exception ex) {
//						//return ex.toString();
//					}
//				}
//				else
//				{
//					log.info("---------Push invoices --------BKAV: NULL");
//				}
//			  } catch (Exception e) {
//					log.error(e.getMessage());
//			  }
//				  return true;
//			  }

	  
//	  public static boolean xuatBienLaiDienTuVNPT_NewNode(String itineraryNo, String documentaryCode, long userId)
//			  throws com.fds.nsw.kernel.exception.SystemException {
//		  try {
//
//			  VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
//						.fetchByitineraryNo_documentaryCode(itineraryNo,
//								documentaryCode);
//
//		  log.info("---------Export invoices/customers--------VNPT: BienLaiDienTu");
//		    if (vmaTransactionSlip.getReportdate() != null) {
//				log.info("---------Da cap HDDT; Bo qua Export invoices/customers--------VNPT:");
//
//			} else if ((exportCustomers != null ) && (exportInvoices != null))
//			{
//				TempDebitnote debitNote = new TempDebitnote();
//				debitNote = TempDebitNoteLocalServiceUtil.fetchTempDebitNote(vmaTransactionSlip.getDebitnoteid());
//				int test1 = vn.gt.portlet.phuongtien.CallWebserviceClient.updateCusNewNode(exportCustomers);
//				log.info("test1 -- Exported Customers -- " +test1);
//
//				String test2 = vn.gt.portlet.phuongtien.CallWebserviceClient.importInvNewNode(exportInvoices);
//				log.info("testImportInv -- Imported Invoices -- " +test2);
//				if (test2.contains("OK")){
//
//					debitNote.setReportby(test2 + ";" + new Date()); // Ngay xac nhan cap moi hoa don dien tu
//					TempDebitNoteLocalServiceUtil.updateTempDebitNote(debitNote);
//
//					vmaTransactionSlip.setStampStatus(2);
//					vmaTransactionSlip.setReportdate(new Date());
//					vmaTransactionSlip.setReportby(test2);
//					VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//					try {
//						String bldt = (test2.contains("_") ? test2.split("_")[0] : test2);
//						String eReceiptNo = (bldt.contains("-") ? bldt.substring(bldt.lastIndexOf("-")+1) : bldt);
//						vmaTransactionSlip.setEReceiptNo(eReceiptNo);
//						vmaTransactionSlip.setEReceiptDate(new Date());
//
//						VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//					} catch (Exception e) {
//						log.error(e.getMessage());
//				  }
//				}
//			}
//			else
//			{
//				log.info("---------Export invoices"  + "---------Export customers--------VNPT: NULL");
//			}
//		  } catch (Exception e) {
//				log.error(e.getMessage());
//		  }
//			  return true;
//		}
//
//
//	  public static boolean xuatBienLaiDienTuVNPT_NewNodeHG(String itineraryNo, String documentaryCode, long userId)
//			  throws com.fds.nsw.kernel.exception.SystemException {
//		  try {
//
//			  VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
//						.fetchByitineraryNo_documentaryCode(itineraryNo,
//								documentaryCode);
//
//		  log.info("---------Export invoices/customers--------VNPT: BienLaiDienTu");
//		    if (vmaTransactionSlip.getReportdate() != null) {
//				log.info("---------Da cap HDDT; Bo qua Export invoices/customers--------VNPT:");
//
//			} else if ((exportCustomers != null ) && (exportInvoices != null))
//			{
//				TempDebitnote debitNote = new TempDebitnote();
//				debitNote = TempDebitNoteLocalServiceUtil.fetchTempDebitNote(vmaTransactionSlip.getDebitnoteid());
//				int test1 = vn.gt.portlet.phuongtien.CallWebserviceClient.updateCusNewNodeHG(exportCustomers);
//				log.info("test1 -- Exported Customers -- " +test1);
//
//				String test2 = vn.gt.portlet.phuongtien.CallWebserviceClient.importInvNewNodeHG(exportInvoices);
//				log.info("testImportInv -- Imported Invoices -- " +test2);
//				if (test2.contains("OK")){
//
//					debitNote.setReportby(test2 + ";" + new Date()); // Ngay xac nhan cap moi hoa don dien tu
//					TempDebitNoteLocalServiceUtil.updateTempDebitNote(debitNote);
//
//					vmaTransactionSlip.setStampStatus(2);
//					vmaTransactionSlip.setReportdate(new Date());
//					vmaTransactionSlip.setReportby(test2);
//					VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//					try {
//						String bldt = (test2.contains("_") ? test2.split("_")[0] : test2);
//						String eReceiptNo = (bldt.contains("-") ? bldt.substring(bldt.lastIndexOf("-")+1) : bldt);
//						vmaTransactionSlip.setEReceiptNo(eReceiptNo);
//						vmaTransactionSlip.setEReceiptDate(new Date());
//
//						VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);
//					} catch (Exception e) {
//						log.error(e.getMessage());
//				  }
//				}
//			}
//			else
//			{
//				log.info("---------Export invoices"  + "---------Export customers--------VNPT: NULL");
//			}
//		  } catch (Exception e) {
//				log.error(e.getMessage());
//		  }
//			  return true;
//		}
//
//	  public static String pushInvoice(CommandData invoices)
//				throws IOException, RemoteException {
//			CallWebserviceClient callWebserviceClient = new CallWebserviceClient();
//			WSPublicEHoaDonSoap publishServiceSoap = callWebserviceClient.getWSPublicEHoaDonSoap();
//			  String	xml2 = MessageFactory.convertObjectToXml(invoices);
//			  // Encrypt
//			  encryptedCommandData = AES.EncodingBase64(xml2);
//			  String test2 = publishServiceSoap.execCommand(partnerGUID, encryptedCommandData);
//			  //String test2 = publishServiceSoap.executeCommand(partnerGUID, encryptedCommandData);
//			  log.info("execCommand -- pushInvoice Invoices -- " +test2);
//			  return test2;
//		}
}
	  

