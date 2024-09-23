package com.fds.nsw.liferay.service;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.ServiceContext;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.service.impl.DLFileEntryLocalServiceImpl;
import com.fds.nsw.liferay.service.impl.DLFolderLocalServiceImpl;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;

@Component
public class DLFileEntryLocalServiceUtil {

    public DLFileEntryLocalServiceUtil(DLFileEntryLocalServiceImpl service) {
        DLFileEntryLocalServiceUtil._service = service;
    }
    public static DLFileEntry addFileEntry(
            long userId, long groupId, long repositoryId, long folderId,
            String sourceFileName, String mimeType, String title,
            String description, String changeLog, long fileEntryTypeId, File file, InputStream is, long size,
            ServiceContext serviceContext)
            throws PortalException, SystemException, NoSuchFileException {
        return getService()
                .addFileEntry(userId, groupId, repositoryId, folderId,
                        sourceFileName, mimeType, title, description, changeLog,
                        fileEntryTypeId, file, is, size, serviceContext);
    }

    public static DLFileEntry getFileEntry(long fileEntryId) throws SystemException, PortalException {
        return getService().getFileEntry(fileEntryId);
    }

    public static DLFileEntry getDLFileEntry(long fileEntryId) throws SystemException, PortalException {
        return getService().getDLFileEntry(fileEntryId);
    }

    public static void remove(DLFileEntry dlFileEntry) {
        getService().remove(dlFileEntry);
    }


    public static DLFileEntryLocalServiceImpl getService() {

        return _service;
    }

    private static DLFileEntryLocalServiceImpl _service;
    
}
