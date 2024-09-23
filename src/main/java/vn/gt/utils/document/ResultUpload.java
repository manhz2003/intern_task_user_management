/**
 * 
 */
package vn.gt.utils.document;

import java.io.Serializable;

/**
 * @author win_64
 *
 */
public class ResultUpload implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean result = false;
	private String code;
	private Long fileId;
	private String tenUpload = "";
	private String url;
	
	public boolean isResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Long getFileId() {
		return fileId;
	}
	
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTenUpload() {
		return tenUpload;
	}
	
	public void setTenUpload(String tenUpload) {
		this.tenUpload = tenUpload;
	}
}
