package com.oauth.base;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
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
public class BaseEntity extends BaseTimeEntity{
    @Column(name="CREATE_ID", updatable = false)
    protected Long createId;

    @Column(name="MODIFY_ID")
    protected Long modifyId;

    @Column
    @ColumnDefault("1")
    private Integer useYn;
}
