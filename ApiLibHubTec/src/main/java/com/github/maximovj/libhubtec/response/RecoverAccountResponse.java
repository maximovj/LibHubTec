package com.github.maximovj.libhubtec.response;

import java.util.Optional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecoverAccountResponse {

    ApiResponse response;
    Optional<Object> data;

}
