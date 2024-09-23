//package vn.gt.portlet.document;
//
//import java.io.BufferedInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import vn.gt.portlet.kehoach.IDocumentStorage_BAK;
//import vn.gt.utils.config.ConfigurationManager;
//import vn.gt.utils.document.ResultUpload;
//
//import com.fds.nsw.kernel.exception.PortalException;
//import com.fds.nsw.kernel.exception.SystemException;
//
//
//import com.fds.nsw.liferay.model.User;
//
//
//import com.fds.nsw.liferay.service.UserLocalServiceUtil;
//import com.fds.nsw.liferay.model.DLFileEntry;
//import com.fds.nsw.liferay.service.DLFileEntryLocalServiceUtil;
//
//@Deprecated
//public class DocumentUtils_BAK {
//
//	static Log log = LogFactoryUtil.getLog(DocumentUtils_BAK.class);
//
//	public static long uploadTaiLieu(byte[] b, long userIdUpload, String formatFile) {
//		String tenUpload = "giao_thong" + System.nanoTime();
//		IDocumentStorage_BAK iDocumentStorage = new DocumentStorageImpl_BAK();
//		ResultUpload upload = iDocumentStorage.upload(userIdUpload, b, tenUpload + parseString(formatFile));
//		if (upload != null && upload.getCode().compareTo(DocumentStorageImpl_BAK.UPLOAD_OK) == 0) {
//			return upload.getFileId();
//		}
//		return 0;
//	}
//
//	//
//	// private static TaiLieuDinhKem createTaiLieuDinhKem(ResultUpload upload,
//	// long userId, String fileName, long thongTinKeHoachId,long thongTinDuAnId,
//	// ActionRequest resourceRequest, String tenUpload) {
//	// TaiLieuDinhKem taiLieuDinhKem = null;
//	// try {
//	// taiLieuDinhKem = new TaiLieuDinhKem();
//	// taiLieuDinhKem.setFileId(upload.getFileId());
//	// taiLieuDinhKem.setNguoiUpload(PortalUtil.getUser(resourceRequest).getEmailAddress());
//	// taiLieuDinhKem.setNgayUpload(new Date());
//	// taiLieuDinhKem.setTenTaiLieu(fileName);
//	// taiLieuDinhKem.setThongTinDuAnId(thongTinDuAnId);
//	// taiLieuDinhKem.setThongTinKeHoachId(thongTinKeHoachId);
//	// taiLieuDinhKem.setTenUpload(tenUpload);
//	//
//	// taiLieuDinhKem.setUserId(userId);
//	// taiLieuDinhKem.setXoa(0);// (0: Khong xoa, 1: Xoa)
//	//
//	// return TaiLieuDinhKemLocalServiceUtil.addTaiLieuDinhKem(taiLieuDinhKem);
//	// } catch (Exception e) {
//	// log.error(e.getMessage());
//	// }
//	// return taiLieuDinhKem;
//	// }
//
//	public static byte[] getByteFromInputStream(InputStream inputStream) {
//		if (inputStream != null) {
//			try {
//				BufferedInputStream byteArrayInputStream = new BufferedInputStream(inputStream);
//				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//				int data = byteArrayInputStream.read();
//				while (data != -1) {
//					byteArrayOutputStream.write(data);
//					data = byteArrayInputStream.read();
//				}
//				return byteArrayOutputStream.toByteArray();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		return null;
//	}
//
//	public static String getLinkDownloadFromNoiLuuTruTaiLieuId(long noiLuuTruTaiLieuId) {
//
//		// id cua user test@liferaylcom
//		long userId = ConfigurationManager.getLongProp("id_admin", 10196);
//		User user = null;
//		try {
//			user = UserLocalServiceUtil.getUserById(userId);
//		} catch (PortalException e) {
//			e.printStackTrace();
//		} catch (SystemException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			PermissionThreadLocal.setPermissionChecker(PermissionCheckerFactoryUtil.create(user, true));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		IDocumentStorage_BAK iDocumentStorage = new DocumentStorageImpl_BAK();
//		return iDocumentStorage.getURLById(noiLuuTruTaiLieuId);
//	}
//
//	public static String getPathPhysicalLocationByFileEntryId(long noiLuuTruTaiLieuId) throws SystemException {
//		// id cua user test@liferaylcom
//		long userId = ConfigurationManager.getLongProp("id_admin", 10196);
//		User user = null;
//		try {
//			user = UserLocalServiceUtil.getUserById(userId);
//		} catch (PortalException e) {
//			e.printStackTrace();
//		} catch (SystemException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			PermissionThreadLocal.setPermissionChecker(PermissionCheckerFactoryUtil.create(user, true));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//
//		DLFileEntry fetchDLFileEntry = null;
//		try {
//			fetchDLFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(noiLuuTruTaiLieuId);
//		} catch (Exception e) {
//		}
//
//		/** /companyId/folderId/numericFileEntryName/versionNumber */
//		String pathPysical = "/opt/liferay/data/document_library/";
//		if (fetchDLFileEntry != null) {
//			pathPysical = "/opt/liferay/data/document_library/" + fetchDLFileEntry.getCompanyId() + "/" + fetchDLFileEntry.getFolderId();
//		}
//
//		return pathPysical;
//	}
//
//	public static String getFileNameURLFile(String fileURL) throws IOException {
//		URL url = new URL(fileURL);
//		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//		int responseCode = httpConn.getResponseCode();
//		String fileName = "";
//		// always check HTTP response code first
//		if (responseCode == HttpURLConnection.HTTP_OK) {
//			String disposition = httpConn.getHeaderField("Content-Disposition");
//
//			if (disposition != null) {
//				// extracts file name from header field
//				int index = disposition.indexOf("filename=");
//				if (index > 0) {
//					fileName = disposition.substring(index + 10, disposition.length() - 1);
//				}
//			} else {
//				// extracts file name from URL
//				fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
//			}
//		} else {
//			log.info("No file to download. Server replied HTTP code: " + responseCode);
//		}
//		httpConn.disconnect();
//		return fileName;
//	}
//
//	public static void writeData(String data, String tenFileDinhKemCaMoRong) {
//		try {
//			FileWriter fileWriter = new FileWriter("/opt/liferay/data_dinhkem/" + System.currentTimeMillis()
//					+ tenFileDinhKemCaMoRong.replaceAll(".pdf", ".txt"));
//			fileWriter.write(data);
//			fileWriter.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static String parseString(String formatFile) {
//		try {
//			if (formatFile != null && formatFile.trim().length() > 0) {
//				String extendFile = formatFile.substring(formatFile.trim().lastIndexOf("."), formatFile.trim().length());
//				if (extendFile.compareToIgnoreCase(".") == 0) {
//					extendFile = ".pdf";
//				}
//				return extendFile;
//			}
//		} catch (Exception es) {
//			es.printStackTrace();
//		}
//		log.info(formatFile + "=old===========formatFile========" + formatFile);
//
//		return formatFile;
//	}
//
//}
