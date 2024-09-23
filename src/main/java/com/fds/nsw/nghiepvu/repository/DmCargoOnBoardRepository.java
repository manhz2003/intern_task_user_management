package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmCargoOnBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmCargoOnBoardRepository extends JpaRepository<DmCargoOnBoard, Integer> {
}