package vn.gt.tichhop.message.haiquan2giaothong;

import java.util.Hashtable;
import java.util.Map.Entry;





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
public class InterfaceRequest2TempUtils
 {
	
	
	
	public static String sqlUpateByRequestCode(Hashtable<String, String> hashSql, String requestCode) {
		if (Validator.isNotNull(hashSql) && hashSql.size() > 0 && Validator.isNotNull(requestCode) && requestCode.length() > 0) {
			String buildSqlRequestCode = buildSqlRequestCode(hashSql, requestCode);
			log.info("==sqlUpateByRequestCode==" + buildSqlRequestCode);
			return buildSqlRequestCode;
		}
		return "";
		
	}
	
	public static String sqlUpateById(Hashtable<String, String> hashSql, long idInterfaceRequest) {
		if (Validator.isNotNull(hashSql) && hashSql.size() > 0 && idInterfaceRequest > 0) {
			String buildSqlIdInterfaceRequest = buildSqlIdInterfaceRequest(hashSql, idInterfaceRequest);
			log.info("==sqlUpateById==" + buildSqlIdInterfaceRequest);
			return buildSqlIdInterfaceRequest;
		}
		return "";
		
	}
	
	private static String buildSqlIdInterfaceRequest(Hashtable<String, String> hashtable, long idInterfaceRequest) {
		StringBuilder query = new StringBuilder();
		query.append("update interface_request set ");
		buildSql(query, hashtable);
		query.append("where id = '" + idInterfaceRequest + "'");
		return query.toString();
	}
	
	private static String buildSqlRequestCode(Hashtable<String, String> hashtable, String requestCode) {
		StringBuilder query = new StringBuilder();
		// update interface_request set requestedDate = ''
		query.append("update interface_request set ");
		buildSql(query, hashtable);
		query.append("where RequestCode = '" + requestCode + "'");
		return query.toString();
	}
	
	private static void buildSql(StringBuilder query, Hashtable<String, String> hashtable) {
		boolean first = true;
		for (Entry<String, String> entry : hashtable.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(OrganizationCode)) {
				buildFiled(entry, OrganizationCode, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(LocCode)) {
				buildFiled(entry, LocCode, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(RequestDate)) {
				buildFiled(entry, RequestDate, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(RequestedDate)) {
				buildFiled(entry, RequestedDate, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(RequestDirection)) {
				buildFiled(entry, RequestDirection, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(RequestState)) {
				buildFiled(entry, RequestState, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(RequestResponseTime)) {
				buildFiled(entry, RequestResponseTime, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(DocumentType)) {
				buildFiled(entry, DocumentType, query, first);
			}
			
//			if (entry.getKey().equalsIgnoreCase(DocumentNameRef)) {
//				buildFiled(entry, DocumentNameRef, query, first);
//			}
			
			if (entry.getKey().equalsIgnoreCase(BusinessType)) {
				buildFiled(entry, BusinessType, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(FunctionType)) {
				buildFiled(entry, FunctionType, query, first);
			}
			
			/*
			 * if (entry.getKey().equalsIgnoreCase(RequestContent)) { query.append(""); }
			 */
			
			if (entry.getKey().equalsIgnoreCase(SenderName)) {
				buildFiled(entry, SenderName, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(RequestVersion)) {
				buildFiled(entry, RequestVersion, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(SenderIdentify)) {
				buildFiled(entry, SenderIdentify, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(ReceiverName)) {
				buildFiled(entry, ReceiverName, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(SendingDate)) {
				buildFiled(entry, SendingDate, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(ReceiverIdentify)) {
				buildFiled(entry, ReceiverIdentify, query, first);
			}
			
			if (entry.getKey().equalsIgnoreCase(Remarks)) {
				buildFiled(entry, Remarks, query, first);
			}
			first = false;
		}
		
	}
	
	private static void buildFiled(Entry<String, String> entry, String field, StringBuilder query, boolean first) {
		if (Validator.isNotNull(entry.getValue()) && entry.getValue().length() > 0) {
			if (first == true) {
				query.append(field + " = '" + entry.getValue() + "' ");
			} else {
				query.append("and " + field + " = '" + entry.getValue() + "' ");
			}
		}
	}
	
	public static String ID = "ID";
	public static String RequestCode = "RequestCode";
	
	public static String RequestDate = "RequestDate";
	public static String RequestedDate = "RequestedDate";
	public static String RequestDirection = "RequestDirection";
	public static String RequestState = "RequestState";
	public static String RequestResponseTime = "RequestResponseTime";
	public static String DocumentType = "DocumentType";
	
	public static String BusinessType = "BusinessType";
	public static String FunctionType = "FunctionType";
	// public static String DocumentNameRef = "DocumentNameRef";
	// public static String RequestContent = "RequestContent";
	public static String RequestVersion = "RequestVersion";
	public static String SenderName = "SenderName";
	public static String SenderIdentify = "SenderIdentify";
	public static String ReceiverName = "ReceiverName";
	public static String ReceiverIdentify = "ReceiverIdentify";
	public static String SendingDate = "SendingDate";
	public static String Remarks = "Remarks";
	public static String OrganizationCode = "OrganizationCode";
	public static String LocCode = "LocCode";
}
