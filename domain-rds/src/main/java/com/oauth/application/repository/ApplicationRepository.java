package com.oauth.application.repository;

import com.oauth.application.entity.Application;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByName(String name);
    List<Application> findAllByDeveloper_Id(@Param(value = "developerId") Long developerId, Sort sort);
}
