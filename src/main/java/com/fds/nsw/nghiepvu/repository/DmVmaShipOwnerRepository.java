package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmVmaShipOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmVmaShipOwnerRepository extends JpaRepository<DmVmaShipOwner, Long> {
}