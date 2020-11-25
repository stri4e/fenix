package com.github.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -1183268148292900990L;

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "profile")
    private ProfileDto profile;

    @NotNull
    @JsonProperty(value = "contact")
    private ContactDto contact;

    @NotNull
    @JsonProperty(value = "address")
    private AddressDto address;

    @JsonProperty(value = "views")
    private List<ProductDto> views;

    public AccountDto and(Runnable runnable) {
        CompletableFuture.runAsync(runnable);
        return this;
    }

}
