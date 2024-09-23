/**
 * 
 */
package vn.gt.portlet;


import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

/**
 * @author win_64
 */
@Slf4j
public class TransportationMVCAction {
    public ResponseEntity<?>  writeJSON(ResourceRequest resourceRequest,
                                ResourceResponse resourceResponse, JSONObject jsonObject) {
        return ResponseEntity.status(200).body(jsonObject.toString());
    }

    public ResponseEntity<?>  writeJSON(ResourceRequest resourceRequest,
                            ResourceResponse resourceResponse, long jsonObject) {
        return ResponseEntity.status(200).body(String.valueOf(jsonObject));
    }

    public ResponseEntity<?>  writeJSON(ResourceRequest resourceRequest,
                            ResourceResponse resourceResponse, double jsonObject) {
        return ResponseEntity.status(200).body(String.valueOf(jsonObject));
    }

    public ResponseEntity<?>  writeJSON(ResourceRequest resourceRequest,
                            ResourceResponse resourceResponse, boolean jsonObject) {
        return ResponseEntity.status(200).body(String.valueOf(jsonObject));
    }

    public ResponseEntity<?>  writeJSON(ResourceRequest resourceRequest,
                            ResourceResponse resourceResponse, String jsonObject) {
        return ResponseEntity.status(200).body(jsonObject);
    }

    public ResponseEntity<?>  writeJSON(ActionRequest resourceRequest,
                                ActionResponse resourceResponse, JSONObject jsonObject) {
        return ResponseEntity.status(200).body(jsonObject.toString());
    }

    public ResponseEntity<?>  writeJSON(ResourceRequest resourceRequest,
                                ResourceResponse resourceResponse, JSONArray jsonArray) {
        return ResponseEntity.status(200).body(jsonArray.toString());
    }

   public ResponseEntity<?> serveResource(ResourceRequest resourceRequest,
                                          ResourceResponse resourceResponse) {
        log.info("Match No URL serveResource");
        return ResponseEntity.status(200).body("Match No URL serveResource");
   }
    public ResponseEntity<?> processAction(ActionRequest resourceRequest,
                              ActionResponse resourceResponse) {
        log.info("Match No URL processAction");
        return ResponseEntity.status(200).body("Match No URL processAction");
    }

	
}
