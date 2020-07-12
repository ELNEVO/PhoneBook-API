package com.neville.phonebook.dao.mapper;

import com.neville.phonebook.model.PhoneNumberEntry;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PhoneNumberRowMapper implements RowMapper<PhoneNumberEntry> {

  private static final String ID = "ID";
  private static final String NAME = "NAME";
  private static final String PHONE_NUMBER = "PHONE_NUMBER";

  @Override
  public PhoneNumberEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new PhoneNumberEntry(rs.getInt(ID), rs.getString(NAME), rs.getString(PHONE_NUMBER));
  }
}
