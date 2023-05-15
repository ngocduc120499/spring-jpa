package com.demoJPA.springjpa.models;

import lombok.*;

import javax.swing.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String token;
    private String refreshToken;


    public String getRefreshToken() {
        return refreshToken;
    }

    public String getToken() {
        return token;
    }
}