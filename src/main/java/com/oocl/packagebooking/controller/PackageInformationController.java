package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.PackageInformation;
import com.oocl.packagebooking.service.PackageInformationService;
import com.oocl.packagebooking.service.impl.PackageInformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/packages/{id}")
    public ResponseEntity updatePackageStatus(@PathVariable long id, @RequestBody PackageInformation packageInformation){
        packageInformation.setId(id);
        if(packageInformationService.updatePackageStatus(packageInformation)!=null){
            return ResponseEntity.ok(packageInformationService.updatePackageStatus(packageInformation));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
