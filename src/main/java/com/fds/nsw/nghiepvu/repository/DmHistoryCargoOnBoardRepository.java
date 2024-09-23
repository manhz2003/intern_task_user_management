package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmHistoryCargoOnBoardRepository extends JpaRepository<DmHistoryCargoOnBoard, Integer> {
}