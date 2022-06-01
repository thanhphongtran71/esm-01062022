package com.stdio.esm.controller;


import com.stdio.esm.service.SecurityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */

@RestController
@RequestMapping(path = "/api/public")
@Validated
public class AuthController {

    @Autowired
    private SecurityService securityService;

    /**
     * Login with username and password
     *
     * @param username {@link String}
     * @param password {@link String}
     * @return {@link ResponseEntity<Map<String,Object>>}
     */

    @PostMapping(value = "/login")
    @ApiOperation(value = "LOGIN")
    public ResponseEntity<Map<String,Object>> login(@NotBlank @RequestParam(name  = "username",required = false) String username,
                                                       @NotBlank @RequestParam(name = "password") String password){
        Map<String,Object> responseData = securityService.login(username,password);
        ResponseEntity<Map<String,Object>> response = ResponseEntity.status(HttpStatus.OK).body(responseData);
        return response;
    }

    /**
     * Get new access token from refresh token
     *
     * @param request refreshToken {@link String}
     * @return {@link ResponseEntity<Map<String,Object>>}
     */
    @PostMapping("/refresh-token")
    @ApiOperation(value = "GET NEW ACCESS TOKEN")
    public ResponseEntity<String> refreshToken(@NotBlank @RequestParam(name = "refreshToken") String request){
        String responseData = securityService.getNewAccessTokenFromRefreshToken(request);
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK).body(responseData);
        return response;
    }


}
