package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Usertracker;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsertrackerRepository extends JpaRepository<Usertracker, Long> {
}