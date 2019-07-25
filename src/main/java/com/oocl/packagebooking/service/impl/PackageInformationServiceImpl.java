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
    public List<PackageInformation> findAllPackages(int status) {
        if(status == -1){
            return packageInformationRepository.findAll();
        }else{
            return packageInformationRepository.findAllByStatus(status);
        }
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

    @Override
    public PackageInformation updatePackageOrderTime(PackageInformation packageInformation) {
        PackageInformation oldpackage = packageInformationRepository.findById(packageInformation.getId()).orElse(null);
        if(oldpackage == null){
            return null;
        }
        oldpackage.setOrderTime(packageInformation.getOrderTime());
        return packageInformationRepository.save(oldpackage);
    }

}
