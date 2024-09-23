package com.fds.nsw.liferay.file;

import com.fds.flex.common.ultility.Validator;
import com.fds.flex.common.utility.string.StringPool;

public class DLUtil {
    public static String getTitleWithExtension(String title, String extension) {
        if (Validator.isNotNull(extension)) {
            String periodAndExtension = StringPool.PERIOD + extension;

            if (!title.endsWith(periodAndExtension)) {
                title += periodAndExtension;
            }
        }

        return title;
    }
}
