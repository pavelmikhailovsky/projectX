package com.projectX.dto;

import com.projectX.entities.User;
import lombok.*;
import org.springframework.lang.NonNull;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class UserDTO {

    @NonNull
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}
