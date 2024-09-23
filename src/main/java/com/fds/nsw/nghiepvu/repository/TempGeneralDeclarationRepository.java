package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempGeneralDeclarationRepository extends JpaRepository<TempGeneralDeclaration, Long> {
}