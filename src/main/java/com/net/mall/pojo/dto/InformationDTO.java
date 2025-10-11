package com.net.mall.pojo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InformationDTO {

    private LocalDate birthday;

    private Long userId;

}
