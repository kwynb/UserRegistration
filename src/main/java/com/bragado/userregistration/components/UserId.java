package com.bragado.userregistration.components;

import javax.validation.Constraint;

@Constraint(validatedBy = UserIdValidator.class)
public @interface UserId {
}
