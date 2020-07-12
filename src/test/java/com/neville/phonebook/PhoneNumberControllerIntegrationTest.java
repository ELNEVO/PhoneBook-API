package com.neville.phonebook;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.neville.phonebook.dao.PhoneNumberDao;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class PhoneNumberControllerIntegrationTest {

  @Autowired private MockMvc mvc;

  @Autowired private PhoneNumberDao dao;

  @Test
  @DisplayName("Get non empty list of phone numbers - response status code: 200")
  @Order(1)
  public void givenPhoneNumbers_whenGetAllPhoneNumbers_thenStatus200() throws Exception {
    mvc.perform(get("/phoneNumber").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(10)))
        .andExpect(jsonPath("$[4].name").value("Leandra Palphramand"));
  }

  @Test
  @DisplayName("Get one existing phone number - response status code: 200")
  @Order(2)
  public void givenPhoneNumbers_whenGetOnePhoneNumberProvidingId5_thenStatus200() throws Exception {
    mvc.perform(get("/phoneNumber/5").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("Leandra Palphramand"));
  }

  @Test
  @DisplayName("Get non-existing phone number - response status code: 404")
  @Order(3)
  public void givenPhoneNumbers_whenGetOnePhoneNumberProvidingId15_thenStatus404()
      throws Exception {
    mvc.perform(get("/phoneNumber/15").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Add one phone number - response status code: 201")
  @Order(4)
  public void givenPhoneNumbers_whenAddPhoneNumber_thenStatus201() throws Exception {
    String phoneNumberEntry =
        "{\"name\":\"Claiborne Willson\", \"phoneNumber\":\"+380 (407) 735-7954\"}";
    mvc.perform(
            post("/phoneNumber").contentType(MediaType.APPLICATION_JSON).content(phoneNumberEntry))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName(
      "Add one phone number providing name of length greater than 100 - response status code: 400")
  @Order(5)
  public void
      givenPhoneNumbers_whenAddPhoneNumberProvidingNameOfLengthGreaterThan100_thenStatus400()
          throws Exception {
    char[] charArray = new char[101];
    Arrays.fill(charArray, 'z');
    String str = new String(charArray);
    String phoneNumberEntry = "{\"name\":\"" + str + "\", \"phoneNumber\":\"+380 (407) 735-7954\"}";
    mvc.perform(
            post("/phoneNumber").contentType(MediaType.APPLICATION_JSON).content(phoneNumberEntry))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName(
      "Add one phone number providing phone number of length less than 7 - response status code: 400")
  @Order(6)
  public void
      givenPhoneNumbers_whenAddPhoneNumberProvidingPhoneNumberOfLengthLessThan7_thenStatus400()
          throws Exception {
    char[] charArray = new char[6];
    Arrays.fill(charArray, '8');
    String str = new String(charArray);
    String phoneNumberEntry = "{\"name\":\"Claiborne Willson\", \"phoneNumber\":\"" + str + "\"}";
    mvc.perform(
            post("/phoneNumber").contentType(MediaType.APPLICATION_JSON).content(phoneNumberEntry))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName(
      "Add one phone number providing phone number of length greater than 25 - response status code: 400")
  @Order(7)
  public void
      givenPhoneNumbers_whenAddPhoneNumberWithPhoneNumberOfLengthGreaterThan25_thenStatus400()
          throws Exception {
    char[] charArray = new char[26];
    Arrays.fill(charArray, '8');
    String str = new String(charArray);
    String phoneNumberEntry = "{\"name\":\"Claiborne Willson\", \"phoneNumber\":\"" + str + "\"}";
    mvc.perform(
            post("/phoneNumber").contentType(MediaType.APPLICATION_JSON).content(phoneNumberEntry))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName(
      "Add one phone number providing phone number with invalid format - response status code: 400")
  @Order(8)
  public void
      givenPhoneNumbers_whenAddPhoneNumberProvidingPhoneNumberWithInvalidFormat_thenStatus400()
          throws Exception {
    String phoneNumberEntry = "{\"name\":\"Claiborne Willson\", \"phoneNumber\":\"+44324 244 ab\"}";
    mvc.perform(
            post("/phoneNumber").contentType(MediaType.APPLICATION_JSON).content(phoneNumberEntry))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Update one phone number - response status code: 200")
  @Order(9)
  public void givenPhoneNumbers_whenUpdatePhoneNumberProvidingId11_thenStatus200()
      throws Exception {
    String phoneNumberEntry =
        "{\"name\":\"Gustav Hartup\", \"phoneNumber\":\"+380 (407) 735-7954\"}";
    mvc.perform(
            put("/phoneNumber/11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(phoneNumberEntry))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Delete one phone number - response status code: 200")
  @Order(10)
  public void givenPhoneNumbers_whenDeletePhoneNumberProvidingId11_thenStatus200()
      throws Exception {
    requestDeletePhoneNumber(11);
  }

  @Test
  @DisplayName("Get empty list of phone numbers - response status code: 204")
  @Order(11)
  public void givenPhoneNumbers_whenDeleteAllPhoneNumbers_whenGetAllPhoneNumbers_thenStatus204()
      throws Exception {
    for (int i = 1; i <= 10; i++) {
      requestDeletePhoneNumber(i);
    }

    mvc.perform(get("/phoneNumber").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(0)));
  }

  private void requestDeletePhoneNumber(int id) throws Exception {
    mvc.perform(delete("/phoneNumber/" + id)).andExpect(status().isOk());
  }
}
