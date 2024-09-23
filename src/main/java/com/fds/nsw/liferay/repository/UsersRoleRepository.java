package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.UsersRole;
import com.fds.nsw.liferay.model.UsersRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersRoleRepository extends JpaRepository<UsersRole, UsersRoleId> {

}