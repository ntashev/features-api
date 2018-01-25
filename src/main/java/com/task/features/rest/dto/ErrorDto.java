package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Data transfer object representation of error.
 */
public class ErrorDto {

    private String errorMessage;

    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errorMessage", errorMessage)
                .toString();
    }
}
