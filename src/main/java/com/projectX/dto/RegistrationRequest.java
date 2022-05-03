package com.projectX.dto;

import lombok.*;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class RegistrationRequest {

    @NonNull
    private String login;

    @NonNull
    private String password;
}
