package com.osadchiy.rentservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class BaseResponseEntity {

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("message")
    private String message;

    public BaseResponseEntity() {
        status = HttpStatus.OK;
        message = "Success!";
    }
}
