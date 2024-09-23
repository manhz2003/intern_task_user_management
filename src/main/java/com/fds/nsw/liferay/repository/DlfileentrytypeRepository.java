package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Dlfileentrytype;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DlfileentrytypeRepository extends JpaRepository<Dlfileentrytype, Long> {
}