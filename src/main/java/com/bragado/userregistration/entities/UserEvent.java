package com.bragado.userregistration.entities;


import com.bragado.userregistration.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEvent {
    private String event;

    @NotNull
    private UserDTO userDTO;
}
