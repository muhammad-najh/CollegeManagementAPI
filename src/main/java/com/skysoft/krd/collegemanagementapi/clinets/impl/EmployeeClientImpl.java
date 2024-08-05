package com.skysoft.krd.collegemanagementapi.clinets.impl;

import com.skysoft.krd.collegemanagementapi.advices.APIResponse;
import com.skysoft.krd.collegemanagementapi.clinets.EmployeeClient;
import com.skysoft.krd.collegemanagementapi.dto.clinet.CurrencyDto;
import com.skysoft.krd.collegemanagementapi.dto.clinet.EmployeeDto;
import com.skysoft.krd.collegemanagementapi.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service // service to communicate to service

public class EmployeeClientImpl implements EmployeeClient {

    Logger logger=LoggerFactory.getLogger(EmployeeClientImpl.class);



    private final RestClient employeeRestClient;
    private final RestClient freeCurrencyApiClient;

    public EmployeeClientImpl(@Qualifier("EmployeeClientImpl") RestClient employeeRestClient,
    @Qualifier("freecurrencyapiClient") RestClient freeCurrencyApiClient
    ) {
        this.employeeRestClient = employeeRestClient;
        this.freeCurrencyApiClient = freeCurrencyApiClient;
    }

    @Override
    public List<EmployeeDto> employeeList() {
        logger.trace("Trying to retrieve employee list in employeeList()");
        try {
            logger.info("Attempting to call the restClient Method in employeeList()");
            APIResponse<List<EmployeeDto>> employeesList = employeeRestClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        logger.error(new String(res.getBody().readAllBytes()));
                    })

                    .body(new ParameterizedTypeReference<>(){});

            logger.debug("Successfully retrieved employee list in employeeList()");
            logger.trace("Successfully retrieved employee list in employeeList() : {},{},{}",employeesList.getData(),"test",5);

            return employeesList.getData();

        } catch (Exception e){
            logger.error("Exception occurred while retrieving employee list in employeeList() : error is {}",e);
           throw new RuntimeException();
        }
    }

    @Override
    public EmployeeDto getEmployeeById(int id) {
        logger.trace("Trying to retrieve employee by id in getEmployeeById() when id is {}",id);
        try {

            APIResponse<EmployeeDto> employeeDto = employeeRestClient.get().uri("employees/{id}", id)
                                .retrieve()
                               .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                               logger.error(new String(res.getBody().readAllBytes()));
                                  })
                                .body(new ParameterizedTypeReference<>() {
                                });

            logger.debug("Successfully retrieved employee by id in getEmployeeById() : {}",id);
            logger.trace("Successfully get employee by id in getEmployeeById() id : {},{}",id,employeeDto.getData());

        return employeeDto.getData();

        }catch (Exception e){
            logger.error("Exception occurred while retrieving employee by id in getEmployeeById() : {}",e);
            throw new RuntimeException();
        }


    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        logger.info("Trying to save employee in createEmployee Function Employee Object : {}",employeeDto);
        try {

       APIResponse<EmployeeDto> savedEmployeeService= employeeRestClient.post().uri("employees/create")
                .body(employeeDto)
                .retrieve()
               .onStatus(HttpStatusCode::is4xxClientError,(rq,res)->{
                   logger.error(new String(res.getBody().readAllBytes()));
                   throw new ResourceNotFoundException("Employee not found");
               })
                .body(new ParameterizedTypeReference<>() {});
            logger.debug("successfully create new Employee detail :{}",employeeDto);
        return savedEmployeeService.getData();

        }catch (Exception e){ //if clinet is off not running
            logger.error("Exception Occur in create Employeee : {}",e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public double liveSalaryInEUR(int id) {
        //employee id maybe we don't need it for test purpose
        logger.info("Tring to connect to FreeCurrencyAPI");
        try {
            APIResponse<CurrencyDto> currencyAPIResponse= freeCurrencyApiClient.get()

                    .retrieve()

                    .onStatus(HttpStatusCode::is4xxClientError,(rq,res)->{
                        logger.error(new String(res.getBody().readAllBytes()));
                    }).body(new ParameterizedTypeReference<>() {});

            logger.info("Successfully connected to FreeCurrencyAPI {} :",currencyAPIResponse.getData().getEUR());
                logger.debug("Successfully connected to FreeCurrencyAPI");
           return currencyAPIResponse.getData().getEUR();


        }catch (Exception e){
            logger.error("Exception Occur in create Employeee : {}",e.getMessage());
        }
        return 0;
    }
}
