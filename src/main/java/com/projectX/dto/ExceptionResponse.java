package com.projectX.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor
@Getter
public class ExceptionResponse {

    @NonNull
    private String message;
}
