package com.oauth.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    @CreatedDate
    @Column(name="CREATE_DATE_TIME", updatable = false)
    private LocalDateTime createDateTime;

    @LastModifiedDate
    @Column(name="MODIFY_DATE_TIME")
    private LocalDateTime modifyDateTime;
}
