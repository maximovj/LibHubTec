package com.github.maximovj.libhubtec.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecoverAccountRequest {

    String sub;
    String code;
    String token;
    String new_password;
    String confirm_password;

}
