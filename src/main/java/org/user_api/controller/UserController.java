package org.user_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.user_api.Model.User;
import org.user_api.Repository.UserJpaRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserJpaRepository userRepository ;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll() ;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userRepository.findById(id).orElseGet(null) ;
    }

    @PostMapping("users")
    public User save(@RequestBody User user){
        return userRepository.save(user) ;
    }

    @DeleteMapping("users/{id}")
    public HttpStatusCode delete(@PathVariable("id") Long id){
         userRepository.deleteById(id);
         return HttpStatusCode.valueOf(200) ;
    }

    @PutMapping("users/{id}")
    public User update(@PathVariable("id") Long id , @RequestBody User user){
        User existingUser = userRepository.findById(id).get() ;
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhone(user.getPhone());
        return userRepository.save(existingUser) ;
    }
}
