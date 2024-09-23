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
 * The utility for the dm vma shipyard local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmVmaShipyardLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmVmaShipyardLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmVmaShipyardLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmVmaShipyardLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmVmaShipyardLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmVmaShipyardLocalServiceUtil {
    public DmVmaShipyardLocalServiceUtil(DmVmaShipyardLocalServiceImpl service) {
        DmVmaShipyardLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmVmaShipyardLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm vma shipyard to the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipyard the dm vma shipyard
     * @return the dm vma shipyard that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard addDmVmaShipyard(
            com.fds.nsw.nghiepvu.model.DmVmaShipyard dmVmaShipyard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmVmaShipyard(dmVmaShipyard);
    }

    /**
     * Creates a new dm vma shipyard with the primary key. Does not add the dm vma shipyard to the database.
     *
     * @param id the primary key for the new dm vma shipyard
     * @return the new dm vma shipyard
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard createDmVmaShipyard(
            long id) {
        return getService().createDmVmaShipyard(id);
    }

    /**
     * Deletes the dm vma shipyard with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the dm vma shipyard
     * @return the dm vma shipyard that was removed
     * @throws PortalException if a dm vma shipyard with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard deleteDmVmaShipyard(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaShipyard(id);
    }

    /**
     * Deletes the dm vma shipyard from the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipyard the dm vma shipyard
     * @return the dm vma shipyard that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard deleteDmVmaShipyard(
            com.fds.nsw.nghiepvu.model.DmVmaShipyard dmVmaShipyard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaShipyard(dmVmaShipyard);
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
   

    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard fetchDmVmaShipyard(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmVmaShipyard(id);
    }

    /**
     * Returns the dm vma shipyard with the primary key.
     *
     * @param id the primary key of the dm vma shipyard
     * @return the dm vma shipyard
     * @throws PortalException if a dm vma shipyard with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard getDmVmaShipyard(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaShipyard(id);
    }

    

    /**
     * Returns a range of all the dm vma shipyards.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm vma shipyards
     * @param end the upper bound of the range of dm vma shipyards (not inclusive)
     * @return the range of dm vma shipyards
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaShipyard> getDmVmaShipyards(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaShipyards(start, end);
    }

    /**
     * Returns the number of dm vma shipyards.
     *
     * @return the number of dm vma shipyards
     * @throws SystemException if a system exception occurred
     */
    public static int getDmVmaShipyardsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaShipyardsCount();
    }

    /**
     * Updates the dm vma shipyard in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipyard the dm vma shipyard
     * @return the dm vma shipyard that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard updateDmVmaShipyard(
            com.fds.nsw.nghiepvu.model.DmVmaShipyard dmVmaShipyard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaShipyard(dmVmaShipyard);
    }

    /**
     * Updates the dm vma shipyard in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipyard the dm vma shipyard
     * @param merge whether to merge the dm vma shipyard with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm vma shipyard that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard updateDmVmaShipyard(
            com.fds.nsw.nghiepvu.model.DmVmaShipyard dmVmaShipyard, boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaShipyard(dmVmaShipyard, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard fetchByShipYardCode(
            java.lang.String shipYardCode) {
        return getService().fetchByShipYardCode(shipYardCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaShipyard> findVmaShipyards(
            java.lang.String maritimeCode, java.lang.String companyName,
            java.lang.String companyAddress, java.lang.String contactEmail,
            java.lang.String telNo, java.lang.String taxCode,
            java.lang.String isDelete, java.lang.String shipYardCodeGroup,
            int start, int end) {
        return getService()
                .findVmaShipyards(maritimeCode, companyName, companyAddress,
                        contactEmail, telNo, taxCode, isDelete, shipYardCodeGroup, start,
                        end);
    }

    public static long countVmaShipyards(java.lang.String maritimeCode,
                                         java.lang.String companyName, java.lang.String companyAddress,
                                         java.lang.String contactEmail, java.lang.String telNo,
                                         java.lang.String taxCode, java.lang.String isDelete,
                                         java.lang.String shipYardCodeGroup) {
        return getService()
                .countVmaShipyards(maritimeCode, companyName,
                        companyAddress, contactEmail, telNo, taxCode, isDelete,
                        shipYardCodeGroup);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard fetchByF_taxCode(
            java.lang.String taxCode) {
        return getService().fetchByF_taxCode(taxCode);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard updateVmaShipYard(
            java.lang.String fromMaritimeCode, java.lang.String shipYardCode,
            java.lang.String companyName, java.lang.String companyAddress,
            java.lang.String contactEmail, java.lang.String faxNo,
            java.lang.String remarks, java.lang.String maritimeCode,
            java.lang.String telNo, java.lang.String taxCode,
            java.lang.String syncVersion, java.lang.String companyShortName,
            int markupMaintainane, int markupConstruction,
            int markupDeconstruction, java.lang.String profileMaintainane,
            java.lang.String profileConstruction,
            java.lang.String profileDeconstruction)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaShipyardException {
        return getService()
                .updateVmaShipYard(fromMaritimeCode, shipYardCode,
                        companyName, companyAddress, contactEmail, faxNo, remarks,
                        maritimeCode, telNo, taxCode, syncVersion, companyShortName,
                        markupMaintainane, markupConstruction, markupDeconstruction,
                        profileMaintainane, profileConstruction, profileDeconstruction);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaShipyard deleteVmaShipYard(
            java.lang.String fromMaritimeCode, java.lang.String shipYardCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaShipyardException {
        return getService()
                .deleteVmaShipYard(fromMaritimeCode, shipYardCode,
                        syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmVmaShipyardLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmVmaShipyardLocalServiceImpl service) {
    }

    private static DmVmaShipyardLocalServiceImpl _service;
}
