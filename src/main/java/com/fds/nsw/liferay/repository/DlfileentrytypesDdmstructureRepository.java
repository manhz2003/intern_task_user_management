package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.DlfileentrytypesDdmstructure;
import com.fds.nsw.liferay.model.DlfileentrytypesDdmstructureId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DlfileentrytypesDdmstructureRepository extends JpaRepository<DlfileentrytypesDdmstructure, DlfileentrytypesDdmstructureId> {
}