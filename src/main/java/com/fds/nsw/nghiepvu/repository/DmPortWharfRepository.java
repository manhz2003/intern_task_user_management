package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmPortWharf;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmPortWharfRepository extends JpaRepository<DmPortWharf, Integer> {
}