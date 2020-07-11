package com.github.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersStatisticsDto {
    private Long id;
    private Long userId;
    private Date lastLoginDate;
    private Integer count;
}
