package vn.gt.portlet.thutuc;

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
import java.nio.file.NoSuchFileException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.List;

import javax.imageio.ImageIO;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;

import com.fds.nsw.liferay.file.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.model.User;


import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.core.PortalUtil;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.service.DLFileEntryLocalServiceUtil;

import vgca.svrsigner.ServerSigner;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import vn.gt.portlet.kehoach.ExtractTextLocations;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.report.ReportConstant;
import vn.gt.tichhop.report.ReportUtils;
import vn.gt.utils.ConvertUtil;
import vn.gt.utils.PageType;
import vn.gt.utils.config.ConfigurationManager;

@Slf4j
public class SignatureUtil {
	

	public static void genComputerHashByNoiDungHoSo(ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			String filePath, long documentName, int documentYear, int messageType, long groupId, int type)
			throws IOException {

		log.info("------genComputerHashByNoiDungHoSo-----");

		long userId = PortalUtil.getUserId(resourceRequest);
		byte[] inHash = null;
		String fieldName = StringPool.BLANK;

		JSONObject jsonFeed = JSONFactoryUtil.createJSONObject();
		JSONArray hashComputers = JSONFactoryUtil.createJSONArray();
		JSONArray signFieldNames = JSONFactoryUtil.createJSONArray();
		JSONArray filePaths = JSONFactoryUtil.createJSONArray();
		JSONArray messages = JSONFactoryUtil.createJSONArray();
		boolean useHSM = ParamUtil.getBoolean(resourceRequest, "useHSM");//TODO:

		try {
			UserSign userSign = UserSignLocalServiceUtil.getByUserId(userId);
//			User user = UserLocalServiceUtil.fetchUser(PortalUtil.getUserId(resourceRequest));
//			if (user != null) {
//				tenDangNhap = user.getScreenName();
//			}

//			String realPath = ReportUtils.getTemplateReportFilePath(resourceRequest);
//			String realExportPath = realPath + "chuky/";
//
//			String imageBase64 = StringPool.BLANK;
//			String cer = realExportPath;
//			String cerPath = cer + tenDangNhap + ".cer";

			if (userSign != null&& (
					(type == 1 && userSign.getFilechukyid() > 0)
					|| (type == 2 && userSign.getFilecondauid() > 0))) {
//				long userFileKy = 0;
//				if (type == 1) {
//					userFileKy = userSign.getFilechukyid();
//				} else if (type == 2) {
//					userFileKy = userSign.getFilecondauid();
//				}

//				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(userFileKy);
//				InputStream imageInputStream = DLStoreUtil.getFileAsStream(fileEntry.getCompanyId(), fileEntry.getFolderId(),
//						fileEntry.getName());

//				imageBase64 = SignatureUtil.getSignatureImageBase64ByInputStream(imageInputStream);
//
//				imageInputStream = DLStoreUtil.getFileAsStream(fileEntry.getCompanyId(), fileEntry.getFolderId(),
//						fileEntry.getName());
				
				InputStream imageInputStream = getImageInputStream(userSign, type);
				
				Certificate cert = getCertificate(userSign, useHSM);

				// doc file cer tren server
//				Certificate cert = null;
//
//				if (userSign.getFilechungthusoid() > 0) {
//					DLFileEntry chungThuSoFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(userSign.getFilechungthusoid());
//					InputStream inputStream = DLStoreUtil.getFileAsStream(chungThuSoFileEntry.getCompanyId(), chungThuSoFileEntry.getFolderId(),
//							chungThuSoFileEntry.getName());
//
//					cert = SignatureUtil.getCertificateByInputStream(inputStream);
//				} else {
//					cert = SignatureUtil.getCertificateByPath(cerPath);
//				}
				
				if(useHSM) {
					String signedFile = kySoHSM(filePath, cert, imageInputStream, type, messageType);
					signFieldNames.put(signedFile);
					filePaths.put(signedFile);
					hashComputers.put(signedFile);
				} else {
					ExtractTextLocations textLocation = new ExtractTextLocations(filePath);

					int pageSize = textLocation.getPageSize();
					
					Rectangle rec = createRectangle(textLocation, type, messageType);
					
					String imageBase64 = SignatureUtil.getSignatureImageBase64ByInputStream(imageInputStream);
					
					ServerSigner signer = SignatureUtil.getServerSigner(filePath, cert, imageBase64);
					
					signer.setSignatureAppearance(PdfSignatureAppearance.RenderingMode.GRAPHIC_AND_DESCRIPTION);
					
					inHash = signer.computeHash(rec, pageSize);

					fieldName = signer.getSignatureName();
					signFieldNames.put(fieldName);
					hashComputers.put(Base64.encode(inHash));
					filePaths.put(filePath);
				}
				
				messages.put("success");
			} else {
				messages.put(
						"K\u00FD s\u1ED1, \u0111\u00F3ng d\u1EA5u kh\u00F4ng th\u00E0nh c\u00F4ng, th\u1EF1c hi\u1EC7n l\u1EA1i");
			}

		} catch (Exception e) {
			messages.put("Error signature filePath: " + filePath);
			hashComputers.put(StringPool.BLANK);
			signFieldNames.put(StringPool.BLANK);
			filePaths.put(filePath);
			log.error(e.getMessage());
		}

		log.info("-----------hashComputers: " + hashComputers);

		jsonFeed.put("hashComputers", hashComputers);
		jsonFeed.put("signFieldNames", signFieldNames);
		jsonFeed.put("filePaths", filePaths);
		jsonFeed.put("msg", messages);
//todo response file in controller
//		PrintWriter out = resourceResponse.getWriter();
//		out.print(jsonFeed.toString());
	}
	
	public static Rectangle createRectangle(ExtractTextLocations textLocation, int type, int messageType) throws IOException {
		int signatureImageWidth = 200;

		int signatureImageHeight = 200;

		float llx = textLocation.getAnchorX();

		float urx = llx + signatureImageWidth / 3;

		float lly = textLocation.getPageURY() - textLocation.getAnchorY() - signatureImageHeight / 3;

		float ury = lly + signatureImageHeight / 3;

		float destLLx = llx + 20;
		float destLLy = lly - 125;
		float destURx = urx + 154;
		float destURy = ury - 80;
		if (type == 2) {
			destLLx = llx + 20;
			destLLy = lly + 15;
			destURx = urx + 154;
			destURy = ury + 0;
		}
		
		// FIX RIENG CHO LENH DIEU DONG
		if (messageType == 70) {
			destLLx = 630;
			destLLy = 15;
			destURx = 800;
			destURy = 75;
			if (type == 2) {
				destLLx = 590;
				destLLy = 80;
				destURx = 800;
				destURy = 140;
			}
		}
		
		return new Rectangle(destLLx, destLLy, destURx, destURy);
	}
	
	public static String kySoHSM(String filePath, Certificate cert, InputStream imageInputStream,
			int type, int messageType) throws IOException {
		ExtractTextLocations textLocation = new ExtractTextLocations(filePath);
		
		Rectangle rec = createRectangle(textLocation, type, messageType);

		vgca.hsmsigner.ServerSigner signer = SignatureUtil.getServerSignerHSM(filePath, cert, imageInputStream);

		signer.Sign(rec, textLocation.getPageSize());

		return signer.getSignedFile();
	}
	
	public static Certificate getCertificate(UserSign userSign, boolean useHSM) 
			throws CertificateException, FileNotFoundException, URISyntaxException, PortalException, SystemException {
		
		Certificate cert = null;

		if(useHSM) {
			//todo load PropsUtil by .properties in spring
			String cerPath = "";
//					PropsUtil.get("dl.store.file.system.root.dir") + "/cert/" + userSign.getFilechungthusohsm();
			
			cert = SignatureUtil.getCertificateByPath(cerPath);
		} else {
			DLFileEntry chungThuSoFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(userSign.getFilechungthusoid());
            InputStream inputStream = null;
            try {
                inputStream = DLStoreUtil.getFileAsStream(chungThuSoFileEntry.getCompanyId(), chungThuSoFileEntry.getFolderId(),
                        chungThuSoFileEntry.getName());
            } catch (NoSuchFileException e) {
                throw new RuntimeException(e);
            }

            cert = SignatureUtil.getCertificateByInputStream(inputStream);
		}
		
		return cert;
	}
	
	public static InputStream getImageInputStream(UserSign userSign, int type) throws PortalException, SystemException {
		long userFileKy = 0;
		
		if (type == 1) {
			userFileKy = userSign.getFilechukyid();
		} else if (type == 2) {
			userFileKy = userSign.getFilecondauid();
		}

		DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(userFileKy);

        try {
            return DLStoreUtil.getFileAsStream(fileEntry.getCompanyId(), fileEntry.getFolderId(),
                    fileEntry.getName());
        } catch (NoSuchFileException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static String completeDongDauHSM(long fileEntryId, long userId, int messageType) 
			throws PortalException, SystemException, CertificateException, URISyntaxException, IOException {
		
		StringBuilder sb = new StringBuilder();
		sb.append(SystemProperties.get(SystemProperties.TMP_DIR));
		sb.append(StringPool.SLASH);
		
		String filePath = SignatureUtil.saveAsPdfUpgrade(sb.toString(), fileEntryId);
		
		log.info("===filePath===completeDongDauHSM===" + filePath);
		
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userId);
		UserSign vanThuSign = null;
		
		List<UserSign> userSigns = UserSignLocalServiceUtil.getByPortCode(userSign.getPortcode(), -1, -1);
		for(UserSign uSign : userSigns) {
			if(uSign.getFilecondauid() > 0) {
				// van thu
				vanThuSign = uSign;
				break;
			}
		}
		
		InputStream imageInputStream = getImageInputStream(vanThuSign, 2);
		
		Certificate cert = getCertificate(vanThuSign, true);
		
		String signedFile = kySoHSM(filePath, cert, imageInputStream, 2, messageType);
		
		return signedFile;
	}

	public static String makeReportFille(ResourceRequest resourceRequest, long documentName, String tieuDe,
			int actionType) throws SystemException, Exception, PortalException {
		String nanoTimePDF = ParamUtil.getString(resourceRequest, "nanoTimePDF");
		String messageType = ParamUtil.getString(resourceRequest, PageType.MESSAGE_TYPE);
		String realPath = ReportUtils.getTemplateReportFilePath(resourceRequest);
		String urlFile = realPath + "export/" + nanoTimePDF;
		
		if (MessageType.GIAY_PHEP_QUA_CANH == ConvertUtil.convertToLong(messageType)) {
			urlFile = urlFile + "_" + ReportConstant.PERMISSION_FOR_TRANSIT_EXPORT;
		} else if (MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH == ConvertUtil.convertToLong(messageType)) {
			urlFile = urlFile + "_" + ReportConstant.ISSUE_PORT_CLEARANCE_EXPORT;
		} else if (MessageType.GIAY_PHEP_ROI_CANG_CHO_TAU_DEN == ConvertUtil.convertToLong(messageType)) {
			urlFile = urlFile + "_" + ReportConstant.PTTND_PORTCLEARANCE_EXPORT;
		} else if (MessageType.PTTND_GIAY_PHEP_VAO_CANG == ConvertUtil.convertToLong(messageType)) {
			urlFile = urlFile + "_" + ReportConstant.PTTND_ACCEPTANCE_EXPORT;
		} else if (MessageType.LENH_DIEU_DONG == ConvertUtil.convertToLong(messageType)) {
			urlFile = urlFile + "_" + ReportConstant.SHIFTING_ORDER_EXPORT;
		}
		
		return urlFile;

	}

	public static Certificate getCertificateByPath(String path)
			throws CertificateException, FileNotFoundException, URISyntaxException {
		return getCertificateByInputStream(new FileInputStream(new File(path)));
	}

	public static Certificate getCertificateByURL(String url)
			throws CertificateException, FileNotFoundException, URISyntaxException {
		return getCertificateByInputStream(new FileInputStream(new File(new URI(url))));
	}

	public static Certificate getCertificateByInputStream(InputStream inputStream)
			throws CertificateException, FileNotFoundException, URISyntaxException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");

		Certificate cert = cf.generateCertificate(inputStream);

		return cert;
	}

	public static ServerSigner getServerSigner(String fullPath, Certificate cert, String imageBase64) {
		ServerSigner signer = new ServerSigner(fullPath, cert);
		signer.setSignatureGraphic(imageBase64);
		// Sonvh 20170718 comment truong hop khong hien thi anh dau.
		// signer.setSignatureAppearance(PdfSignatureAppearance.RenderingMode.GRAPHIC_AND_DESCRIPTION);
		return signer;
	}
	
	public static vgca.hsmsigner.ServerSigner getServerSignerHSM(String fullPath, Certificate cert, byte[] imageBytes) {
		vgca.hsmsigner.ServerSigner signer = new vgca.hsmsigner.ServerSigner(fullPath, null, cert);
		signer.setSignServerUrl("http://cms.ca.gov.vn/Pkcs1Signer.asmx");
		signer.setSignatureGraphic(imageBytes);
		signer.setSignatureAppearance(PdfSignatureAppearance.RenderingMode.GRAPHIC_AND_DESCRIPTION);
		signer.setTsaUrl("http://tsa.ca.gov.vn");
		
		return signer;
	}
	
	public static vgca.hsmsigner.ServerSigner getServerSignerHSM(String fullPath, Certificate cert, InputStream imageIs) 
			throws IOException {
		byte[] bytes = FileUtil.getBytes(imageIs);
		
		return getServerSignerHSM(fullPath, cert, bytes);
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
	
	public static InputStream getSignatureImageInputStream(String fullPath) {

		InputStream is = null;

		try {
			is = new FileInputStream(new File(fullPath));
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return is;
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

	public static BufferedImage getImageByInputStream(InputStream is) throws IOException, URISyntaxException {
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
		InputStream is = null;
		OutputStream os = null;
		String imagePath = StringPool.BLANK;
		try {

			DLFileEntry fileEntry = getFileEntry(fileId);
			if (fileEntry == null)
				return null;
			is = fileEntry.getContentStream();
			imagePath = dest + "/" + fileEntry.getTitle();

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

	public static String saveAsPdfUpgrade(String dest, long fileId) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		String imagePath = StringPool.BLANK;
		try {
			DLFileEntry fileEntry = getFileEntry(fileId);
			if (fileEntry == null)
				return null;
			is = fileEntry.getContentStream();
			imagePath = dest + fileEntry.getTitle();

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

	public static String saveAsImage(String strURL, String dest, String email, String ext, long fileId)
			throws IOException {
		InputStream is = null;
		OutputStream os = null;
		String imagePath = StringPool.BLANK;
		try {
			is = getInputStreamByFileEntryId(fileId);
			imagePath = dest + email + "." + ext;

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

		try {
			long userId = ConfigurationManager.getLongProp("id_admin", 10196);
			User user = UserLocalServiceUtil.getUserById(userId);

			fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEnTryId);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return fileEntry;
	}

	public static InputStream getInputStreamByFileEntryId(long fileEnTryId) {
		InputStream inputStream = null;
		try {
			long userId = ConfigurationManager.getLongProp("id_admin", 10196);
			User user = UserLocalServiceUtil.getUserById(userId);

			DLFileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEnTryId);

			inputStream = fileEntry.getContentStream();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return inputStream;
	}

	
}
