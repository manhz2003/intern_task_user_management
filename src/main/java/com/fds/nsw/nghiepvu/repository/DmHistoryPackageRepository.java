package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmHistoryPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmHistoryPackageRepository extends JpaRepository<DmHistoryPackage, Integer> {
}