package vn.gt.utils;

import java.net.HttpURLConnection;
import java.net.URL;

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
public class URLUtils
 {

	public static void main(String s[]) {
		System.out.println(URLUtils.exists("http://101.99.3.212:8080/tuvanonline-portlet/export/Bao.Cao.theo.nhom.dich.vu.fdf"));
		System.out.println(URLUtils.exists("http://101.99.3.212:8080/tuvanonline-portlet/export/Bao.Cao.theo.nhom.dich.vu1121.fdf"));
		/*
		 * output : true false
		 */
	}

	public static boolean exists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			// note : you may also need
			// HttpURLConnection.setInstanceFollowRedirects(false)
			HttpURLConnection connection = (HttpURLConnection) new URL(URLName).openConnection();
			connection.setRequestMethod("HEAD");
			return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}