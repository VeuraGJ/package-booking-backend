package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.PackageInformation;
import com.oocl.packagebooking.service.PackageInformationService;
import com.oocl.packagebooking.service.impl.PackageInformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8081")
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
    @PutMapping(value = "/packages/{id}",params = {"status"})
    public ResponseEntity updatePackageStatus(@PathVariable long id, @RequestParam int status){
        PackageInformation packageInformation = new PackageInformation();
        packageInformation.setId(id);
        packageInformation.setStatus(status);
        if(packageInformationService.updatePackageStatus(packageInformation)!=null){
            return ResponseEntity.ok(packageInformationService.updatePackageStatus(packageInformation));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/packages/{id}", params = {"orderTime"})
    public ResponseEntity updatePackageOrderTime(@PathVariable long id, @RequestParam String orderTime){
        PackageInformation packageInformation = new PackageInformation();
        packageInformation.setId(id);
        packageInformation.setOrderTime(orderTime);
        if(packageInformationService.updatePackageOrderTime(packageInformation)!=null){
            return ResponseEntity.ok(packageInformationService.updatePackageOrderTime(packageInformation));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
