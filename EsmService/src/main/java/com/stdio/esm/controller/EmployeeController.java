package com.stdio.esm.controller;

import com.stdio.esm.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/users")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/user-details")
    @ApiOperation(value = "USER DETAILS 4")
    public ResponseEntity<Map<String,Object>> getUserDetails4(){
        ResponseEntity<Map<String,Object>> responseData = ResponseEntity.status(HttpStatus.OK).body(employeeService.getUserDetails4());
        return responseData;
    }
}
