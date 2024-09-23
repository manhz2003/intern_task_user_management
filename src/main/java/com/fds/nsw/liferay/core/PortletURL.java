package com.fds.nsw.liferay.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortletURL {

    public String windowState;
    public String portletMode;
    public String resourceID;

    String action;
    String name;
    public void setParameter(String action, String name) {
        this.action = action;
        this.name = name;
    }

    public String toString() {
        return this.getResourceID()+"&p_p_state="
                + this.getWindowState()+"&p_p_mode="
                +this.portletMode+"&"+this.getAction()+"="+this.getName();
    }
}
