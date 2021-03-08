package com.github.config.server.dto;

import java.util.Objects;

public class PropertiesDto {

    private Long id;

    private String application;

    private String key;

    private String value;

    public PropertiesDto() {
    }

    public PropertiesDto(Long id, String application, String key, String value) {
        this.id = id;
        this.application = application;
        this.key = key;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getApplication() {
        return application;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertiesDto that = (PropertiesDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(application, that.application) &&
                Objects.equals(key, that.key) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, application, key, value);
    }

    @Override
    public String toString() {
        return "PropertiesDto{" +
                "id=" + id +
                ", application='" + application + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
