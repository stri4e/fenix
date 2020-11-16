package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 5693842243570892367L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "owner")
    private String owner;

    @NotBlank
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @JsonProperty(value = "fullName")
    private String fullName;

    @NotNull
    @JsonProperty(value = "pow")
    private Integer pow;

    @NotNull
    @JsonProperty(value = "assetType")
    private AssetType assetType;

    public AssetDto(@NotBlank String owner,
                    @NotBlank String name,
                    @NotBlank String fullName,
                    @NotNull Integer pow,
                    @NotNull AssetType assetType) {
        this.owner = owner;
        this.name = name;
        this.fullName = fullName;
        this.pow = pow;
        this.assetType = assetType;
    }
}

