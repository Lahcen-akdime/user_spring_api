package org.user_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.user_api.Model.User;
import org.user_api.Repository.UserJpaRepository;
import org.user_api.Service.UserService;

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
    public User save(@Valid @RequestBody User user){
        return userService.save(user) ;
    }

    @DeleteMapping("/{id}")
    public HttpStatusCode delete(@PathVariable("id") Long id){
        userService.deleteById(id);
        return HttpStatusCode.valueOf(200) ;
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") Long id , @RequestBody User user){
        User existingUser = userService.findById(id).get() ;
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhone(user.getPhone());
        return userService.save(existingUser) ;
    }
}
