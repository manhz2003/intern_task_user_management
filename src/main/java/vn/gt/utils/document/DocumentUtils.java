package vn.gt.utils.document;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fds.nsw.liferay.file.FileUtil;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import vn.gt.utils.config.ConfigurationManager;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;




import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.file.DLAppLocalServiceUtil;

import lombok.extern.slf4j.Slf4j;
import com.fds.flex.common.ultility.Validator;

@Slf4j
public class DocumentUtils
 {

	

	public static long uploadTaiLieu(byte[] b, long userIdUpload, String formatFile) {
		String tenUpload = "giao_thong" + System.nanoTime();
		IDocumentStorage iDocumentStorage = new DocumentStorageImpl();
		ResultUpload upload = iDocumentStorage.upload(userIdUpload, b, tenUpload + parseString(formatFile));
		if (upload != null && upload.getCode().compareTo(DocumentStorageImpl.UPLOAD_OK) == 0) {
			return upload.getFileId();
		}
		return 0;
	}
	
	public static boolean existDLFileAbsPath(DLFileEntry fileEntry) {
	    
	    boolean exist = false;
	    
	    if(fileEntry != null) {
			//todo get config from application.properties
//	      String dlFileAbsPath = PropsUtil.get("dl.store.file.system.root.dir") + "/"
//	        + fileEntry.getCompanyId() + "/" + fileEntry.getFolderId()
//	        + "/" + fileEntry.getName() + "/"
//	        + fileEntry.getVersion();
			String dlFileAbsPath ="";
					log.info("--------fileEntry+++++++++" + fileEntry);
	      File file = new File(dlFileAbsPath);
	      
	      exist = file.exists();
	    }
	    
	    return exist;
	}
	
	public static boolean existDLFileAbsPath(long fileEntryId) 
		      throws SystemException {
		    
		    boolean exist = false;
		    
		    if(fileEntryId > 0) {
		      try {
		        DLFileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
		        
		        exist = existDLFileAbsPath(fileEntry);
		      } catch(Exception e) {
		        log.error(e.getMessage());
		      }
		    }
		    
		    return exist;
	}

	//
	// private static TaiLieuDinhKem createTaiLieuDinhKem(ResultUpload upload,
	// long userId, String fileName, long thongTinKeHoachId,long thongTinDuAnId,
	// ActionRequest resourceRequest, String tenUpload) {
	// TaiLieuDinhKem taiLieuDinhKem = null;
	// try {
	// taiLieuDinhKem = new TaiLieuDinhKem();
	// taiLieuDinhKem.setFileId(upload.getFileId());
	// taiLieuDinhKem.setNguoiUpload(PortalUtil.getUser(resourceRequest).getEmailAddress());
	// taiLieuDinhKem.setNgayUpload(new Date());
	// taiLieuDinhKem.setTenTaiLieu(fileName);
	// taiLieuDinhKem.setThongTinDuAnId(thongTinDuAnId);
	// taiLieuDinhKem.setThongTinKeHoachId(thongTinKeHoachId);
	// taiLieuDinhKem.setTenUpload(tenUpload);
	//
	// taiLieuDinhKem.setUserId(userId);
	// taiLieuDinhKem.setXoa(0);// (0: Khong xoa, 1: Xoa)
	//
	// return TaiLieuDinhKemLocalServiceUtil.addTaiLieuDinhKem(taiLieuDinhKem);
	// } catch (Exception e) {
	// log.error(e.getMessage());
	// }
	// return taiLieuDinhKem;
	// }

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
				log.error(e.getMessage());
			}
		}

		return null;
	}

	public static String getLinkDownloadFromNoiLuuTruTaiLieuId(long noiLuuTruTaiLieuId) {

		// id cua user test@liferaylcom
		/*long userId = ConfigurationManager.getLongProp("id_admin", 10196);
		User user = null;
		try {
			user = UserLocalServiceUtil.getUserById(userId);
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}

		try {
			PermissionThreadLocal.setPermissionChecker(PermissionCheckerFactoryUtil.create(user, true));
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		IDocumentStorage iDocumentStorage = new DocumentStorageImpl();
		return iDocumentStorage.getURLById(noiLuuTruTaiLieuId);
	}

	public static String getPathPhysicalLocationByFileEntryId(long noiLuuTruTaiLieuId) throws SystemException {
		// id cua user test@liferaylcom
		long userId = ConfigurationManager.getLongProp("id_admin", 10196);
		User user = null;
		try {
			user = UserLocalServiceUtil.getUserById(userId);
		} catch (PortalException e) {
			log.error(e.getMessage());
		} catch (SystemException e) {
			log.error(e.getMessage());
		}

//		try {
//			PermissionThreadLocal.setPermissionChecker(PermissionCheckerFactoryUtil.create(user, true));
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//
		
		DLFileEntry fetchDLFileEntry = null;
		try {
			//todo add persistent DLFileEntryLocalServiceUtil
//			fetchDLFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(noiLuuTruTaiLieuId);
		} catch (Exception e) {
		}
		
		/** /companyId/folderId/numericFileEntryName/versionNumber */
		String pathPysical = "/opt/liferay/data/document_library/";
		if (fetchDLFileEntry != null) {
			pathPysical = "/opt/liferay/data/document_library/" + fetchDLFileEntry.getCompanyId() + "/" + fetchDLFileEntry.getFolderId();
		}
		
		return pathPysical;
	}

	public static String getFileNameURLFile(String fileURL) throws IOException {
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		int responseCode = httpConn.getResponseCode();
		String fileName = "";
		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String disposition = httpConn.getHeaderField("Content-Disposition");

			if (disposition != null) {
				// extracts file name from header field
				int index = disposition.indexOf("filename=");
				if (index > 0) {
					fileName = disposition.substring(index + 10, disposition.length() - 1);
				}
			} else {
				// extracts file name from URL
				fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
			}
		} else {
			log.info("No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
		return fileName;
	}

	public static String parseString(String formatFile) {
		try {
			if (formatFile != null && formatFile.trim().length() > 0) {
				String extendFile = formatFile.substring(formatFile.trim().lastIndexOf("."), formatFile.trim().length());
				if (extendFile.compareToIgnoreCase(".") == 0) {
					extendFile = ".pdf";
				}
				return extendFile;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info(formatFile + "=old===========formatFile========" + formatFile);

		return formatFile;
	}
	
	public static long uploadFileEntryFromURL(String fileURL) {
		long fileEntryId = 0;
		
		try {
			byte[] bytes = getFileFromURL(fileURL);
		
			fileEntryId = uploadTaiLieu(bytes, 0, ".pdf");
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		
		return fileEntryId;
	}
	
	public static byte[] getFileFromURL(String fileURL) throws IOException {
		
		HttpURLConnection connection = null;
		byte[] bytes = null;
		if(Validator.isNotNull(fileURL)) {
			
			try {
				URL url = new URL(fileURL);
				
				connection = (HttpURLConnection) url.openConnection();
				connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				connection.addRequestProperty("User-Agent", "Mozilla");
				connection.addRequestProperty("Referer", "google.com");
				
				connection.setInstanceFollowRedirects(false);
				connection.setConnectTimeout(5000);	// 5s
				connection.setReadTimeout(5000);	// 5s
				
				int status = connection.getResponseCode();
				
				boolean redirect = false;

				// normally, 3xx is redirect
				if (status != HttpURLConnection.HTTP_OK) {
					if (status == HttpURLConnection.HTTP_MOVED_TEMP
						|| status == HttpURLConnection.HTTP_MOVED_PERM
							|| status == HttpURLConnection.HTTP_SEE_OTHER)
					redirect = true;
				}
				
				if (redirect) {

					// get redirect url from "location" header field
					String newUrl = connection.getHeaderField("Location");

					// get the cookie if need, for login
					String cookies = connection.getHeaderField("Set-Cookie");

					// open the new connnection again
					connection = (HttpURLConnection) new URL(newUrl).openConnection();
					
					connection.setRequestProperty("Cookie", cookies);
					connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
					connection.addRequestProperty("User-Agent", "Mozilla");
					connection.addRequestProperty("Referer", "google.com");
					
					connection.setConnectTimeout(5000);	// 5s
					connection.setReadTimeout(5000);	// 5s
											
					status = connection.getResponseCode();
				}
			
				if(status == HttpURLConnection.HTTP_OK) {
					InputStream is = connection.getInputStream();
					//File file = FileUtil.createTempFile(is);
					//long size = connection.getContentLengthLong();
					//log.info("===fileURL===" + fileURL + "===" + file.getAbsolutePath());
					//log.info("===fileURL===" + fileURL + "===" + size);
					
					bytes = FileUtil.getBytes(is);
					
					//FileUtil.createTempFile(bytes);
				}
			} catch(IOException ioe) {
				throw new IOException(ioe.getMessage());
			}finally{
				connection.disconnect();
			}
		}
		
		return bytes;
	}

}
