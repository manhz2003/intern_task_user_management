package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.DlfileentrytypesDlfolder;
import com.fds.nsw.liferay.model.DlfileentrytypesDlfolderId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DlfileentrytypesDlfolderRepository extends JpaRepository<DlfileentrytypesDlfolder, DlfileentrytypesDlfolderId> {
}