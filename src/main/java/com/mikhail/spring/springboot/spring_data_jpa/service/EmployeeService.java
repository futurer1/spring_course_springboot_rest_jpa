package com.mikhail.spring.springboot.spring_data_jpa.service;

import com.mikhail.spring.springboot.spring_data_jpa.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public void delEmployee(int empId);

    public Employee getEmployee(int empId);

    public List<Employee> findAllByName(String name);

    public List<Employee> findAllByNameAndSalaryIsBetween(String name, Integer minCount, Integer maxCount);

    public List<Employee> findAllByNam(String name);

    public List<Employee> findMeDataByName(String name);
}