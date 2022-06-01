package com.stdio.esm.controller;


import com.stdio.esm.service.AccountService;
import com.stdio.esm.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/list-users")
    @ApiOperation(value = "LIST USERS")
    public ResponseEntity<String> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body("users");
    }

    @PostMapping(path = "/add-user")
    @ApiOperation(value = "ADD USER")
    public ResponseEntity<String> addUser(){
        return ResponseEntity.status(HttpStatus.OK).body("add user");
    }

    @PutMapping(path = "/edit-user")
    @ApiOperation(value = "UPDATE USER")
    public ResponseEntity<String> updateUser(){
        return ResponseEntity.status(HttpStatus.OK).body("update user");
    }

    @DeleteMapping(path = "/delete-user")
    @ApiOperation(value = "DELETE USER")
    public ResponseEntity<String> deleteUser(){
        return ResponseEntity.status(HttpStatus.OK).body("delete user");
    }

    @PostMapping("/change-password")
    @ApiOperation("CHANGE PASSWORD")
    public ResponseEntity<String> changePassword(@NotNull @RequestBody Map<String,String> request) {
        accountService.changePassword(request);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PostMapping("/reset-password")
    @ApiOperation("RESET PASSWORD")
    public ResponseEntity<?> resetPassword(@RequestParam("username")String userName) {
        userService.resetPassword(userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
