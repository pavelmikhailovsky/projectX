package com.projectX.dto;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class TokenReturn {

    @NonNull
    private String token;
}
