package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempCargoItems;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempCargoItemRepository extends JpaRepository<TempCargoItems, Long> {
}