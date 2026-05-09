package com.example.hrm.service;

import org.springframework.web.multipart.MultipartFile;

public interface FaceRecognitionService {

    boolean verifyFace(
            String avatarUrl,
            MultipartFile image
    );
}
