package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.GroupsUsergroup;
import com.fds.nsw.liferay.model.GroupsUsergroupId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GroupsUsergroupRepository extends JpaRepository<GroupsUsergroup, GroupsUsergroupId> {
}