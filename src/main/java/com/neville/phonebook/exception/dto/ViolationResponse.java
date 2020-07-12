package com.neville.phonebook.exception.dto;

public class ViolationResponse {

  private final String fieldName;
  private final String message;

  public ViolationResponse(String fieldName, String message) {
    this.fieldName = fieldName;
    this.message = message;
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getMessage() {
    return message;
  }
}
