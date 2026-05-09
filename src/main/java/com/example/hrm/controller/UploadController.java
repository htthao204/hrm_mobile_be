package com.example.hrm.controller;

import com.example.hrm.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<?> upload(
            @RequestParam("image") MultipartFile image
    ) {

        String imageUrl = cloudinaryService.uploadImage(image);

        return ResponseEntity.ok(imageUrl);
    }
}