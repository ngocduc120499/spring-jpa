package com.demoJPA.springjpa.service;

import com.demoJPA.springjpa.exceptions.BadRequestException;
import com.demoJPA.springjpa.models.JwtRequestModel;
import com.demoJPA.springjpa.models.JwtResponseModel;
import com.demoJPA.springjpa.models.RegisterRequest;
import com.demoJPA.springjpa.repository.UserRepository;
import com.demoJPA.springjpa.utils.TokenManager;
import lombok.RequiredArgsConstructor;
import com.demoJPA.springjpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final TokenManager tokenManager;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public JwtResponseModel authenticate(JwtRequestModel request) {

        if(userRepository.findByEmail(request.getUsername()).isEmpty()){
            throw new BadRequestException("User does not exist");
        }
        User user = userRepository.findByEmail(request.getUsername()).get();
        String jwtToken = tokenManager.generateJwtToken(user);
        String refreshToken = tokenManager.refreshJwtToken(user);
        return JwtResponseModel.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public JwtResponseModel register(RegisterRequest request){
        Optional<User> exist = userRepository.findByEmail(request.getEmail());
        if(exist.isPresent()){
            throw new IllegalStateException("Email" + request.getEmail() + " exists"); }
        User user = User.builder().firstname(request.getFirstname()).
                    lastname(request.getFirstname()).
                    email(request.getEmail()).
                    password(request.getPassword()).
                    role(request.getRole()).build();
        userRepository.save(user);
        String jwtToken = tokenManager.generateJwtToken(user);
        String refreshToken = tokenManager.refreshJwtToken(user);
        return JwtResponseModel.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}
