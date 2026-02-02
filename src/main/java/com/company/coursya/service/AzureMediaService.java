package com.company.coursya.service;

import com.company.coursya.api.dto.files.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AzureMediaService {

    FileUploadResponse uploadImage(MultipartFile file, String folder);
}
