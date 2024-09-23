package vn.gt.portlet.kehoach;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.file.Base64;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;


import com.fds.nsw.liferay.core.ServiceContext;

import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.core.PortalUtil;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.file.DLAppLocalServiceUtil;
import com.fds.nsw.liferay.service.DLFileEntryLocalServiceUtil;
import com.fds.nsw.liferay.file.DLStoreUtil;

import vgca.svrsigner.ServerSigner;
import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.report.FileUploadUtils;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportUtils;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.ConvertUtil;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;
import vn.gt.utils.config.ConfigurationManager;

@Slf4j
public class SignatureUtil {
	
	

	public static void genComputerHashByNoiDungHoSo(ResourceRequest resourceRequest, ResourceResponse resourceResponse, String filePath,
			long documentName, int documentYear, int messageType, long groupId, int type) throws IOException {
		log.info("------vao 0 genComputerHashByNoiDungHoSo-----");
		long userId = PortalUtil.getUserId(resourceRequest);
		byte[] inHash = null;
//		String filePath2 = "";
		String fieldName = StringPool.BLANK;
		JSONObject jsonFeed = JSONFactoryUtil.createJSONObject();
		String tenDangNhap = "";

		JSONArray hashComputers = JSONFactoryUtil.createJSONArray();
		JSONArray signFieldNames = JSONFactoryUtil.createJSONArray();
		JSONArray filePaths = JSONFactoryUtil.createJSONArray();
		JSONArray messages = JSONFactoryUtil.createJSONArray();
//		JSONArray _url_vehicle = JSONFactoryUtil.createJSONArray();
//		JSONArray _id_vehicle = JSONFactoryUtil.createJSONArray();

		long userSignId = ParamUtil.getLong(resourceRequest, "userSignId");
		UserSign userSign = null;
		
		try {
//			if(userSignId > 0) {
//				userSign = UserSignLocalServiceUtil.getUserSign(userSignId);// lay thong tin chu ky theo user chon
//			}
//			
//			// String realExportPathTmp = request.getContextPath();
//			User user = UserLocalServiceUtil.fetchUser(userId);
//			if (user != null) {
//				tenDangNhap = user.getScreenName();
//			}
//			log.info("***************************tenDangNhap: " + tenDangNhap);
			
			userSign = UserSignLocalServiceUtil.getByUserId(userId);
			// String realExportPathTmp = request.getContextPath();
			User user = UserLocalServiceUtil.fetchUser(PortalUtil.getUserId(resourceRequest));
			if (user != null) {
				tenDangNhap = user.getScreenName();
			}
			log.info("---tenDangNhap--" + tenDangNhap);
			
			String realPath = ReportUtils.getTemplateReportFilePath(resourceRequest);
			String realExportPath = realPath + "chuky/";

			String imageBase64 = StringPool.BLANK;
//			String cer = "/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/chuky/";
			String cer = realExportPath;
			String cerPath = cer + tenDangNhap + ".cer";
			String signImagePath = "";
//			String imgsrc = "/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/chuky/";
			String imgsrc = realExportPath;
			signImagePath = imgsrc + tenDangNhap + ".png";

			log.info("***************************cerPath: " + cerPath);
			log.info("***************************signImagePath: " + signImagePath);

			BufferedImage bufferedImage = null;
			
			log.info("***************************signImagePath: " + signImagePath);
			
			if(userSign != null && ((type == 1 && userSign.getFilechukyid() > 0) || (type == 2 && userSign.getFilecondauid() > 0))) {
				long userFileKy = 0;
				if (type == 1) {
					userFileKy = userSign.getFilechukyid();
				} else if (type == 2) {
					userFileKy = userSign.getFilecondauid();
				}
				
				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(userFileKy);
				InputStream inputStream = DLStoreUtil.getFileAsStream(
						fileEntry.getCompanyId(), fileEntry.getFolderId(),
						fileEntry.getName());
				
				imageBase64 = SignatureUtil.getSignatureImageBase64ByInputStream(inputStream);
				
				inputStream = DLStoreUtil.getFileAsStream(
						fileEntry.getCompanyId(), fileEntry.getFolderId(),
						fileEntry.getName());
				
				bufferedImage = SignatureUtil.getImageByInputStream(inputStream);
				
			} else {
				imageBase64 = SignatureUtil.getSignatureImageBase64ByPath(signImagePath);
				bufferedImage = SignatureUtil.getImageByPath(signImagePath);
			}
			
			// String urlFile = StringPool.BLANK;
			//
			// urlFile = PortalUtil.getPortalURL(request);
			//
			// urlFile = urlFile +
			// DocumentUtils.getLinkDownloadFromNoiLuuTruTaiLieuId(noidungHoSo.getTaiLieuDinhKem());

			log.info("***************************urlFile: " + filePath);

			// tinh toa do chu ky
			ExtractTextLocations textLocation = new ExtractTextLocations(filePath);

			int pageSize = textLocation.getPageSize();
			
			log.info("*********************************" + textLocation.getAnchorX() + "-" + textLocation.getAnchorY()
					+ "********************************");

			log.info("*********************************" + textLocation.getPageLLX() + "-" + textLocation.getPageURX() + "-"
					+ textLocation.getPageLLY() + "-" + textLocation.getPageURY() + "*******************************");

			// doc file cer tren server
			Certificate cert = null;
			
			if(userSign != null && userSign.getFilechungthusoid() > 0) {
				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(userSign.getFilechungthusoid());
				InputStream inputStream = DLStoreUtil.getFileAsStream(
						fileEntry.getCompanyId(), fileEntry.getFolderId(),
						fileEntry.getName());
				
				cert = SignatureUtil.getCertificateByInputStream(inputStream);
			} else {
				cert = SignatureUtil.getCertificateByPath(cerPath);
			}
			ServerSigner signer = SignatureUtil.getServerSigner(filePath, cert, imageBase64);

			log.info("***************************signer: " + signer + "*******filePath:" + filePath);

			// tinh kich thuoc cua anh

			int signatureImageWidth = 200;
			
			int signatureImageHeight = 200;
			
			float llx = textLocation.getAnchorX();

			float urx = llx + signatureImageWidth / 3;

			float lly = 200;

			float ury = lly + signatureImageHeight / 3;

			// inHash = signer.computeHash(new Rectangle(llx + 65, lly - 55, urx
			// + 114, ury-20), 1);
			
			signer.setSignatureAppearance(PdfSignatureAppearance.RenderingMode.GRAPHIC_AND_DESCRIPTION);   
			
			float destLLx = 630;
			float destLLy = 15;
			float destURx = 800;
			float destURy = 75;
			if (type == 2) {
				destLLx = 590;
				destLLy = 80;
				destURx = 800;
				destURy = 140;
			}
			
			inHash = signer.computeHash(new Rectangle(destLLx, destLLy,	destURx, destURy), pageSize);

//			filePath2 = "/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/export/30798683514999_Shifting_Order.pdf";

			fieldName = signer.getSignatureName();
			signFieldNames.put(fieldName);
			hashComputers.put(Base64.encode(inHash));
			filePaths.put(filePath);
			log.info("**************inHash: " + inHash + "-----------fieldName: " + fieldName + "----------filePath: " + filePath);
			
			if(userSign != null && ((type == 1 && userSign.getFilechukyid() > 0) || (type == 2 && userSign.getFilecondauid() > 0))) {
				messages.put("success");
				
//				List<IssueShiftingOrder> results = new ArrayList<IssueShiftingOrder>(
//						IssueShiftingOrderLocalServiceUtil.findByDocumentYearAndDocumentYearOrderByColumn(documentName,
//								documentYear, KeyParams.ID, KeyParams.ORDER_BY_DESC));
//				
//				if (Validator.isNotNull(results) && results.size() > 0) {
//					IssueShiftingOrder shiftOrder = results.get(0);
//					try {
//
//						ServiceContext serviceContext = ServiceContextFactory.getInstance(TempDocument.class.getName(),
//								resourceRequest);
//						
//						String newPath = filePath.replaceAll(".pdf", ".signed.pdf");
//						
//						File file = new File(newPath);
//						
//						long fileEntryId = 0;
//							
//						fileEntryId = shiftOrder.getAttachedFile();
//						
//						DLFileEntry fileEntry = FileUploadUtils.uploadFile(userId, groupId, fileEntryId, file,
//								newPath, null, null, serviceContext);
//						
//						shiftOrder.setAttachedFile(fileEntry.getFileEntryId());
//						
//						IssueShiftingOrderLocalServiceUtil.updateIssueShiftingOrder(shiftOrder);
//						
//					} catch (Exception e) {
//						log.error(e.getMessage());
//					}
//				}
				
			} else {
				messages.put("K\u00FD s\u1ED1, \u0111\u00F3ng d\u1EA5u kh\u00F4ng th\u00E0nh c\u00F4ng, th\u1EF1c hi\u1EC7n l\u1EA1i");
			}
		} catch (Exception e) {
			messages.put("Error signature filePath: " + filePath);
			hashComputers.put(StringPool.BLANK);
			signFieldNames.put(StringPool.BLANK);
			filePaths.put(filePath);
			log.error(e.getMessage());
		}

		
		log.info("-----------hashComputers: " +hashComputers );
		jsonFeed.put("hashComputers", hashComputers);
		jsonFeed.put("signFieldNames", signFieldNames);
		jsonFeed.put("filePaths", filePaths);
		jsonFeed.put("msg", messages);
		//todo response when sign
//		PrintWriter out = resourceResponse.getWriter();
//		out.print(jsonFeed.toString());
	}

	public static String makeReportFille(ResourceRequest resourceRequest, long documentName, String tieuDe, int actionType) throws SystemException,
			Exception, PortalException {

		// var nanoTimePDF = $('#nanoTimePDF').val();
		log.info("----vao`-makeReportFille-----");
		String fileName = "";
		long userId = PortalUtil.getUserId(resourceRequest);
		String nanoTimePDF = ParamUtil.getString(resourceRequest, "nanoTimePDF");
		String messageType = ParamUtil.getString(resourceRequest, PageType.MESSAGE_TYPE);
		log.info("--999999999--`-nanoTimePDF----9999999-" + nanoTimePDF);
		// _Shifting_Order.pdf
		String realPath = ReportUtils.getTemplateReportFilePath(resourceRequest);
		String urlFile = realPath+ "export/"+ nanoTimePDF;//"/home/binhth/MEGAsync/SERVER/liferay-portal-6.1.0-ce-ga1/tomcat-7.0.23/webapps/TichHopGiaoThong-portlet/export/" + nanoTimePDF;
		if (MessageType.LENH_DIEU_DONG == ConvertUtil.convertToLong(messageType)) {
			urlFile = urlFile + "_" + ReportConstant.SHIFTING_ORDER_EXPORT;

			log.info("--999999999--`-urlFile----9999999-" + urlFile);
		}
		return urlFile;
	}

	public static void genComputerHashByNoiDungHoSoDongDau(ResourceRequest resourceRequest, ResourceResponse resourceResponse, String filePath,
			long documentName) throws IOException, PortalException, SystemException {
		log.info("------vao 0 genComputerHashByNoiDungHoSo-----");
		long userId = PortalUtil.getUserId(resourceRequest);
		byte[] inHash = null;
		String filePath2 = "";
		String fieldName = StringPool.BLANK;
		JSONObject jsonFeed = JSONFactoryUtil.createJSONObject();
		String tenDangNhap = "";
		String cangvu = "";

		JSONArray hashComputers = JSONFactoryUtil.createJSONArray();
		JSONArray signFieldNames = JSONFactoryUtil.createJSONArray();
		JSONArray filePaths = JSONFactoryUtil.createJSONArray();
		JSONArray messages = JSONFactoryUtil.createJSONArray();
		JSONArray _url_vehicle = JSONFactoryUtil.createJSONArray();
		JSONArray _id_vehicle = JSONFactoryUtil.createJSONArray();

		User userLogin = PortalUtil.getUser(resourceRequest);
		UserPort portDefault = UserPortLocalServiceUtil.findByUserId(userLogin.getUserId());

		if (Validator.isNull(portDefault)) {
			// not do something
		} else {
			DmMaritime byMaritimeCode = DmMaritimeLocalServiceUtil.getByMaritimeCode(portDefault.getPortCode());
			cangvu = byMaritimeCode.getMaritimeReference();
		}

		//long userSignId = ParamUtil.getLong(resourceRequest, "userSignId");
		UserSign userSign = null;
		
		try {
			//if(userSignId > 0) {
				// lay thong tin dong dau theo user dang nhap
				userSign = UserSignLocalServiceUtil.getByUserId(userId);
			//}
			// String realExportPathTmp = request.getContextPath();
			User user = UserLocalServiceUtil.fetchUser(userId);
			if (user != null) {
				tenDangNhap = user.getScreenName();

			}
			log.info("***************************tenDangNhap: " + tenDangNhap);

			log.info("***********55555555555555555555****************cangvu: " + cangvu);

			String realPath = ReportUtils.getTemplateReportFilePath(resourceRequest);
			String realExportPath = realPath + "chuky/";
			String imageBase64 = StringPool.BLANK;
			String cer = realExportPath;//"/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/chuky/";
			String cerPath = cer + cangvu + ".cer";
			String signImagePath = "";
			String imgsrc = realExportPath;//"/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/chuky/";
			signImagePath = imgsrc + cangvu + "_condau.png";

			log.info("***************************cerPath: " + cerPath);
			log.info("***************************signImagePath: " + signImagePath);

			BufferedImage bufferedImage = null;
			
			if(userSign != null && userSign.getFilecondauid() > 0) {
				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(userSign.getFilecondauid());
				InputStream inputStream = DLStoreUtil.getFileAsStream(
						fileEntry.getCompanyId(), fileEntry.getFolderId(),
						fileEntry.getName());
				
				imageBase64 = SignatureUtil.getSignatureImageBase64ByInputStream(inputStream);
				bufferedImage = SignatureUtil.getImageByInputStream(inputStream);
			} else {
				imageBase64 = SignatureUtil.getSignatureImageBase64ByPath(signImagePath);
				bufferedImage = SignatureUtil.getImageByPath(signImagePath);
			}

			// String urlFile = StringPool.BLANK;
			//
			// urlFile = PortalUtil.getPortalURL(request);
			//
			// urlFile = urlFile +
			// DocumentUtils.getLinkDownloadFromNoiLuuTruTaiLieuId(noidungHoSo.getTaiLieuDinhKem());

			log.info("***************************urlFile: " + filePath);

			// tinh toa do chu ky
			ExtractTextLocations textLocation = new ExtractTextLocations(filePath);

			log.info("*********************************" + textLocation.getAnchorX() + "-" + textLocation.getAnchorY()
					+ "********************************");

			log.info("*********************************" + textLocation.getPageLLX() + "-" + textLocation.getPageURX() + "-"
					+ textLocation.getPageLLY() + "-" + textLocation.getPageURY() + "*******************************");

			// doc file cer tren server
			Certificate cert = null;
			
			if(userSign != null && userSign.getFilechungthusoid() > 0) {
				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(userSign.getFilechungthusoid());
				InputStream inputStream = DLStoreUtil.getFileAsStream(
						fileEntry.getCompanyId(), fileEntry.getFolderId(),
						fileEntry.getName());
				
				cert = SignatureUtil.getCertificateByInputStream(inputStream);
			} else {
				cert = SignatureUtil.getCertificateByPath(cerPath);
			}
			
			ServerSigner signer = SignatureUtil.getServerSigner(filePath, cert, imageBase64);

			log.info("***************************signer: " + signer + "*******filePath:" + filePath);

			// tinh kich thuoc cua anh

			int signatureImageWidth = (bufferedImage != null && bufferedImage.getWidth() > 0) ? bufferedImage.getWidth() : 80;

			int signatureImageHeight = (bufferedImage != null && bufferedImage.getHeight() > 0) ? bufferedImage.getHeight() : 80;
			float llx = textLocation.getAnchorX();

			float urx = llx + signatureImageWidth / 3;

			float lly = textLocation.getPageURY() - textLocation.getAnchorY() - signatureImageHeight / 3;

			float ury = lly + signatureImageHeight / 3;

			// inHash = signer.computeHash(new Rectangle(llx + 65, lly - 55, urx
			signer.setSignatureAppearance(PdfSignatureAppearance.RenderingMode.GRAPHIC_AND_DESCRIPTION);   
			// + 114, ury-20), 1);
			//inHash = signer.computeHash(new Rectangle(llx, lly, urx, ury), 1);
			//inHash = signer.computeHash(new Rectangle(llx + 20, lly - 105,	urx + 94, ury - 70), 1);
			inHash = signer.computeHash(new Rectangle(llx + 20, lly - 20, urx + 94, ury + 15), 1);

			fieldName = signer.getSignatureName();
			signFieldNames.put(fieldName);
			hashComputers.put(Base64.encode(inHash));
			filePaths.put(filePath);
			log.info("**************inHash: " + inHash + "-----------fieldName: " + fieldName + "----------filePath: " + filePath);
			messages.put("success");
		} catch (Exception e) {
			messages.put("Error signature filePath: " + filePath);
			hashComputers.put(StringPool.BLANK);
			signFieldNames.put(StringPool.BLANK);
			filePaths.put(filePath);
			log.error(e.getMessage());
		}

		jsonFeed.put("hashComputers", hashComputers);
		jsonFeed.put("signFieldNames", signFieldNames);
		jsonFeed.put("filePaths", filePaths);
		jsonFeed.put("msg", messages);
//todo response when sign
//		PrintWriter out = resourceResponse.getWriter();
//		out.print(jsonFeed.toString());
	}

	public static Certificate getCertificateByPath(String path) throws CertificateException, FileNotFoundException, URISyntaxException {
		return getCertificateByInputStream(new FileInputStream(new File(path)));
	}
	
	public static Certificate getCertificateByURL(String url) throws CertificateException, FileNotFoundException, URISyntaxException {
		return getCertificateByInputStream(new FileInputStream(new File(new URI(url))));
	}
	
	public static Certificate getCertificateByInputStream(InputStream inputStream) throws CertificateException, FileNotFoundException, URISyntaxException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");

		Certificate cert = cf.generateCertificate(inputStream);

		return cert;
	}

	public static ServerSigner getServerSigner(String fullPath, Certificate cert, String imageBase64) {
		ServerSigner signer = new ServerSigner(fullPath, cert);
		signer.setSignatureGraphic(imageBase64);
		signer.setSignatureAppearance(PdfSignatureAppearance.RenderingMode.GRAPHIC_AND_DESCRIPTION);
		return signer;
	}

	public static String getSignatureImageBase64(String url) {

		String base64 = StringPool.BLANK;

		try {
			InputStream is = new URL(url).openStream();

			base64 = getSignatureImageBase64ByInputStream(is);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return base64;
	}

	public static String getSignatureImageBase64ByPath(String fullPath) {

		String base64 = StringPool.BLANK;

		try {
			InputStream is = new FileInputStream(new File(fullPath));

			base64 = getSignatureImageBase64ByInputStream(is);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return base64;
	}
	
	public static String getSignatureImageBase64ByInputStream(InputStream is) {

		String base64 = StringPool.BLANK;

		ByteArrayOutputStream os = null;

		try {
			os = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];

			byte[] imageBuff = null;

			int length;

			while ((length = is.read(buffer)) != -1)
				os.write(buffer, 0, length); // copy streams

			imageBuff = os.toByteArray();

			base64 = Base64.encode(imageBuff);

		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		
		return base64;
	}

	public static BufferedImage getImage(String url) throws IOException, URISyntaxException {
		return getImageByInputStream(new URL(url).openStream());
	}

	public static BufferedImage getImageByPath(String fullPath) throws IOException, URISyntaxException {
		return getImageByInputStream(new FileInputStream(new File(fullPath)));
	}
	
	public static BufferedImage getImageByInputStream(InputStream is)
			throws IOException, URISyntaxException {
		BufferedImage bimg = null;
		
		try {
			bimg = ImageIO.read(is);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (is != null) {
				is.close();
			}
		}

		return bimg;
	}

	public static String saveAsPdf(String dest, long fileId) throws IOException {
		log.info("--999994444349999--`-saveAsPdf----9934343499999-");
		// BufferedImage image = null;
		InputStream is = null;
		OutputStream os = null;
		String imagePath = StringPool.BLANK;
		try {
			
			// URL url = new URL(strURL);
			// image = ImageIO.read(url);
			// is = new URL(strURL).openStream();
			DLFileEntry fileEntry = getFileEntry(fileId);
			if (fileEntry == null)
				return null;
			// is = getInputStreamByFileEntryId(fileId);
			is = fileEntry.getContentStream();
			// image = ImageIO.read(is);
			imagePath = dest + "/" + fileEntry.getTitle();
			// ImageIO.write(image, ext, new File(fileName));
			
			os = new FileOutputStream(imagePath);
			
			byte[] b = new byte[1024];
			int length;
			
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
		return imagePath;
	}

	public static String saveAsImage(String strURL, String dest, String email, String ext, long fileId) throws IOException {
		log.info("--999994444349999--`-saveAsImage----9934343499999-");
		// BufferedImage image = null;
		InputStream is = null;
		OutputStream os = null;
		String imagePath = StringPool.BLANK;
		try {

			// URL url = new URL(strURL);
			// image = ImageIO.read(url);
			// is = new URL(strURL).openStream();
			is = getInputStreamByFileEntryId(fileId);
			// image = ImageIO.read(is);
			imagePath = dest + email + "." + ext;
			// ImageIO.write(image, ext, new File(fileName));

			os = new FileOutputStream(imagePath);

			byte[] b = new byte[1024];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
		return imagePath;
	}

	public static DLFileEntry getFileEntry(long fileEnTryId) {
		DLFileEntry fileEntry = null;

		log.info("--999994444349999--`-fileEntry----9934343499999-" + fileEntry);
		try {
			long userId = ConfigurationManager.getLongProp("id_admin", 10196);
			// long userId = 10198;
			// LogFactoryMOC.getLog(DocumentUtils.class).debug("===Starting check permission============userId======="
			// + userId);
			User user = UserLocalServiceUtil.getUserById(userId);

			fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEnTryId);

			// inputStream = fileEntry.getContentStream();
		} catch (Exception e) {
			e.printStackTrace();
			// LogFactoryMOC.getLog(DocumentUtils.class).error(e);
		}

		// LogFactoryMOC.getLog(DocumentUtils.class).debug("===== getInputStreamByFileEntryId return inputStream :::"
		// + inputStream);
		return fileEntry;
	}

	public static InputStream getInputStreamByFileEntryId(long fileEnTryId) {
		InputStream inputStream = null;
		log.info("--999994444349999--`vao day -getInputStreamByFileEntryId----9934343499999-");
		try {
			long userId = ConfigurationManager.getLongProp("id_admin", 10196);
			User user = UserLocalServiceUtil.getUserById(userId);

			DLFileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEnTryId);

			inputStream = fileEntry.getContentStream();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return inputStream;
	}

	
}
