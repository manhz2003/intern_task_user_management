package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.ArrayList; import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmCargoOnBoardFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmCargoOnBoardPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmCargoOnBoardLocalServiceImpl { @Autowired
DmCargoOnBoardPersistenceImpl persistence;@Autowired
DmCargoOnBoardFinderImpl finder;

    public DmCargoOnBoard fetchByGoodsTypeCode(String goodsTypeCode) {
        try {
            return persistence
                    .fetchByF_goodsTypeCode(goodsTypeCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DmCargoOnBoard> findByGoodsTypeNameVN(String goodsTypeNameVN,
                                                      int start, int end) {
        try {
            return persistence.findByF_goodsTypeNameVN(
                    goodsTypeNameVN, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DmCargoOnBoard> findCargoOnBoards(String goodsTypeNameVN,
                                                  String isDelete, String goodsTypeCodeGroup, int start, int end) {
        try {
            return finder.findCargoOnBoards(goodsTypeNameVN,
                    isDelete, goodsTypeCodeGroup, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countCargoOnBoards(String goodsTypeNameVN, String isDelete,
                                   String goodsTypeCodeGroup) {
        try {
            return finder.countCargoOnBoards(goodsTypeNameVN,
                    isDelete, goodsTypeCodeGroup);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    public DmCargoOnBoard addDmCargoOnBoard(DmCargoOnBoard DmCargoOnBoard)
		throws SystemException {
		return persistence.updateImpl(DmCargoOnBoard, false);
	}

	public DmCargoOnBoard createDmCargoOnBoard(int id) {
		return persistence.create(id);
	}

	public DmCargoOnBoard deleteDmCargoOnBoard(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmCargoOnBoard deleteDmCargoOnBoard(DmCargoOnBoard DmCargoOnBoard)
		throws SystemException {
		return persistence.remove(DmCargoOnBoard);
	}

	public DmCargoOnBoard fetchDmCargoOnBoard(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmCargoOnBoard getDmCargoOnBoard(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmCargoOnBoard> getDmCargoOnBoards(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmCargoOnBoardsCount() throws SystemException {
		return persistence.countAll();
	}

	public DmCargoOnBoard updateDmCargoOnBoard(DmCargoOnBoard DmCargoOnBoard)
		throws SystemException {
		return updateDmCargoOnBoard(DmCargoOnBoard, true);
	}

	public DmCargoOnBoard updateDmCargoOnBoard(DmCargoOnBoard DmCargoOnBoard,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmCargoOnBoard, merge);
	}


}
