package com.bystrik.springboot.lesson.repository;

import static com.bystrik.springboot.lesson.entity.QEmployeeEntity.employeeEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bystrik.springboot.lesson.IntegrationTestBase;
import com.bystrik.springboot.lesson.dto.EmployeeFilter;
import com.bystrik.springboot.lesson.entity.EmployeeEntity;
import com.bystrik.springboot.lesson.projection.EmployeeNameView;
import com.bystrik.springboot.lesson.projection.EmployeeNativeView;
import com.bystrik.springboot.lesson.util.QPredicates;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

class EmployeeRepositoryTest extends IntegrationTestBase {

    public static final Integer IVAN_ID = 1;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testFindById() {
        Optional<EmployeeEntity> employee = employeeRepository.findById(IVAN_ID);
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstName() {
        Optional<EmployeeEntity> employee = employeeRepository.findByFirstNameContaining("va");
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstNameAndSalary() {
        List<EmployeeEntity> employees = employeeRepository.findAllByFirstNameAndSalary("Ivan", 1000);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testFindBySalary() {
        List<EmployeeNameView> employees = employeeRepository.findAllBySalaryGreaterThan(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindBySalaryNative() {
        List<EmployeeNativeView> employees = employeeRepository.findAllBySalaryGreaterThanNative(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindCustomQuery() {
        EmployeeFilter filter = EmployeeFilter.builder()
                                              .firstName("ivaN")
                                              .build();
        List<EmployeeEntity> employees = employeeRepository.findByFilter(filter);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testQuerydslPredicates() {
        BooleanExpression predicate = employeeEntity.firstName.containsIgnoreCase("ivaN")
                                                              .and(employeeEntity.salary.goe(1000));
        Page<EmployeeEntity> allValue = employeeRepository.findAll(predicate, Pageable.unpaged());
        assertThat(allValue.getContent(), hasSize(1));
    }

    @Test
    void testQPredicates() {
        EmployeeFilter filter = EmployeeFilter.builder()
                                              .firstName("ivaN")
                                              .salary(1000)
                                              .build();
        Predicate predicate = QPredicates.builder()
                                         .add(filter.getFirstName(), employeeEntity.firstName::containsIgnoreCase)
                                         .add(filter.getLastName(), employeeEntity.lastName::containsIgnoreCase)
                                         .add(filter.getSalary(), employeeEntity.salary::goe)
                                         .buildAnd();

        Iterable<EmployeeEntity> result = employeeRepository.findAll(predicate);
        assertTrue(result.iterator().hasNext());
        System.out.println();
    }

}