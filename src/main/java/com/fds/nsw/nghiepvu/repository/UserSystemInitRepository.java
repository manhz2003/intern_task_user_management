package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.UserSystemInit;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserSystemInitRepository extends JpaRepository<UserSystemInit, Integer> {
}