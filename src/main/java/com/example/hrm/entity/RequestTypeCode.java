package com.example.hrm.entity;

public enum RequestTypeCode {

    LEAVE("Nghỉ phép"),
    ATTENDANCE("Chấm công"),
    BUSINESS_TRIP("Công tác"),
    WFH("Làm việc từ xa"),
    OVERTIME("Tăng ca");

    private final String label;

    RequestTypeCode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}