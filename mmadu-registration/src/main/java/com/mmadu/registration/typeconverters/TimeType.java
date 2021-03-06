package com.mmadu.registration.typeconverters;

import com.mmadu.registration.entities.FieldType;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@DataFieldType(name = "time")
public class TimeType extends AbstractFieldTypeConverter {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_TIME;

    public TimeType(FieldType fieldType) {
        super(fieldType);
        Optional.ofNullable(fieldType.getFieldTypePattern())
                .ifPresent(pattern -> {
                    dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
                });
    }

    @Override
    public Object convertToObject(String text) throws FieldConversionException {
        try {
            return LocalTime.parse(text, dateTimeFormatter);
        } catch (DateTimeParseException ex) {
            throw new FieldConversionException(ex.getMessage());
        }
    }

    @Override
    public void validate(String text) throws FieldValidationException {
        try {
            LocalTime time = LocalTime.parse(text, dateTimeFormatter);
            String min = fieldType.getMin();
            if (!StringUtils.isEmpty(min) && time.isBefore(LocalTime.parse(min, dateTimeFormatter))) {
                throw new FieldValidationException("Value is earlier than minimum time");
            }
            String max = fieldType.getMax();
            if (!StringUtils.isEmpty(max) && time.isAfter(LocalTime.parse(max, dateTimeFormatter))) {
                throw new FieldValidationException("Value is later than maximum time");
            }
        } catch (Exception ex) {
            throw new FieldValidationException(ex.getMessage());
        }
    }
}
