package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmGoods;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmGoodRepository extends JpaRepository<DmGoods, Integer> {
}