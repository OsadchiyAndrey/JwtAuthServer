package com.osadchiy.rentservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponse extends BaseResponseEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("time")
    private LocalDateTime timestamp;

    @JsonProperty("url")
    private String url;

    @JsonProperty("type")
    private String type;

    @JsonProperty("stack_trace")
    private String stackTrace;

    public ErrorResponse() {
        timestamp = LocalDateTime.now();
    }
}
