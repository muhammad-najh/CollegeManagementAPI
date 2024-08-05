package com.skysoft.krd.collegemanagementapi.clinets;


import com.skysoft.krd.collegemanagementapi.dto.clinet.EmployeeDto;

import java.util.List;

public interface EmployeeClient {

 List<EmployeeDto> employeeList();
 EmployeeDto getEmployeeById(int id);
 EmployeeDto createEmployee(EmployeeDto employeeDto);
 double liveSalaryInEUR(int id);
}
