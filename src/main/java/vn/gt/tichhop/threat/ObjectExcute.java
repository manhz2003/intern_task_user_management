/**
 * 
 */
package vn.gt.tichhop.threat;

import java.util.ArrayList;
import java.util.List;

import vn.nsw.Header;

/**
 * @author win_64
 */
public class ObjectExcute {
	
	private Header header = null;
	private String xmlData = null;
	private List<Object> liObjects = new ArrayList<Object>();
	
	public ObjectExcute(Header header, List<Object> liObjects, String xmlData) {
		this.header = header;
		this.liObjects = liObjects;
		this.xmlData = xmlData;
	}
	
	/**
	 * @return the header
	 */
	public Header getHeader() {
		return header;
	}
	
	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(Header header) {
		this.header = header;
	}
	
	/**
	 * @return the liObjects
	 */
	public List<Object> getLiObjects() {
		return liObjects;
	}
	
	/**
	 * @param liObjects
	 *            the liObjects to set
	 */
	public void setLiObjects(List<Object> liObjects) {
		this.liObjects = liObjects;
	}
	
	/**
	 * @return the xmlData
	 */
	public String getXmlData() {
		return xmlData;
	}
}
