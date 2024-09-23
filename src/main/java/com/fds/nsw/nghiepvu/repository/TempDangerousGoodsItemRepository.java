package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempDangerousGoodsItemRepository extends JpaRepository<TempDangerousGoodsItems, Long> {
}