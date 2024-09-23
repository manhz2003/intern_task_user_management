package com.fds.nsw.liferay.file;

import com.fds.flex.common.ultility.Validator;
import com.fds.flex.common.utility.string.CharPool;
import com.fds.flex.common.utility.string.StringBundler;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.core.PwdGenerator;

import java.io.*;

import java.time.Instant;

public class FileUtil {
    public static byte[] getBytes(File file) throws IOException {
        if ((file == null) || !file.exists()) {
            return null;
        }

        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

        byte[] bytes = new byte[(int)randomAccessFile.length()];

        randomAccessFile.readFully(bytes);

        randomAccessFile.close();

        return bytes;
    }

    public static byte[] getBytes(InputStream is) throws IOException {
        return getBytes(is, -1);
    }

    public static  byte[] getBytes(InputStream inputStream, int bufferSize)
            throws IOException {

        return getBytes(inputStream, bufferSize, true);
    }

    public static byte[] getBytes(
            InputStream inputStream, int bufferSize, boolean cleanUpStream)
            throws IOException {

        if (inputStream == null) {
            return null;
        }

        UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
                new UnsyncByteArrayOutputStream();

        StreamUtil.transfer(
                inputStream, unsyncByteArrayOutputStream, bufferSize,
                cleanUpStream);

        return unsyncByteArrayOutputStream.toByteArray();
    }

    public static File createTempFile() {
        return createTempFile(StringPool.BLANK);
    }

    public static File createTempFile(String extension) {
        return new File(createTempFileName(extension));
    }

    public static String createTempFileName(String extension) {
        StringBundler sb = new StringBundler();

        sb.append(SystemProperties.get(SystemProperties.TMP_DIR));
        sb.append(StringPool.SLASH);
        sb.append(Time.getTimestamp());
        sb.append(PwdGenerator.getPassword(PwdGenerator.KEY2, 8));

        if (Validator.isFileExtension(extension)) {
            sb.append(StringPool.PERIOD);
            sb.append(extension);
        }

        return sb.toString();
    }

    public static void write(File file, InputStream is) throws IOException {
        mkdirsParentFile(file);

        StreamUtil.transfer(is, new FileOutputStream(file));
    }

    public static void write(File file, byte[] bytes) throws IOException {
        mkdirsParentFile(file);

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        fileOutputStream.write(bytes, 0, bytes.length);

        fileOutputStream.close();
    }

    public static File createTempFile(byte[] bytes) throws IOException {
        File file = createTempFile(StringPool.BLANK);

        write(file, bytes);

        return file;
    }

    public static boolean delete(File file) {
        if (file != null) {
            boolean exists = true;

            try {
                exists = file.exists();
            }
            catch (SecurityException se) {

                // We may have the permission to delete a specific file without
                // having the permission to check if the file exists

            }

            if (exists) {
                return file.delete();
            }
        }

        return false;
    }

    protected static void mkdirsParentFile(File file) {
        File parentFile = file.getParentFile();

        if (parentFile == null) {
            return;
        }

        try {
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
        }
        catch (SecurityException se) {

            // We may have the permission to write a specific file without
            // having the permission to check if the parent file exists

        }
    }

    public static String getExtension(String fileName) {
        if (fileName == null) {
            return null;
        }

        int pos = fileName.lastIndexOf(CharPool.PERIOD);

        if (pos > 0) {
            return fileName.substring(pos + 1, fileName.length()).toLowerCase();
        }
        else {
            return StringPool.BLANK;
        }
    }




}
