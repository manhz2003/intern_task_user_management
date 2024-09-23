package vn.gt.utils.document;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;



import com.fds.nsw.liferay.core.PortalUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ChuKiSoGiaoThongUtil {



	public static long saveFileChuKiSo(ActionRequest request) throws PortalException, SystemException {

		String urlFile = ParamUtil.getString(request, ChuKiSoConstant.FILE_NAME_TEMP_DA_KI);
		log.info("==saveFileChuKiSo==urlFile--" + urlFile);

		long userLoginId = PortalUtil.getUser(request).getUserId();

		if (urlFile.trim().length() > 0) {

			String urlFileKySoTemp = ChuKiSoConstant.PATH_KISO_TEMP_SERVER + urlFile;
			log.info("==saveFileChuKiSo==urlFileKySoTemp--" + urlFileKySoTemp);

			try {
				return FileKySoUtils.uploadUrlFile(urlFileKySoTemp, userLoginId, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;

	}
}
