package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Usertrackerpath;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsertrackerpathRepository extends JpaRepository<Usertrackerpath, Long> {
}