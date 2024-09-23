package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.GroupsRole;
import com.fds.nsw.liferay.model.GroupsRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRoleRepository extends JpaRepository<GroupsRole, GroupsRoleId> {
}