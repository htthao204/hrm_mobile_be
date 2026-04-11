package com.example.hrm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "request_attachments")
@Getter
@Setter
public class RequestAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    private String fileName;

    // link S3 / server
    private String fileUrl;

    // image/pdf/doc
    private String fileType;

    private Long fileSize;
}