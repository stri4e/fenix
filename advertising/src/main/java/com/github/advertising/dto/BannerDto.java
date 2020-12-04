package com.github.advertising.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 7026249164639884522L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "image")
    private String image;

}
