package com.projectX.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class LoginRequest {

    @NonNull
    private String login;

    @NonNull
    private String password;
}
