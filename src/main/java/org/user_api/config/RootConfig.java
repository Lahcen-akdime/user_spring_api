package org.user_api.config;

import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan(value = "org.user_api", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {
                Controller.class,RestController.class
        }) ,
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebConfig.class)
})
@Import(DatabaseConfig.class)
public class RootConfig {

        @Bean
        public BCryptPasswordEncoder PasswordEncoder(){
                return null ;
        }



}