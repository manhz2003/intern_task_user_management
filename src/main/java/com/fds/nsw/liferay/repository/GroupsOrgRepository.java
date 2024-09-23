package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.GroupsOrg;
import com.fds.nsw.liferay.model.GroupsOrgId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GroupsOrgRepository extends JpaRepository<GroupsOrg, GroupsOrgId> {
}