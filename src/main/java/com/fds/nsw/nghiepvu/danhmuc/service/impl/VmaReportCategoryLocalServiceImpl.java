package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.ArrayList; import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.VmaReportCategoryFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.VmaReportCategoryPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class VmaReportCategoryLocalServiceImpl { @Autowired
VmaReportCategoryPersistenceImpl persistence;@Autowired
VmaReportCategoryFinderImpl finder;
public VmaReportCategory addVmaReportCategory(VmaReportCategory VmaReportCategory)
		throws SystemException {
		return persistence.updateImpl(VmaReportCategory, false);
	}

	public VmaReportCategory createVmaReportCategory(long id) {
		return persistence.create(id);
	}

	public VmaReportCategory deleteVmaReportCategory(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaReportCategory deleteVmaReportCategory(VmaReportCategory VmaReportCategory)
		throws SystemException {
		return persistence.remove(VmaReportCategory);
	}

	public VmaReportCategory fetchVmaReportCategory(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaReportCategory getVmaReportCategory(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaReportCategory> getVmaReportCategories(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaReportCategoriesCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaReportCategory updateVmaReportCategory(VmaReportCategory VmaReportCategory)
		throws SystemException {
		return updateVmaReportCategory(VmaReportCategory, true);
	}

	public VmaReportCategory updateVmaReportCategory(VmaReportCategory VmaReportCategory,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaReportCategory, merge);
	}

    public List<VmaReportCategory> getReportGroups(String rptGroup, String rptLevel, int start, int end){
        try{
            return finder.getReportGroups(rptGroup, rptLevel, start, end);
        }catch(Exception e){
            return null;
        }
    }

    public long countReportGroups(String rptGroup, String rptLevel){
        try{
            return finder.coungReportGroups(rptGroup, rptLevel);
        }catch(Exception e){
            return 0;
        }
    }

}
