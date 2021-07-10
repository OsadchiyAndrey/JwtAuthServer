package com.osadchiy.rentservice.exception;

import com.osadchiy.rentservice.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    public static final String ERROR_MESSAGE_TEMPLATE = "message: %s %n requested uri: %s";

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseApiException(BaseException e, WebRequest request) {
        return getErrorResponseResponseEntity(request, e.getHttpStatus(), e.getMessage(), e.getClass().getSimpleName(), ExceptionUtils.getStackTrace(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception e, WebRequest request) {
        final HttpStatus status = getStatusForAllExceptions(e);
        final String localizedMessage = e.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = (StringUtils.isNotEmpty(localizedMessage) ? localizedMessage : status.getReasonPhrase());
        log.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path), e);
        return getErrorResponseResponseEntity(request, status, e.getMessage(), e.getClass().getSimpleName(), ExceptionUtils.getStackTrace(e));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e, WebRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return getErrorResponseResponseEntity(request, status, e.getMessage(), e.getClass().getSimpleName(), ExceptionUtils.getStackTrace(e));
    }

    private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(WebRequest request, HttpStatus status,
                                                                         String message, String simpleName,
                                                                         String stackTrace) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status);
        errorResponse.setMessage(message);
        errorResponse.setType(simpleName);
        errorResponse.setUrl(getUrlFromWebRequest(request));
        errorResponse.setStackTrace(stackTrace);
        return new ResponseEntity<>(errorResponse, status);

    }

    private HttpStatus getStatusForAllExceptions(Exception e) {
        ResponseStatus responseStatus = e.getClass().getAnnotation(ResponseStatus.class);
        return responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private static String getUrlFromWebRequest(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}