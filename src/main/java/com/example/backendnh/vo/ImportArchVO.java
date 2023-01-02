package com.example.backendnh.vo;

import com.example.backendnh.dto.DTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ImportArchVO implements VO{
    List<String> filepathList;

    @Override
    public DTO toDTO() {
        return null;
    }
}
