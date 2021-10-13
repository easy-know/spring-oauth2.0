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
    Application findByNameAndDeveloper_Id(String name, Long developerId);
    List<Application> findAllByDeveloper_Id(@Param(value = "developerId") Long developerId, Sort sort);
    Long countAllByDeveloperId(Long id);
}
