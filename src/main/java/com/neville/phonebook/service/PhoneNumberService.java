package com.neville.phonebook.service;

import com.neville.phonebook.dao.PhoneNumberDao;
import com.neville.phonebook.model.PhoneNumberEntry;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {

  private PhoneNumberDao dao;

  @Autowired
  public PhoneNumberService(PhoneNumberDao dao) {
    this.dao = dao;
  }

  public List<PhoneNumberEntry> requestAll() {
    return dao.getAll();
  }

  public PhoneNumberEntry requestOne(int id) {
    return dao.getOne(id);
  }

  public Integer add(PhoneNumberEntry phoneNumberEntry) {
    return dao.create(phoneNumberEntry);
  }

  public Integer save(PhoneNumberEntry phoneNumberEntry) {
    return dao.update(phoneNumberEntry);
  }

  public Integer remove(int id) {
    return dao.delete(id);
  }
}
