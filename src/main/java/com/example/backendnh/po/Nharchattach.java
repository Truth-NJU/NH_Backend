package com.example.backendnh.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NharchattachDTO;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("nh_archattach")
public class Nharchattach implements Serializable,PO {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField("daid")
    Integer daid; // 档案id

    @TableField("tpmc")
    String tpmc;

    @TableField("tplx")
    String tplx;

    @TableField("tpcd")
    Integer tpcd;

    @TableField("tpkd")
    Integer tpkd;

    @TableField("tpdx")
    Integer tpdx;

    @TableField("psrq")
    Date psrq;

    @TableField("px")
    String px;

    @TableField("tplj")
    String tplj;

    @TableField("tplstmc")
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