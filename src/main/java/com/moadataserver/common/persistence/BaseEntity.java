package com.moadataserver.common.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {

    @CreatedDate
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createDateTime;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATETIME")
    private LocalDateTime lastModifiedDateTime;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @Column(name = "DELETED_DATETIME")
    private LocalDateTime deletedDateTime;

    // 삭제
    public void deleteSoftly(LocalDateTime deletedDateTime) {
        this.deletedDateTime = deletedDateTime;
    }

    // 확인
    public boolean isSoftDeleted() {
        return null != deletedDateTime;
    }
    // 삭제 취소
    public void undoDeletion(){
        this.deletedDateTime = null;
    }

}
