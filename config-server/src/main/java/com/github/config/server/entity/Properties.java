package com.github.config.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "properties")
public class Properties implements Serializable {

    private static final long serialVersionUID = -6984528543373167139L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String application;

    private String profile;

    private String label = "latest";

    private String key;

    @Column(columnDefinition = "TEXT")
    private String value;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    @Column(name = "props_type")
    @Enumerated(EnumType.STRING)
    private PropsType propsType = PropsType.def;

    public Properties() {
    }

    public Properties(String application, String profile, String key, String value) {
        this.application = application;
        this.profile = profile;
        this.key = key;
        this.value = value;
    }

    public Properties(String application, String profile, String key, String value, PropsType propsType) {
        this.application = application;
        this.profile = profile;
        this.key = key;
        this.value = value;
        this.propsType = propsType;
    }

    public Long getId() {
        return id;
    }

    public String getApplication() {
        return application;
    }

    public String getProfile() {
        return profile;
    }

    public String getLabel() {
        return label;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public EntityStatus getStatus() {
        return status;
    }

    public PropsType getPropsType() {
        return propsType;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Properties that = (Properties) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(application, that.application) &&
                Objects.equals(profile, that.profile) &&
                Objects.equals(label, that.label) &&
                Objects.equals(key, that.key) &&
                Objects.equals(value, that.value) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, application, profile, label, key, value, status);
    }

    @Override
    public String toString() {
        return "Properties{" +
                "id=" + id +
                ", application='" + application + '\'' +
                ", profile='" + profile + '\'' +
                ", label='" + label + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", status=" + status +
                '}';
    }
}
