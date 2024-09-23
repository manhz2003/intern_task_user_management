package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmVRCode;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmVrcodeRepository extends JpaRepository<DmVRCode, Integer> {
}