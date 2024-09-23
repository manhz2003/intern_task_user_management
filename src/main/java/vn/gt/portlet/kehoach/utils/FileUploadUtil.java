
package vn.gt.portlet.kehoach.utils;

import java.io.FileInputStream;
import java.io.InputStream;

import com.fds.nsw.kernel.exception.PortalException;
import com.itextpdf.text.pdf.codec.Base64;
import com.fds.nsw.kernel.exception.SystemException;


import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.file.FileUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;

import com.fds.nsw.liferay.core.ServiceContext;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.file.DLAppLocalServiceUtil;
import com.fds.nsw.liferay.service.DLFileEntryLocalServiceUtil;

import lombok.extern.slf4j.Slf4j;
import vgca.svrsigner.ServerSigner;
import vn.gt.utils.document.DocumentUtils;

@Slf4j
public class FileUploadUtil {

	public static DLFileEntry updateFile(
		long userId, long companyId, long groupId, long fileEntryId,
		InputStream inputStream, String fileName, String fileType,
		long fileSize, String destination, String desc,
		ServiceContext serviceContext)
		throws Exception {

		DLFileEntry fileEntry = null;
		if (inputStream != null && fileSize > 0) {

//			PermissionChecker checker =
//				PermissionCheckerFactoryUtil.create(user, Boolean.TRUE);
//			PermissionThreadLocal.setPermissionChecker(checker);
			
			long fileEntryIdNew = DocumentUtils.uploadTaiLieu(FileUtil.getBytes(inputStream), userId, ".pdf");
			
			fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryIdNew);
		}
		return fileEntry;
	}
	
	public static long kySoFile(UploadPortletRequest uploadPortletRequest, long fileEntryId, String desc, ServiceContext serviceContext) throws SystemException {
		String sign = ParamUtil.getString(uploadPortletRequest, "sign");
		String signFieldName = ParamUtil.getString(uploadPortletRequest, "signFieldName");
		String filePath = ParamUtil.getString(uploadPortletRequest, "filePath");

		boolean useHSM = ParamUtil.getBoolean(uploadPortletRequest, "useHSM");//TODO:
		
		log.info("===kySoFile===sign" + sign);
		log.info("===kySoFile===fileEntryId" + fileEntryId);
		log.info("===kySoFile===signFieldName" + signFieldName);
		log.info("===kySoFile===desc" + desc);
		log.info("===kySoFile===useHSM" + useHSM);
		
		
		if(useHSM) {
			try {
				String fileNameSigned = filePath;

				DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
						.getDLFileEntry(fileEntryId);
//						.getDLFileEntry(fileEntryId);

				InputStream inputStream = new FileInputStream(fileNameSigned);

				DLFileEntry fileEntry = updateFile(dlFileEntry.getUserId(), dlFileEntry.getCompanyId(), dlFileEntry.getGroupId(),
						fileEntryId, inputStream, fileNameSigned, dlFileEntry.getMimeType(), inputStream.available(),
						StringPool.BLANK, desc, serviceContext);

				fileEntryId = fileEntry.getFileEntryId();
			} catch (Exception e) {
				log.error(e.getMessage());
				throw new SystemException("===kySoFile===ERROR_SIGN_NULL===" + fileEntryId);
			}
		} else {
			if (Validator.isNotNull(sign) && Validator.isNotNull(filePath)) {

                DLFileEntry dlFileEntry = null;
                try {
                    dlFileEntry = DLFileEntryLocalServiceUtil
                            .getDLFileEntry(fileEntryId);
                } catch (PortalException e) {
                    throw new RuntimeException(e);
                }

                byte[] signature = Base64.decode(sign);

				try {
					ServerSigner signer = new ServerSigner(filePath, null);
					log.info("======55555==filePath=thu tuc====" + filePath);
					signer.completeSign(signature, signFieldName);
					log.info("======77777==signFieldName=thu tuc====" + signFieldName);

					String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
					String fileNameSigned = fileName.substring(0, fileName.indexOf(".pdf")) + ".signed.pdf";

					log.info("======thu tuc==signFieldName  fileNameSigned=thu tuc====" + fileNameSigned);

					filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1) + fileNameSigned;
					log.info("======thu tuc==signFieldName  filePath=thu tuc====" + filePath);

					InputStream inputStream = new FileInputStream(filePath);
					
					DLFileEntry fileEntry = updateFile(dlFileEntry.getUserId(), dlFileEntry.getCompanyId(), dlFileEntry.getGroupId(),
							fileEntryId, inputStream, fileNameSigned, dlFileEntry.getMimeType(), inputStream.available(),
							StringPool.BLANK, desc, serviceContext);

					fileEntryId = fileEntry.getFileEntryId();
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			} else {
				throw new SystemException("===kySoFile===ERROR_SIGN_NULL===" + fileEntryId);
			}
		}
		
		return fileEntryId;
	}
	

}
