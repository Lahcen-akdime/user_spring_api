package org.user_api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.user_api.Model.User;
import org.user_api.Service.UserService;
import org.user_api.exception.ExceptionPage;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService ;

    @PostMapping("/login")
    public User login(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("email") ;
            session.setAttribute("email",email);
            return userService.login(email, request.getParameter("password")) ;
        } catch (RuntimeException e) {
            throw new ExceptionPage(e.getMessage());
        }
    }

    @GetMapping("logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false) ;
        session.invalidate();
    }
}
