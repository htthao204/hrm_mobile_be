package com.example.hrm.service.impl;

import com.example.hrm.service.FaceRecognitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FaceRecognitionServiceImpl
        implements FaceRecognitionService {

    private final RestTemplate restTemplate;

    @Override
    public boolean verifyFace(
            String avatarUrl,
            MultipartFile image
    ) {

        try {

            String url = "http://localhost:5000/verify-face";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(
                    MediaType.MULTIPART_FORM_DATA
            );

            MultiValueMap<String, Object> body =
                    new LinkedMultiValueMap<>();

            // send avatar url
            body.add("avatarUrl", avatarUrl);

            // send image
            body.add("image", new ByteArrayResource(
                    image.getBytes()
            ) {
                @Override
                public String getFilename() {
                    return image.getOriginalFilename();
                }
            });

            HttpEntity<MultiValueMap<String, Object>>
                    requestEntity =
                    new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            requestEntity,
                            Map.class
                    );

            Object matched =
                    response.getBody().get("matched");

            return Boolean.TRUE.equals(matched);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Face verification failed"
            );
        }
    }
}