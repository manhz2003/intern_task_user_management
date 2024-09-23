///**
// *
// */
//package vn.gt.portlet.document;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Calendar;
//
//import com.fds.nsw.nghiepvu.model.UserPort;
//import vn.gt.dao.common.service.UserPortLocalServiceUtil;
//import com.fds.nsw.nghiepvu.model.DmMaritime;
//import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
//import vn.gt.portlet.kehoach.IDocumentStorage_BAK;
//import vn.gt.utils.config.ConfigurationManager;
//import vn.gt.utils.document.ResultUpload;
//
//import com.fds.nsw.kernel.exception.PortalException;
//import com.fds.nsw.kernel.exception.SystemException;
//
//
//import com.fds.nsw.liferay.model.DLFileEntry;
//import com.fds.nsw.liferay.model.Folder;
//import com.fds.nsw.liferay.file.FileUtil;
//import com.liferay.portal.kernel.util.MimeTypesUtil;
//import com.fds.flex.common.utility.string.StringPool;
//import com.fds.flex.common.ultility.Validator;
//import com.fds.nsw.liferay.model.User;
//import com.liferay.portal.security.auth.PrincipalThreadLocal;
//
//
//
//import com.fds.nsw.liferay.core.ServiceContext;
//import com.fds.nsw.liferay.service.UserLocalServiceUtil;
//import com.liferay.portlet.documentlibrary.DuplicateFileException;
//import com.liferay.portlet.documentlibrary.model.DLFolder;
//import com.fds.nsw.liferay.file.DLAppLocalServiceUtil;
//import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
//import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
//
///**
// * @author win_64
// *
// */
//@Deprecated
//public class DocumentStorageImpl_BAK implements IDocumentStorage_BAK {
//
//
//	public static final String UPLOAD_MYFOLDER_NOT_CREATED = "MYFOLDER_NOT_CREATED";
//	public final static String FOLDER_DEFAULT_UPLOAD_NOP_HOSO = "NOPHOSO";
//	private String CMON_DOCUMENT_FOLDER_ID = "CMON_DOCUMENT_FOLDER_ID";
//	public static final String UPLOAD_SYSTEM = "SYSTEM";
//	public static final String UPLOAD_FILE_TYPE = "FILE_TYPE";
//	public static final String UPLOAD_LIMITSIZE = "LIMITSIZE";
//	public static final String UPLOAD_FOLDER_NOT_FOUND = "FOLDER_NOT_FOUND";
//	public static final String UPLOAD_OK = "OK";
//
//	private String[] getFolderNopHoSo(Long userId) throws SystemException {
//		// String[] folders = new String[2];
//		// folders[0] = FOLDER_DEFAULT_UPLOAD_NOP_HOSO;
//		// folders[1] = userId.toString();
//
//		// String[] folders = new String[5];
//		String[] folders = new String[4];
//		Calendar calendar = Calendar.getInstance();
//		folders[0] = FOLDER_DEFAULT_UPLOAD_NOP_HOSO;
//		folders[1] = "" + calendar.get(Calendar.YEAR);
//		folders[2] = "" + (calendar.get(Calendar.MONTH) + 1);
//		UserPort uPort = UserPortLocalServiceUtil.findByUserId(userId);
//		DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.fetchDmMaritime(Integer.valueOf(uPort.getPortCode()));
//		folders[3] = Validator.isNotNull(dmMaritime)?dmMaritime.getMaritimeReference():String.valueOf(userId);
////		folders[3] = "" + userId.toString();
//
//		return folders;
//
//		// return folders;
//	}
//
//	public ResultUpload upload(Long userId, byte[] b, String _fileName) {
//		log.debug("======12344556666666==folderIdfolderId===45646456447474574=====");
//		long limit = 999999999;
//		Long folderId = ConfigurationManager.getLongProp(CMON_DOCUMENT_FOLDER_ID, 50104);
//		// folderId =11605L; //local-VinhNguyen
//		String fileName = _fileName;
//		if (_fileName.split(".").length > 2) {
//			fileName = _fileName.substring(0, _fileName.lastIndexOf("."));
//		}
//		log.debug(fileName + "========folderIdfolderId========" + folderId);
//
//		DLFolder folder = null;
//		ResultUpload result = new ResultUpload();
//		try {
//			folder = DLFolderLocalServiceUtil.getDLFolder(folderId);
//			if (folder == null) {
//				result.setCode(UPLOAD_FOLDER_NOT_FOUND);
//				return result;
//			}
//			Long repositoryId = folder.getRepositoryId();
//			User admin = getAdministrator();
//			ServiceContext serviceContext = getServiceContext(admin);
//			serviceContext.setAddGroupPermissions(true);
//			serviceContext.setAddGuestPermissions(true);
//			// ResourceLocalServiceUtil.addModelResources(AuditedModel
//			// auditedModel, ServiceContext serviceContext)
//			// serviceContext.setAddGuestPermissions(true);
//			// serviceContext.setAddGroupPermissions(true);
//			//
//			Folder myFolder = addFolders(userId, serviceContext, repositoryId, folderId, getFolderNopHoSo(userId));
//			if (myFolder == null) {
//				result.setCode(UPLOAD_MYFOLDER_NOT_CREATED);
//				return result;
//			}
//			log.debug("====b.add file in folder:" + myFolder.getName());
//			return addFile(userId, serviceContext, repositoryId, b, myFolder.getFolderId(), fileName, limit);
//
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			result.setCode(UPLOAD_SYSTEM);
//		}
//
//		return result;
//	}
//
//	private ServiceContext getServiceContext(User admin) throws Exception {
//		PrincipalThreadLocal.setName(admin.getUserId());
//		PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(admin, false);
//		PermissionThreadLocal.setPermissionChecker(permissionChecker);
//
//		ServiceContext serviceContext = new ServiceContext();
//		serviceContext.setAddGroupPermissions(true);
//		serviceContext.setAddGuestPermissions(true);
//
//		return serviceContext;
//	}
//
//	// private ServiceContext getServiceContext(User admin)
//	// throws Exception {
//	// ServiceContext serviceContext = new ServiceContext();
//	// serviceContext.setAddGroupPermissions(true);
//	// serviceContext.setAddGuestPermissions(true);
//	// return serviceContext;
//	// }
//
//	private ResultUpload addFile(long userId, ServiceContext serviceContext, Long repositoryId, byte[] b, Long folderId, String fileName, long limit)
//			throws IOException, PortalException, SystemException {
//		ResultUpload result = new ResultUpload();
//		java.io.File tempFile = FileUtil.createTempFile(fileName);
//		// log.debug("=======addFile======fileName=====" + fileName +
//		// "===bbbbb==" + b);
//		FileOutputStream fos = new FileOutputStream(tempFile);
//		fos.write(b);
//		fos.flush();
//		fos.close();
//		fos = null;
//
//		FileInputStream fis = new FileInputStream(tempFile);
//
//		String contentType = MimeTypesUtil.getContentType(fis, tempFile.getName());
//		fis.close();
//		fis = null;
//
//		fis = new FileInputStream(tempFile);
//		long size = tempFile.length();
//		if (limit > 0) {
//			long limitsize = (long) ((size / (1024 * 1024)));
//			if (limitsize > limit) {
//				result.setCode(UPLOAD_LIMITSIZE);
//				tempFile.delete();
//				return result;
//			} else if (limitsize == limit) {
//				long yes = limitsize * 1024 * 1024;
//				if (yes < size) {
//					result.setCode(UPLOAD_LIMITSIZE);
//					tempFile.delete();
//					return result;
//				}
//			}
//		}
//
//		DLFileEntry fileEntry = addFile(userId, serviceContext, repositoryId, folderId, contentType, b, size, fileName);
//
//		fis.close();
//		fis = null;
//		tempFile.delete();
//		String url = getURL(fileEntry);
//		result.setResult(true);
//		result.setCode(UPLOAD_OK);
//		result.setFileId(fileEntry.getFileEntryId());
//		result.setUrl(url);
//		return result;
//	}
//
//	public void deleteFile(Long documentId) {
//		try {
//			DLAppLocalServiceUtil.deleteFileEntry(documentId);
//		} catch (Exception e) {
//			log.error(e.toString());
//		}
//	}
//
//	private Folder addFolders(long userId, ServiceContext context, long repositoryId, long parentFolderId, String[] folders) {
//
//		if (folders != null && folders.length > 0) {
//			Folder folder = null;
//			for (String folderName : folders) {
//				if (folder != null) {
//					parentFolderId = folder.getFolderId();
//					log.info("vao day addFolders DocumentStorageImpl ");
//				}
//				folder = addFolder(userId, context, repositoryId, parentFolderId, folderName);
//			}
//			return folder;
//		}
//		return null;
//	}
//
//	private Folder addFolder(long userId, ServiceContext context, long repositoryId, long parentFolderId, String name) {
//
//		Folder folder = null;
//		try {
//			folder = DLAppLocalServiceUtil.getFolder(repositoryId, parentFolderId, name);
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//		if (folder == null) {
//			try {
//				folder = DLAppLocalServiceUtil.addFolder(userId, repositoryId, parentFolderId, name, name, context);
//			} catch (Exception e) {
//				log.error(e.getMessage());
//			}
//		}
//
//		return folder;
//	}
//
//	private DLFileEntry addFile(long userId, ServiceContext context, Long repositoryId, Long folderId, String c, byte[] bytes, long size,
//			String fileName) throws PortalException, SystemException {
//
//		DLFileEntry fileEntry = null;
//		try {
//
//			// fileEntry = DLFileEntryLocalServiceUtil.addFileEntry(userId,
//			// groupId, repositoryId, folderId, sourceFileName, mimeType, title,
//			// description, changeLog, fileEntryTypeId, fieldsMap, file, is,
//			// size, serviceContext);
//			fileEntry = DLAppLocalServiceUtil.addFileEntry(userId, repositoryId, folderId, fileName, null, fileName, fileName, fileName, bytes,
//					context);
//			// fileEntry.set
//
//		} catch (Exception e) {
//			try {
//				DLAppServiceUtil.deleteFileEntryByTitle(repositoryId, folderId, fileName);
//
//				fileEntry = DLAppLocalServiceUtil.addFileEntry(userId, repositoryId, folderId, fileName, null, fileName, fileName, fileName,
//						bytes, context);
//			} catch (DuplicateFileException e2) {
//				DLAppServiceUtil.deleteFileEntryByTitle(repositoryId, folderId, fileName);
//
//				fileEntry = DLAppLocalServiceUtil.addFileEntry(userId, repositoryId, folderId, fileName, null, fileName, fileName, fileName,
//						bytes, context);
//			}
//
//		}
//		return fileEntry;
//	}
//
//	private String getURL(DLFileEntry fileEntry) {
//		try {
//			String url = "/documents/" + fileEntry.getGroupId() + StringPool.SLASH + fileEntry.getFolderId() + StringPool.SLASH
//					+ fileEntry.getTitle() + "?version=" + fileEntry.getVersion();
//			return url;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//
//		return "";
//	}
//
//	public String getURLById(Long fileId) {
//		try {
//			return getURL(DLAppLocalServiceUtil.getFileEntry(fileId));
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//		return "";
//	}
//
//	public String getFileName(String fileName, String newName) {
//		String typeFile = getFileType(fileName);
//		fileName = newName + "." + typeFile;
//
//		return fileName;
//
//	}
//
//	private String getFileType(String fileName) {
//		try {
//			String type = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//			return type;
//		} catch (Exception es) {
//			log.error(es);
//		}
//		return "";
//	}
//
//	private User getAdministrator() {
//		Long userId = ConfigurationManager.getLongProp("id_admin", 10196L);
//		User admin = null;
//		try {
//			admin = UserLocalServiceUtil.fetchUser(userId);
//			return admin;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//		return null;
//	}
//
//	public boolean existFile(Long fileId) {
//		try {
//			DLFileEntry file = DLAppLocalServiceUtil.getFileEntry(fileId);
//			if (file == null || file.getFileEntryId() <= 0) {
//				return false;
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//		return true;
//	}
//}