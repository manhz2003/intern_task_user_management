/**
 * 
 */
package vn.gt.listener;




import com.fds.nsw.liferay.core.ThemeDisplay;
import com.fds.nsw.liferay.core.WebKeys;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author win_64
 *
 */
@Slf4j
public class CheckSessionLoginListener implements HttpSessionListener {

	
	//TODO check again
	public void sessionCreated(HttpSessionEvent arg0) {
		try {
			synchronized (this) {
				log.debug("=====sessionCreated HttpSession=====" + arg0.getSession().getId() + "::" + arg0.getSession());
				System.out.println((ThemeDisplay) arg0.getSession().getAttribute(WebKeys.THEME_DISPLAY) + "===22222==sessionCreated HttpSession=====" + arg0.getSession().getId());
				System.out.println(arg0.getSource() + "===33333==sessionCreated HttpSession=====");
			}
		} catch (Exception es) {
			es.printStackTrace();
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		try {
			synchronized (this) {
				log.debug("=====sessionDestroyed HttpSession=====" + arg0.getSession().getId() + "::" + arg0.getSession());
				System.out.println("=====sessionDestroyed HttpSession=====" + arg0.getSession().getId() + "::" + arg0.getSession());
			}
		} catch (Exception es) {
			es.printStackTrace();
		}
	}
}
