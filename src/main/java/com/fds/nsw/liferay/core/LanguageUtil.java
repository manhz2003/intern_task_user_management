package com.fds.nsw.liferay.core;

import java.util.Locale;
import java.util.ResourceBundle;

import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.properties.PropKey;

public class LanguageUtil {

    public static String get(Locale locale, String key) {
//    	String bundleName = GetterUtil.getString(PropKey.getKeyMap().get("nsw.core.default_bundle_name"));
    	ResourceBundle resourceBundle = ResourceBundle.getBundle("Language", locale);
		if (resourceBundle.containsKey(key)) {
			key = resourceBundle.getString(key);
		}

        return key;
    }
}
