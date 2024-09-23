package vn.gt.tichhop.report;

import com.fds.flex.common.ultility.array.ArrayUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;

import com.fds.nsw.liferay.core.ServiceContext;
import com.fds.nsw.liferay.model.DLFolder;
import com.fds.nsw.liferay.service.DLFolderLocalServiceUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DLFolderUtil {


	public static DLFolder addFolder(long userId, long groupId, long repositoryId, boolean mountPoint,
			long parentFolderId, String name, String description, boolean hidden, ServiceContext serviceContext) {

		DLFolder dlFolder = null;
		try {
			if (hasFolder(groupId, parentFolderId, name)) {
				dlFolder = DLFolderLocalServiceUtil.getFolder(groupId, parentFolderId, name);
			} else {

				dlFolder = DLFolderLocalServiceUtil.addFolder(userId, groupId, repositoryId, mountPoint, parentFolderId,
						name, description, serviceContext);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return dlFolder;
	}

	public static DLFolder getFolder(long userId, long groupId, long repositoryId, boolean mountPoint,
			long parentFolderId, String name, String description, boolean hidden, ServiceContext serviceContext) {

		DLFolder dlFolder = makeFolder(userId, groupId, repositoryId, mountPoint, parentFolderId, name, description,
				hidden, serviceContext);

		return getFolder(userId, groupId, repositoryId, mountPoint, dlFolder.getFolderId(), name, description, hidden,
				serviceContext);

	}

	public static DLFolder getFolder(long groupId, long parentFolderId, String name) {

		DLFolder dlFolder = null;

		try {
			
			dlFolder = DLFolderLocalServiceUtil.getFolder(groupId, parentFolderId, name);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return dlFolder;
	}

	public static DLFolder getTargetFolder(long userId, long groupId, long repositoryId, boolean mountPoint,
			long parentFolderId, String destination, String description, boolean hidden, ServiceContext serviceContext)
			throws Exception {

		DLFolder dlFolder = null;

		String[] folderNames = StringUtil.split(destination, StringPool.SLASH);

		if (folderNames != null && folderNames.length > 0) {
			String name = folderNames[0];
			System.out.println("DlfolderUtil.getTargetFolder()"+name);
			dlFolder = makeFolder(userId, groupId, repositoryId, mountPoint, parentFolderId, name, description, hidden,
					serviceContext);
			System.out.println("DlfolderUtil.getTargetFolder()"+dlFolder);

			folderNames = ArrayUtil.remove(folderNames, name);
			if (folderNames.length > 0) {
				dlFolder = getTargetFolder(userId, groupId, repositoryId, mountPoint, dlFolder.getFolderId(),
						StringUtil.merge(folderNames, StringPool.FORWARD_SLASH), description, hidden, serviceContext);
			}

		}

		return dlFolder;
	}

	public static DLFolder getTargetFolder(long groupId, long parentFolderId, String destination) {

		DLFolder dlFolder = null;

		String[] folderNames = StringUtil.split(destination, StringPool.FORWARD_SLASH);

		if (folderNames != null && folderNames.length > 0) {
			String name = folderNames[0];
			dlFolder = getFolder(groupId, parentFolderId, name);
			folderNames = ArrayUtil.remove(folderNames, name);
			if (folderNames.length > 0) {
				dlFolder = getTargetFolder(groupId, dlFolder.getFolderId(),
						StringUtil.merge(folderNames, StringPool.FORWARD_SLASH));
			}

		}

		return dlFolder;
	}

	public static boolean hasFolder(long groupId, long parentFolderId, String name) {

		boolean result = false;

		DLFolder dlFolder = null;

		try {
			dlFolder = DLFolderLocalServiceUtil.getFolder(groupId, parentFolderId, name);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		result = dlFolder != null ? true : false;

		return result;
	}

	public static DLFolder makeFolder(long userId, long groupId, long repositoryId, boolean mountPoint,
			long parentFolderId, String name, String description, boolean hidden, ServiceContext serviceContext) {

		if (hasFolder(groupId, parentFolderId, name)) {
			return getFolder(groupId, parentFolderId, name);
		} else {
			return addFolder(userId, groupId, repositoryId, mountPoint, parentFolderId, name, description, hidden,
					serviceContext);
		}
	}


}
