package com.demoJPA.springjpa.service;

import com.demoJPA.springjpa.entity.User;
import com.demoJPA.springjpa.exceptions.BadRequestException;
import com.demoJPA.springjpa.models.JwtRequestModel;
import com.demoJPA.springjpa.models.JwtResponseModel;
import com.demoJPA.springjpa.models.RegisterRequest;
import com.demoJPA.springjpa.models.Role;
import com.demoJPA.springjpa.repository.UserRepository;
import com.demoJPA.springjpa.utils.TokenManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    TokenManager tokenManager;
    @InjectMocks
    AuthenticationService authenticationService;

    @Test
    void canAuthenticate(){
        JwtRequestModel requestModel = JwtRequestModel.builder()
                .username("admin@mail.com")
                .password("password").build();
        User user = User.builder().userId(1)
                    .firstname("firstName")
                    .lastname("lastName")
                    .email("email@email.com")
                    .password("password")
                    .role(Role.ADMIN).build();
        given(userRepository.findByEmail(requestModel.getUsername())).willReturn(Optional.of(user));
        given(tokenManager.generateJwtToken(user)).willReturn("jwtToken");
        given(tokenManager.refreshJwtToken(user)).willReturn("refreshToken");
        JwtResponseModel response = authenticationService.authenticate(requestModel);
        assertNotNull(response);
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
        assertEquals("jwtToken", response.getToken());
        assertEquals("refreshToken", response.getRefreshToken());
    }

    @Test
    void willThrowWhenUserDoesNotExist(){
        JwtRequestModel requestModel = JwtRequestModel.builder()
                .username("admin@mail.com")
                .password("password").build();
        given(userRepository.findByEmail(requestModel.getUsername())).willReturn(Optional.empty());
        assertThatThrownBy(()->authenticationService.authenticate(requestModel))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("User does not exist");

        verify(userRepository, times(1)).findByEmail(Mockito.any());
    }

    @Test
    void canRegister(){
        RegisterRequest requestModel = RegisterRequest.builder()
                .firstname("firstName")
                .lastname("lastName")
                .email("email@email.com")
                .password("password")
                .role(Role.ADMIN).build();

        User user = User.builder()
                .firstname("firstName")
                .lastname("lastName")
                .email("email@email.com")
                .password("password")
                .role(Role.ADMIN).build();

        String jwtToken = "jwtToken";
        String refreshToken = "refreshToken";
        given(userRepository.findByEmail(requestModel.getEmail())).willReturn(Optional.empty());
        given(tokenManager.generateJwtToken(any(User.class))).willReturn(jwtToken);
        given(tokenManager.refreshJwtToken(any(User.class))).willReturn(refreshToken);
        JwtResponseModel response = authenticationService.register(requestModel);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userArgumentCaptor.capture());
        User capturedCapture = userArgumentCaptor.getValue();

        assertThat(capturedCapture.getUsername()).isEqualTo(user.getUsername());
        assertThat(capturedCapture.getPassword()).isEqualTo(user.getPassword());
        assertThat(capturedCapture.getAuthorities()).isEqualTo(user.getAuthorities());
        assertNotNull(response);
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
        assertEquals("jwtToken", response.getToken());
        assertEquals("refreshToken", response.getRefreshToken());

    }

    @Test
    void willThrowWhenUserExist(){
        RegisterRequest requestModel = RegisterRequest.builder()
                .firstname("firstName")
                .lastname("lastName")
                .email("email@email.com")
                .password("password")
                .role(Role.ADMIN).build();

        User user = User.builder()
                .firstname("firstName")
                .lastname("lastName")
                .email("email@email.com")
                .password("password")
                .role(Role.ADMIN).build();
        given(userRepository.findByEmail(requestModel.getEmail())).willReturn(Optional.of(user));
        assertThatThrownBy(()->authenticationService.register(requestModel))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Email" + requestModel.getEmail() + " exists");

        verify(userRepository, times(1)).findByEmail(Mockito.any());
        verify(userRepository,never()).save(any());
    }
}