package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Dlfileversion;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DlfileversionRepository extends JpaRepository<Dlfileversion, Long> {
}