package com.github.maximovj.libhubtec.response;

import java.util.List;
import java.util.Optional;

import com.github.maximovj.libhubtec.model.NotificationAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationAccountResponse {

    ApiResponse response;
    
    Optional<List<NotificationAccount>> data;

}
