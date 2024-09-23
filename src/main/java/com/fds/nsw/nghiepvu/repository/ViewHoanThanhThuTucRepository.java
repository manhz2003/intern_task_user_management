package com.fds.nsw.nghiepvu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc;

@Repository
public interface ViewHoanThanhThuTucRepository extends JpaRepository<ViewHoanThanhThuTuc, String>{
	Optional<ViewHoanThanhThuTuc> findByCvhh(String cvhh);
}
