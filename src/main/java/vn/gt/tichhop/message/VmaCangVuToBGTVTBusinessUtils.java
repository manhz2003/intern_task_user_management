package vn.gt.tichhop.message;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fds.flex.common.ultility.Validator;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.kernel.exception.SystemException;


import com.fds.flex.common.ultility.GetterUtil;

import com.fds.nsw.nghiepvu.model.*;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtRouteConfigLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.*;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.portlet.phuongtien.VMAUtils;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.tichhop.sendmessage.SendMessgaeFunctions;
import vn.gt.tichhop.threat.ObjectExcute;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.Header.To;

import lombok.extern.slf4j.Slf4j;
import vn.nsw.model.Customers;
import vn.nsw.model.RequestInvoice;
import vn.nsw.model.RequestTransactionList;

@Slf4j
public class VmaCangVuToBGTVTBusinessUtils {

	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
		if (d == null) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String s = sdf.format(d);
		return s;
	}
	//Dong bo Bien lai dien tu
	public static String dongBoBienLaiDienTu(ObjectExcute objectExcute, Header header) throws SystemException {
		try {

			String maritimeCode = StringPool.BLANK;
			int messageType = 0;
			String xmlData = null;
			List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(objectExcute.getXmlData().trim());
			log.info("===dongBoBienLaiDienTu===" + maritimeCode);
			for(Object object : liObjects) {

				if (object instanceof vn.nsw.model.RequestInvoice) {
					log.info("dongBoBienLaiDienTu===RequestInvoice===" + maritimeCode);
					messageType = MessageType.TYPE_TICH_HOP_KE_TOAN_VIET_DA;

					vn.nsw.model.ResponseInvoice item = new vn.nsw.model.ResponseInvoice();
					vn.nsw.model.ResponseInvoice.Invoice invoiceDetails = new vn.nsw.model.ResponseInvoice.Invoice();
					String function = header.getSubject().getFunction();

					xmlData = objectExcute.getXmlData();

					// Trang thai request ban dau
					String requestCodeOld = header.getReference().getMessageId();

					maritimeCode = GetterUtil.getString(RequestInvoice.class.cast(object).getMaritimeCode());
					String transactionCode = GetterUtil.getString(RequestInvoice.class.cast(object).getTransactionCode());

					BusinessUtils.insertHistory(header, xmlData, BusinessUtils.CangVuToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN, UUID
							.randomUUID().toString());

					try {

						VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil.fetchVmaTransactionSlip(Long.valueOf(transactionCode));

						VmaTransactionSlipDetails vmaTransactionSlipDetails = new VmaTransactionSlipDetails();
						if (Validator.isNotNull(vmaTransactionSlip)) {
							vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
									.fetchByItineraryNo_DocumentaryCode(vmaTransactionSlip.getItineraryNo(),
											vmaTransactionSlip.getDocumentaryCode());
						}

						invoiceDetails.setShipName(vmaTransactionSlip.getNameOfShip().toString()); // Tên tàu
						invoiceDetails.setShipFlag(vmaTransactionSlip
								.getFlagStateOfShip() != null ? DmStateLocalServiceUtil
								.getByStateCode(vmaTransactionSlip.getFlagStateOfShip())
								.getStateName() : ""); // Quốc tịch
						invoiceDetails.setExtra3(vmaTransactionSlip.getCallSign()); // Hô hiệu
						if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("4")) {
							if (Validator.isNull(vmaTransactionSlip.getShipOperationName())
									|| (vmaTransactionSlip.getShipOperationName().trim().length() <= 1)
									|| (vmaTransactionSlip.getShipOperationName().equalsIgnoreCase("0"))) {  // Người khai thác
								invoiceDetails.setShipOwner(vmaTransactionSlip.getPaymentName());
								//invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getPaymentName());
							} else {
								invoiceDetails.setShipOwner(vmaTransactionSlip.getShipOperationName());
								//invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getShipOperationName());
							}
						} else if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("3")) {
							invoiceDetails.setShipOwner(vmaTransactionSlip.getPaymentName()); // Người thanh toán Khác
							//invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getPaymentName());
						} else if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("2")) {
							invoiceDetails.setShipOwner(vmaTransactionSlip.getShipOwnerName()); // Chủ tàu hoặc chủ hàng
							//invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getShipOwnerName());
							if (Validator.isNull(vmaTransactionSlip.getShipOwnerName())
									|| (vmaTransactionSlip.getShipOwnerName().trim().length() <= 1)
									|| (vmaTransactionSlip.getShipOwnerName().equalsIgnoreCase("0"))) {
								invoiceDetails.setShipOwner(vmaTransactionSlip.getPaymentName());
								//invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getPaymentName());
							}
						} else {
							invoiceDetails.setShipOwner(vmaTransactionSlip.getShipOwnerName());
							if (Validator.isNull(vmaTransactionSlip.getShipOwnerName())
									|| (vmaTransactionSlip.getShipOwnerName().trim().length() <= 1)
									|| (vmaTransactionSlip.getShipOwnerName().equalsIgnoreCase("0"))) {
								invoiceDetails.setShipOwner("");
								//invoiceBkavUserDefine.setOwner("");
							}

							// Duy nhat CVHH Hải Phòng muốn hiển thị Người khai thác ở mục Chủ tàu, bổ sung thêm CVHH Đà Nẵng
							if  ((maritimeCode.equalsIgnoreCase("19") || maritimeCode.equalsIgnoreCase("18"))
									&& (!(Validator.isNull(vmaTransactionSlip.getShipOperationName())
									|| (vmaTransactionSlip.getShipOperationName().trim().length() <= 1)
									|| (vmaTransactionSlip.getShipOperationName().equalsIgnoreCase("0"))))
							){
								invoiceDetails.setShipOwner(vmaTransactionSlip.getShipOperationName());
								//invoiceBkavUserDefine.setOwner(vmaTransactionSlip.getShipOperationName());
							}
						}


						Customers.Customer customer = new Customers.Customer();
						customer.setCode(vmaTransactionSlip.getItineraryNo());
						customer.setTaxCode(vmaTransactionSlip.getShipAgencyCode());

						if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("3")) {
							customer.setName(vmaTransactionSlip.getPaymentName());
							customer.setTaxCode("----TT----");
							//invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getPaymentName());
						} else if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("2")){
							customer.setName(vmaTransactionSlip.getShipOwnerName());
							//invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getShipOwnerName());
							try {
								String itineraryNo = vmaTransactionSlip.getItineraryNo();
								VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil.fetchByitineraryNo(itineraryNo);

								if (Validator.isNotNull(vmaItinerary.getShipOwnerCode())) {
									DmVmaShipOwner dmVmaShipOwner = DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaItinerary.getShipOwnerCode());
									if (Validator.isNotNull(dmVmaShipOwner) && Validator.isNotNull(dmVmaShipOwner.getTaxCode())) {
										customer.setCode(dmVmaShipOwner.getTaxCode());
										customer.setTaxCode(dmVmaShipOwner.getTaxCode());
										//invoiceBkav.setBuyerTaxCode(dmVmaShipOwner.getTaxCode());
									}
								}
							} catch (Exception e) {

							}
							if (Validator.isNull(vmaTransactionSlip.getShipOwnerName())
									|| (vmaTransactionSlip.getShipOwnerName().trim().length() <= 1)
									|| (vmaTransactionSlip.getShipOwnerName().equalsIgnoreCase("0"))) {
								customer.setName(vmaTransactionSlip.getPaymentName());
								customer.setTaxCode("----TT----");
								//invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getPaymentName());
							}
						} else if (vmaTransactionSlip.getPaymentBy().equalsIgnoreCase("1")){

							if (Validator.isNull(vmaTransactionSlip.getShipAgencyName())) {
								customer.setName(vmaTransactionSlip.getPaymentName()); // lay don vi thanh toan neu khong co dai ly
								//invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getPaymentName());
							} else {
								customer.setName(vmaTransactionSlip.getShipAgencyName());
								customer.setCode(vmaTransactionSlip.getShipAgencyCode());
								customer.setTaxCode(vmaTransactionSlip.getShipAgencyCode());
								//invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getShipAgencyName());
								//invoiceBkav.setBuyerTaxCode(vmaTransactionSlip.getShipAgencyCode());
							}
						} else {
							customer.setName(vmaTransactionSlip.getNameOfShip());
							//invoiceBkav.setBuyerUnitName(vmaTransactionSlip.getNameOfShip());
						}

						if (Validator.isNull(customer.getTaxCode())) {
							customer.setTaxCode("----TT----");
						}
						if (Validator.isNull(customer.getCode())) {
							customer.setCode(vmaTransactionSlip.getItineraryNo());
						}
						customer.setAddress("---");
						customer.setBankAccountName("");
						customer.setBankName("");
						customer.setBankNumber("");
						customer.setEmail("dvc.bgtvt@gmail.com");
						if (Validator.isNotNull(vmaTransactionSlip.getEmailRecipients())) {
							customer.setEmail(vmaTransactionSlip.getEmailRecipients());
						} else {
							customer.setEmail(StringPool.BLANK);
						}
						customer.setFax("");
						customer.setPhone("");
						customer.setContactPerson("");
						customer.setRepresentPerson("");

						if (Validator.isNotNull(vmaTransactionSlip.getShipAgencyCode())) {
							invoiceDetails.setCusName(customer.getName()); // Đại lý
						} else {
							invoiceDetails.setCusName("---"); // Không truyền vào
						}

						invoiceDetails.setCusAddress("---");
						invoiceDetails.setCusPhone("");


						invoiceDetails.setCusCode(customer.getCode());
						if (maritimeCode.equalsIgnoreCase("18") ) { // Sửa riêng cho CV chính thức tích hợp bldt
							invoiceDetails.setCusEmail("dvc.bgtvt@gmail.com");
							if (Validator.isNotNull(vmaTransactionSlip.getEmailRecipients())) {
								invoiceDetails.setCusEmail(vmaTransactionSlip.getEmailRecipients());
							} else {
								invoiceDetails.setCusEmail(StringPool.BLANK);
							}
						}
						invoiceDetails.setCusTaxCode(vmaTransactionSlip.getShipAgencyCode().toString());



						try {
							invoiceDetails.setArrivalDate(FormatData.formatDDMMYYYY
									.format(vmaTransactionSlip.getArrivalDate())); // Ngày đến
						} catch (Exception e) {
							invoiceDetails.setArrivalDate(StringPool.BLANK);
						}
						try {
							invoiceDetails.setDepatureDate(FormatData.formatDDMMYYYY.format(vmaTransactionSlip
									.getDepartureDate())); // Ngày rời
						} catch (Exception e) {
							invoiceDetails.setDepatureDate(StringPool.BLANK);
						}

						invoiceDetails.setShipGRT(String.valueOf(vmaTransactionSlip.getGt()));
						invoiceDetails.setShipGT(String.valueOf(vmaTransactionSlip.getDwt())); //DWT
						invoiceDetails.setShipLOA(vmaTransactionSlip.getArrivalPortName()); // Cảng đến
						invoiceDetails.setBerth(vmaTransactionSlip.getLastPortName()); // Cảng rời
						invoiceDetails.setExtra2(vmaTransactionSlip.getNextPortName()); // Cảng đến tiếp theo
						invoiceDetails.setCusBankName(vmaTransactionSlip.getCargoDescription()); // Hàng hóa xếp, dỡ


						invoiceDetails.setPaymentMethod("Chuyển khoản");
						//invoiceBkav.setPayMethodID("2"); // Chuyển khoản
						if (vmaTransactionSlip.getPaymentType() == 2)
						{
							invoiceDetails.setPaymentMethod("Tiền mặt");
							//invoiceBkav.setPayMethodID("1"); // Tiền mặt
						} else if (vmaTransactionSlip.getPaymentType() == 3)
						{
							invoiceDetails.setPaymentMethod("Ký quỹ");
							//invoiceBkav.setPayMethodID("13"); // Trả trước
						}
						invoiceDetails.setPaymentStatus("1");
						invoiceDetails.setKindOfService(FormatData.getYear(vmaTransactionSlip.getDocumentaryIssued()) + FormatData.getMonth(vmaTransactionSlip.getDocumentaryIssued()));
						Locale locale = new Locale("en", "UK");

						DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
						symbols.setDecimalSeparator(',');
						symbols.setGroupingSeparator('.');

						String pattern = "#,##0.###";
						DecimalFormat formatter = new DecimalFormat(pattern, symbols);
						String patternVND = "#,##0";
						DecimalFormat formatterVND = new DecimalFormat(patternVND, symbols);

						// Chi tiet tung dong ghi tren hoa don
						vn.nsw.model.ResponseInvoice.Invoice.Products products = new vn.nsw.model.ResponseInvoice.Invoice.Products();
						List<VmaTransactionReceipt> listVmaTransactionReceipt = VmaTransactionReceiptLocalServiceUtil
								.findByMaritimeCode(vmaTransactionSlip.getPortofAuthority());

						for (VmaTransactionReceipt objVmaTransactionReceipt : listVmaTransactionReceipt) {


							// Chuan bi list thong tin phi le phi
							int stt = 0;
							String CacKhoanPhi = StringPool.BLANK;
							vn.nsw.model.ResponseInvoice.Invoice.Products.Product product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();

							// <!-- Các khoản phí -->
							String ProdName = StringPool.BLANK;

							// <!-- Số tiền bằng số của đồng Đôla Mỹ USD  -->
							Double Extra1_ThanhTienDongUSD = 0.00;

							// <!-- Đơn giá -->
							long ProdPrice = 0;

							// <!-- total Thành tiền bằng số Việt nam đồng-->
							long Total_ThanhTienVietNamDong = 0;
							long Extra1_ThanhTienDongUSDQuyDoi = 0;

							// <!-- Ghi chú tính phí, lệ phí -->
							String Remark = StringPool.BLANK;
							String GTRemarks = vmaTransactionSlip.getGtRemarks();

							boolean flagChangeSymbolReceipt = false;
							if (vmaTransactionSlip.getItineraryNo().contains("HPG")) {
								flagChangeSymbolReceipt = true;
							}

							if (Validator.isNotNull(GTRemarks)) {
								if (flagChangeSymbolReceipt == true) {
									GTRemarks = StringUtil.replace(GTRemarks, "*", "x");
								}
							}

							stt = 1;
							CacKhoanPhi = "";
							if (CacKhoanPhi.equalsIgnoreCase("GtRemarks")){
								product.setProdName(ProdName);
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark(GTRemarks);
								products.getProduct().add(product);



							}

							CacKhoanPhi = objVmaTransactionReceipt.getTransactionTypeCode().toString();

							stt = 2;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "PortDues"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("PortDues")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("I. Phí cảng vụ (Port dues)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark(GTRemarks);
								if  (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hải Phòng
									product.setRemark("");
									if (vmaTransactionSlipDetails.getGtConversion().doubleValue() == vmaTransactionSlip.getGt().doubleValue()) {
										// do nothing
									} else {
										invoiceDetails.setShipGRT(invoiceDetails.getShipGRT()+ " GTQĐ: "+ vmaTransactionSlipDetails.getGtConversion());
									}

									if ((vmaTransactionSlip.getUsdTotalAmount() > 0) &&
											(maritimeCode.equalsIgnoreCase("19"))){
										product.setRemark("TG: "+ formatterVND
												.format(vmaTransactionSlip.getExchangeRate()) );
										if ((vmaTransactionSlip.getPrintedConvertVND() == 1) ){
											// Trường hợp In quy đổi VNĐ từ USD,
										} else if ((vmaTransactionSlip.getHideExchangeRate() == 0) && (vmaTransactionSlip.getPrintedConvertVND() == 0) ) {
											// Trường hợp In quy đổi VNĐ từ USD,
										} else {
											product.setRemark("");
										}
									}
								}
								if  (maritimeCode.equalsIgnoreCase("2")){ // Duy nhất CVHH Thanh Hóa
									product.setRemark(invoiceDetails.getPaymentMethod() + " " + GTRemarks);
								}

								products.getProduct().add(product);


							}

							stt = 3;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "TonnageFee"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("TonnageFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("1. Phí trọng tải (Tonnage fee)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 4;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "InwardTonnageFee";
							if (CacKhoanPhi.equalsIgnoreCase("InwardTonnageFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Lượt vào (Inward)");
								log.info("vmaTransactionSlipDetails.getInF1311Vnd()======" + vmaTransactionSlipDetails.getInF1311Vnd());
								Remark = "";
								ProdPrice = 0;
								Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1311Vnd()).longValue());
								if (vmaTransactionSlipDetails.getInF1313Vnd() > 0) {
									Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1313Vnd()).longValue());
								}
								Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getInF1312Usd()));
								Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1312Usd() * vmaTransactionSlip.getExchangeRate()).longValue());

								if (vmaTransactionSlipDetails.getInF1311Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1311Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1311Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1311Remarks());
									}
								} else if (vmaTransactionSlipDetails.getInF1312Usd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1312Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1312Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1312Remarks());
									}
								} else if (vmaTransactionSlipDetails.getInF1313Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getInF1313Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getInF1313Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getInF1313Remarks());
									}
								} else {
									product.setRemark("");
								}
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));

								product.setVATRate("0");
								product.setVATAmount("0");


								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}

								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
								}

								// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
								if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
									product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}

								if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
									// Trường hợp In quy đổi VNĐ từ USD,
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setExtra1("0");

									if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
										product.setAmount("0");
									}
								}
								products.getProduct().add(product);


							}

							stt = 5;			product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "OutwardTonnageFee";
							if (CacKhoanPhi.equalsIgnoreCase("OutwardTonnageFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Lượt rời (Outward)");

								Remark = "";
								ProdPrice = 0;
								Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getOutF1311Vnd()).longValue());
								if (vmaTransactionSlipDetails.getOutF1313Vnd() > 0) {
									Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getOutF1313Vnd()).longValue());
								}
								Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getOutF1312Usd()));
								Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getOutF1312Usd() * vmaTransactionSlip.getExchangeRate()).longValue());

								if (vmaTransactionSlipDetails.getOutF1311Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1311Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1311Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1311Remarks());
									}
								} else if (vmaTransactionSlipDetails.getOutF1312Usd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1312Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1312Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1312Remarks());
									}
								} else if (vmaTransactionSlipDetails.getOutF1313Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getInF1313Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getInF1313Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getInF1313Remarks());
									}
								} else {
									product.setRemark("");
								}
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
								product.setVATRate("0");
								product.setVATAmount("0");

								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
								}
								// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
								if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
									product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}

								if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
									// Trường hợp In quy đổi VNĐ từ USD,
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setExtra1("0");

									if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
										product.setAmount("0");
									}
								}
								products.getProduct().add(product);


							}

							stt = 6;			product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "AnchorageFee"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("AnchorageFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("2. Phí neo đậu (Anchorage fee)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 7;			product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "OnShipAnchorageFee";
							if (CacKhoanPhi.equalsIgnoreCase("OnShipAnchorageFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Đối với phương tiện (On ship)");

								Remark = "";
								ProdPrice = 0;
								Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1321Vnd()).longValue());
								Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getInF1322Usd()));
								Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1322Usd() * vmaTransactionSlip.getExchangeRate()).longValue());

								if ((vmaTransactionSlipDetails.getInF1321Vnd() > 0) && (vmaTransactionSlipDetails.getInF1322Usd() > 0)) {
									ProdPrice = 0;

									product.setRemark(vmaTransactionSlipDetails.getF1321Remarks());
									product.setRemark(product.getRemark() + "; " + vmaTransactionSlipDetails.getF1322Remarks());
									if (Validator.isNotNull(product.getRemark()) && (flagChangeSymbolReceipt == true)) {
										product.setRemark(StringUtil.replace(product.getRemark(), "*", "x"));
									}
								} else if (vmaTransactionSlipDetails.getInF1321Vnd() > 0)  {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1321Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1321Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1321Remarks());
									}
								} else if (vmaTransactionSlipDetails.getInF1322Usd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1322Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1322Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1322Remarks());
									}
								} else {
									product.setRemark("");
								}

								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
								product.setVATRate("0");
								product.setVATAmount("0");

								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
								}
								// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
								if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
									product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}

								if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
									// Trường hợp In quy đổi VNĐ từ USD,
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setExtra1("0");

									if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
										product.setAmount("0");
									}
								}
								products.getProduct().add(product);


							}

							stt = 8;			product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "OnCargoAnchorageFee";
							if (CacKhoanPhi.equalsIgnoreCase("OnCargoAnchorageFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Đối với hàng hóa (On cargo)");

								Remark = "";
								ProdPrice = 0;
								Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1331Vnd()).longValue());
								Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getInF1332Usd()));
								Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1332Usd() * vmaTransactionSlip.getExchangeRate()).longValue());

								if ((vmaTransactionSlipDetails.getInF1331Vnd() > 0) && (vmaTransactionSlipDetails.getInF1332Usd() > 0)) {
									ProdPrice = 0;
									product.setRemark(vmaTransactionSlipDetails.getF1331Remarks());
									product.setRemark(product.getRemark() + "; " + vmaTransactionSlipDetails.getF1332Remarks());
									if (Validator.isNotNull(product.getRemark()) && (flagChangeSymbolReceipt == true)) {
										product.setRemark(StringUtil.replace(product.getRemark(), "*", "x"));

									}
								} else if (vmaTransactionSlipDetails.getInF1331Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1331Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1331Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1331Remarks());
									}
								} else if (vmaTransactionSlipDetails.getInF1332Usd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1332Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1332Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1332Remarks());
									}
								} else {
									product.setRemark("");
								}

								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
								product.setVATRate("0");
								product.setVATAmount("0");

								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
								}
								// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
								if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
									product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
									// Trường hợp In quy đổi VNĐ từ USD,
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setExtra1("0");

									if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
										product.setAmount("0");
									}
								}
								products.getProduct().add(product);


							}

							stt = 9;			product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "WharfFee"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("WharfFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("3. Phí sử dụng cầu, bến, phao neo (Buoy/Wharf fee)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 10;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "OnShipWharfFee"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("OnShipWharfFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Đối với phương tiện (On ship)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 11;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "OnCargoWharfFee"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("OnCargoWharfFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Đối với hàng hóa (On cargo)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 12;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "OnPassengerWharfFee"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("OnPassengerWharfFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Đối với hành khách (On passenger)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}


							stt = 13;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "ClearanceFee";
							if (CacKhoanPhi.equalsIgnoreCase("ClearanceFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("4. Lệ phí ra, vào cảng biển (Clearance fee)");

								Remark = "";
								ProdPrice = 0;
								Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlip.getF1361Vnd()).longValue());
								if (vmaTransactionSlip.getF1363Vnd() > 0) {
									Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlip.getF1363Vnd()).longValue());
								}
								Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlip.getF1362Usd()));
								Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlip.getF1362Usd() * vmaTransactionSlip.getExchangeRate()).longValue());

								if ((vmaTransactionSlip.getF1361Vnd() > 0) && (vmaTransactionSlip.getF1362Usd() > 0)) {
									ProdPrice = 0;
									product.setRemark(vmaTransactionSlip.getF1361Remarks());
									product.setRemark(product.getRemark() + "; " + vmaTransactionSlip.getF1362Remarks());
									if (Validator.isNotNull(product.getRemark()) && (flagChangeSymbolReceipt == true)) {
										product.setRemark(StringUtil.replace(product.getRemark(), "*", "x"));
									}
								} else if (vmaTransactionSlip.getF1361Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1361Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1361Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlip.getF1361Remarks());
									}
								} else if (vmaTransactionSlip.getF1362Usd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1362Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1362Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlip.getF1362Remarks());
									}
								} else if (vmaTransactionSlip.getF1363Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1363Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1363Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlip.getF1363Remarks());
									}
								} else {
									product.setRemark("");
								}
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
								product.setVATRate("0");
								product.setVATAmount("0");

								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
								}
								// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
								if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
									product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
									// Trường hợp In quy đổi VNĐ từ USD,
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setExtra1("0");

									if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
										product.setAmount("0");
									}
								}
								products.getProduct().add(product);


							}


							stt = 14;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "SeaProtestFee";
							if (CacKhoanPhi.equalsIgnoreCase("SeaProtestFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("5. Lệ phí chứng thực (Kháng nghị hàng hải) (Sea protest fee)");

								Remark = "";
								ProdPrice = 0;
								Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlip.getF1371Vnd()).longValue());
								if (vmaTransactionSlip.getF1373Vnd() > 0) {
									Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlip.getF1373Vnd()).longValue());
								}
								Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlip.getF1372Usd()));
								Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlip.getF1372Usd() * vmaTransactionSlip.getExchangeRate()).longValue());

								if ((vmaTransactionSlip.getF1371Vnd() > 0) && (vmaTransactionSlip.getF1372Usd() > 0)) {
									ProdPrice = 0;
									product.setRemark(vmaTransactionSlip.getF1371Remarks());
									product.setRemark(product.getRemark() + "; " + vmaTransactionSlip.getF1372Remarks());
									if (Validator.isNotNull(product.getRemark()) && (flagChangeSymbolReceipt == true)) {
										product.setRemark(StringUtil.replace(product.getRemark(), "*", "x"));
									}
								} else if (vmaTransactionSlip.getF1371Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1371Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1371Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlip.getF1371Remarks());
									}
								} else if (vmaTransactionSlip.getF1372Usd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1372Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1372Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlip.getF1372Remarks());
									}
								} else if (vmaTransactionSlip.getF1373Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1373Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1373Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlip.getF1373Remarks());
									}
								} else {
									product.setRemark("");
								}
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
								product.setVATRate("0");
								product.setVATAmount("0");

								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
								}
								// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
								if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
									product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
									// Trường hợp In quy đổi VNĐ từ USD,
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setExtra1("0");

									if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
										product.setAmount("0");
									}
								}
								products.getProduct().add(product);


							}

							stt = 14;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "SeaProtestFee1"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("SeaProtestFee1")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("(Sea protest fee)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 15;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "NavigationFee"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("NavigationFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("II. Phí bảo đảm hàng hải (Aids to navigation fee)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 16;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "InwardNavigationFee";
							if (CacKhoanPhi.equalsIgnoreCase("InwardNavigationFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Lượt vào (Inward)");

								Remark = "";
								ProdPrice = 0;
								Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1351Vnd()).longValue());
								Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getInF1352Usd()));
								Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1352Usd() * vmaTransactionSlip.getExchangeRate()).longValue());

								if (vmaTransactionSlipDetails.getInF1351Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1351Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1351Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1351Remarks());
									}
								} else if (vmaTransactionSlipDetails.getInF1352Usd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1352Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1352Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1352Remarks());
									}
								} else {
									product.setRemark("");
								}

								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
								product.setVATRate("0");
								product.setVATAmount("0");

								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
								}
								// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
								if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
									product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
									// Trường hợp In quy đổi VNĐ từ USD,
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setExtra1("0");

									if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
										product.setAmount("0");
									}
								}
								products.getProduct().add(product);


							}

							stt = 17;			product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "OutwardNavigationFee";
							if (CacKhoanPhi.equalsIgnoreCase("OutwardNavigationFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Lượt rời (Outward)");

								Remark = "";
								ProdPrice = 0;
								Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getOutF1351Vnd()).longValue());
								Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getOutF1352Usd()));
								Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getOutF1352Usd() * vmaTransactionSlip.getExchangeRate()).longValue());

								if (vmaTransactionSlipDetails.getOutF1351Vnd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1351Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1351Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1351Remarks());
									}
								} else if (vmaTransactionSlipDetails.getOutF1352Usd() > 0) {
									ProdPrice = 0;
									if (Validator.isNotNull(vmaTransactionSlipDetails.getF1352Remarks()) && (flagChangeSymbolReceipt == true)) {
										String specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1352Remarks(), "*", "x");
										product.setRemark(specRemarks);
									} else {
										product.setRemark(vmaTransactionSlipDetails.getF1352Remarks());
									}
								} else {
									product.setRemark("");
								}

								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
								product.setVATRate("0");
								product.setVATAmount("0");

								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
								}
								// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
								if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
									product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
									// Trường hợp In quy đổi VNĐ từ USD,
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setExtra1("0");

									if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
										product.setAmount("0");
									}
								}
								products.getProduct().add(product);


							}

							stt = 18;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "PilotFee"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("PilotFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("III. Phí hoa tiêu (Pilot fee)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 19;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "PilotFeeFromTo1"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("PilotFeeFromTo1")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("   Từ (from) ……………. đến (to) ……………….");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 20;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							// CacKhoanPhi = "PilotFeeFromTo2"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("PilotFeeFromTo2")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("   Từ (from) ……………. đến (to) ……………….");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 21;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							// CacKhoanPhi = "AddingFee"; // fixed
							if (CacKhoanPhi.equalsIgnoreCase("AddingFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("Phí bổ sung (Adding fee)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 22;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							// CacKhoanPhi = "ClearanceFee1"; // fixed CVHH Quang Tri
							if (CacKhoanPhi.equalsIgnoreCase("ClearanceFee1")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("III. Lệ phí ra, vào cảng biển (Clearance fee))");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}


							stt = 23;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "InwardClearanceFee";
							if (CacKhoanPhi.equalsIgnoreCase("InwardClearanceFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Lượt vào (Inward)");

								Remark = "";
								ProdPrice = 0;
								Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1361Vnd()).longValue());
								if (vmaTransactionSlipDetails.getInF1363Vnd() > 0) {
									Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1363Vnd()).longValue());
									if ((vmaTransactionSlipDetails.getOutF1363Vnd() == 0) && (vmaTransactionSlip.getPaymentCategory() == 1)) {
										// Chia đôi phí
										Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1363Vnd() / 2).longValue());
									}
								}
								Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getInF1362Usd()));
								Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1362Usd() * vmaTransactionSlip.getExchangeRate()).longValue());

								product.setRemark("");
								if ((vmaTransactionSlipDetails.getInF1362Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
									product.setRemark("$"+vmaTransactionSlipDetails.getInF1362Usd());
								}

								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
								product.setVATRate("0");
								product.setVATAmount("0");

								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
								}
								// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
								if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
									product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
									// Trường hợp In quy đổi VNĐ từ USD,
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setExtra1("0");

									if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
										product.setAmount("0");
									}
								}
								products.getProduct().add(product);


							}

							stt = 24;			product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							//CacKhoanPhi = "OutwardClearanceFee";
							if (CacKhoanPhi.equalsIgnoreCase("OutwardClearanceFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Lượt rời (Outward)");

								Remark = "";
								ProdPrice = 0;
								Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getOutF1361Vnd()).longValue());
								if (vmaTransactionSlipDetails.getOutF1363Vnd() > 0) {
									Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getOutF1363Vnd()).longValue());
								} else {
									if ((vmaTransactionSlipDetails.getInF1363Vnd() > 0) && (vmaTransactionSlip.getPaymentCategory() == 1)) {
										// Chia đôi phí
										Total_ThanhTienVietNamDong = (new Double(vmaTransactionSlipDetails.getInF1363Vnd() / 2).longValue());
									}
								}
								Extra1_ThanhTienDongUSD = (new Double(vmaTransactionSlipDetails.getOutF1362Usd()));
								Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getOutF1362Usd() * vmaTransactionSlip.getExchangeRate()).longValue());

								product.setRemark("");
								if ((vmaTransactionSlipDetails.getOutF1362Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
									product.setRemark("$"+vmaTransactionSlipDetails.getOutF1362Usd());
								}

								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(ProdPrice));
								product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
								product.setExtra1(String.valueOf(Extra1_ThanhTienDongUSD));
								product.setAmount(String.valueOf(Total_ThanhTienVietNamDong));
								product.setVATRate("0");
								product.setVATAmount("0");

								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSD));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								if ((Extra1_ThanhTienDongUSD > 0) &&
										(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh, hien thi tien quy doi
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
								}
								// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
								if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
									product.setTotal(String.valueOf(Total_ThanhTienVietNamDong));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSD));
								}
								products.getProduct().add(product);
								if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (Extra1_ThanhTienDongUSD > 0) ){
									// Trường hợp In quy đổi VNĐ từ USD,
									product.setTotal(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setAmount(String.valueOf(Extra1_ThanhTienDongUSDQuyDoi));
									product.setExtra1("0");

									if (maritimeCode.equalsIgnoreCase("19")){ // Duy nhat CVHH Hai Phong
										product.setAmount("0");
									}
								}

							}

							stt = 25;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							// CacKhoanPhi = "SecurityFee"; // fixed  CVHH Quang Tri
							if (CacKhoanPhi.equalsIgnoreCase("SecurityFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("IV. Phí thẩm định, phê duyệt đánh giá an ninh cảng biển)");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}
							stt = 26;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							// CacKhoanPhi = "PeriodSecurityFee"; // fixed CVHH Quang Tri
							if (CacKhoanPhi.equalsIgnoreCase("PeriodSecurityFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Lần đầu hoặc định kỳ 5 năm");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}
							stt = 27;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							// CacKhoanPhi = "AddingSecurityFee"; // fixed CVHH Quang Tri
							if (CacKhoanPhi.equalsIgnoreCase("AddingSecurityFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("  - Bổ sung");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

							stt = 28;		product = new vn.nsw.model.ResponseInvoice.Invoice.Products.Product();
							// CacKhoanPhi = "CrewBookFee"; // fixed  CVHH Quang Binh, Thai Binh
							if (CacKhoanPhi.equalsIgnoreCase("CrewBookFee")){
								product.setProdName(objVmaTransactionReceipt.getTransactionFunctionNote());
								//product.setProdName("IV. Lệ phí đăng ký và cấp sổ thuyền viên");
								product.setCode(objVmaTransactionReceipt.getSequenceNo()+"");
								product.setProdUnit("Lần");
								product.setProdQuantity(String.valueOf(0));
								product.setProdPrice(String.valueOf(0));
								product.setTotal(String.valueOf(0));
								product.setExtra1(String.valueOf(0));
								product.setAmount("0");
								product.setVATRate("0");
								product.setVATAmount("0");
								product.setRemark("");
								products.getProduct().add(product);


							}

						}
						invoiceDetails.setProducts(products);

						invoiceDetails.setTotal("0");

						invoiceDetails.setVATRate("0");
						invoiceDetails.setVATAmount("0");

						int VND = 1, USD = 2;
						String inWordUSD = DanhMucUtils.convert(
								vmaTransactionSlip.getUsdTotalAmount(), USD) + " ./.";

						String inWordVND = DanhMucUtils.convert(
								vmaTransactionSlip.getVndTotalAmount(), VND) + " ./.";

						invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
						//invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));

						invoiceDetails.setExtra4(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));
						// <!-- Số tiền bằng số của đồng Đôla Mỹ USD  -->

						invoiceDetails.setExtra5(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
						// <!-- Số tiền bằng số Việt nam đồng-->

						if (vmaTransactionSlip.getUsdTotalAmount() > 0 ) {
							//invoiceDetails.setExtra6(inWordUSD);
							// <!-- Tổng tiền (bằng chữ) của đồng Đôla Mỹ USD  -->
						} else {
							//invoiceDetails.setExtra6("///");
						}

						if (vmaTransactionSlip.getVndTotalAmount() > 0 ) {
							invoiceDetails.setAmountInWords(inWordVND);
							// <!-- Tổng tiền (bằng chữ) của Việt nam đồng  -->
						} else {
							invoiceDetails.setAmountInWords("///");
						}


						// Sửa lỗi tách biệt nghiệp vụ CVHH Thua Thien Hue
						if ((vmaTransactionSlip.getVndTotalAmount() > 0) &&
								(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
							invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
							//invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
							invoiceDetails.setAmountInWords(inWordVND);
						}


						if ((vmaTransactionSlip.getUsdTotalAmount() > 0) &&
								(maritimeCode.equalsIgnoreCase("7"))){ // Duy nhat CVHH Thua Thien Hue
							invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));
							//invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));
							invoiceDetails.setAmountInWords(inWordUSD);
						}

						if ((vmaTransactionSlip.getUsdTotalAmount() > 0) &&
								(maritimeCode.equalsIgnoreCase("22"))){ // Duy nhat CVHH Thai Binh
							invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
							//invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
							inWordVND = DanhMucUtils.convert(
									vmaTransactionSlip.getPaymentAmount(), VND) + " ./.";
							invoiceDetails.setAmountInWords(inWordVND);
						}

						if (vmaTransactionSlip.getUsdTotalAmount() > 0){
							invoiceDetails.setExchangeRate(String.valueOf(vmaTransactionSlip.getExchangeRate()));
							invoiceDetails.setCurrencyUnit("USD");

							if ((vmaTransactionSlip.getHideExchangeRate() == 1) && (vmaTransactionSlip.getPrintedConvertVND() == 0)) {
								invoiceDetails.setExchangeRate("0");
							}
						} else {
							invoiceDetails.setExchangeRate("0");
							invoiceDetails.setCurrencyUnit("VND");
						}

						// Sửa lỗi tách biệt nghiệp vụ CVHH Hải Phòng
						if ((vmaTransactionSlip.getVndTotalAmount() > 0 || vmaTransactionSlip.getUsdTotalAmount() > 0) &&
								(maritimeCode.equalsIgnoreCase("19") || maritimeCode.equalsIgnoreCase("18"))){ // Duy nhat CVHH Hai Phong, bổ sung Đà Nẵng
							invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getVndTotalAmount()).longValue())));
							//invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getUsdTotalAmount()))));
							invoiceDetails.setAmountInWords(inWordVND);
							invoiceDetails.setExtra1(inWordUSD);
						}
						if ((vmaTransactionSlip.getVndTotalAmount() > 0) &&
								(maritimeCode.equalsIgnoreCase("19"))){

							invoiceDetails.setCurrencyUnit("VND");
							if ((vmaTransactionSlip.getUsdTotalAmount() > 0) &&
									(maritimeCode.equalsIgnoreCase("19"))){
								invoiceDetails.setCurrencyUnit("VND_USD");
							}
						} else if ((vmaTransactionSlip.getUsdTotalAmount() > 0) &&
								(maritimeCode.equalsIgnoreCase("19"))){
							invoiceDetails.setCurrencyUnit("USD");
						}

						if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (vmaTransactionSlip.getUsdTotalAmount() > 0) ){
							// Trường hợp In quy đổi VNĐ từ USD, Cập nhật lại
							invoiceDetails.setTotal("0");
							//invoiceDetails.setAmount("0");

							invoiceDetails.setExchangeRate(String.valueOf(vmaTransactionSlip.getExchangeRate()));
							invoiceDetails.setCurrencyUnit("VND");

							inWordVND = DanhMucUtils.convert(vmaTransactionSlip.getPaymentAmount(), VND) + " ./.";
							invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
							invoiceDetails.setAmountInWords(inWordVND);
							invoiceDetails.setExtra1("///");
							invoiceDetails.setExtra4("0");
							invoiceDetails.setExtra5(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));


							if ((maritimeCode.equalsIgnoreCase("22")) || (maritimeCode.equalsIgnoreCase("7"))){
								// CVHH Thua Thien Hue, CVHH Thai Binh
								invoiceDetails.setTotal(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
								//invoiceDetails.setAmount(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));
							}

						}

						invoiceDetails.setConvertedAmount(String.valueOf((new Double(vmaTransactionSlip.getPaymentAmount()).longValue())));


						item.getInvoice().add(invoiceDetails);
						item.setMaritimeCode(vmaTransactionSlip.getPortofAuthority());
						item.setPaymentStatus(vmaTransactionSlip.getPaymentStatus()+"");
						item.setTransactionCode(vmaTransactionSlip.getId()+"");
						item.setTransactionDate(vmaTransactionSlip.getPaymentDate() +"");

						String toIdentity = header.getFrom().getIdentity();
						header = BusinessUtils.createHeaderSynchronization(toIdentity, MessageType.NHAP_CANH,
								MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);

						xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header,
								MessageFactory.convertObjectToXml2(item));

					} catch (Exception e) {
						log.error(e.getMessage());
					}



				} else if (object instanceof vn.nsw.model.RequestTransactionList) {
					log.info("dongBoBienLaiDienTu===RequestTransactionList===" + maritimeCode);
					messageType = MessageType.TYPE_TICH_HOP_KE_TOAN_VIET_DA;


					vn.nsw.model.ResponseTransactionList item = new vn.nsw.model.ResponseTransactionList();

					String function = header.getSubject().getFunction();
					xmlData = objectExcute.getXmlData();
					// Trang thai request ban dau
					String requestCodeOld = header.getReference().getMessageId();

					maritimeCode = GetterUtil.getString(RequestTransactionList.class.cast(object).getMaritimeCode());
					String paymentStatus = GetterUtil.getString(RequestTransactionList.class.cast(object).getPaymentStatus());
					String requestedDate = GetterUtil.getString(RequestTransactionList.class.cast(object).getRequestedDate());
					Date sendDate = FormatData.parseStringToDate(header.getSubject().getSendDate());


					String keyData = "TransactionSyncDate"; //StringPool.BLANK;
					String description = StringPool.BLANK;
					String syncDateyyyyMMddHHmmss = date2Str(new Date(),null);

					try {
						DmMaritime maritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode);

						if(maritime != null) {
							description = maritime.getMaritimeReference();
						}
					} catch(Exception e) {
						log.error(e.getMessage());
					}

					if (Validator.isNull(requestedDate)) {

						Calendar calendar = Calendar.getInstance();
						calendar.setTime(sendDate);
						calendar.add(Calendar.MONTH, -3);
						calendar.set(Calendar.DAY_OF_MONTH, 1);
						requestedDate =
								calendar.get(Calendar.YEAR) + "-"
										+ (calendar.get(Calendar.MONTH) + 1) + "-"
										+ calendar.get(Calendar.DATE)
										+ " 00:00:00'";

//						ThamSoHeThong thamSoHeThong = ThamSoHeThongLocalServiceUtil
//								.findByKeyDataAndDescription(keyData, description);
//
//						if (Validator.isNotNull(thamSoHeThong) && Validator.isNotNull(thamSoHeThong.getValueData()) ) {
//							requestedDate = thamSoHeThong.getValueData();
//							// Lay thoi diem dong bo gan nhat, nhung khong qua 03 thang
//						}
					}




					if (Validator.isNull(paymentStatus)) {
						paymentStatus = "6,9";
					} else if (Validator.isNotNull(paymentStatus) && (paymentStatus.contains("6")||paymentStatus.contains("9"))) {
						// giu nguyen
					} else {
						paymentStatus = "6,9";
					}

					int start = -1;
					int end = -1;

					String itineraryNo = StringPool.BLANK;
					String documentaryNo = StringPool.BLANK;
					String documentaryIssued = StringPool.BLANK;
					String documentaryCode = StringPool.BLANK;
					String paymentDate = requestedDate;

					String amountInWordsVnd = StringPool.BLANK;
					String amountInWordsUsd = StringPool.BLANK;
					String financialAccountant = StringPool.BLANK;
					String currentPaymentStatus = StringPool.BLANK;
					String paymentCategory = StringPool.BLANK;
					String imoNumber = StringPool.BLANK;
					String callSign = StringPool.BLANK;
					String nameOfShip = StringPool.BLANK;
					String shipAgencyCode = StringPool.BLANK;
					String shipAgencyName = StringPool.BLANK;
					String shipOwnerName = StringPool.BLANK;
					String paymentBy = StringPool.BLANK;
					String paymentName = StringPool.BLANK;
					Integer paymentType = -1;

					String searchQuery = generateQuery("search", maritimeCode,
							itineraryNo, documentaryNo, documentaryIssued, documentaryCode, paymentDate,
							null,null,
							amountInWordsVnd, amountInWordsUsd, financialAccountant,
							paymentStatus, currentPaymentStatus, paymentCategory, imoNumber, callSign,
							nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName,
							paymentBy, paymentName, paymentType >= 0 ? paymentType
									: null);

					item = VmaTransactionSlipLocalServiceUtil.findVmaTransactionSlip_TichHopBienLai(
							searchQuery, start, end);

					String toIdentity = header.getFrom().getIdentity();
					header = BusinessUtils.createHeaderSynchronization(toIdentity, MessageType.NHAP_CANH,
							MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);

					xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header,
							MessageFactory.convertObjectToXml2(item));

					ReportsBusinessUtils.taoGiaTriThamSo(keyData, description, syncDateyyyyMMddHHmmss);
				}

			}

			return xmlData;
		} catch(Exception e) {
			throw new SystemException(e);

		}
	}


	private static String generateQuery(String cmd, String portofAuthority,
										String itineraryNo, String documentaryNo, String documentaryIssued, String documentaryCode,
										String paymentDate, Double vndTotalAmount, Double usdTotalAmount,
										String amountInWordsVnd, String amountInWordsUsd,
										String financialAccountant, String paymentStatus, String currentPaymentStatus,
										String paymentCategory, String imoNumber, String callSign,
										String nameOfShip, String shipAgencyCode, String shipAgencyName,
										String shipOwnerName, String paymentBy, String paymentName,
										Integer paymentType) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_transaction_slip as a";
		} else {
			sql = "SELECT a.* FROM vma_transaction_slip AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortofAuthority", "'"
					+ portofAuthority + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(documentaryNo)) {
			condition.append(VMAUtils.buildSQLCondition("DocumentaryNo", "'%"
					+ documentaryNo + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(documentaryNo)) {
			condition.append(VMAUtils.buildSQLCondition("documentaryCode", "'%"
					+ documentaryNo + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(documentaryIssued)) {

			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(documentaryIssued);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition(
						"DocumentaryIssued", "'" + strDate + " 00:00:00'"
								+ " AND '" + strDate + " 23:59:59'", "AND",
						StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(paymentDate)) {
			Date date = null;

			try {
				date = FormatData.parseStringToDate(paymentDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("PaymentDate", "'"
						+ strDate + " 00:00:00'", "AND", StringPool.GREATER_THAN_OR_EQUAL));
			}
		}
/*
			if (vndTotalAmount != null) {
				condition.append(VMAUtils.buildSQLCondition("VndTotalAmount",
						vndTotalAmount, "AND", StringPool.EQUAL));
			}

			if (usdTotalAmount != null) {
				condition.append(VMAUtils.buildSQLCondition("UsdTotalAmount",
						usdTotalAmount, "AND", StringPool.EQUAL));
			}
*/
		if (Validator.isNotNull(amountInWordsVnd)) {
			condition.append(VMAUtils.buildSQLCondition("AmountInWordsVnd",
					"'%" + amountInWordsVnd + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(amountInWordsUsd)) {
			condition.append(VMAUtils.buildSQLCondition("AmountInWordsUsd",
					"'%" + amountInWordsUsd + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(financialAccountant)) {
			condition.append(VMAUtils.buildSQLCondition("FinancialAccountant",
					"'%" + financialAccountant + "%'", "AND", StringPool.LIKE));
		}
		if (Validator.isNotNull(paymentStatus)) {
			condition.append(VMAUtils.buildSQLCondition("paymentStatus", "("
					+ paymentStatus + ")", "AND", "IN"));
		}

		if (Validator.isNotNull(paymentCategory)) {
			condition.append(VMAUtils.buildSQLCondition("paymentCategory", "("
					+ paymentCategory + ")", "AND", "IN"));
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(VMAUtils.buildSQLCondition("imoNumber", "'%"
					+ imoNumber + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(VMAUtils.buildSQLCondition("callSign", "'%"
					+ callSign + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("nameOfShip", "'%"
					+ nameOfShip + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(shipAgencyName)) {
			condition.append(VMAUtils.buildSQLCondition("shipAgencyName", "'%"
					+ shipAgencyName + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(shipOwnerName)) {
			condition.append(VMAUtils.buildSQLCondition("shipOwnerName", "'%"
					+ shipOwnerName + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(shipAgencyCode)) {
			condition.append(VMAUtils.buildSQLCondition("shipAgencyCode", "'"
					+ shipAgencyCode + "'", "AND", StringPool.EQUAL));
		}


		if (Validator.isNotNull(currentPaymentStatus) && (!currentPaymentStatus.equals("0")) ) {
			condition.append(VMAUtils.buildSQLCondition("paymentStatus",  "'"
					+ currentPaymentStatus + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(paymentBy)) {
			condition.append(VMAUtils.buildSQLCondition("PaymentBy", "'"
					+ paymentBy + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(paymentName)) {
			condition.append(VMAUtils.buildSQLCondition("PaymentName", "'%"
					+ paymentName + "%'", "AND", StringPool.LIKE));
		}

		if (paymentType != null) {
			condition.append(VMAUtils.buildSQLCondition("PaymentType",
					+paymentType, "AND", StringPool.EQUAL));
		}

		if (cmd.equals("search")) {
			condition.append(" ORDER BY DocumentaryIssued ASC ");
		}
		return sql + condition.toString();
	}
	// Ham nhan xu ly msg dong bo tu cang vu hoac MTGateway
	public static boolean luuVMATables(ObjectExcute objectExcute, Header header) {
		try {
			List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(objectExcute.getXmlData().trim());

			for(Object object : liObjects) {
				if(object instanceof vn.nsw.model.VmaShip) {
					vn.nsw.model.VmaShip obj = (vn.nsw.model.VmaShip) object;

//					for(VmaShipList.VmaShip obj : list.getVmaShip()) {
					VmaShip item = VmaShipLocalServiceUtil.fetchByIMONumber_CallSign(obj.getIMONumber(), obj.getCallSign());

					if(item == null) {
						long id = CounterLocalServiceUtil.increment(VmaShip.class.getName());
						item = VmaShipLocalServiceUtil.createVmaShip(id);
					}

					item.setMtgateway(GetterUtil.getInteger(obj.getMtgateway()));
					item.setMaritimeCode(obj.getMaritimeCode());
					item.setShipName(obj.getShipName());
					item.setShipPreviousName(obj.getShipPreviousName());
					item.setShipTypeMT(obj.getShipTypeMT());
					item.setShipTypeCode(obj.getShipTypeCode());
					item.setShipBoat(obj.getShipBoat());
					item.setHasTugBoat(GetterUtil.getInteger(obj.getHasTugBoat()));
					item.setTugBoatName(obj.getTugBoatName());
					item.setNameOfMaster(obj.getNameOfMaster());
					item.setChiefOfEngineer(obj.getChiefOfEngineer());
					item.setImoNumber(obj.getIMONumber());
					item.setCallSign(obj.getCallSign());
					item.setFlagStateOfShip(obj.getFlagStateOfShip());
					item.setVrCode(obj.getVRCode());
					item.setRegistryNumber(obj.getRegistryNumber());
					item.setRegistryDate(FormatData.parseStringToDate(obj.getRegistryDate()));
					item.setRegistryPortCode(obj.getRegistryPortCode());
					item.setGt(BigDecimal.valueOf(GetterUtil.getDouble(obj.getGT())));
					item.setNt(BigDecimal.valueOf(GetterUtil.getDouble(obj.getNT())));
					item.setDwt(BigDecimal.valueOf(GetterUtil.getDouble(obj.getDWT())));
					item.setLoa(GetterUtil.getDouble(obj.getLOA()));
					item.setBreadth(GetterUtil.getDouble(obj.getBreadth()));
					item.setClearanceHeight(GetterUtil.getDouble(obj.getClearanceHeight()));
					item.setPower(GetterUtil.getDouble(obj.getPower()));
					item.setMaxDraft(GetterUtil.getDouble(obj.getMaxDraft()));
					item.setShownDraftxF(GetterUtil.getDouble(obj.getShownDraftxF()));
					item.setShownDraftxA(GetterUtil.getDouble(obj.getShownDraftxA()));
					item.setUnitPower(obj.getUnitPower());
					item.setProductionCountry(obj.getProductionCountry());
					item.setProductionYear(obj.getProductionYear());
					item.setShipOwnerCode(obj.getShipOwnerCode());
					item.setShipOperatorCode(obj.getShipOperatorCode());
					item.setShipAgencyCode(obj.getShipAgencyCode());
					item.setExpiredDate(FormatData.parseStringToDate(obj.getExpiredDate()));
					item.setRemarks(obj.getRemarks());
					item.setIsDelete(GetterUtil.getInteger(obj.getIsDelete()));
					item.setModifiedDate(FormatData.parseStringToDate(obj.getModifiedDate()));
					item.setMarkedAsDelete(GetterUtil.getInteger(obj.getMarkedAsDelete()));
					item.setRequestedDate(FormatData.parseStringToDate(obj.getRequestedDate()));
					item.setSyncVersion(obj.getSyncVersion());

					VmaShipLocalServiceUtil.updateVmaShip(item);
//					}
				}
			}

			return true;
		} catch(Exception e) {
			log.error(e.getMessage());
		}

		return false;
	}

	//Dong do VMA tu Cang Vu len
	public static void dongBoVMATables(String maritimeCode, Object object) throws SystemException {
		try {
			int messageType = 0;
			String xmlData = null;
			Header header = null;

			log.info("===dongBoVMATables===" + maritimeCode);
			if (object instanceof VmaShip) {
				messageType = MessageType.DONG_BO_VMA_SHIP;

				vn.nsw.model.VmaShip item = new vn.nsw.model.VmaShip();

				item.setMtgateway(GetterUtil.getString(VmaShip.class.cast(object).getMtgateway()));
				item.setMaritimeCode(GetterUtil.getString(VmaShip.class.cast(object).getMaritimeCode()));
				item.setShipName(GetterUtil.getString(VmaShip.class.cast(object).getShipName()));
				item.setShipPreviousName(GetterUtil.getString(VmaShip.class.cast(object).getShipPreviousName()));
				item.setShipTypeMT(GetterUtil.getString(VmaShip.class.cast(object).getShipTypeMT()));
				item.setShipTypeCode(GetterUtil.getString(VmaShip.class.cast(object).getShipTypeCode()));
				item.setShipBoat(GetterUtil.getString(VmaShip.class.cast(object).getShipBoat()));
				item.setHasTugBoat(GetterUtil.getString(VmaShip.class.cast(object).getHasTugBoat()));
				item.setTugBoatName(GetterUtil.getString(VmaShip.class.cast(object).getTugBoatName()));
				item.setNameOfMaster(GetterUtil.getString(VmaShip.class.cast(object).getNameOfMaster()));
				item.setChiefOfEngineer(GetterUtil.getString(VmaShip.class.cast(object).getChiefOfEngineer()));
				item.setIMONumber(GetterUtil.getString(VmaShip.class.cast(object).getImoNumber()));
				item.setCallSign(GetterUtil.getString(VmaShip.class.cast(object).getCallSign()));
				item.setFlagStateOfShip(GetterUtil.getString(VmaShip.class.cast(object).getFlagStateOfShip()));
				item.setVRCode(GetterUtil.getString(VmaShip.class.cast(object).getVrCode()));
				item.setRegistryNumber(GetterUtil.getString(VmaShip.class.cast(object).getRegistryNumber()));
				item.setRegistryDate(FormatData.parseDateToTring((VmaShip.class.cast(object).getRegistryDate())));
				item.setRegistryPortCode(GetterUtil.getString(VmaShip.class.cast(object).getRegistryPortCode()));
				item.setGT(GetterUtil.getString(VmaShip.class.cast(object).getGt()));
				item.setNT(GetterUtil.getString(VmaShip.class.cast(object).getNt()));
				item.setDWT(GetterUtil.getString(VmaShip.class.cast(object).getDwt()));
				item.setLOA(GetterUtil.getString(VmaShip.class.cast(object).getLoa()));
				item.setBreadth(GetterUtil.getString(VmaShip.class.cast(object).getBreadth()));
				item.setClearanceHeight(GetterUtil.getString(VmaShip.class.cast(object).getClearanceHeight()));
				item.setPower(GetterUtil.getString(VmaShip.class.cast(object).getPower()));
				item.setMaxDraft(GetterUtil.getString(VmaShip.class.cast(object).getMaxDraft()));
				item.setShownDraftxF(GetterUtil.getString(VmaShip.class.cast(object).getShownDraftxF()));
				item.setShownDraftxA(GetterUtil.getString(VmaShip.class.cast(object).getShownDraftxA()));
				item.setUnitPower(GetterUtil.getString(VmaShip.class.cast(object).getUnitPower()));
				item.setProductionCountry(GetterUtil.getString(VmaShip.class.cast(object).getProductionCountry()));
				item.setProductionYear(GetterUtil.getString(VmaShip.class.cast(object).getProductionYear()));
				item.setShipOwnerCode(GetterUtil.getString(VmaShip.class.cast(object).getShipOwnerCode()));
				item.setShipOperatorCode(GetterUtil.getString(VmaShip.class.cast(object).getShipOperatorCode()));
				item.setShipAgencyCode(GetterUtil.getString(VmaShip.class.cast(object).getShipAgencyCode()));
				item.setExpiredDate(FormatData.parseDateToTring(VmaShip.class.cast(object).getExpiredDate()));
				item.setRemarks(GetterUtil.getString(VmaShip.class.cast(object).getRemarks()));
				item.setIsDelete(GetterUtil.getString(VmaShip.class.cast(object).getIsDelete()));
				item.setModifiedDate(FormatData.parseDateToTring(VmaShip.class.cast(object).getModifiedDate()));
				item.setMarkedAsDelete(GetterUtil.getString(VmaShip.class.cast(object).getMarkedAsDelete()));
				item.setRequestedDate(FormatData.parseDateToTring(VmaShip.class.cast(object).getRequestedDate()));
				item.setSyncVersion(GetterUtil.getString(VmaShip.class.cast(object).getSyncVersion()));

//				VmaShipList list = new VmaShipList();
//				list.getVmaShip().add(item);

				header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH,
						MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);

				xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header,
						MessageFactory.convertObjectToXml2(item));

			} else if (object instanceof VmaItinerary) {
				messageType = MessageType.DONG_BO_VMA_ITINERARY;

				vn.nsw.model.VmaItinerary item = new vn.nsw.model.VmaItinerary();

				item.setMtgateway(GetterUtil.getString(VmaItinerary.class.cast(object).getMtgateway()));
				item.setMaritimeCode(GetterUtil.getString(VmaItinerary.class.cast(object).getMaritimeCode()));
				item.setItineraryNo(GetterUtil.getString(VmaItinerary.class.cast(object).getItineraryNo()));
				item.setNameOfShip(GetterUtil.getString(VmaItinerary.class.cast(object).getNameOfShip()));
				item.setFlagStateOfShip(GetterUtil.getString(VmaItinerary.class.cast(object).getFlagStateOfShip()));
				item.setIMONumber(GetterUtil.getString(VmaItinerary.class.cast(object).getImoNumber()));
				item.setCallSign(GetterUtil.getString(VmaItinerary.class.cast(object).getCallSign()));
				item.setVRCode(GetterUtil.getString(VmaItinerary.class.cast(object).getVrCode()));
				item.setRegistryNumber(GetterUtil.getString(VmaItinerary.class.cast(object).getRegistryNumber()));
				item.setShipPosition(GetterUtil.getString(VmaItinerary.class.cast(object).getShipPosition()));
				item.setShipTypeCode(GetterUtil.getString(VmaItinerary.class.cast(object).getShipTypeCode()));
				item.setVmaShipTypeCode(GetterUtil.getString(VmaItinerary.class.cast(object).getVmaShipTypeCode()));
				item.setShipCaptain(GetterUtil.getString(VmaItinerary.class.cast(object).getShipCaptain()));
				item.setPortofAuthority(GetterUtil.getString(VmaItinerary.class.cast(object).getPortofAuthority()));
				item.setRepresentativeofAuthority(GetterUtil.getString(VmaItinerary.class.cast(object).getRepresentativeofAuthority()));
				item.setMarkedAsArrival(GetterUtil.getString(VmaItinerary.class.cast(object).getMarkedAsArrival()));
				item.setMarkedAsDeparture(GetterUtil.getString(VmaItinerary.class.cast(object).getMarkedAsDeparture()));
				item.setMarkedAsTransmit(GetterUtil.getString(VmaItinerary.class.cast(object).getMarkedAsTransmit()));
				item.setMarkedAsVoyage(GetterUtil.getString(VmaItinerary.class.cast(object).getMarkedAsVoyage()));
				item.setDocumentNameIN(GetterUtil.getString(VmaItinerary.class.cast(object).getDocumentNameIN()));
				item.setDocumentYearIN(GetterUtil.getString(VmaItinerary.class.cast(object).getDocumentYearIN()));
				item.setDocumentNameOUT(GetterUtil.getString(VmaItinerary.class.cast(object).getDocumentNameOUT()));
				item.setDocumentYearOUT(GetterUtil.getString(VmaItinerary.class.cast(object).getDocumentYearOUT()));
				item.setDocumentNameTRA(GetterUtil.getString(VmaItinerary.class.cast(object).getDocumentNameTRA()));
				item.setDocumentYearTRA(GetterUtil.getString(VmaItinerary.class.cast(object).getDocumentYearTRA()));
				item.setDocumentNameVOY(GetterUtil.getString(VmaItinerary.class.cast(object).getDocumentNameVOY()));
				item.setDocumentYearVOY(GetterUtil.getString(VmaItinerary.class.cast(object).getDocumentYearVOY()));
				item.setVoyageNumber(GetterUtil.getString(VmaItinerary.class.cast(object).getVoyageNumber()));
				item.setTimeOfArrival(GetterUtil.getString(VmaItinerary.class.cast(object).getTimeOfArrival()));
				item.setTimeOfDeparture(GetterUtil.getString(VmaItinerary.class.cast(object).getTimeOfDeparture()));
				item.setShipOwnerCode(GetterUtil.getString(VmaItinerary.class.cast(object).getShipOwnerCode()));
				item.setShipOwnerName(GetterUtil.getString(VmaItinerary.class.cast(object).getShipOwnerName()));
				item.setShipOperatorCode(GetterUtil.getString(VmaItinerary.class.cast(object).getShipOperatorCode()));
				item.setShipOperatorName(GetterUtil.getString(VmaItinerary.class.cast(object).getShipOperatorName()));
				item.setArrivalShipAgencyCode(GetterUtil.getString(VmaItinerary.class.cast(object).getArrivalShipAgencyCode()));
				item.setArrivalShipAgency(GetterUtil.getString(VmaItinerary.class.cast(object).getArrivalShipAgency()));
				item.setDepartureShipAgencyCode(GetterUtil.getString(VmaItinerary.class.cast(object).getDepartureShipAgencyCode()));
				item.setDepartureShipAgency(GetterUtil.getString(VmaItinerary.class.cast(object).getDepartureShipAgency()));
				item.setModifiedDate(GetterUtil.getString(VmaItinerary.class.cast(object).getModifiedDate()));

//				VmaItineraryList list = new VmaItineraryList();
//				list.getVmaItinerary().add(item);

				header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH,
						MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);

				xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header,
						MessageFactory.convertObjectToXml2(item));

			} else if (object instanceof VmaItineraryRemark) {
				messageType = MessageType.DONG_BO_VMA_ITINERARY_REMARKS;

				com.fds.nsw.nghiepvu.model.VmaItineraryRemark item = new com.fds.nsw.nghiepvu.model.VmaItineraryRemark();

				item.setItineraryNo(GetterUtil.getString(VmaItineraryRemark.class.cast(object).getItineraryNo()));
				item.setSequenceNo(GetterUtil.getInteger(VmaItineraryRemark.class.cast(object).getSequenceNo()));
				item.setPortofAuthority(GetterUtil.getString(VmaItineraryRemark.class.cast(object).getPortofAuthority()));
				item.setNameOfShip(GetterUtil.getString(VmaItineraryRemark.class.cast(object).getNameOfShip()));
				item.setDocumentName(GetterUtil.getLong(VmaItineraryRemark.class.cast(object).getDocumentName()));
				item.setDocumentYear(GetterUtil.getInteger(VmaItineraryRemark.class.cast(object).getDocumentYear()));
				item.setNoticeShipType(GetterUtil.getInteger(VmaItineraryRemark.class.cast(object).getNoticeShipType()));
				item.setRequestDate(VmaItineraryRemark.class.cast(object).getRequestDate());
				item.setRequestPerson(GetterUtil.getString(VmaItineraryRemark.class.cast(object).getRequestPerson()));
				item.setRemarks(GetterUtil.getString(VmaItineraryRemark.class.cast(object).getRemarks()));
				item.setMarkedAsPending(GetterUtil.getInteger(VmaItineraryRemark.class.cast(object).getMarkedAsPending()));
				item.setModifiedDate(VmaItineraryRemark.class.cast(object).getModifiedDate());

//				VmaItineraryRemarksList list = new VmaItineraryRemarksList();
//				list.getVmaItineraryRemarks().add(item);

				header = BusinessUtils.createHeaderSynchronization(MessageType.NHAP_CANH,
						MessageType.FUNCTION_KHAI_BAO, messageType, maritimeCode);

				xmlData = BusinessUtils.createContentSendFromBGTVTToNSW(header,
						MessageFactory.convertObjectToXml2(item));

			}

			if(header != null && xmlData != null) {
				insertDMSycnQueue(header, xmlData);
			}
		} catch(Exception e) {
			throw new SystemException(e);
		}
	}

	public static void insertDMSycnQueue(Header header, String xmlData) {
		List<DmGtRouteConfig> routeConfigList = DmGtRouteConfigLocalServiceUtil.findByLocCode(MessageType.BGTVT);

		if(routeConfigList != null && routeConfigList.size() > 0) {
			DmGtRouteConfig routeConfig = routeConfigList.get(0);

			String locCode = routeConfig.getLocCode();

			String webservice = routeConfig.getLinkAddress();

			To to = header.getTo();
			to.setIdentity(locCode);
			to.setName(locCode);

			header.setTo(to);

			BusinessUtils.insertHistory(header, xmlData, locCode, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU,
					UUID.randomUUID().toString());

			SendMessgaeFunctions.insertMessageQueue(header, xmlData, locCode, null, webservice);
		}
	}

	public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



}
