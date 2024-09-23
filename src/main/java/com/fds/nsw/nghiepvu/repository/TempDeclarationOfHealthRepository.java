package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempDeclarationOfHealthRepository extends JpaRepository<TempDeclarationOfHealth, Long> {
}