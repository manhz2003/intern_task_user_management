package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.UsersOrg;
import com.fds.nsw.liferay.model.UsersOrgId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersOrgRepository extends JpaRepository<UsersOrg, UsersOrgId> {
}