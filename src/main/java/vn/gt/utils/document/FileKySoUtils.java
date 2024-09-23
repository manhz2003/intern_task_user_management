package vn.gt.utils.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.fds.nsw.liferay.core.ActionRequest;
import jakarta.servlet.http.HttpServletRequest;



import com.fds.nsw.liferay.core.PortalUtil;

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
public class FileKySoUtils
 {



	private static final String URL_FILE_ = "/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war";
	private static final String HTTP_NO_PORT = "http://hanoi2.dtt.vn:8088/TichHopGiaoThong-portlet";



	public static String getStringRemoveVersion(String urlFileVersion) {
		for (int i = 0; i < 20; i++) {
			urlFileVersion = urlFileVersion.replace("?version=1." + i, "");
		}
		return urlFileVersion;
	}

	// SONVH comment de xoa toan bo noi dung nay

	public static long uploadUrlFile(String urlFile, long userIdUpload, ActionRequest request) {
		long noiLuuTruTaiLieuId = 0;
		if (urlFile.trim().length() > 0) {
			try {
				InputStream inputStream = getInputStreamFromUrl(urlFile, request);
				if (inputStream != null) {
					byte[] fileContext = DocumentUtils.getByteFromInputStream(inputStream);

					log.info("==uploadUrlFile != null===" + fileContext);

					noiLuuTruTaiLieuId = DocumentUtils.uploadTaiLieu(fileContext, userIdUpload, urlFile);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		log.info("==uploadUrlFile==noiLuuTruTaiLieuId==" + noiLuuTruTaiLieuId);
		return noiLuuTruTaiLieuId;
	}

	public static InputStream getInputStreamFromUrl(String urlFileTemp, ActionRequest request) throws IOException {
		InputStream input = null;

		if (input == null) {
			log.info("==getInputStreamFromUrl==urlFile_ Begin==" + urlFileTemp);
			String urlPortal = getURLPortal(request);
			String urlFile = getURLFile(request);

			log.info("==URLPortal==" + urlPortal);
			log.info("==URLFile==" + urlFile);

			String urlFile_ = urlFileTemp.replace(urlPortal, urlFile).replace(HTTP_NO_PORT, URL_FILE_);
			log.info("==urlFile==" + urlFile_);

			File initialFile = new File(urlFile_);
			try {
				input = new FileInputStream(initialFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		if (input == null) {
			try {
				input = new URL(urlFileTemp).openStream();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				input.close();
			}
		}
		//TODO - minhhandsome return InputStream khi dong input.close(); doi tuong do co khoa ko
		return input;
	}



	public static String urlFileChuKy(HttpServletRequest request) {
		try {
			long userIdLogin = PortalUtil.getUserId(request);
			String urlFileChuKy = "";
			if (userIdLogin > 0) {
				// CorporationInspector corporationInspector =
				// CorporationInspectorLocalServiceUtil.fetchByInspectorId(userIdLogin);
				// if (corporationInspector != null &&
				// corporationInspector.getFilechukyId() > 0) {
				long noiLuuTruTaiLieuId = 0;
				urlFileChuKy = DocumentUtils.getLinkDownloadFromNoiLuuTruTaiLieuId(noiLuuTruTaiLieuId);
				urlFileChuKy = urlFileChuKy.replace("?version=1.0", "").replace("?version=1.1", "");
				return urlFileChuKy;
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String urlFileConDau() {
		try {
			String urlFileConDau = "";
			long fileMauConDau = ChuKiSoConstant.FILE_ENTRYID_IMG_CON_DAU;
			if (fileMauConDau > 0) {
				urlFileConDau = DocumentUtils.getLinkDownloadFromNoiLuuTruTaiLieuId(fileMauConDau);
				urlFileConDau = urlFileConDau.replace("?version=1.0", "").replace("?version=1.1", "");
				return urlFileConDau;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/TichHopGiaoThong-portlet/chuky/maucondau_cuc_mtyt.png";
	}



	public static void isDeleteTemp(String tempDir) {
		File directory = new File(tempDir);
		// make sure directory exists
		if (!directory.exists()) {
			log.info("Directory does not exist.");
			System.exit(0);
		} else {
			try {
				log.info("Directory exist.--- delete");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}


	public static String getURLPortal(ActionRequest request) {
		try {
//			if (PortalUtil.getPortalPort() == 80) {
//				if (PortalUtil.getPortalURL(request).contains("hanoi.dtt.vn:8088")) {
//
//					return PortalUtil.getPortalURL(request) + "/TichHopGiaoThong-portlet";
//				}
//				return PortalUtil.getPortalURL(request) + ":" + PortalUtil.getPortalPort() + "/TichHopGiaoThong-portlet";
//			} else {
//				return PortalUtil.getPortalURL(request) + "/TichHopGiaoThong-portlet";
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "http://hanoi.dtt.vn:8088/TichHopGiaoThong-portlet";
	}


	public static String getURLFile(ActionRequest request) {
		try {
//			return request.getPortletSession().getPortletContext().getRealPath("/").replace("/", File.separator).replace(File.separator + ".", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/";
	}

}
