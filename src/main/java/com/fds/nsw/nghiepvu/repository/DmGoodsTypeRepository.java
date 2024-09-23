package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmGoodsType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmGoodsTypeRepository extends JpaRepository<DmGoodsType, Integer> {
}