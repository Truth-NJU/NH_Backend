package com.example.backendnh.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NhfullquerylogDTO;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("nh_fullquerylog")
public class Nhfullquerylog implements Serializable,PO {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField("querybatch")
    String querybatch;

    @TableField("hotword")
    String hotword;

    @TableField("querytime")
    Date querytime;

    @TableField("queryman")
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