package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.AswmsgMessagequeue;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AswmsgMessagequeueRepository extends JpaRepository<AswmsgMessagequeue, Long> {
}