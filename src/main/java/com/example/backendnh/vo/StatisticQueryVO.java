package com.example.backendnh.vo;

import com.example.backendnh.dto.DTO;
import lombok.*;

@Getter
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class StatisticQueryVO implements VO {
    String statistic;

    Integer statisticCount;

    @Override
    public DTO toDTO() {
        return null;
    }
}
