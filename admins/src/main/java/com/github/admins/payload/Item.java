package com.github.admins.payload;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public abstract class Item implements Serializable, Cloneable {

    private static final long serialVersionUID = -7416895101550698061L;

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    private String description;

    private String previewImage;

    private List<String> images = new ArrayList<>();

    private Date createDate;

}
