package org.user_api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class UserDto {

    private String name ;

    private String email ;

    private String password ;

    private String phone ;

}
