package com.neville.phonebook.mapper;

import com.neville.phonebook.dto.PhoneNumberEntryRequest;
import com.neville.phonebook.model.PhoneNumberEntry;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberEntryMapper {

  public PhoneNumberEntry map(PhoneNumberEntryRequest request, Integer id) {
    return new PhoneNumberEntry(id, request.getName(), request.getPhoneNumber());
  }

  public PhoneNumberEntry map(PhoneNumberEntryRequest request) {
    return map(request, null);
  }
}
