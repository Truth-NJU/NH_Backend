package com.example.backendnh.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.backendnh.po.Nharchattach;
import com.example.backendnh.po.PO;
import com.example.backendnh.vo.NharchattachVO;
import com.example.backendnh.vo.VO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NharchattachDTO implements DTO{
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
    public Nharchattach toPO() {
        Nharchattach nharchattach=new Nharchattach();
        nharchattach.setId(id);
        nharchattach.setDaid(daid);
        nharchattach.setTpmc(tpmc);
        nharchattach.setTplx(tplx);
        nharchattach.setTpcd(tpcd);
        nharchattach.setTpkd(tpkd);
        nharchattach.setTpdx(tpdx);
        nharchattach.setPsrq(psrq);
        nharchattach.setPx(px);
        nharchattach.setTplj(tplj);
        nharchattach.setTplstmc(tplstmc);
        return nharchattach;
    }

    @Override
    public NharchattachVO toVO() {
        NharchattachVO nharchattachVO=new NharchattachVO();
        nharchattachVO.setId(id);
        nharchattachVO.setDaid(daid);
        nharchattachVO.setTpmc(tpmc);
        nharchattachVO.setTplx(tplx);
        nharchattachVO.setTpcd(tpcd);
        nharchattachVO.setTpkd(tpkd);
        nharchattachVO.setTpdx(tpdx);
        nharchattachVO.setPsrq(psrq);
        nharchattachVO.setPx(px);
        nharchattachVO.setTplj(tplj);
        nharchattachVO.setTplstmc(tplstmc);
        return nharchattachVO;
    }
}
