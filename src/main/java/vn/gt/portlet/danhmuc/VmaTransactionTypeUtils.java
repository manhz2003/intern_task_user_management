package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.VmaTransactionType;
import vn.gt.dao.noticeandmessage.service.VmaTransactionTypeLocalServiceUtil;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaTransactionTypeUtils {

	public static JSONObject getVmaTransactionTypes(String portofAuthority)
			throws SystemException {
		List<VmaTransactionType> vmaTransactionTypes = VmaTransactionTypeLocalServiceUtil
				.findByPortofAuthority(portofAuthority);
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();

		for (VmaTransactionType vmaTransactionType : vmaTransactionTypes) {
			if (vmaTransactionType.getTransactionLevel() == 2) {
				JSONObject object = JSONFactoryUtil.createJSONObject();
				object.put("id", vmaTransactionType.getId());
				object.put("portofAuthority",
						vmaTransactionType.getPortofAuthority());
				object.put("transactionTypeCode",
						vmaTransactionType.getTransactionTypeCode());
				object.put("transactionTypeName",
						vmaTransactionType.getTransactionTypeName());
				object.put("transactionLevel",
						vmaTransactionType.getTransactionLevel());
				object.put("transactionTypeShortName",
						vmaTransactionType.getTransactionTypeShortName());
				object.put("modifiedDate", vmaTransactionType.getModifiedDate());
				array.put(object);
			}
		}
		result.put("total", VmaTransactionTypeLocalServiceUtil
				.countByPortofAuthority(portofAuthority));
		result.put("data", array);
		return result;
	}
}
