package com.example.backendnh.vo;

import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NharchivesDTO;
import lombok.*;

@Getter
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NharchivesVO implements VO{
    Integer id;
    String bh;
    String damc;
    String jm;
    String wb;
    String flh;
    String sy;
    String gjc;
    String zrh;
    String ywdt;
    String yslh;
    String xgdy;
    String gjrw;
    String szdajh;
    String fwrq;
    String swrq;
    String csrq;
    String fwjg;
    String swjg;
    String gcdw;
    Integer hf;
    String bclj;
    String wzjg;

    @Override
    public NharchivesDTO toDTO() {
        NharchivesDTO nharchivesDTO=new NharchivesDTO();
        nharchivesDTO.setId(id);
        nharchivesDTO.setBh(bh);
        nharchivesDTO.setDamc(damc);
        nharchivesDTO.setJm(jm);
        nharchivesDTO.setWb(wb);
        nharchivesDTO.setFlh(flh);
        nharchivesDTO.setSy(sy);
        nharchivesDTO.setGjc(gjc);
        nharchivesDTO.setZrh(zrh);
        nharchivesDTO.setYwdt(ywdt);
        nharchivesDTO.setYslh(yslh);
        nharchivesDTO.setXgdy(xgdy);
        nharchivesDTO.setGjrw(gjrw);
        nharchivesDTO.setSzdajh(szdajh);
        nharchivesDTO.setFwrq(fwrq);
        nharchivesDTO.setSwrq(swrq);
        nharchivesDTO.setCsrq(csrq);
        nharchivesDTO.setFwjg(fwjg);
        nharchivesDTO.setSwjg(swjg);
        nharchivesDTO.setGcdw(gcdw);
        nharchivesDTO.setHf(hf);
        nharchivesDTO.setBclj(bclj);
        nharchivesDTO.setWzjg(wzjg);
        return nharchivesDTO;
    }
}
