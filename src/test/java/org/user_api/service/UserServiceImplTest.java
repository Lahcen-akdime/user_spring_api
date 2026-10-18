package org.user_api.service;

import org.junit.jupiter.api.BeforeEach;
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
import org.user_api.exception.EmailAlreadyExistException;
import org.user_api.exception.EmailNotExistException;
import org.user_api.exception.PasswordInvalidException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    private User user ;

    @Test
    void findById_user() {
        when(userJpaRepository.findById(20L)).thenReturn(Optional.empty());
        Optional<User> result = userService.findById(20L);
        assertTrue(result.isEmpty());
        verify(userJpaRepository).findById(20L);
    }

    @BeforeEach
    void insertUser(){
        user = new User() ;
        user.setId(20L);
        user.setEmail("email@gmail.com");
        user.setPassword("email1235");
        user.setName("email");
        user.setPhone("54568523");
    }

    @Test
    void returnUsers(){
        when(userJpaRepository.findAll()).thenReturn(List.of(user)) ;
        List<User> users = userService.findAll() ;
        assertEquals(1,users.size());
        assertEquals("email@gmail.com" ,users.get(0).getEmail());
        verify(userJpaRepository).findAll() ;
    }

    @Test
    void saveUserWithHashedPassword() {
        when(userJpaRepository.existsByEmail("email@gmail.com"))
                .thenReturn(false);
        when(saltGenerator.generateSalt()).thenReturn("test-salt");
        when(userJpaRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        User result = userService.save(user);
        assertEquals("test-salt", result.getSalt());
        assertNotEquals("okee123", result.getPassword());
        assertEquals(
                result.getPassword(),
                org.user_api.Utils.PasswordHasher.HashPassword(
                        "email1235", "test-salt"));
        verify(userJpaRepository).save(user);
    }

    @Test
    void passwordHashTest(){
        when(userJpaRepository.existsByEmail("email")).thenReturn(false) ;
        when(saltGenerator.generateSalt()).thenReturn("newSalt") ;
        //User user =
    }

    @Test
    void rejectDuplicateEmail() {
        when(userJpaRepository.existsByEmail("email@gmail.com"))
                .thenReturn(true);
        assertThrows(
                EmailAlreadyExistException.class,
                () -> userService.save(user));
        verify(userJpaRepository, never()).save(any(User.class));
    }

    @Test
    void login() {
        when(userJpaRepository.findByEmail("email@gmail.com"))
                .thenReturn(Optional.of(user));

        user.setSalt("test-salt");
        user.setPassword(
                org.user_api.Utils.PasswordHasher.HashPassword("email1235", "test-salt"));
        User result = userService.login(
                "email@gmail.com", "email1235");
        assertSame(user , result);
    }

    @Test
    void rejectPassword() {
        user.setSalt("test-salt");
        user.setPassword(
                org.user_api.Utils.PasswordHasher.HashPassword("okee222", "test-salt")
        );

        when(userJpaRepository.findByEmail("lahcen@email.com")).thenReturn(Optional.of(user));
        assertThrows(
                PasswordInvalidException.class,
                () -> userService.login("lahcen@email.com", "Password21"));
    }

    @Test
    void rejectEmail() {
        when(userJpaRepository.findByEmail("email@gmail.com")).thenReturn(Optional.empty());
        assertThrows(EmailNotExistException.class,
                () -> userService.login("email@gmail.com", "okee222"));
    }

    @Test
    void deleteUser() {
        userService.deleteById(20L);
        verify(userJpaRepository).deleteById(20L);
    }

}
