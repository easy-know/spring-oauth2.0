package com.oauth.developer.repository;

import com.oauth.developer.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByEmail(String email);
    Optional<Developer> findById(Long id);
}
