package com.neville.phonebook.dao;

import com.neville.phonebook.dao.mapper.PhoneNumberRowMapper;
import com.neville.phonebook.exception.RecordNotFoundException;
import com.neville.phonebook.model.PhoneNumberEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberDao {

  private static final String TABLE_NAME = "PHONE_NUMBERS";
  private static final String GET_ALL = "SELECT * FROM " + TABLE_NAME;
  private static final String GET_ONE = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
  private static final String UPDATE =
      "UPDATE " + TABLE_NAME + " SET name = ?, phone_number = ? WHERE id = ?";
  private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

  private static final String ID = "ID";
  private static final String NAME = "NAME";
  private static final String PHONE_NUMBER = "PHONE_NUMBER";

  private static final String RECORD_NOT_FOUND = "No phone number entry found with id %d";

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public PhoneNumberDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<PhoneNumberEntry> getAll() {
    return jdbcTemplate.query(GET_ALL, new PhoneNumberRowMapper());
  }

  public PhoneNumberEntry getOne(int id) {
    try {
      return jdbcTemplate.queryForObject(GET_ONE, new PhoneNumberRowMapper(), id);
    } catch (DataAccessException ex) {
      throw new RecordNotFoundException(String.format(RECORD_NOT_FOUND, id));
    }
  }

  public int create(PhoneNumberEntry phoneNumberEntry) {
    SimpleJdbcInsert simpleJdbcInsert =
        new SimpleJdbcInsert(this.jdbcTemplate)
            .withTableName(TABLE_NAME)
            .usingGeneratedKeyColumns(ID);

    Map<String, Object> parameters = new HashMap<>();
    parameters.put(NAME, phoneNumberEntry.getName());
    parameters.put(PHONE_NUMBER, phoneNumberEntry.getPhoneNumber());

    return simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
  }

  public int update(PhoneNumberEntry phoneNumberEntry) {
    return jdbcTemplate.update(
        UPDATE,
        phoneNumberEntry.getName(),
        phoneNumberEntry.getPhoneNumber(),
        phoneNumberEntry.getId());
  }

  public int delete(int id) {
    return jdbcTemplate.update(DELETE, id);
  }
}
