package com.oauth.user.repository;

import com.oauth.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
