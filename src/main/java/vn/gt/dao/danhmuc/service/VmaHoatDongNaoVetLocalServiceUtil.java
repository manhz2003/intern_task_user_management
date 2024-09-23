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
 * The utility for the vma hoat dong nao vet local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.VmaHoatDongNaoVetLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaHoatDongNaoVetLocalService
 * @see vn.gt.dao.danhmuc.service.base.VmaHoatDongNaoVetLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.VmaHoatDongNaoVetLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.VmaHoatDongNaoVetLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaHoatDongNaoVetLocalServiceUtil {
    public VmaHoatDongNaoVetLocalServiceUtil(VmaHoatDongNaoVetLocalServiceImpl service) {
        VmaHoatDongNaoVetLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.VmaHoatDongNaoVetLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma hoat dong nao vet to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaHoatDongNaoVet the vma hoat dong nao vet
     * @return the vma hoat dong nao vet that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet addVmaHoatDongNaoVet(
            com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet vmaHoatDongNaoVet)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaHoatDongNaoVet(vmaHoatDongNaoVet);
    }

    /**
     * Creates a new vma hoat dong nao vet with the primary key. Does not add the vma hoat dong nao vet to the database.
     *
     * @param id the primary key for the new vma hoat dong nao vet
     * @return the new vma hoat dong nao vet
     */
    public static com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet createVmaHoatDongNaoVet(
            long id) {
        return getService().createVmaHoatDongNaoVet(id);
    }

    /**
     * Deletes the vma hoat dong nao vet with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma hoat dong nao vet
     * @return the vma hoat dong nao vet that was removed
     * @throws PortalException if a vma hoat dong nao vet with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet deleteVmaHoatDongNaoVet(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaHoatDongNaoVet(id);
    }

    /**
     * Deletes the vma hoat dong nao vet from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaHoatDongNaoVet the vma hoat dong nao vet
     * @return the vma hoat dong nao vet that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet deleteVmaHoatDongNaoVet(
            com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet vmaHoatDongNaoVet)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaHoatDongNaoVet(vmaHoatDongNaoVet);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet fetchVmaHoatDongNaoVet(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaHoatDongNaoVet(id);
    }

    /**
     * Returns the vma hoat dong nao vet with the primary key.
     *
     * @param id the primary key of the vma hoat dong nao vet
     * @return the vma hoat dong nao vet
     * @throws PortalException if a vma hoat dong nao vet with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet getVmaHoatDongNaoVet(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaHoatDongNaoVet(id);
    }

    

    /**
     * Returns a range of all the vma hoat dong nao vets.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma hoat dong nao vets
     * @param end the upper bound of the range of vma hoat dong nao vets (not inclusive)
     * @return the range of vma hoat dong nao vets
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet> getVmaHoatDongNaoVets(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaHoatDongNaoVets(start, end);
    }

    /**
     * Returns the number of vma hoat dong nao vets.
     *
     * @return the number of vma hoat dong nao vets
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaHoatDongNaoVetsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaHoatDongNaoVetsCount();
    }

    /**
     * Updates the vma hoat dong nao vet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaHoatDongNaoVet the vma hoat dong nao vet
     * @return the vma hoat dong nao vet that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet updateVmaHoatDongNaoVet(
            com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet vmaHoatDongNaoVet)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaHoatDongNaoVet(vmaHoatDongNaoVet);
    }

    /**
     * Updates the vma hoat dong nao vet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaHoatDongNaoVet the vma hoat dong nao vet
     * @param merge whether to merge the vma hoat dong nao vet with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma hoat dong nao vet that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet updateVmaHoatDongNaoVet(
            com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet vmaHoatDongNaoVet,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaHoatDongNaoVet(vmaHoatDongNaoVet, merge);
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
   

    

    public static long countVmaHoatDongNaoVet()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaHoatDongNaoVet();
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaHoatDongNaoVetLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaHoatDongNaoVetLocalServiceImpl service) {
    }

    private static VmaHoatDongNaoVetLocalServiceImpl _service;
}
