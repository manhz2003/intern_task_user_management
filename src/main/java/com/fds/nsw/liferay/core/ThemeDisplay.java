package com.fds.nsw.liferay.core;

import com.fds.nsw.liferay.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDisplay {
   long userId;
   
   User user;

   Locale locale;

   long scopeGroupId;

   long companyId;

   long Plid;

   PortletDisplay portletDisplay = new PortletDisplay();

   public ThemeDisplay(User user) {
      this.setUser(user);
      this.setLocale(Locale.ENGLISH);
      this.setUserId(user.getUserId());
   }

   public ThemeDisplay(HttpServletRequest request) {
      User user = new User();
      user.setUserId(ParamUtil.getLong(request,"userId", 0L));
      user.setEmailAddress(ParamUtil.getString(request,"userEmail", ""));
      user.setFirstName(ParamUtil.getString(request,"firstName", ""));
      user.setMiddleName(ParamUtil.getString(request,"middleName", ""));
      user.setLastName(ParamUtil.getString(request,"lastName", ""));
      this.setUser(user);
      this.setLocale(Locale.ENGLISH);
      this.setUserId(ParamUtil.getLong(request,"userId", 0L));

   }
}
