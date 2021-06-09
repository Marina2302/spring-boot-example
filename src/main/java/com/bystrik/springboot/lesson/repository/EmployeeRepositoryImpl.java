package com.bystrik.springboot.lesson.repository;

import static com.bystrik.springboot.lesson.entity.QEmployeeEntity.employeeEntity;

import com.bystrik.springboot.lesson.dto.EmployeeFilter;
import com.bystrik.springboot.lesson.entity.EmployeeEntity;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<EmployeeEntity> findByFilter(EmployeeFilter employeeFilter) {
        return new JPAQuery<EmployeeEntity>(entityManager)
                .select(employeeEntity)
                .from(employeeEntity)
                .where(employeeEntity.firstName.containsIgnoreCase(employeeFilter.getFirstName()))
                .fetch();
    }

}
