package com.real_estate.demo.domain;

import com.real_estate.demo.domain.enums.Status;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //entity의 상위클래스 되도록
@EntityListeners(AuditingEntityListener.class) //Auditing 기능 포함
public abstract class BaseEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NORMAL;

    /**
     * soft delete
     * */
    public void softDelete() {
        this.status = Status.DELETE;
    }

    /**
     * rollback
     * */
    public void rollback() {
        this.status = Status.NORMAL;
    }
}
