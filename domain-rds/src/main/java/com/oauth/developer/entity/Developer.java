package com.oauth.developer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oauth.application.entity.Application;
import com.oauth.base.BaseTimeEntity;
import com.oauth.base.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Developer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "developer_id", insertable = false, updatable = false)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "developer")
    @JsonIgnore
    private List<Application> applicationList;
}
