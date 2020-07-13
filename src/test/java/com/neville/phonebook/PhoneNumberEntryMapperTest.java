package com.neville.phonebook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.neville.phonebook.dto.PhoneNumberEntryRequest;
import com.neville.phonebook.mapper.PhoneNumberEntryMapper;
import com.neville.phonebook.model.PhoneNumberEntry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PhoneNumberEntryMapperTest {

  @Autowired
  private PhoneNumberEntryMapper mapper;

  private static PhoneNumberEntryRequest entryRequest;

  @BeforeAll
  public static void init() {
    entryRequest = new PhoneNumberEntryRequest();
    entryRequest.setName("John Doe");
    entryRequest.setPhoneNumber("+24 (579) 821-2314");
  }

  @Test
  @DisplayName("Test mapping with id")
  public void testMappingWithId() {
    PhoneNumberEntry entry = mapper.map(entryRequest, 8);
    assertEquals(entry.getId(), 8);
  }

  @Test
  @DisplayName("Test mapping without id")
  public void testMappingWithoutId() {
    PhoneNumberEntry entry = mapper.map(entryRequest);
    assertNull(entry.getId());
  }
}
