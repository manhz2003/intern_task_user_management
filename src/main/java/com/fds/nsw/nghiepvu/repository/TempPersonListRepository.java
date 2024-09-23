package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempPersonList;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempPersonListRepository extends JpaRepository<TempPersonList, Long> {
}