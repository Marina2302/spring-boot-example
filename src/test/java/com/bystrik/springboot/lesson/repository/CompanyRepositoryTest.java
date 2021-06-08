package com.bystrik.springboot.lesson.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bystrik.springboot.lesson.IntegrationTestBase;
import com.bystrik.springboot.lesson.entity.CompanyEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class CompanyRepositoryTest extends IntegrationTestBase {

    public static final Integer APPLE_ID = 1;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetById() {
        Optional<CompanyEntity> company = companyRepository.findById(APPLE_ID);
        assertTrue(company.isPresent());
        company.ifPresent(companyEntity -> {
            assertEquals("Apple", companyEntity.getName());
        });
    }

    @Test
    void testSave() {
        CompanyEntity companyEntity = CompanyEntity.builder()
                                                   .name("Fitbit")
                                                   .build();
        companyRepository.save(companyEntity);
        assertNotNull(companyEntity.getId());
    }

}