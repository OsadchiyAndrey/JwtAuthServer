package com.osadchiy.rentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataResponseEntity<T> extends BaseResponseEntity {

    private final T data;
}
