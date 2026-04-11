package com.example.hrm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "request_types")
public class RequestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code; // LEAVE, ATTENDANCE, WFH, OVERTIME
    private String name;

    public RequestType() {
    }

    public RequestType(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}