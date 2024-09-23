package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmMinistry;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmMinistryRepository extends JpaRepository<DmMinistry, Long> {
}