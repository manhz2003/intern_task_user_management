package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaScheduleTugboatListRepository extends JpaRepository<VmaScheduleTugboatList, Long> {
}