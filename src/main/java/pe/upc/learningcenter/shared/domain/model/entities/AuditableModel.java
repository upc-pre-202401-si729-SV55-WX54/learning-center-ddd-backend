package pe.upc.learningcenter.shared.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableModel {

    @Getter
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date createAt;

    @Getter
    @LastModifiedDate
    @Column(nullable = false)
    private Date updateAt;


}
