package com.deviceomi.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Data
@Entity
@Table(name="history")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "createdDate")
    @CreatedDate
    private Date createdDate;

    @Column(name="content")
    private String content;

    @Column(name="descriptions")
    private String editObject;

    @Column(name="page")
    private String page;


}
