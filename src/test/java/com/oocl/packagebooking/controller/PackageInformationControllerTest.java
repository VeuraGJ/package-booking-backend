package com.oocl.packagebooking.controller;

import com.google.gson.Gson;
import com.oocl.packagebooking.entity.PackageInformation;
import com.oocl.packagebooking.service.PackageInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PackageInformationController.class)
public class PackageInformationControllerTest {
   @Autowired
    private MockMvc mockMvc;
   @MockBean
    private PackageInformationService packageInformationService;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   @Test
    public void should_return_all_packages_when_call_get_api() throws Exception {
       List<PackageInformation> packageInformations = new ArrayList<PackageInformation>();
       packageInformations.add(new PackageInformation("lajods",578687897,0,simpleDateFormat.format(new Date())));
       packageInformations.add(new PackageInformation("las",578687897,0,simpleDateFormat.format(new Date())));
       given(packageInformationService.findAllPackages()).willReturn(packageInformations);
       mockMvc.perform(get("/packages"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(2));
   }
    @Test
    public void should_return_specific_status_packages_when_call_get_with_status_api() throws Exception {
        List<PackageInformation> packageInformations = new ArrayList<PackageInformation>();
        packageInformations.add(new PackageInformation("lajods",578687897,0,simpleDateFormat.format(new Date())));
        packageInformations.add(new PackageInformation("las",578687897,1,simpleDateFormat.format(new Date())));
        packageInformations.add(new PackageInformation("loop",578687897,1,simpleDateFormat.format(new Date())));
        List<PackageInformation> exceptedPackages = packageInformations.stream()
                .filter(packageInformation -> packageInformation.getStatus() ==1)
                .collect(Collectors.toList());
        given(packageInformationService.findAllPackagesByStatus(anyInt())).willReturn(exceptedPackages);
        mockMvc.perform(get("/packages?status=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    public void should_return_update_status_packages_when_call_put_api() throws Exception {
        Gson gson = new Gson();
        PackageInformation packageInformation =new PackageInformation("lajods",578687897,0,
               simpleDateFormat.format(new Date()));
        packageInformation.setId(1);
        packageInformation.setStatus(1);
        given(packageInformationService.updatePackageStatus(any(PackageInformation.class))).willReturn(packageInformation);
        mockMvc.perform(put("/packages/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(packageInformation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(1));
    }
}