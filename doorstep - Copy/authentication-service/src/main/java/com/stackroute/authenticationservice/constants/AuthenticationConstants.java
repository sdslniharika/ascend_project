package com.stackroute.authenticationservice.constants;

public class AuthenticationConstants {
    private AuthenticationConstants() {
    }

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String MESSAGE_MISSING_TOKEN = "Authorization token is missing in request";
    public static final String MESSAGE_INVALID_TOKEN = "Authorization token is invalid";
    public static final String MESSAGE_EXPIRED_TOKEN = "Authorization token has expired";
    public static final String MESSAGE_USER_UNAUTHORIZED = "User is not authorized to do this action";
}
