package com.fds.nsw.liferay.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceContext {
    public long userId;

    //todo add getUuid(random)
    public String uuid;

    public String getPortalURL() {
        //todo get config from application.properties
        return "";
    }
}
