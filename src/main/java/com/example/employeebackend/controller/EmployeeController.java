package com.example.employeebackend.controller;

import com.example.employeebackend.entity.Employee;
import com.example.employeebackend.exception.EmployeeNotFoundException;
import com.example.employeebackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }
    //get all emp

    @GetMapping("/getAllEmp")
    public List<Employee> getEmployee(){
        return employeeService.getAllEmployees();
    }

    //get imp by id
    @GetMapping("/get/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(id);
    }


    //update emp
    @PutMapping("/update/{id}")
    public Optional<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee employee) throws EmployeeNotFoundException {
        return Optional.ofNullable(employeeService.updateEmployeeById(id, employee));
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployeeById(id);
    }

}
