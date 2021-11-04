package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "roles")
public class Role implements Serializable {

    private static final long serialVersionUID = -5524420904663290354L;

    @Id
    private String id;

    private String role;

    private EntityStatus status = EntityStatus.on;

    @CreatedBy
    private UUID createBy;

    @LastModifiedBy
    private UUID updateBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public Role(@NotBlank(message = "Role is Required") String role) {
        this.role = role;
    }

    public Role(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role role(String role) {
        this.role = role;
        return this;
    }

    public Role status(EntityStatus status) {
        this.status = status;
        return this;
    }

}
