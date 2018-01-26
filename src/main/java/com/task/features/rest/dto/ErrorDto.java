package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDto errorDto = (ErrorDto) o;
        return Objects.equals(errorMessage, errorDto.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errorMessage", errorMessage)
                .toString();
    }
}
