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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     @Test
     public void should_return_all_Packages_when_call_find_all_function(){
         List<PackageInformation> packageInformations = new ArrayList<PackageInformation>();
         packageInformations.add(new PackageInformation("lajods",578687897,0,new Date()));
         packageInformations.add(new PackageInformation("las",578687897,0,new Date()));
         Mockito.when(packageInformationRepository.findAll()).thenReturn(packageInformations);
         List<PackageInformation> loadPackageInformations = packageInformationService.findAllPackages();
         Assertions.assertEquals(packageInformations,loadPackageInformations);
     }
 }