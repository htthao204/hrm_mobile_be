package com.example.hrm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "request_types")
public class RequestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RequestTypeCode code;

    private String name;

    public RequestType() {}

    public RequestType(int id, RequestTypeCode code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public RequestTypeCode getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(RequestTypeCode code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}