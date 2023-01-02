package com.example.backendnh.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NharchivesDTO;
import com.example.backendnh.vo.NharchivesVO;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("nh_archives")
public class Nharchives implements Serializable ,PO{
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField("bh")
    String bh;

    @TableField("damc")
    String damc;

    @TableField("jm")
    String jm;

    @TableField("wb")
    String wb;

    @TableField("flh")
    String flh;

    @TableField("sy")
    String sy;

    @TableField("gjc")
    String gjc;

    @TableField("zrh")
    String zrh;

    @TableField("ywdt")
    String ywdt;

    @TableField("yslh")
    String yslh;

    @TableField("xgdy")
    String xgdy;

    @TableField("gjrw")
    String gjrw;

    @TableField("szdajh")
    String szdajh;

    @TableField("fwrq")
    String fwrq;

    @TableField("swrq")
    String swrq;

    @TableField("csrq")
    String csrq;

    @TableField("fwjg")
    String fwjg;

    @TableField("swjg")
    String swjg;

    @TableField("gcdw")
    String gcdw;

    @TableField("hf")
    Integer hf;

    @TableField("bclj")
    String bclj;

    @TableField("wzjg")
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
