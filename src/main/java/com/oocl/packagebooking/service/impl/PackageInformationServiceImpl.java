package com.oocl.packagebooking.service.impl;

import com.oocl.packagebooking.entity.PackageInformation;
import com.oocl.packagebooking.repository.PackageInformationRepository;
import com.oocl.packagebooking.service.PackageInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageInformationServiceImpl implements PackageInformationService {
    @Autowired
    private PackageInformationRepository packageInformationRepository;
    @Override
    public List<PackageInformation> findAllPackages() {
        return packageInformationRepository.findAll();
    }

    @Override
    public List<PackageInformation> findAllPackagesByStatus(int status) {
        return packageInformationRepository.findAll()
                .stream()
                .filter(packageInformation -> packageInformation.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public PackageInformation updatePackageStatus(PackageInformation packageInformation) {
        PackageInformation oldpackage = packageInformationRepository.findById(packageInformation.getId()).orElse(null);
        if(oldpackage == null){
            return null;
        }
        oldpackage.setStatus(packageInformation.getStatus());
        return packageInformationRepository.save(oldpackage);
    }
}
