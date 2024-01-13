package com.example.employeebackend.service;

import com.example.employeebackend.entity.Employee;
import com.example.employeebackend.exception.EmployeeNotFoundException;
import com.example.employeebackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        List<Employee> empList=employeeRepository.findAll();
        return empList;
    }

    public Optional<Employee> getEmployeeById(int id) throws EmployeeNotFoundException {
        Optional<Employee> emp=employeeRepository.findById(id);
        if(emp.isPresent()){
          return emp;
        }
       else throw new EmployeeNotFoundException("Employee with id"+id+" not found");
    }

    public Employee addEmployee(Employee employee){
      return employeeRepository.save(employee);
    }

    public Employee updateEmployeeById(int id, Employee employee) throws EmployeeNotFoundException {
        Optional<Employee> employee1 = employeeRepository.findById(id);

        if (employee1.isPresent()) {
            Employee originalEmployee = employee1.get();

            if (Objects.nonNull(employee.getFirst_name()) && !"".equalsIgnoreCase(employee.getFirst_name())) {
                originalEmployee.setFirst_name(employee.getFirst_name());
            }
            if (Objects.nonNull(employee.getLast_name()) && !"".equalsIgnoreCase(employee.getLast_name())) {
                originalEmployee.setLast_name(employee.getLast_name());
            }
            if (Objects.nonNull(employee.getEmail()) && !"".equalsIgnoreCase(employee.getEmail())) {
                originalEmployee.setEmail(employee.getEmail());
            }

            return employeeRepository.save(originalEmployee);
        }
        else throw new EmployeeNotFoundException("Employee with id"+id+" not found");
    }

    public Map<String,Boolean> deleteEmployeeById(int id) {
       /* if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        }
        return "No such employee in the database";
    }*/
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("id not found"));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
