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
 * The utility for the dm vma pilot local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmVmaPilotLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmVmaPilotLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmVmaPilotLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmVmaPilotLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmVmaPilotLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmVmaPilotLocalServiceUtil {
    public DmVmaPilotLocalServiceUtil(DmVmaPilotLocalServiceImpl service) {
        DmVmaPilotLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmVmaPilotLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm vma pilot to the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilot the dm vma pilot
     * @return the dm vma pilot that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilot addDmVmaPilot(
            com.fds.nsw.nghiepvu.model.DmVmaPilot dmVmaPilot)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmVmaPilot(dmVmaPilot);
    }

    /**
     * Creates a new dm vma pilot with the primary key. Does not add the dm vma pilot to the database.
     *
     * @param Id the primary key for the new dm vma pilot
     * @return the new dm vma pilot
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilot createDmVmaPilot(long Id) {
        return getService().createDmVmaPilot(Id);
    }

    /**
     * Deletes the dm vma pilot with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm vma pilot
     * @return the dm vma pilot that was removed
     * @throws PortalException if a dm vma pilot with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilot deleteDmVmaPilot(long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaPilot(Id);
    }

    /**
     * Deletes the dm vma pilot from the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilot the dm vma pilot
     * @return the dm vma pilot that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilot deleteDmVmaPilot(
            com.fds.nsw.nghiepvu.model.DmVmaPilot dmVmaPilot)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaPilot(dmVmaPilot);
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
   

    public static com.fds.nsw.nghiepvu.model.DmVmaPilot fetchDmVmaPilot(long Id)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmVmaPilot(Id);
    }

    /**
     * Returns the dm vma pilot with the primary key.
     *
     * @param Id the primary key of the dm vma pilot
     * @return the dm vma pilot
     * @throws PortalException if a dm vma pilot with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilot getDmVmaPilot(long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaPilot(Id);
    }

    

    /**
     * Returns a range of all the dm vma pilots.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm vma pilots
     * @param end the upper bound of the range of dm vma pilots (not inclusive)
     * @return the range of dm vma pilots
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaPilot> getDmVmaPilots(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaPilots(start, end);
    }

    /**
     * Returns the number of dm vma pilots.
     *
     * @return the number of dm vma pilots
     * @throws SystemException if a system exception occurred
     */
    public static int getDmVmaPilotsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaPilotsCount();
    }

    /**
     * Updates the dm vma pilot in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilot the dm vma pilot
     * @return the dm vma pilot that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilot updateDmVmaPilot(
            com.fds.nsw.nghiepvu.model.DmVmaPilot dmVmaPilot)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaPilot(dmVmaPilot);
    }

    /**
     * Updates the dm vma pilot in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilot the dm vma pilot
     * @param merge whether to merge the dm vma pilot with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm vma pilot that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilot updateDmVmaPilot(
            com.fds.nsw.nghiepvu.model.DmVmaPilot dmVmaPilot, boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaPilot(dmVmaPilot, merge);
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
   

    

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaPilot> findbyMaritimeCode_pilotCompanyCode(
            java.lang.String maritimeCode, java.lang.String pilotCompanyCode,
            int start, int end) {
        return getService()
                .findbyMaritimeCode_pilotCompanyCode(maritimeCode,
                        pilotCompanyCode, start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaPilot> findbyMaritimeCode(
            java.lang.String maritimeCode, int start, int end) {
        return getService().findbyMaritimeCode(maritimeCode, start, end);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaPilot fetchbyPilotCode(
            java.lang.String pilotCode) {
        return getService().fetchbyPilotCode(pilotCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaPilot> findVmaPilots(
            java.lang.String maritimeCode, java.lang.String pilotCompanyCode,
            java.lang.String pilotCategoryCode, java.lang.String pilotName,
            java.lang.String isDelete, java.lang.String pilotCodeGroup, int start,
            int end) {
        return getService()
                .findVmaPilots(maritimeCode, pilotCompanyCode,
                        pilotCategoryCode, pilotName, isDelete, pilotCodeGroup, start, end);
    }

    public static long countVmaPilots(java.lang.String maritimeCode,
                                      java.lang.String pilotCompanyCode, java.lang.String pilotCategoryCode,
                                      java.lang.String pilotName, java.lang.String isDelete,
                                      java.lang.String pilotCodeGroup) {
        return getService()
                .countVmaPilots(maritimeCode, pilotCompanyCode,
                        pilotCategoryCode, pilotName, isDelete, pilotCodeGroup);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaPilot updateVmaPilot(
            java.lang.String fromMaritimeCode, java.lang.String maritimeCode,
            java.lang.String pilotCompanyCode, java.lang.String pilotCompanyName,
            java.lang.String pilotBOD, java.lang.String pilotNo,
            java.lang.String pilotCode, java.lang.String pilotName,
            java.lang.String pilotCertificateNo,
            java.lang.String pilotCategoryCode,
            java.util.Date pilotCertificateDate, java.lang.String remarks,
            java.lang.String syncVersion, java.lang.String pilotShortName,
            java.util.Date pilotExpiredDate)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaPilotException {
        return getService()
                .updateVmaPilot(fromMaritimeCode, maritimeCode,
                        pilotCompanyCode, pilotCompanyName, pilotBOD, pilotNo, pilotCode,
                        pilotName, pilotCertificateNo, pilotCategoryCode,
                        pilotCertificateDate, remarks, syncVersion, pilotShortName,
                        pilotExpiredDate);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaPilot deleteVmaPilot(
            java.lang.String fromMaritimeCode, java.lang.String pilotCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaPilotException {
        return getService()
                .deleteVmaPilot(fromMaritimeCode, pilotCode, syncVersion);
    }

    public static org.json.JSONObject getModelMau26_1T(
            java.lang.String maritimeCode, java.lang.String pilotCode)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getModelMau26_1T(maritimeCode, pilotCode);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmVmaPilotLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmVmaPilotLocalServiceImpl service) {
    }

    private static DmVmaPilotLocalServiceImpl _service;
}
