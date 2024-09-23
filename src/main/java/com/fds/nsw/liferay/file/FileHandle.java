package com.fds.nsw.liferay.file;

import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.model.DLFileEntry;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
public class FileHandle {
    //todo add root dir
    private static File _rootDir = new File("");

    public static InputStream getContentStream(DLFileEntry dlFileEntry) {
        try {
            File companyDir = new File(_rootDir +
                    StringPool.SLASH + dlFileEntry.getCompanyId());

            if (!companyDir.exists()) {
                companyDir.mkdirs();
            }

            File repositoryDir = new File(
                    companyDir + StringPool.SLASH + DLFolderConstants.getDataRepositoryId(dlFileEntry.getGroupId(), dlFileEntry.getFolderId()));

            if (!repositoryDir.exists()) {
                repositoryDir.mkdirs();
            }

            File fileNameDir = new File(repositoryDir + StringPool.SLASH + dlFileEntry.getName());
            File fileNameVersionFile = new File(fileNameDir + StringPool.SLASH + dlFileEntry.getVersion());

            return new FileInputStream(fileNameVersionFile);
        } catch (Exception e) {
            log.error("Loi me gi roi", e);
        }


        return null;
    }

    protected static File getFileNameVersionFile(
            long companyId, long repositoryId, String fileName, String version) {

        File fileNameDir = getFileNameDir(companyId, repositoryId, fileName);

        File fileNameVersionFile = new File(
                fileNameDir + StringPool.SLASH + version);

        return fileNameVersionFile;
    }

    protected static File getFileNameDir(
            long companyId, long repositoryId, String fileName) {

        File repositoryDir = getRepositoryDir(companyId, repositoryId);

        File fileNameDir = new File(
                repositoryDir + StringPool.SLASH + fileName);

        return fileNameDir;
    }

    protected static File getRepositoryDir(long companyId, long repositoryId) {
        File companyDir = getCompanyDir(companyId);

        File repositoryDir = new File(
                companyDir + StringPool.SLASH + repositoryId);

        if (!repositoryDir.exists()) {
            repositoryDir.mkdirs();
        }

        return repositoryDir;
    }

    protected static File getCompanyDir(long companyId) {
        File companyDir = new File(_rootDir + StringPool.SLASH + companyId);

        if (!companyDir.exists()) {
            companyDir.mkdirs();
        }

        return companyDir;
    }

}
