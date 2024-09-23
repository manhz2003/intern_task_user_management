package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempGoodsItems;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempGoodsItemRepository extends JpaRepository<TempGoodsItems, Long> {
}