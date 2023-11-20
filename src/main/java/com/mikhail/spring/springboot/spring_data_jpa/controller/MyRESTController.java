package com.mikhail.spring.springboot.spring_data_jpa.controller;

import com.mikhail.spring.springboot.spring_data_jpa.entity.Employee;
import com.mikhail.spring.springboot.spring_data_jpa.service.EmployeeService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee showEmployee(@PathVariable int id) {
        Employee emp = employeeService.getEmployee(id);

        return emp;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {

        Employee emp = employeeService.getEmployee(employee.getId());

        employeeService.saveEmployee(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);

        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String delEmployee(@PathVariable int id) {

        Employee emp = employeeService.getEmployee(id);

        employeeService.delEmployee(id);
        return "Employee ID = " + id + " was deleted.";
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee> showAllByName(@PathVariable String name) {

        return employeeService.findAllByName(name);
    }

    // маппинг для URL вида http://localhost:8080/api/employees/name/1/Oleg?min=100&max=990
    @GetMapping("/employees/name/1/{name}")
    public List<Employee> showAllByNameAndSalaryBetween(
            @PathVariable String name,
            @RequestParam(value = "min") Integer min, // ?min=100
            @RequestParam(value = "max") Integer max // &max=990
    ) {
        return employeeService.findAllByNameAndSalaryIsBetween(name, min, max);
    }

    @GetMapping("/employees/name/2/{name}")
    public List<Employee> findAllByNam(@PathVariable String name) {
        return employeeService.findAllByNam(name);
    }

    @GetMapping("/employees/name/3/{name}")
    public List<Employee> findMeDataByName(@PathVariable String name) {
        return employeeService.findMeDataByName(name);
    }
}
