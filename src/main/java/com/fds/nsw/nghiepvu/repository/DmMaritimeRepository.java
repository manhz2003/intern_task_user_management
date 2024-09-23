package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmMaritime;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmMaritimeRepository extends JpaRepository<DmMaritime, Integer> {
}