package vn.gt.dao.danhmuc.service;

import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryShipyardLocalServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DmHistoryShipyardLocalServiceUtil {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryShipyardLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public DmHistoryShipyardLocalServiceUtil(DmHistoryShipyardLocalServiceImpl service) {
        DmHistoryShipyardLocalServiceUtil._service = service;
    }
    /**
     * Adds the dm history shipyard to the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryShipyard the dm history shipyard
     * @return the dm history shipyard that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard addDmHistoryShipyard(
            com.fds.nsw.nghiepvu.model.DmHistoryShipyard dmHistoryShipyard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmHistoryShipyard(dmHistoryShipyard);
    }

    /**
     * Creates a new dm history shipyard with the primary key. Does not add the dm history shipyard to the database.
     *
     * @param id the primary key for the new dm history shipyard
     * @return the new dm history shipyard
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard createDmHistoryShipyard(
            long id) {
        return getService().createDmHistoryShipyard(id);
    }

    /**
     * Deletes the dm history shipyard with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the dm history shipyard
     * @return the dm history shipyard that was removed
     * @throws PortalException if a dm history shipyard with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard deleteDmHistoryShipyard(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryShipyard(id);
    }

    /**
     * Deletes the dm history shipyard from the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryShipyard the dm history shipyard
     * @return the dm history shipyard that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard deleteDmHistoryShipyard(
            com.fds.nsw.nghiepvu.model.DmHistoryShipyard dmHistoryShipyard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryShipyard(dmHistoryShipyard);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard fetchDmHistoryShipyard(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmHistoryShipyard(id);
    }

    /**
     * Returns the dm history shipyard with the primary key.
     *
     * @param id the primary key of the dm history shipyard
     * @return the dm history shipyard
     * @throws PortalException if a dm history shipyard with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard getDmHistoryShipyard(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryShipyard(id);
    }

    /**
     * Returns a range of all the dm history shipyards.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm history shipyards
     * @param end the upper bound of the range of dm history shipyards (not inclusive)
     * @return the range of dm history shipyards
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryShipyard> getDmHistoryShipyards(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryShipyards(start, end);
    }

    /**
     * Returns the number of dm history shipyards.
     *
     * @return the number of dm history shipyards
     * @throws SystemException if a system exception occurred
     */
    public static int getDmHistoryShipyardsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryShipyardsCount();
    }

    /**
     * Updates the dm history shipyard in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryShipyard the dm history shipyard
     * @return the dm history shipyard that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard updateDmHistoryShipyard(
            com.fds.nsw.nghiepvu.model.DmHistoryShipyard dmHistoryShipyard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryShipyard(dmHistoryShipyard);
    }

    /**
     * Updates the dm history shipyard in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryShipyard the dm history shipyard
     * @param merge whether to merge the dm history shipyard with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm history shipyard that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard updateDmHistoryShipyard(
            com.fds.nsw.nghiepvu.model.DmHistoryShipyard dmHistoryShipyard,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryShipyard(dmHistoryShipyard, merge);
    }


    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard fetchByShipYardCode_SyncVersion(
            java.lang.String shipYardCode, java.lang.String syncVersion) {
        return getService()
                .fetchByShipYardCode_SyncVersion(shipYardCode, syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard updateHistoryShipYard(
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
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateHistoryShipYard(fromMaritimeCode, shipYardCode,
                        companyName, companyAddress, contactEmail, faxNo, remarks,
                        maritimeCode, telNo, taxCode, syncVersion, companyShortName,
                        markupMaintainane, markupConstruction, markupDeconstruction,
                        profileMaintainane, profileConstruction, profileDeconstruction);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryShipyard deleteHistoryShipYard(
            java.lang.String fromMaritimeCode, java.lang.String shipYardCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .deleteHistoryShipYard(fromMaritimeCode, shipYardCode,
                        syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmHistoryShipyardLocalServiceImpl getService() {


        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmHistoryShipyardLocalServiceImpl service) {
    }

    private static DmHistoryShipyardLocalServiceImpl _service;
}
