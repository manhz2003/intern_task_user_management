package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.DLFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DlfolderRepository extends JpaRepository<DLFolder, Long> {
}