package com.company.coursya.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.company.coursya.api.dto.files.FileUploadResponse;
import com.company.coursya.service.CloudinaryService;
import com.company.coursya.shared.exceptions.ExceptionCode;
import com.company.coursya.shared.exceptions.exceptions.FileUploadingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Override
    public FileUploadResponse uploadFile(MultipartFile file) {
        try {
            String contentType = file.getContentType();
            String resourceType = contentType != null && contentType.startsWith("video") ? "video" : "image";
            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("resource_type", resourceType)
            );

            String url = result.get("secure_url").toString();

            String baseUrl = "https://res.cloudinary.com";
            if (url.startsWith(baseUrl)) {
                url = url.substring(baseUrl.length());
            }

            return FileUploadResponse.builder().fileUrl(url).build();
        } catch (IOException e) {
            throw new FileUploadingException(ExceptionCode.FILE_UPLOADING_EXCEPTION);
        }
    }
}
