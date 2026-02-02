package com.company.coursya.api.controller;

import com.company.coursya.api.dto.files.FileUploadResponse;
import com.company.coursya.service.AzureMediaService;
import com.company.coursya.shared.util.Endpoints;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Tag(name = "Coursya Files API", description = "End-points for files management functions")
@CrossOrigin("*")
@RestController
@RequestMapping(Endpoints.API_PATH)
public class FileController {

    private final AzureMediaService azureMediaService;


    @Operation(summary = "Upload Picture Azure")
    @PostMapping(Endpoints.UPLOAD_FILE_BLOB)
    public ResponseEntity<FileUploadResponse> uploadBlobPic(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(azureMediaService.uploadImage(file, "users/profile-images"));
    }
}
