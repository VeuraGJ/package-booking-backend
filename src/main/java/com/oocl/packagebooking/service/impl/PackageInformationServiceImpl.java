package com.oocl.packagebooking.service.impl;

import com.oocl.packagebooking.entity.PackageInformation;
import com.oocl.packagebooking.repository.PackageInformationRepository;
import com.oocl.packagebooking.service.PackageInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageInformationServiceImpl implements PackageInformationService {
    @Autowired
    private PackageInformationRepository packageInformationRepository;
    @Override
    public List<PackageInformation> findAllPackages() {
        return packageInformationRepository.findAll();
    }
}
