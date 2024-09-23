package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmHistoryGoodsType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmHistoryGoodsTypeRepository extends JpaRepository<DmHistoryGoodsType, Integer> {
}