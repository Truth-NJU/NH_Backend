package com.example.backendnh.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.backendnh.dto.DTO;
import com.example.backendnh.dto.NhclassesDTO;
import lombok.*;

@Getter
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class NhclassesVO implements VO{
    Integer id;

    String classid;

    String classcn;

    Integer parentid;

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
