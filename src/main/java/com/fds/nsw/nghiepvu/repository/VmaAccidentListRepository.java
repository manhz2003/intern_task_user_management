package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaAccidentList;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaAccidentListRepository extends JpaRepository<VmaAccidentList, Long> {
}