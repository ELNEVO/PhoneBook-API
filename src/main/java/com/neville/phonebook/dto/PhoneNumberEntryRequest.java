package com.neville.phonebook.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PhoneNumberEntryRequest {

  @Size(max = 100, message = "Name length cannot be greater than 100")
  private String name;

  @Pattern(
      regexp = "^[+0-9\\-\\(\\)\\s]+$",
      message =
          "Phone number may only contain plus sign, digits, hyphens, parentheses and whitespace")
  @Size(min = 7, max = 25, message = "Phone number length should be between 7 and 25")
  private String phoneNumber;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
