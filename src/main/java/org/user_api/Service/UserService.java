package org.user_api.Service;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.user_api.Model.User;
import org.user_api.Repository.UserJpaRepository;
import org.user_api.Utils.PasswordHasher;
import org.user_api.Utils.SaltGenerator;
import org.user_api.dto.UserDto;
import org.user_api.exception.EmailAlreadyExistException;
import org.user_api.exception.EmailNotExistException;
import org.user_api.exception.PasswordInvalidException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserJpaRepository userRepository ;
    private final SaltGenerator saltGenerator ;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDto getUser(Long id){
        User user = userRepository.findById(id).orElseThrow() ;
        return new UserDto(user);
    }


    public User save(User user){
        if(existsByEmail(user.getEmail())){
            throw new EmailAlreadyExistException("This email "+user.getEmail()+" is already exist") ;
        }
        String salt = saltGenerator.generateSalt() ;
        user.setSalt(salt);
        String hashedPassword = PasswordHasher.HashPassword(user.getPassword(),salt) ;
        user.setPassword(hashedPassword);
        return userRepository.save(user) ;
    }

    public UserDto update(User user){
        User existingUser = findById(user.getId()).get() ;
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhone(user.getPhone());
        return new UserDto(userRepository.updateUser(user)) ;
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id) ;
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public User login(String email , String password){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new EmailNotExistException("email not found"));
        String HashedPassword = PasswordHasher.HashPassword(password,user.getSalt()) ;
        if(HashedPassword.equals(user.getPassword())){
            return user ;
        }
        else throw new PasswordInvalidException("password invalide !") ;
    }

    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email) ;
    }

}
