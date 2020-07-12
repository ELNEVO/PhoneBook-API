package com.neville.phonebook.rest;

import com.neville.phonebook.dto.PhoneNumberEntryRequest;
import com.neville.phonebook.mapper.PhoneNumberEntryMapper;
import com.neville.phonebook.model.PhoneNumberEntry;
import com.neville.phonebook.service.PhoneNumberService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phoneNumber")
public class PhoneNumberController {

  private PhoneNumberService service;
  private PhoneNumberEntryMapper mapper;

  @Autowired
  public PhoneNumberController(PhoneNumberService service, PhoneNumberEntryMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<PhoneNumberEntry>> requestAll() {
    List<PhoneNumberEntry> phoneBookEntries = service.requestAll();
    HttpStatus httpStatus = !phoneBookEntries.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
    return new ResponseEntity<>(phoneBookEntries, httpStatus);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PhoneNumberEntry> requestOne(@PathVariable("id") Integer id) {
    PhoneNumberEntry phoneNumberEntry = service.requestOne(id);
    return new ResponseEntity<>(phoneNumberEntry, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Integer> add(@Valid @RequestBody PhoneNumberEntryRequest request) {
    Integer id = service.add(mapper.map(request));
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Integer> save(
      @PathVariable("id") Integer id, @Valid @RequestBody PhoneNumberEntryRequest request) {
    Integer affectedRows = service.save(mapper.map(request, id));
    return new ResponseEntity<>(affectedRows, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Integer> remove(@PathVariable("id") Integer id) {
    Integer affectedRows = service.remove(id);
    return new ResponseEntity<>(affectedRows, HttpStatus.OK);
  }
}
