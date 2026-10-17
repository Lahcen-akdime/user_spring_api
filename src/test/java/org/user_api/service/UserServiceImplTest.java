package org.user_api.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.user_api.Model.User;
import org.user_api.Repository.UserJpaRepository;
import org.user_api.Service.UserService;
import org.user_api.Utils.PasswordHasher;
import org.user_api.Utils.SaltGenerator;
import org.user_api.dto.UserDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @InjectMocks
    private UserService userService ;

    @Mock
    private SaltGenerator saltGenerator ;

    @Mock
    private PasswordHasher passwordHasher ;

    //private UserDto user = userService.getUser(6L) ;
    //private UserDto user2 = userService.getUser(9L) ;

    @Test
    void findById_user(){
       // when(userService.findById(1L)).thenReturn(Optional.empty());
        when(userJpaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(1L);

        assertTrue(result.isEmpty());
}

}
