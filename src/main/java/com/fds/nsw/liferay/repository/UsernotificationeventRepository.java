package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Usernotificationevent;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsernotificationeventRepository extends JpaRepository<Usernotificationevent, Long> {
}