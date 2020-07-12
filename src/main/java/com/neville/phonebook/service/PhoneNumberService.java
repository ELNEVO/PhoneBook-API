package com.neville.phonebook.service;

import com.neville.phonebook.model.PhoneNumberEntry;
import java.util.List;

public interface PhoneNumberService {

  List<PhoneNumberEntry> requestAll();

  PhoneNumberEntry requestOne(int id);

  Integer add(PhoneNumberEntry phoneNumberEntry);

  Integer save(PhoneNumberEntry phoneNumberEntry);

  Integer remove(int id);
}
