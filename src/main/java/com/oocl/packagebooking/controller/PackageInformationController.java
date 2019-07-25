package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.service.PackageInformationService;
import com.oocl.packagebooking.service.impl.PackageInformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackageInformationController {
    @Autowired
    private PackageInformationService packageInformationService;
    @GetMapping("/packages")
    public ResponseEntity getAllPackages(@RequestParam(name ="status",defaultValue = "-1")int status){
        if(status == -1) {
            return ResponseEntity.ok(packageInformationService.findAllPackages());
        }else{
            return ResponseEntity.ok(packageInformationService.findAllPackagesByStatus(status));
        }
    }
}
