package com.oocl.packagebooking.service.impl;

import com.oocl.packagebooking.entity.PackageInformation;
import com.oocl.packagebooking.repository.PackageInformationRepository;
import com.oocl.packagebooking.service.PackageInformationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
public class PackageInformationServiceImplTest {
    @TestConfiguration
    static class PackageInformationServiceImplContextConfiguration {

        @Bean
        public PackageInformationService packageInformationService() {
            return new PackageInformationServiceImpl();
        }
    }
     @Autowired
     private PackageInformationService packageInformationService;
     @MockBean
     private PackageInformationRepository packageInformationRepository;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     @Test
     public void should_return_all_Packages_when_call_find_all_function(){
         List<PackageInformation> packageInformations = new ArrayList<PackageInformation>();
         packageInformations.add(
                 new PackageInformation("lajods",578687897,0,simpleDateFormat.format(new Date())));
         packageInformations.add(
                 new PackageInformation("las",578687897,0,simpleDateFormat.format(new Date())));
         Mockito.when(packageInformationRepository.findAll()).thenReturn(packageInformations);
         List<PackageInformation> loadPackageInformations = packageInformationService.findAllPackages();
         Assertions.assertEquals(packageInformations,loadPackageInformations);
     }
    @Test
    public void should_return_specific_status_Packages_when_call_find_all_by_status_function(){
        List<PackageInformation> packageInformations = new ArrayList<PackageInformation>();
        packageInformations.add(new PackageInformation("lajods",578687897,0,simpleDateFormat.format(new Date())));
        packageInformations.add(new PackageInformation("las",578687897,1,simpleDateFormat.format(new Date())));
        packageInformations.add(new PackageInformation("loop",578687897,1,simpleDateFormat.format(new Date())));
        List<PackageInformation> exceptedPackages = packageInformations.stream()
                                                    .filter(packageInformation -> packageInformation.getStatus() ==1)
                                                    .collect(Collectors.toList());
        Mockito.when(packageInformationRepository.findAll()).thenReturn(packageInformations);
        List<PackageInformation> loadPackageInformations = packageInformationService.findAllPackagesByStatus(1);
        Assertions.assertEquals(exceptedPackages,loadPackageInformations);
    }
    @Test
    public void should_return_update_status_Package_when_call_update_status_function(){
        PackageInformation packageInformation = new PackageInformation("lajods",578687897,0,simpleDateFormat.format(new Date()));
        packageInformation.setId(1);
        Mockito.when(packageInformationRepository.findById(packageInformation.getId())).thenReturn(java.util.Optional.of(packageInformation));
        packageInformation.setStatus(1);
        Mockito.when(packageInformationRepository.save(packageInformation)).thenReturn(packageInformation);
        PackageInformation updatePackage = packageInformationService.updatePackageStatus(packageInformation);
        Assertions.assertEquals(packageInformation.getStatus(),updatePackage.getStatus());
    }
    @Test
    public void should_return_update_order_time_Package_when_call_update_orderTime_function(){
        PackageInformation packageInformation = new PackageInformation("lajods",578687897,0,simpleDateFormat.format(new Date()));
        packageInformation.setId(1);
        Mockito.when(packageInformationRepository.findById(packageInformation.getId())).thenReturn(java.util.Optional.of(packageInformation));
        packageInformation.setOrderTime("2019-06-06 10:56:00");
        Mockito.when(packageInformationRepository.save(packageInformation)).thenReturn(packageInformation);
        PackageInformation updatePackage = packageInformationService.updatePackageOrderTime(packageInformation);
        Assertions.assertEquals(packageInformation.getOrderTime(),updatePackage.getOrderTime());
    }
 }