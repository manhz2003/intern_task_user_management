package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.ArrayList; import java.util.List;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmRoutePersistenceImpl;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmRouteLocalServiceImpl {
	@Autowired
	DmRoutePersistenceImpl persistence;
public DmRoute addDmRoute(DmRoute DmRoute)
		throws SystemException {
		return persistence.updateImpl(DmRoute, false);
	}

	public DmRoute createDmRoute(int id) {
		return persistence.create(id);
	}

	public DmRoute deleteDmRoute(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmRoute deleteDmRoute(DmRoute DmRoute)
		throws SystemException {
		return persistence.remove(DmRoute);
	}

	public DmRoute fetchDmRoute(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmRoute getDmRoute(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmRoute> getDmRoutes(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmRoutesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmRoute updateDmRoute(DmRoute DmRoute)
		throws SystemException {
		return updateDmRoute(DmRoute, true);
	}

	public DmRoute updateDmRoute(DmRoute DmRoute,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmRoute, merge);
	}
}
