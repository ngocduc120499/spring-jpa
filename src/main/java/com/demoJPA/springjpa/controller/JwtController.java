package com.demoJPA.springjpa.controller;

import com.demoJPA.springjpa.models.JwtRequestModel;
import com.demoJPA.springjpa.models.JwtResponseModel;
import com.demoJPA.springjpa.models.RegisterRequest;
import com.demoJPA.springjpa.service.AuthenticationService;
import com.demoJPA.springjpa.utils.JwtUserDetailsService;
import com.demoJPA.springjpa.utils.RestfulUtils;
import com.demoJPA.springjpa.utils.TokenManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class JwtController {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    public ResponseEntity createToken(@RequestBody JwtRequestModel request) throws Exception {
        RestfulUtils.checkMissingField(request.getUsername(), "username");
        RestfulUtils.checkMissingField(request.getPassword(), "password");
        JwtResponseModel jwtResponseModel = authenticationService.authenticate(request);
        return ResponseEntity.ok(jwtResponseModel);
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) throws Exception {
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        JwtResponseModel jwtResponseModel = authenticationService.register(request);
//        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(jwtResponseModel);
    }
}