package com.example.backendnh.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NhorgDTO;
import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("nh_org")
public class Nhorg implements Serializable,PO {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField("orgname")
    String orgname;

    @TableField("parentid")
    Integer parentid;

    @TableField("orgorder")
    String orgorder;

    @TableField("orgdes")
    String orgdes;

    @Override
    public NhorgDTO toDTO() {
        NhorgDTO nhorgDTO=new NhorgDTO();
        nhorgDTO.setId(id);
        nhorgDTO.setOrgname(orgname);
        nhorgDTO.setParentid(parentid);
        nhorgDTO.setOrgorder(orgorder);
        nhorgDTO.setOrgdes(orgdes);
        return nhorgDTO;
    }
}
