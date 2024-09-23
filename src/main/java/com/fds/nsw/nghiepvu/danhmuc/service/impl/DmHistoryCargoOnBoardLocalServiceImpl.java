package com.fds.nsw.nghiepvu.danhmuc.service.impl;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.model.DmCargoOnBoard;
import com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryCargoOnBoardPersistenceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DmHistoryCargoOnBoardLocalServiceImpl {
    @Autowired
    DmHistoryCargoOnBoardPersistenceImpl persistence;
    public DmHistoryCargoOnBoard fetchByGoodsTypeCode_SyncVersion(String goodsTypeCode, String syncVersion){
        try{
            return persistence.fetchByF_goodsTypeCode_syncVersion(goodsTypeCode, syncVersion);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public DmHistoryCargoOnBoard addDmHistoryCargoOnBoard(DmHistoryCargoOnBoard DmHistoryCargoOnBoard)
		throws SystemException {
		return persistence.updateImpl(DmHistoryCargoOnBoard, false);
	}

	public DmHistoryCargoOnBoard createDmHistoryCargoOnBoard(int id) {
		return persistence.create(id);
	}

	public DmHistoryCargoOnBoard deleteDmHistoryCargoOnBoard(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmHistoryCargoOnBoard deleteDmHistoryCargoOnBoard(DmHistoryCargoOnBoard DmHistoryCargoOnBoard)
		throws SystemException {
		return persistence.remove(DmHistoryCargoOnBoard);
	}

	public DmHistoryCargoOnBoard fetchDmHistoryCargoOnBoard(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmHistoryCargoOnBoard getDmHistoryCargoOnBoard(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmHistoryCargoOnBoard> getDmHistoryCargoOnBoards(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmHistoryCargoOnBoardsCount() throws SystemException {
		return persistence.countAll();
	}

	public DmHistoryCargoOnBoard updateDmHistoryCargoOnBoard(DmHistoryCargoOnBoard DmHistoryCargoOnBoard)
		throws SystemException {
		return updateDmHistoryCargoOnBoard(DmHistoryCargoOnBoard, true);
	}

	public DmHistoryCargoOnBoard updateDmHistoryCargoOnBoard(DmHistoryCargoOnBoard DmHistoryCargoOnBoard,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmHistoryCargoOnBoard, merge);
	}

}
