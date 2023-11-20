package com.mikhail.spring.springboot.spring_data_jpa.service;

import com.mikhail.spring.springboot.spring_data_jpa.dao.EmployeeRepository;
import com.mikhail.spring.springboot.spring_data_jpa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delEmployee(int empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public Employee getEmployee(int empId) {
        Employee employee = null;
        Optional<Employee> employeeOptional = employeeRepository.findById(empId);
        if (employeeOptional.isPresent()) {
            employee = employeeOptional.get();
        }

        return employee;
    }

    @Override
    public List<Employee> findAllByName(String name) {
        List<Employee> employees = employeeRepository.findAllByName(name);
        return employees;
    }

    @Override
    public List<Employee> findAllByNameAndSalaryIsBetween(String name, Integer minCount, Integer maxCount) {
        List<Employee> employees = employeeRepository.findAllByNameAndSalaryIsBetween(name, minCount, maxCount);
        return employees;
    }

    @Override
    public List<Employee> findAllByNam(String name) {
        List<Employee> employees = employeeRepository.findAllByNam(name);
        return employees;
    }

    @Override
    public List<Employee> findMeDataByName(String name) {
        List<Employee> employees = employeeRepository.findMeDataByName(name);
        return employees;
    }
}