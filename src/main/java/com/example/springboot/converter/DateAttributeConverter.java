package com.example.springboot.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Date;

@Converter(autoApply = true)
public class DateAttributeConverter implements AttributeConverter<Date, String> {

    @Override
    public String convertToDatabaseColumn(Date date) {
        if (date == null) {
            return null;
        }
        // Convert java.sql.Date to String for storage
        return date.toString();
    }

    @Override
    public Date convertToEntityAttribute(String dateString) {
        if (dateString == null || dateString.equals("0000-00-00")) {
            return null; // Use a default value or null
        }
        // Convert String back to java.sql.Date
        return Date.valueOf(dateString);
    }
}