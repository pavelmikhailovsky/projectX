package com.projectX.dto;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class TokenReturnResponse {

    @NonNull
    private String token;
}
