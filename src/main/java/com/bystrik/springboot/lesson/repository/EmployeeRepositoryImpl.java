package com.bystrik.springboot.lesson.repository;

import com.bystrik.springboot.lesson.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl  implements EmployeeCustomRepository{

    private final EntityManager entityManager;

    @Override
    public List<EmployeeEntity> findCustomQuery() {
        return Collections.emptyList();
    }

}
