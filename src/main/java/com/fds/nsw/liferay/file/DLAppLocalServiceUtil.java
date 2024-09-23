package com.fds.nsw.liferay.file;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.ServiceContext;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.model.DLFolder;
import com.fds.nsw.liferay.service.DLFileEntryLocalServiceUtil;
import com.fds.nsw.liferay.service.DLFolderLocalServiceUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

@Slf4j
public class DLAppLocalServiceUtil {
    public static DLFileEntry getFileEntry(
            long fileEntryId) {
        try {
            return DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static DLFileEntry addFileEntry(
            long userId, long repositoryId, long folderId,
            String sourceFileName, String mimeType, String title,
            String description, String changeLog, byte[] bytes,
            ServiceContext serviceContext)
            throws PortalException,
            SystemException{

        File file = null;

        try {
            if ((bytes != null) && (bytes.length > 0)) {
                file = FileUtil.createTempFile(bytes);
            }

            if ((file == null) || !file.exists() || (file.length() == 0)) {
                return null;
            }

            long size = 0;

            if (file != null) {
                size = file.length();
            }


            mimeType = DLAppUtil.getMimeType(
                    sourceFileName, mimeType, title, file, null);
            long groupId = repositoryId;

            return DLFileEntryLocalServiceUtil.addFileEntry(
                    userId, groupId, repositoryId, folderId,
                    sourceFileName, mimeType, title, description, changeLog,
                    0, file, null, size, serviceContext);
        }
        catch (IOException ioe) {
            throw new SystemException("Unable to write temporary file", ioe);
        }
        finally {
            FileUtil.delete(file);
        }
    }

    public static DLFileEntry addFileEntry(
            long userId, long repositoryId, long folderId,
            String sourceFileName, String mimeType,
            String title, String description,
            String changeLog, File file,
            ServiceContext serviceContext) throws SystemException, NoSuchFileException, PortalException {
        if ((file == null) || !file.exists() || (file.length() == 0)) {
            return null;
        }
        mimeType = DLAppUtil.getMimeType(
                sourceFileName, mimeType, title, file, null);
        long size = 0;

        if (file != null) {
            size = file.length();
        }
        long groupId = repositoryId;

        return DLFileEntryLocalServiceUtil.addFileEntry(
                userId, groupId, repositoryId, folderId,
                sourceFileName, mimeType, title, description, changeLog,
                0, file, null, size, serviceContext);

    }

    public static DLFolder getFolder( long repositoryId, long parentFolderId, String name) throws SystemException, PortalException {
        return DLFolderLocalServiceUtil.getFolder(repositoryId, parentFolderId, name);
    }

    public static DLFolder addFolder(
            long userId, long repositoryId, long parentFolderId,
            String name, String description,
            ServiceContext serviceContext) throws SystemException, PortalException {
        return DLFolderLocalServiceUtil.addFolder( userId,  repositoryId,  repositoryId,  false,
         parentFolderId,  name,
                 description,
                 serviceContext);
    }

    public static void deleteFileEntry(long fileEntryId) {
        try {
            DLFileEntry dlFileEntry= DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
            DLFileEntryLocalServiceUtil.remove(dlFileEntry);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
    }




}
