package com.fds.nsw.nghiepvu.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.liferay.core.LanguageUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.UploadPortletRequest;
@Slf4j
public class ConfigUtils
 {
	public static final String EGOV_APPLICATION_IPMS = "egov.application.ipms";
	public static final String EGOV_IPMS_PROFILE_ID = "egov.application.ipms.profile.id";
	public static final String NOT_FOUND = "notFound";
	
	public static String getValue(String key) {
		Properties prop = new Properties();
		try {
			InputStream is = ConfigUtils.class
					.getResourceAsStream("/giaothong.properties");
			// InputStream is =
			// ClassLoader.getSystemResourceAsStream("config.properties");
			prop.load(is);
			is.close();
			return prop.getProperty(key);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public static String getKey(List<String> listKey, List<String> listThamSoMaTTHC, String maTTHC) {
		String out = NOT_FOUND;
		int size = listThamSoMaTTHC.size();
		maTTHC = maTTHC.trim();
		for (int k = 0; k < size; ++ k) {
			if (maTTHC.equals(listThamSoMaTTHC.get(k).trim())) {
				out = listKey.get(k);
				break;
			}
		}
		return out;
	}
	
	
	public static String getJSPBYMA(String keyPrefix, String key) throws Exception {
		
	
		return getValue(keyPrefix + key, NOT_FOUND);
	}
	
	public static List<String> getListKey(String key) {
		
		List<String> result = new ArrayList<String>();
		Properties prop = new Properties();
		try {
			Thread.currentThread()
					.getContextClassLoader();
			InputStream is = ConfigUtils.class
					.getResourceAsStream("/giaothong.properties");
			// InputStream is =
			// ClassLoader.getSystemResourceAsStream("config.properties");
			prop.load(is);

			is.close();
			Enumeration<Object> keys = prop.keys();
			int k =0;
			while (keys.hasMoreElements()) {
				String value = (String) keys.nextElement();
				// System.out.println(value);
				if (value != null && value.contains(key)) {
					//int index = value.indexOf(key);
					//System.out.println(index);
					value = value.substring(key.length());
					result.add(k,value);
					k= k +1;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	public static String getValue(String key, String defaultValue) {
		Properties prop = new Properties();
		try {
			Thread.currentThread()
					.getContextClassLoader();
			InputStream is = ConfigUtils.class
					.getResourceAsStream("/giaothong.properties");
			// InputStream is =
			// ClassLoader.getSystemResourceAsStream("config.properties");
			prop.load(is);

			is.close();
			String value = prop.getProperty(key);
			if (value != null) {
				return value;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return defaultValue;
	}

	public int maxlength(String key) {
		Properties prop = new Properties();
		try {
			Thread.currentThread()
					.getContextClassLoader();
			InputStream is = ConfigUtils.class
					.getResourceAsStream("/maxlength.properties");
			// InputStream is =
			// ClassLoader.getSystemResourceAsStream("config.properties");
			prop.load(is);
			is.close();
			return Integer.valueOf(prop.getProperty(key));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1000;
	}

}
