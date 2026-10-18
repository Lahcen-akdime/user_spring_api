package org.user_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.user_api.Model.User;
import org.user_api.Repository.UserJpaRepository;
import org.user_api.Service.UserService;
import org.user_api.dto.UserDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService ;

    @GetMapping
    public List<User> getUsers(){
        return userService.findAll() ;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.findById(id).orElseGet(null) ;
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService.save(user) ;
    }

    @DeleteMapping("/{id}")
    public HttpStatusCode delete(@PathVariable("id") Long id){
        userService.deleteById(id);
        return HttpStatusCode.valueOf(200) ;
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") Long id , @RequestBody User user){
        return userService.update(user) ;
    }
}
