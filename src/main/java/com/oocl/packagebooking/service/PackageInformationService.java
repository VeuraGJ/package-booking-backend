package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.PackageInformation;
import com.oocl.packagebooking.repository.PackageInformationRepository;

import java.util.List;

public interface PackageInformationService {
    List<PackageInformation> findAllPackages();
    List<PackageInformation> findAllPackagesByStatus(int status);
}
