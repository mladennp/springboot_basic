package com.alibu.example.student;

import jakarta.validation.constraints.NotNull;

public record StudentDto(
        @NotNull
        String name,
        @NotNull
        String lastName,
        String email,
        Integer school_id
) {
}
