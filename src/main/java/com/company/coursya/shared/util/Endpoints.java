package com.company.coursya.shared.util;

import java.util.List;

public class Endpoints {

    public static final String API_PATH = "/coursya/v1/api";
    public static final String REGISTER_PATH = "/register";
    public static final String SIGN_IN_PATH = "/sign-in";
    public static final String FIND_USER_BY_EMAIL = "/user/find-by-email";
    public static final String FIND_DETAILED_USER_BY_EMAIL = "/user/details-by-email";
    public static final String UPDATE_USER_DETAILS = "/user/update-details";
    public static final String UPDATE_PROFILE_PIC_URL = "/user/update-pic-url";
    public static final String UPLOAD_FILE = "/file/upload";
    public static final String UPLOAD_FILE_BLOB = "/blob/file/upload";
    public static final String FIND_COURSES = "/course/find-all";
    public static final String CREATE_NEW_COURSE = "/course/save";

    public static final String[] PUBLIC_ENDPOINTS = {
            API_PATH + REGISTER_PATH,
            API_PATH + SIGN_IN_PATH,
            API_PATH + FIND_USER_BY_EMAIL,
            API_PATH + FIND_DETAILED_USER_BY_EMAIL,
            API_PATH + UPDATE_USER_DETAILS,
            API_PATH + UPDATE_PROFILE_PIC_URL,
            API_PATH + UPLOAD_FILE,
            API_PATH + UPLOAD_FILE_BLOB,
            API_PATH + FIND_COURSES,
            API_PATH + CREATE_NEW_COURSE,
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html"
    };

    public static final List<String> VALID_PATHS = List.of(
            API_PATH + REGISTER_PATH,
            API_PATH + SIGN_IN_PATH
    );
}
