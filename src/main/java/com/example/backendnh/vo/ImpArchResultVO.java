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
public class ImpArchResultVO implements VO {
    Integer iSuccess;
    Integer iFail;
    Integer iExist;

    List<String> alError;
    List<String> alSuccess;
    List<String> alExist;

    @Override
    public DTO toDTO() {
        return null;
    }
}
