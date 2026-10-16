package org.user_api.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "users")
@Data
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

    @NotNull
    private String salt ;

}
