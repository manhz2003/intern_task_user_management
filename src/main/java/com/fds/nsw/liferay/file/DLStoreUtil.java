package com.fds.nsw.liferay.file;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.NoSuchFileException;

import static com.fds.nsw.liferay.file.FileHandle.getFileNameVersionFile;

@Slf4j
public class DLStoreUtil {
    public static final String VERSION_DEFAULT = "1.0";

    public static void addFile(
            long companyId, long repositoryId, String fileName,
            boolean validateFileExtension, File file)
            throws PortalException, SystemException, NoSuchFileException {

        InputStream is = null;

        try {
            is = new FileInputStream(file);

            addFile(companyId, repositoryId, fileName, validateFileExtension, is);
        }
        catch (FileNotFoundException fnfe) {
            throw new NoSuchFileException(fileName);
        }
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            }
            catch (IOException ioe) {
                log.error(ioe.getMessage());
            }
        }
    }

    public static void addFile(
            long companyId, long repositoryId, String fileName, boolean validateFileExtension,
            InputStream is)
            throws PortalException, SystemException {

        try {
            File fileNameVersionFile = getFileNameVersionFile(
                    companyId, repositoryId, fileName, VERSION_DEFAULT);

            if (fileNameVersionFile.exists()) {
                log.error("File already exists: " + fileNameVersionFile);
                throw new SystemException(fileNameVersionFile.getPath());
            }

            FileUtil.write(fileNameVersionFile, is);
        }
        catch (IOException ioe) {
            throw new SystemException(ioe);
        }
    }

    public static InputStream getFileAsStream(
            long companyId, long repositoryId, String fileName)
            throws PortalException, NoSuchFileException {


        File fileNameVersionFile = getFileNameVersionFile(
                companyId, repositoryId, fileName, "1.0");

        try {
            return new FileInputStream(fileNameVersionFile);
        }
        catch (FileNotFoundException fnfe) {
            log.error(fnfe.getMessage());

        }
        return null;
    }

}
