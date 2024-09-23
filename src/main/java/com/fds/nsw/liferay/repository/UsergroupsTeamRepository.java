package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.UsergroupsTeam;
import com.fds.nsw.liferay.model.UsergroupsTeamId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsergroupsTeamRepository extends JpaRepository<UsergroupsTeam, UsergroupsTeamId> {
}