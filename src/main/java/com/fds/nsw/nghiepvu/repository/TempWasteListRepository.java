package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempWasteList;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempWasteListRepository extends JpaRepository<TempWasteList, Long> {
}