package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempShipStoresDeclarationRepository extends JpaRepository<TempShipStoresDeclaration, Long> {
}