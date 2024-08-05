package com.skysoft.krd.collegemanagementapi;

import com.skysoft.krd.collegemanagementapi.clinets.EmployeeClient;
import com.skysoft.krd.collegemanagementapi.clinets.impl.EmployeeClientImpl;
import com.skysoft.krd.collegemanagementapi.config.RestClinetConfig;
import com.skysoft.krd.collegemanagementapi.dto.clinet.EmployeeDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CollegeManagementApiApplicationTests {

    @Autowired
    EmployeeClient employeeServiceClient;
    @Autowired
    private RestClinetConfig restClinetConfig;



    @Test
    void contextLoads() {
    }

    @Test
    @Order(3)
    void callingApiRestClientEmployee(){
        List<EmployeeDto> employeeDtoList=employeeServiceClient.employeeList();
    }

    @Test
    @Order(2)
    void getEmployeeByID(){
        EmployeeDto employeeDto=employeeServiceClient.getEmployeeById(2);

    }

    @Test
    @Order(1)
    void addEmployee(){
        EmployeeDto employeeDtoTest=new EmployeeDto(1l,"aras","aras@gmail.com",90,null,false,"ADMIN",1.1);
       EmployeeDto savedEmployee= employeeServiceClient.createEmployee(employeeDtoTest);
    }

    @Test
    @Order(1)
    void salaryInERU(){
        double salaryInERU= employeeServiceClient.liveSalaryInEUR(1);
        System.out.println("1 dollar is equal to "+salaryInERU);

    }

}
