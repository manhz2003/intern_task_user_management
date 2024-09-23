package com.fds.nsw.liferay.file;

public class DLFolderConstants {

    public static final long DEFAULT_PARENT_FOLDER_ID = 0;


    /**
     * Determine the data repository ID from the group ID and folder ID. The
     * folder ID may be zero, implying that it is the root folder for the given
     * group.
     */
    public static long getDataRepositoryId(long groupId, long folderId) {
        if (folderId != DEFAULT_PARENT_FOLDER_ID) {
            return folderId;
        }
        else {
            return groupId;
        }
    }

    /**
     * Determine the folder ID when no knowledge of it currently exists.
     */
    public static long getFolderId(long groupId, long dataRepositoryId) {
        if (groupId != dataRepositoryId) {
            return dataRepositoryId;
        }
        else {
            return DEFAULT_PARENT_FOLDER_ID;
        }
    }

}
