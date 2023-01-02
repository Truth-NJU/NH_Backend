package com.example.backendnh.vo;

import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NharchattachDTO;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class NharchattachVO implements VO {

    Integer id;

    Integer daid; // 档案id

    String tpmc;

    String tplx;

    Integer tpcd;

    Integer tpkd;

    Integer tpdx;

    Date psrq;

    String px;

    String tplj;

    String tplstmc;


    @Override
    public NharchattachDTO toDTO() {
        NharchattachDTO nharchattachDTO=new NharchattachDTO();
        nharchattachDTO.setId(id);
        nharchattachDTO.setDaid(daid);
        nharchattachDTO.setTpmc(tpmc);
        nharchattachDTO.setTplx(tplx);
        nharchattachDTO.setTpcd(tpcd);
        nharchattachDTO.setTpkd(tpkd);
        nharchattachDTO.setTpdx(tpdx);
        nharchattachDTO.setPsrq(psrq);
        nharchattachDTO.setPx(px);
        nharchattachDTO.setTplj(tplj);
        nharchattachDTO.setTplstmc(tplstmc);
        return nharchattachDTO;
    }
}
