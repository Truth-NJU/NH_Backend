package com.example.backendnh.vo;

import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NhorgDTO;
import lombok.*;

@Getter
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NhorgVO implements VO {
    Integer id;

    String orgname;

    Integer parentid;

    String orgorder;

    String orgdes;

    @Override
    public NhorgDTO toDTO() {
        NhorgDTO nhorgDTO = new NhorgDTO();
        nhorgDTO.setId(id);
        nhorgDTO.setOrgname(orgname);
        nhorgDTO.setParentid(parentid);
        nhorgDTO.setOrgorder(orgorder);
        nhorgDTO.setOrgdes(orgdes);
        return nhorgDTO;
    }
}
