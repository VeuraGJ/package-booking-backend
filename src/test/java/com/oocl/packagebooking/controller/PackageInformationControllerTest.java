package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.PackageInformation;
import com.oocl.packagebooking.service.PackageInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PackageInformationController.class)
public class PackageInformationControllerTest {
   @Autowired
    private MockMvc mockMvc;
   @MockBean
    private PackageInformationService packageInformationService;
   @Test
    public void should_return_all_packages_when_call_get_api() throws Exception {
       List<PackageInformation> packageInformations = new ArrayList<PackageInformation>();
       packageInformations.add(new PackageInformation("lajods",578687897,0,new Date()));
       packageInformations.add(new PackageInformation("las",578687897,0,new Date()));
       given(packageInformationService.findAllPackages()).willReturn(packageInformations);
       mockMvc.perform(get("/packages"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(2));
   }
}