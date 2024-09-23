package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmPackageRepository extends JpaRepository<DmPackage, Integer> {
}