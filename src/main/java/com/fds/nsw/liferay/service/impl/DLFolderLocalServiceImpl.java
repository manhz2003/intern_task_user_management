package com.fds.nsw.liferay.service.impl;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.liferay.core.ServiceContext;
import com.fds.nsw.liferay.file.DLFolderConstants;
import com.fds.nsw.liferay.model.DLFolder;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.persistence.DLFolderPersistenceImpl;
import com.fds.nsw.liferay.service.persistence.UserPersistenceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class DLFolderLocalServiceImpl {
    @Autowired
    DLFolderPersistenceImpl persistence;

    @Autowired
    UserPersistenceImpl userPersistence;

    public long getFolderId(long companyId, long folderId)
            throws SystemException {

        if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

            // Ensure folder exists and belongs to the proper company

            DLFolder dlFolder = persistence.fetchByPrimaryKey(folderId);

            if ((dlFolder == null) || (companyId != dlFolder.getCompanyId())) {
                folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
            }
        }

        return folderId;
    }

    protected long getParentFolderId(long groupId, long parentFolderId)
            throws SystemException {

        if (parentFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
            DLFolder parentDLFolder = persistence.fetchByPrimaryKey(
                    parentFolderId);

            if ((parentDLFolder == null) ||
                    (groupId != parentDLFolder.getGroupId())) {

                parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
            }
        }

        return parentFolderId;
    }

    public DLFolder getFolder(long groupId, long parentFolderId, String name)
            throws PortalException, SystemException {

        return persistence.findByG_P_N(groupId, parentFolderId, name);
    }

    public DLFolder getDLFolder(long folderId)
            throws PortalException, SystemException {
        return persistence.findByPrimaryKey(folderId);
    }

    public void updateLastPostDate(long folderId, Date lastPostDate)
            throws PortalException, SystemException {

        DLFolder dlFolder = persistence.findByPrimaryKey(folderId);

        dlFolder.setLastPostDate(lastPostDate);

        persistence.update(dlFolder, false);
    }

    public DLFolder addFolder(
            long userId, long groupId, long repositoryId, boolean mountPoint,
            long parentFolderId, String name, String description,
            ServiceContext serviceContext)
            throws PortalException, SystemException {

        // Folder

        User user = userPersistence.findByPrimaryKey(userId);
        parentFolderId = getParentFolderId(groupId, parentFolderId);
        Date now = new Date();


        long folderId = CounterLocalServiceUtil.increment(DLFolder.class.getName());

        DLFolder dlFolder = persistence.create(folderId);

        dlFolder.setUuid(serviceContext.getUuid());
        dlFolder.setGroupId(groupId);
        dlFolder.setCompanyId(user.getCompanyId());
        dlFolder.setUserId(user.getUserId());
        dlFolder.setCreateDate(now);
        dlFolder.setModifiedDate(now);
        dlFolder.setRepositoryId(repositoryId);
        dlFolder.setMountPoint(!mountPoint ? 0 : 1);
        dlFolder.setParentFolderId(parentFolderId);
        dlFolder.setName(name);
        dlFolder.setDescription(description);
        dlFolder.setOverrideFileEntryTypes(0);

        persistence.update(dlFolder, false);


        // Parent folder

        if (parentFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
            DLFolder parentDLFolder = persistence.findByPrimaryKey(
                    parentFolderId);

            parentDLFolder.setLastPostDate(now);

            persistence.update(parentDLFolder, false);
        }

        return dlFolder;
    }

}
