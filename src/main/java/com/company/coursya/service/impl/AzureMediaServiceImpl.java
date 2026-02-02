package com.company.coursya.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.company.coursya.api.dto.files.FileUploadResponse;
import com.company.coursya.service.AzureMediaService;
import com.company.coursya.shared.exceptions.ExceptionCode;
import com.company.coursya.shared.exceptions.exceptions.FileUploadingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AzureMediaServiceImpl implements AzureMediaService {

    private final BlobContainerClient containerClient;

    @Override
    public FileUploadResponse uploadImage(MultipartFile file, String folder) {
        try {
            String blobName = folder + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            BlobClient blobClient = containerClient.getBlobClient(blobName);

            BlobHttpHeaders headers = new BlobHttpHeaders()
                    .setContentType(file.getContentType());

            blobClient.upload(file.getInputStream(), file.getSize(), true);
            blobClient.setHttpHeaders(headers);

            String fileUrl = blobClient.getBlobUrl();

            return FileUploadResponse.builder()
                    .fileUrl(fileUrl)
                    .build();

        } catch (IOException e) {
            throw new FileUploadingException(ExceptionCode.FILE_UPLOADING_EXCEPTION);
        }
    }

}
