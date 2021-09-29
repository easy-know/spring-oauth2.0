package com.oauth.user.repository;

import com.oauth.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUid(String email);
}
