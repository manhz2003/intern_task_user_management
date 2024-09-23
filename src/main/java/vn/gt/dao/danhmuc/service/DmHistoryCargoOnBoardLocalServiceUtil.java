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

import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryArrivalPurposeLocalServiceImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryCargoOnBoardLocalServiceImpl;


/**
 * The utility for the dm history cargo on board local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryCargoOnBoardLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryCargoOnBoardLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryCargoOnBoardLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryCargoOnBoardLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryCargoOnBoardLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmHistoryCargoOnBoardLocalServiceUtil {

    public DmHistoryCargoOnBoardLocalServiceUtil(DmHistoryCargoOnBoardLocalServiceImpl service) {
        DmHistoryCargoOnBoardLocalServiceUtil._service = service;
    }

    private static DmHistoryCargoOnBoardLocalServiceImpl _service;
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryCargoOnBoardLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm history cargo on board to the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryCargoOnBoard the dm history cargo on board
     * @return the dm history cargo on board that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard addDmHistoryCargoOnBoard(
            com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard dmHistoryCargoOnBoard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmHistoryCargoOnBoard(dmHistoryCargoOnBoard);
    }

    /**
     * Creates a new dm history cargo on board with the primary key. Does not add the dm history cargo on board to the database.
     *
     * @param id the primary key for the new dm history cargo on board
     * @return the new dm history cargo on board
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard createDmHistoryCargoOnBoard(
            int id) {
        return getService().createDmHistoryCargoOnBoard(id);
    }

    /**
     * Deletes the dm history cargo on board with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the dm history cargo on board
     * @return the dm history cargo on board that was removed
     * @throws PortalException if a dm history cargo on board with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard deleteDmHistoryCargoOnBoard(
            int id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryCargoOnBoard(id);
    }

    /**
     * Deletes the dm history cargo on board from the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryCargoOnBoard the dm history cargo on board
     * @return the dm history cargo on board that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard deleteDmHistoryCargoOnBoard(
            com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard dmHistoryCargoOnBoard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryCargoOnBoard(dmHistoryCargoOnBoard);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard fetchDmHistoryCargoOnBoard(
            int id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmHistoryCargoOnBoard(id);
    }


    public static com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard getDmHistoryCargoOnBoard(
            int id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryCargoOnBoard(id);
    }


    /**
     * Returns a range of all the dm history cargo on boards.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm history cargo on boards
     * @param end the upper bound of the range of dm history cargo on boards (not inclusive)
     * @return the range of dm history cargo on boards
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard> getDmHistoryCargoOnBoards(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryCargoOnBoards(start, end);
    }

    /**
     * Returns the number of dm history cargo on boards.
     *
     * @return the number of dm history cargo on boards
     * @throws SystemException if a system exception occurred
     */
    public static int getDmHistoryCargoOnBoardsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryCargoOnBoardsCount();
    }

    /**
     * Updates the dm history cargo on board in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryCargoOnBoard the dm history cargo on board
     * @return the dm history cargo on board that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard updateDmHistoryCargoOnBoard(
            com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard dmHistoryCargoOnBoard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryCargoOnBoard(dmHistoryCargoOnBoard);
    }

    /**
     * Updates the dm history cargo on board in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryCargoOnBoard the dm history cargo on board
     * @param merge whether to merge the dm history cargo on board with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm history cargo on board that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard updateDmHistoryCargoOnBoard(
            com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard dmHistoryCargoOnBoard,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateDmHistoryCargoOnBoard(dmHistoryCargoOnBoard, merge);
    }



    public static com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard fetchByGoodsTypeCode_SyncVersion(
            java.lang.String goodsTypeCode, java.lang.String syncVersion) {
        return getService()
                .fetchByGoodsTypeCode_SyncVersion(goodsTypeCode, syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmHistoryCargoOnBoardLocalServiceImpl getService() {

        return _service;
    }
}
