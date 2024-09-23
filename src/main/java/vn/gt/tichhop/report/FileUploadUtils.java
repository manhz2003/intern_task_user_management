package vn.gt.tichhop.report;

import java.io.File;
import java.util.Calendar;
import java.util.Date;


import com.fds.nsw.liferay.core.ServiceContext;
import com.fds.nsw.liferay.file.FileUtil;
import com.fds.nsw.liferay.file.MimeTypesUtil;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.model.DLFolder;
import com.fds.nsw.liferay.file.DLAppLocalServiceUtil;

import lombok.extern.slf4j.Slf4j;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;

@Slf4j
public class FileUploadUtils
 {

	public static DLFileEntry uploadFile(long userId, long groupId, long fileEntryId, File file, String sourceFileName,
										 String fileType, String destination, ServiceContext serviceContext) throws Exception {

		DLFileEntry fileEntry = null;

		if (file != null && Validator.isNotNull(sourceFileName)) {

			if (Validator.isNull(fileType)) {
				fileType = MimeTypesUtil.getContentType(sourceFileName);
			}

			String title = getFileName(sourceFileName);

//			serviceContext.setAddGroupPermissions(true);
//			serviceContext.setAddGuestPermissions(true);

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(new Date());

			if (destination == null) {
				destination = StringPool.BLANK;
			}

			destination += calendar.get(Calendar.YEAR) + StringPool.SLASH;
			destination += calendar.get(Calendar.MONTH) + StringPool.SLASH;
			destination += calendar.get(Calendar.DAY_OF_MONTH);
			System.out.println("FileUploadUtils.uploadFile()"+destination);
			DLFolder dlFolder = DLFolderUtil.getTargetFolder(userId, groupId, groupId, false, 0, destination,
					StringPool.BLANK, false, serviceContext);
			System.out.println("FileUploadUtils.uploadFile()"+dlFolder);
			User user = UserLocalServiceUtil.getUserById(userId);
			System.out.println("FileUploadUtils.uploadFile()"+user);
//			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user, Boolean.TRUE);
//			PermissionThreadLocal.setPermissionChecker(checker);
			System.out.println("FileUploadUtils.uploadFile()"+fileEntryId);
//			if (fileEntryId > 0) {
//				fileEntry = DLAppLocalServiceUtil.updateFileEntry(userId, fileEntryId, sourceFileName, fileType, title,
//						title, title, true, file, serviceContext);
//			} else {
//				fileEntry = DLAppLocalServiceUtil.addFileEntry(userId, groupId, dlFolder.getFolderId(), title, fileType,
//						title, title, StringPool.BLANK, file, serviceContext);
//			}
			fileEntry = DLAppLocalServiceUtil.addFileEntry(userId, groupId, dlFolder.getFolderId(), title, fileType,
					title, title, StringPool.BLANK, file, serviceContext);

		}

		return fileEntry;
	}

	private static String getFileName(String sourceFileName) {
		String ext = FileUtil.getExtension(sourceFileName);
		
		return System.currentTimeMillis() + "." + ext;
	}
	
}
