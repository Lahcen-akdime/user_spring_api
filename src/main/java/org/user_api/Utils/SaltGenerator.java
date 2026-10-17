package org.user_api.Utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64 ;

@Component
public class SaltGenerator {

    public String generateSalt(){
        SecureRandom secureRandom = new SecureRandom() ;
        byte[] salt = new byte[16] ;
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt) ;
    }

}