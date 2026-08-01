package com.lab.employee_service.repository;

import com.lab.employee_service.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Employee findEmployeeById(String userId);
}