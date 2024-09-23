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

package vn.gt.dao.noticeandmessage.service;



/**
 * The utility for the vma accident list local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaAccidentListLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaAccidentListLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaAccidentListLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaAccidentListLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaAccidentListLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaAccidentListLocalServiceUtil {
    public VmaAccidentListLocalServiceUtil(VmaAccidentListLocalServiceImpl service) {
        VmaAccidentListLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaAccidentListLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma accident list to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaAccidentList the vma accident list
     * @return the vma accident list that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAccidentList addVmaAccidentList(
            com.fds.nsw.nghiepvu.model.VmaAccidentList vmaAccidentList)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaAccidentList(vmaAccidentList);
    }

    /**
     * Creates a new vma accident list with the primary key. Does not add the vma accident list to the database.
     *
     * @param id the primary key for the new vma accident list
     * @return the new vma accident list
     */
    public static com.fds.nsw.nghiepvu.model.VmaAccidentList createVmaAccidentList(
            long id) {
        return getService().createVmaAccidentList(id);
    }

    /**
     * Deletes the vma accident list with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma accident list
     * @return the vma accident list that was removed
     * @throws PortalException if a vma accident list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAccidentList deleteVmaAccidentList(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaAccidentList(id);
    }

    /**
     * Deletes the vma accident list from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaAccidentList the vma accident list
     * @return the vma accident list that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAccidentList deleteVmaAccidentList(
            com.fds.nsw.nghiepvu.model.VmaAccidentList vmaAccidentList)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaAccidentList(vmaAccidentList);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaAccidentList fetchVmaAccidentList(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaAccidentList(id);
    }

    /**
     * Returns the vma accident list with the primary key.
     *
     * @param id the primary key of the vma accident list
     * @return the vma accident list
     * @throws PortalException if a vma accident list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAccidentList getVmaAccidentList(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAccidentList(id);
    }

    

    /**
     * Returns a range of all the vma accident lists.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma accident lists
     * @param end the upper bound of the range of vma accident lists (not inclusive)
     * @return the range of vma accident lists
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaAccidentList> getVmaAccidentLists(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAccidentLists(start, end);
    }

    /**
     * Returns the number of vma accident lists.
     *
     * @return the number of vma accident lists
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaAccidentListsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAccidentListsCount();
    }

    /**
     * Updates the vma accident list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaAccidentList the vma accident list
     * @return the vma accident list that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAccidentList updateVmaAccidentList(
            com.fds.nsw.nghiepvu.model.VmaAccidentList vmaAccidentList)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaAccidentList(vmaAccidentList);
    }

    /**
     * Updates the vma accident list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaAccidentList the vma accident list
     * @param merge whether to merge the vma accident list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma accident list that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAccidentList updateVmaAccidentList(
            com.fds.nsw.nghiepvu.model.VmaAccidentList vmaAccidentList,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaAccidentList(vmaAccidentList, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaAccidentList delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaAccidentListException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaAccidentList> findByPortofAuthority(
            java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByPortofAuthority(portofAuthority);
    }

    public static int countByPortofAuthority(java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByPortofAuthority(portofAuthority);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaAccidentList> getVmaAccidentLists(
            java.lang.String nameOfShip, java.lang.String flagStateOfShip,
            java.lang.String callSign, java.lang.String imoNumber,
            java.lang.String registryNumber, java.util.Date accidentTime,
            java.lang.String accidentType, java.lang.String accidentCriticalType,
            java.lang.String numberOfDead, java.lang.String numberOfMissed,
            java.lang.String numberOfInjured, java.lang.String pilotOnBoad,
            java.lang.String makeInvestigation, java.util.Date investigationDate,
            java.lang.String portofAuthority, java.util.Date accidentOfficialDate,
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getVmaAccidentLists(nameOfShip, flagStateOfShip, callSign,
                        imoNumber, registryNumber, accidentTime, accidentType,
                        accidentCriticalType, numberOfDead, numberOfMissed,
                        numberOfInjured, pilotOnBoad, makeInvestigation, investigationDate,
                        portofAuthority, accidentOfficialDate, start, end);
    }

    public static long countVmaAccidentLists(java.lang.String nameOfShip,
                                             java.lang.String flagStateOfShip, java.lang.String callSign,
                                             java.lang.String imoNumber, java.lang.String registryNumber,
                                             java.util.Date accidentTime, java.lang.String accidentType,
                                             java.lang.String accidentCriticalType, java.lang.String numberOfDead,
                                             java.lang.String numberOfMissed, java.lang.String numberOfInjured,
                                             java.lang.String pilotOnBoad, java.lang.String makeInvestigation,
                                             java.util.Date investigationDate, java.lang.String portofAuthority,
                                             java.util.Date accidentOfficialDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaAccidentLists(nameOfShip, flagStateOfShip,
                        callSign, imoNumber, registryNumber, accidentTime, accidentType,
                        accidentCriticalType, numberOfDead, numberOfMissed,
                        numberOfInjured, pilotOnBoad, makeInvestigation, investigationDate,
                        portofAuthority, accidentOfficialDate);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaAccidentListLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaAccidentListLocalServiceImpl service) {
    }

    private static VmaAccidentListLocalServiceImpl _service;
}
