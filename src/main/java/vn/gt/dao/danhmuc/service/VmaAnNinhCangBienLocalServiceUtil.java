/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package vn.gt.dao.danhmuc.service;



/**
 * The utility for the vma an ninh cang bien local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.VmaAnNinhCangBienLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaAnNinhCangBienLocalService
 * @see vn.gt.dao.danhmuc.service.base.VmaAnNinhCangBienLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.VmaAnNinhCangBienLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.VmaAnNinhCangBienLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaAnNinhCangBienLocalServiceUtil {
    public VmaAnNinhCangBienLocalServiceUtil(VmaAnNinhCangBienLocalServiceImpl service) {
        VmaAnNinhCangBienLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.VmaAnNinhCangBienLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma an ninh cang bien to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaAnNinhCangBien the vma an ninh cang bien
     * @return the vma an ninh cang bien that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien addVmaAnNinhCangBien(
            com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien vmaAnNinhCangBien)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaAnNinhCangBien(vmaAnNinhCangBien);
    }

    /**
     * Creates a new vma an ninh cang bien with the primary key. Does not add the vma an ninh cang bien to the database.
     *
     * @param id the primary key for the new vma an ninh cang bien
     * @return the new vma an ninh cang bien
     */
    public static com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien createVmaAnNinhCangBien(
            long id) {
        return getService().createVmaAnNinhCangBien(id);
    }

    /**
     * Deletes the vma an ninh cang bien with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma an ninh cang bien
     * @return the vma an ninh cang bien that was removed
     * @throws PortalException if a vma an ninh cang bien with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien deleteVmaAnNinhCangBien(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaAnNinhCangBien(id);
    }

    /**
     * Deletes the vma an ninh cang bien from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaAnNinhCangBien the vma an ninh cang bien
     * @return the vma an ninh cang bien that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien deleteVmaAnNinhCangBien(
            com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien vmaAnNinhCangBien)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaAnNinhCangBien(vmaAnNinhCangBien);
    }

    

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery the dynamic query
     * @return the matching rows
     * @throws SystemException if a system exception occurred
     */
    
   

    /**
     * Performs a dynamic query on the database and returns a range of the matching rows.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param dynamicQuery the dynamic query
     * @param start the lower bound of the range of model instances
     * @param end the upper bound of the range of model instances (not inclusive)
     * @return the range of matching rows
     * @throws SystemException if a system exception occurred
     */
    
   

    /**
     * Performs a dynamic query on the database and returns an ordered range of the matching rows.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param dynamicQuery the dynamic query
     * @param start the lower bound of the range of model instances
     * @param end the upper bound of the range of model instances (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching rows
     * @throws SystemException if a system exception occurred
     */
    
    

    /**
     * Returns the number of rows that match the dynamic query.
     *
     * @param dynamicQuery the dynamic query
     * @return the number of rows that match the dynamic query
     * @throws SystemException if a system exception occurred
     */
   

    public static com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien fetchVmaAnNinhCangBien(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaAnNinhCangBien(id);
    }

    /**
     * Returns the vma an ninh cang bien with the primary key.
     *
     * @param id the primary key of the vma an ninh cang bien
     * @return the vma an ninh cang bien
     * @throws PortalException if a vma an ninh cang bien with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien getVmaAnNinhCangBien(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAnNinhCangBien(id);
    }

    

    /**
     * Returns a range of all the vma an ninh cang biens.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma an ninh cang biens
     * @param end the upper bound of the range of vma an ninh cang biens (not inclusive)
     * @return the range of vma an ninh cang biens
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien> getVmaAnNinhCangBiens(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAnNinhCangBiens(start, end);
    }

    /**
     * Returns the number of vma an ninh cang biens.
     *
     * @return the number of vma an ninh cang biens
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaAnNinhCangBiensCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAnNinhCangBiensCount();
    }

    /**
     * Updates the vma an ninh cang bien in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaAnNinhCangBien the vma an ninh cang bien
     * @return the vma an ninh cang bien that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien updateVmaAnNinhCangBien(
            com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien vmaAnNinhCangBien)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaAnNinhCangBien(vmaAnNinhCangBien);
    }

    /**
     * Updates the vma an ninh cang bien in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaAnNinhCangBien the vma an ninh cang bien
     * @param merge whether to merge the vma an ninh cang bien with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma an ninh cang bien that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien updateVmaAnNinhCangBien(
            com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien vmaAnNinhCangBien,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaAnNinhCangBien(vmaAnNinhCangBien, merge);
    }

    /**
     * Returns the Spring bean ID for this bean.
     *
     * @return the Spring bean ID for this bean
     */
    

    /**
     * Sets the Spring bean ID for this bean.
     *
     * @param beanIdentifier the Spring bean ID for this bean
     */
   

    

    public static long countVmaAnNinhCangBien()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaAnNinhCangBien();
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaAnNinhCangBienLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaAnNinhCangBienLocalServiceImpl service) {
    }

    private static VmaAnNinhCangBienLocalServiceImpl _service;
}
