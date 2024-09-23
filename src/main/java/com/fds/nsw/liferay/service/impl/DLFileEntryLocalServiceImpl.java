package com.fds.nsw.liferay.service.impl;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.liferay.core.ServiceContext;
import com.fds.nsw.liferay.file.DLAppUtil;
import com.fds.nsw.liferay.file.DLFileEntryConstants;
import com.fds.nsw.liferay.file.DLFolderConstants;
import com.fds.nsw.liferay.file.DLStoreUtil;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.persistence.DLFileEntryPersistenceImpl;
import com.fds.nsw.liferay.service.persistence.DLFolderPersistenceImpl;
import com.fds.nsw.liferay.service.persistence.UserPersistenceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class DLFileEntryLocalServiceImpl {
    @Autowired
    DLFileEntryPersistenceImpl persistence;

    @Autowired
    DLFolderPersistenceImpl dlFolderPersistence;

    @Autowired
    DLFolderLocalServiceImpl dlFolderLocalService;

    @Autowired
    UserPersistenceImpl userPersistence;

    public DLFileEntry getFileEntry(long fileEntryId)
            throws PortalException, SystemException {

        return  persistence.findByPrimaryKey(
                fileEntryId);
    }

    public DLFileEntry getDLFileEntry(long fileEntryId)
            throws PortalException, SystemException {

        return  persistence.findByPrimaryKey(
                fileEntryId);
    }


    public void remove(DLFileEntry dlFileEntry) {

        try {
            persistence.remove(dlFileEntry);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }

    public DLFileEntry addFileEntry(
            long userId, long groupId, long repositoryId, long folderId,
            String sourceFileName, String mimeType, String title,
            String description, String changeLog, long fileEntryTypeId, File file, InputStream is, long size,
            ServiceContext serviceContext)
            throws PortalException, SystemException, NoSuchFileException {

        if (Validator.isNull(title)) {
            if (size == 0) {
                throw new PortalException();
            }
            else {
                title = sourceFileName;
            }
        }

        // File entry

        User user = userPersistence.findByPrimaryKey(userId);
        folderId = dlFolderLocalService.getFolderId(
                user.getCompanyId(), folderId);
        String name = String.valueOf(
                CounterLocalServiceUtil.increment(DLFileEntry.class.getName()));
        String extension = DLAppUtil.getExtension(title, sourceFileName);
        Date now = new Date();

        long fileEntryId = CounterLocalServiceUtil.increment(DLFileEntry.class.getName());

        DLFileEntry dlFileEntry = persistence.create(fileEntryId);

        dlFileEntry.setUuid(serviceContext.getUuid());
        dlFileEntry.setGroupId(groupId);
        dlFileEntry.setCompanyId(user.getCompanyId());
        dlFileEntry.setUserId(user.getUserId());
        dlFileEntry.setUserName(user.getFullName());
        dlFileEntry.setVersionUserId(user.getUserId());
        dlFileEntry.setVersionUserName(user.getFullName());
        dlFileEntry.setCreateDate(now);
        dlFileEntry.setModifiedDate(now);
        dlFileEntry.setRepositoryId(repositoryId);
        dlFileEntry.setFolderId(folderId);
        dlFileEntry.setName(name);
        dlFileEntry.setExtension(extension);
        dlFileEntry.setMimeType(mimeType);
        dlFileEntry.setTitle(title);
        dlFileEntry.setDescription(description);
        dlFileEntry.setVersion(DLFileEntryConstants.VERSION_DEFAULT);
        dlFileEntry.setSize(size);
        dlFileEntry.setReadCount(DLFileEntryConstants.DEFAULT_READ_COUNT);

        persistence.update(dlFileEntry, false);


        // Folder

        if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
            dlFolderLocalService.updateLastPostDate(
                    dlFileEntry.getFolderId(), dlFileEntry.getModifiedDate());
        }

        // File
        long dataRepositoryId = DLFolderConstants.getDataRepositoryId(
                dlFileEntry.getGroupId(), dlFileEntry.getFolderId());
        if (file != null) {
            DLStoreUtil.addFile(
                    user.getCompanyId(), dataRepositoryId, name,
                    false, file);
        }
        else {
            DLStoreUtil.addFile(
                    user.getCompanyId(), dataRepositoryId, name,
                    false, is);
        }

        return dlFileEntry;
    }
}
