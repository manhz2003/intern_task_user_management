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


import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.DynamicQuery;
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmCargoOnBoardLocalServiceImpl;
import com.fds.nsw.nghiepvu.model.DmCargoOnBoard;

/**
 * The utility for the dm cargo on board local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmCargoOnBoardLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmCargoOnBoardLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmCargoOnBoardLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmCargoOnBoardLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmCargoOnBoardLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmCargoOnBoardLocalServiceUtil {

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmCargoOnBoardLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm cargo on board to the database. Also notifies the appropriate model listeners.
     *
     * @param dmCargoOnBoard the dm cargo on board
     * @return the dm cargo on board that was added
     * @throws SystemException if a system exception occurred
     */

    public DmCargoOnBoardLocalServiceUtil(DmCargoOnBoardLocalServiceImpl service) {
        DmCargoOnBoardLocalServiceUtil._service = service;
    }

    public static DmCargoOnBoard addDmCargoOnBoard(
            DmCargoOnBoard dmCargoOnBoard)
            throws SystemException {
        return getService().addDmCargoOnBoard(dmCargoOnBoard);
    }

    /**
     * Creates a new dm cargo on board with the primary key. Does not add the dm cargo on board to the database.
     *
     * @param id the primary key for the new dm cargo on board
     * @return the new dm cargo on board
     */
    public static DmCargoOnBoard createDmCargoOnBoard(
            int id) {
        return getService().createDmCargoOnBoard(id);
    }

    /**
     * Deletes the dm cargo on board with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the dm cargo on board
     * @return the dm cargo on board that was removed
     * @throws PortalException if a dm cargo on board with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static DmCargoOnBoard deleteDmCargoOnBoard(
            int id)
            throws PortalException,
            SystemException {
        return getService().deleteDmCargoOnBoard(id);
    }

    /**
     * Deletes the dm cargo on board from the database. Also notifies the appropriate model listeners.
     *
     * @param dmCargoOnBoard the dm cargo on board
     * @return the dm cargo on board that was removed
     * @throws SystemException if a system exception occurred
     */
    public static DmCargoOnBoard deleteDmCargoOnBoard(
            DmCargoOnBoard dmCargoOnBoard)
            throws SystemException {
        return getService().deleteDmCargoOnBoard(dmCargoOnBoard);
    }

    public static DmCargoOnBoard fetchDmCargoOnBoard(
            int id) throws SystemException {
        return getService().fetchDmCargoOnBoard(id);
    }

    /**
     * Returns the dm cargo on board with the primary key.
     *
     * @param id the primary key of the dm cargo on board
     * @return the dm cargo on board
     * @throws PortalException if a dm cargo on board with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static DmCargoOnBoard getDmCargoOnBoard(
            int id)
            throws PortalException,
            SystemException {
        return getService().getDmCargoOnBoard(id);
    }


    /**
     * Returns a range of all the dm cargo on boards.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm cargo on boards
     * @param end the upper bound of the range of dm cargo on boards (not inclusive)
     * @return the range of dm cargo on boards
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<DmCargoOnBoard> getDmCargoOnBoards(
            int start, int end)
            throws SystemException {
        return getService().getDmCargoOnBoards(start, end);
    }

    /**
     * Returns the number of dm cargo on boards.
     *
     * @return the number of dm cargo on boards
     * @throws SystemException if a system exception occurred
     */
    public static int getDmCargoOnBoardsCount()
            throws SystemException {
        return getService().getDmCargoOnBoardsCount();
    }

    /**
     * Updates the dm cargo on board in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmCargoOnBoard the dm cargo on board
     * @return the dm cargo on board that was updated
     * @throws SystemException if a system exception occurred
     */
    public static DmCargoOnBoard updateDmCargoOnBoard(
            DmCargoOnBoard dmCargoOnBoard)
            throws SystemException {
        return getService().updateDmCargoOnBoard(dmCargoOnBoard);
    }

    /**
     * Updates the dm cargo on board in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmCargoOnBoard the dm cargo on board
     * @param merge whether to merge the dm cargo on board with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm cargo on board that was updated
     * @throws SystemException if a system exception occurred
     */
    public static DmCargoOnBoard updateDmCargoOnBoard(
            DmCargoOnBoard dmCargoOnBoard, boolean merge)
            throws SystemException {
        return getService().updateDmCargoOnBoard(dmCargoOnBoard, merge);
    }


    public static DmCargoOnBoard fetchByGoodsTypeCode(
            java.lang.String goodsTypeCode) {
        return getService().fetchByGoodsTypeCode(goodsTypeCode);
    }

    public static java.util.List<DmCargoOnBoard> findByGoodsTypeNameVN(
            java.lang.String goodsTypeNameVN, int start, int end) {
        return getService().findByGoodsTypeNameVN(goodsTypeNameVN, start, end);
    }

    public static java.util.List<DmCargoOnBoard> findCargoOnBoards(
            java.lang.String goodsTypeNameVN, java.lang.String isDelete,
            java.lang.String goodsTypeCodeGroup, int start, int end) {
        return getService()
                .findCargoOnBoards(goodsTypeNameVN, isDelete,
                        goodsTypeCodeGroup, start, end);
    }

    public static long countCargoOnBoards(java.lang.String goodsTypeNameVN,
                                          java.lang.String isDelete, java.lang.String goodsTypeCodeGroup) {
        return getService()
                .countCargoOnBoards(goodsTypeNameVN, isDelete,
                        goodsTypeCodeGroup);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmCargoOnBoardLocalServiceImpl getService() {
        return _service;
    }

    private static DmCargoOnBoardLocalServiceImpl _service;
}
