package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.UsersTeam;
import com.fds.nsw.liferay.model.UsersTeamId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersTeamRepository extends JpaRepository<UsersTeam, UsersTeamId> {
}