package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.PackageInformation;
import com.oocl.packagebooking.repository.PackageInformationRepository;

import java.util.List;

public interface PackageInformationService {
    List<PackageInformation> findAllPackages(int status);
    PackageInformation updatePackageStatus(PackageInformation packageInformation);
    PackageInformation updatePackageOrderTime(PackageInformation packageInformation);
}
