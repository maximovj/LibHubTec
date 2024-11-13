package com.github.maximovj.libhubtec.response;

import java.util.List;
import java.util.Optional;

import com.github.maximovj.libhubtec.model.AuthTokenData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {

    private ApiResponse response;
    private Optional<AuthTokenData> data;
   
}
