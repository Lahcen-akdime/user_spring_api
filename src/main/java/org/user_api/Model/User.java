package org.user_api.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotNull
    private String name ;

    @Column(unique = true)
    @NotNull
    private String email ;

    @NotNull
    private String password ;

    @NotNull
    private String phone ;

    private String salt ;

    private Role role = Role.USER ;

    private LocalDateTime createdAt = LocalDateTime.now() ;

    private LocalDateTime updatedAt ;

    @PreUpdate
    private void onUpdate(){
        updatedAt = LocalDateTime.now() ;
    }

}
