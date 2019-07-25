package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.entity.PackageInformation;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class PackageInformationRepositoryTest {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private PackageInformationRepository packageInformationRepository;

    @Before
    public void setUp() throws Exception {
        PackageInformation packageInformation = new PackageInformation("sla",1432434,0,"2018-09-11 09:23:00");
        PackageInformation packageInformation1 = new PackageInformation("la",1432434,1,"2018-09-17 09:23:00");
        entityManager.persist(packageInformation);
        entityManager.persist(packageInformation1);
    }

    @Test
    public void should_return_specific_status_package(){
        List<PackageInformation> packageInformationList = packageInformationRepository.findAllByStatus(1);
        Assertions.assertEquals(packageInformationList.size(),1);
    }
}