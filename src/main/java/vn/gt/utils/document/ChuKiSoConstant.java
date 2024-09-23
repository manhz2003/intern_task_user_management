package vn.gt.utils.document;

import vn.gt.utils.config.ConfigurationManager;

public class ChuKiSoConstant {
	
	public static long FILE_ENTRYID_IMG_CON_DAU = ConfigurationManager.getLongProp("vn.gt.file.id.con.dau", 28427);
	
	public static String PATH_KISO_TEMP_SERVER = ConfigurationManager.getStrProp("path.cks.temp.server", "/opt/liferay/jboss-7.0.2/standalone/deployments/TichHopGiaoThong-portlet.war/kysotemp/");
	public static String FILE_NAME_TEMP_DA_KI = "file_name_temp_da_ki";
}
