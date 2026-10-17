package org.user_api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.user_api.Model.Role;
import org.user_api.Model.User;

@Getter
public class UserDto {

    private String name ;

    private String email ;

    private String password ;

    private Role role ;

    public UserDto(User user){
        name = user.getName();
        email = user.getEmail() ;
        password = user.getPassword() ;
        role = user.getRole() ;
    }


}
