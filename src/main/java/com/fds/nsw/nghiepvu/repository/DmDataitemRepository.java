package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmDataitem;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmDataitemRepository extends JpaRepository<DmDataitem, Long> {
}