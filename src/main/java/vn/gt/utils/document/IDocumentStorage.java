/**
 * 
 */
package vn.gt.utils.document;

/**
 * @author win_64
 *
 */
public interface IDocumentStorage {
	
	public ResultUpload upload(Long userId, byte[] b, String fileName);
	
	public void deleteFile(Long documentId);
	
	public String getURLById(Long fileId);
	
	public String getFileName(String fileName, String newName);
	
	public boolean existFile(Long fileId);
}
