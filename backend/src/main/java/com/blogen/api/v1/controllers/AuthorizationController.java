package com.blogen.api.v1.controllers;

import com.blogen.api.v1.model.JwtAuthenticationResponse;
import com.blogen.api.v1.model.LoginRequestDTO;
import com.blogen.api.v1.model.UserDTO;
import com.blogen.api.v1.services.AuthorizationService;
import com.blogen.api.v1.validators.UserDtoSignupValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for handling new-user sign-ups and user log-ins
 *
 * Author: Cliff
 */
@Api
@Slf4j
@RestController
@RequestMapping( AuthorizationController.BASE_URL )
public class AuthorizationController {

    public static final String BASE_URL = "/api/v1/auth";

    private AuthorizationService authorizationService;
    private UserDtoSignupValidator userSignupValidator;

    @Autowired
    public AuthorizationController( AuthorizationService authorizationService,
                                    UserDtoSignupValidator userSignupValidator ) {
        this.authorizationService = authorizationService;
        this.userSignupValidator = userSignupValidator;
    }

    @InitBinder("userDTO")
    public void setupBinder( WebDataBinder binder ) {
        binder.addValidators( userSignupValidator );
    }

    @ApiOperation( value = "sign-up a new user", consumes = "application/json", produces = "application/json")
    @PostMapping( "/signup" )
    @ResponseStatus( HttpStatus.CREATED )
    public UserDTO signupUser( @RequestBody @Valid UserDTO userDTO ) {
        log.debug( "signup user userDTO=" + userDTO );
        return authorizationService.signUpUser( userDTO );
    }

    @ApiOperation( value = "login a user", consumes = "application/json", produces = "application/json")
    @PostMapping( "/login" )
    public ResponseEntity<?> login( @RequestBody @Valid LoginRequestDTO loginDTO ) {
        log.debug( "login user loginDTO {}",loginDTO );
        String jwt = authorizationService.authenticateAndLoginUser( loginDTO );
        log.debug( "JWT generated {}",jwt );
        return ResponseEntity.ok( new JwtAuthenticationResponse(jwt) );
    }
}
