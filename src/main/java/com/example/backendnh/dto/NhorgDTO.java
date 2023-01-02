package com.example.backendnh.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.backendnh.po.Nhorg;
import com.example.backendnh.po.PO;
import com.example.backendnh.vo.NhorgVO;
import com.example.backendnh.vo.VO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NhorgDTO implements DTO{
    Integer id;

    String orgname;

    Integer parentid;

    String orgorder;

    String orgdes;

    @Override
    public Nhorg toPO() {
        Nhorg nhorg=new Nhorg();
        nhorg.setId(id);
        nhorg.setOrgname(orgname);
        nhorg.setParentid(parentid);
        nhorg.setOrgorder(orgorder);
        nhorg.setOrgdes(orgdes);
        return nhorg;
    }

    @Override
    public NhorgVO toVO() {
        NhorgVO nhorgVO=new NhorgVO();
        nhorgVO.setId(id);
        nhorgVO.setOrgname(orgname);
        nhorgVO.setParentid(parentid);
        nhorgVO.setOrgorder(orgorder);
        nhorgVO.setOrgdes(orgdes);
        return nhorgVO;
    }
}
