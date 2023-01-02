package com.example.backendnh.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.backendnh.dto.ArchmarkDTO;
import com.example.backendnh.dto.DTO;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("nh_archremark")
public class Archmark implements Serializable, PO {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField("archid")
    Integer archid;

    @TableField("archname")
    String archname;

    @TableField("remarkname")
    String remarkname; // 反馈人

    @TableField("remarktime")
    Date remarktime;

    @TableField("remarkcontent")
    String remarkcontent;

    @TableField("isview")
    String isview;

    @Override
    public ArchmarkDTO toDTO() {
        ArchmarkDTO archmarkDTO = new ArchmarkDTO();
        archmarkDTO.setId(id);
        archmarkDTO.setArchid(archid);
        archmarkDTO.setArchname(archname);
        archmarkDTO.setRemarkname(remarkname);
        archmarkDTO.setRemarktime(remarktime);
        archmarkDTO.setRemarkcontent(remarkcontent);
        archmarkDTO.setIsview(isview);
        return archmarkDTO;
    }
}