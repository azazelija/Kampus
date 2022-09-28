package ru.kampus.rest;

import liquibase.repackaged.org.apache.commons.lang3.exception.ExceptionUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ApiError {

    String message;

    String detail;

    public ApiError(Throwable ex) {
        this.message = ex.getMessage();
        this.detail = ExceptionUtils.getStackTrace(ex);
    }
}
