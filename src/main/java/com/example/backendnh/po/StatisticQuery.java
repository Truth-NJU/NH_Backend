package com.example.backendnh.po;

import com.example.backendnh.dto.DTO;
import lombok.*;

import java.io.Serializable;

/**
 * 查询词频统计
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StatisticQuery{
    String statistic;

    Integer statisticCount;
}
