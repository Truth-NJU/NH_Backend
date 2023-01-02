package com.example.backendnh.vo;

import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NhfullquerylogDTO;
import lombok.*;

import java.util.Date;


@Getter
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NhfullquerylogVO implements VO{
    Integer id;

    String querybatch;

    String hotword;

    Date querytime;

    String queryman;


    @Override
    public NhfullquerylogDTO toDTO() {
        NhfullquerylogDTO nhfullquerylogDTO=new NhfullquerylogDTO();
        nhfullquerylogDTO.setId(id);
        nhfullquerylogDTO.setQuerybatch(querybatch);
        nhfullquerylogDTO.setHotword(hotword);
        nhfullquerylogDTO.setQuerytime(querytime);
        nhfullquerylogDTO.setQueryman(queryman);
        return nhfullquerylogDTO;
    }
}
