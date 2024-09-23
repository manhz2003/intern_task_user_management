package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.UserPort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserPortRepository extends JpaRepository<UserPort, Long> {
    UserPort findById(int id);
    UserPort findByUserId(long userId);
}