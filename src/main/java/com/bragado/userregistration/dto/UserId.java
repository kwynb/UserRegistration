package com.bragado.userregistration.dto;

import javax.validation.Constraint;

@Constraint(validatedBy = UserIdValidator.class)
public @interface UserId {
}
