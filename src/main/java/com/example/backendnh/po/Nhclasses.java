package com.example.backendnh.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NhclassesDTO;
import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("nh_classes")
public class Nhclasses implements Serializable ,PO{
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField("classid")
    String classid;

    @TableField("classcn")
    String classcn;

    @TableField("parentid")
    Integer parentid;

    @TableField("classdes")
    String classdes;

    @Override
    public NhclassesDTO toDTO() {
        NhclassesDTO nhclassesDTO=new NhclassesDTO();
        nhclassesDTO.setId(id);
        nhclassesDTO.setClassid(classid);
        nhclassesDTO.setClasscn(classcn);
        nhclassesDTO.setParentid(parentid);
        nhclassesDTO.setClassdes(classdes);
        return nhclassesDTO;
    }
}