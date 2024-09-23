package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.UserSign;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserSignRepository extends JpaRepository<UserSign, Long> {
}