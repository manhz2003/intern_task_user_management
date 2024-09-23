package com.fds.nsw.liferay.service;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.ServiceContext;
import com.fds.nsw.liferay.model.DLFolder;
import com.fds.nsw.liferay.service.impl.DLFolderLocalServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DLFolderLocalServiceUtil {
    public DLFolderLocalServiceUtil(DLFolderLocalServiceImpl service) {
        DLFolderLocalServiceUtil._service = service;
    }

    public static DLFolder getDLFolder(
            long folderId)
            throws PortalException,SystemException {
        return getService().getDLFolder(folderId);
    }

    public static DLFolder addFolder(
            long userId, long groupId, long repositoryId, boolean mountPoint,
            long parentFolderId, String name,
            String description,
            ServiceContext serviceContext) throws PortalException, SystemException {
        return getService()
                .addFolder(userId, groupId, repositoryId, mountPoint,
                        parentFolderId, name, description, serviceContext);
    }

    public static DLFolder getFolder(
            long groupId, long parentFolderId, java.lang.String name)
            throws PortalException, SystemException {
        return getService().getFolder(groupId, parentFolderId, name);
    }



    public static void updateLastPostDate(long folderId,
                                          Date lastPostDate)
            throws PortalException, SystemException {
        getService().updateLastPostDate(folderId, lastPostDate);
    }

    public static DLFolderLocalServiceImpl getService() {

        return _service;
    }

    private static DLFolderLocalServiceImpl _service;
    
}
