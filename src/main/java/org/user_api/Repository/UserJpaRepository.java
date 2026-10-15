package org.user_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user_api.Model.User;

public interface UserJpaRepository extends JpaRepository<User,Long> { }
