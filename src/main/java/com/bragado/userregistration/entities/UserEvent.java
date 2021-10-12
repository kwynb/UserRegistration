package com.bragado.userregistration.entities;


import com.bragado.userregistration.dto.UserDTO;
import lombok.*;

import javax.validation.constraints.NotNull;


@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {
    private String event;

    @NotNull
    private UserDTO userDTO;
}
