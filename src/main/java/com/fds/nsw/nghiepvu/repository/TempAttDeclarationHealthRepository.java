package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempAttDeclarationHealthRepository extends JpaRepository<TempAttDeclarationHealth, Long> {
}