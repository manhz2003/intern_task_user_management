package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmRepresentativeRepository extends JpaRepository<DmRepresentative, Integer> {
}