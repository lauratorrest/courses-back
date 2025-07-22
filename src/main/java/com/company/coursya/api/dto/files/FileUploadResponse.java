package com.company.coursya.api.dto.files;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class FileUploadResponse {

    private String fileUrl;
}
