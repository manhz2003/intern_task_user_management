/**
 * 
 */
package vn.gt.portlet.document;

import java.io.ByteArrayInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


import com.fds.nsw.liferay.core.*;
import com.fds.nsw.liferay.file.DLAppLocalServiceUtil;
import com.fds.nsw.liferay.file.HttpUtil;
import com.fds.nsw.liferay.file.StreamUtil;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import org.codehaus.plexus.util.Base64;
import vn.gt.utils.config.ConfigurationManager;
import com.fds.nsw.liferay.model.User;

/**
 * @author binhnt
 *
 */
import lombok.extern.slf4j.Slf4j;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;

@Slf4j
public class FileUtils
 {
	
	
	
	// public static void main(String[] args) {
	// // String path = "e:\\MPW_Player_R2.zip";
	// // FileInputStream fileInputStream = null;
	// FileInputStream fileInputStream2 = null;
	// try {
	// // fileInputStream = new FileInputStream(path);
	// // byte[] data1 = getByteFromInputStream(fileInputStream);
	// //
	// // byte[] encoded = Base64.encodeBase64(data1);
	// // String encodedString = new String(encoded);
	// // writeData(encodedString);
	// // System.out.println("==============Hoan thanh============1111");
	// // fileInputStream.close();
	//
	//
	// // Giai ma.
	// fileInputStream2 = new FileInputStream("C:\\base64.txt");
	// byte[] data2 = getByteFromInputStream(fileInputStream2);
	// byte[] data3 = Base64.decodeBase64(data2);
	// writeData(data3, "C:\\base64.pdf");
	// System.out.println("==============Hoan thanh============3333");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	
	public static String encodeBase64(byte[] data) {
		log.info("==encodeBase64==");
		byte[] encoded = Base64.encodeBase64(data);
		String encodedString = new String(encoded);
		
		return encodedString;
	}
	
	public static byte[] decodeBase64(String data) {
		log.info("==decodeBase64==");
		byte[] encoded = Base64.encodeBase64(data.getBytes());
		return encoded;
	}
	
	public static byte[] decodeBase64(byte[] data) {
		byte[] encoded = Base64.encodeBase64(data);
		return encoded;
	}
	
	public static void writeData(String data, String fileName) {
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			fileWriter.write(data);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void writeData(byte[] data, String fileName) {
		try {
			FileOutputStream fileWriter = new FileOutputStream(fileName);
			fileWriter.write(data);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static byte[] getByteFromInputStream(InputStream inputStream) {
		if (inputStream != null) {
			try {
				BufferedInputStream byteArrayInputStream = new BufferedInputStream(inputStream);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				int data = byteArrayInputStream.read();
				while (data != -1) {
					byteArrayOutputStream.write(data);
					data = byteArrayInputStream.read();
				}
				
				return byteArrayOutputStream.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	// Cu khong dung duoc, do dung luong phinh to qua
	public static String getBase64FileContent(String urlFile) {
		InputStream inputStream = null;
		try {
			inputStream = new URL(urlFile).openConnection().getInputStream();
			// inputStream = new FileInputStream(urlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] data1 = FileUtils.getByteFromInputStream(inputStream);
		byte[] encoded = Base64.encodeBase64(data1);
		String encodedString = new String(encoded);
		return encodedString;
	}
	
	public static byte[] getByteFromInputStreamNew(InputStream is)
	{
		if (is != null)
		{
			try {
				int len;
				int size = 1024;
				byte[] buf;

				if (is instanceof ByteArrayInputStream) {
					size = is.available();
					buf = new byte[size];
					len = is.read(buf, 0, size);
				} else {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					buf = new byte[size];
					while ((len = is.read(buf, 0, size)) != -1)
						bos.write(buf, 0, len);
					buf = bos.toByteArray();
				}
				
				return buf;
			}
			catch (Exception e) {
				log.error(e.getMessage());
			} finally {
				StreamUtil.cleanUp(is);
			}
		}

		return null;
	}
	
	// su dung duoc
	public static String getBase64FileContent(long fileEnTryId) {
		String encodedString = "";
		InputStream inputStream = getInputStreamByFileEntryId(fileEnTryId);
		
		if (inputStream != null) {
			byte[] data1 = FileUtils.getByteFromInputStreamNew(inputStream);
			if (data1 != null && data1.length > 0) {
				byte[] encoded = Base64.encodeBase64(data1);
				encodedString = new String(encoded);
			}
		}	
		
		return encodedString;
	}
	
	public static InputStream getInputStreamByFileEntryId(long fileEnTryId) {
		InputStream inputStream = null;
		
		try {
			long userId = ConfigurationManager.getLongProp("id_admin", 10196);
			User user = UserLocalServiceUtil.getUserById(userId);
			
//			PermissionThreadLocal.setPermissionChecker(PermissionCheckerFactoryUtil.create(user, true));
			DLFileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEnTryId);
			
			inputStream = fileEntry.getContentStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inputStream;
	}
	
	public static String getNameByFileEntryId(long fileEnTryId) {
		String name = "";
		
		try {
			long userId = ConfigurationManager.getLongProp("id_admin", 10196);
			User user = UserLocalServiceUtil.getUserById(userId);
			
//			PermissionThreadLocal.setPermissionChecker(PermissionCheckerFactoryUtil.create(user, true));
			DLFileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEnTryId);
			if (fileEntry != null) {
				name = fileEntry.getTitle();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return name;
	}
	
	public static String getFullFileURL(long fileEnTryId) {
		String fullFileURL = "";

		try {
			DLFileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEnTryId);
			
			if (fileEntry != null) {
				//todo get config from application.properties
//				String domainFile = GetterUtil.getString(PropsUtil.get("vn.gt.domain.file"));
				String domainFile = "";

//				if(Validator.isNull(domainFile)) {
//					ServiceContext serviceContext = new ServiceContext();
//
//					if(serviceContext != null) {
//						domainFile = serviceContext.getPortalURL();
//					}
//				}
				log.info("------domainFile---------" + domainFile + "--------fileEnTryId-------"+ fileEnTryId);
				
				StringBuilder sb = new StringBuilder(9);
				
				sb.append(domainFile);
				sb.append("/documents/");
				sb.append(fileEntry.getRepositoryId());
				sb.append(StringPool.SLASH);
				sb.append(fileEntry.getFolderId());
				sb.append(StringPool.SLASH);
				sb.append(HttpUtil.encodeURL(HttpUtil.unescape(fileEntry.getTitle()), true));
				sb.append("?version=");
				sb.append(fileEntry.getVersion());
				
				fullFileURL = sb.toString();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return fullFileURL;
	}
}
