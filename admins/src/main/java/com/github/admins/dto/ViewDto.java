package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -2229153044114772408L;

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "createAt")
    private Date createAt;

    @JsonProperty(value = "products")
    private List<ProductDto> products;

}
