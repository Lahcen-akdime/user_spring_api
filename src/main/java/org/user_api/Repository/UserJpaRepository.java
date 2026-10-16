package org.user_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user_api.Model.User;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User,Long> {
    public Optional<User> findByEmail(String email) ;
}
