package com.mmadu.registration.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@EqualsAndHashCode
@ToString
public class UserForm {
    private Map<String, String> properties = new HashMap<>();

    public void set(String property, String value) {
        properties.put(property, value);
    }

    public Optional<String> get(String property) {
        return Optional.ofNullable(properties.get(property));
    }
}