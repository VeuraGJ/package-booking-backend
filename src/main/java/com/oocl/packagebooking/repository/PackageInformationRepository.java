package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.entity.PackageInformation;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PackageInformationRepository extends JpaRepository<PackageInformation,Long> {
    List<PackageInformation> findAllByStatus(int status);
}
