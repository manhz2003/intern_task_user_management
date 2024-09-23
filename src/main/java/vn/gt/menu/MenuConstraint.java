//package vn.gt.menu;
//
//import java.util.List;
//
//
//
//import com.fds.nsw.liferay.model.Layout;
//import com.fds.nsw.liferay.model.ResourceConstants;
//import com.fds.nsw.liferay.model.Role;
//import com.liferay.portal.security.permission.ActionKeys;
//
//import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
//
//
//public class MenuConstraint {
//
//	public static final String SESSION_MENU_SELECTED="SESSION_MENU_SELECTED";
//	public static final String MENU_SELECTED="selected";
//
//
//	public static String getFirstURL(List<Role> roles,List<Role> rolesOfGroup,List<Layout> layouts,boolean isAdmin,boolean isRoot){
//
//		if(layouts != null && roles != null){
//			for (Layout layout : layouts) {
//				boolean isView =isViewPage(roles,rolesOfGroup, layout, isAdmin);
//				if(isRoot && (layout.getParentLayoutId() >0 || !isView)){
//					continue;
//				}else{
//					try {
//						if(!isRoot){
//							if(isView){
//								return getURL(layout);
//							}
//						}else	if(layout.getChildren() == null){
//							if(isView){
//								return getURL(layout);
//							}
//						}else{
//							String cURL = getFirstURL(roles, rolesOfGroup, layout.getAllChildren(), isAdmin,false);
//							if(cURL != null) return cURL;
//						}
//					} catch (Exception e) {
//
//						return getURL(layout);
//					}
//				}
//			}
//		}
//		return null;
//	}
//
//
//	public static String getURL(Layout layout){
//		try {
//			return "/group" + layout.getGroup().getFriendlyURL() + layout.getFriendlyURL();
//		} catch (Exception e) {
//
//
//		}
//		return "";
//	}
//
//	public static boolean isViewPage(List<Role> roles,List<Role> rolesOfGroup,Layout layout,boolean isAdmin){
//		if(roles == null || layout == null) return false;
//		if(isAdmin){
//			return true;
//		}else if(!layout.isHidden()){
//			if(roles != null){
//				for (Role role : roles) {
//					if(isViewPage(role, layout)){
//						return true;
//					}
//				}
//			}
//			if(rolesOfGroup != null){
//				for (Role role : rolesOfGroup) {
//					if(isViewPage(role, layout)){
//						return true;
//					}
//				}
//			}
//
//		}
//		return false;
//	}
//
//	/**
//	 *  This is function check role permission for read only page
//	 * @param role
//	 * @param layout
//	 * @return
//	 */
//	public static boolean isViewPage(Role role,Layout layout){
//		try {
//
//			boolean hasRolePermission = ResourcePermissionLocalServiceUtil
//					.hasResourcePermission(role.getCompanyId(), Layout.class.getName()
//							,ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf( layout.getId())
//							,role.getRoleId(), ActionKeys.VIEW);
//
//			/*System.out.println("roleId:"+role.getRoleId()+",companyid:"+role.getCompanyId()+", layoutname:"+Layout.class.getName()
//			        +",resuurce:"+ ResourceConstants.SCOPE_INDIVIDUAL
//			        +",primary:"+layout.getId()+",action:"+ActionKeys.VIEW);*/
//			if(hasRolePermission){
//				return true;
//			}else if(layout.getChildren() != null
//					&& layout.getChildren().size()>0){
//				//check if permission child view . view parent
//				List<Layout> lList = layout.getAllChildren();
//				for (Layout child : lList) {
//					boolean isView = isViewPage(role, child);
//					if(isView){
//						return true;
//					}
//				}
//			}
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return false;
//
//	}
//	/**
//	 *
//	 * @param value
//	 * @param selected
//	 * @param css
//	 * @return
//	 */
//	public static final String getSelected(String value,String selected,String css){
//		if(value.equals(selected)){
//			return "class=\""+css+"\"";
//		}
//		return "";
//	}
//
//	public static boolean checkMenuSelected(List<Layout> childrens, String menuId) {
//		String url = "";
//		if(childrens != null &&childrens.size() > 0) {
//			for (Layout child : childrens) {
//				url = MenuConstraint.getURL(child);
//				if(url.equals(menuId)){
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}
//}
