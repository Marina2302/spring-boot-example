package com.bystrik.springboot.lesson.repository;

import com.bystrik.springboot.lesson.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeCustomRepository {

    List<EmployeeEntity> findCustomQuery();

}
